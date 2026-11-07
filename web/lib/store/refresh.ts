import { weTrakrClient } from "../wetrakr";
// App store (lib/store.tsx): refreshData (the cloud + tracker pull behind Continue Watching, the watchlist, addons and
// settings) and the triggers that run it.
import { hasPendingAddons, flushAddonOutbox, pendingAddonSnapshot } from "../addonOutbox";
import { applyPendingWatchlist, flushWatchlistOutbox } from "../watchlistOutbox";
import { useCallback, useEffect } from "react";
import { shouldRefreshAutomatically } from "../automaticRefresh";
import { loadLocalAddons, normalizeAddons, saveLocalAddons } from "../addons";
import { mergeCatalogs } from "../catalogs";
import { getContinueWatching, pullCloudContinueWatchingDismissals, pullCloudPayload, pullCloudTrackingSelection, pullCloudWatchedKeys, pullCloudWatchlist, saveCloudAddons, saveCloudTrackingSelection } from "../cloud";
import { completionTimes, includeIptvContinueWatching, mergePartialContinueWatching, mergeTrackerContinueWatching, pruneCompletedResume } from "../continueWatching";
import { episodeAvailabilityKey } from "../episodeAvailability";
import { savePlaylists } from "../iptv";
import { dedupeMedia, historyToItem, hydrateTraktItems, traktItemToMedia, traktPlaybackToMedia } from "../mappers";
import { removeStored } from "../storage";
import { flushSettingsOutbox, hasPendingSettings, settingsWithPendingEdits } from "../settingsOutbox";
import { continueWatchingActivitySignature, createTraktActivityCheck } from "../traktActivity";
import { mdblistClient } from "../mdblist";
import { simklClient } from "../simkl";
import { defaultTrackingPreferences, loadTrackingPreferences, readsFrom, sameTrackingSources, saveTrackingPreferences, syncClient } from "../sync";
import type { MediaItem } from "../types";
import { authClient, traktClient } from "./clients";
import { cwCacheKeyFor, readCachedList, saveCachedList, watchlistCacheKeyFor } from "./listCache";
import { defaultSettings, sameSettings } from "./settings";
import { filterWatchedContinueWatching, hydrateContinueWatchingItems, isPausedPlaybackItem, loadTraktUpNext, mergeTraktWithLocalResume, traktWatchedKeys, validateContinueWatchingEpisodes } from "./watchHistory";
import type { StoreCore } from "./core";

export function useRefreshData(store: StoreCore) {
  const { activeProfileId, activeProfileIdRef, personalAddonsRef: addonsRef, cwSourceRef, lastRefreshStartedRef, lastSyncedSettingsRef, refreshGenerationRef, refreshInFlightRef, refreshKeyRef, setAddons, setAddonsReady, setBusy, setCatalogConfigs, setCategories, setContinueWatching, setMdblistConnected, setSettings, setSettingsSyncState, setSimklConnected, setToast, setTrackingPreferences, setTraktConnected, setWatchedKeys, setWatchlist, personalSettingsRef: settingsRef, managed, traktActivityRef, watchedKeys } = store;
  const refreshData = useCallback((profileIdOverride?: string | null, background = false) => {
    const profileId = profileIdOverride ?? activeProfileId;
    const accountId = authClient.session?.userId;
    const key = `${accountId ?? "local"}:${profileId ?? "no-profile"}`;
    refreshKeyRef.current = key;
    const existing = refreshInFlightRef.current;
    if (existing?.key === key) return existing.promise;
    lastRefreshStartedRef.current = performance.now();
    const generation = ++refreshGenerationRef.current;
    const isCurrent = () => refreshGenerationRef.current === generation && refreshKeyRef.current === key &&
      activeProfileIdRef.current === profileId && authClient.session?.userId === accountId;
    traktClient.setProfile(profileId);
    mdblistClient.setProfile(profileId);
    simklClient.setProfile(profileId);
    weTrakrClient.setProfile(profileId);
    setTrackingPreferences(loadTrackingPreferences(profileId));
    setTraktConnected(traktClient.isConnected);
    setMdblistConnected(mdblistClient.isConnected);
    setSimklConnected(simklClient.isConnected);
    // Capture the version BEFORE history reads; otherwise a watch arriving mid-
    // refresh could be acknowledged without ever fetching that updated history.
    const activityRead = readsFrom("continueWatching", "trakt")
      ? traktClient.continueWatchingActivity().catch(() => null) : Promise.resolve(null);
    const run = (async () => {
      const currentSettings = settingsRef.current;
      if (!background) {
        setAddonsReady(false);
        setBusy("Syncing catalogs");
      }
      // Paint Continue Watching instantly from the last known list for this
      // profile — the fresh Trakt fetch replaces it seconds later. Without
      // this the rail sits empty while up to ~17 Trakt calls round-trip.
      const cwCacheKey = cwCacheKeyFor(profileId);
      const watchlistCacheKey = watchlistCacheKeyFor(profileId);
      try {
        const cachedCw = readCachedList(cwCacheKey);
        if (cachedCw.length && cwSourceRef.current === "none") {
          cwSourceRef.current = "seed";
          setContinueWatching(cachedCw);
          setCategories((current) => current.some((c) => c.id === "continue_watching")
            ? current
            : [{ id: "continue_watching", title: "Continue Watching", items: cachedCw }, ...current]);
        }
        // Same instant-paint for the watchlist grid — without this it sits blank
        // until ~15 Trakt calls round-trip.
        const cachedWatchlist = applyPendingWatchlist(authClient, profileId, readCachedList(watchlistCacheKey));
        if (cachedWatchlist.length) {
          setWatchlist((current) => current.length ? current : cachedWatchlist);
        }
      } catch {
        // Cache read must never block the refresh.
      }
      try {
      await Promise.all([simklClient.restoreSession().catch(() => undefined), weTrakrClient.restoreSession().catch(() => undefined)]);
      if (!isCurrent()) return;
      setSimklConnected(simklClient.isConnected);
      await flushAddonOutbox(authClient).catch(() => undefined);
      await flushWatchlistOutbox(authClient).catch(() => undefined);
      const localAddons = loadLocalAddons();
      await flushSettingsOutbox(authClient).catch(() => setSettingsSyncState("error"));
      const cloud = authClient.session && !hasPendingSettings(authClient, profileId) ? await pullCloudPayload(authClient, profileId).catch(() => null) : null;
      let effectiveSettings = currentSettings;
      if (authClient.session && profileId) {
        const cloudTracking = await pullCloudTrackingSelection(authClient, profileId).catch(() => null);
        if (!isCurrent()) return;
        if (cloudTracking) {
          if (!cloudTracking.hasCloudState) {
            const localPreferences = loadTrackingPreferences(profileId);
            const localTracking = traktClient.token || simklClient.token || mdblistClient.isConnected
              ? {
                  provider: traktClient.token ? "TRAKT" as const : simklClient.token ? "SIMKL" as const : "MDBLIST" as const,
                  traktToken: traktClient.token,
                  mdbListApiKey: mdblistClient.key,
                  mdbListAccessToken: mdblistClient.token?.accessToken ?? null,
                  mdbListRefreshToken: mdblistClient.token?.refreshToken ?? null,
                  mdbListTokenExpiresAt: mdblistClient.token?.expiresAt ?? null,
                  simklToken: simklClient.token,
                  trackingPreferences: localPreferences
                }
              : null;
            if (localTracking) {
              await saveCloudTrackingSelection(authClient, profileId, localTracking).catch(() => undefined);
              if (!isCurrent()) return;
            }
          } else {
            traktClient.setToken(cloudTracking.traktToken);
            if (!simklClient.isV2Connected) simklClient.setToken(cloudTracking.simklToken);
            mdblistClient.setKey(cloudTracking.mdbListApiKey);
            if (cloudTracking.mdbListAccessToken) {
              mdblistClient.setToken({
                accessToken: cloudTracking.mdbListAccessToken,
                refreshToken: cloudTracking.mdbListRefreshToken,
                expiresAt: cloudTracking.mdbListTokenExpiresAt
              });
            } else {
              mdblistClient.setToken(null);
            }
            const preferences = cloudTracking.trackingPreferences ?? defaultTrackingPreferences();
            saveTrackingPreferences(profileId, preferences);
            setTrackingPreferences(preferences);
          }
          setTraktConnected(traktClient.isConnected);
          setSimklConnected(simklClient.isConnected);
          setMdblistConnected(mdblistClient.isConnected);
        }
      }
      if (!isCurrent()) return;
      // A user can edit favorites while the cloud/tracker requests above are
      // in flight. Do not replace those edits with the earlier cloud response.
      const settingsChangedDuringPull = settingsRef.current !== currentSettings || hasPendingSettings(authClient, profileId);
      if (cloud?.settings) {
        effectiveSettings = {
          ...defaultSettings,
          ...currentSettings,
          ...cloud.settings,
          catalogs: mergeCatalogs(cloud.settings?.catalogs ?? currentSettings.catalogs, cloud.settings?.hiddenCatalogIds ?? currentSettings.hiddenCatalogIds,
            cloud.settings?.hiddenAddonCatalogIds ?? currentSettings.hiddenAddonCatalogIds),
          iptvPlaylists: cloud.settings?.iptvPlaylists ?? currentSettings.iptvPlaylists,
          favoriteChannelIds: cloud.settings?.favoriteChannelIds ?? currentSettings.favoriteChannelIds,
          iptvTvSession: cloud.settings?.iptvTvSession ?? currentSettings.iptvTvSession,
          favoriteGroupIds: cloud.settings?.favoriteGroupIds ?? currentSettings.favoriteGroupIds,
          hiddenGroupIds: cloud.settings?.hiddenGroupIds ?? currentSettings.hiddenGroupIds,
          lockedIptvGroupIds: cloud.settings?.lockedIptvGroupIds ?? currentSettings.lockedIptvGroupIds,
          groupOrder: cloud.settings?.groupOrder ?? currentSettings.groupOrder
        };
        // Acknowledge only the server snapshot. If the user edited during this
        // request, retain the live settings and let the queued edit finish saving.
        lastSyncedSettingsRef.current = JSON.stringify({ settings: effectiveSettings, activeProfileId: profileId });
        if (settingsChangedDuringPull) {
          effectiveSettings = settingsWithPendingEdits(authClient, profileId, effectiveSettings, currentSettings, settingsRef.current);
        }
        if (!sameSettings(settingsRef.current, effectiveSettings)) setSettings(effectiveSettings);
        savePlaylists(effectiveSettings.iptvPlaylists);
      } else if (settingsChangedDuringPull) {
        effectiveSettings = settingsRef.current;
      }
      // Addon-wipe protection. Prefer cloud, fall back to local, but NEVER let a
      // failed/empty pull replace a non-empty list. A null `cloud` means the pull
      // errored (Cloudflare-challenged the request, offline, etc.) — treat that as
      // "keep what we have", not "the library is empty".
      const cloudAddons = normalizeAddons(cloud?.addons ?? []);
      const localNormalized = normalizeAddons(localAddons);
      const currentAddons = normalizeAddons(addonsRef.current);
      const source = pendingAddonSnapshot(authClient) ?? (cloudAddons.length
        ? cloudAddons
        : localNormalized.length
          ? localNormalized
          : currentAddons);
      // If the pull explicitly returned an EMPTY cloud list but we still hold
      // addons locally, the cloud is stale/partial — self-heal by pushing our
      // list back up instead of wiping.
      const cloudReturnedEmpty = cloud !== null && cloudAddons.length === 0;
      const addonState = source.map((addon) => ({
        ...addon,
        enabled: !effectiveSettings.disabledAddonIds.includes(addon.id) && addon.enabled !== false
      }));
      setAddons(addonState);
      setAddonsReady(true);
      // Only persist locally when we actually have addons — an empty list can't
      // overwrite a good one.
      if (addonState.length) saveLocalAddons(addonState);
      if (cloudReturnedEmpty && source.length && authClient.session && !hasPendingAddons(authClient)) {
        void saveCloudAddons(authClient, source, profileId).catch(() => undefined);
      }

      effectiveSettings = managed.applySettings(effectiveSettings);
      const effectiveCatalogs = mergeCatalogs(effectiveSettings.catalogs, effectiveSettings.hiddenCatalogIds, effectiveSettings.hiddenAddonCatalogIds);
      setCatalogConfigs(effectiveCatalogs.filter((catalog) => catalog.enabled && catalog.sourceType !== "home-server"));

      const client = syncClient();
      const traktReady = client.isConnected;
      const activitySignature = continueWatchingActivitySignature(await activityRead);
      if (!isCurrent()) return;
      const readFailures = new Set<string>();
      const failedRead = (feature: string) => { readFailures.add(feature); return []; };
      const watchedMoviesRead = traktReady ? client.watched("movies").catch(() => failedRead("watched-movies")) : Promise.resolve([]);
      const watchedShowsRead = traktReady ? client.watched("shows").catch(() => failedRead("watched-shows")) : Promise.resolve([]);
      const cwUsesTrakt = readsFrom("continueWatching", "trakt");
      const cwOnlyTrakt = cwUsesTrakt && !readsFrom("continueWatching", "simkl") && !readsFrom("continueWatching", "mdblist");
      const sameHistorySources = sameTrackingSources("watched", "continueWatching");
      // Reconcile each successful history read before progress, watchlist or artwork requests finish.
      const readCwHistory = (type: "movies" | "shows", shared: Promise<unknown[]>) => {
        const read = sameHistorySources
          ? shared.then((rows) => {
            if (readFailures.has(`watched-${type}`)) readFailures.add(`cw-${type}`);
            return rows;
          })
          : client.watched(type, "continueWatching").catch(() => failedRead(`cw-${type}`));
        return read.then((rows) => {
          if (!isCurrent()) return rows;
          const completions = completionTimes(type === "movies" ? rows : [], type === "shows" ? rows : []);
          if (!completions.size) return rows;
          setContinueWatching((current) => pruneCompletedResume(current, completions));
          setCategories((current) => current.flatMap((category) => {
            if (category.id !== "continue_watching") return [category];
            const items = pruneCompletedResume(category.items, completions);
            return items.length ? [items === category.items ? category : { ...category, items }] : [];
          }));
          const cached = readCachedList(cwCacheKey);
          const pruned = pruneCompletedResume(cached, completions);
          if (pruned !== cached) {
            if (pruned.length) saveCachedList(cwCacheKey, pruned, 20);
            else removeStored(cwCacheKey);
          }
          return rows;
        });
      };
      const cwMoviesRead = traktReady ? readCwHistory("movies", watchedMoviesRead) : Promise.resolve([]);
      const cwShowsRead = traktReady ? readCwHistory("shows", watchedShowsRead) : Promise.resolve([]);
      // Per-show progress specifically needs Trakt history, even when both trackers supply CW.
      const cwTraktShowsRead = !cwUsesTrakt ? Promise.resolve([])
        : cwOnlyTrakt ? cwShowsRead
        : traktClient.watched("shows").catch(() => failedRead("cw-watched"));
      const watchlistReady = ["trakt", "simkl", "mdblist"].some((provider) => readsFrom("watchlist", provider as "trakt" | "simkl" | "mdblist"));
      const cloudWatchedRead = authClient.session ? pullCloudWatchedKeys(authClient, profileId).catch(() => new Set<string>()) : Promise.resolve(new Set<string>());
      const [historyRows, traktRows, playbackRows, watchedMoviesRows, watchedShowsRows, cloudWatchlistRows, cloudWatchedKeys, cloudDismissals, hiddenShowIds, cwMovies, cwShows] = await Promise.all([
        authClient.session ? getContinueWatching(authClient, profileId, addonState).catch(() => []) : Promise.resolve([]),
        traktReady ? client.watchlist().catch(() => failedRead("watchlist")) : Promise.resolve([]),
        traktReady ? cloudWatchedRead.then(keys => client.playback(keys)).catch(() => failedRead("playback")) : Promise.resolve([]),
        watchedMoviesRead,
        watchedShowsRead,
        authClient.session ? pullCloudWatchlist(authClient, profileId).catch(() => []) : Promise.resolve([]),
        cloudWatchedRead,
        authClient.session ? pullCloudContinueWatchingDismissals(authClient, profileId).catch(() => new Map<string, number>()) : Promise.resolve(new Map<string, number>()),
        // Only Trakt has a hidden-from-progress concept; MDBList reads return an
        // empty set so the filters below are no-ops for it.
        readsFrom("continueWatching", "trakt")
          ? traktClient.hiddenProgressShowIds().catch(() => { failedRead("cw-hidden"); return new Set<number>(); })
          : Promise.resolve(new Set<number>()),
        cwMoviesRead,
        cwShowsRead
      ]);
      if (!isCurrent()) return;
      const cwCompletions = completionTimes(cwMovies, cwShows);
      const cwWatchedKeys = traktWatchedKeys(cwMovies, cwShows);

      // Shows dropped on Trakt ("hide from progress") must not resurface here —
      // Trakt omits them from Up Next, so both the playback and up-next paths
      // filter them out.
      const isHiddenShow = (item: MediaItem) =>
        item.mediaType === "tv" && typeof item.traktId === "number" && hiddenShowIds.has(item.traktId);
      const isDismissed = (item: MediaItem) => {
        const showKey = item.mediaType === "movie" ? `movie:${item.id}` : `tv:${item.id}`;
        const exactKey = item.mediaType === "tv" && item.seasonNumber != null && item.episodeNumber != null
          ? `${showKey}:${item.seasonNumber}:${item.episodeNumber}`
          : showKey;
        const dismissedAt = Math.max(cloudDismissals.get(showKey) ?? 0, cloudDismissals.get(exactKey) ?? 0);
        return dismissedAt > 0 && (item.activityAt ?? 0) <= dismissedAt;
      };
      const rejectedEpisodes = new Set<string>();
      let episodeLookupFailures = 0;
      const validateEpisodes = (items: MediaItem[]) => validateContinueWatchingEpisodes(
        items, rejectedEpisodes, () => { episodeLookupFailures += 1; }
      );
      const cloudCw = await validateEpisodes(historyRows.map(historyToItem));
      const traktPlaybackCw = await validateEpisodes(playbackRows
        .map(traktPlaybackToMedia)
        .filter(isPausedPlaybackItem)
        .filter((item) => !isHiddenShow(item) && !isDismissed(item)));
      if (!isCurrent()) return;

      // ── Fast paint ─────────────────────────────────────────────────────────
      // The cloud watchlist + cloud/playback CW are already available now (the
      // cloud pull is ~400ms). Paint them immediately so both rails appear in
      // ~1s, WITHOUT waiting on loadTraktUpNext (~120 per-show progress calls)
      // or the per-item TMDB hydration below. The richer Trakt up-next data
      // enriches CW a moment later.
      const fastWatchlistSource = traktRows.length
        ? dedupeMedia(traktRows.map(traktItemToMedia))
        : cloudWatchlistRows;
      if (fastWatchlistSource.length) {
        void hydrateTraktItems(applyPendingWatchlist(authClient, profileId, fastWatchlistSource)).then((hydrated) => {
          const visibleWatchlist = applyPendingWatchlist(authClient, profileId, hydrated);
          if (visibleWatchlist.length && isCurrent()) {
            setWatchlist((current) => current.length ? current : visibleWatchlist);
            saveCachedList(watchlistCacheKey, visibleWatchlist, 60);
          }
        }).catch(() => undefined);
      }
      // Dedupe the fast paint at SHOW level, not per episode: the same series
      // can sit at a different episode in Trakt playback than in the local cloud
      // resume (you watched on another device), and dedupeMedia keys on the
      // episode subtitle — which rendered that series twice until the enriched
      // pass tidied up. Trakt playback is the newer truth, so it wins.
      const fastSeen = new Set<string>();
      const fastCw = pruneCompletedResume([...traktPlaybackCw, ...cloudCw.filter((item) => isPausedPlaybackItem(item) && !isHiddenShow(item) && !isDismissed(item))], cwCompletions)
        .filter((item) => {
          const key = `${item.mediaType}:${item.id}`;
          if (fastSeen.has(key)) return false;
          fastSeen.add(key);
          return true;
        })
        .sort((a, b) => (b.activityAt ?? 0) - (a.activityAt ?? 0));
      if (fastCw.length) {
        void hydrateContinueWatchingItems(fastCw).then((hydrated) => {
          // Only fill an empty rail: replacing a seeded cache list with this
          // playback-only list would visibly shrink the rail for a few seconds
          // until the enriched list lands.
          if (!isCurrent() || cwSourceRef.current !== "none") return;
          cwSourceRef.current = "fast";
          setContinueWatching(hydrated);
          setCategories((current) => current.some((c) => c.id === "continue_watching")
            ? current
            : [{ id: "continue_watching", title: "Continue Watching", items: hydrated }, ...current]);
          // First visit on a device (no cache yet): persist this list so the
          // NEXT open paints instantly even if this session ends before the
          // enriched pipeline (which normally owns the cache) completes. Never
          // overwrite an existing richer cache with this playback-only list.
          if (!readCachedList(cwCacheKey).length) {
            saveCachedList(cwCacheKey, hydrated, 20);
          }
        }).catch(() => undefined);
      }
      // ───────────────────────────────────────────────────────────────────────

      const cwTraktShows = await cwTraktShowsRead;
      const upNext = cwUsesTrakt
        ? await loadTraktUpNext(cwTraktShows, effectiveSettings.includeSpecials, hiddenShowIds, isCurrent).catch(() => ({ items: [] as MediaItem[], fetchFailures: 1 }))
        : { items: [] as MediaItem[], fetchFailures: 0 };
      const upNextRows = await validateEpisodes(upNext.items);
      if (!isCurrent()) return;
      const watchedKeys = new Set([...traktWatchedKeys(watchedMoviesRows, watchedShowsRows), ...cloudWatchedKeys]);
      // Cloud watched flags may be older than a provider's reset/progress response.
      // Keep those flags for badges, but do not let them veto tracker Continue Watching.
      const traktCw = mergeTraktWithLocalResume(
        mergeTrackerContinueWatching(traktPlaybackCw, upNextRows, cwWatchedKeys, cwCompletions), cloudCw
      );
      if (!readFailures.has("watched-movies") && !readFailures.has("watched-shows") && isCurrent()) setWatchedKeys(watchedKeys);
      const cwBase = includeIptvContinueWatching(
        traktReady ? traktCw : cloudCw.filter(isPausedPlaybackItem),
        cloudCw.filter((item) => !isHiddenShow(item) && !isDismissed(item))
      );
      // Order newest-activity-first across playback + up-next (matches the app's
      // updatedAt-descending sort) so the row leads with what you last watched.
      const cwSorted = dedupeMedia(cwBase).filter((item) => !isDismissed(item)).sort((a, b) => (b.activityAt ?? 0) - (a.activityAt ?? 0));
      const cw = await hydrateContinueWatchingItems(filterWatchedContinueWatching(cwSorted, traktReady ? cwWatchedKeys : watchedKeys, addonState, traktReady ? cwCompletions : undefined));
      // Trakt outage guard: when Trakt is connected but every read came back
      // empty, the calls were blocked (Cloudflare challenges the CORS
      // preflight intermittently, especially on VPN/datacenter IPs) — keep
      // showing the cached rail instead of wiping it with an empty list.
      const traktOutage = readFailures.has("playback") || readFailures.has("cw-watched") || readFailures.has("cw-movies") || readFailures.has("cw-shows") || readFailures.has("cw-hidden");
      if (!traktOutage && upNext.fetchFailures === 0 && episodeLookupFailures === 0 && isCurrent() && activitySignature) {
        traktActivityRef.current = { key: `${accountId ?? "local"}:${profileId}`, signature: activitySignature };
      }
      // Enriched CW (adds Trakt up-next episodes) replaces the fast paint. When
      // the fresh list is non-empty we swap it in. An empty result with Trakt
      // connected and reads healthy means the rail is GENUINELY empty — clear it
      // and its cache so a finished library can't resurrect a stale rail. During
      // an outage (all reads empty) we keep whatever is painted.
      if (!traktOutage && isCurrent()) {
        const reconcile = (current: MediaItem[]) => upNext.fetchFailures > 0 || episodeLookupFailures > 0
          ? mergePartialContinueWatching(cw, current.filter((item) => !isHiddenShow(item) && !isDismissed(item) && !rejectedEpisodes.has(episodeAvailabilityKey(item))), cwCompletions)
          : cw;
        if (cw.length) {
          cwSourceRef.current = "fresh";
          setContinueWatching(reconcile);
          saveCachedList(cwCacheKey, reconcile(readCachedList(cwCacheKey)), 20);
          setCategories((current) => {
            const others = current.filter((c) => c.id !== "continue_watching");
            const previous = current.find((c) => c.id === "continue_watching")?.items ?? [];
            return [{ id: "continue_watching", title: "Continue Watching", items: reconcile(previous) }, ...others];
          });
        } else if (traktReady && upNext.fetchFailures === 0 && episodeLookupFailures === 0) {
          // Only clear on a CLEAN pass: any per-show progress failure means this
          // empty result could be a partial outage, and wiping the cache would
          // recreate the blank-rail-on-startup bug the seed exists to prevent.
          cwSourceRef.current = "fresh";
          setContinueWatching([]);
          setCategories((current) => current.filter((c) => c.id !== "continue_watching"));
          removeStored(cwCacheKey);
        } else if (rejectedEpisodes.size > 0) {
          setContinueWatching(reconcile);
          saveCachedList(cwCacheKey, reconcile(readCachedList(cwCacheKey)), 20);
          setCategories((current) => current.flatMap((category) => {
            if (category.id !== "continue_watching") return [category];
            const items = reconcile(category.items);
            return items.length ? [{ ...category, items }] : [];
          }));
        }
      }
      // Refresh the watchlist with the authoritative Trakt list if it differs.
      const watchlistSource = watchlistReady && !readFailures.has("watchlist")
        ? dedupeMedia(traktRows.map(traktItemToMedia))
        : cloudWatchlistRows;
      const hydratedWatchlist = applyPendingWatchlist(authClient, profileId,
        await hydrateTraktItems(applyPendingWatchlist(authClient, profileId, watchlistSource)));
      if (!readFailures.has("watchlist") && isCurrent()) {
        setWatchlist(hydratedWatchlist);
        saveCachedList(watchlistCacheKey, hydratedWatchlist, 60);
      } else if (isCurrent()) {
        setWatchlist(current => applyPendingWatchlist(authClient, profileId, current));
        saveCachedList(watchlistCacheKey, applyPendingWatchlist(authClient, profileId, readCachedList(watchlistCacheKey)), 60);
      }
      } catch (error) {
        if (isCurrent()) {
          setAddonsReady(true);
          if (!background) setToast(error instanceof Error ? error.message : "Failed to load ARVIO");
        }
      } finally {
        if (isCurrent() && !background) setBusy("");
      }
    })();
    refreshInFlightRef.current = { key, promise: run };
    return run.finally(() => {
      if (refreshInFlightRef.current?.promise === run) refreshInFlightRef.current = null;
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [activeProfileId]);
  return { refreshData };
}

export function useRefreshTriggers(store: StoreCore & Pick<ReturnType<typeof useRefreshData>, "refreshData">) {
  const { activeProfileId, auth, cloudProfilesHydrated, lastRefreshStartedRef, playingRef, previousSectionRef, refreshData, refreshInFlightRef, section, traktActivityRef, traktConnected, view } = store;
  useEffect(() => {
    // Also runs on the profile-selection screen ("profiles" view): the last-used
    // profile is almost always the one picked, so Continue Watching and the
    // rails are already loading (or loaded) by the time Home first mounts.
    if (view === "login") return;
    if (authClient.session && !cloudProfilesHydrated) return;
    void refreshData();
  }, [cloudProfilesHydrated, refreshData, view]);

  useEffect(() => {
    if (view !== "app" || !traktConnected || (authClient.session && !cloudProfilesHydrated)) return;
    const monitor = createTraktActivityCheck({
      key: `${auth?.userId ?? "local"}:${activeProfileId}`,
      snapshot: traktActivityRef,
      isActive: () => document.visibilityState === "visible" && !playingRef.current && !refreshInFlightRef.current && readsFrom("continueWatching", "trakt"),
      read: async () => continueWatchingActivitySignature(await traktClient.continueWatchingActivity()),
      refresh: () => refreshData(undefined, true)
    });
    // No media polling or full sync loop: one tiny direct Trakt check every two
    // visible, idle minutes. Unchanged activity costs zero Netlify calls.
    const timer = window.setInterval(() => { void monitor.check(); }, 120_000);
    return () => { monitor.dispose(); window.clearInterval(timer); };
  }, [activeProfileId, auth?.userId, cloudProfilesHydrated, refreshData, traktConnected, view]);

  useEffect(() => {
    if (view === "login" || (authClient.session && !cloudProfilesHydrated)) return undefined;
    const refreshOnReturn = () => {
      if (!shouldRefreshAutomatically({
        visible: document.visibilityState === "visible",
        playing: playingRef.current,
        inFlight: Boolean(refreshInFlightRef.current),
        lastRefreshAt: lastRefreshStartedRef.current,
        now: performance.now()
      })) return;
      void refreshData(undefined, true);
    };
    const returnedHome = previousSectionRef.current !== "home" && section === "home";
    previousSectionRef.current = section;
    if (view === "app" && returnedHome) refreshOnReturn();
    window.addEventListener("focus", refreshOnReturn);
    document.addEventListener("visibilitychange", refreshOnReturn);
    return () => {
      window.removeEventListener("focus", refreshOnReturn);
      document.removeEventListener("visibilitychange", refreshOnReturn);
    };
  }, [cloudProfilesHydrated, refreshData, section, view]);
  return {  };
}

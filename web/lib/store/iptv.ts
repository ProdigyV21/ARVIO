// App store (lib/store.tsx): Live TV: the playlist snapshot, channel identities, the guide and the Home "Favorite TV" row.
import { useCallback, useEffect, useMemo, useRef } from "react";
import { accessibleChannels, iptvPlaylistSignature, loadIptvChannelIdentities, loadIptvGuideForChannels, loadIptvSnapshot } from "../iptv";
import { cachedFavoriteChannels, favoriteCopyIsStale, resolveFavoriteChannels, saveFavoriteChannels } from "../favoriteTv";
import { isCurrentIptvSnapshot } from "../iptvSession";
import { createIptvRefresher } from "../iptvRefresh";
import { hasStalkerPortals, liveTvStalkerPortals, stalkerSourceSignature } from "../stalkerPortals";
import type { AppSettings, IptvChannel, IptvSnapshot } from "../types";
import { authClient } from "./clients";
import type { StoreCore } from "./core";

export const emptyIptv: IptvSnapshot = {
  channels: [],
  grouped: {},
  nowNext: {},
  favoriteGroups: [],
  favoriteChannels: [],
  hiddenGroups: [],
  groupOrder: [],
  playlistWarnings: [],
  epgWarning: undefined,
  loadedAt: 0
};

export function useIptvState(store: StoreCore) {
  const { activeProfileId, activeProfileIdRef, auth, guideRetryAfter, iptvGuideInFlightRef, iptvSnapshot, section, setBusy, setIptvSnapshot, setToast, settings, settingsRef, view } = store;
  // `quiet` is the Home background load for the Favorite TV row: no status text, no
  // error toast — Live TV reports playlist problems when the user opens it.
  const iptvRefresher = useRef<ReturnType<typeof createIptvRefresher<AppSettings, IptvSnapshot>> | null>(null);
  // `fresh` (a Refresh / Retry button): portals are asked again instead of answering from their recent list.
  const refreshIptv = useCallback(({ quiet = false, fresh = false }: { quiet?: boolean; fresh?: boolean } = {}) => {
    iptvRefresher.current ??= createIptvRefresher<AppSettings, IptvSnapshot>({
      read: () => {
        const current = settingsRef.current;
        return {
          scope: `${authClient.session?.userId ?? "local"}:${activeProfileIdRef.current ?? "local"}`,
          signature: iptvPlaylistSignature(current.iptvPlaylists) + stalkerSourceSignature(current),
          settings: current
        };
      },
      load: (currentSettings, { fresh: freshPortals }) => loadIptvSnapshot(
        currentSettings.iptvPlaylists,
        currentSettings.favoriteChannelIds,
        currentSettings.favoriteGroupIds,
        currentSettings.hiddenGroupIds,
        currentSettings.groupOrder,
        { userAgent: currentSettings.customUserAgent, stalkerPortals: liveTvStalkerPortals(currentSettings), freshPortals }
      ),
      // Stamp which playlists this snapshot came from so Live TV can reuse it
      // on re-entry instead of rebuilding ~139k channels every visit.
      apply: (loadedIptv, { signature, scope }) => setIptvSnapshot({ ...loadedIptv, signature, scopeKey: scope }),
      report: (error) => setToast(error instanceof Error ? error.message : "Failed to load Live TV"),
      setLoading: (loading) => setBusy(current => loading ? "Loading TV" : current === "Loading TV" ? "" : current)
    });
    return iptvRefresher.current({ quiet, fresh });
  }, []);

  useEffect(() => {
    const channels = iptvSnapshot.allChannels ?? iptvSnapshot.channels;
    if (!channels.length || iptvSnapshot.identitiesLoaded || iptvSnapshot.signature !== (iptvPlaylistSignature(settings.iptvPlaylists) + stalkerSourceSignature(settings))) return;
    let cancelled = false;
    const profileId = activeProfileId;
    void loadIptvChannelIdentities(settings.iptvPlaylists, channels, { userAgent: settings.customUserAgent }).then(enriched => {
      if (cancelled || activeProfileIdRef.current !== profileId) return;
      const byId = new Map(enriched.map(channel => [channel.id, channel]));
      setIptvSnapshot(current => current.scopeKey !== iptvSnapshot.scopeKey || current.signature !== iptvSnapshot.signature
        || (current.allChannels ?? current.channels) !== channels ? current : ({ ...current, identitiesLoaded: true, allChannels: enriched,
        channels: current.channels.map(channel => byId.get(channel.id) ?? channel) }));
    });
    return () => { cancelled = true; };
  }, [iptvSnapshot.allChannels, iptvSnapshot.channels, iptvSnapshot.signature, iptvSnapshot.identitiesLoaded, settings.iptvPlaylists, settings.iptvStalkerUrl, settings.iptvStalkerMac, settings.iptvStalkerPortals, settings.customUserAgent, activeProfileId]);

  // Home "Favorite TV" row (see favoriteTv.ts): from the loaded playlist when Live TV
  // has one, otherwise from the copy the last loaded playlist left behind.
  const iptvScopeKey = `${auth?.userId ?? "local"}:${activeProfileId ?? "local"}`;
  const iptvSourceSignature = iptvPlaylistSignature(settings.iptvPlaylists) + stalkerSourceSignature(settings);
  const currentIptvChannels = isCurrentIptvSnapshot(iptvSnapshot, iptvScopeKey, iptvSourceSignature) ? iptvSnapshot.allChannels ?? iptvSnapshot.channels : null;
  const favoriteTvChannels = useMemo(() => {
    const favorites = settings.favoriteChannelIds ?? [];
    if (!settings.iptvFavoritesOnHome || !favorites.length) return [];
    const source = currentIptvChannels?.length ? currentIptvChannels : cachedFavoriteChannels(iptvScopeKey, iptvSourceSignature)?.channels ?? [];
    return resolveFavoriteChannels(favorites, accessibleChannels(source, [...(settings.hiddenGroupIds ?? []), ...(settings.lockedIptvGroupIds ?? [])]));
  }, [currentIptvChannels, iptvScopeKey, iptvSourceSignature, settings.favoriteChannelIds, settings.iptvFavoritesOnHome, settings.hiddenGroupIds, settings.lockedIptvGroupIds]);
  useEffect(() => {
    // An empty (cold or failed) playlist says nothing about the favorites: keep the copy.
    if (!currentIptvChannels?.length) return;
    const favorites = settings.favoriteChannelIds ?? [];
    saveFavoriteChannels(iptvScopeKey, iptvSourceSignature, favorites, resolveFavoriteChannels(favorites, currentIptvChannels));
  }, [currentIptvChannels, iptvScopeKey, iptvSourceSignature, settings.favoriteChannelIds]);
  // Without a usable copy, load the playlist once in the background so the row can appear.
  const favoriteTvLoadKey = useRef("");
  useEffect(() => {
    const favorites = settings.favoriteChannelIds ?? [];
    if (view !== "app" || section !== "home" || !settings.iptvFavoritesOnHome || !favorites.length || currentIptvChannels?.length) return;
    if (!settings.iptvPlaylists.some((playlist) => playlist.enabled && playlist.m3uUrl.trim()) && !hasStalkerPortals(settings)) return;
    const key = `${iptvScopeKey}:${iptvSourceSignature}`;
    if (favoriteTvLoadKey.current === key || !favoriteCopyIsStale(cachedFavoriteChannels(iptvScopeKey, iptvSourceSignature), favorites)) return;
    // Home's own rows go first.
    const timer = setTimeout(() => {
      favoriteTvLoadKey.current = key;
      void refreshIptv({ quiet: true });
    }, 2500);
    return () => clearTimeout(timer);
  }, [view, section, currentIptvChannels, iptvScopeKey, iptvSourceSignature, refreshIptv, settings.favoriteChannelIds, settings.iptvFavoritesOnHome, settings.iptvPlaylists, settings.iptvStalkerUrl, settings.iptvStalkerPortals]);

  const loadIptvGuide = useCallback(async (channels: IptvChannel[]) => {
    if (!channels.length) return;
    const currentSettings = settingsRef.current;
    const profileId = activeProfileIdRef.current;
    const account = authClient.session?.userId;
    const signature = iptvPlaylistSignature(currentSettings.iptvPlaylists) + stalkerSourceSignature(currentSettings);
    const scopedKey = (id: string) => `${account}:${profileId}:${signature}:${id}`;
    // A guide entry whose "now" programme already ended is stale — refetch it so
    // the rows keep showing what is actually on air.
    const isFresh = (channelId: string) => {
      const entry = iptvSnapshot.nowNext[channelId];
      if (!entry) return false;
      if (entry.now) return entry.now.endUtcMillis > Date.now();
      return true;
    };
    const missing = channels.filter((channel) => !isFresh(channel.id) && !iptvGuideInFlightRef.current.has(scopedKey(channel.id)) && (guideRetryAfter.current.get(scopedKey(channel.id)) ?? 0) < Date.now());
    if (!missing.length) return;
    missing.forEach((channel) => { iptvGuideInFlightRef.current.add(scopedKey(channel.id)); guideRetryAfter.current.set(scopedKey(channel.id), Date.now() + 60_000); });
    if (guideRetryAfter.current.size > 2000) for (const [key, expiry] of guideRetryAfter.current) if (expiry < Date.now()) guideRetryAfter.current.delete(key);
    try {
      const guide = await loadIptvGuideForChannels(currentSettings.iptvPlaylists, missing);
      if (activeProfileIdRef.current !== profileId || authClient.session?.userId !== account || iptvPlaylistSignature(settingsRef.current.iptvPlaylists) + stalkerSourceSignature(settingsRef.current) !== signature) return;
      if (!Object.keys(guide).length) return;
      setIptvSnapshot((current) => ({
        ...current,
        nowNext: {
          ...current.nowNext,
          ...guide
        }
      }));
    } catch {
      // Guide is helpful but should never block channel browsing/playback.
    } finally {
      missing.forEach((channel) => iptvGuideInFlightRef.current.delete(scopedKey(channel.id)));
    }
  }, [iptvSnapshot.nowNext]);
  return { iptvRefresher, refreshIptv, iptvScopeKey, iptvSourceSignature, currentIptvChannels, favoriteTvChannels, favoriteTvLoadKey, loadIptvGuide };
}

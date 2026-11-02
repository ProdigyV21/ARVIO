// App store (lib/store.tsx): All store state (useState/useRef) and the small effects that keep refs, catalogs and
// Telegram in sync. Every other store hook receives this object.
import { applyPendingWatchlist } from "../watchlistOutbox";
import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { useManagedConfiguration } from "./managed";
import { config } from "../config";
import { mergeCatalogs } from "../catalogs";
import { saveCloudTrackingSelection } from "../cloud";
import { reportHomeServerPlayback } from "../homeServerPlayback";
import { loadHomeServerRows } from "../homeserver";
import { loadPlaylists } from "../iptv";
import { migrateLegacyIptvKeys } from "../stalkerPortals";
import { loadStored, saveStored } from "../storage";
import type { TraktDeviceCode } from "../trakt";
import type { TraktActivitySnapshot } from "../traktActivity";
import { mdblistClient } from "../mdblist";
import { simklClient, type SimklPinCode } from "../simkl";
import { loadTrackingPreferences, type TrackingPreferences } from "../sync";
import type { AppSettings, Category, InstalledAddon, IptvChannel, CatalogConfig, IptvSnapshot, MediaItem, NavSection, Profile, StreamSource } from "../types";
import { ACTIVE_PROFILE_KEY, AVATAR_IMAGES_KEY, PROFILES_KEY, localProfilesMatchAccount, makeProfile } from "./profiles";
import { cwCacheKeyFor, readCachedList, watchlistCacheKeyFor } from "./listCache";
import { authClient, traktClient } from "./clients";
import { emptyIptv } from "./iptv";
import { defaultSettings, settingsKey } from "./settings";
import type { AppView } from "./types";

export function useStoreCore(initialView?: AppView) {
  const [section, setSection] = useState<NavSection>("home");
  // Seed Continue Watching + Watchlist synchronously from the last-known lists
  // for the active profile so both are on screen the instant the app renders —
  // without this CW stays blank until the ~120-call Trakt up-next fetch returns,
  // and only appeared after a navigation round-trip remounted the screen.
  const initialProfileId = loadStored<string | null>(ACTIVE_PROFILE_KEY, null);
  const initialCw = readCachedList(cwCacheKeyFor(initialProfileId));
  const initialWatchlist = applyPendingWatchlist(authClient, initialProfileId, readCachedList(watchlistCacheKeyFor(initialProfileId)));
  const [categories, setCategories] = useState<Category[]>(
    initialCw.length ? [{ id: "continue_watching", title: "Continue Watching", items: initialCw }] : []
  );
  const [catalogConfigs, setCatalogConfigs] = useState<CatalogConfig[]>([]);
  const [homeServerRows, setHomeServerRows] = useState<Category[]>([]);
  const [continueWatching, setContinueWatching] = useState<MediaItem[]>(initialCw);
  const [watchlist, setWatchlist] = useState<MediaItem[]>(initialWatchlist);
  const watchlistMutationRef = useRef(new Map<string, number>());
  // Where the on-screen CW list came from, so later paints know whether they may
  // replace it: cache seed < fast paint (playback-only) < fresh enriched list.
  const cwSourceRef = useRef<"none" | "seed" | "fast" | "fresh">(initialCw.length ? "seed" : "none");
  // The profile key of the LATEST refresh request. In-flight refreshes for a
  // previous profile check this before writing state, so switching profiles can
  // never end with the old profile's rows landing after the new profile's.
  const refreshKeyRef = useRef<string | null>(null);
  const refreshGenerationRef = useRef(0);
  const traktActivityRef = useRef<TraktActivitySnapshot>(null);
  const [watchedKeys, setWatchedKeys] = useState<Set<string>>(() => new Set());
  const [selected, setSelected] = useState<MediaItem | null>(null);
  const [streams, setStreams] = useState<StreamSource[]>([]);
  const streamsRef = useRef(streams);
  streamsRef.current = streams;
  const [selectedEpisode, setSelectedEpisode] = useState<{ season: number; episode: number } | null>(null);
  const [activeStream, setActiveStream] = useState<StreamSource | null>(null);
  const [activeChannel, setActiveChannel] = useState<IptvChannel | null>(null);
  const playingRef = useRef(false);
  playingRef.current = Boolean(activeStream || activeChannel);
  const [personalAddons, setAddons] = useState<InstalledAddon[]>([]);
  const [addonsReady, setAddonsReady] = useState(false);
  const [iptvSnapshot, setIptvSnapshot] = useState<IptvSnapshot>(emptyIptv);
  const [query, setQuery] = useState("");
  const [results, setResults] = useState<MediaItem[]>([]);
  const [searchState, setSearchState] = useState<"idle" | "loading" | "error">("idle");
  const [settingsSyncState, setSettingsSyncState] = useState<"local" | "saved" | "pending" | "error">("local");
  const [personalSettings, setSettings] = useState<AppSettings>(() => {
    const stored = loadStored<AppSettings>(settingsKey, defaultSettings);
    return {
      ...defaultSettings,
      ...migrateLegacyIptvKeys(stored),
      iptvPlaylists: loadPlaylists(),
      catalogs: mergeCatalogs(stored.catalogs, stored.hiddenCatalogIds, stored.hiddenAddonCatalogIds)
    };
  });
  const [auth, setAuth] = useState(() => authClient.session);
  const [traktConnected, setTraktConnected] = useState(() => traktClient.isConnected);
  const [mdblistConnected, setMdblistConnected] = useState(() => mdblistClient.isConnected);
  const [simklConnected, setSimklConnected] = useState(() => simklClient.isConnected);
  const [deviceCode, setDeviceCode] = useState<TraktDeviceCode | null>(null);
  const [simklDeviceCode, setSimklDeviceCode] = useState<SimklPinCode | null>(null);
  const [busy, setBusy] = useState("Loading ARVIO");
  const busyRef = useRef(busy);
  busyRef.current = busy;
  const [toast, setToast] = useState<string | null>(null);
  const [cloudProfilesHydrated, setCloudProfilesHydrated] = useState(() => !authClient.session);
  const refreshInFlightRef = useRef<{ key: string; promise: Promise<void> } | null>(null);
  const lastRefreshStartedRef = useRef<number | null>(null);
  const previousSectionRef = useRef(section);
  const iptvGuideInFlightRef = useRef(new Set<string>());
  // Snapshot of the settings last known to match the cloud, so the autosave
  // effect can skip pushing settings that just CAME from the cloud (an echo
  // write every app boot = a wasted account-sync-push per user per session).
  const lastSyncedSettingsRef = useRef<string | null>(null);
  const sourceGeneration = useRef(0);
  const playbackGeneration = useRef(0);
  const playbackPreparation = useRef<AbortController | null>(null);
  const ownedPlayback = useRef<{ stream: StreamSource; settings: AppSettings } | null>(null);
  const stopOwnedPlayback = useCallback(() => {
    const owned = ownedPlayback.current;
    ownedPlayback.current = null;
    if (owned) void reportHomeServerPlayback(owned.stream, owned.settings, "stop").catch(() => undefined);
  }, []);
  const guideRetryAfter = useRef(new Map<string, number>());

  const [profiles, setProfiles] = useState<Profile[]>(() => {
    // Don't surface a previous account's cached profiles when a different account
    // is signed in on this browser — start clean and let the cloud pull fill in.
    const stored = localProfilesMatchAccount() ? loadStored<Profile[]>(PROFILES_KEY, []) : [];
    return stored.length ? stored : [makeProfile("Profile 1", 0xffe50914, 0)];
  });
  const [activeProfileId, setActiveProfileId] = useState<string | null>(() =>
    localProfilesMatchAccount() ? loadStored<string | null>(ACTIVE_PROFILE_KEY, null) : null
  );
  const activeProfileIdRef = useRef(activeProfileId);
  activeProfileIdRef.current = activeProfileId;
  useEffect(() => () => { playbackPreparation.current?.abort(); stopOwnedPlayback(); }, [activeProfileId, auth?.userId, stopOwnedPlayback]);
  const [trackingPreferences, setTrackingPreferences] = useState<TrackingPreferences>(() =>
    loadTrackingPreferences(activeProfileId)
  );
  // Hydrate cached custom-avatar images synchronously so profile tiles paint the
  // real avatar on first render instead of flashing the letter fallback.
  const [avatarImages, setAvatarImagesState] = useState<Record<string, string>>(() => loadStored<Record<string, string>>(AVATAR_IMAGES_KEY, {}));
  const setAvatarImages = useCallback((next: Record<string, string>) => {
    setAvatarImagesState(next);
    saveStored(AVATAR_IMAGES_KEY, next);
  }, []);
  const [manageMode, setManageMode] = useState(false);
  const [view, setView] = useState<AppView>(() => {
    if (initialView) return initialView;
    const stored = loadStored<Profile[]>(PROFILES_KEY, []);
    const activeId = loadStored<string | null>(ACTIVE_PROFILE_KEY, null);
    const skip = loadStored<AppSettings>(settingsKey, defaultSettings).skipProfileSelection;
    if (skip && activeId && stored.some((p) => p.id === activeId && !p.isLocked)) return "app";
    return "profiles";
  });

  const activeProfile = profiles.find((p) => p.id === activeProfileId) ?? null;
  // Read inside callbacks (openDetails, playChannel) so a gate always sees the profile in use.
  const activeProfileRef = useRef(activeProfile);
  activeProfileRef.current = activeProfile;

  const managed = useManagedConfiguration(auth?.userId, activeProfileId, playingRef);
  const settings = useMemo(() => managed.applySettings(personalSettings), [personalSettings, managed.resources?.catalogs, managed.resources?.homeServers, managed.resources?.playlists, managed.overrides]);
  const addons = useMemo(() => {
    const assigned = managed.resources?.addons || [];
    const ids = new Set(assigned.map(addon => addon.id));
    return assigned.length ? [...assigned, ...personalAddons.filter(addon => !ids.has(addon.id))] : personalAddons;
  }, [personalAddons, managed.resources?.addons]);
  const personalSettingsRef = useRef(personalSettings);
  personalSettingsRef.current = personalSettings;
  const personalAddonsRef = useRef(personalAddons);
  personalAddonsRef.current = personalAddons;

  // The Home billboard's preview lives in HomeScreen (lib/homeHero): as store
  // state, every 8 s rotation and card hover re-rendered almost the whole app.

  // Refs so stable callbacks always read the latest values without re-creating.
  const addonsRef = useRef(addons);
  useEffect(() => {
    addonsRef.current = addons;
  }, [addons]);

  const settingsRef = useRef(settings);
  useEffect(() => {
    settingsRef.current = settings;
  }, [settings]);

  useEffect(() => {
    const effectiveCatalogs = mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds, settings.hiddenAddonCatalogIds);
    setCatalogConfigs(effectiveCatalogs.filter((catalog) => catalog.enabled && catalog.sourceType !== "home-server"));
  }, [settings.catalogs, settings.hiddenCatalogIds, settings.hiddenAddonCatalogIds]);

  useEffect(() => {
    let cancelled = false;
    const effectiveCatalogs = mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds, settings.hiddenAddonCatalogIds);
    void loadHomeServerRows(
      settings.homeServers,
      settings.hiddenHomeServerCatalogIds,
      effectiveCatalogs
    ).then((rows) => {
      if (!cancelled) setHomeServerRows(rows);
    }).catch(() => {
      if (!cancelled) setHomeServerRows([]);
    });
    return () => {
      cancelled = true;
    };
  }, [settings.catalogs, settings.hiddenCatalogIds, settings.hiddenAddonCatalogIds, settings.hiddenHomeServerCatalogIds, settings.homeServers]);

  const deviceCodeRef = useRef(deviceCode);
  useEffect(() => {
    deviceCodeRef.current = deviceCode;
  }, [deviceCode]);

  const simklDeviceCodeRef = useRef(simklDeviceCode);
  useEffect(() => {
    simklDeviceCodeRef.current = simklDeviceCode;
  }, [simklDeviceCode]);

  // Restore a saved Telegram (browser GramJS) session and prep the streaming
  // service worker so a connected user's sources resolve and play after a
  // reload — the browser equivalent of Android re-opening its TDLib database.
  useEffect(() => {
    if (!config.telegramEnabled) return;
    void (async () => {
      try {
        const tg = await import("@/lib/telegram");
        await tg.restoreSession();
        // Only pre-warm the streaming service worker for users who are actually
        // connected — no need to register a worker for accounts that never link
        // Telegram (a first play still registers it lazily via registerStream).
        if (tg.isConnected()) void tg.initTelegramStreaming();
      } catch {
        /* Telegram is optional; a failure just leaves it disconnected. */
      }
    })();
  }, []);

  useEffect(() => {
    mdblistClient.onTokenRefreshed = async (profileId, token) => {
      if (activeProfileIdRef.current !== profileId || mdblistClient.token !== token) return;
      await saveCloudTrackingSelection(authClient, profileId, {
        provider: traktClient.isConnected ? "TRAKT" : simklClient.isConnected ? "SIMKL" : "MDBLIST",
        traktToken: traktClient.token,
        simklToken: simklClient.token,
        mdbListApiKey: null,
        mdbListAccessToken: token.accessToken,
        mdbListRefreshToken: token.refreshToken ?? null,
        mdbListTokenExpiresAt: token.expiresAt ?? null,
        trackingPreferences: loadTrackingPreferences(profileId),
        changedDomains: ["mdblist"]
      });
    };
    return () => { mdblistClient.onTokenRefreshed = undefined; };
  }, []);
  return { managed, personalSettings, personalSettingsRef, personalAddons, personalAddonsRef, section, setSection, initialProfileId, initialCw, initialWatchlist, categories, setCategories, catalogConfigs, setCatalogConfigs, homeServerRows, setHomeServerRows, continueWatching, setContinueWatching, watchlist, setWatchlist, watchlistMutationRef, cwSourceRef, refreshKeyRef, refreshGenerationRef, traktActivityRef, watchedKeys, setWatchedKeys, selected, setSelected, streams, setStreams, streamsRef, selectedEpisode, setSelectedEpisode, activeStream, setActiveStream, activeChannel, setActiveChannel, playingRef, addons, setAddons, addonsReady, setAddonsReady, iptvSnapshot, setIptvSnapshot, query, setQuery, results, setResults, searchState, setSearchState, settingsSyncState, setSettingsSyncState, settings, setSettings, auth, setAuth, traktConnected, setTraktConnected, mdblistConnected, setMdblistConnected, simklConnected, setSimklConnected, deviceCode, setDeviceCode, simklDeviceCode, setSimklDeviceCode, busy, setBusy, busyRef, toast, setToast, cloudProfilesHydrated, setCloudProfilesHydrated, refreshInFlightRef, lastRefreshStartedRef, previousSectionRef, iptvGuideInFlightRef, lastSyncedSettingsRef, sourceGeneration, playbackGeneration, playbackPreparation, ownedPlayback, stopOwnedPlayback, guideRetryAfter, profiles, setProfiles, activeProfileId, setActiveProfileId, activeProfileIdRef, trackingPreferences, setTrackingPreferences, avatarImages, setAvatarImagesState, setAvatarImages, manageMode, setManageMode, view, setView, activeProfile, activeProfileRef, addonsRef, settingsRef, deviceCodeRef, simklDeviceCodeRef };
}

export type StoreCore = ReturnType<typeof useStoreCore>;

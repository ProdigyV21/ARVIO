"use client";

import { createContext, useCallback, useContext, useEffect, useMemo, useRef, useState } from "react";
import { getStreams, installAddon as installAddonManifest, loadLocalAddons, saveLocalAddons } from "./addons";
import { AuthClient } from "./auth";
import { defaultCatalogs, mergeCatalogs } from "./catalogs";
import { getContinueWatching, pullCloudPayload, pullCloudProfiles, saveCloudAddons, saveCloudProfiles, saveCloudSettings } from "./cloud";
import { loadHomeServerRows } from "./homeserver";
import { loadIptvSnapshot, loadPlaylists, savePlaylists } from "./iptv";
import { dedupeMedia, historyToItem, hydrateTraktItems, traktItemToMedia, traktPlaybackToMedia } from "./mappers";
import { loadStored, saveStored } from "./storage";
import { getDetails, loadCatalog, searchMedia } from "./tmdb";
import { TraktClient, type TraktDeviceCode } from "./trakt";
import type {
  AppSettings,
  AuthSession,
  Category,
  InstalledAddon,
  IptvChannel,
  IptvPlaylistEntry,
  CatalogConfig,
  IptvSnapshot,
  MediaItem,
  NavSection,
  Profile,
  StreamSource
} from "./types";

export const authClient = new AuthClient();
export const traktClient = new TraktClient();

const settingsKey = "arvio.web.settings";
const PROFILES_KEY = "arvio.web.profiles";
const ACTIVE_PROFILE_KEY = "arvio.web.activeProfileId";

export type AppView = "profiles" | "login" | "app";

function randomProfileColor() {
  const colors = [0xffe50914, 0xff1db954, 0xff3b82f6, 0xfff59e0b, 0xff8b5cf6, 0xffec4899, 0xff14b8a6, 0xff6366f1];
  return colors[Math.floor(Math.random() * colors.length)];
}

function makeProfile(name: string, avatarColor: number, avatarId = 0): Profile {
  const now = Date.now();
  return {
    id: (globalThis.crypto?.randomUUID?.() ?? `p_${now}_${Math.floor(Math.random() * 1e6)}`),
    name,
    avatarColor,
    avatarId,
    avatarImageVersion: 0,
    isKidsProfile: false,
    pin: null,
    isLocked: false,
    createdAt: now,
    lastUsedAt: now
  };
}

export const defaultSettings: AppSettings = {
  autoPlayNext: true,
  autoPlaySingleSource: false,
  autoPlayMinQuality: "any",
  trailerAutoPlay: true,
  trailerSound: false,
  trailerDelaySeconds: 2,
  language: "en-US",
  defaultSubtitle: "en",
  secondarySubtitle: "",
  audioLanguage: "",
  subtitleSize: 100,
  subtitleColor: "#ffffff",
  subtitleOffsetMs: 0,
  subtitleStyle: "outline",
  subtitleStylized: false,
  filterSubtitlesByLanguage: false,
  removeHearingImpaired: true,
  aiSubtitlesEnabled: false,
  aiSubtitleModel: "off",
  aiAutoSelect: false,
  aiApiKey: "",
  cardLayoutMode: "landscape",
  deviceModeOverride: "auto",
  oledBlack: false,
  clockFormat: "24h",
  showBudget: true,
  smoothScrolling: true,
  spoilerBlur: false,
  accentColor: "arctic",
  dnsProvider: "system",
  showLoadingStats: false,
  customUserAgent: "",
  skipProfileSelection: false,
  cardDensity: "comfortable",
  catalogs: defaultCatalogs,
  hiddenCatalogIds: [],
  disabledAddonIds: [],
  homeServers: [],
  iptvPlaylists: [],
  favoriteChannelIds: [],
  favoriteGroupIds: [],
  hiddenGroupIds: [],
  groupOrder: []
};

const emptyIptv: IptvSnapshot = {
  channels: [],
  grouped: {},
  nowNext: {},
  favoriteGroups: [],
  favoriteChannels: [],
  hiddenGroups: [],
  groupOrder: [],
  loadedAt: 0
};

export interface AppStore {
  view: AppView;
  profiles: Profile[];
  activeProfile: Profile | null;
  avatarImages: Record<string, string>;
  manageMode: boolean;
  setManageMode: (value: boolean) => void;
  selectProfile: (profile: Profile) => Promise<void>;
  createProfile: (name: string, avatarColor: number, avatarId: number) => Promise<void>;
  updateProfile: (profile: Profile) => Promise<void>;
  deleteProfile: (id: string) => Promise<void>;
  switchProfile: () => void;
  goToLogin: () => void;
  backToProfiles: () => void;

  section: NavSection;
  setSection: (section: NavSection) => void;
  categories: Category[];
  catalogConfigs: CatalogConfig[];
  loadCatalogRow: (catalog: CatalogConfig) => Promise<Category | null>;
  homeServerRows: Category[];
  continueWatching: MediaItem[];
  watchlist: MediaItem[];
  hero: MediaItem | null;
  setHeroPreview: (item: MediaItem | null) => void;
  selected: MediaItem | null;
  streams: StreamSource[];
  selectedEpisode: { season: number; episode: number } | null;
  loadEpisodeStreams: (item: MediaItem, season: number, episode: number) => Promise<void>;
  advanceEpisode: () => Promise<boolean>;
  activeStream: StreamSource | null;
  activeChannel: IptvChannel | null;
  addons: InstalledAddon[];
  iptvSnapshot: IptvSnapshot;
  query: string;
  setQuery: (value: string) => void;
  results: MediaItem[];
  settings: AppSettings;
  setSettings: (next: AppSettings) => void;
  updateSettings: (patch: Partial<AppSettings>) => void;
  auth: AuthSession | null;
  traktConnected: boolean;
  deviceCode: TraktDeviceCode | null;
  busy: string;
  toast: string | null;
  setToast: (value: string | null) => void;

  refreshData: () => Promise<void>;
  openDetails: (item: MediaItem) => Promise<void>;
  closeDetails: () => void;
  playStream: (stream: StreamSource) => void;
  playTrailer: (item: MediaItem) => Promise<void>;
  playChannel: (channel: IptvChannel) => void;
  closePlayer: () => void;
  installAddon: (url: string) => Promise<void>;
  removeAddon: (addon: InstalledAddon) => Promise<void>;
  setAddonsState: (next: InstalledAddon[]) => Promise<void>;
  signIn: (email: string, password: string, mode: "sign-in" | "sign-up") => Promise<void>;
  signOut: () => void;
  beginTrakt: () => Promise<void>;
  pollTrakt: () => Promise<void>;
  disconnectTrakt: () => void;
}

const AppContext = createContext<AppStore | null>(null);

export function useApp(): AppStore {
  const store = useContext(AppContext);
  if (!store) throw new Error("useApp must be used within <AppProvider>");
  return store;
}

export function AppProvider({ children }: { children: React.ReactNode }) {
  const [section, setSection] = useState<NavSection>("home");
  const [categories, setCategories] = useState<Category[]>([]);
  const [catalogConfigs, setCatalogConfigs] = useState<CatalogConfig[]>([]);
  const [homeServerRows, setHomeServerRows] = useState<Category[]>([]);
  const [continueWatching, setContinueWatching] = useState<MediaItem[]>([]);
  const [watchlist, setWatchlist] = useState<MediaItem[]>([]);
  const [selected, setSelected] = useState<MediaItem | null>(null);
  const [streams, setStreams] = useState<StreamSource[]>([]);
  const [selectedEpisode, setSelectedEpisode] = useState<{ season: number; episode: number } | null>(null);
  const [activeStream, setActiveStream] = useState<StreamSource | null>(null);
  const [activeChannel, setActiveChannel] = useState<IptvChannel | null>(null);
  const [addons, setAddons] = useState<InstalledAddon[]>([]);
  const [iptvSnapshot, setIptvSnapshot] = useState<IptvSnapshot>(emptyIptv);
  const [query, setQuery] = useState("");
  const [results, setResults] = useState<MediaItem[]>([]);
  const [settings, setSettings] = useState<AppSettings>(() => {
    const stored = loadStored<AppSettings>(settingsKey, defaultSettings);
    return {
      ...defaultSettings,
      ...stored,
      iptvPlaylists: loadPlaylists(),
      catalogs: mergeCatalogs(stored.catalogs, stored.hiddenCatalogIds)
    };
  });
  const [auth, setAuth] = useState(() => authClient.session);
  const [traktConnected, setTraktConnected] = useState(() => traktClient.isConnected);
  const [deviceCode, setDeviceCode] = useState<TraktDeviceCode | null>(null);
  const [busy, setBusy] = useState("Loading ARVIO");
  const [toast, setToast] = useState<string | null>(null);

  const [profiles, setProfiles] = useState<Profile[]>(() => {
    const stored = loadStored<Profile[]>(PROFILES_KEY, []);
    return stored.length ? stored : [makeProfile("Profile 1", 0xffe50914, 0)];
  });
  const [activeProfileId, setActiveProfileId] = useState<string | null>(() => loadStored<string | null>(ACTIVE_PROFILE_KEY, null));
  const [avatarImages, setAvatarImages] = useState<Record<string, string>>({});
  const [manageMode, setManageMode] = useState(false);
  const [view, setView] = useState<AppView>(() => {
    const stored = loadStored<Profile[]>(PROFILES_KEY, []);
    const activeId = loadStored<string | null>(ACTIVE_PROFILE_KEY, null);
    const skip = loadStored<AppSettings>(settingsKey, defaultSettings).skipProfileSelection;
    if (skip && activeId && stored.some((p) => p.id === activeId)) return "app";
    return "profiles";
  });

  const activeProfile = profiles.find((p) => p.id === activeProfileId) ?? null;

  const [heroPreview, setHeroPreview] = useState<MediaItem | null>(null);
  const hero = heroPreview ?? continueWatching[0] ?? categories[0]?.items[0] ?? null;

  // Refs so stable callbacks always read the latest values without re-creating.
  const addonsRef = useRef(addons);
  useEffect(() => {
    addonsRef.current = addons;
  }, [addons]);

  const deviceCodeRef = useRef(deviceCode);
  useEffect(() => {
    deviceCodeRef.current = deviceCode;
  }, [deviceCode]);

  const persistAddons = useCallback(async (next: InstalledAddon[]) => {
    setAddons(next);
    saveLocalAddons(next);
    await saveCloudAddons(authClient, next).catch(() => undefined);
  }, []);

  const refreshData = useCallback(async () => {
    setBusy("Syncing catalogs");
    try {
      const localAddons = loadLocalAddons();
      const cloud = authClient.session ? await pullCloudPayload(authClient).catch(() => null) : null;
      const mergedAddons = cloud?.addons?.length ? cloud.addons : localAddons;
      const addonState = mergedAddons.map((addon) => ({
        ...addon,
        enabled: !settings.disabledAddonIds.includes(addon.id) && addon.enabled !== false
      }));
      setAddons(addonState);
      saveLocalAddons(mergedAddons);

      const effectiveCatalogs = mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds);
      setCatalogConfigs(effectiveCatalogs.filter((catalog) => catalog.enabled));

      void loadHomeServerRows(settings.homeServers).then(setHomeServerRows).catch(() => setHomeServerRows([]));

      const [historyRows, traktRows, playbackRows, loadedIptv] = await Promise.all([
        authClient.session ? getContinueWatching(authClient).catch(() => []) : Promise.resolve([]),
        traktClient.isConnected ? traktClient.watchlist().catch(() => []) : Promise.resolve([]),
        traktClient.isConnected ? traktClient.playback().catch(() => []) : Promise.resolve([]),
        loadIptvSnapshot(
          settings.iptvPlaylists,
          settings.favoriteChannelIds,
          settings.favoriteGroupIds,
          settings.hiddenGroupIds,
          settings.groupOrder
        )
      ]);

      const cloudCw = historyRows.map(historyToItem);
      const traktCw = playbackRows.map(traktPlaybackToMedia);
      const cw = dedupeMedia([...cloudCw, ...traktCw]);
      setContinueWatching(cw);
      setWatchlist(await hydrateTraktItems(traktRows.map(traktItemToMedia)));
      setCategories(cw.length ? [{ id: "continue_watching", title: "Continue Watching", items: cw }] : []);
      setIptvSnapshot(loadedIptv);
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Failed to load ARVIO");
    } finally {
      setBusy("");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [
    settings.language,
    settings.iptvPlaylists,
    settings.catalogs,
    settings.hiddenCatalogIds,
    settings.disabledAddonIds,
    settings.favoriteChannelIds,
    settings.favoriteGroupIds,
    settings.hiddenGroupIds,
    settings.groupOrder,
    settings.homeServers
  ]);

  useEffect(() => {
    void refreshData();
  }, [refreshData]);

  useEffect(() => {
    saveStored(settingsKey, settings);
    savePlaylists(settings.iptvPlaylists);
    void saveCloudSettings(authClient, settings, addons).catch(() => undefined);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [settings]);

  useEffect(() => {
    saveStored(PROFILES_KEY, profiles);
    saveStored(ACTIVE_PROFILE_KEY, activeProfileId);
  }, [profiles, activeProfileId]);

  // When signed in, pull the shared profiles from the same account_sync_state
  // payload Android writes to (cloud wins, matching replaceProfilesFromCloud).
  useEffect(() => {
    if (!authClient.session) return;
    let cancelled = false;
    void pullCloudProfiles(authClient)
      .then((cloud) => {
        if (cancelled || !cloud.profiles.length) return;
        setProfiles(cloud.profiles);
        setAvatarImages(cloud.avatarImages);
        if (cloud.activeProfileId) setActiveProfileId(cloud.activeProfileId);
      })
      .catch(() => undefined);
    return () => {
      cancelled = true;
    };
  }, [auth]);

  useEffect(() => {
    const handle = setTimeout(async () => {
      if (!query.trim()) {
        setResults([]);
        return;
      }
      setResults(await searchMedia(query, settings.language).catch(() => []));
    }, 260);
    return () => clearTimeout(handle);
  }, [query, settings.language]);

  const updateSettings = useCallback((patch: Partial<AppSettings>) => {
    setSettings((prev) => ({ ...prev, ...patch }));
  }, []);

  const openDetails = useCallback(async (item: MediaItem) => {
    setSelectedEpisode(null);
    // Home-server items carry their own metadata + a direct stream URL — no TMDB.
    if (item.isHomeServer) {
      setSelected(item);
      setStreams(item.homeServerUrl
        ? [{ source: item.title, addonName: "Home Server", quality: "Direct", size: "", url: item.homeServerUrl }]
        : []);
      setBusy("");
      return;
    }
    setBusy("Opening details");
    setStreams([]);
    const detailed = await getDetails(item).catch(() => item);
    setSelected(detailed);
    // Movies fetch sources immediately; TV waits for an episode selection.
    if (item.mediaType === "movie") {
      setBusy("Finding sources");
      const found = await getStreams(addonsRef.current, detailed).catch(() => []);
      setStreams(found);
    }
    setBusy("");
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const loadEpisodeStreams = useCallback(async (item: MediaItem, season: number, episode: number) => {
    setSelectedEpisode({ season, episode });
    setStreams([]);
    setBusy("Finding sources");
    const found = await getStreams(addonsRef.current, item, season, episode).catch(() => []);
    setStreams(found);
    setBusy("");
  }, []);

  const advanceEpisode = useCallback(async (): Promise<boolean> => {
    if (!selected || selected.mediaType !== "tv" || !selectedEpisode) return false;
    const nextEpisode = selectedEpisode.episode + 1;
    setSelectedEpisode({ season: selectedEpisode.season, episode: nextEpisode });
    const found = await getStreams(addonsRef.current, selected, selectedEpisode.season, nextEpisode).catch(() => []);
    setStreams(found);
    const best = found.find((stream) => stream.url);
    setActiveStream(best ?? null);
    return Boolean(best);
  }, [selected, selectedEpisode]);

  const closeDetails = useCallback(() => {
    setSelected(null);
    setSelectedEpisode(null);
    setStreams([]);
  }, []);

  const playStream = useCallback((stream: StreamSource) => {
    if (!stream.url) {
      setToast("This source is not web-playable yet. Browser playback needs a direct HTTP/HLS URL.");
      return;
    }
    setActiveStream(stream);
  }, []);

  const playTrailer = useCallback(async (item: MediaItem) => {
    let url = item.trailerUrl ?? null;
    if (!url) {
      const detailed = await getDetails(item).catch(() => item);
      url = detailed.trailerUrl ?? null;
      setSelected((current) => current ?? detailed);
    }
    if (!url) {
      setToast("No trailer available for this title.");
      return;
    }
    setActiveStream({ source: "Trailer", addonName: "YouTube", quality: "Trailer", size: "", url });
  }, []);

  const playChannel = useCallback((channel: IptvChannel) => {
    setActiveChannel(channel);
    setActiveStream({
      source: channel.name,
      addonName: "Live TV",
      quality: "Live",
      size: "",
      url: channel.streamUrl,
      description: channel.group
    });
  }, []);

  const closePlayer = useCallback(() => {
    setActiveStream(null);
    setActiveChannel(null);
  }, []);

  const loadCatalogRow = useCallback((catalog: CatalogConfig) => loadCatalog(catalog, settings.language), [settings.language]);

  const installAddon = useCallback(async (url: string) => {
    const addon = await installAddonManifest(url);
    const next = [addon, ...addonsRef.current.filter((candidate) => candidate.id !== addon.id)];
    await persistAddons(next);
  }, [persistAddons]);

  const removeAddon = useCallback(async (addon: InstalledAddon) => {
    const next = addonsRef.current.filter((candidate) => candidate.id !== addon.id);
    await persistAddons(next);
  }, [persistAddons]);

  const setAddonsState = useCallback(async (next: InstalledAddon[]) => {
    await persistAddons(next);
    setSettings((prev) => ({
      ...prev,
      disabledAddonIds: next.filter((addon) => addon.enabled === false).map((addon) => addon.id)
    }));
  }, [persistAddons]);

  const signIn = useCallback(async (email: string, password: string, mode: "sign-in" | "sign-up") => {
    const session = mode === "sign-up" ? await authClient.signUp(email, password) : await authClient.signIn(email, password);
    setAuth(session);
    await refreshData();
  }, [refreshData]);

  const signOut = useCallback(() => {
    authClient.signOut();
    setAuth(null);
  }, []);

  const beginTrakt = useCallback(async () => {
    setDeviceCode(await traktClient.beginDeviceLink());
  }, []);

  const pollTrakt = useCallback(async () => {
    const code = deviceCodeRef.current;
    if (!code) return;
    await traktClient.pollDeviceToken(code.device_code);
    setTraktConnected(true);
    setDeviceCode(null);
    await refreshData();
  }, [refreshData]);

  const disconnectTrakt = useCallback(() => {
    traktClient.disconnect();
    setTraktConnected(false);
  }, []);

  const persistProfiles = useCallback((next: Profile[], activeId: string | null) => {
    setProfiles(next);
    setActiveProfileId(activeId);
    saveStored(PROFILES_KEY, next);
    saveStored(ACTIVE_PROFILE_KEY, activeId);
    void saveCloudProfiles(authClient, next, activeId).catch(() => undefined);
  }, []);

  const selectProfile = useCallback(async (profile: Profile) => {
    const updated = profiles.map((p) => (p.id === profile.id ? { ...p, lastUsedAt: Date.now() } : p));
    persistProfiles(updated, profile.id);
    setManageMode(false);
    setView("app");
    setSection("home");
    await refreshData();
  }, [profiles, persistProfiles, refreshData]);

  const createProfile = useCallback(async (name: string, avatarColor: number, avatarId: number) => {
    const profile = makeProfile(name || "Profile", avatarColor || randomProfileColor(), avatarId);
    persistProfiles([...profiles, profile], activeProfileId);
  }, [profiles, activeProfileId, persistProfiles]);

  const updateProfileAction = useCallback(async (profile: Profile) => {
    persistProfiles(profiles.map((p) => (p.id === profile.id ? profile : p)), activeProfileId);
  }, [profiles, activeProfileId, persistProfiles]);

  const deleteProfileAction = useCallback(async (id: string) => {
    const next = profiles.filter((p) => p.id !== id);
    persistProfiles(next, activeProfileId === id ? null : activeProfileId);
  }, [profiles, activeProfileId, persistProfiles]);

  const switchProfile = useCallback(() => {
    setManageMode(false);
    setView("profiles");
  }, []);

  const goToLogin = useCallback(() => setView("login"), []);
  const backToProfiles = useCallback(() => setView("profiles"), []);

  const value = useMemo<AppStore>(() => ({
    view,
    profiles,
    activeProfile,
    avatarImages,
    manageMode,
    setManageMode,
    selectProfile,
    createProfile,
    updateProfile: updateProfileAction,
    deleteProfile: deleteProfileAction,
    switchProfile,
    goToLogin,
    backToProfiles,
    section,
    setSection,
    categories,
    catalogConfigs,
    loadCatalogRow,
    homeServerRows,
    continueWatching,
    watchlist,
    hero,
    setHeroPreview,
    selected,
    streams,
    selectedEpisode,
    loadEpisodeStreams,
    advanceEpisode,
    activeStream,
    activeChannel,
    addons,
    iptvSnapshot,
    query,
    setQuery,
    results,
    settings,
    setSettings,
    updateSettings,
    auth,
    traktConnected,
    deviceCode,
    busy,
    toast,
    setToast,
    refreshData,
    openDetails,
    closeDetails,
    playStream,
    playTrailer,
    playChannel,
    closePlayer,
    installAddon,
    removeAddon,
    setAddonsState,
    signIn,
    signOut,
    beginTrakt,
    pollTrakt,
    disconnectTrakt
  }), [
    view, profiles, activeProfile, avatarImages, manageMode,
    selectProfile, createProfile, updateProfileAction, deleteProfileAction, switchProfile, goToLogin, backToProfiles,
    section, categories, catalogConfigs, loadCatalogRow, homeServerRows, continueWatching, watchlist, hero, heroPreview, selected, streams, selectedEpisode, loadEpisodeStreams, advanceEpisode, activeStream, activeChannel,
    addons, iptvSnapshot, query, results, settings, auth, traktConnected, deviceCode, busy, toast,
    updateSettings, refreshData, openDetails, closeDetails, playStream, playTrailer, playChannel, closePlayer,
    installAddon, removeAddon, setAddonsState, signIn, signOut, beginTrakt, pollTrakt, disconnectTrakt
  ]);

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
}

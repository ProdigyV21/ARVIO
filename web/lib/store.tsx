"use client";

import { createContext, useCallback, useContext, useEffect, useMemo, useRef, useState } from "react";
import { getStreams, getStreamsProgressive, installAddon as installAddonManifest, loadLocalAddons, normalizeAddons, saveLocalAddons } from "./addons";
import { AuthClient, SESSION_KEY, decodeJwtPayload } from "./auth";
import { config, getAuthPortalUrl } from "./config";
import { defaultCatalogs, mergeCatalogs } from "./catalogs";
import { getContinueWatching, isLiveStreamOrSportsItem, pullCloudContinueWatchingDismissals, pullCloudPayload, pullCloudProfiles, pullCloudTrackingSelection, pullCloudWatchedKeys, pullCloudWatchlist, removeContinueWatchingProgress, saveCloudAddons, saveCloudProfiles, saveCloudSettings, saveCloudTrackingSelection, saveCloudWatchlist, saveWatchedState } from "./cloud";
import { completionTimes, includeIptvContinueWatching, isUnwatchedContinueWatching, mergePartialContinueWatching, mergeTrackerContinueWatching, pruneCompletedResume, traktProgressActivityKey } from "./continueWatching";
import { cachedDebridDirectUrl, parseDebridStream } from "./debrid";
import { createPendingExternalPlayback } from "./externalPlayback";
import { trackPremiumEvent } from "./premiumAnalytics";
import { externalLaunchMode, openExternalPlayer } from "./externalPlayers";
import { playbackPlan } from "./streamCompatibility";
import { prepareBrowserStream } from "./prepareBrowserStream";
import { reportHomeServerPlayback } from "./homeServerPlayback";
import { loadHomeServerRows } from "./homeserver";
import { buildXtreamCatchupUrl, iptvPlaylistSignature, loadIptvChannelIdentities, loadIptvGuideForChannels, loadIptvSnapshot, loadPlaylists, savePlaylists } from "./iptv";
import { recordTvPlayback } from "./iptvSession";
import { dedupeMedia, historyToItem, hydrateTraktItems, traktItemToMedia, traktPlaybackToMedia, traktUpNextToMedia } from "./mappers";
import { loadStored, purgeLegacyStorage, removeStored, saveStored } from "./storage";
import { getDetails, getSeasonEpisodes, loadCatalog, searchMedia, resolveTmdbId } from "./tmdb";
import { verifyProfilePin } from "./profilePin";
import { flushSettingsOutbox, hasPendingSettings, queueSettings } from "./settingsOutbox";
import type { MetadataProviderId, ProviderPriorityConfig } from "./metadata/types";
import { TraktClient, type TraktDeviceCode } from "./trakt";
import { continueWatchingActivitySignature, createTraktActivityCheck, type TraktActivitySnapshot } from "./traktActivity";
import { mdblistClient } from "./mdblist";
import { simklClient, type SimklPinCode } from "./simkl";
import {
  activeSyncProvider,
  defaultTrackingPreferences,
  loadTrackingPreferences,
  readsFrom,
  sameTrackingSources,
  saveTrackingPreferences,
  syncClient,
  type TrackingPreferences
} from "./sync";
import type {
  AppSettings,
  AuthSession,
  Category,
  InstalledAddon,
  IptvChannel,
  IptvPlaylistEntry,
  IptvProgram,
  CatalogConfig,
  IptvSnapshot,
  MediaItem,
  NavSection,
  Profile,
  StreamSource
} from "./types";

// Reclaim space from superseded cache keys before anything reads or writes —
// once localStorage is full every save fails silently, which starved the
// Continue Watching seed and Trakt progress caches on long-lived installs.
purgeLegacyStorage();

export const authClient = new AuthClient();
export const traktClient = new TraktClient();

const settingsKey = "arvio.web.settings";
const PROFILES_KEY = "arvio.web.profiles";
const ACTIVE_PROFILE_KEY = "arvio.web.activeProfileId";
const AVATAR_IMAGES_KEY = "arvio.web.avatarImages";
// Which account the locally-cached profiles belong to. Profiles live in a single
// browser-global localStorage key, so without this a second account signing in
// on the same browser would see the FIRST account's profiles (they persist until
// the cloud pull replaces them — and a brand-new account has no cloud profiles to
// replace them with). We stamp the owner email and ignore cached profiles that
// belong to a different account.
const PROFILES_OWNER_KEY = "arvio.web.profilesOwner";

function currentAccountEmail(): string {
  return (authClient.session?.email ?? "").trim().toLowerCase();
}

// Cached profiles are only trusted when they belong to the signed-in account
// (or when signed out, where local-only profiles are expected).
function localProfilesMatchAccount(): boolean {
  const owner = (loadStored<string | null>(PROFILES_OWNER_KEY, null) ?? "").trim().toLowerCase();
  const email = currentAccountEmail();
  if (!email) return true; // signed out: local profiles are fine
  return owner === email;
}

export function getPriorityConfig(settings: AppSettings): ProviderPriorityConfig {
  return {
    movieProviders: (settings.metadataMovieProviders as MetadataProviderId[]) ?? ["tmdb"],
    tvProviders: (settings.metadataTvProviders as MetadataProviderId[]) ?? ["tvdb", "tmdb"],
    animeProviders: (settings.metadataAnimeProviders as MetadataProviderId[]) ?? ["anilist", "tvdb", "tmdb"],
    customTmdbApiKey: settings.customTmdbApiKey,
    customTvdbApiKey: settings.customTvdbApiKey,
    customTvdbUserPin: settings.customTvdbUserPin
  };
}

// Instant-paint caches for Continue Watching / Watchlist. The TTL is deliberately
// long: a stale list is strictly better than a blank rail (the fresh fetch
// replaces it seconds later). The old 24h TTL left the rail blank on the first
// open of the day whenever the previous enriched refresh was more than a day ago.
const LIST_CACHE_TTL_MS = 7 * 24 * 60 * 60 * 1000;

function cwCacheKeyFor(profileId: string | null | undefined) {
  return `arvio.web.cw.v2:${profileId ?? "no-profile"}`;
}

function watchlistCacheKeyFor(profileId: string | null | undefined) {
  return `arvio.web.watchlist.v1:${profileId ?? "no-profile"}`;
}

function readCachedList(key: string): MediaItem[] {
  const cached = loadStored<{ at: number; items: MediaItem[] } | null>(key, null);
  return cached?.items?.length && Date.now() - cached.at < LIST_CACHE_TTL_MS ? cached.items : [];
}

// Cache-only representation of a media item: cast/related are the bulk of a
// hydrated item (tens of KB each) and the rails never render them — details
// refetches both anyway. Full items once pushed localStorage past its ~5MB
// quota, after which EVERY cache write failed silently.
function slimCacheItem(item: MediaItem): MediaItem {
  const { cast, related, ...slim } = item;
  return slim;
}

function saveCachedList(key: string, items: MediaItem[], max: number) {
  try {
    saveStored(key, { at: Date.now(), items: items.slice(0, max).map(slimCacheItem) });
  } catch {
    // Never let a cache write break the refresh.
  }
}

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
  frameRateMatchingMode: "off",
  trailerAutoPlay: true,
  trailerSound: false,
  trailerDelaySeconds: 2,
  trailerInCards: true,
  volumeBoostDb: 0,
  includeSpecials: false,
  qualityFilterPreset: "off",
  qualityFilters: [],
  language: "en-US",
  defaultSubtitle: "en",
  secondarySubtitle: "",
  audioLanguage: "",
  subtitleSize: 100,
  subtitleColor: "#ffffff",
  subtitleColorName: "White",
  subtitleOffsetMs: 0,
  subtitleOffset: "bottom",
  subtitleStyle: "outline",
  subtitleStylized: false,
  filterSubtitlesByLanguage: false,
  removeHearingImpaired: true,
  aiSubtitlesEnabled: false,
  aiSubtitleModel: "off",
  aiAutoSelect: false,
  aiApiKey: "",
  defaultPlayer: "browser",
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
  torrServerBaseUrl: "",
  skipProfileSelection: false,
  cardDensity: "comfortable",
  catalogs: defaultCatalogs,
  hiddenCatalogIds: [],
  hiddenHomeServerCatalogIds: [],
  disabledAddonIds: [],
  homeServers: [],
  iptvPlaylists: [],
  iptvStalkerUrl: "",
  iptvStalkerMac: "",
  favoriteChannelIds: [],
  favoriteGroupIds: [],
  hiddenGroupIds: [],
  groupOrder: [],
  customTmdbApiKey: "",
  customTvdbApiKey: "",
  customTvdbUserPin: "",
  metadataMovieProviders: ["tmdb"],
  metadataTvProviders: ["tvdb", "tmdb"],
  metadataAnimeProviders: ["anilist", "tvdb", "tmdb"],
  iptvSortOrder: "provider"
};


const emptyIptv: IptvSnapshot = {
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

function mediaWatchKey(item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) {
  if (item.mediaType === "movie") return `movie:${item.id}`;
  const season = seasonNumber ?? item.seasonNumber ?? null;
  const episode = episodeNumber ?? item.episodeNumber ?? null;
  if (season !== null && episode !== null && season !== undefined && episode !== undefined) return `tv:${item.id}:${season}:${episode}`;
  return `tv:${item.id}`;
}

function traktWatchedKeys(movies: unknown[], shows: unknown[]) {
  const keys = new Set<string>();
  movies.forEach((raw) => {
    const item = raw as { movie?: { ids?: { tmdb?: number } }; status?: string; last_watched_at?: string };
    const tmdb = item.movie?.ids?.tmdb;
    if (tmdb) {
      if (item.status === undefined || item.status === "completed") {
        keys.add(`movie:${tmdb}`);
      }
    }
  });
  shows.forEach((raw) => {
    const item = raw as {
      show?: { ids?: { tmdb?: number }; aired_episodes?: number };
      status?: string;
      seasons?: Array<{ number?: number; episodes?: Array<{ number?: number }> }>;
    };
    const tmdb = item.show?.ids?.tmdb;
    if (!tmdb) return;
    let watchedEpisodes = 0;
    item.seasons?.forEach((season) => {
      const seasonNumber = season.number;
      // Specials (season 0) don't count toward aired_episodes.
      if (seasonNumber === undefined || seasonNumber === null) return;
      season.episodes?.forEach((episode) => {
        if (episode.number === undefined || episode.number === null) return;
        keys.add(`tv:${tmdb}:${seasonNumber}:${episode.number}`);
        if (seasonNumber > 0) watchedEpisodes += 1;
      });
    });
    // If entire show marked completed, or all aired episodes watched, badge whole show
    const aired = item.show?.aired_episodes;
    if (item.status === "completed" || (typeof aired === "number" && aired > 0 && watchedEpisodes >= aired)) {
      keys.add(`tv:${tmdb}`);
    }
  });
  return keys;
}

function filterWatchedContinueWatching(items: MediaItem[], watchedKeys: Set<string>, addons: InstalledAddon[], completions?: Map<string, number>) {
  const nonLive = items.filter((item) => !isLiveStreamOrSportsItem(item, addons));
  if (!watchedKeys.size) return nonLive;
  return nonLive.filter((item) => isUnwatchedContinueWatching(item, watchedKeys, completions));
}


function isMediaWatched(item: MediaItem, watchedKeys: Set<string>, seasonNumber?: number | null, episodeNumber?: number | null) {
  if (item.isWatched) return true;
  const key = mediaWatchKey(item, seasonNumber, episodeNumber);
  // An episode counts as watched only via its own tv:id:season:episode key —
  // never via the show-level key, which now means "entire show completed".
  return Boolean(key && watchedKeys.has(key));
}

function isPausedPlaybackItem(item: MediaItem) {
  if (item.badge === "Up Next") return true;
  // Match the Android app: in-progress items are 3%–90% watched.
  const progress = item.progress ?? 0;
  return progress >= 3 && progress < 90;
}

function traktActivityTime(raw: unknown) {
  const item = raw as { last_watched_at?: string; last_updated_at?: string; show?: { title?: string } };
  return Date.parse(item.last_watched_at ?? item.last_updated_at ?? "") || 0;
}

// How many watched shows we ask Trakt for per-show progress. The activity-keyed
// progress cache means only shows whose last_watched_at MOVED cost a call on a
// repeat refresh, so this ceiling mostly bounds the very first sync of a large
// library. 120 silently truncated Up Next for heavy Trakt users.
const UP_NEXT_SHOW_LIMIT = 300;
// How many Continue Watching rows get the (expensive) per-item TMDB hydration.
// Rows past this still render — they just use the data Trakt/cloud already gave
// us — instead of being dropped from the rail entirely.
const CW_HYDRATE_LIMIT = 50;

async function loadTraktUpNext(watchedShowsRows: unknown[], includeSpecials: boolean, hiddenShowIds: Set<number>, isCurrent = () => true) {
  // Fetch per-show progress for the whole watched-shows list (newest-activity
  // first) so Continue Watching surfaces every show with an unwatched next
  // episode — not just the most recent handful. The activity-keyed cache means
  // repeat refreshes only hit shows whose last_watched_at actually changed, and
  // concurrency is throttled so this never becomes the old 48-parallel burst.
  //
  // Shows the user hid from progress on Trakt are dropped BEFORE the slice, so
  // they neither appear in the rail nor consume one of the fetch slots.
  const watchedShows = watchedShowsRows
    .filter((raw) => {
      const traktId = (raw as { show?: { ids?: { trakt?: number } } }).show?.ids?.trakt;
      return !(typeof traktId === "number" && hiddenShowIds.has(traktId));
    })
    .sort((a, b) => traktActivityTime(b) - traktActivityTime(a))
    .slice(0, UP_NEXT_SHOW_LIMIT);
  const results: Array<MediaItem | null> = new Array(watchedShows.length).fill(null);
  let cursor = 0;
  // Distinguishes "progress fetch failed" (rate-limited/blocked) from "show has
  // no next episode" — an empty result with failures present is a partial
  // outage, not an empty Continue Watching.
  let fetchFailures = 0;
  const workers = Array.from({ length: Math.min(8, watchedShows.length) }, async () => {
    while (cursor < watchedShows.length && isCurrent()) {
      const index = cursor;
      cursor += 1;
      const watched = watchedShows[index];
      const row = watched as { show?: { ids?: { trakt?: number } } };
      const traktId = row.show?.ids?.trakt;
      if (!traktId) continue;
      // The client owns the account-scoped, expiring cache. A second, timeless
      // cache here hid newly aired episodes and could leak progress by profile.
      const progress = await traktClient.showProgress(traktId, includeSpecials, traktProgressActivityKey(watched)).catch(() => null);
      if (!progress) fetchFailures += 1;
      results[index] = traktUpNextToMedia(watched, progress);
    }
  });
  await Promise.all(workers);
  const items = results
    .filter((item): item is MediaItem => Boolean(item))
    .filter((item) => includeSpecials || item.seasonNumber !== 0);
  return { items, fetchFailures };
}

function mergeTraktWithLocalResume(traktItems: MediaItem[], localItems: MediaItem[]) {
  if (!localItems.length) return traktItems;
  const localByEpisode = new Map(localItems.map((item) => [
    `${item.mediaType}:${item.id}:${item.seasonNumber ?? ""}:${item.episodeNumber ?? ""}`,
    item
  ]));
  return traktItems.map((item) => {
    const local = localByEpisode.get(`${item.mediaType}:${item.id}:${item.seasonNumber ?? ""}:${item.episodeNumber ?? ""}`);
    if (!local) return item;
    return {
      ...item,
      image: item.image || local.image,
      backdrop: item.backdrop || local.backdrop,
      episodeStill: item.episodeStill || local.episodeStill,
      episodeTitle: item.episodeTitle ?? local.episodeTitle ?? null,
      progress: Math.max(item.progress ?? 0, local.progress ?? 0),
      resumePositionSeconds: Math.max(item.resumePositionSeconds ?? 0, local.resumePositionSeconds ?? 0),
      durationSeconds: Math.max(item.durationSeconds ?? 0, local.durationSeconds ?? 0),
      streamAddonId: item.streamAddonId ?? local.streamAddonId,
      timeRemainingLabel: local.timeRemainingLabel ?? item.timeRemainingLabel ?? null
    };
  });
}

async function hydrateContinueWatchingItems(items: MediaItem[]) {
  // Throttled pool (not one big Promise.all): 50 parallel TMDB calls at startup
  // starved user-initiated fetches (opening a details page mid-boot timed out
  // and rendered without seasons/cast). Only the head of the rail pays for
  // hydration; the tail is passed through unhydrated rather than DROPPED, which
  // used to silently cut Continue Watching off at 50 rows.
  const source = items.slice(0, CW_HYDRATE_LIMIT);
  const tail = items.slice(CW_HYDRATE_LIMIT);
  const hydrated: Array<MediaItem | null> = new Array(source.length).fill(null);
  let cursor = 0;
  const workers = Array.from({ length: Math.min(10, source.length) }, async () => {
    while (cursor < source.length) {
      const index = cursor;
      cursor += 1;
      const item = source[index];
      const episodeStillPromise = item.mediaType === "tv" && item.seasonNumber != null && item.episodeNumber != null
        ? getSeasonEpisodes(item.id, item.seasonNumber)
            .then((episodes) => episodes.find((episode) => episode.episodeNumber === item.episodeNumber)?.still ?? null)
            .catch(() => null)
        : Promise.resolve(null);
      const [details, episodeStill] = await Promise.all([
        getDetails(item).catch(() => item),
        episodeStillPromise
      ]);
      const enriched = {
        ...details,
        ...item,
        image: item.image || details.image,
        backdrop: item.backdrop || details.backdrop,
        episodeStill: null,
        overview: details.overview || item.overview,
        rating: details.rating || item.rating,
        duration: details.duration || item.duration
      };
      hydrated[index] = {
        ...enriched,
        episodeStill: episodeStill || item.episodeStill || null
      };
    }
  });
  await Promise.all(workers);
  // Metadata providers can disagree about season numbering. Artwork must never
  // change Trakt's selected episode or remove it based on TMDB episode counts.
  return [...hydrated, ...tail].filter((item): item is MediaItem => Boolean(item));
}


// Only locally advancing playback uses TMDB's season boundaries, not imported
// tracker progress (which may use a different episode ordering).
function nextLocalEpisode(item: MediaItem): MediaItem | null {
  const { seasonNumber, episodeNumber } = item;
  if (!seasonNumber || !episodeNumber) return item;
  const seasons = (item.seasons ?? []).filter((season) => season.seasonNumber > 0);
  const current = seasons.find((season) => season.seasonNumber === seasonNumber);
  if (!current?.episodeCount || episodeNumber <= current.episodeCount) return item;
  const next = seasons.filter((season) => season.seasonNumber > seasonNumber && (season.episodeCount ?? 0) > 0)
    .sort((a, b) => a.seasonNumber - b.seasonNumber)[0];
  return next ? { ...item, seasonNumber: next.seasonNumber, episodeNumber: 1, episodeTitle: null, episodeStill: null, subtitle: `S${next.seasonNumber} E1` } : null;
}

function sameSettings(a: AppSettings, b: AppSettings) {
  return JSON.stringify(a) === JSON.stringify(b);
}

export interface AppStore {
  view: AppView;
  cloudLoginRequired: boolean;
  profiles: Profile[];
  activeProfile: Profile | null;
  avatarImages: Record<string, string>;
  manageMode: boolean;
  setManageMode: (value: boolean) => void;
  selectProfile: (profile: Profile, pin?: string) => Promise<void>;
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
  isWatched: (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) => boolean;
  markWatchedLocally: (item: { mediaType: MediaItem["mediaType"]; id: number; season?: number | null; episode?: number | null }, watched?: boolean) => void;
  hero: MediaItem | null;
  setHeroPreview: (item: MediaItem | null) => void;
  selected: MediaItem | null;
  streams: StreamSource[];
  selectedEpisode: { season: number; episode: number } | null;
  loadEpisodeStreams: (item: MediaItem, season: number, episode: number) => Promise<StreamSource[]>;
  advanceEpisode: () => Promise<boolean>;
  activeStream: StreamSource | null;
  activeChannel: IptvChannel | null;
  addons: InstalledAddon[];
  addonsReady: boolean;
  iptvSnapshot: IptvSnapshot;
  query: string;
  setQuery: (value: string) => void;
  results: MediaItem[];
  settings: AppSettings;
  setSettings: (next: AppSettings) => void;
  updateSettings: (patch: Partial<AppSettings>) => void;
  auth: AuthSession | null;
  traktConnected: boolean;
  mdblistConnected: boolean;
  simklConnected: boolean;
  trackingPreferences: TrackingPreferences;
  updateTrackingPreferences: (patch: Partial<TrackingPreferences>) => Promise<void>;
  deviceCode: TraktDeviceCode | null;
  simklDeviceCode: SimklPinCode | null;
  busy: string;
  toast: string | null;
  setToast: (value: string | null) => void;

  refreshData: (profileIdOverride?: string | null) => Promise<void>;
  refreshIptv: () => Promise<void>;
  loadIptvGuide: (channels: IptvChannel[]) => Promise<void>;
  openDetails: (item: MediaItem) => Promise<void>;
  closeDetails: () => void;
  playStream: (stream: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean }) => void;
  playTrailer: (item: MediaItem) => Promise<void>;
  playChannel: (channel: IptvChannel) => void;
  recordChannelPlayback: (channel: IptvChannel) => void;
  playCatchup: (channel: IptvChannel, program: IptvProgram) => void;
  closePlayer: () => void;
  installAddon: (url: string) => Promise<void>;
  removeAddon: (addon: InstalledAddon) => Promise<void>;
  setAddonsState: (next: InstalledAddon[]) => Promise<void>;
  signIn: (email: string, password: string, mode: "sign-in" | "sign-up") => Promise<void>;
  signOut: () => void;
  beginTrakt: () => Promise<void>;
  pollTrakt: () => Promise<void>;
  disconnectTrakt: () => void;
  connectMdblist: (key: string) => Promise<void>;
  disconnectMdblist: () => void;
  beginSimkl: () => Promise<void>;
  pollSimkl: () => Promise<void>;
  disconnectSimkl: () => void;
  // Watchlist list-source switcher (Trakt custom lists / collection).
  loadTraktLists: () => Promise<Array<{ id: string; name: string }>>;
  loadTraktListItems: (source: string) => Promise<MediaItem[]>;
  loadTrackerLibrary: (provider: "trakt" | "simkl", source: string) => Promise<MediaItem[]>;
  settingsSyncState: "local" | "saved" | "pending" | "error";
  searchState: "idle" | "loading" | "error";

  toggleWatchlist: (item: MediaItem) => Promise<void>;
  toggleWatched: (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null, skipSync?: boolean) => Promise<void>;
  removeFromContinueWatching: (item: MediaItem) => Promise<void>;
  activeContextMenu: ContextMenuTarget | null;
  openContextMenu: (target: ContextMenuTarget) => void;
  closeContextMenu: () => void;
}

export interface ContextMenuTarget {
  item?: MediaItem;
  title?: string;
  subtitle?: string;
  isContinueWatching?: boolean;
  position?: { x: number; y: number } | null;
  actions?: Array<{
    id: string;
    label: string;
    icon: React.ReactNode;
    danger?: boolean;
    action: () => void | Promise<void>;
  }>;
}

export const AppContext = createContext<AppStore | null>(null);

export function useApp(): AppStore {
  const store = useContext(AppContext);
  if (!store) throw new Error("useApp must be used within <AppProvider>");
  return store;
}

export function AppProvider({
  children,
  initialView,
  cloudLoginRequired = false
}: {
  children: React.ReactNode;
  initialView?: AppView;
  cloudLoginRequired?: boolean;
}) {
  const [section, setSection] = useState<NavSection>("home");
  // Seed Continue Watching + Watchlist synchronously from the last-known lists
  // for the active profile so both are on screen the instant the app renders —
  // without this CW stays blank until the ~120-call Trakt up-next fetch returns,
  // and only appeared after a navigation round-trip remounted the screen.
  const initialProfileId = loadStored<string | null>(ACTIVE_PROFILE_KEY, null);
  const initialCw = readCachedList(cwCacheKeyFor(initialProfileId));
  const initialWatchlist = readCachedList(watchlistCacheKeyFor(initialProfileId));
  const [categories, setCategories] = useState<Category[]>(
    initialCw.length ? [{ id: "continue_watching", title: "Continue Watching", items: initialCw }] : []
  );
  const [catalogConfigs, setCatalogConfigs] = useState<CatalogConfig[]>([]);
  const [homeServerRows, setHomeServerRows] = useState<Category[]>([]);
  const [continueWatching, setContinueWatching] = useState<MediaItem[]>(initialCw);
  const [watchlist, setWatchlist] = useState<MediaItem[]>(initialWatchlist);
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
  const [selectedEpisode, setSelectedEpisode] = useState<{ season: number; episode: number } | null>(null);
  const [activeStream, setActiveStream] = useState<StreamSource | null>(null);
  const [activeChannel, setActiveChannel] = useState<IptvChannel | null>(null);
  const playingRef = useRef(false);
  playingRef.current = Boolean(activeStream || activeChannel);
  const [addons, setAddons] = useState<InstalledAddon[]>([]);
  const [addonsReady, setAddonsReady] = useState(false);
  const [iptvSnapshot, setIptvSnapshot] = useState<IptvSnapshot>(emptyIptv);
  const [query, setQuery] = useState("");
  const [results, setResults] = useState<MediaItem[]>([]);
  const [searchState, setSearchState] = useState<"idle" | "loading" | "error">("idle");
  const [settingsSyncState, setSettingsSyncState] = useState<"local" | "saved" | "pending" | "error">("local");
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
  const [mdblistConnected, setMdblistConnected] = useState(() => mdblistClient.isConnected);
  const [simklConnected, setSimklConnected] = useState(() => simklClient.isConnected);
  const [deviceCode, setDeviceCode] = useState<TraktDeviceCode | null>(null);
  const [simklDeviceCode, setSimklDeviceCode] = useState<SimklPinCode | null>(null);
  const [busy, setBusy] = useState("Loading ARVIO");
  const [toast, setToast] = useState<string | null>(null);
  const [cloudProfilesHydrated, setCloudProfilesHydrated] = useState(() => !authClient.session);
  const refreshInFlightRef = useRef<{ key: string; promise: Promise<void> } | null>(null);
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
  const iptvRefresh = useRef<{ key: string; promise: Promise<void> } | null>(null);
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

  const [heroPreview, setHeroPreview] = useState<MediaItem | null>(null);
  const hero = heroPreview ?? continueWatching[0] ?? categories[0]?.items[0] ?? null;

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
    const effectiveCatalogs = mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds);
    setCatalogConfigs(effectiveCatalogs.filter((catalog) => catalog.enabled && catalog.sourceType !== "home-server"));
  }, [settings.catalogs, settings.hiddenCatalogIds]);

  useEffect(() => {
    let cancelled = false;
    const effectiveCatalogs = mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds);
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
  }, [settings.catalogs, settings.hiddenCatalogIds, settings.hiddenHomeServerCatalogIds, settings.homeServers]);

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
    void (async () => {
      try {
        const tg = await import("./telegram");
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

  const persistAddons = useCallback(async (next: InstalledAddon[], options: { removedIds?: string[] } = {}) => {
    const normalized = normalizeAddons(next);
    setAddons(normalized);
    saveLocalAddons(normalized);
    // Cloud writes are union-based; a removal must be an explicit id list so the
    // shared library can never be shrunk by a stale/partial in-memory view.
    await saveCloudAddons(authClient, normalized, activeProfileId, { removedIds: options.removedIds }).catch(() => undefined);
  }, [activeProfileId]);

  const refreshData = useCallback((profileIdOverride?: string | null, background = false) => {
    const profileId = profileIdOverride ?? activeProfileId;
    const accountId = authClient.session?.userId;
    const key = `${accountId ?? "local"}:${profileId ?? "no-profile"}`;
    refreshKeyRef.current = key;
    const existing = refreshInFlightRef.current;
    if (existing?.key === key) return existing.promise;
    const generation = ++refreshGenerationRef.current;
    const isCurrent = () => refreshGenerationRef.current === generation && refreshKeyRef.current === key &&
      activeProfileIdRef.current === profileId && authClient.session?.userId === accountId;
    traktClient.setProfile(profileId);
    mdblistClient.setProfile(profileId);
    simklClient.setProfile(profileId);
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
        const cachedWatchlist = readCachedList(watchlistCacheKey);
        if (cachedWatchlist.length) {
          setWatchlist((current) => current.length ? current : cachedWatchlist);
        }
      } catch {
        // Cache read must never block the refresh.
      }
      try {
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
            const localTracking = traktClient.token || simklClient.token || mdblistClient.key
              ? {
                  provider: traktClient.token ? "TRAKT" as const : simklClient.token ? "SIMKL" as const : "MDBLIST" as const,
                  traktToken: traktClient.token,
                  mdbListApiKey: mdblistClient.key,
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
            simklClient.setToken(cloudTracking.simklToken);
            mdblistClient.setKey(cloudTracking.mdbListApiKey);
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
      if (cloud?.settings && !settingsChangedDuringPull) {
        effectiveSettings = {
          ...defaultSettings,
          ...currentSettings,
          ...cloud.settings,
          catalogs: mergeCatalogs(cloud.settings?.catalogs ?? currentSettings.catalogs, cloud.settings?.hiddenCatalogIds ?? currentSettings.hiddenCatalogIds),
          iptvPlaylists: cloud.settings?.iptvPlaylists ?? currentSettings.iptvPlaylists,
          favoriteChannelIds: cloud.settings?.favoriteChannelIds ?? currentSettings.favoriteChannelIds,
          iptvTvSession: cloud.settings?.iptvTvSession ?? currentSettings.iptvTvSession,
          favoriteGroupIds: cloud.settings?.favoriteGroupIds ?? currentSettings.favoriteGroupIds,
          hiddenGroupIds: cloud.settings?.hiddenGroupIds ?? currentSettings.hiddenGroupIds,
          lockedIptvGroupIds: cloud.settings?.lockedIptvGroupIds ?? currentSettings.lockedIptvGroupIds,
          groupOrder: cloud.settings?.groupOrder ?? currentSettings.groupOrder
        };
        if (!sameSettings(settingsRef.current, effectiveSettings)) setSettings(effectiveSettings);
        // Record what we just synced FROM the cloud (same shape the autosave
        // effect compares against) so it doesn't push it straight back.
        lastSyncedSettingsRef.current = JSON.stringify({ settings: effectiveSettings, activeProfileId: profileId });
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
      const source = cloudAddons.length
        ? cloudAddons
        : localNormalized.length
          ? localNormalized
          : currentAddons;
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
      if (cloudReturnedEmpty && source.length && authClient.session) {
        void saveCloudAddons(authClient, source, profileId).catch(() => undefined);
      }

      const effectiveCatalogs = mergeCatalogs(effectiveSettings.catalogs, effectiveSettings.hiddenCatalogIds);
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
      const [historyRows, traktRows, playbackRows, watchedMoviesRows, watchedShowsRows, cloudWatchlistRows, cloudWatchedKeys, cloudDismissals, hiddenShowIds, cwMovies, cwShows] = await Promise.all([
        authClient.session ? getContinueWatching(authClient, profileId, addonState).catch(() => []) : Promise.resolve([]),
        traktReady ? client.watchlist().catch(() => failedRead("watchlist")) : Promise.resolve([]),
        traktReady ? client.playback().catch(() => failedRead("playback")) : Promise.resolve([]),
        watchedMoviesRead,
        watchedShowsRead,
        authClient.session ? pullCloudWatchlist(authClient, profileId).catch(() => []) : Promise.resolve([]),
        authClient.session ? pullCloudWatchedKeys(authClient, profileId).catch(() => new Set<string>()) : Promise.resolve(new Set<string>()),
        authClient.session ? pullCloudContinueWatchingDismissals(authClient, profileId).catch(() => new Map<string, number>()) : Promise.resolve(new Map<string, number>()),
        // Only Trakt has a hidden-from-progress concept; MDBList reads return an
        // empty set so the filters below are no-ops for it.
        readsFrom("continueWatching", "trakt")
          ? traktClient.hiddenProgressShowIds().catch(() => new Set<number>())
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
      const cloudCw = historyRows.map(historyToItem);
      const traktPlaybackCw = playbackRows
        .map(traktPlaybackToMedia)
        .filter(isPausedPlaybackItem)
        .filter((item) => !isHiddenShow(item) && !isDismissed(item));

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
        void hydrateTraktItems(fastWatchlistSource).then((hydrated) => {
          if (hydrated.length && isCurrent()) {
            setWatchlist((current) => current.length ? current : hydrated);
            saveCachedList(watchlistCacheKey, hydrated, 60);
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
      const upNextRows = upNext.items;
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
      const traktOutage = readFailures.has("playback") || readFailures.has("cw-watched") || readFailures.has("cw-movies") || readFailures.has("cw-shows");
      if (!traktOutage && upNext.fetchFailures === 0 && isCurrent() && activitySignature) {
        traktActivityRef.current = { key: `${accountId ?? "local"}:${profileId}`, signature: activitySignature };
      }
      // Enriched CW (adds Trakt up-next episodes) replaces the fast paint. When
      // the fresh list is non-empty we swap it in. An empty result with Trakt
      // connected and reads healthy means the rail is GENUINELY empty — clear it
      // and its cache so a finished library can't resurrect a stale rail. During
      // an outage (all reads empty) we keep whatever is painted.
      if (!traktOutage && isCurrent()) {
        const reconcile = (current: MediaItem[]) => upNext.fetchFailures > 0
          ? mergePartialContinueWatching(cw, current.filter((item) => !isHiddenShow(item) && !isDismissed(item)), cwCompletions)
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
        } else if (traktReady && upNext.fetchFailures === 0) {
          // Only clear on a CLEAN pass: any per-show progress failure means this
          // empty result could be a partial outage, and wiping the cache would
          // recreate the blank-rail-on-startup bug the seed exists to prevent.
          cwSourceRef.current = "fresh";
          setContinueWatching([]);
          setCategories((current) => current.filter((c) => c.id !== "continue_watching"));
          removeStored(cwCacheKey);
        }
      }
      // Refresh the watchlist with the authoritative Trakt list if it differs.
      const watchlistSource = watchlistReady && !readFailures.has("watchlist")
        ? dedupeMedia(traktRows.map(traktItemToMedia))
        : cloudWatchlistRows;
      const hydratedWatchlist = await hydrateTraktItems(watchlistSource);
      if (!readFailures.has("watchlist") && isCurrent()) {
        setWatchlist(hydratedWatchlist);
        saveCachedList(watchlistCacheKey, hydratedWatchlist, 60);
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

  const refreshIptv = useCallback(async () => {
    const currentSettings = settingsRef.current;
    const profileId = activeProfileIdRef.current;
    const account = authClient.session?.userId;
    const signature = iptvPlaylistSignature(currentSettings.iptvPlaylists);
    const key = `${account}:${profileId}:${signature}`;
    if (iptvRefresh.current?.key === key) return iptvRefresh.current.promise;
    const isCurrent = () => activeProfileIdRef.current === profileId && authClient.session?.userId === account && iptvPlaylistSignature(settingsRef.current.iptvPlaylists) === signature;
    const run = (async () => {
    setBusy("Loading TV");
    try {
      const loadedIptv = await loadIptvSnapshot(
        currentSettings.iptvPlaylists,
        currentSettings.favoriteChannelIds,
        currentSettings.favoriteGroupIds,
        currentSettings.hiddenGroupIds,
        currentSettings.groupOrder,
        { userAgent: currentSettings.customUserAgent }
      );
      // Stamp which playlists this snapshot came from so Live TV can reuse it
      // on re-entry instead of rebuilding ~139k channels every visit.
      if (isCurrent()) setIptvSnapshot({ ...loadedIptv, signature });
    } catch (error) {
      if (isCurrent()) setToast(error instanceof Error ? error.message : "Failed to load Live TV");
    } finally {
      if (isCurrent()) setBusy("");
    }
    })();
    iptvRefresh.current = { key, promise: run };
    return run.finally(() => { if (iptvRefresh.current?.promise === run) iptvRefresh.current = null; });
  }, []);

  useEffect(() => {
    const channels = iptvSnapshot.allChannels ?? iptvSnapshot.channels;
    if (!channels.length || iptvSnapshot.identitiesLoaded || iptvSnapshot.signature !== iptvPlaylistSignature(settings.iptvPlaylists)) return;
    let cancelled = false;
    const profileId = activeProfileId;
    void loadIptvChannelIdentities(settings.iptvPlaylists, channels, { userAgent: settings.customUserAgent }).then(enriched => {
      if (cancelled || activeProfileIdRef.current !== profileId) return;
      const byId = new Map(enriched.map(channel => [channel.id, channel]));
      setIptvSnapshot(current => ({ ...current, identitiesLoaded: true, allChannels: enriched,
        channels: current.channels.map(channel => byId.get(channel.id) ?? channel) }));
    });
    return () => { cancelled = true; };
  }, [iptvSnapshot.allChannels, iptvSnapshot.channels, iptvSnapshot.signature, iptvSnapshot.identitiesLoaded, settings.iptvPlaylists, settings.customUserAgent, activeProfileId]);

  const loadIptvGuide = useCallback(async (channels: IptvChannel[]) => {
    if (!channels.length) return;
    const currentSettings = settingsRef.current;
    const profileId = activeProfileIdRef.current;
    const account = authClient.session?.userId;
    const signature = iptvPlaylistSignature(currentSettings.iptvPlaylists);
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
      if (activeProfileIdRef.current !== profileId || authClient.session?.userId !== account || iptvPlaylistSignature(settingsRef.current.iptvPlaylists) !== signature) return;
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

  useEffect(() => {
    if (typeof window === "undefined") return;
    const hash = window.location.hash || "";
    if (hash.includes("access_token=") && hash.includes("refresh_token=")) {
      if (config.selfHosted) {
        window.history.replaceState({}, document.title, window.location.pathname + window.location.search);
        return;
      }
      try {
        const params = new URLSearchParams(hash.replace(/^#/, ""));
        const access_token = params.get("access_token");
        const refresh_token = params.get("refresh_token");
        const email = params.get("email") || "";
        const expires_in = Number(params.get("expires_in") || "3600");
        if (access_token && refresh_token) {
          const payload = decodeJwtPayload(access_token);
          const userId = (payload.sub as string | undefined) ?? "";
          const provider = ((payload.iss as string | undefined) === "arvio-netlify" ? "netlify" : "supabase") as "netlify" | "supabase";
          const session = {
            accessToken: access_token,
            refreshToken: refresh_token,
            userId,
            email,
            expiresAt: Date.now() + expires_in * 1000,
            provider
          };

          saveStored(SESSION_KEY, session);
          authClient.session = session;
          setAuth(session);
          setCloudProfilesHydrated(false);

          // Clear hash parameters from URL without a page reload
          const cleanUrl = window.location.pathname + window.location.search;
          window.history.replaceState({}, document.title, cleanUrl);

          // Redirect to appropriate view
          const stored = loadStored<Profile[]>(PROFILES_KEY, []);
          const activeId = loadStored<string | null>(ACTIVE_PROFILE_KEY, null);
          const skip = settings.skipProfileSelection;
          if (skip && activeId && stored.some((p) => p.id === activeId && !p.isLocked)) {
            setView("app");
          } else {
            setView("profiles");
          }
        }
      } catch (err) {
        console.error("Failed to parse callback auth parameters", err);
      }
    }
  }, [settings.skipProfileSelection]);

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
    let lastRefreshAt = 0;
    const refreshOnReturn = () => {
      if (document.visibilityState !== "visible") return;
      const now = Date.now();
      if (now - lastRefreshAt < 2_000) return;
      lastRefreshAt = now;
      void refreshData();
    };
    window.addEventListener("focus", refreshOnReturn);
    document.addEventListener("visibilitychange", refreshOnReturn);
    return () => {
      window.removeEventListener("focus", refreshOnReturn);
      document.removeEventListener("visibilitychange", refreshOnReturn);
    };
  }, [cloudProfilesHydrated, refreshData, view]);

  useEffect(() => {
    saveStored(settingsKey, settings);
    savePlaylists(settings.iptvPlaylists);
    if (!authClient.session || !activeProfileId) { setSettingsSyncState("local"); return; }
    if (authClient.session && !cloudProfilesHydrated) return;
    // Skip the cloud push when settings + active profile still match what we
    // last synced from the cloud — otherwise every boot echoes the just-pulled
    // settings straight back, a wasted account-sync-push per user per session. A
    // genuine change (user toggled a setting, switched profile) differs from the
    // snapshot and still saves.
    const snapshot = JSON.stringify({ settings, activeProfileId });
    if (lastSyncedSettingsRef.current !== null && snapshot === lastSyncedSettingsRef.current) {
      setSettingsSyncState(hasPendingSettings(authClient) ? "pending" : "saved");
      return;
    }
    // Persist immediately; only the network flush is debounced.
    // Compare only this profile's acknowledged values, preserving other devices' edits.
    let baseline: AppSettings | null = null;
    try {
      if (lastSyncedSettingsRef.current) {
        const parsed = JSON.parse(lastSyncedSettingsRef.current) as { settings?: AppSettings; activeProfileId?: string | null };
        if (parsed.activeProfileId === activeProfileId && parsed.settings) baseline = parsed.settings;
      }
    } catch {
      baseline = null;
    }
    const accountId = authClient.session?.userId;
    const submitted = { settings, activeProfileId };
    setSettingsSyncState("pending");
    try { queueSettings(authClient, activeProfileId, settings, baseline); }
    catch (error) { setSettingsSyncState("error"); setToast(error instanceof Error ? error.message : "Could not save settings"); return; }
    const handle = setTimeout(() => {
      void flushSettingsOutbox(authClient).then(() => {
        if (authClient.session?.userId !== accountId || activeProfileIdRef.current !== activeProfileId) return;
        lastSyncedSettingsRef.current = JSON.stringify(submitted);
        setSettingsSyncState(hasPendingSettings(authClient) ? "pending" : "saved");
      }).catch(() => setSettingsSyncState("error"));
    }, 1200);
    return () => clearTimeout(handle);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [settings, activeProfileId, profiles, cloudProfilesHydrated]);

  useEffect(() => {
    const retry = () => {
      if (!hasPendingSettings(authClient)) return;
      setSettingsSyncState("pending");
      void flushSettingsOutbox(authClient).then(() => setSettingsSyncState("saved")).catch(() => setSettingsSyncState("error"));
    };
    window.addEventListener("online", retry);
    const timer = window.setInterval(retry, 30_000);
    return () => { window.removeEventListener("online", retry); window.clearInterval(timer); };
  }, []);

  useEffect(() => {
    saveStored(PROFILES_KEY, profiles);
    saveStored(ACTIVE_PROFILE_KEY, activeProfileId);
    // Stamp which account these cached profiles belong to (empty when signed
    // out), so another account on this browser won't inherit them.
    saveStored(PROFILES_OWNER_KEY, currentAccountEmail());
  }, [profiles, activeProfileId, auth]);

  // When signed in, pull the shared profiles from the same account_sync_state
  // payload Android writes to (cloud wins, matching replaceProfilesFromCloud).
  useEffect(() => {
    if (!authClient.session) {
      setCloudProfilesHydrated(true);
      return;
    }
    let cancelled = false;
    void pullCloudProfiles(authClient)
      .then((cloud) => {
        if (cancelled) return;
        if (cloud.profiles.length) {
          setProfiles(cloud.profiles);
          setAvatarImages(cloud.avatarImages);
          if (cloud.activeProfileId) {
            setActiveProfileId(cloud.activeProfileId);
            void refreshData(cloud.activeProfileId);
          } else if (cloud.profiles[0]) {
            setActiveProfileId(cloud.profiles[0].id);
            void refreshData(cloud.profiles[0].id);
          }
        } else {
          // New account with no cloud profiles yet. If the local profiles were
          // stamped for a DIFFERENT account, they leaked from a previous
          // session — replace them with one clean profile.
          if (!localProfilesMatchAccount()) {
            const fresh = [makeProfile("Profile 1", randomProfileColor(), 0)];
            setProfiles(fresh);
            setActiveProfileId(fresh[0].id);
            saveStored(PROFILES_OWNER_KEY, currentAccountEmail());
            void refreshData(fresh[0].id);
          } else {
            void refreshData(activeProfileId);
          }
        }
        setCloudProfilesHydrated(true);
      })
      .catch(() => {
        if (!cancelled) setCloudProfilesHydrated(true);
      });
    return () => {
      cancelled = true;
    };
  }, [activeProfileId, auth, refreshData]);

  useEffect(() => {
    let current = true;
    setSearchState(query.trim() ? "loading" : "idle");
    const handle = setTimeout(async () => {
      if (!query.trim()) {
        setResults([]);
        return;
      }
      try {
        const found = await searchMedia(query, settings.language);
        if (current) { setResults(found); setSearchState("idle"); }
      } catch { if (current) setSearchState("error"); }
    }, 260);
    return () => { current = false; clearTimeout(handle); };
  }, [query, settings.language]);

  const updateSettings = useCallback((patch: Partial<AppSettings>) => {
    setSettings((prev) => ({ ...prev, ...patch }));
  }, []);

  const isWatched = useCallback((item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) => (
    isMediaWatched(item, watchedKeys, seasonNumber, episodeNumber)
  ), [watchedKeys]);

  // Optimistic watched-mark: badge + drop from Continue Watching instantly,
  // before the Trakt round-trip returns (used by the external-playback prompt
  // and the details Mark Watched flow so the UI responds immediately).
  const markWatchedLocally = useCallback((item: { mediaType: MediaItem["mediaType"]; id: number; season?: number | null; episode?: number | null }, watched = true) => {
    const target = { ...item, seasonNumber: item.season ?? null, episodeNumber: item.episode ?? null } as unknown as MediaItem;
    const key = mediaWatchKey(target, item.season, item.episode);
    setWatchedKeys((prev) => {
      const next = new Set(prev);
      if (watched) next.add(key); else next.delete(key);
      return next;
    });
    if (watched) {
      setContinueWatching((prev) => prev.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`));
      setCategories((prev) => prev.map((cat) => cat.id === "continue_watching"
        ? { ...cat, items: cat.items.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`) }
        : cat).filter((cat) => cat.id !== "continue_watching" || cat.items.length));
    }
  }, []);

  // Merge a fresh addon-stream list in without dropping IPTV VOD sources that
  // were appended asynchronously (they carry addonId "iptv_xtream_vod").
  const mergeStreams = useCallback((incoming: StreamSource[]) => {
    setStreams((prev) => {
      // Preserve the supplemental sources injected in parallel (Xtream VOD and
      // home-server files) when the addon/debrid streams resolve.
      const extras = prev.filter(
        (source) =>
          source.addonId === "iptv_xtream_vod" ||
          source.addonId === "home_server" ||
          source.addonId === "telegram_native"
      );
      const seen = new Set(incoming.map((s) => s.url ?? s.source));
      return [...incoming, ...extras.filter((s) => !seen.has(s.url ?? s.source))];
    });
  }, []);

  // Look up the opened title in the Xtream VOD/series catalog and append any
  // match as a supplemental source (parity with the Android app).
  const appendVodSources = useCallback((item: MediaItem, season?: number, episode?: number) => {
    const generation = sourceGeneration.current;
    const playlists = settingsRef.current.iptvPlaylists;
    if (!playlists?.length) return Promise.resolve([] as StreamSource[]);
    return (async () => {
      try {
        const { findMovieVodSources, findEpisodeVodSource } = await import("./xtreamVod");
        const ua = settingsRef.current.customUserAgent;
        const sources = season && episode
          ? await findEpisodeVodSource(playlists, item, season, episode, ua)
          : await findMovieVodSources(playlists, item, ua);
        if (!sources.length || sourceGeneration.current !== generation) return [];
        setStreams((prev) => {
          const seen = new Set(prev.map((s) => s.url ?? s.source));
          const fresh = sources.filter((s) => s.url && !seen.has(s.url));
          return fresh.length ? [...prev, ...fresh] : prev;
        });
        return sources;
      } catch {
        // VOD is best-effort; addon sources are unaffected on failure.
        return [];
      }
    })();
  }, []);

  // Match the opened title on the user's home servers (Jellyfin/Emby/Plex) and
  // append any files found as sources — parity with the Android app, which
  // surfaces home-server media in the same source list as addons.
  const appendHomeServerSources = useCallback((item: MediaItem, season?: number, episode?: number) => {
    const generation = sourceGeneration.current;
    const servers = settingsRef.current.homeServers;
    if (!servers?.length) return Promise.resolve([] as StreamSource[]);
    return (async () => {
      try {
        const { resolveHomeServerMovieSources, resolveHomeServerEpisodeSources } = await import("./homeserver");
        const target = {
          title: item.title,
          year: item.year ? Number(item.year) || undefined : undefined,
          imdbId: item.imdbId ?? undefined,
          tmdbId: item.tmdbId ?? (item.id > 0 && !item.isHomeServer ? item.id : undefined)
        };
        const sources = season && episode
          ? await resolveHomeServerEpisodeSources(servers, target, season, episode)
          : await resolveHomeServerMovieSources(servers, target);
        if (!sources.length || sourceGeneration.current !== generation) return [];
        setStreams((prev) => {
          const seen = new Set(prev.map((s) => s.url ?? s.source));
          const fresh = sources.filter((s) => s.url && !seen.has(s.url));
          return fresh.length ? [...prev, ...fresh] : prev;
        });
        return sources;
      } catch {
        // Best-effort; addon/debrid sources are unaffected on failure.
        return [];
      }
    })();
  }, []);

  // Search the user's connected Telegram account for the opened title and append
  // any matching video files as sources — parity with the Android app, which
  // surfaces Telegram media in the same source list as addons.
  const appendTelegramSources = useCallback((item: MediaItem, season?: number, episode?: number) => {
    const generation = sourceGeneration.current;
    if (item.isHomeServer) return Promise.resolve([] as StreamSource[]);
    return (async () => {
      try {
        const { resolveTelegramSources, isConnected } = await import("./telegram");
        if (!isConnected()) return [];
        const sources = await resolveTelegramSources(item, season, episode, {
          language: settingsRef.current.language
        });
        if (!sources.length || sourceGeneration.current !== generation) return [];
        setStreams((prev) => {
          const seen = new Set(prev.map((s) => s.url ?? s.source));
          const fresh = sources.filter((s) => !seen.has(s.url ?? s.source));
          return fresh.length ? [...prev, ...fresh] : prev;
        });
        return sources;
      } catch {
        // Best-effort; addon/debrid sources are unaffected on failure.
        return [];
      }
    })();
  }, []);

  const openDetails = useCallback(async (item: MediaItem) => {
    const generation = ++sourceGeneration.current;
    const publish = (rows: StreamSource[]) => { if (sourceGeneration.current === generation) mergeStreams(rows); };
    setSelected(item);
    setSelectedEpisode(null);
    // Home-server items carry their own metadata + a direct stream URL — no TMDB.
    if (item.isHomeServer && !item.tmdbId) {
      setSelected(item);
      setStreams(item.homeServerUrl
        ? [{ source: item.title, addonName: "Home Server", quality: "Direct", size: "", url: item.homeServerUrl }]
        : []);
      setBusy("");
      return;
    }
    setBusy("Opening details");
    setStreams([]);
    const priorityConfig = getPriorityConfig(settingsRef.current);
    const resolvedId = item.id > 0 ? item.id : await resolveTmdbId(item).catch(() => null);
    if (sourceGeneration.current !== generation) return;
    if (!resolvedId && !item.isHomeServer) { setBusy(""); setToast("Metadata could not be matched for this title."); return; }
    const detailsTarget = item.isHomeServer && item.tmdbId ? { ...item, id: item.tmdbId } : { ...item, id: resolvedId ?? item.id };
    const detailed = await getDetails(detailsTarget, priorityConfig).catch(() => item);
    if (sourceGeneration.current !== generation) return;
    const withResumeEpisode = {
      ...detailed,
      ...(item.isHomeServer ? {
        isHomeServer: true,
        homeServerUrl: item.homeServerUrl,
        homeServerItemId: item.homeServerItemId,
        homeServerId: item.homeServerId,
        homeServerType: item.homeServerType,
        tmdbId: item.tmdbId,
        image: detailed.image || item.image,
        backdrop: detailed.backdrop || item.backdrop
      } : {}),
      seasonNumber: item.seasonNumber ?? detailed.seasonNumber ?? null,
      episodeNumber: item.episodeNumber ?? detailed.episodeNumber ?? null,
      episodeTitle: item.episodeTitle ?? detailed.episodeTitle ?? null
    };
    setSelected(withResumeEpisode);
    // Movies fetch sources immediately. Continue-watching TV entries already
    // know their episode, so load that episode's sources without an extra tap.
    if (item.mediaType === "movie") {
      setBusy("Finding sources");
      appendVodSources(withResumeEpisode);
      appendHomeServerSources(withResumeEpisode);
      appendTelegramSources(withResumeEpisode);
      const found = await getStreamsProgressive(addonsRef.current, withResumeEpisode, undefined, undefined, publish).catch(() => []);
      publish(found);
    } else if (withResumeEpisode.seasonNumber && withResumeEpisode.episodeNumber) {
      setSelectedEpisode({ season: withResumeEpisode.seasonNumber, episode: withResumeEpisode.episodeNumber });
      setBusy("Finding sources");
      appendVodSources(withResumeEpisode, withResumeEpisode.seasonNumber, withResumeEpisode.episodeNumber);
      appendHomeServerSources(withResumeEpisode, withResumeEpisode.seasonNumber, withResumeEpisode.episodeNumber);
      appendTelegramSources(withResumeEpisode, withResumeEpisode.seasonNumber, withResumeEpisode.episodeNumber);
      const found = await getStreamsProgressive(
        addonsRef.current,
        withResumeEpisode,
        withResumeEpisode.seasonNumber,
        withResumeEpisode.episodeNumber,
        publish
      ).catch(() => []);
      publish(found);
    }
    if (sourceGeneration.current === generation) setBusy("");
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const loadEpisodeStreams = useCallback(async (item: MediaItem, season: number, episode: number) => {
    const generation = ++sourceGeneration.current;
    const publish = (rows: StreamSource[]) => { if (sourceGeneration.current === generation) mergeStreams(rows); };
    setSelectedEpisode({ season, episode });
    setStreams([]);
    setBusy("Finding sources");

    appendVodSources(item, season, episode);
    appendHomeServerSources(item, season, episode);
    appendTelegramSources(item, season, episode);
    const found = await getStreamsProgressive(addonsRef.current, item, season, episode, publish).catch(() => []);
    publish(found);
    if (sourceGeneration.current === generation) setBusy("");
    return found;
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const closeDetails = useCallback(() => {
    sourceGeneration.current++;
    playbackGeneration.current++;
    setSelected(null);
    setSelectedEpisode(null);
    setStreams([]);
  }, []);

  const playStream = useCallback((stream: StreamSource, options: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean } = {}) => {
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    setActiveStream(null);
    const sameEpisode = selected?.mediaType === "movie" || (selected?.seasonNumber === selectedEpisode?.season && selected?.episodeNumber === selectedEpisode?.episode);
    if (stream.resumePositionSeconds === undefined && selected && sameEpisode && !selected.isWatched) {
      stream = { ...stream, resumePositionSeconds: selected.resumePositionSeconds };
    }
    const controller = new AbortController();
    playbackPreparation.current = controller;
    const generation = ++playbackGeneration.current;
    const profileId = activeProfileIdRef.current;
    const accountId = authClient.session?.userId;
    const isCurrent = () => generation === playbackGeneration.current && profileId === activeProfileIdRef.current && accountId === authClient.session?.userId;
    if (!stream.url) {
      setToast("This source has no direct stream URL yet.");
      return;
    }
    // Default external player (VLC / Infuse): hand the source straight to it,
    // resolving the debrid CDN URL first (external players can't follow the
    // torrentio redirect chain). A pending record lets ARVIO scrobble to Trakt
    // + save progress when the user returns. forceBrowser overrides (e.g. the
    // in-player source panel, which is already in the browser player).
    const preferredPlayer = settingsRef.current.defaultPlayer;
    if (!options.forceBrowser && !options.forceRemux && !options.forceTranscode && (preferredPlayer === "vlc" || preferredPlayer === "infuse")) {
      const externalItem = selected;
      const externalTitle = selected?.title ?? stream.source ?? "ARVIO stream";
      const preferredSub = settingsRef.current.defaultSubtitle;
      // The deep link MUST fire synchronously in the Play click — custom-scheme
      // navigation (vlc-x-callback://) is blocked after an await. Use the
      // prefetch-cached CDN url if present, otherwise the raw source (VLC
      // follows the redirect itself). Subtitles attach only if we already have
      // them from the panel prefetch on the stream.
      const cached = parseDebridStream(stream.url) ? cachedDebridDirectUrl(stream.url) : null;
      const target = cached ? { ...stream, url: cached, originalUrl: stream.url } : stream;
      setToast(
        preferredPlayer === "vlc" && externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : `Opening in ${preferredPlayer.toUpperCase()}...`
      );
      createPendingExternalPlayback({
        player: preferredPlayer,
        item: externalItem,
        stream: target,
        title: externalTitle,
        profileId: activeProfile?.id ?? null,
        season: selectedEpisode?.season ?? null,
        episode: selectedEpisode?.episode ?? null
      });
      openExternalPlayer(preferredPlayer, target, externalTitle, preferredSub);
      void trackPremiumEvent(authClient, "external_playback_requested", { player: preferredPlayer, playback_type: "vod" }, true);
      return;
    }
    setToast(stream.homeServer || options.forceTranscode ? "Preparing browser playback..." : null);
    const timeout = window.setTimeout(() => { controller.abort(); if (isCurrent()) setToast("The source did not respond. Please try again or choose another source."); }, 20000);
    void prepareBrowserStream(stream, settingsRef.current, { ...options, signal: controller.signal }).then((prepared) => {
      if (!isCurrent() || controller.signal.aborted) {
        void reportHomeServerPlayback(prepared, settingsRef.current, "stop").catch(() => undefined);
        return;
      }
      setActiveChannel(null);
      ownedPlayback.current = { stream: prepared, settings: settingsRef.current };
      setActiveStream(prepared);
    }).catch((error: unknown) => {
      if (isCurrent() && !controller.signal.aborted) setToast(error instanceof Error ? error.message : "Could not prepare this source.");
    }).finally(() => window.clearTimeout(timeout));
  }, [selected, activeProfile, selectedEpisode]);

  const advanceEpisode = useCallback(async (): Promise<boolean> => {
    if (!selected || selected.mediaType !== "tv" || !selectedEpisode) return false;
    const generation = ++sourceGeneration.current;
    const playback = ++playbackGeneration.current;
    const isCurrent = () => sourceGeneration.current === generation && playbackGeneration.current === playback;
    try {
      const next = nextLocalEpisode({ ...selected, timeRemainingLabel: "Up next", seasonNumber: selectedEpisode.season, episodeNumber: selectedEpisode.episode + 1 });
      if (!next?.seasonNumber || !next.episodeNumber) { setToast("You have reached the last available episode."); return false; }
      const episodes = await getSeasonEpisodes(selected.tmdbId ?? selected.id, next.seasonNumber);
      if (!isCurrent()) return false;
      const episode = episodes.find((item) => item.episodeNumber === next.episodeNumber);
      if (!episode || (episode.airDate && Date.parse(episode.airDate) > Date.now())) { setToast("The next episode is not available yet."); return false; }
      let started = false;
      const choose = (rows: StreamSource[]) => {
        if (!isCurrent() || started) return;
        const candidate = rows.find((row) => row.url && playbackPlan(row).route === "here");
        if (!candidate) return;
        started = true;
        setSelectedEpisode({ season: next.seasonNumber!, episode: next.episodeNumber! });
        setSelected((item) => item ? { ...item, seasonNumber: next.seasonNumber, episodeNumber: next.episodeNumber, episodeTitle: episode.name, episodeStill: episode.still } : item);
        setActiveStream(null);
        playStream({ ...candidate, autoSelect: true, resumePositionSeconds: 0 }, { forceBrowser: true });
      };
      setStreams([]);
      const publish = (rows: StreamSource[]) => { if (sourceGeneration.current === generation) { mergeStreams(rows); choose(rows); } };
      // All source types participate; one unavailable provider does not hold up another.
      await Promise.allSettled([
        getStreamsProgressive(addonsRef.current, selected, next.seasonNumber, next.episodeNumber, publish).then(publish),
        appendHomeServerSources(selected, next.seasonNumber, next.episodeNumber).then(choose),
        appendVodSources(selected, next.seasonNumber, next.episodeNumber).then(choose),
        appendTelegramSources(selected, next.seasonNumber, next.episodeNumber).then(choose)
      ]);
      if (!started && isCurrent()) setToast("No browser-playable source for the next episode. Choose a source manually.");
      return started;
    } catch {
      if (isCurrent()) setToast("Could not load the next episode. Please retry.");
      return false;
    }
  }, [selected, selectedEpisode, playStream, mergeStreams, appendHomeServerSources, appendVodSources, appendTelegramSources]);

  const playTrailer = useCallback(async (item: MediaItem) => {
    let url = item.trailerUrl ?? null;
    if (!url) {
      const priorityConfig = getPriorityConfig(settingsRef.current);
      const detailed = await getDetails(item, priorityConfig).catch(() => item);
      url = detailed.trailerUrl ?? null;
      setSelected((current) => current ?? detailed);
    }
    if (!url) {
      setToast("No trailer available for this title.");
      return;
    }
    // Trailers are YouTube page URLs — the built-in <video> player can't play
    // those. Open YouTube directly: the YouTube app on mobile (youtube.com/
    // youtu.be deep-links into it) or a new browser tab on desktop.
    if (typeof window !== "undefined") {
      const videoId = url.match(/(?:v=|youtu\.be\/|embed\/)([\w-]{11})/)?.[1];
      const target = videoId ? `https://www.youtube.com/watch?v=${videoId}` : url;
      const opened = window.open(target, "_blank", "noopener,noreferrer");
      if (!opened) window.location.href = target;
    }
  }, [setToast]);

  // Hand a live/catch-up stream to VLC/Infuse. Most IPTV is plain-HTTP (mixed
  // content in a secure page) and many providers block anything but a real
  // player, so an external player is the reliable path — it plays from the
  // user's own connection with no browser restrictions, at zero server cost.
  // Returns true when the handoff fired (so the caller skips the browser player).
  const openLiveExternally = useCallback((stream: StreamSource, title: string): boolean => {
    const player = settingsRef.current.defaultPlayer;
    if (player !== "vlc" && player !== "infuse") return false;
    setToast(
      player === "infuse"
        ? "Opening in Infuse..."
        : externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : "Opening in VLC..."
    );
    openExternalPlayer(player, stream, title, settingsRef.current.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player, playback_type: "live_or_catchup" }, true);
    return true;
  }, []);

  const recordChannelPlayback = useCallback((channel: IptvChannel) => {
    setSettings(current => ({ ...current, iptvTvSession: recordTvPlayback(current.iptvTvSession, channel) }));
  }, []);

  const playChannel = useCallback((channel: IptvChannel) => {
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    playbackGeneration.current++;
    const stream: StreamSource = {
      source: channel.name,
      addonName: "Live TV",
      quality: "Live",
      size: "",
      url: channel.streamUrl,
      description: channel.group,
      behaviorHints: { proxyHeaders: { request: channel.requestHeaders } }
    };
    recordChannelPlayback(channel);
    if (openLiveExternally(stream, channel.name)) return;
    setActiveChannel(channel);
    setActiveStream(stream);
  }, [openLiveExternally, recordChannelPlayback]);

  // Catch-up plays a finished programme from the panel's archive. It is a
  // seekable VOD stream (no activeChannel → scrubber works), but the player
  // still gives it the IPTV proxy ladder via the "Catch-up" addonName marker.
  const playCatchup = useCallback((channel: IptvChannel, program: IptvProgram) => {
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    playbackGeneration.current++;
    const url = buildXtreamCatchupUrl(settingsRef.current.iptvPlaylists, channel, program);
    if (!url) {
      setToast("Catch-up is not available for this channel.");
      return;
    }
    const title = `${channel.name} · ${program.title}`;
    const stream: StreamSource = {
      source: title,
      addonName: "Catch-up",
      quality: "Catch-up",
      size: "",
      url,
      description: channel.group,
      behaviorHints: { proxyHeaders: { request: channel.requestHeaders } }
    };
    if (openLiveExternally(stream, title)) return;
    setActiveChannel(null);
    setActiveStream(stream);
  }, [setToast, openLiveExternally]);

  const closePlayer = useCallback(() => {
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    playbackGeneration.current++;
    setActiveStream(null);
    setActiveChannel(null);
  }, []);

  const loadCatalogRow = useCallback((catalog: CatalogConfig) => loadCatalog(catalog, settings.language, addonsRef.current, settingsRef.current.homeServers), [settings.language]);

  const installAddon = useCallback(async (url: string) => {
    const addon = await installAddonManifest(url);
    const next = [addon, ...addonsRef.current.filter((candidate) => candidate.id !== addon.id)];
    await persistAddons(next);
  }, [persistAddons]);

  const removeAddon = useCallback(async (addon: InstalledAddon) => {
    const next = addonsRef.current.filter((candidate) => candidate.id !== addon.id);
    await persistAddons(next, { removedIds: [addon.id] });
  }, [persistAddons]);

  const setAddonsState = useCallback(async (next: InstalledAddon[]) => {
    await persistAddons(next);
    setSettings((prev) => ({
      ...prev,
      disabledAddonIds: next.filter((addon) => addon.enabled === false).map((addon) => addon.id)
    }));
  }, [persistAddons]);

  const signIn = useCallback(async (email: string, password: string, mode: "sign-in" | "sign-up") => {
    const trimmedEmail = email.trim();
    if (!trimmedEmail || !password) throw new Error("Enter your email and password.");
    setBusy(mode === "sign-up" ? "Creating account" : "Signing in");
    setToast(null);
    try {
      const previousEmail = currentAccountEmail();
      const session = mode === "sign-up" ? await authClient.signUp(trimmedEmail, password) : await authClient.signIn(trimmedEmail, password);
      // Signing into a DIFFERENT account on this browser: drop the previous
      // account's in-memory + cached profiles so they don't show through while
      // the new account's cloud profiles load. A brand-new account then starts
      // with one clean profile instead of inheriting the old ones.
      if (previousEmail && previousEmail !== trimmedEmail.toLowerCase()) {
        const fresh = [makeProfile("Profile 1", randomProfileColor(), 0)];
        setProfiles(fresh);
        setActiveProfileId(fresh[0].id);
        setContinueWatching([]);
        setWatchlist([]);
        setCategories([]);
        setWatchedKeys(new Set());
        cwSourceRef.current = "none";
      }
      setCloudProfilesHydrated(false);
      setAuth(session);
    } catch (error) {
      const message = error instanceof Error ? error.message : "Authentication failed.";
      setToast(message);
      throw error;
    } finally {
      setBusy("");
    }
  }, [refreshData, activeProfileId]);

  const signOut = useCallback(() => {
    authClient.signOut();
    setAuth(null);
    setCloudProfilesHydrated(true);
    setToast("Signed out of ARVIO Cloud.");
  }, []);

  const beginTrakt = useCallback(async () => {
    traktClient.setProfile(activeProfileIdRef.current);
    setDeviceCode(await traktClient.beginDeviceLink());
  }, []);

  const pollTrakt = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    const code = deviceCodeRef.current;
    if (!code) return;
    const token = await traktClient.pollDeviceToken(code.device_code);
    if (activeProfileIdRef.current !== targetProfileId) return;
    traktClient.setProfile(targetProfileId);
    mdblistClient.setProfile(targetProfileId);
    simklClient.setProfile(targetProfileId);
    traktClient.setToken(token);
    setTraktConnected(true);
    setDeviceCode(null);
    const previousPreferences = loadTrackingPreferences(targetProfileId);
    const upgradeMode = (current: TrackingPreferences["watchlistReadMode"]) =>
      current === "auto" ? "trakt" as const : current;
    const nextPreferences: TrackingPreferences = {
      watchlistReadMode: upgradeMode(previousPreferences.watchlistReadMode),
      continueWatchingReadMode: upgradeMode(previousPreferences.continueWatchingReadMode),
      watchedReadMode: upgradeMode(previousPreferences.watchedReadMode),
      writeToTrakt: true,
      writeToSimkl: simklClient.isConnected && previousPreferences.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: "TRAKT",
        traktToken: token,
        mdbListApiKey: mdblistClient.key,
        simklToken: simklClient.token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "trakt"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const disconnectTrakt = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    traktClient.setProfile(targetProfileId);
    traktClient.disconnect();
    setTraktConnected(false);
    const current = loadTrackingPreferences(targetProfileId);
    const fallback = simklClient.isConnected ? "simkl" as const : mdblistClient.isConnected ? "mdblist" as const : "auto" as const;
    const replaceTrakt = (mode: typeof current.watchlistReadMode) =>
      mode === "trakt" || mode === "both" ? fallback : mode;
    const nextPreferences: TrackingPreferences = {
      watchlistReadMode: replaceTrakt(current.watchlistReadMode),
      continueWatchingReadMode: replaceTrakt(current.continueWatchingReadMode),
      watchedReadMode: replaceTrakt(current.watchedReadMode),
      writeToTrakt: false,
      writeToSimkl: simklClient.isConnected && current.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: simklClient.isConnected ? "SIMKL" : mdblistClient.isConnected ? "MDBLIST" : "NONE",
        traktToken: null,
        mdbListApiKey: mdblistClient.key,
        simklToken: simklClient.token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "trakt"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const connectMdblist = useCallback(async (key: string) => {
    const targetProfileId = activeProfileIdRef.current;
    const ok = await mdblistClient.validateKey(key);
    if (!ok) throw new Error("Invalid MDBList API key");
    if (activeProfileIdRef.current !== targetProfileId) return;
    traktClient.setProfile(targetProfileId);
    mdblistClient.setProfile(targetProfileId);
    simklClient.setProfile(targetProfileId);
    mdblistClient.setKey(key);
    setMdblistConnected(true);
    const currentPreferences = loadTrackingPreferences(targetProfileId);
    const hasPrimaryTracker = traktClient.isConnected || simklClient.isConnected;
    const mdbPreferences: TrackingPreferences = {
      watchlistReadMode: hasPrimaryTracker ? currentPreferences.watchlistReadMode : "mdblist",
      continueWatchingReadMode: hasPrimaryTracker ? currentPreferences.continueWatchingReadMode : "mdblist",
      watchedReadMode: hasPrimaryTracker ? currentPreferences.watchedReadMode : "mdblist",
      writeToTrakt: traktClient.isConnected && currentPreferences.writeToTrakt,
      writeToSimkl: simklClient.isConnected && currentPreferences.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, mdbPreferences);
    setTrackingPreferences(mdbPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : simklClient.isConnected ? "SIMKL" : "MDBLIST",
        traktToken: traktClient.token,
        mdbListApiKey: key,
        simklToken: simklClient.token,
        trackingPreferences: mdbPreferences,
        changedDomains: ["routing", "mdblist"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const disconnectMdblist = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    mdblistClient.setProfile(targetProfileId);
    mdblistClient.disconnect();
    setMdblistConnected(false);
    const current = loadTrackingPreferences(targetProfileId);
    const fallback = traktClient.isConnected
        ? "trakt" as const
        : simklClient.isConnected
          ? "simkl" as const
          : "auto" as const;
    const replaceMdbList = (mode: TrackingPreferences["watchlistReadMode"]) => mode === "mdblist" ? fallback : mode;
    const nextPreferences: TrackingPreferences = {
      watchlistReadMode: replaceMdbList(current.watchlistReadMode),
      continueWatchingReadMode: replaceMdbList(current.continueWatchingReadMode),
      watchedReadMode: replaceMdbList(current.watchedReadMode),
      writeToTrakt: traktClient.isConnected && current.writeToTrakt,
      writeToSimkl: simklClient.isConnected && current.writeToSimkl
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : simklClient.isConnected ? "SIMKL" : "NONE",
        traktToken: traktClient.token,
        mdbListApiKey: null,
        simklToken: simklClient.token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "mdblist"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const beginSimkl = useCallback(async () => {
    simklClient.setProfile(activeProfileIdRef.current);
    setSimklDeviceCode(await simklClient.beginPinAuth());
  }, []);

  const pollSimkl = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    const code = simklDeviceCodeRef.current;
    if (!code) return;
    const token = await simklClient.pollPinToken(code.user_code);
    if (!token) {
      throw new Error("Simkl has not approved this PIN yet. Please approve the code on Simkl.");
    }
    if (activeProfileIdRef.current !== targetProfileId) return;
    traktClient.setProfile(targetProfileId);
    mdblistClient.setProfile(targetProfileId);
    simklClient.setProfile(targetProfileId);
    simklClient.setToken(token);
    setSimklConnected(true);
    setSimklDeviceCode(null);
    const previousPreferences = loadTrackingPreferences(targetProfileId);
    const mode = traktClient.isConnected ? "trakt" as const : "simkl" as const;
    const upgradeMode = (current: TrackingPreferences["watchlistReadMode"]) =>
      current === "auto" ? mode : current;
    const nextPreferences: TrackingPreferences = {
      watchlistReadMode: upgradeMode(previousPreferences.watchlistReadMode),
      continueWatchingReadMode: upgradeMode(previousPreferences.continueWatchingReadMode),
      watchedReadMode: upgradeMode(previousPreferences.watchedReadMode),
      writeToTrakt: traktClient.isConnected && previousPreferences.writeToTrakt,
      writeToSimkl: true
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : "SIMKL",
        traktToken: traktClient.token,
        mdbListApiKey: mdblistClient.key,
        simklToken: token,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "simkl"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const disconnectSimkl = useCallback(async () => {
    const targetProfileId = activeProfileIdRef.current;
    simklClient.setProfile(targetProfileId);
    simklClient.disconnect();
    setSimklConnected(false);
    const current = loadTrackingPreferences(targetProfileId);
    const fallback = traktClient.isConnected ? "trakt" as const : mdblistClient.isConnected ? "mdblist" as const : "auto" as const;
    const replaceSimkl = (mode: typeof current.watchlistReadMode) =>
      mode === "simkl" || mode === "both" ? fallback : mode;
    const nextPreferences: TrackingPreferences = {
      watchlistReadMode: replaceSimkl(current.watchlistReadMode),
      continueWatchingReadMode: replaceSimkl(current.continueWatchingReadMode),
      watchedReadMode: replaceSimkl(current.watchedReadMode),
      writeToTrakt: traktClient.isConnected && current.writeToTrakt,
      writeToSimkl: false
    };
    if (targetProfileId) saveTrackingPreferences(targetProfileId, nextPreferences);
    setTrackingPreferences(nextPreferences);
    if (targetProfileId) {
      await saveCloudTrackingSelection(authClient, targetProfileId, {
        provider: traktClient.isConnected ? "TRAKT" : mdblistClient.isConnected ? "MDBLIST" : "NONE",
        traktToken: traktClient.token,
        mdbListApiKey: mdblistClient.key,
        simklToken: null,
        trackingPreferences: nextPreferences,
        changedDomains: ["routing", "simkl"]
      }).catch(() => undefined);
    }
    if (activeProfileIdRef.current === targetProfileId) {
      await refreshData(targetProfileId);
    }
  }, [refreshData]);

  const updateTrackingPreferences = useCallback(async (patch: Partial<TrackingPreferences>) => {
    const profileId = activeProfileIdRef.current;
    if (!profileId) return;
    const next = saveTrackingPreferences(profileId, {
      ...loadTrackingPreferences(profileId),
      ...patch
    });
    setTrackingPreferences(next);
    await saveCloudTrackingSelection(authClient, profileId, {
      provider: traktClient.isConnected
        ? "TRAKT"
        : simklClient.isConnected
          ? "SIMKL"
          : mdblistClient.isConnected
            ? "MDBLIST"
            : "NONE",
      traktToken: traktClient.token,
      mdbListApiKey: mdblistClient.key,
      simklToken: simklClient.token,
      trackingPreferences: next,
      changedDomains: ["routing"]
    }).catch(() => undefined);
    await refreshData(profileId);
  }, [refreshData]);

  // Watchlist list-source switcher. Returns the user's custom Trakt lists to
  // populate the dropdown (built-in Watchlist/Collection are added by the UI).
  const loadTraktLists = useCallback(async (): Promise<Array<{ id: string; name: string }>> => {
    if (!traktClient.isConnected) return [];
    const lists = await traktClient.userLists();
    return (lists as Array<Record<string, unknown>>)
      .map((list) => {
        const ids = (list.ids ?? {}) as Record<string, unknown>;
        const id = String(ids.trakt ?? ids.slug ?? "");
        const name = String(list.name ?? "").trim();
        return id && name ? { id, name } : null;
      })
      .filter((v): v is { id: string; name: string } => Boolean(v));
  }, []);

  // Fetch + hydrate a chosen list source into MediaItems. `source` is one of:
  // "watchlist", "collection", or "list:<id>".
  const fetchTraktListItems = useCallback(async (source: string, cacheKey: string): Promise<MediaItem[]> => {
    let rows: unknown[] = [];
    if (source === "collection") {
      const [movies, shows] = await Promise.all([
        traktClient.collection("movies"),
        traktClient.collection("shows")
      ]);
      rows = [...(movies as unknown[]), ...(shows as unknown[])];
    } else if (source.startsWith("list:")) {
      rows = await traktClient.listItems(source.slice(5));
    } else {
      rows = await traktClient.watchlist();
    }
    const hydrated = await hydrateTraktItems(rows.map(traktItemToMedia));
    saveCachedList(cacheKey, hydrated, 200);
    return hydrated;
  }, []);

  // Returns cached items instantly (with a background refresh) when fresh, else
  // fetches. Cached per-source for instant re-selection.
  const loadTraktListItems = useCallback(async (source: string): Promise<MediaItem[]> => {
    if (!traktClient.isConnected) return [];
    const cacheKey = `arvio.web.traktlist.v2:${authClient.session?.userId ?? "local"}:${traktClient.currentProfileId}:${source}`;
    try {
      const cached = loadStored<{ at: number; items: MediaItem[] } | null>(cacheKey, null);
      if (cached?.items?.length && Date.now() - cached.at < 30 * 60 * 1000) {
        void fetchTraktListItems(source, cacheKey).catch(() => undefined);
        return cached.items;
      }
    } catch {
      // ignore cache errors
    }
    return fetchTraktListItems(source, cacheKey);
  }, [fetchTraktListItems]);

  const loadTrackerLibrary = useCallback(async (provider: "trakt" | "simkl", source: string): Promise<MediaItem[]> => {
    if (provider === "trakt") {
      if (!traktClient.isConnected) throw new Error("Connect Trakt in Settings.");
      if (source === "watched") {
        const [movies, shows] = await Promise.all([traktClient.watched("movies"), traktClient.watched("shows")]);
        return hydrateTraktItems(dedupeMedia([...(movies as unknown[]), ...(shows as unknown[])].map(traktItemToMedia)));
      }
      return fetchTraktListItems(source, `arvio.web.traktlist.v2:${authClient.session?.userId ?? "local"}:${traktClient.currentProfileId}:${source}`);
    }
    if (!simklClient.isConnected) throw new Error("Connect Simkl in Settings.");
    if (!["plantowatch", "watching", "completed", "hold", "dropped"].includes(source)) throw new Error("Unknown Simkl library");
    const rows = await simklClient.library(source as "plantowatch" | "watching" | "completed" | "hold" | "dropped");
    return hydrateTraktItems(dedupeMedia(rows.map(traktItemToMedia)));
  }, [fetchTraktListItems]);

  const persistProfiles = useCallback((next: Profile[], activeId: string | null) => {
    activeProfileIdRef.current = activeId;
    setProfiles(next);
    setActiveProfileId(activeId);
    saveStored(PROFILES_KEY, next);
    saveStored(ACTIVE_PROFILE_KEY, activeId);
    void saveCloudProfiles(authClient, next, activeId).catch(() => undefined);
  }, []);

  const selectProfile = useCallback(async (profile: Profile, pin?: string) => {
    if (profile.isLocked && profile.pin && !await verifyProfilePin(pin ?? "", profile.pin)) {
      setToast("Unlock this profile with its PIN.");
      return;
    }
    const updated = profiles.map((p) => (p.id === profile.id ? { ...p, lastUsedAt: Date.now() } : p));
    const switching = profile.id !== activeProfileId;
    persistProfiles(updated, profile.id);
    traktClient.setProfile(profile.id);
    mdblistClient.setProfile(profile.id);
    simklClient.setProfile(profile.id);
    setTraktConnected(traktClient.isConnected);
    setMdblistConnected(mdblistClient.isConnected);
    setSimklConnected(simklClient.isConnected);
    setManageMode(false);
    if (switching) {
      setDeviceCode(null);
      setSimklDeviceCode(null);
      // Drop the previous profile's rows and seed from the new profile's cache
      // so its Continue Watching paints instantly. refreshKeyRef (updated by the
      // refreshData call below) invalidates any in-flight refresh for the old
      // profile before it can write its rows over these.
      const seededCw = readCachedList(cwCacheKeyFor(profile.id));
      cwSourceRef.current = seededCw.length ? "seed" : "none";
      setContinueWatching(seededCw);
      setCategories(seededCw.length ? [{ id: "continue_watching", title: "Continue Watching", items: seededCw }] : []);
      setWatchlist(readCachedList(watchlistCacheKeyFor(profile.id)));
      setWatchedKeys(new Set());
    }
    setView("app");
    setSection("home");
    void refreshData(profile.id);
  }, [profiles, activeProfileId, persistProfiles, refreshData]);

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

  const goToLogin = useCallback(() => {
    if (config.selfHosted) { setView("profiles"); return; }
    if (typeof window !== "undefined") {
      const redirectUri = window.location.origin + "/";
      const portalUrl = getAuthPortalUrl();
      window.location.href = `${portalUrl}?redirect_uri=${encodeURIComponent(redirectUri)}`;
    }
  }, []);
  const backToProfiles = useCallback(() => setView("profiles"), []);

  const [activeContextMenu, setActiveContextMenu] = useState<ContextMenuTarget | null>(null);

  const openContextMenu = useCallback((target: ContextMenuTarget) => {
    setActiveContextMenu(target);
  }, []);

  const closeContextMenu = useCallback(() => {
    setActiveContextMenu(null);
  }, []);

  const toggleWatchlist = useCallback(async (item: MediaItem) => {
    const inWatchlist = watchlist.some((entry) => entry.mediaType === item.mediaType && entry.id === item.id);
    if (activeSyncProvider() === "none") {
      setToast("Connect Trakt, Simkl, or MDBList in Settings to use Watchlist.");
      return;
    }
    const slim = slimCacheItem(item);
    const cacheKey = watchlistCacheKeyFor(activeProfileId);
    const nextWatchlist = inWatchlist
      ? watchlist.filter((entry) => !(entry.mediaType === item.mediaType && entry.id === item.id))
      : [slim, ...watchlist];
    setWatchlist(nextWatchlist);
    saveCachedList(cacheKey, nextWatchlist, 60);

    try {
      const ref = {
        mediaType: item.mediaType,
        tmdbId: item.id,
        isAnime: item.mediaType === "tv" && item.originalLanguage === "ja" && Boolean(item.genreIds?.includes(16))
      };
      if (inWatchlist) {
        await syncClient().removeFromWatchlist(ref);
        setToast("Removed from watchlist.");
      } else {
        await syncClient().addToWatchlist(ref);
        setToast("Added to watchlist.");
      }
      if (authClient.session) {
        await saveCloudWatchlist(authClient, nextWatchlist, activeProfileId).catch(() => undefined);
      }
    } catch (err) {
      setWatchlist((prev) => {
        const next = inWatchlist ? [slim, ...prev] : prev.filter((entry) => !(entry.mediaType === item.mediaType && entry.id === item.id));
        saveCachedList(cacheKey, next, 60);
        return next;
      });
      setToast(err instanceof Error ? err.message : "Failed to update watchlist.");
    }
  }, [watchlist, activeProfileId, authClient]);

  const toggleWatched = useCallback(async (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null, skipSync?: boolean) => {
    const currentlyWatched = isWatched(item, seasonNumber, episodeNumber);
    markWatchedLocally({ mediaType: item.mediaType, id: item.id, season: seasonNumber, episode: episodeNumber }, !currentlyWatched);
    setToast(!currentlyWatched ? "Marked as watched." : "Marked as unwatched.");

    if (authClient.session) {
      try {
        await saveWatchedState(authClient, {
          id: item.id,
          mediaType: item.mediaType,
          seasonNumber: seasonNumber ?? item.seasonNumber ?? null,
          episodeNumber: episodeNumber ?? item.episodeNumber ?? null
        }, !currentlyWatched, activeProfileId);
      } catch {
        // Cloud sync best effort
      }
    }

    if (!skipSync && activeSyncProvider() !== "none") {
      try {
        const ref = {
          mediaType: item.mediaType,
          tmdbId: item.id,
          season: typeof seasonNumber === "number" ? seasonNumber : item.seasonNumber ?? undefined,
          episode: typeof episodeNumber === "number" ? episodeNumber : item.episodeNumber ?? undefined,
          isAnime: item.mediaType === "tv" && item.originalLanguage === "ja" && Boolean(item.genreIds?.includes(16))
        };
        if (!currentlyWatched) {
          await syncClient().addToHistory(ref);
        } else {
          await syncClient().removeFromHistory(ref);
        }
      } catch {
        // Sync best effort
      }
    }
  }, [isWatched, markWatchedLocally, authClient, activeProfileId]);

  const removeFromContinueWatching = useCallback(async (item: MediaItem) => {
    const key = mediaWatchKey(item);
    const cacheKey = cwCacheKeyFor(activeProfileId);

    setContinueWatching((prev) => {
      const next = prev.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`);
      saveCachedList(cacheKey, next, 30);
      return next;
    });

    setCategories((prev) => prev.map((cat) => cat.id === "continue_watching"
      ? { ...cat, items: cat.items.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`) }
      : cat).filter((cat) => cat.id !== "continue_watching" || cat.items.length));

    setToast("Removed from Continue Watching.");

    if (authClient.session) {
      try {
        await removeContinueWatchingProgress(authClient, item, activeProfileId);
      } catch {
        // Cloud sync best effort
      }
    }

    if (activeSyncProvider() !== "none") {
      try {
        await syncClient().dismissFromContinueWatching({ mediaType: item.mediaType, tmdbId: item.id, season: item.seasonNumber, episode: item.episodeNumber });
      } catch {
        // Sync best effort
      }
    }
  }, [activeProfileId, authClient]);

  const value = useMemo<AppStore>(() => ({
    view,
    cloudLoginRequired,
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
    isWatched,
    markWatchedLocally,
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
    addonsReady,
    iptvSnapshot,
    query,
    setQuery,
    results,
    searchState,
    settingsSyncState,
    settings,
    setSettings,
    updateSettings,
    auth,
    traktConnected,
    mdblistConnected,
    simklConnected,
    trackingPreferences,
    updateTrackingPreferences,
    deviceCode,
    simklDeviceCode,
    busy,
    toast,
    setToast,
    refreshData,
    refreshIptv,
    loadIptvGuide,
    openDetails,
    closeDetails,
    playStream,
    playTrailer,
    playChannel,
    recordChannelPlayback,
    playCatchup,
    closePlayer,
    installAddon,
    removeAddon,
    setAddonsState,
    signIn,
    signOut,
    beginTrakt,
    pollTrakt,
    disconnectTrakt,
    connectMdblist,
    disconnectMdblist,
    beginSimkl,
    pollSimkl,
    disconnectSimkl,
    loadTraktLists,
    loadTraktListItems,
    loadTrackerLibrary,
    toggleWatchlist,
    toggleWatched,
    removeFromContinueWatching,
    activeContextMenu,
    openContextMenu,
    closeContextMenu
  }), [
    view, cloudLoginRequired, profiles, activeProfile, avatarImages, manageMode,
    selectProfile, createProfile, updateProfileAction, deleteProfileAction, switchProfile, goToLogin, backToProfiles,
    section, categories, catalogConfigs, loadCatalogRow, homeServerRows, continueWatching, watchlist, isWatched, hero, heroPreview, selected, streams, selectedEpisode, loadEpisodeStreams, advanceEpisode, activeStream, activeChannel,
    addons, addonsReady, iptvSnapshot, query, results, searchState, settingsSyncState, settings, auth, traktConnected, mdblistConnected, simklConnected, trackingPreferences, deviceCode, simklDeviceCode, busy, toast,
    updateSettings, refreshData, openDetails, closeDetails, playStream, playTrailer, playChannel, recordChannelPlayback, playCatchup, closePlayer,
    refreshIptv, loadIptvGuide,
    installAddon, removeAddon, setAddonsState, signIn, signOut, beginTrakt, pollTrakt, disconnectTrakt,
    connectMdblist, disconnectMdblist, beginSimkl, pollSimkl, disconnectSimkl, updateTrackingPreferences,
    loadTraktLists, loadTraktListItems, loadTrackerLibrary,
    toggleWatchlist, toggleWatched, removeFromContinueWatching, activeContextMenu, openContextMenu, closeContextMenu
  ]);

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
}

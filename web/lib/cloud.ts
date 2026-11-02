import { repairIptvPlaylistIds } from "./iptvPlaylistIdentity";
import type { AuthClient } from "./auth";
import { mergeAddonChanges, recordAddonChanges, type AddonChanges } from "./addonChanges";
import { mergeWatchlistChanges, mergeWatchlistItems, watchlistChangesStorageKey, watchlistKey, type CloudWatchlistItem as AndroidWatchlistItem, type WatchlistChanges } from "./watchlistChanges";
import { loadStored, saveStored } from "./storage";
import { config, hasNetlifyBackendUrl } from "./config";
import { parseHomeServerConnectionJson, serializeHomeServerConnectionJson } from "./homeserver";
import { jsonRequest } from "./http";
import { findNextEpisode, type SeasonEpisodesLookup } from "./nextEpisode";
import { discardPendingProgress, flushProgressOutbox, installProgressRetry, pendingProgress, queueProgress, type ProgressItem, type ProgressOperation, type ProgressSendResult } from "./progressOutbox";
import { mergeTvSessions, normalizeTvSession } from "./iptvSession";
import { normalizeIptvPlaylist as normalizeRuntimeIptvPlaylist } from "./iptv";
import { normalizeGroupNames } from "./iptvGroups";
import { legacyStalkerFields, mergeStalkerPortalEdits, migrateIptvCloudState, migrateLegacyStalkerChannelIds, migrateLegacyStalkerKeys, normalizeStalkerPortals, portalsFromCloud } from "./stalkerPortals";
import type { StalkerPortalEntry } from "./types";
import { tmdbImageUrl } from "./mediaImages";
import { applyRowLayoutModes, normalizedRowLayoutModes } from "./homeRows";
import type { TraktToken } from "./trakt";
import { isYouTubeItem } from "./youtubeCollection";
import type { SimklToken } from "./simkl";
import type { TrackingPreferences, TrackingReadMode } from "./sync";
import type { AppSettings, InstalledAddon, IptvPlaylistEntry, MediaItem, Profile, QualityFilterConfig, WatchHistoryEntry } from "./types";

export interface CloudPayload {
  version: number;
  addons: InstalledAddon[];
  settings?: Partial<AppSettings>;
  updatedAt: number;
}

type RawPayload = Record<string, unknown>;

interface AccountSyncPullResponse {
  payload?: RawPayload | string | null;
  source?: string | null;
  updatedAt?: string | null;
}

interface AndroidContinueWatchingItem {
  addonNativeId?: string | null;
  addonNativeAddonId?: string | null;
  id?: number;
  title?: string;
  mediaType?: string;
  progress?: number;
  resumePositionSeconds?: number;
  durationSeconds?: number;
  season?: number | null;
  episode?: number | null;
  displaySeason?: number | null;
  displayEpisode?: number | null;
  episodeTitle?: string | null;
  backdropPath?: string | null;
  episodeStillPath?: string | null;
  posterPath?: string | null;
  streamKey?: string | null;
  streamAddonId?: string | null;
  streamTitle?: string | null;
  isUpNext?: boolean;
  releaseDate?: string | null;
  updatedAtMs?: number;
}

interface AndroidIptvProfileState {
  lockedGroups?: string[];
  m3uUrl?: string;
  epgUrl?: string;
  playlists?: IptvPlaylistEntry[];
  stalkerPortalUrl?: string;
  stalkerMacAddress?: string;
  /** Android's portal list (StalkerPortalEntry[]); authoritative when it has entries. */
  stalkerPortals?: unknown[];
  favoriteChannels?: string[];
  favoriteGroups?: string[];
  hiddenGroups?: string[];
  groupOrder?: string[];
  groupOrderSchema?: number;
  sortOrder?: string;
  /** { "<playlistId>|<group>": [channel ids…] } — manual channel order inside a category. */
  channelOrder?: Record<string, string[]>;
  /** { "<playlistId>|<group>": "<display name>" } — category names set by the user. */
  groupNames?: Record<string, string>;
}

function canUseBackendSync(auth: AuthClient) {
  // Account sync authenticates with the signed-in user's bearer token. The
  // public app key is only needed to create/refresh a password session, so it
  // must not disable cloud pulls for sessions returned by auth.arvio.tv.
  return Boolean(auth.session?.accessToken && auth.isNetlifySession && hasNetlifyBackendUrl());
}

async function backendRequest<T>(auth: AuthClient, path: string, init: RequestInit = {}) {
  const token = await auth.accessToken();
  return jsonRequest<T>(`${config.netlifyBackendUrl.replace(/\/+$/, "")}/${path.replace(/^\/+/, "")}`, {
    ...init,
    headers: {
      Authorization: `Bearer ${token}`,
      ...(init.headers ?? {})
    }
  });
}

function parsePayload(payload: RawPayload | string | null | undefined): RawPayload {
  if (!payload) return {};
  if (typeof payload === "string") {
    try {
      return (JSON.parse(payload) as RawPayload) ?? {};
    } catch {
      return {};
    }
  }
  return payload;
}

function parseNestedJson(value: unknown): unknown {
  if (typeof value !== "string") return value;
  const trimmed = value.trim();
  if (!trimmed || (!trimmed.startsWith("{") && !trimmed.startsWith("["))) return value;
  try {
    return JSON.parse(trimmed) as unknown;
  } catch {
    return value;
  }
}

function objectRecord<T = unknown>(value: unknown): Record<string, T> {
  const parsed = parseNestedJson(value);
  return parsed && typeof parsed === "object" && !Array.isArray(parsed) ? parsed as Record<string, T> : {};
}

function arrayValue<T = unknown>(value: unknown): T[] {
  const parsed = parseNestedJson(value);
  return Array.isArray(parsed) ? parsed as T[] : [];
}

function parseDismissedContinueWatching(value: unknown): Map<string, number> {
  const result = new Map<string, number>();
  const raw = typeof value === "string" ? value.trim() : "";
  if (!raw) return result;
  raw.split("|").forEach((entry) => {
    const split = entry.lastIndexOf(",");
    if (split <= 0) return;
    const key = entry.slice(0, split);
    const timestamp = Number(entry.slice(split + 1));
    if (key && Number.isFinite(timestamp)) result.set(key, timestamp);
  });
  return result;
}

function encodeDismissedContinueWatching(values: Map<string, number>): string {
  return [...values.entries()].map(([key, timestamp]) => `${key},${timestamp}`).join("|");
}

function continueWatchingDismissalKeys(item: {
  id: number;
  mediaType: MediaItem["mediaType"];
  seasonNumber?: number | null;
  episodeNumber?: number | null;
}) {
  const showKey = item.mediaType === "movie" ? `movie:${item.id}` : `tv:${item.id}`;
  const exactKey = item.mediaType === "tv" && item.seasonNumber != null && item.episodeNumber != null
    ? `${showKey}:${item.seasonNumber}:${item.episodeNumber}`
    : showKey;
  return { showKey, exactKey };
}

function updateProfileDismissals(
  root: RawPayload,
  profileId: string,
  update: (values: Map<string, number>) => void
) {
  const byProfile = objectRecord<unknown>(root.dismissedContinueWatchingByProfile);
  const values = parseDismissedContinueWatching(byProfile[profileId]);
  update(values);
  const encoded = encodeDismissedContinueWatching(values);
  if (encoded) byProfile[profileId] = encoded;
  else delete byProfile[profileId];
  root.dismissedContinueWatchingByProfile = byProfile;
}

function scopedValue<T>(root: RawPayload, key: string, profileId?: string | null): T | undefined {
  if (!profileId) return undefined;
  const byProfile = objectRecord<T>(root[key]);
  return parseNestedJson(byProfile[profileId]) as T | undefined;
}

function setScopedValue<T>(root: RawPayload, key: string, profileId: string, value: T) {
  const byProfile = objectRecord<T>(root[key]);
  byProfile[profileId] = value;
  root[key] = byProfile;
}

// ── Per-field last-writer-wins support (multi-device conflict resolution) ──
// Mirrors the Android CloudSyncRepository scheme: a scalar setting carries a per-field timestamp
// under `fieldUpdatedAt` ("g:accentColor", "p:<profileId>:autoPlayNext"). Only the fields this web
// session actually changed get their timestamp bumped, so the web can't revert a phone's newer
// change and its own changes are respected by the Android merge.
function bumpFieldTs(root: RawPayload, key: string, changedAt = Date.now()) {
  const map = (root.fieldUpdatedAt && typeof root.fieldUpdatedAt === "object"
    ? root.fieldUpdatedAt
    : {}) as Record<string, number>;
  map[key] = changedAt;
  root.fieldUpdatedAt = map;
}

/** Value equality tolerant of object/array fields (avoids reference-inequality false positives). */
function sameFieldValue(a: unknown, b: unknown): boolean {
  if (a === b) return true;
  try {
    return JSON.stringify(a) === JSON.stringify(b);
  } catch {
    return false;
  }
}

function stringArray(value: unknown): string[] {
  return arrayValue(value).map((item) => String(item ?? "").trim()).filter(Boolean);
}

function stringValue(value: unknown): string {
  return typeof value === "string" ? value.trim() : "";
}

function normalizeIptvPlaylist(value: unknown, fallbackId: string): IptvPlaylistEntry | null {
  const raw = objectRecord(value);
  const xtreamTriplet = [raw.host ?? raw.server ?? raw.portal ?? raw.baseUrl, raw.username ?? raw.user, raw.password ?? raw.pass]
    .map((item) => stringValue(item))
    .filter(Boolean);
  const m3uUrl = stringValue(
    raw.m3uUrl ??
    raw.m3u_url ??
    raw.url ??
    raw.uri ??
    raw.playlistUrl ??
    raw.playlist_url ??
    raw.xtreamUrl ??
    raw.xtream_url
  ) || (xtreamTriplet.length >= 3 ? xtreamTriplet.join(" ") : "");
  if (!m3uUrl) return null;
  const id = stringValue(raw.id) || fallbackId;
  const name = stringValue(raw.name) || "Cloud IPTV";
  const epgUrl = stringValue(raw.epgUrl ?? raw.epg_url ?? raw.xmltvUrl ?? raw.xmltv_url);
  const normalized = normalizeRuntimeIptvPlaylist({
    id,
    name,
    m3uUrl,
    epgUrl,
    epgUrls: stringArray(raw.epgUrls ?? raw.epg_urls),
    enabled: raw.enabled !== false
  }, Number(fallbackId.match(/\d+$/)?.[0] ?? 1) - 1);
  if (!normalized) return null;
  // Android's per-playlist import switches. Carried through so a web edit of
  // the playlist list does not quietly turn VOD/series import back on.
  for (const flag of ["importLiveTv", "importVod", "importSeries"] as const) {
    if (typeof raw[flag] === "boolean") normalized[flag] = raw[flag] as boolean;
  }
  return normalized;
}

/** iptvByProfile.<id>.channelOrder → only string lists under `playlist|group` keys. */
export function normalizeChannelOrder(value: unknown): Record<string, string[]> {
  const result: Record<string, string[]> = {};
  for (const [key, refs] of Object.entries(objectRecord(value))) {
    if (!key.includes("|")) continue;
    const list = [...new Set(stringArray(refs))];
    if (list.length) result[key] = list;
  }
  return result;
}

function normalizeIptvPlaylists(value: unknown, fallbackPrefix: string): IptvPlaylistEntry[] {
  const values = arrayValue(value);
  return repairIptvPlaylistIds(values.length
    ? values
        .map((playlist, index) => normalizeIptvPlaylist(playlist, `${fallbackPrefix}-${index + 1}`))
        .filter((playlist): playlist is IptvPlaylistEntry => Boolean(playlist))
    : []);
}

function dedupeIptvPlaylists(playlists: IptvPlaylistEntry[]) {
  const seen = new Set<string>();
  return playlists.filter((playlist) => {
    const key = `${playlist.m3uUrl.trim()}|${playlist.epgUrl?.trim() ?? ""}`;
    if (!playlist.m3uUrl.trim() || seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

function androidSubtitleSize(size: number | string | undefined) {
  if (typeof size === "string") return size;
  if (!Number.isFinite(size)) return "Medium";
  if (Number(size) <= 85) return "Small";
  if (Number(size) >= 160) return "Extra Large";
  if (Number(size) >= 125) return "Large";
  return "Medium";
}

// Android stores the AI model as its enum name. Older web builds wrote
// "groq"/"gemini"/"off", which Android does not understand.
export function androidAiModel(value: AppSettings["aiSubtitleModel"]) {
  return value === "gemini" ? "GEMINI_FLASH_25" : "GROQ_LLAMA_70B";
}

export function webAiModel(value: unknown): AppSettings["aiSubtitleModel"] {
  return String(value ?? "").toLowerCase().includes("gemini") ? "gemini" : "groq";
}

// Accent colours: the web ids map onto Android's eight named colours.
const ACCENT_TO_ANDROID: Record<string, string> = {
  arctic: "White", red: "Red", orange: "Orange", gold: "Yellow", green: "Green", blue: "Blue", indigo: "Indigo", purple: "Violet"
};

export function androidAccent(value: string) {
  return ACCENT_TO_ANDROID[value] ?? (Object.values(ACCENT_TO_ANDROID).includes(value) ? value : "White");
}

export function webAccent(value: unknown) {
  const raw = String(value ?? "").trim();
  if (raw in ACCENT_TO_ANDROID) return raw;
  const lower = raw.toLowerCase();
  if (lower === "violet" || lower === "purple") return "purple";
  if (lower === "yellow" || lower === "gold") return "gold";
  const match = Object.entries(ACCENT_TO_ANDROID).find(([, name]) => name.toLowerCase() === lower);
  return match ? match[0] : "arctic";
}

function webSubtitleSize(size: unknown) {
  const raw = String(size ?? "").toLowerCase();
  if (raw === "small") return 80;
  if (raw === "large") return 140;
  if (raw === "extra large" || raw === "xlarge") return 170;
  const parsed = Number(size);
  return Number.isFinite(parsed) ? parsed : 100;
}

function androidSubtitleColor(settings: AppSettings) {
  return settings.subtitleColorName || webSubtitleColorName(settings.subtitleColor);
}

function webSubtitleColorName(value: unknown): AppSettings["subtitleColorName"] {
  const raw = String(value ?? "").trim().toLowerCase();
  if (raw.includes("yellow") || raw === "#ffff00" || raw === "#ffeb3b") return "Yellow";
  if (raw.includes("green") || raw === "#00ff00" || raw === "#4caf50") return "Green";
  if (raw.includes("cyan") || raw === "#00ffff" || raw === "#00bcd4") return "Cyan";
  if (raw.includes("red") || raw === "#ff0000" || raw === "#f44336") return "Red";
  if (raw.includes("orange") || raw === "#ffa500" || raw === "#ff9800") return "Orange";
  if (raw.includes("blue") || raw === "#0000ff" || raw === "#2196f3") return "Blue";
  if (raw.includes("violet") || raw.includes("purple") || raw === "#8b5cf6") return "Violet";
  return "White";
}

function webSubtitleColor(value: unknown) {
  switch (webSubtitleColorName(value)) {
    case "Yellow": return "#ffeb3b";
    case "Green": return "#4caf50";
    case "Cyan": return "#00bcd4";
    case "Red": return "#f44336";
    case "Orange": return "#ff9800";
    case "Blue": return "#2196f3";
    case "Violet": return "#8b5cf6";
    default: return "#ffffff";
  }
}

function androidSubtitleOffset(value: AppSettings["subtitleOffset"]) {
  switch (value) {
    case "low": return "Low";
    case "medium": return "Medium";
    case "high": return "High";
    default: return "Bottom";
  }
}

function webSubtitleOffset(value: unknown): AppSettings["subtitleOffset"] {
  switch (String(value ?? "").trim().toLowerCase()) {
    case "low": return "low";
    case "medium": return "medium";
    case "high": return "high";
    default: return "bottom";
  }
}

function androidSubtitleStyle(value: AppSettings["subtitleStyle"]) {
  if (value === "background") return "Background";
  if (value === "shadow" || value === "raised") return "Normal";
  return "Bold";
}

function webSubtitleStyle(value: unknown): AppSettings["subtitleStyle"] {
  switch (String(value ?? "").trim().toLowerCase()) {
    case "background": return "background";
    case "normal": return "shadow";
    case "bold":
    case "outline":
    default:
      return "outline";
  }
}

function androidFrameRateMode(value: AppSettings["frameRateMatchingMode"]) {
  switch (value) {
    case "seamless": return "Seamless only";
    case "always": return "Always";
    default: return "Off";
  }
}

function webFrameRateMode(value: unknown): AppSettings["frameRateMatchingMode"] {
  switch (String(value ?? "").trim().toLowerCase()) {
    case "seamless":
    case "seamless only":
    case "only if seamless":
    case "only_if_seamless":
      return "seamless";
    case "always":
      return "always";
    default:
      return "off";
  }
}

const ANDROID_MAX_QUALITY = new Set(["Unlimited", "720p", "1080p", "4K"]);
function webMaxQuality(value: unknown): AppSettings["autoPlayMaxQuality"] {
  const raw = String(value ?? "").trim().toLowerCase();
  if (raw === "720p" || raw === "hd") return "720p";
  if (raw === "1080p" || raw === "fhd" || raw === "fullhd") return "1080p";
  if (raw === "4k" || raw === "2160p" || raw === "uhd") return "4K";
  return "Unlimited";
}

function normalizedDns(value: unknown): AppSettings["dnsProvider"] {
  const raw = String(value ?? "").trim().toLowerCase();
  if (raw.includes("cloudflare")) return "cloudflare";
  if (raw.includes("google")) return "google";
  if (raw.includes("adguard") || raw.includes("ad guard")) return "adguard";
  if (raw.includes("quad9")) return "quad9";
  return "system";
}

function validQualityFilters(value: unknown): QualityFilterConfig[] {
  const values = arrayValue(value);
  if (!values.length) return [];
  return values.reduce<QualityFilterConfig[]>((filters, item, index) => {
    const raw = objectRecord(item);
    const regexPattern = stringValue(raw.regexPattern);
    if (!regexPattern) return filters;
    filters.push({
      id: stringValue(raw.id) || `filter-${index + 1}`,
      deviceName: stringValue(raw.deviceName) || "Quality filter",
      regexPattern,
      enabled: raw.enabled !== false,
      createdAt: Number(raw.createdAt) || Date.now()
    });
    return filters;
  }, []);
}

function androidQuality(value: AppSettings["autoPlayMinQuality"]) {
  switch (value) {
    case "hd": return "720p";
    case "fhd": return "1080p";
    case "4k": return "4K";
    default: return "Any";
  }
}

function webQuality(value: unknown): AppSettings["autoPlayMinQuality"] {
  switch (String(value ?? "").toLowerCase()) {
    case "720p":
    case "hd":
      return "hd";
    case "1080p":
    case "fhd":
    case "fullhd":
      return "fhd";
    case "4k":
    case "2160p":
    case "uhd":
      return "4k";
    default:
      return "any";
  }
}

function androidProfileSettings(settings: AppSettings) {
  return {
    defaultSubtitle: settings.defaultSubtitle || "Off",
    defaultAudioLanguage: settings.audioLanguage || "Auto (Original)",
    contentLanguage: settings.language || "en-US",
    trailerAutoPlay: settings.trailerAutoPlay,
    trailerSoundEnabled: settings.trailerSound,
    trailerDelaySeconds: Math.max(0, Math.min(10, Math.round(Number(settings.trailerDelaySeconds) || 0))),
    trailerInCards: settings.trailerInCards,
    clockFormat: settings.clockFormat,
    showBudget: settings.showBudget,
    iptvFavoritesOnHome: settings.iptvFavoritesOnHome,
    showLoadingStats: settings.showLoadingStats,
    spoilerBlurEnabled: settings.spoilerBlur,
    volumeBoostDb: Math.max(0, Math.min(15, Number(settings.volumeBoostDb) || 0)),
    dnsProvider: settings.dnsProvider,
    subtitleUsageJson: "",
    subtitleSettingsUpdatedAt: Date.now(),
    subtitleSize: androidSubtitleSize(settings.subtitleSize),
    subtitleColor: androidSubtitleColor(settings),
    subtitleOffset: androidSubtitleOffset(settings.subtitleOffset),
    subtitleStyle: androidSubtitleStyle(settings.subtitleStyle),
    subtitleStylized: settings.subtitleStylized,
    secondarySubtitle: settings.secondarySubtitle || "Off",
    filterSubtitlesByLanguage: settings.filterSubtitlesByLanguage,
    subtitleFont: settings.subtitleFont || "System",
    useForcedSubtitles: settings.useForcedSubtitles,
    showEpisodeRatings: settings.showEpisodeRatings,
    animeEpisodeStructuring: settings.animeEpisodeStructuring === "standard" ? "standard" : "broadcast",
    streamSearchMode: settings.streamSearchMode === "sequential" ? "sequential" : "parallel",
    // Optional on Android: only sent once chosen here, so the app keeps its own value otherwise.
    ...(settings.netflixHomeStyle !== undefined ? { netflixHomeStyle: settings.netflixHomeStyle } : {}),
    ...(settings.watchlistOnHome !== undefined ? { watchlistOnHome: settings.watchlistOnHome } : {}),
    ...(settings.guideRowCount !== undefined ? { guideRowCount: settings.guideRowCount } : {}),
    ...(settings.autoPlayMaxQuality !== undefined && ANDROID_MAX_QUALITY.has(settings.autoPlayMaxQuality) ? { autoPlayMaxQuality: settings.autoPlayMaxQuality } : {}),
    ...(settings.autoPlayMaxSizeGb !== undefined ? { autoPlayMaxSizeGb: Math.max(0, Math.min(1024, Math.round(Number(settings.autoPlayMaxSizeGb) || 0))) } : {}),
    homeServerConnectionJson: serializeHomeServerConnectionJson(settings.homeServers),
    torrServerBaseUrl: settings.torrServerBaseUrl || null,
    ...(settings.catalogueRowLayoutModes !== undefined ? { catalogueRowLayoutModes: settings.catalogueRowLayoutModes } : {}),
    ...(settings.streamProvidersCustomOrder !== undefined ? { streamProvidersCustomOrder: settings.streamProvidersCustomOrder } : {}),
    ...(settings.streamIntegrationsMacroOrder !== undefined ? { streamIntegrationsMacroOrder: settings.streamIntegrationsMacroOrder } : {}),
    ...(settings.streamMacroEnabledMap !== undefined ? { streamMacroEnabledMap: settings.streamMacroEnabledMap } : {}),
    ...(settings.streamProviderEnabledMap !== undefined ? { streamProviderEnabledMap: settings.streamProviderEnabledMap } : {}),
    cardLayoutMode: settings.cardLayoutMode,
    frameRateMatchingMode: androidFrameRateMode(settings.frameRateMatchingMode),
    autoPlayNext: settings.autoPlayNext,
    autoPlaySingleSource: settings.autoPlaySingleSource,
    autoPlayMinQuality: androidQuality(settings.autoPlayMinQuality),
    includeSpecials: settings.includeSpecials,
    qualityFilters: settings.qualityFilters
  };
}

function settingsFromAndroidProfile(value: unknown): Partial<AppSettings> {
  const state = objectRecord(value);
  const partial: Partial<AppSettings> = {};
  if ("defaultSubtitle" in state) partial.defaultSubtitle = String(state.defaultSubtitle || "");
  if ("defaultAudioLanguage" in state) partial.audioLanguage = String(state.defaultAudioLanguage || "");
  if ("contentLanguage" in state) partial.language = String(state.contentLanguage || "en-US");
  if ("trailerAutoPlay" in state) partial.trailerAutoPlay = Boolean(state.trailerAutoPlay);
  if ("trailerSoundEnabled" in state) partial.trailerSound = Boolean(state.trailerSoundEnabled);
  if ("trailerDelaySeconds" in state) { const delay = Number(state.trailerDelaySeconds); partial.trailerDelaySeconds = Number.isFinite(delay) ? delay : 1; }
  if ("trailerInCards" in state) partial.trailerInCards = Boolean(state.trailerInCards);
  if ("clockFormat" in state) partial.clockFormat = String(state.clockFormat) === "12h" ? "12h" : "24h";
  if ("showBudget" in state) partial.showBudget = Boolean(state.showBudget);
  if ("iptvFavoritesOnHome" in state) partial.iptvFavoritesOnHome = state.iptvFavoritesOnHome !== false;
  if ("showLoadingStats" in state) partial.showLoadingStats = Boolean(state.showLoadingStats);
  if ("spoilerBlurEnabled" in state) partial.spoilerBlur = Boolean(state.spoilerBlurEnabled);
  if ("volumeBoostDb" in state) partial.volumeBoostDb = Math.max(0, Math.min(15, Number(state.volumeBoostDb) || 0));
  if ("includeSpecials" in state) partial.includeSpecials = Boolean(state.includeSpecials);
  if ("dnsProvider" in state) partial.dnsProvider = normalizedDns(state.dnsProvider);
  if ("subtitleSize" in state) partial.subtitleSize = webSubtitleSize(state.subtitleSize);
  if ("subtitleColor" in state) {
    partial.subtitleColorName = webSubtitleColorName(state.subtitleColor);
    partial.subtitleColor = webSubtitleColor(state.subtitleColor);
  }
  if ("subtitleOffset" in state) partial.subtitleOffset = webSubtitleOffset(state.subtitleOffset);
  if ("subtitleStyle" in state) partial.subtitleStyle = webSubtitleStyle(state.subtitleStyle);
  // AI subtitle translation — same cloud fields the Android app writes, so the
  // key/model configured on the TV work here without re-entry.
  if ("subtitleAiEnabled" in state) partial.aiSubtitlesEnabled = Boolean(state.subtitleAiEnabled);
  if ("subtitleAiAutoSelect" in state) partial.aiAutoSelect = Boolean(state.subtitleAiAutoSelect);
  if ("subtitleAiApiKey" in state) partial.aiApiKey = String(state.subtitleAiApiKey || "");
  if ("subtitleAiModel" in state) partial.aiSubtitleModel = webAiModel(state.subtitleAiModel);
  if ("subtitleStylized" in state) partial.subtitleStylized = Boolean(state.subtitleStylized);
  if ("subtitleFont" in state && typeof state.subtitleFont === "string" && state.subtitleFont.trim()) partial.subtitleFont = state.subtitleFont.trim();
  if ("useForcedSubtitles" in state) partial.useForcedSubtitles = Boolean(state.useForcedSubtitles);
  if ("showEpisodeRatings" in state) partial.showEpisodeRatings = Boolean(state.showEpisodeRatings);
  if ("animeEpisodeStructuring" in state) partial.animeEpisodeStructuring = String(state.animeEpisodeStructuring).toLowerCase() === "standard" ? "standard" : "broadcast";
  if ("streamSearchMode" in state) partial.streamSearchMode = String(state.streamSearchMode).toLowerCase() === "sequential" ? "sequential" : "parallel";
  if (typeof state.netflixHomeStyle === "boolean") partial.netflixHomeStyle = state.netflixHomeStyle;
  if (typeof state.watchlistOnHome === "boolean") partial.watchlistOnHome = state.watchlistOnHome;
  if (state.guideRowCount !== undefined && state.guideRowCount !== null && Number.isFinite(Number(state.guideRowCount))) partial.guideRowCount = Number(state.guideRowCount);
  if (state.autoPlayMaxQuality !== undefined && state.autoPlayMaxQuality !== null) partial.autoPlayMaxQuality = webMaxQuality(state.autoPlayMaxQuality);
  if (state.autoPlayMaxSizeGb !== undefined && state.autoPlayMaxSizeGb !== null && Number.isFinite(Number(state.autoPlayMaxSizeGb))) partial.autoPlayMaxSizeGb = Number(state.autoPlayMaxSizeGb);
  if ("secondarySubtitle" in state) partial.secondarySubtitle = String(state.secondarySubtitle || "");
  if ("filterSubtitlesByLanguage" in state) partial.filterSubtitlesByLanguage = Boolean(state.filterSubtitlesByLanguage);
  if ("homeServerConnectionJson" in state && typeof state.homeServerConnectionJson === "string" && state.homeServerConnectionJson.trim()) {
    // The Android app writes { connections: [...] } with its own field names;
    // parseHomeServerConnectionJson handles that shape, a bare array, or a
    // single object, and maps to the web HomeServerConfig.
    const parsed = parseHomeServerConnectionJson(state.homeServerConnectionJson);
    if (parsed.length) partial.homeServers = parsed;
  }
  if ("cardLayoutMode" in state) partial.cardLayoutMode = String(state.cardLayoutMode) === "poster" ? "poster" : "landscape";
  if ("frameRateMatchingMode" in state) partial.frameRateMatchingMode = webFrameRateMode(state.frameRateMatchingMode);
  if ("autoPlayNext" in state) partial.autoPlayNext = Boolean(state.autoPlayNext);
  if ("autoPlaySingleSource" in state) partial.autoPlaySingleSource = Boolean(state.autoPlaySingleSource);
  if ("autoPlayMinQuality" in state) partial.autoPlayMinQuality = webQuality(state.autoPlayMinQuality);
  if ("torrServerBaseUrl" in state) partial.torrServerBaseUrl = stringValue(state.torrServerBaseUrl);
  // Source priority (StreamIntegrationRepository). Blank strings mean "not set" on Android.
  if (typeof state.streamProvidersCustomOrder === "string") partial.streamProvidersCustomOrder = state.streamProvidersCustomOrder;
  if (typeof state.streamIntegrationsMacroOrder === "string") partial.streamIntegrationsMacroOrder = state.streamIntegrationsMacroOrder;
  if ("streamMacroEnabledMap" in state) partial.streamMacroEnabledMap = booleanMap(state.streamMacroEnabledMap);
  if ("streamProviderEnabledMap" in state) partial.streamProviderEnabledMap = booleanMap(state.streamProviderEnabledMap);
  if ("catalogueRowLayoutModes" in state) partial.catalogueRowLayoutModes = normalizedRowLayoutModes(state.catalogueRowLayoutModes) ?? {};
  if ("qualityFilters" in state) {
    const filters = validQualityFilters(state.qualityFilters);
    partial.qualityFilters = filters;
    if (filters.length) partial.qualityFilterPreset = "custom";
  }
  return partial;
}

function booleanMap(value: unknown): Record<string, boolean> {
  return Object.fromEntries(Object.entries(objectRecord(value)).filter(([key, enabled]) => key.trim() && typeof enabled === "boolean")) as Record<string, boolean>;
}

/** The portals a synced IPTV profile has, by Android's import rule (the list, else the single-portal fields). */
function cloudPortals(state: Record<string, unknown>, rootState: RawPayload = {}): StalkerPortalEntry[] {
  return portalsFromCloud(state.stalkerPortals, state.stalkerPortalUrl ?? rootState.iptvStalkerUrl, state.stalkerMacAddress ?? rootState.iptvStalkerMac);
}

function iptvFromAndroid(value: unknown, root?: RawPayload): Partial<AppSettings> {
  const rootState = root ?? {};
  const stalkerPortals = cloudPortals(objectRecord(value), rootState);
  // Category keys and channel ids of single-portal builds (`stalker|…`) belong to Portal 1, as on Android.
  const state = migrateIptvCloudState(objectRecord(value), stalkerPortals) as AndroidIptvProfileState;
  const rootSettings = objectRecord(rootState.settings);
  const profilePlaylists = normalizeIptvPlaylists(state.playlists, "cloud-profile");
  const rootSettingsPlaylists = normalizeIptvPlaylists(rootSettings.iptvPlaylists, "cloud-settings");
  const rootPlaylists = normalizeIptvPlaylists(rootState.iptvPlaylists ?? rootState.playlists, "cloud-root");
  const legacyM3uUrl = stringValue(state.m3uUrl) || stringValue(rootState.iptvM3uUrl);
  const legacyEpgUrl = stringValue(state.epgUrl) || stringValue(rootState.iptvEpgUrl);
  const legacyPlaylists = legacyM3uUrl
      ? [{
          id: "cloud-primary",
          name: "Cloud IPTV",
          m3uUrl: legacyM3uUrl,
          epgUrl: legacyEpgUrl,
          enabled: true
        }]
      : [];
  // An explicit per-profile playlist list is authoritative, including an empty
  // one. Legacy mirrors can be stale or belong to a different profile.
  const playlists = "playlists" in state ? profilePlaylists : dedupeIptvPlaylists([
    ...profilePlaylists,
    ...rootSettingsPlaylists,
    ...rootPlaylists,
    ...legacyPlaylists
  ]);
  const legacyStalker = legacyStalkerFields(stalkerPortals);
  return {
    iptvPlaylists: playlists,
    favoriteChannelIds: value !== undefined ? stringArray(state.favoriteChannels) : migrateLegacyStalkerChannelIds(stringArray(rootState.iptvFavoriteChannels), stalkerPortals),
    iptvTvSession: normalizeTvSession((state as Record<string, unknown>).tvSession),
    favoriteGroupIds: value !== undefined ? stringArray(state.favoriteGroups) : migrateLegacyStalkerKeys(stringArray(rootState.iptvFavoriteGroups)),
    hiddenGroupIds: stringArray(state.hiddenGroups),
    lockedIptvGroupIds: stringArray(state.lockedGroups),
    groupOrder: (state.groupOrderSchema ?? 0) >= 3 ? stringArray(state.groupOrder) : [],
    iptvChannelOrder: normalizeChannelOrder(state.channelOrder),
    iptvGroupNames: normalizeGroupNames(state.groupNames),
    iptvSortOrder: state.sortOrder === "number" || state.sortOrder === "name" ? state.sortOrder : "provider",
    iptvStalkerPortals: stalkerPortals,
    iptvStalkerUrl: legacyStalker.stalkerPortalUrl,
    iptvStalkerMac: legacyStalker.stalkerMacAddress
  };
}

function androidCwToHistory(item: AndroidContinueWatchingItem, profileId?: string | null): WatchHistoryEntry | null {
  const tmdbId = Number(item.id ?? 0);
  if (!tmdbId) return null;
  const mediaType = String(item.mediaType ?? "movie").toLowerCase() === "tv" ? "tv" : "movie";
  const duration = Math.max(0, Number(item.durationSeconds ?? 0));
  const position = Math.max(0, Number(item.resumePositionSeconds ?? 0));
  const progress = Math.max(0, Math.min(0.99, Number(item.progress ?? 0) / 100));
  return {
    ...(item.addonNativeId ? { addon_native_id: item.addonNativeId, addon_native_addon_id: item.addonNativeAddonId } : {}),
    ...(item.isUpNext ? { is_up_next: true, release_date: item.releaseDate || null } : {}),
    user_id: "",
    profile_id: profileId ?? null,
    media_type: mediaType,
    show_tmdb_id: tmdbId,
    season: item.season ?? null,
    episode: item.episode ?? null,
    title: item.title ?? null,
    episode_title: item.episodeTitle ?? null,
    progress,
    duration_seconds: duration,
    position_seconds: position,
    backdrop_path: item.backdropPath ?? null,
    episode_still_path: item.episodeStillPath ?? null,
    poster_path: item.posterPath ?? null,
    stream_key: item.streamKey ?? null,
    stream_addon_id: item.streamAddonId ?? null,
    stream_title: item.streamTitle ?? null,
    updated_at: item.updatedAtMs ? new Date(item.updatedAtMs).toISOString() : null
  };
}

/**
 * A web playback checkpoint in Android's ContinueWatchingItem shape. Fields the
 * web does not know are left null/absent so the merge keeps Android's value
 * (stream_key, display numbering, release date) for the same episode.
 */
function historyToAndroidCw(entry: Omit<WatchHistoryEntry, "user_id">, updatedAtMs = Date.now()): ProgressItem {
  const isTv = entry.media_type === "tv";
  return {
    ...(entry.addon_native_id ? { addonNativeId: entry.addon_native_id, addonNativeAddonId: entry.addon_native_addon_id } : {}),
    id: entry.show_tmdb_id,
    // Android drops rows without a title (sanitizeContinueWatchingItems).
    title: entry.title?.trim() || entry.episode_title?.trim() || "Untitled",
    mediaType: isTv ? "tv" : "movie",
    // 89.6% must not round up to Android's 90% "watched" threshold.
    progress: Math.min(Math.round(Math.max(0, Math.min(1, entry.progress)) * 100), entry.progress < 0.9 ? 89 : 100),
    resumePositionSeconds: Math.max(0, Math.round(entry.position_seconds ?? 0)),
    durationSeconds: Math.max(0, Math.round(entry.duration_seconds ?? 0)),
    season: entry.season ?? null,
    episode: entry.episode ?? null,
    ...(isTv && entry.season != null && entry.episode != null ? { displaySeason: entry.season, displayEpisode: entry.episode } : {}),
    episodeTitle: entry.episode_title ?? null,
    backdropPath: entry.backdrop_path ?? null,
    episodeStillPath: entry.episode_still_path ?? null,
    posterPath: entry.poster_path ?? null,
    streamKey: entry.stream_key ?? null,
    streamAddonId: entry.stream_addon_id ?? null,
    streamTitle: entry.stream_title ?? entry.source ?? null,
    isUpNext: false,
    updatedAtMs
  };
}

const continueWatchingShowKey = (item: { id?: number; mediaType?: string }) =>
  `${String(item.mediaType ?? "").toLowerCase()}:${Number(item.id ?? 0)}`;

/** Android keeps one row per show (TraktRepository.saveLocalContinueWatching); the newest wins. */
function newestPerShow(items: AndroidContinueWatchingItem[]): AndroidContinueWatchingItem[] {
  const byShow = new Map<string, AndroidContinueWatchingItem>();
  for (const item of items) {
    if (!item?.id) continue;
    const key = continueWatchingShowKey(item);
    const current = byShow.get(key);
    if (!current || Number(item.updatedAtMs ?? 0) > Number(current.updatedAtMs ?? 0)) byShow.set(key, item);
  }
  return [...byShow.values()];
}

function androidContinueWatchingItems(root: RawPayload, profileId?: string | null): AndroidContinueWatchingItem[] {
  const readByProfile = (key: string) => {
    const byProfile = objectRecord<unknown>(root[key]);
    if (!Object.keys(byProfile).length) return [] as AndroidContinueWatchingItem[];
    return profileId
      ? arrayValue<AndroidContinueWatchingItem>(byProfile[profileId])
      : Object.values(byProfile).flatMap((items) => arrayValue<AndroidContinueWatchingItem>(items));
  };
  const scoped = [
    ...readByProfile("localContinueWatchingByProfile"),
    ...readByProfile("continueWatchingByProfile"),
    ...readByProfile("watchHistoryByProfile")
  ];
  const global = [
    ...arrayValue<AndroidContinueWatchingItem>(root.localContinueWatching),
    ...arrayValue<AndroidContinueWatchingItem>(root.continueWatching)
  ];
  // Legacy single-profile lists only fill titles the profile's own list lacks.
  const own = newestPerShow(scoped);
  const seen = new Set(own.map(continueWatchingShowKey));
  return [...own, ...newestPerShow(global).filter((item) => !seen.has(continueWatchingShowKey(item)))];
}

// A single app refresh calls pullRawPayload five times (payload, trakt token,
// continue watching, watchlist, profiles) — all reading the SAME account-sync
// document. Deduplicate: one in-flight pull is shared by all callers, and its
// result is cached briefly so the near-simultaneous reads become ONE backend
// (Netlify function) invocation instead of five. This is the single biggest
// per-user-per-session cut on the auth backend. Writes clear the cache so the
// next read is fresh.
let rawPayloadCache: { userId: string; at: number; payload: RawPayload } | null = null;
let rawPayloadInFlight: { userId: string; promise: Promise<RawPayload> } | null = null;
let rawPayloadGeneration = 0;
const RAW_PAYLOAD_TTL_MS = 5_000;

export function invalidateRawPayloadCache() {
  rawPayloadCache = null;
  rawPayloadInFlight = null;
  rawPayloadGeneration++;
}

async function fetchRawPayload(auth: AuthClient): Promise<RawPayload> {
  if (canUseBackendSync(auth)) {
    const response = await backendRequest<AccountSyncPullResponse>(auth, "account-sync-pull", { method: "GET" });
    return parsePayload(response.payload);
  }
  const rows = await auth.supabase<Array<{ payload?: string | null }>>(
    `/rest/v1/account_sync_state?user_id=eq.${auth.session!.userId}&select=user_id,payload,updated_at`
  );
  const raw = rows[0]?.payload;
  if (!raw) return {};
  try {
    return (JSON.parse(raw) as RawPayload) ?? {};
  } catch {
    return {};
  }
}

/** Read the full account_sync_state payload object (shared with Android). */
export async function pullRawPayload(auth: AuthClient): Promise<RawPayload> {
  if (!auth.session) return {};
  const userId = auth.session.userId;
  if (rawPayloadCache && rawPayloadCache.userId === userId && Date.now() - rawPayloadCache.at < RAW_PAYLOAD_TTL_MS) {
    return structuredClone(rawPayloadCache.payload);
  }
  if (rawPayloadInFlight && rawPayloadInFlight.userId === userId) {
    return structuredClone(await rawPayloadInFlight.promise);
  }
  const generation = rawPayloadGeneration;
  const promise = fetchRawPayload(auth)
    .then((payload) => {
      if (generation === rawPayloadGeneration && auth.session?.userId === userId) rawPayloadCache = { userId, at: Date.now(), payload };
      return payload;
    })
    .finally(() => {
      if (rawPayloadInFlight?.promise === promise) rawPayloadInFlight = null;
    });
  rawPayloadInFlight = { userId, promise };
  return structuredClone(await promise);
}

async function writeRawPayload(auth: AuthClient, payload: RawPayload, baseUpdatedAtMs?: number) {
  if (!auth.session) return;
  payload.userId = auth.session.userId;
  payload.updatedAt = Date.now();
  const userId = auth.session.userId;
  if (canUseBackendSync(auth)) {
    // baseUpdatedAtMs = when this document was read. The server keeps another
    // device's Continue Watching rows written after that instead of treating
    // their absence here as a removal (netlify-auth-site _sync-merge.js).
    const result = await backendRequest<{ accepted?: boolean; reason?: string }>(auth, "account-sync-push", {
      method: "POST",
      body: JSON.stringify(baseUpdatedAtMs ? { payload, baseUpdatedAtMs } : { payload })
    });
    if (result.accepted !== true) {
      invalidateRawPayloadCache();
      throw new Error("Cloud did not accept your changes. They remain queued on this device; retry sync.");
    }
    // The server merges concurrent device edits. Read its acknowledged result,
    // not our submitted document (which may omit those edits).
    if (auth.session?.userId === userId) invalidateRawPayloadCache();
    return;
  }
  await auth.supabase("/rest/v1/account_sync_state", {
    method: "POST",
    headers: { Prefer: "resolution=merge-duplicates" },
    body: JSON.stringify({
      user_id: auth.session.userId,
      payload: JSON.stringify(payload),
      updated_at: new Date().toISOString()
    })
  });
  invalidateRawPayloadCache();
}

/**
 * Read-modify-write the shared payload, preserving keys this app doesn't own
 * (e.g. Android's profiles / avatar images). Mirrors Android's
 * AuthRepository.mutateAccountSyncPayload.
 */
const mutationQueues = new Map<string, Promise<void>>();

/**
 * Run `run` after this account's queued full-document writes. A progress
 * checkpoint sent while a full write is between its read and its push would
 * land first, and the push (whose snapshot lacks the new row) would drop it.
 */
async function runInMutationQueue<T>(userId: string, run: () => Promise<T>): Promise<T> {
  const task = (mutationQueues.get(userId) ?? Promise.resolve()).catch(() => undefined).then(run);
  const settled = task.then(() => undefined, () => undefined);
  mutationQueues.set(userId, settled);
  try { return await task; }
  finally { if (mutationQueues.get(userId) === settled) mutationQueues.delete(userId); }
}

export async function mutateCloudPayload(auth: AuthClient, mutator: (root: RawPayload) => void) {
  if (!auth.session) return;
  const userId = auth.session.userId;
  const task = (mutationQueues.get(userId) ?? Promise.resolve()).catch(() => undefined).then(async () => {
    if (auth.session?.userId !== userId) throw new Error("Account changed before sync completed");
    // A recent read cache can predate another device's change. Mutations must
    // start from a fresh snapshot, not echo that stale cached account back.
    invalidateRawPayloadCache();
    const readAt = Date.now();
    const root = await pullRawPayload(auth);
    if (auth.session?.userId !== userId) throw new Error("Account changed before sync completed");
    mutator(root);
    await writeRawPayload(auth, root, readAt);
  });
  mutationQueues.set(userId, task);
  try { await task; }
  finally { if (mutationQueues.get(userId) === task) mutationQueues.delete(userId); }
}

export async function pullCloudPayload(auth: AuthClient, profileId?: string | null): Promise<CloudPayload> {
  const root = await pullRawPayload(auth);
  const profileSettings = settingsFromAndroidProfile(scopedValue(root, "profileSettingsById", profileId));
  const iptvSettings = iptvFromAndroid(scopedValue(root, "iptvByProfile", profileId), root);
  const profileCatalogs = scopedValue<AppSettings["catalogs"]>(root, "catalogsByProfile", profileId);
  const hiddenCatalogIds = scopedValue<string[]>(root, "hiddenPreinstalledByProfile", profileId);
  const hiddenAddonCatalogIds = scopedValue<string[]>(root, "hiddenAddonByProfile", profileId);
  const hiddenHomeServerCatalogIds = scopedValue<string[]>(root, "hiddenHomeServerByProfile", profileId);
  const profileAddons = scopedValue<InstalledAddon[]>(root, "addonsByProfile", profileId);
  const legacySettings = objectRecord<unknown>(root.settings) as Partial<AppSettings>;
  delete legacySettings.customTmdbApiKey;
  delete legacySettings.customTvdbApiKey;
  delete legacySettings.customTvdbUserPin;
  // Profile-scoped values: the legacy blob holds whichever profile saved last.
  delete legacySettings.streamProvidersCustomOrder;
  delete legacySettings.streamIntegrationsMacroOrder;
  delete legacySettings.streamMacroEnabledMap;
  delete legacySettings.streamProviderEnabledMap;
  delete legacySettings.catalogueRowLayoutModes;
  delete legacySettings.androidPluginRepositories;
  const legacyCatalogs = parseNestedJson(root.catalogs);
  const hasProfileCatalogs = Array.isArray(profileCatalogs);
  const effectiveHiddenCatalogs = hiddenCatalogIds ?? (hasProfileCatalogs ? [] : root.hiddenPreinstalledCatalogs ?? legacySettings.hiddenCatalogIds);
  const effectiveHiddenAddons = hiddenAddonCatalogIds ?? (hasProfileCatalogs ? [] : legacySettings.hiddenAddonCatalogIds);
  const effectiveHiddenHomeServers = hiddenHomeServerCatalogIds ?? (hasProfileCatalogs ? [] : legacySettings.hiddenHomeServerCatalogIds);
  // Profile-scoped lists are authoritative, including []. Do not inherit stale
  // visibility flags from the last profile that wrote the legacy settings blob.
  const catalogSettings: Partial<AppSettings> = {
    // Card style per row lives in the profile's catalogueRowLayoutModes (Android drops `layout`).
    ...(hasProfileCatalogs ? { catalogs: applyRowLayoutModes(profileCatalogs, profileSettings.catalogueRowLayoutModes) }
      : Array.isArray(legacyCatalogs) ? { catalogs: applyRowLayoutModes(legacyCatalogs as AppSettings["catalogs"], profileSettings.catalogueRowLayoutModes) } : {}),
    ...(effectiveHiddenCatalogs !== undefined ? { hiddenCatalogIds: arrayValue<string>(effectiveHiddenCatalogs) } : {}),
    ...(effectiveHiddenAddons !== undefined ? { hiddenAddonCatalogIds: arrayValue<string>(effectiveHiddenAddons) } : {}),
    ...(effectiveHiddenHomeServers !== undefined ? { hiddenHomeServerCatalogIds: arrayValue<string>(effectiveHiddenHomeServers) } : {})
  };
  // Canonical, timestamp-managed GLOBAL settings live at the top level of the payload (written by
  // Android with per-field timestamps), NOT in the legacy `root.settings` blob. Map them so an
  // Android change (accent color, AI subtitles, etc.) is actually visible on web.
  const globalSettings: Partial<AppSettings> = {};
  if ("accentColor" in root) globalSettings.accentColor = webAccent(root.accentColor);
  if ("oledBlackBackground" in root) globalSettings.oledBlack = Boolean(root.oledBlackBackground);
  if ("customUserAgent" in root) globalSettings.customUserAgent = String(root.customUserAgent ?? "");
  if ("skipProfileSelection" in root) globalSettings.skipProfileSelection = Boolean(root.skipProfileSelection);
  if ("subtitleAiEnabled" in root) globalSettings.aiSubtitlesEnabled = Boolean(root.subtitleAiEnabled);
  if ("subtitleAiAutoSelect" in root) globalSettings.aiAutoSelect = Boolean(root.subtitleAiAutoSelect);
  if ("subtitleAiApiKey" in root) globalSettings.aiApiKey = String(root.subtitleAiApiKey ?? "");
  // Android stores GROQ_LLAMA_70B / GEMINI_FLASH_25; older web builds wrote groq/gemini/off.
  if ("subtitleAiModel" in root) globalSettings.aiSubtitleModel = webAiModel(root.subtitleAiModel);
  if ("subtitleAiFindBestMatch" in root) globalSettings.aiFindBestMatch = Boolean(root.subtitleAiFindBestMatch);
  if ("subtitlePreloadEnabled" in root) globalSettings.subtitlePreload = root.subtitlePreloadEnabled !== false;
  if ("dolbyVisionCompatEnabled" in root) globalSettings.dolbyVisionCompat = root.dolbyVisionCompatEnabled !== false;
  if ("telegramSearchOnClickOnly" in root) globalSettings.telegramSearchOnClickOnly = root.telegramSearchOnClickOnly !== false;
  if ("subtitleRemoveHearingImpaired" in root) globalSettings.removeHearingImpaired = Boolean(root.subtitleRemoveHearingImpaired);
  // Read only: lets Settings name the TV's plugin repositories in the source list.
  if (Array.isArray(parseNestedJson(root.pluginRepositories))) {
    globalSettings.androidPluginRepositories = arrayValue<Record<string, unknown>>(root.pluginRepositories)
      .map((repo) => ({ id: stringValue(repo?.id), name: stringValue(repo?.name), enabled: repo?.enabled !== false }))
      .filter((repo) => repo.id);
  }
  return {
    version: typeof root.version === "number" ? root.version : 1,
    // Union the per-profile scope with the global list. An empty/partial profile
    // scope must never hide the shared library (that was a wipe amplifier).
    addons: unionAddons(arrayValue<InstalledAddon>(profileAddons), arrayValue<InstalledAddon>(root.addons)),
    settings: {
      ...legacySettings,
      // Explicitly unset, so a profile without them never keeps the previous profile's values.
      streamProvidersCustomOrder: undefined,
      streamIntegrationsMacroOrder: undefined,
      streamMacroEnabledMap: undefined,
      streamProviderEnabledMap: undefined,
      catalogueRowLayoutModes: undefined,
      androidPluginRepositories: undefined,
      ...profileSettings,
      ...globalSettings,
      ...iptvSettings,
      ...catalogSettings
    },
    updatedAt: typeof root.updatedAt === "number" ? root.updatedAt : 0
  };
}

function unionAddons(primary: InstalledAddon[], existing: unknown): InstalledAddon[] {
  const byKey = new Map<string, InstalledAddon>();
  for (const addon of [...primary, ...arrayValue<InstalledAddon>(existing)]) {
    const key = addon?.manifestUrl || addon?.id;
    if (key && !byKey.has(key)) byKey.set(key, addon);
  }
  return [...byKey.values()];
}

export async function saveCloudAddons(
  auth: AuthClient,
  addons: InstalledAddon[],
  profileId?: string | null,
  options: { removedIds?: string[]; changes?: AddonChanges } = {}
) {
  await mutateCloudPayload(auth, (root) => {
    root.version = 2;
    const timestamp = Math.max(Date.now(), Number(root.addonsUpdatedAt || 0) + 1);
    // Outbox operations retain their original timestamps across retries.
    const changes = mergeAddonChanges(root.addonChanges, options.changes ??
      recordAddonChanges({}, [], options.removedIds ?? [], timestamp));
    root.addonChanges = changes;
    // Addon writes are union-based and can only shrink via an explicit remove
    // list. This makes it impossible for a stale/empty in-memory list — or an
    // empty per-profile scope — to wipe the shared library. A removal drops the
    // id from every scope; everything else is merged in.
    const applyRemovals = (list: InstalledAddon[]) => list.filter((a) => !changes[a.id]?.removed);
    root.addons = applyRemovals(unionAddons(addons, root.addons));
    const byProfile = objectRecord<InstalledAddon[]>(root.addonsByProfile);
    for (const [id, list] of Object.entries(byProfile)) byProfile[id] = applyRemovals(arrayValue<InstalledAddon>(list));
    root.addonsByProfile = byProfile;
    if (profileId) {
      const scoped = scopedValue<InstalledAddon[]>(root, "addonsByProfile", profileId);
      setScopedValue(root, "addonsByProfile", profileId, applyRemovals(unionAddons(addons, scoped)));
    }
    // Set-level timestamp: lets Android tell an intentional "removed everything" from a blank pull,
    // so removing the last add-on(s) from web actually propagates (reconcileAddonsWithCloud).
    root.addonsUpdatedAt = timestamp;
  });
}

function androidIptvSettings(settings: AppSettings): Record<string, unknown> {
  // Older apps only read the single-portal fields: they carry the first enabled portal.
  const legacyStalker = settings.iptvStalkerPortals
    ? legacyStalkerFields(settings.iptvStalkerPortals)
    : { stalkerPortalUrl: settings.iptvStalkerUrl, stalkerMacAddress: settings.iptvStalkerMac };
  return {
    m3uUrl: settings.iptvPlaylists[0]?.m3uUrl ?? "",
    epgUrl: settings.iptvPlaylists[0]?.epgUrl ?? "",
    playlists: settings.iptvPlaylists,
    ...legacyStalker,
    // Only once known, so a browser that never read the list cannot blank the TV's portals.
    ...(settings.iptvStalkerPortals !== undefined ? { stalkerPortals: settings.iptvStalkerPortals } : {}),
    favoriteChannels: settings.favoriteChannelIds,
    tvSession: normalizeTvSession(settings.iptvTvSession),
    favoriteGroups: settings.favoriteGroupIds,
    hiddenGroups: settings.hiddenGroupIds,
    groupOrder: settings.groupOrder,
    sortOrder: settings.iptvSortOrder ?? "provider",
    // Only once known, so a browser that never read it cannot blank the TV's.
    ...(settings.iptvChannelOrder !== undefined ? { channelOrder: settings.iptvChannelOrder } : {}),
    ...(settings.iptvGroupNames !== undefined ? { groupNames: settings.iptvGroupNames } : {})
  };
}

/** A missing channel order / name map / portal list and an empty one mean the same thing. */
function sameIptvField(field: string, a: unknown, b: unknown) {
  if (field === "channelOrder") return sameFieldValue(normalizeChannelOrder(a), normalizeChannelOrder(b));
  if (field === "groupNames") return sameFieldValue(normalizeGroupNames(a), normalizeGroupNames(b));
  if (field === "stalkerPortals") return sameFieldValue(normalizeStalkerPortals(a), normalizeStalkerPortals(b));
  return sameFieldValue(a, b);
}

function mergeFavoriteEdits(remote: string[], local: string[], baseline: string[]): string[] {
  const base = new Set(baseline);
  const remoteSet = new Set(remote);
  const localSet = new Set(local);
  // Apply this device's additions/removals/order, retaining additions from
  // other devices and not resurrecting favorites they have already removed.
  return [...new Set([
    ...local.filter((id) => !base.has(id) || remoteSet.has(id)),
    ...remote.filter((id) => !base.has(id) && !localSet.has(id))
  ])];
}

function mergeCatalogEdits(remote: AppSettings["catalogs"], local: AppSettings["catalogs"], baseline: AppSettings["catalogs"]): AppSettings["catalogs"] {
  const baseById = new Map(baseline.map((item) => [item.id, item]));
  const remoteById = new Map(remote.map((item) => [item.id, item]));
  const localIds = new Set(local.map((item) => item.id));
  return [
    ...local.filter((item) => !baseById.has(item.id) || remoteById.has(item.id)).map((item) =>
      sameFieldValue(item, baseById.get(item.id)) ? (remoteById.get(item.id) ?? item) : item),
    ...remote.filter((item) => !baseById.has(item.id) && !localIds.has(item.id))
  ];
}

export function mergeIptvSettings(cloudState: Record<string, unknown>, settings: AppSettings, baseline?: AppSettings | null) {
  const incoming = androidIptvSettings(settings);
  const base = baseline ? androidIptvSettings(baseline) : null;
  // What the cloud holds, in the current key shape: a legacy `stalker|…` twin
  // left in the payload would otherwise bring back what this edit undid.
  const remotePortals = cloudPortals(cloudState);
  const existing = migrateIptvCloudState(cloudState, [...(settings.iptvStalkerPortals ?? []), ...remotePortals]);
  const merged = { ...existing };
  let portalsMerged = false;
  for (const [field, value] of Object.entries(incoming)) {
    if (base && sameIptvField(field, value, base[field])) continue;
    if (field === "tvSession") {
      merged[field] = mergeTvSessions(existing[field], value);
      continue;
    }
    if (field === "stalkerPortals") {
      // Per portal and field, so a portal added or edited on the TV since this browser read the list survives.
      merged[field] = base ? normalizeStalkerPortals(mergeStalkerPortalEdits(remotePortals, normalizeStalkerPortals(value),
        normalizeStalkerPortals(base[field]))) : value;
      portalsMerged = true;
      continue;
    }
    if (field === "channelOrder" || field === "groupNames") {
      // Per category: only the categories this browser reordered / renamed (or reset) change.
      const normalize = (input: unknown): Record<string, unknown> => field === "channelOrder" ? normalizeChannelOrder(input) : normalizeGroupNames(input);
      const local = normalize(value);
      const previous = base ? normalize(base[field]) : {};
      const next = base ? normalize(existing[field]) : {};
      for (const key of new Set([...Object.keys(local), ...Object.keys(previous)])) {
        if (base && sameFieldValue(local[key], previous[key])) continue;
        if (local[key] !== undefined) next[key] = local[key];
        else delete next[key];
      }
      merged[field] = next;
      continue;
    }
    if (field === "groupOrder") {
      merged.groupOrderSchema = 3;
      if (base) {
        const localOrder = stringArray(value);
        const baseOrder = stringArray(base[field]);
        let remoteOrder = stringArray(existing[field]);
        const playlistOf = (key: string) => key.split("|")[0];
        for (const playlist of new Set([...localOrder, ...baseOrder].map(playlistOf))) {
          const local = localOrder.filter(key => playlistOf(key) === playlist);
          const previous = baseOrder.filter(key => playlistOf(key) === playlist);
          if (sameFieldValue(local, previous)) continue;
          remoteOrder = [...remoteOrder.filter(key => playlistOf(key) !== playlist), ...local];
        }
        merged[field] = remoteOrder;
        continue;
      }
    }
    merged[field] = base && field in existing && (field === "favoriteChannels" || field === "favoriteGroups" || field === "hiddenGroups")
      ? mergeFavoriteEdits(stringArray(existing[field]), stringArray(value), stringArray(base[field]))
      : value;
  }
  // Older apps read the single-portal fields: they follow the merged list.
  if (portalsMerged) Object.assign(merged, legacyStalkerFields(merged.stalkerPortals as StalkerPortalEntry[]));
  return merged;
}

export async function saveCloudSettings(
  auth: AuthClient,
  settings: AppSettings,
  addons: InstalledAddon[],
  profileId?: string | null,
  profiles: Profile[] = [],
  // The web session's last-synced settings (same profile). Used to detect which fields THIS session
  // actually changed, so we only assert (and timestamp) those — never reverting a field a phone
  // changed. Null → treat every field as changed (bootstrap / after a profile switch).
  baseline?: AppSettings | null,
  changedAt = Date.now()
) {
  await mutateCloudPayload(auth, (root) => {
    root.version = 2;
    root.updatedAt = Date.now();
    // Settings saves intentionally do NOT touch root.addons: addon writes go
    // exclusively through saveCloudAddons (merge-protected). A stale session's
    // partial in-memory addon list must never leak into the shared payload.
    void addons;
    const { customTmdbApiKey: _k1, customTvdbApiKey: _k2, customTvdbUserPin: _k3, ...sanitizedSettings } = settings;
    root.settings = sanitizedSettings;

    // ── Genuine global settings that Android merges by per-field timestamp. Only write + bump the
    //    timestamp when the web actually changed the field vs its baseline; otherwise leave the
    //    (freshly read-modify-written) cloud value so a phone's newer change survives.
    const globalFields: Array<[string, unknown, unknown]> = [
      ["accentColor", androidAccent(settings.accentColor), baseline ? androidAccent(baseline.accentColor) : undefined],
      ["oledBlackBackground", settings.oledBlack, baseline?.oledBlack],
      ["skipProfileSelection", settings.skipProfileSelection, baseline?.skipProfileSelection],
      ["customUserAgent", settings.customUserAgent, baseline?.customUserAgent],
      ["dnsProvider", settings.dnsProvider, baseline?.dnsProvider],
      ["subtitleAiEnabled", settings.aiSubtitlesEnabled, baseline?.aiSubtitlesEnabled],
      ["subtitleAiAutoSelect", settings.aiAutoSelect, baseline?.aiAutoSelect],
      ["subtitleAiApiKey", settings.aiApiKey, baseline?.aiApiKey],
      ["subtitleAiModel", androidAiModel(settings.aiSubtitleModel), baseline ? androidAiModel(baseline.aiSubtitleModel) : undefined],
      ["subtitleAiFindBestMatch", settings.aiFindBestMatch, baseline?.aiFindBestMatch],
      ["subtitlePreloadEnabled", settings.subtitlePreload, baseline?.subtitlePreload],
      ["dolbyVisionCompatEnabled", settings.dolbyVisionCompat, baseline?.dolbyVisionCompat],
      ["telegramSearchOnClickOnly", settings.telegramSearchOnClickOnly, baseline?.telegramSearchOnClickOnly],
      ["subtitleRemoveHearingImpaired", settings.removeHearingImpaired, baseline?.removeHearingImpaired]
    ];
    // Older web builds wrote "gemini"/"groq"/"off" and "gold"/"purple"/"arctic", which Android
    // cannot read and keeps echoing back. Rewrite those in Android's vocabulary on the next save.
    const legacyRoot = (key: string) =>
      (key === "subtitleAiModel" && typeof root.subtitleAiModel === "string" && !["GROQ_LLAMA_70B", "GEMINI_FLASH_25"].includes(root.subtitleAiModel)) ||
      // Only the web's own accent ids; an unknown value (e.g. a hex colour) is left as it is.
      (key === "accentColor" && typeof root.accentColor === "string" && root.accentColor in ACCENT_TO_ANDROID);
    for (const [rootKey, newVal, baseVal] of globalFields) {
      if (!baseline || !sameFieldValue(newVal, baseVal) || legacyRoot(rootKey)) {
        root[rootKey] = newVal;
        bumpFieldTs(root, `g:${rootKey}`, changedAt);
      }
    }
    root.focusBorderColor = root.accentColor ?? androidAccent(settings.accentColor); // mirror of accentColor (not a merge key)

    // ── Root legacy flat mirrors: only OLD Android clients read these (current clients read
    //    profileSettingsById below). Kept for backward compat; not timestamp-managed.
    root.defaultSubtitle = settings.defaultSubtitle || "Off";
    root.defaultAudioLanguage = settings.audioLanguage || "Auto (Original)";
    root.cardLayoutMode = settings.cardLayoutMode;
    root.frameRateMatchingMode = androidFrameRateMode(settings.frameRateMatchingMode);
    root.autoPlayNext = settings.autoPlayNext;
    root.autoPlaySingleSource = settings.autoPlaySingleSource;
    root.autoPlayMinQuality = androidQuality(settings.autoPlayMinQuality);
    root.includeSpecials = settings.includeSpecials;
    root.torrServerBaseUrl = settings.torrServerBaseUrl;
    root.qualityFilters = settings.qualityFilters;
    const catalogFields = [
      ["catalogs", "catalogsByProfile"],
      ["hiddenCatalogIds", "hiddenPreinstalledByProfile"],
      ["hiddenAddonCatalogIds", "hiddenAddonByProfile"],
      ["hiddenHomeServerCatalogIds", "hiddenHomeServerByProfile"]
    ] as const;
    const catalogValues = {} as Pick<AppSettings, "catalogs" | "hiddenCatalogIds" | "hiddenAddonCatalogIds" | "hiddenHomeServerCatalogIds">;
    for (const [setting, field] of catalogFields) {
      const changed = !baseline || !sameFieldValue(settings[setting], baseline[setting]);
      const existing = scopedValue<unknown[]>(root, field, profileId);
      let value = (changed ? settings[setting] : (existing ?? settings[setting])) ?? [];
      if (changed && baseline && existing) {
        value = setting === "catalogs"
          ? mergeCatalogEdits(existing as AppSettings["catalogs"], settings.catalogs, baseline.catalogs)
          : mergeFavoriteEdits(stringArray(existing), settings[setting], baseline[setting]);
      }
      Object.assign(catalogValues, { [setting]: value });
      if (changed && profileId) {
        setScopedValue(root, field, profileId, value);
        bumpFieldTs(root, `c:${profileId}:${field}`, changedAt);
      }
    }
    root.catalogs = catalogValues.catalogs;
    root.hiddenPreinstalledCatalogs = catalogValues.hiddenCatalogIds;

    const iptvExisting = objectRecord(scopedValue(root, "iptvByProfile", profileId));
    const iptv = mergeIptvSettings(iptvExisting, settings, baseline);
    root.iptvFavoriteChannels = iptv.favoriteChannels;
    root.iptvFavoriteGroups = iptv.favoriteGroups;
    root.iptvStalkerUrl = iptv.stalkerPortalUrl;
    root.iptvStalkerMac = iptv.stalkerMacAddress;
    root.iptvM3uUrl = iptv.m3uUrl;
    root.iptvEpgUrl = iptv.epgUrl;
    root.settings = { ...sanitizedSettings, ...catalogValues, ...iptvFromAndroid(iptv) };

    if (profiles.length) root.profiles = profiles;
    if (profileId) {
      // ── Per-profile settings: merge field-by-field over the freshly-read cloud object. Only
      //    web-changed fields are overwritten + timestamped; everything else keeps the cloud value.
      const newProfile = androidProfileSettings(settings) as Record<string, unknown>;
      const baseProfile = baseline ? (androidProfileSettings(baseline) as Record<string, unknown>) : null;
      const existing = (scopedValue<Record<string, unknown>>(root, "profileSettingsById", profileId) ?? {}) as Record<string, unknown>;
      const merged: Record<string, unknown> = { ...existing };
      // Fields the web doesn't genuinely own (always sends empty) — never write them, so they can't
      // wipe a phone's value. defaultSubtitle keeps its own timestamp logic (handled below).
      const skip = new Set(["subtitleUsageJson", "defaultSubtitle", "subtitleSettingsUpdatedAt"]);
      // Map-valued fields: Android only ever adds or overwrites entries on import,
      // so merge over the cloud's map instead of replacing rows another device set.
      const unionFields = new Set(["catalogueRowLayoutModes", "streamMacroEnabledMap", "streamProviderEnabledMap"]);
      for (const field of Object.keys(newProfile)) {
        if (skip.has(field)) continue;
        if (!baseProfile || !sameFieldValue(newProfile[field], baseProfile[field])) {
          merged[field] = unionFields.has(field)
            ? { ...objectRecord(existing[field]), ...objectRecord(newProfile[field]) }
            : newProfile[field];
          bumpFieldTs(root, `p:${profileId}:${field}`, changedAt);
        }
      }
      // Android applies trailerAutoPlay / netflixHomeStyle only from payloads that declare the
      // current home presentation (HomePresentationMigration).
      if (["trailerAutoPlay", "netflixHomeStyle"].some((field) => field in newProfile && (!baseProfile || !sameFieldValue(newProfile[field], baseProfile[field])))) {
        merged.homePresentationVersion = Math.max(1, Number(merged.homePresentationVersion) || 0);
      }
      // defaultSubtitle uses Android's own subtitleSettingsUpdatedAt LWW — assert it only when the
      // web actually changed it, bumping that timestamp so Android adopts it.
      if (!baseline || !sameFieldValue(settings.defaultSubtitle, baseline.defaultSubtitle)) {
        merged.defaultSubtitle = settings.defaultSubtitle || "Off";
        merged.subtitleSettingsUpdatedAt = changedAt;
      }
      setScopedValue(root, "profileSettingsById", profileId, merged);
      // NOTE: settings saves must NOT touch add-ons. Android now reconciles add-ons to the cloud
      // authoritatively, so writing this session's (possibly stale) add-on list here could delete an
      // add-on installed on another device. Add-ons are written exclusively by saveCloudAddons().
      const newIptv = androidIptvSettings(settings);
      const baseIptv = baseline ? androidIptvSettings(baseline) : null;
      for (const [field, value] of Object.entries(newIptv)) {
        if (!baseIptv || !sameIptvField(field, value, baseIptv[field])) {
          bumpFieldTs(root, `i:${profileId}:${field}`, changedAt);
        }
      }
      setScopedValue(root, "iptvByProfile", profileId, iptv);
    }
  });
}

export interface CloudProfiles {
  profiles: Profile[];
  activeProfileId: string | null;
  avatarImages: Record<string, string>;
}

/** Most recent deletions kept in the payload; older ids are long gone from every device. */
const DELETED_PROFILES_LIMIT = 100;

function deletedProfileIds(root: RawPayload): string[] {
  return arrayValue<unknown>(root.deletedProfileIds).filter((id): id is string => typeof id === "string" && id.length > 0);
}

/**
 * The account's profile list after one device saves its own. Profiles the device does not
 * know stay: a browser or TV holding an older copy of the list must not delete a profile
 * another device added. Only an explicit delete ([deleted]) removes one. For a profile both
 * sides have, the copy edited last wins (`updatedAt`, stamped on edits only, missing = oldest;
 * a tie keeps this device's copy), so a stale copy cannot revert newer settings.
 */
function newerProfile(local: Profile, remote: Profile): Profile {
  const edited = (profile: Profile) => Number((profile as { updatedAt?: number }).updatedAt ?? 0) || 0;
  const winner = edited(remote) > edited(local) ? remote : local;
  const lastUsedAt = Math.max(Number(local.lastUsedAt) || 0, Number(remote.lastUsedAt) || 0);
  return lastUsedAt > 0 ? { ...winner, lastUsedAt } : winner;
}

export function mergeCloudProfiles(remote: Profile[], local: Profile[], deleted: ReadonlySet<string>): Profile[] {
  const localById = new Map(local.map(profile => [profile.id, profile]));
  const merged: Profile[] = [];
  const seen = new Set<string>();
  for (const profile of remote) {
    if (!profile?.id || deleted.has(profile.id) || seen.has(profile.id)) continue;
    const own = localById.get(profile.id);
    merged.push(own ? newerProfile(own, profile) : profile);
    seen.add(profile.id);
  }
  for (const profile of local) {
    if (!profile?.id || deleted.has(profile.id) || seen.has(profile.id)) continue;
    merged.push(profile);
    seen.add(profile.id);
  }
  return merged;
}

export async function pullCloudProfiles(auth: AuthClient): Promise<CloudProfiles> {
  const root = await pullRawPayload(auth);
  const deleted = new Set(deletedProfileIds(root));
  return {
    profiles: arrayValue<Profile>(root.profiles).filter(profile => !deleted.has(profile?.id)),
    activeProfileId: typeof root.activeProfileId === "string" ? root.activeProfileId : null,
    avatarImages: objectRecord<string>(root.profileAvatarImagesById)
  };
}

export type CloudTrackingProvider = "NONE" | "TRAKT" | "MDBLIST" | "SIMKL" | "WETRAKR";
export type CloudTrackingDomain = "routing" | "trakt" | "simkl" | "mdblist";

export interface CloudTrackingSelection {
  provider: CloudTrackingProvider;
  traktToken: TraktToken | null;
  mdbListApiKey: string | null;
  mdbListAccessToken?: string | null;
  mdbListRefreshToken?: string | null;
  mdbListTokenExpiresAt?: number | null;
  simklToken: SimklToken | null;
  trackingPreferences?: TrackingPreferences;
  changedDomains?: CloudTrackingDomain[];
}

export interface CloudTrackingSnapshot extends CloudTrackingSelection {
  hasCloudState: boolean;
  needsCleanup: boolean;
}

function normalizeCloudTrackingProvider(value: unknown): CloudTrackingProvider | null {
  const normalized = typeof value === "string" ? value.trim().toUpperCase() : "";
  return normalized === "NONE" || normalized === "TRAKT" || normalized === "MDBLIST" || normalized === "SIMKL" || normalized === "WETRAKR"
    ? normalized
    : null;
}

function normalizeTrackingReadMode(value: unknown, fallback: TrackingReadMode): TrackingReadMode {
  const normalized = typeof value === "string" ? value.trim().toLowerCase() : "";
  return normalized === "auto" || normalized === "trakt" || normalized === "simkl" ||
    normalized === "both" || normalized === "wetrakr" || normalized === "mdblist" ? normalized : fallback;
}

function readCloudTraktToken(root: RawPayload, profileId: string): TraktToken | null {
  const tokensByProfile = objectRecord<{
    accessToken?: string;
    refreshToken?: string;
    expiresAt?: number;
    access_token?: string;
    refresh_token?: string;
    expires_at?: number;
  }>(root.traktTokens);
  const token = tokensByProfile[profileId];
  const accessToken = token?.accessToken ?? token?.access_token;
  const refreshToken = token?.refreshToken ?? token?.refresh_token;
  const rawExpiresAt = token?.expiresAt ?? token?.expires_at ?? 0;
  const expiresAt = rawExpiresAt > 0 && rawExpiresAt < 1_000_000_000_000 ? rawExpiresAt * 1000 : rawExpiresAt;
  if (!accessToken || !refreshToken) return null;
  return {
    access_token: accessToken,
    refresh_token: refreshToken,
    expires_at: expiresAt
  };
}

function readCloudSimklToken(root: RawPayload, profileId: string): SimklToken | null {
  const directTokens = objectRecord<{ access_token?: string; accessToken?: string }>(root.simklTokens);
  const selections = objectRecord<{
    provider?: string;
    simklAccessToken?: string;
    simklCredentialUpdatedAt?: number;
  }>(root.mdbListSyncByProfile);
  const direct = directTokens[profileId];
  const selection = selections[profileId];
  const hasCanonicalCredentialState = Number(selection?.simklCredentialUpdatedAt ?? 0) > 0;
  const accessToken = hasCanonicalCredentialState
    ? selection?.simklAccessToken
    : direct?.access_token ?? direct?.accessToken ?? selection?.simklAccessToken;
  return accessToken && !accessToken.startsWith("simkl_at_") && !accessToken.startsWith("simkl_session_") ? { access_token: accessToken } : null;
}

export async function pullCloudTrackingSelection(
  auth: AuthClient,
  profileId?: string | null
): Promise<CloudTrackingSnapshot> {
  if (!profileId) {
    return {
      provider: "NONE",
      traktToken: null,
      mdbListApiKey: null,
      mdbListAccessToken: null,
      mdbListRefreshToken: null,
      mdbListTokenExpiresAt: null,
      simklToken: null,
      trackingPreferences: {
        watchlistReadMode: "auto",
        continueWatchingReadMode: "auto",
        watchedReadMode: "auto",
        writeToTrakt: false,
        writeToSimkl: false
      },
      hasCloudState: false,
      needsCleanup: false
    };
  }
  const root = await pullRawPayload(auth);
  const selections = objectRecord<Record<string, unknown>>(root.mdbListSyncByProfile);
  const selection = selections[profileId] ?? {};
  const traktToken = readCloudTraktToken(root, profileId);
  const simklToken = readCloudSimklToken(root, profileId);
  const rawMdbListKey = selection.mdbListApiKey ?? selection.mdblistApiKey;
  const mdbListApiKey = typeof rawMdbListKey === "string" ? rawMdbListKey.trim() || null : null;
  const rawMdbListAccessToken = selection.mdbListAccessToken;
  const mdbListAccessToken = typeof rawMdbListAccessToken === "string" ? rawMdbListAccessToken.trim() || null : null;
  const rawMdbListRefreshToken = selection.mdbListRefreshToken;
  const mdbListRefreshToken = typeof rawMdbListRefreshToken === "string" ? rawMdbListRefreshToken.trim() || null : null;
  const rawMdbListTokenExpiresAt = selection.mdbListTokenExpiresAt;
  const mdbListTokenExpiresAt = typeof rawMdbListTokenExpiresAt === "number" && rawMdbListTokenExpiresAt > 0
    ? rawMdbListTokenExpiresAt
    : null;
  const hasMdbList = Boolean(mdbListApiKey || mdbListAccessToken);
  const explicitProvider = normalizeCloudTrackingProvider(selection.provider);
  const inferredProvider: CloudTrackingProvider = traktToken
    ? "TRAKT"
    : simklToken
      ? "SIMKL"
      : hasMdbList
        ? "MDBLIST"
        : "NONE";
  const requestedProvider = explicitProvider ?? inferredProvider;
  const provider: CloudTrackingProvider = requestedProvider === "WETRAKR" ? "WETRAKR" : requestedProvider === "TRAKT" && traktToken
    ? "TRAKT"
    : requestedProvider === "SIMKL" && simklToken
      ? "SIMKL"
      : requestedProvider === "MDBLIST" && hasMdbList
        ? "MDBLIST"
        : "NONE";
  const storedProviderCount = Number(Boolean(traktToken)) + Number(Boolean(simklToken)) + Number(Boolean(hasMdbList));
  const defaultMode: TrackingReadMode = provider === "WETRAKR" ? "wetrakr" : provider === "TRAKT"
      ? "trakt"
      : provider === "SIMKL"
        ? "simkl"
        : provider === "MDBLIST"
          ? "mdblist"
          : traktToken
        ? "trakt"
        : simklToken
          ? "simkl"
          : hasMdbList
            ? "mdblist"
            : "auto";
  const trackingPreferences: TrackingPreferences = {
    watchlistReadMode: normalizeTrackingReadMode(selection.watchlistReadMode, defaultMode),
    continueWatchingReadMode: normalizeTrackingReadMode(selection.continueWatchingReadMode, defaultMode),
    watchedReadMode: normalizeTrackingReadMode(selection.watchedReadMode, defaultMode),
    writeToTrakt: typeof selection.writeToTrakt === "boolean" ? selection.writeToTrakt : Boolean(traktToken),
    writeToWeTrakr: typeof selection.writeToWeTrakr === "boolean" ? selection.writeToWeTrakr : undefined,
    writeToSimkl: typeof selection.writeToSimkl === "boolean" ? selection.writeToSimkl : Boolean(simklToken)
  };
  const hasCloudState = explicitProvider !== null || storedProviderCount > 0;
  const needsCleanup = !hasCloudState
    ? false
    : explicitProvider === null
    ? storedProviderCount > 0
    : explicitProvider !== provider;

  return {
    provider,
    traktToken,
    mdbListApiKey,
    mdbListAccessToken,
    mdbListRefreshToken,
    mdbListTokenExpiresAt,
    simklToken,
    trackingPreferences,
    hasCloudState,
    needsCleanup
  };
}

export async function saveCloudTrackingSelection(
  auth: AuthClient,
  profileId: string | null | undefined,
  selection: CloudTrackingSelection
) {
  if (!profileId) return;
  await mutateCloudPayload(auth, (root) => {
    const traktTokens = objectRecord<Record<string, unknown>>(root.traktTokens);
    const simklTokens = objectRecord<Record<string, unknown>>(root.simklTokens);
    const selections = objectRecord<Record<string, unknown>>(root.mdbListSyncByProfile);
    const changedDomains = new Set<CloudTrackingDomain>(
      selection.changedDomains ?? ["routing", "trakt", "simkl", "mdblist"]
    );
    const updatedAt = Date.now();
    const previousSelection = selections[profileId] ?? {};
    const previousTraktToken = traktTokens[profileId] ?? {};
    const incomingTraktAccessToken = selection.traktToken?.access_token;
    const previousTraktAccessToken = previousTraktToken.accessToken ?? previousTraktToken.access_token;
    const writeTraktCredential = changedDomains.has("trakt") || Boolean(
      incomingTraktAccessToken && incomingTraktAccessToken !== previousTraktAccessToken
    );
    const incomingSimklAccessToken = selection.simklToken?.access_token;
    const previousSimklAccessToken = previousSelection.simklAccessToken;
    const writeSimklCredential = !selection.simklToken?.server_session && !selection.simklToken?.refresh_token && !incomingSimklAccessToken?.startsWith("simkl_at_") && !incomingSimklAccessToken?.startsWith("simkl_session_") && (changedDomains.has("simkl") || Boolean(
      incomingSimklAccessToken && incomingSimklAccessToken !== previousSimklAccessToken
    ));
    const defaultMode: TrackingReadMode = selection.provider === "TRAKT"
        ? "trakt"
        : selection.provider === "SIMKL"
          ? "simkl"
          : selection.provider === "MDBLIST"
            ? "mdblist"
            : selection.traktToken
              ? "trakt"
              : selection.simklToken
                ? "simkl"
                : selection.mdbListApiKey
                  ? "mdblist"
                  : "auto";
    const preferences = selection.trackingPreferences ?? {
      watchlistReadMode: defaultMode,
      continueWatchingReadMode: defaultMode,
      watchedReadMode: defaultMode,
      writeToTrakt: Boolean(selection.traktToken),
      writeToSimkl: Boolean(selection.simklToken)
    };

    if (writeTraktCredential && selection.traktToken) {
      const token = selection.traktToken;
      traktTokens[profileId] = {
        accessToken: token.access_token,
        refreshToken: token.refresh_token,
        expiresAt: token.expires_at,
        access_token: token.access_token,
        refresh_token: token.refresh_token,
        expires_at: token.expires_at,
        updatedAt
      };
    } else if (writeTraktCredential) {
      traktTokens[profileId] = { updatedAt };
    }
    if (writeSimklCredential && selection.simklToken?.access_token) {
      const token = selection.simklToken;
      simklTokens[profileId] = {
        access_token: token.access_token,
        accessToken: token.access_token,
        updatedAt
      };
    } else if (writeSimklCredential) {
      simklTokens[profileId] = { updatedAt };
    }

    const nextSelection = { ...previousSelection };
    if (changedDomains.has("routing")) {
      Object.assign(nextSelection, {
        provider: selection.provider,
        watchlistReadMode: preferences.watchlistReadMode.toUpperCase(),
        continueWatchingReadMode: preferences.continueWatchingReadMode.toUpperCase(),
        watchedReadMode: preferences.watchedReadMode.toUpperCase(),
        writeToTrakt: preferences.writeToTrakt,
        writeToSimkl: preferences.writeToSimkl,
        writeToWeTrakr: preferences.writeToWeTrakr,
        updatedAt
      });
    }
    if (changedDomains.has("mdblist")) {
      const key = selection.mdbListApiKey?.trim();
      if (key) nextSelection.mdbListApiKey = key;
      else delete nextSelection.mdbListApiKey;

      const accessToken = selection.mdbListAccessToken?.trim();
      if (accessToken) nextSelection.mdbListAccessToken = accessToken;
      else delete nextSelection.mdbListAccessToken;

      const refreshToken = selection.mdbListRefreshToken?.trim();
      if (refreshToken) nextSelection.mdbListRefreshToken = refreshToken;
      else delete nextSelection.mdbListRefreshToken;

      const expiresAt = selection.mdbListTokenExpiresAt;
      if (typeof expiresAt === "number" && expiresAt > 0) nextSelection.mdbListTokenExpiresAt = expiresAt;
      else delete nextSelection.mdbListTokenExpiresAt;

      nextSelection.mdbListCredentialUpdatedAt = updatedAt;
    }
    if (writeSimklCredential) {
      const token = selection.simklToken?.access_token;
      if (token) nextSelection.simklAccessToken = token;
      else delete nextSelection.simklAccessToken;
      nextSelection.simklCredentialUpdatedAt = updatedAt;
    }
    selections[profileId] = nextSelection;

    root.traktTokens = traktTokens;
    root.traktLinked = Object.values(traktTokens).some((token) =>
      Boolean(token?.accessToken ?? token?.access_token)
    );
    root.simklTokens = simklTokens;
    root.mdbListSyncByProfile = selections;
  });
}

export async function pullCloudWatchlist(auth: AuthClient, profileId?: string | null): Promise<MediaItem[]> {
  const userId = auth.session?.userId;
  const root = await pullRawPayload(auth);
  if (auth.session?.userId !== userId) return [];
  const byProfile = objectRecord<unknown>(root.watchlistByProfile);
  const changesByProfile = objectRecord<unknown>(root.watchlistChangesByProfile);
  const profiles = profileId ? [profileId] : [...new Set([...Object.keys(byProfile), ...Object.keys(changesByProfile)])];
  const items = profiles.flatMap((id) => {
    const changes = rememberWatchlistChanges(auth, id, changesByProfile[id]);
    return mergeWatchlistItems(changes, byProfile[id]);
  });
  return items
    .map((item): MediaItem | null => {
      const id = Number(item.tmdbId ?? 0);
      if (!id) return null;
      const mediaType = String(item.mediaType ?? "movie").toLowerCase() === "tv" ? "tv" : "movie";
      return {
        id,
        title: item.title ?? "Untitled",
        mediaType,
        activityAt: item.addedAt,
        subtitle: mediaType === "tv" ? "TV Series" : "Movie",
        image: tmdbImageUrl(config.imageBase, item.posterPath),
        backdrop: tmdbImageUrl(config.backdropBase, item.backdropPath) || null
      };
    })
    .filter((item): item is MediaItem => Boolean(item));
}

function rememberWatchlistChanges(auth: AuthClient, profileId: string, incoming: unknown): WatchlistChanges {
  if (!auth.session) return mergeWatchlistChanges(incoming);
  const key = watchlistChangesStorageKey(auth.session.userId, profileId);
  const changes = mergeWatchlistChanges(loadStored<WatchlistChanges>(key, {}), incoming);
  saveStored(key, changes);
  return changes;
}

export async function saveCloudWatchlist(
  auth: AuthClient,
  items: MediaItem[],
  profileId?: string | null,
  options: { changes?: WatchlistChanges } = {}
) {
  if (!profileId) return;
  await mutateCloudPayload(auth, (root) => {
    const byProfile = objectRecord<unknown>(root.watchlistByProfile);
    const changesByProfile = objectRecord<unknown>(root.watchlistChangesByProfile);
    // Membership changes come only from explicit actions. An incomplete local
    // list cannot delete remote additions or revive a retained removal record.
    const changes = rememberWatchlistChanges(auth, profileId,
      mergeWatchlistChanges(changesByProfile[profileId], options.changes));
    const incoming = items.map((item, index): AndroidWatchlistItem => ({
      tmdbId: item.id,
      mediaType: item.mediaType,
      title: item.title,
      posterPath: item.image || null,
      backdropPath: item.backdrop || null,
      addedAt: item.activityAt ?? changes[watchlistKey(item.mediaType, item.id) ?? '']?.updatedAt ?? 0,
      sourceOrder: index
    }));
    byProfile[profileId] = mergeWatchlistItems(changes, incoming, byProfile[profileId]);
    changesByProfile[profileId] = changes;
    root.watchlistByProfile = byProfile;
    root.watchlistChangesByProfile = changesByProfile;
  });
}

export async function pullCloudWatchedKeys(auth: AuthClient, profileId?: string | null): Promise<Set<string>> {
  const root = await pullRawPayload(auth);
  const keys = new Set<string>();
  const movieProfiles = objectRecord<unknown>(root.localWatchedMoviesByProfile);
  const episodeProfiles = objectRecord<unknown>(root.localWatchedEpisodesByProfile);
  const movies = profileId
    ? arrayValue<number>(movieProfiles[profileId])
    : Object.values(movieProfiles).flatMap((value) => arrayValue<number>(value));
  const episodes = profileId
    ? arrayValue<string>(episodeProfiles[profileId])
    : Object.values(episodeProfiles).flatMap((value) => arrayValue<string>(value));
  movies.forEach((id) => {
    const value = Number(id);
    if (value > 0) keys.add(`movie:${value}`);
  });
  episodes.forEach((value) => {
    const match = /^show_tmdb:(\d+):(\d+):(\d+)$/.exec(String(value));
    if (match) keys.add(`tv:${match[1]}:${match[2]}:${match[3]}`);
  });
  return keys;
}

export async function pullCloudContinueWatchingDismissals(
  auth: AuthClient,
  profileId?: string | null
): Promise<Map<string, number>> {
  const root = await pullRawPayload(auth);
  const byProfile = objectRecord<unknown>(root.dismissedContinueWatchingByProfile);
  if (profileId) return parseDismissedContinueWatching(byProfile[profileId]);
  const merged = new Map<string, number>();
  Object.values(byProfile).forEach((value) => {
    parseDismissedContinueWatching(value).forEach((timestamp, key) => {
      merged.set(key, Math.max(timestamp, merged.get(key) ?? 0));
    });
  });
  return merged;
}

/**
 * Saves this browser's profiles into the account, merged with the list already there (see
 * [mergeCloudProfiles]); [deletedIds] are the profiles the user deleted here.
 */
export async function saveCloudProfiles(
  auth: AuthClient,
  profiles: Profile[],
  activeProfileId: string | null,
  deletedIds: string[] = []
) {
  await mutateCloudPayload(auth, (root) => {
    const deleted = [...new Set([...deletedProfileIds(root), ...deletedIds])].slice(-DELETED_PROFILES_LIMIT);
    const merged = mergeCloudProfiles(arrayValue<Profile>(root.profiles), profiles, new Set(deleted));
    root.profiles = merged;
    if (deleted.length) root.deletedProfileIds = deleted;
    root.activeProfileId = merged.some(profile => profile.id === activeProfileId)
      ? activeProfileId
      : merged[0]?.id ?? null;
  });
}

/** Add imported profiles without asserting a stale copy of the account's list. */
export async function addCloudProfiles(auth: AuthClient, additions: Profile[]): Promise<Profile[]> {
  if (!auth.session) throw new Error("Sign in to your ARVIO account first");
  let profiles: Profile[] = [];
  await mutateCloudPayload(auth, (root) => {
    if (root.profiles != null && !Array.isArray(root.profiles)) {
      throw new Error("Could not read your ARVIO profiles");
    }
    profiles = [...arrayValue<Profile>(root.profiles)];
    const ids = new Set(profiles.map(profile => profile.id));
    for (const profile of additions) {
      if (ids.has(profile.id)) continue;
      profiles.push(profile);
      ids.add(profile.id);
    }
    root.profiles = profiles;
    if (!profiles.some(profile => profile.id === root.activeProfileId)) {
      root.activeProfileId = profiles[0]?.id ?? null;
    }
  });
  return profiles;
}

const liveAddonIdMarkers = [
  "livetv",
  "live_tv",
  "live-tv",
  "live_stream",
  "livestream",
  "live-stream",
  "tvchannels",
  "tv-channels",
  "iptv",
  "sports"
];

// Internal stream providers whose IDs contain "iptv" but serve on-demand
// movies and episodes. Keep this exact so regular IPTV add-ons remain live.
const explicitVodStreamAddonIds = new Set(["iptv_xtream_vod"]);

const vodTypes = new Set(["movie", "film", "series", "show", "anime"]);
const liveTypes = new Set(["tv", "channel", "channels", "live", "sport", "sports", "tvchannel", "live_tv", "livestream"]);

function addonTypes(addon: InstalledAddon): string[] {
  return [
    ...(addon.types ?? []),
    ...addon.catalogs.map((catalog) => catalog.type),
    ...addon.resources.flatMap((resource) => typeof resource === "string" ? [] : resource.types ?? [])
  ].map((type) => type.trim().toLowerCase());
}

function isLiveAddonId(value?: string | null): boolean {
  const id = (value ?? "").trim().toLowerCase();
  if (explicitVodStreamAddonIds.has(id)) return false;
  if (!id || id.includes("cinemeta") || id.includes("tmdb") || id.includes("torrentio")) return false;
  return liveAddonIdMarkers.some((marker) => id.includes(marker));
}

function isLiveOnlyAddon(addon: InstalledAddon): boolean {
  const types = addonTypes(addon);
  if (types.some((type) => vodTypes.has(type))) return false;
  if (isLiveAddonId(addon.id) || isLiveAddonId(addon.name)) return true;
  if (types.some((type) => liveTypes.has(type))) return true;

  const resourceNames = addon.resources.map((resource) =>
    (typeof resource === "string" ? resource : resource.name).trim().toLowerCase()
  );
  const hasStream = resourceNames.some((name) => name === "stream" || name === "streams");
  const hasCatalog = addon.catalogs.length > 0 ||
    resourceNames.some((name) => name === "catalog" || name === "catalogs");
  const text = [
    addon.id,
    addon.name,
    addon.description ?? "",
    ...addon.catalogs.flatMap((catalog) => [catalog.type, catalog.id, catalog.name])
  ].join(" ").toLowerCase();
  const hasSportsTerm = /(sport|football|soccer|basketball|tennis|racing|rugby|hockey|baseball|boxing|ufc|mma|cricket|golf)/.test(text);
  const hasLiveTerm = /(live|event|match|game)/.test(text);
  return hasStream && hasCatalog && hasSportsTerm && hasLiveTerm;
}

export function isLiveStreamOrSportsItem(item: {
  addonNativeId?: string | null;
  addonNativeAddonId?: string | null;
  addon_native_id?: string | null;
  addon_native_addon_id?: string | null;
  mediaType?: string | null;
  media_type?: string | null;
  id?: number | string | null;
  show_tmdb_id?: number | string | null;
  streamAddonId?: string | null;
  stream_addon_id?: string | null;
  title?: string | null;
}, addons: InstalledAddon[] = []): boolean {
  const mediaType = (item.mediaType ?? item.media_type ?? "").toLowerCase();
  const rawId = item.show_tmdb_id ?? item.id;
  const id = typeof rawId === "number" ? rawId : Number(rawId ?? 0);

  const addonId = (item.streamAddonId ?? item.stream_addon_id ?? "").toLowerCase();
  const title = (item.title ?? "").toLowerCase();

  if (mediaType && mediaType !== "movie" && mediaType !== "tv") {
    return true;
  }
  if (id < 0 && isYouTubeItem({
    id, addonNativeId: item.addonNativeId ?? item.addon_native_id,
    addonNativeAddonId: item.addonNativeAddonId ?? item.addon_native_addon_id
  })) return false;
  if (!id || id <= 0) {
    return true;
  }
  if (addonId) {
    const addon = addons.find((candidate) => candidate.id.toLowerCase() === addonId);
    if (addon) {
      if (isLiveOnlyAddon(addon)) return true;
    } else if (isLiveAddonId(addonId)) {
      return true;
    }
  }
  if (title.startsWith("live:") || title.startsWith("[live]")) {
    return true;
  }
  return false;
}

// ── Continue Watching progress (parity with Android TraktRepository/PlayerViewModel) ──

/** Android Constants.MIN_PROGRESS_THRESHOLD (3%) or 60 s played: shorter plays are accidental taps. */
const MIN_PROGRESS = 0.03;
const MIN_POSITION_SECONDS = 60;
/** Android Constants.WATCHED_THRESHOLD (90%). */
const WATCHED_PROGRESS = 0.9;
const PROGRESS_ENDPOINT = "account-sync-progress";
// Set NEXT_PUBLIC_ARVIO_PROGRESS_ENDPOINT=false to send every checkpoint through
// the full-document write (the behaviour before account-sync-progress existed).
const progressEndpointEnabled = process.env.NEXT_PUBLIC_ARVIO_PROGRESS_ENDPOINT !== "false";
const ENDPOINT_MISSING_KEY = "arvio.web.progressEndpointMissing.v1";
const ENDPOINT_RECHECK_MS = 60 * 60_000;
const COMPLETION_REPEAT_MS = 30 * 60_000;
const recentCompletions = new Map<string, number>();

function progressEndpointAvailable() {
  if (!progressEndpointEnabled) return false;
  const missingAt = Number(loadStored<number>(ENDPOINT_MISSING_KEY, 0)) || 0;
  return !missingAt || Date.now() - missingAt > ENDPOINT_RECHECK_MS;
}

/** Same row rules as the server (account-sync-progress), for the full-document fallback. */
function applyProgressOperationToRoot(root: RawPayload, operation: ProgressOperation) {
  const { profileId } = operation;
  if (deletedProfileIds(root).includes(profileId)) return;
  const byProfile = objectRecord<unknown>(root.localContinueWatchingByProfile);
  const current = arrayValue<AndroidContinueWatchingItem>(byProfile[profileId]);
  const updatedAt = (row: AndroidContinueWatchingItem) => Number(row.updatedAtMs ?? 0) || 0;
  const sameEpisode = (row: AndroidContinueWatchingItem, season?: number | null, episode?: number | null) =>
    (row.season ?? null) === (season ?? null) && (row.episode ?? null) === (episode ?? null);
  if (operation.op === "remove") {
    const key = continueWatchingShowKey(operation.match);
    const exact = operation.match.mediaType === "tv" && operation.match.season != null && operation.match.episode != null;
    byProfile[profileId] = current.filter((row) => continueWatchingShowKey(row) !== key ||
      updatedAt(row) > operation.updatedAtMs ||
      (exact && !sameEpisode(row, operation.match.season, operation.match.episode)));
    root.localContinueWatchingByProfile = byProfile;
    return;
  }
  const { item } = operation;
  const key = continueWatchingShowKey(item);
  const sameShow = current.filter((row) => continueWatchingShowKey(row) === key);
  if (sameShow.some((row) => updatedAt(row) > item.updatedAtMs)) return;
  const previous = sameShow.find((row) => sameEpisode(row, item.season, item.episode)) as Record<string, unknown> | undefined;
  const merged: Record<string, unknown> = { ...(previous ?? {}) };
  for (const [field, value] of Object.entries(item)) {
    if (value === undefined) continue;
    if (value !== null || !previous || previous[field] == null) merged[field] = value;
  }
  byProfile[profileId] = [merged as AndroidContinueWatchingItem, ...current.filter((row) => continueWatchingShowKey(row) !== key)]
    .sort((a, b) => updatedAt(b) - updatedAt(a))
    .slice(0, 50);
  root.localContinueWatchingByProfile = byProfile;
  const { showKey, exactKey } = continueWatchingDismissalKeys({
    id: item.id, mediaType: item.mediaType, seasonNumber: item.season, episodeNumber: item.episode
  });
  updateProfileDismissals(root, profileId, (values) => {
    for (const dismissalKey of [showKey, exactKey]) {
      if ((values.get(dismissalKey) ?? Infinity) <= item.updatedAtMs) values.delete(dismissalKey);
    }
  });
}

function errorStatus(error: unknown) {
  const status = Number((error as { status?: unknown } | null)?.status);
  return Number.isFinite(status) ? status : 0;
}

async function sendProgressOperation(auth: AuthClient, userId: string, operation: ProgressOperation, keepalive: boolean): Promise<ProgressSendResult | "unreachable"> {
  if (!progressEndpointAvailable()) return "fallback";
  // A still-valid token is used as is, so a send started while the page is
  // being hidden or closed is issued without waiting on a refresh.
  const session = auth.session;
  if (!session || session.userId !== userId) throw new Error("Account changed before progress was saved");
  const token = session.accessToken && session.expiresAt - Date.now() > 60_000 ? session.accessToken : await auth.accessToken();
  let result: { accepted?: boolean; applied?: boolean; reason?: string } | undefined;
  try {
    // text/plain + token in the body keeps this a CORS "simple" request: no
    // preflight, which keepalive requests on closing pages may not get.
    result = await jsonRequest<{ accepted?: boolean; applied?: boolean; reason?: string }>(
      `${config.netlifyBackendUrl.replace(/\/+$/, "")}/${PROGRESS_ENDPOINT}`,
      {
        method: "POST",
        keepalive,
        headers: { "Content-Type": "text/plain;charset=UTF-8" },
        body: JSON.stringify({ ...operation, accessToken: token })
      }
    );
  } catch (error) {
    const status = errorStatus(error);
    // Backend without this endpoint yet: use the full-document write for a while.
    if (status === 404 || status === 405) {
      saveStored(ENDPOINT_MISSING_KEY, Date.now());
      return "fallback";
    }
    // No account document to merge into, or a lost write race: the full write handles both.
    if (status === 409) return "fallback";
    // The server rejected this change as malformed; retrying cannot help.
    if (status === 400 || status === 413) return "drop";
    // No HTTP status: offline, timed out, or a response the browser hid. A host
    // without this function answers 404 without CORS headers, which surfaces
    // here too, so try the full-document write. Offline, that fails as well
    // and the change stays queued.
    if (!status) return "unreachable";
    throw error;
  }
  // "applied: false" (an older checkpoint, a deleted profile) is still an answer: nothing to retry.
  if (result?.accepted !== true) throw new Error(result?.reason || "Progress was not accepted");
  return "done";
}

function progressTransport(auth: AuthClient, userId: string) {
  let unreachable = false;
  return {
    send: async (operation: ProgressOperation, options: { keepalive: boolean }): Promise<ProgressSendResult> => {
      // A send from a closing page cannot wait behind other writes.
      const result = options.keepalive
        ? await sendProgressOperation(auth, userId, operation, true)
        : await runInMutationQueue(userId, () => sendProgressOperation(auth, userId, operation, false));
      unreachable = result === "unreachable";
      return unreachable ? "fallback" : result as ProgressSendResult;
    },
    fallback: async (operation: ProgressOperation) => {
      await mutateCloudPayload(auth, (root) => applyProgressOperationToRoot(root, operation));
      // The account backend answered but the progress endpoint did not: treat it as missing for a while.
      if (unreachable) saveStored(ENDPOINT_MISSING_KEY, Date.now());
    },
    isCurrent: () => auth.session?.userId === userId
  };
}

/** Send queued progress now. Failures leave it queued for the next attempt. */
export function flushPendingProgress(auth: AuthClient): Promise<void> {
  const userId = auth.session?.userId;
  if (!userId || !canUseBackendSync(auth)) return Promise.resolve();
  installProgressRetry(userId, () => { void flushPendingProgress(auth).catch(() => undefined); });
  return flushProgressOutbox(userId, progressTransport(auth, userId));
}

/** Overlay unsent local progress onto a cloud read so this device shows what it just watched. */
function withPendingProgress(items: AndroidContinueWatchingItem[], userId: string, profileId?: string | null) {
  const pending = pendingProgress(userId).filter((entry) => !profileId || entry.operation.profileId === profileId);
  if (!pending.length) return items;
  const byShow = new Map(items.map((item) => [continueWatchingShowKey(item), item]));
  for (const { operation } of pending) {
    if (operation.op === "upsert") {
      const key = continueWatchingShowKey(operation.item);
      const current = byShow.get(key);
      if (!current || Number(current.updatedAtMs ?? 0) <= operation.item.updatedAtMs) {
        const previous = current && (current.season ?? null) === (operation.item.season ?? null) &&
          (current.episode ?? null) === (operation.item.episode ?? null) ? current : {};
        const merged: Record<string, unknown> = { ...previous };
        for (const [field, value] of Object.entries(operation.item)) {
          if (value !== null && value !== undefined) merged[field] = value;
        }
        byShow.set(key, merged as AndroidContinueWatchingItem);
      }
    } else {
      const key = continueWatchingShowKey(operation.match);
      const current = byShow.get(key);
      const exact = operation.match.mediaType === "tv" && operation.match.season != null && operation.match.episode != null;
      if (current && Number(current.updatedAtMs ?? 0) <= operation.updatedAtMs &&
          (!exact || ((current.season ?? null) === operation.match.season && (current.episode ?? null) === operation.match.episode))) {
        byShow.delete(key);
      }
    }
  }
  return [...byShow.values()];
}

export async function getContinueWatching(auth: AuthClient, profileId?: string | null, addons: InstalledAddon[] = []) {
  if (!auth.session) return [];
  if (canUseBackendSync(auth)) {
    const userId = auth.session.userId;
    // Progress saved while offline or while the app was closing goes out now.
    void flushPendingProgress(auth).catch(() => undefined);
    const root = await pullRawPayload(auth);
    return withPendingProgress(androidContinueWatchingItems(root, profileId), userId, profileId)
      .map((item) => androidCwToHistory(item, profileId))
      .filter((item): item is WatchHistoryEntry => Boolean(item))
      .filter((item) => (item.progress ?? 0) < 0.9 && !isLiveStreamOrSportsItem(item, addons))
      .sort((a, b) => Date.parse(b.updated_at ?? "") - Date.parse(a.updated_at ?? ""))
      .slice(0, 50);
  }
  const profileFilter = profileId ? `&profile_id=eq.${encodeURIComponent(profileId)}` : "";
  const records = await auth.supabase<WatchHistoryEntry[]>(
    `/rest/v1/watch_history?user_id=eq.${auth.session.userId}${profileFilter}&progress=lt.0.9&select=*&order=updated_at.desc&limit=50`
  );
  return records.filter((item) => !isLiveStreamOrSportsItem(item, addons));
}

const defaultSeasonLookup: SeasonEpisodesLookup = async (tvId, season) => {
  const { getSeasonEpisodes } = await import("./tmdb");
  return getSeasonEpisodes(tvId, season);
};

export interface SaveProgressOptions {
  /** Season episode list used to find the episode after a finished one (defaults to TMDB). */
  seasonEpisodes?: SeasonEpisodesLookup;
}

/**
 * Saves a playback checkpoint the way Android's player does:
 * - under 3% and under 60 s nothing is saved (an accidental play creates no row);
 * - one row per show: a new episode replaces the show's previous row;
 * - at 90% the title is finished: a movie leaves Continue Watching, a TV episode
 *   is replaced by the next episode as an "up next" row (persistNextEpisodeAfterCompletion);
 * - stream_key and other fields this browser does not know are kept.
 * The change is queued durably (progressOutbox) and sent as one small item to
 * account-sync-progress, falling back to the full-document write.
 */
export async function saveProgress(
  auth: AuthClient,
  entry: Omit<WatchHistoryEntry, "user_id">,
  profileId?: string | null,
  addons: InstalledAddon[] = [],
  options: SaveProgressOptions = {}
) {
  if (!auth.session || isLiveStreamOrSportsItem(entry, addons)) return;
  if (canUseBackendSync(auth)) {
    const userId = auth.session.userId;
    const targetProfileId = entry.profile_id ?? profileId ?? "default";
    const progress = Math.max(0, Math.min(1, Number(entry.progress) || 0));
    const position = Math.max(0, Number(entry.position_seconds) || 0);
    const mediaType = entry.media_type === "tv" ? "tv" : "movie";
    if (progress < WATCHED_PROGRESS) {
      if (progress < MIN_PROGRESS && position < MIN_POSITION_SECONDS) return;
      queueProgress(userId, {
        profileId: targetProfileId,
        op: "upsert",
        item: historyToAndroidCw({ ...entry, profile_id: targetProfileId })
      });
      return flushPendingProgress(auth);
    }

    // Finished. Periodic checkpoints during the credits must not repeat this.
    const completionKey = `${userId}|${targetProfileId}|${mediaType}:${entry.show_tmdb_id}:${entry.season ?? ""}:${entry.episode ?? ""}`;
    const completedAt = recentCompletions.get(completionKey) ?? 0;
    if (Date.now() - completedAt < COMPLETION_REPEAT_MS) return flushPendingProgress(auth);
    recentCompletions.set(completionKey, Date.now());
    if (recentCompletions.size > 200) recentCompletions.delete(recentCompletions.keys().next().value!);
    // Removing the finished title goes out first, so closing the app now still records it.
    queueProgress(userId, {
      profileId: targetProfileId,
      op: "remove",
      match: { id: entry.show_tmdb_id, mediaType, season: entry.season ?? null, episode: entry.episode ?? null },
      updatedAtMs: Date.now()
    });
    const removal = flushPendingProgress(auth);
    if (mediaType !== "tv" || entry.season == null || entry.episode == null) return removal;
    const next = await findNextEpisode(entry.show_tmdb_id, entry.season, entry.episode, options.seasonEpisodes ?? defaultSeasonLookup)
      .catch(() => null);
    if (next && auth.session?.userId === userId) {
      const item: ProgressItem = {
        ...(entry.addon_native_id ? { addonNativeId: entry.addon_native_id, addonNativeAddonId: entry.addon_native_addon_id } : {}),
        id: entry.show_tmdb_id,
        title: entry.title?.trim() || "Untitled",
        mediaType: "tv",
        progress: 0,
        resumePositionSeconds: 0,
        durationSeconds: 0,
        season: next.seasonNumber,
        episode: next.episodeNumber,
        displaySeason: next.seasonNumber,
        displayEpisode: next.episodeNumber,
        episodeTitle: next.name || null,
        backdropPath: entry.backdrop_path ?? null,
        posterPath: entry.poster_path ?? null,
        isUpNext: true,
        releaseDate: next.airDate ?? "",
        updatedAtMs: Date.now()
      };
      // Replaces the queued removal if it has not been sent; otherwise follows it.
      queueProgress(userId, { profileId: targetProfileId, op: "upsert", item });
    }
    await removal.catch(() => undefined);
    return flushPendingProgress(auth);
  }
  const { addon_native_id: _nativeId, addon_native_addon_id: _nativeAddonId, ...databaseEntry } = entry;
  await auth.supabase("/rest/v1/watch_history", {
    method: "POST",
    headers: { Prefer: "resolution=merge-duplicates" },
    body: JSON.stringify({
      ...databaseEntry,
      user_id: auth.session.userId,
      profile_id: entry.profile_id ?? profileId ?? null,
      paused_at: new Date().toISOString(),
      updated_at: new Date().toISOString()
    })
  });
}

export async function removeContinueWatchingProgress(
  auth: AuthClient,
  item: Pick<MediaItem, "id" | "mediaType" | "seasonNumber" | "episodeNumber">,
  profileId?: string | null
) {
  if (!auth.session) return;
  const matches = (candidate: AndroidContinueWatchingItem) => {
    if (candidate.id !== item.id || String(candidate.mediaType ?? "").toLowerCase() !== item.mediaType) return false;
    if (item.mediaType !== "tv") return true;
    if (item.seasonNumber != null && candidate.season !== item.seasonNumber) return false;
    if (item.episodeNumber != null && candidate.episode !== item.episodeNumber) return false;
    return true;
  };

  if (canUseBackendSync(auth)) {
    // An unsent checkpoint for this title must not bring the row back later.
    discardPendingProgress(auth.session.userId, profileId ?? "default", item.mediaType, item.id);
    await mutateCloudPayload(auth, (root) => {
      const targetProfileId = profileId ?? "default";
      for (const key of ["localContinueWatchingByProfile", "continueWatchingByProfile"]) {
        const byProfile = objectRecord<unknown>(root[key]);
        byProfile[targetProfileId] = arrayValue<AndroidContinueWatchingItem>(byProfile[targetProfileId]).filter((candidate) => !matches(candidate));
        root[key] = byProfile;
      }
      for (const key of ["localContinueWatching", "continueWatching"]) {
        root[key] = arrayValue<AndroidContinueWatchingItem>(root[key]).filter((candidate) => !matches(candidate));
      }
      const { showKey, exactKey } = continueWatchingDismissalKeys(item);
      updateProfileDismissals(root, targetProfileId, (values) => {
        const now = Date.now();
        values.set(showKey, now);
        values.set(exactKey, now);
      });
    });
    return;
  }

  const query = new URLSearchParams({
    user_id: `eq.${auth.session.userId}`,
    media_type: `eq.${item.mediaType}`,
    show_tmdb_id: `eq.${item.id}`
  });
  query.set("profile_id", profileId ? `eq.${profileId}` : "is.null");
  if (item.seasonNumber != null) query.set("season", `eq.${item.seasonNumber}`);
  if (item.episodeNumber != null) query.set("episode", `eq.${item.episodeNumber}`);
  await auth.supabase(`/rest/v1/watch_history?${query.toString()}`, { method: "DELETE" });
  await mutateCloudPayload(auth, (root) => {
    const targetProfileId = profileId ?? "default";
    const { showKey, exactKey } = continueWatchingDismissalKeys(item);
    updateProfileDismissals(root, targetProfileId, (values) => {
      const now = Date.now();
      values.set(showKey, now);
      values.set(exactKey, now);
    });
  });
}

export async function saveWatchedState(
  auth: AuthClient,
  item: Pick<MediaItem, "id" | "mediaType" | "seasonNumber" | "episodeNumber">,
  watched: boolean,
  profileId?: string | null
) {
  if (!auth.session) return;
  const targetProfileId = profileId ?? "default";
  // Android removeFromLocalContinueWatching: only the watched episode's row goes,
  // so the "up next" row written for the following episode stays.
  const isWatchedRow = (candidate: { id?: number; mediaType?: string; season?: number | null; episode?: number | null }) =>
    candidate.id === item.id && String(candidate.mediaType ?? "").toLowerCase() === item.mediaType &&
    (item.mediaType !== "tv" || item.seasonNumber == null || candidate.season === item.seasonNumber) &&
    (item.mediaType !== "tv" || item.episodeNumber == null || candidate.episode === item.episodeNumber);
  if (watched) {
    discardPendingProgress(auth.session.userId, targetProfileId, item.mediaType, item.id, {
      matches: (operation) => operation.op === "upsert" && isWatchedRow(operation.item)
    });
  }
  await mutateCloudPayload(auth, (root) => {
    if (item.mediaType === "movie") {
      const byProfile = objectRecord<unknown>(root.localWatchedMoviesByProfile);
      const ids = new Set(arrayValue<number>(byProfile[targetProfileId]).map(Number).filter((id) => id > 0));
      if (watched) ids.add(item.id); else ids.delete(item.id);
      byProfile[targetProfileId] = [...ids].sort((a, b) => a - b);
      root.localWatchedMoviesByProfile = byProfile;
    } else if (item.seasonNumber != null && item.episodeNumber != null) {
      const byProfile = objectRecord<unknown>(root.localWatchedEpisodesByProfile);
      const episodeKey = `show_tmdb:${item.id}:${item.seasonNumber}:${item.episodeNumber}`;
      const keys = new Set(arrayValue<string>(byProfile[targetProfileId]).map(String).filter(Boolean));
      if (watched) keys.add(episodeKey); else keys.delete(episodeKey);
      byProfile[targetProfileId] = [...keys].sort();
      root.localWatchedEpisodesByProfile = byProfile;
    }

    if (watched) {
      const byProfile = objectRecord<unknown>(root.localContinueWatchingByProfile);
      byProfile[targetProfileId] = arrayValue<AndroidContinueWatchingItem>(byProfile[targetProfileId])
        .filter((candidate) => !isWatchedRow(candidate));
      root.localContinueWatchingByProfile = byProfile;
    }
  });
}

export async function markWatched(auth: AuthClient, entry: Omit<WatchHistoryEntry, "user_id" | "progress" | "position_seconds">, profileId?: string | null) {
  await saveWatchedState(auth, {
    id: entry.show_tmdb_id,
    mediaType: entry.media_type,
    seasonNumber: entry.season,
    episodeNumber: entry.episode
  }, true, profileId);
}

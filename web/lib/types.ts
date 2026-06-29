export type MediaType = "movie" | "tv";

export type NavSection = "home" | "tv" | "search" | "watchlist" | "addons" | "settings";

export interface MediaItem {
  id: number;
  title: string;
  subtitle?: string;
  overview?: string;
  year?: string;
  releaseDate?: string | null;
  rating?: string;
  duration?: string;
  mediaType: MediaType;
  image?: string;
  backdrop?: string | null;
  progress?: number;
  isWatched?: boolean;
  traktId?: number | null;
  badge?: string | null;
  genreIds?: number[];
  nextEpisode?: NextEpisode | null;
  timeRemainingLabel?: string | null;
  trailerUrl?: string | null;
  cast?: PersonCredit[];
  seasons?: SeasonSummary[];
  related?: MediaItem[];
  // Home server (Plex/Jellyfin/Emby) direct playback
  isHomeServer?: boolean;
  homeServerUrl?: string | null;
}

export interface NextEpisode {
  id: number;
  seasonNumber: number;
  episodeNumber: number;
  name: string;
  overview?: string;
}

export interface PersonCredit {
  id: number;
  name: string;
  character?: string;
  image?: string;
}

export interface SeasonSummary {
  id: number;
  seasonNumber: number;
  name: string;
  episodeCount?: number;
  poster?: string;
}

export interface EpisodeInfo {
  id: number;
  episodeNumber: number;
  seasonNumber: number;
  name: string;
  overview?: string;
  still?: string;
  voteAverage?: number;
  airDate?: string;
  runtime?: number;
}

export interface ReviewInfo {
  id: string;
  author: string;
  content: string;
  rating?: number | null;
  createdAt?: string;
  avatar?: string | null;
}

export interface Category {
  id: string;
  title: string;
  items: MediaItem[];
  sourceLabel?: string;
  layout?: "landscape" | "poster";
  sourceUrl?: string;
}

export type CatalogSourceType =
  | "preinstalled"
  | "tmdb"
  | "mdblist"
  | "trakt"
  | "addon"
  | "home-server"
  | "template";

export interface CatalogConfig {
  id: string;
  name: string;
  sourceType: CatalogSourceType;
  mediaType?: MediaType | "all";
  sourceUrl?: string;
  sourceRef?: string;
  endpoint?: string;
  params?: Record<string, string | number | boolean>;
  enabled: boolean;
  isPreinstalled?: boolean;
  layout?: "landscape" | "poster";
}

export interface StreamBehaviorHints {
  notWebReady?: boolean;
  cached?: boolean | null;
  bingeGroup?: string | null;
  proxyHeaders?: {
    request?: Record<string, string>;
    response?: Record<string, string>;
  } | null;
  filename?: string | null;
}

export interface SubtitleTrack {
  id: string;
  url: string;
  lang: string;
  label: string;
  provider?: string;
  isEmbedded?: boolean;
  isForced?: boolean;
}

export interface StreamSource {
  source: string;
  addonName: string;
  addonId?: string;
  quality?: string;
  size?: string;
  sizeBytes?: number | null;
  url?: string | null;
  infoHash?: string | null;
  fileIdx?: number | null;
  behaviorHints?: StreamBehaviorHints | null;
  subtitles?: SubtitleTrack[];
  sources?: string[];
  description?: string | null;
}

export interface InstalledAddon {
  id: string;
  name: string;
  version: string;
  manifestUrl: string;
  description?: string | null;
  catalogs: AddonCatalog[];
  resources: string[];
  logo?: string | null;
  background?: string | null;
  enabled?: boolean;
}

export interface AddonCatalog {
  type: string;
  id: string;
  name: string;
  extra?: Array<{ name: string; isRequired?: boolean; options?: string[] }>;
}

export interface AuthSession {
  accessToken: string;
  refreshToken: string;
  userId: string;
  email: string;
  expiresAt: number;
}

/**
 * Mirrors the Android `Profile` data model (com.arflix.tv.data.model.Profile).
 * Serialized identically inside account_sync_state.payload.profiles, so the web
 * reads/writes the same profiles the Android app created.
 */
export interface Profile {
  id: string;
  name: string;
  avatarColor: number;        // ARGB long, e.g. 0xFFE50914
  avatarId: number;           // 0 = legacy letter+color, 1-84 = fluent emoji avatar
  avatarImageVersion?: number; // 0 = no custom uploaded photo
  avatarImageStoragePath?: string | null;
  isKidsProfile?: boolean;
  pin?: string | null;
  isLocked?: boolean;
  createdAt?: number;
  lastUsedAt?: number;
}

export interface UserProfile {
  id: string;
  email?: string | null;
  addons?: string | null;
  default_subtitle?: string | null;
  auto_play_next?: boolean | null;
}

export interface WatchHistoryEntry {
  id?: string | null;
  user_id: string;
  profile_id?: string | null;
  media_type: MediaType;
  show_tmdb_id: number;
  show_trakt_id?: number | null;
  season?: number | null;
  episode?: number | null;
  title?: string | null;
  episode_title?: string | null;
  progress: number;
  duration_seconds: number;
  position_seconds: number;
  paused_at?: string | null;
  updated_at?: string | null;
  source?: string | null;
  backdrop_path?: string | null;
  poster_path?: string | null;
  stream_key?: string | null;
  stream_addon_id?: string | null;
  stream_title?: string | null;
}

export interface IptvPlaylistEntry {
  id: string;
  name: string;
  m3uUrl: string;
  epgUrl?: string;
  epgUrls?: string[];
  enabled: boolean;
}

export interface IptvChannel {
  id: string;
  name: string;
  group: string;
  logo?: string;
  streamUrl: string;
  tvgId?: string;
  number?: string;
  catchupDays?: number;
  catchupType?: string;
  catchupSource?: string;
  language?: string;
  country?: string;
  qualityLabel?: string;
}

export interface IptvProgram {
  title: string;
  description?: string;
  startUtcMillis: number;
  endUtcMillis: number;
  catchupAvailable?: boolean;
}

export interface IptvNowNext {
  now?: IptvProgram;
  next?: IptvProgram;
  later?: IptvProgram;
  upcoming: IptvProgram[];
  recent: IptvProgram[];
}

export interface IptvSnapshot {
  channels: IptvChannel[];
  grouped: Record<string, IptvChannel[]>;
  nowNext: Record<string, IptvNowNext>;
  favoriteGroups: string[];
  favoriteChannels: string[];
  hiddenGroups: string[];
  groupOrder: string[];
  epgWarning?: string;
  loadedAt: number;
}

export interface HomeServerConfig {
  id: string;
  type: "plex" | "jellyfin" | "emby";
  name: string;
  url: string;
  token?: string;
  username?: string;
  password?: string;
  enabled: boolean;
}

export interface AppSettings {
  // Playback
  autoPlayNext: boolean;
  autoPlaySingleSource: boolean;
  autoPlayMinQuality: "any" | "hd" | "fhd" | "4k";
  trailerAutoPlay: boolean;
  trailerSound: boolean;
  trailerDelaySeconds: number;
  // Language & audio
  language: string;
  defaultSubtitle: string;
  secondarySubtitle: string;
  audioLanguage: string;
  // Subtitles
  subtitleSize: number;
  subtitleColor: string;
  subtitleOffsetMs: number;
  subtitleStyle: "outline" | "shadow" | "background" | "raised";
  subtitleStylized: boolean;
  filterSubtitlesByLanguage: boolean;
  removeHearingImpaired: boolean;
  // AI subtitles
  aiSubtitlesEnabled: boolean;
  aiSubtitleModel: "off" | "groq" | "gemini";
  aiAutoSelect: boolean;
  aiApiKey: string;
  // Appearance
  cardLayoutMode: "landscape" | "poster";
  deviceModeOverride: "auto" | "tv" | "desktop";
  oledBlack: boolean;
  clockFormat: "12h" | "24h";
  showBudget: boolean;
  smoothScrolling: boolean;
  spoilerBlur: boolean;
  accentColor: string;
  // Network
  dnsProvider: "system" | "cloudflare" | "google" | "quad9";
  showLoadingStats: boolean;
  customUserAgent: string;
  // Profiles
  skipProfileSelection: boolean;
  cardDensity: "comfortable" | "compact";
  // Catalogs / addons
  catalogs: CatalogConfig[];
  hiddenCatalogIds: string[];
  disabledAddonIds: string[];
  // Home servers
  homeServers: HomeServerConfig[];
  // IPTV
  iptvPlaylists: IptvPlaylistEntry[];
  favoriteChannelIds: string[];
  favoriteGroupIds: string[];
  hiddenGroupIds: string[];
  groupOrder: string[];
}

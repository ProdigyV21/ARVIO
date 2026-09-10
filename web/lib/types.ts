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
  episodeStill?: string | null;
  progress?: number;
  resumePositionSeconds?: number;
  durationSeconds?: number;
  streamAddonId?: string | null;
  isWatched?: boolean;
  traktId?: number | null;
  imdbId?: string | null;
  badge?: string | null;
  genreIds?: number[];
  genres?: string[];
  status?: string | null;
  budget?: number | null;
  revenue?: number | null;
  originalLanguage?: string | null;
  providerLogos?: Array<{ name: string; logo: string }>;
  networkLogos?: Array<{ name: string; logo: string }>;
  numberOfSeasons?: number | null;
  numberOfEpisodes?: number | null;
  lastAirDate?: string | null;
  nextEpisode?: NextEpisode | null;
  seasonNumber?: number | null;
  episodeNumber?: number | null;
  episodeTitle?: string | null;
  timeRemainingLabel?: string | null;
  // Epoch ms of the last activity (Trakt paused_at / last_watched_at, or cloud
  // history updated_at). Continue Watching is ordered by this, newest first.
  activityAt?: number;
  // Explicit tracker progress reset; show-level activity is not a rewatch.
  progressResetAt?: number;
  trailerUrl?: string | null;
  cast?: PersonCredit[];
  seasons?: SeasonSummary[];
  related?: MediaItem[];
  // Home server (Plex/Jellyfin/Emby) direct playback
  isHomeServer?: boolean;
  homeServerUrl?: string | null;
  homeServerItemId?: string | null;
  homeServerId?: string | null;
  homeServerType?: "plex" | "jellyfin" | "emby" | null;
  tmdbId?: number | null;
  // External metadata IDs & classification
  isAnime?: boolean;
  anilistId?: number | null;
  tvdbId?: number | null;
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

export interface PersonDetails {
  id: number;
  name: string;
  biography: string;
  placeOfBirth?: string | null;
  birthday?: string | null;
  profilePath?: string | null;
  knownFor: MediaItem[];
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
  imdbRating?: string;
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
  title?: string;
  kind?: "STANDARD" | "COLLECTION" | "COLLECTION_RAIL" | "standard" | "collection" | "collection_rail";
  addonId?: string | null;
  addonCatalogType?: string | null;
  addonCatalogId?: string | null;
  addonName?: string | null;
  collectionGroup?: string | null;
  collectionDescription?: string | null;
  collectionTileShape?: "LANDSCAPE" | "POSTER" | "landscape" | "poster";
  collectionHideTitle?: boolean;
  collectionSources?: CollectionSourceConfig[];
  requiredAddonUrls?: string[];
}

export interface CollectionSourceConfig {
  kind: string;
  mediaType?: string | null;
  addonId?: string | null;
  addonCatalogType?: string | null;
  addonCatalogId?: string | null;
  tmdbGenreId?: number | null;
  tmdbPersonId?: number | null;
  tmdbCollectionId?: number | null;
  tmdbKeywordId?: number | null;
  tmdbWatchProviderId?: number | null;
  watchRegion?: string | null;
  sortBy?: string | null;
  curatedRefs?: string[] | null;
  mdblistSlug?: string | null;
  homeServerId?: string | null;
  homeServerLibraryKey?: string | null;
}

export interface StreamBehaviorHints {
  notWebReady?: boolean;
  cached?: boolean | null;
  bingeGroup?: string | null;
  directUrl?: string | null;
  url?: string | null;
  externalUrl?: string | null;
  videoHash?: string | null;
  videoSize?: number | null;
  proxyHeaders?: {
    request?: Record<string, string>;
    response?: Record<string, string>;
  } | null;
  filename?: string | null;
  browserPlayable?: boolean;
  iosPlayable?: boolean;
  externalPlayerRecommended?: boolean;
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
  transport?: "file" | "hls" | "dash" | "mpegts";
  media?: { container?: string; videoCodec?: string; audioCodec?: string; hdr?: string };
  homeServer?: { serverId: string; itemId: string; mediaSourceId?: string; mediaIndex?: number; partIndex?: number };
  playbackSession?: { serverId: string; itemId: string; sessionId: string; mediaSourceId?: string; transcoding: boolean; startOffset?: number };
  autoSelect?: boolean;
  resumePositionSeconds?: number;
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
  // Set when playback was switched to a debrid-transcoded HLS stream; external
  // players should keep using originalUrl.
  transcoded?: boolean;
  originalUrl?: string | null;
  // Set when this source should play through the in-browser MKV remux path.
  remux?: boolean;
}

export interface InstalledAddon {
  id: string;
  name: string;
  version: string;
  manifestUrl: string;
  description?: string | null;
  catalogs: AddonCatalog[];
  resources: Array<string | AddonResource>;
  types?: string[];
  idPrefixes?: string[];
  logo?: string | null;
  background?: string | null;
  enabled?: boolean;
}

export interface AddonResource {
  name: string;
  types?: string[];
  idPrefixes?: string[];
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
  provider?: "netlify" | "supabase";
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
  episode_still_path?: string | null;
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

export interface IptvTvSession {
  lastChannelId: string;
  lastGroupName: string;
  lastFocusedZone: string;
  lastOpenedAt: number;
  /** Android stores oldest first, most recently played last. */
  recentChannelIds: string[];
}

export interface IptvChannel {
  /** Exact provider-playlist identity used by Android cloud sync. */
  cloudId?: string;
  syncAliases?: string[];
  requestHeaders?: Record<string, string>;
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
  artworkUrl?: string;
  category?: string;
}

export interface IptvNowNext {
  now?: IptvProgram;
  next?: IptvProgram;
  later?: IptvProgram;
  upcoming: IptvProgram[];
  recent: IptvProgram[];
}

export interface IptvSnapshot {
  identitiesLoaded?: boolean;
  allChannels?: IptvChannel[];
  channels: IptvChannel[];
  grouped: Record<string, IptvChannel[]>;
  nowNext: Record<string, IptvNowNext>;
  favoriteGroups: string[];
  favoriteChannels: string[];
  hiddenGroups: string[];
  groupOrder: string[];
  playlistWarnings?: string[];
  epgWarning?: string;
  loadedAt: number;
  /**
   * Identifies the playlist set this snapshot was built from, so re-entering
   * Live TV can reuse it instead of re-parsing every channel. Changing a
   * playlist changes the signature and forces a rebuild.
   */
  signature?: string;
}

export interface HomeServerCollectionConfig {
  id: string;
  name: string;
  type: string;
  enabled: boolean;
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
  // APK-parity fields (carried through cloud sync so a server configured on the
  // TV resolves as a source in the web app without re-entry).
  serverId?: string;
  userId?: string;
  userName?: string;
  accountToken?: string; // Plex account token (accessToken is the server token)
  collections?: HomeServerCollectionConfig[];
  lastConnectedAt?: number;
}

export interface QualityFilterConfig {
  id: string;
  deviceName: string;
  regexPattern: string;
  enabled: boolean;
  createdAt?: number;
}

export interface AppSettings {
  // Playback
  autoPlayNext: boolean;
  autoPlaySingleSource: boolean;
  autoPlayMinQuality: "any" | "hd" | "fhd" | "4k";
  frameRateMatchingMode: "off" | "seamless" | "always";
  trailerAutoPlay: boolean;
  trailerSound: boolean;
  trailerDelaySeconds: number;
  trailerInCards: boolean;
  volumeBoostDb: number;
  includeSpecials: boolean;
  qualityFilterPreset: "off" | "1080p-plus" | "1080p-only" | "720p-plus" | "custom";
  qualityFilters: QualityFilterConfig[];
  // Language & audio
  language: string;
  defaultSubtitle: string;
  secondarySubtitle: string;
  audioLanguage: string;
  // Subtitles
  subtitleSize: number;
  subtitleColor: string;
  subtitleColorName: "White" | "Yellow" | "Green" | "Cyan" | "Red" | "Orange" | "Blue" | "Violet";
  subtitleOffsetMs: number;
  subtitleOffset: "bottom" | "low" | "medium" | "high";
  subtitleStyle: "outline" | "shadow" | "background" | "raised";
  subtitleStylized: boolean;
  filterSubtitlesByLanguage: boolean;
  removeHearingImpaired: boolean;
  // AI subtitles
  aiSubtitlesEnabled: boolean;
  aiSubtitleModel: "off" | "groq" | "gemini";
  aiAutoSelect: boolean;
  aiApiKey: string;
  // Playback target: "browser" plays in the built-in player; "vlc"/"infuse"
  // hand the top source straight to that external player on Play (Trakt still
  // syncs via the return-to-app prompt).
  defaultPlayer: "browser" | "vlc" | "infuse";
  // Appearance
  cardLayoutMode: "landscape" | "poster";
  deviceModeOverride: "auto" | "tv" | "tablet" | "phone" | "desktop";
  oledBlack: boolean;
  clockFormat: "12h" | "24h";
  showBudget: boolean;
  smoothScrolling: boolean;
  spoilerBlur: boolean;
  accentColor: string;
  // Network
  dnsProvider: "system" | "cloudflare" | "google" | "adguard" | "quad9";
  showLoadingStats: boolean;
  customUserAgent: string;
  torrServerBaseUrl: string;
  // Profiles
  skipProfileSelection: boolean;
  cardDensity: "comfortable" | "compact";
  // Catalogs / addons
  catalogs: CatalogConfig[];
  hiddenCatalogIds: string[];
  hiddenHomeServerCatalogIds: string[];
  disabledAddonIds: string[];
  // Home servers
  homeServers: HomeServerConfig[];
  // IPTV
  iptvPlaylists: IptvPlaylistEntry[];
  iptvStalkerUrl: string;
  iptvStalkerMac: string;
  favoriteChannelIds: string[];
  iptvTvSession?: IptvTvSession;
  favoriteGroupIds: string[];
  hiddenGroupIds: string[];
  /** Read from cloud; locked IPTV groups are not exposed without a PIN flow. */
  lockedIptvGroupIds?: string[];
  groupOrder: string[];
  // Metadata & API Keys
  customTmdbApiKey: string;
  customTvdbApiKey: string;
  customTvdbUserPin: string;
  metadataMovieProviders: string[];
  metadataTvProviders: string[];
  metadataAnimeProviders: string[];
  iptvSortOrder?: "provider" | "number" | "name";
}

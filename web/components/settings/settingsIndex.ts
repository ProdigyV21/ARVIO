// Every searchable setting: the section it lives in, the row id it scrolls to
// (rows render id="st-<id>"), and extra words people might search for.
import type { SettingScope } from "./ui";

export type SettingsSectionId =
  | "account" | "premium" | "profiles" | "services" | "playback" | "language" | "subtitles" | "ai"
  | "addons" | "catalogs" | "tv" | "homeserver" | "telegram" | "metadata" | "appearance" | "android" | "browser" | "credits";

export interface SettingsIndexEntry {
  section: SettingsSectionId;
  label: string;
  id?: string;
  keywords?: string;
  scope?: SettingScope;
}

export const SETTINGS_INDEX: SettingsIndexEntry[] = [
  { section: "account", label: "ARVIO account", keywords: "sign in sign out log out email cloud sync now" },
  { section: "account", label: "Sync with your Android and TV app", keywords: "sync now cloud refresh tv phone" },
  { section: "premium", label: "ARVIO Web Premium", keywords: "membership subscription pass 12-month yearly ko-fi invite friends referral" },
  { section: "profiles", label: "Manage profiles", id: "active-profile", keywords: "profile switch add kids" },
  { section: "profiles", label: "Skip profile selection on launch", id: "skip-profile-selection", scope: "all" },
  { section: "services", label: "Trakt", keywords: "scrobble connect link" , scope: "all" },
  { section: "services", label: "Simkl", keywords: "connect link anime", scope: "all" },
  { section: "services", label: "MDBList", keywords: "api key lists ratings", scope: "all" },
  { section: "services", label: "Watchlist source", id: "watchlist-source", scope: "all" },
  { section: "services", label: "Continue Watching source", id: "continue-watching-source", keywords: "progress", scope: "all" },
  { section: "services", label: "Watched history source", id: "watched-source", scope: "all" },

  { section: "playback", label: "Auto-play next episode", id: "autoplay-next", keywords: "autoplay binge", scope: "all" },
  { section: "playback", label: "Auto-play single source", id: "autoplay-single", keywords: "autoplay", scope: "all" },
  { section: "playback", label: "Minimum quality", id: "autoplay-min-quality", keywords: "autoplay resolution 1080p 4k hd", scope: "all" },
  { section: "playback", label: "Maximum quality", id: "autoplay-max-quality", keywords: "autoplay resolution limit 4k", scope: "android" },
  { section: "playback", label: "Maximum file size", id: "autoplay-max-size", keywords: "autoplay gb size limit", scope: "android" },
  { section: "playback", label: "Include specials", id: "include-specials", keywords: "season 0", scope: "all" },
  { section: "playback", label: "Search mode", id: "search-mode", keywords: "parallel sequential sources integrations", scope: "android" },
  { section: "playback", label: "Hide sources", id: "quality-filter", keywords: "quality filter regex cam 720p", scope: "web" },
  { section: "playback", label: "Trailer auto-play", id: "trailer-autoplay", keywords: "trailers preview", scope: "android" },
  { section: "playback", label: "Trailer sound", id: "trailer-sound", keywords: "trailers audio", scope: "android" },
  { section: "playback", label: "Trailer delay", id: "trailer-delay", keywords: "trailers seconds", scope: "android" },
  { section: "playback", label: "Trailers in cards", id: "trailer-in-cards", scope: "android" },

  { section: "language", label: "App and metadata language", id: "content-language", keywords: "interface menu language tmdb", scope: "all" },
  { section: "language", label: "Audio language", id: "audio-language", keywords: "dub track", scope: "all" },
  { section: "language", label: "Subtitle language", id: "subtitle-language", keywords: "captions default", scope: "all" },
  { section: "language", label: "Second subtitle language", id: "secondary-subtitle", keywords: "captions fallback", scope: "all" },
  { section: "language", label: "Only list my subtitle languages", id: "filter-subtitles", keywords: "filter", scope: "all" },
  { section: "language", label: "Forced subtitles", id: "forced-subtitles", scope: "android" },

  { section: "subtitles", label: "Size", id: "subtitle-size", keywords: "subtitle font size bigger", scope: "all" },
  { section: "subtitles", label: "Color", id: "subtitle-color", keywords: "subtitle colour", scope: "all" },
  { section: "subtitles", label: "Custom color", id: "subtitle-custom-color", keywords: "subtitle colour", scope: "web" },
  { section: "subtitles", label: "Style", id: "subtitle-style", keywords: "subtitle outline shadow background", scope: "all" },
  { section: "subtitles", label: "Position", id: "subtitle-position", keywords: "subtitle height bottom top", scope: "all" },
  { section: "subtitles", label: "Font", id: "subtitle-font", keywords: "subtitle typeface", scope: "android" },
  { section: "subtitles", label: "Keep subtitle file styling", id: "subtitle-stylized", keywords: "ass ssa stylized", scope: "all" },
  { section: "subtitles", label: "Remove hearing-impaired tags", id: "remove-sdh", keywords: "sdh cc", scope: "all" },
  { section: "subtitles", label: "Preload subtitles", id: "subtitle-preload", scope: "android" },
  { section: "subtitles", label: "Subtitle delay", id: "subtitle-delay", keywords: "sync offset timing", scope: "web" },

  { section: "ai", label: "AI subtitle translation", id: "ai-enabled", keywords: "translate groq gemini", scope: "all" },
  { section: "ai", label: "Translate into", id: "ai-target", keywords: "target language", scope: "all" },
  { section: "ai", label: "API key", id: "ai-key", keywords: "groq gemini key free", scope: "all" },
  { section: "ai", label: "Translate automatically", id: "ai-auto", keywords: "auto select", scope: "all" },
  { section: "ai", label: "Find best match", id: "ai-best-match", keywords: "subtitle sync", scope: "android" },

  { section: "addons", label: "Install an addon", keywords: "stremio manifest url", scope: "all" },
  { section: "addons", label: "Addon order", keywords: "reorder drag priority sort move up down top first catalogs sources", scope: "all" },
  { section: "addons", label: "Source priority", keywords: "source order integrations providers home server plugins telegram iptv vod search first", scope: "android" },
  { section: "addons", label: "Turn a source off", keywords: "disable provider integration skip", scope: "android" },
  { section: "catalogs", label: "Home rows", keywords: "catalogs reorder hide rows drag order move top bottom", scope: "all" },
  { section: "catalogs", label: "Row card style", keywords: "poster wide landscape layout per row", scope: "all" },
  { section: "catalogs", label: "Rename a row", keywords: "title name catalog", scope: "all" },
  { section: "catalogs", label: "Filter rows by source", keywords: "tmdb addon trakt mdblist simkl home server collections youtube group", scope: "all" },
  { section: "catalogs", label: "Add a list", keywords: "trakt mdblist simkl list url catalog", scope: "all" },
  { section: "catalogs", label: "Collections", keywords: "import json pack", scope: "all" },
  { section: "tv", label: "Add a playlist", id: "iptv-add", keywords: "iptv xtream username password m3u epg xmltv stalker mac portal live tv", scope: "all" },
  { section: "tv", label: "Live TV layout", id: "iptv-layout", keywords: "category order categories groups reorder drag hide show tv page", scope: "all" },
  { section: "tv", label: "Channel order", id: "iptv-layout", keywords: "which channel where reorder channels position move inside category", scope: "all" },
  { section: "tv", label: "Favourite channels", id: "iptv-layout", keywords: "favorites favourites star order reorder add remove", scope: "all" },
  { section: "tv", label: "Favourite categories", id: "iptv-layout", keywords: "favorites favourites groups star order", scope: "all" },
  { section: "tv", label: "Rename categories", id: "iptv-layout", keywords: "category name rename group title custom label", scope: "all" },
  { section: "tv", label: "Playlist order", keywords: "main playlist first reorder", scope: "all" },
  { section: "tv", label: "Import movies and series", keywords: "vod series live channels playlist import", scope: "android" },
  { section: "tv", label: "Sort order", id: "iptv-sort", keywords: "channels iptv", scope: "all" },
  { section: "tv", label: "Favorite channels on Home", id: "iptv-favorites-home", keywords: "iptv", scope: "all" },
  { section: "tv", label: "Guide rows", id: "guide-rows", keywords: "epg tv guide", scope: "android" },
  { section: "tv", label: "Stalker portals", id: "stalker-url", keywords: "stalker ministra mac portal address", scope: "all" },
  { section: "homeserver", label: "Home server", keywords: "plex jellyfin emby silo server", scope: "all" },
  { section: "homeserver", label: "Server order", keywords: "priority reorder drag first", scope: "all" },
  { section: "homeserver", label: "Edit a server", keywords: "change url address token password username credentials test", scope: "all" },
  { section: "homeserver", label: "Libraries", keywords: "home server library collections choose which libraries", scope: "android" },
  { section: "telegram", label: "Telegram", keywords: "chats channels sign in qr" },
  { section: "telegram", label: "Only search Telegram when I select it", id: "telegram-on-click", scope: "android" },
  { section: "metadata", label: "TMDB API key", id: "tmdb-key", keywords: "byok", scope: "browser" },
  { section: "metadata", label: "TVDB v4 API key", id: "tvdb-key", keywords: "byok", scope: "browser" },

  { section: "appearance", label: "Accent color", id: "accent", keywords: "theme colour", scope: "all" },
  { section: "appearance", label: "Pure black background", id: "oled", keywords: "oled dark", scope: "all" },
  { section: "appearance", label: "Card style", id: "card-layout", keywords: "poster landscape wide layout", scope: "all" },
  { section: "appearance", label: "Clock format", id: "clock", keywords: "12-hour 24-hour time", scope: "all" },
  { section: "appearance", label: "Netflix-style Home", id: "netflix-home", keywords: "billboard hero", scope: "android" },
  { section: "appearance", label: "Show watchlist on Home", id: "watchlist-home", scope: "android" },
  { section: "appearance", label: "Show Search", id: "navigation-search", keywords: "navigation tabs hide sections menu", scope: "all" },
  { section: "appearance", label: "Show Library", id: "navigation-library", keywords: "navigation tabs hide sections watchlist menu", scope: "all" },
  { section: "appearance", label: "Show TV Player", id: "navigation-tv", keywords: "navigation tabs hide sections iptv live tv menu", scope: "all" },
  { section: "appearance", label: "Blur episode spoilers", id: "spoiler-blur", scope: "all" },
  { section: "appearance", label: "Show budget and box office", id: "show-budget", keywords: "revenue", scope: "all" },
  { section: "appearance", label: "Show episode ratings", id: "episode-ratings", scope: "android" },
  { section: "appearance", label: "Anime episode ordering", id: "anime-order", keywords: "cours broadcast seasons", scope: "android" },

  { section: "android", label: "Frame rate matching", id: "frame-rate", keywords: "refresh rate afr", scope: "android" },
  { section: "android", label: "Dolby Vision compatibility", id: "dolby-vision", keywords: "hdr10 dv", scope: "android" },
  { section: "android", label: "Volume boost", id: "volume-boost", keywords: "louder audio db", scope: "android" },
  { section: "android", label: "Show loading stats", id: "loading-stats", scope: "android" },
  { section: "android", label: "DNS provider", id: "dns", keywords: "cloudflare google adguard blocked", scope: "android" },
  { section: "android", label: "Custom user agent", id: "user-agent", scope: "android" },
  { section: "android", label: "TorrServer address", id: "torrserver", keywords: "torrent", scope: "android" },

  { section: "browser", label: "Preferred player for manual sources", id: "default-player", keywords: "vlc infuse external", scope: "web" },
  { section: "browser", label: "Smooth scrolling", id: "smooth-scrolling", scope: "web" },
  { section: "browser", label: "Check VLC", id: "vlc-check", keywords: "vlc protocol setup script", scope: "browser" },
  { section: "browser", label: "Downloads", keywords: "download offline", scope: "browser" },
  { section: "browser", label: "Reset ARVIO Web in this browser", id: "reset-web", keywords: "clear reset", scope: "browser" },
  { section: "credits", label: "About ARVIO", keywords: "credits tmdb licences copyright version" },
];

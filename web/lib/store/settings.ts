// App store (lib/store.tsx): Default settings and the metadata provider priority.
import { defaultCatalogs } from "../catalogs";
import type { MetadataProviderId, ProviderPriorityConfig } from "../metadata/types";
import type { AppSettings } from "../types";

export const settingsKey = "arvio.web.settings";

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

export const defaultSettings: AppSettings = {
  autoPlayNext: true,
  autoPlaySingleSource: false,
  autoPlayMinQuality: "any",
  frameRateMatchingMode: "off",
  trailerAutoPlay: true,
  trailerSound: false,
  trailerDelaySeconds: 2,
  trailerInCards: true,
  streamSearchMode: "parallel",
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
  subtitleFont: "System",
  useForcedSubtitles: false,
  subtitlePreload: true,
  aiSubtitlesEnabled: false,
  aiSubtitleModel: "groq",
  aiAutoSelect: false,
  aiApiKey: "",
  aiFindBestMatch: false,
  dolbyVisionCompat: true,
  telegramSearchOnClickOnly: true,
  defaultPlayer: "browser",
  cardLayoutMode: "landscape",
  deviceModeOverride: "auto",
  oledBlack: false,
  clockFormat: "24h",
  showBudget: true,
  showEpisodeRatings: false,
  animeEpisodeStructuring: "broadcast",
  iptvFavoritesOnHome: true,
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
  hiddenAddonCatalogIds: [],
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

export function sameSettings(a: AppSettings, b: AppSettings) {
  return JSON.stringify(a) === JSON.stringify(b);
}

import { addonServesOwnMeta, findAddonServingOwnMeta, getNativeDetails, getNativeSeasonEpisodes, isAddonNative, registerNativeItem } from "./addonNative";
import { config } from "./config";
import { apiProxiedUrl, jsonRequest, proxiedUrl } from "./http";
import { fetchAniZipMappingByTmdbId } from "./metadata/anizip";
import { MetadataDispatcher } from "./metadata/dispatcher";
import type { MetadataLookupIds, MetadataMediaType, MetadataProviderId, ProviderPriorityConfig } from "./metadata/types";
import { tmdbImageUrl } from "./mediaImages";
import { loadStored, saveStored } from "./storage";
import type { CatalogConfig, Category, CollectionSourceConfig, EpisodeInfo, HomeServerConfig, InstalledAddon, MediaItem, MediaType, PersonDetails, ReviewInfo } from "./types";

type TmdbItem = {
  job?: string;
  popularity?: number;
  id: number;
  title?: string;
  name?: string;
  original_title?: string;
  original_name?: string;
  overview?: string;
  poster_path?: string | null;
  backdrop_path?: string | null;
  vote_average?: number;
  vote_count?: number;
  release_date?: string;
  first_air_date?: string;
  media_type?: string;
  adult?: boolean;
  genre_ids?: number[];
  runtime?: number;
  episode_run_time?: number[];
  number_of_seasons?: number;
  number_of_episodes?: number;
  last_air_date?: string;
  status?: string;
  budget?: number;
  revenue?: number;
  original_language?: string;
  genres?: Array<{ id: number; name: string }>;
  networks?: Array<{ id: number; name?: string; logo_path?: string | null }>;
  production_companies?: Array<{ id: number; name?: string; logo_path?: string | null }>;
  external_ids?: {
    imdb_id?: string | null;
    tvdb_id?: number | null;
  };
  credits?: {
    cast?: Array<{ id: number; name?: string; character?: string; profile_path?: string | null }>;
  };
  videos?: {
    results?: Array<{ key?: string; site?: string; type?: string; official?: boolean }>;
  };
  seasons?: Array<{ id: number; season_number?: number; name?: string; episode_count?: number; poster_path?: string | null }>;
  similar?: TmdbList;
  recommendations?: TmdbList;
  "watch/providers"?: {
    results?: Record<string, {
      flatrate?: Array<{ provider_id?: number; provider_name?: string; logo_path?: string | null }>;
      ads?: Array<{ provider_id?: number; provider_name?: string; logo_path?: string | null }>;
      free?: Array<{ provider_id?: number; provider_name?: string; logo_path?: string | null }>;
    }>;
  };
};

type TmdbList = { results: TmdbItem[]; page?: number; total_pages?: number };
type TmdbCombinedCredits = { cast?: TmdbItem[]; crew?: TmdbItem[] };
type MdblistItem = Record<string, unknown>;

const TMDB_CATALOG_PAGE_LIMIT = 10;
const HYDRATE_BATCH_SIZE = 24;
const CATALOG_FIRST_PAINT_LIMIT = 96;

type TmdbPersonResponse = {
  id: number;
  name?: string;
  biography?: string;
  place_of_birth?: string | null;
  birthday?: string | null;
  profile_path?: string | null;
  combined_credits?: {
    cast?: Array<TmdbItem & { character?: string; vote_count?: number; popularity?: number }>;
    crew?: Array<TmdbItem & { job?: string; vote_count?: number; popularity?: number }>;
  };
};

function yearFrom(date?: string) {
  return date?.slice(0, 4) ?? "";
}

// TMDB genre ids → display names (movie + TV combined), so list items can show
// genres in the hero without an extra details fetch.
const TMDB_GENRES: Record<number, string> = {
  28: "Action", 12: "Adventure", 16: "Animation", 35: "Comedy", 80: "Crime",
  99: "Documentary", 18: "Drama", 10751: "Family", 14: "Fantasy", 36: "History",
  27: "Horror", 10402: "Music", 9648: "Mystery", 10749: "Romance",
  878: "Science Fiction", 10770: "TV Movie", 53: "Thriller", 10752: "War",
  37: "Western", 10759: "Action & Adventure", 10762: "Kids", 10763: "News",
  10764: "Reality", 10765: "Sci-Fi & Fantasy", 10766: "Soap", 10767: "Talk",
  10768: "War & Politics"
};

export function genreNamesFromIds(ids?: number[]): string[] {
  return (ids ?? []).map((id) => TMDB_GENRES[id]).filter(Boolean);
}

export function isAnime(item: TmdbItem | Partial<MediaItem>): boolean {
  if ("isAnime" in item && typeof item.isAnime === "boolean") return item.isAnime;
  const genreIds = ("genre_ids" in item ? item.genre_ids : "genreIds" in item ? item.genreIds : []) ?? [];
  const genres = ("genres" in item ? item.genres : []) ?? [];
  const hasAnimation = genreIds.includes(16) || (genres as any[]).some((g: any) =>
    typeof g === "string" ? g.toLowerCase() === "animation" : g.id === 16 || g.name === "Animation"
  );
  const origLang = ("original_language" in item ? item.original_language : "originalLanguage" in item ? item.originalLanguage : "") ?? "";
  return hasAnimation && (origLang === "ja" || origLang === "jp");
}

export function mapTmdbItem(item: TmdbItem, fallbackType: MediaType): MediaItem {
  const mediaType: MediaType = item.media_type === "tv" || fallbackType === "tv"
    ? "tv"
    : "movie";
  const animeFlag = isAnime(item);
  const date = mediaType === "movie" ? item.release_date : item.first_air_date;
  const runtime = item.runtime ?? item.episode_run_time?.[0];
  return {
    id: item.id,
    title: item.title ?? item.name ?? "Untitled",
    overview: item.overview ?? "",
    year: yearFrom(date),
    releaseDate: date ?? null,
    rating: item.vote_average ? item.vote_average.toFixed(1) : "",
    duration: runtime ? `${runtime}m` : "",
    mediaType,
    isAnime: animeFlag,
    image: tmdbImageUrl(config.imageBase, item.poster_path),
    backdrop: tmdbImageUrl(config.backdropBase, item.backdrop_path) || null,
    genreIds: item.genre_ids ?? []
  };
}

// When the host rate-limits this client (Netlify per-IP 429 — seen when one
// household IP gets flagged), every further request only extends the penalty
// window, and the retry below would DOUBLE the burst. Back off globally: no
// retry on 429, and short-circuit all TMDB calls for a cooldown so the limit
// can decay. Callers already render retryable/partial UI on failure.
let tmdbCooldownUntil = 0;
const TMDB_COOLDOWN_MS = 30_000;

export async function tmdb<T>(path: string, params: Record<string, string | number | undefined> = {}, customKey?: string) {
  const url = new URL(`/api/tmdb/${path.replace(/^\/+/, "")}`, window.location.origin);
  // Never surface adult titles anywhere in the app (discover/search/trending).
  url.searchParams.set("include_adult", "false");
  Object.entries(params).forEach(([key, value]) => {
    if (value !== undefined && value !== "") url.searchParams.set(key, String(value));
  });
  if (Date.now() < tmdbCooldownUntil) {
    throw new Error("Rate limited — TMDB requests are pausing briefly.");
  }
  const headers: Record<string, string> = {};
  if (customKey?.trim()) {
    headers["X-TMDB-API-Key"] = customKey.trim();
  }
  // Timeout + one retry. Callers swallow failures and render partial UI (a
  // details page without seasons/cast, "No episodes found"), so a single
  // dropped request during the startup burst must not be terminal — and a
  // request with no timeout must not hang a screen forever.
  let lastError: unknown;
  for (let attempt = 0; attempt < 2; attempt += 1) {
    try {
      return await jsonRequest<T>(url.toString(), {
        headers: Object.keys(headers).length > 0 ? headers : undefined,
        signal: typeof AbortSignal.timeout === "function" ? AbortSignal.timeout(12_000) : undefined
      });
    } catch (error) {
      lastError = error;
      if ((error as { status?: number }).status === 429) {
        tmdbCooldownUntil = Date.now() + TMDB_COOLDOWN_MS;
        break;
      }
      if (attempt === 0) await new Promise((resolve) => setTimeout(resolve, 400));
    }
  }
  throw lastError;
}

export async function loadHomeCategories(language = "en-US", catalogs?: CatalogConfig[]): Promise<Category[]> {
  if (catalogs?.length) {
    const enabled = catalogs.filter((catalog) => catalog.enabled);
    const rows = await Promise.all(enabled.map((catalog) => loadCatalog(catalog, language).catch(() => null)));
    const hydrated = rows.filter((row): row is Category => Boolean(row?.items.length));
    if (hydrated.length) return hydrated;
  }

  try {
    const [movies, series, anime, popularMovies, popularTv] = await Promise.all([
      tmdb<TmdbList>("trending/movie/day", { language }),
      tmdb<TmdbList>("trending/tv/day", { language }),
      tmdb<TmdbList>("discover/tv", { language, with_genres: "16", sort_by: "popularity.desc" }),
      tmdb<TmdbList>("discover/movie", { language, sort_by: "popularity.desc" }),
      tmdb<TmdbList>("discover/tv", { language, sort_by: "popularity.desc" })
    ]);
    return [
      { id: "trending_movies", title: "Trending in Movies", items: movies.results.map((x) => mapTmdbItem(x, "movie")) },
      { id: "trending_tv", title: "Trending in Shows", items: series.results.map((x) => mapTmdbItem(x, "tv")) },
      { id: "trending_anime", title: "Trending in Anime", items: anime.results.map((x) => mapTmdbItem(x, "tv")) },
      { id: "popular_movies", title: "Popular Movies", items: popularMovies.results.map((x) => mapTmdbItem(x, "movie")) },
      { id: "popular_tv", title: "Popular Series", items: popularTv.results.map((x) => mapTmdbItem(x, "tv")) }
    ];
  } catch {
    return fallbackCategories;
  }
}

export async function loadCatalog(
  catalog: CatalogConfig,
  language = "en-US",
  addons: InstalledAddon[] = [],
  homeServers: HomeServerConfig[] = []
): Promise<Category | null> {
  if (!catalog.enabled) return null;
  if (isCollectionCatalog(catalog)) {
    const items = await loadCollectionCatalog(catalog, language, addons, homeServers);
    return {
      id: catalog.id,
      title: catalog.name,
      items,
      sourceLabel: "COLLECTION",
      sourceUrl: catalog.sourceUrl,
      layout: catalog.layout ?? "landscape"
    };
  }

  if (catalog.sourceType === "tmdb" && !catalog.endpoint) {
    return {
      id: catalog.id, title: catalog.name,
      items: await loadTmdbPageCatalog(catalog, language),
      sourceLabel: "TMDB", sourceUrl: catalog.sourceUrl,
      layout: catalog.layout ?? "landscape"
    };
  }

  if (catalog.sourceType === "tmdb" && catalog.endpoint) {
    const results = await loadTmdbCatalogPages(catalog, language);
    return {
      id: catalog.id,
      title: catalog.name,
      items: results.map((x) => mapTmdbItem(x, catalog.mediaType === "tv" ? "tv" : "movie")),
      sourceLabel: "TMDB",
      layout: catalog.layout ?? "landscape"
    };
  }

  if (catalog.sourceType === "mdblist" && catalog.sourceUrl) {
    const items = await loadMdblist(catalog, language);
    return {
      id: catalog.id,
      title: catalog.name,
      items,
      sourceLabel: "MDBLIST",
      sourceUrl: catalog.sourceUrl,
      layout: catalog.layout ?? "landscape"
    };
  }

  // Favorite TV is the profile's IPTV favorites; Home renders it from the channel list.
  if (catalog.sourceType === "preinstalled" && catalog.id === "favorite_tv") return null;

  if (catalog.sourceType === "trakt" && catalog.sourceUrl) {
    const refs = await loadTraktPublicList(catalog.sourceUrl) as Array<{ type: MediaType; id: number }>;
    const details = await hydrateRefs(refs, language);
    return {
      id: catalog.id,
      title: catalog.name,
      items: details,
      sourceLabel: "TRAKT",
      sourceUrl: catalog.sourceUrl,
      layout: catalog.layout ?? "landscape"
    };
  }

  if (catalog.sourceType === "simkl" && catalog.sourceUrl) {
    const { loadSimklCustomList } = await import("./simklLists");
    const list = await loadSimklCustomList(catalog.sourceUrl);
    const refs: Array<{ type: MediaType; id: number }> = [];
    for (let start = 0; start < list.items.length && refs.length < 60; start += 6) {
      const batch = await Promise.all(list.items.slice(start, start + 6).map(async item => {
        const type: MediaType = item.type === "movie" || item.type === "movies" || item.anime_type === "movie" ? "movie" : "tv";
        const id = await resolveTmdbId({ mediaType: type, tmdbId: Number(item.ids?.tmdb) || null,
          imdbId: item.ids?.imdb, title: item.title, year: item.year }).catch(() => null);
        return id ? { type, id } : null;
      }));
      refs.push(...batch.filter((ref): ref is { type: MediaType; id: number } => ref !== null));
    }
    return { id: catalog.id, title: catalog.name, items: await hydrateRefs(refs, language),
      sourceLabel: "SIMKL", sourceUrl: catalog.sourceUrl, layout: catalog.layout ?? "landscape" };
  }

  if (catalog.sourceType === "addon") {
    const items = await loadAddonCatalog(catalog, addons, language);
    return {
      id: catalog.id,
      title: catalog.name,
      items,
      sourceLabel: catalog.addonName || "ADDON",
      sourceUrl: catalog.sourceUrl,
      layout: catalog.layout ?? "landscape"
    };
  }

  if (catalog.sourceType === "home-server") {
    const { loadHomeServerLibraryItems, loadHomeServerRows } = await import("./homeserver");
    const servers = homeServers ?? [];
    if (catalog.sourceUrl?.startsWith("hslib:")) {
      const items = await loadHomeServerLibraryItems(servers, catalog.sourceUrl);
      return {
        id: catalog.id,
        title: catalog.name,
        items,
        sourceLabel: "HOME SERVER",
        sourceUrl: catalog.sourceUrl,
        layout: catalog.layout ?? "landscape"
      };
    }
    const rows = await loadHomeServerRows(servers);
    const row = rows.find((r) => r.id === catalog.id || r.title.toLowerCase().includes(catalog.name.toLowerCase()));
    return {
      id: catalog.id,
      title: catalog.name,
      items: row?.items ?? [],
      sourceLabel: "HOME SERVER",
      sourceUrl: catalog.sourceUrl,
      layout: catalog.layout ?? "landscape"
    };
  }

  return null;
}

function isCollectionCatalog(catalog: CatalogConfig) {
  const kind = String(catalog.kind ?? "").toUpperCase();
  return kind === "COLLECTION" || kind === "COLLECTION_RAIL" || Boolean(catalog.collectionSources?.length);
}

// Android stores TMDB page catalogs as a source reference, without a web endpoint.
async function loadTmdbPageCatalog(catalog: CatalogConfig, language: string): Promise<MediaItem[]> {
  const kinds = new Set(["list", "collection", "company", "network", "person", "keyword", "genre"]);
  let parts = /^tmdb:([a-z]+):(\d+):(movie|tv)?$/i.exec(catalog.sourceRef ?? "");
  if (!parts && catalog.sourceUrl) {
    try {
      const url = new URL(/^https?:\/\//i.test(catalog.sourceUrl) ? catalog.sourceUrl : `https://${catalog.sourceUrl}`);
      if (url.hostname === "themoviedb.org" || url.hostname.endsWith(".themoviedb.org")) {
        parts = /^\/(?:[a-z]{2}(?:-[a-z]{2})?\/)?([a-z]+)\/(\d+)(?:-[^/]+)?(?:\/(movie|tv))?\/?$/i.exec(url.pathname);
      }
    } catch { /* Invalid restored URLs have no items. */ }
  }
  if (!parts || !kinds.has(parts[1].toLowerCase()) || Number(parts[2]) <= 0) return [];
  const kind = parts[1].toLowerCase(), id = Number(parts[2]);
  const scope = parts[3]?.toLowerCase();
  const cap = 200;
  const filterScope = (items: MediaItem[]) => dedupeItems(items).filter(item => !scope || item.mediaType === scope).slice(0, cap);
  if (kind === "list") {
    const items: MediaItem[] = [];
    let page = 1, totalPages = 1;
    while (items.length < cap && page <= totalPages) {
      const response = await tmdb<{ items?: TmdbItem[]; total_pages?: number }>(`list/${id}`, { language, page });
      const batch = (response.items ?? []).map(item => mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie"));
      items.push(...batch.filter(item => !scope || item.mediaType === scope));
      totalPages = response.total_pages ?? 1;
      if (!batch.length) break;
      page++;
    }
    return filterScope(items);
  }
  if (kind === "collection") {
    const response = await tmdb<{ parts?: TmdbItem[] }>(`collection/${id}`, { language });
    return filterScope((response.parts ?? []).sort((a, b) => (a.release_date ?? "").localeCompare(b.release_date ?? ""))
      .map(item => mapTmdbItem(item, "movie")));
  }
  if (kind === "person") {
    const response = await tmdb<TmdbCombinedCredits>(`person/${id}/combined_credits`, { language });
    return filterScope([...(response.cast ?? []), ...(response.crew ?? [])]
      .filter(item => !item.adult && (item.media_type === "movie" || item.media_type === "tv"))
      .sort((a, b) => (b.popularity ?? 0) - (a.popularity ?? 0))
      .map(item => mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie")));
  }
  const param = ({ company: "with_companies", network: "with_networks", keyword: "with_keywords", genre: "with_genres" } as Record<string, string>)[kind];
  if (!param) return [];
  const scopes: MediaType[] = kind === "network" ? ["tv"] : scope ? [scope as MediaType] : ["movie", "tv"];
  const batches = await Promise.all(scopes.map(mediaType => loadCollectionSource({
    kind: "TMDB_DISCOVER", mediaType, sortBy: "popularity.desc", discoverParams: { [param]: String(id) }
  }, language, [], [], { page: 1, pageLimit: 10 })));
  const items: MediaItem[] = [];
  for (let i = 0; i < Math.max(...batches.map(batch => batch.length)) && items.length < cap; i++) {
    for (const batch of batches) if (batch[i] && items.length < cap) items.push(batch[i]);
  }
  return filterScope(items);
}

async function loadCollectionCatalog(
  catalog: CatalogConfig,
  language: string,
  addons: InstalledAddon[],
  homeServers: HomeServerConfig[] = []
) {
  const sources = catalog.collectionSources ?? [];
  if (!sources.length) {
    if (catalog.sourceType === "mdblist" && catalog.sourceUrl) return loadMdblist(catalog, language);
    return [];
  }
  const batches = await Promise.all(
    sources.map((source) => loadCollectionSource(source, language, addons, homeServers).catch(() => []))
  );
  return dedupeItems(batches.flat());
}

export async function loadCollectionSource(
  source: CollectionSourceConfig,
  language: string,
  addons: InstalledAddon[],
  homeServers: HomeServerConfig[] = [],
  paging?: { page: number; pageLimit: number }
) {
  const kind = String(source.kind ?? "").toUpperCase();
  const mediaType = sourceMediaType(source);
  if (kind === "TMDB_DISCOVER") {
    const type = mediaType === "tv" ? "tv" : "movie";
    const sort = (source.sortBy || "popularity.desc")
      .replace(type === "tv" ? "primary_release_date" : "first_air_date",
        type === "tv" ? "first_air_date" : "primary_release_date");
    return loadTmdbCatalogPages({
      id: "custom-discover", name: "Collection", sourceType: "tmdb", enabled: true,
      endpoint: `discover/${type}`, mediaType: type,
      params: { ...source.discoverParams, language, sort_by: sort, ...paging }
    }, language).then(items => items.map(item => mapTmdbItem(item, type)));
  }
  if (kind === "TMDB_LIST" && source.tmdbListId) {
    const response = await tmdb<{ items?: TmdbItem[] }>(`list/${source.tmdbListId}`, { language });
    return (response.items ?? []).map(item => mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie"));
  }
  if (kind === "TRAKT_LIST" && source.traktListId) {
    return hydrateRefs(await loadTraktPublicList(`https://trakt.tv/lists/${encodeURIComponent(source.traktListId)}`), language);
  }
  if (kind === "CURATED_IDS") {
    const refs = (source.curatedRefs ?? [])
      .map(parseCuratedRef)
      .filter(<T>(ref: T | null): ref is NonNullable<T> => Boolean(ref));
    const items = await Promise.all(refs.map((ref) => getBasicItem(ref.type, ref.id, language).catch(() => null)));
    return items.filter((item): item is MediaItem => Boolean(item));
  }
  if (kind === "MDBLIST_PUBLIC" && source.mdblistSlug) {
    return loadMdblist({
      id: `collection-mdblist-${source.mdblistSlug}`,
      name: source.mdblistSlug,
      sourceType: "mdblist",
      mediaType,
      sourceUrl: `https://mdblist.com/lists/${source.mdblistSlug}`,
      enabled: true
    }, language);
  }
  if (kind === "TMDB_COLLECTION" && source.tmdbCollectionId) {
    const response = await tmdb<{ parts?: TmdbItem[] }>(`collection/${source.tmdbCollectionId}`, { language });
    return (response.parts ?? []).sort((a, b) => (a.release_date ?? "").localeCompare(b.release_date ?? ""))
      .map((item) => mapTmdbItem({ ...item, media_type: item.media_type ?? "movie" }, "movie"));
  }
  if (kind === "TMDB_PERSON" && source.tmdbPersonId) {
    const response = await tmdb<TmdbCombinedCredits>(`person/${source.tmdbPersonId}/combined_credits`, { language });
    if (source.tmdbCreditRole) {
      const credits = source.tmdbCreditRole === "Director" ? (response.crew ?? []).filter(item => item.job === "Director") : response.cast ?? [];
      const matching = credits.filter(item => !item.adult && item.media_type === (mediaType === "tv" ? "tv" : "movie"));
      const sort = source.sortBy || "popularity.desc";
      const value = (item: TmdbItem): string | number => {
        switch (sort.split('.')[0]) {
          case "primary_release_date": case "first_air_date": case "release_date": return item.release_date || item.first_air_date || "";
          case "vote_average": return item.vote_average || 0;
          case "vote_count": return item.vote_count || 0;
          case "title": case "original_title": return item.title || item.name || "";
          default: return item.popularity || 0;
        }
      };
      matching.sort((a, b) => (value(a) < value(b) ? -1 : value(a) > value(b) ? 1 : 0) * (sort.endsWith('.asc') ? 1 : -1));
      return dedupeItems(matching.map(item => mapTmdbItem(item, mediaType === "tv" ? "tv" : "movie")));
    }
    return dedupeItems([...(response.cast ?? []), ...(response.crew ?? [])]
      .filter((item) => item.media_type === "movie" || item.media_type === "tv")
      .map((item) => mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie")));
  }
  if (kind === "TMDB_GENRE" && source.tmdbGenreId) {
    return loadTmdbCatalogPages({
      id: `collection-genre-${source.tmdbGenreId}`,
      name: String(source.tmdbGenreId),
      sourceType: "tmdb",
      mediaType,
      endpoint: `discover/${mediaType === "tv" ? "tv" : "movie"}`,
      enabled: true,
      params: {
      language,
      with_genres: source.tmdbGenreId,
      sort_by: source.sortBy || "popularity.desc", ...paging
      }
    }, language).then((items) => items.map((item) => mapTmdbItem(item, mediaType === "tv" ? "tv" : "movie")));
  }
  if (kind === "TMDB_KEYWORD" && source.tmdbKeywordId) {
    return loadTmdbCatalogPages({
      id: `collection-keyword-${source.tmdbKeywordId}`,
      name: String(source.tmdbKeywordId),
      sourceType: "tmdb",
      mediaType,
      endpoint: `discover/${mediaType === "tv" ? "tv" : "movie"}`,
      enabled: true,
      params: {
        language,
        with_keywords: source.tmdbKeywordId,
        sort_by: source.sortBy || "popularity.desc", ...paging
      }
    }, language).then((items) => items.map((item) => mapTmdbItem(item, mediaType === "tv" ? "tv" : "movie")));
  }
  if (kind === "TMDB_WATCH_PROVIDER" && source.tmdbWatchProviderId) {
    return loadTmdbCatalogPages({
      id: `collection-provider-${source.tmdbWatchProviderId}`,
      name: String(source.tmdbWatchProviderId),
      sourceType: "tmdb",
      mediaType,
      endpoint: `discover/${mediaType === "tv" ? "tv" : "movie"}`,
      enabled: true,
      params: {
        language,
        watch_region: source.watchRegion || "US",
        with_watch_providers: source.tmdbWatchProviderId,
        sort_by: source.sortBy || "popularity.desc", ...paging
      }
    }, language).then((items) => items.map((item) => mapTmdbItem(item, mediaType === "tv" ? "tv" : "movie")));
  }
  if (kind === "ADDON_CATALOG") {
    const installed = findCollectionAddon(addons, source);
    if (!installed) throw new Error("The add-on for this collection is not installed or enabled.");
    return loadAddonCatalog({
      id: `collection-addon-${source.addonId}-${source.addonCatalogId}`,
      name: source.addonCatalogId || "Addon catalog",
      sourceType: "addon",
      mediaType,
      addonId: installed.id,
      addonCatalogType: source.addonCatalogType,
      addonCatalogId: source.addonCatalogId,
      addonGenre: source.addonGenre,
      enabled: true
    }, addons, language);
  }
  if (kind === "HOME_SERVER" || kind === "HOMESERVER") {
    const { loadHomeServerLibraryItems, loadHomeServerRows } = await import("./homeserver");
    const servers = homeServers ?? [];
    if (source.homeServerLibraryKey) {
      return loadHomeServerLibraryItems(servers, `hslib:${source.homeServerId ?? ""}:${source.homeServerLibraryKey}`);
    }
    const rows = await loadHomeServerRows(servers);
    return rows.flatMap((r) => r.items);
  }
  return [];
}

/**
 * The installed addon a collection's addon source loads from. Collections name the addon by
 * its manifest id ("aio-metadata"); Android-installed entries carry a per-URL suffix. Like
 * Nuvio, the named addon is asked even for a catalog its manifest doesn't list (AIOMetadata
 * serves its streaming catalogs that way).
 */
function findCollectionAddon(addons: InstalledAddon[], source: CollectionSourceConfig) {
  const enabled = addons.filter(a => a.enabled !== false);
  const wanted = String(source.addonId ?? "").trim();
  const isNamed = (a: InstalledAddon) => Boolean(wanted) &&
    (a.id === wanted || (a as { manifest?: { id?: string } }).manifest?.id === wanted);
  const declares = (a: InstalledAddon) => (a.catalogs ?? []).some(c => c.id === source.addonCatalogId &&
    String(c.type).toLowerCase() === String(source.addonCatalogType ?? "").toLowerCase());
  return enabled.find(a => isNamed(a) && declares(a)) ?? enabled.find(declares) ?? enabled.find(isNamed);
}

function sourceMediaType(source: CollectionSourceConfig): MediaType | "all" {
  const raw = String(source.mediaType ?? "").trim().toLowerCase();
  if (raw === "tv" || raw === "series") return "tv";
  if (raw === "movie") return "movie";
  return "all";
}

function parseCuratedRef(ref: string) {
  const [type, rawId] = String(ref).split(":");
  const id = Number(rawId);
  if (!Number.isFinite(id) || id <= 0) return null;
  const mediaType: MediaType = type === "tv" || type === "series" ? "tv" : "movie";
  return { type: mediaType, id };
}

function dedupeItems(items: MediaItem[]) {
  const seen = new Set<string>();
  return items.filter((item) => {
    const key = `${item.mediaType}:${item.id}`;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

function catalogPageLimit(catalog: CatalogConfig) {
  const raw = Number(catalog.params?.pageLimit ?? catalog.params?.pages ?? TMDB_CATALOG_PAGE_LIMIT);
  if (!Number.isFinite(raw) || raw <= 0) return TMDB_CATALOG_PAGE_LIMIT;
  return Math.min(25, Math.max(1, Math.floor(raw)));
}

function cleanCatalogParams(params?: CatalogConfig["params"]) {
  const next: Record<string, string | number | undefined> = {};
  Object.entries(params ?? {}).forEach(([key, value]) => {
    if (key === "pageLimit" || key === "pages") return;
    if (typeof value === "boolean") {
      next[key] = value ? "true" : "false";
      return;
    }
    next[key] = value;
  });
  return next;
}

function dedupeTmdbItems(items: TmdbItem[]) {
  const seen = new Set<string>();
  return items.filter((item) => {
    const type = item.media_type ?? "";
    const key = `${type}:${item.id}`;
    if (!item.id || seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

async function loadTmdbCatalogPages(catalog: CatalogConfig, language: string) {
  if (!catalog.endpoint) return [];
  const params = cleanCatalogParams(catalog.params);
  const startPage = Number(params.page ?? 1);
  const first = await tmdb<TmdbList>(catalog.endpoint, { ...params, language, page: startPage });
  const totalPages = Math.max(startPage, Number(first.total_pages ?? startPage) || startPage);
  const lastPage = Math.min(totalPages, startPage + catalogPageLimit(catalog) - 1);
  const pageRequests: Array<Promise<TmdbList>> = [];
  for (let page = startPage + 1; page <= lastPage; page += 1) {
    pageRequests.push(tmdb<TmdbList>(catalog.endpoint, { ...params, language, page }).catch(() => ({ results: [] })));
  }
  const rest = await Promise.all(pageRequests);
  return dedupeTmdbItems([...(first.results ?? []), ...rest.flatMap((page) => page.results ?? [])]);
}

async function hydrateRefs(refs: Array<{ type: MediaType; id: number }>, language: string) {
  const output: MediaItem[] = [];
  const firstPaintRefs = refs.slice(0, CATALOG_FIRST_PAINT_LIMIT);
  for (let index = 0; index < firstPaintRefs.length; index += HYDRATE_BATCH_SIZE) {
    const chunk = firstPaintRefs.slice(index, index + HYDRATE_BATCH_SIZE);
    const details = await Promise.all(chunk.map((item) => getBasicItem(item.type, item.id, language).catch(() => null)));
    output.push(...details.filter((item): item is MediaItem => Boolean(item?.title)));
  }
  return dedupeItems(output);
}

async function loadMdblist(catalog: CatalogConfig, language: string) {
  const url = `${catalog.sourceUrl!.replace(/\/+$/, "")}/json`;
  const payload = await jsonRequest<unknown>(apiProxiedUrl(url));
  const rawItems = Array.isArray(payload)
    ? payload
    : Array.isArray((payload as { items?: unknown[] }).items)
      ? (payload as { items: unknown[] }).items
      : Array.isArray((payload as { movies?: unknown[] }).movies)
        ? (payload as { movies: unknown[] }).movies
        : [];
  const ids = rawItems
    .map((item) => extractMdblistIdentity(item as MdblistItem, catalog.mediaType))
    .filter(<T>(item: T | null | undefined): item is NonNullable<T> => Boolean((item as any)?.id));
  return hydrateRefs(ids, language);
}

type StremioMeta = {
  id?: string;
  imdb_id?: string;
  tmdb_id?: number | string;
  type?: string;
  name?: string;
  title?: string;
  description?: string;
  poster?: string;
  background?: string;
  logo?: string;
  releaseInfo?: string;
  year?: string | number;
};

async function loadAddonCatalog(catalog: CatalogConfig, addons: InstalledAddon[], language: string) {
  const addon = addons.find((candidate) =>
    candidate.id === catalog.addonId ||
    candidate.name === catalog.addonName ||
    candidate.manifestUrl === catalog.sourceUrl
  );
  const manifestUrl = addon?.manifestUrl || catalog.sourceUrl;
  const requestedType = catalog.addonCatalogType || (catalog.mediaType === "tv" ? "series" : catalog.mediaType === "movie" ? "movie" : "movie");
  const catalogId = catalog.addonCatalogId || catalog.sourceRef || catalog.id;
  // Addons route by the manifest's exact spelling ("Live Docs"); collections may lower-case it.
  const catalogType = (addon?.catalogs ?? []).find(c => c.id === catalogId &&
    String(c.type).toLowerCase() === requestedType.toLowerCase())?.type || requestedType;
  if (!manifestUrl || !catalogId) return [];
  const base = manifestUrl.replace(/\/manifest\.json$/, "").replace(/\/+$/, "");
  const extra = catalog.addonGenre ? `/genre=${encodeURIComponent(catalog.addonGenre)}` : "";
  const url = proxiedUrl(`${base}/catalog/${encodeURIComponent(catalogType)}/${encodeURIComponent(catalogId)}${extra}.json`);
  const payload = await jsonRequest<{ metas?: StremioMeta[] }>(url);
  const metas = payload.metas ?? [];
  const hydrated = await Promise.all(metas.map((meta) => hydrateAddonMeta(meta, catalog.mediaType, language, addon, addons, catalogType).catch(() => null)));
  return hydrated.filter((item): item is MediaItem => Boolean(item));
}

async function hydrateAddonMeta(
  meta: StremioMeta,
  preferred: CatalogConfig["mediaType"],
  language: string,
  addon?: InstalledAddon,
  addons: InstalledAddon[] = [],
  catalogType = ""
): Promise<MediaItem | null> {
  const tmdbId = numberValue(meta.tmdb_id);
  const mediaType: MediaType = String(meta.type ?? preferred ?? "").toLowerCase().includes("series") || preferred === "tv" ? "tv" : "movie";
  // Items the addon describes itself open from its own /meta instead of a TMDB guess. A
  // metadata addon (AIOMetadata) may re-list another addon's catalog; that addon owns the ids.
  if (!tmdbId && !meta.imdb_id && meta.id) {
    const addonType = String(meta.type ?? "").trim() || catalogType || (mediaType === "tv" ? "series" : "movie");
    const owner = addon && addonServesOwnMeta(addon, addonType, meta.id)
      ? addon
      : findAddonServingOwnMeta(addons, addonType, meta.id, addon?.id);
    const native = owner ? registerNativeItem(owner, meta, nativeMediaType(addonType), addonType) : null;
    if (native) return native;
  }
  if (tmdbId) {
    const detailed = await getBasicItem(mediaType, tmdbId, language).catch(() => null);
    if (detailed) return detailed;
  }
  const numericId = numberValue(meta.id);
  if (numericId) {
    const detailed = await getBasicItem(mediaType, numericId, language).catch(() => null);
    if (detailed) return detailed;
  }
  const title = meta.name ?? meta.title;
  if (!title) return null;
  return {
    id: numericId ?? stableStringId(`${mediaType}:${title}`),
    title,
    overview: meta.description ?? "",
    year: String(meta.year ?? meta.releaseInfo ?? "").slice(0, 4),
    mediaType,
    image: meta.poster ?? "",
    backdrop: meta.background ?? null,
    rating: "",
    duration: ""
  };
}

/**
 * How a native item is shown. Stremio's "tv" type is a live channel: one stream, no episodes,
 * so it opens like a movie and plays straight away. Other addon-defined types (podcasts,
 * debrid libraries) are containers of videos, which the series page shows.
 */
function nativeMediaType(addonType: string): MediaType {
  const type = addonType.trim().toLowerCase();
  return type === "tv" || type === "movie" || type === "film" ? "movie" : "tv";
}

function stableStringId(value: string) {
  let hash = 0;
  for (let index = 0; index < value.length; index += 1) {
    hash = ((hash << 5) - hash + value.charCodeAt(index)) | 0;
  }
  return Math.abs(hash);
}

async function loadTraktPublicList(sourceUrl: string) {
  const parsed = parseTraktUrl(sourceUrl);
  if (!parsed) return [];
  const path = parsed.type === "user"
    ? `users/${encodeURIComponent(parsed.user)}/lists/${encodeURIComponent(parsed.slug)}/items`
    : `lists/${encodeURIComponent(parsed.slug)}/items`;
  const url = new URL(`/api/trakt/${path}`, window.location.origin);
  const payload = await jsonRequest<Array<{
    type?: string;
    movie?: { ids?: { tmdb?: number } };
    show?: { ids?: { tmdb?: number } };
  }>>(url.toString());
  return payload
    .map((item) => {
      const type: MediaType = item.type === "show" ? "tv" : "movie";
      const id = type === "tv" ? item.show?.ids?.tmdb : item.movie?.ids?.tmdb;
      return id ? { type, id } : null;
    })
    .filter(<T>(item: T | null): item is NonNullable<T> => Boolean(item));
}

function parseTraktUrl(sourceUrl: string): { type: "user"; user: string; slug: string } | { type: "list"; slug: string } | null {
  try {
    const url = new URL(sourceUrl);
    const parts = url.pathname.split("/").filter(Boolean);
    const usersIndex = parts.indexOf("users");
    if (usersIndex >= 0 && parts[usersIndex + 1] && parts[usersIndex + 3]) {
      return { type: "user", user: parts[usersIndex + 1], slug: parts[usersIndex + 3] };
    }
    const listsIndex = parts.indexOf("lists");
    if (listsIndex >= 0 && parts[listsIndex + 1]) {
      return { type: "list", slug: parts[listsIndex + 1] };
    }
  } catch {
    return null;
  }
  return null;
}

function extractMdblistIdentity(item: MdblistItem, preferred?: CatalogConfig["mediaType"]) {
  const ids = objectValue(item, "ids") ?? objectValue(objectValue(item, "movie"), "ids") ?? objectValue(objectValue(item, "show"), "ids");
  const rawType = String(item.type ?? item.media_type ?? item.mediatype ?? "").toLowerCase();
  // MDBList's flat list JSON uses `id` as the TMDB id and `mediatype` for the type.
  const isFlatMdblist = "mediatype" in item || "release_year" in item;
  const tmdb =
    numberValue(item.tmdb_id) ??
    numberValue(item.tmdbId) ??
    numberValue(item.tmdb) ??
    numberValue(objectValue(ids, "tmdb")) ??
    numberValue(objectValue(objectValue(item, "movie"), "tmdb_id")) ??
    numberValue(objectValue(objectValue(item, "show"), "tmdb_id")) ??
    (isFlatMdblist ? numberValue(item.id) : null);
  if (!tmdb) return null;
  const type: MediaType =
    rawType.includes("show") || rawType.includes("series") || rawType === "tv" || (!rawType && preferred === "tv")
      ? "tv"
      : "movie";
  return { id: tmdb, type };
}

function objectValue(value: unknown, key: string): Record<string, unknown> | undefined {
  if (!value || typeof value !== "object") return undefined;
  const next = (value as Record<string, unknown>)[key];
  return next && typeof next === "object" ? next as Record<string, unknown> : undefined;
}

function numberValue(value: unknown) {
  const number = typeof value === "number" ? value : typeof value === "string" ? Number(value) : NaN;
  return Number.isFinite(number) && number > 0 ? number : null;
}

type TmdbImages = {
  logos?: Array<{ file_path: string; iso_639_1: string | null; vote_average?: number; width?: number }>;
};

type TmdbExternalFind = {
  movie_results?: TmdbItem[];
  tv_results?: TmdbItem[];
};

const externalTmdbIdCache = new Map<string, Promise<number | null>>();

/** Resolve a server-native item to TMDB once, so every card overlay can share it. */
export function resolveTmdbId(item: {
  mediaType: MediaType;
  id?: number | null;
  tmdbId?: number | null;
  imdbId?: string | null;
  title?: string | null;
  year?: number | string | null;
}): Promise<number | null> {
  const directId = item.tmdbId && item.tmdbId > 0
    ? item.tmdbId
    : item.id && item.id > 0
      ? item.id
      : null;
  if (directId) return Promise.resolve(directId);

  const imdbId = item.imdbId?.trim().toLowerCase();
  const title = item.title?.trim() ?? "";
  const year = typeof item.year === "number" ? item.year : Number(item.year) || null;
  const normalizedTitle = normalizeLookupTitle(title);
  if ((!imdbId || !/^tt\d+$/.test(imdbId)) && !normalizedTitle) return Promise.resolve(null);
  const cacheKey = imdbId && /^tt\d+$/.test(imdbId)
    ? `${item.mediaType}:imdb:${imdbId}`
    : `${item.mediaType}:title:${normalizedTitle}:${year ?? 0}`;
  const cached = externalTmdbIdCache.get(cacheKey);
  if (cached) return cached;

  const request = (async () => {
    if (imdbId && /^tt\d+$/.test(imdbId)) {
      const payload = await tmdb<TmdbExternalFind>(`find/${imdbId}`, { external_source: "imdb_id" })
        .catch(() => null);
      const externalResults = item.mediaType === "tv" ? payload?.tv_results : payload?.movie_results;
      const externalId = externalResults?.[0]?.id;
      if (externalId) return externalId;
    }
    if (!normalizedTitle) return null;

    const path = item.mediaType === "tv" ? "search/tv" : "search/movie";
    const yearParam = item.mediaType === "tv" ? "first_air_date_year" : "primary_release_year";
    const search = await tmdb<TmdbList>(path, {
      query: title,
      ...(year ? { [yearParam]: year } : {})
    }).catch(() => null);
    const best = search?.results.map((candidate) => {
      const titleScore = [candidate.title, candidate.name, candidate.original_title, candidate.original_name]
        .filter((value): value is string => Boolean(value))
        .reduce((score, value) => {
          const normalized = normalizeLookupTitle(value);
          if (normalized === normalizedTitle) return Math.max(score, 120);
          if (normalized && (normalized.includes(normalizedTitle) || normalizedTitle.includes(normalized))) {
            return Math.max(score, 60);
          }
          return score;
        }, 0);
      const candidateYear = Number(yearFrom(candidate.release_date ?? candidate.first_air_date)) || null;
      const yearScore = !year || !candidateYear ? 0
        : year === candidateYear ? 35
          : Math.abs(year - candidateYear) === 1 ? 15 : -70;
      return { candidate, score: titleScore + yearScore };
    }).sort((a, b) => b.score - a.score)[0];
    return best && best.score >= 85 ? best.candidate.id : null;
  })().catch(() => null);
  externalTmdbIdCache.set(cacheKey, request);
  return request;
}

function normalizeLookupTitle(value: string): string {
  return value.normalize("NFD")
    .replace(/[\u0300-\u036f]/g, "")
    .toLowerCase()
    .replace(/&/g, " and ")
    .replace(/[^a-z0-9]+/g, " ")
    .trim();
}

type CinemetaSeries = {
  meta?: {
    videos?: Array<{ season?: number; episode?: number; number?: number; rating?: string | number | null }>;
  };
};

const logoCache = new Map<string, string | null>();
const LOGO_CACHE_KEY = "arvio.web.logoCache";

function restoreLogoCache() {
  if (logoCache.size || typeof window === "undefined") return;
  try {
    const raw = window.localStorage.getItem(LOGO_CACHE_KEY);
    if (!raw) return;
    Object.entries(JSON.parse(raw) as Record<string, string | null>).forEach(([k, v]) => logoCache.set(k, v));
  } catch {
    /* ignore */
  }
}

let logoPersistTimer: ReturnType<typeof setTimeout> | null = null;
function persistLogoCache() {
  if (typeof window === "undefined") return;
  if (logoPersistTimer) clearTimeout(logoPersistTimer);
  logoPersistTimer = setTimeout(() => {
    try {
      const trimmed = Array.from(logoCache.entries()).slice(-600);
      window.localStorage.setItem(LOGO_CACHE_KEY, JSON.stringify(Object.fromEntries(trimmed)));
    } catch {
      /* ignore */
    }
  }, 1500);
}

/** Title-treatment (clearlogo) URL for a movie/show — mirrors MediaRepository.getImages logo pick. */
export async function getLogoUrl(item: { mediaType: MediaType; id: number }): Promise<string | null> {
  // Ids below 1 are never TMDB titles (native addon items, unmatched entries).
  if (item.id <= 0) return null;
  const key = `${item.mediaType}:${item.id}`;
  restoreLogoCache();
  if (logoCache.has(key)) return logoCache.get(key) ?? null;
  try {
    const images = await tmdb<TmdbImages>(`${item.mediaType}/${item.id}/images`, { include_image_language: "en,null" });
    const logos = images.logos ?? [];
    const pick =
      logos.filter((l) => l.iso_639_1 === "en").sort((a, b) => (b.vote_average ?? 0) - (a.vote_average ?? 0))[0] ??
      logos.find((l) => l.iso_639_1 === null) ??
      logos[0];
    const url = pick?.file_path ? `https://image.tmdb.org/t/p/w500${pick.file_path}` : null;
    logoCache.set(key, url);
    persistLogoCache();
    return url;
  } catch {
    logoCache.set(key, null);
    return null;
  }
}

// Per-card streaming service names (US flatrate providers), cached and
// persisted like title logos so home rails can show clearlogo badges without
// hammering TMDB on every render.
const PROVIDER_CACHE_KEY = "arvio.web.cardProviders.v1";
const providerCache = new Map<string, string[]>();

function restoreProviderCache() {
  if (providerCache.size || typeof window === "undefined") return;
  try {
    const raw = window.localStorage.getItem(PROVIDER_CACHE_KEY);
    if (!raw) return;
    Object.entries(JSON.parse(raw) as Record<string, string[]>).forEach(([k, v]) => providerCache.set(k, v));
  } catch { /* ignore */ }
}

let providerPersistTimer: ReturnType<typeof setTimeout> | null = null;
function persistProviderCache() {
  if (typeof window === "undefined") return;
  if (providerPersistTimer) clearTimeout(providerPersistTimer);
  providerPersistTimer = setTimeout(() => {
    try {
      const trimmed = Array.from(providerCache.entries()).slice(-600);
      window.localStorage.setItem(PROVIDER_CACHE_KEY, JSON.stringify(Object.fromEntries(trimmed)));
    } catch { /* ignore */ }
  }, 800);
}

// Per-card runtime (minutes), cached + persisted. List responses omit runtime,
// so cards fetch it lazily when needed.
type CardMeta = { runtime: number; image?: string; backdrop?: string | null; imdbId?: string | null };
const CARD_META_KEY = "arvio.web.cardMeta.v2";
const cardMetaCache = new Map<string, CardMeta>();

function restoreCardMetaCache() {
  if (cardMetaCache.size || typeof window === "undefined") return;
  try {
    const raw = window.localStorage.getItem(CARD_META_KEY);
    if (raw) Object.entries(JSON.parse(raw) as Record<string, CardMeta>).forEach(([k, v]) => cardMetaCache.set(k, v));
  } catch { /* ignore */ }
}

let cardMetaTimer: ReturnType<typeof setTimeout> | null = null;
function persistCardMetaCache() {
  if (typeof window === "undefined") return;
  if (cardMetaTimer) clearTimeout(cardMetaTimer);
  cardMetaTimer = setTimeout(() => {
    try {
      const trimmed = Array.from(cardMetaCache.entries()).slice(-800);
      window.localStorage.setItem(CARD_META_KEY, JSON.stringify(Object.fromEntries(trimmed)));
    } catch { /* ignore */ }
  }, 800);
}

export async function getCardMeta(item: { mediaType: MediaType; id: number }): Promise<{ runtime: number; image: string; backdrop: string | null; imdbId: string | null }> {
  if (item.id <= 0) return { runtime: 0, image: "", backdrop: null, imdbId: null };
  const key = `${item.mediaType}:${item.id}`;
  restoreCardMetaCache();
  const cached = cardMetaCache.get(key);
  // Older cache entries only stored runtime; treat a missing `image` field as a
  // miss so the card can back-fill its artwork (fixes grey CW thumbnails).
  if (cached && "image" in cached) {
    return { runtime: cached.runtime, image: cached.image ?? "", backdrop: cached.backdrop ?? null, imdbId: cached.imdbId ?? null };
  }
  try {
    // external_ids rides along on the call this function already makes, so the
    // imdb id needed for real IMDb ratings costs no extra request.
    const payload = await tmdb<{ runtime?: number; episode_run_time?: number[]; poster_path?: string | null; backdrop_path?: string | null; external_ids?: { imdb_id?: string | null } }>(
      `${item.mediaType}/${item.id}`,
      { append_to_response: "external_ids" }
    );
    const runtime = payload.runtime ?? payload.episode_run_time?.[0] ?? 0;
    const meta = {
      runtime,
      image: tmdbImageUrl(config.imageBase, payload.poster_path),
      backdrop: tmdbImageUrl(config.backdropBase, payload.backdrop_path) || null,
      imdbId: payload.external_ids?.imdb_id ?? null
    };
    cardMetaCache.set(key, meta);
    persistCardMetaCache();
    return meta;
  } catch {
    const meta = { runtime: 0, image: "", backdrop: null, imdbId: null };
    cardMetaCache.set(key, meta);
    return meta;
  }
}

export async function getCardProviders(item: { mediaType: MediaType; id: number }): Promise<string[]> {
  if (item.id <= 0) return [];
  const key = `${item.mediaType}:${item.id}`;
  restoreProviderCache();
  const cached = providerCache.get(key);
  if (cached) return cached;
  try {
    const payload = await tmdb<{ results?: Record<string, { flatrate?: Array<{ provider_name?: string }> }> }>(
      `${item.mediaType}/${item.id}/watch/providers`,
      {}
    );
    const names = (payload.results?.US?.flatrate ?? [])
      .map((provider) => provider.provider_name ?? "")
      .filter(Boolean)
      .slice(0, 3);
    providerCache.set(key, names);
    persistProviderCache();
    return names;
  } catch {
    providerCache.set(key, []);
    return [];
  }
}

export async function searchMedia(query: string, language = "en-US") {
  if (!query.trim()) return [];
  const response = await tmdb<TmdbList>("search/multi", { query, language });
  return response.results
    .filter((item) => item.media_type === "movie" || item.media_type === "tv")
    .map((item) => mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie"));
}

export interface SearchFilters { query: string; type: "movie" | "tv"; genre: string; year: string; sort: string; }
export async function searchMediaPage(filters: SearchFilters, language: string, page = 1) {
  const searching = !!filters.query.trim();
  const response = await tmdb<TmdbList & { total_pages?: number }>(searching ? `search/${filters.type}` : `discover/${filters.type}`, {
    language, page, include_adult: "false",
    ...(!searching && filters.sort === "vote_average.desc" ? { "vote_count.gte": 100 } : {}),
    ...(searching ? { query: filters.query.trim() } : { sort_by: filters.sort, ...(filters.genre ? { with_genres: filters.genre } : {}) }),
    ...(filters.year ? { [filters.type === "tv" ? "first_air_date_year" : searching ? "year" : "primary_release_year"]: filters.year } : {})
  });
  const items = response.results.filter(item => !searching || !filters.genre || item.genre_ids?.includes(Number(filters.genre)))
    .map(item => mapTmdbItem(item, filters.type));
  return { items, hasMore: page < Math.min(response.total_pages ?? 1, 500) };
}

const seasonCache = new Map<string, EpisodeInfo[]>();
const seriesEpisodeRatingsCache = new Map<string, Map<string, string>>();
const SEASON_EPISODE_CACHE_KEY = "arvio.web.seasonEpisodes.v1";
const SEASON_EPISODE_CACHE_TTL = 7 * 24 * 60 * 60 * 1000;

export function peekSeasonEpisodes(tvId: number, seasonNumber: number, language = "en-US"): EpisodeInfo[] {
  const key = `${tvId}:${seasonNumber}:${language}`;
  return seasonCache.get(key) ?? readSeasonEpisodesCache(key) ?? [];
}

export interface SeasonMetadataContext {
  tvdbId?: number | null;
  anilistId?: number | null;
  isAnime?: boolean;
}

function providersBeforeTmdb(type: MetadataMediaType, configOverride?: ProviderPriorityConfig): ProviderPriorityConfig | null {
  const priority = MetadataDispatcher.getPriorityList(type, configOverride);
  const tmdbIndex = priority.indexOf("tmdb");
  const providers = priority.slice(0, tmdbIndex >= 0 ? tmdbIndex : priority.length);
  if (providers.length === 0) return null;

  const movieProviders: MetadataProviderId[] = configOverride?.movieProviders ?? ["tmdb"];
  const tvProviders: MetadataProviderId[] = configOverride?.tvProviders ?? ["tvdb", "tmdb"];
  const animeProviders: MetadataProviderId[] = configOverride?.animeProviders ?? ["anilist", "tvdb", "tmdb"];
  return {
    ...configOverride,
    movieProviders: type === "movie" ? providers : movieProviders,
    tvProviders: type === "tv" ? providers : tvProviders,
    animeProviders: type === "anime" ? providers : animeProviders
  };
}

export async function getSeasonEpisodes(
  tvId: number,
  seasonNumber: number,
  language = "en-US",
  priorityConfig?: ProviderPriorityConfig,
  metadataContext?: SeasonMetadataContext
): Promise<EpisodeInfo[]> {
  if (isAddonNative({ id: tvId })) return getNativeSeasonEpisodes(tvId, seasonNumber).catch(() => []);
  const metadataType: MetadataMediaType = metadataContext?.isAnime ? "anime" : "tv";
  const externalConfig = providersBeforeTmdb(metadataType, priorityConfig);
  const lookup: MetadataLookupIds = {
    tmdb: tvId,
    tvdb: metadataContext?.tvdbId,
    anilist: metadataContext?.anilistId
  };
  // TMDB numbering remains canonical for playback. TVDB metadata can enrich a
  // matching normal-TV episode, but must never replace the list or its numbers.
  // Anime numbering differs frequently between providers, so it is deliberately
  // excluded until a verified per-episode ID mapping exists.
  const externalEpisodesPromise = externalConfig && metadataType === "tv"
    ? MetadataDispatcher.getEpisodes(lookup, metadataType, seasonNumber, externalConfig).catch(() => [])
    : Promise.resolve([] as EpisodeInfo[]);
  const enrichEpisodes = async (episodes: EpisodeInfo[]) => {
    const externalEpisodes = await externalEpisodesPromise;
    if (externalEpisodes.length === 0) return episodes;
    const externalByNumber = new Map(
      externalEpisodes.map((episode) => [`${episode.seasonNumber}:${episode.episodeNumber}`, episode])
    );
    return episodes.map((episode) => {
      const external = externalByNumber.get(`${episode.seasonNumber}:${episode.episodeNumber}`);
      if (!external) return episode;
      return {
        ...episode,
        name: external.name || episode.name,
        overview: external.overview || episode.overview,
        still: external.still || episode.still,
        airDate: external.airDate || episode.airDate,
        runtime: external.runtime || episode.runtime
      };
    });
  };
  const key = `${tvId}:${seasonNumber}:${language}`;
  const memo = seasonCache.get(key);
  if (memo?.length) return enrichEpisodes(memo);
  const cached = readSeasonEpisodesCache(key);
  if (cached) {
    seasonCache.set(key, cached);
    return enrichEpisodes(cached);
  }
  try {
    // IMDb episode ratings (external_ids + cinemeta) run in parallel but are
    // capped at 2.5s — they are cosmetic and must never hold up the episode
    // list (the cinemeta proxy call has no timeout and can hang for a long time).
    const season = await tmdb<{ episodes?: Array<{ id: number; episode_number: number; name?: string; overview?: string; still_path?: string | null; vote_average?: number; air_date?: string; runtime?: number }> }>(
      `tv/${tvId}/season/${seasonNumber}`,
      { language },
      priorityConfig?.customTmdbApiKey
    );
    // Real IMDb ratings need the episode numbers first (the imdb id lives on the
    // per-episode endpoint), so this starts after the season lands and still
    // races the 2.5s cap below — it is cosmetic and must never delay the list.
    registerSeasonEpisodeNumbers(tvId, seasonNumber, (season.episodes ?? []).map((e) => e.episode_number).filter(Boolean));
    const ratingsPromise = getSeasonEpisodeRatings(tvId, seasonNumber).catch(() => new Map<string, string>());
    ratingsPromise.catch(() => undefined);
    let ratingsTimedOut = false;
    const imdbRatings = await Promise.race([
      ratingsPromise,
      new Promise<Map<string, string>>((resolve) => setTimeout(() => {
        ratingsTimedOut = true;
        resolve(new Map());
      }, 6000))
    ]);
    const episodes = (season.episodes ?? []).map((episode) => ({
      id: episode.id,
      episodeNumber: episode.episode_number,
      seasonNumber,
      name: episode.name || `Episode ${episode.episode_number}`,
      overview: episode.overview ?? "",
      still: episode.still_path ? `${config.imageBase}${episode.still_path}` : undefined,
      voteAverage: episode.vote_average ?? 0,
      imdbRating: imdbRatings.get(`${seasonNumber}:${episode.episode_number}`) ?? "",
      airDate: episode.air_date ?? "",
      runtime: episode.runtime ?? 0
    }));
    // NEVER cache an empty result: a transient failure (429/timeout) or a
    // response that momentarily lacks episodes would otherwise get pinned as
    // "No episodes found" until the TTL expires, even after the API recovers.
    if (episodes.length) {
      seasonCache.set(key, episodes);
      // A ratings timeout only skips the 7-day persistent cache — the next
      // session then refetches with ratings instead of pinning them missing.
      if (!ratingsTimedOut) writeSeasonEpisodesCache(key, episodes);
    }
    return enrichEpisodes(episodes);
  } catch {
    return [];
  }
}

function readSeasonEpisodesCache(key: string) {
  const cache = loadStored<Record<string, { at: number; episodes: EpisodeInfo[] }>>(SEASON_EPISODE_CACHE_KEY, {});
  const entry = cache[key];
  if (!entry || Date.now() - entry.at > SEASON_EPISODE_CACHE_TTL) return null;
  // Treat a cached empty array as a miss so a previously-poisoned entry (from an
  // old build that cached failures) re-fetches instead of showing no episodes.
  return Array.isArray(entry.episodes) && entry.episodes.length ? entry.episodes : null;
}

function writeSeasonEpisodesCache(key: string, episodes: EpisodeInfo[]) {
  const cache = loadStored<Record<string, { at: number; episodes: EpisodeInfo[] }>>(SEASON_EPISODE_CACHE_KEY, {});
  const next = { ...cache, [key]: { at: Date.now(), episodes } };
  const entries = Object.entries(next).sort((a, b) => b[1].at - a[1].at).slice(0, 80);
  saveStored(SEASON_EPISODE_CACHE_KEY, Object.fromEntries(entries));
}

export async function getReviews(item: { mediaType: MediaType; id: number }): Promise<ReviewInfo[]> {
  if (item.id <= 0) return [];
  try {
    const response = await tmdb<{ results?: Array<{ id: string; author?: string; content?: string; created_at?: string; author_details?: { rating?: number | null; avatar_path?: string | null } }> }>(
      `${item.mediaType}/${item.id}/reviews`
    );
    return (response.results ?? []).slice(0, 12).map((review) => ({
      id: review.id,
      author: review.author || "Anonymous",
      content: review.content || "",
      rating: review.author_details?.rating ?? null,
      createdAt: review.created_at,
      avatar: review.author_details?.avatar_path
        ? (review.author_details.avatar_path.startsWith("/http")
            ? review.author_details.avatar_path.slice(1)
            : `${config.imageBase}${review.author_details.avatar_path}`)
        : null
    }));
  } catch {
    return [];
  }
}

const personCache = new Map<number, PersonDetails | null>();

export async function getPersonDetails(personId: number, language = "en-US"): Promise<PersonDetails | null> {
  if (personCache.has(personId)) return personCache.get(personId) ?? null;
  try {
    const person = await tmdb<TmdbPersonResponse>(`person/${personId}`, {
      language,
      append_to_response: "combined_credits"
    });
    const credits = [...(person.combined_credits?.cast ?? []), ...(person.combined_credits?.crew ?? [])]
      .filter((item) => (item.media_type === "movie" || item.media_type === "tv") && item.id)
      .sort((a, b) => (b.popularity ?? 0) - (a.popularity ?? 0) || (b.vote_count ?? 0) - (a.vote_count ?? 0));
    const seen = new Set<string>();
    const knownFor = credits
      .map((item): MediaItem => {
        const role = "character" in item ? item.character : "job" in item ? item.job : "";
        return {
          ...mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie"),
          subtitle: role ? `as ${role}` : item.media_type === "tv" ? "Series" : "Movie"
        };
      })
      .filter((item) => {
        const key = `${item.mediaType}:${item.id}`;
        if (seen.has(key)) return false;
        seen.add(key);
        return true;
      })
      .slice(0, 18);
    const mapped: PersonDetails = {
      id: person.id,
      name: person.name ?? "Unknown",
      biography: person.biography ?? "",
      placeOfBirth: person.place_of_birth ?? null,
      birthday: person.birthday ?? null,
      profilePath: tmdbImageUrl(config.imageBase, person.profile_path),
      knownFor
    };
    personCache.set(personId, mapped);
    return mapped;
  } catch {
    personCache.set(personId, null);
    return null;
  }
}

// Per-episode IMDb ratings, keyed "season:episode".
//
// Cinemeta lists every episode but its per-episode `rating` is always 0 (checked
// across several series), so reading it here left every episode falling back to
// TMDB's vote_average — a different score under an IMDb badge. Cinemeta still
// provides the episode->imdb-id MAP, and Agregarr turns a batch of those ids
// into real IMDb ratings in one keyless call; that is the same pairing the
// Android app uses (MediaRepository.getAgregarrImdbRatings).
// Per-episode IMDb ratings for ONE season, keyed "season:episode".
//
// Cinemeta lists episodes but its per-episode `rating` is always 0 and it has
// no per-episode imdb id (both verified across several series), so the old
// implementation always came back empty and episodes silently fell back to
// TMDB's vote_average under an IMDb badge. TMDB's season response CAN hand out
// an imdb id per episode (append_to_response=external_ids), and Agregarr turns
// a batch of those into real IMDb ratings in one keyless request — the same
// pairing the Android app uses (MediaRepository.getAgregarrImdbRatings).
async function getSeasonEpisodeRatings(tvId: number, seasonNumber: number) {
  const cacheKey = `${tvId}:${seasonNumber}`;
  if (seriesEpisodeRatingsCache.has(cacheKey)) return seriesEpisodeRatingsCache.get(cacheKey)!;
  const ratings = new Map<string, string>();
  try {
    // TMDB only exposes an episode's imdb id on the per-episode endpoint
    // (append_to_response on the season response leaves external_ids null), so
    // the ids are gathered with a small throttled fan-out — measured at ~740ms
    // for a 10-episode season — and the result is cached per season.
    const episodeNumbers = episodeNumbersFor(tvId, seasonNumber);
    const slots: Array<{ key: string; imdbId: string }> = [];
    let cursor = 0;
    const workers = Array.from({ length: Math.min(8, episodeNumbers.length) }, async () => {
      while (cursor < episodeNumbers.length) {
        const index = cursor;
        cursor += 1;
        const number = episodeNumbers[index];
        const payload = await tmdb<{ external_ids?: { imdb_id?: string | null } }>(
          `tv/${tvId}/season/${seasonNumber}/episode/${number}`,
          { append_to_response: "external_ids" }
        ).catch(() => null);
        const raw = String(payload?.external_ids?.imdb_id ?? "").trim();
        if (/^tt\d+$/.test(raw)) slots.push({ key: `${seasonNumber}:${number}`, imdbId: raw });
      }
    });
    await Promise.all(workers);
    if (slots.length) {
      const { getImdbRatings } = await import("./imdbRatings");
      const byId = await getImdbRatings(slots.map((slot) => slot.imdbId));
      slots.forEach((slot) => {
        const rating = byId[slot.imdbId.toLowerCase()];
        if (rating) ratings.set(slot.key, rating);
      });
    }
  } catch {
    // Ratings are cosmetic; an empty map just means the row shows no badge.
  }
  seriesEpisodeRatingsCache.set(cacheKey, ratings);
  return ratings;
}

// Episode numbers of a season, taken from the season payload the caller has
// already fetched (registered below) so this never costs an extra request.
const seasonEpisodeNumbers = new Map<string, number[]>();
function episodeNumbersFor(tvId: number, seasonNumber: number) {
  return seasonEpisodeNumbers.get(`${tvId}:${seasonNumber}`) ?? [];
}
export function registerSeasonEpisodeNumbers(tvId: number, seasonNumber: number, numbers: number[]) {
  seasonEpisodeNumbers.set(`${tvId}:${seasonNumber}`, numbers);
}

function normalizeRating(value: unknown) {
  const parsed = typeof value === "number" ? value : Number(String(value ?? "").trim());
  if (!Number.isFinite(parsed) || parsed <= 0) return "";
  return Math.max(0, Math.min(10, parsed)).toFixed(1);
}

const basicItemCache = new Map<string, MediaItem | null>();

/** Lightweight details fetch (no append_to_response) with an in-memory cache — used to hydrate catalog rows. */
export async function getCollectionPreview(item: MediaItem, language: string): Promise<MediaItem> {
  if (isAddonNative(item)) return (await getNativeDetails(item)) ?? item;
  const details = await tmdb<TmdbItem>(`${item.mediaType}/${item.id}`, { language, append_to_response: "external_ids" });
  return { ...item, ...mapTmdbItem(details, item.mediaType), budget: details.budget,
    imdbId: details.external_ids?.imdb_id ?? item.imdbId, genres: details.genres?.map(genre => genre.name) };
}

export async function getBasicItem(mediaType: MediaType, id: number, language = "en-US"): Promise<MediaItem | null> {
  const key = `${mediaType}:${id}`;
  if (basicItemCache.has(key)) return basicItemCache.get(key) ?? null;
  try {
    const details = await tmdb<TmdbItem>(`${mediaType}/${id}`, { language });
    // mdblist and other ID-based lists can reference adult titles that the
    // discover include_adult=false guard never touched; drop them here.
    if (details.adult) {
      basicItemCache.set(key, null);
      return null;
    }
    const mapped = mapTmdbItem({ ...details, media_type: mediaType }, mediaType);
    basicItemCache.set(key, mapped);
    return mapped;
  } catch {
    basicItemCache.set(key, null);
    return null;
  }
}

// Raw TMDB details payloads cached for a few minutes so hover-prefetch makes
// the details page open instantly (the per-item merge still runs per call).
const detailsPayloadCache = new Map<string, { at: number; payload: TmdbItem }>();
const DETAILS_CACHE_TTL_MS = 10 * 60 * 1000;

async function fetchDetailsPayload(item: MediaItem, customApiKey?: string) {
  const key = `${item.mediaType}-${item.id}`;
  const cached = detailsPayloadCache.get(key);
  if (cached && Date.now() - cached.at < DETAILS_CACHE_TTL_MS) return cached.payload;
  const payload = await tmdb<TmdbItem>(`${item.mediaType}/${item.id}`, {
    language: "en-US",
    append_to_response: "credits,videos,similar,recommendations,external_ids,watch/providers"
  }, customApiKey);
  detailsPayloadCache.set(key, { at: Date.now(), payload });
  if (detailsPayloadCache.size > 60) {
    const oldest = [...detailsPayloadCache.entries()].sort((a, b) => a[1].at - b[1].at)[0];
    if (oldest) detailsPayloadCache.delete(oldest[0]);
  }
  return payload;
}

/** Warm the details cache from a card hover; failures are irrelevant. */
export function prefetchDetails(item: MediaItem) {
  if (item.id <= 0 || item.isHomeServer) return;
  void fetchDetailsPayload(item).catch(() => undefined);
}

/**
 * Fetch the English and localized titles for a title, used by the Telegram
 * source resolver to search files named in either language (mirrors Android's
 * TelegramSourceResolver.fetchTitles). Returns nulls on any failure.
 */
export async function getTitlesForSearch(
  item: MediaItem,
  language: string
): Promise<{ english: string | null; localized: string | null }> {
  if (!item.id || item.id <= 0) return { english: null, localized: null };
  const type = item.mediaType === "tv" ? "tv" : "movie";
  const langCode = language.replace("iw", "he").split("-")[0];
  const nameOf = (d: { title?: string; name?: string } | null) =>
    (d?.title ?? d?.name)?.trim() || null;
  try {
    const en = await tmdb<{ title?: string; name?: string }>(`${type}/${item.id}`, { language: "en" }).catch(() => null);
    const english = nameOf(en);
    let localized: string | null = null;
    if (langCode !== "en") {
      const loc = await tmdb<{ title?: string; name?: string }>(`${type}/${item.id}`, { language: langCode }).catch(() => null);
      // Only keep the localized title when it actually differs from English —
      // otherwise TMDB just echoed the English name and there's nothing to add.
      const locName = nameOf(loc);
      localized = locName && locName.toLowerCase() !== (english ?? "").toLowerCase() ? locName : null;
    }
    return { english, localized };
  } catch {
    return { english: null, localized: null };
  }
}

export async function getDetails(item: MediaItem, priorityConfig?: ProviderPriorityConfig) {
  if (isAddonNative(item)) return (await getNativeDetails(item).catch(() => null)) ?? item;
  try {
    const details = await fetchDetailsPayload(item, priorityConfig?.customTmdbApiKey);
    const mapped = mapTmdbItem({ ...details, media_type: item.mediaType }, item.mediaType);
    const anime = item.isAnime || isAnime(item) || isAnime(details);
    const aniZipMapping = anime
      ? await fetchAniZipMappingByTmdbId(item.id).catch(() => null)
      : null;
    const anilistId = item.anilistId ?? aniZipMapping?.anilistId ?? null;
    const tvdbId = details.external_ids?.tvdb_id ?? item.tvdbId ?? aniZipMapping?.tvdbId ?? null;
    const imdbId = details.external_ids?.imdb_id ?? item.imdbId ?? null;

    let resolvedMeta: MediaItem | null = null;
    const metadataType: MetadataMediaType = anime ? "anime" : item.mediaType;
    const externalConfig = providersBeforeTmdb(metadataType, priorityConfig);
    if (externalConfig) {
      const lookup: MetadataLookupIds = {
        tmdb: item.id,
        tvdb: tvdbId,
        anilist: anilistId
      };
      resolvedMeta = await MetadataDispatcher.getDetails(lookup, metadataType, externalConfig).catch(() => null);
    }

    const trailer = details.videos?.results?.find((video) => video.site === "YouTube" && video.type === "Trailer" && video.official)
      ?? details.videos?.results?.find((video) => video.site === "YouTube" && video.type === "Trailer")
      ?? details.videos?.results?.find((video) => video.site === "YouTube");
    const related = [...(details.recommendations?.results ?? []), ...(details.similar?.results ?? [])]
      .filter((candidate, index, arr) => arr.findIndex((x) => x.id === candidate.id) === index)
      .slice(0, 18)
      .map((candidate) => mapTmdbItem({ ...candidate, media_type: item.mediaType }, item.mediaType));
    return {
      ...item,
      ...mapped,
      id: item.id, // Preserve canonical TMDB ID
      anilistId: resolvedMeta?.anilistId ?? anilistId,
      tvdbId: tvdbId ?? resolvedMeta?.tvdbId ?? item.tvdbId ?? null,
      ...(resolvedMeta ? {
        title: resolvedMeta.title || mapped.title,
        overview: resolvedMeta.overview || mapped.overview,
        rating: resolvedMeta.rating || mapped.rating,
        badge: resolvedMeta.badge || mapped.badge,
        image: resolvedMeta.image || mapped.image,
        backdrop: resolvedMeta.backdrop || mapped.backdrop
      } : {}),
      rating: resolvedMeta?.rating || mapped.rating || item.rating,
      imdbId,
      genres: (details.genres ?? []).map((genre) => genre.name).filter(Boolean),
      status: resolvedMeta?.status || (details.status ?? null),
      budget: details.budget ?? null,
      revenue: details.revenue ?? null,
      originalLanguage: details.original_language ?? null,
      providerLogos: pickProviderLogos(details),
      networkLogos: (details.networks ?? details.production_companies ?? [])
        .filter((provider) => provider.logo_path && provider.name)
        .slice(0, 4)
        .map((provider) => ({
          name: provider.name ?? "",
          logo: `${config.imageBase}${provider.logo_path}`
        })),
      numberOfSeasons: details.number_of_seasons ?? null,
      numberOfEpisodes: details.number_of_episodes ?? null,
      lastAirDate: details.last_air_date ?? null,
      trailerUrl: trailer?.key ? `https://www.youtube.com/watch?v=${trailer.key}` : null,
      cast: (details.credits?.cast ?? []).slice(0, 12).map((person) => ({
        id: person.id,
        name: person.name ?? "Unknown",
        character: person.character,
        image: person.profile_path ? `${config.imageBase}${person.profile_path}` : ""
      })),
      seasons: item.mediaType === "tv" ? (details.seasons ?? [])
        .filter((season) => season.season_number != null && season.season_number >= 0)
        .map((season) => ({
          id: season.id,
          seasonNumber: season.season_number ?? 0,
          name: season.name ?? `Season ${season.season_number ?? ""}`,
          episodeCount: season.episode_count,
          poster: season.poster_path ? `${config.imageBase}${season.poster_path}` : ""
        })) : [],
      related
    };
  } catch {
    return item;
  }
}

function pickProviderLogos(details: TmdbItem) {
  const providers = details["watch/providers"]?.results ?? {};
  const region = providers.NL ?? providers.US ?? Object.values(providers)[0];
  const entries = [...(region?.flatrate ?? []), ...(region?.free ?? []), ...(region?.ads ?? [])];
  const seen = new Set<number | string>();
  return entries
    .filter((provider) => provider.logo_path && provider.provider_name)
    .filter((provider) => {
      const key = provider.provider_id ?? provider.provider_name ?? provider.logo_path ?? "";
      if (seen.has(key)) return false;
      seen.add(key);
      return true;
    })
    .slice(0, 4)
    .map((provider) => ({
      name: provider.provider_name ?? "",
      logo: `${config.imageBase}${provider.logo_path}`
    }));
}

const fallbackItems: MediaItem[] = [
  {
    id: 10001,
    title: "Echoes of Dawn",
    subtitle: "S1 E6",
    overview: "Long-buried secrets surface as a small crew follows a signal across a collapsing horizon.",
    year: "2026",
    rating: "8.7",
    duration: "45m",
    mediaType: "tv",
    image: "",
    backdrop: null,
    progress: 72
  },
  {
    id: 10002,
    title: "Neon Paradox",
    subtitle: "Cyber noir thriller",
    overview: "A detective chases a synthetic witness through a city where memory is traded like currency.",
    year: "2025",
    rating: "8.2",
    duration: "2h 10m",
    mediaType: "movie",
    image: "",
    backdrop: null
  },
  {
    id: 10003,
    title: "Beyond the Wilds",
    subtitle: "S2 E3",
    overview: "A survival drama moves into stranger terrain when the map stops matching the world.",
    year: "2026",
    rating: "8.0",
    duration: "50m",
    mediaType: "tv",
    image: "",
    backdrop: null,
    progress: 28
  },
  {
    id: 10004,
    title: "The Architect",
    subtitle: "Action cinema",
    overview: "A retired planner is pulled back into the city he designed to stop a hostile takeover.",
    year: "2025",
    rating: "7.9",
    duration: "1h 58m",
    mediaType: "movie",
    image: "",
    backdrop: null
  }
];

const fallbackCategories: Category[] = [
  { id: "continue_watching_demo", title: "Continue Watching", items: fallbackItems.filter((item) => item.progress) },
  { id: "trending_movies_demo", title: "Trending in Movies", items: fallbackItems.filter((item) => item.mediaType === "movie") },
  { id: "trending_tv_demo", title: "Trending in Shows", items: fallbackItems.filter((item) => item.mediaType === "tv") }
];

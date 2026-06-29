import { config } from "./config";
import { jsonRequest, proxiedUrl } from "./http";
import type { CatalogConfig, Category, EpisodeInfo, MediaItem, MediaType, ReviewInfo } from "./types";

type TmdbItem = {
  id: number;
  title?: string;
  name?: string;
  overview?: string;
  poster_path?: string | null;
  backdrop_path?: string | null;
  vote_average?: number;
  release_date?: string;
  first_air_date?: string;
  media_type?: string;
  genre_ids?: number[];
  runtime?: number;
  episode_run_time?: number[];
  number_of_seasons?: number;
  genres?: Array<{ id: number; name: string }>;
  credits?: {
    cast?: Array<{ id: number; name?: string; character?: string; profile_path?: string | null }>;
  };
  videos?: {
    results?: Array<{ key?: string; site?: string; type?: string; official?: boolean }>;
  };
  seasons?: Array<{ id: number; season_number?: number; name?: string; episode_count?: number; poster_path?: string | null }>;
  similar?: TmdbList;
  recommendations?: TmdbList;
};

type TmdbList = { results: TmdbItem[] };
type MdblistItem = Record<string, unknown>;

function yearFrom(date?: string) {
  return date?.slice(0, 4) ?? "";
}

export function mapTmdbItem(item: TmdbItem, fallbackType: MediaType): MediaItem {
  const mediaType: MediaType = item.media_type === "tv" || fallbackType === "tv" ? "tv" : "movie";
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
    image: item.poster_path ? `${config.imageBase}${item.poster_path}` : "",
    backdrop: item.backdrop_path ? `${config.backdropBase}${item.backdrop_path}` : null,
    genreIds: item.genre_ids ?? []
  };
}

async function tmdb<T>(path: string, params: Record<string, string | number | undefined> = {}) {
  const url = new URL(`/api/tmdb/${path.replace(/^\/+/, "")}`, window.location.origin);
  Object.entries(params).forEach(([key, value]) => {
    if (value !== undefined && value !== "") url.searchParams.set(key, String(value));
  });
  return jsonRequest<T>(url.toString());
}

export async function loadHomeCategories(language = "en-US", catalogs?: CatalogConfig[]): Promise<Category[]> {
  if (catalogs?.length) {
    const enabled = catalogs.filter((catalog) => catalog.enabled).slice(0, 36);
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

export async function loadCatalog(catalog: CatalogConfig, language = "en-US"): Promise<Category | null> {
  if (!catalog.enabled) return null;
  if (catalog.sourceType === "tmdb" && catalog.endpoint) {
    const response = await tmdb<TmdbList>(catalog.endpoint, { language, ...(catalog.params ?? {}) });
    return {
      id: catalog.id,
      title: catalog.name,
      items: response.results.map((x) => mapTmdbItem(x, catalog.mediaType === "tv" ? "tv" : "movie")),
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

  if (catalog.sourceType === "preinstalled" && catalog.id === "favorite_tv") {
    const response = await tmdb<TmdbList>("discover/tv", {
      language,
      sort_by: "vote_average.desc",
      "vote_count.gte": 500
    });
    return {
      id: catalog.id,
      title: catalog.name,
      items: response.results.map((x) => mapTmdbItem(x, "tv")),
      sourceLabel: "TMDB",
      layout: catalog.layout ?? "landscape"
    };
  }

  return null;
}

async function loadMdblist(catalog: CatalogConfig, language: string) {
  const url = `${catalog.sourceUrl!.replace(/\/+$/, "")}/json`;
  const payload = await jsonRequest<unknown>(proxiedUrl(url));
  const rawItems = Array.isArray(payload)
    ? payload
    : Array.isArray((payload as { items?: unknown[] }).items)
      ? (payload as { items: unknown[] }).items
      : Array.isArray((payload as { movies?: unknown[] }).movies)
        ? (payload as { movies: unknown[] }).movies
        : [];
  const ids = rawItems
    .map((item) => extractMdblistIdentity(item as MdblistItem, catalog.mediaType))
    .filter((item): item is { id: number; type: MediaType } => Boolean(item?.id))
    .slice(0, 20);
  const details = await Promise.all(ids.map((item) => getBasicItem(item.type, item.id, language).catch(() => null)));
  return details.filter((item): item is MediaItem => Boolean(item?.title));
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

export async function searchMedia(query: string, language = "en-US") {
  if (!query.trim()) return [];
  const response = await tmdb<TmdbList>("search/multi", { query, language });
  return response.results
    .filter((item) => item.media_type === "movie" || item.media_type === "tv")
    .map((item) => mapTmdbItem(item, item.media_type === "tv" ? "tv" : "movie"));
}

const seasonCache = new Map<string, EpisodeInfo[]>();

export async function getSeasonEpisodes(tvId: number, seasonNumber: number, language = "en-US"): Promise<EpisodeInfo[]> {
  const key = `${tvId}:${seasonNumber}`;
  if (seasonCache.has(key)) return seasonCache.get(key)!;
  try {
    const season = await tmdb<{ episodes?: Array<{ id: number; episode_number: number; name?: string; overview?: string; still_path?: string | null; vote_average?: number; air_date?: string; runtime?: number }> }>(
      `tv/${tvId}/season/${seasonNumber}`,
      { language }
    );
    const episodes = (season.episodes ?? []).map((episode) => ({
      id: episode.id,
      episodeNumber: episode.episode_number,
      seasonNumber,
      name: episode.name || `Episode ${episode.episode_number}`,
      overview: episode.overview ?? "",
      still: episode.still_path ? `${config.imageBase}${episode.still_path}` : undefined,
      voteAverage: episode.vote_average ?? 0,
      airDate: episode.air_date ?? "",
      runtime: episode.runtime ?? 0
    }));
    seasonCache.set(key, episodes);
    return episodes;
  } catch {
    return [];
  }
}

export async function getReviews(item: { mediaType: MediaType; id: number }): Promise<ReviewInfo[]> {
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

const basicItemCache = new Map<string, MediaItem | null>();

/** Lightweight details fetch (no append_to_response) with an in-memory cache — used to hydrate catalog rows. */
export async function getBasicItem(mediaType: MediaType, id: number, language = "en-US"): Promise<MediaItem | null> {
  const key = `${mediaType}:${id}`;
  if (basicItemCache.has(key)) return basicItemCache.get(key) ?? null;
  try {
    const details = await tmdb<TmdbItem>(`${mediaType}/${id}`, { language });
    const mapped = mapTmdbItem({ ...details, media_type: mediaType }, mediaType);
    basicItemCache.set(key, mapped);
    return mapped;
  } catch {
    basicItemCache.set(key, null);
    return null;
  }
}

export async function getDetails(item: MediaItem) {
  try {
    const details = await tmdb<TmdbItem>(`${item.mediaType}/${item.id}`, {
      language: "en-US",
      append_to_response: "credits,videos,similar,recommendations"
    });
    const mapped = mapTmdbItem({ ...details, media_type: item.mediaType }, item.mediaType);
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
      rating: mapped.rating || item.rating,
      trailerUrl: trailer?.key ? `https://www.youtube.com/watch?v=${trailer.key}` : null,
      cast: (details.credits?.cast ?? []).slice(0, 12).map((person) => ({
        id: person.id,
        name: person.name ?? "Unknown",
        character: person.character,
        image: person.profile_path ? `${config.imageBase}${person.profile_path}` : ""
      })),
      seasons: item.mediaType === "tv" ? (details.seasons ?? [])
        .filter((season) => (season.season_number ?? 0) > 0)
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

/**
 * Search & Discover model, shared with the Android app's search screen
 * (ui/screens/search: SearchViewModel genre lists, DiscoverFilters,
 * RecentSearchRepository). Pure: no network, no React.
 */
export type SearchType = "all" | "movie" | "tv";
/** `label` (not `name`) so the translation extractor collects every genre. */
export type Genre = { id: number; label: string };

// Android SearchViewModel MOVIE_GENRES / TV_GENRES / ALL_GENRES.
export const MOVIE_GENRES: Genre[] = [
  { id: 28, label: "Action" }, { id: 12, label: "Adventure" }, { id: 16, label: "Animation" },
  { id: 35, label: "Comedy" }, { id: 80, label: "Crime" }, { id: 99, label: "Documentary" },
  { id: 18, label: "Drama" }, { id: 10751, label: "Family" }, { id: 14, label: "Fantasy" },
  { id: 36, label: "History" }, { id: 27, label: "Horror" }, { id: 10402, label: "Music" },
  { id: 9648, label: "Mystery" }, { id: 10749, label: "Romance" }, { id: 878, label: "Sci-Fi" },
  { id: 53, label: "Thriller" }, { id: 10752, label: "War" }, { id: 37, label: "Western" }
];
export const TV_GENRES: Genre[] = [
  { id: 10759, label: "Action & Adventure" }, { id: 16, label: "Animation" },
  { id: 35, label: "Comedy" }, { id: 80, label: "Crime" }, { id: 99, label: "Documentary" },
  { id: 18, label: "Drama" }, { id: 10751, label: "Family" }, { id: 10762, label: "Kids" },
  { id: 9648, label: "Mystery" }, { id: 10765, label: "Sci-Fi & Fantasy" },
  { id: 10768, label: "War & Politics" }, { id: 37, label: "Western" }
];
export const ALL_GENRES: Genre[] = [
  { id: 28, label: "Action" }, { id: 12, label: "Adventure" }, { id: 16, label: "Animation" },
  { id: 35, label: "Comedy" }, { id: 80, label: "Crime" }, { id: 99, label: "Documentary" },
  { id: 18, label: "Drama" }, { id: 10751, label: "Family" }, { id: 14, label: "Fantasy" },
  { id: 27, label: "Horror" }, { id: 9648, label: "Mystery" }, { id: 10749, label: "Romance" },
  { id: 878, label: "Sci-Fi" }, { id: 53, label: "Thriller" }, { id: 10752, label: "War" },
  { id: 37, label: "Western" }
];

export function genresFor(type: SearchType): Genre[] {
  return type === "movie" ? MOVIE_GENRES : type === "tv" ? TV_GENRES : ALL_GENRES;
}

/** Android remapGenreId: the same genre in the other numbering (Action 28 ↔ Action & Adventure 10759). */
export function remapGenreId(id: number, type: "movie" | "tv"): number {
  if (type === "tv") return ({ 28: 10759, 12: 10759, 14: 10765, 878: 10765, 10752: 10768 } as Record<number, number>)[id] ?? id;
  return ({ 10759: 28, 10765: 878, 10768: 10752, 10762: 10751 } as Record<number, number>)[id] ?? id;
}

/** A genre kept across a type switch where a counterpart exists, else dropped. */
export function remapGenreForType(genre: string, type: SearchType): string {
  if (!genre) return "";
  const id = Number(genre);
  const mapped = type === "all" ? id : remapGenreId(id, type);
  return genresFor(type).some((candidate) => candidate.id === mapped) ? String(mapped) : "";
}

// Android DISCOVER_LANGUAGES is ja/ko/hi; the web offers the same three plus
// the larger catalogues users filter by most (Android COUNTRIES list).
export const DISCOVER_LANGUAGES: Array<{ code: string; label: string }> = [
  { code: "en", label: "English" }, { code: "ja", label: "Japanese" }, { code: "ko", label: "Korean" },
  { code: "hi", label: "Hindi" }, { code: "es", label: "Spanish" }, { code: "fr", label: "French" },
  { code: "de", label: "German" }, { code: "it", label: "Italian" }, { code: "zh", label: "Chinese" },
  { code: "tr", label: "Turkish" }
];

/** Whole-point rating floors (Android RATING_VALUES, whole numbers only). */
export const RATING_FLOORS = [9, 8, 7, 6, 5];

export type Decade = { start: number; end: number };
/** Android decadeOptions: the last five decades, newest first, then everything older. */
export function decadeOptions(currentYear: number, oldest = 1950): Decade[] {
  const newest = Math.floor(currentYear / 10) * 10;
  const full = Array.from({ length: 5 }, (_, i) => ({ start: newest - i * 10, end: newest - i * 10 + 9 }));
  const tailEnd = full[full.length - 1].start - 1;
  return tailEnd >= oldest ? [...full, { start: oldest, end: tailEnd }] : full;
}

export const SORTS = ["popularity.desc", "vote_average.desc", "release.desc"] as const;
export type SortKey = typeof SORTS[number];

export type DiscoverFilters = {
  type: SearchType;
  genre: string;
  rating: string;
  decade: string;
  language: string;
  sort: SortKey;
};

export const EMPTY_FILTERS: DiscoverFilters = { type: "all", genre: "", rating: "", decade: "", language: "", sort: "popularity.desc" };

/** Any filter that narrows Discover (the type alone just picks the catalogue). */
export function hasDiscoverFilters(filters: DiscoverFilters) {
  return Boolean(filters.genre || filters.rating || filters.decade || filters.language || filters.sort !== "popularity.desc");
}

function isoDay(date: Date) {
  return date.toISOString().slice(0, 10);
}

/**
 * TMDB discover parameters for one media type. Every filter is applied by
 * TMDB itself, so a page is never emptied after the fact (the old genre
 * filter ran client-side on search pages and left empty pages behind).
 */
export function discoverParams(filters: DiscoverFilters, mediaType: "movie" | "tv", today = new Date()): Record<string, string> {
  const dateField = mediaType === "tv" ? "first_air_date" : "primary_release_date";
  const params: Record<string, string> = {
    sort_by: filters.sort === "release.desc" ? `${dateField}.desc` : filters.sort,
    include_adult: "false"
  };
  if (filters.genre) params.with_genres = String(mediaType === "tv" ? remapGenreId(Number(filters.genre), "tv") : remapGenreId(Number(filters.genre), "movie"));
  if (filters.rating) params["vote_average.gte"] = filters.rating;
  // Rated/newest lists need a vote floor or they fill with unrated entries.
  if (filters.rating || filters.sort === "vote_average.desc") params["vote_count.gte"] = filters.sort === "vote_average.desc" ? "200" : "50";
  if (filters.language) params.with_original_language = filters.language;
  const cap = isoDay(today);
  if (filters.decade) {
    const [from, to] = filters.decade.split("-").map(Number);
    params[`${dateField}.gte`] = `${from}-01-01`;
    params[`${dateField}.lte`] = `${to}-12-31` < cap ? `${to}-12-31` : cap;
  } else if (filters.sort === "release.desc") {
    // Newest = out already: future releases have no artwork or rating yet.
    params[`${dateField}.lte`] = cap;
  }
  return params;
}

type MultiResult = { media_type?: string; id: number; [key: string]: unknown };
/** search/multi rows split into the sections the screen shows. */
export function splitMultiResults<T extends MultiResult>(rows: T[]) {
  return {
    movies: rows.filter((row) => row.media_type === "movie"),
    shows: rows.filter((row) => row.media_type === "tv"),
    people: rows.filter((row) => row.media_type === "person")
  };
}

// --- Recent searches (Android RecentSearchRepository: 8 per profile, newest first) ---
export const MAX_RECENT_SEARCHES = 8;
export const MIN_RECENT_SEARCH_LENGTH = 2;
export const recentSearchesKey = (profileId: string) => `arvio.web.recentSearches.v1:${profileId || "local"}`;

export function withRecentSearch(current: string[], query: string, max = MAX_RECENT_SEARCHES): string[] {
  const trimmed = query.trim();
  if (trimmed.length < MIN_RECENT_SEARCH_LENGTH) return current;
  return [trimmed, ...current.filter((entry) => entry.toLowerCase() !== trimmed.toLowerCase())].slice(0, max);
}

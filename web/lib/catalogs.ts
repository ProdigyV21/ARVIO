import type { CatalogConfig } from "./types";

export const defaultCatalogs: CatalogConfig[] = [
  { id: "favorite_tv", name: "Favorite TV", sourceType: "preinstalled", mediaType: "tv", enabled: true, isPreinstalled: true },
  { id: "trending_movies", name: "Trending in Movies", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/snoak/trending-movies", enabled: true, isPreinstalled: true },
  { id: "trending_tv", name: "Trending in Shows", sourceType: "mdblist", mediaType: "tv", sourceUrl: "https://mdblist.com/lists/snoak/trakt-s-trending-shows", enabled: true, isPreinstalled: true },
  { id: "trending_anime", name: "Trending in Anime", sourceType: "mdblist", mediaType: "tv", sourceUrl: "https://mdblist.com/lists/snoak/trending-anime-shows", enabled: true, isPreinstalled: true },
  { id: "top10_movies_today", name: "Top 10 Movies Today", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/snoak/top-10-movies-of-the-day", enabled: true, isPreinstalled: true },
  { id: "top10_shows_today", name: "Top 10 Shows Today", sourceType: "mdblist", mediaType: "tv", sourceUrl: "https://mdblist.com/lists/snoak/top-10-shows-of-the-day", enabled: true, isPreinstalled: true },
  { id: "just_added", name: "Just Added", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/snoak/latest-movies-digital-release", enabled: true, isPreinstalled: true },
  { id: "latest_tv", name: "Latest Airing", sourceType: "mdblist", mediaType: "tv", sourceUrl: "https://mdblist.com/lists/snoak/latest-tv-shows", enabled: true, isPreinstalled: true },
  { id: "top_movies_week", name: "Top Movies This Week", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/linaspurinis/top-watched-movies-of-the-week", enabled: true, isPreinstalled: true },
  { id: "new_kdramas", name: "New in K-Dramas", sourceType: "mdblist", mediaType: "tv", sourceUrl: "https://mdblist.com/lists/snoak/latest-kdrama-shows", enabled: true, isPreinstalled: true },
  { id: "coming_soon", name: "Coming Soon", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/snoak/upcoming-movies", enabled: true, isPreinstalled: true },
  { id: "netflix", name: "Netflix", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/garycrawfordgc/netflix-shows", enabled: true, isPreinstalled: true },
  { id: "disney", name: "Disney+", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/garycrawfordgc/disney-shows", enabled: true, isPreinstalled: true },
  { id: "prime", name: "Prime Video", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/garycrawfordgc/amazon-prime-shows", enabled: true, isPreinstalled: true },
  { id: "hbo", name: "HBO Max", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/garycrawfordgc/hbo-max-shows", enabled: true, isPreinstalled: true },
  { id: "apple_tv", name: "Apple TV+", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/garycrawfordgc/apple-tv-shows", enabled: true, isPreinstalled: true },
  { id: "action", name: "Popular Action", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/action-movies", enabled: true, isPreinstalled: true },
  { id: "comedy", name: "Popular Comedy", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/comedy-movies", enabled: true, isPreinstalled: true },
  { id: "scifi", name: "Popular Sci-Fi", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/science-fiction-movies", enabled: true, isPreinstalled: true },
  { id: "thriller", name: "Popular Thriller", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/thriller-movies", enabled: true, isPreinstalled: true },
  { id: "drama", name: "Popular Drama", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/drama-movies", enabled: true, isPreinstalled: true },
  { id: "horror", name: "Popular Horror", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/horror-movies", enabled: true, isPreinstalled: true },
  { id: "documentary", name: "Popular Documentary", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/popular-documentary-movies", enabled: true, isPreinstalled: true },
  { id: "romance", name: "Popular Romance", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/popular-romance-movies", enabled: true, isPreinstalled: true },
  { id: "animated", name: "Popular Animated", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/snoak/animationanime-movies", enabled: true, isPreinstalled: true },
  { id: "family", name: "Popular Family", sourceType: "mdblist", mediaType: "all", sourceUrl: "https://mdblist.com/lists/familytv133/family-kids-english-movies-rated-g-pg", enabled: true, isPreinstalled: true },
  { id: "bond", name: "James Bond Collection", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/hdlists/james-bond-movies", enabled: true, isPreinstalled: true },
  { id: "harry_potter", name: "Harry Potter Collection", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/thebirdod/harry-potter-collection", enabled: true, isPreinstalled: true },
  { id: "matrix", name: "The Matrix Collection", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/andyhawks/universe-the-matrix", enabled: true, isPreinstalled: true },
  { id: "lotr", name: "Lord of the Rings and Hobbit Collection", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/spudhead15/lord-of-the-rings-and-hobbit-collection", enabled: true, isPreinstalled: true },
  { id: "jurassic", name: "Jurassic Park Collection", sourceType: "mdblist", mediaType: "movie", sourceUrl: "https://mdblist.com/lists/purple_smurf/jurassic-park", enabled: true, isPreinstalled: true },
  { id: "tmdb_popular_movies", name: "Popular Movies", sourceType: "tmdb", mediaType: "movie", endpoint: "discover/movie", params: { sort_by: "popularity.desc" }, enabled: true, isPreinstalled: true },
  { id: "tmdb_popular_tv", name: "Popular Series", sourceType: "tmdb", mediaType: "tv", endpoint: "discover/tv", params: { sort_by: "popularity.desc" }, enabled: true, isPreinstalled: true }
];

export function mergeCatalogs(saved: CatalogConfig[] | undefined, hiddenIds: string[] = []) {
  const savedById = new Map((saved ?? []).map((catalog) => [catalog.id, catalog]));
  const merged = defaultCatalogs.map((catalog) => ({
    ...catalog,
    ...savedById.get(catalog.id),
    enabled: !hiddenIds.includes(catalog.id) && (savedById.get(catalog.id)?.enabled ?? catalog.enabled)
  }));
  const custom = (saved ?? []).filter((catalog) => !defaultCatalogs.some((base) => base.id === catalog.id));
  return [...merged, ...custom];
}

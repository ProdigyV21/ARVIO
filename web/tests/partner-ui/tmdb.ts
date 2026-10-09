import type { MediaItem } from "../../lib/types";

export async function tmdb<T>(path: string): Promise<T> {
  const response = await fetch(`/fixture/tmdb/${path}`);
  if (!response.ok) throw new Error("Private provider diagnostics: api_key=fixture-secret");
  return response.json();
}
export function mapTmdbItem(item: { id: number; title?: string; name?: string }, mediaType: "movie" | "tv"): MediaItem {
  return { id: item.id, title: item.title ?? item.name ?? "", mediaType, overview: "Deterministic offline partner-link test data." };
}
export async function getDetails(item: MediaItem): Promise<MediaItem> {
  return { ...item, seasons: item.mediaType === "tv" ? [
    { id: 1, seasonNumber: 0, name: "Specials", episodeCount: 2 },
    { id: 2, seasonNumber: 1, name: "Season 1", episodeCount: 2 }
  ] : [], cast: [{ id: 1, name: "Fixture actor" }], related: [] };
}
export const getReviews = async () => [];
export const getLogoUrl = async () => null;
export const getPersonDetails = async () => null;
export const getImdbRating = async () => null;
export const getCardMeta = async () => ({});
export const getCardProviders = async () => [];
export const prefetchDetails = () => {};
export const resolveTmdbId = async (item: MediaItem) => item.id;
export const getSeasonEpisodes = async (_id: number, season: number) => [1, 2].map(episodeNumber => ({
  id: season * 100 + episodeNumber, seasonNumber: season, episodeNumber,
  name: `${season === 0 ? "Special" : "Episode"} ${episodeNumber}`, overview: "Offline episode metadata", still: "", airDate: "2026-01-01"
}));

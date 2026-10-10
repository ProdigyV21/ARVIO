import type { EpisodeInfo, MediaItem } from "./types";

export function episodeImdbRating(episode: EpisodeInfo): string | null {
  const rating = Number(episode.imdbRating);
  return Number.isFinite(rating) && rating > 0 && rating <= 10 ? rating.toFixed(1) : null;
}

export function formatEpisodeAirDate(value?: string, locale = "en-GB"): string {
  if (!value || !/^\d{4}-\d{2}-\d{2}$/.test(value)) return "";
  const date = new Date(`${value}T12:00:00Z`);
  if (!Number.isFinite(date.getTime()) || date.toISOString().slice(0, 10) !== value) return "";
  return new Intl.DateTimeFormat(locale, { day: "numeric", month: "short", year: "numeric", timeZone: "UTC" }).format(date);
}

export function currentPlayerEpisode(
  episodes: EpisodeInfo[], season: number | null | undefined, episode: number | null | undefined
): EpisodeInfo | undefined {
  if (season == null || episode == null) return undefined;
  return episodes.find(entry => entry.seasonNumber === season && entry.episodeNumber === episode);
}

export function playerPauseOverview(item: MediaItem | null, episode?: EpisodeInfo): string {
  return (item?.mediaType === "tv" ? episode?.overview?.trim() : "") || item?.overview?.trim() || "";
}

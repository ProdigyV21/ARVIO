import { config } from "./config";
import { getDetails } from "./tmdb";
import type { MediaItem, WatchHistoryEntry } from "./types";

export function historyToItem(entry: WatchHistoryEntry): MediaItem {
  const title = entry.media_type === "tv" && entry.episode_title
    ? `${entry.title ?? "Series"}: ${entry.episode_title}`
    : entry.title ?? "Untitled";
  const progress = Math.round((entry.progress ?? 0) * 100);
  const remaining = Math.max(0, (entry.duration_seconds ?? 0) - (entry.position_seconds ?? 0));
  return {
    id: entry.show_tmdb_id,
    title,
    subtitle: entry.media_type === "tv" ? `S${entry.season ?? 1} E${entry.episode ?? 1}` : "Movie",
    mediaType: entry.media_type,
    image: entry.poster_path ? `${config.imageBase}${entry.poster_path}` : "",
    backdrop: entry.backdrop_path ? `${config.backdropBase}${entry.backdrop_path}` : null,
    progress,
    timeRemainingLabel: remaining > 0 ? `${Math.ceil(remaining / 60)}m left` : null
  };
}

export function traktItemToMedia(raw: unknown): MediaItem {
  const item = raw as {
    type?: string;
    movie?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number } };
    show?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number } };
  };
  const media = item.movie ?? item.show;
  const mediaType = item.type === "show" ? "tv" : "movie";
  return {
    id: media?.ids?.tmdb ?? media?.ids?.trakt ?? Math.floor(Math.random() * 1000000),
    title: media?.title ?? "Untitled",
    year: media?.year ? String(media.year) : "",
    subtitle: mediaType === "tv" ? "TV Series" : "Movie",
    mediaType,
    traktId: media?.ids?.trakt ?? null
  };
}

export function traktPlaybackToMedia(raw: unknown): MediaItem {
  const item = raw as {
    progress?: number;
    movie?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number } };
    show?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number } };
    episode?: { season?: number; number?: number; title?: string };
  };
  const media = item.movie ?? item.show;
  const isShow = Boolean(item.show);
  return {
    id: media?.ids?.tmdb ?? media?.ids?.trakt ?? Math.floor(Math.random() * 1000000),
    title: isShow && item.episode?.title ? `${media?.title ?? "Series"}: ${item.episode.title}` : media?.title ?? "Untitled",
    year: media?.year ? String(media.year) : "",
    subtitle: isShow ? `S${item.episode?.season ?? 1} E${item.episode?.number ?? 1}` : "Movie",
    mediaType: isShow ? "tv" : "movie",
    traktId: media?.ids?.trakt ?? null,
    progress: Math.round(item.progress ?? 0)
  };
}

export function dedupeMedia(items: MediaItem[]) {
  const seen = new Set<string>();
  return items.filter((item) => {
    const key = `${item.mediaType}:${item.id}:${item.subtitle ?? ""}`;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

export async function hydrateTraktItems(items: MediaItem[]) {
  const hydrated = await Promise.all(items.slice(0, 80).map((item) => getDetails(item).catch(() => item)));
  return hydrated.map((item, index) => ({ ...item, badge: index < 10 ? `#${index + 1}` : item.badge }));
}

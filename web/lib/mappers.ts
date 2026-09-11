import { config } from "./config";
import { getDetails, resolveTmdbId } from "./tmdb";
import { tmdbImageUrl } from "./mediaImages";
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
    image: tmdbImageUrl(config.imageBase, entry.poster_path),
    backdrop: tmdbImageUrl(config.backdropBase, entry.backdrop_path) || null,
    episodeStill: tmdbImageUrl(config.backdropBase, entry.episode_still_path) || null,
    seasonNumber: entry.season ?? null,
    episodeNumber: entry.episode ?? null,
    episodeTitle: entry.episode_title ?? null,
    progress,
    resumePositionSeconds: entry.position_seconds ?? 0,
    durationSeconds: entry.duration_seconds ?? 0,
    streamAddonId: entry.stream_addon_id ?? null,
    activityAt: Date.parse(entry.updated_at ?? entry.paused_at ?? "") || 0,
    timeRemainingLabel: remaining > 0 ? `${Math.ceil(remaining / 60)}m left` : null
  };
}

// Negative IDs are local identities, never IDs in the TMDB namespace.
function trackerIdentity(media: { title?: string; ids?: { tmdb?: number; trakt?: number; imdb?: string } } | undefined): number {
  if (media?.ids?.tmdb && media.ids.tmdb > 0) return media.ids.tmdb;
  const key = String(media?.ids?.trakt ?? media?.ids?.imdb ?? media?.title ?? "unmatched");
  let hash = 2166136261;
  for (const char of key) hash = Math.imul(hash ^ char.charCodeAt(0), 16777619);
  return -((hash >>> 0) || 1);
}

export function traktItemToMedia(raw: unknown): MediaItem {
  const item = raw as {
    type?: string;
    listed_at?: string;
    movie?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
    show?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
  };
  const media = item.movie ?? item.show;
  const mediaType = item.show || item.type === "show" ? "tv" : "movie";
  return {
    id: trackerIdentity(media),
    title: media?.title ?? "Untitled",
    year: media?.year ? String(media.year) : "",
    subtitle: mediaType === "tv" ? "TV Series" : "Movie",
    mediaType,
    traktId: media?.ids?.trakt ?? null,
    imdbId: media?.ids?.imdb ?? null,
    // listed_at = when the user added it to their watchlist — the field
    // "Recently added" must sort by.
    activityAt: Date.parse(item.listed_at ?? "") || 0
  };
}

export function traktPlaybackToMedia(raw: unknown): MediaItem {
  const item = raw as {
    progress?: number;
    paused_at?: string;
    movie?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
    show?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
    episode?: { season?: number; number?: number; title?: string };
    is_up_next?: boolean;
  };
  const media = item.movie ?? item.show;
  const isShow = Boolean(item.show);
  return {
    activityAt: Date.parse(item.paused_at ?? "") || 0,
    id: trackerIdentity(media),
    title: isShow && item.episode?.title ? `${media?.title ?? "Series"}: ${item.episode.title}` : media?.title ?? "Untitled",
    year: media?.year ? String(media.year) : "",
    subtitle: isShow ? `S${item.episode?.season ?? 1} E${item.episode?.number ?? 1}` : "Movie",
    mediaType: isShow ? "tv" : "movie",
    traktId: media?.ids?.trakt ?? null,
    imdbId: media?.ids?.imdb ?? null,
    seasonNumber: item.episode?.season ?? null,
    episodeNumber: item.episode?.number ?? null,
    episodeTitle: item.episode?.title ?? null,
    progress: Math.round(item.progress ?? 0),
    badge: item.is_up_next ? "Up Next" : null,
    timeRemainingLabel: item.is_up_next ? "Up next" : null
  };
}

export function traktUpNextToMedia(watchedRaw: unknown, progressRaw: unknown): MediaItem | null {
  const watched = watchedRaw as {
    last_watched_at?: string;
    last_updated_at?: string;
    show?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
  };
  const progress = progressRaw as {
    aired?: number;
    completed?: number;
    reset_at?: string | null;
    last_watched_at?: string;
    next_episode?: { season?: number; number?: number; title?: string };
  } | null;
  const show = watched.show;
  const tmdbId = show?.ids?.tmdb;
  const nextEpisode = progress?.next_episode;
  if (!tmdbId || !nextEpisode?.season || !nextEpisode?.number) return null;
  const aired = Math.max(0, Number(progress?.aired ?? 0));
  const completed = Math.max(0, Number(progress?.completed ?? 0));
  if (aired > 0 && completed >= aired) return null;
  return {
    id: tmdbId,
    title: show?.title ?? "Untitled",
    year: show?.year ? String(show.year) : "",
    subtitle: `S${nextEpisode.season} E${nextEpisode.number}`,
    mediaType: "tv",
    traktId: show?.ids?.trakt ?? null,
    imdbId: show?.ids?.imdb ?? null,
    seasonNumber: nextEpisode.season,
    episodeNumber: nextEpisode.number,
    episodeTitle: nextEpisode.title ?? null,
    progress: aired > 0 ? Math.round((Math.min(completed, aired) / aired) * 100) : 0,
    badge: "Up Next",
    progressResetAt: Date.parse(progress?.reset_at ?? "") || 0,
    timeRemainingLabel: "Up next",
    activityAt: Date.parse(progress?.last_watched_at ?? watched.last_watched_at ?? watched.last_updated_at ?? "") || 0,
    releaseDate: progress?.last_watched_at ?? watched.last_watched_at ?? watched.last_updated_at ?? null
  };
}

export function traktHistoryToMedia(raw: unknown): MediaItem {
  const item = raw as {
    watched_at?: string;
    movie?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
    show?: { title?: string; year?: number; ids?: { tmdb?: number; trakt?: number; imdb?: string } };
    episode?: { season?: number; number?: number; title?: string };
  };
  const media = item.movie ?? item.show;
  const isShow = Boolean(item.show);
  return {
    id: trackerIdentity(media),
    title: isShow && item.episode?.title ? `${media?.title ?? "Series"}: ${item.episode.title}` : media?.title ?? "Untitled",
    year: media?.year ? String(media.year) : "",
    subtitle: isShow ? `Watched S${item.episode?.season ?? 1} E${item.episode?.number ?? 1}` : "Watched movie",
    mediaType: isShow ? "tv" : "movie",
    traktId: media?.ids?.trakt ?? null,
    imdbId: media?.ids?.imdb ?? null,
    seasonNumber: item.episode?.season ?? null,
    episodeNumber: item.episode?.number ?? null,
    episodeTitle: item.episode?.title ?? null,
    badge: item.watched_at ? "Trakt" : undefined
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
  // Hydrate the whole watchlist so nothing is silently dropped. getDetails is
  // cached, so re-renders don't re-fetch.
  const hydrated = new Array<MediaItem>(items.length);
  let cursor = 0;
  await Promise.all(Array.from({ length: Math.min(6, items.length) }, async () => {
    while (cursor < items.length) {
      const index = cursor++;
      const item = items[index];
      const id = item.id > 0 ? item.id : await resolveTmdbId(item).catch(() => null);
      hydrated[index] = id ? await getDetails({ ...item, id }).catch(() => ({ ...item, id })) : item;
    }
  }));
  // getDetails merges TMDB data over the item but keeps activityAt (added date)
  // from the Trakt mapping via the spread; make sure it survives explicitly.
  return hydrated.map((item, index) => ({ ...item, activityAt: items[index]?.activityAt ?? item.activityAt, badge: index < 10 ? `#${index + 1}` : item.badge }));
}

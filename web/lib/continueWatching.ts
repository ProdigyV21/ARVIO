import type { MediaItem } from "./types";

/** Up Next is authoritative even after a Trakt progress reset/rewatch. */
export function isUnwatchedContinueWatching(item: MediaItem, watchedKeys: Set<string>): boolean {
  if (item.mediaType === "tv" && item.badge === "Up Next") return true;
  const key = item.mediaType === "tv"
    ? `tv:${item.id}:${item.seasonNumber}:${item.episodeNumber}`
    : `movie:${item.id}`;
  return !watchedKeys.has(key);
}

/** A stale pause on a watched episode must not suppress the show's next episode. */
export function mergeTrackerContinueWatching(playback: MediaItem[], upNext: MediaItem[], watchedKeys: Set<string>): MediaItem[] {
  const unwatched = playback.filter((item) => !watchedKeys.has(item.mediaType === "tv"
    ? `tv:${item.id}:${item.seasonNumber}:${item.episodeNumber}`
    : `movie:${item.id}`));
  const pausedShows = new Set(unwatched.filter((item) => item.mediaType === "tv").map((item) => item.id));
  return [...unwatched, ...upNext.filter((item) => !pausedShows.has(item.id))];
}

/** Tracker list membership must not discard saved IPTV VOD sessions. */
export function includeIptvContinueWatching(primary: MediaItem[], local: MediaItem[]): MediaItem[] {
  const key = (item: MediaItem) => `${item.mediaType}:${item.id}`;
  const primaryKeys = new Set(primary.map(key));
  const newest = new Map<string, MediaItem>();
  for (const item of local) {
    const previous = newest.get(key(item));
    if (!previous || (item.activityAt ?? 0) > (previous.activityAt ?? 0)) newest.set(key(item), item);
  }
  const additions = [...newest.values()].filter((item) => {
    const progress = item.progress ?? 0;
    const position = item.resumePositionSeconds ?? 0;
    const duration = item.durationSeconds ?? 0;
    return !primaryKeys.has(key(item)) && item.id > 0 &&
      item.streamAddonId?.trim().toLowerCase() === "iptv_xtream_vod" &&
      !/^(live:|\[live\])/i.test(item.title) && !item.isWatched &&
      progress < 90 && (duration <= 0 || position / duration < 0.9) &&
      (progress >= 3 || position >= 60);
  });
  return [...primary, ...additions].sort((a, b) => (b.activityAt ?? 0) - (a.activityAt ?? 0));
}

import type { EpisodeInfo, MediaItem, WatchHistoryEntry } from "./types";

// Where the details "Play" button should start, from any entry point.
//
// A title opened from search, a rail or a shared link carries no progress of
// its own: only the Continue Watching row (or the cloud watch history) knows
// the viewer stopped at 1:12:04 or is halfway through S2 E3. Details used to
// trust the item it was opened with, so the same film showed "Resume" from the
// Continue Watching rail and "Play" (from 0:00) from search.
//
// Thresholds match the Android app (Constants.MIN_PROGRESS_THRESHOLD = 3,
// WATCHED_THRESHOLD = 90; EpisodePlaybackProgress.kt): under 3 % and under a
// minute is "not started", 90 % or more is "finished".

export const MIN_RESUME_PERCENT = 3;
export const WATCHED_PERCENT = 90;
const MIN_RESUME_SECONDS = 60;

/** 90 % and above counts as watched (player, external-player prompt, Android). */
export function isFinishedPercent(progressPercent: number) {
  return progressPercent >= WATCHED_PERCENT;
}

export type EpisodeKey = `${number}:${number}`;
export const episodeKey = (season: number, episode: number): EpisodeKey => `${season}:${episode}`;

export type ResumePoint = {
  season: number | null;
  episode: number | null;
  /** 0–100. */
  percent: number;
  positionSeconds: number;
  durationSeconds: number;
  /** Continue Watching "Up next" entry: the next episode to start, not a resume. */
  upNext: boolean;
  activityAt: number;
};

/** Stored seconds are occasionally milliseconds (older Android builds). */
function seconds(value: number | null | undefined) {
  const n = Number(value ?? 0);
  if (!Number.isFinite(n) || n <= 0) return 0;
  return n > 86_400 ? Math.round(n / 1000) : Math.round(n);
}

function percentOf(position: number, duration: number, stored: number) {
  if (duration > 0 && position > 0) return Math.max(0, Math.min(100, Math.floor((position / duration) * 100)));
  if (stored > 0) return Math.max(0, Math.min(100, Math.floor(stored)));
  return position > 0 ? 1 : 0;
}

/** A resume point worth offering: started (≥3 % or ≥1 min) and not finished. */
export function isResumable(point: Pick<ResumePoint, "percent" | "positionSeconds" | "upNext">) {
  if (point.upNext) return false;
  if (point.percent >= WATCHED_PERCENT) return false;
  return point.percent >= MIN_RESUME_PERCENT || point.positionSeconds >= MIN_RESUME_SECONDS;
}

export function resumePointFromItem(item: MediaItem): ResumePoint {
  const position = seconds(item.resumePositionSeconds);
  const duration = seconds(item.durationSeconds);
  const upNext = item.badge === "Up Next" || item.timeRemainingLabel === "Up next";
  return {
    season: item.mediaType === "tv" ? item.seasonNumber ?? null : null,
    episode: item.mediaType === "tv" ? item.episodeNumber ?? null : null,
    percent: upNext ? 0 : percentOf(position, duration, item.progress ?? 0),
    positionSeconds: upNext ? 0 : position,
    durationSeconds: duration,
    upNext,
    activityAt: item.activityAt ?? 0
  };
}

export function resumePointFromHistory(entry: WatchHistoryEntry): ResumePoint {
  const position = seconds(entry.position_seconds);
  const duration = seconds(entry.duration_seconds);
  return {
    season: entry.media_type === "tv" ? entry.season ?? null : null,
    episode: entry.media_type === "tv" ? entry.episode ?? null : null,
    percent: percentOf(position, duration, (entry.progress ?? 0) * 100),
    positionSeconds: position,
    durationSeconds: duration,
    upNext: false,
    activityAt: Date.parse(entry.updated_at ?? entry.paused_at ?? "") || 0
  };
}

function sameTitle(a: { mediaType: string; id: number; tmdbId?: number | null }, mediaType: string, id: number) {
  return a.mediaType === mediaType && (a.id === id || a.tmdbId === id);
}

/**
 * Every known progress point for one title, newest first: the item itself,
 * the Continue Watching rail and the cloud watch history rows.
 */
export function collectResumePoints(
  item: Pick<MediaItem, "mediaType" | "id" | "tmdbId">,
  sources: { self?: MediaItem | null; continueWatching?: MediaItem[]; history?: WatchHistoryEntry[] }
): ResumePoint[] {
  const ids = new Set([item.id, item.tmdbId].filter((value): value is number => typeof value === "number" && value > 0));
  const points: ResumePoint[] = [];
  if (sources.self && (sources.self.progress || sources.self.resumePositionSeconds || sources.self.badge === "Up Next")) {
    points.push(resumePointFromItem(sources.self));
  }
  for (const entry of sources.continueWatching ?? []) {
    if ([...ids].some((id) => sameTitle(entry, item.mediaType, id))) points.push(resumePointFromItem(entry));
  }
  for (const row of sources.history ?? []) {
    if (row.media_type === item.mediaType && ids.has(row.show_tmdb_id)) points.push(resumePointFromHistory(row));
  }
  return points.sort((a, b) => b.activityAt - a.activityAt);
}

/** In-progress percent per episode (Android EpisodePlaybackProgress parity). */
export function episodeProgressMap(points: ResumePoint[], isWatched: (season: number, episode: number) => boolean) {
  const map = new Map<EpisodeKey, ResumePoint>();
  for (const point of points) {
    if (point.season == null || point.episode == null) continue;
    const key = episodeKey(point.season, point.episode);
    if (map.has(key) || !isResumable(point) || isWatched(point.season, point.episode)) continue;
    map.set(key, point);
  }
  return map;
}

type LabelValues = Record<string, string | number>;
/** `label` is a translateUi template; `labelValues` fills its {valueN} slots. */
export type PlayPlan =
  | { kind: "play"; label: string; labelValues?: LabelValues; season: null; episode: null; resumeSeconds: 0 }
  | { kind: "resume"; label: string; labelValues?: LabelValues; season: number | null; episode: number | null; resumeSeconds: number; startOverLabel: string }
  | { kind: "episode"; label: string; labelValues?: LabelValues; season: number; episode: number; resumeSeconds: 0 }
  | { kind: "choose"; label: string; labelValues?: LabelValues; season: null; episode: null; resumeSeconds: 0 };

export function formatClock(totalSeconds: number) {
  const total = Math.max(0, Math.floor(totalSeconds));
  const h = Math.floor(total / 3600);
  const m = Math.floor((total % 3600) / 60);
  const s = total % 60;
  return h > 0 ? `${h}:${String(m).padStart(2, "0")}:${String(s).padStart(2, "0")}` : `${m}:${String(s).padStart(2, "0")}`;
}

function resumeSecondsOf(point: ResumePoint) {
  if (point.positionSeconds > 0) return point.positionSeconds;
  return point.durationSeconds > 0 ? Math.round(point.durationSeconds * point.percent / 100) : 0;
}

/**
 * The first episode to offer when nothing is in progress: the earliest
 * regular-season episode that is not watched and has aired. Specials (season
 * 0) are never the default start.
 */
export function firstUnwatchedEpisode(
  episodes: Array<Pick<EpisodeInfo, "seasonNumber" | "episodeNumber" | "airDate">>,
  isWatched: (season: number, episode: number) => boolean,
  now = Date.now()
) {
  return [...episodes]
    .filter((episode) => episode.seasonNumber > 0)
    .sort((a, b) => a.seasonNumber - b.seasonNumber || a.episodeNumber - b.episodeNumber)
    .find((episode) => !isWatched(episode.seasonNumber, episode.episodeNumber)
      && !(episode.airDate && Date.parse(episode.airDate) > now)) ?? null;
}

/**
 * The details primary action.
 *
 * - Movie with progress: "Resume 1:12:04" (+ "Start over").
 * - Series with an in-progress episode: "Resume S2 E3" (+ "Start over").
 * - Series with an "Up next" entry or a chosen episode: "Play S2 E4".
 * - Series with nothing started: the first unwatched aired episode ("Play S1 E1").
 */
export function planPrimaryAction(input: {
  item: Pick<MediaItem, "mediaType" | "isWatched">;
  points: ResumePoint[];
  /** Episode the viewer picked in this details view (wins over history). */
  selectedEpisode?: { season: number; episode: number } | null;
  /** Earliest unwatched aired regular episode, when the episode list is known. */
  firstUnwatched?: { season: number; episode: number } | null;
  isWatched?: (season: number | null, episode: number | null) => boolean;
}): PlayPlan {
  const watched = input.isWatched ?? (() => false);
  if (input.item.mediaType !== "tv") {
    const point = input.points.find((candidate) => candidate.season == null);
    if (point && isResumable(point) && !input.item.isWatched && !watched(null, null)) {
      const at = resumeSecondsOf(point);
      if (at > 0) return { kind: "resume", label: "Resume {value0}", labelValues: { value0: formatClock(at) }, season: null, episode: null, resumeSeconds: at, startOverLabel: "Start over" };
    }
    return { kind: "play", label: "Play", season: null, episode: null, resumeSeconds: 0 };
  }
  const chosen = input.selectedEpisode ?? null;
  const progressFor = (season: number, episode: number) => input.points.find((point) => point.season === season && point.episode === episode && isResumable(point));
  if (chosen) {
    const point = progressFor(chosen.season, chosen.episode);
    if (point && !watched(chosen.season, chosen.episode)) {
      return { kind: "resume", label: "Resume S{value0} E{value1}", labelValues: { value0: chosen.season, value1: chosen.episode }, season: chosen.season, episode: chosen.episode, resumeSeconds: resumeSecondsOf(point), startOverLabel: "Start over" };
    }
    return { kind: "episode", label: "Play S{value0} E{value1}", labelValues: { value0: chosen.season, value1: chosen.episode }, season: chosen.season, episode: chosen.episode, resumeSeconds: 0 };
  }
  const latest = input.points.find((point) => point.season != null && point.episode != null
    && (point.upNext || isResumable(point)) && !watched(point.season, point.episode));
  if (latest && latest.season != null && latest.episode != null) {
    if (!latest.upNext) {
      return { kind: "resume", label: "Resume S{value0} E{value1}", labelValues: { value0: latest.season, value1: latest.episode }, season: latest.season, episode: latest.episode, resumeSeconds: resumeSecondsOf(latest), startOverLabel: "Start over" };
    }
    return { kind: "episode", label: "Play S{value0} E{value1}", labelValues: { value0: latest.season, value1: latest.episode }, season: latest.season, episode: latest.episode, resumeSeconds: 0 };
  }
  if (input.firstUnwatched) {
    return { kind: "episode", label: "Play S{value0} E{value1}", labelValues: { value0: input.firstUnwatched.season, value1: input.firstUnwatched.episode }, season: input.firstUnwatched.season, episode: input.firstUnwatched.episode, resumeSeconds: 0 };
  }
  return { kind: "choose", label: "Choose episode", season: null, episode: null, resumeSeconds: 0 };
}

/**
 * The Continue Watching entry for a title opened from somewhere else (search,
 * a rail, a link), as fields to merge into the details item — or null when the
 * item already carries its own episode/progress or nothing is in progress.
 * An "Up next" entry contributes only its episode (it starts from 0:00).
 */
export function resumeFromContinueWatching(item: MediaItem, rail: MediaItem[]): Partial<MediaItem> | null {
  if (item.progress || item.resumePositionSeconds) return null;
  if (item.mediaType === "tv" && item.seasonNumber != null && item.episodeNumber != null) return null;
  const ids = [item.id, item.tmdbId].filter((value): value is number => typeof value === "number" && value > 0);
  const match = rail.find((entry) => ids.some((id) => sameTitle(entry, item.mediaType, id)));
  if (!match) return null;
  const point = resumePointFromItem(match);
  const progressFields = { progress: match.progress, resumePositionSeconds: match.resumePositionSeconds, durationSeconds: match.durationSeconds };
  if (item.mediaType !== "tv") return isResumable(point) ? progressFields : null;
  if (point.season == null || point.episode == null || (!point.upNext && !isResumable(point))) return null;
  return {
    seasonNumber: point.season,
    episodeNumber: point.episode,
    episodeTitle: match.episodeTitle ?? null,
    ...(point.upNext ? {} : progressFields)
  };
}

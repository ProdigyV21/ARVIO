// App store (lib/store.tsx): Continue Watching building blocks: watched keys, Trakt Up Next, hydration and episode checks.
import { isLiveStreamOrSportsItem } from "../cloud";
import { isUnwatchedContinueWatching, traktProgressActivityKey } from "../continueWatching";
import { createEpisodeValidator } from "../episodeAvailability";
import { HttpError } from "../http";
import { traktUpNextToMedia } from "../mappers";
import { getDetails, getSeasonEpisodes, tmdb } from "../tmdb";
import type { InstalledAddon, MediaItem } from "../types";
import { traktClient } from "./clients";

export const validateContinueWatchingEpisodes = createEpisodeValidator(async (item) => {
  try {
    if (item.traktId) {
      const episode = await traktClient.episodeSummary(item.traktId, item.seasonNumber!, item.episodeNumber!);
      return { exists: episode.season === item.seasonNumber && episode.number === item.episodeNumber, airDate: episode.first_aired };
    }
    const id = item.tmdbId ?? item.id;
    if (id <= 0) throw new Error("Episode has no supported metadata identity");
    const episode = await tmdb<{ season_number: number; episode_number: number; air_date?: string | null }>(
      `tv/${id}/season/${item.seasonNumber}/episode/${item.episodeNumber}`
    );
    return { exists: episode.season_number === item.seasonNumber && episode.episode_number === item.episodeNumber, airDate: episode.air_date };
  } catch (error) {
    if (error instanceof HttpError && error.status === 404) return { exists: false };
    throw error;
  }
});

export function mediaWatchKey(item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) {
  if (item.mediaType === "movie") return `movie:${item.id}`;
  const season = seasonNumber ?? item.seasonNumber ?? null;
  const episode = episodeNumber ?? item.episodeNumber ?? null;
  if (season !== null && episode !== null && season !== undefined && episode !== undefined) return `tv:${item.id}:${season}:${episode}`;
  return `tv:${item.id}`;
}

export function traktWatchedKeys(movies: unknown[], shows: unknown[]) {
  const keys = new Set<string>();
  movies.forEach((raw) => {
    const item = raw as { movie?: { ids?: { tmdb?: number } }; status?: string; last_watched_at?: string };
    const tmdb = item.movie?.ids?.tmdb;
    if (tmdb) {
      if (item.status === undefined || item.status === "completed") {
        keys.add(`movie:${tmdb}`);
      }
    }
  });
  shows.forEach((raw) => {
    const item = raw as {
      show?: { ids?: { tmdb?: number }; aired_episodes?: number };
      status?: string;
      seasons?: Array<{ number?: number; episodes?: Array<{ number?: number }> }>;
    };
    const tmdb = item.show?.ids?.tmdb;
    if (!tmdb) return;
    let watchedEpisodes = 0;
    item.seasons?.forEach((season) => {
      const seasonNumber = season.number;
      // Specials (season 0) don't count toward aired_episodes.
      if (seasonNumber === undefined || seasonNumber === null) return;
      season.episodes?.forEach((episode) => {
        if (episode.number === undefined || episode.number === null) return;
        keys.add(`tv:${tmdb}:${seasonNumber}:${episode.number}`);
        if (seasonNumber > 0) watchedEpisodes += 1;
      });
    });
    // If entire show marked completed, or all aired episodes watched, badge whole show
    const aired = item.show?.aired_episodes;
    if (item.status === "completed" || (typeof aired === "number" && aired > 0 && watchedEpisodes >= aired)) {
      keys.add(`tv:${tmdb}`);
    }
  });
  return keys;
}

export function filterWatchedContinueWatching(items: MediaItem[], watchedKeys: Set<string>, addons: InstalledAddon[], completions?: Map<string, number>) {
  const nonLive = items.filter((item) => !isLiveStreamOrSportsItem(item, addons));
  if (!watchedKeys.size) return nonLive;
  return nonLive.filter((item) => isUnwatchedContinueWatching(item, watchedKeys, completions));
}


export function isMediaWatched(item: MediaItem, watchedKeys: Set<string>, seasonNumber?: number | null, episodeNumber?: number | null) {
  if (item.isWatched) return true;
  const key = mediaWatchKey(item, seasonNumber, episodeNumber);
  // An episode counts as watched only via its own tv:id:season:episode key —
  // never via the show-level key, which now means "entire show completed".
  return Boolean(key && watchedKeys.has(key));
}

export function isPausedPlaybackItem(item: MediaItem) {
  if (item.badge === "Up Next") return true;
  // Match the Android app: in-progress items are 3%–90% watched.
  const progress = item.progress ?? 0;
  return progress >= 3 && progress < 90;
}

export function traktActivityTime(raw: unknown) {
  const item = raw as { last_watched_at?: string; last_updated_at?: string; show?: { title?: string } };
  return Date.parse(item.last_watched_at ?? item.last_updated_at ?? "") || 0;
}

// How many watched shows we ask Trakt for per-show progress. The activity-keyed
// progress cache means only shows whose last_watched_at MOVED cost a call on a
// repeat refresh, so this ceiling mostly bounds the very first sync of a large
// library. 300 keeps the web and Android resolvers aligned for heavy Trakt
// users without making a single refresh unbounded.
export const UP_NEXT_SHOW_LIMIT = 300;
// How many Continue Watching rows get the (expensive) per-item TMDB hydration.
// Rows past this still render — they just use the data Trakt/cloud already gave
// us — instead of being dropped from the rail entirely.
export const CW_HYDRATE_LIMIT = 50;

export async function loadTraktUpNext(watchedShowsRows: unknown[], includeSpecials: boolean, hiddenShowIds: Set<number>, isCurrent = () => true) {
  // Fetch per-show progress for the whole watched-shows list (newest-activity
  // first) so Continue Watching surfaces every show with an unwatched next
  // episode — not just the most recent handful. The activity-keyed cache means
  // repeat refreshes only hit shows whose last_watched_at actually changed, and
  // concurrency is throttled so this never becomes the old 48-parallel burst.
  //
  // Shows the user hid from progress on Trakt are dropped BEFORE the slice, so
  // they neither appear in the rail nor consume one of the fetch slots.
  const watchedShows = watchedShowsRows
    .filter((raw) => {
      const traktId = (raw as { show?: { ids?: { trakt?: number } } }).show?.ids?.trakt;
      return !(typeof traktId === "number" && hiddenShowIds.has(traktId));
    })
    .sort((a, b) => traktActivityTime(b) - traktActivityTime(a))
    .slice(0, UP_NEXT_SHOW_LIMIT);
  const results: Array<MediaItem | null> = new Array(watchedShows.length).fill(null);
  let cursor = 0;
  // Distinguishes "progress fetch failed" (rate-limited/blocked) from "show has
  // no next episode" — an empty result with failures present is a partial
  // outage, not an empty Continue Watching.
  let fetchFailures = 0;
  // Keep the first sync below Trakt's burst threshold. A larger pool made a
  // rate-limited response look like a one-item Continue Watching list.
  const workers = Array.from({ length: Math.min(4, watchedShows.length) }, async () => {
    while (cursor < watchedShows.length && isCurrent()) {
      const index = cursor;
      cursor += 1;
      const watched = watchedShows[index];
      const row = watched as { show?: { ids?: { trakt?: number } } };
      const traktId = row.show?.ids?.trakt;
      if (!traktId) continue;
      // The client owns the account-scoped, expiring cache. A second, timeless
      // cache here hid newly aired episodes and could leak progress by profile.
      const progress = await traktClient.showProgress(traktId, includeSpecials, traktProgressActivityKey(watched)).catch(() => null);
      if (!progress) fetchFailures += 1;
      results[index] = traktUpNextToMedia(watched, progress);
    }
  });
  await Promise.all(workers);
  const items = results
    .filter((item): item is MediaItem => Boolean(item))
    .filter((item) => includeSpecials || item.seasonNumber !== 0);
  return { items, fetchFailures };
}

export function mergeTraktWithLocalResume(traktItems: MediaItem[], localItems: MediaItem[]) {
  if (!localItems.length) return traktItems;
  const localByEpisode = new Map(localItems.map((item) => [
    `${item.mediaType}:${item.id}:${item.seasonNumber ?? ""}:${item.episodeNumber ?? ""}`,
    item
  ]));
  return traktItems.map((item) => {
    const local = localByEpisode.get(`${item.mediaType}:${item.id}:${item.seasonNumber ?? ""}:${item.episodeNumber ?? ""}`);
    if (!local) return item;
    return {
      ...item,
      image: item.image || local.image,
      backdrop: item.backdrop || local.backdrop,
      episodeStill: item.episodeStill || local.episodeStill,
      episodeTitle: item.episodeTitle ?? local.episodeTitle ?? null,
      progress: Math.max(item.progress ?? 0, local.progress ?? 0),
      resumePositionSeconds: Math.max(item.resumePositionSeconds ?? 0, local.resumePositionSeconds ?? 0),
      durationSeconds: Math.max(item.durationSeconds ?? 0, local.durationSeconds ?? 0),
      streamAddonId: item.streamAddonId ?? local.streamAddonId,
      timeRemainingLabel: local.timeRemainingLabel ?? item.timeRemainingLabel ?? null
    };
  });
}

export async function hydrateContinueWatchingItems(items: MediaItem[]) {
  // Throttled pool (not one big Promise.all): 50 parallel TMDB calls at startup
  // starved user-initiated fetches (opening a details page mid-boot timed out
  // and rendered without seasons/cast). Only the head of the rail pays for
  // hydration; the tail is passed through unhydrated rather than DROPPED, which
  // used to silently cut Continue Watching off at 50 rows.
  const source = items.slice(0, CW_HYDRATE_LIMIT);
  const tail = items.slice(CW_HYDRATE_LIMIT);
  const hydrated: Array<MediaItem | null> = new Array(source.length).fill(null);
  let cursor = 0;
  const workers = Array.from({ length: Math.min(10, source.length) }, async () => {
    while (cursor < source.length) {
      const index = cursor;
      cursor += 1;
      const item = source[index];
      const episodeStillPromise = item.mediaType === "tv" && item.seasonNumber != null && item.episodeNumber != null
        ? getSeasonEpisodes(item.id, item.seasonNumber)
            .then((episodes) => episodes.find((episode) => episode.episodeNumber === item.episodeNumber)?.still ?? null)
            .catch(() => null)
        : Promise.resolve(null);
      const [details, episodeStill] = await Promise.all([
        getDetails(item).catch(() => item),
        episodeStillPromise
      ]);
      const enriched = {
        ...details,
        ...item,
        image: item.image || details.image,
        backdrop: item.backdrop || details.backdrop,
        episodeStill: null,
        overview: details.overview || item.overview,
        rating: details.rating || item.rating,
        duration: details.duration || item.duration
      };
      hydrated[index] = {
        ...enriched,
        episodeStill: episodeStill || item.episodeStill || null
      };
    }
  });
  await Promise.all(workers);
  // Metadata providers can disagree about season numbering. Artwork must never
  // change Trakt's selected episode or remove it based on TMDB episode counts.
  return [...hydrated, ...tail].filter((item): item is MediaItem => Boolean(item));
}


// Only locally advancing playback uses TMDB's season boundaries, not imported
// tracker progress (which may use a different episode ordering).
export function nextLocalEpisode(item: MediaItem): MediaItem | null {
  const { seasonNumber, episodeNumber } = item;
  if (seasonNumber == null || episodeNumber == null) return item;
  const seasons = (item.seasons ?? []).filter((season) => season.seasonNumber >= 0);
  const current = seasons.find((season) => season.seasonNumber === seasonNumber);
  if (!current?.episodeCount || episodeNumber <= current.episodeCount) return item;
  const next = seasons.filter((season) => season.seasonNumber > seasonNumber && (season.episodeCount ?? 0) > 0)
    .sort((a, b) => a.seasonNumber - b.seasonNumber)[0];
  return next ? { ...item, seasonNumber: next.seasonNumber, episodeNumber: 1, episodeTitle: null, episodeStill: null, subtitle: `S${next.seasonNumber} E1` } : null;
}

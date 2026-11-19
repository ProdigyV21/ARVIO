import { getDetails, getReviews, getSeasonEpisodes } from "@/lib/tmdb";
import type { ProviderPriorityConfig } from "@/lib/metadata/types";
import type { EpisodeInfo, MediaItem, ReviewInfo } from "@/lib/types";

// One in-flight request per key. Opening a series used to fire tv/{id} three
// times in the same millisecond (store.openDetails, the details view mount and
// its re-run when the hydrated item replaced the shallow one) and every season
// and per-episode rating request twice; the cache in tmdb.ts only helps once a
// response has landed. Sharing the pending promise collapses those into one.

export function createInFlight<K, V>() {
  const pending = new Map<K, Promise<V>>();
  return (key: K, run: () => Promise<V>): Promise<V> => {
    const existing = pending.get(key);
    if (existing) return existing;
    const task = run().finally(() => { if (pending.get(key) === task) pending.delete(key); });
    pending.set(key, task);
    return task;
  };
}

const detailsInFlight = createInFlight<string, MediaItem>();
const seasonInFlight = createInFlight<string, EpisodeInfo[]>();

function configKey(config?: ProviderPriorityConfig) {
  return config ? JSON.stringify(config) : "";
}

export function loadDetails(item: MediaItem, config?: ProviderPriorityConfig) {
  return detailsInFlight(`${item.mediaType}:${item.id}:${configKey(config)}`, () => getDetails(item, config));
}

export function loadSeasonEpisodes(
  tvId: number,
  season: number,
  language = "en-US",
  config?: ProviderPriorityConfig,
  context?: Parameters<typeof getSeasonEpisodes>[4]
) {
  // Same key as tmdb.ts's own season cache: the metadata context (which only
  // enriches names/stills) changes when the shallow item is swapped for the
  // hydrated one, and must not start a second identical season fetch.
  const key = `${tvId}:${season}:${language}`;
  return seasonInFlight(key, () => getSeasonEpisodes(tvId, season, language, config, context));
}

// Reviews are below the fold and never change while details are open: one
// request per title for the session (bounded).
const reviewsInFlight = createInFlight<string, ReviewInfo[]>();
const reviewsCache = new Map<string, ReviewInfo[]>();
export function loadReviews(item: Pick<MediaItem, "mediaType" | "id">) {
  const key = `${item.mediaType}:${item.id}`;
  const hit = reviewsCache.get(key);
  if (hit) return Promise.resolve(hit);
  return reviewsInFlight(key, () => getReviews(item).then((reviews) => {
    reviewsCache.set(key, reviews);
    if (reviewsCache.size > 50) reviewsCache.delete(reviewsCache.keys().next().value!);
    return reviews;
  }));
}

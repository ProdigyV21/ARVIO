// App store (lib/store.tsx): Instant-paint caches for Continue Watching and the watchlist.
import { loadStored, saveStored } from "../storage";
import type { MediaItem } from "../types";

// Instant-paint caches for Continue Watching / Watchlist. The TTL is deliberately
// long: a stale list is strictly better than a blank rail (the fresh fetch
// replaces it seconds later). The old 24h TTL left the rail blank on the first
// open of the day whenever the previous enriched refresh was more than a day ago.
export const LIST_CACHE_TTL_MS = 7 * 24 * 60 * 60 * 1000;

export function cwCacheKeyFor(profileId: string | null | undefined) {
  // Old rails may contain nonexistent/unaired episodes that were never validated.
  return `arvio.web.cw.v4:${profileId ?? "no-profile"}`;
}

export function watchlistCacheKeyFor(profileId: string | null | undefined) {
  return `arvio.web.watchlist.v1:${profileId ?? "no-profile"}`;
}

export function readCachedList(key: string): MediaItem[] {
  const cached = loadStored<{ at: number; items: MediaItem[] } | null>(key, null);
  return cached?.items?.length && Date.now() - cached.at < LIST_CACHE_TTL_MS ? cached.items : [];
}

// Cache-only representation of a media item: cast/related are the bulk of a
// hydrated item (tens of KB each) and the rails never render them — details
// refetches both anyway. Full items once pushed localStorage past its ~5MB
// quota, after which EVERY cache write failed silently.
export function slimCacheItem(item: MediaItem): MediaItem {
  const { cast, related, ...slim } = item;
  return slim;
}

export function saveCachedList(key: string, items: MediaItem[], max: number) {
  try {
    saveStored(key, { at: Date.now(), items: items.slice(0, max).map(slimCacheItem) });
  } catch {
    // Never let a cache write break the refresh.
  }
}

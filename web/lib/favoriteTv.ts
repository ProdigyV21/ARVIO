import { channelIdentityIndex, resolveChannelReferences } from "./iptvSession";
import { loadStored, saveStored } from "./storage";
import type { IptvChannel } from "./types";

/**
 * The Home "Favorite TV" row, as on Android (HomeViewModel.buildFavoriteTvOutcome): the
 * profile's favorite IPTV channels in the user's own order. Playlists only load in Live TV,
 * which is far too heavy for Home, so each loaded playlist leaves a copy of just the
 * favorite channels and Home paints the row from that copy.
 */

export const FAVORITE_TV_CATALOG_ID = "favorite_tv";

const CACHE_KEY = "arvio.web.favoriteTv.v1";
const MAX_SCOPES = 8;

type Entry = { signature: string; at: number; favoriteIds: string[]; channels: IptvChannel[] };

export function resolveFavoriteChannels(favoriteIds: string[], channels: IptvChannel[]) {
  return resolveChannelReferences(favoriteIds, channelIdentityIndex(channels));
}

/** The saved copy for this account/profile and playlist set, or null when there is none. */
export function cachedFavoriteChannels(scope: string, signature: string): Omit<Entry, "signature" | "at"> | null {
  const entry = loadStored<Record<string, Entry>>(CACHE_KEY, {})[scope];
  if (!entry || entry.signature !== signature || !Array.isArray(entry.channels)) return null;
  return { favoriteIds: Array.isArray(entry.favoriteIds) ? entry.favoriteIds : [], channels: entry.channels };
}

export function saveFavoriteChannels(scope: string, signature: string, favoriteIds: string[], channels: IptvChannel[]) {
  const cache = loadStored<Record<string, Entry>>(CACHE_KEY, {});
  const previous = cache[scope];
  if (previous?.signature === signature && JSON.stringify(previous.favoriteIds) === JSON.stringify(favoriteIds)
    && JSON.stringify(previous.channels) === JSON.stringify(channels)) return;
  const next = { ...cache, [scope]: { signature, at: Date.now(), favoriteIds, channels } };
  saveStored(CACHE_KEY, Object.fromEntries(Object.entries(next).sort((a, b) => b[1].at - a[1].at).slice(0, MAX_SCOPES)));
}

/**
 * True when the saved copy cannot answer for every favorite: none was saved yet, or a
 * favorite was added (on this or another device) after it was built. A favorite the
 * playlist no longer carries is not a reason to reload it again.
 */
export function favoriteCopyIsStale(copy: Omit<Entry, "signature" | "at"> | null, favoriteIds: string[]) {
  if (!copy) return favoriteIds.length > 0;
  const known = new Set(copy.favoriteIds);
  return favoriteIds.some((id) => !known.has(id));
}

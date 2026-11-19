// Edits to the synced Live TV favourite / hidden lists (favoriteChannelIds,
// favoriteGroupIds, hiddenGroupIds). Shared by Live TV and Settings so both
// write the same shapes Android reads.
import type { IptvChannel } from "./types";

type ChannelIndex = Map<string, IptvChannel>;

/** Add (at the front, by its cloud id when known) or remove a favourite channel. */
export function toggleFavoriteChannel(favorites: string[], channelId: string, index: ChannelIndex): string[] {
  const isFavorite = favorites.some((reference) => (index.get(reference)?.id ?? reference) === channelId);
  return isFavorite
    ? favorites.filter((reference) => (index.get(reference)?.id ?? reference) !== channelId)
    : [index.get(channelId)?.cloudId ?? channelId, ...favorites];
}

/** Swap a favourite with its neighbour; unchanged at either end or when not a favourite. */
export function moveFavoriteChannel(favorites: string[], channelId: string, direction: -1 | 1, index: ChannelIndex): string[] {
  const from = favorites.findIndex((reference) => (index.get(reference)?.id ?? reference) === channelId);
  const to = from + direction;
  if (from < 0 || to < 0 || to >= favorites.length) return favorites;
  const ordered = [...favorites];
  [ordered[from], ordered[to]] = [ordered[to], ordered[from]];
  return ordered;
}

/** Position of a channel in the favourites list, or -1. */
export function favoritePosition(favorites: string[], channelId: string, index: ChannelIndex): number {
  return favorites.findIndex((reference) => (index.get(reference)?.id ?? reference) === channelId);
}

/** Toggle a group key ("playlistId|Group") in favoriteGroupIds / hiddenGroupIds. */
export function toggleGroupKey(list: string[], key: string): string[] {
  return list.includes(key) ? list.filter((item) => item !== key) : [key, ...list];
}

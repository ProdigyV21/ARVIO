import type { IptvChannel } from "./types";
import { moveIndex } from "./ordering";
import { channelPlaylistId } from "./stalkerPortals";

/**
 * `name` is the playlist's own category name (what keys are built from);
 * `label` is what the user sees: their own name for it, or `name`.
 */
export interface PlaylistGroup { key: string; name: string; label: string; count: number }

/**
 * Key part for a channel without a category name. Android's playlistGroupLabel
 * uses "Ungrouped"; parsers on both sides already turn a missing group-title
 * into "Uncategorized", so this only applies to genuinely blank names.
 */
export const UNGROUPED = "Ungrouped";

export function groupKeyName(group: string | undefined) {
  return group?.trim() || UNGROUPED;
}

/** Same `playlistId|group` key the Android app and the web Live TV page use. */
export function playlistGroupKey(playlistId: string, group: string | undefined) {
  return `${playlistId}|${groupKeyName(group)}`;
}

export function groupNameOf(key: string) {
  const split = key.indexOf("|");
  return split < 0 ? key : key.slice(split + 1);
}

export function playlistGroups(channels: IptvChannel[], playlistId: string, order: string[], names?: Record<string, string>): PlaylistGroup[] {
  const groups = new Map<string, PlaylistGroup>();
  for (const channel of channels) {
    if (channelPlaylistId(channel.id) !== playlistId) continue;
    const name = groupKeyName(channel.group);
    const key = `${playlistId}|${name}`;
    const group = groups.get(key);
    if (group) group.count++;
    else groups.set(key, { key, name, label: names?.[key] || name, count: 1 });
  }
  const ranks = new Map(order.map((key, index) => [key, index]));
  return [...groups.values()].sort((a, b) =>
    (ranks.get(a.key) ?? Number.MAX_SAFE_INTEGER) - (ranks.get(b.key) ?? Number.MAX_SAFE_INTEGER));
}

/**
 * Write a playlist's categories in `keys` order into the shared groupOrder.
 * Only this playlist's known slots change; other playlists and categories that
 * are not loaded right now keep their entries.
 */
export function replacePlaylistGroupOrder(order: string[], keys: string[]): string[] {
  const known = new Set(keys);
  let index = 0;
  const next = [...new Set(order)].map(item => known.has(item) ? keys[index++] : item);
  return [...next, ...keys.slice(index)];
}

export function movePlaylistGroup(order: string[], groups: PlaylistGroup[], key: string, direction: -1 | 1): string[] {
  const from = groups.findIndex(group => group.key === key);
  const to = from + direction;
  if (from < 0 || to < 0 || to >= groups.length) return order;
  return movePlaylistGroupTo(order, groups, from, to);
}

export function movePlaylistGroupTo(order: string[], groups: PlaylistGroup[], from: number, to: number): string[] {
  const keys = groups.map(group => group.key);
  const moved = moveIndex(keys, from, to);
  return moved === keys ? order : replacePlaylistGroupOrder(order, moved);
}

export function setPlaylistGroupsVisible(hidden: string[], groups: PlaylistGroup[], visible: boolean): string[] {
  const keys = new Set(groups.map(group => group.key));
  return visible ? hidden.filter(key => !keys.has(key)) : [...new Set([...hidden, ...keys])];
}

/* ---------- favourites ---------- */

/** Android stores favourite categories by name, older web builds by `playlist|name`; accept both. */
export function isFavoriteGroup(favoriteGroupIds: string[] | undefined, key: string) {
  if (!favoriteGroupIds?.length) return false;
  return favoriteGroupIds.includes(key) || favoriteGroupIds.includes(groupNameOf(key));
}

/** Add or remove a favourite category. New favourites use the name, which is what the Android app reads. */
export function toggleFavoriteGroup(favoriteGroupIds: string[], key: string): string[] {
  const name = groupNameOf(key);
  if (isFavoriteGroup(favoriteGroupIds, key)) return favoriteGroupIds.filter(item => item !== key && item !== name);
  return [name, ...favoriteGroupIds];
}

/* ---------- channel order inside a category ---------- */

/** Most channels the manual order of one category may list (keeps the synced payload small). */
export const MAX_CHANNEL_ORDER_ENTRIES = 3000;

/** The id a channel is saved under in the cloud (matches the Android channel id). */
export function channelRef(channel: Pick<IptvChannel, "id" | "cloudId">) {
  return channel.cloudId || channel.id;
}

function identities(channel: IptvChannel) {
  return [channel.id, channel.cloudId, ...(channel.syncAliases ?? [])].filter((value): value is string => Boolean(value));
}

/**
 * Apply a category's manual channel order: listed channels first, in that
 * order, then every other channel in the order it already had (the playlist
 * sort order). Used by Settings and by the Live TV page so both agree.
 */
export function applyChannelOrder(channels: IptvChannel[], order: string[] | undefined): IptvChannel[] {
  if (!order?.length || channels.length < 2) return channels;
  const rank = new Map<string, number>();
  order.forEach((ref, index) => { if (!rank.has(ref)) rank.set(ref, index); });
  const rankOf = (channel: IptvChannel) => {
    let best = Number.MAX_SAFE_INTEGER;
    for (const id of identities(channel)) {
      const value = rank.get(id);
      if (value !== undefined && value < best) best = value;
    }
    return best;
  };
  return channels.map((channel, index) => ({ channel, index, rank: rankOf(channel) }))
    .sort((a, b) => a.rank - b.rank || a.index - b.index)
    .map(entry => entry.channel);
}

/** Channels of one category, already in the playlist sort order the Live TV page uses. */
export function sortChannelsLikeLiveTv(channels: IptvChannel[], sortOrder: "provider" | "number" | "name" | undefined): IptvChannel[] {
  if (sortOrder === "number") {
    return [...channels].sort((a, b) => {
      const numA = a.number ? parseInt(a.number, 10) : Number.MAX_SAFE_INTEGER;
      const numB = b.number ? parseInt(b.number, 10) : Number.MAX_SAFE_INTEGER;
      if (numA !== numB) return (Number.isNaN(numA) ? Number.MAX_SAFE_INTEGER : numA) - (Number.isNaN(numB) ? Number.MAX_SAFE_INTEGER : numB);
      return a.name.localeCompare(b.name);
    });
  }
  if (sortOrder === "name") return [...channels].sort((a, b) => a.name.localeCompare(b.name));
  return channels;
}

/**
 * The shortest manual order that produces `ordered` when applied over
 * `base` (the category in its normal sort order). Channels at the end that are
 * still in their normal relative order do not need to be stored.
 */
export function minimalChannelOrder(ordered: IptvChannel[], base: IptvChannel[]): string[] {
  const baseRank = new Map(base.map((channel, index) => [channel.id, index]));
  let start = ordered.length;
  let next = Number.MAX_SAFE_INTEGER;
  while (start > 0) {
    const rank = baseRank.get(ordered[start - 1].id) ?? -1;
    if (rank >= next) break;
    next = rank;
    start--;
  }
  return ordered.slice(0, start).map(channelRef);
}

/** Replace one category's manual order in the synced map; an empty order removes the entry. */
export function setGroupChannelOrder(map: Record<string, string[]> | undefined, key: string, refs: string[]): Record<string, string[]> {
  const next = { ...(map ?? {}) };
  if (refs.length) next[key] = refs;
  else delete next[key];
  return next;
}

/** Drop the manual channel orders of one playlist (its categories' keys start with `playlistId|`). */
export function clearPlaylistChannelOrder(map: Record<string, string[]> | undefined, playlistId: string): Record<string, string[]> {
  return Object.fromEntries(Object.entries(map ?? {}).filter(([key]) => !key.startsWith(`${playlistId}|`)));
}

/* ---------- category display names ---------- */

/** Longest display name a category can be given. */
export const MAX_GROUP_NAME_LENGTH = 60;
/** Most renamed categories kept per profile (keeps the synced payload small). */
export const MAX_GROUP_NAMES = 2000;

/** iptvByProfile.<id>.groupNames → only `playlist|group` keys with a non-empty name. */
export function normalizeGroupNames(value: unknown): Record<string, string> {
  const result: Record<string, string> = {};
  if (!value || typeof value !== "object" || Array.isArray(value)) return result;
  for (const [key, raw] of Object.entries(value as Record<string, unknown>)) {
    if (Object.keys(result).length >= MAX_GROUP_NAMES) break;
    if (!key.includes("|") || typeof raw !== "string") continue;
    const name = raw.replace(/\s+/g, " ").trim().slice(0, MAX_GROUP_NAME_LENGTH);
    if (name && name !== groupNameOf(key)) result[key] = name;
  }
  return result;
}

/** The name to show for a category key. */
export function groupDisplayName(names: Record<string, string> | undefined, key: string) {
  return names?.[key] || groupNameOf(key);
}

/** Rename a category; an empty name, or the playlist's own name, removes the custom name. */
export function setGroupDisplayName(names: Record<string, string> | undefined, key: string, name: string): Record<string, string> {
  const next = { ...(names ?? {}) };
  const cleaned = name.replace(/\s+/g, " ").trim().slice(0, MAX_GROUP_NAME_LENGTH);
  if (cleaned && cleaned !== groupNameOf(key)) next[key] = cleaned;
  else delete next[key];
  return next;
}

/** Whether a category matches a search: its own name or the name the user gave it. */
export function groupMatchesQuery(group: Pick<PlaylistGroup, "name" | "label">, needle: string) {
  const query = needle.trim().toLowerCase();
  return !query || group.name.toLowerCase().includes(query) || group.label.toLowerCase().includes(query);
}

import type { IptvChannel } from "./types";

/** Never more than this many live streams at once in the browser. */
export const MULTIVIEW_MAX_TILES = 4;

/** One game in multiview: its best channel first, the others to fall back on. */
export type MultiviewTile = { key: string; title: string; channels: IptvChannel[] };

export type MultiviewAdd = { kind: "added"; tiles: MultiviewTile[] } | { kind: "present"; index: number } | { kind: "full" };

/** The same game, or the same channel under another game, is never opened twice. */
export function addMultiviewTile(tiles: MultiviewTile[], tile: MultiviewTile, capacity: number): MultiviewAdd {
  const first = tile.channels[0]?.id;
  const index = tiles.findIndex(existing => existing.key === tile.key || (!!first && existing.channels[0]?.id === first));
  if (index >= 0) return { kind: "present", index };
  if (!tile.channels.length || tiles.length >= Math.max(1, Math.min(capacity, MULTIVIEW_MAX_TILES))) return { kind: "full" };
  return { kind: "added", tiles: [...tiles, tile] };
}

/**
 * How many streams this browser should run: every tile decodes its own video.
 * Phones and small screens get two, devices that report little memory or few
 * cores three, the rest four.
 */
export function multiviewCapacity(device: { deviceMemory?: number; cores?: number; width?: number }): number {
  if ((device.width ?? 1920) < 700) return 2;
  if ((device.deviceMemory ?? 8) < 4 || (device.cores ?? 8) < 4) return 3;
  return MULTIVIEW_MAX_TILES;
}

/** Which tile keeps the sound after one closes: the same game, or the one that took its place. */
export function audioAfterRemoval(audio: number, removed: number, remaining: number): number {
  if (remaining <= 0) return 0;
  const next = removed < audio ? audio - 1 : removed === audio ? Math.min(removed, remaining - 1) : audio;
  return Math.max(0, Math.min(next, remaining - 1));
}

/** The highest picture a tile asks for: the one with sound gets more. */
export function multiviewMaxHeight(hasAudio: boolean, count: number): number {
  if (count <= 1) return Infinity;
  if (hasAudio) return count === 2 ? 1080 : 720;
  return count === 2 ? 720 : 540;
}

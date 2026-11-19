import type { MediaItem } from "./types";

/**
 * "Play" on the Home billboard opens the title's details and starts playback
 * there through the same automatic source choice as the details Play button
 * (Android's billboard Play does the same: details with play intent). The hero
 * records the intent; the details view consumes it once when it opens.
 */
type Intent = { key: string; at: number; season?: number | null; episode?: number | null };
let pending: Intent | null = null;
const MAX_AGE_MS = 15_000;

const keyOf = (item: Pick<MediaItem, "mediaType" | "id">) => `${item.mediaType}:${item.id}`;

export function requestPlayOnOpen(item: Pick<MediaItem, "mediaType" | "id" | "seasonNumber" | "episodeNumber">, now = Date.now()) {
  pending = { key: keyOf(item), at: now, season: item.seasonNumber ?? null, episode: item.episodeNumber ?? null };
}

/** The pending intent for this title without consuming it (pure: safe in render). */
export function peekPlayOnOpen(item: Pick<MediaItem, "mediaType" | "id">, now = Date.now()) {
  const intent = pending;
  if (!intent || intent.key !== keyOf(item) || now - intent.at > MAX_AGE_MS) return null;
  return { season: intent.season ?? null, episode: intent.episode ?? null };
}

/** Returns the intent for this title (once), or null. Other titles clear it. */
export function takePlayOnOpen(item: Pick<MediaItem, "mediaType" | "id">, now = Date.now()) {
  const intent = pending;
  pending = null;
  if (!intent || intent.key !== keyOf(item) || now - intent.at > MAX_AGE_MS) return null;
  return { season: intent.season ?? null, episode: intent.episode ?? null };
}

export function clearPlayOnOpen() {
  pending = null;
}

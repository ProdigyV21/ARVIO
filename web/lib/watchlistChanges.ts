export type WatchlistChange = { updatedAt: number; removed: boolean };
export type WatchlistChanges = Record<string, WatchlistChange>;

export interface CloudWatchlistItem {
  tmdbId?: number | null;
  mediaType?: string;
  title?: string | null;
  posterPath?: string | null;
  backdropPath?: string | null;
  addedAt?: number;
  sourceOrder?: number;
}

function parsed(value: unknown): unknown {
  if (typeof value !== 'string') return value;
  try { return JSON.parse(value); } catch { return null; }
}

export function watchlistKey(mediaType: unknown, id: unknown): string | null {
  const type = String(mediaType ?? 'movie').trim().toLowerCase();
  const tmdbId = Number(id);
  return (type === 'movie' || type === 'tv') && Number.isSafeInteger(tmdbId) && tmdbId > 0
    ? `${type}:${tmdbId}` : null;
}

export function watchlistChangesStorageKey(userId: string, profileId: string) {
  return `arvio.web.watchlistChanges.v1:${JSON.stringify([userId, profileId])}`;
}

export function mergeWatchlistChanges(...sources: unknown[]): WatchlistChanges {
  const result: WatchlistChanges = Object.create(null);
  for (const source of sources) {
    const values = parsed(source);
    if (!values || typeof values !== 'object' || Array.isArray(values)) continue;
    for (const [key, value] of Object.entries(values)) {
      const change = value as WatchlistChange | null;
      if (!/^(movie|tv):[1-9]\d*$/.test(key) || !change ||
          !Number.isSafeInteger(change.updatedAt) || change.updatedAt <= 0 || typeof change.removed !== 'boolean') continue;
      const previous = result[key];
      if (!previous || change.updatedAt > previous.updatedAt ||
          (change.updatedAt === previous.updatedAt && change.removed)) {
        result[key] = { updatedAt: change.updatedAt, removed: change.removed };
      }
    }
  }
  return result;
}

export function mergeWatchlistItems(changes: WatchlistChanges, ...sources: unknown[]): CloudWatchlistItem[] {
  const result = new Map<string, CloudWatchlistItem>();
  for (const source of sources) {
    const items = parsed(source);
    if (!Array.isArray(items)) continue;
    for (const item of items as CloudWatchlistItem[]) {
      if (!item || typeof item !== 'object') continue;
      const key = watchlistKey(item.mediaType, item.tmdbId);
      if (!key || changes[key]?.removed) continue;
      const addedAt = Number.isFinite(item.addedAt) && Number(item.addedAt) > 0 ? Number(item.addedAt) : 0;
      const previous = result.get(key);
      if (!previous || addedAt > (previous.addedAt ?? 0)) {
        result.set(key, { ...item, tmdbId: Number(item.tmdbId), mediaType: key.split(':')[0], addedAt });
      }
    }
  }
  return [...result.values()];
}

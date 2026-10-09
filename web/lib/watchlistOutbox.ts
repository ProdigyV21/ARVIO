import type { AuthClient } from './auth';
import type { MediaItem } from './types';
import { saveCloudWatchlist } from './cloud';
import { loadStored, saveStored } from './storage';
import { mergeWatchlistChanges, watchlistChangesStorageKey, watchlistKey, type WatchlistChange, type WatchlistChanges } from './watchlistChanges';

type Edit = { id: string; profileId: string; items: MediaItem[]; changes: WatchlistChanges };
const key = (userId: string) => `arvio.web.watchlistOutbox.v1:${userId}`;
const running = new Map<string, Promise<void>>();
const issuedAt = new Map<string, number>();

export function prepareWatchlistChange(auth: AuthClient, item: MediaItem, removed: boolean, profileId?: string | null): WatchlistChange | undefined {
  if (!auth.session || !profileId) return;
  const itemKey = watchlistKey(item.mediaType, item.id);
  if (!itemKey) return;
  const userId = auth.session.userId;
  const changesKey = watchlistChangesStorageKey(userId, profileId);
  const pending = loadStored<Edit[]>(key(userId), []).find(edit => edit.profileId === profileId);
  const observed = mergeWatchlistChanges(loadStored<WatchlistChanges>(changesKey, {}), pending?.changes);
  const clockKey = JSON.stringify([userId, profileId, itemKey]);
  const updatedAt = Math.max(Date.now(), (observed[itemKey]?.updatedAt ?? 0) + 1, (issuedAt.get(clockKey) ?? 0) + 1);
  issuedAt.set(clockKey, updatedAt);
  return { updatedAt, removed };
}

export function queueWatchlistChange(auth: AuthClient, item: MediaItem, removed: boolean, profileId?: string | null, prepared?: WatchlistChange) {
  if (!auth.session || !profileId) return;
  const itemKey = watchlistKey(item.mediaType, item.id);
  const change = prepared ?? prepareWatchlistChange(auth, item, removed, profileId);
  if (!itemKey || !change) return;
  const userId = auth.session.userId;
  const pending = loadStored<Edit[]>(key(userId), []);
  const previous = pending.find(edit => edit.profileId === profileId);
  const changesKey = watchlistChangesStorageKey(userId, profileId);
  const observed = mergeWatchlistChanges(loadStored<WatchlistChanges>(changesKey, {}), previous?.changes);
  const changes = mergeWatchlistChanges(previous?.changes, { [itemKey]: change });
  let items = previous?.items ?? [];
  if (changes[itemKey].updatedAt === change.updatedAt && changes[itemKey].removed === change.removed) {
    items = items.filter(entry => watchlistKey(entry.mediaType, entry.id) !== itemKey);
    if (!change.removed) items.unshift({ ...item, activityAt: change.updatedAt });
  }
  const edit: Edit = { id: crypto.randomUUID(), profileId, items, changes };
  saveStored(key(userId), [...pending.filter(entry => entry.profileId !== profileId), edit]);
  if (!loadStored<Edit[]>(key(userId), []).some(entry => entry.id === edit.id)) {
    throw new Error('Could not save the watchlist change on this device. Keep this page open and retry.');
  }
  saveStored(changesKey, mergeWatchlistChanges(observed, changes));
}

// Apply retained cloud removals and queued edits even when a tracker/cache read
// races an action or a failed cloud write. Scope every lookup to this account.
export function applyPendingWatchlist(auth: AuthClient, profileId: string | null | undefined, items: MediaItem[]): MediaItem[] {
  if (!auth.session || !profileId) return items;
  const userId = auth.session.userId;
  const pending = loadStored<Edit[]>(key(userId), []).find(edit => edit.profileId === profileId);
  const changes = mergeWatchlistChanges(loadStored<WatchlistChanges>(watchlistChangesStorageKey(userId, profileId), {}), pending?.changes);
  const result = new Map<string, MediaItem>();
  for (const item of [...(pending?.items ?? []), ...items]) {
    const itemKey = watchlistKey(item.mediaType, item.id);
    // Trackers may provide a temporary negative id until TMDB hydration.
    const resultKey = itemKey ?? `unresolved:${item.mediaType}:${item.id}`;
    if ((itemKey && changes[itemKey]?.removed) || result.has(resultKey)) continue;
    result.set(resultKey, item);
  }
  return [...result.values()];
}

export async function flushWatchlistOutbox(auth: AuthClient) {
  const userId = auth.session?.userId;
  if (!userId) return;
  if (running.has(userId)) return running.get(userId);
  const task = (async () => {
    while (auth.session?.userId === userId) {
      const edit = loadStored<Edit[]>(key(userId), [])[0];
      if (!edit) return;
      // Reuse the original action timestamp on every retry, so a failed old
      // removal cannot overwrite a more recent add on another device.
      await saveCloudWatchlist(auth, edit.items, edit.profileId, { changes: edit.changes });
      saveStored(key(userId), loadStored<Edit[]>(key(userId), []).filter(entry => entry.id !== edit.id));
    }
  })();
  running.set(userId, task);
  try { await task; } finally { if (running.get(userId) === task) running.delete(userId); }
}

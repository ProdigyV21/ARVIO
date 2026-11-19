// Small, pure list-ordering helpers shared by the Settings layout editors
// (Live TV categories and channels, addons, Home rows, home servers).

/** Move the entry at `from` to `to` (both clamped). Returns the same array when nothing moves. */
export function moveIndex<T>(items: readonly T[], from: number, to: number): T[] {
  if (from < 0 || from >= items.length) return items as T[];
  const target = Math.max(0, Math.min(items.length - 1, to));
  if (target === from) return items as T[];
  const next = [...items];
  const [moved] = next.splice(from, 1);
  next.splice(target, 0, moved);
  return next;
}

/**
 * Move an item while only part of the list is on screen (a search or a source
 * filter). `subset` is the visible keys in their current order; the item moves
 * to the slot of the visible item now at `toSubsetIndex`, and everything that
 * is filtered out keeps its place.
 */
export function moveWithinSubset<T>(items: readonly T[], keyOf: (item: T) => string, subset: readonly string[], fromSubsetIndex: number, toSubsetIndex: number): T[] {
  const fromKey = subset[fromSubsetIndex];
  const to = Math.max(0, Math.min(subset.length - 1, toSubsetIndex));
  const targetKey = subset[to];
  if (fromKey === undefined || targetKey === undefined || fromKey === targetKey) return items as T[];
  const from = items.findIndex((item) => keyOf(item) === fromKey);
  const target = items.findIndex((item) => keyOf(item) === targetKey);
  if (from < 0 || target < 0) return items as T[];
  return moveIndex(items, from, target);
}

/** Order `items` by `order` (keys first, in that order), then the rest in their current order. */
export function orderByKeys<T>(items: readonly T[], keyOf: (item: T) => string, order: readonly string[] | undefined): T[] {
  if (!order?.length) return [...items];
  const rank = new Map<string, number>();
  order.forEach((key, index) => { if (!rank.has(key)) rank.set(key, index); });
  return items
    .map((item, index) => ({ item, index, rank: rank.get(keyOf(item)) }))
    .sort((a, b) => (a.rank ?? Number.MAX_SAFE_INTEGER) - (b.rank ?? Number.MAX_SAFE_INTEGER) || a.index - b.index)
    .map((entry) => entry.item);
}

/**
 * Where an item is displayed while it is being dragged or moved with the
 * keyboard: the list as if the item at `from` were already at `to`. Returns
 * the source index for a displayed position in O(1), so a 50,000-row list does
 * not need to be copied on every arrow key.
 */
export function previewSourceIndex(displayIndex: number, from: number, to: number): number {
  if (from === to) return displayIndex;
  if (displayIndex === to) return from;
  if (from < to && displayIndex >= from && displayIndex < to) return displayIndex + 1;
  if (to < from && displayIndex > to && displayIndex <= from) return displayIndex - 1;
  return displayIndex;
}

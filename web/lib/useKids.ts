"use client";

import { useCallback, useMemo, useSyncExternalStore } from "react";
import { useAppSelector } from "./store";
import { allowsItem } from "./kids";
import { kidsGateFor, kidsRatingStore } from "./kidsGate";
import type { Category, MediaItem } from "./types";

export { isAllowedForProfile } from "./kidsGate";

const noopSubscribe = () => () => {};

/**
 * The active profile's kids filter. The level is read from the active profile on
 * every render (never a cached null), so a kids Home is filtered from its first paint.
 */
export function useKidsFilter() {
  // Only the two inputs of the gate: every card calls this hook, so subscribing
  // to the whole store re-rendered every card on any store change.
  const activeProfile = useAppSelector((store) => store.activeProfile);
  const language = useAppSelector((store) => store.settings.language);
  const { maxAge, region } = kidsGateFor(activeProfile, language);
  const active = maxAge !== null;
  const store = active ? kidsRatingStore() : null;
  const version = useSyncExternalStore(store ? store.subscribe : noopSubscribe, () => store?.getVersion() ?? 0, () => 0);

  const allows = useCallback((item: MediaItem) => {
    if (!store) return true;
    return allowsItem(item, maxAge, region, store.getAges());
    // version: re-evaluate once new ages arrive.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [store, maxAge, region, version]);

  const filter = useCallback(<T extends MediaItem>(items: T[]): T[] => {
    if (!store) return items;
    store.request(items, region);
    return items.filter(allows);
  }, [store, region, allows]);

  const filterCategories = useCallback((categories: Category[]): Category[] => {
    if (!store) return categories;
    return categories.flatMap((category) => {
      const kept = filter(category.items);
      if (kept.length === category.items.length) return [category];
      return kept.length ? [{ ...category, items: kept }] : [];
    });
  }, [store, filter]);

  return useMemo(() => ({ active, maxAge, region, allows, filter, filterCategories }), [active, maxAge, region, allows, filter, filterCategories]);
}

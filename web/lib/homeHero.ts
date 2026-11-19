import type { MediaItem } from "./types";

/**
 * Home billboard state, kept out of the app-wide store: the preview changes
 * every few seconds and on every card hover, and as store state each change
 * re-rendered almost the whole app. Only the hero subscribes to this.
 */
export type HeroState = {
  /** Explicit preview (rotation or a pinned card); null = fall back to Home's default. */
  preview: MediaItem | null;
  /** Showcase titles collected from the first rails. */
  pool: MediaItem[];
  /** The user pointed at / focused a card: the hero follows it and stops rotating. */
  pinned: boolean;
  /** The user pressed the pause control (or prefers reduced motion). */
  paused: boolean;
  /** Pointer or focus is on the hero's own buttons. */
  holding: boolean;
};

export const HERO_POOL_LIMIT = 12;
export const HERO_ROTATE_MS = 8000;

const itemKey = (item: Pick<MediaItem, "mediaType" | "id">) => `${item.mediaType}-${item.id}`;

export function createHeroController(initial?: Partial<HeroState>) {
  let state: HeroState = { preview: null, pool: [], pinned: false, paused: false, holding: false, ...initial };
  let index = 0;
  const listeners = new Set<() => void>();
  const set = (patch: Partial<HeroState>) => {
    const next = { ...state, ...patch };
    if ((Object.keys(patch) as Array<keyof HeroState>).every((key) => next[key] === state[key])) return;
    state = next;
    listeners.forEach((listener) => listener());
  };
  return {
    getState: () => state,
    subscribe(listener: () => void) {
      listeners.add(listener);
      return () => { listeners.delete(listener); };
    },
    /** Feed a loaded rail's titles into the rotation (only ones with artwork). */
    addToPool(items: MediaItem[], allows: (item: MediaItem) => boolean = () => true) {
      if (state.pool.length >= HERO_POOL_LIMIT) return;
      const seen = new Set(state.pool.map(itemKey));
      const additions = items.filter((item) => item.backdrop && allows(item) && !seen.has(itemKey(item))).slice(0, 8);
      if (!additions.length) return;
      const pool = [...state.pool, ...additions].slice(0, HERO_POOL_LIMIT);
      set({ pool, preview: state.preview ?? (state.pinned ? null : pool[0]) });
    },
    /** A card got hover/focus: show it and stop the carousel. */
    pin(item: MediaItem) {
      set({ preview: item, pinned: true });
    },
    rotating() {
      return state.pool.length >= 2 && !state.pinned && !state.paused && !state.holding;
    },
    /** One rotation step; a no-op while paused, pinned or held. */
    advance() {
      if (!this.rotating()) return;
      const current = state.preview ? state.pool.findIndex((item) => itemKey(item) === itemKey(state.preview!)) : -1;
      index = ((current >= 0 ? current : index) + 1) % state.pool.length;
      set({ preview: state.pool[index] });
    },
    setHolding(holding: boolean) { set({ holding }); },
    /** The visible pause/resume control. Resuming also releases a pinned card. */
    togglePaused() {
      if (state.paused || state.pinned) set({ paused: false, pinned: false });
      else set({ paused: true });
    },
    setPaused(paused: boolean) { set({ paused }); }
  };
}

export type HeroController = ReturnType<typeof createHeroController>;

/**
 * Text for the hero synopsis. Whole text, trimmed: the hero clamps it to three
 * lines with CSS instead of cutting at 150 characters mid-word.
 */
export function heroOverview(item: Pick<MediaItem, "overview" | "subtitle"> | null | undefined, fallback: string): string {
  return (item?.overview || item?.subtitle || fallback).replace(/\s+/g, " ").trim();
}

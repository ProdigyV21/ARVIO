export function loadStored<T>(key: string, fallback: T): T {
  if (typeof window === "undefined") return fallback;
  try {
    const raw = window.localStorage.getItem(key);
    if (!raw) return fallback;
    const parsed: unknown = JSON.parse(raw);
    const validate = validatorFor(key);
    if (!validate) return parsed as T;
    const valid = validate(parsed);
    return valid === INVALID ? fallback : (valid as T);
  } catch {
    return fallback;
  }
}

// ---------------------------------------------------------------------------
// Cached-data validation.
//
// Instant-paint caches are re-read on every load for hours. One malformed item
// (a null, a missing id/mediaType — from an old build, a half-written quota
// eviction or a hand-edited store) used to crash the whole app on EVERY start
// ("Cannot read properties of null (reading 'mediaType')") until the TTL ran
// out. Rebuildable caches are therefore validated as they are read: malformed
// items are dropped, a malformed envelope reads as the caller's fallback.

const INVALID = Symbol("invalid-stored-value");
type Validator = (value: unknown) => unknown | typeof INVALID;

const isRecord = (value: unknown): value is Record<string, unknown> =>
  typeof value === "object" && value !== null && !Array.isArray(value);

/** A media item every rail/card can render without throwing. */
export function isRenderableMediaItem(value: unknown): boolean {
  if (!isRecord(value)) return false;
  const { id, mediaType } = value;
  const validId = (typeof id === "number" && Number.isFinite(id)) || (typeof id === "string" && id.length > 0);
  return validId && typeof mediaType === "string" && mediaType.length > 0;
}

/** Keep renderable items; coerce the display fields cards read without guards. */
export function sanitizeMediaItems<T = unknown>(value: unknown): T[] {
  if (!Array.isArray(value)) return [];
  const out: T[] = [];
  for (const item of value) {
    if (!isRenderableMediaItem(item)) continue;
    const next = { ...(item as Record<string, unknown>) };
    if (typeof next.title !== "string") next.title = next.title == null ? "" : String(next.title);
    for (const field of ["genreIds", "genres", "cast", "related"]) {
      if (field in next && !Array.isArray(next[field])) delete next[field];
    }
    for (const field of ["image", "backdrop", "logo", "episodeStill", "year", "overview"]) {
      if (next[field] != null && typeof next[field] !== "string") delete next[field];
    }
    out.push(next as T);
  }
  return out;
}

/** arvio.web.catalogRows.v3: Record<cacheKey, { at, category: { id, title, items } }> */
export function sanitizeCatalogRowCache(value: unknown): Record<string, unknown> | typeof INVALID {
  if (!isRecord(value)) return INVALID;
  const out: Record<string, unknown> = {};
  for (const [key, entry] of Object.entries(value)) {
    if (!isRecord(entry) || typeof entry.at !== "number" || !isRecord(entry.category)) continue;
    const items = sanitizeMediaItems(entry.category.items);
    if (items.length === 0) continue;
    out[key] = { ...entry, category: { ...entry.category, items } };
  }
  return out;
}

/** { at, items } list caches (Continue Watching, watchlist, Trakt lists). */
export function sanitizeListCache(value: unknown): unknown {
  if (value === null) return null;
  if (!isRecord(value) || typeof value.at !== "number") return INVALID;
  return { ...value, items: sanitizeMediaItems(value.items) };
}

/** Local profiles: an array of objects with a string id. */
export function sanitizeProfiles(value: unknown): unknown {
  if (!Array.isArray(value)) return INVALID;
  return value.filter((profile) => isRecord(profile) && typeof profile.id === "string" && profile.id.length > 0);
}

const EXACT_VALIDATORS: Record<string, Validator> = {
  "arvio.web.catalogRows.v3": sanitizeCatalogRowCache,
  "arvio.web.profiles": sanitizeProfiles
};

const PREFIX_VALIDATORS: Array<[string, Validator]> = [
  ["arvio.web.cw.v4:", sanitizeListCache],
  ["arvio.web.watchlist.v1:", sanitizeListCache],
  ["arvio.web.traktlist.v2:", sanitizeListCache]
];

function validatorFor(key: string): Validator | undefined {
  if (key in EXACT_VALIDATORS) return EXACT_VALIDATORS[key];
  return PREFIX_VALIDATORS.find(([prefix]) => key.startsWith(prefix))?.[1];
}

/**
 * Rebuildable caches a crashing screen may be reading. An error boundary
 * clears these before its retry so a poisoned cache cannot crash the same
 * screen again on every load. Never includes settings, profiles, sessions or
 * outboxes — only data the app refetches.
 */
export const SCREEN_CACHE_KEYS: Record<string, readonly string[]> = {
  home: ["arvio.web.catalogRows.v3", "arvio.web.cardExtras.v1", "arvio.web.cw.v4:"],
  search: ["arvio.web.cardExtras.v1"],
  library: ["arvio.web.watchlist.v1:", "arvio.web.traktlist.v2:", "arvio.web.cw.v4:", "arvio.web.cardExtras.v1"],
  tv: [],
  details: ["arvio.web.seasonEpisodes.v1", "arvio.web.cardExtras.v1"],
  player: [],
  settings: [],
  addons: []
};

/** Remove a screen's rebuildable caches (entries ending in ":" are prefixes). */
export function clearScreenCaches(screen: string): string[] {
  if (typeof window === "undefined") return [];
  const patterns = SCREEN_CACHE_KEYS[screen] ?? [];
  const removed: string[] = [];
  let keys: string[] = [];
  try {
    keys = Object.keys(window.localStorage);
  } catch {
    return removed;
  }
  for (const key of keys) {
    if (!patterns.some((pattern) => (pattern.endsWith(":") ? key.startsWith(pattern) : key === pattern))) continue;
    removeStored(key);
    removed.push(key);
  }
  return removed;
}

// Superseded cache keys from older builds. They can hold megabytes (the v1
// catalog rows alone was 1.5MB) and localStorage tops out around 5MB — once
// full, EVERY save silently fails and none of the instant-paint caches
// (Continue Watching seed, Trakt progress) can ever be written again.
const LEGACY_STORAGE_KEYS = [
  "arvio.web.catalogRows.v1",
  "arvio.web.catalogRows.v2",
  "arvio.web.cardMeta.v1",
  // Replaced by the combined arvio.web.cardExtras.v1 (one TMDB call per title).
  "arvio.web.cardMeta.v2",
  "arvio.web.cardProviders.v1",
  "arvio.web.logoCache",
  "arvio.web.cw.v1",
  // v1 stored full Trakt progress payloads (~740KB); v2 is slimmed.
  "arvio.web.trakt.progressCache.v1"
];

export function purgeLegacyStorage() {
  if (typeof window === "undefined") return;
  for (const key of LEGACY_STORAGE_KEYS) {
    try {
      window.localStorage.removeItem(key);
    } catch {
      /* ignore */
    }
  }
}

// Heaviest evictable caches, best-first to sacrifice: all of these are rebuilt
// on demand, so dropping them costs a refetch — never correctness.
const EVICTABLE_CACHE_KEYS = [
  "arvio.web.catalogRows.v3",
  "arvio.web.seasonEpisodes.v1",
  "arvio.web.cardExtras.v1",
  "arvio.web.trakt.progressCache.v2"
];

export function saveStored<T>(key: string, value: T) {
  if (typeof window === "undefined") return;
  const serialized = JSON.stringify(value);
  try {
    window.localStorage.setItem(key, serialized);
  } catch {
    // Quota exceeded (or storage unavailable). Evict rebuildable caches and
    // retry once — a silent drop here is how the Continue Watching seed and
    // progress cache stayed unwritable for weeks on full-storage devices.
    purgeLegacyStorage();
    for (const evictKey of EVICTABLE_CACHE_KEYS) {
      if (evictKey === key) continue;
      try {
        window.localStorage.removeItem(evictKey);
        window.localStorage.setItem(key, serialized);
        return;
      } catch {
        // Still full — evict the next cache and try again.
      }
    }
  }
}

export function removeStored(key: string) {
  if (typeof window === "undefined") return;
  try {
    window.localStorage.removeItem(key);
  } catch {
    // Storage can be unavailable in strict/private browser contexts.
  }
}

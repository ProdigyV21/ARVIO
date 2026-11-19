import { config } from "./config";
import { loadStored, saveStored } from "./storage";

// Real IMDb ratings — parity with the Android app (MediaRepository.getImdbRating).
// TMDB's vote_average is a DIFFERENT score and was previously rendered under an
// IMDb badge, so every number looked wrong next to IMDb. Cinemeta carries the
// actual IMDb rating and is keyed by imdb id, exactly like the app uses it.
//
// Cost: Cinemeta is on the resolver worker's proxy allowlist AND its cacheable
// set, so lookups are served from Cloudflare's edge cache — no Netlify function
// invocations. On top of that we keep a per-session memory cache and a
// localStorage cache, so a rating is fetched at most once per title per week.

type CacheEntry = { rating: string; at: number };

const MEMORY = new Map<string, string>();
const INFLIGHT = new Map<string, Promise<string | null>>();
const STORE_KEY = "arvio.web.imdbRatings.v1";
const TTL_MS = 7 * 24 * 60 * 60 * 1000;
const MAX_ENTRIES = 900;

function normalizeImdbId(value: unknown): string {
  const id = typeof value === "string" ? value.trim().toLowerCase() : "";
  return /^tt\d+$/.test(id) ? id : "";
}

function readStore(): Record<string, CacheEntry> {
  return loadStored<Record<string, CacheEntry>>(STORE_KEY, {});
}

function writeStore(store: Record<string, CacheEntry>) {
  // Bound the cache so it can never grow into the localStorage quota (a full
  // quota silently kills every other write in the app).
  const entries = Object.entries(store);
  if (entries.length > MAX_ENTRIES) {
    const keep = entries.sort((a, b) => b[1].at - a[1].at).slice(0, MAX_ENTRIES);
    store = Object.fromEntries(keep);
  }
  saveStored(STORE_KEY, store);
}

// The persisted store is read once per session into memory. Reading and
// re-parsing it per card (and re-serialising it per fetched rating) cost more
// than the lookups themselves on a 100-card Home.
let restored = false;
function restore() {
  if (restored) return;
  restored = true;
  const cutoff = Date.now() - TTL_MS;
  Object.entries(readStore()).forEach(([id, entry]) => {
    if (entry && typeof entry.rating === "string" && entry.at > cutoff && !MEMORY.has(id)) {
      MEMORY.set(id, entry.rating);
      STAMPS.set(id, entry.at);
    }
  });
}

const STAMPS = new Map<string, number>();

function cached(imdbId: string): string | null {
  restore();
  const hit = MEMORY.get(imdbId);
  return hit !== undefined ? hit : null;
}

let persistTimer: ReturnType<typeof setTimeout> | null = null;
function remember(imdbId: string, rating: string) {
  MEMORY.set(imdbId, rating);
  STAMPS.set(imdbId, Date.now());
  if (persistTimer) return;
  persistTimer = setTimeout(() => {
    persistTimer = null;
    // Merge with what another tab may have written, newest entry wins.
    const store = readStore();
    STAMPS.forEach((at, id) => {
      const rating = MEMORY.get(id);
      if (rating !== undefined && (!store[id] || store[id].at < at)) store[id] = { rating, at };
    });
    writeStore(store);
  }, 1000);
}

/**
 * The cached IMDb rating without a request: a string (possibly "" = known to
 * have none) once looked up, undefined when never looked up.
 */
export function peekImdbRating(imdbId?: string | null): string | null | undefined {
  const id = normalizeImdbId(imdbId);
  if (!/^tt\d+$/.test(id)) return null;
  const hit = cached(id);
  return hit === null ? undefined : hit || null;
}

function cinemetaUrl(mediaType: "movie" | "tv", imdbId: string) {
  const typePath = mediaType === "tv" ? "series" : "movie";
  const target = `https://v3-cinemeta.strem.io/meta/${typePath}/${imdbId}.json`;
  const base = config.resolverUrl.replace(/\/+$/, "");
  // Route through the resolver worker: it is CORS-clean and edge-caches
  // Cinemeta. Without a resolver configured, go direct (Cinemeta allows CORS).
  return base ? `${base}/proxy?url=${encodeURIComponent(target)}` : target;
}

function normalize(raw: unknown): string | null {
  const value = typeof raw === "number" ? raw : Number(String(raw ?? "").trim());
  if (!Number.isFinite(value) || value <= 0) return null;
  return value.toFixed(1);
}

// Episodes need a different source: Cinemeta only carries a series-level
// imdbRating (its per-episode `rating` fields are all 0 — verified across
// several shows), so episode rows would otherwise fall back to TMDB's score.
// Agregarr serves real IMDb ratings for a BATCH of imdb ids in one keyless
// call, which is exactly what the Android app uses (getAgregarrImdbRatings).
const AGREGARR_ENDPOINT = "https://api.agregarr.org/api/ratings";

/**
 * IMDb ratings for many imdb ids at once, keyed by imdb id. Ids already cached
 * are served locally; the rest go out in a single request (chunked at 100, the
 * limit the Android client uses).
 */
export async function getImdbRatings(imdbIds: string[]): Promise<Record<string, string>> {
  const unique = [...new Set(imdbIds.map(normalizeImdbId).filter(Boolean))];
  const result: Record<string, string> = {};
  const missing: string[] = [];
  unique.forEach((id) => {
    const hit = cached(id);
    if (hit === null) missing.push(id);
    else if (hit) result[id] = hit;
  });
  if (!missing.length) return result;

  for (let i = 0; i < missing.length; i += 100) {
    const chunk = missing.slice(i, i + 100);
    const url = `${AGREGARR_ENDPOINT}?${chunk.map((id) => `id=${encodeURIComponent(id)}`).join("&")}`;
    try {
      const response = await fetch(url, {
        headers: { Accept: "application/json" },
        signal: typeof AbortSignal.timeout === "function" ? AbortSignal.timeout(9000) : undefined
      });
      if (!response.ok) continue;
      const rows = await response.json() as Array<{ imdbId?: string; rating?: unknown }>;
      const seen = new Set<string>();
      rows.forEach((row) => {
        const id = normalizeImdbId(row?.imdbId);
        if (!id) return;
        seen.add(id);
        const rating = normalize(row?.rating);
        remember(id, rating ?? "");
        if (rating) result[id] = rating;
      });
      // Ids the endpoint didn't answer for get a negative cache entry too, so a
      // rating-less episode isn't re-requested on every render.
      chunk.forEach((id) => { if (!seen.has(id)) remember(id, ""); });
    } catch {
      // Leave this chunk uncached so a transient failure can be retried later.
    }
  }
  return result;
}

/**
 * The IMDb rating for a title, or null when Cinemeta doesn't know it (common
 * for very new or obscure entries — the caller should then show no badge
 * rather than substituting a different provider's score).
 */
export async function getImdbRating(mediaType: "movie" | "tv", imdbId?: string | null): Promise<string | null> {
  const id = normalizeImdbId(imdbId);
  if (!/^tt\d+$/.test(id)) return null;
  const hit = cached(id);
  if (hit !== null) return hit || null;

  const existing = INFLIGHT.get(id);
  if (existing) return existing;

  const request = (async () => {
    try {
      const response = await fetch(cinemetaUrl(mediaType, id), {
        headers: { Accept: "application/json" },
        signal: typeof AbortSignal.timeout === "function" ? AbortSignal.timeout(8000) : undefined
      });
      if (!response.ok) return null;
      const payload = await response.json() as { meta?: { imdbRating?: unknown } };
      const rating = normalize(payload?.meta?.imdbRating);
      // Cache misses too (as an empty string) so a title Cinemeta has no rating
      // for isn't re-fetched on every render for the next week.
      remember(id, rating ?? "");
      return rating;
    } catch {
      return null;
    } finally {
      INFLIGHT.delete(id);
    }
  })();
  INFLIGHT.set(id, request);
  return request;
}

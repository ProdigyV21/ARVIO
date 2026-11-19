// Kids profile age filter — the web port of Android's KidsContentFilter,
// KidsCertification and ContentRating (com.arflix.tv.data.repository /
// com.arflix.tv.util). Same rules, so a kids profile made on the TV is filtered
// the same way in the browser:
//
// - A kids profile carries an age level; a title passes only when its minimum
//   viewing age is known and at most that level's cap.
// - The own country's and the US certification are both read; the stricter wins.
// - Unknown titles are hidden until their rating is looked up; titles without a
//   readable rating, and items with no TMDB identity, never pass ("block unrated").
// - Ages are cached per country in localStorage, so a kids Home opens filtered at
//   once after the first visit.
import type { MediaItem, MediaType, Profile } from "./types";
import { isYouTubeItem } from "./youtubeCollection";

export type KidsAgeLevel = "LITTLE_KIDS" | "OLDER_KIDS" | "TEENS";

/** Highest minimum viewing age a title may carry at each level (Android KidsAgeLevel.maxAge). */
export const KIDS_LEVEL_MAX_AGE: Record<KidsAgeLevel, number> = {
  LITTLE_KIDS: 7,
  OLDER_KIDS: 10,
  TEENS: 14
};
export const KIDS_LEVELS: KidsAgeLevel[] = ["LITTLE_KIDS", "OLDER_KIDS", "TEENS"];
export const DEFAULT_KIDS_LEVEL: KidsAgeLevel = "OLDER_KIDS";
export const UNRATED = -1;
export const FALLBACK_REGION = "US";

/** The level a profile enforces, or null for a regular profile. Unknown or missing levels read as the default. */
export function kidsLevelOf(profile: Pick<Profile, "isKidsProfile" | "kidsAgeLevel"> | null | undefined): KidsAgeLevel | null {
  if (!profile?.isKidsProfile) return null;
  const name = profile.kidsAgeLevel;
  return KIDS_LEVELS.find(level => level === name) ?? DEFAULT_KIDS_LEVEL;
}

/** Country code from a TMDB content language such as "de-DE"; anything else falls back to the US. */
export function regionOf(contentLanguage: string | null | undefined): string {
  const region = (contentLanguage ?? "").split("-").slice(1).join("-").trim().toUpperCase();
  return /^[A-Z]{2}$/.test(region) ? region : FALLBACK_REGION;
}

// Named certifications; bare numbers ("12", "FSK 16", "R15+", "PG12") are read directly.
const NAMED_AGES: Record<string, number> = {
  // US film. PG covers most current family films, so it sits inside the youngest level.
  "G": 0, "PG": 7, "PG-13": 13, "R": 17, "NC-17": 18,
  // US TV
  "TV-Y": 0, "TV-Y7": 7, "TV-Y7-FV": 7, "TV-G": 0, "TV-PG": 10, "TV-14": 14, "TV-MA": 17,
  // UK
  "U": 0, "UC": 0, "12A": 12, "R18": 18,
  // All-ages markers of other systems (BR, NL, FR, KR, CA). "E" (exempt = unclassified) is left out on purpose.
  "L": 0, "AL": 0, "TP": 0, "ALL": 0, "C": 0, "C8": 8,
  // AU
  "M": 15
};

/** The minimum age behind a certification, or null when it cannot be read. */
export function minimumAge(certification: string | null | undefined): number | null {
  const value = (certification ?? "").trim().toUpperCase();
  if (!value) return null;
  if (value in NAMED_AGES) return NAMED_AGES[value];
  const digits = value.match(/\d{1,2}/g) ?? [];
  // "MA15+", "PG12", "FSK 16" carry one number; anything with two is not a rating.
  if (digits.length !== 1) return null;
  const age = Number(digits[0]);
  return age >= 0 && age <= 21 ? age : null;
}

export interface TmdbReleaseDates {
  results?: Array<{ iso_3166_1?: string; release_dates?: Array<{ certification?: string | null; type?: number }> }>;
}
export interface TmdbContentRatings {
  results?: Array<{ iso_3166_1?: string; rating?: string | null }>;
}

const TYPE_THEATRICAL = 3;

function movieCertification(response: TmdbReleaseDates | null | undefined, region: string): string | null {
  const entry = response?.results?.find(result => result.iso_3166_1?.toUpperCase() === region);
  if (!entry) return null;
  const candidates = (entry.release_dates ?? [])
    .map(release => ({ value: (release.certification ?? "").trim(), type: release.type ?? Number.MAX_SAFE_INTEGER }))
    .filter(candidate => candidate.value);
  if (!candidates.length) return null;
  // The theatrical rating wins, otherwise the lowest release type.
  return candidates.find(candidate => candidate.type === TYPE_THEATRICAL)?.value
    ?? candidates.reduce((best, candidate) => (candidate.type < best.type ? candidate : best)).value;
}

function tvCertification(response: TmdbContentRatings | null | undefined, region: string): string | null {
  const rating = response?.results?.find(result => result.iso_3166_1?.toUpperCase() === region)?.rating?.trim();
  return rating || null;
}

/** Own-country certification, falling back to the US one (ContentRating.selectMovie/selectTv). */
function selected(own: string | null, us: string | null, region: string) {
  return own ?? (region === FALLBACK_REGION ? null : us);
}

function strictest(own: string | null | undefined, us: string | null | undefined): number | null {
  const ages = [minimumAge(own), minimumAge(us)].filter((age): age is number => age !== null);
  return ages.length ? Math.max(...ages) : null;
}

/** Minimum age of a movie: the stricter of the own country's and the US rating; null when neither is readable. */
export function movieAge(response: TmdbReleaseDates | null | undefined, region: string): number | null {
  const own = movieCertification(response, region), us = movieCertification(response, FALLBACK_REGION);
  return strictest(selected(own, us, region), us);
}

/** Minimum age of a TV show: the stricter of the own country's and the US rating; null when neither is readable. */
export function tvAge(response: TmdbContentRatings | null | undefined, region: string): number | null {
  const own = tvCertification(response, region), us = tvCertification(response, FALLBACK_REGION);
  return strictest(selected(own, us, region), us);
}

export function ratingKey(region: string, mediaType: MediaType, tmdbId: number) {
  return `${region}/${mediaType === "tv" ? "TV" : "MOVIE"}/${tmdbId}`;
}

/** The TMDB id the age lookup uses, or null when the item has none (native addon items, unmatched server items). */
export function tmdbIdentity(item: Pick<MediaItem, "id" | "tmdbId" | "isHomeServer" | "addonNativeId">): number | null {
  if (item.isHomeServer) return item.tmdbId && item.tmdbId > 0 ? item.tmdbId : null;
  if (item.addonNativeId) return null;
  return item.id > 0 ? item.id : null;
}

/** True when the item is exempt from rating: a parent approved every video of the profile's YouTube row on Android. */
function isExempt(item: MediaItem) {
  try { return isYouTubeItem(item); } catch { return false; }
}

/** True when [item] may be shown at [maxAge]. Never true for an unknown or unrated title. */
export function allowsItem(item: MediaItem, maxAge: number | null, region: string, ages: ReadonlyMap<string, number>): boolean {
  if (maxAge === null) return true;
  if (isExempt(item)) return true;
  const id = tmdbIdentity(item);
  if (id === null) return false;
  const age = ages.get(ratingKey(region, item.mediaType, id));
  return age !== undefined && age !== UNRATED && age <= maxAge;
}

/** True when [item] still waits for a lookup, i.e. hidden only for now. */
export function isUnknownItem(item: MediaItem, region: string, ages: ReadonlyMap<string, number>): boolean {
  const id = tmdbIdentity(item);
  return id !== null && !isExempt(item) && !ages.has(ratingKey(region, item.mediaType, id));
}

// ---------------------------------------------------------------------------
// Rating store: cached ages + a bounded background lookup queue.
// ---------------------------------------------------------------------------

type Fetcher = (mediaType: MediaType, tmdbId: number) => Promise<TmdbReleaseDates | TmdbContentRatings>;

const STORAGE_KEY = "arvio_kids_ratings_v3";
const MAX_STORED = 20_000;
const LOOKUP_PARALLELISM = 6;
const FLUSH_INTERVAL_MS = 300;

export function createKidsRatingStore(fetcher: Fetcher, storage: Pick<Storage, "getItem" | "setItem" | "removeItem"> | null) {
  let ages = new Map<string, number>();
  try {
    const raw = storage?.getItem(STORAGE_KEY);
    const parsed = raw ? JSON.parse(raw) as Record<string, unknown> : {};
    for (const [key, value] of Object.entries(parsed)) if (typeof value === "number") ages.set(key, value);
  } catch { ages = new Map(); }

  let version = 0;
  const listeners = new Set<() => void>();
  const queue: Array<{ key: string; mediaType: MediaType; id: number; region: string }> = [];
  const inFlight = new Set<string>();
  const pending = new Map<string, number>();
  let running = 0;
  let flushTimer: ReturnType<typeof setTimeout> | null = null;

  const persist = () => {
    if (!storage) return;
    try {
      if (ages.size > MAX_STORED) ages = new Map([...ages].slice(-Math.floor(MAX_STORED / 2)));
      storage.setItem(STORAGE_KEY, JSON.stringify(Object.fromEntries(ages)));
    } catch { /* Storage full or blocked: the filter still works for this session. */ }
  };

  const commit = (batch: Map<string, number>) => {
    if (!batch.size) return;
    ages = new Map([...ages, ...batch]);
    version += 1;
    persist();
    listeners.forEach(listener => listener());
  };

  const scheduleFlush = () => {
    if (flushTimer) return;
    flushTimer = setTimeout(() => {
      flushTimer = null;
      const batch = new Map(pending);
      pending.clear();
      commit(batch);
    }, FLUSH_INTERVAL_MS);
  };

  /** The minimum age, UNRATED when TMDB has no readable rating, null when the request failed. */
  const fetchAge = async (mediaType: MediaType, id: number, region: string): Promise<number | null> => {
    try {
      const response = await fetcher(mediaType, id);
      const age = mediaType === "tv" ? tvAge(response as TmdbContentRatings, region) : movieAge(response as TmdbReleaseDates, region);
      return age ?? UNRATED;
    } catch {
      return null; // A failed request stays unknown: hidden now, asked again next time.
    }
  };

  const pump = () => {
    while (running < LOOKUP_PARALLELISM && queue.length) {
      const job = queue.shift()!;
      running += 1;
      void fetchAge(job.mediaType, job.id, job.region).then((age) => {
        if (age !== null) { pending.set(job.key, age); scheduleFlush(); }
      }).finally(() => {
        inFlight.delete(job.key);
        running -= 1;
        pump();
      });
    }
  };

  return {
    getAges: () => ages as ReadonlyMap<string, number>,
    getVersion: () => version,
    subscribe(listener: () => void) {
      listeners.add(listener);
      return () => { listeners.delete(listener); };
    },
    /** Queues lookups for the items whose age is not known yet. */
    request(items: MediaItem[], region: string) {
      for (const item of items) {
        if (!isUnknownItem(item, region, ages)) continue;
        const id = tmdbIdentity(item)!;
        const key = ratingKey(region, item.mediaType, id);
        if (inFlight.has(key)) continue;
        inFlight.add(key);
        queue.push({ key, mediaType: item.mediaType, id, region });
      }
      pump();
    },
    /** Blocking check for one title (details and player gates): waits for the lookup instead of hiding. */
    async isAllowed(item: MediaItem, maxAge: number | null, region: string): Promise<boolean> {
      if (maxAge === null) return true;
      if (isExempt(item)) return true;
      const id = tmdbIdentity(item);
      if (id === null) return false;
      const key = ratingKey(region, item.mediaType, id);
      let age = ages.get(key);
      if (age === undefined) {
        const fetched = await fetchAge(item.mediaType, id, region);
        if (fetched === null) return false;
        commit(new Map([[key, fetched]]));
        age = fetched;
      }
      return age !== UNRATED && age <= maxAge;
    }
  };
}

export type KidsRatingStore = ReturnType<typeof createKidsRatingStore>;

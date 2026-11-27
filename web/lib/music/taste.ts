// What the profile listens to and looks for, synced with the Android app (MusicTaste.kt) through
// profileSettingsById.<id>.musicTasteJson: the artists of the songs it heard (with a play count)
// and its recent music searches. The Music home turns it into "Because you listened to ..." and
// "Based on your searches" rows. Pure updates, so the rules are tested without a browser.
import { normalizeMusicText } from "./text";

export interface MusicTasteArtist { name: string; plays: number; lastPlayed: number }
export interface MusicTasteSearch { query: string; at: number }
export interface MusicTaste {
  artists: MusicTasteArtist[];
  searches: MusicTasteSearch[];
  /** The Android app's recently played songs; kept as read so a change made here does not drop them. */
  recent?: unknown[];
}

const MAX_ARTISTS = 40;
const MAX_SEARCHES = 15;
const MIN_QUERY = 3;
const ROTATION_POOL = 5;
const HALF_LIFE_DAYS = 21;

export const emptyTaste = (): MusicTaste => ({ artists: [], searches: [] });
export const tasteIsEmpty = (t: MusicTaste) => !t.artists.length && !t.searches.length;

/** Plays count less as they age: one play three weeks ago weighs half a play today. */
export function tasteScore(artist: MusicTasteArtist, now: number) {
  const days = Math.max(0, now - artist.lastPlayed) / 86_400_000;
  return artist.plays * Math.pow(0.5, days / HALF_LIFE_DAYS);
}

/** "Artist feat. Guest" and "A, B" count for their first artist; "Simon & Garfunkel" stays whole. */
export function primaryArtist(artist: string) {
  return artist.split(/\s+(?:feat\.?|ft\.?|featuring)\s+|\s*[,;/]\s*/i)[0].trim();
}

export function recordPlay(taste: MusicTaste, artist: string, now: number): MusicTaste {
  const name = primaryArtist(artist);
  if (!name) return taste;
  const key = normalizeMusicText(name);
  const existing = taste.artists.find((a) => normalizeMusicText(a.name) === key);
  const updated: MusicTasteArtist = { name, plays: (existing?.plays ?? 0) + 1, lastPlayed: now };
  const artists = [updated, ...taste.artists.filter((a) => a !== existing)]
    .map((a, i) => ({ a, i, score: tasteScore(a, now) }))
    .sort((x, y) => y.score - x.score || x.i - y.i)
    .map((x) => x.a)
    .slice(0, MAX_ARTISTS);
  return { ...taste, artists };
}

export function recordSearch(taste: MusicTaste, query: string, now: number): MusicTaste {
  const trimmed = query.trim().replace(/\s+/g, " ");
  if (trimmed.length < MIN_QUERY) return taste;
  const key = normalizeMusicText(trimmed);
  // "dua", "dua l", "dua lipa": a longer search of the same start replaces the shorter one.
  const rest = taste.searches.filter((s) => { const old = normalizeMusicText(s.query); return old !== key && !key.startsWith(old); });
  return { ...taste, searches: [{ query: trimmed, at: now }, ...rest].slice(0, MAX_SEARCHES) };
}

/** Same generator as chartCatalog's, so a day's pick stays put across reloads. */
function seeded(seed: number) {
  let a = seed >>> 0;
  return () => {
    a = (a + 0x6d2b79f5) >>> 0;
    let t = a;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

/** The artists to recommend from today: the favourite always, the rest change by day. */
export function artistsForToday(taste: MusicTaste, now: number, day: number, count = 2): string[] {
  const ranked = taste.artists
    .map((a, i) => ({ a, i, score: tasteScore(a, now) }))
    .sort((x, y) => y.score - x.score || x.i - y.i)
    .map((x) => x.a.name);
  if (!ranked.length) return [];
  const others = ranked.slice(1, 1 + ROTATION_POOL);
  const random = seeded(day);
  for (let i = others.length - 1; i > 0; i--) {
    const j = Math.floor(random() * (i + 1));
    [others[i], others[j]] = [others[j], others[i]];
  }
  return [ranked[0], ...others].slice(0, count);
}

export const recentSearches = (taste: MusicTaste, count = 3) => taste.searches.slice(0, count).map((s) => s.query);

/** Gson leaves fields missing from older saves null, whatever Kotlin says. */
export function parseTaste(json: string | null | undefined): MusicTaste {
  try {
    const raw = JSON.parse(json || "{}") as Record<string, unknown>;
    const arr = (v: unknown) => (Array.isArray(v) ? v : []) as Array<Record<string, unknown>>;
    return {
      artists: arr(raw.artists).filter((a) => a && typeof a.name === "string" && a.name.trim())
        .map((a) => ({ name: a.name as string, plays: Number(a.plays) || 1, lastPlayed: Number(a.lastPlayed) || 0 })),
      searches: arr(raw.searches).filter((s) => s && typeof s.query === "string" && s.query.trim())
        .map((s) => ({ query: s.query as string, at: Number(s.at) || 0 })),
      ...(Array.isArray(raw.recent) ? { recent: raw.recent } : {})
    };
  } catch {
    return emptyTaste();
  }
}

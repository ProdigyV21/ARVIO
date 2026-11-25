// What bitrate this browser's connection actually keeps up with, learned from
// real playback on this device: a file that played for minutes without stalls
// proves its bitrate fits; one that kept stalling proves it does not. Source
// ranking then stops choosing files above that limit (a 70 Mbit/s 4K remux on
// a 40 Mbit/s line), which start slowly and buffer. Stored only in this
// browser, never synced: it describes this device's network, not the account.

export type PlaybackSample = { mbps: number; ok: boolean; at: number };

const KEY = "arvio.playback.connectionSamples";
const MAX_SAMPLES = 24;
const MAX_AGE_MS = 21 * 86_400_000;
/** Watching at least this long without stalls proves the bitrate fits. */
export const CLEAN_SECONDS = 180;
/** This many stalls in a session (seeks excluded) proves it does not. */
export const STALL_LIMIT = 2;

let runtimeMinutes: number | undefined;

function storage(): Pick<Storage, "getItem" | "setItem"> | undefined {
  try { return typeof window === "undefined" ? undefined : window.localStorage; } catch { return undefined; }
}

/** Runtime of the title whose sources are being ranked ("1h 52m", "47m"), or undefined. */
export function setRankingRuntime(duration: string | number | undefined | null, mediaType?: "movie" | "tv") {
  if (typeof duration === "number" && duration > 0) { runtimeMinutes = duration; return; }
  const text = typeof duration === "string" ? duration : "";
  const hours = Number(text.match(/(\d+)\s*h/i)?.[1] ?? 0);
  const minutes = Number(text.match(/(\d+)\s*m/i)?.[1] ?? 0);
  const parsed = hours * 60 + minutes;
  runtimeMinutes = parsed > 0 ? parsed : mediaType === "tv" ? 45 : mediaType === "movie" ? 110 : undefined;
}

export function rankingRuntimeMinutes() { return runtimeMinutes; }

/** Average bitrate of a file in Mbit/s, or null without both size and runtime. */
export function fileMbps(bytes: number, seconds: number): number | null {
  if (!(bytes > 0) || !(seconds > 60)) return null;
  return (bytes * 8) / seconds / 1_000_000;
}

export function readSamples(store = storage(), now = Date.now()): PlaybackSample[] {
  try {
    const saved = JSON.parse(store?.getItem(KEY) || "[]");
    if (!Array.isArray(saved)) return [];
    return saved
      .filter((entry): entry is PlaybackSample => entry && Number.isFinite(entry.mbps) && entry.mbps > 0 && entry.mbps < 1000 && typeof entry.ok === "boolean" && Number.isFinite(entry.at))
      .filter((entry) => now - entry.at < MAX_AGE_MS)
      .slice(-MAX_SAMPLES);
  } catch { return []; }
}

/** One finished (or abandoned) session: how fast the file was and whether it kept up. */
export function recordPlaybackSample(mbps: number | null, watchedSeconds: number, stalls: number, store = storage(), now = Date.now()) {
  if (!mbps || !store) return;
  const struggled = stalls >= STALL_LIMIT;
  // Too short to prove anything either way.
  if (!struggled && watchedSeconds < CLEAN_SECONDS) return;
  const samples = [...readSamples(store, now), { mbps: Math.round(mbps * 10) / 10, ok: !struggled, at: now }].slice(-MAX_SAMPLES);
  try { store.setItem(KEY, JSON.stringify(samples)); } catch { /* storage blocked */ }
  cachedLimit = null;
}

/**
 * The highest bitrate to prefer, or null when this device has never struggled
 * (then nothing is held back). A struggle sets the limit just under that file's
 * bitrate; a later clean session at a higher bitrate lifts it again, because
 * one slow evening must not cap the line for three weeks.
 */
export function sustainableMbps(samples: PlaybackSample[] = readSamples()): number | null {
  const struggles = samples.filter((sample) => !sample.ok);
  if (!struggles.length) return null;
  const lastStruggle = struggles.reduce((latest, sample) => (sample.at > latest.at ? sample : latest));
  const cleanAfter = samples.filter((sample) => sample.ok && sample.at > lastStruggle.at).map((sample) => sample.mbps);
  const limit = Math.min(...struggles.map((sample) => sample.mbps)) * 0.85;
  return Math.max(limit, ...cleanAfter);
}

// Ranking calls this inside sort comparators: read storage at most every few seconds.
let cachedLimit: { value: number | null; at: number } | null = null;
function currentLimit() {
  const now = Date.now();
  if (!cachedLimit || now - cachedLimit.at > 5000) cachedLimit = { value: sustainableMbps(), at: now };
  return cachedLimit.value;
}

/** True when this source's average bitrate is above what the connection proved it can carry. */
export function exceedsConnection(bytes: number, minutes = runtimeMinutes, limit = currentLimit()): boolean {
  if (!limit || !minutes) return false;
  const mbps = fileMbps(bytes, minutes * 60);
  return mbps !== null && mbps > limit;
}

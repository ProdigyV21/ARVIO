import type { PlaybackMethod } from "./playbackTelemetry";

// How fast successful starts are, and how often playback stalls, without any
// extra request: this browser keeps a small tally and hands it to the daily
// playback_started event it already sends (premiumAnalytics.trackPremiumDaily).
// Only counts and allow-listed bucket names: no title, URL, host or time stamp.

export type StartupBucket = "under_2s" | "2_5s" | "5_10s" | "over_10s";
export type StartTally = {
  starts_direct: number; starts_remux: number; starts_hls: number; starts_other: number;
  start_under_2s: number; start_2_5s: number; start_5_10s: number; start_over_10s: number;
  stalls: number;
};

const KEY = "arvio.playback.startTally";
const MAX = 500;

export function startupBucket(milliseconds: number): StartupBucket {
  if (milliseconds < 2000) return "under_2s";
  if (milliseconds < 5000) return "2_5s";
  if (milliseconds < 10000) return "5_10s";
  return "over_10s";
}

export function emptyTally(): StartTally {
  return { starts_direct: 0, starts_remux: 0, starts_hls: 0, starts_other: 0, start_under_2s: 0, start_2_5s: 0, start_5_10s: 0, start_over_10s: 0, stalls: 0 };
}

function storage(): Storage | undefined {
  try { return typeof window === "undefined" ? undefined : window.localStorage; } catch { return undefined; }
}

export function readTally(store: Pick<Storage, "getItem"> | undefined = storage()): StartTally {
  const tally = emptyTally();
  try {
    const saved = JSON.parse(store?.getItem(KEY) || "{}") as Partial<Record<keyof StartTally, unknown>>;
    for (const field of Object.keys(tally) as (keyof StartTally)[]) {
      const value = Math.floor(Number(saved[field]) || 0);
      tally[field] = Math.min(MAX, Math.max(0, value));
    }
  } catch { /* a damaged tally starts over */ }
  return tally;
}

function writeTally(tally: StartTally, store: Pick<Storage, "setItem"> | undefined = storage()) {
  try { store?.setItem(KEY, JSON.stringify(tally)); } catch { /* storage full or blocked: skip */ }
}

/** Counts one successful start in this browser's tally. */
export function recordStart(method: PlaybackMethod, milliseconds: number, store: Pick<Storage, "getItem" | "setItem"> | undefined = storage()) {
  const tally = readTally(store);
  const methodField = `starts_${method}` as keyof StartTally;
  const bucketField = `start_${startupBucket(milliseconds)}` as keyof StartTally;
  tally[methodField] = Math.min(MAX, tally[methodField] + 1);
  tally[bucketField] = Math.min(MAX, tally[bucketField] + 1);
  writeTally(tally, store);
}

/** Counts one stall (buffering after playback had started, not caused by a seek). */
export function recordStall(store: Pick<Storage, "getItem" | "setItem"> | undefined = storage()) {
  const tally = readTally(store);
  tally.stalls = Math.min(MAX, tally.stalls + 1);
  writeTally(tally, store);
}

/** The non-zero part of the tally, ready to ride along on the daily event. */
export function tallyMetadata(tally: StartTally): Partial<StartTally> {
  return Object.fromEntries(Object.entries(tally).filter(([, value]) => value > 0)) as Partial<StartTally>;
}

/** Removes what was reported, keeping anything counted while the request ran. */
export function subtractReported(reported: Partial<StartTally>, store: Pick<Storage, "getItem" | "setItem"> | undefined = storage()) {
  const tally = readTally(store);
  for (const [field, value] of Object.entries(reported) as [keyof StartTally, number][]) {
    tally[field] = Math.max(0, tally[field] - value);
  }
  writeTally(tally, store);
}

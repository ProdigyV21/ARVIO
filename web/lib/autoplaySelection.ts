import type { StreamSource } from "./types";

// Autoplay source choice — "Play" / "Play best" / next episode.
//
// Sources arrive progressively, one addon at a time, and autoplay used to take
// whichever browser-playable row showed up first. The first row is usually the
// FASTEST addon, not the best one: a free file-host that answers in 300 ms beat
// the user's debrid addon that answers in 1.5 s, and then died at play time.
//
// This mirrors the Android app's preferredBingeGroup handling: remember which
// addon (and release group) last played successfully on this device, give that
// addon — or any debrid source — a short, bounded window to arrive, then pick.
// Before starting, the top few candidates are probed (bounded) so a codec the
// release name hid is caught before the user sees an error screen.

export type AutoplayPreference = {
  /** Addon that last started browser playback on this device. */
  addonId?: string;
  /** Stremio behaviorHints.bingeGroup of that source (release group/quality). */
  bingeGroup?: string;
  at?: number;
};

export const AUTOPLAY_PREFERENCE_KEY = "arvio.autoplay.preferredSource.v1";

type StorageLike = Pick<Storage, "getItem" | "setItem">;

function defaultStorage(): StorageLike | undefined {
  try { return typeof window === "undefined" ? undefined : window.localStorage; } catch { return undefined; }
}

const SAFE_TEXT = /^[\w .:/@+|()[\]-]{1,160}$/;

export function loadAutoplayPreference(storage: StorageLike | undefined = defaultStorage()): AutoplayPreference {
  try {
    const raw = JSON.parse(storage?.getItem(AUTOPLAY_PREFERENCE_KEY) || "{}");
    if (!raw || typeof raw !== "object") return {};
    const result: AutoplayPreference = {};
    if (typeof raw.addonId === "string" && SAFE_TEXT.test(raw.addonId)) result.addonId = raw.addonId;
    if (typeof raw.bingeGroup === "string" && SAFE_TEXT.test(raw.bingeGroup)) result.bingeGroup = raw.bingeGroup;
    if (typeof raw.at === "number" && Number.isFinite(raw.at)) result.at = raw.at;
    return result;
  } catch {
    return {};
  }
}

/**
 * Remember the source that just started playing. Only addon-backed VOD sources
 * count: a home server, IPTV channel or Telegram file is not an "addon the
 * user prefers" and would hijack every later title.
 */
export function rememberSuccessfulSource(stream: Pick<StreamSource, "addonId" | "behaviorHints" | "homeServer">,
  storage: StorageLike | undefined = defaultStorage(), now = Date.now()) {
  const addonId = stream.addonId?.trim();
  if (!addonId || stream.homeServer || /^(iptv|telegram|arvio_youtube|home)/i.test(addonId) || !SAFE_TEXT.test(addonId)) return false;
  const bingeGroup = stream.behaviorHints?.bingeGroup?.trim();
  const value: AutoplayPreference = { addonId, at: now, ...(bingeGroup && SAFE_TEXT.test(bingeGroup) ? { bingeGroup } : {}) };
  try {
    storage?.setItem(AUTOPLAY_PREFERENCE_KEY, JSON.stringify(value));
    return true;
  } catch {
    return false;
  }
}

/** A debrid source, or one from the addon that last played on this device. */
export function isPreferredAutoplaySource(stream: StreamSource, preference: AutoplayPreference, isDebrid: (stream: StreamSource) => boolean) {
  if (isDebrid(stream)) return true;
  return Boolean(preference.addonId && stream.addonId === preference.addonId);
}

/**
 * Re-order browser autoplay candidates (already sorted best-first) so the
 * remembered release group, then the remembered addon, lead — but only within
 * the leading playback-method tier. A preference must never trade a source
 * that direct-plays for one that needs conversion.
 */
export function orderByPreference(candidates: StreamSource[], preference: AutoplayPreference, methodOf: (stream: StreamSource) => string = () => "direct") {
  if (!candidates.length || (!preference.addonId && !preference.bingeGroup)) return [...candidates];
  const leadMethod = methodOf(candidates[0]);
  const tier = (stream: StreamSource) => {
    if (methodOf(stream) !== leadMethod) return 3;
    if (preference.addonId && stream.addonId === preference.addonId) {
      return preference.bingeGroup && stream.behaviorHints?.bingeGroup === preference.bingeGroup ? 0 : 1;
    }
    return 2;
  };
  return candidates.map((stream, index) => ({ stream, index, tier: tier(stream) }))
    .sort((a, b) => (Math.min(a.tier, 2) - Math.min(b.tier, 2)) || a.index - b.index)
    .map((entry) => entry.stream);
}

/** true = verified playable, false = verified NOT playable, null = unknown. */
export type CandidateVerifier = (
  candidates: StreamSource[],
  budgetMs: number,
  signal: AbortSignal,
  /** Optional progressive report: each candidate's verdict as soon as it is known. */
  onVerdict?: (stream: StreamSource, verdict: boolean | null) => void
) => Promise<Map<StreamSource, boolean | null>>;

/**
 * The candidate to start, given the verdicts known so far, or undefined while
 * that is still open: walk the order best-first, skip verified failures, stop
 * at the first candidate that passed or is inconclusive. A pending verdict
 * ahead of it means "wait" — a lower-ranked pass never jumps the queue.
 */
export function decidedCandidate(head: StreamSource[], verdicts: Map<StreamSource, boolean | null>) {
  for (const stream of head) {
    if (!verdicts.has(stream)) return undefined;
    if (verdicts.get(stream) === false) continue;
    return stream;
  }
  return null;
}

export type AutoplaySelectionOptions = {
  /** Current browser autoplay candidates, best first (re-read on every poll). */
  candidates: () => StreamSource[];
  /** True while addons are still returning sources. */
  searching: () => boolean;
  preference?: AutoplayPreference;
  isDebrid: (stream: StreamSource) => boolean;
  methodOf?: (stream: StreamSource) => string;
  verify?: CandidateVerifier;
  signal?: AbortSignal;
  /** Window for a preferred/debrid source after the first candidate exists. */
  preferredWaitMs?: number;
  /** Upper bound for the whole pre-play probe of the top candidates. */
  verifyBudgetMs?: number;
  /** How many leading candidates to probe. */
  verifyCount?: number;
  /** Give up waiting for ANY candidate after this long (search still running). */
  maxWaitMs?: number;
  pollMs?: number;
  now?: () => number;
  sleep?: (ms: number, signal?: AbortSignal) => Promise<void>;
};

export type AutoplaySelection = {
  stream: StreamSource | null;
  /** Why this source was chosen — for tests and diagnostics only. */
  reason: "preferred" | "timeout" | "search-complete" | "none" | "aborted";
  waitedMs: number;
  rejected: number;
};

const defaultSleep = (ms: number, signal?: AbortSignal) => new Promise<void>((resolve) => {
  const timer = setTimeout(done, ms);
  function done() { clearTimeout(timer); signal?.removeEventListener("abort", done); resolve(); }
  signal?.addEventListener("abort", done, { once: true });
});

export async function chooseAutoplaySource(options: AutoplaySelectionOptions): Promise<AutoplaySelection> {
  const now = options.now ?? Date.now;
  const sleep = options.sleep ?? defaultSleep;
  const poll = options.pollMs ?? 150;
  const preference = options.preference ?? {};
  const methodOf = options.methodOf ?? (() => "direct");
  const signal = options.signal;
  const started = now();
  const result = (stream: StreamSource | null, reason: AutoplaySelection["reason"], rejected = 0): AutoplaySelection =>
    ({ stream, reason, waitedMs: now() - started, rejected });

  // 1. Wait for the first browser-playable candidate (or the end of the search).
  let list = options.candidates();
  while (!list.length) {
    if (signal?.aborted) return result(null, "aborted");
    if (!options.searching() || now() - started >= (options.maxWaitMs ?? 45_000)) return result(null, "none");
    await sleep(poll, signal);
    list = options.candidates();
  }

  // 2. Bounded window for a debrid source / the remembered addon. Measured from
  //    the first candidate so slow searches do not eat the whole window.
  const preferredDeadline = now() + (options.preferredWaitMs ?? 2_500);
  const hasPreferred = () => list.some((stream) => isPreferredAutoplaySource(stream, preference, options.isDebrid));
  let reason: AutoplaySelection["reason"] = "preferred";
  while (!hasPreferred()) {
    if (signal?.aborted) return result(null, "aborted");
    if (!options.searching()) { reason = "search-complete"; break; }
    if (now() >= preferredDeadline) { reason = "timeout"; break; }
    await sleep(Math.min(poll, Math.max(1, preferredDeadline - now())), signal);
    list = options.candidates();
  }
  if (signal?.aborted) return result(null, "aborted");

  // Debrid rows already outrank free hosts in sourcePickerScore; the wait only
  // gives them time to arrive. The remembered addon/release group then leads
  // its method tier.
  const ordered = orderByPreference(list, preference, methodOf);

  // 3. Probe the top few. Anything verified unplayable drops behind the rest;
  //    unknown verdicts (CORS, timeout, no WebCodecs) never demote a source.
  const count = Math.max(0, options.verifyCount ?? 3);
  if (!options.verify || !count) return result(ordered[0] ?? null, reason);
  const head = ordered.slice(0, count);
  let verdicts = new Map<StreamSource, boolean | null>();
  const budget = options.verifyBudgetMs ?? 2_500;
  const probe = new AbortController();
  const abortProbe = () => probe.abort();
  signal?.addEventListener("abort", abortProbe, { once: true });
  const progressive = new Map<StreamSource, boolean | null>();
  // Resolves the moment the best-ranked candidate is decided (it passed, or
  // everything ahead of it failed): no waiting for slower probes or the budget.
  let decide: (() => void) | undefined;
  const decided = new Promise<void>((resolve) => { decide = resolve; });
  const onVerdict = (stream: StreamSource, verdict: boolean | null) => {
    progressive.set(stream, verdict);
    if (decidedCandidate(head, progressive) !== undefined) decide?.();
  };
  try {
    verdicts = await Promise.race([
      options.verify(head, budget, probe.signal, onVerdict).catch(() => new Map<StreamSource, boolean | null>()),
      decided.then(() => new Map(progressive)),
      sleep(budget + 250, probe.signal).then(() => new Map(progressive))
    ]);
  } finally {
    // Never leave probes holding connections once playback is about to start.
    probe.abort();
    signal?.removeEventListener("abort", abortProbe);
  }
  if (signal?.aborted) return result(null, "aborted");
  const rejected = head.filter((stream) => verdicts.get(stream) === false);
  if (!rejected.length) return result(ordered[0] ?? null, reason);
  const reordered = [...ordered.filter((stream) => !rejected.includes(stream)), ...rejected];
  // Every candidate verified bad: still start the best one. The player has its
  // own remux / provider-conversion / next-source ladder; refusing to play at
  // all would be worse than trying.
  return result(reordered[0] ?? null, reason, rejected.length);
}

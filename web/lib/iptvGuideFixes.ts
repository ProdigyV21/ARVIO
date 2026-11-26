import type { IptvNowNext, IptvProgram } from "./types";

/**
 * The user's own guide corrections, shared with the Android app through
 * iptvByProfile.<profile>.guideSources / guideOffsets:
 * - guideSources: channel id -> the channel whose guide it shows, or "none".
 * - guideOffsets: playlist (or portal) id -> minutes added to every programme time.
 * The loaded guide itself is never rewritten; corrections apply where it is shown.
 */
export const GUIDE_SOURCE_NONE = "none";
export const MAX_GUIDE_OFFSET_MINUTES = 12 * 60;
export const GUIDE_OFFSET_STEP_MINUTES = 30;

export type GuideFixes = { sources: Record<string, string>; offsets: Record<string, number> };

export function normalizeGuideSources(value: unknown): Record<string, string> {
  const result: Record<string, string> = {};
  if (!value || typeof value !== "object" || Array.isArray(value)) return result;
  for (const [rawKey, raw] of Object.entries(value as Record<string, unknown>)) {
    const key = rawKey.trim();
    const source = typeof raw === "string" ? raw.trim() : "";
    if (key && source && source !== key) result[key] = source;
  }
  return result;
}

/** Whole half hours within ±12 hours; zero and anything non-numeric is dropped. */
export function normalizeGuideOffsets(value: unknown): Record<string, number> {
  const result: Record<string, number> = {};
  if (!value || typeof value !== "object" || Array.isArray(value)) return result;
  for (const [rawKey, raw] of Object.entries(value as Record<string, unknown>)) {
    const key = rawKey.trim();
    const minutes = typeof raw === "number" ? raw : typeof raw === "string" && raw.trim() ? Number(raw) : NaN;
    if (!key || !Number.isFinite(minutes)) continue;
    const rounded = Math.max(-MAX_GUIDE_OFFSET_MINUTES, Math.min(MAX_GUIDE_OFFSET_MINUTES,
      Math.round(minutes / GUIDE_OFFSET_STEP_MINUTES) * GUIDE_OFFSET_STEP_MINUTES));
    if (rounded !== 0) result[key] = rounded;
  }
  return result;
}

/** Playlist (or portal) a channel id belongs to: `<playlist>:<id>`, `stalker:<portal>:<id>`. */
export function guidePlaylistIdOf(channelId: string): string {
  if (channelId.startsWith("stalker:")) return channelId.slice("stalker:".length).split(":")[0] ?? "";
  return channelId.split(":")[0] ?? "";
}

/** The channel whose guide `channelId` shows; null when the user turned its guide off. */
export function guideChannelOf(fixes: GuideFixes, channelId: string): string | null {
  const source = fixes.sources[channelId];
  if (!source) return channelId;
  return source === GUIDE_SOURCE_NONE ? null : source;
}

export function hasGuideFixes(fixes: GuideFixes) {
  return Object.keys(fixes.sources).length > 0 || Object.keys(fixes.offsets).length > 0;
}

function rebase(programs: IptvProgram[], now: number): IptvNowNext {
  const sorted = programs.slice().sort((a, b) => a.startUtcMillis - b.startUtcMillis);
  const future = sorted.filter((program) => program.startUtcMillis > now);
  return {
    now: sorted.filter((program) => now >= program.startUtcMillis && now < program.endUtcMillis).pop(),
    next: future[0],
    later: future[1],
    upcoming: future,
    recent: sorted.filter((program) => program.endUtcMillis <= now)
  };
}

/** Moves every programme by `offsetMs`, then works out now/next again. */
export function shiftGuide(entry: IptvNowNext, offsetMs: number, now = Date.now()): IptvNowNext {
  if (!offsetMs) return entry;
  const seen = new Set<string>();
  const programs: IptvProgram[] = [];
  for (const program of [...(entry.recent ?? []), entry.now, entry.next, entry.later, ...(entry.upcoming ?? [])]) {
    if (!program) continue;
    const key = `${program.startUtcMillis}|${program.title}`;
    if (seen.has(key)) continue;
    seen.add(key);
    programs.push({ ...program, startUtcMillis: program.startUtcMillis + offsetMs, endUtcMillis: program.endUtcMillis + offsetMs });
  }
  return rebase(programs, now);
}

/** The guide `channelId` should show, read from the loaded guide by guide channel id. */
export function resolveGuide(fixes: GuideFixes, channelId: string, guide: Record<string, IptvNowNext>, now = Date.now()): IptvNowNext | undefined {
  const guideId = guideChannelOf(fixes, channelId);
  if (!guideId) return undefined;
  const entry = guide[guideId];
  if (!entry) return undefined;
  const offset = fixes.offsets[guidePlaylistIdOf(guideId)] ?? 0;
  return shiftGuide(entry, offset * 60_000, now);
}

/** The whole guide map as the channels show it. Returns the same object when nothing is fixed. */
export function applyGuideFixes(fixes: GuideFixes, guide: Record<string, IptvNowNext>, now = Date.now()): Record<string, IptvNowNext> {
  if (!hasGuideFixes(fixes)) return guide;
  const out: Record<string, IptvNowNext> = { ...guide };
  for (const id of Object.keys(fixes.sources)) delete out[id];
  const offsetPlaylists = new Set(Object.keys(fixes.offsets));
  if (offsetPlaylists.size) {
    for (const id of Object.keys(guide)) {
      if (id in fixes.sources || !offsetPlaylists.has(guidePlaylistIdOf(id))) continue;
      out[id] = resolveGuide(fixes, id, guide, now)!;
    }
  }
  for (const id of Object.keys(fixes.sources)) {
    const resolved = resolveGuide(fixes, id, guide, now);
    if (resolved) out[id] = resolved;
  }
  return out;
}

/** "+1:30" / "−0:30" / "0:00". */
export function formatGuideOffset(minutes: number) {
  const sign = minutes > 0 ? "+" : minutes < 0 ? "−" : "";
  const abs = Math.abs(minutes);
  return `${sign}${Math.floor(abs / 60)}:${String(abs % 60).padStart(2, "0")}`;
}

const QUALITY_WORDS = new Set(["hd", "fhd", "uhd", "sd", "4k", "8k", "hq", "lq", "hevc", "h265", "h264", "x265", "1080p", "720p",
  "2160p", "1080", "720", "50fps", "60fps", "fps", "raw", "backup", "alt", "vip", "plus", "tv", "channel"]);
const TAG = /^\s*(\|[^|]{1,12}\||\[[^\]]{1,12}]|\([^)]{1,12}\)|[a-z]{2,4}\s*[:|]|[a-z]{2,3}\s+-)\s*/;

/** Same rules as the Android picker: lowercase words without tags, quality labels or punctuation. */
export function channelNameWords(name: string): string[] {
  let text = name.normalize("NFD").replace(/\p{Mn}+/gu, "").toLowerCase();
  for (let i = 0; i < 2; i++) text = text.replace(TAG, "");
  text = text.replace(/[[(][^\])]{0,24}[\])]/g, " ");
  return text.split(/[^a-z0-9]+/).filter((word) => word && !QUALITY_WORDS.has(word));
}

export function channelNameScore(a: string[], b: string[]): number {
  if (!a.length || !b.length) return 0;
  const ka = a.join("");
  const kb = b.join("");
  if (ka === kb) return 100;
  const numbersA = new Set(a.filter((word) => /^\d+$/.test(word)));
  const numbersB = new Set(b.filter((word) => /^\d+$/.test(word)));
  if (numbersA.size && numbersB.size && ![...numbersA].some((n) => numbersB.has(n))) return 0;
  if (kb.startsWith(ka) || ka.startsWith(kb)) return 80;
  const setA = new Set(a);
  const setB = new Set(b);
  const shared = [...setA].filter((word) => setB.has(word)).length;
  if (!shared) return 0;
  return Math.max(1, Math.floor(60 * shared / new Set([...setA, ...setB]).size));
}

export type PreparedGuideCandidate<T> = { item: T; name: string; lower: string; words: string[]; key: string };

export function prepareGuideCandidates<T>(items: T[], nameOf: (item: T) => string): PreparedGuideCandidate<T>[] {
  return items.map((item) => {
    const name = nameOf(item);
    const words = channelNameWords(name);
    return { item, name, lower: name.toLowerCase(), words, key: words.join("") };
  });
}

/** Best name matches for `targetName`, or every name containing all words of `query`. */
export function rankGuideCandidates<T>(targetName: string, query: string, candidates: PreparedGuideCandidate<T>[], hasGuide: (item: T) => boolean, limit = 40): T[] {
  const target = channelNameWords(targetName);
  const queryWords = channelNameWords(query);
  const words = queryWords.length ? queryWords : query.trim().toLowerCase().split(/[^a-z0-9]+/).filter(Boolean);
  const scored: Array<{ candidate: PreparedGuideCandidate<T>; score: number }> = [];
  for (const candidate of candidates) {
    const similarity = channelNameScore(target, candidate.words);
    const matches = words.length ? words.every((word) => candidate.lower.includes(word) || candidate.key.includes(word)) : similarity >= 30;
    if (matches) scored.push({ candidate, score: similarity * 2 + (hasGuide(candidate.item) ? 1 : 0) });
  }
  return scored.sort((a, b) => b.score - a.score || a.candidate.name.length - b.candidate.name.length).slice(0, limit).map((entry) => entry.candidate.item);
}

// Skip intro / recap / credits, matching the Android app's SkipIntroRepository:
//   1. IntroDB (https://api.introdb.app/segments?imdb_id&season&episode) for shows;
//   2. otherwise AniSkip (https://api.aniskip.com/v2/skip-times/{malId}/{episode})
//      after resolving IMDb → MyAnimeList through ARM (arm.haglund.dev).
// IntroDB only allows its own origin (CORS), so it goes through the app's
// /api/proxy route; AniSkip and ARM answer browsers directly.
// The button shows 1.2 s before a segment and hides 0.5 s before its end
// (PlayerViewModel.updateActiveSkipInterval).

export type SkipSegment = {
  startMs: number;
  endMs: number;
  /** "intro" | "recap" | "outro" (IntroDB) or "op" | "ed" | "recap" | "mixed-op" | "mixed-ed" (AniSkip). */
  type: string;
  provider: "introdb" | "aniskip";
};

const SHOW_EARLY_MS = 1_200;
const END_GUARD_MS = 500;
const MIN_VISIBLE_MS = 250;

type IntroDbSegment = { start_ms?: number; end_ms?: number; start_sec?: number | null; end_sec?: number | null } | null | undefined;

export function parseIntroDb(body: unknown): SkipSegment[] {
  const data = (body ?? {}) as Record<string, IntroDbSegment>;
  const out: SkipSegment[] = [];
  for (const type of ["recap", "intro", "outro"] as const) {
    const segment = data[type];
    if (!segment) continue;
    const startMs = (segment.start_ms ?? 0) > 0 ? Number(segment.start_ms) : Math.round((segment.start_sec ?? 0) * 1000);
    const endMs = (segment.end_ms ?? 0) > 0 ? Number(segment.end_ms) : Math.round((segment.end_sec ?? 0) * 1000);
    if (Number.isFinite(startMs) && Number.isFinite(endMs) && endMs > startMs && startMs >= 0) out.push({ startMs, endMs, type, provider: "introdb" });
  }
  return out.sort((a, b) => a.startMs - b.startMs);
}

export function parseAniSkip(body: unknown): SkipSegment[] {
  const data = (body ?? {}) as { found?: boolean; results?: Array<{ interval?: { startTime?: number; endTime?: number }; skipType?: string }> };
  if (!data.found) return [];
  return (data.results ?? []).flatMap((result) => {
    const startMs = Math.round((result.interval?.startTime ?? 0) * 1000);
    const endMs = Math.round((result.interval?.endTime ?? 0) * 1000);
    return endMs > startMs && result.skipType ? [{ startMs, endMs, type: result.skipType, provider: "aniskip" as const }] : [];
  }).sort((a, b) => a.startMs - b.startMs);
}

export type SkipFetchJson = (url: string, signal?: AbortSignal) => Promise<unknown>;

const cache = new Map<string, SkipSegment[]>();
const malIds = new Map<string, string | null>();

export async function fetchSkipSegments(
  target: { imdbId: string | null | undefined; season: number | null | undefined; episode: number | null | undefined },
  options: { fetchJson: SkipFetchJson; introDbUrl: (url: string) => string; signal?: AbortSignal }
): Promise<SkipSegment[]> {
  const { imdbId, season, episode } = target;
  if (!imdbId || !/^tt\d+$/.test(imdbId) || season == null || episode == null) return [];
  const key = `${imdbId}:${season}:${episode}`;
  const hit = cache.get(key);
  if (hit) return hit;
  const introDb = await options.fetchJson(options.introDbUrl(`https://api.introdb.app/segments?imdb_id=${encodeURIComponent(imdbId)}&season=${season}&episode=${episode}`), options.signal)
    .then(parseIntroDb).catch(() => [] as SkipSegment[]);
  if (introDb.length) { cache.set(key, introDb); return introDb; }
  let malId = malIds.get(imdbId);
  if (malId === undefined) {
    malId = await options.fetchJson(`https://arm.haglund.dev/api/v2/imdb?id=${encodeURIComponent(imdbId)}&include=myanimelist`, options.signal)
      .then((rows) => {
        const first = Array.isArray(rows) ? rows[0] as { myanimelist?: number | null } | undefined : undefined;
        return first?.myanimelist ? String(first.myanimelist) : null;
      }).catch(() => null);
    if (!options.signal?.aborted) malIds.set(imdbId, malId);
  }
  if (malId) {
    const types = ["op", "ed", "recap", "mixed-op", "mixed-ed"].map((type) => `types=${type}`).join("&");
    const aniSkip = await options.fetchJson(`https://api.aniskip.com/v2/skip-times/${malId}/${episode}?${types}&episodeLength=0`, options.signal)
      .then(parseAniSkip).catch(() => [] as SkipSegment[]);
    if (aniSkip.length) { cache.set(key, aniSkip); return aniSkip; }
  }
  if (!options.signal?.aborted) cache.set(key, []);
  return [];
}

/** The segment whose skip button should show at this position, if any. */
export function activeSkipSegment(segments: SkipSegment[], positionMs: number) {
  return segments.find((segment) => {
    const effectiveEnd = Math.max(segment.endMs - END_GUARD_MS, segment.startMs + MIN_VISIBLE_MS);
    return positionMs >= segment.startMs - SHOW_EARLY_MS && positionMs < effectiveEnd;
  }) ?? null;
}

export function isCreditsSegment(segment: Pick<SkipSegment, "type">) {
  return /^(outro|ed|mixed-ed)$/i.test(segment.type);
}

export function skipSegmentLabel(segment: Pick<SkipSegment, "type">) {
  if (isCreditsSegment(segment)) return "Skip credits";
  if (/recap/i.test(segment.type)) return "Skip recap";
  return "Skip intro";
}

/**
 * When the "Up next" card appears: at the credits when known (an outro
 * segment in the second half), else 40 s before the end of anything ten
 * minutes or longer, else at 95 %.
 */
export function upNextStartTime(durationSeconds: number, segments: SkipSegment[] = []) {
  if (!Number.isFinite(durationSeconds) || durationSeconds <= 0) return Infinity;
  const credits = segments.find((segment) => isCreditsSegment(segment)
    && segment.startMs / 1000 > durationSeconds * 0.5 && segment.startMs / 1000 < durationSeconds - 5);
  if (credits) return credits.startMs / 1000;
  return durationSeconds >= 600 ? durationSeconds - 40 : durationSeconds * 0.95;
}

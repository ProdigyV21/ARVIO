import { cachedDebridDirectUrl, parseDebridStream } from "./debrid";
import { browserPlaysDolbyAudio, canTryRemux, playbackPlan, streamTransport } from "./streamCompatibility";
import { cachedVerdict, verifyStream } from "./streamVerify";
import { recordSourceOutcome } from "./sourceReliability";
import type { StreamSource } from "./types";

type ProbeGlobals = { VideoDecoder?: unknown; AudioDecoder?: unknown };

/**
 * The URL a pre-play probe may read for this candidate, or null when probing
 * it would be pointless or have side effects:
 *
 * - Only sources the plan will DIRECT-play. Remux/transcode paths read the
 *   container themselves, and the verdict (WebCodecs decoders, without the
 *   app's AC-3/DTS/AAC fallbacks registered) would wrongly reject them.
 * - Unresolved debrid links are skipped: resolving one creates a provider-side
 *   download (TorBox/RD), which browsing and probing must never do. A link the
 *   user already resolved this session is probed through its cached CDN URL.
 * - Sources that need custom request headers or are not plain files (HLS,
 *   IPTV, home-server sessions) are skipped; mediabunny cannot represent them.
 */
export function probeUrlFor(stream: StreamSource): string | null {
  if (!stream.url || stream.homeServer || stream.remux || stream.transcoded) return null;
  if (stream.behaviorHints?.proxyHeaders?.request && Object.keys(stream.behaviorHints.proxyHeaders.request).length) return null;
  if (streamTransport(stream) !== "file") return null;
  if (playbackPlan(stream).method !== "direct") return null;
  if (parseDebridStream(stream.originalUrl ?? stream.url)) return cachedDebridDirectUrl(stream.originalUrl ?? stream.url);
  return /^https?:\/\//i.test(stream.url) ? stream.url : null;
}

/**
 * True when the probe showed this file's video plays here but none of its
 * audio tracks does, on a browser whose <video> cannot play Dolby audio
 * natively either (Chromium): played directly it would be silent. The remux
 * path converts that audio in the browser, so start there instead.
 */
export function needsAudioConversion(stream: StreamSource): boolean {
  const url = probeUrlFor(stream);
  const verdict = url ? cachedVerdict(url) : undefined;
  if (!verdict || verdict.playable || !verdict.videoOk || verdict.audioOk || !verdict.audioTrackCount) return false;
  return !browserPlaysDolbyAudio() && canTryRemux(stream);
}

/** Probing is only meaningful where the browser exposes WebCodecs decoders. */
/**
 * The URL a reachability check may read, for candidates the decoder probe
 * skips (remux and unprobeable direct files). Same safety rules as
 * probeUrlFor: never an unresolved debrid link, never custom headers.
 */
export function reachabilityUrlFor(stream: StreamSource): string | null {
  if (!stream.url || stream.homeServer || stream.transcoded) return null;
  if (stream.behaviorHints?.proxyHeaders?.request && Object.keys(stream.behaviorHints.proxyHeaders.request).length) return null;
  if (streamTransport(stream) !== "file") return null;
  if (parseDebridStream(stream.originalUrl ?? stream.url)) return cachedDebridDirectUrl(stream.originalUrl ?? stream.url);
  return /^https?:\/\//i.test(stream.url) ? stream.url : null;
}

// Relays answer a dead usenet/file link by redirecting to a short clip
// ("download_failed.mp4") that would play as if it were the film.
const DEAD_TARGET = /download[_-]?failed|\/(?:error|failed|unavailable|expired)\.(?:mp4|mkv|webm)(?:$|\?)/i;
const MIN_FILE_BYTES = 10 * 1024 * 1024;

/**
 * One byte of the file: false when the link is dead (error status, a
 * "download failed" redirect, a tiny placeholder) or, for sources the browser
 * must repackage, when the browser may not read it at all (CORS); otherwise
 * null (reachable, or unknown). Never true: reaching a file says nothing about
 * its codecs.
 */
export async function checkReachable(stream: StreamSource, url: string, signal: AbortSignal, fetcher: typeof fetch = fetch): Promise<false | null> {
  try {
    const response = await fetcher(url, { headers: { Range: "bytes=0-0" }, signal, credentials: "omit", redirect: "follow" });
    void response.body?.cancel().catch(() => undefined);
    const total = Number(response.headers.get("content-range")?.match(/\/(\d+)\s*$/)?.[1] ?? NaN);
    if (response.status >= 400 || DEAD_TARGET.test(response.url || "") || (Number.isFinite(total) && total < MIN_FILE_BYTES)) return false;
    return null;
  } catch (error) {
    if (signal.aborted) return null;
    // <video> plays a direct file without CORS; repackaging has to read it.
    return playbackPlan(stream).method === "remux" ? false : null;
  }
}

export function canProbeDecoders(scope: ProbeGlobals = globalThis as ProbeGlobals) {
  return typeof scope.VideoDecoder === "function" && typeof scope.AudioDecoder === "function";
}

/** Map a probe verdict to the autoplay ranking signal (null = inconclusive). */
function rankingVerdict(verdict: { playable: boolean; videoOk: boolean; audioOk: boolean } | null | undefined): boolean | null {
  if (!verdict) return null;
  // WebCodecs audio support is narrower than <video>'s (Safari plays AC-3 /
  // E-AC-3 natively but its AudioDecoder may not report them), so an
  // audio-only failure is inconclusive for ranking; only video counts.
  if (!verdict.playable && verdict.videoOk && !verdict.audioOk) return null;
  return verdict.playable;
}

/**
 * Verify the leading autoplay candidates with the streamVerify probe, inside a
 * hard time budget. Returns true/false per candidate when the probe was
 * conclusive, null otherwise.
 *
 * `onVerdict` reports each candidate the moment its verdict is known
 * (unprobeable candidates immediately, as null), so the chooser can start the
 * best-ranked source as soon as IT passes instead of waiting for the slowest
 * probe or the whole budget.
 */
export async function verifyAutoplayCandidates(
  candidates: StreamSource[],
  budgetMs: number,
  signal: AbortSignal,
  onVerdict?: (stream: StreamSource, verdict: boolean | null) => void
) {
  const verdicts = new Map<StreamSource, boolean | null>();
  for (const stream of candidates) verdicts.set(stream, null);
  const report = (stream: StreamSource, verdict: boolean | null) => {
    verdicts.set(stream, verdict);
    onVerdict?.(stream, verdict);
  };
  const probes: Promise<void>[] = [];
  const decoders = canProbeDecoders();
  for (const stream of candidates) {
    const url = decoders ? probeUrlFor(stream) : null;
    if (!url) {
      // No decoder probe: at least make sure the link is alive, so a dead one
      // is skipped here instead of costing a 3-6 s hop in the player.
      const reachable = reachabilityUrlFor(stream);
      if (!reachable) { report(stream, null); continue; }
      probes.push(checkReachable(stream, reachable, signal)
        .then((verdict) => {
          if (signal.aborted) return;
          if (verdict === false) recordSourceOutcome(stream, false);
          report(stream, verdict);
        })
        .catch(() => { if (!signal.aborted) report(stream, null); }));
      continue;
    }
    const known = cachedVerdict(url);
    if (known) { report(stream, rankingVerdict(known)); continue; }
    probes.push(verifyStream(url, undefined, signal)
      .then((verdict) => { if (!signal.aborted) report(stream, rankingVerdict(verdict)); })
      .catch(() => { if (!signal.aborted) report(stream, null); }));
  }
  if (!probes.length) return verdicts;
  let timer: ReturnType<typeof setTimeout> | undefined;
  await Promise.race([
    Promise.all(probes),
    new Promise<void>((resolve) => { timer = setTimeout(resolve, budgetMs); })
  ]);
  if (timer) clearTimeout(timer);
  return verdicts;
}

import { cachedDebridDirectUrl, parseDebridStream } from "./debrid";
import { browserPlaysDolbyAudio, canTryRemux, playbackPlan, streamTransport } from "./streamCompatibility";
import { cachedVerdict, verifyStream } from "./streamVerify";
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
  if (!canProbeDecoders()) {
    for (const stream of candidates) report(stream, null);
    return verdicts;
  }
  const probes: Promise<void>[] = [];
  for (const stream of candidates) {
    const url = probeUrlFor(stream);
    if (!url) { report(stream, null); continue; }
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

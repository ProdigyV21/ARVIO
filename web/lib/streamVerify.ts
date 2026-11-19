/**
 * Truth-based playability verification.
 *
 * The badge on a source row is inferred from its release NAME — codec, audio
 * and container guessed from text. That guess is wrong often enough that users
 * hit "Plays here" sources that never play, and each failure mode has needed
 * its own hand-written rule (TrueHD companions, Dolby Vision base layers,
 * single-track DDP web-dls). Adding rules per report does not scale.
 *
 * This module answers the same question by READING the file: mediabunny pulls
 * the container header over a couple of range requests and the browser itself
 * says whether it can decode each track. No release-name heuristics.
 *
 * It is deliberately separate from `streamCompatibility`, which stays the fast
 * synchronous guess used to order and label the list on first paint. Verified
 * results refine that list once they arrive.
 */

export type VerifiedVerdict = {
  /** Can this browser actually play it, with the audio the remux would pick? */
  playable: boolean;
  /** Video decodes here. */
  videoOk: boolean;
  /** At least one audio track decodes here. */
  audioOk: boolean;
  /** Total audio tracks found — 1 means a remux has nothing to fall back to. */
  audioTrackCount: number;
  /** Short reason when not playable, for the row's secondary line. */
  reason: string;
};

/** Verified results keyed by URL, so a source is probed at most once. */
const cache = new Map<string, VerifiedVerdict>();
const inFlight = new Map<string, Promise<VerifiedVerdict | null>>();

export function cachedVerdict(url: string): VerifiedVerdict | undefined {
  return cache.get(url);
}

/**
 * Probe a source and cache the verdict.
 *
 * Returns null when the probe could not complete (dead link, CORS, timeout) —
 * the caller should keep the name-based guess in that case rather than
 * downgrade a source on inconclusive evidence.
 */
export async function verifyStream(
  url: string,
  requestHeaders?: Record<string, string>,
  signal?: AbortSignal
): Promise<VerifiedVerdict | null> {
  const hit = cache.get(url);
  if (hit) return hit;
  if (signal?.aborted) return null;
  const running = inFlight.get(url);
  if (running) return running;

  const task = (async (): Promise<VerifiedVerdict | null> => {
    let disposeInput: (() => void) | undefined;
    // An aborted probe (autoplay ran out of its time budget) must release its
    // range connections at once: the same URL may be starting playback, and
    // debrid CDNs cap connections per link.
    const onAbort = () => disposeInput?.();
    signal?.addEventListener("abort", onAbort, { once: true });
    try {
      const { Input, UrlSource, ALL_FORMATS } = await import("mediabunny");
      if (signal?.aborted) return null;
      const input = new Input({
        formats: ALL_FORMATS,
        source: new UrlSource(url, requestHeaders ? { requestInit: { headers: requestHeaders } } : undefined),
      });
      disposeInput = () => { try { input.dispose?.(); } catch { /* already disposed */ } };
      // Bounded: a throttled or dead host must not leave this pending forever.
      const limit = <T,>(p: Promise<T>, ms: number): Promise<T | null> =>
        Promise.race([p.catch(() => null), new Promise<null>((r) => setTimeout(() => r(null), ms))]);

      const videoTrack = await limit(input.getPrimaryVideoTrack(), 9000);
      if (!videoTrack) { input.dispose?.(); return null; }
      const videoOk = (await limit(videoTrack.canDecode(), 6000)) ?? false;
      const audioTracks = (await limit(input.getAudioTracks(), 6000)) ?? [];
      const audioFlags = await Promise.all(
        audioTracks.map((t) => limit(t.canDecode(), 5000).then((ok) => ok === true))
      );
      input.dispose?.();
      // Inconclusive, never cached: a cancelled probe proves nothing.
      if (signal?.aborted) return null;

      const audioOk = audioFlags.some(Boolean);
      const verdict: VerifiedVerdict = {
        playable: videoOk && audioOk,
        videoOk,
        audioOk,
        audioTrackCount: audioTracks.length,
        reason: !videoOk
          ? "This browser can't decode the video"
          : !audioOk
            ? (audioTracks.length <= 1
                ? "This browser can't decode its only audio track"
                : "No audio track this browser can decode")
            : "",
      };
      cache.set(url, verdict);
      return verdict;
    } catch {
      return null;
    } finally {
      signal?.removeEventListener("abort", onAbort);
      inFlight.delete(url);
    }
  })();

  inFlight.set(url, task);
  return task;
}

/**
 * Verify several sources with a small concurrency cap.
 *
 * Used to check the handful of rows a user can actually see rather than the
 * whole list — probing 267 sources would be pointless traffic.
 */
export async function verifyTopSources(
  streams: Array<{ url?: string | null; headers?: Record<string, string> }>,
  limitCount = 6,
  concurrency = 3,
  signal?: AbortSignal
): Promise<void> {
  const targets = streams.filter((s) => s.url && !cache.has(s.url)).slice(0, limitCount);
  let cursor = 0;
  const workers = Array.from({ length: Math.min(concurrency, targets.length) }, async () => {
    while (cursor < targets.length && !signal?.aborted) {
      const item = targets[cursor++];
      if (item?.url) await verifyStream(item.url, item.headers, signal);
    }
  });
  await Promise.all(workers);
}

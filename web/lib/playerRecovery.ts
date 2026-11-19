/**
 * Mid-playback stall recovery.
 *
 * The player used to have no recovery once playback had started: the two
 * startup watchdogs only check `readyState` and stop caring the moment the
 * first frame arrives, and the "connection can't keep up" handler needed three
 * stalls inside 90s AND a strictly smaller alternative source before it did
 * anything. With one source, an equal-sized source, or a source whose size is
 * unknown — all common — it returned silently and the video buffered forever
 * with no message and no way to continue.
 *
 * The rules live here rather than inline in the overlay so the escalation order
 * can be unit tested instead of only reproduced by stalling a real stream.
 */

/** How long a stall may last before each step of the ladder is attempted. */
export const NUDGE_AFTER_MS = 6_000;
export const RELOAD_AFTER_MS = 14_000;
export const ESCALATE_AFTER_MS = 24_000;

export type StallAction =
  /** Still inside the grace window — keep waiting. */
  | { kind: "wait" }
  /** Seek a hair forward: unwedges a decoder parked on a bad sample. */
  | { kind: "nudge"; seekTo: number }
  /** Re-attach the same source and resume at the saved position. */
  | { kind: "reload"; resumeAt: number }
  /** Give up on this URL and move down the source ladder. */
  | { kind: "escalate"; resumeAt: number };

export interface StallState {
  /** ms the video has been stalled (not advancing while trying to play). */
  stalledForMs: number;
  /** Playback position to resume from. */
  currentTime: number;
  /** Steps already taken for this stall, so each runs at most once. */
  nudged: boolean;
  reloaded: boolean;
}

/**
 * Next recovery step for a stall.
 *
 * Escalating rather than repeating matters: a nudge fixes a wedged decoder, a
 * reload fixes a dead connection or an expired debrid link, and only after both
 * fail is the source itself likely at fault. Each step is attempted once per
 * stall so a persistent stall walks the ladder instead of looping on the
 * cheapest fix.
 */
export function nextStallAction(state: StallState): StallAction {
  const { stalledForMs, currentTime, nudged, reloaded } = state;

  if (!nudged && stalledForMs >= NUDGE_AFTER_MS) {
    // Forward, never backward: seeking back can re-enter the same bad region.
    return { kind: "nudge", seekTo: currentTime + 0.35 };
  }
  if (!reloaded && stalledForMs >= RELOAD_AFTER_MS) {
    return { kind: "reload", resumeAt: currentTime };
  }
  if (stalledForMs >= ESCALATE_AFTER_MS) {
    return { kind: "escalate", resumeAt: currentTime };
  }
  return { kind: "wait" };
}

/** Observe real playback progress without counting our own recovery seeks as success. */
export function monitorPlaybackStall(video: HTMLVideoElement, options: {
  reload: (position: number) => void;
  live?: boolean;
  /** Live streams can return to the live edge instead of seeking past it. */
  nudge?: (position: number) => void;
  onRecover: () => void;
  onFailure: () => void;
}): () => void {
  let lastPosition = video.currentTime;
  let elapsed = 0;
  let nudged = false;
  let reloaded = false;
  let internalSeek = false;
  let awaitingReload = false;
  let reloadPosition = 0;
  let finished = false;
  const reset = () => { elapsed = 0; nudged = false; reloaded = false; awaitingReload = false; };
  const seeked = () => {
    lastPosition = video.currentTime;
    if (internalSeek || awaitingReload) { internalSeek = false; return; }
    reset();
  };
  video.addEventListener("seeked", seeked);
  const timer = setInterval(() => {
    if (finished) return;
    if (document.visibilityState === "hidden" || video.ended) { reset(); lastPosition = video.currentTime; return; }
    // load() pauses and clears the element before metadata arrives. That is not
    // a user pause and must not restart the recovery budget indefinitely.
    // A new live manifest may start a fresh timestamp window. Only VOD is
    // expected to restore the old position; live recovery proves itself by
    // advancing from the new timeline on subsequent samples.
    if (awaitingReload && video.readyState >= 2 && (video.paused || options.live || video.currentTime >= reloadPosition)) {
      awaitingReload = false;
      lastPosition = video.currentTime;
    }
    if (!awaitingReload && (video.paused || (video.seeking && !internalSeek))) {
      reset(); lastPosition = video.currentTime; return;
    }
    if (!awaitingReload && !internalSeek && video.currentTime > lastPosition + 0.05) {
      reset(); lastPosition = video.currentTime; return;
    }
    if (internalSeek && !video.seeking) { internalSeek = false; lastPosition = video.currentTime; }
    elapsed += 1000;
    const action = nextStallAction({ stalledForMs: elapsed, currentTime: awaitingReload ? reloadPosition : video.currentTime, nudged, reloaded });
    if (action.kind === "wait") return;
    if (!nudged) options.onRecover();
    if (action.kind === "nudge") {
      nudged = true;
      internalSeek = true;
      try {
        if (options.nudge) options.nudge(action.seekTo);
        else video.currentTime = action.seekTo;
      } catch { internalSeek = false; }
      lastPosition = video.currentTime;
      void video.play().catch(() => undefined);
    } else if (action.kind === "reload") {
      reloaded = true;
      awaitingReload = true;
      reloadPosition = action.resumeAt;
      options.reload(action.resumeAt);
    } else {
      finished = true;
      options.onFailure();
    }
  }, 1000);
  return () => { finished = true; clearInterval(timer); video.removeEventListener("seeked", seeked); };
}

export type PlaybackFailureKind = "network" | "format" | "timeout" | "browser_restriction" | "engine" | "unknown";

/** Low-cardinality diagnostics only: never send source URLs, credentials or titles. */
export function playbackFailureKind(fault?: { kind?: string; code?: string | number; message?: string }): PlaybackFailureKind {
  if (!fault) return "unknown";
  if (fault.code === "STARTUP_TIMEOUT" || fault.code === "PLAYBACK_STALLED") return "timeout";
  if (fault.code === "NATIVE_HEADERS_UNSUPPORTED") return "browser_restriction";
  if (fault.code === "ENGINE_LOAD_FAILED") return "engine";
  if (fault.kind === "network") return "network";
  if (fault.kind === "media" || fault.kind === "unsupported") return "format";
  // Worker failures have messages rather than MediaError codes. Keep the
  // original message local and only classify into the fixed categories above.
  if (/timeout|timed out|stopped delivering|stopped responding/i.test(fault.message ?? "")) return "timeout";
  if (/fetch|network|cors|connection|http/i.test(fault.message ?? "")) return "network";
  if (/codec|decode|format|track|profile/i.test(fault.message ?? "")) return "format";
  return "unknown";
}

/**
 * Whether playback counts as stalled.
 *
 * `waiting` alone is not enough — it also fires for ordinary rebuffering that
 * recovers on its own, and for a deliberate seek. A stall is "we want to be
 * playing, and the clock has not moved".
 */
export function isStalled(opts: {
  paused: boolean;
  seeking: boolean;
  ended: boolean;
  currentTime: number;
  lastProgressTime: number;
}): boolean {
  const { paused, seeking, ended, currentTime, lastProgressTime } = opts;
  if (paused || seeking || ended) return false;
  return currentTime <= lastProgressTime;
}

/** Detect audio advancing without video, including streams with known dimensions. */
/**
 * True for an audio file (a podcast episode's .mp3): it never presents video frames, so the
 * frame watchdog would report a playing episode as an undecodable video.
 */
export function isAudioOnlySource(url: string | undefined | null) {
  if (!url) return false;
  try {
    return /\.(mp3|m4a|aac|oga|ogg|opus|wav|flac)$/i.test(new URL(url, "http://local").pathname);
  } catch {
    return false;
  }
}

type DecodeCounters = { webkitAudioDecodedByteCount?: number; webkitVideoDecodedByteCount?: number };

/**
 * Chromium plays a file's video while silently dropping an audio track it
 * cannot decode (E-AC-3/DTS/TrueHD in an MKV with no telling extension). Its
 * decode counters show it: video bytes climb, audio stays at 0. Calls
 * `onSilent` after ~5 s of real playback like that. Browsers without the
 * counters, and files that already decoded audio, are left alone.
 */
export function monitorAudioDecode(video: HTMLVideoElement, onSilent: () => void, graceMs = 5000): () => void {
  const counters = video as HTMLVideoElement & DecodeCounters;
  if (typeof counters.webkitAudioDecodedByteCount !== "number" || typeof counters.webkitVideoDecodedByteCount !== "number") return () => {};
  let stopped = false;
  let lastTime = video.currentTime;
  let lastCheck = Date.now();
  let silentPlayingMs = 0;
  const timer = setInterval(() => {
    if (stopped) return;
    if ((counters.webkitAudioDecodedByteCount ?? 0) > 0) { stop(); return; }
    const now = Date.now();
    const advancing = video.currentTime > lastTime;
    const elapsed = Math.min(2000, Math.max(0, now - lastCheck));
    lastCheck = now;
    lastTime = video.currentTime;
    // Muted playback still decodes audio; pauses, seeks and buffering do not count.
    if (document.visibilityState === "hidden" || video.paused || video.seeking || video.ended || !advancing
      || (counters.webkitVideoDecodedByteCount ?? 0) <= 0) return;
    silentPlayingMs += elapsed;
    if (silentPlayingMs >= graceMs) { stop(); onSilent(); }
  }, 1000);
  function stop() { stopped = true; clearInterval(timer); }
  return stop;
}

export function monitorVideoFrames(video: HTMLVideoElement, onMissing: () => void): () => void {
  let stopped = false;
  let frame: number | undefined;
  let timer: ReturnType<typeof setInterval> | undefined;
  let lastTime = video.currentTime;
  let lastCheck = Date.now();
  let framelessPlayingMs = 0;
  let presented = false;
  const stop = () => {
    stopped = true;
    if (timer !== undefined) clearInterval(timer);
    if (frame !== undefined) video.cancelVideoFrameCallback?.(frame);
  };
  const decodedFrame = () => {
    try {
      const quality = video.getVideoPlaybackQuality?.();
      return quality && quality.totalVideoFrames - quality.droppedVideoFrames > 0;
    } catch { return false; }
  };
  if (video.requestVideoFrameCallback) {
    frame = video.requestVideoFrameCallback(() => { presented = true; stop(); });
  }
  timer = setInterval(() => {
    if (stopped) return;
    if (presented || decodedFrame()) { stop(); return; }
    const now = Date.now();
    const advancing = video.currentTime > lastTime;
    const elapsed = Math.min(2000, Math.max(0, now - lastCheck));
    lastCheck = now;
    lastTime = video.currentTime;
    // Background tabs may intentionally stop presenting frames. Pauses, seeks
    // and ordinary buffering must not consume the missing-video grace period.
    if (document.visibilityState === "hidden" || video.paused || video.seeking || video.ended || !advancing) {
      framelessPlayingMs = 0;
      return;
    }
    const hasFrameTelemetry = !!video.requestVideoFrameCallback || !!video.getVideoPlaybackQuality;
    if (!hasFrameTelemetry && video.videoWidth > 0) { stop(); return; }
    framelessPlayingMs += elapsed;
    if (framelessPlayingMs >= 12000) { stop(); onMissing(); }
  }, 1500);
  return stop;
}

/**
 * Total buffered seconds ahead of the playhead.
 *
 * The overlay previously read only `buffered.end(length - 1)` — the end of the
 * LAST range — which misreports badly after seeking backwards, when the range
 * containing the playhead is no longer the last one.
 */
export function bufferedAhead(ranges: TimeRanges | null, currentTime: number): number {
  if (!ranges) return 0;
  for (let i = 0; i < ranges.length; i += 1) {
    if (currentTime >= ranges.start(i) && currentTime <= ranges.end(i)) {
      return Math.max(0, ranges.end(i) - currentTime);
    }
  }
  return 0;
}

/**
 * What a MediaError means for the source.
 *
 * The player used to treat every failure identically, so a momentary network
 * drop was punished exactly like an undecodable codec: walk the ladder, declare
 * the source unplayable, hop away. They need opposite responses — a network
 * fault is worth retrying on the same source, a decode fault never is.
 *
 * Codes are the MediaError constants (1 aborted, 2 network, 3 decode,
 * 4 src-not-supported).
 */
export type MediaFaultKind = "retryable" | "fatal";

export function classifyMediaError(code: number | null | undefined): MediaFaultKind {
  // DECODE (3) and SRC_NOT_SUPPORTED (4) mean this browser genuinely cannot
  // play these bytes; retrying the same URL will fail the same way.
  if (code === 3 || code === 4) return "fatal";
  // Missing/unknown codes, NETWORK (2), and ABORTED (1) are not evidence of an
  // unsupported codec. Adaptive engines often fail without setting video.error.
  return "retryable";
}

/** End of the buffered range holding the playhead, for the scrubber's buffer bar. */
export function bufferedEndAt(ranges: TimeRanges | null, currentTime: number): number {
  if (!ranges) return 0;
  for (let i = 0; i < ranges.length; i += 1) {
    if (currentTime >= ranges.start(i) && currentTime <= ranges.end(i)) {
      return ranges.end(i);
    }
  }
  return 0;
}

/**
 * What an HTTP status from the media host means for the viewer. A <video>
 * element reports every refused request as "format not supported" (code 4),
 * which used to send people hunting for a codec problem when the host was
 * simply down (503) or the link had expired (403/410).
 */
export function describeHttpStatus(status: number): string | null {
  if (status === 401 || status === 403) return `The host refused this link (HTTP ${status}). It may have expired; pick the source again or choose another one.`;
  if (status === 404 || status === 410) return `The file is no longer on the host (HTTP ${status}). Choose another source.`;
  if (status === 429) return "The host is rate-limiting requests (HTTP 429). Wait a moment or choose another source.";
  if (status >= 500) return `The source's server is unavailable (HTTP ${status}). Try again later or choose another source.`;
  if (status >= 400) return `The host rejected the request (HTTP ${status}). Choose another source.`;
  return null;
}

/**
 * After a startup failure, ask the host for one byte to find out why. Returns a
 * readable cause, or null when the answer is unavailable (CORS, network) or
 * the host looks healthy (then the format really is the problem).
 */
export async function diagnoseSourceFailure(url: string | null | undefined, options: { signal?: AbortSignal; fetchImpl?: typeof fetch; timeoutMs?: number } = {}): Promise<string | null> {
  if (!url || !/^https?:\/\//i.test(url)) return null;
  const fetcher = options.fetchImpl ?? (typeof fetch === "function" ? fetch : undefined);
  if (!fetcher) return null;
  const controller = new AbortController();
  const abort = () => controller.abort();
  options.signal?.addEventListener("abort", abort, { once: true });
  const timer = setTimeout(abort, options.timeoutMs ?? 5000);
  try {
    const response = await fetcher(url, { headers: { Range: "bytes=0-0" }, signal: controller.signal, credentials: "omit", cache: "no-store" });
    await response.body?.cancel().catch(() => undefined);
    return describeHttpStatus(response.status);
  } catch {
    return null;
  } finally {
    clearTimeout(timer);
    options.signal?.removeEventListener("abort", abort);
  }
}

// Platform glue for the web player: fullscreen, picture-in-picture, AirPlay,
// lock-screen (Media Session) controls and the iOS home-screen check.
//
// Everything here takes its DOM objects as arguments so the selection logic
// can be tested without a browser, and every capability is feature-detected:
// a button is only offered where the API really exists.

type WebkitVideo = HTMLVideoElement & {
  webkitEnterFullscreen?: () => void;
  webkitExitFullscreen?: () => void;
  webkitSupportsFullscreen?: boolean;
  webkitDisplayingFullscreen?: boolean;
  webkitSupportsPresentationMode?: (mode: string) => boolean;
  webkitSetPresentationMode?: (mode: "inline" | "picture-in-picture" | "fullscreen") => void;
  webkitPresentationMode?: "inline" | "picture-in-picture" | "fullscreen";
  webkitShowPlaybackTargetPicker?: () => void;
};

type WebkitElement = HTMLElement & { webkitRequestFullscreen?: () => Promise<void> | void };

type WebkitDocument = Document & {
  webkitFullscreenElement?: Element | null;
  webkitExitFullscreen?: () => Promise<void> | void;
  webkitFullscreenEnabled?: boolean;
};

/**
 * How this browser can go fullscreen.
 *
 * - "element": the standard Fullscreen API on the player container, so our own
 *   controls, subtitles and panels stay visible (desktop, Android, iPadOS 16.4+).
 * - "webkit-element": the prefixed element API (older iPadOS / desktop Safari).
 * - "video": iPhone Safari has NO element fullscreen at all; only the <video>
 *   itself can enter Apple's native fullscreen player via webkitEnterFullscreen.
 * - "none": nothing available (embedded web views, some kiosk browsers).
 */
export type FullscreenStrategy = "element" | "webkit-element" | "video" | "none";

export function fullscreenStrategy(container: HTMLElement | null, video: HTMLVideoElement | null, doc: Document | undefined = typeof document === "undefined" ? undefined : document): FullscreenStrategy {
  const webkitDoc = doc as WebkitDocument | undefined;
  if (container && typeof container.requestFullscreen === "function" && webkitDoc?.fullscreenEnabled !== false) return "element";
  if (container && typeof (container as WebkitElement).webkitRequestFullscreen === "function" && webkitDoc?.webkitFullscreenEnabled !== false) return "webkit-element";
  const webkitVideo = video as WebkitVideo | null;
  if (webkitVideo && typeof webkitVideo.webkitEnterFullscreen === "function" && webkitVideo.webkitSupportsFullscreen !== false) return "video";
  return "none";
}

export function isFullscreenActive(video: HTMLVideoElement | null, doc: Document | undefined = typeof document === "undefined" ? undefined : document) {
  const webkitDoc = doc as WebkitDocument | undefined;
  return Boolean(webkitDoc?.fullscreenElement || webkitDoc?.webkitFullscreenElement || (video as WebkitVideo | null)?.webkitDisplayingFullscreen);
}

export type FullscreenResult = "entered" | "exited" | "needs-metadata" | "unsupported" | "failed";

/**
 * Toggle fullscreen with the best strategy available. Must be called from the
 * click/tap handler itself: every browser demands transient user activation.
 *
 * iPhone note: in Apple's native fullscreen player only NATIVE text tracks are
 * drawn. ARVIO's subtitles already are native <track> elements (see
 * playerSubtitles.ts), so they carry over, including AI-translated cue text
 * (cues are edited in place). What does NOT carry over is our styling: iOS
 * draws captions with the system caption style (Settings > Accessibility >
 * Subtitles & Captioning), not the size/colour/position chosen in ARVIO, and
 * the viewer can switch tracks off from the native CC menu.
 */
export async function toggleFullscreen(container: HTMLElement | null, video: HTMLVideoElement | null, doc: Document | undefined = typeof document === "undefined" ? undefined : document): Promise<FullscreenResult> {
  const webkitDoc = doc as WebkitDocument | undefined;
  const webkitVideo = video as WebkitVideo | null;
  try {
    if (webkitDoc?.fullscreenElement) { await webkitDoc.exitFullscreen?.(); return "exited"; }
    if (webkitDoc?.webkitFullscreenElement) { await webkitDoc.webkitExitFullscreen?.(); return "exited"; }
    if (webkitVideo?.webkitDisplayingFullscreen) { webkitVideo.webkitExitFullscreen?.(); return "exited"; }
    const strategy = fullscreenStrategy(container, video, doc);
    if (strategy === "element" || strategy === "webkit-element") {
      try {
        if (strategy === "element") await container!.requestFullscreen();
        else await (container as WebkitElement).webkitRequestFullscreen!();
        return "entered";
      } catch (error) {
        // Some WebKit builds expose the element API but refuse it for media
        // containers; Apple's native video fullscreen still works there.
        if (webkitVideo && typeof webkitVideo.webkitEnterFullscreen === "function" && webkitVideo.readyState >= 1) {
          webkitVideo.webkitEnterFullscreen();
          return "entered";
        }
        throw error;
      }
    }
    if (strategy === "video") {
      // WebKit throws InvalidStateError before the element has metadata.
      if ((webkitVideo?.readyState ?? 0) < 1) return "needs-metadata";
      webkitVideo!.webkitEnterFullscreen!();
      return "entered";
    }
    return "unsupported";
  } catch {
    return "failed";
  }
}

/**
 * Phones: turn to landscape while the player is fullscreen, where the browser
 * allows it (Android Chrome, installed web apps). iOS Safari has no
 * orientation lock; its native video player rotates by itself.
 */
export function lockLandscape(scope: { screen?: { orientation?: { lock?: (orientation: string) => Promise<void> } } } = typeof window === "undefined" ? {} : window as unknown as { screen?: { orientation?: { lock?: (orientation: string) => Promise<void> } } }) {
  const lock = scope.screen?.orientation?.lock;
  if (typeof lock !== "function") return false;
  try { void lock.call(scope.screen!.orientation, "landscape").catch(() => undefined); return true; } catch { return false; }
}

export function unlockOrientation(scope: { screen?: { orientation?: { unlock?: () => void } } } = typeof window === "undefined" ? {} : window as unknown as { screen?: { orientation?: { unlock?: () => void } } }) {
  try { scope.screen?.orientation?.unlock?.(); } catch { /* not locked */ }
}

/** Subscribe to every fullscreen signal (standard, prefixed and iOS video). */
export function onFullscreenChange(video: HTMLVideoElement, listener: () => void, doc: Document = document) {
  const events: Array<[EventTarget, string]> = [
    [doc, "fullscreenchange"], [doc, "webkitfullscreenchange"],
    [video, "webkitbeginfullscreen"], [video, "webkitendfullscreen"]
  ];
  for (const [target, name] of events) target.addEventListener(name, listener);
  return () => { for (const [target, name] of events) target.removeEventListener(name, listener); };
}

// ---------- Picture in picture ----------

export type PipStrategy = "standard" | "webkit" | "none";

export function pipStrategy(video: HTMLVideoElement | null, doc: Document | undefined = typeof document === "undefined" ? undefined : document): PipStrategy {
  if (!video) return "none";
  const webkitVideo = video as WebkitVideo;
  if (doc && (doc as Document & { pictureInPictureEnabled?: boolean }).pictureInPictureEnabled && typeof video.requestPictureInPicture === "function" && !video.disablePictureInPicture) return "standard";
  if (typeof webkitVideo.webkitSupportsPresentationMode === "function" && typeof webkitVideo.webkitSetPresentationMode === "function"
    && webkitVideo.webkitSupportsPresentationMode("picture-in-picture")) return "webkit";
  return "none";
}

export function isPipActive(video: HTMLVideoElement | null, doc: Document | undefined = typeof document === "undefined" ? undefined : document) {
  if (!video) return false;
  return Boolean((doc as Document & { pictureInPictureElement?: Element | null } | undefined)?.pictureInPictureElement === video
    || (video as WebkitVideo).webkitPresentationMode === "picture-in-picture");
}

export async function togglePictureInPicture(video: HTMLVideoElement | null, doc: Document | undefined = typeof document === "undefined" ? undefined : document) {
  const strategy = pipStrategy(video, doc);
  try {
    if (strategy === "standard") {
      const pipDoc = doc as Document & { pictureInPictureElement?: Element | null; exitPictureInPicture?: () => Promise<void> };
      if (pipDoc.pictureInPictureElement) await pipDoc.exitPictureInPicture?.();
      else await video!.requestPictureInPicture();
      return true;
    }
    if (strategy === "webkit") {
      const webkitVideo = video as WebkitVideo;
      webkitVideo.webkitSetPresentationMode!(webkitVideo.webkitPresentationMode === "picture-in-picture" ? "inline" : "picture-in-picture");
      return true;
    }
  } catch { /* fall through */ }
  return false;
}

export function onPipChange(video: HTMLVideoElement, listener: () => void) {
  const names = ["enterpictureinpicture", "leavepictureinpicture", "webkitpresentationmodechanged"];
  for (const name of names) video.addEventListener(name, listener);
  return () => { for (const name of names) video.removeEventListener(name, listener); };
}

// ---------- AirPlay ----------

/** Safari exposes this constructor only where AirPlay target picking exists. */
export function airPlaySupported(scope: { WebKitPlaybackTargetAvailabilityEvent?: unknown } = typeof window === "undefined" ? {} : window as unknown as { WebKitPlaybackTargetAvailabilityEvent?: unknown }) {
  return typeof scope.WebKitPlaybackTargetAvailabilityEvent === "function";
}

/**
 * AirPlay can only send what the receiver can fetch itself: a plain file URL
 * or native HLS. Media fed through MediaSource/ManagedMediaSource (hls.js with
 * header relays, the in-browser remux, MPEG-TS, DASH) sets
 * disableRemotePlayback, and the picker would offer nothing that works.
 */
export function airPlayUsable(video: HTMLVideoElement | null) {
  if (!video || video.disableRemotePlayback) return false;
  return typeof (video as WebkitVideo).webkitShowPlaybackTargetPicker === "function";
}

export function watchAirPlayAvailability(video: HTMLVideoElement, listener: (available: boolean) => void) {
  const onAvailability = (event: Event) => listener((event as Event & { availability?: string }).availability === "available");
  video.addEventListener("webkitplaybacktargetavailabilitychanged", onAvailability);
  return () => video.removeEventListener("webkitplaybacktargetavailabilitychanged", onAvailability);
}

export function showAirPlayPicker(video: HTMLVideoElement | null) {
  try { (video as WebkitVideo | null)?.webkitShowPlaybackTargetPicker?.(); return true; } catch { return false; }
}

// ---------- Media Session (lock screen / Control Center / headset keys) ----------

export type MediaSessionDetails = {
  title: string;
  /** Episode line, e.g. "S2 E5 · The Hunt", or the channel/addon name. */
  subtitle?: string | null;
  album?: string | null;
  poster?: string | null;
  backdrop?: string | null;
  still?: string | null;
};

type Artwork = { src: string; sizes?: string; type?: string };

export function mediaSessionArtwork(details: MediaSessionDetails): Artwork[] {
  const result: Artwork[] = [];
  const add = (src: string | null | undefined, sizes: string) => {
    if (!src || !/^https:\/\//i.test(src) || result.some((entry) => entry.src === src)) return;
    result.push({ src, sizes, type: /\.png(?:$|\?)/i.test(src) ? "image/png" : "image/jpeg" });
  };
  // Lock screens crop to a square; the poster crops best, the episode still
  // and backdrop are wide fallbacks.
  add(details.poster, "780x1170");
  add(details.still, "1280x720");
  add(details.backdrop, "1280x720");
  return result;
}

export type MediaSessionHandlers = {
  play: () => void;
  pause: () => void;
  seekBy?: (deltaSeconds: number) => void;
  seekTo?: (seconds: number, fast: boolean) => void;
  next?: (() => void) | null;
  /** Skip interval of the on-screen buttons; the lock screen uses the same. */
  skipSeconds?: number;
};

type MediaSessionLike = {
  metadata: unknown;
  playbackState?: string;
  setActionHandler: (action: string, handler: ((details: { seekOffset?: number; seekTime?: number; fastSeek?: boolean }) => void) | null) => void;
  setPositionState?: (state?: { duration: number; position: number; playbackRate: number }) => void;
};

type MediaSessionScope = { navigator?: { mediaSession?: MediaSessionLike }; MediaMetadata?: new (init: unknown) => unknown };

function sessionScope(scope?: MediaSessionScope): MediaSessionScope {
  if (scope) return scope;
  return typeof window === "undefined" ? {} : window as unknown as MediaSessionScope;
}

const SESSION_ACTIONS = ["play", "pause", "seekbackward", "seekforward", "seekto", "previoustrack", "nexttrack"] as const;

/**
 * Publish now-playing metadata and transport handlers. Returns a cleanup that
 * clears them so a closed player never leaves stale lock-screen controls.
 */
export function bindMediaSession(details: MediaSessionDetails, handlers: MediaSessionHandlers, scopeArg?: MediaSessionScope) {
  const scope = sessionScope(scopeArg);
  const session = scope.navigator?.mediaSession;
  if (!session) return () => {};
  try {
    if (scope.MediaMetadata) {
      session.metadata = new scope.MediaMetadata({
        title: details.title,
        artist: details.subtitle ?? "",
        album: details.album ?? "ARVIO",
        artwork: mediaSessionArtwork(details)
      });
    }
  } catch { /* metadata is cosmetic */ }
  const set = (action: (typeof SESSION_ACTIONS)[number], handler: Parameters<MediaSessionLike["setActionHandler"]>[1]) => {
    // Unsupported actions throw in some browsers; others are silently ignored.
    try { session.setActionHandler(action, handler); } catch { /* unsupported action */ }
  };
  set("play", () => handlers.play());
  set("pause", () => handlers.pause());
  // The lock screen skips by the same interval as the on-screen buttons.
  const skip = handlers.skipSeconds ?? 0;
  set("seekbackward", handlers.seekBy ? (event) => handlers.seekBy!(-(skip || event?.seekOffset || 10)) : null);
  set("seekforward", handlers.seekBy ? (event) => handlers.seekBy!(skip || event?.seekOffset || 10) : null);
  set("seekto", handlers.seekTo ? (event) => { if (typeof event?.seekTime === "number" && Number.isFinite(event.seekTime)) handlers.seekTo!(event.seekTime, Boolean(event.fastSeek)); } : null);
  // No "previous episode" in the web player; leaving it null lets iOS show
  // the seek-back button instead of a dead previous-track button.
  set("previoustrack", null);
  set("nexttrack", handlers.next ? () => handlers.next!() : null);
  return () => {
    for (const action of SESSION_ACTIONS) set(action, null);
    try { session.metadata = null; } catch { /* ignore */ }
    try { session.setPositionState?.(); } catch { /* ignore */ }
    try { session.playbackState = "none"; } catch { /* ignore */ }
  };
}

/** Keep the lock-screen scrubber in sync. Ignores live/unknown durations. */
export function updateMediaPosition(video: Pick<HTMLVideoElement, "duration" | "currentTime" | "playbackRate" | "paused">, scopeArg?: MediaSessionScope) {
  const session = sessionScope(scopeArg).navigator?.mediaSession;
  if (!session) return false;
  try { session.playbackState = video.paused ? "paused" : "playing"; } catch { /* ignore */ }
  if (!session.setPositionState) return false;
  const duration = video.duration;
  if (!Number.isFinite(duration) || duration <= 0) return false;
  const position = Math.min(duration, Math.max(0, Number.isFinite(video.currentTime) ? video.currentTime : 0));
  const playbackRate = video.playbackRate > 0 ? video.playbackRate : 1;
  try { session.setPositionState({ duration, position, playbackRate }); return true; } catch { return false; }
}

// ---------- iOS home screen ----------

export function isIosSafari(nav: { userAgent?: string; maxTouchPoints?: number } | undefined = typeof navigator === "undefined" ? undefined : navigator) {
  if (!nav) return false;
  const ua = nav.userAgent ?? "";
  const ios = /iPhone|iPad|iPod/.test(ua) || (/Macintosh/.test(ua) && (nav.maxTouchPoints ?? 0) > 1);
  // Chrome/Firefox/Edge on iOS (CriOS/FxiOS/EdgiOS) cannot add web apps the
  // same way; in-app browsers (Instagram, FB) cannot at all.
  return ios && /Safari\//.test(ua) && !/CriOS|FxiOS|EdgiOS|OPiOS|GSA\/|FBAN|FBAV|Instagram|Line\//.test(ua);
}

export function isStandaloneDisplay(scope: { navigator?: { standalone?: boolean }; matchMedia?: (query: string) => { matches: boolean } } = typeof window === "undefined" ? {} : window as unknown as { navigator?: { standalone?: boolean }; matchMedia?: (query: string) => { matches: boolean } }) {
  if (scope.navigator?.standalone === true) return true;
  try { return Boolean(scope.matchMedia?.("(display-mode: standalone)").matches || scope.matchMedia?.("(display-mode: fullscreen)").matches); } catch { return false; }
}

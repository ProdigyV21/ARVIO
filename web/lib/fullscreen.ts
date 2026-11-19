// Fullscreen on the <video> element itself.
//
// The player normally fullscreens its CONTAINER (lib/playerPlatform.ts) so
// ARVIO's own controls, styled subtitles and panels stay visible. On Apple
// Vision Pro (and other no-hover, touch-first WebKit devices) fullscreening the
// <video> hands playback to the system player — on visionOS that is the
// docked/immersive cinema view — which is what people expect there.
//
// For the player owner (workstream B): call `prefersVideoElementFullscreen()`
// in the fullscreen click handler and, when true, `requestVideoFullscreen(video)`
// instead of the container request. Must run inside the user gesture.

type WebkitVideo = HTMLVideoElement & {
  webkitEnterFullscreen?: () => void;
  webkitSupportsFullscreen?: boolean;
};

/** No-hover + touch WebKit on an Apple "desktop-class" UA (Vision Pro, iPad). */
export function prefersVideoElementFullscreen(
  root: { classList: { contains(name: string): boolean } } | undefined = typeof document === "undefined" ? undefined : document.documentElement,
  userAgent: string = typeof navigator === "undefined" ? "" : navigator.userAgent
): boolean {
  if (!root?.classList.contains("no-hover")) return false;
  // Vision Pro reports a Macintosh UA (like iPadOS); iPhone already uses the
  // native video path inside playerPlatform.
  return /\bMacintosh\b/.test(userAgent) && /AppleWebKit/.test(userAgent) && !/\b(Chrome|CriOS|Edg|Firefox|FxiOS)\//.test(userAgent);
}

export type VideoFullscreenResult = "entered" | "unsupported" | "needs-metadata" | "failed";

export async function requestVideoFullscreen(video: HTMLVideoElement | null): Promise<VideoFullscreenResult> {
  if (!video) return "unsupported";
  const webkitVideo = video as WebkitVideo;
  try {
    if (typeof video.requestFullscreen === "function") {
      await video.requestFullscreen();
      return "entered";
    }
    if (typeof webkitVideo.webkitEnterFullscreen === "function" && webkitVideo.webkitSupportsFullscreen !== false) {
      if (video.readyState < 1) return "needs-metadata";
      webkitVideo.webkitEnterFullscreen();
      return "entered";
    }
    return "unsupported";
  } catch {
    return "failed";
  }
}

// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useEffect, type RefObject } from "react";

type WakeLockSentinelLike = { released: boolean; release: () => Promise<void> };
type WakeLockNavigator = Navigator & { wakeLock?: { request: (type: "screen") => Promise<WakeLockSentinelLike> } };

/**
 * Keeps the screen on while the player is loading, converting or playing.
 * A playing <video> already does this, but a slow start (a provider
 * conversion, the in-browser remux, a buffering 4K file) does not, and a
 * laptop or iPad that dims and locks during that wait loses the start. Paused
 * or closed, the screen may sleep again. Unsupported browsers simply skip it.
 */
export function useScreenWakeLock(videoRef: RefObject<HTMLVideoElement | null>, active: boolean) {
  useEffect(() => {
    const wakeLock = typeof navigator === "undefined" ? undefined : (navigator as WakeLockNavigator).wakeLock;
    const video = videoRef.current;
    if (!active || !wakeLock || !video) return undefined;
    let sentinel: WakeLockSentinelLike | null = null;
    let closed = false;
    let pending = false;
    const wanted = () => !closed && document.visibilityState === "visible" && !(video.paused && video.readyState >= 2);
    const acquire = () => {
      if (pending || !wanted() || (sentinel && !sentinel.released)) return;
      pending = true;
      wakeLock.request("screen").then((lock) => {
        if (closed || !wanted()) void lock.release().catch(() => undefined);
        else sentinel = lock;
      }).catch(() => undefined).finally(() => { pending = false; });
    };
    const release = () => {
      const lock = sentinel;
      sentinel = null;
      if (lock && !lock.released) void lock.release().catch(() => undefined);
    };
    const onPause = () => { if (video.readyState >= 2) release(); };
    // The browser drops the lock when the tab is hidden: take it again on return.
    const onVisibility = () => { if (document.visibilityState === "visible") acquire(); };
    acquire();
    video.addEventListener("play", acquire);
    video.addEventListener("waiting", acquire);
    video.addEventListener("pause", onPause);
    document.addEventListener("visibilitychange", onVisibility);
    return () => {
      closed = true;
      video.removeEventListener("play", acquire);
      video.removeEventListener("waiting", acquire);
      video.removeEventListener("pause", onPause);
      document.removeEventListener("visibilitychange", onVisibility);
      release();
    };
  }, [videoRef, active]);
}

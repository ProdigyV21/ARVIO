// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useState, useEffect, useCallback, useRef, type RefObject } from "react";
import { pipStrategy, isPipActive, airPlayUsable, onPipChange, airPlaySupported, watchAirPlayAvailability, togglePictureInPicture, bindMediaSession, updateMediaPosition } from "@/lib/playerPlatform";
import { SKIP_SECONDS } from "./helpers";
import type { MediaItem, StreamSource } from "@/lib/types";
import type { useTranslation } from "@/lib/i18n";

/** Picture in picture, AirPlay and the lock-screen / Control Center media session. */
export function usePlatformControls({ canAdvance, item, liveTv, onAdvance, onToast, seekBy, stream, subtitleLabel, title, translateUi, videoRef }: { canAdvance: boolean; item: MediaItem | null; liveTv: boolean; onAdvance: () => Promise<boolean>; onToast: (message: string) => void; seekBy: (delta: number) => void; stream: StreamSource; subtitleLabel: string | null; title: string; translateUi: ReturnType<typeof useTranslation>; videoRef: RefObject<HTMLVideoElement | null> }) {
  // Picture in picture (standard API, or Safari's presentation mode) and the
  // AirPlay picker. Each button only renders where the API really exists.
  const [pipAvailable, setPipAvailable] = useState(false);
  const [pipActive, setPipActive] = useState(false);
  const [airPlayAvailable, setAirPlayAvailable] = useState(false);
  const [airPlayAllowed, setAirPlayAllowed] = useState(false);
  useEffect(() => {
    const video = videoRef.current;
    if (!video) return undefined;
    const refresh = () => {
      setPipAvailable(pipStrategy(video) !== "none");
      setPipActive(isPipActive(video));
      // MediaSource-fed playback (hls.js relay, remux, MPEG-TS) disables
      // remote playback: AirPlay receivers cannot fetch those bytes.
      setAirPlayAllowed(airPlayUsable(video));
    };
    refresh();
    const media = ["loadedmetadata", "emptied", "playing"];
    for (const name of media) video.addEventListener(name, refresh);
    const stopPip = onPipChange(video, refresh);
    const stopAirPlay = airPlaySupported() ? watchAirPlayAvailability(video, setAirPlayAvailable) : () => {};
    return () => {
      for (const name of media) video.removeEventListener(name, refresh);
      stopPip();
      stopAirPlay();
    };
  }, []);
  const togglePip = useCallback(() => {
    void togglePictureInPicture(videoRef.current).then((ok) => {
      if (!ok) onToast("Picture in picture is not available for this video.");
    });
  }, [onToast]);

  // Lock screen, Control Center, headset and car controls (Media Session).
  // Handlers read through a ref so seeking/advancing never re-registers them.
  const sessionActions = useRef({ seekBy, onAdvance, canAdvance, liveTv });
  sessionActions.current = { seekBy, onAdvance, canAdvance, liveTv };
  const episodeTitle = item?.episodeTitle ?? null;
  useEffect(() => {
    const video = videoRef.current;
    if (!video) return undefined;
    const line = liveTv ? translateUi("Live TV") : [subtitleLabel, episodeTitle].filter(Boolean).join(" · ") || stream.addonName || "";
    const unbind = bindMediaSession({
      title, subtitle: line, album: "ARVIO",
      poster: item?.image ?? null, backdrop: item?.backdrop ?? null, still: item?.episodeStill ?? null
    }, {
      play: () => { void video.play().catch(() => undefined); },
      pause: () => video.pause(),
      seekBy: liveTv ? undefined : (delta) => sessionActions.current.seekBy(delta),
      seekTo: liveTv ? undefined : (seconds, fast) => {
        const target = Math.max(0, Math.min(video.duration || seconds, seconds));
        const seekable = video as HTMLVideoElement & { fastSeek?: (time: number) => void };
        if (fast && typeof seekable.fastSeek === "function") seekable.fastSeek(target);
        else video.currentTime = target;
        updateMediaPosition(video);
      },
      next: canAdvance ? () => { void sessionActions.current.onAdvance(); } : null,
      skipSeconds: SKIP_SECONDS
    });
    let lastPosition = 0;
    const position = (event?: Event) => {
      // timeupdate fires ~4x/s; the lock screen extrapolates between updates.
      const now = Date.now();
      if (event?.type === "timeupdate" && now - lastPosition < 5000) return;
      lastPosition = now;
      if (!sessionActions.current.liveTv) updateMediaPosition(video);
    };
    const names = ["timeupdate", "durationchange", "ratechange", "play", "pause", "seeked", "loadedmetadata"];
    for (const name of names) video.addEventListener(name, position);
    position();
    return () => {
      for (const name of names) video.removeEventListener(name, position);
      unbind();
    };
    // translateUi only formats the static "Live TV" line.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [title, subtitleLabel, episodeTitle, item?.image, item?.backdrop, item?.episodeStill, stream.addonName, liveTv, canAdvance]);
  return { pipAvailable, pipActive, airPlayAvailable, airPlayAllowed, togglePip };
}

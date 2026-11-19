// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useEffect, type RefObject, type Dispatch, type SetStateAction } from "react";
import { authClient } from "@/lib/store";
import { isLiveStreamOrSportsItem, saveProgress, saveWatchedState } from "@/lib/cloud";
import { syncClient } from "@/lib/sync";
import { config } from "@/lib/config";
import type { InstalledAddon, MediaItem, AppSettings, StreamSource } from "@/lib/types";

/** Saves resume progress, scrobbles to the tracking services and marks finished titles watched. */
export function useProgressTracking({ activeProfileId, addons, canAdvance, item, lastSavedRef, liveTv, nextDismissed, onAdvanceRef, onToast, selectedEpisode, setEnded, setNextCountdown, setShowControls, settings, stream, upNextShownRef, videoRef }: { activeProfileId: string | null; addons: InstalledAddon[]; canAdvance: boolean; item: MediaItem | null; lastSavedRef: RefObject<number>; liveTv: boolean; nextDismissed: RefObject<boolean>; onAdvanceRef: RefObject<() => Promise<boolean>>; onToast: (message: string) => void; selectedEpisode: { season: number; episode: number; } | null; setEnded: Dispatch<SetStateAction<boolean>>; setNextCountdown: Dispatch<SetStateAction<number | null>>; setShowControls: Dispatch<SetStateAction<boolean>>; settings: AppSettings; stream: StreamSource; upNextShownRef: RefObject<boolean>; videoRef: RefObject<HTMLVideoElement | null> }) {
  useEffect(() => {
    const video = videoRef.current;
    if (!video || !item) return undefined;
    const userId = authClient.session?.userId;
    let lastPosition: { position: number; duration: number } | null = null;
    let lastQueuedPosition = -1;
    const season = selectedEpisode?.season ?? item.seasonNumber ?? null;
    const episode = selectedEpisode?.episode ?? item.episodeNumber ?? null;
    const isLiveStream = isLiveStreamOrSportsItem({
      mediaType: item.mediaType,
      id: item.id,
      streamAddonId: stream.addonId,
      title: item.title
    }, addons) || liveTv;
    const isAnime = item.mediaType === "tv" && item.originalLanguage === "ja" && Boolean(item.genreIds?.includes(16));
    const tracker = syncClient(activeProfileId);
    const trackingId = item.tmdbId ?? (item.isHomeServer ? null : item.id);
    let trackingWarningShown = false;
    const scrobble = (action: "start" | "pause" | "stop", progress: number) => {
      if (isLiveStream || !trackingId || authClient.session?.userId !== userId) return;
      void tracker.scrobble(action, {
        mediaType: item.mediaType,
        tmdbId: trackingId,
        season,
        episode,
        isAnime,
        progress
      }).catch(() => {
        if (trackingWarningShown || authClient.session?.userId !== userId) return;
        trackingWarningShown = true;
        onToast("A tracking service could not save playback. Check your tracking connections.");
      });
    };
    const capturePosition = () => {
      if (Number.isFinite(video.duration) && video.duration > 0 && Number.isFinite(video.currentTime)) {
        const offset = stream.playbackSession?.startOffset ?? 0;
        lastPosition = { position: video.currentTime + offset, duration: video.duration + offset };
      }
      return lastPosition;
    };
    const playbackProgress = () => {
      const position = capturePosition();
      return position ? Math.min(100, Math.max(0, position.position / position.duration * 100)) : item.progress ?? 0;
    };
    let scrobbleActive = false;
    let playbackStarted = false;
    let stopped = false;
    const onPlaying = () => {
      if (scrobbleActive || video.ended) return;
      playbackStarted = true;
      stopped = false;
      scrobbleActive = true;
      scrobble("start", playbackProgress());
    };
    const onPaused = () => {
      if (!scrobbleActive || video.ended || stopped) return;
      scrobbleActive = false;
      scrobble("pause", playbackProgress());
    };
    const save = (force: boolean | Event = false) => {
      capturePosition();
      if (!lastPosition || !authClient.session || authClient.session.userId !== userId || isLiveStream) return;
      const now = Date.now();
      // Each checkpoint is one small queued item (lib/progressOutbox). Periodic
      // saves every 30 s; pause/seek/close/background/end flush immediately.
      // saveProgress skips plays under 3% and 60 s like Android.
      if (force !== true && now - lastSavedRef.current < 30_000) return;
      const { position, duration } = lastPosition;
      if (lastQueuedPosition === Math.round(position)) return;
      lastQueuedPosition = Math.round(position);
      lastSavedRef.current = now;
      const progress = Math.min(1, Math.max(0, position / duration));
      void saveProgress(authClient, {
        media_type: item.mediaType,
        show_tmdb_id: item.id,
        profile_id: activeProfileId,
        season,
        episode,
        episode_title: item.episodeTitle ?? null,
        title: item.title,
        progress,
        duration_seconds: Math.round(duration),
        position_seconds: Math.round(position),
        backdrop_path: item.backdrop?.replace(config.backdropBase, "") ?? null,
        episode_still_path: item.episodeStill ?? null,
        poster_path: item.image?.replace(config.imageBase, "") ?? null,
        source: stream.addonName,
        stream_addon_id: stream.addonId ?? null,
        stream_title: stream.source
      }, activeProfileId, addons).catch(() => { lastQueuedPosition = -1; });
    };
    const stopTracking = (progress: number) => {
      if (stopped || !playbackStarted) return;
      stopped = true;
      scrobbleActive = false;
      // Match ARVIO's 90% completion threshold; lower-progress stops remain resumable.
      scrobble(progress >= 90 ? "stop" : "pause", progress);
      if (progress >= 90 && authClient.session?.userId === userId && authClient.session && !isLiveStream) {
        void saveWatchedState(authClient, {
          id: item.id,
          mediaType: item.mediaType,
          seasonNumber: season,
          episodeNumber: episode
        }, true, activeProfileId).catch(() => undefined);
      }
    };
    const onStopped = () => { stopTracking(playbackProgress()); save(true); };
    const onEnded = () => {
      stopTracking(100);
      save(true);
      setEnded(true);
      if (settings.autoPlayNext && canAdvance && !nextDismissed.current) {
        setShowControls(true);
        // iOS native fullscreen hides every page element, countdown included:
        // advance straight away rather than wait on a timer nobody can see.
        if ((video as HTMLVideoElement & { webkitDisplayingFullscreen?: boolean }).webkitDisplayingFullscreen) { void onAdvanceRef.current(); return; }
        // The "Up next" card has been on screen through the credits already.
        setNextCountdown(upNextShownRef.current ? 5 : 10);
      }
    };
    const flush = () => { save(true); onPaused(); };
    const onSeeked = () => { if (playbackStarted) save(true); };
    const background = () => {
      if (document.visibilityState === "hidden") flush();
      else if (!video.paused && !video.ended && video.readyState >= 2) onPlaying();
    };
    video.addEventListener("timeupdate", save);
    video.addEventListener("seeked", onSeeked);
    video.addEventListener("pause", flush);
    window.addEventListener("pagehide", onStopped);
    document.addEventListener("visibilitychange", background);
    video.addEventListener("playing", onPlaying);
    video.addEventListener("pause", onPaused);
    video.addEventListener("ended", onEnded);
    video.addEventListener("arvio-tracking-stop", onStopped);
    if (!video.paused && video.readyState >= 2) onPlaying();
    return () => {
      flush();
      video.removeEventListener("timeupdate", save);
      video.removeEventListener("seeked", onSeeked);
      video.removeEventListener("pause", flush);
      window.removeEventListener("pagehide", onStopped);
      document.removeEventListener("visibilitychange", background);
      video.removeEventListener("playing", onPlaying);
      video.removeEventListener("pause", onPaused);
      video.removeEventListener("ended", onEnded);
      video.removeEventListener("arvio-tracking-stop", onStopped);
    };
  }, [item, stream, selectedEpisode, settings.autoPlayNext, activeProfileId, addons, canAdvance, liveTv, onToast]);
}

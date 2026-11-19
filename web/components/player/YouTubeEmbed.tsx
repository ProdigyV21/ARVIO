"use client";

import { useEffect, useRef, useState } from "react";
import { saveProgress } from "@/lib/cloud";
import { authClient, useAppPick } from "@/lib/store";
import { createYouTubeCheckpointWriter, loadYouTubeApi, mountYouTubePlayer, youTubeEmbedUrl } from "@/lib/youtubePlayback";
import type { MediaItem, StreamSource } from "@/lib/types";

export function YouTubeEmbed({ videoId, item, stream }: { videoId: string; item: MediaItem | null; stream: StreamSource }) {
  const { activeProfile, addons } = useAppPick("activeProfile", "addons");
  const host = useRef<HTMLDivElement>(null);
  const scope = useRef({ profileId: activeProfile?.id ?? null, userId: authClient.session?.userId });
  scope.current = { profileId: activeProfile?.id ?? null, userId: authClient.session?.userId };
  const [error, setError] = useState(false);
  useEffect(() => {
    const parent = host.current;
    if (!parent) return;
    setError(false);
    const { profileId, userId } = scope.current;
    const isCurrent = () => scope.current.profileId === profileId && scope.current.userId === userId
      && authClient.session?.userId === userId;
    const checkpoint = createYouTubeCheckpointWriter(async value => {
      if (!item || !userId || stream.addonId !== "arvio_youtube") return;
      await saveProgress(authClient, {
        media_type: item.mediaType, show_tmdb_id: item.id, profile_id: profileId,
        title: item.title, progress: value.progress, position_seconds: value.position,
        duration_seconds: value.duration, poster_path: item.image, backdrop_path: item.backdrop,
        source: "YouTube", stream_addon_id: "arvio_youtube", stream_title: "YouTube",
        addon_native_id: `ytv:${videoId}`, addon_native_addon_id: "arvio_youtube"
      }, profileId, addons);
    }, isCurrent);
    let disposed = false;
    let mounted: ReturnType<typeof mountYouTubePlayer> | undefined;
    let interval: ReturnType<typeof setInterval> | undefined;
    const flush = () => mounted?.capture(true);
    const background = () => { if (document.visibilityState === "hidden") flush(); };
    void loadYouTubeApi().then(api => {
      if (disposed || !isCurrent()) return;
      const resumeSeconds = stream.resumePositionSeconds ?? item?.resumePositionSeconds ?? 0;
      const element = document.createElement("iframe");
      element.src = youTubeEmbedUrl(videoId, resumeSeconds, window.location.origin);
      element.title = item?.title ?? "YouTube";
      element.referrerPolicy = "strict-origin-when-cross-origin";
      element.allow = "autoplay; encrypted-media; fullscreen";
      element.allowFullscreen = true;
      parent.replaceChildren(element);
      mounted = mountYouTubePlayer({
        api, element, videoId, origin: window.location.origin,
        resumeSeconds,
        checkpoint, onError: () => { if (!disposed) setError(true); }
      });
      interval = setInterval(() => mounted?.capture(), 2000);
    }).catch(() => { if (!disposed) setError(true); });
    window.addEventListener("pagehide", flush);
    document.addEventListener("visibilitychange", background);
    return () => {
      disposed = true;
      clearInterval(interval);
      window.removeEventListener("pagehide", flush);
      document.removeEventListener("visibilitychange", background);
      mounted?.destroy();
      parent.replaceChildren();
    };
    // Identity changes own the player lifetime; metadata updates must not restart it.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [videoId, item?.id, activeProfile?.id, scope.current.userId]);
  return <div className="player-youtube" aria-label={item?.title ?? "YouTube"}>
    <div ref={host} style={{ width: "100%", height: "100%" }} />
    {error ? <a href={`https://www.youtube.com/watch?v=${videoId}`} target="_blank" rel="noopener noreferrer">Open in YouTube</a> : null}
  </div>;
}

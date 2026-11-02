"use client";
import "./player.css";
import { useTranslation } from "@/lib/i18n";
import { useMemo } from "react";
import { HomeScreenTip } from "./HomeScreenTip";
import { useAppPick } from "@/lib/store";
import { isSameStream, mergeSubtitleTracks, youTubeId } from "./overlay/helpers";
import { YouTubeLayer } from "./overlay/YouTubeLayer";
import { VideoPlayer } from "./overlay/VideoPlayer";
import { resolveStalkerChannel } from "@/lib/stalker";
import { xtreamInfoFromUrl } from "@/lib/iptv";

// The player overlay. PlayerOverlay picks what to play from the store; the
// player itself is split into modules under ./overlay:
//   VideoPlayer.tsx         state, controls visibility, source ladder, recovery watchdogs
//   usePlaybackEngine.ts    attaching a source: remux, URL ladder, escalation
//   useProgressTracking.ts  resume progress, scrobbling, watched state
//   subtitles.ts            subtitle tracks, delay and AI translation
//   skipUpNext.tsx          skip intro/credits and the "Up next" card
//   usePlayerKeys.ts        keyboard / remote / controller handling
//   usePlatformControls.ts  picture in picture, AirPlay, media session
//   PlayerControls.tsx      top bar, bottom bar, docked Live TV controls
//   PlayerPanels.tsx        sources / audio / subtitles / settings panel
//   PlayerScreens.tsx       boot, buffering, error and end screens
//   YouTubeLayer.tsx        trailers and the YouTube collection
//   helpers.ts              pure helpers shared by the above
// Live TV docking lives in ./useLivePlayerDock.

export function PlayerOverlay() {
  const translateUi = useTranslation();
  const {
    activeStream,
    activeChannel,
    selected,
    selectedEpisode,
    settings,
    addons,
    updateSettings,
    activeProfile,
    streams,
    playStream,
    advanceEpisode,
    setToast,
    closePlayer,
    openDetails
  } = useAppPick("activeStream", "activeChannel", "selected", "selectedEpisode", "settings", "addons", "updateSettings", "activeProfile", "streams", "playStream", "advanceEpisode", "setToast", "closePlayer", "openDetails");

  // Stalker portals hand out short-lived play tokens: the player asks for a
  // fresh link when every connection to the current one failed.
  const refreshLiveSource = useMemo(() => {
    const channel = activeChannel;
    if (!channel?.stalker || channel.stalker.direct) return undefined;
    return async () => {
      const fresh = await resolveStalkerChannel(channel, { fresh: true });
      return { url: fresh.streamUrl, headers: fresh.requestHeaders };
    };
  }, [activeChannel]);
  // The channel's Xtream server: /user/pass/<id> links there are Xtream ids
  // (with a .ts/.m3u8 twin); the same path shape elsewhere is just a path.
  const liveXtreamOrigin = useMemo(() => {
    const playlistId = activeChannel?.id.split(":")[0];
    const playlist = playlistId ? settings.iptvPlaylists?.find((entry) => entry.id === playlistId) : undefined;
    const info = playlist ? xtreamInfoFromUrl(playlist.m3uUrl) : null;
    try { return info ? new URL(info.baseUrl).origin : null; } catch { return null; }
  }, [activeChannel?.id, settings.iptvPlaylists]);
  // The playing copy may carry a prepared URL (relay, resolved debrid link):
  // match the list row by its original URL too, so later-arriving subtitles reach it.
  const enrichment = streams.find((candidate) => activeStream && (isSameStream(candidate, activeStream)
    || (candidate.url && (candidate.url === activeStream.url || candidate.url === activeStream.originalUrl))));
  const enrichedStream = useMemo(() => activeStream ? mergeSubtitleTracks(activeStream, enrichment) : null, [activeStream, enrichment]);
  // The one-time iOS "Add to Home Screen" tip lives here because this
  // component is always mounted; it waits until the player is closed.
  const tip = <HomeScreenTip playerOpen={Boolean(activeStream?.url)} />;
  if (!activeStream?.url || !enrichedStream) return tip;

  const ytId = youTubeId(activeStream.url);
  const title = activeChannel?.name ?? selected?.title ?? activeStream.source;

  if (ytId) {
    return (<>{tip}
      <YouTubeLayer videoId={ytId} item={selected} stream={activeStream} title={title} onClose={closePlayer} />
    </>);
  }

  const canAdvance = Boolean(selected?.mediaType === "tv" && selectedEpisode && !activeChannel);
  return (<>{tip}
    <VideoPlayer
      onOpenDetails={(media) => { closePlayer(); void openDetails(media); }}
      title={title}
      subtitleLabel={selectedEpisode ? `S${selectedEpisode.season} E${selectedEpisode.episode}` : null}
      stream={enrichedStream}
      streams={streams}
      item={selected}
      selectedEpisode={selectedEpisode}
      settings={settings}
      addons={addons}
      updateSettings={updateSettings}
      activeProfileId={activeProfile?.id ?? null}
      liveTv={Boolean(activeChannel)}
      refreshLiveSource={refreshLiveSource}
      liveXtreamOrigin={liveXtreamOrigin}
      canAdvance={canAdvance}
      onSelectStream={playStream}
      onAdvance={advanceEpisode}
      onToast={setToast}
      onClose={closePlayer}
    />
  </>);
}

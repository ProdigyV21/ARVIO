// App store (lib/store.tsx): Starting playback: browser or external player, the next episode, trailers, Live TV and catch-up.
import { resolveStalkerChannel } from "../stalker";
import { browserAutoplayCandidates } from "../browserAutoplay";
import { needsAudioConversion } from "../autoplayVerify";
import { isPreferredAutoplaySource, loadAutoplayPreference, orderByPreference } from "../autoplaySelection";
import { recordBrowserPlaybackFailure, playbackPlan } from "../streamCompatibility";
import { useCallback } from "react";
import { getStreamsProgressive } from "../addons";
import { isDisabledTelegramSource, TELEGRAM_DISABLED_MESSAGE } from "../config";
import { cachedDebridDirectUrl, parseDebridStream } from "../debrid";
import { createPendingExternalPlayback } from "../externalPlayback";
import { trackPremiumEvent } from "../premiumAnalytics";
import { externalLaunchMode, openExternalPlayer } from "../externalPlayers";
import { premiumAllows } from "../premiumGate";
import { prepareBrowserStream } from "../prepareBrowserStream";
import { reportHomeServerPlayback } from "../homeServerPlayback";
import { buildXtreamCatchupUrl } from "../iptv";
import { recordTvPlayback } from "../iptvSession";
import { getDetails, getSeasonEpisodes } from "../tmdb";
import { kidsLevelOf } from "../kids";
import type { IptvChannel, IptvProgram, MediaItem, StreamSource } from "../types";
import { authClient } from "./clients";
import { nextLocalEpisode } from "./watchHistory";
import { getPriorityConfig } from "./settings";
import type { useDetailsSources } from "./details";
import type { StoreCore } from "./core";

export function usePlaybackActions(store: StoreCore & Pick<ReturnType<typeof useDetailsSources>, "appendHomeServerSources" | "appendTelegramSources" | "appendVodSources" | "mergeStreams">) {
  const { activeProfile, activeProfileIdRef, activeProfileRef, addonsRef, appendHomeServerSources, appendTelegramSources, appendVodSources, busyRef, mergeStreams, ownedPlayback, playbackGeneration, playbackPreparation, selected, selectedEpisode, setActiveChannel, setActiveStream, setSelected, setSelectedEpisode, setSettings, setStreams, setToast, settingsRef, sourceGeneration, stopOwnedPlayback, streamsRef } = store;
  const playStream = useCallback((stream: StreamSource, options: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean } = {}) => {
    if (isDisabledTelegramSource(stream)) {
      setToast(TELEGRAM_DISABLED_MESSAGE);
      return;
    }
    // Explore mode: hosted ARVIO Web asks for Premium here (and resumes after
    // payment). Self-hosted builds never register a gate. A source headed for
    // VLC/Infuse is not resumed automatically: iOS opens an app only from
    // inside the original tap, so the member presses Play again.
    const headedExternal = stream.addonId !== "arvio_youtube" && !stream.autoSelect && !options.forceBrowser && !options.forceRemux && !options.forceTranscode
      && (settingsRef.current.defaultPlayer === "vlc" || settingsRef.current.defaultPlayer === "infuse");
    if (!premiumAllows("play", headedExternal ? undefined : () => playStream(stream, options))) return;
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    if (!stream.autoSelect) setActiveStream(null);
    const sameEpisode = selected?.mediaType === "movie" || (selected?.seasonNumber === selectedEpisode?.season && selected?.episodeNumber === selectedEpisode?.episode);
    if (stream.resumePositionSeconds === undefined && selected && sameEpisode && !selected.isWatched) {
      stream = { ...stream, resumePositionSeconds: selected.resumePositionSeconds };
    }
    const controller = new AbortController();
    playbackPreparation.current = controller;
    const generation = ++playbackGeneration.current;
    const profileId = activeProfileIdRef.current;
    const accountId = authClient.session?.userId;
    const isCurrent = () => generation === playbackGeneration.current && profileId === activeProfileIdRef.current && accountId === authClient.session?.userId;
    if (!stream.url) {
      setToast("This source has no direct stream URL yet.");
      return;
    }
    // Default external player (VLC / Infuse): hand the source straight to it,
    // resolving the debrid CDN URL first (external players can't follow the
    // torrentio redirect chain). A pending record lets ARVIO scrobble to Trakt
    // + save progress when the user returns. forceBrowser overrides (e.g. the
    // in-player source panel, which is already in the browser player).
    const preferredPlayer = settingsRef.current.defaultPlayer;
    if (stream.addonId !== "arvio_youtube" && !stream.autoSelect && !options.forceBrowser && !options.forceRemux && !options.forceTranscode && (preferredPlayer === "vlc" || preferredPlayer === "infuse")) {
      const externalItem = selected;
      const externalTitle = selected?.title ?? stream.source ?? "ARVIO stream";
      const preferredSub = settingsRef.current.defaultSubtitle;
      // The deep link MUST fire synchronously in the Play click — custom-scheme
      // navigation (vlc-x-callback://) is blocked after an await. Use the
      // prefetch-cached CDN url if present, otherwise the raw source (VLC
      // follows the redirect itself). Subtitles attach only if we already have
      // them from the panel prefetch on the stream.
      const cached = parseDebridStream(stream.url) ? cachedDebridDirectUrl(stream.url) : null;
      const target = cached ? { ...stream, url: cached, originalUrl: stream.url } : stream;
      setToast(
        preferredPlayer === "vlc" && externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : `Opening in ${preferredPlayer.toUpperCase()}...`
      );
      createPendingExternalPlayback({
        player: preferredPlayer,
        item: externalItem,
        stream: target,
        title: externalTitle,
        profileId: activeProfile?.id ?? null,
        season: selectedEpisode?.season ?? null,
        episode: selectedEpisode?.episode ?? null
      });
      openExternalPlayer(preferredPlayer, target, externalTitle, preferredSub);
      void trackPremiumEvent(authClient, "external_playback_requested", { player: preferredPlayer, playback_type: "vod" }, true);
      return;
    }
    setToast("Preparing browser playback...");
    const initial = stream;
    void (async () => {
      const attempted = new Set<string>();
      let candidate: StreamSource | undefined = initial;
      while (candidate && isCurrent() && !controller.signal.aborted) {
        attempted.add(candidate.originalUrl ?? candidate.url ?? "");
        const attempt = new AbortController();
        const cancel = () => attempt.abort();
        controller.signal.addEventListener("abort", cancel, { once: true });
        let timer: number | undefined;
        try {
          const prepared = await Promise.race([
            prepareBrowserStream(...(() => {
              // A probe already showed this file's audio would be dropped here
              // (E-AC-3/DTS on Chromium): start in the audio-converting path.
              const next = options.forceTranscode || candidate.convertAudio || candidate.homeServer || candidate.transcoded || !needsAudioConversion(candidate)
                ? candidate : { ...candidate, convertAudio: true };
              return [next, settingsRef.current, { ...options, ...(next.convertAudio ? { forceRemux: true } : {}), signal: attempt.signal }] as const;
            })()).then(result => {
              if (attempt.signal.aborted) {
                void reportHomeServerPlayback(result, settingsRef.current, "stop").catch(() => undefined);
                throw new Error("Playback cancelled");
              }
              return result;
            }),
            new Promise<never>((_, reject) => {
              timer = window.setTimeout(() => { reject(new Error("The source did not respond in time. Try another source.")); attempt.abort(); }, 20000);
              attempt.signal.addEventListener("abort", () => reject(new Error("Playback cancelled")), { once: true });
            })
          ]);
          if (!isCurrent() || controller.signal.aborted) {
            void reportHomeServerPlayback(prepared, settingsRef.current, "stop").catch(() => undefined);
            return;
          }
          setActiveChannel(null);
          ownedPlayback.current = { stream: prepared, settings: settingsRef.current };
          setActiveStream(prepared);
          setToast(null);
          return;
        } catch (error) {
          if (!isCurrent() || controller.signal.aborted) return;
          if (!initial.autoSelect) { setToast(error instanceof Error ? error.message : "Could not prepare this source."); return; }
          recordBrowserPlaybackFailure(candidate, "This source could not start in this browser", true);
          candidate = browserAutoplayCandidates(streamsRef.current, attempted)[0];
          // Progressive providers may still be returning sources when the first
          // URL fails. Give that in-flight discovery a bounded chance to finish.
          const waitUntil = Date.now() + 20000;
          while (!candidate && busyRef.current === "Finding sources" && isCurrent() && !controller.signal.aborted && Date.now() < waitUntil) {
            await new Promise<void>(resolve => {
              const done = () => { window.clearTimeout(waitTimer); controller.signal.removeEventListener("abort", done); resolve(); };
              const waitTimer = window.setTimeout(done, 250);
              controller.signal.addEventListener("abort", done, { once: true });
            });
            candidate = browserAutoplayCandidates(streamsRef.current, attempted)[0];
          }
          if (candidate) {
            candidate = { ...candidate, autoSelect: true, resumePositionSeconds: initial.resumePositionSeconds };
            setToast("Trying another browser source...");
          }
        } finally {
          window.clearTimeout(timer);
          controller.signal.removeEventListener("abort", cancel);
        }
      }
      if (isCurrent() && !controller.signal.aborted) {
        setActiveStream(null);
        setToast("No browser-playable source could start. Open Sources to choose another provider or an external player.");
      }
    })();
  }, [selected, activeProfile, selectedEpisode]);

  const advanceEpisode = useCallback(async (): Promise<boolean> => {
    if (!selected || selected.mediaType !== "tv" || !selectedEpisode) return false;
    const generation = ++sourceGeneration.current;
    const playback = ++playbackGeneration.current;
    const isCurrent = () => sourceGeneration.current === generation && playbackGeneration.current === playback;
    try {
      const next = nextLocalEpisode({ ...selected, timeRemainingLabel: "Up next", seasonNumber: selectedEpisode.season, episodeNumber: selectedEpisode.episode + 1 });
      if (next?.seasonNumber == null || next.episodeNumber == null) { setToast("You have reached the last available episode."); return false; }
      const episodes = await getSeasonEpisodes(selected.tmdbId ?? selected.id, next.seasonNumber);
      if (!isCurrent()) return false;
      const episode = episodes.find((item) => item.episodeNumber === next.episodeNumber);
      if (!episode || (episode.airDate && Date.parse(episode.airDate) > Date.now())) { setToast("The next episode is not available yet."); return false; }
      let started = false;
      // Same bounded preference as the details Play button: a debrid source or
      // the addon that last played on this device gets a short window to
      // arrive before the fastest addon's first row wins by default.
      const preference = loadAutoplayPreference();
      // Addon updates are cumulative snapshots while home-server, VOD and
      // Telegram results arrive once each: keep one entry per source.
      const arrived = new Map<string, StreamSource>();
      let settled = false;
      const choose = (rows: StreamSource[]) => {
        if (!isCurrent() || started) return;
        for (const row of rows) arrived.set(`${row.addonId ?? row.addonName ?? ""}|${row.url}`, row);
        const candidates = orderByPreference(browserAutoplayCandidates([...arrived.values()]), preference, (row) => playbackPlan(row).method);
        if (!settled && !candidates.some((row) => isPreferredAutoplaySource(row, preference, (entry) => Boolean(parseDebridStream(entry.originalUrl ?? entry.url))))) return;
        const candidate = candidates[0];
        if (!candidate) return;
        started = true;
        setSelectedEpisode({ season: next.seasonNumber!, episode: next.episodeNumber! });
        setSelected((item) => item ? { ...item, seasonNumber: next.seasonNumber, episodeNumber: next.episodeNumber, episodeTitle: episode.name, episodeStill: episode.still } : item);
        setActiveStream(null);
        playStream({ ...candidate, autoSelect: true, resumePositionSeconds: 0 }, { forceBrowser: true });
      };
      setStreams([]);
      const publish = (rows: StreamSource[]) => { if (sourceGeneration.current === generation) { mergeStreams(rows); choose(rows); } };
      const settleTimer = window.setTimeout(() => { settled = true; choose([]); }, 2500);
      // All source types participate; one unavailable provider does not hold up another.
      await Promise.allSettled([
        getStreamsProgressive(addonsRef.current, selected, next.seasonNumber, next.episodeNumber, publish).then(publish),
        appendHomeServerSources(selected, next.seasonNumber, next.episodeNumber).then(choose),
        appendVodSources(selected, next.seasonNumber, next.episodeNumber).then(choose),
        appendTelegramSources(selected, next.seasonNumber, next.episodeNumber).then(choose)
      ]);
      window.clearTimeout(settleTimer);
      settled = true;
      choose([]);
      if (!started && isCurrent()) setToast("No browser-playable source for the next episode. Choose a source manually.");
      return started;
    } catch {
      if (isCurrent()) setToast("Could not load the next episode. Please retry.");
      return false;
    }
  }, [selected, selectedEpisode, playStream, mergeStreams, appendHomeServerSources, appendVodSources, appendTelegramSources]);

  const playTrailer = useCallback(async (item: MediaItem) => {
    let url = item.trailerUrl ?? null;
    if (!url) {
      const priorityConfig = getPriorityConfig(settingsRef.current);
      const detailed = await getDetails(item, priorityConfig).catch(() => item);
      url = detailed.trailerUrl ?? null;
      setSelected((current) => current ?? detailed);
    }
    if (!url) {
      setToast("No trailer available for this title.");
      return;
    }
    // Trailers are YouTube page URLs: play them in the in-app YouTube embed
    // (PlayerOverlay) instead of window.open, which left the iPhone home-screen
    // app for Safari/YouTube with no way back.
    const videoId = url.match(/(?:v=|youtu\.be\/|embed\/)([\w-]{11})/)?.[1];
    if (!videoId) {
      if (typeof window !== "undefined") window.open(url, "_blank", "noopener,noreferrer");
      return;
    }
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    playbackGeneration.current++;
    setActiveChannel(null);
    setActiveStream({ source: "Trailer", addonName: "Trailer", addonId: "arvio_trailer", url: `https://www.youtube.com/watch?v=${videoId}`, resumePositionSeconds: 0 });
  }, [setToast]);

  // Hand a live/catch-up stream to VLC/Infuse. Most IPTV is plain-HTTP (mixed
  // content in a secure page) and many providers block anything but a real
  // player, so an external player is the reliable path — it plays from the
  // user's own connection with no browser restrictions, at zero server cost.
  // Returns true when the handoff fired (so the caller skips the browser player).
  const openLiveExternally = useCallback((stream: StreamSource, title: string): boolean => {
    if (isDisabledTelegramSource(stream)) {
      setToast(TELEGRAM_DISABLED_MESSAGE);
      return false;
    }
    const player = settingsRef.current.defaultPlayer;
    if (player !== "vlc" && player !== "infuse") return false;
    setToast(
      player === "infuse"
        ? "Opening in Infuse..."
        : externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : "Opening in VLC..."
    );
    openExternalPlayer(player, stream, title, settingsRef.current.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player, playback_type: "live_or_catchup" }, true);
    return true;
  }, []);

  const recordChannelPlayback = useCallback((channel: IptvChannel) => {
    setSettings(current => ({ ...current, iptvTvSession: recordTvPlayback(current.iptvTvSession, channel) }));
  }, []);

  const playChannel = useCallback(async (channel: IptvChannel) => {
    // Live TV carries no age ratings: a kids profile never plays it.
    if (kidsLevelOf(activeProfileRef.current)) { setToast("Not available on this kids profile"); return; }
    if (!premiumAllows("live", () => { void playChannel(channel); })) return;
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    const generation = ++playbackGeneration.current;
    if (channel.stalker) {
      setToast("Preparing browser playback...");
      try { channel = await resolveStalkerChannel(channel); }
      catch (error) { if (generation === playbackGeneration.current) setToast(error instanceof Error ? error.message : "Could not open channel"); return; }
      if (generation !== playbackGeneration.current) return;
      setToast(null);
    }
    const stream: StreamSource = {
      source: channel.name,
      addonName: "Live TV",
      quality: "Live",
      size: "",
      url: channel.streamUrl,
      description: channel.group,
      behaviorHints: { proxyHeaders: { request: channel.requestHeaders } }
    };
    if (!channel.id?.startsWith("sports-addon:")) recordChannelPlayback(channel);

    setActiveChannel(channel);
    setActiveStream(stream);
  }, [openLiveExternally, recordChannelPlayback]);

  // Catch-up plays a finished programme from the panel's archive. It is a
  // seekable VOD stream (no activeChannel → scrubber works), but the player
  // still gives it the IPTV proxy ladder via the "Catch-up" addonName marker.
  const playCatchup = useCallback((channel: IptvChannel, program: IptvProgram) => {
    if (kidsLevelOf(activeProfileRef.current)) { setToast("Not available on this kids profile"); return; }
    if (!premiumAllows("live", () => playCatchup(channel, program))) return;
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    playbackGeneration.current++;
    const url = buildXtreamCatchupUrl(settingsRef.current.iptvPlaylists, channel, program);
    if (!url) {
      setToast("Catch-up is not available for this channel.");
      return;
    }
    const title = `${channel.name} · ${program.title}`;
    const stream: StreamSource = {
      source: title,
      addonName: "Catch-up",
      quality: "Catch-up",
      size: "",
      url,
      description: channel.group,
      behaviorHints: { proxyHeaders: { request: channel.requestHeaders } }
    };

    setActiveChannel(null);
    setActiveStream(stream);
  }, [setToast, openLiveExternally]);

  const closePlayer = useCallback(() => {
    playbackPreparation.current?.abort();
    stopOwnedPlayback();
    playbackGeneration.current++;
    setActiveStream(null);
    setActiveChannel(null);
  }, []);
  return { playStream, advanceEpisode, playTrailer, openLiveExternally, recordChannelPlayback, playChannel, playCatchup, closePlayer };
}

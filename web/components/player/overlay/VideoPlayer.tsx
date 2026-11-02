// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import type { MediaItem, StreamSource, AppSettings, InstalledAddon } from "@/lib/types";
import { useTranslation } from "@/lib/i18n";
import { useRef, useMemo, useCallback, useState, useEffect } from "react";
import { getPriorityConfig, authClient } from "@/lib/store";
import { usePlayerEpisodeMetadata } from "../usePlayerEpisodeMetadata";
import { useLivePlayerDock } from "../useLivePlayerDock";
import type { PlaybackHandle, PlaybackTracks } from "@/lib/player";
import type { LiveFailure } from "@/lib/liveTvPlayback";
import { subtitleLanguageName, SubtitleTranslator } from "@/lib/subtitleAi";
import { trackLanguageName, languageCode } from "@/lib/languages";
import { trackPremiumAttempt, trackPremiumDaily, trackPremiumMilestone, trackPremiumEvent } from "@/lib/premiumAnalytics";
import { playbackAttemptDetail } from "@/lib/playbackTelemetry";
import { parseDebridStream, cachedDebridDirectUrl, resolveDebridDirectUrl, isUncachedDebridStream } from "@/lib/debrid";
import { type SubtitleLoadState, groupSubtitles, positionSubtitleCues } from "@/lib/playerSubtitles";
import type { RemuxAudioTrack } from "@/lib/remux";
import { reportHomeServerPlayback, updateHomeServerPlaybackPosition } from "@/lib/homeServerPlayback";
import { canTryRemux, recordBrowserPlaybackFailure, canProviderTranscode, streamPlayability, streamTransport } from "@/lib/streamCompatibility";
import { rememberSuccessfulSource } from "@/lib/autoplaySelection";
import { markHomeScreenTipEligible } from "@/lib/homeScreenTip";
import { isAudioOnlySource, monitorAudioDecode, monitorVideoFrames, monitorPlaybackStall, bufferedEndAt, bufferedAhead } from "@/lib/playerRecovery";
import { streamSizeBytes, groupSourcesForPlayback } from "@/lib/sourceRank";
import { getLogoUrl } from "@/lib/tmdb";
import { browserAutoplayCandidates } from "@/lib/browserAutoplay";
import { prefersVideoElementFullscreen, requestVideoFullscreen } from "@/lib/fullscreen";
import { isFullscreenActive, toggleFullscreen as toggleFullscreenFor, lockLandscape, unlockOrientation, onFullscreenChange } from "@/lib/playerPlatform";
import { externalLaunchMode, openExternalPlayer, openInAnyPlayer, copyStreamUrl, isAppleMobile, externalPlayerOptions, type ExternalPlayerOption } from "@/lib/externalPlayers";
import { createPendingExternalPlayback } from "@/lib/externalPlayback";
import { PlayerPanel, qualityBadges, defaultSubtitleIndex, SKIP_SECONDS } from "./helpers";
import { useAiSubtitleTranslation, useSubtitleTracks } from "./subtitles";
import { usePlaybackEngine } from "./usePlaybackEngine";
import { useProgressTracking } from "./useProgressTracking";
import { usePlatformControls } from "./usePlatformControls";
import { usePlayerKeys } from "./usePlayerKeys";
import { useSkipAndUpNext, PlayerSkipUpNext } from "./skipUpNext";
import { LiveDockControls, PlayerTopBar, PlayerControlsBar } from "./PlayerControls";
import { PlayerStatusLayers, PlayerEndScreen } from "./PlayerScreens";
import { PlayerSidePanel } from "./PlayerPanels";

export function VideoPlayer({
  onOpenDetails,
  title,
  subtitleLabel,
  stream,
  streams,
  item,
  selectedEpisode,
  settings,
  addons,
  updateSettings,
  activeProfileId,
  liveTv,
  liveXtreamOrigin,
  refreshLiveSource,
  canAdvance,
  onSelectStream: selectStream,
  onAdvance: advance,
  onToast,
  onClose: close
}: {
  onOpenDetails: (item: MediaItem) => void;
  title: string;
  subtitleLabel: string | null;
  stream: StreamSource;
  streams: StreamSource[];
  item: MediaItem | null;
  selectedEpisode: { season: number; episode: number } | null;
  settings: AppSettings;
  addons: InstalledAddon[];
  updateSettings: (patch: Partial<AppSettings>) => void;
  activeProfileId: string | null;
  liveTv: boolean;
  /** Live TV: fetch a fresh link for a channel whose links expire (Stalker portals). */
  refreshLiveSource?: () => Promise<{ url: string; headers?: Record<string, string> } | null>;
  /** Live TV: origin of the channel's Xtream server (see liveStreamShape). */
  liveXtreamOrigin?: string | null;
  canAdvance: boolean;
  onSelectStream: (stream: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean }) => void;
  onAdvance: () => Promise<boolean>;
  onToast: (message: string) => void;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const containerRef = useRef<HTMLDivElement | null>(null);
  const videoRef = useRef<HTMLVideoElement | null>(null);
  const metadataPriority = useMemo(() => getPriorityConfig(settings), [settings]);
  const pauseOverview = usePlayerEpisodeMetadata(item, selectedEpisode, metadataPriority, liveTv);
  const dock = useLivePlayerDock(liveTv, stream.url ?? "", close);
  const onClose = useCallback(() => {
    videoRef.current?.dispatchEvent(new Event("arvio-tracking-stop"));
    close();
  }, [close]);
  const onAdvance = useCallback(async () => {
    const video = videoRef.current;
    video?.dispatchEvent(new Event("arvio-tracking-stop"));
    const advanced = await advance();
    if (!advanced && video && !video.paused && !video.ended) video.dispatchEvent(new Event("playing"));
    return advanced;
  }, [advance]);
  const onAdvanceRef = useRef(onAdvance);
  onAdvanceRef.current = onAdvance;
  const upNextShownRef = useRef(false);
  const onSelectStream = useCallback((next: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean }) => {
    const video = videoRef.current;
    // A failed probe or destroyed MediaSource has a zero clock, not a new
    // resume position. Keep the pending seek until replacement media is ready.
    const time = video && video.readyState >= 1 && Number.isFinite(video.currentTime)
      ? video.currentTime + (stream.playbackSession?.startOffset ?? 0)
      : resumeAtRef.current || stream.resumePositionSeconds || 0;
    selectStream({ ...next, resumePositionSeconds: time }, options);
  }, [selectStream, stream.playbackSession?.startOffset, stream.resumePositionSeconds]);
  const transportRef = useRef<PlaybackHandle | null>(null);
  const [transportTracks, setTransportTracks] = useState<PlaybackTracks>({ audioTracks: [], qualities: [], selectedAudioTrackId: null, selectedQualityId: null });
  const lastSavedRef = useRef(0);
  const hideTimer = useRef<ReturnType<typeof setTimeout> | null>(null);
  const skipTimer = useRef<ReturnType<typeof setTimeout> | null>(null);

  const [playing, setPlaying] = useState(false);
  const [playBlocked, setPlayBlocked] = useState(false);
  // Boot screen (like the app): backdrop + pulsing clearlogo covers the player
  // and blocks input until first frames are ready.
  const [booted, setBooted] = useState(false);
  const [bootLogo, setBootLogo] = useState<string | null>(null);
  // AI subtitle translation (same providers/prompt as the Android app).
  const [aiSubsActive, setAiSubsActive] = useState(false);
  const [aiTranslating, setAiTranslating] = useState(false);
  // Last translation failure, shown in the subtitle panel (and toasted, at
  // most every 20 s) instead of silently leaving cues untranslated.
  const [aiError, setAiError] = useState<string | null>(null);
  const aiTargetName = subtitleLanguageName(trackLanguageName(settings.defaultSubtitle) || settings.defaultSubtitle);
  const aiAvailable = settings.aiSubtitlesEnabled && Boolean(settings.aiApiKey) && Boolean(aiTargetName) && Boolean(languageCode(settings.defaultSubtitle)) && languageCode(settings.defaultSubtitle) !== "en";
  const translatorRef = useRef<SubtitleTranslator | null>(null);
  const [playbackRate, setPlaybackRateState] = useState(1);
  const setPlaybackRate = useCallback((rate: number) => {
    setPlaybackRateState(rate);
    if (videoRef.current) videoRef.current.playbackRate = rate;
  }, []);
  const [current, setCurrent] = useState(0);
  const [duration, setDuration] = useState(0);
  const [buffered, setBuffered] = useState(0);
  // Position being dragged to. While this is set the scrubber shows it instead
  // of `current`, and the seek is deferred to pointer release: React's onChange
  // is the DOM `input` event, so seeking there fired a real seek on every step
  // of a drag — each one restarting the buffer on a remote stream.
  const [scrubTo, setScrubTo] = useState<number | null>(null);
  // Time under the cursor, for the hover tooltip.
  const [hoverTime, setHoverTime] = useState<number | null>(null);
  const [hoverPct, setHoverPct] = useState(0);
  /** Seconds buffered ahead of the playhead, for the buffering readout. */
  const [bufferAheadSec, setBufferAheadSec] = useState(0);
  const [volume, setVolume] = useState(1);
  const [muted, setMuted] = useState(false);
  const [buffering, setBuffering] = useState(true);
  const [showControls, setShowControls] = useState(true);
  // What the loading screen says it is doing ("Connecting…", "Trying next
  // source (2 of 4)") — the boot screen used to be a silent logo for up to 20 s.
  const [bootStatus, setBootStatus] = useState<{ label: string; values?: Record<string, string | number> }>({ label: "Connecting…" });
  const [nextCountdown, setNextCountdown] = useState<number | null>(null);
  const nextDismissed = useRef(false);
  const [upNextDismissed, setUpNextDismissed] = useState(false);
  const [ended, setEnded] = useState(false);
  useEffect(() => { setNextCountdown(null); nextDismissed.current = false; setUpNextDismissed(false); setEnded(false); }, [stream.url]);
  useEffect(() => {
    if (nextCountdown === null) return;
    if (nextCountdown <= 0) {
      setNextCountdown(null);
      void onAdvance();
      return;
    }
    const timer = window.setTimeout(() => setNextCountdown((value) => value === null ? null : value - 1), 1000);
    return () => window.clearTimeout(timer);
  }, [nextCountdown, onAdvance]);
  const [fullscreen, setFullscreen] = useState(false);
  const [error, setError] = useState(false);
  const [errorDetail, setErrorDetail] = useState("");
  // Live TV: why the channel failed, in plain words (lib/liveTvPlayback.ts).
  const [liveFailure, setLiveFailure] = useState<LiveFailure | null>(null);
  const notify = useCallback((message: string, values?: Record<string, string | number>) => {
    onToast(translateUi(message, values && Object.fromEntries(Object.entries(values).map(([key, value]) => [key, typeof value === "string" ? translateUi(value) : value]))));
  }, [onToast, translateUi]);
  // Live channels reconnect after a mid-play stall instead of pointing at a
  // source list Live TV does not have; bounded per channel.
  const liveStallRestartsRef = useRef(0);
  // A refreshed Stalker link replaces the stale one for restarts, outside
  // players and Copy link while the channel is the same stream.
  const liveSourceOverrideRef = useRef<{ from: string; url: string; headers?: Record<string, string> } | null>(null);
  const withLiveLink = useCallback((selected: StreamSource): StreamSource => {
    const fresh = liveSourceOverrideRef.current;
    if (!liveTv || !fresh || selected.url !== fresh.from) return selected;
    return { ...selected, url: fresh.url, behaviorHints: { ...selected.behaviorHints, proxyHeaders: { ...selected.behaviorHints?.proxyHeaders, request: fresh.headers } } };
  }, [liveTv]);
  useEffect(() => { liveStallRestartsRef.current = 0; }, [stream.url]);
  const failureDiagnosticRef = useRef<{ failure_kind: string; phase: "startup" | "playback"; transport?: string }>({ failure_kind: "unknown", phase: "startup" });
  // One diagnostic per failed attempt (capped per page): platform, method,
  // container and host type — allow-listed labels, never URLs or titles.
  // "retried" = this source failed but the player moved on to another one;
  // "final" = the failure reached the error screen.
  const reportFailedAttempt = useCallback((failed: StreamSource, outcome: "retried" | "final") => {
    void trackPremiumAttempt(authClient, "playback_failed", {
      playback_type: liveTv ? "live" : "vod", transport: failed.remux ? "remux" : failed.transport ?? "file",
      ...failureDiagnosticRef.current,
      ...playbackAttemptDetail(failed, { liveTv, debrid: Boolean(parseDebridStream(failed.originalUrl ?? failed.url)) }),
      outcome
    });
  }, [liveTv]);
  useEffect(() => {
    if (error) reportFailedAttempt(currentStreamRef.current, "final");
  }, [error, reportFailedAttempt]);
  const [activePanel, setActivePanel] = useState<PlayerPanel>(null);
  const [activeSubtitle, setActiveSubtitle] = useState(-1);
  const [subtitleState, setSubtitleState] = useState<SubtitleLoadState>({ status: "off", track: null });
  const [subtitleRetry, setSubtitleRetry] = useState(0);
  const manualSubtitle = useRef<{ source: StreamSource["url"]; preference: string; url: string | null } | null>(null);
  const selectSubtitle = (index: number) => {
    manualSubtitle.current = { source: stream.url, preference: settings.defaultSubtitle, url: stream.subtitles?.[index]?.url ?? null };
    setActiveSubtitle(index);
    setAiSubsActive(false);
    setEmbeddedChoiceRef.current(null);
  };
  const setEmbeddedChoiceRef = useRef<(track: TextTrack | null) => void>(() => undefined);
  const [skipOverlay, setSkipOverlay] = useState<number | null>(null);
  const [remuxTracks, setRemuxTracks] = useState<RemuxAudioTrack[]>([]);
  const [remuxAudioIndex, setRemuxAudioIndex] = useState(-1);
  // Desired audio index survives remux restarts (switching audio re-runs the
  // effect); -1 means "use the probe's automatic choice".
  const remuxAudioIndexRef = useRef(stream.remuxAudioIndex ?? -1);
  const [remuxRestartKey, setRemuxRestartKey] = useState(0);
  useEffect(() => {
    if (!stream.playbackSession) return;
    const video = videoRef.current;
    if (!video) return;
    const offset = stream.playbackSession.startOffset ?? 0;
    let position = offset;
    let started = false;
    let stopped = false;
    const report = (event: "start" | "progress" | "stop") => {
      void reportHomeServerPlayback(stream, settings, event, { positionSeconds: position, durationSeconds: video.duration + offset, paused: video.paused }).catch(() => undefined);
    };
    const capture = () => {
      if (video.readyState >= 1) position = video.currentTime + offset;
      updateHomeServerPlaybackPosition(stream, { positionSeconds: position, durationSeconds: video.duration + offset, paused: video.paused });
    };
    const playing = () => {
      if (stopped) { resumeAtRef.current = video.currentTime + offset; onSelectStream(stream, { forceBrowser: true }); return; }
      capture(); started = true;
      // The session reporter deduplicates acknowledged starts and retries failures.
      report("start");
    };
    const paused = () => { capture(); if (started) report("progress"); };
    const stop = () => { capture(); if (!stopped) { stopped = true; report("stop"); } };
    video.addEventListener("timeupdate", capture);
    video.addEventListener("playing", playing);
    video.addEventListener("pause", paused);
    video.addEventListener("ended", stop);
    video.addEventListener("arvio-playback-failed", stop);
    const timer = window.setInterval(() => { capture(); if (started && !video.paused) report("progress"); }, 15000);
    return () => {
      window.clearInterval(timer);
      video.removeEventListener("timeupdate", capture); video.removeEventListener("playing", playing); video.removeEventListener("pause", paused);
      video.removeEventListener("ended", stop); video.removeEventListener("arvio-playback-failed", stop);
      // The store owns the session and releases it even if this effect never mounts.
    };
    // Session/server credentials are captured for this source, not the next profile.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stream.url, stream.playbackSession?.sessionId]);
  useEffect(() => { if (error) videoRef.current?.dispatchEvent(new Event("arvio-playback-failed")); }, [error]);
  const switchRemuxAudio = useCallback((index: number) => {
    remuxAudioIndexRef.current = index;
    setRemuxAudioIndex(index);
    // Picking a track on a direct-played source switches into the remux path,
    // which is what actually lets us choose the audio stream. Keep the
    // position: changing language should not send you back to the start.
    const playhead = videoRef.current?.currentTime ?? 0;
    if (playhead > 5) resumeAtRef.current = playhead;
    if (!stream.remux) {
      onSelectStream({ ...stream, remuxAudioIndex: index }, { forceRemux: true });
      return;
    }
    setRemuxRestartKey((key) => key + 1);
  }, [stream, onSelectStream]);

  // Native file playback may expose no track APIs. Probing during playback would
  // open extra range connections to the same CDN link and starve the video
  // (TorBox limits connections per link) — so the container is probed ONLY
  // when the user opens the Audio panel, on demand.
  const [audioProbeState, setAudioProbeState] = useState<"idle" | "probing" | "done" | "failed">("idle");
  const audioProbeAbort = useRef<AbortController | null>(null);
  useEffect(() => {
    setAudioProbeState("idle");
    if (!stream.remux) {
      setRemuxTracks([]);
      setRemuxAudioIndex(-1);
    }
    return () => audioProbeAbort.current?.abort();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stream.url]);
  const probeAudioTracks = useCallback(() => {
    const current = currentStreamRef.current;
    if (liveTv || current.remux || !current.url) return;
    if (!canTryRemux(current)) return;
    setAudioProbeState("probing");
    audioProbeAbort.current?.abort();
    const controller = new AbortController();
    audioProbeAbort.current = controller;
    void (async () => {
      try {
        const { probeAndPrepareRemux } = await import("@/lib/remux");
        const originalUrl = current.originalUrl ?? current.url!;
        const debrid = parseDebridStream(originalUrl);
        let probeUrl = cachedDebridDirectUrl(originalUrl) ?? current.url!;
        if (debrid && probeUrl === originalUrl) {
          const resolved = await resolveDebridDirectUrl(debrid);
          if (!resolved.url) throw new Error("Could not resolve audio source");
          probeUrl = resolved.url;
        }
        if (controller.signal.aborted) return;
        const prepared = await probeAndPrepareRemux(probeUrl, current.behaviorHints?.proxyHeaders?.request, settings.audioLanguage, { signal: controller.signal });
        if (!prepared) throw new Error("Could not read audio tracks");
        if (!controller.signal.aborted) {
          setRemuxTracks(prepared.probe.audioTracks);
          setAudioProbeState("done");
        }
        prepared.destroy();
      } catch {
        if (!controller.signal.aborted) setAudioProbeState("failed");
      }
    })();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [liveTv, settings.audioLanguage]);

  useEffect(() => {
    setBooted(false);
    const video = videoRef.current;
    if (!video) return undefined;
    const onReady = () => setBooted(true);
    void trackPremiumDaily(authClient, "playback_requested", { playback_type: liveTv ? "live" : "vod" });
    const onPlaying = () => {
      onReady();
      void trackPremiumMilestone(authClient, "first_playback", { playback_type: liveTv ? "live" : "vod" });
      void trackPremiumDaily(authClient, "playback_started", { playback_type: liveTv ? "live" : "vod" });
      if (!liveTv) {
        // Autoplay waits briefly for this addon next time (see autoplaySelection).
        rememberSuccessfulSource(currentStreamRef.current);
        markHomeScreenTipEligible();
      }
    };
    video.addEventListener("playing", onPlaying);
    video.addEventListener("loadeddata", onReady);
    return () => {
      video.removeEventListener("playing", onPlaying);
      video.removeEventListener("loadeddata", onReady);
    };
  }, [stream.url, remuxRestartKey, liveTv]);

  // Recover only when the decoder delivers no video frames. Pixel brightness
  // cannot distinguish unsupported video from a legitimate dark scene.
  useEffect(() => {
    if (!booted || liveTv || isAudioOnlySource(stream.url)) return undefined;
    const video = videoRef.current;
    if (!video) return undefined;
    return monitorVideoFrames(video, () => {
      video.pause();
      recordBrowserPlaybackFailure(stream, "This browser could not decode video frames from the selected source.", !!stream.transcoded);
      if (!stream.transcoded && canProviderTranscode(stream)) {
        onToast("No video frames decoded. Requesting provider conversion for this source.");
        onSelectStream(stream, { forceTranscode: true, forceBrowser: true });
        return;
      }
      failureDiagnosticRef.current = { failure_kind: "format", phase: "playback" };
      if (tryNextSource()) return;
      setErrorDetail("This browser could not decode video frames from the selected source.");
      setBuffering(false);
      setShowControls(true);
      setError(true);
      onToast("Audio is playing but the browser cannot render this video's format. Choose a non-DV version or use a compatible external player.");
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [booted, stream.url, remuxRestartKey, liveTv]);

  // Video but no sound: Chromium dropped an audio track it cannot decode
  // (typically E-AC-3 / DTS in a file whose name does not say so). Keep the
  // position and switch to in-browser audio conversion (the remux path decodes
  // AC-3/E-AC-3/DTS and re-encodes AAC); otherwise the provider's conversion,
  // the next source, or a clear message — never silent playback.
  useEffect(() => {
    if (!booted || liveTv || stream.remux || stream.transcoded || isAudioOnlySource(stream.url) || streamTransport(stream) !== "file") return undefined;
    const video = videoRef.current;
    if (!video) return undefined;
    return monitorAudioDecode(video, () => {
      const playhead = video.currentTime;
      if (playhead > 5) resumeAtRef.current = playhead;
      if (canTryRemux(stream)) {
        onToast("This file's audio can't play directly in this browser. Converting the audio…");
        onSelectStream({ ...stream, convertAudio: true }, { forceRemux: true, forceBrowser: true });
        return;
      }
      if (canProviderTranscode(stream)) {
        onToast("This file's audio can't play in this browser. Requesting provider conversion…");
        onSelectStream(stream, { forceTranscode: true, forceBrowser: true });
        return;
      }
      video.pause();
      failureDiagnosticRef.current = { failure_kind: "format", phase: "playback" };
      if (tryNextSource()) return;
      onToast("This source's audio can't play in this browser. Choose another source or use an external player.");
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [booted, stream.url, stream.remux, remuxRestartKey, liveTv]);

  useAiSubtitleTranslation({ activeSubtitle, aiAvailable, aiSubsActive, aiTargetName, onToast, setAiError, setAiTranslating, settings, stream, subtitleState, translatorRef, videoRef });

  // Adaptive downswitch: repeated mid-play buffering means the connection
  // can't sustain this file's bitrate (common with huge remuxes over VPN) —
  // switch once to a substantially lighter playable version of the same title
  // instead of letting the viewer stutter through it.
  useEffect(() => {
    if (!booted) return undefined;
    const video = videoRef.current;
    if (!video) return undefined;
    if (stream.remux) return undefined;
    const lighterSource = () => {
      const current = currentStreamRef.current;
      const currentSize = streamSizeBytes(current);
      return sourceListRef.current.find((candidate) => {
        if (!candidate.url || candidate.url === current.url || candidate.url === current.originalUrl) return false;
        if (isUncachedDebridStream(candidate)) return false;
        const mode = streamPlayability(candidate).mode;
        if (mode !== "direct" && mode !== "remux") return false;
        const size = streamSizeBytes(candidate);
        return size > 0 && (!currentSize || size < currentSize * 0.55);
      });
    };

    return monitorPlaybackStall(video, {
      live: liveTv,
      reload: (position) => transportRef.current?.reload(liveTv ? undefined : position),
      nudge: liveTv ? () => { transportRef.current?.goLive(); } : undefined,
      onRecover: () => onToast("Playback stalled — trying to recover…"),
      onFailure: () => {
        if (liveTv && liveStallRestartsRef.current < 2) {
          liveStallRestartsRef.current += 1;
          onToast("Reconnecting to the channel…");
          setRemuxRestartKey((key) => key + 1);
          return;
        }
        const lighter = liveTv ? undefined : lighterSource();
        if (lighter && currentStreamRef.current.autoSelect) {
          onToast("Your connection can't keep up with this version — switching to a lighter one.");
          onSelectStream({ ...lighter, autoSelect: true }, { forceBrowser: true });
          return;
        }
        // Nothing lighter: surface the failure instead of buffering silently so
        // the source list (and the external-player options) are reachable.
        failureDiagnosticRef.current = { failure_kind: "timeout", phase: "playback" };
        if (liveTv) {
          setLiveFailure({ kind: "stopped", title: "The channel stopped", message: "The channel stopped sending video and could not be reconnected. Retry, or try again later." });
          setErrorDetail("The channel stopped sending video and could not be reconnected. Retry, or try again later.");
          setBuffering(false); setError(true); setShowControls(true);
          return;
        }
        setErrorDetail("This source stopped responding. Pick another source to continue.");
        setBuffering(false);
        setError(true);
        setShowControls(true);
        onToast("This source stopped responding. Pick another source to continue.");
      }
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [booted, stream.url, liveTv, remuxRestartKey]);

  useEffect(() => {
    setBootLogo(null);
    if (!item || item.id <= 0 || item.isHomeServer) return undefined;
    let active = true;
    void getLogoUrl({ mediaType: item.mediaType, id: item.id }).then((url) => {
      if (active) setBootLogo(url);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item?.id, item?.mediaType, item?.isHomeServer, item]);

  // Same ranked order as the details source picker — the panel and the
  // auto-hop must both walk sources best-first, not raw addon order.
  const sourceGroups = useMemo(() => {
    const playable = streams.filter((candidate) => Boolean(candidate.url));
    // The exact grouping and order of the details source picker: what plays
    // in this browser first (direct, then conversion), then external-only.
    return groupSourcesForPlayback(playable.length ? playable : [stream]);
  }, [streams, stream]);
  const sourceList = useMemo(() => [...sourceGroups.here, ...sourceGroups.external], [sourceGroups]);

  // When a source is truly dead (URL ladder + remux exhausted), hop to the next
  // browser-playable source automatically instead of stranding the viewer on an
  // error screen. Capped, and each failed URL is remembered to prevent loops.
  // Reads through refs so its identity never changes — the playback effect must
  // not restart when the progressive source list grows.
  const failedSourceUrlsRef = useRef(new Set<string>());
  const failedAddonStrikesRef = useRef(new Map<string, number>());
  const autoSourceHopsRef = useRef(0);
  // Where to resume when the player re-attaches for the SAME title: a source
  // hop, a remux escalation or a stall reload. Without this every switch
  // restarted at 0, which is punishing 40 minutes into a film.
  const resumeAtRef = useRef(stream.resumePositionSeconds ?? 0);
  const sourceListRef = useRef(sourceList);
  sourceListRef.current = sourceList;
  const currentStreamRef = useRef(stream);
  currentStreamRef.current = stream;
  useEffect(() => {
    failedSourceUrlsRef.current = new Set();
    failedAddonStrikesRef.current = new Map();
    autoSourceHopsRef.current = 0;
  }, [item?.id, selectedEpisode?.season, selectedEpisode?.episode]);
  const tryNextSource = useCallback(() => {
    if (liveTv || !currentStreamRef.current.autoSelect || autoSourceHopsRef.current >= sourceListRef.current.length) return false;
    // Carry the watched position across the switch — the replacement source is
    // the same title, so restarting at 0 loses the user's place.
    const playhead = videoRef.current?.currentTime ?? 0;
    if (playhead > 5) resumeAtRef.current = playhead;
    const current = currentStreamRef.current;
    if (current.url) failedSourceUrlsRef.current.add(current.url);
    if (current.originalUrl) failedSourceUrlsRef.current.add(current.originalUrl);
    // Two failures from the same addon usually means its host is down/CORS-
    // blocked for the whole list (e.g. 40 dead file-host links in a row) —
    // skip the rest of that addon and jump to the next provider.
    const addonKey = current.addonId || current.addonName || "";
    if (addonKey) failedAddonStrikesRef.current.set(addonKey, (failedAddonStrikesRef.current.get(addonKey) ?? 0) + 1);
    recordBrowserPlaybackFailure(current, "This source failed during browser playback", true);
    const pick = (skipStruckAddons: boolean) => browserAutoplayCandidates(sourceListRef.current, failedSourceUrlsRef.current).find((candidate) => {
      if (!candidate.url || failedSourceUrlsRef.current.has(candidate.url)) return false;
      // Uncached debrid torrents would stall on a server-side download.
      if (isUncachedDebridStream(candidate)) return false;
      if (skipStruckAddons) {
        const key = candidate.addonId || candidate.addonName || "";
        if (key && (failedAddonStrikesRef.current.get(key) ?? 0) >= 2) return false;
      }
      // Transcode counts too: a debrid source whose codecs this browser cannot
      // decode still plays once the provider transcodes it, so excluding it
      // threw away working fallbacks and dead-ended on the error screen.
      const mode = streamPlayability(candidate).mode;
      return mode === "direct" || mode === "remux" || mode === "transcode";
    });
    const next = pick(true) ?? pick(false);
    if (!next) return false;
    reportFailedAttempt(current, "retried");
    autoSourceHopsRef.current += 1;
    onToast(`Source failed — trying ${next.source || next.addonName || "the next source"}`);
    onSelectStream({ ...next, autoSelect: true, resumePositionSeconds: resumeAtRef.current }, { forceBrowser: true });
    return true;
  }, [liveTv, onSelectStream, onToast, reportFailedAttempt]);
  const badges = useMemo(() => {
    if (liveTv) return ["LIVE"];
    const base = qualityBadges(stream);
    if (stream.remux) return ["REMUX", ...base];
    return stream.transcoded ? ["TRANSCODE", ...base] : base;
  }, [stream, liveTv]);
  const mediaMeta = [subtitleLabel, stream.quality, stream.size, stream.addonName].filter(Boolean).join(" - ");
  const playbackIdentity = JSON.stringify([stream.url, stream.remux, stream.transcoded, stream.transport, stream.behaviorHints?.proxyHeaders?.request]);
  useEffect(() => {
    const manual = manualSubtitle.current;
    if (manual && manual.source === stream.url && manual.preference === settings.defaultSubtitle) {
      setActiveSubtitle(manual.url ? (stream.subtitles ?? []).findIndex(subtitle => subtitle.url === manual.url) : -1);
    } else {
      manualSubtitle.current = null;
      const preferred = defaultSubtitleIndex(stream, settings.defaultSubtitle);
      const language = languageCode(settings.defaultSubtitle);
      const english = (stream.subtitles ?? []).findIndex(subtitle => languageCode(subtitle.lang) === "en");
      const translate = !!(aiAvailable && settings.aiAutoSelect && !liveTv && language && preferred < 0 && english >= 0);
      setActiveSubtitle(translate ? english : preferred);
      setAiSubsActive(translate);
    }
  }, [stream.subtitles, stream.url, settings.defaultSubtitle, aiAvailable, settings.aiAutoSelect, liveTv]);

  usePlaybackEngine({ autoSourceHopsRef, currentStreamRef, failureDiagnosticRef, lastSavedRef, liveSourceOverrideRef, liveTv, liveXtreamOrigin, notify, onSelectStream, onToast, playbackIdentity, playbackRate, refreshLiveSource, remuxAudioIndexRef, remuxRestartKey, resumeAtRef, setBootStatus, setBuffering, setError, setErrorDetail, setLiveFailure, setPlayBlocked, setRemuxAudioIndex, setRemuxTracks, setShowControls, settings, setTransportTracks, sourceListRef, stream, transportRef, tryNextSource, videoRef });

  useEffect(() => {
    const video = videoRef.current;
    if (!video) return undefined;
    const onTime = () => {
      setCurrent(video.currentTime);
      // Keep the buffer bar tied to the range under the playhead; `progress`
      // alone only fires while bytes are arriving, so it goes stale on a seek.
      setBuffered(bufferedEndAt(video.buffered, video.currentTime));
      setBufferAheadSec(bufferedAhead(video.buffered, video.currentTime));
    };
    const onDur = () => setDuration(video.duration || 0);
    const onPlay = () => { setPlaying(true); setPlayBlocked(false); setEnded(false); };
    const onPause = () => setPlaying(false);
    const onWaiting = () => setBuffering(true);
    const onPlaying = () => setBuffering(false);
    // Use the range holding the playhead, not the last range: after seeking
    // backwards the last range is somewhere ahead, and the buffer bar claimed
    // far more was loaded than really was.
    // `progress` keeps firing while stalled (bytes still arriving even though
    // the clock is frozen), which is exactly when the readout matters.
    const onProgress = () => {
      setBuffered(bufferedEndAt(video.buffered, video.currentTime));
      setBufferAheadSec(bufferedAhead(video.buffered, video.currentTime));
    };
    const onVol = () => { setVolume(video.volume); setMuted(video.muted); };
    video.addEventListener("timeupdate", onTime);
    video.addEventListener("durationchange", onDur);
    video.addEventListener("loadedmetadata", onDur);
    video.addEventListener("play", onPlay);
    video.addEventListener("pause", onPause);
    video.addEventListener("waiting", onWaiting);
    video.addEventListener("playing", onPlaying);
    video.addEventListener("canplay", onPlaying);
    video.addEventListener("progress", onProgress);
    video.addEventListener("volumechange", onVol);
    return () => {
      video.removeEventListener("timeupdate", onTime);
      video.removeEventListener("durationchange", onDur);
      video.removeEventListener("loadedmetadata", onDur);
      video.removeEventListener("play", onPlay);
      video.removeEventListener("pause", onPause);
      video.removeEventListener("waiting", onWaiting);
      video.removeEventListener("playing", onPlaying);
      video.removeEventListener("canplay", onPlaying);
      video.removeEventListener("progress", onProgress);
      video.removeEventListener("volumechange", onVol);
    };
  }, [stream]);

  const { embeddedTracks, embeddedChoice, selectEmbedded, subtitleDelayMs, changeSubtitleDelay } = useSubtitleTracks({ activeSubtitle, item, manualSubtitle, setActiveSubtitle, setAiSubsActive, setEmbeddedChoiceRef, setSubtitleState, settings, stream, subtitleRetry, videoRef });

  // Reset the manual remux audio override when the source changes so each new
  // source starts from its own automatic best-track choice.
  useEffect(() => {
    remuxAudioIndexRef.current = stream.remuxAudioIndex ?? -1;
  }, [stream.url, stream.remuxAudioIndex]);

  useProgressTracking({ activeProfileId, addons, canAdvance, item, lastSavedRef, liveTv, nextDismissed, onAdvanceRef, onToast, selectedEpisode, setEnded, setNextCountdown, setShowControls, settings, stream, upNextShownRef, videoRef });

  // Controls hide ~3 s after playback starts, after a tap, a key or a mouse
  // move — not only after mouse movement — and never while paused, with a
  // panel open, or on an error/end screen.
  const hideBlockedRef = useRef(false);
  hideBlockedRef.current = Boolean(activePanel) || error || ended;
  const hideControlsNow = useCallback(() => {
    if (hideTimer.current) clearTimeout(hideTimer.current);
    setShowControls(false);
    // Focus must not stay on a hidden button (a remote OK would press it).
    const container = containerRef.current;
    if (container && container.contains(document.activeElement) && document.activeElement !== container) container.focus({ preventScroll: true });
  }, []);
  const flashControls = useCallback(() => {
    setShowControls(true);
    if (hideTimer.current) clearTimeout(hideTimer.current);
    hideTimer.current = setTimeout(() => {
      const video = videoRef.current;
      if (video && !video.paused && !hideBlockedRef.current) hideControlsNow();
    }, 3000);
  }, [hideControlsNow]);
  useEffect(() => {
    const video = videoRef.current;
    if (!video) return undefined;
    // Once per source: a rebuffer must not pop the bar back up.
    const arm = () => flashControls();
    video.addEventListener("playing", arm, { once: true });
    return () => video.removeEventListener("playing", arm);
  }, [flashControls, stream.url]);
  useEffect(() => () => { if (hideTimer.current) clearTimeout(hideTimer.current); }, []);
  // A modal player: focus moves in when it opens and back to whatever opened
  // it (the details Play button) when it closes.
  const playerOpenerRef = useRef<HTMLElement | null>(null);
  useEffect(() => {
    const node = containerRef.current;
    const active = document.activeElement;
    if (active instanceof HTMLElement && !node?.contains(active)) playerOpenerRef.current = active;
    node?.focus({ preventScroll: true });
    return () => {
      // Only on a real close (React dev re-runs effects without unmounting).
      window.setTimeout(() => {
        const opener = playerOpenerRef.current;
        if (node && !node.isConnected && opener?.isConnected) opener.focus({ preventScroll: true });
      }, 0);
    };
  }, []);

  const retryPlayback = useCallback(() => {
    setError(false); setErrorDetail(""); setBuffering(true);
    const position = videoRef.current?.currentTime ?? 0;
    if (position > 0) resumeAtRef.current = position + (stream.playbackSession?.startOffset ?? 0);
    // Terminal failure stops a home-server session. Ask the server for a fresh
    // playback session instead of trying to reopen its stopped conversion URL.
    if (stream.homeServer) selectStream({ ...stream, resumePositionSeconds: resumeAtRef.current || stream.resumePositionSeconds || 0 }, { forceBrowser: true });
    else setRemuxRestartKey((key) => key + 1);
  }, [stream, selectStream]);

  const togglePlay = useCallback(() => {
    const video = videoRef.current;
    if (!video) return;
    if (video.paused) {
      setError(false);
      setBuffering(true);
      if (video.ended) video.currentTime = 0;
      const source = video.currentSrc;
      const attempt = video.play();
      void attempt?.catch((reason: unknown) => {
        // A source switch/close can reject the old play promise. It must not
        // change the next source's volume or playback state.
        if (videoRef.current !== video || video.currentSrc !== source
          || (reason instanceof DOMException && reason.name === "AbortError")) return;
        setBuffering(false);
        setShowControls(true);
        if (video.error) {
          setError(true);
          return;
        }
        if (reason instanceof DOMException && reason.name === "NotAllowedError") setPlayBlocked(true);
        else setError(true);
      });
    }
    else video.pause();
    flashControls();
  }, [flashControls]);

  const seekBy = useCallback((delta: number) => {
    const video = videoRef.current;
    if (!video) return;
    video.currentTime = Math.max(0, Math.min((video.duration || 0), video.currentTime + delta));
    setSkipOverlay(delta);
    if (skipTimer.current) clearTimeout(skipTimer.current);
    skipTimer.current = setTimeout(() => setSkipOverlay(null), 780);
    flashControls();
  }, [flashControls]);

  const toggleFullscreen = useCallback(() => {
    // iPhone Safari has no element fullscreen; this falls back to Apple's
    // native video player (webkitEnterFullscreen). Subtitles are native
    // <track>s, so they show there too — in the iOS system caption style.
    // Vision Pro (and other no-hover Apple WebKit): fullscreen the <video> so
    // the system player takes over; elsewhere the container, keeping ARVIO's
    // controls and styled subtitles (lib/fullscreen.ts, workstream D).
    const request = prefersVideoElementFullscreen() && !isFullscreenActive(videoRef.current)
      ? requestVideoFullscreen(videoRef.current)
      : toggleFullscreenFor(containerRef.current, videoRef.current);
    void request.then((result) => {
      // Phones turn to landscape in fullscreen where the browser allows a lock.
      if (result === "entered" && window.matchMedia?.("(pointer: coarse)").matches) lockLandscape();
      else if (result === "exited") unlockOrientation();
      if (result === "failed") {
        // Fullscreen demands a real click/tap (transient activation), which
        // controller input can never mint — say so instead of doing nothing.
        onToast("Fullscreen needs a real click or tap first — or press F11.");
      } else if (result === "needs-metadata") {
        onToast("Fullscreen is available once the video starts.");
      } else if (result === "unsupported") {
        onToast("This browser does not support fullscreen video.");
      }
    });
  }, [onToast]);

  const { pipAvailable, pipActive, airPlayAvailable, airPlayAllowed, togglePip } = usePlatformControls({ canAdvance, item, liveTv, onAdvance, onToast, seekBy, stream, subtitleLabel, title, translateUi, videoRef });

  const openPanel = useCallback((panel: Exclude<PlayerPanel, null>) => {
    const next = activePanel === panel ? null : panel;
    setActivePanel(next);
    // Keep network work outside state updaters (React can replay them).
    if (next === "audio" && audioProbeState === "idle"
      && remuxTracks.length === 0 && transportTracks.audioTracks.length === 0) probeAudioTracks();
    setShowControls(true);
  }, [activePanel, audioProbeState, remuxTracks.length, transportTracks.audioTracks.length, probeAudioTracks]);

  const openExternal = useCallback((player: "vlc" | "infuse", chosenStream: StreamSource) => {
    const selectedStream = withLiveLink(chosenStream);
    if (!selectedStream.url) {
      onToast("This source has no direct URL for an external player.");
      return;
    }
    onToast(
      player === "infuse"
        ? "Opening in Infuse..."
        : externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : "Opening in VLC..."
    );
    // The deep link must fire synchronously — custom-scheme navigation is
    // blocked after an await. Use the prefetch-cached CDN url when present,
    // else the raw stream (VLC follows the redirect itself).
    const cached = parseDebridStream(selectedStream.url) ? cachedDebridDirectUrl(selectedStream.url) : null;
    const target = cached ? { ...selectedStream, url: cached, originalUrl: selectedStream.url } : selectedStream;
    createPendingExternalPlayback({
      player,
      item,
      stream: target,
      title,
      profileId: activeProfileId,
      season: selectedEpisode?.season ?? item?.seasonNumber ?? null,
      episode: selectedEpisode?.episode ?? item?.episodeNumber ?? null
    });
    // The in-player stream already carries fetched subtitles; pass the user's
    // preferred language so VLC/Infuse pick the right one.
    openExternalPlayer(player, target, title, settings.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player, entry: "player" }, true);
  }, [activeProfileId, item, onToast, selectedEpisode, title, settings.defaultSubtitle, withLiveLink]);

  // Android: open in whichever installed player the user picks (system chooser).
  const openAnyPlayer = useCallback((chosenStream: StreamSource) => {
    const selectedStream = withLiveLink(chosenStream);
    if (!selectedStream.url) {
      onToast("This source has no direct URL for an external player.");
      return;
    }
    onToast("Opening in your player…");
    const cached = parseDebridStream(selectedStream.url) ? cachedDebridDirectUrl(selectedStream.url) : null;
    const target = cached ? { ...selectedStream, url: cached, originalUrl: selectedStream.url } : selectedStream;
    createPendingExternalPlayback({
      player: "vlc",
      item,
      stream: target,
      title,
      profileId: activeProfileId,
      season: selectedEpisode?.season ?? item?.seasonNumber ?? null,
      episode: selectedEpisode?.episode ?? item?.episodeNumber ?? null
    });
    openInAnyPlayer(target, title, settings.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player: "chooser", entry: "player" }, true);
  }, [activeProfileId, item, onToast, selectedEpisode, title, settings.defaultSubtitle, withLiveLink]);

  const copyUrl = useCallback(async (selectedStream: StreamSource) => {
    const copied = await copyStreamUrl(withLiveLink(selectedStream)).catch(() => false);
    onToast(copied ? "Stream URL copied." : "Could not copy this stream URL.");
  }, [onToast, withLiveLink]);

  useEffect(() => {
    const video = videoRef.current;
    if (!video) return undefined;
    // Standard, webkit-prefixed and iOS native video fullscreen all report here.
    return onFullscreenChange(video, () => setFullscreen(isFullscreenActive(video)));
  }, []);

  // Focus follows panels: into the list when one opens (the selected row
  // first), back to its toggle button when it closes.
  const panelOpenerRef = useRef<HTMLElement | null>(null);
  useEffect(() => {
    const container = containerRef.current;
    if (!container) return undefined;
    if (activePanel) {
      const opener = document.activeElement;
      if (opener instanceof HTMLElement && !opener.closest(".player-side-panel")) panelOpenerRef.current = opener;
      const timer = window.setTimeout(() => {
        const panel = container.querySelector<HTMLElement>(".player-side-panel");
        const target = panel?.querySelector<HTMLElement>(".is-active button, button.is-active, [aria-current] button, .src-row-main:not([disabled])")
          ?? panel?.querySelector<HTMLElement>(".player-panel-list button, .player-settings-panel button, .player-panel-list .src-row-main");
        target?.focus({ preventScroll: false });
      }, 0);
      return () => window.clearTimeout(timer);
    }
    const opener = panelOpenerRef.current;
    panelOpenerRef.current = null;
    if (opener?.isConnected) opener.focus({ preventScroll: true });
    return undefined;
  }, [activePanel]);

  const revealControls = useCallback(() => {
    flashControls();
    const container = containerRef.current;
    if (!container) return;
    // Land on the most useful control for the current screen.
    const target = container.querySelector<HTMLElement>(".player-boot-actions button, .player-error-actions button, .player-end-actions button, .player-upnext button")
      ?? container.querySelector<HTMLElement>(".player-play-btn");
    window.setTimeout(() => target?.focus({ preventScroll: true }), 0);
  }, [flashControls]);

  usePlayerKeys({ activePanel, booted, containerRef, dock, ended, error, flashControls, hideControlsNow, liveTv, onClose, openPanel, revealControls, seekBy, setActivePanel, showControls, toggleFullscreen, togglePlay, videoRef });

  const { activeSkip, showSkip, skipNow, nextEpisode, showUpNext, dismissUpNext, playNextNow } = useSkipAndUpNext({ booted, canAdvance, current, duration, ended, error, flashControls, item, liveTv, metadataPriority, nextCountdown, nextDismissed, onAdvance, selectedEpisode, setNextCountdown, setUpNextDismissed, stream, upNextDismissed, upNextShownRef, videoRef });

  // ---------- Touch: tap toggles the controls, double-tap seeks ±10 s ----------
  const lastTap = useRef<{ at: number; x: number } | null>(null);
  const tapTimer = useRef<number | undefined>(undefined);
  useEffect(() => () => window.clearTimeout(tapTimer.current), []);
  const onSurfacePointerUp = (event: React.PointerEvent<HTMLVideoElement>) => {
    if (event.pointerType === "mouse") { togglePlay(); return; }
    const rect = event.currentTarget.getBoundingClientRect();
    const x = (event.clientX - rect.left) / Math.max(1, rect.width);
    const now = Date.now();
    const previous = lastTap.current;
    if (previous && now - previous.at < 300 && Math.abs(previous.x - x) < 0.2 && (x < 0.38 || x > 0.62)) {
      window.clearTimeout(tapTimer.current);
      lastTap.current = null;
      if (!liveTv) seekBy(x < 0.5 ? -SKIP_SECONDS : SKIP_SECONDS);
      return;
    }
    lastTap.current = { at: now, x };
    // Decide from the state at the tap, before compatibility mouse events land.
    const wasVisible = showControlsRef.current;
    window.clearTimeout(tapTimer.current);
    tapTimer.current = window.setTimeout(() => {
      lastTap.current = null;
      if (wasVisible && !videoRef.current?.paused) hideControlsNow();
      else flashControls();
    }, 230);
  };
  const showControlsRef = useRef(showControls);
  showControlsRef.current = showControls;

  // ---------- Platform details ----------
  const appleMobile = useMemo(() => isAppleMobile(), []);
  const externalOptions = useMemo(() => externalPlayerOptions(), []);
  useEffect(() => {
    const video = videoRef.current as (HTMLVideoElement & { autoPictureInPicture?: boolean }) | null;
    if (!video) return;
    // Safari: going home / switching apps continues in picture in picture.
    video.setAttribute("autopictureinpicture", "");
    try { video.autoPictureInPicture = true; } catch { /* unsupported */ }
  }, []);
  useEffect(() => {
    if (!fullscreen) unlockOrientation();
  }, [fullscreen]);
  // AirPlay sends a URL the receiver fetches itself; a file converted inside
  // the browser exists only here, so say why AirPlay is off instead of hiding it.
  const airPlayBlockedReason = airPlayAvailable && !airPlayAllowed
    ? (stream.remux ? "AirPlay is off while this file is converted in your browser. Open it in Infuse or VLC to AirPlay it, or pick a source that plays directly."
      : "AirPlay is off for this stream because the browser relays it. Open it in Infuse or VLC to AirPlay it.")
    : null;
  const openExternalOption = (option: ExternalPlayerOption, selectedStream: StreamSource) => {
    if (option.id === "chooser") openAnyPlayer(selectedStream);
    else openExternal(option.id, selectedStream);
  };
  const subtitleGroups = useMemo(() => groupSubtitles(stream.subtitles ?? [], { preferred: settings.defaultSubtitle, releaseName: stream.behaviorHints?.filename ?? stream.description ?? stream.source }),
    [stream.subtitles, settings.defaultSubtitle, stream.behaviorHints?.filename, stream.description, stream.source]);
  const showEndScreen = ended && !liveTv && !error && !(canAdvance && nextEpisode && !upNextDismissed);

  // Subtitle rendering honours the user's style setting: boxed, outlined,
  // drop-shadowed, or raised — same options as the Android app.
  const cueStyle = {
    background: "background: rgba(0,0,0,0.65);",
    outline: "background: transparent; text-shadow: -1px -1px 2px #000, 1px -1px 2px #000, -1px 1px 2px #000, 1px 1px 2px #000;",
    shadow: "background: transparent; text-shadow: 2px 2px 5px rgba(0,0,0,0.95);",
    raised: "background: transparent; text-shadow: 0 1px 0 #000, 0 2px 4px rgba(0,0,0,0.75);"
  }[settings.subtitleStyle] ?? "background: rgba(0,0,0,0.65);";
  const cueCss = `.player-overlay video::cue { color: ${settings.subtitleColor}; font-size: ${Math.max(60, Math.min(300, settings.subtitleSize))}%; ${cueStyle} }`;

  // Vertical placement: browsers render captions near the very bottom by
  // default (often behind the control bar). `line` is a percentage from the top
  // — lower number = higher on screen. Applied per cue on the active track.
  const subtitleLinePercent = { bottom: 84, low: 78, medium: 68, high: 56 }[settings.subtitleOffset] ?? 84;
  useEffect(() => {
    const track = subtitleState.track;
    if (!track) return undefined;
    const applyLine = () => positionSubtitleCues(track, subtitleLinePercent, subtitleDelayMs);
    applyLine();
    track.addEventListener("cuechange", applyLine);
    return () => track.removeEventListener("cuechange", applyLine);
  }, [subtitleState.track, subtitleLinePercent, subtitleDelayMs]);
  // While scrubbing the bar follows the drag, not the (not yet moved) playhead.
  const scrubDisplayTime = scrubTo ?? current;
  const pct = duration > 0 ? (scrubDisplayTime / duration) * 100 : 0;
  const bufPct = duration > 0 ? (buffered / duration) * 100 : 0;

  return (
    <section
      ref={containerRef}
      className={`player-overlay ${dock.docked ? "player-docked" : ""} ${showControls || activePanel ? "controls-on" : "controls-off"} ${pipAvailable || airPlayAvailable ? "has-platform-actions" : ""} ${!playing && booted && !error ? "is-paused" : ""} ${appleMobile ? "is-apple-mobile" : ""} ${showEndScreen ? "is-ended" : ""}`}
      style={dock.style}
      onPointerMove={(event) => { if (event.pointerType === "mouse") flashControls(); }}
      role="dialog"
      aria-modal={dock.docked ? undefined : true}
      aria-label={translateUi("Player: {value0}", { value0: title })}
      tabIndex={-1}
    >
      <style>{cueCss}</style>
      <video ref={videoRef} autoPlay playsInline preload="auto" onPointerUp={onSurfacePointerUp} poster={item?.backdrop ?? undefined} />
      <LiveDockControls buffering={buffering} dock={dock} error={error} onClose={onClose} playing={playing} togglePlay={togglePlay} />

      <PlayerStatusLayers booted={booted} bootLogo={bootLogo} bootStatus={bootStatus} bufferAheadSec={bufferAheadSec} buffering={buffering} error={error} errorDetail={errorDetail} externalOptions={externalOptions} item={item} liveFailure={liveTv ? liveFailure : null} liveTv={liveTv} onClose={onClose} onSelectStream={onSelectStream} openExternalOption={openExternalOption} openPanel={openPanel} playBlocked={playBlocked} playing={playing} retryPlayback={retryPlayback} showEndScreen={showEndScreen} skipOverlay={skipOverlay} stream={stream} subtitleLabel={subtitleLabel} title={title} togglePlay={togglePlay} />
      <PlayerSkipUpNext activeSkip={activeSkip} dismissUpNext={dismissUpNext} nextCountdown={nextCountdown} nextEpisode={nextEpisode} playNextNow={playNextNow} showSkip={showSkip} showUpNext={showUpNext} skipNow={skipNow} />
      <PlayerEndScreen item={item} onClose={onClose} onOpenDetails={onOpenDetails} showEndScreen={showEndScreen} title={title} togglePlay={togglePlay} />

      <PlayerTopBar airPlayAllowed={airPlayAllowed} airPlayAvailable={airPlayAvailable} airPlayBlockedReason={airPlayBlockedReason} mediaMeta={mediaMeta} onClose={onClose} onToast={onToast} pauseOverview={pauseOverview} pipActive={pipActive} pipAvailable={pipAvailable} playing={playing} stream={stream} title={title} togglePip={togglePip} videoRef={videoRef} />

      <PlayerSidePanel activePanel={activePanel} activeSubtitle={activeSubtitle} aiAvailable={aiAvailable} aiError={aiError} aiSubsActive={aiSubsActive} aiTargetName={aiTargetName} aiTranslating={aiTranslating} audioProbeState={audioProbeState} bufferAheadSec={bufferAheadSec} buffering={buffering} changeSubtitleDelay={changeSubtitleDelay} copyUrl={copyUrl} embeddedChoice={embeddedChoice} embeddedTracks={embeddedTracks} error={error} externalOptions={externalOptions} onSelectStream={onSelectStream} onToast={onToast} openExternalOption={openExternalOption} playbackRate={playbackRate} probeAudioTracks={probeAudioTracks} remuxAudioIndex={remuxAudioIndex} remuxTracks={remuxTracks} selectEmbedded={selectEmbedded} selectSubtitle={selectSubtitle} setActivePanel={setActivePanel} setAiSubsActive={setAiSubsActive} setPlaybackRate={setPlaybackRate} setSubtitleRetry={setSubtitleRetry} settings={settings} sourceGroups={sourceGroups} stream={stream} subtitleDelayMs={subtitleDelayMs} subtitleGroups={subtitleGroups} subtitleState={subtitleState} switchRemuxAudio={switchRemuxAudio} transportRef={transportRef} transportTracks={transportTracks} updateSettings={updateSettings} videoRef={videoRef} />

      <PlayerControlsBar activePanel={activePanel} appleMobile={appleMobile} badges={badges} bufPct={bufPct} canAdvance={canAdvance} duration={duration} error={error} fullscreen={fullscreen} hoverPct={hoverPct} hoverTime={hoverTime} liveTv={liveTv} muted={muted} openPanel={openPanel} pct={pct} playing={playing} playNextNow={playNextNow} scrubDisplayTime={scrubDisplayTime} seekBy={seekBy} setHoverPct={setHoverPct} setHoverTime={setHoverTime} setScrubTo={setScrubTo} toggleFullscreen={toggleFullscreen} togglePlay={togglePlay} transportRef={transportRef} videoRef={videoRef} volume={volume} />
    </section>
  );
}

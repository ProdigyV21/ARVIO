"use client";

import {
  ArrowLeft,
  AudioLines,
  Check,
  Copy,
  ExternalLink,
  Folder,
  Loader2,
  Maximize,
  Minimize,
  Pause,
  Play,
  RotateCcw,
  RotateCw,
  Settings,
  SkipForward,
  Subtitles,
  Volume2,
  VolumeX,
  X
} from "lucide-react";
import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { useLivePlayerDock } from "./useLivePlayerDock";
import { config } from "@/lib/config";
import { createPendingExternalPlayback } from "@/lib/externalPlayback";
import { isLiveStreamOrSportsItem, saveProgress, saveWatchedState } from "@/lib/cloud";
import { cachedDebridDirectUrl, invalidateDebridDirectUrl, isUncachedDebridStream, parseDebridStream, resolveDebridDirectUrl } from "@/lib/debrid";
import type { RemuxAudioTrack } from "@/lib/remux";
import { copyStreamUrl, externalLaunchMode, openExternalPlayer, openInAnyPlayer } from "@/lib/externalPlayers";
import { proxiedUrl } from "@/lib/http";
import { attachPlayback, type PlaybackHandle, type PlaybackTracks, type PlaybackError } from "@/lib/player";
import { resolverMediaUrl, resolverSubtitleUrl } from "@/lib/resolver";
import { sourcePickerScore, streamSizeBytes } from "@/lib/sourceRank";
import { playbackPlan, streamPlayability, canTryRemux, canProviderTranscode, hasDolbyVision, recordBrowserPlaybackFailure } from "@/lib/streamCompatibility";
import { reportHomeServerPlayback, updateHomeServerPlaybackPosition } from "@/lib/homeServerPlayback";
import {
  bufferedAhead,
  bufferedEndAt,
  classifyMediaError,
  isStalled,
  monitorVideoFrames,
  nextStallAction,
} from "@/lib/playerRecovery";
import { authClient, useApp } from "@/lib/store";
import { trackPremiumDaily, trackPremiumEvent, trackPremiumMilestone } from "@/lib/premiumAnalytics";
import { syncClient } from "@/lib/sync";
import { SubtitleTranslator, subtitleLanguageName } from "@/lib/subtitleAi";
import { getLogoUrl } from "@/lib/tmdb";
import type { AppSettings, InstalledAddon, MediaItem, StreamSource } from "@/lib/types";

type PlayerPanel = "sources" | "subtitles" | "audio" | "settings" | null;

/** How often the stall watchdog samples playback progress. */
const STALL_POLL_MS = 1_000;

/**
 * Seconds of no forward progress before the remux path is declared dead.
 *
 * Generous because a remux legitimately spends time downloading and
 * repackaging before the first frame — but bounded, because it used to hang
 * indefinitely with no error and no timeout.
 */
const REMUX_STUCK_TICKS = 25;

function youTubeId(url: string): string | null {
  const match = url.match(/(?:youtube\.com\/(?:watch\?v=|embed\/)|youtu\.be\/)([\w-]{11})/);
  return match?.[1] ?? null;
}

function fmt(seconds: number): string {
  if (!Number.isFinite(seconds) || seconds < 0) return "0:00";
  const total = Math.floor(seconds);
  const h = Math.floor(total / 3600);
  const m = Math.floor((total % 3600) / 60);
  const s = total % 60;
  return h > 0 ? `${h}:${String(m).padStart(2, "0")}:${String(s).padStart(2, "0")}` : `${m}:${String(s).padStart(2, "0")}`;
}

function isSameStream(a: StreamSource, b: StreamSource) {
  return (
    a.url === b.url &&
    a.infoHash === b.infoHash &&
    a.fileIdx === b.fileIdx &&
    a.addonId === b.addonId &&
    a.source === b.source
  );
}

function mergeSubtitleTracks(primary: StreamSource, enriched?: StreamSource) {
  if (!enriched?.subtitles?.length) return primary;
  const existing = primary.subtitles ?? [];
  const seen = new Set(existing.map((subtitle) => subtitle.url));
  const subtitles = [...existing, ...enriched.subtitles.filter((subtitle) => !seen.has(subtitle.url))];
  return subtitles.length === existing.length ? primary : { ...primary, subtitles };
}

function streamMeta(stream: StreamSource) {
  return [stream.addonName, stream.quality, stream.size].filter(Boolean).join(" - ");
}

function isLikelyHlsUrl(url?: string | null) {
  if (!url) return false;
  return /\.m3u8(?:[?#]|$)/i.test(url) || url.toLowerCase().includes("mpegurl");
}

// Xtream panels serve the same live stream as HLS at …/id.m3u8. Playlists with
// output=ts hand out raw-TS URLs the browser often can't use directly, so the
// ladder also tries the HLS twin of an Xtream-style live URL.
function xtreamHlsVariant(url?: string | null) {
  if (!url) return null;
  const match = url.match(/^(https?:\/\/[^?#]*\/[^/?#]+\/[^/?#]+\/\d+)\.ts(?=$|[?#])/i);
  return match ? `${match[1]}.m3u8` : null;
}

function liveTvProxyHeaders() {
  return {
    Accept: "*/*",
    "User-Agent": "VLC/3.0.20 LibVLC/3.0.20",
    "Icy-MetaData": "1"
  };
}

function directManifestUrl(url: string) {
  const target = new URL(proxiedUrl(url, liveTvProxyHeaders()));
  target.searchParams.set("rewrite", "direct");
  return target.toString();
}

function workerManifestUrl(url: string) {
  // Manifest via the app backend (reaches hosts that block Cloudflare),
  // segments via the configured resolver worker with its CORS/header handling.
  const target = new URL(proxiedUrl(url, liveTvProxyHeaders()));
  target.searchParams.set("rewrite", "worker");
  return target.toString();
}

function qualityBadges(stream: StreamSource) {
  const text = `${stream.quality ?? ""} ${stream.description ?? ""}`.toUpperCase();
  return [
    stream.quality || "HD",
    /\bDV\b/.test(text) || text.includes("DOLBY VISION") ? "DV" : "",
    /\bHDR/.test(text) ? "HDR" : "",
    text.includes("ATMOS") ? "ATMOS" : ""
  ].filter(Boolean);
}

function defaultSubtitleIndex(stream: StreamSource, language: string) {
  const desired = language.trim().toLowerCase();
  if (!desired || desired === "off") return -1;
  return (stream.subtitles ?? []).findIndex((subtitle) => {
    const lang = subtitle.lang?.toLowerCase() ?? "";
    const label = subtitle.label?.toLowerCase() ?? "";
    return lang.startsWith(desired) || label.includes(desired);
  });
}

// Subtitle list ordered like the app: the user's preferred language first,
// English next, then everything else alphabetically by language name. Each
// entry keeps its original index (the <track> elements are index-addressed).
function orderedSubtitles(stream: StreamSource, preferred: string) {
  const pref = preferred.trim().toLowerCase();
  return (stream.subtitles ?? [])
    .map((subtitle, index) => ({
      subtitle,
      index,
      langName: subtitleLanguageName(subtitle.lang || subtitle.label || "") || (subtitle.label ?? "Subtitle")
    }))
    .sort((a, b) => {
      const rank = (entry: { subtitle: { lang?: string | null } }) => {
        const lang = (entry.subtitle.lang ?? "").toLowerCase();
        if (pref && pref !== "off" && lang.startsWith(pref)) return 0;
        if (lang.startsWith("en")) return 1;
        return 2;
      };
      return rank(a) - rank(b) || a.langName.localeCompare(b.langName);
    });
}

export function PlayerOverlay() {
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
    closePlayer
  } = useApp();

  const enrichment = streams.find((candidate) => activeStream && (isSameStream(candidate, activeStream) || (candidate.url && candidate.url === activeStream.url)));
  const enrichedStream = useMemo(() => activeStream ? mergeSubtitleTracks(activeStream, enrichment) : null, [activeStream, enrichment]);
  if (!activeStream?.url || !enrichedStream) return null;

  const ytId = youTubeId(activeStream.url);
  const title = activeChannel?.name ?? selected?.title ?? activeStream.source;

  if (ytId) {
    return (
      <section className="player-overlay youtube-player-layout">
        <iframe
          className="player-youtube"
          src={`https://www.youtube-nocookie.com/embed/${ytId}?autoplay=1&playsinline=1`}
          title={title}
          referrerPolicy="strict-origin-when-cross-origin"
          allow="autoplay; encrypted-media; fullscreen"
          allowFullScreen
        />
        <div className="youtube-player-toolbar">
          <div className="player-top-left">
            <button type="button" className="player-icon-btn" onClick={closePlayer} aria-label="Back"><ArrowLeft size={24} /></button>
            <div>
              <p className="eyebrow">Trailer</p>
              <h2>{title}</h2>
            </div>
          </div>
          <a className="player-icon-btn" href={`https://www.youtube.com/watch?v=${ytId}`} target="_blank" rel="noopener noreferrer" aria-label="Open in YouTube" title="Open in YouTube"><ExternalLink size={24} /></a>
          <button type="button" className="player-icon-btn" onClick={closePlayer} aria-label="Close"><X size={24} /></button>
        </div>
      </section>
    );
  }

  const canAdvance = Boolean(selected?.mediaType === "tv" && selectedEpisode && !activeChannel);
  return (
    <VideoPlayer
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
      canAdvance={canAdvance}
      onSelectStream={playStream}
      onAdvance={advanceEpisode}
      onToast={setToast}
      onClose={closePlayer}
    />
  );
}

function VideoPlayer({
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
  canAdvance,
  onSelectStream: selectStream,
  onAdvance: advance,
  onToast,
  onClose: close
}: {
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
  canAdvance: boolean;
  onSelectStream: (stream: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean }) => void;
  onAdvance: () => Promise<boolean>;
  onToast: (message: string) => void;
  onClose: () => void;
}) {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const videoRef = useRef<HTMLVideoElement | null>(null);
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
  // Boot screen (like the app): backdrop + pulsing clearlogo covers the player
  // and blocks input until first frames are ready.
  const [booted, setBooted] = useState(false);
  const [bootLogo, setBootLogo] = useState<string | null>(null);
  // AI subtitle translation (same providers/prompt as the Android app).
  const [aiSubsActive, setAiSubsActive] = useState(false);
  const [aiTranslating, setAiTranslating] = useState(false);
  const aiTargetName = subtitleLanguageName(settings.defaultSubtitle);
  const aiAvailable = settings.aiSubtitlesEnabled && settings.aiSubtitleModel !== "off" && Boolean(settings.aiApiKey) && Boolean(aiTargetName) && !aiTargetName.toLowerCase().startsWith("engl");
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
  const [nextCountdown, setNextCountdown] = useState<number | null>(null);
  const nextDismissed = useRef(false);
  useEffect(() => { setNextCountdown(null); nextDismissed.current = false; }, [stream.url]);
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
  useEffect(() => {
    if (error) void trackPremiumDaily(authClient, "playback_failed", { playback_type: liveTv ? "live" : "vod" });
  }, [error, liveTv]);
  const [activePanel, setActivePanel] = useState<PlayerPanel>(null);
  const [activeSubtitle, setActiveSubtitle] = useState(-1);
  const [skipOverlay, setSkipOverlay] = useState<number | null>(null);
  const [remuxTracks, setRemuxTracks] = useState<RemuxAudioTrack[]>([]);
  const [remuxAudioIndex, setRemuxAudioIndex] = useState(-1);
  // Desired audio index survives remux restarts (switching audio re-runs the
  // effect); -1 means "use the probe's automatic choice".
  const remuxAudioIndexRef = useRef(-1);
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
      onSelectStream(stream, { forceRemux: true });
      return;
    }
    setRemuxRestartKey((key) => key + 1);
  }, [stream, onSelectStream]);

  // Direct-played MKVs expose no track APIs. Probing during playback would
  // open extra range connections to the same CDN link and starve the video
  // (TorBox limits connections per link) — so the container is probed ONLY
  // when the user opens the Audio panel, on demand.
  const [audioProbeState, setAudioProbeState] = useState<"idle" | "probing" | "done">("idle");
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
    const text = `${current.url} ${current.originalUrl ?? ""} ${current.source ?? ""} ${current.description ?? ""}`.toLowerCase();
    if (!/\.mkv|matroska|remux/.test(text)) return;
    setAudioProbeState("probing");
    audioProbeAbort.current?.abort();
    const controller = new AbortController();
    audioProbeAbort.current = controller;
    void (async () => {
      try {
        const { probeAndPrepareRemux } = await import("@/lib/remux");
        const probeUrl = cachedDebridDirectUrl(current.url) ?? current.url!;
        const prepared = await probeAndPrepareRemux(probeUrl, current.behaviorHints?.proxyHeaders?.request, settings.audioLanguage, { signal: controller.signal });
        if (!controller.signal.aborted && prepared && prepared.probe.audioTracks.length > 1) {
          setRemuxTracks(prepared.probe.audioTracks);
        }
        prepared?.destroy();
      } catch {
        // Probe is best-effort; the source keeps direct-playing either way.
      } finally {
        if (!controller.signal.aborted) setAudioProbeState("done");
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
    if (!booted || liveTv) return undefined;
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
      if (tryNextSource()) return;
      setBuffering(false);
      setShowControls(true);
      setError(true);
      onToast("Audio is playing but the browser cannot render this video's format. Choose a non-DV version or use a compatible external player.");
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [booted, stream.url, remuxRestartKey, liveTv]);

  // Live cue translation: when AI subs are active, the selected (English
  // source) track's cues are translated in small batches and swapped in place;
  // upcoming cues are pre-warmed so swaps land before display. Mirrors the
  // app's SubtitleTranslationManager.
  useEffect(() => {
    if (!aiSubsActive || !aiAvailable || activeSubtitle < 0) return undefined;
    const video = videoRef.current;
    const track = video?.textTracks?.[activeSubtitle];
    if (!video || !track) return undefined;
    const translator = new SubtitleTranslator(settings.aiApiKey, settings.aiSubtitleModel === "gemini" ? "gemini" : "groq", aiTargetName);
    translator.onTranslatingChanged = setAiTranslating;
    translatorRef.current = translator;
    const originals = new WeakMap<VTTCue, string>();
    const applyToCue = (cue: VTTCue) => {
      const original = originals.get(cue) ?? cue.text;
      originals.set(cue, original);
      void translator.translate(original).then((translated) => {
        if (translatorRef.current === translator && cue.text !== translated) cue.text = translated;
      });
    };
    const onCueChange = () => {
      const active = [...(track.activeCues ?? [])] as VTTCue[];
      active.forEach(applyToCue);
      // Pre-warm the next handful of cues.
      const all = [...(track.cues ?? [])] as VTTCue[];
      const now = video.currentTime;
      translator.prefetch(
        all.filter((cue) => cue.startTime > now && cue.startTime < now + 30).slice(0, 8)
          .map((cue) => originals.get(cue) ?? cue.text)
      );
    };
    track.addEventListener("cuechange", onCueChange);
    onCueChange();
    return () => {
      track.removeEventListener("cuechange", onCueChange);
      if (translatorRef.current === translator) translatorRef.current = null;
      setAiTranslating(false);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [aiSubsActive, aiAvailable, activeSubtitle, stream.url, settings.aiApiKey, settings.aiSubtitleModel, aiTargetName]);

  // Auto-activate AI (app parity): preferred-language subtitle missing but an
  // English source exists → translate it automatically.
  useEffect(() => {
    if (!aiAvailable || !settings.aiAutoSelect || liveTv) return;
    const subtitles = stream.subtitles ?? [];
    if (!subtitles.length) return;
    const pref = settings.defaultSubtitle.trim().toLowerCase();
    if (!pref || pref === "off") return;
    const hasPreferred = subtitles.some((subtitle) => (subtitle.lang ?? "").toLowerCase().startsWith(pref));
    const englishIndex = subtitles.findIndex((subtitle) => (subtitle.lang ?? "").toLowerCase().startsWith("en"));
    if (!hasPreferred && englishIndex >= 0) {
      setActiveSubtitle(englishIndex);
      setAiSubsActive(true);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stream.url, stream.subtitles?.length, aiAvailable, settings.aiAutoSelect, liveTv]);

  // Adaptive downswitch: repeated mid-play buffering means the connection
  // can't sustain this file's bitrate (common with huge remuxes over VPN) —
  // switch once to a substantially lighter playable version of the same title
  // instead of letting the viewer stutter through it.
  useEffect(() => {
    if (!booted || liveTv) return undefined;
    const video = videoRef.current;
    if (!video) return undefined;
    if (stream.remux) return undefined;
    // Mid-playback stall recovery. Previously this only reacted after three
    // stalls in 90s AND only if a strictly smaller source existed — so a single
    // source, an equal-sized source, or a source with unknown size (all common)
    // meant the video buffered forever with no message and no way to continue.
    // Now every stall is recovered: nudge the decoder, then re-attach the
    // source, then fall down the ladder, preferring a lighter source if one
    // exists because a stall usually means the connection can't keep up.
    let lastProgressTime = video.currentTime;
    let stalledSinceMs = 0;
    let nudged = false;
    let reloaded = false;
    let escalated = false;
    let announced = false;

    const resetStall = () => {
      stalledSinceMs = 0;
      nudged = false;
      reloaded = false;
      announced = false;
    };

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

    const tick = window.setInterval(() => {
      if (escalated) return;
      const stalledNow = isStalled({
        paused: video.paused,
        seeking: video.seeking,
        ended: video.ended,
        currentTime: video.currentTime,
        lastProgressTime,
      });
      if (!stalledNow) {
        lastProgressTime = video.currentTime;
        resetStall();
        return;
      }
      stalledSinceMs += STALL_POLL_MS;
      const action = nextStallAction({
        stalledForMs: stalledSinceMs,
        currentTime: video.currentTime,
        nudged,
        reloaded,
      });
      if (action.kind === "wait") return;
      if (!announced) {
        announced = true;
        onToast("Playback stalled — trying to recover…");
      }
      if (action.kind === "nudge") {
        nudged = true;
        try { video.currentTime = action.seekTo; } catch { /* seek can throw while unbuffered */ }
        void video.play().catch(() => undefined);
        return;
      }
      if (action.kind === "reload") {
        reloaded = true;
        const resumeAt = action.resumeAt;
        transportRef.current?.reload(resumeAt);
        return;
      }
      // escalate
      escalated = true;
      const lighter = lighterSource();
      if (lighter && currentStreamRef.current.autoSelect) {
        onToast("Your connection can't keep up with this version — switching to a lighter one.");
        onSelectStream({ ...lighter, autoSelect: true }, { forceBrowser: true });
        return;
      }
      // Nothing lighter: surface the failure instead of buffering silently so
      // the source list (and the external-player options) are reachable.
      setBuffering(false);
      setError(true);
      setShowControls(true);
      onToast("This source stopped responding. Pick another source to continue.");
    }, STALL_POLL_MS);

    const onSeeked = () => { lastProgressTime = video.currentTime; resetStall(); };
    video.addEventListener("seeked", onSeeked);
    return () => {
      window.clearInterval(tick);
      video.removeEventListener("seeked", onSeeked);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [booted, stream.url, liveTv]);

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
  const sourceList = useMemo(() => {
    const playable = streams.filter((candidate) => Boolean(candidate.url));
    const base = playable.length ? playable : [stream];
    // Always the browser ordering here: this list is the in-player picker and
    // the auto-hop fallback, so every candidate has to decode in this browser
    // no matter what the user's default player is elsewhere.
    return [...base].sort((a, b) => sourcePickerScore(b, "browser") - sourcePickerScore(a, "browser"));
  }, [streams, stream]);

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
    if (liveTv || !currentStreamRef.current.autoSelect || autoSourceHopsRef.current >= 6) return false;
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
    const pick = (skipStruckAddons: boolean) => sourceListRef.current.find((candidate) => {
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
    autoSourceHopsRef.current += 1;
    onToast(`Source failed — trying ${next.source || next.addonName || "the next source"}`);
    onSelectStream({ ...next, autoSelect: true }, { forceBrowser: true });
    return true;
  }, [liveTv, onSelectStream, onToast]);
  const badges = useMemo(() => {
    if (liveTv) return ["LIVE"];
    const base = qualityBadges(stream);
    if (stream.remux) return ["REMUX", ...base];
    return stream.transcoded ? ["TRANSCODE", ...base] : base;
  }, [stream, liveTv]);
  const mediaMeta = [subtitleLabel, stream.quality, stream.size, stream.addonName].filter(Boolean).join(" - ");
  const playbackIdentity = JSON.stringify([stream.url, stream.remux, stream.transcoded, stream.transport, stream.behaviorHints?.proxyHeaders?.request]);
  useEffect(() => { setActiveSubtitle(defaultSubtitleIndex(stream, settings.defaultSubtitle)); }, [stream.subtitles, stream.url, settings.defaultSubtitle]);

  useEffect(() => {
    const video = videoRef.current;
    if (!video || !stream.url) return undefined;

    // In-browser remux path (Tier 3): repackage an MKV direct link and play the
    // browser-safe audio track. Takes over the element entirely for this source.
    if (stream.remux) {
      transportRef.current = null;
      setTransportTracks({ audioTracks: [], qualities: [], selectedAudioTrackId: null, selectedQualityId: null });
      let cancelled = false;
      let recovering = false;
      const controller = new AbortController();
      let handle: { destroy: () => void } | null = null;
      let remuxWatchdog: number | undefined;
      setError(false);
      setErrorDetail("");
      setBuffering(true);
      setActiveSubtitle(defaultSubtitleIndex(stream, settings.defaultSubtitle));
      setRemuxTracks([]);
      lastSavedRef.current = 0;
      const remuxFailed = (message: string) => {
        if (cancelled || recovering) return;
        recovering = true;
        window.clearInterval(remuxWatchdog);
        video.pause();
        // Callback + rejected start() + watchdog can report the same fault.
        // Convert the selected file once, before considering another source.
        // Capture the playhead before abort/destroy resets the video element.
        if (canProviderTranscode(stream) && !stream.transcoded) {
          onToast("Requesting provider conversion for this source...");
          onSelectStream(stream, { forceBrowser: true, forceTranscode: true });
        } else if (!tryNextSource()) {
          setErrorDetail(message);
          setBuffering(false); setError(true); setShowControls(true);
        }
        controller.abort();
        handle?.destroy();
        handle = null;
      };
      void (async () => {
        try {
          const { probeAndPrepareRemux } = await import("@/lib/remux");
          const prepared = await probeAndPrepareRemux(stream.url!, stream.behaviorHints?.proxyHeaders?.request, settings.audioLanguage, { signal: controller.signal, onError: remuxFailed, expectDolbyVision: hasDolbyVision(stream) });
          if (cancelled || recovering) { prepared?.destroy(); return; }
          handle = prepared;
          if (!prepared || (prepared.probe.audioTracks.length > 0 && prepared.probe.chosenAudioIndex < 0) || !prepared.probe.videoPlayable) {
            const reason = !prepared ? "Browser preparation is unavailable for this source."
              : !prepared.probe.videoPlayable ? prepared.probe.videoReason ?? "This browser cannot decode the selected video track."
              : "No compatible audio track found. Use provider conversion or an external player.";
            if (prepared) recordBrowserPlaybackFailure(stream, reason, !!stream.transcoded);
            remuxFailed(reason);
            return;
          }
          setRemuxTracks(prepared.probe.audioTracks);
          const startIndex = remuxAudioIndexRef.current >= 0 ? remuxAudioIndexRef.current : prepared.probe.chosenAudioIndex;
          setRemuxAudioIndex(startIndex);
          try {
            await prepared.start(video, startIndex, resumeAtRef.current);
          } catch (error) {
            remuxFailed(error instanceof Error ? error.message : "Browser playback could not start.");
            return;
          }
          if (cancelled || recovering) return;
          resumeAtRef.current = 0;
          void video.play().catch(() => undefined);
          // A CDN can stop supplying packets without an error. Require clock
          // progress too, and use the same bounded recovery as decoder failures.
          let lastSeen = -1;
          let stuckTicks = 0;
          remuxWatchdog = window.setInterval(() => {
            if (cancelled || recovering) return;
            if (video.paused || video.ended || video.seeking) { lastSeen = video.currentTime; stuckTicks = 0; return; }
            const advancing = video.currentTime > lastSeen + 0.05;
            lastSeen = video.currentTime;
            if (advancing && video.readyState >= 2) { stuckTicks = 0; return; }
            stuckTicks += 1;
            if (stuckTicks < REMUX_STUCK_TICKS) return;
            remuxFailed("Browser preparation stopped delivering playable media.");
          }, 1000);
        } catch (error) {
          remuxFailed(error instanceof Error ? error.message : "Browser preparation failed.");
        }
      })();
      return () => {
        cancelled = true;
        controller.abort();
        window.clearInterval(remuxWatchdog);
        handle?.destroy();
        video.removeAttribute("src");
        video.load();
      };
    }

    setError(false);
    setErrorDetail("");
    setBuffering(true);
    setActiveSubtitle(defaultSubtitleIndex(stream, settings.defaultSubtitle));
    lastSavedRef.current = 0;
    const headers = stream.behaviorHints?.proxyHeaders?.request;
    let handlingError = false;
    let cancelled = false;
    let detach: PlaybackHandle | undefined;
    setTransportTracks({ audioTracks: [], qualities: [], selectedAudioTrackId: null, selectedQualityId: null });
    const attach = (url: string) => {
      const handle = attachPlayback(video, url, {
        onError: handlePlaybackError, live: liveTv,
        transport: url === stream.url ? stream.transport : undefined,
        requestHeaders: url === stream.url ? headers : undefined,
        onTracks: (tracks) => { if (!cancelled) setTransportTracks(tracks); }
      });
      transportRef.current = handle;
      return handle;
    };
    // Set once this source has actually rendered frames. Everything below is
    // STARTUP logic — "can this URL be opened at all?" — and must stand down
    // afterwards. Without this, an error five seconds into a working stream ran
    // the startup ladder and declared the source unplayable, which is why a
    // source that was visibly playing would suddenly say it can't play in the
    // browser and hop to the next one. Once playback has proven itself, a later
    // fault is a stall/interruption and belongs to the recovery watchdog.
    let hasPlayed = false;
    // Playback ladder: direct first (free for CORS-friendly providers), then the
    // Cloudflare resolver media proxy for live TV (fixes CORS/ORB without Netlify
    // bandwidth), then the legacy Netlify fallbacks.
    const attempts: string[] = [stream.url];
    // Catch-up recordings come from the same IPTV panels as live channels, so
    // they get the live relay hops — but keep VOD controls (seekable).
    const iptvRelay = liveTv || stream.addonName === "Catch-up";
    if (iptvRelay) {
      const hlsTwin = xtreamHlsVariant(stream.url);
      if (hlsTwin) attempts.push(hlsTwin);
      const workerUrl = resolverMediaUrl(stream.url, { ...liveTvProxyHeaders(), ...headers });
      if (workerUrl) attempts.push(workerUrl);
      if (hlsTwin) {
        const workerTwin = resolverMediaUrl(hlsTwin, { ...liveTvProxyHeaders(), ...headers });
        if (workerTwin) attempts.push(workerTwin);
        attempts.push(workerManifestUrl(hlsTwin));
      }
      if (isLikelyHlsUrl(stream.url)) {
        if (workerUrl) attempts.push(workerManifestUrl(stream.url));
        attempts.push(directManifestUrl(stream.url));
      }
      if (config.allowNetlifyMediaProxy) {
        attempts.push(proxiedUrl(hlsTwin ?? stream.url, liveTvProxyHeaders()));
      }
    }
    if (config.allowNetlifyMediaProxy && !headers && /^https?:\/\//i.test(stream.url)) {
      attempts.push(proxiedUrl(stream.url));
    }
    const uniqueAttempts = [...new Set(attempts)];
    let attemptIndex = 0;
    // Some sources hang forever without ever firing an "error" event (the CDN
    // accepts the connection but never delivers a playable moov/metadata). A
    // plain error-based ladder can't recover from that. Arm a per-attempt stall
    // timeout: if the element hasn't reached at least metadata within the window,
    // treat it as a failure and escalate down the ladder (next URL, then remux).
    let stallTimer: number | undefined;
    const armStallTimer = () => {
      window.clearTimeout(stallTimer);
      // Live channels that hang (provider accepts the connection but never sends
      // data) need a shorter leash than VOD so the ladder keeps moving.
      stallTimer = window.setTimeout(() => {
        if (cancelled) return;
        if (video.readyState < 1) handlePlaybackError();
        // A cold debrid link has to be fetched and cached by the provider
        // before the first byte arrives, which regularly exceeds the VOD
        // budget — condemning sources that were about to work.
      }, liveTv ? 10000 : (parseDebridStream(stream.originalUrl ?? stream.url) ? 25000 : 13000));
    };
    // Backstop for MSE sources (hls.js / remux): those attach a blob: src and
    // can fire loadedmetadata — which clears the stall timer — while never
    // delivering a frame, leaving the spinner up forever with zero network
    // traffic. This watchdog is independent of those events and only cares
    // whether playback ever became possible.
    const playableWatchdog = window.setTimeout(() => {
      if (cancelled) return;
      if (video.readyState >= 2) return;
      handlePlaybackError();
      // Same reasoning as the stall timer: debrid needs a longer leash.
    }, liveTv ? 15000 : (parseDebridStream(stream.originalUrl ?? stream.url) ? 38000 : 20000));
    const requestPlayback = () => {
      if (cancelled || !video.paused) return;
      setError(false);
      setBuffering(true);
      const attempt = video.play();
      if (attempt && typeof attempt.catch === "function") {
        attempt.catch(() => {
          if (cancelled) return;
          setBuffering(false);
          if (video.error) setError(true);
          setShowControls(true);
        });
      }
    };
    let refreshedLink = false;
    const handlePlaybackError = (fault?: PlaybackError) => {
      if (cancelled || handlingError) return;
      if (fault) setErrorDetail(fault.message);
      // A source that already played is not a startup failure. Walking the
      // ladder here would re-attach a different URL (or hop to another source)
      // mid-film; the stall watchdog recovers in place instead, keeping the
      // user's position and the source they chose. A decode fault is the one
      // exception — those bytes will never play here, so say so plainly rather
      // than letting the watchdog retry something that cannot work.
      if (hasPlayed) {
        const decodeFailure = fault ? fault.kind === "media" || fault.kind === "unsupported" : classifyMediaError(video.error?.code) === "fatal";
        if (decodeFailure) {
          if (!liveTv && !stream.transcoded && canProviderTranscode(stream)) {
            cancelled = true;
            onSelectStream(stream, { forceBrowser: true, forceTranscode: true });
            detach?.();
            return;
          }
          setBuffering(false);
          setError(true);
          setShowControls(true);
          onToast("This source stopped decoding partway through. Try another source or open it in VLC.");
        }
        return;
      }
      handlingError = true;
      // A debrid CDN link is presigned and short-lived. When one expires the
      // CDN rejects it (TorBox: "Invalid Presigned Token", HTTP 400) and every
      // remaining attempt for this source replays the SAME dead url — which is
      // why a single stale token used to cascade into "source failed" across
      // the whole list. Drop the cached link and re-resolve once before
      // treating the source as dead; the sources themselves are usually fine,
      // which is why they still play in VLC and the APK.
      if (!refreshedLink && !liveTv && !stream.transcoded && stream.originalUrl && parseDebridStream(stream.originalUrl)) {
        refreshedLink = true;
        invalidateDebridDirectUrl(stream.originalUrl);
        const debridInfo = parseDebridStream(stream.originalUrl);
        if (debridInfo) {
          detach?.();
          void resolveDebridDirectUrl(debridInfo).then((result) => {
            if (cancelled) return;
            if (result.url && result.url !== stream.url) {
              setError(false);
              setBuffering(true);
              detach = attach(result.url);
              armStallTimer();
              requestPlayback();
              handlingError = false;
            } else {
              handlingError = false;
              handlePlaybackError();
            }
          }).catch(() => {
            handlingError = false;
            handlePlaybackError();
          });
          return;
        }
      }
      attemptIndex += 1;
      const nextUrl = uniqueAttempts[attemptIndex];
      if (nextUrl) {
        setError(false);
        setBuffering(true);
        detach?.();
        detach = attach(nextUrl);
        armStallTimer();
        requestPlayback();
        handlingError = false;
        return;
      }
      // Direct attempts exhausted. For a VOD source the browser couldn't decode
      // (MKV container / lossless audio), auto-escalate to the in-browser remux
      // path instead of surfacing an error — this is the instant-first ladder.
      // Only when the plan says a remux can actually succeed here: escalating a
      // source whose audio this browser cannot decode just burns CPU and time
      // before failing, when the honest move is to fall through to VLC.
      if (!liveTv && !stream.homeServer && !stream.remux && !stream.transcoded && canTryRemux(stream)) {
        cancelled = true;
        const playhead = video.currentTime;
        if (playhead > 5) resumeAtRef.current = playhead;
        onSelectStream(stream, { forceRemux: true });
        detach?.();
        return;
      }
      if (!liveTv && !stream.transcoded && canProviderTranscode(stream)) {
        cancelled = true;
        onSelectStream(stream, { forceTranscode: true, forceBrowser: true });
        detach?.();
        return;
      }
      // This source is dead — hop to the next playable one before giving up.
      if (tryNextSource()) {
        cancelled = true;
        detach?.();
        return;
      }
      setBuffering(false);
      setError(true);
      handlingError = false;
    };
    detach = attach(uniqueAttempts[0]);
    armStallTimer();
    const onReadyToStart = () => {
      window.clearTimeout(stallTimer);
      if (video.playbackRate !== playbackRate) video.playbackRate = playbackRate;
      // Restore the position carried over from a source hop / remux switch.
      // Guarded so it only fires once and never seeks past the end.
      const resumeAt = resumeAtRef.current;
      if (resumeAt > 5 && video.currentTime < 1) {
        const target = video.duration > 0 ? Math.min(resumeAt, video.duration - 5) : resumeAt;
        if (target > 0) { try { video.currentTime = target; } catch { /* not seekable yet */ } }
        resumeAtRef.current = 0;
      }
      requestPlayback();
    };
    const startTimer = window.setTimeout(requestPlayback, 0);
    video.addEventListener("loadedmetadata", onReadyToStart, { once: true });
    video.addEventListener("canplay", onReadyToStart, { once: true });
    const slowTimer = window.setTimeout(() => {
      if (liveTv && video.readyState === 0 && video.paused) setBuffering(false);
    }, 6500);
    const onErr = () => handlePlaybackError();
    video.addEventListener("error", onErr);
    // The moment real frames arrive this source has proven it plays here, so
    // retire the startup watchdogs and the ladder. Anything that goes wrong
    // from now on is handled by the stall watchdog, which recovers in place.
    const onFirstPlaying = () => {
      if (video.readyState < 3) return;
      hasPlayed = true;
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      video.removeEventListener("playing", onFirstPlaying);
      video.removeEventListener("timeupdate", onFirstPlaying);
    };
    video.addEventListener("playing", onFirstPlaying);
    // `playing` can be missed when a source starts already-buffered; a moving
    // clock is the same proof.
    video.addEventListener("timeupdate", onFirstPlaying);
    return () => {
      cancelled = true;
      window.clearTimeout(startTimer);
      window.clearTimeout(slowTimer);
      window.clearTimeout(stallTimer);
      window.clearTimeout(playableWatchdog);
      video.removeEventListener("playing", onFirstPlaying);
      video.removeEventListener("timeupdate", onFirstPlaying);
      video.removeEventListener("loadedmetadata", onReadyToStart);
      video.removeEventListener("canplay", onReadyToStart);
      video.removeEventListener("error", onErr);
      detach?.();
      if (transportRef.current === detach) transportRef.current = null;
    };
    // Artwork, source enrichment and subtitle updates must not restart a playing URL.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [playbackIdentity, remuxRestartKey, liveTv]);

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
    const onPlay = () => setPlaying(true);
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

  useEffect(() => {
    const video = videoRef.current;
    if (!video) return;
    const tracks = Array.from(video.textTracks);
    tracks.forEach((track, index) => {
      track.mode = activeSubtitle === index ? "showing" : "disabled";
    });
  }, [activeSubtitle, stream]);

  // Reset the manual remux audio override when the source changes so each new
  // source starts from its own automatic best-track choice.
  useEffect(() => {
    remuxAudioIndexRef.current = -1;
  }, [stream.url]);

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
      // The current backend saves an account snapshot per checkpoint. Bound
      // periodic traffic while pause/close/background/end still flush immediately.
      if (force !== true && now - lastSavedRef.current < 60_000) return;
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
      if (settings.autoPlayNext && canAdvance && !nextDismissed.current) { setShowControls(true); setNextCountdown(10); }
    };
    const flush = () => { save(true); onPaused(); };
    const background = () => {
      if (document.visibilityState === "hidden") flush();
      else if (!video.paused && !video.ended && video.readyState >= 2) onPlaying();
    };
    video.addEventListener("timeupdate", save);
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
      video.removeEventListener("pause", flush);
      window.removeEventListener("pagehide", onStopped);
      document.removeEventListener("visibilitychange", background);
      video.removeEventListener("playing", onPlaying);
      video.removeEventListener("pause", onPaused);
      video.removeEventListener("ended", onEnded);
      video.removeEventListener("arvio-tracking-stop", onStopped);
    };
  }, [item, stream, selectedEpisode, settings.autoPlayNext, activeProfileId, addons, canAdvance, liveTv, onToast]);

  const flashControls = useCallback(() => {
    setShowControls(true);
    if (hideTimer.current) clearTimeout(hideTimer.current);
    hideTimer.current = setTimeout(() => {
      if (videoRef.current && !videoRef.current.paused && !activePanel) setShowControls(false);
    }, 3000);
  }, [activePanel]);

  const togglePlay = useCallback(() => {
    const video = videoRef.current;
    if (!video) return;
    if (video.paused) {
      setError(false);
      setBuffering(true);
      const attempt = video.play();
      void attempt?.catch(() => {
        if (video.error) {
          setBuffering(false);
          setError(true);
          setShowControls(true);
          return;
        }
        // No media error means autoplay policy: the page has no user
        // activation yet (controller-only session). Muted playback is exempt.
        video.muted = true;
        void video.play().then(() => {
          onToast("Started muted — press M or the speaker button to unmute.");
        }).catch(() => {
          setBuffering(false);
          setShowControls(true);
        });
      });
    }
    else video.pause();
    flashControls();
  }, [flashControls, onToast]);

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
    const el = containerRef.current;
    if (!el) return;
    if (!document.fullscreenElement) {
      void el.requestFullscreen?.().catch(() => {
        // Fullscreen demands a real click/tap (transient activation), which
        // controller input can never mint — say so instead of doing nothing.
        onToast("Fullscreen needs a real click or tap first — or press F11.");
      });
    }
    else void document.exitFullscreen?.().catch(() => undefined);
  }, [onToast]);

  const openPanel = useCallback((panel: Exclude<PlayerPanel, null>) => {
    setActivePanel((currentPanel) => {
      const next = currentPanel === panel ? null : panel;
      // Opening the Audio panel on a direct-played source triggers the
      // on-demand track probe (never during unattended playback).
      if (next === "audio" && audioProbeState === "idle" && remuxTracks.length === 0) probeAudioTracks();
      return next;
    });
    setShowControls(true);
  }, [audioProbeState, remuxTracks.length, probeAudioTracks]);

  const openExternal = useCallback((player: "vlc" | "infuse", selectedStream: StreamSource) => {
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
  }, [activeProfileId, item, onToast, selectedEpisode, title, settings.defaultSubtitle]);

  // Android: open in whichever installed player the user picks (system chooser).
  const openAnyPlayer = useCallback((selectedStream: StreamSource) => {
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
  }, [activeProfileId, item, onToast, selectedEpisode, title, settings.defaultSubtitle]);

  const copyUrl = useCallback(async (selectedStream: StreamSource) => {
    const copied = await copyStreamUrl(selectedStream).catch(() => false);
    onToast(copied ? "Stream URL copied." : "Could not copy this stream URL.");
  }, [onToast]);

  useEffect(() => {
    const onFs = () => setFullscreen(Boolean(document.fullscreenElement));
    document.addEventListener("fullscreenchange", onFs);
    return () => document.removeEventListener("fullscreenchange", onFs);
  }, []);

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if (dock.docked) return;
      const target = e.target;
      if (e.defaultPrevented || e.altKey || e.ctrlKey || e.metaKey) return;
      if (e.key !== "Escape" && target instanceof HTMLElement && (
        target.closest('input, select, textarea, [contenteditable="true"]') ||
        (target.closest('button, [role="button"]') && e.key === " ") ||
        (activePanel && e.key.startsWith("Arrow"))
      )) return;
      switch (e.key) {
        case " ":
        case "k":
          e.preventDefault();
          togglePlay();
          break;
        case "ArrowLeft":
        case "j":
          e.preventDefault();
          seekBy(-10);
          break;
        case "ArrowRight":
        case "l":
          e.preventDefault();
          seekBy(10);
          break;
        case "ArrowUp": {
          e.preventDefault();
          const v = videoRef.current;
          if (v) {
            v.volume = Math.min(1, v.volume + 0.1);
            flashControls();
          }
          break;
        }
        case "ArrowDown": {
          e.preventDefault();
          const v = videoRef.current;
          if (v) {
            v.volume = Math.max(0, v.volume - 0.1);
            flashControls();
          }
          break;
        }
        case "m": {
          const v = videoRef.current;
          if (v) v.muted = !v.muted;
          break;
        }
        case "c":
          openPanel("subtitles");
          break;
        case "s":
          openPanel("sources");
          break;
        case "f":
          toggleFullscreen();
          break;
        case "Escape":
          if (activePanel) setActivePanel(null);
          // A synthetic Escape (gamepad B) can't trigger the browser's own
          // exit-fullscreen default — do it ourselves, then close next press.
          else if (document.fullscreenElement) void document.exitFullscreen?.().catch(() => undefined);
          else onClose();
          break;
        default:
          break;
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [togglePlay, seekBy, toggleFullscreen, flashControls, onClose, openPanel, activePanel, dock.docked]);

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
    const video = videoRef.current;
    if (!video || activeSubtitle < 0) return undefined;
    const track = video.textTracks?.[activeSubtitle];
    if (!track) return undefined;
    // Subtitle sync offset. The Settings slider wrote subtitleOffsetMs but
    // nothing ever read it, so the control did nothing at all. Shift each cue
    // by the stored delta, remembering the original times so repeated changes
    // (and switching back to 0) stay accurate instead of compounding.
    const shiftSec = (settings.subtitleOffsetMs ?? 0) / 1000;
    const originals = new WeakMap<VTTCue, { start: number; end: number }>();
    const applyLine = () => {
      for (const cue of Array.from(track.cues ?? []) as VTTCue[]) {
        cue.snapToLines = false;
        cue.line = subtitleLinePercent;
        if (!originals.has(cue)) originals.set(cue, { start: cue.startTime, end: cue.endTime });
        const base = originals.get(cue)!;
        const start = Math.max(0, base.start + shiftSec);
        const end = Math.max(start + 0.05, base.end + shiftSec);
        if (cue.startTime !== start) cue.startTime = start;
        if (cue.endTime !== end) cue.endTime = end;
      }
    };
    applyLine();
    track.addEventListener("cuechange", applyLine);
    return () => track.removeEventListener("cuechange", applyLine);
  }, [activeSubtitle, subtitleLinePercent, settings.subtitleOffsetMs, stream.url]);
  // While scrubbing the bar follows the drag, not the (not yet moved) playhead.
  const scrubDisplayTime = scrubTo ?? current;
  const pct = duration > 0 ? (scrubDisplayTime / duration) * 100 : 0;
  const bufPct = duration > 0 ? (buffered / duration) * 100 : 0;

  return (
    <section
      ref={containerRef}
      className={`player-overlay ${dock.docked ? "player-docked" : ""} ${showControls || activePanel ? "controls-on" : "controls-off"}`}
      style={dock.style}
      onMouseMove={flashControls}
    >
      <style>{cueCss}</style>
      <video ref={videoRef} autoPlay playsInline preload="auto" onClick={togglePlay} poster={item?.backdrop ?? undefined}>
        {(stream.subtitles ?? []).map((subtitle, index) => (
          <track
            key={subtitle.id || subtitle.url}
            kind="subtitles"
            srcLang={subtitle.lang || "en"}
            label={subtitle.label || subtitle.lang || "Subtitle"}
            src={resolverSubtitleUrl(subtitle.url)}
            default={activeSubtitle === index}
          />
        ))}
      </video>
      {dock.docked && <div className="player-dock-controls">
        <span role="status">{error ? "Unavailable" : buffering ? "Connecting" : playing ? "LIVE" : "Paused"}</span>
        <button type="button" onClick={togglePlay} aria-label={playing ? "Pause" : "Play"} title={playing ? "Pause" : "Play"}>{playing ? <Pause size={20} /> : <Play size={20} />}</button>
        <button type="button" onClick={dock.expand} aria-label="Expand player" title="Expand player"><Maximize size={20} /></button>
        <button type="button" onClick={onClose} aria-label="Stop channel" title="Stop channel"><X size={20} /></button>
      </div>}
      {!dock.docked && dock.canDock && <button type="button" className="player-dock-return" onClick={dock.collapse} aria-label="Return to guide" title="Return to guide"><Minimize size={22} /></button>}

      {!booted && !error && (
        <div className="player-boot" style={{ backgroundImage: item?.backdrop ? `url(${item.backdrop})` : undefined }} aria-label="Loading playback">
          {bootLogo
            ? <img className="player-boot-logo" src={bootLogo} alt={title} />
            : <h2 className="player-boot-title">{title}</h2>}
        </div>
      )}
      {buffering && !error && booted && (
        <div className="player-spinner">
          <Loader2 size={56} />
          {/* Show how much is actually buffered ahead: a bare spinner cannot
              distinguish "downloading fine" from "wedged and going nowhere". */}
          {bufferAheadSec > 0 && <span className="player-buffer-health">{Math.round(bufferAheadSec)}s buffered</span>}
        </div>
      )}
      {error && (
        <div className="player-error">
          <p>{liveTv ? "This channel could not be played right now." : "This source could not be played in the browser."}</p>
          <span>
            {errorDetail || "The source could not be opened. Its network access, browser permissions or media format may be unsupported. Try another source or an external player."}
          </span>
          <div className="player-error-actions">
            <button type="button" className="player-error-external" onClick={() => openExternal("vlc", stream)}>
              <ExternalLink size={15} /> Open in VLC
            </button>
            <button type="button" className="player-error-external" onClick={() => openAnyPlayer(stream)}>
              <ExternalLink size={15} /> Open in player
            </button>
            {!liveTv && !stream.transcoded && parseDebridStream(stream.url) && (
              <button type="button" className="player-error-transcode" onClick={() => onSelectStream(stream, { forceTranscode: true })}>
                <Play size={15} fill="currentColor" /> Transcode
              </button>
            )}
          </div>
        </div>
      )}
      {skipOverlay !== null && (
        <div className={`player-skip-overlay ${skipOverlay > 0 ? "forward" : "back"}`}>
          {skipOverlay > 0 ? <RotateCw size={34} /> : <RotateCcw size={34} />}
          <strong>{skipOverlay > 0 ? "+" : ""}{skipOverlay}s</strong>
        </div>
      )}
      {!playing && !buffering && !error && (
        <button type="button" className="player-bigplay" onClick={togglePlay} aria-label="Play"><Play size={48} fill="currentColor" /></button>
      )}

      <div className="player-top">
        <div className="player-top-left player-metadata">
          <button type="button" className="player-icon-btn" onClick={onClose} aria-label="Back"><ArrowLeft size={24} /></button>
          <span className="player-accent-rail" />
          <div>
            <p className="eyebrow">{mediaMeta || stream.source}</p>
            <h2>{title}</h2>
            {!playing && item?.overview && <p className="player-overview">{item.overview}</p>}
          </div>
        </div>
        <div className="player-top-actions">
          {nextCountdown !== null && <div className="next-episode-prompt" role="status"><span>Next episode in {nextCountdown}s</span><button type="button" className="icon-button" aria-label="Cancel next episode" onClick={() => { nextDismissed.current = true; setNextCountdown(null); }}><X size={20} /></button></div>}
          {canAdvance && <button type="button" className="player-next" onClick={() => { setNextCountdown(null); void onAdvance(); }}><SkipForward size={18} /> Next episode</button>}
          <button type="button" className="player-icon-btn" onClick={onClose} aria-label="Close"><X size={22} /></button>
        </div>
      </div>

      {activePanel && (
        <aside className="player-side-panel">
          <div className="player-panel-head">
            <div>
              <p className="eyebrow">{activePanel}</p>
              <h3>{activePanel === "sources" ? "Choose Source" : activePanel === "subtitles" ? "Subtitles" : activePanel === "audio" ? "Audio" : "Playback Settings"}</h3>
            </div>
            <button type="button" className="player-icon-btn" onClick={() => setActivePanel(null)} aria-label="Close panel"><X size={18} /></button>
          </div>

          {activePanel === "sources" && (
            <div className="player-panel-list">
              {sourceList.map((candidate, index) => {
                const active = isSameStream(candidate, stream);
                return (
                  <article
                    key={`${candidate.addonId ?? candidate.addonName}-${candidate.source}-${index}`}
                    className={`player-panel-row ${active ? "is-active" : ""}`}
                  >
                    <span className="player-row-icon">{active ? <Check size={17} /> : index + 1}</span>
                    <span>
                      <strong>{candidate.source || candidate.addonName}</strong>
                      <em>{streamMeta(candidate) || "Direct stream"}</em>
                      <span className="player-row-actions">
                        <button
                          type="button"
                          onClick={() => {
                            onSelectStream(candidate, { forceBrowser: true });
                            setActivePanel(null);
                          }}
                        >
                          <Play size={13} fill="currentColor" /> Play
                        </button>
                        <button type="button" onClick={() => openExternal("vlc", candidate)}>
                          <ExternalLink size={13} /> VLC
                        </button>
                        <button type="button" onClick={() => openAnyPlayer(candidate)}>
                          <ExternalLink size={13} /> Player
                        </button>
                        <button type="button" onClick={() => void copyUrl(candidate)} aria-label="Copy stream URL">
                          <Copy size={13} />
                        </button>
                      </span>
                    </span>
                  </article>
                );
              })}
            </div>
          )}

          {activePanel === "audio" && (
            <div className="player-panel-list">
              {transportTracks.audioTracks.length > 0 ? transportTracks.audioTracks.map((track) => (
                <button type="button" key={track.id} className={`player-panel-row ${transportTracks.selectedAudioTrackId === track.id ? "is-active" : ""}`} onClick={() => transportRef.current?.selectAudioTrack(track.id)}>
                  <span className="player-row-icon">{transportTracks.selectedAudioTrackId === track.id ? <Check size={17} /> : ""}</span>
                  <span><strong>{track.label}</strong><em>{track.language ?? ""}</em></span>
                </button>
              )) : remuxTracks.length > 0 ? (
                remuxTracks.map((track) => (
                  <button
                    type="button"
                    key={track.index}
                    className={`player-panel-row ${remuxAudioIndex === track.index ? "is-active" : ""} ${track.browserPlayable ? "" : "is-disabled"}`}
                    disabled={!track.browserPlayable}
                    onClick={() => track.browserPlayable && switchRemuxAudio(track.index)}
                  >
                    <span className="player-row-icon">{remuxAudioIndex === track.index ? <Check size={17} /> : ""}</span>
                    <span><strong>{track.label}</strong><em>{track.browserPlayable ? track.codec : "Lossless — external player only"}</em></span>
                  </button>
                ))
              ) : audioProbeState === "probing" ? (
                <p className="player-panel-empty">Reading audio tracks from this source…</p>
              ) : (
                <p className="player-panel-empty">This source plays its default audio track — no other selectable tracks were found.</p>
              )}
            </div>
          )}

          {activePanel === "subtitles" && (
            <div className="player-panel-list">
              <button type="button" className={`player-panel-row ${activeSubtitle < 0 ? "is-active" : ""}`} onClick={() => { setActiveSubtitle(-1); setAiSubsActive(false); }}>
                <span className="player-row-icon">{activeSubtitle < 0 ? <Check size={17} /> : ""}</span>
                <span><strong>Off</strong><em>No subtitle track</em></span>
              </button>
              {aiAvailable && (
                <button
                  type="button"
                  className={`player-panel-row ${aiSubsActive ? "is-active" : ""}`}
                  onClick={() => {
                    if (aiSubsActive) {
                      setAiSubsActive(false);
                      return;
                    }
                    const englishIndex = (stream.subtitles ?? []).findIndex((subtitle) => (subtitle.lang ?? "").toLowerCase().startsWith("en"));
                    if (englishIndex < 0) {
                      onToast("AI subtitles need an English source subtitle for this title.");
                      return;
                    }
                    setActiveSubtitle(englishIndex);
                    setAiSubsActive(true);
                  }}
                >
                  <span className="player-row-icon">{aiSubsActive ? <Check size={17} /> : "AI"}</span>
                  <span>
                    <strong>AI · {aiTargetName}</strong>
                    <em>{aiSubsActive ? (aiTranslating ? "Translating…" : "Live-translating English subtitles") : `Translate English subtitles to ${aiTargetName}`}</em>
                  </span>
                </button>
              )}
              {orderedSubtitles(stream, settings.defaultSubtitle).map(({ subtitle, index, langName }) => (
                <button
                  type="button"
                  key={subtitle.id || subtitle.url}
                  className={`player-panel-row ${activeSubtitle === index && !aiSubsActive ? "is-active" : ""}`}
                  onClick={() => { setActiveSubtitle(index); setAiSubsActive(false); }}
                >
                  <span className="player-row-icon">{activeSubtitle === index && !aiSubsActive ? <Check size={17} /> : ""}</span>
                  <span>
                    <strong>{langName}</strong>
                    <em>{[subtitle.label && subtitle.label !== langName ? subtitle.label : "", subtitle.provider, subtitle.isForced ? "Forced" : ""].filter(Boolean).join(" - ") || "External subtitle"}</em>
                  </span>
                </button>
              ))}
              {(stream.subtitles?.length ?? 0) === 0 && <p className="player-panel-empty">No external subtitles were returned for this source.</p>}
            </div>
          )}

          {activePanel === "settings" && (
            <div className="player-settings-panel">
              <div className="player-setting-row"><span><strong>Playback</strong></span><span>{stream.transcoded ? "Server conversion" : stream.remux ? "On-device conversion" : stream.transport === "hls" ? "HLS" : stream.transport === "dash" ? "DASH" : stream.transport === "mpegts" ? "MPEG-TS" : "Direct"}</span></div>
              <div className="player-setting-row"><span><strong>Video</strong></span><span>{videoRef.current?.videoWidth ? `${videoRef.current.videoWidth} x ${videoRef.current.videoHeight}` : "Loading"}</span></div>
              <div className="player-setting-row"><span><strong>Buffered ahead</strong></span><span>{Math.round(bufferAheadSec)} s</span></div>
              {transportTracks.qualities.length > 0 && <div className="player-setting-row">
                <span><strong>Quality</strong></span>
                <select aria-label="Playback quality" value={transportTracks.selectedQualityId ?? "auto"} onChange={(event) => transportRef.current?.selectQuality(event.target.value === "auto" ? null : event.target.value)}>
                  <option value="auto">Auto</option>
                  {transportTracks.qualities.map((quality) => <option key={quality.id} value={quality.id}>{quality.label}</option>)}
                </select>
              </div>}
              <button type="button" className="player-setting-toggle" onClick={() => updateSettings({ autoPlayNext: !settings.autoPlayNext })}>
                <span><strong>Auto-play next episode</strong><em>Play the next episode automatically</em></span>
                <span className={`player-switch ${settings.autoPlayNext ? "is-on" : ""}`} />
              </button>
              <button type="button" className="player-setting-toggle" onClick={() => updateSettings({ autoPlaySingleSource: !settings.autoPlaySingleSource })}>
                <span><strong>Auto-play single source</strong><em>Start playing when only one source is found</em></span>
                <span className={`player-switch ${settings.autoPlaySingleSource ? "is-on" : ""}`} />
              </button>
              <div className="player-setting-row">
                <span><strong>Subtitle size</strong></span>
                <div className="player-setting-stepper">
                  <button type="button" onClick={() => updateSettings({ subtitleSize: Math.max(60, settings.subtitleSize - 10) })} aria-label="Decrease subtitle size">−</button>
                  <b>{settings.subtitleSize}%</b>
                  <button type="button" onClick={() => updateSettings({ subtitleSize: Math.min(300, settings.subtitleSize + 10) })} aria-label="Increase subtitle size">+</button>
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>Subtitle position</strong></span>
                <div className="player-setting-choices">
                  {([["bottom", "Bottom"], ["low", "Low"], ["medium", "Middle"], ["high", "High"]] as const).map(([value, label]) => (
                    <button
                      type="button"
                      key={value}
                      className={settings.subtitleOffset === value ? "is-active" : ""}
                      onClick={() => updateSettings({ subtitleOffset: value })}
                    >
                      {label}
                    </button>
                  ))}
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>Playback speed</strong></span>
                <div className="player-setting-choices">
                  {[0.75, 1, 1.25, 1.5, 2].map((rate) => (
                    <button
                      type="button"
                      key={rate}
                      className={playbackRate === rate ? "is-active" : ""}
                      onClick={() => setPlaybackRate(rate)}
                    >
                      {rate}×
                    </button>
                  ))}
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>Subtitle color</strong></span>
                <div className="player-setting-choices player-subtitle-colors">
                  {([["White", "#ffffff"], ["Yellow", "#ffeb3b"], ["Green", "#4caf50"], ["Cyan", "#00e5ff"]] as const).map(([name, hex]) => (
                    <button
                      type="button"
                      key={name}
                      className={settings.subtitleColorName === name ? "is-active" : ""}
                      style={{ ["--dot" as string]: hex }}
                      onClick={() => updateSettings({ subtitleColorName: name, subtitleColor: hex })}
                      aria-label={`${name} subtitles`}
                    >
                      <i />
                    </button>
                  ))}
                </div>
              </div>
              <div className="player-setting-row">
                <span><strong>Subtitle style</strong></span>
                <div className="player-setting-choices">
                  {(["background", "outline", "shadow", "raised"] as const).map((style) => (
                    <button
                      type="button"
                      key={style}
                      className={settings.subtitleStyle === style ? "is-active" : ""}
                      onClick={() => updateSettings({ subtitleStyle: style })}
                    >
                      {style === "background" ? "Boxed" : style.charAt(0).toUpperCase() + style.slice(1)}
                    </button>
                  ))}
                </div>
              </div>
            </div>
          )}
        </aside>
      )}

      <div className="player-controls">
        {!liveTv && (
          <div
            className="scrubber-track"
            onPointerMove={(e) => {
              if (!duration) return;
              const rect = e.currentTarget.getBoundingClientRect();
              const ratio = Math.max(0, Math.min(1, (e.clientX - rect.left) / rect.width));
              setHoverTime(ratio * duration);
              setHoverPct(ratio * 100);
            }}
            onPointerLeave={() => setHoverTime(null)}
          >
            {hoverTime !== null && (
              <span
                className="scrubber-hover-time"
                // Clamp so the readout stays inside the bar at both ends
                // instead of hanging off the edge near 0:00 and the finish.
                style={{ left: `${Math.min(96, Math.max(4, hoverPct))}%` }}
              >
                {fmt(hoverTime)}
              </span>
            )}
            <input
              className="scrubber"
              type="range"
              min={0}
              max={duration || 0}
              // 1s keeps keyboard arrows useful; 0.1 made a 2h film 72,000 steps.
              step={1}
              value={scrubDisplayTime}
              aria-label="Seek"
              aria-valuetext={fmt(scrubDisplayTime)}
              style={{ ["--pct" as string]: `${pct}%`, ["--buf" as string]: `${bufPct}%` }}
              onChange={(e) => setScrubTo(Number(e.target.value))}
              onPointerUp={(event) => {
                const v = videoRef.current;
                if (v) v.currentTime = Number(event.currentTarget.value);
                setScrubTo(null);
              }}
              onKeyUp={(event) => {
                if (!["ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown", "Home", "End", "PageUp", "PageDown"].includes(event.key)) return;
                // Keyboard seeking has no pointer release to commit on.
                const v = videoRef.current;
                if (v) v.currentTime = Number(event.currentTarget.value);
                setScrubTo(null);
              }}
              onBlur={() => setScrubTo(null)}
            />
          </div>
        )}
        <div className="player-controls-row">
          <div className="player-controls-left">
            <button type="button" className="player-icon-btn player-play-btn" onClick={togglePlay} aria-label={playing ? "Pause" : "Play"}>
              {playing ? <Pause size={24} fill="currentColor" /> : <Play size={24} fill="currentColor" />}
            </button>
            {!liveTv && (
              <>
                <button type="button" className="player-icon-btn player-seek-btn" onClick={() => seekBy(-30)} aria-label="Back 30 seconds">
                  <RotateCcw size={22} /><span className="player-seek-label">30</span>
                </button>
                <button type="button" className="player-icon-btn player-seek-btn" onClick={() => seekBy(30)} aria-label="Forward 30 seconds">
                  <RotateCw size={22} /><span className="player-seek-label">30</span>
                </button>
              </>
            )}
            <div className="player-volume">
              <button type="button" className="player-icon-btn" onClick={() => { const v = videoRef.current; if (v) v.muted = !v.muted; }} aria-label="Mute">
                {muted || volume === 0 ? <VolumeX size={20} /> : <Volume2 size={20} />}
              </button>
              <input
                className="volume-slider"
                type="range"
                min={0}
                max={1}
                step={0.05}
                value={muted ? 0 : volume}
                style={{ ["--pct" as string]: `${(muted ? 0 : volume) * 100}%` }}
                onChange={(e) => {
                  const v = videoRef.current;
                  if (v) {
                    v.volume = Number(e.target.value);
                    v.muted = Number(e.target.value) === 0;
                  }
                }}
              />
            </div>
            {liveTv
              ? <button type="button" className="player-time player-live-indicator" title="Return to live" onClick={() => { if (transportRef.current?.goLive()) void videoRef.current?.play().catch(() => undefined); }}><span className="live-dot" /> LIVE</button>
              : (
                <span className="player-time">
                  {fmt(scrubDisplayTime)} <em>/</em> {fmt(duration)}
                  {duration > 0 && (
                    // "How much is left" is the question people actually ask;
                    // the elapsed/total pair alone makes you do the subtraction.
                    <span className="player-time-remaining">-{fmt(Math.max(0, duration - scrubDisplayTime))}</span>
                  )}
                </span>
              )}
          </div>
          <div className="player-controls-right">
            <div className="player-badges">
              {badges.map((badge) => <span key={badge} className="player-quality">{badge}</span>)}
            </div>
            {!liveTv && (
              <>
                <button type="button" className={`player-icon-btn ${activePanel === "subtitles" ? "is-active" : ""}`} onClick={() => openPanel("subtitles")} aria-label="Subtitles"><Subtitles size={20} /></button>
                <button type="button" className={`player-icon-btn ${activePanel === "audio" ? "is-active" : ""}`} onClick={() => openPanel("audio")} aria-label="Audio"><AudioLines size={20} /></button>
              </>
            )}
            <button type="button" className={`player-icon-btn ${activePanel === "sources" ? "is-active" : ""}`} onClick={() => openPanel("sources")} aria-label="Sources"><Folder size={20} /></button>
            <button type="button" className={`player-icon-btn ${activePanel === "settings" ? "is-active" : ""}`} onClick={() => openPanel("settings")} aria-label="Player settings"><Settings size={20} /></button>
            <button type="button" className="player-icon-btn" onClick={toggleFullscreen} aria-label="Fullscreen">
              {fullscreen ? <Minimize size={20} /> : <Maximize size={20} />}
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}

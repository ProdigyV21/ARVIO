"use client";

import {
  ArrowLeft, Loader2, Maximize, Minimize, Pause, Play, SkipForward, Subtitles, Volume2, VolumeX, X
} from "lucide-react";
import { useCallback, useEffect, useRef, useState } from "react";
import { config } from "@/lib/config";
import { saveProgress } from "@/lib/cloud";
import { proxiedUrl } from "@/lib/http";
import { attachPlayback } from "@/lib/player";
import { authClient, traktClient, useApp } from "@/lib/store";
import type { AppSettings, MediaItem, StreamSource } from "@/lib/types";

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

export function PlayerOverlay() {
  const { activeStream, activeChannel, selected, selectedEpisode, settings, advanceEpisode, closePlayer } = useApp();
  if (!activeStream?.url) return null;

  const ytId = youTubeId(activeStream.url);
  const title = activeChannel?.name ?? selected?.title ?? activeStream.source;

  if (ytId) {
    return (
      <section className="player-overlay">
        <iframe
          className="player-youtube"
          src={`https://www.youtube-nocookie.com/embed/${ytId}?autoplay=1&rel=0&modestbranding=1`}
          title={title}
          allow="autoplay; encrypted-media; fullscreen"
          allowFullScreen
        />
        <div className="player-top show">
          <div><p className="eyebrow">Trailer</p><h2>{title}</h2></div>
          <button className="player-icon-btn" onClick={closePlayer} aria-label="Close"><X size={24} /></button>
        </div>
      </section>
    );
  }

  const canAdvance = Boolean(selected?.mediaType === "tv" && selectedEpisode && !activeChannel);
  return (
    <VideoPlayer
      title={title}
      subtitleLabel={selectedEpisode ? `S${selectedEpisode.season} E${selectedEpisode.episode}` : null}
      stream={activeStream}
      item={selected}
      settings={settings}
      canAdvance={canAdvance}
      onAdvance={advanceEpisode}
      onClose={closePlayer}
    />
  );
}

function VideoPlayer({ title, subtitleLabel, stream, item, settings, canAdvance, onAdvance, onClose }: {
  title: string;
  subtitleLabel: string | null;
  stream: StreamSource;
  item: MediaItem | null;
  settings: AppSettings;
  canAdvance: boolean;
  onAdvance: () => Promise<boolean>;
  onClose: () => void;
}) {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const videoRef = useRef<HTMLVideoElement | null>(null);
  const lastSavedRef = useRef(0);
  const hideTimer = useRef<ReturnType<typeof setTimeout> | null>(null);

  const [playing, setPlaying] = useState(false);
  const [current, setCurrent] = useState(0);
  const [duration, setDuration] = useState(0);
  const [buffered, setBuffered] = useState(0);
  const [volume, setVolume] = useState(1);
  const [muted, setMuted] = useState(false);
  const [buffering, setBuffering] = useState(true);
  const [showControls, setShowControls] = useState(true);
  const [fullscreen, setFullscreen] = useState(false);
  const [error, setError] = useState(false);

  useEffect(() => {
    const video = videoRef.current;
    if (!video || !stream.url) return undefined;
    setError(false);
    setBuffering(true);
    const headers = stream.behaviorHints?.proxyHeaders?.request;
    const playbackUrl = headers ? proxiedUrl(stream.url, headers) : stream.url;
    const detach = attachPlayback(video, playbackUrl);
    const onErr = () => setError(true);
    video.addEventListener("error", onErr);
    return () => {
      video.removeEventListener("error", onErr);
      detach?.();
    };
  }, [stream]);

  useEffect(() => {
    const video = videoRef.current;
    if (!video) return undefined;
    const onTime = () => setCurrent(video.currentTime);
    const onDur = () => setDuration(video.duration || 0);
    const onPlay = () => setPlaying(true);
    const onPause = () => setPlaying(false);
    const onWaiting = () => setBuffering(true);
    const onPlaying = () => setBuffering(false);
    const onProgress = () => {
      if (video.buffered.length) setBuffered(video.buffered.end(video.buffered.length - 1));
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
    if (!video || !item) return undefined;
    void traktClient.scrobble("start", { mediaType: item.mediaType, tmdbId: item.id, progress: item.progress ?? 0 }).catch(() => undefined);
    const save = () => {
      if (!authClient.session || !Number.isFinite(video.duration) || video.duration <= 0) return;
      const now = Date.now();
      if (now - lastSavedRef.current < 15_000) return;
      lastSavedRef.current = now;
      const progress = Math.min(1, Math.max(0, video.currentTime / video.duration));
      void saveProgress(authClient, {
        media_type: item.mediaType,
        show_tmdb_id: item.id,
        title: item.title,
        progress,
        duration_seconds: Math.round(video.duration),
        position_seconds: Math.round(video.currentTime),
        backdrop_path: item.backdrop?.replace(config.backdropBase, "") ?? null,
        poster_path: item.image?.replace(config.imageBase, "") ?? null,
        source: stream.addonName,
        stream_addon_id: stream.addonId ?? null,
        stream_title: stream.source
      }).catch(() => undefined);
    };
    const onEnded = () => {
      void traktClient.scrobble("stop", { mediaType: item.mediaType, tmdbId: item.id, progress: 100 }).catch(() => undefined);
      if (settings.autoPlayNext && canAdvance) void onAdvance();
    };
    video.addEventListener("timeupdate", save);
    video.addEventListener("pause", save);
    video.addEventListener("ended", onEnded);
    return () => {
      save();
      video.removeEventListener("timeupdate", save);
      video.removeEventListener("pause", save);
      video.removeEventListener("ended", onEnded);
    };
  }, [item, stream, settings.autoPlayNext, canAdvance, onAdvance]);

  const flashControls = useCallback(() => {
    setShowControls(true);
    if (hideTimer.current) clearTimeout(hideTimer.current);
    hideTimer.current = setTimeout(() => {
      if (videoRef.current && !videoRef.current.paused) setShowControls(false);
    }, 3000);
  }, []);

  const togglePlay = useCallback(() => {
    const video = videoRef.current;
    if (!video) return;
    if (video.paused) void video.play().catch(() => undefined);
    else video.pause();
    flashControls();
  }, [flashControls]);

  const seekBy = useCallback((delta: number) => {
    const video = videoRef.current;
    if (!video) return;
    video.currentTime = Math.max(0, Math.min((video.duration || 0), video.currentTime + delta));
    flashControls();
  }, [flashControls]);

  const toggleFullscreen = useCallback(() => {
    const el = containerRef.current;
    if (!el) return;
    if (!document.fullscreenElement) void el.requestFullscreen?.().catch(() => undefined);
    else void document.exitFullscreen?.().catch(() => undefined);
  }, []);

  const cycleSubtitles = useCallback(() => {
    const video = videoRef.current;
    if (!video) return;
    const tracks = Array.from(video.textTracks);
    if (!tracks.length) return;
    const activeIndex = tracks.findIndex((t) => t.mode === "showing");
    tracks.forEach((t) => { t.mode = "disabled"; });
    const next = activeIndex + 1;
    if (next < tracks.length) tracks[next].mode = "showing";
    flashControls();
  }, [flashControls]);

  useEffect(() => {
    const onFs = () => setFullscreen(Boolean(document.fullscreenElement));
    document.addEventListener("fullscreenchange", onFs);
    return () => document.removeEventListener("fullscreenchange", onFs);
  }, []);

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      switch (e.key) {
        case " ": case "k": e.preventDefault(); togglePlay(); break;
        case "ArrowLeft": case "j": seekBy(-10); break;
        case "ArrowRight": case "l": seekBy(10); break;
        case "ArrowUp": { const v = videoRef.current; if (v) { v.volume = Math.min(1, v.volume + 0.1); flashControls(); } break; }
        case "ArrowDown": { const v = videoRef.current; if (v) { v.volume = Math.max(0, v.volume - 0.1); flashControls(); } break; }
        case "m": { const v = videoRef.current; if (v) v.muted = !v.muted; break; }
        case "f": toggleFullscreen(); break;
        case "Escape": if (!document.fullscreenElement) onClose(); break;
        default: break;
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [togglePlay, seekBy, toggleFullscreen, flashControls, onClose]);

  const cueCss = `.player-overlay video::cue { color: ${settings.subtitleColor}; font-size: ${Math.max(60, Math.min(200, settings.subtitleSize))}%; background: rgba(0,0,0,0.5); }`;
  const pct = duration > 0 ? (current / duration) * 100 : 0;
  const bufPct = duration > 0 ? (buffered / duration) * 100 : 0;

  return (
    <section
      ref={containerRef}
      className={`player-overlay ${showControls ? "controls-on" : "controls-off"}`}
      onMouseMove={flashControls}
    >
      <style>{cueCss}</style>
      <video ref={videoRef} autoPlay playsInline onClick={togglePlay} poster={item?.backdrop ?? undefined}>
        {(stream.subtitles ?? []).map((subtitle) => (
          <track
            key={subtitle.id || subtitle.url}
            kind="subtitles"
            srcLang={subtitle.lang || "en"}
            label={subtitle.label || subtitle.lang || "Subtitle"}
            src={`/api/subtitle?url=${encodeURIComponent(subtitle.url)}`}
            default={Boolean(settings.defaultSubtitle && subtitle.lang?.toLowerCase().startsWith(settings.defaultSubtitle.toLowerCase()))}
          />
        ))}
      </video>

      {buffering && !error && <div className="player-spinner"><Loader2 size={56} /></div>}
      {error && (
        <div className="player-error">
          <p>This source could not be played in the browser.</p>
          <span>Direct MP4/HLS streams play here; MKV/torrent sources need the Android app.</span>
        </div>
      )}
      {!playing && !buffering && !error && (
        <button className="player-bigplay" onClick={togglePlay} aria-label="Play"><Play size={48} fill="currentColor" /></button>
      )}

      <div className="player-top">
        <div className="player-top-left">
          <button className="player-icon-btn" onClick={onClose} aria-label="Back"><ArrowLeft size={24} /></button>
          <div>
            <p className="eyebrow">{stream.addonName}{subtitleLabel ? ` · ${subtitleLabel}` : ""}</p>
            <h2>{title}</h2>
          </div>
        </div>
        <div className="player-top-actions">
          {canAdvance && <button className="player-next" onClick={() => void onAdvance()}><SkipForward size={18} /> Next</button>}
          <button className="player-icon-btn" onClick={onClose} aria-label="Close"><X size={22} /></button>
        </div>
      </div>

      <div className="player-controls">
        <input
          className="scrubber"
          type="range"
          min={0}
          max={duration || 0}
          step={0.1}
          value={current}
          style={{ ["--pct" as string]: `${pct}%`, ["--buf" as string]: `${bufPct}%` }}
          onChange={(e) => { const v = videoRef.current; if (v) v.currentTime = Number(e.target.value); }}
        />
        <div className="player-controls-row">
          <button className="player-icon-btn" onClick={togglePlay} aria-label={playing ? "Pause" : "Play"}>
            {playing ? <Pause size={22} fill="currentColor" /> : <Play size={22} fill="currentColor" />}
          </button>
          <span className="player-time">{fmt(current)} <em>/</em> {fmt(duration)}</span>
          <div className="player-volume">
            <button className="player-icon-btn" onClick={() => { const v = videoRef.current; if (v) v.muted = !v.muted; }} aria-label="Mute">
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
              onChange={(e) => { const v = videoRef.current; if (v) { v.volume = Number(e.target.value); v.muted = Number(e.target.value) === 0; } }}
            />
          </div>
          <div className="player-spacer" />
          {(stream.subtitles?.length ?? 0) > 0 && (
            <button className="player-icon-btn" onClick={cycleSubtitles} aria-label="Subtitles"><Subtitles size={20} /></button>
          )}
          <span className="player-quality">{stream.quality || "HD"}</span>
          <button className="player-icon-btn" onClick={toggleFullscreen} aria-label="Fullscreen">
            {fullscreen ? <Minimize size={20} /> : <Maximize size={20} />}
          </button>
        </div>
      </div>
    </section>
  );
}

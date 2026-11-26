"use client";
import { Maximize2, Plus, Volume2, VolumeX, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { attachPlayback, preferNativeHls, type PlaybackHandle, type PlaybackTracks } from "@/lib/player";
import { planLiveAttempts, type LiveAttempt } from "@/lib/liveTvPlayback";
import { getMediaCapabilities } from "@/lib/capabilities";
import { resolverMediaUrl } from "@/lib/resolver";
import { proxiedUrl } from "@/lib/http";
import { config } from "@/lib/config";
import { resolveStalkerChannel } from "@/lib/stalker";
import { directManifestUrl, liveTvProxyHeaders, workerManifestUrl } from "@/components/player/overlay/helpers";
import { audioAfterRemoval, multiviewMaxHeight, type MultiviewTile } from "@/lib/multiview";
import type { IptvChannel } from "@/lib/types";
import "./multiview.css";

const STARTUP_MS = 15_000;

function attemptsFor(channel: IptvChannel): LiveAttempt[] {
  const caps = getMediaCapabilities();
  const headers = channel.requestHeaders;
  const plan = planLiveAttempts({ url: channel.streamUrl, headers }, {
    pageProtocol: window.location.protocol, mse: caps.mse, nativeHls: caps.nativeHls, preferNativeHls: preferNativeHls(),
  }, {
    relay: (target) => resolverMediaUrl(target, { ...liveTvProxyHeaders(), ...headers }),
    manifestRelay: (target) => workerManifestUrl(target, headers),
    manifestDirect: (target) => directManifestUrl(target, headers),
    appProxy: config.allowNetlifyMediaProxy ? (target) => proxiedUrl(target, { ...liveTvProxyHeaders(), ...headers }) : undefined,
  });
  return plan.attempts;
}

type TileState = "loading" | "playing" | "failed";

/** One game: walks its channels and each channel's routes until one plays. */
function MultiviewTileView({ tile, hasAudio, count, onSelect, onFullscreen, onRemove }: {
  tile: MultiviewTile; hasAudio: boolean; count: number;
  onSelect: () => void; onFullscreen: (channel: IptvChannel) => void; onRemove: () => void;
}) {
  const translateUi = useTranslation();
  const video = useRef<HTMLVideoElement>(null);
  const handle = useRef<PlaybackHandle | null>(null);
  const tracks = useRef<PlaybackTracks | null>(null);
  const [state, setState] = useState<TileState>("loading");
  const [playing, setPlaying] = useState<IptvChannel>(tile.channels[0]);
  const maxHeight = multiviewMaxHeight(hasAudio, count);
  const maxHeightRef = useRef(maxHeight);
  maxHeightRef.current = maxHeight;

  const applyQuality = () => {
    const qualities = tracks.current?.qualities ?? [];
    if (!qualities.length || !handle.current) return;
    const fitting = qualities.filter(q => (q.height ?? 0) > 0 && (q.height ?? 0) <= maxHeightRef.current);
    const best = fitting.sort((a, b) => (b.height ?? 0) - (a.height ?? 0))[0];
    handle.current.selectQuality(maxHeightRef.current === Infinity || !best ? null : best.id);
  };

  useEffect(() => {
    const element = video.current;
    if (!element) return;
    let cancelled = false;
    let timer: number | undefined;
    const channels = tile.channels;
    const run = async (channelIndex: number, attemptIndex: number, attempts?: LiveAttempt[]) => {
      if (cancelled) return;
      let channel = channels[channelIndex];
      if (!channel) { setState("failed"); return; }
      if (!attempts) {
        try { if (channel.stalker) channel = await resolveStalkerChannel(channel); }
        catch { void run(channelIndex + 1, 0); return; }
        if (cancelled) return;
        attempts = attemptsFor(channel);
        setPlaying(channel);
      }
      const attempt = attempts[attemptIndex];
      if (!attempt) { void run(channelIndex + 1, 0); return; }
      setState("loading");
      handle.current?.destroy();
      tracks.current = null;
      const next = () => { window.clearTimeout(timer); if (!cancelled) void run(channelIndex, attemptIndex + 1, attempts); };
      handle.current = attachPlayback(element, attempt.url, {
        live: true, transport: attempt.transport, hlsEngine: attempt.hlsEngine, requestHeaders: attempt.headers,
        onError: next,
        onTracks: (value) => { tracks.current = value; applyQuality(); },
      });
      timer = window.setTimeout(() => { if (element.readyState < 1) next(); }, attempt.startupMs ?? STARTUP_MS);
      element.play().catch(() => { /* Autoplay of a muted tile is allowed; a sound tile may need a click. */ });
    };
    const onPlaying = () => { window.clearTimeout(timer); setState("playing"); };
    element.addEventListener("playing", onPlaying);
    void run(0, 0);
    return () => {
      cancelled = true;
      window.clearTimeout(timer);
      element.removeEventListener("playing", onPlaying);
      handle.current?.destroy();
      handle.current = null;
    };
  }, [tile]);

  useEffect(() => {
    const element = video.current;
    if (!element) return;
    element.muted = !hasAudio;
    if (hasAudio) element.play().catch(() => undefined);
    applyQuality();
  }, [hasAudio, count]);

  return <div className={`tv-multiview-tile${hasAudio ? " has-audio" : ""}`} tabIndex={0} role="group" aria-label={tile.title}
    onClick={onSelect} onKeyDown={(event) => { if (event.key === "Enter" && event.target === event.currentTarget) onSelect(); }}>
    <video ref={video} muted playsInline autoPlay />
    <div className="tv-multiview-label">{hasAudio ? <Volume2 size={14} /> : <VolumeX size={14} />}<span>{tile.title}</span></div>
    <div className="tv-multiview-actions">
      <button type="button" onClick={(event) => { event.stopPropagation(); onFullscreen(playing); }} aria-label={translateUi("Full screen")}><Maximize2 size={16} /></button>
      <button type="button" onClick={(event) => { event.stopPropagation(); onRemove(); }} aria-label={translateUi("Remove")}><X size={16} /></button>
    </div>
    {state === "loading" && <span className="tv-multiview-status" role="status">{translateUi("Loading…")}</span>}
    {state === "failed" && <span className="tv-multiview-status">{translateUi("Channel offline")}</span>}
  </div>;
}

/**
 * Up to four live games at once in the browser. Click a game to hear it; the
 * others play muted at a lower quality. Each tile uses one connection of the
 * user's own IPTV subscription.
 */
export function MultiviewOverlay({ tiles, capacity, candidates, onAdd, onRemove, onFullscreen, onClose }: {
  tiles: MultiviewTile[]; capacity: number; candidates: MultiviewTile[];
  onAdd: (tile: MultiviewTile) => void; onRemove: (index: number) => void;
  onFullscreen: (channel: IptvChannel) => void; onClose: () => void;
}) {
  const translateUi = useTranslation();
  const [audio, setAudio] = useState(0);
  const [picking, setPicking] = useState(false);
  const root = useRef<HTMLDivElement>(null);
  useEffect(() => { root.current?.focus({ preventScroll: true }); }, []);
  useEffect(() => { setAudio(current => Math.min(current, Math.max(0, tiles.length - 1))); }, [tiles.length]);
  const present = new Set(tiles.map(tile => tile.key));
  const available = candidates.filter(tile => !present.has(tile.key));
  const canAdd = tiles.length < capacity;
  return <div className="tv-multiview" ref={root} tabIndex={-1} role="dialog" aria-modal="true" aria-label={translateUi("Multiview")}
    onKeyDown={(event) => {
      if (event.key !== "Escape") return;
      event.preventDefault(); event.stopPropagation();
      if (picking) setPicking(false); else onClose();
    }}>
    <header>
      <h2>{translateUi("Multiview")}</h2>
      {canAdd && <button type="button" className="secondary" onClick={() => setPicking(true)}><Plus size={16} />{translateUi("Add")}</button>}
      <button type="button" onClick={onClose} aria-label={translateUi("Close")}><X /></button>
    </header>
    <div className={`tv-multiview-grid is-${Math.max(1, tiles.length)}`}>
      {tiles.map((tile, index) => <MultiviewTileView key={tile.key} tile={tile} hasAudio={index === audio} count={tiles.length}
        onSelect={() => setAudio(index)} onFullscreen={onFullscreen}
        onRemove={() => { setAudio(current => audioAfterRemoval(current, index, tiles.length - 1)); onRemove(index); }} />)}
    </div>
    {picking && <div className="tv-multiview-picker" role="dialog" aria-label={translateUi("Live now")}>
      <header><h3>{translateUi("Live now")}</h3><button type="button" onClick={() => setPicking(false)} aria-label={translateUi("Close")}><X /></button></header>
      {!available.length && <p>{translateUi("No channels")}</p>}
      {available.map(tile => <button type="button" key={tile.key} className="tv-event-source" onClick={() => { onAdd(tile); setAudio(tiles.length); setPicking(false); }}>
        <span><strong>{tile.title}</strong><small>{tile.channels[0]?.name}</small></span></button>)}
    </div>}
  </div>;
}

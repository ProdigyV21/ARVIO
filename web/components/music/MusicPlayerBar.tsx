"use client";
import "./music.css";

import { ChevronDown, Heart, ListMusic, Loader2, Pause, Play, Repeat, Repeat1, Shuffle, SkipBack, SkipForward, X } from "lucide-react";
import { useEffect, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { isFavoriteTrack, toggleFavoriteTrack, useMusicLibrary } from "@/lib/music/library";
import { currentTrack, cycleRepeat, jumpTo, next, previous, removeFromQueue, seek, setExpanded, stopMusic, toggleShuffle, togglePlay, useMusicPlayer } from "@/lib/music/player";
import { blockedOnThisPage } from "@/lib/music/sources";
import { formatDuration } from "@/lib/music/text";

function Art({ src, className }: { src: string | null | undefined; className: string }) {
  const [failed, setFailed] = useState(false);
  useEffect(() => setFailed(false), [src]);
  return <div className={className}>{src && !failed && !blockedOnThisPage(src) ? <img src={src} alt="" onError={() => setFailed(true)} /> : <ListMusic aria-hidden="true" />}</div>;
}

function Progress({ position, duration, label }: { position: number; duration: number; label: string }) {
  const max = Math.max(duration, 1);
  return (
    <div className="music-progress">
      <span>{formatDuration(position) || "0:00"}</span>
      <input type="range" min={0} max={max} step={1} value={Math.min(position, max)} onChange={(event) => seek(Number(event.target.value))} aria-label={label}
        style={{ ["--fill" as string]: `${(Math.min(position, max) / max) * 100}%` }} />
      <span>{formatDuration(duration)}</span>
    </div>
  );
}

/** The mini player above the navigation, and the full player it opens. Lives in the app shell, so music keeps playing on every screen. */
export function MusicPlayerBar({ hidden }: { hidden: boolean }) {
  const translateUi = useTranslation();
  const player = useMusicPlayer();
  const { collections } = useMusicLibrary();
  const track = currentTrack(player);
  const [showQueue, setShowQueue] = useState(false);

  useEffect(() => {
    if (!player.expanded) return undefined;
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Escape" || event.key === "GoBack" || event.key === "BrowserBack") { event.preventDefault(); event.stopPropagation(); setExpanded(false); }
    };
    window.addEventListener("keydown", onKey, true);
    return () => window.removeEventListener("keydown", onKey, true);
  }, [player.expanded]);

  useEffect(() => {
    document.documentElement.classList.toggle("has-music-bar", Boolean(track) && !hidden);
    return () => document.documentElement.classList.remove("has-music-bar");
  }, [track, hidden]);

  if (!track || hidden) return null;
  const liked = isFavoriteTrack(collections, track.id);
  const playLabel = player.playing ? translateUi("Pause") : translateUi("Play");
  const PlayIcon = player.loading ? Loader2 : player.playing ? Pause : Play;
  const RepeatIcon = player.repeat === "one" ? Repeat1 : Repeat;

  if (player.expanded) {
    return (
      <div className="music-full" role="dialog" aria-modal="true" aria-label={translateUi("Now playing")}>
        <div className="music-full-backdrop" style={track.coverUrl && !blockedOnThisPage(track.coverUrl) ? { backgroundImage: `url("${track.coverUrl}")` } : undefined} />
        <div className="music-full-inner">
          <div className="music-full-top">
            <button type="button" className="music-icon-btn is-large" onClick={() => setExpanded(false)} aria-label={translateUi("Close player")}><ChevronDown size={26} /></button>
            <button type="button" className={`music-icon-btn is-large ${showQueue ? "is-on" : ""}`} onClick={() => setShowQueue((v) => !v)} aria-pressed={showQueue} aria-label={translateUi("Up next")}><ListMusic size={22} /></button>
          </div>
          {showQueue ? (
            <div className="music-queue">
              <h2>{translateUi("Up next")}</h2>
              <ol>
                {player.queue.map((item, index) => (
                  <li key={`${item.id}-${index}`} className={index === player.index ? "is-current" : index < player.index ? "is-past" : ""}>
                    <button type="button" onClick={() => jumpTo(index)}>
                      <Art src={item.coverUrl} className="music-queue-art" />
                      <span><strong>{item.title}</strong><small>{item.artist}</small></span>
                    </button>
                    {index !== player.index && <button type="button" className="music-icon-btn" onClick={() => removeFromQueue(index)} aria-label={translateUi("Remove")}><X size={16} /></button>}
                  </li>
                ))}
              </ol>
            </div>
          ) : (
            <Art src={track.coverUrl} className="music-full-art" />
          )}
          <div className="music-full-meta">
            <div>
              <h2>{track.title}</h2>
              <p>{track.artist}{track.album ? ` · ${track.album}` : ""}</p>
            </div>
            <button type="button" className={`music-icon-btn is-large ${liked ? "is-on" : ""}`} aria-pressed={liked} onClick={() => toggleFavoriteTrack(track)} aria-label={liked ? translateUi("Remove from liked songs") : translateUi("Add to liked songs")}>
              <Heart size={24} fill={liked ? "currentColor" : "none"} />
            </button>
          </div>
          <Progress position={player.position} duration={player.duration || track.durationSec} label={translateUi("Position")} />
          <div className="music-full-controls">
            <button type="button" className={`music-icon-btn is-large ${player.shuffle ? "is-on" : ""}`} aria-pressed={player.shuffle} onClick={toggleShuffle} aria-label={translateUi("Shuffle")}><Shuffle size={22} /></button>
            <button type="button" className="music-icon-btn is-large" onClick={previous} aria-label={translateUi("Previous")}><SkipBack size={28} fill="currentColor" /></button>
            <button type="button" className="music-play-btn" onClick={togglePlay} aria-label={playLabel}><PlayIcon size={32} fill={player.loading ? "none" : "currentColor"} className={player.loading ? "music-spin" : ""} /></button>
            <button type="button" className="music-icon-btn is-large" onClick={() => next()} aria-label={translateUi("Next")}><SkipForward size={28} fill="currentColor" /></button>
            <button type="button" className={`music-icon-btn is-large ${player.repeat !== "off" ? "is-on" : ""}`} aria-pressed={player.repeat !== "off"} onClick={cycleRepeat} aria-label={translateUi("Repeat")}><RepeatIcon size={22} /></button>
          </div>
          {player.notice && <p className="music-notice" role="status">{player.notice}</p>}
        </div>
      </div>
    );
  }

  const fill = player.duration > 0 ? (player.position / player.duration) * 100 : 0;
  return (
    <div className="music-bar" role="region" aria-label={translateUi("Now playing")}>
      <div className="music-bar-progress" style={{ width: `${fill}%` }} aria-hidden="true" />
      <button type="button" className="music-bar-open" onClick={() => setExpanded(true)} aria-label={translateUi("Open player")}>
        <Art src={track.coverUrl} className="music-bar-art" />
        <span className="music-bar-text">
          <strong>{track.title}</strong>
          <small>{player.notice ?? track.artist}</small>
        </span>
      </button>
      <button type="button" className={`music-icon-btn music-bar-like ${liked ? "is-on" : ""}`} aria-pressed={liked} onClick={() => toggleFavoriteTrack(track)} aria-label={liked ? translateUi("Remove from liked songs") : translateUi("Add to liked songs")}>
        <Heart size={20} fill={liked ? "currentColor" : "none"} />
      </button>
      <button type="button" className="music-icon-btn music-bar-prev" onClick={previous} aria-label={translateUi("Previous")}><SkipBack size={20} fill="currentColor" /></button>
      <button type="button" className="music-icon-btn music-bar-play" onClick={togglePlay} aria-label={playLabel}><PlayIcon size={24} fill={player.loading ? "none" : "currentColor"} className={player.loading ? "music-spin" : ""} /></button>
      <button type="button" className="music-icon-btn" onClick={() => next()} aria-label={translateUi("Next")}><SkipForward size={20} fill="currentColor" /></button>
      <button type="button" className="music-icon-btn music-bar-close" onClick={stopMusic} aria-label={translateUi("Close player")}><X size={18} /></button>
    </div>
  );
}

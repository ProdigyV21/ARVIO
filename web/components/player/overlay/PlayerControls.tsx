// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { Pause, Play, Maximize, X, Minimize, ArrowLeft, Airplay, PictureInPicture2, RotateCcw, RotateCw, VolumeX, Volume2, Subtitles, AudioLines, Folder, SkipForward, Settings } from "lucide-react";
import { showAirPlayPicker } from "@/lib/playerPlatform";
import { fmt, SKIP_SECONDS, type PlayerPanel } from "./helpers";
import type { useLivePlayerDock } from "../useLivePlayerDock";
import type { StreamSource } from "@/lib/types";
import type { PlaybackHandle } from "@/lib/player";
import { useTranslation } from "@/lib/i18n";
import { memo, type RefObject, type Dispatch, type SetStateAction } from "react";

/** Controls of the docked Live TV mini-player. */
export function LiveDockControls({ buffering, dock, error, onClose, playing, togglePlay }: { buffering: boolean; dock: ReturnType<typeof useLivePlayerDock>; error: boolean; onClose: () => void; playing: boolean; togglePlay: () => void }) {
  const translateUi = useTranslation();
  return (<>
      {dock.docked && <div className="player-dock-controls">
        <span role="status">{error ? translateUi("Unavailable") : buffering ? translateUi("Connecting") : playing ? translateUi("LIVE") : translateUi("Paused")}</span>
        <button type="button" onClick={togglePlay} aria-label={playing ? translateUi("Pause") : translateUi("Play")} title={playing ? translateUi("Pause") : translateUi("Play")}>{playing ? <Pause size={20} /> : <Play size={20} />}</button>
        <button type="button" onClick={dock.expand} aria-label={translateUi("Expand player")} title={translateUi("Expand player")}><Maximize size={20} /></button>
        <button type="button" onClick={onClose} aria-label={translateUi("Stop channel")} title={translateUi("Stop channel")}><X size={20} /></button>
      </div>}
      {!dock.docked && dock.canDock && <button type="button" className="player-dock-return" onClick={dock.collapse} aria-label={translateUi("Return to guide")} title={translateUi("Return to guide")}><Minimize size={22} /></button>}
  </>);
}

/**
 * Title, AirPlay / picture in picture and close. Memoized: nothing it shows
 * changes while the clock ticks, so time updates skip it.
 */
export const PlayerTopBar = memo(function PlayerTopBar({ airPlayAllowed, airPlayAvailable, airPlayBlockedReason, mediaMeta, onClose, onToast, pauseOverview, pipActive, pipAvailable, playing, stream, title, togglePip, videoRef }: { airPlayAllowed: boolean; airPlayAvailable: boolean; airPlayBlockedReason: string | null; mediaMeta: string; onClose: () => void; onToast: (message: string) => void; pauseOverview: string; pipActive: boolean; pipAvailable: boolean; playing: boolean; stream: StreamSource; title: string; togglePip: () => void; videoRef: RefObject<HTMLVideoElement | null> }) {
  const translateUi = useTranslation();
  return (<>
      <div className="player-top">
        <div className="player-top-left player-metadata">
          <button type="button" className="player-icon-btn" onClick={onClose} aria-label={translateUi("Back")}><ArrowLeft size={24} /></button>
          <span className="player-accent-rail" />
          <div>
            <p className="eyebrow">{mediaMeta || stream.source}</p>
            <h2>{title}</h2>
            {!playing && pauseOverview && <p className="player-overview">{pauseOverview}</p>}
          </div>
        </div>
        <div className="player-top-actions">
          {airPlayAvailable && (airPlayAllowed
            ? <button type="button" className="player-icon-btn player-platform-btn" onClick={() => showAirPlayPicker(videoRef.current)} aria-label={translateUi("AirPlay")} title={translateUi("AirPlay")}><Airplay size={20} /></button>
            : <button type="button" className="player-icon-btn player-platform-btn is-unavailable" onClick={() => onToast(airPlayBlockedReason ?? "AirPlay is not available for this stream.")} aria-label={translateUi("AirPlay unavailable")} title={translateUi(airPlayBlockedReason ?? "AirPlay is not available for this stream.")}><Airplay size={20} /></button>)}
          {pipAvailable && <button type="button" className={`player-icon-btn player-platform-btn ${pipActive ? "is-active" : ""}`} onClick={togglePip} aria-label={translateUi("Picture in picture")} title={translateUi("Picture in picture")}><PictureInPicture2 size={20} /></button>}
          <button type="button" className="player-icon-btn" onClick={onClose} aria-label={translateUi("Close")}><X size={22} /></button>
        </div>
      </div>
  </>);
});

/** The bottom bar: scrubber, play/seek, volume, time and the panel buttons. */
export function PlayerControlsBar({ activePanel, appleMobile, badges, bufPct, canAdvance, duration, error, fullscreen, hoverPct, hoverTime, liveTv, muted, openPanel, pct, playing, playNextNow, scrubDisplayTime, seekBy, setHoverPct, setHoverTime, setScrubTo, toggleFullscreen, togglePlay, transportRef, videoRef, volume }: { activePanel: PlayerPanel; appleMobile: boolean; badges: string[]; bufPct: number; canAdvance: boolean; duration: number; error: boolean; fullscreen: boolean; hoverPct: number; hoverTime: number | null; liveTv: boolean; muted: boolean; openPanel: (panel: Exclude<PlayerPanel, null>) => void; pct: number; playing: boolean; playNextNow: () => void; scrubDisplayTime: number; seekBy: (delta: number) => void; setHoverPct: Dispatch<SetStateAction<number>>; setHoverTime: Dispatch<SetStateAction<number | null>>; setScrubTo: Dispatch<SetStateAction<number | null>>; toggleFullscreen: () => void; togglePlay: () => void; transportRef: RefObject<PlaybackHandle | null>; videoRef: RefObject<HTMLVideoElement | null>; volume: number }) {
  const translateUi = useTranslation();
  return (<>
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
              aria-label={translateUi("Seek")}
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
            <button type="button" className="player-icon-btn player-play-btn" onClick={togglePlay} aria-label={playing && !error ? translateUi("Pause") : translateUi("Play")}>
              {playing && !error ? <Pause size={24} fill="currentColor" /> : <Play size={24} fill="currentColor" />}
            </button>
            {!liveTv && (
              <>
                <button type="button" className="player-icon-btn player-seek-btn" onClick={() => seekBy(-SKIP_SECONDS)} aria-label={translateUi("Back {value0} seconds", { value0: SKIP_SECONDS })}>
                  <RotateCcw size={22} /><span className="player-seek-label">{SKIP_SECONDS}</span>
                </button>
                <button type="button" className="player-icon-btn player-seek-btn" onClick={() => seekBy(SKIP_SECONDS)} aria-label={translateUi("Forward {value0} seconds", { value0: SKIP_SECONDS })}>
                  <RotateCw size={22} /><span className="player-seek-label">{SKIP_SECONDS}</span>
                </button>
              </>
            )}
            {/* iOS ignores page volume (hardware buttons only): no dead slider there. */}
            {!appleMobile && <div className="player-volume">
              <button type="button" className="player-icon-btn" onClick={() => { const v = videoRef.current; if (v) { if (v.volume === 0) v.volume = 1; v.muted = !(v.muted || volume === 0); } }} aria-label={translateUi(muted || volume === 0 ? "Unmute" : "Mute")}>
                {muted || volume === 0 ? <VolumeX size={20} /> : <Volume2 size={20} />}
              </button>
              <input
                className="volume-slider"
                type="range"
                min={0}
                max={1}
                step={0.05}
                value={muted ? 0 : volume}
                aria-label={translateUi("Volume")}
                style={{ ["--pct" as string]: `${(muted ? 0 : volume) * 100}%` }}
                onChange={(e) => {
                  const v = videoRef.current;
                  if (v) {
                    v.volume = Number(e.target.value);
                    v.muted = Number(e.target.value) === 0;
                  }
                }}
              />
            </div>}
            {liveTv
              ? <button type="button" className="player-time player-live-indicator" title={translateUi("Return to live")} onClick={() => { if (transportRef.current?.goLive()) void videoRef.current?.play().catch(() => undefined); }}><span className="live-dot" /> {translateUi(" LIVE")}</button>
              : (
                <span className="player-time player-time-readout">
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
                <button type="button" className={`player-icon-btn ${activePanel === "subtitles" ? "is-active" : ""}`} onClick={() => openPanel("subtitles")} aria-label={translateUi("Subtitles")} aria-expanded={activePanel === "subtitles"}><Subtitles size={20} /></button>
                <button type="button" className={`player-icon-btn ${activePanel === "audio" ? "is-active" : ""}`} onClick={() => openPanel("audio")} aria-label={translateUi("Audio")} aria-expanded={activePanel === "audio"}><AudioLines size={20} /></button>
              </>
            )}
            <button type="button" className={`player-icon-btn ${activePanel === "sources" ? "is-active" : ""}`} onClick={() => openPanel("sources")} aria-label={translateUi("Sources")} aria-expanded={activePanel === "sources"}><Folder size={20} /></button>
            {canAdvance && <button type="button" className="player-icon-btn player-next-inline" onClick={playNextNow} aria-label={translateUi("Next episode")}><SkipForward size={20} /></button>}
            <button type="button" className={`player-icon-btn ${activePanel === "settings" ? "is-active" : ""}`} onClick={() => openPanel("settings")} aria-label={translateUi("Player settings")} aria-expanded={activePanel === "settings"}><Settings size={20} /></button>
            <button type="button" className="player-icon-btn" onClick={toggleFullscreen} aria-label={translateUi("Fullscreen")}>
              {fullscreen ? <Minimize size={20} /> : <Maximize size={20} />}
            </button>
          </div>
        </div>
      </div>
  </>);
}

// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { Play, ArrowLeft, X, Loader2, TriangleAlert, RotateCw, Folder, ExternalLink, RotateCcw } from "lucide-react";
import { canProviderTranscode } from "@/lib/streamCompatibility";
import { MediaCard } from "@/components/media/MediaCard";
import type { ExternalPlayerOption } from "@/lib/externalPlayers";
import type { MediaItem, StreamSource } from "@/lib/types";
import type { LiveFailure } from "@/lib/liveTvPlayback";
import "./liveFailure.css";
import type { PlayerPanel } from "./helpers";
import { useTranslation } from "@/lib/i18n";
import { memo } from "react";

/** Tap-to-play prompt, boot screen, buffering spinner, error card, skip flash and the big play button. */
export function PlayerStatusLayers({ booted, bootLogo, bootStatus, bufferAheadSec, buffering, error, errorDetail, externalOptions, item, liveFailure, liveTv, onClose, onSelectStream, openExternalOption, openPanel, playBlocked, playing, retryPlayback, showEndScreen, skipOverlay, stream, subtitleLabel, title, togglePlay }: { booted: boolean; bootLogo: string | null; bootStatus: { label: string; values?: Record<string, string | number>; }; bufferAheadSec: number; buffering: boolean; error: boolean; errorDetail: string; externalOptions: ExternalPlayerOption[]; item: MediaItem | null; liveFailure?: LiveFailure | null; liveTv: boolean; onClose: () => void; onSelectStream: (next: StreamSource, options?: { forceTranscode?: boolean; forceRemux?: boolean; forceBrowser?: boolean; }) => void; openExternalOption: (option: ExternalPlayerOption, selectedStream: StreamSource) => void; openPanel: (panel: Exclude<PlayerPanel, null>) => void; playBlocked: boolean; playing: boolean; retryPlayback: () => void; showEndScreen: boolean; skipOverlay: number | null; stream: StreamSource; subtitleLabel: string | null; title: string; togglePlay: () => void }) {
  const translateUi = useTranslation();
  // Values such as "this browser" are UI words too.
  const failureValues = liveFailure?.values && Object.fromEntries(Object.entries(liveFailure.values)
    .map(([key, value]) => [key, typeof value === "string" ? translateUi(value) : value]));
  const externalButtons = externalOptions.map((option) => (
    <button key={option.id} type="button" className="player-error-external" onClick={() => openExternalOption(option, stream)}>
      <ExternalLink size={15} /> {option.id === "chooser" ? translateUi("Open in player") : translateUi("Open in {value0}", { value0: translateUi(option.label) })}</button>
  ));
  return (<>
      {playBlocked && !error && <div className="player-start-prompt">
        <button type="button" className="player-error-external" onClick={togglePlay}>
          <Play size={24} fill="currentColor" /> {translateUi("Play")}
        </button>
      </div>}
      {!booted && !error && !playBlocked && (
        <div className="player-boot" style={{ backgroundImage: item?.backdrop ? `url(${item.backdrop})` : undefined }}>
          <div className="player-boot-actions">
            <button type="button" className="player-icon-btn" onClick={onClose} aria-label={translateUi("Back")}><ArrowLeft size={24} /></button>
            <button type="button" className="player-icon-btn" onClick={onClose} aria-label={translateUi("Close")}><X size={22} /></button>
          </div>
          <div className="player-boot-center">
            {bootLogo
              ? <img className="player-boot-logo" src={bootLogo} alt={title} />
              : <h2 className="player-boot-title">{title}</h2>}
            <p className="player-boot-status" role="status" aria-live="polite">
              <Loader2 size={16} aria-hidden="true" /> {translateUi(bootStatus.label, bootStatus.values)}
            </p>
            {subtitleLabel ? <p className="player-boot-sub">{subtitleLabel}{item?.episodeTitle ? ` · ${item.episodeTitle}` : ""}</p> : null}
          </div>
        </div>
      )}
      {buffering && !error && booted && (
        <div className="player-spinner">
          <Loader2 size={56} />
          {/* Show how much is actually buffered ahead: a bare spinner cannot
              distinguish "downloading fine" from "wedged and going nowhere". */}
          {bufferAheadSec > 0 && <span className="player-buffer-health">{Math.round(bufferAheadSec)}{translateUi("s buffered")}</span>}
        </div>
      )}
      {error && (
        <div className="player-error" role="alert">
          <div className="player-error-card" data-failure={liveFailure?.kind}>
            <TriangleAlert size={30} aria-hidden="true" />
            <p>{liveFailure ? translateUi(liveFailure.title) : liveTv ? translateUi("This channel could not be played right now.") : translateUi("This source could not be played in the browser.")}</p>
            <span>
              {liveFailure ? translateUi(liveFailure.message, failureValues)
                : translateUi(errorDetail) || translateUi("The source could not be opened. Its network access, browser permissions or media format may be unsupported. Try another source or an external player.")}
            </span>
            <div className="player-error-actions">
              <button type="button" className="player-error-external" onClick={retryPlayback}>
                <RotateCw size={15} /> {translateUi("Retry")}</button>
              {!liveTv && <button type="button" className="player-error-external" onClick={() => openPanel("sources")}>
                <Folder size={15} /> {translateUi("Sources")}</button>}
              {!liveTv && externalButtons}
              {!liveTv && !stream.transcoded && canProviderTranscode(stream) && (
                <button type="button" className="player-error-transcode" onClick={() => onSelectStream(stream, { forceTranscode: true })}>
                  <Play size={15} fill="currentColor" /> {translateUi(" Transcode")}</button>
              )}
              <button type="button" className="player-error-external" onClick={onClose}>
                <ArrowLeft size={15} /> {translateUi("Back")}</button>
            </div>
            {/* Live TV plays in the browser first; outside players are the fallback, not the answer. */}
            {liveTv && externalButtons.length > 0 && (
              <div className="player-error-secondary">
                <small>{translateUi("Other ways to watch")}</small>
                <div className="player-error-actions">{externalButtons}</div>
              </div>
            )}
          </div>
        </div>
      )}
      {skipOverlay !== null && (
        <div className={`player-skip-overlay ${skipOverlay > 0 ? "forward" : "back"}`}>
          {skipOverlay > 0 ? <RotateCw size={34} /> : <RotateCcw size={34} />}
          <strong>{skipOverlay > 0 ? "+" : ""}{skipOverlay}{translateUi("s")}</strong>
        </div>
      )}
      {!playing && !buffering && !error && !showEndScreen && (
        <button type="button" className="player-bigplay" onClick={togglePlay} aria-label={translateUi("Play")}><Play size={48} fill="currentColor" /></button>
      )}
  </>);
}

/** The end screen (watch again, back, more like this). Memoized: time updates skip it. */
export const PlayerEndScreen = memo(function PlayerEndScreen({ item, onClose, onOpenDetails, showEndScreen, title, togglePlay }: { item: MediaItem | null; onClose: () => void; onOpenDetails: (item: MediaItem) => void; showEndScreen: boolean; title: string; togglePlay: () => void }) {
  const translateUi = useTranslation();
  return (<>
      {showEndScreen ? (
        <div className="player-end" role="dialog" aria-label={translateUi("Finished: {value0}", { value0: title })}>
          <div className="player-end-card">
            <p className="eyebrow">{translateUi("Finished")}</p>
            <h2>{title}</h2>
            <div className="player-end-actions">
              <button type="button" className="player-error-external" onClick={togglePlay}><RotateCcw size={16} /> {translateUi("Watch again")}</button>
              <button type="button" className="player-error-external" onClick={onClose}><ArrowLeft size={16} /> {translateUi("Back to details")}</button>
            </div>
            {item?.related?.length ? (
              <div className="player-end-related">
                <p className="eyebrow">{translateUi("More Like This")}</p>
                <div className="player-end-rail">
                  {item.related.slice(0, 6).map((related) => (
                    <MediaCard key={`end-${related.mediaType}-${related.id}`} item={related} onOpen={onOpenDetails} posterMode />
                  ))}
                </div>
              </div>
            ) : null}
          </div>
        </div>
      ) : null}
  </>);
});

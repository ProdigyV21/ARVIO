"use client";

import { Check, Clapperboard, MoreHorizontal, Play } from "lucide-react";
import type { MouseEvent } from "react";
import { useTranslation } from "@/lib/i18n";
import { IMDB_LOGO } from "@/lib/serviceLogos";
import type { EpisodeInfo } from "@/lib/types";
import { episodeImdbRating, formatEpisodeAirDate } from "@/lib/episodePresentation";

export function EpisodeCard({ episode, active, watched, progress, onPlay, onContextMenu, onMenu }: {
  episode: EpisodeInfo;
  active: boolean;
  watched: boolean;
  /** 0–100 while the episode is in progress (Android EpisodePlaybackProgress). */
  progress?: number | null;
  onPlay: () => void;
  onContextMenu: (event: MouseEvent<HTMLButtonElement>) => void;
  /** Opens the episode's actions (mark watched/unwatched) from a visible button. */
  onMenu: (anchor: HTMLElement) => void;
}) {
  const translateUi = useTranslation();
  const rating = episodeImdbRating(episode);
  const airDate = formatEpisodeAirDate(episode.airDate);
  const percent = progress != null && progress > 0 && !watched ? Math.max(2, Math.min(100, Math.round(progress))) : null;
  return (
    <div className={`episode-card-wrap ${active ? "is-active" : ""}`}>
      <button
        type="button"
        className={`episode-row episode-art-card ${active ? "is-active" : ""} ${watched ? "is-watched" : ""}`}
        onClick={onPlay}
        onContextMenu={onContextMenu}
        aria-label={[
          `S${episode.seasonNumber} E${episode.episodeNumber}`,
          episode.name,
          watched ? translateUi("Watched") : percent != null ? translateUi("{value0}% watched", { value0: percent }) : ""
        ].filter(Boolean).join(", ")}
      >
        <span className="episode-still">
          {episode.still ? <img src={episode.still} alt="" loading="lazy" /> : <Clapperboard size={24} />}
          <span className="episode-chip episode-chip-left">S{episode.seasonNumber} E{String(episode.episodeNumber).padStart(2, "0")}</span>
          {rating && <span className="episode-card-rating"><img src={IMDB_LOGO} alt="IMDb" loading="lazy" />{rating}</span>}
          <span className="episode-play"><Play size={18} fill="currentColor" /></span>
          <span className="episode-info">
            <strong>{episode.name}</strong>
            <span className="episode-subline">
              {airDate && <time dateTime={episode.airDate}>{airDate}</time>}
              {airDate && Boolean(episode.runtime) && <span aria-hidden="true"> / </span>}
              {Boolean(episode.runtime) && <span>{translateUi("{value0}m", { value0: episode.runtime! })}</span>}
            </span>
            <span className="episode-card-overview">{episode.overview || ""}</span>
          </span>
          {watched && <span className="episode-card-watched" aria-hidden="true"><Check size={14} /></span>}
          {percent != null && (
            <span className="episode-progress" aria-hidden="true"><span style={{ width: `${percent}%` }} /></span>
          )}
        </span>
      </button>
      <button
        type="button"
        className="episode-menu-button"
        aria-haspopup="dialog"
        aria-label={translateUi("Episode options for S{value0} E{value1}", { value0: episode.seasonNumber, value1: episode.episodeNumber })}
        onClick={(event) => onMenu(event.currentTarget)}
      >
        <MoreHorizontal size={18} />
      </button>
    </div>
  );
}

"use client";

import { Check, Clapperboard, Play } from "lucide-react";
import type { MouseEvent } from "react";
import { useTranslation } from "@/lib/i18n";
import { IMDB_LOGO } from "@/lib/serviceLogos";
import type { EpisodeInfo } from "@/lib/types";
import { episodeImdbRating, formatEpisodeAirDate } from "@/lib/episodePresentation";

export function EpisodeCard({ episode, active, watched, onPlay, onContextMenu }: {
  episode: EpisodeInfo;
  active: boolean;
  watched: boolean;
  onPlay: () => void;
  onContextMenu: (event: MouseEvent<HTMLButtonElement>) => void;
}) {
  const translateUi = useTranslation();
  const rating = episodeImdbRating(episode);
  const airDate = formatEpisodeAirDate(episode.airDate);
  return (
    <button
      type="button"
      className={`episode-row episode-art-card ${active ? "is-active" : ""} ${watched ? "is-watched" : ""}`}
      onClick={onPlay}
      onContextMenu={onContextMenu}
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
        {watched && <span className="episode-card-watched" aria-label={translateUi("Watched")}><Check size={14} /></span>}
      </span>
    </button>
  );
}

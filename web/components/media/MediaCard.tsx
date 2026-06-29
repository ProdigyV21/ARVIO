"use client";

import { Check, Clapperboard } from "lucide-react";
import { useEffect, useState } from "react";
import { getLogoUrl } from "@/lib/tmdb";
import type { MediaItem } from "@/lib/types";

export function MediaCard({ item, onOpen, onFocus, posterMode = false }: {
  item: MediaItem;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  posterMode?: boolean;
}) {
  const [logo, setLogo] = useState<string | null>(null);
  const progress = item.progress ?? 0;
  const showProgress = !item.isWatched && progress >= 1 && progress <= 94;
  const artwork = posterMode ? (item.image || item.backdrop) : (item.backdrop || item.image);

  // Rails load lazily, so a card only mounts when its row is near the viewport —
  // fetch the title-treatment logo on mount (getLogoUrl is cached + persisted).
  useEffect(() => {
    if (item.id <= 0 || item.isHomeServer) return undefined;
    let active = true;
    void getLogoUrl({ mediaType: item.mediaType, id: item.id }).then((url) => {
      if (active) setLogo(url);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item.id, item.mediaType]);

  return (
    <button
      className={`media-card ${posterMode ? "is-poster" : ""}`}
      onClick={() => onOpen(item)}
      onMouseEnter={onFocus ? () => onFocus(item) : undefined}
      onFocus={onFocus ? () => onFocus(item) : undefined}
    >
      <div className="poster">
        {artwork ? <img src={artwork} alt="" /> : <Clapperboard size={42} />}
        {logo && !posterMode && <img className="card-logo" src={logo} alt="" />}
        {item.isWatched && <span className="watched-badge"><Check size={10} /></span>}
        {item.timeRemainingLabel && <span className="cw-badge top-right">{item.timeRemainingLabel}</span>}
        {showProgress && (
          <span className="cw-progress">
            <span style={{ width: `${progress}%` }} />
          </span>
        )}
      </div>
      <strong>{item.title}</strong>
      <span>{item.releaseDate?.slice(0, 4) || item.subtitle || item.year || (item.mediaType === "tv" ? "TV Series" : "Movie")}</span>
    </button>
  );
}

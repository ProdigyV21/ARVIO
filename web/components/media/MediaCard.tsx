"use client";

import { BadgeCheck, Clapperboard } from "lucide-react";
import { memo, useEffect, useRef, useState } from "react";
import { getImdbRating } from "@/lib/imdbRatings";
import { IMDB_LOGO, serviceClearLogo } from "@/lib/serviceLogos";
import { useApp } from "@/lib/store";
import { getCardMeta, getCardProviders, getLogoUrl, prefetchDetails, resolveTmdbId } from "@/lib/tmdb";
import type { MediaItem } from "@/lib/types";

function formatReleaseDate(raw?: string | null): string {
  if (!raw) return "";
  const date = new Date(`${raw}T00:00:00`);
  if (Number.isNaN(date.getTime())) return raw.slice(0, 4);
  try {
    return new Intl.DateTimeFormat([], { day: "numeric", month: "short", year: "numeric" }).format(date);
  } catch {
    return raw;
  }
}

function formatRuntime(minutesRaw?: string | number | null): string {
  const minutes = typeof minutesRaw === "string" ? Number(minutesRaw.replace(/\D/g, "")) : minutesRaw;
  if (!minutes || !Number.isFinite(minutes)) return "";
  const h = Math.floor(minutes / 60);
  const m = minutes % 60;
  return h > 0 ? (m > 0 ? `${h}h ${m}m` : `${h}h`) : `${m}m`;
}

// "S1 · E9 · Whisper" for a Continue Watching / Up Next card. Only shown when
// the item carries episode info (CW rails populate season/episode/title).
function formatEpisodeLine(item: MediaItem): string {
  if (item.mediaType !== "tv" || !item.seasonNumber || !item.episodeNumber) return "";
  const code = `S${item.seasonNumber} · E${item.episodeNumber}`;
  const title = item.episodeTitle?.trim();
  return title ? `${code} · ${title}` : code;
}

function MediaCardBase({ item, onOpen, onFocus, posterMode }: {
  item: MediaItem;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  posterMode?: boolean;
}) {
  const { settings, isWatched, openContextMenu } = useApp();
  const effectivePosterMode = posterMode ?? settings.cardLayoutMode === "poster";
  const [logo, setLogo] = useState<string | null>(null);
  const [loadedArtwork, setLoadedArtwork] = useState("");
  const [failedArtwork, setFailedArtwork] = useState("");
  const progress = item.progress ?? 0;
  const watched = isWatched(item);
  // "Up next" rows carry SERIES completion (how far through the show you are),
  // not progress into the episode on the card — a 40% bar under "Up next S2 E5"
  // reads as "you're 40% into that episode", which is wrong. Those rows get the
  // "Up next" chip instead; the bar stays for genuinely resumable items.
  const isUpNext = item.timeRemainingLabel === "Up next";
  const showProgress = !watched && !isUpNext && progress >= 1 && progress <= 94;
  const isContinueWatchingCard = isUpNext || showProgress || Boolean(item.timeRemainingLabel);
  const longPressTimer = useRef<ReturnType<typeof setTimeout> | null>(null);
  const suppressClickUntil = useRef(0);
  // CW/up-next items from Trakt arrive with no artwork, and a hydration that hit
  // a network/429 error leaves image+backdrop empty — the card renders grey while
  // the (separately cached) logo shows. Back-fill artwork lazily from TMDB.
  const [fallbackArt, setFallbackArt] = useState<{ image: string; backdrop: string | null } | null>(null);
  const image = item.image || fallbackArt?.image || "";
  const backdrop = item.backdrop || fallbackArt?.backdrop || "";
  const episodeArtwork = isContinueWatchingCard ? item.episodeStill || "" : "";
  const artwork = effectivePosterMode ? (image || backdrop) : (episodeArtwork || backdrop || image);
  const imgLoaded = Boolean(artwork && loadedArtwork === artwork);
  const imgFailed = Boolean(artwork && failedArtwork === artwork);
  const year = item.releaseDate?.slice(0, 4) || item.year || (item.mediaType === "tv" ? "Series" : "Movie");
  const directMetadataId = item.tmdbId && item.tmdbId > 0 ? item.tmdbId : item.id > 0 ? item.id : null;
  const [metadataId, setMetadataId] = useState<number | null>(directMetadataId);

  useEffect(() => {
    let active = true;
    setMetadataId(directMetadataId);
    if (directMetadataId || !item.isHomeServer) return () => { active = false; };
    void resolveTmdbId(item).then((id) => {
      if (active) setMetadataId(id);
    });
    return () => { active = false; };
  }, [directMetadataId, item.homeServerId, item.homeServerItemId, item.imdbId, item.isHomeServer, item.mediaType]);

  useEffect(() => () => {
    if (longPressTimer.current) clearTimeout(longPressTimer.current);
    if (hoverTimer.current) clearTimeout(hoverTimer.current);
  }, []);

  const triggerContextMenu = (posX?: number, posY?: number) => {
    openContextMenu({
      item,
      isContinueWatching: isContinueWatchingCard,
      position: posX !== undefined && posY !== undefined ? { x: posX, y: posY } : null
    });
  };

  const handleContextMenu = (e: React.MouseEvent) => {
    e.preventDefault();
    e.stopPropagation();
    triggerContextMenu(e.clientX, e.clientY);
  };

  const handleTouchStart = (e: React.TouchEvent) => {
    if (longPressTimer.current) clearTimeout(longPressTimer.current);
    const touch = e.touches[0];
    const posX = touch?.clientX ?? 0;
    const posY = touch?.clientY ?? 0;
    longPressTimer.current = setTimeout(() => {
      suppressClickUntil.current = Date.now() + 750;
      triggerContextMenu(posX, posY);
    }, 500);
  };

  const handleTouchEnd = () => {
    if (longPressTimer.current) clearTimeout(longPressTimer.current);
  };

  const handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    if (Date.now() < suppressClickUntil.current) {
      e.preventDefault();
      e.stopPropagation();
      return;
    }
    onOpen(item);
  };

  const hoverTimer = useRef<ReturnType<typeof setTimeout> | null>(null);

  const handleMouseEnter = () => {
    if (hoverTimer.current) clearTimeout(hoverTimer.current);
    hoverTimer.current = setTimeout(() => {
      prefetchDetails(item);
      onFocus?.(item);
    }, 120);
  };

  const handleMouseLeave = () => {
    if (hoverTimer.current) {
      clearTimeout(hoverTimer.current);
      hoverTimer.current = null;
    }
  };

  // Rails load lazily, so a card only mounts when its row is near the viewport —
  // fetch the title-treatment logo on mount (getLogoUrl is cached + persisted).
  useEffect(() => {
    if (!metadataId) return undefined;
    let active = true;
    void getLogoUrl({ mediaType: item.mediaType, id: metadataId }).then((url) => {
      if (active) setLogo(url);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item.mediaType, metadataId]);

  // Streaming-service clearlogos, shown top-left inside the artwork (cached).
  const [serviceBadges, setServiceBadges] = useState<string[]>([]);
  useEffect(() => {
    setServiceBadges([]);
    if (!metadataId) return undefined;
    let active = true;
    void getCardProviders({ mediaType: item.mediaType, id: metadataId }).then((names) => {
      if (!active) return;
      const logos = names.map((name) => serviceClearLogo(name)).filter((url): url is string => Boolean(url));
      setServiceBadges([...new Set(logos)].slice(0, 2));
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item.mediaType, metadataId]);

  // Runtime for the meta line (list responses rarely include it) and a poster/
  // backdrop back-fill for cards that arrived without artwork — both come from
  // the same cached TMDB call, so fetch when either is missing.
  const [runtime, setRuntime] = useState<number | null>(null);
  const missingArtwork = !item.image && !item.backdrop;
  // The badge shows the REAL IMDb rating (Cinemeta, keyed by imdb id) — the same
  // source the Android app uses. TMDB's vote_average is a different score and
  // must never be shown under an IMDb badge.
  const [imdbRating, setImdbRating] = useState<string | null>(null);
  useEffect(() => {
    setRuntime(null);
    setFallbackArt(null);
    setImdbRating(null);
    if (!metadataId) return undefined;
    let active = true;
    void getCardMeta({ mediaType: item.mediaType, id: metadataId }).then((meta) => {
      if (!active) return;
      if (meta.runtime) setRuntime(meta.runtime);
      if (missingArtwork && (meta.image || meta.backdrop)) {
        setFallbackArt({ image: meta.image, backdrop: meta.backdrop });
      }
      const imdbId = item.imdbId ?? meta.imdbId;
      if (!imdbId) return;
      return getImdbRating(item.mediaType, imdbId).then((rating) => {
        if (active && rating) setImdbRating(rating);
      });
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item.imdbId, item.mediaType, metadataId, missingArtwork]);

  const dateLabel = formatReleaseDate(item.releaseDate) || item.subtitle || year;
  const runtimeLabel = formatRuntime(item.duration || runtime);
  const episodeLine = formatEpisodeLine(item);

  return (
    <button
      type="button"
      className={`media-card ${effectivePosterMode ? "is-poster" : ""}`}
      onClick={handleClick}
      onContextMenu={handleContextMenu}
      onTouchStart={handleTouchStart}
      onTouchEnd={handleTouchEnd}
      onTouchMove={handleTouchEnd}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
      onFocus={() => { prefetchDetails(item); onFocus?.(item); }}
    >
      <div className={`poster ${artwork && !imgLoaded && !imgFailed ? "is-loading" : ""}`}>
        {artwork && !imgFailed ? (
          <img
            key={artwork}
            src={artwork}
            alt=""
            loading="lazy"
            decoding="async"
            onLoad={() => setLoadedArtwork(artwork)}
            onError={() => setFailedArtwork(artwork)}
            className={`poster-art ${imgLoaded ? "is-loaded" : ""}`}
          />
        ) : (
          <Clapperboard size={42} />
        )}
        {logo && !effectivePosterMode && <img className="card-logo" src={logo} alt="" loading="lazy" decoding="async" />}
        {serviceBadges.length > 0 && (
          <span className="card-services top-left">
            {serviceBadges.map((badge) => <img key={badge} src={badge} alt="" loading="lazy" />)}
          </span>
        )}
        {watched && <span className="watched-badge" aria-label="Watched"><BadgeCheck size={13} /></span>}
        {item.timeRemainingLabel && <span className="cw-badge top-right">{item.timeRemainingLabel}</span>}
        {imdbRating ? (
          <span className="card-imdb">
            <img src={IMDB_LOGO} alt="IMDb" loading="lazy" />
            <b>{imdbRating}</b>
          </span>
        ) : null}
        {showProgress && (
          <span className="cw-progress">
            <span style={{ width: `${progress}%` }} />
          </span>
        )}
      </div>
      <strong>{item.title}</strong>
      {episodeLine ? (
        <div className="card-episode-line">
          {isUpNext && <span className="card-upnext">Up next</span>}
          <span className="card-episode">{episodeLine}</span>
        </div>
      ) : (
        <div className="card-meta-row">
          <span className="card-date">{dateLabel}</span>
          {runtimeLabel && <span className="card-runtime">{runtimeLabel}</span>}
        </div>
      )}
    </button>
  );
}

// Rails mount hundreds of cards; without memoization every parent re-render
// (hero rotation, hover, etc.) re-renders all of them, which is the main cause
// of vertical-scroll jank. Re-render only when the item or display mode change.
export const MediaCard = memo(MediaCardBase, (prev, next) =>
  prev.item === next.item &&
  prev.posterMode === next.posterMode &&
  prev.onOpen === next.onOpen &&
  prev.onFocus === next.onFocus
);

"use client";
import { useTranslation } from "@/lib/i18n";


import { BadgeCheck, Clapperboard } from "lucide-react";
import { memo, useEffect, useRef, useState } from "react";
import { config } from "@/lib/config";
import { homeFetchPriority } from "@/lib/fetchPriority";
import { getImdbRating, peekImdbRating } from "@/lib/imdbRatings";
import { formatReleaseDate, formatRuntime } from "@/lib/mediaFormat";
import { tmdbSized, tmdbSrcSet } from "@/lib/mediaImages";
import { canonicalServiceName, serviceClearLogo } from "@/lib/serviceLogos";
import { useAppPick, useAppSelector } from "@/lib/store";
import { useKidsFilter } from "@/lib/useKids";
import { getCardExtras, peekCardExtras, prefetchDetails, resolveTmdbId } from "@/lib/tmdb";
import type { CardExtras } from "@/lib/cardExtras";
import type { MediaItem } from "@/lib/types";
import { ImdbLogo, RankRibbon, useNearViewport } from "./CardBits";

// Rendered card widths (CSS px) per layout, for the browser to pick a TMDB
// rendition from the srcset: ~2.4 cards across on phones, ~293px on a laptop.
const LANDSCAPE_SIZES = "(max-width: 680px) 42vw, (max-width: 1180px) 31vw, (min-width: 1800px) 360px, 300px";
const POSTER_SIZES = "(max-width: 680px) 31vw, (max-width: 1180px) 20vw, 200px";
const LOGO_SIZES = "(max-width: 680px) 24vw, 170px";

// "S1 · E9 · Whisper" for a Continue Watching / Up Next card. Only shown when
// the item carries episode info (CW rails populate season/episode/title).
function formatEpisodeLine(item: MediaItem): string {
  if (item.mediaType !== "tv" || !item.seasonNumber || !item.episodeNumber) return "";
  const code = `S${item.seasonNumber} · E${item.episodeNumber}`;
  const title = item.episodeTitle?.trim();
  return title ? `${code} · ${title}` : code;
}

function MediaCardBase({ item, onOpen, onFocus, posterMode, rank, hideService }: {
  item: MediaItem;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  posterMode?: boolean;
  /** 1-10 on Top 10 rows: draws the rank ribbon (Android TopRankRibbon). */
  rank?: number;
  /** Canonical service name a whole collection is about; its badge would repeat on every card. */
  hideService?: string;
}) {
  const translateUi = useTranslation();
  const { isWatched, openContextMenu } = useAppPick("isWatched", "openContextMenu");
  // Only the two settings a card shows, so unrelated settings edits leave the cards alone.
  const cardLayoutMode = useAppSelector((store) => store.settings.cardLayoutMode);
  const language = useAppSelector((store) => store.settings.language);
  const effectivePosterMode = posterMode ?? cardLayoutMode === "poster";
  const cardRef = useRef<HTMLButtonElement | null>(null);
  const near = useNearViewport(cardRef);
  // Hover/focus fetches the extras right away, even before the observer fires.
  const [interacted, setInteracted] = useState(false);
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
  const directMetadataId = item.tmdbId && item.tmdbId > 0 ? item.tmdbId : item.id > 0 ? item.id : null;
  const [metadataId, setMetadataId] = useState<number | null>(directMetadataId);
  // Logo, service badges, runtime, artwork back-fill and imdb id: one cached
  // TMDB call (lib/cardExtras), seeded synchronously from the cache.
  const [extras, setExtras] = useState<CardExtras | null>(() => directMetadataId ? peekCardExtras({ mediaType: item.mediaType, id: directMetadataId }) : null);
  const missingArtwork = !item.image && !item.backdrop;
  const image = item.image || (missingArtwork ? extras?.image : "") || "";
  const backdrop = item.backdrop || (missingArtwork ? extras?.backdrop : "") || "";
  const episodeArtwork = isContinueWatchingCard ? item.episodeStill || "" : "";
  const artwork = effectivePosterMode ? (image || backdrop) : (episodeArtwork || backdrop || image);
  const imgLoaded = Boolean(artwork && loadedArtwork === artwork);
  const imgFailed = Boolean(artwork && failedArtwork === artwork);
  const year = item.releaseDate?.slice(0, 4) || item.year || (item.mediaType === "tv" ? "Series" : "Movie");

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
      setInteracted(true);
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

  useEffect(() => {
    if (!metadataId) { setExtras(null); return undefined; }
    const cached = peekCardExtras({ mediaType: item.mediaType, id: metadataId });
    if (cached) { setExtras(cached); return undefined; }
    setExtras(null);
    if (!near && !interacted) return undefined;
    // Row hydration goes first; decoration waits for a slot (lib/fetchPriority).
    const controller = new AbortController();
    void homeFetchPriority.run(() => getCardExtras({ mediaType: item.mediaType, id: metadataId }), controller.signal, interacted)
      .then((value) => { if (!controller.signal.aborted && value) setExtras(value); })
      .catch(() => undefined);
    return () => controller.abort();
  }, [item.mediaType, metadataId, near, interacted]);

  // Streaming-service clearlogos, shown top-left inside the artwork.
  const hidden = hideService ? canonicalServiceName(hideService) : "";
  const serviceBadges = [...new Set((extras?.providers ?? [])
    .filter((name) => !hidden || canonicalServiceName(name) !== hidden)
    .map((name) => serviceClearLogo(name))
    .filter((url): url is string => Boolean(url)))].slice(0, 2);
  const logo = extras?.logo ?? null;
  const runtime = extras?.runtime || null;

  // The badge shows the REAL IMDb rating (Cinemeta, keyed by imdb id) - the same
  // source the Android app uses. TMDB's vote_average is a different score and
  // must never be shown under an IMDb badge.
  const imdbId = item.imdbId ?? extras?.imdbId ?? null;
  const [imdbRating, setImdbRating] = useState<string | null>(() => peekImdbRating(imdbId) ?? null);
  useEffect(() => {
    const cached = peekImdbRating(imdbId);
    if (cached !== undefined) { setImdbRating(cached); return undefined; }
    setImdbRating(null);
    if (!imdbId || (!near && !interacted)) return undefined;
    const controller = new AbortController();
    void homeFetchPriority.run(() => getImdbRating(item.mediaType, imdbId), controller.signal, interacted)
      .then((rating) => { if (!controller.signal.aborted) setImdbRating(rating); })
      .catch(() => undefined);
    return () => controller.abort();
  }, [imdbId, item.mediaType, near, interacted]);

  const dateLabel = formatReleaseDate(item.releaseDate, language) || item.subtitle || year;
  const runtimeLabel = formatRuntime(item.duration || runtime);
  const episodeLine = formatEpisodeLine(item);

  const posterArt = effectivePosterMode && artwork === image;
  const artworkSrcSet = artwork && !(episodeArtwork && episodeArtwork === artwork)
    ? tmdbSrcSet(artwork, posterArt ? config.cardImageWidths.poster : config.cardImageWidths.backdrop)
    : undefined;
  // Smallest sensible default for browsers that ignore srcset.
  const artworkSrc = artworkSrcSet ? tmdbSized(artwork, posterArt ? 342 : 780) : artwork;

  return (
    <button
      ref={cardRef}
      type="button"
      className={`media-card ${effectivePosterMode ? "is-poster" : ""} ${rank ? "is-ranked" : ""}`}
      onClick={handleClick}
      onContextMenu={handleContextMenu}
      onTouchStart={handleTouchStart}
      onTouchEnd={handleTouchEnd}
      onTouchMove={handleTouchEnd}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
      onFocus={() => { setInteracted(true); prefetchDetails(item); onFocus?.(item); }}
    >
      <div className={`poster ${artwork && !imgLoaded && !imgFailed ? "is-loading" : ""}`}>
        {artwork && !imgFailed ? (
          <img
            key={artwork}
            src={artworkSrc}
            srcSet={artworkSrcSet}
            sizes={artworkSrcSet ? (effectivePosterMode ? POSTER_SIZES : LANDSCAPE_SIZES) : undefined}
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
        {logo && !effectivePosterMode && <img className="card-logo" src={tmdbSized(logo, 300)} srcSet={tmdbSrcSet(logo, config.cardImageWidths.logo)}
          sizes={LOGO_SIZES} alt="" loading="lazy" decoding="async" />}
        {rank ? <RankRibbon rank={rank} poster={effectivePosterMode} /> : null}
        {serviceBadges.length > 0 && (
          <span className="card-services top-left">
            {serviceBadges.map((badge) => <img key={badge} src={badge} alt="" loading="lazy" />)}
          </span>
        )}
        {watched && <span className="watched-badge" aria-label={translateUi("Watched")}><BadgeCheck size={13} /></span>}
        {item.timeRemainingLabel && <span className="cw-badge top-right">{item.timeRemainingLabel}</span>}
        {imdbRating ? (
          <span className="card-imdb">
            <ImdbLogo height={12} />
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
          {isUpNext && <span className="card-upnext">{translateUi("Up next")}</span>}
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
const MemoMediaCard = memo(MediaCardBase, (prev, next) =>
  prev.item === next.item &&
  prev.posterMode === next.posterMode &&
  prev.rank === next.rank &&
  prev.hideService === next.hideService &&
  prev.onOpen === next.onOpen &&
  prev.onFocus === next.onFocus
);

/**
 * Every card passes the kids filter: on a kids profile a title is shown only once its
 * rating is known and fits the profile's age (grids, collections, Library, related titles).
 */
export function MediaCard(props: Parameters<typeof MediaCardBase>[0]) {
  const kids = useKidsFilter();
  if (kids.active && !kids.filter([props.item]).length) return null;
  return <MemoMediaCard {...props} />;
}

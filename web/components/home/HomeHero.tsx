"use client";

import { Info, Pause, Play } from "lucide-react";
import { useEffect, useMemo, useState, useSyncExternalStore } from "react";
import { useTranslation } from "@/lib/i18n";
import { HERO_ROTATE_MS, heroOverview, type HeroController } from "@/lib/homeHero";
import { getImdbRating, peekImdbRating } from "@/lib/imdbRatings";
import { formatRuntime } from "@/lib/mediaFormat";
import { tmdbSrcSet } from "@/lib/mediaImages";
import { genreNamesFromIds, getCardExtras, peekCardExtras } from "@/lib/tmdb";
import { useKidsFilter } from "@/lib/useKids";
import type { CardExtras } from "@/lib/cardExtras";
import type { MediaItem } from "@/lib/types";
import { AddSourceChip } from "@/components/shell/NoAddonsPrompt";
import { ImdbLogo } from "@/components/media/CardBits";

function usePrefersReducedMotion() {
  const [reduced, setReduced] = useState(false);
  useEffect(() => {
    if (typeof window.matchMedia !== "function") return undefined;
    const query = window.matchMedia("(prefers-reduced-motion: reduce)");
    const update = () => setReduced(query.matches);
    update();
    query.addEventListener?.("change", update);
    return () => query.removeEventListener?.("change", update);
  }, []);
  return reduced;
}

const extrasFor = (item: MediaItem | null) => item && item.id > 0 && !item.isHomeServer ? peekCardExtras(item) : null;

/**
 * The Home billboard. It owns the rotating preview (lib/homeHero) so a
 * rotation or a card hover re-renders only this component. Its box is
 * reserved from the first paint (no layout shift when the first title lands).
 */
export function HomeHero({ controller, fallback, loading = true, onOpen, onPlay }: {
  controller: HeroController;
  fallback: MediaItem | null;
  loading?: boolean;
  onOpen: (item: MediaItem) => void;
  onPlay: (item: MediaItem) => void;
}) {
  const translateUi = useTranslation();
  const kids = useKidsFilter();
  const state = useSyncExternalStore(controller.subscribe, controller.getState, controller.getState);
  const reducedMotion = usePrefersReducedMotion();

  // WCAG 2.2.2: no auto-advance for reduced-motion users (they can still start it).
  useEffect(() => { if (reducedMotion) controller.setPaused(true); }, [reducedMotion, controller]);
  const rotating = state.pool.length >= 2 && !state.pinned && !state.paused && !state.holding;
  useEffect(() => {
    if (!rotating) return undefined;
    const timer = window.setInterval(() => controller.advance(), HERO_ROTATE_MS);
    return () => window.clearInterval(timer);
  }, [rotating, controller, state.preview]);

  const target = state.preview ?? fallback;
  // Swap logo, text and artwork together once the title's extras (logo,
  // runtime, imdb id; one cached call) are known, so nothing pops in late.
  const [shown, setShown] = useState<{ item: MediaItem; extras: CardExtras | null } | null>(() =>
    target ? { item: target, extras: extrasFor(target) } : null);
  useEffect(() => {
    if (!target) { setShown(null); return undefined; }
    const cached = extrasFor(target);
    if (cached || target.id <= 0 || target.isHomeServer) { setShown({ item: target, extras: cached }); return undefined; }
    let active = true;
    // First title: show it at once; later swaps wait for the extras (≤ ~1 s).
    setShown((current) => current ?? { item: target, extras: null });
    const timer = window.setTimeout(() => { if (active) setShown({ item: target, extras: null }); }, 1200);
    void getCardExtras(target).then((extras) => {
      if (!active) return;
      window.clearTimeout(timer);
      setShown({ item: target, extras });
    });
    return () => { active = false; window.clearTimeout(timer); };
  }, [target]);

  const item = shown && kids.allows(shown.item) ? shown.item : null;
  const extras = shown?.extras ?? null;
  const imdbId = item?.imdbId ?? extras?.imdbId ?? null;
  const [rating, setRating] = useState<{ id: string; value: string | null } | null>(null);
  useEffect(() => {
    if (!imdbId || !item) return undefined;
    const cached = peekImdbRating(imdbId);
    if (cached !== undefined) { setRating({ id: imdbId, value: cached }); return undefined; }
    let active = true;
    void getImdbRating(item.mediaType, imdbId).then((value) => { if (active) setRating({ id: imdbId, value }); }).catch(() => undefined);
    return () => { active = false; };
  }, [imdbId, item]);
  const imdbRating = rating && rating.id === imdbId ? rating.value : (imdbId ? peekImdbRating(imdbId) ?? null : null);

  const [failedLogo, setFailedLogo] = useState<string | null>(null);
  const logo = extras?.logo && extras.logo !== failedLogo ? extras.logo : null;
  const genres = useMemo(() => (item?.genres?.length ? item.genres : genreNamesFromIds(item?.genreIds)).slice(0, 2), [item]);
  const runtime = formatRuntime(item?.duration || extras?.runtime);
  const metaBits = item ? [
    item.mediaType === "tv" ? translateUi("Series") : translateUi("Movie"),
    item.releaseDate?.slice(0, 4) || item.year || null,
    runtime || null,
    ...genres
  ].filter((bit): bit is string => Boolean(bit)) : [];
  const backdrop = item?.backdrop || extras?.backdrop || "";
  const showToggle = state.pool.length >= 2;
  const paused = state.paused || state.pinned;
  const toggleLabel = paused ? translateUi("Resume slideshow") : translateUi("Pause slideshow");
  if (!item && !loading) return null;

  return (
    <section className={`hero home-hero ${item ? "" : "is-empty"}`} aria-roledescription={translateUi("Featured")}
      aria-label={item?.title ?? translateUi("Featured")}>
      {backdrop && <img key={backdrop} className="hero-backdrop" src={backdrop} alt=""
        srcSet={tmdbSrcSet(backdrop, [780, 1280])} sizes="100vw" decoding="async"
        // eslint-disable-next-line react/no-unknown-property
        fetchPriority="high" />}
      <div className="hero-copy"
        onMouseEnter={() => controller.setHolding(true)} onMouseLeave={() => controller.setHolding(false)}
        onFocus={() => controller.setHolding(true)}
        onBlur={(event) => { if (!event.currentTarget.contains(event.relatedTarget as Node | null)) controller.setHolding(false); }}>
        {item ? <>
          <div className="hero-title">
            {logo ? <img className="hero-logo" src={logo} alt={item.title} onError={() => setFailedLogo(logo)} /> : <h2>{item.title}</h2>}
          </div>
          <div className="hero-meta">
            {imdbRating && <span className="hero-imdb"><ImdbLogo height={20} /><b>{imdbRating}</b></span>}
            {metaBits.map((bit) => <span key={bit}>{bit}</span>)}
          </div>
          <p>{heroOverview(item, translateUi("Continue from your ARVIO library."))}</p>
          <div className="hero-actions">
            <button type="button" className="primary" data-autofocus onClick={() => onPlay(item)}><Play size={20} fill="currentColor" aria-hidden /> {translateUi("Play")}</button>
            <button type="button" className="secondary" onClick={() => onOpen(item)}><Info size={20} aria-hidden /> {translateUi("More Info")}</button>
            {showToggle && <button type="button" className="hero-rotation-toggle" onClick={() => controller.togglePaused()}
              aria-pressed={paused} aria-label={toggleLabel}
              title={toggleLabel}>
              {paused ? <Play size={16} fill="currentColor" aria-hidden /> : <Pause size={16} fill="currentColor" aria-hidden />}
            </button>}
            <AddSourceChip />
          </div>
        </> : <>
          {/* Same boxes as a loaded billboard, so the title landing moves nothing. */}
          <div className="hero-title" aria-hidden><span className="hero-skeleton hero-skeleton-title" /></div>
          <div className="hero-meta" aria-hidden><span className="hero-skeleton hero-skeleton-meta" /></div>
          <p aria-hidden><span className="hero-skeleton" /><span className="hero-skeleton" /><span className="hero-skeleton is-short" /></p>
          <div className="hero-actions" aria-hidden><span className="hero-skeleton hero-skeleton-button" /><span className="hero-skeleton hero-skeleton-button" /></div>
        </>}
      </div>
    </section>
  );
}

"use client";

import { Info, Play, Star } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { getLogoUrl } from "@/lib/tmdb";
import { useApp } from "@/lib/store";
import { LazyRail } from "@/components/media/LazyRail";
import { MediaRail } from "@/components/media/MediaRail";
import type { Category, MediaItem } from "@/lib/types";

export function HomeScreen() {
  const { hero, categories, catalogConfigs, homeServerRows, continueWatching, openDetails, setHeroPreview, settings } = useApp();
  const posterMode = settings.cardLayoutMode === "poster";
  const [heroLogo, setHeroLogo] = useState<string | null>(null);
  const hoverTimer = useRef<ReturnType<typeof setTimeout> | null>(null);
  const seededHero = useRef(false);

  const seedHeroFromRow = (row: Category) => {
    if (seededHero.current || continueWatching.length) return;
    const first = row.items[0];
    if (first) {
      seededHero.current = true;
      setHeroPreview(first);
    }
  };

  // Title-treatment logo for the hero.
  useEffect(() => {
    setHeroLogo(null);
    if (!hero || hero.id <= 0) return;
    let active = true;
    void getLogoUrl({ mediaType: hero.mediaType, id: hero.id }).then((url) => {
      if (active) setHeroLogo(url);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [hero?.id, hero?.mediaType]);

  const onCardFocus = (item: MediaItem) => {
    if (hoverTimer.current) clearTimeout(hoverTimer.current);
    hoverTimer.current = setTimeout(() => setHeroPreview(item), 220);
  };

  const metaBits = [
    hero?.mediaType === "tv" ? "Series" : "Movie",
    hero?.releaseDate?.slice(0, 4) || hero?.year || null,
    hero?.duration || null
  ].filter(Boolean);

  return (
    <div className="screen">
      {hero && (
        <section className="hero" style={{ backgroundImage: hero.backdrop ? `url(${hero.backdrop})` : undefined }}>
          <div className="hero-copy">
            {heroLogo ? (
              <img className="hero-logo" src={heroLogo} alt={hero.title} />
            ) : (
              <h2>{hero.title}</h2>
            )}
            <div className="hero-meta">
              {metaBits.map((bit) => <span key={String(bit)}>{bit}</span>)}
              {hero.rating && <span className="hero-rating"><Star size={15} fill="currentColor" /> {hero.rating}</span>}
            </div>
            <p>{hero.overview || hero.subtitle || "Continue from your ARVIO library."}</p>
            <div className="hero-actions">
              <button className="primary" onClick={() => openDetails(hero)}><Play size={20} fill="currentColor" /> Play</button>
              <button className="secondary" onClick={() => openDetails(hero)}><Info size={20} /> More Info</button>
            </div>
          </div>
        </section>
      )}
      {categories.map((category) => (
        <MediaRail key={category.id} category={category} onOpen={openDetails} onFocus={onCardFocus} posterMode={posterMode} />
      ))}
      {homeServerRows.map((category) => (
        <MediaRail key={category.id} category={category} onOpen={openDetails} onFocus={onCardFocus} posterMode={posterMode} />
      ))}
      {catalogConfigs.map((catalog, index) => (
        <LazyRail
          key={catalog.id}
          catalog={catalog}
          eager={index < 8}
          posterMode={posterMode}
          onOpen={openDetails}
          onFocus={onCardFocus}
          onLoaded={seedHeroFromRow}
        />
      ))}
    </div>
  );
}

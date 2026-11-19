"use client";
import "@/app/collections.css";

import { useEffect, useRef, useState } from 'react';
import { useTranslation } from '@/lib/i18n';
import { getCollectionPreview, getCardProviders, getLogoUrl } from '@/lib/tmdb';
import { getImdbRating } from '@/lib/imdbRatings';
import { canonicalServiceName, serviceClearLogo } from '@/lib/serviceLogos';
import { formatRuntime } from '@/lib/mediaFormat';
import { ImdbLogo } from './CardBits';
import { mediaPreviewKey, mergeMediaPreview } from '@/lib/mediaPreview';
import type { MediaItem } from '@/lib/types';

type Preview = { key: string; item: MediaItem; logo: string | null; rating: string | null; services: string[] };

export function useCollectionPreview(preview: MediaItem | undefined, language: string) {
  const [hero, setHero] = useState<Preview | null>(null);
  const cache = useRef(new Map<string, Preview>());
  const previewKey = mediaPreviewKey(preview);
  useEffect(() => {
    if (!preview) return;
    const cacheKey = `${language}:${previewKey}`;
    const cached = cache.current.get(cacheKey);
    if (cached) { setHero(cached); return; }
    let cancelled = false;
    const timer = setTimeout(async () => {
      const id = preview.tmdbId && preview.tmdbId > 0 ? preview.tmdbId : preview.id;
      const canEnrich = id > 0 || Boolean(preview.addonNativeId);
      const reference = { ...preview, id };
      const [details, logo, services] = await Promise.all([
        canEnrich ? getCollectionPreview(reference, language).catch(() => null) : null,
        canEnrich ? getLogoUrl(reference).catch(() => null) : null,
        id > 0 ? getCardProviders(reference).catch(() => []) : []
      ]);
      const item = mergeMediaPreview(preview, details);
      // "Netflix" and "Netflix Standard with Ads" share one clearlogo: show it once.
      const serviceLogos = [...new Set(services.map(serviceClearLogo).filter((url): url is string => Boolean(url)))];
      if (!cancelled) setHero({ key: cacheKey, item, logo, rating: null, services: serviceLogos });
      const rating = await getImdbRating(item.mediaType, item.imdbId).catch(() => null);
      if (!cancelled) {
        const result = { key: cacheKey, item, logo, rating, services: serviceLogos };
        if (cache.current.size >= 80) cache.current.delete(cache.current.keys().next().value!);
        cache.current.set(cacheKey, result);
        setHero(result);
      }
    }, 160);
    return () => { cancelled = true; clearTimeout(timer); };
  }, [previewKey, language]);
  const metadata = hero?.key === `${language}:${previewKey}` ? hero : null;
  return { item: preview ? mergeMediaPreview(preview, metadata?.item) : undefined, metadata };
}

export function CollectionPreview({ preview, language, fallbackTitle = '', fallbackOverview, fallbackArtwork, hideService }: {
  preview?: MediaItem; language: string; fallbackTitle?: string; fallbackOverview?: string | null; fallbackArtwork?: string | null;
  /** The collection's own service (its badge is implied by the page). */
  hideService?: string;
}) {
  const t = useTranslation();
  const { item, metadata } = useCollectionPreview(preview, language);
  const [failedLogo, setFailedLogo] = useState<string | null>(null);
  const artwork = item?.backdrop || fallbackArtwork;
  const budget = item?.mediaType === 'movie' && item.budget && item.budget > 0 ? new Intl.NumberFormat(language, {
    style: 'currency', currency: 'USD', notation: 'compact', maximumFractionDigits: 1
  }).format(item.budget) : null;
  return <>
    {artwork && <div className="collection-page-backdrop" style={{ backgroundImage: `url(${JSON.stringify(artwork)})` }} />}
    <section className="collection-spotlight" aria-label={item?.title || fallbackTitle} data-preview-key={mediaPreviewKey(preview)}>
      <div className="collection-spotlight-title">{metadata?.logo && failedLogo !== metadata.logo
        ? <img src={metadata.logo} alt={item?.title} onError={() => setFailedLogo(metadata.logo)} />
        : <h2>{item?.title || fallbackTitle}</h2>}</div>
      <div className="collection-spotlight-facts">
        {metadata?.services.filter(url => !hideService || url !== serviceClearLogo(canonicalServiceName(hideService))).slice(0, 2)
          .map(url => <img key={url} src={url} alt="" className="collection-service" />)}
        {/* The real IMDb rating only, like cards and the billboard (never TMDB's score under it). */}
        {metadata?.rating ? <span className="collection-imdb"><ImdbLogo height={16} />{metadata.rating}</span> : item?.isHomeServer && item.rating ? <span>{item.rating}</span> : null}
        {item?.year && <span>{item.year}</span>}{formatRuntime(item?.duration) && <span>{formatRuntime(item?.duration)}</span>}{budget && <span>{t('Budget')} {budget}</span>}
      </div>
      <p>{item?.overview || fallbackOverview}</p>
    </section>
  </>;
}

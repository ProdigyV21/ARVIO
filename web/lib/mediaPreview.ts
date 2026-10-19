import type { MediaItem } from './types';

export function mediaPreviewKey(item?: MediaItem | null): string {
  if (!item) return '';
  if (item.isHomeServer) return `server:${item.homeServerId}:${item.homeServerItemId ?? item.id}:${item.mediaType}`;
  if (item.addonNativeId) return `addon:${item.addonNativeAddonId}:${item.addonNativeId}:${item.mediaType}`;
  return `${item.mediaType}:${item.id}`;
}

export function mergeMediaPreview(item: MediaItem, details?: MediaItem | null): MediaItem {
  if (!details || details.mediaType !== item.mediaType) return item;
  return { ...item,
    overview: details.overview || item.overview,
    backdrop: item.backdrop || details.backdrop,
    image: item.image || details.image,
    year: details.year || item.year,
    duration: details.duration || item.duration,
    rating: details.rating || item.rating,
    imdbId: details.imdbId || item.imdbId,
    budget: details.budget ?? item.budget,
    genres: details.genres?.length ? details.genres : item.genres
  };
}

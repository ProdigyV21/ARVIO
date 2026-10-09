import type { CatalogConfig, CollectionSourceConfig, MediaType } from './types';

export function collectionGroupKey(catalog: CatalogConfig): string | null {
  return catalog.collectionRailKey || catalog.collectionGroup || null;
}

export function collectionFolders(rail: CatalogConfig, catalogs: CatalogConfig[]) {
  const key = collectionGroupKey(rail);
  return catalogs.filter(c => c.enabled !== false && String(c.kind).toUpperCase() === 'COLLECTION' && key && collectionGroupKey(c) === key);
}

export function collectionHomeCatalogs(catalogs: CatalogConfig[]) {
  // Collection folders only belong inside their rail. Hiding/removing that rail
  // must not promote its folders into standalone movie rows on Home.
  return catalogs.filter(c => c.enabled !== false && String(c.kind).toUpperCase() !== 'COLLECTION');
}

export function collectionSourceSupports(source: CollectionSourceConfig, type: MediaType) {
  const raw = String(source.mediaType || source.addonCatalogType || '').toLowerCase();
  if (raw === 'movie') return type === 'movie';
  if (raw === 'tv' || raw === 'series') return type === 'tv';
  if (source.kind.toUpperCase() === 'TMDB_COLLECTION') return type === 'movie';
  if (source.kind.toUpperCase() === 'CURATED_IDS') return (source.curatedRefs || []).some(ref => ref.startsWith(type === 'tv' ? 'tv:' : 'movie:') || (type === 'tv' && ref.startsWith('series:')));
  return true;
}

export function collectionMediaTypes(catalog: CatalogConfig): MediaType[] {
  const sources = catalog.collectionSources || [];
  return (['movie', 'tv'] as MediaType[]).filter(type => sources.length ? sources.some(s => collectionSourceSupports(s, type)) : !catalog.mediaType || catalog.mediaType === 'all' || catalog.mediaType === type);
}

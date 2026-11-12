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

export function collectionParentRail(catalog: CatalogConfig, catalogs: CatalogConfig[]): CatalogConfig | undefined {
  if (String(catalog.kind).toUpperCase() !== 'COLLECTION') return undefined;
  const key = collectionGroupKey(catalog);
  if (!key) return undefined;
  return catalogs.find(c => String(c.kind).toUpperCase() === 'COLLECTION_RAIL' && collectionGroupKey(c) === key);
}

/**
 * Enabled Home rows that actually render on Home: excludes standalone folders
 * and empty collection rails whose folders are all disabled.
 */
export function visibleHomeCatalogs(catalogs: CatalogConfig[]) {
  return collectionHomeCatalogs(catalogs).filter(catalog =>
    String(catalog.kind).toUpperCase() !== 'COLLECTION_RAIL' || collectionFolders(catalog, catalogs).length > 0
  );
}

/** Enabled collection folders whose parent rail exists but is currently hidden. */
export function orphanedEnabledFolders(catalogs: CatalogConfig[]) {
  return catalogs.filter(catalog => {
    if (catalog.enabled === false || String(catalog.kind).toUpperCase() !== 'COLLECTION') return false;
    const parent = collectionParentRail(catalog, catalogs);
    return Boolean(parent && parent.enabled === false);
  });
}

/**
 * Enable or hide the targeted catalog rows; when showing a collection folder,
 * also ensure its parent collection rail is enabled so the folder is reachable on Home.
 */
export function setCatalogsEnabledWithParentRails(catalogs: CatalogConfig[], ids: Set<string>, enabled: boolean): CatalogConfig[] {
  const parentKeysToEnable = new Set<string>();
  if (enabled) {
    for (const catalog of catalogs) {
      if (!ids.has(catalog.id) || String(catalog.kind).toUpperCase() !== 'COLLECTION') continue;
      const key = collectionGroupKey(catalog);
      if (key) parentKeysToEnable.add(key);
    }
  }
  return catalogs.map(catalog => {
    if (ids.has(catalog.id)) return { ...catalog, enabled };
    if (enabled && String(catalog.kind).toUpperCase() === 'COLLECTION_RAIL') {
      const key = collectionGroupKey(catalog);
      if (key && parentKeysToEnable.has(key)) return { ...catalog, enabled: true };
    }
    return catalog;
  });
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

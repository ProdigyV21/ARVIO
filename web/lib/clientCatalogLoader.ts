import { matchesClientFilter, parseClientDelegate, parseClientFilter, type ClientCatalogFilter, type ClientCatalogRequest } from './clientCatalog';
import type { CatalogConfig, HomeServerConfig, IptvPlaylistEntry, MediaItem } from './types';

type Page = { items: MediaItem[]; hasMore: boolean };
export type ClientCatalogServices = {
  tracker(request: ClientCatalogRequest): Promise<{ items: MediaItem[]; isCurrent: () => boolean }>;
  homePage(source: string, servers: HomeServerConfig[], offset: number, limit: number): Promise<Page>;
  vodSeeds?: (request: ClientCatalogRequest) => Promise<MediaItem[]>;
  hasVodMatch?: (item: MediaItem) => Promise<boolean>;
  hasHomeServerMatch?: (item: MediaItem, servers: HomeServerConfig[]) => Promise<boolean>;
};

function readLocalIptvPlaylists(): IptvPlaylistEntry[] {
  try {
    const raw = typeof window !== 'undefined' ? window.localStorage?.getItem('arvio.web.settings') : null;
    if (!raw) return [];
    const parsed = JSON.parse(raw) as { iptvPlaylists?: IptvPlaylistEntry[] };
    return Array.isArray(parsed.iptvPlaylists) ? parsed.iptvPlaylists : [];
  } catch {
    return [];
  }
}

const services: ClientCatalogServices = {
  async tracker(request) {
    const { traktItemToMedia } = await import('./mappers');
    let rows: unknown[];
    let isCurrent: () => boolean;
    if (request.provider === 'trakt') {
      const { traktClient } = await import('./store/clients');
      const profile = traktClient.currentProfileId;
      isCurrent = () => traktClient.currentProfileId === profile;
      if (request.action === 'watchlist') rows = await traktClient.watchlist();
      else {
        rows = [];
        if (request.mediaType !== 'tv') rows.push(...await traktClient.watched('movies'));
        if (request.mediaType !== 'movie') rows.push(...await traktClient.watched('shows'));
      }
    } else {
      const { simklClient } = await import('./simkl');
      const profile = simklClient.currentProfileId;
      isCurrent = () => simklClient.currentProfileId === profile;
      rows = await simklClient.library(request.action as Parameters<typeof simklClient.library>[0]);
    }
    if (!isCurrent()) throw new Error('Client catalog profile changed');
    return { items: rows.map(traktItemToMedia), isCurrent };
  },
  async homePage(source, servers, offset, limit) {
    const { loadHomeServerLibraryPage } = await import('./homeserver');
    let library = source;
    if (source.startsWith('home_server_catalog|')) {
      const decode = (part: string) => decodeURIComponent(part.replace(/\+/g, ' '));
      const [serverKey, libraryId] = source.slice('home_server_catalog|'.length).split('|').map(decode);
      if (!serverKey || !libraryId) throw new Error('Invalid home-server library source');
      const server = servers.find(s => (s.serverId?.trim() || s.id) === serverKey || s.id === serverKey);
      if (!server) return { items: [], hasMore: false };
      library = `hslib:${encodeURIComponent(server.id)}:${encodeURIComponent(libraryId)}`;
    }
    if (!/^hslib:[^:]+:.+$/.test(library)) throw new Error('Invalid home-server library source');
    return loadHomeServerLibraryPage(servers, library, { offset, limit, throwOnError: true });
  },
  async vodSeeds(request) {
    const { loadXtreamVodCatalogSeeds } = await import('./iptv');
    const seeds = await loadXtreamVodCatalogSeeds(readLocalIptvPlaylists(), request.mediaType, request.category);
    return seeds.slice(0, 500).map((seed, index) => ({
      id: seed.id,
      title: seed.title,
      mediaType: seed.mediaType,
      year: seed.year || '',
      imdbId: seed.imdbId,
      overview: '',
      rating: '',
      image: '',
      backdrop: '',
      sourceOrder: index
    } as unknown as MediaItem));
  },
  async hasVodMatch(item) {
    const { hasXtreamVodMatch } = await import('./iptv');
    return hasXtreamVodMatch(readLocalIptvPlaylists(), {
      id: item.id,
      mediaType: item.mediaType,
      title: item.title
    });
  },
  async hasHomeServerMatch(item, servers) {
    const { hasHomeServerTitleMatch } = await import('./homeserver');
    if (!servers.length) return false;
    const target = { title: item.title, year: Number(item.year) || undefined, tmdbId: item.id > 0 ? item.id : undefined };
    return hasHomeServerTitleMatch(servers, target, item.mediaType);
  }
};

async function matchesAvailability(
  item: MediaItem,
  filter: ClientCatalogFilter,
  homeServers: HomeServerConfig[],
  clients: ClientCatalogServices
): Promise<boolean> {
  if (!filter.availableOn) return true;
  if (filter.availableOn === 'vod') {
    const check = clients.hasVodMatch ?? services.hasVodMatch!;
    return check(item);
  }
  const check = clients.hasHomeServerMatch ?? services.hasHomeServerMatch!;
  return check(item, homeServers);
}

export async function filterClientCatalogItems(
  items: MediaItem[],
  filterJson: string | null | undefined,
  homeServers: HomeServerConfig[],
  limit?: number,
  clients: ClientCatalogServices = services
): Promise<MediaItem[]> {
  const filter = parseClientFilter(filterJson);
  const wanted = limit ? limit + 1 : Infinity;
  const output: MediaItem[] = [];
  for (const item of items) {
    if (output.length >= wanted) break;
    if (!matchesClientFilter(item, filter)) continue;
    if (await matchesAvailability(item, filter, homeServers, clients)) output.push(item);
  }
  return output;
}

/** Filters run before the caller's limit; native server identities are not replaced by TMDB ids. */
export async function loadClientDelegatedCatalog(
  delegate: string,
  catalog: CatalogConfig,
  homeServers: HomeServerConfig[],
  limit: number | undefined,
  hydrate: (item: MediaItem) => Promise<MediaItem | null>,
  clients: ClientCatalogServices = services
): Promise<MediaItem[]> {
  const request = parseClientDelegate(delegate, catalog.addonCatalogType || catalog.mediaType);
  const filter = parseClientFilter(catalog.clientFilter);
  const output: MediaItem[] = [];
  const seen = new Set<string>();
  // One extra match lets loadCatalog expose hasMore without dropping a matching title.
  const wanted = limit ? limit + 1 : Infinity;
  const accept = async (item: MediaItem) => {
    const key = item.isHomeServer ? `${item.homeServerId}:${item.homeServerItemId}` : `${item.mediaType}:${item.id}`;
    if (output.length < wanted &&
        (!request.mediaType || item.mediaType === request.mediaType) &&
        matchesClientFilter(item, filter) &&
        !seen.has(key) &&
        await matchesAvailability(item, filter, homeServers, clients)) {
      seen.add(key);
      output.push(item);
    }
  };
  if (request.provider === 'homeserver') {
    const source = request.source || [catalog.sourceRef, catalog.sourceUrl].find(value => value?.startsWith('hslib:') || value?.startsWith('home_server_catalog|'));
    if (!source) throw new Error('Home-server delegation requires an explicit library source');
    let offset = 0;
    while (output.length < wanted) {
      const page = await clients.homePage(source, homeServers, offset, Math.min(100, wanted === Infinity ? 100 : wanted - output.length));
      for (const item of page.items) await accept(item);
      offset += page.items.length;
      if (!page.hasMore || !page.items.length) break;
    }
    return output;
  }
  if (request.provider === 'vod') {
    const loadVod = clients.vodSeeds ?? services.vodSeeds!;
    const seeds = (await loadVod(request)).filter(item => !request.mediaType || item.mediaType === request.mediaType);
    for (let start = 0; start < seeds.length && output.length < wanted;) {
      const batch = seeds.slice(start, start + Math.min(6, wanted === Infinity ? 6 : wanted - output.length));
      start += batch.length;
      const items = await Promise.all(batch.map(hydrate));
      for (const item of items) {
        if (item) await accept(item);
      }
    }
    return output;
  }
  const snapshot = await clients.tracker(request);
  const seeds = snapshot.items.filter(item => !request.mediaType || item.mediaType === request.mediaType);
  for (let start = 0; start < seeds.length && output.length < wanted;) {
    if (!snapshot.isCurrent()) throw new Error('Client catalog profile changed');
    const batch = seeds.slice(start, start + Math.min(6, wanted === Infinity ? 6 : wanted - output.length));
    start += batch.length;
    const items = await Promise.all(batch.map(hydrate));
    if (!snapshot.isCurrent()) throw new Error('Client catalog profile changed');
    for (const item of items) {
      if (item) await accept(item);
    }
  }
  return output;
}

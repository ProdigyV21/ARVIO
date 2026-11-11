import type { MediaItem, MediaType } from './types';

export type ClientCatalogRequest = { provider: 'trakt' | 'simkl' | 'homeserver' | 'vod'; action: string; mediaType: MediaType | null; source?: string; category?: string };
export type ClientCatalogFilter = { mediaType?: MediaType | null; genreIds?: number[]; minYear?: number; maxYear?: number; minRating?: number; availableOn?: 'vod' | 'homeserver' };

export function clientCatalogMediaType(value?: string | null): MediaType | null {
  switch (value?.trim().toLowerCase()) {
    case undefined: case '': case 'all': return null;
    case 'movie': case 'movies': return 'movie';
    case 'tv': case 'series': case 'show': case 'shows': return 'tv';
    default: throw new Error('Unsupported client catalog media type');
  }
}

export function parseClientDelegate(value: string, fallbackType?: string): ClientCatalogRequest {
  const uri = new URL(value.trim());
  if (value.includes('#')) throw new Error('Client delegation does not support fragments');
  let provider = uri.protocol.slice(0, -1).toLowerCase();
  if (!['trakt', 'simkl', 'homeserver', 'hybrid', 'vod'].includes(provider)) throw new Error('Unsupported client delegate');
  const rest = value.trim().slice(value.trim().indexOf(':') + 1).replace(/^\/\//, '');
  const action = rest.split('?')[0].replace(/^\/+|\/+$/g, '').toLowerCase();
  const parameters = new URLSearchParams(rest.includes('?') ? rest.slice(rest.indexOf('?') + 1) : '');
  const seen = new Set<string>();
  for (const [key] of parameters) {
    if (!['type', 'source', 'tracker', 'category'].includes(key) || seen.has(key)) throw new Error('Unsupported or repeated delegate parameter');
    seen.add(key);
  }
  if (provider === 'hybrid') provider = parameters.get('tracker')?.toLowerCase() || 'trakt';
  if (!['trakt', 'simkl', 'homeserver', 'vod'].includes(provider)) throw new Error('Unsupported hybrid tracker');
  const normalizedAction = provider === 'simkl' && ['plan', 'watchlist'].includes(action) ? 'plantowatch' : action;
  const actions = provider === 'trakt'
    ? ['watchlist', 'watched']
    : provider === 'simkl'
      ? ['plantowatch', 'completed', 'watching', 'hold', 'dropped']
      : provider === 'vod'
        ? ['recent', 'categories']
        : ['library'];
  if (!actions.includes(normalizedAction)) throw new Error('Unsupported client delegate action');
  return { provider: provider as ClientCatalogRequest['provider'], action: normalizedAction,
    mediaType: clientCatalogMediaType(parameters.get('type') ?? fallbackType), source: parameters.get('source') ?? undefined,
    category: parameters.get('category') ?? undefined };
}

export function parseClientFilter(value?: string | null): ClientCatalogFilter {
  if (!value?.trim()) return {};
  const filter = JSON.parse(value);
  if (!filter || typeof filter !== 'object' || Array.isArray(filter) || Object.keys(filter).some(key => !['mediaType', 'genreIds', 'minYear', 'maxYear', 'minRating', 'availableOn'].includes(key))) throw new Error('Invalid clientFilter object');
  const output: ClientCatalogFilter = {};
  for (const key of ['minYear', 'maxYear', 'minRating'] as const) {
    if (!(key in filter)) continue;
    const number = filter[key];
    if (typeof number !== 'number' || !Number.isFinite(number)) throw new Error(`${key} must be numeric`);
    if (key === 'minRating' ? number < 0 || number > 10 : !Number.isInteger(number) || number < 1 || number > 9999) throw new Error(`Invalid ${key}`);
    output[key] = number;
  }
  if (output.minYear !== undefined && output.maxYear !== undefined && output.minYear > output.maxYear) throw new Error('Invalid year range');
  if ('mediaType' in filter) {
    if (typeof filter.mediaType !== 'string') throw new Error('Invalid mediaType');
    output.mediaType = clientCatalogMediaType(filter.mediaType);
  }
  if ('genreIds' in filter) {
    if (!Array.isArray(filter.genreIds) || filter.genreIds.some((id: unknown) => typeof id !== 'number' || !Number.isInteger(id) || id <= 0 || id > 2147483647)) throw new Error('Invalid genreIds');
    output.genreIds = [...new Set<number>(filter.genreIds)];
  }
  if ('availableOn' in filter) {
    if (typeof filter.availableOn !== 'string') throw new Error('Invalid availableOn');
    const target = filter.availableOn.trim().toLowerCase();
    if (!['vod', 'homeserver'].includes(target)) throw new Error('Unsupported availableOn target');
    output.availableOn = target as 'vod' | 'homeserver';
  }
  return output;
}

export function matchesClientFilter(item: MediaItem, filter: ClientCatalogFilter): boolean {
  if (filter.mediaType && item.mediaType !== filter.mediaType) return false;
  if (filter.genreIds?.length && !item.genreIds?.some(id => filter.genreIds!.includes(id))) return false;
  const year = /^\d{4}/.test(item.year || '') ? Number(item.year!.slice(0, 4)) : NaN;
  if (filter.minYear !== undefined && !(year >= filter.minYear)) return false;
  if (filter.maxYear !== undefined && !(year <= filter.maxYear)) return false;
  const rating = item.rating?.trim() ? Number(item.rating) : NaN;
  return filter.minRating === undefined || rating >= filter.minRating;
}

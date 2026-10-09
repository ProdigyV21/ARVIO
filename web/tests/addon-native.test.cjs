const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
// Values built inside the vm sandbox have their own Array prototype.
const plain = value => JSON.parse(JSON.stringify(value));

// An addon as the Android app syncs it: install id, catalogs/resources only inside `manifest`.
const androidAddon = {
  id: 'example.vod_0123456789ab', name: 'Example VOD', version: '1.0.0', isEnabled: true,
  url: 'https://vod.example.test/key/manifest.json',
  manifest: {
    id: 'example.vod', name: 'Example VOD', types: ['series'], idPrefixes: ['exvod:'],
    catalogs: [{ type: 'series', id: 'ex-news', name: 'News' }],
    resources: [{ name: 'catalog', types: [] }, { name: 'meta', types: ['series'], idPrefixes: ['exvod:'] }, { name: 'stream', types: ['series'], idPrefixes: ['exvod:'] }]
  }
};

const meta = {
  id: 'exvod:news:show', type: 'series', name: 'Show', description: 'About the show', poster: 'https://img.test/p.jpg', background: 'https://img.test/b.jpg',
  videos: [
    { id: 'exvod:news:show:1:2', title: 'Second', season: 1, episode: 2, released: '2026-02-03T00:00:00.000Z', thumbnail: 'https://img.test/2.jpg' },
    { id: 'exvod:news:show:1:1', title: 'First', season: 1, episode: 1, released: '2025-01-02T00:00:00.000Z' },
    { id: 'exvod:news:show:2:1', season: 2, episode: 1 }
  ]
};

function fixture() {
  const calls = [];
  const http = {
    proxiedUrl: url => `proxy:${url}`,
    jsonRequest: async url => {
      calls.push(url);
      if (url.includes('/meta/series/')) return { meta };
      if (url.includes('/stream/series/')) return { streams: [{ name: 'HD', url: 'https://vod.example.test/key/vodplay/x.m3u8' }] };
      if (url.includes('/subtitles/')) return { subtitles: [] };
      return {};
    }
  };
  const store = storage();
  const native = load('lib/addonNative.ts', { './http': http, './storage': store });
  const addons = load('lib/addons.ts', {
    './http': http, './addonNative': native,
    './config': { hasResolverConfig: () => false },
    './resolver': { getResolverStreamsProgressive: async () => { throw new Error('native items must not use the resolver'); } },
    './storage': store,
    './streamCompatibility': { isBrowserPlayableStream: () => true, isIosPlayableStream: () => true },
    './addonStreamInfo': { isInformationalAddonStream: () => false }
  });
  return { calls, native, addons };
}

test('Android-synced addons take their catalogs, resources and id prefixes from the manifest', () => {
  const { addons } = fixture();
  const addon = addons.normalizeAddon(androidAddon);
  assert.equal(addon.id, 'example.vod_0123456789ab');
  assert.deepEqual(plain(addon.catalogs.map(c => `${c.type}:${c.id}`)), ['series:ex-news']);
  assert.deepEqual(plain(addon.resources.map(r => typeof r === 'string' ? r : r.name)), ['catalog', 'meta', 'stream']);
  assert.deepEqual(plain(addon.idPrefixes), ['exvod:']);
  // A web-installed entry keeps its own top-level fields.
  const own = addons.normalizeAddon({ ...androidAddon, catalogs: [{ type: 'movie', id: 'top', name: 'Top' }] });
  assert.deepEqual(plain(own.catalogs.map(c => c.id)), ['top']);
});

test('native ids match the Android app and only self-describing addon ids qualify', () => {
  const { native, addons } = fixture();
  assert.equal(native.nativeItemId('example.vod_0123456789ab', 'exvod:news:evening-show'), -433811470);
  const addon = addons.normalizeAddon(androidAddon);
  assert.equal(native.addonServesOwnMeta(addon, 'series', 'exvod:news:show'), true);
  assert.equal(native.addonServesOwnMeta(addon, 'series', 'tt1234567'), false);
  assert.equal(native.addonServesOwnMeta(addon, 'movie', 'exvod:news:show'), false);
  assert.equal(native.addonServesOwnMeta({ ...addon, resources: ['stream'] }, 'series', 'exvod:news:show'), false);
});

test('native shows get details, seasons and sorted episodes from the addon meta', async () => {
  const { native, addons, calls } = fixture();
  const card = native.registerNativeItem(addons.normalizeAddon(androidAddon), meta, 'tv');
  assert.ok(card.id < 0);
  assert.equal(card.addonNativeId, 'exvod:news:show');
  assert.equal(native.isAddonNative(card), true);
  assert.equal(native.isAddonNative({ id: -5 }), false);
  const details = await native.getNativeDetails(card);
  assert.equal(details.overview, 'About the show');
  assert.equal(details.year, '2025');
  assert.deepEqual(plain(details.seasons.map(s => [s.seasonNumber, s.episodeCount])), [[1, 2], [2, 1]]);
  const episodes = await native.getNativeSeasonEpisodes(card.id, 1);
  assert.deepEqual(plain(episodes.map(e => [e.episodeNumber, e.name, e.airDate])), [[1, 'First', '2025-01-02'], [2, 'Second', '2026-02-03']]);
  assert.equal((await native.getNativeSeasonEpisodes(card.id, 2))[0].name, 'Episode 1');
  assert.ok(calls[0].endsWith('/key/meta/series/exvod%3Anews%3Ashow.json'));
  assert.equal(calls.filter(url => url.includes('/meta/')).length, 1, 'meta is cached');
});

test('a VOD collection finds the Android-synced addon and shows its titles natively', async () => {
  const { addons } = fixture();
  const calls = [];
  const http = { proxiedUrl: url => url, apiProxiedUrl: url => url, jsonRequest: async url => {
    calls.push(url);
    if (url.includes('/catalog/series/ex-news.json')) return { metas: [{ id: 'exvod:news:show', type: 'series', name: 'Show', poster: 'https://img.test/p.jpg' }] };
    return {};
  } };
  const api = load('lib/tmdb.ts', {
    './config': { config: {} }, './storage': storage(), './http': http,
    './metadata/anizip': {}, './metadata/dispatcher': {}, './mediaImages': { tmdbImageUrl: () => '' }
  }, { window: { location: { origin: 'https://web.invalid' } } });
  const items = await api.loadCollectionSource({ kind: 'ADDON_CATALOG', mediaType: 'tv', addonId: 'example.vod', addonCatalogType: 'series', addonCatalogId: 'ex-news' },
    'he', [addons.normalizeAddon(androidAddon)]);
  assert.equal(items.length, 1);
  assert.ok(items[0].id < 0);
  assert.equal(items[0].addonNativeId, 'exvod:news:show');
  assert.equal(calls.some(url => url.includes('/tv/')), false, 'no TMDB lookup for native titles');
});

test('native episodes request streams from the owning addon by the video id', async () => {
  const { native, addons, calls } = fixture();
  const owner = addons.normalizeAddon(androidAddon);
  const other = addons.normalizeAddon({ id: 'other', name: 'Other', manifestUrl: 'https://other.test/manifest.json', resources: ['stream'], types: ['series'] });
  const card = native.registerNativeItem(owner, meta, 'tv');
  const streams = await addons.getStreamsProgressive([owner, other], card, 1, 2);
  assert.equal(streams.length, 1);
  const streamCalls = calls.filter(url => url.includes('/stream/'));
  assert.deepEqual(plain(streamCalls), ['https://vod.example.test/key/stream/series/exvod%3Anews%3Ashow%3A1%3A2.json']);
  assert.equal(calls.some(url => url.includes('opensubtitles')), false);
  assert.deepEqual(plain(await addons.getStreamsProgressive([owner], card, 9, 9)), []);
});

// A broadcaster addon with addon-defined types and no movie type (live channels are "tv").
const broadcasterAddon = {
  id: 'example.channels', name: 'Channels', version: '1.0.0', manifestUrl: 'https://ch.example.test/manifest.json',
  types: ['series', 'tv', 'Podcasts'], idPrefixes: ['chx_', 'tt'],
  catalogs: [{ type: 'Podcasts', id: 'pods', name: 'Pods' }, { type: 'tv', id: 'live', name: 'Live' }],
  resources: ['catalog', 'meta', 'stream']
};
// A metadata addon re-listing the broadcaster's catalog under its own type spelling.
const metadataAddon = {
  id: 'aio-metadata_0123456789ab', name: 'Meta', version: '1.0.0', manifestUrl: 'https://meta.example.test/u/manifest.json',
  manifest: { id: 'aio-metadata' }, types: ['movie', 'series'], idPrefixes: ['tmdb:', 'tt'],
  catalogs: [{ type: 'VOD Example', id: 'relisted.kids', name: 'Kids' }], resources: ['catalog', 'meta']
};

function catalogApi(respond) {
  const calls = [];
  const http = { proxiedUrl: url => url, apiProxiedUrl: url => url, jsonRequest: async url => { calls.push(url); return respond(url) ?? {}; } };
  const api = load('lib/tmdb.ts', {
    './config': { config: {} }, './storage': storage(), './http': http,
    './metadata/anizip': {}, './metadata/dispatcher': {}, './mediaImages': { tmdbImageUrl: () => '' }
  }, { window: { location: { origin: 'https://web.invalid' } } });
  return { api, calls };
}

test('a re-listed catalog item belongs to the installed addon that serves its meta', async () => {
  const { addons } = fixture();
  const meta = addons.normalizeAddon(metadataAddon);
  const owner = addons.normalizeAddon(broadcasterAddon);
  const { api, calls } = catalogApi(url => url.includes('/catalog/') ? { metas: [{ id: 'chx_kids_1', type: 'series', name: 'Kids show' }] } : null);
  // The collection names the addon by manifest id and lower-cases the type.
  const items = await api.loadCollectionSource({ kind: 'ADDON_CATALOG', mediaType: 'tv', addonId: 'aio-metadata',
    addonCatalogType: 'vod example', addonCatalogId: 'relisted.kids' }, 'he', [meta, owner]);
  assert.equal(items.length, 1);
  assert.ok(items[0].id < 0);
  assert.equal(items[0].addonNativeAddonId, 'example.channels');
  assert.deepEqual(plain(calls), ['https://meta.example.test/u/catalog/VOD%20Example/relisted.kids.json']);
});

test('the named collection addon is asked for a catalog its manifest does not list', async () => {
  const { addons } = fixture();
  const { api, calls } = catalogApi(() => ({ metas: [] }));
  await api.loadCollectionSource({ kind: 'ADDON_CATALOG', mediaType: 'movie', addonId: 'aio-metadata',
    addonCatalogType: 'movie', addonCatalogId: 'streaming.nfx' }, 'he', [addons.normalizeAddon(metadataAddon)]);
  assert.deepEqual(plain(calls), ['https://meta.example.test/u/catalog/movie/streaming.nfx.json']);
});

test('podcasts keep their own type and live channels play by the "tv" type', async () => {
  const calls = [];
  const http = { proxiedUrl: url => url, jsonRequest: async url => {
    calls.push(url);
    if (url.includes('/meta/Podcasts/')) return { meta: { id: 'chx_pod_1', name: 'Pod', videos: [{ id: 'chx_pod_1:1:1', season: 1, episode: 1 }] } };
    if (url.includes('/stream/')) return { streams: [{ name: 'Live', url: 'https://ch.example.test/live.m3u8' }] };
    return {};
  } };
  const store = storage();
  const native = load('lib/addonNative.ts', { './http': http, './storage': store });
  const addons = load('lib/addons.ts', {
    './http': http, './addonNative': native, './config': { hasResolverConfig: () => false },
    './resolver': { getResolverStreamsProgressive: async () => [] }, './storage': store,
    './streamCompatibility': { isBrowserPlayableStream: () => true, isIosPlayableStream: () => true },
    './addonStreamInfo': { isInformationalAddonStream: () => false }
  });
  const owner = addons.normalizeAddon(broadcasterAddon);
  const pod = native.registerNativeItem(owner, { id: 'chx_pod_1', name: 'Pod' }, 'tv', 'Podcasts');
  assert.equal((await native.getNativeSeasonEpisodes(pod.id, 1)).length, 1);
  assert.ok(calls.some(url => url.endsWith('/meta/Podcasts/chx_pod_1.json')));
  const live = native.registerNativeItem(owner, { id: 'chx_live_1', name: 'Live' }, 'movie', 'tv');
  assert.equal(live.mediaType, 'movie');
  assert.equal((await addons.getStreamsProgressive([owner], live)).length, 1);
  assert.ok(calls.some(url => url.endsWith('/stream/tv/chx_live_1.json')), 'a live channel is asked for as "tv"');
});

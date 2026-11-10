const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const helper = load('lib/clientCatalog.ts');
const plain = value => JSON.parse(JSON.stringify(value));
const catalog = { id: 'personal', name: 'Personal', enabled: true, sourceType: 'addon', addonCatalogType: 'movie' };
const item = (id, type = 'movie') => ({ id, title: `Title ${id}`, mediaType: type, year: id % 2 ? '1999' : '2024', rating: '8', genreIds: [28] });
const mapper = load('lib/mappers.ts', { './config': { config: {} }, './tmdb': {}, './mediaImages': {} });
function loader(mocks = {}) {
  return load('lib/clientCatalogLoader.ts', { './clientCatalog': helper, './mappers': mapper, ...mocks }).loadClientDelegatedCatalog;
}

test('opaque and hierarchical client delegation URLs agree across providers', () => {
  for (const scheme of ['trakt', 'simkl', 'hybrid', 'homeserver']) {
    const action = scheme === 'homeserver' ? 'library' : 'watchlist';
    assert.deepEqual(plain(helper.parseClientDelegate(`${scheme}:${action}?type=series`)), plain(helper.parseClientDelegate(`${scheme}://${action}?type=series`)));
  }
  assert.equal(helper.parseClientDelegate('TRAKT:WATCHLIST?type=movies', 'series').mediaType, 'movie');
  assert.equal(helper.parseClientDelegate('trakt:watched?type=all', 'movie').mediaType, null);
  for (const uri of ['https://example.org', 'trakt:unknown', 'trakt:watchlist?type=anime', 'trakt:watchlist?type=tv&type=movie', 'trakt:watchlist#fragment']) assert.throws(() => helper.parseClientDelegate(uri));
});

test('client filters validate types and never execute arbitrary expressions', () => {
  for (const input of ['[]', 'null', '{"unknown":1}', '{"minYear":"2000"}', '{"minRating":11}', '{"minYear":2025,"maxYear":2020}', '{"genreIds":[-1]}']) assert.throws(() => helper.parseClientFilter(input));
  const filter = helper.parseClientFilter('{"mediaType":"movie","genreIds":[28],"minYear":2000,"maxYear":2025,"minRating":6}');
  assert.equal(helper.matchesClientFilter(item(2), filter), true);
  assert.equal(helper.matchesClientFilter(item(1), filter), false);
  assert.equal(helper.matchesClientFilter({ ...item(2), year: '' }, filter), false);
  assert.equal(helper.matchesClientFilter({ ...item(2), genreIds: [] }, filter), false);
});

test('fresh installs preserve delegation and filters in Android-compatible synced manifests', async () => {
  const declaration = { type: 'movie', id: 'personal', name: 'Personal', clientDelegate: 'trakt:watchlist', clientFilter: '{"minYear":2020}' };
  const addons = load('lib/addons.ts', {
    './http': { jsonRequest: async () => ({ id: 'fixture', catalogs: [declaration], resources: ['catalog'], types: ['movie'] }) },
    './addonNative': {}, './config': {}, './resolver': {}, './storage': storage(), './streamCompatibility': {}, './addonStreamInfo': {},
  });
  const installed = await addons.installAddon('https://addon.invalid/manifest.json');
  assert.equal(installed.catalogs[0].clientDelegate, declaration.clientDelegate);
  assert.equal(installed.catalogs[0].clientFilter, declaration.clientFilter);
  assert.equal(installed.manifest.catalogs[0].clientDelegate, declaration.clientDelegate);
  assert.equal(installed.manifest.catalogs[0].clientFilter, declaration.clientFilter);
});

test('filtering precedes the Home limit and hydration stops after the extra matching item', async () => {
  const hydrated = [];
  const services = { tracker: async () => ({ items: Array.from({ length: 30 }, (_, i) => item(i + 1)), isCurrent: () => true }) };
  const rows = await loader()('trakt:watchlist', { ...catalog, clientFilter: '{"minYear":2020}' }, [], 1, async seed => { hydrated.push(seed.id); return seed; }, services);
  assert.deepEqual(Array.from(rows, row => row.id), [2, 4]);
  assert.deepEqual(hydrated, [1, 2, 3, 4]);
});

test('production adapters dispatch Trakt, Simkl statuses and hybrid to their own connected clients', async () => {
  const calls = [];
  const raw = id => ({ type: 'movie', movie: { title: 'Film', ids: { tmdb: id } } });
  const read = loader({
    './store/clients': { traktClient: { currentProfileId: 'profile', watchlist: async () => { calls.push('trakt-watchlist'); return [raw(2)]; }, watched: async type => { calls.push(`trakt-${type}`); return [raw(4)]; } } },
    './simkl': { simklClient: { currentProfileId: 'profile', library: async status => { calls.push(`simkl-${status}`); return [raw(6)]; } } },
  });
  assert.equal((await read('trakt:watchlist', catalog, [], 2, async seed => seed))[0].id, 2);
  assert.equal((await read('trakt:watched', catalog, [], 2, async seed => seed))[0].id, 4);
  assert.equal((await read('simkl:plan', catalog, [], 2, async seed => seed))[0].id, 6);
  assert.equal((await read('hybrid:completed?tracker=simkl', catalog, [], 2, async seed => seed))[0].id, 6);
  assert.deepEqual(calls, ['trakt-watchlist', 'trakt-movies', 'simkl-plantowatch', 'simkl-completed']);
});

test('a profile switch during loading rejects stale tracker catalog data', async () => {
  let finish;
  const client = { currentProfileId: 'first', watchlist: () => new Promise(resolve => { finish = resolve; }) };
  const read = loader({ './store/clients': { traktClient: client } });
  const pending = read('trakt:watchlist', catalog, [], 2, async seed => seed);
  while (!finish) await new Promise(setImmediate);
  client.currentProfileId = 'second';
  finish([]);
  await assert.rejects(pending, /profile changed/);
});

test('explicit home library selection supports Android refs and preserves native identity', async () => {
  const calls = [];
  const read = loader({ './homeserver': { loadHomeServerLibraryPage: async (_servers, source, options) => {
    calls.push([source, options.offset]);
    return { items: [{ ...item(2), id: -7, isHomeServer: true, homeServerId: 'connection', homeServerItemId: 'native-7' }], hasMore: false };
  } } });
  const source = 'home_server_catalog|server|films|movie';
  const rows = await read(`homeserver:library?source=${encodeURIComponent(source)}`, catalog, [{ id: 'connection', serverId: 'server' }], 2, async () => { throw Error('Native items must not be replaced'); });
  assert.equal(rows[0].id, -7);
  assert.equal(rows[0].homeServerItemId, 'native-7');
  assert.deepEqual(calls, [['hslib:connection:films', 0]]);
  await assert.rejects(read('homeserver:library', catalog, [], 2, async seed => seed), /explicit library/);
});

test('the actual catalog path resolves manifest delegation and inherited filters, not addon HTTP', async () => {
  const read = loader({ './store/clients': { traktClient: { currentProfileId: 'p', watchlist: async () => [1, 3, 2, 4].map(id => ({ type: 'movie', movie: { title: 'Film', ids: { tmdb: id } } })) } } });
  const requests = [];
  const api = load('lib/tmdb.ts', {
    './clientCatalogLoader': { loadClientDelegatedCatalog: read },
    './config': { config: {} }, './storage': storage(),
    './metadata/anizip': {}, './metadata/dispatcher': {}, './mediaImages': { tmdbImageUrl: () => '' },
    './http': { proxiedUrl: value => value, apiProxiedUrl: value => value, jsonRequest: async value => {
      requests.push(value);
      const id = Number(new URL(value).pathname.split('/').at(-1));
      return { id, title: `Film ${id}`, adult: id === 1, release_date: id === 3 ? '1999-01-01' : '2024-01-01', genre_ids: [28] };
    } },
  }, { window: { location: { origin: 'https://web.invalid' } } });
  const addon = { id: 'fixture', enabled: true, manifestUrl: 'https://addon.invalid/manifest.json', catalogs: [
    { type: 'movie', id: 'personal', clientDelegate: 'trakt:watchlist', clientFilter: '{"minYear":2020}' },
  ] };
  const row = await api.loadCatalog({ ...catalog, addonId: 'fixture', addonCatalogId: 'personal' }, 'en', [addon], [], { limit: 1 });
  assert.deepEqual(Array.from(row.items, entry => entry.id), [2]);
  assert.equal(row.hasMore, true);
  assert.ok(requests.length && requests.every(url => !url.includes('addon.invalid')));
});


test('parseClientDelegate parses vod recent/categories and parseClientFilter parses availableOn', () => {
  const testAssert = require('node:assert/strict');
  const { load: loadModule } = require('./load.cjs');
  const clientCatalog = loadModule('lib/clientCatalog.ts');

  const recentVod = clientCatalog.parseClientDelegate('vod:recent?type=movie');
  testAssert.equal(recentVod.provider, 'vod');
  testAssert.equal(recentVod.action, 'recent');
  testAssert.equal(recentVod.mediaType, 'movie');
  testAssert.equal(recentVod.source, undefined);
  testAssert.equal(recentVod.category, undefined);

  const catVod = clientCatalog.parseClientDelegate('vod:categories?type=series&category=Action%20%26%20Sci-Fi');
  testAssert.equal(catVod.provider, 'vod');
  testAssert.equal(catVod.action, 'categories');
  testAssert.equal(catVod.mediaType, 'tv');
  testAssert.equal(catVod.category, 'Action & Sci-Fi');

  const vodFilter = clientCatalog.parseClientFilter('{"availableOn":"vod","minYear":2020}');
  testAssert.equal(vodFilter.availableOn, 'vod');
  testAssert.equal(vodFilter.minYear, 2020);
  const hsFilter = clientCatalog.parseClientFilter('{"availableOn":"homeserver"}');
  testAssert.equal(hsFilter.availableOn, 'homeserver');
});

test('loadClientDelegatedCatalog handles vod:recent, hybrid availableOn=vod, and filterClientCatalogItems', async () => {
  const testAssert = require('node:assert/strict');
  const { load: loadModule } = require('./load.cjs');
  const clientCatalog = loadModule('lib/clientCatalog.ts');
  const loader = loadModule('lib/clientCatalogLoader.ts', { './clientCatalog': clientCatalog });

  const movieA = { id: 101, mediaType: 'movie', title: 'Alpha VOD', tmdbId: 101, year: '2025' };
  const movieB = { id: 202, mediaType: 'movie', title: 'Beta Not On Server', tmdbId: 202, year: '2024' };

  const fakeServices = {
    tracker: async () => ({ items: [movieA, movieB], isCurrent: () => true }),
    homePage: async () => ({ items: [] }),
    vodSeeds: async () => [movieA],
    hasVodMatch: async (item) => item.id === 101,
    hasHomeServerMatch: async (item) => item.id === 202
  };

  const vodItems = await loader.loadClientDelegatedCatalog(
    'vod:recent?type=movie',
    { id: 'vod-recent-movies', sourceType: 'addon', name: 'Recent VOD', mediaType: 'movie', addonCatalogType: 'movie' },
    [],
    20,
    async (item) => ({ ...item, posterPath: '/hydrated.jpg' }),
    fakeServices
  );
  testAssert.equal(vodItems.length, 1);
  testAssert.equal(vodItems[0].id, 101);
  testAssert.equal(vodItems[0].posterPath, '/hydrated.jpg');

  const hybridVod = await loader.loadClientDelegatedCatalog(
    'trakt:watchlist?type=movie',
    {
      id: 'hybrid-watchlist-vod',
      sourceType: 'addon',
      name: 'Watchlist On VOD',
      mediaType: 'movie',
      addonCatalogType: 'movie',
      clientFilter: '{"availableOn":"vod"}'
    },
    [],
    20,
    async (item) => item,
    fakeServices
  );
  testAssert.deepEqual([...hybridVod.map((i) => i.id)], [101]);

  const filteredHttpServer = await loader.filterClientCatalogItems(
    [movieA, movieB],
    '{"availableOn":"homeserver"}',
    [{ id: 'hs-1', name: 'NAS', url: 'http://nas', enabled: true }],
    20,
    fakeServices
  );
  testAssert.deepEqual([...filteredHttpServer.map((i) => i.id)], [202]);
});

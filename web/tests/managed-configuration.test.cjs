const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const managed = load('lib/managedConfiguration.ts');
const plain = value => JSON.parse(JSON.stringify(value));
const permissions = { editAppearance: false, editCatalogs: false, addConnections: false, editMetadata: false, editPlayback: true };
const delivery = (extra = {}) => ({ managed: true, active: true, organizationId: 'org-a', revision: 1, name: 'Northside',
  config: { settings: { netflixHomeStyle: true, trailerSoundEnabled: false }, catalogs: [], collectionUrls: [] }, permissions, services: [], ...extra });
const personal = () => ({ catalogs: [{ id: 'personal', name: 'Personal', enabled: true }, { id: 'default', isPreinstalled: true }],
  hiddenCatalogIds: ['personal', 'partner-org-a-cinema'], homeServers: [{ id: 'own', token: 'own-token' }],
  iptvPlaylists: [], netflixHomeStyle: false, trailerSound: true, language: 'nl', favoriteChannelIds: ['favorite'] });
const adapters = { addon: async () => { throw Error('unexpected addon'); }, collection: async () => { throw Error('unexpected collection'); } };

test('managed settings never mutate personal settings and leaving restores them exactly', async () => {
  const before = personal(); const snapshot = JSON.stringify(before);
  const resources = await managed.resolveManagedResources(delivery({ config: { settings: { netflixHomeStyle: true }, catalogs: [{ id: 'cinema', name: 'Cinema' }], collectionUrls: [] } }), adapters);
  const effective = managed.overlayManagedSettings(before, resources);
  assert.equal(effective.netflixHomeStyle, true);
  assert.deepEqual(plain(effective.catalogs.map(c => c.id)), ['partner-org-a-cinema']);
  assert.equal(effective.language, 'nl');
  assert.equal(JSON.stringify(before), snapshot);
  assert.equal(managed.overlayManagedSettings(before, null), before);
  assert.equal(managed.overlayManagedSettings(before, { ...resources, delivery: { ...resources.delivery, active: false } }), before);
});

test('permissions protect assigned preferences but preserve language and watch history choices', async () => {
  assert.throws(() => managed.assertManagedEdit({ netflixHomeStyle: false }, delivery()), /managed by Northside/);
  assert.throws(() => managed.assertManagedEdit({ catalogs: [] }, delivery()), /managed/);
  assert.doesNotThrow(() => managed.assertManagedEdit({ language: 'nl', favoriteChannelIds: ['mine'] }, delivery()));
  assert.doesNotThrow(() => managed.assertManagedEdit({ netflixHomeStyle: false }, delivery({ active: false })));
  const resources = await managed.resolveManagedResources(delivery(), adapters);
  assert.equal(managed.overlayManagedSettings(personal(), resources, { netflixHomeStyle: false }).netflixHomeStyle, true);
});

test('personal cloud patches strip all managed services and catalogs', () => {
  const result = managed.personalManagedPatch({ homeServers: [{ id: 'own', token: 'personal' }, { id: 'partner-org-service', token: 'managed-secret' }],
    iptvPlaylists: [{ id: 'partner-org-iptv', m3uUrl: 'https://private.test/secret' }], catalogs: [{ id: 'partner-org-catalog' }, { id: 'mine' }], language: 'nl' });
  assert.deepEqual(plain(result), { homeServers: [{ id: 'own', token: 'personal' }], iptvPlaylists: [], catalogs: [{ id: 'mine' }], language: 'nl' });
  assert.equal(JSON.stringify(result).includes('managed-secret'), false);
});

test('per-customer IPTV credentials are encoded and the guide uses the same provider', async () => {
  const resources = await managed.resolveManagedResources(delivery({ services: [
    { id: 'iptv', name: 'TV', kind: 'iptv', url: 'https://provider.test/base/player_api.php', credentials: { username: 'a+b', password: 'a&b' } },
    { id: 'direct', name: 'Direct', kind: 'iptv', url: 'https://other.test/list.m3u?token=secret', credentials: null },
    { id: 'pending', name: 'Needs credentials', kind: 'iptv', url: 'https://provider.test', credentials: null },
    { id: 'server', name: 'Movies', kind: 'emby', url: 'https://server.test', credentials: { token: 'customer-token', userId: 'customer-id' } }
  ] }), adapters);
  assert.equal(resources.playlists.length, 2);
  const playlist = new URL(resources.playlists[0].m3uUrl);
  assert.equal(playlist.pathname, '/base/get.php');
  assert.equal(playlist.searchParams.get('username'), 'a+b');
  assert.equal(playlist.searchParams.get('password'), 'a&b');
  assert.equal(new URL(resources.playlists[0].epgUrl).pathname, '/base/xmltv.php');
  assert.equal(resources.homeServers[0].id, 'partner-org-a-server');
  assert.equal(resources.homeServers[0].token, 'customer-token');
});

test('IPTV feature choices and customer-specific M3U/EPG URLs survive managed resolution', async () => {
  const resources = await managed.resolveManagedResources(delivery({ services: [
    { id: 'vod', kind: 'iptv', name: 'Movies only', url: 'https://provider.test', credentials: { username: 'u', password: 'p' }, iptv: { mode: 'xtream', liveTv: false, movies: true, series: false, epg: false, catchup: false } },
    { id: 'm3u', kind: 'iptv', name: 'Live', url: 'https://provider.test', credentials: { playlistUrl: 'https://provider.test/custom?token=qa', epgUrl: 'https://guide.test/qa.xml' }, iptv: { mode: 'm3u', liveTv: true, movies: false, series: false, epg: true, catchup: false } }
  ] }), adapters);
  const [vod, live] = resources.playlists;
  assert.equal(vod.importLiveTv, false);
  assert.equal(vod.importVod, true);
  assert.equal(vod.importSeries, false);
  assert.equal(vod.epgUrl, undefined);
  assert.equal(vod.enableEpg, false);
  assert.equal(live.m3uUrl, 'https://provider.test/custom?token=qa');
  assert.equal(live.epgUrl, 'https://guide.test/qa.xml');
  assert.equal(live.enableCatchup, false);
  assert.equal(live.importVod, false);
});

test('collection resolution is bounded, order stable and incomplete revisions do not apply', async () => {
  let inFlight = 0, peak = 0;
  const config = { settings: {}, catalogs: [], collectionUrls: Array.from({ length: 12 }, (_, n) => `https://feed.test/${n}`) };
  const resources = await managed.resolveManagedResources(delivery({ config }), { ...adapters, collection: async url => {
    peak = Math.max(peak, ++inFlight); const index = Number(url.split('/').at(-1));
    await new Promise(resolve => setTimeout(resolve, 12 - index)); inFlight--;
    return [{ id: String(index), name: String(index) }];
  } });
  assert.ok(peak <= 4);
  assert.deepEqual(plain(resources.catalogs.map(c => c.name)), Array.from({ length: 12 }, (_, n) => String(n)));
  await assert.rejects(managed.resolveManagedResources(delivery({ config }), { ...adapters, collection: async () => { throw Error('Provider unavailable'); } }), /Provider unavailable/);
});

test('scoped addon identities preserve collection references without merging personal rails', async () => {
  const collection = { id: 'row', name: 'Movies', packId: 'feed', collectionRailKey: 'movies', addonId: 'org.movies', collectionSources: [{ kind: 'ADDON_CATALOG', addonId: 'org.movies', addonCatalogId: 'popular', addonCatalogType: 'movie' }] };
  const config = { settings: {}, catalogs: [], collectionUrls: ['https://feed.test/collections.json'] };
  const resources = await managed.resolveManagedResources(delivery({ config, services: [{ id: 'svc', name: 'Movies', kind: 'addon', url: 'https://addon.test/manifest.json', credentials: null }] }), {
    addon: async () => ({ id: 'org.movies', name: 'Movies', manifestUrl: 'https://addon.test/manifest.json', catalogs: [{ id: 'popular', type: 'movie' }] }),
    collection: async () => [collection]
  });
  assert.equal(resources.addons[0].id, 'partner-org-a-svc');
  assert.equal(resources.catalogs[0].addonId, resources.addons[0].id);
  assert.equal(resources.catalogs[0].collectionSources[0].addonId, resources.addons[0].id);
  assert.equal(resources.catalogs[0].packId, 'partner-org-a-feed');
  assert.equal(resources.catalogs[0].collectionRailKey, 'partner-org-a-movies');
  assert.equal(collection.collectionSources[0].addonId, 'org.movies');
});

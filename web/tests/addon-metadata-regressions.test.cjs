const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const auth = { session: { accessToken: 'test-token', userId: 'test-account' },
  isNetlifySession: true, accessToken: async () => 'test-token' };
const addon = (setupUpdatedAt, metadataUpdatedAt, catalogs) => ({
  id: 'family', name: 'My family addon', manifestUrl: 'https://addon.example/p/family/manifest.json',
  enabled: true, isEnabled: true, setupUpdatedAt, metadataUpdatedAt,
  version: String(metadataUpdatedAt), catalogs, resources: ['catalog'],
  manifest: { id: 'family', version: String(metadataUpdatedAt), catalogs, resources: ['catalog'] }
});
const row = (clientDelegate = 'trakt:watchlist') => ({ id: 'family', type: 'movie', clientDelegate });
const plain = value => JSON.parse(JSON.stringify(value));

function cloudHarness(root) {
  let payload = root;
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': {},
    './http': { jsonRequest: async (_url, request) => {
      if (request?.method === 'POST') { payload = JSON.parse(request.body).payload; return { accepted: true }; }
      return { payload };
    } }
  });
  return { cloud, payload: () => payload };
}

test('web retains newer setup but applies independently newer manifest metadata', async () => {
  const settings = { ...addon(200, 300, [row()]), enabled: false, isEnabled: false };
  const metadata = addon(100, 400, [row('vod:recent')]);
  for (const [rootAddon, profileAddon] of [[settings, metadata], [metadata, settings]]) {
    const h = cloudHarness({ addons: [rootAddon], addonsByProfile: { p1: [profileAddon] } });
    const result = (await h.cloud.pullCloudPayload(auth, 'p1')).addons[0];
    assert.equal(result.enabled, false);
    assert.equal(result.setupUpdatedAt, 200);
    assert.equal(result.metadataUpdatedAt, 400);
    assert.equal(result.manifest.catalogs[0].clientDelegate, 'vod:recent');
    assert.equal(result.catalogs[0].clientDelegate, 'vod:recent');
  }
});

test('web sync and cache enrichment preserve explicit empty catalogues and resources', async () => {
  const old = addon(200, 300, [row()]);
  const cleared = { ...addon(200, 600, []), resources: [],
    manifest: { ...old.manifest, version: '600', catalogs: [], resources: [] } };
  const h = cloudHarness({ addons: [old], addonsByProfile: { p1: [old] },
    addonMetadataByKey: { 'https://addon.example/p/family': {
      manifest: old.manifest, catalogs: old.catalogs, resources: old.resources, metadataUpdatedAt: 300 } } });
  await h.cloud.saveCloudAddons(auth, [cleared], 'p1');
  const saved = h.payload();
  assert.deepEqual(saved.addons[0].catalogs, []);
  assert.deepEqual(saved.addonMetadataByKey['https://addon.example/p/family'].catalogs, []);
  assert.deepEqual(saved.addonMetadataByKey['https://addon.example/p/family'].resources, []);
  const pulled = (await h.cloud.pullCloudPayload(auth, 'p1')).addons[0];
  assert.deepEqual(plain(pulled.catalogs), []);
  assert.deepEqual(plain(pulled.manifest.catalogs), []);
  assert.deepEqual(plain(pulled.resources), []);
});

test('omitted catalogue fields in a legacy push still retain the known manifest', async () => {
  const old = addon(200, 300, [row('vod:recent')]);
  const h = cloudHarness({ addons: [old], addonsByProfile: { p1: [old] } });
  await h.cloud.saveCloudAddons(auth, [{ id: old.id, name: old.name, manifestUrl: old.manifestUrl }], 'p1');
  const result = (await h.cloud.pullCloudPayload(auth, 'p1')).addons[0];
  assert.equal(result.catalogs[0].clientDelegate, 'vod:recent');
});

test('web availability checks a series title without preparing its pilot or playback', async () => {
  const client = load('lib/clientCatalog.ts');
  const calls = [];
  const homeserver = load('lib/homeserver.ts', { './http': {
    proxiedUrl: url => url,
    jsonRequest: async url => {
      calls.push(url);
      assert.doesNotMatch(url, /Episodes|PlaybackInfo|stream\./i);
      return { Items: [{ Id: 'series-with-only-season-two', Name: 'Family series', ProductionYear: 2024,
        ProviderIds: { Tmdb: '42' }, Type: 'Series' }] };
    }
  } });
  const loader = load('lib/clientCatalogLoader.ts', { './clientCatalog': client, './homeserver': homeserver });
  const result = await loader.filterClientCatalogItems(
    [{ id: 42, title: 'Family series', mediaType: 'tv', year: '2024' }],
    '{"availableOn":"homeserver"}',
    [{ id: 'nas', type: 'jellyfin', url: 'https://nas.example', enabled: true, token: 'test', userId: 'family' }], 20);
  assert.equal(result.length, 1);
  assert.ok(calls.length > 0);
  assert.ok(calls.some(url => url.includes('IncludeItemTypes=Series')));
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const { mergeCatalogs, defaultCatalogs, updateHiddenCatalogIds } = load('lib/catalogs.ts');
const { collectionHomeCatalogs, collectionFolders } = load('lib/collectionPresentation.ts');
const ids = rows => Array.from(rows, row => row.id);
const catalog = (id, patch = {}) => ({ id, name: id, sourceType: 'preinstalled', enabled: true, ...patch });
const home = (saved, hidden = [], hiddenAddons = []) => collectionHomeCatalogs(
  mergeCatalogs(saved, hidden, hiddenAddons).filter(row => row.enabled)
);

test('only an absent saved list seeds defaults; an empty or retired-only list stays empty', () => {
  assert.equal(mergeCatalogs(undefined).length, defaultCatalogs.length);
  assert.deepEqual(ids(mergeCatalogs([])), []);
  assert.deepEqual(ids(mergeCatalogs([catalog('sports'), catalog('popular_live_tv')])), []);
  assert.deepEqual(ids(mergeCatalogs([null, { id: 'invalid' }])), []);
});

test('hidden Android addon catalogs are excluded without hiding unrelated sources', () => {
  const rows = [catalog('custom'), catalog('addon', { sourceType: 'ADDON' }), catalog('built-in')];
  assert.deepEqual(ids(home(rows, ['built-in'], ['addon', 'custom'])), ['custom']);
  assert.equal(mergeCatalogs(rows, [], ['addon']).find(row => row.id === 'addon').enabled, false);
});

test('catalog normalization keeps saved order and deduplicates like Android', () => {
  assert.deepEqual(ids(home([catalog('second'), catalog('first'), catalog('second')])), ['second', 'first']);
});

test('removing or hiding a collection rail never promotes its folders to extra Home rows', () => {
  const rail = catalog('rail', { kind: 'COLLECTION_RAIL', collectionRailKey: 'spotlight' });
  const folders = ['The Headliner', 'The Visionary'].map(id => catalog(id, {
    kind: 'COLLECTION', collectionRailKey: 'spotlight', collectionSources: [{ kind: 'TMDB_PERSON', tmdbPersonId: 1 }]
  }));
  const regular = catalog('trending');
  assert.deepEqual(ids(home([regular, rail, ...folders])), ['trending', 'rail']);
  assert.deepEqual(ids(collectionFolders(rail, mergeCatalogs([rail, ...folders]))), ['The Headliner', 'The Visionary']);
  assert.deepEqual(ids(home([regular, rail, ...folders], ['rail'])), ['trending']);
  assert.deepEqual(ids(home([regular, ...folders])), ['trending']);
  assert.deepEqual(ids(home([regular, { ...rail, enabled: false }, ...folders])), ['trending']);
  assert.deepEqual(ids(home([regular, rail, ...folders], ['collection_row_spotlight'])), ['trending']);
});

test('legacy collection group visibility and individual hidden folders are retained', () => {
  const rail = catalog('network-rail', { kind: 'COLLECTION_RAIL', collectionGroup: 'NETWORK' });
  const netflix = catalog('netflix-folder', { kind: 'COLLECTION', collectionGroup: 'NETWORK' });
  const hulu = catalog('hulu-folder', { kind: 'COLLECTION', collectionGroup: 'NETWORK' });
  assert.deepEqual(ids(home([rail, netflix, hulu], ['collection_row_network'])), []);
  assert.deepEqual(ids(collectionFolders(rail, mergeCatalogs([rail, netflix, hulu], ['netflix-folder']))), ['hulu-folder']);
});

test('visibility edits preserve absent catalog tombstones but allow explicitly showing a catalog', () => {
  const rows = [catalog('shown'), catalog('hidden', { enabled: false })];
  assert.deepEqual(Array.from(updateHiddenCatalogIds(rows, ['deleted', 'shown'])), ['deleted', 'hidden']);
  const rail = catalog('rail', { kind: 'COLLECTION_RAIL', collectionRailKey: 'custom' });
  assert.deepEqual(Array.from(updateHiddenCatalogIds([rail], ['collection_row_custom'])), []);
});

const auth = { session: { userId: 'test-account', accessToken: 'fixture' }, isNetlifySession: true, accessToken: async () => 'fixture' };
function cloudFixture(initial) {
  let remote = structuredClone(initial);
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '[]', parseHomeServerConnectionJson: () => [] },
    './iptv': load('lib/iptv.ts', { './storage': storage(), './http': {} }), './mediaImages': {},
    './http': { jsonRequest: async (_url, init) => {
      if (init?.method === 'POST') {
        remote = JSON.parse(init.body).payload;
        return { accepted: true };
      }
      return { payload: structuredClone(remote) };
    } }
  });
  return { cloud, get remote() { return remote; } };
}

test('Android hidden addon scope is read on web, including JSON-encoded lists and empty overrides', async () => {
  const rows = [catalog('kept'), catalog('addon', { sourceType: 'ADDON' })];
  const f = cloudFixture({ catalogsByProfile: { p1: JSON.stringify(rows), p2: rows },
    hiddenAddonByProfile: { p1: JSON.stringify(['addon']), p2: [] },
    settings: { hiddenAddonCatalogIds: ['kept'] } });
  const a = (await f.cloud.pullCloudPayload(auth, 'p1')).settings;
  const b = (await f.cloud.pullCloudPayload(auth, 'p2')).settings;
  assert.deepEqual(ids(home(a.catalogs, a.hiddenCatalogIds, a.hiddenAddonCatalogIds)), ['kept']);
  assert.deepEqual(ids(home(b.catalogs, b.hiddenCatalogIds, b.hiddenAddonCatalogIds)), ['kept', 'addon']);
});

test('empty scoped and legacy lists do not resurrect catalogs or visibility from another profile', async () => {
  const f = cloudFixture({ catalogs: [], settings: { catalogs: [catalog('stale')], hiddenCatalogIds: ['kept'] },
    catalogsByProfile: { empty: [], selected: [catalog('kept')] } });
  for (const profile of ['empty', 'legacy']) {
    const s = (await f.cloud.pullCloudPayload(auth, profile)).settings;
    assert.deepEqual(ids(home(s.catalogs, s.hiddenCatalogIds, s.hiddenAddonCatalogIds)), []);
  }
  const s = (await f.cloud.pullCloudPayload(auth, 'selected')).settings;
  assert.deepEqual(ids(home(s.catalogs, s.hiddenCatalogIds, s.hiddenAddonCatalogIds)), ['kept']);
});

test('an account without any saved catalog settings does not clear local visibility on its first sync', async () => {
  const f = cloudFixture({});
  const s = (await f.cloud.pullCloudPayload(auth, 'new')).settings;
  assert.equal(s.catalogs, undefined);
  assert.equal(s.hiddenCatalogIds, undefined);
  assert.equal(s.hiddenAddonCatalogIds, undefined);
  assert.equal(s.hiddenHomeServerCatalogIds, undefined);
});

test('web addon visibility changes sync to Android with a per-profile timestamp and retain remote hides', async () => {
  const f = cloudFixture({ hiddenAddonByProfile: { p1: ['remote-hidden', 'unhidden'], p2: ['other-profile'] } });
  const baseline = { catalogs: [], hiddenCatalogIds: [], hiddenAddonCatalogIds: ['unhidden'], hiddenHomeServerCatalogIds: [],
    iptvPlaylists: [], homeServers: [], favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [] };
  await f.cloud.saveCloudSettings(auth, { ...baseline, hiddenAddonCatalogIds: ['local-hidden'] }, [], 'p1', [], baseline, 500);
  assert.deepEqual(f.remote.hiddenAddonByProfile.p1, ['local-hidden', 'remote-hidden']);
  assert.deepEqual(f.remote.hiddenAddonByProfile.p2, ['other-profile']);
  assert.equal(f.remote.fieldUpdatedAt['c:p1:hiddenAddonByProfile'], 500);
  const pulled = (await f.cloud.pullCloudPayload(auth, 'p1')).settings;
  assert.deepEqual(Array.from(pulled.hiddenAddonCatalogIds), ['local-hidden', 'remote-hidden']);
});

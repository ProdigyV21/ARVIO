const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const { defaultCatalogs, legacyWebDefaultCatalogs, mergeCatalogs, setDefaultCollectionsEnabled,
  DEFAULT_COLLECTIONS_DISABLED } = load('lib/catalogs.ts');
const { collectionHomeCatalogs, collectionFolders, collectionMediaTypes } = load('lib/collectionPresentation.ts');
const plain = value => JSON.parse(JSON.stringify(value));
const ids = rows => Array.from(rows, row => row.id);
const fresh = () => plain(defaultCatalogs);
const legacy = () => plain(legacyWebDefaultCatalogs);
const custom = { id: 'my-list', name: 'My list', sourceType: 'mdblist', sourceUrl: 'https://mdblist.com/lists/me/list', enabled: true };

test('fresh web defaults contain the same ten catalogs and five collection rails as Android', () => {
  const defaults = mergeCatalogs(undefined);
  assert.equal(defaults.length, 58);
  assert.deepEqual(ids(defaults.filter(c => c.kind === 'STANDARD')), [
    'trending_movies', 'trending_tv', 'trending_anime', 'favorite_tv', 'top10_movies_today',
    'top10_shows_today', 'just_added', 'top_movies_week', 'new_kdramas', 'coming_soon'
  ]);
  const home = collectionHomeCatalogs(defaults);
  assert.equal(home.length, 15);
  assert.deepEqual(ids(home).slice(0, 8), ['trending_movies', 'trending_tv', 'trending_anime',
    'collection_rail_featured', 'collection_rail_service', 'collection_rail_genre', 'collection_rail_franchise', 'collection_rail_decade']);
  assert.equal(new Set(ids(defaults)).size, 58);
  for (const id of ['latest_tv', 'action', 'bond', 'tmdb_popular_movies', 'tmdb_popular_tv', 'sports']) {
    assert.ok(!defaults.some(c => c.id === id), `${id} is not a default row`);
  }
});

test('all 43 folders have artwork and direct sources with the correct media tabs', () => {
  const folders = defaultCatalogs.filter(c => c.kind === 'COLLECTION');
  assert.equal(folders.length, 43);
  for (const [group, count] of [['FEATURED', 4], ['SERVICE', 13], ['GENRE', 10], ['FRANCHISE', 10], ['DECADE', 6]]) {
    const rail = defaultCatalogs.find(c => c.kind === 'COLLECTION_RAIL' && c.collectionGroup === group);
    assert.equal(collectionFolders(rail, defaultCatalogs).length, count);
  }
  for (const folder of folders) {
    assert.ok(folder.collectionCoverImageUrl.startsWith('https://image.tmdb.org/'));
    assert.ok(folder.collectionDescription);
    assert.ok(folder.collectionSources.length > 0);
    assert.ok(folder.collectionSources.every(s => s.kind.startsWith('TMDB_')));
    assert.equal(folder.requiredAddonUrls.length, 0);
  }
  assert.equal(collectionMediaTypes(folders.find(c => c.name === 'Netflix')).join(), 'movie,tv');
  assert.equal(collectionMediaTypes(folders.find(c => c.name === 'Harry Potter')).join(), 'movie');
  const paramount = folders.find(c => c.name === 'Paramount+');
  assert.deepEqual([...new Set(paramount.collectionSources.map(s => s.tmdbWatchProviderId))], [2303, 2616]);
  assert.ok(paramount.collectionSources.every(s => s.watchRegion === 'US'));
});

test('recognizable old web defaults migrate once while retaining custom lists and visibility', () => {
  const old = legacy();
  old.find(c => c.id === 'top10_shows_today').enabled = false;
  old.find(c => c.id === 'comedy').enabled = false;
  const migrated = mergeCatalogs([custom, ...old, { ...custom, id: 'last' }], ['action', 'harry_potter']);
  assert.deepEqual(ids(migrated), ['my-list', ...ids(defaultCatalogs), 'last']);
  for (const id of ['top10_shows_today', 'collection_genre_comedy', 'collection_genre_action', 'collection_franchise_harry_potter']) {
    assert.equal(migrated.find(c => c.id === id).enabled, false, id);
  }
  assert.equal(migrated[0].sourceUrl, custom.sourceUrl);
  assert.deepEqual(plain(mergeCatalogs(migrated, ['action', 'harry_potter'])), plain(migrated));
});

test('removed reordered renamed or replaced defaults and explicitly empty profiles are not reset', () => {
  for (const old of [[], [custom], legacy().slice(1), [...legacy().slice(1), legacy()[0]],
    legacy().map(c => c.id === 'trending_movies' ? { ...c, name: 'My movies' } : c),
    legacy().map(c => c.id === 'action' ? { ...c, sourceUrl: custom.sourceUrl } : c)]) {
    assert.deepEqual(ids(mergeCatalogs(old)), ids(old));
  }
  assert.deepEqual(ids(mergeCatalogs(fresh())), ids(defaultCatalogs), 'Android sync is not expanded or reordered');
});

test('migrating a fully hidden old home does not resurrect new collection rows', () => {
  assert.ok(mergeCatalogs(legacy(), ids(legacy())).every(c => !c.enabled));
  assert.ok(mergeCatalogs(legacy().map(c => ({ ...c, enabled: false }))).every(c => !c.enabled));
});

test('Android global collection disable and legacy hides apply without hiding imported collections', () => {
  const imported = { ...fresh().find(c => c.kind === 'COLLECTION'), id: 'usercol_mine', isPreinstalled: false };
  const rows = [...fresh(), imported];
  const hidden = mergeCatalogs(rows, [DEFAULT_COLLECTIONS_DISABLED]);
  assert.equal(hidden.filter(c => c.enabled && c.kind === 'COLLECTION').length, 1);
  assert.equal(hidden.find(c => c.id === 'trending_movies').enabled, true);
  const oldHides = ['collection_rail_service', 'collection_rail_genre', 'collection_rail_franchise',
    ...ids(rows.filter(c => c.kind === 'COLLECTION' && c.collectionGroup === 'SERVICE'))];
  assert.equal(mergeCatalogs(fresh(), oldHides).filter(c => c.kind === 'COLLECTION' && c.enabled).length, 0);
});

test('explicit default-collection toggles round trip the Android marker without affecting custom folders', () => {
  const imported = { ...fresh().find(c => c.kind === 'COLLECTION'), id: 'usercol_mine', isPreinstalled: false };
  const disabled = setDefaultCollectionsEnabled([...fresh(), imported], ['deleted'], false);
  assert.ok(disabled.hiddenIds.includes(DEFAULT_COLLECTIONS_DISABLED));
  assert.equal(disabled.catalogs.find(c => c.id === imported.id).enabled, true);
  const enabled = setDefaultCollectionsEnabled(mergeCatalogs(disabled.catalogs, disabled.hiddenIds), disabled.hiddenIds, true);
  assert.deepEqual(Array.from(enabled.hiddenIds), ['deleted']);
  assert.ok(mergeCatalogs(enabled.catalogs, enabled.hiddenIds).every(c => c.enabled));
});

test('default service genre discover and franchise sources load without add-ons', async () => {
  const calls = [];
  const api = load('lib/tmdb.ts', {
    './config': { config: {} }, './storage': storage(), './metadata/anizip': {}, './metadata/dispatcher': {},
    './mediaImages': { tmdbImageUrl: () => '' }, './http': {
      proxiedUrl: x => x, apiProxiedUrl: x => x, jsonRequest: async raw => {
        const url = new URL(raw, 'https://web.invalid'); calls.push(url);
        if (url.pathname.includes('/collection/')) return { parts: [
          { id: 2, title: 'Later', release_date: '2002-01-01' }, { id: 1, title: 'Earlier', release_date: '2001-01-01' }
        ] };
        return { results: [{ id: 1, title: 'Film', name: 'Show' }], total_pages: 1 };
      }
    }
  }, { window: { location: { origin: 'https://web.invalid' } } });
  for (const name of ['Netflix', 'Action', 'Under two hours', 'Harry Potter']) {
    const folder = defaultCatalogs.find(c => c.kind === 'COLLECTION' && c.name === name);
    for (const source of folder.collectionSources) assert.ok((await api.loadCollectionSource(source, 'en-US', [])).length);
  }
  assert.ok(calls.some(url => url.pathname.endsWith('/discover/tv') && url.searchParams.get('with_watch_providers') === '8'));
  assert.ok(calls.some(url => url.searchParams.get('with_genres') === '10759'));
  assert.ok(calls.some(url => url.searchParams.get('with_runtime.lte') === '120'));
  const films = await api.loadCollectionSource({ kind: 'TMDB_COLLECTION', tmdbCollectionId: 1241 }, 'en-US', []);
  assert.deepEqual(ids(films), [1, 2]);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

// Values built inside the vm sandbox have their own Array prototype.
const plain = value => JSON.parse(JSON.stringify(value));
const channel = (id, extra = {}) => ({ id, name: id.toUpperCase(), group: 'News', streamUrl: `https://tv.test/${id}.m3u8`, ...extra });

function favoriteTv() {
  const store = storage();
  return { api: load('lib/favoriteTv.ts', { './storage': store }), store };
}

test('favorites keep the user order and match Android cloud ids', () => {
  const { api } = favoriteTv();
  const channels = [channel('list_2:a'), channel('list_2:b', { cloudId: 'list_2:m3u:b:1-x' }), channel('list_2:c', { syncAliases: ['old-c'] })];
  const resolved = api.resolveFavoriteChannels(['old-c', 'missing', 'list_2:m3u:b:1-x', 'list_2:a', 'list_2:a'], channels);
  assert.deepEqual(plain(resolved.map(c => c.id)), ['list_2:c', 'list_2:b', 'list_2:a']);
});

test('the saved copy belongs to one profile and playlist set', () => {
  const { api } = favoriteTv();
  api.saveFavoriteChannels('acct:p1', 'sig-1', ['a', 'b'], [channel('a')]);
  assert.deepEqual(plain(api.cachedFavoriteChannels('acct:p1', 'sig-1')), { favoriteIds: ['a', 'b'], channels: [channel('a')] });
  assert.equal(api.cachedFavoriteChannels('acct:p1', 'sig-2'), null, 'a changed playlist set invalidates it');
  assert.equal(api.cachedFavoriteChannels('acct:p2', 'sig-1'), null, 'another profile has its own copy');
});

test('only a missing copy or a newly added favorite reloads the playlist', () => {
  const { api } = favoriteTv();
  assert.equal(api.favoriteCopyIsStale(null, ['a']), true);
  assert.equal(api.favoriteCopyIsStale(null, []), false);
  const copy = { favoriteIds: ['a', 'gone'], channels: [channel('a')] };
  assert.equal(api.favoriteCopyIsStale(copy, ['a', 'gone']), false, 'a favorite the playlist dropped is not a reason to reload');
  assert.equal(api.favoriteCopyIsStale(copy, ['a']), false);
  assert.equal(api.favoriteCopyIsStale(copy, ['new', 'a']), true);
});

test('the Favorite TV catalog stays in the saved list so Home keeps its position', () => {
  const { mergeCatalogs, defaultCatalogs } = load('lib/catalogs.ts');
  const saved = [{ id: 'trending_movies', name: 'Trending', sourceType: 'mdblist', sourceUrl: 'https://mdblist.com/lists/x', enabled: true },
    { id: 'favorite_tv', name: 'Favorite TV', sourceType: 'preinstalled', mediaType: 'tv', enabled: true, isPreinstalled: true }];
  assert.deepEqual(plain(mergeCatalogs(saved).map(c => c.id)), ['trending_movies', 'favorite_tv']);
  assert.equal(defaultCatalogs.find(c => c.id === 'favorite_tv').sourceType, 'preinstalled');
  assert.ok(defaultCatalogs.findIndex(c => c.id === 'favorite_tv') > defaultCatalogs.findIndex(c => c.id === 'collection_rail_decade'));
});

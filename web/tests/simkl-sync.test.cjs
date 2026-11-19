const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load, storage } = require('./load.cjs');

function libraryFixture() {
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: {} }, './sync': {}, './storage': storage(), './http': {}, './tmdb': {}
  });
  const client = new SimklClient();
  client.setProfile('review-profile');
  client.setToken({ access_token: 'fake-test-token' });
  const row = (id, type) => ({ status: 'plantowatch', [type === 'movies' ? 'movie' : 'show']: {
    title: `Title ${id}`, ids: { simkl: id, tmdb: id }
  } });
  const state = {
    activities: { all: '2026-09-08T10:00:00Z' },
    initial: { movies: [row(1, 'movies'), row(2, 'movies')], shows: [row(3, 'shows')], anime: [row(4, 'anime')] },
    delta: { movies: [], shows: [], anime: [] },
    ids: { movies: [row(2, 'movies')], shows: [], anime: [] },
    failIds: false, requests: []
  };
  client.simkl = async request => {
    state.requests.push(request);
    if (request === '/sync/activities') return state.activities;
    const url = new URL(request, 'https://example.invalid');
    if (url.searchParams.has('date_from')) return state.delta;
    const type = url.pathname.split('/')[3];
    if (url.searchParams.get('extended') === 'ids_only') {
      if (state.failIds) throw new Error('HTTP 503');
      return { [type]: state.ids[type] };
    }
    return { [type]: state.initial[type] };
  };
  const expire = () => { client.snapshot.checkedAt = 0; client.lastSnapshotFailureAt = 0; };
  return { client, state, expire, row };
}

test('Simkl deletions reconcile movie, TV and anime IDs without redownloading their metadata', async () => {
  const { client, state, expire } = libraryFixture();
  assert.equal((await client.watchlist()).length, 4);
  state.activities = {
    all: '2026-09-08T11:00:00Z', movies: { removed_from_list: 'new' },
    tv_shows: { removed_from_list: 'new' }, anime: { removed_from_list: 'new' }
  };
  expire();
  const rows = await client.watchlist();
  assert.deepEqual(Array.from(rows, row => (row.movie ?? row.show).ids.tmdb), [2]);
  assert.equal(state.requests.filter(p => p.includes('ids_only')).length, 3);
  const before = state.requests.length;
  expire();
  await client.watchlist();
  assert.deepEqual(state.requests.slice(before), ['/sync/activities']);
});

test('Simkl updates retain unchanged rows and do not fetch IDs without a deletion', async () => {
  const { client, state, expire, row } = libraryFixture();
  await client.watchlist();
  state.activities = { all: '2026-09-08T11:00:00Z' };
  state.delta.movies = [{ ...row(1, 'movies'), status: 'completed' }];
  expire();
  const rows = await client.watchlist();
  assert.deepEqual(Array.from(rows, row => (row.movie ?? row.show).ids.tmdb), [2, 3, 4]);
  assert.equal(state.requests.filter(p => p.includes('ids_only')).length, 0);
});

test('Simkl TV and anime changes are not masked by an unchanged movie activity marker', async () => {
  const { client, state, expire, row } = libraryFixture();
  state.activities = { movies: { all: '2026-09-08T10:00:00Z' }, tv_shows: { all: '2026-09-08T10:00:00Z' } };
  await client.watchlist();
  state.activities.tv_shows.all = '2026-09-08T11:00:00Z';
  state.delta.shows = [{ ...row(3, 'shows'), status: 'completed' }];
  expire();
  const result = await client.watchlist();
  assert.ok(!result.some(item => item.show?.ids?.tmdb === 3));
  assert.equal(client.snapshot.activity, '2026-09-08T11:00:00Z');
  assert.equal(state.requests.filter(p => p.includes('date_from=2026-09-08T10')).length, 1);
});

test('calendar retains anime without TMDB IDs for bounded identity resolution instead of silently dropping them', async () => {
  const { client, state } = libraryFixture();
  state.initial.anime = [{ status: 'watching', show: { title: 'Anime with external identity', year: 2026, ids: { simkl: 900, tvdb: 123, imdb: 'tt1234567' } } }];
  const rows = await client.watchlist(['plantowatch', 'watching'], { throwOnError: true, resolveIds: false });
  assert.equal(rows.length, 4);
  const { traktItemToMedia } = load('lib/mappers.ts', { './config': { config: {} }, './tmdb': {}, './mediaImages': {} });
  const anime = traktItemToMedia(rows.find(row => row.show?.ids?.simkl === 900));
  assert.equal(anime.mediaType, 'tv');
  assert.ok(anime.id < 0, 'Foreign numeric IDs must never be mistaken for TMDB IDs');
  assert.equal(anime.tvdbId, 123);
  assert.equal(anime.imdbId, 'tt1234567');
  assert.equal(traktItemToMedia({ show: { ids: { tmdb: '321' } } }).id, 321);
});

test('failed deletion reconciliation retains the snapshot and retries from the unacknowledged watermark', async () => {
  const { client, state, expire } = libraryFixture();
  await client.watchlist();
  state.activities = { all: '2026-09-08T11:00:00Z', movies: { removed_from_list: 'new' } };
  state.failIds = true;
  expire();
  assert.equal((await client.watchlist()).length, 4);
  assert.equal(client.snapshot.activity, '2026-09-08T10:00:00Z');
  state.failIds = false;
  expire();
  assert.equal((await client.watchlist()).length, 3);
  assert.equal(client.snapshot.activity, '2026-09-08T11:00:00Z');
  assert.equal(state.requests.filter(p => p.includes('date_from=2026-09-08T10')).length, 2);
  assert.equal(state.requests.filter(p => p.includes('/all?')).length, 3, 'only one full bootstrap');
});

test('Simkl delta without seasons retains existing seasons in snapshot', async () => {
  const { client, state, expire } = libraryFixture();
  state.initial.shows = [{
    status: 'plantowatch',
    show: { title: 'Show 1', ids: { simkl: 10, tmdb: 10 } },
    seasons: [{ number: 1, episodes: [{ number: 1, watched: true }] }]
  }];
  await client.watchlist();
  assert.equal(client.snapshot.shows[0].seasons?.length, 1);

  state.activities = { all: '2026-09-08T11:00:00Z' };
  // Delta row omits seasons
  state.delta.shows = [{
    status: 'watching',
    show: { title: 'Show 1', ids: { simkl: 10, tmdb: 10 } }
  }];
  expire();
  await client.watchlist();
  assert.equal(client.snapshot.shows[0].status, 'watching');
  assert.equal(client.snapshot.shows[0].seasons?.length, 1, 'seasons must be retained when delta omits them');
});

test('Simkl explicit empty seasons clears watched episodes', async () => {
  const { client, state, expire } = libraryFixture();
  state.initial.shows = [{
    status: 'watching', show: { ids: { simkl: 10, tmdb: 10 } },
    seasons: [{ number: 1, episodes: [{ number: 1 }] }]
  }];
  await client.watchlist();
  state.activities = { all: '2026-09-08T11:00:00Z' };
  state.delta.shows = [{ status: 'plantowatch', show: { ids: { simkl: 10, tmdb: 10 } }, seasons: [] }];
  expire();
  await client.watchlist();
  assert.equal(client.snapshot.shows[0].seasons.length, 0);
});

test('Simkl snapshot persists across client instances for the same profile', async () => {
  const disk = storage();
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: {} }, './sync': {}, './storage': disk, './http': {}, './tmdb': {}
  });
  const client1 = new SimklClient();
  client1.setProfile('persisted-profile');
  client1.setToken({ access_token: 'token-1' });
  client1.simkl = async req => {
    if (req === '/sync/activities') return { all: '2026-09-08T10:00:00Z' };
    const type = req.split('/')[3].split('?')[0];
    return { [type]: [{ status: 'plantowatch', [type === 'movies' ? 'movie' : 'show']: { ids: { simkl: 99, tmdb: 99 } } }] };
  };
  await client1.watchlist();
  assert.ok(client1.snapshot);
  assert.equal(client1.snapshot.activity, '2026-09-08T10:00:00Z');

  // Second client on same profile and disk
  const client2 = new SimklClient();
  client2.setProfile('persisted-profile');
  client2.setToken({ access_token: 'token-1' });
  let bootstrapCalled = false;
  client2.simkl = async req => {
    if (req === '/sync/activities') return { all: '2026-09-08T10:00:00Z' };
    bootstrapCalled = true;
    return {};
  };
  const rows = await client2.watchlist();
  assert.equal(bootstrapCalled, false, 'bootstrap must not run when persisted snapshot is valid');
  assert.equal(rows.length, 3);
});

test('Simkl invalidateSnapshot persists checkedAt = 0 and recreated client loads invalidated timestamp', async () => {
  const disk = storage();
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: {} }, './sync': {}, './storage': disk, './http': {}, './tmdb': {}
  });
  const client1 = new SimklClient();
  client1.setProfile('invalidate-profile');
  client1.setToken({ access_token: 'token-inv' });
  client1.simkl = async req => {
    if (req === '/sync/activities') return { all: '2026-09-08T10:00:00Z' };
    const type = req.split('/')[3].split('?')[0];
    return { [type]: [{ status: 'plantowatch', [type === 'movies' ? 'movie' : 'show']: { ids: { simkl: 1, tmdb: 1 } } }] };
  };
  await client1.watchlist();
  assert.ok(client1.snapshot.checkedAt > 0);

  // Invalidate via action (e.g. addToWatchlist calls invalidateSnapshot)
  client1.simkl = async () => ({ result: 'OK' });
  await client1.addToWatchlist({ mediaType: 'movie', tmdbId: 2 });
  assert.equal(client1.snapshot.checkedAt, 0);

  // Recreated client on same disk
  const client2 = new SimklClient();
  client2.setProfile('invalidate-profile');
  client2.setToken({ access_token: 'token-inv' });
  let activitiesChecked = false;
  client2.simkl = async req => {
    if (req === '/sync/activities') {
      activitiesChecked = true;
      return { all: '2026-09-08T10:00:00Z' };
    }
    return {};
  };
  await client2.watchlist();
  assert.equal(activitiesChecked, true, 'recreated client must check activities because persisted snapshot had checkedAt = 0');
});

test('Simkl getSimklItemUrl favors numeric id over slug', () => {
  const { getSimklItemUrl } = load('lib/simkl.ts', {
    './config': { config: {} }, './sync': {}, './storage': storage(), './http': {}, './tmdb': {}
  });
  assert.equal(getSimklItemUrl({ simkl: 12345, slug: 'test-movie' }, 'movie'), 'https://simkl.com/movies/12345/test-movie');
  assert.equal(getSimklItemUrl({ simkl: 12345 }, 'tv'), 'https://simkl.com/tv/12345');
  assert.equal(getSimklItemUrl({ slug: 'test-anime' }, 'anime'), 'https://simkl.com/anime/test-anime');
  assert.equal(getSimklItemUrl(null), null);
});

function routerFixture() {
  const calls = [];
  const provider = name => ({
    isConnected: true, currentProfileId: 'p',
    addToHistory: async ref => calls.push([name, 'add', ref.episode]),
    removeFromHistory: async ref => calls.push([name, 'remove', ref.episode])
  });
  const trakt = provider('trakt'), simkl = provider('simkl'), mdb = provider('mdb');
  simkl.markSeasonWatched = async (_item, season, watched) => calls.push(['simkl', 'batch', season, watched]);
  mdb.isConnected = false;
  const router = load('lib/sync.ts', {
    './storage': storage(), './store': { traktClient: trakt }, './simkl': { simklClient: simkl },
    './mdblist': { mdblistClient: mdb }
  });
  return { router, calls, simkl, trakt };
}

for (const watched of [true, false]) {
  test(`season ${watched ? 'watch' : 'unwatch'} batches Simkl and still writes to Trakt`, async () => {
    const { router, calls } = routerFixture();
    await router.syncSeasonWatched({ mediaType: 'tv', tmdbId: 42 }, 2, [1, 2], watched);
    assert.equal(calls.filter(call => call[0] === 'simkl' && call[1] === 'batch').length, 1);
    assert.equal(calls.filter(call => call[0] === 'simkl').length, 1, 'no duplicate per-episode Simkl writes');
    assert.deepEqual(calls.filter(call => call[0] === 'trakt'), [
      ['trakt', watched ? 'add' : 'remove', 1], ['trakt', watched ? 'add' : 'remove', 2]
    ]);
  });
}

test('connected Simkl is not written when the user disables Simkl writes', async () => {
  const { router, calls } = routerFixture();
  router.saveTrackingPreferences('p', { ...router.defaultTrackingPreferences(), writeToSimkl: false });
  await router.syncSeasonWatched({ mediaType: 'tv', tmdbId: 42 }, 2, [1], true);
  assert.deepEqual(calls, [['trakt', 'add', 1]]);
});

test('season batch failures are returned to the caller rather than swallowed', async () => {
  const { router, simkl } = routerFixture();
  simkl.markSeasonWatched = async () => { throw new Error('HTTP 429'); };
  await assert.rejects(router.syncSeasonWatched({ mediaType: 'tv', tmdbId: 42 }, 2, [1], true), /Not all tracking services saved/);
});

function seasonAction(globals) {
  const filename = path.join(__dirname, '../components/details/DetailsDrawer.tsx');
  const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let initializer;
  function visit(node) {
    if (ts.isVariableDeclaration(node) && node.name.getText(source) === 'updateSeasonWatched') initializer = node.initializer.getText(source);
    ts.forEachChild(node, visit);
  }
  visit(source);
  assert.ok(initializer);
  const output = ts.transpileModule(`module.exports = ${initializer};`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(output, {
    module, item: { id: 42, mediaType: 'tv' },
    getSeasonEpisodes: async () => [{ episodeNumber: 1 }, { episodeNumber: 2 }],
    loadSeasonEpisodes: async () => [{ episodeNumber: 1 }, { episodeNumber: 2 }],
    priorityConfig: {}, metadataContext: {}, isWatched: () => false, ...globals
  });
  return module.exports;
}

test('failed season sync does not change local watched state or show a success toast', async () => {
  const calls = [], toasts = [];
  const action = seasonAction({
    syncSeasonWatched: async () => { throw new Error('HTTP 429'); },
    toggleWatched: async (...args) => calls.push(args), setToast: message => toasts.push(message)
  });
  await action(1, true);
  assert.equal(calls.length, 0);
  assert.equal(toasts.length, 1);
  assert.match(toasts[0], /Could not update/);
});

test('successful season sync precedes local updates, which do not repeat remote writes', async () => {
  const order = [];
  const action = seasonAction({
    syncSeasonWatched: async () => order.push('remote'),
    toggleWatched: async (_item, _season, episode, skipSync) => { assert.equal(skipSync, true); order.push(episode); },
    setToast: message => order.push(message)
  });
  await action(1, true);
  assert.deepEqual(order, ['remote', 1, 2, 'Season 1 marked as watched.']);
});

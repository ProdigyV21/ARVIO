const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load, storage } = require('./load.cjs');

const cw = load('lib/continueWatching.ts');
const flush = () => new Promise(setImmediate);
const before = Date.parse('2026-09-08T10:00:00Z');
const after = '2026-09-08T11:00:00Z';
const movie = { id: 1, title: 'Movie', mediaType: 'movie', progress: 40, activityAt: before };
const episode = { id: 2, title: 'Series', mediaType: 'tv', seasonNumber: 1, episodeNumber: 2, progress: 40, activityAt: before };
const watchedMovie = { movie: { ids: { tmdb: 1 } }, last_watched_at: after };
const watchedShow = { show: { ids: { tmdb: 2, trakt: 20 } }, last_watched_at: after,
  seasons: [{ number: 1, episodes: [{ number: 2, last_watched_at: after }] }] };

function deferred() {
  let resolve, reject;
  const promise = new Promise((yes, no) => { resolve = yes; reject = no; });
  return { promise, resolve, reject };
}

// Execute the production store callback; rendering and external services are fixtures.
function extracted(name, globals) {
  const filename = path.resolve(__dirname, '../lib/store.tsx');
  const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let match;
  const visit = (node) => {
    if (ts.isFunctionDeclaration(node) && node.name?.text === name) match = node;
    if (ts.isVariableDeclaration(node) && node.name.getText(source) === name && ts.isCallExpression(node.initializer)) match = node.initializer.arguments[0];
    ts.forEachChild(node, visit);
  };
  visit(source);
  assert.ok(match, name);
  const code = ts.transpileModule(`module.exports = (${match.getText(source)});`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, { module, console, ...globals }, { filename });
  return module.exports;
}

function harness(options = {}) {
  const initial = options.initial ?? [movie, { ...episode, badge: 'Up Next' }];
  const cache = new Map([['cw:a', initial]]);
  const state = { cw: initial, categories: [{ id: 'continue_watching', items: initial }], errors: [], progressCalls: 0, busy: [], ready: [] };
  const update = (field) => (value) => { state[field] = typeof value === 'function' ? value(state[field]) : value; };
  const calls = [];
  const client = { isConnected: true, watchlist: async () => [], playback: async () => options.playback ?? [movie, episode],
    watched: async (type, feature = 'watched') => {
      calls.push([type, feature]);
      if (options.watched) return options.watched(type, feature);
      return type === 'movies' ? [watchedMovie] : [watchedShow];
    } };
  const tracker = { setProfile() {}, isConnected: true, hiddenProgressShowIds: async () => new Set(), continueWatchingActivity: options.activity ?? (async () => null) };
  const noop = () => {};
  const globals = {
    ...cw, activeProfileId: 'a', activeProfileIdRef: { current: 'a' },
    authClient: { session: null }, refreshKeyRef: { current: null }, refreshGenerationRef: { current: 0 },
    refreshInFlightRef: { current: null }, cwSourceRef: { current: initial.length ? 'seed' : 'none' },
    traktActivityRef: { current: null }, continueWatchingActivitySignature: load('lib/traktActivity.ts').continueWatchingActivitySignature,
    settingsRef: { current: { disabledAddonIds: [], catalogs: [], hiddenCatalogIds: [] } },
    addonsRef: { current: [] }, lastSyncedSettingsRef: { current: null },
    traktClient: tracker, mdblistClient: tracker, simklClient: tracker,
    loadTrackingPreferences: () => ({}), setTrackingPreferences: noop,
    setTraktConnected: noop, setMdblistConnected: noop, setSimklConnected: noop,
    setAddonsReady: value => state.ready.push(value), setBusy: value => state.busy.push(value), setAddons: noop, setCatalogConfigs: noop,
    setContinueWatching: update('cw'), setCategories: update('categories'), setWatchlist: noop, setWatchedKeys: noop,
    setToast: (error) => state.errors.push(error), setSettingsSyncState: noop,
    cwCacheKeyFor: (profile) => `cw:${profile}`, watchlistCacheKeyFor: (profile) => `watchlist:${profile}`,
    readCachedList: (key) => cache.get(key) ?? [], saveCachedList: (key, items) => cache.set(key, items), removeStored: (key) => cache.delete(key),
    loadLocalAddons: () => [], normalizeAddons: (items) => items, saveLocalAddons: noop,
    flushSettingsOutbox: async () => {}, hasPendingSettings: () => false,
    mergeCatalogs: (items) => items, syncClient: () => client,
    readsFrom: (feature, provider) => provider === 'trakt', sameTrackingSources: () => options.sameSources !== false,
    historyToItem: (item) => item, traktPlaybackToMedia: (item) => item, traktItemToMedia: (item) => item,
    hydrateTraktItems: async (items) => items, hydrateContinueWatchingItems: async (items) => items,
    dedupeMedia: (items) => items,
    loadTraktUpNext: async () => { state.progressCalls++; return options.progress ? options.progress() : { items: [], fetchFailures: 0 }; },
  };
  globals.traktWatchedKeys = extracted('traktWatchedKeys', {});
  globals.isPausedPlaybackItem = extracted('isPausedPlaybackItem', {});
  globals.filterWatchedContinueWatching = extracted('filterWatchedContinueWatching', { ...cw, isLiveStreamOrSportsItem: () => false });
  globals.mergeTraktWithLocalResume = extracted('mergeTraktWithLocalResume', {});
  return { state, cache, calls, globals, refresh: extracted('refreshData', globals) };
}

test('TV completion removes saved movie and episode from state, home rail and disk before slow progress resolves', async () => {
  const progress = deferred();
  const h = harness({ progress: () => progress.promise });
  const running = h.refresh();
  await flush();
  assert.equal(h.state.progressCalls, 1);
  assert.equal(h.state.cw.length, 0);
  assert.equal(h.state.categories.length, 0);
  assert.equal(h.cache.has('cw:a'), false);
  progress.resolve({ items: [], fetchFailures: 0 });
  await running;
  assert.equal(h.state.cw.length, 0);
  assert.deepEqual(h.state.errors, []);
  assert.equal(h.calls.length, 2, 'same sources share history requests');
});

test('Continue Watching checks its own movie history, not the separately selected badge provider', async () => {
  const h = harness({ sameSources: false, watched: async (type, feature) =>
    feature === 'continueWatching' && type === 'movies' ? [watchedMovie] : [] });
  await h.refresh();
  assert.ok(h.calls.some(([type, feature]) => type === 'movies' && feature === 'continueWatching'));
  assert.equal(h.state.cw.some(item => item.mediaType === 'movie'), false);
  assert.deepEqual(h.state.errors, []);
});

test('background refresh reconciles completed items without resetting catalog loading state', async () => {
  const h = harness();
  await h.refresh(undefined, true);
  assert.equal(h.state.cw.length, 0);
  assert.deepEqual(h.state.busy, []);
  assert.equal(h.state.ready.includes(false), false);
  assert.deepEqual(h.state.errors, []);
});

test('history is read after the activity baseline, so a mid-refresh TV watch cannot be silently acknowledged', async () => {
  const activity = deferred();
  const h = harness({ activity: () => activity.promise });
  const running = h.refresh();
  await flush();
  assert.equal(h.calls.length, 0);
  activity.resolve({ episodes: { watched_at: after } });
  await running;
  assert.equal(h.calls.length, 2);
  assert.equal(h.globals.traktActivityRef.current.key, 'local:a');
  assert.equal(h.globals.traktActivityRef.current.signature, h.globals.continueWatchingActivitySignature({ episodes: { watched_at: after } }));
});

test('partial history failure still prunes confirmed movies but keeps unconfirmed series', async () => {
  const h = harness({ watched: async (type) => {
    if (type === 'shows') throw Error('Trakt unavailable');
    return [watchedMovie];
  } });
  await h.refresh();
  assert.equal(h.state.cw.length, 1);
  assert.equal(h.state.cw[0].id, 2);
  assert.equal(h.cache.get('cw:a').length, 1);
});

test('fresh reset Up Next remains authoritative even when old history lists that episode', async () => {
  const next = { ...episode, badge: 'Up Next', progressResetAt: Date.parse(after) + 60000 };
  const h = harness({ progress: async () => ({ items: [next], fetchFailures: 0 }) });
  await h.refresh();
  assert.equal(h.state.cw.length, 1);
  assert.equal(h.state.cw[0].badge, 'Up Next');
  assert.equal(h.state.cw[0].episodeNumber, 2);
});

test('later show activity cannot resurrect an already completed Up Next episode in state or cache', async () => {
  const stale = { ...episode, badge: 'Up Next', activityAt: Date.parse(after) + 86400000 };
  const h = harness({ initial: [stale], playback: [], progress: async () => ({ items: [stale], fetchFailures: 0 }) });
  await h.refresh();
  assert.equal(h.state.cw.length, 0);
  assert.equal(h.state.categories.some(row => row.id === 'continue_watching'), false);
  assert.equal(h.cache.has('cw:a'), false);
  assert.deepEqual(h.state.errors, []);
});

test('watching an episode again after a progress reset removes its old Up Next entry', () => {
  const staleReset = { ...episode, badge: 'Up Next', progressResetAt: before,
    activityAt: Date.parse(after) + 86400000 };
  assert.equal(cw.pruneCompletedResume([staleReset], cw.completionTimes([], [watchedShow])).length, 0);
});

test('stale profile read cannot prune the newly selected profile', async () => {
  const history = deferred();
  const h = harness({ watched: () => history.promise });
  const running = h.refresh();
  await flush();
  h.globals.activeProfileIdRef.current = 'b';
  history.resolve([watchedMovie]);
  await running;
  assert.equal(h.state.cw.length, 2);
  assert.equal(h.cache.get('cw:a').length, 2);
  assert.equal(h.state.progressCalls, 0);
});

test('new movie rewatch survives the full refresh after an older completed watch', async () => {
  const replay = { ...movie, activityAt: Date.parse(after) + 60000 };
  const h = harness({ initial: [replay], playback: [replay] });
  await h.refresh();
  assert.equal(h.state.cw.length, 1);
  assert.equal(h.state.cw[0].activityAt, replay.activityAt);
  assert.deepEqual(h.state.errors, []);
});

test('failed progress for one show preserves its cache while successfully fetched next episodes still advance', async () => {
  const unrelated = { ...episode, id: 99, badge: 'Up Next' };
  const next = { ...episode, episodeNumber: 3, badge: 'Up Next', activityAt: Date.parse(after) };
  const h = harness({ initial: [movie, episode, unrelated], progress: async () => ({ items: [next], fetchFailures: 1 }) });
  await h.refresh();
  assert.equal(h.state.cw.length, 2);
  assert.equal(h.state.cw.find(item => item.id === 2).episodeNumber, 3);
  assert.ok(h.state.cw.some(item => item.id === 99));
  assert.equal(h.cache.get('cw:a').length, 2);
  assert.deepEqual(h.state.errors, []);
});

test('cached replay newer than completion survives; exact old episode is removed without deleting its show', () => {
  const times = cw.completionTimes([watchedMovie], [watchedShow]);
  const replay = { ...movie, activityAt: Date.parse(after) + 60000 };
  const next = { ...episode, episodeNumber: 3 };
  const result = cw.pruneCompletedResume([replay, episode, next], times);
  assert.deepEqual(Array.from(result), [replay, next]);
  const local = [{ ...movie, id: 999, streamAddonId: 'iptv_xtream_vod' }];
  assert.equal(cw.pruneCompletedResume(local, times), local);
});

test('older Simkl Up Next in the combined playback feed cannot override a newer Trakt completion', () => {
  const staleSimkl = { ...episode, badge: 'Up Next' };
  const next = { ...episode, episodeNumber: 3, badge: 'Up Next', activityAt: Date.parse(after) };
  const result = cw.mergeTrackerContinueWatching([staleSimkl], [next], new Set(['tv:2:1:2']), cw.completionTimes([], [watchedShow]));
  assert.equal(result.length, 1);
  assert.equal(result[0].episodeNumber, 3);
});

test('unknown completion time cannot erase a cached reset Up Next', () => {
  const next = [{ ...episode, badge: 'Up Next' }];
  assert.equal(cw.pruneCompletedResume(next, new Map([['tv:2:1:2', 0]])), next);
});

test('corrected/backdated watches and resets invalidate progress even when last watched timestamp is unchanged', () => {
  const initial = { last_watched_at: after, last_updated_at: after };
  assert.notEqual(cw.traktProgressActivityKey(initial), cw.traktProgressActivityKey({ ...initial, last_updated_at: '2026-09-08T12:00:00Z' }));
  assert.notEqual(cw.traktProgressActivityKey(initial), cw.traktProgressActivityKey({ ...initial, reset_at: after }));
});

test('actual tracking router uses Continue Watching preferences independently and fails closed on partial reads', async () => {
  const saved = storage();
  const tracker = (name) => ({ isConnected: true, currentProfileId: 'a', watched: async (type) => [{ name, type }] });
  const trakt = tracker('trakt'), simkl = tracker('simkl'), mdb = tracker('mdblist');
  const sync = load('lib/sync.ts', { './storage': saved, './store': { traktClient: trakt }, './simkl': { simklClient: simkl }, './mdblist': { mdblistClient: mdb } });
  const prefs = { watchlistReadMode: 'simkl', watchedReadMode: 'mdblist', continueWatchingReadMode: 'trakt', writeToTrakt: false, writeToSimkl: false };
  sync.saveTrackingPreferences('a', prefs);
  assert.equal((await sync.syncClient().watched('movies'))[0].name, 'mdblist');
  assert.equal((await sync.syncClient().watched('movies', 'continueWatching'))[0].name, 'trakt');
  assert.equal(sync.sameTrackingSources('watched', 'continueWatching'), false);
  sync.saveTrackingPreferences('a', { ...prefs, watchedReadMode: 'trakt' });
  assert.equal(sync.sameTrackingSources('watched', 'continueWatching'), true);
  sync.saveTrackingPreferences('a', { ...prefs, continueWatchingReadMode: 'both' });
  simkl.watched = async () => { throw Error('offline'); };
  await assert.rejects(sync.syncClient().watched('movies', 'continueWatching'), /could not be read/);
});

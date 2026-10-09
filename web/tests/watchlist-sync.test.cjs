const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const authFor = userId => ({ session: { userId, accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' });
const auth = authFor('account');
const movie = id => ({ id, mediaType: 'movie', title: `Movie ${id}`, image: '', activityAt: 100 });
const cloudItem = id => ({ tmdbId: id, mediaType: 'movie', title: `Movie ${id}`, addedAt: 100 });
const plain = value => JSON.parse(JSON.stringify(value));
const changes = load('lib/watchlistChanges.ts');

function cloudHarness(initial, disk = storage()) {
  let root = structuredClone(initial);
  const sent = [];
  const cloud = load('lib/cloud.ts', {
    './storage': disk,
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': { tmdbImageUrl: (_base, path) => path ?? '' },
    './http': { jsonRequest: async (_url, request) => {
      if (request?.method === 'POST') { root = JSON.parse(request.body).payload; sent.push(root); return { accepted: true }; }
      return { payload: root };
    } }
  });
  return { cloud, disk, sent };
}

test('cloud removal survives a stale snapshot with a freshly regenerated addedAt', async () => {
  const h = cloudHarness({ watchlistByProfile: { p: [cloudItem(1), cloudItem(2)] },
    watchlistChangesByProfile: { p: { 'movie:1': { updatedAt: 200, removed: true } } } });
  await h.cloud.saveCloudWatchlist(auth, [{ ...movie(1), activityAt: 900 }], 'p');
  assert.deepEqual(h.sent[0].watchlistByProfile.p.map(item => item.tmdbId), [2]);
  assert.equal(h.sent[0].watchlistChangesByProfile.p['movie:1'].removed, true);
});

test('explicit one-item removal preserves remote additions and another profile', async () => {
  const h = cloudHarness({ watchlistByProfile: { p: [cloudItem(1), cloudItem(2)], other: [cloudItem(1)] } });
  await h.cloud.saveCloudWatchlist(auth, [], 'p', { changes: { 'movie:1': { updatedAt: 200, removed: true } } });
  assert.deepEqual(h.sent[0].watchlistByProfile.p.map(item => item.tmdbId), [2]);
  assert.deepEqual(h.sent[0].watchlistByProfile.other.map(item => item.tmdbId), [1]);
  assert.equal(h.sent[0].watchlistChangesByProfile.other, undefined);
});

test('removing the last item sends an empty list plus a retained deletion record', async () => {
  const h = cloudHarness({ watchlistByProfile: { p: [cloudItem(1)] } });
  await h.cloud.saveCloudWatchlist(auth, [], 'p', { changes: { 'movie:1': { updatedAt: 200, removed: true } } });
  assert.deepEqual(h.sent[0].watchlistByProfile.p, []);
  assert.equal(h.sent[0].watchlistChangesByProfile.p['movie:1'].updatedAt, 200);
});

test('newer explicit add restores an item and replayed old removal cannot remove it', async () => {
  const h = cloudHarness({ watchlistByProfile: { p: [] },
    watchlistChangesByProfile: { p: { 'movie:1': { updatedAt: 200, removed: true } } } });
  await h.cloud.saveCloudWatchlist(auth, [{ ...movie(1), activityAt: 300 }], 'p',
    { changes: { 'movie:1': { updatedAt: 300, removed: false } } });
  await h.cloud.saveCloudWatchlist(auth, [], 'p', { changes: { 'movie:1': { updatedAt: 200, removed: true } } });
  assert.deepEqual(h.sent.at(-1).watchlistByProfile.p.map(item => item.tmdbId), [1]);
  assert.deepEqual(h.sent.at(-1).watchlistChangesByProfile.p['movie:1'], { updatedAt: 300, removed: false });
});

test('pull applies profile tombstones, preserves activityAt, and accepts nested legacy JSON', async () => {
  const h = cloudHarness({ watchlistByProfile: { p: JSON.stringify([cloudItem(1), cloudItem(2)]), other: [cloudItem(1)] },
    watchlistChangesByProfile: { p: JSON.stringify({ 'movie:1': { updatedAt: 200, removed: true } }) } });
  assert.deepEqual(plain(await h.cloud.pullCloudWatchlist(auth, 'p')).map(item => [item.id, item.activityAt]), [[2, 100]]);
  assert.deepEqual(plain(await h.cloud.pullCloudWatchlist(auth, 'other')).map(item => item.id), [1]);
});

test('passive stale writes retain remote metadata and never stamp an addition', async () => {
  const h = cloudHarness({ watchlistByProfile: { p: [{ ...cloudItem(1), title: 'Remote', addedAt: 300 }] } });
  await h.cloud.saveCloudWatchlist(auth, [{ ...movie(1), activityAt: undefined }], 'p');
  assert.equal(h.sent[0].watchlistByProfile.p[0].addedAt, 300);
  assert.equal(h.sent[0].watchlistByProfile.p[0].title, 'Remote');
  assert.deepEqual(h.sent[0].watchlistChangesByProfile.p, {});
});

test('remove wins equal-time add and invalid event keys/stamps are ignored', () => {
  const add = { 'movie:1': { updatedAt: 100, removed: false } };
  const remove = { 'movie:1': { updatedAt: 100, removed: true } };
  assert.equal(changes.mergeWatchlistChanges(add, remove)['movie:1'].removed, true);
  assert.equal(changes.mergeWatchlistChanges(remove, add)['movie:1'].removed, true);
  assert.deepEqual(plain(changes.mergeWatchlistChanges({ 'movie:0': { updatedAt: 100, removed: true },
    'movie:1': { updatedAt: -1, removed: true }, 'tv:2': { updatedAt: 100, removed: 'true' } })), {});
});

function outboxHarness(disk = storage(), save = async () => {}) {
  const api = load('lib/watchlistOutbox.ts', { './storage': disk, './cloud': { saveCloudWatchlist: save } });
  return { api, disk };
}

test('failed removal survives reload, filters stale tracker data, and retries the same timestamp', async () => {
  const disk = storage();
  const sent = [];
  const first = outboxHarness(disk, async (...args) => { sent.push(plain(args[3])); throw Error('offline'); });
  first.api.queueWatchlistChange(auth, movie(1), true, 'p');
  await assert.rejects(first.api.flushWatchlistOutbox(auth), /offline/);
  const second = outboxHarness(disk, async (...args) => { sent.push(plain(args[3])); });
  assert.deepEqual(plain(second.api.applyPendingWatchlist(auth, 'p', [movie(1), movie(2)])).map(item => item.id), [2]);
  await second.api.flushWatchlistOutbox(auth);
  assert.deepEqual(sent[1], sent[0]);
  assert.deepEqual(disk.loadStored('arvio.web.watchlistOutbox.v1:account', []), []);
  assert.deepEqual(plain(second.api.applyPendingWatchlist(auth, 'p', [movie(1)])), []);
});

test('explicit re-add advances beyond a known future removal and pending remove', async () => {
  const { api, disk } = outboxHarness();
  const future = Date.now() + 60_000;
  disk.saveStored(changes.watchlistChangesStorageKey('account', 'p'), { 'movie:1': { updatedAt: future, removed: true } });
  api.queueWatchlistChange(auth, movie(1), true, 'p');
  api.queueWatchlistChange(auth, movie(1), false, 'p');
  const edit = disk.loadStored('arvio.web.watchlistOutbox.v1:account', [])[0];
  assert.deepEqual(edit.changes['movie:1'], { updatedAt: future + 2, removed: false });
  assert.deepEqual(plain(api.applyPendingWatchlist(auth, 'p', [])).map(item => item.id), [1]);
});

test('queued changes are isolated by account, profile, and movie/TV identity', async () => {
  const { api } = outboxHarness();
  api.queueWatchlistChange(auth, movie(1), true, 'p');
  assert.deepEqual(plain(api.applyPendingWatchlist(authFor('another'), 'p', [movie(1)])).map(item => item.id), [1]);
  assert.deepEqual(plain(api.applyPendingWatchlist(auth, 'other', [movie(1)])).map(item => item.id), [1]);
  assert.deepEqual(plain(api.applyPendingWatchlist(auth, 'p', [{ ...movie(1), mediaType: 'tv' }])).map(item => item.mediaType), ['tv']);
  assert.deepEqual(plain(api.applyPendingWatchlist(auth, 'p', [movie(-99)])).map(item => item.id), [-99]);
});

test('a second action queued during upload is not acknowledged with the first', async () => {
  let release;
  const firstUpload = new Promise(resolve => { release = resolve; });
  const sent = [];
  const { api } = outboxHarness(storage(), async (...args) => {
    sent.push(plain(args[3]));
    if (sent.length === 1) await firstUpload;
  });
  api.queueWatchlistChange(auth, movie(1), true, 'p');
  const uploading = api.flushWatchlistOutbox(auth);
  api.queueWatchlistChange(auth, movie(1), false, 'p');
  release();
  await uploading;
  assert.equal(sent.length, 2);
  assert.equal(sent[0].changes['movie:1'].removed, true);
  assert.equal(sent[1].changes['movie:1'].removed, false);
  assert.ok(sent[1].changes['movie:1'].updatedAt > sent[0].changes['movie:1'].updatedAt);
});

test('late cloud response cannot copy the previous account deletion records to a new account', async () => {
  let release;
  const response = new Promise(resolve => { release = resolve; });
  const disk = storage();
  const switchedAuth = authFor('first');
  const cloud = load('lib/cloud.ts', { './storage': disk,
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': {}, './http': { jsonRequest: () => response } });
  const pulling = cloud.pullCloudWatchlist(switchedAuth, 'p');
  switchedAuth.session = { userId: 'second', accessToken: 'test' };
  release({ payload: { watchlistChangesByProfile: { p: { 'movie:1': { updatedAt: 200, removed: true } } } } });
  assert.deepEqual(plain(await pulling), []);
  assert.equal(disk.loadStored(changes.watchlistChangesStorageKey('second', 'p'), null), null);
});

function extractToggle(globals) {
  const fs = require('node:fs');
  const path = require('node:path');
  const vm = require('node:vm');
  const ts = require('typescript');
  const filename = path.resolve(__dirname, '../lib/store.tsx');
  const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let callback;
  const visit = node => {
    if (ts.isVariableDeclaration(node) && node.name.getText(source) === 'toggleWatchlist') callback = node.initializer.arguments[0];
    ts.forEachChild(node, visit);
  };
  visit(source);
  assert.ok(callback);
  const module = { exports: {} };
  const code = ts.transpileModule(`module.exports = (${callback.getText(source)});`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS }
  }).outputText;
  vm.runInNewContext(code, Object.assign(globals, { module }), { filename });
  return module.exports;
}

test('watchlist toggle queues only its explicit item and retains the change after cloud failure', async () => {
  const queued = [];
  const toasts = [];
  let displayed = [movie(1), movie(2)];
  const toggle = extractToggle({ watchlist: displayed, authClient: auth, activeProfileId: 'p',
    activeProfileIdRef: { current: 'p' }, watchlistMutationRef: { current: new Map() },
    activeSyncProvider: () => 'trakt', slimCacheItem: item => item, watchlistCacheKeyFor: () => 'cache',
    setWatchlist: value => { displayed = typeof value === 'function' ? value(displayed) : value; }, saveCachedList: () => {},
    setToast: text => toasts.push(text), syncClient: () => ({ removeFromWatchlist: async () => {} }),
    prepareWatchlistChange: () => ({ updatedAt: 100, removed: true }),
    queueWatchlistChange: (...args) => queued.push(args), flushWatchlistOutbox: async () => { throw Error('offline'); }
  });
  await toggle(movie(1));
  assert.deepEqual(displayed.map(item => item.id), [2]);
  assert.equal(queued.length, 1);
  assert.equal(queued[0][1].id, 1);
  assert.equal(queued[0][2], true);
  assert.equal(queued[0][3], 'p');
  assert.match(toasts.at(-1), /retry/);
});

test('delayed tracker completion retains click order for both add/remove sequences', () => {
  for (const firstRemoved of [false, true]) {
    const { api, disk } = outboxHarness();
    const first = api.prepareWatchlistChange(auth, movie(1), firstRemoved, 'p');
    const second = api.prepareWatchlistChange(auth, movie(1), !firstRemoved, 'p');
    api.queueWatchlistChange(auth, movie(1), !firstRemoved, 'p', second);
    api.queueWatchlistChange(auth, movie(1), firstRemoved, 'p', first);
    const edit = disk.loadStored('arvio.web.watchlistOutbox.v1:account', [])[0];
    assert.equal(edit.changes['movie:1'].removed, !firstRemoved);
    assert.equal(edit.changes['movie:1'].updatedAt, second.updatedAt);
    assert.equal(edit.items.length, firstRemoved ? 1 : 0);
  }
});

test('a stale tracker failure cannot undo a later toggle or change a newly selected profile/account UI', async () => {
  for (const scenario of ['newer-action', 'profile-switch', 'account-switch']) {
    let rejectTracker;
    const tracker = new Promise((_resolve, reject) => { rejectTracker = reject; });
    const localAuth = authFor('account');
    const globals = { watchlist: [], authClient: localAuth, activeProfileId: 'p',
      activeProfileIdRef: { current: 'p' }, watchlistMutationRef: { current: new Map() },
      activeSyncProvider: () => 'trakt', slimCacheItem: item => item, watchlistCacheKeyFor: () => 'cache',
      saveCachedList: () => {}, setToast: () => {}, prepareWatchlistChange: () => undefined,
      queueWatchlistChange: () => {}, flushWatchlistOutbox: async () => {},
      syncClient: () => ({ addToWatchlist: () => tracker, removeFromWatchlist: async () => {} }) };
    let displayed = [];
    globals.setWatchlist = value => { displayed = typeof value === 'function' ? value(displayed) : value; };
    const toggle = extractToggle(globals);
    const first = toggle(movie(1));
    if (scenario === 'newer-action') {
      globals.watchlist = displayed;
      await toggle(movie(1));
    } else {
      displayed = [movie(99)];
      if (scenario === 'profile-switch') globals.activeProfileIdRef.current = 'other';
      else localAuth.session = authFor('other').session;
    }
    rejectTracker(Error('late tracker failure'));
    await first;
    assert.deepEqual(plain(displayed).map(item => item.id), scenario === 'newer-action' ? [] : [99], scenario);
  }
});

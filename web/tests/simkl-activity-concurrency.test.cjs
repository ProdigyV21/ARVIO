const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

function fixture() {
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: {} }, './sync': {}, './storage': storage(), './http': {}, './tmdb': {}
  });
  const client = new SimklClient();
  client.setProfile('activity-test');
  client.setToken({ access_token: 'activity-test-token' });
  const requests = [];
  let release;
  let entered;
  const started = new Promise(resolve => { entered = resolve; });
  const pending = new Promise(resolve => { release = resolve; });
  let wait = true;
  let fail = false;
  client.simkl = async request => {
    requests.push(request);
    if (request === '/sync/activities') {
      entered();
      if (wait) await pending;
      if (fail) throw new Error('activities offline');
      return { all: '2026-10-16T10:00:00Z' };
    }
    return {};
  };
  return { client, requests, started, release: () => { wait = false; release(); }, fail: () => { fail = true; } };
}

test('a watchlist write does not detach the shared activity request or lose its invalidation', async () => {
  const h = fixture();
  const first = h.client.watchlist();
  await h.started;
  await h.client.addToWatchlist({ mediaType: 'movie', tmdbId: 101 });
  const readers = [h.client.watchlist(), h.client.watched('movies'), h.client.watchlist(['watching'])];
  await Promise.resolve();
  assert.equal(h.requests.filter(p => p === '/sync/activities').length, 1);
  h.release();
  await Promise.all([first, ...readers]);
  assert.equal(h.client.snapshot.checkedAt, 0, 'the older in-flight snapshot cannot acknowledge a newer write');
  await h.client.watchlist();
  assert.equal(h.requests.filter(p => p === '/sync/activities').length, 2, 'one later reconciliation, not one request per queued reader');
});

test('writing while activities are offline does not reset the read failure cooldown', async () => {
  const h = fixture(); h.fail(); h.release();
  await assert.rejects(h.client.watchlist(['plantowatch'], { throwOnError: true }), /activities offline/);
  await h.client.addToWatchlist({ mediaType: 'movie', tmdbId: 101 });
  for (let i = 0; i < 10; i++) await h.client.watchlist();
  assert.equal(h.requests.filter(p => p === '/sync/activities').length, 1);
});

test('scrobble stop preserves an in-flight activity request and expires its result', async () => {
  const h = fixture();
  const first = h.client.watchlist();
  await h.started;
  await h.client.scrobble('stop', { mediaType: 'movie', tmdbId: 101, progress: 45 });
  const second = h.client.watchlist();
  h.release();
  await Promise.all([first, second]);
  assert.equal(h.requests.filter(p => p === '/sync/activities').length, 1);
  assert.equal(h.requests.filter(p => p.startsWith('/scrobble/stop')).length, 1);
  assert.equal(h.client.snapshot.checkedAt, 0);
});

test('account changes never join the previous accounts in-flight activity request', async () => {
  const h = fixture();
  const old = h.client.watchlist();
  await h.started;
  h.client.setToken({ access_token: 'other-activity-token' });
  const next = h.client.watchlist();
  h.release();
  await Promise.all([old, next]);
  assert.equal(h.requests.filter(p => p === '/sync/activities').length, 2);
  assert.ok(h.client.snapshot.scope.endsWith(':other-activity-token'));
});

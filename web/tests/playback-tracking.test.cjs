const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const tick = () => new Promise(setImmediate);
const episode = (number = 3) => ({ mediaType: 'tv', tmdbId: 42, season: 2, episode: number, progress: 25 });

function simklHarness() {
  let now = 1_000_000, timerId = 0;
  const timers = new Map(), calls = [];
  class Clock extends Date { static now() { return now; } }
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: {} }, './storage': storage(), './sync': {}, './http': {}, './tmdb': {}
  }, {
    Date: Clock,
    setTimeout: (fn, ms) => { const id = ++timerId; timers.set(id, { fn, at: now + ms }); return id; },
    clearTimeout: id => timers.delete(id)
  });
  const client = new SimklClient();
  client.setProfile('profile-a'); client.setToken({ access_token: 'test-a' });
  client.simkl = async (path, options) => {
    calls.push({ path, body: JSON.parse(options.body), at: now, keepalive: options.keepalive, token: client.token.access_token });
  };
  async function advance(ms = 20_500) {
    now += ms;
    for (const [id, timer] of [...timers]) if (timer.at <= now) { timers.delete(id); timer.fn(); }
    await tick();
  }
  return { client, calls, timers, advance };
}

test('Simkl completion survives immediate next-episode start, with actual delivery acknowledgements', async () => {
  const h = simklHarness();
  await h.client.scrobble('start', episode()); await tick();
  let completed = false;
  const stop = h.client.scrobble('stop', { ...episode(), progress: 100 }).then(() => { completed = true; });
  const startNext = h.client.scrobble('start', episode(4));
  await tick();
  assert.equal(completed, false, 'queued does not mean accepted by Simkl');
  assert.equal(h.calls.length, 1);
  await h.advance(); await stop;
  assert.equal(h.calls[1].path, '/scrobble/stop');
  assert.equal(h.calls[1].body.episode.number, 3);
  assert.equal(h.calls[1].body.progress, 100);
  await h.advance(); await startNext;
  assert.equal(h.calls[2].path, '/scrobble/start');
  assert.equal(h.calls[2].body.episode.number, 4);
  assert.equal(h.calls[2].at - h.calls[1].at, 20_500);
  assert.equal(h.timers.size, 0);
});

test('rapid play/pause events coalesce but keep the final resume position', async () => {
  const h = simklHarness();
  await h.client.scrobble('start', episode()); await tick();
  const pending = [
    h.client.scrobble('pause', { ...episode(), progress: 30 }),
    h.client.scrobble('start', { ...episode(), progress: 30 }),
    h.client.scrobble('pause', { ...episode(), progress: 43 })
  ];
  await h.advance(); await Promise.all(pending);
  assert.equal(h.calls.length, 2);
  assert.equal(h.calls[1].path, '/scrobble/pause');
  assert.equal(h.calls[1].body.progress, 43);
  assert.equal(h.calls[1].keepalive, true);
});

test('Simkl anime scrobbles retain season and episode numbering, including specials', async () => {
  const h = simklHarness();
  await h.client.scrobble('start', { ...episode(), season: 0, isAnime: true });
  assert.deepEqual(h.calls[0].body.show, { ids: { tmdb: 42 }, use_tvdb_anime_seasons: true });
  assert.deepEqual(h.calls[0].body.episode, { season: 0, number: 3 });
});

test('Simkl completed writes expire freshness without dropping cached library metadata', async () => {
  const h = simklHarness();
  h.client.snapshot = { scope: 'profile-a:test-a', checkedAt: 123, activity: 'saved-marker', movies: [{ id: 42 }] };
  await h.client.scrobble('stop', { mediaType: 'movie', tmdbId: 42, progress: 100 });
  assert.equal(h.client.snapshot.checkedAt, 0);
  assert.equal(h.client.snapshot.activity, 'saved-marker');
  assert.deepEqual(h.client.snapshot.movies, [{ id: 42 }]);
});

test('Simkl queued failures reach the caller; they are not silently acknowledged', async () => {
  const h = simklHarness();
  await h.client.scrobble('start', episode()); await tick();
  h.client.simkl = async () => { throw Object.assign(new Error('HTTP 503'), { status: 503 }); };
  const failure = assert.rejects(h.client.scrobble('stop', { ...episode(), progress: 100 }), /HTTP 503/);
  await h.advance(); await failure;
  assert.equal(h.timers.size, 0, 'no background retry loop');
});

test('Simkl already-completed response is not retried as a new watched event', async () => {
  const h = simklHarness();
  h.client.simkl = async () => { throw Object.assign(new Error('Already scrobbled'), { status: 409 }); };
  await h.client.scrobble('stop', { ...episode(), progress: 100 });
  await tick();
  assert.equal(h.timers.size, 0);
});

test('changing tracking profile cancels queued events instead of writing them to the new account', async () => {
  const h = simklHarness();
  await h.client.scrobble('start', episode()); await tick();
  const cancelled = assert.rejects(h.client.scrobble('stop', { ...episode(), progress: 100 }), /account changed/);
  h.client.setProfile('profile-b'); h.client.setToken({ access_token: 'test-b' });
  await cancelled; await h.advance();
  assert.equal(h.calls.length, 1);
  await h.client.scrobble('start', episode(4));
  assert.equal(h.calls[1].token, 'test-b');
  assert.equal(h.calls[1].body.episode.number, 4);
});

test('slow Simkl requests never overlap even after the write lock expires', async () => {
  const h = simklHarness();
  let release;
  h.client.simkl = () => new Promise(resolve => { release = resolve; });
  const first = h.client.scrobble('start', episode());
  const next = h.client.scrobble('stop', { ...episode(), progress: 100 });
  await h.advance(60_000);
  assert.equal(h.client.pendingScrobbles.length, 1);
  h.client.simkl = async () => {};
  release(); await first; await next;
});

test('an old in-flight request cannot release a new profile queue', async () => {
  const h = simklHarness();
  let releaseA, releaseB;
  h.client.simkl = () => new Promise(resolve => { releaseA = resolve; });
  const a = h.client.scrobble('start', episode());
  h.client.setProfile('profile-b'); h.client.setToken({ access_token: 'test-b' });
  h.client.simkl = () => new Promise(resolve => { releaseB = resolve; });
  const b = h.client.scrobble('start', episode(4));
  const next = h.client.scrobble('stop', { ...episode(4), progress: 100 });
  releaseA(); await a; await h.advance(60_000);
  assert.equal(h.client.scrobbleInFlight, true);
  assert.equal(h.client.pendingScrobbles.length, 1);
  h.client.simkl = async () => {};
  releaseB(); await b; await next;
});

function routerHarness() {
  const calls = [];
  const client = name => ({ currentProfileId: 'profile-a', isConnected: true,
    scrobble: async (action, item) => { calls.push({ name, action, item }); } });
  const trakt = client('trakt'), simkl = client('simkl'), mdb = client('mdb');
  mdb.isConnected = false;
  const router = load('lib/sync.ts', {
    './storage': storage(), './store': { traktClient: trakt }, './simkl': { simklClient: simkl },
    './mdblist': { mdblistClient: mdb }
  });
  return { router, calls, trakt, simkl, mdb };
}

test('browser tracking writes to both services even when reads use only Trakt', async () => {
  const h = routerHarness();
  h.router.saveTrackingPreferences('profile-a', { ...h.router.defaultTrackingPreferences(), watchlistReadMode: 'trakt', continueWatchingReadMode: 'trakt' });
  await h.router.syncClient('profile-a').scrobble('stop', { ...episode(), progress: 100 });
  assert.deepEqual(h.calls.map(c => c.name), ['trakt', 'simkl']);
});

test('browser scrobbles respect disabled tracker writes and connected MDBList', async () => {
  const h = routerHarness();
  h.mdb.isConnected = true;
  h.router.saveTrackingPreferences('profile-a', { ...h.router.defaultTrackingPreferences(), writeToSimkl: false });
  await h.router.syncClient('profile-a').scrobble('pause', episode());
  assert.deepEqual(h.calls.map(c => c.name), ['trakt', 'mdb']);
});

test('profile-bound playback tracking cannot use credentials from a switched profile', async () => {
  const h = routerHarness();
  const tracker = h.router.syncClient('profile-a');
  h.trakt.currentProfileId = 'profile-b'; h.simkl.currentProfileId = 'profile-b';
  await tracker.scrobble('pause', episode());
  assert.equal(h.calls.length, 0);
});

test('unmatched IDs or a series without an episode never scrobble an unrelated title', async () => {
  const h = routerHarness();
  for (const ref of [ { ...episode(), tmdbId: -1 }, { mediaType: 'tv', tmdbId: 42, progress: 100 }, { ...episode(), episode: 0 } ]) {
    await h.router.syncClient('profile-a').scrobble('stop', ref);
  }
  assert.equal(h.calls.length, 0);
  await h.router.syncClient('profile-a').scrobble('start', { ...episode(), season: 0 });
  assert.equal(h.calls.length, 2, 'season zero specials are valid episodes');
});

test('Trakt token refresh cannot send an old playback into a newly selected profile', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: {} }, './storage': storage(), './http': {} });
  const client = new TraktClient();
  client.setProfile('profile-a');
  let release, calls = 0;
  client.refreshIfNeeded = () => new Promise(resolve => { release = resolve; });
  client.trakt = async () => { calls++; };
  const send = client.scrobble('stop', { ...episode(), progress: 100 });
  client.setProfile('profile-b'); release({ access_token: 'new-profile-token' }); await send;
  assert.equal(calls, 0);
});

test('Trakt payload includes episode IDs and can finish an in-flight small request on page exit', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: {} }, './storage': storage(), './http': {} });
  const client = new TraktClient();
  client.refreshIfNeeded = async () => ({ access_token: 'test-token' });
  let sent;
  client.trakt = async (path, init) => { sent = { path, ...init, body: JSON.parse(init.body) }; };
  await client.scrobble('stop', { ...episode(), progress: 100 });
  assert.equal(sent.path, '/scrobble/stop');
  assert.equal(sent.keepalive, true);
  assert.equal(sent.body.progress, 100);
  assert.equal(sent.body.show.ids.tmdb, 42);
  assert.deepEqual(sent.body.episode, { season: 2, number: 3 });
  assert.equal(sent.body.shows, undefined, 'bulk history format is not a valid scrobble');
});

test('Trakt movie scrobble uses a singular movie, not a bulk movies array', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: {} }, './storage': storage(), './http': {} });
  const client = new TraktClient();
  client.refreshIfNeeded = async () => ({ access_token: 'test-token' });
  let body;
  client.trakt = async (_path, init) => { body = JSON.parse(init.body); };
  for (const action of ['start', 'pause', 'stop']) {
    await client.scrobble(action, { mediaType: 'movie', tmdbId: 99, progress: 43.25 });
    assert.deepEqual(body, { movie: { ids: { tmdb: 99 } }, progress: 43.25 });
  }
});

for (const status of [400, 404, 409, 422, 429]) {
  test(`Trakt HTTP ${status} scrobble is not repeated through Netlify`, async () => {
    const proxyCalls = [];
    const { TraktClient } = load('lib/trakt.ts', {
      './config': { config: { traktClientId: 'test-client' } }, './storage': storage(),
      './http': { jsonRequest: async (...args) => { proxyCalls.push(args); } }
    }, { window: { location: { origin: 'https://web.example' } } });
    const client = new TraktClient();
    client.refreshIfNeeded = async () => ({ access_token: 'test-token' });
    client.directTrakt = async () => { throw Object.assign(new Error(`HTTP ${status}`), { status }); };
    const request = client.scrobble('stop', { mediaType: 'movie', tmdbId: 99, progress: 100 });
    if (status === 409) await request;
    else await assert.rejects(request, new RegExp(`HTTP ${status}`));
    assert.equal(proxyCalls.length, 0);
  });
}

const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const activity = load('lib/traktActivity.ts');

test('activity signature reacts to watches, pauses and hidden shows, not unrelated ratings', () => {
  const initial = { movies: { watched_at: 'a', paused_at: 'b' }, episodes: { watched_at: 'c', paused_at: 'd' }, shows: { hidden_at: 'e' } };
  const signature = activity.continueWatchingActivitySignature(initial);
  assert.ok(signature);
  assert.equal(activity.continueWatchingActivitySignature({ ...initial, all: 'new', ratings: { updated_at: 'new' } }), signature);
  assert.notEqual(activity.continueWatchingActivitySignature({ ...initial, episodes: { watched_at: 'new' } }), signature);
  assert.notEqual(activity.continueWatchingActivitySignature({ ...initial, shows: { hidden_at: 'new' } }), signature);
  assert.equal(activity.continueWatchingActivitySignature({}), null);
});

test('idle visible polling refreshes only after activity changed; hidden/playing/in-flight sessions do no work', async () => {
  let active = true, signature = 'one', reads = 0, refreshes = 0;
  const snapshot = { current: { key: 'a', signature } };
  const monitor = activity.createTraktActivityCheck({ key: 'a', snapshot, isActive: () => active,
    read: async () => { reads++; return signature; },
    refresh: async () => { refreshes++; snapshot.current = { key: 'a', signature }; } });
  await monitor.check();
  assert.equal(refreshes, 0);
  active = false;
  signature = 'two';
  await monitor.check();
  assert.equal(reads, 1);
  active = true;
  await monitor.check();
  assert.equal(refreshes, 1);
  await monitor.check();
  assert.equal(refreshes, 1);
  monitor.dispose();
  await monitor.check();
  assert.equal(reads, 3);
});

test('failed refresh backs off rather than repeatedly invoking a full sync', async () => {
  let now = Date.now(), refreshes = 0;
  class Clock extends Date { static now() { return now; } }
  const { createTraktActivityCheck } = load('lib/traktActivity.ts', {}, { Date: Clock });
  const monitor = createTraktActivityCheck({ key: 'a', snapshot: { current: { key: 'a', signature: 'old' } },
    isActive: () => true, read: async () => 'new', refresh: async () => { refreshes++; throw Error('offline'); } });
  await monitor.check();
  now += 120000;
  await monitor.check();
  assert.equal(refreshes, 1);
  now += 600000;
  await monitor.check();
  assert.equal(refreshes, 2);
});

test('switching profile during an activity read cannot refresh the next profile', async () => {
  let resolve, reads = 0, refreshes = 0;
  const read = new Promise((yes) => { resolve = yes; });
  const monitor = activity.createTraktActivityCheck({ key: 'a', snapshot: { current: { key: 'a', signature: 'old' } },
    isActive: () => true, read: () => { reads++; return read; }, refresh: async () => { refreshes++; } });
  const pending = monitor.check();
  await monitor.check();
  assert.equal(reads, 1);
  monitor.dispose();
  resolve('new');
  await pending;
  assert.equal(refreshes, 0);
});

function clientHarness(fetch) {
  let proxy = 0;
  class HttpError extends Error { constructor(status, message) { super(message); this.status = status; } }
  const { TraktClient } = load('lib/trakt.ts', {
    './storage': storage(), './config': { config: { traktClientId: 'own-public-id' } },
    './http': { HttpError, jsonRequest: async (_url, init) => { proxy++; assert.equal(init.cache, 'no-store'); return []; } }
  }, { fetch, window: { location: { origin: 'https://web.example' } } });
  const client = new TraktClient();
  client.token = { access_token: 'user-token', refresh_token: 'refresh', expires_at: Date.now() + 3600000 };
  return { client, proxyCalls: () => proxy };
}

test('background activity request goes directly to Trakt, never through Netlify even on a 403', async () => {
  const urls = [];
  const h = clientHarness(async (url, init) => {
    urls.push(url);
    assert.equal(init.cache, 'no-store');
    assert.equal(new Headers(init.headers).get('authorization'), 'Bearer user-token');
    return new Response('blocked', { status: 403 });
  });
  await assert.rejects(h.client.continueWatchingActivity(), /blocked/);
  assert.equal(urls[0], 'https://api.trakt.tv/sync/last_activities');
  assert.equal(h.proxyCalls(), 0);
  h.client.token.expires_at = 0;
  assert.equal(await h.client.continueWatchingActivity(), null);
  assert.equal(urls.length, 1, 'expired token is not refreshed by background polling');
});

test('normal fallback reads bypass stale browser cache as well', async () => {
  const h = clientHarness(async () => { throw Error('direct unavailable'); });
  assert.equal((await h.client.playback()).length, 0);
  assert.equal(h.proxyCalls(), 1);
});

test('authenticated proxy responses are not cacheable; public catalog caching remains enabled', async () => {
  class NextResponse extends Response { static json(data, init) { return Response.json(data, init); } }
  const route = load('app/api/trakt/[...path]/route.ts', { 'next/server': { NextResponse } }, {
    process: { env: { TRAKT_CLIENT_ID: 'own-id', NEXT_PUBLIC_SELF_HOSTED: 'true' } },
    fetch: async () => Response.json([])
  });
  const context = { params: Promise.resolve({ path: ['sync', 'playback'] }) };
  const response = await route.GET(new Request('https://self.example/api/trakt/sync/playback', { headers: { 'x-user-token': 'user' } }), context);
  assert.equal(response.headers.get('cache-control'), 'private, no-store');
  const publicResponse = await route.GET(new Request('https://self.example/api/trakt/movies/trending'), { params: Promise.resolve({ path: ['movies', 'trending'] }) });
  assert.match(publicResponse.headers.get('cache-control'), /public.*s-maxage=900/);
});

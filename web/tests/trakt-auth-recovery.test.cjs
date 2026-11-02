const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

function harness(direct, proxy = async () => { throw new Error('Unexpected proxy request'); }) {
  const stored = storage();
  const http = load('lib/http.ts', { './config': { config: {} } });
  let proxyCalls = 0;
  const { TraktClient } = load('lib/trakt.ts', {
    './config': { config: { traktClientId: 'fixture-client', traktClientSecret: 'fixture-secret' }, hasTraktConfig: () => true },
    './storage': stored,
    './http': { ...http, jsonRequest: (...args) => { proxyCalls++; return proxy(...args); } }
  }, { fetch: direct, window: { location: { origin: 'https://web.example' } } });
  const client = new TraktClient();
  client.setProfile('first');
  return { client, stored, proxyCalls: () => proxyCalls, HttpError: http.HttpError };
}

for (const status of [400, 401, 403, 404, 409, 410, 418, 429]) {
  test(`device poll preserves Trakt HTTP ${status} without hiding it behind a blocked proxy`, async () => {
    const h = harness(async () => new Response('{"error":"authorization_pending"}', {
      status, headers: { 'content-type': 'application/json', 'retry-after': '20' }
    }));
    await assert.rejects(h.client.pollDeviceToken('fixture-code'), error => error.status === status && error.retryAfter === '20');
    assert.equal(h.proxyCalls(), 0);
    assert.equal(h.client.isConnected, false);
  });
}

test('device code and approved token use direct requests with valid Trakt headers', async () => {
  const h = harness(async (url, init) => {
    assert.equal(new Headers(init.headers).get('trakt-api-key'), 'fixture-client');
    return new Response(JSON.stringify(url.endsWith('/code')
      ? { device_code: 'device', user_code: 'CODE', verification_url: 'https://trakt.tv/activate', expires_in: 600, interval: 5 }
      : { access_token: 'access', refresh_token: 'refresh', expires_in: 86400 }), { status: 200 });
  });
  assert.equal((await h.client.beginDeviceLink()).user_code, 'CODE');
  assert.equal((await h.client.pollDeviceToken('device')).access_token, 'access');
  assert.equal(h.proxyCalls(), 0);
});

test('a transport failure may use the existing backend fallback', async () => {
  const h = harness(async () => { throw new TypeError('Network unavailable'); }, async () => ({ access_token: 'access', refresh_token: 'refresh', expires_in: 60 }));
  assert.equal((await h.client.pollDeviceToken('device')).access_token, 'access');
  assert.equal(h.proxyCalls(), 1);
});

for (const status of [401, 403]) {
  test(`HTML ${status} during refresh retains the saved connection when both routes fail`, async () => {
    let h;
    h = harness(async () => new Response('<!DOCTYPE html><HTML>Blocked</HTML>', { status, headers: { 'content-type': 'text/html' } }),
      async () => { throw new h.HttpError(503, 'Trakt is temporarily blocking this connection. Please try again later.'); });
    const token = { access_token: 'saved', refresh_token: 'saved-refresh', expires_at: 1 };
    h.client.setToken(token);
    await assert.rejects(h.client.playback(), error => error.status === 503 && !error.message.includes('still connected'));
    assert.equal(h.client.token.access_token, token.access_token);
    assert.equal(h.stored.loadStored('arvio.web.trakt.token:first').refresh_token, token.refresh_token);
  });
}

test('genuine rejected refresh clears only the current connection, without proxy fallback', async () => {
  const h = harness(async () => new Response('{"error":"invalid_token"}', { status: 401 }));
  h.client.setToken({ access_token: 'old', refresh_token: 'old-refresh', expires_at: 1 });
  await assert.rejects(h.client.playback(), error => error.status === 401);
  assert.equal(h.client.isConnected, false);
  assert.equal(h.proxyCalls(), 0);
});

test('a late refresh rejection cannot disconnect another profile', async () => {
  let finish;
  const h = harness(() => new Promise(resolve => { finish = resolve; }));
  h.client.setToken({ access_token: 'first', refresh_token: 'first-refresh', expires_at: 1 });
  const pending = h.client.playback();
  h.client.setProfile('second');
  h.client.setToken({ access_token: 'second', refresh_token: 'second-refresh', expires_at: Date.now() + 86400000 });
  finish(new Response('{"error":"invalid_token"}', { status: 401 }));
  await assert.rejects(pending);
  assert.equal(h.client.token.access_token, 'second');
});

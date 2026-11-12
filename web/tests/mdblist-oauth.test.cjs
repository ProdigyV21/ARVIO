const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

class HttpError extends Error { constructor(status) { super('HTTP error'); this.status = status; } }
function setup(request) {
  const stored = storage();
  const { MdbListClient } = load('lib/mdblist.ts', {
    './http': { jsonRequest: request, HttpError }, './storage': stored
  }, { window: { location: { origin: 'https://arvio.test' } } });
  const client = new MdbListClient();
  client.setProfile('one');
  return { client, stored };
}
const token = (expiresAt = Date.now() + 3600000) => ({ accessToken: 'access', refreshToken: 'refresh', expiresAt });

test('OAuth supports watched history and writes without an API key', async () => {
  const requests = [];
  const { client } = setup(async (url, init) => {
    requests.push([url, init]);
    return { movies: [{ movie: { ids: { tmdb: 42 } } }], pagination: { has_more: false } };
  });
  client.setToken(token());
  assert.equal((await client.watched('movies')).length, 1);
  await client.addToWatchlist({ mediaType: 'movie', tmdbId: 42 });
  await client.addToHistory({ mediaType: 'movie', tmdbId: 42 });
  await client.removeFromHistory({ mediaType: 'movie', tmdbId: 42 });
  assert.equal(requests.length, 4);
  for (const [, init] of requests) {
    assert.equal(init.headers.authorization, 'Bearer access');
    assert.equal(init.headers['x-mdblist-key'], undefined);
  }
});

test('new API-key connection replaces OAuth locally and in storage', async () => {
  const { client, stored } = setup(async () => ({}));
  client.setToken(token());
  client.setKey('api-key');
  assert.equal(client.token, null);
  assert.equal(stored.values.has('arvio.web.mdblist.token:one'), false);
  client.setToken(token());
  assert.equal(client.key, null);
  assert.equal(stored.values.has('arvio.web.mdblist.key:one'), false);
});

test('concurrent expiry shares refresh and preserves an omitted refresh token', async () => {
  let renewals = 0, saved = 0;
  const { client } = setup(async (url, init) => {
    if (url.includes('/oauth/token/')) { renewals++; await new Promise(r => setTimeout(r, 5)); return { access_token: 'new', expires_in: 3600 }; }
    assert.equal(init.headers.authorization, 'Bearer new');
    return [];
  });
  client.setToken(token(1));
  client.onTokenRefreshed = async (profile, value) => { assert.equal(profile, 'one'); assert.equal(value.refreshToken, 'refresh'); saved++; };
  await Promise.all([client.addToHistory({ mediaType: 'movie', tmdbId: 1 }), client.addToWatchlist({ mediaType: 'movie', tmdbId: 2 })]);
  assert.equal(renewals, 1);
  assert.equal(saved, 1);
});

test('disconnect and profile switch discard a late refresh', async () => {
  for (const change of [c => c.disconnect(), c => c.setProfile('two'), c => c.setKey('replacement')]) {
    let complete;
    let writes = 0;
    const { client, stored } = setup(async url => {
      if (url.includes('/oauth/token/')) return new Promise(resolve => { complete = resolve; });
      writes++; return {};
    });
    client.setToken(token(1));
    const pending = client.addToHistory({ mediaType: 'movie', tmdbId: 1 });
    change(client);
    complete({ access_token: 'late', expires_in: 3600 });
    await assert.rejects(pending, /connection changed/);
    assert.equal(client.token, null);
    assert.equal(writes, 0);
    assert.notEqual(stored.loadStored('arvio.web.mdblist.token:one', null)?.accessToken, 'late');
  }
});

test('401 refreshes once then propagates a second failure', async () => {
  let renewals = 0, requests = 0;
  const { client } = setup(async url => {
    if (url.includes('/oauth/token/')) { renewals++; return { access_token: 'new', expires_in: 3600 }; }
    requests++; throw new HttpError(401);
  });
  client.setToken(token());
  await assert.rejects(client.addToHistory({ mediaType: 'movie', tmdbId: 1 }), HttpError);
  assert.equal(renewals, 1);
  assert.equal(requests, 2);
});

test('proxy refresh uses server public client ID and form encoding, never an API key', async () => {
  let call;
  class NextResponse extends Response { static json(value, init) { return Response.json(value, init); } }
  const route = load('app/api/mdblist/[...path]/route.ts', { 'next/server': { NextResponse } }, {
    process: { env: { MDBLIST_CLIENT_ID: 'public-client' } },
    fetch: async (url, init) => { call = [url, init]; return Response.json({ access_token: 'new' }); }
  });
  const result = await route.POST(new Request('https://arvio.test/api/mdblist/oauth/token/', {
    method: 'POST', body: JSON.stringify({ refresh_token: 'refresh', client_id: 'untrusted' })
  }), { params: Promise.resolve({ path: ['oauth', 'token'] }) });
  assert.equal(result.status, 200);
  assert.equal(call[0], 'https://api.mdblist.com/oauth/token/');
  assert.equal(call[1].body.get('client_id'), 'public-client');
  assert.equal(call[1].body.get('grant_type'), 'refresh_token');
  assert.equal(call[1].headers.authorization, undefined);
  assert.equal(result.headers.get('cache-control'), 'no-store');
});

test('proxy device-authorization and device_code token exchange use server client_id', async () => {
  const calls = [];
  class NextResponse extends Response { static json(value, init) { return Response.json(value, init); } }
  const routeWithEnv = (env) => load('app/api/mdblist/[...path]/route.ts', { 'next/server': { NextResponse } }, {
    process: { env },
    fetch: async (url, init) => {
      calls.push([url, init]);
      return url.includes('device-authorization')
        ? Response.json({ device_code: 'dc-1', user_code: 'ABCD', verification_uri: 'https://mdblist.com/oauth/device', expires_in: 600, interval: 5 })
        : Response.json({ access_token: 'acc-1', refresh_token: 'ref-1', expires_in: 3600 });
    }
  });

  const unconfigured = routeWithEnv({});
  assert.equal((await unconfigured.GET(new Request('https://arvio.test/api/mdblist/oauth/status'), { params: Promise.resolve({ path: ['oauth', 'status'] }) }).then(r => r.json())).oauthConfigured, false);
  assert.equal((await unconfigured.POST(new Request('https://arvio.test/api/mdblist/oauth/device-authorization', { method: 'POST', body: '{}' }), { params: Promise.resolve({ path: ['oauth', 'device-authorization'] }) })).status, 503);

  const route = routeWithEnv({ MDBLIST_CLIENT_ID: 'public-client' });
  assert.equal((await route.GET(new Request('https://arvio.test/api/mdblist/oauth/status'), { params: Promise.resolve({ path: ['oauth', 'status'] }) }).then(r => r.json())).oauthConfigured, true);

  const startRes = await route.POST(new Request('https://arvio.test/api/mdblist/oauth/device-authorization', { method: 'POST', body: '{}' }), { params: Promise.resolve({ path: ['oauth', 'device-authorization'] }) });
  assert.equal(startRes.status, 200);
  assert.equal(calls[0][0], 'https://api.mdblist.com/oauth/device-authorization/');
  assert.equal(calls[0][1].body.get('client_id'), 'public-client');
  assert.equal(calls[0][1].body.get('scope'), 'write');

  const pollRes = await route.POST(new Request('https://arvio.test/api/mdblist/oauth/token/', {
    method: 'POST', body: JSON.stringify({ device_code: 'dc-1', client_id: 'untrusted' })
  }), { params: Promise.resolve({ path: ['oauth', 'token'] }) });
  assert.equal(pollRes.status, 200);
  assert.equal(calls[1][0], 'https://api.mdblist.com/oauth/token/');
  assert.equal(calls[1][1].body.get('grant_type'), 'urn:ietf:params:oauth:grant-type:device_code');
  assert.equal(calls[1][1].body.get('client_id'), 'public-client');
  assert.equal(calls[1][1].body.get('device_code'), 'dc-1');
});

test('OAuth proxy preserves provider Retry-After for both authorization and token polling', async () => {
  class NextResponse extends Response { static json(value, init) { return Response.json(value, init); } }
  const route = load('app/api/mdblist/[...path]/route.ts', { 'next/server': { NextResponse } }, {
    process: { env: { MDBLIST_CLIENT_ID: 'public-client' } },
    fetch: async () => Response.json({ error: 'slow_down' }, { status: 429, headers: { 'retry-after': '60' } }),
  });
  for (const endpoint of ['device-authorization', 'token']) {
    const result = await route.POST(new Request(`https://arvio.test/api/mdblist/oauth/${endpoint}/`, {
      method: 'POST', body: JSON.stringify({ device_code: 'fixture' }),
    }), { params: Promise.resolve({ path: ['oauth', endpoint] }) });
    assert.equal(result.status, 429);
    assert.equal(result.headers.get('retry-after'), '60');
    assert.equal(result.headers.get('cache-control'), 'no-store');
  }
});

test('client begins device link, handles pending/denied/expired errors, and exchanges token', async () => {
  let step = 0;
  class MockHttpError extends Error {
    constructor(status, message, retryAfter = null) { super(message); this.status = status; this.retryAfter = retryAfter; }
  }
  const { MdbListClient } = load('lib/mdblist.ts', {
    './http': {
      HttpError: MockHttpError,
      jsonRequest: async (url, init) => {
        if (url.endsWith('/oauth/status/')) return { oauthConfigured: true };
        if (url.endsWith('/oauth/device-authorization/')) {
          return { device_code: 'dc-1', user_code: 'CODE-1', verification_uri: 'https://mdblist.com/oauth/device', expires_in: 600, interval: 5 };
        }
        const body = JSON.parse(init.body);
        assert.equal(body.grant_type, 'urn:ietf:params:oauth:grant-type:device_code');
        assert.equal(body.device_code, 'dc-1');
        step++;
        if (step === 1) throw new MockHttpError(400, 'authorization_pending');
        if (step === 2) throw new MockHttpError(400, 'access_denied');
        return { access_token: 'new-access', refresh_token: 'new-refresh', expires_in: 3600 };
      }
    },
    './storage': storage()
  }, { window: { location: { origin: 'https://arvio.test' } } });

  const client = new MdbListClient();
  client.setProfile('one');
  assert.equal(await client.isOAuthConfigured(), true);
  const code = await client.beginDeviceLink();
  assert.equal(code.user_code, 'CODE-1');

  const pendingErr = await client.pollDeviceToken('dc-1').catch(e => e);
  assert.match(pendingErr.message, /not approved/);
  const deniedErr = await client.pollDeviceToken('dc-1').catch(e => e);
  assert.match(deniedErr.message, /declined/);

  const tokenRes = await client.pollDeviceToken('dc-1');
  assert.equal(tokenRes.accessToken, 'new-access');
  assert.equal(tokenRes.refreshToken, 'new-refresh');
});

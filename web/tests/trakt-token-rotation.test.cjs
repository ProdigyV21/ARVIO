const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

// Trakt refresh tokens are single use. A rotation on one device must reach the others, and a stale
// cloud copy must never replace a newer local pair.

function traktHarness(direct) {
  const stored = storage();
  const http = load('lib/http.ts', { './config': { config: {} } });
  const { TraktClient } = load('lib/trakt.ts', {
    './config': { config: { traktClientId: 'fixture-client', traktClientSecret: 'fixture-secret' }, hasTraktConfig: () => true },
    './storage': stored,
    './http': { ...http, jsonRequest: async () => { throw new Error('Unexpected proxy request'); } }
  }, { fetch: direct, window: { location: { origin: 'https://web.example' } } });
  const client = new TraktClient();
  client.setProfile('first');
  return { client };
}

function cloudHarness(root) {
  let payload = root;
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': {},
    './http': { jsonRequest: async (_url, request) => {
      if (request?.method === 'POST') { payload = JSON.parse(request.body).payload; return { accepted: true }; }
      return { payload };
    } }
  });
  return { cloud, payload: () => payload };
}

test('a rotated refresh token is published once, for the profile that rotated it', async () => {
  const h = traktHarness(async () => new Response(
    JSON.stringify({ access_token: 'new-access', refresh_token: 'new-refresh', expires_in: 7776000 }), { status: 200 }));
  const published = [];
  h.client.onTokenRefreshed = async (profileId, token) => { published.push({ profileId, token }); };
  h.client.setToken({ access_token: 'old', refresh_token: 'old-refresh', expires_at: 1 });
  await h.client.playback().catch(() => undefined);
  await new Promise(resolve => setTimeout(resolve, 0));
  assert.equal(published.length, 1);
  assert.equal(published[0].profileId, 'first');
  assert.equal(published[0].token.refresh_token, 'new-refresh');
  assert.equal(published[0].token.access_token, 'new-access');
});

test('a failing publish never breaks the refresh that triggered it', async () => {
  const h = traktHarness(async () => new Response(
    JSON.stringify({ access_token: 'new-access', refresh_token: 'new-refresh', expires_in: 7776000 }), { status: 200 }));
  h.client.onTokenRefreshed = async () => { throw new Error('cloud unreachable'); };
  h.client.setToken({ access_token: 'old', refresh_token: 'old-refresh', expires_at: 1 });
  await h.client.playback().catch(() => undefined);
  await new Promise(resolve => setTimeout(resolve, 0));
  assert.equal(h.client.token.refresh_token, 'new-refresh');
});

test('no hook, no change: refresh still works without a publisher', async () => {
  const h = traktHarness(async () => new Response(
    JSON.stringify({ access_token: 'new-access', refresh_token: 'new-refresh', expires_in: 7776000 }), { status: 200 }));
  h.client.setToken({ access_token: 'old', refresh_token: 'old-refresh', expires_at: 1 });
  await h.client.playback().catch(() => undefined);
  assert.equal(h.client.token.access_token, 'new-access');
});

test('a newer local pair is kept over an older cloud copy, and the cloud wins otherwise', () => {
  const { cloud } = cloudHarness({});
  const older = { access_token: 'a1', refresh_token: 'r1', expires_at: 1_000_000_000_000 };
  const newer = { access_token: 'a2', refresh_token: 'r2', expires_at: 2_000_000_000_000 };
  assert.equal(cloud.chooseTraktToken(newer, older), newer);
  assert.equal(cloud.chooseTraktToken(older, newer), newer);
  assert.equal(cloud.chooseTraktToken(older, { ...older }).refresh_token, 'r1');
  assert.equal(cloud.chooseTraktToken(older, null), null, 'a cloud disconnect still disconnects');
  assert.equal(cloud.chooseTraktToken(null, older), older);
});

test('the cloud copy carries the expiry in epoch seconds so Android reads it right', async () => {
  const { cloud, payload } = cloudHarness({});
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  const expiresAtMs = 1_800_000_000_000;
  assert.equal(cloud.cloudTraktExpirySeconds(expiresAtMs), 1_800_000_000);
  assert.equal(cloud.cloudTraktExpirySeconds(1_800_000_000), 1_800_000_000, 'seconds stay seconds');
  await cloud.saveCloudTrackingSelection(auth, 'p1', {
    provider: 'TRAKT',
    traktToken: { access_token: 'a', refresh_token: 'r', expires_at: expiresAtMs },
    simklToken: null,
    mdbListApiKey: null,
    changedDomains: ['trakt']
  }).catch(() => undefined);
  const saved = payload().traktTokens?.p1;
  assert.ok(saved, 'the Trakt credential was written');
  assert.equal(saved.expiresAt, 1_800_000_000);
  assert.equal(saved.expires_at, 1_800_000_000);
  assert.equal(saved.refreshToken, 'r');
});

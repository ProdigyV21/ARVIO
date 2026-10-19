const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const id = 'a1101111-2222-4333-8444-555566667777';
function fixture({ privacy = {}, blocked = false, now = Date.now() } = {}) {
  const values = new Map(); const requests = []; const options = []; let clock = now;
  const location = new URL(`https://web.arvio.tv/?intent=trial&arvio_journey=${id}&utm_source=discord&utm_campaign=launch`);
  const storage = { getItem: key => values.get(key), setItem: (key, value) => values.set(key, value) };
  const window = { location, history: { state: null, replaceState(_state, _title, value) { const target = new URL(value); location.href = target.href; } } };
  Object.defineProperty(window, 'sessionStorage', { get() { if (blocked) throw Error('blocked'); return storage; } });
  Object.defineProperty(window, 'localStorage', { get() { throw Error('persistent storage must not be used for attribution'); } });
  class Clock extends Date { static now() { return clock; } }
  const module = load('lib/premiumAnalytics.ts', {
    './config': { config: { paywallEnabled: true, netlifyBackendUrl: 'https://backend.invalid' } },
    './http': { jsonRequest: async (_url, init) => { requests.push(JSON.parse(init.body)); options.push(init); } }
  }, { window, document: { referrer: 'https://arvio.tv/premium/' }, navigator: privacy, Date: Clock });
  const auth = { session: { userId: 'account-a' }, accessToken: async () => 'fixture' };
  return { module, requests, options, auth, values, location, advance: ms => { clock += ms; } };
}

test('campaign navigation links to an account using expiring session storage and removes the URL id', async () => {
  const f = fixture();
  f.module.capturePremiumAttribution();
  assert.equal(f.location.searchParams.has('arvio_journey'), false);
  assert.equal(f.location.searchParams.get('intent'), 'trial');
  await f.module.trackPremiumEvent(f.auth, 'paywall_view');
  assert.equal(f.requests[0].journey_id, id);
  assert.equal(f.requests[0].metadata.source, 'discord');
  assert.equal(f.requests[0].metadata.campaign, 'launch');
  assert.equal(f.requests[0].metadata.journey_id, undefined);
  assert.equal(f.values.has('arvio.premium.attribution.v1'), false);
});

test('membership entry placement does not overwrite the original campaign source', async () => {
  const f = fixture();
  await f.module.trackPremiumEvent(f.auth, 'checkout_opened', { entry: 'trial_nav' });
  assert.equal(f.requests[0].metadata.source, 'discord');
  assert.equal(f.requests[0].metadata.entry, 'trial_nav');
  assert.equal(f.requests[0].journey_id, id);
});

test('handoff telemetry survives navigation without sending identity in a provider URL', async () => {
  const f = fixture();
  await f.module.trackPremiumEvent(f.auth, 'checkout_opened', { entry: 'paywall', flow_version: 'direct_v2' });
  assert.equal(f.options[0].keepalive, true);
  assert.equal(f.options[0].headers.Authorization, 'Bearer fixture');
  assert.equal(f.requests[0].metadata.flow_version, 'direct_v2');
  assert.equal(JSON.stringify(f.requests).includes('email'), false);
});

test('a new signed-in account and expired sessions cannot inherit the previous campaign', async () => {
  const f = fixture();
  await f.module.trackPremiumEvent(f.auth, 'paywall_view');
  f.auth.session.userId = 'account-b';
  await f.module.trackPremiumEvent(f.auth, 'paywall_view');
  assert.equal(f.requests[1].journey_id, undefined);
  assert.equal(f.requests[1].metadata.source, 'direct');
  const g = fixture();
  await g.module.trackPremiumEvent(g.auth, 'paywall_view');
  g.advance(24 * 3600000 + 1);
  await g.module.trackPremiumEvent(g.auth, 'checkout_opened');
  assert.equal(g.requests[1].journey_id, undefined);
  assert.equal(g.requests[1].metadata.source, 'direct');
});

test('blocked storage still permits optional in-memory measurement without delaying access', async () => {
  const f = fixture({ blocked: true });
  assert.equal(await f.module.trackPremiumEvent(f.auth, 'paywall_view'), true);
  assert.equal(f.requests[0].journey_id, id);
});

test('DNT and GPC suppress browser tracking and storage', async () => {
  for (const privacy of [{ doNotTrack: '1' }, { globalPrivacyControl: true }]) {
    const f = fixture({ privacy });
    assert.equal(await f.module.trackPremiumEvent(f.auth, 'paywall_view'), false);
    f.module.capturePremiumAttribution();
    assert.equal(f.requests.length, 0);
    assert.equal(f.values.size, 0);
  }
});

test('account switches during token refresh cannot attach the old journey to another identity', async () => {
  const f = fixture();
  f.auth.accessToken = async () => { f.auth.session.userId = 'account-b'; return 'new-token'; };
  assert.equal(await f.module.trackPremiumEvent(f.auth, 'paywall_view'), false);
  assert.equal(f.requests.length, 0);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const id = 'a1101111-2222-4333-8444-555566667777';
function fixture({ privacy = {}, blocked = false, now = Date.now(),
  url = `https://web.arvio.tv/?intent=trial&arvio_journey=${id}&utm_source=discord&utm_campaign=launch`,
  referrer = 'https://arvio.tv/premium/' } = {}) {
  const values = new Map(); const requests = []; const options = []; let clock = now;
  const location = new URL(url);
  const storage = { getItem: key => values.get(key), setItem: (key, value) => values.set(key, value) };
  const window = { location, history: { state: null, replaceState(_state, _title, value) { const target = new URL(value); location.href = target.href; } } };
  Object.defineProperty(window, 'sessionStorage', { get() { if (blocked) throw Error('blocked'); return storage; } });
  Object.defineProperty(window, 'localStorage', { get() { throw Error('persistent storage must not be used for attribution'); } });
  class Clock extends Date { static now() { return clock; } }
  const module = load('lib/premiumAnalytics.ts', {
    './config': { config: { paywallEnabled: true, netlifyBackendUrl: 'https://backend.invalid' } },
    './http': { jsonRequest: async (_url, init) => { requests.push(JSON.parse(init.body)); options.push(init); } }
  }, { window, document: { referrer }, navigator: privacy, Date: Clock });
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

test('direct web arrivals retain the observed search/referral source without an anonymous journey', async () => {
  for (const [referrer, source, medium] of [
    ['https://www.google.nl/search?q=private-title', 'www.google.nl', 'organic'],
    ['https://www.reddit.com/r/arvio/?private=value', 'www.reddit.com', 'referral'],
    ['https://www.google.com.evil.invalid/', 'www.google.com.evil.invalid', 'referral'],
    ['https://arvio.tv/premium/', 'arvio.tv', 'internal'],
    ['', 'direct', 'direct']
  ]) {
    const f = fixture({ url: 'https://web.arvio.tv/', referrer });
    await f.module.trackPremiumEvent(f.auth, 'checkout_opened', { entry: 'feature_preview' });
    assert.equal(f.requests[0].journey_id, undefined);
    assert.equal(f.requests[0].metadata.source, source);
    assert.equal(f.requests[0].metadata.medium, medium);
    assert.equal(f.requests[0].metadata.page, 'web');
    assert.equal(f.requests[0].metadata.landing_page, 'web');
    assert.equal(JSON.stringify(f.requests).includes('private'), false);
  }
});

test('bounded marketing landing survives login and checkout while private paths never enter attribution', async () => {
  const f = fixture({ url: `https://web.arvio.tv/?arvio_journey=${id}&arvio_landing=jellyfin&utm_source=www.google.com&utm_medium=organic` });
  f.module.capturePremiumAttribution();
  assert.equal(f.location.searchParams.has('arvio_landing'), false);
  await f.module.trackPremiumEvent(f.auth, 'account_connected');
  await f.module.trackPremiumEvent(f.auth, 'checkout_opened', { entry: 'account_settings' });
  assert.equal(f.requests[1].metadata.source, 'www.google.com');
  assert.equal(f.requests[1].metadata.medium, 'organic');
  assert.equal(f.requests[1].metadata.landing_page, 'jellyfin');
  const g = fixture({ url: 'https://web.arvio.tv/?arvio_landing=%2Faccount%3Femail%3Dprivate%40example.test&utm_source=private%40example.test', referrer: 'invalid' });
  await g.module.trackPremiumEvent(g.auth, 'checkout_opened');
  assert.equal(g.requests[0].metadata.landing_page, 'web');
  assert.equal(g.requests[0].metadata.source, 'direct');
  assert.equal(JSON.stringify(g.requests).includes('private'), false);
});

test('explicit campaign tags win over the observed referring site and a later campaign resets stale fields', async () => {
  const f = fixture({ url: 'https://web.arvio.tv/?utm_source=newsletter&utm_medium=email&utm_campaign=november', referrer: 'https://www.google.com/' });
  await f.module.trackPremiumEvent(f.auth, 'paywall_view');
  assert.equal(f.requests[0].metadata.source, 'newsletter');
  assert.equal(f.requests[0].metadata.medium, 'email');
  f.location.href = 'https://web.arvio.tv/?utm_source=reddit';
  await f.module.trackPremiumEvent(f.auth, 'checkout_opened');
  assert.equal(f.requests[1].metadata.source, 'reddit');
  assert.equal(f.requests[1].metadata.medium, 'campaign');
  assert.equal(f.requests[1].metadata.campaign, 'premium');
});

test('expired and switched-account attribution cannot reuse an earlier external referrer or landing', async () => {
  for (const action of ['expire', 'switch']) {
    const f = fixture({ url: 'https://web.arvio.tv/?arvio_landing=addons', referrer: 'https://www.reddit.com/' });
    await f.module.trackPremiumEvent(f.auth, 'paywall_view');
    if (action === 'expire') f.advance(24 * 3600000 + 1);
    else f.auth.session.userId = 'account-b';
    await f.module.trackPremiumEvent(f.auth, 'checkout_opened');
    assert.equal(f.requests[1].metadata.source, 'direct');
    assert.equal(f.requests[1].metadata.referrer, '');
    assert.equal(f.requests[1].metadata.landing_page, 'web');
  }
});

test('a later campaign from the same source cannot retain the earlier campaign journey or content', async () => {
  const f = fixture({ url: `https://web.arvio.tv/?arvio_journey=${id}&arvio_landing=addons&utm_source=newsletter&utm_medium=email&utm_campaign=november&utm_content=oldhero` });
  await f.module.trackPremiumEvent(f.auth, 'paywall_view');
  f.location.href = 'https://web.arvio.tv/?utm_source=newsletter&utm_campaign=december';
  await f.module.trackPremiumEvent(f.auth, 'checkout_opened');
  assert.equal(f.requests[1].journey_id, undefined);
  assert.equal(f.requests[1].metadata.source, 'newsletter');
  assert.equal(f.requests[1].metadata.campaign, 'december');
  assert.equal(f.requests[1].metadata.content, 'unspecified');
  assert.equal(f.requests[1].metadata.landing_page, 'web');
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const id = 'a1101111-2222-4333-8444-555566667777';
function fixture({ privacy = {}, blocked = false, selfHosted = false, now = Date.now(),
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
    './config': { config: { selfHosted, paywallEnabled: true, netlifyBackendUrl: 'https://backend.invalid' } },
    './http': { jsonRequest: async (_url, init) => { requests.push(JSON.parse(init.body)); options.push(init); } }
  }, { window, document: { referrer }, navigator: privacy, Date: Clock });
  const auth = { session: { userId: 'account-a' }, accessToken: async () => 'fixture' };
  return { module, requests, options, auth, values, location, advance: ms => { clock += ms; },
    loginRedirect: () => load('lib/partnerLinks.ts', {
      './premiumAnalytics': module,
      './premiumPurchaseIntent': load('lib/premiumPurchaseIntent.ts', { './config': { config: { paywallEnabled: true } } }, { window, Date: Clock })
    }, { window, Date: Clock }).partnerLoginRedirect(location.origin, location.search)
  };
}

test('real capture and login handoff preserve attribution with blocked storage and clean it again on return', async () => {
  const now = Date.UTC(2026, 10, 17, 12);
  const before = fixture({ blocked: true, now,
    url: `https://web.arvio.tv/?premium_plan=yearly&arvio_journey=${id}&arvio_landing=premium&utm_source=discord&utm_campaign=launch&email=private@example.com&return_url=https://evil.invalid` });
  before.module.capturePremiumAttribution();
  assert.equal(before.location.searchParams.has('arvio_journey'), false);
  const destination = new URL(before.loginRedirect());
  assert.equal(destination.origin, 'https://web.arvio.tv');
  assert.equal(destination.searchParams.get('premium_plan'), 'yearly');
  assert.equal(destination.searchParams.get('arvio_journey'), id);
  assert.equal(destination.searchParams.get('arvio_landing'), 'premium');
  assert.equal(destination.searchParams.get('arvio_attribution_until'), String(now + 24 * 3600000));
  assert.equal(destination.searchParams.get('utm_source'), 'discord');
  assert.equal(destination.searchParams.has('email'), false);
  assert.equal(destination.searchParams.has('return_url'), false);
  const after = fixture({ blocked: true, now: now + 1000, url: destination.href, referrer: 'https://auth.arvio.tv/' });
  await after.module.trackPremiumEvent(after.auth, 'checkout_opened', { entry: 'website_yearly' });
  assert.equal(after.requests[0].journey_id, id);
  assert.equal(after.requests[0].metadata.source, 'discord');
  assert.equal(after.requests[0].metadata.campaign, 'launch');
  assert.equal(after.requests[0].metadata.landing_page, 'premium');
  for (const key of ['arvio_journey', 'arvio_landing', 'arvio_attribution_until', 'utm_source', 'utm_campaign']) assert.equal(after.location.searchParams.has(key), false);
  const reloaded = fixture({ blocked: true, now: now + 24 * 3600000 + 1, url: after.location.href, referrer: after.location.href });
  await reloaded.module.trackPremiumEvent(reloaded.auth, 'checkout_opened');
  assert.equal(reloaded.requests[0].journey_id, undefined);
  assert.notEqual(reloaded.requests[0].metadata.source, 'discord');
  assert.notEqual(reloaded.requests[0].metadata.campaign, 'launch');
  // The return trip preserves the original expiry, rather than giving the
  // campaign another 24 hours after every auth navigation.
  after.advance(24 * 3600000 - 1000);
  assert.equal(after.module.premiumAttributionRedirectParams().size, 0);
  await after.module.trackPremiumEvent(after.auth, 'checkout_returned');
  assert.equal(after.requests[1].journey_id, undefined);
  assert.equal(after.requests[1].metadata.source, 'direct');
  assert.equal(after.requests[1].metadata.referrer, '');
});

test('sign-in attribution handoff respects privacy/self-hosting and never exports owned or expired campaigns', async () => {
  for (const options of [{ privacy: { doNotTrack: '1' } }, { privacy: { globalPrivacyControl: true } }, { selfHosted: true }]) {
    const f = fixture({ blocked: true, ...options, url: `https://web.arvio.tv/?premium_plan=monthly&arvio_journey=${id}&arvio_landing=premium&utm_source=discord` });
    f.module.capturePremiumAttribution();
    const destination = new URL(f.loginRedirect());
    assert.equal(destination.searchParams.get('premium_plan'), 'monthly');
    for (const key of ['arvio_journey', 'arvio_landing', 'arvio_attribution_until', 'utm_source']) assert.equal(destination.searchParams.has(key), false);
  }
  const otherOrigin = fixture({ url: `https://selfhost.example/?arvio_journey=${id}&utm_source=discord` });
  assert.equal(otherOrigin.module.premiumAttributionRedirectParams().size, 0);
  const owned = fixture({ blocked: true, url: `https://web.arvio.tv/?premium_plan=monthly&arvio_journey=${id}&utm_source=discord` });
  await owned.module.trackPremiumEvent(owned.auth, 'paywall_view');
  assert.equal(owned.module.premiumAttributionRedirectParams().size, 0);
  assert.equal(new URL(owned.loginRedirect()).searchParams.has('arvio_journey'), false);
  const expired = fixture({ blocked: true, url: `https://web.arvio.tv/?premium_plan=monthly&arvio_journey=${id}&utm_source=discord` });
  expired.module.capturePremiumAttribution(); expired.advance(24 * 3600000);
  const destination = new URL(expired.loginRedirect());
  assert.equal(destination.searchParams.has('arvio_journey'), false);
  assert.equal(destination.searchParams.has('utm_source'), false);
});

test('malformed or stale handoff values cannot revive campaign, journey, or the old document referrer', async () => {
  const now = Date.UTC(2026, 10, 17, 12);
  for (const expiry of [String(now), String(now - 1), String(now + 24 * 3600000 + 1), 'forever', '']) {
    const f = fixture({ blocked: true, now,
      url: `https://web.arvio.tv/?premium_plan=monthly&arvio_journey=${id}&arvio_landing=premium&arvio_attribution_until=${expiry}&utm_source=discord&utm_campaign=old`,
      referrer: 'https://old-campaign.example/private/path' });
    await f.module.trackPremiumEvent(f.auth, 'paywall_view');
    assert.equal(f.requests[0].journey_id, undefined, expiry);
    assert.equal(f.requests[0].metadata.source, 'direct', expiry);
    assert.equal(f.requests[0].metadata.referrer, '', expiry);
    assert.equal(f.location.searchParams.has('arvio_attribution_until'), false);
  }
  const invalid = fixture({ blocked: true, now,
    url: 'https://web.arvio.tv/?premium_plan=monthly&arvio_journey=not-a-uuid&arvio_landing=private&email=private@example.com&utm_source=private@example.com&utm_campaign=https://evil.invalid' });
  const destination = new URL(invalid.loginRedirect());
  assert.equal(destination.searchParams.has('arvio_journey'), false);
  assert.equal(destination.searchParams.get('arvio_landing'), 'web');
  assert.equal(destination.searchParams.has('email'), false);
  assert.equal(destination.href.includes('private'), false);
  assert.equal(destination.href.includes('evil.invalid'), false);
});

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

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const START = Date.UTC(2026, 10, 17, 12);
function fixture({ url = 'https://web.arvio.tv/?premium_plan=yearly&utm_source=discord#details', blocked = false,
  values = new Map(), now = START, selfHosted = false, paywallEnabled = true } = {}) {
  const location = new URL(url);
  let clock = now;
  const storage = { getItem: key => values.get(key) ?? null, setItem: (key, value) => values.set(key, value), removeItem: key => values.delete(key) };
  const window = { location, history: { state: { preserved: true }, replaceState(state, _title, value) {
    assert.deepEqual(state, { preserved: true }); location.href = new URL(value, location.href).href;
  } } };
  Object.defineProperty(window, 'sessionStorage', { get() { if (blocked) throw Error('blocked'); return storage; } });
  Object.defineProperty(window, 'localStorage', { get() { throw Error('persistent storage must not be used'); } });
  class Clock extends Date { static now() { return clock; } }
  const module = load('lib/premiumPurchaseIntent.ts', { './config': { config: { selfHosted, paywallEnabled } } }, { window, Date: Clock });
  return { module, location, values, advance: ms => { clock += ms; } };
}

test('only exact monthly/yearly values are accepted, with bounded nonfuture timestamps', () => {
  const f = fixture();
  assert.equal(f.module.parsePremiumPurchaseIntent('?premium_plan=monthly', START).plan, 'monthly');
  assert.equal(f.module.parsePremiumPurchaseIntent('?premium_plan=yearly', START).plan, 'yearly');
  for (const search of ['?premium_plan=Monthly', '?premium_plan=annual', '?premium_plan=', '?premium_plan=yearly&premium_plan=monthly',
    '?premium_plan=https://evil.invalid', '?premium_plan=yearly&premium_intent_at=', '?premium_plan=yearly&premium_intent_at=1e12',
    `?premium_plan=yearly&premium_intent_at=${START + 1}`, `?premium_plan=yearly&premium_intent_at=${START - 30 * 60 * 1000}`,
    `?premium_plan=yearly&premium_intent_at=${START}&premium_intent_at=${START}`, '?premium_plan=yearly&extra=' + 'x'.repeat(4096)]) {
    assert.equal(f.module.parsePremiumPurchaseIntent(search, START), null, search);
  }
});

test('capture timestamps the choice without losing attribution/hash and consumption clears it once', () => {
  const f = fixture();
  const intent = f.module.capturePremiumPurchaseIntent();
  assert.equal(intent.plan, 'yearly'); assert.equal(intent.createdAt, START);
  assert.equal(f.location.searchParams.get('premium_intent_at'), String(START));
  assert.equal(f.location.searchParams.get('utm_source'), 'discord');
  assert.equal(f.location.hash, '#details');
  f.advance(1000);
  assert.equal(f.module.capturePremiumPurchaseIntent().createdAt, START, 'rerender does not renew TTL');
  assert.equal(f.module.consumePremiumPurchaseIntent(), 'yearly');
  assert.equal(f.location.searchParams.has('premium_plan'), false);
  assert.equal(f.location.searchParams.has('premium_intent_at'), false);
  assert.equal(f.location.hash, '#details');
  assert.equal(f.location.searchParams.get('utm_source'), 'discord');
  assert.equal(f.values.size, 0);
  assert.equal(f.module.consumePremiumPurchaseIntent(), null);
  const reloaded = fixture({ url: f.location.href, values: f.values });
  assert.equal(reloaded.module.capturePremiumPurchaseIntent(), null);
});

test('a blocked-storage sign-in round trip retains selected plan, attribution, and original TTL', () => {
  const before = fixture({ blocked: true });
  before.module.capturePremiumPurchaseIntent();
  const redirect = before.module.premiumPurchaseLoginRedirect(before.location.origin, before.location.search, START + 1000);
  assert.equal(new URL(redirect).searchParams.get('premium_plan'), 'yearly');
  assert.equal(new URL(redirect).searchParams.get('premium_intent_at'), String(START));
  assert.equal(new URL(redirect).searchParams.get('utm_source'), 'discord');
  const after = fixture({ url: redirect, blocked: true, now: START + 1000 });
  assert.equal(after.module.consumePremiumPurchaseIntent(), 'yearly');
  assert.equal(after.module.consumePremiumPurchaseIntent(), null);
  const expired = fixture({ url: redirect, blocked: true, now: START + 30 * 60 * 1000 });
  assert.equal(expired.module.capturePremiumPurchaseIntent(), null);
  assert.equal(expired.location.searchParams.has('premium_plan'), false);
});

test('login destination is fixed, query allowlisted, malformed/duplicate choices and other origins rejected', () => {
  const f = fixture();
  const redirect = f.module.premiumPurchaseLoginRedirect('https://web.arvio.tv', '?premium_plan=monthly&utm_source=discord&return_url=https://evil.invalid&email=private@example.com', START);
  const url = new URL(redirect);
  assert.equal(url.origin, 'https://web.arvio.tv'); assert.equal(url.pathname, '/');
  assert.equal(url.searchParams.get('premium_plan'), 'monthly');
  assert.equal(url.searchParams.get('utm_source'), 'discord');
  assert.equal(url.searchParams.has('email'), false); assert.equal(url.searchParams.has('return_url'), false);
  for (const origin of ['https://evil.invalid', 'https://web.arvio.tv.evil.invalid', 'http://web.arvio.tv', 'http://localhost:3000']) {
    assert.equal(f.module.premiumPurchaseLoginRedirect(origin, '?premium_plan=yearly', START), null);
  }
  assert.equal(f.module.premiumPurchaseLoginRedirect('https://web.arvio.tv', '?premium_plan=yearly&premium_plan=monthly', START), null);
});

test('malformed incoming choice cancels a previous stored intent rather than resurrecting it', () => {
  const f = fixture(); f.module.capturePremiumPurchaseIntent();
  f.location.search = '?premium_plan=bogus';
  assert.equal(f.module.capturePremiumPurchaseIntent(), null);
  assert.equal(f.values.size, 0);
  assert.equal(f.location.search, '');
  assert.equal(f.module.consumePremiumPurchaseIntent(), null);
});

test('restored functional storage expires; clear dismisses the selection without future prompts', () => {
  const f = fixture(); f.module.capturePremiumPurchaseIntent();
  const saved = fixture({ url: 'https://web.arvio.tv/', values: f.values, now: START + 1000 });
  assert.equal(saved.module.capturePremiumPurchaseIntent().plan, 'yearly');
  saved.module.clearPremiumPurchaseIntent();
  assert.equal(saved.module.capturePremiumPurchaseIntent(), null);
  f.advance(30 * 60 * 1000);
  assert.equal(f.module.capturePremiumPurchaseIntent(), null);
});

test('self-hosted, disabled-paywall, and nonofficial origins cannot activate a purchase intent', () => {
  for (const options of [{ selfHosted: true }, { paywallEnabled: false }, { url: 'https://selfhost.example/?premium_plan=yearly' },
    { url: 'http://localhost:3000/?premium_plan=yearly' }]) {
    const f = fixture(options);
    assert.equal(f.module.capturePremiumPurchaseIntent(), null);
    assert.equal(f.module.consumePremiumPurchaseIntent(), null);
    assert.equal(f.values.size, 0);
    assert.equal(f.location.searchParams.has('premium_intent_at'), false);
  }
});

// Regional price for the 12-month pass: visitors from lower-income countries
// get the Ko-fi 50% discount link; everyone else keeps the $20 pass.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');

const react = { useEffect: () => {}, useState: (init) => [typeof init === 'function' ? init() : init, () => {}] };
function pricing(country) {
  const session = new Map();
  let fetches = 0;
  const mod = load('lib/regionalPricing.ts', { react }, {
    window: {},
    sessionStorage: { getItem: (k) => session.has(k) ? session.get(k) : null, setItem: (k, v) => session.set(k, String(v)) },
    fetch: async () => { fetches += 1; return { ok: true, json: async () => ({ country }) }; }
  });
  return { mod, session, get fetches() { return fetches; } };
}

test('Brazil, India and the Philippines get the regional pass; the US and Netherlands do not', async () => {
  for (const [code, regional] of [['BR', true], ['IN', true], ['PH', true], ['US', false], ['NL', false], [null, false]]) {
    const { mod } = pricing(code);
    await mod.loadVisitorCountry();
    assert.equal(mod.regionalYearly(), regional, String(code));
  }
});

test('the country is fetched once per session and remembered', async () => {
  const h = pricing('IN');
  await h.mod.loadVisitorCountry(); await h.mod.loadVisitorCountry();
  assert.equal(h.fetches, 1);
  assert.equal(h.session.get('arvio.visitorCountry.v1'), 'IN');
});

test('the Ko-fi link is the 50% discount on the 12-month pass', () => {
  const { mod } = pricing('BR');
  assert.equal(mod.REGIONAL_YEARLY_URL, 'https://ko-fi.com/arvio/link/ARVIOREGION50');
  assert.equal(mod.REGIONAL_YEARLY_PRICE, '$10');
});

test('kofiYearlyUrl swaps to the regional link only for regional visitors', () => {
  const entitlement = (regional, yearly = 'https://ko-fi.com/s/pass') => load('lib/entitlement.ts', {
    './storage': { loadStored: (_, f) => f, saveStored() {}, removeStored() {} }, './entitlementPolicy': {}, './http': { jsonRequest() {}, HttpError: class extends Error {} },
    './config': { config: { kofiYearlyUrl: yearly } },
    './regionalPricing': { REGIONAL_YEARLY_URL: 'https://ko-fi.com/arvio/link/ARVIOREGION50', regionalYearly: () => regional }
  });
  assert.equal(entitlement(true).kofiYearlyUrl(), 'https://ko-fi.com/arvio/link/ARVIOREGION50');
  assert.equal(entitlement(false).kofiYearlyUrl(), 'https://ko-fi.com/s/pass');
  assert.equal(entitlement(true, '').kofiYearlyUrl(), '', 'no yearly plan configured, no regional link');
});

test('/api/geo returns only the two-letter country and allows arvio.tv', () => {
  class NextResponse { constructor(body, init) { this.body = body; this.init = init; } static json(body, init) { return { body, init }; } }
  const route = load('app/api/geo/route.ts', { 'next/server': { NextResponse } });
  const req = (h) => ({ headers: new Headers(h) });
  assert.equal(JSON.stringify(route.GET(req({ 'cf-ipcountry': 'br' })).body), JSON.stringify({ country: 'BR' }));
  assert.equal(JSON.stringify(route.GET(req({ 'cf-ipcountry': 'XX', 'x-nf-geo': Buffer.from(JSON.stringify({ country: { code: 'IN' } })).toString('base64') })).body), JSON.stringify({ country: 'IN' }));
  assert.equal(JSON.stringify(route.GET(req({})).body), JSON.stringify({ country: null }));
  assert.equal(route.GET(req({ origin: 'https://arvio.tv' })).init.headers['access-control-allow-origin'], 'https://arvio.tv');
  assert.equal(route.GET(req({ origin: 'https://evil.example' })).init.headers['access-control-allow-origin'], undefined);
  assert.equal(route.GET(req({})).init.headers['cache-control'], 'private, no-store');
});

test('the plan card shows the regional price and the discounted link', () => {
  const plans = fs.readFileSync(path.join(__dirname, '../components/shell/PremiumPlans.tsx'), 'utf8');
  assert.match(plans, /regional \? translateUi\("Regional price · save \{value0\} vs\. 12 monthly payments", \{ value0: REGIONAL_YEARLY_SAVING \}\)/);
  assert.match(plans, /<strong>\{regional \? REGIONAL_YEARLY_PRICE : STANDARD_YEARLY_PRICE\}<\/strong>/);
  for (const file of ['PremiumOffer.tsx', 'PremiumAccount.tsx']) {
    assert.match(fs.readFileSync(path.join(__dirname, '../components/shell', file), 'utf8'), /useRegionalYearly\(\);/);
  }
});

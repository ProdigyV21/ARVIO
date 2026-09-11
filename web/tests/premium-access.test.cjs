const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const policy = load('lib/entitlementPolicy.ts');
const now = Date.parse('2026-09-06T10:00:00Z');
const trial = { entitled: true, reason: 'trial', expiresAt: new Date(now + 60000).toISOString(), trialAvailable: false };

test('trial time uses the real server deadline and disappears for expired or paid access', () => {
  assert.equal(policy.trialRemainingLabel(trial, now), 'Trial: 1 minute left');
  assert.equal(policy.trialRemainingLabel(trial, now + 60000), null);
  assert.equal(policy.trialRemainingLabel({ ...trial, reason: 'subscription' }, now), null);
  assert.equal(policy.trialRemainingLabel({ ...trial, expiresAt: 'bad' }, now), null);
  assert.equal(policy.trialRemainingLabel({ ...trial, expiresAt: new Date(now + 72 * 3600000).toISOString() }, now), 'Trial: 3 days left');
  assert.equal(policy.trialRemainingLabel({ ...trial, expiresAt: new Date(now + 2 * 3600000).toISOString() }, now), 'Trial: 2 hours left');
});

test('trial expiry is enforced without reloading the app', () => {
  assert.equal(policy.currentEntitlement(trial, now).entitled, true);
  const expired = policy.currentEntitlement(trial, now + 60000);
  assert.equal(expired.entitled, false);
  assert.equal(expired.reason, 'expired');
  assert.equal(policy.entitlementCheckDelay(trial, now), 60100);
});
test('expired, corrupt and long subscription deadlines cannot overflow timers', () => {
  assert.equal(policy.entitlementCheckDelay(trial, now + 60000), 1000);
  assert.equal(policy.currentEntitlement({ ...trial, expiresAt: 'broken' }, now).entitled, false);
  assert.equal(policy.entitlementCheckDelay({ ...trial, expiresAt: '2099-01-01' }, now), 900000);
  assert.equal(policy.currentEntitlement({ ...trial, reason: 'subscription', expiresAt: null }, now).entitled, true);
  assert.equal(policy.entitlementCheckDelay(null, now), null);
});
function entitlement(disk, request) {
  return load('lib/entitlement.ts', { './storage': disk, './entitlementPolicy': policy, './config': { config: { netlifyBackendUrl: 'https://backend.invalid' } }, './http': { jsonRequest: request, HttpError: class extends Error {} } });
}
test('cached trials cannot grant access beyond their server expiry', () => {
  const disk = storage();
  disk.saveStored('arvio.web.entitlement.v2:a', { at: Date.now(), state: { ...trial, expiresAt: '2000-01-01' } });
  assert.equal(entitlement(disk).cachedEntitlement({ session: { userId: 'a' } }).entitled, false);
});
test('late entitlement response cannot populate the next account cache', async () => {
  const disk = storage(); let finish;
  const auth = { session: { userId: 'a' }, accessToken: async () => 'fixture' };
  const m = entitlement(disk, () => new Promise(resolve => { finish = resolve; }));
  const pending = m.fetchEntitlement(auth);
  await new Promise(resolve => setImmediate(resolve));
  auth.session = { userId: 'b' }; finish(trial); await pending;
  assert.equal(m.cachedEntitlement(auth), null);
  assert.equal(disk.values.size, 0);
});
test('funnel session deduplication is isolated to each account', async () => {
  const values = new Map(); let requests = 0;
  const store = { getItem: key => values.get(key), setItem: (key, value) => values.set(key, value) };
  const m = load('lib/premiumAnalytics.ts', { './config': { config: { netlifyBackendUrl: 'https://backend.invalid' } }, './http': { jsonRequest: async () => { requests++; } } }, { window: { sessionStorage: store, localStorage: store, location: { search: '' } }, document: { referrer: '' } });
  const auth = { session: { userId: 'a' }, accessToken: async () => 'fixture' };
  await m.trackPremiumEvent(auth, 'paywall_view', {}, true);
  await m.trackPremiumEvent(auth, 'paywall_view', {}, true);
  auth.session.userId = 'b'; await m.trackPremiumEvent(auth, 'paywall_view', {}, true);
  assert.equal(requests, 2);
});

test('blocked browser storage cannot break playback analytics or its caller', async () => {
  let requests = 0;
  const browser = { location: { search: '' } };
  for (const key of ['localStorage', 'sessionStorage']) Object.defineProperty(browser, key, { get() { throw Error('Storage blocked'); } });
  const m = load('lib/premiumAnalytics.ts', { './config': { config: { netlifyBackendUrl: 'https://backend.invalid' } }, './http': { jsonRequest: async () => { requests++; } } }, { window: browser, document: { referrer: '' } });
  const auth = { session: { userId: 'a' }, accessToken: async () => 'fixture' };
  assert.equal(await m.trackPremiumEvent(auth, 'download_handoff', {}, true), true);
  assert.equal(requests, 1);
});

test('simultaneous session events coalesce without delaying playback', async () => {
  let requests = 0;
  const m = load('lib/premiumAnalytics.ts', { './config': { config: { netlifyBackendUrl: 'https://backend.invalid' } }, './http': { jsonRequest: async () => { requests++; } } });
  const auth = { session: { userId: 'a' }, accessToken: async () => 'fixture' };
  await Promise.all([m.trackPremiumEvent(auth, 'download_handoff', {}, true), m.trackPremiumEvent(auth, 'download_handoff', {}, true)]);
  assert.equal(requests, 1);
});

test('daily playback diagnostics are bounded, repeat next day, and isolate accounts', async () => {
  const requests = [];
  const m = load('lib/premiumAnalytics.ts', { './config': { config: { paywallEnabled: true, netlifyBackendUrl: 'https://backend.invalid' } }, './http': { jsonRequest: async (_url, init) => { requests.push(JSON.parse(init.body)); } } });
  const auth = { session: { userId: 'a' }, accessToken: async () => 'fixture' };
  const today = new Date('2026-09-08T12:00:00Z');
  await Promise.all(Array.from({length: 30}, () => m.trackPremiumDaily(auth, 'playback_started', {}, today)));
  await m.trackPremiumDaily(auth, 'playback_started', {}, today);
  assert.equal(requests.length, 1);
  await m.trackPremiumDaily(auth, 'playback_started', {}, new Date('2026-09-09T12:00:00Z'));
  assert.equal(requests.length, 2);
  auth.session.userId = 'b';
  await m.trackPremiumDaily(auth, 'playback_started', {}, today);
  assert.equal(requests.length, 3);
});

test('new usage diagnostics do not run on self-hosted instances or signed-out visitors', async () => {
  let requests = 0;
  const m = load('lib/premiumAnalytics.ts', { './config': { config: { paywallEnabled: false } }, './http': { jsonRequest: async () => { requests++; } } });
  await m.trackPremiumDaily({ session: { userId: 'a' } }, 'web_opened');
  await m.trackPremiumDaily({ session: null }, 'web_opened');
  assert.equal(requests, 0);
});

test('failed daily diagnostics can retry and cannot mark a different account as recorded', async () => {
  let fail = true; let requests = 0; let finish;
  const m = load('lib/premiumAnalytics.ts', { './config': { config: { paywallEnabled: true, netlifyBackendUrl: 'https://backend.invalid' } }, './http': { jsonRequest: async () => { requests++; if (fail) throw Error('offline'); if (finish !== false) await new Promise(resolve => { finish = resolve; }); } } });
  const auth = { session: { userId: 'a' }, accessToken: async () => 'fixture' };
  assert.equal(await m.trackPremiumDaily(auth, 'web_opened'), false);
  fail = false;
  const pending = m.trackPremiumDaily(auth, 'web_opened');
  await new Promise(resolve => setImmediate(resolve));
  auth.session.userId = 'b'; finish(); await pending;
  auth.session.userId = 'a'; finish = false;
  await m.trackPremiumDaily(auth, 'web_opened');
  assert.equal(requests, 3);
});

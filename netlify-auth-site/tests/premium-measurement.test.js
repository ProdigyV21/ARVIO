const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const vm = require('node:vm');
const crypto = require('node:crypto');
process.env.ARVIO_AUTH_SECRET = 'test-only-secret-that-is-longer-than-32-bytes';
const measurement = require('../netlify/functions/_premium-measurement');
const funnel = require('../netlify/functions/_premium-funnel');

const journeyId = 'a1101111-2222-4333-8444-555566667777';
const journeyKey = measurement.journeyKey(journeyId);

test('guide funnel reports use bounded landing labels and never treat a click as a subscription', () => {
  const landing = { journeyKey, eventName: 'guide_page_view', firstAt: '2026-10-08T10:00:00Z', metadata: { page: 'jellyfin', source: 'google' } };
  const click = { ...landing, eventName: 'membership_clicked', firstAt: '2026-10-08T10:01:00Z', metadata: { page: 'premium', source: 'google' } };
  const report = measurement.summarizeMeasurements([], [click, landing]);
  assert.equal(report.journeys.guidePageViews, 1);
  assert.equal(report.journeys.byLandingPage.jellyfin.membershipClicks, 1);
  assert.equal(report.journeys.bySource.google.confirmedNewMemberships, 0);
  assert.equal(report.journeys.byLandingPage.premium, undefined);
  assert.deepEqual(measurement.marketingMetadata({ page: '/private/path?email=user@example.test' }), {});
});

test('a retained earlier authenticated journey explains a later payment without increasing period visits', () => {
  const contextJourneys = [{ journeyKey, eventName: 'premium_page_view', firstAt: '2026-10-01T10:00:00Z', metadata: { source: 'google', page: 'premium' } }];
  const contextRecords = [{ accountKey: 'buyer', eventName: 'journey_connected', firstAt: '2026-10-01T11:00:00Z', metadata: { journey_key: journeyKey } }];
  const records = [{ accountKey: 'buyer', eventName: 'subscription_started', firstAt: '2026-10-10T12:00:00Z' }];
  const result = measurement.summarizeMeasurements(records, [], [], { contextRecords, contextJourneys });
  assert.equal(result.journeys.journeys, 0);
  assert.equal(result.journeys.premiumPageViews, 0);
  assert.equal(result.journeys.connectedAccounts, 0);
  assert.equal(result.journeys.confirmedNewMemberships, 1);
  assert.equal(result.journeys.bySource.google.confirmedNewMemberships, 1);
  assert.equal(result.paymentActivation.confirmedNewMemberships, 1);
});

test('earlier clicks cannot inflate period events, attribute anonymous sales, or override an ambiguous owner', () => {
  const earlier = { journeyKey, eventName: 'membership_clicked', firstAt: '2026-10-01T10:00:00Z', metadata: { source: 'google' } };
  const contextRecords = ['buyer', 'other'].map(accountKey => ({ accountKey, eventName: 'journey_connected', firstAt: '2026-10-01T11:00:00Z', metadata: { journey_key: journeyKey } }));
  const sale = { accountKey: 'buyer', eventName: 'subscription_started', firstAt: '2026-10-10T12:00:00Z' };
  const current = { ...earlier, eventName: 'premium_page_view', firstAt: '2026-10-10T10:00:00Z' };
  const result = measurement.summarizeMeasurements([sale], [current], [], { contextRecords, contextJourneys: [earlier] });
  assert.equal(result.journeys.membershipClicks, 0);
  assert.equal(result.journeys.premiumPageViews, 1);
  assert.equal(result.journeys.confirmedNewMemberships, 0);
  assert.equal(result.journeys.confirmedPaymentsNotMatchedToJourney, 1);
});

test('short reports read only bounded past attribution context and never count future sales', async () => {
  const store = memoryStore();
  const put = (date, accountKey, eventName, metadata = {}) => store.data.set(`events/date/${date}/account/${accountKey}/${eventName}.json`, { date, accountKey, eventName: eventName === '_journey' ? 'journey_connected' : eventName, metadata, firstAt: `${date}T12:00:00Z` });
  const visit = (date, key) => store.data.set(`journeys/date/${date}/${key}/premium_page_view.json`, { date, journeyKey: key, eventName: 'premium_page_view', firstAt: `${date}T10:00:00Z`, metadata: { source: 'google' } });
  put('2026-10-01', 'buyer', '_journey', { journey_key: journeyKey }); visit('2026-10-01', journeyKey);
  put('2026-10-10', 'buyer', 'subscription_started'); put('2026-10-13', 'future', 'subscription_started');
  const oldKey = 'f'.repeat(64);
  put('2026-08-01', 'old', '_journey', { journey_key: oldKey }); visit('2026-08-01', oldKey); put('2026-10-10', 'old', 'subscription_started');
  const module = fixtureModule('_premium-funnel', { '@netlify/blobs': { connectLambda() {}, getStore: () => store }, './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement });
  const report = await module.premiumFunnelReport({}, 5, '2026-10-12');
  assert.equal(report.paymentActivation.confirmedNewMemberships, 2);
  assert.equal(report.journeys.confirmedNewMemberships, 1);
  assert.equal(report.journeys.premiumPageViews, 0);
  assert.equal(report.journeys.confirmedPaymentsNotMatchedToJourney, 1);
  assert.equal(report.attributionLookback.contextStart, '2026-09-08');
});

test('checkout recovery separates returns, checks, and server payments', () => {
  const record = (eventName, time) => ({ accountKey: 'buyer', eventName, firstAt: `2026-10-10T${time}:00Z` });
  const report = measurement.summarizeMeasurements([
    record('checkout_opened', '10:00'), record('checkout_returned', '10:01'), record('payment_status_checked', '10:02')
  ], []);
  assert.equal(report.checkoutRecovery.returnedAfterClick, 1);
  assert.equal(report.checkoutRecovery.checkedStatusAfterReturn, 1);
  assert.equal(report.checkoutRecovery.confirmedPaymentAfterClick, 0);
  assert.equal(report.paymentActivation.confirmedNewMemberships, 0);
  assert.equal(funnel.CLIENT_PREMIUM_EVENTS.has('subscription_started'), false);
});

test('guide events retain existing deduplication and write ceilings', async () => {
  const store = memoryStore();
  const now = new Date('2026-10-08T12:00:00Z');
  const event = { headers: { 'x-nf-client-connection-ip': '192.0.2.10' } };
  const body = { event_name: 'guide_page_view', journey_id: journeyId, metadata: { page: 'jellyfin', source: 'google' } };
  assert.equal((await measurement.recordJourneyEvent(store, event, body, now)).status, 200);
  const writes = store.writes();
  assert.equal((await measurement.recordJourneyEvent(store, event, body, now)).status, 200);
  assert.equal(store.writes(), writes);
});
function memoryStore() {
  const data = new Map(), versions = new Map(); let writes = 0;
  return { data, writes: () => writes,
    get: async key => data.get(key) || null,
    getWithMetadata: async key => data.has(key) ? { data: data.get(key), etag: String(versions.get(key) || 0) } : null,
    setJSON: async (key, value, options) => {
      if (options?.onlyIfNew && data.has(key)) return { modified: false };
      if (options?.onlyIfMatch !== undefined && (!data.has(key) || options.onlyIfMatch !== String(versions.get(key) || 0))) return { modified: false };
      writes++; data.set(key, value); versions.set(key, (versions.get(key) || 0) + 1); return { modified: true };
    },
    list: async ({ prefix }) => ({ blobs: [...data.keys()].filter(key => key.startsWith(prefix)).map(key => ({ key })) }),
    delete: async key => { data.delete(key); }
  };
}
function fixtureModule(name, dependencies) {
  const module = { exports: {} };
  vm.runInNewContext(fs.readFileSync(require.resolve(`../netlify/functions/${name}`), 'utf8'), {
    module, exports: module.exports, console, Date, Set, Map, URLSearchParams, Buffer,
    require: name => { if (name in dependencies) return dependencies[name]; throw Error(`Missing mock ${name}`); }
  });
  return module.exports;
}

test('journeys are keyed hashes and metadata rejects URLs, addresses and unknown fields', () => {
  assert.match(journeyKey, /^[a-f0-9]{64}$/);
  assert.notEqual(journeyKey, journeyId);
  assert.equal(measurement.journeyKey('person@example.test'), null);
  assert.deepEqual(measurement.marketingMetadata({ source: 'Discord', campaign: 'launch', content: 'person@example.test', page: 'premium', token: 'secret', url: 'https://private/' }), { source: 'discord', campaign: 'launch', page: 'premium' });
  assert.deepEqual(funnel._test.sanitizeMetadata({ failure_kind: 'network', transport: 'hls', phase: 'startup', token: 'private', url: 'https://private', referrer: 'person@example.test', provider: 'valid', email: 'secret', arbitrary: 'private' }), { failure_kind: 'network', transport: 'hls', phase: 'startup', provider: 'valid' });
  assert.deepEqual(funnel._test.sanitizeMetadata({ failure_kind: 'raw-error-url', transport: 'unknown-provider', phase: 'unexpected' }), {});
});

test('anonymous endpoint rejects untrusted origins, malformed and oversized payloads before storage', async () => {
  let records = 0;
  const handler = fixtureModule('premium-funnel-visit', {
    './_backend': { parseBody: event => JSON.parse(event.body) },
    './_premium-funnel': { premiumFunnelStore: () => ({}) },
    './_premium-measurement': { recordJourneyEvent: async () => { records++; return { status: 200 }; } }
  }).handler;
  const valid = { httpMethod: 'POST', headers: { origin: 'https://arvio.tv' }, body: '{}' };
  assert.equal((await handler({ ...valid, headers: { origin: 'https://evil.invalid' } })).statusCode, 403);
  assert.equal((await handler({ ...valid, body: 'x'.repeat(4097) })).statusCode, 413);
  assert.equal((await handler({ ...valid, body: '{' })).statusCode, 400);
  assert.equal(records, 0);
  const result = await handler(valid);
  assert.equal(result.statusCode, 200);
  assert.equal(result.headers['access-control-allow-origin'], 'https://arvio.tv');
  assert.equal(records, 1);
});

test('anonymous records deduplicate and are bounded per network and globally', async () => {
  const store = memoryStore();
  const now = new Date('2026-09-22T12:00:00Z');
  const event = { headers: { 'x-nf-client-connection-ip': '192.0.2.10' } };
  const body = { event_name: 'premium_page_view', journey_id: journeyId, metadata: { source: 'discord' } };
  assert.equal((await measurement.recordJourneyEvent(store, event, body, now)).status, 200);
  const writes = store.writes();
  assert.equal((await measurement.recordJourneyEvent(store, event, body, now)).status, 200);
  assert.equal(store.writes(), writes);
  for (let i = 0; i < 2; i++) assert.equal((await measurement.recordJourneyEvent(store, event, { ...body, journey_id: crypto.randomUUID() }, now)).status, 200);
  assert.equal((await measurement.recordJourneyEvent(store, event, { ...body, journey_id: crypto.randomUUID() }, now)).status, 429);
  for (let i = 0; i < 400; i++) await measurement.recordJourneyEvent(store, { headers: { 'x-nf-client-connection-ip': `192.0.2.${i + 20}` } }, { ...body, journey_id: crypto.randomUUID() }, now);
  const stored = [...store.data.entries()].filter(([key]) => key.startsWith('journeys/'));
  assert.ok(stored.length <= 256);
  assert.ok(stored.length > 100);
  assert.equal(JSON.stringify([...store.data]).includes('192.0.2.'), false);
  assert.equal(JSON.stringify(stored).includes(journeyId), false);
});

test('report follows a measured journey through verified payment to observed access without attributing unrelated sales', () => {
  const record = (eventName, time, accountKey = 'account-a', metadata = {}) => ({ eventName, accountKey, date: '2026-09-22', firstAt: `2026-09-22T${time}:00Z`, metadata });
  const journeys = [
    { journeyKey, eventName: 'premium_page_view', firstAt: '2026-09-22T12:00:00Z', metadata: { source: 'discord' } },
    { journeyKey, eventName: 'web_clicked', firstAt: '2026-09-22T12:01:00Z', metadata: { source: 'discord' } }
  ];
  const report = measurement.summarizeMeasurements([
    record('paywall_view', '12:02', 'account-a', { journey_key: journeyKey }),
    record('trial_started', '12:03'), record('checkout_opened', '12:04'),
    record('subscription_started', '12:05', 'billing-a'), record('paid_access_observed', '12:06'),
    record('subscription_started', '12:08', 'unattributed'),
    record('playback_failed', '12:09', 'account-a', { failure_kind: 'network', transport: 'hls', phase: 'startup' })
  ], journeys, [{ billingKey: 'billing-a', accountKey: 'account-a' }]);
  assert.equal(report.journeys.bySource.discord.trialsStarted, 1);
  assert.equal(report.journeys.confirmedNewMemberships, 1);
  assert.equal(report.journeys.confirmedPaymentsNotMatchedToJourney, 1);
  assert.deepEqual(report.paymentActivation, { confirmedNewMemberships: 2, paidAccessObservedAfterPayment: 1, accessNotYetObservedInWindow: 1, paymentAfterMembershipPageClick: 1 });
  assert.equal(report.playbackDiagnostics.unit, 'account-event-day');
  assert.equal(report.playbackDiagnostics.failureKind.network, 1);
  assert.equal('failureRate' in report.playbackDiagnostics, false);
});

test('shared journeys and ambiguous billing identities cannot multiply attribution', () => {
  const journeys = [{ journeyKey, eventName: 'premium_page_view', firstAt: '2026-09-22T10:00:00Z', metadata: { source: 'discord' } }];
  const records = ['a', 'b'].map(accountKey => ({ accountKey, eventName: 'paywall_view', firstAt: '2026-09-22T11:00:00Z', metadata: { journey_key: journeyKey } }));
  records.push({ accountKey: 'billing', eventName: 'subscription_started', firstAt: '2026-09-22T12:00:00Z' });
  const result = measurement.summarizeMeasurements(records, journeys, [{ billingKey: 'billing', accountKey: 'a' }, { billingKey: 'billing', accountKey: 'b' }]);
  assert.equal(result.journeys.connectedAccounts, 0);
  assert.equal(result.journeys.confirmedNewMemberships, 0);
  assert.equal(result.paymentActivation.confirmedNewMemberships, 1);
});

test('cleanup expires anonymous journeys and network budgets sooner than account diagnostics', async () => {
  const store = memoryStore();
  const old = new Date(Date.now() - 35 * 86400000).toISOString().slice(0, 10);
  store.data.set(`events/date/${old}/account/a/trial_started.json`, {});
  store.data.set(`journeys/date/${old}/a/premium_page_view.json`, {});
  store.data.set(`visit-budget/date/${old}/a/1.json`, {});
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  assert.equal(await module.cleanupPremiumFunnel({}), 2);
  assert.equal(store.data.size, 1);
  assert.match([...store.data.keys()][0], /^events/);
});

test('server paid-access observation cannot be manufactured by browser events', async () => {
  assert.equal(funnel.CLIENT_PREMIUM_EVENTS.has('paid_access_observed'), false);
  let recorded = 0;
  const entitlement = { entitled: true, reason: 'subscription', source: 'kofi' };
  const handler = fixtureModule('entitlement-status', {
    './_backend': { json: (statusCode, body) => ({ statusCode, body }), options: () => null, resolveIdentity: async () => ({ email: 'customer@example.test' }), normalizeEmail: value => value, sha256: value => value },
    './_entitlements': { entitlementsStore: () => ({}), readEntitlement: async () => ({}), evaluateEntitlement: () => entitlement },
    './_premium-funnel': { recordPremiumEvent: async (_event, body) => { assert.equal(body.eventName, 'paid_access_observed'); recorded++; throw Error('analytics offline'); } },
    './_trial-emails': { queueTrialEmails: async () => {} }
  }).handler;
  const result = await handler({ httpMethod: 'GET' });
  assert.equal(result.statusCode, 200);
  assert.equal(result.body.entitled, true);
  assert.equal(recorded, 1);
});

test('complete historical report dates cannot include a partial current day', () => {
  assert.deepEqual(funnel._test.dayRange(5, '2026-09-21'), ['2026-09-17', '2026-09-18', '2026-09-19', '2026-09-20', '2026-09-21']);
});

test('historical reports reuse earlier verified billing ownership and stop at the selected end date', async () => {
  const store = memoryStore();
  const cloud = 'a'.repeat(64), billing = 'b'.repeat(64);
  const put = (date, accountKey, eventName, metadata = {}) => {
    store.data.set(`events/date/${date}/account/${accountKey}/${eventName}.json`, { date, accountKey, eventName, metadata, firstAt: `${date}T12:00:00Z` });
  };
  put('2026-09-01', cloud, 'billing_email_verified', { billing_key: billing });
  put('2026-09-18', cloud, 'trial_started');
  put('2026-09-19', billing, 'subscription_started');
  put('2026-09-20', cloud, 'paid_access_observed');
  put('2026-09-22', 'c'.repeat(64), 'subscription_started');
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  const report = await module.premiumFunnelReport({}, 5, '2026-09-21');
  assert.equal(report.periodStart, '2026-09-17');
  assert.equal(report.periodEnd, '2026-09-21');
  assert.equal(report.includesPartialToday, false);
  assert.equal(report.trialCohort.paidByReportEnd, 1);
  assert.equal(report.paymentActivation.confirmedNewMemberships, 1);
  assert.equal(report.paymentActivation.paidAccessObservedAfterPayment, 1);
  await assert.rejects(module.premiumFunnelReport({}, 5, '2026-02-31'), error => error.statusCode === 400);
  await assert.rejects(module.premiumFunnelReport({}, 5, 'bad'), error => error.statusCode === 400);
});

test('a later authenticated journey and paid access are observed even after earlier events in the same report', () => {
  const records = [
    { accountKey: 'a', eventName: 'web_opened', firstAt: '2026-09-21T10:00:00Z' },
    { accountKey: 'a', eventName: 'paid_access_observed', firstAt: '2026-09-21T10:00:00Z' },
    { accountKey: 'a', eventName: 'journey_connected', firstAt: '2026-09-22T10:01:00Z', metadata: { journey_key: journeyKey } },
    { accountKey: 'a', eventName: 'subscription_started', firstAt: '2026-09-22T10:03:00Z' },
    { accountKey: 'a', eventName: 'paid_access_observed', firstAt: '2026-09-22T10:04:00Z' }
  ];
  const result = measurement.summarizeMeasurements(records, [{ journeyKey, eventName: 'premium_page_view', firstAt: '2026-09-22T10:00:00Z', metadata: { source: 'discord' } }]);
  assert.equal(result.journeys.connectedAccounts, 1);
  assert.equal(result.paymentActivation.paidAccessObservedAfterPayment, 1);
});

test('arbitrary journey IDs cannot create more than one account attribution link per day', async () => {
  const store = memoryStore();
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  for (let i = 0; i < 20; i++) await module.recordPremiumEvent({}, { email: 'fixture@example.test', eventName: 'paywall_view', journeyId: crypto.randomUUID() });
  assert.equal(store.data.size, 2);
  assert.equal(store.writes(), 2);
});

test('anonymous visits support Lambda contexts without uncachedEdgeURL while preserving atomic dedup', async () => {
  const store = memoryStore();
  const read = store.get;
  store.get = async (key, options) => {
    if (options?.consistency === 'strong') throw Error("Netlify Blobs has not been configured with an 'uncachedEdgeURL' property");
    return read(key);
  };
  const event = { headers: { 'x-nf-client-connection-ip': '192.0.2.10' } };
  const body = { event_name: 'premium_page_view', journey_id: journeyId, metadata: { source: 'discord' } };
  assert.equal((await measurement.recordJourneyEvent(store, event, body)).status, 200);
  const writes = store.writes();
  assert.equal((await measurement.recordJourneyEvent(store, event, body)).status, 200);
  assert.equal(store.writes(), writes);
  store.get = async () => { throw Error('unrelated storage outage'); };
  await assert.rejects(measurement.recordJourneyEvent(store, event, body), /unrelated storage outage/);
});

test('direct checkout stages count unique accounts, retain bounded labels, and never imply payments', () => {
  const record = (eventName, accountKey = 'buyer', metadata = {}) => ({ accountKey, eventName, firstAt: '2026-10-19T12:00:00Z', metadata });
  const report = measurement.summarizeMeasurements([
    record('membership_cta_viewed', 'buyer', { entry: 'trial_nav', flow_version: 'direct_v2' }),
    record('membership_cta_viewed', 'buyer', { entry: 'paywall', flow_version: 'direct_v2' }),
    record('checkout_opened', 'buyer', { entry: 'paywall', flow_version: 'direct_v2' }),
    record('checkout_recovery_opened', 'buyer', { entry: 'paywall', flow_version: 'direct_v2' }),
    record('checkout_recovery_closed', 'buyer', { entry: 'paywall', flow_version: 'direct_v2' }),
    record('membership_linked'), record('membership_link_failed', 'other'),
    record('checkout_opened', 'old', { entry: '__proto__', flow_version: 'anything-private' })
  ], []);
  const result = report.checkoutDiagnostics;
  assert.equal(result.ctaViewed, 1);
  assert.equal(result.membershipLinkOpened, 2);
  assert.equal(result.recoveryOpened, 1);
  assert.equal(result.recoveryClosed, 1);
  assert.equal(result.billingLinkReportedComplete, 1);
  assert.equal(result.billingOwnershipVerified, 0);
  assert.equal(result.byEntry.trial_nav.ctaViewed, 1);
  assert.equal(result.byEntry.paywall.ctaViewed, 1);
  assert.equal(result.byEntry.unspecified.membershipLinkOpened, 1);
  assert.equal(result.byFlowVersion.direct_v2.membershipLinkOpened, 1);
  assert.equal(result.byFlowVersion.unversioned.membershipLinkOpened, 1);
  assert.equal(report.paymentActivation.confirmedNewMemberships, 0);
  assert.match(result.note, /not a sequential funnel or abandonment rate/);
  assert.match(result.note, /Missing historical events mean unmeasured/);
  assert.deepEqual(funnel._test.sanitizeMetadata({ flow_version: 'direct_v2' }), { flow_version: 'direct_v2' });
  for (const flow_version of ['private-person', 'https://private', 'buyer@example.test', 1]) assert.deepEqual(funnel._test.sanitizeMetadata({ flow_version }), {});
});

test('checkout recovery requires an observed click then return then check, not unrelated earlier activity', () => {
  const record = (eventName, time) => ({ accountKey: 'buyer', eventName, firstAt: `2026-10-19T${time}:00Z` });
  const early = [record('checkout_returned', '10:00'), record('payment_status_checked', '10:01'), record('checkout_opened', '11:00')];
  const before = measurement.summarizeMeasurements(early, []).checkoutRecovery;
  assert.equal(before.accountsWithMembershipClick, 1);
  assert.equal(before.returnedAfterClick, 0);
  assert.equal(before.checkedStatusAfterReturn, 0);
  const after = measurement.summarizeMeasurements([...early, record('checkout_returned', '11:01'), record('payment_status_checked', '11:02')], []).checkoutRecovery;
  assert.equal(after.returnedAfterClick, 1);
  assert.equal(after.checkedStatusAfterReturn, 1);
});

test('a post-payment journey connection cannot retroactively attribute a sale', () => {
  const records = [
    { accountKey: 'buyer', eventName: 'subscription_started', firstAt: '2026-10-19T11:00:00Z' },
    { accountKey: 'buyer', eventName: 'journey_connected', firstAt: '2026-10-19T12:00:00Z', metadata: { journey_key: journeyKey } }
  ];
  const result = measurement.summarizeMeasurements(records, [{ journeyKey, eventName: 'premium_page_view', firstAt: '2026-10-19T10:00:00Z', metadata: { source: 'google' } }]);
  assert.equal(result.journeys.connectedAccounts, 1);
  assert.equal(result.journeys.confirmedNewMemberships, 0);
  assert.equal(result.journeys.confirmedPaymentsNotMatchedToJourney, 1);
});

test('an ineligible earlier journey cannot swallow a later eligible payment attribution', () => {
  const otherKey = 'f'.repeat(64);
  const connected = (journey_key, firstAt) => ({ accountKey: 'buyer', eventName: 'journey_connected', firstAt, metadata: { journey_key } });
  const result = measurement.summarizeMeasurements([
    connected(journeyKey, '2026-10-19T12:00:00Z'), connected(otherKey, '2026-10-19T10:05:00Z'),
    { accountKey: 'buyer', eventName: 'subscription_started', firstAt: '2026-10-19T11:00:00Z' }
  ], [
    { journeyKey, eventName: 'premium_page_view', firstAt: '2026-10-19T09:00:00Z', metadata: { source: 'google' } },
    { journeyKey: otherKey, eventName: 'premium_page_view', firstAt: '2026-10-19T10:00:00Z', metadata: { source: 'discord' } }
  ]);
  assert.equal(result.journeys.confirmedNewMemberships, 1);
  assert.equal(result.journeys.bySource.google.confirmedNewMemberships, 0);
  assert.equal(result.journeys.bySource.discord.confirmedNewMemberships, 1);
});

test('pending then active and error status outcomes survive daily dedup with bounded atomic updates', async () => {
  const store = memoryStore();
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  const body = { email: 'buyer@example.test', eventName: 'payment_status_checked', occurredAt: '2026-10-19T10:00:00Z', metadata: { entry: 'paywall', flow_version: 'direct_v2', status: 'pending' } };
  await module.recordPremiumEvent({}, body);
  await Promise.all(['active', 'error'].map((status, index) => module.recordPremiumEvent({}, { ...body, occurredAt: `2026-10-19T10:0${index + 1}:00Z`, metadata: { entry: 'trial_nav', flow_version: 'direct_v2', status } })));
  for (let index = 0; index < 20; index++) await module.recordPremiumEvent({}, { ...body, metadata: { status: ['pending', 'active', 'error'][index % 3] } });
  assert.equal(store.data.size, 1);
  assert.equal(store.writes(), 3);
  const record = [...store.data.values()][0];
  assert.equal(record.firstAt, '2026-10-19T10:00:00.000Z');
  assert.equal(record.metadata.status, 'pending');
  assert.equal(record.metadata.entry, 'paywall');
  assert.deepEqual(Object.keys(record.observedStatuses).sort(), ['active', 'error', 'pending']);
  const result = measurement.summarizeMeasurements([record], []);
  assert.deepEqual(result.checkoutDiagnostics.browserReportedStatusResults, { pending: 1, active: 1, error: 1 });
  assert.equal(result.checkoutDiagnostics.statusChecked, 1);
  assert.equal(result.paymentActivation.confirmedNewMemberships, 0);
});

test('concurrent duplicate checkout events preserve the first stored timestamp and placement', async () => {
  const store = memoryStore();
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  await Promise.all(Array.from({ length: 20 }, (_, index) => module.recordPremiumEvent({}, {
    email: 'buyer@example.test', eventName: 'checkout_opened', metadata: { entry: index ? 'trial_nav' : 'paywall' }, occurredAt: `2026-10-19T10:00:${String(index).padStart(2, '0')}Z`
  })));
  assert.equal(store.data.size, 1);
  assert.equal(store.writes(), 1);
  const record = [...store.data.values()][0];
  assert.equal(record.firstAt, '2026-10-19T10:00:00.000Z');
  assert.equal(record.metadata.entry, 'paywall');
});

test('status outcomes from historical records are not invented or treated as confirmed payments', () => {
  const result = measurement.summarizeMeasurements([
    { accountKey: 'buyer', eventName: 'payment_status_checked', firstAt: '2026-10-18T12:00:00Z', metadata: { status: 'pending' } },
    { accountKey: 'other', eventName: 'payment_status_checked', firstAt: '2026-10-18T12:00:00Z', metadata: { status: 'active' } }
  ], []);
  assert.deepEqual(result.checkoutDiagnostics.browserReportedStatusResults, { pending: 1, active: 1, error: 0 });
  assert.equal(result.paymentActivation.confirmedNewMemberships, 0);
  assert.equal(result.checkoutDiagnostics.ctaViewed, 0);
});

test('a later retained status outcome can establish a check after return without inventing another click', () => {
  const record = (eventName, firstAt, extra = {}) => ({ accountKey: 'buyer', eventName, firstAt, ...extra });
  const result = measurement.summarizeMeasurements([
    record('payment_status_checked', '2026-10-19T10:00:00Z', { metadata: { status: 'pending' }, observedStatuses: { pending: '2026-10-19T10:00:00Z', active: '2026-10-19T11:02:00Z' } }),
    record('checkout_opened', '2026-10-19T11:00:00Z'), record('checkout_returned', '2026-10-19T11:01:00Z')
  ], []);
  assert.equal(result.checkoutDiagnostics.statusChecked, 1);
  assert.equal(result.checkoutRecovery.checkedStatusAfterReturn, 1);
  assert.equal(result.paymentActivation.confirmedNewMemberships, 0);
});

test('status updates tolerate missing uncached endpoints and stop retrying ETag contention', async () => {
  const store = memoryStore();
  const read = store.getWithMetadata;
  store.getWithMetadata = async (key, options) => {
    if (options?.consistency === 'strong') throw Error("Netlify Blobs has not been configured with an 'uncachedEdgeURL' property");
    return read(key);
  };
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  const body = { email: 'buyer@example.test', eventName: 'payment_status_checked', occurredAt: '2026-10-19T10:00:00Z', metadata: { status: 'pending' } };
  await module.recordPremiumEvent({}, body);
  await module.recordPremiumEvent({}, { ...body, metadata: { status: 'active' } });
  assert.equal(store.writes(), 2);
  let conflicts = 0;
  const write = store.setJSON;
  store.setJSON = async (key, value, options) => {
    if (options?.onlyIfMatch !== undefined) { conflicts++; return { modified: false }; }
    return write(key, value, options);
  };
  await module.recordPremiumEvent({}, { ...body, metadata: { status: 'error' } });
  assert.equal(conflicts, 3);
  assert.equal(store.writes(), 2);
});

test('authenticated funnel endpoint accepts bounded diagnostics but rejects fabricated server payments and identities', async () => {
  const events = [];
  const handler = fixtureModule('premium-funnel-event', {
    './_backend': {
      json: (statusCode, body) => ({ statusCode, body }), options: () => null,
      parseBody: event => JSON.parse(event.body),
      resolveIdentity: async event => { if (!event.authenticated) throw Object.assign(Error('unauthorized'), { statusCode: 401 }); return { email: 'verified@example.test', supabaseUserId: 'real-account' }; }
    },
    './_premium-funnel': { CLIENT_PREMIUM_EVENTS: funnel.CLIENT_PREMIUM_EVENTS, recordPremiumEvent: async (_event, body) => events.push(body) }
  }).handler;
  const request = event_name => ({ httpMethod: 'POST', authenticated: true, body: JSON.stringify({ event_name, email: 'forged@example.test', accountId: 'forged', occurredAt: '2000-01-01', metadata: { flow_version: 'direct_v2' } }) });
  for (const event_name of ['membership_cta_viewed', 'checkout_opened', 'checkout_recovery_opened', 'checkout_recovery_closed', 'payment_status_checked']) assert.equal((await handler(request(event_name))).statusCode, 200);
  assert.equal(events.length, 5);
  assert.equal(events[0].email, 'verified@example.test');
  assert.equal(events[0].accountId, 'real-account');
  assert.equal(events[0].occurredAt, undefined);
  for (const event_name of ['subscription_started', 'subscription_renewed', 'paid_access_observed', 'billing_email_verified', 'trial_started']) assert.equal((await handler(request(event_name))).statusCode, 400);
  assert.equal((await handler({ ...request('checkout_opened'), authenticated: false })).statusCode, 401);
  assert.equal(events.length, 5);
});

test('protected report includes new checkout detail records without counting future events or anonymous payment guesses', async () => {
  const store = memoryStore();
  for (const [date, eventName] of [['2026-10-18', 'membership_cta_viewed'], ['2026-10-18', 'checkout_recovery_opened'], ['2026-10-18', 'checkout_recovery_closed'], ['2026-10-19', 'checkout_opened']]) {
    store.data.set(`events/date/${date}/account/buyer/${eventName}.json`, { date, accountKey: 'buyer', eventName, firstAt: `${date}T12:00:00Z`, metadata: { entry: 'paywall', flow_version: 'direct_v2' } });
  }
  const module = fixtureModule('_premium-funnel', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_namespace, value) => value }, './_premium-measurement': measurement
  });
  const report = await module.premiumFunnelReport({}, 1, '2026-10-18');
  assert.equal(report.checkoutDiagnostics.ctaViewed, 1);
  assert.equal(report.checkoutDiagnostics.recoveryOpened, 1);
  assert.equal(report.checkoutDiagnostics.recoveryClosed, 1);
  assert.equal(report.checkoutDiagnostics.membershipLinkOpened, 0);
  assert.equal(report.paymentActivation.confirmedNewMemberships, 0);
  assert.equal(report.uniqueAccounts.membership_cta_viewed, 1);
});

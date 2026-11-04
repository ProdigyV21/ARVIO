const test = require('node:test');
const assert = require('node:assert/strict');

// In-memory Netlify Blobs with the parts of the API these modules use.
const stores = new Map();
function memoryStore() {
  const data = new Map();
  let version = 0;
  const etags = new Map();
  const clone = (value) => (value === undefined ? undefined : structuredClone(value));
  return {
    data,
    async get(key) { return data.has(key) ? clone(data.get(key)) : null; },
    async getWithMetadata(key) { return data.has(key) ? { data: clone(data.get(key)), etag: etags.get(key) ?? 'e0' } : null; },
    async setJSON(key, value, opts = {}) {
      if (opts.onlyIfNew && data.has(key)) return { modified: false };
      if (opts.onlyIfMatch && (etags.get(key) ?? (data.has(key) ? 'e0' : undefined)) !== opts.onlyIfMatch) return { modified: false };
      data.set(key, clone(value)); etags.set(key, `e${++version}`);
      return { modified: true };
    },
    async delete(key) { data.delete(key); etags.delete(key); },
    async list({ prefix = '' } = {}) { return { blobs: [...data.keys()].filter((k) => k.startsWith(prefix)).map((key) => ({ key })) }; }
  };
}
function store(name) { if (!stores.has(name)) stores.set(name, memoryStore()); return stores.get(name); }
require.cache[require.resolve('@netlify/blobs')] = { id: '@netlify/blobs', filename: '@netlify/blobs', loaded: true, exports: { connectLambda() {}, getStore: (name) => store(name) } };

process.env.ARVIO_AUTH_SECRET = 'x'.repeat(40);
process.env.KOFI_VERIFICATION_TOKEN = 'kofi-token';
process.env.KOFI_YEARLY_ITEM_CODES = 'abc123def, other999';
process.env.KOFI_YEARLY_URL = 'https://ko-fi.com/s/abc123def';
process.env.STREAM_TICKET_SECRET = 's'.repeat(40);

const { evaluateEntitlement, buildPaidRecord, addMonths, DAY_MS } = require('../netlify/functions/_entitlements');
const webhook = require('../netlify/functions/kofi-webhook');
const referrals = require('../netlify/functions/_referrals');
const memberEmails = require('../netlify/functions/_member-emails');
const streamTicket = require('../netlify/functions/stream-ticket');
const exitSurvey = require('../netlify/functions/premium-exit-survey');
const { sha256 } = require('../netlify/functions/_backend');

const NOW = Date.parse('2026-11-01T12:00:00Z');
const reset = () => stores.clear();
const entitlement = (email) => store('entitlements').data.get(`email/${sha256(email)}.json`);
const kofi = (data) => webhook.handler({ httpMethod: 'POST', body: new URLSearchParams({ data: JSON.stringify({ verification_token: 'kofi-token', ...data }) }).toString() });

test('paid time stacks on time still ahead and never throws days away', () => {
  const active = { status: 'active', source: 'kofi', startedAt: '2026-10-01T00:00:00Z', expiresAt: new Date(NOW + 10 * DAY_MS).toISOString() };
  assert.equal(buildPaidRecord(active, { days: 31, now: NOW }).expiresAt, new Date(NOW + 41 * DAY_MS).toISOString());
  const lapsed = { ...active, expiresAt: new Date(NOW - 5 * DAY_MS).toISOString() };
  assert.equal(buildPaidRecord(lapsed, { days: 31, now: NOW }).expiresAt, new Date(NOW + 31 * DAY_MS).toISOString());
  assert.equal(buildPaidRecord({ ...active, expiresAt: null }, { days: 31, now: NOW }).expiresAt, null, 'lifetime stays lifetime');
  const trial = { status: 'active', source: 'trial', expiresAt: new Date(NOW + 2 * DAY_MS).toISOString(), startedAt: '2026-10-30T00:00:00Z' };
  const upgraded = buildPaidRecord(trial, { days: 31, now: NOW });
  assert.equal(upgraded.expiresAt, new Date(NOW + 31 * DAY_MS).toISOString(), 'a legacy trial deadline is not paid time');
  assert.equal(upgraded.startedAt, new Date(NOW).toISOString());
});

test('a late renewal keeps access for 3 days of grace, then access ends', () => {
  const record = (daysAgo) => ({ status: 'active', source: 'kofi', startedAt: '2026-09-01T00:00:00Z', expiresAt: new Date(NOW - daysAgo * DAY_MS).toISOString() });
  const grace = evaluateEntitlement(record(1), NOW);
  assert.equal(grace.entitled, true);
  assert.equal(grace.reason, 'grace');
  assert.equal(grace.graceEndsAt, new Date(NOW + 2 * DAY_MS).toISOString());
  assert.equal(evaluateEntitlement(record(4), NOW).entitled, false);
  assert.equal(evaluateEntitlement(record(4), NOW).everPaid, true);
  assert.equal(evaluateEntitlement({ status: 'active', source: 'trial', expiresAt: new Date(NOW - DAY_MS).toISOString() }, NOW).entitled, false, 'legacy trials get no grace');
  assert.equal(evaluateEntitlement({ status: 'cancelled', source: 'kofi', expiresAt: new Date(NOW - DAY_MS).toISOString() }, NOW).entitled, false, 'revoked access gets no grace');
});

test('monthly payments: first payment queues the first-month emails, retries never grant twice', async () => {
  reset();
  const first = await kofi({ type: 'Subscription', email: 'Member@Example.test', is_subscription_payment: true, is_first_subscription_payment: true, kofi_transaction_id: 'tx-1', tier_name: 'Premium' });
  assert.equal(first.statusCode, 200);
  assert.equal(first.body ? JSON.parse(first.body).plan : null, 'monthly');
  const record = entitlement('member@example.test');
  assert.equal(record.plan, 'monthly');
  assert.deepEqual(record.payments, ['tx-1']);
  const jobs = [...store('premium-member-emails').data.values()].map((job) => job.type).sort();
  assert.deepEqual(jobs, ['tips_day7', 'yearly_day20']);
  assert.ok(![...store('premium-member-emails').data.values()].some((job) => JSON.stringify(job).includes('member@example.test')), 'addresses are stored encrypted');

  const retry = await kofi({ type: 'Subscription', email: 'member@example.test', is_subscription_payment: true, kofi_transaction_id: 'tx-1' });
  assert.equal(JSON.parse(retry.body).duplicate, true);
  assert.equal(entitlement('member@example.test').expiresAt, record.expiresAt);

  await kofi({ type: 'Subscription', email: 'member@example.test', is_subscription_payment: true, kofi_transaction_id: 'tx-2' });
  const renewed = new Date(record.expiresAt);
  renewed.setUTCMonth(renewed.getUTCMonth() + 1);
  assert.equal(entitlement('member@example.test').expiresAt, renewed.toISOString(), 'an early renewal adds one calendar month on top');
});

test('a 12-month pass grants 366 days, stacks on a membership and schedules two reminders', async () => {
  reset();
  await kofi({ type: 'Subscription', email: 'pass@example.test', is_subscription_payment: true, is_first_subscription_payment: true, kofi_transaction_id: 'm1' });
  const monthlyEnd = Date.parse(entitlement('pass@example.test').expiresAt);
  const response = await kofi({ type: 'Shop Order', email: 'pass@example.test', kofi_transaction_id: 's1', shop_items: [{ direct_link_code: 'ABC123DEF', quantity: 1 }] });
  assert.equal(JSON.parse(response.body).plan, 'yearly');
  const record = entitlement('pass@example.test');
  assert.equal(Date.parse(record.expiresAt) - monthlyEnd, 366 * DAY_MS);
  assert.equal(record.plan, 'yearly');
  assert.equal(record.tier, '12-month pass');
  const types = [...store('premium-member-emails').data.values()].map((job) => job.type).sort();
  assert.deepEqual(types, ['pass_ends_14', 'pass_ends_3', 'tips_day7', 'yearly_day20']);
});

test('tips and other shop items never unlock Premium', async () => {
  reset();
  const tip = await kofi({ type: 'Donation', email: 'tip@example.test', kofi_transaction_id: 'd1' });
  assert.equal(JSON.parse(tip.body).ignored, 'not_membership');
  const sticker = await kofi({ type: 'Shop Order', email: 'tip@example.test', kofi_transaction_id: 's2', shop_items: [{ direct_link_code: 'sticker', quantity: 3 }] });
  assert.equal(JSON.parse(sticker.body).ignored, 'not_membership');
  assert.equal(entitlement('tip@example.test'), undefined);
  assert.equal(webhook._test.passesInOrder({ shop_items: [{ direct_link_code: 'abc123def', quantity: 2 }, { direct_link_code: 'other999' }] }), 3);
});

test('give a month, get a month: instant on first verified payment, once per payer, within the cap', async () => {
  reset();
  const ent = store('entitlements');
  const owner = { email: 'owner@example.test', emailHash: sha256('owner@example.test') };
  const status = await referrals.referralStatus({}, owner);
  assert.match(status.code, /^[A-HJ-NP-Z2-9]{8}$/);
  assert.equal(status.link, `https://web.arvio.tv/?ref=${status.code}`);
  assert.equal((await referrals.referralStatus({}, owner)).code, status.code, 'the code is stable');

  assert.equal((await referrals.claimReferral({}, { emailHash: owner.emailHash, code: status.code })).error, 'own_code');
  assert.equal((await referrals.claimReferral({}, { emailHash: sha256('x@example.test'), code: 'NOPE' })).error, 'invalid_code');
  ent.data.set(`email/${sha256('member@example.test')}.json`, { status: 'active', source: 'kofi', startedAt: '2026-01-01', expiresAt: '2099-01-01' });
  assert.equal((await referrals.claimReferral({}, { emailHash: sha256('member@example.test'), code: status.code })).error, 'already_member');
  ent.data.set(`email/${sha256('linked@example.test')}.json`, { status: 'expired', linkedFrom: 'billing@example.test' });
  assert.equal((await referrals.claimReferral({}, { emailHash: sha256('linked@example.test'), code: status.code })).error, 'already_member', 'a linked billing email counts as a member');

  const friendHash = sha256('friend@example.test');
  assert.equal((await referrals.claimReferral({}, { emailHash: friendHash, code: status.code.toLowerCase() })).ok, true);
  assert.equal((await referrals.claimReferral({}, { emailHash: friendHash, code: status.code })).error, 'already_claimed');
  assert.equal((await referrals.rewardReferralIfDue({}, { emailHash: friendHash })).reason, 'not_paying', 'signup alone earns no bonus');
  assert.equal(entitlement('friend@example.test'), undefined);
  assert.equal(entitlement('owner@example.test'), undefined);

  // The owner only ever had a legacy trial: a bonus must still be visible.
  ent.data.set(`email/${owner.emailHash}.json`, { status: 'expired', source: 'trial', startedAt: '2026-08-01', expiresAt: '2026-08-04T00:00:00Z', trialUsed: true });

  const payment = { type: 'Subscription', email: 'friend@example.test', is_subscription_payment: true, is_first_subscription_payment: true, kofi_transaction_id: 'f1' };
  const response = JSON.parse((await kofi(payment)).body);
  const friend = entitlement('friend@example.test');
  const paidEnd = addMonths(Date.parse(friend.startedAt), 1);
  assert.equal(Date.parse(friend.expiresAt) - paidEnd, 31 * DAY_MS, 'friend: 31 bonus days on the first webhook');
  assert.equal(response.expiresAt, friend.expiresAt);
  assert.equal(JSON.parse((await kofi(payment)).body).duplicate, true);
  assert.equal(entitlement('friend@example.test').expiresAt, friend.expiresAt);
  const later = Date.now() + 8 * DAY_MS;
  const inviter = entitlement('owner@example.test');
  assert.equal(inviter.source, 'referral', 'a trial record becomes a visible referral record');
  assert.equal(evaluateEntitlement(inviter, later).entitled, true);
  assert.ok([...store('premium-member-emails').data.values()].some((job) => job.type === 'referral_reward'));
  assert.equal((await referrals.rewardReferralIfDue({}, { emailHash: friendHash, now: later })).rewarded, false, 'rewarded once');
  assert.equal((await referrals.referralStatus({}, owner)).joined, 1);

  // A second account that pays with the same billing email earns nothing more.
  const twin = sha256('twin@example.test');
  await referrals.claimReferral({}, { emailHash: twin, code: status.code });
  assert.equal((await referrals.rewardReferralIfDue({}, { emailHash: twin, payerHash: friendHash, now: later + DAY_MS })).reason, 'payer_already_rewarded');

  // Someone who was already paying before claiming is not a new member.
  const veteran = sha256('veteran@example.test');
  ent.data.set(`email/${veteran}.json`, { status: 'active', source: 'kofi', plan: 'monthly', startedAt: new Date(Date.now() - 200 * DAY_MS).toISOString(), expiresAt: new Date(Date.now() + 20 * DAY_MS).toISOString() });
  store('premium-referrals').data.set(`referred/${veteran}.json`, { code: status.code, ownerHash: owner.emailHash, claimedAt: new Date().toISOString() });
  assert.equal((await referrals.rewardReferralIfDue({}, { emailHash: veteran, now: later })).reason, 'existing_member');

  // The yearly cap.
  const ownerRecord = store('premium-referrals').data.get(`owner/${owner.emailHash}.json`);
  ownerRecord.rewards = Array.from({ length: 6 }, () => new Date(later).toISOString());
  const second = sha256('second@example.test');
  await referrals.claimReferral({}, { emailHash: second, code: status.code });
  ent.data.set(`email/${second}.json`, { status: 'active', source: 'manual', startedAt: new Date().toISOString() });
  assert.equal((await referrals.rewardReferralIfDue({}, { emailHash: second })).reason, 'not_paying', 'a complimentary grant is not a payment');
  ent.data.set(`email/${second}.json`, { status: 'active', source: 'kofi', plan: 'monthly', startedAt: new Date(Date.now()).toISOString(), expiresAt: new Date(Date.now() + 30 * DAY_MS).toISOString() });
  const before = entitlement('owner@example.test').expiresAt;
  const capped = await referrals.rewardReferralIfDue({}, { emailHash: second, now: later });
  assert.equal(capped.rewarded, true);
  assert.equal(capped.inviterRewarded, false, 'at most 6 free months a year for the inviter');
  assert.equal(entitlement('owner@example.test').expiresAt, before);

  // The inviter deleted their account: a later reward recreates nothing.
  store('premium-referrals').data.delete(`owner/${owner.emailHash}.json`);
  const third = sha256('third@example.test');
  store('premium-referrals').data.set(`referred/${third}.json`, { code: status.code, ownerHash: owner.emailHash, claimedAt: new Date().toISOString() });
  ent.data.set(`email/${third}.json`, { status: 'active', source: 'kofi', plan: 'monthly', startedAt: new Date().toISOString(), expiresAt: new Date(Date.now() + 30 * DAY_MS).toISOString() });
  assert.equal((await referrals.rewardReferralIfDue({}, { emailHash: third, now: later })).inviterRewarded, false);
  assert.equal(store('premium-referrals').data.has(`owner/${owner.emailHash}.json`), false);
});

test('an invited annual buyer receives the bonus before expiry reminders are scheduled', async () => {
  reset();
  const ownerEmail = 'annual-inviter@example.test';
  const buyerEmail = 'annual-buyer@example.test';
  const { code } = await referrals.referralStatus({}, { email: ownerEmail, emailHash: sha256(ownerEmail) });
  await referrals.claimReferral({}, { emailHash: sha256(buyerEmail), code });
  const response = JSON.parse((await kofi({
    type: 'Shop Order', email: buyerEmail, kofi_transaction_id: 'annual-referral',
    shop_items: [{ direct_link_code: 'ABC123DEF', quantity: 1 }]
  })).body);
  const buyer = entitlement(buyerEmail);
  assert.equal(Date.parse(buyer.expiresAt) - Date.parse(buyer.startedAt), 397 * DAY_MS);
  assert.equal(response.expiresAt, buyer.expiresAt);
  const reminders = [...store('premium-member-emails').data.values()].filter((job) => job.type.startsWith('pass_ends_'));
  assert.equal(reminders.length, 2);
  assert.ok(reminders.every((job) => job.targetEnd === buyer.expiresAt));
  assert.equal(entitlement(ownerEmail).source, 'referral');
});

test('grace is for late monthly Ko-fi renewals only, and an unmatched $20 order is recorded', async () => {
  const ended = new Date(NOW - DAY_MS).toISOString();
  assert.equal(evaluateEntitlement({ status: 'active', source: 'kofi', plan: 'monthly', startedAt: '2026-01-01', expiresAt: ended }, NOW).reason, 'grace');
  assert.equal(evaluateEntitlement({ status: 'active', source: 'kofi', startedAt: '2026-01-01', expiresAt: ended }, NOW).reason, 'grace', 'legacy monthly records');
  assert.equal(evaluateEntitlement({ status: 'active', source: 'kofi', plan: 'yearly', tier: '12-month pass', startedAt: '2026-01-01', expiresAt: ended }, NOW).entitled, false, 'a pass just ends');
  assert.equal(evaluateEntitlement({ status: 'active', source: 'referral', startedAt: '2026-01-01', expiresAt: ended }, NOW).entitled, false);
  assert.equal(evaluateEntitlement({ status: 'active', source: 'manual', startedAt: '2026-01-01', expiresAt: ended }, NOW).entitled, false);

  reset();
  const response = await kofi({ type: 'Shop Order', email: 'buyer@example.test', kofi_transaction_id: 'unknown-1', amount: '20.00', shop_items: [{ direct_link_code: 'renamed99', quantity: 1 }] });
  assert.equal(JSON.parse(response.body).ignored, 'not_membership');
  const unmatched = store('entitlements').data.get('unmatched-shop-orders/unknown-1.json');
  assert.deepEqual(unmatched.codes, ['renamed99']);
  assert.equal(unmatched.emailHash, sha256('buyer@example.test'));
});

test('failing member emails back off and stop after five attempts', async () => {
  reset();
  const jobs = store('premium-member-emails');
  const entitlements = store('entitlements');
  entitlements.data.set(`email/${sha256('b@example.test')}.json`, { status: 'active', source: 'kofi', plan: 'monthly', startedAt: '2026-10-01', expiresAt: new Date(NOW + 10 * DAY_MS).toISOString() });
  await memberEmails.queueMemberEmail({}, { email: 'b@example.test', emailHash: sha256('b@example.test'), type: 'tips_day7', dueAt: NOW });
  const [key] = [...jobs.data.keys()];
  const failing = async () => { throw new Error('mailbox unavailable'); };
  let now = NOW;
  for (let attempt = 1; attempt <= 5; attempt += 1) {
    const result = await memberEmails.runDueMemberEmails({}, 50, { now, store: jobs, entitlements, send: failing });
    assert.equal(result.failed, 1);
    const job = jobs.data.get(key);
    assert.equal(job.attempts, attempt);
    now = Date.parse(job.dueAt) + 1000;
  }
  assert.equal(jobs.data.get(key).status, 'failed');
  assert.equal(jobs.data.get(key).sealedEmail, null);
  assert.equal((await memberEmails.runDueMemberEmails({}, 50, { now, store: jobs, entitlements, send: failing })).failed, 0, 'no more attempts');
});

test('unsubscribe links show a confirmation page; only its button unsubscribes', async () => {
  reset();
  const optout = require('../netlify/functions/member-email-optout');
  const accountKey = memberEmails.memberAccountKey('c@example.test');
  const s = memberEmails._test.optoutSignature(accountKey);
  const get = await optout.handler({ httpMethod: 'GET', queryStringParameters: { a: accountKey, s } });
  assert.match(get.body, /<form method="post"/);
  assert.equal(store('premium-member-emails').data.has(`optout/${accountKey}.json`), false, 'a link scanner opening the link changes nothing');
  const post = await optout.handler({ httpMethod: 'POST', queryStringParameters: { a: accountKey, s } });
  assert.equal(post.statusCode, 200);
  assert.equal(store('premium-member-emails').data.has(`optout/${accountKey}.json`), true);
  assert.equal((await optout.handler({ httpMethod: 'POST', queryStringParameters: { a: accountKey, s: 'f'.repeat(64) } })).statusCode, 400);
});

test('member emails are skipped when they no longer apply and can be unsubscribed', async () => {
  reset();
  const { stillRelevant, memberEmailContent, deliverMemberEmailJob } = memberEmails._test;
  const active = { status: 'active', source: 'kofi', plan: 'monthly', startedAt: '2026-10-01', expiresAt: new Date(NOW + 10 * DAY_MS).toISOString() };
  assert.equal(stillRelevant({ type: 'tips_day7' }, active, NOW), true);
  assert.equal(stillRelevant({ type: 'tips_day7' }, { ...active, expiresAt: new Date(NOW - 10 * DAY_MS).toISOString() }, NOW), false);
  assert.equal(stillRelevant({ type: 'yearly_day20' }, { ...active, plan: 'yearly' }, NOW), false);
  const target = active.expiresAt;
  assert.equal(stillRelevant({ type: 'pass_ends_3', targetEnd: target }, active, NOW), true);
  assert.equal(stillRelevant({ type: 'pass_ends_3', targetEnd: target }, { ...active, expiresAt: new Date(NOW + 300 * DAY_MS).toISOString() }, NOW), false, 'renewed passes get no reminder');

  const accountKey = memberEmails.memberAccountKey('a@example.test');
  const content = memberEmailContent('pass_ends_3', { accountKey, targetEnd: '2027-11-02T00:00:00Z' });
  assert.match(content.subject, /November 2, 2027/);
  assert.match(content.text, /member-email-optout\?a=[a-f0-9]{64}&s=[a-f0-9]{64}/);
  assert.equal(memberEmails.verifyOptout(accountKey, memberEmails._test.optoutSignature(accountKey)), true);
  assert.equal(memberEmails.verifyOptout(accountKey, 'f'.repeat(64)), false);

  const jobs = store('premium-member-emails');
  const entitlements = store('entitlements');
  entitlements.data.set(`email/${sha256('a@example.test')}.json`, active);
  await memberEmails.queueMemberEmail({}, { email: 'a@example.test', emailHash: sha256('a@example.test'), type: 'tips_day7', dueAt: NOW });
  const [key] = [...jobs.data.keys()];
  const sent = [];
  await memberEmails.optOut({}, accountKey);
  assert.equal(await deliverMemberEmailJob({}, jobs, key, jobs.data.get(key), { now: NOW, send: async (...args) => sent.push(args), entitlements }), false);
  assert.equal(sent.length, 0);
  assert.equal(jobs.data.get(key).status, 'suppressed');
  assert.equal(jobs.data.get(key).sealedEmail, null, 'the address is dropped once the job is done');
});

test('stream tickets: valid until expiry, useless when changed', () => {
  const { signTicket, verifyTicket, TICKET_TTL_SECONDS } = streamTicket._test;
  const secret = 's'.repeat(40);
  const now = 1_800_000_000;
  const { ticket } = signTicket(secret, 'abcdef1234', now);
  assert.equal(verifyTicket(secret, ticket, now + 60), true);
  assert.equal(verifyTicket(secret, ticket, now + TICKET_TTL_SECONDS + 1), false);
  assert.equal(verifyTicket('t'.repeat(40), ticket, now), false);
  const parts = ticket.split('.');
  assert.equal(verifyTicket(secret, [parts[0], String(now + 999999), parts[2], parts[3]].join('.'), now), false);
});

test('exit survey only accepts the listed reasons and trims free text', () => {
  assert.deepEqual(exitSurvey._test.REASONS, ['too_expensive', 'playback_problems', 'missing_feature', 'only_needed_briefly', 'use_android_instead', 'other']);
  assert.equal(exitSurvey._test.cleanDetail(` a\nb\t${'x'.repeat(400)}`).length, 300);
});

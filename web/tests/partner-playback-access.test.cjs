const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

test('Partner access honors its server-provided grace deadline and then expires', () => {
  const policy = load('lib/entitlementPolicy.ts');
  const now = Date.parse('2026-11-01T12:00:00Z');
  const state = { entitled: true, reason: 'partner', source: 'partner', status: 'grace', expiresAt: new Date(now - 86400000).toISOString(), graceEndsAt: new Date(now + 86400000).toISOString() };
  assert.equal(policy.currentEntitlement(state, now).entitled, true);
  assert.equal(policy.accessEndsAt(state), state.graceEndsAt);
  assert.equal(policy.currentEntitlement(state, now + 86400001).entitled, false);
});

test('a one-day relay lease is reused and refreshed halfway through, not on every call', async () => {
  let now = 1800000000000;
  let calls = 0;
  class Clock extends Date { static now() { return now; } }
  const api = load('lib/streamTicket.ts', { './entitlement': { fetchStreamTicket: async () => ({ ticket: `lease-${++calls}`, expiresAt: new Date(now + 86400000).toISOString() }) } }, { Date: Clock });
  const auth = { session: { userId: 'viewer' } };
  await api.refreshStreamTicket(auth);
  now += 11 * 3600000;
  await api.refreshStreamTicket(auth);
  assert.equal(calls, 1);
  assert.equal(api.currentStreamTicket(), 'lease-1');
  now += 2 * 3600000;
  await api.refreshStreamTicket(auth);
  assert.equal(calls, 2);
});

test('logout and entitlement revocation invalidate in-flight relay tickets', async () => {
  let finish;
  const api = load('lib/streamTicket.ts', { './entitlement': { fetchStreamTicket: () => new Promise(resolve => { finish = resolve; }) } });
  const pending = api.refreshStreamTicket({ session: { userId: 'viewer' } });
  api.clearStreamTicket();
  finish({ ticket: 'revoked', expiresAt: new Date(Date.now() + 86400000).toISOString() });
  await pending;
  assert.equal(api.currentStreamTicket(), '');
});

test('account switching does not reuse a different account request or its late response', async () => {
  const pending = new Map();
  const api = load('lib/streamTicket.ts', { './entitlement': { fetchStreamTicket: auth => new Promise(resolve => pending.set(auth.session.userId, resolve)) } });
  const first = api.refreshStreamTicket({ session: { userId: 'first' } });
  const second = api.refreshStreamTicket({ session: { userId: 'second' } });
  const expiry = new Date(Date.now() + 86400000).toISOString();
  pending.get('second')({ ticket: 'second-ticket', expiresAt: expiry });
  await second;
  pending.get('first')({ ticket: 'first-ticket', expiresAt: expiry });
  await first;
  assert.equal(api.currentStreamTicket(), 'second-ticket');
});

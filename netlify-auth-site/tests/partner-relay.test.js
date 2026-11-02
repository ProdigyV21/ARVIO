const test = require('node:test');
const assert = require('node:assert/strict');
const { ticketLifetime, signTicket, verifyTicket, TICKET_TTL_SECONDS, PARTNER_TICKET_TTL_SECONDS } = require('../netlify/functions/stream-ticket')._test;

test('Partner tickets survive long films but are bounded by one day and the paid grace deadline', () => {
  const now = Date.parse('2026-11-01T12:00:00Z');
  const state = { source: 'partner', expiresAt: new Date(now - 86400000).toISOString(), graceEndsAt: new Date(now + 2 * 86400000).toISOString() };
  const ttl = ticketLifetime(state, now);
  assert.equal(ttl, PARTNER_TICKET_TTL_SECONDS);
  const signed = signTicket('secret-for-fixture', 'viewer', now / 1000, ttl);
  assert.equal(verifyTicket('secret-for-fixture', signed.ticket, now / 1000 + 4 * 3600), true);
  assert.equal(verifyTicket('secret-for-fixture', signed.ticket, now / 1000 + 86401), false);
  assert.equal(ticketLifetime({ ...state, graceEndsAt: new Date(now + 1800000).toISOString() }, now), 1800);
  assert.equal(ticketLifetime({ ...state, graceEndsAt: new Date(now - 1).toISOString() }, now), 0);
  assert.equal(ticketLifetime({ source: 'partner' }, now), 0);
  assert.equal(ticketLifetime({ source: 'kofi' }, now), TICKET_TTL_SECONDS);
});

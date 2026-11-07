const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const vm = require('node:vm');
const backend = require('../netlify/functions/_backend');
const domain = require('../netlify/functions/_partner-domain');

function fixture(file, extra = {}) {
  const calls = [];
  const service = new Proxy({}, { get: (_, name) => async (...args) => {
    calls.push({ name, args });
    if (extra.failure) throw extra.failure;
    return { ok: true };
  } });
  const dependencies = {
    './_backend': { ...backend, getPool: () => ({}), sendTransactionalEmail: async () => {},
      resolveIdentity: async event => {
        if (!event.validIdentity) throw Object.assign(new Error('Session expired'), { statusCode: 401 });
        return { supabaseUserId: 'verified-account', email: 'verified@example.test' };
      }
    },
    './_partner-domain': domain,
    './_partner-database': { getPool: () => ({}) },
    './_partner-service': { createService: () => service },
    './_entitlements': { entitlementsStore: () => { throw Error('Partner payment reached consumer storage'); } },
    './_premium-funnel': { recordPremiumEvent: () => { throw Error('Unexpected consumer analytics'); } },
    './_member-emails': { queueForPayment: () => { throw Error('Unexpected consumer mail'); } },
    './_referrals': { rewardReferralIfDue: () => { throw Error('Unexpected consumer referral'); } }
  };
  const exports = {};
  vm.runInNewContext(fs.readFileSync(require.resolve(`../netlify/functions/${file}`), 'utf8'), {
    exports, Buffer, URLSearchParams, Date, SyntaxError,
    console: { error() {} },
    process: { env: { PARTNER_ENABLED: 'true', KOFI_VERIFICATION_TOKEN: 'fixture-verification-token', ...extra.env } },
    require: name => { if (!dependencies[name]) throw Error(`Unexpected dependency ${name}`); return dependencies[name]; }
  });
  async function request(payload, overrides = {}) {
    const response = await exports.handler({ httpMethod: 'POST', headers: { Authorization: 'Bearer token' }, validIdentity: true, body: JSON.stringify(payload), ...overrides });
    return { ...response, data: response.body ? JSON.parse(response.body) : null };
  }
  return { request, calls };
}

test('Partner API requires a verified bearer identity and explicit consent, with private no-store responses', async () => {
  const f = fixture('partner-api');
  assert.equal((await f.request({ operation: 'overview' }, { headers: {} })).statusCode, 401);
  assert.equal((await f.request({ operation: 'overview' }, { validIdentity: false, headers: { Authorization: 'Bearer invalid', 'x-arvio-user-id': 'someone-else' } })).statusCode, 401);
  assert.equal((await f.request({ operation: 'accept-invitation', token: 'token' })).statusCode, 400);
  assert.equal((await f.request({ operation: 'leave' })).statusCode, 400);
  for (const payload of [null, 'string', [], { operation: 'unknown' }]) assert.equal((await f.request(payload)).statusCode, 400);
  assert.equal(f.calls.length, 0);
  const result = await f.request({ operation: 'delivery', accountId: 'spoofed', device: { key: 'device' } });
  assert.equal(result.statusCode, 200);
  assert.equal(result.headers['cache-control'], 'private, no-store');
  assert.equal(f.calls[0].args[0].supabaseUserId, 'verified-account');
  assert.equal(f.calls[0].name, 'delivery');
});

test('Partner API enforces rollout and body limits and never returns database secrets', async () => {
  assert.equal((await fixture('partner-api', { env: { PARTNER_ENABLED: 'false' } }).request({ operation: 'overview' })).statusCode, 503);
  const f = fixture('partner-api');
  assert.equal((await f.request({}, { httpMethod: 'GET' })).statusCode, 405);
  assert.equal((await f.request({}, { body: 'x'.repeat(512001) })).statusCode, 413);
  assert.equal((await f.request({}, { body: Buffer.alloc(512001).toString('base64'), isBase64Encoded: true })).statusCode, 413);
  assert.equal((await f.request({}, { body: '{broken' })).statusCode, 400);
  const result = await fixture('partner-api', { failure: new Error('postgres://password@private-host') }).request({ operation: 'overview' });
  assert.equal(result.statusCode, 500);
  assert.equal(result.body.includes('password'), false);
});

test('same-account Ko-fi Partner payments are verified and never grant consumer Premium by mistake', async () => {
  const payload = { verification_token: 'fixture-verification-token', tier_name: 'ARVIO Partner 20', email: 'buyer@example.test', type: 'Subscription' };
  const f = fixture('kofi-webhook');
  assert.equal((await f.request({ ...payload, verification_token: 'wrong' })).statusCode, 401);
  assert.equal((await f.request({ ...payload, tier_name: 'ARVIO Partner 21' })).statusCode, 400);
  assert.equal(f.calls.length, 0);
  const body = new URLSearchParams({ data: JSON.stringify(payload) }).toString();
  assert.equal((await f.request({}, { body })).statusCode, 200);
  assert.equal(f.calls[0].name, 'payment');
  assert.equal(f.calls[0].args[0].tier_name, payload.tier_name);
  const disabled = fixture('kofi-webhook', { env: { PARTNER_ENABLED: 'false' } });
  assert.equal((await disabled.request(payload)).statusCode, 503);
  assert.equal(disabled.calls.length, 0);
});

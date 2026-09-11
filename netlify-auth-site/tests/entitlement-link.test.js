const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const vm = require('node:vm');
const crypto = require('node:crypto');
const { evaluateEntitlement } = require('../netlify/functions/_entitlements');
const { parseBody } = require('../netlify/functions/_backend');

function setup() {
  const data = new Map();
  const emails = [];
  const hash = (value) => crypto.createHash('sha256').update(value).digest('hex');
  const store = {
    get: async (key) => data.get(key) ?? null,
    setJSON: async (key, value, options) => {
      if (options?.onlyIfNew && data.has(key)) return { modified: false };
      data.set(key, value); return { modified: true };
    },
    delete: async (key) => data.delete(key)
  };
  const exports = {};
  vm.runInNewContext(fs.readFileSync(require.resolve('../netlify/functions/entitlement-link'), 'utf8'), {
    exports, Buffer, Date, console,
    require: (name) => name === 'node:crypto' ? crypto : name === './_backend' ? {
      json: (statusCode, body) => ({ statusCode, body }), options: () => null, parseBody,
      resolveIdentity: async (event) => { if (!event.identity) throw Error('unauthorized'); return { email: event.identity }; },
      normalizeEmail: (email) => String(email || '').toLowerCase().trim(), sha256: hash, privacyHash: (_purpose, value) => hash(value),
      sendTransactionalEmail: async (email, subject, text) => emails.push({ email, subject, text })
    } : name === './_premium-funnel' ? { recordPremiumEvent: async (_event, value) => data.set('verified-funnel', value) } : { entitlementsStore: () => store, readEntitlement: async (_store, key) => data.get(key), writeEntitlement: async (_store, key, value) => data.set(key, value), evaluateEntitlement }
  });
  const request = (body, identity = 'account@example.test', encoded = false) => exports.handler({ httpMethod: 'POST', body: encoded ? Buffer.from(JSON.stringify(body)).toString('base64') : JSON.stringify(body), identity, isBase64Encoded: encoded });
  data.set(hash('billing@example.test'), { status: 'active', source: 'kofi', expiresAt: new Date(Date.now() + 86_400_000).toISOString() });
  const code = () => emails.at(-1).text.match(/code is ([a-f0-9]{16})/)[1];
  return { request, emails, data, hash, code };
}

test('knowing a billing email cannot grant Premium without verification', async () => {
  const fixture = setup();
  const result = await fixture.request({ kofiEmail: 'billing@example.test' });
  assert.equal(result.statusCode, 202);
  assert.equal(result.body.verificationRequired, true);
  assert.equal(fixture.data.has(fixture.hash('account@example.test')), false);
  assert.equal(fixture.emails[0].email, 'billing@example.test');
  assert.equal(fixture.data.has('verified-funnel'), false);
});

test('Netlify base64 requests preserve the billing email and ownership code', async () => {
  const fixture = setup();
  const challenge = await fixture.request({ kofiEmail: 'billing@example.test' }, undefined, true);
  assert.equal(challenge.statusCode, 202);
  const linked = await fixture.request({ kofiEmail: 'billing@example.test', code: fixture.code() }, undefined, true);
  assert.equal(linked.statusCode, 200);
  assert.equal(linked.body.linked, true);
  assert.equal(fixture.data.get('verified-funnel').metadata.billing_key, fixture.hash('billing@example.test'));
});

test('malformed billing bodies fail with a controlled validation error', async () => {
  for (const body of [null, [], { kofiEmail: '' }]) assert.equal((await setup().request(body)).statusCode, 400);
});

test('verified code links once; replay, wrong codes and another account are rejected', async () => {
  const fixture = setup();
  await fixture.request({ kofiEmail: 'billing@example.test' });
  assert.equal((await fixture.request({ kofiEmail: 'billing@example.test', code: '0'.repeat(16) })).statusCode, 400);
  const body = { kofiEmail: 'billing@example.test', code: fixture.code() };
  assert.equal((await fixture.request(body, 'other@example.test')).statusCode, 400);
  assert.equal((await fixture.request(body)).body.linked, true);
  assert.equal((await fixture.request(body)).statusCode, 400);
});

test('expired challenge and a membership owned by another account fail closed', async () => {
  const fixture = setup();
  await fixture.request({ kofiEmail: 'billing@example.test' });
  const key = `link-proof/${fixture.hash('account@example.test')}/${fixture.hash('billing@example.test')}`;
  const proof = fixture.data.get(key);
  const body = { kofiEmail: 'billing@example.test', code: fixture.code() };
  fixture.data.set(key, { ...proof, expiresAt: 0 });
  assert.equal((await fixture.request(body)).statusCode, 400);
  fixture.data.set(key, proof);
  fixture.data.set(`link-owner/${fixture.hash('billing@example.test')}`, { accountHash: 'another-account' });
  assert.equal((await fixture.request(body)).statusCode, 409);
});

test('mail requests are throttled per account, not just per destination', async () => {
  const fixture = setup();
  await fixture.request({ kofiEmail: 'billing@example.test' });
  assert.equal((await fixture.request({ kofiEmail: 'other@example.test' })).statusCode, 429);
  assert.equal(fixture.emails.length, 1);
});

test('an expired lifetime record cannot turn a paid monthly link into lifetime access', async () => {
  const fixture = setup();
  fixture.data.set(fixture.hash('account@example.test'), { status: 'cancelled', expiresAt: null });
  await fixture.request({ kofiEmail: 'billing@example.test' });
  const result = await fixture.request({ kofiEmail: 'billing@example.test', code: fixture.code() });
  assert.equal(result.statusCode, 200);
  assert.ok(result.body.expiresAt);
});

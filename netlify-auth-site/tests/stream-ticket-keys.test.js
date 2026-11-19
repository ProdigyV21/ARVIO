const test = require('node:test');
const assert = require('node:assert/strict');

// In-memory Netlify Blobs (only what _stream-keys.js uses).
const stores = new Map();
function memoryStore() {
  const data = new Map();
  return {
    data,
    async get(key) { return data.has(key) ? structuredClone(data.get(key)) : null; },
    async setJSON(key, value, opts = {}) {
      if (opts.onlyIfNew && data.has(key)) return { modified: false };
      data.set(key, structuredClone(value));
      return { modified: true };
    }
  };
}
const store = (name) => { if (!stores.has(name)) stores.set(name, memoryStore()); return stores.get(name); };
require.cache[require.resolve('@netlify/blobs')] = { id: '@netlify/blobs', filename: '@netlify/blobs', loaded: true, exports: { connectLambda() {}, getStore: (name) => store(name) } };
delete process.env.STREAM_TICKET_SECRET;

const keys = require('../netlify/functions/_stream-keys');
const keyEndpoint = require('../netlify/functions/stream-ticket-key');

test('the auth site creates one signing key and keeps it', async () => {
  keys._resetCache();
  const s = store('stream-keys');
  const [a] = await keys.publicKeys(s);
  keys._resetCache();
  const [b] = await keys.publicKeys(s);
  assert.equal(a.kid, b.kid, 'the stored key is reused after a cold start');
  assert.equal(a.kty, 'EC'); assert.equal(a.crv, 'P-256');
  assert.equal('d' in a, false, 'the private part is never published');
  assert.ok(s.data.get(keys.KEY_NAME).privateJwk.d, 'the private key stays in the site\'s own store');
});

test('concurrent first calls agree on one key', async () => {
  stores.delete('stream-keys');
  keys._resetCache();
  const s = store('stream-keys');
  const [x, y] = await Promise.all([keys.signingKey(s), (async () => { keys._resetCache(); return keys.signingKey(s); })()]);
  keys._resetCache();
  const stored = await keys.signingKey(s);
  assert.equal(stored.kid, s.data.get(keys.KEY_NAME).kid);
  assert.ok([x.kid, y.kid].includes(stored.kid));
});

test('v2 tickets verify with the public key only, and expire', async () => {
  keys._resetCache();
  const s = store('stream-keys');
  const now = 1_800_000_000;
  const { ticket, expiresAt } = await keys.signTicketV2(s, 'abcdef1234', 3600, now);
  const [pub] = await keys.publicKeys(s);
  assert.match(ticket, /^v2\.\d+\.abcdef1234\.[\w-]+\.[\w-]+$/);
  assert.equal(expiresAt, new Date((now + 3600) * 1000).toISOString());
  assert.equal(await keys.verifyTicketV2(pub, ticket, now + 60), true);
  assert.equal(await keys.verifyTicketV2(pub, ticket, now + 3601), false, 'expired');
  const parts = ticket.split('.');
  assert.equal(await keys.verifyTicketV2(pub, [parts[0], String(now + 999999), ...parts.slice(2)].join('.'), now), false, 'tampered expiry');
  const other = await (async () => { stores.delete('stream-keys'); keys._resetCache(); return (await keys.publicKeys(store('stream-keys')))[0]; })();
  assert.equal(await keys.verifyTicketV2(other, ticket, now), false, 'another key does not verify it');
});

test('the key endpoint publishes the public key with a cache header', async () => {
  keys._resetCache();
  const res = await keyEndpoint.handler({ httpMethod: 'GET', headers: {} });
  assert.equal(res.statusCode, 200);
  const body = JSON.parse(res.body);
  assert.equal(body.keys.length, 1);
  assert.equal('d' in body.keys[0], false);
  assert.match(res.headers['Cache-Control'], /max-age=300/);
});

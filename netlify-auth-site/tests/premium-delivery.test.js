const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const vm = require('node:vm');
const crypto = require('node:crypto');
const { evaluateEntitlement } = require('../netlify/functions/_entitlements');

function fixture({ paid = false, failRead = false, linkedPaid = false } = {}) {
  const data = new Map(); let emails = 0; let writes = 0;
  const store = {
    get: async key => data.get(key) || null,
    setJSON: async (key, value) => { writes++; data.set(key, value); },
    list: async () => ({ blobs: [] })
  };
  const backend = { sha256: x => x, normalizeEmail: x => x || '', privacyHash: (_p, x) => x, sendTransactionalEmail: async () => { emails++; return { id: 'fixture' }; } };
  const module = { exports: {} };
  const record = { status: 'active', source: paid ? 'kofi' : 'trial', expiresAt: '2099-01-01', linkedFrom: linkedPaid ? 'billing@example.test' : null };
  vm.runInNewContext(fs.readFileSync(require.resolve('../netlify/functions/_trial-emails'), 'utf8'), {
    module, exports: module.exports, Buffer, Date, Intl, console,
    process: { env: { ARVIO_AUTH_SECRET: 'test-secret-longer-than-thirty-two-characters' } },
    require: name => name === 'crypto' ? crypto : name === '@netlify/blobs' ? { connectLambda() {}, getStore: () => store } : name === './_backend' ? backend : name === './_premium-funnel' ? { recordPremiumEvent: async () => {} } : {
      entitlementsStore: () => store,
      readEntitlement: async (_store, email) => { if (failRead) throw Error('Temporary store failure'); return email === 'billing@example.test' ? { ...record, source: 'kofi' } : record; },
      evaluateEntitlement
    }
  });
  const api = module.exports._test;
  const job = { type: 'reminder', expiresAt: '2099-01-01', dueAt: '2000-01-01', status: 'pending', sealedEmail: api.sealEmail('test@example.test') };
  return { api, job, store, data, emailCount: () => emails, writes: () => writes };
}

test('paid trial users get no queued reminder and the job cannot be resent', async () => {
  for (const setup of [{ paid: true }, { linkedPaid: true }]) {
    const f = fixture(setup);
    assert.equal(await f.api.deliverTrialEmailJob({}, f.store, 'job', f.job), false);
    assert.equal(f.emailCount(), 0);
    assert.equal(f.data.get('job').status, 'suppressed');
    assert.equal(f.data.get('job').sealedEmail, null);
    await f.api.deliverTrialEmailJob({}, f.store, 'job', f.data.get('job'));
    assert.equal(f.emailCount(), 0);
  }
});

test('an unavailable entitlement check defers mail rather than sending a false expiry', async () => {
  const f = fixture({ failRead: true });
  await assert.rejects(f.api.deliverTrialEmailJob({}, f.store, 'job', f.job));
  assert.equal(f.emailCount(), 0);
  assert.equal(f.data.get('job').status, 'pending');
  assert.ok(f.data.get('job').nextAttemptAt);
});

test('genuine trial reminder still sends once and removes encrypted address', async () => {
  const f = fixture();
  assert.equal(await f.api.deliverTrialEmailJob({}, f.store, 'job', f.job), true);
  assert.equal(f.emailCount(), 1);
  assert.equal(f.data.get('job').sealedEmail, null);
  await f.api.deliverTrialEmailJob({}, f.store, 'job', f.data.get('job'));
  assert.equal(f.emailCount(), 1);
});

test('repeated browser events do not create repeated Blob writes', async () => {
  const records = new Map(); let writes = 0;
  const store = { get: async key => records.get(key), setJSON: async (key, value) => { writes++; records.set(key, value); } };
  const module = { exports: {} };
  vm.runInNewContext(fs.readFileSync(require.resolve('../netlify/functions/_premium-funnel'), 'utf8'), {
    module, exports: module.exports, Date, Map, Set,
    require: name => name === '@netlify/blobs' ? { connectLambda() {}, getStore: () => store } : { privacyHash: (_p, x) => x }
  });
  const record = module.exports.recordPremiumEvent;
  for (let i = 0; i < 20; i++) await record({}, { email: 'fixture@example.test', eventName: 'playback_started', metadata: { source: String(i) }, occurredAt: '2026-09-08' });
  assert.equal(writes, 1);
  assert.equal([...records.values()][0].metadata.source, '0');
  await record({}, { email: 'fixture@example.test', eventName: 'playback_started', occurredAt: '2026-09-09' });
  assert.equal(writes, 2);
});

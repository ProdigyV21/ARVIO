const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const vm = require('node:vm');

function load(file, env, dependencies) {
  const module = { exports: {} };
  vm.runInNewContext(fs.readFileSync(require.resolve(`../netlify/functions/${file}`), 'utf8'), {
    module, process: { env }, console: { error() {} },
    require: name => {
      assert.ok(dependencies[name], `Unexpected dependency ${name}`);
      return dependencies[name];
    },
  });
  return module.exports;
}

test('Partner uses only its dedicated restricted connection and reuses a bounded pool', () => {
  const configurations = [];
  const dependencies = { pg: { Pool: class { constructor(options) { configurations.push(options); } } } };
  const absent = load('_partner-database', { NETLIFY_DB_URL: 'must-not-use-owner-url' }, dependencies);
  assert.throws(() => absent.getPool(), error => error.code === 'PARTNER_DATABASE_NOT_CONFIGURED' && error.statusCode === 503);
  assert.equal(configurations.length, 0);
  const configured = load('_partner-database', { PARTNER_DATABASE_URL: 'restricted-test-url' }, dependencies);
  assert.equal(configured.getPool(), configured.getPool());
  assert.equal(configurations.length, 1);
  assert.equal(configurations[0].connectionString, 'restricted-test-url');
  assert.equal(configurations[0].max, 2);
  assert.ok(configurations[0].statement_timeout > 0);
});

test('Partner outages fail closed without breaking consumer entitlement checks', async () => {
  const identity = { supabaseUserId: 'test-account' };
  for (const phase of ['connection', 'query']) {
    const api = load('_partner-entitlement', { PARTNER_ENABLED: 'true' }, {
      './_partner-database': { getPool() { if (phase === 'connection') throw Error('missing connection'); return {}; } },
      './_partner-service': { createService: () => ({ entitlement: async () => { throw Error('unavailable database'); } }) },
    });
    assert.equal(await api.partnerEntitlement(identity), null);
  }
  assert.equal(await load('_partner-entitlement', {}, {}).partnerEntitlement(identity), null);
  const expected = { premium: true };
  const api = load('_partner-entitlement', { PARTNER_ENABLED: 'true' }, {
    './_partner-database': { getPool: () => ({}) },
    './_partner-service': { createService: () => ({ entitlement: async value => { assert.equal(value, identity); return expected; } }) },
  });
  assert.equal(await api.partnerEntitlement(identity), expected);
});

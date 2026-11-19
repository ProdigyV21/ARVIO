const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

// Server side of per-attempt playback telemetry (netlify-auth-site). Loaded
// in a sandbox with an in-memory blob store, like the auth-site's own tests.
const functions = path.resolve(__dirname, '../../netlify-auth-site/netlify/functions');
const plain = (value) => JSON.parse(JSON.stringify(value));

function sandboxed(name, dependencies) {
  const module = { exports: {} };
  vm.runInNewContext(fs.readFileSync(path.join(functions, name), 'utf8'), {
    module, exports: module.exports, console, Date, Set, Map, URLSearchParams, Buffer,
    require: (dep) => { if (dep in dependencies) return dependencies[dep]; throw Error(`Missing mock ${dep}`); }
  });
  return module.exports;
}

function memoryStore() {
  const data = new Map(), versions = new Map();
  return { data,
    get: async (key) => data.get(key) || null,
    getWithMetadata: async (key) => (data.has(key) ? { data: data.get(key), etag: String(versions.get(key) || 0) } : null),
    setJSON: async (key, value, options) => {
      if (options?.onlyIfNew && data.has(key)) return { modified: false };
      if (options?.onlyIfMatch !== undefined && (!data.has(key) || options.onlyIfMatch !== String(versions.get(key) || 0))) return { modified: false };
      data.set(key, structuredClone(value)); versions.set(key, (versions.get(key) || 0) + 1); return { modified: true };
    },
    list: async ({ prefix }) => ({ blobs: [...data.keys()].filter((key) => key.startsWith(prefix)).map((key) => ({ key })) }),
    delete: async (key) => { data.delete(key); }
  };
}

const measurement = sandboxed('_premium-measurement.js', { './_backend': { privacyHash: (_ns, value) => `h-${value}` } });
function funnel(store) {
  return sandboxed('_premium-funnel.js', {
    '@netlify/blobs': { connectLambda() {}, getStore: () => store },
    './_backend': { privacyHash: (_ns, value) => value },
    './_premium-measurement': measurement
  });
}

const attempt = (overrides = {}) => ({
  playback_type: 'vod', transport: 'file', failure_kind: 'format', phase: 'startup',
  platform: 'iphone', method: 'direct', container: 'mkv', host_type: 'debrid', outcome: 'final', attempt: true, ...overrides
});

test('attempt metadata is allow-listed: unknown values, URLs and identifying text are dropped', () => {
  const { sanitizeMetadata } = funnel(memoryStore())._test;
  assert.deepEqual(plain(sanitizeMetadata(attempt())), plain(attempt()));
  const hostile = sanitizeMetadata({
    platform: 'iPhone 15 Pro', method: 'https://cdn.example/movie.mkv', container: 'mkv?token=1', host_type: 'real-debrid.com',
    outcome: 'maybe', attempt: 'yes', title: 'Secret Movie', url: 'https://x', host: 'cdn.example'
  });
  assert.deepEqual(plain(hostile), {});
  // Attribution plus every playback field still fits inside the key budget.
  const full = sanitizeMetadata({ source: 'direct', medium: 'web', campaign: 'premium', content: 'unspecified', referrer: '', ...attempt() });
  for (const key of ['platform', 'method', 'container', 'host_type', 'outcome', 'attempt', 'source']) assert.ok(key in full, key);
});

test('each failed attempt is recorded, rescued attempts do not count as failed playback', async () => {
  const store = memoryStore();
  const module = funnel(store);
  const at = '2026-10-24T10:00:00Z';
  const send = (metadata) => module.recordPremiumEvent({}, { accountId: 'acct', eventName: 'playback_failed', metadata, occurredAt: at });
  await send(attempt({ outcome: 'retried', host_type: 'addon_http', container: 'mp4', failure_kind: 'network' }));
  const dailyKey = 'events/date/2026-10-24/account/acct/playback_failed.json';
  const attemptsKey = 'events/date/2026-10-24/account/acct/_playback_attempts.json';
  assert.equal(store.data.has(dailyKey), false, 'a hop to the next source is not a failed playback');
  await send(attempt());
  await send(attempt());
  await send(attempt({ platform: 'ipad', method: 'remux' }));
  const daily = store.data.get(dailyKey);
  assert.equal(daily.metadata.failure_kind, 'format');
  assert.equal('attempt' in daily.metadata || 'outcome' in daily.metadata, false);
  const record = store.data.get(attemptsKey);
  assert.equal(record.attemptCount, 4);
  assert.deepEqual(plain(record.attempts.map((entry) => [entry.platform, entry.method, entry.outcome, entry.count])), [
    ['iphone', 'direct', 'retried', 1], ['iphone', 'direct', 'final', 2], ['ipad', 'remux', 'final', 1]
  ]);
  // Old clients without attempt detail keep the daily record behaviour.
  await module.recordPremiumEvent({}, { accountId: 'other', eventName: 'playback_failed', metadata: { failure_kind: 'network' }, occurredAt: at });
  assert.equal(store.data.has('events/date/2026-10-24/account/other/_playback_attempts.json'), false);
  assert.equal(store.data.has('events/date/2026-10-24/account/other/playback_failed.json'), true);
});

test('attempt records are bounded per account and day', () => {
  const { mergePlaybackAttempt, playbackAttempt } = funnel(memoryStore())._test;
  let record = {};
  for (let index = 0; index < 260; index += 1) {
    const next = mergePlaybackAttempt(record, playbackAttempt(attempt({ phase: index % 2 ? 'playback' : 'startup', failure_kind: ['network', 'format', 'timeout', 'engine', 'unknown', 'browser_restriction'][index % 6], platform: ['iphone', 'ipad', 'android', 'firefox', 'other'][index % 5] })));
    if (!next) break;
    record = next;
  }
  assert.equal(record.attemptCount, 200);
  assert.ok(record.attempts.length <= 24);
  assert.equal(playbackAttempt({ platform: 'iphone' }), null, 'incomplete detail is not an attempt');
});

test('the report summarises attempts by platform, method, container and host type', async () => {
  const store = memoryStore();
  const module = funnel(store);
  const today = new Date().toISOString().slice(0, 10);
  const at = `${today}T09:00:00Z`;
  for (const [account, metadata] of [
    ['a', attempt()], ['a', attempt()], ['b', attempt()], ['b', attempt({ platform: 'chromium_desktop', container: 'mp4', host_type: 'addon_http', outcome: 'retried', failure_kind: 'network' })]
  ]) await module.recordPremiumEvent({}, { accountId: account, eventName: 'playback_failed', metadata, occurredAt: at });
  const report = await module.premiumFunnelReport({}, 1);
  const summary = report.playbackAttempts;
  assert.equal(summary.attempts, 4);
  assert.equal(summary.accountDaysWithDetail, 2);
  assert.deepEqual(plain(summary.byPlatform), { iphone: 3, chromium_desktop: 1 });
  assert.deepEqual(plain(summary.byOutcome), { final: 3, retried: 1 });
  assert.deepEqual(plain(summary.topCombinations[0]), { platform: 'iphone', method: 'direct', container: 'mkv', host_type: 'debrid', failure_kind: 'format', attempts: 3, accountDays: 2 });
  // Funnel semantics are unchanged: two accounts reached a failure screen.
  assert.equal(report.uniqueAccounts.playback_failed, 2);
  assert.equal(/https?:|cdn\.|token=/.test(JSON.stringify(summary)), false, JSON.stringify(summary).match(/https?:|cdn\.|token=/)?.[0]);
});

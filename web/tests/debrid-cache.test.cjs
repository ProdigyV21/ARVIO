const assert = require('node:assert/strict');
const { test } = require('node:test');
const { load } = require('./load.cjs');

const hash = (index = 1) => index.toString(16).padStart(40, '0');
const info = (overrides = {}) => ({
  provider: 'premiumize', apiKey: 'account-key-a', infoHash: hash(), fileName: 'Episode.mkv', ...overrides
});
const sourceUrl = (source) => `https://resolver.invalid/resolve/${source.provider}/${source.apiKey}/${source.infoHash}`
  + (source.fileName !== undefined || source.fileIndex !== undefined ? `/${encodeURIComponent(source.fileName ?? 'null')}` : '')
  + (source.fileIndex !== undefined ? `/${source.fileIndex}` : '');
const payload = (url = 'https://cdn.invalid/episode.mkv') => ({
  status: 'success', content: [{ path: 'Episode.mkv', size: 100, link: url }]
});
function deferred() {
  let resolve;
  let reject;
  const promise = new Promise((yes, no) => { resolve = yes; reject = no; });
  return { promise, resolve, reject };
}
function setup(handler = () => payload()) {
  const calls = [];
  let now = 1_000;
  class Clock extends Date { static now() { return now; } }
  const debrid = load('lib/debrid.ts', { './http': {
    apiProxiedUrl: (url, headers) => ({ url: new URL(url), headers }),
    jsonRequest: async (target, options) => {
      const call = { ...target, options };
      calls.push(call);
      return handler(call, calls.length);
    }
  } }, { Date: Clock });
  return { ...debrid, calls, advance: (milliseconds) => { now += milliseconds; } };
}

test('direct URLs are isolated by provider, account, hash, filename and file selector', async () => {
  const h = setup((_call, count) => ({ status: 'success', content: [
    { path: 'Episode.mkv', size: 100, link: `https://cdn.invalid/${count}/episode.mkv` },
    { path: 'Other.mkv', size: 200, link: `https://cdn.invalid/${count}/other.mkv` }
  ] }));
  const sources = [info(), info({ apiKey: 'account-key-b' }), info({ infoHash: hash(2) }),
    info({ fileName: 'Other.mkv' }), info({ fileIndex: 0 }), info({ fileIndex: 1 })];
  const results = [];
  for (const source of sources) results.push(await h.resolveDebridDirectUrl(source));
  assert.equal(h.calls.length, sources.length);
  assert.equal(new Set(results.map((result) => result.url)).size, sources.length);
  for (const [index, source] of sources.entries()) {
    assert.equal(h.cachedDebridDirectUrl(sourceUrl(source)), results[index].url);
    assert.equal((await h.resolveDebridDirectUrl(source)).url, results[index].url);
  }
  assert.equal(h.cachedDebridDirectUrl(sourceUrl(info({ provider: 'torbox' }))), null);
  assert.equal(h.calls[1].headers.Authorization, 'Bearer account-key-b');
  assert.equal(h.calls.length, sources.length);
});

test('cache identity preserves delimiter-bearing credentials and filenames', async () => {
  const h = setup((_call, count) => ({ status: 'success', content: [
    { path: 'a:b.mkv', size: 1, link: `https://cdn.invalid/${count}/a.mkv` },
    { path: 'b.mkv', size: 1, link: `https://cdn.invalid/${count}/b.mkv` }
  ] }));
  const first = await h.resolveDebridDirectUrl(info({ apiKey: 'account:key', fileName: 'a:b.mkv' }));
  const second = await h.resolveDebridDirectUrl(info({ apiKey: 'account:key:a', fileName: 'b.mkv' }));
  assert.notEqual(first.url, second.url);
  assert.equal(h.calls.length, 2);
});

test('concurrent resolutions share one promise and one provider request', async () => {
  const gate = deferred();
  const h = setup(() => gate.promise);
  const first = h.resolveDebridDirectUrl(info());
  const second = h.resolveDebridDirectUrl(info());
  assert.equal(first, second);
  await Promise.resolve();
  assert.equal(h.calls.length, 1);
  gate.resolve(payload());
  assert.equal((await first).url, (await second).url);
  assert.equal(h.calls.length, 1);
});

test('different accounts and file indices do not share an in-flight resolution', async () => {
  const gate = deferred();
  const h = setup(() => gate.promise);
  const pending = [info({ fileIndex: 0 }), info({ fileIndex: 1 }), info({ apiKey: 'account-key-b', fileIndex: 0 })]
    .map((source) => h.resolveDebridDirectUrl(source));
  await Promise.resolve();
  assert.equal(h.calls.length, 3);
  gate.resolve(payload());
  await Promise.all(pending);
});

test('cache TTL expires at three minutes and lookup does not extend its lifetime', async () => {
  const h = setup((_call, count) => payload(`https://cdn.invalid/${count}.mkv`));
  const url = sourceUrl(info());
  await h.resolveDebridDirectUrl(info());
  h.advance(179_999);
  assert.equal(h.cachedDebridDirectUrl(url), 'https://cdn.invalid/1.mkv');
  h.advance(1);
  assert.equal(h.cachedDebridDirectUrl(url), null);
  assert.equal((await h.resolveDebridDirectUrl(info())).url, 'https://cdn.invalid/2.mkv');
  h.advance(-1);
  assert.equal(h.cachedDebridDirectUrl(url), null, 'a backwards clock must not prolong a presigned link');
});

test('cache retains at most 100 entries and evicts the least recently used', async () => {
  const h = setup();
  for (let index = 1; index <= 100; index++) await h.resolveDebridDirectUrl(info({ infoHash: hash(index) }));
  const first = sourceUrl(info());
  assert.ok(h.cachedDebridDirectUrl(first));
  await h.resolveDebridDirectUrl(info({ infoHash: hash(101) }));
  assert.ok(h.cachedDebridDirectUrl(first));
  assert.equal(h.cachedDebridDirectUrl(sourceUrl(info({ infoHash: hash(2) }))), null);
  assert.ok(h.cachedDebridDirectUrl(sourceUrl(info({ infoHash: hash(101) }))));
});

test('in-flight requests are bounded without evicting or duplicating active work', async () => {
  const gate = deferred();
  const h = setup(() => gate.promise);
  const pending = [];
  for (let index = 1; index <= 100; index++) pending.push(h.resolveDebridDirectUrl(info({ infoHash: hash(index) })));
  assert.equal(h.resolveDebridDirectUrl(info()), pending[0]);
  assert.match((await h.resolveDebridDirectUrl(info({ infoHash: hash(101) }))).error, /Too many/);
  assert.equal(h.calls.length, 100);
  gate.resolve(payload());
  await Promise.all(pending);
  assert.ok((await h.resolveDebridDirectUrl(info({ infoHash: hash(101) }))).url);
});

test('invalidation is account/file scoped and includes pending resolutions', async () => {
  const h = setup();
  const a = info({ fileIndex: 0 });
  const b = info({ fileIndex: 1 });
  const c = info({ apiKey: 'account-key-b', fileIndex: 0 });
  for (const source of [a, b, c]) await h.resolveDebridDirectUrl(source);
  assert.equal(h.invalidateDebridDirectUrl(sourceUrl(a)), true);
  assert.equal(h.invalidateDebridDirectUrl(sourceUrl(a)), false);
  assert.equal(h.cachedDebridDirectUrl(sourceUrl(a)), null);
  assert.ok(h.cachedDebridDirectUrl(sourceUrl(b)));
  assert.ok(h.cachedDebridDirectUrl(sourceUrl(c)));
  assert.equal(h.invalidateDebridDirectUrl('not a url'), false);
});

for (const oldFinishesFirst of [true, false]) {
  test(`invalidated response cannot overwrite or remove newer work (old first: ${oldFinishesFirst})`, async () => {
    const oldGate = deferred();
    const newGate = deferred();
    const h = setup((_call, count) => count === 1 ? oldGate.promise : newGate.promise);
    const source = info();
    const url = sourceUrl(source);
    const oldPending = h.resolveDebridDirectUrl(source);
    await Promise.resolve();
    assert.equal(h.invalidateDebridDirectUrl(url), true);
    const newPending = h.resolveDebridDirectUrl(source);
    await Promise.resolve();
    if (oldFinishesFirst) {
      oldGate.resolve(payload('https://cdn.invalid/stale.mkv'));
      assert.match((await oldPending).error, /invalidated/);
      assert.equal(h.cachedDebridDirectUrl(url), null);
      assert.equal(h.resolveDebridDirectUrl(source), newPending);
    }
    newGate.resolve(payload('https://cdn.invalid/fresh.mkv'));
    assert.equal((await newPending).url, 'https://cdn.invalid/fresh.mkv');
    if (!oldFinishesFirst) {
      oldGate.resolve(payload('https://cdn.invalid/stale.mkv'));
      assert.match((await oldPending).error, /invalidated/);
    }
    assert.equal(h.cachedDebridDirectUrl(url), 'https://cdn.invalid/fresh.mkv');
    assert.equal(h.calls.length, 2);
  });
}

test('invalidating without a replacement suppresses the stale result and cache write', async () => {
  const gate = deferred();
  const h = setup(() => gate.promise);
  const pending = h.resolveDebridDirectUrl(info());
  await Promise.resolve();
  h.invalidateDebridDirectUrl(sourceUrl(info()));
  gate.resolve(payload());
  assert.match((await pending).error, /invalidated/);
  assert.equal(h.cachedDebridDirectUrl(sourceUrl(info())), null);
  assert.equal(h.invalidateDebridDirectUrl(sourceUrl(info())), false);
});

test('failed resolutions are not cached and release single-flight state for a retry', async () => {
  const h = setup((_call, count) => {
    if (count === 1) throw new Error('fixture failure');
    return payload();
  });
  assert.ok((await h.resolveDebridDirectUrl(info())).error);
  assert.equal(h.cachedDebridDirectUrl(sourceUrl(info())), null);
  assert.ok((await h.resolveDebridDirectUrl(info())).url);
  assert.equal(h.calls.length, 2);
});

test('resolution snapshots inputs and cached URLs cannot be mutated through a returned result', async () => {
  const gate = deferred();
  const h = setup(() => gate.promise);
  const original = info({ infoHash: 'a'.repeat(40) });
  const changing = { ...original, infoHash: original.infoHash.toUpperCase() };
  const pending = h.resolveDebridDirectUrl(changing);
  changing.apiKey = 'changed-key';
  changing.fileName = 'Wrong.mkv';
  await Promise.resolve();
  assert.equal(h.calls[0].headers.Authorization, 'Bearer account-key-a');
  assert.equal(new URLSearchParams(h.calls[0].options.body).get('src'), `magnet:?xt=urn:btih:${original.infoHash}`);
  gate.resolve(payload());
  const result = await pending;
  result.url = 'https://cdn.invalid/modified.mkv';
  assert.equal(h.cachedDebridDirectUrl(sourceUrl(original)), 'https://cdn.invalid/episode.mkv');
  assert.equal((await h.resolveDebridDirectUrl(original)).url, 'https://cdn.invalid/episode.mkv');
  assert.equal(h.calls.length, 1);
});

test('source prefetch never performs network requests or creates provider downloads', async () => {
  const h = setup(() => { throw new Error('prefetch must not reach the provider'); });
  for (const provider of ['torbox', 'realdebrid', 'premiumize', 'alldebrid']) {
    const url = sourceUrl(info({ provider }));
    assert.equal(h.prefetchDebridDirectUrl(url), undefined);
    h.prefetchDebridDirectUrl(url);
  }
  h.prefetchDebridDirectUrl(null);
  h.prefetchDebridDirectUrl('bad url');
  await Promise.resolve();
  assert.equal(h.calls.length, 0);
});

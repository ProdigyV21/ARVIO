const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

function fixture(respond = async () => '{"epg_listings":[]}') {
  let now = 1_000;
  const hits = [];
  class TestDate extends Date { static now() { return now; } }
  const api = load('lib/iptv.ts', {
    './storage': storage(),
    './http': {
      proxiedUrl: (url) => `https://relay.invalid/?url=${encodeURIComponent(url)}`,
      textRequest: async (url) => { hits.push(url); return respond(url); },
    },
  }, { Date: TestDate, setTimeout: (fn, ms) => { now += ms; Promise.resolve().then(fn); return 1; } });
  const url = (id) => `https://provider.invalid/player_api.php?username=test&password=test&action=get_short_epg&stream_id=${id}`;
  return { api, hits, url, advance: (ms) => { now += ms; } };
}

test('5000 requested guide rows issue at most 30 real metadata requests per minute', async () => {
  const f = fixture();
  for (let i = 0; i < 5_000; i++) await f.api.fetchXtreamJson(f.url(i), {}).catch(() => null);
  assert.equal(f.hits.length, 30);
  f.advance(60_001);
  await f.api.fetchXtreamJson(f.url(6000), {});
  assert.equal(f.hits.length, 31);
});

test('overlapping guide calls coalesce and empty guides are negatively cached', async () => {
  const f = fixture();
  await Promise.all(Array.from({ length: 50 }, () => f.api.fetchXtreamJson(f.url(1), {})));
  assert.equal(f.hits.length, 1);
  f.advance(120_001);
  await f.api.fetchXtreamJson(f.url(1), {});
  assert.equal(f.hits.length, 1);
  f.advance(600_001);
  await f.api.fetchXtreamJson(f.url(1), {});
  assert.equal(f.hits.length, 2);
});

test('rate limits and overload do not trigger a proxy or alternate API retry', async () => {
  for (const status of [429, 503, 513]) {
    const f = fixture(async () => { throw Object.assign(new Error('blocked'), { status }); });
    await assert.rejects(f.api.fetchXtreamJson(f.url(1), {}));
    await assert.rejects(f.api.fetchXtreamJson(f.url(2), {}));
    assert.equal(f.hits.length, 1, `HTTP ${status}`);
  }
});

test('direct 401/403 permits one media-header proxy fallback, then locks out on proxy refusal without masking auth as rate limiting', async () => {
  const { playlistErrorCause } = load('lib/iptvGuide.ts');
  for (const status of [401, 403]) {
    const f = fixture(async () => { throw Object.assign(new Error('{"user_info":{"auth":0}}'), { status }); });
    await assert.rejects(f.api.fetchXtreamJson(f.url(1), {}));
    await assert.rejects(f.api.fetchXtreamJson(f.url(2), {}), new RegExp(`HTTP ${status}`));
    assert.equal(f.hits.length, 2, `HTTP ${status}: direct + single proxy fallback only`);

    const playlist = {
      id: 'list_1',
      name: 'SamIam',
      enabled: true,
      m3uUrl: 'https://provider.invalid/get.php?username=test&password=test&type=m3u_plus&output=m3u8'
    };
    const snapshot = await f.api.loadIptvSnapshot([playlist]);
    assert.equal(f.hits.length, 2, 'subsequent snapshot check respects cooldown without hitting network');
    assert.equal(snapshot.playlistErrors.length, 1);
    assert.equal(playlistErrorCause(snapshot.playlistErrors[0].message), 'auth');
  }
});

test('direct 401/403 succeeds via media-header proxy fallback and remembers proxy preference for the host', async () => {
  const f = fixture(async (url) => {
    if (!url.startsWith('https://relay.invalid/')) {
      throw Object.assign(new Error('{"user_info":{"auth":0}}'), { status: 401 });
    }
    if (url.includes('get_live_categories')) return JSON.stringify([{ category_id: '1', category_name: 'News' }]);
    if (url.includes('get_live_streams')) return JSON.stringify([{ stream_id: 10, name: 'News HD', category_id: '1' }]);
    return '{"epg_listings":[]}';
  });
  const snapshot = await f.api.loadIptvSnapshot([{
    id: 'list_1',
    name: 'SamIam',
    enabled: true,
    m3uUrl: 'https://provider.invalid/get.php?username=test&password=test&type=m3u_plus&output=m3u8'
  }]);
  assert.equal(snapshot.playlistErrors.length, 0);
  assert.equal(snapshot.channels.length, 1);
  assert.equal(snapshot.channels[0].name, 'News HD');
  const hitsBefore = f.hits.length;
  await f.api.fetchXtreamJson(f.url(99), {});
  assert.equal(f.hits.length, hitsBefore + 1, 'subsequent call skips failing direct hop');
  assert.ok(f.hits.at(-1).startsWith('https://relay.invalid/'));
});

test('Retry-After HTTP dates are respected across guide and catalog calls', async () => {
  let reject = true;
  const f = fixture(async () => {
    if (reject) throw Object.assign(new Error('busy'), { status: 429, retryAfter: 'Thu, 1 Jan 1970 00:30:01 GMT' });
    return '{"epg_listings":[]}';
  });
  await assert.rejects(f.api.fetchXtreamJson(f.url(1), {}));
  reject = false;
  f.advance(600_000);
  await assert.rejects(f.api.fetchXtreamJson(f.url(2).replace('get_short_epg', 'get_live_streams'), {}));
  assert.equal(f.hits.length, 1);
  f.advance(1_200_001);
  await f.api.fetchXtreamJson(f.url(2), {});
  assert.equal(f.hits.length, 2);
});

test('browser CORS failure still permits one bounded proxy fallback', async () => {
  const f = fixture(async (url) => {
    if (!url.startsWith('https://relay.invalid/')) throw new TypeError('Failed to fetch');
    return '{"epg_listings":[]}';
  });
  await f.api.fetchXtreamJson(f.url(1), {});
  assert.equal(f.hits.length, 2);
});

test('HTML returned with HTTP 200 still permits the media-header proxy fallback', async () => {
  const f = fixture(async (url) => url.startsWith('https://relay.invalid/') ? '{"epg_listings":[]}' : '<html>Not JSON</html>');
  await f.api.fetchXtreamJson(f.url(1), {});
  assert.equal(f.hits.length, 2);
});

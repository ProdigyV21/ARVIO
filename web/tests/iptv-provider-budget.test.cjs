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
      proxiedUrl: (url, headers) => {
        const proxy = new URL('https://relay.invalid/');
        proxy.searchParams.set('url', url);
        if (headers) proxy.searchParams.set('headers', btoa(JSON.stringify(headers)));
        return proxy.toString();
      },
      textRequest: async (url) => { hits.push(url); return respond(url); },
    },
  }, { Date: TestDate, Error, TypeError, setTimeout: (fn, ms) => { now += ms; Promise.resolve().then(fn); return 1; } });
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

test('direct 401/403 succeeds via media-header proxy fallback and remembers the API route preference', async () => {
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
  await f.api.fetchXtreamJson(f.url(99), f.api.playlistProxyHeaders());
  assert.equal(f.hits.length, hitsBefore + 1, 'subsequent call skips failing direct hop');
  assert.ok(f.hits.at(-1).startsWith('https://relay.invalid/'));
});

test('API proxy preference does not skip working direct playlist and EPG endpoints or block later API calls', async () => {
  const f = fixture(async (raw) => {
    const request = new URL(raw);
    const proxy = request.hostname === 'relay.invalid';
    const target = proxy ? new URL(request.searchParams.get('url')) : request;
    if (target.pathname === '/player_api.php') {
      if (!proxy) throw new TypeError('Failed to fetch');
      return '{"epg_listings":[]}';
    }
    if (proxy) throw Object.assign(new Error('Forbidden proxy user agent'), { status: 403 });
    if (target.pathname === '/channels.m3u') return '#EXTM3U\n#EXTINF:-1 tvg-id="news",News\nhttps://streams.invalid/news.m3u8';
    return '<tv><programme start="19700101000001 +0000" stop="19700102000000 +0000" channel="news"><title>News</title></programme></tv>';
  });
  const headers = f.api.playlistProxyHeaders();
  await f.api.fetchXtreamJson(f.url(1), headers);
  const playlists = [{ id: 'list_1', name: 'Test', enabled: true,
    m3uUrl: 'https://provider.invalid/channels.m3u', epgUrl: 'https://provider.invalid/guide.xml' }];
  const snapshot = await f.api.loadIptvSnapshot(playlists);
  assert.equal(snapshot.channels.length, 1);
  const guide = await f.api.loadIptvGuideForChannels(playlists, snapshot.channels);
  assert.equal(Object.keys(guide).length, 1);
  assert.equal(guide[snapshot.channels[0].id].now.title, 'News');
  assert.ok(f.hits.includes(playlists[0].m3uUrl));
  assert.ok(f.hits.includes(playlists[0].epgUrl));
  const hitsBefore = f.hits.length;
  await f.api.fetchXtreamJson(f.url(2), headers);
  assert.equal(f.hits.length, hitsBefore + 1, 'API preference still skips its failing direct hop');
});

test('XMLTV proxy fallback carries media-player headers after a direct auth refusal', async () => {
  for (const status of [401, 403]) {
    const f = fixture(async (raw) => {
      const request = new URL(raw);
      if (request.pathname === '/channels.m3u') return '#EXTM3U\n#EXTINF:-1 tvg-id="news",News\nhttps://streams.invalid/news.m3u8';
      if (request.hostname !== 'relay.invalid') throw Object.assign(new Error('Forbidden browser user agent'), { status });
      const headers = JSON.parse(atob(request.searchParams.get('headers') || btoa('{}')));
      assert.match(headers['User-Agent'] || '', /^VLC\//);
      return '<tv><programme start="19700101000001 +0000" stop="19700102000000 +0000" channel="news"><title>News</title></programme></tv>';
    });
    const playlists = [{ id: 'list_1', name: 'Test', enabled: true,
      m3uUrl: 'https://provider.invalid/channels.m3u', epgUrl: 'https://provider.invalid/guide.xml' }];
    const snapshot = await f.api.loadIptvSnapshot(playlists);
    const guide = await f.api.loadIptvGuideForChannels(playlists, snapshot.channels);
    assert.equal(Object.keys(guide).length, 1);
    assert.equal(f.hits.length, 3, 'one playlist load plus direct EPG and one proxy fallback');
  }
});

test('API proxy preferences are isolated by endpoint, account and request headers and expire', async () => {
  const f = fixture(async (raw) => {
    const request = new URL(raw);
    if (request.hostname !== 'relay.invalid') throw Object.assign(new Error('Forbidden'), { status: 403 });
    return '{"epg_listings":[]}';
  });
  const headers = f.api.playlistProxyHeaders();
  await f.api.fetchXtreamJson(f.url(1), headers);
  let hitsBefore = f.hits.length;
  await f.api.fetchXtreamJson(f.url(2), headers);
  assert.equal(f.hits.length, hitsBefore + 1);
  for (const [url, changedHeaders] of [
    [f.url(3).replace('/player_api.php', '/other/player_api.php'), headers],
    [f.url(4).replace('username=test', 'username=other'), headers],
    [f.url(5), f.api.playlistProxyHeaders('CustomPlayer/1.0')],
  ]) {
    hitsBefore = f.hits.length;
    await f.api.fetchXtreamJson(url, changedHeaders);
    assert.equal(f.hits.length, hitsBefore + 2, 'a different request context tries its own direct route');
  }
  f.advance(900_001);
  hitsBefore = f.hits.length;
  await f.api.fetchXtreamJson(f.url(6), headers);
  assert.equal(f.hits.length, hitsBefore + 2, 'expired preferences retry the direct route');
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

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

test('VOD checks every eligible provider, coalesces requests, and never fetches disabled content', async () => {
  const calls = [];
  const vod = load('lib/xtreamVod.ts', { './iptv': {
    xtreamInfoFromUrl: value => { const u = new URL(value); return { baseUrl: u.origin, username: u.searchParams.get('username'), password: u.searchParams.get('password') }; },
    playlistProxyHeaders: () => ({}),
    buildXtreamPlayerApiUrl: (base, user, pass, action) => `${base}/api?username=${user}&password=${pass}&action=${action}`,
    fetchXtreamJson: async value => {
      const u = new URL(value); calls.push(u); await new Promise(setImmediate);
      if (u.hostname === 'broken.test') throw Error('offline');
      return [{ tmdb: 10, stream_id: 1, name: 'Movie', container_extension: 'mp4' }];
    }
  } });
  const playlist = (host, extra = {}) => ({ enabled: true, name: host, m3uUrl: `https://${host}/get.php?username=qa&password=pass`, ...extra });
  const lists = [playlist('broken.test'), playlist('a.test'), playlist('b.test'), playlist('disabled.test', { importVod: false }), playlist('a.test')];
  const [sources, second] = await Promise.all([vod.findMovieVodSources(lists, { id: 10, title: 'Movie' }), vod.findMovieVodSources(lists, { id: 10, title: 'Movie' })]);
  assert.deepEqual(Array.from(sources, s => new URL(s.url).hostname), ['a.test', 'b.test']);
  assert.equal(second.length, 2);
  assert.equal(calls.filter(u => u.hostname === 'a.test').length, 1);
  assert.equal(calls.filter(u => u.hostname === 'b.test').length, 1);
  assert.equal(calls.some(u => u.hostname === 'disabled.test'), false);
  assert.equal(calls.some(u => u.searchParams.get('action') !== 'get_vod_streams'), false);
  const before = calls.length;
  assert.equal((await vod.findEpisodeVodSource([playlist('a.test', { importSeries: false })], { id: 10, title: 'Movie' }, 1, 1)).length, 0);
  assert.equal(calls.length, before);
  await vod.findMovieVodSources([playlist('a.test', { m3uUrl: 'https://a.test/get.php?username=qa&password=rotated' })], { id: 10, title: 'Movie' });
  assert.equal(calls.at(-1).searchParams.get('password'), 'rotated', 'credential rotation cannot reuse a different line cache');
});

test('series discovery retains HD alternatives when the first variant fails or lacks the requested episode', async () => {
  const queried = [];
  let active = 0, peak = 0;
  const vod = load('lib/xtreamVod.ts', { './iptv': {
    xtreamInfoFromUrl: () => ({ baseUrl: 'https://provider.example', username: 'fixture', password: 'test-only' }),
    playlistProxyHeaders: () => ({ 'User-Agent': 'Fixture' }),
    buildXtreamPlayerApiUrl: (_base, _user, _pass, action) => `https://provider.example/api?action=${action}`,
    fetchXtreamJson: async url => {
      const parsed = new URL(url), action = parsed.searchParams.get('action');
      if (action === 'get_vod_streams') return [];
      if (action === 'get_series') return [
        { series_id: 1, tmdb: 20, name: '4K Fixture' },
        { series_id: 2, tmdb: 20, name: '1080p Fixture' },
        { series_id: 2, tmdb: 20, name: 'Duplicate' },
        { series_id: 3, tmdb: 20, name: 'Unavailable' },
        { series_id: 4, tmdb: 20, name: '720p Fixture' },
        { series_id: 5, tmdb: 99, name: 'Different show' }
      ];
      const id = Number(parsed.searchParams.get('series_id')); queried.push(id); peak = Math.max(peak, ++active);
      await new Promise(setImmediate); active--;
      if (id === 1) return { episodes: { '2': [{ id: 100, episode_num: 1 }] } };
      if (id === 3) throw new Error('provider unavailable');
      return { episodes: { '2': [{ id: id * 100, episode_num: 3, title: 'Episode three', container_extension: 'mp4' }] } };
    }
  } });
  const sources = await vod.findEpisodeVodSource([{ enabled: true, m3uUrl: 'https://provider.example/get.php' }], { id: 20, title: 'Fixture' }, 2, 3);
  assert.deepEqual(Array.from(sources, s => s.quality), ['1080p', '720p']);
  assert.deepEqual(Array.from(sources, s => new URL(s.url).pathname.split('/').pop()), ['200.mp4', '400.mp4']);
  assert.deepEqual(queried.sort(), [1, 2, 3, 4]);
  assert.ok(peak <= 2, 'provider lookups are bounded');
});

test('IPTV movies and episodes retain the catalogue player identity through browser preparation', async () => {
  const requests = [];
  const headersFor = ua => ({ Accept: '*/*', 'User-Agent': ua || 'Default IPTV player', 'Icy-MetaData': '1' });
  const vod = load('lib/xtreamVod.ts', { './iptv': {
    xtreamInfoFromUrl: () => ({ baseUrl: 'http://provider.example', username: 'fixture', password: 'test-only' }),
    playlistProxyHeaders: headersFor,
    buildXtreamPlayerApiUrl: (_base, _user, _pass, action) => `https://provider.example/api?action=${action}`,
    fetchXtreamJson: async (url, headers) => {
      requests.push(headers);
      const action = new URL(url).searchParams.get('action');
      if (action === 'get_vod_streams') return [{ tmdb: 10, name: 'Fixture movie', stream_id: 100, container_extension: 'mp4' }];
      if (action === 'get_series') return [{ tmdb: 20, name: 'Fixture show', series_id: 200 }];
      return { episodes: { '1': [{ id: 201, episode_num: 2, container_extension: 'mp4' }] } };
    }
  } });
  const prepare = load('lib/prepareBrowserStream.ts', {
    './debrid': { cachedDebridDirectUrl: () => null, parseDebridStream: () => null },
    './streamCompatibility': { playbackPlan: () => ({ route: 'here', method: 'direct' }), canTryRemux: () => false,
      streamTransport: () => 'file', streamContainer: () => 'mp4' },
    './homeServerPlayback': {},
    './resolver': load('lib/resolver.ts', { './config': { config: { resolverUrl: 'https://relay.example' } } })
  });
  for (const ua of [undefined, 'Configured player']) {
    const playlists = [{ enabled: true, m3uUrl: 'https://provider.example/get.php' }];
    const movies = await vod.findMovieVodSources(playlists, { id: 10, title: 'Fixture movie' }, ua);
    const episodes = await vod.findEpisodeVodSource(playlists, { id: 20, title: 'Fixture show' }, 1, 2, ua);
    for (const source of [...movies, ...episodes]) {
      assert.equal(source.behaviorHints.proxyHeaders.request['User-Agent'], headersFor(ua)['User-Agent']);
      const result = await prepare.prepareBrowserStream(source, {});
      assert.equal(result.url, source.url, 'IPTV keeps the subscriber-IP native path first');
      assert.equal(result.remux, false);
      assert.deepEqual(result.behaviorHints.proxyHeaders.request, headersFor(ua));
      const repackaged = await prepare.prepareBrowserStream(source, {}, { forceRemux: true });
      const relay = new URL(repackaged.url);
      assert.equal(relay.origin, 'https://relay.example');
      assert.equal(relay.searchParams.get('url'), source.url);
      assert.deepEqual(JSON.parse(atob(relay.searchParams.get('h'))), headersFor(ua));
      assert.equal(repackaged.originalUrl, source.url);
      assert.equal(repackaged.behaviorHints.proxyHeaders.request, undefined);
    }
  }
  assert.ok(requests.length >= 3);
});

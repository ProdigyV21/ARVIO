const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const { repairIptvPlaylistIds, MAX_IPTV_PLAYLISTS } = load('lib/iptvPlaylistIdentity.ts');
const iptv = load('lib/iptv.ts', { './http': {}, './storage': storage() });
const plain = x => JSON.parse(JSON.stringify(x));
const lists = Array.from({ length: 10 }, (_, i) => ({ id: `list_${i + 1}`, name: `Playlist ${i + 1}`, m3uUrl: `https://example.invalid/${i + 1}.m3u`, enabled: true }));

test('all ten providers survive normalization in the original order', () => {
  assert.equal(MAX_IPTV_PLAYLISTS, 10);
  const result = iptv.normalizeIptvPlaylists(lists);
  assert.deepEqual(plain(result.map(p => p.id)), lists.map(p => p.id));
  assert.deepEqual(plain(result.map(p => p.m3uUrl)), lists.map(p => p.m3uUrl));
});

test('legacy duplicate IDs keep both sources and repair deterministically', () => {
  const input = [lists[0], lists[2], { ...lists[3], id: 'list_3' }];
  const result = iptv.normalizeIptvPlaylists(input);
  assert.equal(new Set(result.map(p => p.id)).size, 3);
  assert.deepEqual(plain(result.map(p => p.m3uUrl)), input.map(p => p.m3uUrl));
  assert.deepEqual(plain(repairIptvPlaylistIds(result)), plain(result));
});

test('repair reserves IDs used later in the same configuration', () => {
  const result = repairIptvPlaylistIds([lists[0], lists[0], { ...lists[2], id: 'list_recovered_2' }]);
  assert.deepEqual(plain(result.map(p => p.id)), ['list_1', 'list_recovered_2_1', 'list_recovered_2']);
});

test('ten playlists load ten independently addressable channel sets', async () => {
  const requested = [];
  const runtime = load('lib/iptv.ts', {
    './storage': storage(),
    './http': {
      proxiedUrl: url => url,
      textRequest: async url => {
        requested.push(url);
        const n = new URL(url).pathname.split('/').pop().split('.')[0];
        return `#EXTM3U\n#EXTINF:-1 group-title="News",Channel ${n}\nhttps://streams.example.invalid/${n}.m3u8`;
      },
    },
  });
  const snapshot = await runtime.loadIptvSnapshot(lists);
  assert.equal(requested.length, 10);
  assert.equal(snapshot.playlistErrors.length, 0);
  assert.deepEqual(plain(snapshot.channels.map(c => c.id.split(':')[0])), lists.map(p => p.id));
  assert.equal(new Set(snapshot.channels.map(c => c.id)).size, 10);
});

test('a failing third playlist does not discard channels from the other nine', async () => {
  const runtime = load('lib/iptv.ts', {
    './storage': storage(),
    './http': {
      proxiedUrl: url => url,
      textRequest: async url => {
        if (new URL(url).pathname === '/3.m3u') throw new Error('Provider unavailable');
        return '#EXTM3U\n#EXTINF:-1,Channel\nhttps://streams.example.invalid/live.m3u8';
      },
    },
  });
  const snapshot = await runtime.loadIptvSnapshot(lists);
  assert.equal(snapshot.channels.length, 9);
  assert.deepEqual(plain(snapshot.playlistErrors.map(p => p.playlistId)), ['list_3']);
});

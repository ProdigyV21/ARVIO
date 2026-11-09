import { test } from 'node:test';
import assert from 'node:assert/strict';
import http from 'node:http';
import { createBridge, candidateRejection, publicAddress } from './bridge.mjs';

test('refuses private network targets, unsupported protocols, DRM and runtime-only streams', () => {
  for (const address of ['127.0.0.1', '10.1.2.3', '172.16.0.2', '192.168.0.1', '169.254.169.254', '100.64.0.1', '::1', '::ffff:127.0.0.1', 'fd00::1', 'fe80::1']) assert.equal(publicAddress(address), false, address);
  assert.equal(publicAddress('1.1.1.1'), true);
  assert.equal(publicAddress('2606:4700:4700::1111'), true);
  const link = { url: 'https://example.org/video.mp4', type: 'VIDEO' };
  assert.equal(candidateRejection(link), null);
  assert.equal(candidateRejection({ ...link, drm: true }), 'drm');
  assert.equal(candidateRejection({ ...link, interceptor: true }), 'requires-runtime-interceptor');
  assert.equal(candidateRejection({ ...link, url: 'http://example.org' }), 'unsafe-url');
  assert.equal(candidateRejection({ ...link, url: 'https://user:secret@example.org' }), 'unsafe-url');
});

test('existing Stremio contract: search, metadata, exact episode, direct URL, deduplication, cache, no relay', async t => {
  const calls = [];
  const { server, metrics } = createBridge({ token: 'x'.repeat(32), workerFetch: async raw => {
    const url = new URL(raw); calls.push(url);
    const data = url.pathname === '/search' ? { items: [{ name: 'Test series', type: 'TvSeries', url: 'https://metadata.example/1' }] }
      : url.pathname === '/load' ? { name: 'Test series', episodes: [{ season: 2, episode: 3, name: 'Third' }] }
      : { done: true, links: [
        { url: 'https://media.example/episode.m3u8', type: 'M3U8', quality: 1080, headers: { Cookie: 'secret' }, source: 'Example' },
        { url: 'https://media.example/episode.m3u8', type: 'M3U8', quality: 1080 },
        { url: 'https://media.example/drm.mpd', type: 'DASH', drm: true },
        { url: 'https://media.example/blocked.mp4', type: 'VIDEO' }
      ] };
    return new Response(JSON.stringify(data));
  }, probe: async url => ({ ok: !url.includes('blocked'), reason: 'cors' }) });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  t.after(() => server.close());
  const base = `http://127.0.0.1:${server.address().port}`;
  const get = path => fetch(base + path).then(r => r.json());
  const catalog = await get('/catalog/series/streamplay/search=Test.json');
  const id = catalog.metas[0].id;
  const { meta } = await get(`/meta/series/${id}.json`);
  assert.equal(meta.videos[0].id, `${id}:2:3`);
  const [a, b] = await Promise.all([get(`/stream/series/${id}:2:3.json`), get(`/stream/series/${id}:2:3.json`)]);
  assert.equal(a.streams.length, 1);
  assert.deepEqual(a, b);
  assert.equal(a.streams[0].url, 'https://media.example/episode.m3u8');
  assert.equal(a.streams[0].transport, 'hls');
  assert.equal(JSON.stringify(a).includes('secret'), false);
  assert.equal(JSON.stringify(a).includes('proxyHeaders'), false);
  await get(`/stream/series/${id}:2:3.json`);
  const resolutions = calls.filter(c => c.pathname === '/resolve');
  assert.equal(resolutions.length, 1);
  assert.equal(resolutions[0].searchParams.get('season'), '2');
  assert.equal(resolutions[0].searchParams.get('episode'), '3');
  assert.equal(metrics.mediaRelayBytes, 0);
  assert.equal((await fetch(base + '/media?url=https://media.example')).status, 404);
  assert.equal((await fetch(base + '/manifest.json', { headers: { Origin: 'https://evil.example' } })).status, 403);
  const reboundStatus = await new Promise(resolve => http.get(base + '/manifest.json', { headers: { Host: 'evil.example' } }, r => { r.resume(); resolve(r.statusCode); }));
  assert.equal(reboundStatus, 403);
  assert.equal((await fetch(base + '/stream/movie/cs-poc:000000000000000000000000.json')).status, 404);
  await get('/stream/movie/tmdb:45745.json');
  assert.deepEqual(JSON.parse(calls.filter(c => c.pathname === '/resolve').at(-1).searchParams.get('url')),
    { id: 45745, type: 'movie', aniId: null, malId: null });
  assert.equal((await get('/plugins.json'))[0].version, 694);
  // Restored cached native IDs work without having searched this process first.
  assert.equal((await get('/meta/movie/cs-poc:45745.json')).meta.id, 'cs-poc:45745');
});

test('probes alternative hosts instead of stopping on completed extraction or same-host variants', async t => {
  const probed = [];
  const { server } = createBridge({ token: 'x'.repeat(32), workerFetch: async () => new Response(JSON.stringify({ done: true, links: [
    ...Array.from({ length: 8 }, (_, i) => ({ url: `https://blocked.example/${i}.mp4`, type: 'VIDEO', quality: 2160 - i })),
    ...Array.from({ length: 5 }, (_, i) => ({ url: `https://other${i}.example/manifest`, type: 'M3U8', quality: 720 }))
  ] })), probe: async url => { probed.push(url); return { ok: url.includes('other4'), reason: 'cors' }; } });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  t.after(() => server.close());
  const result = await fetch(`http://127.0.0.1:${server.address().port}/stream/movie/tmdb:45745.json`).then(r => r.json());
  assert.equal(result.streams.length, 1);
  assert.equal(result.streams[0].transport, 'hls');
  assert.ok(probed.indexOf('https://other0.example/manifest') < probed.indexOf('https://blocked.example/1.mp4'));
});

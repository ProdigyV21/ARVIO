const test = require('node:test');
const assert = require('node:assert/strict');
const sharp = require('sharp');
const { load } = require('./load.cjs');

const next = { NextResponse: Response };
const publicUrl = 'https://provider.example/metadata';
const hosted = { NODE_ENV: 'production', NETLIFY: 'true' };
const video = Buffer.from([0, 0, 0, 24, 102, 116, 121, 112, 105, 115, 111, 109]);
const png = Buffer.from('iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAwMCAO+aWQAAAABJRU5ErkJggg==', 'base64');
const flush = () => new Promise(setImmediate);

function proxy(respond, env = hosted, lookup) {
  const calls = [];
  const agents = [];
  const api = load('lib/server/safeProxy.ts', {
    'node:dns/promises': { lookup: lookup || (async () => [{ address: '8.8.8.8', family: 4 }]) },
    sharp,
    undici: {
      Agent: class {
        constructor() { this.destroyed = 0; agents.push(this); }
        async destroy() { this.destroyed++; }
      },
      fetch: async (url, init) => { calls.push({ url: String(url), init }); return respond(url, init); }
    }
  }, { process: { env }, Error });
  return { ...api, calls, agents };
}

function route(relative, api, env = hosted) {
  return load(relative, { 'next/server': next, '@/lib/server/safeProxy': api }, { process: { env }, Error });
}

function request(target, params = {}, init) {
  const url = new URL('https://web.example/api/proxy');
  url.searchParams.set('url', target);
  for (const [key, value] of Object.entries(params)) url.searchParams.set(key, value);
  return new Request(url, init);
}

for (const env of [
  { ...hosted, ALLOW_NETLIFY_MEDIA_PROXY: 'true' },
  { ...hosted, NEXT_PUBLIC_ALLOW_NETLIFY_MEDIA_PROXY: 'true' },
  { NODE_ENV: 'production', ALLOW_NETLIFY_MEDIA_PROXY: 'true', NEXT_PUBLIC_ALLOW_NETLIFY_MEDIA_PROXY: 'true' },
  { NEXT_PUBLIC_ALLOW_NETLIFY_MEDIA_PROXY: 'true' }
]) test(`hosted/browser flags cannot enable media relay: ${JSON.stringify(env)}`, async () => {
  const api = proxy(() => new Response(video, { headers: { 'content-type': 'video/mp4' } }), env);
  assert.equal(api.allowsMediaProxy(), false);
  await assert.rejects(api.safeProxyFetch(new URL(publicUrl), {}), /not allowed/);
  assert.equal(api.agents[0].destroyed, 1);
});

test('explicit server-only development opt-in stays local, and cannot override subtitle text-only policy', async () => {
  const api = proxy(() => new Response(video, { headers: { 'content-type': 'video/mp4' } }), { ALLOW_NETLIFY_MEDIA_PROXY: 'true' });
  assert.equal(api.allowsMediaProxy(), true);
  assert.deepEqual(Buffer.from(await (await api.safeProxyFetch(new URL(publicUrl), {})).arrayBuffer()), video);
  await assert.rejects(api.safeProxyFetch(new URL(publicUrl), {}, { textOnly: true }), /not allowed/);
});

for (const type of ['application/octet-stream', 'text/plain', 'application/json', 'application/vnd.apple.mpegurl', '']) {
  test(`binary video is rejected even when labeled ${type || 'without a content type'}`, async () => {
    const api = proxy(() => new Response(video, { headers: type ? { 'content-type': type } : {} }));
    await assert.rejects(api.safeProxyFetch(new URL(publicUrl), {}), /Binary media/);
    assert.equal(api.agents[0].destroyed, 1);
  });
}

test('binary content arriving after an innocuous text prefix is not forwarded', async () => {
  const api = proxy(() => new Response(new ReadableStream({
    start(controller) { controller.enqueue(Buffer.from('{"title":"')); controller.enqueue(video); controller.close(); }
  }), { headers: { 'content-type': 'application/json' } }));
  const response = await api.safeProxyFetch(new URL(publicUrl), {});
  await assert.rejects(response.text(), /Binary media/);
  assert.equal(api.agents[0].destroyed, 1);
});

test('encoded Range headers, partial responses and redirect media extensions cannot bypass the policy', async () => {
  const api = proxy(() => new Response(null, { status: 302, headers: { location: '/film%2Emp4' } }));
  await assert.rejects(api.safeProxyFetch(new URL(publicUrl), { headers: { Range: 'bytes=0-1023' } }), /Media proxy disabled/);
  assert.equal(api.calls.length, 0);
  await assert.rejects(api.safeProxyFetch(new URL(publicUrl), {}), /Media proxy disabled/);
  assert.equal(api.calls.length, 1);
  const partial = proxy(() => new Response('printable fragment', { status: 206 }));
  await assert.rejects(partial.safeProxyFetch(new URL(publicUrl), {}), /not allowed/);
});

test('provider JSON/XML/playlist text survives incorrect octet-stream MIME types', async () => {
  for (const text of ['{"MediaSources":[{"Id":"version"}]}', '<MediaContainer size="0"/>', '#EXTM3U\nhttps://provider.example/live.ts']) {
    const api = proxy(() => new Response(text, { headers: { 'content-type': 'application/octet-stream' } }));
    assert.equal(await (await api.safeProxyFetch(new URL(publicUrl), {})).text(), text);
    assert.equal(api.agents[0].destroyed, 1);
  }
});

test('JSON with multibyte text split across chunks is retained', async () => {
  const data = Buffer.from('{"title":"\u00e9"}');
  const api = proxy(() => new Response(new ReadableStream({ start(controller) {
    controller.enqueue(data.subarray(0, 11)); controller.enqueue(data.subarray(11)); controller.close();
  } })));
  assert.equal(await (await api.safeProxyFetch(new URL(publicUrl), {})).text(), data.toString());
});

test('declared and streaming byte limits cancel upstream without trusting Content-Length', async () => {
  for (const declared of [true, false]) {
    let cancelled = false;
    const api = proxy(() => new Response(new ReadableStream({
      pull(controller) { controller.enqueue(Buffer.from('123456789')); },
      cancel() { cancelled = true; }
    }, { highWaterMark: 0 }), { headers: declared ? { 'content-length': '9' } : {} }));
    await assert.rejects(api.safeProxyFetch(new URL(publicUrl), {}, { maxBytes: 8 }), /size limit/);
    assert.equal(cancelled, true);
    assert.equal(api.agents[0].destroyed, 1);
  }
});

test('small complete images are allowed but a video labeled as an image is rejected', async () => {
  const source = await sharp({ create: { width: 2, height: 2, channels: 3, background: '#ffffff' } }).png().toBuffer();
  const api = proxy(() => new Response(source, { headers: { 'content-type': 'image/png' } }));
  const response = await api.safeProxyFetch(new URL(publicUrl), {});
  const output = Buffer.from(await response.arrayBuffer());
  const image = await sharp(output).metadata();
  assert.deepEqual(output, source);
  assert.equal(response.headers.get('content-type'), 'image/png');
  assert.equal(image.width, 2);
  assert.equal(image.height, 2);
  for (const type of ['image/png', 'image/jpeg', 'image/gif', 'image/webp', 'image/avif']) {
    const bad = proxy(() => new Response(video, { headers: { 'content-type': type } }));
    await assert.rejects(bad.safeProxyFetch(new URL(publicUrl), {}), /Invalid proxy image/);
    assert.equal(bad.agents[0].destroyed, 1);
  }
});

test('SVG stays unchanged and bounded, with its sandbox policy retained through the public route', async () => {
  const svg = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"><rect width="20" height="20"/></svg>';
  const api = proxy(() => new Response(svg, { headers: { 'content-type': 'image/svg+xml' } }));
  const response = await route('app/api/proxy/route.ts', api).GET(request(publicUrl));
  assert.equal(await response.text(), svg);
  assert.equal(response.headers.get('content-type'), 'image/svg+xml');
  assert.equal(response.headers.get('x-content-type-options'), 'nosniff');
  assert.match(response.headers.get('content-security-policy'), /sandbox; default-src 'none'/);
  const large = proxy(() => new Response(svg, { headers: { 'content-type': 'image/svg+xml', 'content-length': String(4 * 1024 * 1024 + 1) } }));
  await assert.rejects(large.safeProxyFetch(new URL(publicUrl), {}), /size limit/);
});

test('images have a separate 4 MiB ceiling, not the large text catalog allowance', async () => {
  const api = proxy(() => new Response(png, { headers: { 'content-type': 'image/png', 'content-length': String(4 * 1024 * 1024 + 1) } }));
  await assert.rejects(api.safeProxyFetch(new URL(publicUrl), {}), /size limit/);
});

test('pre-aborted requests do no DNS/network work', async () => {
  let lookups = 0;
  const api = proxy(() => new Response('{}'), hosted, async () => { lookups++; return []; });
  await assert.rejects(api.safeProxyFetch(new URL(publicUrl), { signal: AbortSignal.abort() }), { name: 'AbortError' });
  assert.equal(lookups, 0);
  assert.equal(api.calls.length, 0);
});

test('caller cancellation reaches an in-flight fetch and releases its dispatcher', async () => {
  const caller = new AbortController();
  const api = proxy((_url, init) => new Promise((_resolve, reject) => {
    init.signal.addEventListener('abort', () => reject(init.signal.reason), { once: true });
  }));
  const pending = api.safeProxyFetch(new URL(publicUrl), { signal: caller.signal });
  await flush(); caller.abort();
  await assert.rejects(pending, { name: 'AbortError' });
  assert.equal(api.agents[0].destroyed, 1);
});

test('disconnect cancels an unread response body and does not leave its upstream open', async () => {
  let cancelled = false;
  const caller = new AbortController();
  const api = proxy(() => new Response(new ReadableStream({
    start(controller) { controller.enqueue(Buffer.from('{}')); },
    cancel() { cancelled = true; }
  })));
  const response = await api.safeProxyFetch(new URL(publicUrl), { signal: caller.signal });
  caller.abort(); await flush();
  assert.equal(cancelled, true);
  assert.equal(api.agents[0].destroyed, 1);
  await response.body.cancel().catch(() => {});
});

test('proxy catalog rewrites keep video URLs off Netlify and cache account-specific catalogs by full query', async () => {
  const api = proxy(() => new Response('#EXTM3U\n#EXTINF:-1,Example\nlive.ts', { headers: { 'content-type': 'audio/x-mpegurl' } }));
  const { GET } = route('app/api/proxy/route.ts', api);
  const response = await GET(request('https://provider.example/get.php?username=user&password=fixture&type=m3u_plus'));
  assert.equal(response.status, 200);
  assert.equal(await response.text(), '#EXTM3U\n#EXTINF:-1,Example\nhttps://provider.example/live.ts');
  assert.equal(response.headers.get('netlify-vary'), 'query');
  assert.match(response.headers.get('netlify-cdn-cache-control'), /max-age=3600/);
});

test('HLS manifests remain small/direct and are never cached as hour-long IPTV catalogs', async () => {
  const api = proxy(() => new Response('#EXTM3U\n#EXT-X-TARGETDURATION:6\nsegment.ts', { headers: { 'content-type': 'application/vnd.apple.mpegurl' } }));
  const { GET } = route('app/api/proxy/route.ts', api);
  for (const [filename, rewrite] of [['live.m3u8', '0'], ['live.m3u8', 'direct'], ['live.m3u', '0'], ['get.php', 'direct']]) {
    const response = await GET(request(`https://provider.example/${filename}`, { rewrite }));
    assert.equal(response.status, 200);
    assert.equal(response.headers.get('netlify-cdn-cache-control'), null);
    assert.equal(response.headers.get('cache-control'), 'private, max-age=5');
    assert.doesNotMatch(await response.text(), /\/api\/proxy/);
  }
  const large = proxy(() => new Response('#EXTM3U', { headers: { 'content-length': String(2 * 1024 * 1024 + 1) } }));
  assert.equal((await route('app/api/proxy/route.ts', large).GET(request('https://provider.example/live.m3u8', { rewrite: 'direct' }))).status, 502);
});

test('encoded Range in proxy header parameters is rejected before fetching', async () => {
  const api = proxy(() => new Response('{}'));
  const response = await route('app/api/proxy/route.ts', api).GET(request(publicUrl,
    { headers: Buffer.from(JSON.stringify({ Range: 'bytes=0-8191' })).toString('base64') }));
  assert.equal(response.status, 502);
  assert.equal(api.calls.length, 0);
});

test('late binary playlist errors become an uncached failure instead of returning video bytes', async () => {
  const api = proxy(() => new Response(new ReadableStream({ start(controller) {
    controller.enqueue(Buffer.from('#EXTM3U\n')); controller.enqueue(video); controller.close();
  } }), { headers: { 'content-type': 'application/vnd.apple.mpegurl' } }));
  const response = await route('app/api/proxy/route.ts', api).GET(request('https://provider.example/live.m3u8', { rewrite: 'direct' }));
  assert.equal(response.status, 502);
  assert.equal(response.headers.get('cache-control'), 'no-store');
  assert.equal(response.headers.get('netlify-cdn-cache-control'), null);
  assert.deepEqual(await response.json(), { error: 'Proxy target unavailable or blocked' });
});

test('GET and POST proxy routes propagate caller cancellation to the metadata fetch', async () => {
  for (const method of ['GET', 'POST']) {
    const caller = new AbortController();
    const api = proxy((_url, init) => new Promise((_resolve, reject) => {
      init.signal.addEventListener('abort', () => reject(init.signal.reason), { once: true });
    }));
    const pending = route('app/api/proxy/route.ts', api)[method](request(publicUrl, {},
      { method, signal: caller.signal, ...(method === 'POST' ? { body: '{}' } : {}) }));
    await flush(); caller.abort();
    assert.equal((await pending).status, 504);
    assert.equal(api.agents[0].destroyed, 1);
  }
});

test('GET and POST preserve provider Retry-After without caching the rejection', async () => {
  for (const method of ['GET', 'POST']) {
    const api = proxy(() => new Response('Slow down', { status: 429, headers: { 'retry-after': '1800' } }));
    const response = await route('app/api/proxy/route.ts', api)[method](request(publicUrl, {},
      { method, ...(method === 'POST' ? { body: '{}' } : {}) }));
    assert.equal(response.status, 429);
    assert.equal(response.headers.get('retry-after'), '1800');
    assert.equal(response.headers.get('netlify-cdn-cache-control'), null);
  }
});

test('subtitle conversion retains SRT/VTT and varies its CDN cache by complete subtitle URL', async () => {
  for (const input of ['1\n00:00:01,000 --> 00:00:02,000\nFixture', 'WEBVTT\n\n00:01.000 --> 00:02.000\nFixture']) {
    const api = proxy(() => new Response(input));
    const response = await route('app/api/subtitle/route.ts', api).GET(request('https://provider.example/subtitle.srt?token=fixture'));
    assert.equal(response.status, 200);
    assert.match(await response.text(), /^WEBVTT/);
    assert.equal(response.headers.get('netlify-vary'), 'query');
    assert.match(response.headers.get('netlify-cdn-cache-control'), /max-age=86400/);
  }
});

test('subtitles enforce DNS security, binary rejection and a 2 MiB response bound', async () => {
  for (const api of [
    proxy(() => new Response('WEBVTT'), hosted, async () => [{ address: '127.0.0.1', family: 4 }]),
    proxy(() => new Response(video, { headers: { 'content-type': 'application/octet-stream' } })),
    proxy(() => new Response('WEBVTT', { headers: { 'content-length': String(2 * 1024 * 1024 + 1) } }))
  ]) {
    const response = await route('app/api/subtitle/route.ts', api).GET(request('https://provider.example/subtitle'));
    assert.equal(response.status, 502);
    assert.equal(response.headers.get('cache-control'), 'no-store');
    assert.equal(response.headers.get('netlify-cdn-cache-control'), null);
  }
});

test('subtitle upstream failures are not cached and do not expose upstream bodies', async () => {
  const api = proxy(() => new Response('large provider error', { status: 503 }));
  const response = await route('app/api/subtitle/route.ts', api).GET(request(publicUrl));
  assert.equal(response.status, 503);
  assert.equal(response.headers.get('cache-control'), 'no-store');
  assert.doesNotMatch(await response.text(), /large provider error/);
});

test('subtitle request budget rejects excess calls before upstream fetch', async () => {
  let calls = 0;
  const response = await route('app/api/subtitle/route.ts', {
    withinProxyBudget: () => false, safeProxyFetch: async () => { calls++; }
  }).GET(request(publicUrl));
  assert.equal(response.status, 429);
  assert.equal(response.headers.get('retry-after'), '60');
  assert.equal(calls, 0);
});

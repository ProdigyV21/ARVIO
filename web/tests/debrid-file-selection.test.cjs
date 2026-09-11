const assert = require('node:assert/strict');
const { test } = require('node:test');
const { load } = require('./load.cjs');

const HASH = 'a'.repeat(40);
const PROVIDERS = ['torbox', 'realdebrid', 'premiumize', 'alldebrid'];
const info = (provider, overrides = {}) => ({ provider, apiKey: 'fixture-account-key', infoHash: HASH, fileName: 'Episode01.mkv', ...overrides });
const FILES = [
  { id: 77, name: 'Series/Season 1/Episode01.mkv', size: 100, selected: 1 },
  { id: 1, name: 'Series/Season 1/Episode02.mkv', size: 900, selected: 1 }
];
const locked = (id) => `https://locked.invalid/${id}`;
const direct = (id) => `https://cdn.invalid/${id}.mkv`;

function setup(provider, { files = FILES, rdLinks, nested = false, handler } = {}) {
  const calls = [];
  const api = load('lib/debrid.ts', { './http': {
    apiProxiedUrl: (url, headers) => ({ url: new URL(url), headers }),
    jsonRequest: async (target, options = {}) => {
      const call = { ...target, options, form: new URLSearchParams(options.body) };
      calls.push(call);
      if (handler) {
        const override = await handler(call);
        if (override !== undefined) return override;
      }
      const path = call.url.pathname;
      if (path.endsWith('/torrents/mylist')) return { success: true, data: [{ id: 42, hash: HASH, files }] };
      if (path.endsWith('/torrents/requestdl')) return { success: true, data: direct(call.url.searchParams.get('file_id')) };
      if (path.endsWith('/stream/createstream')) return { success: true, data: { hls_url: `https://cdn.invalid/${call.url.searchParams.get('file_id')}.m3u8` } };
      if (path.endsWith('/torrents')) return [{ id: 'torrent-42', hash: HASH }];
      if (path.includes('/torrents/info/')) return { id: 'torrent-42', files: files.map((file) => ({ ...file, path: file.name, bytes: file.size })),
        links: rdLinks ?? files.filter((file) => file.selected === 1).map((file) => locked(file.id)) };
      if (path.endsWith('/unrestrict/link')) {
        const id = new URL(call.form.get('link')).pathname.slice(1);
        return { id, download: direct(id) };
      }
      if (path.includes('/streaming/transcode/')) return { apple: { full: `https://cdn.invalid/${path.split('/').pop()}.m3u8` } };
      if (path.endsWith('/transfer/directdl')) return { status: 'success', content: files.map((file) => ({ path: file.name, size: file.size, link: direct(file.id) })) };
      if (path.endsWith('/magnet/upload')) return { status: 'success', data: { magnets: [{ id: 42 }] } };
      if (path.endsWith('/magnet/status')) return { status: 'success', data: { magnets: { statusCode: 4,
        ...(nested ? { files: [{ n: 'ProviderRoot', e: files.map((file) => ({ n: file.name, s: file.size, l: locked(file.id) })) }] }
          : { links: files.map((file) => ({ filename: file.name, size: file.size, link: locked(file.id) })) })
      } } };
      if (path.endsWith('/link/unlock')) return { status: 'success', data: { link: direct(new URL(call.form.get('link')).pathname.slice(1)) } };
      throw new Error(`Unexpected fixture request: ${provider} ${path}`);
    }
  } });
  return { ...api, calls };
}

for (const provider of PROVIDERS) {
  test(`${provider}: parser retains verified Torrentio filename/index and legacy index/filename selections`, () => {
    const h = setup(provider);
    const base = `https://resolver.invalid/resolve/${provider}/fixture-account-key/${HASH.toUpperCase()}`;
    for (const suffix of ['/Season%201%2FEpisode01.mkv/0', '/0/Season%201%2FEpisode01.mkv']) {
      const parsed = h.parseDebridStream(`${base}${suffix}?ignored=1#fragment`);
      assert.equal(parsed.provider, provider);
      assert.equal(parsed.infoHash, HASH);
      assert.equal(parsed.fileName, 'Season 1/Episode01.mkv');
      assert.equal(parsed.fileIndex, 0);
    }
    const withoutIndex = h.parseDebridStream(`${base}/Episode01.mkv`);
    assert.equal(withoutIndex.fileName, 'Episode01.mkv');
    assert.equal(withoutIndex.fileIndex, undefined);
    assert.equal(h.parseDebridStream(`${base}/Episode01.mkv/undefined`).fileName, 'Episode01.mkv');
    assert.equal(h.parseDebridStream(`${base}/Episode01.mkv/null`).fileIndex, undefined);
    assert.equal(h.parseDebridStream(`${base}/Episode01.mkv/9007199254740992`), null);
    assert.equal(h.parseDebridStream(`${base}/Episode01.mkv/-1`), null);
    assert.equal(h.parseDebridStream(`${base}/Episode01.mkv/1.2`), null);
    assert.equal(h.parseDebridStream(`${base}/Episode01.mkv/1/extra`), null);
  });

  test(`${provider}: a selected episode never falls back to the largest when missing`, async () => {
    const h = setup(provider);
    const result = await h.resolveDebridDirectUrl(info(provider, { fileName: 'Episode03.mkv' }));
    assert.equal(result.url, undefined);
    assert.match(result.error, /selected file.*missing or ambiguous/i);
    assert.equal(h.calls.some((call) => /requestdl|unrestrict|unlock|createstream/.test(call.url.pathname)), false);
  });

  test(`${provider}: selects the requested file, not the largest or an arbitrary provider ID`, async () => {
    const h = setup(provider);
    const result = await h.resolveDebridDirectUrl(info(provider, { fileIndex: 0 }));
    assert.equal(result.url, direct(77));
    assert.ok(h.calls.every((call) => call.headers.Authorization === 'Bearer fixture-account-key'));
  });

  test(`${provider}: preserves largest-video fallback only when no file was requested`, async () => {
    const h = setup(provider);
    const result = await h.resolveDebridDirectUrl(info(provider, { fileName: undefined }));
    assert.equal(result.url, direct(1));
  });

  test(`${provider}: a substring or similar episode title is not a filename match`, async () => {
    const h = setup(provider, { files: [{ id: 8, name: 'OtherEpisode01.mkv', size: 1000, selected: 1 }] });
    assert.ok((await h.resolveDebridDirectUrl(info(provider))).error);
  });

  test(`${provider}: duplicate basenames require an unambiguous path`, async () => {
    const h = setup(provider, { files: [
      { id: 3, name: 'Show/Season 1/Episode01.mkv', size: 100, selected: 1 },
      { id: 5, name: 'Show/Season 2/Episode01.mkv', size: 500, selected: 1 }
    ] });
    assert.ok((await h.resolveDebridDirectUrl(info(provider))).error);
    assert.equal((await h.resolveDebridDirectUrl(info(provider, { fileName: 'season 1\\EPISODE01.mkv' }))).url, direct(3));
    assert.ok((await h.resolveDebridDirectUrl(info(provider, { fileName: 'Season 3/Episode01.mkv' }))).error);
  });

  test(`${provider}: empty file selections and invalid numeric selectors fail before network requests`, async () => {
    const h = setup(provider);
    for (const selection of [{ fileName: '' }, { fileName: '  ' }, { fileIndex: -1 }, { fileIndex: NaN }, { fileIndex: 1.5 }, { fileIndex: Infinity }]) {
      assert.ok((await h.resolveDebridDirectUrl(info(provider, selection))).error);
      assert.ok((await h.resolveTranscodeStream(info(provider, selection))).error);
    }
    assert.equal(h.calls.length, 0);
  });

  test(`${provider}: no available video never resolves a default/archive file`, async () => {
    const h = setup(provider, { files: [{ id: 1, name: 'Archive.zip', size: 1000, selected: 1 }] });
    assert.ok((await h.resolveDebridDirectUrl(info(provider, { fileName: undefined }))).error);
    assert.equal(h.calls.some((call) => /requestdl|unrestrict|unlock/.test(call.url.pathname)), false);
  });
}

test('Real-Debrid canonical /null/index maps only to the verified one-based file ID', async () => {
  const h = setup('realdebrid', { files: [
    { id: 1, name: 'Largest.mkv', size: 1000, selected: 1 },
    { id: 8, name: 'Unselected.mkv', size: 300, selected: 0 },
    { id: 9, name: 'Requested.mkv', size: 100, selected: 1 }
  ] });
  const parsed = h.parseDebridStream(`https://resolver.invalid/realdebrid/fixture-account-key/${HASH}/null/8`);
  assert.equal(parsed.fileName, undefined);
  assert.equal(parsed.fileIndex, 8);
  assert.equal((await h.resolveDebridDirectUrl(parsed)).url, direct(9));
  assert.ok((await h.resolveDebridDirectUrl({ ...parsed, fileIndex: 7 })).error, 'unselected files must not substitute the largest');
  assert.ok((await h.resolveDebridDirectUrl({ ...parsed, fileIndex: 2 })).error, 'array offset 2 is not file ID 3');
});

test('Real-Debrid index zero is preserved and a bare numeric tail is not guessed to be a provider ID', async () => {
  const h = setup('realdebrid');
  const prefix = `https://resolver.invalid/real-debrid=fixture-account-key/${HASH}`;
  const zero = h.parseDebridStream(`${prefix}/null/0`);
  assert.equal(zero.provider, 'realdebrid');
  assert.equal((await h.resolveDebridDirectUrl(zero)).url, direct(1));
  const unknown = h.parseDebridStream(`${prefix}/77`);
  assert.equal(unknown.fileIndex, undefined);
  assert.ok((await h.resolveDebridDirectUrl(unknown)).error);
});

for (const provider of ['torbox', 'premiumize', 'alldebrid']) {
  test(`${provider}: an index without a filename never guesses provider IDs or array ordering`, async () => {
    const h = setup(provider);
    const parsed = h.parseDebridStream(`https://resolver.invalid/${provider}/fixture-account-key/${HASH}/null/0`);
    assert.ok((await h.resolveDebridDirectUrl(parsed)).error);
    assert.ok((await h.resolveTranscodeStream(parsed)).error);
    assert.equal(h.calls.length, 0);
  });
}

test('Real-Debrid keeps the original selected-file link ordering when excluding non-video entries', async () => {
  const h = setup('realdebrid', { files: [
    { id: 10, name: 'cover.jpg', size: 10_000, selected: 1 },
    { id: 11, name: 'Episode01.mkv', size: 100, selected: 1 },
    { id: 12, name: 'Episode02.mkv', size: 200, selected: 0 }
  ] });
  assert.equal((await h.resolveDebridDirectUrl(info('realdebrid'))).url, direct(11));
});

test('Real-Debrid refuses a bundled link when it cannot identify the selected file', async () => {
  const h = setup('realdebrid', { rdLinks: ['https://locked.invalid/bundle'] });
  assert.match((await h.resolveDebridDirectUrl(info('realdebrid'))).error, /separate links/);
  assert.equal(h.calls.some((call) => call.url.pathname.endsWith('/unrestrict/link')), false);
});

test('AllDebrid nested file paths preserve episode selection and reject missing filenames', async () => {
  const h = setup('alldebrid', { nested: true });
  assert.equal((await h.resolveDebridDirectUrl(info('alldebrid', { fileName: 'Season 1/Episode01.mkv' }))).url, direct(77));
  assert.ok((await h.resolveDebridDirectUrl(info('alldebrid', { fileName: 'Season 2/Episode01.mkv' }))).error);
  assert.equal(h.calls.filter((call) => call.url.pathname.endsWith('/link/unlock')).length, 1);
});

for (const provider of ['torbox', 'realdebrid']) {
  test(`${provider}: transcoding enforces the same selected-episode rules`, async () => {
    const h = setup(provider);
    assert.ok((await h.resolveTranscodeStream(info(provider, { fileName: 'Missing.mkv' }))).error);
    assert.equal(h.calls.some((call) => /unrestrict|createstream|streaming/.test(call.url.pathname)), false);
    assert.equal((await h.resolveTranscodeStream(info(provider))).url, 'https://cdn.invalid/77.m3u8');
  });
}

test('TorBox unavailable account listing cannot trigger torrent creation', async () => {
  const h = setup('torbox', { handler: (call) => call.url.pathname.endsWith('/torrents/mylist')
    ? { success: false, error: 'BAD_TOKEN' } : undefined });
  assert.ok((await h.resolveDebridDirectUrl(info('torbox'))).error);
  assert.equal(h.calls.length, 1);
});

test('TorBox creates only on explicit playback, and concurrent callers create/mint once', async () => {
  let created = false;
  const h = setup('torbox', { handler: (call) => {
    if (call.url.pathname.endsWith('/torrents/mylist') && !created) return { success: true, data: [] };
    if (call.url.pathname.endsWith('/torrents/createtorrent')) {
      assert.equal(call.form.get('add_only_if_cached'), 'true');
      assert.equal(call.options.method, 'POST');
      created = true;
      return { success: true, data: { torrent_id: 42 } };
    }
  } });
  const source = info('torbox');
  h.prefetchDebridDirectUrl(`https://resolver.invalid/torbox/fixture-account-key/${HASH}/Episode01.mkv/0`);
  assert.equal(h.calls.length, 0);
  const [first, second] = await Promise.all([h.resolveDebridDirectUrl(source), h.resolveDebridDirectUrl(source)]);
  assert.equal(first.url, direct(77));
  assert.equal(second.url, first.url);
  assert.equal(h.calls.filter((call) => call.url.pathname.endsWith('/torrents/createtorrent')).length, 1);
  assert.equal(h.calls.filter((call) => call.url.pathname.endsWith('/torrents/requestdl')).length, 1);
});

for (const endpoint of ['/torrents/createtorrent', '/torrents/requestdl']) {
  test(`TorBox does not retry uncertain mutating request ${endpoint}`, async () => {
    const h = setup('torbox', { handler: (call) => {
      if (endpoint.endsWith('createtorrent') && call.url.pathname.endsWith('/torrents/mylist')) return { success: true, data: [] };
      if (call.url.pathname.endsWith(endpoint)) throw new Error('uncertain transport result');
    } });
    assert.ok((await h.resolveDebridDirectUrl(info('torbox'))).error);
    assert.equal(h.calls.filter((call) => call.url.pathname.endsWith(endpoint)).length, 1);
  });
}

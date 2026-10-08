const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { pathToFileURL } = require('node:url');
const { load } = require('./load.cjs');

const studioPath = path.resolve(__dirname, '../../netlify-arvio-tv-site/collection-studio');
const corePromise = import(pathToFileURL(path.join(studioPath, 'studio-core.mjs')).href);
// Use the app's real TypeScript parser, rather than reproducing its format in a test.
const { parseCustomCollections, mergeImportedCollections } = load('lib/customCollections.ts');
const fragmentFor = value => `#studio=${Buffer.from(JSON.stringify(value), 'utf8').toString('base64url')}`;
const clone = value => JSON.parse(JSON.stringify(value));

test('all published starter files import and merge through the actual app parser without collisions', async () => {
  const { STARTERS } = await import(pathToFileURL(path.join(studioPath, 'starters.mjs')).href);
  let imported = [];
  for (const starter of STARTERS) {
    const json = fs.readFileSync(path.join(studioPath, 'examples', `${starter.id}.json`), 'utf8');
    const parsed = await parseCustomCollections(json);
    assert.equal(parsed[0].title, starter.draft.title);
    assert.equal(parsed.length, starter.draft.folders.length + 1);
    assert.ok(parsed.slice(1).every(item => item.collectionSources.length === 1 && item.collectionSources[0].kind === 'TMDB_DISCOVER'));
    imported = mergeImportedCollections(imported, parsed);
  }
  assert.equal(imported.filter(item => item.kind === 'COLLECTION_RAIL').length, STARTERS.length);
  assert.equal(new Set(imported.map(item => item.id)).size, imported.length);
});

test('default JSON imports through the actual app parser as one Home row and three single-source folders', async () => {
  const core = await corePromise;
  const json = core.serializeCollections(core.createDefaultDraft());
  const parsed = await parseCustomCollections(json);
  assert.equal(parsed.length, 4);
  assert.equal(parsed[0].kind, 'COLLECTION_RAIL');
  assert.equal(parsed[0].title, 'Weekend discoveries');
  assert.equal(new Set(parsed.map(item => item.id)).size, 4);
  assert.ok(parsed.slice(1).every(folder => folder.kind === 'COLLECTION' && folder.collectionSources.length === 1));
  assert.equal(parsed[1].collectionSources[0].kind, 'TMDB_DISCOVER');
  assert.equal(parsed[1].collectionSources[0].discoverParams['vote_average.gte'], '7');
  assert.equal(parsed[2].collectionSources[0].mediaType, 'tv');
  assert.equal(parsed[3].collectionSources[0].discoverParams.with_genres, '878');
});

test('every authored preset plus both public list providers preserves its meaning in the actual parser', async () => {
  const core = await corePromise;
  const draft = { version: 1, title: 'All supported sources', folders: core.PRESETS.map((preset, index) => ({
    id: `preset-${index}`, title: preset.label, description: preset.detail, theme: 'sage', source: { kind: 'tmdb', preset: preset.id }
  })) };
  draft.folders.push({ id: 'mdb', title: 'MDBList', description: '', theme: 'plum', source: { kind: 'mdblist', url: 'https://mdblist.com/lists/example/curated-list/' } });
  draft.folders.push({ id: 'trakt', title: 'Trakt', description: '', theme: 'ocean', source: { kind: 'trakt', url: 'https://trakt.tv/lists/123456789012345/' } });
  const json = core.serializeCollections(draft);
  const parsed = await parseCustomCollections(json);
  assert.equal(parsed.length, 9);
  core.PRESETS.forEach((preset, index) => {
    const source = parsed[index + 1].collectionSources[0];
    assert.equal(source.kind, 'TMDB_DISCOVER');
    assert.equal(source.mediaType, preset.mediaType.toLowerCase());
    assert.equal(source.sortBy, preset.sortBy);
    assert.equal(source.discoverParams['vote_count.gte'], String(preset.filters.voteCountGte));
    if (preset.filters.withGenres) assert.equal(source.discoverParams.with_genres, preset.filters.withGenres);
  });
  assert.equal(parsed[7].collectionSources[0].mdblistSlug, 'example/curated-list');
  assert.equal(parsed[8].collectionSources[0].traktListId, '123456789012345');
  assert.equal(parsed[8].collectionSources[0].kind, 'TRAKT_LIST');
});

test('exports contain only the authoritative collection contract, without studio-only colors or share state', async () => {
  const core = await corePromise;
  const document = core.createCollectionDocument(core.createDefaultDraft());
  assert.deepEqual(Object.keys(document[0]).sort(), ['folders', 'id', 'title']);
  for (const folder of document[0].folders) {
    assert.deepEqual(Object.keys(folder).sort(), ['description', 'id', 'sources', 'tileShape', 'title']);
    assert.equal(folder.tileShape, 'LANDSCAPE');
    assert.equal(folder.sources.length, 1);
  }
  assert.doesNotMatch(JSON.stringify(document), /coverImageUrl|heroBackdropUrl|titleLogoUrl|theme|version|apiKey|addon/);
});

test('shared UTF-8 previews round-trip Unicode and canonical text without losing emoji or combining marks', async () => {
  const core = await corePromise;
  const draft = core.createDefaultDraft();
  draft.title = '  Кино — الأفلام 🍿  ';
  draft.folders[0].title = 'Cafe\u0301 / 映画 🎬';
  draft.folders[0].description = 'مرحبا بالعالم\nUn soir à Paris ✨';
  const fragment = core.encodeShare(draft);
  assert.match(fragment, /^#studio=[A-Za-z0-9_-]+$/);
  assert.ok(fragment.length <= core.LIMITS.share);
  const decoded = core.decodeShare(fragment);
  assert.equal(decoded.title, 'Кино — الأفلام 🍿');
  assert.equal(decoded.folders[0].title, 'Café / 映画 🎬');
  assert.equal(decoded.folders[0].description, draft.folders[0].description);
  assert.equal(core.encodeShare(decoded), fragment);
  const parsed = await parseCustomCollections(core.serializeCollections(decoded));
  assert.equal(parsed[1].title, 'Café / 映画 🎬');
});

test('HTML and script-like labels remain inert text in share and collection JSON', async () => {
  const core = await corePromise;
  const draft = core.createDefaultDraft();
  draft.title = '<img src=x onerror=alert(1)>';
  draft.folders[0].title = '</textarea><script>alert(1)</script>';
  draft.folders[0].description = '<svg onload=alert(1)> & "quoted"';
  const decoded = core.decodeShare(core.encodeShare(draft));
  assert.equal(decoded.title, draft.title);
  const parsed = await parseCustomCollections(core.serializeCollections(decoded));
  assert.equal(parsed[1].title, draft.folders[0].title);
  const entry = fs.readFileSync(path.join(studioPath, 'studio.js'), 'utf8');
  assert.doesNotMatch(entry, /innerHTML|outerHTML|insertAdjacentHTML|document\.write|\beval\s*\(|new Function/);
  assert.match(entry, /element\.textContent = text/);
  assert.match(entry, /byId\('json-output'\)\.value = json/);
});

test('public list URLs accept only exact HTTPS MDBList slugs and numeric Trakt list links', async () => {
  const core = await corePromise;
  assert.equal(core.validatePublicListUrl(' https://mdblist.com/lists/Some_User/the-list/ ', 'mdblist'), 'https://mdblist.com/lists/Some_User/the-list');
  assert.equal(core.validatePublicListUrl('https://trakt.tv/lists/1', 'trakt'), 'https://trakt.tv/lists/1');
  assert.throws(() => core.validatePublicListUrl('https://trakt.tv/users/example/lists/favorites', 'trakt'), /numeric/);
  assert.throws(() => core.validatePublicListUrl('12345', 'trakt'), /numeric/);
  assert.throws(() => core.validatePublicListUrl('https://trakt.tv/lists/0', 'trakt'));
  assert.throws(() => core.validatePublicListUrl('https://trakt.tv/lists/01', 'trakt'));
  assert.throws(() => core.validatePublicListUrl('https://trakt.tv/lists/1234567890123456', 'trakt'));
});

test('list URL allowlist rejects credentials, token queries, spoofed domains and arbitrary host URLs', async () => {
  const core = await corePromise;
  const mdblistUrls = [
    'http://mdblist.com/lists/user/list', 'https://www.mdblist.com/lists/user/list',
    'https://mdblist.com.evil.invalid/lists/user/list', 'https://mdblist.com@evil.invalid/lists/user/list',
    'https://user:password@mdblist.com/lists/user/list', 'https://mdblist.com:443/lists/user/list',
    'https://mdblist.com/lists/user/list?api_key=secret', 'https://mdblist.com/lists/user/list?token=secret',
    'https://mdblist.com/lists/user/list?', 'https://mdblist.com/lists/user/list#token',
    'https://localhost/lists/user/list', 'https://127.0.0.1/lists/user/list',
    'https://192.168.1.4/lists/user/list', 'https://10.0.0.5/lists/user/list',
    'https://[::1]/lists/user/list', 'https://media.internal/lists/user/list',
    'file:///etc/passwd', 'javascript:alert(1)', 'data:application/json,{}',
    '//mdblist.com/lists/user/list', 'https:\\mdblist.com\\lists\\user\\list',
    'https://mdblist.com/lists/user%2flist', 'https://mdblist.com/lists/user/list%3Ftoken',
    'https://mdblist.com/lists/user/../list', 'https://mdblist.com/lists/user/list/extra',
    'https://mdblist.com/lists/üser/list', 'https://mdblist.com/lists/user/list\n?token=secret'
  ];
  for (const url of mdblistUrls) assert.throws(() => core.validatePublicListUrl(url, 'mdblist'), undefined, url);
  for (const url of ['https://trakt.tv/lists/123?token=secret', 'https://trakt.tv/lists/123#secret', 'https://user:secret@trakt.tv/lists/123', 'https://trakt.tv:443/lists/123', 'https://trakt.tv/lists/123%2fitems']) {
    assert.throws(() => core.validatePublicListUrl(url, 'trakt'), undefined, url);
  }
});

test('credential assignments and URL-shaped text cannot be smuggled into a public title or description', async () => {
  const core = await corePromise;
  for (const text of ['token=supersecret', 'password: hunter2', 'API_KEY = abc', '{"api_key":"private-secret"}', '{"clientSecret":"private-secret"}', 'Authorization: Bearer secret', 'Bearer abc.def.ghi', 'https://10.0.0.1:8096', 'javascript:alert(1)', 'file:///private', 'data:text/plain,secret']) {
    const draft = core.createDefaultDraft();
    draft.folders[0].description = text;
    assert.throws(() => core.validateDraft(draft), undefined, text);
    assert.throws(() => core.decodeShare(fragmentFor(draft)), undefined, text);
  }
  const normal = core.createDefaultDraft();
  normal.folders[0].description = 'A secret garden. The token of an old friendship.';
  assert.doesNotThrow(() => core.validateDraft(normal));
});

test('unknown fields, artwork URLs and source properties are rejected before decoding a preview', async () => {
  const core = await corePromise;
  const mutations = [
    draft => { draft.apiKey = 'secret'; },
    draft => { draft.folders[0].coverImageUrl = 'https://evil.invalid/track'; },
    draft => { draft.folders[0].heroVideoUrl = 'https://localhost/video'; },
    draft => { draft.folders[0].sources = [{ provider: 'addon', catalogId: 'x', type: 'movie' }]; },
    draft => { draft.folders[0].source.apiKey = 'secret'; },
    draft => { draft.folders[0].source.filters = { api_key: 'secret' }; },
    draft => { draft.folders[0].source.url = 'https://evil.invalid/art'; },
    draft => { draft.folders[0].source = { kind: 'addon', url: 'https://localhost/manifest.json' }; },
    draft => { draft.folders[0].source = { kind: 'server', url: 'https://private/video' }; }
  ];
  for (const mutate of mutations) {
    const draft = core.createDefaultDraft();
    mutate(draft);
    assert.throws(() => core.validateDraft(draft));
    assert.throws(() => core.encodeShare(draft));
    assert.throws(() => core.decodeShare(fragmentFor(draft)));
  }
  const prototypePayload = JSON.parse('{"version":1,"title":"Title","folders":[],"__proto__":{"polluted":true}}');
  assert.throws(() => core.decodeShare(fragmentFor(prototypePayload)));
  assert.equal({}.polluted, undefined);
});

test('mixed source properties and arbitrary discover presets cannot widen the subset', async () => {
  const core = await corePromise;
  for (const source of [
    { kind: 'tmdb', preset: 'custom-filter' }, { kind: 'tmdb', preset: 'movie-popular', url: '' },
    { kind: 'tmdb', preset: 'movie-popular', tmdbId: 42 },
    { kind: 'mdblist', url: 'https://mdblist.com/lists/u/l', preset: 'movie-popular' },
    { kind: 'trakt', url: 'https://trakt.tv/lists/123', preset: undefined }
  ]) {
    const draft = core.createDefaultDraft();
    draft.folders[0].source = source;
    assert.throws(() => core.validateDraft(draft));
  }
  const filtersBefore = JSON.stringify(core.PRESETS);
  const document = core.createCollectionDocument(core.createDefaultDraft());
  document[0].folders[0].sources[0].filters.voteAverageGte = 0;
  assert.equal(JSON.stringify(core.PRESETS), filtersBefore);
  assert.ok(core.PRESETS.every(preset => Object.isFrozen(preset) && Object.isFrozen(preset.filters)));
});

test('folder count, identifiers, title and description limits include boundaries and reject duplicates', async () => {
  const core = await corePromise;
  const draft = core.createDefaultDraft();
  draft.title = '🎬'.repeat(64);
  draft.folders[0].description = '🍿'.repeat(180);
  assert.doesNotThrow(() => core.validateDraft(draft));
  draft.title += 'x';
  assert.throws(() => core.validateDraft(draft), /64/);
  draft.title = 'A title';
  draft.folders[0].description += 'x';
  assert.throws(() => core.validateDraft(draft), /180/);
  for (const badFolders of [[], Array.from({ length: 9 }, (_, index) => ({ ...clone(core.createDefaultDraft().folders[0]), id: `folder-${index}` }))]) {
    assert.throws(() => core.validateDraft({ version: 1, title: 'Count', folders: badFolders }), /between 1 and 8/);
  }
  for (const id of ['folder_1', 'FOLDER-1', 'folder/1', '../folder', '1folder', 'folder--1', 'folder-', '', 'a'.repeat(41)]) {
    const next = core.createDefaultDraft();
    next.folders[0].id = id;
    assert.throws(() => core.validateDraft(next), /IDs/);
  }
  const duplicate = core.createDefaultDraft();
  duplicate.folders[1].id = duplicate.folders[0].id;
  assert.throws(() => core.validateDraft(duplicate), /unique/);
});

test('share limits fail early while a larger valid collection remains exportable and importable', async () => {
  const core = await corePromise;
  const draft = { version: 1, title: 'Large public collection', folders: Array.from({ length: 8 }, (_, index) => ({
    id: `folder-${index}`, title: '🎬'.repeat(64), description: '🍿'.repeat(180), theme: 'sage', source: { kind: 'tmdb', preset: 'movie-popular' }
  })) };
  assert.throws(() => core.encodeShare(draft), /too long/);
  const json = core.serializeCollections(draft);
  assert.ok(Buffer.byteLength(json) <= core.LIMITS.documentBytes);
  assert.equal((await parseCustomCollections(json)).length, 9);
  assert.throws(() => core.decodeShare('#studio=' + 'a'.repeat(core.LIMITS.share)), /too long/);
});

test('missing, truncated, noncanonical, invalid UTF-8 and malformed JSON shares are safely rejected', async () => {
  const core = await corePromise;
  for (const fragment of ['', '#', '#unknown=abc', '#studio=', '#studio=a', '#studio=@@@', '#studio=abcd=', '#studio=ab+/', '#studio=Zh', '#studio=_w', '#studio=eyJ', '#studio=bnVsbA', '#studio=W10', '#studio=e30', null, {}, 123]) {
    assert.throws(() => core.decodeShare(fragment), undefined, String(fragment));
  }
  assert.throws(() => core.decodeShare(fragmentFor({ ...core.createDefaultDraft(), version: 2 })), /version/);
  const proper = core.encodeShare(core.createDefaultDraft());
  assert.throws(() => core.decodeShare(proper.slice(0, -8)));
  const malformedSecret = '#studio=' + Buffer.from('api_key=private-secret-not-for-recovery').toString('base64url');
  assert.throws(() => core.decodeShare(malformedSecret), error => !error.message.includes('private-secret'));
});

test('non-objects, wrong types, missing fields, controls and invalid themes are rejected', async () => {
  const core = await corePromise;
  for (const value of [null, [], 'text', 42, {}, { version: 1, title: 12, folders: [] }, new Date(), Object.create({ version: 1 })]) {
    assert.throws(() => core.validateDraft(value));
  }
  const mutations = [
    draft => { draft.title = '  '; }, draft => { draft.folders[0].description = null; },
    draft => { draft.folders[0].source = []; }, draft => { draft.folders[0].theme = 'https://evil.invalid'; },
    draft => { draft.folders[0].title = 'a\u0000b'; }, draft => { draft.title = 'A\u202eb'; },
    draft => { draft.title = 'Bad\ud800'; }, draft => { draft.folders[0].source = { kind: 'mdblist', url: 12 }; }
  ];
  for (const mutate of mutations) {
    const draft = core.createDefaultDraft();
    mutate(draft);
    assert.throws(() => core.validateDraft(draft));
  }
});

test('folder reordering preserves stable identifiers and exports in the same order', async () => {
  const core = await corePromise;
  const draft = core.createDefaultDraft();
  const oldIds = draft.folders.map(folder => folder.id);
  draft.folders.reverse();
  const parsed = await parseCustomCollections(core.serializeCollections(draft));
  assert.deepEqual(JSON.parse(JSON.stringify(parsed.slice(1).map(folder => folder.title))), draft.folders.map(folder => folder.title));
  assert.deepEqual(draft.folders.map(folder => folder.id), oldIds.reverse());
  assert.equal(new Set(parsed.map(folder => folder.id)).size, parsed.length);
});

test('fixed studio IDs are namespaced by the real parser for distinct pasted documents and hosted URLs', async () => {
  const core = await corePromise;
  const firstDraft = core.createDefaultDraft();
  const secondDraft = core.createDefaultDraft();
  secondDraft.title = 'A second independent Home row';
  const firstJson = core.serializeCollections(firstDraft);
  const secondJson = core.serializeCollections(secondDraft);
  assert.equal(JSON.parse(firstJson)[0].id, JSON.parse(secondJson)[0].id);
  const first = await parseCustomCollections(firstJson);
  const second = await parseCustomCollections(secondJson);
  assert.notEqual(first[0].packId, second[0].packId);
  const both = mergeImportedCollections(first, second);
  assert.equal(both.length, 8);
  assert.equal(new Set(both.map(item => item.id)).size, 8);
  const urlA = 'https://example.com/first/collections.json';
  const urlB = 'https://example.com/second/collections.json';
  const hostedFirst = await parseCustomCollections(firstJson, urlA);
  const hostedSecond = await parseCustomCollections(firstJson, urlB);
  assert.notEqual(hostedFirst[0].packId, hostedSecond[0].packId);
  const hostedBoth = mergeImportedCollections(hostedFirst, hostedSecond);
  assert.equal(new Set(hostedBoth.map(item => item.id)).size, 8);
  const updatedAtSameUrl = await parseCustomCollections(secondJson, urlA);
  assert.equal(updatedAtSameUrl[0].packId, hostedFirst[0].packId);
  assert.equal(mergeImportedCollections(hostedBoth, updatedAtSameUrl).length, 8, 'Re-importing the same hosted URL updates that pack rather than colliding with a second pack');
});

test('page has local-only execution, read-only preview/fork, clipboard fallback and accurate manual import guidance', () => {
  const html = fs.readFileSync(path.join(studioPath, 'index.html'), 'utf8');
  const entry = fs.readFileSync(path.join(studioPath, 'studio.js'), 'utf8');
  const core = fs.readFileSync(path.join(studioPath, 'studio-core.mjs'), 'utf8');
  assert.doesNotMatch(entry + core, /\bfetch\s*\(|XMLHttpRequest|localStorage|sessionStorage|indexedDB|sendBeacon|WebSocket/);
  assert.doesNotMatch(html, /<img|<iframe|type="file"|arvio:\/\/|<script[^>]+src="https?:/);
  assert.match(html, /id="fork-shared"/);
  assert.match(html, /id="clipboard-fallback"/);
  assert.match(html, /id="json-output"[^>]*readonly/);
  assert.match(html, /Anyone with the preview link can read/);
  assert.match(html, /No playable media is included/);
  assert.match(html, /direct file uploads into ARVIO are not currently supported/);
  assert.match(html, /generic Studio link preview/);
  assert.match(entry, /byId\('editor'\)\.hidden = shared/);
  assert.match(entry, /byId\('fork-shared'\)\.hidden = !shared/);
  assert.match(entry, /history\.replaceState/);
});

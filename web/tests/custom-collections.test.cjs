const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');
const { webcrypto, createHash } = require('node:crypto');
const code = ts.transpileModule(fs.readFileSync(require.resolve('../lib/customCollections.ts'), 'utf8'), {
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 }
}).outputText;
const sandbox = { exports: {}, crypto: webcrypto, TextEncoder, URL };
vm.runInNewContext(code, sandbox);
const { parseCustomCollections, mergeImportedCollections } = sandbox.exports;
const { load, storage } = require('./load.cjs');
const document = [{ id: 'studios', title: 'Studios', folders: [{ id: 'pixar', title: 'Pixar', sources: [
  { provider: 'tmdb', tmdbSourceType: 'COMPANY', tmdbId: 3, mediaType: 'MOVIE' }
] }] }];
const json = JSON.stringify(document);

test('imports rails and folders with Android-compatible stable identifiers', async () => {
  const url = 'https://example.com/collections.json';
  const result = await parseCustomCollections(json, url);
  const hash = createHash('sha256').update(url).digest('hex').slice(0, 12);
  assert.equal(result.length, 2);
  assert.equal(result[0].id, `collection_rail_custom_usercol_${hash}_studios`);
  assert.equal(result[1].collectionSources[0].discoverParams.with_companies, '3');
  assert.ok(result.every(c => !c.isPreinstalled && c.packId === `usercol_${hash}`));
  assert.deepEqual(JSON.parse(JSON.stringify(result)), JSON.parse(JSON.stringify(await parseCustomCollections(json, url))));
});

test('reimport preserves order, names and enabled state, replaces old members', async () => {
  const imported = await parseCustomCollections(json);
  const current = [{ id: 'other', name: 'Other' }, ...imported.slice().reverse().map(c => ({ ...c, name: 'Renamed', enabled: false }))];
  const merged = mergeImportedCollections(current, imported);
  assert.equal(JSON.stringify(merged), JSON.stringify(current));
  assert.equal(mergeImportedCollections(merged, imported).length, current.length);
  assert.equal(merged.filter(c => c.packId !== imported[0].packId).length, 1);
});

test('rejects malformed IDs, duplicate folders and unsupported documents', async () => {
  await assert.rejects(parseCustomCollections('not json'));
  await assert.rejects(parseCustomCollections('{}'));
  await assert.rejects(parseCustomCollections(json.replace('"tmdbId":3', '"tmdbId":-1')));
  await assert.rejects(parseCustomCollections(JSON.stringify([{ ...document[0], folders: [document[0].folders[0], document[0].folders[0]] }])));
});

test('maps TV discover filters and public list providers', async () => {
  const source = { provider: 'tmdb', tmdbSourceType: 'DISCOVER', mediaType: 'TV', filters: { year: 2025, withOriginCountry: 'IL' } };
  const result = await parseCustomCollections(JSON.stringify({ title: 'Lists', folders: [{ title: 'Shows', sources: [source,
    { provider: 'trakt', traktListId: 123 }, { provider: 'tmdb', tmdbSourceType: 'LIST', tmdbId: 99 },
    { provider: 'mdblist', slug: 'user/list' }
  ] }] }));
  const sources = result[1].collectionSources;
  assert.equal(sources[0].discoverParams.first_air_date_year, '2025');
  assert.equal(sources[0].discoverParams.with_origin_country, 'IL');
  assert.equal(sources[1].traktListId, '123');
  assert.equal(sources[2].tmdbListId, 99);
  assert.equal(sources[3].mdblistSlug, 'user/list');
});

test('production loaders send discover filters, list IDs and addon genre to the correct endpoints', async () => {
  const calls = [];
  const api = load('lib/tmdb.ts', {
    './config': { config: {} }, './storage': storage(),
    './metadata/anizip': {}, './metadata/dispatcher': {},
    './mediaImages': { tmdbImageUrl: () => '' },
    './http': { proxiedUrl: x => x, apiProxiedUrl: x => x, jsonRequest: async url => {
      calls.push(url);
      if (url.includes('/discover/tv')) return { results: [{ id: 7, name: 'Show' }], total_pages: 1 };
      if (url.includes('/list/99')) return { items: [{ id: 8, title: 'Film' }] };
      if (url.includes('/person/123/combined_credits')) return { crew: [
        { id: 9, name: 'Directed show', media_type: 'tv', job: 'Director' },
        { id: 10, name: 'Produced show', media_type: 'tv', job: 'Producer' },
        { id: 11, title: 'Directed film', media_type: 'movie', job: 'Director' }
      ] };
      if (url.includes('/catalog/')) return { metas: [] };
      return {};
    } }
  }, { window: { location: { origin: 'https://web.invalid' } } });
  const catalog = sources => ({ id: 'collection', name: 'Collection', enabled: true, kind: 'COLLECTION', sourceType: 'preinstalled', collectionSources: sources });
  const shows = await api.loadCatalog(catalog([{ kind: 'TMDB_DISCOVER', mediaType: 'tv', discoverParams: { with_origin_country: 'IL' }, sortBy: 'primary_release_date.desc' }]), 'en', []);
  assert.equal(shows.items[0].title, 'Show');
  assert.ok(calls.some(url => url.includes('with_origin_country=IL') && url.includes('sort_by=first_air_date.desc')));
  const list = await api.loadCatalog(catalog([{ kind: 'TMDB_LIST', tmdbListId: 99 }]), 'en', []);
  assert.equal(list.items[0].title, 'Film');
  await api.loadCatalog(catalog([{ kind: 'ADDON_CATALOG', addonId: 'old-addon-id', addonCatalogType: 'movie', addonCatalogId: 'top', addonGenre: 'Science Fiction' }]), 'en',
    [{ id: 'installed', manifestUrl: 'https://addon.invalid/manifest.json', catalogs: [{ id: 'top', type: 'movie' }] }]);
  assert.ok(calls.includes('https://addon.invalid/catalog/movie/top/genre=Science%20Fiction.json'));
  const person = await parseCustomCollections(JSON.stringify({ title: 'Director', folders: [{ title: 'Shows', sources: [
    { provider: 'tmdb', tmdbSourceType: 'DIRECTOR', tmdbId: 123, mediaType: 'TV' }
  ] }] }));
  const directed = await api.loadCatalog(person[1], 'en', []);
  assert.equal(directed.items.length, 1);
  assert.equal(directed.items[0].title, 'Directed show');
});

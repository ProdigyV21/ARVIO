const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load, storage } = require('./load.cjs');

const filename = path.join(__dirname, '../components/livetv/PlaylistManager.tsx');
const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), { compilerOptions: {
  module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022, jsx: ts.JsxEmit.ReactJSX,
} }).outputText;
const moduleUnderTest = { exports: {} };
const mocks = {
  '@/lib/iptv': load('lib/iptv.ts', { './http': {}, './storage': storage() }),
  '@/lib/iptvGuide': load('lib/iptvGuide.ts'),
  '@/lib/managedConfiguration': {}, '@/lib/iptvPlaylistIdentity': {}, '@/lib/i18n': {},
  '@/lib/maskUrl': {}, '@/lib/iptvGroups': {}, '@/lib/stalkerPortals': {},
  'react': {}, 'react/jsx-runtime': {}, 'lucide-react': {},
};
vm.runInNewContext(code, { module: moduleUnderTest, exports: moduleUnderTest.exports,
  require: (name) => { assert.ok(name in mocks, `Unmocked import: ${name}`); return mocks[name]; },
});
const { playlistDraftFromEntry, playlistFromDraft } = moduleUnderTest.exports;
const plain = value => JSON.parse(JSON.stringify(value));
const m3u = { id: 'fixture', name: 'Fixture', enabled: false, m3uUrl: 'https://provider.invalid/channels.m3u',
  epgUrl: 'https://guides.invalid/main.xml', epgUrls: ['https://guides.invalid/main.xml', 'https://guides.invalid/backup.xml'],
  importVod: false, importLiveTv: true, importSeries: false };
const xtream = { ...m3u,
  m3uUrl: 'https://provider.invalid/get.php?username=fixture&password=old&type=m3u_plus&output=ts&token=keep' };
const edit = (existing, patch = {}) => plain(playlistFromDraft({ ...playlistDraftFromEntry(existing), ...patch }, existing.id, existing));

test('renaming an M3U playlist preserves every guide, import flag and enabled state', () => {
  assert.deepEqual(edit(m3u, { name: 'Renamed' }), { ...m3u, name: 'Renamed' });
});

test('renaming Xtream preserves its exact playlist URL and custom primary/fallback guides', () => {
  assert.deepEqual(edit(xtream, { name: 'Renamed' }), { ...xtream, name: 'Renamed' });
});

test('editing a playlist with only an EPG list preserves it without inventing a primary', () => {
  const entry = { ...m3u, epgUrl: undefined };
  assert.deepEqual(edit(entry, { name: 'Renamed' }), plain({ ...entry, name: 'Renamed' }));
});

test('changing Xtream credentials updates generated guides but keeps custom fallbacks', () => {
  const primary = 'https://provider.invalid/xmltv.php?username=fixture&password=old';
  const entry = { ...xtream, epgUrl: primary, epgUrls: [primary, m3u.epgUrls[1]] };
  const result = edit(entry, { password: 'new' });
  assert.equal(result.epgUrl, 'https://provider.invalid/xmltv.php?username=fixture&password=new');
  assert.deepEqual(result.epgUrls, [result.epgUrl, m3u.epgUrls[1]]);
  assert.equal(new URL(result.m3uUrl).searchParams.get('password'), 'new');
  assert.equal(result.enabled, false);
  assert.equal(result.importVod, false);
});

test('changing Xtream credentials does not replace a user-supplied guide', () => {
  const result = edit(xtream, { password: 'new' });
  assert.equal(result.epgUrl, xtream.epgUrl);
  assert.deepEqual(result.epgUrls, xtream.epgUrls);
});

test('explicit primary guide edits retain the other guides without duplicating the new primary', () => {
  const epg = m3u.epgUrls[1];
  const result = edit(m3u, { epg });
  assert.equal(result.epgUrl, epg);
  assert.deepEqual(result.epgUrls, [epg]);
  const added = edit(m3u, { epg: 'https://guides.invalid/new.xml' });
  assert.deepEqual(added.epgUrls, [added.epgUrl, m3u.epgUrls[1]]);
});

test('explicitly clearing the guide removes legacy fallback guides too', () => {
  const result = edit(m3u, { epg: '' });
  assert.equal(result.epgUrl, '');
  assert.deepEqual(result.epgUrls, []);
});

test('switching an existing Xtream draft to M3U keeps the populated URL and guide', () => {
  assert.deepEqual(edit(xtream, { kind: 'm3u' }), xtream);
});

test('converting a guide-less M3U entry to Xtream generates its guide URL', () => {
  const entry = { ...m3u, epgUrl: '', epgUrls: [] };
  const result = edit(entry, { kind: 'xtream', host: 'https://provider.invalid', username: 'fixture', password: 'new' });
  assert.equal(result.epgUrl, 'https://provider.invalid/xmltv.php?username=fixture&password=new');
});

test('new Xtream logins still generate URLs and invalid edited guide URLs are rejected', () => {
  const draft = { ...playlistDraftFromEntry(xtream), password: 'new' };
  const result = plain(playlistFromDraft(draft, 'new'));
  assert.equal(result.epgUrl, 'https://provider.invalid/xmltv.php?username=fixture&password=new');
  assert.equal(playlistFromDraft({ ...draft, epg: 'invalid' }, xtream.id, xtream), 'bad-epg');
  assert.equal(playlistFromDraft({ ...playlistDraftFromEntry(m3u), epg: 'invalid' }, m3u.id, m3u), 'bad-epg');
});

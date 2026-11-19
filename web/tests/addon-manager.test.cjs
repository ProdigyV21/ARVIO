const test = require('node:test');
const assert = require('node:assert/strict');
const crypto = require('node:crypto');
const { load } = require('./load.cjs');

const plain = (value) => JSON.parse(JSON.stringify(value));
const lib = () => load('lib/addonInstall.ts');
const addon = (id, url, extra = {}) => ({ id, name: id, version: '1.0.0', manifestUrl: url, catalogs: [], resources: ['stream'], manifest: { id: extra.manifestId ?? id, behaviorHints: extra.hints ?? {} }, ...extra });

test('a second install of the same manifest id is found, by id, manifest id or link', () => {
  const { duplicateAddons, isSameInstall } = lib();
  const installed = [addon('torrentio_ab12cd', 'https://t.example/a/manifest.json', { manifestId: 'torrentio' }), addon('other', 'https://o.example/manifest.json')];
  const candidate = addon('torrentio', 'https://t.example/b/manifest.json');
  assert.deepEqual(plain(duplicateAddons(installed, candidate)).map((item) => item.id), ['torrentio_ab12cd']);
  assert.equal(isSameInstall(installed, candidate), false);
  assert.equal(isSameInstall(installed, addon('torrentio', 'HTTPS://t.example/a/manifest.json')), true);
  assert.deepEqual(plain(duplicateAddons(installed, addon('new', 'https://n.example/manifest.json'))), []);
});

test('replace keeps the old place and on/off choice; keep both adds a second install', () => {
  const { applyAddonInstall } = lib();
  const installed = [addon('a', 'https://a/manifest.json'), addon('t', 'https://t/1/manifest.json', { enabled: false }), addon('c', 'https://c/manifest.json')];
  const replaced = applyAddonInstall(installed, addon('t', 'https://t/2/manifest.json'), 'replace');
  assert.deepEqual(plain(replaced.next).map((item) => [item.id, item.manifestUrl, item.enabled]), [['a', 'https://a/manifest.json', undefined], ['t', 'https://t/2/manifest.json', false], ['c', 'https://c/manifest.json', undefined]]);
  assert.deepEqual(plain(replaced.removedIds), []);
  const renamed = applyAddonInstall([addon('t_old', 'https://t/1/manifest.json', { manifestId: 't' })], addon('t', 'https://t/2/manifest.json'), 'replace');
  assert.deepEqual(plain(renamed.removedIds), ['t_old'], 'a replaced install under another id is removed for sync');
  const both = applyAddonInstall(installed, addon('t_99', 'https://t/2/manifest.json', { manifestId: 't' }), 'keep-both');
  assert.deepEqual(plain(both.next).map((item) => item.id), ['t_99', 'a', 't', 'c']);
});

test('keep both uses Android\'s installation id: manifestId_ + 6 bytes of sha256(url)', async () => {
  const { addonInstanceId } = load('lib/addonInstall.ts', {}, { crypto: crypto.webcrypto });
  const url = 'https://T.example/b/manifest.json';
  const expected = crypto.createHash('sha256').update(url.toLowerCase()).digest('hex').slice(0, 12);
  assert.equal(await addonInstanceId('torrentio', url), `torrentio_${expected}`);
});

test('configure link only when the manifest says the addon is configurable', () => {
  const { addonConfigureUrl, addonCapabilities } = lib();
  assert.equal(addonConfigureUrl(addon('x', 'https://x.example/abc/manifest.json', { hints: { configurable: true }, transportUrl: 'https://x.example/abc' })), 'https://x.example/abc/configure');
  assert.equal(addonConfigureUrl(addon('x', 'https://x.example/manifest.json')), null);
  assert.equal(addonConfigureUrl(addon('x', 'https://x.example/manifest.json', { configureUrl: 'https://x.example/settings' })), 'https://x.example/settings');
  assert.deepEqual(plain(addonCapabilities({ ...addon('x', 'u'), resources: ['stream', { name: 'subtitles' }], catalogs: [{}, {}] })), { catalogs: 2, streams: true, subtitles: true, meta: false });
});

test('installed manifests keep their configurable hint in Android\'s boolean shape', async () => {
  const manifest = { id: 'cfg', name: 'Cfg', version: '2.0.0', resources: ['stream'], behaviorHints: { configurable: true, extra: 'x' } };
  const http = { jsonRequest: async () => manifest, textRequest: async () => '', proxiedUrl: (u) => u };
  const store = { loadStored: (_k, f) => f, saveStored() {} };
  const addons = load('lib/addons.ts', {
    './http': http, './storage': store, './addonNative': { addonServesOwnMeta: () => false, nativeStreamTarget: () => null },
    './youtubeCollection': { isYouTubeItem: () => false, youTubeStreams: () => [] },
    './config': { hasResolverConfig: () => false }, './resolver': { getResolverStreamsProgressive: async () => [] },
    './streamCompatibility': { isBrowserPlayableStream: () => true, isIosPlayableStream: () => true },
    './addonStreamInfo': { isInformationalAddonStream: () => false }
  });
  const installed = await addons.installAddon('https://cfg.example');
  assert.deepEqual(plain(installed.manifest.behaviorHints), { adult: false, p2p: false, configurable: true, configurationRequired: false });
  assert.equal(installed.manifestUrl, 'https://cfg.example/manifest.json');
});

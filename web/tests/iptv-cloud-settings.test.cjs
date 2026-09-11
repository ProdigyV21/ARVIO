const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const settings = (playlists) => ({
  iptvPlaylists: playlists, iptvStalkerUrl: '', iptvStalkerMac: '',
  favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [],
  iptvSortOrder: 'provider', homeServers: [], catalogs: [], hiddenCatalogIds: [],
  hiddenHomeServerCatalogIds: [], subtitleStyle: 'outline', subtitleSize: 100,
  subtitleOffset: 0, subtitleColorName: 'white', frameRateMatchingMode: 'off',
  autoPlayMinQuality: 'any', accentColor: '#ffffff',
});

async function save(payload, next, baseline) {
  let pushed;
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '[]' },
    './iptv': load('lib/iptv.ts', { './http': {}, './storage': storage() }), './mediaImages': {},
    './http': { jsonRequest: async (_url, options) => {
      if (options?.method === 'POST') {
        pushed = JSON.parse(options.body).payload;
        return { accepted: true };
      }
      return { payload };
    } },
  });
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  await cloud.saveCloudSettings(auth, next, [], 'p1', [], baseline, 300);
  return pushed;
}

test('unrelated web settings do not restore a playlist deleted on TV', async () => {
  const a = { id: 'a', m3uUrl: 'https://example.invalid/a' };
  const b = { id: 'b', m3uUrl: 'https://example.invalid/b' };
  const payload = { iptvByProfile: { p1: { playlists: [a], lockedGroups: ['private'], tvSession: { lastChannelId: 'a:1' } } },
    fieldUpdatedAt: { 'i:p1:playlists': 200 } };
  const baseline = settings([a, b]);
  const pushed = await save(payload, { ...baseline, accentColor: '#ff0000' }, baseline);
  assert.deepEqual(pushed.iptvByProfile.p1, payload.iptvByProfile.p1);
  assert.equal(pushed.fieldUpdatedAt['i:p1:playlists'], 200);
});

test('explicitly deleting all web playlists writes an empty list with a timestamp', async () => {
  const a = { id: 'a', m3uUrl: 'https://example.invalid/a' };
  const payload = { iptvByProfile: { p1: { playlists: [a], m3uUrl: a.m3uUrl }, p2: { playlists: [a] } } };
  const pushed = await save(payload, settings([]), settings([a]));
  assert.deepEqual(pushed.iptvByProfile.p1.playlists, []);
  assert.equal(pushed.iptvByProfile.p1.m3uUrl, '');
  assert.equal(pushed.fieldUpdatedAt['i:p1:playlists'], 300);
  assert.deepEqual(pushed.iptvByProfile.p2, payload.iptvByProfile.p2);
});

test('favorite edits retain remote additions and removals while stamping local changes', async () => {
  const payload = { iptvByProfile: { p1: {
    playlists: [], favoriteChannels: ['kept', 'remote-new'], lockedGroups: ['private'],
  } } };
  const baseline = { ...settings([]), favoriteChannelIds: ['removed-remotely', 'kept'] };
  const next = { ...baseline, favoriteChannelIds: ['kept', 'local-new', 'removed-remotely'] };
  const pushed = await save(payload, next, baseline);
  assert.deepEqual(pushed.iptvByProfile.p1.favoriteChannels, ['kept', 'local-new', 'remote-new']);
  assert.deepEqual(pushed.iptvFavoriteChannels, pushed.iptvByProfile.p1.favoriteChannels);
  assert.deepEqual(pushed.settings.favoriteChannelIds, pushed.iptvByProfile.p1.favoriteChannels);
  assert.deepEqual(pushed.iptvByProfile.p1.lockedGroups, ['private']);
  assert.equal(pushed.fieldUpdatedAt['i:p1:favoriteChannels'], 300);
});

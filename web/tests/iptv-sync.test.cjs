const test = require('node:test');
const assert = require('node:assert/strict');
const { createHash } = require('node:crypto');
const { load, storage } = require('./load.cjs');

const playlist = (id = 'list_1') => ({ id, name: id, enabled: true,
  m3uUrl: 'https://provider.invalid/get.php?username=fixture&password=fixture&type=m3u_plus' });
const settings = (patch = {}) => ({
  iptvPlaylists: [playlist()], iptvStalkerUrl: '', iptvStalkerMac: '',
  favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [], iptvSortOrder: 'provider',
  catalogs: [], hiddenCatalogIds: [], hiddenHomeServerCatalogIds: [], homeServers: [],
  accentColor: '#fff', subtitleSize: 'medium', subtitleColor: '#fff', ...patch
});
const auth = { session: { userId: 'account', accessToken: 'fixture' }, isNetlifySession: true, accessToken: async () => 'fixture' };

function fixture(initial = {}) {
  let remote = structuredClone(initial);
  let reject = false;
  const writes = [];
  const iptv = load('lib/iptv.ts', { './storage': storage(), './http': {
    proxiedUrl: (url) => url,
    textRequest: async (url) => JSON.stringify(url.includes('get_live_categories')
      ? [{ category_id: 'nl', category_name: 'Netherlands' }]
      : [
        { stream_id: 101, name: 'NPO 1 HD', epg_channel_id: 'npo1.nl', category_id: 'nl' },
        { stream_id: 102, name: 'NPO 1 SD', epg_channel_id: 'npo1.nl', category_id: 'nl' },
        { stream_id: 103, name: 'NPO 2 HD', epg_channel_id: 'npo2.nl', category_id: 'nl' }
      ])
  } });
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '', parseHomeServerConnectionJson: () => [] },
    './iptv': iptv, './mediaImages': {},
    './http': { jsonRequest: async (url, init) => {
      if (url.endsWith('account-sync-pull')) return { payload: structuredClone(remote) };
      assert.ok(url.endsWith('account-sync-push'));
      if (reject) return { accepted: false, reason: 'existing_snapshot_is_richer' };
      remote = JSON.parse(init.body).payload;
      writes.push(structuredClone(remote));
      return { accepted: true };
    } }
  });
  return { cloud, iptv, writes, get remote() { return remote; }, set remote(value) { remote = structuredClone(value); },
    set reject(value) { reject = value; } };
}

function legacyId(channel, url = channel.streamUrl) {
  const hash = createHash('sha1').update(url.trim()).digest('hex').slice(0, 16);
  return `${channel.id.split(':')[0]}:m3u:${channel.tvgId}:${url.length}-${hash}`;
}

test('Xtream IDs match Android independent of HLS/TS, with separate variants and providers', async () => {
  const f = fixture();
  const snapshot = await f.iptv.loadIptvSnapshot([playlist(), playlist('list_2')]);
  assert.deepEqual(Array.from(snapshot.channels, (c) => c.id), [
    'list_1:xtream:101', 'list_1:xtream:102', 'list_1:xtream:103',
    'list_2:xtream:101', 'list_2:xtream:102', 'list_2:xtream:103'
  ]);
  assert.ok(snapshot.channels.every((c) => c.streamUrl.endsWith('.m3u8')));
});

test('Existing hashed favorites migrate exactly, preserving order, unavailable channels and provider scope', async () => {
  const f = fixture();
  const { channels } = await f.iptv.loadIptvSnapshot([playlist(), playlist('list_2')]);
  const favorites = [legacyId(channels[2]), legacyId(channels[1], channels[1].streamUrl.replace('.m3u8', '.ts')),
    legacyId(channels[3]), 'disabled:m3u:unavailable:123-hash', channels[2].id];
  assert.deepEqual(Array.from(f.iptv.migrateXtreamFavoriteIds(favorites, channels)), [
    'list_1:xtream:103', 'list_1:xtream:102', 'list_2:xtream:101', 'disabled:m3u:unavailable:123-hash'
  ]);
  assert.equal(f.iptv.migrateXtreamFavoriteIds(favorites, []), favorites, 'Do not discard unresolved favorites');
});

test('An explicit empty profile favorites list never inherits another profile from legacy mirrors', async () => {
  const f = fixture({ iptvFavoriteChannels: ['other:xtream:42'], iptvFavoriteGroups: ['other|Adult'],
    iptvByProfile: { arvind: { favoriteChannels: [], favoriteGroups: [] }, child: {} } });
  for (const profile of ['arvind', 'child']) {
    const result = await f.cloud.pullCloudPayload(auth, profile);
    assert.deepEqual(Array.from(result.settings.favoriteChannelIds), []);
    assert.deepEqual(Array.from(result.settings.favoriteGroupIds), []);
  }
  const legacy = await f.cloud.pullCloudPayload(auth, 'legacy-profile');
  assert.deepEqual(Array.from(legacy.settings.favoriteChannelIds), ['other:xtream:42']);
});

test('Unrelated web settings save retains newer Android favorites, TV session, locks and other profiles', async () => {
  const tv = { playlists: [playlist()], favoriteChannels: ['list_1:xtream:103'], favoriteGroups: [],
    tvSession: { lastPlayedChannelId: 'list_1:xtream:103' }, lockedGroups: ['list_1|Adult'], groupOrderSchema: 5,
    stalkerPortals: [{ id: 'portal' }] };
  const f = fixture({ iptvByProfile: { arvind: tv, child: { favoriteChannels: ['child:xtream:5'] } } });
  await f.cloud.saveCloudSettings(auth, settings({ accentColor: '#123' }), [], 'arvind', [], settings());
  const actual = f.remote.iptvByProfile.arvind;
  for (const [key, value] of Object.entries(tv)) assert.deepEqual(actual[key], value, key);
  assert.deepEqual(f.remote.iptvByProfile.child, { favoriteChannels: ['child:xtream:5'] });
  assert.deepEqual(f.remote.iptvFavoriteChannels, tv.favoriteChannels);
});

test('Web favorite changes round-trip through the Android cloud shape, including removing the last favorite', async () => {
  const f = fixture({ iptvByProfile: { arvind: { favoriteChannels: [], playlists: [playlist()] } } });
  const selected = settings({ favoriteChannelIds: ['list_1:xtream:101', 'list_1:xtream:103'] });
  await f.cloud.saveCloudSettings(auth, selected, [], 'arvind', [], settings());
  assert.deepEqual(f.remote.iptvByProfile.arvind.favoriteChannels, selected.favoriteChannelIds);
  assert.deepEqual(Array.from((await f.cloud.pullCloudPayload(auth, 'arvind')).settings.favoriteChannelIds), selected.favoriteChannelIds);
  await f.cloud.saveCloudSettings(auth, settings(), [], 'arvind', [], selected);
  assert.deepEqual(f.remote.iptvByProfile.arvind.favoriteChannels, []);
  assert.deepEqual(Array.from((await f.cloud.pullCloudPayload(auth, 'arvind')).settings.favoriteChannelIds), []);
});

test('Concurrent favorite additions and removals survive a stale web settings baseline', async () => {
  const f = fixture({ iptvByProfile: { arvind: { favoriteChannels: ['remote-new', 'keep'] } } });
  await f.cloud.saveCloudSettings(auth,
    settings({ favoriteChannelIds: ['web-new', 'removed-remotely', 'keep'] }), [], 'arvind', [],
    settings({ favoriteChannelIds: ['removed-remotely', 'removed-locally', 'keep'] }));
  assert.deepEqual(f.remote.iptvByProfile.arvind.favoriteChannels, ['web-new', 'keep', 'remote-new']);
});

test('Settings mutations fetch fresh cloud data even after a cached pull', async () => {
  const f = fixture({ iptvByProfile: { arvind: { favoriteChannels: [] } } });
  await f.cloud.pullRawPayload(auth);
  f.remote = { iptvByProfile: { arvind: { favoriteChannels: ['tv-new'] } } };
  await f.cloud.saveCloudSettings(auth, settings({ accentColor: '#123' }), [], 'arvind', [], settings());
  assert.deepEqual(f.remote.iptvByProfile.arvind.favoriteChannels, ['tv-new']);
});

test('profile playlists override legacy mirrors, including deleted playlists and other profiles', async () => {
  const current = playlist();
  const f = fixture({ settings: { iptvPlaylists: [current, playlist('stale')] }, iptvM3uUrl: current.m3uUrl,
    iptvByProfile: { arvind: { playlists: [current] }, child: { playlists: [] } } });
  assert.deepEqual(Array.from((await f.cloud.pullCloudPayload(auth, 'arvind')).settings.iptvPlaylists, p => p.id), ['list_1']);
  assert.deepEqual(Array.from((await f.cloud.pullCloudPayload(auth, 'child')).settings.iptvPlaylists), []);
});

test('Android recent history pulls and web playback round-trips without overwriting newer TV plays', async () => {
  const remote = { lastChannelId: 'tv', lastOpenedAt: 300, recentChannelIds: ['old', 'tv'] };
  const f = fixture({ iptvByProfile: { arvind: { playlists: [playlist()], tvSession: remote } } });
  const pulled = (await f.cloud.pullCloudPayload(auth, 'arvind')).settings;
  assert.equal(pulled.iptvTvSession.lastChannelId, 'tv');
  assert.deepEqual(Array.from(pulled.iptvTvSession.recentChannelIds), ['old', 'tv']);
  await f.cloud.saveCloudSettings(auth, settings({ iptvTvSession: { lastChannelId: 'web', lastOpenedAt: 200, recentChannelIds: ['old', 'web'] } }), [], 'arvind', [], settings());
  assert.equal(f.remote.iptvByProfile.arvind.tvSession.lastChannelId, 'tv');
  assert.ok(f.remote.iptvByProfile.arvind.tvSession.recentChannelIds.includes('web'));
  assert.ok(f.remote.fieldUpdatedAt['i:arvind:tvSession'] > 0);
});

test('A HTTP 200 rejected cloud save stays in the durable outbox and succeeds on retry', async () => {
  const f = fixture();
  const outbox = load('lib/settingsOutbox.ts', { './storage': storage(), './cloud': f.cloud });
  outbox.queueSettings(auth, 'arvind', settings({ favoriteChannelIds: ['list_1:xtream:101'] }), settings());
  f.reject = true;
  await assert.rejects(outbox.flushSettingsOutbox(auth), /Cloud did not accept/);
  assert.equal(outbox.hasPendingSettings(auth, 'arvind'), true);
  f.reject = false;
  await outbox.flushSettingsOutbox(auth);
  assert.equal(outbox.hasPendingSettings(auth), false);
  assert.deepEqual(f.remote.iptvByProfile.arvind.favoriteChannels, ['list_1:xtream:101']);
});

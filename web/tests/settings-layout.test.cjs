// Settings layout editors: ordering helpers, the synced fields they write
// (channel order, source priority, per-row card style, playlist import
// switches) and how those fields round-trip through the shared cloud payload.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const ordering = load('lib/ordering.ts');
const groups = load('lib/iptvGroups.ts');
const sources = load('lib/sourceOrder.ts');
const rows = load('lib/homeRows.ts');
const plain = (value) => JSON.parse(JSON.stringify(value));

/* ---------- pure ordering ---------- */

test('moveIndex clamps and returns the same array when nothing moves', () => {
  const list = ['a', 'b', 'c', 'd'];
  assert.deepEqual(plain(ordering.moveIndex(list, 0, 2)), ['b', 'c', 'a', 'd']);
  assert.deepEqual(plain(ordering.moveIndex(list, 3, -5)), ['d', 'a', 'b', 'c']);
  assert.equal(ordering.moveIndex(list, 1, 1), list);
  assert.equal(ordering.moveIndex(list, 9, 0), list);
});

test('the drag preview shows exactly the order the drop commits', () => {
  const list = Array.from({ length: 9 }, (_, i) => `item${i}`);
  for (let from = 0; from < list.length; from++) {
    for (let to = 0; to < list.length; to++) {
      const preview = list.map((_, display) => list[ordering.previewSourceIndex(display, from, to)]);
      assert.deepEqual(preview, plain(ordering.moveIndex(list, from, to)), `${from}→${to}`);
    }
  }
});

test('moving inside a filtered view keeps hidden items in place', () => {
  const list = ['tmdb1', 'addon1', 'tmdb2', 'addon2', 'tmdb3', 'addon3'];
  const visible = ['addon1', 'addon2', 'addon3'];
  assert.deepEqual(plain(ordering.moveWithinSubset(list, (x) => x, visible, 2, 0)), ['tmdb1', 'addon3', 'addon1', 'tmdb2', 'addon2', 'tmdb3']);
  assert.deepEqual(plain(ordering.moveWithinSubset(list, (x) => x, visible, 0, 2)), ['tmdb1', 'tmdb2', 'addon2', 'tmdb3', 'addon3', 'addon1']);
  assert.equal(ordering.moveWithinSubset(list, (x) => x, visible, 1, 1), list);
});

/* ---------- Live TV categories, channels and favourites ---------- */

test('moving a category rewrites only that playlist and keeps unloaded categories', () => {
  const channels = [{ id: 'p:1', group: 'A' }, { id: 'p:2', group: 'B' }, { id: 'p:3', group: 'C' }];
  const saved = ['other|X', 'p|C', 'p|gone', 'p|A'];
  const list = groups.playlistGroups(channels, 'p', saved);
  assert.deepEqual(plain(list.map((g) => g.key)), ['p|C', 'p|A', 'p|B']);
  const next = groups.movePlaylistGroupTo(saved, list, 2, 0);
  assert.deepEqual(plain(next), ['other|X', 'p|B', 'p|gone', 'p|C', 'p|A']);
  assert.deepEqual(plain(groups.playlistGroups(channels, 'p', next).map((g) => g.key)), ['p|B', 'p|C', 'p|A']);
});

test('favourite categories match Android names and older playlist keys; new ones are saved by name', () => {
  assert.equal(groups.isFavoriteGroup(['News'], 'p|News'), true);
  assert.equal(groups.isFavoriteGroup(['p|News'], 'p|News'), true);
  assert.equal(groups.isFavoriteGroup(['Sport'], 'p|News'), false);
  assert.deepEqual(plain(groups.toggleFavoriteGroup(['Sport'], 'p|News')), ['News', 'Sport']);
  assert.deepEqual(plain(groups.toggleFavoriteGroup(['p|News', 'Sport', 'News'], 'p|News')), ['Sport']);
});

const ch = (n, extra = {}) => ({ id: `p:${n}`, name: `Ch ${n}`, group: 'G', streamUrl: '', ...extra });

test('a manual channel order lists its channels first and resolves cloud ids and aliases', () => {
  const list = [ch(1), ch(2, { cloudId: 'p:cloud2' }), ch(3, { syncAliases: ['p:alias3'] }), ch(4)];
  assert.deepEqual(plain(groups.applyChannelOrder(list, ['p:alias3', 'p:cloud2']).map((c) => c.id)), ['p:3', 'p:2', 'p:1', 'p:4']);
  assert.equal(groups.applyChannelOrder(list, []), list);
  assert.deepEqual(plain(groups.applyChannelOrder(list, ['p:missing', 'p:4']).map((c) => c.id)), ['p:4', 'p:1', 'p:2', 'p:3']);
});

test('the stored channel order is the shortest prefix that reproduces the arrangement', () => {
  const base = [ch(1), ch(2, { cloudId: 'p:c2' }), ch(3), ch(4), ch(5)];
  // Move channel 4 to the top: only it needs storing.
  const top = ordering.moveIndex(base, 3, 0);
  assert.deepEqual(plain(groups.minimalChannelOrder(top, base)), ['p:4']);
  // Move channel 1 below channel 3: storing 2 and 3 is enough, 1 then follows the sort.
  const down = ordering.moveIndex(base, 0, 2);
  const refs = groups.minimalChannelOrder(down, base);
  assert.deepEqual(plain(refs), ['p:c2', 'p:3']);
  for (const arrangement of [top, down, base, [...base].reverse()]) {
    const stored = groups.minimalChannelOrder(arrangement, base);
    assert.deepEqual(plain(groups.applyChannelOrder(base, stored).map((c) => c.id)), plain(arrangement.map((c) => c.id)));
  }
  assert.deepEqual(plain(groups.minimalChannelOrder(base, base)), []);
});

test('channel order map edits touch one category; resetting a playlist keeps the others', () => {
  const map = { 'p|A': ['p:1'], 'q|A': ['q:1'] };
  assert.deepEqual(plain(groups.setGroupChannelOrder(map, 'p|B', ['p:2'])), { 'p|A': ['p:1'], 'q|A': ['q:1'], 'p|B': ['p:2'] });
  assert.deepEqual(plain(groups.setGroupChannelOrder(map, 'p|A', [])), { 'q|A': ['q:1'] });
  assert.deepEqual(plain(groups.clearPlaylistChannelOrder(map, 'p')), { 'q|A': ['q:1'] });
});

test('sorting by number and name matches the Live TV page', () => {
  const list = [ch(1, { name: 'b', number: '10' }), ch(2, { name: 'a', number: '2' }), ch(3, { name: 'c' })];
  assert.deepEqual(plain(groups.sortChannelsLikeLiveTv(list, 'number').map((c) => c.id)), ['p:2', 'p:1', 'p:3']);
  assert.deepEqual(plain(groups.sortChannelsLikeLiveTv(list, 'name').map((c) => c.id)), ['p:2', 'p:1', 'p:3']);
  assert.equal(groups.sortChannelsLikeLiveTv(list, 'provider'), list);
});

/* ---------- source priority (Android StreamIntegrationRepository) ---------- */

const providerList = () => sources.knownProviders({
  homeServers: [{ id: 'hs1', name: 'Jelly', type: 'jellyfin' }],
  addons: [{ id: 'a', name: 'A', resources: ['stream'] }, { id: 'subs', name: 'Subs', resources: ['subtitles'] }, { id: 'b', name: 'B', resources: [] }],
  pluginRepositories: [{ id: 'repo', name: 'Repo' }],
  playlists: [{ id: 'list_1', name: 'IPTV' }],
  existingOrder: ['iptv_stalker:portal1'],
});

test('known providers follow Android ids and skip addons without streams', () => {
  assert.deepEqual(plain(providerList().map((p) => p.id)), ['homeserver:hs1', 'stremio:a', 'stremio:b', 'plugin_repo:repo', 'telegram', 'iptv_playlist:list_1', 'iptv_stalker:portal1']);
});

test('without a custom order providers follow the integration order; a custom order lists its ids first', () => {
  const list = providerList();
  assert.deepEqual(plain(sources.effectiveProviderOrder(list, '', 'iptv_vod,stremio_addons').map((p) => p.id)),
    ['iptv_playlist:list_1', 'iptv_stalker:portal1', 'stremio:a', 'stremio:b', 'homeserver:hs1', 'plugin_repo:repo', 'telegram']);
  assert.deepEqual(plain(sources.effectiveProviderOrder(list, 'telegram,unknown:x,stremio:b', '').map((p) => p.id).slice(0, 3)), ['telegram', 'stremio:b', 'homeserver:hs1']);
});

test('reordering addons moves only the Stremio slots of the TV source order', () => {
  const list = providerList();
  const order = sources.providerOrderForAddons(list, 'stremio:a,telegram,homeserver:hs1,stremio:b', '', ['b', 'subs', 'a']);
  assert.deepEqual(plain(order.split(',')), ['stremio:b', 'telegram', 'homeserver:hs1', 'stremio:a', 'plugin_repo:repo', 'iptv_playlist:list_1', 'iptv_stalker:portal1']);
});

test('changing the integration order re-sorts a custom order instead of blanking it (Android ignores blanks)', () => {
  assert.equal(sources.resortProviderOrder('', ['iptv_vod']), '');
  assert.equal(sources.resortProviderOrder('stremio:a,iptv_playlist:x,homeserver:h,telegram',
    ['iptv_vod', 'telegram', 'home_server', 'stremio_addons', 'plugins']), 'iptv_playlist:x,telegram,homeserver:h,stremio:a');
});

/* ---------- Home rows ---------- */

test('row layout keys normalise exactly like Kotlin normalizeCatalogueRowLayoutKey("home:<id>")', () => {
  assert.equal(rows.rowLayoutKey('trending_movies'), 'home:trending_movies');
  assert.equal(rows.rowLayoutKey('Custom List #1 (Ünïcode)'), 'home:custom_list_1_n_code');
  assert.equal(rows.rowLayoutKey('addon.catalog:Top-10'), 'home:addon.catalog:top-10');
});

test('rows are grouped by source and card styles are applied from the Android map', () => {
  assert.equal(rows.homeRowSource({ sourceType: 'mdblist' }), 'lists');
  assert.equal(rows.homeRowSource({ sourceType: 'preinstalled', kind: 'COLLECTION_RAIL' }), 'collections');
  assert.equal(rows.homeRowSource({ sourceType: 'home-server' }), 'home-server');
  const applied = rows.applyRowLayoutModes([{ id: 'a', layout: 'landscape' }, { id: 'b' }], { 'home:a': 'Poster' });
  assert.deepEqual(plain(applied), [{ id: 'a', layout: 'poster' }, { id: 'b' }]);
  assert.deepEqual(plain(rows.withRowLayoutMode({ 'home:x': 'Poster' }, 'a', 'landscape')), { 'home:x': 'Poster', 'home:a': 'Landscape' });
});

/* ---------- cloud round-trips ---------- */

const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
function cloudFixture(initial) {
  let remote = structuredClone(initial);
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '', parseHomeServerConnectionJson: () => [] },
    './iptv': load('lib/iptv.ts', { './http': {}, './storage': storage() }), './mediaImages': {},
    './http': { jsonRequest: async (_url, init) => {
      if (init?.method === 'POST') { remote = JSON.parse(init.body).payload; return { accepted: true }; }
      return { payload: structuredClone(remote) };
    } },
  });
  return { cloud, get remote() { return remote; } };
}
const base = () => ({
  iptvPlaylists: [], iptvStalkerUrl: '', iptvStalkerMac: '', favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [],
  iptvSortOrder: 'provider', homeServers: [], catalogs: [], hiddenCatalogIds: [], hiddenAddonCatalogIds: [], hiddenHomeServerCatalogIds: [],
  subtitleStyle: 'outline', subtitleSize: 100, subtitleOffset: 'bottom', subtitleColorName: 'White', frameRateMatchingMode: 'off',
  autoPlayMinQuality: 'any', accentColor: 'arctic', qualityFilters: [],
});

test('channel order: web → payload → Android-shaped read → web, merging per category', async () => {
  const f = cloudFixture({
    iptvByProfile: { p1: { playlists: [], channelOrder: { 'p|A': ['p:1'], 'p|Remote': ['p:9'] } } },
    fieldUpdatedAt: { 'i:p1:channelOrder': 100 },
  });
  const pulled = (await f.cloud.pullCloudPayload(auth, 'p1')).settings;
  assert.deepEqual(plain(pulled.iptvChannelOrder), { 'p|A': ['p:1'], 'p|Remote': ['p:9'] });
  // Another device adds a category order after this browser read the cloud.
  f.remote.iptvByProfile.p1.channelOrder['p|Other'] = ['p:5'];
  const baseline = { ...base(), iptvChannelOrder: { 'p|A': ['p:1'], 'p|Remote': ['p:9'] } };
  const next = { ...baseline, iptvChannelOrder: { 'p|A': ['p:2', 'p:1'], 'p|Remote': ['p:9'] } };
  await f.cloud.saveCloudSettings(auth, next, [], 'p1', [], baseline, 500);
  assert.deepEqual(f.remote.iptvByProfile.p1.channelOrder, { 'p|A': ['p:2', 'p:1'], 'p|Remote': ['p:9'], 'p|Other': ['p:5'] });
  assert.equal(f.remote.fieldUpdatedAt['i:p1:channelOrder'], 500);
  // Shape the Android app parses (IptvCloudProfileState.channelOrder: Map<String, List<String>>).
  for (const [key, list] of Object.entries(f.remote.iptvByProfile.p1.channelOrder)) {
    assert.match(key, /\|/);
    assert.ok(list.every((id) => typeof id === 'string'));
  }
  const back = (await f.cloud.pullCloudPayload(auth, 'p1')).settings;
  assert.deepEqual(plain(back.iptvChannelOrder), f.remote.iptvByProfile.p1.channelOrder);
  // Resetting a category removes only that key.
  await f.cloud.saveCloudSettings(auth, { ...next, iptvChannelOrder: { 'p|Remote': ['p:9'] } }, [], 'p1', [], next, 600);
  assert.deepEqual(f.remote.iptvByProfile.p1.channelOrder, { 'p|Remote': ['p:9'], 'p|Other': ['p:5'] });
});

test('unrelated saves never bump the channel order or source priority timestamps', async () => {
  const f = cloudFixture({
    iptvByProfile: { p1: { playlists: [], channelOrder: { 'p|A': ['p:1'] } } },
    profileSettingsById: { p1: { streamProvidersCustomOrder: 'stremio:a', streamMacroEnabledMap: { plugins: false }, catalogueRowLayoutModes: { 'home:a': 'Poster' } } },
    fieldUpdatedAt: { 'i:p1:channelOrder': 100, 'p:p1:streamProvidersCustomOrder': 100, 'p:p1:streamMacroEnabledMap': 100, 'p:p1:catalogueRowLayoutModes': 100 },
  });
  const pulled = { ...base(), ...plain((await f.cloud.pullCloudPayload(auth, 'p1')).settings) };
  await f.cloud.saveCloudSettings(auth, { ...pulled, accentColor: 'gold' }, [], 'p1', [], pulled, 700);
  for (const key of ['i:p1:channelOrder', 'p:p1:streamProvidersCustomOrder', 'p:p1:streamMacroEnabledMap', 'p:p1:catalogueRowLayoutModes']) {
    assert.equal(f.remote.fieldUpdatedAt[key], 100, key);
  }
  assert.equal(f.remote.fieldUpdatedAt['g:accentColor'], 700);
  assert.deepEqual(f.remote.iptvByProfile.p1.channelOrder, { 'p|A': ['p:1'] });
  // A browser that has never seen the field (older baseline) does not create it either.
  const older = { ...pulled }; delete older.iptvChannelOrder;
  await f.cloud.saveCloudSettings(auth, { ...older, accentColor: 'red' }, [], 'p1', [], older, 800);
  assert.equal(f.remote.fieldUpdatedAt['i:p1:channelOrder'], 100);
});

test('source priority fields round-trip and only the changed one is stamped', async () => {
  const f = cloudFixture({ profileSettingsById: { p1: { streamProvidersCustomOrder: 'stremio:a,stremio:b', streamProviderEnabledMap: { 'stremio:a': true, 'telegram': false } } } });
  const pulled = { ...base(), ...plain((await f.cloud.pullCloudPayload(auth, 'p1')).settings) };
  assert.equal(pulled.streamProvidersCustomOrder, 'stremio:a,stremio:b');
  assert.deepEqual(pulled.streamProviderEnabledMap, { 'stremio:a': true, telegram: false });
  assert.equal(pulled.streamIntegrationsMacroOrder, undefined);
  const next = { ...pulled, streamProvidersCustomOrder: 'stremio:b,stremio:a', streamProviderEnabledMap: { ...pulled.streamProviderEnabledMap, 'stremio:b': false } };
  f.remote.profileSettingsById.p1.streamProviderEnabledMap['plugin_repo:x'] = true; // set on the TV meanwhile
  await f.cloud.saveCloudSettings(auth, next, [], 'p1', [], pulled, 900);
  const saved = f.remote.profileSettingsById.p1;
  assert.equal(saved.streamProvidersCustomOrder, 'stremio:b,stremio:a');
  assert.deepEqual(saved.streamProviderEnabledMap, { 'stremio:a': true, telegram: false, 'plugin_repo:x': true, 'stremio:b': false });
  assert.equal('streamIntegrationsMacroOrder' in saved, false);
  assert.equal(f.remote.fieldUpdatedAt['p:p1:streamProvidersCustomOrder'], 900);
  assert.equal(f.remote.fieldUpdatedAt['p:p1:streamProviderEnabledMap'], 900);
  assert.equal(f.remote.fieldUpdatedAt['p:p1:streamIntegrationsMacroOrder'], undefined);
});

test('per-row card style survives Android dropping `layout` from catalogs', async () => {
  // Android writes catalogsByProfile with its own CatalogConfig (no layout) and keeps
  // row styles in profileSettingsById.catalogueRowLayoutModes.
  const f = cloudFixture({
    catalogsByProfile: { p1: [{ id: 'trending', title: 'Trending', sourceType: 'PREINSTALLED' }, { id: 'top', title: 'Top', sourceType: 'PREINSTALLED' }] },
    profileSettingsById: { p1: { catalogueRowLayoutModes: { 'home:trending': 'Poster', 'home:other': 'Poster' } } },
  });
  const pulled = { ...base(), ...plain((await f.cloud.pullCloudPayload(auth, 'p1')).settings) };
  assert.equal(pulled.catalogs.find((c) => c.id === 'trending').layout, 'poster');
  assert.equal(pulled.catalogs.find((c) => c.id === 'top').layout, undefined);
  const next = { ...pulled, catalogueRowLayoutModes: rows.withRowLayoutMode(pulled.catalogueRowLayoutModes, 'top', 'poster') };
  await f.cloud.saveCloudSettings(auth, next, [], 'p1', [], pulled, 1000);
  assert.deepEqual(f.remote.profileSettingsById.p1.catalogueRowLayoutModes, { 'home:trending': 'Poster', 'home:other': 'Poster', 'home:top': 'Poster' });
  assert.equal(f.remote.fieldUpdatedAt['p:p1:catalogueRowLayoutModes'], 1000);
});

test('profile-scoped values in the legacy settings blob never leak into another profile', async () => {
  const f = cloudFixture({
    settings: { streamProvidersCustomOrder: 'stremio:fromA', catalogueRowLayoutModes: { 'home:x': 'Poster' }, iptvChannelOrder: { 'p|A': ['p:1'] } },
    profileSettingsById: { p2: {} }, iptvByProfile: { p2: { playlists: [] } },
  });
  const s = (await f.cloud.pullCloudPayload(auth, 'p2')).settings;
  assert.equal(s.streamProvidersCustomOrder, undefined);
  assert.equal(s.catalogueRowLayoutModes, undefined);
  assert.deepEqual(plain(s.iptvChannelOrder), {});
});

test("Android's playlist import switches survive a web edit of the playlist list", async () => {
  const playlist = { id: 'list_1', name: 'Main', m3uUrl: 'https://example.invalid/a.m3u', importVod: false, importSeries: true };
  const f = cloudFixture({ iptvByProfile: { p1: { playlists: [playlist] } } });
  const pulled = { ...base(), ...plain((await f.cloud.pullCloudPayload(auth, 'p1')).settings) };
  assert.equal(pulled.iptvPlaylists[0].importVod, false);
  assert.equal(pulled.iptvPlaylists[0].importSeries, true);
  assert.equal('importLiveTv' in pulled.iptvPlaylists[0], false);
  const next = { ...pulled, iptvPlaylists: [{ ...pulled.iptvPlaylists[0], name: 'Renamed', importLiveTv: false }] };
  await f.cloud.saveCloudSettings(auth, next, [], 'p1', [], pulled, 1100);
  const saved = f.remote.iptvByProfile.p1.playlists[0];
  assert.deepEqual([saved.name, saved.importLiveTv, saved.importVod, saved.importSeries], ['Renamed', false, false, true]);
});

test('reordering addons goes through the merge-protected path: order is kept, nothing another device added is dropped', async () => {
  const addon = (id) => ({ id, manifestUrl: `https://${id}.example/manifest.json` });
  const f = cloudFixture({ addons: ['a', 'b', 'tvOnly'].map(addon), addonsByProfile: { p1: ['a', 'b', 'tvOnly'].map(addon), p2: ['a'].map(addon) } });
  // The web reorders a stale view that never saw "tvOnly".
  await f.cloud.saveCloudAddons(auth, ['b', 'a'].map(addon), 'p1');
  assert.deepEqual(plain(f.remote.addons.map((a) => a.id)), ['b', 'a', 'tvOnly']);
  assert.deepEqual(plain(f.remote.addonsByProfile.p1.map((a) => a.id)), ['b', 'a', 'tvOnly']);
  assert.deepEqual(plain(f.remote.addonsByProfile.p2.map((a) => a.id)), ['a']);
  assert.deepEqual(f.remote.addonChanges, {});
  const back = await f.cloud.pullCloudPayload(auth, 'p1');
  assert.deepEqual(plain(back.addons.map((a) => a.id)), ['b', 'a', 'tvOnly']);
});

test('plugin repository names are read for the source list', async () => {
  const f = cloudFixture({ pluginRepositories: [{ id: 'r1', name: 'Repo One', enabled: false }, { name: 'no id' }] });
  const s = (await f.cloud.pullCloudPayload(auth, 'p1')).settings;
  assert.deepEqual(plain(s.androidPluginRepositories), [{ id: 'r1', name: 'Repo One', enabled: false }]);
});

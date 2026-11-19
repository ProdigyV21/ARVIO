// Several Stalker portals (Android's stalkerPortals list), category display
// names (iptvByProfile.groupNames) and the category key for channels without
// a category name.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const plain = (value) => JSON.parse(JSON.stringify(value));
// Values from the loaded modules come from another realm: compare them as plain JSON.
const eq = (actual, expected, message) => assert.deepEqual(plain(actual), plain(expected), message);
const portals = load('lib/stalkerPortals.ts');
const groups = load('lib/iptvGroups.ts');
const guide = load('lib/iptvGuide.ts');

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
const base = (patch = {}) => ({
  iptvPlaylists: [], iptvStalkerUrl: '', iptvStalkerMac: '', favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [],
  iptvSortOrder: 'provider', homeServers: [], catalogs: [], hiddenCatalogIds: [], hiddenAddonCatalogIds: [], hiddenHomeServerCatalogIds: [],
  subtitleStyle: 'outline', subtitleSize: 100, subtitleOffset: 'bottom', subtitleColorName: 'White', frameRateMatchingMode: 'off',
  autoPlayMinQuality: 'any', accentColor: 'arctic', qualityFilters: [], ...patch,
});
const pulled = async (f, profile = 'p1') => ({ ...base(), ...plain((await f.cloud.pullCloudPayload(auth, profile)).settings) });

test('ten named IPTV playlists survive a cloud save and fresh pull', async () => {
  const list = Array.from({ length: 10 }, (_, i) => ({ id: `list_${i + 1}`, name: `Playlist ${i + 1}`, m3uUrl: `https://example.invalid/${i + 1}.m3u`, epgUrl: '', enabled: true }));
  const f = cloudFixture({});
  await f.cloud.saveCloudSettings(auth, base({ iptvPlaylists: list }), [], 'p1', [], base(), 300);
  eq((await pulled(f)).iptvPlaylists.map(p => [p.id, p.name, p.m3uUrl]), list.map(p => [p.id, p.name, p.m3uUrl]));
});

test('cloud restore repairs the old duplicate ID bug without hiding a source', async () => {
  const list = [1, 3, 4].map((n, i) => ({ id: i ? 'list_3' : 'list_1', name: `P${n}`, m3uUrl: `https://example.invalid/${n}.m3u`, enabled: true }));
  const f = cloudFixture({ iptvByProfile: { p1: { playlists: list } } });
  const restored = (await pulled(f)).iptvPlaylists;
  assert.equal(new Set(restored.map(p => p.id)).size, 3);
  eq(restored.map(p => p.m3uUrl), list.map(p => p.m3uUrl));
});

/* ---------- portal list ---------- */

test('the Android portal list is read with every field kept; incomplete entries are dropped like on Android', () => {
  const list = portals.normalizeStalkerPortals([
    { id: 'stalker1', name: 'Home', portalUrl: ' http://a.example/c/ ', macAddress: '00:1a:79:00:00:01', enabled: false, importVod: false, androidOnly: { x: 1 } },
    { id: 'stalker1', name: '', portalUrl: 'http://b.example/c', macAddress: '00:1A:79:00:00:02' },
    { id: 'stalker3', name: 'No MAC', portalUrl: 'http://c.example/c', macAddress: '' },
    { portalUrl: 'http://d.example', macAddress: '00:1A:79:00:00:04' },
    { id: 'stalker9', portalUrl: 'http://e.example', macAddress: '00:1A:79:00:00:05' },
  ]);
  eq(plain(list), [
    { id: 'stalker1', name: 'Home', portalUrl: 'http://a.example/c', macAddress: '00:1A:79:00:00:01', enabled: false, importVod: false, androidOnly: { x: 1 } },
    { id: 'stalker2', name: 'Portal 2', portalUrl: 'http://b.example/c', macAddress: '00:1A:79:00:00:02', enabled: true },
    { id: 'stalker4', name: 'Portal 4', portalUrl: 'http://d.example', macAddress: '00:1A:79:00:00:04', enabled: true },
    { id: 'stalker9', name: 'Portal 5', portalUrl: 'http://e.example', macAddress: '00:1A:79:00:00:05', enabled: true },
  ]);
  // Only enabled portals with live TV import on fill Live TV.
  const settings = { iptvStalkerUrl: '', iptvStalkerMac: '', iptvStalkerPortals: [...list, { ...list[1], id: 'stalker5', importLiveTv: false }] };
  eq(portals.liveTvStalkerPortals(settings).map((p) => p.id), ['stalker2', 'stalker4', 'stalker9']);
  // Older apps read the single-portal fields: they carry the first enabled portal.
  eq(plain(portals.stalkerPortalsPatch(list)), { iptvStalkerPortals: plain(list), iptvStalkerUrl: 'http://b.example/c', iptvStalkerMac: '00:1A:79:00:00:02' });
});

test('adding portals uses the next free id and stops at ten; editing keeps the other fields', () => {
  let list = [];
  for (const n of Array.from({ length: 11 }, (_, i) => i + 1)) list = portals.upsertStalkerPortal(list, { name: n === 2 ? '' : `P${n}`, portalUrl: `http://p${n}.example/c/`, macAddress: `00:1a:79:00:00:${String(n).padStart(2, '0')}` });
  eq(list.map((p) => [p.id, p.name, p.portalUrl]), Array.from({ length: 10 }, (_, i) => [`stalker${i + 1}`, i === 1 ? 'Portal 2' : `P${i + 1}`, `http://p${i + 1}.example/c`]));
  list = [{ ...list[0], importVod: false, extra: 'kept', enabled: false }, ...list.slice(1)];
  const edited = portals.upsertStalkerPortal(list, { id: 'stalker1', name: 'Renamed', portalUrl: 'http://new.example/c', macAddress: '00:1a:79:aa:bb:cc' });
  eq(plain(edited[0]), { id: 'stalker1', name: 'Renamed', portalUrl: 'http://new.example/c', macAddress: '00:1A:79:AA:BB:CC', enabled: false, importVod: false, extra: 'kept' });
  assert.equal(portals.nextStalkerPortalId(['stalker2']), 'stalker3');
  assert.equal(portals.portalDraftProblem('portal.example', '00:1A:79:00:00:01'), 'bad-url');
  assert.equal(portals.portalDraftProblem('http://portal.example/c/', '001A79'), 'bad-mac');
  assert.equal(portals.portalDraftProblem('', ''), 'missing');
  assert.equal(portals.portalDraftProblem('http://portal.example/c/', '00:1a:79:00:00:01'), null);
});

test('channel ids and keys follow Android: stalker:<portal>:<id> belongs to the portal, old stalker| keys become Portal 1', () => {
  assert.equal(portals.channelPlaylistId('stalker:stalker2:77'), 'stalker2');
  assert.equal(portals.channelPlaylistId('list_1:xtream:5'), 'list_1');
  assert.equal(portals.migrateLegacyStalkerKey('stalker|News'), 'stalker1|News');
  assert.equal(portals.migrateLegacyStalkerKey('stalker2|News'), 'stalker2|News');
  const migrated = portals.migrateLegacyIptvKeys({
    hiddenGroupIds: ['stalker|Adult', 'list|A'], groupOrder: ['stalker|B', 'stalker1|B'], favoriteGroupIds: ['News', 'stalker|C'],
    iptvChannelOrder: { 'stalker|B': ['x'], 'stalker1|B': ['y'] }, iptvGroupNames: { 'stalker|B': 'Bee' },
  });
  eq(plain(migrated), {
    hiddenGroupIds: ['stalker1|Adult', 'list|A'], groupOrder: ['stalker1|B'], favoriteGroupIds: ['News', 'stalker1|C'],
    iptvChannelOrder: { 'stalker1|B': ['y'] }, iptvGroupNames: { 'stalker1|B': 'Bee' },
  });
});

/* ---------- loading several portals ---------- */

function portalHttp(events, { failHost } = {}) {
  return {
    proxiedUrl: (url, headers) => ({ url, headers }),
    jsonRequest: async (target) => {
      const u = new URL(target.url);
      const action = u.searchParams.get('action');
      events.push(`${u.host}:${action}`);
      await new Promise((resolve) => setTimeout(resolve, 2));
      if (u.host === failHost) throw new Error('portal down');
      if (action === 'handshake') return { js: { token: 't' } };
      if (action === 'get_profile') return { js: { id: 1 } };
      if (action === 'get_genres') return { js: [{ id: 'g', title: 'News' }, { id: 'e', title: '' }] };
      if (action === 'get_all_channels') {
        return { js: { total_items: 3, data: [
          { id: 1, name: `${u.host} one`, cmd: 'ffmpeg http://localhost/ch/1', tv_genre_id: 'g' },
          { id: 2, name: `${u.host} two`, cmd: 'ffmpeg http://localhost/ch/2', tv_genre_id: 'e' },
          { id: 3, name: `${u.host} three`, cmd: 'ffmpeg http://localhost/ch/3' },
        ] } };
      }
      return { js: {} };
    },
  };
}
const twoPortals = [
  { id: 'stalker1', name: 'One', portalUrl: 'http://one.example/c', macAddress: '00:1A:79:00:00:01', enabled: true },
  { id: 'stalker2', name: 'Two', portalUrl: 'http://two.example/c', macAddress: '00:1A:79:00:00:02', enabled: true },
];

test('every portal loads as its own playlist, one portal at a time, with Android channel ids and group names', async () => {
  const events = [];
  const iptv = load('lib/iptv.ts', { './http': portalHttp(events), './storage': storage() });
  const snapshot = await iptv.loadIptvSnapshot([], [], [], [], [], { stalkerPortals: twoPortals });
  const ids = snapshot.allChannels.map((c) => c.id);
  eq(ids, ['stalker:stalker1:1', 'stalker:stalker1:2', 'stalker:stalker1:3', 'stalker:stalker2:1', 'stalker:stalker2:2', 'stalker:stalker2:3']);
  // Group names as StalkerApi: genre title, an empty title stays empty, no genre = "Uncategorized".
  eq(snapshot.allChannels.slice(0, 3).map((c) => c.group), ['News', '', 'Uncategorized']);
  eq(snapshot.allChannels.slice(0, 3).map(iptv.groupKey), ['stalker1|News', 'stalker1|Ungrouped', 'stalker1|Uncategorized']);
  assert.equal(iptv.groupKey(snapshot.allChannels[3]), 'stalker2|News');
  // The id older web builds saved favourites under still resolves.
  assert.ok(snapshot.allChannels[0].syncAliases.includes(`stalker:${encodeURIComponent('http://one.example/c')}:1`));
  // Sequential: portal two starts only after portal one finished.
  const lastOne = events.map((e) => e.startsWith('one.example')).lastIndexOf(true);
  const firstTwo = events.findIndex((e) => e.startsWith('two.example'));
  assert.ok(lastOne < firstTwo, events.join(', '));
  // Groups per portal stay apart in Settings.
  eq(plain(groups.playlistGroups(snapshot.allChannels, 'stalker2', [], { 'stalker2|News': 'Nieuws' })).map((g) => [g.key, g.label]),
    [['stalker2|News', 'Nieuws'], ['stalker2|Ungrouped', 'Ungrouped'], ['stalker2|Uncategorized', 'Uncategorized']]);
});

test('a failing portal reports its own error and the others still load; lists are reused unless a fresh load is asked', async () => {
  const events = [];
  const iptv = load('lib/iptv.ts', { './http': portalHttp(events, { failHost: 'one.example' }), './storage': storage() });
  const snapshot = await iptv.loadIptvSnapshot([], [], [], [], [], { stalkerPortals: twoPortals });
  eq(snapshot.allChannels.map((c) => c.id.split(':')[1]), ['stalker2', 'stalker2', 'stalker2']);
  eq(plain(snapshot.playlistErrors).map((e) => [e.playlistId, e.name]), [['stalker1', 'One']]);
  const before = events.filter((e) => e.startsWith('two.example')).length;
  await iptv.loadIptvSnapshot([], [], [], [], [], { stalkerPortals: twoPortals });
  assert.equal(events.filter((e) => e.startsWith('two.example')).length, before, 'portal two was served from the recent list');
  assert.ok(events.filter((e) => e.startsWith('one.example')).length > 1, 'the failed portal is asked again');
  const tested = await iptv.testIptvPlaylist(null, { stalkerPortals: [twoPortals[1]] });
  eq(tested, { ok: true, channels: 3 });
  assert.ok(events.filter((e) => e.startsWith('two.example')).length > before, 'Test asks the portal again');
});

test('the single-portal option still loads as Portal 1', async () => {
  const iptv = load('lib/iptv.ts', { './http': portalHttp([]), './storage': storage() });
  const snapshot = await iptv.loadIptvSnapshot([], [], [], [], [], { stalkerUrl: 'http://one.example/c/', stalkerMac: '00:1a:79:00:00:01' });
  assert.equal(snapshot.allChannels[0].id, 'stalker:stalker1:1');
  // A favourite saved by an older build under the address as typed (trailing slash) still resolves.
  assert.ok(snapshot.allChannels[0].syncAliases.includes(`stalker:${encodeURIComponent('http://one.example/c/')}:1`));
});

test('portals load after the playlists, as the single portal did', async () => {
  const events = [];
  const http = portalHttp(events);
  const iptv = load('lib/iptv.ts', { './http': { ...http, textRequest: async () => { events.push('playlist'); await new Promise((r) => setTimeout(r, 5)); return '#EXTM3U\n#EXTINF:-1,One\nhttp://example.test/1'; } }, './storage': storage() });
  const snapshot = await iptv.loadIptvSnapshot([{ id: 'list_1', name: 'List', m3uUrl: 'https://playlist.example/list.m3u', enabled: true }], [], [], [], [], { stalkerPortals: [twoPortals[0]] });
  eq(snapshot.allChannels.map((c) => c.id.split(':')[0]), ['list_1', 'stalker', 'stalker', 'stalker']);
  assert.ok(events.indexOf('playlist') < events.findIndex((e) => e.startsWith('one.example')), events.join(', '));
});

/* ---------- cloud: portals ---------- */

test('portals: Android list → web → payload keeps unknown fields, legacy fields and timestamps', async () => {
  const androidPortal = { id: 'stalker1', name: 'Home', portalUrl: 'http://one.example/c', macAddress: '00:1A:79:00:00:01', enabled: false,
    importLiveTv: true, importVod: true, importSeries: false, futureAndroidField: 'keep me' };
  const f = cloudFixture({ iptvByProfile: { p1: { playlists: [], stalkerPortals: [androidPortal], stalkerPortalUrl: '', stalkerMacAddress: '' } },
    fieldUpdatedAt: { 'i:p1:stalkerPortals': 100 } });
  const read = await pulled(f);
  eq(read.iptvStalkerPortals, [androidPortal]);
  assert.equal(read.iptvStalkerUrl, '', 'no enabled portal');
  // An unrelated save writes nothing about portals.
  await f.cloud.saveCloudSettings(auth, { ...read, accentColor: 'rose' }, [], 'p1', [], read, 200);
  eq(f.remote.iptvByProfile.p1.stalkerPortals, [androidPortal]);
  assert.equal(f.remote.fieldUpdatedAt['i:p1:stalkerPortals'], 100);
  // Adding a second portal on the web.
  const next = { ...read, ...portals.stalkerPortalsPatch(portals.upsertStalkerPortal(read.iptvStalkerPortals, { name: 'Away', portalUrl: 'http://two.example/c/', macAddress: '00:1a:79:00:00:02' })) };
  await f.cloud.saveCloudSettings(auth, next, [], 'p1', [], read, 300);
  const saved = f.remote.iptvByProfile.p1;
  eq(saved.stalkerPortals, [androidPortal, { id: 'stalker2', name: 'Away', portalUrl: 'http://two.example/c', macAddress: '00:1A:79:00:00:02', enabled: true }]);
  assert.equal(saved.stalkerPortalUrl, 'http://two.example/c');
  assert.equal(saved.stalkerMacAddress, '00:1A:79:00:00:02');
  assert.equal(f.remote.fieldUpdatedAt['i:p1:stalkerPortals'], 300);
  assert.equal(f.remote.fieldUpdatedAt['i:p1:stalkerPortalUrl'], 300);
  eq((await pulled(f)).iptvStalkerPortals, saved.stalkerPortals);
});

test('portals: a payload from a single-portal build becomes Portal 1 and its stalker| keys move with it', async () => {
  const f = cloudFixture({ iptvByProfile: { p1: { playlists: [], stalkerPortalUrl: 'http://one.example/c/', stalkerMacAddress: '00:1a:79:00:00:01',
    hiddenGroups: ['stalker|Adult'], groupOrder: ['stalker|News', 'stalker|Sport'], groupOrderSchema: 3, channelOrder: { 'stalker|News': ['stalker:stalker1:2'] } } } });
  const read = await pulled(f);
  eq(read.iptvStalkerPortals, [{ id: 'stalker1', name: 'Portal 1', portalUrl: 'http://one.example/c', macAddress: '00:1A:79:00:00:01', enabled: true }]);
  eq(read.hiddenGroupIds, ['stalker1|Adult']);
  eq(read.groupOrder, ['stalker1|News', 'stalker1|Sport']);
  eq(read.iptvChannelOrder, { 'stalker1|News': ['stalker:stalker1:2'] });
});

test('portals: a browser that never read the list does not write one', async () => {
  const f = cloudFixture({ iptvByProfile: { p1: { playlists: [], stalkerPortals: [{ id: 'stalker1', name: 'TV', portalUrl: 'http://tv.example', macAddress: '00:1A:79:00:00:09', enabled: true }] } } });
  const old = base();
  await f.cloud.saveCloudSettings(auth, { ...old, iptvSortOrder: 'name' }, [], 'p1', [], old, 200);
  assert.equal(f.remote.iptvByProfile.p1.stalkerPortals.length, 1);
  assert.equal(f.remote.fieldUpdatedAt?.['i:p1:stalkerPortals'], undefined);
});

/* ---------- category names ---------- */

test('group names: helpers clean names and a reset or the playlist name removes the entry', () => {
  eq(plain(groups.normalizeGroupNames({ 'p|News': '  My   News ', 'p|Same': 'Same', noKey: 'x', 'p|Empty': ' ', 'p|Num': 5 })), { 'p|News': 'My News' });
  let names = groups.setGroupDisplayName(undefined, 'p|News', ' Headlines ');
  eq(plain(names), { 'p|News': 'Headlines' });
  assert.equal(groups.groupDisplayName(names, 'p|News'), 'Headlines');
  assert.equal(groups.groupDisplayName(names, 'p|Sport'), 'Sport');
  eq(plain(groups.setGroupDisplayName(names, 'p|News', 'News')), {});
  eq(plain(groups.setGroupDisplayName(names, 'p|News', '')), {});
  assert.equal(groups.setGroupDisplayName({}, 'p|A', 'x'.repeat(100))['p|A'].length, groups.MAX_GROUP_NAME_LENGTH);
  // Search finds a category by either name.
  const group = { name: 'UK | Sports HD', label: 'Football' };
  assert.ok(groups.groupMatchesQuery(group, 'foot'));
  assert.ok(groups.groupMatchesQuery(group, 'sports'));
  assert.ok(!groups.groupMatchesQuery(group, 'news'));
  // Channel search on the Live TV page matches the given name too.
  const channel = { id: 'p:1', name: 'Sky', group: 'UK | Sports HD', streamUrl: '' };
  assert.ok(guide.channelMatchesQuery(channel, 'football', undefined, 'Football'));
  assert.ok(guide.channelMatchesQuery(channel, 'uk |', undefined, 'Football'));
  assert.ok(!guide.channelMatchesQuery(channel, 'football'));
});

test('group names: web → payload (Android shape) → web, merged per category and stamped', async () => {
  const f = cloudFixture({ iptvByProfile: { p1: { playlists: [], groupNames: { 'p|A': 'Alpha', 'p|R': 'Remote' } } }, fieldUpdatedAt: { 'i:p1:groupNames': 100 } });
  const read = await pulled(f);
  eq(read.iptvGroupNames, { 'p|A': 'Alpha', 'p|R': 'Remote' });
  // The TV renames another category after this browser read the cloud.
  f.remote.iptvByProfile.p1.groupNames['p|Other'] = 'From TV';
  const next = { ...read, iptvGroupNames: groups.setGroupDisplayName(groups.setGroupDisplayName(read.iptvGroupNames, 'p|A', 'Aleph'), 'p|B', 'Beta') };
  await f.cloud.saveCloudSettings(auth, next, [], 'p1', [], read, 500);
  eq(f.remote.iptvByProfile.p1.groupNames, { 'p|A': 'Aleph', 'p|R': 'Remote', 'p|Other': 'From TV', 'p|B': 'Beta' });
  assert.equal(f.remote.fieldUpdatedAt['i:p1:groupNames'], 500);
  for (const [key, value] of Object.entries(f.remote.iptvByProfile.p1.groupNames)) { assert.match(key, /\|/); assert.equal(typeof value, 'string'); }
  // Reset one name: only that key goes.
  const after = await pulled(f);
  await f.cloud.saveCloudSettings(auth, { ...after, iptvGroupNames: groups.setGroupDisplayName(after.iptvGroupNames, 'p|A', '') }, [], 'p1', [], after, 600);
  eq(f.remote.iptvByProfile.p1.groupNames, { 'p|R': 'Remote', 'p|Other': 'From TV', 'p|B': 'Beta' });
  // An unrelated save keeps the timestamp.
  const last = await pulled(f);
  await f.cloud.saveCloudSettings(auth, { ...last, accentColor: 'rose' }, [], 'p1', [], last, 700);
  assert.equal(f.remote.fieldUpdatedAt['i:p1:groupNames'], 600);
});

/* ---------- channels without a category name ---------- */

test('channels without a category name use the same key as Android', () => {
  const iptv = load('lib/iptv.ts', { './http': {}, './storage': storage() });
  const channels = iptv.parseM3u([
    '#EXTM3U',
    '#EXTINF:-1 tvg-id="a",No group', 'http://example.test/a',
    '#EXTINF:-1 tvg-id="b" group-title=" ",Blank group', 'http://example.test/b',
    '#EXTINF:-1 tvg-id="c" group-title="News",Named', 'http://example.test/c',
  ].join('\n'), 'list_1');
  // Android's M3U parser: a missing or blank group-title is "Uncategorized".
  eq(channels.map((c) => c.group), ['Uncategorized', 'Uncategorized', 'News']);
  eq(channels.map(iptv.groupKey), ['list_1|Uncategorized', 'list_1|Uncategorized', 'list_1|News']);
  // A channel stored without any name (LiveCategory.playlistGroupLabel) is "Ungrouped" on both sides.
  assert.equal(iptv.groupKey({ id: 'list_1:x', group: '  ' }), 'list_1|Ungrouped');
  assert.equal(groups.playlistGroupKey('list_1', ''), 'list_1|Ungrouped');
});

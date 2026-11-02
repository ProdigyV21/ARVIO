// Review fixes for several Stalker portals: legacy `stalker|…` keys left in the
// cloud must not undo web edits, the portal list merges per portal, Refresh asks
// portals again, and a new portal does not inherit a removed portal's layout.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const plain = (value) => JSON.parse(JSON.stringify(value));
const eq = (actual, expected, message) => assert.deepEqual(plain(actual), plain(expected), message);
const portals = load('lib/stalkerPortals.ts');

const cloud = load('lib/cloud.ts', {
  './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
  './homeserver': { serializeHomeServerConnectionJson: () => '', parseHomeServerConnectionJson: () => [] },
  './iptv': load('lib/iptv.ts', { './http': {}, './storage': storage() }), './mediaImages': {}, './http': {},
});
const portal1 = { id: 'stalker1', name: 'Portal 1', portalUrl: 'http://p', macAddress: '00:1A:79:00:00:01', enabled: true };
const settings = (patch = {}) => ({
  iptvPlaylists: [], iptvStalkerUrl: 'http://p', iptvStalkerMac: '00:1A:79:00:00:01', favoriteChannelIds: [], favoriteGroupIds: [],
  hiddenGroupIds: [], groupOrder: [], iptvSortOrder: 'provider', iptvStalkerPortals: [portal1], ...patch,
});
const legacyKeys = (value) => JSON.stringify(value).match(/"stalker\|[^"]*"|stalker:http/g) ?? [];

/* ---------- legacy keys in the cloud ---------- */

test('un-hiding, un-favouriting and reordering with legacy keys still in the cloud stick', () => {
  const existing = { hiddenGroups: ['stalker|News'], favoriteGroups: ['stalker|Sport'], groupOrder: ['stalker|A', 'stalker|B'], groupOrderSchema: 3,
    stalkerPortalUrl: 'http://p', stalkerMacAddress: '00:1A:79:00:00:01' };
  const baseline = settings({ hiddenGroupIds: ['stalker1|News'], favoriteGroupIds: ['stalker1|Sport'], groupOrder: ['stalker1|A', 'stalker1|B'] });
  const local = { ...baseline, hiddenGroupIds: [], favoriteGroupIds: [], groupOrder: ['stalker1|B', 'stalker1|A'] };
  const merged = cloud.mergeIptvSettings(existing, local, baseline);
  eq(merged.hiddenGroups, []);
  eq(merged.favoriteGroups, []);
  eq(merged.groupOrder, ['stalker1|B', 'stalker1|A']);
  eq(legacyKeys(merged), [], 'nothing in the old shape is written back');
});

test('resetting a channel order or a name removes it even when the cloud has the legacy key', () => {
  const existing = { channelOrder: { 'stalker|News': ['stalker:stalker1:1', 'stalker:stalker1:2'] }, groupNames: { 'stalker|News': 'Headlines' } };
  const baseline = settings({ iptvChannelOrder: { 'stalker1|News': ['stalker:stalker1:1', 'stalker:stalker1:2'] }, iptvGroupNames: { 'stalker1|News': 'Headlines' } });
  const merged = cloud.mergeIptvSettings(existing, { ...baseline, iptvChannelOrder: {}, iptvGroupNames: {} }, baseline);
  eq(merged.channelOrder, {});
  eq(merged.groupNames, {});
});

test('a favourite channel saved under the old web id can be removed', () => {
  const legacyId = `stalker:${encodeURIComponent('http://p/')}:5`;
  const existing = { favoriteChannels: [legacyId, 'list_1:xtream:9'], tvSession: { lastChannelId: legacyId, recentChannelIds: [legacyId] } };
  const baseline = settings({ favoriteChannelIds: ['stalker:stalker1:5', 'list_1:xtream:9'] });
  const merged = cloud.mergeIptvSettings(existing, { ...baseline, favoriteChannelIds: ['list_1:xtream:9'] }, baseline);
  eq(merged.favoriteChannels, ['list_1:xtream:9']);
  eq(merged.tvSession.recentChannelIds, ['stalker:stalker1:5']);
  eq(legacyKeys(merged), []);
});

test('new-shape keys win over their legacy twins, in their own position', () => {
  eq(portals.migrateLegacyStalkerKeys(['stalker|A', 'stalker1|B', 'stalker|B', 'stalker1|A', 'stalker|C']), ['stalker1|B', 'stalker1|A', 'stalker1|C']);
  eq(portals.migrateLegacyStalkerChannelId('stalker:123', []), 'stalker:stalker1:123');
  eq(portals.migrateLegacyStalkerChannelId(`stalker:${encodeURIComponent('http://two.example/c/')}:7`, [portal1, { ...portal1, id: 'stalker2', portalUrl: 'http://two.example/c' }]), 'stalker:stalker2:7');
  eq(portals.migrateLegacyStalkerChannelId(`stalker:${encodeURIComponent('http://gone.example')}:7`, [portal1]), `stalker:${encodeURIComponent('http://gone.example')}:7`);
  eq(portals.migrateLegacyStalkerChannelId('stalker:stalker2:7', [portal1]), 'stalker:stalker2:7');
});

/* ---------- portal list merge ---------- */

test('a portal added on the TV survives a web edit of another portal', () => {
  const tvPortal = { id: 'stalker2', name: 'TV portal', portalUrl: 'http://tv.example', macAddress: '00:1A:79:00:00:02', enabled: true, extra: 1 };
  const existing = { stalkerPortals: [{ ...portal1, name: 'Renamed on TV' }, tvPortal] };
  const baseline = settings();
  const local = { ...baseline, iptvStalkerPortals: [{ ...portal1, importVod: false }] };
  const merged = cloud.mergeIptvSettings(existing, local, baseline);
  eq(merged.stalkerPortals, [{ ...portal1, name: 'Renamed on TV', importVod: false }, tvPortal]);
  eq([merged.stalkerPortalUrl, merged.stalkerMacAddress], ['http://p', '00:1A:79:00:00:01']);
});

test('removing a portal on the web removes only that one; a portal removed on the TV stays removed', () => {
  const tvPortal = { id: 'stalker3', name: 'TV portal', portalUrl: 'http://tv.example', macAddress: '00:1A:79:00:00:03', enabled: true };
  const second = { id: 'stalker2', name: 'Two', portalUrl: 'http://two.example', macAddress: '00:1A:79:00:00:02', enabled: true };
  // The TV removed portal 2 and added portal 3; the web removed portal 1.
  const existing = { stalkerPortals: [portal1, tvPortal] };
  const baseline = settings({ iptvStalkerPortals: [portal1, second] });
  const merged = cloud.mergeIptvSettings(existing, { ...baseline, iptvStalkerPortals: [second] }, baseline);
  eq(merged.stalkerPortals.map((p) => p.id), ['stalker3']);
  eq(merged.stalkerPortalUrl, 'http://tv.example', 'the single-portal fields follow the merged list');
});

test('without a baseline the web list is written as it is', () => {
  const merged = cloud.mergeIptvSettings({ stalkerPortals: [{ ...portal1, id: 'stalker9' }] }, settings(), null);
  eq(merged.stalkerPortals.map((p) => p.id), ['stalker1']);
});

/* ---------- refresh ---------- */

test('Refresh asks the portals again; automatic loads may reuse their recent list', async () => {
  const { createIptvRefresher } = load('lib/iptvRefresh.ts');
  const calls = [];
  const refresh = createIptvRefresher({
    read: () => ({ scope: 'a', signature: 's', settings: {} }),
    load: async (_settings, options) => { calls.push(options.fresh); return {}; },
    apply: () => {}, report: () => {}, setLoading: () => {},
  });
  await refresh();
  await refresh({ fresh: true });
  await refresh({ quiet: true });
  eq(calls, [false, true, false]);
});

/* ---------- new portal ids ---------- */

test('a new portal does not take over the id of a removed portal that settings still refer to', () => {
  const left = { hiddenGroupIds: ['stalker1|Adult'], favoriteChannelIds: ['stalker:stalker2:5'], iptvGroupNames: { 'list_1|News': 'x' } };
  eq(portals.referencedStalkerPortalIds(left).sort(), ['stalker1', 'stalker2']);
  eq(portals.nextStalkerPortalId([], portals.referencedStalkerPortalIds(left)), 'stalker3');
  eq(portals.upsertStalkerPortal([], { name: 'New', portalUrl: 'http://new.example', macAddress: '00:1A:79:00:00:04' }, ['stalker1'])[0].id, 'stalker2');
  // Reserved ids do not count towards the ten-portal limit.
  eq(portals.nextStalkerPortalId(['stalker4', 'stalker5'], ['stalker1', 'stalker2', 'stalker3']), 'stalker6');
  eq(portals.nextStalkerPortalId(Array.from({ length: 10 }, (_, i) => `stalker${i + 1}`), []), null);
  // Android keeps ids above three as they are.
  eq(portals.normalizeStalkerPortals([{ ...portal1, id: 'stalker6' }]).map((p) => p.id), ['stalker6']);
});

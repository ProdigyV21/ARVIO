const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');
function modules(http = {}) {
  const loaded = {};
  function load(name) {
    if (name === './http') return { proxiedUrl: u => u, ...http };
    if (name === './config') return { config: {} };
    if (loaded[name]) return loaded[name];
    const exports = loaded[name] = {};
    const code = ts.transpileModule(fs.readFileSync(require.resolve(`../lib/${name.slice(2)}.ts`), 'utf8'), { compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 } }).outputText;
    vm.runInNewContext(code, { exports, require: load, Date, URL, Map, Set, AbortSignal, AbortController });
    return exports;
  }
  return { ...load('./sportsAddons'), ...load('./sportsGuide') };
}
const now = Date.parse('2026-09-21T18:00:00Z');
const catalog = { type: 'sport', id: 'sports_live', name: 'Live Now', extra: [{ name: 'skip' }] };
const addon = { id: 'sports', name: 'Sports', version: '1', manifestUrl: 'https://example.com/config/manifest.json?token=private', resources: ['stream', 'catalog'], catalogs: [catalog] };
const meta = { id: 'event:1', type: 'sport', name: 'North vs South', genres: ['Football'], releaseInfo: 'LIVE', background: 'https://example.com/match.jpg' };
const channel = { id: 'p:1', name: 'Sport', streamUrl: 'https://example.com/live.m3u8', group: 'Sports' };
const guide = { id: 'guide', title: 'North vs South', sportId: 'football', programme: { title: 'North vs South', startUtcMillis: now - 60_000, endUtcMillis: now + 60_000 }, channels: [channel] };

const paidArt = { title: meta.name, key: 'north vs south', genres: ['Football'], startsAt: now,
  source: 'TheSportsDB', background: 'https://example.com/official.jpg',
  homeBadge: 'https://example.com/north.png', awayBadge: 'https://example.com/south.png' };
test('subscription artwork enriches addon-only events and preserves playable sources', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  for (const events of [[], [guide]]) {
    const result = m.attachSportsAddonSources(events, [s], now, [paidArt]);
    assert.equal(result.length, 1);
    assert.equal(result[0].artwork, paidArt.background);
    assert.equal(result[0].addonSources[0].eventId, s.eventId);
  }
});
test('subscription team badges outrank addon and secondary-feed banners', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  const badges = { ...paidArt, background: '' };
  const result = m.attachSportsAddonSources([], [s], now, [{ ...paidArt, source: 'ESPN' }, badges])[0];
  assert.equal(result.artwork, undefined);
  assert.equal(result.teamArtwork.homeBadge, paidArt.homeBadge);
  const existing = m.attachSportsAddonSources([{ ...guide, teamArtwork: badges }], [s], now)[0];
  assert.equal(existing.artwork, undefined);
});
test('unmatched metadata cannot erase fallback art or borrow another fixture artwork', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  for (const art of [[], [{ ...paidArt, startsAt: now + 86_400_000 }], [{ ...paidArt, genres: ['Basketball'] }],
    [{ ...paidArt, title: 'North U21 vs South U21' }], [{ ...paidArt, background: '', homeBadge: undefined, awayBadge: undefined }]]) {
    const result = m.attachSportsAddonSources([], [s], now, art)[0];
    assert.equal(result.artwork, s.artwork);
    assert.equal(result.teamArtwork, undefined);
  }
});

test('both IPTV and multiple addons share one event without duplicate sources', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  const result = m.attachSportsAddonSources([guide], [s, s, { ...s, key: 'other', installation: 'other' }], now);
  assert.equal(result.length, 1); assert.equal(result[0].channels.length, 1); assert.equal(result[0].addonSources.length, 2);
});
test('addon-only live and upcoming events appear without an IPTV list', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  const result = m.attachSportsAddonSources([], [s, { ...s, key: 'future', title: 'East vs West', startsAt: now + 3_600_000, live: false }], now);
  assert.equal(result.length, 2); assert.equal(m.isOnAir(result[0], now), true); assert.equal(m.isOnAir(result[1], now), false);
  assert.ok(m.sportsPresentationRows(result, now, new Set()).length);
});

test('fresh live results do not wait for the next guide clock tick', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now + 1000);
  const result = m.attachSportsAddonSources([], [s], now);
  assert.equal(result.length, 1);
  assert.equal(m.isOnAir(result[0], now), true);
});
test('matching rejects wrong date, sport and youth qualifiers', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  for (const other of [{ ...s, startsAt: now + 86_400_000, live: false }, { ...s, genres: ['Basketball'] }, { ...s, title: 'North U21 vs South U21' }])
    assert.equal(m.attachSportsAddonSources([guide], [other], now)[0].addonSources.length, 0);
});
test('parse highfly UTC description, live marker and standard ISO dates', () => {
  const m = modules();
  assert.equal(m.toSportsAddonEvent({ ...meta, releaseInfo: '21 Sep 2026 · 22:35 UTC' }, addon, catalog, now).startsAt, Date.parse('2026-09-21T22:35:00Z'));
  assert.equal(m.toSportsAddonEvent({ ...meta, released: '2026-09-21T19:00:00+01:00' }, addon, catalog, now).startsAt, now);
  assert.equal(m.toSportsAddonEvent({ ...meta, name: '🔴 LIVE: North vs South' }, addon, catalog, now).title, meta.name);
});
test('does not invent times, include replays/recordings or preserve stale live', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  assert.equal(m.toSportsAddonEvent({ ...meta, releaseInfo: 'TBD' }, addon, catalog, now), null);
  assert.equal(m.toSportsAddonEvent({ ...meta, id: 'leaf:channel' }, addon, catalog, now), null);
  assert.equal(m.toSportsAddonEvent({ ...meta, releaseInfo: 'Replay' }, addon, catalog, now), null);
  assert.equal(m.attachSportsAddonSources([], [s], now + 300_001).length, 0);
});
test('configured path and query survive catalog and stream URLs', () => {
  const m = modules();
  assert.equal(m.sportsAddonUrl(addon.manifestUrl, 'stream', 'sport', 'event:1'), 'https://example.com/config/stream/sport/event%3A1.json?token=private');
  assert.match(m.sportsAddonUrl(addon.manifestUrl, 'catalog', 'sport', 'sports_live', 20), /sports_live\/skip=20.json\?token=private$/);
});
test('disabled addons and replay catalogs are not requested', () => {
  const m = modules();
  assert.equal(m.sportsEventCatalogs({ ...addon, enabled: false }).length, 0);
  assert.equal(m.sportsEventCatalogs({ ...addon, catalogs: [catalog, { ...catalog, id: 'sports_replays' }] }).length, 1);
});
test('pagination stops when a provider ignores skip; progress publishes before finishing', async () => {
  const urls = [], batches = [];
  const m = modules({ jsonRequest: async url => { urls.push(url); return { metas: [meta] }; } });
  const result = await m.loadSportsAddonEvents([addon], new AbortController().signal, events => batches.push(events));
  assert.equal(urls.length, 2); assert.equal(result.length, 1); assert.ok(batches.length); assert.match(urls[1], /skip=1/);
});
test('provider failures do not remove other addons and stream URLs are resolved lazily', async () => {
  const urls = [];
  const m = modules({ jsonRequest: async url => { urls.push(url); if (url.includes('broken')) throw Error('offline'); return { metas: [meta] }; } });
  const result = await m.loadSportsAddonEvents([addon, { ...addon, manifestUrl: 'https://broken.test/manifest.json' }], new AbortController().signal, () => {});
  assert.equal(result.length, 1); assert.ok(urls.every(u => u.includes('/catalog/')));
});
test('stream resolution preserves headers and distinguishes external webpages', async () => {
  const m = modules({ jsonRequest: async () => ({ streams: [
    { name: 'One', url: 'https://example.com/stream', headers: { Referer: 'https://example.com' }, behaviorHints: { proxyHeaders: { request: { Authorization: 'secret' } } } },
    { name: 'External', externalUrl: 'https://example.com/player' }
  ] }) });
  const s = m.toSportsAddonEvent(meta, addon, catalog, now);
  const streams = await m.resolveSportsAddon(s, [addon], new AbortController().signal);
  assert.equal(streams[0].headers.Authorization, 'secret'); assert.equal(streams[0].external, false); assert.equal(streams[1].external, true);
  assert.equal((await m.resolveSportsAddon(s, [{ ...addon, enabled: false }], new AbortController().signal)).length, 0);
});

test('missing genres match only an unambiguous event and equal-time ties stay separate', () => {
  const m = modules(), s = m.toSportsAddonEvent({ ...meta, genres: [] }, addon, catalog, now);
  assert.equal(m.attachSportsAddonSources([guide], [s], now).length, 1);
  const ambiguous = m.attachSportsAddonSources([guide, { ...guide, id: 'basketball', sportId: 'basketball' }], [s], now);
  assert.ok(ambiguous.slice(0, 2).every(e => !e.addonSources.length));
  const dated = { ...s, genres: ['Football'], startsAt: now };
  const tied = m.attachSportsAddonSources([guide, { ...guide, id: 'same-time' }], [dated], now);
  assert.ok(tied.slice(0, 2).every(e => !e.addonSources.length));
});

test('duplicate catalogs retain live evidence, date and art without refreshing the live timestamp', () => {
  const m = modules(), s = m.toSportsAddonEvent(meta, addon, catalog, now);
  const sparse = { ...s, live: false, observedAt: now + 10_000, artwork: undefined, genres: [], startsAt: now - 60_000 };
  const merged = m.mergeSportsAddonEvent(s, sparse);
  assert.equal(merged.live, true); assert.equal(merged.observedAt, now);
  assert.equal(merged.artwork, s.artwork); assert.equal(merged.genres[0], 'Football');
  assert.equal(merged.startsAt, sparse.startsAt);
  assert.equal(m.attachSportsAddonSources([], [s, sparse], now)[0].addonSources.length, 1);
  assert.equal(m.addonEventIsLive(merged, now + 300_001), false);
});

test('failed catalogs are retried rather than cached as empty for two minutes', async () => {
  let fail = true, calls = 0;
  const m = modules({ jsonRequest: async () => { calls++; if (fail) throw Error('temporary'); return { metas: [meta] }; } });
  const a = { ...addon, catalogs: [{ ...catalog, extra: [] }] };
  assert.equal((await m.loadSportsAddonEvents([a], new AbortController().signal, () => {})).length, 0);
  fail = false;
  assert.equal((await m.loadSportsAddonEvents([a], new AbortController().signal, () => {})).length, 1);
  assert.equal(calls, 2);
});

test('upstream channel and quality details are preserved without claiming verified event content', async () => {
  const m = modules({ jsonRequest: async () => ({ streams: [{ name: 'Leaf: Sport Klub 3', title: '720p Stereo', url: 'https://example.com/live' }] }) });
  const result = await m.resolveSportsAddon(m.toSportsAddonEvent(meta, addon, catalog, now), [addon], new AbortController().signal);
  assert.equal(result[0].name, 'Leaf: Sport Klub 3'); assert.equal(result[0].description, '720p Stereo');
});

test('live-sport-plugin tv protocol preserves exact IDs and merges with Highfly and IPTV', async () => {
  const urls = [];
  const m = modules({ jsonRequest: async url => { urls.push(url); return { streams: [{ name: 'Provider', url: 'https://media.example/live.m3u8' }] }; } });
  const second = { ...addon, id: 'community.example.live-sports', name: 'Live Sports', types: ['tv', 'series', 'channel'],
    manifestUrl: 'https://second.example/config/manifest.json', catalogs: [
      { type: 'tv', id: 'community_sports_live', name: 'Live Now' },
      { type: 'tv', id: 'community_sports_networks', name: '24/7 Live TV' },
      { type: 'tv', id: 'community_sports_replays', name: 'Sports Replays' }
    ] };
  const catalogs = m.sportsEventCatalogs(second);
  assert.equal(catalogs.length, 1);
  const source = m.toSportsAddonEvent({ id: 'community_sport_match-123', type: 'tv', name: '\uD83D\uDD34 LIVE: North vs South',
    released: new Date(now - 60_000).toISOString(), releaseInfo: 'LIVE', genres: ['FOOTBALL'] }, second, catalogs[0], now);
  const highfly = m.toSportsAddonEvent(meta, addon, catalog, now);
  const merged = m.attachSportsAddonSources([guide], [highfly, source], now);
  assert.equal(merged.length, 1); assert.equal(merged[0].addonSources.length, 2); assert.equal(merged[0].channels.length, 1);
  await m.resolveSportsAddon(source, [second], new AbortController().signal);
  assert.equal(urls[0], 'https://second.example/config/stream/tv/community_sport_match-123.json');
});

test('promotional links are excluded and playable video precedes external handoffs', async () => {
  const m = modules({ jsonRequest: async () => ({ streams: [
    { name: 'Support the project!', externalUrl: 'https://example.com/support' },
    { name: 'Watch on provider', externalUrl: 'https://example.com/watch' },
    { name: 'Channel HD', url: 'https://example.com/live.m3u8' }
  ] }) });
  const result = await m.resolveSportsAddon(m.toSportsAddonEvent(meta, addon, catalog, now), [addon], new AbortController().signal);
  assert.deepEqual(Array.from(result, s => s.name), ['Channel HD', 'Watch on provider']);
});

test('sports provider notices cannot become playable sources while genuine media remains', async () => {
  const m = modules({ jsonRequest: async () => ({ streams: [
    { name: 'Join Discord', url: 'https://discord.gg/example' },
    { name: 'Match not found or has ended', url: 'https://example.com/unavailable.html' },
    { name: 'Unavailable', title: 'Match not found or has ended', url: 'https://www.google.com/' },
    { name: 'Match not found or has ended', url: 'https://example.com/live.m3u8' }
  ] }) });
  const result = await m.resolveSportsAddon(m.toSportsAddonEvent(meta, addon, catalog, now), [addon], new AbortController().signal);
  assert.equal(result.length, 1);
  assert.equal(result[0].url, 'https://example.com/live.m3u8');
});

test('hybrid PenguPlay includes general live/upcoming catalogs and emoji status without promoting replays', () => {
  const m = modules();
  const pengu = { ...addon, id: 'com.penguplay', name: 'PenguPlay', types: ['movie', 'series', 'tv'], catalogs: [
    { type: 'tv', id: 'pp-live-now', name: 'Live Now - TV' }, { type: 'tv', id: 'pp-live-football', name: 'Soccer - TV' },
    { type: 'tv', id: 'pp-live-upcoming', name: 'Upcoming - TV' }] };
  assert.equal(m.sportsEventCatalogs(pengu).length, 3);
  const raw = { ...meta, id: 'pp-live:provider~123', type: 'tv', name: '\uD83D\uDD34 LIVE: North vs South',
    releaseInfo: '2026-09-21 19:00 CEST', released: '2026-09-21T17:00:00Z', description: 'Soccer\n\uD83D\uDD34 Live now\nKickoff: 19:00 CEST' };
  assert.equal(m.toSportsAddonEvent(raw, pengu, pengu.catalogs[0], now).live, true);
  assert.equal(m.toSportsAddonEvent({ ...raw, name: 'North vs South' }, pengu, pengu.catalogs[0], now).live, true);
  assert.equal(m.toSportsAddonEvent({ ...raw, description: 'Finished — a replay may still be available' }, pengu, pengu.catalogs[0], now), null);
});

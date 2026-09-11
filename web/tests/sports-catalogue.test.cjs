const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');
const modules = {};
function load(name) {
  if (modules[name]) return modules[name];
  if (name === './http') return {};
  if (name === './config') return { config: {} };
  const sandbox = { exports: {}, URL, Date, require: load };
  vm.runInNewContext(ts.transpileModule(fs.readFileSync(`${__dirname}/../lib/${name.slice(2)}.ts`, 'utf8'), {
    compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 },
  }).outputText, sandbox);
  return modules[name] = sandbox.exports;
}
const { parseSportsMetadata } = load('./sportsArtwork');
const { buildSportsCatalogue, sportsProminence } = load('./sportsCatalogue');
const { sportsGuideRows, isOnAir, isConfirmedLive, availableEventChannels, hasSportsChannels } = load('./sportsGuide');
const now = Date.parse('2026-09-10T12:00:00Z');
const channel = { id: 'p:1', name: 'UK | Sky Sports Main Event FHD', streamUrl: 'https://example.invalid/live' };
const raw = { id: '42', title: 'North vs South', sport: 'Soccer', startsAt: now + 3600000, status: 'scheduled', league: 'English Premier League', observedAt: now,
  broadcasters: [{ name: 'Sky Sports Main Event HD', country: 'United Kingdom', startsAt: now + 3600000 }] };
const art = (changes = {}) => parseSportsMetadata({ version: 1, catalogueEnabled: true, events: [{ ...raw, ...changes }] });
const epg = { id: 'guide', title: raw.title, sportId: 'football', competition: 'Premier League', programme: { title: raw.title, startUtcMillis: now - 60000, endUtcMillis: now + 3600000 }, channels: [channel], schedules: { [channel.id]: { title: raw.title, startUtcMillis: now - 60000, endUtcMillis: now + 3600000 } } };

test('real provider decorations match broadcasters without mixing countries or channel numbers', () => {
  const { sportsChannelKey } = load('./sportsCatalogue');
  assert.equal(sportsChannelKey('UK-NOWTV| TNT SPORT 2 FHD'), sportsChannelKey('UK TNT Sports 2'));
  assert.notEqual(sportsChannelKey('UK-NOWTV| TNT SPORT 2 FHD'), sportsChannelKey('DE TNT Sports 2'));
  assert.notEqual(sportsChannelKey('UK-NOWTV| TNT SPORT 2 FHD'), sportsChannelKey('UK TNT Sports 1'));
});
test('decorated guide titles match both participants but not womens fixtures', () => {
  const decorated = { ...epg, title: 'Football: North - South, Premier League 2026/2027' };
  const metadata = art({ startsAt: now, homeTeam: 'North', awayTeam: 'South', homeBadge: 'https://example.com/n.png', awayBadge: 'https://example.com/s.png' });
  const events = buildSportsCatalogue([decorated], metadata, [], now);
  assert.equal(events.length, 1);
  assert.equal(events[0].channels.length, 1);
  assert.ok(events[0].teamArtwork);
  const women = buildSportsCatalogue([{ ...decorated, title: 'Football: North - South, Women' }], metadata, [], now);
  assert.equal(women.find(e => e.fixture).channels.length, 0);
});
test('sports channel genre and descriptive mentions do not turn drama or downtime into events', () => {
  const { buildSportsGuideEvents } = load('./sportsGuide');
  for (const title of ['Sendepause', 'Die Aquarium-Profis', 'Murder Under the Friday Night Lights', "Familien Green i storby'n", 'Best of NBA Action']) {
    const p = { ...epg.programme, title, description: 'A family talks about football and cricket' };
    assert.equal(buildSportsGuideEvents([{ ...channel, group: 'Football' }], { [channel.id]: { now: p, upcoming: [] } }, now).length, 0, title);
  }
});
test('event UI has no bundled generic sport fallback', () => {
  const pane = fs.readFileSync(`${__dirname}/../components/livetv/SportsGuidePane.tsx`, 'utf8');
  assert.ok(!pane.includes('/images/sports/'));
  assert.ok(pane.includes('failedArtwork'));
});
test('fixtures without pictures or guide remain browsable, no invented duration or live flag', () => {
  const event = buildSportsCatalogue([], art(), [], now)[0];
  assert.equal(event.id, 'sportsdb:42');
  assert.equal(event.channels.length, 0);
  assert.equal(hasSportsChannels(event, now), false);
  assert.equal(isOnAir(event, now), false);
  assert.equal(isOnAir(event, now + 7200000), false);
  assert.equal(sportsGuideRows([event], now)[0].id, 'upcoming');
});
test('channel hints are separate from confirmed guide matches and respect regional prefixes', () => {
  const hint = buildSportsCatalogue([], art(), [channel, { ...channel, id: 'wrong-country', name: 'DE | Sky Sports Main Event HD' }], now)[0];
  assert.equal(hint.channels.length, 0);
  assert.equal(hint.possibleChannels.length, 1);
  assert.equal(hasSportsChannels(hint, now), true);
  const actual = buildSportsCatalogue([epg], art({ startsAt: now }), [channel], now);
  assert.equal(actual.length, 1);
  assert.equal(availableEventChannels(actual[0], now).length, 1);
  assert.equal(actual[0].schedules[channel.id].endUtcMillis, epg.programme.endUtcMillis);
  assert.equal(buildSportsCatalogue([], art(), [], now)[0].possibleChannels.length, 0);
});
test('50k channel index and 2k fixtures remain bounded without stream requests', () => {
  const channels = Array.from({length: 50000}, (_, i) => ({...channel, id: `p:${i}`, name: `Channel ${i}`}));
  const metadata = parseSportsMetadata({ version: 1, catalogueEnabled: true, events: Array.from({length: 2000}, (_, i) => ({...raw, id: String(i + 1), title: `Team ${i} vs Other`, broadcasters: [{name: `Channel ${i}`, country: '', startsAt: raw.startsAt}]})) });
  const start = performance.now();
  const events = buildSportsCatalogue([], metadata, channels, now);
  const elapsed = performance.now() - start;
  assert.equal(events.length, 2000);
  assert.equal(events.filter(e => e.possibleChannels.length === 1).length, 2000);
  console.log(`50k channels / 2k fixtures indexed in ${Math.round(elapsed)}ms (host JS, not a device frame benchmark)`);
  assert.ok(elapsed < 5000);
});
test('women, other sports, other leagues and distant broadcasts cannot steal matches', () => {
  for (const changes of [{ qualifier: 'women' }, { sport: 'Basketball' }, { startsAt: now + 10800000 }, { league: 'UEFA Champions League' }]) {
    const result = buildSportsCatalogue([epg], art(changes), [], now);
    assert.equal(result.find(e => e.fixture).channels.length, 0);
    assert.equal(result.filter(e => !e.fixture).length, 1);
  }
});
test('live scores expire independently; finished status suppresses stale guide', () => {
  const event = buildSportsCatalogue([], art({ startsAt: now - 60000, status: 'live' }), [], now)[0];
  assert.equal(isConfirmedLive(event, now), true);
  assert.equal(isOnAir(event, now + 300001), false);
  assert.equal(buildSportsCatalogue([epg], art({ startsAt: now, status: 'finished' }), [], now).length, 0);
});
test('featured highlights rank prominent competitions; sport rows keep upcoming chronological', () => {
  const prominent = buildSportsCatalogue([], art({ startsAt: now - 60000, status: 'live' }), [], now)[0];
  const minor = { ...prominent, id: 'minor', prominence: sportsProminence('Minor League') };
  assert.equal(sportsGuideRows([minor, prominent], now)[0].events[0].id, prominent.id);
  const later = { ...prominent, id: 'later', fixture: { ...prominent.fixture, status: 'scheduled' }, programme: { ...prominent.programme, startUtcMillis: now + 7200000 } };
  const earlier = { ...later, id: 'earlier', prominence: 0, programme: { ...later.programme, startUtcMillis: now + 3600000 } };
  const rows = sportsGuideRows([later, earlier], now);
  assert.equal(rows[0].events[0].id, 'later');
  assert.equal(rows.at(-1).events[0].id, 'earlier');
  assert.equal(rows.some(row => row.id === 'more'), false);
});

test('country suffix in broadcaster name preserves the exact channel and region', () => {
  const { sportsChannelKey, sportsBroadcasterKeys } = load('./sportsCatalogue');
  const keys = sportsBroadcasterKeys('ESPN 3 Netherlands', 'Netherlands');
  assert.ok(keys.includes(sportsChannelKey('NL | ESPN 3 UHD 8K')));
  assert.ok(!keys.includes(sportsChannelKey('US | ESPN 3 HD')));
  assert.ok(!keys.includes(sportsChannelKey('NL | ESPN 2 HD')));
  assert.ok(!sportsBroadcasterKeys('ESPN 3 France', 'Netherlands').includes(sportsChannelKey('NL ESPN 3')));
});
test('backend kill switch retains legacy artwork mode', () => {
  const items = parseSportsMetadata({ version: 1, catalogueEnabled: false, events: [raw] });
  assert.equal(items.length, 0);
});

test('all quality versions match across more regions without crossing channels or countries', () => {
  const { sportsBroadcasterKeys, sportsChannelKey } = load('./sportsCatalogue');
  const keys = sportsBroadcasterKeys('beIN Sports 2', 'Turkey');
  for (const name of ['TR| beINSPORTS2 FHD', 'TR| beIN Sport 2 1080p 50FPS BACKUP']) assert.ok(keys.includes(sportsChannelKey(name)), name);
  for (const name of ['FR| beIN Sports 2', 'TR| beIN Sports 3', 'TR| beIN Sports 2 +1']) assert.ok(!keys.includes(sportsChannelKey(name)), name);
  assert.ok(sportsBroadcasterKeys('SuperSport 1', 'South Africa').includes(sportsChannelKey('ZA | SuperSport 1 UHD')));
});

test('short and explicitly known participant aliases match all provider variants', () => {
  const metadata = art({ title: 'Manchester United vs PSV', homeTeam: 'Manchester United', awayTeam: 'PSV', startsAt: now });
  const other = { ...channel, id: 'other:2' };
  const guide = [{ ...epg, title: 'Football: Man Utd - PSV, Premier League' }, { ...epg, id: 'second', title: 'Football: PSV - Manchester United', channels: [other], schedules: { [other.id]: epg.programme } }];
  const event = buildSportsCatalogue(guide, metadata, [], now).find(e => e.fixture);
  assert.equal(event.channels.length, 2);
  assert.equal(buildSportsCatalogue([{ ...epg, title: 'Man City - PSV' }], metadata, [], now).find(e => e.fixture).channels.length, 0);
});

test('new sport families are classified, F1 and Australian football stay distinct', () => {
  for (const [sport, id] of [['Rugby', 'rugby'], ['Golf', 'golf'], ['Motorsport', 'motorsport'], ['Formula 1', 'f1'], ['Australian Football', 'australian-football'], ['Snooker', 'snooker'], ['Cycling', 'cycling'], ['Volleyball', 'volleyball']]) {
    const event = buildSportsCatalogue([], art({ sport }), [channel], now)[0];
    assert.equal(event.sportId, id);
    assert.ok(sportsGuideRows([event], now).some(row => row.id === id));
  }
});

test('missing or failed artwork never hides matched events, but hidden sources stay excluded', () => {
  const { sportsPresentationRows } = load('./sportsGuide');
  const event = buildSportsCatalogue([], art(), [channel], now)[0];
  const rows = sportsPresentationRows([event], now, new Set());
  assert.equal(rows.length, 1);
  assert.equal(rows[0].id, 'football-schedule');
  const pictured = { ...event, artwork: 'https://example.com/event.jpg' };
  assert.equal(sportsPresentationRows([pictured], now, new Set([event.id]))[0].id, 'football-schedule');
  assert.equal(sportsPresentationRows([{ ...event, possibleChannels: [] }], now, new Set()).length, 0);
});

test('premium broadcaster coverage is not truncated at one hundred', () => {
  const item = art({ broadcasters: Array.from({length:350}, (_, i) => ({name:`Sports ${i}`, country:'Netherlands', startsAt:now})) })[0];
  assert.equal(item.fixture.broadcasters.length, 350);
});

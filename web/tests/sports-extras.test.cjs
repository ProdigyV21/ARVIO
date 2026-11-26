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
  const sandbox = { exports: {}, URL, Date, Intl, require: load };
  vm.runInNewContext(ts.transpileModule(fs.readFileSync(`${__dirname}/../lib/${name.slice(2)}.ts`, 'utf8'), {
    compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 },
  }).outputText, sandbox);
  return modules[name] = sandbox.exports;
}
const x = load('./sportsExtras');
const plain = value => JSON.parse(JSON.stringify(value));
const now = Date.parse('2026-11-26T12:00:00Z');
const ch = (id, name = id, logo) => ({ id, name, streamUrl: `https://example.invalid/${id}`, logo });

test('league networks are found by channel name, like the Android app', () => {
  assert.equal(x.sportsNetworkFor('US: NBA TV HD')?.id, 'nba-tv');
  assert.equal(x.sportsNetworkFor('UK | Sky Sports Premier League FHD')?.id, 'premier-league');
  assert.equal(x.sportsNetworkFor('ESPN 2')?.id, 'espn');
  assert.equal(x.sportsNetworkFor('Strong8K \u00b7 CA EN: NBA TV HD')?.id, 'nba-tv');
  assert.equal(x.sportsNetworkFor('Strong8K \u00b7 AF: NBA TV \u1d3f\u1d2c\u1d42')?.id, 'nba-tv');
  assert.equal(x.sportsNetworkFor('Strong8K \u00b7 UK: BBC One'), undefined);
  assert.equal(x.sportsNetworkFor('ESPNews Weekly Magazine'), undefined);
  assert.equal(x.sportsNetworkFor('BBC One'), undefined);
});

test('network tiles: one per network, capped, followed sports first, synced Android sport names understood', () => {
  const channels = [ch('e1', 'ESPN'), ...Array.from({ length: 9 }, (_, i) => ch(`n${i}`, `NBA TV ${i}`, i === 2 ? 'https://example.invalid/logo.png' : undefined)), ch('b', 'BBC One')];
  const tiles = x.sportsNetworkEvents(channels, [{ kind: 'team', name: 'Boston Celtics', sport: 'BASKETBALL' }], now);
  assert.deepEqual(plain(tiles.map(t => t.id)), ['network:nba-tv', 'network:espn']);
  assert.equal(tiles[0].channels.length, x.SPORTS_NETWORK_CHANNELS);
  assert.equal(tiles[0].artwork, undefined);
  assert.ok(tiles.every(t => t.channelOnly && x.isNetworkEvent(t)));
  assert.ok(tiles[0].programme.startUtcMillis <= now && now < tiles[0].programme.endUtcMillis);
});

test('the channel last used for a competition comes first; memory stays bounded', () => {
  const event = { id: 'e', title: 'A vs B', sportId: 'football', competition: 'English Premier League', programme: { title: 'A vs B', startUtcMillis: now, endUtcMillis: now }, channels: [] };
  const keys = x.channelMemoryKeys(event);
  assert.deepEqual(plain(keys), ['league:premier league']);
  const memory = x.withRemembered({}, keys, 'c');
  assert.deepEqual(plain(x.withRememberedFirst([ch('a'), ch('b'), ch('c')], memory, keys).map(c => c.id)), ['c', 'a', 'b']);
  let full = {};
  for (let i = 1; i <= 100; i++) full = x.withRemembered(full, [`league:${i}`], `x${i}`);
  assert.equal(Object.keys(full).length, x.SPORTS_CHANNEL_MEMORY_LIMIT);
  assert.equal(full['league:100'], 'x100');
});

test('failover tries every other channel once, then stops', () => {
  let failover = { eventId: 'e', title: 'A vs B', channels: [ch('a'), ch('b'), ch('c')], tried: [] };
  const first = x.nextFailover(failover, 'b');
  assert.equal(first.channel.id, 'c');
  const second = x.nextFailover(first.failover, 'c');
  assert.equal(second.channel.id, 'a');
  assert.equal(x.nextFailover(second.failover, 'a'), undefined);
  assert.ok(x.failoverCovers(failover, 'a'));
  assert.equal(x.failoverCovers(null, 'a'), false);
});

test('game details parse and drop malformed parts; long tables keep the rows around both teams', () => {
  const details = x.parseSportsGameDetails({ version: 1, status: 'live', periods: [{ label: 'Q1', home: 30, away: 28 }, { label: 'Q2', home: 'x', away: 1 }],
    standings: [{ rank: 1, team: 'Knicks', highlight: true }, { rank: 2 }], form: { home: ['W', 'L', 'X'], away: [] },
    headToHead: [{ date: '2026-11-01', homeTeam: 'A', awayTeam: 'B', homeScore: 1, awayScore: 2 }, { date: 'bad', homeTeam: 'A', awayTeam: 'B', homeScore: 1, awayScore: 2 }] });
  assert.equal(details.periods.length, 1);
  assert.deepEqual(plain(details.homeForm), ['W', 'L']);
  assert.equal(details.headToHead.length, 1);
  assert.equal(x.parseSportsGameDetails({ version: 2 }), null);
  const rows = Array.from({ length: 20 }, (_, i) => ({ rank: i + 1, highlight: i === 9 || i === 17 }));
  assert.deepEqual(plain(x.standingsAround(rows).map(r => r.rank)), [1, 2, 9, 10, 11, 17, 18, 19]);
});

const score = (id, home, away, status, sport = 'Soccer') => ({ title: 'Ajax vs PSV', key: 'ajax vs psv', background: '', genres: [sport], startsAt: now - 3600000,
  homeTeam: 'Ajax', awayTeam: 'PSV', fixture: { id, status, observedAt: now, homeScore: home, awayScore: away, broadcasters: [] } });
const states = current => new Map([...current].map(([id, e]) => [id, e.state]));

test('score alerts: goals and full time after a baseline, never for the watched game or basketball baskets', () => {
  const follows = [{ kind: 'team', name: 'AFC Ajax' }];
  const first = x.followedScores([score('1', 0, 0, 'live'), { ...score('2', undefined, undefined, 'scheduled'), homeTeam: 'Feyenoord', awayTeam: 'AZ' }], follows);
  assert.deepEqual([...first.keys()], ['1']);
  assert.equal(x.detectScoreAlerts(null, first).length, 0);
  const goal = x.followedScores([score('1', 1, 0, 'live')], follows);
  assert.equal(x.detectScoreAlerts(states(first), goal)[0].homeScore, 1);
  assert.equal(x.detectScoreAlerts(states(first), goal, '1').length, 0);
  const end = x.followedScores([score('1', 1, 0, 'finished')], follows);
  assert.equal(x.detectScoreAlerts(states(goal), end)[0].final, true);
  assert.equal(x.detectScoreAlerts(states(end), end).length, 0);
  const basket = x.followedScores([score('1', 52, 48, 'live', 'Basketball')], follows);
  assert.equal(x.detectScoreAlerts(states(x.followedScores([score('1', 50, 48, 'live', 'Basketball')], follows)), basket).length, 0);
  assert.ok(x.anyActiveGame(goal, now));
});

test('any channel fails over to its other copies by guide id or name without quality tags', () => {
  const failed = { ...ch('a', 'NL: NPO 1 HD'), tvgId: 'npo1.nl' };
  const all = [failed, { ...ch('b', 'NPO 1 FHD'), tvgId: 'NPO1.nl' }, ch('c', 'NL: NPO 1 SD'), ch('d', 'NPO 2 HD'), ch('e', 'RTL 4')];
  const failover = x.channelCopyFailover(failed, all);
  assert.deepEqual(plain(failover.channels.map(c => c.id)), ['a', 'b', 'c']);
  assert.equal(failover.eventId, 'channel:a');
  assert.equal(x.nextFailover(failover, 'a').channel.id, 'b');
  assert.equal(x.channelCopyFailover(ch('e', 'RTL 4'), all), undefined);
});

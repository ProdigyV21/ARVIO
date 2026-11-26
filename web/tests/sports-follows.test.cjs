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
const f = load('./sportsFollows');
const now = Date.parse('2026-09-10T12:00:00Z');
const hour = 3600000;
const plain = value => JSON.parse(JSON.stringify(value));
const channel = { id: 'c1', name: 'ESPN 1', streamUrl: 'https://example.invalid/live' };
const event = (id, title, start, extra = {}) => ({ id, title, sportId: 'football', programme: { title, startUtcMillis: start, endUtcMillis: start + 2 * hour },
  channels: [channel], fixture: { id, status: 'scheduled', observedAt: now, broadcasters: [], league: extra.competition }, ...extra });
const ajax = { kind: 'team', name: 'AFC Ajax' };
const epl = { kind: 'league', name: 'English Premier League' };

test('follows match teams and leagues like the Android app', () => {
  assert.equal(f.followKey(ajax), 'team:ajax');
  assert.equal(f.followKey(epl), 'league:premier league');
  assert.ok(f.isFollowed(event('1', 'Football: PSV - Ajax', now), [ajax]));
  assert.ok(!f.isFollowed(event('2', 'Jong Ajax vs Den Bosch', now), [ajax]));
  assert.ok(f.isFollowed(event('3', 'Arsenal vs Chelsea', now, { competition: 'Premier League' }), [epl]));
  assert.deepEqual(plain(f.followOptions(event('4', 'Ajax vs PSV', now, { competition: 'Eredivisie' })).map(f.followKey)), ['team:ajax', 'team:psv', 'league:eredivisie']);
  const toggled = f.toggleFollow([], ajax);
  assert.equal(toggled.length, 1);
  assert.equal(f.toggleFollow(toggled, { kind: 'team', name: 'Ajax' }).length, 0);
  assert.equal(f.sanitizeFollows([ajax, ajax, { kind: 'x', name: 'y' }, null]).length, 1);
});

test('my sports lists live first and skips finished games', () => {
  const live = event('live', 'PSV vs Ajax', now - hour);
  const later = event('later', 'Ajax vs Feyenoord', now + 5 * hour);
  const done = event('done', 'Ajax vs AZ', now - hour, { fixture: { id: 'done', status: 'finished', observedAt: now, broadcasters: [] } });
  assert.deepEqual(plain(f.mySportsEvents([later, done, live], [ajax], now).map(e => e.id)), ['live', 'later']);
});

test('day view merges schedule-only fixtures, finished ones included', () => {
  const start = now + 24 * hour;
  const day = f.localDateKey(start);
  const fixture = { id: '9', league: 'Eredivisie', status: 'finished', observedAt: now, homeScore: 2, awayScore: 1, broadcasters: [] };
  const events = f.sportsEventsForDay([event('sportsdb:1', 'Ajax vs PSV', start)],
    [{ title: 'Ajax vs PSV', genres: ['Soccer'], startsAt: start, background: '', fixture: { ...fixture, id: '1' } },
     { title: 'AZ vs Twente', genres: ['Soccer'], startsAt: start + hour, background: '', fixture }], day);
  assert.deepEqual(plain(events.map(e => e.id)), ['sportsdb:1', 'sportsdb:9']);
  const rows = f.sportsDayRows(events, [ajax], now);
  assert.equal(rows[0].id, f.SPORTS_ROW_MINE);
  assert.ok(rows.some(r => r.id === 'day-football' && r.events.length === 2));
  assert.equal(f.utcDaysFor(Date.UTC(2026, 10, 20)).length >= 1, true);
});

test('auto reminders for followed teams, turned off for good, due a minute before', () => {
  const game = event('g', 'Ajax vs PSV', now + 3 * hour);
  const league = event('l', 'Arsenal vs Chelsea', now + 3 * hour, { competition: 'Premier League' });
  const reminders = f.withAutoReminders([], [game, league], [ajax, epl], now);
  assert.deepEqual(plain(reminders.map(r => r.eventId)), ['g']);
  assert.equal(f.withAutoReminders(reminders, [game], [ajax], now), reminders);
  const off = f.withReminder(reminders, game, false);
  assert.ok(!f.isReminded(f.withAutoReminders(off, [game], [ajax], now), 'g'));
  assert.equal(f.dueReminder(reminders, now, new Set()), undefined);
  assert.equal(f.dueReminder(reminders, game.programme.startUtcMillis - 30000, new Set())?.eventId, 'g');
  assert.equal(f.dueReminder(reminders, game.programme.startUtcMillis - 30000, new Set(['g'])), undefined);
  assert.equal(f.nextReminderAt(reminders, now, new Set()), game.programme.startUtcMillis - 60000);
});

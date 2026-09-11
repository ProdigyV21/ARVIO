const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');
const code = ts.transpileModule(fs.readFileSync(require.resolve('../lib/sportsGuide.ts'), 'utf8'), {
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 }
}).outputText;
const sandbox = { exports: {}, Date, Map, Set, URL };
vm.runInNewContext(code, sandbox);
const { buildSportsGuideEvents, sportsGuideRows, sportsDayIncludes, guideSports, isOnAir } = sandbox.exports;
const now = Date.parse('2026-09-09T18:00:00Z');
const a = { id: 'a:1', name: 'Sports', group: 'Football', streamUrl: 'https://example.invalid/a' };
const b = { ...a, id: 'b:1' };
const p = { title: 'Football: North vs South', startUtcMillis: now - 60_000, endUtcMillis: now + 60_000 };
const slice = (p) => ({ now: p, next: p, upcoming: [p], recent: [] });

test('date filtering keeps local calendar boundaries without empty rows', () => {
  const clock = new Date(2026, 9, 25, 0, 30).getTime();
  const lateToday = new Date(2026, 9, 25, 23, 30).getTime();
  const tomorrow = new Date(2026, 9, 26, 0, 30).getTime();
  assert.equal(sportsDayIncludes(lateToday, clock, 'today'), true);
  assert.equal(sportsDayIncludes(tomorrow, clock, 'today'), false);
  assert.equal(sportsDayIncludes(tomorrow, clock, 'tomorrow'), true);
  const rows = sportsGuideRows([{ id: 'future', sportId: 'football', programme: { ...p, startUtcMillis: tomorrow, endUtcMillis: tomorrow + 60_000 }, channels: [a] }], clock, 'today');
  assert.equal(rows.length, 0);
});
test('providers remain separate sources and repeated now/next is deduplicated', () => {
  const events = buildSportsGuideEvents([a, b], { [a.id]: slice(p), [b.id]: slice(p) }, now);
  assert.equal(events.length, 1);
  assert.equal(events[0].channels.length, 2);
});
test('hidden channel cannot leak from a cached schedule', () => {
  const events = buildSportsGuideEvents([a], { [a.id]: slice(p), [b.id]: slice(p) }, now);
  assert.equal(events[0].channels.length, 1);
});
test('replays, invalid and expired intervals are excluded', () => {
  for (const programme of [{ ...p, title: 'Football highlights' }, { ...p, title: 'Football replay' }, { ...p, endUtcMillis: now }, { ...p, startUtcMillis: NaN }]) {
    assert.equal(buildSportsGuideEvents([a], { [a.id]: slice(programme) }, now).length, 0);
  }
});
test('different broadcasts of similar names are not silently merged', () => {
  assert.equal(buildSportsGuideEvents([a, b], { [a.id]: slice(p), [b.id]: slice({ ...p, startUtcMillis: now + 120_000, endUtcMillis: now + 240_000 }) }, now).length, 2);
});
test('provider padding matches but each source keeps its actual start time', () => {
  const first = { ...p, title: 'Premier League: North versus South', endUtcMillis: now + 7_200_000 };
  const second = { ...first, title: 'LIVE: South v North [HD]', startUtcMillis: now + 60_000 };
  const events = buildSportsGuideEvents([a, b], { [a.id]: slice(first), [b.id]: slice(second) }, now);
  assert.equal(events.length, 1);
  assert.equal(events[0].competition, 'Premier League');
  assert.equal(sandbox.exports.availableEventChannels(events[0], now).length, 1);
  assert.equal(sandbox.exports.availableEventChannels(events[0], now + 120_000).length, 2);
});
test('general channels use programme category and safe artwork without addon', () => {
  const channel = { ...a, name: 'National One', group: 'General' };
  const programme = { ...p, title: 'North vs South', category: 'Football', artworkUrl: 'https://example.com/event.webp' };
  const event = buildSportsGuideEvents([channel], { [a.id]: slice(programme) }, now)[0];
  assert.equal(event.artwork, programme.artworkUrl);
  for (const title of ['North vs South cancelled', 'North vs South postponed']) assert.equal(buildSportsGuideEvents([channel], { [a.id]: slice({ ...programme, title }) }, now).length, 0);
  assert.equal(buildSportsGuideEvents([a, b], { [a.id]: slice(p), [b.id]: slice({ ...p, title: 'North Women vs South Women' }) }, now).length, 2);
});
test('American football and football remain separate', () => {
  assert.equal(guideSports.find((s) => s.pattern.test('American football NFL')).id, 'american-football');
});
test('event ends at exclusive interval boundary', () => {
  assert.equal(isOnAir({ programme: p }, p.endUtcMillis), false);
});
test('featured is capped, complete sport row retains all events', () => {
  const programmes = Array.from({ length: 12 }, (_, i) => ({ ...p, title: `Football: Team ${i} vs Other` }));
  const events = buildSportsGuideEvents([a], { [a.id]: { upcoming: programmes, recent: [] } }, now);
  const rows = sportsGuideRows(events, now);
  const featured = rows.find((row) => row.id === 'featured').events;
  const football = rows.find((row) => row.id === 'football').events;
  assert.equal(featured.length, 8);
  assert.equal(football.length, 12);
  assert.equal(rows.some(row => row.id === 'more'), false);
});

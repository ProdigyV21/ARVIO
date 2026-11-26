const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const fixes = load('lib/iptvGuideFixes.ts');
const hour = 3_600_000;
const program = (title, start) => ({ title, startUtcMillis: start, endUtcMillis: start + hour });
const entry = (now, next) => ({ now, next, later: undefined, upcoming: next ? [next] : [], recent: [] });

test('a channel shows the guide of the channel the user picked', () => {
  const guide = { 'list_1:a': entry(program('Wrong', 10 * hour)), 'list_2:b': entry(program('News', 10 * hour), program('Film', 11 * hour)) };
  const shown = fixes.applyGuideFixes({ sources: { 'list_1:a': 'list_2:b' }, offsets: {} }, guide, 10.5 * hour);
  assert.equal(shown['list_1:a'].now.title, 'News');
  assert.equal(shown['list_2:b'].now.title, 'News');
});

test('no guide hides a wrong guide, and a missing source leaves nothing stale', () => {
  const guide = { 'list_1:a': entry(program('Wrong', 10 * hour)) };
  assert.equal(fixes.applyGuideFixes({ sources: { 'list_1:a': 'none' }, offsets: {} }, guide)['list_1:a'], undefined);
  assert.equal(fixes.applyGuideFixes({ sources: { 'list_1:a': 'list_2:b' }, offsets: {} }, guide)['list_1:a'], undefined);
  assert.equal(fixes.guideChannelOf({ sources: { 'list_1:a': 'none' }, offsets: {} }, 'list_1:a'), null);
});

test('a time correction moves one playlist and works out now/next again', () => {
  const guide = { 'list_2:b': entry(program('News', 10 * hour), program('Film', 11 * hour)), 'list_1:c': entry(program('Other', 10 * hour)) };
  const shown = fixes.applyGuideFixes({ sources: {}, offsets: { list_2: 60 } }, guide, 11.5 * hour);
  assert.equal(shown['list_2:b'].now.title, 'News');
  assert.equal(shown['list_2:b'].now.startUtcMillis, 11 * hour);
  assert.equal(shown['list_2:b'].next.title, 'Film');
  assert.equal(shown['list_1:c'], guide['list_1:c']);
});

test('nothing fixed returns the same guide object', () => {
  const guide = {};
  assert.equal(fixes.applyGuideFixes({ sources: {}, offsets: {} }, guide), guide);
});

test('offsets and sources normalise like the Android app', () => {
  assert.deepEqual({ ...fixes.normalizeGuideOffsets({ a: 61, b: '-89', c: 2000, d: 0, e: 'x' }) }, { a: 60, b: -90, c: 720 });
  assert.deepEqual({ ...fixes.normalizeGuideSources({ a: 'b', c: 'c', d: ' ', '': 'e' }) }, { a: 'b' });
  assert.equal(fixes.guidePlaylistIdOf('stalker:stalker2:77'), 'stalker2');
  assert.equal(fixes.formatGuideOffset(90), '+1:30');
  assert.equal(fixes.formatGuideOffset(-30), '−0:30');
});

test('suggestions find the same channel under other tags, never another number', () => {
  const names = ['NPO 2', 'NPO 1 4K', 'Discovery', 'NPO 1', 'NPO 1 Extra'];
  const prepared = fixes.prepareGuideCandidates(names, (name) => name);
  const ranked = fixes.rankGuideCandidates('NL | NPO 1 HD', '', prepared, (name) => name === 'NPO 1');
  assert.deepEqual([...ranked.slice(0, 2)], ['NPO 1', 'NPO 1 4K']);
  assert.ok(!ranked.includes('NPO 2'));
  assert.deepEqual([...fixes.rankGuideCandidates('x', 'sky sport', fixes.prepareGuideCandidates(['Sky Sport 1', 'Sky Cinema', 'Ziggo Sport'], (n) => n), () => false)], ['Sky Sport 1']);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const budget = load('lib/connectionBudget.ts');
const memory = () => { const data = new Map(); return { getItem: key => data.get(key) ?? null, setItem: (key, value) => data.set(key, String(value)) }; };
const GB = 1e9;

test('no struggle, no limit: nothing is held back', () => {
  const store = memory();
  budget.recordPlaybackSample(70, 600, 0, store, 1000);
  assert.equal(budget.sustainableMbps(budget.readSamples(store, 2000)), null);
  assert.equal(budget.exceedsConnection(60 * GB, 120, null), false);
});

test('a file that kept stalling sets the limit just below its bitrate', () => {
  const store = memory();
  budget.recordPlaybackSample(70, 300, 3, store, 1000);
  const limit = budget.sustainableMbps(budget.readSamples(store, 2000));
  assert.ok(limit > 59 && limit < 60, `limit ${limit}`);
  // 60 GB over 2 hours is ~67 Mbit/s: too much. 15 GB is ~17 Mbit/s: fine.
  assert.equal(budget.exceedsConnection(60 * GB, 120, limit), true);
  assert.equal(budget.exceedsConnection(15 * GB, 120, limit), false);
  assert.equal(budget.exceedsConnection(60 * GB, undefined, limit), false, 'unknown runtime decides nothing');
});

test('a later clean session at a higher bitrate lifts the limit; short sessions prove nothing', () => {
  const store = memory();
  budget.recordPlaybackSample(70, 300, 3, store, 1000);
  budget.recordPlaybackSample(90, 60, 0, store, 2000);
  assert.equal(budget.readSamples(store, 3000).length, 1, 'one minute without stalls is not evidence');
  budget.recordPlaybackSample(80, 900, 0, store, 4000);
  assert.equal(budget.sustainableMbps(budget.readSamples(store, 5000)), 80);
});

test('old evidence expires and runtimes parse from the card text', () => {
  const store = memory();
  budget.recordPlaybackSample(70, 300, 3, store, 0);
  assert.equal(budget.readSamples(store, 22 * 86400000).length, 0);
  budget.setRankingRuntime('1h 52m', 'movie');
  assert.equal(budget.rankingRuntimeMinutes(), 112);
  budget.setRankingRuntime('', 'tv');
  assert.equal(budget.rankingRuntimeMinutes(), 45);
  assert.equal(budget.fileMbps(15 * GB, 7200).toFixed(1), '16.7');
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const stats = load('lib/playbackStartStats.ts');
const memory = () => { const data = new Map(); return { getItem: key => data.get(key) ?? null, setItem: (key, value) => data.set(key, String(value)) }; };

test('startup times fall into four buckets', () => {
  assert.equal(stats.startupBucket(900), 'under_2s');
  assert.equal(stats.startupBucket(2000), '2_5s');
  assert.equal(stats.startupBucket(7000), '5_10s');
  assert.equal(stats.startupBucket(45000), 'over_10s');
});

test('the tally rides along once and keeps what was counted meanwhile', () => {
  const store = memory();
  stats.recordStart('remux', 3000, store);
  stats.recordStart('direct', 800, store);
  stats.recordStall(store);
  const sent = stats.tallyMetadata(stats.readTally(store));
  assert.deepEqual({ ...sent }, { starts_direct: 1, starts_remux: 1, start_under_2s: 1, start_2_5s: 1, stalls: 1 });
  stats.recordStart('hls', 12000, store);
  stats.subtractReported(sent, store);
  assert.deepEqual({ ...stats.tallyMetadata(stats.readTally(store)) }, { starts_hls: 1, start_over_10s: 1 });
});

test('a damaged or oversized tally is bounded', () => {
  const store = memory();
  store.setItem('arvio.playback.startTally', JSON.stringify({ stalls: 1e9, starts_direct: -4, starts_remux: 'x' }));
  const tally = stats.readTally(store);
  assert.equal(tally.stalls, 500);
  assert.equal(tally.starts_direct, 0);
  assert.equal(tally.starts_remux, 0);
  store.setItem('arvio.playback.startTally', '{not json');
  assert.equal(stats.readTally(store).stalls, 0);
});

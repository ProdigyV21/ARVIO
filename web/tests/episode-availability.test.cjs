const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { sourceOf, storeSource } = require('./sources.cjs');
const episode = (number, extra = {}) => ({
  id: 123, title: 'Chad Powers', mediaType: 'tv', seasonNumber: 1,
  episodeNumber: number, badge: 'Up Next', ...extra,
});

test('nonexistent episode seven and future episodes never enter the rail', async () => {
  const { createEpisodeValidator } = load('lib/episodeAvailability.ts');
  const validate = createEpisodeValidator(async (item) => item.episodeNumber === 7
    ? { exists: false } : { exists: true, airDate: '2999-01-01T00:00:00Z' });
  const rejected = new Set();
  assert.equal((await validate([episode(7), episode(8)], rejected)).length, 0);
  assert.equal(rejected.size, 2);
});

test('real aired episodes and movies remain without inventing a replacement', async () => {
  const { createEpisodeValidator } = load('lib/episodeAvailability.ts');
  const validate = createEpisodeValidator(async () => ({ exists: true, airDate: '2020-01-01' }));
  const rows = [episode(6), { id: 2, mediaType: 'movie' }];
  assert.deepEqual(Array.from(await validate(rows)), rows);
});

test('Trakt and TMDB numbering remain independent', async () => {
  const { createEpisodeValidator } = load('lib/episodeAvailability.ts');
  let calls = 0;
  const validate = createEpisodeValidator(async (item) => {
    calls++;
    return item.traktId ? { exists: true, airDate: '2020-01-01' } : { exists: false };
  });
  const rows = await validate([episode(1173, { traktId: 22, seasonNumber: 23 }), episode(1173, { seasonNumber: 23 })]);
  assert.equal(rows.length, 1);
  assert.equal(rows[0].episodeNumber, 1173);
  assert.equal(calls, 2);
});

test('an outage preserves real paused playback but does not guess Up Next', async () => {
  const { createEpisodeValidator } = load('lib/episodeAvailability.ts');
  let failures = 0;
  const validate = createEpisodeValidator(async () => { throw new Error('429'); });
  const rejected = new Set();
  const rows = await validate([episode(3), episode(4, { badge: null, progress: 30 })], rejected, () => failures++);
  assert.equal(rows.length, 1);
  assert.equal(rows[0].episodeNumber, 4);
  assert.equal(rejected.size, 0);
  assert.equal(failures, 2);
});

test('unknown air dates and invalid numbers cannot create Up Next', async () => {
  const { createEpisodeValidator } = load('lib/episodeAvailability.ts');
  const validate = createEpisodeValidator(async () => ({ exists: true, airDate: null }));
  assert.equal((await validate([episode(0), episode(-1), episode(2.5), episode(7)])).length, 0);
});

test('cached metadata rechecks airing time and validates beyond the artwork limit', async () => {
  let now = Date.parse('2026-01-01T00:00:00Z');
  class Clock extends Date { static now() { return now; } }
  const { createEpisodeValidator } = load('lib/episodeAvailability.ts', {}, { Date: Clock });
  let calls = 0;
  const validate = createEpisodeValidator(async () => {
    calls++;
    return { exists: true, airDate: '2026-01-01T00:01:00Z' };
  });
  const rows = Array.from({ length: 60 }, (_, index) => episode(index + 1));
  assert.equal((await validate(rows)).length, 0);
  now += 60_000;
  assert.equal((await validate(rows)).length, 60);
  assert.equal(calls, 60);
});

test('store validates playback before it can suppress a genuine next episode and expires old rails', () => {
  const source = storeSource();
  assert.match(source, /traktPlaybackCw = await validateEpisodes/);
  assert.match(source, /upNextRows = await validateEpisodes/);
  assert.match(source, /arvio\.web\.cw\.v4/);
  assert.match(source, /!rejectedEpisodes\.has\(episodeAvailabilityKey\(item\)\)/);
});

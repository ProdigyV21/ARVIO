const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const calendar = load('lib/calendar.ts');
const movie = { id: 1, mediaType: 'movie', title: 'Saved movie' };
const show = { id: 2, mediaType: 'tv', title: 'Saved series' };
const options = { start: '2026-10-01', end: '2026-10-31', language: 'en', region: 'NL' };
const json = value => JSON.parse(JSON.stringify(value));
const deferred = () => { let resolve; const promise = new Promise(done => { resolve = done; }); return { promise, resolve }; };
function loader(tmdb = async () => ({}), resolveTmdbId = async () => null) {
  return load('lib/calendarLoader.ts', { './calendar': calendar, './tmdb': { tmdb, resolveTmdbId, mapTmdbItem: (details, mediaType) => ({ id: details.id, title: details.title || details.name, mediaType }) } });
}

test('month grid starts Monday, contains today and has five or six full weeks across leap years', () => {
  for (const [year, month, size] of [[2026, 9, 35], [2026, 2, 42], [2024, 1, 35]]) {
    const days = calendar.calendarMonthDays(new Date(year, month, 1, 12));
    assert.equal(days.length, size);
    assert.equal(days[0].getDay(), 1);
    assert.equal(days.at(-1).getDay(), 0);
  }
  assert.equal(calendar.parseCalendarDate('2026-02-30'), null);
  assert.ok(calendar.parseCalendarDate('2024-02-29'));
});

test('source merge is by media type and TMDB ID, retains memberships and rejects local placeholder IDs', () => {
  const rows = calendar.mergeCalendarTitles([{ source: 'arvio', items: [movie, show, { ...movie, id: -99 }] }, { source: 'trakt', items: [{ ...movie, traktId: 9 }, { ...show, id: 1 }] }]);
  assert.equal(rows.length, 3);
  assert.deepEqual(json(rows[0].sources), ['arvio', 'trakt']);
  assert.equal(rows[0].item.traktId, 9);
  assert.equal(rows[2].item.mediaType, 'tv');
});

test('month navigation preserves day number and clamps across short months and year boundaries', () => {
  for (const [from, step, expected] of [['2026-10-16', 1, '2026-11-16'], ['2026-10-31', 1, '2026-11-30'], ['2026-03-31', -1, '2026-02-28'], ['2024-01-31', 1, '2024-02-29'], ['2026-12-31', 1, '2027-01-31']]) {
    assert.equal(calendar.calendarDate(calendar.shiftCalendarMonth(calendar.parseCalendarDate(from), step)), expected);
  }
});

test('empty-source counts distinguish an empty list from failed or unresolved metadata', async () => {
  const module = loader(async () => ({ id: 1, title: movie.title, release_date: '2026-10-10' }));
  const result = await module.loadCalendar({ ...options, sources: [
    { source: 'arvio', read: async () => [] },
    { source: 'trakt', read: async () => [movie, { ...show, id: -22 }] },
    { source: 'simkl', read: async () => { throw Error('offline'); } }
  ] });
  assert.deepEqual(json(result.sourceTitleCounts), { arvio: 0, trakt: 2 });
  assert.deepEqual(json(result.failedSources), ['simkl']);
  assert.equal(result.failedTitles, 1);
  assert.equal(result.releases.length, 1);
});

test('movie release types dedupe theatrical/limited, keep cinema/digital distinct and never invent midnight times', () => {
  const module = loader();
  const rows = module.movieCalendarReleases({ item: movie, sources: ['arvio'] }, { id: 1, release_dates: { results: [{ iso_3166_1: 'NL', release_dates: [{ type: 2, release_date: '2026-10-16T00:00:00.000Z' }, { type: 3, release_date: '2026-10-16T00:00:00.000Z' }, { type: 4, release_date: '2026-10-16T00:00:00.000Z' }, { type: 5, release_date: '2026-02-30' }] }] } }, 'NL');
  assert.deepEqual(json(rows.map(row => row.kind)), ['cinema', 'digital']);
  assert.ok(rows.every(row => row.date === '2026-10-16' && !row.timestamp && calendar.calendarTime(row) === 'Time TBA'));
});

test('release fallback uses labeled US metadata or untyped primary date rather than an arbitrary country', () => {
  const module = loader();
  const rows = module.movieCalendarReleases({ item: movie, sources: ['arvio'] }, { id: 1, release_date: '2026-10-12', release_dates: { results: [{ iso_3166_1: 'DE', release_dates: [{ type: 3, release_date: '2026-10-10' }] }] } }, 'NL');
  assert.equal(rows[0].date, '2026-10-12'); assert.equal(rows[0].kind, 'release'); assert.equal(rows[0].region, undefined);
});

test('authoritative timestamps use local date through midnight and DST, date-only metadata has no time', () => {
  const previous = process.env.TZ;
  process.env.TZ = 'Europe/Amsterdam';
  try {
    assert.equal(calendar.authoritativeEpisodeTime('2026-10-15T23:30:00Z').date, '2026-10-16');
    assert.equal(calendar.calendarTime({ timestamp: '2026-10-15T23:30:00Z' }, 'en'), '01:30');
    assert.equal(calendar.calendarTime({ timestamp: '2026-10-25T02:30:00Z' }, 'en'), '03:30');
    assert.equal(calendar.authoritativeEpisodeTime('2026-10-16'), null);
    assert.equal(calendar.authoritativeEpisodeTime('2026-10-16T00:00:00'), null);
  } finally { if (previous === undefined) delete process.env.TZ; else process.env.TZ = previous; }
});

test('ARVIO-only account loads releases without any tracker reader, preserving typed movie releases', async () => {
  const calls = [];
  const module = loader(async path => { calls.push(path); return { id: 1, title: movie.title, release_dates: { results: [{ iso_3166_1: 'NL', release_dates: [{ type: 3, release_date: '2026-10-16T00:00:00Z' }] }] } }; });
  const result = await module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [movie] }] });
  assert.deepEqual(calls, ['movie/1']);
  assert.equal(result.releases.length, 1); assert.equal(result.failedTitles, 0);
  assert.deepEqual(json(result.releases[0].sources), ['arvio']);
});

test('source and season failures retain other releases and report partial data', async () => {
  const module = loader(async path => {
    if (path === 'tv/2') return { id: 2, name: show.title, seasons: [{ id: 5, season_number: 1, air_date: '2026-09-01' }], next_episode_to_air: { season_number: 1, episode_number: 8, air_date: '2026-10-16' } };
    throw new Error('offline');
  });
  const result = await module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }, { source: 'trakt', read: async () => { throw new Error('offline'); } }] });
  assert.equal(result.releases.length, 1); assert.equal(result.failedTitles, 1);
  assert.deepEqual(json(result.failedSources), ['trakt']);
  assert.equal(calendar.calendarTime(result.releases[0]), 'Time TBA');
});

test('overlapping seasons, specials and unknown dates are not silently dropped; ended series avoid needless requests', async () => {
  const module = loader(async path => ({ id: 2, name: show.title, status: 'Ended', last_episode_to_air: { season_number: 1, episode_number: 5, air_date: '2025-10-10' }, seasons: [{ id: 1, season_number: 1 }] }));
  const candidates = module.calendarSeasonCandidates([{ id: 1, season_number: 0 }, { id: 2, season_number: 1, air_date: '2024-01-01' }, { id: 3, season_number: 2, air_date: '2025-01-01' }, { id: 4, season_number: 3, air_date: '2027-01-01' }], options.start, options.end);
  assert.deepEqual(json(candidates.map(row => row.season_number)), [2, 1, 0]);
  const result = await module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }] });
  assert.equal(result.releases.length, 0); assert.equal(result.failedTitles, 0);
});

test('identity resolution does not mutate shared watchlists and can merge external memberships', async () => {
  const unknown = { ...movie, id: -12, imdbId: 'tt0123456' };
  const module = loader(async () => ({ id: 1, title: movie.title, release_date: '2026-10-10' }), async () => 1);
  const result = await module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [movie] }, { source: 'mdblist', read: async () => [unknown] }] });
  assert.equal(unknown.id, -12); assert.equal(result.titleCount, 1); assert.equal(result.releases.length, 1);
  assert.deepEqual(json(result.releases[0].sources), ['arvio', 'mdblist']);
});

test('completed titles render before a slow title finishes and aborted months stop requesting episode times', async () => {
  let finishSlow;
  const slow = new Promise(resolve => { finishSlow = resolve; });
  let firstPaint;
  const painted = new Promise(resolve => { firstPaint = resolve; });
  const module = loader(async path => path === 'movie/3' ? slow : { id: 1, title: movie.title, release_date: '2026-10-10' });
  const request = module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [movie, { ...movie, id: 3 }] }], onProgress: result => { if (result.releases.length === 1) firstPaint(); } });
  await painted;
  finishSlow({ id: 3, title: 'Slow movie', release_date: '2026-10-12' });
  assert.equal((await request).releases.length, 2);
  const controller = new AbortController();
  const tv = loader(async path => path === 'tv/2' ? { id: 2, name: show.title, seasons: [{ id: 1, season_number: 1 }] } : { episodes: [1, 2, 3].map(episode_number => ({ season_number: 1, episode_number, air_date: '2026-10-16' })) });
  let timeCalls = 0;
  await tv.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }], signal: controller.signal, episodeTime: async () => { timeCalls++; controller.abort(); return null; } });
  assert.equal(timeCalls, 1);
});

test('public Trakt episode timestamps work without a connected account or OAuth calls', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: { traktClientId: 'public-app-id' } }, './http': {} });
  const client = new TraktClient();
  const paths = [];
  client.trakt = async path => { paths.push(path); return path.startsWith('/search/') ? [{ show: { ids: { tmdb: 2, trakt: 44 } } }] : [{ season: 1, number: 8, first_aired: '2026-10-16T19:00:00Z' }]; };
  const episodes = await client.calendarSeason(2, 1);
  assert.equal(client.isConnected, false);
  assert.deepEqual(paths, ['/search/tmdb/2?type=show', '/shows/44/seasons/1?extended=full']);
  assert.equal(episodes[0].first_aired, '2026-10-16T19:00:00Z');
});

test('ARVIO dates publish before a delayed source, then merge late provenance without duplicate metadata or mutating snapshots', async () => {
  const tracker = deferred(), painted = deferred();
  const calls = [];
  const module = loader(async path => { calls.push(path); return { id: 1, title: movie.title, release_date: '2026-10-16' }; });
  const request = module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [movie] }, { source: 'trakt', read: () => tracker.promise }],
    onProgress: value => { if (value.releases.length && !value.pendingSources.includes('arvio')) painted.resolve(value); } });
  const initial = await painted.promise;
  assert.deepEqual(json(initial.pendingSources), ['trakt']);
  assert.deepEqual(json(initial.failedSources), []);
  assert.equal(initial.sourceTitleCounts.trakt, undefined, 'Pending is not an empty source');
  tracker.resolve([{ ...movie, traktId: 123 }]);
  const final = await request;
  assert.deepEqual(calls, ['movie/1']);
  assert.equal(final.releases.length, 1);
  assert.deepEqual(json(final.releases[0].sources), ['arvio', 'trakt']);
  assert.deepEqual(json(initial.releases[0].sources), ['arvio'], 'Published snapshots remain immutable');
  assert.deepEqual(json(final.pendingSources), []);
});

test('known episode dates publish before a slow historical season and exact times enrich independently', async () => {
  const season = deferred(), time = deferred(), datePainted = deferred(), schedulesDone = deferred();
  const module = loader(async path => path === 'tv/2' ? { id: 2, name: show.title, seasons: [{ id: 1, season_number: 1 }], next_episode_to_air: { season_number: 1, episode_number: 8, air_date: '2026-10-16' } } : season.promise);
  const request = module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }], episodeTime: () => time.promise,
    onProgress: value => { if (value.releases.length) { datePainted.resolve(value); if (!value.pendingSources.length) schedulesDone.resolve(value); } } });
  assert.equal((await datePainted.promise).releases[0].timestamp, undefined);
  season.resolve({ episodes: [] });
  assert.equal((await schedulesDone.promise).releases[0].date, '2026-10-16');
  time.resolve('2026-10-16T19:00:00Z');
  const result = await request;
  assert.equal(result.releases.length, 1); assert.equal(result.releases[0].timestamp, '2026-10-16T19:00:00Z');
  assert.equal(result.failedTitles, 0);
});

test('timeouts bound stalled providers and optional timestamps while keeping known dates', async () => {
  const never = new Promise(() => {});
  const module = loader(async path => path === 'tv/2' ? { id: 2, name: show.title, seasons: [{ id: 1, season_number: 1 }], next_episode_to_air: { season_number: 1, episode_number: 8, air_date: '2026-10-16' } } : never);
  const result = await module.loadCalendar({ ...options, timeoutMs: 25, sources: [{ source: 'arvio', read: async () => [show] }, { source: 'trakt', read: () => never }], episodeTime: () => never });
  assert.deepEqual(json(result.failedSources), ['trakt']);
  assert.deepEqual(json(result.pendingSources), []);
  assert.equal(result.failedTitles, 1, 'Unresponsive season is a partial metadata failure');
  assert.equal(result.releases[0].date, '2026-10-16');
  assert.equal(result.releases[0].timestamp, undefined);
});

test('cancellation closes pending requests promptly, skips queued work and never publishes stale source results', async () => {
  const controller = new AbortController(), started = deferred(), metadata = deferred(), tracker = deferred();
  let calls = 0, paints = 0;
  const module = loader(async () => { calls++; started.resolve(); return metadata.promise; });
  const request = module.loadCalendar({ ...options, signal: controller.signal,
    sources: [{ source: 'arvio', read: async () => Array.from({ length: 8 }, (_, i) => ({ ...movie, id: i + 1 })) }, { source: 'trakt', read: () => tracker.promise }], onProgress: () => { paints++; } });
  await started.promise; controller.abort(); const atAbort = paints;
  await request;
  assert.ok(calls <= 4, 'Metadata concurrency stays bounded');
  metadata.resolve({ id: 1, title: movie.title, release_date: '2026-10-16' }); tracker.resolve([movie]);
  await Promise.resolve(); await Promise.resolve();
  assert.equal(paints, atAbort);
});

test('large episode batches coalesce progress and expired enrichment queues skip network calls', async () => {
  const episodes = Array.from({ length: 80 }, (_, index) => ({ season_number: index + 1, episode_number: 1, air_date: '2026-10-16' }));
  const module = loader(async path => path === 'tv/2' ? { id: 2, name: show.title, seasons: [{ id: 1, season_number: 1 }] } : { episodes });
  const snapshots = []; let timeCalls = 0;
  const result = await module.loadCalendar({ ...options, timeoutMs: 500, enrichmentTimeoutMs: 25,
    sources: [{ source: 'arvio', read: async () => [show] }], episodeTime: async () => { timeCalls++; return new Promise(() => {}); }, onProgress: value => snapshots.push(value) });
  assert.equal(result.releases.length, 80, 'All confirmed dates remain available');
  assert.equal(timeCalls, 4, 'Expired queue entries do not start new network requests');
  assert.ok(snapshots.length <= 4, 'Progress snapshots are coalesced instead of copied/sorted per episode');
  assert.equal(snapshots.at(-1).releases.length, 80, 'Final snapshot is immediate and complete');
});

test('historical season walks cannot block the first details response of later watchlist titles', async () => {
  const oldSeasons = deferred(), painted = deferred();
  const calls = [];
  const module = loader(async path => {
    calls.push(path);
    if (path.includes('/season/')) return oldSeasons.promise;
    if (path === 'movie/5') return { id: 5, title: 'Upcoming movie', release_date: '2026-10-16' };
    return { id: Number(path.split('/')[1]), name: 'Long-running series', seasons: [{ id: 1, season_number: 1 }] };
  });
  const request = module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [
    ...Array.from({ length: 4 }, (_, index) => ({ ...show, id: index + 1 })), { ...movie, id: 5 }
  ] }], onProgress: result => { if (result.releases.some(row => row.item.id === 5)) painted.resolve(result); } });
  const first = await Promise.race([painted.promise, new Promise((_, reject) => setTimeout(() => reject(Error('First paint blocked by historical seasons')), 1000))]);
  assert.equal(first.releases[0].item.id, 5);
  assert.ok(calls.indexOf('movie/5') < calls.indexOf('tv/1/season/1'));
  oldSeasons.resolve({ episodes: [] });
  assert.equal((await request).releases.length, 1);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const calendar = load('lib/calendar.ts');
const movie = { id: 1, mediaType: 'movie', title: 'Saved movie' };
const show = { id: 2, mediaType: 'tv', title: 'Saved series' };
const options = { start: '2026-10-01', end: '2026-10-31', language: 'en', region: 'NL' };
const json = value => JSON.parse(JSON.stringify(value));
const deferred = () => { let resolve; const promise = new Promise(done => { resolve = done; }); return { promise, resolve }; };
function loader(tmdb = async () => ({}), resolveTmdbId = async () => null, globals = {}) {
  return load('lib/calendarLoader.ts', { './calendar': calendar, './calendarTitleCache': load('lib/calendarTitleCache.ts', { './storage': storage() }), './tmdb': { tmdb, resolveTmdbId, mapTmdbItem: (details, mediaType) => ({ id: details.id, title: details.title || details.name, mediaType }) } }, globals);
}

test('season time enrichment keeps every verified episode after waiting for other seasons', async () => {
  const calls = [];
  const module = loader(async path => path.includes('/season/') ? {
    episodes: Array.from({ length: 20 }, (_, i) => ({ season_number: 1, episode_number: i + 1, air_date: '2026-10-16' }))
  } : { id: Number(path.split('/')[1]), name: show.title, seasons: [{ season_number: 1 }] });
  const result = await module.loadCalendar({ ...options, enrichmentTimeoutMs: 50,
    sources: [{ source: 'arvio', read: async () => Array.from({ length: 12 }, (_, i) => ({ ...show, id: i + 1 })) }],
    episodeSeason: async (item, season) => {
      calls.push(`${item.id}:${season}`);
      await new Promise(resolve => setTimeout(resolve, 30));
      return Array.from({ length: 21 }, (_, i) => ({ season, number: i + 1, first_aired: '2026-10-16T23:30:00Z' }));
    }
  });
  assert.equal(calls.length, 12, 'Only one request per season, including work queued longer than the request budget');
  assert.equal(result.releases.length, 240, 'Time service cannot invent its extra episode 21');
  assert.ok(result.releases.every(row => row.timestamp === '2026-10-16T23:30:00Z'));
  assert.ok(result.releases.every(row => row.date === calendar.authoritativeEpisodeTime(row.timestamp).date));
});

test('one slow season does not suppress healthy queued season timestamps', async () => {
  const module = loader(async path => path.includes('/season/') ? {
    episodes: [{ season_number: 1, episode_number: 1, air_date: '2026-10-16' }]
  } : { id: Number(path.split('/')[1]), name: show.title, seasons: [{ season_number: 1 }] });
  let calls = 0;
  const result = await module.loadCalendar({ ...options, enrichmentTimeoutMs: 40,
    sources: [{ source: 'arvio', read: async () => Array.from({ length: 12 }, (_, i) => ({ ...show, id: i + 1 })) }],
    episodeSeason: async item => {
      calls++;
      if (item.id === 1) return new Promise(() => {});
      await new Promise(resolve => setTimeout(resolve, 15));
      return [{ season: 1, number: 1, first_aired: '2026-10-16T23:30:00Z' }];
    }
  });
  assert.equal(calls, 12);
  assert.equal(result.releases.filter(row => row.timestamp).length, 11);
});

test('calendar metadata uses a separate half-hour cache while ordinary details retain their cache', async () => {
  const calls = [];
  const route = load('app/api/tmdb/[...path]/route.ts', { 'next/server': { NextResponse: Response } }, {
    process: { env: { APP_ANON_KEY: 'fixture-public-key'.repeat(4), TMDB_API_KEY: 'fixture-key' } },
    fetch: async (url, init) => { calls.push({ url: new URL(url), init }); return new Response('{}'); }
  });
  const context = { params: Promise.resolve({ path: ['tv', '2', 'season', '1'] }) };
  const response = await route.GET(new Request('https://web.example/api/tmdb/tv/2/season/1?arvio_calendar=1&language=en'), context);
  assert.equal(calls[0].init.next.revalidate, 1800);
  assert.equal(calls[0].url.searchParams.get('cv'), 'calendar-3');
  assert.equal(calls[0].url.searchParams.has('arvio_calendar'), false);
  assert.equal(calls[0].url.searchParams.get('language'), 'en');
  assert.match(response.headers.get('netlify-cdn-cache-control'), /max-age=1800/);
  assert.doesNotMatch(response.headers.get('cache-control'), /stale-while-revalidate/);
  const ordinary = await route.GET(new Request('https://web.example/api/tmdb/tv/2/season/1?language=en'), context);
  assert.equal(calls[1].init.next.revalidate, 86400);
  assert.equal(calls[1].url.searchParams.get('cv'), '2');
  assert.match(ordinary.headers.get('cache-control'), /s-maxage=21600/);
});

test('season times apply to episodes verified later and an outage keeps dates without a long queue', async () => {
  const season = deferred(), timeDone = deferred();
  const module = loader(async path => path.includes('/season/') ? season.promise : {
    id: 2, name: show.title, seasons: [{ season_number: 1 }], next_episode_to_air: { season_number: 1, episode_number: 1, air_date: '2026-10-16' }
  });
  const request = module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }],
    episodeSeason: async () => { timeDone.resolve(); return [1, 2].map(number => ({ season: 1, number, first_aired: '2026-10-16T19:30:00Z' })); } });
  await timeDone.promise;
  season.resolve({ episodes: [1, 2].map(episode_number => ({ season_number: 1, episode_number, air_date: '2026-10-16' })) });
  const result = await request;
  assert.equal(result.releases.length, 2);
  assert.ok(result.releases.every(row => row.timestamp === '2026-10-16T19:30:00Z'));
  let calls = 0;
  const unavailable = loader(async path => path.includes('/season/') ? { episodes: [{ season_number: 1, episode_number: 1, air_date: '2026-10-16' }] }
    : { id: Number(path.split('/')[1]), name: show.title, seasons: [{ season_number: 1 }] });
  const fallback = await unavailable.loadCalendar({ ...options, enrichmentTimeoutMs: 10,
    sources: [{ source: 'arvio', read: async () => Array.from({ length: 12 }, (_, i) => ({ ...show, id: i + 1 })) }],
    episodeSeason: async () => { calls++; return new Promise(() => {}); } });
  assert.equal(fallback.releases.length, 12);
  assert.equal(fallback.failedTitles, 0);
  assert.equal(calls, 4, 'A timed-out service does not hold up every queued season');
});

test('verified show schedules survive raw season eviction without dropping adjacent month episodes', async () => {
  let calls = 0;
  const module = loader(async (path, params) => {
    calls++;
    if (path === 'tv/999') return { id: 999, name: 'Completed series', status: 'Ended', last_episode_to_air: { air_date: '2020-01-01' }, seasons: [{ season_number: 1 }] };
    if (params.append_to_response) return Object.fromEntries(params.append_to_response.split(',').map(key => [key, {
      season_number: Number(key.split('/')[1]), episodes: ['2026-10-16', '2026-11-16'].map((air_date, i) => ({ season_number: Number(key.split('/')[1]), episode_number: i + 1, air_date }))
    }]));
    return { id: Number(path.split('/')[1]), name: show.title, seasons: Array.from({ length: 24 }, (_, i) => ({ season_number: i + 1 })) };
  });
  const sources = [{ source: 'arvio', read: async () => [{ ...show, id: 999 }, ...Array.from({ length: 90 }, (_, i) => ({ ...show, id: i + 1 }))] }];
  const october = await module.loadCalendar({ ...options, sources });
  const before = calls;
  const november = await module.loadCalendar({ ...options, start: '2026-11-01', end: '2026-11-30', sources });
  assert.equal(october.releases.length, 2160);
  assert.equal(november.releases.length, 2160);
  assert.equal(november.failedTitles, 0);
  assert.equal(calls, before, 'Whole schedules and completed-show summaries survive raw season eviction');
});

test('future seasons of ended anime remain discoverable and incomplete schedules stay retryable', async () => {
  let offline = true;
  const calls = [];
  const module = loader(async path => {
    calls.push(path);
    if (path === 'tv/2') return { id: 2, name: show.title, status: 'Ended', last_episode_to_air: { season_number: 1, episode_number: 1, air_date: '2026-10-16' }, seasons: [{ season_number: 1, air_date: '2026-10-01' }, { season_number: 2, air_date: '2026-11-01' }] };
    if (offline) throw Error('temporary outage');
    return { episodes: [{ season_number: Number(path.split('/').at(-1)), episode_number: 1, air_date: '2026-11-16' }] };
  });
  const sources = [{ source: 'arvio', read: async () => [show] }];
  const first = await module.loadCalendar({ ...options, sources });
  assert.equal(first.failedTitles, 1);
  offline = false;
  const next = await module.loadCalendar({ ...options, start: '2026-11-01', end: '2026-11-30', sources });
  assert.equal(next.failedTitles, 0);
  assert.ok(next.releases.some(row => row.season === 2));
  assert.ok(calls.includes('tv/2/season/2'));
});

test('confirmed missing metadata backs off but explicit retry and refresh bypass it', async () => {
  let calls = 0;
  const module = loader(async () => { calls++; throw Object.assign(Error('missing'), { status: 404 }); });
  const sources = [{ source: 'arvio', read: async () => [movie] }];
  await module.loadCalendar({ ...options, sources });
  await module.loadCalendar({ ...options, start: '2026-11-01', end: '2026-11-30', sources });
  assert.equal(calls, 1);
  await module.loadCalendar({ ...options, sources, retryTitleKeys: ['movie:1'] });
  assert.equal(calls, 2);
  await module.loadCalendar({ ...options, sources, refreshMetadata: true });
  assert.equal(calls, 3);
});

test('region, TV release type, stable order and distinct day previews agree with Android', () => {
  assert.equal(calendar.calendarRegion('nl-NL'), 'NL');
  assert.equal(calendar.calendarRegion('en'), 'US');
  const rows = loader().movieCalendarReleases({ item: movie, sources: ['arvio'] }, { release_dates: { results: [
    { iso_3166_1: 'NL', release_dates: [] },
    { iso_3166_1: 'US', release_dates: [{ type: 6, release_date: '2026-10-16' }] }
  ] } }, 'nl');
  assert.equal(rows[0].kind, 'tv'); assert.equal(rows[0].region, 'US');
  const unknown = { ...rows[0], item: show, id: 'unknown', kind: 'episode', episode: 1 };
  const timed = { ...unknown, id: 'timed', episode: 2, timestamp: '2026-10-16T19:00:00Z' };
  assert.deepEqual(json(calendar.sortCalendarReleases([unknown, timed]).map(row => row.id)), ['timed', 'unknown']);
  assert.equal(calendar.calendarPreviewTitles([unknown, timed]).length, 1);
});

test('25 unknown seasons load in two batches without losing any episodes', async () => {
  const calls = [];
  const module = loader(async (path, params) => {
    calls.push([path, params.append_to_response]);
    if (!params.append_to_response) return { id: 2, name: show.title, seasons: Array.from({ length: 25 }, (_, i) => ({ id: i + 1, season_number: i + 1 })) };
    return Object.fromEntries(params.append_to_response.split(',').map(key => {
      const number = Number(key.split('/')[1]);
      return [key, { season_number: number, episodes: [{ season_number: number, episode_number: 1, air_date: '2026-10-16' }] }];
    }));
  });
  const result = await module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }] });
  assert.equal(result.releases.length, 25); assert.equal(result.failedTitles, 0);
  assert.equal(calls.length, 3, 'One details request plus two batches, not 25 season requests');
  assert.ok(calls.every(([path]) => path === 'tv/2'));
});

test('missing appended season falls back individually without refetching successful blocks', async () => {
  const calls = [];
  const episode = number => ({ season_number: number, episode_number: 1, air_date: '2026-10-16' });
  const module = loader(async (path, params) => {
    calls.push(path);
    if (params.append_to_response) return { 'season/2': { season_number: 2, episodes: [episode(2)] } };
    if (path === 'tv/2/season/1') return { episodes: [episode(1)] };
    return { id: 2, name: show.title, seasons: [1, 2].map(season_number => ({ id: season_number, season_number })) };
  });
  const result = await module.loadCalendar({ ...options, sources: [{ source: 'arvio', read: async () => [show] }] });
  assert.equal(result.releases.length, 2); assert.equal(result.failedTitles, 0);
  assert.deepEqual(calls, ['tv/2', 'tv/2', 'tv/2/season/1']);
});

test('verified empty title projections survive reloads while failed titles alone bypass metadata cache', async () => {
  const calls = []; let offline = true;
  const module = loader(async path => {
    calls.push(path);
    if (path === 'movie/3' && offline) throw Error('temporary failure');
    return { id: Number(path.split('/')[1]), title: movie.title, release_date: path === 'movie/2' ? '2026-11-16' : '2026-10-16' };
  });
  const base = { ...options, cacheScope: 'profile-a', sources: [{ source: 'arvio', read: async () => [1, 2, 3].map(id => ({ ...movie, id })) }] };
  const first = await module.loadCalendar(base);
  assert.deepEqual(json(first.failedTitleKeys), ['movie:3']);
  offline = false;
  const second = await module.loadCalendar({ ...base, retryTitleKeys: first.failedTitleKeys });
  assert.equal(second.failedTitles, 0); assert.equal(second.releases.length, 2);
  assert.equal(calls.filter(path => path === 'movie/1').length, 1);
  assert.equal(calls.filter(path => path === 'movie/2').length, 1, 'Confirmed empty month is cached too');
  assert.equal(calls.filter(path => path === 'movie/3').length, 2);
  const filtered = await module.loadCalendar({ ...base, sources: [{ source: 'trakt', read: async () => [movie] }] });
  assert.deepEqual(json(filtered.releases[0].sources), ['trakt'], 'Cache cannot resurrect removed memberships');
});

test('title caches persist empty months, isolate profiles and expire without extending on reads', () => {
  const store = storage();
  const cached = load('lib/calendarTitleCache.ts', { './storage': store });
  const now = Date.now; let clock = now(); Date.now = () => clock;
  try {
    cached.rememberCalendarTitle('profile-a:title', [], 100);
    cached.persistCalendarTitles();
    const reopened = load('lib/calendarTitleCache.ts', { './storage': store });
    assert.deepEqual(json(reopened.readCalendarTitle('profile-a:title')), []);
    assert.equal(reopened.readCalendarTitle('profile-b:title'), undefined);
    clock += 99; assert.ok(reopened.readCalendarTitle('profile-a:title'));
    clock += 2; assert.equal(reopened.readCalendarTitle('profile-a:title'), undefined);
  } finally { Date.now = now; }
});

test('planned titles publish while the same tracker is still reading watching shows', async () => {
  const watching = deferred(), painted = deferred();
  const module = loader(async path => ({ id: Number(path.split('/')[1]), title: movie.title, release_date: '2026-10-16' }));
  const request = module.loadCalendar({ ...options, sources: [{ source: 'trakt', read: async publish => {
    publish([movie]); await watching.promise; return [movie, { ...movie, id: 3 }];
  } }], onProgress: value => { if (value.releases.length) painted.resolve(value); } });
  assert.equal((await painted.promise).releases.length, 1);
  watching.resolve(); const final = await request;
  assert.equal(final.releases.length, 2); assert.equal(final.titleCount, 2);
});

test('calendar watching identities paginate past 500, exclude all hidden pages and avoid episode history', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: {} }, './http': {} });
  const client = new TraktClient(); client.refreshIfNeeded = async () => ({ access_token: 'fixture' });
  const paths = [];
  client.trakt = async path => {
    paths.push(path);
    const page = Number(new URL(`https://fixture${path}`).searchParams.get('page'));
    const total = path.startsWith('/sync') ? 602 : path.includes('reset') ? 0 : 101;
    return Array.from({ length: Math.max(0, Math.min(100, total - (page - 1) * 100)) }, (_, i) => ({ show: { ids: { trakt: (page - 1) * 100 + i + 1 } } }));
  };
  const rows = await client.calendarWatchingShows();
  assert.equal(rows.length, 501); assert.equal(rows[0].show.ids.trakt, 102);
  assert.ok(rows.every(row => row.type === 'show'));
  assert.ok(paths.every(path => !path.includes('extended=')));
  client.trakt = async () => { throw Error('offline'); };
  await assert.rejects(client.calendarWatchingShows(), /offline/);
});

test('planned Trakt watchlists paginate fully and stop if a provider repeats a page', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: {} }, './http': {} });
  const client = new TraktClient(); client.refreshIfNeeded = async () => ({ access_token: 'fixture' });
  const paths = [];
  client.trakt = async path => {
    paths.push(path);
    const page = Number(new URL(`https://fixture${path}`).searchParams.get('page'));
    return Array.from({ length: Math.min(100, 242 - (page - 1) * 100) }, (_, i) => ({ type: 'show', show: { ids: { trakt: (page - 1) * 100 + i, tmdb: (page - 1) * 100 + i } } }));
  };
  assert.equal((await client.watchlist()).length, 242);
  assert.equal(paths.length, 3);
  const repeated = Array.from({ length: 100 }, (_, i) => ({ type: 'show', show: { ids: { trakt: i } } }));
  client.trakt = async () => repeated;
  assert.equal((await client.watchlist()).length, 100);
});

test('public calendar metadata uses direct CORS without an account and reuses seasons across months', async () => {
  const calls = [];
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: { traktClientId: 'public-app-id' } }, './http': {}, './storage': storage() }, {
    window: { location: { origin: 'https://web.arvio.tv' } },
    fetch: async (url, init) => {
      calls.push(url);
      assert.equal(new Headers(init.headers).has('Authorization'), false);
      return new Response(JSON.stringify(url.includes('/search/') ? [{ show: { ids: { tmdb: 2, trakt: 44 } } }] : [{ season: 1, number: 8, first_aired: '2026-10-16T19:00:00Z' }]));
    }
  });
  const client = new TraktClient();
  const [first, second] = await Promise.all([client.calendarSeason(2, 1), client.calendarSeason(2, 1)]);
  assert.equal(first[0].first_aired, second[0].first_aired);
  await client.calendarSeason(2, 1);
  assert.equal(calls.length, 2);
  assert.ok(calls.every(url => url.startsWith('https://api.trakt.tv/')));
  assert.equal(client.canUseDirectFallback('/oauth/token', {}), false);
  assert.equal(client.canUseDirectFallback('/sync/watchlist', {}), false);
  assert.equal(client.canUseDirectFallback('/shows/44/seasons/1', { method: 'POST' }), false);
});

test('a large library does not evict its details between adjacent months', async () => {
  let calls = 0;
  const module = loader(async path => { calls++; return { id: Number(path.split('/')[1]), title: 'Movie', release_date: '2026-11-16' }; });
  const sources = [{ source: 'arvio', read: async () => Array.from({ length: 800 }, (_, id) => ({ ...movie, id: id + 1 })) }];
  const october = await module.loadCalendar({ ...options, sources });
  assert.equal(october.releases.length, 0);
  const november = await module.loadCalendar({ ...options, start: '2026-11-01', end: '2026-11-30', sources });
  assert.equal(november.releases.length, 800);
  assert.equal(calls, 800, 'All existing metadata is reused, not downloaded a second time');
});

test('public Trakt missing metadata and throttling never retry through a blocked proxy', async () => {
  class HttpError extends Error { constructor(status) { super('fixture'); this.status = status; } }
  for (const status of [404, 429]) {
    let direct = 0, proxy = 0;
    const { TraktClient } = load('lib/trakt.ts', { './config': { config: { traktClientId: 'public-app-id' } }, './http': {
      HttpError, jsonRequest: async () => { proxy++; throw Error('blocked proxy'); }
    }, './storage': storage() }, { window: { location: { origin: 'https://web.arvio.tv' } }, fetch: async () => { direct++; return new Response('', { status }); } });
    await assert.rejects(new TraktClient().calendarSeason(2, 1), error => error.status === status);
    assert.equal(direct, 1); assert.equal(proxy, 0);
  }
});

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

test('Calendar retries a temporary list outage once without treating it as an empty watchlist', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: { traktClientId: 'public-app-id' } }, './http': {} });
  const client = new TraktClient();
  client.refreshIfNeeded = async () => ({ access_token: 'fixture-token' });
  let calls = 0;
  client.trakt = async () => { if (++calls === 1) throw Object.assign(Error('temporary outage'), { status: 503 }); return [{ type: 'show', show: { ids: { trakt: 2 } } }]; };
  assert.equal((await client.watchlist()).length, 1);
  assert.equal(calls, 2);
  calls = 0;
  client.trakt = async () => { calls++; throw Object.assign(Error('rate limited'), { status: 429 }); };
  await assert.rejects(client.watchlist(), error => error.status === 429);
  assert.equal(calls, 1, 'Do not amplify rate limiting');
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
  // Advance the deadline and request timers together. Real 25 ms timers can fire
  // just before Date.now reaches the deadline, making a fifth call appear in CI.
  let now = 0, nextTimer = 0;
  const timers = new Map(), started = deferred();
  const clock = {
    Date: class extends Date { static now() { return now; } },
    setTimeout: (callback, delay) => { const id = ++nextTimer; timers.set(id, { callback, at: now + delay }); return id; },
    clearTimeout: id => timers.delete(id),
  };
  const episodes = Array.from({ length: 80 }, (_, index) => ({ season_number: index + 1, episode_number: 1, air_date: '2026-10-16' }));
  const module = loader(async path => path === 'tv/2' ? { id: 2, name: show.title, seasons: [{ id: 1, season_number: 1 }] } : { episodes }, undefined, clock);
  const snapshots = []; let timeCalls = 0;
  const request = module.loadCalendar({ ...options, timeoutMs: 500, enrichmentTimeoutMs: 25,
    sources: [{ source: 'arvio', read: async () => [show] }], episodeTime: async () => { if (++timeCalls === 4) started.resolve(); return new Promise(() => {}); }, onProgress: value => snapshots.push(value) });
  await started.promise;
  now = 25;
  for (const [id, timer] of [...timers]) {
    if (timer.at <= now) { timers.delete(id); timer.callback(); }
  }
  const result = await request;
  assert.equal(result.releases.length, 80, 'All confirmed dates remain available');
  assert.equal(timeCalls, 4, 'Expired queue entries do not start new network requests');
  assert.ok(snapshots.length <= 4, 'Progress snapshots are coalesced instead of copied/sorted per episode');
  assert.equal(snapshots.at(-1).releases.length, 80, 'Final snapshot is immediate and complete');
  assert.equal(timers.size, 0, 'Completed work clears its timers');
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

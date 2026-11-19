const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load, storage } = require('./load.cjs');
const { sourceOf, storeSource } = require('./sources.cjs');
// Values built inside load()'s VM context carry its own Array/Object
// prototypes, so compare structure only.
const same = (actual, expected, message) => assert.deepEqual(JSON.parse(JSON.stringify(actual)), expected, message);

const tick = () => new Promise((resolve) => setImmediate(resolve));

// ---- Card extras: one TMDB call per title, cached ---------------------------

test('card extras come from ONE details call per title and are cached in memory and storage', async () => {
  const { createCardExtrasCache } = load('lib/cardExtras.ts');
  const calls = [];
  const saved = [];
  const timers = [];
  const cache = createCardExtrasCache({
    request: async (mediaType, id) => {
      calls.push(`${mediaType}/${id}`);
      return {
        runtime: 145, poster_path: '/p.jpg', backdrop_path: '/b.jpg', external_ids: { imdb_id: 'tt123' },
        images: { logos: [{ file_path: '/de.png', iso_639_1: 'de', vote_average: 9 }, { file_path: '/en-low.png', iso_639_1: 'en', vote_average: 1 }, { file_path: '/en.png', iso_639_1: 'en', vote_average: 5 }] },
        'watch/providers': { results: { US: { flatrate: [{ provider_name: 'Netflix' }, { provider_name: 'Hulu' }] }, DE: { flatrate: [{ provider_name: 'WOW' }] } } }
      };
    },
    imageUrl: (kind, value) => value ? `${kind}:${value}` : '',
    load: () => null,
    save: (value) => saved.push(value),
    setTimer: (fn) => timers.push(fn)
  });
  assert.equal(cache.peek('movie', 7), null);
  const [first, second] = await Promise.all([cache.get('movie', 7), cache.get('movie', 7)]);
  same(calls, ['movie/7'], 'concurrent cards share one request');
  assert.equal(first, second);
  assert.equal(first.logo, 'logo:/en.png', 'best-voted English logo, like Android');
  same(first.providers, ['Netflix', 'Hulu'], 'US flatrate providers only');
  assert.equal(first.runtime, 145);
  assert.equal(first.imdbId, 'tt123');
  await cache.get('movie', 7);
  assert.equal(calls.length, 1, 'a cached title makes no further request');
  assert.equal(cache.peek('movie', 7).logo, 'logo:/en.png');
  timers.forEach((fn) => fn());
  assert.equal(Object.keys(saved.at(-1)).length, 1, 'persisted for the next visit');
});

test('card extras restore from storage on a warm load without any request', async () => {
  const { createCardExtrasCache } = load('lib/cardExtras.ts');
  const calls = [];
  const cache = createCardExtrasCache({
    request: async () => { calls.push(1); return {}; },
    imageUrl: () => '',
    load: () => ({ 'tv:5': { l: 'logo.png', p: ['Netflix'], r: 50, i: '', b: null, m: 'tt9', at: Date.now() } }),
    save: () => {}
  });
  const extras = await cache.get('tv', 5);
  assert.equal(extras.logo, 'logo.png');
  assert.equal(extras.imdbId, 'tt9');
  assert.equal(calls.length, 0);
});

test('a failed lookup is not retried immediately and ids below 1 never request', async () => {
  const { createCardExtrasCache } = load('lib/cardExtras.ts');
  let calls = 0;
  let now = 1000;
  const cache = createCardExtrasCache({ request: async () => { calls++; throw new Error('429'); }, imageUrl: () => '', load: () => null, save: () => {}, now: () => now });
  assert.equal(await cache.get('movie', 1), null);
  assert.equal(await cache.get('movie', 1), null);
  assert.equal(calls, 1);
  now += 3 * 60 * 1000;
  await cache.get('movie', 1);
  assert.equal(calls, 2, 'retried after the failure window');
  assert.equal(await cache.get('movie', -4), null);
  assert.equal(calls, 2);
});

test('tmdb card helpers share the combined call (logo, providers and meta: one request)', async () => {
  const calls = [];
  const api = load('lib/tmdb.ts', {
    './config': { config: { imageBase: 'https://image.tmdb.org/t/p/w780', backdropBase: 'https://image.tmdb.org/t/p/w1280', logoBase: 'https://image.tmdb.org/t/p/w500' } },
    './storage': storage(), './metadata/anizip': {}, './metadata/dispatcher': {},
    './mediaImages': { tmdbImageUrl: (base, value) => value ? base + value : '' },
    './http': { proxiedUrl: (x) => x, apiProxiedUrl: (x) => x, jsonRequest: async (url) => {
      calls.push(new URL(url));
      return { runtime: 100, images: { logos: [{ file_path: '/l.png', iso_639_1: 'en' }] }, 'watch/providers': { results: { US: { flatrate: [{ provider_name: 'Netflix' }] } } }, external_ids: { imdb_id: 'tt1' } };
    } }
  }, { window: { location: { origin: 'https://web.example' } } });
  const [logo, providers, meta] = await Promise.all([
    api.getLogoUrl({ mediaType: 'movie', id: 3 }), api.getCardProviders({ mediaType: 'movie', id: 3 }), api.getCardMeta({ mediaType: 'movie', id: 3 })
  ]);
  assert.equal(calls.length, 1);
  assert.equal(calls[0].pathname, '/api/tmdb/movie/3');
  assert.equal(calls[0].searchParams.get('append_to_response'), 'images,external_ids,watch/providers');
  assert.equal(calls[0].searchParams.get('include_image_language'), 'en,null');
  assert.equal(logo, 'https://image.tmdb.org/t/p/w500/l.png');
  same(providers, ['Netflix']);
  assert.equal(meta.imdbId, 'tt1');
});

test('a hover prefetch and the details page opening share one in-flight details request', async () => {
  let resolve;
  const calls = [];
  const api = load('lib/tmdb.ts', {
    './config': { config: { imageBase: '', backdropBase: '', logoBase: '' } }, './storage': storage(),
    './metadata/anizip': {}, './metadata/dispatcher': {}, './mediaImages': { tmdbImageUrl: () => '' },
    './http': { proxiedUrl: (x) => x, apiProxiedUrl: (x) => x, jsonRequest: (url) => { calls.push(String(url)); return new Promise((done) => { resolve = done; }); } }
  }, { window: { location: { origin: 'https://web.example' } } });
  const item = { id: 1396, mediaType: 'tv', title: 'Breaking Bad' };
  api.prefetchDetails(item);
  api.prefetchDetails(item);
  assert.equal(calls.filter((url) => url.includes('credits')).length, 1);
  resolve({ id: 1396, name: 'Breaking Bad' });
  await tick();
  api.prefetchDetails(item);
  assert.equal(calls.filter((url) => url.includes('credits')).length, 1, 'served from the cache once settled');
});

// ---- Row priority over decoration -------------------------------------------

test('card decoration waits while a row loads, then runs a few at a time', async () => {
  const { createFetchPriority } = load('lib/fetchPriority.ts');
  const timers = [];
  const priority = createFetchPriority({ concurrency: 2, maxRowWaitMs: 2500, setTimer: (fn) => { timers.push(fn); return timers.length; }, clearTimer: () => {} });
  let finishRow;
  const row = priority.trackRow(new Promise((resolve) => { finishRow = resolve; }));
  const started = [];
  const releases = [];
  for (const id of [1, 2, 3]) void priority.slot().then((release) => { started.push(id); releases.push(release); });
  await tick();
  same(started, [], 'no decoration while the row loads');
  finishRow('row'); await row; await tick();
  same(started, [1, 2], 'two at a time');
  releases[0](); await tick();
  same(started, [1, 2, 3]);
});

test('decoration is never starved by a slow row, and hovered cards jump the queue', async () => {
  const { createFetchPriority } = load('lib/fetchPriority.ts');
  const timers = [];
  const priority = createFetchPriority({ concurrency: 1, setTimer: (fn) => { timers.push(fn); return 1; }, clearTimer: () => {} });
  void priority.trackRow(new Promise(() => {}));
  const started = [];
  void priority.slot().then((release) => { started.push('visible'); release(); });
  void priority.slot(undefined, true).then((release) => { started.push('hovered'); release(); });
  await tick();
  same(started, ['hovered'], 'an urgent (hovered/focused) card runs even while rows load');
  timers.forEach((fn) => fn()); await tick();
  same(started, ['hovered', 'visible'], 'the starvation guard opens the gate');
});

test('a card that scrolls away gives up its place in the queue', async () => {
  const { createFetchPriority } = load('lib/fetchPriority.ts');
  const priority = createFetchPriority({ concurrency: 1 });
  let release;
  void priority.slot().then((value) => { release = value; });
  await tick();
  const controller = new AbortController();
  const aborted = priority.slot(controller.signal);
  controller.abort();
  await assert.rejects(aborted, /Aborted/);
  release();
  assert.equal(priority.stats().queued, 0);
});

// ---- Home rows: 20 titles + View all ----------------------------------------

function tmdbWithCalls(response) {
  const calls = [];
  const api = load('lib/tmdb.ts', {
    './config': { config: { imageBase: '', backdropBase: '', logoBase: '' } }, './storage': storage(),
    './metadata/anizip': {}, './metadata/dispatcher': {}, './mediaImages': { tmdbImageUrl: () => '' },
    './http': { proxiedUrl: (x) => x, apiProxiedUrl: (x) => x, jsonRequest: async (url) => { const u = new URL(url, 'https://web.example'); calls.push(u); return response(u); } }
  }, { window: { location: { origin: 'https://web.example' } } });
  return { api, calls };
}

test('a Home row hydrates only its first 20 list titles and says it has more', async () => {
  const refs = Array.from({ length: 96 }, (_, index) => ({ id: index + 1, mediatype: 'movie' }));
  const { api, calls } = tmdbWithCalls((url) => url.pathname.includes('mdblist') || url.href.includes('mdblist') ? refs : { id: Number(url.pathname.split('/').pop()), title: 'T' });
  const catalog = { id: 'trending_movies', name: 'Trending', sourceType: 'mdblist', mediaType: 'movie', sourceUrl: 'https://mdblist.com/lists/x/y', enabled: true };
  const row = await api.loadCatalog(catalog, 'en-US', [], [], { limit: api.HOME_ROW_LIMIT });
  assert.equal(api.HOME_ROW_LIMIT, 20);
  assert.equal(row.items.length, 20);
  assert.equal(row.hasMore, true);
  assert.equal(calls.filter((url) => url.pathname.startsWith('/api/tmdb/')).length, 20, 'one TMDB call per shown title, not 96');
  calls.length = 0;
  const full = await api.loadCatalog(catalog, 'en-US');
  assert.equal(full.items.length, 96, '"View all" loads the whole list');
});

test('a limited TMDB endpoint row asks for one page instead of ten', async () => {
  const { api, calls } = tmdbWithCalls((url) => ({ results: Array.from({ length: 20 }, (_, i) => ({ id: Number(url.searchParams.get('page')) * 100 + i, title: 'x' })), total_pages: 50 }));
  const catalog = { id: 'popular', name: 'Popular', sourceType: 'tmdb', endpoint: 'movie/popular', mediaType: 'movie', enabled: true };
  const row = await api.loadCatalog(catalog, 'en-US', [], [], { limit: 20 });
  assert.equal(calls.length, 1);
  assert.equal(row.items.length, 20);
  assert.equal(row.hasMore, true);
});

test('the Watchlist row follows Continue Watching only when the synced setting is on', () => {
  const { withHomeWatchlistRow, HOME_WATCHLIST_ROW_ID } = load('lib/homeRows.ts');
  const rows = [{ id: 'continue_watching', title: 'Continue Watching', items: [{ id: 1, mediaType: 'tv', title: 'A' }] }, { id: 'trending', title: 'Trending', items: [] }];
  const watchlist = [{ id: 9, mediaType: 'movie', title: 'Saved' }, { id: 9, mediaType: 'movie', title: 'Saved' }, { id: 10, mediaType: 'movie', title: ' ' }];
  same(withHomeWatchlistRow(rows, watchlist, false).map((row) => row.id), ['continue_watching', 'trending'], 'off by default, like Android');
  const shown = withHomeWatchlistRow(rows, watchlist, true);
  same(shown.map((row) => row.id), ['continue_watching', HOME_WATCHLIST_ROW_ID, 'trending']);
  assert.equal(shown[1].items.length, 1, 'deduplicated, blank titles dropped');
  same(withHomeWatchlistRow([rows[1]], watchlist, true).map((row) => row.id), [HOME_WATCHLIST_ROW_ID, 'trending']);
  same(withHomeWatchlistRow(rows, [], true).map((row) => row.id), ['continue_watching', 'trending']);
});

// ---- Billboard ---------------------------------------------------------------

test('the billboard rotates its pool, stops on a hovered card and pauses for focus or the pause control', () => {
  const { createHeroController } = load('lib/homeHero.ts');
  const hero = createHeroController();
  const a = { id: 1, mediaType: 'movie', title: 'A', backdrop: 'a' };
  const b = { id: 2, mediaType: 'movie', title: 'B', backdrop: 'b' };
  const noArt = { id: 3, mediaType: 'movie', title: 'C', backdrop: null };
  let renders = 0;
  hero.subscribe(() => renders++);
  hero.addToPool([a, noArt, b]);
  same(hero.getState().pool.map((item) => item.id), [1, 2], 'only titles with artwork');
  assert.equal(hero.getState().preview, a);
  hero.advance();
  assert.equal(hero.getState().preview, b);
  hero.setHolding(true);
  hero.advance();
  assert.equal(hero.getState().preview, b, 'no rotation while the hero buttons have focus/hover');
  hero.setHolding(false);
  hero.togglePaused();
  hero.advance();
  assert.equal(hero.getState().preview, b, 'paused');
  hero.togglePaused();
  hero.advance();
  assert.equal(hero.getState().preview, a, 'resumed');
  hero.pin(b);
  hero.advance();
  assert.equal(hero.getState().preview, b, 'a hovered card pins the hero');
  hero.togglePaused();
  assert.equal(hero.getState().pinned, false, 'the control resumes the slideshow');
  const before = renders;
  hero.setHolding(false);
  assert.equal(renders, before, 'no-op updates do not notify subscribers');
});

test('hero text is the whole synopsis (CSS clamps it) and runtimes share one format', () => {
  const { heroOverview } = load('lib/homeHero.ts');
  const long = 'Word '.repeat(80).trim();
  assert.equal(heroOverview({ overview: long }, 'x'), long, 'no 150-character mid-word cut');
  assert.equal(heroOverview({ overview: '' }, 'fallback'), 'fallback');
  const { formatRuntime } = load('lib/mediaFormat.ts');
  assert.equal(formatRuntime(145), '2h 25m');
  assert.equal(formatRuntime('145m'), '2h 25m');
  assert.equal(formatRuntime(120), '2h');
  assert.equal(formatRuntime(48), '48m');
  assert.equal(formatRuntime(0), '');
});

test('hero Play records a one-shot play intent that only the same title consumes', () => {
  const intent = load('lib/playIntent.ts');
  intent.requestPlayOnOpen({ mediaType: 'tv', id: 5, seasonNumber: 2, episodeNumber: 3 }, 1000);
  assert.equal(intent.peekPlayOnOpen({ mediaType: 'movie', id: 5 }, 1000), null);
  same(intent.peekPlayOnOpen({ mediaType: 'tv', id: 5 }, 1000), { season: 2, episode: 3 });
  same(intent.takePlayOnOpen({ mediaType: 'tv', id: 5 }, 1001), { season: 2, episode: 3 });
  assert.equal(intent.takePlayOnOpen({ mediaType: 'tv', id: 5 }, 1002), null, 'consumed once');
  intent.requestPlayOnOpen({ mediaType: 'movie', id: 1 }, 0);
  assert.equal(intent.peekPlayOnOpen({ mediaType: 'movie', id: 1 }, 60_000), null, 'stale intents expire');
});

test('details starts playback from the billboard intent through its normal Play path', () => {
  const source = fs.readFileSync(path.join(__dirname, '../components/details/DetailsDrawer.tsx'), 'utf8');
  assert.match(source, /peekPlayOnOpen\(item\)/);
  assert.match(source, /if \(!isTv\) \{ start\(\(\) => playPrimary\(false\)\); return undefined; \}/, 'movies use the details Play button path');
  assert.match(source, /plan\.season != null && plan\.episode != null\) \{ start\(\(\) => playPrimary\(false\)\)/, 'series resume through the same plan');
  assert.match(source, /openEpisodeSources\(heroPlayIntent\.season \?\? 1, heroPlayIntent\.episode \?\? 1\)/);
});

// ---- Images -------------------------------------------------------------------

test('cards get TMDB srcsets instead of 1280px backdrops; the logo is a small file', () => {
  const { tmdbSrcSet, tmdbSized } = load('lib/mediaImages.ts');
  const url = 'https://image.tmdb.org/t/p/w1280/abc.jpg';
  assert.equal(tmdbSrcSet(url, [300, 780]), 'https://image.tmdb.org/t/p/w300/abc.jpg 300w, https://image.tmdb.org/t/p/w780/abc.jpg 780w');
  assert.equal(tmdbSized(url, 780), 'https://image.tmdb.org/t/p/w780/abc.jpg');
  assert.equal(tmdbSrcSet('http://plex.local/thumb.jpg', [300]), undefined, 'home-server artwork is left alone');
  const { config } = load('lib/config.ts');
  same(config.cardImageWidths.backdrop, [300, 780, 1280]);
  const logo = fs.statSync(path.join(__dirname, '../public/arvio-logo.svg')).size;
  assert.ok(logo < 40 * 1024, `arvio-logo.svg is ${logo} bytes`);
});

// ---- Source banner -------------------------------------------------------------

test('dismissing the source banner persists across launches (localStorage), per profile', () => {
  const values = new Map();
  const events = [];
  const window = {
    localStorage: { getItem: (key) => values.has(key) ? values.get(key) : null, setItem: (key, value) => values.set(key, value), removeItem: (key) => values.delete(key) },
    dispatchEvent: (event) => events.push(event.type)
  };
  class CustomEvent { constructor(type) { this.type = type; } }
  const prompt = load('lib/sourcePrompt.ts', {}, { window, CustomEvent });
  assert.equal(prompt.isSourcePromptDismissed('p1'), false);
  prompt.setSourcePromptDismissed('p1', true);
  assert.equal(prompt.isSourcePromptDismissed('p1'), true);
  assert.equal(prompt.isSourcePromptDismissed('p2'), false);
  same(events, ['arvio:source-prompt'], 'the quiet "Add a source" entry point is told');
  prompt.setSourcePromptDismissed('p1', false);
  assert.equal(prompt.isSourcePromptDismissed('p1'), false, '"Add a source" brings it back');
  const css = fs.readFileSync(path.join(__dirname, '../app/source-setup.css'), 'utf8');
  assert.doesNotMatch(css, /\.content > \.source-setup-card\s*\{/, 'the selector that never matched is gone');
});

test('the banner is expected from the last launch so it never pushes Home down late', () => {
  const values = new Map();
  const window = { localStorage: { getItem: (key) => values.has(key) ? values.get(key) : null, setItem: (key, value) => values.set(key, value), removeItem: (key) => values.delete(key) }, dispatchEvent: () => {} };
  const prompt = load('lib/sourcePrompt.ts', {}, { window, CustomEvent: class { constructor(type) { this.type = type; } } });
  assert.equal(prompt.isSourcePromptExpected('p1'), false, 'first launch: wait for the addon pull');
  prompt.setSourcePromptExpected('p1', true);
  assert.equal(prompt.isSourcePromptExpected('p1'), true);
  prompt.setSourcePromptExpected('p1', false);
  assert.equal(prompt.isSourcePromptExpected('p1'), false, 'a profile that gained a source stops reserving it');
  const source = fs.readFileSync(path.join(__dirname, '../components/shell/NoAddonsPrompt.tsx'), 'utf8');
  assert.match(source, /\(addonsReady \|\| expected\)/);
});

// ---- Collections ----------------------------------------------------------------

test('collections show 4-5 columns on a laptop and hide their own service badge', () => {
  const { collectionColumns, collectionService } = load('lib/collectionLayout.ts', { './serviceLogos': load('lib/serviceLogos.ts') });
  assert.ok([4, 5].includes(collectionColumns(1376, false)), String(collectionColumns(1376, false)));
  assert.equal(collectionColumns(358, false), 2);
  assert.equal(collectionService({ name: 'Netflix', collectionSources: [{ kind: 'TMDB_WATCH_PROVIDER' }] }), 'Netflix');
  assert.equal(collectionService({ name: 'HBO Max', collectionGroup: 'SERVICE' }), 'HBO Max');
  assert.equal(collectionService({ name: 'Action', collectionGroup: 'GENRE', collectionSources: [{ kind: 'TMDB_GENRE' }] }), undefined);
});

// ---- Search ---------------------------------------------------------------------

test('search finds movies, series and people together, and a chosen type keeps its year filter', async () => {
  const { api, calls } = tmdbWithCalls((url) => url.pathname.endsWith('/search/multi')
    ? { results: [{ id: 1396, name: 'Breaking Bad', media_type: 'tv' }, { id: 5, title: 'El Camino', media_type: 'movie' }, { id: 17419, name: 'Bryan Cranston', media_type: 'person', known_for: [{ name: 'Breaking Bad' }] }], total_pages: 2, total_results: 30 }
    : { results: [{ id: 7, name: 'A show' }], total_pages: 1, total_results: 1 });
  const all = await api.searchTitlesPage({ query: 'breaking bad', type: 'all' }, 'en-US', 1);
  assert.equal(calls[0].pathname, '/api/tmdb/search/multi');
  same(all.shows.map((item) => item.title), ['Breaking Bad']);
  same(all.movies.map((item) => item.title), ['El Camino']);
  same(all.people.map((person) => [person.name, person.knownFor]), [['Bryan Cranston', 'Breaking Bad']]);
  assert.equal(all.total, 30);
  assert.equal(all.hasMore, true);
  await api.searchTitlesPage({ query: 'space', type: 'tv', year: '2020' }, 'es-ES', 2);
  assert.equal(calls[1].pathname, '/api/tmdb/search/tv');
  assert.equal(calls[1].searchParams.get('first_air_date_year'), '2020');
  assert.equal(calls[1].searchParams.get('page'), '2');
  assert.equal(calls[1].searchParams.get('language'), 'es-ES');
});

test('discover applies genre, rating, decade and language on the server and "All" merges movies and series', async () => {
  const { api, calls } = tmdbWithCalls((url) => ({ results: [{ id: url.pathname.endsWith('/tv') ? 2 : 1, title: 'x', name: 'y' }], total_pages: 3, total_results: 40 }));
  const result = await api.discoverTitlesPage({ type: 'all', genre: '878', rating: '7', decade: '1990-1999', language: 'ja', sort: 'popularity.desc' }, 'en-US', 1);
  const movie = calls.find((url) => url.pathname === '/api/tmdb/discover/movie');
  const tv = calls.find((url) => url.pathname === '/api/tmdb/discover/tv');
  assert.equal(movie.searchParams.get('with_genres'), '878');
  assert.equal(tv.searchParams.get('with_genres'), '10765', 'Sci-Fi maps to Sci-Fi & Fantasy for series (Android remapGenreId)');
  assert.equal(movie.searchParams.get('vote_average.gte'), '7');
  assert.equal(movie.searchParams.get('primary_release_date.gte'), '1990-01-01');
  assert.equal(tv.searchParams.get('first_air_date.lte'), '1999-12-31');
  assert.equal(movie.searchParams.get('with_original_language'), 'ja');
  same(result.items.map((item) => item.mediaType), ['movie', 'tv']);
  assert.equal(result.total, 80);
});

test('search model: Android genre lists, decades and recent searches', () => {
  const model = load('lib/searchModel.ts');
  const names = model.genresFor('movie').map((genre) => genre.label);
  for (const name of ['Horror', 'Sci-Fi', 'Romance', 'Thriller', 'War', 'Western']) assert.ok(names.includes(name), name);
  assert.equal(model.remapGenreForType('28', 'tv'), '10759');
  assert.equal(model.remapGenreForType('27', 'tv'), '', 'Horror has no series counterpart');
  same(model.decadeOptions(2026).map((d) => d.start), [2020, 2010, 2000, 1990, 1980, 1950]);
  let recent = [];
  for (const query of ['dune', 'a', 'Severance', 'DUNE']) recent = model.withRecentSearch(recent, query);
  same(recent, ['DUNE', 'Severance']);
  for (let i = 0; i < 12; i++) recent = model.withRecentSearch(recent, `query ${i}`);
  assert.equal(recent.length, model.MAX_RECENT_SEARCHES);
  const newest = model.discoverParams({ ...model.EMPTY_FILTERS, sort: 'release.desc' }, 'tv', new Date('2026-10-24T12:00:00Z'));
  assert.equal(newest.sort_by, 'first_air_date.desc');
  assert.equal(newest['first_air_date.lte'], '2026-10-24', 'newest never lists unreleased titles');
});

test('the search screen uses button pickers, keeps focus on Escape and supports "/"', () => {
  const source = fs.readFileSync(path.join(__dirname, '../components/search/SearchScreen.tsx'), 'utf8');
  assert.doesNotMatch(source, /<select\s/, 'no native selects (remote arrows changed their value)');
  assert.match(source, /role="dialog"/);
  assert.match(source, /event\.key !== '\/'/);
  assert.match(source, /if \(event\.key === 'Escape'\) \{[\s\S]{0,200}event\.preventDefault\(\);/);
});

// ---- Library --------------------------------------------------------------------

test('Add to Watchlist works without a tracker: ARVIO Cloud (outbox) is the store, trackers mirror', () => {
  const store = storeSource();
  const toggle = store.slice(store.indexOf('const toggleWatchlist = useCallback'), store.indexOf('const toggleWatched = useCallback'));
  assert.doesNotMatch(toggle, /activeSyncProvider\(\) === "none"/);
  assert.doesNotMatch(toggle, /Connect Trakt, Simkl, or MDBList/);
  assert.match(toggle, /queueWatchlistChange\(/);
  assert.match(toggle, /flushWatchlistOutbox\(/);
});

test('calendar list view groups a month by day in date order', () => {
  const { agendaDays } = load('lib/calendarAgenda.ts');
  const rows = [
    { id: 'b', date: '2026-10-20', timestamp: '2026-10-20T21:00:00Z' },
    { id: 'a', date: '2026-10-20', timestamp: '2026-10-20T19:00:00Z' },
    { id: 'c', date: '2026-10-03' },
    { id: 'x', date: '2026-11-01' }
  ];
  same(agendaDays(rows, '2026-10').map((day) => [day.date, day.entries.map((row) => row.id)]), [['2026-10-03', ['c']], ['2026-10-20', ['a', 'b']]]);
});

test('library empty states deep-link to the home-server settings and offer Browse', () => {
  const source = fs.readFileSync(path.join(__dirname, '../components/watchlist/WatchlistScreen.tsx'), 'utf8');
  assert.match(source, /requestSourceSettings\("homeserver"\)/);
  assert.match(source, /translateUi\("Browse"\)/);
  assert.match(source, /"Home servers"/);
  assert.doesNotMatch(source, /LibraryDialog title=\{translateUi\("Search library"\)\}/, 'inline search, not a one-input modal');
  assert.equal(fs.existsSync(path.join(__dirname, '../components/watchlist/TrackerLibrary.tsx')), false);
});

// ---- Navigation -----------------------------------------------------------------

test('every screen gets its own document title', () => {
  const { screenTitle } = load('lib/screenTitle.ts');
  assert.equal(screenTitle('home'), 'Home · ARVIO');
  assert.equal(screenTitle('watchlist'), 'Library · ARVIO');
  assert.equal(screenTitle('search', 'Dune'), 'Dune · ARVIO');
});

test('IMDb ratings are served from one in-memory copy of the store after the first read', async () => {
  const reads = [];
  const values = new Map([['arvio.web.imdbRatings.v1', { tt1: { rating: '8.1', at: Date.now() }, tt2: { rating: '', at: Date.now() } }]]);
  let fetches = 0;
  const api = load('lib/imdbRatings.ts', {
    './config': { config: { resolverUrl: '' } },
    './storage': { loadStored: (key, fallback) => { reads.push(key); return values.get(key) ?? fallback; }, saveStored: (key, value) => values.set(key, value) }
  }, { fetch: async () => { fetches++; return { ok: true, json: async () => ({ meta: { imdbRating: '7.0' } }) }; } });
  assert.equal(api.peekImdbRating('tt1'), '8.1');
  assert.equal(api.peekImdbRating('tt2'), null, 'known to have no rating');
  assert.equal(api.peekImdbRating('tt3'), undefined, 'never looked up');
  for (let i = 0; i < 50; i++) await api.getImdbRating('movie', 'tt1');
  assert.equal(fetches, 0);
  assert.equal(reads.length, 1, 'the persisted store is parsed once, not per card');
});

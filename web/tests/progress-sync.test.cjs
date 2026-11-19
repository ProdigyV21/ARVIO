const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load, storage } = require('./load.cjs');

// The backend's own merge rules, so these tests exercise the real client/server contract.
const serverMergePath = path.resolve(__dirname, '../../netlify-auth-site/netlify/functions/_sync-merge.js');
const server = fs.existsSync(serverMergePath) ? require(serverMergePath) : null;

const plain = (value) => JSON.parse(JSON.stringify(value));

function androidPayload() {
  return {
    version: 1,
    profiles: [{ id: 'p1', name: 'Arvind' }, { id: 'p2', name: 'Kids' }],
    accentColor: 'White',
    profileSettingsById: { p1: { autoPlayNext: true } },
    localContinueWatchingByProfile: {
      p1: [
        { id: 30, mediaType: 'TV', title: 'Show', season: 1, episode: 4, displaySeason: 1, displayEpisode: 4, progress: 50,
          resumePositionSeconds: 1200, durationSeconds: 2400, streamKey: 'android-stream', streamAddonId: 'com.torrentio',
          year: '2020', releaseDate: '', isUpNext: false, updatedAtMs: 1_000 },
        { id: 10, mediaType: 'MOVIE', title: 'Movie', progress: 20, resumePositionSeconds: 900, durationSeconds: 6000, updatedAtMs: 900 }
      ]
    },
    dismissedContinueWatchingByProfile: {}
  };
}

function fakeBackend(options = {}) {
  const state = { payload: options.payload ?? androidPayload(), requests: [], offline: false, progress: options.progress ?? 'real' };
  const jsonRequest = async (url, init = {}) => {
    state.requests.push({ url, init });
    if (state.offline) throw new TypeError('Failed to fetch');
    if (url.endsWith('account-sync-pull')) return { payload: structuredClone(state.payload) };
    if (state.writesFail) throw Object.assign(new Error('Service unavailable'), { status: 503 });
    if (url.endsWith('account-sync-push')) {
      state.payload = JSON.parse(init.body).payload;
      return { accepted: true };
    }
    if (url.endsWith('account-sync-progress')) {
      if (state.progress === 'missing') throw Object.assign(new Error('Not found'), { status: 404 });
      // Netlify's 404 for an unknown function has no CORS headers: the browser reports a network error.
      if (state.progress === 'blocked') throw new TypeError('Failed to fetch');
      const body = JSON.parse(init.body);
      assert.equal(body.accessToken, 'token');
      const result = server.applyProgressOperation(state.payload, server.normalizeProgressOperation(body));
      state.payload = result.payload;
      return { accepted: true, applied: result.applied };
    }
    throw new Error(`unexpected ${url}`);
  };
  return { state, http: { jsonRequest } };
}

function client(backend, disk = storage(), globals = {}) {
  const cloud = load('lib/cloud.ts', {
    './storage': disk,
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': {},
    './http': backend.http
  }, globals);
  return { cloud, disk };
}

const auth = () => ({
  session: { userId: 'account', accessToken: 'token', expiresAt: Date.now() + 3_600_000 },
  isNetlifySession: true,
  accessToken: async () => 'token'
});

const episode = (overrides = {}) => ({
  media_type: 'tv', show_tmdb_id: 30, profile_id: 'p1', season: 1, episode: 4, title: 'Show', episode_title: 'Four',
  progress: 0.6, position_seconds: 1440, duration_seconds: 2400, source: 'Web addon', stream_addon_id: null, ...overrides
});

const seasons = { 1: [1, 2, 3, 4, 5, 6].map((number) => ({ id: number, seasonNumber: 1, episodeNumber: number, name: `E${number}`, airDate: '2020-01-0' + number })),
  2: [{ id: 21, seasonNumber: 2, episodeNumber: 1, name: 'Premiere', airDate: '2030-05-01' }] };
const lookup = async (_id, season) => seasons[season] ?? [];

const progressCalls = (state) => state.requests.filter((request) => request.url.endsWith('account-sync-progress'));
const rows = (state, profile = 'p1') => state.payload.localContinueWatchingByProfile[profile];

test('an accidental play under 3% and under 60 s creates no Continue Watching row', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), { ...episode(), show_tmdb_id: 77, progress: 0.02, position_seconds: 45 }, 'p1');
  assert.equal(backend.state.requests.length, 0);
  await cloud.saveProgress(auth(), { ...episode(), show_tmdb_id: 77, progress: 0.01, position_seconds: 61 }, 'p1');
  assert.equal(progressCalls(backend.state).length, 1, '60 s of a long title counts, like Android');
});

test('a checkpoint is one small keepalive item with no preflight-triggering headers', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), episode(), 'p1');
  const [call] = progressCalls(backend.state);
  assert.equal(call.init.keepalive, true);
  assert.equal(call.init.method, 'POST');
  assert.deepEqual(plain(call.init.headers), { 'Content-Type': 'text/plain;charset=UTF-8' });
  assert.ok(call.init.body.length < 2_000);
  const body = JSON.parse(call.init.body);
  assert.equal(body.op, 'upsert');
  assert.equal(body.profileId, 'p1');
  assert.equal(body.item.isUpNext, false);
  assert.equal(backend.state.requests.some((request) => request.url.endsWith('account-sync-push')), false, 'no full-document write');
});

test('saving keeps Android\'s stream key for the same episode and one row per show for a new one', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), episode(), 'p1');
  let show = rows(backend.state).filter((row) => row.id === 30);
  assert.equal(show.length, 1);
  assert.equal(show[0].resumePositionSeconds, 1440);
  assert.equal(show[0].streamKey, 'android-stream');
  assert.equal(show[0].streamAddonId, 'com.torrentio');
  await cloud.saveProgress(auth(), episode({ episode: 5, position_seconds: 300, progress: 0.12 }), 'p1');
  show = rows(backend.state).filter((row) => row.id === 30);
  assert.equal(show.length, 1);
  assert.equal(show[0].episode, 5);
});

test('finishing an episode writes the next episode as an up next row', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), episode({ progress: 0.93, position_seconds: 2232 }), 'p1', [], { seasonEpisodes: lookup });
  const [row] = rows(backend.state).filter((item) => item.id === 30);
  assert.equal(row.episode, 5);
  assert.equal(row.season, 1);
  assert.equal(row.isUpNext, true);
  assert.equal(row.progress, 0);
  assert.equal(row.episodeTitle, 'E5');
  assert.equal(row.releaseDate, '2020-01-05');
  // Credits checkpoints after completion do not repeat it.
  const sent = progressCalls(backend.state).length;
  await cloud.saveProgress(auth(), episode({ progress: 0.96, position_seconds: 2300 }), 'p1', [], { seasonEpisodes: lookup });
  assert.equal(progressCalls(backend.state).length, sent);
});

test('a season finale points to the next season; a series finale just leaves Continue Watching', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), episode({ episode: 6, progress: 0.95 }), 'p1', [], { seasonEpisodes: lookup });
  const [next] = rows(backend.state).filter((item) => item.id === 30);
  assert.deepEqual([next.season, next.episode, next.isUpNext, next.releaseDate], [2, 1, true, '2030-05-01']);
  await cloud.saveProgress(auth(), episode({ season: 2, episode: 1, progress: 0.97 }), 'p1', [], { seasonEpisodes: lookup });
  assert.deepEqual(rows(backend.state).map((item) => item.id), [10]);
});

test('finishing a movie removes it', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), { media_type: 'movie', show_tmdb_id: 10, profile_id: 'p1', title: 'Movie', progress: 0.95, position_seconds: 5700, duration_seconds: 6000 }, 'p1');
  assert.deepEqual(rows(backend.state).map((item) => item.id), [30]);
});

test('offline progress survives a reload and is sent on the next app start', { skip: !server }, async () => {
  const backend = fakeBackend();
  const disk = storage();
  const first = client(backend, disk);
  backend.state.offline = true;
  await assert.rejects(first.cloud.saveProgress(auth(), episode({ position_seconds: 1500 }), 'p1'));
  await assert.rejects(first.cloud.saveProgress(auth(), episode({ position_seconds: 1560 }), 'p1'));
  const queued = [...disk.values.values()].find(Array.isArray);
  assert.equal(queued.length, 1, 'checkpoints for one title coalesce');
  assert.equal(queued[0].operation.item.resumePositionSeconds, 1560);
  // A fresh page load with the same storage, while saving still fails.
  backend.state.offline = false;
  backend.state.writesFail = true;
  const second = client(backend, disk);
  const history = await second.cloud.getContinueWatching(auth(), 'p1');
  assert.equal(history.find((item) => item.show_tmdb_id === 30).position_seconds, 1560, 'unsent progress is shown');
  assert.equal(rows(backend.state).find((item) => item.id === 30).resumePositionSeconds, 1200);
  backend.state.writesFail = false;
  await second.cloud.flushPendingProgress(auth());
  assert.equal(rows(backend.state).find((item) => item.id === 30).resumePositionSeconds, 1560);
  assert.equal([...disk.values.values()].find(Array.isArray).length, 0, 'acknowledged entries are removed');
});

test('without the endpoint on the backend the change goes through the full-document write', { skip: !server }, async () => {
  const backend = fakeBackend({ progress: 'missing' });
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), episode(), 'p1');
  const push = backend.state.requests.find((request) => request.url.endsWith('account-sync-push'));
  assert.ok(push, 'full write used');
  assert.ok(JSON.parse(push.init.body).baseUpdatedAtMs > 0, 'the read time is sent for the server merge');
  const show = rows(backend.state).filter((row) => row.id === 30);
  assert.equal(show.length, 1);
  assert.equal(show[0].streamKey, 'android-stream');
  // The missing endpoint is remembered instead of being probed on every checkpoint.
  await cloud.saveProgress(auth(), episode({ position_seconds: 1500 }), 'p1');
  assert.equal(progressCalls(backend.state).length, 1);
});

test('an endpoint the browser cannot reach (404 without CORS) falls back once the account backend answers', { skip: !server }, async () => {
  const backend = fakeBackend({ progress: 'blocked' });
  const disk = storage();
  const { cloud } = client(backend, disk);
  await cloud.saveProgress(auth(), episode(), 'p1');
  assert.equal(rows(backend.state).find((row) => row.id === 30).resumePositionSeconds, 1440);
  await cloud.saveProgress(auth(), episode({ position_seconds: 1500 }), 'p1');
  assert.equal(progressCalls(backend.state).length, 1, 'not probed again for a while');
  assert.equal(rows(backend.state).find((row) => row.id === 30).resumePositionSeconds, 1500);
});

test('NEXT_PUBLIC_ARVIO_PROGRESS_ENDPOINT=false keeps every save on the full-document write', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend, storage(), { process: { env: { NEXT_PUBLIC_ARVIO_PROGRESS_ENDPOINT: 'false' } } });
  await cloud.saveProgress(auth(), episode(), 'p1');
  assert.equal(progressCalls(backend.state).length, 0);
  assert.equal(rows(backend.state).find((row) => row.id === 30).resumePositionSeconds, 1440);
});

test('marking an episode watched discards its unsent checkpoint so it cannot come back', { skip: !server }, async () => {
  const backend = fakeBackend();
  const disk = storage();
  const { cloud } = client(backend, disk);
  backend.state.offline = true;
  await assert.rejects(cloud.saveProgress(auth(), episode({ position_seconds: 1500 }), 'p1'));
  backend.state.offline = false;
  await cloud.saveWatchedState(auth(), { id: 30, mediaType: 'tv', seasonNumber: 1, episodeNumber: 4 }, true, 'p1');
  await cloud.flushPendingProgress(auth());
  assert.deepEqual(rows(backend.state).map((item) => item.id), [10]);
  assert.deepEqual(backend.state.payload.localWatchedEpisodesByProfile.p1, ['show_tmdb:30:1:4']);
});

test('marking an episode watched keeps the up next row for the following episode', { skip: !server }, async () => {
  const backend = fakeBackend();
  const { cloud } = client(backend);
  await cloud.saveProgress(auth(), episode({ progress: 0.92 }), 'p1', [], { seasonEpisodes: lookup });
  await cloud.saveWatchedState(auth(), { id: 30, mediaType: 'tv', seasonNumber: 1, episodeNumber: 4 }, true, 'p1');
  const [row] = rows(backend.state).filter((item) => item.id === 30);
  assert.deepEqual([row.episode, row.isUpNext], [5, true]);
});

test('Continue Watching reads one row per show and maps up next rows to Up Next cards', async () => {
  const payload = androidPayload();
  payload.localContinueWatchingByProfile.p1.push(
    { id: 30, mediaType: 'TV', title: 'Show', season: 1, episode: 2, progress: 70, updatedAtMs: 100 },
    { id: 40, mediaType: 'TV', title: 'Next show', season: 3, episode: 2, progress: 0, isUpNext: true, releaseDate: '2021-02-03', updatedAtMs: 2_000 }
  );
  const backend = fakeBackend({ payload });
  const { cloud } = client(backend);
  const history = await cloud.getContinueWatching(auth(), 'p1');
  assert.deepEqual(plain(history.map((item) => [item.show_tmdb_id, item.episode])), [[40, 2], [30, 4], [10, null]]);
  const { historyToItem } = load('lib/mappers.ts', { './config': { config: {} }, './tmdb': {}, './mediaImages': { tmdbImageUrl: (_base, value) => value ?? '' } });
  const card = historyToItem(history[0]);
  assert.equal(card.badge, 'Up Next');
  assert.equal(card.timeRemainingLabel, 'Up next');
  assert.equal(card.releaseDate, '2021-02-03');
  assert.equal(historyToItem(history[1]).badge, undefined);
});

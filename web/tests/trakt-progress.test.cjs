const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

test('production build exposes canonical public Trakt ID, never its secret', () => {
  const config = load('next.config.mjs', {
    'node:fs': { writeFileSync() {}, mkdirSync() {} }
  }, { process: { cwd: () => '.', env: {
    NEXT_PUBLIC_TRAKT_CLIENT_ID: '', TRAKT_CLIENT_ID: 'public-client-id',
    TRAKT_CLIENT_SECRET: 'private-secret', APP_ANON_KEY: 'public-app-key',
    NEXT_PUBLIC_ARVIO_APP_ANON_KEY: ''
  } } }).default;
  assert.equal(config.env.NEXT_PUBLIC_TRAKT_CLIENT_ID, 'public-client-id');
  assert.equal(config.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, 'public-app-key');
  assert.equal(JSON.stringify(config.env).includes('private-secret'), false);
});

test('explicit browser client ID takes precedence over the canonical fallback', () => {
  const config = load('next.config.mjs', {
    'node:fs': { writeFileSync() {}, mkdirSync() {} }
  }, { process: { cwd: () => '.', env: {
    NEXT_PUBLIC_TRAKT_CLIENT_ID: 'explicit-id', TRAKT_CLIENT_ID: 'fallback-id'
  } } }).default;
  assert.equal(config.env.NEXT_PUBLIC_TRAKT_CLIENT_ID, 'explicit-id');
});

test('new episodes invalidate cached completed progress without new watch activity', async () => {
  let now = Date.now();
  class Clock extends Date { static now() { return now; } }
  const { TraktClient } = load('lib/trakt.ts', {
    './config': { config: {} }, './storage': storage(), './http': {},
  }, { Date: Clock });
  const client = new TraktClient();
  client.token = { access_token: 'test-user-one', refresh_token: 'refresh', expires_at: now + 86400000 };
  let calls = 0;
  client.trakt = async () => ++calls === 1
    ? { aired: 3, completed: 3, next_episode: null }
    : { aired: 4, completed: 3, next_episode: { season: 2, number: 4 } };
  assert.equal((await client.showProgress(123, false, 42)).next_episode, null);
  now += 60000;
  await client.showProgress(123, false, 42);
  assert.equal(calls, 1);
  now += 15 * 60000;
  assert.equal((await client.showProgress(123, false, 42)).next_episode.number, 4);
  assert.equal(calls, 2);
  await client.showProgress(123, false, 43);
  assert.equal(calls, 3);
  client.token = { ...client.token, access_token: 'test-user-two' };
  await client.showProgress(123, false, 43);
  assert.equal(calls, 4);
});

test('watched playback does not hide next episode; genuine unfinished playback still wins', () => {
  const { mergeTrackerContinueWatching } = load('lib/continueWatching.ts');
  const paused = { id: 1, mediaType: 'tv', seasonNumber: 1, episodeNumber: 9, progress: 50 };
  const next = { ...paused, episodeNumber: 10, badge: 'Up Next' };
  assert.equal(mergeTrackerContinueWatching([paused], [next], new Set(['tv:1:1:9']))[0].episodeNumber, 10);
  assert.equal(mergeTrackerContinueWatching([paused], [next], new Set())[0].episodeNumber, 9);
  assert.equal(mergeTrackerContinueWatching([paused], [next], new Set()).length, 1);
});

test('Trakt Up Next survives old watched flags after resetting progress', () => {
  const { isUnwatchedContinueWatching } = load('lib/continueWatching.ts');
  const next = { id: 1, mediaType: 'tv', seasonNumber: 1, episodeNumber: 10, badge: 'Up Next' };
  assert.equal(isUnwatchedContinueWatching(next, new Set(['tv:1:1:10', 'tv:1'])), true);
});

test('ordinary watched playback remains excluded', () => {
  const { isUnwatchedContinueWatching } = load('lib/continueWatching.ts');
  const paused = { id: 1, mediaType: 'tv', seasonNumber: 1, episodeNumber: 10 };
  assert.equal(isUnwatchedContinueWatching(paused, new Set(['tv:1:1:10'])), false);
  assert.equal(isUnwatchedContinueWatching({ id: 2, mediaType: 'movie' }, new Set(['movie:2'])), false);
  assert.equal(isUnwatchedContinueWatching(paused, new Set(['tv:1:1:9'])), true);
});

test('stale pause and old history cannot remove an authoritative next episode', () => {
  const { mergeTrackerContinueWatching, isUnwatchedContinueWatching } = load('lib/continueWatching.ts');
  const paused = { id: 1, mediaType: 'tv', seasonNumber: 1, episodeNumber: 9 };
  const next = { ...paused, episodeNumber: 10, badge: 'Up Next' };
  const keys = new Set(['tv:1:1:9', 'tv:1:1:10']);
  const result = mergeTrackerContinueWatching([paused], [next], keys)
    .filter((item) => isUnwatchedContinueWatching(item, keys));
  assert.equal(result.length, 1);
  assert.equal(result[0].episodeNumber, 10);
});

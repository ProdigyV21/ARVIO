const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const plain = value => JSON.parse(JSON.stringify(value));

test('YouTube cards preserve portable identity and restore without a local catalog registry', () => {
  const youtube = load('lib/youtubeCollection.ts', { './storage': storage() });
  const [card] = youtube.youTubeCatalogItems({ sourceRef: 'youtube:[{"id":"abcDEF12_-x","title":"A video"}]' });
  assert.equal(card.addonNativeId, 'ytv:abcDEF12_-x');
  assert.equal(card.addonNativeAddonId, 'arvio_youtube');
  const anotherDevice = load('lib/youtubeCollection.ts', { './storage': storage() });
  assert.equal(anotherDevice.isYouTubeItem(card), true);
  assert.equal(anotherDevice.youTubeStreams(card)[0].url, 'https://www.youtube.com/watch?v=abcDEF12_-x');
  assert.equal(anotherDevice.isYouTubeItem({ ...card, id: -123 }), false);
});

test('YouTube checkpoints are bounded, flush explicitly and stop at account/profile changes', async () => {
  const { createYouTubeCheckpointWriter } = load('lib/youtubePlayback.ts');
  let now = 0, current = true, fail = false;
  const saves = [];
  const write = createYouTubeCheckpointWriter(async checkpoint => {
    if (fail) throw new Error('offline');
    saves.push(plain(checkpoint));
  }, () => current, () => now);
  write(0, 100); write(2, 100); write(4, 100);
  assert.equal(saves.length, 1);
  write(5, 100, true);
  assert.equal(saves.length, 2);
  now = 60_001; write(65, 100);
  assert.equal(saves.length, 3);
  fail = true; write(70, 100, true); await new Promise(resolve => setImmediate(resolve));
  fail = false; write(70, 100, true);
  assert.equal(saves.length, 4);
  current = false; write(80, 100, true);
  assert.equal(saves.length, 4);
  current = true; write(Infinity, 100, true); write(90, NaN, true);
  assert.equal(saves.length, 4);
});

test('YouTube API resumes and records pause, completion and cleanup only after playback', () => {
  const { mountYouTubePlayer } = load('lib/youtubePlayback.ts');
  let options, destroyed = false, position = 42;
  const api = { Player: class {
    constructor(element, value) { options = value; }
    getCurrentTime() { return position; }
    getDuration() { return 100; }
    destroy() { destroyed = true; }
  } };
  const checkpoints = [];
  const mounted = mountYouTubePlayer({ api, element: {}, videoId: 'abcDEF12_-x', resumeSeconds: 42.9,
    origin: 'https://app.test', checkpoint: (...args) => checkpoints.push(args), onError() {} });
  assert.equal(options.host, 'https://www.youtube-nocookie.com');
  assert.equal(options.playerVars.start, 42);
  assert.equal(options.playerVars.origin, 'https://app.test');
  mounted.capture();
  assert.equal(checkpoints.length, 0);
  options.events.onStateChange({ data: 1 });
  mounted.capture();
  options.events.onStateChange({ data: 2 });
  position = 99; options.events.onStateChange({ data: 0 });
  assert.deepEqual(checkpoints, [[42, 100, false], [42, 100, true], [100, 100, true]]);
  mounted.destroy();
  assert.deepEqual(checkpoints.at(-1), [100, 100, true]);
  assert.equal(destroyed, true);
});

test('YouTube progress round trips through account sync with native identity and resume position', async () => {
  let payload = {};
  const cloud = load('lib/cloud.ts', {
    './storage': storage(),
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': { tmdbImageUrl: (_base, path) => path ?? '' },
    './http': { jsonRequest: async (url, request) => {
      // A backend without the single-item progress endpoint: the full-document write is used.
      if (url.endsWith('account-sync-progress')) throw Object.assign(new Error('Not found'), { status: 404 });
      if (request?.method === 'POST') { payload = JSON.parse(request.body).payload; return { accepted: true }; }
      return { payload };
    } }
  });
  const youtube = load('lib/youtubeCollection.ts', { './storage': storage() });
  const id = youtube.youTubeItemId('abcDEF12_-x');
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  await cloud.saveProgress(auth, {
    media_type: 'movie', show_tmdb_id: id, title: 'Video', progress: .42,
    position_seconds: 42, duration_seconds: 100,
    addon_native_id: 'ytv:abcDEF12_-x', addon_native_addon_id: 'arvio_youtube'
  }, 'child');
  assert.equal(payload.localContinueWatchingByProfile.child[0].addonNativeId, 'ytv:abcDEF12_-x');
  const [history] = await cloud.getContinueWatching(auth, 'child');
  assert.equal(history.position_seconds, 42);
  assert.equal(history.addon_native_addon_id, 'arvio_youtube');
  assert.deepEqual(plain(await cloud.getContinueWatching(auth, 'other')), []);
  const mappers = load('lib/mappers.ts', {
    './config': { config: {} }, './tmdb': {}, './mediaImages': { tmdbImageUrl: (_base, value) => value ?? '' }
  });
  const card = mappers.historyToItem(history);
  assert.equal(card.resumePositionSeconds, 42);
  assert.equal(youtube.isYouTubeItem(card), true);
  await cloud.saveProgress(auth, { ...history, position_seconds: 100, progress: 1 }, 'child');
  assert.deepEqual(plain(await cloud.getContinueWatching(auth, 'child')), []);
});

test('legacy progress storage does not send unsupported native identity columns', async () => {
  const cloud = load('lib/cloud.ts', {
    './storage': storage(), './config': { config: {}, hasNetlifyBackendUrl: () => false },
    './homeserver': {}, './iptv': {}, './mediaImages': {}, './http': {}
  });
  let saved;
  const auth = { session: { userId: 'legacy' }, supabase: async (_url, request) => { saved = JSON.parse(request.body); } };
  await cloud.saveProgress(auth, {
    media_type: 'movie', show_tmdb_id: 123, title: 'Legacy movie', progress: .42,
    position_seconds: 42, duration_seconds: 100, addon_native_id: null, addon_native_addon_id: null
  }, 'profile');
  assert.equal(saved.position_seconds, 42);
  assert.equal(saved.profile_id, 'profile');
  assert.equal('addon_native_id' in saved, false);
  assert.equal('addon_native_addon_id' in saved, false);
});

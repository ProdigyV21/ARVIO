const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { includeIptvContinueWatching: merge } = load('lib/continueWatching.ts');
const remote = { id: 10, title: 'Tracker show', mediaType: 'tv', seasonNumber: 2, episodeNumber: 3, activityAt: 100 };
const vod = { id: 20, title: 'IPTV movie', mediaType: 'movie', progress: 15, resumePositionSeconds: 900, durationSeconds: 6000, streamAddonId: 'iptv_xtream_vod', activityAt: 200 };

test('IPTV VOD survives fast paint being replaced by refreshed tracker results', () => {
  const fast = [vod, remote];
  const fresh = merge([remote], [vod]);
  assert.deepEqual(Array.from(fresh), fast);
  assert.deepEqual(Array.from(merge([remote], [vod])), fast);
  assert.deepEqual(Array.from(merge([], [vod])), [vod]);
});

test('matching movie and a previous episode never duplicate a tracker card', () => {
  assert.equal(merge([vod], [vod]).length, 1);
  assert.deepEqual(Array.from(merge([remote], [{ ...vod, id: 10, mediaType: 'tv', seasonNumber: 2, episodeNumber: 2 }])), [remote]);
});

test('completed, unstarted, dismissed upstream, and live items stay absent', () => {
  const excluded = [
    { ...vod, progress: 90 },
    { ...vod, id: 21, progress: 0, resumePositionSeconds: 0 },
    { ...vod, id: 22, streamAddonId: 'iptv_live' },
    { ...vod, id: -23 },
    { ...vod, id: 24, isWatched: true },
    { ...vod, id: 25, progress: 0, resumePositionSeconds: 5900 },
    { ...vod, id: 26, streamAddonId: 'torrentio' },
  ];
  assert.deepEqual(Array.from(merge([remote], excluded)), [remote]);
  assert.deepEqual(Array.from(merge([remote], [])), [remote]);
});

test('short sessions on long VOD do not disappear due to rounded percentage', () => {
  const entry = { ...vod, progress: 1, resumePositionSeconds: 90, durationSeconds: 10000 };
  assert.deepEqual(Array.from(merge([remote], [entry])), [entry, remote]);
});

test('newest local episode wins, but a completed latest session does not resurrect old progress', () => {
  const old = { ...vod, mediaType: 'tv', seasonNumber: 1, episodeNumber: 1 };
  const next = { ...old, episodeNumber: 2, activityAt: 300 };
  assert.deepEqual(Array.from(merge([remote], [old, next])), [next, remote]);
  assert.deepEqual(Array.from(merge([remote], [old, { ...next, progress: 95 }])), [remote]);
});

test('Android/cloud history mapping preserves VOD source and resume data for the final merge', () => {
  const { historyToItem } = load('lib/mappers.ts', {
    './config': { config: {} }, './mediaImages': { tmdbImageUrl: (_base, value) => value || '' }, './tmdb': {},
  });
  const entry = historyToItem({ show_tmdb_id: 20, media_type: 'movie', title: 'IPTV movie', progress: 0.15,
    position_seconds: 900, duration_seconds: 6000, stream_addon_id: 'iptv_xtream_vod', updated_at: '2026-09-07T12:00:00Z' });
  assert.equal(entry.streamAddonId, 'iptv_xtream_vod');
  assert.equal(entry.resumePositionSeconds, 900);
  assert.equal(entry.durationSeconds, 6000);
  assert.equal(merge([remote], [entry])[0].id, 20);
});

test('a saved Android IPTV movie survives cloud loading and tracker refresh in its own profile only', async () => {
  const payload = { localContinueWatchingByProfile: { 'profile-a': [{
    id: 20, mediaType: 'MOVIE', title: 'IPTV movie', progress: 15,
    resumePositionSeconds: 900, durationSeconds: 6000, streamAddonId: 'iptv_xtream_vod',
    streamKey: 'vod-source', updatedAtMs: 200,
  }], 'profile-b': [] } };
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': {}, './iptv': {}, './mediaImages': {},
    './http': { jsonRequest: async () => ({ payload }) },
  });
  const { historyToItem } = load('lib/mappers.ts', {
    './config': { config: {} }, './mediaImages': { tmdbImageUrl: (_base, value) => value || '' }, './tmdb': {},
  });
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  const history = await cloud.getContinueWatching(auth, 'profile-a');
  assert.equal(history.length, 1);
  assert.equal(history[0].stream_addon_id, 'iptv_xtream_vod');
  const refreshed = merge([remote], history.map(historyToItem));
  assert.deepEqual(Array.from(refreshed, (item) => item.id), [20, 10]);
  assert.equal(refreshed[0].resumePositionSeconds, 900);
  const otherProfile = await cloud.getContinueWatching(auth, 'profile-b');
  assert.deepEqual(Array.from(merge([remote], otherProfile.map(historyToItem))), [remote]);
});

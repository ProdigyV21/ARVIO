const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

function fixture() {
  const watchedAt = '2026-09-01T12:00:00Z';
  const state = { playback: [], seasons: new Map([[1, [1, 2, 3, 4]]]), requests: [], offline: false, future: false };
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: {} }, './sync': {}, './storage': storage(), './http': {},
    './tmdb': { tmdb: async path => {
      state.requests.push(path);
      if (state.offline) throw new Error('metadata offline');
      const season = Number(path.split('/')[3]);
      if (!season) return { seasons: Array.from(state.seasons.keys(), season_number => ({ season_number })) };
      return { episodes: (state.seasons.get(season) ?? []).map(episode_number => ({ episode_number, name: `Episode ${episode_number}`, air_date: state.future ? '2099-01-01' : '2025-01-01' })) };
    } }
  });
  const client = new SimklClient(); client.setProfile('regression'); client.setToken({ access_token: 'fake-test-token' });
  const show = { title: 'Show', ids: { tmdb: 200 } };
  const row = { show, status: 'watching', last_watched_at: watchedAt, next_to_watch: 'S01E03',
    seasons: [{ number: 1, episodes: [1, 2].map(number => ({ number, watched_at: watchedAt })) }] };
  client.loadSnapshot = async () => ({ complete: true, movies: [], shows: [row], anime: [] });
  client.simkl = async path => path === '/sync/playback' ? state.playback : {};
  const pause = (number = 2, paused_at = '2026-08-31T12:00:00Z') => {
    state.playback = [{ show, progress: 40, paused_at, episode: { season: 1, number } }];
  };
  return { client, row, state, pause };
}

test('SIMKL watched paused episode cannot suppress its real next episode', async () => {
  const h = fixture(); h.pause();
  const rows = await h.client.continueWatching();
  assert.equal(rows.length, 1); assert.equal(rows[0].episode.number, 3); assert.equal(rows[0].is_up_next, true);
});
test('SIMKL bulk completed show drops old playback without individual episode history', async () => {
  const h = fixture(); h.row.status = 'completed'; h.row.next_to_watch = null; h.row.seasons = []; h.pause();
  assert.equal((await h.client.continueWatching()).length, 0);
});
test('SIMKL null pointer is authoritative even with leftover next episode info', async () => {
  const h = fixture(); h.row.next_to_watch = null; h.row.next_to_watch_info = { season: 1, episode: 3 };
  assert.equal((await h.client.continueWatching()).length, 0);
});
test('SIMKL caught up pointer does not invent older skipped episodes', async () => {
  const h = fixture(); h.row.next_to_watch = null; h.row.seasons[0].episodes = [{ number: 4 }];
  assert.equal((await h.client.continueWatching()).length, 0);
});
test('SIMKL next episode must exist', async () => {
  const h = fixture(); h.row.next_to_watch = 'S01E99';
  assert.equal((await h.client.continueWatching()).length, 0);
});
test('SIMKL invalid paused episode cannot hide a valid next episode', async () => {
  const h = fixture(); h.pause(99);
  assert.equal((await h.client.continueWatching())[0].episode.number, 3);
});
test('SIMKL stale watched pointer advances through real episodes', async () => {
  const h = fixture(); h.row.next_to_watch = 'S01E02';
  assert.equal((await h.client.continueWatching())[0].episode.number, 3);
});
test('SIMKL respects ARVIO Cloud watched episodes', async () => {
  const h = fixture();
  assert.equal((await h.client.continueWatching(new Set(['tv:200:1:3'])))[0].episode.number, 4);
});
test('SIMKL final episode can advance into the actual next season', async () => {
  const h = fixture(); h.row.next_to_watch = 'S01E04'; h.state.seasons.set(2, [1]);
  const rows = await h.client.continueWatching(new Set(['tv:200:1:4']));
  assert.equal(rows[0].episode.season, 2); assert.equal(rows[0].episode.number, 1);
});
test('SIMKL fully watched season never invents another episode', async () => {
  const h = fixture(); h.row.next_to_watch = 'S01E04';
  assert.equal((await h.client.continueWatching(new Set(['tv:200:1:4']))).length, 0);
});
test('SIMKL later timestamped rewatch remains resumable', async () => {
  const h = fixture(); h.pause(2, '2026-09-02T12:00:00Z');
  const rows = await h.client.continueWatching();
  assert.equal(rows[0].episode.number, 2); assert.equal(rows[0].progress, 40); assert.ok(!rows[0].is_up_next);
});
test('SIMKL unknown placeholder watch date is not proof of a later rewatch', async () => {
  const h = fixture(); h.row.seasons[0].episodes[1].watched_at = '1970-01-01T00:00:01Z'; h.pause();
  assert.equal((await h.client.continueWatching())[0].episode.number, 3);
});
test('SIMKL local watched action survives delayed provider acknowledgement', async () => {
  const h = fixture(); h.row.next_to_watch = 'S01E02'; h.row.seasons[0].episodes = [{ number: 1 }]; h.pause();
  await h.client.addToHistory({ mediaType: 'tv', tmdbId: 200, season: 1, episode: 2 });
  assert.equal((await h.client.continueWatching())[0].episode.number, 3);
});
test('SIMKL future episode cannot appear as playable Up Next', async () => {
  const h = fixture(); h.state.future = true;
  assert.equal((await h.client.continueWatching()).length, 0);
});
test('SIMKL whole-show watched action immediately removes stale playback and Up Next', async () => {
  const h = fixture(); h.pause(3);
  await h.client.addToHistory({ mediaType: 'tv', tmdbId: 200 });
  assert.equal((await h.client.continueWatching()).length, 0);
});
test('SIMKL maps watched anime coordinates and dates into shared history', async () => {
  const h = fixture(); h.row.seasons = [{ number: 1, episodes: [{ number: 13, tvdb: { season: 2, episode: 1 }, watched_at: '2026-09-01T12:00:00Z' }] }];
  const rows = await h.client.watched('shows');
  assert.equal(rows[0].seasons[0].number, 2); assert.equal(rows[0].seasons[0].episodes[0].number, 1);
  assert.equal(rows[0].seasons[0].episodes[0].last_watched_at, '2026-09-01T12:00:00Z');
});
test('SIMKL anime pointer uses the mapped TVDB season', async () => {
  const h = fixture(); h.row.next_to_watch = 'E02'; h.row.next_to_watch_info = { episode: 2 }; h.row.mapped_tvdb_seasons = [2]; h.state.seasons.set(2, [1, 2]);
  h.row.seasons = [{ number: 1, episodes: [{ number: 1, tvdb: { season: 2, episode: 1 } }] }];
  const rows = await h.client.continueWatching();
  assert.equal(rows[0].episode.season, 2); assert.equal(rows[0].episode.number, 2);
});
test('SIMKL metadata failures stay failures rather than pretending the user is caught up', async () => {
  const h = fixture(); h.state.offline = true;
  await assert.rejects(h.client.continueWatching(), /metadata offline/);
});
test('SIMKL playback failures stay failures rather than pretending the user is caught up', async () => {
  const h = fixture(); h.row.next_to_watch = null; h.client.simkl = async () => { throw new Error('playback offline'); };
  await assert.rejects(h.client.continueWatching(), /playback offline/);
});
test('SIMKL cached real season data avoids repeat metadata requests', async () => {
  const h = fixture(); await h.client.continueWatching(); await h.client.continueWatching();
  assert.equal(h.state.requests.filter(path => path.includes('/season/')).length, 1);
});

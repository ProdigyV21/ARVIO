const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { episodeImdbRating, formatEpisodeAirDate, currentPlayerEpisode, playerPauseOverview } = load('lib/episodePresentation.ts');

test('IMDb episode badges never substitute a TMDB or series score', () => {
  assert.equal(episodeImdbRating({ imdbRating: '7.5', voteAverage: 9.4 }), '7.5');
  assert.equal(episodeImdbRating({ voteAverage: 9.4 }), null);
  for (const imdbRating of ['', '0', 'NaN', '-1', '11']) assert.equal(episodeImdbRating({ imdbRating }), null);
});

test('episode dates use calendar days and reject invalid dates', () => {
  assert.equal(formatEpisodeAirDate('2024-08-25'), '25 Aug 2024');
  assert.equal(formatEpisodeAirDate('2024-08-25', 'nl'), '25 aug 2024');
  for (const value of [undefined, '', '2024-02-30', 'invalid', '2024-08-25T12:00:00Z']) {
    assert.equal(formatEpisodeAirDate(value), '');
  }
});

test('pause synopsis is taken only from the exact season and episode', () => {
  const episodes = [
    { seasonNumber: 3, episodeNumber: 3, overview: 'Episode three' },
    { seasonNumber: 3, episodeNumber: 4, overview: 'Episode four' },
    { seasonNumber: 2, episodeNumber: 3, overview: 'Wrong season' },
  ];
  const show = { mediaType: 'tv', overview: 'Series synopsis' };
  assert.equal(playerPauseOverview(show, currentPlayerEpisode(episodes, 3, 3)), 'Episode three');
  assert.equal(playerPauseOverview(show, currentPlayerEpisode(episodes, 3, 4)), 'Episode four');
  assert.equal(playerPauseOverview(show, currentPlayerEpisode(episodes, 3, 5)), 'Series synopsis');
  assert.equal(currentPlayerEpisode(episodes, null, 3), undefined);
});

test('blank metadata has a safe fallback and movies keep their own synopsis', () => {
  assert.equal(playerPauseOverview({ mediaType: 'tv', overview: ' Show ' }, { overview: '  ' }), 'Show');
  assert.equal(playerPauseOverview({ mediaType: 'movie', overview: ' Movie ' }, { overview: 'Episode' }), 'Movie');
  assert.equal(playerPauseOverview(null), '');
});

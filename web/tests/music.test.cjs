const test = require('node:test');
const assert = require('node:assert/strict');
const crypto = require('node:crypto');
const { load } = require('./load.cjs');

const text = load('lib/music/text.ts');
const charts = load('lib/music/chartCatalog.ts');
const { md5Hex } = load('lib/music/md5.ts');
const deezer = load('lib/music/deezer.ts', { './chartCatalog': charts, './text': text });

const track = (title, artist, durationSec = 200, extra = {}) => ({ id: title, title, artist, album: '', albumId: null, durationSec, coverUrl: null, streamUrl: 'https://x/y', ...extra });

test('chart songs match the same song in the user sources, not a different one', () => {
  const wanted = { title: 'Blinding Lights', artist: 'The Weeknd', durationSec: 200 };
  assert.ok(text.matchScore(wanted, track('Blinding Lights', 'The Weeknd', 201)) >= text.MATCH_THRESHOLD);
  assert.ok(text.matchScore(wanted, track('Blinding Lights (Remastered)', 'The Weeknd', 200)) >= text.MATCH_THRESHOLD);
  assert.ok(text.matchScore(wanted, track('Save Your Tears', 'The Weeknd', 200)) < text.MATCH_THRESHOLD);
  assert.ok(text.matchScore(wanted, track('Blinding Lights', 'Someone Else', 320)) < text.MATCH_THRESHOLD);
  // The exact title beats a live take.
  assert.ok(text.matchScore(wanted, track('Blinding Lights', 'The Weeknd')) > text.matchScore(wanted, track('Blinding Lights - Live', 'The Weeknd')));
});

test('search puts playlists first for playlist-like queries and songs first for a song', () => {
  const result = { artists: [], albums: [], tracks: [track('Flowers', 'Miley Cyrus')], playlists: [{ id: 'p', name: 'Gym', artist: '', artistId: null, coverUrl: null }] };
  assert.equal(text.searchOrder('top gym hits', result)[0], 'playlists');
  assert.equal(text.searchOrder('nederlandse hits 90s', result)[0], 'playlists');
  assert.equal(text.searchOrder('flowers', result)[0], 'tracks');
  assert.equal(text.wantsPlaylists('Hello'), false);
  assert.deepEqual(text.rankTracks('flowers', [track('Flowers (Demo)', 'X'), track('Wildflowers', 'Y'), track('Flowers', 'Miley Cyrus')]).map((t) => t.title)[0], 'Flowers');
});

test('the home leads with the country charts, then the user sources, then the other charts', () => {
  const shelf = (key) => ({ key, albums: [] });
  const arranged = text.arrangeHome([shelf('2/charts:country'), shelf('2/charts:charts'), shelf('2/charts:a'), shelf('2/charts:b'), shelf('2/charts:c'), shelf('0/NEWEST')]);
  assert.deepEqual([...arranged.map((s) => s.key)], ['2/charts:country', '2/charts:charts', '2/charts:a', '2/charts:b', '0/NEWEST', '2/charts:c']);
});

test('chart rows follow the app language and rotate by day', () => {
  const nl = charts.chartRows('nl-NL', '2026-11-26');
  assert.equal(nl[0].title, 'Top Nederland');
  assert.equal(nl[1].query, 'Top 50 Netherlands');
  assert.ok(nl.some((row) => row.key === 'christmas'), 'Christmas rows from mid November');
  assert.ok(!nl.some((row) => row.key === 'summer'));
  const de = charts.chartRows('de-DE', '2026-11-26');
  assert.equal(de[0].title, 'Top Deutschland');
  assert.equal(charts.chartRows('xx', '2026-11-26')[0].query, 'Top USA');
  const tomorrow = charts.chartRows('nl-NL', '2026-11-27');
  assert.deepEqual(nl.slice(0, 12), tomorrow.slice(0, 12), 'pinned rows stay');
  assert.notDeepEqual(nl.slice(12).map((r) => r.key), tomorrow.slice(12).map((r) => r.key), 'the rest rotates');
  assert.deepEqual(charts.chartRows('nl-NL', '2026-11-26'), nl, 'same day, same order');
});

test('chart songs carry a match placeholder instead of a Deezer stream', () => {
  const parsed = deezer.parseTrack({ id: 7, title: 'Song', title_short: 'Song', duration: 180, artist: { name: 'Band' }, album: { title: 'LP', cover_xl: 'https://c/x.jpg' } });
  assert.equal(parsed.id, 'deezer:7');
  assert.ok(parsed.streamUrl.startsWith('arvio-music-match://'));
  const params = text.placeholderParams(parsed.streamUrl);
  assert.equal(params.get('t'), 'Song');
  assert.equal(params.get('a'), 'Band');
  assert.equal(params.get('d'), '180');
  // Android writes "+"-free URIs too, but a "+" must still read as a space.
  assert.equal(text.placeholderParams('arvio-music-match://track?t=Two+Words&a=A').get('t'), 'Two Words');
  assert.equal(deezer.parsePlaylist({ id: 9, title: 'Top', nb_tracks: 50, user: { name: 'Deezer' } }).id, 'playlist:9');
});

test('Subsonic token login uses a standard MD5', () => {
  for (const value of ['', 'secret1234abcd', 'wachtwoord✓salt']) assert.equal(md5Hex(value), crypto.createHash('md5').update(value).digest('hex'));
});

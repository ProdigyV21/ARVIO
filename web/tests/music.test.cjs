const test = require('node:test');
const assert = require('node:assert/strict');
const crypto = require('node:crypto');
const { load } = require('./load.cjs');

const text = load('lib/music/text.ts');
const charts = load('lib/music/chartCatalog.ts');
const { md5Hex } = load('lib/music/md5.ts');
const deezer = load('lib/music/deezer.ts', { './chartCatalog': charts, './text': text });
const taste = load('lib/music/taste.ts', { './text': text });

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

test('charts follow the country, not the app language', () => {
  const en = charts.chartRows('en-US', '2026-11-26', 'nl');
  assert.equal(en[0].query, 'Top Netherlands');
  assert.equal(en[1].query, 'Top 50 Netherlands');
  assert.equal(en[1].title, 'Charts');
  assert.equal(en[2].query, 'Top 40 Nederland');
  const be = charts.chartRows('nl', '2026-11-26', 'BE');
  assert.equal(be[0].query, 'Top Belgium');
  assert.equal(be[0].title, 'Top België');
  assert.equal(charts.chartRows('en', '2026-11-26', 'GR')[0].query, 'Top Greece');
  assert.equal(charts.chartCountry('nl-BE', null), 'BE');
  assert.equal(charts.chartCountry('xx', null), 'US');
  assert.equal(charts.browserCountry('Europe/Amsterdam', ['en-US']), 'NL');
  assert.equal(charts.browserCountry('UTC', ['nl', 'nl-BE']), 'BE');
  assert.equal(charts.browserCountry(undefined, ['en']), null);
});

test('taste keeps played artists and settled searches, and the personal rows lead', () => {
  const t0 = 1_700_000_000_000, day = 86_400_000;
  let t = taste.emptyTaste();
  t = taste.recordPlay(t, 'Old Band', t0 - 120 * day);
  t = taste.recordPlay(t, 'Dua Lipa feat. DaBaby', t0);
  t = taste.recordPlay(t, 'Dua Lipa', t0 + 1);
  t = taste.recordPlay(t, 'Simon & Garfunkel', t0 + 2);
  assert.deepEqual([...t.artists.map((a) => a.name)], ['Dua Lipa', 'Simon & Garfunkel', 'Old Band']);
  assert.equal(t.artists[0].plays, 2);
  assert.equal(taste.artistsForToday(t, t0 + day, 20000)[0], 'Dua Lipa');
  for (const q of ['du', 'dua', 'dua lipa', 'top gym hits']) t = taste.recordSearch(t, q, t0);
  assert.deepEqual([...taste.recentSearches(t)], ['top gym hits', 'dua lipa']);
  // Same JSON shape as the Android app's Gson.
  assert.equal(JSON.stringify(taste.parseTaste(JSON.stringify(t))), JSON.stringify(t));
  assert.equal(JSON.stringify(taste.parseTaste('{"artists":[null,{"name":"X"}]}').artists), '[{"name":"X","plays":1,"lastPlayed":0}]');
  const shelf = (key) => ({ key, albums: [] });
  const arranged = text.arrangeHome([shelf('2/charts:country'), shelf('2/charts:charts'), shelf('2/charts:you:artist:7'), shelf('2/charts:you:searches'), shelf('2/charts:a'), shelf('2/charts:b'), shelf('2/charts:c'), shelf('0/NEWEST')]);
  assert.equal(arranged[6].key, '0/NEWEST');
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

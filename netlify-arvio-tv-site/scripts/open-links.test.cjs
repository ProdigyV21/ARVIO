const test = require('node:test');
const assert = require('node:assert/strict');
const { parse, destinations } = require('../open/links.js');

test('accepts IMDb, TMDB movies, series and specials without guessing identity', () => {
  assert.deepEqual(parse('?imdb=tt0137523'), { imdb: 'tt0137523' });
  assert.deepEqual(parse('?type=movie&id=550'), { type: 'movie', id: 550 });
  assert.deepEqual(parse('?type=tv&id=1399&season=0&episode=1'), { type: 'tv', id: 1399, season: 0, episode: 1 });
  assert.deepEqual(parse('?imdb=tt0944947&season=&episode='), { imdb: 'tt0944947' });
  assert.deepEqual(parse('?type=tv&id=1399&season=1'), { type: 'tv', id: 1399, season: 1 });
});

test('rejects invalid, ambiguous and duplicate identities and coordinates', () => {
  const invalid = ['', '?imdb=', '?imdb=TT0137523', '?imdb=tt1', '?type=series&id=1', '?type=movie&id=0', '?type=movie&id=01', '?type=movie&id=2147483648', '?type=tv&id=-1', '?type=tv&id=1e2', '?type=movie&id=550&season=1', '?type=tv&id=1&episode=1', '?type=tv&id=1&season=-1', '?type=tv&id=1&season=1&episode=0', '?type=tv&id=1&season=10001', '?imdb=tt0137523&id=550', '?imdb=tt0137523&type=movie', '?imdb=tt0137523&imdb=tt0137523', '?type=tv&type=movie&id=1', '?type=tv&id=1&season=1&season=2', '?imdb=%3Cscript%3Ealert(1)%3C/script%3E'];
  for (const query of invalid) assert.equal(parse(query), null, query);
  assert.equal(parse('?'.repeat(4097)), null);
});

test('destinations stay on fixed origins, drop unknown parameters and encode store attribution', () => {
  const links = destinations('?imdb=tt0137523&utm_source=simkl&redirect=https://evil.invalid&token=private');
  assert.equal(links.native, 'arvio://open?imdb=tt0137523');
  assert.equal(links.web, 'https://web.arvio.tv/?open=1&imdb=tt0137523&utm_source=simkl&utm_medium=integration&utm_campaign=open_in_arvio');
  assert.ok(!links.web.includes('private'));
  assert.ok(!links.web.includes('evil.invalid'));
  const install = new URL(links.install);
  assert.equal(install.origin, 'https://play.google.com');
  assert.equal(install.searchParams.get('id'), 'com.arvio.tv');
  assert.equal(new URLSearchParams(install.searchParams.get('referrer')).get('utm_source'), 'simkl');
  assert.ok(links.androidIntent.startsWith('intent://open?imdb=tt0137523#Intent;scheme=arvio;package=com.arvio.tv;'));
  assert.ok(links.androidIntent.endsWith(';end'));
  assert.ok(!links.androidIntent.includes('private'));
  assert.equal(new URLSearchParams(new URL(destinations('?imdb=tt0137523&utm_source=%3Bbad').install).searchParams.get('referrer')).get('utm_source'), 'partner');
  assert.equal(new URL(destinations('?imdb=tt0137523&utm_source=%3Bbad').web).searchParams.get('utm_source'), 'partner');
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

test('malformed service names do not crash details or produce bogus logos', () => {
  const { canonicalServiceName, serviceClearLogo } = load('lib/serviceLogos.ts');
  for (const value of [null, undefined, 123, false, {}, [], { name: 'Netflix' }]) {
    assert.equal(canonicalServiceName(value), '');
    assert.equal(serviceClearLogo(value), null);
  }
  assert.equal(canonicalServiceName('  Netflix  '), 'Netflix');
  assert.equal(serviceClearLogo('Amazon Prime'), '/logos/logo_prime_video.svg');
});

test('invalid IMDb IDs are ignored without requesting ratings or crashing cards', async () => {
  let requests = 0;
  const ratings = load('lib/imdbRatings.ts', { './storage': storage(), './config': { config: { resolverUrl: '' } } }, {
    fetch: async () => { requests++; throw Error('Unexpected request'); }
  });
  const invalid = [undefined, null, 12345, false, {}, [], { id: 'tt123' }];
  for (const id of invalid) {
    assert.equal(ratings.peekImdbRating(id), null);
    assert.equal(await ratings.getImdbRating('movie', id), null);
  }
  assert.equal(JSON.stringify(await ratings.getImdbRatings(invalid)), '{}');
  assert.equal(requests, 0);
  assert.equal(ratings.peekImdbRating(' TT123 '), undefined);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { readableReleaseName, dedupeReleases } = load('lib/releaseName.ts');
const plain = (value) => value && JSON.parse(JSON.stringify(value));

test('release file names become readable card titles', () => {
  assert.deepEqual(plain(readableReleaseName('Drishyam-The.Conclusion.2026.Hindi.1080p.WEB-DL.x264')), { title: 'Drishyam The Conclusion', year: '2026' });
  assert.deepEqual(plain(readableReleaseName('Project Hail Mary 2026 IMAX 2160p WEB-DL')), { title: 'Project Hail Mary', year: '2026' });
  assert.deepEqual(plain(readableReleaseName('The.Office.S03.1080p.BluRay')), { title: 'The Office', season: 3 });
});

test('normal titles are left alone', () => {
  for (const title of ['Blade Runner 2049', '1917', 'Mr. Robot', 'Dune: Part Two', 'Spider-Man: No Way Home', 'Se7en']) {
    assert.equal(readableReleaseName(title), null, title);
  }
});

test('series releases drop the episode marker and fansub group from the title', () => {
  assert.deepEqual(plain(readableReleaseName('Cupertino.S01E08.Judith.and.Michael.1080p.WEB-DL')), { title: 'Cupertino', season: 1, episode: 8 });
  assert.deepEqual(plain(readableReleaseName('[SubsPlease] Ace of Diamond Act II S2 (01-13) (1080p) [Batch]')), { title: 'Ace of Diamond Act II', season: 2 });
  assert.deepEqual(plain(readableReleaseName('The K2 S01 KOREAN 1080p')), { title: 'The K2', season: 1 });
});

test('a debrid library lists the same film once, but keeps different episodes', () => {
  const items = [
    { title: 'The.Matrix.1999.2160p.UHD.BluRay.REMUX', mediaType: 'movie', addonNativeId: 'a' },
    { title: 'The Matrix 1999 1080p BluRay x264', mediaType: 'movie', addonNativeId: 'b' },
    { title: 'Show.S01E01.1080p.WEB-DL', mediaType: 'tv', addonNativeId: 'c' },
    { title: 'Show.S01E02.1080p.WEB-DL', mediaType: 'tv', addonNativeId: 'd' },
    { title: 'The Matrix', mediaType: 'movie' },
    { title: 'The Matrix', mediaType: 'movie' }
  ];
  assert.deepEqual(dedupeReleases(items).map((item) => item.addonNativeId ?? 'tmdb'), ['a', 'c', 'd', 'tmdb', 'tmdb']);
});

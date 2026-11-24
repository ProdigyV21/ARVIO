const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { readableReleaseName } = load('lib/releaseName.ts');
const plain = (value) => value && JSON.parse(JSON.stringify(value));

test('release file names become readable card titles', () => {
  assert.deepEqual(plain(readableReleaseName('Drishyam-The.Conclusion.2026.Hindi.1080p.WEB-DL.x264')), { title: 'Drishyam The Conclusion', year: '2026' });
  assert.deepEqual(plain(readableReleaseName('Project Hail Mary 2026 IMAX 2160p WEB-DL')), { title: 'Project Hail Mary', year: '2026' });
  assert.deepEqual(plain(readableReleaseName('The.Office.S03.1080p.BluRay')), { title: 'The Office S03' });
});

test('normal titles are left alone', () => {
  for (const title of ['Blade Runner 2049', '1917', 'Mr. Robot', 'Dune: Part Two', 'Spider-Man: No Way Home', 'Se7en']) {
    assert.equal(readableReleaseName(title), null, title);
  }
});

const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { compareLibraryItems, LIBRARY_SORT_OPTIONS } = load('lib/librarySort.ts');

test('Release sort differs from recently added; partial and invalid dates are handled', () => {
  const rows = [
    { id: 1, title: 'Old film added today', releaseDate: '1980-12-10', activityAt: 999999 },
    { id: 2, title: 'New release', releaseDate: '2025-12-10T00:00:00Z' },
    { id: 3, title: 'Earlier same year', releaseDate: '2025-02-01' },
    { id: 4, title: 'Year only', year: '2020' },
    { id: 5, title: 'Unknown' },
    { id: 6, title: 'Invalid', releaseDate: '2025-02-30' }
  ];
  const sorted = (mode) => [...rows].sort((a, b) => compareLibraryItems(a, b, mode)).map((row) => row.id);
  assert.deepEqual(sorted('release-newest'), [2, 3, 4, 1, 6, 5]);
  assert.deepEqual(sorted('release-oldest'), [1, 4, 3, 2, 6, 5]);
  assert.equal(sorted('added')[0], 1);
  assert.equal(LIBRARY_SORT_OPTIONS.length, 5);
});

test('Release sort falls back to year and keeps equal dates in stable title order', () => {
  const rows = [{ id: 1, title: 'Zulu', year: '2024', releaseDate: 'invalid' }, { id: 2, title: 'Alpha', releaseDate: '2024' }];
  for (const mode of ['release-newest', 'release-oldest']) {
    assert.deepEqual([...rows].sort((a, b) => compareLibraryItems(a, b, mode)).map((row) => row.id), [2, 1]);
  }
});

for (const type of ['plex', 'jellyfin', 'emby']) {
  for (const [mode, descending] of [['release-newest', true], ['release-oldest', false]]) {
    test(`${type} requests ${mode} for both pages, retaining search and library filters`, async () => {
      const requests = [];
      const module = load('lib/homeserver.ts', { './http': {
        proxiedUrl: (url) => url,
        jsonRequest: async (url) => {
          const query = new URL(url).searchParams;
          requests.push(query);
          const offset = Number(query.get(type === 'plex' ? 'X-Plex-Container-Start' : 'StartIndex'));
          const rows = Array.from({ length: 60 }, (_, i) => type === 'plex'
            ? { ratingKey: String(offset + i), title: `Film ${offset + i}`, type: 'movie', originallyAvailableAt: '2025-06-01' }
            : { Id: String(offset + i), Name: `Film ${offset + i}`, Type: 'Movie', PremiereDate: '2025-06-01T00:00:00Z' });
          return type === 'plex' ? { MediaContainer: { Metadata: rows, totalSize: 14255 } } : { Items: rows, TotalRecordCount: 14255 };
        }
      } });
      const server = { id: 'server', type, name: 'Fixture', url: 'https://media.invalid', enabled: true, token: 'fixture', userId: 'user' };
      for (const offset of [0, 60]) {
        const page = await module.loadHomeServerLibraryPage([server], 'hslib:server:library', { sort: mode, offset, limit: 60, search: 'Film', libraryMediaType: 'movie', throwOnError: true });
        assert.equal(page.items.length, 60);
        assert.equal(page.total, 14255);
        assert.equal(page.hasMore, true);
        assert.ok(page.items[0].releaseDate.startsWith('2025-06-01'));
        const query = requests.at(-1);
        if (type === 'plex') {
          assert.equal(query.get('sort'), `originallyAvailableAt:${descending ? 'desc' : 'asc'}`);
          assert.equal(query.get('X-Plex-Container-Start'), String(offset));
          assert.equal(query.get('X-Plex-Container-Size'), '60');
          assert.equal(query.get('type'), '1');
          assert.equal(query.get('title'), 'Film');
        } else {
          assert.equal(query.get('SortBy'), 'PremiereDate');
          assert.equal(query.get('SortOrder'), descending ? 'Descending' : 'Ascending');
          assert.equal(query.get('StartIndex'), String(offset));
          assert.equal(query.get('Limit'), '60');
          assert.equal(query.get('ParentId'), 'library');
          assert.equal(query.get('SearchTerm'), 'Film');
          assert.equal(query.get('IncludeItemTypes'), 'Movie');
        }
      }
      assert.equal(requests.length, 2);
    });
  }
}

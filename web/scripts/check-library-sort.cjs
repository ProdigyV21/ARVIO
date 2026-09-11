const { chromium } = require('playwright');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

(async () => {
  const browser = await chromium.launch({ channel: 'msedge', headless: true });
  const out = path.resolve('../artifacts/library-sort');
  fs.mkdirSync(out, { recursive: true });
  try {
    for (const width of [1440, 390]) {
      const page = await browser.newPage({ viewport: { width, height: 900 } });
      await page.route('**/api/tmdb/**', (route) => route.fulfill({ json: { results: [] } }));
      const requests = [];
      await page.route('**/api/proxy?**', async (route) => {
        const target = new URL(new URL(route.request().url()).searchParams.get('url'));
        if (!target.hostname.endsWith('.invalid')) return route.continue();
        const plex = target.hostname === 'plex.invalid';
        if (target.pathname === '/library/sections' || target.pathname.endsWith('/Views')) {
          return route.fulfill({ json: plex ? { MediaContainer: { Directory: [{ key: 'movies', title: '4K Movies', type: 'movie' }] } } : { Items: [{ Id: 'movies', Name: '4K Movies', CollectionType: 'movies' }] } });
        }
        requests.push(target);
        const query = target.searchParams;
        const asc = plex ? query.get('sort')?.endsWith(':asc') : query.get('SortOrder') === 'Ascending';
        const offset = Number(query.get(plex ? 'X-Plex-Container-Start' : 'StartIndex'));
        const limit = Number(query.get(plex ? 'X-Plex-Container-Size' : 'Limit'));
        const rows = Array.from({ length: 140 }, (_, i) => ({ id: i, date: `${1900 + i}-06-01`, title: `Release ${1900 + i}` }));
        if (!asc) rows.reverse();
        const items = rows.slice(offset, offset + limit).map((row) => plex
          ? { ratingKey: String(row.id), title: row.title, type: 'movie', year: Number(row.date.slice(0, 4)), originallyAvailableAt: row.date }
          : { Id: String(row.id), Name: row.title, Type: 'Movie', ProductionYear: Number(row.date.slice(0, 4)), PremiereDate: row.date });
        return route.fulfill({ json: plex ? { MediaContainer: { Metadata: items, totalSize: 140 } } : { Items: items, TotalRecordCount: 140 } });
      });
      await page.goto('http://127.0.0.1:3035/dev/stabilization');
      await page.locator('[data-fixture-ready="true"]').waitFor();
      await page.getByRole('button', { name: 'Test home server libraries' }).click();
      for (const provider of ['Jellyfin', 'Plex', 'Emby']) {
        await page.getByRole('button', { name: provider, exact: true }).click();
        const sort = page.getByRole('combobox', { name: 'Sort titles' });
        for (const [value, first] of [['release-newest', 'Release 2039'], ['release-oldest', 'Release 1900']]) {
          await sort.selectOption(value);
          await page.waitForFunction((title) => document.querySelector('.library-grid')?.textContent?.trim().startsWith(title), first);
          const option = await sort.locator('option:checked').textContent();
          assert.match(option, /Release date:/);
          assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > window.innerWidth + 1), false);
        }
        await page.screenshot({ path: path.join(out, `${provider.toLowerCase()}-${width}.png`), fullPage: false });
      }
      await page.getByRole('button', { name: 'Watchlist', exact: true }).click();
      await page.getByRole('combobox', { name: 'Sort titles' }).selectOption('release-newest');
      await page.getByRole('button', { name: 'Simkl', exact: true }).click();
      await page.getByRole('combobox', { name: 'Sort titles' }).selectOption('release-oldest');
      assert.ok(requests.some((url) => url.searchParams.get('SortBy') === 'PremiereDate'));
      assert.ok(requests.some((url) => url.searchParams.get('sort') === 'originallyAvailableAt:asc'));
      console.log(`PASS ${width}px: home server menus, both release directions, watchlist and tracker options`);
      await page.close();
    }
  } finally { await browser.close(); }
})().catch((error) => { console.error(error); process.exitCode = 1; });

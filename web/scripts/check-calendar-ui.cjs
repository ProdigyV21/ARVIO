/* Production Calendar and loader, deterministic local provider/metadata adapters. No account access. */
const path = require('node:path');
const fs = require('node:fs');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');
(async () => {
  const root = path.resolve(__dirname, '..');
  const out = path.join(root, '.calendar-ui-test'); fs.mkdirSync(out, { recursive: true });
  const stub = path.join(root, 'tests/calendar-ui/stubs.ts');
  await esbuild.build({ entryPoints: [path.join(root, 'tests/library-ui/entry.tsx')], bundle: true, outfile: path.join(out, 'app.js'), jsx: 'automatic', nodePaths: (process.env.NODE_PATH || '').split(path.delimiter).filter(Boolean), define: { 'process.env.NODE_ENV': '"test"', 'process.env': '{}' }, plugins: [{ name: 'offline-calendar-adapters', setup(build) {
    build.onResolve({ filter: /^@\/lib\/(store|tmdb|imdbRatings|homeserver|cloud|watchlistOutbox|simkl|mdblist|mappers)$/ }, () => ({ path: stub }));
    build.onResolve({ filter: /^\.\/tmdb$/ }, args => args.importer.endsWith('calendarLoader.ts') ? { path: stub } : undefined);
    build.onResolve({ filter: /^@\// }, args => ({ path: ['.tsx', '.ts', '.js', '/index.tsx', '/index.ts', ''].map(ext => path.join(root, args.path.slice(2) + ext)).find(file => fs.existsSync(file)) }));
  } }] });
  const fixture = path.resolve(root, '../app/src/androidTest/assets/library');
  const server = http.createServer((req, res) => {
    const file = req.url.split('?')[0];
    if (file === '/') { res.setHeader('content-type', 'text/html'); res.end('<html><head><meta name="viewport" content="width=device-width,initial-scale=1"/><link rel="stylesheet" href="/globals.css"/><link rel="stylesheet" href="/calendar.css"/><style>.library-test-header{z-index:100;height:74px;position:absolute;top:0;left:0;right:0;display:flex;align-items:center;justify-content:space-between;padding:0 3vw;background:#000;color:#fff}.library-test-header nav,.library-test-header span{display:flex;gap:12px;align-items:center}.library-test-header nav{gap:32px}.library-test-header .active{background:#242426;border-radius:28px;padding:14px 24px}.library-test-avatar{background:#242426;border-radius:50%;padding:12px}.library-test-header svg{width:22px}@media(max-width:650px){.library-test-header{display:none}}</style></head><body><div id="root"></div><script src="/app.js"></script></body></html>'); return; }
    const location = file === '/app.js' ? path.join(out, 'app.js') : file === '/globals.css' || file === '/calendar.css' ? path.join(root, 'app', file) : file.startsWith('/fixtures/') ? path.join(fixture, path.basename(file)) : path.join(root, 'public', file);
    if (!fs.existsSync(location)) { res.statusCode = 404; res.end(); return; }
    res.setHeader('content-type', file.endsWith('.css') ? 'text/css' : file.endsWith('.js') ? 'application/javascript' : file.endsWith('.png') ? 'image/png' : file.endsWith('.jpg') ? 'image/jpeg' : file.endsWith('.svg') ? 'image/svg+xml' : 'application/octet-stream'); fs.createReadStream(location).pipe(res);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  const url = `http://127.0.0.1:${server.address().port}`;
  const evidence = path.resolve(root, '../artifacts/calendar'); fs.mkdirSync(evidence, { recursive: true });
  try {
    for (const [name, width, height] of [['web-tv', 1672, 940], ['web-tv-720', 1280, 720], ['web-tv-1080', 1920, 1080], ['web-tablet', 1024, 768], ['web-phone', 390, 844]]) {
      const page = await browser.newPage({ viewport: { width, height }, timezoneId: 'Europe/Amsterdam' });
      const errors = []; page.on('pageerror', error => { errors.push(error.message); console.error('Fixture page error:', error.message); });
      await page.route('**/*', route => route.request().url().startsWith(url) ? route.continue() : route.abort());
      await page.clock.setFixedTime(new Date('2026-10-16T12:00:00Z'));
      await page.goto(url); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
      await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
      await page.locator('.calendar-release-logo').first().waitFor();
      assert.equal(await page.locator('[role=row]').count(), 5);
      assert.equal(await page.locator('[role=gridcell]').count(), 35);
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, 'No page overflow');
      if (width >= 1100) assert.ok((await page.locator('.calendar-release-card>small').first().boundingBox()).y + (await page.locator('.calendar-release-card>small').first().boundingBox()).height <= height, 'TV viewport includes full release caption and provider');
      if (width >= 1100) {
        const header = await page.locator('.library-test-header').boundingBox();
        const tabs = await page.locator('.oled-library-toolbar').boundingBox();
        assert.ok(tabs.y >= header.y + header.height && tabs.y - (header.y + header.height) <= 2, 'Calendar sits close below the unchanged topbar');
      }
      if (width > 650) {
        const day = page.locator('[data-calendar-date="2026-10-16"]');
        assert.equal(await day.locator('.calendar-day-poster').count(), 3);
        assert.equal(await day.locator('.calendar-poster-more').textContent(), '+6');
        for (const poster of await page.locator('.calendar-day-poster').all()) {
          const image = await poster.boundingBox();
          const cell = await poster.locator('xpath=ancestor::button').boundingBox();
          assert.ok(image && image.x >= cell.x && image.x + image.width <= cell.x + cell.width
            && image.y >= cell.y && image.y + image.height <= cell.y + cell.height, 'Every poster fits within its date');
        }
      }
      assert.equal(await page.locator('.calendar-timezone,.calendar-refresh,.calendar-today').count(), 0, 'Calendar toolbar contains only month and source controls');
      assert.ok((await page.locator('.calendar-release-row').textContent()).includes('Time TBA'));
      assert.ok((await page.locator('.calendar-release-row').textContent()).includes('21:00'));
      assert.equal(await page.locator('[aria-current=date]').getAttribute('data-calendar-date'), '2026-10-16');
      for (const control of await page.locator('.calendar-toolbar button,.calendar-toolbar select,.oled-calendar-screen .oled-library-toolbar nav button').all()) {
        const box = await control.boundingBox(); assert.ok(box.width >= 44 && box.height >= 44, 'Toolbar controls have 44px touch targets');
      }
      if (width < 650) assert.equal(await page.locator('.calendar-source-legend').isVisible(), false, 'Mobile does not repeat provider legend');
      await page.screenshot({ path: path.join(evidence, `${name}.png`) });
      const selected = page.locator('[data-calendar-date="2026-10-16"]'); await selected.focus();
      await page.keyboard.press('ArrowRight');
      await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-17');
      await page.keyboard.press('ArrowLeft'); await page.keyboard.press('Enter');
      await page.waitForFunction(() => document.activeElement?.classList.contains('calendar-release-card'));
      await page.keyboard.press('Escape');
      await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-16');
      await page.locator('.calendar-release-card').first().click();
      assert.ok(await page.evaluate(() => Boolean(window.openedLibraryItem?.id)), 'Release opens real media details');
      await page.getByLabel('Calendar watchlist source').selectOption('arvio');
      assert.equal(await page.locator('.calendar-release-card').count(), 3);
      if (width > 650) {
        assert.equal(await page.locator('.calendar-day.is-selected .calendar-day-poster').count(), 3);
        assert.equal(await page.locator('.calendar-day.is-selected .calendar-poster-more').count(), 0, 'Exactly three releases need no overflow marker');
      }
      assert.equal(await page.locator('.calendar-source-legend').count(), 0, 'Filtered source is already named by its selector');
      await page.locator('[data-calendar-date="2026-10-17"]').click();
      assert.equal(await page.locator('[data-calendar-date="2026-10-16"]').getAttribute('aria-current'), 'date', 'Today marker survives selection change');
      await page.screenshot({ path: path.join(evidence, `${name}-empty-day.png`) });
      await page.keyboard.press('Enter');
      await page.waitForFunction(() => document.activeElement?.classList.contains('calendar-next-release'));
      await page.keyboard.press('ArrowUp');
      await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-17');
      await page.keyboard.press('Enter'); await page.keyboard.press('Enter');
      assert.equal(await page.locator('.calendar-day.is-selected').getAttribute('data-calendar-date'), '2026-10-20', 'Next release respects ARVIO source, skipping another source on October 18');
      await page.locator('[data-calendar-date="2026-10-31"]').click();
      assert.equal(await page.getByRole('button', { name: 'Next release', exact: true }).count(), 0, 'No automatic month advance or wrap to past dates');
      await page.getByRole('button', { name: 'Next month', exact: true }).click();
      assert.match(await page.locator('.calendar-month-controls h1').textContent(), /November 2026/);
      assert.equal(await page.locator('.calendar-day.is-selected').getAttribute('data-calendar-date'), '2026-11-30', 'Toolbar month switch clamps day 31 to day 30');
      await page.locator('.calendar-day.is-selected').focus();
      await page.keyboard.press('PageDown');
      await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-12-30');
      await page.keyboard.press('PageUp');
      await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-11-30');
      await page.goto(url + '/?arvio=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
      await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
      assert.equal(await page.getByLabel('Calendar watchlist source').locator('option').count(), 2);
      assert.equal(await page.locator('.calendar-release-card').count(), 3, 'ARVIO-only source loads cloud releases');
      await page.screenshot({ path: path.join(evidence, `${name}-arvio-only.png`) });
      await page.goto(url + '/?month=2026-11&arvio=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
      await page.getByRole('button', { name: 'Next month', exact: true }).click();
      await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
      assert.equal(await page.locator('[role=row]').count(), 6); assert.equal(await page.locator('[role=gridcell]').count(), 42);
      assert.equal(await page.locator('.calendar-day.is-selected').getAttribute('data-calendar-date'), '2026-11-16', 'Day number preserved');
      assert.equal(await page.locator('.calendar-release-card').count(), 3);
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false);
      if (width >= 1100) assert.ok((await page.locator('.calendar-release-card>small').first().boundingBox()).y + (await page.locator('.calendar-release-card>small').first().boundingBox()).height <= height, 'Six-week TV month retains full release caption and provider');
      await page.screenshot({ path: path.join(evidence, `${name}-six-week.png`) });
      assert.deepEqual(errors, []); await page.close(); console.log(`${name}: passed`);
    }
    const page = await browser.newPage(); await page.clock.setFixedTime(new Date('2026-10-16T12:00:00Z'));
    await page.goto(url + '/?partial=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-partial').waitFor(); assert.match(await page.locator('.calendar-partial').textContent(), /MDBList/);
    assert.ok(await page.locator('.calendar-release-card').count(), 'One unavailable source does not erase other releases');
    await page.goto(url + '/?arvio=1&empty=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-month-grid[aria-busy=false]').waitFor(); assert.match(await page.locator('.calendar-empty').textContent(), /Add movies and series/);
    await page.goto(url + '/?empty=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
    assert.ok(await page.locator('.calendar-release-card').count());
    await page.getByLabel('Calendar watchlist source').selectOption('arvio');
    assert.match(await page.locator('.calendar-empty').textContent(), /this watchlist/);
    assert.equal(await page.getByRole('button', { name: 'Next release', exact: true }).count(), 0);
    await page.evaluate(() => localStorage.clear());
    await page.goto(url + '/?partial=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
    await page.getByLabel('Calendar watchlist source').selectOption('mdblist');
    assert.match(await page.locator('.calendar-empty').textContent(), /Calendar unavailable/);
    await page.locator('.calendar-day.is-selected').focus(); await page.keyboard.press('Enter');
    await page.waitForFunction(() => document.activeElement?.textContent === 'Retry');
    await page.keyboard.press('Escape');
    await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-16');
    await page.evaluate(() => localStorage.clear());
    await page.goto(url + '/?loading=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-month-grid[aria-busy=true]').waitFor();
    assert.match(await page.locator('.calendar-selected-heading').textContent(), /Finding your releases/);
    assert.equal(await page.locator('.calendar-empty').count(), 0);
    assert.equal(await page.locator('.calendar-day[aria-label*="0 releases"]').count(), 0, 'Loading dates never announce zero releases');
    assert.equal(await page.getByRole('button', { name: 'Next release', exact: true }).count(), 0);
    await page.emulateMedia({ reducedMotion: 'reduce' });
    assert.equal(await page.locator('.library-calendar').evaluate(el => getComputedStyle(el).scrollBehavior), 'auto');
    await page.evaluate(() => window.finishCalendarLoading());
    await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
    await page.evaluate(() => { const original = HTMLElement.prototype.scrollIntoView; HTMLElement.prototype.scrollIntoView = function (options) { window.lastCalendarScrollBehavior = options?.behavior; original.call(this, options); }; });
    await page.locator('.calendar-release-card').first().focus(); await page.keyboard.press('ArrowRight');
    assert.equal(await page.evaluate(() => window.lastCalendarScrollBehavior), 'auto', 'Remote navigation respects reduced motion');
    await page.evaluate(() => { document.documentElement.dir = 'rtl'; });
    await page.locator('[data-calendar-date="2026-10-16"]').focus();
    await page.keyboard.press('ArrowLeft');
    await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-17');
    await page.keyboard.press('ArrowRight');
    await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-16');
    await page.keyboard.press('Home');
    await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-12');
    await page.keyboard.press('End');
    await page.waitForFunction(() => document.activeElement?.getAttribute('data-calendar-date') === '2026-10-18');
    assert.match(await page.locator('.calendar-day-art').first().evaluate(el => getComputedStyle(el).maskImage), /to left/, 'RTL art leaves the text side dark');
    await page.screenshot({ path: path.join(evidence, 'web-rtl.png') });
    await page.goto(url + '/?loading=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-month-grid[aria-busy=true]').waitFor();
    assert.ok(await page.locator('.calendar-release-card').count(), 'Saved month restores immediately even while all metadata requests are held');
    await page.screenshot({ path: path.join(evidence, 'web-cached-immediate.png') });
    await page.evaluate(() => { window.finishCalendarLoading(); localStorage.clear(); });
    await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
    await page.evaluate(() => localStorage.clear());
    await page.goto(url + '/?slowtracker=1&slowtime=1'); await page.getByRole('button', { name: 'Calendar', exact: true }).click();
    await page.locator('.calendar-release-card').first().waitFor();
    assert.equal(await page.locator('.calendar-month-grid').getAttribute('aria-busy'), 'true');
    assert.match(await page.locator('.calendar-release-row').textContent(), /Time TBA/);
    await page.getByLabel('Calendar watchlist source').selectOption('arvio');
    await page.locator('.calendar-month-grid[aria-busy=false]').waitFor();
    assert.equal(await page.locator('.calendar-release-card').count(), 3, 'ARVIO dates render without waiting for tracker or exact times');
    await page.locator('[data-calendar-date="2026-10-17"]').click();
    await page.getByRole('button', { name: 'Next release', exact: true }).click();
    assert.equal(await page.locator('.calendar-day.is-selected').getAttribute('data-calendar-date'), '2026-10-20');
    await page.evaluate(() => { window.finishCalendarTracker(); window.finishCalendarTimes(); });
    await page.waitForFunction(() => document.querySelector('.calendar-release-row')?.textContent.includes('ARVIO + Trakt'));
    assert.equal(await page.locator('.calendar-day.is-selected').getAttribute('data-calendar-date'), '2026-10-20', 'Progressive updates preserve selection');
    assert.match(await page.locator('.calendar-release-row').textContent(), /21:00/);
    assert.match(await page.locator('.calendar-release-row').textContent(), /ARVIO \+ Trakt/);
    await page.close(); console.log('partial, empty-source, pending-load, reduced-motion and RTL states: passed');
  } finally { await browser.close(); await new Promise(resolve => server.close(resolve)); }
})().catch(error => { console.error(error); process.exitCode = 1; });

/* Render the real rails/details with the exported APK defaults and offline title data. */
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');

(async () => {
  const root = path.resolve(__dirname, '..');
  const output = path.resolve(root, '../artifacts/default-collections');
  fs.mkdirSync(output, { recursive: true });
  const stub = path.join(root, 'tests/default-collections-ui/stubs.ts');
  await esbuild.build({ entryPoints: [path.join(root, 'tests/default-collections-ui/entry.tsx')],
    bundle: true, outfile: path.join(output, 'app.js'), jsx: 'automatic',
    define: { 'process.env.NODE_ENV': '"test"', 'process.env': '{}' },
    plugins: [{ name: 'offline-adapters', setup(build) {
      build.onResolve({ filter: /^@\/lib\/(store|tmdb|imdbRatings)$/ }, () => ({ path: stub }));
      build.onResolve({ filter: /^\.\/tmdb$/ }, args => args.importer.endsWith('collectionLoader.ts') ? { path: stub } : undefined);
      build.onResolve({ filter: /^@\// }, args => ({ path: ['.tsx', '.ts', '.js', '/index.tsx', '/index.ts', '']
        .map(ext => path.join(root, args.path.slice(2) + ext)).find(fs.existsSync) }));
    } }] });
  const fixture = path.resolve(root, '../app/src/androidTest/assets/library');
  const server = http.createServer((req, res) => {
    const url = req.url.split('?')[0];
    if (url === '/') {
      res.setHeader('content-type', 'text/html');
      res.end('<html><head><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/globals.css"></head><body><div id="root"></div><script src="/app.js"></script></body></html>');
      return;
    }
    const file = url === '/app.js' ? path.join(output, 'app.js') : url === '/globals.css' ? path.join(root, 'app/globals.css')
      : url.startsWith('/fixtures/') ? path.join(fixture, path.basename(url)) : path.join(root, 'public', url);
    if (!fs.existsSync(file)) { res.writeHead(404).end(); return; }
    res.setHeader('content-type', url.endsWith('.js') ? 'text/javascript' : url.endsWith('.css') ? 'text/css'
      : url.endsWith('.png') ? 'image/png' : url.endsWith('.svg') ? 'image/svg+xml' : 'image/jpeg');
    fs.createReadStream(file).pipe(res);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  let browser;
  try {
    browser = await chromium.launch({ channel: 'msedge', headless: true });
    for (const [name, width, height] of [['desktop', 1672, 940], ['tablet', 1024, 768], ['mobile', 390, 844]]) {
      const page = await browser.newPage({ viewport: { width, height } });
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      // Keep real configured image URLs while making rendering independent of the CDN.
      await page.route('https://image.tmdb.org/**', route => route.fulfill({
        path: path.join(fixture, fs.readdirSync(fixture).find(file => file.endsWith('.jpg'))), contentType: 'image/jpeg'
      }));
      await page.goto(`http://127.0.0.1:${server.address().port}`);
      assert.equal(await page.locator('.custom-collection-rail').count(), 5);
      assert.equal(await page.locator('.custom-collection-tile').count(), 43);
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false);
      for (let index = 0; index < 5; index++) {
        await page.locator('.custom-collection-rail').nth(index).locator('.custom-collection-tile').first().click();
        const dialog = page.getByRole('dialog');
        await dialog.locator('.media-card').first().waitFor();
        assert.equal(await dialog.evaluate(node => node.scrollWidth > node.clientWidth), false);
        if (await dialog.getByRole('tab', { name: 'Series', exact: true }).count()) {
          await dialog.getByRole('tab', { name: 'Series', exact: true }).click();
          await dialog.locator('.media-card').first().waitFor();
        }
        await dialog.locator('.media-card .poster-art.is-loaded').first().waitFor();
        if (index === 1) {
          await page.waitForTimeout(300);
          await page.screenshot({ path: path.join(output, `${name}-service.png`) });
        }
        await page.keyboard.press('Escape');
        await dialog.waitFor({ state: 'detached' });
      }
      assert.deepEqual(errors, []);
      await page.close();
      console.log(`${name}: 5 rails, 43 folders, all collection groups and tabs passed`);
    }
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
  }
})().catch(error => { console.error(error); process.exitCode = 1; });

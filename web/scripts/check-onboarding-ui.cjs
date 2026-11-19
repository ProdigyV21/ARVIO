// Runs real source setup components against an isolated offline profile.
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');
(async () => {
  const root = path.resolve(__dirname, '..');
  const result = await esbuild.build({ entryPoints: [path.join(root, 'tests/onboarding-ui/entry.tsx')], bundle: true, write: false, outdir: 'fixture', jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"' }, plugins: [{ name: 'offline-profile', setup(build) {
    build.onResolve({ filter: /^@\/lib\/store$/ }, () => ({ path: path.join(root, 'tests/onboarding-ui/store.tsx') }));
    build.onResolve({ filter: /^@\// }, args => ({ path: ['.tsx', '.ts', '/index.tsx', '/index.ts', ''].map(ext => path.join(root, args.path.slice(2) + ext)).find(file => fs.existsSync(file)) }));
  } }] });
  const js = result.outputFiles.find(file => file.path.endsWith('.js')).contents;
  const css = result.outputFiles.find(file => file.path.endsWith('.css')).contents;
  const server = http.createServer((req, res) => {
    const url = new URL(req.url, 'http://localhost');
    if (url.pathname === '/app.js' || url.pathname === '/app.css') {
      res.setHeader('Content-Type', url.pathname.endsWith('.js') ? 'text/javascript' : 'text/css');
      return res.end(url.pathname.endsWith('.js') ? js : css);
    }
    if (/^\/i18n\/[a-zA-Z-]+\.json$/.test(url.pathname)) {
      res.setHeader('Content-Type', 'application/json');
      return res.end(fs.readFileSync(path.join(root, 'public', url.pathname)));
    }
    res.setHeader('Content-Type', 'text/html');
    res.end('<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/app.css"></head><body><div id="root"></div><script src="/app.js"></script></body></html>');
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  const base = `http://127.0.0.1:${server.address().port}`;
  const evidence = path.resolve(root, '../artifacts/premium-improvements/onboarding');
  fs.mkdirSync(evidence, { recursive: true });
  try {
    for (const [name, width, height, language] of [['phone', 390, 844, 'en'], ['small-phone', 320, 720, 'es'], ['tablet', 1024, 768, 'nl'], ['desktop', 1440, 900, 'en'], ['rtl-phone', 390, 844, 'ar']]) {
      const context = await browser.newContext({ viewport: { width, height } });
      const page = await context.newPage(); const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.goto(`${base}/?lang=${language}`);
      await page.locator('.source-setup-card').waitFor();
      if (language !== 'en') await page.waitForFunction(() => document.querySelector('.source-setup-warning')?.textContent !== 'None of your enabled addons provide playback sources.');
      assert.equal(await page.getByRole('dialog').count(), 0, 'Setup does not block browsing');
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, name + ': no horizontal overflow');
      await page.screenshot({ path: path.join(evidence, name + '-setup.png') });
      await page.locator('.source-setup-actions button').nth(1).click();
      assert.equal(await page.getByTestId('settings-target').textContent(), 'homeserver');
      await page.goto(base);
      await page.locator('.source-setup-actions button').nth(2).click();
      assert.equal(await page.getByTestId('settings-target').textContent(), 'tv');
      await page.goto(base);
      await page.getByRole('button', { name: 'Add an addon', exact: true }).click();
      await page.locator('.source-setup-panel').waitFor();
      assert.equal(await page.getByRole('button', { name: 'Install', exact: true }).isDisabled(), true);
      await page.getByLabel('Addon settings', { exact: true }).fill('https://fixture.invalid/manifest.json');
      await page.getByLabel('Addon settings', { exact: true }).press('Enter');
      await page.getByRole('heading', { name: 'Installed fixture' }).waitFor();
      assert.equal(await page.locator('.source-setup-panel').count(), 0, 'Guidance clears once a source addon is installed');
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, name + ': Addons no overflow');
      await page.goto(base);
      await page.getByRole('button', { name: 'Close', exact: true }).click();
      assert.equal(await page.locator('.source-setup-card').count(), 0);
      await page.reload();
      await page.getByText('Fixture home content remains available.').waitFor();
      assert.equal(await page.locator('.source-setup-card').count(), 0, 'Dismissal persists across launches');
      await page.evaluate(() => localStorage.clear());
      await page.goto(base + '/?sources=ready');
      await page.getByText('Fixture home content remains available.').waitFor();
      assert.equal(await page.locator('.source-setup-card').count(), 0, 'Configured users are not prompted');
      assert.deepEqual(errors, []);
      await context.close();
      console.log(name + ': setup, destination actions, install, dismissal, responsive layout passed');
    }
  } finally { await browser.close(); await new Promise(resolve => server.close(resolve)); }
})().catch(error => { console.error(error); process.exitCode = 1; });

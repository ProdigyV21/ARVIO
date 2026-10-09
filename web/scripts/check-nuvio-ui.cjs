const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');

(async () => {
  const root = path.resolve(__dirname, '..');
  const output = path.resolve(root, '../artifacts/nuvio-import');
  fs.mkdirSync(output, { recursive: true });
  const stub = path.join(root, 'tests/nuvio-ui/stubs.tsx');
  await esbuild.build({
    entryPoints: [path.join(root, 'tests/nuvio-ui/entry.tsx')], bundle: true,
    outfile: path.join(output, 'app.js'), jsx: 'automatic',
    define: { 'process.env.NODE_ENV': '"test"' },
    plugins: [{ name: 'offline-account', setup(build) {
      build.onResolve({ filter: /^@\/lib\// }, () => ({ path: stub }));
    } }]
  });
  const server = http.createServer((req, res) => {
    if (req.url === '/app.js') {
      res.setHeader('content-type', 'text/javascript');
      fs.createReadStream(path.join(output, 'app.js')).pipe(res);
    } else if (req.url === '/setup.css') {
      res.setHeader('content-type', 'text/css');
      fs.createReadStream(path.join(root, 'app/migrate/setup.css')).pipe(res);
    } else if (req.url === '/globals.css') {
      res.setHeader('content-type', 'text/css');
      fs.createReadStream(path.join(root, 'app/globals.css')).pipe(res);
    } else if (req.url.startsWith('/logos/')) {
      const file = path.join(root, 'public/logos', path.basename(req.url));
      if (!fs.existsSync(file)) { res.writeHead(404).end(); return; }
      res.setHeader('content-type', file.endsWith('.svg') ? 'image/svg+xml' : 'image/png');
      fs.createReadStream(file).pipe(res);
    } else if (req.url === '/arvio-icon-192.png') {
      res.setHeader('content-type', 'image/png');
      fs.createReadStream(path.join(root, 'public/arvio-icon-192.png')).pipe(res);
    } else {
      res.setHeader('content-type', 'text/html');
      res.end('<html><head><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/globals.css"><link rel="stylesheet" href="/setup.css"></head><body><div id="root"></div><script src="/app.js"></script></body></html>');
    }
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  let browser;
  try {
    browser = await chromium.launch({ channel: 'msedge', headless: true });
    for (const [name, width, height] of [['desktop', 1440, 1000], ['mobile', 390, 844]]) {
      const page = await browser.newPage({ viewport: { width, height } });
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.goto(`http://127.0.0.1:${server.address().port}`);
      await page.getByText('Could not read your ARVIO account', { exact: true }).waitFor();
      const nuvio = page.locator('section').filter({ has: page.getByRole('heading', { name: /Nuvio/ }) });
      await nuvio.locator('input[type=email]').fill('demo@nuvio.example');
      await nuvio.locator('input[type=password]').fill('test-password');
      await nuvio.locator('button.setup__primary').first().click();
      const copy = page.getByRole('button', { name: /Copy 1 profiles into ARVIO/ });
      await copy.waitFor();
      assert.equal(await copy.isDisabled(), true, 'failed account load must block importing');
      assert.equal(await page.getByText('This account has no profiles yet.', { exact: false }).count(), 0);
      await page.getByRole('button', { name: 'Retry loading account' }).click();
      await page.waitForFunction(() => window.nuvioTest.resolveProfiles !== null);
      assert.equal(await copy.isDisabled(), true, 'pending retry must block importing');
      await page.evaluate(() => window.nuvioTest.resolveProfiles());
      await page.waitForFunction(() => ![...document.querySelectorAll('button')].find(button => button.textContent.includes('Copy 1 profiles')).disabled);
      await copy.click();
      await page.getByText('Import finished with warnings', { exact: true }).waitFor();
      await page.getByRole('status').filter({ hasText: 'Nuvio did not return collections' }).waitFor();
      assert.equal(await page.evaluate(() => window.nuvioTest.imports), 1);
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false);
      assert.deepEqual(errors, []);
      assert.equal(await page.locator('.setup__brand img').evaluate(image => image.complete && image.naturalWidth > 0), true);
      await page.screenshot({ path: path.join(output, `${name}.png`), fullPage: true });
      await page.getByRole('button', { name: 'Reconnect to Nuvio' }).click();
      await page.getByRole('button', { name: 'Connect to Nuvio', exact: true }).waitFor();
      await page.close();
      console.log(`${name}: failed/pending account load blocked; retry enabled import; warnings visible`);
    }
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
  }
})().catch(error => { console.error(error); process.exitCode = 1; });

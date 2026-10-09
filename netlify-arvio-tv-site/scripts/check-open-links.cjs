const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');
const assert = require('node:assert/strict');
// Reuse the checked-in web dev dependency, not a machine-global browser package.
const { chromium } = require('../../web/node_modules/@playwright/test');
const root = path.resolve(__dirname, '..');
const output = path.resolve(root, '../artifacts/growth-open-links');
const mime = { '.html': 'text/html', '.js': 'text/javascript', '.css': 'text/css', '.png': 'image/png' };
const server = http.createServer((req, res) => {
  let file;
  try {
    const url = new URL(req.url, 'http://localhost');
    file = path.resolve(root, '.' + decodeURIComponent(url.pathname), url.pathname.endsWith('/') ? 'index.html' : '');
  } catch { res.writeHead(400).end(); return; }
  if (!file.startsWith(root + path.sep) || !fs.existsSync(file) || !fs.statSync(file).isFile()) { res.writeHead(404).end(); return; }
  res.writeHead(200, { 'Content-Type': mime[path.extname(file)] || 'application/octet-stream' });
  fs.createReadStream(file).pipe(res);
});
(async () => {
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const origin = `http://127.0.0.1:${server.address().port}`;
  fs.mkdirSync(output, { recursive: true });
  let browser;
  try {
    browser = await chromium.launch({ headless: true, channel: process.env.PLAYWRIGHT_CHANNEL || 'chrome' });
    for (const [name, width, height, android] of [['desktop', 1440, 1000, false], ['mobile', 390, 844, true], ['small-mobile', 320, 740, true]]) {
      const context = await browser.newContext({ viewport: { width, height }, ...(android ? { userAgent: 'Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 Chrome/130.0.0.0 Mobile Safari/537.36' } : {}) });
      const page = await context.newPage();
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.goto(origin + '/open/?imdb=tt0944947&season=1&episode=2&utm_source=simkl');
      await page.getByRole('link', { name: 'Continue in browser' }).waitFor();
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, `${name}: overflow`);
      assert.equal(await page.locator('img').evaluateAll(imgs => imgs.every(img => img.complete && img.naturalWidth > 0)), true);
      assert.equal(await page.locator('#web').getAttribute('href'), 'https://web.arvio.tv/?open=1&imdb=tt0944947&season=1&episode=2&utm_source=simkl&utm_medium=integration&utm_campaign=open_in_arvio');
      assert.match(await page.locator('#native').getAttribute('href'), android ? /^intent:\/\/open\?/ : /^arvio:\/\/open\?/);
      assert.equal(await page.locator('#reference').textContent(), 'IMDb tt0944947 · Season 1 · Episode 2');
      assert.equal(await page.evaluate(() => getComputedStyle(document.documentElement).colorScheme), 'dark');
      await page.screenshot({ path: path.join(output, name + '.png'), fullPage: true });
      await page.locator('#native').focus();
      assert.equal(await page.evaluate(() => document.activeElement.id), 'native');
      await page.goto(origin + '/open/?imdb=tt0137523&imdb=tt0944947');
      assert.equal(await page.locator('#heading').textContent(), 'This link needs a title.');
      assert.equal(await page.locator('#actions').isVisible(), false);
      assert.equal(await page.locator('#install').getAttribute('href'), 'https://play.google.com/store/apps/details?id=com.arvio.tv');
      await page.screenshot({ path: path.join(output, name + '-invalid.png'), fullPage: true });
      assert.deepEqual(errors, []);
      console.log(name + ': assets, valid/invalid links, fixed destinations, Android fallback, focus and overflow passed');
      await context.close();
    }
    const context = await browser.newContext({ javaScriptEnabled: false });
    const page = await context.newPage();
    await page.goto(origin + '/open/?imdb=tt0137523');
    await page.locator('noscript a').waitFor({ state: 'visible' });
    assert.equal(await page.locator('noscript a').getAttribute('href'), 'https://web.arvio.tv/');
    await context.close();
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
  }
})().catch(error => { console.error(error); process.exitCode = 1; });

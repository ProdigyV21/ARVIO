// Local-only marketing UI verification. No payment links or analytics are sent.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const { chromium } = require('../../web/node_modules/@playwright/test');
const site = path.resolve(__dirname, '..');
const artifacts = path.resolve(site, '../artifacts/paid-only-marketing-20261023');
const types = { '.html': 'text/html', '.js': 'text/javascript', '.css': 'text/css', '.json': 'application/json', '.png': 'image/png', '.webp': 'image/webp', '.svg': 'image/svg+xml' };

async function main() {
  const server = http.createServer((req, res) => {
    const requested = new URL(req.url, 'http://localhost').pathname;
    const filename = path.resolve(site, '.' + decodeURIComponent(requested), requested.endsWith('/') ? 'index.html' : '');
    if (!filename.startsWith(site + path.sep) || !fs.existsSync(filename) || !fs.statSync(filename).isFile()) {
      res.writeHead(404).end(); return;
    }
    res.setHeader('content-type', types[path.extname(filename)] || 'application/octet-stream');
    res.end(fs.readFileSync(filename));
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const origin = `http://127.0.0.1:${server.address().port}`;
  let browser;
  try {
    browser = await chromium.launch({ headless: true, channel: process.env.ARVIO_TEST_BROWSER_CHANNEL || undefined });
    fs.mkdirSync(artifacts, { recursive: true });
    for (const [name, locale, width] of [['desktop-en', 'en-US', 1440], ['mobile-nl', 'nl-NL', 390], ['mobile-ar', 'ar-SA', 390], ['small-de', 'de-DE', 320]]) {
      const context = await browser.newContext({ viewport: { width, height: 900 }, locale });
      await context.route('**/*', route => route.request().url().startsWith(origin + '/') ? route.continue() : route.abort());
      const page = await context.newPage();
      await page.goto(origin + '/premium/');
      await page.waitForFunction(code => document.documentElement.lang === code, locale);
      await page.locator('img').evaluateAll(images => Promise.all(images.map(image => {
        image.loading = 'eager';
        return image.decode();
      })));
      const membership = page.locator('[data-premium-placement="hero-membership"]');
      const preview = page.locator('[data-premium-placement="plan-yearly"]');
      assert.equal(await membership.getAttribute('href'), 'https://web.arvio.tv/');
      assert.equal(await preview.getAttribute('href'), 'https://web.arvio.tv/');
      assert.equal(await page.locator('a[href*="intent=trial"]').count(), 0);
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), true, name + ': no horizontal overflow');
      for (const link of [membership, preview]) {
        const box = await link.boundingBox();
        assert.ok(box && box.width > 100 && box.height >= 44 && box.x >= 0 && box.x + box.width <= width + 1, name + ': usable CTA');
      }
      await preview.focus();
      assert.equal(await preview.evaluate(element => element === document.activeElement), true);
      if (locale === 'ar-SA') assert.equal(await page.locator('html').getAttribute('dir'), 'rtl');
      await page.screenshot({ path: path.join(artifacts, name + '.png'), fullPage: true });
      await context.close();
      console.log('Verified ' + name);
    }
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
  }
}
main().catch(error => { console.error(error); process.exitCode = 1; });

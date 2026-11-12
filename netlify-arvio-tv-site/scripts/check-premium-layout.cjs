// Run locally, or set SITE_BASE=https://arvio.tv to check the deployed pages.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const http = require('node:http');
const path = require('node:path');
const { createRequire } = require('node:module');
const { chromium } = createRequire(path.resolve(__dirname, '../../web/package.json'))('playwright');

const siteRoot = path.resolve(__dirname, '..');
const evidence = path.resolve(__dirname, '../../artifacts/premium-layout');
const copies = JSON.parse(fs.readFileSync(path.join(siteRoot, 'premium/languages.json'), 'utf8'));
const representativeLocales = ['en-US', 'he-IL', 'ar-SA', 'de-DE', 'nl-NL', 'fi-FI'];
const widths = [320, 360, 390, 430, 768, 1024, 1440];
const mime = {
  '.html': 'text/html; charset=utf-8', '.css': 'text/css', '.js': 'text/javascript',
  '.json': 'application/json', '.svg': 'image/svg+xml', '.png': 'image/png',
  '.webp': 'image/webp', '.woff2': 'font/woff2',
};

async function localServer() {
  const server = http.createServer((request, response) => {
    const pathname = decodeURIComponent(new URL(request.url, 'http://localhost').pathname);
    let file = path.resolve(siteRoot, `.${pathname}`);
    if (file !== siteRoot && !file.startsWith(siteRoot + path.sep)) {
      response.writeHead(403).end();
      return;
    }
    if (fs.existsSync(file) && fs.statSync(file).isDirectory()) file = path.join(file, 'index.html');
    if (!fs.existsSync(file)) {
      response.writeHead(404).end();
      return;
    }
    response.setHeader('Content-Type', mime[path.extname(file)] || 'application/octet-stream');
    const stream = fs.createReadStream(file);
    response.on('close', () => stream.destroy());
    stream.pipe(response);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  return server;
}

async function fits(page, label) {
  const layout = await page.evaluate(() => {
    const selectors = '.brand img, .language, #language, h1, .copy, .hero-video, .plan, .plan-button, .plan-badge, footer';
    const outside = [...document.querySelectorAll(selectors)].filter(element => {
      const box = element.getBoundingClientRect();
      return box.width && (box.left < -1 || box.right > innerWidth + 1);
    }).map(element => element.className || element.tagName);
    return { width: innerWidth, content: document.documentElement.scrollWidth, outside };
  });
  assert.ok(layout.content <= layout.width + 1, `${label}: horizontal overflow ${JSON.stringify(layout)}`);
  assert.deepEqual(layout.outside, [], `${label}: content is clipped`);
  assert.ok(await page.locator('.brand img').evaluate(image => image.complete && image.naturalWidth > 0), `${label}: wordmark did not load`);
}

async function main() {
  fs.mkdirSync(evidence, { recursive: true });
  const server = process.env.SITE_BASE ? null : await localServer();
  const base = process.env.SITE_BASE || `http://127.0.0.1:${server.address().port}`;
  let browser;
  let checks = 0;
  try {
    browser = await chromium.launch({ channel: process.env.BROWSER_CHANNEL || 'chrome', headless: true });
    for (const width of widths) {
      const context = await browser.newContext({ viewport: { width, height: 900 }, locale: 'en-US', reducedMotion: 'reduce' });
      // Keep pricing deterministic; no live geo lookup or account calls are needed.
      await context.addInitScript(() => {
        sessionStorage.setItem('arvio.visitorCountry.v1', 'NL');
        Object.defineProperty(navigator, 'doNotTrack', { value: '1' });
      });
      const page = await context.newPage();
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.goto(`${base}/premium/`, { waitUntil: 'domcontentloaded' });
      await page.waitForFunction(() => document.querySelector('#language').options.length > 2);
      await page.evaluate(() => document.fonts.ready);
      const locales = [320, 390].includes(width) ? Object.keys(copies) : representativeLocales;
      for (const locale of locales) {
        assert.ok(copies[locale], `Missing locale: ${locale}`);
        await page.locator('#language').selectOption(locale);
        await fits(page, `${width}px ${locale}`);
        checks++;
        if ([320, 390, 1440].includes(width) && ['en-US', 'he-IL'].includes(locale)) {
          await page.screenshot({ path: path.join(evidence, `${width}-${locale}.png`), animations: 'disabled' });
        }
      }
      assert.deepEqual(errors, [], `${width}px: browser errors`);
      await context.close();
      console.log(`${width}px: ${locales.length} languages fit`);
    }

    for (const javaScriptEnabled of [true, false]) {
      const context = await browser.newContext({ viewport: { width: 320, height: 900 }, javaScriptEnabled, locale: 'en-US', reducedMotion: 'reduce' });
      await context.addInitScript(() => sessionStorage.setItem('arvio.visitorCountry.v1', 'NL'));
      const page = await context.newPage();
      await page.goto(`${base}/he/premium/`, { waitUntil: 'domcontentloaded' });
      await page.evaluate(() => document.fonts.ready);
      await fits(page, `Hebrew route, JavaScript ${javaScriptEnabled}`);
      assert.equal(await page.locator('html').getAttribute('dir'), 'rtl');
      if (javaScriptEnabled) {
        await page.waitForFunction(() => document.querySelector('#language').value === 'he-IL');
        await page.locator('a[href="#plans"]').first().click();
        assert.equal(new URL(page.url()).hash, '#plans');
      }
      checks++;
      await context.close();
    }
    console.log(`Passed ${checks} Premium layout checks, including the Hebrew route with and without JavaScript.`);
  } finally {
    if (browser) await browser.close();
    if (server) {
      server.closeAllConnections();
      await new Promise(resolve => server.close(resolve));
    }
  }
}

main().catch(error => {
  console.error(error.message);
  process.exitCode = 1;
});

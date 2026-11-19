// Run against Netlify Dev for the website and a built Next server for the webapp.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { chromium } = require('@playwright/test');

const site = process.env.SITE_BASE || 'http://127.0.0.1:8897';
const web = process.env.WEB_BASE || 'http://127.0.0.1:3337';
const evidence = path.resolve(__dirname, '../../artifacts/pr877-browser');
const discord = 'https://discord.gg/UavuEYMfQ4';

async function fit(page) {
  const dimensions = await page.evaluate(() => ({ width: innerWidth, content: document.documentElement.scrollWidth }));
  assert.ok(dimensions.content <= dimensions.width + 1, `Horizontal overflow: ${JSON.stringify(dimensions)}`);
  for (const image of await page.locator('img[src*="wordmark"]').all()) {
    assert.ok(await image.evaluate(img => img.complete && img.naturalWidth > 0), 'Wordmark must load');
  }
}

async function report(page, base, name) {
  const error = '<img src=x onerror=alert(1)> verification';
  const target = `${base}/report/?id=pr877-verification&v=2.3.1&err=${encodeURIComponent(error)}`;
  await page.goto(target, { waitUntil: 'load' });
  const canonical = page.url();
  await page.getByText('Crash Report Captured', { exact: true }).waitFor();
  assert.equal(await page.locator('img[src="x"]').count(), 0, 'Error text must not become HTML');
  await page.getByRole('button', { name: 'Copy Report & Open Discord', exact: true }).click();
  await page.getByRole('alert').filter({ hasText: 'Your browser blocked copying' }).waitFor();
  const manual = page.getByRole('textbox', { name: 'Report text', exact: true });
  assert.match(await manual.inputValue(), /pr877-verification/);
  assert.ok((await manual.inputValue()).includes(error));
  assert.equal(await manual.evaluate(el => el.selectionStart === 0 && el.selectionEnd === el.value.length), true);
  await page.waitForTimeout(900);
  assert.equal(page.url(), canonical, 'Failure must not redirect to Discord');
  await fit(page);
  await page.screenshot({ path: path.join(evidence, `${name}-report-copy-failed.png`), fullPage: true });

  await page.evaluate(() => {
    navigator.clipboard.writeText = async text => { window.copiedReport = text; };
  });
  await page.getByRole('button', { name: /Couldn't copy/ }).click();
  assert.match(await page.evaluate(() => window.copiedReport), /pr877-verification/);
  await page.waitForURL(discord);

  await page.goto(`${base}/report/`, { waitUntil: 'load' });
  await page.getByText('No Crash Detected', { exact: true }).waitFor();
  assert.equal(await page.getByRole('button', { name: /Copy Report/ }).isVisible(), false);
  await fit(page);
  await page.screenshot({ path: path.join(evidence, `${name}-report-empty.png`), fullPage: true });
}

async function main() {
  fs.mkdirSync(evidence, { recursive: true });
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  try {
    const request = await browser.newContext();
    for (const [route, status, destination] of [
      ['/app', 302, 'https://web.arvio.tv'],
      ['/app/library', 302, 'https://web.arvio.tv/library'],
      ['/companion', 301, '/'], ['/companion/old-path', 301, '/'],
      ['/privacy', 302, 'https://github.com/ProdigyV21/ARVIO/blob/main/PRIVACY.md'],
    ]) {
      const response = await request.request.get(site + route, { maxRedirects: 0 });
      assert.equal(response.status(), status, route);
      assert.equal(new URL(response.headers().location, site).href, new URL(destination, site).href, route);
    }
    for (const route of ['/go/premium', '/go/premium/hero', '/go/membership/nav']) {
      const response = await request.request.get(site + route);
      assert.equal(response.status(), 200, route);
      assert.match(await response.text(), /premium/i);
    }
    for (const base of [site, web]) {
      const response = await request.request.get(base + '/pr877-unknown-route');
      assert.equal(response.status(), 404, base);
      assert.match(await response.text(), /404/);
    }
    await request.close();
    console.log('Netlify route precedence, companion redirects, premium rewrites and both 404 responses passed');

    for (const [name, viewport] of [['desktop', { width: 1440, height: 900 }], ['mobile', { width: 390, height: 844 }]]) {
      const context = await browser.newContext({ viewport, isMobile: name === 'mobile', hasTouch: name === 'mobile', locale: 'en-US', reducedMotion: 'reduce',
        ...(name === 'mobile' ? { userAgent: 'Mozilla/5.0 (Linux; Android 14; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Mobile Safari/537.36' } : {}) });
      context.setDefaultTimeout(20_000);
      context.setDefaultNavigationTimeout(45_000);
      await context.addInitScript(() => {
        Object.defineProperty(navigator, 'clipboard', { configurable: true,
          value: { writeText: async () => { throw new DOMException('Blocked', 'NotAllowedError'); } } });
        document.execCommand = () => false;
      });
      let releaseAvailable = true;
      await context.route('**/*', async route => {
        const url = new URL(route.request().url());
        if (url.href === discord) return route.fulfill({ contentType: 'text/html', body: '<h1>Discord handoff</h1>' });
        if (url.href.includes('api.github.com/repos/ProdigyV21/ARVIO/releases/latest')) {
          const headers = { 'access-control-allow-origin': '*' };
          if (!releaseAvailable) return route.fulfill({ status: 429, headers, json: { message: 'Rate limited' } });
          return route.fulfill({ headers, json: { tag_name: 'v9.9.9', html_url: 'https://github.com/ProdigyV21/ARVIO/releases/tag/v9.9.9' } });
        }
        if (url.href === 'https://web.arvio.tv/api/geo') return route.fulfill({ headers: { 'access-control-allow-origin': '*' }, json: { country: 'IN' } });
        if (![new URL(site).origin, new URL(web).origin].includes(url.origin)) return route.abort();
        return route.continue();
      });
      const page = await context.newPage();
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      for (const [route, label] of [['/', 'LATEST RELEASE'], ['/es/', 'ULTIMA VERSION'], ['/pt-br/', 'ULTIMA VERSAO']]) {
        console.log(`${name}: checking website ${route}`);
        await page.goto(site + route, { waitUntil: 'load' });
        await page.locator('.release').filter({ hasText: 'v9.9.9' }).waitFor();
        const release = await page.locator('.release').innerText();
        assert.ok(release.normalize('NFD').replace(/[\u0300-\u036f]/g, '').includes(label));
        assert.ok(await page.locator('.hero-store').isVisible());
        await fit(page);
        await page.screenshot({ path: path.join(evidence, `${name}-home-${route.replaceAll('/', '') || 'en'}.png`) });
      }
      releaseAvailable = false;
      await page.goto(site, { waitUntil: 'load' });
      assert.match(await page.locator('.release').innerText(), /2\.3\.1/);
      await page.goto(site + '/premium/', { waitUntil: 'load' });
      await page.locator('.plan-best .plan-price strong').filter({ hasText: '$10' }).waitFor();
      await fit(page);
      await page.screenshot({ path: path.join(evidence, `${name}-premium.png`), fullPage: false });
      for (const base of [site, web]) {
        const surface = base === site ? 'site' : 'web';
        await report(page, base, `${name}-${surface}`);
        const response = await page.goto(base + '/pr877-unknown-route', { waitUntil: 'load' });
        assert.equal(response.status(), 404);
        assert.ok((await page.getByRole('heading', { level: 1 }).innerText()).trim());
        assert.doesNotMatch(await page.locator('body').innerText(), /&nearr;/);
        await fit(page);
        await page.screenshot({ path: path.join(evidence, `${name}-${surface}-404.png`), fullPage: true });
      }
      await page.goto(web + '/discover', { waitUntil: 'load' });
      await page.locator('.wordmark-logo').waitFor();
      await fit(page);
      await page.screenshot({ path: path.join(evidence, `${name}-discover.png`) });
      assert.deepEqual(errors, [], 'No browser runtime errors');
      await context.close();
      console.log(`${name}: localized home/release, pricing, diagnostics failure/retry, 404s and discover passed`);
    }
  } finally {
    await browser.close();
  }
}

main().catch(error => { console.error(error); process.exitCode = 1; });

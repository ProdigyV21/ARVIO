const { chromium } = require('playwright');
const assert = require('node:assert/strict');
const path = require('node:path');

(async () => {
  const browser = await chromium.launch({ channel: 'msedge', headless: true });
  try {
    const page = await browser.newPage();
    const requests = new Map();
    const origin = 'https://logos.example.test/';
    let directories = 0;
    await page.route('**/data/channel-logos.json', route => {
      directories++;
      return route.fulfill({ json: { entries: [
        ['Known.us', 'US', ['Known'], [origin + 'bad-fallback.png', origin + 'fallback.png']],
        ['ESPN.nl', 'NL', ['ESPN'], [origin + 'nl.png']],
        ['ESPN.us', 'US', ['ESPN'], [origin + 'us.png']],
        ['Failed.us', 'US', ['Failed'], [origin + 'bad-one.png', origin + 'bad-two.png']],
      ] } });
    });
    await page.route(origin + '**', route => {
      const name = route.request().url().slice(origin.length);
      requests.set(name, (requests.get(name) ?? 0) + 1);
      if (/bad|broken/.test(name)) return route.fulfill({ status: 404, body: '' });
      return route.fulfill({ contentType: 'image/svg+xml', body: '<svg xmlns="http://www.w3.org/2000/svg" width="140" height="78"><rect width="140" height="78" fill="#33aadd"/><circle cx="70" cy="39" r="26" fill="white"/></svg>' });
    });
    for (const [width, height] of [[1672, 941], [768, 1024], [390, 844]]) {
      await page.setViewportSize({ width, height });
      await page.goto((process.env.TEST_URL || 'http://127.0.0.1:3111') + '/dev/channel-logos');
      for (const [id, suffix] of [['provider', 'provider.png'], ['broken', 'fallback.png'], ['missing', 'nl.png']]) {
        await page.waitForFunction(({ id, suffix }) => {
          const img = document.querySelector(`[data-testid="${id}"] img`);
          return img?.src.endsWith(suffix) && img.complete && img.naturalWidth > 0 && getComputedStyle(img).opacity === '1';
        }, { id, suffix }).catch(async error => {
          console.error({ id, width, requests: [...requests], directories, html: await page.locator(`[data-testid="${id}"]`).innerHTML() });
          throw error;
        });
      }
      await page.waitForFunction(() => [...document.querySelectorAll('[data-testid="exhausted"] img')].length === 0);
      for (const id of ['ambiguous', 'unknown', 'exhausted']) {
        assert.equal(await page.locator(`[data-testid="${id}"] img`).count(), 0);
        assert.equal(await page.locator(`[data-testid="${id}"] svg`).count(), 1);
      }
      const before = requests.get('broken.png');
      await page.getByRole('button', { name: 'Remount' }).click();
      await page.waitForFunction(() => document.querySelector('[data-testid="broken"] img')?.src.endsWith('fallback.png'));
      assert.equal(requests.get('broken.png'), before, 'broken image should not retry on remount');
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false);
      if (process.env.SCREENSHOT_DIR) await page.screenshot({ path: path.join(process.env.SCREENSHOT_DIR, `channel-logos-web-${width}.png`), fullPage: true });
    }
    assert.equal(directories, 3, 'one metadata request per page load, not per channel');
    assert.equal(requests.has('us.png'), false, 'ambiguous ESPN must not guess US');
    console.log('PASS: provider-first, missing/broken fallbacks, ambiguity, exhaustion, remount retry cap, desktop/tablet/mobile');
  } finally { await browser.close(); }
})().catch(error => { console.error(error); process.exitCode = 1; });

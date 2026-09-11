const { chromium } = require('playwright');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

(async () => {
  const response = await fetch(process.env.SPORTS_METADATA_URL);
  assert.equal(response.status, 200);
  const payload = await response.json();
  const out = process.env.TV_OVERHAUL_SCREENSHOTS || path.resolve('test-results/sportsdb');
  fs.mkdirSync(out, {recursive: true});
  const browser = await chromium.launch({channel: 'chrome', headless: true});
  try {
    for (const badgesOnly of [false, true]) {
      const page = await browser.newPage({viewport: {width: 1672, height: 941}, timezoneId: 'Europe/Amsterdam'});
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.route('**/*', route => {
        const url = new URL(route.request().url());
        if (url.pathname.endsWith('/sports-metadata')) return route.fulfill({json: {...payload, events: payload.events.map(event => ({...event, background: badgesOnly ? null : event.background}))}});
        return ['127.0.0.1', 'r2.thesportsdb.com', 'www.thesportsdb.com'].includes(url.hostname) ? route.continue() : route.abort();
      });
      await page.goto('http://127.0.0.1:3109/dev/sportsdb', {waitUntil: 'domcontentloaded', timeout: 120000});
      const selector = badgesOnly ? '.tv-event-teams' : '.tv-event-image > img';
      await page.waitForFunction(() => [...document.querySelectorAll('.tv-event-image > img, .tv-event-teams')].filter(el => getComputedStyle(el).opacity === '1').length >= 3, null, {timeout: 45000});
      assert.equal(await page.getByText('Available on my channels', {exact: true}).count(), 0);
      assert.equal(await page.locator('.tv-sports a').count(), 0);
      assert.equal(await page.locator('.tv-event-card').getByText('0 channels', {exact: true}).count(), 0);
      for (const width of [1672, 768, 390]) {
        await page.setViewportSize({width, height: width === 390 ? 844 : 941});
        await page.waitForTimeout(250);
        assert.ok(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), `No overflow ${width}`);
        const container = await page.locator('.tv-event-art').first().boundingBox();
        assert.ok(Math.abs(container.width / container.height - 16 / 9) < .02, `16:9 match artwork ${width}`);
        if (width === 1672) assert.ok(container.width < 350, 'Desktop match cards stay compact');
        const images = await page.locator(`${selector} img`).first().count();
        if (badgesOnly && images) {
          const rect = await page.locator(`${selector} img`).first().boundingBox();
          assert.ok(rect.width > 25 && rect.x >= container.x && rect.x + rect.width <= container.x + container.width, 'Crest fits the card');
        }
        await page.screenshot({path: path.join(out, `${badgesOnly ? 'crests' : 'banners'}-${width}.png`)});
        await page.locator('.tv-event-card').first().click();
        await page.locator('dialog[open]').waitFor();
        await page.waitForFunction(() => {
          const images = [...document.querySelectorAll('.tv-event-picker-art img')];
          return images.length > 0 && images.every(img => img.complete && img.naturalWidth > 0);
        });
        const pickerArt = await page.locator('.tv-event-picker-art').boundingBox();
        assert.ok(Math.abs(pickerArt.width / pickerArt.height - 16 / 9) < .02, 'Picker preserves match artwork proportions');
        await page.locator('.tv-event-source').first().waitFor();
        assert.ok(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), `Picker fits ${width}`);
        await page.screenshot({path: path.join(out, `${badgesOnly ? 'crests' : 'banners'}-picker-${width}.png`), animations: 'disabled'});
        await page.getByRole('button', {name: 'Close', exact: true}).click();
        assert.equal(await page.locator('dialog[open]').count(), 0);
      }
      await page.goto('http://127.0.0.1:3109/dev/sportsdb?guide=empty');
      await page.getByText('No sports events matched to your channels', {exact: true}).waitFor();
      assert.equal(await page.locator('.tv-event-card').count(), 0);
      assert.deepEqual(errors, []);
      await page.close();
    }
    console.log(JSON.stringify({passed: true, realFeedEvents: payload.events.length, viewports: [1672,768,390], screenshots: out}));
  } finally { await browser.close(); }
})().catch(error => {console.error(error); process.exitCode = 1;});

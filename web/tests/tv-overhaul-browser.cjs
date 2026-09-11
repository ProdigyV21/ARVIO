const { chromium } = require('playwright');
const assert = require('node:assert/strict');
const path = require('node:path');
const fs = require('node:fs');

(async () => {
  const output = process.env.TV_OVERHAUL_SCREENSHOTS || path.resolve('test-results/tv-overhaul');
  fs.mkdirSync(output, { recursive: true });
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  const errors = [];
  try {
    const page = await browser.newPage({ viewport: { width: 1672, height: 941 } });
    let emptyArtwork = false;
    page.on('pageerror', (error) => errors.push(error.message));
    page.on('console', (message) => {
      if (message.type() === 'error' && /hydrat|React|Unhandled/i.test(message.text())) errors.push(message.text());
    });
    await page.route('**/*', route => {
      const url = new URL(route.request().url());
      if (url.pathname === '/api/proxy' && url.searchParams.get('url')?.startsWith('https://example.invalid/sports/catalog/')) {
        return route.fulfill({ json: emptyArtwork ? { metas: [] } : require('../app/dev/stabilization/sports-artwork.json') });
      }
      return ['127.0.0.1', 'cdn.highfly.dev', 'interactive-examples.mdn.mozilla.net'].includes(url.hostname) ? route.continue() : route.abort();
    });
    await page.goto('http://127.0.0.1:3109/dev/stabilization', { waitUntil: 'domcontentloaded', timeout: 120_000 });
    await page.locator('[data-fixture-ready="true"]').waitFor({ timeout: 120_000 });
    await page.getByRole('button', { name: 'All Channels', exact: false }).waitFor();
    await page.waitForTimeout(1200);
    await page.screenshot({ path: path.join(output, 'web-01-guide-open.png'), animations: 'disabled' });
    await page.getByRole('button', { name: 'Toggle categories' }).click();
    await page.waitForTimeout(240);
    await page.screenshot({ path: path.join(output, 'web-02-guide-closed.png'), animations: 'disabled' });
    await page.getByRole('button', { name: 'Test guide mini-player', exact: true }).click();
    await page.locator('.player-docked video').waitFor();
    await page.locator('video').evaluate(video => { video.loop = true; window.__dockVideo = video; });
    await page.waitForFunction(() => document.querySelector('video')?.currentTime > 0.25, { timeout: 30_000 });
    await page.locator('#live-tv-player-dock').scrollIntoViewIfNeeded();
    await page.waitForTimeout(300);
    const dockBox = await page.locator('#live-tv-player-dock').boundingBox();
    const playerBox = await page.locator('.player-docked').boundingBox();
    assert.ok(Math.abs(dockBox.x - playerBox.x) < 2 && Math.abs(dockBox.width - playerBox.width) < 2, 'Player occupies guide preview bounds');
    assert.ok(Math.abs(dockBox.width / dockBox.height - 16/9) < .05, 'Guide preview has a stable video aspect ratio');
    await page.screenshot({ path: path.join(output, 'web-guide-playing.png'), animations: 'disabled' });
    await page.getByRole('button', { name: 'Expand player', exact: true }).click();
    assert.equal(await page.locator('.player-docked').count(), 0);
    await page.getByRole('button', { name: 'Return to guide', exact: true }).click();
    assert.ok(await page.locator('video').evaluate(video => video === window.__dockVideo), 'Expanding/collapsing must retain the same video/connection');
    for (const width of [390, 768, 1672]) {
      await page.setViewportSize({ width, height: 941 });
      await page.locator('#live-tv-player-dock').scrollIntoViewIfNeeded();
      await page.waitForTimeout(300);
      const rect = await page.locator('.player-docked').boundingBox();
      assert.ok(rect.width > 100 && rect.x >= 0 && rect.x + rect.width <= width + 1, `Mini-player fits ${width}px`);
    }
    await page.getByRole('button', { name: 'Stop channel', exact: true }).click();
    assert.equal(await page.locator('video').count(), 0);
    await page.getByRole('button', { name: 'Toggle categories' }).click();
    await page.getByRole('button', { name: 'Sports', exact: true }).click();
    await page.locator('.tv-event-card').first().waitFor({ timeout: 30_000 });
    await page.locator('.tv-event-image > img').first().waitFor();
    await page.locator('.tv-event-image > img').first().evaluate(img => img.decode());
    await page.getByRole('button', { name: 'Categories', exact: true }).click();
    await page.waitForTimeout(240);
    await page.screenshot({ path: path.join(output, 'web-03-sports-open.png'), animations: 'disabled' });
    await page.locator('.tv-event-card').first().focus();
    await page.waitForTimeout(240);
    await page.screenshot({ path: path.join(output, 'web-04-sports-closed.png'), animations: 'disabled' });
    const size = await page.locator('.tv-event-art').first().boundingBox();
    assert.ok(Math.abs(size.width / size.height - 2.25) < 0.03, 'Event artwork must keep its wide ratio');
    assert.ok(await page.locator('.tv-event-image > img').first().evaluate((img) => img.complete && img.naturalWidth > 0), 'Sports artwork must render');
    assert.equal(await page.locator('.tv-event-image > img').first().evaluate(img => getComputedStyle(img).objectFit), 'contain', 'Crests must not be cropped');
    const cards = await page.locator('.tv-sports-row').first().locator('.tv-event-card').evaluateAll(items => items.slice(0, 4).map(item => { const r = item.getBoundingClientRect(); return { left: r.left, right: r.right }; }));
    assert.equal(cards.length, 4);
    assert.ok(cards.every(card => card.left >= 0 && card.right <= 1672), 'Four complete desktop cards must fit');
    assert.equal(await page.getByRole('combobox', { name: 'Upcoming date' }).count(), 0);
    await page.locator('.tv-event-card').first().focus();
    await page.keyboard.press('Enter');
    await page.locator('.tv-event-picker[open]').waitFor();
    await page.waitForFunction(() => document.activeElement?.classList.contains('tv-event-source'));
    assert.ok(await page.locator('.tv-event-source').first().evaluate(element => element === document.activeElement), 'First playable source owns initial focus');
    await page.screenshot({ path: path.join(output, 'web-05-event-picker.png'), animations: 'disabled' });
    assert.equal(await page.locator('.tv-event-picker').evaluate(element => getComputedStyle(element).opacity), '1', 'Picker must settle opaque');
    await page.locator('.tv-event-source').first().click();
    assert.equal(await page.locator('.tv-event-picker[open]').count(), 0);
    assert.match(await page.locator('.fixture-toast').innerText(), /Selected:/);
    await page.keyboard.press('ArrowLeft');
    assert.equal(await page.locator('.livetv-cats').getAttribute('inert'), null);
    for (const width of [390, 768]) {
      await page.setViewportSize({ width, height: 900 });
      await page.locator('.tv-event-card').first().focus();
      await page.waitForTimeout(250);
      assert.ok(await page.evaluate(() => document.documentElement.scrollWidth <= window.innerWidth), `No horizontal overflow at ${width}px`);
      await page.screenshot({ path: path.join(output, `web-sports-${width}.png`), animations: 'disabled' });
      await page.locator('.tv-event-card').first().click();
      const picker = await page.locator('.tv-event-picker').boundingBox();
      assert.ok(picker.width <= width, 'Picker must fit');
      await page.keyboard.press('Escape');
      assert.ok(await page.locator('.tv-event-card').first().evaluate(element => element === document.activeElement), 'Closing picker restores the originating card');
    }
    emptyArtwork = true;
    await page.setViewportSize({ width: 1672, height: 941 });
    await page.reload({ waitUntil: 'domcontentloaded' });
    await page.locator('[data-fixture-ready="true"]').waitFor();
    await page.getByRole('button', { name: 'Sports', exact: true }).click();
    await page.waitForTimeout(2000);
    await page.screenshot({ path: path.join(output, 'web-fallback-cold.png'), animations: 'disabled' });
    await page.locator('.tv-event-card').first().waitFor({ timeout: 30_000 });
    await page.screenshot({ path: path.join(output, 'web-fallback-check.png'), animations: 'disabled' });
    await page.locator('.tv-event-fallback img').first().waitFor();
    await page.locator('.tv-event-fallback img').first().evaluate(img => img.decode());
    assert.equal(await page.locator('.tv-event-image > img').count(), 0, 'No event banner is invented without metadata');
    assert.ok(await page.locator('.tv-event-fallback img').first().evaluate(img => img.naturalWidth > 0), 'Local fallback photography decodes without an addon');
    await page.screenshot({ path: path.join(output, 'web-sports-local-artwork.png'), animations: 'disabled' });
    assert.deepEqual(errors, [], 'No uncaught browser errors');
    console.log(JSON.stringify({ passed: true, viewports: [1672, 768, 390], screenshots: output }, null, 2));
  } finally { await browser.close(); }
})().catch((error) => { console.error(error); process.exitCode = 1; });

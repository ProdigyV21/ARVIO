const { chromium } = require('playwright');
const assert = require('node:assert/strict');
const fs = require('node:fs/promises');
const path = require('node:path');

(async () => {
  const output = path.resolve(process.env.UI_CAPTURE_DIR || '../artifacts/copyright-ui');
  await fs.mkdir(output, { recursive: true });
  const browser = await chromium.launch({ channel: 'msedge', headless: true });
  try {
    for (const viewport of [{ width: 1440, height: 900 }, { width: 390, height: 844 }]) {
      const page = await browser.newPage({ viewport });
      page.setDefaultTimeout(20000);
      page.setDefaultNavigationTimeout(45000);
      console.log('Start viewport', viewport.width);
      await page.goto('http://localhost:3035/dev/stabilization', { waitUntil: 'domcontentloaded' });
      await page.locator('[data-fixture-ready="true"]').waitFor();
      await page.getByRole('button', { name: 'Settings', exact: true }).click();
      if (viewport.width < 800) await page.getByRole('button', { name: 'Open navigation menu', exact: true }).click();
      await page.locator(viewport.width < 800 ? '.settings-mobile-nav' : '.settings-nav').getByRole('button', { name: 'About & Credits', exact: true }).click();
      console.log('Credits selected');
      await page.getByText('This product uses the TMDB API but is not endorsed or certified by TMDB.').waitFor();
      const image = page.getByAltText('TMDB');
      await image.scrollIntoViewIfNeeded();
      await page.waitForFunction(() => [...document.images].some(img => img.alt === 'TMDB' && img.complete && img.naturalWidth > 0), null, { timeout: 15000 });
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), true, 'Credits overflow');
      await page.screenshot({ path: path.join(output, `web-credits-${viewport.width}.png`), fullPage: true });

      await page.getByRole('button', { name: 'Test YouTube embed', exact: true }).click();
      const frame = page.locator('iframe.player-youtube');
      await frame.waitFor();
      const frameBox = await frame.boundingBox();
      const toolbarBox = await page.locator('.youtube-player-toolbar').boundingBox();
      assert.ok(frameBox.y >= toolbarBox.y + toolbarBox.height - 1, 'Toolbar covers YouTube');
      assert.ok(frameBox.width >= 200 && frameBox.height >= 200, 'YouTube viewport too small');
      await page.waitForFunction(() => getComputedStyle(document.querySelector('.youtube-player-layout')).opacity === '1', null, { timeout: 10000 });
      await page.screenshot({ path: path.join(output, `web-youtube-${viewport.width}.png`), animations: 'disabled' });
      await page.locator('.youtube-player-toolbar').getByRole('button', { name: 'Close', exact: true }).click();

      await page.getByRole('button', { name: 'Test source setup', exact: true }).click();
      await page.getByRole('dialog', { name: 'Connect your media sources' }).waitFor();
      await page.screenshot({ path: path.join(output, `web-onboarding-${viewport.width}.png`) });
      await page.getByRole('button', { name: 'Open settings', exact: true }).click();
      assert.equal(await page.getByRole('dialog', { name: 'Connect your media sources' }).count(), 0);

      await page.goto('http://127.0.0.1:8035/credits/', { waitUntil: 'networkidle' });
      assert.ok(await page.getByAltText('TMDB').evaluate(img => img.complete && img.naturalWidth > 0));
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), true, 'Website overflow');
      await page.screenshot({ path: path.join(output, `website-credits-${viewport.width}.png`), fullPage: true });
      console.log(JSON.stringify({ viewport, credits: 'pass', toolbarDoesNotOverlap: 'pass', sourceSetup: 'pass', website: 'pass' }));
      await page.close();
    }
  } finally {
    await browser.close();
  }
})().catch(error => { console.error(error); process.exitCode = 1; });

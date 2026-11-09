const path = require('node:path');
const fs = require('node:fs');
const assert = require('node:assert/strict');
const { chromium } = require('../../web/node_modules/@playwright/test');
// No fallback to system Chrome: the dedicated shell has no window UI.
const executablePath = process.env.ARVIO_HEADLESS_SHELL;
if (!executablePath || !/^chrome-headless-shell(?:\.exe)?$/i.test(path.basename(executablePath)) || !fs.existsSync(executablePath)) {
  throw new Error('Set ARVIO_HEADLESS_SHELL to the dedicated chrome-headless-shell executable. System Chrome is not allowed.');
}
const evidence = path.resolve(__dirname, '../../artifacts/cloudstream-poc');
fs.mkdirSync(evidence, { recursive: true });
const state = path.join(evidence, 'browser-state.json');
const base = 'http://127.0.0.1:8876';
const bridge = 'http://127.0.0.1:8950';
(async () => {
  const browser = await chromium.launch({ executablePath, headless: true });
  const context = await browser.newContext({ viewport: { width: 1440, height: 900 }, storageState: state });
  const page = await context.newPage();
  const media = [], errors = [], relayRequests = [];
  page.on('pageerror', error => errors.push(error.message.slice(0, 180)));
  let playing = false;
  page.on('request', request => {
    if (!playing) return;
    const u = new URL(request.url());
    if (u.origin === base && /^\/api\/(proxy|.*media|.*remux)/.test(u.pathname)) relayRequests.push(u.pathname);
  });
  page.on('response', response => {
    if (!playing) return;
    const type = response.headers()['content-type'] || '';
    // Some upstream HLS hosts mislabel segments. Decoded frames/audio are the playback proof.
    if (/video\/|audio\/|mpegurl|dash\+xml/.test(type) || ['fetch', 'xhr'].includes(response.request().resourceType()) && !response.url().startsWith(base))
      media.push({ host: new URL(response.url()).hostname, status: response.status(), type });
  });
  try {
    await page.goto(base, { waitUntil: 'domcontentloaded', timeout: 90000 });
    await page.evaluate(async bridge => {
      // Isolated test profile only. Real catalog/metadata/source responses, no mock media.
      const settings = JSON.parse(localStorage.getItem('arvio.web.settings') || '{}');
      settings.catalogs = [{ id: 'cs-poc-movies', name: 'StreamPlay movies', sourceType: 'addon', mediaType: 'movie',
        addonId: 'arvio.local.cloudstream.streamplay.poc', addonCatalogId: 'streamplay', addonCatalogType: 'movie', sourceUrl: `${bridge}/manifest.json`, enabled: true }];
      localStorage.setItem('arvio.web.settings', JSON.stringify(settings));
      localStorage.removeItem('arvio.web.catalogRows.v3');
      await fetch(`${bridge}/catalog/movie/streamplay/search=Sintel.json`);
    }, bridge);
    await page.reload();
    await page.locator('button[data-profile-id]').first().click({ timeout: 30000 });
    await page.getByText('Sintel', { exact: true }).first().click({ timeout: 30000 });
    await page.waitForFunction(() => /[1-9]\d* sources/.test(document.body.innerText), {}, { timeout: 60000 });
    const start = Date.now();
    playing = true;
    await page.getByRole('button', { name: 'Play', exact: true }).click();
    await page.waitForFunction(() => { const v = document.querySelector('video'); return v && v.currentTime > 3 && v.videoWidth > 0; }, {}, { timeout: 45000 });
    const sample = () => page.evaluate(() => { const v = document.querySelector('video'), q = v.getVideoPlaybackQuality();
      return { time: v.currentTime, width: v.videoWidth, height: v.videoHeight, frames: q.totalVideoFrames, dropped: q.droppedVideoFrames,
        audioBytes: v.webkitAudioDecodedByteCount, paused: v.paused }; });
    const first = await sample(), timeToThreeSecondsMs = Date.now() - start;
    assert.ok(first.frames > 0 && first.audioBytes > 0, 'decoded picture and audio');
    const seekStart = Date.now();
    await page.evaluate(() => { document.querySelector('video').currentTime = 120; });
    await page.waitForFunction(() => { const v = document.querySelector('video'); return v.currentTime > 122 && !v.seeking; }, {}, { timeout: 30000 });
    const afterSeek = await sample(), seekToTwoSecondsMs = Date.now() - seekStart;
    assert.ok(afterSeek.frames > first.frames && afterSeek.audioBytes > first.audioBytes && !afterSeek.paused);
    assert.ok(media.length > 0, 'real remote source responses');
    assert.ok(media.every(r => !['127.0.0.1', 'localhost'].includes(r.host)), 'no ARVIO video relay');
    assert.deepEqual(relayRequests, [], 'no ARVIO proxy or remux requests');
    await page.screenshot({ path: path.join(evidence, 'actual-web-playback.png') });
    const health = await fetch(`${bridge}/health`).then(r => r.json());
    assert.equal(health.metrics.mediaRelayBytes, 0);
    const result = { testedAt: new Date().toISOString(), browser: browser.version(), dedicatedHeadlessShell: true,
      title: 'Sintel', timeToThreeSecondsMs, seekToTwoSecondsMs, first, afterSeek,
      mediaHosts: [...new Set(media.map(r => r.host))], mediaResponses: media.length, mediaRelayBytes: health.metrics.mediaRelayBytes, pageErrors: errors };
    fs.writeFileSync(path.join(evidence, 'web-playback-result.json'), JSON.stringify(result, null, 2));
    console.log(JSON.stringify(result));
  } catch (error) {
    console.log(await page.evaluate(() => ({ text: document.body.innerText.slice(-1400),
      registry: JSON.parse(localStorage.getItem('arvio.web.addonNativeItems.v1') || '{}'),
      rows: JSON.parse(localStorage.getItem('arvio.web.catalogRows.v3') || '{}') })));
    throw error;
  } finally { await browser.close(); }
})().catch(error => { console.error(error.message); process.exitCode = 1; });

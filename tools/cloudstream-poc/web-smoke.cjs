const path = require('node:path');
const fs = require('node:fs');
const assert = require('node:assert/strict');
const { chromium, webkit, devices } = require('../../web/node_modules/@playwright/test');
const appleTest = process.env.ARVIO_TEST_BROWSER === 'webkit';
// No fallback to system Chrome: the dedicated shell has no window UI.
const executablePath = process.env.ARVIO_HEADLESS_SHELL;
if (!appleTest && (!executablePath || !/^chrome-headless-shell(?:\.exe)?$/i.test(path.basename(executablePath)) || !fs.existsSync(executablePath))) {
  throw new Error('Set ARVIO_HEADLESS_SHELL to the dedicated chrome-headless-shell executable. System Chrome is not allowed.');
}
const evidence = path.resolve(__dirname, '../../artifacts/cloudstream-poc');
fs.mkdirSync(evidence, { recursive: true });
const state = path.join(evidence, 'browser-state.json');
const base = 'http://127.0.0.1:8876';
const bridge = 'http://127.0.0.1:8950';
(async () => {
  const browser = await (appleTest ? webkit : chromium).launch({ ...(appleTest ? {} : { executablePath }), headless: true });
  const context = await browser.newContext({ ...(appleTest ? devices['iPad Pro 11 landscape'] : { viewport: { width: 1440, height: 900 } }),
    ...(fs.existsSync(state) ? { storageState: state } : {}) });
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
    const capabilities = await page.evaluate(() => ({ nativeHls: !!document.createElement('video').canPlayType('application/vnd.apple.mpegurl'), mse: typeof MediaSource !== 'undefined' }));
    if (appleTest) assert.ok(capabilities.nativeHls, 'This WebKit installation cannot test Apple native HLS; use a macOS runner');
    await page.evaluate(async bridge => {
      // Isolated test profile only. Real catalog/metadata/source responses, no mock media.
      const settings = JSON.parse(localStorage.getItem('arvio.web.settings') || '{}');
      settings.catalogs = [{ id: 'cs-poc-movies', name: 'StreamPlay movies', sourceType: 'addon', mediaType: 'movie',
        addonId: 'arvio.local.cloudstream.streamplay.poc', addonCatalogId: 'streamplay', addonCatalogType: 'movie', sourceUrl: `${bridge}/manifest.json`, enabled: true }];
      localStorage.setItem('arvio.web.settings', JSON.stringify(settings));
      localStorage.removeItem('arvio.web.catalogRows.v3');
      if (!localStorage.getItem('arvio.web.profiles')) {
        localStorage.setItem('arvio.web.profiles', JSON.stringify([{ id: 'cloudstream-qa', name: 'Test profile', avatarColor: 4293200148, avatarId: 0, isKidsProfile: false, isLocked: false, createdAt: Date.now() }]));
      }
      await fetch(`${bridge}/catalog/movie/streamplay/search=Sintel.json`);
    }, bridge);
    await page.reload();
    await page.locator('button[data-profile-id]').first().click({ timeout: 30000 });
    if (!fs.existsSync(state)) {
      await page.getByRole('button', { name: 'Settings', exact: true }).click();
      await page.getByRole('button', { name: 'Addons', exact: true }).click();
      await page.getByLabel('Addon link', { exact: true }).fill(`${bridge}/manifest.json`);
      await page.getByRole('button', { name: 'Continue', exact: true }).click();
      await page.getByRole('button', { name: 'Install', exact: true }).click();
      await page.waitForFunction(() => (localStorage.getItem('arvio.web.installed.addons') || '').includes('arvio.local.cloudstream.streamplay.poc'));
      await page.evaluate(() => localStorage.removeItem('arvio.web.catalogRows.v3'));
      await page.reload();
      await page.locator('button[data-profile-id]').first().click({ timeout: 30000 });
    }
    await page.getByText('Sintel', { exact: true }).first().click({ timeout: 30000 });
    await page.waitForFunction(() => /[1-9]\d* sources/.test(document.body.innerText), {}, { timeout: 60000 });
    const start = Date.now();
    playing = true;
    const playButton = page.getByRole('button', { name: 'Play', exact: true });
    if (appleTest) await playButton.tap(); else await playButton.click();
    await page.locator('video').waitFor({ state: 'attached' });
    await page.evaluate(() => {
      const v = document.querySelector('video');
      window.__qaPresentedFrames = 0;
      const tick = () => { window.__qaPresentedFrames++; v.requestVideoFrameCallback(tick); };
      v.requestVideoFrameCallback(tick);
    });
    await page.waitForFunction(() => { const v = document.querySelector('video'); return v && v.currentTime > 3 && v.videoWidth > 0; }, {}, { timeout: 45000 });
    const sample = () => page.evaluate(() => { const v = document.querySelector('video'), q = v.getVideoPlaybackQuality();
      return { time: v.currentTime, width: v.videoWidth, height: v.videoHeight, frames: q.totalVideoFrames, dropped: q.droppedVideoFrames,
        audioBytes: v.webkitAudioDecodedByteCount, audioTracks: v.audioTracks?.length, presentedFrames: window.__qaPresentedFrames, paused: v.paused,
        directNativeHost: v.currentSrc.startsWith('https:') ? new URL(v.currentSrc).hostname : null }; });
    const first = await sample(), timeToThreeSecondsMs = Date.now() - start;
    assert.ok(first.frames > 0 && (first.audioBytes > 0 || appleTest && first.audioTracks > 0), 'decoded picture and audio indicator');
    const seekStart = Date.now();
    await page.evaluate(() => { document.querySelector('video').currentTime = 120; });
    await page.waitForFunction(() => { const v = document.querySelector('video'); return v.currentTime > 122 && !v.seeking; }, {}, { timeout: 30000 });
    const afterSeek = await sample(), seekToTwoSecondsMs = Date.now() - seekStart;
    console.log(JSON.stringify({ first, afterSeek }));
    // Native Apple HLS can reset decoded-byte/frame counters on a seek.
    // A separate presented-frame callback count proves new pictures arrived.
    assert.ok(afterSeek.presentedFrames > first.presentedFrames && !afterSeek.paused);
    if (first.audioBytes !== undefined) assert.ok(afterSeek.audioBytes > 0);
    assert.ok(media.length > 0 || afterSeek.directNativeHost, 'real remote source responses or native HTTPS media');
    assert.ok(media.every(r => !['127.0.0.1', 'localhost'].includes(r.host)), 'no ARVIO video relay');
    assert.deepEqual(relayRequests, [], 'no ARVIO proxy or remux requests');
    const prefix = appleTest ? 'webkit-' : '';
    await page.screenshot({ path: path.join(evidence, `${prefix}actual-web-playback.png`) });
    const health = await fetch(`${bridge}/health`).then(r => r.json());
    assert.equal(health.metrics.mediaRelayBytes, 0);
    const result = { testedAt: new Date().toISOString(), browser: browser.version(), platform: process.platform, engine: appleTest ? 'webkit' : 'chromium', physicalIpad: false, capabilities, dedicatedHeadlessShell: !appleTest,
      title: 'Sintel', timeToThreeSecondsMs, seekToTwoSecondsMs, first, afterSeek,
      mediaHosts: [...new Set(media.map(r => r.host))], mediaResponses: media.length, mediaRelayBytes: health.metrics.mediaRelayBytes, pageErrors: errors };
    fs.writeFileSync(path.join(evidence, `${prefix}web-playback-result.json`), JSON.stringify(result, null, 2));
    console.log(JSON.stringify(result));
  } catch (error) {
    console.log(await page.evaluate(() => ({ text: document.body.innerText.slice(-1400),
      registry: JSON.parse(localStorage.getItem('arvio.web.addonNativeItems.v1') || '{}'),
      rows: JSON.parse(localStorage.getItem('arvio.web.catalogRows.v3') || '{}') })));
    throw error;
  } finally { await browser.close(); }
})().catch(error => { console.error(error.message); process.exitCode = 1; });

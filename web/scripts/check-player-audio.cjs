// Run against tests/playback-ui-server.cjs. Optional selected URL stays in memory.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { chromium } = require('@playwright/test');

(async () => {
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  try {
    const page = await browser.newPage({ viewport: { width: 1280, height: 900 } });
    await page.goto(process.env.PLAYBACK_FIXTURE_URL || 'http://127.0.0.1:3099');
    if (process.env.PLAYBACK_SELECTED_URL) {
      // Follow only the explicitly selected file, never probe a source list.
      const response = await fetch(process.env.PLAYBACK_SELECTED_URL, {
        headers: { Range: 'bytes=0-0' }, signal: AbortSignal.timeout(15000)
      });
      assert.ok(response.ok, `Selected source HTTP ${response.status}`);
      const url = response.url;
      await response.body?.cancel();
      await page.locator('#remote').fill(url);
      await page.locator('#remote-play').click();
    } else {
      await page.getByRole('button', { name: 'Two audio tracks', exact: true }).click();
    }
    const read = () => page.locator('#status').evaluate(el => JSON.parse(el.textContent));
    await page.waitForFunction(() => {
      const state = JSON.parse(document.querySelector('#status').textContent);
      return state.phase === 'failed' || (state.phase === 'playing' && state.audioRms > 0.001 && state.time > 2);
    }, null, { timeout: 60000 });
    const initial = await read();
    assert.equal(initial.phase, 'playing', initial.error);
    assert.equal(initial.error, '');
    assert.ok(initial.audioRms > 0.001, 'Decoded sound must not be silent');
    assert.ok(initial.frames > 0, 'Video frames must also render');
    assert.ok(initial.probe.audioTracks.length >= 2, 'Container audio tracks must be discovered');
    const audio = initial.probe.audioTracks[initial.probe.chosenAudioIndex];
    assert.match(audio.language, /^en(?:g)?$/i, 'Choose the English track');
    await page.getByRole('button', { name: 'Seek to 12s', exact: true }).click();
    await page.waitForFunction(() => {
      const state = JSON.parse(document.querySelector('#status').textContent);
      return state.time > 13 && !state.seeking && state.audioRms > 0.001;
    }, null, { timeout: 30000 });
    const afterSeek = await read();
    assert.equal(afterSeek.error, '');
    await page.getByRole('button', { name: 'Second audio track', exact: true }).click();
    await page.waitForFunction(() => {
      const state = JSON.parse(document.querySelector('#status').textContent);
      return state.phase === 'failed' || (state.selectedAudioIndex === 1 && state.phase === 'playing' && state.time > 14 && state.audioRms > 0.001);
    }, null, { timeout: 60000 });
    const afterSwitch = await read();
    assert.equal(afterSwitch.error, '');
    assert.equal(afterSwitch.phase, 'playing');
    assert.equal(afterSwitch.selectedAudioIndex, 1);
    assert.ok(afterSwitch.time >= afterSeek.time, 'Changing audio must keep the playhead');
    assert.ok(afterSwitch.audioRms > 0.001, 'The newly selected track must produce sound');
    const output = path.resolve(__dirname, '../../artifacts/player-audio');
    fs.mkdirSync(output, { recursive: true });
    // Status contains no provider URLs, tokens or account identifiers.
    fs.writeFileSync(path.join(output, process.env.PLAYBACK_SELECTED_URL ? 'selected-source.json' : 'synthetic.json'), JSON.stringify({ initial, afterSeek, afterSwitch }, null, 2));
    await page.screenshot({ path: path.join(output, process.env.PLAYBACK_SELECTED_URL ? 'selected-source.png' : 'synthetic.png') });
    await page.getByRole('button', { name: 'Close', exact: true }).click();
    console.log(JSON.stringify({ verified: true, tracks: initial.probe.audioTracks.length, language: audio.language,
      codec: audio.codec, converted: !audio.passthrough, audioRms: initial.audioRms, probeMs: initial.probeMs,
      firstFrameMs: initial.firstFrameMs, seekAudioRms: afterSeek.audioRms, switchedTrack: afterSwitch.selectedAudioIndex,
      switchedAudioRms: afterSwitch.audioRms }));
  } finally { await browser.close(); }
})().catch(error => { console.error(error.message); process.exitCode = 1; });

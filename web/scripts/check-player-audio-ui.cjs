// Render the production Audio panel with deterministic track lists; no account or streams are opened.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const ts = require('typescript');
const { build } = require('esbuild');
const { chromium } = require('@playwright/test');

async function main() {
  const root = path.resolve(__dirname, '..');
  const source = ts.createSourceFile('PlayerOverlay.tsx', fs.readFileSync(path.join(root, 'components/player/PlayerOverlay.tsx'), 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  const panels = [];
  function visit(node) {
    if (ts.isJsxElement(node) && node.openingElement.attributes.properties.some(attr =>
      ts.isJsxAttribute(attr) && attr.name.text === 'className' && attr.initializer?.text === 'player-side-panel')) panels.push(node);
    ts.forEachChild(node, visit);
  }
  visit(source);
  assert.equal(panels.length, 1);
  const bundle = await build({ stdin: { loader: 'tsx', resolveDir: root, contents: `
    import React, { useState } from 'react';
    import { createRoot } from 'react-dom/client';
    import { Check, X } from 'lucide-react';
    const translateUi = text => text;
    const tracks = [
      { index: 0, label: 'RUS / TVShows / AC-3 / 6ch / converted', codec: 'ac-3', browserPlayable: true },
      { index: 1, label: 'RUS / Main audio / AC-3 / 2ch / converted', codec: 'ac-3', browserPlayable: true },
      { index: 2, label: 'RUS / LE-Production / AC-3 / 2ch / converted', codec: 'ac-3', browserPlayable: true },
      { index: 3, label: 'EN / EC-3 / 6ch / converted', codec: 'ec-3', browserPlayable: true },
      { index: 4, label: 'EN / TRUEHD / 8ch', codec: 'truehd', browserPlayable: false }
    ];
    function Fixture() {
      const [activePanel, setActivePanel] = useState('audio');
      const [remuxAudioIndex, switchRemuxAudio] = useState(3);
      const [audioProbeState, setPhase] = useState(location.pathname === '/failed' ? 'failed' : 'done');
      const [remuxTracks, setTracks] = useState(['/failed', '/native', '/loading'].includes(location.pathname) ? [] : location.pathname === '/single' ? [tracks[0]] : tracks);
      const [nativeIndex, selectAudioTrack] = useState('en');
      const stream = { remux: location.pathname === '/loading' };
      const buffering = stream.remux;
      const error = false;
      const transportTracks = { audioTracks: location.pathname === '/native' ? [
        { id: 'en', label: 'English', language: 'en' }, { id: 'nl', label: 'Dutch', language: 'nl' }
      ] : [], selectedAudioTrackId: nativeIndex };
      const transportRef = { current: { selectAudioTrack } };
      const probeAudioTracks = () => { setTracks(tracks); setPhase('done'); };
      return <main className="player-overlay">{activePanel && ${panels[0].getText(source)}}</main>;
    }
    createRoot(document.getElementById('root')).render(<Fixture />);
  ` }, bundle: true, write: false, jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"' } });
  const css = fs.readFileSync(path.join(root, 'app/globals.css'));
  const server = http.createServer((req, res) => {
    if (req.url === '/app.js') { res.setHeader('Content-Type', 'text/javascript'); res.end(bundle.outputFiles[0].contents); }
    else if (req.url === '/styles.css') { res.setHeader('Content-Type', 'text/css'); res.end(css); }
    else res.end('<!doctype html><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/styles.css"><div id="root"></div><script src="/app.js"></script>');
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  const origin = `http://127.0.0.1:${server.address().port}`;
  const output = path.resolve(root, '../artifacts/player-audio');
  fs.mkdirSync(output, { recursive: true });
  try {
    for (const [name, width, height] of [['desktop', 1440, 900], ['phone', 390, 844], ['small-phone', 320, 568], ['landscape', 844, 390]]) {
      const page = await browser.newPage({ viewport: { width, height } });
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.goto(origin);
      const rows = page.locator('.player-panel-row');
      await rows.first().waitFor();
      assert.equal(await rows.count(), 5, 'All tracks, including unsupported codecs, remain listed');
      assert.equal(await rows.nth(4).isDisabled(), true);
      await rows.nth(1).click();
      assert.match(await rows.nth(1).getAttribute('class'), /is-active/);
      await rows.nth(2).focus();
      await page.keyboard.press('Enter');
      assert.match(await rows.nth(2).getAttribute('class'), /is-active/);
      const overflow = await page.locator('.player-panel-list').evaluate(el => el.scrollWidth > el.clientWidth + 1);
      assert.equal(overflow, false);
      assert.equal(await rows.first().locator('strong').evaluate(el => getComputedStyle(el).whiteSpace), 'normal');
      await page.screenshot({ path: path.join(output, `audio-menu-${name}.png`) });
      await page.goto(origin + '/single');
      await rows.first().waitFor();
      assert.equal(await rows.count(), 1, 'Do not hide the only audio track');
      await page.goto(origin + '/failed');
      await page.getByRole('button', { name: 'Retry', exact: true }).click();
      assert.equal(await rows.count(), 5, 'Failed reads can be retried');
      await page.goto(origin + '/native');
      await rows.first().waitFor();
      assert.equal(await rows.count(), 2);
      await page.getByRole('button', { name: 'Dutch nl' }).click();
      assert.match(await rows.nth(1).getAttribute('class'), /is-active/);
      await page.goto(origin + '/loading');
      await page.getByText('Reading audio tracks from this source', { exact: false }).waitFor();
      assert.equal(await page.getByText('no other selectable tracks', { exact: false }).count(), 0);
      assert.deepEqual(errors, []);
      await page.close();
    }
    console.log('Audio menu passed: 4 viewports, all/single/unsupported/native tracks, retry, click and keyboard selection.');
  } finally { await browser.close(); await new Promise(resolve => server.close(resolve)); }
}
main().catch(error => { console.error(error); process.exitCode = 1; });

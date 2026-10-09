// Local regression fixture: production subtitle effects/menu, cross-origin media and relay.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const { execFileSync } = require('node:child_process');
const ts = require('typescript');
const { build } = require('esbuild');
const { chromium, expect } = require('@playwright/test');

async function main() {
  const root = path.resolve(__dirname, '..');
  const output = path.resolve(root, '../artifacts/player-subtitles');
  fs.mkdirSync(output, { recursive: true });
  const media = path.join(output, 'sample.mp4');
  execFileSync('ffmpeg', ['-v', 'error', '-y', '-f', 'lavfi', '-i', 'color=c=0x15191c:s=640x360:r=10',
    '-t', '15', '-c:v', 'libx264', '-pix_fmt', 'yuv420p', '-movflags', '+faststart', media]);
  const requests = [];
  let failed = false;
  const relay = http.createServer((req, res) => {
    requests.push(req.url);
    // Deliberately no video CORS headers: fixing captions must not break native video.
    if (req.url === '/sample.mp4') { res.setHeader('Content-Type', 'video/mp4'); res.end(fs.readFileSync(media)); return; }
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Content-Type', 'text/vtt');
    if (req.url === '/retry' && !failed) { failed = true; res.writeHead(503).end(); return; }
    const body = req.url === '/nl' ? '1\n00:00:00,000 --> 00:02:00,000\nNederlandse ondertiteling werkt.'
      : 'WEBVTT\n\n00:00:00.000 --> 00:02:00.000\nEnglish subtitles are visible.\n';
    if (req.url === '/slow') setTimeout(() => res.end(body), 1200);
    else res.end(body);
  });
  await new Promise(resolve => relay.listen(0, '127.0.0.1', resolve));
  const relayOrigin = `http://127.0.0.1:${relay.address().port}`;
  const source = ts.createSourceFile('PlayerOverlay.tsx', fs.readFileSync(path.join(root, 'components/player/PlayerOverlay.tsx'), 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  const player = source.statements.find(node => ts.isFunctionDeclaration(node) && node.name.text === 'VideoPlayer');
  const statements = player.body.statements.map(node => node.getText(source));
  const take = predicate => {
    const matches = statements.filter(predicate);
    assert.equal(matches.length, 1, 'Production effect/selection must exist exactly once');
    return matches[0];
  };
  const state = ['const [activeSubtitle,', 'const [subtitleState,', 'const [subtitleRetry,', 'const manualSubtitle =', 'const selectSubtitle =']
    .map(prefix => take(text => text.startsWith(prefix))).join('\n');
  const effects = [
    text => text.startsWith('useEffect(') && text.includes('const manual = manualSubtitle.current'),
    text => text.startsWith('const selectedSubtitle ='),
    text => text.startsWith('useEffect(') && text.includes('attachExternalSubtitle('),
    text => text.startsWith('useEffect(') && text.includes('positionSubtitleCues(')
  ].map(take).join('\n');
  const helpers = source.statements.filter(node => ts.isFunctionDeclaration(node) && ['defaultSubtitleIndex', 'orderedSubtitles'].includes(node.name.text)).map(node => node.getText(source)).join('\n');
  let panel;
  function visit(node) {
    if (ts.isJsxElement(node) && node.openingElement.attributes.properties.some(attr =>
      ts.isJsxAttribute(attr) && attr.name.text === 'className' && attr.initializer?.text === 'player-side-panel')) panel = node.getText(source);
    ts.forEachChild(node, visit);
  }
  visit(player);
  assert.ok(panel);
  const bundle = await build({ stdin: { loader: 'tsx', resolveDir: root, contents: `
    import React, { useState, useEffect, useRef } from 'react';
    import { createRoot } from 'react-dom/client';
    import { Check, X } from 'lucide-react';
    import { attachExternalSubtitle, positionSubtitleCues } from './lib/playerSubtitles';
    import { subtitleLanguageName } from './lib/subtitleAi';
    const translateUi = text => text;
    const resolverSubtitleUrl = url => url;
    ${helpers}
    const relay = ${JSON.stringify(relayOrigin)};
    function Fixture() {
      const videoRef = useRef(null);
      const [activePanel, setActivePanel] = useState('subtitles');
      const [aiSubsActive, setAiSubsActive] = useState(false);
      const aiAvailable = false;
      const liveTv = false;
      const [settings, setSettings] = useState({ defaultSubtitle: 'en', subtitleOffsetMs: 0 });
      const subtitleLinePercent = 68;
      const [stream, setStream] = useState({ url: relay + '/sample.mp4', subtitles: [
        { url: relay + '/en', lang: 'en', label: 'English' },
        { url: relay + '/nl', lang: 'nl', label: 'Dutch' },
        { url: relay + '/slow', lang: 'fr', label: 'Slow subtitle' },
        { url: relay + '/retry', lang: 'de', label: 'Retry subtitle' }
      ] });
      ${state}
      ${effects}
      return <>
        <main className="player-overlay">
          <style>{'.player-overlay video::cue { color:white; background:#000; font:24px sans-serif; }'}</style>
          <video ref={videoRef} src={stream.url} autoPlay muted playsInline loop />
          {activePanel && ${panel}}
        </main>
        <nav style={{ position:'fixed', top:12, left:12, zIndex:100, display:'flex', gap:8 }}>
          <button onClick={() => setStream(s => ({ ...s, subtitles: [...s.subtitles] }))}>Refresh metadata</button>
          <button onClick={() => setSettings(s => ({ ...s, subtitleOffsetMs: s.subtitleOffsetMs + 1000 }))}>Offset</button>
          <button onClick={() => { videoRef.current.currentTime = 6; }}>Seek</button>
          <button onClick={() => setActivePanel('subtitles')}>Subtitles</button>
          <output data-testid="load-state">{subtitleState.status}</output>
        </nav>
      </>;
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
  let browser;
  try {
    browser = await chromium.launch({ channel: 'msedge', headless: true });
    for (const [name, width, height] of [['desktop', 1440, 900], ['phone', 390, 844]]) {
      const page = await browser.newPage({ viewport: { width, height } });
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.goto(`http://127.0.0.1:${server.address().port}`);
      const status = page.getByTestId('load-state');
      await expect(status).toHaveText('ready');
      const captions = () => page.locator('video').evaluate(video => Array.from(video.textTracks).filter(track => track.mode === 'showing').flatMap(track => Array.from(track.activeCues || []).map(cue => cue.text)));
      await expect.poll(captions).toEqual(['English subtitles are visible.']);
      assert.equal(await page.locator('video').getAttribute('crossorigin'), null);
      await page.getByRole('button', { name: 'Dutch', exact: false }).click();
      await expect(status).toHaveText('ready');
      await expect.poll(captions).toEqual(['Nederlandse ondertiteling werkt.']);
      const downloads = requests.length;
      await page.getByRole('button', { name: 'Refresh metadata' }).click();
      await expect.poll(captions).toEqual(['Nederlandse ondertiteling werkt.']);
      assert.equal(requests.length, downloads, 'Metadata refresh must not refetch subtitles');
      await page.getByRole('button', { name: 'Offset', exact: true }).click();
      await page.getByRole('button', { name: 'Offset', exact: true }).click();
      await expect.poll(() => page.locator('video track').evaluate(el => el.track.cues[0].startTime)).toBe(2);
      await page.getByRole('button', { name: 'Seek', exact: true }).click();
      await expect.poll(captions).toEqual(['Nederlandse ondertiteling werkt.']);
      await page.getByRole('button', { name: 'Close panel' }).click();
      await page.screenshot({ path: path.join(output, `subtitles-${name}.png`) });
      await page.getByRole('button', { name: 'Subtitles', exact: true }).click();
      await page.getByRole('button', { name: 'Off No subtitle track', exact: true }).click();
      await expect(status).toHaveText('off');
      await expect.poll(captions).toEqual([]);
      assert.equal(await page.locator('video track').count(), 0);
      await page.getByRole('button', { name: 'Refresh metadata' }).click();
      await expect(status).toHaveText('off');
      assert.equal(await page.locator('video track').count(), 0);
      await page.getByRole('button', { name: /Slow subtitle/ }).click();
      await expect(status).toHaveText('loading');
      await page.getByRole('button', { name: 'Dutch', exact: false }).click();
      await expect(status).toHaveText('ready');
      await expect.poll(captions).toEqual(['Nederlandse ondertiteling werkt.']);
      failed = false;
      await page.getByRole('button', { name: /Retry subtitle/ }).click();
      await expect(status).toHaveText('failed');
      await page.getByRole('button', { name: 'Retry', exact: true }).click();
      await expect(status).toHaveText('ready');
      await expect.poll(captions).toEqual(['English subtitles are visible.']);
      assert.deepEqual(errors, []);
      await page.close();
      console.log(`${name}: rendered captions, switch/off, metadata refresh, timing, seek, cancellation and retry passed`);
    }
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
    await new Promise(resolve => relay.close(resolve));
  }
}
main().catch(error => { console.error(error); process.exit(1); });

const assert = require('node:assert/strict');
const { appCss } = require('./app-styles.cjs');
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');

async function main() {
  const root = path.resolve(__dirname, '..');
  const evidence = path.join(root, '../artifacts/pr819-youtube');
  fs.mkdirSync(evidence, { recursive: true });
  const built = await esbuild.build({
    absWorkingDir: root, bundle: true, write: false, format: 'iife', jsx: 'automatic',
    stdin: { resolveDir: root, loader: 'tsx', contents: `
      import React from 'react';
      import { createRoot } from 'react-dom/client';
      import { YouTubeEmbed } from './components/player/YouTubeEmbed';
      window.saved = [];
      window.position = 42;
      window.YT = { Player: class {
        constructor(element, options) { window.options = options; this.element = element; }
        getCurrentTime() { return window.position; }
        getDuration() { return 100; }
        destroy() { this.element.remove(); window.destroyed = true; }
      } };
      const item = { id: -123, title: 'Collection video', mediaType: 'movie', image: '', resumePositionSeconds: 42 };
      const stream = { source: 'YouTube', addonName: 'YouTube', addonId: 'arvio_youtube', url: 'https://www.youtube.com/watch?v=abcDEF12_-x' };
      const root = createRoot(document.getElementById('app'));
      root.render(<section className="player-overlay youtube-player-layout">
        <YouTubeEmbed videoId="abcDEF12_-x" item={item} stream={stream}/>
        <div className="youtube-player-toolbar"><button onClick={() => root.unmount()}>Back</button><h2>Collection video</h2></div>
      </section>);
    ` },
    plugins: [{ name: 'isolated-test-account', setup(build) {
      build.onResolve({ filter: /^@\/lib\/(store|cloud)$/ }, args => ({ path: args.path, namespace: 'mock' }));
      build.onLoad({ filter: /.*/, namespace: 'mock' }, args => ({ contents: args.path.endsWith('store')
        ? `export const authClient = { session: { userId: 'test-account' } }; export const useApp = () => ({ activeProfile: { id: 'test-profile' }, addons: [] }); export const useAppPick = () => useApp(); export const useAppSelector = (select) => select(useApp());`
        : `export async function saveProgress(auth, entry, profileId) { window.saved.push({ entry, profileId }); }` }));
      build.onResolve({ filter: /^@\// }, args => ({ path: path.join(root, args.path.slice(2) + '.ts') }));
    } }]
  });
  const css = appCss(root);
  const server = http.createServer((req, res) => {
    res.setHeader('Content-Type', req.url === '/app.js' ? 'text/javascript' : req.url === '/style.css' ? 'text/css' : 'text/html');
    res.end(req.url === '/app.js' ? built.outputFiles[0].contents : req.url === '/style.css' ? css
      : '<!doctype html><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/style.css"><div id="app"></div><script src="/app.js"></script>');
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  let browser;
  try {
    browser = await chromium.launch({ channel: 'chrome', headless: true });
    for (const [name, width, height] of [['desktop', 1280, 720], ['phone', 390, 844], ['landscape', 844, 390]]) {
      const page = await browser.newPage({ viewport: { width, height } });
      await page.route('https://www.youtube-nocookie.com/**', route => route.fulfill({ contentType: 'text/html', body: '<body style="margin:0;background:#222;color:white;display:grid;place-items:center;height:100vh">YouTube test player</body>' }));
      await page.goto(`http://127.0.0.1:${server.address().port}`);
      await page.waitForFunction(() => !!window.options);
      assert.equal(await page.evaluate(() => window.options.playerVars.start), 42);
      const frame = page.locator('iframe');
      const box = await frame.boundingBox();
      assert(box && box.width >= 200 && box.height >= 200 && box.x >= 0 && box.y >= 0 && box.x + box.width <= width + 1 && box.y + box.height <= height + 1);
      assert.equal(await frame.getAttribute('referrerpolicy'), 'strict-origin-when-cross-origin');
      await page.evaluate(() => { window.options.events.onStateChange({ data: 1 }); window.position = 55; window.options.events.onStateChange({ data: 2 }); });
      await page.waitForFunction(() => window.saved.length === 1);
      assert.deepEqual(await page.evaluate(() => ({ position: window.saved[0].entry.position_seconds, profileId: window.saved[0].profileId })), { position: 55, profileId: 'test-profile' });
      await page.screenshot({ path: path.join(evidence, `${name}.png`) });
      await page.getByRole('button', { name: 'Back', exact: true }).click();
      await page.waitForFunction(() => window.destroyed === true);
      assert.equal(await page.locator('iframe').count(), 0);
      await page.close();
    }
    console.log('YouTube embed: resume, scoped checkpoint, teardown and desktop/phone/landscape layout passed. Playback API mocked; no real account or YouTube media was used.');
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
  }
}
main().catch(error => { console.error(error); process.exitCode = 1; });

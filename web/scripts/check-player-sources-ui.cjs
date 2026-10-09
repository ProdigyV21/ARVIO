/* Exercise the production JSX and styles without opening streams or changing account data. */
const assert = require('node:assert/strict');
const fs = require('node:fs');
const http = require('node:http');
const path = require('node:path');
const ts = require('typescript');
const { build } = require('esbuild');
const { chromium } = require('@playwright/test');

const root = path.resolve(__dirname, '..');
const evidence = path.resolve(root, '../artifacts/player-sources-ui');
const styles = ['globals.css', 'premium.css', 'tv-guide.css', 'source-setup.css'];

function jsxWithClass(file, className) {
  const source = ts.createSourceFile(file, fs.readFileSync(path.join(root, file), 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  const matches = [];
  function visit(node) {
    if (ts.isJsxElement(node) && node.openingElement.attributes.properties.some(attr =>
      ts.isJsxAttribute(attr) && attr.name.text === 'className' && attr.initializer &&
      ts.isStringLiteral(attr.initializer) && attr.initializer.text === className)) matches.push(node);
    ts.forEachChild(node, visit);
  }
  visit(source);
  assert.equal(matches.length, 1, `Expected one ${className}`);
  return matches[0].getText(source);
}

async function main() {
  const panel = jsxWithClass('components/player/PlayerOverlay.tsx', 'player-side-panel');
  const actions = jsxWithClass('components/details/DetailsDrawer.tsx', 'detail-actions');
  const bundle = await build({
    stdin: { contents: `
      import React, { useState } from 'react';
      import { createRoot } from 'react-dom/client';
      import { Check, Copy, Play, ExternalLink, X, Folder, Trash2, Bookmark, BadgeCheck } from 'lucide-react';
      const translateUi = text => text;
      const sourceList = Array.from({ length: 16 }, (_, index) => ({
        source: index === 1 ? 'VeryLongUnbrokenSourceName'.repeat(7) :
          index === 2 ? 'Media library / Director cut / Extended edition / High bitrate / Original audio' :
          ['Library 4K', 'Cinema archive', 'Home server'][index % 3],
        addonName: index === 3 ? 'Long provider name '.repeat(8) : 'Home library', quality: '4K', size: '8.5 GB',
        url: 'https://media.invalid/' + index
      }));
      const isSameStream = (a, b) => a.url === b.url;
      const streamMeta = stream => [stream.addonName, stream.quality, stream.size].join(' - ');
      window.testActions = [];
      const record = (name, candidate, options) => window.testActions.push({ name, url: candidate?.url, options });
      function Fixture() {
        const [activePanel, setActivePanel] = useState('sources');
        const [stream, setStream] = useState(sourceList[0]);
        const [sourcePickerVisible, setSourcePickerVisible] = useState(false);
        const onSelectStream = (candidate, options) => { record('play', candidate, options); setStream(candidate); };
        const openExternal = (player, candidate) => record(player, candidate);
        const openAnyPlayer = candidate => record('player', candidate);
        const copyUrl = candidate => record('copy', candidate);
        const playBest = () => record('best');
        const continueLabel = 'Play';
        const inWatchlist = false;
        const detailWatched = false;
        const displayItem = { trailerUrl: 'https://media.invalid/trailer' };
        const toggleWatchlist = () => record('watchlist');
        const markWatched = () => record('watched');
        const playTrailer = () => record('trailer');
        if (location.pathname === '/details') return <main style={{padding: '32px', maxWidth: '100%'}}>
          <h1>Film details</h1>{${actions}}{sourcePickerVisible && <div role="status">Source picker opened</div>}
        </main>;
        return <div className="player-overlay">
          <img src="/backdrop.jpg" alt="" style={{position: 'absolute', width: '100%', height: '100%', objectFit: 'cover', opacity: 0.4}} />
          <div className="player-top"><div className="player-title"><div><h2>Playback sources</h2></div></div></div>
          {!activePanel && <button type="button" className="player-icon-btn" aria-label="Sources"
            style={{position: 'absolute', right: 30, bottom: 30}} onClick={() => setActivePanel('sources')}><Folder /></button>}
          {activePanel && ${panel}}
        </div>;
      }
      createRoot(document.getElementById('root')).render(<Fixture />);
    `, resolveDir: root, loader: 'tsx' },
    bundle: true, write: false, jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"' },
  });
  const files = new Map(styles.map(name => ['/' + name, { type: 'text/css', data: fs.readFileSync(path.join(root, 'app', name)) }]));
  files.set('/app.js', { type: 'text/javascript', data: bundle.outputFiles[0].contents });
  files.set('/backdrop.jpg', { type: 'image/jpeg', data: fs.readFileSync(path.join(root, '../app/src/androidTest/assets/library/157336-backdrop.jpg')) });
  const server = http.createServer((req, res) => {
    const file = files.get(new URL(req.url, 'http://localhost').pathname);
    res.setHeader('Content-Type', file?.type || 'text/html');
    res.end(file?.data || `<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1">
      ${styles.map(name => `<link rel="stylesheet" href="/${name}">`).join('')}
      </head><body><div id="root"></div><script src="/app.js"></script></body></html>`);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  fs.mkdirSync(evidence, { recursive: true });
  const url = `http://127.0.0.1:${server.address().port}`;
  const failures = [];
  try {
    for (const [name, width, height] of [
      ['desktop', 1440, 900], ['desktop-short', 1280, 600], ['tablet', 768, 1024],
      ['phone', 390, 844], ['phone-small', 320, 568], ['phone-landscape', 844, 390],
    ]) {
      const page = await browser.newPage({ viewport: { width, height } });
      const errors = [];
      page.on('pageerror', error => errors.push(error.message));
      await page.route('**/*', route => new URL(route.request().url()).origin === url ? route.continue() : route.abort());
      try {
        await page.goto(url);
        await page.locator('.player-panel-row').first().waitFor();
        await page.evaluate(() => Promise.all(document.getAnimations().map(animation => animation.finished)));
        await page.screenshot({ path: path.join(evidence, `${name}-sources.png`) });
        if (name === 'desktop') await page.locator('.player-side-panel').screenshot({ path: path.join(evidence, 'source-panel.png') });
        const layout = await page.locator('.player-panel-list').evaluate(list => {
          const panel = list.closest('.player-side-panel').getBoundingClientRect();
          const rows = [...list.querySelectorAll('.player-panel-row')];
          const outside = (outer, inner) => inner.left < outer.left - 1 || inner.right > outer.right + 1 ||
            inner.top < outer.top - 1 || inner.bottom > outer.bottom + 1;
          return {
            overflow: list.scrollWidth > list.clientWidth + 1,
            panelOutside: panel.left < 0 || panel.right > innerWidth || panel.top < 0 || panel.bottom > innerHeight,
            listHeight: list.clientHeight,
            titleWrapping: getComputedStyle(rows[1].querySelector('strong')).whiteSpace,
            titleHeight: rows[1].querySelector('strong').getBoundingClientRect().height,
            clipped: rows.flatMap((row, index) => [...row.querySelectorAll('button')]
              .filter(button => outside(row.getBoundingClientRect(), button.getBoundingClientRect())).map(() => index)),
          };
        });
        assert.equal(layout.overflow, false, 'Source list must not scroll horizontally');
        assert.equal(layout.panelOutside, false, 'Panel stays in viewport');
        assert.equal(layout.titleWrapping, 'normal', 'Long source names can wrap');
        assert(layout.titleHeight > 30 && layout.titleHeight <= 44, 'Long names use two lines without enlarging the row indefinitely');
        assert(layout.listHeight >= 90, 'Short screens retain usable source-list space');
        assert.deepEqual(layout.clipped, [], 'All action buttons stay inside their own source row');
        const rows = page.locator('.player-panel-row');
        for (const [label, expected] of [['VLC', 'vlc'], ['Player', 'player'], ['Copy stream URL', 'copy']]) {
          await rows.first().getByRole('button', { name: label, exact: true }).click();
          assert.equal(await page.evaluate(() => window.testActions.at(-1).name), expected);
        }
        const play = rows.nth(2).getByRole('button', { name: 'Play', exact: true });
        await play.focus();
        await page.keyboard.press('Enter');
        await page.locator('.player-side-panel').waitFor({ state: 'detached' });
        assert.deepEqual(await page.evaluate(() => window.testActions.at(-1)),
          { name: 'play', url: 'https://media.invalid/2', options: { forceBrowser: true } });
        await page.getByRole('button', { name: 'Sources', exact: true }).click();
        assert(await rows.nth(2).evaluate(node => node.classList.contains('is-active')), 'Selected row is retained');
        await rows.last().getByRole('button', { name: 'Copy stream URL' }).click();
        assert(await page.locator('.player-panel-list').evaluate(node => node.scrollTop > 0), 'Last source is reachable');
        await page.getByRole('button', { name: 'Close panel', exact: true }).click();
        await page.locator('.player-side-panel').waitFor({ state: 'detached' });

        await page.goto(url + '/details');
        const sources = page.getByRole('button', { name: 'Sources', exact: true });
        await sources.waitFor();
        await page.screenshot({ path: path.join(evidence, `${name}-details.png`) });
        assert.equal(await sources.locator('svg').count(), 1, 'Sources has the shared action icon');
        const actionStyles = await page.locator('.detail-actions button.secondary').evaluateAll(buttons => buttons.map(button => {
          const css = getComputedStyle(button);
          return { height: button.getBoundingClientRect().height, background: css.backgroundColor, borderRadius: css.borderRadius,
            fontSize: css.fontSize, padding: css.padding, display: css.display, gap: css.gap };
        }));
        for (const sibling of actionStyles.slice(1)) assert.deepEqual(actionStyles[0], sibling, 'Secondary detail buttons share styling');
        assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, 'Details actions fit viewport');
        await sources.focus();
        await page.keyboard.press('Enter');
        await page.getByRole('status').waitFor();
        assert.deepEqual(errors, [], 'No runtime errors');
        console.log(`${name}: passed`);
      } catch (error) {
        failures.push(`${name}: ${error.message}`);
      } finally {
        await page.close();
      }
    }
    assert.deepEqual(failures, []);
  } finally {
    await browser.close();
    await new Promise(resolve => server.close(resolve));
  }
  console.log(`Screenshots: ${evidence}`);
}
main().catch(error => { console.error(error); process.exitCode = 1; });

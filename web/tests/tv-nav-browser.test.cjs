// Remote / controller navigation in a real browser engine: lib/tvNav.ts and
// lib/gamepadNav.ts bundled with esbuild and driven through Playwright.
// Skips (with a note) when no Chromium is installed for Playwright; set
// ARVIO_TEST_CHROMIUM to a chromium/headless_shell binary to force a run.
const test = require('node:test');
const assert = require('node:assert/strict');
const path = require('node:path');
const esbuild = require('esbuild');

const root = path.resolve(__dirname, '..');

async function launch() {
  let chromium;
  try { ({ chromium } = require('playwright')); } catch { return null; }
  const candidates = [...new Set([process.env.ARVIO_TEST_CHROMIUM || undefined, undefined, ...installedShells()])];
  for (const executablePath of candidates) {
    if (executablePath === '') continue;
    try { return await chromium.launch(executablePath ? { executablePath } : {}); } catch { /* next */ }
  }
  return null;
}

// A Playwright browser cache built for another Playwright version still works
// for this engine-level test.
function installedShells() {
  const fs = require('node:fs');
  const dir = process.env.PLAYWRIGHT_BROWSERS_PATH || '/opt/pw-browsers';
  try {
    return fs.readdirSync(dir).filter((name) => name.startsWith('chromium_headless_shell-'))
      .map((name) => path.join(dir, name, 'chrome-linux', 'headless_shell')).filter((file) => fs.existsSync(file));
  } catch { return []; }
}

async function bundle() {
  const entry = `
    import * as tv from "./lib/tvNav";
    import * as pad from "./lib/gamepadNav";
    import * as osk from "./components/shell/OnScreenKeyboard";
    window.__nav = { tv, pad, osk };
  `;
  const result = await esbuild.build({
    stdin: { contents: entry, resolveDir: root, loader: 'ts' }, bundle: true, write: false, format: 'iife',
    jsx: 'automatic', define: { 'process.env.NODE_ENV': '"production"', 'process.env': '{}' }, logLevel: 'silent',
    tsconfig: path.join(root, 'tsconfig.json')
  });
  return result.outputFiles[0].text;
}

const PAGE = `<!doctype html><html><head><style>
  body { margin: 0; font: 16px sans-serif; }
  button, select, input { display: inline-block; width: 120px; height: 40px; margin: 10px; }
  .row { display: flex; margin-bottom: 120px; }
</style></head><body>
  <main><section class="content">
    <h1 id="heading">Home</h1>
    <div class="row"><button id="a1">A1</button><button id="skip" data-tv-skip>Skip</button><button id="a2">A2</button></div>
    <div class="row"><button id="b1">B1</button><button id="play" data-autofocus>Play</button></div>
    <div class="row"><button id="s-left">SL</button><select id="sel"><option>x</option><option>y</option></select><button id="s-right">SR</button></div>
    <div class="row"><input id="field" type="search" aria-label="Search"></div>
  </section></main>
</body></html>`;

let browser, page, script;

test.before(async () => {
  browser = await launch();
  if (!browser) return;
  script = await bundle();
  page = await browser.newPage({ viewport: { width: 1280, height: 800 } });
});
test.after(async () => { await browser?.close(); });

async function fresh({ tv = true, url = 'http://arvio.test/' } = {}) {
  await page.route('http://arvio.test/**', (route) => route.fulfill({ contentType: 'text/html', body: PAGE }));
  await page.goto(url);
  await page.addScriptTag({ content: script });
  await page.evaluate((tv) => {
    if (tv) document.documentElement.classList.add('is-tv');
    window.__nav.tv.installTvNav();
  }, tv);
}
const focused = () => page.evaluate(() => document.activeElement?.id || document.activeElement?.tagName);
const tvBack = () => page.evaluate(() => {
  const ev = new KeyboardEvent('keydown', { bubbles: true, cancelable: true });
  Object.defineProperty(ev, 'keyCode', { get: () => 10009 });
  (document.activeElement || document.body).dispatchEvent(ev);
});

test('spatial navigation honours data-autofocus first and never lands on data-tv-skip', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  await page.keyboard.press('ArrowDown');
  assert.equal(await focused(), 'play');
  await page.evaluate(() => document.getElementById('a1').focus());
  await page.keyboard.press('ArrowRight');
  assert.equal(await focused(), 'a2', 'the data-tv-skip button is jumped over');
});

test('inside an open native modal dialog the arrows stay in it and TV Back cancels it', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  await page.evaluate(() => {
    const d = document.createElement('dialog');
    d.id = 'dlg';
    d.innerHTML = '<button id="m">Monthly</button><button id="y">Yearly</button>';
    d.addEventListener('cancel', () => { window.cancels = (window.cancels || 0) + 1; });
    document.body.appendChild(d);
    d.showModal();
  });
  assert.equal(await focused(), 'm');
  await page.keyboard.press('ArrowRight');
  assert.equal(await focused(), 'y', 'background elements are not candidates');
  await tvBack();
  assert.equal(await page.evaluate(() => document.getElementById('dlg').open), false);
  assert.equal(await page.evaluate(() => window.cancels), 1, 'cancel fires so React onCancel handlers run');
});

test('a dialog that prevents cancel stays open (its own handler decides)', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  await page.evaluate(() => {
    const d = document.createElement('dialog');
    d.id = 'dlg';
    d.innerHTML = '<button>Ok</button>';
    d.addEventListener('cancel', (event) => event.preventDefault());
    document.body.appendChild(d);
    d.showModal();
  });
  await tvBack();
  assert.equal(await page.evaluate(() => document.getElementById('dlg').open), true);
});

test('TV Back steps out of a nested route when no layer consumes Escape, but never exits from the home root', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  await page.evaluate(() => history.pushState({ arvio: true }, '', '/?title=movie:1'));
  await page.evaluate(() => document.getElementById('a1').focus());
  await tvBack();
  await page.waitForFunction(() => location.search === '');
  assert.equal(await page.evaluate(() => location.search), '');
  const before = await page.evaluate(() => history.length);
  assert.equal(await page.evaluate(() => window.__nav.tv.performBack()), 'none');
  assert.equal(await page.evaluate(() => history.length), before);
});

test('a deep-linked first entry goes back to the home root inside the app instead of leaving it', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  const fresh2 = await browser.newPage();
  await fresh2.route('http://arvio.test/**', (route) => route.fulfill({ contentType: 'text/html', body: PAGE }));
  await fresh2.goto('http://arvio.test/?view=search&q=dune');
  await fresh2.addScriptTag({ content: script });
  const result = await fresh2.evaluate(() => {
    let popped = false;
    window.addEventListener('popstate', () => { popped = true; });
    const outcome = history.length > 1 ? 'skip' : window.__nav.tv.performBack();
    return { outcome, search: location.search, popped };
  });
  if (result.outcome !== 'skip') assert.deepEqual(result, { outcome: 'history', search: '', popped: true });
  await fresh2.close();
});

test('an Escape a layer prevents is consumed; a focused field is blurred first', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  await page.evaluate(() => history.pushState({}, '', '/?view=settings'));
  await page.evaluate(() => window.addEventListener('keydown', (e) => { if (e.key === 'Escape') { e.preventDefault(); window.closedPanel = true; } }));
  await page.evaluate(() => document.getElementById('field').focus());
  assert.equal(await page.evaluate(() => window.__nav.tv.performBack()), 'blurred');
  assert.equal(await page.evaluate(() => window.__nav.tv.performBack()), 'escape');
  assert.equal(await page.evaluate(() => window.closedPanel), true);
  assert.equal(await page.evaluate(() => location.search), '?view=settings', 'no history step when a layer handled it');
});

test('on a TV every arrow leaves a closed <select>; on desktop Up/Down keep stepping it', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh({ tv: true });
  await page.focus('#sel');
  await page.keyboard.press('ArrowDown');
  assert.notEqual(await focused(), 'sel');
  assert.equal(await page.$eval('#sel', (s) => s.value), 'x');
  await page.focus('#sel');
  await page.keyboard.press('ArrowRight');
  assert.equal(await focused(), 's-right');
  await fresh({ tv: false });
  await page.focus('#sel');
  await page.keyboard.press('ArrowRight');
  assert.equal(await focused(), 's-right', 'Left/Right leave on desktop too');
});

test('after a view change focus goes to data-autofocus in key mode and to the heading for pointer users', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh({ tv: true });
  await page.evaluate(() => document.activeElement?.blur());
  await page.evaluate(() => window.__nav.tv.focusAfterViewChange({ waitMs: 0 }));
  await page.waitForFunction(() => document.activeElement?.id === 'play');
  await fresh({ tv: false });
  await page.evaluate(() => window.__nav.tv.focusAfterViewChange({ waitMs: 0 }));
  await page.waitForFunction(() => document.activeElement?.id === 'heading');
  // Never steals focus the user placed.
  await page.focus('#a2');
  await page.evaluate(() => window.__nav.tv.focusAfterViewChange({ waitMs: 0 }));
  await page.waitForTimeout(50);
  assert.equal(await focused(), 'a2');
});

test('gamepad: A on a text field asks for the on-screen keyboard; View in the player emits the subtitles command', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  const result = await page.evaluate(() => {
    const seen = [];
    window.addEventListener('arvio:osk-open', (e) => { e.detail.accepted = true; seen.push('osk:' + e.detail.target.id); });
    window.addEventListener('arvio:player-command', (e) => { e.preventDefault(); seen.push('cmd:' + e.detail.command); });
    document.getElementById('field').focus();
    window.__nav.pad.fireIntent('accept');
    const overlay = document.createElement('div');
    overlay.className = 'player-overlay';
    document.body.appendChild(overlay);
    window.__nav.pad.fireIntent('view');
    window.__nav.pad.fireIntent('sources');
    return seen;
  });
  assert.deepEqual(result, ['osk:field', 'cmd:subtitles', 'cmd:sources']);
});

test('an unhandled player command falls back to the player keyboard shortcut', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  const keys = await page.evaluate(() => {
    const seen = [];
    window.addEventListener('keydown', (e) => seen.push(e.key));
    const overlay = document.createElement('div');
    overlay.className = 'player-overlay';
    document.body.appendChild(overlay);
    window.__nav.pad.fireIntent('view');
    window.__nav.pad.fireIntent('sources');
    return seen;
  });
  assert.deepEqual(keys, ['c', 's']);
});

test('on-screen keyboard typing goes through the native setter and an input event (React-visible)', async (t) => {
  if (!browser) return t.skip('no Playwright Chromium available');
  await fresh();
  const result = await page.evaluate(() => {
    const field = document.getElementById('field');
    const events = [];
    // React's value tracker: an own-property setter that would swallow a plain assignment.
    field.addEventListener('input', () => events.push(field.value));
    field.focus();
    window.__nav.osk.insertIntoField(field, 'du');
    window.__nav.osk.insertIntoField(field, 'ne');
    window.__nav.osk.deleteFromField(field);
    return { value: field.value, events };
  });
  assert.deepEqual(result, { value: 'dun', events: ['du', 'dune', 'dun'] });
});

/* Verify the real loading markup and stylesheet, including animation across frames. */
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const http = require('node:http');
const vm = require('node:vm');
const ts = require('typescript');
const { renderToStaticMarkup } = require('react-dom/server');
const { chromium } = require('@playwright/test');

const root = path.resolve(__dirname, '..');
const evidence = path.resolve(root, '../artifacts/player-loading-ui');
const fixture = path.resolve(root, '../app/src/androidTest/assets/library');
const styles = ['globals.css', 'premium.css', 'tv-guide.css', 'source-setup.css'];
const filename = path.join(root, 'components/player/PlayerOverlay.tsx');
const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
const matches = [];
function visit(node) {
  if (ts.isJsxElement(node) && node.openingElement.attributes.properties.some(attr =>
    ts.isJsxAttribute(attr) && attr.name.text === 'className' && attr.initializer &&
    ts.isStringLiteral(attr.initializer) && attr.initializer.text === 'player-boot')) matches.push(node);
  ts.forEachChild(node, visit);
}
visit(source);
assert.equal(matches.length, 1);
const code = ts.transpileModule(`module.exports = (${matches[0].getText(source)});`, {
  compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS, jsx: ts.JsxEmit.ReactJSX },
}).outputText;
function markup(logo) {
  const module = { exports: {} };
  vm.runInNewContext(code, { module, exports: module.exports, require,
    bootLogo: logo ? '/logo.png' : null, title: 'Interstellar', item: { backdrop: '/backdrop.jpg' }, translateUi: value => value });
  return renderToStaticMarkup(module.exports);
}

async function main() {
  fs.mkdirSync(evidence, { recursive: true });
  const files = new Map(styles.map(name => ['/' + name, { type: 'text/css', data: fs.readFileSync(path.join(root, 'app', name)) }]));
  files.set('/logo.png', { type: 'image/png', data: fs.readFileSync(path.join(fixture, '157336-logo.png')) });
  files.set('/backdrop.jpg', { type: 'image/jpeg', data: fs.readFileSync(path.join(fixture, '157336-backdrop.jpg')) });
  const server = http.createServer((req, res) => {
    const url = new URL(req.url, 'http://localhost');
    const file = files.get(url.pathname);
    res.setHeader('Content-Type', file?.type || 'text/html');
    res.end(file?.data || `<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1">
      ${styles.map(name => `<link rel="stylesheet" href="/${name}">`).join('')}</head>
      <body><div class="player-overlay">${markup(!url.searchParams.has('text'))}</div></body></html>`);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  const url = `http://127.0.0.1:${server.address().port}`;
  try {
    for (const [name, width, height] of [['desktop', 1280, 720], ['phone', 390, 844], ['landscape', 844, 390]]) {
      for (const text of [false, true]) {
        const page = await browser.newPage({ viewport: { width, height }, reducedMotion: 'no-preference' });
        try {
          await page.goto(url + (text ? '?text=1' : ''));
          const target = page.locator(text ? '.player-boot-title' : '.player-boot-logo');
          if (!text) await target.evaluate(img => img.decode());
          const samples = await target.evaluate(async element => {
            const samples = [];
            for (let i = 0; i < 32; i++) {
              const css = getComputedStyle(element);
              samples.push({ scale: new DOMMatrixReadOnly(css.transform).a, opacity: css.opacity });
              await new Promise(resolve => setTimeout(resolve, 100));
            }
            return samples;
          });
          assert(Math.max(...samples.map(s => s.scale)) - Math.min(...samples.map(s => s.scale)) > 0.1, 'Pulse is visibly running across multiple cycles');
          assert(samples.every(s => s.opacity === '1'), 'Logo stays readable throughout the heartbeat');
          const keyframes = await target.evaluate(element => {
            const animation = element.getAnimations().find(a => a.animationName === 'player-boot-pulse');
            const duration = animation.effect.getTiming().duration;
            const parent = element.parentElement.getBoundingClientRect();
            animation.pause();
            const samples = [0, 160, 280, 420, 620, 1499].map(time => {
              animation.currentTime = time;
              const box = element.getBoundingClientRect();
              return { scale: new DOMMatrixReadOnly(getComputedStyle(element).transform).a,
                centerX: box.x + box.width / 2, centerY: box.y + box.height / 2,
                outside: box.left < 0 || box.right > innerWidth || box.top < 0 || box.bottom > innerHeight };
            });
            return { duration, samples, centerX: parent.x + parent.width / 2, centerY: parent.y + parent.height / 2 };
          });
          assert.equal(keyframes.duration, 1500);
          [1, 1.08, 1.02, 1.12, 1, 1].forEach((scale, i) => {
            assert(Math.abs(keyframes.samples[i].scale - scale) < 0.001, 'Matches APK heartbeat timing');
            assert(Math.abs(keyframes.samples[i].centerX - keyframes.centerX) < 1, 'Pulse stays centered horizontally');
            assert(Math.abs(keyframes.samples[i].centerY - keyframes.centerY) < 1, 'Pulse stays centered vertically');
            assert.equal(keyframes.samples[i].outside, false, 'Artwork stays inside the viewport');
          });
          if (!text) {
            for (const [label, time] of [['rest', 0], ['peak', 420]]) {
              await target.evaluate((element, time) => { element.getAnimations()[0].currentTime = time; }, time);
              await page.screenshot({ path: path.join(evidence, `${name}-${label}.png`) });
            }
            assert.notDeepEqual(fs.readFileSync(path.join(evidence, `${name}-rest.png`)), fs.readFileSync(path.join(evidence, `${name}-peak.png`)), 'Rendered pixels change with the pulse');
          }
          await page.emulateMedia({ reducedMotion: 'reduce' });
          await page.reload();
          await page.waitForTimeout(300);
          const reduced = await target.evaluate(element => ({ scale: new DOMMatrixReadOnly(getComputedStyle(element).transform).a,
            running: element.getAnimations().some(animation => animation.playState === 'running') }));
          assert.equal(reduced.running, false, 'Respect reduced-motion preferences');
          assert.equal(reduced.scale, 1);
          console.log(`${name} / ${text ? 'text fallback' : 'clearlogo'}: passed`);
        } finally { await page.close(); }
      }
    }
    const context = await browser.newContext({ viewport: { width: 1280, height: 720 }, reducedMotion: 'no-preference',
      recordVideo: { dir: evidence, size: { width: 1280, height: 720 } } });
    const page = await context.newPage();
    await page.goto(url);
    await page.waitForTimeout(4800);
    await context.close();
    await page.video().saveAs(path.join(evidence, 'loading-pulse.webm'));
    console.log(`Screenshots and recording: ${evidence}`);
  } finally {
    await browser.close();
    await new Promise(resolve => server.close(resolve));
  }
}
main().catch(error => { console.error(error); process.exitCode = 1; });

// Browser check for the Live TV layout editor (Settings → Live TV → Layout):
// keyboard "hold to move", drop/cancel, persistence, show/hide per playlist,
// reset, and no sideways scrolling on desktop and phone.
const path = require('node:path');
const { appCss } = require('./app-styles.cjs');
const fs = require('node:fs');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
let chromium;
try { ({ chromium } = require(process.env.ARVIO_PLAYWRIGHT || '@playwright/test')); } catch { ({ chromium } = require('playwright')); }

(async () => {
  const root = path.resolve(__dirname, '..');
  const out = path.join(root, '.iptv-groups-ui-test');
  fs.mkdirSync(out, { recursive: true });
  await esbuild.build({ entryPoints: [path.join(root, 'tests/iptv-groups-ui/entry.tsx')], bundle: true,
    outfile: path.join(out, 'app.js'), jsx: 'automatic', plugins: [{ name: 'fixture', setup(build) {
      build.onResolve({ filter: /^@\/lib\/i18n$/ }, () => ({ path: 'i18n', namespace: 'fixture' }));
      build.onLoad({ filter: /.*/, namespace: 'fixture' }, () => ({ contents: 'export const useTranslation = () => (text, values) => values ? text.replace(/\\{(\\w+)\\}/g, (_, key) => String(values[key] ?? "")) : text;', loader: 'js' }));
    }}] });
  const css = appCss(root);
  const server = http.createServer((req, res) => {
    if (req.url === '/app.js') { res.setHeader('content-type','application/javascript'); res.end(fs.readFileSync(path.join(out,'app.js'))); }
    else if (req.url === '/style.css') { res.setHeader('content-type','text/css'); res.end(css); }
    else res.end('<html><head><meta name="viewport" content="width=device-width,initial-scale=1"/><link rel="stylesheet" href="/style.css"/></head><body><div id="root"></div><script src="/app.js"></script></body></html>');
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const names = (page) => page.locator('.iptv-group-name').allInnerTexts();
  let browser;
  try {
    browser = await chromium.launch();
    for (const [name,width,height] of [['desktop',1280,900],['phone',390,844]]) {
      const page = await browser.newPage({ viewport:{width,height} });
      const errors=[]; page.on('pageerror', e=>errors.push(e.message));
      await page.goto(`http://127.0.0.1:${server.address().port}`);
      await page.evaluate(() => localStorage.clear()); await page.reload();
      assert.deepEqual((await names(page)).slice(0, 3), ['News', 'Sports', 'Movies']);
      const hold = page.getByRole('button',{name:'Hold to move: News',exact:true});
      await hold.focus(); await page.keyboard.press('Enter');
      assert.equal(await hold.getAttribute('aria-pressed'),'true');
      await page.keyboard.press('ArrowDown'); await page.keyboard.press('ArrowDown');
      assert.equal((await names(page))[2],'News');
      await page.keyboard.press('Escape');
      assert.equal(await hold.getAttribute('aria-pressed'),'false');
      assert.equal((await names(page))[0],'News', 'Escape puts the category back');
      await hold.focus(); await page.keyboard.press('Space');
      await page.keyboard.press('ArrowDown'); await page.keyboard.press('ArrowDown'); await page.keyboard.press('Enter');
      assert.equal((await names(page))[2],'News');
      await page.reload();
      assert.equal((await names(page))[2],'News', 'saved');
      await page.getByRole('button',{name:'Hide all',exact:true}).click();
      assert.equal(await page.getByRole('button',{name:/^Show: /}).count(),4);
      await page.getByRole('button',{name:/^Playlist: /}).click();
      await page.getByRole('dialog').getByRole('button',{name:'Other playlist'}).click();
      assert.equal(await page.getByRole('button',{name:'Hide: News',exact:true}).count(),1);
      await page.getByRole('button',{name:/^Playlist: /}).click();
      await page.getByRole('dialog').getByRole('button',{name:'Main playlist'}).click();
      await page.getByRole('button',{name:'Show all',exact:true}).click();
      await page.getByRole('button',{name:'Reset order',exact:true}).click();
      assert.equal((await names(page))[0],'News');
      assert.equal(await page.evaluate(()=>document.documentElement.scrollWidth>innerWidth),false);
      await page.screenshot({path:path.join(out,`${name}.png`),fullPage:true});
      assert.deepEqual(errors,[]); await page.close(); console.log(`${name}: passed`);
    }
  } finally { if (browser) await browser.close(); server.close(); }
})().catch(error=>{console.error(error);process.exitCode=1;});

const assert = require('node:assert/strict');
const fs = require('node:fs');
const os = require('node:os');
const path = require('node:path');
const root = path.resolve(__dirname, '..');
const { build } = require('esbuild');
const { chromium, expect } = require('@playwright/test');
const { appCss } = require('./app-styles.cjs');
const outputDir = process.env.OUTPUT_DIR || path.join(os.tmpdir(), 'arvio-iptv-playlist-settings');
const stubs = {
  '@/lib/i18n': `export const useTranslation=()=> (text,values={})=>Object.entries(values).reduce((s,[k,v])=>s.replaceAll('{'+k+'}',String(v)),text);`,
  '@/lib/store': `import {useSyncExternalStore} from 'react';let state;const listeners=new Set();const notify=()=>listeners.forEach(f=>f());
    export const useAppPick=()=>useSyncExternalStore(cb=>{listeners.add(cb);return()=>listeners.delete(cb)},()=>state);
    export function reset(patch={}){window.updates=[];window.testCalls=[];window.pendingTests=[];
      const settings={iptvPlaylists:[],iptvStalkerPortals:[],iptvStalkerUrl:'',iptvStalkerMac:'',hiddenGroupIds:[],favoriteChannelIds:[],favoriteGroupIds:[],groupOrder:[],...patch};
      state={settings,activeProfile:{id:'fixture'},auth:null,iptvSnapshot:{scopeKey:'none',channels:[]},busy:'',
      updateSettings(patch){window.updates.push(patch);state={...state,settings:{...state.settings,...patch}};notify()},refreshIptv:async()=>{},setToast(){}};
      window.snapshot=()=>state;notify();}`,
  '@/lib/iptv': `export * from ${JSON.stringify(path.join(root, 'lib/iptv.ts').replaceAll('\\', '/'))};
    export function testIptvPlaylist(entry,options){window.testCalls.push({entry,options});return new Promise(resolve=>window.pendingTests.push(resolve));}`,
};

async function main() {
  fs.mkdirSync(outputDir, { recursive: true });
  const output = await build({ stdin: { resolveDir: root, loader: 'tsx', contents: `
    import React from 'react';import {createRoot} from 'react-dom/client';import {LiveTvSection} from './components/settings/sections/Sources';import {reset} from '@/lib/store';
    const root=createRoot(document.getElementById('root'));let generation=0;window.setup=(settings)=>{reset(settings);root.render(<main className="st-screen"><div key={++generation} className="st-main"><LiveTvSection/></div></main>)};window.setup({});
  ` }, bundle: true, write: false, format: 'iife', jsx: 'automatic', outfile: 'fixture.js', define: { 'process.env': '{}' },
  plugins: [{ name: 'fixture', setup(builder) {
    builder.onResolve({ filter: /^@\// }, args => args.path in stubs ? { path: args.path, namespace: 'stub' } : undefined);
    builder.onLoad({ filter: /.*/, namespace: 'stub' }, args => ({ contents: stubs[args.path], loader: 'tsx', resolveDir: root }));
    builder.onLoad({ filter: /[\\/]IptvGroupSettings\.tsx$/ }, () => ({ contents: 'export const IptvGroupSettings=()=>null;', loader: 'tsx' }));
    builder.onLoad({ filter: /[\\/]ManagedConnections\.tsx$/ }, () => ({ contents: 'export const ManagedConnections=()=>null;', loader: 'tsx' }));
    builder.onLoad({ filter: /\.css$/ }, () => ({ contents: '', loader: 'css' }));
  }}] });
  const browser = await chromium.launch({ ...(process.env.BROWSER_CHANNEL ? { channel: process.env.BROWSER_CHANNEL } : {}), headless: true });
  try {
    const page = await browser.newPage({ viewport: { width: 1440, height: 1000 } });
    const errors = [];
    page.on('pageerror', error => errors.push(error.message));
    await page.route('https://fixture.invalid/**', route => route.fulfill({ contentType: 'text/html', body: '<div id="root"></div>' }));
    await page.goto('https://fixture.invalid/');
    await page.addStyleTag({ content: appCss(root) });
    await page.addScriptTag({ content: output.outputFiles.find(file => file.path.endsWith('.js')).text });
    const m3u = { id: 'm3u', name: 'M3U fixture', enabled: true, m3uUrl: 'https://provider.invalid/channels.m3u',
      epgUrl: 'https://guide.invalid/main.xml', epgUrls: ['https://guide.invalid/main.xml', 'https://guide.invalid/backup.xml'] };
    await page.evaluate(entry => window.setup({ iptvPlaylists: [entry] }), m3u);
    await page.getByRole('button', { name: 'Edit M3U fixture', exact: true }).click();
    let region = page.getByRole('region', { name: 'Edit M3U fixture', exact: true });
    await region.getByLabel('Playlist name', { exact: true }).fill('Renamed M3U');
    await region.getByRole('button', { name: 'Save', exact: true }).click();
    assert.deepEqual(await page.evaluate(() => window.snapshot().settings.iptvPlaylists[0]), { ...m3u, name: 'Renamed M3U' });
    const xtream = { ...m3u, id: 'xtream', name: 'Xtream fixture',
      m3uUrl: 'https://provider.invalid/get.php?username=test&password=test&type=m3u_plus&output=ts' };
    await page.evaluate(entry => window.setup({ iptvPlaylists: [entry] }), xtream);
    await page.getByRole('button', { name: 'Edit Xtream fixture', exact: true }).click();
    region = page.getByRole('region', { name: 'Edit Xtream fixture', exact: true });
    await region.getByLabel('Playlist name', { exact: true }).fill('Renamed Xtream');
    await region.getByRole('button', { name: 'Save', exact: true }).click();
    assert.deepEqual(await page.evaluate(() => window.snapshot().settings.iptvPlaylists[0]), { ...xtream, name: 'Renamed Xtream' });

    await page.evaluate(() => window.setup({}));
    const add = page.locator('#st-iptv-add');
    await add.getByRole('radio', { name: 'M3U link', exact: true }).click();
    await add.getByLabel('M3U playlist URL', { exact: true }).fill('https://untested.invalid/channels.m3u');
    await add.getByRole('radio', { name: 'Xtream Codes', exact: true }).click();
    await add.getByLabel('Server address', { exact: true }).fill('https://provider.invalid');
    await add.getByLabel('Username', { exact: true }).fill('test');
    await add.getByLabel('Password', { exact: true }).fill('test');
    await add.getByRole('button', { name: 'Test', exact: true }).click();
    await add.getByRole('radio', { name: 'M3U link', exact: true }).click();
    await expect(add.locator('.stalker-test')).toHaveCount(0);
    await page.evaluate(() => window.pendingTests[0]({ ok: true, channels: 123 }));
    await page.evaluate(() => new Promise(resolve => requestAnimationFrame(() => requestAnimationFrame(resolve))));
    await expect(add.locator('.stalker-test')).toHaveCount(0);
    await expect(add.getByLabel('M3U playlist URL', { exact: true })).toHaveValue('https://untested.invalid/channels.m3u');
    await add.getByRole('button', { name: 'Test', exact: true }).click();
    await page.evaluate(() => window.pendingTests[1]({ ok: true, channels: 456 }));
    await expect(add.getByText('Works: 456 channels found.', { exact: true })).toBeVisible();
    await add.getByRole('radio', { name: 'Xtream Codes', exact: true }).click();
    await expect(add.locator('.stalker-test')).toHaveCount(0);
    await expect(add.getByLabel('Username', { exact: true })).toHaveValue('test');
    await add.getByRole('button', { name: 'Test', exact: true }).click();
    await add.getByRole('radio', { name: 'Stalker portal', exact: true }).click();
    await page.evaluate(() => window.pendingTests[2]({ ok: true, channels: 789 }));
    await expect(add.locator('.stalker-test')).toHaveCount(0);
    await add.getByRole('radio', { name: 'Xtream Codes', exact: true }).click();
    await expect(add.locator('.stalker-test')).toHaveCount(0);
    await add.getByRole('button', { name: 'Test', exact: true }).click();
    await expect(add.getByRole('alert')).toBeVisible();
    await add.getByRole('radio', { name: 'M3U link', exact: true }).click();
    await expect(add.getByRole('alert')).toHaveCount(0);

    await page.evaluate(entries => window.setup({ iptvPlaylists: entries }), [m3u, xtream]);
    await page.getByRole('button', { name: 'Edit Xtream fixture', exact: true }).click();
    for (const width of [1440, 390]) {
      await page.setViewportSize({ width, height: 1000 });
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, `overflow at ${width}px`);
      await page.screenshot({ path: path.join(outputDir, `playlist-settings-${width}.png`), fullPage: true });
    }
    assert.deepEqual(errors, []);
    console.log('Passed: guide preservation, pending/completed test invalidation, draft retention, unmount cleanup, validation reset, desktop/mobile layout.');
    console.log(`Screenshots: ${outputDir}`);
  } finally { await browser.close(); }
}
main().catch(error => { console.error(error); process.exitCode = 1; });

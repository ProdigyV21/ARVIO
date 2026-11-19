const fs = require('node:fs'), path = require('node:path'), http = require('node:http'), assert = require('node:assert/strict');
const { build } = require('esbuild'); const { chromium } = require('@playwright/test');
const root = path.resolve(__dirname, '..'), evidence = path.resolve(root, '../artifacts/wetrakr-ui');
const { APP_SHEETS: styles } = require('./app-styles.cjs');
(async () => {
  const mocks = {
    '@/lib/store': `import React,{createContext,useContext,useState} from 'react'; const Context=createContext({}); export function Provider({children}){ const [trackingPreferences,set]=useState({watchlistReadMode:'auto',continueWatchingReadMode:'auto',watchedReadMode:'auto',writeToTrakt:false,writeToSimkl:false});const value={activeProfile:{id:'fixture'},refreshData:async()=>{},traktConnected:false,simklConnected:false,mdblistConnected:false,trackingPreferences,updateTrackingPreferences:async v=>set(p=>({...p,...v})),deviceCode:null,simklDeviceCode:null};return <Context.Provider value={value}>{children}</Context.Provider>}export const useApp=()=>useContext(Context);export const useAppPick=useApp;`,
    '@/lib/i18n': `export const useTranslation=()=> (s,v)=>Object.entries(v||{}).reduce((a,[k,b])=>a.replaceAll('{'+k+'}',b),s);`,
    '@/lib/config': `export const config={}; export const getAuthPortalUrl=()=>''; export const hasNetlifyBackendConfig=()=>false; export const hasSimklConfig=()=>true;export const hasSupabaseConfig=()=>false;export const hasTraktConfig=()=>true;`,
    '@/components/shell/PremiumAccount': `export const PremiumAccount=()=>null;`
  };
  const bundle = await build({ stdin: { resolveDir: root, loader: 'tsx', contents: `import React from 'react';import {createRoot} from 'react-dom/client';import {Provider} from '@/lib/store';import {ServicesSection} from './components/settings/sections/Account';createRoot(document.getElementById('root')).render(<Provider><main className="st-shell is-detail" style={{display:'block'}}><section className="st-main" style={{padding:24,maxWidth:1000,margin:'auto'}}><ServicesSection/></section></main></Provider>);` }, bundle: true, write: false, jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"' }, plugins: [{ name: 'fixtures', setup(api) {
    api.onResolve({ filter: /^@\// }, args => mocks[args.path] ? { path: args.path, namespace: 'fixture' } : { path: path.resolve(root, args.path.slice(2)) + '.ts' });
    api.onLoad({ filter: /.*/, namespace: 'fixture' }, args => ({ contents: mocks[args.path], loader: 'tsx', resolveDir: root }));
  } }] });
  const server = http.createServer((req, res) => {
    const url = new URL(req.url, 'http://localhost');
    if (url.pathname === '/app.js') { res.setHeader('Content-Type', 'text/javascript'); return res.end(bundle.outputFiles[0].contents); }
    if (styles.some(s => url.pathname === '/' + s)) { res.setHeader('Content-Type', 'text/css'); return res.end(fs.readFileSync(path.join(root, url.pathname.slice(1)))); }
    res.setHeader('Content-Type', 'text/html'); res.end('<html><head>' + styles.map(s => `<link rel="stylesheet" href="/${s}">`).join('') + '</head><body><div id="root"></div><script src="/app.js"></script></body></html>');
  });
  await new Promise(r => server.listen(0, '127.0.0.1', r)); const origin = `http://127.0.0.1:${server.address().port}`; let browser;
  try {
    fs.mkdirSync(evidence, { recursive: true }); browser = await chromium.launch({ channel: 'msedge', headless: true });
    for (const [name, width, height] of [['desktop', 1440, 1100], ['phone', 390, 844]]) {
      const page = await browser.newPage({ viewport: { width, height } }); const errors = []; page.on('pageerror', e => errors.push(e.message)); let connected = false; const writes = [];
      await page.route('**/api/wetrakr/**', async route => {
        let data = {}; const request = route.request();
        if (request.url().endsWith('/session')) {
          const input = request.postDataJSON();
          if (!input) data = { token: connected ? { connection_id: 'fixture', server_session: true } : null };
          else if (input.action === 'start') data = { user_code: 'ABC123', verification_url: 'https://wetrakr.com/activate', expires_in: 600, interval: 5 };
          else if (input.action === 'poll') { connected = true; data = { token: { connection_id: 'fixture', server_session: true } }; }
          else if (input.action === 'disconnect') { connected = false; data = { disconnected: true }; }
        } else { writes.push(request.url()); data = []; }
        await route.fulfill({ json: data });
      });
      await page.goto(origin); await page.getByRole('button', { name: 'Connect WeTrakr', exact: true }).click();
      await page.getByText('ABC123', { exact: true }).waitFor(); await page.getByText('ABC123', { exact: true }).scrollIntoViewIfNeeded();
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false);
      await page.locator(".st-group").filter({ has: page.getByRole("heading", {name:"WeTrakr",exact:true}) }).screenshot({ path: path.join(evidence, `${name}-activation.png`) });
      await page.getByRole('button', { name: 'Disconnect WeTrakr', exact: true }).waitFor({ timeout: 10000 });
      assert.equal(await page.getByText('ABC123', { exact: true }).count(), 0);
      await page.getByRole('button', { name: 'Disconnect WeTrakr', exact: true }).scrollIntoViewIfNeeded();
      await page.locator(".st-group").filter({ has: page.getByRole("heading", {name:"WeTrakr",exact:true}) }).screenshot({ path: path.join(evidence, `${name}-connected-fixture.png`) });
      await page.getByRole('button', { name: 'Disconnect WeTrakr', exact: true }).click();
      await page.getByRole('button', { name: 'Connect WeTrakr', exact: true }).waitFor(); assert.deepEqual(errors, []); assert.deepEqual(writes, []);
      await page.close(); console.log(name + ': activation, automatic polling, routing, disconnect and no horizontal overflow passed');
    }
  } finally { await browser?.close(); server.close(); }
})().catch(e => { console.error(e); process.exitCode = 1; });

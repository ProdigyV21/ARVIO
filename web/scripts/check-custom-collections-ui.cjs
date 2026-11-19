const path = require('node:path');
const { appCss } = require('./app-styles.cjs');
const fs = require('node:fs');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');

(async () => {
  const root = path.resolve(__dirname, '..');
  const live = process.env.COLLECTION_LIVE === 'true';
  const out = path.join(root, '.custom-collections-ui-test');
  fs.mkdirSync(out, { recursive: true });
  await esbuild.build({
    stdin: { contents: `import React from 'react'; import {createRoot} from 'react-dom/client';
      import {CustomCollectionRail} from './components/media/CustomCollectionRail';import {FixtureProvider} from '@/lib/store';
      const folder={id:'scifi',name:'Science fiction',kind:'COLLECTION',collectionGroup:'GENRE',enabled:true,sourceType:'tmdb',collectionCoverImageUrl:'/cover.jpg',collectionSources:[{kind:'TMDB_DISCOVER',mediaType:'movie',discoverParams:{with_genres:'878'}},{kind:'TMDB_DISCOVER',mediaType:'series',discoverParams:{with_genres:'10765'}}]};
      createRoot(document.getElementById('root')).render(<FixtureProvider>{open=><CustomCollectionRail catalog={{id:'rail',name:'Genres'}} folders={[folder]} onOpen={open}/>}</FixtureProvider>);`, resolveDir: root, loader: 'tsx' },
    bundle: true, outfile: path.join(out, 'app.js'), jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"', 'process.env': '{}' },
    plugins: [{ name: 'adapters', setup(build) {
      build.onResolve({ filter: /^@\/lib\/(store|i18n|tmdb|imdbRatings)$/ }, args => live && /\/(tmdb|imdbRatings)$/.test(args.path) ? undefined : ({ path: args.path, namespace: 'fixture' }));
      build.onLoad({ filter: /.*/, namespace: 'fixture' }, args => ({ resolveDir: root, loader: 'tsx', contents: args.path.endsWith('i18n')
        ? `export const useTranslation=()=>s=>s;`
        : args.path.endsWith('tmdb') ? `export const getLogoUrl=async()=>'/clearlogo.svg';export const getCardProviders=async()=>['Netflix'];export const getCardMeta=async()=>({});export const resolveTmdbId=async()=>1;export const prefetchDetails=()=>{};export const getCollectionPreview=async(item)=>({...item,overview:'A journey through unfamiliar worlds and extraordinary discoveries. Explore the stories, characters and ideas that made these films and series memorable.',year:'2024',duration:'2h 10m',budget:150000000,imdbId:'tt123',backdrop:'/cover.jpg'});`
        : args.path.endsWith('imdbRatings') ? `export const getImdbRating=async()=> '8.2';`
        : `import React,{createContext,useContext,useState} from 'react';const Context=createContext(null);const settings={language:'en',homeServers:[],cardLayoutMode:'landscape'};export const useApp=()=>useContext(Context);export const useAppPick=()=>useApp();export const useAppSelector=(select)=>select(useApp());export function FixtureProvider({children}){const[selected,setSelected]=useState(null);return <Context.Provider value={{settings,addons:[],selected,isWatched:()=>false,openContextMenu:()=>{}}}>{children(setSelected)}{selected&&<button onClick={()=>setSelected(null)}>Back to collection</button>}</Context.Provider>}` }));
      build.onResolve({ filter: /^@\// }, args => ({ path: ['.tsx', '.ts', '/index.tsx', '/index.ts'].map(ext => path.join(root, args.path.slice(2) + ext)).find(fs.existsSync) }));
    } }]
  });
  const server = http.createServer(async (req, res) => {
    if (live && req.url.startsWith('/api/')) {
      try { const upstream = await fetch(`https://web.arvio.tv${req.url}`); res.writeHead(upstream.status, { 'content-type': upstream.headers.get('content-type') || 'application/json' }); return res.end(Buffer.from(await upstream.arrayBuffer())); }
      catch { res.writeHead(502); return res.end('{}'); }
    }
    if (req.url === '/app.js') { res.setHeader('content-type', 'application/javascript'); return res.end(fs.readFileSync(path.join(out, 'app.js'))); }
    if (req.url === '/globals.css') { res.setHeader('content-type', 'text/css'); return res.end(appCss(root)); }
    if (req.url.startsWith('/api/tmdb/')) {
      const url = new URL(req.url, 'http://localhost'); const page = Number(url.searchParams.get('page') || 1); const tv = url.pathname.includes('/tv');
      res.setHeader('content-type', 'application/json');
      return res.end(JSON.stringify({ results: page > 3 ? [] : Array.from({ length: 20 }, (_, i) => ({id: i+1+(page-1)*20, title: `${tv?'Series':'Movie'} ${i+1+(page-1)*20}`, name: `${tv?'Series':'Movie'} ${i+1+(page-1)*20}`, backdrop_path:'/art.jpg',poster_path:'/art.jpg',release_date:'2024-01-01',first_air_date:'2024-01-01',vote_average:8.1})), total_pages:3 }));
    }
    if (req.url === '/clearlogo.svg' || req.url === '/service.svg') { res.setHeader('content-type', 'image/svg+xml'); return res.end(fs.readFileSync(path.join(root, req.url === '/clearlogo.svg' ? 'public/arvio-wordmark.svg' : 'public/arvio-logo.svg'))); }
    if (req.url === '/cover.jpg') { res.setHeader('content-type', 'image/jpeg'); return res.end(fs.readFileSync(path.join(root, '../app/src/androidTest/assets/library/120467-backdrop.jpg'))); }
    if (/^\/logos\/[a-zA-Z0-9_.-]+\.svg$/.test(req.url)) { res.setHeader('content-type', 'image/svg+xml'); return res.end(fs.readFileSync(path.join(root, 'public', req.url))); }
    res.setHeader('content-type', 'text/html');
    res.end('<meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/globals.css"><div id="root"></div><script src="/app.js"></script>');
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  try {
    for (const [width, height] of [[1920,1080],[1024,768],[390,844],[360,640],[844,390]]) {
      const page = await browser.newPage({ viewport: { width,height } });
      const errors = []; page.on('pageerror', e => { errors.push(e.message); console.error(e.message); });
      if (!live) await page.route('https://image.tmdb.org/**', route => route.fulfill({contentType:'image/jpeg',body:fs.readFileSync(path.join(root,'../app/src/androidTest/assets/library/120467-backdrop.jpg'))}));
      await page.goto(`http://127.0.0.1:${server.address().port}`);
      await page.getByRole('button', { name: 'Science fiction', exact: true }).click();
      if (live) {
        await page.locator('.collection-grid-row .media-card').first().waitFor();
        const firstMovie = await page.locator('.collection-grid-row .media-card strong').first().textContent();
        await page.getByRole('tab', { name: 'Series', exact: true }).click();
        await page.locator('.collection-grid-row .media-card').first().waitFor();
        const firstSeries = await page.locator('.collection-grid-row .media-card strong').first().textContent();
        assert.notEqual(firstMovie, firstSeries);
        await page.locator('.collection-spotlight-title img').waitFor({timeout:30000});
        await page.locator('.collection-spotlight-title img').evaluate(img => img.decode());
        await page.locator('.collection-grid-row .poster img').first().evaluate(img => img.decode());
        assert.equal(await page.locator('.collection-grid-viewport').evaluate(e => e.scrollWidth > e.clientWidth), false);
        await page.screenshot({path:path.join(out, `live-collection-${width}.png`)});
        assert.deepEqual(errors, []);
        console.log(`${width}x${height}: real movies '${firstMovie}', series '${firstSeries}', artwork loaded`);
        await page.close(); continue;
      }
      await page.getByRole('dialog').getByRole('button', { name: /^Movie 1 / }).waitFor();
      assert.equal(await page.locator('[role=tab][aria-selected=true]').textContent(), 'Movies');
      await page.getByRole('tab', { name: 'Series', exact: true }).click();
      await page.getByRole('button', { name: /^Series 1 / }).waitFor();
      assert.equal(await page.locator('.collection-grid-viewport').evaluate(e => e.scrollWidth > e.clientWidth), false);
      const hero = await page.locator('.collection-spotlight').boundingBox();
      const grid = await page.locator('.collection-grid-viewport').boundingBox();
      assert.ok(grid.y >= hero.y + hero.height - 1);
      assert.ok(grid.height >= 150, `usable grid ${width}x${height}: ${grid.height}`);
      const secondPage = page.waitForResponse(r => r.url().includes('/discover/tv') && r.url().includes('page=2'));
      await page.locator('.collection-grid-viewport').evaluate(e => e.scrollTop = e.scrollHeight);
      await secondPage;
      await page.locator('[role=tabpanel][aria-busy=false]').waitFor();
      await page.locator('.collection-grid-viewport').evaluate(e => e.scrollTop = e.scrollHeight);
      await page.getByRole('button', { name: /^Series 40 / }).waitFor();
      await page.locator('.collection-grid-viewport').evaluate(e => e.scrollTop = 0);
      await page.getByRole('button', { name: /^Series 1 / }).click();
      await page.getByRole('button', { name: 'Back to collection', exact: true }).click();
      await page.getByRole('dialog').waitFor();
      assert.equal(await page.locator('[role=tab][aria-selected=true]').textContent(), 'Series');
      await page.getByRole('tab', { name: 'Movies', exact: true }).click();
      await page.getByRole('button', { name: /^Movie 1 / }).waitFor();
      await page.locator('.collection-imdb').waitFor();
      await page.locator('.collection-spotlight-title img').evaluate(img => img.decode());
      const description = await page.locator('.collection-spotlight p').boundingBox();
      assert.ok(description.y + description.height <= grid.y + 1, 'Hero text must not overlap tabs or grid');
      await page.screenshot({ path: path.join(out, `collection-${width}.png`) });
      await page.keyboard.press('Escape');
      assert.equal(await page.getByRole('dialog').count(), 0);
      assert.deepEqual(errors, []);
      await page.close(); console.log(`${width}x${height}: tabs, pagination, return, layout passed`);
    }
  } finally { await browser.close(); server.close(); }
})().catch(e => { console.error(e); process.exitCode = 1; });

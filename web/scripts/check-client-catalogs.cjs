// Production catalog loaders and cards, with deterministic provider HTTP fixtures.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const http = require('node:http');
const path = require('node:path');
const { build } = require('esbuild');
const { chromium } = require('@playwright/test');
const { APP_SHEETS } = require('./app-styles.cjs');
const root = path.resolve(__dirname, '..');
const evidence = path.resolve(root, '../artifacts/pr881-web-browser');

async function main() {
  const stubs = {
    store: `const state = {settings:{cardLayoutMode:'landscape',language:'en'},activeProfile:null};
      export const useAppSelector = select => select(state);
      export const useAppPick = () => ({isWatched:()=>false,openContextMenu:()=>{}});`,
    trakt: `export const traktClient = {currentProfileId:'fixture-profile',
      watchlist:async()=>fetch('/fixture/trakt/watchlist').then(r=>r.json()),
      watched:async(type)=>fetch('/fixture/trakt/'+type).then(r=>r.json())};`,
    simkl: `export const simklClient = {currentProfileId:'fixture-profile',
      library:async(status)=>fetch('/fixture/simkl/'+status).then(r=>r.json())};`,
  };
  const bundle = await build({
    stdin: {resolveDir:root,loader:'tsx',contents:`
      import React,{useState,useEffect} from 'react';
      import {createRoot} from 'react-dom/client';
      import {loadCatalog} from './lib/tmdb';
      import {MediaCard} from './components/media/MediaCard';
      import {RailScroller} from './components/media/RailScroller';
      window.selected=[];
      const mode=new URLSearchParams(location.search).get('mode')||'trakt';
      const delegates={trakt:'trakt://watchlist?type=movie',simkl:'hybrid:completed?tracker=simkl&type=movie',
        home:'homeserver:library?source='+encodeURIComponent('home_server_catalog|server|films|movie'),bad:'trakt:unknown'};
      const addon={id:'fixture',enabled:true,manifestUrl:location.origin+'/fixture/addon/manifest.json',catalogs:[
        {type:'movie',id:'personal',clientDelegate:delegates[mode],clientFilter:'{"minYear":2020}'}]};
      const catalog={id:'personal',name:'Personal',enabled:true,sourceType:'addon',addonId:'fixture',addonCatalogId:'personal',addonCatalogType:'movie'};
      const servers=[{id:'connection',serverId:'server',type:'jellyfin',url:location.origin+'/fixture/home',
        enabled:true,token:'fixture-not-a-secret',userId:'fixture-user'}];
      function Fixture(){
        const [row,setRow]=useState(null),[error,setError]=useState(''),[opened,setOpened]=useState('');
        useEffect(()=>{loadCatalog(catalog,'en',[addon],servers,{limit:1}).then(setRow).catch(e=>setError(e.message));},[]);
        return <main style={{padding:24}}><h1>Personal collection</h1>{error?<p role="alert">{error}</p>:
          row?<><RailScroller ariaLabel="Personal collection" className="media-row">{row.items.map(item=><MediaCard key={item.id}
            item={item} onOpen={selected=>{window.selected.push(selected);setOpened(selected.title);}} />)}</RailScroller>
            <p data-testid="more">{row.hasMore?'More titles':'End of collection'}</p></>:<p>Loading</p>}
          <output>{opened}</output></main>;
      }
      createRoot(document.getElementById('root')).render(<Fixture/>);
    `},
    bundle:true,write:false,jsx:'automatic',define:{'process.env':'{}'},
    plugins:[{name:'provider-fixtures',setup(api){
      api.onResolve({filter:/^(?:@\/lib\/store|\.\/store)$/},()=>({path:'store',namespace:'fixture'}));
      api.onResolve({filter:/\/store\/clients$/},()=>({path:'trakt',namespace:'fixture'}));
      api.onResolve({filter:/^\.\/simkl$/},()=>({path:'simkl',namespace:'fixture'}));
      api.onLoad({filter:/.*/,namespace:'fixture'},args=>({contents:stubs[args.path],loader:'ts'}));
    }}],
  });
  const requests=[];
  const image=fs.readFileSync(path.join(root,'../app/src/androidTest/assets/library/157336-backdrop.jpg'));
  const server=http.createServer((req,res)=>{
    const url=new URL(req.url,'http://localhost');
    const json=value=>{res.setHeader('Content-Type','application/json');res.end(JSON.stringify(value));};
    if(url.pathname==='/app.js'){res.setHeader('Content-Type','text/javascript; charset=utf-8');return res.end(bundle.outputFiles[0].contents);}
    if(APP_SHEETS.some(file=>url.pathname==='/'+file)){res.setHeader('Content-Type','text/css');return res.end(fs.readFileSync(path.join(root,url.pathname.slice(1))));}
    if(req.url.includes('/Images/')||url.pathname==='/art.jpg'){res.setHeader('Content-Type','image/jpeg');return res.end(image);}
    if(url.pathname.startsWith('/fixture/trakt/')||url.pathname.startsWith('/fixture/simkl/')){
      requests.push(url.pathname);
      const ids=url.pathname.includes('simkl')?[6,8]:[1,3,2,4];
      return json(ids.map(id=>({type:'movie',movie:{title:'Film '+id,ids:{tmdb:id}}})));
    }
    if(url.pathname.startsWith('/api/tmdb/')){
      requests.push(url.pathname);
      const id=Number(url.pathname.split('/').at(-1));
      return json({id,title:'Verified Film '+id,adult:id===1,release_date:id===3?'1999-01-01':'2024-01-01',
        poster_path:'/fixture.jpg',backdrop_path:'/fixture.jpg',genre_ids:[28],vote_average:8});
    }
    if(url.pathname==='/api/proxy'){
      const target=new URL(url.searchParams.get('url'));
      requests.push(target.pathname);
      if(target.pathname.includes('/Images/')){res.setHeader('Content-Type','image/jpeg');return res.end(image);}
      const offset=Number(target.searchParams.get('StartIndex')),limit=Number(target.searchParams.get('Limit'));
      return json({TotalRecordCount:2,Items:[9,10].slice(offset,offset+limit).map(id=>({Id:'native-'+id,Name:'Native Film '+id,
        Type:'Movie',ProductionYear:2024,CommunityRating:8,ImageTags:{Primary:'fixture'},ProviderIds:{}}))});
    }
    if(url.pathname.includes('/fixture/addon/')){requests.push('UNEXPECTED_ADDON_HTTP');res.writeHead(500);return res.end();}
    if(url.pathname.startsWith('/assets/')){const file=path.resolve(root,'public',url.pathname.slice(1));
      if(file.startsWith(path.join(root,'public')+path.sep)&&fs.existsSync(file)){return res.end(fs.readFileSync(file));}}
    res.setHeader('Content-Type','text/html');
    res.end('<!doctype html><html><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">'+
      APP_SHEETS.map(file=>'<link rel="stylesheet" href="/'+file+'">').join('')+'</head><body><div id="root"></div><script src="/app.js"></script></body></html>');
  });
  await new Promise(resolve=>server.listen(0,'127.0.0.1',resolve));
  const origin='http://127.0.0.1:'+server.address().port;
  let browser;
  fs.mkdirSync(evidence,{recursive:true});
  try{
    browser=await chromium.launch({channel:process.env.UI_BROWSER_CHANNEL||'msedge',headless:true});
    for(const [name,width,height] of [['desktop',1440,900],['phone',390,844]]){
      const page=await browser.newPage({viewport:{width,height}});
      const errors=[];
      page.on('pageerror',e=>errors.push(e.message));
      await page.route('**/*',route=>{
        const url=new URL(route.request().url());
        if(url.hostname==='image.tmdb.org')return route.fulfill({contentType:'image/jpeg',body:image});
        return url.origin===origin?route.continue():route.abort();
      });
      for(const [mode,id,title] of [['trakt',2,'Verified Film 2'],['simkl',6,'Verified Film 6'],['home',null,'Native Film 9']]){
        await page.goto(origin+'/?mode='+mode);
        const card=page.locator('.media-card');
        await card.waitFor().catch(async error=>{
          console.error({mode,errors,requests,body:await page.locator('body').innerText()});
          await page.screenshot({path:path.join(evidence,name+'-'+mode+'-failure.png')});
          throw error;
        });
        assert.equal(await card.count(),1,'Home limit follows filtering');
        assert.match(await card.innerText(),new RegExp(title));
        await page.waitForFunction(()=>[...document.querySelectorAll('.media-card img')].every(image=>image.complete&&image.naturalWidth>0));
        assert(await card.locator('img').count()>0,'Catalog artwork renders');
        await card.focus();
        await page.keyboard.press('Enter');
        await page.waitForFunction(()=>window.selected.length===1);
        const selected=await page.evaluate(()=>window.selected[0]);
        if(id!==null)assert.equal(selected.id,id);
        else{assert(selected.id<0);assert.equal(selected.homeServerItemId,'native-9');assert(selected.homeServerUrl.includes('/Videos/native-9/stream'));}
        assert.equal(await page.getByTestId('more').innerText(),'More titles');
        assert.equal(await page.evaluate(()=>document.documentElement.scrollWidth>innerWidth),false);
        await page.screenshot({path:path.join(evidence,name+'-'+mode+'.png')});
      }
      await page.goto(origin+'/?mode=bad');
      await page.getByRole('alert').waitFor();
      assert.equal(await page.locator('.media-card').count(),0,'Invalid delegate fails closed');
      assert.deepEqual(errors,[]);
      await page.close();
      console.log('PASS '+name+': production catalog loading, filtering, keyboard selection, native server identity and errors');
    }
    assert(requests.includes('/fixture/trakt/watchlist'));
    assert(requests.includes('/fixture/simkl/completed'));
    assert(requests.some(url=>url.includes('/Users/fixture-user/Items')));
    assert(!requests.includes('UNEXPECTED_ADDON_HTTP'));
    fs.writeFileSync(path.join(evidence,'requests.json'),JSON.stringify(requests,null,2));
  }finally{if(browser)await browser.close();await new Promise(resolve=>server.close(resolve));}
}
main().catch(error=>{console.error(error);process.exitCode=1;});

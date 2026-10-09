/* Isolated production Library component, with deterministic offline data adapters. */
const path=require('node:path');
const fs=require('node:fs');
const http=require('node:http');
const assert=require('node:assert/strict');
const esbuild=require('esbuild');
const {chromium}=require('@playwright/test');
(async()=>{
 const root=path.resolve(__dirname,'..');const out=path.join(root,'.library-ui-test');fs.mkdirSync(out,{recursive:true});
 const stub=path.join(root,'tests/library-ui/stubs.ts');
 await esbuild.build({entryPoints:[path.join(root,'tests/library-ui/entry.tsx')],bundle:true,outfile:path.join(out,'app.js'),jsx:'automatic',nodePaths:(process.env.NODE_PATH||'').split(path.delimiter).filter(Boolean),define:{'process.env.NODE_ENV':'"test"','process.env':'{}'},plugins:[{name:'fixture-adapters',setup(build){build.onResolve({filter:/^@\/lib\/(store|tmdb|imdbRatings|homeserver)$/},()=>({path:stub}));build.onResolve({filter:/^@\//},args=>({path:[".tsx",".ts",".js","/index.tsx","/index.ts",""].map(ext=>path.join(root,args.path.slice(2)+ext)).find(file=>fs.existsSync(file))}));}}]});
 const fixture=path.resolve(root,'../app/src/androidTest/assets/library');
 const server=http.createServer((req,res)=>{
  let file=req.url.split('?')[0];
  if(file==='/') {res.setHeader('content-type','text/html');res.end(`<html><head><meta name="viewport" content="width=device-width,initial-scale=1"/><link rel="stylesheet" href="/globals.css"/><style>.library-test-header{z-index:100;height:84px;position:absolute;top:0;left:0;right:0;display:flex;align-items:center;justify-content:space-between;padding:0 3vw;background:#000;color:#fff}.library-test-header nav,.library-test-header span{display:flex;gap:12px;align-items:center}.library-test-header nav{gap:32px}.library-test-header .active{background:#242426;border-radius:10px;padding:14px}.library-test-avatar{background:#242426;border-radius:50%;padding:12px}.library-test-header svg{width:22px}@media(max-width:650px){.library-test-header{display:none}}</style></head><body><div id="root"></div><script src="/app.js"></script></body></html>`);return;}
  const location=file==='/app.js'?path.join(out,'app.js'):file==='/globals.css'?path.join(root,'app/globals.css'):file.startsWith('/fixtures/')?path.join(fixture,path.basename(file)):path.join(root,'public',file);
  if(!fs.existsSync(location)){res.statusCode=404;res.end();return;}
  res.setHeader('content-type',file.endsWith('.css')?'text/css':file.endsWith('.js')?'application/javascript':file.endsWith('.png')?'image/png':file.endsWith('.jpg')?'image/jpeg':'application/octet-stream');fs.createReadStream(location).pipe(res);
 });
 await new Promise(resolve=>server.listen(0,'127.0.0.1',resolve));
 const browser=await chromium.launch({channel:'chrome',headless:true});const url=`http://127.0.0.1:${server.address().port}`;
 const evidence=path.resolve(root,'../artifacts/library-emulator');fs.mkdirSync(evidence,{recursive:true});
 try {
  for(const [name,width,height] of [['web-tv',1672,940],['web-tablet',1024,768],['web-phone',390,844]]){
   const page=await browser.newPage({viewport:{width,height}});const errors=[];page.on('pageerror',e=>errors.push(e.message));
   await page.goto(url);await page.locator('.media-card .poster-art.is-loaded').first().waitFor();await page.waitForTimeout(700);
   assert.equal(await page.evaluate(()=>document.documentElement.scrollWidth>innerWidth),false,'No horizontal overflow');
   assert(await page.locator('.media-card').count()<100,'Grid virtualizes the full library');
   assert(await page.locator('.card-logo').count()>0,'Horizontal cards render clearlogos');
   if(width>650) await page.locator('.oled-source-nav').getByRole('button',{name:'My watchlist',exact:true}).focus();
   await page.screenshot({path:path.join(evidence,`${name}-watchlists.png`)});
   await page.locator('[data-library-index="0"] button').focus();
   await page.keyboard.press('ArrowRight');
   await page.waitForFunction(()=>document.activeElement?.closest('[data-library-index]')?.getAttribute('data-library-index')==='1');
   await page.locator('.oled-grid-viewport').evaluate(node=>node.scrollTop=500);
   await page.waitForTimeout(100);
   const savedOffset=await page.locator('.oled-grid-viewport').evaluate(node=>node.scrollTop);
   if(width<=650) await page.getByLabel('Library source',{exact:true}).selectOption('trakt:watchlist');
   else await page.locator('.oled-source-nav').getByRole('button',{name:'Watchlist',exact:true}).click();
   await page.locator('.media-card').first().waitFor();
   if(width<=650) await page.getByLabel('Library source',{exact:true}).selectOption('saved');
   else await page.locator('.oled-source-nav').getByRole('button',{name:'My watchlist',exact:true}).click();
   await page.waitForTimeout(100);
   assert(Math.abs(await page.locator('.oled-grid-viewport').evaluate(node=>node.scrollTop)-savedOffset)<3,'Switching sources restores scroll');
   await page.locator('.oled-grid-viewport').evaluate(node=>node.scrollTop=0);
   await page.getByRole('button',{name:'Filters',exact:true}).click();
   const box=await page.getByRole('dialog').boundingBox();assert(box.x>=width-Math.min(420,width)-1,'Drawer is on right');
   await page.keyboard.press('Escape');assert.equal(await page.getByRole('dialog').count(),0);
   await page.getByRole('button',{name:'My lists',exact:true}).click();await page.locator('.oled-collection img').first().waitFor();await page.waitForTimeout(300);
   if(width>650) await page.locator('.oled-collection').first().focus();
   await page.screenshot({path:path.join(evidence,`${name}-lists.png`)});
   await page.getByRole('button',{name:'Homeserver',exact:true}).click();await page.locator('.media-card').first().waitFor();await page.waitForTimeout(300);
   await page.screenshot({path:path.join(evidence,`${name}-servers.png`)});
   await page.goto(url+'/?poster=1');await page.locator('.media-card.is-poster').first().waitFor();await page.waitForTimeout(400);
   assert.equal(await page.locator('.card-logo').count(),0,'Poster layout does not overlay clearlogos');
   await page.screenshot({path:path.join(evidence,`${name}-poster.png`)});
   assert.deepEqual(errors,[],'No runtime errors');await page.close();console.log(`${name}: passed`);
  }
 }finally{await browser.close();server.close();}
})().catch(e=>{console.error(e);process.exitCode=1;});

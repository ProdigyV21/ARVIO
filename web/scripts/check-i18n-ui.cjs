const path=require('node:path'),fs=require('node:fs'),http=require('node:http'),assert=require('node:assert/strict');
const esbuild=require('esbuild'),ts=require('typescript'),{chromium}=require('@playwright/test');
const {load}=require('../tests/load.cjs');
(async()=>{
 const root=path.resolve(__dirname,'..'),out=path.resolve(root,'../artifacts/web-i18n');fs.mkdirSync(out,{recursive:true});
 const manifest=require('../lib/i18n/manifest.json');
 const {resolveLocale,translate}=load('lib/i18n/core.ts',{'./manifest.json':manifest});
 const languages=require('../lib/i18n/languages.json');
 const source=ts.createSourceFile('store.tsx',fs.readFileSync(path.join(root,'lib/store.tsx'),'utf8'),99,true,ts.ScriptKind.TSX);
 let defaults;function visit(n){if(ts.isVariableDeclaration(n)&&n.name.getText(source)==='defaultSettings')defaults=n.initializer.getText(source);ts.forEachChild(n,visit)}visit(source);
 assert(defaults);
 const defaultsScript=ts.transpileModule(`const defaultCatalogs=[]; window.fixtureDefaults=${defaults};`,{compilerOptions:{target:ts.ScriptTarget.ES2022}}).outputText;
 await esbuild.build({entryPoints:[path.join(root,'tests/i18n-ui/entry.tsx')],bundle:true,outfile:path.join(out,'app.js'),jsx:'automatic',nodePaths:(process.env.NODE_PATH||'').split(path.delimiter).filter(Boolean),define:{'process.env.NODE_ENV':'"test"','process.env':'{}'},plugins:[{name:'fixture',setup(build){
   build.onResolve({filter:/^@\/lib\/telegram$/},()=>({path:'telegram',namespace:'offline'}));
   build.onLoad({filter:/.*/,namespace:'offline'},()=>({contents:'export const isTelegramConfigured=false; export const subscribe=()=>()=>{};',loader:'js'}));
   build.onResolve({filter:/^@\/lib\/store$/},()=>({path:path.join(root,'tests/i18n-ui/store.tsx')}));
   build.onResolve({filter:/^@\/lib\/(tmdb|imdbRatings)$/},()=>({path:path.join(root,'tests/library-ui/stubs.ts')}));
   build.onResolve({filter:/^@\//},args=>({path:['.tsx','.ts','.js','/index.tsx','/index.ts',''].map(ext=>path.join(root,args.path.slice(2)+ext)).find(file=>fs.existsSync(file))}));
 }}]});
 const server=http.createServer((req,res)=>{
  const pathname=new URL(req.url,'http://localhost').pathname;
  if(pathname==='/'){res.setHeader('content-type','text/html; charset=utf-8');res.end(`<html><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><link rel="stylesheet" href="/globals.css"></head><body><div id="root"></div><script>${defaultsScript}</script><script src="/app.js"></script></body></html>`);return;}
  const file=pathname==='/app.js'?path.join(out,'app.js'):pathname==='/globals.css'?path.join(root,'app/globals.css'):pathname.startsWith('/fixtures/')?path.join(root,'../app/src/androidTest/assets/library',path.basename(pathname)):path.join(root,'public',pathname);
  if(!fs.existsSync(file)){res.statusCode=404;res.end();return;}
  res.setHeader('content-type',file.endsWith('.js')?'application/javascript':file.endsWith('.json')?'application/json':file.endsWith('.css')?'text/css':file.endsWith('.svg')?'image/svg+xml':file.endsWith('.png')?'image/png':'image/jpeg');fs.createReadStream(file).pipe(res);
 });
 await new Promise(resolve=>server.listen(0,'127.0.0.1',resolve));const browser=await chromium.launch({channel:'chrome',headless:true});
 try{
  for(const [name,width,height] of [['tv',1920,1080],['tablet',1024,768],['phone',390,844]]){
   const page=await browser.newPage({viewport:{width,height}}),errors=[];page.on('pageerror',error=>{errors.push(error.message);console.error('Browser:',error.message)});
   await page.goto(`http://127.0.0.1:${server.address().port}`);
   await page.getByRole('button',{name:'Listas para ver',exact:true}).waitFor();
   assert.equal(await page.locator('html').getAttribute('lang'),'es-ES');
   assert.equal(await page.locator('.profile-name-text').textContent(),'Home','User profile name stays unchanged');
   await page.locator('.media-card').first().waitFor();
   await page.screenshot({path:path.join(out,`${name}-spanish-library.png`)});
   if(process.env.ARVIO_TEST_ALL_LANGUAGES==='1') {
     for(const {code} of languages) {
       const locale=resolveLocale(code),dictionary=locale==='en'?{}:JSON.parse(fs.readFileSync(path.join(root,`public/i18n/${locale}.json`),'utf8'));
       await page.evaluate(code=>window.setFixtureLanguage(code),code);
       await page.waitForFunction(({code,label})=>document.documentElement.lang===code&&document.querySelector('.oled-library-toolbar nav button:nth-child(2)')?.textContent===label,{code,label:translate(dictionary,'My lists')});
         assert.equal(await page.locator('.profile-name-text').textContent(),'Home');
         if(width>=1024) for(const label of ['Collection','Watching','Completed','Dropped']) {
           await page.getByRole('button',{name:translate(dictionary,label),exact:true}).waitFor();
         }
       const rtl=/^(ar|he|fa|ur)(-|$)/.test(code);
       assert.equal(await page.locator('html').getAttribute('dir'),rtl?'rtl':'ltr');
       assert.equal(await page.evaluate(()=>document.documentElement.scrollWidth>innerWidth),false,`${code}: no overflow at ${width}px`);
       if(width>650 && rtl) {
         await page.locator('[data-library-index="0"] button').focus();
         await page.keyboard.press('ArrowLeft');
         await page.waitForFunction(()=>document.activeElement?.closest('[data-library-index]')?.getAttribute('data-library-index')==='1');
         await page.keyboard.press('ArrowRight');
         await page.waitForFunction(()=>document.activeElement?.closest('[data-library-index]')?.getAttribute('data-library-index')==='0');
       }
       if(['nl','de','fr','ar','he','ja','zh-CN','hi','gu'].includes(locale)) await page.screenshot({path:path.join(out,`${name}-${locale}-library.png`)});
     }
     await page.evaluate(()=>window.setFixtureLanguage('es-ES'));
     await page.getByRole('button',{name:'Listas para ver',exact:true}).waitFor();
   }
   const navigation=width<681?page.locator('.mobile-bottom-nav'):page.locator('.sidebar');
   await navigation.getByRole('button',{name:'Buscar',exact:true}).click();
   const search=page.getByRole('textbox',{name:'Buscar películas y series'});await search.fill('Dune');
   await page.evaluate(()=>window.setFixtureLanguage('en-US'));
   await page.getByRole('textbox',{name:'Search movies and series'}).waitFor();
   assert.equal(await page.getByRole('textbox',{name:'Search movies and series'}).inputValue(),'Dune','Switching language preserves the open screen and query');
   await page.evaluate(()=>window.setFixtureLanguage('es-ES'));
   await page.getByRole('textbox',{name:'Buscar películas y series'}).waitFor();
   await navigation.getByRole('button',{name:'Ajustes',exact:true}).click();
   if(width<681){await page.getByRole('button',{name:'Abrir menú de navegación'}).click();await page.locator('.settings-mobile-nav').getByText('Idioma y audio',{exact:true}).click();}
   else await page.locator('.settings-sidebar').getByRole('button',{name:'Idioma y audio',exact:true}).click();
   await page.getByText('Idioma del contenido y la interfaz',{exact:true}).waitFor();
   await page.getByRole('button',{name:'Español',exact:true}).click();
   await page.getByRole('dialog',{name:'Elegir opción'}).waitFor();
   assert.equal(await page.getByRole('dialog').locator('.option-row').count(),languages.length+1,'Every Android language is selectable');
   await page.getByRole('dialog').getByRole('button',{name:'English (US)',exact:true}).click();
   await page.getByText('Content language',{exact:true}).waitFor();
   await page.getByRole('button',{name:'English (US)',exact:true}).click();
   await page.getByRole('dialog').getByRole('button',{name:'Español',exact:true}).click();
   await page.getByText('Idioma del contenido y la interfaz',{exact:true}).waitFor();
   await page.screenshot({path:path.join(out,`${name}-spanish-settings.png`)});
   if(name==='tv') {
     const dictionary=JSON.parse(fs.readFileSync(path.join(root,'public/i18n/es.json'),'utf8'));
     for(const button of await page.locator('.settings-sidebar .settings-nav button').all()) {
       await button.click();
       await page.waitForTimeout(120);
       const untranslated=await page.locator('.settings-content').evaluate((node, dictionary)=>{
         const walker=document.createTreeWalker(node,NodeFilter.SHOW_TEXT),missing=[];
         while(walker.nextNode()) {const text=walker.currentNode.textContent.trim();if(dictionary[text]&&dictionary[text]!==text&&!Object.values(dictionary).includes(text))missing.push(text);}
         return missing;
       },dictionary);
       assert.deepEqual(untranslated,[],`Untranslated settings text in ${await button.getAttribute('title')}`);
     }
   }
   assert.equal(await page.evaluate(()=>document.documentElement.scrollWidth>innerWidth),false,'No page overflow');
   assert.deepEqual(errors,[]);await page.close();console.log(`${name}: translated library, search, settings, dropdowns and live language changes passed`);
  }
 }finally{await browser.close();await new Promise(resolve=>server.close(resolve));}
})().catch(error=>{console.error(error);process.exitCode=1});

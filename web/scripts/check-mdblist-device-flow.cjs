const assert = require('node:assert/strict');
const fs = require('node:fs');
const os = require('node:os');
const path = require('node:path');
const { build } = require('esbuild');
const { chromium, expect } = require('@playwright/test');
const { appCss } = require('./app-styles.cjs');
const root = path.resolve(__dirname, '..');
const outputDir = process.env.OUTPUT_DIR || path.join(os.tmpdir(), 'arvio-mdblist-device-flow');

const stubs = {
  '@/lib/i18n': `export const useTranslation=()=> (text,values={})=>Object.entries(values).reduce((s,[k,v])=>s.replaceAll('{'+k+'}',String(v)),text);`,
  '@/lib/wetrakr': `export const weTrakrClient={isConnected:false,setProfile(){},restoreSession:async()=>{}};`,
  '@/lib/simkl': `export const simklClient={isConnected:false,isV2Connected:false,token:null,setProfile(){}};`,
  '@/lib/config': `export const config={};export const getAuthPortalUrl=()=>'';export const hasNetlifyBackendConfig=()=>false;export const hasSimklConfig=()=>false;export const hasSupabaseConfig=()=>false;export const hasTraktConfig=()=>false;`,
  '@/components/shell/PremiumAccount': `export const PremiumAccount=()=>null;`,
  'fixture:clients': `export const authClient={session:{userId:'owner'}};export const traktClient={isConnected:false,token:null,setProfile(){}};`,
  'fixture:cloud': `export async function saveCloudTrackingSelection(_auth,profile,payload){window.cloudWrites.push({profile,payload})}`,
  'fixture:sync': `export const loadTrackingPreferences=()=>({watchlistReadMode:'auto',continueWatchingReadMode:'auto',watchedReadMode:'auto',writeToTrakt:false,writeToSimkl:false});export const saveTrackingPreferences=()=>{};`,
  'fixture:mappers': `export const dedupeMedia=x=>x;export const hydrateTraktItems=async x=>x;export const traktItemToMedia=x=>x;`,
  'fixture:list-cache': `export const saveCachedList=()=>{};`,
  'fixture:http': `export class HttpError extends Error{constructor(status,message,retryAfter=null){super(message);this.status=status;this.retryAfter=retryAfter}}
    export async function jsonRequest(url,init={}){const request={url,init};window.requests.push(request);
      if(url.includes('/oauth/status/'))return {oauthConfigured:true};
      if(url.includes('/device-authorization/')){if(window.deferStart)return new Promise(resolve=>window.pendingStarts.push(resolve));return window.makeCode()}
      if(url.includes('/oauth/token/'))return new Promise((resolve,reject)=>window.pendingTokens.push({resolve,reject,status:(message,retryAfter=null)=>reject(new HttpError(400,message,retryAfter))}));
      if(url.includes('/user'))return {username:'fixture'};throw Error('Unexpected fixture URL: '+url)};`,
  '@/lib/store': `import React,{createContext,useCallback,useContext,useRef,useState} from 'react';
    import {useTrackingActions} from './lib/store/tracking';import {mdblistClient} from '@/lib/mdblist';import {authClient} from 'fixture:clients';
    const Context=createContext(null);export const useApp=()=>useContext(Context);export const useAppPick=useApp;
    export function FixtureProvider({children}){const [state,setState]=useState({activeProfileId:'A',activeProfile:{id:'A'},auth:authClient.session,traktConnected:false,simklConnected:false,mdblistConnected:false,deviceCode:null,simklDeviceCode:null,mdblistDeviceCode:null,
      trackingPreferences:{watchlistReadMode:'auto',continueWatchingReadMode:'auto',watchedReadMode:'auto',writeToTrakt:false,writeToSimkl:false}});
      const activeProfileIdRef=useRef('A'),mdblistDeviceCodeRef=useRef(null),deviceCodeRef=useRef(null),simklDeviceCodeRef=useRef(null);
      const setMdblistDeviceCode=useCallback(value=>setState(s=>({...s,mdblistDeviceCode:value})),[]);
      const setMdblistConnected=useCallback(value=>setState(s=>({...s,mdblistConnected:value})),[]);
      const setTrackingPreferences=useCallback(value=>setState(s=>({...s,trackingPreferences:value})),[]);
      const refreshData=useCallback(async()=>{},[]);
      const actions=useTrackingActions({...state,activeProfileIdRef,mdblistDeviceCodeRef,deviceCodeRef,simklDeviceCodeRef,setMdblistDeviceCode,setMdblistConnected,setTrackingPreferences,refreshData});
      const value={...state,...actions,refreshData};window.snapshot=()=>value;window.mdb=mdblistClient;
      window.switchProfile=id=>{activeProfileIdRef.current=id;mdblistClient.setProfile(id);setState(s=>({...s,activeProfileId:id,activeProfile:{id},mdblistConnected:mdblistClient.isConnected}))};
      return <Context.Provider value={value}>{children}</Context.Provider>}`,
};

async function main() {
  fs.mkdirSync(outputDir, { recursive: true });
  const output = await build({ stdin: { resolveDir: root, loader: 'tsx', contents: `
    import React from 'react';import {createRoot} from 'react-dom/client';import {ServicesSection} from './components/settings/sections/Account';import {FixtureProvider} from '@/lib/store';import {mdblistClient} from '@/lib/mdblist';
    const root=createRoot(document.getElementById('root'));let generation=0;
    window.setup=(options={})=>{window.cloudWrites=[];window.requests=[];window.pendingStarts=[];window.pendingTokens=[];window.deferStart=!!options.deferStart;window.makeCode=()=>({device_code:'device-'+generation,user_code:'CODE-'+generation,verification_uri:'https://mdblist.invalid/device',expires_in:options.expiresIn||300,interval:5});
      mdblistClient.disconnect();localStorage.clear();mdblistClient.setProfile('A');root.render(<main className="st-screen"><FixtureProvider key={++generation}><ServicesSection/></FixtureProvider></main>)};window.setup();
  ` }, bundle: true, write: false, format: 'iife', jsx: 'automatic', outfile: 'fixture.js', define: { 'process.env': '{}' },
    plugins: [{ name: 'fixture', setup(builder) {
      builder.onResolve({ filter: /.*/ }, args => {
        if (args.path in stubs) return { path: args.path, namespace: 'stub' };
        const importer = args.importer.replaceAll('\\', '/');
        if (importer.endsWith('/lib/store/tracking.ts')) {
          const aliases = { '../http': 'fixture:http', '../cloud': 'fixture:cloud', '../mappers': 'fixture:mappers',
            '../sync': 'fixture:sync', './clients': 'fixture:clients', './listCache': 'fixture:list-cache',
            '../wetrakr': '@/lib/wetrakr', '../simkl': '@/lib/simkl' };
          if (aliases[args.path]) return { path: aliases[args.path], namespace: 'stub' };
        }
        if (importer.endsWith('/lib/mdblist.ts') && args.path === './http') return { path: 'fixture:http', namespace: 'stub' };
      });
      builder.onLoad({ filter: /.*/, namespace: 'stub' }, args => ({ contents: stubs[args.path], loader: 'tsx', resolveDir: root }));
    }}] });
  const browser = await chromium.launch({ ...(process.env.BROWSER_CHANNEL ? { channel: process.env.BROWSER_CHANNEL } : {}), headless: true });
  try {
    const page = await browser.newPage({ viewport: { width: 1440, height: 1000 } });
    const errors = [];
    page.on('pageerror', error => errors.push(error.message));
    await page.route('https://fixture.invalid/**', route => route.fulfill({ contentType: 'text/html', body: '<div id="root"></div>' }));
    await page.goto('https://fixture.invalid/');
    await page.clock.install();
    await page.addStyleTag({ content: appCss(root) });
    await page.addScriptTag({ content: output.outputFiles[0].text });
    const card = page.locator('.st-group').filter({ has: page.getByRole('heading', { name: 'MDBList', exact: true }) });
    const tokenChecks = () => page.evaluate(() => window.requests.filter(r => r.url.includes('/oauth/token/')).length);
    const approve = async () => {
      await card.getByRole('button', { name: 'I approved it', exact: true }).click();
      await expect.poll(() => page.evaluate(() => window.pendingTokens.length)).toBe(1);
    };
    const finish = () => page.evaluate(() => window.pendingTokens[0].resolve({ access_token: 'approved', refresh_token: 'refresh', expires_in: 3600 }));

    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    await approve();
    await page.clock.runFor(5000);
    assert.equal(await tokenChecks(), 1, 'manual and automatic checks share one exchange');
    await finish();
    await expect(card.getByText('Connected', { exact: true })).toBeVisible();
    assert.equal(await page.evaluate(() => window.cloudWrites.length), 1);
    assert.equal(await page.evaluate(() => window.cloudWrites[0].profile), 'A');

    await page.evaluate(() => window.setup({ deferStart: true }));
    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    await card.getByRole('button', { name: 'Use API key instead', exact: true }).click();
    await page.evaluate(() => window.pendingStarts[0](window.makeCode()));
    await page.clock.runFor(6000);
    await expect(card.getByLabel('MDBList API key', { exact: true })).toBeVisible();
    assert.equal(await tokenChecks(), 0);
    assert.equal(await page.evaluate(() => window.snapshot().mdblistDeviceCode), null);

    await page.evaluate(() => window.setup());
    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    await approve();
    await card.getByRole('button', { name: 'Use API key instead', exact: true }).click();
    await card.getByLabel('MDBList API key', { exact: true }).fill('manual-key');
    await card.getByRole('button', { name: 'Connect', exact: true }).click();
    await expect(card.getByText('Connected', { exact: true })).toBeVisible();
    await finish();
    await page.clock.runFor(10000);
    assert.equal(await page.evaluate(() => window.mdb.key), 'manual-key');
    assert.equal(await page.evaluate(() => window.mdb.token), null);
    assert.equal(await page.evaluate(() => window.cloudWrites.length), 1);
    assert.equal(await tokenChecks(), 1, 'API-key mode stops hidden OAuth polling');

    await page.evaluate(() => window.setup({ deferStart: true }));
    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    await page.evaluate(() => window.switchProfile('B'));
    await page.evaluate(() => window.pendingStarts[0](window.makeCode()));
    await page.clock.runFor(6000);
    assert.equal(await page.evaluate(() => window.snapshot().mdblistDeviceCode), null);
    assert.equal(await page.evaluate(() => window.cloudWrites.length), 0);
    await page.evaluate(() => { window.deferStart = false; });
    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    await approve();
    await finish();
    await expect(card.getByText('Connected', { exact: true })).toBeVisible();
    assert.equal(await page.evaluate(() => window.cloudWrites[0].profile), 'B');

    await page.evaluate(() => window.setup({ expiresIn: 2 }));
    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    await page.clock.runFor(5000);
    await expect(card.getByText('This MDBList code has expired. Get a new code and try again.', { exact: true })).toBeVisible();
    assert.equal(await tokenChecks(), 0);
    await page.evaluate(() => window.setup());
    await card.getByRole('button', { name: 'Connect MDBList', exact: true }).click();
    for (const width of [1440, 390]) {
      await page.setViewportSize({ width, height: 1000 });
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false);
      await page.screenshot({ path: path.join(outputDir, `mdblist-device-flow-${width}.png`), fullPage: true });
    }
    assert.deepEqual(errors, []);
    console.log('Passed: approval and cloud commit, manual/automatic single flight, API-key cancellation, stale starts/tokens, profile isolation, code expiry, desktop/mobile layout.');
    console.log(`Screenshots: ${outputDir}`);
  } finally { await browser.close(); }
}
main().catch(error => { console.error(error); process.exitCode = 1; });

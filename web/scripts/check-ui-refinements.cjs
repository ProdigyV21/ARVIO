/* Render the production controls and pause-metadata hook with isolated fixture data. */
const assert = require('node:assert/strict');
const fs = require('node:fs');
const http = require('node:http');
const path = require('node:path');
const ts = require('typescript');
const { build } = require('esbuild');
const { chromium } = require('@playwright/test');

const root = path.resolve(__dirname, '..');
const evidence = path.resolve(root, '../artifacts/ui-refinement-web');
const styles = ['globals.css', 'premium.css', 'tv-guide.css', 'source-setup.css', 'calendar.css', 'ui-refinements.css'];

function extract(file, predicate) {
  const source = ts.createSourceFile(file, fs.readFileSync(path.join(root, file), 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  const matches = [];
  function visit(node) {
    if (predicate(node)) matches.push(node);
    ts.forEachChild(node, visit);
  }
  visit(source);
  assert.equal(matches.length, 1, `Expected one matching fragment in ${file}`);
  return matches[0].getText(source);
}
const withClass = className => node => ts.isJsxElement(node) && node.openingElement.attributes.properties.some(attr =>
  ts.isJsxAttribute(attr) && attr.name.text === 'className' && attr.initializer &&
  ts.isStringLiteral(attr.initializer) && attr.initializer.text === className);

async function main() {
  const playerTop = extract('components/player/PlayerOverlay.tsx', withClass('player-top'));
  const seasonTabs = extract('components/details/DetailsDrawer.tsx', withClass('season-tabs'));
  const select = extract('components/settings/SettingsScreen.tsx', node => ts.isFunctionDeclaration(node) && node.name?.text === 'Select');
  const stubs = {
    '@/lib/store': `export const useApp = () => ({ activeContextMenu: {
      title: 'Episode options', position: { x: 32, y: 32 }, actions: [
        { id: 'play', label: 'Play Episode', action: () => window.actions.push('menu-play') },
        { id: 'watched', label: 'Mark as Watched', action: () => window.actions.push('watched') }
      ] }, closeContextMenu() {}, watchlist: [], continueWatching: [] });`,
    '@/lib/tmdb': `export function peekSeasonEpisodes() { return [{seasonNumber:3, episodeNumber:3, overview:'Cached episode three'}]; }
      export function getSeasonEpisodes(id, season) { return new Promise(resolve => window.requests.push({id, season, resolve})); }`,
  };
  const bundle = await build({
    stdin: { resolveDir: root, loader: 'tsx', contents: `
      import React, { useState, useEffect } from 'react';
      import { createRoot } from 'react-dom/client';
      import { createPortal } from 'react-dom';
      import { ArrowLeft, Check, ChevronDown, SkipForward, X } from 'lucide-react';
      import { useTranslation } from './lib/i18n';
      import { EpisodeCard } from './components/details/EpisodeCard';
      import { RailScroller } from './components/media/RailScroller';
      import { ProfileDialog } from './components/profile/ProfileDialog';
      import { MediaContextMenu } from './components/shell/MediaContextMenu';
      import { usePlayerEpisodeMetadata } from './components/player/usePlayerEpisodeMetadata';
      window.actions = []; window.requests = [];
      const translateUi = (text, values) => Object.entries(values ?? {}).reduce((result, [key, value]) => result.replaceAll('{'+key+'}', value), text);
      const priority = { movieProviders:['tmdb'], tvProviders:['tmdb'], animeProviders:['tmdb'] };
      const item = { id:99, mediaType:'tv', title:'Industry', overview:'Series synopsis' };
      const episodes = Array.from({ length:6 }, (_, index) => ({ id:index, seasonNumber:3, episodeNumber:index+1,
        name: index === 1 ? 'Very long episode title that must remain contained in its own artwork' : 'Episode '+(index+1),
        overview:'A detailed episode synopsis that remains inside the artwork and wraps without covering the date or title.',
        still:index === 1 ? '/white.svg' : '/still.jpg', airDate:'2024-08-25', runtime:58, imdbRating:index === 1 ? '' : '8.4', voteAverage:9.5 }));
      ${select}
      function PlayerFixture() {
        const [number, setNumber] = useState(3);
        const [movie, setMovie] = useState(false);
        const [playing, setPlaying] = useState(false);
        const pauseOverview = usePlayerEpisodeMetadata(movie ? {...item, mediaType:'movie'} : item, {season:3, episode:number}, priority, false);
        const title = 'Industry', mediaMeta = 'S3 E'+number, stream = {source:'Fixture'}, nextCountdown = null, canAdvance = false;
        const onClose = () => {}, onAdvance = () => {}, nextDismissed = {current:false}, setNextCountdown = () => {};
        return <div className="player-overlay">${playerTop}<div style={{position:'absolute', bottom:30, left:30, display:'flex', gap:12}}>
          <button onClick={() => setNumber(4)}>Next fixture episode</button>
          <button onClick={() => setMovie(true)}>Movie fixture</button>
          <button onClick={() => setPlaying(value => !value)}>Toggle playing</button>
        </div></div>;
      }
      function DetailsFixture() {
        const [season, setSeason] = useState(3);
        const seasons = [1,2,3].map(n => ({id:n, seasonNumber:n, name:'Season '+n}));
        const loadingDetails = false;
        const handleSeasonContextMenu = (event, number) => {event.preventDefault(); window.actions.push('season-menu-'+number);};
        return <main style={{padding:24}}><section className="detail-section episodes-section detail-wide">
          <h3>Episodes</h3>${seasonTabs}
          <RailScroller className="episode-list" ariaLabel="Episodes">{episodes.map(episode => <EpisodeCard key={episode.id}
            episode={{...episode, seasonNumber:season}} active={false} watched={episode.id===0}
            onPlay={() => window.actions.push('play-'+episode.episodeNumber)}
            onContextMenu={event => {event.preventDefault(); window.actions.push('episode-menu-'+episode.episodeNumber);}} />)}
          </RailScroller></section></main>;
      }
      function SettingsFixture() {
        const [value, setValue] = useState('en');
        return <main className="settings-shell" style={{padding:24}}><div className="set-row"><span className="set-label">Default subtitle</span>
          <span className="set-control"><Select value={value} options={[['en','English'],['ms','Malay']]} onChange={setValue} /></span>
        </div></main>;
      }
      const route = location.pathname;
      createRoot(document.getElementById('root')).render(route === '/player' ? <PlayerFixture /> : route === '/menu' ? <MediaContextMenu /> :
        route === '/settings' ? <SettingsFixture /> : route === '/profile' ? <ProfileDialog mode="edit" initial={{name:'Arvind',avatarId:1,avatarColor:0xff000000}}
          onConfirm={() => window.actions.push('save')} onClose={() => window.actions.push('cancel')} onDelete={() => window.actions.push('delete')} /> : <DetailsFixture />);
    ` },
    bundle: true, write: false, jsx: 'automatic', define: {'process.env.NODE_ENV':'"test"'},
    plugins: [{name:'isolate-account-and-metadata', setup(api) {
      api.onResolve({filter:/^@\/lib\/(store|tmdb)$/}, args => ({path:args.path, namespace:'fixture'}));
      api.onLoad({filter:/.*/, namespace:'fixture'}, args => ({contents:stubs[args.path], loader:'ts'}));
    }}],
  });
  const server = http.createServer((req, res) => {
    const route = new URL(req.url, 'http://localhost').pathname;
    if (route === '/app.js') {res.setHeader('Content-Type','text/javascript'); return res.end(bundle.outputFiles[0].contents);}
    if (styles.some(name => route === '/'+name)) {res.setHeader('Content-Type','text/css'); return res.end(fs.readFileSync(path.join(root,'app',route.slice(1))));}
    if (route === '/still.jpg') {res.setHeader('Content-Type','image/jpeg'); return res.end(fs.readFileSync(path.join(root,'../app/src/androidTest/assets/library/157336-backdrop.jpg')));}
    if (route === '/white.svg') {res.setHeader('Content-Type','image/svg+xml'); return res.end('<svg xmlns="http://www.w3.org/2000/svg" width="800" height="450"><path fill="white" d="M0 0h800v450H0z"/></svg>');}
    const publicFile = path.resolve(root,'public',route.replace(/^\//,''));
    if (publicFile.startsWith(path.join(root,'public')+path.sep) && fs.existsSync(publicFile) && fs.statSync(publicFile).isFile()) {
      res.setHeader('Content-Type', publicFile.endsWith('.svg') ? 'image/svg+xml' : publicFile.endsWith('.png') ? 'image/png' : 'application/octet-stream');
      return res.end(fs.readFileSync(publicFile));
    }
    res.setHeader('Content-Type','text/html');
    res.end(`<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1">${styles.map(name=>`<link rel="stylesheet" href="/${name}">`).join('')}</head><body><div id="root"></div><script src="/app.js"></script></body></html>`);
  });
  await new Promise(resolve => server.listen(0,'127.0.0.1',resolve));
  let browser;
  fs.mkdirSync(evidence,{recursive:true});
  const origin = `http://127.0.0.1:${server.address().port}`;
  try {
    browser = await chromium.launch({channel:process.env.UI_BROWSER_CHANNEL || 'msedge',headless:true});
    for (const [name,width,height] of [['desktop',1920,1080],['tablet',768,1024],['phone',390,844],['phone-small',320,568],['phone-landscape',844,390]]) {
      const page = await browser.newPage({viewport:{width,height}});
      const errors = [];
      page.on('pageerror',error=>errors.push(error.message));
      await page.route('**/*', route => new URL(route.request().url()).origin === origin ? route.continue() : route.abort());
      await page.goto(origin);
      const cards = page.locator('.episode-art-card');
      await cards.first().waitFor();
      await page.waitForFunction(()=>[...document.querySelectorAll('.episode-art-card:first-child img')].every(image=>image.complete && image.naturalWidth>0));
      const layout = await cards.first().evaluate(card => {
        const bounds = card.getBoundingClientRect();
        const contents = [...card.querySelectorAll('.episode-info, .episode-card-rating, .episode-card-watched, .episode-chip')];
        return {width:bounds.width,height:bounds.height, clipped:contents.some(node=>{
          const rect=node.getBoundingClientRect(); return rect.left<bounds.left || rect.top<bounds.top || rect.right>bounds.right || rect.bottom>bounds.bottom;
        })};
      });
      assert.equal(layout.clipped,false,`${name}: episode information stays inside artwork`);
      assert(layout.width <= width-40 && layout.height < 260,`${name}: stable compact card size`);
      assert.equal(await cards.nth(1).locator('.episode-card-rating').count(),0,'No TMDB fallback under IMDb badge');
      await cards.first().click();
      assert.deepEqual(await page.evaluate(()=>window.actions),['play-1']);
      await cards.first().click({button:'right'});
      assert.equal(await page.evaluate(()=>window.actions.at(-1)),'episode-menu-1');
      await page.getByRole('button',{name:'Season 2',exact:true}).click();
      assert.equal(await page.getByRole('button',{name:'Season 2',exact:true}).getAttribute('aria-pressed'),'true');
      assert.match(await cards.first().innerText(),/S2 E01/);
      await page.keyboard.press('Tab');
      await cards.nth(1).focus();
      assert.match(await cards.nth(1).evaluate(node=>getComputedStyle(node,'::after').boxShadow),/255, 255, 255/,'Focus visible even on white art');
      await page.mouse.move(width-5,height-5);
      await page.waitForTimeout(160);
      await page.screenshot({path:path.join(evidence,`${name}-episodes.png`)});
      await page.evaluate(()=>document.body.classList.add('spoiler-blur'));
      await page.waitForFunction(()=>getComputedStyle(document.querySelectorAll('.episode-card-overview')[2]).filter==='blur(5px)');
      assert.equal(await cards.nth(2).locator('.episode-card-overview').evaluate(node=>getComputedStyle(node).filter),'blur(5px)','Spoiler protection remains available');
      await cards.nth(2).focus();
      await page.waitForFunction(()=>getComputedStyle(document.querySelectorAll('.episode-card-overview')[2]).filter==='none');
      assert.equal(await cards.nth(2).locator('.episode-card-overview').evaluate(node=>getComputedStyle(node).filter),'none','Keyboard focus reveals the protected synopsis');
      await page.goto(origin+'/menu');
      await page.getByRole('button',{name:'Play Episode',exact:true}).focus();
      assert.equal(await page.getByRole('button',{name:'Play Episode',exact:true}).evaluate(node=>getComputedStyle(node).color),'rgb(17, 17, 17)');
      assert.equal(await page.locator('.context-menu-actions .lucide-check').count(),0,'Focus must not imply selection');
      await page.screenshot({path:path.join(evidence,`${name}-menu.png`)});
      await page.goto(origin+'/settings');
      await page.locator('.option-button').click();
      await page.getByRole('button',{name:'Malay',exact:true}).click();
      assert.equal(await page.locator('.option-button').innerText(),'Malay');
      await page.screenshot({path:path.join(evidence,`${name}-settings.png`)});
      await page.goto(origin+'/profile');
      await page.getByLabel('Profile name',{exact:true}).fill('Test profile');
      await page.screenshot({path:path.join(evidence,`${name}-profile-top.png`)});
      await page.getByRole('button',{name:'Save',exact:true}).click();
      await page.getByRole('button',{name:'Cancel',exact:true}).click();
      await page.getByRole('button',{name:'Delete',exact:true}).click();
      assert.deepEqual(await page.evaluate(()=>window.actions),['save','cancel','delete']);
      assert.equal(await page.evaluate(()=>document.documentElement.scrollWidth>innerWidth),false,`${name}: no page overflow`);
      await page.screenshot({path:path.join(evidence,`${name}-profile.png`)});
      assert.deepEqual(errors,[],`${name}: no browser errors`);
      await page.close();
      console.log(`PASS ${name}: cards, seasons, context menu, settings, profile`);
    }
    const page = await browser.newPage({viewport:{width:1440,height:900}});
    await page.goto(origin+'/player');
    await page.getByText('Cached episode three',{exact:true}).waitFor();
    await page.waitForFunction(()=>window.requests.length===1);
    await page.getByRole('button',{name:'Next fixture episode',exact:true}).click();
    await page.getByText('Series synopsis',{exact:true}).waitFor();
    await page.waitForFunction(()=>window.requests.length===2);
    await page.evaluate(()=>window.requests[1].resolve([{seasonNumber:3,episodeNumber:4,overview:'Episode four synopsis'}]));
    await page.getByText('Episode four synopsis',{exact:true}).waitFor();
    await page.evaluate(()=>window.requests[0].resolve([{seasonNumber:3,episodeNumber:3,overview:'Late episode three synopsis'}]));
    await page.waitForTimeout(100);
    assert.equal(await page.locator('.player-overview').innerText(),'Episode four synopsis','Late metadata must not replace current episode');
    await page.screenshot({path:path.join(evidence,'desktop-paused-episode.png')});
    await page.getByRole('button',{name:'Toggle playing',exact:true}).click();
    assert.equal(await page.locator('.player-overview').count(),0,'Synopsis hidden while playing');
    await page.getByRole('button',{name:'Toggle playing',exact:true}).click();
    await page.getByRole('button',{name:'Movie fixture',exact:true}).click();
    assert.equal(await page.locator('.player-overview').innerText(),'Series synopsis','Movies retain their own overview');
    assert.equal(await page.evaluate(()=>window.requests.length),2,'No episode lookup for movies');
    console.log('PASS cached/exact-episode synopsis, next episode, out-of-order responses, paused state, movie fallback');
    await page.close();
  } finally {
    await browser?.close();
    await new Promise(resolve=>server.close(resolve));
  }
}
main().catch(error=>{console.error(error);process.exitCode=1;});

// AniList bursts, repeated TMDB 404s and the details-page external links.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const read = (file) => fs.readFileSync(path.join(__dirname, '..', file), 'utf8');

test('AniList requests are cached, serialised, spaced and paused after a refusal', () => {
  const src = read('lib/metadata/anilist.ts');
  assert.match(src, /const MIN_GAP_MS = 700;/);
  assert.match(src, /const COOLDOWN_MS = 60_000;/);
  assert.match(src, /responses\.get\(key\)/);
  assert.match(src, /if \(res\.status === 429 \|\| res\.status >= 500\) \{ pausedUntil = Date\.now\(\) \+ COOLDOWN_MS;/);
  assert.equal((src.match(/fetch\(ANILIST_GRAPHQL_ENDPOINT/g) || []).length, 1, 'one fetch site, behind the queue');
});

test('TMDB: no retry on 4xx, title 404s remembered for a day', () => {
  const src = read('lib/tmdb.ts');
  assert.match(src, /status >= 400 && status < 500 && status !== 408/);
  assert.match(src, /if \(status === 404 && recordPath\) rememberMissing\(recordPath\);/);
  assert.match(src, /if \(recordPath && knownMissing\(recordPath\)\)/);
});

test('View on Simkl / Trakt open the title page', () => {
  const links = read('lib/externalLinks.ts');
  assert.match(links, /https:\/\/api\.simkl\.com\/redirect\?/);
  assert.match(links, /\$\{relay\}\/trakt\/link\?/);
  const worker = read('../resolver-worker/src/index.ts');
  assert.match(worker, /url\.pathname === "\/trakt\/link"/);
  assert.match(worker, /https:\/\/api\.trakt\.tv\/search\/\$\{source\}/);
  assert.match(worker, /https:\/\/trakt\.tv\/\$\{kind === "movie" \? "movies" : "shows"\}/);
  const drawer = read('components/details/DetailsDrawer.tsx');
  assert.match(drawer, /\?\? simklRedirectUrl\(displayItem\)/);
  assert.match(drawer, /href=\{traktPage \?\? traktLinkUrl\(displayItem, relayTraktLink\)\}/);
  assert.match(links, /ids\?\.traktslug/);
  assert.match(worker, /features: \["trakt-link"\]/);
});

test('Play shows each startup step', () => {
  const drawer = read('components/details/DetailsDrawer.tsx');
  assert.match(drawer, /translateUi\("Picking the best source…"\)/);
  const engine = read('components/player/overlay/usePlaybackEngine.ts');
  assert.match(engine, /stream\.convertAudio \? "Converting the audio for your browser…"/);
});

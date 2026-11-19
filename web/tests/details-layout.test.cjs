// Details page: episode cards fill their slot, the Back button and title stay
// clear of the top bar, and cast photos get the card hover/focus treatment.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const css = fs.readFileSync(path.join(__dirname, '../components/details/details.css'), 'utf8');
const nav = fs.readFileSync(path.join(__dirname, '../components/shell/TopNav.tsx'), 'utf8');
const card = fs.readFileSync(path.join(__dirname, '../components/details/EpisodeCard.tsx'), 'utf8');

test('episode card fills its wrapper (a legacy width:auto !important collapsed it to 0px)', () => {
  assert.match(card, /className=\{`episode-card-wrap/);
  assert.match(css, /width: auto !important;/, 'the legacy rule this override exists for');
  const fix = css.lastIndexOf('.episode-card-wrap > .episode-row { width: 100% !important; }');
  assert.ok(fix > css.lastIndexOf('width: auto !important;'), 'override comes after the legacy rule');
});

test('top bar publishes where its controls end', () => {
  assert.match(nav, /root\.style\.setProperty\("--topnav-bottom"/);
  assert.match(nav, /new ResizeObserver\(measure\)/);
  assert.match(nav, /<header ref=\{barRef\} className=\{`sidebar/);
});

test('Back button and title sit below the top bar on desktop', () => {
  const block = css.slice(css.lastIndexOf('@media (min-width: 681px) {'));
  assert.match(block, /\.details-drawer \.detail-back \{\s*top: calc\(var\(--topnav-bottom, 84px\) \+ 14px\);/);
  assert.match(block, /\.details-drawer \.detail-body \{\s*padding-top: calc\(var\(--topnav-bottom, 84px\) \+ 52px\);/);
  assert.match(block, /\.app-shell:has\(\.details-drawer\) \.sidebar\.is-scrolled::before/);
});

test('cast: the round photo carries hover and focus, not a square box', () => {
  assert.match(css, /\.details-drawer \.mini-card\.cast-card:hover \{\s*transform: none !important;/);
  assert.match(css, /\.details-drawer \.cast-card:hover img,[\s\S]*?box-shadow: 0 0 0 2px color-mix\(in srgb, var\(--accent, #fff\) 76%, transparent\), 0 18px 52px/);
  assert.match(css, /\.details-drawer \.cast-card:focus-visible img,[\s\S]*?html\.kbd-nav \.details-drawer \.cast-card:focus > svg \{\s*transform: scale\(1\.04\);\s*outline: 3px solid #fff;/);
});

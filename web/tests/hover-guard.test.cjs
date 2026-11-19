// Hover effects sit inside `@media (hover: hover) and (pointer: fine)` so a tap
// on iPhone/iPad/Vision Pro never leaves a card lifted or zoomed. The keyboard /
// remote focus twins (lib/hoverParity) must NOT inherit that guard: TV remotes,
// gamepads and keyboards on tablets report no hover-capable pointer.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const postcss = require('postcss');
const { load } = require('./load.cjs');
const { APP_SHEETS } = require('../scripts/app-styles.cjs');

const GUARD = '(hover: hover) and (pointer: fine)';

function fakeCssom() {
  class CSSStyleRule { constructor(selectorText, cssText) { this.selectorText = selectorText; this.style = { cssText }; this.parentRule = null; } }
  class CSSMediaRule { constructor(conditionText, rules) { this.conditionText = conditionText; this.cssRules = rules; this.parentRule = null; for (const r of rules) r.parentRule = this; } }
  const inserted = [];
  const target = { cssRules: [], insertRule(text) { inserted.push(text); this.cssRules.push(text); return this.cssRules.length - 1; } };
  const app = {
    cssRules: [
      new CSSMediaRule(GUARD, [new CSSStyleRule('.media-card:hover .poster', 'transform: scale(1.04);')]),
      new CSSMediaRule('(max-width: 680px)', [new CSSMediaRule(GUARD, [new CSSStyleRule('.rail-arrow:hover', 'opacity: 1;')])]),
      new CSSMediaRule(`(min-width: 1600px) and ${GUARD}`, [new CSSStyleRule('.primary:hover', 'filter: brightness(1.1);')]),
      new CSSStyleRule('.spoiler-blur .episode-row:hover p', 'filter: none;'),
    ],
  };
  const classes = new Set();
  const document = {
    styleSheets: [app],
    head: { appendChild() {} },
    createElement() { return { setAttribute() {}, sheet: target }; },
    documentElement: { classList: { add: (c) => classes.add(c), remove: (c) => classes.delete(c), contains: (c) => classes.has(c) } },
  };
  const window = { addEventListener() {} };
  return { globals: { document, window, CSSStyleRule, CSSMediaRule }, inserted, classes };
}

test('focus twins of guarded hover rules apply on every key-driven device', () => {
  const dom = fakeCssom();
  const parity = load('lib/hoverParity.ts', {}, dom.globals);
  parity.enterKbdNav();
  assert.ok(dom.classes.has('kbd-nav'));
  assert.deepEqual(dom.inserted, [
    'html.kbd-nav .media-card:focus-within .poster { transform: scale(1.04); }',
    '@media (max-width: 680px) { html.kbd-nav .rail-arrow:focus-within { opacity: 1; } }',
    '@media (min-width: 1600px) { html.kbd-nav .primary:focus-within { filter: brightness(1.1); } }',
  ]);
});

test('parityCondition strips only the hover guard', () => {
  const { parityCondition, HOVER_GUARD } = load('lib/hoverParity.ts');
  assert.equal(HOVER_GUARD, GUARD);
  assert.equal(parityCondition(GUARD), null);
  assert.equal(parityCondition(`(max-width: 980px) and ${GUARD}`), '(max-width: 980px)');
  assert.equal(parityCondition('(max-width: 980px)'), '(max-width: 980px)');
  assert.equal(parityCondition(`(max-width: 600px) and ${GUARD}, (min-width: 1600px) and ${GUARD}`), '(max-width: 600px), (min-width: 1600px)');
  assert.equal(parityCondition('(prefers-reduced-motion: reduce)'), '(prefers-reduced-motion: reduce)');
});

// Reveal-on-hover rules (opacity / visibility, spoiler unblur) intentionally stay
// unguarded: on touch screens a tap is the only way to reach them.
function isReveal(rule) {
  if (/spoiler-blur/.test(rule.selector)) return true;
  let reveal = false;
  rule.walkDecls(/^(opacity|visibility)$/, () => { reveal = true; });
  return reveal;
}
function insideGuard(rule) {
  for (let p = rule.parent; p && p.type !== 'root'; p = p.parent) if (p.type === 'atrule' && p.name === 'media' && p.params.includes(GUARD)) return true;
  return false;
}

test('every hover lift/zoom/colour rule in the app sheets is guarded', () => {
  const unguarded = [];
  for (const sheet of [...APP_SHEETS, 'app/migrate/setup.css']) {
    const root = postcss.parse(fs.readFileSync(path.join(__dirname, '..', sheet), 'utf8'));
    root.walkRules((rule) => {
      const hover = rule.selectors.filter((s) => /:hover/.test(s.replace(/:not\([^)]*\)/g, '')));
      if (!hover.length || isReveal(rule) || insideGuard(rule)) return;
      unguarded.push(`${sheet}:${rule.source.start.line} ${hover.join(', ')}`);
    });
  }
  assert.deepEqual(unguarded, []);
});

test('the old html.no-hover hover neutralisers are gone', () => {
  const css = fs.readFileSync(path.join(__dirname, '../app/app-foundations.css'), 'utf8');
  assert.doesNotMatch(css, /html\.no-hover [^{]*:hover/);
});

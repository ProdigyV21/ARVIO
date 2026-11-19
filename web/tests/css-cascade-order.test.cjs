// Cascade-order guard for the split stylesheets.
//
// The app CSS loads as: layout sheets (globals, source-setup, app-foundations),
// then the eager screens' sheets, then each code-split screen's sheet when the
// screen first opens (scripts/app-styles.cjs lists them in that order). A rule
// in an EARLIER sheet can never beat a rule with the same selector in a LATER
// sheet. When stylesheets were one file, a later "override" in globals.css won;
// after the split the same override can end up before its base rule and silently
// stop applying (phones got the desktop login padding and 34px section headings
// that way). This test fails when a declaration is shadowed everywhere it
// applies by the same selector + property in a later-loading sheet, and when
// two code-split sheets (no fixed order between them) set the same selector +
// property to different values under overlapping media.
//
// Fix a failure by moving the override into the same sheet as its base rule,
// after it — never by reordering imports.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const postcss = require('postcss');
const { APP_SHEETS } = require('../scripts/app-styles.cjs');

const root = path.join(__dirname, '..');
const LAYOUT = new Set(['app/globals.css', 'app/source-setup.css', 'app/app-foundations.css']);
const EAGER = new Set(['app/collections.css', 'app/home.css', 'components/profile/profiles.css', 'app/premium.css', 'components/shell/premium-plans.css', 'app/topnav.css']);
const tier = (sheet) => (LAYOUT.has(sheet) ? 0 : EAGER.has(sheet) ? 1 : 2);
// Layout and eager sheets load in a fixed order; code-split sheets after all of them.
const loadsAfter = (later, earlier) => later !== earlier &&
  (tier(later) > tier(earlier) || (tier(later) === tier(earlier) && tier(later) < 2 && APP_SHEETS.indexOf(later) > APP_SHEETS.indexOf(earlier)));

// ---- media evaluation over a grid of devices ----
const WIDTHS = [320, 390, 430, 520, 560, 600, 640, 650, 651, 680, 681, 700, 720, 760, 768, 820, 860, 900, 980, 981, 1000, 1024, 1100, 1180, 1200, 1280, 1440, 1540, 1600, 1700, 1920, 2560];
const HEIGHTS = [400, 500, 600, 700, 850, 1080];
const CONFIGS = [];
for (const w of WIDTHS) for (const h of HEIGHTS) for (const dev of ['mouse', 'touch', 'tv']) for (const rm of ['reduce', 'no-preference']) CONFIGS.push({ w, h, dev, rm });
function feature(f, c) {
  const m = f.match(/^\(\s*([a-z-]+)\s*(?::\s*([^)]+))?\)$/);
  if (!m) return true;
  const k = m[1], v = (m[2] || '').trim(), px = parseFloat(v);
  if (k === 'min-width') return c.w >= px;
  if (k === 'max-width') return c.w <= px;
  if (k === 'min-height') return c.h >= px;
  if (k === 'max-height') return c.h <= px;
  if (k === 'orientation') return v === 'landscape' ? c.w >= c.h : c.w < c.h;
  if (k === 'hover' || k === 'any-hover') return v === (c.dev === 'mouse' ? 'hover' : 'none');
  if (k === 'pointer' || k === 'any-pointer') return v === (c.dev === 'mouse' ? 'fine' : c.dev === 'touch' ? 'coarse' : 'none');
  if (k === 'prefers-reduced-motion') return v === c.rm;
  return true;
}
function applies(media, c) {
  return media.every((q) => q.split(',').some((part) => {
    part = part.trim().toLowerCase();
    let neg = false;
    if (part.startsWith('not ')) { neg = true; part = part.slice(4); }
    const ok = part.split(/\s+and\s+/).filter((x) => x && !['screen', 'all', 'only screen'].includes(x))
      .every((f) => feature(f.replace(/\(\s*/, '(').replace(/\s*\)/, ')'), c));
    return neg ? !ok : ok;
  }));
}

// Declarations that were already dead before the split (the screen sheet's rule
// came later in the single stylesheet too); cleanup candidates, not regressions.
// Format: "<selector> { <property> } @ <earlier sheet> < <later sheet>".
const KNOWN_SHADOWED = new Set(fs.readFileSync(path.join(__dirname, 'css-known-shadowed.txt'), 'utf8').split('\n').map((l) => l.trim()).filter((l) => l && !l.startsWith('#')));

function declarations() {
  const out = [];
  let order = 0;
  for (const sheet of APP_SHEETS) {
    postcss.parse(fs.readFileSync(path.join(root, sheet), 'utf8')).walkDecls((d) => {
      const rule = d.parent;
      if (rule.type !== 'rule') return;
      const media = [];
      let skip = false;
      for (let p = rule.parent; p && p.type !== 'root'; p = p.parent) {
        if (p.type !== 'atrule') continue;
        if (/keyframes$/.test(p.name)) skip = true;
        else if (p.name === 'media') media.push(p.params);
        else skip = skip || p.name === 'supports' || p.name === 'container'; // conditional: leave alone
      }
      if (skip) return;
      const pos = order++;
      for (const sel of rule.selectors) {
        out.push({ key: `${sel.replace(/\s+/g, ' ').trim()} { ${d.prop.toLowerCase()} }`, value: d.value.replace(/\s+/g, ' ').trim(), important: d.important, media, sheet, line: d.source.start.line, pos });
      }
    });
  }
  return out;
}

function violations() {
  const byKey = new Map();
  for (const d of declarations()) (byKey.get(d.key) ?? byKey.set(d.key, []).get(d.key)).push(d);
  const shadowed = [];
  const ambiguous = [];
  for (const [key, list] of byKey) {
    const sheets = new Set(list.map((d) => d.sheet));
    if (sheets.size < 2) continue;
    for (const e of list) {
      // configs where e applies; is it beaten in all of them by a later sheet?
      const live = CONFIGS.filter((c) => applies(e.media, c));
      if (!live.length) continue;
      const beatenBy = live.map((c) => list.find((l) => loadsAfter(l.sheet, e.sheet) &&
        l.value !== e.value && (l.important || !e.important) && applies(l.media, c)));
      if (beatenBy.every(Boolean) && !KNOWN_SHADOWED.has(`${key} @ ${e.sheet} < ${beatenBy[0].sheet}`)) {
        shadowed.push(`${key} @ ${e.sheet} < ${beatenBy[0].sheet}: ${e.value} (line ${e.line}) never wins over ${beatenBy[0].value} (line ${beatenBy[0].line})`);
      }
      if (tier(e.sheet) === 2) {
        const other = list.find((l) => tier(l.sheet) === 2 && l.sheet !== e.sheet && l.sheet < e.sheet && l.value !== e.value && !!l.important === !!e.important && live.some((c) => applies(l.media, c)));
        if (other) ambiguous.push(`${key}: ${e.sheet}:${e.line} (${e.value}) vs ${other.sheet}:${other.line} (${other.value})`);
      }
    }
  }
  return { shadowed: [...new Set(shadowed)], ambiguous: [...new Set(ambiguous)] };
}

test('no declaration is silently overridden by the same selector in a later-loading sheet', () => {
  assert.deepEqual(violations().shadowed, []);
});

test('code-split sheets never set the same selector + property to different values', () => {
  assert.deepEqual(violations().ambiguous, []);
});

test('the check catches an override that loads before its base rule', () => {
  // globals.css used to carry phone overrides after the base login rules; once
  // the base moved into the code-split login sheet, the override lost.
  const e = { media: ['(max-width: 680px)'] }, l = { media: [] };
  const live = CONFIGS.filter((c) => applies(e.media, c));
  assert.ok(live.length > 0 && live.every((c) => applies(l.media, c)));
  assert.equal(tier('app/globals.css') < tier('components/login/login.css'), true);
});

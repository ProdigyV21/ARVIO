// Right-to-left guard for the app stylesheets (Arabic, Hebrew, Persian, Urdu).
// Start/end spacing, borders and alignment are written logically
// (margin-inline-start, text-align: start, …; Next's autoprefixer emits the
// -webkit-*-start/end fallbacks older TV engines need). Offsets and other
// geometry stay physical — inset-inline-* is unknown to pre-2021 TV browsers —
// and every directional one has a `[dir="rtl"]` twin right after it.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const postcss = require('postcss');
const { APP_SHEETS } = require('../scripts/app-styles.cjs');

const SHEETS = [...APP_SHEETS, 'app/migrate/setup.css'];
// Physical on purpose: the video timeline and transport controls run left
// to right in every language (and the double-tap seek zones stay put), the pointer-placed context menu and the guide's now line are
// positioned by script, the on-screen keyboard is a Latin keyboard, and
// safe-area insets belong to a physical side of the device.
const PHYSICAL_SELECTORS = [/scrubber|player-progress|seek-preview|volume-slider|player-timeline/, /\.player-controls/, /player-skip-overlay/, /context-menu-card\.is-floating/, /livetv-guide-nowline/, /^\.osk/];
const physicalRule = (rule) => rule.selectors.every((s) => PHYSICAL_SELECTORS.some((re) => re.test(s)));
const physicalValue = (value) => /safe-area-inset-(left|right)/.test(value);

function eachRule(fn) {
  for (const sheet of SHEETS) {
    const root = postcss.parse(fs.readFileSync(path.join(__dirname, '..', sheet), 'utf8'));
    root.walkRules((rule) => {
      if (rule.parent.type === 'atrule' && /keyframes$/.test(rule.parent.name)) return;
      if (rule.selectors.some((s) => s.includes('[dir="rtl"]'))) return;
      fn(rule, sheet);
    });
  }
}

test('start/end spacing, borders and alignment are logical', () => {
  const found = [];
  eachRule((rule, sheet) => {
    if (physicalRule(rule)) return;
    rule.walkDecls((d) => {
      const prop = d.prop.toLowerCase();
      if (physicalValue(d.value)) return;
      if (/^(margin|padding)-(left|right)$/.test(prop) || /^border-(left|right)(-|$)/.test(prop)) found.push(`${sheet}: ${rule.selector} { ${prop} }`);
      if (prop === 'text-align' && /\b(left|right)\b/.test(d.value)) found.push(`${sheet}: ${rule.selector} { text-align: ${d.value} }`);
      if (/^inset-inline/.test(prop)) found.push(`${sheet}: ${rule.selector} { ${prop} } (unsupported before Chromium 87 / Safari 14.1)`);
      if ((prop === 'margin' || prop === 'padding') && postcss.list.space(d.value).length === 4) {
        const v = postcss.list.space(d.value);
        if (v[1] !== v[3]) found.push(`${sheet}: ${rule.selector} { ${prop}: ${d.value} }`);
      }
    });
  });
  assert.deepEqual(found, []);
});

test('every directional left/right offset has a right-to-left twin', () => {
  const missing = [];
  for (const sheet of SHEETS) {
    const root = postcss.parse(fs.readFileSync(path.join(__dirname, '..', sheet), 'utf8'));
    root.walkRules((rule) => {
      if (rule.parent.type === 'atrule' && /keyframes$/.test(rule.parent.name)) return;
      if (rule.selectors.some((s) => s.includes('[dir="rtl"]')) || physicalRule(rule)) return;
      let left = null, right = null, transform = null;
      rule.each((d) => {
        if (d.type !== 'decl') return;
        if (d.prop === 'left') left = d.value;
        if (d.prop === 'right') right = d.value;
        if (d.prop === 'transform') transform = d.value;
        if (d.prop === 'inset') { const v = postcss.list.space(d.value); if (v.length === 4) { right = v[1]; left = v[3]; } else if (v.length >= 2) { right = v[1]; left = v[1]; } else { left = right = v[0]; } }
      });
      if (left === null && right === null) return;
      if (left !== null && left === right) return; // symmetric
      if (left === '50%' && /translate(X)?\(\s*-50%/.test(transform || '')) return; // centred
      if (physicalValue(`${left} ${right}`)) return;
      const twin = rule.next();
      const expected = rule.selectors.map((s) => s.trim().startsWith('html') ? s.trim().replace(/^html/, 'html[dir="rtl"]') : `[dir="rtl"] ${s.trim()}`);
      const ok = twin && twin.type === 'rule' && expected.every((s) => twin.selectors.includes(s)) && twin.nodes.some((d) => d.prop === 'left') && twin.nodes.some((d) => d.prop === 'right');
      if (!ok) missing.push(`${sheet}:${rule.source.start.line} ${rule.selector}`);
    });
  }
  assert.deepEqual(missing, []);
});

test('directional icons mirror, the player timeline does not', () => {
  const css = fs.readFileSync(path.join(__dirname, '../app/globals.css'), 'utf8');
  for (const icon of ['arrow-left', 'arrow-right', 'chevron-left', 'chevron-right']) assert.match(css, new RegExp(`\\[dir="rtl"\\] \\.lucide-${icon}`));
  const player = fs.readFileSync(path.join(__dirname, '../components/player/player.css'), 'utf8');
  assert.match(player, /\[dir="rtl"\] \.player-controls\s*\{[^}]*direction:\s*ltr/);
});

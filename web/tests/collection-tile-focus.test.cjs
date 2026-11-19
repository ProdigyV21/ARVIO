// Home collection tiles react like title cards, with the mouse and the remote.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const css = fs.readFileSync(path.join(__dirname, '../app/collections.css'), 'utf8');
test('mouse hover: the same lift, accent ring, shadow and artwork zoom as a title card', () => {
  const hover = css.slice(css.indexOf('@media (hover: hover) and (pointer: fine) {\n  /* Exactly what a title card'));
  assert.match(hover, /\.custom-collection-tile:hover \{\s*z-index: 6; transform: translateY\(-4px\); filter: brightness\(1\.05\);/);
  assert.match(hover, /box-shadow: 0 0 0 2px color-mix\(in srgb, var\(--accent, #fff\) 76%, transparent\), 0 18px 52px/);
  assert.match(hover, /\.custom-collection-tile:hover img \{ transform: scale\(1\.045\); \}/);
});
test('keyboard / remote focus: the white ring, scale and halo', () => {
  assert.match(css, /\.custom-collection-tile:focus-visible,\s*html\.kbd-nav \.custom-collection-tile:focus \{ z-index: 6; transform: scale\(1\.04\); outline: 3px solid #fff/);
});

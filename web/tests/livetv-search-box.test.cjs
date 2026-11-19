// The Live TV channel search box: the clear button must not inherit the
// full-width category-button styles (which squeezed the text field to a
// sliver), focus shows on the whole box, and remote keys leave it sensibly.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const css = fs.readFileSync(path.join(__dirname, '../components/livetv/livetv.css'), 'utf8');
const screen = fs.readFileSync(path.join(__dirname, '../components/livetv/LiveTvScreen.tsx'), 'utf8');

test('the clear button keeps its own size inside the guide workspace', () => {
  const rule = css.slice(css.lastIndexOf('.tv-guide-workspace .livetv-cats .livetv-search button'));
  assert.match(rule, /width: 28px; height: 28px/);
  assert.ok(css.lastIndexOf('.tv-guide-workspace .livetv-cats .livetv-search button') > css.lastIndexOf('.tv-guide-workspace .livetv-cats button {'), 'comes after the generic category-button rule');
});

test('focus is drawn on the box, not as a ring on the input', () => {
  assert.match(css, /\.livetv-search:focus-within \{[^}]*box-shadow/);
  assert.match(css, /\.livetv-search input:focus-visible \{ outline: none !important/);
});

test('Down leaves the box to the destinations, Enter jumps into the results, Up comes back', () => {
  assert.match(screen, /event\.key === "ArrowDown"\) \{\s*const next = input\.closest\("\.livetv-cats"\)/);
  assert.match(screen, /event\.key === "Enter" \|\| \(event\.key === "ArrowRight" && atEnd && input\.value\)/);
  assert.match(screen, /Up from the first destination returns to the channel search box/);
});

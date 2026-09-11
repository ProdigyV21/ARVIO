const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const postcss = require('postcss');

const css = postcss.parse(fs.readFileSync(path.join(__dirname, '../app/globals.css'), 'utf8'));
function declarations(selector) {
  const values = {};
  css.walkRules(rule => {
    if (rule.selectors.includes(selector)) rule.walkDecls(decl => { values[decl.prop] = decl.value; });
  });
  return values;
}

test('WebKit seek thumb remains vertically centered at rest, on hover and on keyboard focus', () => {
  const base = declarations('.player-controls .scrubber');
  const thumb = declarations('.player-controls .scrubber::-webkit-slider-thumb');
  assert.equal(base.height, 'var(--scrubber-track-height)');
  assert.equal(thumb.height, 'var(--scrubber-thumb-size)');
  assert.equal(thumb['margin-top'], 'calc((var(--scrubber-track-height) - var(--scrubber-thumb-size)) / 2)');
  assert.match(thumb.transition, /margin-top 0\.12s ease/);
  assert.equal(base.transition, 'height 0.12s ease');
  for (const state of ['', '.player-controls .scrubber:hover', '.scrubber-track:hover .scrubber', '.scrubber-track:focus-within .scrubber']) {
    const style = { ...base, ...declarations(state) };
    const trackSize = parseFloat(style['--scrubber-track-height']);
    const thumbSize = parseFloat(style['--scrubber-thumb-size']);
    const offset = (trackSize - thumbSize) / 2;
    assert.equal(offset + thumbSize / 2, trackSize / 2, state || 'rest');
  }
});

test('Firefox retains native centering with the same thumb dimensions', () => {
  const thumb = declarations('.player-controls .scrubber::-moz-range-thumb');
  assert.equal(thumb.width, 'var(--scrubber-thumb-size)');
  assert.equal(thumb.height, 'var(--scrubber-thumb-size)');
  assert.equal(thumb['margin-top'], undefined);
});

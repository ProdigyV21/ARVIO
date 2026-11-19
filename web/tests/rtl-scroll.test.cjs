// Rails measure and scroll from the inline start, so Arabic/Hebrew rails start
// at the right edge and "next" moves towards the left, in every engine.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { scrollFromStart, towardsEnd } = load('lib/rtlScroll.ts');

const box = (scrollLeft) => ({ scrollLeft, scrollWidth: 1000, clientWidth: 400 });

test('left-to-right rails count scrollLeft from the start', () => {
  assert.equal(scrollFromStart(box(0), false, 'negative'), 0);
  assert.equal(scrollFromStart(box(250), false, 'negative'), 250);
  assert.equal(towardsEnd(300, false, 'negative'), 300);
  assert.equal(towardsEnd(-300, false, 'default'), -300);
});

test('current engines: 0 at the start, negative towards the end', () => {
  assert.equal(scrollFromStart(box(0), true, 'negative'), 0);
  assert.equal(scrollFromStart(box(-600), true, 'negative'), 600);
  // "next" moves the viewport left
  assert.equal(towardsEnd(300, true, 'negative'), -300);
  assert.equal(towardsEnd(-300, true, 'negative'), 300);
});

test('older Chromium/WebKit TV engines: the maximum at the start, counting down', () => {
  assert.equal(scrollFromStart(box(600), true, 'default'), 0);
  assert.equal(scrollFromStart(box(0), true, 'default'), 600);
  assert.equal(towardsEnd(300, true, 'default'), -300);
});

test('legacy Edge: 0 at the start, positive towards the end', () => {
  assert.equal(scrollFromStart(box(0), true, 'reverse'), 0);
  assert.equal(scrollFromStart(box(600), true, 'reverse'), 600);
  assert.equal(towardsEnd(300, true, 'reverse'), 300);
});

test('the interface direction follows the language', () => {
  const { directionOf } = load('lib/i18n/core.ts', { './manifest.json': require('../lib/i18n/manifest.json') });
  for (const code of ['ar', 'ar-SA', 'he', 'iw', 'fa', 'ur-PK']) assert.equal(directionOf(code), 'rtl', code);
  for (const code of ['en', 'nl', 'fr-CA', 'hi', 'zh-CN', 'arn']) assert.equal(directionOf(code), 'ltr', code);
});

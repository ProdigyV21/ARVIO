const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const browser = (lang, languages) => ({ document: { documentElement: { lang } }, navigator: { languages, language: languages[0] } });

test('dates follow the app language and keep the browser region', () => {
  const { uiLocales } = load('lib/uiLocale.ts', {}, browser('en-US', ['nl', 'nl-NL']));
  assert.deepEqual([...uiLocales()], ['en-NL', 'en-US']);
  const text = load('components/details/detailsText.ts', {}, browser('en-US', ['nl', 'nl-NL']));
  const date = text.formatDate('2025-03-30');
  assert.match(date, /Mar/, 'English month name on an English UI');
  assert.doesNotMatch(date, /mrt/, 'not the Dutch browser month');
});

test('without a region or a document the formatter falls back cleanly', () => {
  assert.deepEqual([...load('lib/uiLocale.ts', {}, browser('de', ['de'])).uiLocales()], ['de']);
  assert.equal(load('lib/uiLocale.ts').uiLocales(), undefined);
});

test('the Live TV guide day uses the app language', () => {
  const guide = load('lib/iptvGuide.ts', {}, browser('en-US', ['nl-NL']));
  const day = guide.formatGuideDay(Date.UTC(2026, 10, 24, 12));
  assert.match(day, /Tue/);
});

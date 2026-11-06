const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const { sourceTitle } = load('components/details/sourcePlaybackPresentation.ts');
const stream = (overrides = {}) => ({
  source: 'Fixture',
  addonName: 'Library',
  ...overrides
});

test('sourceTitle tolerates malformed runtime filename values', () => {
  for (const filename of [123, { name: 'movie.mkv' }, ['movie.mkv']]) {
    assert.equal(sourceTitle(stream({ behaviorHints: { filename } })), 'Fixture');
  }
});

test('sourceTitle tolerates malformed runtime description values', () => {
  for (const description of [123, { text: 'movie' }, ['movie']]) {
    assert.equal(sourceTitle(stream({ description })), 'Fixture');
  }
});

test('sourceTitle keeps valid provider labels and safe fallbacks', () => {
  assert.equal(sourceTitle(stream({ behaviorHints: { filename: '  Movie.2026.1080p.mkv  ' } })), 'Movie.2026.1080p.mkv');
  assert.equal(sourceTitle(stream({ description: 'Movie.2026.1080p.WEB-DL.mkv\n💾 3.2 GB' })), 'Movie.2026.1080p.WEB-DL.mkv');
  assert.equal(sourceTitle({ source: 42, addonName: 'Fallback addon', behaviorHints: { filename: null }, description: null }), 'Fallback addon');
  assert.equal(sourceTitle({ source: null, addonName: null, behaviorHints: { filename: {} }, description: {} }), 'Source');
});

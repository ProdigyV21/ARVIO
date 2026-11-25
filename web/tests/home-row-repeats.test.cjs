const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { trimRepeatedTitles, homeItemKey } = load('lib/homeRows.ts');
const { sharedChannelPrefix, withoutCategoryPrefix } = load('lib/iptvCategoryFilter.ts');

const items = (ids) => ids.map((id) => ({ mediaType: 'tv', id }));

test('a row skips titles a row above already shows', () => {
  const seen = new Set(items([1, 2]).map(homeItemKey));
  const row = items([1, 3, 4, 5, 6, 7, 8, 9, 10, 11]);
  assert.deepEqual(trimRepeatedTitles(row, seen).map((item) => item.id), [3, 4, 5, 6, 7, 8, 9, 10, 11]);
});

test('a row that would be hollowed out stays whole', () => {
  const row = items([1, 2, 3, 4]);
  const seen = new Set(items([1, 2]).map(homeItemKey));
  assert.equal(trimRepeatedTitles(row, seen), row);
});

test('channel rows drop the provider tag most names share', () => {
  const names = ['Strong8K · NPO 1', 'Strong8K · NPO 2', 'Strong8K · RTL 4', 'Strong8K · SBS 6', 'BBC One'];
  const prefix = sharedChannelPrefix(names);
  assert.equal(prefix, 'Strong8K · ');
  assert.equal(withoutCategoryPrefix(names[0], prefix), 'NPO 1');
  assert.equal(sharedChannelPrefix(['NPO 1', 'RTL 4', 'News: Live', 'BBC One']), '');
});

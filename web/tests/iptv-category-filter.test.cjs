const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { sharedCategoryPrefix, matchesCategoryQuery, foldCategoryText } = load('lib/iptvCategoryFilter.ts');

test('the playlist name shared by every category is said once', () => {
  assert.equal(sharedCategoryPrefix(['Strong8K: NL| ALGEMEEN', 'Strong8K: AFR| AZAM', 'Strong8K: 4K| RELAX']), 'Strong8K: ');
  assert.equal(sharedCategoryPrefix(['Strong8K: NL', 'Other: AFR', 'Strong8K: 4K']), '');
  assert.equal(sharedCategoryPrefix(['News', 'Sports', 'Movies']), '');
  assert.equal(sharedCategoryPrefix(['A: x', 'A: y']), '', 'too few to be sure');
});

test('the filter ignores case, accents, superscripts and word order', () => {
  assert.equal(foldCategoryText('4K| RELAX ᵁᴴᴰ'), '4k| relax uhd');
  assert.ok(matchesCategoryQuery('Strong8K: 4K| RELAX ᵁᴴᴰ ³⁸⁴⁰ᴾ', 'relax uhd'));
  assert.ok(matchesCategoryQuery('Strong8K: NL| ALGEMEEN HD/4K', 'nl algemeen'));
  assert.ok(matchesCategoryQuery('Télé France', 'tele'));
  assert.ok(!matchesCategoryQuery('Strong8K: AFR| AZAM NETWORK', 'nl'));
  assert.ok(matchesCategoryQuery('Anything', '  '));
});

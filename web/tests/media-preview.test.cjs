const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { mediaPreviewKey, mergeMediaPreview } = load('lib/mediaPreview.ts');

test('preview enrichment preserves server identity, playback state and original artwork', () => {
  const item = { id: -1, title: 'Provider title', mediaType: 'movie', isHomeServer: true,
    homeServerId: 'jellyfin-a', homeServerItemId: 'native-1', progress: 45, image: 'provider-art' };
  const result = mergeMediaPreview(item, { id: 101, title: 'TMDB title', mediaType: 'movie',
    overview: 'Full description', duration: '2h', image: 'tmdb-poster', backdrop: 'tmdb-backdrop', budget: 100000 });
  assert.equal(result.id, -1);
  assert.equal(result.title, item.title);
  assert.equal(result.homeServerItemId, item.homeServerItemId);
  assert.equal(result.progress, 45);
  assert.equal(result.image, 'provider-art');
  assert.equal(result.overview, 'Full description');
});

test('preview keys distinguish movie, TV, native addons and multiple servers', () => {
  const base = { id: 101, mediaType: 'movie' };
  assert.notEqual(mediaPreviewKey(base), mediaPreviewKey({ ...base, mediaType: 'tv' }));
  const server = { ...base, isHomeServer: true, homeServerId: 'a', homeServerItemId: '1' };
  assert.notEqual(mediaPreviewKey(server), mediaPreviewKey({ ...server, homeServerId: 'b' }));
  assert.notEqual(mediaPreviewKey({ ...base, addonNativeId: 'native', addonNativeAddonId: 'a' }),
    mediaPreviewKey({ ...base, addonNativeId: 'native', addonNativeAddonId: 'b' }));
});

test('missing and wrong-type preview metadata cannot clear the original description', () => {
  const item = { id: 101, mediaType: 'movie', overview: 'Provider description' };
  assert.equal(mergeMediaPreview(item, null), item);
  assert.equal(mergeMediaPreview(item, { mediaType: 'tv', overview: 'Wrong type' }), item);
  assert.equal(mergeMediaPreview(item, { mediaType: 'movie', overview: '' }).overview, item.overview);
});

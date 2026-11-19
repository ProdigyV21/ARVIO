const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const kids = load('lib/kids.ts', { './youtubeCollection': { isYouTubeItem: (item) => item.addonNativeAddonId === 'arvio_youtube' } });
const { hashProfilePin, verifyProfilePin, isValidProfilePin } = load('lib/profilePin.ts');

// Ported from app/src/test/kotlin/com/arflix/tv/util/KidsCertificationTest.kt — both apps must agree.
test('reads named and numeric certifications', () => {
  const cases = { 'G': 0, 'PG': 7, 'PG-13': 13, 'TV-Y7': 7, 'TV-MA': 17, '12A': 12, '16': 16, 'MA15+': 15, 'PG12': 12, ' l ': 0 };
  for (const [value, age] of Object.entries(cases)) assert.equal(kids.minimumAge(value), age, value);
});

test('unreadable certifications are unknown', () => {
  for (const value of [null, '', 'NR', 'E']) assert.equal(kids.minimumAge(value), null, String(value));
});

test('movie uses US when the own country is missing or unreadable', () => {
  const response = { results: [
    { iso_3166_1: 'FR', release_dates: [{ certification: 'NR', type: 3 }] },
    { iso_3166_1: 'US', release_dates: [{ certification: 'PG', type: 3 }] }
  ] };
  assert.equal(kids.movieAge(response, 'FR'), 7);
  assert.equal(kids.movieAge(response, 'DE'), 7);
  assert.equal(kids.movieAge({ results: [] }, 'US'), null);
});

test('the theatrical rating wins over other release types', () => {
  const response = { results: [{ iso_3166_1: 'DE', release_dates: [{ certification: '12', type: 4 }, { certification: '16', type: 3 }] }] };
  assert.equal(kids.movieAge(response, 'DE'), 16);
});

test('the stricter of own country and US counts', () => {
  const response = { results: [
    { iso_3166_1: 'IL', rating: 'All' },
    { iso_3166_1: 'DE', rating: '16' },
    { iso_3166_1: 'US', rating: 'TV-PG' }
  ] };
  assert.equal(kids.tvAge(response, 'IL'), 10);
  assert.equal(kids.tvAge(response, 'DE'), 16);
  assert.equal(kids.tvAge(response, 'US'), 10);
});

test('filter allows only known titles under the cap', () => {
  const item = (id, mediaType, extra = {}) => ({ id, title: String(id), mediaType, ...extra });
  const ages = new Map([
    [kids.ratingKey('US', 'movie', 1), 10],
    [kids.ratingKey('US', 'movie', 2), 13],
    [kids.ratingKey('US', 'tv', 3), kids.UNRATED]
  ]);
  const older = kids.KIDS_LEVEL_MAX_AGE.OLDER_KIDS;
  assert.equal(kids.allowsItem(item(1, 'movie'), older, 'US', ages), true);
  assert.equal(kids.allowsItem(item(2, 'movie'), older, 'US', ages), false);
  assert.equal(kids.allowsItem(item(3, 'tv'), older, 'US', ages), false, 'unrated is blocked');
  assert.equal(kids.allowsItem(item(4, 'tv'), older, 'US', ages), false, 'unknown is hidden');
  assert.equal(kids.allowsItem(item(-5, 'movie', { addonNativeId: 'x' }), older, 'US', ages), false, 'no TMDB identity');
  assert.equal(kids.allowsItem(item(-6, 'movie', { addonNativeAddonId: 'arvio_youtube' }), older, 'US', ages), true, 'parent-approved YouTube row');
  assert.equal(kids.isUnknownItem(item(4, 'tv'), 'US', ages), true);
  assert.equal(kids.allowsItem(item(2, 'movie'), kids.KIDS_LEVEL_MAX_AGE.TEENS, 'US', ages), true);
  assert.equal(kids.allowsItem(item(4, 'tv'), null, 'US', new Map()), true, 'regular profiles are unfiltered');
});

test('home-server items are judged by their TMDB match', () => {
  const ages = new Map([[kids.ratingKey('US', 'movie', 77), 0]]);
  assert.equal(kids.allowsItem({ id: 9001, tmdbId: 77, isHomeServer: true, title: 'x', mediaType: 'movie' }, 7, 'US', ages), true);
  assert.equal(kids.allowsItem({ id: 9002, tmdbId: null, isHomeServer: true, title: 'y', mediaType: 'movie' }, 7, 'US', ages), false);
});

test('profiles saved before the age level read as the default', () => {
  assert.equal(kids.kidsLevelOf({ isKidsProfile: true, kidsAgeLevel: null }), 'OLDER_KIDS');
  assert.equal(kids.kidsLevelOf({ isKidsProfile: true, kidsAgeLevel: 'TEENS' }), 'TEENS');
  assert.equal(kids.kidsLevelOf({ isKidsProfile: false, kidsAgeLevel: 'TEENS' }), null);
  assert.equal(kids.kidsLevelOf(null), null);
});

test('content language picks the rating country', () => {
  assert.equal(kids.regionOf('de-DE'), 'DE');
  assert.equal(kids.regionOf('pt-BR'), 'BR');
  assert.equal(kids.regionOf('en'), 'US');
  assert.equal(kids.regionOf('zh-Hans'), 'US');
});

test('rating store looks titles up, caches them and gates single titles', async () => {
  const calls = [];
  const fetcher = async (mediaType, id) => {
    calls.push(`${mediaType}/${id}`);
    if (id === 1) return { results: [{ iso_3166_1: 'US', release_dates: [{ certification: 'G', type: 3 }] }] };
    if (id === 2) return { results: [{ iso_3166_1: 'US', rating: 'TV-MA' }] };
    throw new Error('offline');
  };
  const saved = new Map();
  const storage = { getItem: (k) => saved.get(k) ?? null, setItem: (k, v) => saved.set(k, v), removeItem: (k) => saved.delete(k) };
  const store = kids.createKidsRatingStore(fetcher, storage);
  let notified = 0;
  store.subscribe(() => { notified += 1; });
  store.request([{ id: 1, title: 'a', mediaType: 'movie' }, { id: 2, title: 'b', mediaType: 'tv' }, { id: 3, title: 'c', mediaType: 'movie' }], 'US');
  await new Promise((resolve) => setTimeout(resolve, 400));
  assert.equal(store.getAges().get(kids.ratingKey('US', 'movie', 1)), 0);
  assert.equal(store.getAges().get(kids.ratingKey('US', 'tv', 2)), 17);
  assert.equal(store.getAges().has(kids.ratingKey('US', 'movie', 3)), false, 'a failed lookup stays unknown');
  assert.ok(notified >= 1);
  assert.ok(saved.get('arvio_kids_ratings_v3').includes('US/MOVIE/1'));

  assert.equal(await store.isAllowed({ id: 1, title: 'a', mediaType: 'movie' }, 7, 'US'), true);
  assert.equal(await store.isAllowed({ id: 2, title: 'b', mediaType: 'tv' }, 14, 'US'), false);
  assert.equal(await store.isAllowed({ id: 3, title: 'c', mediaType: 'movie' }, 14, 'US'), false, 'unreachable rating is blocked');

  const reopened = kids.createKidsRatingStore(async () => { throw new Error('not called'); }, storage);
  assert.equal(reopened.getAges().get(kids.ratingKey('US', 'tv', 2)), 17, 'ages survive a reload');
});

test('parent PIN hashes in the Android PinUtil format and verifies', async () => {
  assert.equal(isValidProfilePin('1234'), true);
  assert.equal(isValidProfilePin('12a4'), false);
  const stored = await hashProfilePin('4321');
  const [salt, hash] = stored.split('$');
  assert.equal(Buffer.from(salt, 'base64').length, 16);
  assert.equal(Buffer.from(hash, 'base64').length, 32);
  assert.equal(await verifyProfilePin('4321', stored), true);
  assert.equal(await verifyProfilePin('4322', stored), false);
  assert.notEqual(await hashProfilePin('4321'), stored, 'a fresh salt each time');
});

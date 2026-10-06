// The Nuvio import, run against the REAL cloud helpers with a stubbed backend.
//
// The unit tests above stub `saveCloudSettings`, which is exactly the layer the
// first two findings on PR #774 lived in: the import looked correct and the
// damage happened inside the helper. These tests assert on the payload that
// actually leaves the browser.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const same = (actual, expected) => assert.deepEqual(JSON.parse(JSON.stringify(actual)), expected);

// What the account already contains: a theme, AI subtitle settings with a saved
// key, an IPTV playlist, and one catalog. None of it is the import's business.
const account = () => ({
  version: 2,
  accentColor: '#00d588',
  oledBlackBackground: true,
  subtitleAiEnabled: true,
  subtitleAiApiKey: 'the-users-saved-key',
  subtitleAiModel: 'groq',
  profiles: [{ id: 'p1', name: 'Main' }],
  catalogsByProfile: { p1: [{ id: 'trending_movies', name: 'Trending in Movies', enabled: true, isPreinstalled: true }] },
  iptvByProfile: { p1: { playlists: [{ id: 'a', name: 'Home', m3uUrl: 'https://example.invalid/a.m3u' }], favoriteChannels: ['a:1'] } },
  addonsByProfile: { p1: [] },
  addons: []
});

const snapshot = {
  email: 'demo@nuvio.example',
  warnings: [],
  profiles: [{
    profileId: 1, name: 'Main', avatarColorHex: '#E50914', avatarId: 'avatar_3', pinEnabled: false,
    addons: [{ url: 'https://a.example/manifest.json', name: 'A', enabled: true, sortOrder: 0 }],
    plugins: [],
    collectionsJson: [{ id: 'studios', title: 'Studios', folders: [
      { id: 'pixar', title: 'Pixar', sources: [{ provider: 'tmdb', tmdbSourceType: 'COMPANY', tmdbId: 3, mediaType: 'MOVIE' }] }
    ] }],
    homeCatalogSettings: null
  }]
};

// Only the fields saveCloudSettings reads; the rest of AppSettings is irrelevant
// here and the page passes its own defaults for them.
const defaults = {
  catalogs: [], hiddenCatalogIds: [], hiddenHomeServerCatalogIds: [],
  accentColor: '#ffffff', oledBlack: false, aiSubtitlesEnabled: false, aiApiKey: '',
  aiSubtitleModel: 'off', aiAutoSelect: false, removeHearingImpaired: false,
  customUserAgent: '', skipProfileSelection: false, dnsProvider: 'system',
  iptvPlaylists: [], iptvStalkerUrl: '', iptvStalkerMac: '',
  favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [],
  iptvSortOrder: 'provider', homeServers: [], subtitleStyle: 'outline', subtitleSize: 100,
  subtitleOffset: 0, subtitleColorName: 'white', frameRateMatchingMode: 'off',
  autoPlayMinQuality: 'any', defaultSubtitle: 'Off', audioLanguage: 'Auto (Original)'
};

// `failRead` picks which account reads fail, counted in order: 1 is the read
// behind the profile-list write, 2 is the import's own read of the profile.
function setup({ failRead = () => false } = {}) {
  let stored = account();
  let reads = 0;
  let writes = 0;
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '[]', parseHomeServerConnectionJson: () => [] },
    './iptv': load('lib/iptv.ts', { './http': {}, './storage': storage() }),
    './mediaImages': { tmdbImageUrl: () => '' },
    './http': {
      jsonRequest: async (_url, options) => {
        if (options?.method === 'POST') { writes += 1; stored = JSON.parse(options.body).payload; return { accepted: true }; }
        reads += 1;
        if (failRead(reads)) throw new Error('backend unreachable');
        return { payload: structuredClone(stored) };
      }
    }
  });
  const runner = load('lib/nuvioImportRunner.ts', {
    './addons': {
      ...load('lib/addons.ts', {
        './http': {}, './config': { hasResolverConfig: () => false }, './resolver': {},
        './storage': storage(), './streamCompatibility': {}, './addonStreamInfo': {}
      }),
      installAddon: async (url) => ({ id: url, name: 'Imported addon', version: '1.0.0', manifestUrl: url, catalogs: [], resources: [], enabled: true, isEnabled: true })
    },
    './auth': {}, './types': {},
    './catalogs': load('lib/catalogs.ts'),
    './cloud': cloud,
    './customCollections': load('lib/customCollections.ts'),
    './nuvioMigration': load('lib/nuvioMigration.ts'),
    './profiles': load('lib/profiles.ts')
  });
  return { runner, read: () => stored, writes: () => writes };
}

const auth = {
  session: { userId: 'u1', email: 'me@arvio.example', accessToken: 'test' },
  isNetlifySession: true, accessToken: async () => 'test'
};
const choices = [{ nuvioProfileId: 1, target: { kind: 'existing', profileId: 'p1' } }];

test('an import leaves every settings field it does not own untouched', async () => {
  const { runner, read } = setup();
  const before = account();
  const result = await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot, choices, baseSettings: defaults
  });
  assert.equal(result.summaries[0].failed, undefined);

  const after = read();
  // Theme, AI subtitles and the saved key are exactly as the account had them —
  // this is the regression that reset them to the page's defaults.
  assert.equal(after.accentColor, before.accentColor);
  assert.equal(after.oledBlackBackground, true);
  assert.equal(after.subtitleAiEnabled, true);
  assert.equal(after.subtitleAiApiKey, 'the-users-saved-key');
  assert.equal(after.subtitleAiModel, 'groq');
  // …and so is the IPTV setup, which the same write used to clear.
  same(after.iptvByProfile.p1.playlists, before.iptvByProfile.p1.playlists);
  same(after.iptvByProfile.p1.favoriteChannels, before.iptvByProfile.p1.favoriteChannels);

  // The one thing the import does own did change: the catalog the account had
  // is still there, with the imported collection added alongside it.
  const catalogs = after.catalogsByProfile.p1;
  assert.ok(catalogs.some(catalog => catalog.id === 'trending_movies'));
  assert.ok(catalogs.length > before.catalogsByProfile.p1.length);
  assert.equal(after.addonsByProfile.p1.length, 1);
});

test('a failed read writes nothing at all', async () => {
  // One read fails — the import's own read of the profile — while everything
  // else keeps working. That is the exact shape of the bug: the failure passed
  // for an empty profile and the write went ahead with the page's defaults.
  const { runner, read } = setup({ failRead: (n) => n === 2 });
  const before = account();
  const result = await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot, choices, baseSettings: defaults
  });
  assert.ok(result.summaries[0].failed, 'the profile is reported as not imported');

  const after = read();
  // Not one field of the profile's state was written: no defaults over the
  // theme or the saved key, no replaced catalogs, no cleared IPTV playlists.
  assert.equal(after.accentColor, before.accentColor);
  assert.equal(after.subtitleAiApiKey, before.subtitleAiApiKey);
  same(after.catalogsByProfile, before.catalogsByProfile);
  same(after.iptvByProfile, before.iptvByProfile);
  same(after.addonsByProfile, before.addonsByProfile);
  assert.equal(after.settings, undefined);
});

test('an account that cannot be read at all is never written to', async () => {
  const { runner, read, writes } = setup({ failRead: () => true });
  await assert.rejects(runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot, choices, baseSettings: defaults
  }));
  assert.equal(writes(), 0);
  same(read(), account());
});

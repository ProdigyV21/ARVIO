const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
// Values produced inside the sandbox carry that realm's prototypes.
const same = (actual, expected) => assert.deepEqual(JSON.parse(JSON.stringify(actual)), expected);

const snapshot = {
  email: 'demo@nuvio.example',
  profiles: [
    {
      profileId: 1, name: 'Main', avatarColorHex: '#E50914', avatarId: 'avatar_3', pinEnabled: false,
      addons: [{ url: 'https://a.example/manifest.json', name: 'A', enabled: true, sortOrder: 0 },
               { url: 'https://down.example/manifest.json', name: 'Down', enabled: true, sortOrder: 1 }],
      plugins: [{ url: 'https://p.example/repo.json', name: 'P', enabled: true, sortOrder: 0, repoType: 'github' }],
      collectionsJson: [{ id: 'studios', title: 'Studios', folders: [
        { id: 'pixar', title: 'Pixar', sources: [{ provider: 'tmdb', tmdbSourceType: 'COMPANY', tmdbId: 3, mediaType: 'MOVIE' }] }
      ] }],
      homeCatalogSettings: null
    },
    {
      profileId: 2, name: 'Kids', avatarColorHex: '#1DB954', avatarId: 'avatar_9', pinEnabled: true,
      addons: [{ url: 'https://a.example/manifest.json', name: 'A', enabled: true, sortOrder: 0 }],
      plugins: [], collectionsJson: null, homeCatalogSettings: null
    }
  ]
};

function setup({ existingProfiles = [], existingAddons = [] } = {}) {
  const calls = { addons: [], settings: [], profiles: [], installed: [] };
  const runner = load('lib/nuvioImportRunner.ts', {
    './addons': {
      installAddon: async (url) => {
        calls.installed.push(url);
        if (url.includes('down.example')) throw new Error('unreachable');
        return { id: url, name: `Addon ${calls.installed.length}`, manifestUrl: url, enabled: true };
      },
      normalizeAddons: (list) => list.filter(Boolean)
    },
    './auth': {},
    './catalogs': { defaultCatalogs: [{ id: 'trending', enabled: true }] },
    './cloud': {
      pullCloudPayload: async () => ({ addons: existingAddons, settings: { catalogs: [{ id: 'trending', enabled: true }] } }),
      saveCloudAddons: async (_auth, addons, profileId) => calls.addons.push({ profileId, count: addons.length }),
      saveCloudProfiles: async (_auth, profiles) => calls.profiles.push(profiles.map(p => p.name)),
      saveCloudSettings: async (_auth, settings, _addons, profileId) =>
        calls.settings.push({ profileId, catalogs: settings.catalogs.length })
    },
    './customCollections': load('lib/customCollections.ts'),
    './nuvioMigration': load('lib/nuvioMigration.ts'),
    './profiles': { profileColors: [1, 2, 3] },
    './types': {}
  });
  return { runner, calls };
}

const auth = { session: { userId: 'u1', email: 'me@arvio.example' } };
const baseSettings = { catalogs: [] };

test('creates the missing profiles and writes each one to the account', async () => {
  const { runner, calls } = setup({ existingProfiles: [] });
  const result = await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot,
    choices: [
      { nuvioProfileId: 1, target: { kind: 'existing', profileId: 'p1' } },
      { nuvioProfileId: 2, target: { kind: 'create' } }
    ],
    baseSettings
  });
  // Kids had no ARVIO profile, so it was created before anything was written.
  same(calls.profiles, [['Main', 'Kids']]);
  assert.equal(result.profiles.length, 2);
  assert.equal(result.summaries.length, 2);

  const main = result.summaries[0];
  assert.equal(main.addons, 1);          // one installed
  assert.equal(main.addonsFailed, 1);    // the unreachable one is reported, not fatal
  assert.equal(main.collections, 1);
  assert.equal(main.plugins, 1);         // counted so the UI can say they stay behind
  assert.equal(result.summaries[1].created, true);

  // Settings (catalogs) are written for both profiles, addons only where new ones resolved.
  same(calls.settings.map(entry => entry.profileId).sort(), ['p1', result.profiles[1].id].sort());
  assert.equal(calls.addons.length, 2);
});

test('skips profiles the user did not choose and never writes them', async () => {
  const { runner, calls } = setup();
  const result = await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot,
    choices: [{ nuvioProfileId: 1, target: { kind: 'skip' } }, { nuvioProfileId: 2, target: { kind: 'skip' } }],
    baseSettings
  });
  same(result.summaries, []);
  same(calls.settings, []);
  same(calls.profiles, []);
});

test('an addon already on the profile is not installed twice', async () => {
  const { runner, calls } = setup({ existingAddons: [{ id: 'x', name: 'A', manifestUrl: 'https://a.example/manifest.json' }] });
  await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot,
    choices: [{ nuvioProfileId: 1, target: { kind: 'existing', profileId: 'p1' } }],
    baseSettings
  });
  same(calls.installed, ['https://down.example/manifest.json']);
});

test('refuses to run without an ARVIO session', async () => {
  const { runner } = setup();
  await assert.rejects(
    runner.applyNuvioImport({ auth: { session: null }, profiles: [], snapshot, choices: [], baseSettings }),
    /Sign in to your ARVIO account/
  );
});

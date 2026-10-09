const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
// Values produced inside the sandbox carry that realm's prototypes.
const same = (actual, expected) => assert.deepEqual(JSON.parse(JSON.stringify(actual)), expected);

const snapshot = {
  email: 'demo@nuvio.example',
  warnings: [],
  profiles: [
    {
      profileId: 1, name: 'Main', avatarColorHex: '#E50914', avatarId: 'avatar_3', pinEnabled: false,
      addons: [{ url: 'https://a.example/manifest.json', name: 'A', enabled: true, sortOrder: 0 },
               { url: 'https://down.example/manifest.json', name: 'Down', enabled: true, sortOrder: 1 },
               { url: 'https://off.example/manifest.json', name: 'Off', enabled: false, sortOrder: 2 }],
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

function setup({ existingProfiles = [{ id: 'p1', name: 'Main' }, { id: 'p2', name: 'Kids' }], existingAddons = [], pull } = {}) {
  const calls = { addons: [], settings: [], profiles: [], installed: [], saved: [] };
  const runner = load('lib/nuvioImportRunner.ts', {
    './addons': {
      installAddon: async (url) => {
        calls.installed.push(url);
        if (url.includes('down.example')) throw new Error('unreachable');
        return { id: url, name: `Addon ${calls.installed.length}`, manifestUrl: url, enabled: true };
      },
      normalizeAddons: (list) => list.filter(Boolean),
      normalizeAddon: (addon) => ({ ...addon, isEnabled: addon.enabled !== false })
    },
    './auth': {},
    './catalogs': { defaultCatalogs: [{ id: 'trending', enabled: true }] },
    './cloud': {
      invalidateRawPayloadCache: () => {},
      pullCloudProfiles: async () => ({ profiles: existingProfiles, activeProfileId: existingProfiles[0]?.id ?? null }),
      pullCloudPayload: pull ?? (async () => ({ addons: existingAddons, settings: { catalogs: [{ id: 'trending', enabled: true }] } })),
      saveCloudAddons: async (_auth, addons, profileId) => calls.addons.push({ profileId, count: addons.length, addons }),
      addCloudProfiles: async (_auth, additions) => {
        existingProfiles = [...existingProfiles, ...additions];
        calls.profiles.push(existingProfiles.map(p => p.name));
        return existingProfiles;
      },
      saveCloudSettings: async (_auth, settings, _addons, profileId, _profiles, baseline) =>
        calls.settings.push({ profileId, catalogs: settings.catalogs.length, settings, baseline, profiles: _profiles })
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
  const { runner, calls } = setup({ existingProfiles: [{ id: 'p1', name: 'Main' }] });
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
  assert.equal(main.addons, 2);          // two installed
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
  same(calls.installed, ['https://down.example/manifest.json', 'https://off.example/manifest.json']);
});

test('refuses to run without an ARVIO session', async () => {
  const { runner } = setup();
  await assert.rejects(
    runner.applyNuvioImport({ auth: { session: null }, profiles: [], snapshot, choices: [], baseSettings }),
    /Sign in to your ARVIO account/
  );
});

// ── Regression: the five findings on PR #774 ────────────────────────────────

test('a profile whose current state cannot be read is skipped, not overwritten', async () => {
  const { runner, calls } = setup({
    pull: async (_auth, profileId) => {
      if (profileId === 'p1') throw new Error('network down');
      return { addons: [], settings: { catalogs: [{ id: 'trending', enabled: true }] } };
    }
  });
  const result = await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }, { id: 'p2', name: 'Kids' }], snapshot,
    choices: [
      { nuvioProfileId: 1, target: { kind: 'existing', profileId: 'p1' } },
      { nuvioProfileId: 2, target: { kind: 'existing', profileId: 'p2' } }
    ],
    baseSettings
  });
  // Nothing at all is written for the profile that could not be read — not
  // settings, not addons — and the failure is reported instead of swallowed.
  assert.match(result.summaries[0].failed, /network down/);
  same(calls.settings.map(entry => entry.profileId), ['p2']);
  same(calls.addons.map(entry => entry.profileId), ['p2']);
  // The other profile still imports.
  assert.equal(result.summaries[1].failed, undefined);
});

test('the write is limited to catalogs and carries the account as its baseline', async () => {
  const cloudSettings = {
    catalogs: [{ id: 'trending', enabled: true }],
    accentColor: '#00d588', aiSubtitlesEnabled: true, aiApiKey: 'secret-key',
    iptvPlaylists: [{ id: 'a', m3uUrl: 'https://example.invalid/a' }]
  };
  const { runner, calls } = setup({ pull: async () => ({ addons: [], settings: cloudSettings }) });
  await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot,
    choices: [{ nuvioProfileId: 1, target: { kind: 'existing', profileId: 'p1' } }],
    // The defaults the page carries must never reach the account.
    baseSettings: { ...baseSettings, accentColor: '#ffffff', aiSubtitlesEnabled: false, aiApiKey: '', iptvPlaylists: [] }
  });
  const write = calls.settings[0];
  // Everything the account already had is handed back untouched…
  assert.equal(write.settings.accentColor, '#00d588');
  assert.equal(write.settings.aiApiKey, 'secret-key');
  assert.equal(write.settings.aiSubtitlesEnabled, true);
  same(write.settings.iptvPlaylists, cloudSettings.iptvPlaylists);
  // …and the baseline matches it field for field, so saveCloudSettings asserts
  // nothing but the catalogs this import actually changed.
  const { catalogs: _written, ...writtenRest } = write.settings;
  const { catalogs: _base, ...baselineRest } = write.baseline;
  same(writtenRest, JSON.parse(JSON.stringify(baselineRest)));
  same(write.baseline.catalogs, cloudSettings.catalogs);
  assert.ok(write.settings.catalogs.length > cloudSettings.catalogs.length);
  same(write.profiles, []);
  same(calls.profiles, []);
});

test('an addon switched off in Nuvio arrives switched off', async () => {
  const { runner, calls } = setup();
  const result = await runner.applyNuvioImport({
    auth, profiles: [{ id: 'p1', name: 'Main' }], snapshot,
    choices: [{ nuvioProfileId: 1, target: { kind: 'existing', profileId: 'p1' } }],
    baseSettings
  });
  const written = calls.addons[0].addons;
  const off = written.find(addon => addon.manifestUrl === 'https://off.example/manifest.json');
  assert.equal(off.enabled, false);
  assert.equal(off.isEnabled, false);
  assert.equal(written.find(addon => addon.manifestUrl === 'https://a.example/manifest.json').enabled, true);
  assert.equal(result.summaries[0].addonsDisabled, 1);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');

const code = ts.transpileModule(fs.readFileSync(require.resolve('../lib/nuvioMigration.ts'), 'utf8'), {
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 }
}).outputText;
const sandbox = { exports: {}, URL, console };
vm.runInNewContext(code, sandbox);
const {
  normalizeBackendUrl, parseDiscovery, discoverNuvioBackend, signInToNuvio, fetchNuvioSnapshot,
  buildSnapshot, mapAddonRows, hexToArgb, avatarIdToArvio, defaultChoices, newProfileFrom,
  summarizePlan, addonsToInstall, applyHomeCatalogSettings, profileIdsFromRows, reconcileChoices
} = sandbox.exports;

const jsonResponse = (body, ok = true, status = 200) => ({ ok, status, json: async () => body });
// Values built inside the VM sandbox carry that realm's prototypes, so compare
// them structurally rather than by reference.
const plain = (value) => JSON.parse(JSON.stringify(value));
const same = (actual, expected) => assert.deepEqual(plain(actual), expected);

test('accepts the server address in the forms a user types', () => {
  assert.equal(normalizeBackendUrl('nuvio.example.com'), 'https://nuvio.example.com');
  assert.equal(normalizeBackendUrl('https://nuvio.example.com/'), 'https://nuvio.example.com');
  assert.equal(normalizeBackendUrl('https://nuvio.example.com/.well-known/nuvio'), 'https://nuvio.example.com');
  assert.equal(normalizeBackendUrl('http://localhost:8000'), 'http://localhost:8000');
  // A local server speaks plain HTTP, so a scheme-less local address keeps it.
  assert.equal(normalizeBackendUrl('localhost:4599'), 'http://localhost:4599');
  assert.equal(normalizeBackendUrl('127.0.0.1:8000'), 'http://127.0.0.1:8000');
  // Plaintext to a remote host would leak the password.
  assert.throws(() => normalizeBackendUrl('http://nuvio.example.com'), /HTTPS/);
  assert.throws(() => normalizeBackendUrl('   '), /server address/);
});

test('reads the publishable key from the discovery document', () => {
  const discovery = parseDiscovery({
    backend_url: 'https://api.example.com', publishable_key: 'pk_test', self_hosted: true,
    capabilities: { email_password_auth: true }
  }, 'https://nuvio.example.com');
  assert.equal(discovery.backendUrl, 'https://api.example.com');
  assert.equal(discovery.publishableKey, 'pk_test');
  assert.equal(discovery.selfHosted, true);
  assert.equal(discovery.emailPasswordAuth, true);
  // A server that answers without a key cannot be signed in to.
  assert.throws(() => parseDiscovery({ backend_url: 'https://api.example.com' }, 'https://x.example'), /publishable key/);
  // Older deployments omit capabilities entirely.
  assert.equal(parseDiscovery({ publishable_key: 'pk' }, 'https://x.example').emailPasswordAuth, true);
});

test('discovery falls back to the typed address when the document omits backend_url', async () => {
  const discovery = await discoverNuvioBackend('nuvio.example.com', async (url) => {
    assert.equal(url, 'https://nuvio.example.com/.well-known/nuvio');
    return jsonResponse({ publishable_key: 'pk_test' });
  });
  assert.equal(discovery.backendUrl, 'https://nuvio.example.com');
});

test('sign-in surfaces the server message instead of a generic failure', async () => {
  const discovery = { backendUrl: 'https://api.example.com', publishableKey: 'pk', emailPasswordAuth: true };
  const session = await signInToNuvio(discovery, ' user@example.com ', 'secret', async (url, init) => {
    assert.equal(url, 'https://api.example.com/auth/v1/token?grant_type=password');
    assert.equal(init.headers.apikey, 'pk');
    same(JSON.parse(init.body), { email: 'user@example.com', password: 'secret' });
    return jsonResponse({ access_token: 'token', user: { id: 'u1', email: 'user@example.com' } });
  });
  assert.equal(session.accessToken, 'token');
  assert.equal(session.email, 'user@example.com');

  await assert.rejects(
    signInToNuvio(discovery, 'user@example.com', 'wrong', async () =>
      jsonResponse({ error_description: 'Invalid login credentials' }, false, 400)),
    /Invalid login credentials/
  );
  await assert.rejects(
    signInToNuvio({ ...discovery, emailPasswordAuth: false }, 'a@b.c', 'x', async () => jsonResponse({})),
    /email sign-in disabled/
  );
});

test('groups addons, plugins and collections by their Nuvio profile', () => {
  const snapshot = buildSnapshot(
    'user@example.com',
    [
      { profile_id: 2, name: 'Kids', avatar_color_hex: '#1DB954', avatar_id: 'avatar_7', pin_enabled: true },
      { profile_id: 1, name: 'Main', avatar_color_hex: '#E50914' }
    ],
    [
      { profile_id: 1, url: 'https://a.example/manifest.json', name: 'A', sort_order: 2 },
      { profile_id: 1, url: 'https://b.example/manifest.json', name: 'B', sort_order: 1, enabled: false },
      { profile_id: 2, url: 'https://kids.example/manifest.json' },
      { profile_id: 1, url: 'not-a-url' }
    ],
    [{ profile_id: 1, url: 'https://plug.example/repo.json', repo_type: 'github' }],
    [{ profile_id: 1, collections_json: [{ title: 'Studios', folders: [] }] }],
    [{ profile_id: 1, settings_json: { order: ['b', 'a'] } }]
  );
  same(snapshot.profiles.map(p => p.profileId), [1, 2]);
  const main = snapshot.profiles[0];
  // sort_order decides the order, and a malformed URL is dropped.
  same(main.addons.map(a => a.name), ['B', 'A']);
  assert.equal(main.addons[0].enabled, false);
  assert.equal(main.plugins[0].repoType, 'github');
  assert.equal(main.collectionsJson.length, 1);
  same(main.homeCatalogSettings, { order: ['b', 'a'] });
  assert.equal(snapshot.profiles[1].addons.length, 1);
  assert.equal(snapshot.profiles[1].pinEnabled, true);
});

test('a missing table does not fail the whole import', async () => {
  const discovery = { backendUrl: 'https://api.example.com', publishableKey: 'pk' };
  const snapshot = await fetchNuvioSnapshot(discovery, { accessToken: 't', userId: 'u', email: 'u@e.c' }, async (url) => {
    if (url.includes('/profiles')) return jsonResponse([{ profile_id: 1, name: 'Main' }]);
    if (url.includes('/plugins')) return jsonResponse({ message: 'relation does not exist' }, false, 404);
    return jsonResponse([]);
  });
  assert.equal(snapshot.profiles.length, 1);
  same(snapshot.profiles[0].plugins, []);

  // A deployment that exposes nothing directly still yields the default profile,
  // so the sync RPCs below have something to fill.
  const bare = await fetchNuvioSnapshot(discovery, { accessToken: 't', userId: 'u', email: 'u@e.c' }, async () => jsonResponse([]));
  assert.equal(bare.profiles.length, 1);
  assert.equal(bare.profiles[0].profileId, 1);
});

test('falls back to the sync RPCs when a direct table read is empty', async () => {
  const discovery = { backendUrl: 'https://api.example.com', publishableKey: 'pk' };
  const called = [];
  const snapshot = await fetchNuvioSnapshot(discovery, { accessToken: 't', userId: 'u', email: 'u@e.c' }, async (url, init) => {
    if (url.includes('/rpc/')) {
      called.push({ url, profileId: JSON.parse(init.body).p_profile_id });
      return url.includes('sync_pull_collections')
        ? jsonResponse([{ collections_json: [{ title: 'Studios', folders: [] }] }])
        : jsonResponse([{ settings_json: { order: ['a'] } }]);
    }
    if (url.includes('/profiles')) return jsonResponse([{ profile_id: 1, name: 'Main' }]);
    return jsonResponse([]);
  });
  // Row-level security can hide the tables while the RPCs still answer.
  assert.equal(snapshot.profiles[0].collectionsJson.length, 1);
  same(snapshot.profiles[0].homeCatalogSettings, { order: ['a'] });
  assert.deepEqual(called.map(entry => entry.profileId), [1, 1]);
});

test('converts Nuvio profile looks into ARVIO ones', () => {
  assert.equal(hexToArgb('#E50914', 0), 0xffe50914);
  assert.equal(hexToArgb('E50914', 0), 0xffe50914);
  assert.equal(hexToArgb('nonsense', 0xff123456), 0xff123456);
  assert.equal(avatarIdToArvio('avatar_12'), 12);
  assert.equal(avatarIdToArvio('85'), 0);
  assert.equal(avatarIdToArvio(null), 0);

  const profile = newProfileFrom(
    { name: 'Kids', avatarColorHex: '#1DB954', avatarId: 'avatar_7', pinEnabled: true },
    'new-id', 0xff000000
  );
  assert.equal(profile.avatarColor, 0xff1db954);
  assert.equal(profile.avatarId, 7);
  // A Nuvio PIN hash is not portable, so the copy must not arrive locked.
  assert.equal(profile.isLocked, false);
  assert.equal(profile.pin, null);
});

test('matches profiles by name and creates the missing ones', () => {
  const snapshot = {
    email: 'u@e.c',
    profiles: [
      { profileId: 1, name: 'Main', addons: [], plugins: [], collectionsJson: null, homeCatalogSettings: null },
      { profileId: 2, name: 'Kids', addons: [{ url: 'https://a.example/manifest.json' }], plugins: [], collectionsJson: [{}, {}], homeCatalogSettings: { order: [] } }
    ]
  };
  const choices = defaultChoices(snapshot, [{ id: 'p1', name: ' main ' }]);
  same(choices[0].target, { kind: 'existing', profileId: 'p1' });
  same(choices[1].target, { kind: 'create' });

  const summary = summarizePlan(snapshot, choices);
  assert.equal(summary.length, 2);
  assert.equal(summary[1].created, true);
  assert.equal(summary[1].addons, 1);
  assert.equal(summary[1].collections, 2);
  assert.equal(summary[1].catalogSettings, true);

  // An explicit skip, or a profile the user never chose a target for, is left out.
  assert.equal(summarizePlan(snapshot, [{ nuvioProfileId: 1, target: { kind: 'skip' } }]).length, 0);
  assert.equal(summarizePlan(snapshot, [{ nuvioProfileId: 2, target: { kind: 'create' } }]).length, 1);
});

test('only adds addons that are not installed yet, keeping their Nuvio state', () => {
  const added = addonsToInstall(
    ['https://a.example/manifest.json'],
    [{ url: 'https://A.example/manifest.json', enabled: true },
     { url: 'https://b.example/manifest.json', enabled: false },
     { url: 'https://b.example/manifest.json', enabled: true }]
  );
  // Whole entries, not URLs: the import needs the enabled flag to carry over.
  same(added, [{ url: 'https://b.example/manifest.json', enabled: false }]);
});

test('applies Nuvio row order and hidden rows onto ARVIO catalogs', () => {
  const catalogs = [
    { id: 'a', enabled: true }, { id: 'b', enabled: true }, { id: 'c', enabled: true }
  ];
  const result = applyHomeCatalogSettings(catalogs, { order: ['c', 'a'], hidden: ['b'] });
  same(result.map(c => c.id), ['c', 'a', 'b']);
  assert.equal(result.find(c => c.id === 'b').enabled, false);
  // Nothing recognisable means the user's existing order is left alone.
  same(applyHomeCatalogSettings(catalogs, { unrelated: true }), catalogs);
});

// ── Regression: a failed read is not an empty account (PR #774 review, 4) ──

test('a profile table that fails to load is reported, not treated as empty', async () => {
  const discovery = { backendUrl: 'https://api.example.com', publishableKey: 'pk' };
  const snapshot = await fetchNuvioSnapshot(discovery, { accessToken: 't', userId: 'u', email: 'u@e.c' }, async (url) => {
    if (url.includes('/profiles')) return jsonResponse({ message: 'permission denied' }, false, 403);
    if (url.includes('/addons')) return jsonResponse([
      { profile_id: 1, url: 'https://a.example/manifest.json' },
      { profile_id: 3, url: 'https://c.example/manifest.json' }
    ]);
    return jsonResponse([]);
  });
  // Profiles 1 and 3 both have content, so both must be offered — building only
  // profile 1 used to drop everything that belonged to the others.
  same(snapshot.profiles.map(p => p.profileId), [1, 3]);
  assert.match(snapshot.warnings.join(' '), /profile list could not be read/);

  // A plugins table that errors is called out rather than passing for "no plugins".
  const partial = await fetchNuvioSnapshot(discovery, { accessToken: 't', userId: 'u', email: 'u@e.c' }, async (url) => {
    if (url.includes('/profiles')) return jsonResponse([{ profile_id: 1, name: 'Main' }]);
    if (url.includes('/plugins')) return jsonResponse({ message: 'nope' }, false, 500);
    return jsonResponse([]);
  });
  assert.match(partial.warnings.join(' '), /plugins/);
  // A table that answered with nothing is simply empty and says nothing.
  assert.equal(partial.warnings.some(entry => entry.includes('addons')), false);
});

test('recovers every profile the sync RPCs answer for when the table is hidden', async () => {
  const discovery = { backendUrl: 'https://api.example.com', publishableKey: 'pk' };
  const snapshot = await fetchNuvioSnapshot(discovery, { accessToken: 't', userId: 'u', email: 'u@e.c' }, async (url, init) => {
    if (url.includes('sync_pull_collections')) {
      const profileId = JSON.parse(init.body).p_profile_id;
      // Only profiles 2 and 4 exist on this account.
      return profileId === 2 || profileId === 4
        ? jsonResponse([{ collections_json: [{ title: `Set ${profileId}`, folders: [] }] }])
        : jsonResponse([]);
    }
    return jsonResponse([]);
  });
  same(snapshot.profiles.map(p => p.profileId), [2, 4]);
  assert.equal(snapshot.profiles[0].collectionsJson.length, 1);
  assert.equal(snapshot.profiles[1].collectionsJson.length, 1);
});

test('profile ids are recovered from any table that carries them', () => {
  same(profileIdsFromRows([{ profile_id: 3 }], [{ profile_index: 1 }], [{ profile_id: 'nonsense' }, { profile_id: 3 }]), [1, 3]);
  same(profileIdsFromRows([]), []);
});

// ── Regression: choices recalculated after a late sign-in (PR #774 review, 3) ──

test('matches are recalculated once ARVIO profiles arrive, keeping manual picks', () => {
  const snapshot = {
    email: 'u@e.c', warnings: [],
    profiles: [
      { profileId: 1, name: 'Main', addons: [], plugins: [], collectionsJson: null, homeCatalogSettings: null },
      { profileId: 2, name: 'Kids', addons: [], plugins: [], collectionsJson: null, homeCatalogSettings: null }
    ]
  };
  // Nuvio is connected before the ARVIO sign-in, so nothing can be matched yet.
  const first = defaultChoices(snapshot, []);
  same(first.map(choice => choice.target.kind), ['create', 'create']);

  // Signing in afterwards must not leave both profiles set to "create".
  const profiles = [{ id: 'p1', name: 'Main' }, { id: 'p2', name: 'Kids' }];
  const after = reconcileChoices(snapshot, profiles, first);
  same(after.map(choice => choice.target), [{ kind: 'existing', profileId: 'p1' }, { kind: 'existing', profileId: 'p2' }]);

  // A target the user picked by hand survives the next recalculation, and the
  // profile they claimed is not handed to someone else by name matching.
  const edited = after.map(choice => choice.nuvioProfileId === 2 ? { ...choice, userSet: true, target: { kind: 'skip' } } : choice);
  const again = reconcileChoices(snapshot, profiles, edited);
  same(again[1].target, { kind: 'skip' });
  same(again[0].target, { kind: 'existing', profileId: 'p1' });

  const claimed = reconcileChoices(snapshot, profiles,
    [{ nuvioProfileId: 1, userSet: true, target: { kind: 'existing', profileId: 'p2' } }, { nuvioProfileId: 2, target: { kind: 'create' } }]);
  same(claimed[0].target, { kind: 'existing', profileId: 'p2' });
  // 'Kids' is taken by the manual choice above, so it cannot be matched again.
  same(claimed[1].target, { kind: 'create' });
});

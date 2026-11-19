const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

function fixture(owner = 'inviter@example.test', pull = async () => ({ profiles: [], avatarImages: {} })) {
  const saved = new Map([['arvio.web.profilesOwner', owner], ['arvio.web.profiles', [{ id: 'private-profile', name: 'Private' }]]]);
  const effects = [];
  const state = {};
  const authClient = { session: { userId: 'recipient', email: 'recipient@example.test' } };
  const store = new Proxy({
    auth: authClient.session, profiles: saved.get('arvio.web.profiles'), activeProfileId: 'private-profile',
    activeProfileIdRef: { current: 'private-profile' }, refreshGenerationRef: { current: 1 },
    refreshKeyRef: { current: 'inviter:private-profile' }, lastSyncedSettingsRef: { current: 'old-settings' },
    settingsRef: { current: { private: true } }, cwSourceRef: { current: 'seed' },
    refreshData: async () => {},
  }, { get: (target, key) => key.startsWith('set') ? value => { state[key] = value; } : target[key] });
  const m = load('lib/store/profiles.ts', {
    react: { useEffect: fn => effects.push(fn), useCallback: fn => fn },
    '../wetrakr': {}, '../watchlistOutbox': {}, '../config': { config: {} },
    '../cloud': { pullCloudProfiles: pull },
    '../storage': { loadStored: (key, fallback) => saved.has(key) ? saved.get(key) : fallback, saveStored: (key, value) => saved.set(key, value) },
    '../addons': { saveLocalAddons: value => { state.localAddons = value; } },
    '../iptv': { savePlaylists: value => { state.playlists = value; } },
    './settings': { defaultSettings: { theme: 'dark', iptvPlaylists: [] }, settingsKey: 'settings' },
    '../profilePin': {}, '../profiles': {}, '../partnerLinks': {}, '../mdblist': {}, '../simkl': {},
    './clients': { authClient }, './listCache': {},
  });
  return { m, saved, effects, state, store, authClient };
}

test('auth change cannot relabel old profiles before the new-account cloud pull', async () => {
  const f = fixture();
  f.m.useProfileSync(f.store);
  f.effects[0]();
  assert.equal(f.saved.get('arvio.web.profilesOwner'), 'inviter@example.test');
  f.effects[1]();
  await new Promise(resolve => setImmediate(resolve));
  assert.notEqual(f.state.setProfiles[0].id, 'private-profile');
  assert.equal(f.state.setProfiles[0].name, 'Profile 1');
  assert.equal(f.saved.get('arvio.web.profilesOwner'), 'recipient@example.test');
  assert.equal(f.state.setSettings.private, undefined);
  assert.equal(f.state.localAddons.length, 0);
  assert.equal(f.state.playlists.length, 0);
  assert.equal(f.store.lastSyncedSettingsRef.current, null);
});

test('logout does not erase ownership, and subsequent signup starts clean', () => {
  const f = fixture();
  f.authClient.session = null;
  f.m.useProfileSync(f.store);
  f.effects[0]();
  assert.equal(f.saved.get('arvio.web.profilesOwner'), 'inviter@example.test');
  f.m.resetDifferentAccount(f.store, 'recipient@example.test');
  assert.equal(f.state.localAddons.length, 0);
  assert.notEqual(f.state.setProfiles[0].id, 'private-profile');
});

test('reopening the same account preserves local data', () => {
  const f = fixture('recipient@example.test');
  f.m.resetDifferentAccount(f.store, 'RECIPIENT@example.test');
  assert.equal(f.state.setProfiles, undefined);
  assert.equal(f.state.localAddons, undefined);
});

test('a pending cloud response cannot populate a subsequently signed-in account', async () => {
  let finish;
  const f = fixture('recipient@example.test', () => new Promise(resolve => { finish = resolve; }));
  f.m.useProfileSync(f.store);
  f.effects[1]();
  f.authClient.session = { userId: 'another-account', email: 'another@example.test' };
  finish({ profiles: [{ id: 'previous-private-profile' }], avatarImages: {} });
  await new Promise(resolve => setImmediate(resolve));
  assert.equal(f.state.setProfiles, undefined);
  assert.equal(f.state.setCloudProfilesHydrated, undefined);
});

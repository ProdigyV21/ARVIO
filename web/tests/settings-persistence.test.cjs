// Accent colours sync as Android's names (White, Red, …); the web keeps its own ids (arctic, red, …).
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load, storage } = require('./load.cjs');
const { sourceOf, storeSource } = require('./sources.cjs');

function harness(managedResources = null) {
  const management = load('lib/managedConfiguration.ts');
  const disk = storage();
  let remote = { accentColor: 'White', profileSettingsById: { p1: { contentLanguage: 'en-US' } } };
  let reject = false;
  let pause;
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '[]' },
    './iptv': load('lib/iptv.ts', { './http': {}, './storage': disk }), './mediaImages': {},
    './http': { jsonRequest: async (_url, options) => {
      if (options?.method === 'POST') {
        if (pause) { const wait = pause; pause = null; await wait; }
        if (reject) return { accepted: false };
        remote = JSON.parse(options.body).payload;
        return { accepted: true };
      }
      return { payload: structuredClone(remote) };
    } },
  });
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  const createOutbox = () => load('lib/settingsOutbox.ts', { './storage': disk, './cloud': cloud });
  const outbox = createOutbox();
  const initial = { language: 'en-US', accentColor: 'arctic', iptvPlaylists: [], homeServers: [], catalogs: [],
    hiddenCatalogIds: [], hiddenHomeServerCatalogIds: [], favoriteChannelIds: [], favoriteGroupIds: [], hiddenGroupIds: [], groupOrder: [],
    subtitleStyle: 'outline', subtitleSize: 100, subtitleOffset: 0, subtitleColorName: 'white', frameRateMatchingMode: 'off', qualityFilters: [] };
  const settingsRef = { current: initial };
  const personalSettingsRef = { current: initial };
  let state = initial;
  let sync;
  const source = storeSource();
  const start = source.indexOf('  const updateSettings = useCallback(');
  const end = source.indexOf('  return { updateSettings };', start);
  const code = ts.transpileModule(source.slice(start,end) + '\nresult = updateSettings;', { compilerOptions: { target: ts.ScriptTarget.ES2022 } }).outputText;
  const context = { result: null, useCallback: fn => fn, settingsRef, personalSettingsRef, activeProfileIdRef: {current:'p1'}, authClient: auth,
    personalManagedPatch: management.personalManagedPatch,
    managed: { recordPreferences: patch => management.assertManagedEdit(patch, managedResources?.delivery),
      applySettings: value => management.overlayManagedSettings(value, managedResources) },
    queueSettings: outbox.queueSettings, saveStored: disk.saveStored, settingsKey:'settings',
    setSettings: next => { state = typeof next === 'function' ? next(state) : next; },
    setSettingsSyncState: next => {sync = next;}, setToast: () => {} };
  vm.runInNewContext(code, context);
  return { auth, initial, outbox, createOutbox, cloud, disk, edit: context.result,
    remote: () => remote, state: () => state, sync: () => sync,
    reject: value => { reject = value; }, pause: promise => {pause = promise;} };
}

test('an edit before initial cloud hydration is durable immediately and survives reload', async () => {
  const h = harness();
  h.edit({language:'nl-NL'});
  assert.equal(h.outbox.hasPendingSettings(h.auth,'p1'),true);
  assert.equal(h.sync(),'pending');
  assert.equal(h.disk.loadStored('settings',{}).language,'nl-NL');
  // Reload: a fresh outbox instance must replay the persisted edit.
  await h.createOutbox().flushSettingsOutbox(h.auth);
  const restored = await h.cloud.pullCloudPayload(h.auth,'p1');
  assert.equal(restored.settings.language,'nl-NL');
  assert.equal(restored.settings.accentColor,'arctic');
});

test('managed preferences and credentials never enter settings storage or the cloud outbox', async () => {
  const managedResources = { delivery: { managed: true, active: true, name: 'Partner', permissions: { addConnections: true, editAppearance: false },
    config: { settings: { netflixHomeStyle: true }, catalogs: [], collectionUrls: [] } },
    catalogs: [], addons: [], homeServers: [{ id: 'partner-org-service', token: 'managed-secret' }], playlists: [] };
  const h = harness(managedResources);
  h.edit({ language: 'nl-NL', homeServers: managedResources.homeServers });
  assert.equal(JSON.stringify(h.disk.loadStored('settings', {})).includes('managed-secret'), false);
  assert.deepEqual(Array.from(h.state().homeServers), []);
  await h.outbox.flushSettingsOutbox(h.auth);
  assert.equal(JSON.stringify(h.remote()).includes('managed-secret'), false);
  h.edit({ netflixHomeStyle: false });
  assert.equal(h.state().netflixHomeStyle, undefined, 'A locked setting cannot become a personal override');
});

test('back-to-back edits preserve both changes before React effects run', async () => {
  const h = harness();
  h.edit({language:'nl-NL'});
  h.edit({accentColor:'red'});
  await h.outbox.flushSettingsOutbox(h.auth);
  const restored = await h.cloud.pullCloudPayload(h.auth,'p1');
  assert.equal(restored.settings.language,'nl-NL');
  assert.equal(restored.settings.accentColor,'red');
});

test('reverting a setting while its earlier save is in flight persists the latest choice', async () => {
  const h = harness();
  let release;
  h.pause(new Promise(resolve => {release = resolve;}));
  h.edit({accentColor:'red'});
  const saving = h.outbox.flushSettingsOutbox(h.auth);
  await new Promise(setImmediate);
  h.edit({accentColor:'arctic'});
  release();
  await saving;
  assert.equal(h.remote().accentColor,'White');
  assert.equal(h.outbox.hasPendingSettings(h.auth),false);
});

test('rejected saves remain queued across refresh and succeed on retry', async () => {
  const h = harness();
  h.reject(true);
  h.edit({language:'es-ES'});
  await assert.rejects(h.outbox.flushSettingsOutbox(h.auth),/did not accept/);
  assert.equal(h.outbox.hasPendingSettings(h.auth),true);
  h.reject(false);
  await h.createOutbox().flushSettingsOutbox(h.auth);
  assert.equal((await h.cloud.pullCloudPayload(h.auth,'p1')).settings.language,'es-ES');
});

test('explicit pre-hydration edits do not overwrite unrelated settings on another device', async () => {
  const h = harness();
  h.remote().accentColor='Green';
  h.edit({language:'fr-FR'});
  await h.outbox.flushSettingsOutbox(h.auth);
  assert.equal(h.remote().accentColor,'Green');
  assert.equal(h.remote().profileSettingsById.p1.contentLanguage,'fr-FR');
});

test('a late hydration response preserves the edit while adopting unrelated cloud settings', async () => {
  const h = harness();
  h.edit({language:'nl-NL'});
  const remote = {...h.initial, accentColor:'green'};
  const merged = h.outbox.settingsWithPendingEdits(h.auth,'p1',remote,h.initial,h.state());
  assert.equal(merged.language,'nl-NL');
  assert.equal(merged.accentColor,'green');
  // The autosave effect sees the merged state, not the old local defaults.
  h.outbox.queueSettings(h.auth,'p1',merged,remote);
  // A second remote edit after the pull must also survive the queued save.
  h.remote().accentColor='Blue';
  await h.outbox.flushSettingsOutbox(h.auth);
  assert.equal(h.remote().accentColor,'Blue');
  assert.equal(h.remote().profileSettingsById.p1.contentLanguage,'nl-NL');
});

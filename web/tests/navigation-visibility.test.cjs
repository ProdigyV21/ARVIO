const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const plain = (value) => JSON.parse(JSON.stringify(value));

function harness(remote = {}) {
  const disk = storage();
  const nav = load('lib/navigationVisibility.ts', { './storage': disk });
  const state = { remote };
  const cloud = load('lib/cloud.ts', {
    './navigationVisibility': nav, './storage': disk,
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '' },
    './iptv': load('lib/iptv.ts', { './http': {}, './storage': disk }), './mediaImages': {},
    './http': { jsonRequest: async (_url, options) => {
      if (options?.method === 'POST') { state.remote = JSON.parse(options.body).payload; return { accepted: true }; }
      return { payload: structuredClone(state.remote) };
    } },
  });
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  const defaults = load('lib/store/settings.ts', { '../catalogs': { defaultCatalogs: [] } }).defaultSettings;
  return { nav, cloud, auth, defaults, remote: () => state.remote };
}

test('defaults show every tab; malformed data cannot hide Home or Settings', () => {
  const { nav } = harness();
  for (const hidden of [undefined, null, 'tv', {}, ['home', 'settings', 'future', 42]]) {
    assert.deepEqual(plain(nav.normalizedHiddenNavigationSections(hidden)), []);
    for (const section of ['home', 'settings', 'search', 'watchlist', 'tv']) assert.equal(nav.navigationSectionVisible(section, hidden), true);
  }
  assert.deepEqual(plain(nav.normalizedHiddenNavigationSections(['tv', 'search', 'tv', 'home'])), ['search', 'tv']);
});

test('every combination preserves essentials and the Kids Live TV restriction', () => {
  const { nav } = harness();
  for (let mask = 0; mask < 8; mask++) for (const kids of [false, true]) {
    const hidden = ['search', 'watchlist', 'tv'].filter((_, index) => mask & (1 << index));
    assert.equal(nav.navigationSectionVisible('home', hidden, kids), true);
    assert.equal(nav.navigationSectionVisible('settings', hidden, kids), true);
    for (const section of ['search', 'watchlist', 'tv']) {
      assert.equal(nav.navigationSectionVisible(section, hidden, kids), !hidden.includes(section) && !(kids && section === 'tv'));
    }
  }
  assert.deepEqual(plain(nav.withNavigationSectionVisible(['tv'], 'search', false)), ['search', 'tv']);
  assert.deepEqual(plain(nav.withNavigationSectionVisible(['tv'], 'tv', true)), []);
});

test('local navigation choices are isolated by profile and account', () => {
  const { nav } = harness();
  nav.saveNavigationVisibility('one', 'first', ['tv']);
  nav.saveNavigationVisibility('one', 'second', ['search']);
  assert.deepEqual(plain(nav.loadNavigationVisibility('one', 'first')), ['tv']);
  assert.deepEqual(plain(nav.loadNavigationVisibility('one', 'second')), ['search']);
  assert.equal(nav.loadNavigationVisibility('two', 'first'), undefined);
  nav.saveNavigationVisibility('one', 'first', []);
  assert.deepEqual(plain(nav.loadNavigationVisibility('one', 'first')), []);
});

test('web saves an Android-readable profile field and another device restores it', async () => {
  const h = harness({ profileSettingsById: { first: {}, second: { hiddenNavigationSections: ['watchlist'] } } });
  await h.cloud.saveCloudSettings(h.auth, { ...h.defaults, hiddenNavigationSections: ['tv', 'search', 'settings'] }, [], 'first', [], h.defaults);
  assert.deepEqual(h.remote().profileSettingsById.first.hiddenNavigationSections, ['search', 'tv']);
  assert.deepEqual(h.remote().profileSettingsById.second.hiddenNavigationSections, ['watchlist']);
  assert.ok(h.remote().fieldUpdatedAt['p:first:hiddenNavigationSections'] > 0);
  const peer = harness(h.remote());
  assert.deepEqual(plain((await peer.cloud.pullCloudPayload(peer.auth, 'first')).settings.hiddenNavigationSections), ['search', 'tv']);
  assert.deepEqual(plain((await peer.cloud.pullCloudPayload(peer.auth, 'second')).settings.hiddenNavigationSections), ['watchlist']);
});

test('Android choices restore on web and explicit empty arrays sync the unhide action', async () => {
  const h = harness({ profileSettingsById: { first: { hiddenNavigationSections: ['tv'] } } });
  const settings = (await h.cloud.pullCloudPayload(h.auth, 'first')).settings;
  assert.deepEqual(plain(settings.hiddenNavigationSections), ['tv']);
  const base = { ...h.defaults, ...settings };
  await h.cloud.saveCloudSettings(h.auth, { ...base, hiddenNavigationSections: [] }, [], 'first', [], base);
  assert.deepEqual(h.remote().profileSettingsById.first.hiddenNavigationSections, []);
  assert.deepEqual(plain((await h.cloud.pullCloudPayload(h.auth, 'first')).settings.hiddenNavigationSections), []);
});

test('unrelated saves preserve the field; legacy snapshots use only this profiles own cache', async () => {
  const h = harness({ settings: { hiddenNavigationSections: ['search'] }, profileSettingsById: { first: { hiddenNavigationSections: ['tv'] }, second: {} } });
  await h.cloud.saveCloudSettings(h.auth, { ...h.defaults, showBudget: false }, [], 'first', [], h.defaults);
  assert.deepEqual(h.remote().profileSettingsById.first.hiddenNavigationSections, ['tv']);
  assert.equal((await h.cloud.pullCloudPayload(h.auth, 'second')).settings.hiddenNavigationSections, undefined);
  h.nav.saveNavigationVisibility('account', 'second', ['watchlist']);
  assert.deepEqual(plain((await h.cloud.pullCloudPayload(h.auth, 'second')).settings.hiddenNavigationSections), ['watchlist']);
  assert.equal((await h.cloud.pullCloudPayload(h.auth, 'third')).settings.hiddenNavigationSections, undefined);
});

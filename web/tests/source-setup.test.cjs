const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const setup = load('lib/sourceSetup.ts');

test('VOD-only IPTV packages count as on-demand sources, not live TV playlists', () => {
  const playlist = { enabled: true, m3uUrl: 'https://provider.invalid/get.php?username=qa&password=fixture', importLiveTv: false, importVod: true, importSeries: false };
  const state = setup.sourceSetupState([], { homeServers: [], iptvPlaylists: [playlist] });
  assert.equal(state.liveTvPlaylists, 0);
  assert.equal(state.hasOnDemand, true);
  assert.equal(state.hasAny, true);
  const disabled = setup.sourceSetupState([], { homeServers: [], iptvPlaylists: [{ ...playlist, importVod: false }] });
  assert.equal(disabled.hasAny, false);
});
const empty = { homeServers: [], iptvPlaylists: [] };

test('catalog, metadata, subtitle and disabled addons do not count as playback setup', () => {
  const state = setup.sourceSetupState([
    { resources: ['catalog', 'meta'] },
    { resources: [{ name: 'subtitles' }] },
    { enabled: false, resources: ['stream'] }
  ], empty);
  assert.equal(state.hasAny, false);
  assert.equal(state.hasOnDemand, false);
  assert.equal(state.catalogOnly, true);
  assert.equal(state.streamAddons, 0);
});

test('both manifest stream resource formats count as configured without treating them as proven playable', () => {
  const state = setup.sourceSetupState([{ resources: ['stream'] }, { resources: [{ name: 'stream', types: ['movie'] }] }], empty);
  assert.equal(state.streamAddons, 2);
  assert.equal(state.hasOnDemand, true);
  assert.equal(state.catalogOnly, false);
});

test('enabled configured home servers and TV playlists are distinct playback routes', () => {
  const live = setup.sourceSetupState([], { ...empty, iptvPlaylists: [{ enabled: true, m3uUrl: 'https://example.test/playlist' }] });
  assert.equal(live.hasAny, true);
  assert.equal(live.hasOnDemand, false);
  const server = setup.sourceSetupState([], { ...empty, homeServers: [{ enabled: true, url: 'https://example.test' }] });
  assert.equal(server.hasOnDemand, true);
  const disabled = setup.sourceSetupState([], { homeServers: [{ enabled: false, url: 'https://example.test' }, { enabled: true, url: ' ' }], iptvPlaylists: [{ enabled: false, m3uUrl: 'https://example.test' }, { enabled: true, m3uUrl: '' }] });
  assert.equal(disabled.hasAny, false);
});

test('settings destination survives repeated render reads and is cleared after mount', () => {
  setup.requestSourceSettings('homeserver');
  assert.equal(setup.requestedSourceSettings(), 'homeserver');
  assert.equal(setup.requestedSourceSettings(), 'homeserver');
  setup.clearSourceSettingsRequest();
  assert.equal(setup.requestedSourceSettings(), null);
  setup.requestSourceSettings('tv');
  assert.equal(setup.requestedSourceSettings(), 'tv');
  setup.clearSourceSettingsRequest();
});

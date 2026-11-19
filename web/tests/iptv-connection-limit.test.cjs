// IPTV connection limits (#849): XC_VM / Xtream panels count every open
// stream request as a connection and evict the oldest viewer over the limit.
// Rules in lib/iptvConnections.ts, the panel request in lib/iptv.ts, the probe
// skip in the player (components/player/overlay/VideoPlayer.tsx).
const test = require('node:test');
const assert = require('node:assert/strict');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load, storage } = require('./load.cjs');
const { sourceOf } = require('./sources.cjs');

function connections({ usage = async () => null, timers } = {}) {
  const calls = [];
  const api = load('lib/iptvConnections.ts', { './iptv': {
    playlistProxyHeaders: () => ({ 'User-Agent': 'Default IPTV player' }),
    fetchXtreamAccountUsage: (info, headers) => { calls.push({ info, headers }); return usage(info, headers); }
  } }, timers ?? {});
  return { api, calls };
}

test('the account behind a stream: Xtream live, VOD and catch-up paths; nothing for M3U, Stalker or add-ons', () => {
  const { api } = connections();
  const origin = 'http://panel.example:8080';
  const live = (url, xtreamOrigin = origin) => api.iptvAccountOf({ url }, { liveTv: true, xtreamOrigin });
  assert.deepEqual({ ...live('http://panel.example:8080/live/al%40ice/p%2Fw/12.ts') }, { baseUrl: origin, username: 'al@ice', password: 'p/w' });
  assert.deepEqual({ ...live('http://panel.example:8080/alice/pw/12') }, { baseUrl: origin, username: 'alice', password: 'pw' });
  assert.equal(live('http://cdn.example/live/alice/pw/12.ts'), null, 'not the playlist\'s server');
  assert.equal(live('http://panel.example:8080/live/alice/pw/12.ts', null), null, 'an M3U or Stalker channel');
  assert.equal(live('http://panel.example:8080/play/live.php?stream=9&extension=ts'), null);
  const vod = { addonId: 'iptv_xtream_vod', url: 'https://relay.example/media?url=x', originalUrl: 'http://panel.example:8080/sub/movie/alice/pw/7.mkv' };
  assert.deepEqual({ ...api.iptvAccountOf(vod, { liveTv: false }) }, { baseUrl: 'http://panel.example:8080/sub', username: 'alice', password: 'pw' });
  const catchup = { addonName: 'Catch-up', url: 'http://panel.example:8080/timeshift/alice/pw/60/2026-11-02:20-00/12.m3u8' };
  assert.equal(api.iptvAccountOf(catchup, { liveTv: false }).username, 'alice');
  assert.equal(api.iptvAccountOf({ addonId: 'torrentio', url: 'http://panel.example:8080/movie/alice/pw/7.mkv' }, { liveTv: false }), null);
  assert.equal(api.isIptvSource({ addonId: 'iptv_xtream_vod' }), true);
  assert.equal(api.isIptvSource({ addonName: 'Catch-up' }), true);
  assert.equal(api.isIptvSource({ addonId: 'com.example.addon', addonName: 'Addon' }), false);
});

test('only a clear "every connection in use" answer stops a tune', () => {
  const { api } = connections();
  assert.equal(api.accountVerdict({ active: 3, max: 3 }), 'full');
  assert.equal(api.accountVerdict({ active: 4, max: 3 }), 'full');
  assert.equal(api.accountVerdict({ active: 2, max: 3 }), 'free');
  assert.equal(api.accountVerdict({ active: 5, max: 0 }), 'unknown', 'unlimited');
  assert.equal(api.accountVerdict({ active: NaN, max: 3 }), 'unknown');
  assert.equal(api.accountVerdict(null), 'unknown');
});

test('the check waits about 2.5 s for one answer; silence or an error lets the tune proceed', async () => {
  const waits = [];
  const timers = { setTimeout: (fn, ms) => { waits.push(ms); Promise.resolve().then(fn); return 1; }, clearTimeout: () => {} };
  const silent = connections({ usage: () => new Promise(() => {}), timers });
  assert.equal(await silent.api.checkIptvAccount({ baseUrl: 'http://p', username: 'u', password: 'p' }), 'unknown');
  assert.deepEqual(waits, [2500]);
  const failing = connections({ usage: async () => { throw new Error('IPTV provider requests are temporarily paused.'); } });
  assert.equal(await failing.api.checkIptvAccount({ baseUrl: 'http://p', username: 'u', password: 'p' }), 'unknown');
  const full = connections({ usage: async () => ({ active: 3, max: 3 }) });
  assert.equal(await full.api.checkIptvAccount({ baseUrl: 'http://p', username: 'u', password: 'p' }), 'full');
  assert.equal(full.calls.length, 1, 'one request per tune, no polling');
  assert.equal(full.calls[0].headers['User-Agent'], 'Default IPTV player', 'the playlist identity when the stream has none');
  await full.api.checkIptvAccount({ baseUrl: 'http://p', username: 'u', password: 'p' }, { 'User-Agent': 'Configured' });
  assert.equal(full.calls[1].headers['User-Agent'], 'Configured');
});

test('a channel change or reconnect within a minute of this device\'s own stream on the account is not checked', () => {
  const { api } = connections();
  const alice = { baseUrl: 'http://panel.example:8080', username: 'alice', password: 'pw' };
  const bob = { baseUrl: 'http://panel.example:8080', username: 'bob', password: 'pw' };
  assert.equal(api.shouldCheckIptvAccount(alice, 1_000), true);
  api.noteIptvAccountInUse(alice, 1_000);
  assert.equal(api.shouldCheckIptvAccount({ ...alice, baseUrl: 'http://PANEL.example:8080/sub' }, 60_999), false, 'the same line');
  assert.equal(api.shouldCheckIptvAccount(bob, 2_000), true, 'another account on the same panel');
  assert.equal(api.shouldCheckIptvAccount(alice, 61_000), true);
});

function panelFixture(respond) {
  let now = 1_000;
  const hits = [];
  class TestDate extends Date { static now() { return now; } }
  const api = load('lib/iptv.ts', {
    './storage': storage(),
    './http': {
      proxiedUrl: (url) => `https://relay.invalid/?url=${encodeURIComponent(url)}`,
      textRequest: async (url) => { hits.push(url); return respond(url); }
    }
  }, { Date: TestDate, setTimeout: (fn, ms) => { now += ms; Promise.resolve().then(fn); return 1; } });
  return { api, hits, advance: (ms) => { now += ms; } };
}
const info = { baseUrl: 'https://panel.invalid', username: 'alice', password: 'pw' };

test('user_info is read fresh each time (never from the 6-hour API cache), shared while in flight, and behind the request guard', async () => {
  let active = 2;
  const f = panelFixture(async () => JSON.stringify({ user_info: { auth: 1, active_cons: String(active), max_connections: '3' } }));
  const [first, second] = await Promise.all([f.api.fetchXtreamAccountUsage(info, {}), f.api.fetchXtreamAccountUsage(info, {})]);
  assert.deepEqual({ ...first }, { active: 2, max: 3 });
  assert.deepEqual({ ...second }, { active: 2, max: 3 });
  assert.equal(f.hits.length, 1);
  const url = new URL(f.hits[0]);
  assert.equal(url.pathname, '/player_api.php');
  assert.equal(url.searchParams.get('action'), null, 'user_info, which opens no stream');
  active = 3;
  f.advance(1_000);
  assert.deepEqual({ ...await f.api.fetchXtreamAccountUsage(info, {}) }, { active: 3, max: 3 });
  assert.equal(f.hits.length, 2);
  const unknown = panelFixture(async () => JSON.stringify({ user_info: { auth: 0 } }));
  assert.equal(await unknown.api.fetchXtreamAccountUsage(info, {}), null);
});

test('a panel that limits API requests pauses the check like every other provider request', async () => {
  const f = panelFixture(async () => { throw Object.assign(new Error('busy'), { status: 429 }); });
  await assert.rejects(f.api.fetchXtreamAccountUsage(info, {}));
  await assert.rejects(f.api.fetchXtreamAccountUsage(info, {}), /temporarily paused/);
  assert.equal(f.hits.length, 1, 'no proxy retry and no second request while paused');
});

test('the Audio panel never probes an IPTV file while it plays (each read is another connection on the line)', async () => {
  const filename = path.resolve(__dirname, '..', 'components/player/PlayerOverlay.tsx');
  const source = ts.createSourceFile(filename, sourceOf('components/player/PlayerOverlay.tsx'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let match;
  const visit = (node) => {
    if (ts.isVariableDeclaration(node) && node.name.getText(source) === 'probeAudioTracks') match = node.initializer.arguments[0];
    ts.forEachChild(node, visit);
  };
  visit(source);
  const code = ts.transpileModule(`module.exports = (${match.getText(source)});`, { compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS } }).outputText;
  const { api } = connections();
  for (const [stream, expected] of [
    [{ url: 'http://panel.example/movie/u/p/1.mkv', addonId: 'iptv_xtream_vod', addonName: 'IPTV VOD' }, 0],
    [{ url: 'http://panel.example/timeshift/u/p/60/2026-11-02:20-00/1.mkv', addonName: 'Catch-up' }, 0],
    [{ url: 'https://addon.example/file.mkv', addonId: 'addon' }, 1]
  ]) {
    let probes = 0;
    const module = { exports: {} };
    vm.runInNewContext(code, { module, AbortController, console,
      liveTv: false, currentStreamRef: { current: stream }, canTryRemux: () => true, isIptvSource: api.isIptvSource,
      setAudioProbeState: () => {}, audioProbeAbort: { current: null }, settings: {}, setRemuxTracks: () => {},
      cachedDebridDirectUrl: () => null, parseDebridStream: () => null,
      require: () => ({ probeAndPrepareRemux: async () => { probes++; return null; } }) }, { filename });
    module.exports();
    await new Promise(setImmediate);
    assert.equal(probes, expected, stream.url);
  }
});

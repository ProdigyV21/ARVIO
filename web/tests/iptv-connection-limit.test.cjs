// IPTV connection limits (#849): XC_VM / Xtream panels count every open
// stream request as a connection and evict the oldest viewer over the limit.
// Rules in lib/iptvConnections.ts, the probe skip in the player
// (components/player/overlay/VideoPlayer.tsx).
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

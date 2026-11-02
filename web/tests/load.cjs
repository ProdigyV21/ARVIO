const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const crypto = require('node:crypto');
exports.load = (relative, mocks = {}, globals = {}) => {
  const filename = path.resolve(__dirname, '..', relative);
  const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), { compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022, esModuleInterop: true } }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, {
    module, exports: module.exports, require: (name) => { if (name in mocks) return mocks[name]; if (name.startsWith('./') && name.endsWith('.json')) return JSON.parse(fs.readFileSync(path.resolve(path.dirname(filename), name), 'utf8')); if (name === './wetrakr') return exports.load('lib/wetrakr.ts', {}, globals); if (name === './config' || name === '@/lib/config') return exports.load('lib/config.ts', {}, globals); if (name === './selfhostRuntimeConfig' || name === '../selfhostRuntimeConfig') return exports.load('lib/selfhostRuntimeConfig.ts', {}, globals); if (name === './watchlistChanges') return exports.load('lib/watchlistChanges.ts'); if (name === './cardExtras') return exports.load('lib/cardExtras.ts'); if (name === './searchModel') return exports.load('lib/searchModel.ts'); if (name === './homeRows') return exports.load('lib/homeRows.ts'); if (name === './ordering') return exports.load('lib/ordering.ts'); if (name === './stalkerPortals') return exports.load('lib/stalkerPortals.ts'); if (name === './iptvGroups') return exports.load('lib/iptvGroups.ts'); if (name === './storage') return exports.load('lib/storage.ts'); if (name === './addonChanges') return exports.load('lib/addonChanges.ts'); if (name === './stalker') return exports.load('lib/stalker.ts', { './http': mocks['./http'] ?? {} }); if (name === './addonNative') return exports.load('lib/addonNative.ts', { './http': mocks['./http'] ?? {}, ...(mocks['./storage'] ? { './storage': mocks['./storage'] } : {}) }, globals); if (name === './iptvPlaylistIdentity') return exports.load('lib/iptvPlaylistIdentity.ts'); if (name === './iptvSession') return exports.load('lib/iptvSession.ts'); if (name === './liveTvPlayback' || name === '@/lib/liveTvPlayback') return exports.load('lib/liveTvPlayback.ts'); if (name === './languages' || name === '@/lib/languages') return exports.load('lib/languages.ts'); if (name === './progressOutbox') return exports.load('lib/progressOutbox.ts', { ...(mocks['./storage'] ? { './storage': mocks['./storage'] } : {}) }, globals); if (name === './nextEpisode') return exports.load('lib/nextEpisode.ts'); if (name === './streamTicket' || name === '@/lib/streamTicket') return exports.load('lib/streamTicket.ts', { './entitlement': { fetchStreamTicket: async () => ({}) } }); if (name === './premiumGate' || name === '@/lib/premiumGate') return exports.load('lib/premiumGate.ts'); if (name === './youtubeCollection') return exports.load('lib/youtubeCollection.ts', { ...(mocks['./storage'] ? { './storage': mocks['./storage'] } : {}) }, globals); if (name.startsWith('node:') || ['ipaddr.js', 'undici'].includes(name)) return require(name); throw new Error(`Unmocked dependency ${name}`); },
    URL, URLSearchParams, Headers, Request, Response, ReadableStream, TextEncoder, TextDecoder, Uint8Array, Buffer, Date, Map, Set, AbortController, AbortSignal, structuredClone, setTimeout, clearTimeout, atob, btoa, crypto: crypto.webcrypto, console, process: { env: {} }, ...globals
  }, { filename });
  return module.exports;
};
exports.storage = () => {
  const values = new Map();
  return { loadStored: (key, fallback) => values.has(key) ? structuredClone(values.get(key)) : fallback,
    saveStored: (key, value) => values.set(key, structuredClone(value)), removeStored: (key) => values.delete(key), values };
};

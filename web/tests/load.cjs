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
    module, exports: module.exports, require: (name) => { if (name in mocks) return mocks[name]; if (name === './iptvSession') return exports.load('lib/iptvSession.ts'); if (name.startsWith('node:') || ['ipaddr.js', 'undici'].includes(name)) return require(name); throw new Error(`Unmocked dependency ${name}`); },
    URL, URLSearchParams, Headers, Request, Response, ReadableStream, TextEncoder, TextDecoder, Uint8Array, Buffer, Date, Map, Set, AbortController, AbortSignal, structuredClone, setTimeout, clearTimeout, atob, btoa, crypto: crypto.webcrypto, console, process: { env: {} }, ...globals
  }, { filename });
  return module.exports;
};
exports.storage = () => {
  const values = new Map();
  return { loadStored: (key, fallback) => values.has(key) ? structuredClone(values.get(key)) : fallback,
    saveStored: (key, value) => values.set(key, structuredClone(value)), removeStored: (key) => values.delete(key), values };
};

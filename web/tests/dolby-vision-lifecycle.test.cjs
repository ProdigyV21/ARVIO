const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load } = require('./load.cjs');

function client() {
  const workers = [], timers = new Map();
  const filename = path.resolve(__dirname, '../lib/dolbyVisionProbeClient.ts');
  const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), {
    compilerOptions: { module: ts.ModuleKind.ESNext, target: ts.ScriptTarget.ES2022 }
  }).outputText.replace('export function', 'function').replace('import.meta.url', JSON.stringify('https://fixture.invalid/dolbyVisionProbeClient.js'));
  class Worker {
    constructor(url, options) { this.url = url; this.options = options; this.terminated = 0; workers.push(this); }
    postMessage(message) { this.message = message; }
    terminate() { this.terminated++; }
  }
  const context = { Worker, URL, Promise, setTimeout: (callback, ms) => {
    const id = {}; timers.set(id, { callback, ms }); return id;
  }, clearTimeout: (id) => timers.delete(id) };
  vm.createContext(context);
  vm.runInContext(code, context, { filename });
  return { workers, timers, run: context.runDolbyVisionProbe };
}

test('Probe worker is terminated and deadline cleared after a successful result; signal is not cloned', async () => {
  const h = client(), signal = new AbortController();
  const pending = h.run('https://fixture.invalid/file', { trackId: 13, headers: { Authorization: 'fixture' }, signal: signal.signal });
  assert.equal(h.workers.length, 1);
  const worker = h.workers[0];
  assert.equal(worker.options.type, 'module');
  assert.equal(worker.message.options.trackId, 13);
  assert.equal('signal' in worker.message.options, false);
  worker.onmessage({ data: { status: 'absent', trackId: 13 } });
  assert.equal((await pending).status, 'absent');
  signal.abort();
  worker.onmessage({ data: { status: 'unknown', reason: 'late' } });
  assert.equal(worker.terminated, 1);
  assert.equal(h.timers.size, 0);
});

test('External deadline terminates a nonresponsive parser worker, with a hard 10-second ceiling', async () => {
  const h = client();
  const pending = h.run('https://fixture.invalid/file', { timeoutMs: 60000 });
  const timer = [...h.timers.values()][0];
  assert.equal(timer.ms, 10000);
  timer.callback();
  assert.equal((await pending).reason, 'timeout');
  assert.equal(h.workers[0].terminated, 1);
  assert.equal(h.timers.size, 0);
});

test('Abort and parser failure terminate the isolated worker and resolve unknown', async () => {
  for (const mode of ['abort', 'error', 'messageerror']) {
    const h = client(), controller = new AbortController();
    const pending = h.run('https://fixture.invalid/file', { signal: controller.signal });
    if (mode === 'abort') controller.abort();
    else if (mode === 'error') h.workers[0].onerror({ preventDefault() {} });
    else h.workers[0].onmessageerror();
    assert.equal((await pending).status, 'unknown');
    assert.equal(h.workers[0].terminated, 1);
    assert.equal(h.timers.size, 0);
  }
  const h = client(), controller = new AbortController(); controller.abort();
  assert.equal((await h.run('https://fixture.invalid/file', { signal: controller.signal })).reason, 'aborted');
  assert.equal(h.workers.length, 0);
});

test('Child worker closes itself after completion even if the caller no longer exists', async () => {
  const messages = [];
  let closed = 0;
  const self = { postMessage: (message) => messages.push(message), close: () => closed++ };
  load('lib/dolbyVisionProbe.worker.ts', {
    './dolbyVisionMetadata': { probeDolbyVisionMetadata: async () => ({ status: 'unknown', reason: 'timeout' }) }
  }, { self });
  await self.onmessage({ data: { url: 'https://fixture.invalid/file', options: {} } });
  assert.equal(messages[0].reason, 'timeout');
  assert.equal(closed, 1);
});

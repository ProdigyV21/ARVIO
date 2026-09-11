const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const { pathToFileURL } = require('node:url');
const { getEventListeners } = require('node:events');
const ts = require('typescript');

const filename = path.resolve(__dirname, '../lib/remux.ts');
// The regular CJS test loader cannot evaluate import.meta; only replace the worker URL base.
const source = fs.readFileSync(filename, 'utf8').replaceAll('import.meta.url', JSON.stringify(pathToFileURL(filename).href));
const code = ts.transpileModule(source, {
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 }
}).outputText;
const flush = () => new Promise(setImmediate);

function harness(probeOverrides = {}) {
  const state = { trace: [], timeouts: new Set(), revoked: [], terminated: 0, errors: [] };

  class SourceBufferMock extends EventTarget {
    constructor() {
      super();
      this.ranges = [];
      this.holdAppend = false;
      this.holdRemove = false;
      this.updating = false;
    }
    get buffered() {
      const ranges = this.ranges;
      return { length: ranges.length, start: (i) => ranges[i][0], end: (i) => ranges[i][1] };
    }
    appendBuffer() {
      assert.equal(this.updating, false, 'SourceBuffer operations must be serialized');
      this.updating = true;
      state.trace.push('append');
      if (!this.holdAppend) queueMicrotask(() => this.completeAppend());
    }
    completeAppend() {
      assert.equal(this.updating, true);
      this.updating = false;
      this.ranges = [[0, 25]];
      state.video.readyState = 4;
      this.dispatchEvent(new Event('updateend'));
      queueMicrotask(() => state.video.dispatchEvent(new Event('loadeddata')));
    }
    remove() {
      assert.equal(this.updating, false, 'SourceBuffer operations must be serialized');
      this.updating = true;
      this.ranges = [];
      state.trace.push('remove');
      if (!this.holdRemove) queueMicrotask(() => this.completeRemove());
    }
    completeRemove() {
      assert.equal(this.updating, true);
      this.updating = false;
      this.dispatchEvent(new Event('updateend'));
    }
  }

  class MediaSourceMock extends EventTarget {
    static isTypeSupported() { return true; }
    constructor() {
      super();
      state.mediaSource = this;
      this.readyState = 'open';
      this.buffer = new SourceBufferMock();
    }
    addSourceBuffer() { return this.buffer; }
    endOfStream() { state.trace.push('endOfStream'); this.readyState = 'ended'; }
  }

  class VideoMock extends EventTarget {
    constructor() { super(); this.readyState = 0; this.position = 0; }
    set src(value) {
      this.url = value;
      queueMicrotask(() => state.mediaSource.dispatchEvent(new Event('sourceopen')));
    }
    getAttribute() { return this.url; }
    removeAttribute() { this.url = undefined; }
    load() {}
    get buffered() { return state.mediaSource.buffer.buffered; }
    get currentTime() { return this.position; }
    set currentTime(value) {
      this.position = value;
      state.trace.push(`seek:${value}`);
      this.dispatchEvent(new Event('seeking'));
    }
  }

  class WorkerMock {
    constructor() { state.worker = this; this.messages = []; }
    postMessage(message) {
      this.messages.push(message);
      if (message.type === 'probe') {
        queueMicrotask(() => this.emit({ type: 'probe', probe: {
          container: 'Matroska', videoCodec: 'avc1.42001e', videoPlayable: true,
          audioTracks: [], chosenAudioIndex: -1, duration: 100, ...probeOverrides
        } }));
      }
      if (message.type === 'start') {
        state.trace.push(`start:${message.generation}:${message.time}`);
        if (message.generation === 0) queueMicrotask(() => this.emit({ type: 'chunk', generation: 0, id: 1, data: new ArrayBuffer(1) }));
      }
    }
    emit(data) { this.onmessage({ data }); }
    terminate() { state.terminated++; }
  }

  class URLMock extends URL {
    static createObjectURL() { return 'blob:remux-lifecycle'; }
    static revokeObjectURL(url) { state.revoked.push(url); }
  }

  const module = { exports: {} };
  vm.runInNewContext(code, {
    module, exports: module.exports,
    require: (name) => {
      assert.equal(name, './capabilities');
      return { mediaSourceConstructor: () => MediaSourceMock };
    },
    URL: URLMock, Worker: WorkerMock, Event, EventTarget, DOMException, console,
    setTimeout: (callback, ms) => {
      const timer = setTimeout(() => { state.timeouts.delete(timer); callback(); }, ms);
      state.timeouts.add(timer);
      return timer;
    },
    clearTimeout: (timer) => { state.timeouts.delete(timer); clearTimeout(timer); },
    setInterval: (callback) => { state.clock = callback; return 1; },
    clearInterval: () => { state.clock = undefined; }
  }, { filename });
  state.prepare = module.exports.probeAndPrepareRemux;
  state.video = new VideoMock();
  state.starts = () => state.worker.messages.filter(({ type }) => type === 'start').map(({ generation, time }) => ({ generation, time }));
  state.tick = () => state.clock?.();
  return state;
}

test('A container-safety rejection cannot be overwritten by HEVC MSE support', async () => {
  const state = harness({ videoCodec: 'hev1.2.4.L153.B0', videoPlayable: false,
    videoReason: 'Dolby Vision profile 5 requires compatible conversion' });
  const handle = await state.prepare('https://fixture.invalid/film.mp4', undefined, undefined, { expectDolbyVision: true });
  assert.equal(state.worker.messages[0].expectDolbyVision, true);
  assert.equal(handle.probe.videoPlayable, false);
  await assert.rejects(handle.start(state.video), /Dolby Vision profile 5/);
  handle.destroy();
  assert.equal(state.terminated, 1);
  assert.equal(state.timeouts.size, 0);
});

async function playing(t) {
  const state = harness();
  state.handle = await state.prepare('https://fixture.invalid/film.mkv', undefined, undefined, {
    onError: (message) => state.errors.push(message)
  });
  t.after(() => state.handle.destroy());
  await state.handle.start(state.video);
  return state;
}

test('A second seek during buffer removal replaces the pending worker target', async (t) => {
  const state = await playing(t);
  const buffer = state.mediaSource.buffer;
  buffer.holdRemove = true;
  state.video.currentTime = 65;
  await flush();
  assert.equal(buffer.updating, true);
  state.video.currentTime = 12;
  buffer.completeRemove();
  await flush();
  state.tick();
  assert.deepEqual(state.starts(), [
    { generation: 0, time: 0 }, { generation: 1, time: 65 }, { generation: 2, time: 12 }
  ]);
  assert.equal(state.worker.messages.filter(({ type }) => type === 'clock').at(-1).time, 12);
  assert.deepEqual(state.errors, []);
});

test('An old append and queued end cannot reposition or finish a newer seek', async (t) => {
  const state = await playing(t);
  const buffer = state.mediaSource.buffer;
  buffer.holdAppend = true;
  state.worker.emit({ type: 'chunk', generation: 0, id: 2, data: new ArrayBuffer(1) });
  await flush();
  assert.equal(buffer.updating, true);
  state.worker.emit({ type: 'end', generation: 0 });
  state.video.currentTime = 65;
  buffer.completeAppend();
  await flush();
  assert.deepEqual(state.starts(), [{ generation: 0, time: 0 }, { generation: 1, time: 65 }]);
  assert.equal(state.trace.filter((entry) => entry === 'seek:65').length, 1);
  assert.equal(state.trace.includes('endOfStream'), false);
  assert.equal(state.mediaSource.readyState, 'open');
  assert.ok(state.worker.messages.some(({ type, id }) => type === 'ack' && id === 2));
  state.worker.emit({ type: 'end', generation: 1 });
  await flush();
  assert.equal(state.trace.filter((entry) => entry === 'endOfStream').length, 1);
  assert.deepEqual(state.errors, []);
});

test('A chunk arriving from an old generation is acknowledged without an append', async (t) => {
  const state = await playing(t);
  state.video.currentTime = 65;
  await flush();
  const appended = state.trace.filter((entry) => entry === 'append').length;
  state.worker.emit({ type: 'chunk', generation: 0, id: 99, data: new ArrayBuffer(1) });
  state.worker.emit({ type: 'error', generation: 0, message: 'Stale failure' });
  await flush();
  assert.equal(state.trace.filter((entry) => entry === 'append').length, appended);
  assert.ok(state.worker.messages.some(({ type, id }) => type === 'ack' && id === 99));
  assert.deepEqual(state.errors, []);
});

test('Destroy during append releases the worker, URL, timers and event waiters once', async (t) => {
  const state = await playing(t);
  const buffer = state.mediaSource.buffer;
  buffer.holdAppend = true;
  state.worker.emit({ type: 'chunk', generation: 0, id: 2, data: new ArrayBuffer(1) });
  await flush();
  assert.equal(buffer.updating, true);
  state.handle.destroy();
  state.handle.destroy();
  await flush();
  const messages = state.worker.messages.length;
  state.video.currentTime = 65;
  state.worker.emit({ type: 'chunk', generation: 0, id: 3, data: new ArrayBuffer(1) });
  state.tick();
  await flush();
  assert.equal(state.worker.messages.length, messages);
  assert.equal(state.terminated, 1);
  assert.deepEqual(state.revoked, ['blob:remux-lifecycle']);
  assert.equal(state.video.getAttribute('src'), undefined);
  assert.equal(state.timeouts.size, 0);
  assert.equal(state.clock, undefined);
  assert.equal(getEventListeners(state.video, 'seeking').length, 0);
  assert.equal(getEventListeners(buffer, 'updateend').length, 0);
  assert.equal(getEventListeners(buffer, 'error').length, 0);
  assert.deepEqual(state.errors, []);
});

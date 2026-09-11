const test = require('node:test');
const assert = require('node:assert/strict');
const mb = require('mediabunny');
const { load } = require('./load.cjs');

// Encoded 16x16 black H.264 samples; timestamps below model a 40-second GOP.
// The worker uses the real MP4 muxer; no browser, ffmpeg, file or provider is needed.
const keyData = Buffer.from('AAAACmWIhDomKAAJAuA=', 'base64');
const deltaData = Buffer.from('AAAABUGaIBSl', 'base64');
const decoderConfig = {
  codec: 'avc1.42c00a', codedWidth: 16, codedHeight: 16,
  description: Buffer.from('AULACv/hABVnQsAK2nsBEAAAAwAQAAADACDxImoBAARozg/I', 'base64')
};
const flush = () => new Promise(setImmediate);

function workerHarness(t, packets, fetch = async () => { throw new Error('Unexpected fetch'); }, audioPackets = [], overrides = {}) {
  const messages = [];
  const waits = [];
  const timers = new Map();
  const outputs = [];
  const budgets = [];
  const { RemuxBufferBudget } = load('lib/remuxBufferBudget.ts', { mp4box: require('mp4box') }, { Error });
  class Budget extends RemuxBufferBudget {
    constructor() { super(); budgets.push(this); }
  }
  let options;
  let lastPacketTime;
  const emit = (message) => {
    messages.push(message);
    for (const waiter of [...waits]) {
      if (waiter.predicate(message)) {
        waits.splice(waits.indexOf(waiter), 1);
        waiter.resolve(message);
      }
    }
  };
  const video = {
    codec: 'avc', rotation: 0, id: 7,
    getCodecParameterString: async () => decoderConfig.codec,
    getDecoderConfig: async () => decoderConfig, ...overrides.video
  };
  const audio = {
    codec: 'aac', languageCode: 'eng', numberOfChannels: 2,
    getCodecParameterString: async () => 'mp4a.40.2', canDecode: async () => true,
    getDecoderConfig: async () => ({ codec: 'mp4a.40.2', sampleRate: 48000, numberOfChannels: 2,
      description: new Uint8Array([0x11, 0x90]) })
  };
  class InputMock {
    async getFormat() { return { name: 'Matroska' }; }
    async getPrimaryVideoTrack() { return video; }
    async getAudioTracks() { return audioPackets.length ? [audio] : []; }
    async computeDuration() { return 90; }
  }
  class UrlSourceMock {
    constructor(_url, value) { options = value; }
  }
  class PacketSinkMock {
    constructor(track) { this.isAudio = track === audio; }
    async getKeyPacket() { return packets[0]; }
    async getFirstPacket() { return this.isAudio ? audioPackets[0] : packets[0]; }
    async getPacket() { return this.getFirstPacket(); }
    async *packets() {
      for (const packet of this.isAudio ? audioPackets : packets) {
        if (this.isAudio) await flush();
        else lastPacketTime = packet.timestamp;
        yield packet;
      }
    }
  }
  class Output extends mb.Output {
    constructor(config) { super(config); outputs.push(this); }
  }
  const port = {
    postMessage(message) {
      emit(message);
      if (message.type === 'chunk') queueMicrotask(() => port.onmessage({ data: { type: 'ack', id: message.id } }));
    }
  };
  load('lib/remux.worker.ts', {
    mediabunny: { ...mb, Input: InputMock, UrlSource: UrlSourceMock, EncodedPacketSink: PacketSinkMock, Output },
    './remuxProtocol': load('lib/remuxProtocol.ts'),
    './remuxBufferBudget': { RemuxBufferBudget: Budget },
    './dolbyVision': overrides.dolbyVision ?? { probeDolbyVision: async () => { throw new Error('Non-HEVC must not trigger Dolby Vision reads'); }, canExtractHdr10BaseLayer: () => false }
  }, {
    self: port, WritableStream, fetch, Error, TypeError,
    setTimeout: (callback, ms) => {
      const timer = {};
      timers.set(timer, { callback, ms });
      if (ms === 80) emit({ type: 'throttled', timestamp: lastPacketTime });
      return timer;
    },
    clearTimeout: (timer) => timers.delete(timer)
  });
  t.after(async () => {
    for (const output of outputs) if (!['finalized', 'canceled'].includes(output.state)) await output.cancel();
    timers.clear();
  });
  return {
    messages, timers, budgets,
    options: () => options,
    send: (data) => port.onmessage({ data }),
    waitFor: (predicate) => {
      const message = messages.find(predicate);
      return message ? Promise.resolve(message) : new Promise((resolve) => waits.push({ predicate, resolve }));
    },
    fireTimers: (ms) => {
      for (const [id, timer] of [...timers]) {
        if (timer.ms === ms) { timers.delete(id); timer.callback(); }
      }
    }
  };
}

async function probe(harness) {
  harness.send({ type: 'probe', url: 'https://fixture.invalid/film.mkv', audioCodecs: ['mp4a.40.2'] });
  return harness.waitFor(({ type }) => type === 'probe');
}

test('The worker verifies the selected HEVC track and refuses profile 5 before emitting media', async (t) => {
  const helpers = load('lib/dolbyVision.ts', { './dolbyVisionProbeClient': {} });
  let options;
  const h = workerHarness(t, [], undefined, [], { video: { codec: 'hevc' }, dolbyVision: { ...helpers,
    probeDolbyVision: async (_url, value) => { options = value; return { status: 'present', trackId: 7, codec: 'hevc', nalLengthSize: 4,
      config: { profile: 5, compatibilityId: 0, baseLayer: true, enhancementLayer: false, rpu: true } }; }
  } });
  const { probe: result } = await probe(h);
  assert.equal(options.trackId, 7);
  assert.equal(result.videoPlayable, false);
  assert.match(result.videoReason, /profile 5/);
  h.send({ type: 'start', generation: 0, time: 0, audioIndex: -1 });
  await h.waitFor(({ type }) => type === 'error');
  assert.equal(h.messages.some(({ type }) => type === 'chunk'), false);
});

test('Profile 8.1 extraction is enabled only by confirmed file metadata, while normal HEVC remains valid', async (t) => {
  const helpers = load('lib/dolbyVision.ts', { './dolbyVisionProbeClient': {} });
  for (const metadata of [
    { status: 'present', trackId: 7, codec: 'hevc', nalLengthSize: 4,
      config: { profile: 8, compatibilityId: 1, baseLayer: true, enhancementLayer: false, rpu: true } },
    { status: 'absent', trackId: 7 },
    { status: 'unknown', reason: 'range-not-supported' }
  ]) {
    const h = workerHarness(t, [], undefined, [], { video: { codec: 'hevc' },
      dolbyVision: { ...helpers, probeDolbyVision: async () => metadata } });
    const { probe: result } = await probe(h);
    assert.equal(result.videoPlayable, metadata.status !== 'unknown');
    assert.equal(result.hdr10BaseLayer, metadata.status === 'present');
  }
});

test('A 40-second GOP emits a real MP4 media fragment before waiting for the playback clock', { timeout: 5000 }, async (t) => {
  const packets = Array.from({ length: 90 }, (_, timestamp) => {
    const key = timestamp % 40 === 0;
    return new mb.EncodedPacket(key ? keyData : deltaData, key ? 'key' : 'delta', timestamp, 1, timestamp);
  });
  const worker = workerHarness(t, packets);
  await probe(worker);
  worker.send({ type: 'start', generation: 0, time: 0, audioIndex: -1 });
  const stopped = await worker.waitFor(({ type }) => type === 'throttled' || type === 'error');
  assert.equal(stopped.type, 'throttled');
  assert.equal(stopped.timestamp, 40, 'Do not stop at a delta packet before the fragment can flush');
  const chunks = worker.messages.filter(({ type }) => type === 'chunk');
  assert.ok(chunks.some(({ data }) => Buffer.from(data).includes(Buffer.from('moof'))), 'A fragment must precede clock-driven throttling');
  assert.equal(worker.messages.some(({ type }) => type === 'end'), false);

  worker.send({ type: 'clock', time: 70 });
  worker.fireTimers(80);
  const finished = await worker.waitFor(({ type }) => type === 'end' || type === 'error');
  assert.equal(finished.type, 'end');
  const output = Buffer.concat(worker.messages.filter(({ type }) => type === 'chunk').map(({ data }) => Buffer.from(data)));
  const input = new mb.Input({ formats: mb.ALL_FORMATS, source: new mb.BufferSource(output) });
  try {
    assert.equal((await input.getFormat()).name, 'MP4');
    assert.equal(await input.computeDuration(), 90);
    const video = await input.getPrimaryVideoTrack();
    assert.equal(video.codec, 'avc');
    const sink = new mb.EncodedPacketSink(video);
    assert.equal((await sink.getKeyPacket(65)).timestamp, 40);
  } finally { input.dispose(); }
});

test('A single GOP over 24 MiB still fails with an actionable error', { timeout: 5000 }, async (t) => {
  const tooLarge = new mb.EncodedPacket(new Uint8Array(24 * 1024 * 1024 + 1), 'key', 0, 1);
  const worker = workerHarness(t, [tooLarge]);
  await probe(worker);
  worker.send({ type: 'start', generation: 0, time: 0, audioIndex: -1 });
  const result = await worker.waitFor(({ type }) => type === 'error' || type === 'throttled' || type === 'end');
  assert.equal(result.type, 'error');
  assert.match(result.message, /keyframes.*bounded browser playback/i);
  assert.match(result.message, /server conversion or an external player/i);
});

for (const packetMiB of [8, 10, 12]) {
  test(`${packetMiB * 2} MiB GOPs are not mistaken for oversized fragments while audio catches up`, { timeout: 15000 }, async (t) => {
    const data = new Uint8Array(packetMiB * 1024 * 1024);
    data.set(keyData);
    const packets = Array.from({ length: 10 }, (_, i) => new mb.EncodedPacket(data, i % 2 ? 'delta' : 'key', i, 1, i));
    const audio = packets.map((_, i) => new mb.EncodedPacket(new Uint8Array([0x21, 0x10, 0x04, 0x60]), 'key', i, 1, i));
    const worker = workerHarness(t, packets, undefined, audio);
    await probe(worker);
    worker.send({ type: 'start', generation: 0, time: 0, audioIndex: 0 });
    worker.send({ type: 'clock', time: 100 });
    for (let i = 0; i < 1000 && !worker.messages.some(({ type }) => type === 'end' || type === 'error'); i++) {
      await flush();
      worker.fireTimers(80);
    }
    const error = worker.messages.find(({ type }) => type === 'error');
    assert.equal(error, undefined, error?.message);
    assert.ok(worker.messages.some(({ type }) => type === 'end'));
    assert.equal(worker.budgets[0].pendingBytes, 0, 'All video and audio bytes must be released, including queued GOPs');
    const output = Buffer.concat(worker.messages.filter(({ type }) => type === 'chunk').map(({ data }) => Buffer.from(data)));
    const input = new mb.Input({ formats: mb.ALL_FORMATS, source: new mb.BufferSource(output) });
    try {
      assert.equal(await input.computeDuration(), 10);
      assert.equal((await input.getAudioTracks()).length, 1);
      assert.equal((await new mb.EncodedPacketSink(await input.getPrimaryVideoTrack()).getKeyPacket(7)).timestamp, 6);
    } finally { input.dispose(); }
  });
}

test('The independent queue limit cannot be bypassed by frequent keyframes or audio packets', () => {
  const { RemuxBufferBudget } = load('lib/remuxBufferBudget.ts', { mp4box: require('mp4box') });
  const budget = new RemuxBufferBudget();
  for (let i = 0; i < 4; i++) budget.addVideo(16 * 1024 * 1024, true);
  assert.equal(budget.pendingBytes, 64 * 1024 * 1024);
  assert.throws(() => budget.addVideo(1, true), /interleaved.*memory limit/);
  assert.throws(() => budget.add(1), /interleaved.*memory limit/);
});

for (const audioTimes of [
  Array.from({ length: 40 }, (_, i) => i),
  [10, 11, 12, 30, 31], // Delayed start, gap and audio ending before video.
]) {
  test(`High-bitrate video stays bounded while slower audio catches up (${audioTimes.length} packets)`, { timeout: 15000 }, async (t) => {
    const data = new Uint8Array(1024 * 1024);
    data.set(keyData);
    const packets = Array.from({ length: 40 }, (_, i) => new mb.EncodedPacket(data, i % 2 ? 'delta' : 'key', i, 1, i));
    const audio = audioTimes.map((i) => new mb.EncodedPacket(new Uint8Array([0x21, 0x10, 0x04, 0x60]), 'key', i, 1, i));
    const worker = workerHarness(t, packets, undefined, audio);
    await probe(worker);
    worker.send({ type: 'start', generation: 0, time: 0, audioIndex: 0 });
    worker.send({ type: 'clock', time: 100 });
    for (let i = 0; i < 500 && !worker.messages.some(({ type }) => type === 'end' || type === 'error'); i++) {
      await flush();
      worker.fireTimers(80);
    }
    const error = worker.messages.find(({ type }) => type === 'error');
    assert.equal(error, undefined, error?.message);
    assert.ok(worker.messages.some(({ type }) => type === 'end'), 'Both tracks must finish without waiting on each other');
    assert.ok(worker.messages.filter(({ type, data }) => type === 'chunk' && Buffer.from(data).includes(Buffer.from('moof'))).length > 5);
  });
}

test('Range-ignorant responses are cancelled without retry or a leaked deadline', async (t) => {
  let cancelled = 0;
  const worker = workerHarness(t, [], async () => new Response(new ReadableStream({ cancel() { cancelled++; } }), { status: 200 }));
  await probe(worker);
  const options = worker.options();
  const caller = new AbortController();
  await assert.rejects(options.fetchFn('https://fixture.invalid/film.mkv', {
    headers: { Range: 'bytes=500-' }, signal: caller.signal
  }), /range requests/);
  assert.equal(cancelled, 1);
  assert.equal(options.getRetryDelay(1, new Error('Range unsupported')), null);
  assert.equal(worker.timers.size, 0);
});

test('Transient fetch retries are capped across the input, including body resumes and parallel ranges', async (t) => {
  const worker = workerHarness(t, []);
  await probe(worker);
  const retry = worker.options().getRetryDelay;
  assert.equal(retry(1, new Error('Range unsupported')), null);
  assert.equal(retry(1, new DOMException('Playback cancelled', 'AbortError')), null);
  assert.equal(retry(1, new TypeError('Invalid media packet')), null);
  assert.equal(retry(2, new TypeError('Failed to fetch')), null);
  assert.equal(retry(1, new TypeError('Failed to fetch')), 0.35);
  assert.equal(retry(1, new TypeError('Load failed')), 0.35);
  assert.equal(retry(1, new TypeError('Network error')), null);
  assert.equal(retry(1, new TypeError('Failed to fetch')), null);
});

test('Fetch headers and stalled range bodies both have 15-second deadlines', async (t) => {
  for (const phase of ['headers', 'body']) {
    let requestSignal;
    const worker = workerHarness(t, [], async (_url, init) => {
      requestSignal = init.signal;
      if (phase === 'headers') return new Promise((_, reject) => {
        init.signal.addEventListener('abort', () => reject(init.signal.reason), { once: true });
      });
      return new Response(new ReadableStream({
        start(controller) {
          init.signal.addEventListener('abort', () => controller.error(init.signal.reason), { once: true });
        }
      }), { status: 206, headers: { 'Content-Range': 'bytes 0-999/1000' } });
    });
    await probe(worker);
    const pending = worker.options().fetchFn('https://fixture.invalid/film.mkv', { headers: { Range: 'bytes=0-' } });
    const operation = phase === 'headers' ? pending : (await pending).body.getReader().read();
    const rejected = assert.rejects(operation, /abort/i);
    await flush();
    assert.ok([...worker.timers.values()].some(({ ms }) => ms === 15000));
    worker.fireTimers(15000);
    await rejected;
    assert.equal(requestSignal.aborted, true);
    assert.equal(worker.timers.size, 0);
  }
});

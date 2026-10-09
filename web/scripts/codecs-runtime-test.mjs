import assert from 'node:assert/strict';
import fs from 'node:fs/promises';
import http from 'node:http';
import path from 'node:path';
import { createRequire } from 'node:module';

// Run against exported compiler output, never the original npm codecs:
// node web/scripts/codecs-runtime-test.mjs codec-output web
const output = path.resolve(process.argv[2] || 'codec-output');
const app = path.resolve(process.argv[3] || 'web');
const require = createRequire(path.join(app, 'package.json'));
const { chromium } = require('@playwright/test');
const core = path.dirname(require.resolve('mediabunny'));
const mime = { '.mjs': 'text/javascript', '.js': 'text/javascript', '.json': 'application/json' };
const html = `<!doctype html><meta charset="utf-8"><title>ARVIO rebuilt codec test</title>
<script type="importmap">{"imports":{"mediabunny":"/facade.mjs"}}</script>`;
const facade = `export * from '/core/mediabunny.mjs';
export const encoders=[]; export const decoders=[];
export const registerEncoder=c=>encoders.push(c);
export const registerDecoder=c=>decoders.push(c);`;

const server = http.createServer(async (request, response) => {
  try {
    const url = new URL(request.url, 'http://localhost');
    if (url.pathname === '/') { response.setHeader('Content-Type', 'text/html'); response.end(html); return; }
    if (url.pathname === '/facade.mjs') { response.setHeader('Content-Type', 'text/javascript'); response.end(facade); return; }
    const root = url.pathname.startsWith('/core/') ? core : output;
    const relative = url.pathname.startsWith('/core/') ? url.pathname.slice(6) : url.pathname.slice(1);
    const file = path.resolve(root, relative);
    if (!file.startsWith(root + path.sep)) { response.writeHead(403); response.end(); return; }
    response.setHeader('Content-Type', mime[path.extname(file)] || 'application/octet-stream');
    response.end(await fs.readFile(file));
  } catch { response.writeHead(404); response.end(); }
});
await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
let browser;
try {
  browser = await chromium.launch({ headless: true, channel: process.env.PLAYWRIGHT_CHANNEL || 'chrome' });
  const page = await browser.newPage();
  page.setDefaultTimeout(120000);
  const errors = [];
  page.on('pageerror', error => errors.push(error.message));
  await page.goto(`http://127.0.0.1:${server.address().port}/`);
  let runtimeTimeout;
  const evaluation = page.evaluate(async () => {
    const M = await import('/facade.mjs');
    const ac3 = await import('/packages/@mediabunny/ac3/dist/bundles/mediabunny-ac3.mjs');
    const dts = await import('/packages/@mediabunny/dts/dist/bundles/mediabunny-dts.mjs');
    const aac = await import('/packages/@mediabunny/aac-encoder/dist/bundles/mediabunny-aac-encoder.mjs');
    ac3.registerAc3Encoder(); ac3.registerAc3Decoder();
    dts.registerDtsEncoder(); dts.registerDtsDecoder(); aac.registerAacEncoder();
    const sampleRate = 48000, channels = 2, frames = sampleRate;
    const results = [];
    const check = (condition, message) => { if (!condition) throw Error(message); };
    for (const codec of ['ac3', 'eac3', 'dts', 'aac']) {
      const config = { codec, sampleRate, numberOfChannels: channels, bitrate: codec === 'dts' ? 768000 : 192000 };
      const Encoder = M.encoders.find(C => C.supports(codec, config));
      check(Encoder, `No rebuilt ${codec} encoder registered`);
      const packets = [];
      let decoderConfig;
      const encoder = new Encoder();
      Object.assign(encoder, { codec, config, onError: e => { throw e; }, onPacket: (packet, meta) => {
        packets.push(packet); decoderConfig ||= meta?.decoderConfig;
      } });
      await encoder.init();
      for (let start = 0; start < frames; start += 1536) {
        const count = Math.min(1536, frames - start);
        const pcm = new Float32Array(count * channels);
        for (let i = 0; i < count; i++) {
          pcm[i * 2] = 0.25 * Math.sin(2 * Math.PI * 440 * (start + i) / sampleRate);
          pcm[i * 2 + 1] = 0.2 * Math.sin(2 * Math.PI * 660 * (start + i) / sampleRate);
        }
        const sample = new M.AudioSample({ data: pcm, format: 'f32', numberOfChannels: channels, sampleRate, timestamp: start / sampleRate });
        try { await encoder.encode(sample); } finally { sample.close(); }
      }
      await encoder.flush();
      await encoder.close();
      check(packets.length >= 20 && decoderConfig, `${codec}: packets/config missing`);
      let decodedFrames = 0, sumSquares = 0, firstTimestamp = Infinity, lastTimestamp = 0;
      const tone = [{ sin: 0, cos: 0, frequency: 440 }, { sin: 0, cos: 0, frequency: 660 }];
      const consume = sample => {
        check(sample.sampleRate === sampleRate && sample.numberOfChannels === channels, `${codec}: wrong audio format`);
        const pcm = new Float32Array(sample.numberOfFrames * channels);
        sample.copyTo(pcm, { format: 'f32', planeIndex: 0 });
        for (const value of pcm) { check(Number.isFinite(value), `${codec}: non-finite PCM`); sumSquares += value * value; }
        for (let i = 0; i < sample.numberOfFrames; i++) {
          for (let channel = 0; channel < channels; channel++) {
            const phase = 2 * Math.PI * tone[channel].frequency * (decodedFrames + i) / sampleRate;
            tone[channel].sin += pcm[i * channels + channel] * Math.sin(phase);
            tone[channel].cos += pcm[i * channels + channel] * Math.cos(phase);
          }
        }
        decodedFrames += sample.numberOfFrames;
        firstTimestamp = Math.min(firstTimestamp, sample.timestamp);
        lastTimestamp = Math.max(lastTimestamp, sample.timestamp + sample.numberOfFrames / sampleRate);
        sample.close();
      };
      if (codec === 'aac') {
        // This extension intentionally supplies an AAC encoder only. Decode its
        // actual WASM-produced packets using Chromium's independent decoder.
        check(typeof AudioDecoder !== 'undefined', 'AAC validation requires native WebCodecs decoding');
        let decodeError;
        const decoder = new AudioDecoder({ output: data => consume(new M.AudioSample(data)), error: e => { decodeError = e; } });
        decoder.configure(decoderConfig);
        for (const packet of packets) decoder.decode(new EncodedAudioChunk({ type: 'key', timestamp: Math.round(packet.timestamp * 1e6), duration: Math.round(packet.duration * 1e6), data: packet.data }));
        await decoder.flush(); decoder.close();
        check(!decodeError, `AAC decoder failed: ${decodeError}`);
      } else {
        const Decoder = M.decoders.find(C => C.supports(codec, decoderConfig));
        check(Decoder, `No rebuilt ${codec} decoder registered`);
        const decoder = new Decoder();
        Object.assign(decoder, { codec, config: decoderConfig, onSample: consume, onError: e => { throw e; } });
        await decoder.init();
        for (const packet of packets) await decoder.decode(packet);
        await decoder.flush(); await decoder.close();
      }
      const rms = Math.sqrt(sumSquares / (decodedFrames * channels));
      const duration = decodedFrames / sampleRate;
      const toneAmplitudes = tone.map(item => 2 * Math.hypot(item.sin, item.cos) / decodedFrames);
      check(duration >= 0.98 && duration <= 1.1, `${codec}: unexpected decoded duration ${duration}`);
      check(rms > 0.05 && rms < 0.35, `${codec}: silent/corrupt PCM, RMS=${rms}`);
      check(toneAmplitudes[0] > 0.1 && toneAmplitudes[1] > 0.07, `${codec}: decoded channel tones were lost: ${toneAmplitudes}`);
      check(firstTimestamp >= -0.05 && firstTimestamp <= 0.05 && lastTimestamp <= 1.15, `${codec}: timestamp drift`);
      results.push({ codec, packets: packets.length, encodedBytes: packets.reduce((n, p) => n + p.data.length, 0), decodedFrames, duration, rms, toneAmplitudes, firstTimestamp, lastTimestamp });
    }
    return results;
  });
  const result = await Promise.race([evaluation, new Promise((_, reject) => {
    runtimeTimeout = setTimeout(() => reject(new Error('Codec worker round-trip exceeded 120 seconds')), 120000);
    runtimeTimeout.unref();
  })]).finally(() => clearTimeout(runtimeTimeout));
  assert.deepEqual(result.map(item => item.codec), ['ac3', 'eac3', 'dts', 'aac']);
  assert.deepEqual(errors, []);
  console.log(JSON.stringify({ passed: true, browser: await browser.version(), fixture: '1s 48kHz stereo 440/660Hz PCM', results: result }, null, 2));
} finally {
  await browser?.close();
  await new Promise(resolve => server.close(resolve));
}

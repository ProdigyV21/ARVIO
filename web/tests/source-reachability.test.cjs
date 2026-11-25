const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const verify = load('lib/autoplayVerify.ts', {
  './debrid': { cachedDebridDirectUrl: () => null, parseDebridStream: () => null },
  './streamCompatibility': { browserPlaysDolbyAudio: () => false, canTryRemux: () => true, playbackPlan: (s) => ({ method: s.method ?? 'direct' }), streamTransport: () => 'file' },
  './streamVerify': { cachedVerdict: () => undefined, verifyStream: async () => null }
});

const response = (url, status = 206, total = 4_000_000_000) => ({ url, status, headers: { get: (name) => name === 'content-range' ? `bytes 0-0/${total}` : null }, body: { cancel: async () => {} } });
const signal = new AbortController().signal;
const stream = { url: 'https://relay.example/media?t=1', method: 'remux' };

test('a link that redirects to a "download failed" clip is dead', async () => {
  assert.equal(await verify.checkReachable(stream, stream.url, signal, async () => response('https://usenet.example/static/download_failed.mp4', 200, 120_000)), false);
});

test('error statuses and tiny placeholder files are dead; a real file is unknown, not proven', async () => {
  assert.equal(await verify.checkReachable(stream, stream.url, signal, async () => response(stream.url, 404)), false);
  assert.equal(await verify.checkReachable(stream, stream.url, signal, async () => response(stream.url, 206, 2_000_000)), false);
  assert.equal(await verify.checkReachable(stream, stream.url, signal, async () => response(stream.url)), null);
});

test('a CORS-blocked file rules out repackaging but not direct play', async () => {
  const blocked = async () => { throw new TypeError('Failed to fetch'); };
  assert.equal(await verify.checkReachable(stream, stream.url, signal, blocked), false);
  assert.equal(await verify.checkReachable({ ...stream, method: 'direct' }, stream.url, signal, blocked), null);
});

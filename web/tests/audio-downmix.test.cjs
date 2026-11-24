const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const { dialogueDownmix } = load('lib/dialogueDownmix.ts');
const { convertedAudioLayout } = load('lib/remuxProtocol.ts');
const planes = (values) => values.map((value) => Float32Array.from([value, value]));

test('speech in the centre channel reaches both speakers at full level', () => {
  const [left, right] = dialogueDownmix(planes([0, 0, 0.5, 0.9, 0, 0]));
  assert.ok(Math.abs(left[0] - 0.5) < 1e-6 && Math.abs(right[0] - 0.5) < 1e-6);
});

test('LFE is dropped, surrounds are lowered and loud peaks never clip', () => {
  const [left] = dialogueDownmix(planes([0, 0, 0, 1, 0.4, 0]));
  assert.ok(Math.abs(left[0] - 0.2) < 1e-6, 'surround at -6 dB, no LFE');
  const [loud] = dialogueDownmix(planes([1, 1, 1, 1, 1, 1]));
  assert.ok(loud[0] <= 1 && loud[0] > 0.85, `soft-limited peak ${loud[0]}`);
});

test('converted audio keeps 5.1 only for a 5.1 source on surround output', () => {
  assert.deepEqual({ ...convertedAudioLayout(6, true, true) }, { numberOfChannels: 6, bitrate: 384000 });
  assert.deepEqual({ ...convertedAudioLayout(6, false, true) }, { numberOfChannels: 2, bitrate: 192000 });
  assert.deepEqual({ ...convertedAudioLayout(2, true, true) }, { numberOfChannels: 2, bitrate: 192000 });
  assert.deepEqual({ ...convertedAudioLayout(6, true, false) }, { numberOfChannels: 2, bitrate: 192000 });
});

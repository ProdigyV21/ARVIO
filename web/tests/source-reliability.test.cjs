const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { sourceFamily, recordSourceOutcome, isUnreliableSource } = load('lib/sourceReliability.ts', {
  './debrid': { parseDebridStream: (url) => /torbox/.test(url) ? { provider: 'torbox' } : null }
});

const memory = () => { const values = new Map(); return { getItem: (k) => values.get(k) ?? null, setItem: (k, v) => values.set(k, v) }; };
const usenet = (n) => ({ url: `https://flixnest.app/flix-streams/api/usenet-vault-aiostreams/media?t=token${n}` });

test('links from one relay share a family; ids and tokens do not split it', () => {
  assert.equal(sourceFamily(usenet(1)), sourceFamily(usenet(2)));
  assert.equal(sourceFamily(usenet(1)), 'flixnest.app/flix-streams/api/usenet-vault-aiostreams');
  assert.equal(sourceFamily({ url: 'https://cdn.example/files/9f8e7d6c5b4a39281716/movie.mkv' }), 'cdn.example/files/movie.mkv');
});

test('a family that fails twice on this device is tried last, until it plays again', () => {
  const storage = memory();
  assert.equal(isUnreliableSource(usenet(3), storage), false);
  recordSourceOutcome(usenet(1), false, storage);
  assert.equal(isUnreliableSource(usenet(3), storage), false);
  recordSourceOutcome(usenet(2), false, storage);
  assert.equal(isUnreliableSource(usenet(3), storage), true);
  recordSourceOutcome(usenet(4), true, storage);
  assert.equal(isUnreliableSource(usenet(3), storage), false);
});

test('debrid providers are never demoted', () => {
  const storage = memory();
  const torbox = { url: 'https://store.torbox.app/x' };
  recordSourceOutcome(torbox, false, storage);
  recordSourceOutcome(torbox, false, storage);
  assert.equal(isUnreliableSource(torbox, storage), false);
});

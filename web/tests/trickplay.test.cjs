const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const trickplay = load('lib/trickplay.ts', { './homeserver': { ensureHomeServerSession: async () => null } });

test('a time maps to the right tile and cell of a 10x10 grid', () => {
  assert.deepEqual({ ...trickplay.tilePosition(0, 10, 10, 10) }, { tile: 0, column: 0, row: 0 });
  assert.deepEqual({ ...trickplay.tilePosition(125, 10, 10, 10) }, { tile: 0, column: 2, row: 1 });
  assert.deepEqual({ ...trickplay.tilePosition(1005, 10, 10, 10) }, { tile: 1, column: 0, row: 0 });
});

function bif(frames, intervalMs = 5000) {
  const header = 64 + (frames.length + 1) * 8;
  const total = header + frames.reduce((sum, frame) => sum + frame.length, 0);
  const buffer = new ArrayBuffer(total);
  const view = new DataView(buffer);
  [0x89, 0x42, 0x49, 0x46, 0x0d, 0x0a, 0x1a, 0x0a].forEach((byte, index) => view.setUint8(index, byte));
  view.setUint32(12, frames.length, true);
  view.setUint32(16, intervalMs, true);
  let offset = header;
  frames.forEach((frame, index) => {
    view.setUint32(64 + index * 8, index, true);
    view.setUint32(64 + index * 8 + 4, offset, true);
    new Uint8Array(buffer, offset, frame.length).set(frame);
    offset += frame.length;
  });
  view.setUint32(64 + frames.length * 8, 0xffffffff, true);
  view.setUint32(64 + frames.length * 8 + 4, offset, true);
  return buffer;
}

test('a Plex BIF index yields its interval and frame ranges; anything else is rejected', () => {
  const parsed = trickplay.parseBif(bif([[1, 2, 3], [4, 5]]));
  assert.equal(parsed.interval, 5);
  assert.equal(parsed.frames.length, 2);
  assert.equal(parsed.frames[1].end - parsed.frames[1].start, 2);
  assert.equal(trickplay.parseBif(new ArrayBuffer(80)), null);
});

test('only Jellyfin and Plex servers over HTTPS are asked', async () => {
  const signal = new AbortController().signal;
  const stream = { url: 'https://media.example/x', homeServer: { serverId: 's', itemId: 'i' } };
  assert.equal(await trickplay.loadTrickplay(stream, [{ id: 's', type: 'emby', url: 'https://emby.example', enabled: true }], 1.78, signal), null);
  assert.equal(await trickplay.loadTrickplay(stream, [{ id: 's', type: 'jellyfin', url: 'http://lan.example', enabled: true }], 1.78, signal), null);
  assert.equal(await trickplay.loadTrickplay({ url: 'https://x' }, [], 1.78, signal), null);
});

const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');
const sandbox = { exports: {} };
vm.runInNewContext(ts.transpileModule(fs.readFileSync(`${__dirname}/../lib/multiview.ts`, 'utf8'), {
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 },
}).outputText, sandbox);
const m = sandbox.exports;
const ch = (id) => ({ id, name: id, streamUrl: `https://example.invalid/${id}`, group: 'Sports' });
const tile = (id, ...alts) => ({ key: `game:${id}`, title: id, channels: [ch(id), ...alts.map(ch)] });

test('tiles are added up to the capacity, never twice', () => {
  const one = m.addMultiviewTile([], tile('a'), 2);
  assert.equal(one.kind, 'added');
  const two = m.addMultiviewTile(one.tiles, tile('b'), 2);
  assert.equal(m.addMultiviewTile(two.tiles, tile('c'), 2).kind, 'full');
  assert.deepEqual(JSON.parse(JSON.stringify(m.addMultiviewTile(two.tiles, tile('b'), 4))), { kind: 'present', index: 1 });
  assert.equal(m.addMultiviewTile(two.tiles, { ...tile('a'), key: 'other' }, 4).kind, 'present');
  assert.equal(m.addMultiviewTile([], { key: 'x', title: 'x', channels: [] }, 4).kind, 'full');
  const four = ['a', 'b', 'c', 'd'].map(id => tile(id));
  assert.equal(m.addMultiviewTile(four, tile('e'), 9).kind, 'full');
});

test('capacity follows the screen and the device', () => {
  assert.equal(m.multiviewCapacity({ width: 390 }), 2);
  assert.equal(m.multiviewCapacity({ width: 1920, deviceMemory: 2 }), 3);
  assert.equal(m.multiviewCapacity({ width: 1920, cores: 2 }), 3);
  assert.equal(m.multiviewCapacity({ width: 1920, deviceMemory: 8, cores: 8 }), 4);
  assert.equal(m.multiviewCapacity({}), 4);
});

test('sound stays with its game when a tile closes', () => {
  assert.equal(m.audioAfterRemoval(2, 0, 3), 1);
  assert.equal(m.audioAfterRemoval(2, 3, 3), 2);
  assert.equal(m.audioAfterRemoval(3, 3, 3), 2);
  assert.equal(m.audioAfterRemoval(0, 0, 0), 0);
});

test('the tile with sound asks for the sharper picture', () => {
  assert.equal(m.multiviewMaxHeight(true, 1), Infinity);
  assert.equal(m.multiviewMaxHeight(true, 2), 1080);
  assert.equal(m.multiviewMaxHeight(false, 4), 540);
});

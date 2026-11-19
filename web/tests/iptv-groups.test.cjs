const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { playlistGroups, movePlaylistGroup, setPlaylistGroupsVisible } = load('lib/iptvGroups.ts');
const channels = [{id:'p:1',group:'A'}, {id:'p:2',group:'B'}, {id:'p:3',group:'B'}, {id:'other:1',group:'A'}];
const plain = value => JSON.parse(JSON.stringify(value));

test('Groups use provider order, stable playlist identity and include hidden channels', () => {
  assert.deepEqual(plain(playlistGroups(channels, 'p', [])), [{key:'p|A',name:'A',label:'A',count:1},{key:'p|B',name:'B',label:'B',count:2}]);
  assert.deepEqual(plain(playlistGroups(channels, 'p', ['p|B'])).map(g=>g.name), ['B','A']);
});
test('Repeated moves follow identity, preserve other playlists and stop at boundaries', () => {
  const saved = ['other|Z','other|A','p|missing'];
  let groups = playlistGroups(channels, 'p', saved);
  const moved = movePlaylistGroup(saved, groups, 'p|A', 1);
  assert.deepEqual(plain(moved), [...saved,'p|B','p|A']);
  groups = playlistGroups(channels, 'p', moved);
  assert.equal(movePlaylistGroup(moved,groups,'p|A',1),moved);
  assert.deepEqual(plain(movePlaylistGroup(moved,groups,'p|A',-1)), [...saved,'p|A','p|B']);
});
test('Show/hide all affects only the selected playlist', () => {
  const groups = playlistGroups(channels,'p',[]);
  assert.deepEqual(plain(setPlaylistGroupsVisible(['other|A'],groups,false)), ['other|A','p|A','p|B']);
  assert.deepEqual(plain(setPlaylistGroupsVisible(['other|A','p|A','p|B'],groups,true)), ['other|A']);
});

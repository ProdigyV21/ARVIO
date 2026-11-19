// Fixes from the review of the merged workstreams (progress ordering, Live TV
// keys, manual channel order, favourite categories, disabled libraries).
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { playerSource } = require('./sources.cjs');

const read = (file) => fs.readFileSync(path.join(__dirname, '..', file), 'utf8');

test('progress checkpoints wait for queued full-document writes, except from a closing page', () => {
  const cloud = read('lib/cloud.ts');
  assert.match(cloud, /async function runInMutationQueue<T>\(userId: string, run: \(\) => Promise<T>\)/);
  assert.match(cloud, /options\.keepalive\s+\? await sendProgressOperation\(auth, userId, operation, true\)\s+: await runInMutationQueue\(userId, \(\) => sendProgressOperation\(auth, userId, operation, false\)\)/);
});

test('Live TV zapping leaves arrows and Back to a focused control bar', () => {
  const player = playerSource();
  assert.match(player, /!\(controlBarKey && showControls && focusedControl\) && handleLiveZapKey\(e, dock\)/);
});

test('the Live TV page applies the manual channel order and Android-style favourite categories', () => {
  const screen = read('components/livetv/LiveTvScreen.tsx');
  assert.match(screen, /applyChannelOrder\(sorted, settings\.iptvChannelOrder\?\.\[activeCategory\.slice\(6\)\]\)/);
  assert.match(screen, /favoriteGroupIds: toggleFavoriteGroup\(favoriteGroups, groupId\(group\)\)/);
  assert.match(screen, /isFavoriteGroup\(favoriteGroups, group\)/);
});

test('switched-off home servers and libraries get no Home row', () => {
  const homeserver = read('lib/homeserver.ts');
  const start = homeserver.indexOf('export async function buildHomeServerCatalogConfigs');
  const body = homeserver.slice(start, start + 1500);
  assert.match(body, /if \(server\.enabled === false\) continue;/);
  assert.match(body, /collection\.enabled === false\) continue;/);
});

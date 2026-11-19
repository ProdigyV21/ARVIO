// Video playing with no sound (Chromium dropped an E-AC-3/DTS track) is
// detected and handed to the audio-converting path; subtitles reach every row.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');

function setup(overrides = {}) {
  let now = 0, tick, silent = 0, cleared = 0;
  const document = { visibilityState: 'visible' };
  const video = { currentTime: 0, paused: false, seeking: false, ended: false,
    webkitAudioDecodedByteCount: 0, webkitVideoDecodedByteCount: 5000, ...overrides };
  const { monitorAudioDecode } = load('lib/playerRecovery.ts', {}, {
    document, Date: { now: () => now },
    setInterval: (fn) => { tick = fn; return 1; }, clearInterval: () => cleared++
  });
  const stop = monitorAudioDecode(video, () => silent++);
  return { video, document, stop,
    advance: (count = 1, progress = true) => { for (let i = 0; i < count; i++) { now += 1000; if (progress) video.currentTime += 1; tick && tick(); } },
    get silent() { return silent; }, get cleared() { return cleared; }, get ticking() { return Boolean(tick); } };
}

test('video decoding with zero audio bytes for 5 s of playback is reported once', () => {
  const h = setup();
  h.advance(4); assert.equal(h.silent, 0);
  h.advance(1); assert.equal(h.silent, 1);
  h.advance(10); assert.equal(h.silent, 1);
});

test('any decoded audio ends the watch', () => {
  const h = setup();
  h.advance(2); h.video.webkitAudioDecodedByteCount = 128; h.advance(10);
  assert.equal(h.silent, 0); assert.equal(h.cleared, 1);
});

for (const state of ['paused', 'seeking', 'hidden', 'buffering']) {
  test(`${state} does not count as silent playback`, () => {
    const h = setup();
    if (state === 'hidden') h.document.visibilityState = 'hidden';
    else if (state !== 'buffering') h.video[state] = true;
    h.advance(20, state !== 'buffering'); assert.equal(h.silent, 0);
  });
}

test('browsers without decode counters are left alone', () => {
  const h = setup({ webkitAudioDecodedByteCount: undefined });
  assert.equal(h.ticking, false);
});

const player = fs.readFileSync(path.join(__dirname, '../components/player/overlay/VideoPlayer.tsx'), 'utf8');
const playback = fs.readFileSync(path.join(__dirname, '../lib/store/playback.ts'), 'utf8');
const addons = fs.readFileSync(path.join(__dirname, '../lib/addons.ts'), 'utf8');
const overlay = fs.readFileSync(path.join(__dirname, '../components/player/PlayerOverlay.tsx'), 'utf8');

test('silent direct playback switches to audio conversion at the same position', () => {
  const block = player.slice(player.indexOf('return monitorAudioDecode(video'));
  assert.match(block, /if \(playhead > 5\) resumeAtRef\.current = playhead;/);
  assert.match(block, /canTryRemux\(stream\)[\s\S]*?onSelectStream\(\{ \.\.\.stream, convertAudio: true \}, \{ forceRemux: true, forceBrowser: true \}\)/);
  assert.match(block, /tryNextSource\(\)/);
});

test('a probe that already saw undecodable audio starts in the converting path', () => {
  assert.match(playback, /!needsAudioConversion\(candidate\)\s*\? candidate : \{ \.\.\.candidate, convertAudio: true \}/);
  assert.match(playback, /next\.convertAudio \? \{ forceRemux: true \}/);
});

test('relay and browser lookups publish one merged list, all rows with subtitles', () => {
  const block = addons.slice(addons.indexOf('if (hasResolverConfig()) {'), addons.indexOf('return getBrowserStreamsProgressive(addons, item, season, episode, onUpdate);'));
  assert.match(block, /dedupeStreams\(\[\.\.\.browserLatest, \.\.\.resolverLatest\]\)/);
  assert.match(block, /\.map\(\(stream\) => attachSubtitles\(stream, subtitles\)\)/);
  assert.match(block, /fetchSubtitlesForItem\(addons, item, season, episode\)/);
  assert.match(block, /resolverLatest = all; publish\(batch\);/);
});

test('the player finds the list row by its original URL too', () => {
  assert.match(overlay, /candidate\.url === activeStream\.originalUrl/);
});

test('the playlist proxy forwards v2 (ECDSA) stream tickets to relay segment URLs', () => {
  const route = fs.readFileSync(path.join(__dirname, '../app/api/proxy/route.ts'), 'utf8');
  const pattern = new RegExp(route.match(/streamTicket && \/(.+?)\/\.test\(streamTicket\)/)[1]);
  assert.ok(pattern.test('v2.1793000000.abcdef0123.BY73WryOwF0.' + 'A'.repeat(86)));
  assert.ok(pattern.test('v1.1793000000.abcdef0123.' + 'B'.repeat(43)));
  assert.ok(!pattern.test('v3.x'));
});

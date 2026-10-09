const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

function harness({ live = false } = {}) {
  const state = { nudges: 0, reloads: [], recoveries: 0, failures: 0 };
  const document = { visibilityState: 'visible' };
  const video = Object.assign(new EventTarget(), {
    currentTime: 120, paused: false, seeking: false, ended: false, readyState: 4,
    play() { this.paused = false; return Promise.resolve(); }
  });
  const timers = new Set();
  const recovery = load('lib/playerRecovery.ts', {}, {
    document, setInterval: fn => { timers.add(fn); return fn; }, clearInterval: fn => timers.delete(fn)
  });
  const stop = recovery.monitorPlaybackStall(video, {
    live,
    reload: position => {
      state.reloads.push(position);
      video.currentTime = 0; video.readyState = 0; video.paused = true;
    },
    onRecover: () => state.recoveries++, onFailure: () => state.failures++
  });
  return { state, document, video, stop, timers,
    tick(count = 1) { for (let i = 0; i < count; i++) for (const timer of [...timers]) timer(); },
    seeked() { video.dispatchEvent(new Event('seeked')); }
  };
}

test('a recovery seek is not counted as real progress; a paused reload still reaches a bounded failure', () => {
  const h = harness();
  h.tick(6);
  assert.equal(h.video.currentTime, 120.35);
  h.seeked();
  h.tick(8);
  assert.deepEqual(h.state.reloads, [120.35]);
  h.tick(10);
  assert.equal(h.state.failures, 1);
  assert.equal(h.state.recoveries, 1);
  h.tick(60);
  assert.equal(h.state.failures, 1);
  assert.equal(h.state.reloads.length, 1);
  h.stop();
});

test('a recovered moving clock resets the budget, without mistaking restored position for progress', () => {
  const h = harness();
  h.tick(14);
  const position = h.state.reloads[0];
  h.video.currentTime = position; h.video.readyState = 4; h.video.paused = false;
  h.seeked(); h.tick();
  for (let i = 0; i < 40; i++) { h.video.currentTime += 1; h.tick(); }
  assert.equal(h.state.failures, 0);
  assert.equal(h.state.reloads.length, 1);
  h.tick(6);
  assert.equal(h.state.recoveries, 2);
  h.stop();
});

test('an unrecovered restored timestamp does not hide a persistent stall', () => {
  const h = harness();
  h.tick(14);
  h.video.currentTime = h.state.reloads[0]; h.video.readyState = 4; h.video.paused = false;
  h.seeked(); h.tick(10);
  assert.equal(h.state.failures, 1);
  h.stop();
});

test('live reload can restart its timestamp window and proves recovery through subsequent progress', () => {
  const h = harness({ live: true });
  h.video.currentTime = 500;
  h.tick(); // First sample observes ordinary progress to the old live timeline.
  h.tick(14);
  assert.equal(h.state.reloads.length, 1);
  h.video.currentTime = 10; h.video.readyState = 4; h.video.paused = false;
  h.seeked(); h.tick();
  for (let i = 0; i < 40; i++) { h.video.currentTime += 1; h.tick(); }
  assert.equal(h.state.failures, 0);
  assert.equal(h.state.reloads.length, 1);
  assert.equal(h.state.recoveries, 1);
  h.stop();
});

test('user pause, user seek, background tabs and cleanup do not trigger unsolicited recovery', () => {
  for (const mode of ['pause', 'seek', 'hidden', 'ended', 'cleanup']) {
    const h = harness();
    if (mode === 'pause') h.video.paused = true;
    if (mode === 'seek') h.video.seeking = true;
    if (mode === 'hidden') h.document.visibilityState = 'hidden';
    if (mode === 'ended') h.video.ended = true;
    if (mode === 'cleanup') h.stop();
    h.tick(120);
    assert.equal(h.state.recoveries, 0, mode);
    assert.equal(h.state.failures, 0, mode);
    h.stop();
    assert.equal(h.timers.size, 0);
  }
});

test('a deliberate seek starts a fresh grace period after ordinary buffering', () => {
  const h = harness();
  h.tick(4);
  h.video.currentTime = 500; h.seeked(); h.tick(4);
  assert.equal(h.state.recoveries, 0);
  h.video.currentTime += 1; h.tick(1);
  assert.equal(h.state.failures, 0);
  h.stop();
});

test('failure diagnostics are fixed categories even when exception text contains private source URLs', () => {
  const { playbackFailureKind } = load('lib/playerRecovery.ts');
  assert.equal(playbackFailureKind({ kind: 'network', message: 'https://private.example/secret?token=example' }), 'network');
  assert.equal(playbackFailureKind({ kind: 'media' }), 'format');
  assert.equal(playbackFailureKind({ kind: 'unsupported' }), 'format');
  assert.equal(playbackFailureKind({ code: 'STARTUP_TIMEOUT' }), 'timeout');
  assert.equal(playbackFailureKind({ code: 'NATIVE_HEADERS_UNSUPPORTED' }), 'browser_restriction');
  assert.equal(playbackFailureKind({ code: 'ENGINE_LOAD_FAILED' }), 'engine');
  assert.equal(playbackFailureKind({ message: 'Failed to fetch https://private.example/key' }), 'network');
  assert.equal(playbackFailureKind({ message: 'No compatible decoder' }), 'format');
  assert.equal(playbackFailureKind(), 'unknown');
});

test('audio files are not judged by the video-frame watchdog', () => {
  const { isAudioOnlySource } = load('lib/playerRecovery.ts');
  assert.equal(isAudioOnlySource('https://traffic.omny.fm/d/clips/a/b/audio.mp3'), true);
  assert.equal(isAudioOnlySource('https://cdn.example/ep.M4A?token=1'), true);
  assert.equal(isAudioOnlySource('https://cdn.example/master.m3u8'), false);
  assert.equal(isAudioOnlySource('https://cdn.example/movie.mkv'), false);
  assert.equal(isAudioOnlySource(undefined), false);
});

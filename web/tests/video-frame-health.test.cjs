const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

function setup(overrides = {}) {
  let now = 0, tick, frame, failed = 0, cleared = 0, cancelled = 0;
  const document = { visibilityState: 'visible' };
  const video = { currentTime: 0, videoWidth: 3840, paused: false, seeking: false, ended: false,
    requestVideoFrameCallback: (fn) => { frame = fn; return 0; },
    cancelVideoFrameCallback: () => cancelled++, ...overrides };
  const { monitorVideoFrames } = load('lib/playerRecovery.ts', {}, {
    document, Date: { now: () => now },
    setInterval: (fn) => { tick = fn; return 1; }, clearInterval: () => cleared++
  });
  const stop = monitorVideoFrames(video, () => failed++);
  return { video, document, stop, present: () => frame(),
    advance: (count = 1, progress = true) => { for (let i = 0; i < count; i++) { now += 1500; if (progress) video.currentTime += 1.5; tick(); } },
    get failed() { return failed; }, get cleared() { return cleared; }, get cancelled() { return cancelled; } };
}

test('known 4K dimensions do not mask audio advancing with zero video frames', () => {
  const h = setup();
  h.advance(7); assert.equal(h.failed, 0);
  h.advance(); assert.equal(h.failed, 1);
  h.advance(10); assert.equal(h.failed, 1);
  assert.equal(h.cancelled, 1);
});

test('a presented frame, even a legitimately black scene, is healthy', () => {
  const h = setup(); h.present(); h.advance(15);
  assert.equal(h.failed, 0); assert.equal(h.cleared, 1);
});

test('frames decoded before the monitor starts count as healthy', () => {
  const h = setup({ getVideoPlaybackQuality: () => ({ totalVideoFrames: 30, droppedVideoFrames: 2 }) });
  h.advance(15); assert.equal(h.failed, 0);
});

test('all frames dropped does not count as video rendering', () => {
  const h = setup({ requestVideoFrameCallback: undefined,
    getVideoPlaybackQuality: () => ({ totalVideoFrames: 30, droppedVideoFrames: 30 }) });
  h.advance(8); assert.equal(h.failed, 1);
});

for (const state of ['paused', 'seeking', 'ended', 'hidden', 'buffering']) {
  test(`${state} does not consume the missing-video grace period`, () => {
    const h = setup(); h.advance(4);
    if (state === 'hidden') h.document.visibilityState = 'hidden';
    else if (state !== 'buffering') h.video[state] = true;
    h.advance(12, state !== 'buffering'); assert.equal(h.failed, 0);
    h.document.visibilityState = 'visible'; h.video.paused = false; h.video.seeking = false; h.video.ended = false;
    h.advance(7); assert.equal(h.failed, 0);
    h.advance(); assert.equal(h.failed, 1);
  });
}

test('older browsers without frame telemetry only fail when dimensions are missing', () => {
  const healthy = setup({ requestVideoFrameCallback: undefined });
  healthy.advance(15); assert.equal(healthy.failed, 0);
  const failed = setup({ requestVideoFrameCallback: undefined, videoWidth: 0 });
  failed.advance(8); assert.equal(failed.failed, 1);
});

test('source teardown cancels pending frame callbacks and timers', () => {
  const h = setup(); h.stop(); h.advance(15);
  assert.equal(h.failed, 0); assert.equal(h.cancelled, 1); assert.equal(h.cleared, 1);
});

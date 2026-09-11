const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const recovery = load('lib/playerRecovery.ts');
const flush = () => new Promise((resolve) => setImmediate(resolve));
const plain = (value) => JSON.parse(JSON.stringify(value));
const ranges = (...values) => ({ length: values.length, start: (i) => values[i][0], end: (i) => values[i][1] });

class Events {
  handlers = new Map();
  on(event, fn) { this.handlers.set(event, [...this.listeners(event), fn]); }
  off(event, fn) { this.handlers.set(event, this.listeners(event).filter((item) => item !== fn)); }
  listeners(event) { return this.handlers.get(event) ?? []; }
  emit(event, ...args) { for (const fn of [...this.listeners(event)]) fn(...args); }
  addEventListener(event, fn) { this.on(event, fn); }
  removeEventListener(event, fn) { this.off(event, fn); }
}

class Video extends Events {
  src = '';
  currentTime = 0;
  duration = 180;
  paused = true;
  ended = false;
  error = null;
  buffered = ranges();
  seekable = ranges();
  loads = 0;
  plays = 0;
  nativeHls = false;
  nativeTs = false;
  disableRemotePlayback = false;
  canPlayType(type) { return (type.includes('mpegurl') && this.nativeHls) || (type === 'video/mp2t' && this.nativeTs) ? 'probably' : ''; }
  removeAttribute(name) { if (name === 'src') this.src = ''; }
  load() { this.loads++; this.currentTime = 0; this.paused = true; }
  play() { this.plays++; this.paused = false; return Promise.resolve(); }
}

function engines() {
  const hls = [], dash = [], ts = [];
  class Hls extends Events {
    static Events = Object.fromEntries(['ERROR', 'MANIFEST_PARSED', 'AUDIO_TRACKS_UPDATED', 'LEVELS_UPDATED', 'AUDIO_TRACK_SWITCHED', 'LEVEL_SWITCHED'].map((name) => [name, name]));
    static ErrorTypes = { NETWORK_ERROR: 'network', MEDIA_ERROR: 'media' };
    static supported = true;
    static isSupported() { return this.supported; }
    audioTracks = [{ name: 'English', lang: 'en' }, { name: 'Nederlands', lang: 'nl' }];
    audioTrack = 0;
    levels = [{ height: 720, width: 1280, bitrate: 2_000_000 }, { height: 1080, width: 1920, bitrate: 4_000_000 }];
    currentLevel = -1;
    liveSyncPosition = 93;
    recoveries = 0;
    restarts = 0;
    sources = [];
    destroyed = 0;
    constructor(config) { super(); this.config = config; hls.push(this); }
    get autoLevelEnabled() { return this.currentLevel === -1; }
    get manualLevel() { return this.currentLevel; }
    loadSource(url) { this.sources.push(url); }
    attachMedia(video) { this.video = video; }
    startLoad() { this.restarts++; }
    recoverMediaError() { this.recoveries++; }
    destroy() { this.destroyed++; }
    fatal(type, details = 'fragLoadError', status) { this.emit('ERROR', 'ERROR', { fatal: true, type, details, response: status === undefined ? undefined : { code: status } }); }
  }
  class Dash extends Events {
    settings = [];
    interceptors = [];
    audio = [{ id: 'en', index: 0, lang: 'en', labels: [{ text: 'English' }] }, { id: 'nl', index: 1, lang: 'nl', labels: [] }];
    selectedAudio = this.audio[0];
    qualities = [{ id: 'low', width: 1280, height: 720, bandwidth: 2_000_000 }, { id: 'high', width: 1920, height: 1080, bandwidth: 4_000_000 }];
    resets = 0;
    liveSeeks = 0;
    dynamic = true;
    constructor() { super(); dash.push(this); }
    updateSettings(settings) { this.settings.push(settings); }
    addRequestInterceptor(fn) { this.interceptors.push(fn); }
    removeRequestInterceptor(fn) { this.interceptors = this.interceptors.filter((item) => item !== fn); }
    initialize(video, url, autoplay) { this.initialized = { video, url, autoplay }; }
    getTracksFor() { return this.audio; }
    getCurrentTrackFor() { return this.selectedAudio; }
    setCurrentTrack(track) { this.selectedAudio = track; }
    getRepresentationsByType() { return this.qualities; }
    setRepresentationForTypeById(type, id) { this.selectedQuality = { type, id }; }
    isDynamic() { return this.dynamic; }
    seekToOriginalLive() { this.liveSeeks++; }
    reset() { this.resets++; }
  }
  const MediaPlayer = () => ({ create: () => new Dash() });
  MediaPlayer.events = Object.fromEntries(['ERROR', 'PLAYBACK_ERROR', 'STREAM_INITIALIZED', 'PERIOD_SWITCH_COMPLETED', 'TRACK_CHANGE_RENDERED', 'NEW_TRACK_SELECTED', 'QUALITY_CHANGE_RENDERED'].map((name) => [name, name]));
  MediaPlayer.errors = { MANIFEST_LOADER_LOADING_FAILURE_ERROR_CODE: 11, FRAGMENT_LOADER_LOADING_FAILURE_ERROR_CODE: 17,
    MANIFEST_LOADER_PARSING_FAILURE_ERROR_CODE: 10, CAPABILITY_MEDIASOURCE_ERROR_CODE: 23, CAPABILITY_MEDIAKEYS_ERROR_CODE: 24,
    TIME_SYNC_FAILED_ERROR_CODE: 16, TIMED_TEXT_ERROR_ID_PARSE_CODE: 33, APPEND_ERROR_CODE: 20, MEDIASOURCE_TYPE_UNSUPPORTED_CODE: 35 };
  const mpegts = {
    supported: true,
    isSupported() { return this.supported; },
    Events: { ERROR: 'ERROR' }, ErrorTypes: Hls.ErrorTypes,
    createPlayer(source, config) {
      const instance = new Events();
      Object.assign(instance, { source, config, destroyed: 0, loads: 0,
        attachMediaElement(video) { this.video = video; }, load() { this.loads++; },
        destroy() { this.destroyed++; if (this.throwOnDestroy) throw new Error('teardown'); }
      });
      ts.push(instance);
      return instance;
    }
  };
  return { hls, dash, ts, Hls, Dash, MediaPlayer, mpegts,
    mocks: { 'hls.js': Hls, dashjs: { MediaPlayer }, 'mpegts.js': mpegts } };
}

function environment({ mse = true, managed = false, ua = 'Chrome Safari', memory = 2, mocks = {}, globals = {} } = {}) {
  const engine = engines();
  const window = {};
  if (mse) window.MediaSource = class {};
  if (managed) window.ManagedMediaSource = class {};
  const timerQueue = new Map();
  let timerId = 0;
  const capabilities = load('lib/capabilities.ts', {}, { window });
  const player = load('lib/player.ts', { './capabilities': capabilities, './playerRecovery': recovery, ...engine.mocks, ...mocks }, {
    window, navigator: { userAgent: ua, deviceMemory: memory },
    setTimeout(fn, delay) { const id = ++timerId; timerQueue.set(id, { fn, delay }); return id; },
    clearTimeout(id) { timerQueue.delete(id); }, ...globals
  });
  return { ...player, ...engine, timerQueue,
    runTimer() { const [id, timer] = timerQueue.entries().next().value; timerQueue.delete(id); timer.fn(); }
  };
}

test('missing and unknown MediaError codes are not proof of fatal decoding failure', () => {
  for (const code of [undefined, null, 0, 1, 2, 99]) assert.equal(recovery.classifyMediaError(code), 'retryable');
  for (const code of [3, 4]) assert.equal(recovery.classifyMediaError(code), 'fatal');
});

test('file handle stays callable, reload restores VOD position and paused state, and destroy is idempotent', () => {
  const { attachPlayback } = environment();
  const video = new Video();
  const handle = attachPlayback(video, '/movie.mp4');
  assert.equal(typeof handle, 'function');
  assert.equal(handle.destroy, handle);
  assert.equal(video.src, '/movie.mp4');
  assert.equal(handle.selectAudioTrack('0'), false);
  assert.equal(handle.selectQuality(null), false);
  assert.equal(handle.goLive(), false);
  video.currentTime = 73;
  handle.reload();
  video.emit('loadedmetadata');
  assert.equal(video.currentTime, 73);
  assert.equal(video.plays, 0);
  video.paused = false;
  handle.reload(400);
  video.emit('canplay');
  video.emit('loadedmetadata');
  assert.equal(video.currentTime, 179.9);
  assert.equal(video.plays, 1);
  handle();
  const loads = video.loads;
  handle.destroy();
  handle.reload();
  assert.equal(video.loads, loads);
  assert.equal(video.src, '');
  assert.equal([...video.handlers.values()].flat().length, 0);
});

test('native Safari HLS is preferred even with MSE and exposes native audio tracks', async () => {
  const e = environment({ ua: 'Macintosh AppleWebKit Version/18.0 Safari/605.1', managed: true });
  const video = new Video(); video.nativeHls = true;
  const tracks = Object.assign(new Events(), { 0: { id: 'en', label: 'English', language: 'en', enabled: true },
    1: { id: 'nl', label: 'Dutch', language: 'nl', enabled: false }, length: 2 });
  video.audioTracks = tracks;
  const snapshots = [];
  const handle = e.attachPlayback(video, '/master.M3U8', { onTracks: (value) => snapshots.push(value), live: true });
  await flush();
  assert.equal(e.hls.length, 0);
  assert.equal(video.src, '/master.M3U8');
  assert.equal(handle.selectAudioTrack('nl'), true);
  assert.equal(tracks[0].enabled, false);
  assert.equal(tracks[1].enabled, true);
  assert.equal(snapshots.at(-1).selectedAudioTrackId, 'nl');
  video.seekable = ranges([5, 90], [100, 110]);
  assert.equal(handle.goLive(), true);
  assert.equal(video.currentTime, 109.5);
  const stale = tracks.listeners('change')[0];
  handle();
  const count = snapshots.length;
  stale();
  assert.equal(snapshots.length, count);
  assert.equal([...tracks.handlers.values()].flat().length, 0);
});

test('HLS native fallback works without MSE, while Chrome prefers hls.js', async () => {
  for (const mse of [false, true]) {
    const e = environment({ mse }); const video = new Video(); video.nativeHls = true;
    const handle = e.attachPlayback(video, '/stream.m3u8');
    await flush();
    assert.equal(e.hls.length, mse ? 1 : 0);
    assert.equal(video.src, mse ? '' : '/stream.m3u8');
    handle();
  }
});

test('HLS with custom headers uses ManagedMediaSource-only Safari and forwards headers', async () => {
  const e = environment({ mse: false, managed: true, ua: 'iPhone Safari' });
  const video = new Video(); video.nativeHls = true;
  const headers = { Authorization: 'Bearer token' };
  const handle = e.attachPlayback(video, '/opaque', { transport: 'hls', requestHeaders: headers });
  headers.Authorization = 'changed';
  await flush();
  assert.equal(e.hls.length, 1);
  const sent = {};
  e.hls[0].config.xhrSetup({ setRequestHeader(name, value) { sent[name] = value; } });
  assert.deepEqual(sent, { Authorization: 'Bearer token' });
  assert.equal(e.hls[0].config.preferManagedMediaSource, true);
  assert.equal(video.disableRemotePlayback, true);
  handle();
  assert.equal(video.disableRemotePlayback, false);
});

test('HLS tracks and quality selection support Auto, unavailable ids, and reload persistence', async () => {
  const e = environment(); const video = new Video(); const snapshots = [];
  const handle = e.attachPlayback(video, '/stream.m3u8', { live: true, onTracks: (value) => snapshots.push(value) });
  assert.equal(handle.selectAudioTrack('0'), false);
  await flush();
  const first = e.hls[0]; first.emit('MANIFEST_PARSED');
  assert.equal(snapshots.at(-1).qualities[1].label, '1080p');
  assert.equal(handle.selectAudioTrack('1'), true);
  assert.equal(handle.selectQuality('1'), true);
  assert.equal(snapshots.at(-1).selectedQualityId, '1');
  assert.equal(handle.selectAudioTrack('garbage'), false);
  assert.equal(handle.selectQuality('01'), false);
  assert.equal(handle.goLive(), true);
  assert.equal(video.currentTime, 93);
  handle.reload(); await flush();
  const second = e.hls[1]; second.emit('MANIFEST_PARSED');
  assert.equal(first.destroyed, 1);
  assert.equal(second.audioTrack, 1);
  assert.equal(second.currentLevel, 1);
  assert.equal(handle.selectQuality(null), true);
  assert.equal(snapshots.at(-1).selectedQualityId, null);
  const count = snapshots.length;
  first.emit('LEVEL_SWITCHED'); first.fatal('media');
  assert.equal(snapshots.length, count);
  assert.equal(first.recoveries, 0);
  handle();
});

test('HLS uses lower finite RAM and per-request retry bounds', async () => {
  for (const memory of [undefined, 2, 8]) {
    const e = environment({ memory }); const handle = e.attachPlayback(new Video(), '/a.m3u8'); await flush();
    const config = e.hls[0].config;
    assert.ok(config.backBufferLength <= 30);
    assert.ok(config.maxMaxBufferLength <= 60);
    assert.ok(config.maxBufferSize <= 40 * 1024 * 1024);
    assert.equal(config.lowLatencyMode, false);
    for (const key of ['manifestLoadPolicy', 'playlistLoadPolicy', 'fragLoadPolicy', 'keyLoadPolicy']) {
      assert.equal(config[key].default.errorRetry.maxNumRetry, 2);
      assert.equal(config[key].default.timeoutRetry.maxNumRetry, 2);
    }
    handle();
  }
});

test('HLS recovers network twice with backoff, then reports one structured terminal error', async () => {
  const e = environment(); const errors = [];
  const handle = e.attachPlayback(new Video(), '/a.m3u8', { onError: (error) => errors.push(error) }); await flush();
  const hls = e.hls[0];
  hls.emit('ERROR', 'ERROR', { fatal: false, type: 'network' });
  assert.equal(e.timerQueue.size, 0);
  for (let attempt = 1; attempt <= 2; attempt++) {
    hls.fatal('network');
    hls.fatal('network');
    assert.equal(e.timerQueue.size, 1);
    assert.equal(e.timerQueue.values().next().value.delay, attempt * 500);
    e.runTimer();
  }
  assert.equal(hls.restarts, 2);
  assert.equal(errors.length, 0);
  hls.fatal('network'); hls.fatal('network');
  await flush();
  assert.equal(errors.length, 1);
  assert.deepEqual(plain(errors[0]), { transport: 'hls', kind: 'network', fatal: true, retryable: true, message: 'HLS playback recovery exhausted.', code: 'fragLoadError' });
  assert.equal(hls.destroyed, 1);
  assert.equal(e.timerQueue.size, 0);
  handle();
});

test('HLS manifest failure reloads the manifest and 403 fails without retry', async () => {
  const e = environment(); const errors = [];
  const handle = e.attachPlayback(new Video(), '/a.m3u8', { onError: (error) => errors.push(error) }); await flush();
  const hls = e.hls[0]; hls.fatal('network', 'manifestLoadError'); e.runTimer();
  assert.deepEqual(hls.sources, ['/a.m3u8', '/a.m3u8']);
  hls.fatal('network', 'manifestLoadError', 403);
  await flush();
  assert.equal(errors[0].retryable, false);
  assert.equal(e.timerQueue.size, 0);
  handle();
});

test('HLS has an independent media budget and never loops recovery indefinitely', async () => {
  const e = environment(); const errors = [];
  const handle = e.attachPlayback(new Video(), '/a.m3u8', { onError: (error) => errors.push(error) }); await flush();
  const hls = e.hls[0]; hls.fatal('network'); e.runTimer();
  for (let i = 0; i < 10; i++) hls.fatal('media', 'bufferAppendError');
  await flush();
  assert.equal(hls.recoveries, 2);
  assert.equal(errors.length, 1);
  assert.equal(errors[0].kind, 'media');
  assert.equal(errors[0].retryable, false);
  handle();
});

test('destroy and reload cancel pending HLS retries, including a queued stale timer callback', async () => {
  for (const action of ['destroy', 'reload']) {
    const e = environment(); const errors = []; const handle = e.attachPlayback(new Video(), '/a.m3u8', { onError: (error) => errors.push(error) }); await flush();
    const hls = e.hls[0]; hls.fatal('network'); const staleTimer = e.timerQueue.values().next().value.fn;
    handle[action](); staleTimer(); await flush();
    assert.equal(e.timerQueue.size, 0);
    assert.equal(hls.restarts, 0);
    assert.equal(errors.length, 0);
    handle();
  }
});

test('all dynamic engines ignore pending imports after destroy, replacement, or reload', async () => {
  for (const transport of ['hls', 'dash', 'mpegts']) {
    for (const action of ['destroy', 'replace', 'reload']) {
      const e = environment(); const video = new Video(); const errors = [];
      const handle = e.attachPlayback(video, '/opaque', { transport, onError: (error) => errors.push(error) });
      let replacement;
      if (action === 'replace') replacement = e.attachPlayback(video, '/new.mp4'); else handle[action]();
      await flush();
      assert.equal(e.hls.length + e.dash.length + e.ts.length, action === 'reload' ? 1 : 0);
      assert.equal(errors.length, 0);
      if (replacement) { handle(); assert.equal(video.src, '/new.mp4'); replacement(); } else handle();
    }
  }
});

test('rejected lazy imports only report errors for the current generation', async () => {
  for (const transport of ['hls', 'dash', 'mpegts']) {
    const moduleName = { hls: 'hls.js', dash: 'dashjs', mpegts: 'mpegts.js' }[transport];
    const module = {};
    Object.defineProperty(module, '__esModule', { get() { throw new Error('chunk unavailable'); } });
    for (const cancel of [false, true]) {
      const e = environment({ mocks: { [moduleName]: module } }); const errors = [];
      const handle = e.attachPlayback(new Video(), '/opaque', { transport, onError: (error) => errors.push(error) });
      if (cancel) handle();
      await flush();
      assert.equal(errors.length, cancel ? 0 : 1);
      if (!cancel) assert.equal(errors[0].code, 'ENGINE_LOAD_FAILED');
      handle();
    }
  }
});

test('DASH initializes lazily, forwards headers before initialization, and provides audio/quality/live controls', async () => {
  const e = environment({ mse: false, managed: true }); const video = new Video(); const snapshots = [];
  const handle = e.attachPlayback(video, '/opaque', { transport: 'dash', live: true, requestHeaders: { Authorization: 'secret' }, onTracks: (value) => snapshots.push(value) });
  assert.equal(e.dash.length, 0); await flush(); const dash = e.dash[0];
  assert.equal(dash.initialized.autoplay, false);
  assert.equal(dash.initialized.url, '/opaque');
  assert.equal(video.disableRemotePlayback, true);
  assert.equal(handle.selectQuality('high'), false);
  assert.equal(handle.goLive(), false);
  const request = await dash.interceptors[0]({ headers: { Range: 'bytes=1-2' } });
  assert.deepEqual(plain(request.headers), { Range: 'bytes=1-2', Authorization: 'secret' });
  dash.emit('STREAM_INITIALIZED');
  assert.equal(snapshots.at(-1).audioTracks[0].label, 'English');
  assert.equal(handle.selectAudioTrack('nl'), true);
  assert.equal(dash.selectedAudio.id, 'nl');
  assert.equal(handle.selectAudioTrack('missing'), false);
  assert.equal(handle.selectQuality('missing'), false);
  assert.equal(handle.selectQuality('high'), true);
  assert.deepEqual(dash.selectedQuality, { type: 'video', id: 'high' });
  assert.equal(dash.settings.at(-1).streaming.abr.autoSwitchBitrate.video, false);
  assert.equal(snapshots.at(-1).selectedQualityId, 'high');
  assert.equal(handle.selectQuality(null), true);
  assert.equal(dash.settings.at(-1).streaming.abr.autoSwitchBitrate.video, true);
  assert.equal(handle.goLive(), true);
  assert.equal(dash.liveSeeks, 1);
  dash.dynamic = false; assert.equal(handle.goLive(), false);
  const config = dash.settings[0].streaming;
  assert.equal(config.scheduling.scheduleWhilePaused, true);
  assert.equal(config.buffer.bufferTimeAtTopQualityLongForm, 20);
  assert.equal(config.retryAttempts.MediaSegment, 2);
  handle();
  assert.equal(video.disableRemotePlayback, false);
  assert.equal(dash.resets, 1);
  assert.equal(dash.interceptors.length, 0);
  assert.equal([...dash.handlers.values()].flat().length, 0);
});

test('DASH reload preserves VOD position/selections and ignores old engine callbacks', async () => {
  const e = environment(); const video = new Video(); const errors = [], snapshots = [];
  const handle = e.attachPlayback(video, '/a.MPD?token=x', { onError: (value) => errors.push(value), onTracks: (value) => snapshots.push(value) });
  await flush(); const first = e.dash[0]; first.emit('STREAM_INITIALIZED');
  handle.selectAudioTrack('nl'); handle.selectQuality('high'); video.currentTime = 41;
  const staleError = first.listeners('ERROR')[0], staleTracks = first.listeners('QUALITY_CHANGE_RENDERED')[0];
  handle.reload(); await flush(); e.dash[1].emit('STREAM_INITIALIZED'); video.emit('loadedmetadata');
  assert.equal(video.currentTime, 41);
  assert.equal(e.dash[1].selectedAudio.id, 'nl');
  assert.equal(e.dash[1].selectedQuality.id, 'high');
  const count = snapshots.length;
  staleError({ error: { code: 11 } }); staleTracks();
  assert.equal(errors.length, 0); assert.equal(snapshots.length, count);
  assert.equal(handle.goLive(), false);
  handle();
});

test('DASH terminal errors are classified and reset exactly once', async () => {
  for (const [code, kind, retryable] of [[11, 'network', true], [17, 'network', true], [23, 'unsupported', false]]) {
    const e = environment(); const errors = [];
    const handle = e.attachPlayback(new Video(), '/a.mpd', { onError: (error) => errors.push(error) }); await flush();
    const dash = e.dash[0]; const listener = dash.listeners('ERROR')[0];
    listener({ error: { code } }); listener({ error: { code } });
    await flush();
    assert.equal(errors.length, 1); assert.equal(errors[0].kind, kind); assert.equal(errors[0].retryable, retryable);
    assert.equal(dash.resets, 1); handle(); assert.equal(dash.resets, 1);
  }
});

test('raw TS supports live and VOD with different latency/loading settings and forwards headers', async () => {
  for (const live of [false, true]) {
    const e = environment({ mse: false, managed: true }); const video = new Video();
    const handle = e.attachPlayback(video, '/movie.TS?auth=x', { live, requestHeaders: { Authorization: 'secret' } }); await flush();
    const player = e.ts[0];
    assert.equal(player.source.isLive, live);
    assert.equal(player.config.liveBufferLatencyChasing, live);
    assert.equal(player.config.liveBufferLatencyChasingOnPaused, live);
    assert.equal(video.disableRemotePlayback, true);
    assert.equal(player.config.lazyLoad, !live);
    assert.equal(player.config.autoCleanupSourceBuffer, true);
    assert.equal(player.config.lazyLoadMaxDuration, 20);
    assert.equal(player.config.autoCleanupMaxBackwardDuration, 20);
    assert.equal(player.config.headers.Authorization, 'secret');
    video.buffered = ranges([0, 60]);
    assert.equal(handle.goLive(), live);
    handle(); assert.equal(player.destroyed, 1); assert.equal(video.disableRemotePlayback, false);
  }
});

test('explicit hints override inference; encoded URLs and extensionless live TS remain supported', async () => {
  for (const [url, options, expected] of [
    ['/movie.m3u8', { transport: 'file' }, 'file'],
    ['/opaque', { transport: 'mpegts' }, 'ts'],
    ['/opaque', { transport: 'dash' }, 'dash'],
    ['https://example.test/live/user/pass/123', { live: true }, 'ts'],
    ['https://example.test/live/user/pass/123', { live: false }, 'file'],
    ['/proxy?url=https%3A%2F%2Fexample.test%2Fa.TS%3Ftoken%3Dx', {}, 'ts'],
    ['/proxy?url=https%3A%2F%2Fexample.test%2Fa.M3U8%3Ftoken%3Dx', {}, 'hls']
  ]) {
    const e = environment(); const video = new Video(); const handle = e.attachPlayback(video, url, options); await flush();
    if (expected === 'file') assert.equal(video.src, url); else assert.equal(e[expected].length, 1);
    handle();
  }
});

test('MPEG-TS stale errors are ignored and throwing teardown still clears the element', async () => {
  const e = environment(); const video = new Video(); const errors = [];
  const handle = e.attachPlayback(video, '/a.ts', { onError: (error) => errors.push(error) }); await flush();
  const player = e.ts[0]; const stale = player.listeners('ERROR')[0]; player.throwOnDestroy = true;
  handle(); stale('network', 'timeout');
  assert.equal(errors.length, 0); assert.equal(video.src, '');
  assert.equal(player.listeners('ERROR').length, 0);
});

test('unsupported transports fail once; native headers are never silently ignored', async () => {
  for (const transport of ['hls', 'dash', 'mpegts', 'file']) {
    const e = environment({ mse: false }); const video = new Video(); const errors = [];
    const handle = e.attachPlayback(video, '/opaque', { transport, requestHeaders: { Authorization: 'secret' }, onError: (error) => errors.push(error) });
    await flush();
    assert.equal(errors.length, 1); assert.equal(errors[0].kind, 'unsupported'); assert.equal(video.src, '');
    handle();
  }
});

test('unsupported hls.js falls back to native only without custom headers', async () => {
  for (const requestHeaders of [undefined, { Authorization: 'secret' }]) {
    const e = environment(); e.Hls.supported = false; const video = new Video(); video.nativeHls = true; const errors = [];
    const handle = e.attachPlayback(video, '/a.m3u8', { requestHeaders, onError: (error) => errors.push(error) }); await flush();
    assert.equal(video.src, requestHeaders ? '' : '/a.m3u8');
    assert.equal(errors.length, requestHeaders ? 1 : 0); handle();
  }
});

test('native error events ignore absent MediaError and preserve zero-argument callback compatibility', async () => {
  const e = environment(); const video = new Video(); let errors = 0;
  const handle = e.attachPlayback(video, '/a.mp4', { onError: () => { errors++; } });
  video.emit('error'); assert.equal(errors, 0);
  video.error = { code: 3 }; video.emit('error'); video.emit('error');
  await flush();
  assert.equal(errors, 1); handle();
});

test('DASH optional text and clock warnings do not terminate media playback', async () => {
  const e = environment(); const errors = [];
  const handle = e.attachPlayback(new Video(), '/a.mpd', { onError: (error) => errors.push(error) }); await flush();
  const dash = e.dash[0];
  for (const error of [{ code: 16 }, { code: 33 }, 'cc', undefined]) dash.emit('ERROR', { error });
  dash.emit('PLAYBACK_ERROR', { error: undefined });
  await flush();
  assert.equal(dash.resets, 0); assert.equal(errors.length, 0);
  dash.emit('PLAYBACK_ERROR', { error: { code: 3 } }); await flush();
  assert.equal(errors.length, 1); assert.equal(errors[0].kind, 'media'); assert.equal(errors[0].retryable, false);
  assert.equal(dash.resets, 1); handle();
});

test('terminal callbacks are deferred until a handle exists and cancelled with the session', async () => {
  const e = environment({ mse: false }); const video = new Video(); let calls = 0;
  const handle = e.attachPlayback(video, '/a.mpd', { onError: () => { calls++; } });
  assert.equal(calls, 0); handle(); await flush(); assert.equal(calls, 0);
  let returnedHandle;
  returnedHandle = e.attachPlayback(video, '/a.mpd', { onError: () => { assert.equal(typeof returnedHandle, 'function'); calls++; } });
  await flush(); assert.equal(calls, 1); returnedHandle();
});

const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load } = require('./load.cjs');

const flush = () => new Promise(setImmediate);
const capabilities = { mse: true, nativeHls: false, h264: true, aac: true, hevc: false,
  hevc10: false, dolbyVision: false, av1: false, vp9: false, ac3: false, eac3: false, opus: false, flac: false };
const server = { id: 'server', type: 'jellyfin', name: 'Library', enabled: true,
  url: 'https://original.example', token: 'original-token', userId: 'original-user' };
const settings = { homeServers: [server], defaultPlayer: 'browser' };
const file = () => ({ source: 'Fixture', addonName: 'Library', url: 'https://original.example/Videos/item/stream.mp4',
  transport: 'file', media: { container: 'mp4', videoCodec: 'h264', audioCodec: 'aac' } });
const homeStream = () => ({ ...file(), homeServer: { serverId: server.id, itemId: 'item', mediaSourceId: 'version' } });
const preparedStream = (id = 'session') => ({ ...homeStream(),
  playbackSession: { serverId: server.id, itemId: 'item', mediaSourceId: 'version', sessionId: id, transcoding: false, startOffset: 0 } });
function deferred() {
  let resolve, reject;
  const promise = new Promise((yes, no) => { resolve = yes; reject = no; });
  return { promise, resolve, reject };
}

function preparation(overrides = {}) {
  const calls = [];
  const debrid = {
    cachedDebridDirectUrl: () => null,
    parseDebridStream: () => null,
    resolveDebridDirectUrl: async () => ({ url: 'https://cdn.example/file.mkv' }),
    resolveTranscodeStream: async () => ({ url: 'https://cdn.example/master.m3u8' }),
    ...overrides.debrid
  };
  const compatibility = load('lib/streamCompatibility.ts', { './capabilities': { getMediaCapabilities: () => capabilities }, './debrid': debrid });
  return {
    calls,
    ...load('lib/prepareBrowserStream.ts', {
      './debrid': debrid,
      './streamCompatibility': compatibility,
      './homeServerPlayback': { prepareHomeServerPlayback: async (...args) => {
        calls.push(args);
        return overrides.home ? overrides.home(...args) : preparedStream();
      } }
    }, { DOMException, Error })
  };
}

// Execute the actual callback/effect rather than a manually copied version. This
// intentionally excludes unrelated React rendering and other provider state.
function extracted(relative, selector, globals) {
  const filename = path.resolve(__dirname, '..', relative);
  const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  const matches = [];
  const visit = (node) => { const match = selector(node, source); if (match) matches.push(match); ts.forEachChild(node, visit); };
  visit(source);
  assert.equal(matches.length, 1, `Expected one integration callback in ${relative}`);
  const code = ts.transpileModule(`module.exports = (${matches[0].getText(source)});`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, { module, Error, Event, DOMException, AbortController, console, ...globals }, { filename });
  return module.exports;
}

function conversionRecoveryHarness(overrides = {}) {
  const state = { errors: [], details: [], selections: [], hops: 0, destroyed: 0, resolutions: 0 };
  const video = Object.assign(new EventTarget(), { readyState: 4, currentTime: 420, duration: 3600, paused: false, ended: false, seeking: false,
    pause() { this.paused = true; }, play() { this.paused = false; return Promise.resolve(); },
    removeAttribute() { this.currentTime = 0; this.readyState = 0; }, load() {} });
  const timers = new Map();
  const stream = { ...file(), remux: true, originalUrl: 'https://provider.example/selected-file', ...overrides.stream };
  const resumeAtRef = { current: stream.resumePositionSeconds ?? 0 };
  let options;
  let transport;
  const prepared = { probe: { videoPlayable: true, audioTracks: [], chosenAudioIndex: -1 },
    start: async () => {}, destroy: () => { state.destroyed++; video.removeAttribute('src'); }, ...overrides.prepared };
  const noop = () => {};
  const globals = {
    stream, settings, videoRef: { current: video }, resumeAtRef, transportRef: { current: null },
    liveTv: false, playbackRate: 1, config: { allowNetlifyMediaProxy: false },
    setError: (value) => state.errors.push(value), setErrorDetail: (value) => state.details.push(value),
    setBuffering: noop, setShowControls: noop, setActiveSubtitle: noop, setRemuxTracks: noop,
    setRemuxAudioIndex: noop, setTransportTracks: noop, defaultSubtitleIndex: () => -1,
    lastSavedRef: { current: 0 }, remuxAudioIndexRef: { current: -1 }, REMUX_STUCK_TICKS: 3,
    onToast: noop, canProviderTranscode: () => overrides.canConvert ?? true,
    canTryRemux: () => false, hasDolbyVision: () => false, recordBrowserPlaybackFailure: noop,
    tryNextSource: () => { state.hops++; return !!overrides.autoSelect; },
    parseDebridStream: () => ({ provider: 'torbox' }), invalidateDebridDirectUrl: noop,
    resolveDebridDirectUrl: async () => { state.resolutions++; return { url: 'https://cdn.example/original.mkv' }; },
    classifyMediaError: load('lib/playerRecovery.ts').classifyMediaError,
    selectStream: (next, opts) => state.selections.push({ next, options: opts }),
    attachPlayback: (_video, _url, opts) => { transport = opts; return () => { video.removeAttribute('src'); }; },
    window: { setInterval: (fn) => { timers.set(fn, 'interval'); return fn; }, clearInterval: (fn) => timers.delete(fn),
      setTimeout: (fn) => { timers.set(fn, 'timeout'); return fn; }, clearTimeout: (fn) => timers.delete(fn) },
    require(name) {
      assert.equal(name, '@/lib/remux');
      return { probeAndPrepareRemux: async (...args) => {
        options = args[3];
        return overrides.probe ? overrides.probe(options, prepared) : prepared;
      } };
    }
  };
  globals.onSelectStream = extracted('components/player/PlayerOverlay.tsx', node =>
    ts.isVariableDeclaration(node) && node.name.getText() === 'onSelectStream' && ts.isCallExpression(node.initializer)
      ? node.initializer.arguments[0] : undefined, globals);
  const setup = extracted('components/player/PlayerOverlay.tsx', (node, source) =>
    ts.isCallExpression(node) && node.expression.getText(source) === 'useEffect' && ts.isArrowFunction(node.arguments[0])
      && node.arguments[0].getText(source).includes('const remuxFailed') ? node.arguments[0] : undefined, globals);
  return { state, video, prepared, resumeAtRef, setup,
    error: message => options.onError(message), transportError: error => transport.onError(error),
    tick: () => { for (const [fn, kind] of [...timers]) if (kind === 'interval') fn(); },
    emit: event => video.dispatchEvent(new Event(event)) };
}

test('remux start rejection requests conversion of the same file before hopping sources', async () => {
  const h = conversionRecoveryHarness({ autoSelect: true, prepared: { start: async () => { throw new Error('Decoder rejected sample'); } } });
  const cleanup = h.setup(); await flush();
  assert.equal(h.state.selections.length, 1);
  assert.equal(h.state.selections[0].next.originalUrl, 'https://provider.example/selected-file');
  assert.equal(h.state.selections[0].next.resumePositionSeconds, 420);
  assert.equal(h.state.selections[0].options.forceTranscode, true);
  assert.equal(h.state.hops, 0);
  assert.ok(h.state.destroyed > 0);
  cleanup();
});

test('runtime callback and rejected start cannot request duplicate conversions', async () => {
  const pending = deferred();
  const h = conversionRecoveryHarness({ prepared: { start: () => pending.promise } });
  const cleanup = h.setup(); await flush();
  h.error('Decode error'); h.error('Duplicate decode error'); pending.reject(new Error('start failed'));
  await flush();
  assert.equal(h.state.selections.length, 1);
  assert.equal(h.state.hops, 0);
  assert.equal(h.state.errors.includes(true), false, 'Do not publish a terminal failure while conversion is being selected');
  cleanup();
});

test('a probe failure preserves the pending resume position instead of replacing it with zero', async () => {
  const h = conversionRecoveryHarness({ stream: { resumePositionSeconds: 930 },
    probe: async (options) => { options.onError('Metadata unavailable'); return null; } });
  h.video.readyState = 0; h.video.currentTime = 0;
  const cleanup = h.setup(); await flush();
  assert.equal(h.state.selections.length, 1);
  assert.equal(h.state.selections[0].next.resumePositionSeconds, 930);
  cleanup();
});

test('unsupported probe codecs and exhausted remux watchdogs both use provider conversion', async () => {
  for (const phase of ['probe', 'watchdog']) {
    const h = conversionRecoveryHarness(phase === 'probe'
      ? { prepared: { probe: { videoPlayable: false, videoReason: 'Unsupported profile', audioTracks: [], chosenAudioIndex: -1 } } } : {});
    const cleanup = h.setup(); await flush();
    if (phase === 'watchdog') for (let n = 0; n < 5; n++) h.tick();
    assert.equal(h.state.selections.length, 1, phase);
    assert.equal(h.state.selections[0].options.forceTranscode, true);
    cleanup();
  }
});

test('closing or switching sources cancels late remux failure recovery', async () => {
  const pending = deferred();
  const h = conversionRecoveryHarness({ prepared: { start: () => pending.promise } });
  const cleanup = h.setup(); await flush(); cleanup();
  h.error('Late decoder event'); pending.reject(new Error('aborted')); await flush();
  assert.equal(h.state.selections.length, 0);
  assert.equal(h.state.hops, 0);
  assert.equal(h.state.errors.includes(true), false);
});

test('no provider conversion or a previously converted file ends without a conversion loop', async () => {
  for (const input of [{ canConvert: false }, { stream: { transcoded: true } }]) {
    const h = conversionRecoveryHarness(input);
    const cleanup = h.setup(); await flush();
    h.error('No compatible decoder'); h.error('Duplicate failure');
    assert.equal(h.state.selections.length, 0);
    assert.equal(h.state.hops, 1);
    assert.equal(h.state.details.at(-1), 'No compatible decoder');
    assert.equal(h.state.errors.at(-1), true);
    cleanup();
  }
});

test('a fatal decoder error during direct playback converts without losing its playhead', async () => {
  const h = conversionRecoveryHarness({ stream: { remux: false, playbackSession: { startOffset: 100 } } });
  const cleanup = h.setup(); h.emit('playing');
  h.transportError({ kind: 'media', fatal: true, message: 'Decode failed' });
  h.transportError({ kind: 'media', fatal: true, message: 'Duplicate' });
  assert.equal(h.state.selections.length, 1);
  assert.equal(h.state.selections[0].next.resumePositionSeconds, 520);
  assert.equal(h.state.selections[0].options.forceTranscode, true);
  assert.equal(h.state.resolutions, 0);
  cleanup();
});

test('a mid-playback network failure is not treated as a codec failure requiring conversion', () => {
  const h = conversionRecoveryHarness({ stream: { remux: false } });
  const cleanup = h.setup(); h.emit('playing');
  h.transportError({ kind: 'network', fatal: true, message: 'Connection interrupted' });
  assert.equal(h.state.selections.length, 0);
  assert.equal(h.state.errors.includes(true), false);
  cleanup();
});

test('failed converted HLS never refreshes back to the incompatible original CDN file', () => {
  const h = conversionRecoveryHarness({ stream: { remux: false, transcoded: true, transport: 'hls' } });
  const cleanup = h.setup();
  h.transportError({ kind: 'media', fatal: true, message: 'Converted HLS failed' });
  assert.equal(h.state.resolutions, 0);
  assert.equal(h.state.selections.length, 0);
  assert.equal(h.state.errors.at(-1), true);
  cleanup();
});

function storeHarness(prepare, report = async () => {}, overrides = {}) {
  const state = { active: null, accepted: [], toasts: [], timers: new Map() };
  const globals = {
    playbackPreparation: { current: null }, playbackGeneration: { current: 0 }, ownedPlayback: { current: null },
    activeProfileIdRef: { current: 'profile-a' }, settingsRef: { current: settings },
    authClient: { session: { userId: 'account-a' } }, selected: { title: 'Fixture' }, activeProfile: { id: 'profile-a' }, selectedEpisode: null,
    prepareBrowserStream: prepare, reportHomeServerPlayback: report,
    setActiveChannel: () => {}, setActiveStream: (value) => { state.active = value; state.accepted.push(value); },
    setToast: (value) => state.toasts.push(value),
    openLiveExternally: () => false, recordChannelPlayback: () => {}, buildXtreamCatchupUrl: () => 'https://iptv.example/archive.m3u8',
    window: { setTimeout: (fn) => { const id = state.timers.size + 1; state.timers.set(id, fn); return id; }, clearTimeout: (id) => state.timers.delete(id) },
    ...overrides
  };
  const select = (name) => (node) => ts.isVariableDeclaration(node) && node.name.getText() === name
    && ts.isCallExpression(node.initializer) ? node.initializer.arguments[0] : undefined;
  globals.stopOwnedPlayback = extracted('lib/store.tsx', select('stopOwnedPlayback'), globals);
  const profileEffect = extracted('lib/store.tsx', (node, source) => ts.isCallExpression(node)
    && node.expression.getText(source) === 'useEffect' && ts.isArrowFunction(node.arguments[0])
    && node.arguments[0].getText(source).includes('stopOwnedPlayback()') ? node.arguments[0] : undefined, globals);
  return { state, globals,
    play: extracted('lib/store.tsx', select('playStream'), globals),
    close: extracted('lib/store.tsx', select('closePlayer'), globals),
    playChannel: extracted('lib/store.tsx', select('playChannel'), globals),
    playCatchup: extracted('lib/store.tsx', select('playCatchup'), globals),
    profileCleanup: profileEffect()
  };
}

function sessionEffect(stream, report, update = () => {}) {
  const video = new EventTarget();
  Object.assign(video, { readyState: 1, currentTime: 0, duration: 300, paused: true });
  const timers = new Set();
  const selections = [];
  const resumeAtRef = { current: 0 };
  const effect = extracted('components/player/PlayerOverlay.tsx', (node, source) =>
    ts.isCallExpression(node) && node.expression.getText(source) === 'useEffect'
      && ts.isArrowFunction(node.arguments[0]) && node.arguments[0].getText(source).includes('reportHomeServerPlayback(')
      ? node.arguments[0] : undefined, {
    stream, settings, videoRef: { current: video }, reportHomeServerPlayback: report,
    updateHomeServerPlaybackPosition: update, resumeAtRef,
    onSelectStream: (...args) => selections.push(args),
    window: { setInterval: (fn) => { timers.add(fn); return fn; }, clearInterval: (fn) => timers.delete(fn) }
  });
  const terminalFailureEffect = extracted('components/player/PlayerOverlay.tsx', (node, source) =>
    ts.isCallExpression(node) && node.expression.getText(source) === 'useEffect' && ts.isArrowFunction(node.arguments[0])
      && node.arguments[0].getText(source).includes('dispatchEvent(new Event("arvio-playback-failed"))') ? node.arguments[0] : undefined,
    { error: true, videoRef: { current: video } });
  return { video, selections, resumeAtRef, setup: effect, fail: terminalFailureEffect,
    tick: () => { for (const fn of timers) fn(); }, emit: (name) => video.dispatchEvent(new Event(name)) };
}

function progressEffect(overrides = {}) {
  const video = new EventTarget();
  Object.assign(video, { currentTime: 10, duration: 1000, paused: true, readyState: 4 });
  const window = new EventTarget();
  const document = Object.assign(new EventTarget(), { visibilityState: 'visible' });
  const calls = [];
  const scrobbles = [], watched = [], toasts = [];
  const authClient = { session: { userId: 'account-a' } };
  let now = 1_000_000;
  const setup = extracted('components/player/PlayerOverlay.tsx', (node, source) =>
    ts.isCallExpression(node) && node.expression.getText(source) === 'useEffect'
      && ts.isArrowFunction(node.arguments[0]) && node.arguments[0].getText(source).includes('lastQueuedPosition')
      ? node.arguments[0] : undefined, {
    videoRef: { current: video }, item: { id: 1, title: 'Episode', mediaType: 'tv' },
    stream: { addonId: 'fixture', addonName: 'Fixture' }, addons: [], activeProfileId: 'profile-a',
    selectedEpisode: { season: 1, episode: 3 }, authClient,
    Date: { now: () => now }, window, document, lastSavedRef: { current: 0 },
    isLiveStreamOrSportsItem: () => false, config: {}, settings: { autoPlayNext: false },
    liveTv: false, onToast: message => toasts.push(message),
    syncClient: profile => ({ scrobble: async (action, item) => scrobbles.push({ profile, action, item }) }),
    saveWatchedState: async (...args) => watched.push(args),
    saveProgress: async (...args) => { calls.push(args); }, ...overrides
  });
  const cleanup = setup();
  return { video, window, document, calls, authClient, scrobbles, watched, toasts, cleanup,
    advance: (seconds) => { now += seconds * 1000; video.currentTime += seconds; },
    emit: (name) => video.dispatchEvent(new Event(name)) };
}

test('Progress cloud checkpoints are once per minute, not every 15 seconds', () => {
  const h = progressEffect();
  h.emit('timeupdate');
  assert.equal(h.calls.length, 1);
  for (let n = 0; n < 3; n++) { h.advance(15); h.emit('timeupdate'); }
  assert.equal(h.calls.length, 1);
  h.advance(15); h.emit('timeupdate');
  assert.equal(h.calls.length, 2);
  h.cleanup();
  assert.equal(h.calls.length, 2, 'unchanged teardown position is not saved twice');
});

test('Pause, background, pagehide, end and close bypass periodic checkpoint throttling', () => {
  for (const event of ['pause', 'background', 'pagehide', 'ended', 'close']) {
    const h = progressEffect();
    h.emit('timeupdate');
    h.advance(3);
    if (event === 'background') { h.document.visibilityState = 'hidden'; h.document.dispatchEvent(new Event('visibilitychange')); }
    else if (event === 'pagehide') h.window.dispatchEvent(new Event('pagehide'));
    else if (event === 'close') h.cleanup();
    else h.emit(event);
    assert.equal(h.calls.length, 2, event);
    assert.equal(h.calls[1][1].position_seconds, 13, event);
    assert.equal(h.calls[1][1].episode, 3);
    if (event !== 'close') h.cleanup();
  }
});

test('Progress cleanup cannot save the former profile into a new account', () => {
  const h = progressEffect();
  h.emit('timeupdate'); h.advance(5);
  h.authClient.session.userId = 'account-b';
  h.cleanup();
  assert.equal(h.calls.length, 1);
});

test('browser episode playback sends start, pause, resume and exactly one completed stop', () => {
  const h = progressEffect();
  h.video.paused = false; h.emit('playing'); h.emit('playing');
  h.video.currentTime = 330; h.video.paused = true; h.emit('pause');
  h.video.paused = false; h.emit('playing');
  h.video.currentTime = 1000; h.video.ended = true; h.video.paused = true;
  h.emit('pause'); h.emit('ended'); h.emit('arvio-tracking-stop'); h.cleanup();
  assert.deepEqual(h.scrobbles.map(c => [c.action, c.item.progress]), [['start', 1], ['pause', 33], ['start', 33], ['stop', 100]]);
  assert.ok(h.scrobbles.every(c => c.profile === 'profile-a' && c.item.tmdbId === 1 && c.item.season === 1 && c.item.episode === 3));
  assert.equal(h.watched.length, 1);
  assert.equal(h.watched[0][3], 'profile-a');
});

test('hiding and returning to a playing tab resumes tracking without extra heartbeat calls', () => {
  const h = progressEffect();
  h.video.paused = false; h.emit('playing');
  for (let n = 0; n < 20; n++) { h.advance(1); h.emit('timeupdate'); h.emit('seeked'); }
  h.document.visibilityState = 'hidden'; h.document.dispatchEvent(new Event('visibilitychange'));
  h.document.visibilityState = 'visible'; h.document.dispatchEvent(new Event('visibilitychange'));
  assert.deepEqual(h.scrobbles.map(c => c.action), ['start', 'pause', 'start']);
  h.cleanup();
});

test('closing before completion remains resumable; closing during credits marks watched', () => {
  for (const position of [450, 850, 950]) {
    const h = progressEffect();
    h.video.paused = false; h.emit('playing'); h.video.currentTime = position;
    h.emit('arvio-tracking-stop'); h.cleanup();
    assert.equal(h.scrobbles.at(-1).action, position >= 900 ? 'stop' : 'pause');
    assert.equal(h.scrobbles.at(-1).item.progress, position / 10);
    assert.equal(h.scrobbles.length, 2);
    assert.equal(h.watched.length, position >= 900 ? 1 : 0);
  }
});

test('re-render cleanup is a pause, not a watched stop during credits', () => {
  const h = progressEffect();
  h.video.paused = false; h.emit('playing'); h.video.currentTime = 950; h.cleanup();
  assert.equal(h.scrobbles.at(-1).action, 'pause');
  assert.equal(h.watched.length, 0);
});

test('tracking and cloud checkpoints include provider conversion start offsets', () => {
  const h = progressEffect({ stream: { addonName: 'Jellyfin', playbackSession: { startOffset: 500 } } });
  h.video.paused = false; h.video.currentTime = 250; h.emit('playing'); h.emit('pause');
  assert.equal(h.scrobbles[0].item.progress, 50);
  assert.equal(h.calls[0][1].position_seconds, 750);
  assert.equal(h.calls[0][1].duration_seconds, 1500);
  h.video.duration = NaN; h.video.currentTime = 0; h.cleanup();
  assert.equal(h.calls.length, 1, 'destroyed media state cannot overwrite the captured resume position');
});

test('unknown home-server IDs are never submitted to trackers as TMDB IDs', () => {
  const h = progressEffect({ item: { id: 991231, isHomeServer: true, title: 'Private file', mediaType: 'movie' } });
  h.video.paused = false; h.emit('playing'); h.emit('arvio-tracking-stop'); h.cleanup();
  assert.equal(h.scrobbles.length, 0);
  assert.equal(h.calls.length, 1, 'ARVIO cloud progress still saves');
});

test('mapped home-server titles scrobble their TMDB ID instead of local server ID', () => {
  const h = progressEffect({ item: { id: 991231, tmdbId: 42, isHomeServer: true, title: 'Mapped file', mediaType: 'movie' } });
  h.video.paused = false; h.emit('playing'); h.emit('arvio-tracking-stop'); h.cleanup();
  assert.equal(h.scrobbles[0].item.tmdbId, 42);
});

test('live TV and playback that never starts do not create watched history', () => {
  for (const liveTv of [true, false]) {
    const h = progressEffect({ liveTv });
    if (liveTv) { h.video.paused = false; h.emit('playing'); }
    h.video.currentTime = 1000; h.emit('ended'); h.emit('arvio-tracking-stop'); h.cleanup();
    assert.equal(h.scrobbles.length, 0);
    assert.equal(h.watched.length, 0);
  }
});

test('an old player cannot mark watched after switching accounts', () => {
  const h = progressEffect();
  h.video.paused = false; h.emit('playing'); h.authClient.session.userId = 'account-b';
  h.video.currentTime = 1000; h.emit('ended'); h.cleanup();
  assert.equal(h.scrobbles.length, 1);
  assert.equal(h.watched.length, 0);
  assert.equal(h.calls.length, 0);
});

test('tracker failures are reported once, without blocking cloud progress or retry polling', async () => {
  const h = progressEffect({ syncClient: () => ({ scrobble: async () => { throw new Error('HTTP 401'); } }) });
  h.video.paused = false; h.emit('playing'); h.emit('pause'); await flush();
  assert.equal(h.toasts.length, 1);
  assert.match(h.toasts[0], /tracking service/);
  assert.equal(h.calls.length, 1);
  h.cleanup();
});

function actualHomeApi(respond = async () => '') {
  const calls = [];
  const http = {
    proxiedUrl: (url, headers) => JSON.stringify({ url, headers }),
    jsonRequest: async () => ({ PlaySessionId: 'remembered-session', MediaSources: [{
      Id: 'version', Container: 'mp4', SupportsDirectPlay: true,
      MediaStreams: [{ Type: 'Video', Codec: 'h264' }, { Type: 'Audio', Codec: 'aac' }]
    }] }),
    textRequest: async (target, init = {}) => {
      const call = { ...JSON.parse(target), method: init.method, body: init.body ? JSON.parse(init.body) : undefined };
      calls.push(call);
      return respond(call);
    }
  };
  const homeserver = load('lib/homeserver.ts', { './http': http });
  return { calls, ...load('lib/homeServerPlayback.ts', { './http': http, './homeserver': homeserver,
    './capabilities': { getMediaCapabilities: () => capabilities } }, { Error }) };
}

test('preparation routes direct files without allocating a home-server session', async () => {
  const h = preparation();
  const result = await h.prepareBrowserStream(file(), settings);
  assert.equal(result.url, file().url);
  assert.equal(result.remux, false);
  assert.equal(h.calls.length, 0);
});

test('home-server preparation passes exact context, config, transcode intent and signal', async () => {
  const h = preparation();
  const controller = new AbortController();
  const selected = homeStream();
  const result = await h.prepareBrowserStream(selected, settings, { forceTranscode: true, signal: controller.signal });
  assert.equal(result.playbackSession.sessionId, 'session');
  assert.equal(h.calls[0][0], selected);
  assert.equal(h.calls[0][1], settings);
  assert.equal(h.calls[0][2].signal, controller.signal);
  assert.equal(h.calls[0][2].forceTranscode, true);
});

test('preparation preserves the actual home-server rejection instead of trying debrid conversion', async () => {
  const failure = new Error('The selected media version is no longer available. Refresh the sources.');
  const h = preparation({ home: async () => { throw failure; } });
  await assert.rejects(h.prepareBrowserStream(homeStream(), settings), (error) => error === failure);
});

test('missing URLs and pre-aborted requests are rejected before provider calls', async () => {
  const h = preparation();
  await assert.rejects(h.prepareBrowserStream({ ...file(), url: null }, settings), /no playback URL/);
  await assert.rejects(h.prepareBrowserStream(homeStream(), settings, { signal: AbortSignal.abort() }), { name: 'AbortError' });
  assert.equal(h.calls.length, 0);
});

test('remux resolves an uncached debrid URL and preserves the original provider URL', async () => {
  const h = preparation({ debrid: { parseDebridStream: () => ({ provider: 'torbox' }) } });
  const input = { ...file(), url: 'https://provider.example/file.mkv', media: { container: 'mkv', videoCodec: 'h264', audioCodec: 'dts' } };
  const result = await h.prepareBrowserStream(input, settings);
  assert.equal(result.url, 'https://cdn.example/file.mkv');
  assert.equal(result.originalUrl, input.url);
  assert.equal(result.remux, true);
});

test('provider HLS conversion preserves the selected file identity but drops original request headers/codecs', async () => {
  const requests = [];
  const info = { provider: 'torbox', infoHash: 'selected-hash', fileIndex: 3 };
  const h = preparation({ debrid: { parseDebridStream: () => info,
    resolveTranscodeStream: async (input) => { requests.push(input); return { url: 'https://cdn.example/master.m3u8' }; }
  } });
  const input = { ...file(), originalUrl: 'https://addon.example/selected-file', resumePositionSeconds: 420,
    media: { container: 'mkv', videoCodec: 'hevc', audioCodec: 'truehd', hdr: 'dv' },
    behaviorHints: { filename: 'original-DV.mkv', proxyHeaders: { request: { Authorization: 'original-header', Referer: 'https://addon.example' } } }
  };
  const result = await h.prepareBrowserStream(input, settings, { forceTranscode: true });
  assert.equal(requests.length, 1);
  assert.equal(requests[0], info);
  assert.equal(result.originalUrl, input.originalUrl);
  assert.equal(result.url, 'https://cdn.example/master.m3u8');
  assert.equal(result.transcoded, true);
  assert.equal(result.remux, false);
  assert.equal(result.transport, 'hls');
  assert.equal(result.resumePositionSeconds, 420);
  assert.equal(result.media, undefined);
  assert.equal(result.behaviorHints.proxyHeaders, undefined);
  assert.equal(input.behaviorHints.proxyHeaders.request.Authorization, 'original-header', 'Original source remains unchanged');
});

test('reopening converted HLS does not request conversion again or follow the old direct URL', async () => {
  let requests = 0;
  const h = preparation({ debrid: { parseDebridStream: () => ({ provider: 'torbox' }),
    resolveTranscodeStream: async () => { requests++; return { url: 'https://cdn.example/master.m3u8' }; }
  } });
  const input = { ...file(), url: 'https://cdn.example/master.m3u8', originalUrl: 'https://addon.example/file', transcoded: true, transport: 'hls',
    source: '4K DV TrueHD original source', remux: true };
  const result = await h.prepareBrowserStream(input, settings);
  assert.equal(result.url, input.url);
  assert.equal(result.remux, false);
  assert.equal(requests, 0);
  for (const option of ['forceRemux', 'forceTranscode']) {
    await assert.rejects(h.prepareBrowserStream(input, settings, { [option]: true }), /already attempted/);
  }
  assert.equal(requests, 0);
});

test('converted HLS is classified independently from the original file failure', () => {
  const compatibility = load('lib/streamCompatibility.ts', {
    './capabilities': { getMediaCapabilities: () => capabilities },
    './debrid': { parseDebridStream: () => ({ provider: 'torbox' }) }
  });
  const original = { ...file(), source: '4K DV TrueHD', media: { container: 'mkv', videoCodec: 'hevc', audioCodec: 'truehd' } };
  const converted = { ...original, url: 'https://cdn.example/master.m3u8', originalUrl: original.url, media: undefined, transport: 'hls', transcoded: true };
  compatibility.recordBrowserPlaybackFailure(original, 'Original video cannot decode');
  assert.equal(compatibility.streamPlayability(converted).mode, 'direct');
  assert.equal(compatibility.streamPlayability(original).mode, 'transcode');
  compatibility.recordBrowserPlaybackFailure(converted, 'Conversion unavailable', true);
  assert.equal(compatibility.streamPlayability(original).mode, 'external');
  assert.equal(compatibility.streamPlayability(converted).mode, 'external');
});

test('provider conversion rejection is shown without repeated automatic API requests', async () => {
  let requests = 0;
  const h = preparation({ debrid: { parseDebridStream: () => ({ provider: 'torbox' }),
    resolveTranscodeStream: async () => { requests++; return { error: 'Web transcoding requires the TorBox Pro plan.' }; }
  } });
  await assert.rejects(h.prepareBrowserStream(file(), settings, { forceTranscode: true }), /TorBox Pro/);
  assert.equal(requests, 1);
  await assert.rejects(h.prepareBrowserStream(file(), settings), /conversion is unavailable/);
  assert.equal(requests, 1);
});

for (const route of ['remux', 'transcode']) test(`cancellation discards a late debrid ${route} response`, async () => {
  const pending = deferred();
  const controller = new AbortController();
  const h = preparation({ debrid: {
    parseDebridStream: () => ({ provider: 'torbox' }),
    resolveDebridDirectUrl: () => pending.promise, resolveTranscodeStream: () => pending.promise
  } });
  const result = h.prepareBrowserStream(file(), settings, { [route === 'remux' ? 'forceRemux' : 'forceTranscode']: true, signal: controller.signal });
  controller.abort();
  pending.resolve({ url: 'https://cdn.example/result' });
  await assert.rejects(result, { name: 'AbortError' });
});

test('provider conversion errors reach the caller verbatim', async () => {
  const h = preparation({ debrid: { parseDebridStream: () => ({ provider: 'torbox' }),
    resolveTranscodeStream: async () => ({ error: 'Transcoding permission denied' }) } });
  await assert.rejects(h.prepareBrowserStream(file(), settings, { forceTranscode: true }), /Transcoding permission denied/);
});

test('forced audio remux cannot bypass an unsupported video or Dolby Vision gate', async () => {
  const h = preparation();
  for (const media of [{ videoCodec: 'hevc' }, { videoCodec: 'h264', hdr: 'Dolby Vision' }]) {
    await assert.rejects(h.prepareBrowserStream({ ...file(), media }, settings, { forceRemux: true }), /HEVC|Dolby Vision/);
  }
});

test('Dolby Vision conversion keeps the manually selected file and propagates failures', async () => {
  const input = { ...file(), description: 'Mayday.2160p.DV.HDR10+.MP4', media: { videoCodec: 'hevc' } };
  let requested;
  const provider = { provider: 'torbox', fileName: 'selected.mp4' };
  const h = preparation({ debrid: {
    parseDebridStream: () => provider,
    resolveTranscodeStream: async (info) => { requested = info; return { error: 'Provider conversion unavailable' }; }
  } });
  await assert.rejects(h.prepareBrowserStream(input, settings), /Provider conversion unavailable/);
  assert.equal(requested, provider);
});

for (const converted of [false, true]) test(`missing video ${converted ? 'after conversion stops with an error' : 'requests conversion of the same file'}`, () => {
  const stream = { ...file(), transcoded: converted };
  const video = { pause: () => { paused = true; } };
  let missing, paused = false, failure = false;
  const selections = [];
  const toasts = [];
  const failures = [];
  const effect = extracted('components/player/PlayerOverlay.tsx', (node, source) =>
    ts.isCallExpression(node) && node.expression.getText(source) === 'useEffect'
      && ts.isArrowFunction(node.arguments[0]) && node.arguments[0].getText(source).includes('monitorVideoFrames(')
      ? node.arguments[0] : undefined, {
    booted: true, liveTv: false, videoRef: { current: video }, stream,
    monitorVideoFrames: (_, callback) => { missing = callback; return () => {}; },
    canProviderTranscode: () => true,
    recordBrowserPlaybackFailure: (...args) => failures.push(args),
    onSelectStream: (...args) => selections.push(args), tryNextSource: () => false,
    setError: (value) => { failure = value; }, setBuffering: () => {}, setShowControls: () => {},
    onToast: (message) => toasts.push(message)
  });
  effect();
  missing();
  assert.equal(paused, true);
  assert.equal(failure, converted);
  assert.equal(failures[0][0], stream);
  assert.equal(failures[0][2], converted);
  assert.equal(selections.length, converted ? 0 : 1);
  if (!converted) { assert.equal(selections[0][0], stream); assert.equal(selections[0][1].forceTranscode, true); }
  assert.doesNotMatch(toasts.join(' '), /switching source/i);
});

test('store forwards preparation errors to the visible toast and does not mount a failed source', async () => {
  const h = storeHarness(async () => { throw new Error('Plex refused playback. Check server availability and transcoding permissions.'); });
  h.play(homeStream());
  await flush();
  assert.equal(h.state.active, null);
  assert.equal(h.state.toasts.at(-1), 'Plex refused playback. Check server availability and transcoding permissions.');
  assert.equal(h.state.timers.size, 0);
});

test('store stops a late cancelled prepared session using its captured credentials after profile replacement', async () => {
  const api = actualHomeApi();
  const prepared = await api.prepareHomeServerPlayback(homeStream(), settings);
  const pending = deferred();
  const p = preparation({ home: () => pending.promise });
  const h = storeHarness(p.prepareBrowserStream, api.reportHomeServerPlayback);
  h.play(homeStream());
  h.globals.playbackPreparation.current.abort();
  h.globals.activeProfileIdRef.current = 'profile-b';
  h.globals.settingsRef.current = { homeServers: [{ ...server, url: 'https://wrong.example', token: 'wrong-token', userId: 'wrong-user' }] };
  pending.resolve(prepared);
  await flush();
  assert.equal(h.state.active, null);
  assert.equal(api.calls.length, 1);
  assert.equal(new URL(api.calls[0].url).hostname, 'original.example');
  assert.equal(api.calls[0].headers['X-Emby-Token'], 'original-token');
  assert.equal(api.calls[0].body.PlaySessionId, 'remembered-session');
});

test('store timeout cancels negotiation and stops a later prepared result without mounting it', async () => {
  const pending = deferred();
  const stopped = [];
  const h = storeHarness(() => pending.promise, async (...args) => { stopped.push(args); });
  h.play(homeStream());
  [...h.state.timers.values()][0]();
  assert.equal(h.globals.playbackPreparation.current.signal.aborted, true);
  pending.resolve(preparedStream());
  await flush();
  assert.equal(h.state.active, null);
  assert.equal(stopped[0][2], 'stop');
  assert.match(h.state.toasts.at(-1), /did not respond/);
});

test('store invalidates a superseded preparation without stopping the newer session', async () => {
  const a = deferred(), b = deferred();
  const reports = [];
  let count = 0;
  const h = storeHarness(() => ++count === 1 ? a.promise : b.promise, async (stream, _settings, event) => reports.push([stream.playbackSession.sessionId, event]));
  h.play(homeStream()); h.play(homeStream());
  a.resolve(preparedStream('old')); b.resolve(preparedStream('new'));
  await flush();
  assert.equal(h.state.active.playbackSession.sessionId, 'new');
  assert.deepEqual(reports, [['old', 'stop']]);
});

test('session effect captures absolute playhead without sending final stop during effect cleanup', async () => {
  const reports = [];
  const selected = preparedStream(); selected.playbackSession.startOffset = 100;
  const h = sessionEffect(selected, async (_stream, _settings, event, options) => reports.push({ event, ...options }));
  const cleanup = h.setup();
  h.video.currentTime = 25; h.video.paused = false; h.emit('playing');
  h.video.currentTime = 26; h.emit('timeupdate');
  h.tick(); cleanup(); await flush();
  assert.deepEqual(reports.map((report) => [report.event, report.positionSeconds]), [['start', 125], ['progress', 126]]);
});

test('terminal startup errors release the home-server session without waiting for overlay unmount', async () => {
  const reports = [];
  const h = sessionEffect(preparedStream(), async (_stream, _settings, event) => reports.push(event));
  const cleanup = h.setup();
  h.video.error = { code: 3 }; h.fail(); await flush();
  try { assert.ok(reports.includes('stop'), `Reports after fatal startup error: ${JSON.stringify(reports)}`); }
  finally { cleanup(); }
});

test('a naturally ended title reports home-server stop while the overlay remains open', async () => {
  const reports = [];
  const h = sessionEffect(preparedStream(), async (_stream, _settings, event) => reports.push(event));
  const cleanup = h.setup();
  h.video.paused = false; h.emit('playing');
  h.video.currentTime = 300; h.video.paused = true; h.video.ended = true; h.emit('timeupdate'); h.emit('ended');
  await flush();
  try { assert.ok(reports.includes('stop'), `Reports after ended: ${JSON.stringify(reports)}`); }
  finally { cleanup(); }
});

test('nonzero HLS offsets use a full-title duration in Plex reports', async () => {
  const reports = [];
  const selected = preparedStream(); selected.playbackSession.startOffset = 120;
  const h = sessionEffect(selected, async (_stream, _settings, event, options) => reports.push({ event, ...options }));
  const cleanup = h.setup();
  h.video.duration = 180; h.video.currentTime = 10; h.video.paused = false; h.emit('playing');
  await flush();
  try { assert.equal(reports[0].positionSeconds, 130); assert.equal(reports[0].durationSeconds, 300); }
  finally { cleanup(); }
});

test('an accepted session cancelled before VideoPlayer commits still gets stopped', async () => {
  const stopped = [];
  const h = storeHarness(async () => preparedStream(), async (...args) => stopped.push(args));
  h.play(homeStream()); await flush();
  assert.equal(h.state.active.playbackSession.sessionId, 'session');
  // Model close/profile teardown before React commits the scheduled player render.
  h.close(); await flush();
  assert.equal(h.state.active, null);
  assert.equal(stopped.length, 1, 'An accepted-but-unmounted session has no cleanup owner');
});

test('React effect replay keeps the owned session alive and deduplicates start reporting', async () => {
  const api = actualHomeApi();
  const selected = await api.prepareHomeServerPlayback(homeStream(), settings);
  const h = sessionEffect(selected, api.reportHomeServerPlayback, api.updateHomeServerPlaybackPosition);
  const firstCleanup = h.setup(); firstCleanup();
  const cleanup = h.setup();
  h.video.paused = false; h.emit('playing'); h.emit('playing'); await flush();
  try { assert.equal(api.calls.filter((call) => new URL(call.url).pathname === '/Sessions/Playing').length, 1,
    `Provider requests: ${JSON.stringify(api.calls.map((call) => new URL(call.url).pathname))}`); }
  finally { cleanup(); await api.reportHomeServerPlayback(selected, settings, 'stop'); }
});

test('forcing remux of an already-transcoded home stream renegotiates on the same server', async () => {
  const h = preparation();
  const input = { ...preparedStream(), url: 'https://original.example/Videos/item/master.m3u8', originalUrl: file().url,
    transport: 'hls', transcoded: true, media: { container: 'ts', videoCodec: 'h264', audioCodec: 'aac' } };
  const result = await h.prepareBrowserStream(input, settings, { forceRemux: true });
  assert.notEqual(result.remux && result.transport === 'hls', true, `File-remux input: ${result.url}`);
  assert.equal(h.calls.length, 1);
  assert.equal(h.calls[0][0], input);
  assert.equal(h.calls[0][2].forceTranscode, true);
});

for (const action of ['new selection', 'channel', 'catchup', 'profile cleanup']) {
  test(`store releases accepted session on ${action} before any player effect mounts`, async () => {
    const reports = [];
    const h = storeHarness(async () => preparedStream(), async (...args) => reports.push(args));
    h.play(homeStream()); await flush();
    const originalSettings = h.globals.ownedPlayback.current.settings;
    h.globals.settingsRef.current = { homeServers: [], defaultPlayer: 'browser' };
    if (action === 'new selection') h.play(file());
    if (action === 'channel') h.playChannel({ name: 'Channel', streamUrl: 'https://iptv.example/live.m3u8' });
    if (action === 'catchup') h.playCatchup({ name: 'Channel' }, { title: 'Archive' });
    if (action === 'profile cleanup') h.profileCleanup();
    await flush();
    assert.equal(reports.length, 1);
    assert.equal(reports[0][2], 'stop');
    assert.equal(reports[0][1], originalSettings);
  });
}

test('timeupdate snapshots survive effect cleanup and let store-only close report the current playhead', async () => {
  const api = actualHomeApi();
  const selected = await api.prepareHomeServerPlayback(homeStream(), settings);
  const store = storeHarness(async () => selected, api.reportHomeServerPlayback);
  store.play(homeStream()); await flush();
  const effect = sessionEffect(selected, api.reportHomeServerPlayback, api.updateHomeServerPlaybackPosition);
  const cleanup = effect.setup();
  effect.video.currentTime = 61.25; effect.video.paused = false; effect.emit('timeupdate');
  assert.equal(api.calls.length, 0, 'snapshot updates are local');
  cleanup(); assert.equal(api.calls.length, 0, 'effect cleanup does not release store ownership');
  store.close(); await flush();
  assert.equal(api.calls[0].body.PositionTicks, 612500000);
});

test('playing after ended asks the store for a new session instead of restarting the stopped session', async () => {
  const reports = [];
  const h = sessionEffect(preparedStream(), async (_stream, _settings, event) => reports.push(event));
  const cleanup = h.setup();
  h.video.paused = false; h.emit('playing');
  h.video.currentTime = 300; h.video.paused = true; h.emit('ended');
  h.video.currentTime = 12; h.video.paused = false; h.emit('playing');
  await flush();
  assert.deepEqual(reports, ['start', 'stop']);
  assert.equal(h.selections.length, 1);
  assert.equal(h.selections[0][1].forceBrowser, true);
  assert.equal(h.resumeAtRef.current, 12);
  cleanup();
});

test('new selection clears the stopped source while its replacement is still preparing', async () => {
  const pending = deferred();
  const reports = [];
  let count = 0;
  const h = storeHarness(() => ++count === 1 ? Promise.resolve(preparedStream('old')) : pending.promise,
    async (stream, _settings, event) => reports.push([stream.playbackSession.sessionId, event]));
  h.play(homeStream()); await flush();
  assert.equal(h.state.active.playbackSession.sessionId, 'old');
  h.play(homeStream());
  assert.equal(h.state.active, null);
  assert.equal(h.globals.ownedPlayback.current, null);
  pending.resolve(preparedStream('new')); await flush();
  assert.deepEqual(reports, [['old', 'stop']]);
  assert.equal(h.state.active.playbackSession.sessionId, 'new');
});

for (const [label, selected, episode, explicit, expected] of [
  ['unwatched movie', { mediaType: 'movie', resumePositionSeconds: 120 }, null, undefined, 120],
  ['watched movie', { mediaType: 'movie', resumePositionSeconds: 120, isWatched: true }, null, undefined, undefined],
  ['same episode', { mediaType: 'tv', seasonNumber: 2, episodeNumber: 3, resumePositionSeconds: 90 }, { season: 2, episode: 3 }, undefined, 90],
  ['different episode', { mediaType: 'tv', seasonNumber: 2, episodeNumber: 3, resumePositionSeconds: 90 }, { season: 2, episode: 4 }, undefined, undefined],
  ['explicit restart', { mediaType: 'movie', resumePositionSeconds: 120 }, null, 0, 0],
  ['source-switch resume', { mediaType: 'movie', resumePositionSeconds: 120 }, null, 140, 140]
]) test(`store applies resume to ${label} without crossing episode boundaries`, async () => {
  const inputs = [];
  const h = storeHarness(async (input) => { inputs.push(input); return input; }, undefined,
    { selected, selectedEpisode: episode });
  h.play({ ...homeStream(), ...(explicit === undefined ? {} : { resumePositionSeconds: explicit }) });
  await flush();
  assert.equal(inputs[0].resumePositionSeconds, expected);
});

test('in-player selection wrapper carries the absolute current position across component remount', () => {
  const selected = preparedStream(); selected.playbackSession.startOffset = 120;
  const selections = [];
  const select = extracted('components/player/PlayerOverlay.tsx', (node) => ts.isVariableDeclaration(node)
    && node.name.getText() === 'onSelectStream' && ts.isCallExpression(node.initializer) ? node.initializer.arguments[0] : undefined,
    { videoRef: { current: { currentTime: 15, readyState: 4 } }, resumeAtRef: { current: 0 },
      stream: selected, selectStream: (...args) => selections.push(args) });
  select(homeStream(), { forceTranscode: true });
  assert.equal(selections[0][0].resumePositionSeconds, 135);
  assert.equal(selections[0][1].forceTranscode, true);
});

test('observer retries a failed start POST on later playing and deduplicates only acknowledged starts', async () => {
  let attempts = 0;
  const api = actualHomeApi(async (call) => {
    if (new URL(call.url).pathname === '/Sessions/Playing' && ++attempts === 1) {
      throw new Error('Start POST temporarily unavailable');
    }
    return '';
  });
  const selected = await api.prepareHomeServerPlayback(homeStream(), settings);
  const observer = sessionEffect(selected, api.reportHomeServerPlayback, api.updateHomeServerPlaybackPosition);
  const cleanup = observer.setup();
  try {
    observer.video.paused = false;
    observer.video.currentTime = 10;
    observer.emit('playing'); await flush();
    assert.equal(attempts, 1);
    observer.video.currentTime = 15;
    observer.emit('playing'); await flush();
    assert.equal(attempts, 2, 'A failed request must not mark the observer started permanently');
    observer.video.currentTime = 20;
    observer.emit('playing'); await flush();
    assert.equal(attempts, 2, 'A successful retry must suppress later duplicate starts');
    assert.deepEqual(api.calls.map((call) => [call.method, call.body.PositionTicks]), [
      ['POST', 100000000], ['POST', 150000000]
    ]);
  } finally {
    cleanup();
    await api.reportHomeServerPlayback(selected, settings, 'stop');
  }
  assert.equal(api.calls.at(-1).body.PositionTicks, 200000000);
});

test('browser resume remains separate from zero-offset home-server negotiation', async () => {
  const h = preparation();
  const selected = { ...homeStream(), resumePositionSeconds: 125 };
  await h.prepareBrowserStream(selected, settings, { forceTranscode: true });
  assert.equal(h.calls[0][0].resumePositionSeconds, 125);
  assert.equal(h.calls[0][2].startTime ?? 0, 0);
});

test('advanceEpisode starts at zero despite playStream retaining the previous episode resume closure', async () => {
  const selected = { id: 42, mediaType: 'tv', seasonNumber: 1, episodeNumber: 3,
    resumePositionSeconds: 1500, isWatched: false };
  const selectedEpisode = { season: 1, episode: 3 };
  const inputs = [];
  const selections = [];
  const updates = {};
  const candidate = homeStream();
  const h = storeHarness(async (input) => { inputs.push(input); return input; }, undefined,
    { selected, selectedEpisode });
  const advance = extracted('lib/store.tsx', (node) => ts.isVariableDeclaration(node)
    && node.name.getText() === 'advanceEpisode' && ts.isCallExpression(node.initializer)
    ? node.initializer.arguments[0] : undefined, {
    ...h.globals,
    sourceGeneration: { current: 0 }, addonsRef: { current: [] },
    nextLocalEpisode: (item) => item,
    getSeasonEpisodes: async () => [{ episodeNumber: 4, name: 'Next episode' }],
    playbackPlan: () => ({ route: 'here' }),
    // React setters schedule a render; the current playStream closure stays stale.
    setSelectedEpisode: (value) => { updates.episode = value; },
    setSelected: (update) => { updates.selected = update(selected); },
    setStreams: () => {}, mergeStreams: () => {},
    getStreamsProgressive: async () => [],
    appendHomeServerSources: async () => [candidate],
    appendVodSources: async () => [], appendTelegramSources: async () => [],
    playStream: (...args) => { selections.push(args); h.play(...args); }
  });
  assert.equal(await advance(), true);
  await flush();
  assert.equal(updates.episode.episode, 4);
  assert.equal(updates.selected.episodeNumber, 4);
  assert.equal(h.globals.selected.episodeNumber, 3);
  assert.equal(h.globals.selected.resumePositionSeconds, 1500);
  assert.equal(selections.length, 1);
  assert.equal(selections[0][0].resumePositionSeconds, 0);
  assert.equal(selections[0][0].autoSelect, true);
  assert.equal(selections[0][1].forceBrowser, true);
  assert.equal(inputs.length, 1);
  assert.equal(inputs[0].resumePositionSeconds, 0);
  assert.equal(h.state.active.resumePositionSeconds, 0);
  assert.equal(candidate.resumePositionSeconds, undefined);
});

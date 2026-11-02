// Live TV in the browser: which engine and route each (browser, stream shape)
// gets, what the player does when attempts fail, and what it tells the viewer.
// The decisions live in lib/liveTvPlayback.ts; the player hook walks them.
const test = require('node:test');
const assert = require('node:assert/strict');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load } = require('./load.cjs');
const { sourceOf } = require('./sources.cjs');

const live = load('lib/liveTvPlayback.ts');
const plain = (value) => JSON.parse(JSON.stringify(value));

// Browser engine profiles (what lib/capabilities.ts + preferNativeHls report).
const CHROME = { pageProtocol: 'https:', mse: true, nativeHls: false, preferNativeHls: false };
const SAFARI_MAC = { pageProtocol: 'https:', mse: true, nativeHls: true, preferNativeHls: true };
const IPHONE_17 = { pageProtocol: 'https:', mse: true, nativeHls: true, preferNativeHls: true }; // ManagedMediaSource
const IPHONE_OLD = { pageProtocol: 'https:', mse: false, nativeHls: true, preferNativeHls: true };
const TV_BROWSER = { pageProtocol: 'https:', mse: true, nativeHls: true, preferNativeHls: false }; // Tizen/webOS style
const RELAY = 'https://relay.example/media';
const routes = {
  relay: (url) => `${RELAY}?url=${encodeURIComponent(url)}`,
  manifestRelay: (url) => `https://app.example/api/proxy?rewrite=worker&url=${encodeURIComponent(url)}`
};
const summary = (plan) => Array.from(plan.attempts, (a) => `${a.route}:${a.transport}${a.hlsEngine ? `/${a.hlsEngine}` : ''}:${a.variant}`);

test('channel URL shapes match Android: HLS, raw TS, numeric Xtream ids, Stalker links, opaque URLs', () => {
  const cases = {
    'http://p.example:8080/live/u/p/123.m3u8': 'hls',
    'http://p.example:8080/live/u/p/123.ts': 'mpegts',
    'http://p.example:8080/live/u/p/123': 'mpegts',
    'http://p.example:8080/u/p/123': 'unknown', // a short Xtream id only on the playlist's own server (below)
    'https://cdn.example/tv/hd/101': 'unknown',
    'http://p.example/play/live.php?mac=00:1A:79:00:00:01&stream=9&extension=ts&play_token=x': 'mpegts',
    'http://p.example/play/live.php?stream=9&extension=m3u8': 'hls',
    'http://p.example/get.php?username=u&password=p&output=m3u8': 'hls',
    'https://cdn.example/hls/channel/index': 'hls',
    'https://cdn.example/stream.mpd': 'dash',
    'https://radio.example/stream.mp3': 'file',
    'https://cdn.example/channel?token=abc': 'unknown',
    'http://p.example/movie/u/p/123': 'unknown',
    'not a url': 'unknown'
  };
  for (const [url, shape] of Object.entries(cases)) assert.equal(live.liveStreamShape(url), shape, url);
  assert.equal(live.liveStreamShape('http://p.example:8080/u/p/123', 'http://p.example:8080'), 'mpegts');
  assert.equal(live.liveStreamShape('https://cdn.example/tv/hd/101', 'http://p.example:8080'), 'unknown');
});

test('Xtream channels have a twin in the other output format, both ways, keeping the query', () => {
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/live/u/p/123.ts'), 'http://p.example:8080/live/u/p/123.m3u8');
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/live/u/p/123.m3u8?token=1'), 'http://p.example:8080/live/u/p/123.ts?token=1');
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/live/u/p/123'), 'http://p.example:8080/live/u/p/123.m3u8');
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/u/p/123', 'http://p.example:8080'), 'http://p.example:8080/live/u/p/123.m3u8');
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/u/p/123'), null, 'not the playlist\'s Xtream server');
  assert.equal(live.xtreamTwinUrl('https://cdn.example/tv/hd/101', 'http://p.example:8080'), null);
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/movie/u/p/123.mkv'), null);
  assert.equal(live.xtreamTwinUrl('http://p.example:8080/series/u/p/123'), null);
  assert.equal(live.xtreamTwinUrl('https://cdn.example/hls/a/b.m3u8'), null);
});

test('engine choice per browser: Safari tries AVFoundation then hls.js, Chrome hls.js, old iPhones cannot play raw TS', () => {
  const choices = (shape, env) => Array.from(live.engineChoices(shape, env), (c) => `${c.transport}${c.hlsEngine ? `/${c.hlsEngine}` : ''}`);
  assert.deepEqual(choices('hls', CHROME), ['hls/mse']);
  assert.deepEqual(choices('hls', SAFARI_MAC), ['hls/native', 'hls/mse']);
  assert.deepEqual(choices('hls', IPHONE_17), ['hls/native', 'hls/mse']);
  assert.deepEqual(choices('hls', IPHONE_OLD), ['hls/native']);
  assert.deepEqual(choices('hls', TV_BROWSER), ['hls/mse', 'hls/native']);
  assert.deepEqual(choices('mpegts', SAFARI_MAC), ['mpegts']);
  assert.deepEqual(choices('mpegts', IPHONE_OLD), []);
  assert.deepEqual(choices('unknown', CHROME), ['mpegts', 'hls/mse', 'file']);
  assert.deepEqual(choices('unknown', IPHONE_OLD), ['hls/native', 'file']);
});

test('http:// channels on the secure site go straight to the relay (no silent mixed-content attempt)', () => {
  const plan = live.planLiveAttempts({ url: 'http://p.example:8080/live/u/p/1.m3u8' }, SAFARI_MAC, routes);
  assert.deepEqual(summary(plan), ['relay:hls/native:original', 'relay:hls/mse:original', 'relay:mpegts:twin', 'manifest-relay:hls/native:original']);
  assert.ok(plan.attempts.every((a) => a.url.startsWith('https://')));
  // Chrome: one HLS engine; the .ts twin covers panels that refuse HLS output.
  assert.deepEqual(summary(live.planLiveAttempts({ url: 'http://p.example:8080/live/u/p/1.m3u8' }, CHROME, routes)),
    ['relay:hls/mse:original', 'relay:mpegts:twin', 'manifest-relay:hls/mse:original']);
});

test('https channels are tried directly first (free, the subscriber\'s own IP), then relayed', () => {
  const plan = live.planLiveAttempts({ url: 'https://cdn.example/live/u/p/7.ts' }, CHROME, routes);
  assert.deepEqual(summary(plan), ['direct:mpegts:original', 'direct:hls/mse:twin', 'relay:mpegts:original', 'relay:hls/mse:twin', 'manifest-relay:hls/mse:twin']);
});

test('providers that need a player User-Agent or Referer are relayed first; the browser never sends forbidden headers', () => {
  const headers = { 'User-Agent': 'VLC/3.0.20', Referer: 'https://portal.example/', Authorization: 'Bearer t' };
  const plan = live.planLiveAttempts({ url: 'https://cdn.example/stream.m3u8', headers }, CHROME, routes);
  assert.deepEqual(summary(plan), ['relay:hls/mse:original', 'manifest-relay:hls/mse:original', 'direct:hls/mse:original']);
  assert.deepEqual(plain(plan.attempts.at(-1).headers), { Authorization: 'Bearer t' });
  assert.equal(plan.attempts[0].headers, undefined);
});

test('without a relay: Safari may still load http:// natively (Safari 17), everywhere else it is blocked up front', () => {
  const none = {};
  const safari = live.planLiveAttempts({ url: 'http://p.example/live/u/p/1.m3u8' }, SAFARI_MAC, none);
  assert.deepEqual(summary(safari), ['direct:hls/native:original']);
  assert.equal(safari.attempts[0].startupMs, 6000);
  const chrome = live.planLiveAttempts({ url: 'http://p.example/live/u/p/1.m3u8' }, CHROME, none);
  assert.equal(chrome.attempts.length, 0);
  assert.equal(chrome.blocked, 'insecure');
  // An http page (self-hosted on a LAN) and localhost are not mixed content.
  assert.equal(live.planLiveAttempts({ url: 'http://p.example/live/u/p/1.m3u8' }, { ...CHROME, pageProtocol: 'http:' }, none).attempts[0].route, 'direct');
  assert.equal(live.planLiveAttempts({ url: 'http://127.0.0.1:8080/x.m3u8' }, CHROME, none).attempts[0].route, 'direct');
});

test('old iPhones play the HLS form of an Xtream channel and say why a raw TS channel cannot play', () => {
  const plan = live.planLiveAttempts({ url: 'http://p.example/live/u/p/1.ts' }, IPHONE_OLD, routes);
  assert.deepEqual(summary(plan), ['relay:hls/native:twin', 'manifest-relay:hls/native:twin']);
  const raw = live.planLiveAttempts({ url: 'http://p.example/channel/ch1.ts' }, IPHONE_OLD, routes);
  assert.equal(raw.blocked, 'no-engine');
  const failure = live.classifyLiveFailure([], { browser: 'Safari', insecureSource: true, relayAvailable: true, relayTicket: true, played: false }, raw.blocked, raw.shape);
  assert.equal(failure.kind, 'unsupported-browser');
  assert.match(failure.message, /iOS 17\.1/);
  // Only a TS channel is called raw MPEG-TS.
  const dash = live.planLiveAttempts({ url: 'https://cdn.example/live.mpd' }, IPHONE_OLD, routes);
  const other = live.classifyLiveFailure([], { browser: 'Safari', insecureSource: false, relayAvailable: true, relayTicket: true, played: false }, dash.blocked, dash.shape);
  assert.equal(other.kind, 'unsupported-browser');
  assert.doesNotMatch(other.message, /MPEG-TS/);
  // A numeric CDN path is not an Xtream id: old iPhones still try it natively.
  assert.deepEqual(summary(live.planLiveAttempts({ url: 'https://cdn.example/tv/hd/101' }, IPHONE_OLD, {})), ['direct:hls/native:original', 'direct:file:original']);
});

test('opaque live URLs are tried as raw TS first, then as HLS, and never through the app backend', () => {
  // /api/proxy only refuses media it can recognise; an opaque URL could be a
  // raw TS stream, which must not run through hosting bandwidth.
  assert.deepEqual(summary(live.planLiveAttempts({ url: 'https://cdn.example/channel?token=abc' }, CHROME, routes)),
    ['direct:mpegts:original', 'direct:hls/mse:original', 'direct:file:original', 'relay:mpegts:original', 'relay:hls/mse:original', 'relay:file:original']);
  const ts = live.planLiveAttempts({ url: 'http://p.example/channel/ch1.ts' }, CHROME, routes);
  assert.ok(ts.attempts.every((a) => a.route !== 'manifest-relay'));
});

test('a second engine for a request the provider already refused is skipped', () => {
  const plan = live.planLiveAttempts({ url: 'http://p.example/live/u/p/1.m3u8' }, SAFARI_MAC, routes);
  const [native, mse, twin] = plan.attempts;
  assert.equal(live.attemptIsPointless(mse, [{ attempt: native, fault: { kind: 'unsupported', code: 4 } }]), false, 'AVFoundation refusing a stream is exactly when hls.js may play it');
  const refused = [{ attempt: native, fault: { kind: 'network', httpStatus: 403 } }];
  assert.equal(live.attemptIsPointless(mse, refused), true);
  assert.equal(live.attemptIsPointless(twin, refused), false, 'the other output format is a different request');
});

const context = (overrides = {}) => ({ browser: 'Chrome', canDecode: live.decodeCheck((type) => /avc1|mp4a\.40|audio\/mpeg/.test(type)),
  insecureSource: true, relayAvailable: true, relayTicket: true, played: false, ...overrides });
const relayAttempt = (overrides = {}) => ({ url: RELAY, transport: 'mpegts', route: 'relay', target: 'http://p.example/live/u/p/1.ts', variant: 'original', ...overrides });

test('failures are explained by what the provider said', () => {
  const kind = (status) => live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'network', httpStatus: status } }], context()).kind;
  assert.equal(kind(404), 'offline');
  assert.equal(kind(410), 'offline');
  assert.equal(kind(403), 'provider-refused');
  assert.equal(kind(401), 'provider-refused');
  assert.equal(kind(429), 'provider-busy');
  assert.equal(kind(503), 'provider-down');
  assert.equal(kind(402), 'relay-denied');
  const refused = live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'network', httpStatus: 403 } }], context());
  assert.equal(refused.values.value0, 403);
  assert.match(refused.message, /\{value0\}/);
  // The relay's answer outranks a CORS-blocked direct attempt.
  const mixed = live.classifyLiveFailure([
    { attempt: relayAttempt({ route: 'direct' }), fault: { kind: 'network', httpStatus: 500 } },
    { attempt: relayAttempt(), fault: { kind: 'network', httpStatus: 404 } }], context());
  assert.equal(mixed.kind, 'offline');
  // The channel's own answer outranks its twin's (panels refuse .m3u8 for an offline .ts).
  const twin = live.classifyLiveFailure([
    { attempt: relayAttempt({ transport: 'hls', hlsEngine: 'mse', variant: 'twin' }), fault: { kind: 'network', httpStatus: 403 } },
    { attempt: relayAttempt(), fault: { kind: 'network', httpStatus: 404 } }], context());
  assert.equal(twin.kind, 'offline');
  // Engines missing at run time read as "not playable in this browser", not as a network fault.
  assert.equal(live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'unsupported', code: 'TRANSPORT_UNSUPPORTED' } }], context()).kind, 'unsupported-browser');
});

test('codec failures name the format and the browser; a refused twin does not hide them', () => {
  const failure = live.classifyLiveFailure([
    { attempt: relayAttempt(), fault: { kind: 'media', code: 'MediaMSEError', mediaInfo: { audioCodec: 'ac-3' } } },
    { attempt: relayAttempt({ transport: 'hls', hlsEngine: 'mse', variant: 'twin' }), fault: { kind: 'network', httpStatus: 403 } }], context());
  assert.equal(failure.kind, 'codec-audio');
  assert.deepEqual(plain(failure.values), { value0: 'Dolby Digital (AC-3)', value1: 'Chrome' });
  assert.match(failure.message, /Safari/);
  const mpeg2 = live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'media', mediaInfo: { videoCodec: 'mpeg2video' } } }], context());
  assert.equal(mpeg2.kind, 'codec-video');
  assert.match(mpeg2.message, /no web browser/);
  const unnamed = live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'media', code: 'MediaMSEError' } }], context({ browser: 'Firefox' }));
  assert.equal(unnamed.kind, 'codec');
  assert.equal(unnamed.values.value0, 'Firefox');
});

test('the element\'s "source not supported" is not mistaken for a codec problem', () => {
  // Browsers report blocked, refused and missing sources as MEDIA_ERR_SRC_NOT_SUPPORTED.
  const native = relayAttempt({ transport: 'hls', hlsEngine: 'native' });
  assert.equal(live.classifyLiveFailure([{ attempt: native, fault: { kind: 'unsupported', code: 4 } }], context()).kind, 'network');
  assert.equal(live.classifyLiveFailure([{ attempt: native, fault: { kind: 'media', code: 3 } }], context()).kind, 'codec');
});

test('timeouts, drops and blocked plans each get their own explanation', () => {
  const timeout = { kind: 'network', code: 'STARTUP_TIMEOUT' };
  assert.equal(live.classifyLiveFailure([{ attempt: relayAttempt(), fault: timeout }], context()).kind, 'no-data');
  assert.equal(live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'network', code: 'Exception' } }], context({ played: true })).kind, 'stopped');
  assert.equal(live.classifyLiveFailure([{ attempt: relayAttempt(), fault: { kind: 'network', code: 'Exception' } }], context()).kind, 'network');
  assert.equal(live.classifyLiveFailure([], context(), 'insecure').kind, 'insecure');
});

test('notices once a channel plays: missing picture, undecodable audio, silence outside Safari', () => {
  const ctx = context();
  assert.match(live.liveMediaNotice({ picture: false, sound: true }, ctx).message, /No picture/);
  const dolby = live.liveMediaNotice({ picture: true, sound: null, info: { audioCodec: 'ac-3' } }, ctx);
  assert.equal(dolby.values.value0, 'Dolby Digital (AC-3)');
  assert.match(live.liveMediaNotice({ picture: true, sound: false }, context({ browser: 'Firefox' })).message, /No sound/);
  assert.equal(live.liveMediaNotice({ picture: true, sound: false }, context({ browser: 'Safari', appleWebKit: true })), null);
  // Chrome on iPhone is WebKit: no "Safari plays it with sound" advice there either.
  assert.equal(live.liveMediaNotice({ picture: true, sound: false, info: { audioCodec: 'ac-3' } }, context({ browser: 'Chrome', appleWebKit: true })), null);
  assert.match(live.liveMediaNotice({ picture: false, sound: true }, context({ browser: 'Chrome', appleWebKit: true })).message, /No picture/);
  assert.equal(live.liveMediaNotice({ picture: true, sound: true, info: { audioCodec: 'mp4a.40.2' } }, ctx), null);
});

test('decode checks know MPEG audio rides in audio/mpeg (Chromium) or MP4 (Firefox)', () => {
  const chromium = live.decodeCheck((type) => type === 'audio/mpeg');
  const firefox = live.decodeCheck((type) => type === 'audio/mp4; codecs="mp3"');
  for (const check of [chromium, firefox]) assert.equal(check('audio', 'mp3'), true);
  assert.equal(chromium('audio', 'ac-3'), false);
  assert.deepEqual(plain(live.mediaInfoFromMime("Failed to execute 'addSourceBuffer' on 'MediaSource': The type provided ('audio/mp4;codecs=ac-3') is unsupported.")), { audioCodec: 'ac-3' });
  assert.deepEqual(plain(live.mediaInfoFromMime('video/mp4; codecs="hvc1.1.6.L93.B0"')), { videoCodec: 'hvc1.1.6.L93.B0' });
  assert.equal(live.mediaInfoFromMime('MediaSource.addSourceBuffer: Type not supported in MediaSource'), undefined);
});

test('browser names for the messages', () => {
  const ua = {
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.2 Safari/605.1.15': 'Safari',
    'Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148': 'Safari',
    'Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/126.0 Mobile/15E148 Safari/604.1': 'Chrome',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36': 'Chrome',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64; Xbox; Xbox One) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0': 'Edge',
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 14.5; rv:128.0) Gecko/20100101 Firefox/128.0': 'Firefox',
    'Mozilla/5.0 (SMART-TV; Linux; Tizen 6.5) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.0 Chrome/85.0 TV Safari/537.36': 'Samsung Internet',
    'Mozilla/5.0 (Web0S; Linux/SmartTV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0 Safari/537.36 WebAppManager': 'Chrome'
  };
  for (const [agent, name] of Object.entries(ua)) assert.equal(live.browserName(agent), name, agent);
});

// ---- The player hook walking a plan (components/player/overlay/usePlaybackEngine.ts) ----

function effect(globals) {
  const relative = 'components/player/PlayerOverlay.tsx';
  const filename = path.resolve(__dirname, '..', relative);
  const source = ts.createSourceFile(filename, sourceOf(relative), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let match;
  const visit = (node) => {
    if (ts.isCallExpression(node) && node.expression.getText(source) === 'useEffect' && ts.isArrowFunction(node.arguments[0])
      && node.arguments[0].getText(source).includes('const remuxFailed')) match = node.arguments[0];
    ts.forEachChild(node, visit);
  };
  visit(source);
  const code = ts.transpileModule(`module.exports = (${match.getText(source)});`, { compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS } }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, { module, Error, Event, DOMException, AbortController, console, Set, Date, Object, JSON, URL, encodeURIComponent, ...globals }, { filename });
  return module.exports;
}

const flush = () => new Promise(setImmediate);

function liveHarness({ url, headers, refresh, env = CHROME, ticket = 'v1.ticket', paywall = true, xtreamOrigin = null, overrideRef = { current: null } } = {}) {
  const state = { attached: [], liveFailure: null, errors: [], toasts: [], refreshes: 0 };
  const video = Object.assign(new EventTarget(), { readyState: 0, currentTime: 0, duration: NaN, paused: true, ended: false, videoWidth: 0,
    pause() { this.paused = true; }, play() { this.paused = false; return Promise.resolve(); }, removeAttribute() {}, load() {} });
  const timers = new Map();
  let id = 0;
  const noop = () => {};
  const stream = { source: 'Channel', addonName: 'Live TV', url, behaviorHints: { proxyHeaders: { request: headers } } };
  const handles = [];
  const globals = {
    stream, settings: {}, videoRef: { current: video }, resumeAtRef: { current: 0 }, transportRef: { current: null }, liveTv: true, playbackRate: 1,
    config: { allowNetlifyMediaProxy: false, resolverUrl: 'https://relay.example', paywallEnabled: paywall },
    setError: (value) => state.errors.push(value), setErrorDetail: noop, setBuffering: noop, setShowControls: noop, setPlayBlocked: noop,
    setRemuxTracks: noop, setRemuxAudioIndex: noop, setTransportTracks: noop, setBootStatus: noop, setLiveFailure: (value) => { state.liveFailure = value; },
    lastSavedRef: { current: 0 }, remuxAudioIndexRef: { current: -1 }, failureDiagnosticRef: { current: {} }, autoSourceHopsRef: { current: 0 }, sourceListRef: { current: [] },
    currentStreamRef: { current: stream }, onToast: (m) => state.toasts.push(m), notify: (m) => state.toasts.push(m), onSelectStream: noop, tryNextSource: () => false,
    refreshLiveSource: refresh ? async () => { state.refreshes++; return refresh(); } : undefined,
    browserAutoplayCandidates: () => [], diagnoseSourceFailure: async () => null,
    canProviderTranscode: () => false, canTryRemux: () => false, hasDolbyVision: () => false, recordBrowserPlaybackFailure: noop,
    parseDebridStream: () => null, invalidateDebridDirectUrl: noop, resolveDebridDirectUrl: async () => ({}),
    classifyMediaError: load('lib/playerRecovery.ts').classifyMediaError, playbackFailureKind: load('lib/playerRecovery.ts').playbackFailureKind,
    xtreamHlsVariant: () => null, isLikelyHlsUrl: () => false, needsBrowserHeaderRelay: () => false,
    liveTvProxyHeaders: () => ({ 'User-Agent': 'VLC/3.0.20 LibVLC/3.0.20' }),
    resolverMediaUrl: (target) => `https://relay.example/media?url=${encodeURIComponent(target)}`,
    workerManifestUrl: (target) => `https://app.example/api/proxy?rewrite=worker&url=${encodeURIComponent(target)}`,
    directManifestUrl: (target) => `https://app.example/api/proxy?rewrite=direct&url=${encodeURIComponent(target)}`,
    proxiedUrl: (target) => `https://app.example/api/proxy?url=${encodeURIComponent(target)}`,
    ...live, liveSourceOverrideRef: overrideRef, liveXtreamOrigin: xtreamOrigin,
    getMediaCapabilities: () => ({ mse: env.mse, nativeHls: env.nativeHls }), preferNativeHls: () => env.preferNativeHls,
    mediaSourceConstructor: () => ({ isTypeSupported: () => true }), currentStreamTicket: () => ticket,
    navigator: { userAgent: 'Mozilla/5.0 Chrome/126.0 Safari/537.36' }, document: { createElement: () => ({ canPlayType: () => '' }) },
    attachPlayback: (_video, attachedUrl, options) => {
      const handle = Object.assign(() => {}, { url: attachedUrl, options, reload: noop, goLive: () => true });
      state.attached.push({ url: attachedUrl, transport: options.transport, hlsEngine: options.hlsEngine, headers: options.requestHeaders });
      handles.push(handle);
      return handle;
    },
    window: { location: { protocol: env.pageProtocol }, setTimeout: (fn) => { timers.set(++id, fn); return id; }, clearTimeout: (key) => timers.delete(key),
      setInterval: () => 0, clearInterval: noop }
  };
  const setup = effect(globals);
  return { state, video, setup, overrideRef, fail: (fault) => handles.at(-1).options.onError(fault), last: () => state.attached.at(-1),
    playing: () => { video.readyState = 4; video.dispatchEvent(new Event('playing')); } };
}

test('player: an http:// Xtream channel starts on the relay and walks to the .ts twin when HLS is refused', async () => {
  const h = liveHarness({ url: 'http://p.example:8080/live/u/p/1.m3u8' });
  const cleanup = h.setup();
  assert.equal(h.state.attached.length, 1);
  assert.match(h.last().url, /^https:\/\/relay\.example\/media\?url=http%3A%2F%2Fp\.example%3A8080%2Flive%2Fu%2Fp%2F1\.m3u8/);
  assert.equal(h.last().transport, 'hls');
  h.fail({ transport: 'hls', kind: 'network', fatal: true, retryable: false, message: 'refused', httpStatus: 403 });
  assert.equal(h.last().transport, 'mpegts');
  assert.match(decodeURIComponent(h.last().url), /1\.ts$/);
  cleanup();
});

test('player: every attempt refused ends on a specific explanation, not a generic error', async () => {
  const h = liveHarness({ url: 'http://p.example:8080/live/u/p/99.ts' });
  const cleanup = h.setup();
  for (let i = 0; i < 6 && !h.state.liveFailure; i++) h.fail({ transport: h.last().transport, kind: 'network', fatal: true, retryable: false, message: 'x', httpStatus: 404 });
  assert.equal(h.state.liveFailure.kind, 'offline');
  assert.equal(h.state.errors.at(-1), true);
  assert.ok(h.state.attached.length <= 4, `bounded attempts, got ${h.state.attached.length}`);
  cleanup();
});

test('player: an expired Stalker link is re-resolved once and the channel plays from the fresh link', async () => {
  const h = liveHarness({ url: 'http://portal.example/play/live.php?stream=9&extension=ts&play_token=old',
    headers: { 'User-Agent': 'MAG200' }, refresh: () => ({ url: 'http://portal.example/play/live.php?stream=9&extension=ts&play_token=new', headers: { 'User-Agent': 'MAG200' } }) });
  const cleanup = h.setup();
  assert.match(h.last().url, /relay\.example/);
  for (let i = 0; i < 4 && h.state.refreshes === 0; i++) h.fail({ transport: h.last().transport, kind: 'network', fatal: true, retryable: false, message: 'x', httpStatus: 403 });
  await flush(); await flush();
  assert.equal(h.state.refreshes, 1);
  assert.match(decodeURIComponent(h.last().url), /play_token=new/);
  assert.equal(h.state.liveFailure, null);
  cleanup();
});

test('player: a decoder error after the channel played reconnects once, then ends on the explanation (not the VOD message)', async () => {
  const h = liveHarness({ url: 'http://p.example/channel/sd.ts' });
  const cleanup = h.setup();
  const playing = () => { h.video.readyState = 4; h.video.dispatchEvent(new Event('playing')); };
  playing();
  const rungs = h.state.attached.length;
  h.fail({ transport: 'mpegts', kind: 'media', fatal: true, retryable: false, message: 'MPEG-TS playback failed.', code: 'MediaMSEError' });
  assert.equal(h.state.attached.length, rungs, 'first: an in-place reconnect, not a new rung');
  for (let i = 0; i < 8 && !h.state.liveFailure; i++) { playing(); h.fail({ transport: h.last().transport, kind: 'media', fatal: true, retryable: false, message: 'x', code: 'MediaMSEError' }); }
  assert.equal(h.state.liveFailure.kind, 'codec');
  assert.equal(h.state.toasts.includes('This source stopped decoding partway through. Try another source or open it in VLC.'), false);
  cleanup();
});

test('player: a plan that cannot run explains itself immediately', () => {
  const h = liveHarness({ url: 'http://p.example/channel/ch1.ts', env: IPHONE_OLD });
  const cleanup = h.setup();
  assert.equal(h.state.attached.length, 0);
  assert.equal(h.state.liveFailure.kind, 'unsupported-browser');
  cleanup();
});

// ---- Review fixes ----

const relayRoutes = { ...routes, manifestDirect: (url) => `https://app.example/api/proxy?rewrite=direct&url=${encodeURIComponent(url)}` };

test('playlists also go through the backend with segments straight from the provider, with or without a relay', () => {
  // https HLS without CORS on a provider that blocks Cloudflare: backend playlist, direct segments.
  assert.deepEqual(summary(live.planLiveAttempts({ url: 'https://cdn.example/live.m3u8' }, CHROME, relayRoutes)),
    ['direct:hls/mse:original', 'relay:hls/mse:original', 'manifest-relay:hls/mse:original', 'manifest-direct:hls/mse:original']);
  // Self-hosted without a resolver: the backend route still exists.
  assert.deepEqual(summary(live.planLiveAttempts({ url: 'https://cdn.example/live.m3u8' }, CHROME, { manifestRelay: routes.manifestRelay, manifestDirect: relayRoutes.manifestDirect })),
    ['direct:hls/mse:original', 'manifest-direct:hls/mse:original']);
  // The Xtream twin's playlist too.
  assert.ok(summary(live.planLiveAttempts({ url: 'https://cdn.example/live/u/p/7.ts' }, CHROME, relayRoutes)).includes('manifest-direct:hls/mse:twin'));
});

test('only paths the backend itself treats as playlists reach its manifest routes', () => {
  for (const url of ['https://cdn.example/hls/channel/index', 'http://p.example/play/live.php?stream=9&extension=m3u8', 'https://cdn.example/channel?token=abc']) {
    assert.ok(Array.from(live.planLiveAttempts({ url }, CHROME, relayRoutes).attempts).every((a) => !a.route.startsWith('manifest')), url);
  }
});

test('a browser HLS player attempt that would need headers is skipped where hls.js can send them, sent without them where it cannot', () => {
  const headers = { Authorization: 'Bearer t' };
  assert.deepEqual(summary(live.planLiveAttempts({ url: 'https://cdn.example/live.m3u8', headers }, SAFARI_MAC, {})), ['direct:hls/mse:original']);
  const oldIphone = live.planLiveAttempts({ url: 'https://cdn.example/live.m3u8', headers }, IPHONE_OLD, {});
  assert.deepEqual(summary(oldIphone), ['direct:hls/native:original']);
  assert.equal(oldIphone.attempts[0].headers, undefined);
});

test('isDecodeFault: a segment that failed to parse is not blamed on the codec', () => {
  const hls = relayAttempt({ transport: 'hls', hlsEngine: 'mse' });
  assert.equal(live.classifyLiveFailure([{ attempt: hls, fault: { kind: 'media', code: 'fragParsingError' } }], context()).kind, 'network');
  assert.equal(live.classifyLiveFailure([{ attempt: hls, fault: { kind: 'media', code: 'bufferAppendError' } }], context()).kind, 'codec');
  assert.equal(live.classifyLiveFailure([{ attempt: hls, fault: { kind: 'media', code: 'fragParsingError', mediaInfo: { audioCodec: 'ac-3' } } }], context()).kind, 'codec-audio');
});

test('after the provider limits or refuses the channel, the plan stops pressing on', () => {
  const plan = live.planLiveAttempts({ url: 'https://cdn.example/live/u/p/7.ts' }, CHROME, relayRoutes);
  const [directTs, directTwin, relayTs, relayTwin, manifestRelayTwin, manifestDirectTwin] = plan.attempts;
  const ctx = { relayTicket: true, relayNeedsTicket: true };
  const limited = [{ attempt: directTs, fault: { kind: 'network', httpStatus: 429 } }];
  assert.equal(live.providerRefusal(limited, ctx), 429);
  assert.ok([directTwin, relayTs, relayTwin, manifestRelayTwin].every((a) => !live.attemptAllowed(a, limited, ctx)));
  assert.equal(live.mayRefreshChannel(limited, ctx), false);
  assert.equal(live.mayRetryChannel(limited, ctx), false);
  // 401/403: the twin once, nothing else, no second pass; the single Stalker refresh stays allowed.
  const refused = [{ attempt: relayTs, fault: { kind: 'network', httpStatus: 403 } }];
  assert.equal(live.attemptAllowed(directTwin, refused, ctx), true);
  assert.equal(live.attemptAllowed(manifestRelayTwin, refused, ctx), false);
  assert.equal(live.attemptAllowed(manifestDirectTwin, refused, ctx), false);
  const afterTwin = [...refused, { attempt: directTwin, fault: { kind: 'network', code: 'Exception' } }];
  assert.equal(live.attemptAllowed(relayTwin, afterTwin, ctx), false, 'the twin only once');
  assert.equal(live.mayRefreshChannel(refused, ctx), true);
  assert.equal(live.mayRetryChannel(refused, ctx), false);
  // A twin's own refusal does not stop the channel itself.
  assert.equal(live.providerRefusal([{ attempt: relayTwin, fault: { kind: 'network', httpStatus: 403 } }], ctx), null);
});

test('after the relay refused the session, nothing else goes through the relay', () => {
  const plan = live.planLiveAttempts({ url: 'http://p.example/live/u/p/1.m3u8' }, CHROME, relayRoutes);
  const [relayHls, relayTwin, manifestRelay, manifestDirect] = plan.attempts;
  const ctx = { relayTicket: true, relayNeedsTicket: true };
  const denied = [{ attempt: relayHls, fault: { kind: 'network', httpStatus: 402 } }];
  assert.equal(live.attemptAllowed(relayTwin, denied, ctx), false);
  assert.equal(live.attemptAllowed(manifestRelay, denied, ctx), false);
  assert.equal(manifestDirect.route, 'manifest-direct');
  assert.equal(live.attemptAllowed(manifestDirect, denied, ctx), true, 'the backend route does not use the relay');
  assert.equal(live.providerRefusal(denied, ctx), null);
});

test('until the 402 worker is deployed, a ticketless relay 403 reads as the relay refusing the session, not the subscription', () => {
  const fault = [{ attempt: relayAttempt(), fault: { kind: 'network', httpStatus: 403 } }];
  assert.equal(live.classifyLiveFailure(fault, context({ relayTicket: false, relayNeedsTicket: true })).kind, 'relay-denied');
  assert.equal(live.providerRefusal(fault, { relayTicket: false, relayNeedsTicket: true }), null);
  // With a ticket, or on a build whose relay needs none (self-hosted), it is the provider's 403.
  assert.equal(live.classifyLiveFailure(fault, context({ relayTicket: true, relayNeedsTicket: true })).kind, 'provider-refused');
  assert.equal(live.classifyLiveFailure(fault, context({ relayTicket: false, relayNeedsTicket: false })).kind, 'provider-refused');
  // A direct 403 is always the provider's.
  assert.equal(live.classifyLiveFailure([{ attempt: relayAttempt({ route: 'direct' }), fault: { kind: 'network', httpStatus: 403 } }], context({ relayTicket: false, relayNeedsTicket: true })).kind, 'provider-refused');
});

test('Apple WebKit gets no "try Safari" advice; TV and console WebKit is not Safari', () => {
  const iosChrome = 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/126.0 Mobile/15E148 Safari/604.1';
  assert.equal(live.isAppleWebKit(iosChrome), true);
  assert.equal(live.isAppleWebKit('Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.2 Safari/605.1.15'), true);
  assert.equal(live.isAppleWebKit('Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0 Safari/537.36'), false);
  for (const ua of ['Mozilla/5.0 (Web0S; Linux/SmartTV) AppleWebKit/538.2 (KHTML, like Gecko) Large Screen Safari/538.2 LG Browser/7.00.00(LGE; 60UH6550-UB; 03.00.15; 1; DTV_W16N); webOS.TV-2016; LG NetCast.TV-2013 Compatible (LGE, 60UH6550-UB, wireless)',
    'Mozilla/5.0 (PlayStation 4 9.00) AppleWebKit/605.1.15 (KHTML, like Gecko)']) {
    assert.equal(live.isAppleWebKit(ua), false, ua);
    assert.equal(live.browserName(ua), 'this browser', ua);
  }
  const fault = [{ attempt: relayAttempt(), fault: { kind: 'media', code: 'MediaMSEError' } }];
  assert.doesNotMatch(live.classifyLiveFailure(fault, context({ browser: 'Chrome', appleWebKit: true })).message, /Try Safari/);
  assert.match(live.classifyLiveFailure(fault, context({ browser: 'Chrome', appleWebKit: false })).message, /Try Safari/);
  const hevc = [{ attempt: relayAttempt(), fault: { kind: 'media', code: 'bufferAddCodecError', mediaInfo: { videoCodec: 'hvc1.1.6.L93.B0' } } }];
  assert.doesNotMatch(live.classifyLiveFailure(hevc, context({ browser: 'Firefox', appleWebKit: true, canDecode: () => false })).message, /Try Safari/);
});

test('player: an old fault from before the channel played does not explain a later drop or skip URLs on the next pass', async () => {
  const h = liveHarness({ url: 'http://p.example:8080/live/u/p/1.ts' });
  const cleanup = h.setup();
  // First rung: a decoder error, then the next rung (the .m3u8 twin) plays.
  h.fail({ transport: 'mpegts', kind: 'media', fatal: true, retryable: false, message: 'x', code: 'MediaMSEError' });
  h.playing();
  // Later the connection drops on every rung.
  for (let i = 0; i < 12 && !h.state.liveFailure; i++) { h.fail({ transport: h.last().transport, kind: 'network', fatal: true, retryable: true, message: 'x', code: 'Exception' }); }
  assert.equal(h.state.liveFailure.kind, 'stopped', 'not "a format Chrome can\'t decode"');
  cleanup();
});

test('player: a provider 429 stops at once: no relay retries, no refresh, no second pass', async () => {
  let refreshes = 0;
  const h = liveHarness({ url: 'https://cdn.example/live/u/p/7.ts', refresh: () => { refreshes++; return { url: 'https://cdn.example/live/u/p/7.ts?fresh=1' }; } });
  const cleanup = h.setup();
  h.fail({ transport: 'mpegts', kind: 'network', fatal: true, retryable: true, message: 'x', httpStatus: 429 });
  await flush();
  assert.equal(h.state.attached.length, 1);
  assert.equal(h.state.liveFailure.kind, 'provider-busy');
  assert.equal(refreshes, 0);
  cleanup();
});

test('player: after a provider 403 only the twin is tried once, then the explanation', async () => {
  const h = liveHarness({ url: 'http://p.example:8080/live/u/p/1.ts' });
  const cleanup = h.setup();
  h.fail({ transport: 'mpegts', kind: 'network', fatal: true, retryable: false, message: 'x', httpStatus: 403 });
  assert.match(decodeURIComponent(h.last().url), /1\.m3u8/);
  h.fail({ transport: 'hls', kind: 'network', fatal: true, retryable: false, message: 'x', code: 'manifestLoadError' });
  await flush();
  assert.equal(h.state.attached.length, 2);
  assert.equal(h.state.liveFailure.kind, 'provider-refused');
  cleanup();
});

test('player: a refreshed Stalker link is kept for restarts and external players', async () => {
  const overrideRef = { current: null };
  const stale = 'http://portal.example/play/live.php?stream=9&extension=ts&play_token=old';
  const h = liveHarness({ url: stale, headers: { 'User-Agent': 'MAG200' }, overrideRef,
    refresh: () => ({ url: 'http://portal.example/play/live.php?stream=9&extension=ts&play_token=new', headers: { 'User-Agent': 'MAG200' } }) });
  const cleanup = h.setup();
  for (let i = 0; i < 4 && h.state.refreshes === 0; i++) h.fail({ transport: h.last().transport, kind: 'network', fatal: true, retryable: false, message: 'x', httpStatus: 410 });
  await flush(); await flush();
  assert.equal(overrideRef.current.from, stale);
  assert.match(overrideRef.current.url, /play_token=new/);
  cleanup();
  // A restart (Retry, stall reconnect) starts from the fresh link.
  const again = liveHarness({ url: stale, overrideRef });
  const cleanupAgain = again.setup();
  assert.match(decodeURIComponent(again.last().url), /play_token=new/);
  cleanupAgain();
});

test('a 403 seen only through ARVIO servers still lets the viewer\'s own connection try once', () => {
  const plan = live.planLiveAttempts({ url: 'https://panel.example/live/u/p/9.m3u8', headers: { 'User-Agent': 'VLC' } }, CHROME, relayRoutes);
  const relay = plan.attempts.find((a) => a.route === 'relay');
  const direct = plan.attempts.find((a) => a.route === 'direct' && a.variant === 'original');
  assert.ok(relay && direct, 'header channels try the relay first, then directly');
  assert.ok(plan.attempts.indexOf(relay) < plan.attempts.indexOf(direct));
  const ctx = { relayTicket: true, relayNeedsTicket: true };
  const walled = [{ attempt: relay, fault: { kind: 'network', httpStatus: 403 } }];
  assert.equal(live.attemptAllowed(direct, walled, ctx), true);
  // The direct answer is the provider's own: after a direct 403, stop.
  const refusedHere = [...walled, { attempt: direct, fault: { kind: 'network', httpStatus: 403 } }];
  const other = plan.attempts.find((a) => a.route === 'direct' && a !== direct && a.variant === 'original');
  if (other) assert.equal(live.attemptAllowed(other, refusedHere, ctx), false);
  // Its http:// IP video server is then explained, not "subscription expired".
  const blocked = [...walled, { attempt: direct, fault: { kind: 'network', resourceUrl: 'http://185.245.1.253/hls/a.ts' } }];
  assert.equal(live.classifyLiveFailure(blocked, { browser: 'Chrome', relayAvailable: true, relayTicket: true }).kind, 'insecure-media');
});

test('Strong8K-style failure: timeouts directly, 403 only at the app backend, http:// IP segments', () => {
  const a = (route, variant, transport, hlsEngine) => ({ url: 'x', route, variant, transport, hlsEngine, target: 'x' });
  const faults = [
    { attempt: a('direct', 'original', 'hls', 'mse'), fault: { kind: 'network', code: 'STARTUP_TIMEOUT', resourceUrl: 'http://185.245.1.253/hls/a.ts' } },
    { attempt: a('direct', 'twin', 'mpegts'), fault: { kind: 'unsupported', code: 'FormatUnsupported' } },
    { attempt: a('manifest-relay', 'original', 'hls', 'mse'), fault: { kind: 'network', httpStatus: 403, code: 'manifestLoadError' } }
  ];
  const ctx = { browser: 'Chrome', relayAvailable: true, relayTicket: true };
  assert.equal(live.classifyLiveFailure(faults, ctx).kind, 'insecure-media');
  // Without the segment address: not "your subscription", just unreachable.
  const unknown = faults.map((f, i) => i === 0 ? { ...f, fault: { kind: 'network', code: 'STARTUP_TIMEOUT' } } : f);
  assert.notEqual(live.classifyLiveFailure(unknown, ctx).kind, 'provider-refused');
});

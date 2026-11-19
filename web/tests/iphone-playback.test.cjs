const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
// Values built inside the vm sandbox have that realm's prototypes.
const plain = (value) => JSON.parse(JSON.stringify(value));

// ---------- Source ranking on phones/tablets ----------

const rankMocks = {
  './debrid': { parseDebridStream: (url) => (String(url).includes('/torbox/') ? { provider: 'torbox' } : null), isUncachedDebridStream: () => false },
  './streamCompatibility': {
    audioDecodableForDevice: () => true, videoDecodableForDevice: () => true,
    isDirectPlayableStream: (s) => /\.mp4$/.test(s.url ?? ''), isBrowserPlayableStream: () => true
  }
};
const rank = load('lib/sourceRank.ts', rankMocks);
const remux4k = { source: 'Movie 2160p REMUX DV HDR TrueHD Atmos', quality: '4K', size: '62 GB', url: 'https://cdn.example/movie.mkv', addonName: 'A' };
const web1080 = { source: 'Movie 1080p WEB-DL', quality: '1080p', size: '4.2 GB', url: 'https://cdn.example/movie.mp4', addonName: 'B' };
const web4k = { source: 'Movie 2160p WEB-DL', quality: '4K', size: '18 GB', url: 'https://cdn.example/movie-4k.mp4', addonName: 'C' };

test('phones rank a direct-playable 1080p above 4K remuxes and huge files', () => {
  const order = [remux4k, web4k, web1080].sort((a, b) => rank.sourcePickerScore(b, 'browser', 'mobile') - rank.sourcePickerScore(a, 'browser', 'mobile'));
  assert.deepEqual(order.map((s) => s.addonName), ['B', 'C', 'A']);
  assert.ok(rank.sourcePickerScore(remux4k, 'browser', 'mobile') < rank.sourcePickerScore(web1080, 'browser', 'mobile') - 500);
});

test('desktop and external players keep the quality-first order', () => {
  const desktop = [web1080, remux4k].sort((a, b) => rank.sourcePickerScore(b, 'browser', 'desktop') - rank.sourcePickerScore(a, 'browser', 'desktop'));
  assert.equal(desktop[0], remux4k);
  // VLC/Infuse on a phone still want the best release.
  assert.ok(rank.sourcePickerScore(remux4k, 'external', 'mobile') > rank.sourcePickerScore(web1080, 'external', 'mobile'));
  assert.equal(rank.sourcePickerScore(remux4k, 'external', 'mobile'), rank.sourcePickerScore(remux4k, 'external', 'desktop'));
});

test('device profile detection treats iPhone, iPad (desktop UA) and Android as mobile', () => {
  const ua = {
    iphone: 'Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.0 Mobile/15E148 Safari/604.1',
    ipad: 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.0 Safari/605.1.15',
    android: 'Mozilla/5.0 (Linux; Android 14; Pixel 8) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0 Mobile Safari/537.36',
    windows: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0 Safari/537.36'
  };
  assert.equal(rank.detectDeviceProfile({ userAgent: ua.iphone }), 'mobile');
  assert.equal(rank.detectDeviceProfile({ userAgent: ua.ipad, maxTouchPoints: 5 }), 'mobile');
  assert.equal(rank.detectDeviceProfile({ userAgent: ua.ipad, maxTouchPoints: 0 }), 'desktop');
  assert.equal(rank.detectDeviceProfile({ userAgent: ua.android }), 'mobile');
  assert.equal(rank.detectDeviceProfile({ userAgent: ua.windows }), 'desktop');
  assert.equal(rank.detectDeviceProfile(undefined), 'desktop');
});

// ---------- Preferred addon / debrid wait and verification ----------

const selection = load('lib/autoplaySelection.ts');
function clock() {
  let now = 0;
  return { now: () => now, sleep: async (ms) => { now += ms; }, advance: (ms) => { now += ms; } };
}
const free = { url: 'https://free.example/a.mp4', addonId: 'freehost', source: 'Free' };
const debrid = { url: 'https://torrentio.example/resolve/torbox/key12345/abc/1', addonId: 'torrentio', source: 'TB' };
const remembered = { url: 'https://mine.example/b.mp4', addonId: 'myaddon', source: 'Mine', behaviorHints: { bingeGroup: 'mine|1080p' } };
const isDebrid = (s) => s.url.includes('/torbox/');

function storage(initial = {}) {
  const values = new Map(Object.entries(initial));
  return { getItem: (k) => values.get(k) ?? null, setItem: (k, v) => values.set(k, String(v)), values };
}

test('autoplay waits a bounded window for a debrid source instead of taking the fastest addon', async () => {
  const c = clock();
  let list = [free];
  // Debrid arrives 1.2 s after the free host.
  const result = await selection.chooseAutoplaySource({
    candidates: () => { if (c.now() >= 1200) list = [debrid, free]; return list; },
    searching: () => true, isDebrid, now: c.now, sleep: c.sleep, preferredWaitMs: 2500, pollMs: 150
  });
  assert.equal(result.stream, debrid);
  assert.equal(result.reason, 'preferred');
  assert.ok(result.waitedMs >= 1200 && result.waitedMs < 2500);
});

test('autoplay gives up waiting after the window and plays the best available source', async () => {
  const c = clock();
  const result = await selection.chooseAutoplaySource({
    candidates: () => [free], searching: () => true, isDebrid, now: c.now, sleep: c.sleep, preferredWaitMs: 2500
  });
  assert.equal(result.stream, free);
  assert.equal(result.reason, 'timeout');
  assert.ok(result.waitedMs >= 2500 && result.waitedMs <= 2700);
});

test('a finished search never waits, and an immediately preferred source starts at once', async () => {
  const c = clock();
  const done = await selection.chooseAutoplaySource({ candidates: () => [free], searching: () => false, isDebrid, now: c.now, sleep: c.sleep });
  assert.equal(done.stream, free); assert.equal(done.reason, 'search-complete'); assert.equal(done.waitedMs, 0);
  const instant = await selection.chooseAutoplaySource({ candidates: () => [debrid, free], searching: () => true, isDebrid, now: c.now, sleep: c.sleep });
  assert.equal(instant.stream, debrid); assert.equal(instant.waitedMs, 0);
});

test('the addon that last played on this device leads its method tier', async () => {
  const store = storage();
  assert.equal(selection.rememberSuccessfulSource(remembered, store, 1000), true);
  const preference = selection.loadAutoplayPreference(store);
  assert.deepEqual(plain(preference), { addonId: 'myaddon', bingeGroup: 'mine|1080p', at: 1000 });
  const c = clock();
  const result = await selection.chooseAutoplaySource({
    candidates: () => [free, remembered], searching: () => true, preference, isDebrid, now: c.now, sleep: c.sleep
  });
  assert.equal(result.stream, remembered);
  assert.equal(result.waitedMs, 0);
  // Never trade direct play for conversion just because the addon is preferred.
  const remuxed = { ...remembered, url: 'https://mine.example/b.mkv' };
  const ordered = selection.orderByPreference([free, remuxed], preference, (s) => (s.url.endsWith('.mkv') ? 'remux' : 'direct'));
  assert.equal(ordered[0], free);
});

test('home server, IPTV and malformed preferences are never remembered', () => {
  const store = storage();
  assert.equal(selection.rememberSuccessfulSource({ addonId: 'iptv_xtream_vod' }, store), false);
  assert.equal(selection.rememberSuccessfulSource({ addonId: 'plex', homeServer: { serverId: 's', itemId: 'i' } }, store), false);
  assert.equal(store.values.size, 0);
  const tampered = storage({ [selection.AUTOPLAY_PREFERENCE_KEY]: JSON.stringify({ addonId: '<script>', bingeGroup: 7 }) });
  assert.deepEqual(plain(selection.loadAutoplayPreference(tampered)), {});
  assert.deepEqual(plain(selection.loadAutoplayPreference(storage({ [selection.AUTOPLAY_PREFERENCE_KEY]: '{oops' }))), {});
});

test('verification demotes a source proven unplayable and keeps unknown verdicts', async () => {
  const c = clock();
  const a = { url: 'https://h.example/a.mp4', addonId: 'x' };
  const b = { url: 'https://h.example/b.mp4', addonId: 'x' };
  const d = { url: 'https://h.example/d.mp4', addonId: 'x' };
  let probed;
  const result = await selection.chooseAutoplaySource({
    candidates: () => [a, b, d], searching: () => false, isDebrid, now: c.now, sleep: c.sleep, verifyCount: 2,
    verify: async (head) => { probed = head; return new Map([[a, false], [b, null]]); }
  });
  assert.equal(probed.length, 2, 'only the top candidates are probed');
  assert.ok(probed[0] === a && probed[1] === b);
  assert.equal(result.stream, b);
  assert.equal(result.rejected, 1);
});

test('verification is bounded by its time budget and falls back gracefully', async () => {
  const a = { url: 'https://h.example/a.mp4' };
  const b = { url: 'https://h.example/b.mp4' };
  let signal;
  // Real timers here: a verifier that never resolves must not block playback.
  const started = Date.now();
  const hung = await selection.chooseAutoplaySource({
    candidates: () => [a, b], searching: () => false, isDebrid, verifyBudgetMs: 60,
    verify: (_head, _budget, probeSignal) => { signal = probeSignal; return new Promise(() => {}); }
  });
  assert.equal(hung.stream, a);
  assert.ok(Date.now() - started < 1000);
  assert.equal(signal.aborted, true, 'probes are cancelled once the budget is spent');
  const c = clock();
  const thrown = await selection.chooseAutoplaySource({ candidates: () => [a], searching: () => false, isDebrid, now: c.now, sleep: c.sleep, verify: async () => { throw new Error('boom'); } });
  assert.equal(thrown.stream, a);
  const allBad = await selection.chooseAutoplaySource({ candidates: () => [a, b], searching: () => false, isDebrid, now: c.now, sleep: c.sleep, verify: async () => new Map([[a, false], [b, false]]) });
  assert.equal(allBad.stream, a, 'still tries the best source when every probe fails');
});

test('no candidate: waits while searching, stops when the search ends or is aborted', async () => {
  const c = clock();
  let searching = true;
  const none = await selection.chooseAutoplaySource({ candidates: () => [], searching: () => { if (c.now() > 600) searching = false; return searching; }, isDebrid, now: c.now, sleep: c.sleep });
  assert.equal(none.stream, null); assert.equal(none.reason, 'none');
  const controller = new AbortController();
  controller.abort();
  const aborted = await selection.chooseAutoplaySource({ candidates: () => [], searching: () => true, isDebrid, signal: controller.signal, now: c.now, sleep: c.sleep });
  assert.equal(aborted.reason, 'aborted');
});

test('pre-play probes only read direct-play files and never resolve debrid links', async () => {
  const verifyCalls = [];
  const verifier = load('lib/autoplayVerify.ts', {
    './debrid': {
      parseDebridStream: (url) => (String(url).includes('/torbox/') ? { provider: 'torbox' } : null),
      cachedDebridDirectUrl: (url) => (String(url).includes('resolved') ? 'https://cdn.torbox.example/file.mp4' : null)
    },
    './streamCompatibility': {
      playbackPlan: (s) => ({ method: s.url.endsWith('.mkv') ? 'remux' : 'direct' }),
      streamTransport: (s) => (s.url.includes('.m3u8') ? 'hls' : 'file')
    },
    './streamVerify': {
      verifyStream: async (url) => { verifyCalls.push(url); return url.includes('bad') ? { playable: false } : url.includes('good') ? { playable: true } : null; },
      cachedVerdict: () => undefined
    }
  }, { VideoDecoder: function () {}, AudioDecoder: function () {} });
  assert.equal(verifier.probeUrlFor({ url: 'https://h.example/good.mp4' }), 'https://h.example/good.mp4');
  assert.equal(verifier.probeUrlFor({ url: 'https://h.example/movie.mkv' }), null, 'remux candidates probe themselves');
  assert.equal(verifier.probeUrlFor({ url: 'https://h.example/live.m3u8' }), null);
  assert.equal(verifier.probeUrlFor({ url: 'https://tio.example/torbox/k/hash/1' }), null, 'unresolved debrid would create a provider download');
  assert.equal(verifier.probeUrlFor({ url: 'https://tio.example/torbox/resolved/hash/1' }), 'https://cdn.torbox.example/file.mp4');
  assert.equal(verifier.probeUrlFor({ url: 'https://h.example/good.mp4', behaviorHints: { proxyHeaders: { request: { Referer: 'x' } } } }), null);
  assert.equal(verifier.canProbeDecoders({}), false, 'no WebCodecs (older iOS) means no verdicts');
  const good = { url: 'https://h.example/good.mp4' };
  const bad = { url: 'https://h.example/bad.mp4' };
  const remux = { url: 'https://h.example/movie.mkv' };
  const reported = [];
  const verdicts = await verifier.verifyAutoplayCandidates([good, bad, remux], 500, new AbortController().signal, (stream, verdict) => reported.push([stream.url, verdict]));
  assert.deepEqual(plain(verifyCalls), [good.url, bad.url]);
  // Unprobeable candidates are reported at once; probed ones as they finish.
  assert.deepEqual(plain(reported[0]), [remux.url, null]);
  assert.equal(reported.length, 3);
  assert.equal(verdicts.get(good), true);
  assert.equal(verdicts.get(bad), false);
  assert.equal(verdicts.get(remux), null);
});

// ---------- Fullscreen, PiP, AirPlay, Media Session ----------

const platform = load('lib/playerPlatform.ts');
function fakeVideo(overrides = {}) {
  const listeners = new Map();
  return {
    readyState: 1, calls: [],
    addEventListener: (name, fn) => listeners.set(name, fn), removeEventListener: (name) => listeners.delete(name), listeners,
    ...overrides
  };
}

test('iPhone Safari (no element fullscreen) falls back to the native video player', async () => {
  const container = {};
  const video = fakeVideo({ webkitEnterFullscreen() { this.calls.push('enter'); }, webkitExitFullscreen() { this.calls.push('exit'); }, webkitSupportsFullscreen: true });
  const doc = { fullscreenEnabled: false };
  assert.equal(platform.fullscreenStrategy(container, video, doc), 'video');
  assert.equal(await platform.toggleFullscreen(container, video, doc), 'entered');
  assert.deepEqual(video.calls, ['enter']);
  video.webkitDisplayingFullscreen = true;
  assert.equal(platform.isFullscreenActive(video, doc), true);
  assert.equal(await platform.toggleFullscreen(container, video, doc), 'exited');
  assert.deepEqual(video.calls, ['enter', 'exit']);
});

test('iPhone fullscreen before metadata asks the user to wait instead of throwing', async () => {
  const video = fakeVideo({ readyState: 0, webkitEnterFullscreen() { throw new Error('InvalidStateError'); } });
  assert.equal(await platform.toggleFullscreen({}, video, {}), 'needs-metadata');
});

test('element fullscreen is preferred where it exists, prefixed API next, none otherwise', async () => {
  const calls = [];
  const container = { requestFullscreen: async () => calls.push('element') };
  const video = fakeVideo({ webkitEnterFullscreen: () => calls.push('video') });
  assert.equal(platform.fullscreenStrategy(container, video, { fullscreenEnabled: true }), 'element');
  assert.equal(await platform.toggleFullscreen(container, video, { fullscreenEnabled: true }), 'entered');
  assert.deepEqual(calls, ['element']);
  assert.equal(platform.fullscreenStrategy({ webkitRequestFullscreen: () => {} }, null, {}), 'webkit-element');
  assert.equal(platform.fullscreenStrategy({}, fakeVideo(), {}), 'none');
  assert.equal(await platform.toggleFullscreen({}, fakeVideo(), {}), 'unsupported');
  // A refused element request on WebKit still reaches native video fullscreen.
  const refusing = { requestFullscreen: async () => { throw new Error('denied'); } };
  const fallback = [];
  assert.equal(await platform.toggleFullscreen(refusing, fakeVideo({ webkitEnterFullscreen: () => fallback.push('video') }), {}), 'entered');
  assert.deepEqual(fallback, ['video']);
  // Exit works from either API.
  let exited = false;
  assert.equal(await platform.toggleFullscreen(container, video, { fullscreenElement: {}, exitFullscreen: async () => { exited = true; } }), 'exited');
  assert.equal(exited, true);
});

test('fullscreen listeners cover standard, prefixed and iOS video events', () => {
  const docEvents = new Set();
  const doc = { addEventListener: (n) => docEvents.add(n), removeEventListener: (n) => docEvents.delete(n) };
  const video = fakeVideo();
  const stop = platform.onFullscreenChange(video, () => {}, doc);
  assert.deepEqual([...docEvents].sort(), ['fullscreenchange', 'webkitfullscreenchange']);
  assert.ok(video.listeners.has('webkitbeginfullscreen') && video.listeners.has('webkitendfullscreen'));
  stop();
  assert.equal(docEvents.size, 0);
  assert.equal(video.listeners.size, 0);
});

test('picture in picture uses the standard API or Safari presentation mode, only where supported', async () => {
  const requested = [];
  const standard = fakeVideo({ requestPictureInPicture: async () => requested.push('pip'), disablePictureInPicture: false });
  assert.equal(platform.pipStrategy(standard, { pictureInPictureEnabled: true }), 'standard');
  assert.equal(await platform.togglePictureInPicture(standard, { pictureInPictureEnabled: true }), true);
  assert.deepEqual(requested, ['pip']);
  const safari = fakeVideo({ webkitSupportsPresentationMode: (m) => m === 'picture-in-picture', webkitSetPresentationMode(mode) { this.webkitPresentationMode = mode; }, webkitPresentationMode: 'inline' });
  assert.equal(platform.pipStrategy(safari, {}), 'webkit');
  await platform.togglePictureInPicture(safari, {});
  assert.equal(safari.webkitPresentationMode, 'picture-in-picture');
  assert.equal(platform.isPipActive(safari, {}), true);
  await platform.togglePictureInPicture(safari, {});
  assert.equal(safari.webkitPresentationMode, 'inline');
  assert.equal(platform.pipStrategy(fakeVideo(), {}), 'none');
  assert.equal(platform.pipStrategy(fakeVideo({ requestPictureInPicture: async () => {}, disablePictureInPicture: true }), { pictureInPictureEnabled: true }), 'none');
});

test('AirPlay is offered only on Safari and only for media a receiver can fetch', () => {
  assert.equal(platform.airPlaySupported({}), false);
  assert.equal(platform.airPlaySupported({ WebKitPlaybackTargetAvailabilityEvent: function () {} }), true);
  const picker = () => {};
  assert.equal(platform.airPlayUsable(fakeVideo({ webkitShowPlaybackTargetPicker: picker, disableRemotePlayback: false })), true);
  // hls.js / remux over ManagedMediaSource sets disableRemotePlayback.
  assert.equal(platform.airPlayUsable(fakeVideo({ webkitShowPlaybackTargetPicker: picker, disableRemotePlayback: true })), false);
  assert.equal(platform.airPlayUsable(fakeVideo()), false);
  const video = fakeVideo();
  const seen = [];
  const stop = platform.watchAirPlayAvailability(video, (available) => seen.push(available));
  video.listeners.get('webkitplaybacktargetavailabilitychanged')({ availability: 'available' });
  video.listeners.get('webkitplaybacktargetavailabilitychanged')({ availability: 'not-available' });
  assert.deepEqual(seen, [true, false]);
  stop();
});

test('media session publishes metadata, artwork and transport actions, and clears them on close', () => {
  const handlers = new Map();
  const positions = [];
  class MediaMetadata { constructor(init) { Object.assign(this, init); } }
  const session = { metadata: null, playbackState: 'none', setActionHandler: (action, fn) => handlers.set(action, fn), setPositionState: (s) => positions.push(s) };
  const scope = { navigator: { mediaSession: session }, MediaMetadata };
  const calls = [];
  const unbind = platform.bindMediaSession({
    title: 'Show', subtitle: 'S1 E2 · Pilot', poster: 'https://image.tmdb.org/t/p/w780/p.jpg', backdrop: 'https://image.tmdb.org/t/p/w1280/b.jpg', still: 'http://insecure/s.jpg'
  }, {
    play: () => calls.push('play'), pause: () => calls.push('pause'),
    seekBy: (d) => calls.push(['seekBy', d]), seekTo: (t, fast) => calls.push(['seekTo', t, fast]), next: () => calls.push('next')
  }, scope);
  assert.equal(session.metadata.title, 'Show');
  assert.equal(session.metadata.artist, 'S1 E2 · Pilot');
  assert.deepEqual(plain(session.metadata.artwork.map((a) => a.src)), ['https://image.tmdb.org/t/p/w780/p.jpg', 'https://image.tmdb.org/t/p/w1280/b.jpg']);
  handlers.get('play')(); handlers.get('pause')();
  handlers.get('seekbackward')({}); handlers.get('seekforward')({ seekOffset: 30 });
  handlers.get('seekto')({ seekTime: 120, fastSeek: true }); handlers.get('nexttrack')();
  assert.deepEqual(calls, ['play', 'pause', ['seekBy', -10], ['seekBy', 30], ['seekTo', 120, true], 'next']);
  assert.equal(handlers.get('previoustrack'), null);
  assert.equal(platform.updateMediaPosition({ duration: 3600, currentTime: 4000, playbackRate: 1.5, paused: false }, scope), true);
  assert.deepEqual(plain(positions.at(-1)), { duration: 3600, position: 3600, playbackRate: 1.5 });
  assert.equal(session.playbackState, 'playing');
  assert.equal(platform.updateMediaPosition({ duration: Infinity, currentTime: 5, playbackRate: 1, paused: true }, scope), false, 'live streams have no scrubber');
  unbind();
  assert.equal(session.metadata, null);
  assert.ok([...handlers.values()].every((h) => h === null));
});

test('live TV registers no seek or next actions; missing Media Session is a no-op', () => {
  const handlers = new Map();
  const scope = { navigator: { mediaSession: { setActionHandler: (a, fn) => { if (a === 'seekto') throw new Error('unsupported'); handlers.set(a, fn); } } } };
  platform.bindMediaSession({ title: 'Channel' }, { play: () => {}, pause: () => {} }, scope);
  assert.equal(handlers.get('seekbackward'), null);
  assert.equal(handlers.get('nexttrack'), null);
  assert.equal(typeof platform.bindMediaSession({ title: 'x' }, { play() {}, pause() {} }, {}), 'function');
});

// ---------- Add to Home Screen tip ----------

const tip = load('lib/homeScreenTip.ts', { './playerPlatform': platform }, { window: undefined });
const IPHONE_SAFARI = 'Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.0 Mobile/15E148 Safari/604.1';

test('the home-screen tip is iOS Safari only, after first playback, once, and never in standalone mode', () => {
  assert.equal(platform.isIosSafari({ userAgent: IPHONE_SAFARI }), true);
  assert.equal(platform.isIosSafari({ userAgent: IPHONE_SAFARI.replace('Version/18.0', 'CriOS/130.0') }), false);
  assert.equal(platform.isIosSafari({ userAgent: 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/18.0 Safari/605.1.15', maxTouchPoints: 5 }), true);
  assert.equal(platform.isIosSafari({ userAgent: 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/18.0 Safari/605.1.15', maxTouchPoints: 0 }), false);
  assert.equal(platform.isStandaloneDisplay({ navigator: { standalone: true } }), true);
  assert.equal(platform.isStandaloneDisplay({ navigator: {}, matchMedia: (q) => ({ matches: q === '(display-mode: standalone)' }) }), true);
  assert.equal(platform.isStandaloneDisplay({ navigator: {}, matchMedia: () => ({ matches: false }) }), false);

  const store = storage();
  const env = { storage: store, iosSafari: true, standalone: false };
  assert.equal(tip.shouldShowHomeScreenTip(env), false, 'not before a playback started');
  assert.equal(tip.markHomeScreenTipEligible(env), true);
  assert.equal(tip.shouldShowHomeScreenTip(env), true);
  assert.equal(tip.shouldShowHomeScreenTip({ ...env, standalone: true }), false);
  tip.dismissHomeScreenTip(env);
  assert.equal(tip.markHomeScreenTipEligible(env), false, 'dismissal is permanent');
  assert.equal(tip.shouldShowHomeScreenTip(env), false);
  assert.equal(tip.markHomeScreenTipEligible({ storage: storage(), iosSafari: false, standalone: false }), false);
  assert.equal(tip.markHomeScreenTipEligible({ storage: storage(), iosSafari: true, standalone: true }), false);
});

// ---------- Playback telemetry detail ----------

const telemetry = load('lib/playbackTelemetry.ts');
test('failure detail uses allow-listed labels only', () => {
  const nav = (userAgent, maxTouchPoints = 0) => ({ userAgent, maxTouchPoints });
  assert.equal(telemetry.playbackPlatform(nav(IPHONE_SAFARI)), 'iphone');
  assert.equal(telemetry.playbackPlatform(nav('Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/18.0 Safari/605.1.15', 5)), 'ipad');
  assert.equal(telemetry.playbackPlatform(nav('Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/18.0 Safari/605.1.15')), 'mac_safari');
  assert.equal(telemetry.playbackPlatform(nav('Mozilla/5.0 (Linux; Android 14) Chrome/130.0 Mobile Safari/537.36')), 'android');
  assert.equal(telemetry.playbackPlatform(nav('Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 Chrome/130.0 Safari/537.36 Edg/130.0')), 'chromium_desktop');
  assert.equal(telemetry.playbackPlatform(nav('Mozilla/5.0 (X11; Linux x86_64; rv:131.0) Gecko/20100101 Firefox/131.0')), 'firefox');
  assert.equal(telemetry.playbackPlatform(undefined), 'other');

  const detail = telemetry.playbackAttemptDetail({ url: 'https://cdn.example/path/Secret.Movie.2024.mkv?token=abc', addonId: 'torrentio', addonName: 'Torrentio', source: 'Secret Movie' }, { debrid: true, nav: nav(IPHONE_SAFARI) });
  assert.deepEqual(plain(detail), { platform: 'iphone', method: 'direct', container: 'mkv', host_type: 'debrid' });
  assert.doesNotMatch(JSON.stringify(detail), /secret|token|cdn|torrentio/i);
  assert.deepEqual(plain(telemetry.playbackAttemptDetail({ url: 'https://x/y.mkv', remux: true, addonId: 'a' }, { nav: nav('') })),
    { platform: 'other', method: 'remux', container: 'mkv', host_type: 'addon_http' });
  assert.equal(telemetry.playbackContainer({ url: 'https://x/master.m3u8', transport: 'hls' }), 'm3u8');
  assert.equal(telemetry.playbackContainer({ url: 'https://x/stream', transcoded: true }), 'm3u8');
  assert.equal(telemetry.playbackContainer({ url: 'https://x/live/1.ts' }), 'ts');
  assert.equal(telemetry.playbackContainer({ url: 'https://x/watch?id=1' }), 'unknown');
  assert.equal(telemetry.playbackMethod({ url: 'https://x/a.m3u8' }), 'hls');
  assert.equal(telemetry.playbackHostType({ homeServer: { serverId: 's', itemId: 'i' } }), 'home_server');
  assert.equal(telemetry.playbackHostType({ addonId: 'iptv_xtream_vod' }), 'iptv');
  assert.equal(telemetry.playbackHostType({ addonId: 'whatever' }, { liveTv: true }), 'iptv');
});

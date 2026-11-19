const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const { load } = require('./load.cjs');
// Values from vm contexts carry foreign prototypes; compare plain data.
const plain = (value) => JSON.parse(JSON.stringify(value));

const root = path.resolve(__dirname, '..');

// ---------------------------------------------------------------------------
// Device classes before first paint.

const UAS = {
  tizen: 'Mozilla/5.0 (SMART-TV; LINUX; Tizen 6.5) AppleWebKit/537.36 (KHTML, like Gecko) 85.0.4183.93/6.5 TV Safari/537.36',
  webos: 'Mozilla/5.0 (Web0S; Linux/SmartTV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 WebAppManager',
  xbox: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; Xbox; Xbox One) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0',
  visionPro: 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15',
  chrome: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36'
};

function runInlineScript(signals) {
  const { DEVICE_CLASS_SCRIPT } = load('lib/deviceClass.ts');
  const classes = new Set();
  const navigator = { userAgent: signals.userAgent, maxTouchPoints: signals.maxTouchPoints };
  vm.runInNewContext(DEVICE_CLASS_SCRIPT, {
    document: { documentElement: { classList: { add: (name) => classes.add(name) } } },
    navigator,
    window: { matchMedia: () => ({ matches: signals.coarseNoHover }) }
  });
  return { classes: [...classes], navigator };
}

test('the inline pre-paint script and the typed detector agree on every device', () => {
  const { deviceClasses } = load('lib/deviceClass.ts');
  const cases = [
    [{ userAgent: UAS.tizen, coarseNoHover: false, maxTouchPoints: 0 }, ['is-tv']],
    [{ userAgent: UAS.webos, coarseNoHover: false, maxTouchPoints: 0 }, ['is-tv']],
    [{ userAgent: UAS.xbox, coarseNoHover: false, maxTouchPoints: 0 }, ['is-tv', 'is-xbox']],
    // Vision Pro / iPad report a Mac UA: detected by capability, never UA.
    [{ userAgent: UAS.visionPro, coarseNoHover: true, maxTouchPoints: 5 }, ['no-hover']],
    [{ userAgent: UAS.visionPro, coarseNoHover: false, maxTouchPoints: 0 }, []],
    // A coarse report without touch points is not trusted (some TV/desktop shells).
    [{ userAgent: UAS.chrome, coarseNoHover: true, maxTouchPoints: 0 }, []],
    [{ userAgent: UAS.chrome, coarseNoHover: false, maxTouchPoints: 0 }, []]
  ];
  for (const [signals, expected] of cases) {
    assert.deepEqual(plain(deviceClasses(signals)), expected, signals.userAgent);
    assert.deepEqual(plain(runInlineScript(signals).classes), expected, `inline: ${signals.userAgent}`);
  }
});

test('Xbox Edge is asked for gamepad input emulation; other browsers are left alone', () => {
  assert.equal(runInlineScript({ userAgent: UAS.xbox, coarseNoHover: false, maxTouchPoints: 0 }).navigator.gamepadInputEmulation, 'gamepad');
  assert.equal(runInlineScript({ userAgent: UAS.chrome, coarseNoHover: false, maxTouchPoints: 0 }).navigator.gamepadInputEmulation, undefined);
});

test('the inline script can never break boot', () => {
  const { DEVICE_CLASS_SCRIPT } = load('lib/deviceClass.ts');
  assert.doesNotThrow(() => vm.runInNewContext(DEVICE_CLASS_SCRIPT, {}));
});

test('the layout runs the device script in <head> and allows pinch zoom', () => {
  const layout = fs.readFileSync(path.join(root, 'app/layout.tsx'), 'utf8');
  assert.match(layout, /dangerouslySetInnerHTML=\{\{ __html: DEVICE_CLASS_SCRIPT \}\}/);
  assert.doesNotMatch(layout, /maximumScale/);
});

test('Xbox controller tip shows once on Xbox only', () => {
  const { shouldShowXboxTip } = load('lib/deviceClass.ts');
  const root = (names) => ({ classList: { contains: (name) => names.includes(name) } });
  assert.equal(shouldShowXboxTip(root(['is-tv', 'is-xbox']), false), true);
  assert.equal(shouldShowXboxTip(root(['is-tv', 'is-xbox']), true), false);
  assert.equal(shouldShowXboxTip(root(['is-tv']), false), false);
});

// ---------------------------------------------------------------------------
// Cached-data validation.

function storageModule(entries) {
  const store = new Map(Object.entries(entries).map(([k, v]) => [k, typeof v === 'string' ? v : JSON.stringify(v)]));
  const localStorage = {
    getItem: (k) => (store.has(k) ? store.get(k) : null), setItem: (k, v) => store.set(k, String(v)), removeItem: (k) => store.delete(k)
  };
  // Object.keys(localStorage) enumerates stored keys in browsers.
  const proxy = new Proxy(localStorage, { ownKeys: () => [...store.keys()], getOwnPropertyDescriptor: (t, k) => (store.has(k) ? { enumerable: true, configurable: true, value: store.get(k) } : Reflect.getOwnPropertyDescriptor(t, k)) });
  const module = load('lib/storage.ts', {}, { window: { localStorage: proxy } });
  return { module, store };
}

test('a poisoned catalog row cache no longer crashes rails: malformed items are dropped', () => {
  const at = Date.now();
  const { module } = storageModule({
    'arvio.web.catalogRows.v3': {
      trending: { at, category: { id: 'trending', title: 'Trending', items: [
        { id: 1, mediaType: 'movie', title: null, genreIds: 'x', image: 5 },
        null,
        { id: 2 },
        { mediaType: 'tv' },
        'junk',
        { id: 3, mediaType: 'tv', title: 'Ok', genreIds: [18] }
      ] } },
      emptied: { at, category: { id: 'e', title: 'E', items: [null] } },
      broken: null,
      noCategory: { at }
    }
  });
  const cache = plain(module.loadStored('arvio.web.catalogRows.v3', {}));
  assert.deepEqual(Object.keys(cache), ['trending']);
  assert.deepEqual(cache.trending.category.items, [
    { id: 1, mediaType: 'movie', title: '' },
    { id: 3, mediaType: 'tv', title: 'Ok', genreIds: [18] }
  ]);
});

test('list caches and profiles are validated; a wrong envelope reads as the fallback', () => {
  const { module } = storageModule({
    'arvio.web.cw.v4:p1': { at: 1, items: [null, { id: 9, mediaType: 'tv' }] },
    'arvio.web.watchlist.v1:p1': [1, 2, 3],
    'arvio.web.profiles': { a: 1 },
    'arvio.web.settings': { catalogs: null }
  });
  assert.deepEqual(plain(module.loadStored('arvio.web.cw.v4:p1', null)), { at: 1, items: [{ id: 9, mediaType: 'tv', title: '' }] });
  assert.equal(module.loadStored('arvio.web.watchlist.v1:p1', null), null);
  assert.deepEqual(plain(module.loadStored('arvio.web.profiles', [])), []);
  // Unvalidated keys are returned exactly as stored.
  assert.deepEqual(plain(module.loadStored('arvio.web.settings', {})), { catalogs: null });
});

test('a crashing screen clears only its rebuildable caches', () => {
  const { module, store } = storageModule({
    'arvio.web.catalogRows.v3': {}, 'arvio.web.cw.v4:p1': {}, 'arvio.web.cw.v4:p2': {},
    'arvio.web.settings': {}, 'arvio.web.profiles': [], 'arvio.web.watchlist.v1:p1': {}
  });
  const removed = plain(module.clearScreenCaches('home')).sort();
  assert.deepEqual(removed, ['arvio.web.catalogRows.v3', 'arvio.web.cw.v4:p1', 'arvio.web.cw.v4:p2']);
  assert.deepEqual([...store.keys()].sort(), ['arvio.web.profiles', 'arvio.web.settings', 'arvio.web.watchlist.v1:p1']);
  assert.deepEqual(plain(module.clearScreenCaches('settings')), []);
});

// ---------------------------------------------------------------------------
// Gamepad mapping.

function padModule() {
  return load('lib/gamepadNav.ts', { './hoverParity': { enterKbdNav() {} }, './tvNav': {}, './playerCommands': {} });
}
const pad = (pressed = []) => ({ buttons: Array.from({ length: 17 }, (_, i) => pressed.includes(i)), axes: [0, 0, 0, 0] });

test('Y: a tap toggles fullscreen on release, a hold opens sources once', () => {
  const { computeFiredIntents, LONG_PRESS_MS } = padModule();
  const clock = new Map();
  assert.deepEqual(plain(computeFiredIntents([pad([3])], clock, 0)), []);
  assert.deepEqual(plain(computeFiredIntents([pad([3])], clock, 100)), []);
  assert.deepEqual(plain(computeFiredIntents([pad()], clock, 150)), ['fullscreen']);
  assert.deepEqual(plain(computeFiredIntents([pad([3])], clock, 1000)), []);
  assert.deepEqual(plain(computeFiredIntents([pad([3])], clock, 1000 + LONG_PRESS_MS)), ['sources']);
  assert.deepEqual(plain(computeFiredIntents([pad([3])], clock, 2000 + LONG_PRESS_MS)), [], 'fires once per hold');
  assert.deepEqual(plain(computeFiredIntents([pad()], clock, 2100 + LONG_PRESS_MS)), [], 'release after a hold is silent');
});

test('A/B/X/LB/RB keep their meaning; View is its own intent', () => {
  const { computeFiredIntents } = padModule();
  const fire = (button) => plain(computeFiredIntents([pad([button])], new Map(), 0));
  assert.deepEqual(fire(0), ['accept']);
  assert.deepEqual(fire(1), ['back']);
  assert.deepEqual(fire(2), ['playpause']);
  assert.deepEqual(fire(4), ['seekBack']);
  assert.deepEqual(fire(5), ['seekForward']);
  assert.deepEqual(fire(8), ['view']);
});

// ---------------------------------------------------------------------------
// Toasts.

test('failure toasts are assertive, everything else polite', () => {
  const { toastPoliteness } = load('lib/toastPoliteness.ts');
  assert.equal(toastPoliteness('Failed to load Live TV'), 'assertive');
  assert.equal(toastPoliteness('Could not save settings'), 'assertive');
  assert.equal(toastPoliteness('This source has no direct stream URL yet.'), 'assertive');
  assert.equal(toastPoliteness('Added to watchlist'), 'polite');
  assert.equal(toastPoliteness('Preparing browser playback...'), 'polite');
});

// ---------------------------------------------------------------------------
// Service worker routing (public/tg-stream-sw.js).

function serviceWorker() {
  const listeners = {};
  const self = {
    location: new URL('https://web.arvio.tv/tg-stream-sw.js'),
    registration: { active: null },
    addEventListener: (name, fn) => { listeners[name] = fn; },
    skipWaiting: () => { self.skipped = (self.skipped || 0) + 1; },
    clients: { claim: async () => {}, get: async () => null, matchAll: async () => [] }
  };
  const source = fs.readFileSync(path.join(root, 'public/tg-stream-sw.js'), 'utf8');
  const cache = { put: async () => {}, keys: async () => [], delete: async () => {}, add: async () => {} };
  const caches = { match: async () => undefined, open: async () => cache, keys: async () => [], delete: async () => {} };
  vm.runInNewContext(source, { self, URL, Request, Response, Headers, caches, fetch: async () => new Response(''), MessageChannel, setTimeout, clearTimeout });
  const route = (url, init = {}) => {
    let responded = false;
    listeners.fetch({ request: { url, method: init.method || 'GET', mode: init.mode || 'no-cors', headers: new Headers(init.headers || {}) }, respondWith: (result) => { responded = true; Promise.resolve(result).catch(() => {}); }, clientId: '' });
    return responded;
  };
  return { self, listeners, route };
}

test('the service worker caches only the app shell and never API, auth, stream or dev assets', () => {
  const { route } = serviceWorker();
  const handled = [
    ['https://web.arvio.tv/?view=search', { mode: 'navigate' }],
    ['https://web.arvio.tv/_next/static/chunks/6668-8e83a81a4d941c17.js'],
    ['https://web.arvio.tv/_next/static/css/0a1b2c3d4e5f6a7b.css'],
    ['https://web.arvio.tv/_next/static/media/569ce4b8f30dc480-s.p.woff2'],
    ['https://web.arvio.tv/manifest.webmanifest'],
    ['https://web.arvio.tv/tg-stream/abc'] // Telegram proxy keeps working
  ];
  const untouched = [
    ['https://web.arvio.tv/api/tmdb/movie/1'],
    ['https://web.arvio.tv/api/cloud-auth/login', { method: 'POST' }],
    ['https://web.arvio.tv/version.json'],
    ['https://web.arvio.tv/_next/static/chunks/app-pages-internals.js'],
    ['https://web.arvio.tv/_next/static/development/_buildManifest.js'],
    ['https://web.arvio.tv/_next/static/chunks/6668-8e83a81a4d941c17.js', { headers: { range: 'bytes=0-' } }],
    ['https://cdn.example.com/video.m3u8'],
    ['https://image.tmdb.org/t/p/w500/x.jpg'],
    ['https://web.arvio.tv/api/proxy?url=x', { mode: 'navigate' }]
  ];
  for (const [url, init] of handled) assert.equal(route(url, init), true, url);
  for (const [url, init] of untouched) assert.equal(route(url, init), false, url);
});

test('a new service worker waits for the update prompt; the first install takes over', () => {
  const first = serviceWorker();
  first.listeners.install({ waitUntil() {} });
  assert.equal(first.self.skipped, 1);
  const update = serviceWorker();
  update.self.registration.active = {};
  update.listeners.install({ waitUntil() {} });
  assert.equal(update.self.skipped, undefined);
  update.listeners.message({ data: { type: 'SKIP_WAITING' } });
  assert.equal(update.self.skipped, 1);
});

test('manifest offers Search, Live TV and Library shortcuts that the router understands', () => {
  const manifest = JSON.parse(fs.readFileSync(path.join(root, 'public/manifest.webmanifest'), 'utf8'));
  const { readWebRoute } = load('lib/webNavigation.ts');
  const sections = manifest.shortcuts.map((s) => readWebRoute(new URL(s.url, 'https://web.arvio.tv')).section);
  assert.deepEqual(plain(sections), ['search', 'tv', 'watchlist']);
  for (const shot of manifest.screenshots) assert.ok(fs.existsSync(path.join(root, 'public', shot.src)), shot.src);
});

// ---------------------------------------------------------------------------
// Code splitting.

test('AppShell keeps heavy screens and the player out of the first-load bundle', () => {
  const shell = fs.readFileSync(path.join(root, 'components/shell/AppShell.tsx'), 'utf8');
  for (const screen of ['SettingsScreen', 'PlayerOverlay', 'WatchlistScreen', 'LiveTvScreen', 'DetailsDrawer', 'SearchScreen']) {
    assert.doesNotMatch(shell, new RegExp(`^import \\{[^}]*\\b${screen}\\b[^}]*\\} from`, 'm'), `${screen} must not be statically imported`);
  }
  assert.doesNotMatch(shell, /^import .*hls\.js|^import .*mediabunny/m);
});

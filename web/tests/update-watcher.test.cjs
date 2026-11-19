const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const policy = load('lib/updatePolicy.ts');
const current = '1790077410740';
const newer = '1790078410740';

// A tiny document: `blockers` decides what querySelector finds (player /
// dialog) and `active` is the focused element.
function fixture({ remote = newer, blockers = {}, saved = new Map(), query = '', storageBlocked = false, bootOffsetMs = 0 } = {}) {
  const document = new EventTarget();
  document.visibilityState = 'visible';
  document.activeElement = null;
  let interval, cleanup, now = Date.now();
  document.querySelector = (selector) => {
    if (/video/.test(selector)) return blockers.player ? {} : null;
    if (/dialog/.test(selector)) return blockers.dialog ? {} : null;
    return null;
  };
  const navigations = [];
  const storage = { getItem(key) { if (storageBlocked) throw Error('blocked'); return saved.get(key); }, setItem(key, value) { if (storageBlocked) throw Error('blocked'); saved.set(key, value); } };
  const window = {
    location: { href: `https://web.arvio.tv/${query}`, search: query, replace: value => navigations.push(value) },
    history: { state: null, replaceState() {} }, localStorage: storage,
    setInterval(fn) { interval = fn; return 1; }, clearInterval() {}
  };
  const RealDate = Date;
  class ClockDate extends RealDate { static now() { return now; } }
  const module = load('components/shell/UpdateWatcher.tsx', {
    react: { useEffect: effect => { cleanup = effect(); } }, '@/lib/updatePolicy': policy,
    '@/lib/appServiceWorker': { registerAppServiceWorker: async () => null, onWaitingWorker: () => () => {}, activateWaitingWorker: () => false }
  }, { window, document, Date: ClockDate, process: { env: { NEXT_PUBLIC_BUILD_STAMP: current, NODE_ENV: 'test' } }, fetch: async () => ({ ok: true, json: async () => ({ v: remote }) }) });
  module.UpdateWatcher();
  now += bootOffsetMs;
  return {
    module, saved, navigations, blockers,
    focus: (tagName) => { document.activeElement = tagName ? { tagName } : null; },
    advance: (ms) => { now += ms; },
    tick: () => interval(), visible: () => document.dispatchEvent(new Event('visibilitychange')), cleanup: () => cleanup()
  };
}
const flush = () => new Promise(resolve => setImmediate(resolve));

test('older or invalid build manifests are never offered or applied', async () => {
  for (const remote of ['1790003341540', current, 'bad', {}, Number.MAX_SAFE_INTEGER + 1]) {
    const f = fixture({ remote });
    await flush(); f.tick(); f.visible(); await flush();
    assert.equal(f.navigations.length, 0);
    assert.equal(f.module.getUpdateState().version, null);
    f.cleanup();
  }
});

test('a newer build found at app start applies itself when nothing is in progress', async () => {
  const f = fixture();
  await flush();
  assert.equal(f.navigations.length, 1);
  assert.equal(new URL(f.navigations[0]).searchParams.get('_v'), newer);
});

test('a newer build found later is only offered — no reload while browsing, typing or watching', async () => {
  const f = fixture({ bootOffsetMs: 60_000 });
  await flush();
  assert.equal(f.navigations.length, 0, 'not app start any more');
  assert.equal(f.module.getUpdateState().version, newer, 'the Update ready prompt is offered');
  // Foreground return and the 10-minute timer re-check but never reload.
  f.visible(); f.tick(); await flush();
  assert.equal(f.navigations.length, 0);
  // A section change is a safe moment — unless a field has focus,
  // a dialog is open or a player is on screen.
  f.focus('INPUT'); f.module.notifySectionNavigation();
  f.focus(null); f.blockers.dialog = true; f.module.notifySectionNavigation();
  f.blockers.dialog = false; f.blockers.player = true; f.module.notifySectionNavigation();
  assert.equal(f.navigations.length, 0);
  f.blockers.player = false; f.module.notifySectionNavigation();
  assert.equal(f.navigations.length, 1);
});

test('the Reload button applies even mid-task; dismissing only hides the prompt', async () => {
  const f = fixture({ bootOffsetMs: 60_000, blockers: { player: true } });
  await flush();
  f.module.dismissUpdate();
  assert.equal(f.module.getUpdateState().dismissed, true);
  assert.equal(f.module.applyUpdate('manual'), true);
  assert.equal(f.navigations.length, 1);
});

test('a player open at app start defers the update instead of closing playback', async () => {
  const f = fixture({ blockers: { player: true } });
  await flush();
  assert.equal(f.navigations.length, 0);
  f.blockers.player = false; f.module.notifySectionNavigation();
  assert.equal(f.navigations.length, 1);
});

test('the same stale HTML cannot loop after the four-minute cooldown expires', async () => {
  const first = fixture(); await flush();
  assert.equal(first.navigations.length, 1);
  first.saved.set('arvio.web.lastUpdateReload', String(Date.now() - 20 * 60000));
  const repeated = fixture({ saved: first.saved }); await flush(); repeated.tick(); await flush();
  repeated.module.notifySectionNavigation();
  assert.equal(repeated.navigations.length, 0);
  assert.equal(repeated.module.getUpdateState().version, null, 'the attempted version is not offered again');
});

test('blocked storage still permits one update and the cache-bust URL prevents a loop', async () => {
  const first = fixture({ storageBlocked: true }); await flush();
  assert.equal(first.navigations.length, 1);
  const repeated = fixture({ storageBlocked: true, query: `?_v=${newer}` });
  await flush(); repeated.tick(); await flush();
  assert.equal(repeated.navigations.length, 0);
});

test('update blockers: player, focused field, open dialog', () => {
  const doc = (found, active = null) => ({ querySelector: (s) => found.some((f) => s.includes(f)) ? {} : null, activeElement: active });
  assert.equal(policy.updateBlocker(doc(['video'])), 'player');
  assert.equal(policy.updateBlocker(doc([], { tagName: 'TEXTAREA' })), 'text');
  assert.equal(policy.updateBlocker(doc([], { tagName: 'DIV', isContentEditable: true })), 'text');
  assert.equal(policy.updateBlocker(doc(['dialog[open]'])), 'dialog');
  assert.equal(policy.updateBlocker(doc([], { tagName: 'BUTTON' })), null);
  assert.equal(policy.shouldAutoApply({ trigger: 'boot', sinceBootMs: 60_000, blocked: false, now: 1, lastReloadAt: 0 }), false);
  assert.equal(policy.shouldAutoApply({ trigger: 'navigation', sinceBootMs: 60_000, blocked: false, now: 10 * 60000, lastReloadAt: 9 * 60000 }), false, 'cooldown');
});

test('version manifest is static and uses the exact browser compilation stamp', async () => {
  const route = load('app/version.json/route.ts', {}, { process: { env: { NEXT_PUBLIC_BUILD_STAMP: current } } });
  assert.equal(route.dynamic, 'force-static');
  const response = route.GET();
  assert.deepEqual(await response.json(), { v: current });
  assert.equal(response.headers.get('cache-control'), 'no-store');
});

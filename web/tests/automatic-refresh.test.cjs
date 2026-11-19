const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { sourceOf, storeSource } = require('./sources.cjs');
const { shouldRefreshAutomatically: shouldRefresh } = load('lib/automaticRefresh.ts');
const ready = { visible: true, playing: false, inFlight: false, lastRefreshAt: null, now: 0 };

test('first return can refresh; rapid returns are throttled for thirty seconds', () => {
  assert.equal(shouldRefresh(ready), true);
  assert.equal(shouldRefresh({ ...ready, lastRefreshAt: 0, now: 29999 }), false);
  assert.equal(shouldRefresh({ ...ready, lastRefreshAt: 0, now: 30000 }), true);
});

test('background, playback and an existing refresh suppress automatic work', () => {
  for (const patch of [{ visible: false }, { playing: true }, { inFlight: true }]) {
    assert.equal(shouldRefresh({ ...ready, ...patch }), false);
  }
});

test('Home return and browser visibility use the same cooldown and clean up listeners', () => {
  const fs = require('node:fs');
  const vm = require('node:vm');
  const ts = require('typescript');
  const source = storeSource();
  const file = ts.createSourceFile('store.tsx', source, ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let callback;
  function visit(node) {
    if (ts.isCallExpression(node) && node.expression.getText(file) === 'useEffect' &&
        node.arguments[0]?.getText(file).includes('const refreshOnReturn =')) callback = node.arguments[0].getText(file);
    ts.forEachChild(node, visit);
  }
  visit(file);
  assert.ok(callback);
  const window = new EventTarget();
  const document = new EventTarget();
  document.visibilityState = 'visible';
  let now = 40000;
  let calls = 0;
  const lastRefreshStartedRef = { current: 0 };
  const globals = {
    window, document, view: 'app', section: 'home', authClient: { session: {} },
    cloudProfilesHydrated: true, previousSectionRef: { current: 'settings' },
    playingRef: { current: false }, refreshInFlightRef: { current: null }, lastRefreshStartedRef,
    performance: { now: () => now }, shouldRefreshAutomatically: shouldRefresh,
    refreshData: (_, background) => { assert.equal(background, true); calls++; lastRefreshStartedRef.current = now; }
  };
  const code = ts.transpileModule(`const effect = ${callback}; effect();`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022 }
  }).outputText;
  const cleanup = vm.runInNewContext(code, globals);
  assert.equal(calls, 1);
  window.dispatchEvent(new Event('focus'));
  document.dispatchEvent(new Event('visibilitychange'));
  assert.equal(calls, 1);
  now += 30000;
  document.dispatchEvent(new Event('visibilitychange'));
  assert.equal(calls, 2);
  cleanup();
  now += 30000;
  window.dispatchEvent(new Event('focus'));
  assert.equal(calls, 2);
});

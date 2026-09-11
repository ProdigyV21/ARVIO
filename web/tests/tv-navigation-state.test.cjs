const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

test('TV snapshots belong to both the playlist configuration and account/profile', () => {
  const { isCurrentIptvSnapshot } = load('lib/iptvSession.ts');
  const snapshot = { scopeKey: 'account:profile', signature: 'playlist-A' };
  assert.equal(isCurrentIptvSnapshot(snapshot, 'account:profile', 'playlist-A'), true);
  assert.equal(isCurrentIptvSnapshot(snapshot, 'account:profile', 'empty'), false);
  assert.equal(isCurrentIptvSnapshot(snapshot, 'account:other', 'playlist-A'), false);
  assert.equal(isCurrentIptvSnapshot(snapshot, 'other:profile', 'playlist-A'), false);
  assert.equal(isCurrentIptvSnapshot({}, 'account:profile', 'playlist-A'), false);
});

test('a docked player does not disable guide keyboard navigation', () => {
  let expanded = false;
  const { playerIsOpen } = load('lib/tvNav.ts', { './hoverParity': { enterKbdNav() {} } }, {
    document: { querySelector: selector => {
      assert.equal(selector, '.player-overlay:not(.player-docked)');
      return expanded ? {} : null;
    } }
  });
  assert.equal(playerIsOpen(), false);
  expanded = true;
  assert.equal(playerIsOpen(), true);
});

test('live player follows position-only layout changes and closes once when the slot disappears', () => {
  const effects = [], states = [], frames = new Map(), events = new Map();
  let mutation, now = 0, closed = 0, frameId = 0;
  const slot = { isConnected: true, getBoundingClientRect: () => rect };
  let rect = { left: 800, top: 100, width: 400, height: 225 };
  class Element { contains(candidate) { return candidate === slot; } }
  const hooks = {
    useState(initial) { const index = states.length; states.push(initial); return [initial, value => { states[index] = typeof value === 'function' ? value(states[index]) : value; }]; },
    useEffect(fn) { effects.push(fn); }
  };
  const { useLivePlayerDock } = load('components/player/useLivePlayerDock.ts', { react: hooks }, {
    performance: { now: () => now },
    Element,
    requestAnimationFrame: fn => { frames.set(++frameId, fn); return frameId; },
    cancelAnimationFrame: id => frames.delete(id),
    ResizeObserver: class { observe() {} unobserve() {} disconnect() {} },
    MutationObserver: class { constructor(fn) { mutation = fn; } observe() {} disconnect() {} },
    document: { body: {}, getElementById: () => slot.isConnected ? slot : null,
      addEventListener: (name, fn) => events.set(name, fn), removeEventListener: name => events.delete(name) },
    window: { addEventListener: (name, fn) => events.set(name, fn), removeEventListener: name => events.delete(name) }
  });
  const flush = () => { for (const [id, fn] of [...frames]) { frames.delete(id); fn(); } };
  useLivePlayerDock(true, 'channel', () => closed++);
  const cleanup = effects.map(fn => fn());
  flush();
  assert.equal(states[0].top, 100);
  rect = { ...rect, top: 340 };
  mutation(); flush();
  assert.equal(states[0].top, 340);
  const unchanged = states[0];
  mutation(); flush();
  assert.equal(states[0], unchanged, 'unchanged bounds do not trigger a new render');
  rect = { ...rect, width: 0 };
  mutation(); flush();
  assert.equal(closed, 0, 'a responsive hidden slot must not terminate playback');
  assert.equal(states[0], undefined);
  rect = { ...rect, width: 400 };
  events.get('transitionrun')({ target: new Element() });
  rect = { ...rect, left: 700 };
  flush();
  assert.equal(states[0].left, 700);
  now = 600; flush();
  assert.equal(frames.size, 0, 'tracking stops after the transition');
  events.get('arvio:expand-live-player')();
  assert.equal(states[1], true);
  slot.isConnected = false;
  mutation(); mutation();
  assert.equal(closed, 1);
  assert.equal(states[0], undefined);
  cleanup.forEach(fn => fn?.());
  assert.equal(events.size, 0);
});

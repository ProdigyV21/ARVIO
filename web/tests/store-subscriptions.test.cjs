const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');

// A React-shaped harness for lib/store/context.ts: each "component" is a hook
// call; useSyncExternalStore subscribes and, on a store notification, the
// component re-renders only when getSnapshot() changed — React's own rule.
function harness() {
  const contexts = new Map();
  const components = [];
  let current = null;
  const react = {
    createContext: (fallback) => { const context = { fallback }; return context; },
    useContext: (context) => (contexts.has(context) ? contexts.get(context) : context.fallback),
    useRef: (initial) => {
      const slot = current.slots[current.cursor++] ??= { value: { current: initial } };
      return slot.value;
    },
    useEffect: (effect) => { current.layout.push(effect); },
    useLayoutEffect: (effect) => { current.layout.push(effect); },
    useSyncExternalStore: (subscribe, getSnapshot) => {
      const component = current;
      const index = component.cursor++;
      const slot = component.slots[index] ??= {};
      slot.getSnapshot = getSnapshot;
      if (slot.subscribe !== subscribe) {
        slot.unsubscribe?.();
        slot.subscribe = subscribe;
        slot.unsubscribe = subscribe(() => {
          if (!Object.is(slot.getSnapshot(), slot.value)) component.render();
        });
      }
      slot.value = getSnapshot();
      return slot.value;
    }
  };
  const context = load('lib/store/context.ts', { react });
  function mount(hook) {
    const component = { slots: [], renders: 0, value: undefined, layout: [] };
    component.render = () => {
      const previous = current;
      current = component;
      component.cursor = 0;
      component.layout = [];
      component.renders += 1;
      component.value = hook();
      current = previous;
      for (const effect of component.layout) effect();
    };
    components.push(component);
    component.render();
    return component;
  }
  return { context, contexts, mount };
}

const baseStore = () => ({ toast: null, query: '', settings: { language: 'en', cardLayoutMode: 'landscape' }, setToast() {}, openDetails() {} });

function providerFor(h, initial) {
  // The provider is a component too: it owns the handle and publishes each value after commit.
  let value = initial;
  const provider = h.mount(() => h.context.useAppStoreHandle(value));
  h.contexts.set(h.context.AppStoreContext, provider.value);
  return {
    handle: provider.value,
    update(patch) { value = { ...value, ...patch }; provider.render(); return value; },
    rerender() { provider.render(); }
  };
}

test('a picked field re-renders its component; other store changes do not', () => {
  const h = harness();
  const store = providerFor(h, baseStore());
  const toast = h.mount(() => h.context.useAppPick('toast', 'setToast'));
  const everything = h.mount(() => h.context.useApp());
  assert.equal(toast.renders, 1);
  assert.equal(toast.value.toast, null);

  store.update({ query: 'i' });
  store.update({ query: 'in' });
  assert.equal(toast.renders, 1, 'typing a search query leaves the toast alone');
  assert.equal(everything.renders, 3, 'useApp() still sees every change');
  assert.equal(everything.value.query, 'in');

  store.update({ toast: 'Saved' });
  assert.equal(toast.renders, 2);
  assert.equal(toast.value.toast, 'Saved');
  const picked = toast.value;
  store.update({ query: 'int' });
  assert.equal(toast.value, picked, 'an unchanged pick keeps its identity');
});

test('a selector re-renders only when the selected value changes', () => {
  const h = harness();
  const store = providerFor(h, baseStore());
  const layout = h.mount(() => h.context.useAppSelector((s) => s.settings.cardLayoutMode));
  store.update({ settings: { language: 'nl', cardLayoutMode: 'landscape' } });
  assert.equal(layout.renders, 1, 'a settings object with the same layout is not a change');
  store.update({ settings: { language: 'nl', cardLayoutMode: 'poster' } });
  assert.equal(layout.renders, 2);
  assert.equal(layout.value, 'poster');
});

test('the newest value is readable while the provider renders, and subscribers hear of it after commit', () => {
  const h = harness();
  const store = providerFor(h, baseStore());
  const heard = [];
  store.handle.subscribe(() => heard.push(store.handle.get().toast));
  const next = store.update({ toast: 'Hello' });
  assert.equal(store.handle.get(), next);
  assert.deepEqual(heard, ['Hello'], 'one notification per committed change');
  // A provider render that produced the same (memoized) value does not notify.
  store.rerender();
  assert.deepEqual(heard, ['Hello']);
  assert.equal(store.handle.get(), next);
});

test('dev fixtures that provide a plain AppContext value keep working', () => {
  const h = harness();
  const fixture = { ...baseStore(), toast: 'From fixture' };
  h.contexts.set(h.context.AppContext, fixture);
  const picked = h.mount(() => h.context.useAppPick('toast'));
  const all = h.mount(() => h.context.useApp());
  const selected = h.mount(() => h.context.useAppSelector((s) => s.settings.language));
  assert.equal(picked.value.toast, 'From fixture');
  assert.equal(all.value, fixture);
  assert.equal(selected.value, 'en');
});

test('outside any provider the hooks fail loudly', () => {
  const h = harness();
  assert.throws(() => h.mount(() => h.context.useApp()), /within <AppProvider>/);
  assert.throws(() => h.mount(() => h.context.useAppPick('toast')), /within <AppProvider>/);
});

test('the app shell, Home rows, cards, search and player subscribe to fields, not the whole store', () => {
  const root = path.join(__dirname, '..');
  const hot = ['components/shell/AppShell.tsx', 'components/shell/TopNav.tsx', 'components/shell/Toast.tsx',
    'components/shell/BackHandler.tsx', 'components/home/HomeScreen.tsx', 'components/media/MediaRail.tsx',
    'components/media/LazyRail.tsx', 'components/media/MediaCard.tsx', 'components/search/SearchScreen.tsx',
    'components/details/DetailsDrawer.tsx', 'components/livetv/LiveTvScreen.tsx', 'components/player/PlayerOverlay.tsx', 'lib/useKids.ts'];
  for (const file of hot) {
    const source = fs.readFileSync(path.join(root, file), 'utf8');
    assert.doesNotMatch(source, /\buseApp\(\)/, `${file} reads the whole store`);
  }
  // A shell render (opening a title, starting a channel) must not re-render its always-mounted children.
  const shell = fs.readFileSync(path.join(root, 'components/shell/AppShell.tsx'), 'utf8');
  for (const child of ['TopNav', 'Toast', 'BackHandler', 'MediaContextMenu', 'HomeScreen']) {
    assert.match(shell, new RegExp(`const ${child} = memo\\(${child}View\\);`), `${child} is memoized in the shell`);
  }
  const store = fs.readFileSync(path.join(root, 'lib/store.tsx'), 'utf8');
  assert.match(store, /useAppStoreHandle\(value\)/);
  assert.match(store, /<AppStoreContext\.Provider value=\{handle\}>/);
});

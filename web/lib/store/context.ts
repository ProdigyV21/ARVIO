import { createContext, useContext, useEffect, useLayoutEffect, useRef, useSyncExternalStore } from "react";
import type { AppStore } from "./types";

/**
 * A plain store value. The real <AppProvider> does not fill this context (it
 * publishes through AppStoreContext); dev fixtures and test harnesses provide
 * a hand-made AppStore here, and every hook below reads it as a fallback.
 */
export const AppContext = createContext<AppStore | null>(null);

/**
 * The live store behind <AppProvider>: one stable handle, so a state change no
 * longer re-renders every consumer through context. Components subscribe to
 * the fields they show with useAppPick / useAppSelector and re-render only when
 * those change; useApp() still subscribes to everything.
 */
export interface AppStoreHandle {
  get: () => AppStore;
  subscribe: (listener: () => void) => () => void;
}

interface WritableAppStoreHandle extends AppStoreHandle {
  /** Render phase: make the provider's newest value readable by consumers rendering in the same pass. */
  set: (value: AppStore) => void;
  /** Commit phase: tell subscribers when the value changed since the last notification. */
  publish: () => void;
}

export const AppStoreContext = createContext<AppStoreHandle | null>(null);

export function createAppStoreHandle(initial: AppStore): WritableAppStoreHandle {
  let value = initial;
  let published = initial;
  const listeners = new Set<() => void>();
  return {
    get: () => value,
    subscribe: (listener) => {
      listeners.add(listener);
      return () => { listeners.delete(listener); };
    },
    set: (next) => { value = next; },
    publish: () => {
      if (published === value) return;
      published = value;
      listeners.forEach((listener) => listener());
    }
  };
}

const useIsomorphicLayoutEffect = typeof window === "undefined" ? useEffect : useLayoutEffect;

/** Owned by <AppProvider>: returns the stable handle and publishes each new value after commit. */
export function useAppStoreHandle(value: AppStore): AppStoreHandle {
  const handle = useRef<WritableAppStoreHandle | null>(null);
  handle.current ??= createAppStoreHandle(value);
  handle.current.set(value);
  const current = handle.current;
  useIsomorphicLayoutEffect(() => {
    current.publish();
  });
  return current;
}

const noSubscribe = () => () => {};
const missing = (): never => { throw new Error("useApp must be used within <AppProvider>"); };

/** Every field of the store; the component re-renders on any store change. Prefer useAppPick. */
export function useApp(): AppStore {
  const handle = useContext(AppStoreContext);
  const fixture = useContext(AppContext);
  const store = useSyncExternalStore(handle ? handle.subscribe : noSubscribe, handle ? handle.get : () => fixture, handle ? handle.get : () => fixture);
  return store ?? missing();
}

/**
 * One derived value from the store. The component re-renders only when the
 * selected value changes (Object.is, or `isEqual` when given).
 */
export function useAppSelector<T>(selector: (store: AppStore) => T, isEqual: (a: T, b: T) => boolean = Object.is): T {
  const handle = useContext(AppStoreContext);
  const fixture = useContext(AppContext);
  const memo = useRef<{ source: AppStore; selector: (store: AppStore) => T; value: T } | null>(null);
  const read = () => {
    const source = handle ? handle.get() : fixture;
    if (!source) return missing();
    const previous = memo.current;
    if (previous && previous.source === source && previous.selector === selector) return previous.value;
    const next = selector(source);
    const value = previous && isEqual(previous.value, next) ? previous.value : next;
    memo.current = { source, selector, value };
    return value;
  };
  return useSyncExternalStore(handle ? handle.subscribe : noSubscribe, read, read);
}

function samePick<T extends object>(a: T, b: T) {
  for (const key in a) if (!Object.is(a[key], b[key])) return false;
  return true;
}

/**
 * The named store fields, e.g. `const { settings, openDetails } = useAppPick("settings", "openDetails")`.
 * The component re-renders only when one of those fields changes.
 */
export function useAppPick<K extends keyof AppStore>(...keys: K[]): Pick<AppStore, K> {
  return useAppSelector((store) => {
    const picked = {} as Pick<AppStore, K>;
    for (const key of keys) picked[key] = store[key];
    return picked;
  }, samePick);
}

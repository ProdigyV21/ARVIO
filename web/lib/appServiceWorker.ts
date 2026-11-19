// Registration of the single app service worker (public/tg-stream-sw.js).
//
// One worker per scope: the Telegram streaming proxy and the offline app-shell
// cache share the root-scope worker, so it keeps its historical file name —
// lib/telegram/stream.ts registers the very same URL, and two different
// script URLs on one scope would keep replacing each other.
//
// Only production builds register it from here: in `next dev` the chunks are
// unhashed and HMR-driven, and an app-shell cache would only get in the way
// (Telegram playback still registers it on demand, where the caching rules
// stay inert for unhashed dev assets).

export const APP_SW_URL = "/tg-stream-sw.js";

let registration: ServiceWorkerRegistration | null = null;
const waitingListeners = new Set<() => void>();

function notifyWaiting() {
  for (const listener of waitingListeners) listener();
}

function track(reg: ServiceWorkerRegistration) {
  registration = reg;
  if (reg.waiting && navigator.serviceWorker.controller) notifyWaiting();
  reg.addEventListener("updatefound", () => {
    const installing = reg.installing;
    installing?.addEventListener("statechange", () => {
      // "installed" with an existing controller = a new worker is waiting
      // for the update prompt (the first install activates immediately).
      if (installing.state === "installed" && navigator.serviceWorker.controller) notifyWaiting();
    });
  });
}

export function registerAppServiceWorker(): Promise<ServiceWorkerRegistration | null> {
  if (typeof navigator === "undefined" || !("serviceWorker" in navigator)) return Promise.resolve(null);
  return navigator.serviceWorker
    .register(APP_SW_URL)
    .then((reg) => {
      track(reg);
      return reg;
    })
    .catch(() => null);
}

/** Subscribe to "a new service worker is installed and waiting". */
export function onWaitingWorker(listener: () => void): () => void {
  waitingListeners.add(listener);
  return () => waitingListeners.delete(listener);
}

/** Let a waiting worker take over (called when an update is applied). */
export function activateWaitingWorker(): boolean {
  const waiting = registration?.waiting;
  if (!waiting) return false;
  waiting.postMessage({ type: "SKIP_WAITING" });
  return true;
}

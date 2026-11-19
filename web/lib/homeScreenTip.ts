import { isIosSafari, isStandaloneDisplay } from "./playerPlatform";

// One-time "Add ARVIO to your Home Screen" tip for iPhone/iPad Safari.
//
// It appears only after the first playback that actually started (so it is
// never the first thing a new visitor sees) and only in Safari tabs — the
// home-screen web app gets its own window, wake lock and lock-screen controls.

export const HOME_SCREEN_TIP_KEY = "arvio.homeScreenTip.v1";
export const HOME_SCREEN_TIP_EVENT = "arvio-home-screen-tip";

type StorageLike = Pick<Storage, "getItem" | "setItem">;
type TipState = "eligible" | "dismissed";

function storage(): StorageLike | undefined {
  try { return typeof window === "undefined" ? undefined : window.localStorage; } catch { return undefined; }
}

function read(store: StorageLike | undefined): TipState | null {
  try {
    const value = store?.getItem(HOME_SCREEN_TIP_KEY);
    return value === "eligible" || value === "dismissed" ? value : null;
  } catch { return null; }
}

export type TipEnvironment = {
  storage?: StorageLike;
  iosSafari?: boolean;
  standalone?: boolean;
};

function environment(env: TipEnvironment = {}) {
  return {
    store: "storage" in env ? env.storage : storage(),
    iosSafari: env.iosSafari ?? isIosSafari(),
    standalone: env.standalone ?? isStandaloneDisplay()
  };
}

/** Call when playback has really started. Returns true when the tip is now due. */
export function markHomeScreenTipEligible(env?: TipEnvironment) {
  const { store, iosSafari, standalone } = environment(env);
  if (!iosSafari || standalone || read(store) === "dismissed") return false;
  try { store?.setItem(HOME_SCREEN_TIP_KEY, "eligible"); } catch { /* storage blocked: tip for this page only */ }
  if (typeof window !== "undefined") {
    try { window.dispatchEvent(new Event(HOME_SCREEN_TIP_EVENT)); } catch { /* ignore */ }
  }
  return true;
}

export function shouldShowHomeScreenTip(env?: TipEnvironment) {
  const { store, iosSafari, standalone } = environment(env);
  return iosSafari && !standalone && read(store) === "eligible";
}

export function dismissHomeScreenTip(env?: TipEnvironment) {
  const { store } = environment(env);
  try { store?.setItem(HOME_SCREEN_TIP_KEY, "dismissed"); return true; } catch { return false; }
}

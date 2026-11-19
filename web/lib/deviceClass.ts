// Device classes on <html>, decided BEFORE first paint.
//
//   is-tv     smart-TV shells (Tizen, webOS, NetCast, HbbTV) and Xbox Edge —
//             the couch layout: always-on, big focus rings, no backdrop blur.
//   is-xbox   Xbox Edge specifically (controller tip, input emulation).
//   no-hover  touch-first devices whose primary pointer cannot hover (iPhone,
//             iPad without a trackpad, Apple Vision Pro). Vision Pro reports a
//             Mac/iPad user agent, so this is feature-detected, never UA-sniffed.
//
// The same logic exists twice on purpose: as a typed function for runtime
// callers and tests, and as a tiny dependency-free inline script that the root
// layout runs in <head> so the classes exist before the first frame (the old
// async-import path flipped `is-tv` seconds after paint). The test suite runs
// the inline script against the typed function to keep them identical.

export const TV_UA_PATTERN = /\b(tizen|web0s|webos|smart-?tv|netcast|hbbtv|xbox)\b/i;
export const XBOX_UA_PATTERN = /\bxbox\b/i;
export const NO_HOVER_QUERY = "(pointer: coarse) and (hover: none)";

export type DeviceSignals = {
  userAgent: string;
  /** matchMedia(NO_HOVER_QUERY).matches */
  coarseNoHover: boolean;
  maxTouchPoints: number;
};

export function deviceClasses({ userAgent, coarseNoHover, maxTouchPoints }: DeviceSignals): string[] {
  const out: string[] = [];
  if (TV_UA_PATTERN.test(userAgent)) out.push("is-tv");
  if (XBOX_UA_PATTERN.test(userAgent)) out.push("is-xbox");
  if (coarseNoHover && maxTouchPoints > 0) out.push("no-hover");
  return out;
}

export function readDeviceSignals(): DeviceSignals {
  if (typeof navigator === "undefined" || typeof window === "undefined") {
    return { userAgent: "", coarseNoHover: false, maxTouchPoints: 0 };
  }
  let coarseNoHover = false;
  try { coarseNoHover = window.matchMedia(NO_HOVER_QUERY).matches; } catch { /* very old engines */ }
  return { userAgent: navigator.userAgent || "", coarseNoHover, maxTouchPoints: navigator.maxTouchPoints || 0 };
}

/**
 * Inline <head> script. ES5 only (Tizen 4 / webOS 4 engines), wrapped in
 * try/catch so a hostile environment can never break boot. On Xbox it also
 * asks Edge to deliver the controller as a Gamepad (UWP-documented
 * `gamepadInputEmulation`) instead of a virtual mouse cursor; unsupported
 * engines just ignore the property.
 */
export const DEVICE_CLASS_SCRIPT = `(function(){try{var d=document.documentElement,n=navigator,u=n.userAgent||"",c=[];` +
  `if(${TV_UA_PATTERN.toString()}.test(u))c.push("is-tv");` +
  `if(${XBOX_UA_PATTERN.toString()}.test(u)){c.push("is-xbox");try{n.gamepadInputEmulation="gamepad"}catch(e){}}` +
  `var h=false;try{h=window.matchMedia(${JSON.stringify(NO_HOVER_QUERY)}).matches}catch(e){}` +
  `if(h&&(n.maxTouchPoints||0)>0)c.push("no-hover");` +
  `for(var i=0;i<c.length;i++)d.classList.add(c[i])}catch(e){}})();`;

/** The one-time Xbox controller tip (components/shell/XboxControllerTip). */
export function shouldShowXboxTip(root: { classList: { contains(name: string): boolean } }, dismissed: boolean): boolean {
  return root.classList.contains("is-xbox") && !dismissed;
}

/** Keep `no-hover` in step when an iPad gains/loses a trackpad. */
export function watchHoverCapability(root: HTMLElement = document.documentElement): () => void {
  if (typeof window === "undefined" || typeof window.matchMedia !== "function") return () => undefined;
  let query: MediaQueryList;
  try { query = window.matchMedia(NO_HOVER_QUERY); } catch { return () => undefined; }
  const apply = () => {
    const on = query.matches && (navigator.maxTouchPoints || 0) > 0;
    root.classList.toggle("no-hover", on);
  };
  apply();
  query.addEventListener?.("change", apply);
  return () => query.removeEventListener?.("change", apply);
}

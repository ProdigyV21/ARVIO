// TV & keyboard navigation. Makes the whole app drivable with a D-pad remote
// (Samsung Tizen, LG webOS, Android TV browsers, Fire TV, Xbox Edge) or a
// plain keyboard:
//   - Arrow keys move focus GEOMETRICALLY to the nearest interactive element in
//     that direction (spatial navigation) — Enter then activates it natively,
//     since every card/row/control in the app is a real <button>/<a>.
//   - TV "Back" keys (Tizen 10009, webOS 461, GoBack) run the shared Back
//     ladder (performBack): free a text field → let an open layer consume an
//     Escape → cancel a native <dialog> → step back through app history. The
//     gamepad's B button runs the very same ladder.
//   - Remote media keys (play/pause/rewind/fast-forward) drive the <video>
//     element directly, so playback control works even when the player's own
//     shortcuts aren't focused.
//
// The engine stands down while the video player overlay is open (it owns the
// arrow keys for seek/volume) and whenever a text field has focus.
//
// Markup hooks other screens can use:
//   data-autofocus   the control to focus first when a view opens in TV /
//                    keyboard mode (hero Play, last-used profile…).
//   data-tv-skip     never a spatial-navigation target (rail arrows etc.).

import { enterKbdNav } from "./hoverParity";
import { TV_UA_PATTERN } from "./deviceClass";

export const FOCUS_SELECTOR = [
  "button:not([disabled])",
  "a[href]",
  "input:not([disabled])",
  "select:not([disabled])",
  "textarea:not([disabled])",
  '[role="button"]:not([aria-disabled="true"])',
  '[tabindex]:not([tabindex="-1"])'
].join(",");

/**
 * Are we in a TV browser (Samsung Tizen, LG webOS, other smart-TV shells, Xbox)?
 *
 * The `is-tv` class on <html> is applied before first paint by the inline
 * script in app/layout.tsx (lib/deviceClass.ts); this is the runtime twin used
 * to switch focus scrolling from smooth to instant — TV SoCs jank through
 * animated scrolls.
 */
export function isTvBrowser(): boolean {
  if (typeof navigator === "undefined") return false;
  return TV_UA_PATTERN.test(navigator.userAgent);
}

/** TV couch mode: class set before paint, UA as the fallback. */
export function isTvMode(): boolean {
  if (typeof document !== "undefined" && document.documentElement?.classList.contains("is-tv")) return true;
  return isTvBrowser();
}

/** Key-driven navigation of any kind (TV remote, keyboard arrows, gamepad). */
export function isKeyNavMode(): boolean {
  if (typeof document === "undefined") return false;
  return isTvMode() || document.documentElement.classList.contains("kbd-nav");
}

type Direction = "up" | "down" | "left" | "right";

const ARROW_TO_DIRECTION: Record<string, Direction> = {
  ArrowUp: "up",
  ArrowDown: "down",
  ArrowLeft: "left",
  ArrowRight: "right"
};

// Legacy numeric key codes sent by TV browser remotes (no modern `key` value).
const TV_BACK_CODES = new Set([10009, 461]); // Tizen, webOS
const TV_PLAY_CODES = new Set([415, 10252]); // play, play/pause toggle
const TV_PAUSE_CODES = new Set([19]);
const TV_STOP_CODES = new Set([413]);
const TV_FF_CODES = new Set([417]);
const TV_RW_CODES = new Set([412]);

function isTextTarget(target: EventTarget | null): boolean {
  if (!(target instanceof HTMLElement)) return false;
  const tag = target.tagName;
  if (tag === "INPUT") {
    // Arrow keys on a slider, checkbox or colour picker should move focus like any button.
    const type = (target as HTMLInputElement).type;
    return !["range", "checkbox", "radio", "color", "button", "submit", "reset"].includes(type);
  }
  return (
    tag === "TEXTAREA" ||
    tag === "SELECT" ||
    target.isContentEditable
  );
}

const TYPEABLE_INPUTS = new Set(["", "text", "search", "url", "email", "password", "tel", "number"]);

/** Inputs the on-screen keyboard can type into. */
export function isTypeableField(el: Element | null): el is HTMLInputElement | HTMLTextAreaElement {
  if (!el) return false;
  if (el.tagName === "TEXTAREA") return !(el as HTMLTextAreaElement).readOnly && !(el as HTMLTextAreaElement).disabled;
  if (el.tagName !== "INPUT") return false;
  const input = el as HTMLInputElement;
  return TYPEABLE_INPUTS.has((input.getAttribute("type") || "").toLowerCase()) && !input.readOnly && !input.disabled;
}

export function playerIsOpen(): boolean {
  return document.querySelector(".player-overlay:not(.player-docked)") !== null;
}

function isVisible(el: HTMLElement): boolean {
  if (el.closest('[aria-hidden="true"], [inert], [data-tv-skip]')) return false;
  const rect = el.getBoundingClientRect();
  if (rect.width < 4 || rect.height < 4) return false;
  // Off-screen elements inside horizontal rails are still valid targets when
  // they're near the viewport edge (scrollIntoView will reveal them), but
  // elements far outside are noise that breaks nearest-neighbour scoring.
  const margin = 400;
  return (
    rect.bottom > -margin &&
    rect.top < window.innerHeight + margin &&
    rect.right > -margin &&
    rect.left < window.innerWidth + margin
  );
}

function isRendered(el: Element): boolean {
  return typeof (el as HTMLElement).getClientRects !== "function" || (el as HTMLElement).getClientRects().length > 0;
}

// When a modal layer is open (source picker, cast modal, details drawer, a
// native <dialog>), spatial focus must stay inside it — otherwise the arrows
// jump to elements behind the overlay (and for a native modal dialog, focus()
// on those inert elements silently fails, leaving the arrows dead).
const LAYER_SCOPES = 'dialog[open], [role="dialog"], [aria-modal="true"], .details-drawer';

function isModalDialog(dialog: HTMLDialogElement): boolean {
  try {
    return dialog.matches(":modal");
  } catch {
    return true; // engine predates :modal — an open dialog is the best guess
  }
}

/**
 * The topmost open native modal dialog. Browsers move focus into a dialog on
 * showModal() and keep it there, so the innermost modal containing the focus
 * is the top one; without focus, the last in DOM order.
 */
export function topModalDialog(): HTMLDialogElement | null {
  const modals = Array.from(document.querySelectorAll<HTMLDialogElement>("dialog[open]")).filter(isModalDialog);
  if (modals.length === 0) return null;
  const active = document.activeElement;
  const holding = active ? modals.filter((d) => d.contains(active)) : [];
  if (holding.length > 0) return holding[holding.length - 1];
  return modals[modals.length - 1];
}

/** The element spatial navigation is confined to, or the whole document. */
export function focusScope(): HTMLElement | Document {
  const modal = topModalDialog();
  const root: HTMLElement | Document = modal ?? document;
  // Within the root, the LAST rendered layer in the DOM is the topmost one.
  const layers = Array.from(root.querySelectorAll<HTMLElement>(LAYER_SCOPES)).filter(
    (el) => el !== modal && isRendered(el) && !(el instanceof HTMLDialogElement && !el.open)
  );
  return layers.length > 0 ? layers[layers.length - 1] : root;
}

function candidates(scope: ParentNode = focusScope()): HTMLElement[] {
  return Array.from(scope.querySelectorAll<HTMLElement>(FOCUS_SELECTOR)).filter(isVisible);
}

// Score how good `to` is as a move from `from` in `direction`: primary-axis
// distance plus a penalty for sideways drift. Lower is better; negative-axis
// candidates (wrong side) are rejected.
export function directionalScore(from: DOMRect, to: DOMRect, direction: Direction): number | null {
  const fromCx = from.left + from.width / 2;
  const fromCy = from.top + from.height / 2;
  const toCx = to.left + to.width / 2;
  const toCy = to.top + to.height / 2;
  let primary = 0;
  let secondary = 0;
  switch (direction) {
    case "up":
      primary = fromCy - toCy;
      secondary = Math.abs(toCx - fromCx);
      break;
    case "down":
      primary = toCy - fromCy;
      secondary = Math.abs(toCx - fromCx);
      break;
    case "left":
      primary = fromCx - toCx;
      secondary = Math.abs(toCy - fromCy);
      break;
    case "right":
      primary = toCx - fromCx;
      secondary = Math.abs(toCy - fromCy);
      break;
  }
  if (primary <= 4) return null; // wrong side (or same spot)
  // Sideways drift weighs double: prefer the element straight ahead over a
  // slightly closer one in the diagonal — matches how TV UIs feel.
  return primary + secondary * 2;
}

function topLeftMost(list: HTMLElement[]): HTMLElement | undefined {
  return list
    .slice()
    .sort((a, b) => {
      const ra = a.getBoundingClientRect();
      const rb = b.getBoundingClientRect();
      return ra.top - rb.top || ra.left - rb.left;
    })[0];
}

function moveFocus(direction: Direction): boolean {
  const current = document.activeElement instanceof HTMLElement ? document.activeElement : null;
  const all = candidates();
  if (all.length === 0) return false;

  if (!current || current === document.body || !all.includes(current)) {
    // Nothing focused yet: honour a screen's preferred start, else the
    // top-left-most interactive element.
    const preferred = all.find((el) => el.hasAttribute("data-autofocus"));
    focusElement(preferred ?? topLeftMost(all)!);
    return true;
  }

  const fromRect = current.getBoundingClientRect();
  let best: HTMLElement | null = null;
  let bestScore = Infinity;
  for (const el of all) {
    if (el === current) continue;
    const score = directionalScore(fromRect, el.getBoundingClientRect(), direction);
    if (score !== null && score < bestScore) {
      bestScore = score;
      best = el;
    }
  }
  if (!best) return false;
  focusElement(best);
  return true;
}

function focusElement(el: HTMLElement) {
  el.focus({ preventScroll: true });
  el.scrollIntoView({ block: "nearest", inline: "nearest", behavior: isTvMode() || window.matchMedia("(prefers-reduced-motion: reduce)").matches ? "auto" : "smooth" });
}

/** Dispatch a cancelable Escape at the focused element; true when a layer prevented it. */
function dispatchEscape(): boolean {
  const escape = new KeyboardEvent("keydown", { key: "Escape", code: "Escape", bubbles: true, cancelable: true });
  const target = document.activeElement instanceof HTMLElement ? document.activeElement : document.body;
  target.dispatchEvent(escape);
  return escape.defaultPrevented;
}

/**
 * Close a native <dialog> the way a trusted Escape would: fire `cancel` (so
 * React onCancel handlers run and may take over), then close() unless the
 * handler prevented it. Synthetic key events never trigger this natively.
 */
export function cancelNativeDialog(dialog: HTMLDialogElement): void {
  const cancel = new Event("cancel", { cancelable: true });
  dialog.dispatchEvent(cancel);
  if (!cancel.defaultPrevented && dialog.open) dialog.close();
}

/** Is there anywhere "back" to go inside the app (details, a non-home tab)? */
function routeIsNested(): boolean {
  try {
    const params = new URL(window.location.href).searchParams;
    return params.has("title") || params.has("view");
  } catch {
    return false;
  }
}

function stepBackInApp(): boolean {
  if (!routeIsNested()) return false; // home root: never exit the TV app / leave the site
  if (window.history.length > 1) {
    window.history.back();
    return true;
  }
  // Deep-linked first entry: history.back() would leave ARVIO entirely. Rewrite
  // the entry to the home root and let BackHandler's popstate ladder apply it.
  const root = new URL(window.location.href);
  root.searchParams.delete("title");
  root.searchParams.delete("view");
  root.searchParams.delete("q");
  window.history.replaceState(window.history.state, "", root.toString());
  window.dispatchEvent(new PopStateEvent("popstate", { state: window.history.state }));
  return true;
}

export type BackResult = "blurred" | "escape" | "dialog" | "layer" | "history" | "none";

/**
 * The one Back ladder shared by TV remotes (Tizen/webOS Back) and the
 * gamepad's B button:
 *   1. a focused text field / select is freed (blurred);
 *   2. an Escape goes to the focused element — any layer that handles it and
 *      calls preventDefault() wins (player panels, menus, the keyboard);
 *   3. a native modal <dialog> on top gets cancel/close;
 *   4. other open layers (player, role="dialog" overlays) are assumed to have
 *      consumed the Escape — they all close on it;
 *   5. otherwise step back through app history (details → list, tab → home),
 *      which BackHandler's popstate handler applies.
 */
export function performBack(): BackResult {
  const active = document.activeElement;
  if (active instanceof HTMLElement && active !== document.body && isTextTarget(active)) {
    active.blur();
    return "blurred";
  }
  // Snapshot BEFORE dispatching: React applies state from native listeners
  // asynchronously, so a layer that closes on this Escape is still in the DOM
  // right after dispatch — but measuring first keeps this independent of that.
  const scope = focusScope();
  const player = playerIsOpen();
  if (dispatchEscape()) return "escape";
  if (scope instanceof HTMLDialogElement) {
    cancelNativeDialog(scope);
    return "dialog";
  }
  if (player) return "layer";
  if (scope instanceof HTMLElement && !scope.classList.contains("details-drawer")) return "layer";
  const modal = topModalDialog();
  if (modal) {
    cancelNativeDialog(modal);
    return "dialog";
  }
  return stepBackInApp() ? "history" : "none";
}

function appVideo(): HTMLVideoElement | null {
  return document.querySelector("video");
}

/** Ask the on-screen keyboard (components/shell/OnScreenKeyboard) to open for a field. */
export function requestOnScreenKeyboard(field: HTMLInputElement | HTMLTextAreaElement): boolean {
  const detail = { target: field, accepted: false };
  window.dispatchEvent(new CustomEvent("arvio:osk-open", { detail }));
  return detail.accepted;
}

// Tizen, webOS and Android TV bring a system IME for focused inputs; Xbox Edge
// (with gamepad input emulation) and HTPC pads do not. Remote Enter opens the
// built-in keyboard only where no system one exists.
function remoteEnterOpensKeyboard(): boolean {
  return typeof document !== "undefined" && document.documentElement.classList.contains("is-xbox");
}

function onKeyDown(event: KeyboardEvent) {
  if (event.defaultPrevented) return;
  // --- Escape frees a focused text field. ---
  // Without this, remotes and controllers have no way OUT of an input: the
  // guard below swallows their arrows and nothing else listens.
  if (event.key === "Escape" && isTextTarget(event.target)) {
    (event.target as HTMLElement).blur();
    return;
  }

  // --- TV Back (Tizen/webOS/remotes): the shared Back ladder. ---
  // "XF86Back" is what some older Tizen builds report as event.key.
  if (TV_BACK_CODES.has(event.keyCode) || event.key === "GoBack" || event.key === "BrowserBack" || event.key === "XF86Back") {
    event.preventDefault(); // keep the TV browser from exiting the app
    enterKbdNav();
    performBack();
    return;
  }

  // --- Remote media keys: drive the video element directly. ---
  const video = appVideo();
  if (video) {
    if (event.key === "MediaPlayPause" || TV_PLAY_CODES.has(event.keyCode)) {
      event.preventDefault();
      if (video.paused) void video.play().catch(() => undefined);
      else video.pause();
      return;
    }
    if (event.key === "MediaPause" || TV_PAUSE_CODES.has(event.keyCode)) {
      event.preventDefault();
      video.pause();
      return;
    }
    if (event.key === "MediaPlay") {
      event.preventDefault();
      void video.play().catch(() => undefined);
      return;
    }
    if (event.key === "MediaFastForward" || TV_FF_CODES.has(event.keyCode)) {
      event.preventDefault();
      video.currentTime = Math.min(video.duration || Infinity, video.currentTime + 10);
      return;
    }
    if (event.key === "MediaRewind" || TV_RW_CODES.has(event.keyCode)) {
      event.preventDefault();
      video.currentTime = Math.max(0, video.currentTime - 10);
      return;
    }
    if (event.key === "MediaStop" || TV_STOP_CODES.has(event.keyCode)) {
      event.preventDefault();
      dispatchEscape();
      return;
    }
  }

  // --- Remote Enter on a text field opens the built-in keyboard (Xbox). ---
  if (event.key === "Enter" && event.isTrusted && isTypeableField(event.target as Element) && remoteEnterOpensKeyboard()) {
    if (requestOnScreenKeyboard(event.target as HTMLInputElement)) {
      event.preventDefault();
      return;
    }
  }

  // Tab is keyboard navigation too: without this, Tab users get focus skins
  // but none of the hover-parity reveals (play overlays etc.) arrow users see.
  if (event.key === "Tab" && event.isTrusted) enterKbdNav();

  // --- Spatial navigation with the arrows. ---
  const direction = ARROW_TO_DIRECTION[event.key];
  if (!direction) return;
  if (event.altKey || event.ctrlKey || event.metaKey || event.shiftKey) return;
  // Trusted arrows in a text field belong to the caret. A CLOSED <select> is
  // different: on a TV remote every arrow leaves it (Enter opens the native
  // picker), and with a keyboard Left/Right leave while Up/Down keep stepping
  // the value. The gamepad's synthetic arrows have no default action at all,
  // so for them spatial navigation is the only way OUT of a field.
  if (event.isTrusted && isTextTarget(event.target)) {
    const isSelect = (event.target as HTMLElement).tagName === "SELECT";
    if (!isSelect) return;
    if (!isTvMode() && (direction === "up" || direction === "down")) return;
  }
  // Key-driven navigation: light up hover-equivalent focus styling.
  enterKbdNav();
  if (playerIsOpen()) return; // the player owns arrows (seek/volume)
  if (moveFocus(direction)) event.preventDefault(); // stop page scroll / select stepping on a handled move
}

// ---------------------------------------------------------------------------
// Starting focus after a view change.

function focusIsLost(): boolean {
  const active = document.activeElement;
  if (!active || active === document.body || active === document.documentElement) return true;
  if (!active.isConnected) return true;
  return active instanceof HTMLElement && !isRendered(active);
}

function firstHeading(scope: ParentNode): HTMLElement | null {
  const heading = Array.from(scope.querySelectorAll<HTMLElement>("h1, h2")).find((el) => isRendered(el) && !el.closest('[aria-hidden="true"], [hidden]'));
  return heading ?? null;
}

function focusHeading(heading: HTMLElement) {
  if (!heading.hasAttribute("tabindex")) heading.setAttribute("tabindex", "-1");
  heading.focus({ preventScroll: true });
}

let pendingFocus = 0;

/**
 * After a view change (profile screen, a new tab, details, sign-in) nothing is
 * focused when the control that triggered it unmounted. In TV / keyboard mode
 * focus the screen's `data-autofocus` control (waiting briefly for content
 * that renders late, like the Home hero), else its first control; for pointer
 * and screen-reader users focus the first heading so the new view is announced.
 * Never moves focus the user (or the screen itself) already placed.
 */
export function focusAfterViewChange({ waitMs = 1500 }: { waitMs?: number } = {}): () => void {
  if (typeof window === "undefined") return () => undefined;
  window.clearTimeout(pendingFocus);
  const started = Date.now();
  let cancelled = false;
  const attempt = () => {
    if (cancelled) return;
    if (!focusIsLost()) return; // someone focused something — leave it
    if (topModalDialog()) return; // the browser manages focus inside modals
    const scope = focusScope();
    if (isKeyNavMode()) {
      const all = candidates(scope);
      const preferred = all.find((el) => el.hasAttribute("data-autofocus"));
      if (preferred) { focusElement(preferred); return; }
      if (Date.now() - started < waitMs) { pendingFocus = window.setTimeout(attempt, 100); return; }
      const content = scope.querySelector<HTMLElement>(".details-drawer") ?? scope.querySelector<HTMLElement>(".content") ?? scope.querySelector<HTMLElement>("main");
      const inContent = content ? all.filter((el) => content.contains(el)) : [];
      // Code-split screens render their controls a moment later — keep
      // waiting for them rather than parking focus in the top bar.
      if (inContent.length === 0 && Date.now() - started < waitMs + 4000) { pendingFocus = window.setTimeout(attempt, 150); return; }
      const target = topLeftMost(inContent.length ? inContent : all);
      if (target) focusElement(target);
      return;
    }
    const heading = firstHeading(scope);
    if (heading) { focusHeading(heading); return; }
    if (Date.now() - started < waitMs) pendingFocus = window.setTimeout(attempt, 100);
  };
  pendingFocus = window.setTimeout(attempt, 0);
  return () => { cancelled = true; window.clearTimeout(pendingFocus); };
}

let installed = false;

export function installTvNav() {
  if (installed || typeof window === "undefined") return;
  installed = true;
  window.addEventListener("keydown", onKeyDown);
  // Normally already set before first paint by the layout's inline script;
  // kept as a fallback for embeds that render AppShell without the layout.
  if (isTvBrowser()) document.documentElement.classList.add("is-tv");
}

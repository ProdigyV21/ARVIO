// Keyboard / remote / gamepad model for the player and the layers over the
// details page, matching the Android TV player:
//   controls hidden:  Left/Right seek ±10 s; OK/Enter, Up or Down reveal the
//                     control bar (focus on Play); Space toggles play;
//   controls visible: arrows move focus between the controls, Enter activates
//                     the focused one; any key keeps the bar up;
//   Back (Escape):    closes an open panel, else hides the controls, else
//                     closes the player.
// lib/tvNav.ts deliberately stands down while the player is open, so focus
// movement inside the player is implemented here.

export type PlayerKeyState = {
  controlsVisible: boolean;
  panelOpen: boolean;
  /** Error, end screen or boot screen: there is nothing to hide. */
  modalState: boolean;
  /** The focused element is the seek bar (range input). */
  onSeekBar: boolean;
  /** The focused element is inside the player controls/panel. */
  focusInControls: boolean;
};

export type PlayerKeyAction =
  | { type: "seek"; seconds: number }
  | { type: "show-controls" }
  | { type: "toggle-play" }
  | { type: "move-focus"; direction: "up" | "down" | "left" | "right" }
  | { type: "close-panel" }
  | { type: "hide-controls" }
  | { type: "close" }
  | { type: "native" }
  | { type: "none" };

export const REMOTE_SEEK_SECONDS = 10;

export function playerKeyAction(key: string, state: PlayerKeyState): PlayerKeyAction {
  const arrow = key === "ArrowUp" ? "up" : key === "ArrowDown" ? "down" : key === "ArrowLeft" ? "left" : key === "ArrowRight" ? "right" : null;
  if (key === "Escape" || key === "GoBack" || key === "BrowserBack") {
    if (state.panelOpen) return { type: "close-panel" };
    if (state.controlsVisible && !state.modalState) return { type: "hide-controls" };
    return { type: "close" };
  }
  if (state.panelOpen) {
    // Panels are lists: arrows walk the rows, Enter activates natively.
    if (arrow) return { type: "move-focus", direction: arrow };
    return key === "Enter" ? { type: "native" } : { type: "none" };
  }
  if (!state.controlsVisible) {
    if (arrow === "left") return { type: "seek", seconds: -REMOTE_SEEK_SECONDS };
    if (arrow === "right") return { type: "seek", seconds: REMOTE_SEEK_SECONDS };
    if (arrow === "up" || arrow === "down" || key === "Enter") return { type: "show-controls" };
    if (key === " " || key === "k") return { type: "toggle-play" };
    return { type: "none" };
  }
  if (!state.focusInControls) {
    // Visible bar but focus elsewhere (mouse user, or just revealed): the
    // first arrow lands on the controls; Left/Right still seek.
    if (arrow === "left") return { type: "seek", seconds: -REMOTE_SEEK_SECONDS };
    if (arrow === "right") return { type: "seek", seconds: REMOTE_SEEK_SECONDS };
    if (arrow || key === "Enter") return { type: "show-controls" };
    if (key === " " || key === "k") return { type: "toggle-play" };
    return { type: "none" };
  }
  // The seek bar keeps its own Left/Right (native range stepping).
  if (state.onSeekBar && (arrow === "left" || arrow === "right")) return { type: "native" };
  if (arrow) return { type: "move-focus", direction: arrow };
  if (key === "Enter") return { type: "native" };
  if (key === "k") return { type: "toggle-play" };
  return { type: "none" };
}

type Rect = { left: number; top: number; width: number; height: number };

/** Nearest candidate in a direction (same scoring as lib/tvNav). */
export function nearestInDirection<T>(from: Rect, candidates: Array<{ item: T; rect: Rect }>, direction: "up" | "down" | "left" | "right"): T | null {
  const fx = from.left + from.width / 2;
  const fy = from.top + from.height / 2;
  let best: T | null = null;
  let bestScore = Infinity;
  for (const { item, rect } of candidates) {
    const tx = rect.left + rect.width / 2;
    const ty = rect.top + rect.height / 2;
    const primary = direction === "up" ? fy - ty : direction === "down" ? ty - fy : direction === "left" ? fx - tx : tx - fx;
    const secondary = direction === "up" || direction === "down" ? Math.abs(tx - fx) : Math.abs(ty - fy);
    if (primary <= 4) continue;
    const score = primary + secondary * 2;
    if (score < bestScore) { bestScore = score; best = item; }
  }
  return best;
}

const FOCUSABLE = 'button:not([disabled]), [href], input:not([disabled]), select:not([disabled]), [tabindex]:not([tabindex="-1"])';

function visible(element: HTMLElement) {
  if (element.closest('[aria-hidden="true"], [inert], [hidden]')) return false;
  const rect = element.getBoundingClientRect();
  return rect.width >= 4 && rect.height >= 4;
}

export function focusableWithin(root: ParentNode) {
  return Array.from(root.querySelectorAll<HTMLElement>(FOCUSABLE)).filter(visible);
}

/** Move focus geometrically among the focusable elements inside `root`. */
export function moveFocusWithin(root: ParentNode, direction: "up" | "down" | "left" | "right", current: Element | null = document.activeElement) {
  const all = focusableWithin(root);
  if (!all.length) return false;
  if (!(current instanceof HTMLElement) || !all.includes(current)) {
    all[0].focus({ preventScroll: true });
    return true;
  }
  const next = nearestInDirection(current.getBoundingClientRect(), all.filter((el) => el !== current).map((item) => ({ item, rect: item.getBoundingClientRect() })), direction);
  if (!next) return false;
  next.focus({ preventScroll: true });
  next.scrollIntoView?.({ block: "nearest", inline: "nearest" });
  return true;
}

/** Keep Tab / Shift+Tab inside a dialog. Returns true when it moved focus. */
export function trapTab(event: KeyboardEvent, root: HTMLElement) {
  if (event.key !== "Tab") return false;
  const all = focusableWithin(root);
  if (!all.length) { event.preventDefault(); return true; }
  const first = all[0];
  const last = all[all.length - 1];
  const active = document.activeElement;
  if (event.shiftKey && (active === first || !root.contains(active))) { event.preventDefault(); last.focus(); return true; }
  if (!event.shiftKey && (active === last || !root.contains(active))) { event.preventDefault(); first.focus(); return true; }
  return false;
}

// lib/tvNav synthesises TV "Back" as TWO Escape keydowns (one on the focused
// element, one on window). Layers that close on Escape must act once per
// press, or Back in the source picker would also close the details page.
// Every listener sees the same answer for one event (only the topmost layer
// acts on it); a synthetic Escape right after a fresh one is the duplicate.
let lastEscapeAt = -Infinity;
const freshEscapes = new WeakSet<object>();
const duplicateEscapes = new WeakSet<object>();
export function claimEscape(event: Pick<KeyboardEvent, "key" | "isTrusted">, now = typeof performance !== "undefined" ? performance.now() : Date.now()) {
  if (event.key !== "Escape") return false;
  if (freshEscapes.has(event)) return true;
  if (duplicateEscapes.has(event)) return false;
  if (!event.isTrusted && now - lastEscapeAt < 150) { duplicateEscapes.add(event); return false; }
  freshEscapes.add(event);
  lastEscapeAt = now;
  return true;
}
export function resetEscapeClaim() { lastEscapeAt = -Infinity; }

/** Is `layer` the topmost open dialog (and no full player covers it)? */
export function isTopmostLayer(layer: Element | null) {
  if (!layer || typeof document === "undefined") return false;
  const player = document.querySelector(".player-overlay:not(.player-docked)");
  if (player && player !== layer && !layer.contains(player)) return false;
  const dialogs = Array.from(document.querySelectorAll('[role="dialog"]')).filter((el) => !el.closest("[hidden], [inert]"));
  return dialogs[dialogs.length - 1] === layer;
}

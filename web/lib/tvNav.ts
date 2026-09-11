// TV & keyboard navigation. Makes the whole app drivable with a D-pad remote
// (Samsung Tizen, LG webOS, Android TV browsers, Fire TV) or a plain keyboard:
//   - Arrow keys move focus GEOMETRICALLY to the nearest interactive element in
//     that direction (spatial navigation) — Enter then activates it natively,
//     since every card/row/control in the app is a real <button>/<a>.
//   - TV "Back" keys (Tizen 10009, webOS 461, GoBack) are translated into an
//     Escape keydown so the existing close handlers (player, drawers, panels)
//     respond without knowing about TV key codes.
//   - Remote media keys (play/pause/rewind/fast-forward) drive the <video>
//     element directly, so playback control works even when the player's own
//     shortcuts aren't focused.
//
// The engine stands down while the video player overlay is open (it owns the
// arrow keys for seek/volume) and whenever a text field has focus.

import { enterKbdNav } from "./hoverParity";

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
 * Are we in a TV browser (Samsung Tizen, LG webOS, other smart-TV shells)?
 *
 * Used to flip an `is-tv` class on <html> that CSS keys off for an
 * always-visible focus ring and for dropping GPU-expensive effects, and to
 * switch focus scrolling from smooth to instant — TV SoCs janks through
 * animated scrolls, and old TV engines are exactly where smoothness matters
 * least.
 */
export function isTvBrowser(): boolean {
  if (typeof navigator === "undefined") return false;
  return /\b(tizen|web0s|webos|smart-?tv|netcast|hbbtv)\b/i.test(navigator.userAgent);
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
  return (
    tag === "INPUT" ||
    tag === "TEXTAREA" ||
    tag === "SELECT" ||
    target.isContentEditable
  );
}

export function playerIsOpen(): boolean {
  return document.querySelector(".player-overlay:not(.player-docked)") !== null;
}

function isVisible(el: HTMLElement): boolean {
  if (el.closest('[aria-hidden="true"], [inert]')) return false;
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

// When a modal layer is open (source picker, cast modal, details drawer),
// spatial focus must stay inside it — otherwise the arrows jump to elements
// behind the overlay. The LAST matching layer in the DOM is the topmost one.
const FOCUS_SCOPES = '[role="dialog"], .details-drawer';

function focusScope(): ParentNode {
  const scopes = document.querySelectorAll<HTMLElement>(FOCUS_SCOPES);
  return scopes.length > 0 ? scopes[scopes.length - 1] : document;
}

function candidates(): HTMLElement[] {
  return Array.from(focusScope().querySelectorAll<HTMLElement>(FOCUS_SELECTOR)).filter(isVisible);
}

// Score how good `to` is as a move from `from` in `direction`: primary-axis
// distance plus a penalty for sideways drift. Lower is better; negative-axis
// candidates (wrong side) are rejected.
function directionalScore(from: DOMRect, to: DOMRect, direction: Direction): number | null {
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

function moveFocus(direction: Direction): boolean {
  const current = document.activeElement instanceof HTMLElement ? document.activeElement : null;
  const all = candidates();
  if (all.length === 0) return false;

  if (!current || current === document.body || !all.includes(current)) {
    // Nothing focused yet: start at the top-left-most interactive element.
    const first = all
      .slice()
      .sort((a, b) => {
        const ra = a.getBoundingClientRect();
        const rb = b.getBoundingClientRect();
        return ra.top - rb.top || ra.left - rb.left;
      })[0];
    focusElement(first);
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
  el.scrollIntoView({ block: "nearest", inline: "nearest", behavior: isTvBrowser() ? "auto" : "smooth" });
}

function synthesizeEscape() {
  const escape = new KeyboardEvent("keydown", { key: "Escape", bubbles: true, cancelable: true });
  (document.activeElement ?? document.body).dispatchEvent(escape);
  window.dispatchEvent(new KeyboardEvent("keydown", { key: "Escape" }));
}

function appVideo(): HTMLVideoElement | null {
  return document.querySelector("video");
}

function onKeyDown(event: KeyboardEvent) {
  if (event.defaultPrevented) return;
  // --- Escape frees a focused text field. ---
  // Without this, remotes and controllers have no way OUT of an input: the
  // guard below swallows their arrows and nothing else listens. TV Back runs
  // through here too (synthesizeEscape re-dispatches onto the field), so the
  // first Back blurs and the next one closes layers.
  if (event.key === "Escape" && isTextTarget(event.target)) {
    (event.target as HTMLElement).blur();
    return;
  }

  // --- TV Back (Tizen/webOS/remotes): translate into Escape everywhere. ---
  // "XF86Back" is what some older Tizen builds report as event.key.
  if (TV_BACK_CODES.has(event.keyCode) || event.key === "GoBack" || event.key === "BrowserBack" || event.key === "XF86Back") {
    event.preventDefault(); // keep the TV browser from exiting the app
    enterKbdNav();
    synthesizeEscape();
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
      synthesizeEscape();
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
  // Trusted arrows in a text field belong to the caret / select value. The
  // gamepad's synthetic arrows have no default action at all, so for them
  // spatial navigation is the only way OUT of a field — let them through.
  if (event.isTrusted && isTextTarget(event.target)) return;
  // Key-driven navigation: light up hover-equivalent focus styling.
  enterKbdNav();
  if (playerIsOpen()) return; // the player owns arrows (seek/volume)
  if (moveFocus(direction)) event.preventDefault(); // stop page scroll on a handled move
}

let installed = false;

export function installTvNav() {
  if (installed || typeof window === "undefined") return;
  installed = true;
  window.addEventListener("keydown", onKeyDown);
  // CSS keys off this for the always-visible focus ring and for skipping
  // effects TV GPUs struggle with (backdrop blur). Done here rather than in a
  // component so it exists before first paint settles.
  if (isTvBrowser()) document.documentElement.classList.add("is-tv");
}

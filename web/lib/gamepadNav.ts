// Game-controller navigation (HTPC mode). An Xbox/PlayStation pad drives the
// whole app the way Plex HTPC does: the D-pad / left stick move focus through
// the existing spatial-navigation layer (tvNav), A activates, B backs out, and
// the shoulder buttons seek inside the player.
//
// Full map (standard layout; Xbox names):
//   D-pad / left stick   move focus (repeats while held)
//   A                    activate; on a text field, open the on-screen keyboard
//   B                    Back (the shared ladder in tvNav.performBack)
//   X                    play / pause
//   Y                    tap: fullscreen · hold ~0.6 s: sources panel
//   LB / RB              seek −/+ inside the player
//   View (Select)        in the player: subtitles panel · elsewhere: Back
//
// Browsers never turn gamepad input into key events, so this polls the Gamepad
// API each frame and translates state changes into the SAME synthetic keydowns
// tvNav and the player overlay already consume. Two subtleties drive the
// design:
//   - Synthetic (untrusted) key events run our JS handlers but never trigger
//     browser default actions — so "A" cannot be a synthetic Enter on a
//     focused button; it calls element.click() directly.
//   - Events are dispatched on document.activeElement (bubbling to window), so
//     tvNav's text-field guard sees the real target, exactly as with a
//     hardware keyboard.
//
// The poll loop only runs while a pad is connected and the tab is visible.

import { enterKbdNav } from "./hoverParity";
import { FOCUS_SELECTOR, isTypeableField, performBack, playerIsOpen, requestOnScreenKeyboard } from "./tvNav";
import { dispatchPlayerCommand } from "./playerCommands";

/** Standard-mapping button indices (w3c.github.io/gamepad/#remapping). */
const BTN = {
  A: 0,
  B: 1,
  X: 2,
  Y: 3,
  LB: 4,
  RB: 5,
  SELECT: 8,
  DPAD_UP: 12,
  DPAD_DOWN: 13,
  DPAD_LEFT: 14,
  DPAD_RIGHT: 15,
} as const;

/** Stick tilt below this is ignored — resting sticks drift. */
const DEADZONE = 0.5;
/** Hold a direction this long before it starts repeating… */
const REPEAT_DELAY_MS = 400;
/** …then repeat at this rate. Matches TV remote feel. */
const REPEAT_INTERVAL_MS = 110;
/** Holding Y this long opens sources instead of toggling fullscreen. */
export const LONG_PRESS_MS = 600;

export type Intent =
  | "up"
  | "down"
  | "left"
  | "right"
  | "accept"
  | "back"
  | "playpause"
  | "fullscreen"
  | "seekBack"
  | "seekForward"
  | "view"
  | "sources";

/** Which intents auto-repeat while held. Face buttons fire once per press. */
const REPEATING: ReadonlySet<Intent> = new Set([
  "up",
  "down",
  "left",
  "right",
  "seekBack",
  "seekForward",
]);

export type PadSnapshot = {
  buttons: boolean[];
  axes: number[];
};

// intent -> next allowed fire time; "y:down"/"y:long" track the Y long-press.
export type IntentClock = Map<Intent | "y:down" | "y:long", number>;

/** Read one gamepad into a plain snapshot (also what tests construct). */
export function snapshotPad(pad: Gamepad): PadSnapshot {
  return {
    buttons: pad.buttons.map((b) => b.pressed),
    axes: [...pad.axes],
  };
}

function wantsIntent(pads: PadSnapshot[], intent: Intent): boolean {
  const pressed = (index: number) => pads.some((p) => p.buttons[index] === true);
  const axis = (index: number) => pads.reduce((max, p) => {
    const v = p.axes[index] ?? 0;
    return Math.abs(v) > Math.abs(max) ? v : max;
  }, 0);
  switch (intent) {
    case "up": return pressed(BTN.DPAD_UP) || axis(1) < -DEADZONE;
    case "down": return pressed(BTN.DPAD_DOWN) || axis(1) > DEADZONE;
    case "left": return pressed(BTN.DPAD_LEFT) || axis(0) < -DEADZONE;
    case "right": return pressed(BTN.DPAD_RIGHT) || axis(0) > DEADZONE;
    case "accept": return pressed(BTN.A);
    case "back": return pressed(BTN.B);
    case "view": return pressed(BTN.SELECT);
    case "playpause": return pressed(BTN.X);
    // Y is resolved by press duration in computeFiredIntents.
    case "fullscreen": case "sources": return false;
    case "seekBack": return pressed(BTN.LB);
    case "seekForward": return pressed(BTN.RB);
  }
}

const ALL_INTENTS: readonly Intent[] = [
  "up", "down", "left", "right",
  "accept", "back", "view", "playpause",
  "seekBack", "seekForward",
];

/**
 * Pure core: given current pad state, the per-intent repeat clock and "now",
 * return the intents that fire THIS frame and update the clock. Kept free of
 * DOM access so it can be exercised without a physical controller.
 */
export function computeFiredIntents(
  pads: PadSnapshot[],
  clock: IntentClock,
  now: number
): Intent[] {
  const fired: Intent[] = [];
  for (const intent of ALL_INTENTS) {
    if (!wantsIntent(pads, intent)) {
      clock.delete(intent);
      continue;
    }
    const nextAt = clock.get(intent);
    if (nextAt === undefined) {
      // Rising edge: fire immediately, arm the initial repeat delay.
      fired.push(intent);
      clock.set(intent, now + REPEAT_DELAY_MS);
    } else if (REPEATING.has(intent) && now >= nextAt) {
      fired.push(intent);
      clock.set(intent, now + REPEAT_INTERVAL_MS);
    }
    // Held non-repeating intents stay armed but silent until released.
  }
  // Y: a tap (released before LONG_PRESS_MS) is fullscreen and fires on
  // release; a hold fires "sources" once, as soon as the threshold passes,
  // and its release is silent.
  const yDown = pads.some((p) => p.buttons[BTN.Y] === true);
  const downAt = clock.get("y:down");
  if (yDown) {
    if (downAt === undefined) clock.set("y:down", now);
    else if (!clock.has("y:long") && now - downAt >= LONG_PRESS_MS) {
      fired.push("sources");
      clock.set("y:long", now);
    }
  } else if (downAt !== undefined) {
    if (!clock.has("y:long")) fired.push("fullscreen");
    clock.delete("y:down");
    clock.delete("y:long");
  }
  return fired;
}

function dispatchKey(key: string) {
  const target = document.activeElement instanceof HTMLElement ? document.activeElement : document.body;
  target.dispatchEvent(new KeyboardEvent("keydown", { key, bubbles: true, cancelable: true }));
}

/**
 * Step a focused <select> the way its native picker would. Untrusted arrow
 * events never run the browser's option-change default action, so without
 * this a select is a dead end for a controller.
 */
function stepSelect(select: HTMLSelectElement, delta: number) {
  const options = Array.from(select.options);
  let index = select.selectedIndex;
  do {
    index += delta;
  } while (options[index] && options[index].disabled);
  if (index < 0 || index >= options.length || index === select.selectedIndex) return;
  select.selectedIndex = index;
  select.dispatchEvent(new Event("input", { bubbles: true }));
  select.dispatchEvent(new Event("change", { bubbles: true }));
}

function activate() {
  const el = document.activeElement;
  // While the player overlay covers the app, focus can still sit on the
  // (hidden) control that launched playback — clicking it would restart the
  // stream. A during playback means play/pause on every HTPC player.
  const overlay = document.querySelector(".player-overlay");
  if (overlay && !(el && overlay.contains(el))) {
    dispatchKey(" ");
    return;
  }
  // A text field has no keyboard on a couch: A opens the built-in one.
  if (isTypeableField(el) && requestOnScreenKeyboard(el)) return;
  if (el instanceof HTMLElement && el.matches(FOCUS_SELECTOR)) {
    // An untrusted click() on a target="_blank" anchor is popup-blocked
    // (needs user activation, which pads never mint) — navigate in place.
    if (el instanceof HTMLAnchorElement && el.target === "_blank" && el.href) {
      window.location.href = el.href;
      return;
    }
    // A synthetic Enter would reach our handlers but never the browser's
    // default button activation — click() is the reliable path.
    el.click();
    return;
  }
  dispatchKey("Enter");
}

function goBack() {
  // One ladder for remotes and pads: free a field → layers consume Escape →
  // cancel a native <dialog> → app history (details, non-home tabs), which
  // BackHandler's popstate handler applies. See tvNav.performBack.
  performBack();
}

export function fireIntent(intent: Intent) {
  // A controller can't hover; every pad input keeps hover-parity mode lit.
  enterKbdNav();
  // A focused <select> owns up/down (value stepping); left/right fall through
  // to spatial navigation so the pad can leave it.
  const active = document.activeElement;
  if (active instanceof HTMLSelectElement && (intent === "up" || intent === "down")) {
    stepSelect(active, intent === "down" ? 1 : -1);
    return;
  }
  switch (intent) {
    case "up": dispatchKey("ArrowUp"); break;
    case "down": dispatchKey("ArrowDown"); break;
    case "left": dispatchKey("ArrowLeft"); break;
    case "right": dispatchKey("ArrowRight"); break;
    case "accept": activate(); break;
    case "back": goBack(); break;
    // View/Select: subtitles inside the player; Back everywhere else (its
    // previous meaning, kept for muscle memory).
    case "view": if (playerIsOpen()) dispatchPlayerCommand("subtitles"); else goBack(); break;
    case "sources": if (playerIsOpen()) dispatchPlayerCommand("sources"); break;
    // The player overlay's keydown handler owns these when it is open;
    // outside the player, space and "f" are harmless no-ops…
    case "playpause": dispatchKey(" "); break;
    case "fullscreen": dispatchKey("f"); break;
    // …but the seek arrows would double as left/right navigation, so the
    // shoulder buttons only act inside the player.
    case "seekBack": if (playerIsOpen()) dispatchKey("ArrowLeft"); break;
    case "seekForward": if (playerIsOpen()) dispatchKey("ArrowRight"); break;
  }
}

let installed = false;
let rafId = 0;
// Indices, not a counter: the initial scan and the gamepadconnected event can
// both observe the same pad (Blink even dispatches the event synchronously
// from addEventListener), and a double-counted pad would keep the poll loop
// alive forever after disconnect. Set.add is idempotent.
const padIndices = new Set<number>();
const clock: IntentClock = new Map();

// Only standard-mapping pads: wheels, HOTAS sticks and pedals report mapping
// "" with scrambled button indices, and their throttle axes REST beyond the
// deadzone — polling one would machine-gun navigation without any input.
function isUsablePad(pad: Gamepad | null): pad is Gamepad {
  return pad !== null && pad.connected && pad.mapping === "standard";
}

function poll() {
  rafId = 0;
  if (padIndices.size === 0 || document.hidden) return;
  const pads: PadSnapshot[] = [];
  for (const pad of navigator.getGamepads?.() ?? []) {
    if (isUsablePad(pad)) pads.push(snapshotPad(pad));
  }
  for (const intent of computeFiredIntents(pads, clock, performance.now())) {
    fireIntent(intent);
  }
  rafId = requestAnimationFrame(poll);
}

function startLoop() {
  if (rafId === 0 && padIndices.size > 0 && !document.hidden) rafId = requestAnimationFrame(poll);
}

function stopLoop() {
  if (rafId !== 0) cancelAnimationFrame(rafId);
  rafId = 0;
  clock.clear();
}

export function installGamepadNav() {
  if (installed || typeof window === "undefined" || !("getGamepads" in navigator)) return;
  installed = true;
  window.addEventListener("gamepadconnected", (event) => {
    const pad = (event as GamepadEvent).gamepad;
    if (!pad || pad.mapping !== "standard") return;
    padIndices.add(pad.index);
    startLoop();
  });
  window.addEventListener("gamepaddisconnected", (event) => {
    const pad = (event as GamepadEvent).gamepad;
    if (pad) padIndices.delete(pad.index);
    if (padIndices.size === 0) stopLoop();
  });
  document.addEventListener("visibilitychange", () => {
    if (document.hidden) stopLoop();
    else startLoop();
  });
  // A pad connected before page load fires no event until its first input in
  // some browsers; a cheap initial scan catches the already-connected case.
  for (const pad of navigator.getGamepads?.() ?? []) {
    if (isUsablePad(pad)) padIndices.add(pad.index);
  }
  startLoop();
}

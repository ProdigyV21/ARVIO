// Live TV zapping in the fullscreen player (parity with Android's
// LiveTvScreen fullscreen key plan): Up/CH+ next channel, Down/CH- previous,
// digits tune a channel number, OK shows now/next, Back returns to the guide.
//
// The player owns the keyboard while it is fullscreen; Live TV owns the
// channel list. The player's key handler forwards keys here and Live TV
// registers what a key should do, so neither imports the other.

/** Tizen/webOS remote channel keys (no standard `key` value on older TVs). */
export const TV_CHANNEL_UP_CODES = new Set([427, 33]);
export const TV_CHANNEL_DOWN_CODES = new Set([428, 34]);
/** A held or bouncing channel key must not become a burst of stream requests. */
export const MIN_ZAP_INTERVAL_MS = 150;
/** Digits typed within this window build one channel number (Android: 1.5 s). */
export const DIGIT_WINDOW_MS = 1500;
/** An unambiguous prefix tunes after this pause (Android: 1.2 s). */
export const DIGIT_COMMIT_MS = 1200;

export type LiveZapAction =
  | { type: "zap"; delta: 1 | -1 }
  | { type: "digit"; digit: number }
  | { type: "info" }
  | { type: "back" };

/** Map a key event to a zap action, or null when the key is not ours. */
export function liveZapAction(event: Pick<KeyboardEvent, "key" | "keyCode" | "altKey" | "ctrlKey" | "metaKey">, targetIsControl = false): LiveZapAction | null {
  if (event.altKey || event.ctrlKey || event.metaKey) return null;
  const { key, keyCode } = event;
  if (key === "ArrowUp" || key === "ChannelUp" || key === "PageUp" || TV_CHANNEL_UP_CODES.has(keyCode) && key !== "ArrowDown") return { type: "zap", delta: 1 };
  if (key === "ArrowDown" || key === "ChannelDown" || key === "PageDown" || TV_CHANNEL_DOWN_CODES.has(keyCode)) return { type: "zap", delta: -1 };
  if (/^[0-9]$/.test(key)) return { type: "digit", digit: Number(key) };
  // Remote OK on the video itself; on a focused player button it stays a click.
  if ((key === "Enter" || key === "OK" || key === "Select") && !targetIsControl) return { type: "info" };
  if (key === "Escape" || key === "GoBack" || key === "BrowserBack") return { type: "back" };
  return null;
}

/** The channel a zap lands on: neighbours in the current list, wrapping round. */
export function zapTarget<T extends { id: string }>(channels: T[], currentId: string | null | undefined, delta: 1 | -1): T | null {
  if (!channels.length) return null;
  const index = currentId ? channels.findIndex((channel) => channel.id === currentId) : -1;
  if (index < 0) return channels[delta > 0 ? 0 : channels.length - 1];
  if (channels.length === 1) return null;
  return channels[(index + delta + channels.length) % channels.length];
}

/** Append a digit (max 4, like Android) unless the previous one is too old. */
export function appendChannelDigit(buffer: string, digit: number, lastAt: number, now: number): string {
  const prefix = now - lastAt > DIGIT_WINDOW_MS ? "" : buffer;
  return (prefix + String(digit)).slice(-4);
}

function channelNumber(channel: { number?: string }) {
  const raw = channel.number?.trim();
  if (!raw) return "";
  const parsed = Number.parseInt(raw, 10);
  return Number.isFinite(parsed) ? String(parsed) : raw;
}

/** Exact channel-number match, tuned at once. */
export function exactChannelNumber<T extends { number?: string }>(channels: T[], buffer: string): T | null {
  if (!buffer) return null;
  const wanted = String(Number.parseInt(buffer, 10));
  return channels.find((channel) => channelNumber(channel) === wanted) ?? null;
}

/** After the pause: the only channel whose number starts with the buffer, if exactly one. */
export function uniqueChannelNumberPrefix<T extends { number?: string }>(channels: T[], buffer: string): T | null {
  if (!buffer) return null;
  const wanted = String(Number.parseInt(buffer, 10));
  let found: T | null = null;
  for (const channel of channels) {
    if (!channelNumber(channel).startsWith(wanted)) continue;
    if (found) return null;
    found = channel;
  }
  return found;
}

export type LiveZapHandler = (action: LiveZapAction) => boolean;
let handler: LiveZapHandler | null = null;
let lastBackAt = 0;

/** Live TV registers while it is mounted. Returns the unregister function. */
export function registerLiveZap(next: LiveZapHandler) {
  handler = next;
  return () => { if (handler === next) handler = null; };
}

/**
 * Called by the fullscreen player's key handler for live channels. Returns
 * true when the key was handled (the player then ignores it).
 */
export function handleLiveZapKey(event: KeyboardEvent, context: { canDock: boolean; collapse: () => void }): boolean {
  const target = event.target instanceof Element ? event.target : null;
  const isControl = Boolean(target?.closest("button, a, input, select, textarea, [role='button'], [role='slider']"));
  const action = liveZapAction(event, isControl);
  if (!action) return false;
  if (action.type === "back") {
    // TV Back arrives as two synthetic Escapes in quick succession; one Back
    // is one step.
    const now = Date.now();
    if (now - lastBackAt < 80) { event.preventDefault(); return true; }
    if (handler?.(action)) { lastBackAt = now; event.preventDefault(); return true; }
    if (!context.canDock) return false;
    lastBackAt = now;
    event.preventDefault();
    context.collapse();
    return true;
  }
  if (!handler || !handler(action)) return false;
  event.preventDefault();
  return true;
}

/** Set before a zap so the player stays fullscreen when its stream changes. */
let keepExpandedUntil = 0;
export function keepLivePlayerExpanded() { keepExpandedUntil = Date.now() + 5000; }
export function consumeKeepLivePlayerExpanded(): boolean {
  const keep = Date.now() < keepExpandedUntil;
  keepExpandedUntil = 0;
  return keep;
}

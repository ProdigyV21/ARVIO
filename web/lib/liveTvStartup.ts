/**
 * "Start in last channel" (iptvByProfile.startLastChannel, shared with the Android app):
 * the first Home of a page load hands over to Live TV, which then plays that channel.
 * Decided once per page load, and only while the user has not moved on yet.
 */
const STARTUP_WINDOW_MS = 15_000;
let decided = false;
let pendingChannelId: string | null = null;
const loadedAt = Date.now();

export function shouldStartInLastChannel(enabled: boolean | undefined, lastChannelId: string | undefined, section: string, now = Date.now()): string | null {
  if (decided || now - loadedAt > STARTUP_WINDOW_MS) return null;
  if (section !== "home" || !enabled || !lastChannelId) return null;
  decided = true;
  pendingChannelId = lastChannelId;
  return lastChannelId;
}

/** Live TV takes the channel once (null afterwards). */
export function takeStartupChannel(): string | null {
  const id = pendingChannelId;
  pendingChannelId = null;
  return id;
}

export function peekStartupChannel(): string | null {
  return pendingChannelId;
}

import { loadStored, saveStored } from "./storage";

/**
 * Durable queue for Continue Watching progress.
 *
 * Every playback checkpoint is stored here before it is sent, keyed by
 * (profile, title) so a newer checkpoint replaces an unsent older one. Entries
 * survive reloads and app closes (localStorage, with an in-memory mirror when
 * storage is unavailable) and are retried on the next save, when the browser
 * comes back online, regains focus or becomes visible again, and on app start.
 * An entry is removed only after the backend acknowledged that exact entry.
 */

/** Android ContinueWatchingItem field names (TraktRepository.kt). */
export interface ProgressItem {
  id: number;
  title: string;
  mediaType: "movie" | "tv";
  progress: number;
  resumePositionSeconds: number;
  durationSeconds: number;
  season?: number | null;
  episode?: number | null;
  displaySeason?: number | null;
  displayEpisode?: number | null;
  episodeTitle?: string | null;
  backdropPath?: string | null;
  episodeStillPath?: string | null;
  posterPath?: string | null;
  streamKey?: string | null;
  streamAddonId?: string | null;
  streamTitle?: string | null;
  releaseDate?: string;
  isUpNext?: boolean;
  addonNativeId?: string | null;
  addonNativeAddonId?: string | null;
  updatedAtMs: number;
}

export type ProgressOperation =
  | { profileId: string; op: "upsert"; item: ProgressItem }
  | {
    profileId: string;
    op: "remove";
    match: { id: number; mediaType: "movie" | "tv"; season?: number | null; episode?: number | null };
    updatedAtMs: number;
  };

export interface PendingProgress {
  id: string;
  key: string;
  operation: ProgressOperation;
  queuedAt: number;
}

/** "done": acknowledged; "fallback": send through the full-document write; "drop": can never succeed. */
export type ProgressSendResult = "done" | "fallback" | "drop";

export interface ProgressTransport {
  send(operation: ProgressOperation, options: { keepalive: boolean }): Promise<ProgressSendResult>;
  fallback(operation: ProgressOperation): Promise<void>;
  isCurrent(): boolean;
}

/** Periodic checkpoint while playing; pause, seek, close and backgrounding save immediately. */
export const PROGRESS_CHECKPOINT_MS = 30_000;
const MAX_PENDING = 100;
const memory = new Map<string, PendingProgress[]>();
const running = new Map<string, Promise<void>>();
// Accounts whose localStorage write did not stick (quota, private mode): use memory only.
const memoryOnly = new Set<string>();

const storageKey = (userId: string) => `arvio.web.progressOutbox.v1:${userId}`;

export function progressKey(profileId: string, mediaType: string, id: number) {
  return `${profileId}|${String(mediaType).toLowerCase()}:${id}`;
}

function operationKey(operation: ProgressOperation) {
  const target = operation.op === "upsert" ? operation.item : operation.match;
  return progressKey(operation.profileId, target.mediaType, target.id);
}

function operationTime(operation: ProgressOperation) {
  return operation.op === "upsert" ? operation.item.updatedAtMs : operation.updatedAtMs;
}

function read(userId: string): PendingProgress[] {
  const stored = memoryOnly.has(userId) ? null : loadStored<PendingProgress[] | null>(storageKey(userId), null);
  if (Array.isArray(stored)) {
    const valid = stored.filter((entry) => entry && typeof entry.id === "string" && entry.operation);
    memory.set(userId, valid);
    return valid.map((entry) => ({ ...entry }));
  }
  return (memory.get(userId) ?? []).map((entry) => ({ ...entry }));
}

function write(userId: string, entries: PendingProgress[]) {
  const next = entries.slice(-MAX_PENDING);
  memory.set(userId, next);
  if (memoryOnly.has(userId)) return;
  saveStored(storageKey(userId), next);
  const stored = loadStored<PendingProgress[] | null>(storageKey(userId), null);
  // In a browser a failed write leaves the previous list behind; reading it back
  // would silently lose this change. Outside a browser nothing is stored at all.
  if (Array.isArray(stored) && JSON.stringify(stored.map((entry) => entry.id)) !== JSON.stringify(next.map((entry) => entry.id))) {
    memoryOnly.add(userId);
  }
}

function newId() {
  try {
    return crypto.randomUUID();
  } catch {
    return `${Date.now().toString(36)}-${Math.random().toString(36).slice(2)}`;
  }
}

/** Queue one change; an unsent older change for the same title is replaced, a newer one kept. */
export function queueProgress(userId: string, operation: ProgressOperation): PendingProgress | null {
  const key = operationKey(operation);
  const entries = read(userId);
  const previous = entries.find((entry) => entry.key === key);
  if (previous && operationTime(previous.operation) > operationTime(operation)) return null;
  const entry: PendingProgress = { id: newId(), key, operation, queuedAt: Date.now() };
  write(userId, [...entries.filter((candidate) => candidate.key !== key), entry]);
  return entry;
}

export function pendingProgress(userId: string, profileId?: string | null): PendingProgress[] {
  return read(userId).filter((entry) => !profileId || entry.operation.profileId === profileId);
}

/**
 * Forget unsent changes for a title that a newer explicit action (marked
 * watched, removed from Continue Watching) has superseded, so a later flush
 * cannot bring the row back.
 */
export function discardPendingProgress(
  userId: string,
  profileId: string,
  mediaType: string,
  id: number,
  options: { before?: number; matches?: (operation: ProgressOperation) => boolean } = {}
) {
  const key = progressKey(profileId, mediaType, id);
  const before = options.before ?? Date.now();
  const entries = read(userId);
  const kept = entries.filter((entry) => entry.key !== key || operationTime(entry.operation) > before ||
    (options.matches ? !options.matches(entry.operation) : false));
  if (kept.length !== entries.length) write(userId, kept);
}

function acknowledge(userId: string, id: string) {
  const entries = read(userId);
  const kept = entries.filter((entry) => entry.id !== id);
  if (kept.length !== entries.length) write(userId, kept);
}

/**
 * Sends queued changes one at a time, oldest first (changes queued meanwhile
 * are picked up by the same run). Stops at the first transient failure and
 * rethrows it; the remaining entries stay queued. A call made while a run is
 * active waits for it and then sends whatever that run left behind, so its
 * caller learns whether its own change went out.
 */
export async function flushProgressOutbox(userId: string, transport: ProgressTransport, options: { keepalive?: boolean } = {}): Promise<void> {
  const active = running.get(userId);
  if (active) {
    await active.catch(() => undefined);
    if (!transport.isCurrent() || !read(userId).length) return;
    return flushProgressOutbox(userId, transport, options);
  }
  const keepalive = options.keepalive ?? true;
  const run = (async () => {
    const attempted = new Set<string>();
    while (transport.isCurrent()) {
      const entry = read(userId).find((candidate) => !attempted.has(candidate.id));
      if (!entry) break;
      attempted.add(entry.id);
      const result = await transport.send(entry.operation, { keepalive });
      if (result === "fallback") await transport.fallback(entry.operation);
      acknowledge(userId, entry.id);
    }
  })();
  running.set(userId, run);
  try {
    await run;
  } finally {
    if (running.get(userId) === run) running.delete(userId);
  }
}

const installed = new Set<string>();

/** Retry queued progress when the app becomes usable again. Idempotent per account. */
export function installProgressRetry(userId: string, flush: () => void) {
  if (typeof window === "undefined" || installed.has(userId)) return;
  installed.add(userId);
  const retry = () => {
    if (!pendingProgress(userId).length) return;
    flush();
  };
  window.addEventListener("online", retry);
  window.addEventListener("focus", retry);
  window.addEventListener("pageshow", retry);
  // Leaving the page: send what is queued with keepalive so it can finish after close.
  window.addEventListener("pagehide", retry);
  if (typeof document !== "undefined") document.addEventListener("visibilitychange", retry);
}

/** Test seam: forget in-memory state. */
export function resetProgressOutboxForTests() {
  memory.clear();
  memoryOnly.clear();
  running.clear();
  installed.clear();
}

// Which kinds of sources fail on THIS device, remembered across titles.
//
// A "family" is where a link really comes from: the debrid provider, or the
// host plus the first path segments (an addon's usenet relay, a file host).
// When one family keeps failing here (dead usenet links that redirect to a
// "download failed" clip, a host that blocks browsers), autoplay tries the
// other families first instead of spending 3-6 s per dead link on every title.

import { parseDebridStream } from "./debrid";
import type { StreamSource } from "./types";

const KEY = "arvio.web.sourceReliability.v1";
const MAX_ENTRIES = 200;
const MAX_AGE_MS = 7 * 24 * 60 * 60 * 1000;

type Entry = { ok: number; fail: number; at: number };
type Store = Record<string, Entry>;
type StorageLike = Pick<Storage, "getItem" | "setItem">;

function defaultStorage(): StorageLike | undefined {
  try { return typeof window !== "undefined" ? window.localStorage : undefined; } catch { return undefined; }
}

export function sourceFamily(stream: Pick<StreamSource, "url" | "originalUrl">): string | null {
  const raw = stream.originalUrl ?? stream.url;
  if (!raw) return null;
  const debrid = parseDebridStream(raw);
  if (debrid) return `debrid:${debrid.provider}`;
  try {
    const url = new URL(raw);
    if (!/^https?:$/.test(url.protocol)) return null;
    const segments = url.pathname.split("/").filter(Boolean).slice(0, 3);
    // Drop segments that look like ids or tokens: they differ per link, not per family.
    const stable = segments.filter((segment) => segment.length <= 40 && !/^[a-f0-9-]{16,}$/i.test(segment) && !/\d{5,}/.test(segment));
    return [url.hostname, ...stable].join("/");
  } catch {
    return null;
  }
}

function read(storage: StorageLike | undefined): Store {
  try {
    const parsed = JSON.parse(storage?.getItem(KEY) ?? "{}");
    return parsed && typeof parsed === "object" ? parsed as Store : {};
  } catch {
    return {};
  }
}

function write(store: Store, storage: StorageLike | undefined, now: number) {
  const entries = Object.entries(store)
    .filter(([, entry]) => now - entry.at < MAX_AGE_MS)
    .sort((a, b) => b[1].at - a[1].at)
    .slice(0, MAX_ENTRIES);
  try { storage?.setItem(KEY, JSON.stringify(Object.fromEntries(entries))); } catch { /* storage full or blocked */ }
}

export function recordSourceOutcome(stream: Pick<StreamSource, "url" | "originalUrl">, ok: boolean, storage = defaultStorage(), now = Date.now()) {
  const family = sourceFamily(stream);
  if (!family) return;
  const store = read(storage);
  const entry = store[family] && now - store[family].at < MAX_AGE_MS ? store[family] : { ok: 0, fail: 0, at: now };
  // A success forgives earlier failures (a host that was down is back).
  store[family] = ok ? { ok: entry.ok + 1, fail: 0, at: now } : { ok: entry.ok, fail: entry.fail + 1, at: now };
  write(store, storage, now);
}

/**
 * A checker over one read of the store: true when a source's family has failed
 * here repeatedly without playing since. Debrid providers are never demoted.
 */
export function unreliableSourceCheck(storage = defaultStorage(), now = Date.now()) {
  const store = read(storage);
  return (stream: Pick<StreamSource, "url" | "originalUrl">) => {
    const family = sourceFamily(stream);
    if (!family || family.startsWith("debrid:")) return false;
    const entry = store[family];
    return Boolean(entry && now - entry.at < MAX_AGE_MS && entry.fail >= 2);
  };
}

export function isUnreliableSource(stream: Pick<StreamSource, "url" | "originalUrl">, storage = defaultStorage(), now = Date.now()) {
  return unreliableSourceCheck(storage, now)(stream);
}

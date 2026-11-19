"use client";

import { useEffect } from "react";
import { shouldAutoApply, shouldOfferUpdate, updateBlocker, type UpdateTrigger } from "@/lib/updatePolicy";
import { activateWaitingWorker, onWaitingWorker, registerAppServiceWorker } from "@/lib/appServiceWorker";

// iOS home-screen webapps cache the start page HTML for days — users end up
// running old bundles long after a deploy. This watcher compares the bundle's
// baked-in build stamp with the server's /version.json (no-store) on boot, on
// return-to-foreground and every 10 minutes.
//
// A newer deploy is OFFERED ("Update ready — Reload", see UpdateBanner) and
// only applies itself at a safe moment: app start, or the next navigation
// between top-level sections — never while a text field has focus, a dialog
// is open or a player/trailer is on screen. Reloading mid-task used to wipe
// typed text and scroll position.
const RELOAD_GUARD_KEY = "arvio.web.lastUpdateReload";
const ATTEMPTED_VERSION_KEY = "arvio.web.attemptedUpdateVersion";

type UpdateState = { version: string | null; dismissed: boolean };

let state: UpdateState = { version: null, dismissed: false };
let bootAt = 0;
let attempted = "";
let reloading = false;
const listeners = new Set<() => void>();

function setState(next: UpdateState) {
  state = next;
  for (const listener of listeners) listener();
}

export function subscribeUpdate(listener: () => void): () => void {
  listeners.add(listener);
  return () => listeners.delete(listener);
}

export function getUpdateState(): UpdateState {
  return state;
}

/** Hide the banner for this version; it still applies at the next safe moment. */
export function dismissUpdate() {
  if (state.version) setState({ ...state, dismissed: true });
}

function readNumber(key: string): number {
  try { return Number(window.localStorage.getItem(key) ?? 0) || 0; } catch { return 0; }
}

/**
 * Apply the offered update if `trigger` allows it right now. Returns true when
 * a navigation was started.
 */
export function applyUpdate(trigger: UpdateTrigger): boolean {
  const version = state.version;
  if (!version || reloading || typeof window === "undefined") return false;
  const blocked = updateBlocker(document) !== null;
  if (!shouldAutoApply({ trigger, sinceBootMs: Date.now() - bootAt, blocked, now: Date.now(), lastReloadAt: readNumber(RELOAD_GUARD_KEY) })) return false;
  // Remember this exact version, not merely a cooldown: stale HTML can never
  // repeatedly reset the user's current screen.
  attempted = version;
  reloading = true;
  try {
    window.localStorage.setItem(RELOAD_GUARD_KEY, String(Date.now()));
    window.localStorage.setItem(ATTEMPTED_VERSION_KEY, attempted);
  } catch { /* the _v URL also guards the next load when storage is blocked */ }
  // A new service worker (if any) takes over for the reload. HTML is
  // network-first either way, so the fresh document references the newest
  // hashed CSS/JS regardless of which worker answers.
  activateWaitingWorker();
  // location.reload() can re-serve cached HTML on iOS; navigating to a fresh
  // URL forces the document to be re-fetched. The param is stripped on load.
  const url = new URL(window.location.href);
  url.searchParams.set("_v", version);
  window.location.replace(url.toString());
  return true;
}

/** AppShell calls this after every top-level section change (a safe moment). */
export function notifySectionNavigation() {
  applyUpdate("navigation");
}

export function UpdateWatcher() {
  useEffect(() => {
    const baked = process.env.NEXT_PUBLIC_BUILD_STAMP;
    bootAt = Date.now();
    if (process.env.NODE_ENV === "production") void registerAppServiceWorker();
    if (!baked) return undefined;
    let disposed = false;
    let checking = false;
    attempted = new URLSearchParams(window.location.search).get("_v") || "";
    try { attempted ||= window.localStorage.getItem(ATTEMPTED_VERSION_KEY) || ""; } catch { /* storage is optional */ }

    // Strip the cache-bust param left by a prior update reload so the URL stays
    // clean and doesn't keep growing across updates.
    if (window.location.search.includes("_v=")) {
      const clean = new URL(window.location.href);
      clean.searchParams.delete("_v");
      window.history.replaceState(window.history.state, "", clean.toString());
    }

    const check = async () => {
      if (checking || reloading) return;
      checking = true;
      try {
        const response = await fetch("/version.json", { cache: "no-store" });
        if (!response.ok) return;
        const payload = (await response.json()) as { v?: string | number };
        if (disposed) return;
        if (!shouldOfferUpdate({ current: baked, remote: payload?.v, attempted })) return;
        const version = String(payload.v);
        if (state.version !== version) setState({ version, dismissed: false });
        // App start is a safe moment (when nothing is in progress yet).
        applyUpdate("boot");
      } catch {
        // Offline or blocked — retry on the next trigger.
      } finally {
        checking = false;
      }
    };

    const onVisible = () => {
      if (document.visibilityState === "visible") void check();
    };
    void check();
    document.addEventListener("visibilitychange", onVisible);
    const timer = window.setInterval(() => void check(), 10 * 60 * 1000);
    // A new service worker waiting without a new /version.json (worker-only
    // change) is offered the same way, keyed by the running build.
    const stopWaiting = onWaitingWorker(() => {
      if (!state.version) setState({ version: `${baked}-sw`, dismissed: false });
    });
    return () => {
      disposed = true;
      document.removeEventListener("visibilitychange", onVisible);
      window.clearInterval(timer);
      stopWaiting();
    };
  }, []);

  return null;
}

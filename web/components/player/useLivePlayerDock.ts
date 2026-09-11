"use client";

import { useEffect, useState, type CSSProperties } from "react";

/** Reposition the existing player, never mount a second video/connection for preview. */
export function useLivePlayerDock(enabled: boolean, identity: string, onClose: () => void) {
  const [bounds, setBounds] = useState<CSSProperties>();
  const [expanded, setExpanded] = useState(false);
  useEffect(() => setExpanded(false), [identity]);
  useEffect(() => {
    if (!enabled) return;
    const expand = () => setExpanded(true);
    window.addEventListener("arvio:expand-live-player", expand);
    return () => window.removeEventListener("arvio:expand-live-player", expand);
  }, [enabled]);
  useEffect(() => {
    if (!enabled) { setBounds(undefined); return; }
    let slot: HTMLElement | null = null;
    let attached = false;
    let frame = 0;
    let followUntil = 0;
    let closed = false;
    const closeMissingSlot = () => {
      setBounds(undefined);
      if (attached && !closed) { closed = true; onClose(); }
    };
    const update = () => {
      frame = 0;
      if (!slot?.isConnected) { closeMissingSlot(); return; }
      const r = slot.getBoundingClientRect();
      if (r.width <= 0 || r.height <= 0) { setBounds(undefined); return; }
      const next = { position: "fixed" as const, inset: "auto", left: r.left, top: r.top, width: r.width, height: r.height };
      setBounds(previous => previous?.left === next.left && previous?.top === next.top
        && previous?.width === next.width && previous?.height === next.height ? previous : next);
      if (performance.now() < followUntil) frame = requestAnimationFrame(update);
    };
    const measure = () => {
      if (!frame) frame = requestAnimationFrame(update);
    };
    // A slot can move without resizing (drawers, banners, grid transitions).
    // Follow only the short transition window, never a permanent render loop.
    const followLayout = (event: TransitionEvent) => {
      if (!slot || !(event.target instanceof Element) || !event.target.contains(slot)) return;
      followUntil = performance.now() + 500;
      measure();
    };
    const resize = new ResizeObserver(measure);
    const discover = () => {
      if (slot?.isConnected) { measure(); return; }
      const next = document.getElementById("live-tv-player-dock");
      if (!next && attached) { closeMissingSlot(); return; }
      if (!next) return;
      if (slot) resize.unobserve(slot);
      slot = next; attached = true; resize.observe(slot); measure();
    };
    const mutations = new MutationObserver(discover);
    mutations.observe(document.body, { childList: true, subtree: true });
    window.addEventListener("scroll", measure, true);
    window.addEventListener("resize", measure);
    document.addEventListener("transitionrun", followLayout, true);
    document.addEventListener("transitionend", measure, true);
    discover();
    return () => {
      resize.disconnect(); mutations.disconnect(); cancelAnimationFrame(frame);
      window.removeEventListener("scroll", measure, true); window.removeEventListener("resize", measure);
      document.removeEventListener("transitionrun", followLayout, true);
      document.removeEventListener("transitionend", measure, true);
    };
  }, [enabled, identity, onClose]);
  return { docked: enabled && Boolean(bounds) && !expanded, canDock: enabled && Boolean(bounds),
    style: enabled && !expanded ? bounds : undefined, expand: () => setExpanded(true), collapse: () => setExpanded(false) };
}

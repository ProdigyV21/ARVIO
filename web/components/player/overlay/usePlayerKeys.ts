// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useEffect, type RefObject, type Dispatch, type SetStateAction } from "react";
import { trapTab, claimEscape, playerKeyAction, moveFocusWithin } from "@/lib/playerKeys";
import { handleLiveZapKey } from "@/lib/liveZap";
import { isFullscreenActive } from "@/lib/playerPlatform";
import { type PlayerCommandDetail, PLAYER_COMMAND_EVENT } from "@/lib/playerCommands";
import { SKIP_SECONDS, type PlayerPanel } from "./helpers";
import type { useLivePlayerDock } from "../useLivePlayerDock";

/** Keyboard, TV remote and controller handling for the player. */
export function usePlayerKeys({ activePanel, booted, containerRef, dock, ended, error, flashControls, hideControlsNow, liveTv, onClose, openPanel, revealControls, seekBy, setActivePanel, showControls, toggleFullscreen, togglePlay, videoRef }: { activePanel: PlayerPanel; booted: boolean; containerRef: RefObject<HTMLDivElement | null>; dock: ReturnType<typeof useLivePlayerDock>; ended: boolean; error: boolean; flashControls: () => void; hideControlsNow: () => void; liveTv: boolean; onClose: () => void; openPanel: (panel: Exclude<PlayerPanel, null>) => void; revealControls: () => void; seekBy: (delta: number) => void; setActivePanel: Dispatch<SetStateAction<PlayerPanel>>; showControls: boolean; toggleFullscreen: () => void; togglePlay: () => void; videoRef: RefObject<HTMLVideoElement | null> }) {
  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if (dock.docked) return;
      const container = containerRef.current;
      if (!container) return;
      if (e.defaultPrevented || e.altKey || e.ctrlKey || e.metaKey) return;
      if (e.key === "Tab") { trapTab(e, container); flashControls(); return; }
      const target = e.target;
      const inField = target instanceof HTMLElement && target.closest('input:not([type="range"]), select, textarea, [contenteditable="true"]');
      if (inField && e.key !== "Escape") return;
      if (e.key === "Escape" && !claimEscape(e)) return;
      // Live TV zapping (Up/Down, CH+/-, digits, OK info, Back → guide): lib/liveZap.ts.
      // While the control bar is up and focused, arrows move between its
      // buttons and Back hides it first; digits and CH+/- still zap.
      const focusedControl = document.activeElement instanceof HTMLElement && container.contains(document.activeElement)
        ? document.activeElement.closest(".player-controls, .player-top") : null;
      const controlBarKey = e.key.startsWith("Arrow") || e.key === "Escape";
      if (liveTv && !activePanel && !(controlBarKey && showControls && focusedControl) && handleLiveZapKey(e, dock)) return;
      switch (e.key) {
        case "m": { const v = videoRef.current; if (v) v.muted = !v.muted; return; }
        case "c": openPanel("subtitles"); return;
        case "s": openPanel("sources"); return;
        case "f": toggleFullscreen(); return;
        case "j": e.preventDefault(); seekBy(-SKIP_SECONDS); return;
        case "l": e.preventDefault(); seekBy(SKIP_SECONDS); return;
        default: break;
      }
      if (e.key === " ") {
        // A real Space on a focused button presses that button; the
        // gamepad's synthetic Space (X) has no default and means play/pause.
        if (e.isTrusted && target instanceof HTMLElement && target.closest("button, [role=\"button\"]")) { flashControls(); return; }
        e.preventDefault();
        togglePlay();
        return;
      }
      const active = document.activeElement;
      const focusInControls = Boolean(active && active !== container && container.contains(active)
        && (active as HTMLElement).closest(".player-controls, .player-top, .player-side-panel, .player-error, .player-end, .player-upnext, .player-skip, .player-boot, .player-start-prompt"));
      const action = playerKeyAction(e.key, {
        controlsVisible: showControls || Boolean(activePanel),
        panelOpen: Boolean(activePanel),
        modalState: error || ended || !booted,
        onSeekBar: active instanceof HTMLInputElement && active.type === "range",
        focusInControls
      });
      const exitFullscreenFirst = () => {
        // A synthetic Escape (TV Back, gamepad B) can't trigger the browser's
        // own exit-fullscreen default — do it ourselves first.
        if (!e.isTrusted && isFullscreenActive(videoRef.current)) { toggleFullscreen(); return true; }
        return false;
      };
      switch (action.type) {
        case "seek": e.preventDefault(); seekBy(action.seconds); break;
        case "show-controls": e.preventDefault(); revealControls(); break;
        case "toggle-play": e.preventDefault(); togglePlay(); break;
        case "move-focus": {
          e.preventDefault();
          // Panels, the error card and the end screen keep focus to themselves.
          const layer = activePanel ? ".player-side-panel" : error ? ".player-error" : ended ? ".player-end" : null;
          const scope = (layer && container.querySelector(layer)) || container;
          moveFocusWithin(scope, action.direction);
          flashControls();
          break;
        }
        case "close-panel": e.preventDefault(); setActivePanel(null); break;
        case "hide-controls": e.preventDefault(); if (!exitFullscreenFirst()) hideControlsNow(); break;
        case "close": e.preventDefault(); if (!exitFullscreenFirst()) onClose(); break;
        case "native": flashControls(); break;
        default: break;
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [togglePlay, seekBy, toggleFullscreen, flashControls, revealControls, hideControlsNow, onClose, openPanel, activePanel, dock.docked, dock, liveTv, showControls, error, ended, booted]);

  // Controller View/Select and long-press Y (lib/gamepadNav via
  // lib/playerCommands) open the subtitle and source panels.
  useEffect(() => {
    const onCommand = (event: Event) => {
      if (dock.docked) return;
      const command = (event as CustomEvent<PlayerCommandDetail>).detail?.command;
      if (command !== "subtitles" && command !== "sources") return;
      if (liveTv && command === "subtitles") return;
      event.preventDefault();
      openPanel(command);
    };
    window.addEventListener(PLAYER_COMMAND_EVENT, onCommand);
    return () => window.removeEventListener(PLAYER_COMMAND_EVENT, onCommand);
  }, [openPanel, dock.docked, liveTv]);
}

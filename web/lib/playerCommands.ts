// Controller → player commands.
//
// The gamepad layer (lib/gamepadNav.ts) emits these as a cancelable window
// CustomEvent so the player can react without knowing about controllers:
//
//   window.addEventListener(PLAYER_COMMAND_EVENT, (event) => {
//     const { command } = (event as CustomEvent<PlayerCommandDetail>).detail;
//     if (command === "subtitles") { openPanel("subtitles"); event.preventDefault(); }
//   });
//
// A listener that handles the command calls preventDefault(). When nobody
// does, the emitter falls back to the player's existing keyboard shortcut
// ("c" subtitles, "s" sources), so the mapping works before and after the
// player adopts the event.

export const PLAYER_COMMAND_EVENT = "arvio:player-command";

export type PlayerCommand = "subtitles" | "sources";

export type PlayerCommandDetail = { command: PlayerCommand };

const FALLBACK_KEY: Record<PlayerCommand, string> = { subtitles: "c", sources: "s" };

/** Emit a player command; returns true when a listener handled it. */
export function dispatchPlayerCommand(command: PlayerCommand): boolean {
  const event = new CustomEvent<PlayerCommandDetail>(PLAYER_COMMAND_EVENT, { detail: { command }, cancelable: true });
  window.dispatchEvent(event);
  if (event.defaultPrevented) return true;
  const target = document.activeElement instanceof HTMLElement ? document.activeElement : document.body;
  target.dispatchEvent(new KeyboardEvent("keydown", { key: FALLBACK_KEY[command], bubbles: true, cancelable: true }));
  return false;
}

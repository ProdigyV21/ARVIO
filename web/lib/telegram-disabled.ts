// Only selected by the Unraid build alias. No executable imports from the
// original Telegram client, no SDK, service worker or saved-session access.
import { TELEGRAM_DISABLED_MESSAGE } from "./config";
import type { MediaItem, StreamSource } from "./types";
import type { TgAuthState } from "./telegram/client";
import type { TelegramResolveOptions } from "./telegram/index";

export type { TgAuthState, TelegramResolveOptions };
export const TELEGRAM_ADDON_ID = "telegram_native";
export const TELEGRAM_ADDON_NAME = "Telegram";

const disabledState: TgAuthState = { k: "error", message: TELEGRAM_DISABLED_MESSAGE };
export function getAuthState(): TgAuthState { return { ...disabledState }; }
export function subscribe(cb: (state: TgAuthState) => void): () => void {
  cb(getAuthState());
  return () => {};
}
export function isConnected(): boolean { return false; }
export function isTelegramConfigured(): boolean { return false; }
// Startup restoration and disconnect are intentionally inert, retaining saved
// credentials for a different build without reading or deleting them here.
export async function restoreSession(): Promise<void> {}
export async function disconnect(): Promise<void> {}
export function resetToIdle(): void {}
export async function startQrAuth(): Promise<void> { throw new Error(TELEGRAM_DISABLED_MESSAGE); }
export async function startPhoneAuth(_phone: string): Promise<void> { throw new Error(TELEGRAM_DISABLED_MESSAGE); }
export function submitCode(_code: string): void { throw new Error(TELEGRAM_DISABLED_MESSAGE); }
export function submitPassword(_password: string): void { throw new Error(TELEGRAM_DISABLED_MESSAGE); }
export async function initTelegramStreaming(): Promise<void> { throw new Error(TELEGRAM_DISABLED_MESSAGE); }
export async function resolveTelegramSources(
  _item: MediaItem,
  _season: number | undefined,
  _episode: number | undefined,
  _opts: TelegramResolveOptions = {}
): Promise<StreamSource[]> { return []; }

export function sanitizeTelegramApiId(val?: string | number | null): number {
  if (val === undefined || val === null) return 0;
  const str = String(val).trim();
  if (!str || str.toLowerCase().startsWith("your-") || str.toLowerCase() === "disabled") {
    return 0;
  }
  const num = Number(str);
  return Number.isSafeInteger(num) && num > 0 ? num : 0;
}

export function sanitizeTelegramApiHash(val?: string | null): string {
  if (!val) return "";
  const str = String(val).trim();
  if (!str || str.toLowerCase().startsWith("your-") || str.toLowerCase() === "disabled") {
    return "";
  }
  return str;
}

export function isTelegramCredentialsConfigured(apiId: number, apiHash: string): boolean {
  return apiId > 0 && Boolean(apiHash);
}

export const TELEGRAM_API_ID = sanitizeTelegramApiId(process.env.NEXT_PUBLIC_TELEGRAM_API_ID);
export const TELEGRAM_API_HASH = sanitizeTelegramApiHash(process.env.NEXT_PUBLIC_TELEGRAM_API_HASH);
export const isTelegramConfigured = isTelegramCredentialsConfigured(TELEGRAM_API_ID, TELEGRAM_API_HASH);

// localStorage key holding the GramJS StringSession (the authorization key). This
// is the browser equivalent of Android's on-device TDLib database — losing it
// just means the user re-scans the QR code.
export const TELEGRAM_SESSION_KEY = "arvio.web.telegram.session";

// Resolver tuning — mirrors TelegramSourceResolver.kt.
export const TELEGRAM_SCORE_THRESHOLD = 55;
export const TELEGRAM_SEARCH_TIMEOUT_MS = 20_000;
export const TELEGRAM_MAX_RESULTS = 100;

// addonId stamped on every Telegram source so the store can keep them in the
// list when addon/debrid streams resolve later (see mergeStreams).
export const TELEGRAM_ADDON_ID = "telegram_native";
export const TELEGRAM_ADDON_NAME = "Telegram";

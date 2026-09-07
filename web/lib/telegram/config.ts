// Telegram MTProto app credentials. These are the SAME public api_id / api_hash
// the Android app ships (see app/.../telegram/TelegramConfig.kt) — they identify
// the ARVIO application to Telegram, not the user, and are already shipped in the
// public APK, so surfacing them in the browser bundle exposes nothing new.
export const TELEGRAM_API_ID = Number(process.env.NEXT_PUBLIC_TELEGRAM_API_ID || 23905496);
export const TELEGRAM_API_HASH = process.env.NEXT_PUBLIC_TELEGRAM_API_HASH || "1e48b355edfe55f9a4fbf8d3c2324628";

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

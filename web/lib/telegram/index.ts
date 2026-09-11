// Telegram source resolver — the web port of TelegramSourceResolver.kt. Turns a
// title (movie or episode) into scored, browser-streamable StreamSource entries
// backed by the user's connected Telegram account.

import type { MediaItem, StreamSource } from "../types";
import {
  TELEGRAM_ADDON_ID,
  TELEGRAM_ADDON_NAME,
  TELEGRAM_MAX_RESULTS,
  TELEGRAM_SCORE_THRESHOLD,
  TELEGRAM_SEARCH_TIMEOUT_MS,
  isTelegramConfigured,
} from "./config";
import { isConnected, searchVideoMessages, type TgVideo } from "./client";
import * as matcher from "./matcher";
import { registerStream } from "./stream";
import { getTitlesForSearch } from "../tmdb";

export {
  getAuthState,
  subscribe,
  isConnected,
  restoreSession,
  startQrAuth,
  startPhoneAuth,
  submitCode,
  submitPassword,
  disconnect,
  resetToIdle,
  type TgAuthState,
} from "./client";
export { initTelegramStreaming } from "./stream";
export { TELEGRAM_ADDON_ID, TELEGRAM_ADDON_NAME, isTelegramConfigured } from "./config";

export interface TelegramResolveOptions {
  excludedChatIds?: string[];
  language?: string;
}

const CACHE_TTL_SHORT_MS = 2 * 60 * 60 * 1000;
const CACHE_TTL_LONG_MS = 24 * 60 * 60 * 1000;

interface CacheEntry {
  results: StreamSource[];
  expiresAt: number;
}
const cache = new Map<string, CacheEntry>();

function cacheKey(item: MediaItem, season?: number, episode?: number): string {
  const id = item.imdbId || `${item.mediaType}:${item.id}`;
  return `${id}:${season ?? ""}:${episode ?? ""}`;
}

function cacheTtl(year: number | null, isMovie: boolean): number {
  if (!isMovie) return CACHE_TTL_SHORT_MS;
  const currentYear = new Date().getFullYear();
  return year != null && year < currentYear - 1 ? CACHE_TTL_LONG_MS : CACHE_TTL_SHORT_MS;
}

/**
 * Resolve Telegram sources for a movie or a specific episode. Returns [] when
 * Telegram is not connected. Never throws — failures resolve to an empty list.
 */
export async function resolveTelegramSources(
  item: MediaItem,
  season: number | undefined,
  episode: number | undefined,
  opts: TelegramResolveOptions = {}
): Promise<StreamSource[]> {
  if (!isTelegramConfigured || !isConnected()) return [];

  const key = cacheKey(item, season, episode);
  const cached = cache.get(key);
  if (cached && Date.now() < cached.expiresAt) return cached.results;

  const isMovie = item.mediaType === "movie";
  const year = item.year ? Number(item.year) || null : null;

  try {
    const results = await withTimeout(
      resolveInternal(item, season, episode, year, isMovie, opts),
      TELEGRAM_SEARCH_TIMEOUT_MS
    );
    cache.set(key, { results, expiresAt: Date.now() + cacheTtl(year, isMovie) });
    return results;
  } catch {
    return [];
  }
}

async function resolveInternal(
  item: MediaItem,
  season: number | undefined,
  episode: number | undefined,
  year: number | null,
  isMovie: boolean,
  opts: TelegramResolveOptions
): Promise<StreamSource[]> {
  const excluded = new Set(opts.excludedChatIds ?? []);
  const langCode = (opts.language ?? "en").replace("iw", "he").split("-")[0];

  // Fetch the English + localized titles from TMDB so we search files named in
  // either language — critical for non-English content (e.g. a Hebrew-dubbed
  // episode named "מפרץ ההרפתקאות ע1 פ2" won't match an English query). Mirrors
  // Android's TelegramSourceResolver.fetchTitles. item.title is the display
  // title in the user's language and is always used as a fallback.
  const title = item.title;
  const { english, localized } = await getTitlesForSearch(item, opts.language ?? "en").catch(() => ({
    english: null as string | null,
    localized: null as string | null,
  }));
  const englishTitle = english ?? title;
  const localizedTitle = localized;

  const queries =
    season != null && episode != null
      ? matcher.buildSeriesQueries(title, season, episode, localizedTitle, englishTitle, langCode)
      : matcher.buildMovieQueries(title, year, localizedTitle, englishTitle);

  const seen = new Set<string>();
  const messages: TgVideo[] = [];

  const batches = await Promise.all(
    queries.map((q) =>
      searchVideoMessages(q, TELEGRAM_MAX_RESULTS)
        .then((list) => list.filter((m) => !excluded.has(m.chatId)))
        .catch(() => [] as TgVideo[])
    )
  );
  for (const batch of batches) {
    for (const msg of batch) {
      if (seen.has(msg.key)) continue;
      seen.add(msg.key);
      messages.push(msg);
    }
  }

  const allScored = messages.map((msg) => ({
    msg,
    s: matcher.score({
      fileName: msg.fileName,
      caption: msg.caption,
      title,
      localizedTitle,
      englishTitle,
      year,
      season,
      episode,
    }),
  }));
  const scored = allScored.filter((x) => x.s >= TELEGRAM_SCORE_THRESHOLD);

  const sources = scored.map(({ msg }) => toStreamSource(msg));

  sources.sort((a, b) => {
    const heA = langCode === "he" && matcher.isHebrew(a.source) ? 1 : 0;
    const heB = langCode === "he" && matcher.isHebrew(b.source) ? 1 : 0;
    if (heA !== heB) return heB - heA;
    const qA = qualityTier(a.quality);
    const qB = qualityTier(b.quality);
    if (qA !== qB) return qB - qA;
    return (b.sizeBytes ?? 0) - (a.sizeBytes ?? 0);
  });

  return sources;
}

function toStreamSource(msg: TgVideo): StreamSource {
  const isDefault = msg.fileName === "Default_Name.mkv" || msg.fileName === "Default_Name.mp4";
  const displayName = isDefault && msg.caption.trim() ? msg.caption : msg.fileName;
  const quality = parseQuality(`${msg.fileName} ${msg.caption}`);
  const url = registerStream(msg);
  return {
    source: displayName,
    addonName: TELEGRAM_ADDON_NAME,
    addonId: TELEGRAM_ADDON_ID,
    quality,
    size: formatBytes(msg.size),
    sizeBytes: msg.size,
    url,
    infoHash: null,
    fileIdx: null,
    behaviorHints: {
      notWebReady: false,
      filename: msg.fileName,
      videoSize: msg.size,
    },
    subtitles: [],
    sources: [],
    description: msg.caption.trim() ? msg.caption : null,
  };
}

function qualityTier(quality?: string): number {
  switch (quality) {
    case "4K":
      return 6;
    case "1080p":
      return 5;
    case "720p":
      return 4;
    case "480p":
      return 3;
    case "360p":
      return 2;
    case "CAM":
    case "SCR":
      return 1;
    default:
      return 0;
  }
}

function parseQuality(raw: string): string {
  const t = raw.toLowerCase().replace(/ /g, ".");
  const has = (...xs: string[]) => xs.some((x) => t.includes(x));
  if (has("dvdscr", "screener", ".scr.")) return "SCR";
  if (has(".cam.", "camrip", "hdcam", "hdts", "telesync")) return "CAM";
  if (has("360", "36o")) return "360p";
  if (has("480", "48o")) return "480p";
  if (has("720", "72o")) return "720p";
  if (has("1080", "1o8o", "108o", "1o80", ".fhd.")) return "1080p";
  if (has("2160", "216o", ".4k.", ".uhd.", "ultrahd")) return "4K";
  return "Unknown";
}

function formatBytes(bytes: number): string {
  if (bytes <= 0) return "";
  if (bytes >= 1_000_000_000) return `${(bytes / 1_000_000_000).toFixed(2)} GB`;
  if (bytes >= 1_000_000) return `${(bytes / 1_000_000).toFixed(1)} MB`;
  return `${(bytes / 1_000).toFixed(0)} KB`;
}

function withTimeout<T>(promise: Promise<T>, ms: number): Promise<T> {
  return new Promise((resolve, reject) => {
    const timer = setTimeout(() => reject(new Error("timeout")), ms);
    promise.then(
      (v) => {
        clearTimeout(timer);
        resolve(v);
      },
      (e) => {
        clearTimeout(timer);
        reject(e);
      }
    );
  });
}

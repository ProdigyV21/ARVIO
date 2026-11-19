import type { StreamSource } from "./types";

// Per-attempt playback-failure detail. Every value is drawn from a short
// allow-list: no URL, host name, title, addon name or file name ever leaves the
// browser, and the server re-validates the same lists (_premium-funnel.js).

export const PLAYBACK_PLATFORMS = ["iphone", "ipad", "android", "mac_safari", "chromium_desktop", "firefox", "other"] as const;
export const PLAYBACK_METHODS = ["direct", "remux", "hls", "other"] as const;
export const PLAYBACK_CONTAINERS = ["mp4", "mkv", "ts", "m3u8", "unknown"] as const;
export const PLAYBACK_HOST_TYPES = ["debrid", "addon_http", "home_server", "iptv", "other"] as const;
export const PLAYBACK_OUTCOMES = ["retried", "final"] as const;

export type PlaybackPlatform = (typeof PLAYBACK_PLATFORMS)[number];
export type PlaybackMethod = (typeof PLAYBACK_METHODS)[number];
export type PlaybackContainer = (typeof PLAYBACK_CONTAINERS)[number];
export type PlaybackHostType = (typeof PLAYBACK_HOST_TYPES)[number];

export type PlaybackAttemptDetail = {
  platform: PlaybackPlatform;
  method: PlaybackMethod;
  container: PlaybackContainer;
  host_type: PlaybackHostType;
};

export function playbackPlatform(nav: { userAgent?: string; maxTouchPoints?: number } | undefined =
  typeof navigator === "undefined" ? undefined : navigator): PlaybackPlatform {
  const ua = nav?.userAgent ?? "";
  if (/iPhone|iPod/.test(ua)) return "iphone";
  if (/iPad/.test(ua) || (/Macintosh/.test(ua) && (nav?.maxTouchPoints ?? 0) > 1)) return "ipad";
  if (/Android/i.test(ua)) return "android";
  if (/Firefox\//.test(ua)) return "firefox";
  if (/Chrome\/|Chromium\/|Edg\/|OPR\//.test(ua)) return "chromium_desktop";
  if (/Macintosh/.test(ua) && /Safari\//.test(ua)) return "mac_safari";
  return "other";
}

function pathOf(url: string | null | undefined) {
  if (!url) return "";
  try {
    const parsed = new URL(url);
    const nested = parsed.searchParams.get("url");
    return decodeURIComponent(nested ? new URL(nested).pathname : parsed.pathname).toLowerCase();
  } catch {
    return url.split(/[?#]/)[0].toLowerCase();
  }
}

export function playbackMethod(stream: Pick<StreamSource, "remux" | "transcoded" | "transport" | "url">): PlaybackMethod {
  if (stream.remux) return "remux";
  if (stream.transcoded || stream.transport === "hls" || /\.m3u8$/.test(pathOf(stream.url))) return "hls";
  if (stream.transport === "dash" || stream.transport === "mpegts") return "other";
  return "direct";
}

export function playbackContainer(stream: Pick<StreamSource, "media" | "url" | "originalUrl" | "transport" | "transcoded" | "behaviorHints">): PlaybackContainer {
  if (stream.transcoded || stream.transport === "hls") return "m3u8";
  const declared = stream.media?.container?.toLowerCase() ?? "";
  const names = [declared, pathOf(stream.originalUrl), pathOf(stream.url), (stream.behaviorHints?.filename ?? "").toLowerCase()];
  for (const name of names) {
    const ext = name.match(/(?:^|\.)(mp4|m4v|mov|mkv|matroska|webm|ts|m2ts|mts|m3u8)$/)?.[1];
    if (!ext) continue;
    if (ext === "mp4" || ext === "m4v" || ext === "mov") return "mp4";
    if (ext === "mkv" || ext === "matroska") return "mkv";
    if (ext === "ts" || ext === "m2ts" || ext === "mts") return "ts";
    if (ext === "m3u8") return "m3u8";
  }
  if (stream.transport === "mpegts") return "ts";
  return "unknown";
}

export function playbackHostType(stream: Pick<StreamSource, "homeServer" | "playbackSession" | "addonId" | "addonName">, options: { liveTv?: boolean; debrid?: boolean } = {}): PlaybackHostType {
  if (stream.homeServer || stream.playbackSession) return "home_server";
  if (options.liveTv || /^(iptv|stalker|xtream)/i.test(stream.addonId ?? "") || stream.addonName === "Catch-up") return "iptv";
  if (options.debrid) return "debrid";
  if (/^telegram/i.test(stream.addonId ?? "")) return "other";
  return "addon_http";
}

export function playbackAttemptDetail(stream: StreamSource, options: { liveTv?: boolean; debrid?: boolean; nav?: { userAgent?: string; maxTouchPoints?: number } } = {}): PlaybackAttemptDetail {
  return {
    platform: playbackPlatform(options.nav),
    method: playbackMethod(stream),
    container: playbackContainer(stream),
    host_type: playbackHostType(stream, options)
  };
}

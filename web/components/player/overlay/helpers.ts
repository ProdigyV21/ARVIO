// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import type { StreamSource } from "@/lib/types";
import { proxiedUrl } from "@/lib/http";
import { currentStreamTicket } from "@/lib/streamTicket";
import { languageCode, trackLanguageName, sameLanguage } from "@/lib/languages";
import { subtitleLanguageName } from "@/lib/subtitleAi";

export type PlayerPanel = "sources" | "subtitles" | "audio" | "settings" | null;

/**
 * Seconds of no forward progress before the remux path is declared dead.
 *
 * Generous because a remux legitimately spends time downloading and
 * repackaging before the first frame — but bounded, because it used to hang
 * indefinitely with no error and no timeout.
 */
export const REMUX_STUCK_TICKS = 25;

export function youTubeId(url: string): string | null {
  const match = url.match(/(?:youtube\.com\/(?:watch\?v=|embed\/)|youtu\.be\/)([\w-]{11})/);
  return match?.[1] ?? null;
}

export function fmt(seconds: number): string {
  if (!Number.isFinite(seconds) || seconds < 0) return "0:00";
  const total = Math.floor(seconds);
  const h = Math.floor(total / 3600);
  const m = Math.floor((total % 3600) / 60);
  const s = total % 60;
  return h > 0 ? `${h}:${String(m).padStart(2, "0")}:${String(s).padStart(2, "0")}` : `${m}:${String(s).padStart(2, "0")}`;
}

export function isSameStream(a: StreamSource, b: StreamSource) {
  return (
    a.url === b.url &&
    a.infoHash === b.infoHash &&
    a.fileIdx === b.fileIdx &&
    a.addonId === b.addonId &&
    a.source === b.source
  );
}

export function mergeSubtitleTracks(primary: StreamSource, enriched?: StreamSource) {
  if (!enriched?.subtitles?.length) return primary;
  const existing = primary.subtitles ?? [];
  const seen = new Set(existing.map((subtitle) => subtitle.url));
  const subtitles = [...existing, ...enriched.subtitles.filter((subtitle) => !seen.has(subtitle.url))];
  return subtitles.length === existing.length ? primary : { ...primary, subtitles };
}

export function streamMeta(stream: StreamSource) {
  return [stream.addonName, stream.quality, stream.size].filter(Boolean).join(" - ");
}

export function isLikelyHlsUrl(url?: string | null) {
  if (!url) return false;
  return /\.m3u8(?:[?#]|$)/i.test(url) || url.toLowerCase().includes("mpegurl");
}

// Xtream panels serve the same live stream as HLS at …/id.m3u8. Playlists with
// output=ts hand out raw-TS URLs the browser often can't use directly, so the
// ladder also tries the HLS twin of an Xtream-style live URL.
export function xtreamHlsVariant(url?: string | null) {
  if (!url) return null;
  const match = url.match(/^(https?:\/\/[^?#]*\/[^/?#]+\/[^/?#]+\/\d+)\.ts(?=$|[?#])/i);
  return match ? `${match[1]}.m3u8` : null;
}

export function liveTvProxyHeaders() {
  return {
    Accept: "*/*",
    "User-Agent": "VLC/3.0.20 LibVLC/3.0.20",
    "Icy-MetaData": "1"
  };
}

export function needsBrowserHeaderRelay(headers?: Record<string, string>) {
  return Object.keys(headers ?? {}).some(name => /^(referer|origin|user-agent|cookie|host)$/i.test(name));
}

export function directManifestUrl(url: string, headers?: Record<string, string>) {
  const target = new URL(proxiedUrl(url, { ...liveTvProxyHeaders(), ...headers }));
  target.searchParams.set("rewrite", "direct");
  return target.toString();
}

export function workerManifestUrl(url: string, headers?: Record<string, string>) {
  // Manifest via the app backend (reaches hosts that block Cloudflare),
  // segments via the configured resolver worker with its CORS/header handling.
  const target = new URL(proxiedUrl(url, { ...liveTvProxyHeaders(), ...headers }));
  target.searchParams.set("rewrite", "worker");
  const ticket = currentStreamTicket();
  if (ticket) target.searchParams.set("t", ticket);
  return target.toString();
}

export function qualityBadges(stream: StreamSource) {
  const text = `${stream.quality ?? ""} ${stream.description ?? ""}`.toUpperCase();
  return [
    stream.quality || "HD",
    /\bDV\b/.test(text) || text.includes("DOLBY VISION") ? "DV" : "",
    /\bHDR/.test(text) ? "HDR" : "",
    text.includes("ATMOS") ? "ATMOS" : ""
  ].filter(Boolean);
}

// The preference is a code ("en") or the Android app's display name
// ("English", "Portuguese (Brazil)") synced from ARVIO Cloud.
export function defaultSubtitleIndex(stream: StreamSource, language: string) {
  const desired = languageCode(language);
  if (!desired) return -1;
  const name = trackLanguageName(language).toLowerCase();
  const subtitles = stream.subtitles ?? [];
  const exact = subtitles.findIndex((subtitle) => languageCode(subtitle.lang) === desired);
  if (exact >= 0) return exact;
  return subtitles.findIndex((subtitle) => {
    const label = subtitle.label?.toLowerCase() ?? "";
    return sameLanguage(subtitle.lang, desired) || (name.length > 2 && label.includes(name)) || languageCode(subtitle.label) === desired;
  });
}

// Subtitle list ordered like the app: the user's preferred language first,
// English next, then everything else alphabetically by language name. Each
// entry keeps its original index in the source's subtitle list.
export function orderedSubtitles(stream: StreamSource, preferred: string) {
  const pref = languageCode(preferred);
  return (stream.subtitles ?? [])
    .map((subtitle, index) => ({
      subtitle,
      index,
      langName: subtitleLanguageName(subtitle.lang || subtitle.label || "") || (subtitle.label ?? "Subtitle")
    }))
    .sort((a, b) => {
      const rank = (entry: { subtitle: { lang?: string | null } }) => {
        const lang = languageCode(entry.subtitle.lang);
        if (pref && sameLanguage(lang, pref)) return 0;
        if (lang === "en") return 1;
        return 2;
      };
      return rank(a) - rank(b) || a.langName.localeCompare(b.langName);
    });
}

/** Skip interval of the on-screen buttons, remote arrows, double-tap and lock screen. */
export const SKIP_SECONDS = 10;

export function fetchJson(url: string, signal?: AbortSignal) {
  return fetch(url, { signal, credentials: "omit" }).then((response) => response.ok ? response.json() : Promise.reject(new Error(String(response.status))));
}

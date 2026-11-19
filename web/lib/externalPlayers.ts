import { resolverDownloadUrl, resolverLaunchUrl, resolverSubtitleUrl } from "./resolver";
import type { StreamSource, SubtitleTrack } from "./types";

export type ExternalPlayer = "vlc" | "infuse";

function cleanFilename(value: string) {
  return value
    .replace(/[\\/:*?"<>|]+/g, " ")
    .replace(/\s+/g, " ")
    .trim()
    .slice(0, 140) || "ARVIO stream";
}

function subtitleUrl(subtitle?: SubtitleTrack) {
  if (!subtitle?.url) return "";
  return resolverSubtitleUrl(subtitle.url);
}

// External players (VLC/Infuse) accept a single sidecar subtitle URL. Pick the
// one matching the user's preferred language, else English, else the first —
// so the addon subtitle (e.g. OpenSubtitles) is loaded automatically.
function pickSubtitle(subtitles: SubtitleTrack[] | undefined, preferredLang: string): SubtitleTrack | undefined {
  if (!subtitles?.length) return undefined;
  const pref = preferredLang.trim().toLowerCase();
  const byLang = (prefix: string) => subtitles.find((s) => (s.lang ?? "").toLowerCase().startsWith(prefix) && !s.isForced && !s.isEmbedded);
  if (pref && pref !== "off") {
    const match = byLang(pref);
    if (match) return match;
  }
  return byLang("en") ?? subtitles.find((s) => !s.isEmbedded) ?? subtitles[0];
}

function isTorrentioResolve(url?: string | null) {
  return Boolean(url && /torrentio\.strem\.fun\/(?:resolve\/)?(?:torbox|realdebrid)/i.test(url));
}

export function externalPlayerUrl(player: ExternalPlayer, stream: StreamSource, title: string, preferredSubtitleLang = "", mode: "play" | "download" = "play") {
  // External players decode everything natively. Prefer the ORIGINAL file over
  // a debrid-transcoded HLS variant — UNLESS the original is a torrentio
  // /resolve/ redirect, in which case the resolved CDN url in `stream.url` is
  // what makes VLC start instantly (following the redirect chain costs ~10-15s).
  const targetUrl = isTorrentioResolve(stream.originalUrl)
    ? stream.url
    : (stream.originalUrl ?? stream.url);
  if (!targetUrl) return "";
  // Give the external player the real container in the filename so it probes
  // ALL streams (a .mp4 hint on an actual MKV can make VLC miss extra audio
  // tracks). Detect from the source text / URL; default to mkv (most debrid
  // remuxes) rather than mp4.
  const containerText = `${targetUrl} ${stream.originalUrl ?? ""} ${stream.source ?? ""} ${stream.description ?? ""}`.toLowerCase();
  const ext = /\.m3u8|mpegurl/.test(containerText)
    ? "m3u8"
    : /\.mp4(?:[?#/]|$)/.test(containerText)
      ? "mp4"
      : /\.(mkv|matroska)|remux|\bweb-?dl\b|bluray|\bhevc\b|x265/.test(containerText)
        ? "mkv"
        : "mp4";
  const filename = `${cleanFilename(title)}.${ext}`;
  // Hand VLC/Infuse the addon subtitle (e.g. OpenSubtitles) in the user's
  // language — their URL schemes accept exactly one sidecar sub.
  const sub = subtitleUrl(pickSubtitle(stream.subtitles, preferredSubtitleLang));
  const params = new URLSearchParams();
  params.set("url", targetUrl);
  params.set("filename", filename);
  if (sub) params.set("sub", sub);

  if (player === "infuse") {
    params.set("download", "0");
    return `infuse://x-callback-url/play?${params.toString()}`;
  }

  // Android: vlc-x-callback:// is iOS-ONLY — Android VLC doesn't register it, so
  // it silently does nothing (the Samsung/Chrome bug). Android launches players
  // via an intent:// URL instead. Target VLC's package, with a browser fallback.
  if (player === "vlc" && isAndroid()) {
    return androidIntentUrl(targetUrl, filename, sub, "org.videolan.vlc");
  }

  // Desktop with the one-time vlc:// handler installed: our handler validates
  // the nested HTTP(S) URL and passes it to VLC as one media argument.
  if (player === "vlc" && isDesktop()) {
    return `vlc://${targetUrl}`;
  }

  // VLC's "download" action saves the file to VLC's own storage (offline
  // playback) instead of just streaming it — the reliable way to get a large
  // file onto an iPad, where a browser tab can't (WebKit memory limit). "stream"
  // just plays it.
  return `vlc-x-callback://x-callback-url/${mode === "download" ? "download" : "stream"}?${params.toString()}`;
}

// Android intent:// URL. When `pkg` is set it targets that app (e.g. VLC);
// when omitted, Android shows its own "Open with" chooser listing every
// installed video player (MX Player, Just Player, VLC, …). The video MIME type
// makes Android offer players rather than browsers.
function androidIntentUrl(url: string, filename: string, sub: string, pkg?: string) {
  const scheme = url.startsWith("https") ? "https" : "http";
  const bare = url.replace(/^https?:\/\//, "");
  const mime = /\.m3u8|mpegurl/i.test(url) ? "application/x-mpegurl" : "video/*";
  const parts = [
    `intent://${bare}#Intent`,
    `scheme=${scheme}`,
    "action=android.intent.action.VIEW",
    `type=${mime}`
  ];
  if (pkg) parts.push(`package=${pkg}`);
  if (sub) parts.push(`S.subtitles_location=${encodeURIComponent(sub)}`);
  parts.push(`S.title=${encodeURIComponent(filename)}`);
  // If the targeted app isn't installed, fall back to opening the raw URL.
  parts.push(`S.browser_fallback_url=${encodeURIComponent(url)}`);
  parts.push("end");
  return parts.join(";");
}

// iOS home-screen webapps (standalone PWAs) silently DROP custom-scheme
// navigations — no error, no app launch. Detect that context and route the
// scheme through the resolver's /launch interstitial instead: the PWA opens
// https links in its Safari sheet, which CAN launch app schemes (native
// "Open in VLC?" prompt). Regular browser tabs use a programmatic anchor
// click, the most reliable scheme launch on iOS Safari/Chrome.
// This MUST run synchronously inside the click handler.
function isIosStandalone() {
  return typeof navigator !== "undefined" && (navigator as { standalone?: boolean }).standalone === true;
}

function launchScheme(href: string) {
  if (typeof document === "undefined") return;
  if (isIosStandalone()) {
    const interstitial = resolverLaunchUrl(href);
    if (interstitial) {
      window.location.href = interstitial;
      return;
    }
  }
  // Anchor click — most reliable in real browser tabs for custom schemes.
  try {
    const a = document.createElement("a");
    a.href = href;
    a.rel = "noopener";
    // Keep it out of layout but still clickable.
    a.style.position = "fixed";
    a.style.left = "-9999px";
    document.body.appendChild(a);
    a.click();
    // Remove after the navigation has had a chance to fire.
    setTimeout(() => a.remove(), 1500);
  } catch {
    // Direct assignment fallback (desktop / Android WebView).
    try {
      window.location.href = href;
    } catch {
      /* ignore */
    }
  }
}

export function isAppleMobile() {
  if (typeof navigator === "undefined") return false;
  const ua = navigator.userAgent;
  // iPadOS 13+ masquerades as Macintosh but has touch points.
  return /iPad|iPhone|iPod/.test(ua) || (/Macintosh/.test(ua) && navigator.maxTouchPoints > 1);
}

export function isAndroid() {
  return typeof navigator !== "undefined" && /Android/i.test(navigator.userAgent);
}

export function isWindows() {
  if (typeof navigator === "undefined") return false;
  const ua = navigator.userAgent;
  // Exclude Windows Phone (long dead, but the token exists) and Android.
  return /Windows NT/i.test(ua) && !/Android/i.test(ua);
}

// macOS desktop (NOT an iPad masquerading as Macintosh — those have touch
// points and are handled by isAppleMobile). On macOS, VLC registers the vlc://
// scheme itself at install time, so the direct scheme launch works with no
// setup — unlike Windows, which needs the vlc-setup.bat handler.
export function isMac() {
  if (typeof navigator === "undefined") return false;
  const ua = navigator.userAgent;
  return /Macintosh|Mac OS X/i.test(ua) && !(navigator.maxTouchPoints > 1);
}

export function isLinux() {
  if (typeof navigator === "undefined") return false;
  const ua = navigator.userAgent;
  return /Linux/i.test(ua) && !/Android/i.test(ua);
}

export function isDesktop() {
  return !isAppleMobile() && !isAndroid();
}

// One-time setup scripts that register the vlc:// handler so desktop "Open in VLC"
// launches VLC directly instead of downloading a .m3u. Hosted by ARVIO.
// Bump this query and readiness key whenever the installed protocol handler
// changes. Browsers must not keep using or re-download an obsolete handler.
const VLC_SETUP_VERSION = "2";
export const VLC_SETUP_URL = `/vlc-setup.bat?v=${VLC_SETUP_VERSION}`;
export const VLC_SETUP_SH_URL = `/vlc-setup.sh?v=${VLC_SETUP_VERSION}`;

// Whether the user has run the one-time desktop vlc:// setup. We can't detect
// protocol registration from the browser, so we remember that they installed
// it and use the direct vlc:// launch; otherwise we fall back to the .m3u.
const VLC_PROTOCOL_KEY = `arvio.web.vlcProtocolReady.v${VLC_SETUP_VERSION}`;
const LEGACY_VLC_PROTOCOL_KEY = "arvio.web.vlcProtocolReady";

export function vlcProtocolReady(): boolean {
  if (typeof localStorage === "undefined") return false;
  const ready = localStorage.getItem(VLC_PROTOCOL_KEY) === "1";
  if (!ready) localStorage.removeItem(LEGACY_VLC_PROTOCOL_KEY);
  return ready;
}

export function setVlcProtocolReady(ready: boolean) {
  if (typeof localStorage === "undefined") return;
  if (ready) localStorage.setItem(VLC_PROTOCOL_KEY, "1");
  else localStorage.removeItem(VLC_PROTOCOL_KEY);
  localStorage.removeItem(LEGACY_VLC_PROTOCOL_KEY);
}

// How VLC is launched on this platform:
// - iOS/Android: a custom scheme (vlc-x-callback:// / intent://).
// - Desktop WITH the one-time vlc:// handler installed: direct scheme launch.
// - Desktop WITHOUT it: a tiny .m3u download (VLC owns the .m3u association),
//   which needs no setup but takes an extra click.
export function externalLaunchMode(player: ExternalPlayer): "scheme" | "playlist" {
  if (player === "vlc" && isDesktop()) {
    // macOS: VLC self-registers vlc://, so the direct scheme launch always
    // works — no installer, no .m3u fallback. Windows needs the one-time
    // vlc-setup.bat handler, so fall back to the .m3u until it's installed.
    if (isMac()) return "scheme";
    return vlcProtocolReady() ? "scheme" : "playlist";
  }
  return "scheme";
}

function downloadVlcPlaylist(stream: StreamSource, title: string, preferredSubtitleLang: string) {
  const targetUrl = isTorrentioResolve(stream.originalUrl) ? stream.url : (stream.originalUrl ?? stream.url);
  if (!targetUrl || typeof document === "undefined") return false;
  const sub = subtitleUrl(pickSubtitle(stream.subtitles, preferredSubtitleLang));
  const lines = ["#EXTM3U", `#EXTINF:-1,${cleanFilename(title)}`];
  // input-slave attaches the addon subtitle as a second input — VLC loads it
  // automatically alongside the stream.
  if (sub) lines.push(`#EXTVLCOPT:input-slave=${sub}`);
  lines.push(targetUrl);
  const blob = new Blob([`${lines.join("\n")}\n`], { type: "audio/x-mpegurl" });
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url;
  a.download = `${cleanFilename(title)}.m3u`;
  document.body.appendChild(a);
  a.click();
  setTimeout(() => {
    a.remove();
    URL.revokeObjectURL(url);
  }, 4000);
  return true;
}

export function openExternalPlayer(player: ExternalPlayer, stream: StreamSource, title: string, preferredSubtitleLang = "") {
  if (externalLaunchMode(player) === "playlist") {
    return downloadVlcPlaylist(stream, title, preferredSubtitleLang);
  }
  const href = externalPlayerUrl(player, stream, title, preferredSubtitleLang);
  if (!href) return false;
  launchScheme(href);
  return true;
}

// Save a file into VLC's own storage for offline playback (iOS: the reliable
// way to get a large file onto the device — a browser tab can't). Uses VLC's
// `download` action; always the scheme launch (never the desktop .m3u path).
export function downloadToVlc(stream: StreamSource, title: string, preferredSubtitleLang = "") {
  const href = externalPlayerUrl("vlc", stream, title, preferredSubtitleLang, "download");
  if (!href) return false;
  launchScheme(href);
  return true;
}

// A single "Open in external player" button, shown on every device (replaces
// the iOS-only Infuse button). It does the best each platform allows, because a
// generic "pick a player" chooser only exists on Android:
//   - Android: intent chooser listing every installed player (VLC, MX Player, …)
//   - iOS: VLC (the only generic scheme handoff on iOS)
//   - Desktop: VLC via vlc:// (if the one-time handler is set up) else a .m3u
// Always available — there's a working route on every platform.
export function canOpenInAnyPlayer() {
  return true;
}

export function openInAnyPlayer(stream: StreamSource, title: string, preferredSubtitleLang = "") {
  const targetUrl = isTorrentioResolve(stream.originalUrl) ? stream.url : (stream.originalUrl ?? stream.url);
  if (!targetUrl) return false;

  if (isAndroid()) {
    const ext = /\.m3u8|mpegurl/i.test(targetUrl) ? "m3u8" : /\.mp4(?:[?#/]|$)/i.test(targetUrl) ? "mp4" : "mkv";
    const filename = `${cleanFilename(title)}.${ext}`;
    const sub = subtitleUrl(pickSubtitle(stream.subtitles, preferredSubtitleLang));
    // No package → Android's own "Open with" chooser across all video apps.
    launchScheme(androidIntentUrl(targetUrl, filename, sub));
    return true;
  }

  // iOS + desktop: VLC is the one route that works generically. This reuses the
  // platform-correct VLC path (iOS scheme, desktop vlc:// or .m3u fallback).
  return openExternalPlayer("vlc", stream, title, preferredSubtitleLang);
}

export type ExternalPlayerOption = { id: ExternalPlayer | "chooser"; label: string };

/**
 * The external players worth offering on this device, best first — one button
 * each, never two that launch the same app:
 * - iPhone / iPad / Mac: Infuse, then VLC (both have URL schemes);
 * - Android: the system "Open with" chooser (VLC, MX Player, Just Player…);
 * - Windows / Linux / other desktops: VLC.
 */
export function externalPlayerOptions(platform: "apple" | "android" | "desktop" = isAndroid() ? "android" : (isAppleMobile() || isMac()) ? "apple" : "desktop"): ExternalPlayerOption[] {
  if (platform === "android") return [{ id: "chooser", label: "Open in player" }];
  if (platform === "apple") return [{ id: "infuse", label: "Infuse" }, { id: "vlc", label: "VLC" }];
  return [{ id: "vlc", label: "VLC" }];
}

// Whether "Open in external player" resolves to a specific-app launch (so the UI
// can hint what happens). Only Android shows a real chooser.
export function externalPlayerIsChooser() {
  return isAndroid();
}

export async function copyStreamUrl(stream: StreamSource) {
  const targetUrl = stream.originalUrl ?? stream.url;
  if (!targetUrl || typeof navigator === "undefined" || !navigator.clipboard) return false;
  await navigator.clipboard.writeText(targetUrl);
  return true;
}

// Download via the resolver worker, which adds Content-Disposition: attachment.
// CDNs serve video files inline, so a plain link makes iOS Safari/Chrome open a
// preview page instead of downloading — the attachment header hands the file to
// the browser's own download manager (visible progress, Files app on iOS).
export function downloadStreamUrl(stream: StreamSource, title: string) {
  const targetUrl = isTorrentioResolve(stream.originalUrl) ? stream.url : (stream.originalUrl ?? stream.url);
  if (!targetUrl) return null;
  const containerText = `${targetUrl} ${stream.originalUrl ?? ""} ${stream.source ?? ""} ${stream.description ?? ""}`.toLowerCase();
  const ext = /\.mp4(?:[?#/]|$)/.test(containerText) ? "mp4" : "mkv";
  const filename = `${cleanFilename(title)}.${ext}`;
  return resolverDownloadUrl(targetUrl, filename) ?? targetUrl;
}

export function downloadStream(stream: StreamSource, title: string) {
  const href = downloadStreamUrl(stream, title);
  if (!href) return false;
  return triggerDownload(href, cleanFilename(title));
}

// Start a download via a programmatic <a download> click — the reliable path in
// real browser tabs AND installed PWAs. `window.location.href = href` navigated
// the whole tab: a non-200 (rate-limited/expired token) then left a blank page
// with no feedback, and even a 200 attachment could replace the app in some
// engines. The anchor keeps the app in place and lets the browser's own
// download manager own the transfer (the download proxy sets Content-
// Disposition: attachment, so this never opens an inline preview).
export function triggerDownload(href: string, filename: string) {
  if (typeof document === "undefined") return false;
  try {
    const a = document.createElement("a");
    a.href = href;
    a.download = filename || "arvio-download";
    a.rel = "noopener";
    a.style.position = "fixed";
    a.style.left = "-9999px";
    document.body.appendChild(a);
    a.click();
    setTimeout(() => a.remove(), 4000);
    return true;
  } catch {
    try {
      window.location.href = href;
      return true;
    } catch {
      return false;
    }
  }
}

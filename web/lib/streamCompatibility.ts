import { getMediaCapabilities } from "./capabilities";
import { parseDebridStream } from "./debrid";
import type { StreamSource } from "./types";

type CompatStream = Pick<StreamSource, "url" | "originalUrl" | "source" | "description" | "behaviorHints" | "media" | "transport" | "homeServer" | "transcoded">;
export type PlaybackMode = "direct" | "remux" | "transcode" | "external" | "locked";
export type StreamPlayability = { mode: PlaybackMode; reason: string };
export type PlaybackPlan = { route: "here" | "vlc" | "dead"; method: "direct" | "remux" | "transcode"; label: string; detail: string };

// Tab-local, bounded evidence from a selected file. Never sync signed URLs or
// device-specific decoder failures to an account or persist them across browsers.
const failures = new Map<string, { result: StreamPlayability; expires: number }>();
const listeners = new Set<() => void>();
let revision = 0;
export const playbackCompatibilityRevision = () => revision;
export function subscribePlaybackCompatibility(listener: () => void) {
  listeners.add(listener);
  return () => { listeners.delete(listener); };
}
function compatibilityKey(stream: CompatStream) { return stream.originalUrl ?? stream.url; }
export function recordBrowserPlaybackFailure(stream: CompatStream, reason: string, conversionUnavailable = false) {
  const key = compatibilityKey(stream);
  if (!key) return;
  failures.delete(key);
  failures.set(key, { result: { mode: !conversionUnavailable && canProviderTranscode(stream) ? "transcode" : "external", reason }, expires: Date.now() + 10 * 60_000 });
  while (failures.size > 100) failures.delete(failures.keys().next().value!);
  revision++;
  listeners.forEach((listener) => listener());
}
export function clearBrowserPlaybackFailure(stream: CompatStream) {
  const key = compatibilityKey(stream);
  if (key && failures.delete(key)) { revision++; listeners.forEach((listener) => listener()); }
}
export function hasDolbyVision(stream: CompatStream) {
  return /dolby[. _-]?vision|\bdovi\b|\bdv\b|\bdvhe\b|\bdvh1\b/i.test(`${stream.media?.hdr ?? ""} ${stream.media?.videoCodec ?? ""} ${text(stream)}`);
}
function canCheckHdrBase(stream: CompatStream) {
  const caps = getMediaCapabilities();
  const profile = `${stream.media?.videoCodec ?? ""} ${stream.media?.hdr ?? ""}`.match(/(?:dvhe|dvh1)\.(\d+)/i)?.[1];
  return caps.mse && caps.hevc10 && streamTransport(stream) === "file"
    && (!profile || Number(profile) === 8)
    && !/^(zip|rar|7z|iso|exe|torrent|avi|wmv|flv|webm)$/.test(streamContainer(stream));
}

export function mediaPath(url: string): string {
  try {
    const parsed = new URL(url);
    const nested = parsed.searchParams.get("url");
    return decodeURIComponent(nested ? new URL(nested).pathname : parsed.pathname).toLowerCase();
  } catch { return url.split(/[?#]/)[0].toLowerCase(); }
}
function text(stream: CompatStream) {
  return `${stream.behaviorHints?.filename ?? ""} ${stream.source ?? ""} ${stream.description ?? ""}`.toLowerCase();
}
export function streamContainer(stream: CompatStream): string {
  if (stream.media?.container) return stream.media.container.toLowerCase();
  return mediaPath(stream.url ?? "").match(/\.([a-z0-9]+)$/)?.[1]
    ?? text(stream).match(/\b(mkv|mp4|webm|matroska|avi|wmv|flv)\b/)?.[1] ?? "";
}
export function streamTransport(stream: CompatStream): NonNullable<StreamSource["transport"]> {
  if (stream.transport) return stream.transport;
  const path = mediaPath(stream.url ?? "");
  if (/\.m3u8?$/.test(path)) return "hls";
  if (/\.mpd$/.test(path)) return "dash";
  if (/\.(ts|m2ts)$/.test(path) || /\/live\/[^/]+\/[^/]+\/\d+$/.test(path)) return "mpegts";
  return "file";
}
function videoReason(stream: CompatStream): string | null {
  const caps = getMediaCapabilities();
  const video = stream.media?.videoCodec?.toLowerCase() || text(stream);
  // Knowing the codec (HEVC) does not tell us whether it carries Dolby Vision.
  if (hasDolbyVision(stream) && !caps.dolbyVision && !canCheckHdrBase(stream)) return "Dolby Vision needs compatible video decoding. Use a non-DV version or a compatible external player.";
  if (/hi10p|high[. _-]?10|avc1\.6e/i.test(`${video} ${text(stream)}`)) return "10-bit H.264 (Hi10P) requires a compatible external player or server conversion";
  if (/\b(vc-?1|wmv3|mpeg-?2|divx|xvid)\b/.test(video) || /\bmpeg-?4\b(?![. _-]*(?:avc|h\.?264))/.test(video)) return "This video codec requires server conversion or a compatible external player";
  if (/\bav1\b|av01/.test(video)) return caps.av1 ? null : "This device has no AV1 decoder";
  if (/x265|h\.?265|hevc|hvc1|hev1|dvhe|dvh1/.test(video) && !caps.hevc && !caps.hevc10) return "This device has no HEVC decoder";
  if (/vp9|vp09/.test(video) && !caps.vp9) return "This device has no VP9 decoder";
  // Resolution is not a codec. Unknown metadata is checked on selection.
  return null;
}
function audioReason(stream: CompatStream): string | null {
  const caps = getMediaCapabilities();
  const audio = stream.media?.audioCodec?.toLowerCase() || text(stream);
  if (/true[ -]?hd|\bmlp\b/.test(audio)) return "TrueHD needs a compatible audio track or server conversion";
  if (/\bdts|\bdca\b/.test(audio)) return "DTS needs audio conversion";
  if (/e-?ac-?3|ec-3|ddp|dd\+|digital plus/.test(audio)) return caps.eac3 ? null : "Dolby Digital Plus needs audio conversion";
  if (/\bac-?3\b|dd5\.1/.test(audio)) return caps.ac3 ? null : "Dolby Digital needs audio conversion";
  return null;
}
export function canProviderTranscode(stream: CompatStream): boolean {
  const provider = parseDebridStream(stream.originalUrl ?? stream.url)?.provider;
  return !!stream.homeServer || provider === "torbox" || provider === "realdebrid";
}
export function videoDecodableForDevice(stream: CompatStream) { return !videoReason(stream); }
export function audioDecodableForDevice(stream: CompatStream) {
  return !audioReason(stream) || (getMediaCapabilities().mse && !/true[ -]?hd|\bmlp\b/i.test(stream.media?.audioCodec || text(stream)));
}
export function canDirectPlayMkvStream(stream: CompatStream): boolean {
  if (typeof navigator === "undefined" || /iPhone|iPad|iPod|CriOS|FxiOS/.test(navigator.userAgent)) return false;
  const version = navigator.userAgent.match(/(?:Chrome|Chromium|Edg)\/(\d+)/);
  return !!version && Number(version[1]) >= 145 && /^(mkv|matroska)$/.test(streamContainer(stream)) && !hasDolbyVision(stream) && !videoReason(stream) && !audioReason(stream);
}
export function canTryRemux(stream: CompatStream): boolean {
  return /^https?:/i.test(stream.url ?? "") && getMediaCapabilities().mse
    && streamTransport(stream) === "file" && !videoReason(stream)
    && !/^(zip|rar|7z|iso|exe|torrent|avi|wmv|flv)$/.test(streamContainer(stream));
}
export function streamPlayability(stream: CompatStream): StreamPlayability {
  if (!stream.url || stream.behaviorHints?.notWebReady) return { mode: "locked", reason: "Needs a resolved playback URL" };
  const container = streamContainer(stream);
  if (/^(zip|rar|7z|tar|gz|iso|exe|nfo|torrent)$/.test(container)) return { mode: "locked", reason: "Not a playable media file" };
  const key = compatibilityKey(stream);
  const failure = key ? failures.get(key) : undefined;
  if (failure && failure.expires > Date.now() && (!stream.transcoded || failure.result.mode === "external")) return failure.result;
  if (key && failure && failure.expires <= Date.now()) failures.delete(key);
  if (stream.transcoded && streamTransport(stream) === "hls") {
    const caps = getMediaCapabilities();
    return caps.mse || caps.nativeHls ? { mode: "direct", reason: "" }
      : { mode: "external", reason: "This browser has no HLS playback support" };
  }
  const video = videoReason(stream);
  const audio = audioReason(stream);
  if (video) return { mode: canProviderTranscode(stream) ? "transcode" : "external", reason: video };
  if (/^(avi|wmv|flv)$/.test(container)) return { mode: canProviderTranscode(stream) ? "transcode" : "external", reason: "Unsupported container" };
  if (streamTransport(stream) !== "file") return { mode: audio ? canProviderTranscode(stream) ? "transcode" : "external" : "direct", reason: audio ?? "" };
  if (hasDolbyVision(stream) && !getMediaCapabilities().dolbyVision && canCheckHdrBase(stream)) {
    return { mode: "remux", reason: "Selected-file check required: only a verified HDR10-compatible Dolby Vision base layer can be played." };
  }
  if (audio || /^(mkv|matroska)$/.test(container)) {
    if (canDirectPlayMkvStream(stream)) return { mode: "direct", reason: "" };
    if (canTryRemux(stream)) return { mode: "remux", reason: audio || "Repackage for browser playback" };
    return { mode: canProviderTranscode(stream) ? "transcode" : "external", reason: audio || "This browser cannot repackage this file" };
  }
  return { mode: "direct", reason: "" };
}
export function playbackPlan(stream: CompatStream): PlaybackPlan {
  const { mode, reason } = streamPlayability(stream);
  return {
    route: mode === "locked" ? "dead" : mode === "external" ? "vlc" : "here",
    method: mode === "transcode" ? "transcode" : mode === "remux" ? "remux" : "direct",
    label: mode === "locked" ? "Not playable" : mode === "external" ? "External player" : "Play in browser",
    detail: mode === "transcode" ? `${reason ? `${reason}. ` : ""}Server conversion requires provider support and permission` : reason
  };
}
export function isBrowserPlayableStream(stream: CompatStream) { return playbackPlan(stream).route === "here"; }
export function isDirectPlayableStream(stream: CompatStream) { return streamPlayability(stream).mode === "direct"; }
export function isIosPlayableStream(stream: CompatStream) { return isDirectPlayableStream(stream); }
export function playbackWarning(stream: CompatStream) { return playbackPlan(stream).detail; }

import { isUncachedDebridStream, parseDebridStream } from "./debrid";
import { exceedsConnection } from "./connectionBudget";
import { audioDecodableForDevice, isBrowserPlayableStream, isDirectPlayableStream, playbackPlan, videoDecodableForDevice, type PlaybackPlan } from "./streamCompatibility";
import type { StreamSource } from "./types";

// Shared source ranking — the details source picker, the in-player source
// panel, and the auto-hop fallback must all agree on what "the next best
// source" means.

/**
 * Who is going to play this.
 *
 * The two audiences want opposite orderings of the same list. A 4K Dolby
 * Vision remux with TrueHD is the WORST thing to hand a browser and the BEST
 * thing to hand VLC, so a single ranking cannot serve both: ranking used to
 * ignore the user's player entirely, which is why browser users had unplayable
 * sources at the top of every list.
 *
 * "external" keeps the quality-first order that was always right for VLC and
 * Infuse; "browser" additionally sinks anything this browser cannot decode.
 */
export type PlaybackTarget = "browser" | "external";

/**
 * What kind of device the browser runs on.
 *
 * "mobile" = phones and tablets (iPhone, iPad, Android touch devices). Their
 * browsers play a 1080p MP4 instantly but choke on a 60 GB 4K remux: the file
 * either needs the in-browser remux path (heavy on a phone CPU and battery) or
 * stalls over cellular/Wi-Fi long before the first frame. Desktop keeps the
 * quality-first ordering it always had.
 */
export type DeviceProfile = "mobile" | "desktop";

let detectedProfile: DeviceProfile | null = null;

export function detectDeviceProfile(nav: { userAgent?: string; maxTouchPoints?: number } | undefined =
  typeof navigator === "undefined" ? undefined : navigator): DeviceProfile {
  if (!nav) return "desktop";
  const ua = nav.userAgent ?? "";
  if (/iPhone|iPad|iPod|Android|Mobile/i.test(ua)) return "mobile";
  // iPadOS 13+ reports a desktop Mac user agent but has touch points.
  if (/Macintosh/.test(ua) && (nav.maxTouchPoints ?? 0) > 1) return "mobile";
  return "desktop";
}

function currentDeviceProfile(): DeviceProfile {
  detectedProfile ??= detectDeviceProfile();
  return detectedProfile;
}

const GB = 1_000_000_000;

export function sourcePickerScore(stream: StreamSource, target: PlaybackTarget = "browser", device: DeviceProfile = currentDeviceProfile()) {
  const text = `${stream.quality ?? ""} ${stream.source ?? ""} ${stream.description ?? ""} ${stream.size ?? ""}`.toLowerCase();
  // Only in-browser playback on a phone/tablet changes the order; an external
  // player (VLC/Infuse) on the same phone still wants the best release.
  const mobile = target === "browser" && device === "mobile";
  let score = 0;
  score += mobile ? mobileQualityScore(text) : qualityScore(text);
  score += mobile ? mobileSizeScore(stream, text) : sizeScore(stream, text);
  // A remux is the largest possible file for its resolution — the best pick
  // for a desktop, the worst for a phone browser.
  if (text.includes("remux")) score += mobile ? -150 : 180;
  if (text.includes("bluray") || text.includes("blu-ray")) score += 70;
  if (text.includes("web-dl") || text.includes("webrip")) score += 35;
  if (text.includes("hdr10+") || text.includes("dolby vision") || /\bdv\b/i.test(text)) score += 28;
  else if (text.includes("hdr")) score += 18;
  // DV-only (no HDR fallback) = Dolby Vision profile 5 → black picture in
  // browsers. Sink these well below normal HDR/SDR sources — but only when the
  // browser is doing the decoding; VLC renders Dolby Vision correctly.
  if (target === "browser"
    && (text.includes("dolby vision") || /\bdv\b/i.test(text))
    && !text.includes("hdr")
    && !text.includes("remux")) score -= 300;
  if (text.includes("atmos") || text.includes("truehd") || text.includes("dts-hd")) score += 14;
  if (stream.behaviorHints?.cached) score += 45;
  if (stream.url) score += 12;
  // Debrid CDNs (TorBox/RD) reliably allow browser range requests + CORS;
  // free file-hosts (hubcloud/R2/pixeldrain mirrors) frequently don't and die
  // at play time — prefer debrid at equal quality.
  if (parseDebridStream(stream.url)) score += 55;
  // On a phone, "plays natively without conversion" is worth more than any
  // resolution step: the remux/transcode fallbacks are slow and battery-heavy.
  if (isDirectPlayableStream(stream)) score += mobile ? 260 : 3;
  else if (isBrowserPlayableStream(stream)) score += 2;
  // Decisive, and only for in-browser playback: a source this browser cannot
  // decode never plays here regardless of quality, so it sinks below every
  // option that does — 4K still wins wherever it is genuinely decodable.
  // External players decode all of this themselves, so they keep the pure
  // quality order and none of these penalties apply.
  if (target === "browser") {
    // What we know about the AUDIO decides whether a "direct" file is heard.
    // Chromium plays the picture of a UHD Blu-ray rip and silently drops its
    // AC-3/DTS/TrueHD track; release names often omit the codec entirely, so
    // those files used to rank first, start, go silent and hop (#audio).
    const audio = browserAudioClass(stream);
    if (audio === "ok") score += 300;
    else if (audio === "unknown-disc") score -= 450;
    else if (audio === "unknown") score -= 120;
    if (!videoDecodableForDevice(stream)) score -= 1000;
    // The missing half: undecodable AUDIO was scored normally, so DDP/Atmos
    // releases (nearly every modern streaming rip) ranked top and then failed.
    if (!audioDecodableForDevice(stream)) score -= 900;
    // Above the bitrate this device's connection proved it can carry: it would
    // start slowly and stall, so a smaller release of the same title wins.
    if (exceedsConnection(streamSizeBytes(stream))) score -= 400;
  }
  if (text.includes("cam") || text.includes("hdcam") || text.includes("telesync") || text.includes("ts ")) score -= 500;
  // Uncached debrid torrents must download server-side before playing — sink
  // them below every cached option so they're never picked by default.
  if (isUncachedDebridStream(stream)) score -= 600;
  return score;
}

function qualityScore(text: string) {
  if (text.includes("4320") || text.includes("8k")) return 900;
  if (text.includes("2160") || text.includes("4k") || text.includes("uhd")) return 700;
  if (text.includes("1440")) return 540;
  if (text.includes("1080")) return 420;
  if (text.includes("720")) return 260;
  if (text.includes("480")) return 120;
  return 180;
}

// Phones: 1080p is the sweet spot (sharp on any phone/tablet screen, sane
// bitrate); 4K and above rank below 1080p and 1440p.
function mobileQualityScore(text: string) {
  if (text.includes("4320") || text.includes("8k")) return 120;
  if (text.includes("2160") || text.includes("4k") || text.includes("uhd")) return 240;
  if (text.includes("1440")) return 380;
  if (text.includes("1080")) return 420;
  if (text.includes("720")) return 300;
  if (text.includes("480")) return 120;
  return 180;
}

// Phones: bigger is not better. Reward a normal streaming size, penalise files
// that will not start quickly or keep up over a mobile connection.
function mobileSizeScore(stream: StreamSource, text: string) {
  const bytes = stream.sizeBytes ?? parseSizeBytes(stream.size || text);
  if (!bytes) return 0;
  if (bytes > 40 * GB) return -450;
  if (bytes > 20 * GB) return -300;
  if (bytes > 12 * GB) return -160;
  if (bytes >= 1 * GB && bytes <= 8 * GB) return 40;
  return 0;
}

function sizeScore(stream: StreamSource, text: string) {
  const bytes = stream.sizeBytes ?? parseSizeBytes(stream.size || text);
  if (!bytes) return 0;
  return Math.min(260, bytes / 500_000_000);
}

/** Best-effort size of a stream in bytes (metadata field or parsed from text). */
export function streamSizeBytes(stream: StreamSource): number {
  const text = `${stream.quality ?? ""} ${stream.source ?? ""} ${stream.description ?? ""} ${stream.size ?? ""}`.toLowerCase();
  return stream.sizeBytes ?? parseSizeBytes(stream.size || text);
}

function parseSizeBytes(value: string) {
  const match = value.match(/\b(\d+(?:\.\d+)?)\s?(tb|gb|mb)\b/i);
  if (!match) return 0;
  const amount = Number(match[1]);
  if (!Number.isFinite(amount)) return 0;
  const unit = match[2].toLowerCase();
  if (unit === "tb") return amount * 1_000_000_000_000;
  if (unit === "gb") return amount * 1_000_000_000;
  return amount * 1_000_000;
}

/**
 * What the release says about its audio, for in-browser playback:
 * - "ok": AAC / Opus / MP3 / FLAC (YTS/YIFY always ship AAC), nothing else named
 * - "dolby": AC-3, E-AC-3, DTS, TrueHD or Atmos named (the playback plan
 *   decides whether this browser decodes it or it needs conversion)
 * - "unknown-disc": no codec named on a Blu-ray / UHD / remux release, which
 *   almost always carries AC-3, DTS or TrueHD: silent in Chromium
 * - "unknown": no codec named on anything else (WEB releases, bare names)
 */
export type BrowserAudioClass = "ok" | "dolby" | "unknown-disc" | "unknown";
const DOLBY_AUDIO = /\b(?:e-?)?ac-?3\b|\bec-?3\b|\bddp|\bdd\+|\bdd[257]\.?[01]|digital[. ]plus|\bdts|true-?hd|\batmos\b|\bmlp\b|\blpcm\b|\bpcm\b/;
const BROWSER_AUDIO = /\baac|\bmp4a|\bopus\b|\bmp3\b|\bflac\b|\byts\b|\byts\.|\byify\b/;
const DISC_RELEASE = /blu-?ray|\bbd-?rip|\bbd-?remux|\bbdmv\b|\bbr-?rip|\buhd\b|remux|\bbd(?:25|50|66|100)?\b/;
export function browserAudioClass(stream: Pick<StreamSource, "quality" | "source" | "description" | "behaviorHints" | "media">): BrowserAudioClass {
  const codec = stream.media?.audioCodec?.toLowerCase();
  if (codec) return BROWSER_AUDIO.test(codec) || /^(?:aac|opus|mp3|flac|vorbis)/.test(codec) ? "ok" : "dolby";
  const text = `${stream.behaviorHints?.filename ?? ""} ${stream.quality ?? ""} ${stream.source ?? ""} ${stream.description ?? ""}`.toLowerCase();
  if (DOLBY_AUDIO.test(text)) return "dolby";
  if (BROWSER_AUDIO.test(text)) return "ok";
  return DISC_RELEASE.test(text) ? "unknown-disc" : "unknown";
}

type PlanOf = (stream: StreamSource) => Pick<PlaybackPlan, "route" | "method">;

/**
 * Coarse playback-risk tier, lowest first. Direct files whose audio is known
 * to play here start instantly; direct files with unnamed audio may turn out
 * silent; a Blu-ray/UHD file with unnamed audio is nearly always silent in
 * Chromium, so even in-browser audio conversion of a known codec is the safer
 * bet. Shared by the ranking and the autoplay preference reordering.
 */
export function browserPlaybackTier(stream: StreamSource, planOf: PlanOf = playbackPlan): number {
  const method = planOf(stream).method;
  if (method === "transcode") return 4;
  if (method === "remux") return 2;
  const audio = browserAudioClass(stream);
  if (audio === "ok" || audio === "dolby") return 0;
  return audio === "unknown" ? 1 : 3;
}

/**
 * Best-first order for playing in THIS browser: sources that play as they are
 * with audio this browser is known to decode, then direct files with unnamed
 * audio, then in-browser conversion, then disc releases with unnamed audio,
 * then provider conversion (see browserPlaybackTier); quality and
 * debrid preference decide within each method. Autoplay, the details source
 * picker and the in-player source list all use this one comparator, so "the
 * next source" means the same thing everywhere.
 */
export function compareForBrowser(a: StreamSource, b: StreamSource, planOf: PlanOf = playbackPlan) {
  return browserPlaybackTier(a, planOf) - browserPlaybackTier(b, planOf)
    || sourcePickerScore(b, "browser") - sourcePickerScore(a, "browser");
}

export type PlaybackGroups = {
  /** Plays in this browser (directly or after conversion), best first. */
  here: StreamSource[];
  /** No browser route: external players only, quality first. */
  external: StreamSource[];
};

/** Split and order a source list into "Plays here" and "External player". */
export function groupSourcesForPlayback(streams: StreamSource[], planOf: PlanOf = playbackPlan): PlaybackGroups {
  const here: StreamSource[] = [];
  const external: StreamSource[] = [];
  for (const stream of streams) {
    if (stream.url && planOf(stream).route === "here") here.push(stream);
    else external.push(stream);
  }
  here.sort((a, b) => compareForBrowser(a, b, planOf));
  external.sort((a, b) => sourcePickerScore(b, "external") - sourcePickerScore(a, "external"));
  return { here, external };
}

/** The combined order (plays-here group first) used by every source list. */
export function orderSourcesForPlayback(streams: StreamSource[], planOf: PlanOf = playbackPlan) {
  const groups = groupSourcesForPlayback(streams, planOf);
  return [...groups.here, ...groups.external];
}

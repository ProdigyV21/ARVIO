import { languageCode, sameLanguage, trackLanguageName } from "./languages";

export type SubtitleLoadState = {
  status: "off" | "loading" | "ready" | "failed";
  track: TextTrack | null;
};

const MAX_SUBTITLE_BYTES = 2 * 1024 * 1024;

// ---------- Text encoding ----------
//
// Subtitle sites still serve a lot of legacy single-byte files (Polish and
// Czech in windows-1250, Russian in windows-1251, Arabic in windows-1256, …)
// with no charset in the response. Decoding those as UTF-8 turned every
// accented letter into "�". Order of evidence: a byte-order mark, valid UTF-8,
// then the code page whose decoded text looks most like real words.

type CodePage = { label: string; frequent: Set<string> | null; script: RegExp | null };
const CENTRAL_EUROPEAN = new Set("ąćęłńśźżčďěňřšťůžőűăşţáéíóöúüýäôĄĆĘŁŃŚŹŻČĎĚŇŘŠŤŮŽŐŰĂŞŢÁÉÍÓÖÚÜÝÄÔ");
const CODE_PAGES: CodePage[] = [
  { label: "windows-1252", frequent: new Set("àâäçèéêëîïôöùûüßñáíóúãõœæÀÂÄÇÈÉÊËÎÏÔÖÙÛÜÑÁÍÓÚÃÕ"), script: null },
  { label: "windows-1250", frequent: CENTRAL_EUROPEAN, script: null },
  { label: "windows-1251", frequent: new Set("оеаинтсрвлкмдп"), script: /[Ѐ-ӿ]/ },
  { label: "windows-1256", frequent: null, script: /[؀-ۿ]/ },
  { label: "windows-1253", frequent: new Set("αεηιονστυρκμπλ"), script: /[Ͱ-Ͽ]/ },
  { label: "windows-1254", frequent: new Set("ğışçöüİĞŞÇÖÜ"), script: null },
  { label: "windows-1255", frequent: null, script: /[֐-׿]/ },
  // ISO-8859-2 places several Polish/Czech letters elsewhere than 1250.
  { label: "iso-8859-2", frequent: CENTRAL_EUROPEAN, script: null }
];

function codePageScore(text: string, page: CodePage) {
  let total = 0;
  for (const ch of text) {
    const code = ch.codePointAt(0) ?? 0;
    if (code === 0xfffd || (code >= 0x80 && code <= 0x9f)) { total -= 10; continue; }
    if (code < 0x80) continue;
    if (page.frequent?.has(ch)) total += 2;
    else if (page.script?.test(ch)) total += page.frequent ? 0.4 : 1.7;
  }
  for (const word of text.split(/[^\p{L}\p{M}]+/u)) {
    if (!word) continue;
    const latin = /[A-Za-zÀ-ɏ]/.test(word);
    const other = /[Ͱ-ϿЀ-ӿ֐-׿؀-ۿ]/.test(word);
    if (latin && other) total -= 6;
    // Lower-to-upper switches inside a word ("гСНИЗЎ") mean the wrong page.
    total -= (word.match(/\p{Ll}\p{Lu}/gu)?.length ?? 0) * 3;
  }
  // Symbols glued between letters ("na³ka") are a wrong code page too.
  total -= (text.match(/\p{L}[ -¿×÷ -⃿]\p{L}/gu)?.length ?? 0) * 4;
  if (page.label === "windows-1252") {
    total += (text.match(/(^|[\s"'(-])[¿¡]/g)?.length ?? 0) * 3;
    total -= (text.match(/\p{L}[þðÞÐ]|[þðÞÐ]\p{L}/gu)?.length ?? 0) * 3;
    total -= (text.match(/[õÕ](?![eE])/g)?.length ?? 0) * 2;
    total -= (text.match(/\p{L}[ªº]|[ªº]\p{L}/gu)?.length ?? 0) * 3;
  }
  return total;
}

function decodeWith(label: string, bytes: Uint8Array, fatal = false) {
  return new TextDecoder(label, { fatal }).decode(bytes);
}

/** Decode subtitle bytes: BOM → UTF-8 → declared charset → best legacy code page. */
export function decodeSubtitleBytes(bytes: Uint8Array, declaredCharset?: string | null): { text: string; encoding: string } {
  if (bytes[0] === 0xef && bytes[1] === 0xbb && bytes[2] === 0xbf) return { text: decodeWith("utf-8", bytes.subarray(3)), encoding: "utf-8" };
  if (bytes[0] === 0xff && bytes[1] === 0xfe) return { text: decodeWith("utf-16le", bytes.subarray(2)), encoding: "utf-16le" };
  if (bytes[0] === 0xfe && bytes[1] === 0xff) return { text: decodeWith("utf-16be", bytes.subarray(2)), encoding: "utf-16be" };
  try { return { text: decodeWith("utf-8", bytes, true), encoding: "utf-8" }; } catch { /* legacy code page below */ }
  const declared = declaredCharset?.trim().toLowerCase();
  let best: { text: string; encoding: string; score: number } | null = null;
  const pages = [...CODE_PAGES];
  if (declared && !/^utf-?8$/.test(declared) && !pages.some((page) => page.label === declared)) {
    pages.unshift({ label: declared, frequent: null, script: null });
  }
  for (const page of pages) {
    let text: string;
    try { text = decodeWith(page.label, bytes); } catch { continue; }
    // A charset the server named gets a nudge, not a veto: it is often wrong.
    const score = codePageScore(text, page) + (page.label === declared ? 4 : 0);
    if (!best || score > best.score) best = { text, encoding: page.label, score };
  }
  return best ? { text: best.text, encoding: best.encoding } : { text: decodeWith("utf-8", bytes), encoding: "utf-8" };
}

export function charsetFromContentType(contentType?: string | null) {
  return contentType?.match(/charset=["']?([\w.:-]+)/i)?.[1] ?? null;
}

// ---------- Formats ----------

const CUE_TIMING = /(?:\d{2,}:)?\d{2}:\d{2}[.,]\d{3}\s+-->\s+(?:\d{2,}:)?\d{2}:\d{2}[.,]\d{3}/;

export function isAssSubtitle(text: string) {
  return /^\s*\[Script Info\]/i.test(text) || (/^\[Events\]/im.test(text) && /^Dialogue:/im.test(text));
}

function assTime(value: string) {
  const match = value.trim().match(/^(\d+):(\d{1,2}):(\d{1,2})(?:[.,](\d{1,3}))?$/);
  if (!match) return null;
  const [, h, m, s, frac = "0"] = match;
  const ms = Number(frac.padEnd(3, "0").slice(0, 3));
  return Number(h) * 3600 + Number(m) * 60 + Number(s) + ms / 1000;
}

function vttTime(seconds: number) {
  const total = Math.max(0, Math.round(seconds * 1000));
  const h = Math.floor(total / 3_600_000);
  const m = Math.floor((total % 3_600_000) / 60_000);
  const s = Math.floor((total % 60_000) / 1000);
  const ms = total % 1000;
  return `${String(h).padStart(2, "0")}:${String(m).padStart(2, "0")}:${String(s).padStart(2, "0")}.${String(ms).padStart(3, "0")}`;
}

function escapeCueText(text: string) {
  return text.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
}

/**
 * ASS/SSA → WebVTT. Styling, positioning, karaoke and drawing commands are
 * dropped (browsers cannot render them); only timing and text are kept.
 */
export function assToVtt(input: string) {
  const lines = input.replace(/^﻿/, "").replace(/\r\n?/g, "\n").split("\n");
  let inEvents = false;
  let format = ["layer", "start", "end", "style", "name", "marginl", "marginr", "marginv", "effect", "text"];
  const cues: Array<{ start: number; end: number; text: string }> = [];
  for (const raw of lines) {
    const line = raw.trim();
    if (/^\[.+\]$/.test(line)) { inEvents = /^\[events\]$/i.test(line); continue; }
    if (!inEvents) continue;
    if (/^format:/i.test(line)) {
      format = line.slice(line.indexOf(":") + 1).split(",").map((field) => field.trim().toLowerCase());
      continue;
    }
    if (!/^dialogue:/i.test(line)) continue;
    const body = line.slice(line.indexOf(":") + 1).replace(/^\s/, "");
    const fields: string[] = [];
    let rest = body;
    // Text is the last field and may itself contain commas.
    for (let i = 0; i < format.length - 1; i += 1) {
      const comma = rest.indexOf(",");
      if (comma < 0) break;
      fields.push(rest.slice(0, comma));
      rest = rest.slice(comma + 1);
    }
    fields.push(rest);
    const start = assTime(fields[format.indexOf("start")] ?? "");
    const end = assTime(fields[format.indexOf("end")] ?? "");
    let text = fields[format.indexOf("text")] ?? "";
    if (start == null || end == null || end <= start) continue;
    // Vector drawings ({\p1}…) are shapes, not words.
    if (/\{[^}]*\\p[1-9]/.test(text)) continue;
    text = text.replace(/\{[^}]*\}/g, "").replace(/\\[Nn]/g, "\n").replace(/\\h/g, " ").trim();
    if (!text) continue;
    cues.push({ start, end, text });
  }
  cues.sort((a, b) => a.start - b.start || a.end - b.end);
  return `WEBVTT\n\n${cues.map((cue) => `${vttTime(cue.start)} --> ${vttTime(cue.end)}\n${escapeCueText(cue.text)}`).join("\n\n")}\n`;
}

export function normalizeSubtitleText(input: string): string {
  const raw = input.replace(/^﻿/, "");
  if (isAssSubtitle(raw)) {
    const converted = assToVtt(raw);
    if (!CUE_TIMING.test(converted)) throw new Error("Invalid subtitle response");
    return converted;
  }
  const text = raw.replace(/\r\n?/g, "\n").trim();
  // The browser parses WebVTT. Reject empty/error responses before allocating a track.
  if (!CUE_TIMING.test(text) || /^(?:<!doctype|<html|\{)/i.test(text)) throw new Error("Invalid subtitle response");
  return /^WEBVTT(?:\s|$)/.test(text)
    ? `${text}\n`
    // SRT override tags ({\an8}, {\i1}) are ASS leftovers WebVTT would print.
    : `WEBVTT\n\n${text.replace(/(\d{2}:\d{2}:\d{2}),(\d{3})/g, "$1.$2").replace(/\{\\[^}]*\}/g, "")}\n`;
}

export async function fetchSubtitleText(url: string, signal: AbortSignal): Promise<string> {
  const response = await fetch(url, { signal, credentials: "omit" });
  if (!response.ok) throw new Error("Subtitle request failed");
  if (Number(response.headers.get("content-length")) > MAX_SUBTITLE_BYTES) {
    await response.body?.cancel();
    throw new Error("Subtitle file too large");
  }
  const reader = response.body?.getReader();
  if (!reader) throw new Error("Empty subtitle response");
  const chunks: Uint8Array[] = [];
  let size = 0;
  try {
    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      size += value.byteLength;
      if (size > MAX_SUBTITLE_BYTES) throw new Error("Subtitle file too large");
      chunks.push(value);
    }
    const bytes = new Uint8Array(size);
    let offset = 0;
    for (const chunk of chunks) { bytes.set(chunk, offset); offset += chunk.byteLength; }
    return normalizeSubtitleText(decodeSubtitleBytes(bytes, charsetFromContentType(response.headers.get("content-type"))).text);
  } finally {
    await reader.cancel().catch(() => undefined);
    reader.releaseLock();
  }
}

// ---------- Tracks ----------

/** Text tracks this module created (external subtitles), as opposed to the media's own. */
const externalTracks = new WeakSet<TextTrack>();
export function isExternalSubtitleTrack(track: TextTrack) { return externalTracks.has(track); }

/** The media's own subtitle/caption tracks (MKV/MP4 text tracks, HLS renditions). */
export function embeddedTextTracks(video: Pick<HTMLVideoElement, "textTracks">): TextTrack[] {
  return Array.from(video.textTracks as unknown as ArrayLike<TextTrack>)
    .filter((track) => !externalTracks.has(track) && (track.kind === "subtitles" || track.kind === "captions" || !track.kind));
}

/**
 * Fetch only the selected subtitle through the relay, without changing video
 * CORS mode. The media's own text tracks are hidden rather than disabled while
 * an external file shows (so they stay listed and switch back instantly), and
 * `showTrack` shows one of them instead of an external file.
 */
export function attachExternalSubtitle(
  video: HTMLVideoElement,
  subtitle: { url: string; lang?: string; label?: string } | null,
  onState: (state: SubtitleLoadState) => void,
  options: { showTrack?: TextTrack | null } = {}
): () => void {
  const controller = new AbortController();
  let element: HTMLTrackElement | null = null;
  let blobUrl: string | null = null;
  let disposed = false;
  let failed = false;
  const show = options.showTrack ?? null;
  const syncModes = () => {
    for (const track of Array.from(video.textTracks as unknown as ArrayLike<TextTrack>)) {
      if (element?.track === track) track.mode = failed ? "disabled" : "showing";
      else if (show && track === show) track.mode = "showing";
      else if (externalTracks.has(track)) track.mode = "disabled";
      // Embedded tracks: hidden while another subtitle shows, off otherwise.
      else track.mode = subtitle || show ? (track.mode === "disabled" ? "disabled" : "hidden") : "disabled";
    }
  };
  const fail = () => {
    if (disposed || failed) return;
    failed = true;
    window.clearTimeout(timeout);
    controller.abort();
    syncModes();
    onState({ status: "failed", track: null });
  };
  const loaded = () => {
    if (disposed || failed) return;
    if (!element?.track.cues?.length) { fail(); return; }
    window.clearTimeout(timeout);
    syncModes();
    onState({ status: "ready", track: element.track });
  };
  const timeout = subtitle ? window.setTimeout(fail, 20000) : undefined;
  video.textTracks.addEventListener("addtrack", syncModes);
  video.addEventListener("loadedmetadata", syncModes);
  syncModes();
  onState(show && !subtitle ? { status: "ready", track: show } : { status: subtitle ? "loading" : "off", track: null });
  if (subtitle) {
    void fetchSubtitleText(subtitle.url, controller.signal).then(text => {
      if (disposed || failed) return;
      blobUrl = URL.createObjectURL(new Blob([text], { type: "text/vtt" }));
      element = document.createElement("track");
      element.kind = "subtitles";
      element.srclang = subtitle.lang || "en";
      element.label = subtitle.label || subtitle.lang || "Subtitle";
      element.src = blobUrl;
      externalTracks.add(element.track);
      element.addEventListener("load", loaded);
      element.addEventListener("error", fail);
      video.appendChild(element);
      syncModes();
    }).catch(fail);
  }
  return () => {
    disposed = true;
    controller.abort();
    window.clearTimeout(timeout);
    video.textTracks.removeEventListener("addtrack", syncModes);
    video.removeEventListener("loadedmetadata", syncModes);
    if (element) {
      element.removeEventListener("load", loaded);
      element.removeEventListener("error", fail);
      element.track.mode = "disabled";
      element.remove();
    }
    if (blobUrl) URL.revokeObjectURL(blobUrl);
  };
}

const originalCueTimes = new WeakMap<VTTCue, { start: number; end: number }>();

export function positionSubtitleCues(track: TextTrack, line: number, offsetMs: number) {
  const shift = Number.isFinite(offsetMs) ? offsetMs / 1000 : 0;
  for (const cue of Array.from(track.cues ?? []) as VTTCue[]) {
    cue.snapToLines = false;
    cue.line = line;
    if (!originalCueTimes.has(cue)) originalCueTimes.set(cue, { start: cue.startTime, end: cue.endTime });
    const base = originalCueTimes.get(cue)!;
    const start = Math.max(0, base.start + shift);
    const end = Math.max(start + 0.05, base.end + shift);
    if (cue.startTime !== start) cue.startTime = start;
    if (cue.endTime !== end) cue.endTime = end;
  }
}

// ---------- Presentation ----------

// Display names. Matching and normalising go through lib/languages.ts (the
// port of Android's LanguageCodes); this only adds the regional variants that
// subtitle sites use and Intl does not name ("pob" used to print as "pob").
const REGIONAL_NAMES: Record<string, string> = {
  "pt-br": "Portuguese (Brazil)", spl: "Spanish (Latin America)", spn: "Spanish (Latin America)", ea: "Spanish (Latin America)",
  "es-419": "Spanish (Latin America)", "es-la": "Spanish (Latin America)", zht: "Chinese (Traditional)", "zh-tw": "Chinese (Traditional)",
  "zh-hant": "Chinese (Traditional)", zhs: "Chinese (Simplified)", zhc: "Chinese (Simplified)", "zh-cn": "Chinese (Simplified)",
  "zh-hans": "Chinese (Simplified)", ze: "Chinese (Bilingual)", mne: "Montenegrin", scc: "Serbian", scr: "Croatian", und: "Unknown language"
};

/** Readable language name for a subtitle's language code or label. */
export function subtitleLanguageLabel(codeOrName: string | null | undefined): string {
  const value = (codeOrName ?? "").trim();
  if (!value) return "Unknown language";
  const raw = value.toLowerCase().replace(/_/g, "-");
  if (REGIONAL_NAMES[raw]) return REGIONAL_NAMES[raw];
  const code = languageCode(value);
  if (!code) return value;
  if (REGIONAL_NAMES[code]) return REGIONAL_NAMES[code];
  const known = trackLanguageName(code);
  if (known && languageCode(known) === code && known.toLowerCase() !== code) return known;
  if (/^[a-z]{2,3}(?:-[a-z0-9]{2,4})?$/.test(code)) {
    try {
      const name = new Intl.DisplayNames(["en"], { type: "language" }).of(code);
      if (name && name.toLowerCase() !== code) return name;
    } catch { /* fall through */ }
  }
  return value;
}

export type SubtitleChoice = {
  index: number;
  language: string;
  /** Release/file name, when the label carries one. */
  release: string | null;
  hearingImpaired: boolean;
  forced: boolean;
  provider: string | null;
  /** Higher = better match for the playing file. */
  match: number;
};

export type SubtitleGroup = { language: string; choices: SubtitleChoice[] };

type SubtitleLike = { lang?: string | null; label?: string | null; id?: string | null; url?: string | null; provider?: string | null; isForced?: boolean | null };

const HI_PATTERN = /(?:^|[^a-z])(?:sdh|hi|cc|hearing[ ._-]?impaired)(?:[^a-z]|$)/i;

function releaseTokens(name: string | null | undefined) {
  return new Set((name ?? "").toLowerCase().replace(/\.(srt|vtt|ass|ssa|sub|mkv|mp4)$/i, "")
    .split(/[^a-z0-9]+/).filter((token) => token.length > 1 && !/^(the|and|of|a|en|eng|english)$/.test(token)));
}

function releaseOf(subtitle: SubtitleLike, language: string) {
  const label = subtitle.label?.trim() ?? "";
  if (!label || label.toLowerCase() === language.toLowerCase() || label.toLowerCase() === (subtitle.lang ?? "").toLowerCase()) return null;
  // A bare language label ("English", "English [CC]") is not a release name.
  if (/^[\p{L} ()[\]-]{1,32}$/u.test(label) && !/[._]/.test(label)) return null;
  return label;
}

/**
 * Subtitles grouped by language — the preferred language first, then English,
 * then A–Z — and, inside each language, the best match for the playing release
 * first (shared release tokens such as "1080p", "WEB-DL", the group name),
 * full subtitles before forced-only ones, and regular before SDH/HI.
 */
export function groupSubtitles(subtitles: SubtitleLike[], options: { preferred?: string | null; releaseName?: string | null } = {}): SubtitleGroup[] {
  const preferredCode = languageCode(options.preferred);
  const playing = releaseTokens(options.releaseName);
  const groups = new Map<string, SubtitleChoice[]>();
  subtitles.forEach((subtitle, index) => {
    const language = subtitleLanguageLabel(subtitle.lang || subtitle.label);
    const release = releaseOf(subtitle, language);
    const flags = `${subtitle.label ?? ""} ${subtitle.id ?? ""} ${subtitle.url ?? ""}`;
    const tokens = releaseTokens(release);
    let shared = 0;
    for (const token of tokens) if (playing.has(token)) shared += 1;
    const choice: SubtitleChoice = {
      index,
      language,
      release,
      hearingImpaired: HI_PATTERN.test(flags),
      forced: Boolean(subtitle.isForced) || /(?:^|[^a-z])forced(?:[^a-z]|$)/i.test(flags),
      provider: subtitle.provider ?? null,
      match: shared
    };
    groups.set(language, [...(groups.get(language) ?? []), choice]);
  });
  // Rank groups with lib/languages: the preferred language, then English.
  const rankOf = (choices: SubtitleChoice[]) => {
    const first = subtitles[choices[0]?.index ?? -1];
    const code = languageCode(first?.lang || first?.label);
    if (preferredCode && code === preferredCode) return 0;
    if (preferredCode && sameLanguage(code, preferredCode)) return 0.5;
    return code === "en" ? 1 : 2;
  };
  return [...groups.entries()]
    .map(([language, choices]) => ({
      language,
      choices: choices.sort((a, b) => b.match - a.match || Number(a.forced) - Number(b.forced)
        || Number(a.hearingImpaired) - Number(b.hearingImpaired) || a.index - b.index)
    }))
    .sort((a, b) => rankOf(a.choices) - rankOf(b.choices) || a.language.localeCompare(b.language));
}

// ---------- Per-title subtitle delay ----------

const DELAY_KEY = "arvio.player.subtitleDelay.v1";
type DelayStore = Pick<Storage, "getItem" | "setItem">;
function delayStorage(): DelayStore | undefined {
  try { return typeof window === "undefined" ? undefined : window.localStorage; } catch { return undefined; }
}

/** Subtitle delay remembered for one title, else the global default. */
export function loadTitleSubtitleDelay(titleKey: string, fallbackMs: number, storage: DelayStore | undefined = delayStorage()) {
  try {
    const map = JSON.parse(storage?.getItem(DELAY_KEY) || "{}") as Record<string, number>;
    const value = map[titleKey];
    return typeof value === "number" && Number.isFinite(value) ? value : fallbackMs;
  } catch { return fallbackMs; }
}

export function saveTitleSubtitleDelay(titleKey: string, delayMs: number, storage: DelayStore | undefined = delayStorage()) {
  try {
    const map = JSON.parse(storage?.getItem(DELAY_KEY) || "{}") as Record<string, number>;
    delete map[titleKey];
    map[titleKey] = Math.round(delayMs);
    // Bounded: keep the 200 most recently changed titles.
    const entries = Object.entries(map);
    const trimmed = Object.fromEntries(entries.slice(Math.max(0, entries.length - 200)));
    storage?.setItem(DELAY_KEY, JSON.stringify(trimmed));
  } catch { /* per-viewer convenience only */ }
}

/** Step a delay by ±100 ms, clamped to ±30 s. */
export function stepSubtitleDelay(currentMs: number, direction: 1 | -1) {
  return Math.max(-30_000, Math.min(30_000, Math.round(currentMs + direction * 100)));
}

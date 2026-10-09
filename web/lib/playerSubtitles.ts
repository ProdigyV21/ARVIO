export type SubtitleLoadState = {
  status: "off" | "loading" | "ready" | "failed";
  track: TextTrack | null;
};

const MAX_SUBTITLE_BYTES = 2 * 1024 * 1024;

export function normalizeSubtitleText(input: string): string {
  const text = input.replace(/^\uFEFF/, "").replace(/\r\n?/g, "\n").trim();
  // The browser parses WebVTT. Reject empty/error responses before allocating a track.
  if (!/(?:\d{2,}:)?\d{2}:\d{2}[.,]\d{3}\s+-->\s+(?:\d{2,}:)?\d{2}:\d{2}[.,]\d{3}/.test(text)
    || /^(?:<!doctype|<html|\{)/i.test(text)) throw new Error("Invalid subtitle response");
  return /^WEBVTT(?:\s|$)/.test(text)
    ? `${text}\n`
    : `WEBVTT\n\n${text.replace(/(\d{2}:\d{2}:\d{2}),(\d{3})/g, "$1.$2")}\n`;
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
  const decoder = new TextDecoder();
  let size = 0;
  let text = "";
  try {
    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      size += value.byteLength;
      if (size > MAX_SUBTITLE_BYTES) throw new Error("Subtitle file too large");
      text += decoder.decode(value, { stream: true });
    }
    return normalizeSubtitleText(text + decoder.decode());
  } finally {
    await reader.cancel().catch(() => undefined);
    reader.releaseLock();
  }
}

/** Fetch only the selected subtitle through the relay, without changing video CORS mode. */
export function attachExternalSubtitle(
  video: HTMLVideoElement,
  subtitle: { url: string; lang?: string; label?: string } | null,
  onState: (state: SubtitleLoadState) => void
): () => void {
  const controller = new AbortController();
  let element: HTMLTrackElement | null = null;
  let blobUrl: string | null = null;
  let disposed = false;
  let failed = false;
  const syncModes = () => {
    for (const track of Array.from(video.textTracks)) {
      track.mode = !failed && element?.track === track ? "showing" : "disabled";
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
  onState({ status: subtitle ? "loading" : "off", track: null });
  if (subtitle) {
    void fetchSubtitleText(subtitle.url, controller.signal).then(text => {
      if (disposed || failed) return;
      blobUrl = URL.createObjectURL(new Blob([text], { type: "text/vtt" }));
      element = document.createElement("track");
      element.kind = "subtitles";
      element.srclang = subtitle.lang || "en";
      element.label = subtitle.label || subtitle.lang || "Subtitle";
      element.src = blobUrl;
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

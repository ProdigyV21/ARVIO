// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useEffect, useState, type Dispatch, type SetStateAction, type RefObject } from "react";
import { SubtitleTranslator } from "@/lib/subtitleAi";
import { embeddedTextTracks, attachExternalSubtitle, loadTitleSubtitleDelay, saveTitleSubtitleDelay, type SubtitleLoadState } from "@/lib/playerSubtitles";
import { resolverSubtitleUrl } from "@/lib/resolver";
import type { AppSettings, StreamSource, MediaItem } from "@/lib/types";

/** Live AI translation of the selected subtitle track (mirrors the app's SubtitleTranslationManager). */
export function useAiSubtitleTranslation({ activeSubtitle, aiAvailable, aiSubsActive, aiTargetName, onToast, setAiError, setAiTranslating, settings, stream, subtitleState, translatorRef, videoRef }: { activeSubtitle: number; aiAvailable: boolean; aiSubsActive: boolean; aiTargetName: string; onToast: (message: string) => void; setAiError: Dispatch<SetStateAction<string | null>>; setAiTranslating: Dispatch<SetStateAction<boolean>>; settings: AppSettings; stream: StreamSource; subtitleState: SubtitleLoadState; translatorRef: RefObject<SubtitleTranslator | null>; videoRef: RefObject<HTMLVideoElement | null> }) {
  // Live cue translation: when AI subs are active, the selected (English
  // source) track's cues are translated in small batches and swapped in place;
  // upcoming cues are pre-warmed so swaps land before display. Mirrors the
  // app's SubtitleTranslationManager.
  useEffect(() => {
    if (!aiSubsActive || !aiAvailable || activeSubtitle < 0) return undefined;
    const video = videoRef.current;
    const track = subtitleState.track;
    if (!video || !track) return undefined;
    const translator = new SubtitleTranslator(settings.aiApiKey, settings.aiSubtitleModel === "gemini" ? "gemini" : "groq", aiTargetName);
    translator.onTranslatingChanged = setAiTranslating;
    let lastErrorToast = 0;
    translator.onError = (failure) => {
      if (translatorRef.current !== translator) return;
      setAiError(failure.message);
      const now = Date.now();
      if (now - lastErrorToast >= 20_000) {
        lastErrorToast = now;
        onToast(failure.message);
      }
    };
    setAiError(null);
    translatorRef.current = translator;
    const originals = new WeakMap<VTTCue, string>();
    const applyToCue = (cue: VTTCue) => {
      const original = originals.get(cue) ?? cue.text;
      originals.set(cue, original);
      void translator.translate(original).then((translated) => {
        if (translatorRef.current === translator && cue.text !== translated) cue.text = translated;
      });
    };
    const onCueChange = () => {
      const active = [...(track.activeCues ?? [])] as VTTCue[];
      active.forEach(applyToCue);
      // Pre-warm the next handful of cues.
      const all = [...(track.cues ?? [])] as VTTCue[];
      const now = video.currentTime;
      translator.prefetch(
        all.filter((cue) => cue.startTime > now && cue.startTime < now + 30).slice(0, 8)
          .map((cue) => originals.get(cue) ?? cue.text)
      );
    };
    track.addEventListener("cuechange", onCueChange);
    onCueChange();
    return () => {
      track.removeEventListener("cuechange", onCueChange);
      if (translatorRef.current === translator) translatorRef.current = null;
      setAiTranslating(false);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [aiSubsActive, aiAvailable, activeSubtitle, subtitleState.track, stream.url, settings.aiApiKey, settings.aiSubtitleModel, aiTargetName]);
}

/** Embedded text tracks, the external subtitle download and the per-title subtitle delay. */
export function useSubtitleTracks({ activeSubtitle, item, manualSubtitle, setActiveSubtitle, setAiSubsActive, setEmbeddedChoiceRef, setSubtitleState, settings, stream, subtitleRetry, videoRef }: { activeSubtitle: number; item: MediaItem | null; manualSubtitle: RefObject<{ source: StreamSource["url"]; preference: string; url: string | null; } | null>; setActiveSubtitle: Dispatch<SetStateAction<number>>; setAiSubsActive: Dispatch<SetStateAction<boolean>>; setEmbeddedChoiceRef: RefObject<(track: TextTrack | null) => void>; setSubtitleState: Dispatch<SetStateAction<SubtitleLoadState>>; settings: AppSettings; stream: StreamSource; subtitleRetry: number; videoRef: RefObject<HTMLVideoElement | null> }) {
  const selectedSubtitle = stream.subtitles?.[activeSubtitle];
  // The media's own text tracks (MP4/WebM text tracks, HLS subtitle
  // renditions) are listed next to the addon subtitles and can be chosen.
  const [embeddedTracks, setEmbeddedTracks] = useState<TextTrack[]>([]);
  const [embeddedChoice, setEmbeddedChoice] = useState<TextTrack | null>(null);
  setEmbeddedChoiceRef.current = setEmbeddedChoice;
  useEffect(() => {
    const video = videoRef.current;
    setEmbeddedChoice(null);
    if (!video) return undefined;
    const refresh = () => setEmbeddedTracks(embeddedTextTracks(video));
    refresh();
    video.textTracks.addEventListener("addtrack", refresh);
    video.textTracks.addEventListener("removetrack", refresh);
    video.addEventListener("loadedmetadata", refresh);
    return () => {
      video.textTracks.removeEventListener("addtrack", refresh);
      video.textTracks.removeEventListener("removetrack", refresh);
      video.removeEventListener("loadedmetadata", refresh);
    };
  }, [stream.url]);
  const selectEmbedded = (track: TextTrack) => {
    manualSubtitle.current = { source: stream.url, preference: settings.defaultSubtitle, url: null };
    setActiveSubtitle(-1);
    setAiSubsActive(false);
    setEmbeddedChoice(track);
  };
  useEffect(() => {
    const video = videoRef.current;
    if (!video) return;
    return attachExternalSubtitle(video, selectedSubtitle ? {
      url: resolverSubtitleUrl(selectedSubtitle.url), lang: selectedSubtitle.lang, label: selectedSubtitle.label
    } : null, setSubtitleState, { showTrack: selectedSubtitle ? null : embeddedChoice });
    // Metadata refreshes must not restart the same subtitle download.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [stream.url, selectedSubtitle?.url, selectedSubtitle?.lang, selectedSubtitle?.label, subtitleRetry, embeddedChoice]);

  // Subtitle delay per title, ±0.1 s from the panel; the global setting is
  // the starting value for titles never adjusted.
  const titleKey = item ? `${item.mediaType}:${item.tmdbId ?? item.id}` : null;
  const [subtitleDelayMs, setSubtitleDelayMs] = useState(() => titleKey ? loadTitleSubtitleDelay(titleKey, settings.subtitleOffsetMs ?? 0) : settings.subtitleOffsetMs ?? 0);
  useEffect(() => {
    setSubtitleDelayMs(titleKey ? loadTitleSubtitleDelay(titleKey, settings.subtitleOffsetMs ?? 0) : settings.subtitleOffsetMs ?? 0);
  }, [titleKey, settings.subtitleOffsetMs]);
  const changeSubtitleDelay = (next: number) => {
    setSubtitleDelayMs(next);
    if (titleKey) saveTitleSubtitleDelay(titleKey, next);
  };
  return { embeddedTracks, embeddedChoice, selectEmbedded, subtitleDelayMs, changeSubtitleDelay };
}

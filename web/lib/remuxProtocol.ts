import { languageCode, sameLanguage } from "./languages";
import type { MkvSubtitleCue, MkvSubtitleTrack } from "./mkvSubtitles";

export type RemuxAudioTrack = {
  index: number; codec: string; label: string; language?: string; channels?: number;
  browserPlayable: boolean; passthrough: boolean;
};
export type RemuxProbe = {
  container: string; videoCodec?: string; videoPlayable: boolean;
  videoReason?: string; hdr10BaseLayer?: boolean; videoProbeStatus?: string;
  audioTracks: RemuxAudioTrack[]; chosenAudioIndex: number; duration: number;
};
export type RemuxCommand =
  | { type: "probe"; url: string; headers?: Record<string, string>; audioCodecs: string[]; language?: string; expectDolbyVision?: boolean; iptv?: boolean;
      /** The audio output has 6+ channels (a receiver or surround speakers): keep 5.1 when converting. */
      surround?: boolean }
  | { type: "start"; generation: number; time: number; audioIndex: number }
  | { type: "clock"; time: number }
  | { type: "ack"; id: number };
export type RemuxEvent =
  | { type: "probe"; probe: RemuxProbe }
  | { type: "chunk"; generation: number; id: number; data: ArrayBuffer }
  | { type: "end"; generation: number }
  | { type: "error"; generation: number; message: string }
  | { type: "subtitle-tracks"; tracks: MkvSubtitleTrack[] }
  | { type: "subtitle-cues"; cues: MkvSubtitleCue[] };

/**
 * [language] is the audio language, optionally followed by the second one ("Danish,English").
 * The second choice only counts when the first is a real language: under Original / no
 * preference the stream's own default stays.
 */
export function preferredAudioIndex(tracks: RemuxAudioTrack[], language = "") {
  const [first = "", ...rest] = language.split(",").map((value) => value.trim());
  const firstIsLanguage = first !== "" && !first.toLowerCase().startsWith("auto") && first.toLowerCase() !== "none";
  if (firstIsLanguage) {
    for (const choice of [first, ...rest].filter(Boolean)) {
      const index = preferredAudioIndexFor(tracks, choice, true);
      if (index >= 0) return index;
    }
  }
  return preferredAudioIndexFor(tracks, first, false);
}

function preferredAudioIndexFor(tracks: RemuxAudioTrack[], language: string, matchOnly: boolean) {
  const languages: Record<string, string[]> = {
    english: ["en", "eng"], dutch: ["nl", "nld", "dut"], german: ["de", "deu", "ger"],
    french: ["fr", "fra", "fre"], spanish: ["es", "spa"], portuguese: ["pt", "por"],
    italian: ["it", "ita"], japanese: ["ja", "jpn"], arabic: ["ar", "ara"]
  };
  const pref = language.trim().toLowerCase();
  const codes = languages[pref] ?? Object.values(languages).find((v) => v.includes(pref)) ?? [pref];
  const playable = tracks.filter((track) => track.browserPlayable);
  // The preference is a code or an Android display name ("Turkish", "Portuguese (Brazil)").
  const wanted = languageCode(language);
  const matching = playable.filter((track) => codes.includes((track.language ?? "").toLowerCase()) || (wanted !== "" && sameLanguage(track.language, wanted)));
  // Keep provider/default order; a compatible stereo track beats unnecessary conversion.
  if (matchOnly && !matching.length) return -1;
  const pool = matching.length ? matching : playable;
  return (pool.find((track) => track.passthrough) ?? pool[0])?.index ?? -1;
}

/** Channels and bitrate for converted (AAC) audio: 5.1 only for a 5.1+ source on surround output. */
export function convertedAudioLayout(sourceChannels: number | undefined, surround: boolean, encoderSupports6: boolean) {
  return surround && encoderSupports6 && (sourceChannels ?? 0) >= 6
    ? { numberOfChannels: 6, bitrate: 384000 }
    : { numberOfChannels: 2, bitrate: 192000 };
}

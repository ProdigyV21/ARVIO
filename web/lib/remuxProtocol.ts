import { languageCode, sameLanguage } from "./languages";

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
  | { type: "probe"; url: string; headers?: Record<string, string>; audioCodecs: string[]; language?: string; secondaryLanguage?: string; expectDolbyVision?: boolean; iptv?: boolean }
  | { type: "start"; generation: number; time: number; audioIndex: number }
  | { type: "clock"; time: number }
  | { type: "ack"; id: number };
export type RemuxEvent =
  | { type: "probe"; probe: RemuxProbe }
  | { type: "chunk"; generation: number; id: number; data: ArrayBuffer }
  | { type: "end"; generation: number }
  | { type: "error"; generation: number; message: string };

/**
 * Index of the audio track to play: the first match for `language`, else (when `language`
 * names a real language with no matching track) the first match for `secondary`, else the
 * provider's default order.
 */
export function preferredAudioIndex(tracks: RemuxAudioTrack[], language = "", secondary = "") {
  const playable = tracks.filter((track) => track.browserPlayable);
  let matching = tracksInLanguage(playable, language);
  if (!matching.length && isSpecificLanguage(language) && isSpecificLanguage(secondary)) matching = tracksInLanguage(playable, secondary);
  // Keep provider/default order; a compatible stereo track beats unnecessary conversion.
  const pool = matching.length ? matching : playable;
  return (pool.find((track) => track.passthrough) ?? pool[0])?.index ?? -1;
}

/** False for the "no specific language" settings: empty (Original), Auto, None and Off. */
function isSpecificLanguage(language: string) {
  const value = language.trim().toLowerCase();
  return value !== "" && value !== "none" && value !== "off" && !value.startsWith("auto");
}

function tracksInLanguage(playable: RemuxAudioTrack[], language: string) {
  const languages: Record<string, string[]> = {
    english: ["en", "eng"], dutch: ["nl", "nld", "dut"], german: ["de", "deu", "ger"],
    french: ["fr", "fra", "fre"], spanish: ["es", "spa"], portuguese: ["pt", "por"],
    italian: ["it", "ita"], japanese: ["ja", "jpn"], arabic: ["ar", "ara"]
  };
  const pref = language.trim().toLowerCase();
  const codes = languages[pref] ?? Object.values(languages).find((v) => v.includes(pref)) ?? [pref];
  // The preference is a code or an Android display name ("Turkish", "Portuguese (Brazil)").
  const wanted = languageCode(language);
  return playable.filter((track) => codes.includes((track.language ?? "").toLowerCase()) || (wanted !== "" && sameLanguage(track.language, wanted)));
}

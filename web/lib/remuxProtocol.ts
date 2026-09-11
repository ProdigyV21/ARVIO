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
  | { type: "probe"; url: string; headers?: Record<string, string>; audioCodecs: string[]; language?: string; expectDolbyVision?: boolean }
  | { type: "start"; generation: number; time: number; audioIndex: number }
  | { type: "clock"; time: number }
  | { type: "ack"; id: number };
export type RemuxEvent =
  | { type: "probe"; probe: RemuxProbe }
  | { type: "chunk"; generation: number; id: number; data: ArrayBuffer }
  | { type: "end"; generation: number }
  | { type: "error"; generation: number; message: string };

export function preferredAudioIndex(tracks: RemuxAudioTrack[], language = "") {
  const languages: Record<string, string[]> = {
    english: ["en", "eng"], dutch: ["nl", "nld", "dut"], german: ["de", "deu", "ger"],
    french: ["fr", "fra", "fre"], spanish: ["es", "spa"], portuguese: ["pt", "por"],
    italian: ["it", "ita"], japanese: ["ja", "jpn"], arabic: ["ar", "ara"]
  };
  const pref = language.trim().toLowerCase();
  const codes = languages[pref] ?? Object.values(languages).find((v) => v.includes(pref)) ?? [pref];
  const playable = tracks.filter((track) => track.browserPlayable);
  const matching = playable.filter((track) => codes.includes((track.language ?? "").toLowerCase()));
  // Keep provider/default order; a compatible stereo track beats unnecessary conversion.
  const pool = matching.length ? matching : playable;
  return (pool.find((track) => track.passthrough) ?? pool[0])?.index ?? -1;
}

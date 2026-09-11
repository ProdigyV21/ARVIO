import { runDolbyVisionProbe } from './dolbyVisionProbeClient';

export type DolbyVisionConfig = {
  profile: number;
  level: number;
  compatibilityId: number;
  baseLayer: boolean;
  enhancementLayer: boolean;
  rpu: boolean;
};

export type DolbyVisionProbeResult =
  | { status: 'present'; trackId: number; config: DolbyVisionConfig;
      nalLengthSize: 1 | 2 | 4 | null; codec: 'hevc' | 'other' }
  | { status: 'absent'; trackId: number }
  | { status: 'unknown'; reason: string };

export type DolbyVisionProbeOptions = {
  headers?: Record<string, string>;
  signal?: AbortSignal;
  /** MP4 track_ID or Matroska TrackNumber, not TrackUID or a track array index. */
  trackId?: number;
  maxBytes?: number;
  maxRequests?: number;
  timeoutMs?: number;
};

/** Direct, bounded metadata probe. Unknown is not evidence of absent Dolby Vision. */
export function probeDolbyVision(url: string, options: DolbyVisionProbeOptions = {}): Promise<DolbyVisionProbeResult> {
  return runDolbyVisionProbe(url, options);
}

/** Compatibility alias; container detection is based on parsed data, not the filename. */
export const probeDolbyVisionMp4 = probeDolbyVision;

export function canExtractHdr10BaseLayer(result: DolbyVisionProbeResult): boolean {
  return result.status === 'present' && result.codec === 'hevc'
    && result.config.profile === 8 && result.config.compatibilityId === 1
    && result.config.baseLayer && !result.config.enhancementLayer && result.config.rpu
    && [1, 2, 4].includes(result.nalLengthSize ?? 0);
}

/**
 * Removes only in-band RPU NALs from confirmed single-layer profile 8.1 packets.
 * null means byte-for-byte unchanged; an empty array means an RPU-only packet.
 * Throws on ineligible metadata or malformed/non-single-layer packets. The caller
 * must preserve the original hvcC, full-range flag and color metadata unchanged.
 * This is base-layer extraction, not tone mapping or HEVC transcoding.
 */
export function extractHdr10BaseLayer(packet: Uint8Array, result: DolbyVisionProbeResult): Uint8Array | null {
  if (!canExtractHdr10BaseLayer(result) || result.status !== 'present' || !result.nalLengthSize) {
    throw new Error('HDR10 extraction requires confirmed single-layer Dolby Vision profile 8.1 HEVC.');
  }
  const lengthSize = result.nalLengthSize;
  const nextNal = (start: number) => {
    if (packet.length - start < lengthSize) throw new Error('Truncated HEVC NAL length.');
    let size = 0;
    for (let i = 0; i < lengthSize; i++) size = size * 256 + packet[start + i];
    const payload = start + lengthSize;
    if (size < 2 || size > packet.length - payload) throw new Error('Invalid HEVC NAL length.');
    const first = packet[payload];
    const second = packet[payload + 1];
    if ((first & 0x80) || !(second & 7)) throw new Error('Invalid HEVC NAL header.');
    const type = (first >> 1) & 63;
    const layer = ((first & 1) << 5) | (second >> 3);
    if (layer !== 0 || type === 63) throw new Error('Unexpected enhancement-layer HEVC payload.');
    return { end: payload + size, remove: type === 62 };
  };

  let removed = 0;
  for (let offset = 0; offset < packet.length;) {
    const nal = nextNal(offset);
    if (nal.remove) removed += nal.end - offset;
    offset = nal.end;
  }
  if (!removed) return null;
  const output = new Uint8Array(packet.length - removed);
  let written = 0;
  for (let offset = 0; offset < packet.length;) {
    const nal = nextNal(offset);
    if (!nal.remove) {
      output.set(packet.subarray(offset, nal.end), written);
      written += nal.end - offset;
    }
    offset = nal.end;
  }
  return output;
}

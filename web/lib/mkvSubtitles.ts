// Text subtitles carried inside an MKV (SRT, ASS/SSA, WebVTT), read from the
// bytes the in-browser remux downloads anyway. mediabunny repackages only video
// and audio, so these tracks were lost; this streaming reader watches each
// downloaded range, keeps only the small subtitle blocks and skips everything
// else without copying it. No extra request is made: what has not been
// downloaded yet (far ahead of the playhead) simply arrives later.

export type MkvSubtitleTrack = { number: number; codec: "srt" | "ass" | "vtt"; language?: string; name?: string; isDefault?: boolean; forced?: boolean };
export type MkvSubtitleCue = { track: number; start: number; end: number; text: string };

const ID = {
  segment: 0x18538067, info: 0x1549a966, tracks: 0x1654ae6b, cluster: 0x1f43b675,
  timecodeScale: 0x2ad7b1, trackEntry: 0xae, trackNumber: 0xd7, trackType: 0x83, codecId: 0x86,
  language: 0x22b59c, languageBcp47: 0x22b59d, name: 0x536e, flagDefault: 0x88, flagForced: 0x55aa,
  timestamp: 0xe7, simpleBlock: 0xa3, blockGroup: 0xa0, block: 0xa1, blockDuration: 0x9b
};
const TOP_LEVEL = new Set([0x114d9b74, 0x1549a966, 0x1654ae6b, 0x1c53bb6b, 0x1043a770, 0x1941a469, 0x1254c367, ID.cluster]);
const SUBTITLE_TYPE = 0x11;
const UNKNOWN_SIZE = -1;
const MAX_HEADER = 4 * 1024 * 1024;
const MAX_TEXT_BLOCK = 64 * 1024;
const DEFAULT_DURATION = 4;

/** Reads an EBML element ID (marker bits kept). Returns [id, length] or null when incomplete. */
function readId(bytes: Uint8Array, at: number): [number, number] | null {
  if (at >= bytes.length) return null;
  const first = bytes[at];
  const length = first >= 0x80 ? 1 : first >= 0x40 ? 2 : first >= 0x20 ? 3 : first >= 0x10 ? 4 : 0;
  if (!length) return [-1, 1];
  if (at + length > bytes.length) return null;
  let id = 0;
  for (let index = 0; index < length; index += 1) id = id * 256 + bytes[at + index];
  return [id, length];
}

/** Reads an EBML size (marker bit removed). Returns [size, length]; size -1 = unknown. */
function readSize(bytes: Uint8Array, at: number): [number, number] | null {
  if (at >= bytes.length) return null;
  const first = bytes[at];
  let length = 1;
  while (length <= 8 && !(first & (0x80 >> (length - 1)))) length += 1;
  if (length > 8) return [-2, 1];
  if (at + length > bytes.length) return null;
  let value = first & (0xff >> length);
  let allOnes = value === (0xff >> length);
  for (let index = 1; index < length; index += 1) {
    value = value * 256 + bytes[at + index];
    if (bytes[at + index] !== 0xff) allOnes = false;
  }
  return [allOnes ? UNKNOWN_SIZE : value, length];
}

function readUint(bytes: Uint8Array) { let value = 0; for (const byte of bytes) value = value * 256 + byte; return value; }
const decoder = typeof TextDecoder === "undefined" ? null : new TextDecoder("utf-8");
const text = (bytes: Uint8Array) => decoder?.decode(bytes) ?? "";

/** ASS dialogue payload in Matroska: "ReadOrder,Layer,Style,Name,MarginL,MarginR,MarginV,Effect,Text". */
export function cueText(codec: MkvSubtitleTrack["codec"], payload: string) {
  let value = payload;
  if (codec === "ass") {
    let commas = 0, index = 0;
    for (; index < value.length && commas < 8; index += 1) if (value[index] === ",") commas += 1;
    value = commas === 8 ? value.slice(index) : value;
    value = value.replace(/\{[^}]*\}/g, "").replace(/\\N/gi, "\n").replace(/\\h/g, " ");
  } else {
    value = value.replace(/<\/?(?:font|span)[^>]*>/gi, "").replace(/\{\\[^}]*\}/g, "");
  }
  return value.replace(/\r/g, "").trim();
}

/** Parses Segment > Info and Tracks from the start of the file. */
export function parseMkvHeader(bytes: Uint8Array): { scale: number; tracks: MkvSubtitleTrack[] } | null {
  let at = 0;
  const ebml = readId(bytes, at); const ebmlSize = ebml && readSize(bytes, at + ebml[1]);
  if (!ebml || !ebmlSize || ebml[0] !== 0x1a45dfa3 || ebmlSize[0] < 0) return null;
  at += ebml[1] + ebmlSize[1] + ebmlSize[0];
  const segment = readId(bytes, at); const segmentSize = segment && readSize(bytes, at + segment[1]);
  if (!segment || !segmentSize || segment[0] !== ID.segment) return null;
  at += segment[1] + segmentSize[1];
  let scale = 1_000_000;
  let tracks: MkvSubtitleTrack[] | null = null;
  while (at < bytes.length) {
    const id = readId(bytes, at); const size = id && readSize(bytes, at + id[1]);
    if (!id || !size || size[0] < 0) break;
    const start = at + id[1] + size[1];
    const end = start + size[0];
    if (id[0] === ID.cluster) break;
    if (end > bytes.length) break;
    if (id[0] === ID.info) {
      for (const [child, data] of children(bytes, start, end)) if (child === ID.timecodeScale) scale = readUint(data) || scale;
    } else if (id[0] === ID.tracks) {
      tracks = [];
      for (const [child, entryStart, entryEnd] of childRanges(bytes, start, end)) {
        if (child !== ID.trackEntry) continue;
        const entry: Partial<MkvSubtitleTrack> & { type?: number; codecId?: string } = {};
        for (const [field, data] of children(bytes, entryStart, entryEnd)) {
          if (field === ID.trackNumber) entry.number = readUint(data);
          else if (field === ID.trackType) entry.type = readUint(data);
          else if (field === ID.codecId) entry.codecId = text(data).replace(/\0+$/, "");
          else if (field === ID.languageBcp47) entry.language = text(data).replace(/\0+$/, "");
          else if (field === ID.language && !entry.language) entry.language = text(data).replace(/\0+$/, "");
          else if (field === ID.name) entry.name = text(data).replace(/\0+$/, "");
          else if (field === ID.flagDefault) entry.isDefault = readUint(data) === 1;
          else if (field === ID.flagForced) entry.forced = readUint(data) === 1;
        }
        const codec = entry.codecId === "S_TEXT/UTF8" ? "srt" : /^S_TEXT\/(ASS|SSA)$/.test(entry.codecId ?? "") ? "ass" : entry.codecId === "S_TEXT/WEBVTT" ? "vtt" : null;
        if (entry.type === SUBTITLE_TYPE && codec && entry.number) {
          tracks.push({ number: entry.number, codec, language: entry.language && entry.language !== "und" ? entry.language : undefined, name: entry.name, isDefault: entry.isDefault, forced: entry.forced });
        }
      }
    }
    at = end;
  }
  return tracks ? { scale, tracks } : null;
}

function* childRanges(bytes: Uint8Array, start: number, end: number): Generator<[number, number, number]> {
  let at = start;
  while (at < end) {
    const id = readId(bytes, at); const size = id && readSize(bytes, at + id[1]);
    if (!id || !size || size[0] < 0) return;
    const dataStart = at + id[1] + size[1];
    if (dataStart + size[0] > end) return;
    yield [id[0], dataStart, dataStart + size[0]];
    at = dataStart + size[0];
  }
}
function* children(bytes: Uint8Array, start: number, end: number): Generator<[number, Uint8Array]> {
  for (const [id, from, to] of childRanges(bytes, start, end)) yield [id, bytes.subarray(from, to)];
}

type Block = { track: number; relative: number; payload: Uint8Array };
/** A Block/SimpleBlock body: track vint, int16 relative time, flags, data (laced blocks skipped). */
function parseBlock(bytes: Uint8Array): Block | null {
  const track = readSize(bytes, 0);
  if (!track || track[0] < 0 || bytes.length < track[1] + 3) return null;
  const relative = ((bytes[track[1]] << 8) | bytes[track[1] + 1]) << 16 >> 16;
  const flags = bytes[track[1] + 2];
  if (flags & 0x06) return null;
  return { track: track[0], relative, payload: bytes.subarray(track[1] + 3) };
}

/**
 * Streaming reader for one downloaded range at a time. Ranges may start
 * anywhere; the reader waits for the next Cluster and follows the file from
 * there. A range that continues exactly where another stopped resumes it.
 */
export class MkvSubtitleTap {
  private header: Uint8Array[] = [];
  private headerBytes = 0;
  private scale = 1_000_000;
  private tracks = new Map<number, MkvSubtitleTrack>();
  private ready = false;
  private parsers = new Map<number, StreamParser>();
  private pending: MkvSubtitleCue[] = [];

  constructor(private onTracks: (tracks: MkvSubtitleTrack[]) => void, private onCues: (cues: MkvSubtitleCue[]) => void) {}

  /** Bytes of one response, in order. `offset` = absolute position of `chunk[0]`. */
  feed(offset: number, chunk: Uint8Array) {
    if (!this.ready && offset === this.headerBytes && this.headerBytes < MAX_HEADER) {
      this.header.push(chunk.slice(0, MAX_HEADER - this.headerBytes));
      this.headerBytes += Math.min(chunk.length, MAX_HEADER - this.headerBytes);
      const joined = new Uint8Array(this.headerBytes);
      let at = 0;
      for (const part of this.header) { joined.set(part, at); at += part.length; }
      const parsed = parseMkvHeader(joined);
      if (parsed) {
        this.ready = true;
        this.header = [];
        this.scale = parsed.scale;
        for (const track of parsed.tracks) this.tracks.set(track.number, track);
        this.onTracks(parsed.tracks);
      }
    }
    if (!this.ready || !this.tracks.size) return;
    const parser = this.parsers.get(offset) ?? new StreamParser(offset, (cue) => this.pending.push(cue), this.tracks, this.scale);
    this.parsers.delete(offset);
    parser.feed(chunk);
    // Bound the resumable states: old ones belong to ranges that never continue.
    this.parsers.set(parser.position, parser);
    while (this.parsers.size > 16) this.parsers.delete(this.parsers.keys().next().value!);
    this.flush();
  }

  flush() {
    if (!this.pending.length) return;
    const cues = this.pending;
    this.pending = [];
    this.onCues(cues);
  }
}

class StreamParser {
  /** Absolute position of the next byte this parser expects. */
  position: number;
  private carry: Uint8Array = new Uint8Array(0);
  private skip = 0;
  private inCluster = false;
  private clusterEnd = Infinity;
  private clusterTime = 0;
  private group: { end: number; block?: Block; duration?: number } | null = null;
  private collect: { id: number; size: number; parts: Uint8Array[]; got: number } | null = null;

  constructor(start: number, private emit: (cue: MkvSubtitleCue) => void, private tracks: Map<number, MkvSubtitleTrack>, private scale: number) {
    this.position = start;
  }

  feed(chunk: Uint8Array) {
    let bytes = chunk;
    let base = this.position;
    if (this.carry.length) {
      const joined = new Uint8Array(this.carry.length + chunk.length);
      joined.set(this.carry); joined.set(chunk, this.carry.length);
      bytes = joined; base -= this.carry.length; this.carry = new Uint8Array(0);
    }
    this.position = base + bytes.length;
    let at = 0;
    while (at < bytes.length) {
      if (this.skip) { const step = Math.min(this.skip, bytes.length - at); this.skip -= step; at += step; continue; }
      if (this.collect) {
        const step = Math.min(this.collect.size - this.collect.got, bytes.length - at);
        this.collect.parts.push(bytes.slice(at, at + step));
        this.collect.got += step; at += step;
        if (this.collect.got === this.collect.size) this.finishCollect(base + at);
        continue;
      }
      const absolute = base + at;
      if (this.group && absolute >= this.group.end) this.finishGroup();
      if (this.inCluster && absolute >= this.clusterEnd) this.inCluster = false;
      if (!this.inCluster) {
        // Find the next Cluster ID (1F 43 B6 75).
        const found = findCluster(bytes, at);
        if (found < 0) { this.carry = bytes.slice(Math.max(at, bytes.length - 3)); return; }
        at = found;
      }
      const id = readId(bytes, at); const size = id && readSize(bytes, at + (id?.[1] ?? 0));
      if (!id || !size) { this.carry = bytes.slice(at); return; }
      const dataStart = at + id[1] + size[1];
      if (id[0] === ID.cluster) {
        this.finishGroup();
        this.inCluster = true;
        this.clusterTime = 0;
        this.clusterEnd = size[0] < 0 ? Infinity : base + dataStart + size[0];
        at = dataStart;
        continue;
      }
      if (!this.inCluster || id[0] < 0 || size[0] < 0 || TOP_LEVEL.has(id[0])) {
        // Not inside a cluster we understand: resynchronise on the next Cluster.
        this.inCluster = false;
        this.group = null;
        at += 1;
        continue;
      }
      at = dataStart;
      if (id[0] === ID.blockGroup) { this.finishGroup(); this.group = { end: base + dataStart + size[0] }; continue; }
      if (id[0] === ID.timestamp || id[0] === ID.blockDuration) { this.collect = { id: id[0], size: size[0], parts: [], got: 0 }; if (!size[0]) this.finishCollect(base + at); continue; }
      if (id[0] === ID.simpleBlock || id[0] === ID.block) {
        // Peek at the track number; only subtitle blocks are copied.
        const track = readSize(bytes, at);
        if (!track) { this.carry = bytes.slice(at - (id[1] + size[1])); this.position = base + bytes.length; return; }
        if (this.tracks.has(track[0]) && size[0] <= MAX_TEXT_BLOCK) { this.collect = { id: id[0], size: size[0], parts: [], got: 0 }; continue; }
        this.skip = size[0];
        continue;
      }
      this.skip = size[0];
    }
  }

  private finishCollect(absoluteEnd: number) {
    const collect = this.collect!;
    this.collect = null;
    const data = new Uint8Array(collect.size);
    let at = 0;
    for (const part of collect.parts) { data.set(part, at); at += part.length; }
    if (collect.id === ID.timestamp) this.clusterTime = readUint(data);
    else if (collect.id === ID.blockDuration) { if (this.group) this.group.duration = readUint(data); }
    else {
      const block = parseBlock(data);
      if (!block) return;
      if (collect.id === ID.block && this.group) this.group.block = block;
      else this.emitBlock(block, undefined);
    }
    if (this.group && absoluteEnd >= this.group.end) this.finishGroup();
  }

  private finishGroup() {
    const group = this.group;
    this.group = null;
    if (group?.block) this.emitBlock(group.block, group.duration);
  }

  private emitBlock(block: Block, duration: number | undefined) {
    const track = this.tracks.get(block.track);
    if (!track) return;
    const unit = this.scale / 1e9;
    const start = (this.clusterTime + block.relative) * unit;
    const end = duration ? start + duration * unit : start + DEFAULT_DURATION;
    const value = cueText(track.codec, text(block.payload));
    if (value && start >= 0 && end > start) this.emit({ track: track.number, start, end, text: value });
  }
}

function findCluster(bytes: Uint8Array, from: number) {
  for (let index = from; index + 3 < bytes.length; index += 1) {
    if (bytes[index] === 0x1f && bytes[index + 1] === 0x43 && bytes[index + 2] === 0xb6 && bytes[index + 3] === 0x75) return index;
  }
  return -1;
}

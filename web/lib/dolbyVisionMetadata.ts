import { Box, BoxParser, createFile, type Movie, type MultiBufferStream } from 'mp4box';
// The raw decoder's transitive ebml browser entry is an IIFE without exports.
// Use the publisher's standalone bundle, which includes its browser dependencies.
import { Decoder as EBMLDecoder } from 'ts-ebml/dist/EBML.js';
import type { EBMLElementDetail } from 'ts-ebml/lib/EBML';
import type { DolbyVisionConfig, DolbyVisionProbeOptions, DolbyVisionProbeResult } from './dolbyVision';

const MAX_BYTES = 8 * 1024 * 1024;
const MAX_REQUESTS = 32;
const CHUNK_BYTES = 256 * 1024;
const unknown = (reason: string): DolbyVisionProbeResult => ({ status: 'unknown', reason });
const hevcEntries = new Set(['hvc1', 'hev1', 'dvh1', 'dvhe']);
const dvEntries = new Set(['dvh1', 'dvhe', 'dvav', 'dva1', 'dav1']);

const sampleTables = [
  'stsz', 'stz2', 'stts', 'ctts', 'cslg', 'stsc', 'stco', 'co64', 'stss', 'stsh',
  'stdp', 'sdtp', 'padb', 'subs', 'saiz', 'saio', 'senc', 'sbgp', 'sgpd',
  'edts', 'elst', 'moof', 'mfra', 'trun', 'sidx', 'tfra', 'meta'
];

function metadataOnlyMp4() {
  const file = createFile(false);
  // onReady/getInfo need track metadata, not millions of expanded sample objects.
  file.buildSampleLists = () => {
    for (const track of file.moov?.traks ?? []) {
      track.samples = [];
      track.samples_size = 0;
      track.samples_duration = 0;
    }
  };
  file.updateSampleLists = () => {};
  file.flattenItemInfo = () => {};

  const append = (buffer: ArrayBuffer & { fileStart: number }, resourceSize: number) => {
    // mp4box 2.4.1 exposes one registry per realm. Replace only sample-table
    // readers during this synchronous append, and restore it even on failure.
    // This avoids prototype changes and cannot span another probe's fetch await.
    const registry = BoxParser as unknown as { box: Record<string, typeof Box> };
    const original = registry.box;
    const metadataBoxes = { ...original };
    for (const type of sampleTables) {
      metadataBoxes[type] = class extends Box {
        constructor(size?: number) { super(size); this.type = type; }
        write(): never { throw new Error('Metadata-only boxes cannot be written.'); }
        parse(stream: MultiBufferStream) {
          const end = this.start! + this.size;
          if (type === 'stsz' || type === 'stz2') {
            if (end - stream.getPosition() < 12) throw new Error('Truncated sample size table.');
            if (stream.readUint32() !== 0) throw new Error('Unsupported sample size table version/flags.');
            const field = stream.readUint32();
            const count = stream.readUint32();
            if (type === 'stsz') {
              // A constant-size table has no entries to read. Its declared media
              // cannot exceed the whole resource; this is not a duration/count cap.
              if (field ? count > Math.floor(resourceSize / field) || stream.getPosition() !== end
                : count * 4 !== end - stream.getPosition()) throw new Error('Invalid sample size table.');
            } else {
              if (![4, 8, 16].includes(field) || Math.ceil(count * field / 8) !== end - stream.getPosition()) {
                throw new Error('Invalid compact sample size table.');
              }
            }
          }
          stream.seek(end);
        }
      };
    }
    registry.box = metadataBoxes;
    try { return file.appendBuffer(buffer); }
    finally { registry.box = original; }
  };
  return { file, append };
}

// DOVIDecoderConfigurationRecord, not an ISO box-header scan:
// https://github.com/gpac/gpac/blob/master/src/isomedia/box_code_base.c (dvcC_box_read).
function parseConfig(data: Uint8Array): DolbyVisionConfig | null {
  if (data.length !== 24 || data[0] !== 1 || data[1] !== 0) return null;
  // New compression/features/reserved bits are not supported by this extraction path.
  if ((data[4] & 15) || data.subarray(5).some((byte) => byte !== 0)) return null;
  const profile = data[2] >> 1;
  const level = ((data[2] & 1) << 5) | (data[3] >> 3);
  if (!profile || !level) return null;
  return { profile, level, compatibilityId: data[4] >> 4,
    baseLayer: !!(data[3] & 1), enhancementLayer: !!(data[3] & 2), rpu: !!(data[3] & 4) };
}

function lengthSize(value: number): 1 | 2 | 4 | null {
  return value === 1 || value === 2 || value === 4 ? value : null;
}

function completeChildren(box: Box, children: Box[] | undefined, prefix = 0): boolean {
  if (box.start === undefined || box.hdr_size === undefined || !children) return false;
  let position = box.start + box.hdr_size + prefix;
  for (const child of children) {
    if (child.start !== position || !Number.isSafeInteger(child.size) || child.size < (child.hdr_size ?? 8)) return false;
    position += child.size;
  }
  return position === box.start + box.size;
}

function mp4Result(file: ReturnType<typeof createFile>, info: Movie, trackId?: number): DolbyVisionProbeResult {
  const tracks = info.videoTracks;
  if (new Set(tracks.map((track) => track.id)).size !== tracks.length) return unknown('duplicate-track-id');
  const selected = trackId === undefined ? (tracks.length === 1 ? tracks[0] : undefined)
    : tracks.find((track) => track.id === trackId);
  if (!selected) return unknown('video-track-not-resolved');
  const trak = file.getTrackById(selected.id);
  const stsd = trak?.mdia?.minf?.stbl?.stsd;
  const entries = stsd?.entries;
  // mp4box can omit a malformed child and still call onReady. Check the parsed
  // tree accounts for all bytes, including the sample entry's fixed visual fields.
  if ([file.moov, trak, trak?.mdia, trak?.mdia?.minf, trak?.mdia?.minf?.stbl]
    .some((box) => !box || !completeChildren(box, box.boxes))
    || !stsd || !completeChildren(stsd, entries, 4)) return unknown('incomplete-sample-description');
  // A changing sample description cannot safely share one packet rewrite policy.
  if (entries?.length !== 1) return unknown('ambiguous-sample-description');
  const entry = entries[0];
  if (!entry.boxes || !completeChildren(entry, entry.boxes, 70) || entry.type === 'encv') return unknown('unsupported-sample-description');
  const configs = entry.boxes.filter((box: Box) => ['dvcC', 'dvvC', 'dvwC'].includes(box.type));
  if (!configs.length) return dvEntries.has(entry.type) ? unknown('missing-dv-configuration')
    : { status: 'absent', trackId: selected.id };
  if (configs.length !== 1 || configs[0].type === 'dvwC') return unknown('unsupported-dv-configuration');
  const config = parseConfig(Uint8Array.from(configs[0].data ?? []));
  if (!config) return unknown('invalid-dv-configuration');
  const hvcC = 'hvcC' in entry ? entry.hvcC as { configurationVersion?: number; lengthSizeMinusOne?: number } : undefined;
  return { status: 'present', trackId: selected.id, config,
    codec: hevcEntries.has(entry.type) ? 'hevc' : 'other',
    nalLengthSize: hvcC?.configurationVersion === 1 ? lengthSize((hvcC.lengthSizeMinusOne ?? -1) + 1) : null };
}

type Mapping = { type?: number; extra?: Uint8Array };
type MkvTrack = { id?: number; type?: number; codec?: string; privateData?: Uint8Array;
  encrypted?: boolean; mappings: Mapping[] };

function mkvResult(tracks: MkvTrack[], trackId?: number): DolbyVisionProbeResult {
  if (tracks.some((track) => !Number.isSafeInteger(track.id) || !track.id || !track.type || !track.codec)
    || new Set(tracks.map((track) => track.id)).size !== tracks.length) return unknown('invalid-tracks');
  const videos = tracks.filter((track) => track.type === 1);
  const selected = trackId === undefined ? (videos.length === 1 ? videos[0] : undefined)
    : videos.find((track) => track.id === trackId);
  if (!selected?.id) return unknown('video-track-not-resolved');
  if (selected.encrypted) return unknown('encoded-track-content');
  // https://www.matroska.org/technical/block_additional_mappings.html
  const configs = selected.mappings.filter(({ type }) => [0x64766343, 0x64767643, 0x64767743].includes(type ?? 0));
  if (selected.mappings.some(({ type }) => type === undefined || [0x68766345, 0x61766345].includes(type))) {
    return unknown('unsupported-block-addition');
  }
  if (!configs.length) return { status: 'absent', trackId: selected.id };
  if (configs.length !== 1 || configs[0].type === 0x64767743) return unknown('unsupported-dv-configuration');
  const config = configs[0].extra && parseConfig(configs[0].extra);
  if (!config) return unknown('invalid-dv-configuration');
  const data = selected.privateData;
  // CodecPrivate is HEVCDecoderConfigurationRecord, parsed by the same ISO parser.
  let nalLengthSize: 1 | 2 | 4 | null = null;
  if (selected.codec === 'V_MPEGH/ISO/HEVC' && data && data.length >= 23 && data[0] === 1) {
    const file = createFile(false);
    try {
      const id = file.addTrack({ type: 'hvc1', hevcDecoderConfigRecord: Uint8Array.from(data).buffer });
      const entry = file.getTrackById(id).mdia.minf.stbl.stsd.entries[0];
      const hvcC = 'hvcC' in entry ? entry.hvcC as { lengthSizeMinusOne: number } : undefined;
      nalLengthSize = hvcC ? lengthSize(hvcC.lengthSizeMinusOne + 1) : null;
    } catch { /* A valid DV record does not make a truncated hvcC usable. */ }
  }
  return { status: 'present', trackId: selected.id, config, nalLengthSize,
    codec: selected.codec === 'V_MPEGH/ISO/HEVC' ? 'hevc' : 'other' };
}

function matroskaParser(trackId?: number) {
  const decoder = new EBMLDecoder();
  const stack: EBMLElementDetail[] = [];
  const tracks: MkvTrack[] = [];
  let current: MkvTrack | undefined;
  let mapping: Mapping | undefined;
  let docType: string | undefined;
  let sawTracks = false;
  return (data: Uint8Array): DolbyVisionProbeResult | undefined => {
    for (const element of decoder.decode(Uint8Array.from(data).buffer)) {
      const parent = stack[stack.length - 1];
      if (element.type === 'm' && element.isEnd) {
        if (parent?.name !== element.name || parent.dataEnd !== element.dataEnd) return unknown('invalid-ebml-nesting');
        stack.pop();
        if (element.name === 'BlockAdditionMapping') {
          if (!current || !mapping) return unknown('invalid-block-addition');
          current.mappings.push(mapping); mapping = undefined;
        } else if (element.name === 'TrackEntry') {
          if (!current) return unknown('invalid-tracks');
          tracks.push(current); current = undefined;
        } else if (element.name === 'Tracks') {
          return mkvResult(tracks, trackId);
        }
        continue;
      }
      if (parent && parent.dataEnd >= 0 && element.dataEnd > parent.dataEnd) return unknown('invalid-ebml-size');
      if (element.type === 'm') {
        if (stack.length > 32) return unknown('ebml-depth-limit');
        if (element.unknownSize && element.name !== 'Segment') return unknown('unknown-metadata-size');
        if (element.name === 'Cluster') return unknown('tracks-not-before-cluster');
        if (element.name === 'Tracks') {
          if (parent?.name !== 'Segment' || sawTracks || !['matroska', 'webm'].includes(docType ?? '')) return unknown('invalid-tracks');
          sawTracks = true;
        } else if (element.name === 'TrackEntry') {
          if (parent?.name !== 'Tracks' || current) return unknown('invalid-tracks');
          current = { mappings: [] };
        } else if (element.name === 'BlockAdditionMapping') {
          if (parent?.name !== 'TrackEntry' || mapping) return unknown('invalid-block-addition');
          mapping = {};
        } else if (element.name === 'ContentEncodings' && current) current.encrypted = true;
        stack.push(element);
      } else {
        if (element.name === 'DocType' && parent?.name === 'EBML' && element.type === 's') docType = element.value;
        if (current && parent?.name === 'TrackEntry') {
          if (element.name === 'TrackNumber' && element.type === 'u') { if (current.id !== undefined) return unknown('duplicate-track-field'); current.id = element.value; }
          if (element.name === 'TrackType' && element.type === 'u') { if (current.type !== undefined) return unknown('duplicate-track-field'); current.type = element.value; }
          if (element.name === 'CodecID' && element.type === 's') { if (current.codec !== undefined) return unknown('duplicate-track-field'); current.codec = element.value; }
          if (element.name === 'CodecPrivate' && element.type === 'b') { if (current.privateData) return unknown('duplicate-track-field'); current.privateData = element.data; }
        }
        if (mapping && parent?.name === 'BlockAdditionMapping') {
          if (element.name === 'BlockAddIDType' && element.type === 'u') { if (mapping.type !== undefined) return unknown('duplicate-mapping-field'); mapping.type = element.value; }
          if (element.name === 'BlockAddIDExtraData' && element.type === 'b') { if (mapping.extra) return unknown('duplicate-mapping-field'); mapping.extra = element.data; }
        }
      }
    }
    return undefined;
  };
}

function bounded(value: number | undefined, limit: number): number {
  if (value === undefined) return limit;
  if (!Number.isSafeInteger(value) || value <= 0) throw new Error('invalid-options');
  return Math.min(value, limit);
}

function abortable<T>(operation: Promise<T>, signal: AbortSignal): Promise<T> {
  return new Promise((resolve, reject) => {
    const abort = () => reject(new Error('aborted'));
    if (signal.aborted) { operation.catch(() => {}); abort(); return; }
    signal.addEventListener('abort', abort, { once: true });
    operation.then(resolve, reject).finally(() => signal.removeEventListener('abort', abort));
  });
}

/** Worker-only implementation; callers must use probeDolbyVision for a hard parser deadline. */
export async function probeDolbyVisionMetadata(url: string, options: DolbyVisionProbeOptions = {}): Promise<DolbyVisionProbeResult> {
  const controller = new AbortController();
  const abort = () => controller.abort();
  let timer: ReturnType<typeof setTimeout> | undefined;
  let timedOut = false;
  try {
    const parsedUrl = new URL(url);
    if (!['https:', 'http:'].includes(parsedUrl.protocol) || parsedUrl.username || parsedUrl.password) return unknown('invalid-url');
    if (parsedUrl.pathname.startsWith('/.netlify/functions/')) return unknown('proxy-not-supported');
    const maxBytes = bounded(options.maxBytes, MAX_BYTES);
    const maxRequests = bounded(options.maxRequests, MAX_REQUESTS);
    const timeoutMs = bounded(options.timeoutMs, 10_000);
    if (options.trackId !== undefined && (!Number.isSafeInteger(options.trackId) || options.trackId <= 0)) return unknown('invalid-track-id');
    if (options.signal?.aborted) return unknown('aborted');
    options.signal?.addEventListener('abort', abort, { once: true });
    timer = setTimeout(() => { timedOut = true; abort(); }, timeoutMs);
    const { file, append } = metadataOnlyMp4();
    let ready: DolbyVisionProbeResult | undefined;
    let parserError = false;
    file.onError = () => { parserError = true; };
    file.onReady = (info) => { ready = mp4Result(file, info, options.trackId); };
    let mkv: ReturnType<typeof matroskaParser> | undefined;
    let total: number | undefined;
    let validator: string | null | undefined;
    let offset = 0;
    let transferred = 0;
    for (let request = 0; request < maxRequests && transferred < maxBytes; request++) {
      const end = Math.min(offset + Math.min(CHUNK_BYTES, maxBytes - transferred) - 1, (total ?? Number.MAX_SAFE_INTEGER) - 1);
      const headers = new Headers(options.headers);
      headers.set('Range', `bytes=${offset}-${end}`);
      const response = await abortable(fetch(url, { headers, signal: controller.signal, credentials: 'omit', cache: 'no-store' }), controller.signal);
      const cancelBody = () => { void response.body?.cancel().catch(() => {}); };
      if (response.status !== 206) { cancelBody(); return unknown('range-not-supported'); }
      const rangeHeader = response.headers.get('Content-Range');
      // Content-Range is not CORS-safelisted. A readable 206 body can contain
      // complete Matroska Tracks even when the CDN does not expose this header.
      // Only inspect one bounded, self-identifying prefix in that case; never
      // guess byte offsets or the total size for subsequent range requests.
      const prefixOnly = rangeHeader === null && offset === 0;
      const range = /^bytes (\d+)-(\d+)\/(\d+)$/.exec(rangeHeader ?? '');
      const start = prefixOnly ? 0 : Number(range?.[1]);
      const last = prefixOnly ? end : Number(range?.[2]);
      const size = Number(range?.[3]);
      if (!prefixOnly && (!range || ![start, last, size].every(Number.isSafeInteger) || start !== offset || last < start || last > end || size <= last
        || (total !== undefined && total !== size))) { cancelBody(); return unknown('invalid-content-range'); }
      const encoding = response.headers.get('Content-Encoding');
      if (encoding && encoding !== 'identity') { cancelBody(); return unknown('encoded-range-response'); }
      const currentValidator = response.headers.get('ETag') ?? response.headers.get('Last-Modified');
      if (validator !== undefined && validator !== currentValidator) { cancelBody(); return unknown('resource-changed'); }
      validator = currentValidator;
      if (!prefixOnly) total = size;
      const reader = response.body?.getReader();
      if (!reader) return unknown('missing-range-body');
      const bytes = new Uint8Array(last - start + 1);
      let read = 0;
      try {
        while (true) {
          const part = await abortable(reader.read(), controller.signal);
          if (part.done) break;
          if (part.value.byteLength > bytes.length - read) return unknown('oversized-range-body');
          bytes.set(part.value, read); read += part.value.byteLength;
        }
      } finally { void reader.cancel().catch(() => {}); }
      if (!prefixOnly && read !== bytes.length) return unknown('truncated-range-body');
      transferred += read;
      if (offset === 0 && read >= 4 && bytes[0] === 0x1a && bytes[1] === 0x45 && bytes[2] === 0xdf && bytes[3] === 0xa3) {
        mkv = matroskaParser(options.trackId);
      }
      if (prefixOnly) {
        return mkv?.(bytes.subarray(0, read)) ?? unknown('missing-content-range');
      }
      let next: number;
      if (mkv) { ready = mkv(bytes); next = offset + read; }
      else {
        const buffer = bytes.buffer as ArrayBuffer & { fileStart: number };
        buffer.fileStart = offset;
        next = append(buffer, total!);
        if (parserError) return unknown('invalid-mp4');
      }
      if (ready) return ready;
      if (!Number.isSafeInteger(next) || next <= offset || next >= total!) return unknown('incomplete-metadata');
      offset = next;
    }
    return unknown('metadata-budget-exceeded');
  } catch (error) {
    return unknown(timedOut ? 'timeout' : controller.signal.aborted ? 'aborted'
      : error instanceof Error && error.message === 'invalid-options' ? 'invalid-options' : 'probe-failed');
  } finally {
    clearTimeout(timer);
    options.signal?.removeEventListener('abort', abort);
    controller.abort();
  }
}

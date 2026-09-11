const mp4box = require('mp4box');
const { Encoder, tools } = require('ts-ebml');

function dvConfig({ profile = 8, level = 6, compatibilityId = 1, baseLayer = true,
  enhancementLayer = false, rpu = true } = {}) {
  const data = Buffer.alloc(24);
  data[0] = 1;
  data[2] = (profile << 1) | (level >> 5);
  data[3] = ((level & 31) << 3) | (rpu ? 4 : 0) | (enhancementLayer ? 2 : 0) | (baseLayer ? 1 : 0);
  data[4] = compatibilityId << 4;
  return data;
}

function hvcc(lengthSize = 4) {
  const data = Buffer.alloc(23);
  data[0] = 1;
  data[1] = 2;
  data[12] = 120;
  data[13] = 0xf0;
  data[15] = 0xfc;
  data[16] = 0xfd;
  data[17] = 0xfa;
  data[18] = 0xfa;
  data[21] = 0x0c | (lengthSize - 1);
  return data;
}

function rawBox(type, data) {
  const box = new mp4box.Box();
  box.type = type;
  box.data = Uint8Array.from(data);
  return box;
}

function mp4Fixture(tracks = [{}], mutate) {
  const file = mp4box.createFile();
  for (const [index, track] of tracks.entries()) {
    const data = track.hvcc ?? hvcc(track.lengthSize);
    const boxes = track.absent ? [] : [rawBox(track.boxType ?? 'dvvC', track.config ?? dvConfig())];
    boxes.push(...(track.boxes ?? []));
    file.addTrack({ type: track.type ?? 'hvc1', id: track.id ?? index + 1,
      hevcDecoderConfigRecord: data.buffer.slice(data.byteOffset, data.byteOffset + data.length),
      description_boxes: boxes, width: 3840, height: 2160 });
  }
  mutate?.(file);
  return Buffer.from(file.getBuffer().buffer);
}

const master = (name, children) => [{ name, type: 'm', isEnd: false }, ...children, { name, type: 'm', isEnd: true }];
const uint = (name, value) => ({ name, type: 'u', data: tools.createUIntBuffer(value) });
const string = (name, value) => ({ name, type: 's', data: Buffer.from(value) });
const binary = (name, data) => ({ name, type: 'b', data: Buffer.from(data) });

function mkvFixture(tracks = [{}], { unknownSegment = false, beforeTracks = [], afterTracks = [] } = {}) {
  const elements = tracks.flatMap((track, index) => master('TrackEntry', [
    uint('TrackNumber', track.id ?? index + 1), uint('TrackUID', 1000 + index), uint('TrackType', track.trackType ?? 1),
    string('CodecID', track.codec ?? 'V_MPEGH/ISO/HEVC'), binary('CodecPrivate', track.hvcc ?? hvcc(track.lengthSize)),
    ...master('Video', [uint('PixelWidth', 3840), uint('PixelHeight', 2160)]),
    ...(track.absent ? [] : master('BlockAdditionMapping', [
      uint('BlockAddIDValue', 2), uint('BlockAddIDType', track.mappingType ?? 0x64767643),
      ...(track.missingExtra ? [] : [binary('BlockAddIDExtraData', track.config ?? dvConfig())])
    ])),
    ...(track.extra ?? [])
  ]));
  const contents = [...beforeTracks, ...master('Info', [uint('TimestampScale', 1000000)]),
    ...master('Tracks', elements), ...afterTracks];
  const segment = unknownSegment ? [{ name: 'Segment', type: 'm', isEnd: false, unknownSize: true }, ...contents]
    : master('Segment', contents);
  const encoder = new Encoder();
  // encode() exposes a pooled Node Buffer's entire ArrayBuffer; encodeChunk keeps the exact views.
  return Buffer.concat([
    ...master('EBML', [uint('EBMLVersion', 1), uint('EBMLReadVersion', 1), uint('EBMLMaxIDLength', 4),
      uint('EBMLMaxSizeLength', 8), string('DocType', 'matroska'), uint('DocTypeVersion', 4), uint('DocTypeReadVersion', 2)]),
    ...segment
  ].flatMap((element) => encoder.encodeChunk(element)));
}

function nal(type, lengthSize = 4, payload = [0xab, 0xcd], layer = 0) {
  const body = Buffer.from([(type << 1) | (layer >> 5), ((layer & 31) << 3) | 1, ...payload]);
  const prefix = Buffer.alloc(lengthSize);
  prefix.writeUIntBE(body.length, 0, lengthSize);
  return Buffer.concat([prefix, body]);
}

module.exports = { dvConfig, hvcc, rawBox, mp4Fixture, mkvFixture, master, uint, binary, nal };

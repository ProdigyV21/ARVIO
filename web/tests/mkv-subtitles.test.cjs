const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const mkv = load('lib/mkvSubtitles.ts', {}, { TextDecoder });

// Minimal EBML writer: element(id, children-or-bytes).
const idBytes = (id) => { const out = []; let value = id; while (value > 0) { out.unshift(value & 0xff); value = Math.floor(value / 256); } return out; };
const sizeBytes = (size) => (size < 0x7f ? [0x80 | size] : [0x40 | (size >> 8), size & 0xff]);
const el = (id, body) => { const data = Array.isArray(body) ? body.flat(Infinity) : [...body]; return [...idBytes(id), ...sizeBytes(data.length), ...data]; };
const uint = (value) => (value < 256 ? [value] : [value >> 8, value & 0xff]);
const str = (value) => [...Buffer.from(value, 'utf8')];
const block = (track, rel, payload) => [0x80 | track, (rel >> 8) & 0xff, rel & 0xff, 0, ...payload];

function file() {
  const header = el(0x1a45dfa3, el(0x4282, str('matroska')));
  const info = el(0x1549a966, el(0x2ad7b1, [0x0f, 0x42, 0x40])); // 1 ms
  const tracks = el(0x1654ae6b, [
    el(0xae, [el(0xd7, uint(1)), el(0x83, uint(1)), el(0x86, str('V_MPEG4/ISO/AVC'))]),
    el(0xae, [el(0xd7, uint(2)), el(0x83, uint(0x11)), el(0x86, str('S_TEXT/UTF8')), el(0x22b59c, str('dut'))]),
    el(0xae, [el(0xd7, uint(3)), el(0x83, uint(0x11)), el(0x86, str('S_TEXT/ASS')), el(0x536e, str('Signs'))])
  ]);
  const cluster = (time, items) => el(0x1f43b675, [el(0xe7, uint(time)), ...items]);
  const video = new Array(300).fill(7);
  const clusters = [
    cluster(1000, [el(0xa3, block(1, 0, video)), el(0xa0, [el(0xa1, block(2, 500, str('<i>Hallo</i> daar'))), el(0x9b, uint(2000))]), el(0xa3, block(1, 40, video))]),
    cluster(5000, [el(0xa3, block(3, 0, str('0,0,Default,,0,0,0,,{\\an8}Line one\\Nline two'))), el(0xa3, block(1, 0, video))])
  ];
  // Segment of unknown size (as streamed MKVs often are), then its children.
  return Uint8Array.from([...header, 0x18, 0x53, 0x80, 0x67, 0x01, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, ...info, ...tracks, ...clusters.flat()]);
}

function run(chunkSize) {
  const bytes = file();
  const found = { tracks: [], cues: [] };
  const tap = new mkv.MkvSubtitleTap((tracks) => found.tracks.push(...tracks), (cues) => found.cues.push(...cues));
  for (let at = 0; at < bytes.length; at += chunkSize) tap.feed(at, bytes.subarray(at, Math.min(bytes.length, at + chunkSize)));
  return found;
}

test('text subtitle tracks and their cues come out of the downloaded bytes', () => {
  for (const size of [1, 7, 64, 100000]) {
    const { tracks, cues } = run(size);
    assert.deepEqual(tracks.map((t) => [t.number, t.codec, t.language ?? null, t.name ?? null]), [[2, 'srt', 'dut', null], [3, 'ass', null, 'Signs']], `chunk ${size}`);
    assert.equal(cues.length, 2, `chunk ${size}`);
    assert.deepEqual({ ...cues[0] }, { track: 2, start: 1.5, end: 3.5, text: '<i>Hallo</i> daar' });
    assert.equal(cues[1].text, 'Line one\nline two');
    assert.equal(cues[1].start, 5);
  }
});

test('a range starting mid-file waits for the next cluster', () => {
  const bytes = file();
  const cues = [];
  const tap = new mkv.MkvSubtitleTap(() => {}, (found) => cues.push(...found));
  tap.feed(0, bytes.subarray(0, 200));
  const second = bytes.indexOf(0x1f, bytes.length - 400);
  tap.feed(bytes.length - 330, bytes.subarray(bytes.length - 330));
  assert.ok(cues.every((cue) => cue.track === 3 || cue.start === 1.5));
  assert.ok(second > 0);
});

test('ASS override tags are removed and SRT font tags stripped', () => {
  assert.equal(mkv.cueText('ass', '1,0,Default,,0,0,0,,{\\b1}Bold{\\b0} text'), 'Bold text');
  assert.equal(mkv.cueText('srt', '<font color="#fff">White</font>'), 'White');
});

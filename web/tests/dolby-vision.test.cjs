const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const { gunzipSync } = require('node:zlib');
const { spawnSync } = require('node:child_process');
const ts = require('typescript');
const mp4box = require('mp4box');
const { load } = require('./load.cjs');
const { dvConfig, hvcc, rawBox, mp4Fixture, mkvFixture, master, uint, binary, nal } = require('./dolby-vision-fixtures.cjs');

const helpers = load('lib/dolbyVision.ts', { './dolbyVisionProbeClient': { runDolbyVisionProbe: () => {} } });
const { canExtractHdr10BaseLayer, extractHdr10BaseLayer } = helpers;
const eligible = { status: 'present', trackId: 1, codec: 'hevc', nalLengthSize: 4,
  config: { profile: 8, level: 6, compatibilityId: 1, baseLayer: true, enhancementLayer: false, rpu: true } };

function harness(data, fetchOverride) {
  const requests = [];
  const fetch = async (url, init) => {
    requests.push({ url, ...init });
    if (fetchOverride) return fetchOverride(url, init, requests);
    const [, a, b] = /^bytes=(\d+)-(\d+)$/.exec(init.headers.get('Range'));
    const start = Number(a), end = Math.min(Number(b), data.length - 1);
    return new Response(data.subarray(start, end + 1), { status: 206,
      headers: { 'Content-Range': `bytes ${start}-${end}/${data.length}` } });
  };
  const { probeDolbyVisionMetadata } = load('lib/dolbyVisionMetadata.ts', {
    mp4box, 'ts-ebml/dist/EBML.js': require('ts-ebml/dist/EBML.js')
  }, { fetch, ArrayBuffer });
  return { requests, probe: (options = {}, url = 'https://fixture.invalid/selected-file') => probeDolbyVisionMetadata(url, options) };
}

test('Generated ffmpeg Main10 MP4 metadata is explicitly absent, not unknown', async () => {
  // Actual 80-second 640x360 SDR fixture, ftyp/moov only; no encoded media included.
  const data = gunzipSync(fs.readFileSync(path.join(__dirname, 'fixtures/hevc-main10-metadata.mp4.gz')));
  const result = await harness(data).probe({ trackId: 1 });
  assert.deepEqual(structuredClone(result), { status: 'absent', trackId: 1 });
});

test('A CORS-readable 206 Matroska prefix does not require an exposed Content-Range', async () => {
  for (const absent of [false, true]) {
    const data = mkvFixture([{ absent }]);
    const h = harness(data, async () => new Response(data, { status: 206 }));
    const result = await h.probe({ trackId: 1 });
    assert.equal(result.status, absent ? 'absent' : 'present');
    if (!absent) assert.equal(canExtractHdr10BaseLayer(result), true);
    assert.equal(h.requests.length, 1);
  }
});

test('Hidden range headers do not make Profile 5 HDR10-compatible', async () => {
  const data = mkvFixture([{ config: dvConfig({ profile: 5, compatibilityId: 0 }), mappingType: 0x64766343 }]);
  const h = harness(data, async () => new Response(data, { status: 206 }));
  const result = await h.probe({ trackId: 1 });
  assert.equal(result.status, 'present');
  assert.equal(result.config.profile, 5);
  assert.equal(canExtractHdr10BaseLayer(result), false);
});

test('Unexposed ranges never cause guessed offset requests or full-file reads', async () => {
  for (const data of [mkvFixture().subarray(0, 30), mp4Fixture(), Buffer.from('<html>Not media</html>')]) {
    const h = harness(data, async () => new Response(data, { status: 206 }));
    assert.equal((await h.probe()).status, 'unknown');
    assert.equal(h.requests.length, 1);
  }
  const h = harness(null, async () => new Response(Buffer.alloc(256 * 1024 + 1), { status: 206 }));
  assert.equal((await h.probe()).reason, 'oversized-range-body');
  assert.equal(h.requests.length, 1);
});

test('Malformed visible range headers are still rejected, not treated as CORS-hidden', async () => {
  const data = mkvFixture();
  const h = harness(data, async () => new Response(data, { status: 206, headers: { 'Content-Range': 'invalid' } }));
  assert.equal((await h.probe()).reason, 'invalid-content-range');
});

test('Huge declared sample counts fail closed in a tiny file without exhausting a 48 MiB heap', { timeout: 10000 }, () => {
  const data = mp4Fixture();
  const position = data.indexOf(Buffer.from('stsz'));
  data.writeUInt32BE(1, position + 8);
  data.writeUInt32BE(0xffffffff, position + 12);
  const code = ts.transpileModule(fs.readFileSync(path.join(__dirname, '../lib/dolbyVisionMetadata.ts'), 'utf8'), {
    compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022, esModuleInterop: true }
  }).outputText;
  function child() {
    const fs = require('node:fs');
    const assert = require('node:assert/strict');
    const mp4box = require('mp4box');
    const input = JSON.parse(fs.readFileSync(0, 'utf8'));
    const bytes = Buffer.from(input.fixture, 'base64');
    const registry = mp4box.BoxParser.box;
    const parsed = [];
    const realRequire = require;
    const module = { exports: {} };
    const importer = (name) => name === 'mp4box' ? {
      ...mp4box, createFile(...args) { const file = mp4box.createFile(...args); parsed.push(file); return file; }
    } : realRequire(name);
    new Function('module', 'exports', 'require', input.code)(module, module.exports, importer);
    const probe = module.exports.probeDolbyVisionMetadata;
    (async () => {
      // The exact same sample count is impossible in a tiny resource, but can be
      // represented in a long resource. Neither case may allocate per-sample data.
      for (const size of [bytes.length, 23 * 1024 ** 3]) {
        globalThis.fetch = async () => new Response(bytes, { status: 206,
          headers: { 'Content-Range': `bytes 0-${bytes.length - 1}/${size}` } });
        const result = await probe('https://fixture.invalid/selected.mp4', { trackId: 1 });
        assert.equal(result.status, size === bytes.length ? 'unknown' : 'present');
        assert.equal(mp4box.BoxParser.box, registry, 'Registry must be restored on failure and success');
        if (result.status === 'present') {
          const track = parsed.at(-1).getTrackById(1);
          assert.equal(track.samples.length, 0);
          assert.equal(track.mdia.minf.stbl.stsz.sample_sizes, undefined);
          assert.equal(track.mdia.minf.stbl.stts.sample_counts, undefined);
          assert.equal(result.config.profile, 8);
        }
        console.log(JSON.stringify({ status: result.status, size, heapUsed: process.memoryUsage().heapUsed }));
      }
    })().catch((error) => { console.error(error); process.exitCode = 1; });
  }
  // Transpile outside the constrained child: the TypeScript compiler is not part
  // of production probing and would otherwise dominate this memory regression.
  const output = spawnSync(process.execPath, ['--max-old-space-size=48', '-e', `(${child.toString()})()`], {
    input: JSON.stringify({ code, fixture: data.toString('base64') }), cwd: path.resolve(__dirname, '..'),
    timeout: 7000, maxBuffer: 16384, windowsHide: true, encoding: 'utf8'
  });
  assert.equal(output.error, undefined, output.error?.message);
  assert.equal(output.status, 0, output.stderr);
  assert.deepEqual(output.stdout.trim().split(/\r?\n/).map((line) => JSON.parse(line).status), ['unknown', 'present']);
});

test('Sample-size table validation rejects truncated variable and compact counts without allocation', async () => {
  const registry = mp4box.BoxParser.box;
  for (const type of ['stsz', 'stz2']) {
    const data = mp4Fixture();
    const position = data.indexOf(Buffer.from('stsz'));
    data.write(type, position);
    data.writeUInt32BE(type === 'stsz' ? 0 : 16, position + 8);
    data.writeUInt32BE(0xffffffff, position + 12);
    assert.equal((await harness(data).probe()).status, 'unknown');
    assert.equal(mp4box.BoxParser.box, registry);
  }
});

test('The EBML browser distribution loads without require, Buffer or process and decodes DV mappings', () => {
  const context = { console, Uint8Array, ArrayBuffer, DataView, TextDecoder, TextEncoder, setTimeout, clearTimeout };
  vm.runInNewContext(fs.readFileSync(require.resolve('ts-ebml/dist/EBML.js'), 'utf8'), context);
  const elements = new context.EBML.Decoder().decode(Uint8Array.from(mkvFixture()).buffer);
  assert.equal(elements.find((element) => element.name === 'BlockAddIDType').value, 0x64767643);
  assert.equal(elements.find((element) => element.name === 'BlockAddIDExtraData').data.length, 24);
});

for (const [format, fixture] of [['MP4', mp4Fixture], ['Matroska', mkvFixture]]) {
  test(`${format}: real parser returns exact profile 8.1 configuration and HEVC length size`, async () => {
    for (const lengthSize of [1, 2, 4]) {
      const result = await harness(fixture([{ lengthSize }])).probe({ trackId: 1 });
      assert.deepEqual(structuredClone(result), { ...eligible, nalLengthSize: lengthSize });
      assert.equal(canExtractHdr10BaseLayer(result), true);
    }
  });

  test(`${format}: profile 5 is detected but can never be stripped as HDR10`, async () => {
    const result = await harness(fixture([{ config: dvConfig({ profile: 5, compatibilityId: 0 }), boxType: 'dvcC', mappingType: 0x64766343 }])).probe();
    assert.equal(result.status, 'present');
    assert.equal(result.config.profile, 5);
    assert.equal(result.config.compatibilityId, 0);
    assert.equal(canExtractHdr10BaseLayer(result), false);
    assert.throws(() => extractHdr10BaseLayer(nal(62), result), /confirmed/);
  });

  test(`${format}: only the explicitly selected video track supplies DV metadata`, async () => {
    const probe = harness(fixture([{ id: 7, config: dvConfig({ profile: 5, compatibilityId: 0 }) }, { id: 13, absent: true }]));
    assert.equal((await probe.probe()).status, 'unknown');
    assert.equal((await probe.probe({ trackId: 7 })).config.profile, 5);
    assert.deepEqual(structuredClone(await probe.probe({ trackId: 13 })), { status: 'absent', trackId: 13 });
    assert.equal((await probe.probe({ trackId: 1000 })).status, 'unknown', 'TrackUID/index must not substitute for track ID');
  });

  test(`${format}: truncated/configuration-version/feature data is unknown, not absent`, async () => {
    for (const length of [0, 4, 5, 23]) {
      assert.equal((await harness(fixture([{ config: dvConfig().subarray(0, length) }])).probe()).status, 'unknown');
    }
    for (const [offset, value] of [[0, 2], [1, 1], [4, 0x14], [15, 1]]) {
      const config = dvConfig(); config[offset] = value;
      assert.equal((await harness(fixture([{ config }])).probe()).status, 'unknown');
    }
    const bytes = fixture();
    assert.equal((await harness(bytes.subarray(0, bytes.length - 17)).probe()).status, 'unknown');
  });

  test(`${format}: absent signaling is distinct from failed probing`, async () => {
    const result = await harness(fixture([{ absent: true }])).probe();
    assert.deepEqual(structuredClone(result), { status: 'absent', trackId: 1 });
    assert.equal(canExtractHdr10BaseLayer(result), false);
  });
}

test('MP4: DV-like bytes in an unrelated box are not a Dolby Vision record', async () => {
  const data = mp4Fixture([{ absent: true, boxes: [rawBox('free', Buffer.concat([Buffer.from('dvcC'), dvConfig()]))] }]);
  assert.equal((await harness(data).probe()).status, 'absent');
});

test('MP4: missing DV-branded configuration, duplicate boxes and changing sample entries are unknown', async () => {
  assert.equal((await harness(mp4Fixture([{ absent: true, type: 'dvh1' }])).probe()).status, 'unknown');
  assert.equal((await harness(mp4Fixture([{ boxes: [rawBox('dvcC', dvConfig({ profile: 5 }))] }])).probe()).status, 'unknown');
  const data = mp4Fixture([{}], (file) => {
    const stsd = file.getTrackById(1).mdia.minf.stbl.stsd;
    stsd.entries.push(stsd.entries[0]);
  });
  assert.equal((await harness(data).probe()).status, 'unknown');
});

test('MP4: a malformed DV child omitted by mp4box must never become explicit absence', async () => {
  const data = mp4Fixture();
  const position = data.indexOf(Buffer.from('dvvC'));
  data.writeUInt32BE(9999, position - 4);
  assert.equal((await harness(data).probe()).status, 'unknown');
});

test('MP4: uses parser-provided sparse offset to skip a 23 GiB mdat', async () => {
  const original = mp4Fixture();
  const ftypSize = original.readUInt32BE(0);
  const ftyp = original.subarray(0, ftypSize);
  const moov = original.subarray(ftypSize);
  const mdatSize = 23 * 1024 ** 3;
  const header = Buffer.alloc(16);
  header.writeUInt32BE(1); header.write('mdat', 4); header.writeBigUInt64BE(BigInt(mdatSize), 8);
  const moovOffset = ftyp.length + mdatSize;
  const total = moovOffset + moov.length;
  const h = harness(null, async (_url, { headers }) => {
    const [, a, b] = /^bytes=(\d+)-(\d+)$/.exec(headers.get('Range'));
    const start = Number(a), end = Math.min(Number(b), total - 1);
    const bytes = Buffer.alloc(end - start + 1);
    if (start === 0) { ftyp.copy(bytes); header.copy(bytes, ftyp.length); }
    else { assert.equal(start, moovOffset); moov.copy(bytes); }
    return new Response(bytes, { status: 206, headers: { 'Content-Range': `bytes ${start}-${end}/${total}` } });
  });
  assert.equal((await h.probe()).status, 'present');
  assert.equal(h.requests.length, 2);
});

test('Matroska: unknown-sized Segment is supported without reading clusters', async () => {
  const data = mkvFixture([{}], { unknownSegment: true, afterTracks: master('Cluster', [uint('Timestamp', 0)]) });
  assert.equal((await harness(data).probe()).status, 'present');
});

test('Matroska: names and opaque metadata do not impersonate registered configuration mappings', async () => {
  const result = await harness(mkvFixture([{ mappingType: 1 }])).probe();
  assert.equal(result.status, 'absent');
  assert.equal((await harness(mkvFixture([{ missingExtra: true }])).probe()).status, 'unknown');
  assert.equal((await harness(mkvFixture([{ mappingType: 0x64767743 }])).probe()).status, 'unknown');
});

test('Matroska: enhancement mappings and content encoding cannot qualify as single-layer passthrough', async () => {
  const enhancement = master('BlockAdditionMapping', [uint('BlockAddIDType', 0x68766345), binary('BlockAddIDExtraData', hvcc())]);
  assert.equal((await harness(mkvFixture([{ extra: enhancement }])).probe()).status, 'unknown');
  const encryption = master('ContentEncodings', master('ContentEncoding', [uint('ContentEncodingType', 1)]));
  assert.equal((await harness(mkvFixture([{ extra: encryption }])).probe()).status, 'unknown');
});

test('Matroska: does not scan media clusters for a later Tracks element', async () => {
  const bytes = mkvFixture([{}], { beforeTracks: master('Cluster', [uint('Timestamp', 0)]) });
  assert.equal((await harness(bytes).probe()).reason, 'tracks-not-before-cluster');
});

test('Arbitrary bytes, including a DV record outside a container, are unknown', async () => {
  for (const bytes of [Buffer.alloc(0), Buffer.from('dvcC'), Buffer.concat([Buffer.from('dvvC'), dvConfig()]), Buffer.alloc(100, 0x55)]) {
    assert.equal((await harness(bytes).probe()).status, 'unknown');
  }
});

test('Range-only fetch forwards stream headers, overrides a supplied Range, and never uses a proxy', async () => {
  const h = harness(mp4Fixture());
  assert.equal((await h.probe({ headers: { Authorization: 'Bearer fixture', Range: 'bytes=9-' } })).status, 'present');
  assert.equal(h.requests[0].url, 'https://fixture.invalid/selected-file');
  assert.equal(h.requests[0].headers.get('Range'), 'bytes=0-262143');
  assert.equal(h.requests[0].headers.get('Authorization'), 'Bearer fixture');
  assert.equal(h.requests[0].credentials, 'omit');
  assert.equal((await h.probe({}, 'https://site.netlify.app/.netlify/functions/proxy?url=x')).status, 'unknown');
  assert.equal(h.requests.length, 1);
});

test('Range-ignorant responses are cancelled before any body read', async () => {
  let cancelled = 0;
  const h = harness(null, async () => new Response(new ReadableStream({ cancel() { cancelled++; } }), { status: 200 }));
  assert.equal((await h.probe()).reason, 'range-not-supported');
  assert.equal(cancelled, 1);
});

test('Malformed Content-Range, transformed bodies and overlong/short bodies fail closed', async () => {
  for (const range of [null, 'bytes 1-8/100', 'bytes 0-8/*', 'bytes 0-100/90', 'bytes 0-262144/999999', 'bytes 0-8/9007199254740993']) {
    const h = harness(null, async () => new Response(Buffer.alloc(9), { status: 206, headers: range ? { 'Content-Range': range } : {} }));
    assert.equal((await h.probe()).reason, range === null ? 'missing-content-range' : 'invalid-content-range');
  }
  for (const length of [8, 10]) {
    const h = harness(null, async () => new Response(Buffer.alloc(length), { status: 206, headers: { 'Content-Range': 'bytes 0-8/100' } }));
    assert.equal((await h.probe()).reason, length < 9 ? 'truncated-range-body' : 'oversized-range-body');
  }
  const h = harness(null, async () => new Response(Buffer.alloc(9), { status: 206,
    headers: { 'Content-Range': 'bytes 0-8/100', 'Content-Encoding': 'gzip' } }));
  assert.equal((await h.probe()).reason, 'encoded-range-response');
});

test('Abort and deadline cover fetch and stalled body reads without mistaking failure for absence', async () => {
  const controller = new AbortController(); controller.abort();
  const before = harness(mp4Fixture());
  assert.equal((await before.probe({ signal: controller.signal })).reason, 'aborted');
  assert.equal(before.requests.length, 0);
  const fetchStall = harness(null, () => new Promise(() => {}));
  assert.equal((await fetchStall.probe({ timeoutMs: 20 })).reason, 'timeout');
  let cancelled = false;
  const readStall = harness(null, async () => new Response(new ReadableStream({ cancel() { cancelled = true; } }),
    { status: 206, headers: { 'Content-Range': 'bytes 0-99/100' } }));
  assert.equal((await readStall.probe({ timeoutMs: 20 })).reason, 'timeout');
  assert.equal(cancelled, true);
  const active = new AbortController();
  const pending = fetchStall.probe({ signal: active.signal }); active.abort();
  assert.equal((await pending).reason, 'aborted');
});

test('Metadata byte/request budgets are hard caps and changing resources cannot be stitched', async () => {
  const large = mp4Fixture([{}], (file) => file.moov.addBox(rawBox('free', Buffer.alloc(300000))));
  const h = harness(large);
  assert.equal((await h.probe({ maxBytes: 128 })).status, 'unknown');
  assert.equal(h.requests[0].headers.get('Range'), 'bytes=0-127');
  const r = harness(large);
  assert.equal((await r.probe({ maxRequests: 1 })).reason, 'metadata-budget-exceeded');
  assert.equal(r.requests.length, 1);
  const changed = harness(null, async (_url, { headers }, requests) => {
    const [, a, b] = /^bytes=(\d+)-(\d+)$/.exec(headers.get('Range'));
    const start = Number(a), end = Math.min(Number(b), large.length - 1);
    return new Response(large.subarray(start, end + 1), { status: 206,
      headers: { 'Content-Range': `bytes ${start}-${end}/${large.length}`, ETag: `"version-${requests.length}"` } });
  });
  assert.equal((await changed.probe()).reason, 'resource-changed');
});

test('Extraction preserves every ordinary NAL and prefix byte, including HDR SEI and full-range SPS', () => {
  for (const lengthSize of [1, 2, 4]) {
    const metadata = { ...eligible, nalLengthSize: lengthSize };
    const kept = [nal(32, lengthSize), nal(33, lengthSize, [0xff, 0x80, 0x00, 0x03, 0x01]), nal(34, lengthSize),
      nal(39, lengthSize, [137, 144, 147, 0x80]), nal(19, lengthSize), nal(40, lengthSize)];
    const input = Buffer.concat([kept[0], nal(62, lengthSize), ...kept.slice(1), nal(62, lengthSize)]);
    const original = Buffer.from(input);
    assert.deepEqual(Buffer.from(extractHdr10BaseLayer(input, metadata)), Buffer.concat(kept));
    assert.deepEqual(input, original);
    assert.equal(extractHdr10BaseLayer(Buffer.concat(kept), metadata), null);
    assert.equal(extractHdr10BaseLayer(Buffer.alloc(0), metadata), null);
    assert.equal(extractHdr10BaseLayer(nal(62, lengthSize), metadata).length, 0);
  }
});

test('Only confirmed HEVC P8 compatibility 1, BL-present, EL-absent, RPU-present metadata authorizes extraction', () => {
  const results = [{ status: 'absent', trackId: 1 }, { status: 'unknown', reason: 'timeout' },
    { ...eligible, codec: 'other' }, { ...eligible, nalLengthSize: null },
    ...[{ profile: 5 }, { profile: 7 }, { compatibilityId: 2 }, { compatibilityId: 4 },
      { baseLayer: false }, { enhancementLayer: true }, { rpu: false }].map((change) => ({ ...eligible, config: { ...eligible.config, ...change } }))];
  for (const result of results) {
    assert.equal(canExtractHdr10BaseLayer(result), false);
    assert.throws(() => extractHdr10BaseLayer(nal(62), result), /confirmed/);
  }
});

test('Packet validation rejects malformed lengths, headers, type 63 and unexpected enhancement layers', () => {
  const malformed = [Buffer.from([0, 0, 0]), Buffer.from([0, 0, 0, 0]), Buffer.from([0xff, 0xff, 0xff, 0xff]),
    Buffer.from([0, 0, 0, 1, 0]), Buffer.from([0, 0, 0, 2, 0x80, 1]), Buffer.from([0, 0, 0, 2, 0x26, 0]),
    nal(63), nal(19, 4, [0], 1), nal(62, 4, [0], 32)];
  for (const data of malformed) {
    const original = Buffer.from(data);
    assert.throws(() => extractHdr10BaseLayer(data, eligible));
    assert.throws(() => extractHdr10BaseLayer(Buffer.concat([nal(62), data]), eligible));
    assert.deepEqual(data, original);
  }
});

import assert from 'node:assert/strict';
import fs from 'node:fs/promises';
import path from 'node:path';
import crypto from 'node:crypto';
import { execFileSync } from 'node:child_process';
import { createRequire } from 'node:module';

const expected = JSON.parse(await fs.readFile(process.env.CODECS_INPUTS_PATH ?? new URL('../distribution-sources/codecs/inputs.json', import.meta.url), 'utf8'));
const oldWasm = new Set([
  'd1b1805b18da68c87d862ed709da7be478bc1a8e8263319aade8b52ffa96c15a7',
  'c2c98ec2b351983e0a99a57fa22ef8eed3edbb28a883229e972d64e19417cad13',
  '8488acb0bc9f43055b83600cc4c2c07d20f07675aa65671f91ac1d75515845d9',
]);
const hash = bytes => crypto.createHash('sha256').update(bytes).digest('hex');
export function checkManifestShape(manifest) {
  assert.equal(manifest.schemaVersion, 1);
  assert.deepEqual(manifest.inputs, expected);
  assert.equal(manifest.rebuilt, true);
  assert.equal(manifest.featuresRemoved, false);
  assert.equal(manifest.sourceOnlyMediabunnyArchive, true);
  assert.ok(Array.isArray(manifest.files) && manifest.files.length > 30);
  const seen = new Set();
  for (const entry of manifest.files) {
    assert.match(entry.path, /^(packages\/@mediabunny\/(ac3|dts|aac-encoder)\/|distribution-sources\/codecs\/)[A-Za-z0-9._/@-]+$/);
    assert.ok(!entry.path.split('/').includes('..'));
    assert.ok(!seen.has(entry.path)); seen.add(entry.path);
    assert.match(entry.sha256, /^[a-f0-9]{64}$/);
    assert.ok(Number.isSafeInteger(entry.bytes) && entry.bytes >= 0);
  }
}
function readEmbeddedWasm(text, ts, depth = 0) {
  const base64 = text.match(/data:application\/(?:octet-stream|wasm);base64,([A-Za-z0-9+/=]+)/)?.[1];
  if (base64) return Buffer.from(base64, 'base64');
  const ast = ts.createSourceFile('codec.js', text, ts.ScriptTarget.Latest, true, ts.ScriptKind.JS);
  let found;
  const workers = [];
  function visit(node) {
    if (found) return;
    if (ts.isStringLiteralLike(node) && node.text.length > 1024) {
      // Worker bundles minify the helper name. Recognize a valid WASM literal,
      // not an identifier spelling, and safely parse nested inline worker text.
      const literal = node.text;
      const bytes = Buffer.from(Uint8Array.from(literal, char => (~char.charCodeAt(0) >> 8) & char.charCodeAt(0)));
      if (bytes.subarray(0, 4).equals(Buffer.from([0, 97, 115, 109])) && WebAssembly.validate(bytes)) found = bytes;
      else if (depth < 2 && (literal.includes('WebAssembly') || literal.includes('binaryDecode'))) workers.push(literal);
    }
    ts.forEachChild(node, visit);
  }
  visit(ast);
  if (!found) for (const worker of workers) { found = readEmbeddedWasm(worker, ts, depth + 1); if (found) break; }
  return found;
}

const root = path.resolve(process.argv[2]);
const materialRoot = path.join(root, 'distribution-sources/codecs');
const manifest = JSON.parse(await fs.readFile(path.join(materialRoot, 'manifest.json'), 'utf8'));
checkManifestShape(manifest);
for (const entry of manifest.files) {
  const file = path.resolve(root, entry.path);
  assert.ok(file.startsWith(root + path.sep));
  const bytes = await fs.readFile(file);
  assert.equal(bytes.length, entry.bytes, `Changed file length: ${entry.path}`);
  assert.equal(hash(bytes), entry.sha256, `Changed file hash: ${entry.path}`);
}
const require = process.argv[3] ? createRequire(path.resolve(process.argv[3], 'package.json')) : createRequire(import.meta.url);
const ts = require('typescript');
for (const codec of expected.codecs) {
  const base = path.join(root, 'packages/@mediabunny', codec.package);
  const meta = JSON.parse(await fs.readFile(path.join(base, 'package.json'), 'utf8'));
  assert.equal(meta.version, '1.55.7'); assert.equal(meta.license, 'MPL-2.0');
  const text = await fs.readFile(path.join(base, 'dist/modules/build', codec.stem + '.js'), 'utf8');
  const wasm = readEmbeddedWasm(text, ts);
  assert.ok(wasm, 'Missing inlined WASM');
  assert.ok(WebAssembly.validate(wasm));
  assert.ok(!oldWasm.has(hash(wasm)), 'Original unproven upstream WASM remains');
  assert.match(text, /_init_encoder/);
  if (codec.decoders.length) assert.match(text, /_init_decoder/);
  const config = await fs.readFile(path.join(materialRoot, 'relink', codec.package, 'config.h'), 'utf8');
  const components = await fs.readFile(path.join(materialRoot, 'relink', codec.package, 'config_components.h'), 'utf8');
  assert.match(config, /#define CONFIG_GPL 0/); assert.match(config, /#define CONFIG_NONFREE 0/);
  for (const name of codec.decoders) assert.ok(components.includes(`#define CONFIG_${name.toUpperCase()}_DECODER 1`));
  for (const name of codec.encoders) assert.ok(components.includes(`#define CONFIG_${name.toUpperCase()}_ENCODER 1`));
  for (const extension of ['.mjs', '.min.mjs', '.js', '.min.js']) {
    const bundle = await fs.readFile(path.join(base, 'dist/bundles', `mediabunny-${codec.package}${extension}`), 'utf8');
    assert.match(bundle, /FFmpeg 8\.1\.3/);
    const bundledWasm = readEmbeddedWasm(bundle, ts);
    assert.ok(bundledWasm, `Missing worker WASM in ${codec.package}${extension}`);
    assert.equal(hash(bundledWasm), hash(wasm), `Bundle uses a different WASM: ${codec.package}${extension}`);
  }
}
const archive = path.join(materialRoot, 'mediabunny-1.55.7-sources.tar.gz');
const archiveFiles = execFileSync('tar', ['-tzf', archive], { encoding: 'utf8' }).split(/\r?\n/).filter(Boolean);
assert.ok(archiveFiles.includes('src/index.ts'));
assert.ok(archiveFiles.includes('LICENSE'));
assert.ok(archiveFiles.includes('package-lock.json'));
for (const codec of expected.codecs) assert.ok(archiveFiles.includes(`packages/${codec.package}/src/bridge.c`));
assert.ok(!archiveFiles.some(file => /(^|\/)(build|dist|node_modules|test)\//.test(file) || /\.(wasm|a|o|mp4|webm)$/.test(file)), 'Source archive contains compiled upstream artifacts or test media');
assert.equal(hash(await fs.readFile(path.join(materialRoot, 'ffmpeg-8.1.3.tar.xz'))), expected.ffmpeg.sha256);
for (const mutate of [
  value => { value.featuresRemoved = true; },
  value => { value.rebuilt = false; },
  value => { value.inputs.ffmpeg.sha256 = '0'.repeat(64); },
  value => { value.files[0].path = '../outside'; },
  value => { value.files.push(value.files[0]); },
]) {
  const fixture = structuredClone(manifest); mutate(fixture);
  assert.throws(() => checkManifestShape(fixture));
}
console.log(`Codec source closure passed: ${manifest.files.length} checksummed files, 3 valid newly built WASMs, all decoder/encoder flags retained, source-only archive and 5 adversarial manifest cases.`);

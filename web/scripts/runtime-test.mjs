import assert from 'node:assert/strict';
import { execFileSync } from 'node:child_process';
import { createHash } from 'node:crypto';
import { mkdtempSync, readFileSync, rmSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { basename, dirname, join, resolve } from 'node:path';
import { fileURLToPath } from 'node:url';
import { parseDscChecksums, sourcePlan } from './runtime-fetch-sources.mjs';

const webRoot = fileURLToPath(new URL('../', import.meta.url));
const manifest = JSON.parse(readFileSync(join(webRoot, 'distribution-sources/runtime/manifest.json'), 'utf8'));
const plan = sourcePlan(manifest);
assert.equal(manifest.base.platform, 'linux/amd64');
assert.equal(manifest.node.version, '22.23.3');
for (const digest of [manifest.base.indexDigest, manifest.base.amd64Digest, manifest.base.debianBaseDigest]) assert.match(digest, /^sha256:[a-f0-9]{64}$/);
assert.match(manifest.node.sourceSha256, /^[a-f0-9]{64}$/);
assert.equal(new Set(plan.map(item => item.path)).size, plan.length);
assert.equal(manifest.sharp.nativeSources.length, Object.keys(manifest.sharp.versions).length);
assert.ok(plan.some(item => item.path.endsWith('THIRD-PARTY-NOTICES.md')));
assert.ok(plan.some(item => item.path.endsWith('MPL-2.0.txt')));
assert.ok(plan.some(item => item.path.endsWith('glib-without-gregex.patch')));
for (const item of plan) {
  assert.equal(new URL(item.url).protocol, 'https:');
  assert.equal(new URL(item.url).username, '');
  assert.equal(new URL(item.url).password, '');
  assert.equal(item.path.includes('..'), false);
  assert.match(item.sha256, /^[a-f0-9]{64}$/);
}
const missing = structuredClone(manifest); missing.sharp.nativeSources.pop();
assert.throws(() => sourcePlan(missing), /Every upstream native version/);
const malicious = structuredClone(manifest); malicious.sharp.patches[0].name = '../escape';
assert.throws(() => sourcePlan(malicious), /Unsafe/);
const hash = 'a'.repeat(64);
assert.deepEqual(parseDscChecksums(`Format: 3.0 (quilt)\nChecksums-Sha256:\n ${hash} 123 source_1.0.orig.tar.xz\nFiles:\n ignored\n`), [{ sha256: hash, bytes: 123, name: 'source_1.0.orig.tar.xz' }]);
assert.throws(() => parseDscChecksums('Format: 3.0 (quilt)\n'), /lacks/);
assert.throws(() => parseDscChecksums('Checksums-Sha256:\n wrong 123 file.tar.xz\n'), /Invalid/);
assert.throws(() => parseDscChecksums(`Checksums-Sha256:\n ${hash} 123 ../escape.tar.xz\n`), /Unsafe/);
console.log('PASS pinned runtime/native source plan and Debian descriptor validation');

const tempRoot = resolve(tmpdir());
const output = mkdtempSync(join(tempRoot, 'arvio-runtime-tests-'));
try {
  execFileSync(process.execPath, [join(webRoot, 'scripts/runtime-asset-inventory.mjs'), output], { encoding: 'utf8' });
  const inventory = JSON.parse(readFileSync(join(output, 'public-assets.json'), 'utf8'));
  assert.ok(inventory.files.length > 150);
  assert.equal(new Set(inventory.files.map(item => item.path)).size, inventory.files.length);
  for (const item of inventory.files) {
    const data = readFileSync(join(webRoot, item.path));
    assert.equal(data.length, item.bytes);
    assert.equal(createHash('sha256').update(data).digest('hex'), item.sha256);
    assert.match(item.projectDeclaredLicense, /not an individual authorship/);
  }
  const metadata = inventory.files.find(item => item.path === 'public/data/channel-logos.json');
  assert.equal(metadata.licenseConcluded, 'Unlicense (metadata only)');
  assert.match(metadata.provenance, /iptv-org/);
  assert.ok(inventory.files.filter(item => item.category === 'profile avatar').every(item => item.licenseConcluded === 'NOASSERTION'));
  console.log(`PASS ${inventory.files.length} exact public asset hashes, retained features, and honest provenance labels`);
} finally {
  if (dirname(output) !== tempRoot || !basename(output).startsWith('arvio-runtime-tests-')) throw new Error('Unsafe test cleanup target.');
  rmSync(output, { recursive: true });
}

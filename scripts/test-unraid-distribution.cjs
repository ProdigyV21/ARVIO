const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { spawnSync } = require('node:child_process');
const { createHash } = require('node:crypto');
const base = path.resolve(__dirname, '../web/distribution-artifacts');
fs.mkdirSync(base, { recursive: true });
const fixture = fs.mkdtempSync(path.join(base, 'index-test-'));
const sha256 = bytes => createHash('sha256').update(bytes).digest('hex');
function write(name, value) {
  const target = path.join(fixture, name);
  fs.mkdirSync(path.dirname(target), { recursive: true });
  fs.writeFileSync(target, typeof value === 'string' ? value : JSON.stringify(value));
}
function run() {
  return spawnSync(process.execPath, [path.resolve(__dirname, '../web/scripts/distribution-index.mjs'), fixture], { encoding: 'utf8' });
}
try {
  const archive = 'public fixture archive';
  write('arvio-source.tar.gz', archive);
  write('arvio-source.json', { sourceCommit: 'a'.repeat(40), archive: 'arvio-source.tar.gz', archiveSha256: sha256(archive) });
  for (const file of ['ARVIO-LICENSE.txt', 'DISTRIBUTION-LICENSE.txt', 'runtime/runtime-inventory.json', 'notices/packages.json']) write(file, 'fixture');
  write('notices/unraid-audit.json', { passed: true, issues: [], excludedProductionPackages: [], findings: [{ kind: 'webpack-module-stats' }] });
  write('codecs/manifest.json', { rebuilt: true, featuresRemoved: false, sourceOnlyMediabunnyArchive: true, files: [] });
  write('runtime/test-source.txt', 'public test source');
  const runtime = { complete: true, scope: 'actual Linux runtime/native sources', files: [{ path: 'test-source.txt', bytes: Buffer.byteLength('public test source'), sha256: sha256('public test source') }] };
  write('runtime/runtime-downloads.json', runtime);
  assert.equal(run().status, 0, 'A complete fixture must generate a public source index');
  assert.match(fs.readFileSync(path.join(fixture, 'index.html'), 'utf8'), /distribution-specific licence/);
  for (const mutation of [
    item => { item.complete = false; },
    item => { item.scope = 'native audit only'; },
    item => { item.files[0].sha256 = '0'.repeat(64); },
    item => { item.files[0].path = '../missing'; }
  ]) {
    const invalid = structuredClone(runtime); mutation(invalid);
    write('runtime/runtime-downloads.json', invalid);
    assert.notEqual(run().status, 0, 'Incomplete or corrupt source material must fail packaging');
  }
  write('runtime/runtime-downloads.json', runtime);
  write('arvio-source.tar.gz', 'tampered');
  assert.notEqual(run().status, 0, 'Corrupt project source archive must fail packaging');
  console.log('Public distribution index: valid fixture + 5 incomplete/corrupt source rejection cases passed.');
} finally {
  // Only remove this test's exact generated child directory, never user data.
  const actual = fs.realpathSync(fixture);
  if (!actual.startsWith(fs.realpathSync(base) + path.sep) || !path.basename(actual).startsWith('index-test-')) throw Error('Unsafe fixture cleanup target');
  fs.rmSync(actual, { recursive: true, force: true });
}

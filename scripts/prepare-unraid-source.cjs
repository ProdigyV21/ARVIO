// Package only committed public build inputs, never the working directory.
const { execFileSync } = require('node:child_process');
const { createHash } = require('node:crypto');
const { mkdirSync, writeFileSync } = require('node:fs');
const path = require('node:path');
const { gzipSync } = require('node:zlib');

const buildInputs = ['web', 'LICENSE', '.github/workflows/unraid-web.yml',
  'scripts/prepare-unraid-source.cjs', 'scripts/test-unraid-source.cjs',
  'scripts/test-unraid-distribution.cjs',
  'scripts/check-unraid-container.cjs', 'scripts/check-unraid-browser.cjs',
  'scripts/check-unraid.ps1', 'scripts/test-unraid-contract.ps1',
  'scripts/export-unraid-feed.ps1', 'unraid'];
const sha256 = bytes => createHash('sha256').update(bytes).digest('hex');
function allowed(name) {
  if (!name || name.includes('\\') || name.split('/').some(part => part === '..' || part === '.')) return false;
  if (!buildInputs.some(input => name === input || name.startsWith(input + '/'))) return false;
  if (name.split('/').some(part => /^(?:node_modules|\.git|\.next.*|distribution-artifacts|rebuild-output|\.planning)$/.test(part))) return false;
  if (/\.(?:pem|key|jks|keystore|log|tsbuildinfo)$/i.test(name)) return false;
  const leaf = path.posix.basename(name);
  if (leaf.startsWith('.env') && !['.env.example', '.env.selfhost.example'].includes(leaf)) return false;
  return true;
}

function tarFiles(tar) {
  const files = new Map();
  for (let offset = 0; offset + 512 <= tar.length;) {
    const header = tar.subarray(offset, offset + 512);
    if (header.every(byte => byte === 0)) break;
    const text = (start, end) => header.subarray(start, end).toString('utf8').replace(/\0.*$/, '');
    const rawName = text(0, 100), prefix = text(345, 500);
    const name = prefix ? `${prefix}/${rawName}` : rawName;
    const size = parseInt(text(124, 136).trim(), 8);
    const kind = text(156, 157);
    if (!Number.isSafeInteger(size) || size < 0 || offset + 512 + size > tar.length) throw Error('Invalid archive size');
    if (kind === '0' || kind === '') {
      if (!name.startsWith('arvio-source/') || !allowed(name.slice(13)) || files.has(name.slice(13))) throw Error(`Unexpected archive file: ${name}`);
      files.set(name.slice(13), tar.subarray(offset + 512, offset + 512 + size));
    } else if (kind !== '5' && kind !== 'g') throw Error(`Unsupported source archive entry: ${kind}`);
    offset += 512 + Math.ceil(size / 512) * 512;
  }
  return files;
}

function prepare(root, requestedSha) {
  const git = args => execFileSync('git', args, { cwd: root, maxBuffer: 512 * 1024 * 1024 });
  const head = git(['rev-parse', 'HEAD']).toString().trim();
  if (!/^[a-f0-9]{40}$/.test(head) || (requestedSha && requestedSha !== head)) throw Error('Source SHA must equal the checked-out immutable commit');
  const records = git(['ls-tree', '-r', '-z', '--full-tree', head, '--', ...buildInputs]).toString().split('\0').filter(Boolean).map(line => {
    const match = /^(\d+) blob ([a-f0-9]+)\t(.+)$/.exec(line);
    if (!match || !['100644', '100755'].includes(match[1]) || !allowed(match[3])) throw Error(`Unsafe or non-source tracked build input: ${line.split('\t').at(-1)}`);
    return { mode: match[1], blob: match[2], path: match[3] };
  });
  if (!records.some(item => item.path === 'web/package-lock.json') || !records.some(item => item.path === 'web/Dockerfile')) throw Error('Missing committed Docker build inputs');
  const dirty = git(['diff', '--name-only', head, '--', ...buildInputs]).toString().trim();
  const untracked = git(['ls-files', '--others', '--exclude-standard', '--', ...buildInputs]).toString().trim();
  if (dirty || untracked) throw Error('Commit all public build-input edits before creating an exact-source bundle');
  const tar = git(['archive', '--format=tar', '--prefix=arvio-source/', head, ...buildInputs]);
  const files = tarFiles(tar);
  if (files.size !== records.length || records.some(item => !files.has(item.path))) throw Error('Git archive omitted tracked source inputs (check export-ignore attributes)');
  const archive = gzipSync(tar, { level: 9 });
  const output = path.join(root, 'web', 'distribution-artifacts');
  mkdirSync(output, { recursive: true });
  const manifest = { schemaVersion: 1, sourceCommit: head,
    repository: 'https://github.com/ProdigyV21/ARVIO',
    archive: 'arvio-source.tar.gz', archiveSha256: sha256(archive),
    scope: 'Committed web source, original project licence, container recipe and distribution/test helpers. Example env files contain public configuration examples, not runtime settings.',
    files: records.map(item => ({ ...item, sha256: sha256(files.get(item.path)), bytes: files.get(item.path).length })) };
  writeFileSync(path.join(output, manifest.archive), archive);
  writeFileSync(path.join(output, 'arvio-source.json'), JSON.stringify(manifest, null, 2) + '\n');
  console.log(`Exact public source: ${head}, ${files.size} files, ${archive.length} bytes, SHA256 ${manifest.archiveSha256}`);
  return manifest;
}

module.exports = { allowed, buildInputs, prepare, tarFiles };
if (require.main === module) prepare(path.resolve(__dirname, '..'), process.argv[2]);

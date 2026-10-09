import { createHash } from 'node:crypto';
import { mkdir, readFile, readdir, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const root = path.resolve(process.argv[2] || fileURLToPath(new URL('../public/distribution-sources/', import.meta.url)));
const ignored = new Set(['index.html', 'files.json']);
const files = [];
async function walk(directory) {
  for (const entry of await readdir(directory, { withFileTypes: true })) {
    const file = path.join(directory, entry.name);
    if (entry.isDirectory()) await walk(file);
    else if (entry.isFile()) {
      const relative = path.relative(root, file).replaceAll('\\', '/');
      if (ignored.has(relative)) continue;
      const bytes = await readFile(file);
      files.push({ path: relative, bytes: bytes.length, sha256: createHash('sha256').update(bytes).digest('hex') });
    } else throw Error('Distribution sources must not contain symlinks or special files');
  }
}
await mkdir(root, { recursive: true });
await walk(root);
for (const required of ['arvio-source.tar.gz', 'arvio-source.json', 'ARVIO-LICENSE.txt', 'DISTRIBUTION-LICENSE.txt', 'codecs/manifest.json', 'runtime/runtime-inventory.json', 'runtime/runtime-downloads.json', 'notices/packages.json', 'notices/unraid-audit.json']) {
  if (!files.some(file => file.path === required)) throw Error(`Distribution source closure missing ${required}`);
}
files.sort((a, b) => a.path.localeCompare(b.path));
const source = JSON.parse(await readFile(path.join(root, 'arvio-source.json'), 'utf8'));
if (!/^[a-f0-9]{40}$/.test(source.sourceCommit) || source.archive !== 'arvio-source.tar.gz' || source.archiveSha256 !== files.find(file => file.path === source.archive)?.sha256) throw Error('Exact ARVIO source archive does not match its manifest');
if (files.some(file => file.path.startsWith('aes/'))) throw Error('Telegram/AES is not part of the released preview');
const audit = JSON.parse(await readFile(path.join(root, 'notices/unraid-audit.json'), 'utf8'));
if (audit.passed !== true || audit.issues?.length !== 0 || audit.excludedProductionPackages?.length !== 0 || !audit.findings?.some(item => item.kind === 'webpack-module-stats')) throw Error('Actual built-output exclusion/notice audit must pass before packaging');
const runtime = JSON.parse(await readFile(path.join(root, 'runtime/runtime-downloads.json'), 'utf8'));
if (runtime.complete !== true || !Array.isArray(runtime.files) || runtime.scope !== 'actual Linux runtime/native sources') throw Error('The public runtime source bundle must be complete and verified against the actual image');
for (const item of runtime.files) {
  const actual = files.find(file => file.path === `runtime/${item.path}`);
  if (!actual || actual.bytes !== item.bytes || actual.sha256 !== item.sha256) throw Error(`Incomplete or changed runtime source: ${item.path}`);
}
const codecs = JSON.parse(await readFile(path.join(root, 'codecs/manifest.json'), 'utf8'));
if (codecs.rebuilt !== true || codecs.featuresRemoved !== false || codecs.sourceOnlyMediabunnyArchive !== true) throw Error('Codec source closure must describe rebuilt full-feature packages');
for (const item of codecs.files.filter(file => file.path.startsWith('distribution-sources/codecs/'))) {
  const relative = item.path.replace('distribution-sources/', '');
  const actual = files.find(file => file.path === relative);
  if (!actual || actual.bytes !== item.bytes || actual.sha256 !== item.sha256) throw Error(`Incomplete or changed codec source: ${relative}`);
}
const escape = text => text.replace(/[&<>"']/g, character => ({'&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;'}[character]));
await writeFile(path.join(root, 'files.json'), JSON.stringify({ schemaVersion: 1, files }, null, 2) + '\n');
await writeFile(path.join(root, 'index.html'), `<!doctype html><html lang="en"><meta charset="utf-8"><meta name="viewport" content="width=device-width"><title>ARVIO Web — source and notices</title><style>body{font:16px system-ui;max-width:1000px;margin:40px auto;padding:0 20px;color:#ddd;background:#121216}a{color:#abcaff}li{margin:10px 0;overflow-wrap:anywhere}code{font-size:12px}small{color:#aaa}</style><h1>Source and third-party notices</h1><p>Telegram is not included in this Unraid preview. ARVIO's original <a href="ARVIO-LICENSE.txt">Apache-2.0 licence</a> is retained; it is not a blanket licence for Node, Debian, codecs, native libraries or other dependencies. See <a href="DISTRIBUTION-LICENSE.txt">the distribution-specific licence notice</a>.</p><p>The exact committed ARVIO source archive includes the container recipe and build helpers. Codecs include pinned FFmpeg/Mediabunny source, build configuration, object/static libraries and relinking instructions. Runtime material contains installed package notices and corresponding-source archives/build recipes.</p><p>Use <a href="files.json">the SHA-256 inventory</a> to verify each file. Build-only dependencies may appear in the npm notices inventory; their presence there does not mean their binaries ship in the application. This packaging record is not a legal opinion or a guarantee about third-party trademarks or codec patents.</p><ul>${files.map(file => `<li><a href="${file.path.split('/').map(encodeURIComponent).join('/')}">${escape(file.path)}</a> <small>${file.bytes.toLocaleString('en-US')} bytes</small><br><code>${file.sha256}</code></li>`).join('')}</ul></html>\n`);
console.log(`Public source/notice index validated: ${files.length} files.`);

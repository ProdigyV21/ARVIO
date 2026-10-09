import { createHash } from 'node:crypto';
import { existsSync, mkdirSync, readFileSync, renameSync, writeFileSync } from 'node:fs';
import { dirname, join, resolve, sep } from 'node:path';
import { fileURLToPath } from 'node:url';

const webRoot = fileURLToPath(new URL('../', import.meta.url));
const manifest = JSON.parse(readFileSync(join(webRoot, 'distribution-sources/runtime/manifest.json'), 'utf8'));
const flags = new Set(process.argv.slice(2).filter(arg => arg.startsWith('--')));
const output = resolve(process.argv.slice(2).find(arg => !arg.startsWith('--')) || join(webRoot, '.next/third-party-notices/runtime'));
const maxBytes = 512 * 1024 * 1024;
const checksum = (buffer, algorithm = 'sha256') => createHash(algorithm).update(buffer).digest('hex');
const safeName = name => {
  if (!/^[a-zA-Z0-9][a-zA-Z0-9._+~:-]{0,240}$/.test(name) || name.includes('..')) throw new Error(`Unsafe upstream filename ${name}`);
  return name;
};
const timeout = ms => new Promise(resolve => setTimeout(resolve, ms));

export function parseDscChecksums(text) {
  const section = /^Checksums-Sha256:\r?\n((?:[ \t].+\r?\n)+)/m.exec(text);
  if (!section) throw new Error('Debian source descriptor lacks Checksums-Sha256.');
  return section[1].trim().split(/\r?\n/).map(line => {
    const match = /^\s*([a-f0-9]{64})\s+(\d+)\s+(\S+)\s*$/.exec(line);
    if (!match) throw new Error('Invalid Debian source SHA256 entry.');
    return { sha256: match[1], bytes: Number(match[2]), name: safeName(match[3]) };
  });
}

export function sourcePlan(plan) {
  const sharp = plan.sharp;
  if (new Set(sharp.nativeSources.map(item => item.name)).size !== Object.keys(sharp.versions).length || sharp.nativeSources.some(item => !(item.name in sharp.versions))) throw new Error('Every upstream native version needs a corresponding source archive.');
  const archive = (name, version, url) => ({ path: `sources/native/${name}-${version}${new URL(url).pathname.endsWith('.xz') ? '.tar.xz' : '.tar.gz'}`, url });
  const resources = [
    { path: `sources/node/node-v${plan.node.version}.tar.xz`, url: plan.node.source, sha256: plan.node.sourceSha256 },
    { path: 'sources/node/SHASUMS256.txt', url: plan.node.checksums },
    { path: 'licenses/node/LICENSE', url: plan.node.licenseUrl },
    { path: `sources/node/yarn-v${plan.node.yarnVersion}.tar.gz`, url: plan.node.yarnSource },
    { path: `sources/native/sharp-${sharp.version}.tar.gz`, url: sharp.source },
    { path: `sources/native/sharp-libvips-${sharp.libvipsPackageVersion}-recipe.tar.gz`, url: sharp.recipeSource },
    { path: 'licenses/native/THIRD-PARTY-NOTICES.md', url: sharp.upstreamNotices },
    { path: 'licenses/native/MPL-2.0.txt', url: 'https://raw.githubusercontent.com/spdx/license-list-data/v3.27.0/text/MPL-2.0.txt' },
    { path: 'licenses/iptv-org-Unlicense', url: 'https://raw.githubusercontent.com/iptv-org/api/master/LICENSE' },
    ...sharp.nativeSources.map(item => archive(item.name, sharp.versions[item.name], item.url)),
    ...sharp.patches.map(item => ({ path: `sources/native/patches/${safeName(item.name)}`, url: item.url }))
  ];
  return resources.map(item => {
    const sha256 = item.sha256 || plan.sha256ByPath?.[item.path];
    if (!/^[a-f0-9]{64}$/.test(sha256 || '')) throw new Error(`Source lacks its audited SHA256: ${item.path}`);
    return { ...item, sha256 };
  });
}

async function get(url, expected = {}) {
  const parsed = new URL(url);
  if (parsed.protocol !== 'https:' || parsed.username || parsed.password) throw new Error('Only credential-free HTTPS upstream sources are allowed.');
  let lastError;
  for (let attempt = 0; attempt < 3; attempt++) {
    try {
      const response = await fetch(url, { signal: AbortSignal.timeout(120000), headers: { 'User-Agent': 'ARVIO-distribution-source-packager/1' } });
      if (!response.ok) throw new Error(`HTTP ${response.status} from ${url}`);
      if (new URL(response.url).protocol !== 'https:') throw new Error('Upstream source redirected to an insecure URL.');
      const declaredBytes = Number(response.headers.get('content-length'));
      if (declaredBytes > maxBytes) throw new Error('Upstream source exceeds the packaging byte limit.');
      const chunks = []; let bytes = 0;
      for await (const chunk of response.body) {
        bytes += chunk.length;
        if (bytes > maxBytes) throw new Error('Upstream source exceeds the packaging byte limit.');
        chunks.push(chunk);
      }
      const data = Buffer.concat(chunks);
      if (!data.length) throw new Error('Empty upstream source.');
      if (expected.bytes !== undefined && data.length !== expected.bytes) throw new Error(`Source byte count differs for ${url}`);
      if (expected.sha256 && checksum(data) !== expected.sha256) throw new Error(`Source SHA256 differs for ${url}`);
      if (expected.sha1 && checksum(data, 'sha1') !== expected.sha1) throw new Error(`Source SHA1 differs for ${url}`);
      return { data, sha256: checksum(data), bytes: data.length, resolvedUrl: response.url };
    } catch (error) { lastError = error; if (attempt < 2) await timeout((attempt + 1) * 1000); }
  }
  throw lastError;
}

async function run() {
  const plan = sourcePlan(manifest);
  if (flags.has('--plan')) { console.log(JSON.stringify(plan, null, 2)); return; }
  if ([...flags].some(flag => flag !== '--native-only')) throw new Error('Unknown source-packaging option.');
  const inventoryPath = join(output, 'runtime-inventory.json');
  let inventory;
  if (!flags.has('--native-only')) {
    if (process.platform !== 'linux' || process.arch !== 'x64') throw new Error('Full source collection requires the actual Linux amd64 runtime.');
    inventory = JSON.parse(readFileSync(inventoryPath, 'utf8'));
    if (inventory.nodeVersion !== manifest.node.version || inventory.base.amd64Digest !== manifest.base.amd64Digest) throw new Error('Runtime inventory does not match the pinned source plan.');
    if (process.versions.node !== manifest.node.version) throw new Error('Source packager Node does not match the pinned runtime.');
    const installed = JSON.parse(readFileSync(join(webRoot, `node_modules/${manifest.sharp.libvipsPackage}/package.json`), 'utf8'));
    const versions = JSON.parse(readFileSync(join(webRoot, `node_modules/${manifest.sharp.libvipsPackage}/versions.json`), 'utf8'));
    const sharp = JSON.parse(readFileSync(join(webRoot, 'node_modules/sharp/package.json'), 'utf8'));
    if (installed.version !== manifest.sharp.libvipsPackageVersion || sharp.version !== manifest.sharp.version || JSON.stringify(Object.entries(versions).sort()) !== JSON.stringify(Object.entries(manifest.sharp.versions).sort())) throw new Error('Installed Linux sharp/libvips native versions changed; update exact corresponding sources.');
    for (const name of ['GPL-2', 'GPL-3', 'LGPL-2.1', 'LGPL-3']) if (!existsSync(join(output, 'licenses/common', name))) throw new Error(`Missing full ${name} text in actual runtime notices.`);
  } else {
    // Audit/download mode does not claim to inventory or validate a Linux image.
    const lock = JSON.parse(readFileSync(join(webRoot, 'package-lock.json'), 'utf8'));
    if (lock.packages[`node_modules/${manifest.sharp.libvipsPackage}`]?.integrity !== manifest.sharp.libvipsPackageIntegrity) throw new Error('Lockfile libvips integrity changed.');
  }
  mkdirSync(output, { recursive: true });
  const indexPath = join(output, 'runtime-downloads.json');
  const previous = existsSync(indexPath) ? JSON.parse(readFileSync(indexPath, 'utf8')).files : [];
  const records = [];
  async function download(item) {
    const target = resolve(output, item.path);
    if (!target.startsWith(output + sep)) throw new Error('Source destination escaped the packaging output.');
    const cached = previous.find(record => record.path === item.path && record.url === item.url);
    if (existsSync(target) && cached) {
      const data = readFileSync(target);
      if (checksum(data) !== cached.sha256 || (item.sha256 && checksum(data) !== item.sha256) || (item.sha1 && checksum(data, 'sha1') !== item.sha1)) throw new Error(`Cached source checksum mismatch: ${item.path}`);
      records.push(cached); return data;
    }
    const result = await get(item.url, item);
    if (existsSync(target) && !cached && !readFileSync(target).equals(result.data)) throw new Error(`Refusing to overwrite unrelated output: ${item.path}`);
    mkdirSync(dirname(target), { recursive: true });
    writeFileSync(target + '.part', result.data);
    renameSync(target + '.part', target);
    records.push({ path: item.path, url: item.url, resolvedUrl: result.resolvedUrl, bytes: result.bytes, sha256: result.sha256, ...(item.sha1 ? { snapshotSha1: item.sha1 } : {}), ...(item.sha256 ? { upstreamSha256: item.sha256 } : {}) });
    // Persist verified resumable downloads; completeness is marked separately.
    writeFileSync(indexPath, JSON.stringify({ schemaVersion: 1, complete: false, files: records }, null, 2) + '\n');
    console.log(`SOURCE ${item.path} ${result.bytes} bytes`);
    return result.data;
  }
  // Bounded parallelism avoids hammering the upstream source mirrors.
  const queue = [...plan];
  await Promise.all(Array.from({ length: 3 }, async () => { while (queue.length) await download(queue.shift()); }));
  if (inventory) {
    const sources = [...new Map(inventory.packages.map(pkg => [`${pkg.sourceName}@${pkg.sourceVersion}`, pkg])).values()];
    for (const pkg of sources) {
      const listing = JSON.parse((await get(pkg.sourceIndex)).data.toString('utf8'));
      if (listing.package !== pkg.sourceName || listing.version !== pkg.sourceVersion) throw new Error('Debian source index differs from the actual installed source identity.');
      if (!Array.isArray(listing.result) || !listing.result.length) throw new Error(`No corresponding Debian source files for ${pkg.sourceName} ${pkg.sourceVersion}`);
      const sourceFiles = new Map(); let dsc;
      for (const entry of listing.result) {
        if (!/^[a-f0-9]{40}$/.test(entry.hash)) throw new Error('Invalid Debian snapshot content address.');
        const info = JSON.parse((await get(`https://snapshot.debian.org/mr/file/${entry.hash}/info`)).data.toString('utf8'));
        const meta = info.result?.find(item => item.name && item.name.startsWith(pkg.sourceName + '_')) || info.result?.find(item => item.name);
        if (!meta || !Number.isSafeInteger(meta.size) || meta.size < 1) throw new Error('Incomplete Debian source file metadata.');
        const name = safeName(meta.name);
        const data = await download({ path: `sources/debian/${safeName(pkg.sourceName)}/${name}`, url: `https://snapshot.debian.org/file/${entry.hash}`, sha1: entry.hash, bytes: meta.size });
        sourceFiles.set(name, { sha256: checksum(data), bytes: data.length });
        if (name.endsWith('.dsc')) dsc = data.toString('utf8');
      }
      if (!dsc) throw new Error(`Debian source ${pkg.sourceName} lacks its source descriptor.`);
      if (/^Source:\s*(\S+)\s*$/m.exec(dsc)?.[1] !== pkg.sourceName || /^Version:\s*(\S+)\s*$/m.exec(dsc)?.[1] !== pkg.sourceVersion) throw new Error('Debian source descriptor differs from the actual installed source identity.');
      for (const expected of parseDscChecksums(dsc)) {
        const actual = sourceFiles.get(expected.name);
        if (!actual || actual.sha256 !== expected.sha256 || actual.bytes !== expected.bytes) throw new Error(`Incomplete/mismatched Debian corresponding source ${expected.name}`);
      }
    }
  }
  records.sort((a, b) => a.path.localeCompare(b.path));
  writeFileSync(indexPath, JSON.stringify({ schemaVersion: 1, complete: true, scope: inventory ? 'actual Linux runtime/native sources' : 'native/Node download audit only; no Linux inventory assertion', files: records }, null, 2) + '\n');
  console.log(`Complete source bundle: ${records.length} files, ${records.reduce((sum, record) => sum + record.bytes, 0)} bytes.`);
}

if (process.argv[1] && resolve(process.argv[1]) === fileURLToPath(import.meta.url)) await run();

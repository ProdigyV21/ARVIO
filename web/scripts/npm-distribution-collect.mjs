import { mkdir, readFile, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { createTarGz, readTarGz, sha256, sourceFiles, verifyIntegrity } from './npm-distribution-lib.mjs';

const webRoot = fileURLToPath(new URL('../', import.meta.url));
const output = path.resolve(process.argv[2] || path.join(webRoot, '.next/distribution-sources/npm'));
const providers = process.argv[3] ? path.resolve(process.argv[3]) : null;
const auditOnly = process.argv.includes('--audit');
const plan = JSON.parse(await readFile(path.join(output, 'metadata-plan.json'), 'utf8'));
const cache = path.resolve(process.env.ARVIO_NPM_SOURCE_CACHE || path.join(webRoot, '.next/distribution-input-cache/npm'));
const policy = JSON.parse(await readFile(path.join(webRoot, 'distribution-sources/npm/manifest.json'), 'utf8'));
const pins = await readFile(path.join(webRoot, 'distribution-sources/npm/source-archive-lock.json'), 'utf8').then(JSON.parse).catch(error => { if (error.code === 'ENOENT' && auditOnly) return {}; throw error; });
await mkdir(cache, { recursive: true });
await mkdir(path.join(output, 'sources'), { recursive: true });
const sourcePromises = new Map(), sourceInputs = [], files = [], unresolved = [];
const packages = [];
const slug = text => text.replaceAll('@', '').replaceAll('/', '__').replace(/[^a-zA-Z0-9._-]/g, '_');
async function download(url, integrity = null, expectedHash = null) {
  const file = path.join(cache, sha256(url) + '.tgz');
  let bytes = await readFile(file).catch(error => { if (error.code === 'ENOENT') return null; throw error; });
  if (!bytes) {
    let failure;
    for (let attempt = 0; attempt < 3; attempt++) {
      try {
        const response = await fetch(url, { signal: AbortSignal.timeout(180000) });
        if (!response.ok) throw new Error(`HTTP ${response.status}: ${url}`);
        bytes = Buffer.from(await response.arrayBuffer()); break;
      } catch (error) { failure = error; }
    }
    if (!bytes) throw failure;
    if (integrity) verifyIntegrity(bytes, integrity);
    if (expectedHash && sha256(bytes) !== expectedHash) throw new Error(`Changed pinned upstream source archive: ${url}`);
    await writeFile(file, bytes);
  }
  if (integrity) verifyIntegrity(bytes, integrity);
  if (expectedHash && sha256(bytes) !== expectedHash) throw new Error(`Changed cached source archive: ${url}`);
  return bytes;
}
async function publish(relative, bytes) {
  await writeFile(path.join(output, relative), bytes);
  const record = { path: relative, bytes: bytes.length, sha256: sha256(bytes) };
  files.push(record); return record;
}
async function upstream(source) {
  if (sourcePromises.has(source.archive)) return sourcePromises.get(source.archive);
  const promise = (async () => {
    const pin = pins[source.archive];
    if (!auditOnly && (!pin || !/^[a-f0-9]{64}$/.test(pin.sha256))) throw new Error(`Unpinned preferred-source input: ${source.archive}`);
    const bytes = await download(source.archive, null, pin?.sha256);
    const entries = readTarGz(bytes), filtered = sourceFiles(entries);
    if (!filtered.files.length) throw new Error('Upstream archive contains no safe textual source/build material');
    const input = { url: source.archive, repository: source.repository, commit: source.commit, bytes: bytes.length, sha256: sha256(bytes) };
    sourceInputs.push(input);
    const filename = `sources/${slug(source.repository.split('/').slice(-2).join('__'))}-${source.commit}.source.tar.gz`;
    const archive = await publish(filename, createTarGz(filtered.files.map(file => ({ ...file, path: `source/${file.path}` }))));
    const inventory = await publish(filename + '.json', Buffer.from(JSON.stringify({ schemaVersion: 1, input, sourceOnly: true, filter: 'Only UTF8 textual source, build inputs/configs, locks and notices. No symlinks/special files. Compiled native/WASM artifacts, archives, fonts/media and fixture trees excluded. See exact omissions below.', files: filtered.files.map(file => ({ path: file.path, bytes: file.bytes.length, sha256: sha256(file.bytes) })), excluded: filtered.excluded }, null, 2) + '\n'));
    return { input, archive, inventory, entries: filtered.files };
  })();
  sourcePromises.set(source.archive, promise); return promise;
}
async function externalEvidence(pkg) {
  if (!providers) throw new Error(`External source provider not supplied: ${pkg.external.bundle}`);
  const target = path.resolve(providers, pkg.external.evidence);
  if (!target.startsWith(providers + path.sep)) throw new Error('Unsafe external source evidence path');
  const bytes = await readFile(target), evidence = JSON.parse(bytes);
  if (pkg.external.bundle === 'aes' && (evidence.complete !== true && evidence.verified !== true && evidence.exact !== true)) {
    // AES comparison schema uses per-file results; require its independently built verifier evidence.
    if (!Array.isArray(evidence.files) || !evidence.files.length || evidence.files.some(file => file.matches === false || file.match === false)) throw new Error('AES correspondence evidence not complete');
  }
  if (pkg.external.bundle === 'runtime' && (evidence.complete !== true || evidence.scope !== 'actual Linux runtime/native sources')) throw new Error('Runtime source provider is not the actual complete Linux image inventory');
  if (pkg.external.bundle === 'codecs' && (evidence.rebuilt !== true || evidence.sourceOnlyMediabunnyArchive !== true || evidence.featuresRemoved !== false)) throw new Error('Codec provider does not prove full-feature source-only rebuilt packages');
  if (pkg.external.bundle === 'telegram' && evidence.complete !== true) throw new Error('Telegram preferred-source provider unresolved');
  return { path: pkg.external.evidence, bytes: bytes.length, sha256: sha256(bytes), reason: pkg.external.reason };
}
const queue = [...new Map(plan.graph.packages.map(pkg => [`${pkg.name}@${pkg.version}`, pkg])).values()];
await Promise.all(Array.from({ length: 3 }, async () => {
  while (queue.length) {
    const pkg = queue.shift();
    const result = { ...pkg, source: undefined, unresolved: undefined, external: undefined };
    try {
      if (pkg.metadataFile) {
        const bytes = await readFile(path.join(output, pkg.metadataFile));
        if (sha256(bytes) !== pkg.metadataSha256) throw new Error('Changed exact-version registry metadata snapshot');
        files.push({ path: pkg.metadataFile, bytes: bytes.length, sha256: sha256(bytes) });
      }
      if (pkg.external) {
        result.correspondence = { method: 'independent-provider', ...await externalEvidence(pkg) };
        result.originalNpmArchivePublished = false;
        packages.push(result); continue;
      }
      let npmFiles = null;
      if (pkg.tarball) {
        const bytes = await download(pkg.tarball, pkg.integrity);
        result.npmArtifact = { url: pkg.tarball, bytes: bytes.length, sha256: sha256(bytes), integrityVerified: true, published: false };
        npmFiles = readTarGz(bytes);
        const packageFile = npmFiles.find(file => /^[^/]+\/package\.json$/.test(file.path));
        const metadata = packageFile && JSON.parse(packageFile.bytes);
        if (metadata?.name !== pkg.name || metadata.version !== pkg.version) throw new Error('Npm artifact identity does not match exact package/version');
      }
      if (pkg.npmPreferred || pkg.typeOnly) {
        const filter = sourceFiles(npmFiles);
        const required = pkg.npmPreferred?.requiredFiles || ['index.d.ts', 'package.json'];
        for (const name of required) if (!filter.files.some(file => file.path === name)) throw new Error(`Missing reviewed authored source ${name}`);
        const excluded = new Set(pkg.npmPreferred?.excludeFiles || []);
        const selected = filter.files.filter(file => !excluded.has(file.path));
        const archive = await publish(`sources/${slug(pkg.name)}-${pkg.version}.npm-source.tar.gz`, createTarGz(selected.map(file => ({ ...file, path: `source/${file.path}` }))));
        const inventory = await publish(archive.path + '.json', Buffer.from(JSON.stringify({ schemaVersion: 1, input: result.npmArtifact, files: selected.map(file => ({ path: file.path, bytes: file.bytes.length, sha256: sha256(file.bytes) })), excluded: [...filter.excluded, ...filter.files.filter(file => excluded.has(file.path)).map(file => ({ path: file.path, reason: 'Reviewed generated derivative; authored source retained' }))] }, null, 2) + '\n'));
        result.correspondence = { method: pkg.typeOnly ? 'declaration-only-compiler-input' : 'reviewed-authored-npm-source', archive, inventory, reason: (pkg.npmPreferred || pkg.typeOnly).reason };
      } else {
        if (pkg.unresolved || !pkg.source) throw new Error(pkg.unresolved || 'No preferred-source identity');
        const collected = await upstream(pkg.source);
        const directory = pkg.source.directory || '';
        const packageFile = collected.entries.find(file => file.path === (directory ? directory + '/' : '') + 'package.json');
        let sourceMetadata = packageFile ? JSON.parse(packageFile.bytes) : null;
        const directories = pkg.source.correspondence?.preferredSourceDirectories || [directory];
        const preferred = collected.entries.filter(file => directories.some(directory => !directory || file.path.startsWith(directory + '/')) && /\.(?:[cm]?js|[cm]?ts|tsx|jsx|rs|c|cc|cpp|h|hpp)$/.test(file.path) && !/\.d\.ts$|\.min\.js$/.test(file.path));
        if (!preferred.length) throw new Error(`No preferred implementation source in ${directories.join(', ') || 'repository root'}`);
        if (pkg.source.correspondence && sourceMetadata?.version !== pkg.version) throw new Error(`Official release package source version mismatch: ${sourceMetadata?.version || 'absent'} != ${pkg.version}`);
        result.correspondence = { method: pkg.gitDependency ? 'lock-immutable-git-dependency' : pkg.source.correspondence ? 'verified-official-release' : 'exact-npm-publisher-gitHead', repository: pkg.source.repository, directory, commit: pkg.source.commit, input: collected.input, archive: collected.archive, inventory: collected.inventory, sourcePackage: sourceMetadata ? { name: sourceMetadata.name, version: sourceMetadata.version } : null, preferredImplementationFiles: preferred.length, evidence: pkg.source.correspondence || null, limitations: 'Publisher-designated source revision and source/build input availability verified; no claim of bit-identical compilation or closure of opaque bundled third-party/generated code beyond this inventory.' };
      }
    } catch (error) { result.unresolved = error.message; unresolved.push({ name: pkg.name, version: pkg.version, reason: error.message }); }
    packages.push(result);
    console.log(`${result.unresolved ? 'UNRESOLVED' : 'SOURCE'} ${pkg.name}@${pkg.version}${result.unresolved ? ': ' + result.unresolved : ''}`);
  }
}));
packages.sort((a, b) => `${a.name}@${a.version}`.localeCompare(`${b.name}@${b.version}`));
sourceInputs.sort((a, b) => a.url.localeCompare(b.url));
await publish('package-lock.json', await readFile(path.resolve(webRoot, plan.lockPath)));
if (files.find(file => file.path === 'package-lock.json')?.sha256 !== plan.lockSha256) throw new Error('Lock changed after source planning');
await publish('source-policy.json', Buffer.from(JSON.stringify(policy, null, 2) + '\n'));
const report = { schemaVersion: 1, complete: unresolved.length === 0 && !auditOnly, scope: 'actual production npm graph preferred-source inputs', auditOnly, lockSha256: plan.lockSha256, target: plan.graph.target, installedPaths: plan.graph.packages.map(pkg => ({ path: pkg.path, installedName: pkg.installedName, name: pkg.name, version: pkg.version })), edges: plan.graph.edges, excludedNonTargetPackages: plan.graph.excludedNonTargetPackages, packages, sourceInputs, files: files.sort((a, b) => a.path.localeCompare(b.path)), unresolved, limitations: 'Source-only inventory records publisher correspondence, exact inputs and exclusions. Does not assert bit-identical builds, legal status, or unexamined embedded/generated third-party source closure.' };
await writeFile(path.join(output, 'manifest.json'), JSON.stringify(report, null, 2) + '\n');
await writeFile(path.join(output, 'source-input-pins.json'), JSON.stringify(Object.fromEntries(sourceInputs.map(input => [input.url, { bytes: input.bytes, sha256: input.sha256 }])), null, 2) + '\n');
console.log(`Preferred-source audit: ${packages.length} package versions, ${sourceInputs.length} upstream revisions, ${unresolved.length} unresolved; complete=${report.complete}`);
if (unresolved.length && !auditOnly) process.exitCode = 1;

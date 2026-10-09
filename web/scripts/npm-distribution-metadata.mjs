import { mkdir, readFile, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { productionGraph, repositorySource, sha256 } from './npm-distribution-lib.mjs';

const webRoot = fileURLToPath(new URL('../', import.meta.url));
const policy = JSON.parse(await readFile(path.join(webRoot, 'distribution-sources/npm/manifest.json'), 'utf8'));
const lockPath = path.resolve(process.argv[3] || path.join(webRoot, 'package-lock.json'));
const lockBytes = await readFile(lockPath);
const lock = JSON.parse(lockBytes);
let graph = productionGraph(lock, policy.platform);
const output = path.resolve(process.argv[2] || path.join(webRoot, '.next/distribution-sources/npm'));
await mkdir(output, { recursive: true });
await mkdir(path.join(output, 'metadata'), { recursive: true });
const results = new Map();
const unique = [...new Map(graph.packages.map(pkg => [`${pkg.name}@${pkg.version}`, pkg])).values()];
const queue = [...unique];
await Promise.all(Array.from({ length: 4 }, async () => {
  while (queue.length) {
    const pkg = queue.shift();
    if (pkg.gitDependency) {
      results.set(`${pkg.name}@${pkg.version}`, { ...pkg, metadataUrl: null, metadataSha256: null, registryLicense: pkg.declaredLicense, repositoryMetadata: pkg.gitDependency.repository, gitHead: pkg.gitDependency.commit, source: pkg.gitDependency, external: null, unresolved: null, provenance: 'Immutable exact commit in package-lock; not an npm registry tarball' });
      continue;
    }
    const url = `https://registry.npmjs.org/${encodeURIComponent(pkg.name)}/${encodeURIComponent(pkg.version)}`;
    const response = await fetch(url, { signal: AbortSignal.timeout(30000) });
    if (!response.ok) throw new Error(`Npm metadata ${pkg.name}@${pkg.version}: HTTP${response.status}`);
    const bytes = Buffer.from(await response.arrayBuffer());
    const metadata = JSON.parse(bytes);
    if (metadata.name !== pkg.name || metadata.version !== pkg.version || metadata.dist?.tarball !== pkg.tarball || metadata.dist?.integrity !== pkg.integrity) throw new Error(`Exact-version registry metadata disagrees with the lock: ${pkg.name}@${pkg.version}`);
    const external = policy.externalPackages.find(item => item.name === pkg.name && item.version === pkg.version);
    const npmPreferred = (policy.npmPreferredSourcePackages || []).find(item => item.name === pkg.name && item.version === pkg.version);
    const typeOnly = (policy.typeOnlyPackages || []).find(item => item.name === pkg.name && item.version === pkg.version);
    const metadataFile = `metadata/${pkg.name.replaceAll('/', '__').replaceAll('@', '')}-${pkg.version}.json`;
    await writeFile(path.join(output, metadataFile), bytes);
    let source = null, unresolved = null;
    if (!external && !npmPreferred && !typeOnly) {
      try {
        const exception = policy.verifiedExceptions.find(item => item.name === pkg.name && item.version === pkg.version);
        source = exception ? { ...repositorySource({ url: exception.repository, directory: exception.directory }, exception.commit), correspondence: exception } : repositorySource(metadata.repository, metadata.gitHead);
      }
      catch (error) { unresolved = error.message; }
    }
    results.set(`${pkg.name}@${pkg.version}`, { ...pkg, metadataUrl: url, metadataFile, metadataSha256: sha256(bytes), registryLicense: metadata.license || 'NOASSERTION', platformMetadata: { os: metadata.os, cpu: metadata.cpu, libc: metadata.libc }, repositoryMetadata: metadata.repository || null, gitHead: metadata.gitHead || null, source, external: external || null, npmPreferred: npmPreferred || null, typeOnly: typeOnly || null, unresolved });
  }
}));
// Lock v3 may omit registry libc constraints. Re-resolve with verified exact-version platform data.
for (const pkg of graph.packages) Object.assign(lock.packages[pkg.path], results.get(`${pkg.name}@${pkg.version}`).platformMetadata || {});
graph = productionGraph(lock, policy.platform);
const packages = graph.packages.map(pkg => ({ ...results.get(`${pkg.name}@${pkg.version}`), path: pkg.path }));
const report = { schemaVersion: 1, lockSha256: sha256(lockBytes), lockPath: path.relative(webRoot, lockPath).replaceAll('\\', '/'), policy, graph: { ...graph, packages }, complete: false, scope: 'Registry-metadata source plan only; archive availability and preferred-source contents not yet verified', unresolved: packages.filter(pkg => pkg.unresolved).map(pkg => ({ name: pkg.name, version: pkg.version, reason: pkg.unresolved })) };
await writeFile(path.join(output, 'metadata-plan.json'), JSON.stringify(report, null, 2) + '\n');
console.log(`Production graph ${packages.length} installed paths/${unique.length} package versions; ${report.unresolved.length} missing source identities.`);
for (const pkg of report.unresolved) console.log(`UNRESOLVED ${pkg.name}@${pkg.version}: ${pkg.reason}`);

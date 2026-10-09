import { createHash } from 'node:crypto';
import { copyFileSync, existsSync, mkdirSync, readFileSync, readdirSync, realpathSync, writeFileSync } from 'node:fs';
import { dirname, join, relative, resolve, sep } from 'node:path';
import { execFileSync } from 'node:child_process';
import { fileURLToPath } from 'node:url';

const webRoot = fileURLToPath(new URL('../', import.meta.url));
const manifest = JSON.parse(readFileSync(join(webRoot, 'distribution-sources/runtime/manifest.json'), 'utf8'));
const output = resolve(process.argv[2] || join(webRoot, '.next/third-party-notices/runtime'));
if (process.platform !== 'linux' || process.arch !== 'x64') throw new Error('Collect the actual linux/amd64 image, not a host-platform install.');
if (process.versions.node !== manifest.node.version) throw new Error(`Expected Node ${manifest.node.version}; received ${process.versions.node}.`);
const osRelease = readFileSync('/etc/os-release', 'utf8');
if (!/^ID=debian$/m.test(osRelease) || !/^VERSION_ID="?12"?$/m.test(osRelease)) throw new Error('Expected the pinned Debian12 runtime.');
mkdirSync(output, { recursive: true });
const files = [];
function preserve(source, destination) {
  if (!existsSync(source)) throw new Error(`Missing runtime copyright/license: ${source}`);
  mkdirSync(dirname(destination), { recursive: true });
  copyFileSync(source, destination);
  const data = readFileSync(destination);
  files.push({ path: relative(output, destination).replaceAll(sep, '/'), bytes: data.length, sha256: createHash('sha256').update(data).digest('hex'), installedPath: source });
}
function notices(directory, destination) {
  if (!existsSync(directory)) return;
  const root = realpathSync(directory);
  const walk = current => {
    for (const entry of readdirSync(current, { withFileTypes: true })) {
      if (entry.isSymbolicLink()) continue;
      const source = join(current, entry.name);
      if (entry.isDirectory()) walk(source);
      else if (entry.isFile() && /(?:^|[._-])(?:LICEN[CS]E|COPYING|NOTICE[S]?|COPYRIGHT)(?:[._-]|$)/i.test(entry.name)) preserve(source, join(destination, relative(root, source)));
    }
  };
  walk(root);
}

// Capture in the base stage before apt installs build-only tools. Source fields
// resolve binary-to-source naming/version differences and epoch-qualified versions.
const format = '${db:Status-Status}\t${binary:Package}\t${Version}\t${source:Package}\t${source:Version}\t${Architecture}\n';
const rows = execFileSync('dpkg-query', ['-W', `-f=${format}`], { encoding: 'utf8', maxBuffer: 4 * 1024 * 1024 });
const packages = rows.trim().split('\n').map(row => row.split('\t')).filter(row => row[0] === 'installed').map(([, name, version, sourceName, sourceVersion, architecture]) => {
  if (!/^[a-z0-9][a-z0-9+.-]*(?::[a-z0-9]+)?$/.test(name) || !/^[a-z0-9][a-z0-9+.-]*$/.test(sourceName)) throw new Error('Invalid dpkg package identity.');
  if (!sourceVersion || !version || !architecture) throw new Error(`Incomplete source identity for ${name}.`);
  const copyright = `/usr/share/doc/${name.split(':')[0]}/copyright`;
  preserve(copyright, join(output, 'licenses/debian', name, 'copyright'));
  const licenseDeclarations = [...new Set([...readFileSync(copyright, 'utf8').matchAll(/^License:\s*(.+)$/gm)].map(match => match[1].trim()))];
  return { name, version, architecture, sourceName, sourceVersion, licenseDeclarations, licenseConcluded: 'NOASSERTION', copyright: relative(output, join(output, 'licenses/debian', name, 'copyright')).replaceAll(sep, '/'), sourceIndex: `https://snapshot.debian.org/mr/package/${encodeURIComponent(sourceName)}/${encodeURIComponent(sourceVersion)}/srcfiles` };
}).sort((a, b) => a.name.localeCompare(b.name));
if (packages.length < 30) throw new Error('Runtime package inventory is unexpectedly incomplete.');
for (const entry of readdirSync('/usr/share/common-licenses', { withFileTypes: true })) {
  if (entry.isFile()) preserve(join('/usr/share/common-licenses', entry.name), join(output, 'licenses/common', entry.name));
}
preserve('/usr/local/LICENSE', join(output, 'licenses/node/LICENSE'));
notices('/usr/local/lib/node_modules', join(output, 'licenses/node-global'));
notices(`/opt/yarn-v${manifest.node.yarnVersion}`, join(output, 'licenses/yarn'));
const npmVersion = execFileSync('npm', ['--version'], { encoding: 'utf8' }).trim();
const yarnVersion = execFileSync('yarn', ['--version'], { encoding: 'utf8' }).trim();
if (yarnVersion !== manifest.node.yarnVersion) throw new Error('Yarn changed; update the corresponding-source manifest.');
const inventory = { schemaVersion: 1, base: manifest.base, nodeVersion: process.versions.node, npmVersion, yarnVersion, osRelease, packages, noticeFiles: files.sort((a, b) => a.path.localeCompare(b.path)) };
writeFileSync(join(output, 'runtime-inventory.json'), JSON.stringify(inventory, null, 2) + '\n');
copyFileSync(join(webRoot, 'distribution-sources/runtime/manifest.json'), join(output, 'runtime-source-plan.json'));
copyFileSync(join(webRoot, 'distribution-sources/runtime/README.md'), join(output, 'README.md'));

const spdxPackages = [
  { SPDXID: 'SPDXRef-Node', name: 'node', versionInfo: process.versions.node, downloadLocation: manifest.node.source, filesAnalyzed: false, licenseConcluded: 'NOASSERTION', licenseDeclared: 'NOASSERTION', copyrightText: 'See licenses/node/LICENSE: Node MIT license and its bundled third-party licenses' },
  ...packages.map((pkg, index) => ({ SPDXID: `SPDXRef-Debian-${index}`, name: pkg.name, versionInfo: pkg.version, downloadLocation: pkg.sourceIndex, filesAnalyzed: false, licenseConcluded: 'NOASSERTION', licenseDeclared: 'NOASSERTION', copyrightText: `See ${pkg.copyright}`, sourceInfo: `${pkg.sourceName} ${pkg.sourceVersion}; ${pkg.architecture}`, externalRefs: [{ referenceCategory: 'PACKAGE-MANAGER', referenceType: 'purl', referenceLocator: `pkg:deb/debian/${encodeURIComponent(pkg.name.split(':')[0])}@${encodeURIComponent(pkg.version)}?arch=${encodeURIComponent(pkg.architecture)}&distro=debian-12` }] }))
];
writeFileSync(join(output, 'runtime-sbom.spdx.json'), JSON.stringify({ spdxVersion: 'SPDX-2.3', dataLicense: 'CC0-1.0', SPDXID: 'SPDXRef-DOCUMENT', name: `Node Debian runtime ${manifest.base.amd64Digest}`, documentNamespace: `https://github.com/ProdigyV21/ARVIO/runtime-sbom/${manifest.base.amd64Digest.slice(7)}`, creationInfo: { created: new Date().toISOString(), creators: ['Tool: ARVIO runtime-collect.mjs'] }, packages: spdxPackages, relationships: spdxPackages.map(pkg => ({ spdxElementId: 'SPDXRef-DOCUMENT', relationshipType: 'DESCRIBES', relatedSpdxElement: pkg.SPDXID })) }, null, 2) + '\n');
console.log(`Collected actual Node${process.versions.node}/Debian runtime: ${packages.length} packages, ${files.length} copyright/license files.`);

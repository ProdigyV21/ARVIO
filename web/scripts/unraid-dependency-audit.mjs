import { createHash } from 'node:crypto';
import { access, mkdir, readFile, readdir, realpath, stat, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const sha256 = bytes => createHash('sha256').update(bytes).digest('hex');
const slash = value => value.replaceAll('\\', '/');
const forbidden = new Set(['telegram', '@cryptography/aes']);
const packagePath = /(?:^|\/)node_modules\/(?:telegram|@cryptography\/aes)(?:\/|$)/;
// This image is pinned to Linux amd64 glibc. Its native-source provider covers
// the active glibc Sharp/libvips packages, not unused musl alternatives.
const unsupportedNativePath = /(?:^|\/)node_modules\/@img\/(?:sharp|sharp-libvips)-linuxmusl-x64(?:\/|$)/;
// Known literals observed in the exact excluded SDK/AES artifacts, not generic "Telegram" UI text.
export const excludedSdkMarkers = [
  'GramJs:apiCache',
  'Invalid constructor code, vector was expected',
  'AES-CTR mode counter must be 16 bytes length',
  'pq_inner_data_dc#',
  'inputPhoneContact#',
];
export function isLegalFile(name) {
  return /^(?:(?:THIRD[-_]PARTY[-_])?LICEN[CS]ES?|COPYING|(?:THIRD[-_]PARTY[-_])?NOTICES?|COPYRIGHT)(?:[._-]|$)/i.test(name)
    || /\.(?:LEGAL|LICEN[CS]ES?|NOTICES?)(?:[._-]|$)/i.test(name);
}
export function productionPackages(lock) {
  if (lock.lockfileVersion !== 3 || !lock.packages?.['']) throw new Error('Audit requires npm lockfileVersion3');
  const root = lock.packages[''], all = lock.packages, visited = new Set(), result = [];
  const rootDependencies = { ...root.dependencies, ...root.optionalDependencies };
  function resolve(parent, name) {
    let base = parent;
    for (;;) {
      const key = `${base ? base + '/' : ''}node_modules/${name}`;
      if (all[key]) return key;
      if (!base) return null;
      const index = base.lastIndexOf('/node_modules/'); base = index < 0 ? '' : base.slice(0, index);
    }
  }
  function visit(parent, name, optional = false, peer = false) {
    const key = resolve(parent, name);
    if (!key) { if (!optional && !peer) throw new Error(`Missing production lock dependency ${name}`); return; }
    if (peer && (all[key].dev || (Object.hasOwn(root.devDependencies || {}, name) && !Object.hasOwn(rootDependencies, name)))) return;
    if (visited.has(key)) return;
    visited.add(key);
    const record = all[key], installedName = key.slice(key.lastIndexOf('node_modules/') + 13);
    result.push({ path: key, installedName, name: record.name || installedName, version: record.version });
    for (const dependency of Object.keys({ ...record.dependencies, ...record.optionalDependencies })) visit(key, dependency, Object.hasOwn(record.optionalDependencies || {}, dependency));
    for (const dependency of Object.keys(record.peerDependencies || {})) visit(key, dependency, true, true);
  }
  for (const name of Object.keys(rootDependencies)) visit('', name, Object.hasOwn(root.optionalDependencies || {}, name));
  return result.sort((a, b) => a.path.localeCompare(b.path));
}
async function walk(directory) {
  const files = [], root = await realpath(directory), seen = new Set();
  async function visit(current) {
    const absolute = await realpath(current);
    if (absolute !== root && !absolute.startsWith(root + path.sep)) throw new Error(`Artifact symlink escapes audited root: ${current}`);
    if (seen.has(absolute)) return;
    seen.add(absolute);
    for (const item of await readdir(current, { withFileTypes: true })) {
      if (item.name === '.bin' || item.name === '.cache') continue;
      const filename = path.join(current, item.name);
      if (item.isDirectory()) await visit(filename);
      else if (item.isSymbolicLink()) {
        const resolved = await realpath(filename);
        if (resolved !== root && !resolved.startsWith(root + path.sep)) throw new Error(`Artifact symlink escapes audited root: ${filename}`);
        const info = await stat(filename);
        if (info.isDirectory()) await visit(filename);
        else if (info.isFile()) files.push({ absolute: filename, path: slash(path.relative(directory, filename)) });
        else throw new Error(`Special file in artifact: ${filename}`);
      } else if (item.isFile()) files.push({ absolute: filename, path: slash(path.relative(directory, filename)) });
      else throw new Error(`Special file in artifact: ${filename}`);
    }
  }
  await visit(directory); return files;
}
async function exists(filename) { return access(filename).then(() => true, () => false); }
function manifestForbidden(metadata) {
  return forbidden.has(metadata.name) || Object.keys({ ...metadata.dependencies, ...metadata.optionalDependencies }).some(name => forbidden.has(name));
}
export function scanJavaScript(text) {
  const markers = excludedSdkMarkers.filter(marker => text.includes(marker));
  if (/\b(?:require|import)\s*\(\s*['"](?:telegram(?:\/[^'"]*)?|@cryptography\/aes(?:\/[^'"]*)?)['"]\s*\)/.test(text)) markers.push('Executable external SDK import/require');
  if (/\bfrom\s*['"](?:telegram(?:\/[^'"]*)?|@cryptography\/aes(?:\/[^'"]*)?)['"]/.test(text)) markers.push('Executable external SDK import');
  return markers;
}
export async function auditUnraidDependencies(options) {
  const issues = [], limitations = [], findings = [];
  const lockBytes = await readFile(options.lock);
  const lock = JSON.parse(lockBytes), production = productionPackages(lock);
  const excludedProduction = production.filter(pkg => forbidden.has(pkg.name) || forbidden.has(pkg.installedName));
  for (const pkg of excludedProduction) issues.push({ kind: 'excluded-production-dependency', package: pkg });
  const inputFiles = await walk(options.input);
  const inputByPath = new Map(inputFiles.map(file => [file.path, file]));
  const noticeFiles = await walk(options.notices);
  const noticeByPath = new Map(noticeFiles.map(file => [file.path, file]));
  const expectedLegal = inputFiles.filter(file => isLegalFile(path.basename(file.path)));
  const missingLegal = [];
  for (const file of expectedLegal) {
    const bytes = await readFile(file.absolute), copy = noticeByPath.get(`files/${file.path}`);
    if (!copy || sha256(await readFile(copy.absolute)) !== sha256(bytes)) missingLegal.push({ path: file.path, bytes: bytes.length, sha256: sha256(bytes), status: copy ? 'changed' : 'missing' });
  }
  for (const item of missingLegal) issues.push({ kind: 'dependency-legal-file-not-preserved', ...item });
  let javaScriptFiles = 0, javaScriptBytes = 0;
  const runtimePackages = [], runtimeVendoredPackages = [], redistributedNativeAndFontFiles = [];
  const scanned = new Set();
  for (const [scope, directory] of [['standalone', options.standalone], ['client', options.client]]) {
    const runtimeFiles = await walk(directory);
    if (!runtimeFiles.length) issues.push({ kind: 'empty-built-output', scope });
    if (scope === 'standalone' && !runtimeFiles.some(file => file.path === 'server.js')) issues.push({ kind: 'missing-standalone-server' });
    if (scope === 'client' && !runtimeFiles.some(file => /\.[cm]?js$/.test(file.path))) issues.push({ kind: 'missing-client-chunks' });
    for (const file of runtimeFiles) {
      if (packagePath.test(file.path)) issues.push({ kind: 'excluded-sdk-runtime-path', scope, path: file.path });
      if (unsupportedNativePath.test(file.path)) issues.push({ kind: 'unsupported-native-runtime-path', scope, path: file.path, reason: 'Unused musl alternative is outside the pinned Linux amd64 glibc runtime/source plan' });
      if (file.path.endsWith('/package.json') || file.path === 'package.json') {
        const metadata = JSON.parse(await readFile(file.absolute, 'utf8'));
        if (manifestForbidden(metadata)) issues.push({ kind: 'excluded-sdk-runtime-declaration', scope, path: file.path, name: metadata.name || null });
        if (typeof metadata.name === 'string' && /(?:^|\/)node_modules\//.test(file.path)) {
          const record = { path: file.path, name: metadata.name, version: metadata.version || 'NOASSERTION', declaredLicense: metadata.license || metadata.licenses || 'NOASSERTION' };
          runtimePackages.push(record);
          if (file.path.includes('/next/dist/compiled/')) runtimeVendoredPackages.push(record);
        }
      }
      if (/\.(?:wasm|node|so(?:\.[0-9.]+)?|ttf|woff2?|otf)$/.test(file.path)) redistributedNativeAndFontFiles.push({ scope, path: file.path });
      if (isLegalFile(path.basename(file.path)) && file.path.includes('node_modules/')) {
        const relative = file.path.slice(file.path.indexOf('node_modules/') + 13);
        const expected = inputByPath.get(relative), copy = noticeByPath.get(`files/${relative}`);
        const bytes = await readFile(file.absolute);
        if (!copy || sha256(await readFile(copy.absolute)) !== sha256(bytes)) issues.push({ kind: 'runtime-legal-file-not-preserved', scope, path: file.path, expectedBuilderPath: expected ? relative : null });
      }
      if (!/\.[cm]?js$/.test(file.path)) continue;
      const absolute = await realpath(file.absolute);
      if (scanned.has(absolute)) continue;
      scanned.add(absolute);
      const bytes = await readFile(file.absolute); javaScriptFiles++; javaScriptBytes += bytes.length;
      for (const marker of scanJavaScript(bytes.toString('utf8'))) issues.push({ kind: 'excluded-sdk-bundle-marker', scope, path: file.path, marker });
    }
  }
  const statsFiles = [...options.stats || []];
  if (options.statsDirectory) {
    const filenames = (await readdir(options.statsDirectory)).filter(name => /^(?:client|server-[a-z0-9-]+)\.json$/.test(name));
    for (const required of ['client.json', 'server-nodejs.json']) if (!filenames.includes(required)) issues.push({ kind: 'missing-required-webpack-stats', file: required });
    for (const filename of filenames.sort()) statsFiles.push(path.join(options.statsDirectory, filename));
  }
  for (const filename of statsFiles) {
    const stats = JSON.parse(await readFile(filename, 'utf8'));
    const names = [];
    function modules(object) {
      if (!object || typeof object !== 'object') return;
      for (const key of ['identifier', 'name', 'nameForCondition', 'resource']) if (typeof object[key] === 'string') names.push(slash(object[key]));
      for (const key of ['modules', 'children']) for (const child of object[key] || []) modules(child);
    }
    modules(stats);
    // Next may emit an empty edge compilation when no edge-runtime entry is used.
    if (!names.length && path.basename(filename) !== 'server-edge.json') issues.push({ kind: 'empty-webpack-module-stats', file: filename });
    for (const name of new Set(names)) if (packagePath.test(name) || /^external\b.*["'](?:telegram|@cryptography\/aes)(?:\/|["'])/.test(name)) issues.push({ kind: 'excluded-sdk-webpack-module', file: filename, identifier: name });
    findings.push({ kind: 'webpack-module-stats', file: filename, modules: names.length });
  }
  if (!statsFiles.length) limitations.push('No webpack module stats supplied. Output package/declaration and known-literal scans passed only; these are not proof against arbitrary renaming/obfuscation or unidentified generated code.');
  const compiledLegal = expectedLegal.filter(file => file.path.startsWith('next/dist/compiled/'));
  const compiledMissing = missingLegal.filter(file => file.path.startsWith('next/dist/compiled/'));
  limitations.push('Builder notices may include development-only packages (including excluded SDK declarations). Those notice/source references are not treated as redistributed runtime code.');
  limitations.push('Preservation of discovered legal files and declared licences is a packaging check, not legal clearance or proof that publishers supplied every required notice. Native/font/source obligations are tracked separately.');
  return { schemaVersion: 1, passed: issues.length === 0, lockSha256: sha256(lockBytes), scope: 'Telegram-free Unraid built outputs and dependency notice preservation', productionPackages: production.length, excludedProductionPackages: excludedProduction, javaScriptFiles, javaScriptBytes, dependencyLegalFiles: expectedLegal.length, preservedLegalFiles: expectedLegal.length - missingLegal.length, nextCompiledLegalFiles: compiledLegal.length, nextCompiledMissingLegalFiles: compiledMissing, runtimePackages, runtimeVendoredPackages, redistributedNativeAndFontFiles, findings, issues, limitations };
}
if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  const webRoot = fileURLToPath(new URL('../', import.meta.url));
  const args = process.argv.slice(2), parsed = {};
  for (let index = 0; index < args.length; index++) {
    const name = args[index];
    if (!['--standalone', '--client', '--notices', '--lock', '--input', '--stats', '--stats-directory', '--output'].includes(name) || !args[index + 1]) throw new Error(`Unknown or incomplete argument: ${name}`);
    if (name === '--stats') (parsed.stats ||= []).push(path.resolve(args[++index]));
    else parsed[name === '--stats-directory' ? 'statsDirectory' : name.slice(2)] = path.resolve(args[++index]);
  }
  const report = await auditUnraidDependencies({ standalone: path.join(webRoot, '.next/standalone'), client: path.join(webRoot, '.next/static'), notices: path.join(webRoot, '.next/third-party-notices'), lock: path.join(webRoot, 'distribution-sources/unraid/package-lock.json'), input: path.join(webRoot, 'node_modules'), ...parsed });
  const json = JSON.stringify(report, null, 2) + '\n';
  if (parsed.output) {
    for (const directory of [parsed.input || path.join(webRoot, 'node_modules'), parsed.standalone || path.join(webRoot, '.next/standalone'), parsed.client || path.join(webRoot, '.next/static')]) {
      if (parsed.output === directory || parsed.output.startsWith(directory + path.sep)) throw new Error('Generated audit report must not alter dependency or runtime code inputs');
    }
    await mkdir(path.dirname(parsed.output), { recursive: true });
    await writeFile(parsed.output, json);
    console.log(`Unraid dependency audit ${report.passed ? 'passed' : 'FAILED'}: ${report.javaScriptFiles} JS files, ${report.productionPackages} production-lock packages, ${report.preservedLegalFiles}/${report.dependencyLegalFiles} legal files preserved, ${report.issues.length} issues. Report: ${parsed.output}`);
  } else console.log(json);
  if (!report.passed) process.exitCode = 1;
}

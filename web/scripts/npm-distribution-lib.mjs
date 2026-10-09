import { createHash } from 'node:crypto';
import { gunzipSync, gzipSync } from 'node:zlib';

export const sha256 = data => createHash('sha256').update(data).digest('hex');
export function platformMatches(record, target) {
  const permits = (values, actual) => {
    if (!values) return true;
    const list = Array.isArray(values) ? values : [values];
    if (list.includes(`!${actual}`)) return false;
    const positive = list.filter(value => !value.startsWith('!'));
    return !positive.length || positive.includes(actual) || positive.includes('any');
  };
  return permits(record.os, target.os) && permits(record.cpu, target.cpu) && permits(record.libc, target.libc);
}
export function productionGraph(lock, target = { os: 'linux', cpu: 'x64', libc: 'glibc' }) {
  if (lock.lockfileVersion !== 3 || !lock.packages?.['']) throw new Error('Require npm lockfileVersion3 with package records.');
  const all = lock.packages;
  const rootProduction = { ...all[''].dependencies, ...all[''].optionalDependencies };
  const resolveDependency = (parent, name) => {
    let base = parent;
    for (;;) {
      const key = `${base ? base + '/' : ''}node_modules/${name}`;
      if (all[key]) return key;
      if (!base) return null;
      const position = base.lastIndexOf('/node_modules/');
      base = position < 0 ? '' : base.slice(0, position);
    }
  };
  const visited = new Map(), edges = [], excluded = [];
  const visit = (parent, dependency, optional = false, peer = false) => {
    const key = resolveDependency(parent, dependency);
    if (!key) { if (!optional && !peer) throw new Error(`Missing production dependency ${dependency} from ${parent || 'root'}`); return; }
    const record = all[key];
    if (peer && (record.dev === true || (Object.hasOwn(all[''].devDependencies || {}, dependency) && !Object.hasOwn(rootProduction, dependency)))) return; // Root build/test/type-only peer tools are not shipped application modules.
    if (!platformMatches(record, target)) { if (!optional) throw new Error(`Required package cannot run on target: ${key}`); excluded.push(key); return; }
    edges.push({ from: parent || 'root', to: key, optional, peer });
    if (visited.has(key)) return;
    const gitRef = /^(git\+[^#]+)#([a-f0-9]{40})$/.exec(record.resolved || '');
    if (!record.version || !record.resolved || (!record.integrity && !gitRef) || record.link) throw new Error(`Unpinned production package ${key}`);
    const installedName = key.slice(key.lastIndexOf('node_modules/') + 13);
    const name = record.name || installedName; // npm aliases retain a different canonical registry name.
    visited.set(key, { path: key, installedName, name, version: record.version, tarball: record.integrity ? record.resolved : null, integrity: record.integrity || null, gitDependency: gitRef ? repositorySource(gitRef[1], gitRef[2]) : null, declaredLicense: record.license || 'NOASSERTION', optional: Boolean(record.optional) });
    const dependencies = { ...record.dependencies, ...record.optionalDependencies };
    for (const name of Object.keys(dependencies).sort()) visit(key, name, Object.hasOwn(record.optionalDependencies || {}, name));
    for (const name of Object.keys(record.peerDependencies || {}).sort()) visit(key, name, true, true);
  };
  for (const name of Object.keys({ ...all[''].dependencies, ...all[''].optionalDependencies }).sort()) visit('', name, Object.hasOwn(all[''].optionalDependencies || {}, name));
  return { target, packages: [...visited.values()].sort((a, b) => a.path.localeCompare(b.path)), edges, excludedNonTargetPackages: [...new Set(excluded)].sort() };
}
export function verifyIntegrity(bytes, integrity) {
  const entries = String(integrity).split(/\s+/).map(value => /^(sha(?:256|384|512))-([A-Za-z0-9+/]+={0,2})$/.exec(value)).filter(Boolean);
  if (!entries.length || !entries.some(([, algorithm, expected]) => createHash(algorithm).update(bytes).digest('base64') === expected)) throw new Error('Npm archive does not match lock integrity.');
}
export function repositorySource(repository, gitHead) {
  if (!/^[a-f0-9]{40}$/.test(gitHead || '')) throw new Error('Exact npm metadata does not provide an immutable40-character gitHead.');
  const raw = typeof repository === 'string' ? repository : repository?.url;
  if (!raw) throw new Error('Exact npm metadata has no upstream repository.');
  let value = raw.replace(/^git\+/, '').replace(/^git:\/\//, 'https://').replace(/^ssh:\/\/git@/, 'https://');
  if (/^git@github\.com:/.test(value)) value = value.replace(/^git@github\.com:/, 'https://github.com/');
  if (/^github:/.test(value)) value = value.replace(/^github:/, 'https://github.com/');
  const url = new URL(value);
  const project = url.pathname.replace(/^\//, '').replace(/\.git\/?$/, '').replace(/\/$/, '');
  // A repository metadata branch fragment does not override the separately pinned npm gitHead.
  if (url.username || url.password || url.search || !/^[A-Za-z0-9_.-]+\/[A-Za-z0-9_.-]+$/.test(project)) throw new Error('Repository identity needs explicit review rather than guessed URL conversion.');
  const directory = typeof repository === 'object' && repository?.directory ? repository.directory : '';
  if (directory.startsWith('/') || directory.includes('\\') || directory.split('/').some(part => part === '..')) throw new Error('Unsafe metadata repository directory.');
  if (url.hostname === 'github.com') return { repository: `https://github.com/${project}`, directory, commit: gitHead, archive: `https://codeload.github.com/${project}/tar.gz/${gitHead}` };
  if (url.hostname === 'gitlab.com') return { repository: `https://gitlab.com/${project}`, directory, commit: gitHead, archive: `https://gitlab.com/${project}/-/archive/${gitHead}/${project.split('/')[1]}-${gitHead}.tar.gz` };
  if (url.hostname === 'bitbucket.org') return { repository: `https://bitbucket.org/${project}`, directory, commit: gitHead, archive: `https://bitbucket.org/${project}/get/${gitHead}.tar.gz` };
  throw new Error(`Unsupported source host ${url.hostname}; explicit verified source correspondence is required.`);
}

// Read tar in memory without executing scripts or extracting symlinks, special files or paths.
export function readTarGz(bytes) {
  const tar = gunzipSync(bytes, { maxOutputLength: 512 * 1024 * 1024 });
  const files = [], seen = new Set();
  let pending = {}, longName = null;
  const field = (header, start, length) => header.subarray(start, start + length).toString('utf8').replace(/\0.*$/, '');
  for (let cursor = 0; cursor + 512 <= tar.length;) {
    const header = tar.subarray(cursor, cursor + 512);
    if (header.every(byte => byte === 0)) break;
    const expected = parseInt(field(header, 148, 8).trim(), 8);
    let checksum = 0;
    for (let index = 0; index < 512; index++) checksum += index >= 148 && index < 156 ? 32 : header[index];
    if (expected !== checksum) throw new Error('Invalid tar header checksum');
    const sizeField = field(header, 124, 12).trim();
    if (!/^[0-7]+$/.test(sizeField)) throw new Error('Unsupported tar size encoding');
    const size = parseInt(sizeField, 8), type = field(header, 156, 1);
    if (!Number.isSafeInteger(size) || cursor + 512 + size > tar.length) throw new Error('Truncated tar entry');
    const data = tar.subarray(cursor + 512, cursor + 512 + size);
    cursor += 512 + Math.ceil(size / 512) * 512;
    if (type === 'x' || type === 'g') {
      let position = 0;
      while (position < data.length) {
        const space = data.indexOf(32, position), count = Number(data.subarray(position, space).toString());
        if (space < 0 || !Number.isInteger(count) || count <= 0 || position + count > data.length) throw new Error('Invalid PAX record');
        const text = data.subarray(space + 1, position + count - 1).toString('utf8'), equals = text.indexOf('=');
        if (type === 'x') pending[text.slice(0, equals)] = text.slice(equals + 1);
        position += count;
      }
      continue;
    }
    if (type === 'L') { longName = data.toString('utf8').replace(/\0.*$/, ''); continue; }
    const prefix = field(header, 345, 155);
    const name = (pending.path || longName || (prefix ? prefix + '/' : '') + field(header, 0, 100)).replace(/^\.\//, '');
    pending = {}; longName = null;
    if (!name || name.startsWith('/') || name.includes('\\') || name.split('/').some(part => part === '..') || /[\x00-\x1f]/.test(name)) throw new Error('Unsafe tar path');
    if (type === '5' || type === '2' || type === '1') continue; // Do not follow archive links.
    if (type !== '' && type !== '0') throw new Error(`Unsupported tar entry type ${type}`);
    if (seen.has(name)) throw new Error(`Duplicate tar path ${name}`);
    seen.add(name); files.push({ path: name, bytes: Buffer.from(data) });
  }
  return files;
}
export function sourceFiles(entries) {
  const result = [], excluded = [];
  const decoder = new TextDecoder('utf-8', { fatal: true });
  for (const entry of entries) {
    const name = entry.path.split('/').slice(1).join('/');
    if (!name) continue;
    const base = name.split('/').at(-1);
    let reason = null;
    if (/(^|\/)(node_modules|\.git|fixtures?|test-data|test-media|testdata|screenshots?|coverage|\.next)(\/|$)/i.test(name)) reason = 'Non-build fixture, generated output or nested dependency tree';
    else if (/\.(wasm|node|a|o|so(?:\.[0-9.]+)?|dll|exe|bin|png|jpe?g|gif|webp|ico|mp[34]|mkv|webm|wav|flac|ogg|pdf|woff2?|ttf|otf|zip|tgz|gz|xz|bz2|tar|7z)$/i.test(base)) reason = 'Compiled artifact, archive, media or font; not preferred textual source';
    else {
      try { decoder.decode(entry.bytes); if (entry.bytes.includes(0)) reason = 'Binary data'; }
      catch { reason = 'Non-UTF8 input; requires explicit preferred-source review'; }
    }
    if (reason) excluded.push({ path: name, bytes: entry.bytes.length, sha256: sha256(entry.bytes), reason });
    else result.push({ path: name, bytes: entry.bytes });
  }
  return { files: result, excluded };
}
export function createTarGz(entries) {
  const blocks = [];
  for (const entry of [...entries].sort((a, b) => a.path.localeCompare(b.path))) {
    const name = Buffer.from(entry.path);
    if (name.length > 100) {
      // POSIX PAX preserves arbitrarily long UTF8 source filenames.
      const content = `path=${entry.path}\n`;
      let length = Buffer.byteLength(content) + 2;
      while (Buffer.byteLength(`${length} ${content}`) !== length) length = Buffer.byteLength(`${length} ${content}`);
      const data = Buffer.from(`${length} ${content}`);
      blocks.push(tarHeader('PaxHeader', data.length, 'x'), data, Buffer.alloc((512 - data.length % 512) % 512));
    }
    blocks.push(tarHeader(name.length > 100 ? 'source-file' : entry.path, entry.bytes.length, '0'), entry.bytes, Buffer.alloc((512 - entry.bytes.length % 512) % 512));
  }
  blocks.push(Buffer.alloc(1024));
  return gzipSync(Buffer.concat(blocks), { level: 9, mtime: 0 });
}
function tarHeader(name, size, type) {
  const header = Buffer.alloc(512);
  header.write(name, 0, 100); header.write('0000644\0', 100); header.write('0000000\0', 108); header.write('0000000\0', 116);
  header.write(size.toString(8).padStart(11, '0') + '\0', 124); header.write('00000000000\0', 136);
  header.fill(32, 148, 156); header.write(type, 156); header.write('ustar\0', 257); header.write('00', 263);
  let sum = 0; for (const byte of header) sum += byte;
  header.write(sum.toString(8).padStart(6, '0') + '\0 ', 148);
  return header;
}

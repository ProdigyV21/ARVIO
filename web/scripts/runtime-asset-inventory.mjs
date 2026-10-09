import { createHash } from 'node:crypto';
import { copyFileSync, mkdirSync, readFileSync, readdirSync, writeFileSync } from 'node:fs';
import { join, relative, resolve, sep } from 'node:path';
import { fileURLToPath } from 'node:url';

const webRoot = fileURLToPath(new URL('../', import.meta.url));
const publicRoot = join(webRoot, 'public');
const output = resolve(process.argv[2] || join(webRoot, '.next/third-party-notices/runtime'));
if (output === publicRoot || output.startsWith(publicRoot + sep)) throw new Error('Generate the inventory outside public; copy the completed distribution bundle afterwards.');
mkdirSync(output, { recursive: true });
const entries = [];
function walk(directory) {
  for (const entry of readdirSync(directory, { withFileTypes: true })) {
    if (entry.isSymbolicLink()) throw new Error('Do not follow asset symlinks outside the public tree.');
    const source = join(directory, entry.name);
    if (entry.isDirectory()) { walk(source); continue; }
    if (!entry.isFile()) continue;
    const path = relative(publicRoot, source).replaceAll(sep, '/');
    const data = readFileSync(source);
    const metadata = path === 'data/channel-logos.json';
    const brand = path.startsWith('logos/') || path.startsWith('badges/') || path === 'tmdb-logo.svg';
    entries.push({ path: `public/${path}`, bytes: data.length, sha256: createHash('sha256').update(data).digest('hex'), category: metadata ? 'channel metadata (no bundled channel-logo image binaries)' : brand ? 'third-party identifying mark/badge' : path.startsWith('avatars/') ? 'profile avatar' : path.startsWith('images/sports/') ? 'sports illustration' : path.startsWith('i18n/') ? 'translation JSON' : 'project public asset', projectDeclaredLicense: 'Apache-2.0 (repository declaration; not an individual authorship/trademark-rights guarantee)', licenseConcluded: metadata ? 'Unlicense (metadata only)' : 'NOASSERTION', provenance: metadata ? 'Generated from https://iptv-org.github.io/api/ by scripts/update-channel-logo-directory.mjs; see channel-logo-fallbacks.md' : 'Exact original asset author/source/reuse terms are not independently recorded by this inventory', trademarkNote: brand ? 'Identification does not imply ownership, endorsement, or a trademark license' : null });
  }
}
walk(publicRoot);
entries.sort((a, b) => a.path.localeCompare(b.path));
writeFileSync(join(output, 'public-assets.json'), JSON.stringify({ schemaVersion: 1, sourceProject: 'https://github.com/ProdigyV21/ARVIO', purpose: 'Exact distributed-file inventory, not proof of original authorship or a legal determination. No feature assets are removed.', files: entries }, null, 2) + '\n');
copyFileSync(join(webRoot, 'distribution-sources/runtime/ASSET-NOTICES.md'), join(output, 'ASSET-NOTICES.md'));
console.log(`Inventoried ${entries.length} public assets with SHA256 and explicit provenance limits.`);

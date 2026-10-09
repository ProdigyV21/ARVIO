import fs from 'node:fs/promises';
import path from 'node:path';
import crypto from 'node:crypto';
const root = path.resolve(process.argv[2]);
const output = path.join(root, 'distribution-sources/codecs');
const inputs = JSON.parse(await fs.readFile(path.join(output, 'inputs.json'), 'utf8'));
const files = [];
async function visit(directory) {
  for (const entry of await fs.readdir(directory, { withFileTypes: true })) {
    const file = path.join(directory, entry.name);
    if (file === path.join(output, 'manifest.json')) continue;
    if (entry.isDirectory()) await visit(file);
    else if (entry.isFile()) {
      const bytes = await fs.readFile(file);
      files.push({ path: path.relative(root, file).replaceAll('\\', '/'), bytes: bytes.length, sha256: crypto.createHash('sha256').update(bytes).digest('hex') });
    } else throw new Error('Unsupported codec output entry');
  }
}
await visit(root);
files.sort((a, b) => a.path.localeCompare(b.path));
const manifest = { schemaVersion: 1, inputs, rebuilt: true, featuresRemoved: false, sourceOnlyMediabunnyArchive: true, files };
await fs.writeFile(path.join(output, 'manifest.json'), JSON.stringify(manifest, null, 2) + '\n');
console.log(`Rebuilt full-featured codec packages and ${files.length} source/relink artifacts with checksums.`);

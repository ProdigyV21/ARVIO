// Read-only research helper: inspect exact locked package sources and authoritative release refs.
import { readFile } from 'node:fs/promises';
import { fileURLToPath } from 'node:url';
import { readTarGz, verifyIntegrity } from './npm-distribution-lib.mjs';

const plan = JSON.parse(await readFile(fileURLToPath(new URL('../../.planning/npm-distribution-audit/metadata-plan.json', import.meta.url)), 'utf8'));
const names = process.argv.slice(2);
for (const pkg of plan.graph.packages.filter(pkg => names.includes(pkg.name))) {
  if (!pkg.tarball) continue;
  const response = await fetch(pkg.tarball, { signal: AbortSignal.timeout(60000) });
  if (!response.ok) throw new Error(`HTTP ${response.status}`);
  const bytes = Buffer.from(await response.arrayBuffer()); verifyIntegrity(bytes, pkg.integrity);
  const entries = readTarGz(bytes);
  const metadata = entries.find(file => file.path === 'package/package.json');
  console.log(JSON.stringify({ name: pkg.name, version: pkg.version, package: metadata ? JSON.parse(metadata.bytes) : null, files: entries.map(file => ({ path: file.path, bytes: file.bytes.length })) }));
  for (const file of entries.filter(file => /\.(?:js|d\.ts)$/.test(file.path) && !/\.min\.js$/.test(file.path)).slice(0, 2)) console.log(file.path + '\n' + file.bytes.toString('utf8').slice(0, 1800));
}

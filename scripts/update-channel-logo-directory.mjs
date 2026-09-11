import { mkdir, writeFile } from 'node:fs/promises';
import { fileURLToPath } from 'node:url';

// Metadata only: never fetch stream lists, guide feeds or image binaries.
const base = 'https://iptv-org.github.io/api/';
async function read(name) {
  const response = await fetch(base + name, { signal: AbortSignal.timeout(30000) });
  if (!response.ok) throw new Error(`${name}: ${response.status}`);
  return response.json();
}
const channels = await read('channels.json');
const logos = await read('logos.json');
const byId = new Map();
for (const logo of logos) {
  if (!logo.in_use || logo.feed || !/^https:\/\//i.test(logo.url) || logo.width < 32 || logo.height < 16) continue;
  if (!['PNG', 'SVG', 'WebP', 'JPEG'].includes(logo.format)) continue;
  const list = byId.get(logo.channel) ?? [];
  list.push(logo);
  byId.set(logo.channel, list);
}
const score = logo => (logo.url.includes('upload.wikimedia.org') ? 4 : 0) + (logo.tags.includes('white') ? 2 : 0) + (logo.format === 'PNG' ? 1 : 0);
const entries = channels.filter(ch => !ch.closed && !ch.is_nsfw && byId.has(ch.id)).map(ch => {
  const urls = [...new Set(byId.get(ch.id).sort((a, b) => score(b) - score(a)).map(l => l.url))].slice(0, 2);
  return [ch.id, ch.country, [ch.name, ...ch.alt_names], urls];
}).sort((a, b) => a[0].localeCompare(b[0]));
if (entries.length < 1000) throw new Error('Refusing incomplete directory');
const data = JSON.stringify({ version: 1, source: base, updated: new Date().toISOString().slice(0, 10), entries });
for (const target of ['../app/src/main/assets/channel-logos.json', '../web/public/data/channel-logos.json']) {
  const path = fileURLToPath(new URL(target, import.meta.url));
  await mkdir(fileURLToPath(new URL('.', new URL(target, import.meta.url))), { recursive: true });
  await writeFile(path, data + '\n');
}
console.log(JSON.stringify({ entries: entries.length, bytes: Buffer.byteLength(data) }));

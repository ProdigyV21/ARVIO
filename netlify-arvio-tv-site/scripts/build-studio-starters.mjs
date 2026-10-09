import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { STARTERS } from '../collection-studio/starters.mjs';
import { encodeShare, serializeCollections } from '../collection-studio/studio-core.mjs';

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const escape = value => String(value).replace(/[&<>"']/g, c => ({'&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;'}[c]));
const cards = STARTERS.map(starter => {
  const jsonPath = `/collection-studio/examples/${starter.id}.json`;
  fs.mkdirSync(path.join(root, 'collection-studio/examples'), { recursive: true });
  fs.writeFileSync(path.join(root, jsonPath), serializeCollections(starter.draft) + '\n');
  return `<article class="starter-card"><p class="eyebrow">${starter.draft.folders.length} discovery folders</p><h3>${escape(starter.title)}</h3><p>${escape(starter.description)}</p><ul>${starter.draft.folders.map(f => `<li>${escape(f.title)}</li>`).join('')}</ul><div class="starter-actions"><a class="button secondary" href="/collection-studio/${encodeShare(starter.draft)}" target="_blank" rel="noopener">Preview &amp; customize <span aria-hidden="true">↗</span></a><a href="${jsonPath}" download>Download JSON</a></div></article>`;
}).join('\n');
const file = path.join(root, 'collection-studio/index.html');
const source = fs.readFileSync(file, 'utf8');
const marker = /<!-- starters:start -->[\s\S]*?<!-- starters:end -->/;
if (!marker.test(source)) throw Error('Missing Studio starter markers');
fs.writeFileSync(file, source.replace(marker, `<!-- starters:start -->\n${cards}\n<!-- starters:end -->`));
console.log(`Built ${STARTERS.length} public-safe Studio previews and JSON files.`);

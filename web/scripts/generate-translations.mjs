import fs from 'node:fs';
import path from 'node:path';
import crypto from 'node:crypto';
import { fileURLToPath } from 'node:url';
import {androidLanguages, localeFor, requiredPhrases, normalize} from './translation-sources.mjs';

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const resources = path.resolve(root, '../app/src/main/res');
const decode = text => text.replace(/<!\[CDATA\[([\s\S]*?)\]\]>/g, '$1').replace(/<[^>]*>/g, '')
  .replace(/&#(x[\da-f]+|\d+);/gi, (_, n) => String.fromCodePoint(n[0] === 'x' ? parseInt(n.slice(1), 16) : Number(n)))
  .replace(/&(lt|gt|amp|quot|apos);/g, (_, e) => ({lt:'<',gt:'>',amp:'&',quot:'"',apos:"'"}[e]))
  .replace(/\\n/g, '\n').replace(/\\t/g, '\t').replace(/\\(["'@?\\])/g, '$1').replace(/^"([\s\S]*)"$/, '$1');
function read(file) {
  return Object.fromEntries([...fs.readFileSync(file, 'utf8').matchAll(/<string\s+([^>]*\bname="([^"]+)"[^>]*)>([\s\S]*?)<\/string>/g)]
    .filter(m => !/translatable="false"/.test(m[1])).map(m => [m[2], decode(m[3])]));
}
function readDirectory(directory) {
  return Object.assign({}, ...fs.readdirSync(directory).filter(file => file.endsWith('.xml')).sort().map(file => read(path.join(directory, file))));
}
const english = readDirectory(path.join(resources, 'values'));
const output = path.join(root, 'public/i18n');
fs.mkdirSync(output, {recursive:true});
const manifest = {};
const dictionaries = new Map();
for (const dir of fs.readdirSync(resources).filter(d => /^values-[a-z]{2}(-r[A-Z]{2})?$/.test(d))) {
  const locale = dir.slice(7).replace('-r', '-').replace(/^iw$/, 'he').replace(/^in$/, 'id');
  const translated = readDirectory(path.join(resources, dir));
  const dictionary = {};
  // Keep the primary resource when Android has several context-specific IDs
  // with the same English wording. Web-specific overrides remain explicit.
  for (const [key, value] of Object.entries(translated)) if (english[key] && value && !(english[key] in dictionary)) dictionary[english[key]] = value;
  dictionaries.set(locale, dictionary);
}
const languages = androidLanguages();
for (const {code} of languages) if (!code.startsWith('en')) {
  const locale=localeFor(code);
  if (!dictionaries.has(locale)) dictionaries.set(locale, {});
}
const phrases = requiredPhrases();
for (const [locale, own] of dictionaries) {
  const dictionary = {...(locale.includes('-') ? dictionaries.get(locale.split('-')[0]) ?? {} : {}), ...own};
  const extras = path.join(root, 'lib/i18n', `${locale}.json`);
  if (fs.existsSync(extras)) Object.assign(dictionary, JSON.parse(fs.readFileSync(extras, 'utf8')));
  // Machine-seeded gap translations never overwrite community/app translations.
  const seed = path.join(root, 'lib/i18n/generated', `${locale}.json`);
  const index = new Map(Object.keys(dictionary).map(key=>[normalize(key), key]));
  if (fs.existsSync(seed)) for (const [key, value] of Object.entries(JSON.parse(fs.readFileSync(seed,'utf8')))) {
    if (!index.has(normalize(key))) { dictionary[key]=value; index.set(normalize(key),key); }
  }
  // The Library tab uses the compact spelling of the existing Home Server label.
  dictionary.Homeserver ??= dictionary['Home Server'];
  // Newly introduced UI remains readable until a community/explicit locale
  // translation arrives. Keep this fallback after all translated sources.
  const covered = new Set(Object.keys(dictionary).map(normalize));
  for (const phrase of phrases) if (!covered.has(normalize(phrase))) dictionary[phrase] = phrase;
  const data = JSON.stringify(dictionary);
  fs.writeFileSync(path.join(output, `${locale}.json`), data + '\n');
  manifest[locale] = crypto.createHash('sha256').update(data).digest('hex').slice(0, 12);
}
fs.mkdirSync(path.join(root, 'lib/i18n'), {recursive:true});
fs.writeFileSync(path.join(root, 'lib/i18n/manifest.json'), JSON.stringify(manifest, null, 2) + '\n');
fs.writeFileSync(path.join(root, 'lib/i18n/languages.json'), JSON.stringify(languages, null, 2) + '\n');
fs.writeFileSync(path.join(root, 'lib/i18n/phrases.json'), JSON.stringify(phrases, null, 2) + '\n');
console.log(`Exported ${Object.keys(manifest).length} Android interface languages.`);

import { createHash } from "node:crypto";
import { readFileSync } from "node:fs";
import { dirname, join, resolve } from "node:path";
import { fileURLToPath } from "node:url";
import { localeFor, normalize, requiredPhrases } from "./translation-sources.mjs";

const webRoot = resolve(dirname(fileURLToPath(import.meta.url)), "..");
const readJson = file => JSON.parse(readFileSync(file, "utf8"));
const slots = text => [...text.matchAll(/\{\w+\}/g)].map(match => match[0]).sort().join("|");
const object = value => value && typeof value === "object" && !Array.isArray(value);

/** Container contexts contain web/ only. Validate the checked-in generated
 * assets instead of silently attempting to read absent Android resources. */
export function validatePrebuiltTranslations(root = webRoot, expectedPhrases = requiredPhrases()) {
  const manifest = readJson(join(root, "lib/i18n/manifest.json"));
  const languages = readJson(join(root, "lib/i18n/languages.json"));
  const phrases = readJson(join(root, "lib/i18n/phrases.json"));
  if (!object(manifest) || !Object.keys(manifest).length) throw new Error("Missing prebuilt translation manifest.");
  if (!Array.isArray(languages) || !languages.length || languages.some(value => !object(value) ||
    typeof value.code !== "string" || typeof value.label !== "string" || !value.label.trim())) {
    throw new Error("Invalid prebuilt language catalog.");
  }
  if (!Array.isArray(phrases) || phrases.some(value => typeof value !== "string") ||
      JSON.stringify(phrases) !== JSON.stringify(expectedPhrases)) {
    throw new Error("Prebuilt UI phrases are stale. Generate translations from the full repository before packaging.");
  }
  for (const { code } of languages) {
    if (code.startsWith("en")) continue;
    if (!Object.hasOwn(manifest, localeFor(code))) throw new Error(`Missing prebuilt dictionary for language ${code}.`);
  }
  for (const [locale, hash] of Object.entries(manifest)) {
    if (!/^[a-z]{2}(?:-[A-Z]{2})?$/.test(locale) || typeof hash !== "string" || !/^[a-f0-9]{12}$/.test(hash)) {
      throw new Error("Invalid prebuilt locale/hash in translation manifest.");
    }
    const dictionary = readJson(join(root, "public/i18n", `${locale}.json`));
    if (!object(dictionary) || Object.values(dictionary).some(value => typeof value !== "string")) {
      throw new Error(`Invalid prebuilt dictionary ${locale}.`);
    }
    const actualHash = createHash("sha256").update(JSON.stringify(dictionary)).digest("hex").slice(0, 12);
    if (actualHash !== hash) throw new Error(`Prebuilt dictionary ${locale} does not match its manifest hash.`);
    const indexed = new Map(Object.entries(dictionary).map(([key, value]) => [normalize(key), value]));
    for (const phrase of phrases) {
      const value = indexed.get(normalize(phrase));
      if (!value?.trim() || slots(value) !== slots(phrase) || /[⟦⟧⟪⟫]/.test(value)) {
        throw new Error(`Incomplete prebuilt translation in ${locale}: ${phrase}`);
      }
    }
  }
  return { locales: Object.keys(manifest).length, phrases: phrases.length };
}

if (process.argv[1] && resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  const checked = validatePrebuiltTranslations();
  console.log(`Validated ${checked.locales} packaged interface languages (${checked.phrases} phrases each).`);
}

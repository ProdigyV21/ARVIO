// Indexable Premium pages per language: /<dir>/premium/ is the English
// premium/index.html with that language's copy rendered into the HTML, its own
// <html lang>, title, description and canonical, and hreflang links between
// all of them. premium.js keeps the language picker working on every page.
// Run after scripts/build-premium.py (which writes premium/languages.json).
import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const siteRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const base = "https://arvio.tv";
export const premiumLocales = [
  { code: "de-DE", dir: "de", hreflang: "de" },
  { code: "fr-FR", dir: "fr", hreflang: "fr" },
  { code: "nl-NL", dir: "nl", hreflang: "nl" },
  { code: "it-IT", dir: "it", hreflang: "it" },
  { code: "es-ES", dir: "es", hreflang: "es" },
  { code: "pt-BR", dir: "pt-br", hreflang: "pt-BR" },
  { code: "sv-SE", dir: "sv", hreflang: "sv" },
  { code: "da-DK", dir: "da", hreflang: "da" },
  { code: "no-NO", dir: "no", hreflang: "nb" },
  { code: "fi-FI", dir: "fi", hreflang: "fi" },
  { code: "hi-IN", dir: "hi", hreflang: "hi" }
];

const template = fs.readFileSync(path.join(siteRoot, "premium/index.html"), "utf8");
const languages = JSON.parse(fs.readFileSync(path.join(siteRoot, "premium/languages.json"), "utf8"));
const escapeHtml = (text) => String(text).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
const escapeAttr = (text) => escapeHtml(text).replace(/"/g, "&quot;");

function hreflangBlock() {
  return [
    "<!-- hreflang:start -->",
    `  <link rel="alternate" hreflang="en" href="${base}/premium/">`,
    ...premiumLocales.map(({ dir, hreflang }) => `  <link rel="alternate" hreflang="${hreflang}" href="${base}/${dir}/premium/">`),
    `  <link rel="alternate" hreflang="x-default" href="${base}/premium/">`,
    "  <!-- hreflang:end -->"
  ].join("\n");
}

function withHreflang(html) {
  const block = hreflangBlock();
  if (/<!-- hreflang:start -->[\s\S]*?<!-- hreflang:end -->/.test(html)) return html.replace(/<!-- hreflang:start -->[\s\S]*?<!-- hreflang:end -->/, block);
  return html.replace(/(<link rel="canonical"[^>]*>)/, `$1\n  ${block}`);
}

function render(locale) {
  const copy = { ...languages["en-US"], ...languages[locale.code] };
  let html = template;
  // Text of every element marked data-copy.
  html = html.replace(/(<([a-z0-9]+)\b[^>]*\bdata-copy="([A-Za-z]+)"[^>]*>)([^<]*)(<\/\2>)/g,
    (whole, open, tag, key, _text, close) => copy[key] ? `${open}${escapeHtml(copy[key])}${close}` : whole);
  const title = `ARVIO Premium — ${copy.headline}`;
  const description = `${copy.lead} ${copy.fromPrice}`.trim();
  const url = `${base}/${locale.dir}/premium/`;
  html = html
    .replace(/<html lang="[^"]*">/, `<html lang="${locale.hreflang}" data-page-lang="${locale.code}">`)
    .replace(/<title>[\s\S]*?<\/title>/, `<title>${escapeHtml(title)}</title>`)
    .replace(/<meta name="description" content="[^"]*">/, `<meta name="description" content="${escapeAttr(description)}">`)
    .replace(/<link rel="canonical" href="[^"]*">/, `<link rel="canonical" href="${url}">`)
    .replace(/<meta property="og:title" content="[^"]*">/, `<meta property="og:title" content="${escapeAttr(title)}">`)
    .replace(/<meta property="og:description" content="[^"]*">/, `<meta property="og:description" content="${escapeAttr(description)}">`)
    // Assets live in /premium/.
    .replace(/(href|src)="\.\/(?!\/)/g, '$1="/premium/');
  return withHreflang(html);
}

for (const locale of premiumLocales) {
  const dir = path.join(siteRoot, locale.dir, "premium");
  fs.mkdirSync(dir, { recursive: true });
  fs.writeFileSync(path.join(dir, "index.html"), render(locale), "utf8");
}
fs.writeFileSync(path.join(siteRoot, "premium/index.html"), withHreflang(template), "utf8");
fs.writeFileSync(path.join(siteRoot, "premium/localized-routes.json"), JSON.stringify(premiumLocales.map(({ dir }) => `/${dir}/premium/`), null, 2) + "\n", "utf8");
console.log(`Built ${premiumLocales.length} localized Premium pages.`);

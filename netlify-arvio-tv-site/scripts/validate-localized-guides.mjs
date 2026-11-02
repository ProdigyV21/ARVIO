import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { standaloneGuideRoutes } from "./standalone-guide-routes.mjs";

const siteRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const baseUrl = "https://arvio.tv";
// pt-BR and es, plus every language in scripts/guide-locales/.
const extraLocales = fs.readdirSync(path.join(siteRoot, "scripts/guide-locales")).filter((name) => name.endsWith(".json"))
  .map((name) => JSON.parse(fs.readFileSync(path.join(siteRoot, "scripts/guide-locales", name), "utf8")));
const roots = ["pt-br", "es", ...extraLocales.map((data) => data.slugs.home.replaceAll("/", ""))];
const localizedPremium = JSON.parse(fs.readFileSync(path.join(siteRoot, "premium/localized-routes.json"), "utf8"));
const errors = [];
const pages = [];

function walk(directory) {
  for (const entry of fs.readdirSync(directory, { withFileTypes: true })) {
    const target = path.join(directory, entry.name);
    // The Premium pages are checked by premium-membership.test.cjs.
    if (entry.isDirectory() && entry.name !== "premium") walk(target);
    if (entry.isFile() && entry.name === "index.html") pages.push(target);
  }
}

for (const root of roots) walk(path.join(siteRoot, root));

const routeForFile = (file) => `/${path.relative(siteRoot, path.dirname(file)).replaceAll("\\", "/")}/`;
const knownRoutes = new Set(pages.map(routeForFile));
knownRoutes.add("/");
knownRoutes.add("/guides/");
for (const directory of [
  "android-tv-media-hub", "jellyfin-android-tv", "plex-emby-jellyfin",
  "debrid-usenet-android-tv", "trakt-simkl-sync", "live-tv-epg",
  "ai-subtitles-android-tv", "fire-tv-media-player", "arvio-web"
]) knownRoutes.add(`/${directory}/`);
for (const { route } of standaloneGuideRoutes) knownRoutes.add(route);

for (const file of pages) {
  const html = fs.readFileSync(file, "utf8");
  const route = routeForFile(file);
  const expectedCanonical = `${baseUrl}${route}`;
  const canonical = html.match(/<link rel="canonical" href="([^"]+)"/u)?.[1];
  if (canonical !== expectedCanonical) errors.push(`${route}: expected canonical ${expectedCanonical}, got ${canonical}`);

  for (const hreflang of ["en", "pt-BR", "es", ...extraLocales.map((data) => data.locale.hreflang), "x-default"]) {
    if (!html.includes(`hreflang="${hreflang}"`)) errors.push(`${route}: missing hreflang ${hreflang}`);
  }

  const jsonBlocks = [...html.matchAll(/<script type="application\/ld\+json">([\s\S]*?)<\/script>/gu)];
  if (!jsonBlocks.length) errors.push(`${route}: missing JSON-LD`);
  for (const [, json] of jsonBlocks) {
    try { JSON.parse(json); } catch (error) { errors.push(`${route}: invalid JSON-LD (${error.message})`); }
  }

  const links = [...html.matchAll(/href="(\/[^"]*)"/gu)].map((match) => match[1]);
  for (const href of links) {
    if (href.startsWith("/assets/") || href.endsWith(".css") || href.startsWith("/go/") || href === "/privacy") continue;
    const cleanRoute = href.split(/[?#]/u)[0];
    const localIndex = path.join(siteRoot, cleanRoute.replace(/^\//u, ""), "index.html");
    if (!knownRoutes.has(cleanRoute) && !fs.existsSync(localIndex)) errors.push(`${route}: internal link does not resolve locally (${href})`);
  }
}

const sitemap = fs.readFileSync(path.join(siteRoot, "sitemap.xml"), "utf8");
const sitemapUrls = [...sitemap.matchAll(/<loc>([^<]+)<\/loc>/gu)].map((match) => match[1]);
// 11 pages × (English + pt + es + extra languages) + /premium/ /media-kit/ /sponsors/ + standalone guides + Premium per language.
const expectedSitemapCount = 11 * (3 + extraLocales.length) + 3 + standaloneGuideRoutes.length + localizedPremium.length;
if (sitemapUrls.length !== expectedSitemapCount) errors.push(`sitemap: expected ${expectedSitemapCount} URLs, got ${sitemapUrls.length}`);
if (new Set(sitemapUrls).size !== sitemapUrls.length) errors.push("sitemap: duplicate URLs");
for (const { route } of standaloneGuideRoutes) {
  if (!sitemapUrls.includes(`${baseUrl}${route}`)) errors.push(`sitemap: missing ${baseUrl}${route}`);
  const file = path.join(siteRoot, route.replace(/^\//u, ""), "index.html");
  if (!fs.existsSync(file)) { errors.push(`${route}: standalone guide does not exist`); continue; }
  const html = fs.readFileSync(file, "utf8");
  if (!html.includes('<html lang="en">')) errors.push(`${route}: incorrect document language`);
  if (!html.includes(`<link rel="canonical" href="${baseUrl}${route}">`)) errors.push(`${route}: missing canonical`);
  for (const [, json] of html.matchAll(/<script type="application\/ld\+json">([\s\S]*?)<\/script>/gu)) {
    try { JSON.parse(json); } catch (error) { errors.push(`${route}: invalid JSON-LD (${error.message})`); }
  }
  for (const [, href] of html.matchAll(/(?:href|src)="(\/[^"?#]*)/gu)) {
    if (href === "/privacy") continue;
    const localPath = path.join(siteRoot, href.replace(/^\//u, ""), href.endsWith("/") ? "index.html" : "");
    if (!fs.existsSync(localPath)) errors.push(`${route}: local resource does not resolve (${href})`);
  }
}
for (const route of localizedPremium) if (!sitemapUrls.includes(`${baseUrl}${route}`)) errors.push(`sitemap: missing ${baseUrl}${route}`);
for (const file of pages) {
  const url = `${baseUrl}${routeForFile(file)}`;
  if (!sitemapUrls.includes(url)) errors.push(`sitemap: missing ${url}`);
}

const englishHome = fs.readFileSync(path.join(siteRoot, "index.html"), "utf8");
const englishBody = englishHome.match(/<body>[\s\S]*<\/body>/u)?.[0] ?? "";
const englishBodyTags = [...englishBody.matchAll(/<([a-z][a-z0-9-]*)\b/giu)].map((match) => match[1]).join(",");
const englishCss = englishHome.match(/<style>([\s\S]*?)<\/style>/u)?.[1].replaceAll("/assets/", "assets/");
const stylesheetLinks = (html) => [...html.matchAll(/<link rel="stylesheet" href="([^"]+)"/gu)].map((match) => match[1]);
const englishStylesheets = stylesheetLinks(englishHome);

for (const [directory, language] of [["pt-br", "pt-BR"], ["es", "es"]]) {
  const file = path.join(siteRoot, directory, "index.html");
  const html = fs.readFileSync(file, "utf8");
  const body = html.match(/<body>[\s\S]*<\/body>/u)?.[0] ?? "";
  const bodyTags = [...body.matchAll(/<([a-z][a-z0-9-]*)\b/giu)].map((match) => match[1]).join(",");
  const css = html.match(/<style>([\s\S]*?)<\/style>/u)?.[1].replaceAll("/assets/", "assets/");

  if (!html.includes(`<html lang="${language}">`)) errors.push(`/${directory}/: incorrect document language`);
  if (html.includes('href="/guides/guide.css"')) errors.push(`/${directory}/: simplified guide template was generated instead of the main site`);
  if (bodyTags !== englishBodyTags) errors.push(`/${directory}/: homepage structure differs from the English production homepage`);
  if (css !== englishCss) errors.push(`/${directory}/: homepage styles differ from the English production homepage`);
  if (JSON.stringify(stylesheetLinks(html)) !== JSON.stringify(englishStylesheets)) errors.push(`/${directory}/: homepage stylesheet links differ from English`);
  try {
    const labels = JSON.parse(html.match(/<script type="application\/json" id="home-copy">([\s\S]*?)<\/script>/u)?.[1] ?? "{}");
    for (const key of ["See it in action", "Stop preview", "Enable motion ↗", "Your channels.\nA guide that feels right."]) {
      if (!labels[key] || labels[key] === key) errors.push(`/${directory}/: missing translated interactive label (${key})`);
    }
  } catch (error) { errors.push(`/${directory}/: invalid interactive translations (${error.message})`); }
  if (html.includes('src="assets/') || html.includes('url("assets/')) errors.push(`/${directory}/: contains a locale-relative asset path`);
}

if (errors.length) {
  console.error(errors.join("\n"));
  process.exit(1);
}

console.log(`Validated ${pages.length} localized pages and ${sitemapUrls.length} sitemap URLs.`);

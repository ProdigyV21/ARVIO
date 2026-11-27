#!/usr/bin/env node
// Regenerates every Android launcher asset (adaptive foreground, legacy square/round
// icons for all densities, and the Android TV banner) from the brand sources:
//   netlify-arvio-tv-site/assets/arvio-mark.png  (colour mark, transparent background)
//   web/public/arvio-wordmark.svg                (ARVIO wordmark)
//   app/src/main/res/drawable/ic_launcher_monochrome.xml (glyph paths, used for staging)
//
// Every layer is drawn on one flat background colour so no box or seam can show
// through under any launcher mask (circle, squircle, rounded square, TV banner).
//
// Usage: node scripts/generate-launcher-icons.mjs   (needs the `playwright` package)
import { chromium } from 'playwright';
import { readFileSync, writeFileSync, mkdirSync } from 'node:fs';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';
import { execFileSync } from 'node:child_process';

const root = join(dirname(fileURLToPath(import.meta.url)), '..');
const res = (flavor, p) => join(root, 'app/src', flavor, 'res', p);

const BG = { main: '#02070C', staging: '#000000' };
const DENSITIES = { mdpi: 1, hdpi: 1.5, xhdpi: 2, xxhdpi: 3, xxxhdpi: 4 };

const markPng = readFileSync(join(root, 'netlify-arvio-tv-site/assets/arvio-mark.png')).toString('base64');
const markUri = `data:image/png;base64,${markPng}`;
const MARK_RATIO = 450 / 376; // width / height of arvio-mark.png

const wordmarkSvg = readFileSync(join(root, 'web/public/arvio-wordmark.svg'), 'utf8');
const wordmarkUri = (white) =>
  `data:image/svg+xml;base64,${Buffer.from(
    white ? wordmarkSvg.replace(/#0bfab6/gi, '#ffffff') : wordmarkSvg,
  ).toString('base64')}`;
const WORDMARK_RATIO = 210.9 / 22.25;

// White glyph for the staging flavour, built from the monochrome vector paths (108x108 viewport).
const monoXml = readFileSync(res('main', 'drawable/ic_launcher_monochrome.xml'), 'utf8');
const monoPaths = [...monoXml.matchAll(/android:pathData="([^"]+)"/g)].map((m) => m[1]);
const glyphSvg = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="30 36 48 38">${monoPaths
  .map((d) => `<path d="${d}" fill="#fff" stroke="#fff" stroke-width="1.4" stroke-linejoin="round"/>`)
  .join('')}</svg>`;
const glyphUri = `data:image/svg+xml;base64,${Buffer.from(glyphSvg).toString('base64')}`;
const GLYPH_RATIO = 48 / 38;

const art = (flavor) =>
  flavor === 'main' ? { uri: markUri, ratio: MARK_RATIO } : { uri: glyphUri, ratio: GLYPH_RATIO };

// Mark placement in a 108-unit adaptive canvas. Width 52 keeps the apex and both
// bottom corners inside the 33-unit safe-zone radius, so circle masks never clip it.
function markBox(size, flavor) {
  const { ratio } = art(flavor);
  const u = size / 108;
  const w = 52 * u;
  const h = w / ratio;
  const top = (54 - 27) * u; // apex 27 units above centre, base ~16 below (optical centre)
  return { left: (size - w) / 2, top, w, h };
}

function iconHtml({ size, flavor, bg, shape }) {
  const { uri } = art(flavor);
  // Legacy icons show the 72-unit visible part of the 108-unit adaptive canvas.
  const canvas = shape === 'fg' ? size : (size * 108) / 72;
  const off = (canvas - size) / 2;
  const b = markBox(canvas, flavor);
  const radius = shape === 'round' ? '50%' : shape === 'square' ? `${size * 0.22}px` : '0';
  return `<!doctype html><html><body style="margin:0;background:transparent">
<div style="width:${size}px;height:${size}px;position:relative;overflow:hidden;border-radius:${radius};background:${bg}">
<img src="${uri}" style="position:absolute;left:${b.left - off}px;top:${b.top - off}px;width:${b.w}px;height:${b.h}px"></div></body></html>`;
}

function bannerHtml({ w, h, flavor }) {
  const { uri, ratio } = art(flavor);
  const u = w / 320;
  const markH = 92 * u;
  const wordW = 132 * u;
  return `<!doctype html><html><body style="margin:0">
<div style="width:${w}px;height:${h}px;background:${BG[flavor]};display:flex;flex-direction:column;align-items:center;justify-content:center;gap:${15 * u}px">
<img src="${uri}" style="height:${markH}px;width:${markH * ratio}px;margin-top:${4 * u}px">
<img src="${wordmarkUri(flavor !== 'main')}" style="width:${wordW}px;height:${wordW / WORDMARK_RATIO}px"></div></body></html>`;
}

const browser = await chromium.launch();
const page = await browser.newPage({ deviceScaleFactor: 1 });

async function shot(html, w, h, out, transparent) {
  await page.setViewportSize({ width: Math.ceil(w), height: Math.ceil(h) });
  await page.setContent(html, { waitUntil: 'load' });
  mkdirSync(dirname(out), { recursive: true });
  const buf = await page.screenshot({ omitBackground: transparent, clip: { x: 0, y: 0, width: w, height: h } });
  writeFileSync(out, buf);
}

for (const flavor of ['main', 'staging']) {
  const bg = BG[flavor];
  for (const [d, s] of Object.entries(DENSITIES)) {
    const fg = 108 * s;
    const legacy = 48 * s;
    // Foreground: transparent everywhere except the mark itself.
    await shot(iconHtml({ size: fg, flavor, bg: 'transparent', shape: 'fg' }), fg, fg,
      res(flavor, `mipmap-${d}/ic_launcher_foreground.png`), true);
    await shot(iconHtml({ size: legacy, flavor, bg, shape: 'square' }), legacy, legacy,
      res(flavor, `mipmap-${d}/ic_launcher.png`), true);
    await shot(iconHtml({ size: legacy, flavor, bg, shape: 'round' }), legacy, legacy,
      res(flavor, `mipmap-${d}/ic_launcher_round.png`), true);
  }
  const bannerExt = flavor === 'main' ? 'webp' : 'png';
  for (const [dir, w, h] of [['drawable', 320, 180], ['drawable-xhdpi', 640, 360]]) {
    const out = res(flavor, `${dir}/app_banner.${bannerExt}`);
    const tmp = out.replace(/\.\w+$/, '.tmp.png');
    await shot(bannerHtml({ w, h, flavor }), w, h, bannerExt === 'webp' ? tmp : out, false);
    if (bannerExt === 'webp') {
      execFileSync('convert', [tmp, '-define', 'webp:lossless=true', out]);
      execFileSync('rm', [tmp]);
    }
  }
}
await browser.close();

// Keep the adaptive background colour in sync with the artwork.
for (const flavor of ['main', 'staging']) {
  const p = res(flavor, 'values/colors.xml');
  const xml = readFileSync(p, 'utf8');
  writeFileSync(p, xml.replace(/(<color name="ic_launcher_background">)#[0-9A-Fa-f]{6}(<\/color>)/, `$1${BG[flavor]}$2`));
}
console.log('Launcher assets regenerated.');

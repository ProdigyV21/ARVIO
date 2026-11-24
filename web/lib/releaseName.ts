// Debrid and file-library addons list raw release names
// ("Drishyam-The.Conclusion.2026.Hindi.1080p.WEB-DL"). Cards show the readable
// part ("Drishyam The Conclusion", 2026); the item itself keeps the full name.
const RELEASE_TAG = /(?:^|[\s._-])(?:2160p|1080p|720p|576p|480p|4k|uhd|hdr(?:10)?\+?|dv|web-?dl|web-?rip|webrip|blu-?ray|bdrip|brrip|remux|hdtv|dvdrip|x26[45]|h\.?26[45]|hevc|avc|aac|ddp?\d?(?:\.\d)?|atmos|imax|proper|repack|extended|uncut|multi|dual)(?=$|[\s._-])/i;
const YEAR = /(?:^|[\s._(\[-])((?:19|20)\d{2})(?=$|[\s._)\]-])/;

export function readableReleaseName(raw: string | null | undefined): { title: string; year?: string } | null {
  const name = raw?.trim();
  if (!name) return null;
  const separators = (name.match(/[._]/g) ?? []).length;
  const sceneStyle = !/\s/.test(name) && separators >= 2;
  if (!sceneStyle && !RELEASE_TAG.test(name)) return null;
  const year = name.match(YEAR);
  const tag = name.match(RELEASE_TAG);
  const cut = Math.min(year?.index ?? Infinity, tag?.index ?? Infinity);
  if (!Number.isFinite(cut) || cut <= 0) return null;
  const title = name.slice(0, cut).replace(/[._]+/g, " ").replace(/\s-\s|-(?=\S)/g, " ").replace(/\s{2,}/g, " ").trim();
  if (title.length < 2) return null;
  return year && year.index === cut ? { title, year: year[1] } : { title };
}

// Debrid and file-library addons list raw release names
// ("Drishyam-The.Conclusion.2026.Hindi.1080p.WEB-DL"). Cards show the readable
// part ("Drishyam The Conclusion", 2026); the item itself keeps the full name.
const RELEASE_TAG = /(?:^|[\s._-])(?:2160p|1080p|720p|576p|480p|4k|uhd|hdr(?:10)?\+?|dv|web-?dl|web-?rip|webrip|blu-?ray|bdrip|brrip|remux|hdtv|dvdrip|x26[45]|h\.?26[45]|hevc|avc|aac|ddp?\d?(?:\.\d)?|atmos|imax|proper|repack|extended|uncut|multi|dual)(?=$|[\s._-])/i;
const YEAR = /(?:^|[\s._(\[-])((?:19|20)\d{2})(?=$|[\s._)\]-])/;

// "S01E08", "S2", "s03" — a series release; the show name ends before it.
const EPISODE = /(?:^|[\s._-])S(\d{1,2})(?:E(\d{1,3}))?(?=$|[\s._(\[-])/i;
// Fansub releases lead with the group: "[SubsPlease] Show - 05 (1080p)".
const GROUP_PREFIX = /^\s*\[[^\]]*\]\s*/;

export type ReleaseName = { title: string; year?: string; season?: number; episode?: number };

export function readableReleaseName(raw: string | null | undefined): ReleaseName | null {
  const name = raw?.trim().replace(GROUP_PREFIX, "");
  if (!name) return null;
  const separators = (name.match(/[._]/g) ?? []).length;
  const sceneStyle = !/\s/.test(name) && separators >= 2;
  const episode = name.match(EPISODE);
  if (!sceneStyle && !RELEASE_TAG.test(name) && !episode) return null;
  const year = name.match(YEAR);
  const tag = name.match(RELEASE_TAG);
  const cut = Math.min(year?.index ?? Infinity, tag?.index ?? Infinity, episode?.index ?? Infinity);
  if (!Number.isFinite(cut) || cut <= 0) return null;
  const title = name.slice(0, cut).replace(/[._]+/g, " ").replace(/\s-\s|-(?=\S)/g, " ").replace(/\s{2,}/g, " ").trim();
  if (title.length < 2) return null;
  return {
    title,
    ...(year && year.index === cut ? { year: year[1] } : {}),
    ...(episode ? { season: Number(episode[1]) } : {}),
    ...(episode?.[2] ? { episode: Number(episode[2]) } : {})
  };
}

/**
 * One key per title and episode, so a debrid library that holds the same film
 * four times (different encodes) shows it once.
 */
export function releaseKey(raw: string, mediaType: string) {
  const release = readableReleaseName(raw);
  const title = (release?.title ?? raw).normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase().replace(/[^a-z0-9]+/g, " ").trim();
  return [mediaType, title, release?.year ?? "", release?.season ?? "", release?.episode ?? ""].join("|");
}

/** Drops repeat releases of the same title from an addon file library; other items pass through. */
export function dedupeReleases<T extends { title: string; mediaType: string; addonNativeId?: unknown }>(items: T[]): T[] {
  const seen = new Set<string>();
  const kept = items.filter((item) => {
    if (!item.addonNativeId) return true;
    const key = releaseKey(item.title, item.mediaType);
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
  return kept.length === items.length ? items : kept;
}

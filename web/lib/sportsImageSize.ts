// Sports cards are ~300 CSS px wide, but TheSportsDB serves 1280×720 thumbs and
// 512 px badges. Decoded at full size, a screen of sports rows held hundreds of
// MB of bitmaps and iOS Safari killed the tab ("A problem repeatedly
// occurred"). TheSportsDB publishes resized copies under /medium, /small and
// /tiny; other hosts are left untouched.
const SPORTSDB_HOST = /(^|\.)thesportsdb\.com$/i;
const RESIZABLE_PATH = /\.(jpe?g|png|webp)$/i;

export type SportsImageSize = "medium" | "small" | "tiny";

export function sizedSportsImage(url: string | undefined, size: SportsImageSize): string | undefined {
  if (!url) return url;
  try {
    const parsed = new URL(url);
    if (!SPORTSDB_HOST.test(parsed.hostname) || !RESIZABLE_PATH.test(parsed.pathname) || parsed.search) return url;
    return `${url}/${size}`;
  } catch {
    return url;
  }
}

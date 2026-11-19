import type { MediaItem } from "@/lib/types";

/** Human date for an ISO day ("2024-02-27" → "Feb 27, 2024"). */
export function formatDate(date: string | null | undefined) {
  if (!date) return "";
  const parsed = new Date(`${date.slice(0, 10)}T00:00:00`);
  if (Number.isNaN(parsed.getTime())) return date;
  return parsed.toLocaleDateString(undefined, { year: "numeric", month: "short", day: "numeric" });
}

/** TMDB reviews are raw HTML/markdown from users: show them as plain text. */
export function cleanReviewText(input: string) {
  return input
    .replace(/<br\s*\/?>/gi, "\n")
    .replace(/<\/p>\s*<p[^>]*>/gi, "\n\n")
    .replace(/<[^>]+>/g, "")
    .replace(/\[([^\]]+)\]\((?:https?:\/\/|\/)[^)]*\)/g, "$1")
    .replace(/(\*\*|__)(.+?)\1/g, "$2")
    .replace(/(^|[\s(])[*_]([^*_\n]+)[*_](?=[\s).,!?:;]|$)/g, "$1$2")
    .replace(/^\s{0,3}(?:#{1,6}\s+|>\s?|[-*+]\s+)/gm, "")
    .replace(/`{1,3}([^`]*)`{1,3}/g, "$1")
    .replace(/&nbsp;/g, " ").replace(/&amp;/g, "&").replace(/&quot;/g, "\"").replace(/&#0?39;|&apos;/g, "'").replace(/&lt;/g, "<").replace(/&gt;/g, ">")
    .replace(/\n{3,}/g, "\n\n")
    .trim();
}

export function buildDetailMeta(item: MediaItem, showBudget: boolean) {
  const meta = [
    formatDate(item.releaseDate) || item.year || null,
    item.duration || null,
    item.mediaType === "tv" && item.numberOfSeasons ? `${item.numberOfSeasons} season${item.numberOfSeasons === 1 ? "" : "s"}` : null,
    item.mediaType === "tv" && item.numberOfEpisodes ? `${item.numberOfEpisodes} episodes` : null,
    item.mediaType === "tv" && item.lastAirDate ? `Last aired ${formatDate(item.lastAirDate)}` : null,
    // "Released" says nothing a release date doesn't; series states stay.
    item.status && !/^released$/i.test(item.status) ? item.status : null,
    item.originalLanguage ? item.originalLanguage.toUpperCase() : null
  ];
  if (showBudget && item.mediaType === "movie") {
    meta.push(formatMoney(item.budget, "Budget"));
    meta.push(formatMoney(item.revenue, "Box office"));
  }
  return meta.filter((value): value is string => Boolean(value));
}

export function formatMoney(value: number | null | undefined, label: string) {
  if (!value || value <= 0) return null;
  const compact = new Intl.NumberFormat("en-US", {
    notation: "compact",
    maximumFractionDigits: 1,
    style: "currency",
    currency: "USD"
  }).format(value);
  return `${label} ${compact}`;
}


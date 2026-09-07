import type { MediaItem } from "./types";

export const LIBRARY_SORT_OPTIONS = [
  { value: "added", label: "Recently added" },
  { value: "release-newest", label: "Release date: newest first" },
  { value: "release-oldest", label: "Release date: oldest first" },
  { value: "rating", label: "Highest rated" },
  { value: "title", label: "Title A-Z" }
] as const;

export type LibrarySort = typeof LIBRARY_SORT_OPTIONS[number]["value"];

function releaseKey(item: MediaItem): number | null {
  const date = item.releaseDate?.match(/^(\d{4})-(\d{2})-(\d{2})(?:T.*)?$/);
  if (date) {
    const [year, month, day] = date.slice(1).map(Number);
    const parsed = new Date(`${date[1]}-${date[2]}-${date[3]}T00:00:00Z`);
    if (year > 0 && parsed.getUTCFullYear() === year && parsed.getUTCMonth() + 1 === month && parsed.getUTCDate() === day) {
      return year * 10000 + month * 100 + day;
    }
  }
  const year = Number(item.year?.match(/^\d{4}$/)?.[0] ?? item.releaseDate?.match(/^\d{4}$/)?.[0]);
  return year > 0 ? year * 10000 + 101 : null;
}

export function compareLibraryItems(a: MediaItem, b: MediaItem, sort: LibrarySort): number {
  if (sort === "title") return a.title.localeCompare(b.title);
  if (sort === "rating") return (Number(b.rating) || 0) - (Number(a.rating) || 0);
  if (sort === "release-newest" || sort === "release-oldest") {
    const left = releaseKey(a);
    const right = releaseKey(b);
    // Undated titles stay at the end in either direction, without inventing a date.
    if (left === null && right !== null) return 1;
    if (right === null && left !== null) return -1;
    return (left !== null && right !== null ? (left - right) * (sort === "release-newest" ? -1 : 1) : 0)
      || a.title.localeCompare(b.title) || a.id - b.id;
  }
  return (b.activityAt ?? 0) - (a.activityAt ?? 0);
}

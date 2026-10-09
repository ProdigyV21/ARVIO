import type { MediaItem } from "./types";

export type CalendarSource = "arvio" | "trakt" | "simkl" | "mdblist";
export type ReleaseKind = "episode" | "cinema" | "digital" | "physical" | "tv" | "release";
export const CALENDAR_SOURCE_LABELS: Record<CalendarSource, string> = { arvio: "ARVIO", trakt: "Trakt", simkl: "SIMKL", mdblist: "MDBList" };
export interface CalendarTitle { item: MediaItem; sources: CalendarSource[] }
export interface CalendarRelease extends CalendarTitle {
  id: string;
  date: string;
  kind: ReleaseKind;
  season?: number;
  episode?: number;
  episodeTitle?: string;
  /** Only an authoritative episode timestamp, never a date-only midnight. */
  timestamp?: string;
  artwork?: string;
  region?: string;
}

export function calendarDate(date: Date): string {
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}-${String(date.getDate()).padStart(2, "0")}`;
}

export function parseCalendarDate(value?: string | null): Date | null {
  if (!value || !/^\d{4}-\d{2}-\d{2}$/.test(value)) return null;
  const [year, month, day] = value.split("-").map(Number);
  const date = new Date(year, month - 1, day, 12);
  return calendarDate(date) === value ? date : null;
}

export function calendarMonthDays(month: Date): Date[] {
  const first = new Date(month.getFullYear(), month.getMonth(), 1, 12);
  const offset = (first.getDay() + 6) % 7;
  const count = Math.max(35, Math.ceil((offset + new Date(month.getFullYear(), month.getMonth() + 1, 0).getDate()) / 7) * 7);
  return Array.from({ length: count }, (_, index) => new Date(month.getFullYear(), month.getMonth(), 1 - offset + index, 12));
}

/** Keep the selected day number where possible, clamping at the destination month's end. */
export function shiftCalendarMonth(date: Date, step: number): Date {
  const lastDay = new Date(date.getFullYear(), date.getMonth() + step + 1, 0, 12).getDate();
  return new Date(date.getFullYear(), date.getMonth() + step, Math.min(date.getDate(), lastDay), 12);
}

export function mergeCalendarTitles(groups: Array<{ source: CalendarSource; items: MediaItem[] }>): CalendarTitle[] {
  const titles = new Map<string, CalendarTitle>();
  for (const { source, items } of groups) for (const item of items) {
    if (item.id <= 0 || item.isHomeServer) continue;
    const key = `${item.mediaType}:${item.id}`;
    const current = titles.get(key);
    if (current) {
      if (!current.sources.includes(source)) current.sources.push(source);
      current.item = { ...item, ...current.item, traktId: current.item.traktId ?? item.traktId, image: current.item.image || item.image, backdrop: current.item.backdrop || item.backdrop };
    } else titles.set(key, { item, sources: [source] });
  }
  return [...titles.values()];
}

export function authoritativeEpisodeTime(value?: string | null): { timestamp: string; date: string } | null {
  if (!value || !/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}(?:\.\d+)?(?:Z|[+-]\d{2}:\d{2})$/.test(value)) return null;
  const instant = new Date(value);
  return Number.isFinite(instant.getTime()) ? { timestamp: value, date: calendarDate(instant) } : null;
}

export function calendarTime(release: CalendarRelease, locale?: string): string {
  return release.timestamp ? new Date(release.timestamp).toLocaleTimeString(locale, { hour: "2-digit", minute: "2-digit", hour12: false }) : "Time TBA";
}

export function calendarEpisodeLabel(release: CalendarRelease): string {
  return release.kind === "episode" ? `S${String(release.season).padStart(2, "0")} E${String(release.episode).padStart(2, "0")}` : "";
}

export const CALENDAR_KIND_LABELS: Record<ReleaseKind, string> = { episode: "Episode", cinema: "Cinema release", digital: "Digital release", physical: "Physical release", tv: "TV release", release: "Release" };

export function sortCalendarReleases(releases: CalendarRelease[]): CalendarRelease[] {
  return [...releases].sort((a, b) => a.date.localeCompare(b.date) || (a.timestamp ? Date.parse(a.timestamp) : Infinity) - (b.timestamp ? Date.parse(b.timestamp) : Infinity) || a.item.title.localeCompare(b.item.title) || (a.season ?? 0) - (b.season ?? 0) || (a.episode ?? 0) - (b.episode ?? 0) || a.kind.localeCompare(b.kind));
}

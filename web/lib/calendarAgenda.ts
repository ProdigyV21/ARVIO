import type { CalendarRelease } from "./calendar";

/** Releases of one month grouped by day, in date order (the agenda/list view). */
export function agendaDays(releases: CalendarRelease[], monthPrefix: string) {
  const days = new Map<string, CalendarRelease[]>();
  for (const release of [...releases].sort((a, b) => a.date.localeCompare(b.date) || (a.timestamp ?? "~").localeCompare(b.timestamp ?? "~"))) {
    if (!release.date.startsWith(monthPrefix)) continue;
    days.set(release.date, [...(days.get(release.date) ?? []), release]);
  }
  return [...days.entries()].map(([date, entries]) => ({ date, entries }));
}

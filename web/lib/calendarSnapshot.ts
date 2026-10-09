import { loadStored, saveStored } from "./storage";
import { sortCalendarReleases } from "./calendar";
import type { CalendarResult } from "./calendarLoader";

const STORAGE_KEY = "arvio.web.calendarSnapshots.v1";
const MAX_AGE = 24 * 60 * 60_000;
interface Snapshot { key: string; at: number; value: CalendarResult }

/** Cache partition only: never place the connected providers' credentials in storage keys. */
export function calendarConnectionFingerprint(connections: Array<string | null | undefined>): string {
  let fingerprint = 0xcbf29ce484222325n;
  for (const char of JSON.stringify(connections.map(value => value || null))) {
    fingerprint = BigInt.asUintN(64, (fingerprint ^ BigInt(char.charCodeAt(0))) * 0x100000001b3n);
  }
  return fingerprint.toString(16);
}

function snapshots(): Snapshot[] {
  const stored = loadStored<unknown>(STORAGE_KEY, []);
  return Array.isArray(stored) ? stored.filter(row => row && typeof row.key === "string" && Number.isFinite(row.at)
    && Date.now() - row.at >= 0 && Date.now() - row.at < MAX_AGE && row.value && Array.isArray(row.value.releases)
    && Array.isArray(row.value.failedSources) && Array.isArray(row.value.pendingSources)) : [];
}

/** The caller includes account, profile, month, locale and active sources in key. */
export function readCalendarSnapshot(key: string): CalendarResult | null {
  return snapshots().find(row => row.key === key)?.value ?? null;
}

export function saveCalendarSnapshot(key: string, value: CalendarResult): void {
  if (value.pendingSources.length) return;
  // Persist confirmed removals even if another provider is unavailable. Preserve
  // only the failed source/title portions instead of reviving the old whole view.
  const previous = snapshots().find(row => row.key === key);
  const reconciled = mergeCalendarRefresh(previous?.value ?? null, value);
  const fresh = new Map(value.releases.map(row => [row.id, row]));
  const retainsFallback = reconciled.releases.some(row => !fresh.has(row.id) || row.sources.some(source => !fresh.get(row.id)!.sources.includes(source)));
  const at = retainsFallback && previous ? previous.at : Date.now();
  saveStored(STORAGE_KEY, [{ key, at, value: reconciled }, ...snapshots().filter(row => row.key !== key)].slice(0, 6));
}

/** Keep cached sources visible only until their own authoritative refresh finishes. */
export function mergeCalendarRefresh(cached: CalendarResult | null, fresh: CalendarResult): CalendarResult {
  if (!cached) return fresh;
  const awaiting = new Set([...fresh.pendingSources, ...fresh.failedSources]);
  const failedTitles = new Set(fresh.failedTitleKeys ?? []);
  const releases = new Map(fresh.releases.map(row => [row.id, row]));
  for (const row of cached.releases) {
    const titleKey = `${row.item.mediaType}:${row.item.id}`;
    const retained = row.sources.filter(source => awaiting.has(source)
      || (failedTitles.has(titleKey) && fresh.sourceTitleIds?.[source]?.includes(titleKey)));
    if (!retained.length) continue;
    const updated = releases.get(row.id);
    releases.set(row.id, updated ? { ...updated, sources: [...new Set([...updated.sources, ...retained])] } : { ...row, sources: retained });
  }
  return { ...fresh, releases: sortCalendarReleases([...releases.values()]) };
}

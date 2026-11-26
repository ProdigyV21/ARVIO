import { guideSports, isOnAir, hasSportsChannels, sportsArtworkKey, type SportsGuideEvent } from "./sportsGuide";
import type { SportsEventArtwork } from "./sportsArtwork";
import { sportsProminence } from "./sportsCatalogue";

/*
 * "My sports", day tabs, filters and kick-off reminders. Mirrors the Android
 * app (SportsMySports.kt) so follows synced between them mean the same thing.
 */

export interface SportsFollow { kind: "team" | "league"; name: string; sport?: string }
export interface SportsReminder { eventId: string; title: string; startsAt: number; channelIds: string[]; auto?: boolean; dismissed?: boolean }

const followAffixes = /^(?:(?:fc|cf|afc|sc|club)\s+)+|(?:\s+(?:fc|cf|afc|sc))+$/g;
const leagueCountries = /^(?:english|spanish|italian|german|french|dutch|portuguese|scottish|belgian|turkish)\s+/;
export function sportsFollowKey(name: string, league = false): string {
  const key = sportsArtworkKey(name);
  return (league ? key.replace(leagueCountries, "") : key.replace(followAffixes, "")).trim();
}
export const followKey = (follow: SportsFollow) => `${follow.kind}:${sportsFollowKey(follow.name, follow.kind === "league")}`;
export const SPORTS_FOLLOW_LIMIT = 100;

export function sanitizeFollows(value: unknown): SportsFollow[] {
  if (!Array.isArray(value)) return [];
  const seen = new Set<string>();
  return value.flatMap(item => {
    const follow = item as Partial<SportsFollow> | null;
    if (!follow || typeof follow.name !== "string" || !follow.name.trim() || (follow.kind !== "team" && follow.kind !== "league")) return [];
    const clean: SportsFollow = { kind: follow.kind, name: follow.name, ...(typeof follow.sport === "string" ? { sport: follow.sport } : {}) };
    const key = followKey(clean);
    if (seen.has(key)) return [];
    seen.add(key);
    return [clean];
  }).slice(0, SPORTS_FOLLOW_LIMIT);
}
export function toggleFollow(follows: SportsFollow[], follow: SportsFollow): SportsFollow[] {
  if (!follow.name.trim()) return follows;
  const key = followKey(follow);
  return follows.some(f => followKey(f) === key) ? follows.filter(f => followKey(f) !== key) : [follow, ...follows].slice(0, SPORTS_FOLLOW_LIMIT);
}

const matchupSplit = /\s+(?:vs?\.?|versus|at|[-–—])\s+/i;
export function eventTeams(event: SportsGuideEvent): string[] {
  const home = event.teamArtwork?.homeTeam, away = event.teamArtwork?.awayTeam;
  if (home && away) return [home, away];
  const plain = event.title.split(": ").at(-1)!.trim();
  const parts = plain.split(matchupSplit).map(p => p.trim()).filter(Boolean);
  return parts.length === 2 ? parts : [];
}
export function followsTeam(event: SportsGuideEvent, follows: SportsFollow[]): boolean {
  const teams = new Set(follows.filter(f => f.kind === "team").map(f => sportsFollowKey(f.name)));
  return teams.size > 0 && eventTeams(event).some(team => teams.has(sportsFollowKey(team)));
}
export function followsLeague(event: SportsGuideEvent, follows: SportsFollow[]): boolean {
  const competition = event.competition ?? event.fixture?.league;
  if (!competition) return false;
  const key = sportsFollowKey(competition, true);
  return follows.some(f => f.kind === "league" && sportsFollowKey(f.name, true) === key);
}
export const isFollowed = (event: SportsGuideEvent, follows: SportsFollow[]) => follows.length > 0 && (followsTeam(event, follows) || followsLeague(event, follows));
export const isFinished = (event: SportsGuideEvent) => event.fixture?.status === "finished";

export function followOptions(event: SportsGuideEvent): SportsFollow[] {
  const options: SportsFollow[] = eventTeams(event).map(name => ({ kind: "team", name, sport: event.sportId }));
  const competition = event.competition ?? event.fixture?.league;
  if (competition?.trim()) options.push({ kind: "league", name: competition, sport: event.sportId });
  return options.filter((option, index) => options.findIndex(other => followKey(other) === followKey(option)) === index);
}

export function mySportsEvents(events: SportsGuideEvent[], follows: SportsFollow[], now: number): SportsGuideEvent[] {
  if (!follows.length) return [];
  return events.filter(e => isFollowed(e, follows) && !isFinished(e) && hasSportsChannels(e, now) && (isOnAir(e, now) || e.programme.startUtcMillis > now))
    .sort((a, b) => Number(isOnAir(b, now)) - Number(isOnAir(a, now)) || a.programme.startUtcMillis - b.programme.startUtcMillis || a.id.localeCompare(b.id));
}

// ── Filters ──
export type SportsFilter = "all" | "live" | string;
export function matchesSportsFilter(event: SportsGuideEvent, filter: SportsFilter, now: number): boolean {
  return filter === "all" ? true : filter === "live" ? isOnAir(event, now) : event.sportId === filter;
}
export function sportsFilterOptions(events: SportsGuideEvent[]) {
  const present = new Set(events.map(e => e.sportId));
  return guideSports.filter(sport => present.has(sport.id));
}

// ── Day tabs ──
export const SPORTS_DAY_OFFSETS = Array.from({ length: 15 }, (_, i) => i - 7);
/** Local calendar date as "YYYY-MM-DD". */
export function localDateKey(millis: number): string {
  const d = new Date(millis);
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, "0")}-${String(d.getDate()).padStart(2, "0")}`;
}
export function localDayStart(now: number, offset: number): number {
  const d = new Date(now); d.setHours(0, 0, 0, 0); d.setDate(d.getDate() + offset);
  return d.getTime();
}
/** The UTC days the server is asked for to cover one local day. */
export function utcDaysFor(dayStart: number): string[] {
  const next = new Date(dayStart); next.setDate(next.getDate() + 1);
  return [...new Set([new Date(dayStart).toISOString().slice(0, 10), new Date(next.getTime() - 1).toISOString().slice(0, 10)])];
}

export function fixtureOnlyEvent(item: SportsEventArtwork): SportsGuideEvent | null {
  if (!item.fixture || item.startsAt === undefined) return null;
  const text = `${item.genres.join(" ")} ${item.fixture.league ?? ""}`;
  const sport = guideSports.find(s => s.id !== "other" && s.pattern.test(text))?.id ?? "other";
  return { id: `sportsdb:${item.fixture.id}`, title: item.title, sportId: sport,
    programme: { title: item.title, startUtcMillis: item.startsAt, endUtcMillis: item.startsAt } as SportsGuideEvent["programme"],
    channels: [], artwork: item.background || undefined,
    teamArtwork: item.homeBadge && item.awayBadge ? { homeBadge: item.homeBadge, awayBadge: item.awayBadge, homeTeam: item.homeTeam, awayTeam: item.awayTeam } : undefined,
    competition: item.fixture.league, fixture: item.fixture,
    prominence: sportsProminence(item.fixture.league, new Set(item.fixture.broadcasters.map(b => b.country).filter(Boolean)).size) };
}

/** Every game on one local day: catalogue games (with channels) plus schedule-only fixtures, finished ones included. */
export function sportsEventsForDay(catalogue: SportsGuideEvent[], fixtures: SportsEventArtwork[], dayKey: string): SportsGuideEvent[] {
  const onDay = catalogue.filter(e => localDateKey(e.programme.startUtcMillis) === dayKey);
  const known = new Set(onDay.map(e => e.id));
  const extra: SportsGuideEvent[] = [];
  for (const item of fixtures) {
    if (item.startsAt === undefined || localDateKey(item.startsAt) !== dayKey) continue;
    const event = fixtureOnlyEvent(item);
    if (event && !known.has(event.id)) { known.add(event.id); extra.push(event); }
  }
  return [...onDay, ...extra];
}

export const SPORTS_ROW_MINE = "my-sports";
export function sportsDayRows(events: SportsGuideEvent[], follows: SportsFollow[], now: number) {
  const ordered = [...events].sort((a, b) => a.programme.startUtcMillis - b.programme.startUtcMillis || (b.prominence ?? 0) - (a.prominence ?? 0) || a.id.localeCompare(b.id));
  const rows: { id: string; title: string; events: SportsGuideEvent[] }[] = [];
  const mine = ordered.filter(e => isFollowed(e, follows));
  if (mine.length) rows.push({ id: SPORTS_ROW_MINE, title: "My sports", events: mine });
  const live = ordered.filter(e => isOnAir(e, now) && !e.channelOnly);
  if (live.length) rows.push({ id: "day-live", title: "Live now", events: live });
  for (const sport of guideSports) {
    const items = ordered.filter(e => e.sportId === sport.id && !e.channelOnly);
    if (items.length) rows.push({ id: `day-${sport.id}`, title: sport.title, events: items });
  }
  return rows;
}

// ── Reminders ──
export const SPORTS_REMINDER_LEAD_MS = 60_000;
export const SPORTS_REMINDER_COUNTDOWN_S = 10;
const LATE_MS = 15 * 60_000, AUTO_WINDOW_MS = 2 * 86_400_000, KEEP_MS = 6 * 3_600_000;

export const reminderChannelIds = (event: SportsGuideEvent) => [...new Set([...event.channels, ...(event.possibleChannels ?? [])].map(ch => ch.id))].slice(0, 8);
export const toReminder = (event: SportsGuideEvent, auto = false): SportsReminder =>
  ({ eventId: event.id, title: event.title, startsAt: event.programme.startUtcMillis, channelIds: reminderChannelIds(event), auto });
export const isReminded = (reminders: SportsReminder[], eventId: string) => reminders.some(r => r.eventId === eventId && !r.dismissed);

export function withReminder(reminders: SportsReminder[], event: SportsGuideEvent, on: boolean): SportsReminder[] {
  const existing = reminders.find(r => r.eventId === event.id);
  const rest = reminders.filter(r => r.eventId !== event.id);
  if (on) return [...rest, toReminder(event, existing?.auto === true)];
  return existing?.auto ? [...rest, { ...existing, dismissed: true }] : rest;
}

/** Adds reminders for followed teams (not whole leagues), refreshes channels, drops old ones. Same array when unchanged. */
export function withAutoReminders(reminders: SportsReminder[], events: SportsGuideEvent[], follows: SportsFollow[], now: number): SportsReminder[] {
  const byId = new Map(events.map(e => [e.id, e]));
  let changed = false;
  const kept = reminders.filter(r => { const keep = r.startsAt > now - KEEP_MS; if (!keep) changed = true; return keep; }).map(r => {
    const event = byId.get(r.eventId);
    if (!event) return r;
    const channels = reminderChannelIds(event);
    if (!channels.length || (channels.join("|") === r.channelIds.join("|") && event.programme.startUtcMillis === r.startsAt)) return r;
    changed = true;
    return { ...r, channelIds: channels, startsAt: event.programme.startUtcMillis };
  });
  const known = new Set(kept.map(r => r.eventId));
  const added = events.filter(e => !known.has(e.id) && e.programme.startUtcMillis > now && e.programme.startUtcMillis - now <= AUTO_WINDOW_MS
    && !isFinished(e) && !e.channelOnly && followsTeam(e, follows) && reminderChannelIds(e).length > 0).map(e => toReminder(e, true));
  if (!changed && !added.length) return reminders;
  return [...kept, ...added];
}

export function dueReminder(reminders: SportsReminder[], now: number, handled: ReadonlySet<string>): SportsReminder | undefined {
  return reminders.filter(r => !r.dismissed && !handled.has(r.eventId) && now >= r.startsAt - SPORTS_REMINDER_LEAD_MS && now < r.startsAt + LATE_MS)
    .sort((a, b) => a.startsAt - b.startsAt)[0];
}
export function nextReminderAt(reminders: SportsReminder[], now: number, handled: ReadonlySet<string>): number | undefined {
  const times = reminders.filter(r => !r.dismissed && !handled.has(r.eventId) && r.startsAt + LATE_MS > now).map(r => Math.max(now, r.startsAt - SPORTS_REMINDER_LEAD_MS));
  return times.length ? Math.min(...times) : undefined;
}

export function sanitizeReminders(value: unknown): SportsReminder[] {
  if (!Array.isArray(value)) return [];
  const seen = new Set<string>();
  return value.flatMap(item => {
    const r = item as Partial<SportsReminder> | null;
    if (!r || typeof r.eventId !== "string" || !r.eventId || typeof r.title !== "string" || typeof r.startsAt !== "number" || seen.has(r.eventId)) return [];
    seen.add(r.eventId);
    return [{ eventId: r.eventId, title: r.title, startsAt: r.startsAt, channelIds: Array.isArray(r.channelIds) ? r.channelIds.filter((id): id is string => typeof id === "string").slice(0, 8) : [],
      ...(r.auto ? { auto: true } : {}), ...(r.dismissed ? { dismissed: true } : {}) }];
  });
}

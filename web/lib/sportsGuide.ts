import type { IptvChannel, IptvNowNext, IptvProgram } from "./types";
import type { SportsFixture } from "./sportsArtwork";

export const guideSports = [
  { id: "american-football", title: "American football", asset: "american_football", pattern: /\b(american football|nfl|ncaa football)\b/i },
  { id: "basketball", title: "Basketball", asset: "basketball", pattern: /\b(basketball|nba|wnba|euroleague)\b/i },
  { id: "f1", title: "Formula 1", asset: "motor_sports", pattern: /\b(f1|formula 1|formula one)\b/i },
  { id: "motorsport", title: "Motorsport", asset: "motor_sports", pattern: /\b(motorsport|motor sports|motogp|nascar|indycar|superbike|formula e|rally)\b/i },
  { id: "tennis", title: "Tennis", asset: "tennis", pattern: /\b(tennis|atp|wta|wimbledon)\b/i },
  { id: "mma", title: "MMA", asset: "fight", pattern: /\b(mma|ufc|bellator|pfl)\b/i },
  { id: "boxing", title: "Boxing", asset: "fight", pattern: /\b(boxing|boxen)\b/i },
  { id: "cricket", title: "Cricket", asset: "cricket", pattern: /\b(cricket|t20|ipl)\b/i },
  { id: "baseball", title: "Baseball", asset: "baseball", pattern: /\b(baseball|mlb)\b/i },
  { id: "hockey", title: "Ice hockey", asset: "hockey", pattern: /\b(ice hockey|hockey|nhl)\b/i },
  { id: "rugby", title: "Rugby", asset: "rugby", pattern: /\b(rugby|six nations)\b/i },
  { id: "golf", title: "Golf", asset: "golf", pattern: /\b(golf|pga|lpga|ryder cup|solheim cup)\b/i },
  { id: "snooker", title: "Snooker", asset: "billiards", pattern: /\b(snooker|billiards)\b/i },
  { id: "darts", title: "Darts", asset: "darts", pattern: /\b(darts|pdc)\b/i },
  { id: "australian-football", title: "Australian football", asset: "afl", pattern: /\b(australian football|aussie rules|afl)\b/i },
  { id: "cycling", title: "Cycling", asset: "other", pattern: /\b(cycling|tour de france|vuelta|giro d italia)\b/i },
  { id: "athletics", title: "Athletics", asset: "other", pattern: /\b(athletics|track and field|diamond league)\b/i },
  { id: "volleyball", title: "Volleyball", asset: "other", pattern: /\b(volleyball)\b/i },
  { id: "handball", title: "Handball", asset: "other", pattern: /\b(handball)\b/i },
  { id: "football", title: "Football", asset: "football", pattern: /\b(football|soccer|premier league|champions league|la liga|eredivisie|bundesliga)\b/i },
] as const;
export type GuideSport = typeof guideSports[number];
export interface SportsGuideEvent {
  id: string;
  title: string;
  sportId: GuideSport["id"];
  programme: IptvProgram;
  channels: IptvChannel[];
  artwork?: string;
  schedules?: Record<string, IptvProgram>;
  competition?: string;
  teamArtwork?: { homeBadge: string; awayBadge: string; homeTeam?: string; awayTeam?: string };
  fixture?: SportsFixture;
  possibleChannels?: IptvChannel[];
  prominence?: number;
}
const nonEvent = /\b(highlights?|hoogtepunten|samenvatting|resumen|replay|re-?run|classic|news|magazine|review|preview|cancelled|canceled|postponed|abandoned|sendepause|off air|no signal|best of|teleshopping|infomercial|documentary)\b/i;
export const sportsProgrammeKey = (p: IptvProgram) => `${p.title.trim().toLowerCase().replace(/\s+/g, " ")}|${p.startUtcMillis}|${p.endUtcMillis}`;
const programmeOnAir = (p: IptvProgram, now: number) => p.startUtcMillis <= now && now < p.endUtcMillis;
export function safeSportsImage(value?: string): string | undefined {
  try { return value && value.length <= 2048 && !/_UTC/i.test(value) && ["http:", "https:"].includes(new URL(value).protocol) ? value : undefined; } catch { return undefined; }
}
export const isConfirmedLive = (event: SportsGuideEvent, now: number) => event.fixture?.status === "live" && now >= event.fixture.observedAt && now - event.fixture.observedAt < 300_000;
export const isOnAir = (event: SportsGuideEvent, now: number) => !["finished", "postponed"].includes(event.fixture?.status ?? "") && (isConfirmedLive(event, now) || Object.values(event.schedules ?? { fallback: event.programme }).some(p => programmeOnAir(p, now)));
export const availableEventChannels = (event: SportsGuideEvent, now: number) => event.channels.filter(ch => programmeOnAir(event.schedules?.[ch.id] ?? event.programme, now));
export const hasSportsChannels = (event: SportsGuideEvent, now: number) => (isOnAir(event, now) ? availableEventChannels(event, now) : event.channels).length > 0 || (event.possibleChannels?.length ?? 0) > 0;
export function sportsPresentationRows(events: SportsGuideEvent[], now: number, failedArtwork: ReadonlySet<string>) {
  const available = events.filter(e => hasSportsChannels(e, now));
  const illustrated = (e: SportsGuideEvent) => !failedArtwork.has(e.id) && Boolean(e.artwork || (e.teamArtwork?.homeBadge && e.teamArtwork.awayBadge));
  return [...sportsGuideRows(available.filter(illustrated), now),
    ...sportsGuideRows(available.filter(e => !illustrated(e)), now)
      .filter(row => row.id !== "featured" && row.id !== "upcoming" && row.id !== "more")
      .map(row => ({ ...row, id: `${row.id}-schedule`, title: `${row.title} schedule` }))];
}
export function sportsChannelSummary(event: SportsGuideEvent, now: number): string {
  const matched = new Set((isOnAir(event, now) ? availableEventChannels(event, now) : event.channels).map(ch => ch.id));
  const possible = new Set((event.possibleChannels ?? []).filter(ch => !matched.has(ch.id)).map(ch => ch.id));
  return [matched.size ? `${matched.size} guide ${matched.size === 1 ? "match" : "matches"}` : "",
    possible.size ? `${possible.size} possible` : ""].filter(Boolean).join(" · ") || "No channels";
}
export const sportsArtworkKey = (title: string) => title.normalize("NFD").replace(/\p{M}+/gu, "").toLowerCase()
  .replace(/^(live\s*[:|-]\s*|live\s+)/, "").replace(/^(football|soccer|basketball|baseball|tennis|ice hockey|american football|boxing|mma|cricket)\s*:\s*/, "")
  .replace(/\b(vs\.?|versus|v\.)\s+/g, "vs ").replace(/[^\p{L}\p{N}]+/gu, " ").trim();
export function sportsQualifierKey(text: string): string {
  return [...new Set((text.toLowerCase().match(/\b(women(?:s|'s)?|youth|u\d{2}|under[ -]?\d{2})\b/g) ?? [])
    .map(value => value.replace(/^women.*/, "women").replace("under", "u").replace(/[ -]/g, "")))].sort().join("|");
}
export function sportsEventIdentity(title: string): string {
  const plain = title.replace(/\s*[\[(](?:live|hd|fhd|uhd|4k)[\])]\s*/gi, " ");
  const matchup = plain.split(":").at(-1)!.split(",")[0].trim();
  const separator = /\s+(?:vs?\.?|versus|at|[-–—])\s+/gi;
  const normalized = sportsArtworkKey((separator.test(matchup) ? matchup : plain).replace(separator, " vs "));
  const sides = normalized.split(" vs ");
  return sides.length === 2 && sides.every(s => s.length >= 3) ? sides.sort().join(" vs ") : normalized;
}
const competitions = ["UEFA Champions League", "Premier League", "La Liga", "Eredivisie", "Bundesliga", "Serie A", "Ligue 1", "WNBA", "NBA", "Euroleague", "NFL", "MLB", "NHL", "Wimbledon", "UFC", "Formula 1"]
  .map(name => ({ name, pattern: new RegExp(`\\b${name}\\b`, "i") }));

/** Inputs must already exclude hidden/locked groups. This never requests a stream. */
export function buildSportsGuideEvents(channels: IptvChannel[], guide: Record<string, IptvNowNext>, now: number, end?: number): SportsGuideEvent[] {
  const tomorrowEnd = new Date(now);
  tomorrowEnd.setDate(tomorrowEnd.getDate() + 2);
  tomorrowEnd.setHours(0, 0, 0, 0);
  const until = end ?? tomorrowEnd.getTime();
  const events = new Map<string, SportsGuideEvent[]>();
  for (const channel of channels) {
    const slice = guide[channel.id];
    if (!slice) continue;
    const programmes = [slice.now, slice.next, slice.later, ...slice.upcoming].filter((p): p is IptvProgram => Boolean(p));
    for (const programme of programmes) {
      if (!Number.isFinite(programme.startUtcMillis) || !Number.isFinite(programme.endUtcMillis) ||
          programme.endUtcMillis <= now || programme.startUtcMillis >= until || programme.endUtcMillis <= programme.startUtcMillis ||
          !programme.title.trim() || nonEvent.test(programme.title)) continue;
      const explicit = guideSports.find((s) => s.pattern.test(`${programme.category ?? ""} ${programme.title}`));
      // A sports channel also broadcasts advertising, documentaries and downtime.
      const matchup = /\s+(?:vs?\.?|versus|at|[-–—])\s+/i.test(programme.title);
      const sport = explicit ?? (matchup ? guideSports.find((s) => s.pattern.test(`${channel.group} ${channel.name}`)) : undefined);
      if (!sport) continue;
      const key = `${sport.id}|${sportsEventIdentity(programme.title)}`;
      const group = events.get(key) ?? [];
      const old = group.find(event => {
        const p = event.programme;
        const overlap = Math.min(p.endUtcMillis, programme.endUtcMillis) - Math.max(p.startUtcMillis, programme.startUtcMillis);
        return Math.abs(p.startUtcMillis - programme.startUtcMillis) <= 15 * 60_000 && overlap > 0 &&
          overlap >= Math.min(p.endUtcMillis - p.startUtcMillis, programme.endUtcMillis - programme.startUtcMillis) / 2;
      });
      if (!old) group.push({ id: `${key}|${programme.startUtcMillis}`, title: programme.title, sportId: sport.id, programme, channels: [channel], schedules: { [channel.id]: programme },
        artwork: safeSportsImage(programme.artworkUrl),
        competition: competitions.find(entry => entry.pattern.test(`${programme.title} ${programme.description ?? ""}`))?.name });
      else {
        if (!old.channels.some(ch => ch.id === channel.id)) old.channels.push(channel);
        old.schedules![channel.id] = programme;
        if (!old.programme.artworkUrl && programme.artworkUrl) old.programme = { ...old.programme, artworkUrl: programme.artworkUrl };
      }
      events.set(key, group);
    }
  }
  return [...events.values()].flat().sort((a, b) => Number(isOnAir(b, now)) - Number(isOnAir(a, now)) || a.programme.startUtcMillis - b.programme.startUtcMillis || a.title.localeCompare(b.title));
}

export type SportsDay = "both" | "today" | "tomorrow";
export function sportsDayIncludes(start: number, now: number, day: SportsDay) {
  const today = new Date(now), tomorrow = new Date(now), date = new Date(start).toDateString();
  tomorrow.setDate(tomorrow.getDate() + 1);
  return (day !== "tomorrow" && date === today.toDateString()) ||
    (day !== "today" && date === tomorrow.toDateString());
}

export function sportsGuideRows(events: SportsGuideEvent[], now: number, day: SportsDay = "both") {
  const live = events.filter((event) => isOnAir(event, now)).sort((a, b) => (b.prominence ?? 0) - (a.prominence ?? 0) || a.programme.startUtcMillis - b.programme.startUtcMillis || a.id.localeCompare(b.id));
  const upcoming = events.filter((event) => event.programme.startUtcMillis > now && !isOnAir(event, now) && sportsDayIncludes(event.programme.startUtcMillis, now, day)).sort((a, b) => (b.prominence ?? 0) - (a.prominence ?? 0) || a.programme.startUtcMillis - b.programme.startUtcMillis || a.id.localeCompare(b.id));
  return [
    { id: "featured", title: "Featured live", events: live.slice(0, 8) },
    { id: "upcoming", title: "Upcoming highlights", events: upcoming.slice(0, 8) },
    ...[...new Set(["football", "basketball", "f1", "tennis", "mma", "boxing", "american-football", "cricket", "baseball", "hockey", ...guideSports.map(sport => sport.id)])]
      .map((id) => guideSports.find((sport) => sport.id === id)!)
      .map((sport) => ({ id: sport.id, title: sport.title, events: [...live.filter((event) => event.sportId === sport.id), ...upcoming.filter(event => event.sportId === sport.id).sort((a, b) => a.programme.startUtcMillis - b.programme.startUtcMillis)] })),
  ].filter((row) => row.events.length > 0);
}

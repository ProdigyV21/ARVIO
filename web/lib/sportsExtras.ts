import { sportsChannelKey } from "./sportsCatalogue";
import { sportsFollowKey, type SportsFollow } from "./sportsFollows";
import type { SportsEventArtwork } from "./sportsArtwork";
import type { SportsGuideEvent, GuideSport } from "./sportsGuide";
import type { IptvChannel } from "./types";

/*
 * Sports batch 2: league networks, remembered channels, channel failover, the
 * game page's details and score alerts. Mirrors the Android app
 * (SportsNetworks.kt, SportsScoreAlerts.kt, SportsGameDetails.kt).
 */

// ── League networks ───────────────────────────────────────────────────────

export interface SportsNetwork { id: string; name: string; sportId?: GuideSport["id"]; prefixes: string[] }
export const SPORTS_NETWORKS: SportsNetwork[] = [
  { id: "nba-tv", name: "NBA TV", sportId: "basketball", prefixes: ["nba tv"] },
  { id: "nba-league-pass", name: "NBA League Pass", sportId: "basketball", prefixes: ["nba league pass"] },
  { id: "nfl-network", name: "NFL Network", sportId: "american-football", prefixes: ["nfl network"] },
  { id: "nfl-redzone", name: "NFL RedZone", sportId: "american-football", prefixes: ["nfl redzone", "redzone"] },
  { id: "nhl-network", name: "NHL Network", sportId: "hockey", prefixes: ["nhl network"] },
  { id: "mlb-network", name: "MLB Network", sportId: "baseball", prefixes: ["mlb network"] },
  { id: "premier-league", name: "Sky Sports Premier League", sportId: "football", prefixes: ["sky sports premier league", "premier league tv"] },
  { id: "sky-football", name: "Sky Sports Football", sportId: "football", prefixes: ["sky sports football"] },
  { id: "laliga", name: "LaLiga TV", sportId: "football", prefixes: ["laliga tv", "la liga tv", "movistar laliga", "m laliga", "dazn laliga"] },
  { id: "bundesliga", name: "Sky Sport Bundesliga", sportId: "football", prefixes: ["sky sport bundesliga"] },
  { id: "ziggo-sport", name: "Ziggo Sport", prefixes: ["ziggo sport"] },
  { id: "sky-f1", name: "Sky Sports F1", sportId: "f1", prefixes: ["sky sports f1", "f1 tv"] },
  { id: "tnt-sports", name: "TNT Sports", prefixes: ["tnt sports"] },
  { id: "espn", name: "ESPN", prefixes: ["espn"] },
  { id: "dazn", name: "DAZN", prefixes: ["dazn"] },
  { id: "bein", name: "beIN Sports", prefixes: ["bein sports"] },
  { id: "eurosport", name: "Eurosport", prefixes: ["eurosport"] },
  { id: "viaplay", name: "Viaplay Sports", prefixes: ["viaplay sport", "v sport"] },
  { id: "canal-sport", name: "Canal+ Sport", prefixes: ["canal plus sport", "canal sport"] },
  { id: "fox-sports", name: "Fox Sports", prefixes: ["fox sports", "fs1", "fs2"] },
  { id: "tsn", name: "TSN", prefixes: ["tsn"] },
  { id: "sportsnet", name: "Sportsnet", prefixes: ["sportsnet"] },
  { id: "willow", name: "Willow", sportId: "cricket", prefixes: ["willow"] },
  { id: "golf", name: "Golf Channel", sportId: "golf", prefixes: ["golf channel", "sky sports golf"] },
  { id: "tennis", name: "Tennis Channel", sportId: "tennis", prefixes: ["tennis channel"] },
  { id: "ufc", name: "UFC Fight Pass", sportId: "mma", prefixes: ["ufc fight pass", "ufc tv"] },
];
export const SPORTS_NETWORK_CHANNELS = 6;
export const SPORTS_NETWORK_PREFIX = "network:";
export const SPORTS_ROW_NETWORKS = "league-networks";
export const isNetworkEvent = (event: SportsGuideEvent) => event.id.startsWith(SPORTS_NETWORK_PREFIX);
const countryPrefix = /^(?:uk|gb|us|usa|ca|au|nz|ie|nl|be|de|at|ch|fr|es|it|pt|br|ar|mx|in|za|tr|pl|se|no|dk|fi|ro|ae|sa|en|int) /;

/** The network a channel name belongs to, if any. */
const leadingLabel = /^[^:|\u00b7]{1,24}[:|\u00b7]\s*/;

function networkForKey(channelName: string): SportsNetwork | undefined {
  const name = sportsChannelKey(channelName).replace(countryPrefix, "");
  return SPORTS_NETWORKS.find(network => network.prefixes.some(prefix => name === prefix || name.startsWith(`${prefix} `)));
}

/**
 * Playlists put labels in front of the name, such as "Strong8K · CA EN: NBA TV HD"
 * or "US| ESPN", so each leading label is dropped in turn until the rest names a network.
 */
export function sportsNetworkFor(channelName: string): SportsNetwork | undefined {
  let rest = channelName.trim();
  for (let i = 0; i < 5; i++) {
    const found = networkForKey(rest);
    if (found) return found;
    const next = rest.replace(leadingLabel, "");
    if (next === rest || !next.trim()) return undefined;
    rest = next;
  }
  return undefined;
}

/** Android stores the enum name ("AMERICAN_FOOTBALL"), the web app the id ("american-football"). */
const followSportId = (sport?: string) => sport?.toLowerCase().replace(/_/g, "-");

/**
 * One tile per network the playlist has, as a channel-only Sports event so the
 * Sports page can show it in a row and open its channel list. Networks of a
 * followed sport come first.
 */
export function sportsNetworkEvents(channels: IptvChannel[], follows: SportsFollow[], now: number): SportsGuideEvent[] {
  const byNetwork = new Map<string, IptvChannel[]>();
  for (const channel of channels) {
    const network = sportsNetworkFor(channel.name);
    if (!network) continue;
    const list = byNetwork.get(network.id) ?? [];
    if (list.length < SPORTS_NETWORK_CHANNELS && !list.some(other => other.id === channel.id)) list.push(channel);
    byNetwork.set(network.id, list);
  }
  const start = new Date(now); start.setHours(0, 0, 0, 0);
  const followed = new Set(follows.map(f => followSportId(f.sport)).filter(Boolean));
  const events = SPORTS_NETWORKS.flatMap(network => {
    const list = byNetwork.get(network.id);
    if (!list?.length) return [];
    const programme = { title: network.name, startUtcMillis: start.getTime(), endUtcMillis: start.getTime() + 2 * 86_400_000 };
    return [{ id: `${SPORTS_NETWORK_PREFIX}${network.id}`, title: network.name, sportId: network.sportId ?? "other", programme, channels: list,
      // Playlists often give network channels the wrong logo, so the tile shows the name.
      artwork: undefined, channelOnly: true } as SportsGuideEvent];
  });
  return [...events.filter(e => followed.has(e.sportId)), ...events.filter(e => !followed.has(e.sportId))];
}

// ── Remembered channels ───────────────────────────────────────────────────

/** What a channel choice is remembered under: the network tile, or the competition. */
export function channelMemoryKeys(event: SportsGuideEvent): string[] {
  const keys: string[] = [];
  if (isNetworkEvent(event)) keys.push(event.id);
  const competition = event.competition ?? event.fixture?.league;
  if (competition?.trim()) keys.push(`league:${sportsFollowKey(competition, true)}`);
  return keys;
}
export const SPORTS_CHANNEL_MEMORY_LIMIT = 60;
export function withRemembered(memory: Record<string, string>, keys: string[], channelId: string): Record<string, string> {
  const fresh = Object.fromEntries([...new Set(keys.filter(Boolean))].map(key => [key, channelId]));
  const rest = Object.entries(memory).filter(([key]) => !(key in fresh));
  return Object.fromEntries([...Object.entries(fresh), ...rest].slice(0, SPORTS_CHANNEL_MEMORY_LIMIT));
}
/** The channel last chosen for this competition or network first, then the rest in their usual order. */
export function withRememberedFirst<T extends { id: string }>(channels: T[], memory: Record<string, string>, keys: string[]): T[] {
  const preferred = [...new Set(keys.map(key => memory[key]).filter(Boolean))];
  if (!preferred.length || channels.length < 2) return channels;
  const rank = (id: string) => { const index = preferred.indexOf(id); return index < 0 ? Infinity : index; };
  return [...channels].sort((a, b) => rank(a.id) - rank(b.id));
}

// ── Failover ──────────────────────────────────────────────────────────────

export interface SportsFailover { eventId: string; title: string; channels: IptvChannel[]; tried: string[] }
export const failoverCovers = (failover: SportsFailover | null, channelId?: string) => Boolean(failover && channelId && failover.channels.some(ch => ch.id === channelId));
/** The next channel after the one that failed, or undefined once every channel was tried. */
const QUALITY_TAGS = /\b(?:uhd|fhd|hd|sd|4k|hevc|h265|1080p?|720p?|50fps|60fps)\b/g;
const copyName = (name: string) => name.toLowerCase().replace(/[[\]()|:._-]+/g, " ").replace(QUALITY_TAGS, " ").replace(/\s+/g, " ").trim();

/**
 * Any channel's failover: its other copies (same guide id, or the same name
 * without HD/FHD/SD tags) from every playlist, the failed one first. Mirrors
 * Android's channelCopyFailover. Undefined when there is no other copy.
 */
export function channelCopyFailover(failed: IptvChannel, all: readonly IptvChannel[]): SportsFailover | undefined {
  const tvg = failed.tvgId?.trim().toLowerCase();
  const name = copyName(failed.name);
  if (!tvg && !name) return undefined;
  const copies = all.filter(ch => ch.id !== failed.id && ((tvg && ch.tvgId?.trim().toLowerCase() === tvg) || (name && copyName(ch.name) === name)));
  const channels = [failed, ...copies].slice(0, 8);
  return channels.length > 1 ? { eventId: `channel:${failed.id}`, title: failed.name, channels, tried: [] } : undefined;
}

export function nextFailover(failover: SportsFailover, failedId: string): { channel: IptvChannel; failover: SportsFailover } | undefined {
  const tried = [...new Set([...failover.tried, failedId])];
  const start = Math.max(0, failover.channels.findIndex(ch => ch.id === failedId));
  const ordered = [...failover.channels.slice(start + 1), ...failover.channels.slice(0, start)];
  const channel = ordered.find(ch => !tried.includes(ch.id));
  return channel ? { channel, failover: { ...failover, tried } } : undefined;
}

// ── Game details ──────────────────────────────────────────────────────────

export interface SportsGameDetails {
  status: string;
  periods: { label: string; home: number; away: number }[];
  standingsTitle?: string;
  standings: { rank: number; team: string; played?: number; won?: number; drawn?: number; lost?: number; difference?: number; points?: number; highlight: boolean }[];
  homeForm: string[]; awayForm: string[];
  headToHead: { date: string; homeTeam: string; awayTeam: string; homeScore: number; awayScore: number }[];
}
const num = (value: unknown) => typeof value === "number" && Number.isFinite(value) ? value : undefined;
const str = (value: unknown) => typeof value === "string" && value.trim() ? value.trim().slice(0, 120) : undefined;
const list = (value: unknown, limit: number) => Array.isArray(value) ? value.slice(0, limit).filter(item => item && typeof item === "object") as Record<string, unknown>[] : [];
export function parseSportsGameDetails(payload: unknown): SportsGameDetails | null {
  const data = payload as Record<string, unknown> | null;
  if (!data || data.version !== 1) return null;
  const form = (data.form ?? {}) as Record<string, unknown>;
  const results = (side: unknown) => Array.isArray(side) ? side.slice(0, 5).filter((r): r is string => r === "W" || r === "D" || r === "L") : [];
  return {
    status: str(data.status) ?? "scheduled",
    periods: list(data.periods, 20).flatMap(p => {
      const label = str(p.label), home = num(p.home), away = num(p.away);
      return label && home !== undefined && away !== undefined ? [{ label: label.slice(0, 6), home, away }] : [];
    }),
    standingsTitle: str(data.standingsTitle),
    standings: list(data.standings, 40).flatMap((row, index) => {
      const team = str(row.team);
      return team ? [{ rank: num(row.rank) ?? index + 1, team, played: num(row.played), won: num(row.won), drawn: num(row.drawn), lost: num(row.lost),
        difference: num(row.difference), points: num(row.points), highlight: row.highlight === true }] : [];
    }),
    homeForm: results(form.home), awayForm: results(form.away),
    headToHead: list(data.headToHead, 5).flatMap(m => {
      const date = str(m.date), homeTeam = str(m.homeTeam), awayTeam = str(m.awayTeam), homeScore = num(m.homeScore), awayScore = num(m.awayScore);
      return date && /^\d{4}-\d{2}-\d{2}$/.test(date) && homeTeam && awayTeam && homeScore !== undefined && awayScore !== undefined
        ? [{ date, homeTeam, awayTeam, homeScore, awayScore }] : [];
    }),
  };
}
export const gameDetailsEmpty = (d: SportsGameDetails) => !d.periods.length && !d.standings.length && !d.homeForm.length && !d.awayForm.length && !d.headToHead.length;
/** A short table whole; a long one as the rows around both teams. */
export function standingsAround<T extends { highlight: boolean }>(rows: T[], limit = 8): T[] {
  if (rows.length <= limit) return rows;
  const keep = new Set<number>();
  rows.forEach((row, i) => { if (row.highlight) [i - 1, i, i + 1].filter(j => j >= 0 && j < rows.length).forEach(j => keep.add(j)); });
  if (!keep.size) return rows.slice(0, limit);
  for (let i = 0; keep.size < limit && i < rows.length; i++) keep.add(i);
  return [...keep].sort((a, b) => a - b).slice(0, limit).map(i => rows[i]);
}

// ── Score alerts ──────────────────────────────────────────────────────────

export interface SportsScoreState { home?: number; away?: number; status: string }
export interface SportsScoreAlert { eventId: string; homeTeam: string; awayTeam: string; homeScore: number; awayScore: number; final: boolean }
export const SPORTS_SCORE_POLL_LIVE_MS = 60_000;
export const SPORTS_SCORE_POLL_IDLE_MS = 10 * 60_000;

export function followedScores(items: SportsEventArtwork[], follows: SportsFollow[]): Map<string, { item: SportsEventArtwork; state: SportsScoreState }> {
  const teams = new Set(follows.filter(f => f.kind === "team").map(f => sportsFollowKey(f.name)));
  const output = new Map<string, { item: SportsEventArtwork; state: SportsScoreState }>();
  if (!teams.size) return output;
  for (const item of items) {
    if (!item.fixture || !item.homeTeam || !item.awayTeam) continue;
    if (!teams.has(sportsFollowKey(item.homeTeam)) && !teams.has(sportsFollowKey(item.awayTeam))) continue;
    output.set(item.fixture.id, { item, state: { home: item.fixture.homeScore, away: item.fixture.awayScore, status: item.fixture.status } });
  }
  return output;
}
/**
 * Alerts between two observations: a score went up, or a game ended. The first
 * observation only sets the baseline; basketball only alerts at the end; the
 * game being watched is skipped, as its stream may run behind the score feed.
 */
export function detectScoreAlerts(previous: Map<string, SportsScoreState> | null, current: ReturnType<typeof followedScores>, watchingEventId?: string | null): SportsScoreAlert[] {
  if (!previous) return [];
  const alerts: SportsScoreAlert[] = [];
  for (const [id, { item, state }] of current) {
    const before = previous.get(id);
    if (!before || id === watchingEventId || state.home === undefined || state.away === undefined) continue;
    const ended = state.status === "finished" && before.status !== "finished";
    const basketball = item.genres.some(g => /basketball/i.test(g));
    const scored = before.home !== undefined && before.away !== undefined && state.home + state.away > before.home + before.away && state.status === "live";
    if (!ended && (!scored || basketball)) continue;
    alerts.push({ eventId: id, homeTeam: item.homeTeam ?? "", awayTeam: item.awayTeam ?? "", homeScore: state.home, awayScore: state.away, final: ended });
  }
  return alerts;
}
export function anyActiveGame(current: ReturnType<typeof followedScores>, now: number): boolean {
  for (const { item, state } of current.values()) {
    if (state.status === "live") return true;
    if (item.startsAt !== undefined && state.status !== "finished" && item.startsAt - now < 15 * 60_000 && now - item.startsAt < 4 * 3600_000) return true;
  }
  return false;
}

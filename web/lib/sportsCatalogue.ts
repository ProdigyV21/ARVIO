import { attachSportsArtwork, type SportsEventArtwork } from "./sportsArtwork";
import { guideSports, sportsEventIdentity, sportsArtworkKey, sportsQualifierKey, type SportsGuideEvent } from "./sportsGuide";
import type { IptvChannel } from "./types";

// Broadcast reach and editorial competition priority are NOT measured viewership.
export function sportsProminence(league = "", countries = 0): number {
  const name = league.toLowerCase();
  const major = /^(uefa champions league|english premier league|premier league|spanish la liga|la liga|nba|nfl|formula 1|ufc|fifa world cup)$/;
  const featured = /^(italian serie a|serie a|german bundesliga|bundesliga|french ligue 1|ligue 1|nhl|mlb|wimbledon|us open|atp us open|wta us open|indian premier league)$/;
  return (major.test(name) ? 200 : featured.test(name) ? 100 : 0) + Math.min(50, countries) * 2;
}

export function sportsChannelKey(name: string): string {
  return sportsArtworkKey(name.replace(/\b(uhd|fhd|hd|sd|4k|8k|hevc|h[.]?265|h[.]?264|1080p|720p|2160p|(?:25|30|50|60)fps|raw|backup)\b/gi, ""))
    .replace(/^([a-z]{2,3})\s+nowtv\s+/, "$1 ")
    .replace(/\btnt sport\b/g, "tnt sports").replace(/\bbein\s*sports?\s*(\d*)/g, "bein sports $1")
    .replace(/\b(sports|espn)(\d+)\b/g, "$1 $2").replace(/\s+/g, " ").trim();
}
export function sportsBroadcasterKeys(name: string, country: string): string[] {
  const regions: Record<string, string[]> = { "united kingdom": ["uk", "gb"], "united states": ["us", "usa"], netherlands: ["nl", "nld"], germany: ["de", "ger"], france: ["fr"], spain: ["es"], italy: ["it"], portugal: ["pt"], brazil: ["br"], australia: ["au"], canada: ["ca"], belgium: ["be"], switzerland: ["ch"], austria: ["at"], ireland: ["ie"], denmark: ["dk", "dnk"], sweden: ["se"], norway: ["no"], finland: ["fi"], poland: ["pl"], romania: ["ro"], turkey: ["tr"], india: ["in"], argentina: ["ar"], mexico: ["mx"], "south africa": ["za"], "new zealand": ["nz"], "saudi arabia": ["sa"], "united arab emirates": ["ae", "uae"] };
  const key = sportsChannelKey(name), suffix = ` ${sportsArtworkKey(country)}`;
  const localName = country && key.endsWith(suffix) ? key.slice(0, -suffix.length) : key;
  return [...new Set([key, localName, ...(regions[country.toLowerCase()] ?? []).flatMap(code => [`${code} ${localName}`, `${localName} ${code}`])])];
}
const leagueKey = (name: string) => sportsArtworkKey(name).replace(/^(english premier league|spanish la liga|italian serie a|german bundesliga|french ligue 1)$/, value => value.split(" ").slice(1).join(" "));

const footballAliases = [
  ["manchester united", "man utd"], ["manchester city", "man city"],
  ["paris saint germain", "paris sg", "psg"], ["bayern munich", "bayern munchen"],
  ["internazionale", "inter milan"], ["atletico madrid", "atl madrid"],
];
function participantKeys(name: string | undefined, sport: string): string[] {
  const key = name ? sportsArtworkKey(name) : "";
  if (key.length < 3) return [];
  return sport === "football" ? footballAliases.find(group => group.includes(key)) ?? [key] : [key];
}

export function buildSportsCatalogue(guide: SportsGuideEvent[], artwork: SportsEventArtwork[], channels: IptvChannel[], now: number): SportsGuideEvent[] {
  const fixtures = artwork.filter(item => item.fixture && item.startsAt);
  if (!fixtures.length) return attachSportsArtwork(guide, artwork);
  const byIdentity = new Map<string, SportsGuideEvent[]>();
  for (const event of guide) {
    const key = `${event.sportId}|${sportsEventIdentity(event.title)}`;
    const group = byIdentity.get(key) ?? []; group.push(event); byIdentity.set(key, group);
  }
  const byChannel = new Map<string, IptvChannel[]>();
  for (const channel of channels) {
    const key = sportsChannelKey(channel.name);
    const group = byChannel.get(key) ?? []; group.push(channel); byChannel.set(key, group);
  }
  const used = new Set<string>();
  const guideTitles = new Map(guide.map(event => [event.id, ` ${sportsArtworkKey(event.title)} `]));
  const until = new Date(now); until.setDate(until.getDate() + 2); until.setHours(0, 0, 0, 0);
  const output: SportsGuideEvent[] = [];
  const seen = new Set<string>();
  for (const item of fixtures) {
    const fixture = item.fixture!, start = item.startsAt!;
    const sport = guideSports.find(s => s.pattern.test(item.genres.join(" ")));
    if (!sport || seen.has(fixture.id) || start >= until.getTime() || start < now - 24 * 3600_000) continue;
    seen.add(fixture.id);
    // Match both complete participant names inside decorated EPG titles, not one team or a league alone.
    const home = participantKeys(item.homeTeam, sport.id), away = participantKeys(item.awayTeam, sport.id);
    const candidates = home.length && away.length && !home.some(key => away.includes(key))
      ? guide.filter(event => event.sportId === sport.id && home.some(key => guideTitles.get(event.id)!.includes(` ${key} `)) && away.some(key => guideTitles.get(event.id)!.includes(` ${key} `))) : [];
    const matches = [...new Set([...(byIdentity.get(`${sport.id}|${sportsEventIdentity(item.title)}`) ?? []), ...candidates])].filter(event =>
      Math.abs(event.programme.startUtcMillis - start) <= 2 * 3600_000 &&
      sportsQualifierKey(`${event.title} ${event.competition ?? ""}`) === sportsQualifierKey(`${item.title} ${fixture.league ?? ""}`) &&
      (!fixture.qualifier || `${event.title} ${event.competition ?? ""}`.toLowerCase().includes(fixture.qualifier)) &&
      (!event.competition || !fixture.league || leagueKey(event.competition) === leagueKey(fixture.league)));
    // Ambiguous same-team events cannot claim the same broadcast twice.
    const unique = matches.filter(event => !used.has(event.id));
    unique.forEach(event => used.add(event.id));
    const mapped = [...new Map(unique.flatMap(event => event.channels).map(ch => [ch.id, ch])).values()];
    const schedules = Object.assign({}, ...unique.map(event => event.schedules ?? Object.fromEntries(event.channels.map(ch => [ch.id, event.programme]))));
    const possible = [...new Map(fixture.broadcasters.filter(b => Math.abs(b.startsAt - start) < 2 * 3600_000)
      .flatMap(b => sportsBroadcasterKeys(b.name, b.country).flatMap(key => byChannel.get(key) ?? [])).filter(ch => !mapped.some(m => m.id === ch.id)).map(ch => [ch.id, ch])).values()];
    const event: SportsGuideEvent = {
      id: `sportsdb:${fixture.id}`, title: item.title, sportId: sport.id,
      // Only real channel schedules decide source availability. No invented event duration.
      programme: { title: item.title, startUtcMillis: start, endUtcMillis: start },
      channels: mapped, schedules, possibleChannels: possible, fixture,
      artwork: item.background || unique.find(e => e.artwork)?.artwork,
      teamArtwork: item.homeBadge && item.awayBadge ? { homeBadge: item.homeBadge, awayBadge: item.awayBadge, homeTeam: item.homeTeam, awayTeam: item.awayTeam } : undefined,
      competition: fixture.league,
      prominence: sportsProminence(fixture.league, new Set(fixture.broadcasters.map(b => b.country).filter(Boolean)).size),
    };
    // Finished/cancelled metadata remains a suppression record, not an EPG fallback.
    if (!["finished", "postponed"].includes(fixture.status)) output.push(event);
  }
  return [...output, ...attachSportsArtwork(guide.filter(event => !used.has(event.id)), artwork)];
}

import { jsonRequest, proxiedUrl } from "./http";
import { config } from "./config";
import { guideSports, sportsArtworkKey, sportsEventIdentity, sportsQualifierKey, safeSportsImage, type SportsGuideEvent } from "./sportsGuide";
export { sportsArtworkKey } from "./sportsGuide";
import type { InstalledAddon } from "./types";

export interface SportsFixture { id: string; league?: string; qualifier?: string; venue?: string; round?: string;
  status: string; observedAt: number; homeScore?: number; awayScore?: number;
  broadcasters: { name: string; country: string; startsAt: number }[] }
export interface SportsEventArtwork { title: string; key: string; background: string; genres: string[]; startsAt?: number;
  homeBadge?: string; awayBadge?: string; homeTeam?: string; awayTeam?: string; source?: string; fixture?: SportsFixture }

export function parseSportsMetadata(payload: unknown): SportsEventArtwork[] {
  const data = payload as { version?: number; catalogueEnabled?: boolean; events?: Record<string, unknown>[] } | null;
  if (data?.version !== 1 || !Array.isArray(data.events)) return [];
  return data.events.slice(0, 6000).flatMap(item => {
    if (!item || typeof item.title !== "string" || !item.title.trim() || typeof item.sport !== "string" || !item.sport.trim()
      || typeof item.startsAt !== "number" || !Number.isFinite(item.startsAt) || item.startsAt <= 0) return [];
    const picture = (key: string) => typeof item[key] === "string" ? safeSportsImage(item[key] as string) : undefined;
    const background = picture("background"), homeBadge = picture("homeBadge"), awayBadge = picture("awayBadge");
    const text = (key: string) => typeof item[key] === "string" ? item[key] as string : undefined;
    const fixture: SportsFixture | undefined = data.catalogueEnabled && /^\d+$/.test(text("id") ?? "") ? {
      id: text("id")!, league: text("league"), qualifier: text("qualifier"), venue: text("venue"), round: text("round"),
      status: text("status") ?? "scheduled", observedAt: typeof item.observedAt === "number" ? item.observedAt : 0,
      homeScore: typeof item.homeScore === "number" ? item.homeScore : undefined, awayScore: typeof item.awayScore === "number" ? item.awayScore : undefined,
      broadcasters: Array.isArray(item.broadcasters) ? item.broadcasters.slice(0, 1500).filter(b => b && typeof b.name === "string" && typeof b.country === "string" && Number.isFinite(b.startsAt)) : [],
    } : undefined;
    if (!fixture && !background && !(homeBadge && awayBadge)) return [];
    return [{ title: item.title, key: sportsArtworkKey(item.title), background: background ?? "", genres: [item.sport], startsAt: item.startsAt,
      homeBadge: awayBadge ? homeBadge : undefined, awayBadge: homeBadge ? awayBadge : undefined,
      homeTeam: typeof item.homeTeam === "string" ? item.homeTeam : undefined, awayTeam: typeof item.awayTeam === "string" ? item.awayTeam : undefined, source: "TheSportsDB", fixture }];
  });
}

let metadataCache: { until: number; request: Promise<SportsEventArtwork[]> } | undefined;
let lastMetadata: SportsEventArtwork[] = [];
let lastMetadataAt = 0;
const metadataEndpoint = () => config.sportsMetadataUrl || (config.netlifyBackendUrl ? `${config.netlifyBackendUrl.replace(/\/$/, "")}/sports-metadata` : "");
export function cachedSportsMetadata(): SportsEventArtwork[] {
  if (lastMetadata.length && Date.now() - lastMetadataAt < 86_400_000) return lastMetadata;
  try {
    const stored = JSON.parse(localStorage.getItem("arvio:sports-fixtures:v1") ?? "null");
    if (stored?.endpoint === metadataEndpoint() && Date.now() >= stored.at && Date.now() - stored.at < 86_400_000) {
      lastMetadata = parseSportsMetadata(stored.payload); lastMetadataAt = stored.at;
      return lastMetadata;
    }
  } catch { /* Storage can be disabled or full; online metadata still works. */ }
  return [];
}
export function loadSportsMetadata(): Promise<SportsEventArtwork[]> {
  const endpoint = metadataEndpoint();
  if (!endpoint) return Promise.resolve([]);
  if (metadataCache && metadataCache.until > Date.now()) return metadataCache.request;
  const entry = { until: Date.now() + 120_000, request: Promise.resolve([] as SportsEventArtwork[]) };
  entry.request = jsonRequest<unknown>(endpoint, { signal: AbortSignal.timeout(8_000) }).then(payload => {
    lastMetadata = parseSportsMetadata(payload); lastMetadataAt = Date.now();
    try { localStorage.setItem("arvio:sports-fixtures:v1", JSON.stringify({ endpoint, at: lastMetadataAt, payload })); } catch { /* Optional cache. */ }
    if (!lastMetadata.length) entry.until = Date.now() + 60_000;
    return lastMetadata;
  }).catch(() => { entry.until = Date.now() + 60_000; return Date.now() - lastMetadataAt < 86_400_000 ? lastMetadata : []; });
  metadataCache = entry;
  return entry.request;
}

export function toSportsEventArtwork(meta: Record<string, unknown>): SportsEventArtwork | null {
  if (!meta || typeof meta !== "object") return null;
  if (typeof meta.name !== "string" || !meta.name.trim() || String(meta.id).startsWith("leaf:")) return null;
  // Event backgrounds are untimed; posters can have a foreign timezone baked into them.
  if (typeof meta.background !== "string" || /_UTC/i.test(meta.background)) return null;
  try { if (!["https:", "http:"].includes(new URL(meta.background).protocol)) return null; } catch { return null; }
  return { title: meta.name, key: sportsArtworkKey(meta.name), background: meta.background,
    startsAt: typeof meta.released === "string" && Number.isFinite(Date.parse(meta.released)) ? Date.parse(meta.released) : undefined,
    genres: Array.isArray(meta.genres) ? meta.genres.filter((g): g is string => typeof g === "string") : [] };
}

export function attachSportsArtwork(events: SportsGuideEvent[], artwork: SportsEventArtwork[]): SportsGuideEvent[] {
  const byTitle = new Map<string, SportsEventArtwork[]>();
  for (const item of artwork) { const key = sportsEventIdentity(item.title); byTitle.set(key, [...(byTitle.get(key) ?? []), item]); }
  return events.map(event => {
    const matches = byTitle.get(sportsEventIdentity(event.title))?.filter(item => {
    const sport = guideSports.find(s => s.pattern.test(item.genres.join(" ")));
    return (sport?.id === event.sportId || (!sport && item.source !== "TheSportsDB"))
      && sportsQualifierKey(`${event.title} ${event.competition ?? ""}`) === sportsQualifierKey(`${item.title} ${item.fixture?.league ?? ""}`)
      && (item.startsAt === undefined || Math.abs(item.startsAt - event.programme.startUtcMillis) <= (item.source === "TheSportsDB" ? 2 : 6) * 60 * 60_000);
    }) ?? [];
    const match = matches.find(item => item.homeBadge && item.awayBadge);
    return { ...event, artwork: safeSportsImage(event.programme.artworkUrl) ?? matches.map(item => safeSportsImage(item.background)).find(Boolean),
      teamArtwork: match ? { homeBadge: match.homeBadge!, awayBadge: match.awayBadge!, homeTeam: match.homeTeam, awayTeam: match.awayTeam } : undefined };
  });
}

const cache = new Map<string, { until: number; request: Promise<SportsEventArtwork[]> }>();
const sportsCatalog = /sport|football|soccer|basketball|tennis|motorsport|formula|racing|rugby|hockey|baseball|boxing|ufc|mma|cricket|golf/i;
export function loadSportsGuideArtwork(addons: InstalledAddon[]): Promise<SportsEventArtwork[]> {
  const requests = addons.filter(addon => addon.enabled !== false && addon.resources.some(r => /^(stream|streams)$/i.test(typeof r === "string" ? r : r.name))
    && addon.catalogs.some(c => sportsCatalog.test(`${c.type} ${c.id} ${c.name}`))).slice(0, 2)
    .flatMap(addon => addon.catalogs.filter(c => sportsCatalog.test(`${c.type} ${c.id} ${c.name}`))
      .map(catalog => { const text = `${catalog.id} ${catalog.name}`.toLowerCase(); return { catalog, rank: text.includes("today") ? 0 : text.includes("live") ? 1 : text.includes("all") ? 2 : 3 }; })
      .sort((a, b) => a.rank - b.rank).slice(0, 3).map(({ catalog }) =>
        `${addon.manifestUrl.replace(/\/manifest\.json$/, "").replace(/\/+$/, "")}/catalog/${encodeURIComponent(catalog.type)}/${encodeURIComponent(catalog.id)}.json`));
  const key = JSON.stringify(requests);
  const stored = cache.get(key);
  if (stored && stored.until > Date.now()) return stored.request;
  const entry = { until: Date.now() + 10 * 60_000, request: Promise.resolve([] as SportsEventArtwork[]) };
  entry.request = Promise.all(requests.map(async url => {
    const controller = new AbortController();
    const timer = setTimeout(() => controller.abort(), 5_000);
    try {
      const payload = await jsonRequest<{ metas?: Record<string, unknown>[]; items?: Record<string, unknown>[] }>(proxiedUrl(url), { signal: controller.signal });
      const metas = payload.metas ?? payload.items;
      return Array.isArray(metas) ? metas.slice(0, 500).map(toSportsEventArtwork).filter((item): item is SportsEventArtwork => Boolean(item)) : [];
    } catch { return []; } finally { clearTimeout(timer); }
  })).then(results => {
    const items = [...new Map(results.flat().map(item => [`${item.key}|${item.background}`, item])).values()].slice(0, 1_000);
    if (!items.length) entry.until = Date.now() + 60_000;
    return items;
  });
  if (cache.size >= 4) cache.delete(cache.keys().next().value!);
  cache.set(key, entry);
  return entry.request;
}

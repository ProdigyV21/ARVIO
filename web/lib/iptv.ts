import { proxiedUrl, textRequest } from "./http";
import { loadStored, saveStored } from "./storage";
import type { IptvChannel, IptvNowNext, IptvPlaylistEntry, IptvProgram, IptvSnapshot } from "./types";

const IPTV_KEY = "arvio.web.iptv.playlists";
const IPTV_TEXT_CACHE = "arvio.web.iptv.text.v1";
const IPTV_TEXT_CACHE_META = "arvio.web.iptv.textMeta.v1";
const PLAYLIST_TTL_MS = 6 * 60 * 60 * 1000;
const EPG_TTL_MS = 3 * 60 * 60 * 1000;
const LARGE_IPTV_LIST_CHANNEL_COUNT = 10_000;
const DEFAULT_IPTV_USER_AGENT = "VLC/3.0.20 LibVLC/3.0.20";

type ProviderBudget = { starts: number[]; active: number; nextStart: number; blockedUntil: number };
const providerBudgets = new Map<string, ProviderBudget>();
const apiInFlight = new Map<string, Promise<unknown>>();
const guideApiCache = new Map<string, { data: unknown; expires: number }>();

// Shared by every visible row and fallback. Media segments do not use this path.
async function providerTextRequest(providerUrl: string, requestUrl: string): Promise<string> {
  const url = new URL(providerUrl);
  const key = url.hostname + (url.port && !["80", "443"].includes(url.port) ? `:${url.port}` : "");
  let budget = providerBudgets.get(key);
  if (!budget) {
    budget = { starts: [], active: 0, nextStart: 0, blockedUntil: 0 };
    providerBudgets.set(key, budget);
  }
  const queuedAt = Date.now();
  while (true) {
    const now = Date.now();
    budget.starts = budget.starts.filter((at) => now - at < 60_000);
    if (now < budget.blockedUntil || budget.starts.length >= 30 || now - queuedAt >= 5_000) {
      throw new Error("IPTV provider requests are temporarily paused. Please wait before refreshing.");
    }
    if (budget.active < 2 && now >= budget.nextStart) break;
    await new Promise((resolve) => setTimeout(resolve, budget!.active >= 2 ? 50
      : Math.max(1, Math.min(100, budget!.nextStart - now))));
  }
  budget.starts.push(Date.now());
  budget.nextStart = Date.now() + 500;
  budget.active++;
  try { return await textRequest(requestUrl, { cache: "no-store" }); }
  catch (error) {
    const status = Number((error as { status?: number })?.status);
    const cooldown = [401, 403, 451].includes(status) ? 900_000
      : [429, 503, 513].includes(status) ? 300_000 : status >= 500 && status <= 599 ? 60_000 : 0;
    if (cooldown) {
      const header = (error as { retryAfter?: string | null })?.retryAfter;
      const requested = header ? (/^\d+$/.test(header) ? Number(header) * 1000 : Date.parse(header) - Date.now()) : 0;
      budget.blockedUntil = Math.max(budget.blockedUntil, Date.now() + Math.max(cooldown,
        Number.isFinite(requested) ? Math.min(86_400_000, requested) : 0));
    }
    throw error;
  } finally { budget.active--; }
}

type IptvLoadOptions = {
  userAgent?: string;
};

type TextValidator = (text: string) => boolean;

export type XtreamInfo = {
  baseUrl: string;
  username: string;
  password: string;
};

/** Xtream credentials from a playlist's get.php/player_api URL, or null if it isn't an Xtream playlist. */
export function xtreamInfoFromUrl(url: string): XtreamInfo | null {
  return xtreamInfoFromPlaylist(normalizeIptvInput(url));
}

export { buildXtreamPlayerApiUrl, playlistProxyHeaders, fetchXtreamJson };

type XtreamCategory = {
  category_id?: string | number;
  category_name?: string;
};

type XtreamStream = {
  name?: string;
  stream_id?: string | number;
  stream_icon?: string;
  epg_channel_id?: string;
  category_id?: string | number;
  num?: string | number;
  tv_archive?: number | string;
  tv_archive_duration?: number | string;
};

export function loadPlaylists() {
  return loadStored<IptvPlaylistEntry[]>(IPTV_KEY, []);
}

export function savePlaylists(playlists: IptvPlaylistEntry[]) {
  saveStored(IPTV_KEY, playlists);
}

export async function loadIptvChannels(playlists: IptvPlaylistEntry[]) {
  return (await loadIptvSnapshot(playlists)).channels;
}

export async function loadIptvSnapshot(
  playlists: IptvPlaylistEntry[],
  favoriteChannels: string[] = [],
  favoriteGroups: string[] = [],
  hiddenGroups: string[] = [],
  groupOrder: string[] = [],
  options: IptvLoadOptions = {}
): Promise<IptvSnapshot> {
  const normalizedPlaylists = normalizeIptvPlaylists(playlists);
  const enabled = normalizedPlaylists.filter((playlist) => playlist.enabled && playlist.m3uUrl.trim());
  const playlistWarnings: string[] = [];
  const channelSets = await Promise.all(
    enabled.map(async (playlist) => {
      try {
        // Xtream playlists load API-first: the JSON API is faster than a huge
        // M3U text, provides category names + EPG channel ids, and yields HLS
        // (.m3u8) stream URLs the browser can actually play.
        const isXtream = Boolean(xtreamInfoFromPlaylist(playlist.m3uUrl));
        if (isXtream) {
          const xtreamChannels = await fetchXtreamChannels(playlist, options).catch(() => []);
          if (xtreamChannels.length) return xtreamChannels;
        }
        try {
          const text = await fetchPlaylistText(playlist.m3uUrl, options);
          const channels = parseM3u(text, playlist.id);
          if (channels.length) return channels;
        } catch (m3uError) {
          const xtreamChannels = isXtream ? [] : await fetchXtreamChannels(playlist, options).catch(() => []);
          if (xtreamChannels.length) return xtreamChannels;
          throw m3uError;
        }

        const xtreamChannels = isXtream ? [] : await fetchXtreamChannels(playlist, options).catch(() => []);
        if (xtreamChannels.length) return xtreamChannels;
        throw new Error("Playlist response did not contain any channels.");
      } catch (error) {
        playlistWarnings.push(`${playlist.name || playlist.m3uUrl}: ${error instanceof Error ? error.message : "Could not load playlist"}`);
        return [] as IptvChannel[];
      }
    })
  );
  const allChannels = channelSets.flat();
  const channels = accessibleChannels(allChannels, hiddenGroups);
  // Xtream channels get now/next on demand from the panel's JSON EPG (fast,
  // per-channel); only non-Xtream channels need the upfront XMLTV pass.
  const xtreamPlaylistIds = new Set(enabled.filter((playlist) => xtreamInfoFromPlaylist(playlist.m3uUrl)).map((playlist) => playlist.id));
  const xmltvChannels = channels.filter((channel) => !xtreamPlaylistIds.has(channel.id.split(":")[0] ?? ""));
  const skipInitialEpg = xmltvChannels.length > LARGE_IPTV_LIST_CHANNEL_COUNT;
  // Channels must not wait for a potentially huge XMLTV response. The visible
  // window requests its guide independently after the snapshot is painted.
  const nowNext = {};
  const epgWarning = skipInitialEpg
    ? `Guide loads on demand for this ${channels.length.toLocaleString()} channel playlist, so Live TV opens fast without parsing the full EPG upfront.`
    : undefined;
  const hidden = new Set(hiddenGroups);
  const grouped = channels.reduce<Record<string, IptvChannel[]>>((acc, channel) => {
    const group = channel.group || "Uncategorized";
    if (hidden.has(group) || hidden.has(groupKey(channel))) return acc;
    (acc[group] ??= []).push(channel);
    return acc;
  }, {});
  const orderedGroups = orderGroups(grouped, groupOrder, channels);
  return {
    channels,
    allChannels,
    grouped: Object.fromEntries(orderedGroups.map((group) => [group, grouped[group]])),
    nowNext,
    favoriteChannels,
    favoriteGroups,
    hiddenGroups,
    groupOrder,
    playlistWarnings,
    epgWarning,
    loadedAt: Date.now()
  };
}

export async function loadIptvGuideForChannels(playlists: IptvPlaylistEntry[], channels: IptvChannel[]) {
  if (!channels.length) return {} as Record<string, IptvNowNext>;
  const normalizedPlaylists = normalizeIptvPlaylists(playlists);
  const enabled = normalizedPlaylists.filter((playlist) => playlist.enabled && playlist.m3uUrl.trim());
  if (!enabled.length) return {} as Record<string, IptvNowNext>;

  // Xtream channels get their guide from the panel's own per-channel EPG API —
  // instant JSON now/next without downloading a multi-megabyte XMLTV file.
  // Everything else falls back to the XMLTV path.
  const byPlaylist = new Map(enabled.map((playlist) => [playlist.id, playlist]));
  const xtreamChannels: Array<{ info: XtreamInfo; channel: IptvChannel; streamId: string }> = [];
  const xmltvChannels: IptvChannel[] = [];
  for (const channel of channels) {
    const playlist = byPlaylist.get(channel.id.split(":")[0] ?? "");
    const info = playlist ? xtreamInfoFromPlaylist(playlist.m3uUrl) : null;
    const streamId = info ? xtreamStreamIdFromUrl(channel.streamUrl) : null;
    if (info && streamId) xtreamChannels.push({ info, channel, streamId });
    else xmltvChannels.push(channel);
  }

  const [xtreamGuide, xmltvGuide] = await Promise.all([
    loadXtreamGuide(xtreamChannels),
    xmltvChannels.length ? loadNowNext(enabled, xmltvChannels).catch(() => ({} as Record<string, IptvNowNext>)) : Promise.resolve({} as Record<string, IptvNowNext>)
  ]);
  return { ...xmltvGuide, ...xtreamGuide };
}

function xtreamStreamIdFromUrl(url: string) {
  return url.match(/\/(\d+)\.(?:m3u8|ts)(?:[?#]|$)/i)?.[1] ?? url.match(/\/live\/[^/]+\/[^/]+\/(\d+)(?:[?#]|$)/i)?.[1] ?? null;
}

type XtreamEpgListing = {
  title?: string;
  description?: string;
  start_timestamp?: string | number;
  stop_timestamp?: string | number;
};

function decodeXtreamText(value?: string) {
  if (!value) return "";
  try {
    // Xtream EPG titles are base64-encoded UTF-8.
    return decodeURIComponent(escape(atob(value)));
  } catch {
    return value;
  }
}

async function loadXtreamGuide(entries: Array<{ info: XtreamInfo; channel: IptvChannel; streamId: string }>) {
  const guide: Record<string, IptvNowNext> = {};
  if (!entries.length) return guide;
  const results = await mapWithConcurrency(entries, 6, async (entry) => {
    const target = new URL(`${entry.info.baseUrl.replace(/\/+$/, "")}/player_api.php`);
    target.searchParams.set("username", entry.info.username);
    target.searchParams.set("password", entry.info.password);
    target.searchParams.set("action", "get_short_epg");
    target.searchParams.set("stream_id", entry.streamId);
    target.searchParams.set("limit", "8");
    const data = await fetchXtreamJson<{ epg_listings?: XtreamEpgListing[] }>(target.toString(), playlistProxyHeaders()).catch(() => null);
    const programs: IptvProgram[] = (data?.epg_listings ?? [])
      .map((listing) => ({
        title: decodeXtreamText(listing.title) || "Untitled",
        description: decodeXtreamText(listing.description),
        startUtcMillis: Number(listing.start_timestamp) * 1000,
        endUtcMillis: Number(listing.stop_timestamp) * 1000
      }))
      .filter((program) => Number.isFinite(program.startUtcMillis) && program.startUtcMillis > 0 && program.endUtcMillis > program.startUtcMillis);
    return { channelId: entry.channel.id, programs };
  });
  const now = Date.now();
  for (const result of results) {
    if (!result || !result.programs.length) continue;
    const sorted = result.programs.sort((a, b) => a.startUtcMillis - b.startUtcMillis);
    const live = sorted.find((program) => now >= program.startUtcMillis && now < program.endUtcMillis);
    const future = sorted.filter((program) => program.startUtcMillis > now);
    guide[result.channelId] = {
      now: live,
      next: future[0],
      later: future[1],
      upcoming: future.slice(0, 8),
      recent: []
    };
  }
  return guide;
}

export type CatchupProgram = IptvProgram & { hasArchive: boolean };

function xtreamInfoForChannel(playlists: IptvPlaylistEntry[], channel: IptvChannel) {
  const playlistId = channel.id.split(":")[0] ?? "";
  const playlist = normalizeIptvPlaylists(playlists).find((entry) => entry.id === playlistId);
  return playlist ? xtreamInfoFromPlaylist(playlist.m3uUrl) : null;
}

/** Past programmes available for catch-up on an Xtream channel (tv_archive). */
export async function loadXtreamCatchup(playlists: IptvPlaylistEntry[], channel: IptvChannel): Promise<CatchupProgram[]> {
  const info = xtreamInfoForChannel(playlists, channel);
  const streamId = info ? xtreamStreamIdFromUrl(channel.streamUrl) : null;
  if (!info || !streamId) return [];
  const target = new URL(`${info.baseUrl.replace(/\/+$/, "")}/player_api.php`);
  target.searchParams.set("username", info.username);
  target.searchParams.set("password", info.password);
  target.searchParams.set("action", "get_simple_data_table");
  target.searchParams.set("stream_id", streamId);
  const data = await fetchXtreamJson<{ epg_listings?: Array<XtreamEpgListing & { has_archive?: number | string }> }>(
    target.toString(),
    playlistProxyHeaders()
  ).catch(() => null);
  const now = Date.now();
  const oldest = now - (channel.catchupDays || 1) * 24 * 60 * 60 * 1000;
  return (data?.epg_listings ?? [])
    .map((listing) => ({
      title: decodeXtreamText(listing.title) || "Untitled",
      description: decodeXtreamText(listing.description),
      startUtcMillis: Number(listing.start_timestamp) * 1000,
      endUtcMillis: Number(listing.stop_timestamp) * 1000,
      hasArchive: Number(listing.has_archive) === 1
    }))
    .filter((program) =>
      program.hasArchive &&
      Number.isFinite(program.startUtcMillis) &&
      program.endUtcMillis > program.startUtcMillis &&
      program.endUtcMillis <= now &&
      program.startUtcMillis >= oldest
    )
    .sort((a, b) => b.startUtcMillis - a.startUtcMillis)
    .slice(0, 40);
}

/**
 * Xtream timeshift URL for a finished programme:
 * {base}/timeshift/{user}/{pass}/{durationMinutes}/{YYYY-MM-DD:HH-MM}/{id}.m3u8
 * The start stamp is in the panel's local timezone; ARVIO formats it in the
 * viewer's timezone, which matches panels hosted for their local market.
 */
export function buildXtreamCatchupUrl(playlists: IptvPlaylistEntry[], channel: IptvChannel, program: IptvProgram): string | null {
  const info = xtreamInfoForChannel(playlists, channel);
  const streamId = info ? xtreamStreamIdFromUrl(channel.streamUrl) : null;
  if (!info || !streamId) return null;
  const start = new Date(program.startUtcMillis);
  const pad = (value: number) => String(value).padStart(2, "0");
  const stamp = `${start.getFullYear()}-${pad(start.getMonth() + 1)}-${pad(start.getDate())}:${pad(start.getHours())}-${pad(start.getMinutes())}`;
  const durationMinutes = Math.max(1, Math.round((program.endUtcMillis - program.startUtcMillis) / 60000));
  const base = info.baseUrl.replace(/\/+$/, "");
  return `${base}/timeshift/${encodeURIComponent(info.username)}/${encodeURIComponent(info.password)}/${durationMinutes}/${stamp}/${streamId}.m3u8`;
}

async function mapWithConcurrency<T, R>(items: T[], limit: number, task: (item: T) => Promise<R>): Promise<Array<R | null>> {
  const results: Array<R | null> = new Array(items.length).fill(null);
  let cursor = 0;
  const workers = Array.from({ length: Math.min(limit, items.length) }, async () => {
    while (cursor < items.length) {
      const index = cursor;
      cursor += 1;
      results[index] = await task(items[index]).catch(() => null);
    }
  });
  await Promise.all(workers);
  return results;
}

function playlistTextUrl(url: string, headers?: Record<string, string>) {
  const target = new URL(proxiedUrl(url, headers));
  target.searchParams.set("rewrite", "0");
  return target.toString();
}

async function fetchPlaylistText(url: string, options: IptvLoadOptions = {}) {
  return cachedRemoteText(
    `playlist:${url}`,
    PLAYLIST_TTL_MS,
    async () => {
      const headers = playlistProxyHeaders(options.userAgent);
      const candidates: Array<{ label: string; load: () => Promise<string> }> = [
        { label: "direct", load: () => providerTextRequest(url, url) },
        { label: "proxy", load: () => providerTextRequest(url, playlistTextUrl(url)) },
        { label: "proxy with media headers", load: () => providerTextRequest(url, playlistTextUrl(url, headers)) }
      ];
      const errors: string[] = [];

      for (const candidate of candidates) {
        try {
          const text = await candidate.load();
          assertPlaylistText(text);
          return text;
        } catch (error) {
          errors.push(`${candidate.label}: ${error instanceof Error ? error.message : "failed"}`);
        }
      }

      throw new Error(errors.join("; "));
    },
    isPlaylistText
  );
}

export function normalizeIptvPlaylists(playlists: IptvPlaylistEntry[]) {
  return playlists
    .map((playlist, index) => normalizeIptvPlaylist(playlist, index))
    .filter((playlist): playlist is IptvPlaylistEntry => Boolean(playlist));
}

export function accessibleChannels(channels: IptvChannel[], hiddenGroups: string[]) {
  const hidden = new Set(hiddenGroups);
  return channels.filter((channel) => !hidden.has(channel.group || "Uncategorized") && !hidden.has(groupKey(channel)));
}

export function normalizeIptvPlaylist(playlist: IptvPlaylistEntry, index = 0): IptvPlaylistEntry | null {
  const m3uUrl = normalizeIptvInput(playlist.m3uUrl);
  if (!m3uUrl) return null;
  const epgUrls = normalizeEpgInputs([playlist.epgUrl, ...(playlist.epgUrls ?? [])].filter(Boolean).join("\n"));
  return {
    id: playlist.id?.trim() || `list_${index + 1}`,
    name: playlist.name?.trim() || `List ${index + 1}`,
    m3uUrl,
    epgUrl: epgUrls[0] ?? "",
    epgUrls,
    enabled: playlist.enabled !== false
  };
}

export function normalizeIptvInput(raw: string): string {
  const trimmed = decodeLegacyBase64Url(raw.trim()) ?? raw.trim();
  if (!trimmed) return "";
  const triplet = extractXtreamTriplet(trimmed);
  if (triplet) return buildXtreamM3uUrl(triplet.baseUrl, triplet.username, triplet.password);
  const parsed = parseUrl(trimmed);
  if (!parsed) return trimmed;
  const creds = xtreamCredentialsFromUrl(parsed);
  if (creds && isXtreamUrl(parsed)) return buildXtreamM3uUrl(xtreamBaseUrl(parsed), creds.username, creds.password);
  return trimmed;
}

export function normalizeEpgInputs(raw: string): string[] {
  const trimmed = decodeLegacyBase64Url(raw.trim()) ?? raw.trim();
  if (!trimmed) return [];
  const triplet = extractXtreamTriplet(trimmed);
  if (triplet) return [buildXtreamEpgUrl(triplet.baseUrl, triplet.username, triplet.password)];
  const urls = trimmed.match(/https?:\/\/[^\s,;|]+/gi);
  if (urls && urls.length > 1) return [...new Set(urls.flatMap(normalizeEpgInputs))];
  const parts = trimmed.split(/[\n\r,;|]+/).map((part) => part.trim()).filter(Boolean);
  if (parts.length > 1) return [...new Set(parts.flatMap(normalizeEpgInputs))];
  const parsed = parseUrl(trimmed);
  if (!parsed) return [trimmed];
  const creds = xtreamCredentialsFromUrl(parsed);
  if (creds && isXtreamUrl(parsed)) return [buildXtreamEpgUrl(xtreamBaseUrl(parsed), creds.username, creds.password)];
  return [trimmed];
}

export function groupKey(channel: Pick<IptvChannel, "id" | "group">) {
  const playlistId = channel.id.split(":")[0] || "default";
  return `${playlistId}|${(channel.group || "Uncategorized").trim() || "Uncategorized"}`;
}

function parseUrl(value: string) {
  try {
    return new URL(value);
  } catch {
    return null;
  }
}

function decodeLegacyBase64Url(value: string): string | null {
  if (value.length < 12 || value.includes("://") || /\s/.test(value)) return null;
  try {
    const decoded = atob(value.replace(/-/g, "+").replace(/_/g, "/")).trim();
    return /^https?:\/\//i.test(decoded) ? decoded : null;
  } catch {
    return null;
  }
}

function extractXtreamTriplet(raw: string): { baseUrl: string; username: string; password: string } | null {
  const parts = raw
    .split(/\s+/)
    .map((part) => part.trim())
    .filter(Boolean);
  const lineParts = raw
    .split(/[\n\r]+/)
    .map((part) => part.trim())
    .filter(Boolean);
  const chosen = lineParts.length >= 3 ? lineParts : parts;
  if (chosen.length < 3) return null;
  const host = chosen[0];
  const username = chosen[1];
  const password = chosen[2];
  const baseUrl = normalizeXtreamHost(host);
  return baseUrl && username && password ? { baseUrl, username, password } : null;
}

function normalizeXtreamHost(host: string): string | null {
  const cleaned = host
    .trim()
    .replace(/^xtream:\/\//i, "")
    .replace(/^xstream:\/\//i, "")
    .replace(/^xtreamcodes:\/\//i, "")
    .replace(/^xc:\/\//i, "")
    .replace(/^http:\/(?!\/)/i, "http://")
    .replace(/^https:\/(?!\/)/i, "https://")
    .replace(/\/+$/, "");
  if (!cleaned) return null;
  return /^https?:\/\//i.test(cleaned) ? cleaned : `http://${cleaned}`;
}

function isXtreamUrl(url: URL) {
  const path = url.pathname.toLowerCase();
  return path.endsWith("/get.php") || path.endsWith("/xmltv.php") || path.endsWith("/player_api.php");
}

function xtreamCredentialsFromUrl(url: URL) {
  const username = url.searchParams.get("username") || url.searchParams.get("user") || url.searchParams.get("uname") || "";
  const password = url.searchParams.get("password") || url.searchParams.get("pass") || url.searchParams.get("pwd") || "";
  return username.trim() && password.trim() ? { username: username.trim(), password: password.trim() } : null;
}

function xtreamBaseUrl(url: URL) {
  const base = `${url.protocol}//${url.host}${url.pathname}`
    .replace(/\/(?:get|xmltv|player_api)\.php$/i, "")
    .replace(/\/+$/, "");
  return base;
}

function buildXtreamM3uUrl(baseUrl: string, username: string, password: string) {
  const target = new URL(`${baseUrl.replace(/\/+$/, "")}/get.php`);
  target.searchParams.set("username", username.trim());
  target.searchParams.set("password", password.trim());
  target.searchParams.set("type", "m3u_plus");
  target.searchParams.set("output", "m3u8");
  return target.toString();
}

function buildXtreamEpgUrl(baseUrl: string, username: string, password: string) {
  const target = new URL(`${baseUrl.replace(/\/+$/, "")}/xmltv.php`);
  target.searchParams.set("username", username.trim());
  target.searchParams.set("password", password.trim());
  return target.toString();
}

function buildXtreamPlayerApiUrl(baseUrl: string, username: string, password: string, action?: string) {
  const target = new URL(`${baseUrl.replace(/\/+$/, "")}/player_api.php`);
  target.searchParams.set("username", username.trim());
  target.searchParams.set("password", password.trim());
  if (action) target.searchParams.set("action", action);
  return target.toString();
}

function xtreamInfoFromPlaylist(url: string): XtreamInfo | null {
  const parsed = parseUrl(url);
  if (!parsed) return null;
  const creds = xtreamCredentialsFromUrl(parsed);
  if (!creds || !isXtreamUrl(parsed)) return null;
  return {
    baseUrl: xtreamBaseUrl(parsed),
    username: creds.username,
    password: creds.password
  };
}

function playlistProxyHeaders(userAgent?: string) {
  const trimmedUserAgent = userAgent?.trim() || DEFAULT_IPTV_USER_AGENT;
  return {
    Accept: "*/*",
    "User-Agent": trimmedUserAgent,
    "Icy-MetaData": "1"
  };
}

async function fetchXtreamChannels(playlist: IptvPlaylistEntry, options: IptvLoadOptions = {}) {
  const info = xtreamInfoFromPlaylist(playlist.m3uUrl);
  if (!info) return [] as IptvChannel[];
  const headers = playlistProxyHeaders(options.userAgent);
  const streamsUrl = buildXtreamPlayerApiUrl(info.baseUrl, info.username, info.password, "get_live_streams");
  const categoriesUrl = buildXtreamPlayerApiUrl(info.baseUrl, info.username, info.password, "get_live_categories");
  const [streams, categories] = await Promise.all([
    fetchXtreamJson<XtreamStream[]>(streamsUrl, headers),
    fetchXtreamJson<XtreamCategory[]>(categoriesUrl, headers).catch(() => [] as XtreamCategory[])
  ]);
  if (!Array.isArray(streams)) return [];
  const groupById = new Map(
    categories
      .map((category) => [String(category.category_id ?? ""), category.category_name?.trim() ?? ""])
      .filter((entry): entry is [string, string] => Boolean(entry[0] && entry[1]))
  );
  const seen = new Set<string>();

  return streams.flatMap((stream) => {
    const streamId = String(stream.stream_id ?? "").trim();
    if (!streamId) return [];
    const streamUrl = buildXtreamStreamUrl(info, streamId);
    // Match Android's provider identity; the browser uses HLS while Android
    // may use TS, so hashing the playback URL creates different favorites.
    const id = `${playlist.id}:xtream:${streamId}`;
    if (seen.has(id)) return [];
    seen.add(id);
    const group = groupById.get(String(stream.category_id ?? "")) || "Uncategorized";
    const name = stream.name?.trim() || `Channel ${streamId}`;
    // tv_archive=1 means the panel keeps a rolling archive of this channel
    // (catch-up); tv_archive_duration is how many days back it reaches.
    const archiveDays = Number(stream.tv_archive) ? Number(stream.tv_archive_duration) || 1 : 0;
    return [{
      id,
      name,
      group,
      logo: stream.stream_icon?.trim() || undefined,
      tvgId: stream.epg_channel_id?.trim() || undefined,
      number: stream.num === undefined ? undefined : String(stream.num),
      qualityLabel: inferQualityLabel(name, group),
      catchupDays: archiveDays,
      catchupType: archiveDays ? "xtream" : undefined,
      streamUrl
    } satisfies IptvChannel];
  });
}

function buildXtreamStreamUrl(info: XtreamInfo, streamId: string) {
  const base = info.baseUrl.replace(/\/+$/, "");
  return `${base}/live/${encodeURIComponent(info.username)}/${encodeURIComponent(info.password)}/${encodeURIComponent(streamId)}.m3u8`;
}

async function fetchXtreamJson<T>(url: string, headers: Record<string, string>) {
  const key = `${url}|${JSON.stringify(headers)}`;
  const cached = guideApiCache.get(key);
  if (cached && cached.expires > Date.now()) return cached.data as T;
  const existing = apiInFlight.get(key);
  if (existing) return existing as Promise<T>;
  const task = (async () => {
    const load = async () => {
      try {
        const text = await providerTextRequest(url, url);
        jsonFromText(text);
        return text;
      } catch {
        const text = await providerTextRequest(url, playlistTextUrl(url, headers));
        jsonFromText(text);
        return text;
      }
    };
    const action = new URL(url).searchParams.get("action");
    const guide = action === "get_short_epg" || action === "get_simple_data_table";
    const text = guide ? await load() : await cachedRemoteText(`api:${key}`, PLAYLIST_TTL_MS, load);
    const data = jsonFromText<T>(text);
    if (guide) {
      const empty = !(data as { epg_listings?: unknown[] })?.epg_listings?.length;
      guideApiCache.set(key, { data, expires: Date.now() + (empty ? 600_000 : 120_000) });
      while (guideApiCache.size > 128) guideApiCache.delete(guideApiCache.keys().next().value!);
    }
    return data;
  })();
  apiInFlight.set(key, task);
  try { return await task; } finally { if (apiInFlight.get(key) === task) apiInFlight.delete(key); }
}

function jsonFromText<T>(text: string) {
  if (isHtmlText(text)) throw new Error("The remote service returned an HTML error page instead of API data.");
  return JSON.parse(text) as T;
}

function isHtmlText(text: string) {
  const trimmed = text.trimStart().slice(0, 600).toLowerCase();
  return trimmed.startsWith("<!doctype html") || trimmed.startsWith("<html") || trimmed.includes("<html") || trimmed.includes("<head");
}

function isPlaylistText(text: string) {
  const trimmed = text.trimStart();
  return trimmed.startsWith("#EXTM3U") || trimmed.includes("#EXTINF");
}

function assertPlaylistText(text: string) {
  if (isPlaylistText(text)) return;
  if (isHtmlText(text)) throw new Error("The provider returned an HTML page instead of an M3U playlist.");
  throw new Error("The provider returned data, but it was not an M3U playlist.");
}

function orderGroups(grouped: Record<string, IptvChannel[]>, groupOrder: string[], channels: IptvChannel[]) {
  if (!groupOrder.length) return Object.keys(grouped);
  const firstChannelByGroup = channels.reduce<Record<string, IptvChannel>>((acc, channel) => {
    const group = channel.group || "Uncategorized";
    if (!acc[group]) acc[group] = channel;
    return acc;
  }, {});
  const orderMap = new Map(groupOrder.map((group, index) => [group, index]));
  return Object.keys(grouped).sort((a, b) => {
    const aKey = firstChannelByGroup[a] ? groupKey(firstChannelByGroup[a]) : a;
    const bKey = firstChannelByGroup[b] ? groupKey(firstChannelByGroup[b]) : b;
    const ai = orderMap.get(aKey) ?? orderMap.get(a) ?? Number.MAX_SAFE_INTEGER;
    const bi = orderMap.get(bKey) ?? orderMap.get(b) ?? Number.MAX_SAFE_INTEGER;
    return ai === bi ? 0 : ai - bi;
  });
}

export function parseM3u(text: string, playlistId = "default") {
  const lines = text.split(/\r?\n/);
  const channels: IptvChannel[] = [];
  const seen = new Set<string>();
  let pending: Record<string, string> | null = null;
  let requestHeaders: Record<string, string> = Object.create(null);

  for (const rawLine of lines) {
    const line = rawLine.trim();
    if (/^#EXTINF:/i.test(line)) {
      // Header values can contain commas; only an unquoted comma starts the title.
      const [, metadata = line, title = ""] = line.match(/^#EXTINF:((?:[^"',]|"[^"]*"|'[^']*')*),(.*)$/i) ?? [];
      pending = {
        name: attr(metadata, "tvg-name") || title.trim() || "Unknown Channel",
        group: attr(metadata, "group-title") || "Uncategorized",
        logo: attr(metadata, "tvg-logo"),
        tvgId: attr(metadata, "tvg-id"),
        number: firstAttr(metadata, ["tvg-chno", "tvg-ch-number", "channel-number", "ch-number", "number"]),
        catchupDays: attr(metadata, "catchup-days") || attr(metadata, "timeshift"),
        catchupType: attr(metadata, "catchup"),
        catchupSource: attr(metadata, "catchup-source"),
        language: firstAttr(metadata, ["tvg-language", "tvg-lang", "language", "lang"]),
        country: firstAttr(metadata, ["tvg-country", "country"]),
        qualityLabel: firstAttr(metadata, ["quality", "tvg-quality", "resolution"])
      };
      requestHeaders = Object.create(null);
      for (const match of metadata.matchAll(/(?:^|\s)([\w-]+)\s*=\s*(?:"([^"]*)"|'([^']*)'|([^\s,]+))/g)) {
        const name = m3uHeaderName(match[1]);
        if (name) setM3uHeader(requestHeaders, name, match[2] ?? match[3] ?? match[4] ?? "");
      }
    } else if (pending && /^#EXTVLCOPT:/i.test(line)) {
      const option = line.match(/^#EXTVLCOPT:\s*([^=]+)=(.*)$/i);
      const name = option && m3uHeaderName(option[1]);
      if (name && option) setM3uHeader(requestHeaders, name, option[2].trim().replace(/^(["'])(.*)\1$/, "$2"));
    } else if (pending && line && !line.startsWith("#")) {
      if (isDividerChannelName(pending.name)) {
        pending = null;
        continue;
      }
      const pipe = line.indexOf("|");
      const streamUrl = (pipe < 0 ? line : line.slice(0, pipe)).trim();
      // URL headers override entry options, without decoding/re-serializing a signed stream URL.
      if (pipe >= 0) {
        for (const [name, value] of new URLSearchParams(line.slice(pipe + 1).replace(/\|/g, "&"))) {
          setM3uHeader(requestHeaders, name, value);
        }
      }
      if (!streamUrl) {
        pending = null;
        continue;
      }
      const id = `${playlistId}:${buildChannelId(streamUrl, pending.tvgId)}`;
      if (seen.has(id)) {
        pending = null;
        continue;
      }
      seen.add(id);
      channels.push({
        id,
        name: pending.name || "Channel",
        group: pending.group || "Uncategorized",
        logo: pending.logo,
        tvgId: pending.tvgId,
        number: pending.number,
        catchupDays: Number(pending.catchupDays || 0),
        catchupType: pending.catchupType,
        catchupSource: pending.catchupSource,
        language: pending.language,
        country: pending.country,
        qualityLabel: pending.qualityLabel || inferQualityLabel(pending.name, pending.group),
        streamUrl,
        ...(Object.keys(requestHeaders).length ? { requestHeaders: { ...requestHeaders } } : {})
      });
      pending = null;
    }
  }

  return channels;
}

export function migrateXtreamFavoriteIds(favorites: string[], channels: IptvChannel[]): string[] {
  const legacyPrefixes = new Set(favorites.filter((id) => id.includes(":m3u:"))
    .map((id) => id.slice(0, id.lastIndexOf(":") + 1)));
  if (!legacyPrefixes.size) return favorites;
  const favoriteSet = new Set(favorites);
  const replacements = new Map<string, string>();
  for (const channel of channels) {
    const match = channel.id.match(/^(.+):xtream:\d+$/);
    if (!match) continue;
    const epg = normalizeChannelKey(channel.tvgId ?? "");
    const prefix = `${match[1]}:m3u:${epg ? `${epg}:` : ""}`;
    if (!legacyPrefixes.has(prefix)) continue;
    // Recognize the previous web HLS hash and Android's M3U/TS hash without
    // guessing by name (HD/SD variants and other playlists must stay distinct).
    for (const url of [channel.streamUrl, channel.streamUrl.replace(/\.m3u8(?=[?#]|$)/i, ".ts")]) {
      const legacy = `${match[1]}:${buildChannelId(url, channel.tvgId)}`;
      if (favoriteSet.has(legacy)) replacements.set(legacy, channel.id);
    }
  }
  if (!replacements.size) return favorites;
  return [...new Set(favorites.map((id) => replacements.get(id) ?? id))];
}

function m3uHeaderName(name: string): string | undefined {
  switch (name.trim().toLowerCase()) {
    case "http-user-agent": case "user-agent": case "useragent": return "User-Agent";
    case "http-referrer": case "http-referer": case "referrer": case "referer": return "Referer";
    case "http-origin": case "origin": return "Origin";
    default: return undefined;
  }
}

function setM3uHeader(headers: Record<string, string>, rawName: string, rawValue: string) {
  const name = m3uHeaderName(rawName) ?? rawName.trim();
  const value = rawValue.trim();
  if (!/^[!#$%&'*+.^_`|~0-9a-z-]+$/i.test(name) || !value || /[^\t\x20-\x7e]/.test(rawValue)) return;
  const existing = Object.keys(headers).find((key) => key.toLowerCase() === name.toLowerCase());
  headers[existing ?? name] = value;
}

export function isDividerChannelName(name?: string) {
  const trimmed = (name ?? "").trim();
  if (!trimmed) return true;
  // Separator rows like "###### ALBANIA ######" or "---- SPORTS ----" are
  // playlist decoration, not channels; playing them always fails.
  if (/^[#=\-*•_~|<>♦►\s]+$/.test(trimmed)) return true;
  return /^[#=\-*•_~|<>♦►]{3,}/.test(trimmed) && /[#=\-*•_~|<>♦►]{3,}$/.test(trimmed);
}

async function loadNowNext(playlists: IptvPlaylistEntry[], channels: IptvChannel[]) {
  if (!channels.length) return {};
  const programsById: Record<string, IptvProgram[]> = {};
  for (const playlist of playlists) {
    const lookup = new Map<string, Set<string>>();
    for (const channel of channels) {
      if (!channel.id.startsWith(`${playlist.id}:`)) continue;
      for (const key of [channel.tvgId, channel.name]) {
        if (!key?.trim()) continue;
        const normalized = key.trim().toLowerCase();
        if (!lookup.has(normalized)) lookup.set(normalized, new Set());
        lookup.get(normalized)!.add(channel.id);
      }
    }
    if (!lookup.size) continue;
    const urls = [...new Set([playlist.epgUrl, ...(playlist.epgUrls ?? [])].filter((url): url is string => Boolean(url?.trim())))];
    for (const url of urls) {
      const programs = await cachedXmltvPrograms(url).catch(() => []);
      const matchedThisSource = new Set<string>();
      for (const { channel, program } of programs) {
        for (const id of lookup.get(channel.trim().toLowerCase()) ?? []) {
          // Earlier configured sources own the channel when they contain data.
          if (programsById[id] && !matchedThisSource.has(id)) continue;
          matchedThisSource.add(id);
          (programsById[id] ??= []).push(program);
        }
      }
    }
  }

  const now = Date.now();
  return Object.fromEntries(Object.entries(programsById).map(([channelId, programs]) => {
    const sorted = [...new Map(programs.map((p) => [p.startUtcMillis, p])).values()].sort((a, b) => a.startUtcMillis - b.startUtcMillis);
    const live = sorted.find((program) => now >= program.startUtcMillis && now < program.endUtcMillis);
    const future = sorted.filter((program) => program.startUtcMillis > now);
    const recent = sorted.filter((program) => program.endUtcMillis <= now && program.endUtcMillis >= now - 48 * 3600_000);
    return [channelId, {
      now: live,
      next: future[0],
      later: future[1],
      upcoming: future.filter((program) => program.startUtcMillis < now + 48 * 3600_000),
      recent
    } satisfies IptvNowNext];
  }));
}

const parsedGuides = new Map<string, { at: number; promise: Promise<ReturnType<typeof parseXmltv>> }>();
function cachedXmltvPrograms(url: string) {
  const cached = parsedGuides.get(url);
  if (cached && Date.now() - cached.at < EPG_TTL_MS) return cached.promise;
  const promise = fetchEpgText(url).then(parseXmltv).catch((error) => {
    parsedGuides.delete(url);
    throw error;
  });
  if (parsedGuides.size >= 4) parsedGuides.delete(parsedGuides.keys().next().value!);
  parsedGuides.set(url, { at: Date.now(), promise });
  return promise;
}

function parseXmltv(xml: string) {
  const results: Array<{ channel: string; program: IptvProgram }> = [];
  const programRe = /<programme\b([^>]*)>([\s\S]*?)<\/programme>/gi;
  let match: RegExpExecArray | null;
  while ((match = programRe.exec(xml))) {
    const attrs = match[1];
    const body = match[2];
    const channel = attr(attrs, "channel");
    const start = parseXmltvTime(attr(attrs, "start"));
    const stop = parseXmltvTime(attr(attrs, "stop"));
    if (!channel || !start || !stop) continue;
    results.push({
      channel,
      program: {
        title: decodeXml(textTag(body, "title") || "Untitled"),
        description: decodeXml(textTag(body, "desc") || ""),
        artworkUrl: safeGuideImage(decodeXml(attr(body.match(/<icon\b([^>]*)\/?\s*>/i)?.[1] ?? "", "src"))),
        category: decodeXml(textTag(body, "category") || "").slice(0, 200) || undefined,
        startUtcMillis: start,
        endUtcMillis: stop
      }
    });
  }
  return results;
}

function safeGuideImage(value: string): string | undefined {
  try { return value.length <= 2048 && ["https:", "http:"].includes(new URL(value).protocol) ? value : undefined; } catch { return undefined; }
}

const identityLoads = new Map<string, { expires: number; task: Promise<IptvChannel[]> }>();

/** A single cached metadata request, never a stream probe or a request per channel. */
export async function loadIptvChannelIdentities(playlists: IptvPlaylistEntry[], channels: IptvChannel[], options: IptvLoadOptions = {}) {
  const replacements = new Map<string, IptvChannel>();
  for (const playlist of normalizeIptvPlaylists(playlists).filter(p => p.enabled)) {
    const scoped = channels.filter(c => c.id.startsWith(`${playlist.id}:xtream:`));
    if (!scoped.length) continue;
    const url = new URL(playlist.m3uUrl);
    url.searchParams.set("output", "ts");
    const key = `${playlist.id}:${url}`;
    let cached = identityLoads.get(key);
    if (!cached || cached.expires < Date.now()) {
      const task = fetchPlaylistText(url.toString(), options)
        .then(text => attachM3uChannelIdentities(scoped, text, playlist.id));
      cached = { expires: Date.now() + PLAYLIST_TTL_MS, task };
      identityLoads.set(key, cached);
      // A provider failure must not become a render/retry loop.
      void task.catch(() => { if (identityLoads.get(key) === cached) cached!.expires = Date.now() + 300_000; });
      if (identityLoads.size > 12) identityLoads.delete(identityLoads.keys().next().value!);
    }
    try { for (const channel of await cached.task) replacements.set(channel.id, channel); }
    catch { /* Keep API channels available when the optional identity bridge fails. */ }
  }
  return channels.map(channel => {
    const identity = replacements.get(channel.id);
    return identity?.cloudId ? { ...channel, cloudId: identity.cloudId, syncAliases: identity.syncAliases } : channel;
  });
}

export async function attachM3uChannelIdentities(channels: IptvChannel[], text: string, playlistId: string) {
  const byId = new Map(channels.map(c => [c.id, c]));
  const replacements = new Map<string, IptvChannel>();
  let epgId: string | undefined;
  const lines = text.split(/\r?\n/);
  for (let i = 0; i < lines.length; i++) {
    if (i > 0 && i % 500 === 0) await new Promise(resolve => setTimeout(resolve, 0));
    const line = lines[i].trim();
    if (line.startsWith("#EXTINF:")) { epgId = attr(line, "tvg-id"); continue; }
    if (!line || line.startsWith("#")) continue;
    const path = parseUrl(line)?.pathname;
    const streamId = path?.match(/\/(\d+)(?:\.[a-z0-9]+)?$/i)?.[1];
    const id = `${playlistId}:xtream:${streamId}`;
    const channel = byId.get(id);
    if (channel) {
      const cloudId = `${playlistId}:${buildChannelId(line, epgId)}`;
      const hlsAlias = `${playlistId}:${buildChannelId(line.replace(/\.ts(?=\?|$)/i, ".m3u8"), epgId)}`;
      const previous = replacements.get(id);
      replacements.set(id, { ...channel, cloudId: previous?.cloudId ?? cloudId,
        syncAliases: [...new Set([...(previous?.syncAliases ?? []), cloudId, hlsAlias])] });
    }
    epgId = undefined;
  }
  return channels.map(channel => replacements.get(channel.id) ?? channel);
}

function parseXmltvTime(value: string) {
  const match = value.match(/^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})\s*([+-]\d{4})?/);
  if (!match) return 0;
  const [, year, month, day, hour, minute, second, offset] = match;
  const base = Date.UTC(Number(year), Number(month) - 1, Number(day), Number(hour), Number(minute), Number(second));
  if (!offset) return base;
  const sign = offset.startsWith("-") ? -1 : 1;
  const hours = Number(offset.slice(1, 3));
  const minutes = Number(offset.slice(3, 5));
  return base - sign * (hours * 60 + minutes) * 60_000;
}

function textTag(xml: string, tag: string) {
  return xml.match(new RegExp(`<${tag}\\b[^>]*>([\\s\\S]*?)<\\/${tag}>`, "i"))?.[1]?.trim() ?? "";
}

function decodeXml(value: string) {
  return value
    .replace(/&amp;/g, "&")
    .replace(/&lt;/g, "<")
    .replace(/&gt;/g, ">")
    .replace(/&quot;/g, '"')
    .replace(/&#39;/g, "'");
}

function attr(line: string, name: string) {
  const match = line.match(new RegExp(`${name}\\s*=\\s*(?:"([^"]*)"|'([^']*)'|([^\\s,]+))`, "i"));
  return match?.[1] ?? match?.[2] ?? match?.[3] ?? "";
}

async function fetchEpgText(url: string) {
  return cachedRemoteText(
    `epg:${url}`,
    EPG_TTL_MS,
    async () => {
      try {
        return await providerTextRequest(url, url);
      } catch {
        return providerTextRequest(url, proxiedUrl(url));
      }
    },
    isXmltvText
  );
}

function isXmltvText(text: string) {
  const trimmed = text.trimStart();
  return trimmed.startsWith("<?xml") || trimmed.includes("<tv") || trimmed.includes("<programme");
}

async function cachedRemoteText(key: string, ttlMs: number, loader: () => Promise<string>, validator?: TextValidator) {
  const cacheKey = `https://arvio.local/iptv-cache/${sha1Hex(key)}`;
  const cached = await readCachedText(cacheKey);
  const cachedIsValid = cached?.text && (!validator || validator(cached.text));
  if (cached && cachedIsValid && Date.now() - cached.cachedAt < ttlMs) return cached.text;
  try {
    const fresh = await loader();
    if (validator && !validator(fresh)) throw new Error("The remote response was not valid data.");
    await writeCachedText(cacheKey, fresh);
    return fresh;
  } catch (error) {
    if (cachedIsValid) return cached.text;
    throw error;
  }
}

async function readCachedText(cacheKey: string) {
  if (typeof caches === "undefined" || typeof localStorage === "undefined") return null;
  try {
    const meta = loadStored<Record<string, number>>(IPTV_TEXT_CACHE_META, {});
    const cachedAt = meta[cacheKey] ?? 0;
    if (!cachedAt) return null;
    const cache = await caches.open(IPTV_TEXT_CACHE);
    const response = await cache.match(cacheKey);
    if (!response) return null;
    return { text: await response.text(), cachedAt };
  } catch {
    return null;
  }
}

async function writeCachedText(cacheKey: string, text: string) {
  if (typeof caches === "undefined" || typeof localStorage === "undefined") return;
  try {
    const cache = await caches.open(IPTV_TEXT_CACHE);
    await cache.put(cacheKey, new Response(text, { headers: { "content-type": "text/plain; charset=utf-8" } }));
    const meta = loadStored<Record<string, number>>(IPTV_TEXT_CACHE_META, {});
    const next = { ...meta, [cacheKey]: Date.now() };
    const entries = Object.entries(next).sort((a, b) => b[1] - a[1]).slice(0, 12);
    saveStored(IPTV_TEXT_CACHE_META, Object.fromEntries(entries));
  } catch {
    // Cache API quota varies by browser; failing to cache should never block TV.
  }
}

function buildChannelId(streamUrl: string, epgId?: string) {
  const streamKey = stableStreamKey(streamUrl);
  const normalizedEpg = normalizeChannelKey(epgId ?? "");
  return normalizedEpg ? `m3u:${normalizedEpg}:${streamKey}` : `m3u:${streamKey}`;
}

function stableStreamKey(streamUrl: string) {
  const normalized = streamUrl.trim();
  if (!normalized) return "empty";
  return `${normalized.length}-${sha1Hex(normalized).slice(0, 16)}`;
}

function normalizeChannelKey(value: string) {
  return value.trim().toLowerCase().replace(/\s+/g, " ");
}

function inferQualityLabel(name?: string, group?: string) {
  const text = `${name ?? ""} ${group ?? ""}`.toUpperCase();
  if (text.includes("UHD") || text.includes("4K") || text.includes("2160")) return "4K";
  if (text.includes("FHD") || text.includes("1080")) return "FHD";
  if (text.includes("HD") || text.includes("720")) return "HD";
  if (text.includes("SD")) return "SD";
  return undefined;
}

function firstAttr(line: string, names: string[]) {
  for (const name of names) {
    const value = attr(line, name);
    if (value) return value;
  }
  return "";
}

function sha1Hex(input: string) {
  const bytes = new TextEncoder().encode(input);
  const words: number[] = [];
  for (let i = 0; i < bytes.length; i += 1) words[i >> 2] |= bytes[i] << (24 - (i % 4) * 8);
  words[bytes.length >> 2] |= 0x80 << (24 - (bytes.length % 4) * 8);
  words[(((bytes.length + 8) >> 6) << 4) + 15] = bytes.length * 8;
  let h0 = 0x67452301;
  let h1 = 0xefcdab89;
  let h2 = 0x98badcfe;
  let h3 = 0x10325476;
  let h4 = 0xc3d2e1f0;
  for (let i = 0; i < words.length; i += 16) {
    const w = new Array<number>(80);
    for (let t = 0; t < 16; t += 1) w[t] = words[i + t] | 0;
    for (let t = 16; t < 80; t += 1) w[t] = rol(w[t - 3] ^ w[t - 8] ^ w[t - 14] ^ w[t - 16], 1);
    let a = h0;
    let b = h1;
    let c = h2;
    let d = h3;
    let e = h4;
    for (let t = 0; t < 80; t += 1) {
      const f = t < 20 ? (b & c) | (~b & d) : t < 40 ? b ^ c ^ d : t < 60 ? (b & c) | (b & d) | (c & d) : b ^ c ^ d;
      const k = t < 20 ? 0x5a827999 : t < 40 ? 0x6ed9eba1 : t < 60 ? 0x8f1bbcdc : 0xca62c1d6;
      const temp = (rol(a, 5) + f + e + k + w[t]) | 0;
      e = d;
      d = c;
      c = rol(b, 30);
      b = a;
      a = temp;
    }
    h0 = (h0 + a) | 0;
    h1 = (h1 + b) | 0;
    h2 = (h2 + c) | 0;
    h3 = (h3 + d) | 0;
    h4 = (h4 + e) | 0;
  }
  return [h0, h1, h2, h3, h4].map((h) => (h >>> 0).toString(16).padStart(8, "0")).join("");
}

function rol(value: number, bits: number) {
  return (value << bits) | (value >>> (32 - bits));
}

/**
 * Identity of a playlist set. Live TV reuses an in-memory snapshot while this
 * is unchanged, instead of re-parsing every channel on each visit (a large
 * provider is ~139k channels, measured at ~3.3s of pure CPU work per entry).
 */
export function iptvPlaylistSignature(playlists: IptvPlaylistEntry[]): string {
  return playlists
    .map((playlist) => `${playlist.id}:${playlist.enabled}:${playlist.m3uUrl}:${playlist.epgUrl ?? ""}:${playlist.epgUrls?.join("|") ?? ""}`)
    .join("||");
}

/** How long an in-memory channel snapshot stays good before a rebuild. */
export const IPTV_SNAPSHOT_TTL_MS = 6 * 60 * 60 * 1000;

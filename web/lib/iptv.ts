import { proxiedUrl, textRequest } from "./http";
import { loadStored, saveStored } from "./storage";
import type { IptvChannel, IptvNowNext, IptvPlaylistEntry, IptvProgram, IptvSnapshot } from "./types";

const IPTV_KEY = "arvio.web.iptv.playlists";

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
  groupOrder: string[] = []
): Promise<IptvSnapshot> {
  const enabled = playlists.filter((playlist) => playlist.enabled && playlist.m3uUrl.trim());
  const channelSets = await Promise.all(
    enabled.map(async (playlist) => {
      try {
        const text = await textRequest(proxiedUrl(playlist.m3uUrl));
        return parseM3u(text, playlist.id);
      } catch {
        return [] as IptvChannel[];
      }
    })
  );
  const channels = channelSets.flat();
  const nowNext = await loadNowNext(enabled, channels).catch(() => ({} as Record<string, IptvNowNext>));
  const hidden = new Set(hiddenGroups);
  const grouped = channels.reduce<Record<string, IptvChannel[]>>((acc, channel) => {
    const group = channel.group || "Uncategorized";
    if (hidden.has(group)) return acc;
    acc[group] = [...(acc[group] ?? []), channel];
    return acc;
  }, {});
  const orderedGroups = groupOrder.filter((group) => grouped[group]).concat(Object.keys(grouped).filter((group) => !groupOrder.includes(group)));
  return {
    channels,
    grouped: Object.fromEntries(orderedGroups.map((group) => [group, grouped[group]])),
    nowNext,
    favoriteChannels,
    favoriteGroups,
    hiddenGroups,
    groupOrder,
    loadedAt: Date.now()
  };
}

export function parseM3u(text: string, playlistId = "default") {
  const lines = text.split(/\r?\n/);
  const channels: IptvChannel[] = [];
  let pending: Record<string, string> | null = null;

  for (const line of lines) {
    if (line.startsWith("#EXTINF")) {
      const title = line.split(",").slice(1).join(",").trim();
      pending = {
        name: title,
        group: attr(line, "group-title") || "Uncategorized",
        logo: attr(line, "tvg-logo"),
        tvgId: attr(line, "tvg-id"),
        number: attr(line, "tvg-chno"),
        catchupDays: attr(line, "catchup-days") || attr(line, "timeshift"),
        catchupType: attr(line, "catchup"),
        catchupSource: attr(line, "catchup-source"),
        language: attr(line, "tvg-language"),
        country: attr(line, "tvg-country")
      };
    } else if (pending && line.trim() && !line.startsWith("#")) {
      const streamUrl = line.trim();
      channels.push({
        id: `${playlistId}:${pending.tvgId || pending.name}:${channels.length}`,
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
        streamUrl
      });
      pending = null;
    }
  }

  return channels;
}

async function loadNowNext(playlists: IptvPlaylistEntry[], channels: IptvChannel[]) {
  const urls = playlists.flatMap((playlist) => [playlist.epgUrl, ...(playlist.epgUrls ?? [])]).filter((url): url is string => Boolean(url?.trim()));
  if (!urls.length || !channels.length) return {};
  const programsById: Record<string, IptvProgram[]> = {};
  const channelLookup = new Map(channels.flatMap((channel) => [
    [channel.tvgId?.toLowerCase(), channel.id],
    [channel.name.toLowerCase(), channel.id]
  ].filter((pair): pair is [string, string] => Boolean(pair[0]))));

  const xmlTexts = await Promise.all(urls.slice(0, 3).map((url) => textRequest(proxiedUrl(url)).catch(() => "")));
  for (const xml of xmlTexts) {
    for (const program of parseXmltv(xml)) {
      const channelId = channelLookup.get(program.channel.toLowerCase());
      if (!channelId) continue;
      programsById[channelId] = [...(programsById[channelId] ?? []), program.program];
    }
  }

  const now = Date.now();
  return Object.fromEntries(Object.entries(programsById).map(([channelId, programs]) => {
    const sorted = programs.sort((a, b) => a.startUtcMillis - b.startUtcMillis);
    const live = sorted.find((program) => now >= program.startUtcMillis && now < program.endUtcMillis);
    const future = sorted.filter((program) => program.startUtcMillis > now);
    const recent = sorted.filter((program) => program.endUtcMillis <= now).slice(-12);
    return [channelId, {
      now: live,
      next: future[0],
      later: future[1],
      upcoming: future.slice(0, 8),
      recent
    } satisfies IptvNowNext];
  }));
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
        startUtcMillis: start,
        endUtcMillis: stop
      }
    });
  }
  return results;
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
  const match = line.match(new RegExp(`${name}="([^"]*)"`, "i"));
  return match?.[1] ?? "";
}

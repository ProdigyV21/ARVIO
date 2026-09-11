import type { IptvChannel, IptvSnapshot, IptvTvSession } from "./types";

export function isCurrentIptvSnapshot(snapshot: IptvSnapshot, scopeKey: string, signature: string) {
  return snapshot.scopeKey === scopeKey && snapshot.signature === signature;
}

export function normalizeTvSession(value: unknown): IptvTvSession {
  let raw = value;
  if (typeof raw === "string") { try { raw = JSON.parse(raw); } catch { raw = null; } }
  const session = (raw && typeof raw === "object" ? raw : {}) as Partial<IptvTvSession>;
  return {
    lastChannelId: typeof session.lastChannelId === "string" ? session.lastChannelId : "",
    lastGroupName: typeof session.lastGroupName === "string" ? session.lastGroupName : "",
    lastFocusedZone: typeof session.lastFocusedZone === "string" ? session.lastFocusedZone : "GUIDE",
    lastOpenedAt: Number.isFinite(session.lastOpenedAt) ? Math.max(0, session.lastOpenedAt!) : 0,
    recentChannelIds: [...new Set(Array.isArray(session.recentChannelIds)
      ? session.recentChannelIds.filter((id): id is string => typeof id === "string" && Boolean(id.trim())) : [])].slice(-40)
  };
}

export function channelIdentityIndex(channels: IptvChannel[]): Map<string, IptvChannel> {
  const index = new Map<string, IptvChannel>();
  for (const channel of channels) {
    index.set(channel.id, channel);
    if (channel.cloudId) index.set(channel.cloudId, channel);
    for (const alias of channel.syncAliases ?? []) index.set(alias, channel);
  }
  return index;
}

export function resolveChannelReferences(ids: string[], index: Map<string, IptvChannel>): IptvChannel[] {
  const seen = new Set<string>();
  return ids.flatMap(id => {
    const channel = index.get(id);
    if (!channel || seen.has(channel.id)) return [];
    seen.add(channel.id);
    return [channel];
  });
}

export function recordTvPlayback(value: unknown, channel: IptvChannel, now = Date.now()): IptvTvSession {
  const session = normalizeTvSession(value);
  const id = channel.cloudId || channel.id;
  const aliases = new Set([channel.id, id, ...(channel.syncAliases ?? [])]);
  return { ...session, lastChannelId: id, lastGroupName: channel.group, lastFocusedZone: "GUIDE", lastOpenedAt: now,
    recentChannelIds: [...session.recentChannelIds.filter(previous => !aliases.has(previous)), id].slice(-40) };
}

export function mergeTvSessions(remote: unknown, local: unknown): IptvTvSession {
  const a = normalizeTvSession(remote), b = normalizeTvSession(local);
  const [older, newer] = a.lastOpenedAt > b.lastOpenedAt ? [b, a] : [a, b];
  // Preserve plays made on another device while this browser was offline.
  return { ...newer, recentChannelIds: [...older.recentChannelIds.filter(id => !newer.recentChannelIds.includes(id)),
    ...newer.recentChannelIds].slice(-40) };
}

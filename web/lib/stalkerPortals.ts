// Stalker/Ministra portals in the Android app's multi-portal shape
// (iptvByProfile.<id>.stalkerPortals, see StalkerPortalSupport.kt):
//   - up to ten portals with stable IDs;
//   - channel ids `stalker:<portalId>:<channelId>`, category keys `<portalId>|<group>`;
//   - the old single-portal fields (stalkerPortalUrl / stalkerMacAddress) become
//     "Portal 1", and old `stalker|<group>` keys become `stalker1|<group>`.
import type { AppSettings, StalkerPortalEntry } from "./types";

export const MAX_STALKER_PORTALS = 10;
/** Playlist id the single-portal builds used for every Stalker channel and category key. */
export const LEGACY_STALKER_ID = "stalker";

type PortalSettings = Pick<AppSettings, "iptvStalkerUrl" | "iptvStalkerMac"> & Partial<Pick<AppSettings, "iptvStalkerPortals">>;

const invisible = /[​-‍⁠﻿]/g;
const text = (value: unknown) => (typeof value === "string" ? value : "").replace(invisible, "").trim();

/** Android's normalisation: trimmed URL without a trailing slash, upper-case MAC. */
export function normalizePortalUrl(value: unknown) { return text(value).replace(/\/+$/, ""); }
export function normalizePortalMac(value: unknown) { return text(value).toUpperCase(); }

/**
 * The next free "stalkerN" id (StalkerPortalSupport.nextAvailablePortalId), or
 * null when the list is full. `reserved` are ids that settings still refer to
 * (a removed portal's hidden categories, favourites, names…): a new portal
 * never takes one over, so it does not inherit another provider's layout.
 */
export function nextStalkerPortalId(existing: readonly string[], reserved: readonly string[] = [], max = MAX_STALKER_PORTALS): string | null {
  const used = new Set(existing.map((id) => id.trim()));
  if (max <= 0 || used.size >= max) return null;
  const taken = new Set([...used, ...reserved.map((id) => id.trim())]);
  let suffix = Math.max(0, ...[...taken].map((id) => Number(id.replace(/^stalker/, ""))).filter((n) => Number.isInteger(n) && n > 0)) + 1;
  while (taken.has(`stalker${suffix}`)) suffix++;
  return `stalker${suffix}`;
}

/** Portal ids ("stalkerN") that category keys or channel ids in these settings still refer to. */
export function referencedStalkerPortalIds(settings: Partial<AppSettings>): string[] {
  const ids = new Set<string>();
  const fromKey = (key: string) => { const id = key.split("|")[0]; if (/^stalker\d+$/.test(id)) ids.add(id); };
  const fromChannel = (id: string) => { const match = id.match(/^stalker:(stalker\d+):/); if (match) ids.add(match[1]); };
  for (const list of [settings.hiddenGroupIds, settings.lockedIptvGroupIds, settings.groupOrder, settings.favoriteGroupIds]) (list ?? []).forEach(fromKey);
  Object.keys(settings.iptvChannelOrder ?? {}).forEach(fromKey);
  Object.keys(settings.iptvGroupNames ?? {}).forEach(fromKey);
  Object.values(settings.iptvChannelOrder ?? {}).forEach((refs) => refs.forEach(fromChannel));
  (settings.favoriteChannelIds ?? []).forEach(fromChannel);
  (settings.iptvTvSession?.recentChannelIds ?? []).forEach(fromChannel);
  if (settings.iptvTvSession?.lastChannelId) fromChannel(settings.iptvTvSession.lastChannelId);
  return [...ids];
}

/**
 * Read a synced portal list. Entries without an address or MAC are dropped
 * (Android drops them too); every other field of an entry is kept as it was.
 */
export function normalizeStalkerPortals(value: unknown): StalkerPortalEntry[] {
  if (!Array.isArray(value)) return [];
  const used = new Set<string>();
  const result: StalkerPortalEntry[] = [];
  value.forEach((raw, index) => {
    if (result.length >= MAX_STALKER_PORTALS || !raw || typeof raw !== "object") return;
    const entry = raw as Record<string, unknown>;
    const portalUrl = normalizePortalUrl(entry.portalUrl);
    const macAddress = normalizePortalMac(entry.macAddress);
    if (!portalUrl || !macAddress) return;
    const wanted = text(entry.id) || `stalker${index + 1}`;
    const id = used.has(wanted) ? nextStalkerPortalId([...used]) : wanted;
    if (!id) return;
    used.add(id);
    result.push({ ...entry, id, name: text(entry.name) || `Portal ${index + 1}`, portalUrl, macAddress, enabled: entry.enabled !== false });
  });
  return result;
}

/** The single-portal fields as Portal 1 (StalkerPortalSupport.migratedPortalFromLegacy). */
export function legacyStalkerPortal(url: unknown, mac: unknown): StalkerPortalEntry | null {
  const portalUrl = normalizePortalUrl(url);
  const macAddress = normalizePortalMac(mac);
  return portalUrl && macAddress ? { id: "stalker1", name: "Portal 1", portalUrl, macAddress, enabled: true } : null;
}

/** Android's import rule: the list when it has entries, otherwise the legacy single portal. */
export function portalsFromCloud(list: unknown, legacyUrl: unknown, legacyMac: unknown): StalkerPortalEntry[] {
  const portals = normalizeStalkerPortals(list);
  if (portals.length) return portals;
  const legacy = legacyStalkerPortal(legacyUrl, legacyMac);
  return legacy ? [legacy] : [];
}

/** Every configured portal, enabled or not. */
export function stalkerPortalsOf(settings: PortalSettings): StalkerPortalEntry[] {
  if (settings.iptvStalkerPortals) return settings.iptvStalkerPortals;
  const legacy = legacyStalkerPortal(settings.iptvStalkerUrl, settings.iptvStalkerMac);
  return legacy ? [legacy] : [];
}

/** Portals that fill Live TV: enabled, complete, and live TV import not switched off (activeStalkerLiveTvPortals). */
export function liveTvStalkerPortals(settings: PortalSettings): StalkerPortalEntry[] {
  return stalkerPortalsOf(settings).filter((portal) => portal.enabled !== false && portal.portalUrl.trim() && portal.macAddress.trim() && portal.importLiveTv !== false);
}

export function hasStalkerPortals(settings: PortalSettings) {
  return liveTvStalkerPortals(settings).length > 0;
}

/** Part of the Live TV source signature: changes when a loading portal is added, removed or edited. */
export function stalkerSourceSignature(settings: PortalSettings): string {
  const portals = liveTvStalkerPortals(settings);
  return portals.length ? JSON.stringify(portals.map((portal) => [portal.id, portal.portalUrl, portal.macAddress])) : "";
}

/** Settings patch for a new portal list, keeping the legacy fields on the first enabled portal for older apps. */
export function stalkerPortalsPatch(portals: StalkerPortalEntry[]): Pick<AppSettings, "iptvStalkerPortals" | "iptvStalkerUrl" | "iptvStalkerMac"> {
  const first = portals.find((portal) => portal.enabled !== false);
  return { iptvStalkerPortals: portals, iptvStalkerUrl: first?.portalUrl ?? "", iptvStalkerMac: first?.macAddress ?? "" };
}

/** Legacy fields to publish for a portal list (first enabled portal). */
export function legacyStalkerFields(portals: StalkerPortalEntry[]) {
  const { iptvStalkerUrl, iptvStalkerMac } = stalkerPortalsPatch(portals);
  return { stalkerPortalUrl: iptvStalkerUrl, stalkerMacAddress: iptvStalkerMac };
}

export type PortalDraftProblem = "missing" | "bad-url" | "bad-mac";

/** Problems with a portal the user typed, before it is saved or tested. */
export function portalDraftProblem(url: string, mac: string): PortalDraftProblem | null {
  const portalUrl = normalizePortalUrl(url);
  const macAddress = normalizePortalMac(mac);
  if (!portalUrl || !macAddress) return "missing";
  if (!/^https?:\/\/[^/\s]+/i.test(portalUrl)) return "bad-url";
  if (!/^([0-9A-F]{2}:){5}[0-9A-F]{2}$/.test(macAddress)) return "bad-mac";
  return null;
}

/** Add a portal at the end, or replace the one with the same id (its other fields are kept). */
export function upsertStalkerPortal(portals: StalkerPortalEntry[], draft: { id?: string; name: string; portalUrl: string; macAddress: string }, reserved: readonly string[] = []): StalkerPortalEntry[] {
  const portalUrl = normalizePortalUrl(draft.portalUrl);
  const macAddress = normalizePortalMac(draft.macAddress);
  const existing = draft.id ? portals.find((portal) => portal.id === draft.id) : undefined;
  if (existing) {
    return portals.map((portal) => portal.id === existing.id
      ? { ...portal, name: draft.name.trim() || portal.name, portalUrl, macAddress }
      : portal);
  }
  const id = nextStalkerPortalId(portals.map((portal) => portal.id), reserved);
  if (!id) return portals;
  return [...portals, { id, name: draft.name.trim() || `Portal ${id.replace(/^stalker/, "")}`, portalUrl, macAddress, enabled: true }];
}

/* ---------- channel ids and category keys ---------- */

/** The playlist (or portal) a channel belongs to (StalkerPortalSupport.playlistIdFromChannelId). */
export function channelPlaylistId(channelId: string): string {
  if (channelId.startsWith("stalker:")) {
    const portal = channelId.slice(8).split(":")[0];
    if (portal) return portal;
  }
  return channelId.split(":")[0] || "default";
}

/** `stalker|<group>` → `stalker1|<group>` (StalkerPortalSupport.migrateLegacyPlaylistGroupKey). */
export function migrateLegacyStalkerKey(key: string): string {
  return key.startsWith(`${LEGACY_STALKER_ID}|`) ? `stalker1|${key.slice(LEGACY_STALKER_ID.length + 1)}` : key;
}

const isLegacyKey = (key: string) => key.startsWith(`${LEGACY_STALKER_ID}|`);

/**
 * Old `stalker|…` keys as Portal 1 keys. When both shapes are present the
 * new-shape key (and its position) wins and its legacy twin is dropped, so an
 * edit written in the new shape is never overridden by a stale legacy key.
 */
export function migrateLegacyStalkerKeys(keys: string[]): string[] {
  if (!keys.some(isLegacyKey)) return keys;
  const modern = new Set(keys.filter((key) => !isLegacyKey(key)));
  return [...new Set(keys.filter((key) => !isLegacyKey(key) || !modern.has(migrateLegacyStalkerKey(key))).map(migrateLegacyStalkerKey))];
}

/**
 * Channel ids of older single-portal builds as Portal ids:
 * Android's `stalker:<channel>` becomes `stalker:stalker1:<channel>`
 * (StalkerPortalSupport.migrateLegacyChannelId), and the web's
 * `stalker:<encoded portal address>:<channel>` becomes the id of the portal
 * with that address. Anything else is returned unchanged.
 */
export function migrateLegacyStalkerChannelId(id: string, portals: readonly StalkerPortalEntry[]): string {
  if (!id.startsWith("stalker:")) return id;
  const rest = id.slice(8);
  const split = rest.indexOf(":");
  if (split < 0) return rest ? `stalker:stalker1:${rest}` : id;
  const segment = rest.slice(0, split);
  if (!/%3A/i.test(segment)) return id;
  let address: string;
  try { address = normalizePortalUrl(decodeURIComponent(segment)); } catch { return id; }
  const portal = portals.find((entry) => normalizePortalUrl(entry.portalUrl) === address);
  return portal ? `stalker:${portal.id}:${rest.slice(split + 1)}` : id;
}

export function migrateLegacyStalkerChannelIds(ids: string[], portals: readonly StalkerPortalEntry[]): string[] {
  const next = ids.map((id) => migrateLegacyStalkerChannelId(id, portals));
  return next.some((id, index) => id !== ids[index]) ? [...new Set(next)] : ids;
}

const record = (value: unknown): Record<string, unknown> | null =>
  value && typeof value === "object" && !Array.isArray(value) ? value as Record<string, unknown> : null;

/**
 * One synced IPTV profile (iptvByProfile.<id>) with every category key and
 * channel id in the current shape. Applied to what the cloud holds before a
 * web edit is merged into it, so a write never carries a legacy twin that
 * would bring an undone edit back.
 */
export function migrateIptvCloudState(state: Record<string, unknown>, portals: readonly StalkerPortalEntry[]): Record<string, unknown> {
  const next = { ...state };
  for (const field of ["hiddenGroups", "lockedGroups", "groupOrder", "favoriteGroups"]) {
    if (Array.isArray(next[field])) next[field] = migrateLegacyStalkerKeys((next[field] as unknown[]).filter((key): key is string => typeof key === "string"));
  }
  if (Array.isArray(next.favoriteChannels)) {
    next.favoriteChannels = migrateLegacyStalkerChannelIds((next.favoriteChannels as unknown[]).filter((id): id is string => typeof id === "string"), portals);
  }
  const channelOrder = record(next.channelOrder);
  if (channelOrder) {
    next.channelOrder = Object.fromEntries(Object.entries(migrateLegacyStalkerKeyMap(channelOrder)).map(([key, refs]) =>
      [key, Array.isArray(refs) ? migrateLegacyStalkerChannelIds(refs.filter((ref): ref is string => typeof ref === "string"), portals) : refs]));
  }
  const groupNames = record(next.groupNames);
  if (groupNames) next.groupNames = migrateLegacyStalkerKeyMap(groupNames);
  const session = record(next.tvSession);
  if (session) {
    next.tvSession = {
      ...session,
      ...(typeof session.lastChannelId === "string" ? { lastChannelId: migrateLegacyStalkerChannelId(session.lastChannelId, portals) } : {}),
      ...(Array.isArray(session.recentChannelIds) ? { recentChannelIds: migrateLegacyStalkerChannelIds(
        session.recentChannelIds.filter((id): id is string => typeof id === "string"), portals) } : {}),
    };
  }
  return next;
}

const sameJson = (a: unknown, b: unknown) => JSON.stringify(a) === JSON.stringify(b);

/**
 * A web edit of the portal list merged into the list the cloud holds now, per
 * portal and per field: portals added or removed on another device since this
 * browser read the list survive, a portal removed here goes, and only the
 * fields changed here overwrite the other device's.
 */
export function mergeStalkerPortalEdits(remote: StalkerPortalEntry[], local: StalkerPortalEntry[], baseline: StalkerPortalEntry[]): StalkerPortalEntry[] {
  const baseById = new Map(baseline.map((portal) => [portal.id, portal]));
  const remoteById = new Map(remote.map((portal) => [portal.id, portal]));
  const localIds = new Set(local.map((portal) => portal.id));
  const merged: StalkerPortalEntry[] = [];
  for (const portal of local) {
    const before = baseById.get(portal.id);
    const now = remoteById.get(portal.id);
    if (!before) { merged.push(portal); continue; }
    // Removed on another device and not edited here: stays removed.
    if (!now) { if (!sameJson(portal, before)) merged.push(portal); continue; }
    const changed = Object.fromEntries(Object.entries(portal).filter(([field, value]) => !sameJson(value, before[field])));
    const dropped = Object.keys(before).filter((field) => !(field in portal));
    const result: StalkerPortalEntry = { ...now, ...changed };
    for (const field of dropped) delete result[field];
    merged.push(result);
  }
  for (const portal of remote) if (!baseById.has(portal.id) && !localIds.has(portal.id)) merged.push(portal);
  return merged;
}

export function migrateLegacyStalkerKeyMap<T>(map: Record<string, T>): Record<string, T> {
  const keys = Object.keys(map);
  if (!keys.some((key) => key.startsWith(`${LEGACY_STALKER_ID}|`))) return map;
  const next: Record<string, T> = {};
  // A key already written in the new shape wins over its legacy twin.
  for (const key of keys) if (!key.startsWith(`${LEGACY_STALKER_ID}|`)) next[key] = map[key];
  for (const key of keys) if (key.startsWith(`${LEGACY_STALKER_ID}|`)) next[migrateLegacyStalkerKey(key)] ??= map[key];
  return next;
}

/** Category-keyed settings with single-portal Stalker keys moved to Portal 1. */
export function migrateLegacyIptvKeys<T extends Partial<AppSettings>>(settings: T): T {
  const next: Partial<AppSettings> = { ...settings };
  if (Array.isArray(next.hiddenGroupIds)) next.hiddenGroupIds = migrateLegacyStalkerKeys(next.hiddenGroupIds);
  if (Array.isArray(next.lockedIptvGroupIds)) next.lockedIptvGroupIds = migrateLegacyStalkerKeys(next.lockedIptvGroupIds);
  if (Array.isArray(next.groupOrder)) next.groupOrder = migrateLegacyStalkerKeys(next.groupOrder);
  if (Array.isArray(next.favoriteGroupIds)) next.favoriteGroupIds = migrateLegacyStalkerKeys(next.favoriteGroupIds);
  if (next.iptvGroupNames) next.iptvGroupNames = migrateLegacyStalkerKeyMap(next.iptvGroupNames);
  const portals = stalkerPortalsOf({ iptvStalkerUrl: next.iptvStalkerUrl ?? "", iptvStalkerMac: next.iptvStalkerMac ?? "", iptvStalkerPortals: next.iptvStalkerPortals });
  if (next.iptvChannelOrder) {
    next.iptvChannelOrder = Object.fromEntries(Object.entries(migrateLegacyStalkerKeyMap(next.iptvChannelOrder))
      .map(([key, refs]) => [key, migrateLegacyStalkerChannelIds(refs, portals)]));
  }
  if (Array.isArray(next.favoriteChannelIds)) next.favoriteChannelIds = migrateLegacyStalkerChannelIds(next.favoriteChannelIds, portals);
  if (next.iptvTvSession) {
    next.iptvTvSession = { ...next.iptvTvSession, lastChannelId: migrateLegacyStalkerChannelId(next.iptvTvSession.lastChannelId ?? "", portals),
      recentChannelIds: migrateLegacyStalkerChannelIds(next.iptvTvSession.recentChannelIds ?? [], portals) };
  }
  return next as T;
}

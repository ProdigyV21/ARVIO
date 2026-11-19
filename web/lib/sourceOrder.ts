// Source priority as the Android app stores it (StreamIntegrationRepository):
//  - streamIntegrationsMacroOrder: "home_server,stremio_addons,plugins,telegram,iptv_vod"
//  - streamProvidersCustomOrder:   "homeserver:<id>,stremio:<addonId>,plugin_repo:<id>,telegram,iptv_playlist:<id>,…"
//  - streamMacroEnabledMap / streamProviderEnabledMap: on/off per integration or provider.
// Android lists the custom-order ids first, in that order, and appends every
// provider it does not find there. Without a custom order it sorts providers
// by the integration order.

import type { HomeServerConfig, InstalledAddon, IptvPlaylistEntry } from "./types";

export type IntegrationType = "home_server" | "stremio_addons" | "plugins" | "telegram" | "iptv_vod";
export const DEFAULT_INTEGRATION_ORDER: IntegrationType[] = ["home_server", "stremio_addons", "plugins", "telegram", "iptv_vod"];

export const INTEGRATION_LABELS: Record<IntegrationType, string> = {
  home_server: "Home server",
  stremio_addons: "Stremio addons",
  plugins: "Plugins",
  telegram: "Telegram",
  iptv_vod: "IPTV movies and series",
};

export interface SourceProvider { id: string; type: IntegrationType; label: string; detail?: string }

export function parseIdList(value: string | undefined): string[] {
  return [...new Set(String(value ?? "").split(",").map((id) => id.trim()).filter(Boolean))];
}

export function serializeIdList(ids: readonly string[]): string {
  return [...new Set(ids.map((id) => id.trim()).filter(Boolean))].join(",");
}

export function integrationOrder(value: string | undefined): IntegrationType[] {
  const parsed = parseIdList(value).filter((id): id is IntegrationType => (DEFAULT_INTEGRATION_ORDER as string[]).includes(id));
  return [...new Set([...parsed, ...DEFAULT_INTEGRATION_ORDER])];
}

export function providerType(id: string): IntegrationType | null {
  if (id.startsWith("homeserver:")) return "home_server";
  if (id.startsWith("stremio:")) return "stremio_addons";
  if (id.startsWith("plugin_repo:")) return "plugins";
  if (id === "telegram") return "telegram";
  if (id.startsWith("iptv_playlist:") || id.startsWith("iptv_stalker:")) return "iptv_vod";
  return null;
}

/** Same filter Android uses for the Stremio provider list: installed addons that serve streams. */
export function addonServesStreams(addon: InstalledAddon): boolean {
  const resources = Array.isArray(addon.resources) ? addon.resources : [];
  if (!resources.length) return true;
  return resources.some((resource) => {
    const name = typeof resource === "string" ? resource : resource?.name;
    return /^streams?$/i.test(String(name ?? ""));
  });
}

/** Providers the web knows about, in Android's raw order (before any custom order). */
export function knownProviders(input: {
  homeServers?: HomeServerConfig[];
  addons?: InstalledAddon[];
  pluginRepositories?: Array<{ id: string; name?: string }>;
  playlists?: IptvPlaylistEntry[];
  existingOrder?: string[];
}): SourceProvider[] {
  const list: SourceProvider[] = [];
  for (const server of input.homeServers ?? []) {
    list.push({ id: `homeserver:${server.id}`, type: "home_server", label: server.name || "Home server", detail: server.type });
  }
  for (const addon of (input.addons ?? []).filter(addonServesStreams)) {
    list.push({ id: `stremio:${addon.id}`, type: "stremio_addons", label: addon.name || addon.id, detail: addon.version ? `v${addon.version}` : undefined });
  }
  for (const repo of input.pluginRepositories ?? []) {
    if (repo?.id) list.push({ id: `plugin_repo:${repo.id}`, type: "plugins", label: repo.name || repo.id });
  }
  list.push({ id: "telegram", type: "telegram", label: "Telegram" });
  // Android lists a playlist for VOD unless both movie and series import are off.
  for (const playlist of (input.playlists ?? []).filter((item) => item.importVod !== false || item.importSeries !== false)) {
    list.push({ id: `iptv_playlist:${playlist.id}`, type: "iptv_vod", label: playlist.name || "IPTV playlist" });
  }
  // Providers that only exist on the TV (a Stalker portal, a plugin repository
  // the cloud does not describe) stay in the list under their id.
  const known = new Set(list.map((item) => item.id));
  for (const id of input.existingOrder ?? []) {
    const type = providerType(id);
    if (!known.has(id) && type) {
      list.push({ id, type, label: id.replace(/^[a-z_]+:/, "") || id, detail: "On your TV" });
      known.add(id);
    }
  }
  return list;
}

/** The order Android shows for these providers right now. */
export function effectiveProviderOrder(providers: SourceProvider[], customOrder: string | undefined, macroOrder: string | undefined): SourceProvider[] {
  const custom = parseIdList(customOrder);
  if (custom.length) {
    const byId = new Map(providers.map((item) => [item.id, item]));
    const listed = custom.map((id) => byId.get(id)).filter((item): item is SourceProvider => Boolean(item));
    const listedIds = new Set(listed.map((item) => item.id));
    return [...listed, ...providers.filter((item) => !listedIds.has(item.id))];
  }
  const macro = integrationOrder(macroOrder);
  return providers
    .map((item, index) => ({ item, index }))
    .sort((a, b) => macro.indexOf(a.item.type) - macro.indexOf(b.item.type) || a.index - b.index)
    .map((entry) => entry.item);
}

/**
 * Integration order changed. Android clears its custom provider order when
 * this happens on the TV, but a blank value from the cloud is ignored there,
 * so the custom order is re-sorted by the new integration order instead.
 */
export function resortProviderOrder(customOrder: string | undefined, macroOrder: IntegrationType[]): string | undefined {
  const custom = parseIdList(customOrder);
  if (!custom.length) return customOrder;
  const rank = (id: string) => {
    const type = providerType(id);
    return type ? macroOrder.indexOf(type) : macroOrder.length;
  };
  return serializeIdList(custom.map((id, index) => ({ id, index })).sort((a, b) => rank(a.id) - rank(b.id) || a.index - b.index).map((entry) => entry.id));
}

/**
 * Addons were reordered. Put the Stremio providers in the new addon order
 * while every other provider keeps its slot, so the TV's source priority
 * follows the web's addon order.
 */
export function providerOrderForAddons(providers: SourceProvider[], customOrder: string | undefined, macroOrder: string | undefined, addonIds: string[]): string {
  const current = effectiveProviderOrder(providers, customOrder, macroOrder).map((item) => item.id);
  const wanted = addonIds.map((id) => `stremio:${id}`).filter((id) => current.includes(id));
  const slots = new Set(wanted);
  let index = 0;
  return serializeIdList(current.map((id) => slots.has(id) ? wanted[index++] : id));
}

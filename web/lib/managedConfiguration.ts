import type { AppSettings, CatalogConfig, HomeServerConfig, InstalledAddon, IptvPlaylistEntry } from "./types";
import type { PartnerConfiguration, PartnerPermissions, PartnerIptvOptions } from "./partnerManagement";

export type ManagedService = { id: string; name: string; kind: "plex" | "emby" | "jellyfin" | "addon" | "iptv"; url: string; credentialMode: "assigned" | "customer"; credentials: Record<string, string> | null; iptv?: PartnerIptvOptions };
export type ManagedDelivery = {
  managed: boolean; active?: boolean; name?: string; reason?: string; organizationId?: string; customerId?: string;
  revision?: number; leaseUntil?: string; config?: PartnerConfiguration; permissions?: PartnerPermissions; services?: ManagedService[];
};
export type ManagedResources = {
  delivery: ManagedDelivery; catalogs: CatalogConfig[]; addons: InstalledAddon[];
  homeServers: HomeServerConfig[]; playlists: IptvPlaylistEntry[];
};
export const managedId = (organizationId: string, id: string) => `partner-${organizationId}-${id}`;
export const isManagedId = (id: string) => id.startsWith("partner-");

const settingsMap = { trailerSoundEnabled: "trailerSound", spoilerBlurEnabled: "spoilerBlur" } as const;
const allowedSettings = new Set([
  "netflixHomeStyle", "watchlistOnHome", "trailerAutoPlay", "trailerInCards", "trailerSound", "autoPlayNext", "autoPlaySingleSource",
  "showBudget", "showEpisodeRatings", "iptvFavoritesOnHome", "spoilerBlur", "includeSpecials", "cardLayoutMode", "clockFormat", "streamSearchMode", "animeEpisodeStructuring"
]);
export function managedSettings(config: PartnerConfiguration): Partial<AppSettings> {
  return Object.fromEntries(Object.entries(config.settings).flatMap(([key, value]) => {
    const field = settingsMap[key as keyof typeof settingsMap] || key;
    return allowedSettings.has(field) ? [[field, value]] : [];
  })) as Partial<AppSettings>;
}

const appearance = new Set(["netflixHomeStyle", "watchlistOnHome", "cardLayoutMode", "oledBlack", "clockFormat", "showBudget", "showEpisodeRatings", "iptvFavoritesOnHome", "smoothScrolling", "spoilerBlur", "accentColor", "cardDensity", "guideRowCount", "catalogueRowLayoutModes"]);
const catalogs = new Set(["catalogs", "hiddenCatalogIds", "hiddenAddonCatalogIds", "hiddenHomeServerCatalogIds"]);
const connections = new Set(["homeServers", "iptvPlaylists", "iptvStalkerUrl", "iptvStalkerMac", "iptvStalkerPortals", "disabledAddonIds", "torrServerBaseUrl", "streamProvidersCustomOrder", "streamIntegrationsMacroOrder", "streamMacroEnabledMap", "streamProviderEnabledMap"]);
const metadata = new Set(["customTmdbApiKey", "customTvdbApiKey", "customTvdbUserPin", "metadataMovieProviders", "metadataTvProviders", "metadataAnimeProviders", "animeEpisodeStructuring"]);
const personalOnly = new Set(["language", "defaultSubtitle", "secondarySubtitle", "audioLanguage", "skipProfileSelection", "deviceModeOverride", "favoriteChannelIds", "iptvTvSession", "favoriteGroupIds", "hiddenGroupIds", "lockedIptvGroupIds", "groupOrder", "iptvChannelOrder", "iptvGroupNames", "iptvSortOrder", "dnsProvider", "customUserAgent", "showLoadingStats"]);
export function managedPermissionFor(key: keyof AppSettings): keyof PartnerPermissions | null {
  if (appearance.has(key)) return "editAppearance";
  if (catalogs.has(key)) return "editCatalogs";
  if (connections.has(key)) return "addConnections";
  if (metadata.has(key)) return "editMetadata";
  return personalOnly.has(key) ? null : "editPlayback";
}
export function assertManagedEdit(patch: Partial<AppSettings>, delivery?: ManagedDelivery | null) {
  if (!delivery?.managed || !delivery.active) return;
  for (const field of Object.keys(patch) as (keyof AppSettings)[]) {
    const permission = managedPermissionFor(field);
    if (permission && !delivery.permissions?.[permission]) throw new Error(`This preference is managed by ${delivery.name || "your administrator"}.`);
  }
}

export function overlayManagedSettings(personal: AppSettings, resources: ManagedResources | null, overrides: Partial<AppSettings> = {}): AppSettings {
  const delivery = resources?.delivery;
  if (!resources || !delivery?.managed || !delivery.active || !delivery.config) return personal;
  const preferences = managedSettings(delivery.config);
  for (const key of Object.keys(overrides) as (keyof AppSettings)[]) {
    const permission = managedPermissionFor(key);
    if (allowedSettings.has(key) && (!permission || delivery.permissions?.[permission])) (preferences as Record<string, unknown>)[key] = overrides[key];
  }
  const templateHasCatalogs = delivery.config.catalogs.length > 0 || delivery.config.collectionUrls.length > 0;
  return {
    ...personal, ...preferences,
    catalogs: templateHasCatalogs ? [...resources.catalogs, ...(delivery.permissions?.editCatalogs ? personal.catalogs.filter(c => !c.isPreinstalled && !isManagedId(c.id)) : [])] : personal.catalogs,
    hiddenCatalogIds: delivery.permissions?.editCatalogs ? personal.hiddenCatalogIds : personal.hiddenCatalogIds.filter(id => !isManagedId(id)),
    homeServers: [...personal.homeServers.filter(s => !isManagedId(s.id)), ...resources.homeServers],
    iptvPlaylists: [...personal.iptvPlaylists.filter(p => !isManagedId(p.id)), ...resources.playlists]
  };
}

// Only the customer's own arrays may enter personal/cloud storage. A settings
// page can submit a whole effective array when it edits one unrelated item.
export function personalManagedPatch(patch: Partial<AppSettings>): Partial<AppSettings> {
  return { ...patch,
    ...(patch.homeServers ? { homeServers: patch.homeServers.filter(s => !isManagedId(s.id)) } : {}),
    ...(patch.iptvPlaylists ? { iptvPlaylists: patch.iptvPlaylists.filter(p => !isManagedId(p.id)) } : {}),
    ...(patch.catalogs ? { catalogs: patch.catalogs.filter(c => !isManagedId(c.id)) } : {})
  };
}

export async function resolveManagedResources(delivery: ManagedDelivery, adapters: {
  addon: (url: string) => Promise<InstalledAddon>;
  collection: (url: string) => Promise<CatalogConfig[]>;
}): Promise<ManagedResources> {
  if (!delivery.managed || !delivery.active || !delivery.organizationId || !delivery.config || !delivery.permissions) throw new Error("No active managed configuration");
  const resources: ManagedResources = { delivery, catalogs: [], addons: [], homeServers: [], playlists: [] };
  const key = (id: string) => managedId(delivery.organizationId!, id);
  const collections: CatalogConfig[][] = [];
  const addons: InstalledAddon[] = [];
  const addonAliases: string[] = [];
  const tasks: (() => Promise<void>)[] = delivery.config.collectionUrls.map((url, index) => async () => {
    const catalogs = await adapters.collection(url);
    collections[index] = catalogs.map(catalog => ({ ...catalog, id: key(catalog.id) }));
  });
  resources.catalogs.push(...delivery.config.catalogs.map(catalog => ({ ...catalog, id: key(String(catalog.id)), name: String(catalog.name || catalog.title), enabled: catalog.enabled !== false } as CatalogConfig)));
  for (const [index, service] of (delivery.services || []).entries()) {
    const credentials = service.credentials || {};
    const id = key(service.id);
    if (service.kind === "addon") {
      tasks.push(async () => {
        const addon = await adapters.addon(credentials.manifestUrl || service.url);
        addonAliases[index] = addon.id;
        addons[index] = { ...addon, id, managedOrganizationId: delivery.organizationId };
      });
    } else if (service.kind === "iptv") {
      const endpoint = new URL(service.url);
      const isPlaylist = service.iptv?.mode === "m3u" || /\.(?:m3u8?)$/i.test(endpoint.pathname) || endpoint.pathname.endsWith("/get.php");
      let playlist = credentials.playlistUrl || (isPlaylist ? endpoint.href : "");
      let epgUrl: string | undefined;
      if (!playlist && credentials.username && credentials.password) {
        const url = new URL(service.url);
        url.pathname = `${url.pathname.replace(/\/(?:player_api\.php|get\.php)\/?$/, "").replace(/\/$/, "")}/get.php`;
        url.search = new URLSearchParams({ username: credentials.username, password: credentials.password, type: "m3u_plus", output: "ts" }).toString();
        playlist = url.href;
        url.pathname = url.pathname.replace(/get\.php$/, "xmltv.php");
        url.search = new URLSearchParams({ username: credentials.username, password: credentials.password }).toString();
        epgUrl = url.href;
      }
      if (playlist) {
        const parsed = new URL(playlist);
        if (!epgUrl && parsed.pathname.endsWith('/get.php') && parsed.searchParams.has('username') && parsed.searchParams.has('password')) {
          parsed.pathname = parsed.pathname.replace(/get\.php$/, 'xmltv.php');
          parsed.searchParams.delete('type'); parsed.searchParams.delete('output');
          epgUrl = parsed.href;
        }
        resources.playlists.push({ id, name: service.name, m3uUrl: playlist,
          epgUrl: service.iptv?.epg === false ? undefined : credentials.epgUrl || epgUrl,
          enabled: true, importLiveTv: service.iptv?.liveTv !== false,
          importVod: service.iptv?.movies !== false, importSeries: service.iptv?.series !== false,
          enableEpg: service.iptv?.epg !== false, enableCatchup: service.iptv?.catchup !== false });
      }
    } else if (credentials.token || credentials.username && credentials.password) {
      resources.homeServers.push({ id, name: service.name, type: service.kind, url: service.url, enabled: true,
        token: credentials.token, userId: credentials.userId, username: credentials.username, password: credentials.password });
    }
  }
  // Bounded fan-out; no retry storms against customers' providers.
  let next = 0;
  await Promise.all(Array.from({ length: Math.min(4, tasks.length) }, async () => {
    while (next < tasks.length) await tasks[next++]();
  }));
  resources.addons = addons.filter(Boolean);
  const addonIds = new Map<string, string>();
  const serviceIds = new Set((delivery.services || []).map(service => service.id));
  addons.forEach((addon, index) => { if (addon && !addonIds.has(addonAliases[index])) addonIds.set(addonAliases[index], addon.id); });
  const addonId = (id: string | null | undefined) => id ? addonIds.get(id) || (serviceIds.has(id) ? key(id) : id) : id;
  const byId = new Map([...resources.catalogs, ...collections.flat()].map(c => [c.id, {
    ...c, addonId: addonId(c.addonId),
    // Personal copies of the same collection feed must not merge with a
    // managed rail or use the customer's unrelated addon installation.
    ...(c.packId ? { packId: key(c.packId) } : {}),
    ...(c.collectionRailKey ? { collectionRailKey: key(c.collectionRailKey) } : {}),
    ...(c.collectionSources ? { collectionSources: c.collectionSources.map(source => ({ ...source,
      addonId: addonId(source.addonId),
      ...(source.homeServerId && serviceIds.has(source.homeServerId) ? { homeServerId: key(source.homeServerId) } : {})
    })) } : {})
  }]));
  resources.catalogs = [...byId.values()];
  return resources;
}

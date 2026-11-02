import { liveTvStalkerPortals } from "./stalkerPortals";
import type { AppSettings, InstalledAddon } from "./types";

export function addonProvidesStreams(addon: Pick<InstalledAddon, "enabled" | "resources">) {
  return addon.enabled !== false && Array.isArray(addon.resources) && addon.resources.some((resource) =>
    (typeof resource === "string" ? resource : resource?.name) === "stream"
  );
}

// Catalog/subtitle addons can fill Home without supplying playable sources.
// These counts describe configuration, not a guarantee of provider availability.
export function sourceSetupState(addons: InstalledAddon[], settings: Pick<AppSettings, "homeServers" | "iptvPlaylists"> & Partial<Pick<AppSettings, "iptvStalkerUrl" | "iptvStalkerMac" | "iptvStalkerPortals">>) {
  const streamAddons = addons.filter(addonProvidesStreams).length;
  const homeServers = settings.homeServers.filter(server => server.enabled && server.url.trim()).length;
  const enabledPlaylists = settings.iptvPlaylists.filter(playlist => playlist.enabled && playlist.m3uUrl.trim());
  const liveTvPlaylists = enabledPlaylists.filter(playlist => playlist.importLiveTv !== false).length
    + liveTvStalkerPortals({ iptvStalkerUrl: settings.iptvStalkerUrl ?? "", iptvStalkerMac: settings.iptvStalkerMac ?? "", iptvStalkerPortals: settings.iptvStalkerPortals }).length;
  const hasIptvVod = enabledPlaylists.some(playlist =>
    (playlist.importVod !== false || playlist.importSeries !== false) && [playlist.m3uUrl, playlist.epgUrl].some(value => {
      try {
        const url = new URL(value || "");
        return /^https?:$/.test(url.protocol) && !!url.searchParams.get("username") && !!url.searchParams.get("password");
      } catch { return false; }
    })
  );
  return {
    streamAddons,
    homeServers,
    liveTvPlaylists,
    hasOnDemand: streamAddons + homeServers > 0 || hasIptvVod,
    hasAny: streamAddons + homeServers + liveTvPlaylists > 0 || hasIptvVod,
    catalogOnly: addons.some(addon => addon.enabled !== false) && streamAddons === 0
  };
}

export type SourceSettingsSection = "homeserver" | "tv";
export const SOURCE_SETTINGS_EVENT = "arvio:source-settings";
let requestedSection: SourceSettingsSection | null = null;

// Read separately from clearing so repeated React renders retain the target.
export function requestSourceSettings(section: SourceSettingsSection) {
  requestedSection = section;
  if (typeof window !== "undefined") window.dispatchEvent(new CustomEvent(SOURCE_SETTINGS_EVENT, { detail: section }));
}
export function requestedSourceSettings() { return requestedSection; }
export function clearSourceSettingsRequest() { requestedSection = null; }

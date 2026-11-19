"use client";
// Dev-only fixture for the Settings layout editors: a 55,000-channel playlist,
// addons, Home rows and home servers, without an account or network.

import { useCallback, useMemo, useState } from "react";
import { TopNav } from "@/components/shell/TopNav";
import { SettingsScreen } from "@/components/settings/SettingsScreen";
import { AppContext, defaultSettings, type AppStore } from "@/lib/store";
import { iptvPlaylistSignature } from "@/lib/iptv";
import { stalkerSourceSignature } from "@/lib/stalkerPortals";
import type { AppSettings, InstalledAddon, IptvChannel } from "@/lib/types";

const names = ["NPO 1", "NPO 2", "NPO 3", "RTL 4", "RTL 5", "SBS 6", "Veronica", "Net5", "Ziggo Sport", "ESPN 1", "BBC One", "Discovery"];
const groupNames = ["Netherlands", "Sports", "News", "Movies", "Kids", "Documentary", "Music", "United Kingdom", "Germany", "France"];
const channels: IptvChannel[] = Array.from({ length: 55_000 }, (_, i) => ({
  id: `fixture:${i}`, cloudId: `fixture:m3u-${i}`,
  name: i < 12 ? names[i] : `${groupNames[i % 10]} ${i + 1}`,
  group: i < 12 ? "Netherlands" : i < 2000 ? groupNames[i % 10] : `International ${Math.floor(i / 250)}`,
  number: String(i + 1), streamUrl: "https://example.invalid/fixture.m3u8", tvgId: `fixture-${i}`,
}));
const second: IptvChannel[] = Array.from({ length: 40 }, (_, i) => ({ id: `backup:${i}`, name: `Backup ${i + 1}`, group: i % 2 ? "Sports" : "News", streamUrl: "https://example.invalid/b.m3u8" }));
const portalChannels: IptvChannel[] = Array.from({ length: 30 }, (_, i) => ({ id: `stalker:stalker1:${i}`, name: `Portal ${i + 1}`, group: i % 3 ? "Sport" : "News", streamUrl: "https://example.invalid/p.m3u8" }));
const all = [...channels, ...second, ...portalChannels];

const addon = (id: string, name: string, resources: InstalledAddon["resources"], catalogs = 0): InstalledAddon => ({
  id, name, version: "1.2.0", manifestUrl: `https://${id}.example/manifest.json`, resources,
  catalogs: Array.from({ length: catalogs }, (_, i) => ({ type: "movie", id: `${id}-${i}`, name: `${name} ${i}` })),
});
const fixtureAddons = [
  addon("torrentio", "Torrentio", ["stream"]),
  addon("cinemeta", "Cinemeta", ["catalog", "meta"], 4),
  addon("opensubtitles", "OpenSubtitles v3", ["subtitles"]),
  addon("mediafusion", "MediaFusion", ["stream", "catalog"], 2),
];

const noop = () => {};
export function SettingsLayoutFixture() {
  const [settings, setSettings] = useState<AppSettings>(() => ({
    ...defaultSettings,
    iptvPlaylists: [
      { id: "fixture", name: "Main playlist", enabled: true, m3uUrl: "https://example.invalid/list.m3u", importVod: false },
      { id: "backup", name: "Backup", enabled: true, m3uUrl: "https://example.invalid/backup.m3u" },
    ],
    favoriteChannelIds: ["fixture:m3u-8", "fixture:m3u-0", "fixture:missing"],
    favoriteGroupIds: ["Sports"],
    hiddenGroupIds: ["fixture|Music"],
    lockedIptvGroupIds: ["fixture|Kids"],
    groupOrder: ["fixture|Sports", "fixture|Netherlands"],
    iptvChannelOrder: { "fixture|Netherlands": ["fixture:m3u-8"] },
    iptvGroupNames: { "fixture|Sports": "Football and more", "stalker1|Sport": "Live sport" },
    iptvStalkerPortals: [
      { id: "stalker1", name: "Home portal", portalUrl: "http://portal.example/stalker_portal/c", macAddress: "00:1A:79:12:34:56", enabled: true, importVod: false, androidOnly: "kept" },
      { id: "stalker2", name: "Holiday portal", portalUrl: "http://other.example:8080/c", macAddress: "00:1A:79:AB:CD:EF", enabled: false },
    ],
    homeServers: [
      { id: "hs1", type: "jellyfin", name: "Living room Jellyfin", url: "https://jelly.example:8096", token: "t", enabled: true,
        collections: [{ id: "m", name: "Movies", type: "movies", enabled: true }, { id: "s", name: "Shows", type: "tvshows", enabled: true }, { id: "k", name: "Kids", type: "movies", enabled: false }] },
      { id: "hs2", type: "plex", name: "Office Plex", url: "https://plex.example:32400", token: "p", enabled: false },
    ],
    androidPluginRepositories: [{ id: "repo1", name: "Community scrapers", enabled: true }],
    streamIntegrationsMacroOrder: "stremio_addons,home_server,plugins,telegram,iptv_vod",
  }));
  const [addons, setAddons] = useState(fixtureAddons);
  const updateSettings = useCallback((patch: Partial<AppSettings>) => setSettings((old) => ({ ...old, ...patch })), []);
  const snapshot = useMemo(() => ({ allChannels: all, channels: all, grouped: {}, nowNext: {}, identitiesLoaded: true,
    favoriteChannels: settings.favoriteChannelIds, favoriteGroups: [], hiddenGroups: [], groupOrder: [],
    loadedAt: Date.now(), signature: iptvPlaylistSignature(settings.iptvPlaylists) + stalkerSourceSignature(settings), scopeKey: "local:fixture" }), [settings]);
  const app = {
    settings, setSettings, updateSettings, iptvSnapshot: snapshot, addons,
    setAddonsState: async (next: InstalledAddon[]) => setAddons(next),
    installAddon: async () => {}, removeAddon: async (a: InstalledAddon) => setAddons((list) => list.filter((x) => x.id !== a.id)),
    view: "app", section: "settings", setSection: noop, switchProfile: noop, avatarImages: {}, closeDetails: noop, selected: null,
    setToast: (message: string | null) => { if (message) console.info("toast:", message); }, refreshIptv: async () => {}, refreshData: async () => {},
    busy: "", auth: null, settingsSyncState: "idle", traktConnected: false, simklConnected: false, mdblistConnected: false,
    profiles: [{ id: "fixture", name: "Test profile" }], activeProfile: { id: "fixture", name: "Test profile" },
  } as unknown as AppStore;
  return <AppContext.Provider value={app}>
    <main className="app-shell"><TopNav /><section className="content"><SettingsScreen /></section></main>
  </AppContext.Provider>;
}

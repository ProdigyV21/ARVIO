"use client";

import { useCallback, useMemo, useState } from "react";
import { TopNav } from "@/components/shell/TopNav";
import { AppContext, defaultSettings, type AppStore } from "@/lib/store";
import { LiveTvScreen } from "@/components/livetv/LiveTvScreen";
import { iptvPlaylistSignature } from "@/lib/iptv";
import { stalkerSourceSignature } from "@/lib/stalkerPortals";
import { recordTvPlayback } from "@/lib/iptvSession";
import type { AppSettings, IptvChannel, IptvNowNext } from "@/lib/types";

const names = ["ESPN", "NPO 1", "NPO 2", "NPO 3", "Ziggo Sport", "RTL 4", "BBC One", "BBC Two", "Discovery", "National Geographic", "Sky Sports", "Eurosport"];
const channels: IptvChannel[] = Array.from({ length: 55_000 }, (_, i) => ({
  id: `fixture:xtream:${i}`, cloudId: `fixture:m3u:exact-${i}`, name: i < 12 ? names[i] : `${names[i % 12]} ${i + 1}`,
  group: i < 12 ? "Netherlands" : `International sports and entertainment ${Math.floor(i / 200)}`,
  number: String(i + 1), streamUrl: "https://example.invalid/fixture.m3u8", tvgId: `fixture-${i}`
}));
// Two Stalker portals, each its own source with its own categories.
const portalChannels: IptvChannel[] = ["stalker1", "stalker2"].flatMap((portal, p) => Array.from({ length: 12 }, (_, i) => ({
  id: `stalker:${portal}:${i}`, name: `${p ? "Holiday" : "Home"} ${i % 2 ? "Sport" : "News"} ${i + 1}`, group: i % 2 ? "Sport" : "News",
  number: String(9000 + p * 100 + i), streamUrl: "https://example.invalid/portal.m3u8"
})));
const allChannels = [...channels, ...portalChannels];
const noop = () => {};
export function TvSyncFixture() {
  const [settings, setSettings] = useState<AppSettings>({ ...defaultSettings,
    iptvPlaylists: [{ id: "fixture", name: "Reference playlist", enabled: true, m3uUrl: "https://example.invalid/list.m3u" }],
    favoriteChannelIds: channels.slice(1, 4).map(c => c.cloudId!),
    iptvTvSession: { lastChannelId: channels[0].cloudId!, lastGroupName: "recent", lastFocusedZone: "GUIDE", lastOpenedAt: 1,
      recentChannelIds: channels.slice(0, 12).reverse().map(c => c.cloudId!) },
    iptvStalkerPortals: [
      { id: "stalker1", name: "Home portal", portalUrl: "http://portal.example/c", macAddress: "00:1A:79:12:34:56", enabled: true },
      { id: "stalker2", name: "Holiday portal", portalUrl: "http://other.example/c", macAddress: "00:1A:79:AB:CD:EF", enabled: true },
    ],
    iptvGroupNames: { "fixture|Netherlands": "Dutch TV", "stalker2|Sport": "Live sport" }
  });
  const updateSettings = useCallback((patch: Partial<AppSettings>) => setSettings(old => ({ ...old, ...patch })), []);
  const [nowNext, setNowNext] = useState<Record<string, IptvNowNext>>({});
  const loadIptvGuide = useCallback(async (rows: IptvChannel[]) => {
    const start = Math.floor(Date.now() / 3_600_000) * 3_600_000;
    const titles = ["Eredivisie Highlights", "NOS Journaal", "BinnensteBuiten", "Checkpoint", "Grand Prix Review", "RTL Nieuws"];
    setNowNext(old => ({ ...old, ...Object.fromEntries(rows.map((channel, index) => {
      const programs = Array.from({ length: 5 }, (_, i) => ({ title: titles[(index + i) % titles.length],
        description: "The latest news, highlights and analysis from today's programme.", startUtcMillis: start + i * 3_600_000, endUtcMillis: start + (i + 1) * 3_600_000 }));
      return [channel.id, { now: programs[0], next: programs[1], upcoming: programs.slice(1), recent: [] }];
    })) }));
  }, []);
  const record = useCallback((channel: IptvChannel) => setSettings(old => ({ ...old, iptvTvSession: recordTvPlayback(old.iptvTvSession, channel) })), []);
  const snapshot = useMemo(() => ({ allChannels, channels: allChannels, grouped: {}, nowNext, identitiesLoaded: true,
    favoriteChannels: settings.favoriteChannelIds, favoriteGroups: [], hiddenGroups: [], groupOrder: [],
    loadedAt: Date.now(), signature: iptvPlaylistSignature(settings.iptvPlaylists) + stalkerSourceSignature(settings) }), [nowNext, settings]);
  const app = { settings, setSettings, updateSettings, iptvSnapshot: snapshot, playChannel: record, recordChannelPlayback: record,
    view: "app", section: "tv", setSection: noop, switchProfile: noop, avatarImages: [], closeDetails: noop, selected: null,
    playCatchup: noop, setToast: noop, refreshIptv: async () => {}, loadIptvGuide, busy: "", auth: null,
    activeProfile: { id: "tv-sync-fixture", name: "Test profile" }, addons: [] } as unknown as AppStore;
  return <AppContext.Provider value={app}>
    <main className="app-shell"><TopNav /><section className="content"><LiveTvScreen /></section></main>
  </AppContext.Provider>;
}

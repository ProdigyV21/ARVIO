"use client";

import { useCallback, useEffect, useMemo, useState } from "react";
import { Home, Library, Tv, Settings, LayoutGrid } from "lucide-react";
import { AppContext, defaultSettings, type AppStore } from "@/lib/store";
import { LiveTvScreen } from "@/components/livetv/LiveTvScreen";
import { WatchlistScreen } from "@/components/watchlist/WatchlistScreen";
import { MediaCard } from "@/components/media/MediaCard";
import { SettingsScreen } from "@/components/settings/SettingsScreen";
import { PlayerOverlay } from "@/components/player/PlayerOverlay";
import { PaywallScreen } from "@/components/shell/Paywall";
import { iptvPlaylistSignature } from "@/lib/iptv";
import { NoAddonsPrompt } from "@/components/shell/NoAddonsPrompt";
import type { AppSettings, IptvChannel, IptvNowNext, MediaItem, StreamSource } from "@/lib/types";

const noop = () => {};
const empty = async () => [];
const names = ["BBC One", "BBC Two", "ITV", "Channel 4", "National Geographic", "Eurosport", "Discovery", "Sky Arts"];
const groups = ["Entertainment", "Documentaries", "Sports", "News", "Cinema", "Kids", "Music", "International"];
const programs = ["The Evening Report", "Wild Coast", "Championship Live", "Inside the Studio", "The Long Journey", "World Stories"];
const channels: IptvChannel[] = Array.from({ length: 55_000 }, (_, i) => ({
  id: `fixture:${i}`, name: i < 8 ? names[i] : `${names[i % names.length]} ${i + 1}`, group: `${groups[Math.floor(i / 100) % groups.length]} ${Math.floor(i / 100) + 1}`,
  number: String(i + 1), streamUrl: "https://example.invalid/fixture.m3u8", tvgId: `fixture-${i}`, logo: "", catchupDays: 0
}));
const posters = ["/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg", "/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "/5KCVkau1HEl7ZzfPsKAPM0sMiKc.jpg"];
const titles = ["Dune: Part Two", "The Dark Knight", "Interstellar", "The Shawshank Redemption"];
const media: MediaItem[] = Array.from({ length: 24 }, (_, i) => ({ id: -i - 1, mediaType: "movie", title: titles[i % 4], year: "2024", image: `https://image.tmdb.org/t/p/w500${posters[i % 4]}`, backdrop: `https://image.tmdb.org/t/p/w780${posters[i % 4]}`, rating: "8.4", overview: "Controlled test data", activityAt: 100 - i }));

export function StabilizationFixture() {
  const [ready, setReady] = useState(false);
  useEffect(() => setReady(true), []);
  const [page, setPage] = useState("tv");
  const [showOnboarding, setShowOnboarding] = useState(false);
  const [toast, setToast] = useState("");
  const [failLibrary, setFailLibrary] = useState(false);
  const [activeStream, setActiveStream] = useState<StreamSource | null>(null);
  const [settings, setSettings] = useState<AppSettings>({ ...defaultSettings, cardLayoutMode: "poster", iptvPlaylists: [{ id: "fixture", name: "Reference playlist", enabled: true, m3uUrl: "https://example.invalid/playlist.m3u" }], favoriteChannelIds: channels.slice(0, 8).map((ch) => ch.id) });
  const [nowNext, setNowNext] = useState<Record<string, IptvNowNext>>({});
  const loadIptvGuide = useCallback(async (rows: IptvChannel[]) => {
    const start = Math.floor(Date.now() / 3_600_000) * 3_600_000;
    setNowNext((old) => {
      const next = { ...old };
      for (const ch of rows) {
        const entries = Array.from({ length: 5 }, (_, i) => ({ channelId: ch.id, title: programs[(Number(ch.number) + i) % programs.length], startUtcMillis: start + i * 3_600_000, endUtcMillis: start + (i + 1) * 3_600_000 }));
        next[ch.id] = { now: entries[0], next: entries[1], upcoming: entries.slice(1), recent: [] };
      }
      return next;
    });
  }, []);
  const loadTrackerLibrary = useCallback(async (_provider: string, source: string) => {
    if (failLibrary) throw new Error("Test service unavailable. Your cached library is still here.");
    return source === "dropped" ? [] : media;
  }, [failLibrary]);
  const loadTraktLists = useCallback(async () => [{ id: "curated", name: "Weekend watch" }], []);
  const iptvSnapshot = useMemo(() => ({ channels, allChannels: channels, grouped: {}, nowNext, groupOrder: [], favoriteGroups: [], hiddenGroups: [], favoriteChannels: settings.favoriteChannelIds, signature: iptvPlaylistSignature(settings.iptvPlaylists), loadedAt: Date.now() }), [nowNext, settings]);
  const app = {
    view: "app", section: page === "onboarding" ? "home" : page, addonsReady: true, closeDetails: noop,
    settings, setSettings, updateSettings: (patch: object) => setSettings((old) => ({ ...old, ...patch })),
    iptvSnapshot, loadIptvGuide, refreshIptv: async () => {}, busy: "", auth: null, activeProfile: { id: "fixture", name: "Test profile" },
    profiles: [], addons: [], watchlist: media, continueWatching: media.slice(0, 4), traktConnected: true, simklConnected: true, mdblistConnected: false,
    openDetails: (item: MediaItem) => setToast(`Selected: ${item.title}`), openContextMenu: noop, isWatched: () => false,
    loadTrackerLibrary, loadTraktLists, loadTraktListItems: async () => media,
    playChannel: (channel: IptvChannel) => setToast(`Selected: ${channel.name}`), playCatchup: noop, setToast,
    trackingPreferences: { watchlistReadMode: "trakt", continueWatchingReadMode: "both", watchedReadMode: "both", writeToTrakt: true, writeToSimkl: true },
    settingsSyncState: "local", saveTrackingPreferences: noop, setSection: setPage, signOut: noop, refreshData: empty,
    homeServerRows: [], categories: [], catalogConfigs: [], selected: null, streams: [], activeStream, activeChannel: null, selectedEpisode: null,
    playStream: setActiveStream, closePlayer: () => setActiveStream(null), advanceEpisode: async () => false,
  } as unknown as AppStore;
  return <AppContext.Provider value={app}>
    <div data-fixture-ready={ready} style={{ maxWidth: 1600, margin: "auto", padding: "18px 20px" }}>
      <nav className="fixture-nav" aria-label="Test navigation"><img src="/arvio-wordmark.svg" alt="ARVIO" width={130} />
        {[{ id: "home", label: "Home", icon: Home }, { id: "library", label: "Library", icon: Library }, { id: "tv", label: "Live TV", icon: Tv }, { id: "settings", label: "Settings", icon: Settings }].map(({ id, label, icon: Icon }) => <button className={page === id ? "primary" : "secondary"} key={id} onClick={() => setPage(id)}><Icon size={18} />{label}</button>)}
        <button className="secondary" onClick={() => setPage("premium")}>Premium</button><span>Test data</span>
      </nav>
      <div className="fixture-tools"><button className="secondary" onClick={() => setSettings((old) => ({ ...old, cardLayoutMode: old.cardLayoutMode === "poster" ? "landscape" : "poster" }))}><LayoutGrid size={16} />Card layout</button><label><input type="checkbox" checked={failLibrary} onChange={(event) => setFailLibrary(event.target.checked)} /> Simulate tracker outage</label><button className="secondary" onClick={() => setActiveStream({ source: "CC0 playback sample", addonName: "Test fixture", quality: "HD", size: "", url: "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4" })}>Play sample</button></div>
      {page === "premium" ? <PaywallScreen state={null} accountId={null} isSignedIn={false} onEntitled={noop} onConnect={() => setToast("Cloud connection requested (test only)")} onSignOut={noop} /> : page === "tv" ? <LiveTvScreen /> : page === "library" ? <WatchlistScreen /> : page === "settings" ? <SettingsScreen /> : <section className="screen"><h2>Continue Watching</h2><div className="grid-results">{media.slice(0, 8).map((item) => <MediaCard key={item.id} item={item} onOpen={app.openDetails} />)}</div></section>}
      {toast && <div role="status" className="fixture-toast" onClick={() => setToast("")}>{toast}</div>}
      <PlayerOverlay />
      <div className="fixture-tools">
        <button onClick={() => { setSettings((old) => ({ ...old, homeServers: (["plex", "jellyfin", "emby"] as const).map((type) => ({ id: type, type, name: `Fixture ${type}`, url: `https://${type}.invalid`, token: "fixture-only", userId: "fixture", enabled: true })) })); setPage("library"); }}>Test home server libraries</button>
        <button onClick={() => setActiveStream({ source: "YouTube player example", addonName: "Test fixture", quality: "", size: "", url: "https://www.youtube.com/watch?v=M7lc1UVf-VE" })}>Test YouTube embed</button>
        <button onClick={() => { sessionStorage.removeItem("arvio.web.noAddonsPrompt.v1:fixture"); setSettings((old) => ({ ...old, homeServers: [], iptvPlaylists: [] })); setPage("onboarding"); setShowOnboarding(true); }}>Test source setup</button>
      </div>
      {showOnboarding && <NoAddonsPrompt />}
    </div>
  </AppContext.Provider>;
}

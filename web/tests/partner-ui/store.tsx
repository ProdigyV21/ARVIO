// Test-only, typed offline store. Never imported by a production entry point.
import { createContext, useContext, useState } from "react";
import type { AppStore } from "../../lib/store";
import type { AppSettings, MediaItem } from "../../lib/types";
import { PartnerLinkHandler } from "../../components/shell/PartnerLinkHandler";
import { BackHandler } from "../../components/shell/BackHandler";
import { DetailsDrawer } from "../../components/details/DetailsDrawer";

type FixtureStore = Pick<AppStore, "view" | "partnerLinkReady" | "auth" | "activeProfile" | "selected" | "section" |
  "settings" | "openDetails" | "closePlayer" | "closeDetails" | "query" | "setQuery" | "setSection" |
  "activeStream" | "activeChannel" | "streams" | "playStream" | "selectedEpisode" | "addons" | "loadEpisodeStreams" |
  "playTrailer" | "setToast" | "watchlist" | "refreshData" | "busy" | "isWatched" | "markWatchedLocally" |
  "toggleWatchlist" | "mdblistConnected" | "openContextMenu" | "toggleWatched">;
const Context = createContext<FixtureStore | null>(null);
export function useApp() { const value = useContext(Context); if (!value) throw new Error("Missing fixture store"); return value; }
export const authClient = { session: null };
export const traktClient = { isConnected: false };
export const getPriorityConfig = () => ({ movieProviders: ["tmdb"], tvProviders: ["tmdb"], animeProviders: ["tmdb"] });

const settings: AppSettings = {
  autoPlayNext: false, autoPlaySingleSource: false, autoPlayMinQuality: "any", frameRateMatchingMode: "off",
  trailerAutoPlay: false, trailerSound: false, trailerDelaySeconds: 2, trailerInCards: false, volumeBoostDb: 0,
  includeSpecials: true, qualityFilterPreset: "off", qualityFilters: [], language: "en-US", defaultSubtitle: "en",
  secondarySubtitle: "", audioLanguage: "", subtitleSize: 100, subtitleColor: "#fff", subtitleColorName: "White",
  subtitleOffsetMs: 0, subtitleOffset: "bottom", subtitleStyle: "outline", subtitleStylized: false,
  filterSubtitlesByLanguage: false, removeHearingImpaired: false, aiSubtitlesEnabled: false, aiSubtitleModel: "off",
  aiAutoSelect: false, aiApiKey: "", defaultPlayer: "browser", cardLayoutMode: "landscape", deviceModeOverride: "auto",
  oledBlack: true, clockFormat: "24h", showBudget: false, iptvFavoritesOnHome: false, smoothScrolling: false, spoilerBlur: false, accentColor: "arctic",
  dnsProvider: "system", showLoadingStats: false, customUserAgent: "", torrServerBaseUrl: "", skipProfileSelection: false,
  cardDensity: "comfortable", catalogs: [], hiddenCatalogIds: [], hiddenAddonCatalogIds: [], hiddenHomeServerCatalogIds: [], disabledAddonIds: [],
  homeServers: [], iptvPlaylists: [], iptvStalkerUrl: "", iptvStalkerMac: "", favoriteChannelIds: [], favoriteGroupIds: [],
  hiddenGroupIds: [], groupOrder: [], customTmdbApiKey: "", customTvdbApiKey: "", customTvdbUserPin: "",
  metadataMovieProviders: ["tmdb"], metadataTvProviders: ["tmdb"], metadataAnimeProviders: ["tmdb"], iptvSortOrder: "provider"
};

const noop = () => {};
const empty = async () => {};

export function PartnerFixture() {
  const [view, setView] = useState<AppStore["view"]>("login");
  const [hydrated, setHydrated] = useState(false);
  const [entitled, setEntitled] = useState(false);
  const [selected, setSelected] = useState<MediaItem | null>(null);
  const [section, setSection] = useState<AppStore["section"]>("home");
  const [query, setQuery] = useState("");
  const [opens, setOpens] = useState(0);
  const [plays, setPlays] = useState(0);
  const [toast, setToast] = useState<string | null>(null);
  const [selectedEpisode, setSelectedEpisode] = useState<AppStore["selectedEpisode"]>(null);
  const app: FixtureStore = {
    view, partnerLinkReady: view === "app" && hydrated, auth: null,
    activeProfile: { id: "fixture", name: "Offline test profile", avatarColor: 0, avatarId: 0, avatarImageVersion: 0,
      isKidsProfile: false, isLocked: false, pin: null, createdAt: 0, lastUsedAt: 0 },
    selected, section, settings, query, setQuery, setSection, selectedEpisode,
    openDetails: async item => {
      setOpens(value => value + 1);
      setSelected(item);
      setSelectedEpisode(item.seasonNumber != null && item.episodeNumber != null
        ? { season: item.seasonNumber, episode: item.episodeNumber } : null);
    },
    closeDetails: () => { setSelected(null); setSelectedEpisode(null); }, closePlayer: noop,
    activeStream: null, activeChannel: null, streams: [], addons: [], busy: "", watchlist: [], mdblistConnected: false,
    playStream: () => setPlays(value => value + 1), playTrailer: async () => { setPlays(value => value + 1); },
    loadEpisodeStreams: async () => [], setToast, refreshData: empty, isWatched: () => false,
    markWatchedLocally: noop, toggleWatchlist: empty, toggleWatched: empty, openContextMenu: noop
  };
  return <Context.Provider value={app}>
    <nav aria-label="Offline QA controls" style={{ display: "flex", gap: 12, padding: 20, flexWrap: "wrap", position: "relative", zIndex: 10 }}>
      <button onClick={() => setView("profiles")}>Simulate login</button>
      <button onClick={() => setView("app")}>Choose profile</button>
      <button onClick={() => setEntitled(true)}>Grant membership</button>
      <button onClick={() => setHydrated(true)}>Hydrate profile</button>
      <button onClick={() => { void app.openDetails({ id: 999, title: "Manual selection", mediaType: "movie" }); }}>Manual selection</button>
      <output data-testid="qa-state" data-view={view} data-hydrated={hydrated} data-entitled={entitled} data-opens={opens} data-plays={plays}>Offline fixture — no real accounts or media</output>
    </nav>
    {view !== "app" ? <main>{view === "login" ? "Login required (fixture)" : "Choose a profile (fixture)"}</main> :
      !entitled ? <main>Membership required (fixture)</main> : <main className="app-shell oled">
        <PartnerLinkHandler />
        {selected ? <DetailsDrawer /> : <p>Ready for a title link</p>}
        <BackHandler />
      </main>}
    {toast ? <p role="status">{toast}</p> : null}
  </Context.Provider>;
}

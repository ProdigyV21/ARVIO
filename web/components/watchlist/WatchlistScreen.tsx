"use client";

import { Bookmark, Film, LoaderCircle, RefreshCw, Search, Server, Tv } from "lucide-react";
import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { MediaCard } from "@/components/media/MediaCard";
import { TrackerLibrary } from "./TrackerLibrary";
import { LIBRARY_SORT_OPTIONS, compareLibraryItems } from "@/lib/librarySort";
import type { HomeServerLibraryOption, HomeServerLibraryPage, HomeServerLibrarySort } from "@/lib/homeserver";
import { useApp } from "@/lib/store";
import type { HomeServerConfig, MediaItem } from "@/lib/types";

type WatchlistFilter = "all" | "movie" | "tv";
type LibraryTab = "watchlist" | HomeServerConfig["type"];

const PAGE_SIZE = 60;
const BUILTIN_SOURCES = [
  { value: "watchlist", label: "Watchlist" },
  { value: "collection", label: "Trakt collection" }
] as const;
const PROVIDER_LABELS: Record<HomeServerConfig["type"], string> = {
  plex: "Plex",
  jellyfin: "Jellyfin",
  emby: "Emby"
};
const libraryCache = new Map<string, HomeServerLibraryPage>();

function itemKey(item: MediaItem): string {
  return item.isHomeServer
    ? `${item.homeServerId ?? "server"}:${item.homeServerItemId ?? item.id}`
    : `${item.mediaType}:${item.id}`;
}

export function WatchlistScreen() {
  const {
    watchlist, traktConnected, simklConnected, mdblistConnected, openDetails,
    settings, trackingPreferences, loadTraktLists, loadTraktListItems, auth, activeProfile
  } = useApp();
  const posterMode = settings.cardLayoutMode === "poster";
  const homeServers = useMemo(
    () => (settings.homeServers ?? []).filter((server) => server.enabled && server.url),
    [settings.homeServers]
  );
  const providerTypes = useMemo(
    () => (["plex", "jellyfin", "emby"] as const).filter((type) => homeServers.some((server) => server.type === type)),
    [homeServers]
  );

  const [tab, setTab] = useState<LibraryTab>("watchlist");
  const [trackerTab, setTrackerTab] = useState<"trakt" | "simkl" | null>(null);
  const [filter, setFilter] = useState<WatchlistFilter>("all");
  const [sort, setSort] = useState<HomeServerLibrarySort>("added");
  const [search, setSearch] = useState("");
  const [searchQuery, setSearchQuery] = useState("");
  const [watchlistSource, setWatchlistSource] = useState("watchlist");
  const [customLists, setCustomLists] = useState<Array<{ id: string; name: string }>>([]);
  const [libraries, setLibraries] = useState<HomeServerLibraryOption[]>([]);
  const [selectedLibrary, setSelectedLibrary] = useState("");
  const [libraryPage, setLibraryPage] = useState<HomeServerLibraryPage>({ items: [], hasMore: false, total: 0 });
  const [sourceItems, setSourceItems] = useState<MediaItem[] | null>(null);
  const [loading, setLoading] = useState(false);
  const [loadingMore, setLoadingMore] = useState(false);
  const [libraryError, setLibraryError] = useState(false);
  const requestRef = useRef(0);
  const librariesRequest = useRef(0);
  const loadMoreRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    const timer = window.setTimeout(() => setSearchQuery(search.trim()), 280);
    return () => window.clearTimeout(timer);
  }, [search]);

  useEffect(() => {
    if (!traktConnected) return;
    let active = true;
    void loadTraktLists().then((lists) => { if (active) setCustomLists(lists); }).catch(() => undefined);
    return () => { active = false; };
  }, [traktConnected, loadTraktLists]);

  const refreshLibraries = useCallback(async () => {
    const id = ++librariesRequest.current;
    if (!homeServers.length) {
      setLibraries([]);
      return;
    }
    const { listHomeServerLibraries } = await import("@/lib/homeserver");
    try {
      const next = await listHomeServerLibraries(homeServers);
      if (librariesRequest.current === id) setLibraries(next);
    } catch {
      if (librariesRequest.current === id) setLibraryError(true);
    }
  }, [homeServers]);

  useEffect(() => { void refreshLibraries(); return () => { librariesRequest.current++; requestRef.current++; }; }, [refreshLibraries]);

  useEffect(() => {
    if (tab !== "watchlist" && !providerTypes.includes(tab)) setTab("watchlist");
  }, [providerTypes, tab]);

  const visibleLibraries = useMemo(
    () => tab === "watchlist" ? [] : libraries.filter((library) => library.serverType === tab),
    [libraries, tab]
  );

  useEffect(() => {
    if (tab === "watchlist") return;
    if (!visibleLibraries.some((library) => library.value === selectedLibrary)) {
      setSelectedLibrary(visibleLibraries[0]?.value ?? "");
    }
  }, [tab, visibleLibraries, selectedLibrary]);

  useEffect(() => {
    if (tab !== "watchlist" && filter !== "all") setFilter("all");
  }, [filter, tab]);

  const activeLibrary = useMemo(
    () => libraries.find((library) => library.value === selectedLibrary),
    [libraries, selectedLibrary]
  );

  useEffect(() => {
    if (tab !== "watchlist" || watchlistSource === "watchlist") {
      setSourceItems(null);
      return;
    }
    const requestId = ++requestRef.current;
    setLoading(true);
    void loadTraktListItems(watchlistSource)
      .then((items) => { if (requestId === requestRef.current) setSourceItems(items); })
      .catch(() => { if (requestId === requestRef.current) setSourceItems([]); })
      .finally(() => { if (requestId === requestRef.current) setLoading(false); });
  }, [tab, watchlistSource, loadTraktListItems]);

  const libraryFilter: WatchlistFilter = "all";
  const cacheKey = `${auth?.userId ?? "local"}:${activeProfile?.id}:${selectedLibrary}|${sort}|${searchQuery.toLowerCase()}`;
  const loadLibrary = useCallback(async (force = false) => {
    if (tab === "watchlist" || !selectedLibrary) return;
    const requestId = ++requestRef.current;
    const cached = libraryCache.get(cacheKey);
    if (cached && !force) setLibraryPage(cached);
    setLoading(!cached);
    setLibraryError(false);
    const { loadHomeServerLibraryPage } = await import("@/lib/homeserver");
    try {
      const page = await loadHomeServerLibraryPage(homeServers, selectedLibrary, {
        offset: 0,
        limit: PAGE_SIZE,
        sort,
        filter: libraryFilter,
        libraryMediaType: activeLibrary?.mediaType,
        search: searchQuery,
        throwOnError: true
      });
      if (requestId !== requestRef.current) return;
      libraryCache.set(cacheKey, page);
      setLibraryPage(page);
    } catch {
      if (requestId === requestRef.current) setLibraryError(true);
    } finally {
      if (requestId === requestRef.current) setLoading(false);
    }
  }, [activeLibrary?.mediaType, cacheKey, homeServers, libraryFilter, searchQuery, selectedLibrary, sort, tab]);

  useEffect(() => { void loadLibrary(); return () => { requestRef.current++; }; }, [loadLibrary]);

  const loadMore = useCallback(async (retry = false) => {
    if (tab === "watchlist" || loading || loadingMore || (libraryError && !retry) || !libraryPage.hasMore || !selectedLibrary) return;
    setLibraryError(false);
    setLoadingMore(true);
    const requestId = requestRef.current;
    const { loadHomeServerLibraryPage } = await import("@/lib/homeserver");
    try {
      const next = await loadHomeServerLibraryPage(homeServers, selectedLibrary, {
        offset: libraryPage.items.length,
        limit: PAGE_SIZE,
        sort,
        filter: libraryFilter,
        libraryMediaType: activeLibrary?.mediaType,
        search: searchQuery,
        throwOnError: true
      });
      if (requestId !== requestRef.current) return;
      setLibraryPage((current) => {
        const seen = new Set(current.items.map(itemKey));
        const merged = [...current.items, ...next.items.filter((item) => !seen.has(itemKey(item)))];
        const page = { items: merged, total: next.total, hasMore: next.hasMore };
        libraryCache.set(cacheKey, page);
        return page;
      });
    } catch {
      if (requestId === requestRef.current) setLibraryError(true);
    } finally {
      setLoadingMore(false);
    }
  }, [activeLibrary?.mediaType, cacheKey, homeServers, libraryError, libraryFilter, libraryPage.hasMore, libraryPage.items.length, loading, loadingMore, searchQuery, selectedLibrary, sort, tab]);

  useEffect(() => {
    const target = loadMoreRef.current;
    if (!target || tab === "watchlist") return;
    const observer = new IntersectionObserver((entries) => {
      if (entries[0]?.isIntersecting) void loadMore();
    }, { rootMargin: "600px" });
    observer.observe(target);
    return () => observer.disconnect();
  }, [loadMore, tab]);

  const watchlistList = watchlistSource === "watchlist" ? watchlist : (sourceItems ?? []);
  const items = useMemo(() => {
    if (tab !== "watchlist") return libraryPage.items;
    const filtered = filter === "all" ? watchlistList : watchlistList.filter((item) => item.mediaType === filter);
    return [...filtered].sort((a, b) => compareLibraryItems(a, b, sort));
  }, [filter, libraryPage.items, sort, tab, watchlistList]);

  const activeServerName = activeLibrary?.serverName ?? visibleLibraries[0]?.serverName ?? "Home server";
  const heading = tab === "watchlist" ? "Watchlist" : `${PROVIDER_LABELS[tab]} Library`;
  const watchlistSyncLabel = (() => {
    const mode = trackingPreferences.watchlistReadMode;
    if (mode === "both" && traktConnected && simklConnected) return "Trakt + Simkl";
    if (mode === "simkl" && simklConnected) return "Simkl";
    if (mode === "mdblist" && mdblistConnected) return "MDBList";
    if (mode === "trakt" && traktConnected) return "Trakt";
    if (traktConnected) return "Trakt";
    if (simklConnected) return "Simkl";
    if (mdblistConnected) return "MDBList";
    return null;
  })();
  const eyebrow = tab === "watchlist"
    ? watchlistSyncLabel ? `Synced with ${watchlistSyncLabel}` : "Saved across your ARVIO devices"
    : loading && !items.length ? `Connecting to ${PROVIDER_LABELS[tab]}` : `${libraryPage.total.toLocaleString()} titles${activeLibrary ? ` in ${activeLibrary.libraryName}` : ""}`;

  return (
    <div className={`screen has-section-heading library-screen ${posterMode ? "poster-results" : ""}`}>
      <section className="section-heading library-heading">
        <div className="library-title-block">
          <p className="eyebrow">{trackerTab ? "Your connected library" : eyebrow}</p>
          <h2>{trackerTab ? trackerTab === "trakt" ? "Trakt Library" : "Simkl Library" : heading}</h2>
        </div>
        <nav className="library-provider-tabs" aria-label="Library source">
            <button type="button" className={!trackerTab && tab === "watchlist" ? "is-active" : ""} onClick={() => { setTrackerTab(null); setTab("watchlist"); }}>
            <Bookmark size={17} /> Watchlist
          </button>
          {providerTypes.map((type) => (
            <button key={type} type="button" className={!trackerTab && tab === type ? "is-active" : ""} onClick={() => { setTrackerTab(null); setTab(type); }}>
              <span className={`library-provider-mark is-${type}`} aria-hidden="true" /> {PROVIDER_LABELS[type]}
            </button>
          ))}
          {traktConnected && <button type="button" className={trackerTab === "trakt" ? "is-active" : ""} onClick={() => setTrackerTab("trakt")}><span className="library-provider-mark is-trakt" /> Trakt</button>}
          {simklConnected && <button type="button" className={trackerTab === "simkl" ? "is-active" : ""} onClick={() => setTrackerTab("simkl")}><span className="library-provider-mark is-simkl" /> Simkl</button>}
          {!trackerTab && tab !== "watchlist" && (
            <select className="library-provider-sort" value={sort} onChange={(event) => setSort(event.target.value as HomeServerLibrarySort)} aria-label="Sort titles">
              {LIBRARY_SORT_OPTIONS.map(({ value, label }) => <option key={value} value={value}>{label}</option>)}
            </select>
          )}
        </nav>
      </section>

      {trackerTab ? <TrackerLibrary key={trackerTab} provider={trackerTab} /> : <div className={`library-workspace ${tab !== "watchlist" && visibleLibraries.length ? "has-library-sidebar" : ""}`}>
        {tab !== "watchlist" && visibleLibraries.length > 0 && (
          <aside className="library-sidebar" aria-label={`${PROVIDER_LABELS[tab]} libraries`}>
            <strong className="library-sidebar-server">{activeServerName}</strong>
            <span className="library-sidebar-label">Libraries</span>
            <div role="tablist">
              {visibleLibraries.map((library) => (
                <button key={library.value} type="button" role="tab" aria-selected={selectedLibrary === library.value}
                  className={selectedLibrary === library.value ? "is-active" : ""} onClick={() => setSelectedLibrary(library.value)}>
                  {library.mediaType === "movie"
                    ? <Film className={`library-type-icon is-${library.serverType}`} size={17} aria-hidden="true" />
                    : <Tv className={`library-type-icon is-${library.serverType}`} size={17} aria-hidden="true" />}
                  <span>{library.libraryName}</span>
                  {visibleLibraries.filter((item) => item.libraryName === library.libraryName).length > 1 && <small>{library.serverName}</small>}
                </button>
              ))}
            </div>
          </aside>
        )}
        <div className={`library-main ${loading && items.length > 0 ? "is-refreshing" : ""}`}>
          {tab !== "watchlist" && visibleLibraries.length > 0 && (
            <label className="library-mobile-select">
              {activeLibrary?.mediaType === "movie" ? <Film size={16} /> : <Tv size={16} />}
              <select value={selectedLibrary} onChange={(event) => setSelectedLibrary(event.target.value)} aria-label="Choose library">
                {visibleLibraries.map((library) => (
                  <option key={library.value} value={library.value}>
                    {library.libraryName}{visibleLibraries.filter((item) => item.libraryName === library.libraryName).length > 1 ? ` — ${library.serverName}` : ""}
                  </option>
                ))}
              </select>
            </label>
          )}

          <div className="library-toolbar">
        {tab === "watchlist" && traktConnected && (
          <select className="watchlist-source" value={watchlistSource} onChange={(event) => setWatchlistSource(event.target.value)} aria-label="Choose list">
            {traktConnected && BUILTIN_SOURCES.map((source) => <option key={source.value} value={source.value}>{source.label}</option>)}
            {!traktConnected && <option value="watchlist">Watchlist</option>}
            {customLists.map((list) => <option key={list.id} value={`list:${list.id}`}>{list.name}</option>)}
          </select>
        )}
        {tab === "watchlist" && (
          <div className="watchlist-pills" role="group" aria-label="Filter titles">
            {([["all", "All"], ["movie", "Movies"], ["tv", "Series"]] as const).map(([value, label]) => (
              <button key={value} type="button" className={`watchlist-pill ${filter === value ? "is-active" : ""}`} onClick={() => setFilter(value)}>{label}</button>
            ))}
          </div>
        )}
        {tab === "watchlist" && (
          <select className="watchlist-sort" value={sort} onChange={(event) => setSort(event.target.value as HomeServerLibrarySort)} aria-label="Sort titles">
            {LIBRARY_SORT_OPTIONS.map(({ value, label }) => <option key={value} value={value}>{label}</option>)}
          </select>
        )}
        {tab !== "watchlist" && (
          <label className="library-search">
            <Search size={17} />
            <input value={search} onChange={(event) => setSearch(event.target.value)} placeholder={`Search ${PROVIDER_LABELS[tab]}`} />
          </label>
        )}
        {tab !== "watchlist" && (
          <select className="watchlist-sort library-mobile-sort" value={sort} onChange={(event) => setSort(event.target.value as HomeServerLibrarySort)} aria-label="Sort titles">
            {LIBRARY_SORT_OPTIONS.map(({ value, label }) => <option key={value} value={value}>{label}</option>)}
          </select>
        )}
        {tab !== "watchlist" && (
          <button type="button" className="library-refresh" title="Refresh library" aria-label="Refresh library" onClick={() => void loadLibrary(true)} disabled={loading}>
            <RefreshCw size={17} />
          </button>
        )}
          </div>

          {loading && items.length === 0 ? (
            <div className="library-loading" aria-label="Loading library"><LoaderCircle size={34} /></div>
          ) : libraryError && items.length === 0 ? (
            <div className="watchlist-empty"><Server size={42} /><p>Server unavailable</p><span>Check the server connection and try again.</span><button type="button" onClick={() => void loadLibrary(true)}>Retry</button></div>
          ) : items.length === 0 ? (
            <div className="watchlist-empty"><Bookmark size={42} /><p>{searchQuery ? "No matching titles" : "This library is empty"}</p><span>{tab === "watchlist" ? "Add movies and series from their details page." : "Try another library or filter."}</span></div>
          ) : (
            <>
              <div className="grid-results library-grid">
                {items.map((item) => <MediaCard key={itemKey(item)} item={item} onOpen={openDetails} posterMode={posterMode} />)}
              </div>
              {libraryError && <div className="library-error" role="alert"><span>The server could not update this library. Loaded titles are still available.</span><button type="button" className="secondary" onClick={() => void (libraryPage.hasMore ? loadMore(true) : loadLibrary(true))}>Retry</button></div>}
              {loading && items.length > 0 && <div className="library-refreshing-indicator" aria-label="Updating library"><LoaderCircle size={24} /></div>}
              {tab !== "watchlist" && <div ref={loadMoreRef} className="library-load-more">{loadingMore && <LoaderCircle size={26} />}</div>}
            </>
          )}
        </div>
      </div>}
    </div>
  );
}

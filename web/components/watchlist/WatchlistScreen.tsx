"use client";
import { useTranslation } from "@/lib/i18n";


import { Bookmark, Film, LoaderCircle, RefreshCw, Search, Server, Tv, SlidersHorizontal, ArrowLeft, X } from "lucide-react";
import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { MediaCard } from "@/components/media/MediaCard";
import { CollectionCover, LibraryGrid, LibraryDialog } from "./LibraryPresentation";
import { CalendarScreen } from "./CalendarScreen";
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
const viewportPositions = new Map<string, number>();
interface LibraryView { section: "watchlists" | "lists" | "libraries" | "calendar"; openedList: string | null; tab: LibraryTab; trackerTab: "trakt" | "simkl" | null; trackerSource: string; selectedLibrary: string; sort: HomeServerLibrarySort; filter: WatchlistFilter; search: string }
const savedViews = new Map<string, LibraryView>();

function itemKey(item: MediaItem): string {
  return item.isHomeServer
    ? `${item.homeServerId ?? "server"}:${item.homeServerItemId ?? item.id}`
    : `${item.mediaType}:${item.id}`;
}

export function WatchlistScreen() {
  const translateUi = useTranslation();
  const {
    watchlist, traktConnected, simklConnected, mdblistConnected, openDetails,
    settings, trackingPreferences, loadTraktLists, loadTraktListItems, loadTrackerLibrary, loadCatalogRow, catalogConfigs, auth, activeProfile, setSection: navigate
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

  const viewScope = `${auth?.userId ?? "local"}:${activeProfile?.id ?? "default"}`;
  const saved = savedViews.get(viewScope);
  const [section, setSection] = useState<LibraryView["section"]>(saved?.section ?? "watchlists");
  const [openedList, setOpenedList] = useState<string | null>(saved?.openedList ?? null);
  const [showFilters, setShowFilters] = useState(false);
  const [showSearch, setShowSearch] = useState(false);
  const [trackerSource, setTrackerSource] = useState(saved?.trackerSource ?? "watchlist");
  const [retry, setRetry] = useState(0);
  const [tab, setTab] = useState<LibraryTab>(saved?.tab ?? "watchlist");
  const [trackerTab, setTrackerTab] = useState<"trakt" | "simkl" | null>(saved?.trackerTab ?? null);
  const [filter, setFilter] = useState<WatchlistFilter>(saved?.filter ?? "all");
  const [sort, setSort] = useState<HomeServerLibrarySort>(saved?.sort ?? "added");
  const [search, setSearch] = useState(saved?.search ?? "");
  const [searchQuery, setSearchQuery] = useState("");
  const [watchlistSource, setWatchlistSource] = useState("watchlist");
  const [customLists, setCustomLists] = useState<Array<{ id: string; name: string }>>([]);
  const [libraries, setLibraries] = useState<HomeServerLibraryOption[]>([]);
  const [selectedLibrary, setSelectedLibrary] = useState(saved?.selectedLibrary ?? "");
  const [libraryPage, setLibraryPage] = useState<HomeServerLibraryPage>({ items: [], hasMore: false, total: 0 });
  const [sourceItems, setSourceItems] = useState<MediaItem[] | null>(null);
  const [loading, setLoading] = useState(false);
  const [loadingMore, setLoadingMore] = useState(false);
  const [libraryError, setLibraryError] = useState(false);
  const latestView = useRef<LibraryView>({ section, openedList, tab, trackerTab, trackerSource, selectedLibrary, sort, filter, search });
  latestView.current = { section, openedList, tab, trackerTab, trackerSource, selectedLibrary, sort, filter, search };
  useEffect(() => () => { savedViews.set(viewScope, latestView.current); if(savedViews.size > 12) savedViews.delete(savedViews.keys().next().value!); }, [viewScope]);
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

  const personalLists = useMemo(() => [
    ...customLists.map((list) => ({ id: `list:${list.id}`, name: list.name, provider: "Trakt", catalog: null as typeof catalogConfigs[number] | null })),
    ...catalogConfigs.filter((catalog) => !catalog.isPreinstalled && !["preinstalled", "home_server"].includes(catalog.sourceType.toLowerCase()) && !["collection", "collection_rail"].includes((catalog.kind ?? "").toLowerCase()))
      .map((catalog) => ({ id: `catalog:${catalog.id}`, name: catalog.title || catalog.name, provider: catalog.addonName || catalog.sourceType, catalog }))
  ], [customLists, catalogConfigs]);
  const activeList = personalLists.find((list) => list.id === openedList);
  useEffect(() => {
    if (tab !== "watchlist") return;
    if (!trackerTab && !openedList && watchlistSource === "watchlist") { setSourceItems(null); setLoading(false); setLibraryError(false); return; }
    const requestId = ++requestRef.current;
    setSourceItems(null); setLoading(true); setLibraryError(false);
    const task = activeList?.catalog ? loadCatalogRow(activeList.catalog).then((row) => row?.items ?? [])
      : trackerTab ? loadTrackerLibrary(trackerTab, trackerSource)
      : loadTraktListItems(openedList ?? watchlistSource);
    void task.then((rows) => { if (requestId === requestRef.current) setSourceItems(rows); })
      .catch(() => { if (requestId === requestRef.current) setLibraryError(true); })
      .finally(() => { if (requestId === requestRef.current) setLoading(false); });
    return () => { requestRef.current++; };
  }, [tab, trackerTab, trackerSource, openedList, activeList?.catalog, watchlistSource, loadTraktListItems, loadTrackerLibrary, loadCatalogRow, retry]);

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

  const watchlistList = !trackerTab && !openedList && watchlistSource === "watchlist" ? watchlist : (sourceItems ?? []);
  const items = useMemo(() => {
    if (tab !== "watchlist") return libraryPage.items;
    const filtered = watchlistList.filter((item) => (filter === "all" || item.mediaType === filter) && item.title.toLowerCase().includes(searchQuery.toLowerCase()));
    return [...filtered].sort((a, b) => compareLibraryItems(a, b, sort));
  }, [filter, libraryPage.items, sort, tab, watchlistList, searchQuery]);

  const collections = section === "lists" && !openedList;
  const trackerSources = [
    ...(traktConnected ? [{ provider: "trakt" as const, id: "watchlist", name: "Watchlist" }, { provider: "trakt" as const, id: "collection", name: "Collection" }, { provider: "trakt" as const, id: "watched", name: "Watched" }] : []),
    ...(simklConnected ? ["plantowatch", "watching", "completed", "hold", "dropped"].map((id, index) => ({ provider: "simkl" as const, id, name: ["Plan to watch", "Watching", "Completed", "On hold", "Dropped"][index] })) : [])
  ];
  const sourceValue = tab !== "watchlist" ? selectedLibrary : trackerTab ? `${trackerTab}:${trackerSource}` : "saved";
  const selectSource = (value: string) => {
    if (value === "saved") { setTab("watchlist"); setTrackerTab(null); setWatchlistSource("watchlist"); }
    else if (value.startsWith("trakt:") || value.startsWith("simkl:")) { const [provider, id] = value.split(":"); setTab("watchlist"); setTrackerTab(provider as "trakt" | "simkl"); setTrackerSource(id); }
    else { const library = libraries.find((entry) => entry.value === value); if(library) { setTrackerTab(null); setTab(library.serverType); setSelectedLibrary(value); } }
  };
  const changeSection = (value: typeof section) => {
    setSection(value); setOpenedList(null); setSearch(""); setSearchQuery(""); setFilter("all"); setTrackerTab(null);
    if(value === "libraries") { setTab(providerTypes[0] ?? "watchlist"); if(libraries[0]) selectSource(libraries[0].value); }
    else { setTab("watchlist"); setWatchlistSource("watchlist"); }
  };
  const refresh = () => { if(tab === "watchlist") setRetry((value) => value + 1); else void loadLibrary(true); };
  const sourceOptions = section === "libraries" ? libraries.map((entry) => ({ value: entry.value, name: `${entry.serverName} / ${entry.libraryName}` }))
    : [{ value: "saved", name: translateUi("My watchlist") }, ...trackerSources.map((entry) => ({ value: `${entry.provider}:${entry.id}`, name: `${entry.provider === "trakt" ? "Trakt" : "Simkl"} / ${translateUi(entry.name)}` }))];
  return (
    <div className={`screen oled-library ${section === "calendar" ? "oled-calendar-screen" : ""} ${posterMode ? "poster-results" : ""}`}>
      <header className="oled-library-toolbar">
        <nav aria-label={translateUi("Library sections")}>{([ ["watchlists", "Watchlists"], ["lists", "My lists"], ["libraries", "Homeserver"], ["calendar", "Calendar"] ] as const).map(([value, label]) =>
          <button key={value} aria-current={section === value ? "page" : undefined} onClick={() => changeSection(value)}>{translateUi(label)}</button>)}</nav>
        {section !== "calendar" && <div className="oled-library-actions"><span>{collections ? translateUi("{value0} lists", {value0: personalLists.length}) : translateUi("{value0} titles", {value0: tab !== "watchlist" ? libraryPage.total : items.length})}</span>
          {collections && <button onClick={() => navigate("settings")}>{translateUi("+ New list")}</button>}
          <button aria-label={translateUi("Search library")} onClick={() => setShowSearch(true)}><Search size={22} /></button>
          <button className="oled-filter-button" onClick={() => setShowFilters(true)}><SlidersHorizontal size={20} /><span>{translateUi("Filters")}</span></button>
        </div>}
      </header>
      {section === "calendar" ? <CalendarScreen key={viewScope} /> : <>
      {!collections && !openedList && <select className="oled-source-select" value={sourceValue} onChange={(event) => selectSource(event.target.value)} aria-label={translateUi("Library source")}>{sourceOptions.map((source) => <option key={source.value} value={source.value}>{source.name}</option>)}</select>}
      {openedList && <div className="oled-list-breadcrumb"><button onClick={() => setOpenedList(null)}><ArrowLeft size={18}/> {translateUi(" My lists")}</button><span>{activeList?.name}</span></div>}
      <div className={`oled-library-body ${!collections && !openedList ? "with-sources" : ""}`}>
        {!collections && !openedList && <aside className="oled-source-nav" aria-label={translateUi("Library sources")}>
          {section === "watchlists" ? <>
            <p>{translateUi("Saved")}</p><button aria-current={sourceValue === "saved" ? "true" : undefined} onClick={() => selectSource("saved")}><Bookmark size={18}/>{translateUi("My watchlist")}</button>
            {(["trakt", "simkl"] as const).map((provider) => trackerSources.some((source) => source.provider === provider) && <div key={provider}><p>{provider === "trakt" ? translateUi("Trakt") : translateUi("Simkl")}</p>{trackerSources.filter((source) => source.provider === provider).map((source) => <button key={source.id} aria-current={sourceValue === `${provider}:${source.id}` ? "true" : undefined} onClick={() => selectSource(`${provider}:${source.id}`)}>{translateUi(source.name)}</button>)}</div>)}
          </> : homeServers.map((server) => <div key={server.id}><p>{server.name}<small>{PROVIDER_LABELS[server.type]}</small></p>{libraries.filter((library) => library.serverId === server.id).map((library) => <button key={library.value} aria-current={selectedLibrary === library.value ? "true" : undefined} onClick={() => selectSource(library.value)}>{library.mediaType === "movie" ? <Film size={18}/> : <Tv size={18}/>}<span>{library.libraryName}</span></button>)}</div>)}
          {section === "libraries" && <button className="oled-connect" onClick={() => navigate("settings")}>{translateUi("+ Connect server")}</button>}
        </aside>}
        <section className="oled-library-content" aria-label={collections ? translateUi("Personal lists") : activeList?.name || translateUi("Library titles")}>
          {collections ? <div className="oled-collections">{personalLists.filter((list) => list.name.toLowerCase().includes(searchQuery.toLowerCase())).map((list) => <CollectionCover key={list.id} title={list.name} provider={list.provider} load={() => list.catalog ? loadCatalogRow(list.catalog).then((row) => row?.items ?? []) : loadTraktListItems(list.id)} onOpen={() => { setOpenedList(list.id); setSearch(""); setSearchQuery(""); }} />)}{!personalLists.length && <div className="watchlist-empty"><p>{translateUi("No personal lists yet")}</p><span>{translateUi("Your custom catalogs and connected personal lists appear here.")}</span></div>}</div>
          : section === "libraries" && !libraries.length ? <div className="watchlist-empty"><Server size={36}/><p>{translateUi("No libraries connected")}</p><button onClick={() => navigate("settings")}>{translateUi("Connect a server")}</button></div>
          : loading && !items.length ? <div className="library-loading" aria-label={translateUi("Loading library")}><LoaderCircle size={32}/></div>
          : !items.length ? <div className="watchlist-empty"><Bookmark size={36}/><p>{libraryError ? translateUi("Library unavailable") : translateUi("No titles found")}</p>{libraryError ? <button onClick={refresh}>{translateUi("Retry")}</button> : <span>{translateUi("Choose another source or add titles to your watchlist.")}</span>}</div>
          : <><LibraryGrid key={`${auth?.userId}:${activeProfile?.id}:${sourceValue}:${openedList}:${sort}:${searchQuery}:${filter}`} positions={viewportPositions} positionKey={`${viewScope}:${sourceValue}:${openedList}:${sort}:${searchQuery}:${filter}:${posterMode}`} items={items} poster={posterMode} onOpen={openDetails} onNearEnd={tab !== "watchlist" ? loadMore : undefined}/>
            {libraryError && <div className="library-error" role="alert">{translateUi("Could not update this source. ")}<button onClick={refresh}>{translateUi("Retry")}</button></div>}
            {tab !== "watchlist" && <div ref={loadMoreRef} className="library-load-more">{loadingMore && <LoaderCircle size={24}/>}</div>}</>}
        </section>
      </div>
      {showFilters && <LibraryDialog title={translateUi("Filters")} close={() => setShowFilters(false)}>
        <label>{translateUi("Sort titles")}<select value={sort} onChange={(event) => setSort(event.target.value as HomeServerLibrarySort)}>{LIBRARY_SORT_OPTIONS.map((option) => <option key={option.value} value={option.value}>{translateUi(option.label)}</option>)}</select></label>
        {tab === "watchlist" && !collections && <label>{translateUi("Type")}<select value={filter} onChange={(event) => setFilter(event.target.value as WatchlistFilter)}><option value="all">{translateUi("All titles")}</option><option value="movie">{translateUi("Movies")}</option><option value="tv">{translateUi("Series")}</option></select></label>}
        {!collections && <button onClick={refresh}><RefreshCw size={18}/>{translateUi("Refresh source")}</button>}
      </LibraryDialog>}
      {showSearch && <LibraryDialog title={translateUi("Search library")} close={() => setShowSearch(false)}><form onSubmit={(event) => { event.preventDefault(); setShowSearch(false); }}><label>{translateUi("Title")}<input autoFocus value={search} onChange={(event) => setSearch(event.target.value)} placeholder={translateUi("Search titles or lists")} /></label><button type="submit">{translateUi("Done")}</button></form></LibraryDialog>}
      </>}
    </div>
  );
}

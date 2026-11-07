"use client";
import { premiumAllows } from "@/lib/premiumGate";
import { useTranslation } from "@/lib/i18n";


import { useEffect, useMemo, useRef, useState, type CSSProperties } from "react";
import { X, Tv, PanelLeft, ChevronRight, Play, RefreshCw } from "lucide-react";
import { guideSports, isOnAir, isConfirmedLive, hasSportsChannels, availableEventChannels, sportsPresentationRows, sportsChannelSummary, type SportsGuideEvent } from "@/lib/sportsGuide";
import { sportsChannelKey, sportsBroadcasterKeys } from "@/lib/sportsCatalogue";
import type { InstalledAddon, IptvChannel, IptvNowNext } from "@/lib/types";
import { cachedSportsMetadata, loadSportsMetadata, type SportsEventArtwork } from "@/lib/sportsArtwork";
import { attachSportsAddonSources, loadSportsAddonEvents, resolveSportsAddon, type SportsAddonEvent, type SportsAddonStream } from "@/lib/sportsAddons";
import { VirtualList } from "@/components/ui/VirtualList";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";
import { channelPlaylistId } from "@/lib/stalkerPortals";
import { sizedSportsImage } from "@/lib/sportsImageSize";

const NO_ADDONS: InstalledAddon[] = [];
export function SportsGuidePane({ channels, guide, onPlay, onEnter, onOpenCategories, providerNames = {}, addons = NO_ADDONS, clockFormat }: {
  channels: IptvChannel[]; guide: Record<string, IptvNowNext>;
  onPlay: (channel: IptvChannel) => void; onEnter: () => void; onOpenCategories: () => void;
  providerNames?: Record<string, string>;
  addons?: InstalledAddon[];
  clockFormat?: "12h" | "24h";
}) {
  const translateUi = useTranslation();
  const [artwork, setArtwork] = useState<SportsEventArtwork[]>([]);
  const [metadataLoading, setMetadataLoading] = useState(true);
  const [retry, setRetry] = useState(0);
  const [addonEvents, setAddonEvents] = useState<SportsAddonEvent[]>([]);
  const [addonLoading, setAddonLoading] = useState(false);
  useEffect(() => {
    const controller = new AbortController();
    setAddonEvents([]);
    let running = false;
    let loaded = false;
    const load = async () => {
      if (running) return;
      running = true; setAddonLoading(true);
      try {
        const refreshed = await loadSportsAddonEvents(addons, controller.signal, items => {
          if (!loaded) setAddonEvents(items);
        });
        if (!controller.signal.aborted) { setAddonEvents(refreshed); loaded = true; }
      }
      finally { running = false; if (!controller.signal.aborted) setAddonLoading(false); }
    };
    void load();
    const timer = setInterval(() => { if (!document.hidden) void load(); }, 120_000);
    return () => { controller.abort(); clearInterval(timer); };
  }, [addons, retry]);
  useEffect(() => {
    let active = true;
    let addonArt: SportsEventArtwork[] = [], metadataArt: SportsEventArtwork[] = cachedSportsMetadata();
    setArtwork(metadataArt);
    const publish = () => { if (active) setArtwork([...addonArt, ...metadataArt]); };
    const load = () => {
      setMetadataLoading(true);
      void Promise.allSettled([
        loadSportsMetadata().then(items => { metadataArt = items; publish(); }),
      ]).then(() => { if (active) setMetadataLoading(false); });
    };
    load();
    const refresh = setInterval(() => { if (!document.hidden) load(); }, 120_000);
    return () => { active = false; clearInterval(refresh); };
  }, [addons, retry]);
  const [events, setEvents] = useState<SportsGuideEvent[]>([]);
  const [loading, setLoading] = useState(true);
  const [failed, setFailed] = useState(false);
  const [now, setNow] = useState(Date.now);
  const [selectedId, setSelectedId] = useState<string | null>(null);
  const [showScore, setShowScore] = useState(false);
  const [rowLimits, setRowLimits] = useState<Record<string, number>>({});
  const [focusedOrder, setFocusedOrder] = useState<{ row: string; ids: string[] } | null>(null);
  // Rows more than a screen away drop their artwork so decoded bitmaps don't
  // pile up while scrolling (iOS Safari kills tabs that hold too much image memory).
  const [nearRows, setNearRows] = useState<Set<string>>(() => new Set());
  const dialog = useRef<HTMLDialogElement>(null);
  const origin = useRef<HTMLButtonElement | null>(null);
  const root = useRef<HTMLElement>(null);
  const entered = useRef(false);
  useEffect(() => { const timer = setInterval(() => setNow(Date.now()), 30_000); return () => clearInterval(timer); }, []);
  const scanDay = new Date(now).toDateString();
  useEffect(() => {
    setLoading(true);
    setFailed(false);
    // Only send channels with cached schedules, not the entire 100k-channel playlist.
    const worker = new Worker(new URL("./sportsGuide.worker.ts", import.meta.url));
    worker.onmessage = (event: MessageEvent<SportsGuideEvent[]>) => {
      setEvents(event.data);
      setLoading(false);
    };
    worker.onerror = () => { setFailed(true); setLoading(false); };
    const broadcasters = new Set(artwork.flatMap(item => item.fixture?.broadcasters.flatMap(b => sportsBroadcasterKeys(b.name, b.country)) ?? []));
    const scanned = channels.filter((ch) => Boolean(guide[ch.id]) || broadcasters.has(sportsChannelKey(ch.name)));
    // Copy only the schedules of those channels into the worker, not the whole guide.
    const scannedGuide: Record<string, IptvNowNext> = {};
    for (const ch of scanned) if (guide[ch.id]) scannedGuide[ch.id] = guide[ch.id];
    worker.postMessage({ channels: scanned, guide: scannedGuide, now: Date.now(), artwork });
    return () => worker.terminate();
  }, [channels, guide, scanDay, retry, artwork]);
  const accessibleIds = useMemo(() => new Set(channels.map((ch) => ch.id)), [channels]);
  // Hide revoked/hidden sources immediately, including during a worker refresh.
  const visibleEvents = useMemo(() => attachSportsAddonSources(events.map((event) => ({ ...event, channels: event.channels.filter((ch) => accessibleIds.has(ch.id)),
    possibleChannels: event.possibleChannels?.filter(ch => accessibleIds.has(ch.id)),
    schedules: event.schedules ? Object.fromEntries(Object.entries(event.schedules).filter(([id]) => accessibleIds.has(id))) : undefined }))
    .filter((event) => event.fixture || event.channels.length && Object.values(event.schedules ?? { fallback: event.programme }).some(p => p.endUtcMillis > now)),
    addonEvents.filter(e => addons.some(a => a.enabled !== false && a.manifestUrl === e.installation)), now, artwork), [events, accessibleIds, now, addonEvents, addons, artwork]);
  const [failedArtwork, setFailedArtwork] = useState<Set<string>>(() => new Set());
  useEffect(() => { setFailedArtwork(new Set()); }, [artwork, retry]);
  const rows = useMemo(() => sportsPresentationRows(visibleEvents, now, failedArtwork).map(row => {
    if (row.id !== focusedOrder?.row) return row;
    const rank = new Map(focusedOrder.ids.map((id, index) => [id, index]));
    return { ...row, events: [...row.events].sort((a, b) => (rank.get(a.id) ?? Infinity) - (rank.get(b.id) ?? Infinity)) };
  }), [visibleEvents, failedArtwork, now, focusedOrder]);
  useEffect(() => {
    if (!rows.length || entered.current) return;
    entered.current = true;
    root.current?.querySelector<HTMLButtonElement>(".tv-event-card")?.focus({ preventScroll: true });
  }, [rows.length]);
  const rowKey = rows.map(row => row.id).join("|");
  useEffect(() => {
    const sections = [...(root.current?.querySelectorAll<HTMLElement>(".tv-sports-section[data-row]") ?? [])];
    if (typeof IntersectionObserver === "undefined") { setNearRows(new Set(sections.map(section => section.dataset.row!))); return; }
    const observer = new IntersectionObserver(entries => setNearRows(current => {
      const next = new Set(current);
      for (const entry of entries) { const id = (entry.target as HTMLElement).dataset.row!; if (entry.isIntersecting) next.add(id); else next.delete(id); }
      return next.size === current.size && [...next].every(id => current.has(id)) ? current : next;
    }), { rootMargin: "100% 0px" });
    sections.forEach(section => observer.observe(section));
    return () => observer.disconnect();
  }, [rowKey]);
  const selected = visibleEvents.find((event) => event.id === selectedId);
  const confirmedChannels = selected ? (isOnAir(selected, now) ? availableEventChannels(selected, now) : selected.channels) : [];
  const possibleChannels = (selected?.possibleChannels ?? []).filter((ch, i, rows) => !confirmedChannels.some(match => match.id === ch.id) && rows.findIndex(other => other.id === ch.id) === i);
  const sourceChannels = [...confirmedChannels, ...possibleChannels];
  const close = () => { dialog.current?.close(); setSelectedId(null); origin.current?.focus({ preventScroll: true }); };
  useEffect(() => {
    if (!selectedId || dialog.current?.open) return;
    dialog.current?.showModal();
    // Virtual rows appear after ResizeObserver measures the opened dialog.
    const observer = new MutationObserver(() => focusFirst());
    const focusFirst = () => {
      const first = dialog.current?.querySelector<HTMLButtonElement>(".tv-event-source:not(:disabled), .tv-addon-source button:not(:disabled)");
      if (first) { first.focus({ preventScroll: true }); observer.disconnect(); }
      return Boolean(first);
    };
    if (!focusFirst() && dialog.current) observer.observe(dialog.current, { childList: true, subtree: true });
    // Nothing playable yet (an upcoming event): start on Close rather than nowhere.
    const fallback = window.setTimeout(() => {
      if (!dialog.current?.contains(document.activeElement) || document.activeElement === dialog.current) dialog.current?.querySelector<HTMLButtonElement>("header button")?.focus({ preventScroll: true });
    }, 400);
    return () => { observer.disconnect(); window.clearTimeout(fallback); };
  }, [selectedId]);
  const stamp = (event: SportsGuideEvent) => {
    if (isConfirmedLive(event, now) || (event.channelOnly && isOnAir(event, now))) return "LIVE";
    if (isOnAir(event, now)) return "ON AIR";
    const date = new Date(event.programme.startUtcMillis);
    const today = new Date(now), tomorrow = new Date(now); tomorrow.setDate(tomorrow.getDate() + 1);
    const day = date.toDateString() === today.toDateString() ? "Today" : date.toDateString() === tomorrow.toDateString() ? "Tomorrow" : new Intl.DateTimeFormat([], { weekday: "short", day: "numeric", month: "short" }).format(date);
    return `${day} ${new Intl.DateTimeFormat([], { hour: "numeric", minute: "2-digit", ...(clockFormat ? { hour12: clockFormat === "12h" } : {}) }).format(date)}`;
  };
  return <section ref={root} className="tv-sports" aria-label={translateUi("Sports")}>
    <h2><button className="tv-sports-drawer" type="button" aria-label={translateUi("Categories")} onClick={onOpenCategories}><PanelLeft size={22} /></button>{translateUi("Sports")}</h2>
    {!rows.length && <div className="tv-sports-empty" role="status"><Tv size={32} /><p>{loading || metadataLoading || addonLoading ? translateUi("Reading sports schedule") : failed ? translateUi("Schedule unavailable") : visibleEvents.some(e => hasSportsChannels(e, now) && (isOnAir(e, now) || e.programme.startUtcMillis > now)) ? translateUi("Event artwork unavailable") : translateUi("No sources found yet.")}</p>
      {!loading && !metadataLoading && <button type="button" className="secondary" onClick={() => setRetry(value => value + 1)}><RefreshCw size={18} />{translateUi("Retry")}</button>}
      <button type="button" className="secondary" onClick={onOpenCategories}><PanelLeft size={18} />{translateUi("Categories")}</button></div>}
    {rows.map((row, rowIndex) => <section className={`tv-sports-section${row.id === "more" ? " is-compact" : ""}`} key={row.id} data-row={row.id} aria-label={row.title}>
      <div className="tv-sports-row-heading"><h3 title={row.id === "featured" ? translateUi("Ranked by competition priority and broadcast reach, not measured viewers") : undefined}>{row.title}</h3></div>
      <div className="tv-sports-row" onScroll={e => {
        const element = e.currentTarget;
        if (element.scrollWidth - element.clientWidth - Math.abs(element.scrollLeft) < 600 && (rowLimits[row.id] ?? 16) < row.events.length)
          setRowLimits(current => ({ ...current, [row.id]: Math.min(row.events.length, (current[row.id] ?? 16) + 12) }));
      }}>{row.events.slice(0, rowLimits[row.id] ?? 16).map((event, index) => {
        const sport = guideSports.find((s) => s.id === event.sportId)!;
        const scheduleOnly = row.id.endsWith("-schedule");
        return <button type="button" className={`tv-event-card${scheduleOnly ? " is-schedule" : ""}`} key={event.id} onFocus={() => {
          onEnter();
          if (focusedOrder?.row !== row.id) setFocusedOrder({ row: row.id, ids: row.events.map(e => e.id) });
          if (index >= (rowLimits[row.id] ?? 16) - 3 && (rowLimits[row.id] ?? 16) < row.events.length)
            setRowLimits(current => ({ ...current, [row.id]: Math.min(row.events.length, (current[row.id] ?? 16) + 12) }));
        }}
          onKeyDown={(key) => {
            if (!["ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown"].includes(key.key)) return;
            key.preventDefault(); key.stopPropagation();
            const rtl = getComputedStyle(key.currentTarget).direction === "rtl";
            const backward = key.key === (rtl ? "ArrowRight" : "ArrowLeft");
            const horizontal = key.key === "ArrowLeft" || key.key === "ArrowRight";
            if (horizontal && backward && index === 0) { onOpenCategories(); return; }
            const nextRow = horizontal ? rowIndex : rowIndex + (key.key === "ArrowUp" ? -1 : 1);
            const section = root.current?.querySelectorAll(".tv-sports-row")[nextRow];
            const cards = section?.querySelectorAll<HTMLButtonElement>(".tv-event-card");
            const nextIndex = horizontal ? index + (backward ? -1 : 1) : Math.min(index, (cards?.length ?? 1) - 1);
            const next = cards?.[nextIndex];
            next?.focus({ preventScroll: true });
            next?.scrollIntoView({ block: "nearest", inline: "nearest", behavior: "instant" });
          }}
          onClick={(click) => { origin.current = click.currentTarget; setShowScore(false); setSelectedId(event.id); }}>
          {scheduleOnly ? <span className="tv-event-schedule-time">{stamp(event)}</span> : <div className="tv-event-art">{(nearRows.has(row.id) || rowIndex < 2) && <EventArtwork event={event} onUnavailable={() => setFailedArtwork(current => new Set([...current, event.id]))} />}<span className={`tv-event-stamp${isOnAir(event, now) ? " is-on-air" : ""}`}>{stamp(event)}</span></div>}
          <strong>{event.title}</strong><small className="tv-event-meta"><span className="tv-event-competition">{[sport.title, event.competition].filter(Boolean).join(" · ")}</span>{isOnAir(event, now) && <span><Tv size={16} />{sportsChannelSummary(event, now)}</span>}</small>
        </button>;
      })}</div>
    </section>)}
    <dialog ref={dialog} className="tv-event-picker" style={{ "--source-count": Math.max(1, sourceChannels.length) } as CSSProperties}
      onCancel={(event) => { event.preventDefault(); close(); }} onClick={(event) => { if (event.target === event.currentTarget) close(); }}
      // TV Back arrives as a synthetic Escape, which never fires "cancel".
      onKeyDown={(event) => { if (event.key === "Escape") { event.preventDefault(); event.stopPropagation(); close(); } }}>
      <header>{selected && !failedArtwork.has(selected.id) && (selected.artwork || selected.teamArtwork) && <div className="tv-event-picker-art"><EventArtwork event={selected} /></div>}<div><p>{selected ? `${stamp(selected)} · ${guideSports.find(s => s.id === selected.sportId)!.title}` : translateUi("This event is no longer in the available guide.")}</p><h2>{selected?.title ?? translateUi("Schedule changed")}</h2></div><button type="button" onClick={close} aria-label={translateUi("Close")}><X /></button></header>
      {selected?.fixture && <div className="tv-event-details"><span>{[selected.competition, selected.fixture.venue, selected.fixture.round ? `Round ${selected.fixture.round}` : undefined].filter(Boolean).join(" · ")}</span>
        {selected.fixture.homeScore !== undefined && selected.fixture.awayScore !== undefined && isConfirmedLive(selected, now) && <button type="button" className="secondary" onClick={() => setShowScore(value => !value)}>{showScore ? `${selected.fixture.homeScore} : ${selected.fixture.awayScore}` : translateUi("Show score")}</button>}</div>}
      <h3>{selected?.addonSources?.length ? translateUi("Sources") : selected && isOnAir(selected, now) ? translateUi("Channels") : translateUi("Scheduled channels")}<span>{selected ? sportsChannelSummary(selected, now) : translateUi("No channels")}</span></h3>
      {!sourceChannels.length && !selected?.addonSources?.length && <p className="tv-event-no-channels">{translateUi("No sources found yet.")}</p>}
      {sourceChannels.length > 0 && <VirtualList items={sourceChannels} estimate={72} itemKey={(ch) => ch.id} label={translateUi("Available channels")} renderItem={(ch) =>
        <button type="button" className="tv-event-source" disabled={!selected || !isOnAir(selected, now)} onClick={() => {
          if (selected && accessibleIds.has(ch.id) && isOnAir(selected, Date.now()) && (availableEventChannels(selected, Date.now()).some(channel => channel.id === ch.id) || possibleChannels.some(channel => channel.id === ch.id))) { close(); onPlay(ch); }
        }}><span className="tv-source-logo-fallback"><ChannelLogo channel={ch} size={28} /></span><span><strong>{ch.name}</strong><small>{providerNames[channelPlaylistId(ch.id)] || ch.group}{possibleChannels.some(candidate => candidate.id === ch.id) ? translateUi(" · Possible broadcast") : translateUi(" · Guide match")}</small></span>{ch.qualityLabel && <em>{ch.qualityLabel}</em>}{ch.language && <em>{ch.language.toUpperCase()}</em>}<ChevronRight className="tv-source-arrow" size={22} /><Play className="tv-source-play" size={22} /></button>} />}
      {selected?.addonSources?.map(source => <AddonSourceRow key={`${selected.id}:${source.key}`} event={source} addons={addons} canPlay={isOnAir(selected, now) && (source.startsAt === undefined || source.startsAt <= now)} onPlay={stream => {
        if (!addons.some(a => a.enabled !== false && a.manifestUrl === source.installation)) return;
        if (stream.external) { if (premiumAllows("external")) window.open(stream.url, "_blank", "noopener,noreferrer"); }
        else onPlay({ id: `sports-addon:${source.addonId}:${source.type}:${source.eventId}`, name: source.title, group: source.addonName, streamUrl: stream.url, requestHeaders: stream.headers });
        close();
      }} />)}
    </dialog>
  </section>;
}

function AddonSourceRow({ event, addons, canPlay, onPlay }: { event: SportsAddonEvent; addons: InstalledAddon[]; canPlay: boolean; onPlay: (stream: SportsAddonStream) => void }) {
  const translateUi = useTranslation();
  const [sources, setSources] = useState<SportsAddonStream[] | null>(null);
  const [loading, setLoading] = useState(false);
  const request = useRef<AbortController | null>(null);
  useEffect(() => () => request.current?.abort(), []);
  const load = async () => {
    request.current?.abort();
    const controller = new AbortController(); request.current = controller;
    setLoading(true);
    try { const result = await resolveSportsAddon(event, addons, controller.signal); if (!controller.signal.aborted) setSources(result); }
    catch { if (!controller.signal.aborted) setSources([]); }
    finally { if (!controller.signal.aborted) setLoading(false); }
  };
  return <div>
    <button type="button" className="tv-event-source" aria-busy={loading} onClick={() => { if (!loading) void load(); }}>
      <Tv size={24} /><span><strong>{event.addonName}</strong><small>{loading ? translateUi("Loading…") : sources?.length === 0 ? `${translateUi("No sources found yet.")} ${translateUi("Retry")}` : translateUi("Addons")}</small></span><ChevronRight size={22} />
    </button>
    {sources?.map((stream, i) => <button type="button" className="tv-event-source" key={i} disabled={loading || !canPlay && !stream.external} onClick={() => onPlay(stream)}>
      <Play size={20} /><span><strong>{stream.name}</strong><small>{[event.addonName, stream.description].filter(Boolean).join(" · ")}{stream.external ? " ↗" : ""}</small></span>
    </button>)}
  </div>;
}


// Ask TheSportsDB for a resized copy first; fall back to the original if the
// resized one is missing.
function useSizedImage(url: string | undefined, size: "medium" | "small") {
  const [fallback, setFallback] = useState<string | null>(null);
  const sized = sizedSportsImage(url, size);
  const src = fallback === url ? url : sized;
  return { src, canFallBack: src !== url, fallBack: () => setFallback(url ?? null) };
}

function EventArtwork({ event, onUnavailable }: { event: SportsGuideEvent; onUnavailable?: () => void }) {
  const translateUi = useTranslation();
  const banner = useSizedImage(event.artwork, "medium");
  const home = useSizedImage(event.teamArtwork?.homeBadge, "small");
  const away = useSizedImage(event.teamArtwork?.awayBadge, "small");
  const [loadedUrl, setLoadedUrl] = useState<string | null>(null);
  const [badges, setBadges] = useState<string[]>([]);
  const loaded = Boolean(event.artwork) && loadedUrl === event.artwork;
  const pair = event.teamArtwork;
  const [bannerFailed, setBannerFailed] = useState(false);
  const [pairFailed, setPairFailed] = useState(false);
  useEffect(() => { setBannerFailed(false); setPairFailed(false); }, [event.artwork, pair?.homeBadge, pair?.awayBadge]);
  useEffect(() => { if ((!event.artwork || bannerFailed) && (!pair || pairFailed)) onUnavailable?.(); }, [event.artwork, bannerFailed, pair, pairFailed, onUnavailable]);
  const pairLoaded = pair && badges.includes(pair.homeBadge) && badges.includes(pair.awayBadge);
  return <div className="tv-event-image">
    {!loaded && pair && <div className="tv-event-teams" style={{ opacity: pairLoaded ? 1 : 0 }}>
      <img src={home.src} alt={pair.homeTeam ?? ""} loading="lazy" decoding="async" onLoad={() => setBadges(current => [...current, pair.homeBadge])} onError={() => home.canFallBack ? home.fallBack() : setPairFailed(true)} />
      <strong>{translateUi("VS")}</strong>
      <img src={away.src} alt={pair.awayTeam ?? ""} loading="lazy" decoding="async" onLoad={() => setBadges(current => [...current, pair.awayBadge])} onError={() => away.canFallBack ? away.fallBack() : setPairFailed(true)} />
    </div>}
    {event.artwork && <img src={banner.src} alt="" loading="lazy" decoding="async" style={{ opacity: loaded ? 1 : 0 }} onLoad={() => setLoadedUrl(event.artwork!)} onError={() => { setLoadedUrl(null); if (banner.canFallBack) banner.fallBack(); else setBannerFailed(true); }} />}
  </div>;
}

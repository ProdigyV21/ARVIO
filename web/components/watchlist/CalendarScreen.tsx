"use client";

import { CalendarDays, ChevronLeft, ChevronRight } from "lucide-react";
import { useEffect, useMemo, useRef, useState, type CSSProperties, type KeyboardEvent } from "react";
import { useApp, authClient, traktClient } from "@/lib/store";
import { useTranslation } from "@/lib/i18n";
import { pullCloudWatchlist } from "@/lib/cloud";
import { applyPendingWatchlist } from "@/lib/watchlistOutbox";
import { simklClient } from "@/lib/simkl";
import { mdblistClient } from "@/lib/mdblist";
import { traktItemToMedia } from "@/lib/mappers";
import { getLogoUrl } from "@/lib/tmdb";
import { loadCalendar, type CalendarRead, type CalendarResult } from "@/lib/calendarLoader";
import { calendarConnectionFingerprint, mergeCalendarRefresh, readCalendarSnapshot, saveCalendarSnapshot } from "@/lib/calendarSnapshot";
import { CALENDAR_KIND_LABELS, CALENDAR_SOURCE_LABELS, calendarDate, calendarEpisodeLabel, calendarMonthDays, calendarTime, parseCalendarDate, shiftCalendarMonth, type CalendarRelease, type CalendarSource } from "@/lib/calendar";
import type { MediaItem } from "@/lib/types";

type SourceFilter = "all" | CalendarSource;
const views = new Map<string, { month: string; selected: string; source: SourceFilter }>();

function SourceMark({ source }: { source: CalendarSource }) {
  return <span className={`calendar-source-mark ${source}`} aria-hidden="true"><img src={source === "arvio" ? "/arvio-icon-192.png" : `/logos/calendar_${source}.${source === "trakt" ? "svg" : "png"}`} alt="" /></span>;
}

function ReleaseCard({ release, locale, onOpen }: { release: CalendarRelease; locale: string; onOpen: (item: MediaItem) => void }) {
  const translateUi = useTranslation();
  const [logo, setLogo] = useState<string | null>(null);
  useEffect(() => { let active = true; void getLogoUrl(release.item).then(value => { if (active) setLogo(value); }).catch(() => undefined); return () => { active = false; }; }, [release.item.id, release.item.mediaType]);
  return <button className="calendar-release-card" onClick={() => onOpen({ ...release.item, ...(release.kind === "episode" ? { seasonNumber: release.season, episodeNumber: release.episode, episodeTitle: release.episodeTitle } : {}) })}>
    <span className="calendar-release-art">{(release.artwork || release.item.backdrop || release.item.image) && <img src={release.artwork || release.item.backdrop || release.item.image} alt="" loading="lazy" />}{logo && <img className="calendar-release-logo" src={logo} alt="" loading="lazy" />}</span>
    <strong>{release.item.title}</strong>
    <span>{translateUi(CALENDAR_KIND_LABELS[release.kind])}{release.kind === "episode" ? ` · ${calendarEpisodeLabel(release)}` : ""} · {(release.timestamp ? calendarTime(release, locale) : translateUi("Time TBA"))}</span>
    <small>{release.sources.map(source => CALENDAR_SOURCE_LABELS[source]).join(" + ")}{release.region ? ` · ${release.region}` : ""}</small>
  </button>;
}

export function CalendarScreen() {
  const translateUi = useTranslation();
  const { watchlist, auth, activeProfile, traktConnected, simklConnected, mdblistConnected, settings, openDetails } = useApp();
  const scope = `${auth?.userId ?? "local"}:${activeProfile?.id ?? "default"}`;
  const saved = views.get(scope);
  const [month, setMonth] = useState(() => parseCalendarDate(saved?.month) ?? new Date());
  const [selected, setSelected] = useState(() => saved?.selected ?? calendarDate(new Date()));
  const [source, setSource] = useState<SourceFilter>(saved?.source ?? "all");
  const [loading, setLoading] = useState(true);
  const [retry, setRetry] = useState(0);
  const grid = useRef<HTMLDivElement>(null);
  const cards = useRef<HTMLDivElement>(null);
  const retryButton = useRef<HTMLButtonElement>(null);
  const focusDate = useRef<string | null>(null);
  const locale = settings.language || "en";
  const zone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  const days = useMemo(() => calendarMonthDays(month), [month]);
  const firstDay = calendarDate(days[0]);
  const lastDay = calendarDate(days[days.length - 1]);
  const sources = useMemo(() => (["arvio", ...(traktConnected ? ["trakt"] : []), ...(simklConnected ? ["simkl"] : []), ...(mdblistConnected ? ["mdblist"] : [])] as CalendarSource[]), [traktConnected, simklConnected, mdblistConnected]);
  let region = "US";
  try { region = new Intl.Locale(locale).region || (typeof navigator !== "undefined" ? new Intl.Locale(navigator.language).region : undefined) || "US"; } catch { /* Stable fallback for legacy locale codes. */ }
  const watchlistSignature = watchlist.map(item => `${item.mediaType}:${item.id}:${item.imdbId ?? ""}`).sort().join(",");
  const connections = calendarConnectionFingerprint([traktConnected ? traktClient.token?.access_token : null, simklConnected ? simklClient.token?.access_token : null, mdblistConnected ? mdblistClient.token?.accessToken || mdblistClient.key : null]);
  const snapshotKey = `${scope}:${connections}:${firstDay}:${lastDay}:${locale}:${region}:${zone}:${sources.join(",")}:${watchlistSignature}`;
  const cached = useMemo(() => readCalendarSnapshot(snapshotKey), [snapshotKey]);
  const [loaded, setLoaded] = useState<{ key: string; value: CalendarResult | null }>(() => ({ key: snapshotKey, value: cached }));
  const result = loaded.key === snapshotKey ? loaded.value : cached;
  useEffect(() => { if (source !== "all" && !sources.includes(source)) setSource("all"); }, [source, sources]);
  useEffect(() => { views.set(scope, { month: calendarDate(month), selected, source }); if (views.size > 12) views.delete(views.keys().next().value!); }, [scope, month, selected, source]);
  useEffect(() => {
    const controller = new AbortController();
    const initial = readCalendarSnapshot(snapshotKey);
    setLoading(true); setLoaded({ key: snapshotKey, value: initial });
    const sourceReads: CalendarRead[] = [{ source: "arvio", read: async () => auth?.userId && activeProfile?.id
      ? applyPendingWatchlist(authClient, activeProfile.id, await pullCloudWatchlist(authClient, activeProfile.id))
      : watchlist }];
    if (traktConnected) sourceReads.push({ source: "trakt", read: async () => (await traktClient.watchlist()).map(traktItemToMedia) });
    if (simklConnected) sourceReads.push({ source: "simkl", read: async () => (await simklClient.watchlist(["plantowatch", "watching"], { throwOnError: true })).map(traktItemToMedia) });
    if (mdblistConnected) sourceReads.push({ source: "mdblist", read: async () => (await mdblistClient.watchlist({ throwOnError: true })).map(traktItemToMedia) });
    const seasonTimes = new Map<string, ReturnType<typeof traktClient.calendarSeason>>();
    // Fetch a complete season once, retaining only its published first_aired values.
    const episodeTime = async (item: MediaItem, season: number, episode: number) => {
      if (controller.signal.aborted) return null;
      const key = `${item.id}:${season}`;
      let request = seasonTimes.get(key);
      if (!request) { request = traktClient.calendarSeason(item.id, season, item.traktId); seasonTimes.set(key, request); }
      return (await request).find(row => row.season === season && row.number === episode)?.first_aired;
    };
    // Padding handles UTC air times that land on a neighbouring local day.
    const start = parseCalendarDate(firstDay)!; start.setDate(start.getDate() - 1);
    const end = parseCalendarDate(lastDay)!; end.setDate(end.getDate() + 1);
    void loadCalendar({ sources: sourceReads, start: calendarDate(start), end: calendarDate(end), language: locale, region, customApiKey: settings.customTmdbApiKey, signal: controller.signal, episodeTime, onProgress: value => { if (!controller.signal.aborted) setLoaded({ key: snapshotKey, value: mergeCalendarRefresh(initial, value) }); } })
      .then(value => { if (!controller.signal.aborted) { setLoaded({ key: snapshotKey, value: mergeCalendarRefresh(initial, value) }); saveCalendarSnapshot(snapshotKey, value); } })
      .catch(() => { if (!controller.signal.aborted) setLoaded({ key: snapshotKey, value: mergeCalendarRefresh(initial, { releases: [], failedSources: sources, pendingSources: [], failedTitles: 0, titleCount: 0, sourceTitleCounts: {} }) }); })
      .finally(() => { if (!controller.signal.aborted) setLoading(false); });
    return () => controller.abort();
  }, [scope, auth?.userId, activeProfile?.id, firstDay, lastDay, locale, region, settings.customTmdbApiKey, sources, snapshotKey, retry, traktConnected, simklConnected, mdblistConnected]);

  const releases = useMemo(() => (result?.releases ?? []).filter(row => source === "all" || row.sources.includes(source)), [result, source]);
  const byDay = useMemo(() => { const grouped = new Map<string, CalendarRelease[]>(); for (const row of releases) grouped.set(row.date, [...(grouped.get(row.date) ?? []), row]); return grouped; }, [releases]);
  const selectedReleases = byDay.get(selected) ?? [];
  const selectedDate = parseCalendarDate(selected)!;
  const selectedLoading = loading && (!result || (source === "all" ? result.pendingSources.length > 0 : result.pendingSources.includes(source)));
  const today = calendarDate(new Date());
  const monthPrefix = calendarDate(month).slice(0, 7);
  const nextRelease = releases.find(release => release.date > selected && release.date.startsWith(monthPrefix));
  const emptySource = result && (source === "all"
    ? !result.failedSources.length && Object.values(result.sourceTitleCounts).every(count => count === 0)
    : result.sourceTitleCounts[source] === 0);
  const unavailableSource = result && (source === "all" ? result.failedSources.length === sources.length : result.failedSources.includes(source));
  const moveMonth = (step: number) => {
    const next = new Date(month.getFullYear(), month.getMonth() + step, 1, 12);
    next.setDate(Math.min(selectedDate.getDate(), new Date(next.getFullYear(), next.getMonth() + 1, 0).getDate()));
    setMonth(next); setSelected(calendarDate(next));
  };
  const chooseDay = (date: Date, keyboard = false) => {
    const key = calendarDate(date);
    setSelected(key);
    if (date.getMonth() !== month.getMonth() || date.getFullYear() !== month.getFullYear()) setMonth(new Date(date.getFullYear(), date.getMonth(), 1, 12));
    if (keyboard) focusDate.current = key;
  };
  useEffect(() => { if (focusDate.current) { grid.current?.querySelector<HTMLButtonElement>(`[data-calendar-date="${focusDate.current}"]`)?.focus(); focusDate.current = null; } }, [selected, month]);
  const navigateDay = (event: KeyboardEvent<HTMLButtonElement>, date: Date) => {
    const rtl = getComputedStyle(event.currentTarget).direction === "rtl";
    const offsets: Record<string, number> = { ArrowLeft: rtl ? 1 : -1, ArrowRight: rtl ? -1 : 1, ArrowUp: -7, ArrowDown: 7, Home: -((date.getDay() + 6) % 7), End: 6 - ((date.getDay() + 6) % 7) };
    if (event.key in offsets) { event.preventDefault(); event.stopPropagation(); const next = new Date(date); next.setDate(date.getDate() + offsets[event.key]); chooseDay(next, true); }
    else if (event.key === "PageUp" || event.key === "PageDown") { event.preventDefault(); event.stopPropagation(); chooseDay(shiftCalendarMonth(date, event.key === "PageUp" ? -1 : 1), true); }
    else if (event.key === "Enter") {
      const target = cards.current?.querySelector<HTMLButtonElement>("button") ?? (retryButton.current?.disabled ? null : retryButton.current);
      if (target) { event.preventDefault(); event.stopPropagation(); target.focus(); }
    }
  };
  const returnToDay = (event: KeyboardEvent) => {
    if (event.key === "Escape" || event.key === "ArrowUp") { event.preventDefault(); event.stopPropagation(); grid.current?.querySelector<HTMLButtonElement>(`[data-calendar-date="${selected}"]`)?.focus(); }
  };
  return <section className="library-calendar" style={{ "--calendar-weeks": days.length / 7 } as CSSProperties} aria-label={translateUi("Release calendar")}>
    <div className="calendar-toolbar">
      <div className="calendar-month-controls"><button aria-label={translateUi("Previous month")} onClick={() => moveMonth(-1)}><ChevronLeft /></button><h1>{month.toLocaleDateString(locale, { month: "long", year: "numeric" })}</h1><button aria-label={translateUi("Next month")} onClick={() => moveMonth(1)}><ChevronRight /></button></div>
      <select value={source} onChange={event => setSource(event.target.value as SourceFilter)} aria-label={translateUi("Calendar watchlist source")}><option value="all">{translateUi("All watchlists")}</option>{sources.map(id => <option key={id} value={id}>{CALENDAR_SOURCE_LABELS[id]}</option>)}</select>
      {source === "all" && <div className="calendar-source-legend">{sources.map(id => <span key={id} aria-label={CALENDAR_SOURCE_LABELS[id]}><SourceMark source={id} />{id !== "simkl" && CALENDAR_SOURCE_LABELS[id]}</span>)}</div>}
    </div>
    <div className="calendar-weekdays" aria-hidden="true">{days.slice(0, 7).map(day => <span key={day.getDay()}><span className="calendar-weekday-full">{day.toLocaleDateString(locale, { weekday: "long" })}</span><span className="calendar-weekday-short">{day.toLocaleDateString(locale, { weekday: "short" })}</span></span>)}</div>
    <div className="calendar-month-grid" ref={grid} role="grid" aria-label={month.toLocaleDateString(locale, { month: "long", year: "numeric" })} aria-busy={selectedLoading}>
      {Array.from({ length: days.length / 7 }, (_, week) => <div className="calendar-week" role="row" key={week}>{days.slice(week * 7, week * 7 + 7).map(day => {
        const key = calendarDate(day); const entries = byDay.get(key) ?? []; const active = key === selected; const art = entries[0]?.artwork || entries[0]?.item.backdrop || entries[0]?.item.image;
        return <div role="gridcell" aria-selected={active} key={key}><button data-calendar-date={key} aria-current={key === today ? "date" : undefined} tabIndex={active ? 0 : -1} className={`calendar-day ${active ? "is-selected" : ""} ${entries.length ? "has-releases" : ""} ${entries.length > 1 ? "has-multiple" : ""} ${day.getMonth() !== month.getMonth() ? "outside-month" : ""} ${key === today ? "is-today" : ""}`} aria-label={`${day.toLocaleDateString(locale, { weekday: "long", day: "numeric", month: "long" })}${entries.length || !selectedLoading ? `, ${translateUi("{value0} releases", { value0: entries.length })}` : ""}${entries.length ? `: ${entries.slice(0, 3).map(row => `${row.item.title}, ${calendarEpisodeLabel(row) || translateUi(CALENDAR_KIND_LABELS[row.kind])}, ${(row.timestamp ? calendarTime(row, locale) : translateUi("Time TBA"))}`).join("; ")}` : ""}`} onClick={() => chooseDay(day)} onFocus={() => setSelected(key)} onKeyDown={event => navigateDay(event, day)}>
          {!active && art && <img className="calendar-day-art" src={art} alt="" loading="lazy" />}
          {active && art && <img className="calendar-day-selected-art" src={art} alt="" loading="lazy" />}
          <span className="calendar-day-number">{day.getDate()}</span>
          {entries.length > 1 && <span className="calendar-day-posters" aria-hidden="true">{entries.slice(0, 3).map(row => <span className="calendar-day-poster" key={row.id}><span>{row.item.title.slice(0, 1)}</span>{(row.item.image || row.artwork || row.item.backdrop) && <img src={row.item.image || row.artwork || row.item.backdrop || undefined} alt="" loading="lazy" />}</span>)}{entries.length > 3 && <small className="calendar-poster-more">+{entries.length - 3}</small>}</span>}
          <span className="calendar-day-entries">{entries.slice(0, active ? 2 : 1).map(row => <span className="calendar-day-entry" key={row.id}><span><strong>{row.item.title}</strong><small>{row.kind === "episode" ? calendarEpisodeLabel(row) : translateUi(CALENDAR_KIND_LABELS[row.kind]).replace(/ release$/, "")} · {(row.timestamp ? calendarTime(row, locale) : translateUi("Time TBA"))}</small></span>{active && (row.artwork || row.item.backdrop) && <img src={row.artwork || row.item.backdrop || undefined} alt="" loading="lazy" />}</span>)}{entries.length > (active ? 2 : 1) && <small className="calendar-more">{translateUi("+{value0} more", { value0: entries.length - (active ? 2 : 1) })}</small>}</span>
          {entries.length > 0 && <span className="calendar-day-count" aria-hidden="true">{entries.length}</span>}
        </button></div>;
      })}</div>)}
    </div>
    {selectedLoading && <p className="calendar-loading-announcement" role="status">{translateUi("Loading watchlist releases…")}</p>}
    {result && (result.failedSources.length > 0 || result.failedTitles > 0) && <div className="calendar-status calendar-partial" role="status"><span>{result.failedSources.length ? `${translateUi("Could not load")}: ${result.failedSources.map(id => CALENDAR_SOURCE_LABELS[id]).join(", ")}. ` : ""}{result.failedTitles > 0 ? translateUi("Some release details unavailable") : ""}</span><button ref={retryButton} disabled={loading} onKeyDown={returnToDay} onClick={() => setRetry(value => value + 1)}>{translateUi("Retry")}</button></div>}
    <div className="calendar-selected-heading"><h2>{selectedDate.toLocaleDateString(locale, { weekday: "long", day: "numeric", month: "long" })}</h2><span>{selectedLoading && !selectedReleases.length ? translateUi("Finding your releases…") : translateUi("{value0} releases", { value0: selectedReleases.length })}</span></div>
    <div className="calendar-release-row" ref={cards} onKeyDown={returnToDay}>
      {selectedReleases.map(release => <ReleaseCard key={release.id} release={release} locale={locale} onOpen={openDetails} />)}
      {!selectedReleases.length && !selectedLoading && <div className="calendar-empty"><CalendarDays size={24} /><span>{unavailableSource ? translateUi("Calendar unavailable") : emptySource ? (source === "all" ? translateUi("Add movies and series to your watchlists to see their releases here.") : translateUi("Add movies and series to this watchlist to see their releases here.")) : translateUi("No scheduled releases for this day.")}</span>{nextRelease && <button className="calendar-next-release" onClick={() => chooseDay(parseCalendarDate(nextRelease.date)!, true)}>{translateUi("Next release")}</button>}</div>}
    </div>
  </section>;
}

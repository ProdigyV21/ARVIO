"use client";
import { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import { Check, ChevronDown, History, LoaderCircle, RotateCw, Search, User, X } from 'lucide-react';
import { useTranslation } from '@/lib/i18n';
import { useAppPick } from '@/lib/store';
import { useKidsFilter } from '@/lib/useKids';
import { loadStored, saveStored } from '@/lib/storage';
import { discoverTitlesPage, getPersonDetails, searchTitlesPage, tmdb, mapTmdbItem, type SearchPerson } from '@/lib/tmdb';
import {
  DISCOVER_LANGUAGES, EMPTY_FILTERS, RATING_FLOORS, decadeOptions, genresFor, hasDiscoverFilters, recentSearchesKey,
  remapGenreForType, withRecentSearch, type DiscoverFilters, type SearchType, type SortKey
} from '@/lib/searchModel';
import type { Category, MediaItem } from '@/lib/types';
import { MediaCard } from '@/components/media/MediaCard';
import { MediaRail } from '@/components/media/MediaRail';
import '@/app/search.css';

type Picker = 'genre' | 'rating' | 'decade' | 'language' | 'sort';
type Results = { movies: MediaItem[]; shows: MediaItem[]; people: SearchPerson[]; items: MediaItem[]; total: number; hasMore: boolean };
const EMPTY_RESULTS: Results = { movies: [], shows: [], people: [], items: [], total: 0, hasMore: false };

function readFilters(): DiscoverFilters {
  if (typeof window === 'undefined') return EMPTY_FILTERS;
  const params = new URL(location.href).searchParams;
  const type = params.get('searchType');
  const sort = params.get('sort');
  return {
    type: type === 'movie' || type === 'tv' ? type : 'all',
    genre: params.get('genre') || '',
    rating: params.get('rating') || '',
    decade: params.get('decade') || '',
    language: params.get('lang') || '',
    sort: (sort === 'vote_average.desc' || sort === 'release.desc' ? sort : 'popularity.desc') as SortKey
  };
}

function usePhone() {
  const [phone, setPhone] = useState(false);
  useEffect(() => {
    const query = window.matchMedia('(max-width: 680px)');
    const update = () => setPhone(query.matches);
    update();
    query.addEventListener?.('change', update);
    return () => query.removeEventListener?.('change', update);
  }, []);
  return phone;
}

// Discover rows for an empty query, loaded once per language per session.
const discoverRowsCache = new Map<string, Promise<Category[]>>();
function loadDiscoverRows(language: string) {
  const cached = discoverRowsCache.get(language);
  if (cached) return cached;
  type List = { results: Array<Parameters<typeof mapTmdbItem>[0]> };
  const row = (id: string, title: string, path: string, type: 'movie' | 'tv' | 'mixed', params: Record<string, string> = {}) =>
    tmdb<List>(path, { language, ...params }).then((response) => ({
      id, title,
      items: response.results.filter((item) => type !== 'mixed' || item.media_type === 'movie' || item.media_type === 'tv')
        .map((item) => mapTmdbItem(item, type === 'tv' || item.media_type === 'tv' ? 'tv' : 'movie'))
    })).catch(() => null);
  const task = Promise.all([
    row('search_trending', 'Trending this week', 'trending/all/week', 'mixed'),
    row('search_popular_movies', 'Popular movies', 'movie/popular', 'movie'),
    row('search_popular_tv', 'Popular series', 'tv/popular', 'tv'),
    row('search_top_movies', 'Top rated movies', 'movie/top_rated', 'movie')
  ]).then((rows) => rows.filter((value): value is Category => Boolean(value?.items.length)));
  task.then((rows) => { if (!rows.length) discoverRowsCache.delete(language); }, () => discoverRowsCache.delete(language));
  discoverRowsCache.set(language, task);
  return task;
}

export function SearchScreen() {
  const translateUi = useTranslation();
  const { query, setQuery, openDetails, settings, activeProfile } = useAppPick("query", "setQuery", "openDetails", "settings", "activeProfile");
  const kids = useKidsFilter();
  const phone = usePhone();
  const posterMode = settings.cardLayoutMode === 'poster' || phone;
  const [filters, setFilters] = useState<DiscoverFilters>(readFilters);
  const [results, setResults] = useState<Results>(EMPTY_RESULTS);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [retry, setRetry] = useState(0);
  const [picker, setPicker] = useState<Picker | null>(null);
  const [person, setPerson] = useState<{ person: SearchPerson; items: MediaItem[] | null } | null>(null);
  const [discoverRows, setDiscoverRows] = useState<Category[] | null>(null);
  const generation = useRef(0);
  const input = useRef<HTMLInputElement>(null);
  const firstChip = useRef<HTMLButtonElement>(null);
  const searching = Boolean(query.trim());
  const filtered = hasDiscoverFilters(filters) || filters.type !== 'all';
  const showRows = !searching && !filtered;

  // Recent searches (Android RecentSearchRepository): per profile, newest first.
  const recentKey = recentSearchesKey(activeProfile?.id ?? '');
  const [recent, setRecent] = useState<string[]>(() => loadStored<string[]>(recentKey, []));
  useEffect(() => { setRecent(loadStored<string[]>(recentKey, [])); }, [recentKey]);
  const remember = useCallback((value: string) => {
    setRecent((current) => {
      const next = withRecentSearch(current, value);
      if (next !== current) saveStored(recentKey, next);
      return next;
    });
  }, [recentKey]);
  const clearRecent = () => { setRecent([]); saveStored(recentKey, []); };

  // Filters live in the URL next to the query (?q=, kept by BackHandler).
  useEffect(() => {
    const url = new URL(location.href);
    const entries: Record<string, string> = { searchType: filters.type === 'all' ? '' : filters.type, genre: filters.genre, rating: filters.rating,
      decade: filters.decade, lang: filters.language, sort: filters.sort === 'popularity.desc' ? '' : filters.sort };
    for (const [key, value] of Object.entries(entries)) { if (value) url.searchParams.set(key, value); else url.searchParams.delete(key); }
    url.searchParams.delete('year');
    history.replaceState(history.state, '', url);
  }, [filters]);

  // "/" focuses the search field (desktop keyboards), unless typing elsewhere.
  useEffect(() => {
    const onKey = (event: KeyboardEvent) => {
      if (event.key !== '/' || event.ctrlKey || event.metaKey || event.altKey) return;
      const target = event.target as HTMLElement | null;
      if (target && (/^(INPUT|TEXTAREA|SELECT)$/.test(target.tagName) || target.isContentEditable)) return;
      event.preventDefault();
      input.current?.focus();
    };
    window.addEventListener('keydown', onKey);
    return () => window.removeEventListener('keydown', onKey);
  }, []);

  useEffect(() => {
    if (!showRows || discoverRows) return;
    let active = true;
    void loadDiscoverRows(settings.language).then((rows) => { if (active) setDiscoverRows(rows); });
    return () => { active = false; };
  }, [showRows, discoverRows, settings.language]);

  const fetchPage = useCallback(async (target: number): Promise<Results> => {
    if (searching) {
      const result = await searchTitlesPage({ query, type: filters.type }, settings.language, target);
      return { ...result, items: [...result.movies, ...result.shows] };
    }
    const result = await discoverTitlesPage(filters, settings.language, target);
    return { movies: [], shows: [], people: [], ...result };
  }, [searching, query, filters, settings.language]);

  const signature = JSON.stringify({ query: query.trim(), filters, language: settings.language });
  useEffect(() => {
    const id = ++generation.current;
    setPage(1); setResults(EMPTY_RESULTS); setError(false); setPerson(null);
    if (showRows) { setLoading(false); return undefined; }
    setLoading(true);
    const timer = setTimeout(() => {
      void fetchPage(1).then((result) => { if (generation.current === id) setResults(result); })
        .catch(() => { if (generation.current === id) setError(true); })
        .finally(() => { if (generation.current === id) setLoading(false); });
    }, searching ? 260 : 0);
    return () => { clearTimeout(timer); generation.current++; };
    // The signature covers every request parameter.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [signature, retry, showRows]);

  const loadMore = async () => {
    const id = generation.current;
    setLoading(true); setError(false);
    try {
      const next = await fetchPage(page + 1);
      if (id !== generation.current) return;
      const merge = <T extends { id: number; mediaType?: string }>(a: T[], b: T[]) => {
        const seen = new Set(a.map((item) => `${item.mediaType}:${item.id}`));
        return [...a, ...b.filter((item) => !seen.has(`${item.mediaType}:${item.id}`))];
      };
      setResults((previous) => ({ movies: merge(previous.movies, next.movies), shows: merge(previous.shows, next.shows),
        people: merge(previous.people, next.people), items: merge(previous.items, next.items), total: next.total || previous.total, hasMore: next.hasMore }));
      setPage(page + 1);
    } catch { if (id === generation.current) setError(true); }
    finally { if (id === generation.current) setLoading(false); }
  };

  const openPerson = (value: SearchPerson) => {
    setPerson({ person: value, items: null });
    void getPersonDetails(value.id, settings.language).then((details) =>
      setPerson((current) => current?.person.id === value.id ? { person: value, items: details?.knownFor ?? [] } : current));
  };
  const open = (item: MediaItem) => {
    if (searching) remember(query);
    void openDetails(item);
  };
  const update = (patch: Partial<DiscoverFilters>) => setFilters((current) => {
    const next = { ...current, ...patch };
    if (patch.type && patch.type !== current.type) next.genre = remapGenreForType(current.genre, patch.type);
    return next;
  });
  const reset = () => { setFilters(EMPTY_FILTERS); setQuery(''); input.current?.focus(); };

  const visible = { movies: kids.filter(results.movies), shows: kids.filter(results.shows), items: kids.filter(results.items) };
  const decades = useMemo(() => decadeOptions(new Date().getFullYear()), []);
  const genreList = genresFor(filters.type);
  const genreName = genreList.find((genre) => String(genre.id) === filters.genre)?.label;
  const decade = decades.find((entry) => `${entry.start}-${entry.end}` === filters.decade);
  const decadeLabel = decade ? (decade.end - decade.start === 9 ? `${decade.start}s` : translateUi('Before {value0}', { value0: decade.end + 1 })) : '';
  const languageName = DISCOVER_LANGUAGES.find((entry) => entry.code === filters.language)?.label;
  const sortLabel = (sort: SortKey) => sort === 'vote_average.desc' ? translateUi('Top rated') : sort === 'release.desc' ? translateUi('Newest') : translateUi('Popular');
  const pickerTitle = (id: Picker) => id === 'genre' ? translateUi('Genre') : id === 'rating' ? translateUi('Rating') : id === 'decade' ? translateUi('Year') : id === 'language' ? translateUi('Language') : translateUi('Sort');
  const rowTitle = (id: string, fallback: string) => id === 'search_trending' ? translateUi('Trending this week') : id === 'search_popular_movies' ? translateUi('Popular movies')
    : id === 'search_popular_tv' ? translateUi('Popular series') : id === 'search_top_movies' ? translateUi('Top rated movies') : fallback;
  const count = results.total;

  const grid = (items: MediaItem[]) => <div className={`search-grid ${posterMode ? 'is-poster' : ''}`}>
    {items.map((item) => <MediaCard key={`${item.mediaType}:${item.id}`} item={item} onOpen={open} posterMode={posterMode} />)}
  </div>;
  const skeleton = <div className={`search-grid ${posterMode ? 'is-poster' : ''}`} aria-hidden>
    {Array.from({ length: phone ? 9 : 12 }).map((_, index) => <div key={index} className="search-skeleton-card"><span /><i /><i /></div>)}
  </div>;

  const chip = (id: Picker, label: string, value: string | undefined, ref?: React.Ref<HTMLButtonElement>) =>
    <button ref={ref} type="button" className={`search-chip ${value ? 'is-set' : ''}`} aria-haspopup="dialog" aria-expanded={picker === id}
      id={`search-chip-${id}`} onClick={() => setPicker(picker === id ? null : id)}>
      {value ? <><span className="search-chip-label">{label}:</span> {value}</> : label}<ChevronDown size={16} aria-hidden />
    </button>;

  return <div className={`screen has-search-hero search-screen ${posterMode ? 'poster-results' : ''}`}>
    <section className="search-hero">
      <span className="search-icon-shell"><Search size={28} aria-hidden /></span>
      <div className="search-field">
        <input ref={input} type="search" value={query} enterKeyHint="search"
          onChange={(event) => setQuery(event.target.value)}
          onKeyDown={(event) => {
            if (event.key === 'Enter' && results.items.length) remember(query);
            if (event.key === 'Escape') {
              // Keep the user in the field: clear it first; an empty field
              // hands focus to the filters (remotes still need a way out).
              event.preventDefault();
              if (query) setQuery('');
              else firstChip.current?.focus();
            }
          }}
          placeholder={translateUi('Search titles and people')} aria-label={translateUi('Search movies, series and people')} />
        {query && <button type="button" className="search-clear" aria-label={translateUi('Clear search')} onClick={() => { setQuery(''); input.current?.focus(); }}><X size={20} /></button>}
      </div>
    </section>

    <div className="search-chips" role="toolbar" aria-label={translateUi('Filters')}>
      <div className="search-type" role="radiogroup" aria-label={translateUi('Type')}>
        {(['all', 'movie', 'tv'] as SearchType[]).map((type, index) => <button key={type} ref={index === 0 ? firstChip : undefined} type="button" role="radio"
          aria-checked={filters.type === type} className={filters.type === type ? 'is-active' : ''} onClick={() => update({ type })}>
          {type === 'all' ? translateUi('All') : type === 'movie' ? translateUi('Movies') : translateUi('Series')}
        </button>)}
      </div>
      {/* TMDB search takes no genre/rating filters: they shape Discover only. */}
      {!searching && <>
        {chip('genre', translateUi('Genre'), genreName && translateUi(genreName))}
        {chip('rating', translateUi('Rating'), filters.rating && `${filters.rating}+`)}
        {chip('decade', translateUi('Year'), decadeLabel)}
        {chip('language', translateUi('Language'), languageName && translateUi(languageName))}
        {chip('sort', translateUi('Sort'), filters.sort !== 'popularity.desc' ? sortLabel(filters.sort) : undefined)}
      </>}
      {(searching || filtered) && <button type="button" className="search-chip search-reset" onClick={reset}>{translateUi('Reset filters')}</button>}
    </div>

    {picker && <PickerPanel title={pickerTitle(picker)}
      onClose={() => { setPicker(null); document.getElementById(`search-chip-${picker}`)?.focus(); }}
      options={picker === 'genre' ? [{ value: '', label: translateUi('All genres') }, ...genreList.map((genre) => ({ value: String(genre.id), label: translateUi(genre.label) }))]
        : picker === 'rating' ? [{ value: '', label: translateUi('Any rating') }, ...RATING_FLOORS.map((value) => ({ value: String(value), label: `${value}+` }))]
        : picker === 'decade' ? [{ value: '', label: translateUi('Any year') }, ...decades.map((entry) => ({ value: `${entry.start}-${entry.end}`,
          label: entry.end - entry.start === 9 ? `${entry.start}s` : translateUi('Before {value0}', { value0: entry.end + 1 }) }))]
        : picker === 'language' ? [{ value: '', label: translateUi('Any language') }, ...DISCOVER_LANGUAGES.map((entry) => ({ value: entry.code, label: translateUi(entry.label) }))]
        : (['popularity.desc', 'vote_average.desc', 'release.desc'] as SortKey[]).map((value) => ({ value, label: sortLabel(value) }))}
      selected={picker === 'genre' ? filters.genre : picker === 'rating' ? filters.rating : picker === 'decade' ? filters.decade : picker === 'language' ? filters.language : filters.sort}
      onSelect={(value) => update(picker === 'sort' ? { sort: value as SortKey } : { [picker === 'decade' ? 'decade' : picker === 'language' ? 'language' : picker]: value })} />}

    {showRows && recent.length > 0 && <section className="search-recent" aria-label={translateUi('Recent searches')}>
      <h2><History size={18} aria-hidden />{translateUi('Recent searches')}</h2>
      <div className="search-recent-list">
        {recent.map((entry) => <button key={entry} type="button" className="search-chip" onClick={() => { setQuery(entry); input.current?.focus(); }}>{entry}</button>)}
        <button type="button" className="search-chip search-reset" onClick={clearRecent}>{translateUi('Clear')}</button>
      </div>
    </section>}

    {showRows ? <div className="search-discover-rows">
      {discoverRows === null ? skeleton : discoverRows.map((row) => <MediaRail key={row.id} category={{ ...row, title: rowTitle(row.id, row.title) }} onOpen={open} />)}
    </div> : <>
      <div className="search-results-head">
        <h2 className="search-results-title">{searching ? translateUi('Search results') : translateUi('Discover')}</h2>
        {!loading && !error && count > 0 && <span className="search-count" aria-live="polite">
          {count === 1 ? translateUi('{value0} result', { value0: 1 }) : translateUi('{value0} results', { value0: count.toLocaleString(settings.language) })}
        </span>}
      </div>
      {error && <div className="library-error" role="alert">{translateUi('Search is temporarily unavailable. Please try again.')} <button className="secondary" onClick={() => results.items.length ? void loadMore() : setRetry((value) => value + 1)}><RotateCw size={16} aria-hidden />{translateUi('Retry')}</button></div>}
      {loading && !results.items.length && !results.people.length && skeleton}
      {!loading && !error && !visible.items.length && !results.people.length && <div className="watchlist-empty"><Search size={34} /><p>{translateUi('No matching titles')}</p></div>}

      {person && <section className="search-section search-person-titles">
        <div className="search-section-head"><h3>{translateUi('Titles with {value0}', { value0: person.person.name })}</h3>
          <button type="button" className="search-chip" onClick={() => setPerson(null)}><X size={16} aria-hidden />{translateUi('Close')}</button></div>
        {person.items === null ? skeleton : grid(kids.filter(person.items))}
      </section>}
      {searching && filters.type === 'all' ? <>
        {results.people.length > 0 && <section className="search-section">
          <h3>{translateUi('People')}</h3>
          <div className="search-people">{results.people.map((entry) => <button key={entry.id} type="button" className="search-person" onClick={() => openPerson(entry)}>
            <span className="search-person-photo">{entry.image ? <img src={entry.image.replace('/w780/', '/w185/')} alt="" loading="lazy" /> : <User size={28} aria-hidden />}</span>
            <strong>{entry.name}</strong>{entry.knownFor && <small>{entry.knownFor}</small>}
          </button>)}</div>
        </section>}
        {visible.shows.length > 0 && <section className="search-section"><h3>{translateUi('Series')}</h3>{grid(visible.shows)}</section>}
        {visible.movies.length > 0 && <section className="search-section"><h3>{translateUi('Movies')}</h3>{grid(visible.movies)}</section>}
      </> : visible.items.length > 0 && grid(visible.items)}
      <div className="search-pagination">{loading && results.items.length ? <span role="status"><LoaderCircle size={20} className="spin" />{translateUi('Loading')}</span>
        : results.hasMore && !error && <button className="secondary" onClick={() => void loadMore()}>{translateUi('Load more')}</button>}</div>
    </>}
  </div>;
}

/**
 * A button picker that a TV remote can drive: a dialog of option buttons
 * (tvNav keeps arrows inside [role=dialog]); Enter picks, Escape/Back closes.
 * Replaces native <select>s, whose arrows changed the value instead of moving.
 */
function PickerPanel({ title, options, selected, onSelect, onClose }: {
  title: string; options: Array<{ value: string; label: string }>; selected: string;
  onSelect: (value: string) => void; onClose: () => void;
}) {
  const translateUi = useTranslation();
  const panel = useRef<HTMLDivElement>(null);
  const close = useRef(onClose);
  close.current = onClose;
  useEffect(() => {
    const target = panel.current?.querySelector<HTMLButtonElement>('[aria-checked="true"]') ?? panel.current?.querySelector<HTMLButtonElement>('button');
    target?.focus({ preventScroll: true });
    const outside = (event: PointerEvent) => {
      if (panel.current && !panel.current.contains(event.target as Node) && !(event.target as HTMLElement)?.closest?.('.search-chip')) close.current();
    };
    document.addEventListener('pointerdown', outside);
    return () => document.removeEventListener('pointerdown', outside);
  }, []);
  return <div ref={panel} className="search-picker" role="dialog" aria-label={title}
    onKeyDown={(event) => { if (event.key === 'Escape') { event.preventDefault(); event.stopPropagation(); onClose(); } }}>
    <div className="search-picker-head"><strong>{title}</strong>
      <button type="button" className="search-picker-close" aria-label={translateUi('Close')} onClick={onClose}><X size={18} /></button></div>
    <div className="search-picker-options" role="radiogroup" aria-label={title}>
      {options.map((option) => <button key={option.value || 'any'} type="button" role="radio" aria-checked={option.value === selected}
        className={option.value === selected ? 'is-selected' : ''} onClick={() => { onSelect(option.value); onClose(); }}>
        {option.value === selected && <Check size={16} aria-hidden />}{option.label}
      </button>)}
    </div>
  </div>;
}

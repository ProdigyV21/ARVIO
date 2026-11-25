import { config } from "./config";
import { tmdb, mapTmdbItem, resolveTmdbId } from "./tmdb";
import { authoritativeEpisodeTime, mergeCalendarTitles, parseCalendarDate, sortCalendarReleases, type CalendarRelease, type CalendarSource, type CalendarTitle, type ReleaseKind } from "./calendar";
import type { MediaItem } from "./types";
import { persistCalendarTitles, readCalendarTitle, rememberCalendarTitle } from "./calendarTitleCache";

interface Episode { season_number: number; episode_number: number; air_date?: string; name?: string; still_path?: string }
interface Season { id: number; season_number: number; air_date?: string; episode_count?: number }
interface ReleaseDate { type: number; release_date?: string }
interface Details {
  id: number; title?: string; name?: string; poster_path?: string; backdrop_path?: string; adult?: boolean; release_date?: string; status?: string;
  seasons?: Season[]; last_episode_to_air?: Episode; next_episode_to_air?: Episode;
  release_dates?: { results?: Array<{ iso_3166_1: string; release_dates: ReleaseDate[] }> };
}
export interface CalendarResult { releases: CalendarRelease[]; failedSources: CalendarSource[]; pendingSources: CalendarSource[]; failedTitles: number; failedTitleKeys?: string[]; titleCount: number; sourceTitleCounts: Partial<Record<CalendarSource, number>>; sourceTitleIds?: Partial<Record<CalendarSource, string[]>> }
export interface CalendarRead { source: CalendarSource; read: (onItems?: (items: MediaItem[]) => void) => Promise<MediaItem[]> }
export interface CalendarLoadOptions {
  sources: CalendarRead[]; start: string; end: string; language: string; region: string; customApiKey?: string; signal?: AbortSignal;
  episodeTime?: (item: MediaItem, season: number, episode: number) => Promise<string | null | undefined>;
  episodeSeason?: (item: MediaItem, season: number) => Promise<Array<{ season: number; number: number; first_aired?: string | null }>>;
  onProgress?: (result: CalendarResult) => void;
  /** Bound unresponsive providers; tests can use a shorter deadline. */
  timeoutMs?: number;
  /** Bound optional air-time requests; season requests start their budget after queueing. */
  enrichmentTimeoutMs?: number;
  cacheScope?: string;
  retryTitleKeys?: string[];
  refreshMetadata?: boolean;
}
const cache = new Map<string, { at: number; value: unknown }>();
const detailsCache = new Map<string, { at: number; value: unknown }>();
const missingMetadata = new Map<string, number>();
const schedules = new Map<string, { at: number; details: Details; seasons: number[]; episodes: Episode[] }>();
function cacheMetadata(key: string, value: unknown) {
  const target = /:(tv|movie)\/\d+:/.test(key) ? detailsCache : cache;
  target.delete(key); target.set(key, { at: Date.now(), value });
  while (target.size > (target === detailsCache ? 1024 : 2048)) target.delete(target.keys().next().value!);
}
function bounded<T>(read: () => Promise<T>, options: CalendarLoadOptions): Promise<T> {
  return new Promise((resolve, reject) => {
    const abort = () => finish(new Error("Calendar request cancelled"));
    const timer = setTimeout(() => finish(new Error("Calendar request timed out")), options.timeoutMs ?? 12_000);
    function finish(error?: Error, value?: T) {
      clearTimeout(timer); options.signal?.removeEventListener("abort", abort);
      if (error) reject(error); else resolve(value as T);
    }
    if (options.signal?.aborted) { abort(); return; }
    options.signal?.addEventListener("abort", abort, { once: true });
    Promise.resolve().then(() => { if (options.signal?.aborted) throw new Error("Calendar request cancelled"); return read(); }).then(value => finish(undefined, value), error => finish(error));
  });
}
function queue(concurrency: number) {
  let active = 0;
  const waiting: Array<() => void> = [];
  return async <T>(read: () => Promise<T>): Promise<T> => {
    if (active >= concurrency) await new Promise<void>(resolve => waiting.push(resolve));
    else active++;
    try { return await read(); }
    finally { const next = waiting.shift(); if (next) next(); else active--; }
  };
}
async function metadata<T>(path: string, options: CalendarLoadOptions, extra: Record<string, string> = {}): Promise<T> {
  if (options.signal?.aborted) throw new Error("Calendar request cancelled");
  const key = `${options.language}:${options.customApiKey || "default"}:${path}:${JSON.stringify(extra)}`;
  const missingAt = missingMetadata.get(key);
  if (!options.refreshMetadata && missingAt !== undefined && Date.now() - missingAt < 15 * 60_000) throw new Error("Calendar metadata not found");
  const target = /:(tv|movie)\/\d+:/.test(key) ? detailsCache : cache;
  const cached = target.get(key);
  if (!options.refreshMetadata && cached && Date.now() - cached.at < 30 * 60_000) {
    target.delete(key); target.set(key, cached);
    return cached.value as T;
  }
  let value: T;
  try { value = await bounded(() => tmdb<T>(path, { language: options.language, arvio_calendar: "1", ...extra }, options.customApiKey), options); }
  catch (error) {
    if ((error as { status?: number }).status === 404) {
      missingMetadata.set(key, Date.now());
      while (missingMetadata.size > 512) missingMetadata.delete(missingMetadata.keys().next().value!);
    }
    throw error;
  }
  missingMetadata.delete(key);
  // Seasons are cached individually below. Retaining the large appended copy
  // as well evicted current show details on watchlists with hundreds of titles.
  if (!extra.append_to_response?.includes("season/")) cacheMetadata(key, value);
  return value;
}

/** Date-only release metadata keeps its published civil date in every timezone. */
export function movieCalendarReleases(title: CalendarTitle, details: Details, region: string): CalendarRelease[] {
  const regions = details.release_dates?.results ?? [];
  // Use the requested country, then US; a global primary date is the fallback.
  const selected = regions.find(row => row.iso_3166_1.toUpperCase() === region.toUpperCase() && row.release_dates.length) ?? regions.find(row => row.iso_3166_1 === "US" && row.release_dates.length);
  const kinds: Record<number, ReleaseKind> = { 2: "cinema", 3: "cinema", 4: "digital", 5: "physical", 6: "tv" };
  const releases = new Map<string, CalendarRelease>();
  for (const row of selected?.release_dates ?? []) {
    const date = row.release_date?.slice(0, 10);
    const kind = kinds[row.type];
    if (!date || !parseCalendarDate(date) || !kind) continue;
    const id = `movie:${title.item.id}:${kind}:${date}`;
    releases.set(id, { ...title, id, date, kind, region: selected?.iso_3166_1, artwork: title.item.backdrop || title.item.image });
  }
  if (!releases.size && parseCalendarDate(details.release_date)) {
    const date = details.release_date!;
    releases.set(`movie:${title.item.id}:release:${date}`, { ...title, id: `movie:${title.item.id}:release:${date}`, date, kind: "release", artwork: title.item.backdrop || title.item.image });
  }
  return [...releases.values()];
}

export function calendarSeasonCandidates(seasons: Season[], start: string, end: string): Season[] {
  // A later season starting does not prove the previous season has finished.
  // Specials and unknown season dates must be queried too.
  return [...seasons].filter(row => row.season_number >= 0 && (!row.air_date || row.air_date <= end)).sort((a, b) => b.season_number - a.season_number);
}

async function titleReleases(title: CalendarTitle, options: CalendarLoadOptions, publish: (release: CalendarRelease) => void, readMetadata: ReturnType<typeof queue>): Promise<{ failed: boolean }> {
  const { item } = title;
  const scheduleKey = `${options.language}:${options.customApiKey || "default"}:${item.id}`;
  const cachedSchedule = item.mediaType === "tv" && !options.refreshMetadata ? schedules.get(scheduleKey) : undefined;
  const savedSchedule = cachedSchedule && Date.now() - cachedSchedule.at < 30 * 60_000 ? cachedSchedule : undefined;
  let details: Details;
  try {
    details = savedSchedule?.details ?? await readMetadata(() => metadata<Details>(`${item.mediaType}/${item.id}`, options, item.mediaType === "movie" ? { append_to_response: "release_dates" } : {}));
  } catch (error) {
    if ((error as { status?: number }).status !== 404 && (error as Error).message !== "Calendar metadata not found") throw error;
    // Lists sometimes file a film under series (anime films synced as shows):
    // TMDB has no series with that id, so read it as the film it is.
    if (item.mediaType === "tv") {
      const movie = await readMetadata(() => metadata<Details>(`movie/${item.id}`, options, { append_to_response: "release_dates" })).catch(() => null);
      if (movie && !movie.adult) {
        movieCalendarReleases({ ...title, item: { ...item, ...mapTmdbItem(movie, "movie"), traktId: item.traktId } }, movie, options.region).forEach(publish);
      }
    }
    // A title TMDB does not know is not a failed load: Retry cannot fix it.
    return { failed: false };
  }
  if (details.adult) return { failed: false };
  const hydrated: CalendarTitle = { ...title, item: { ...item, ...mapTmdbItem(details, item.mediaType), traktId: item.traktId } };
  if (item.mediaType === "movie") { movieCalendarReleases(hydrated, details, options.region).forEach(publish); return { failed: false }; }
  if (["Ended", "Canceled"].includes(details.status ?? "") && parseCalendarDate(details.last_episode_to_air?.air_date) && details.last_episode_to_air!.air_date! < options.start && !details.next_episode_to_air &&
      !details.seasons?.some(row => row.air_date && row.air_date >= options.start && row.air_date <= options.end)) return { failed: false };
  const verifiedEpisodes = new Map<string, Episode>();
  const publishEpisode = (episode: Episode) => {
    if (options.signal?.aborted || !parseCalendarDate(episode.air_date) || episode.season_number < 0 || episode.episode_number <= 0) return;
    verifiedEpisodes.set(`${episode.season_number}:${episode.episode_number}`, { season_number: episode.season_number, episode_number: episode.episode_number, air_date: episode.air_date, name: episode.name, still_path: episode.still_path });
    const date = episode.air_date!;
    if (date < options.start || date > options.end) return;
    publish({ ...hydrated, id: `tv:${item.id}:${episode.season_number}:${episode.episode_number}`, kind: "episode", date, season: episode.season_number, episode: episode.episode_number, episodeTitle: episode.name, artwork: episode.still_path ? `${config.backdropBase}${episode.still_path}` : hydrated.item.backdrop || hydrated.item.image });
  };
  // A known next episode is useful even while an old season or exact-time service is slow.
  for (const episode of [details.last_episode_to_air, details.next_episode_to_air]) if (episode) publishEpisode(episode);
  let failed = false;
  const candidates = calendarSeasonCandidates(details.seasons ?? [], options.start, options.end);
  if (!options.refreshMetadata && savedSchedule && Date.now() - savedSchedule.at < 30 * 60_000 && candidates.every(row => savedSchedule.seasons.includes(row.season_number))) {
    savedSchedule.episodes.forEach(publishEpisode);
    return { failed: false };
  }
  let oldestMetadata = savedSchedule?.at ?? detailsCache.get(`${options.language}:${options.customApiKey || "default"}:tv/${item.id}:{}`)?.at ?? Date.now();
  const missing = candidates.filter(season => {
    const saved = cache.get(`${options.language}:${options.customApiKey || "default"}:tv/${item.id}/season/${season.season_number}:{}`);
    if (options.refreshMetadata || !saved || Date.now() - saved.at >= 30 * 60_000) return true;
    oldestMetadata = Math.min(oldestMetadata, saved.at);
    for (const episode of (saved.value as { episodes?: Episode[] }).episodes ?? []) publishEpisode(episode);
    return false;
  });
  for (let index = 0; index < missing.length; index += 20) {
    const batch = missing.slice(index, index + 20);
    let appended: Record<string, { season_number?: number; episodes?: Episode[] }> = {};
    if (batch.length > 1) {
      try { appended = await readMetadata(() => metadata(`tv/${item.id}`, options, { append_to_response: batch.map(row => `season/${row.season_number}`).join(",") })); }
      catch (error) { if (options.signal?.aborted) throw error; }
    }
    await Promise.all(batch.map(async season => {
      const path = `tv/${item.id}/season/${season.season_number}`;
      try {
        let data = appended[`season/${season.season_number}`];
        if (data?.season_number === season.season_number && Array.isArray(data.episodes)) {
          cacheMetadata(`${options.language}:${options.customApiKey || "default"}:${path}:{}`, data);
        } else data = await readMetadata(() => metadata<{ episodes?: Episode[] }>(path, options));
        oldestMetadata = Math.min(oldestMetadata, cache.get(`${options.language}:${options.customApiKey || "default"}:${path}:{}`)?.at ?? Date.now());
        for (const episode of data.episodes ?? []) publishEpisode(episode);
      } catch (error) { if (options.signal?.aborted) throw error; failed = true; }
    }));
  }
  if (!failed && !options.signal?.aborted) {
    schedules.delete(scheduleKey);
    schedules.set(scheduleKey, { at: oldestMetadata, details, seasons: candidates.map(row => row.season_number), episodes: [...verifiedEpisodes.values()] });
    let episodeCount = [...schedules.values()].reduce((count, schedule) => count + schedule.episodes.length, 0);
    while (schedules.size > 1024 || episodeCount > 40_000) {
      const key = schedules.keys().next().value!;
      episodeCount -= schedules.get(key)!.episodes.length; schedules.delete(key);
    }
  }
  return { failed };
}

export async function loadCalendar(options: CalendarLoadOptions): Promise<CalendarResult> {
  const titles = new Map<string, CalendarTitle>();
  const releases = new Map<string, CalendarRelease>();
  const failedTitles = new Set<string>();
  const failedSources = new Set<CalendarSource>();
  const pendingSources = new Set(options.sources.map(row => row.source));
  const sourceTitleCounts: CalendarResult["sourceTitleCounts"] = {};
  const sourceTitleIds: NonNullable<CalendarResult["sourceTitleIds"]> = {};
  const titleJobs = new Map<string, Promise<void>>();
  const timeJobs: Promise<void>[] = [];
  const requestedTimes = new Set<string>();
  const unavailableTimeSeasons = new Set<string>();
  const timeDeadlines = new Map<string, number>();
  const requestedSeasons = new Set<string>();
  const seasonTimes = new Map<string, ReturnType<typeof authoritativeEpisodeTime>>();
  let timeServiceTimedOut = false;
  let timeServiceSucceeded = false;
  // A historical season must not occupy a title slot while other titles still
  // need their first details response. Bound individual requests instead.
  const readMetadata = queue(4), readIdentity = queue(4), readTime = queue(4);
  const snapshot = (): CalendarResult => ({
    releases: sortCalendarReleases([...releases.values()].filter(row => row.date >= options.start && row.date <= options.end).map(row => ({ ...row, sources: [...(titles.get(`${row.item.mediaType}:${row.item.id}`)?.sources ?? row.sources)] }))),
    failedSources: [...failedSources], pendingSources: [...pendingSources], failedTitles: failedTitles.size, failedTitleKeys: [...failedTitles], titleCount: titles.size, sourceTitleCounts: { ...sourceTitleCounts }, sourceTitleIds: Object.fromEntries(Object.entries(sourceTitleIds).map(([source, ids]) => [source, [...ids]]))
  });
  let publicationTimer: ReturnType<typeof setTimeout> | undefined;
  let lastPublished = 0, publishedFirstRelease = false, publishedInitial = false;
  const cancelPublication = () => { clearTimeout(publicationTimer); publicationTimer = undefined; };
  options.signal?.addEventListener("abort", cancelPublication, { once: true });
  const publish = (immediate = false) => {
    if (options.signal?.aborted) return;
    const send = () => {
      cancelPublication();
      if (options.signal?.aborted) return;
      publishedInitial = true; publishedFirstRelease ||= releases.size > 0; lastPublished = Date.now();
      options.onProgress?.(snapshot());
    };
    // Paint the first date immediately; coalesce large seasons and exact-time updates.
    if (immediate || !publishedInitial || (!publishedFirstRelease && releases.size > 0) || Date.now() - lastPublished >= 125) send();
    else if (!publicationTimer) publicationTimer = setTimeout(send, 125 - (Date.now() - lastPublished));
  };
  const publishRelease = (release: CalendarRelease) => {
    if (options.signal?.aborted) return;
    const current = releases.get(release.id);
    const savedTime = seasonTimes.get(release.id);
    releases.set(release.id, savedTime ? { ...release, ...savedTime } : current?.timestamp ? { ...release, timestamp: current.timestamp, date: current.date } : release);
    publish();
    if (release.kind === "episode" && options.episodeSeason) {
      const seasonKey = `${release.item.id}:${release.season}`;
      if (requestedSeasons.has(seasonKey)) return;
      requestedSeasons.add(seasonKey);
      timeJobs.push(readTime(async () => {
        if (options.signal?.aborted || timeServiceTimedOut) return;
        try {
          // One season response enriches both existing and subsequently verified episodes.
          const rows = await bounded(() => options.episodeSeason!(release.item, release.season!), {
            ...options, timeoutMs: Math.min(options.timeoutMs ?? 12_000, options.enrichmentTimeoutMs ?? 4_000)
          });
          if (options.signal?.aborted) return;
          timeServiceSucceeded = true;
          for (const row of rows) {
            if (row.season !== release.season || !Number.isInteger(row.number) || row.number <= 0) continue;
            const time = authoritativeEpisodeTime(row.first_aired);
            if (!time) continue;
            const id = `tv:${release.item.id}:${row.season}:${row.number}`;
            seasonTimes.set(id, time);
            const verified = releases.get(id);
            if (verified) releases.set(id, { ...verified, ...time });
          }
          publish();
        } catch (error) {
          // An unavailable optional time service must not serialize a long watchlist.
          if ((error as Error)?.message === "Calendar request timed out" && !timeServiceSucceeded) timeServiceTimedOut = true;
        }
      }));
      return;
    }
    if (release.kind !== "episode" || !options.episodeTime || requestedTimes.has(release.id)) return;
    requestedTimes.add(release.id);
    const titleKey = `${release.item.mediaType}:${release.item.id}`;
    if (!timeDeadlines.has(titleKey)) timeDeadlines.set(titleKey, Date.now() + (options.enrichmentTimeoutMs ?? 4_000));
    const deadline = timeDeadlines.get(titleKey)!;
    timeJobs.push(readTime(async () => {
      const seasonKey = `${release.item.id}:${release.season}`;
      const remaining = deadline - Date.now();
      if (options.signal?.aborted || unavailableTimeSeasons.has(seasonKey) || remaining <= 0) return;
      try {
        const time = authoritativeEpisodeTime(await bounded(() => options.episodeTime!(release.item, release.season!, release.episode!), { ...options, timeoutMs: Math.min(options.timeoutMs ?? 12_000, remaining) }));
        if (time && !options.signal?.aborted) { releases.set(release.id, { ...releases.get(release.id)!, ...time }); publish(); }
      } catch { unavailableTimeSeasons.add(seasonKey); }
    }));
  };
  publish();
  // Each provider schedules its titles independently; a stalled tracker cannot hold ARVIO dates back.
  await Promise.all(options.sources.map(async ({ source, read }) => {
    try {
      const sourceItems = new Map<string, MediaItem>();
      const ingest = async (incoming: MediaItem[]) => {
      if (options.signal?.aborted) return;
      const items = incoming.filter(item => !item.isHomeServer).map(item => ({ ...item }));
      for (const item of items) sourceItems.set(`${item.mediaType}:${item.id}:${item.imdbId || ""}`, item);
      sourceTitleCounts[source] = sourceItems.size; sourceTitleIds[source] ??= []; publish();
      await Promise.all(items.map(async (item, index) => {
        if (item.id <= 0) {
          const id = await readIdentity(() => bounded(() => resolveTmdbId(item), options)).catch(() => null);
          if (id) item.id = id;
        }
        if (options.signal?.aborted) return;
        if (item.id <= 0) { failedTitles.add(`${source}:${index}`); return; }
        const key = `${item.mediaType}:${item.id}`;
        if (!sourceTitleIds[source]!.includes(key)) sourceTitleIds[source]!.push(key);
        const previous = titles.get(key);
        if (previous) {
          if (!previous.sources.includes(source)) previous.sources.push(source);
          previous.item = mergeCalendarTitles([{ source: previous.sources[0], items: [previous.item, item] }])[0].item;
          publish();
        } else {
          const title: CalendarTitle = { item, sources: [source] }; titles.set(key, title);
          titleJobs.set(key, (async () => {
            if (options.signal?.aborted) return;
            const projectionKey = options.cacheScope ? `v3:${options.cacheScope}:${options.language}:${options.region}:${options.start}:${options.end}:${key}:${item.traktId || ""}` : undefined;
            const refreshMetadata = Boolean(options.refreshMetadata || options.retryTitleKeys?.includes(key));
            const saved = projectionKey && !refreshMetadata ? readCalendarTitle(projectionKey) : undefined;
            try {
              if (saved) saved.forEach(row => publishRelease({ ...row, sources: title.sources }));
              else if ((await titleReleases(title, { ...options, refreshMetadata }, publishRelease, readMetadata)).failed) failedTitles.add(key);
            }
            catch { if (!options.signal?.aborted) failedTitles.add(key); }
            if (projectionKey && !saved && !failedTitles.has(key) && !options.signal?.aborted) {
              rememberCalendarTitle(projectionKey, [...releases.values()].filter(row => `${row.item.mediaType}:${row.item.id}` === key), 30 * 60_000);
            }
            publish();
          })());
        }
        await titleJobs.get(key);
      }));
      };
      const partials: Promise<void>[] = [];
      try {
        const items = await bounded(() => read(items => { partials.push(ingest(items)); }), { ...options, timeoutMs: options.timeoutMs ?? 45_000 });
        await ingest(items);
      } finally { await Promise.all(partials); }
    } catch (error) {
      if (!options.signal?.aborted) {
        failedSources.add(source);
        console.warn(`[Calendar] ${source} source unavailable (${(error as { status?: number })?.status ?? "network"}, ${(error as Error)?.name ?? "Error"})`);
      }
    }
    finally { pendingSources.delete(source); publish(); }
  }));
  await Promise.all(timeJobs);
  publish(true);
  cancelPublication(); options.signal?.removeEventListener("abort", cancelPublication);
  if (!options.signal?.aborted && options.cacheScope) persistCalendarTitles();
  return snapshot();
}

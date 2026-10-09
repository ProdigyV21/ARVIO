import { config } from "./config";
import { tmdb, mapTmdbItem, resolveTmdbId } from "./tmdb";
import { authoritativeEpisodeTime, mergeCalendarTitles, parseCalendarDate, sortCalendarReleases, type CalendarRelease, type CalendarSource, type CalendarTitle, type ReleaseKind } from "./calendar";
import type { MediaItem } from "./types";

interface Episode { season_number: number; episode_number: number; air_date?: string; name?: string; still_path?: string }
interface Season { id: number; season_number: number; air_date?: string; episode_count?: number }
interface ReleaseDate { type: number; release_date?: string }
interface Details {
  id: number; title?: string; name?: string; poster_path?: string; backdrop_path?: string; adult?: boolean; release_date?: string; status?: string;
  seasons?: Season[]; last_episode_to_air?: Episode; next_episode_to_air?: Episode;
  release_dates?: { results?: Array<{ iso_3166_1: string; release_dates: ReleaseDate[] }> };
}
export interface CalendarResult { releases: CalendarRelease[]; failedSources: CalendarSource[]; pendingSources: CalendarSource[]; failedTitles: number; failedTitleKeys?: string[]; titleCount: number; sourceTitleCounts: Partial<Record<CalendarSource, number>>; sourceTitleIds?: Partial<Record<CalendarSource, string[]>> }
export interface CalendarRead { source: CalendarSource; read: () => Promise<MediaItem[]> }
export interface CalendarLoadOptions {
  sources: CalendarRead[]; start: string; end: string; language: string; region: string; customApiKey?: string; signal?: AbortSignal;
  episodeTime?: (item: MediaItem, season: number, episode: number) => Promise<string | null | undefined>;
  onProgress?: (result: CalendarResult) => void;
  /** Bound unresponsive providers; tests can use a shorter deadline. */
  timeoutMs?: number;
  /** A title's optional air-time budget includes time spent waiting in the queue. */
  enrichmentTimeoutMs?: number;
}
const cache = new Map<string, { at: number; value: unknown }>();
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
  const key = `${options.language}:${path}:${JSON.stringify(extra)}`;
  const cached = cache.get(key);
  if (cached && Date.now() - cached.at < 10 * 60_000) return cached.value as T;
  const value = await bounded(() => tmdb<T>(path, { language: options.language, ...extra }, options.customApiKey), options);
  cache.set(key, { at: Date.now(), value });
  if (cache.size > 600) cache.delete(cache.keys().next().value!);
  return value;
}

/** Date-only release metadata keeps its published civil date in every timezone. */
export function movieCalendarReleases(title: CalendarTitle, details: Details, region: string): CalendarRelease[] {
  const regions = details.release_dates?.results ?? [];
  // Use the requested country, then US; a global primary date is the fallback.
  const selected = regions.find(row => row.iso_3166_1 === region) ?? regions.find(row => row.iso_3166_1 === "US");
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
  const details = await readMetadata(() => metadata<Details>(`${item.mediaType}/${item.id}`, options, item.mediaType === "movie" ? { append_to_response: "release_dates" } : {}));
  if (details.adult) return { failed: false };
  const hydrated: CalendarTitle = { ...title, item: { ...item, ...mapTmdbItem(details, item.mediaType), traktId: item.traktId } };
  if (item.mediaType === "movie") { movieCalendarReleases(hydrated, details, options.region).forEach(publish); return { failed: false }; }
  if (["Ended", "Canceled"].includes(details.status ?? "") && parseCalendarDate(details.last_episode_to_air?.air_date) && details.last_episode_to_air!.air_date! < options.start && !details.next_episode_to_air) return { failed: false };
  const publishEpisode = (episode: Episode) => {
    if (options.signal?.aborted || !parseCalendarDate(episode.air_date) || episode.season_number < 0 || episode.episode_number <= 0) return;
    const date = episode.air_date!;
    if (date < options.start || date > options.end) return;
    publish({ ...hydrated, id: `tv:${item.id}:${episode.season_number}:${episode.episode_number}`, kind: "episode", date, season: episode.season_number, episode: episode.episode_number, episodeTitle: episode.name, artwork: episode.still_path ? `${config.backdropBase}${episode.still_path}` : hydrated.item.backdrop || hydrated.item.image });
  };
  // A known next episode is useful even while an old season or exact-time service is slow.
  for (const episode of [details.last_episode_to_air, details.next_episode_to_air]) if (episode) publishEpisode(episode);
  let failed = false;
  for (const season of calendarSeasonCandidates(details.seasons ?? [], options.start, options.end)) {
    try {
      const data = await readMetadata(() => metadata<{ episodes?: Episode[] }>(`tv/${item.id}/season/${season.season_number}`, options));
      for (const episode of data.episodes ?? []) publishEpisode(episode);
    } catch (error) { if (options.signal?.aborted) throw error; failed = true; }
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
    releases.set(release.id, current?.timestamp ? { ...release, timestamp: current.timestamp, date: current.date } : release);
    publish();
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
      const items = (await bounded(read, options)).filter(item => !item.isHomeServer).map(item => ({ ...item }));
      sourceTitleCounts[source] = items.length; sourceTitleIds[source] = []; publish();
      await Promise.all(items.map(async (item, index) => {
        if (item.id <= 0) {
          const id = await readIdentity(() => bounded(() => resolveTmdbId(item), options)).catch(() => null);
          if (id) item.id = id;
        }
        if (options.signal?.aborted) return;
        if (item.id <= 0) { failedTitles.add(`${source}:${index}`); return; }
        const key = `${item.mediaType}:${item.id}`;
        sourceTitleIds[source]!.push(key);
        const previous = titles.get(key);
        if (previous) {
          if (!previous.sources.includes(source)) previous.sources.push(source);
          previous.item = mergeCalendarTitles([{ source: previous.sources[0], items: [previous.item, item] }])[0].item;
          publish();
        } else {
          const title: CalendarTitle = { item, sources: [source] }; titles.set(key, title);
          titleJobs.set(key, (async () => {
            if (options.signal?.aborted) return;
            try { if ((await titleReleases(title, options, publishRelease, readMetadata)).failed) failedTitles.add(key); }
            catch { if (!options.signal?.aborted) failedTitles.add(key); }
            publish();
          })());
        }
        await titleJobs.get(key);
      }));
    } catch { if (!options.signal?.aborted) failedSources.add(source); }
    finally { pendingSources.delete(source); publish(); }
  }));
  await Promise.all(timeJobs);
  publish(true);
  cancelPublication(); options.signal?.removeEventListener("abort", cancelPublication);
  return snapshot();
}

import { SyncClient, SyncMediaRef } from "./sync";
import { loadStored, removeStored, saveStored } from "./storage";
import { jsonRequest } from "./http";
import { resolveTmdbId } from "./tmdb";
import { config } from "./config";

const LEGACY_SIMKL_TOKEN_KEY = "arvio.web.simkl.token";
const SNAPSHOT_TTL_MS = 15 * 60 * 1000;
const FAILED_SNAPSHOT_RETRY_MS = 60 * 1000;
const SCROBBLE_WRITE_LOCK_MS = 20_500;
const MAX_PENDING_SCROBBLES = 32;

type PendingScrobble = {
  scope: string;
  action: "start" | "pause" | "stop";
  item: SyncMediaRef & { progress: number };
  waiters: Array<{ resolve: () => void; reject: (error: unknown) => void }>;
};

export interface SimklToken {
  access_token: string;
}

export interface SimklPinCode {
  user_code: string;
  verification_url: string;
  expires_in: number;
  interval: number;
}

type SimklIds = { tmdb?: number; simkl?: number; simkl_id?: number; imdb?: string; slug?: string };
type SimklMovieRow = {
  movie?: { title?: string; year?: number; ids?: SimklIds };
  status?: string;
  last_watched_at?: string;
};
type SimklShowRow = {
  show?: { title?: string; year?: number; ids?: SimklIds };
  status?: string;
  last_watched_at?: string;
  seasons?: Array<{ number?: number; episodes?: Array<{ number?: number; watched_at?: string }> }>;
  next_to_watch?: string | null;
  next_to_watch_info?: { season?: number; episode?: number; title?: string; date?: string } | null;
};
type SimklPlaybackRow = {
  id?: number;
  progress?: number;
  paused_at?: string;
  movie?: { title?: string; year?: number; ids?: SimklIds };
  show?: { title?: string; year?: number; ids?: SimklIds };
  anime?: { title?: string; year?: number; ids?: SimklIds };
  episode?: { season?: number; number?: number; episode?: number; title?: string };
};
type SimklSnapshot = {
  scope: string;
  activity: string | null;
  checkedAt: number;
  complete: boolean;
  initialized?: boolean;
  removals?: Partial<Record<"movies" | "shows" | "anime", string>>;
  movies: SimklMovieRow[];
  shows: SimklShowRow[];
  anime: SimklShowRow[];
};

function extractItems<T>(res: unknown, key: "movies" | "shows" | "anime"): T[] {
  if (!res) return [];
  if (Array.isArray(res)) return res as T[];
  if (typeof res === "object" && res !== null && key in res) {
    const list = (res as Record<string, unknown>)[key];
    if (Array.isArray(list)) return list as T[];
  }
  return [];
}

function rowKey(ids?: SimklIds): string | null {
  const simkl = ids?.simkl ?? ids?.simkl_id;
  if (simkl != null) return `simkl:${simkl}`;
  if (ids?.tmdb != null) return `tmdb:${ids.tmdb}`;
  return ids?.imdb ? `imdb:${ids.imdb}` : null;
}

function mergeRows<T extends { movie?: { ids?: SimklIds }; show?: { ids?: SimklIds } }>(
  existing: T[],
  incoming: T[],
  key: "movie" | "show"
): T[] {
  const map = new Map<string, T>();
  for (const item of existing) {
    const ids = key === "movie" ? item.movie?.ids : item.show?.ids;
    const id = rowKey(ids);
    if (id != null) map.set(String(id), item);
  }
  for (const item of incoming) {
    const ids = key === "movie" ? item.movie?.ids : item.show?.ids;
    const id = rowKey(ids);
    if (id != null) map.set(String(id), item);
  }
  return Array.from(map.values());
}

function activityMarker(value: unknown): string | null {
  if (!value || typeof value !== "object") return null;
  const root = value as Record<string, unknown>;
  if (typeof root.all === "string") return root.all;
  for (const key of ["movies", "tv_shows", "shows", "anime"]) {
    const group = root[key];
    if (group && typeof group === "object" && typeof (group as Record<string, unknown>).all === "string") {
      return (group as Record<string, string>).all;
    }
  }
  return null;
}

function removalMarkers(value: unknown): NonNullable<SimklSnapshot["removals"]> {
  if (!value || typeof value !== "object") return {};
  const root = value as Record<string, { removed_from_list?: unknown } | undefined>;
  const result: NonNullable<SimklSnapshot["removals"]> = {};
  for (const type of ["movies", "shows", "anime"] as const) {
    const group = type === "shows" ? root.tv_shows ?? root.shows : root[type];
    if (typeof group?.removed_from_list === "string") result[type] = group.removed_from_list;
  }
  return result;
}

function parseNextToWatch(value?: string | null): NonNullable<SimklShowRow["next_to_watch_info"]> | null {
  const match = /^(?:S(\d+))?E(\d+)$/i.exec(value?.trim() ?? "");
  if (!match) return null;
  const season = Number(match[1] || 1);
  const episode = Number(match[2]);
  if (!Number.isFinite(season) || !Number.isFinite(episode) || episode <= 0) return null;
  return { season, episode };
}

export class SimklClient implements SyncClient {
  token: SimklToken | null = null;
  private profileId: string | null = null;

  get currentProfileId(): string | null {
    return this.profileId;
  }
  private snapshot: SimklSnapshot | null = null;
  private snapshotPromise: Promise<SimklSnapshot> | null = null;
  private lastSnapshotFailureAt = 0;
  private lastScrobbleWriteAt = 0;
  private pendingScrobbles: PendingScrobble[] = [];
  private scrobbleInFlight = false;
  private scrobbleGeneration = 0;
  private scrobbleTimer: ReturnType<typeof setTimeout> | null = null;

  get isConnected(): boolean {
    return Boolean(this.token?.access_token);
  }

  private tokenKey(profileId: string): string {
    return `arvio.web.simkl.token:${profileId}`;
  }

  setProfile(profileId: string | null) {
    const normalized = profileId?.trim() || null;
    if (normalized === this.profileId) return;
    this.resetScrobbleQueue();
    this.profileId = normalized;
    this.snapshot = null;
    this.snapshotPromise = null;
    this.lastSnapshotFailureAt = 0;
    if (!normalized) {
      this.token = null;
      return;
    }

    let stored = loadStored<SimklToken | null>(this.tokenKey(normalized), null);
    if (!stored) {
      const legacy = loadStored<SimklToken | null>(LEGACY_SIMKL_TOKEN_KEY, null);
      if (legacy?.access_token) {
        stored = legacy;
        saveStored(this.tokenKey(normalized), legacy);
        removeStored(LEGACY_SIMKL_TOKEN_KEY);
      }
    }
    this.token = stored?.access_token ? stored : null;
  }

  setToken(token: SimklToken | null) {
    const next = token?.access_token ? token : null;
    const tokenChanged = next?.access_token !== this.token?.access_token;
    if (tokenChanged) {
      this.resetScrobbleQueue();
      this.snapshot = null;
      this.snapshotPromise = null;
      this.lastSnapshotFailureAt = 0;
    }
    this.token = next;
    if (!this.profileId) return;
    if (this.token) saveStored(this.tokenKey(this.profileId), this.token);
    else removeStored(this.tokenKey(this.profileId));
  }

  disconnect() {
    this.setToken(null);
  }

  private async simkl<T>(path: string, options: RequestInit = {}, accessToken = this.token?.access_token): Promise<T> {
    const headers: Record<string, string> = {
      "content-type": "application/json",
      ...(options.headers as Record<string, string>)
    };
    if (accessToken) headers["x-user-token"] = accessToken;

    const [pathname, queryString] = path.split("?");
    const params = new URLSearchParams(queryString || "");
    if (!params.has("app-name")) params.set("app-name", "arvio");
    if (!params.has("app-version")) params.set("app-version", "1.9.996");
    if (config.simklClientId && !params.has("client_id")) {
      params.set("client_id", config.simklClientId);
    }
    const finalQuery = params.toString();
    const finalUrl = `/api/simkl${pathname}${finalQuery ? `?${finalQuery}` : ""}`;

    return jsonRequest<T>(finalUrl, { ...options, headers });
  }

  private scope(): string {
    return `${this.profileId ?? "none"}:${this.token?.access_token ?? "none"}`;
  }

  private invalidateSnapshot() {
    this.snapshot = null;
    this.snapshotPromise = null;
    this.lastSnapshotFailureAt = 0;
  }

  private resetScrobbleQueue() {
    if (this.scrobbleTimer) clearTimeout(this.scrobbleTimer);
    this.scrobbleTimer = null;
    this.scrobbleGeneration++;
    this.scrobbleInFlight = false;
    const pending = this.pendingScrobbles.splice(0);
    for (const entry of pending) {
      for (const waiter of entry.waiters) waiter.reject(new Error("Tracking account changed before playback was sent"));
    }
    this.lastScrobbleWriteAt = 0;
  }

  private async loadSnapshot(): Promise<SimklSnapshot> {
    if (!this.isConnected) {
      return {
        scope: this.scope(), activity: null, checkedAt: Date.now(), complete: true,
        movies: [], shows: [], anime: []
      };
    }
    const scope = this.scope();
    const accessToken = this.token?.access_token;
    const cached = this.snapshot?.scope === scope ? this.snapshot : null;
    const now = Date.now();
    if (cached?.complete && now - cached.checkedAt < SNAPSHOT_TTL_MS) return cached;
    if (now - this.lastSnapshotFailureAt < FAILED_SNAPSHOT_RETRY_MS) {
      return cached ?? {
        scope, activity: null, checkedAt: now, complete: false,
        movies: [], shows: [], anime: []
      };
    }
    if (this.snapshotPromise) return this.snapshotPromise;

    const request = (async () => {
      const activities = await this.simkl<unknown>("/sync/activities", {}, accessToken).catch(() => null);
      const marker = activityMarker(activities);
      const removals = removalMarkers(activities);
      const removedTypes = (["movies", "shows", "anime"] as const)
        .filter(type => removals[type] && removals[type] !== cached?.removals?.[type]);
      if (cached?.complete && marker && marker === cached.activity && !removedTypes.length) {
        return { ...cached, checkedAt: Date.now() };
      }

      let moviesResult: SimklMovieRow[] = [];
      let showsResult: SimklShowRow[] = [];
      let animeResult: SimklShowRow[] = [];
      let complete = false;

      if (cached?.initialized && cached.activity) {
        // Continuous sync delta (Phase 2): single request for all types modified since watermark
        try {
          const deltaQuery = `?date_from=${encodeURIComponent(cached.activity)}&extended=full&episode_watched_at=yes&include_all_episodes=yes&next_watch_info=yes`;
          const deltaRes = await this.simkl<unknown>(`/sync/all-items${deltaQuery}`, {}, accessToken);
          if (!deltaRes || typeof deltaRes !== "object" || Array.isArray(deltaRes)) {
            throw new Error("Unexpected Simkl delta response");
          }
          const moviesDelta = extractItems<SimklMovieRow>(deltaRes, "movies");
          const showsDelta = extractItems<SimklShowRow>(deltaRes, "shows");
          const animeDelta = extractItems<SimklShowRow>(deltaRes, "anime");

          moviesResult = mergeRows(cached.movies, moviesDelta, "movie");
          showsResult = mergeRows(cached.shows, showsDelta, "show");
          animeResult = mergeRows(cached.anime, animeDelta, "show");
          // Incremental responses omit deletions; only reconcile categories with a changed removal marker.
          for (const type of removedTypes) {
            const idsRes = await this.simkl<unknown>(`/sync/all-items/${type}?extended=ids_only`, {}, accessToken);
            if (!idsRes || typeof idsRes !== "object" || (!Array.isArray(idsRes) &&
              Object.keys(idsRes).length > 0 && !Array.isArray((idsRes as Record<string, unknown>)[type]))) {
              throw new Error(`Unexpected Simkl ${type} IDs response`);
            }
            const ids = new Set(extractItems<SimklMovieRow & SimklShowRow>(idsRes, type)
              .map(row => rowKey(type === "movies" ? row.movie?.ids : row.show?.ids)));
            if (type === "movies") moviesResult = moviesResult.filter(row => ids.has(rowKey(row.movie?.ids)));
            if (type === "shows") showsResult = showsResult.filter(row => ids.has(rowKey(row.show?.ids)));
            if (type === "anime") animeResult = animeResult.filter(row => ids.has(rowKey(row.show?.ids)));
          }
          complete = true;
        } catch {
          complete = false;
        }
      } else {
        // Initial sync (Phase 1): pull type by type sequentially
        const query = "?extended=full&episode_watched_at=yes&include_all_episodes=yes&next_watch_info=yes";
        try {
          const moviesRes = await this.simkl<unknown>(`/sync/all-items/movies/all${query}`, {}, accessToken);
          moviesResult = extractItems<SimklMovieRow>(moviesRes, "movies");
          const showsRes = await this.simkl<unknown>(`/sync/all-items/shows/all${query}`, {}, accessToken);
          showsResult = extractItems<SimklShowRow>(showsRes, "shows");
          const animeRes = await this.simkl<unknown>(`/sync/all-items/anime/all?extended=full_anime_seasons&episode_watched_at=yes&include_all_episodes=yes&next_watch_info=yes`, {}, accessToken);
          animeResult = extractItems<SimklShowRow>(animeRes, "anime");
          complete = true;
        } catch {
          complete = false;
        }
      }

      if (complete) this.lastSnapshotFailureAt = 0;
      else this.lastSnapshotFailureAt = Date.now();

      return {
        scope,
        activity: complete ? marker : cached?.activity ?? null,
        initialized: complete || cached?.initialized || false,
        removals: complete ? removals : cached?.removals,
        checkedAt: Date.now(),
        complete,
        movies: complete ? moviesResult : cached?.movies ?? [],
        shows: complete ? showsResult : cached?.shows ?? [],
        anime: complete ? animeResult : cached?.anime ?? []
      };
    })();
    this.snapshotPromise = request;

    try {
      const result = await request;
      if (result.scope === this.scope()) this.snapshot = result;
      return result;
    } finally {
      if (this.snapshotPromise === request) this.snapshotPromise = null;
    }
  }

  async beginPinAuth(): Promise<SimklPinCode> {
    return this.simkl<SimklPinCode>("/oauth/pin");
  }

  async pollPinToken(userCode: string): Promise<SimklToken | null> {
    type PollRes = { result: string; access_token?: string };
    const res = await this.simkl<PollRes>(`/oauth/pin/${encodeURIComponent(userCode)}`);
    if (res.result === "OK" && res.access_token) {
      return { access_token: res.access_token };
    }
    return null;
  }

  async watchlist(): Promise<unknown[]> {
    const snapshot = await this.loadSnapshot();
    const movies = (await Promise.all(snapshot.movies
      .filter((item) => item.status === "plantowatch")
      .map(async (item) => ({
        type: "movie",
        movie: await this.resolveMedia(item.movie, "movie"),
        listed_at: item.last_watched_at
      })))).filter((item) => item.movie?.ids?.tmdb);
    const shows = (await Promise.all([...snapshot.shows, ...snapshot.anime]
      .filter((item) => item.status === "plantowatch")
      .map(async (item) => ({
        type: "show",
        show: await this.resolveMedia(item.show, "tv"),
        listed_at: item.last_watched_at
      })))).filter((item) => item.show?.ids?.tmdb);
    return [...movies, ...shows];
  }

  async library(status: "plantowatch" | "watching" | "completed" | "hold" | "dropped"): Promise<unknown[]> {
    const snapshot = await this.loadSnapshot();
    const rows = [
      ...snapshot.movies.filter((row) => row.status === status).map((row) => ({ type: "movie", movie: row.movie, listed_at: row.last_watched_at })),
      ...[...snapshot.shows, ...snapshot.anime].filter((row) => row.status === status).map((row) => ({ type: "show", show: row.show, listed_at: row.last_watched_at }))
    ];
    // Identity/artwork is resolved by the shared bounded tracker hydrator.
    return rows;
  }

  async playback(): Promise<unknown[]> {
    if (!this.isConnected) return [];
    let rows: SimklPlaybackRow[] = [];
    let playbackError: unknown = null;
    try {
      rows = await this.simkl<SimklPlaybackRow[]>("/sync/playback");
    } catch (err) {
      playbackError = err;
      rows = [];
    }
    let snapshot: SimklSnapshot;
    try {
      snapshot = await this.loadSnapshot();
    } catch (err) {
      if (playbackError) throw err;
      snapshot = { shows: [], anime: [] } as unknown as SimklSnapshot;
    }
    const normalized = (await Promise.all(rows.map(async (row) => ({
      ...row,
      movie: await this.resolveMedia(row.movie, "movie"),
      show: await this.resolveMedia(row.show ?? row.anime, "tv"),
      episode: row.episode
        ? { ...row.episode, number: row.episode.number ?? row.episode.episode }
        : undefined
    })))).filter((row) => row.movie?.ids?.tmdb || row.show?.ids?.tmdb);
    const pausedShows = new Set(normalized.map((row) => row.show?.ids?.tmdb).filter(Boolean));
    const upNext = (await Promise.all([...snapshot.shows, ...snapshot.anime].map(async (row) => {
      if (row.status !== "watching") return null;
      const show = await this.resolveMedia(row.show, "tv");
      const episode = row.next_to_watch_info ?? parseNextToWatch(row.next_to_watch);
      const number = episode?.episode;
      const season = episode?.season ?? 1;
      if (!show?.ids?.tmdb || !number || pausedShows.has(show.ids.tmdb)) return null;
      return {
        progress: 0,
        paused_at: row.last_watched_at,
        show,
        episode: { ...episode, season, number },
        is_up_next: true
      };
    }))).filter(Boolean);
    return [...normalized, ...upNext];
  }

  async watched(type: "movies" | "shows"): Promise<unknown[]> {
    const snapshot = await this.loadSnapshot();
    if (type === "movies") {
      return (await Promise.all(snapshot.movies.filter((item) =>
        item.status === "completed"
      ).map(async (item) => ({ ...item, movie: await this.resolveMedia(item.movie, "movie") }))))
        .filter((item) => item.movie?.ids?.tmdb);
    }
    return (await Promise.all([...snapshot.shows, ...snapshot.anime]
      .map(async (item) => ({ ...item, show: await this.resolveMedia(item.show, "tv") }))))
      .filter((item) => item.show?.ids?.tmdb);
  }

  async addToWatchlist(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const body = item.mediaType === "movie"
      ? { movies: [{ to: "plantowatch", ids: { tmdb: item.tmdbId } }] }
      : item.isAnime
        ? { anime: [{ to: "plantowatch", ids: { tmdb: item.tmdbId } }] }
        : { shows: [{ to: "plantowatch", ids: { tmdb: item.tmdbId } }] };
    await this.simkl("/sync/add-to-list", { method: "POST", body: JSON.stringify(body) });
    this.invalidateSnapshot();
  }

  async removeFromWatchlist(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const snapshot = await this.loadSnapshot();
    const watched = item.mediaType === "movie"
      ? snapshot.movies.some((row) => row.movie?.ids?.tmdb === item.tmdbId && Boolean(row.last_watched_at))
      : [...snapshot.shows, ...snapshot.anime].some((row) =>
          row.show?.ids?.tmdb === item.tmdbId && (Boolean(row.last_watched_at) || row.seasons?.some((s) => s.episodes?.length))
        );
    const body = item.mediaType === "movie"
      ? { movies: [{ ...(watched ? { to: "completed" } : {}), ids: { tmdb: item.tmdbId } }] }
      : item.isAnime
        ? { anime: [{ ...(watched ? { to: "completed" } : {}), ids: { tmdb: item.tmdbId } }] }
        : { shows: [{ ...(watched ? { to: "completed" } : {}), ids: { tmdb: item.tmdbId } }] };
    await this.simkl(watched ? "/sync/add-to-list" : "/sync/history/remove", {
      method: "POST",
      body: JSON.stringify(body)
    });
    this.invalidateSnapshot();
  }

  async addToHistory(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const hasEpisode = typeof item.season === "number" && typeof item.episode === "number";
    const series = {
      ids: { tmdb: item.tmdbId },
      ...(item.isAnime ? { use_tvdb_anime_seasons: true } : {}),
      seasons: hasEpisode ? [{ number: item.season!, episodes: [{ number: item.episode! }] }] : undefined
    };
    const body = item.mediaType === "movie"
      ? { movies: [{ ids: { tmdb: item.tmdbId } }] }
      : { shows: [series] };
    await this.simkl("/sync/history", { method: "POST", body: JSON.stringify(body) });
    this.invalidateSnapshot();
  }

  async removeFromHistory(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    if (item.mediaType === "movie") {
      // Use add-to-list to move to plantowatch instead of removing the movie and user rating completely
      const body = { movies: [{ to: "plantowatch", ids: { tmdb: item.tmdbId } }] };
      await this.simkl("/sync/add-to-list", { method: "POST", body: JSON.stringify(body) });
    } else {
      const hasEpisode = typeof item.season === "number" && typeof item.episode === "number";
      const series = {
        ids: { tmdb: item.tmdbId },
        ...(item.isAnime ? { use_tvdb_anime_seasons: true } : {}),
        seasons: hasEpisode ? [{ number: item.season!, episodes: [{ number: item.episode! }] }] : undefined
      };
      const body = { shows: [series] };
      await this.simkl("/sync/history/remove", { method: "POST", body: JSON.stringify(body) });
    }
    this.invalidateSnapshot();
  }

  async markSeasonWatched(item: SyncMediaRef, seasonNumber: number, watched: boolean): Promise<void> {
    if (!this.isConnected) return;
    const series = {
      ids: { tmdb: item.tmdbId },
      ...(item.isAnime ? { use_tvdb_anime_seasons: true } : {}),
      seasons: [{ number: seasonNumber }]
    };
    const body = { shows: [series] };
    const endpoint = watched ? "/sync/history" : "/sync/history/remove";
    await this.simkl(endpoint, { method: "POST", body: JSON.stringify(body) });
    this.invalidateSnapshot();
  }

  async dismissFromContinueWatching(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const rows = await this.simkl<SimklPlaybackRow[]>("/sync/playback");
    const matching = rows.filter((row) => {
      const media = row.movie ?? row.show ?? row.anime;
      if (media?.ids?.tmdb !== item.tmdbId) return false;
      if (item.mediaType === "movie") return Boolean(row.movie);
      const number = row.episode?.number ?? row.episode?.episode;
      return (item.season == null || row.episode?.season === item.season) &&
        (item.episode == null || number === item.episode);
    });
    for (const row of matching) {
      if (row.id) {
        await this.simkl(`/sync/playback/${row.id}`, { method: "DELETE" }).catch(() => null);
      }
    }
  }

  private async resolveMedia<T extends { ids?: SimklIds; title?: string; year?: number }>(
    media: T | undefined,
    mediaType: "movie" | "tv"
  ): Promise<T | undefined> {
    if (!media || media.ids?.tmdb) return media;
    const tmdbId = await resolveTmdbId({
      mediaType,
      id: null,
      tmdbId: null,
      imdbId: media.ids?.imdb ?? null,
      title: media.title ?? null,
      year: media.year ?? null
    });
    return tmdbId ? { ...media, ids: { ...media.ids, tmdb: tmdbId } } : media;
  }

  private async sendScrobble(action: "start" | "pause" | "stop", item: SyncMediaRef & { progress: number }): Promise<void> {
    if (!this.isConnected) return;
    const scope = this.scope();
    const progress = Number.isFinite(item.progress) ? Math.min(100, Math.max(0, item.progress)) : 0;
    const body = item.mediaType === "movie"
      ? { movie: { ids: { tmdb: item.tmdbId } }, progress }
      : {
          show: { ids: { tmdb: item.tmdbId }, ...(item.isAnime ? { use_tvdb_anime_seasons: true } : {}) },
          episode: typeof item.season === "number" && typeof item.episode === "number"
            ? { season: item.season, number: item.episode }
            : undefined,
          progress
        };
    try {
      await this.simkl(`/scrobble/${action}`, { method: "POST", body: JSON.stringify(body), keepalive: true });
    } catch (error) {
      if (action !== "stop" || (error as { status?: number }).status !== 409) throw error;
    }
    // Keep metadata and the incremental watermark; only expire freshness.
    if (action === "stop" && scope === this.scope() && this.snapshot) this.snapshot.checkedAt = 0;
  }

  async scrobble(action: "start" | "pause" | "stop", item: SyncMediaRef & { progress: number }): Promise<void> {
    if (!this.isConnected) return;
    return new Promise<void>((resolve, reject) => {
      const last = this.pendingScrobbles.at(-1);
      const sameItem = last && last.scope === this.scope() && last.item.mediaType === item.mediaType &&
        last.item.tmdbId === item.tmdbId && last.item.season === item.season && last.item.episode === item.episode;
      // Coalesce quick play/pause toggles, never erase a completed episode for the next one.
      if (sameItem && last.action !== "stop") {
        last.action = action;
        last.item = { ...item };
        last.waiters.push({ resolve, reject });
      } else {
        if (this.pendingScrobbles.length >= MAX_PENDING_SCROBBLES) {
          reject(new Error("Too many pending playback updates. Please wait for tracking to finish."));
          return;
        }
        this.pendingScrobbles.push({ scope: this.scope(), action, item: { ...item }, waiters: [{ resolve, reject }] });
      }
      this.drainScrobbles();
    });
  }

  private drainScrobbles(): void {
    if (this.scrobbleInFlight || this.scrobbleTimer || !this.pendingScrobbles.length) return;
    const remaining = this.lastScrobbleWriteAt ? SCROBBLE_WRITE_LOCK_MS - (Date.now() - this.lastScrobbleWriteAt) : 0;
    if (remaining > 0) {
      this.scrobbleTimer = setTimeout(() => {
        this.scrobbleTimer = null;
        this.drainScrobbles();
      }, remaining);
      return;
    }
    const pending = this.pendingScrobbles.shift()!;
    const generation = this.scrobbleGeneration;
    this.scrobbleInFlight = true;
    this.lastScrobbleWriteAt = Date.now();
    void this.sendScrobble(pending.action, pending.item).then(
      () => { for (const waiter of pending.waiters) waiter.resolve(); },
      error => { for (const waiter of pending.waiters) waiter.reject(error); }
    ).finally(() => {
      if (generation !== this.scrobbleGeneration) return;
      this.scrobbleInFlight = false;
      this.drainScrobbles();
    });
  }
}

export const simklClient = new SimklClient();

export function getSimklItemUrl(ids?: SimklIds | null, type: "movie" | "tv" | "anime" = "movie"): string | null {
  if (!ids) return null;
  if (ids.slug) return `https://simkl.com/${type === "movie" ? "movies" : type}/${ids.slug}`;
  const simklId = ids.simkl ?? ids.simkl_id;
  if (simklId) return `https://simkl.com/${type === "movie" ? "movies" : type}/${simklId}`;
  return null;
}

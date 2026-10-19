import { SyncClient, SyncMediaRef } from "./sync";
import { loadStored, removeStored, saveStored } from "./storage";
import { jsonRequest } from "./http";
import { resolveTmdbId, tmdb } from "./tmdb";
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
  refresh_token?: string;
  expires_at?: number;
  connection_id?: string;
  server_session?: boolean;
}

export interface SimklPinCode {
  user_code: string;
  verification_url: string;
  expires_in: number;
  interval: number;
}

type SimklIds = { tmdb?: number | string; simkl?: number | string; simkl_id?: number | string; imdb?: string; slug?: string };
type SimklMovieRow = {
  movie?: { title?: string; year?: number; ids?: SimklIds };
  status?: string;
  last_watched_at?: string;
};
type SimklShowRow = {
  show?: { title?: string; year?: number; ids?: SimklIds };
  status?: string;
  last_watched_at?: string;
  mapped_tvdb_seasons?: unknown;
  seasons?: Array<{
    number?: number;
    episodes?: Array<{
      number?: number;
      watched_at?: string;
      tvdb?: { season?: number; episode?: number };
    }>;
  }>;
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
type SimklPlaybackSnapshot = {
  scope: string;
  activity: string | null;
  markers: Record<string, string | null>;
  rows: SimklPlaybackRow[];
};
type SimklSnapshot = {
  scope: string;
  activity: string | null;
  checkedAt: number;
  complete: boolean;
  initialized?: boolean;
  removals?: Partial<Record<"movies" | "shows" | "anime", string>>;
  playbackMarkers?: Record<string, string | null>;
  playback?: SimklPlaybackSnapshot;
  movies: SimklMovieRow[];
  shows: SimklShowRow[];
  anime: SimklShowRow[];
};

type SimklTmdbEpisode = { episode_number: number; name?: string; air_date?: string };
type SeasonEpisodes = { episodes?: SimklTmdbEpisode[] };
const episodeCache = new Map<string, { at: number; value: Promise<SeasonEpisodes> }>();
async function realEpisodes(id: number, season: number): Promise<SimklTmdbEpisode[]> {
  const path = `tv/${id}/season/${season}`;
  let cached = episodeCache.get(path);
  if (!cached || Date.now() - cached.at > SNAPSHOT_TTL_MS) {
    const value = tmdb<SeasonEpisodes>(path).catch(error => {
      episodeCache.delete(path);
      if ((error as { status?: number }).status === 404) return { episodes: [] };
      throw error;
    });
    cached = { at: Date.now(), value };
    episodeCache.set(path, cached);
  }
  return ((await cached.value).episodes ?? []).slice().sort((a, b) => a.episode_number - b.episode_number);
}

function watchedSeasons(row: SimklShowRow) {
  const seasons = new Map<number, Array<{ number: number; last_watched_at?: string }>>();
  for (const season of row.seasons ?? []) for (const episode of season.episodes ?? []) {
    const s = episode.tvdb?.season ?? season.number;
    const e = episode.tvdb?.episode ?? episode.number;
    if (s == null || e == null || s < 0 || e < 1) continue;
    const list = seasons.get(s) ?? [];
    list.push({ number: e, last_watched_at: episode.watched_at });
    seasons.set(s, list);
  }
  return Array.from(seasons, ([number, episodes]) => ({ number, episodes }));
}

function toTmdbNumber(id?: number | string | null): number | null {
  if (id == null) return null;
  const parsed = Number(id);
  return Number.isFinite(parsed) && parsed > 0 ? parsed : null;
}

function matchesTmdb(ids?: SimklIds | null, target?: number | string | null): boolean {
  const a = toTmdbNumber(ids?.tmdb);
  const b = toTmdbNumber(target);
  return a != null && b != null && a === b;
}

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
  const tmdb = toTmdbNumber(ids?.tmdb);
  if (tmdb != null) return `tmdb:${tmdb}`;
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
    if (id != null) {
      const existingItem = map.get(String(id));
      const incomingSeasons = (item as unknown as SimklShowRow).seasons;
      const existingSeasons = (existingItem as unknown as SimklShowRow | undefined)?.seasons;
      if (key === "show" && existingItem && incomingSeasons == null && existingSeasons && existingSeasons.length > 0) {
        map.set(String(id), { ...item, seasons: existingSeasons });
      } else {
        map.set(String(id), item);
      }
    }
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

function playbackMarkers(value: unknown): Record<string, string | null> {
  const root = (value ?? {}) as Record<string, { playback?: string } | undefined>;
  const markers = {
    movies: root.movies?.playback ?? null,
    shows: (root.tv_shows ?? root.shows)?.playback ?? null,
    anime: root.anime?.playback ?? null
  };
  return Object.values(markers).some(Boolean) ? markers : { all: activityMarker(value) };
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
  private recentCompletions = new Map<string, number>();
  private snapshotPromise: Promise<SimklSnapshot> | null = null;
  private snapshotRevision = 0;
  private playbackSnapshot: SimklPlaybackSnapshot | null = null;
  private playbackPromise: Promise<SimklPlaybackRow[]> | null = null;
  private lastPlaybackFailureAt = 0;
  private lastSnapshotFailureAt = 0;
  private lastScrobbleWriteAt = 0;
  private pendingScrobbles: PendingScrobble[] = [];
  private scrobbleInFlight = false;
  private scrobbleGeneration = 0;
  private scrobbleTimer: ReturnType<typeof setTimeout> | null = null;

  get isConnected(): boolean {
    return Boolean(this.token?.access_token);
  }

  get isV2Connected(): boolean { return Boolean(this.token?.server_session || this.token?.refresh_token); }

  private deviceSession: { user_code: string; profileId: string | null; interval: number; nextPoll: number; deadline: number } | null = null;
  private sessionGeneration = 0;
  private restorePromise: Promise<void> | null = null;

  private async sessionRequest<T>(profile: string, input?: Record<string, unknown>, connection?: string): Promise<T> {
    const response = await fetch("/api/simkl/session", {
      method: input ? "POST" : "GET", credentials: "same-origin", cache: "no-store",
      headers: { "Content-Type": "application/json", "x-simkl-profile": profile, ...(connection ? { "x-simkl-connection": connection } : {}) },
      body: input ? JSON.stringify(input) : undefined, signal: AbortSignal.timeout(30_000)
    });
    const data = await response.json();
    if (!response.ok || data.error) throw Object.assign(new Error(data.message || data.error || `SIMKL HTTP ${response.status}`), { reason: data.error, status: response.status });
    return data as T;
  }

  private disconnectKey(profile: string) { return `arvio.web.simkl.disconnect:${profile}`; }

  /** Migrate old browser grants once; thereafter only non-secret connection metadata is stored. */
  async restoreSession(): Promise<void> {
    const profile = this.profileId;
    if (!profile) return;
    if (this.restorePromise) return this.restorePromise;
    const generation = this.sessionGeneration;
    const operation = (async () => {
      const pendingDisconnect = loadStored<{ connection?: string } | null>(this.disconnectKey(profile), null);
      if (pendingDisconnect) {
        await this.sessionRequest(profile, { action: "disconnect" }, pendingDisconnect.connection);
        removeStored(this.disconnectKey(profile));
        return;
      }
      const old = this.token;
      const data = await this.sessionRequest<{ token: SimklToken | null }>(profile,
        old?.refresh_token ? { action: "migrate", refresh_token: old.refresh_token, connection_id: old.connection_id } : undefined);
      if (profile !== this.profileId || generation !== this.sessionGeneration) return;
      if (data.token) {
        if (!data.token.server_session || data.token.refresh_token || !data.token.connection_id) throw new Error("Invalid SIMKL session response");
        this.setToken(data.token);
      } else if (old?.server_session) this.setToken(null);
    })();
    const pending = operation.finally(() => { if (this.restorePromise === pending) this.restorePromise = null; });
    this.restorePromise = pending;
    return pending;
  }

  private tokenKey(profileId: string): string {
    return `arvio.web.simkl.token:${profileId}`;
  }

  private snapshotKey(profileId: string): string {
    return `arvio.web.simkl.snapshot:${profileId}`;
  }

  private persistSnapshot(snapshot: SimklSnapshot) {
    if (!this.profileId || !this.token) return;
    saveStored(this.snapshotKey(this.profileId), snapshot);
  }

  setProfile(profileId: string | null) {
    const normalized = profileId?.trim() || null;
    if (normalized === this.profileId) return;
    this.resetScrobbleQueue();
    this.recentCompletions.clear();
    this.deviceSession = null;
    this.sessionGeneration++;
    this.restorePromise = null;
    this.profileId = normalized;
    this.snapshot = null;
    this.snapshotPromise = null;
    this.playbackSnapshot = null;
    this.playbackPromise = null;
    this.lastPlaybackFailureAt = 0;
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
    if (this.token && normalized) {
      const persisted = loadStored<SimklSnapshot | null>(this.snapshotKey(normalized), null);
      if (persisted && persisted.scope === this.scope()) {
        this.snapshot = persisted;
      }
    }
  }

  setToken(token: SimklToken | null) {
    if (token?.refresh_token || token?.access_token.startsWith("simkl_at_")) throw new Error("SIMKL V2 credentials must remain on the server");
    const next = token?.access_token ? token : null;
    const tokenChanged = (next?.connection_id ?? next?.access_token) !== (this.token?.connection_id ?? this.token?.access_token);
    if (tokenChanged) {
      this.resetScrobbleQueue();
      this.recentCompletions.clear();
      if (this.profileId) removeStored(this.snapshotKey(this.profileId));
      this.snapshot = null;
      this.snapshotPromise = null;
      this.playbackSnapshot = null;
      this.playbackPromise = null;
      this.lastPlaybackFailureAt = 0;
      this.lastSnapshotFailureAt = 0;
    }
    this.token = next;
    if (!this.profileId) return;
    if (this.token) saveStored(this.tokenKey(this.profileId), this.token);
    else removeStored(this.tokenKey(this.profileId));
  }

  async disconnect(): Promise<void> {
    const profile = this.profileId;
    const old = this.token;
    this.sessionGeneration++;
    this.restorePromise = null;
    this.deviceSession = null;
    if (this.profileId) removeStored(this.snapshotKey(this.profileId));
    this.setToken(null);
    if (profile && (old?.server_session || old?.refresh_token)) {
      // A failed network request must not reconnect the account on the next refresh.
      saveStored(this.disconnectKey(profile), { connection: old.connection_id });
      await this.sessionRequest(profile, { action: "disconnect", ...(old.refresh_token ? { refresh_token: old.refresh_token, connection_id: old.connection_id } : {}) }, old.connection_id);
      removeStored(this.disconnectKey(profile));
    }
  }

  private async simkl<T>(path: string, options: RequestInit = {}, accessToken = this.token?.access_token): Promise<T> {
    const scope = this.scope();
    if (this.token?.refresh_token) {
      await this.restoreSession();
      if (scope !== this.scope()) throw new Error("SIMKL profile changed before the request was sent");
      accessToken = this.token?.access_token;
    }
    const headers: Record<string, string> = {
      "content-type": "application/json",
      ...(options.headers as Record<string, string>)
    };
    if (this.token?.server_session) {
      headers["x-simkl-profile"] = this.profileId!;
      headers["x-simkl-connection"] = this.token.connection_id!;
    } else if (accessToken) headers["x-user-token"] = accessToken;

    const [pathname, queryString] = path.split("?");
    const params = new URLSearchParams(queryString || "");
    if (!params.has("app-name")) params.set("app-name", "arvio");
    if (!params.has("app-version")) params.set("app-version", "2.0");
    if (config.simklClientId && !params.has("client_id")) {
      params.set("client_id", this.token?.server_session ? config.simklV2ClientId : config.simklClientId);
    }
    const finalQuery = params.toString();
    const finalUrl = `/api/simkl${pathname}${finalQuery ? `?${finalQuery}` : ""}`;

    return jsonRequest<T>(finalUrl, { ...options, credentials: "same-origin", headers });
  }

  private scope(): string {
    return `${this.profileId ?? "none"}:${this.token?.connection_id ?? this.token?.access_token ?? "none"}`;
  }

  private invalidateSnapshot() {
    this.snapshotRevision++;
    if (this.snapshot) {
      this.snapshot.checkedAt = 0;
      this.persistSnapshot(this.snapshot);
    }
    // A write must not detach an in-flight read or bypass failure backoff.
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
    if (this.snapshotPromise) return this.snapshotPromise;
    const cached = this.snapshot?.scope === scope ? this.snapshot : null;
    const now = Date.now();
    if (cached?.complete && now - cached.checkedAt < SNAPSHOT_TTL_MS) return cached;
    if (now - this.lastSnapshotFailureAt < FAILED_SNAPSHOT_RETRY_MS) {
      return cached ?? {
        scope, activity: null, checkedAt: now, complete: false,
        movies: [], shows: [], anime: []
      };
    }
    const revision = this.snapshotRevision;
    const request = (async () => {
      const activities = await this.simkl<unknown>("/sync/activities", {}, accessToken);
      const marker = activityMarker(activities);
      const removals = removalMarkers(activities);
      const currentPlaybackMarkers = playbackMarkers(activities);
      const removedTypes = (["movies", "shows", "anime"] as const)
        .filter(type => removals[type] && removals[type] !== cached?.removals?.[type]);
      if (cached?.complete && marker && marker === cached.activity && !removedTypes.length) {
        return { ...cached, checkedAt: Date.now(), playbackMarkers: currentPlaybackMarkers };
      }

      let moviesResult: SimklMovieRow[] = [];
      let showsResult: SimklShowRow[] = [];
      let animeResult: SimklShowRow[] = [];
      let complete = false;

      if (cached?.initialized && cached.activity) {
        // Continuous sync delta (Phase 2): single request for all types modified since watermark
        try {
          const deltaQuery = `?date_from=${encodeURIComponent(cached.activity)}&extended=full_anime_seasons&episode_watched_at=yes&include_all_episodes=yes&next_watch_info=yes`;
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
        playbackMarkers: currentPlaybackMarkers,
        playback: this.playbackSnapshot ?? cached?.playback,
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
      if (result.scope === this.scope()) {
        // A write completed during this read: do not mark its older snapshot fresh.
        if (this.snapshotRevision !== revision) result.checkedAt = 0;
        this.snapshot = result;
        if (result.complete) this.persistSnapshot(result);
      }
      return result;
    } catch (error) {
      if (scope === this.scope()) this.lastSnapshotFailureAt = Date.now();
      throw error;
    } finally {
      if (this.snapshotPromise === request) this.snapshotPromise = null;
    }
  }

  private async loadPlayback(snapshot: SimklSnapshot): Promise<SimklPlaybackRow[]> {
    const scope = this.scope();
    const cached = this.playbackSnapshot?.scope === scope ? this.playbackSnapshot
      : snapshot.playback?.scope === scope ? snapshot.playback : null;
    const markers = snapshot.playbackMarkers ?? { all: snapshot.activity ?? null };
    if (cached && snapshot.activity && JSON.stringify(cached.markers) === JSON.stringify(markers)) {
      return cached.rows;
    }
    if (this.playbackPromise) return this.playbackPromise;
    if (Date.now() - this.lastPlaybackFailureAt < FAILED_SNAPSHOT_RETRY_MS) {
      throw new Error("SIMKL playback sync will retry shortly");
    }
    const request = (async () => {
      let rows: SimklPlaybackRow[];
      if (!cached?.activity) {
        rows = await this.simkl<SimklPlaybackRow[]>("/sync/playback");
      } else {
        const from = encodeURIComponent(cached.activity);
        const delta = await this.simkl<SimklPlaybackRow[]>(`/sync/playback?date_from=${from}`);
        // No deletion tombstones: check the older partition only after activity changes.
        const older = cached.rows.length || snapshot.activity !== cached.activity
          ? await this.simkl<SimklPlaybackRow[]>(`/sync/playback?date_to=${from}`) : [];
        if (!Array.isArray(delta) || !Array.isArray(older)) throw new Error("Unexpected SIMKL playback response");
        rows = [...older, ...delta];
      }
      if (!Array.isArray(rows)) throw new Error("Unexpected SIMKL playback response");
      if (scope !== this.scope()) throw new Error("SIMKL profile changed during playback sync");
      this.playbackSnapshot = { scope, activity: snapshot.activity ?? null, markers, rows };
      if (this.snapshot?.scope === scope) {
        this.snapshot.playback = this.playbackSnapshot;
        this.persistSnapshot(this.snapshot);
      }
      this.lastPlaybackFailureAt = 0;
      return rows;
    })();
    this.playbackPromise = request;
    try {
      return await request;
    } catch (error) {
      if (scope === this.scope()) this.lastPlaybackFailureAt = Date.now();
      throw error;
    } finally {
      if (this.playbackPromise === request) this.playbackPromise = null;
    }
  }

  findItemIds(tmdbId: number | string, mediaType: "movie" | "tv" | "anime" = "movie"): SimklIds | null {
    if (!this.snapshot) return null;
    if (mediaType === "movie") {
      const found = this.snapshot.movies.find(row => matchesTmdb(row.movie?.ids, tmdbId));
      return found?.movie?.ids ?? null;
    } else {
      const rows = mediaType === "anime" ? this.snapshot.anime : [...this.snapshot.shows, ...this.snapshot.anime];
      const found = rows.find(row => matchesTmdb(row.show?.ids, tmdbId));
      return found?.show?.ids ?? null;
    }
  }

  async beginPinAuth(): Promise<SimklPinCode> {
    const profileId = this.profileId;
    if (!profileId) throw new Error("Select an ARVIO profile first");
    const generation = ++this.sessionGeneration;
    this.restorePromise = null;
    if (loadStored(this.disconnectKey(profileId), null)) await this.restoreSession();
    const data = await this.sessionRequest<SimklPinCode>(profileId, { action: "start" });
    if (profileId !== this.profileId || generation !== this.sessionGeneration) throw new Error("SIMKL profile changed");
    const interval = Math.max(5, data.interval || 5);
    this.deviceSession = { user_code: data.user_code, profileId, interval,
      nextPoll: Date.now() + interval * 1000, deadline: Date.now() + data.expires_in * 1000 };
    return { ...data, interval };
  }

  customListsRequest<T>(path: string, options: RequestInit = {}): Promise<T> { return this.simkl<T>(path, options); }

  async pollPinToken(userCode: string): Promise<SimklToken | null> {
    const session = this.deviceSession;
    if (!session || session.user_code !== userCode || session.profileId !== this.profileId || Date.now() >= session.deadline) throw new Error("SIMKL sign-in expired; start again");
    if (Date.now() < session.nextPoll) return null;
    session.nextPoll = Date.now() + session.interval * 1000;
    const data = await this.sessionRequest<{ token: SimklToken | null }>(session.profileId!, { action: "poll", user_code: userCode });
    if (session !== this.deviceSession || session.profileId !== this.profileId) return null;
    if (!data.token) return null;
    if (!data.token.server_session || data.token.refresh_token || !data.token.connection_id) throw new Error("Invalid SIMKL session response");
    this.deviceSession = null;
    return data.token;
  }

  async watchlist(statuses: Array<"plantowatch" | "watching"> = ["plantowatch"], options: { throwOnError?: boolean; resolveIds?: boolean } = {}): Promise<unknown[]> {
    const snapshot = await this.loadSnapshot();
    if (options.throwOnError && !snapshot.complete) throw new Error("SIMKL watchlist is unavailable");
    const movies = (await Promise.all(snapshot.movies
      .filter((item) => statuses.includes(item.status as "plantowatch" | "watching"))
      .map(async (item) => ({
        type: "movie",
        movie: options.resolveIds === false ? item.movie : await this.resolveMedia(item.movie, "movie"),
        listed_at: item.last_watched_at
      })))).filter((item) => item.movie && (options.resolveIds === false || item.movie.ids?.tmdb != null));
    const shows = (await Promise.all([...snapshot.shows, ...snapshot.anime]
      .filter((item) => statuses.includes(item.status as "plantowatch" | "watching"))
      .map(async (item) => ({
        type: "show",
        show: options.resolveIds === false ? item.show : await this.resolveMedia(item.show, "tv"),
        listed_at: item.last_watched_at
      })))).filter((item) => item.show && (options.resolveIds === false || item.show.ids?.tmdb != null));
    return [...movies, ...shows];
  }

  async library(status: "plantowatch" | "watching" | "completed" | "hold" | "dropped"): Promise<unknown[]> {
    const snapshot = await this.loadSnapshot();
    const rows = [
      ...snapshot.movies.filter((row) => row.status === status || (status === "dropped" && row.status === "notinteresting")).map((row) => ({ type: "movie", movie: row.movie, listed_at: row.last_watched_at })),
      ...[...snapshot.shows, ...snapshot.anime].filter((row) => row.status === status || (status === "dropped" && row.status === "notinteresting")).map((row) => ({ type: "show", show: row.show, listed_at: row.last_watched_at }))
    ];
    // Identity/artwork is resolved by the shared bounded tracker hydrator.
    return rows;
  }

  async playback(localWatchedKeys = new Set<string>()): Promise<unknown[]> {
    return this.continueWatching(localWatchedKeys);
  }

  async continueWatching(localWatchedKeys = new Set<string>()): Promise<unknown[]> {
    const scope = this.scope();
    const snapshot = await this.loadSnapshot();
    if (!snapshot.complete) throw new Error("SIMKL Continue Watching is unavailable");
    let playbackFailure: unknown;
    const playback = await this.loadPlayback(snapshot).catch(error => { playbackFailure = error; return []; });
    const shows = await Promise.all([...snapshot.shows, ...snapshot.anime].map(async row => ({ ...row, show: await this.resolveMedia(row.show, "tv") })));
    const watched = new Map<string, number>();
    const completedShows = new Map<number, number>();
    for (const row of snapshot.movies) if (row.status === "completed") {
      const movie = await this.resolveMedia(row.movie, "movie");
      if (movie?.ids?.tmdb) watched.set(`movie:${movie.ids.tmdb}`, Date.parse(row.last_watched_at ?? "") || 0);
    }
    for (const row of shows) {
      const id = toTmdbNumber(row.show?.ids?.tmdb);
      if (!id) continue;
      if (row.status === "completed") completedShows.set(id, Date.parse(row.last_watched_at ?? "") || 0);
      for (const season of watchedSeasons(row)) for (const episode of season.episodes) {
        watched.set(`tv:${id}:${season.number}:${episode.number}`, Date.parse(episode.last_watched_at ?? "") || 0);
      }
    }
    for (const [key, at] of this.recentCompletions) {
      if (Date.now() - at > SNAPSHOT_TTL_MS) this.recentCompletions.delete(key);
      else {
        watched.set(key, Math.max(watched.get(key) ?? 0, at));
        if (/^tv:\d+$/.test(key)) completedShows.set(Number(key.split(":")[1]), at);
      }
    }
    const normalized = (await Promise.all(playback.map(async (row) => ({
      ...row,
      movie: await this.resolveMedia(row.movie, "movie"),
      show: await this.resolveMedia(row.show ?? row.anime, "tv"),
      episode: row.episode
        ? { ...row.episode, number: row.episode.number ?? row.episode.episode }
        : undefined
    })))).filter((row) => {
      const id = toTmdbNumber(row.show?.ids?.tmdb ?? row.movie?.ids?.tmdb);
      if (!id || !(Number(row.progress) > 0 && Number(row.progress) < 95)) return false;
      const key = row.show ? `tv:${id}:${row.episode?.season}:${row.episode?.number}` : `movie:${id}`;
      const complete = watched.has(key) || (Boolean(row.show) && completedShows.has(id));
      if (!complete) return !localWatchedKeys.has(key);
      const at = Math.max(watched.get(key) ?? 0, row.show ? completedShows.get(id) ?? 0 : 0);
      // Unknown SIMKL history timestamps cannot establish that this pause is a rewatch.
      return at > 1000 && (Date.parse(row.paused_at ?? "") || 0) > at;
    });
    const validPauses = [];
    for (const row of normalized) {
      const id = toTmdbNumber(row.show?.ids?.tmdb);
      if (!id) { validPauses.push(row); continue; }
      const season = row.episode?.season, episode = row.episode?.number;
      if (season == null || season < 0 || !episode || episode < 1) continue;
      if ((await realEpisodes(id, season)).some(e => e.episode_number === episode)) validPauses.push(row);
    }
    const pausedShows = new Set(validPauses.map(row => toTmdbNumber(row.show?.ids?.tmdb)).filter(Boolean));
    const upNext = [];
    // Bound metadata requests; season responses are shared across consecutive refreshes.
    for (let i = 0; i < shows.length; i += 4) upNext.push(...(await Promise.all(shows.slice(i, i + 4).map(async (row) => {
      if (row.status !== "watching") return null;
      const show = row.show;
      const pointer = parseNextToWatch(row.next_to_watch);
      const mapped = Array.isArray(row.mapped_tvdb_seasons) ? row.mapped_tvdb_seasons.filter(Number.isInteger) as number[] : [];
      const season = /^S/i.test(row.next_to_watch ?? "") ? pointer?.season
        : row.next_to_watch_info?.season ?? (mapped.length === 1 ? mapped[0] : pointer?.season);
      const number = pointer?.episode;
      const showTmdb = toTmdbNumber(show?.ids?.tmdb);
      if (!showTmdb || !number || pausedShows.has(showTmdb)) return null;
      if (completedShows.has(showTmdb)) return null;
      if (season == null || season < 0) return null;
      let rows = await realEpisodes(showTmdb, season);
      if (!rows.some(e => e.episode_number === number)) return null;
      const isWatched = (s: number, e: number) => watched.has(`tv:${showTmdb}:${s}:${e}`) || localWatchedKeys.has(`tv:${showTmdb}:${s}:${e}`);
      let selectedSeason = season;
      let selected = rows.find(e => e.episode_number >= number && !isWatched(season, e.episode_number));
      if (!selected) {
        const details = await tmdb<{ seasons?: Array<{ season_number: number }> }>(`tv/${showTmdb}`);
        for (const s of (details.seasons ?? []).map(s => s.season_number).filter(s => s > season).sort((a, b) => a - b)) {
          rows = await realEpisodes(showTmdb, s);
          selected = rows.find(e => !isWatched(s, e.episode_number));
          if (selected) { selectedSeason = s; break; }
        }
      }
      if (!selected?.air_date || selected.air_date > new Date().toISOString().slice(0, 10)) return null;
      return {
        progress: 0,
        paused_at: row.last_watched_at,
        show,
        episode: { season: selectedSeason, number: selected.episode_number, title: selected.name },
        is_up_next: true
      };
    }))).filter(Boolean));
    const result = [...validPauses, ...upNext];
    if (scope !== this.scope()) throw new Error("SIMKL profile changed");
    // A failed pause read may still yield verified Up Next, but cannot prove an empty rail.
    if (playbackFailure && !result.length) throw playbackFailure;
    return result;
  }

  async watched(type: "movies" | "shows"): Promise<unknown[]> {
    const snapshot = await this.loadSnapshot();
    if (type === "movies") {
      return (await Promise.all(snapshot.movies.filter((item) =>
        item.status === "completed"
      ).map(async (item) => ({ ...item, movie: await this.resolveMedia(item.movie, "movie") }))))
        .filter((item) => item.movie?.ids?.tmdb != null);
    }
    return (await Promise.all([...snapshot.shows, ...snapshot.anime]
      .map(async (item) => ({ ...item, seasons: watchedSeasons(item), show: await this.resolveMedia(item.show, "tv") }))))
      .filter((item) => item.show?.ids?.tmdb != null);
  }

  async addToWatchlist(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const body = item.mediaType === "movie"
      ? { movies: [{ to: "plantowatch", ids: { tmdb: item.tmdbId } }] }
      : item.isAnime
        ? { anime: [{ to: "plantowatch", ids: { tmdb: item.tmdbId }, use_tvdb_anime_seasons: true }] }
        : { shows: [{ to: "plantowatch", ids: { tmdb: item.tmdbId }, use_tvdb_anime_seasons: true }] };
    await this.simkl("/sync/add-to-list", { method: "POST", body: JSON.stringify(body) });
    this.invalidateSnapshot();
  }

  async removeFromWatchlist(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const snapshot = await this.loadSnapshot();
    const watched = item.mediaType === "movie"
      ? snapshot.movies.some((row) => matchesTmdb(row.movie?.ids, item.tmdbId) && Boolean(row.last_watched_at))
      : [...snapshot.shows, ...snapshot.anime].some((row) =>
          matchesTmdb(row.show?.ids, item.tmdbId) && (Boolean(row.last_watched_at) || row.seasons?.some((s) => s.episodes?.length))
        );
    const body = item.mediaType === "movie"
      ? { movies: [{ ...(watched ? { to: "completed" } : {}), ids: { tmdb: item.tmdbId } }] }
      : item.isAnime
        ? { anime: [{ ...(watched ? { to: "completed" } : {}), ids: { tmdb: item.tmdbId }, use_tvdb_anime_seasons: true }] }
        : { shows: [{ ...(watched ? { to: "completed" } : {}), ids: { tmdb: item.tmdbId }, use_tvdb_anime_seasons: true }] };
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
      use_tvdb_anime_seasons: true,
      seasons: hasEpisode ? [{ number: item.season!, episodes: [{ number: item.episode! }] }] : undefined
    };
    const body = item.mediaType === "movie"
      ? { movies: [{ ids: { tmdb: item.tmdbId } }] }
      : { shows: [series] };
    await this.simkl("/sync/history", { method: "POST", body: JSON.stringify(body) });
    this.recentCompletions.set(item.mediaType === "movie" ? `movie:${item.tmdbId}` : hasEpisode ? `tv:${item.tmdbId}:${item.season}:${item.episode}` : `tv:${item.tmdbId}`, Date.now());
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
        use_tvdb_anime_seasons: true,
        seasons: hasEpisode ? [{ number: item.season!, episodes: [{ number: item.episode! }] }] : undefined
      };
      const body = { shows: [series] };
      await this.simkl("/sync/history/remove", { method: "POST", body: JSON.stringify(body) });
    }
    this.recentCompletions.delete(item.mediaType === "movie" ? `movie:${item.tmdbId}` : `tv:${item.tmdbId}:${item.season}:${item.episode}`);
    if (item.mediaType !== "movie") this.recentCompletions.delete(`tv:${item.tmdbId}`);
    this.invalidateSnapshot();
  }

  async markSeasonWatched(item: SyncMediaRef, seasonNumber: number, watched: boolean): Promise<void> {
    if (!this.isConnected) return;
    const series = {
      ids: { tmdb: item.tmdbId },
      use_tvdb_anime_seasons: true,
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
      if (!matchesTmdb(media?.ids, item.tmdbId)) return false;
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
      await this.simkl(`/scrobble/${action}${action === "pause" ? "" : "?min_progress=90"}`, { method: "POST", body: JSON.stringify(body), keepalive: true });
    } catch (error) {
      if (action !== "stop" || (error as { status?: number }).status !== 409) throw error;
    }
    // Keep metadata and the incremental watermark; only expire freshness.
    if (action === "stop" && scope === this.scope()) this.invalidateSnapshot();
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
  const section = type === "movie" ? "movies" : type;
  const simklId = ids.simkl ?? ids.simkl_id;
  if (simklId != null) {
    return ids.slug ? `https://simkl.com/${section}/${simklId}/${ids.slug}` : `https://simkl.com/${section}/${simklId}`;
  }
  if (ids.slug) return `https://simkl.com/${section}/${ids.slug}`;
  return null;
}

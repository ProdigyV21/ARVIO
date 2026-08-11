import { jsonRequest } from "./http";
import { loadStored, removeStored, saveStored } from "./storage";

const MDBLIST_KEY_STORAGE = "arvio.web.mdblist.key";

export interface MdbMediaRef {
  mediaType: "movie" | "tv";
  tmdbId: number;
  season?: number | null;
  episode?: number | null;
}

/**
 * MDBList client. Per-profile alternative to Trakt, authenticated with a static
 * user API key (mdblist.com/preferences). All calls go through the
 * `/api/mdblist` proxy (CORS + key injection).
 *
 * Read methods intentionally return data in the SAME shapes as TraktClient
 * (watchlist / playback / watched), so the existing mappers and store pipeline
 * work unchanged regardless of the active provider.
 */
export class MdbListClient {
  key = loadStored<string | null>(MDBLIST_KEY_STORAGE, null);

  get isConnected() {
    return Boolean(this.key);
  }

  setKey(key: string | null) {
    const trimmed = key?.trim();
    this.key = trimmed ? trimmed : null;
    this.watchedCache = null; // don't serve a previous key's watched history
    if (this.key) saveStored(MDBLIST_KEY_STORAGE, this.key);
    else removeStored(MDBLIST_KEY_STORAGE);
  }

  disconnect() {
    this.setKey(null);
  }

  async validateKey(key: string): Promise<boolean> {
    try {
      const user = await this.request<{ username?: string }>("user", { headers: { "x-mdblist-key": key.trim() } });
      return Boolean(user?.username);
    } catch {
      return false;
    }
  }

  // ===== Reads (Trakt-compatible shapes) =====

  async watchlist(): Promise<unknown[]> {
    if (!this.key) return [];
    const rows = await this.request<MdbWatchlistRow[]>("watchlist/items?unified=true&limit=1000", {}).catch(() => []);
    return (rows ?? []).map((row) => {
      const isShow = row.mediatype === "show";
      const media = { title: row.title, year: row.release_year, ids: { tmdb: row.ids?.tmdb ?? row.id } };
      return isShow
        ? { type: "show", listed_at: row.watchlist_at, show: media }
        : { type: "movie", listed_at: row.watchlist_at, movie: media };
    });
  }

  async playback(): Promise<unknown[]> {
    if (!this.key) return [];
    const rows = await this.request<MdbPlaybackRow[]>("sync/playback", {}).catch(() => []);
    return (rows ?? []).map((row) => {
      const progress = Number(row.progress) || 0;
      if (row.type === "movie") {
        return {
          progress,
          paused_at: row.paused_at,
          movie: { title: row.movie?.title, year: row.movie?.year, ids: { tmdb: row.movie?.ids?.tmdb } }
        };
      }
      return {
        progress,
        paused_at: row.paused_at,
        show: { title: row.show?.title, year: row.show?.year, ids: { tmdb: row.show?.ids?.tmdb } },
        episode: { season: row.episode?.season, number: row.episode?.number, title: row.episode?.title }
      };
    });
  }

  async watched(type: "movies" | "shows"): Promise<unknown[]> {
    if (!this.key) return [];
    const data = await this.fetchAllWatched();
    if (type === "movies") {
      return (data.movies ?? [])
        .map((m) => ({ movie: { ids: { tmdb: m.movie?.ids?.tmdb } } }))
        .filter((m) => Boolean(m.movie.ids.tmdb));
    }
    // Group MDBList episodes[] into Trakt watched-shows shape so traktWatchedKeys works.
    const byShow = new Map<number, Map<number, Set<number>>>();
    (data.episodes ?? []).forEach((entry) => {
      const ep = entry.episode;
      const showTmdb = ep?.show?.ids?.tmdb;
      const season = ep?.season;
      const number = ep?.number;
      if (!showTmdb || season == null || number == null) return;
      let seasons = byShow.get(showTmdb);
      if (!seasons) {
        seasons = new Map();
        byShow.set(showTmdb, seasons);
      }
      let episodes = seasons.get(season);
      if (!episodes) {
        episodes = new Set();
        seasons.set(season, episodes);
      }
      episodes.add(number);
    });
    return Array.from(byShow.entries()).map(([showTmdb, seasons]) => ({
      show: { ids: { tmdb: showTmdb } },
      seasons: Array.from(seasons.entries()).map(([number, episodes]) => ({
        number,
        episodes: Array.from(episodes).map((n) => ({ number: n }))
      }))
    }));
  }

  private watchedCache: { at: number; data: Promise<{ movies: MdbWatchedMovieRow[]; episodes: MdbWatchedEpisodeRow[] }> } | null = null;

  private fetchAllWatched() {
    // watched("movies") and watched("shows") both call this in the same refresh
    // (store.tsx Promise.all). Share one in-flight fetch (30s TTL) so the full
    // history isn't paginated twice — halves /api/mdblist invocations per refresh.
    if (this.watchedCache && Date.now() - this.watchedCache.at < 30_000) {
      return this.watchedCache.data;
    }
    const data = this.loadAllWatched();
    this.watchedCache = { at: Date.now(), data };
    return data;
  }

  private async loadAllWatched() {
    const movies: MdbWatchedMovieRow[] = [];
    const episodes: MdbWatchedEpisodeRow[] = [];
    const limit = 1000;
    // Hard page cap: never loop indefinitely on a bad has_more, even if it costs
    // a truncated tail (20k movies / 20k episodes is far beyond any real library).
    for (let page = 0; page < 20; page += 1) {
      const resp = await this.request<MdbWatchedResponse>(`sync/watched?limit=${limit}&offset=${page * limit}`, {}).catch(() => null);
      if (!resp) break;
      if (resp.movies) movies.push(...resp.movies);
      if (resp.episodes) episodes.push(...resp.episodes);
      if (!resp.pagination?.has_more) break;
    }
    return { movies, episodes };
  }

  // MDBList has no per-show up-next progress endpoint here; Continue Watching is
  // built from paused playback sessions only.
  async showProgress(): Promise<null> {
    return null;
  }

  // ===== Writes =====

  async addToWatchlist(item: MdbMediaRef) {
    await this.modifyWatchlist("add", item);
  }

  async removeFromWatchlist(item: MdbMediaRef) {
    await this.modifyWatchlist("remove", item);
  }

  private async modifyWatchlist(action: "add" | "remove", item: MdbMediaRef) {
    if (!this.key) return;
    const body = item.mediaType === "tv"
      ? { shows: [{ tmdb: item.tmdbId }] }
      : { movies: [{ tmdb: item.tmdbId }] };
    await this.request(`watchlist/items/${action}`, { method: "POST", body: JSON.stringify(body) });
  }

  async addToHistory(item: MdbMediaRef) {
    if (!this.key) return;
    await this.request("sync/watched", { method: "POST", body: JSON.stringify(this.watchedBody(item)) });
  }

  async removeFromHistory(item: MdbMediaRef) {
    if (!this.key) return;
    await this.request("sync/watched/remove", { method: "POST", body: JSON.stringify(this.watchedBody(item)) });
  }

  async dismissFromContinueWatching(_item: MdbMediaRef) {
    // MDBList does not expose paused-playback dismissal. Keep watched history intact.
  }

  private watchedBody(item: MdbMediaRef) {
    if (item.mediaType === "tv") {
      const ids = { tmdb: item.tmdbId };
      if (item.season && item.episode) {
        return { shows: [{ ids, seasons: [{ number: item.season, episodes: [{ number: item.episode }] }] }] };
      }
      return { shows: [{ ids }] };
    }
    return { movies: [{ ids: { tmdb: item.tmdbId } }] };
  }

  async scrobble(action: "start" | "pause" | "stop", item: MdbMediaRef & { progress: number }) {
    if (!this.key) return;
    const progress = Math.round(item.progress);
    let body: unknown;
    if (item.mediaType === "tv") {
      if (!item.season || !item.episode) return; // MDBList needs season+episode to scrobble an episode
      body = {
        progress,
        show: { ids: { tmdb: item.tmdbId }, season: { number: item.season, episode: { number: item.episode } } }
      };
    } else {
      body = { progress, movie: { ids: { tmdb: item.tmdbId } } };
    }
    await this.request(`scrobble/${action}`, { method: "POST", body: JSON.stringify(body) });
    if (action === "stop" && progress >= 80) {
      await this.addToHistory(item).catch(() => undefined);
    }
  }

  private async request<T>(path: string, init: RequestInit): Promise<T> {
    // Build a PLAIN object: jsonRequest spreads init.headers, and spreading a
    // Headers instance yields {} (Headers isn't enumerable), dropping our key.
    const headers: Record<string, string> = {};
    new Headers(init.headers ?? {}).forEach((value, key) => {
      headers[key] = value;
    });
    if (!headers["x-mdblist-key"] && this.key) headers["x-mdblist-key"] = this.key;
    if (init.body && !headers["content-type"]) headers["content-type"] = "application/json";
    const url = new URL(`/api/mdblist/${path.replace(/^\/+/, "")}`, window.location.origin);
    return jsonRequest<T>(url.toString(), {
      ...init,
      headers,
      cache: "no-store",
      signal: init.signal ?? (typeof AbortSignal.timeout === "function" ? AbortSignal.timeout(15_000) : undefined)
    });
  }
}

export const mdblistClient = new MdbListClient();

// ===== MDBList response shapes (raw) =====

interface MdbIds {
  tmdb?: number;
  imdb?: string;
  trakt?: number;
  tvdb?: number;
  mdblist?: string;
}

interface MdbWatchlistRow {
  id?: number;
  mediatype?: string;
  ids?: MdbIds;
  title?: string;
  release_year?: number;
  watchlist_at?: string;
}

interface MdbMovieInfo {
  title?: string;
  year?: number;
  ids?: MdbIds;
}

interface MdbEpisodeInfo {
  season?: number;
  number?: number;
  title?: string;
  ids?: MdbIds;
  show?: MdbMovieInfo;
}

interface MdbPlaybackRow {
  progress?: string;
  paused_at?: string;
  type?: string;
  movie?: MdbMovieInfo;
  show?: MdbMovieInfo;
  episode?: MdbEpisodeInfo;
}

interface MdbWatchedMovieRow {
  movie?: MdbMovieInfo;
}

interface MdbWatchedEpisodeRow {
  episode?: MdbEpisodeInfo;
}

interface MdbWatchedResponse {
  movies?: MdbWatchedMovieRow[];
  episodes?: MdbWatchedEpisodeRow[];
  pagination?: { has_more?: boolean };
}

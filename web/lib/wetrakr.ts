import type { SyncClient, SyncMediaRef } from "./sync";

type Row = Record<string, any>;
type Marker = { connection_id: string; server_session: true };
export type WeTrakrCode = { user_code: string; verification_url: string; expires_in: number; interval: number; expiresAt: number };
const tmdb = (row: Row): number | null => Number.isSafeInteger(Number(row.ids?.tmdb)) && Number(row.ids.tmdb) > 0 ? Number(row.ids.tmdb) : null;
const media = (row: Row) => ({ ...row, ids: { ...row.ids, tmdb: tmdb(row) }, year: Number(String(row.release_date || row.first_air_date || "").slice(0, 4)) || undefined });
export const weTrakrEpisodeKey = (row: Row) => tmdb(row.show || {}) && Number.isInteger(row.season_number) && row.season_number >= 0 && Number.isInteger(row.number) && row.number > 0
  ? `show_tmdb:${tmdb(row.show)}:${row.season_number}:${row.number}` : null;
export function weTrakrBody(item: SyncMediaRef, status: string) {
  if (item.tmdbId <= 0) throw new Error("Missing title identity");
  const entry: Row = { ids: { tmdb: item.tmdbId }, status };
  if (item.mediaType === "tv" && item.season != null && item.episode != null) {
    if (item.season < 0 || item.episode < 1) throw new Error("Invalid episode");
    entry.seasons = [{ number: item.season, episodes: [{ number: item.episode, status }] }];
  }
  return { allow_rewatch: false, [item.mediaType === "movie" ? "movies" : "shows"]: [entry] };
}
export function weTrakrPlaybackRef(item: SyncMediaRef) {
  if (item.tmdbId <= 0) throw new Error("Missing title identity");
  if (item.mediaType === "movie") return { movie: { ids: { tmdb: item.tmdbId } } };
  if (!Number.isInteger(item.season) || item.season! < 0 || !Number.isInteger(item.episode) || item.episode! < 1) throw new Error("Missing episode identity");
  return { show: { ids: { tmdb: item.tmdbId } }, episode: { season: item.season, number: item.episode } };
}
export function assertWeTrakrWrite(result: Row) {
  const items = (value: any): any[] => Array.isArray(value) ? value : value && typeof value === "object" ? Object.values(value).flatMap(items) : value == null ? [] : [value];
  if (items(result.notFound).length || items(result.errored).some(item => (item.message || item.error) !== "Already watched!")) throw new Error("WeTrakr could not save this title. Please retry.");
}

/** The browser holds only an opaque connection marker; grants remain in an encrypted server session. */
export class WeTrakrClient implements SyncClient {
  private profile: string | null = null;
  private marker: Marker | null = null;
  private cache = new Map<string, { at: number; rows: Row[] }>();
  private blockedUntil = 0;
  get currentProfileId() { return this.profile; }
  get isConnected() { return Boolean(this.marker); }
  setProfile(profile: string | null) {
    if (profile !== this.profile) { this.profile = profile; this.marker = null; this.cache.clear(); }
  }
  private async call(path: string, method = "GET", body?: unknown, profile = this.profile) {
    if (!profile) throw new Error("Choose an ARVIO profile first.");
    if (Date.now() < this.blockedUntil) throw new Error("WeTrakr is limiting requests. Please try again later.");
    const connection = this.marker?.connection_id;
    const response = await fetch(`/api/wetrakr/${path}`, { method, credentials: "same-origin", cache: "no-store",
      headers: { "Content-Type": "application/json", "x-wetrakr-profile": profile, "x-wetrakr-connection": this.marker?.connection_id || "" },
      body: body === undefined ? undefined : JSON.stringify(body), signal: AbortSignal.timeout(30000) });
    const data = await response.json().catch(() => ({}));
    if (!response.ok) {
      if (response.status === 429) this.blockedUntil = Date.now() + Math.max(5, Number(response.headers.get("retry-after")) || 60) * 1000;
      if (profile === this.profile && data.error === "session_required") this.marker = null;
      const messages: Record<string, string> = { expired_token: "The WeTrakr code expired. Request a new code.", access_denied: "WeTrakr connection was declined.", session_required: "Please reconnect WeTrakr in this browser." };
      throw new Error(messages[data.error] || (response.status === 429 ? "WeTrakr is limiting requests. Please try again later." : "WeTrakr is temporarily unavailable. Please retry."));
    }
    if (profile !== this.profile || path !== "session" && connection !== this.marker?.connection_id) throw new Error("The active connection changed.");
    return { data, response };
  }
  async restoreSession() { const { data } = await this.call("session"); this.marker = data.token || null; }
  async begin(): Promise<WeTrakrCode> {
    const { data } = await this.call("session", "POST", { action: "start" });
    return { ...data, expiresAt: Date.now() + data.expires_in * 1000 };
  }
  async poll(code: WeTrakrCode) {
    if (Date.now() >= code.expiresAt) throw new Error("The WeTrakr code expired. Request a new code.");
    const { data } = await this.call("session", "POST", { action: "poll", user_code: code.user_code });
    if (data.token) { this.marker = data.token; this.cache.clear(); return true; }
    return false;
  }
  async disconnect() { await this.call("session", "POST", { action: "disconnect" }); this.marker = null; this.cache.clear(); }
  private async list(status: string, target: string) {
    const profile = this.profile;
    const key = `${profile}:${status}:${target}`;
    const saved = this.cache.get(key);
    if (saved && Date.now() - saved.at < 300000) return saved.rows;
    const rows: Row[] = [];
    for (let page = 1; ; page++) {
      if (page > 10000) throw new Error("WeTrakr list pagination did not finish.");
      const { data, response } = await this.call(`sync/tracking/${status}/${target}?limit=100&page=${page}&extended=show_level_1,episode_level_1`, "GET", undefined, profile);
      if (!Array.isArray(data)) throw new Error("WeTrakr returned an invalid list.");
      rows.push(...data);
      const pages = Number(response.headers.get("x-pagination-page-count"));
      if (pages > 0 ? page >= pages : data.length < 100) break;
    }
    this.cache.set(key, { at: Date.now(), rows });
    return rows;
  }
  watchlist() { return this.library("planning"); }
  async library(status: string) {
    if (!["planning", "watching", "watched", "hold", "dropped"].includes(status)) throw new Error("Unknown WeTrakr library");
    return (await this.list(status, ["watching", "hold"].includes(status) ? "shows" : "all")).filter(row => tmdb(row) && ["movie", "show"].includes(row.type))
      .map(row => ({ type: row.type, [row.type]: media(row), listed_at: row.tracked_at }));
  }
  async watched(type: "movies" | "shows") {
    if (type === "movies") return (await this.list("watched", "movies")).filter(row => tmdb(row)).map(row => ({ movie: media(row), last_watched_at: row.watched_at }));
    const shows = new Map<number, Row>();
    for (const row of await this.list("watched", "episodes")) {
      if (!weTrakrEpisodeKey(row)) continue;
      const id = tmdb(row.show)!;
      const entry = shows.get(id) || { show: media(row.show), seasons: [], last_watched_at: row.watched_at };
      let season = entry.seasons.find((s: Row) => s.number === row.season_number);
      if (!season) { season = { number: row.season_number, episodes: [] }; entry.seasons.push(season); }
      season.episodes.push({ number: row.number, last_watched_at: row.watched_at });
      shows.set(id, entry);
    }
    return [...shows.values()];
  }
  async playback(localWatchedKeys = new Set<string>()) {
    const watchedMovies = new Set((await this.list("watched", "movies")).map(tmdb));
    const watchedEpisodes = new Set([...localWatchedKeys, ...(await this.list("watched", "episodes")).map(weTrakrEpisodeKey)]);
    const result: Row[] = [];
    for (const row of await this.list("playing", "all")) {
      const progress = Number(row.playback?.progress_percent);
      if (!(progress > 0 && progress < 90)) continue;
      if (row.type === "movie" && tmdb(row) && !watchedMovies.has(tmdb(row))) result.push({ movie: media(row), progress, paused_at: row.playback.tracked_at });
      else if (row.type === "episode" && weTrakrEpisodeKey(row) && !watchedEpisodes.has(weTrakrEpisodeKey(row))) result.push({
        show: media(row.show), episode: { ...row, season: row.season_number, number: row.number }, progress, paused_at: row.playback.tracked_at });
    }
    for (const row of await this.list("watching", "shows")) {
      const ep = row.next_watching_episode;
      if (!tmdb(row) || !ep || result.some(item => item.show?.ids.tmdb === tmdb(row))) continue;
      const key = weTrakrEpisodeKey({ ...ep, show: row });
      const date = ep.air_date || ep.release_date;
      if (!key || watchedEpisodes.has(key) || !date || String(date).slice(0, 10) > new Date().toISOString().slice(0, 10)) continue;
      result.push({ show: media(row), episode: { ...ep, season: ep.season_number, number: ep.number }, progress: 0, paused_at: row.tracked_at, is_up_next: true });
    }
    return result;
  }
  private async change(item: SyncMediaRef, status: string, remove = false) {
    const path = !remove ? "sync/tracking" : status === "watched" ? "sync/tracking/remove/all" : "sync/tracking/remove";
    const { data } = await this.call(path, "POST", weTrakrBody(item, status));
    this.cache.clear(); assertWeTrakrWrite(data);
  }
  addToWatchlist(item: SyncMediaRef) { return this.change(item, "planning"); }
  removeFromWatchlist(item: SyncMediaRef) { return this.change(item, "planning", true); }
  addToHistory(item: SyncMediaRef) { return this.change(item, "watched"); }
  removeFromHistory(item: SyncMediaRef) { return this.change(item, "watched", true); }
  async dismissFromContinueWatching(item: SyncMediaRef) { await this.call("scrobble/playing", "DELETE", weTrakrPlaybackRef(item)); this.cache.clear(); }
  async scrobble(action: "start" | "pause" | "stop", item: SyncMediaRef & { progress: number }) {
    if (!Number.isFinite(item.progress)) return;
    await this.call(`scrobble/${action}?min_progress=90`, "POST", { ...weTrakrPlaybackRef(item), progress: Math.max(0, Math.min(100, item.progress)), app_version: "2.0" });
    this.cache.clear();
  }
}
export const weTrakrClient = new WeTrakrClient();

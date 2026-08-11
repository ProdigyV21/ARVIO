import { SyncClient, SyncMediaRef } from "./sync";
import { loadStored, removeStored, saveStored } from "./storage";
import { jsonRequest } from "./http";

const SIMKL_TOKEN_KEY = "arvio.web.simkl.token";

export interface SimklToken {
  access_token: string;
}

export interface SimklPinCode {
  user_code: string;
  verification_url: string;
  expires_in: number;
  interval: number;
}

export class SimklClient implements SyncClient {
  token: SimklToken | null = loadStored<SimklToken | null>(SIMKL_TOKEN_KEY, null);

  get isConnected(): boolean {
    return Boolean(this.token?.access_token);
  }

  setToken(token: SimklToken | null) {
    this.token = token;
    if (this.token) saveStored(SIMKL_TOKEN_KEY, this.token);
    else removeStored(SIMKL_TOKEN_KEY);
  }

  private async simkl<T>(path: string, options: RequestInit = {}): Promise<T> {
    const headers: Record<string, string> = {
      "content-type": "application/json",
      ...(options.headers as Record<string, string>)
    };
    if (this.token?.access_token) {
      headers["x-user-token"] = this.token.access_token;
    }
    return jsonRequest<T>(`/api/simkl${path}`, { ...options, headers });
  }

  async beginPinAuth(): Promise<SimklPinCode> {
    return this.simkl<SimklPinCode>("/oauth/pin");
  }

  async pollPinToken(userCode: string): Promise<boolean> {
    type PollRes = { result: string; access_token?: string };
    const res = await this.simkl<PollRes>(`/oauth/pin/${userCode}`);
    if (res.result === "OK" && res.access_token) {
      this.setToken({ access_token: res.access_token });
      return true;
    }
    return false;
  }

  async watchlist(): Promise<unknown[]> {
    if (!this.isConnected) return [];
    return this.simkl<unknown[]>("/sync/all-items/movies");
  }

  async playback(): Promise<unknown[]> {
    return [];
  }

  async watched(type: "movies" | "shows"): Promise<unknown[]> {
    if (!this.isConnected) return [];
    return this.simkl<unknown[]>(`/sync/all-items/${type}`);
  }

  async addToWatchlist(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const body = item.mediaType === "movie"
      ? { movies: [{ ids: { tmdb: item.tmdbId } }] }
      : { shows: [{ ids: { tmdb: item.tmdbId } }] };
    await this.simkl("/sync/watchlist", { method: "POST", body: JSON.stringify(body) });
  }

  async removeFromWatchlist(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const body = item.mediaType === "movie"
      ? { movies: [{ ids: { tmdb: item.tmdbId } }] }
      : { shows: [{ ids: { tmdb: item.tmdbId } }] };
    await this.simkl("/sync/watchlist/remove", { method: "POST", body: JSON.stringify(body) });
  }

  async addToHistory(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const body = item.mediaType === "movie"
      ? { movies: [{ ids: { tmdb: item.tmdbId } }] }
      : {
          shows: [{
            ids: { tmdb: item.tmdbId },
            seasons: item.season && item.episode ? [{ number: item.season, episodes: [{ number: item.episode }] }] : undefined
          }]
        };
    await this.simkl("/sync/history?allow_rewatch=yes", { method: "POST", body: JSON.stringify(body) });
  }

  async removeFromHistory(item: SyncMediaRef): Promise<void> {
    if (!this.isConnected) return;
    const body = item.mediaType === "movie"
      ? { movies: [{ ids: { tmdb: item.tmdbId } }] }
      : { shows: [{ ids: { tmdb: item.tmdbId } }] };
    await this.simkl("/sync/history/remove", { method: "POST", body: JSON.stringify(body) });
  }

  async dismissFromContinueWatching(): Promise<void> {
    // No-op for Simkl
  }

  async scrobble(action: "start" | "pause" | "stop", item: SyncMediaRef & { progress: number }): Promise<void> {
    if (!this.isConnected) return;
    const normProgress = item.progress <= 1.0 ? item.progress * 100 : item.progress;
    const body = item.mediaType === "movie"
      ? { movie: { ids: { tmdb: item.tmdbId } }, progress: normProgress }
      : {
          show: { ids: { tmdb: item.tmdbId } },
          episode: item.episode ? { number: item.episode } : undefined,
          progress: normProgress
        };
    await this.simkl(`/scrobble/${action}`, { method: "POST", body: JSON.stringify(body) });
  }
}

export const simklClient = new SimklClient();

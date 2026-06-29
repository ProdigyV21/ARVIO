import { config, hasTraktConfig } from "./config";
import { jsonRequest } from "./http";
import { loadStored, removeStored, saveStored } from "./storage";

const TRAKT_TOKEN_KEY = "arvio.web.trakt.token";

export interface TraktDeviceCode {
  device_code: string;
  user_code: string;
  verification_url: string;
  expires_in: number;
  interval: number;
}

export interface TraktToken {
  access_token: string;
  refresh_token: string;
  expires_at: number;
}

export class TraktClient {
  token = loadStored<TraktToken | null>(TRAKT_TOKEN_KEY, null);

  get isConnected() {
    return Boolean(this.token?.access_token);
  }

  async beginDeviceLink() {
    if (!hasTraktConfig()) throw new Error("Trakt is not configured");
    return this.trakt<TraktDeviceCode>("/oauth/device/code", {
      method: "POST",
      body: JSON.stringify({ client_id: config.traktClientId })
    });
  }

  async pollDeviceToken(code: string) {
    const response = await this.trakt<{ access_token: string; refresh_token: string; expires_in: number }>(
      "/oauth/device/token",
      {
        method: "POST",
        body: JSON.stringify({ code, client_id: config.traktClientId })
      }
    );
    this.token = {
      access_token: response.access_token,
      refresh_token: response.refresh_token,
      expires_at: Date.now() + response.expires_in * 1000
    };
    saveStored(TRAKT_TOKEN_KEY, this.token);
  }

  async watchlist() {
    if (!this.token) return [];
    return this.trakt<unknown[]>("/sync/watchlist", {
      headers: { "x-user-token": this.token.access_token }
    });
  }

  async playback() {
    if (!this.token) return [];
    return this.trakt<unknown[]>("/sync/playback", {
      headers: { "x-user-token": this.token.access_token }
    });
  }

  async watched(type: "movies" | "shows") {
    if (!this.token) return [];
    return this.trakt<unknown[]>(`/sync/watched/${type}`, {
      headers: { "x-user-token": this.token.access_token }
    });
  }

  async history(type?: "movies" | "shows" | "episodes") {
    if (!this.token) return [];
    const path = type ? `/sync/history/${type}` : "/sync/history";
    return this.trakt<unknown[]>(path, {
      headers: { "x-user-token": this.token.access_token }
    });
  }

  async addToWatchlist(item: { mediaType: "movie" | "tv"; tmdbId: number }) {
    if (!this.token) return;
    await this.trakt("/sync/watchlist", {
      method: "POST",
      headers: { "x-user-token": this.token.access_token },
      body: JSON.stringify(this.mediaBody(item))
    });
  }

  async removeFromWatchlist(item: { mediaType: "movie" | "tv"; tmdbId: number }) {
    if (!this.token) return;
    await this.trakt("/sync/watchlist/remove", {
      method: "POST",
      headers: { "x-user-token": this.token.access_token },
      body: JSON.stringify(this.mediaBody(item))
    });
  }

  async scrobble(action: "start" | "pause" | "stop", item: { mediaType: "movie" | "tv"; tmdbId: number; progress: number }) {
    if (!this.token) return;
    await this.trakt(`/scrobble/${action}`, {
      method: "POST",
      headers: { "x-user-token": this.token.access_token },
      body: JSON.stringify({ ...this.mediaBody(item), progress: Math.round(item.progress) })
    });
  }

  disconnect() {
    this.token = null;
    removeStored(TRAKT_TOKEN_KEY);
  }

  private async trakt<T>(path: string, init: RequestInit = {}) {
    const url = new URL(`/api/trakt/${path.replace(/^\/+/, "")}`, window.location.origin);
    return jsonRequest<T>(url.toString(), {
      ...init,
      headers: {
        "trakt-api-version": "2",
        "trakt-api-key": config.traktClientId,
        ...(init.headers ?? {})
      }
    });
  }

  private mediaBody(item: { mediaType: "movie" | "tv"; tmdbId: number }) {
    if (item.mediaType === "tv") {
      return { shows: [{ ids: { tmdb: item.tmdbId } }] };
    }
    return { movies: [{ ids: { tmdb: item.tmdbId } }] };
  }
}

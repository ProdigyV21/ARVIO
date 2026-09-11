import { config, hasTraktConfig } from "./config";
import { HttpError, jsonRequest } from "./http";
import { loadStored, removeStored, saveStored } from "./storage";

const LEGACY_TRAKT_TOKEN_KEY = "arvio.web.trakt.token";
// Keep the payload slim, but invalidate v2 entries: they discarded reset_at,
// which is required to distinguish genuine rewatches from stale Up Next.
const TRAKT_PROGRESS_CACHE_KEY = "arvio.web.trakt.progressCache.v3";
const TRAKT_PROGRESS_TTL_MS = 15 * 60 * 1000;

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

function normalizeToken(token: TraktToken | null): TraktToken | null {
  if (!token?.access_token) return null;
  const expiresAt = Number(token.expires_at ?? 0);
  return {
    ...token,
    expires_at: expiresAt > 0 && expiresAt < 1_000_000_000_000 ? expiresAt * 1000 : expiresAt
  };
}

export class TraktClient {
  token: TraktToken | null = null;
  private profileId: string | null = null;
  private refreshInFlight: { profileId: string | null; token: string; promise: Promise<TraktToken | null> } | null = null;

  get currentProfileId(): string | null {
    return this.profileId;
  }

  get isConnected() {
    return Boolean(this.token?.access_token);
  }

  private tokenKey(profileId: string) {
    return `arvio.web.trakt.token:${profileId}`;
  }

  setProfile(profileId: string | null) {
    const normalized = profileId?.trim() || null;
    if (normalized === this.profileId) return;
    this.profileId = normalized;
    if (!normalized) {
      this.token = null;
      return;
    }

    let stored = normalizeToken(loadStored<TraktToken | null>(this.tokenKey(normalized), null));
    if (!stored) {
      const legacy = normalizeToken(loadStored<TraktToken | null>(LEGACY_TRAKT_TOKEN_KEY, null));
      if (legacy) {
        stored = legacy;
        saveStored(this.tokenKey(normalized), legacy);
        removeStored(LEGACY_TRAKT_TOKEN_KEY);
      }
    }
    this.token = stored;
  }

  setToken(token: TraktToken | null) {
    this.token = normalizeToken(token);
    if (!this.profileId) return;
    if (this.token) saveStored(this.tokenKey(this.profileId), this.token);
    else removeStored(this.tokenKey(this.profileId));
  }

  async beginDeviceLink() {
    if (!hasTraktConfig()) throw new Error("Trakt is not configured");
    return this.trakt<TraktDeviceCode>("/oauth/device/code", {
      method: "POST",
      body: JSON.stringify({ client_id: config.traktClientId })
    });
  }

  async pollDeviceToken(code: string): Promise<TraktToken> {
    const response = await this.exchangeDeviceToken(code);
    return {
      access_token: response.access_token,
      refresh_token: response.refresh_token,
      expires_at: Date.now() + response.expires_in * 1000
    };
  }

  private async exchangeDeviceToken(code: string) {
    type TokenResponse = { access_token: string; refresh_token: string; expires_in: number };
    // Trakt Cloudflare-blocks our Netlify backend's egress IP, so the token
    // exchange goes direct from the browser (which Trakt does not block). This
    // needs the client secret; it is already shipped in the public Android app,
    // so exposing it here is not a new disclosure.
    if (config.traktClientSecret) {
      try {
        return await jsonRequest<TokenResponse>("https://api.trakt.tv/oauth/device/token", {
          method: "POST",
          headers: { "content-type": "application/json", "trakt-api-version": "2" },
          body: JSON.stringify({ code, client_id: config.traktClientId, client_secret: config.traktClientSecret })
        });
      } catch {
        // fall through to the backend proxy
      }
    }
    return this.trakt<TokenResponse>("/oauth/device/token", {
      method: "POST",
      body: JSON.stringify({ code, client_id: config.traktClientId })
    });
  }

  async watchlist() {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    return this.trakt<unknown[]>("/sync/watchlist", {
      headers: { "x-user-token": token.access_token }
    });
  }

  // The user's personal collection (owned/available items).
  async collection(type: "movies" | "shows") {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    return this.trakt<unknown[]>(`/sync/collection/${type}`, {
      headers: { "x-user-token": token.access_token }
    });
  }

  // Custom Trakt lists owned by the user: [{ ids: { trakt, slug }, name, ... }].
  async userLists() {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    return this.trakt<unknown[]>("/users/me/lists", {
      headers: { "x-user-token": token.access_token }
    });
  }

  // Items in a specific custom list (movies + shows).
  async listItems(listId: string | number) {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    const rows: unknown[] = [];
    const safeListId = encodeURIComponent(String(listId));
    const limit = 100;
    for (let page = 1; page <= 5; page += 1) {
      const batch = await this.trakt<unknown[]>(
        `/users/me/lists/${safeListId}/items/movie,show?extended=full&page=${page}&limit=${limit}`,
        { headers: { "x-user-token": token.access_token } }
      );
      rows.push(...batch);
      if (batch.length < limit) break;
    }
    return rows;
  }

  /** Background checks never invoke the paid proxy or refresh an expired token. */
  async continueWatchingActivity(): Promise<unknown | null> {
    const token = this.token;
    const profileId = this.profileId;
    if (!config.traktClientId || !token || token.expires_at <= Date.now()) return null;
    const activities = await this.directTrakt<unknown>("/sync/last_activities", {
      headers: { "x-user-token": token.access_token }
    }, 3_000);
    return this.profileId === profileId && this.token?.access_token === token.access_token ? activities : null;
  }

  async playback() {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    return this.trakt<unknown[]>("/sync/playback", {
      headers: { "x-user-token": token.access_token }
    });
  }

  async watched(type: "movies" | "shows") {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    const all: unknown[] = [];
    let page = 1;
    // Trakt's July 2026 API update (trakt-api discussion #775): extended=
    // progress responses are server-capped at 100 items/page (others at 250).
    // Requesting above the cap made `rows.length < limit` true on a FULL page,
    // silently stopping pagination after page 1 for large libraries.
    const limit = type === "shows" ? 100 : 250;
    const maxPages = type === "shows" ? 5 : 8;
    while (true) {
      const query = new URLSearchParams({
        page: String(page),
        limit: String(limit)
      });
      // Season/episode data is opt-in now and required for watched badges;
      // it is also the heavy variant, hence the page ceiling.
      if (type === "shows") query.set("extended", "progress");
      const rows = await this.trakt<unknown[]>(`/sync/watched/${type}?${query.toString()}`, {
        headers: { "x-user-token": token.access_token }
      });
      if (!rows.length) break;
      all.push(...rows);
      if (rows.length < limit || page >= maxPages) break;
      page += 1;
    }
    return all;
  }

  // Shows the user hid from their Trakt progress ("dropped"), across BOTH
  // hidden sections Trakt keeps: progress_watched and progress_watched_reset.
  // Trakt itself omits these from Up Next, so Continue Watching must too —
  // otherwise a dropped show reappears here forever. Returns trakt show ids.
  async hiddenProgressShowIds(): Promise<Set<number>> {
    const token = await this.refreshIfNeeded();
    if (!token) return new Set();
    const sections = ["progress_watched", "progress_watched_reset"];
    const ids = new Set<number>();
    const pages = await Promise.all(sections.map((section) =>
      this.trakt<unknown[]>(`/users/hidden/${section}?type=show&limit=100`, {
        headers: { "x-user-token": token.access_token }
      }).catch(() => [] as unknown[])
    ));
    pages.flat().forEach((raw) => {
      const traktId = (raw as { show?: { ids?: { trakt?: number } } }).show?.ids?.trakt;
      if (typeof traktId === "number") ids.add(traktId);
    });
    return ids;
  }

  // Activity changes invalidate immediately; the TTL also catches new episodes
  // airing or corrected episode metadata without any new watch activity.
  async showProgress(traktShowId: number, includeSpecials = false, activityKey?: string | number) {
    const token = await this.refreshIfNeeded();
    if (!token) return null;
    const cached = readProgressCache(token.access_token, traktShowId, includeSpecials, activityKey);
    if (cached !== undefined) return cached;
    const query = new URLSearchParams({
      hidden: "false",
      specials: String(includeSpecials),
      count_specials: String(includeSpecials)
    });
    const progress = await this.trakt<unknown>(`/shows/${traktShowId}/progress/watched?${query.toString()}`, {
      headers: { "x-user-token": token.access_token }
    });
    writeProgressCache(token.access_token, traktShowId, includeSpecials, progress, activityKey);
    return progress;
  }

  async history(type?: "movies" | "shows" | "episodes") {
    const token = await this.refreshIfNeeded();
    if (!token) return [];
    const path = type ? `/sync/history/${type}` : "/sync/history";
    return this.trakt<unknown[]>(path, {
      headers: { "x-user-token": token.access_token }
    });
  }

  async addToWatchlist(item: TraktMediaRef) {
    const token = await this.refreshIfNeeded();
    if (!token) return;
    await this.trakt("/sync/watchlist", {
      method: "POST",
      headers: { "x-user-token": token.access_token },
      body: JSON.stringify(this.mediaBody(item))
    });
  }

  // Mark watched on Trakt (registers in the user's history, same as the app's
  // "Mark watched"). A full-show ref (no season/episode) marks the whole show.
  async addToHistory(item: TraktMediaRef) {
    const token = await this.refreshIfNeeded();
    if (!token) return;
    await this.trakt("/sync/history", {
      method: "POST",
      headers: { "x-user-token": token.access_token },
      body: JSON.stringify(this.mediaBody(item))
    });
  }

  async removeFromHistory(item: TraktMediaRef) {
    const token = await this.refreshIfNeeded();
    if (!token) return;
    await this.trakt("/sync/history/remove", {
      method: "POST",
      headers: { "x-user-token": token.access_token },
      body: JSON.stringify(this.mediaBody(item))
    });
  }

  async dismissFromContinueWatching(item: TraktMediaRef) {
    const token = await this.refreshIfNeeded();
    if (!token) return;

    const playbackRows = await this.trakt<unknown[]>("/sync/playback", {
      headers: { "x-user-token": token.access_token }
    });
    const matchingPlaybackIds = playbackRows.flatMap((raw) => {
      const row = raw as {
        id?: number;
        movie?: { ids?: { tmdb?: number } };
        show?: { ids?: { tmdb?: number } };
        episode?: { season?: number; number?: number };
      };
      const sameTitle = item.mediaType === "movie"
        ? row.movie?.ids?.tmdb === item.tmdbId
        : row.show?.ids?.tmdb === item.tmdbId;
      const sameEpisode = item.mediaType !== "tv" ||
        ((item.season == null || row.episode?.season === item.season) &&
          (item.episode == null || row.episode?.number === item.episode));
      return sameTitle && sameEpisode && typeof row.id === "number" ? [row.id] : [];
    });

    await Promise.all(matchingPlaybackIds.map((id) => this.trakt(`/sync/playback/${id}`, {
      method: "DELETE",
      headers: { "x-user-token": token.access_token }
    })));

    if (item.mediaType === "tv") {
      await this.trakt("/users/hidden/progress_watched", {
        method: "POST",
        headers: { "x-user-token": token.access_token },
        body: JSON.stringify({ shows: [{ ids: { tmdb: item.tmdbId } }] })
      });
    }
  }

  async removeFromWatchlist(item: TraktMediaRef) {
    const token = await this.refreshIfNeeded();
    if (!token) return;
    await this.trakt("/sync/watchlist/remove", {
      method: "POST",
      headers: { "x-user-token": token.access_token },
      body: JSON.stringify(this.mediaBody(item))
    });
  }

  async scrobble(action: "start" | "pause" | "stop", item: TraktMediaRef & { progress: number }) {
    if (!Number.isSafeInteger(item.tmdbId) || item.tmdbId <= 0) return;
    if (item.mediaType === "tv" && (!Number.isInteger(item.season) || item.season! < 0 ||
      !Number.isInteger(item.episode) || item.episode! <= 0)) return;
    const profileId = this.profileId;
    const token = await this.refreshIfNeeded();
    if (!token || this.profileId !== profileId) return;
    const media = item.mediaType === "movie"
      ? { movie: { ids: { tmdb: item.tmdbId } } }
      : { show: { ids: { tmdb: item.tmdbId } }, episode: { season: item.season, number: item.episode } };
    try {
      await this.trakt(`/scrobble/${action}`, {
        method: "POST",
        keepalive: true,
        headers: { "x-user-token": token.access_token },
        body: JSON.stringify({ ...media, progress: Number.isFinite(item.progress) ? Math.min(100, Math.max(0, item.progress)) : 0 })
      });
    } catch (error) {
      if (action !== "stop" || (error as { status?: number }).status !== 409) throw error;
    }
  }

  disconnect() {
    this.setToken(null);
  }

  private async trakt<T>(path: string, init: RequestInit = {}) {
    const requestProfileId = this.profileId;
    const originalHeaders = new Headers(init.headers ?? {});
    const originalAccessToken = originalHeaders.get("x-user-token")
      ?? originalHeaders.get("Authorization")?.replace(/^Bearer\s+/i, "")
      ?? null;
    const url = new URL(`/api/trakt/${path.replace(/^\/+/, "")}`, window.location.origin);
    const request = {
      ...init,
      cache: "no-store",
      // Hard timeout: a single hanging Trakt response (throttling that never
      // answers) used to block one of the up-next workers forever — and
      // Promise.all over the workers then hung the WHOLE enriched Continue
      // Watching pipeline, so the fresh rail never landed and the instant-paint
      // cache never got written.
      signal: init.signal ?? (typeof AbortSignal.timeout === "function" ? AbortSignal.timeout(15_000) : undefined),
      headers: {
        "trakt-api-version": "2",
        "trakt-api-key": config.traktClientId,
        ...(init.headers ?? {})
      }
    } satisfies RequestInit;
    // Direct-first: api.trakt.tv serves CORS to browsers, so most calls never
    // need the Netlify proxy — each proxied call was a paid function
    // invocation (~8 per boot per user, up to ~120 on cold progress caches),
    // and Trakt intermittently blocks Netlify egress anyway. The proxy remains
    // the fallback for networks where Trakt challenges browser preflights, and
    // the primary path for oauth flows that need the backend secret.
    const send = async (): Promise<T> => {
      if (this.canUseDirectFallback(path, init)) {
        try {
          return await this.directTrakt<T>(path, request);
        } catch (error) {
          // A 401 is an expired/revoked token, not a transport problem — the
          // proxy would fail identically, so let it bubble to the retry below.
          if ((error as { status?: number }).status === 401) throw error;
          if (path.startsWith("/scrobble/") && [400, 404, 409, 422, 429].includes((error as { status?: number }).status ?? 0)) throw error;
          return jsonRequest<T>(url.toString(), request);
        }
      }
      try {
        return await jsonRequest<T>(url.toString(), request);
      } catch (error) {
        if ((error as { status?: number }).status === 401) throw error;
        if (!this.canUseDirectFallback(path, init)) throw error;
        return this.directTrakt<T>(path, request);
      }
    };

    try {
      return await send();
    } catch (error) {
      // Expired-token recovery. refreshIfNeeded() only fires when the STORED
      // expiry says the token is stale; a token Trakt rejected early (revoked,
      // clock skew, or an expiry we never recorded) left every call 401-ing
      // forever, which silently froze Continue Watching until the user
      // re-linked Trakt by hand. Refresh once on a real 401 and retry — the
      // refresh itself goes direct to Trakt, so it works even though Trakt
      // blocks our server egress.
      const status = (error as { status?: number }).status;
      if (status !== 401 || path.replace(/^\/+/, "").startsWith("oauth/")) throw error;
      if (this.profileId !== requestProfileId) throw error;
      const currentAccessToken = this.token?.access_token ?? null;
      const refreshed = originalAccessToken && currentAccessToken && originalAccessToken !== currentAccessToken
        ? currentAccessToken
        : await this.forceRefresh();
      if (!refreshed) throw error;
      const retryHeaders = new Headers(request.headers as HeadersInit);
      if (retryHeaders.has("x-user-token")) retryHeaders.set("x-user-token", refreshed);
      if (retryHeaders.has("Authorization")) retryHeaders.set("Authorization", `Bearer ${refreshed}`);
      const retry = { ...request, headers: Object.fromEntries(retryHeaders.entries()) };
      if (this.canUseDirectFallback(path, retry)) return this.directTrakt<T>(path, retry);
      return jsonRequest<T>(url.toString(), retry);
    }
  }

  /** Refresh regardless of the stored expiry. Returns the new access token. */
  private async forceRefresh(): Promise<string | null> {
    const profileId = this.profileId;
    const token = this.token;
    if (!token?.refresh_token) return null;
    const previous = token.access_token;
    try {
      // Reuse the normal refresh by pretending the token is already stale.
      this.token = { ...token, expires_at: 0 };
      const refreshed = await this.refreshIfNeeded();
      if (this.profileId !== profileId) return null;
      const next = refreshed?.access_token ?? null;
      return next && next !== previous ? next : null;
    } catch {
      return null;
    }
  }

  private canUseDirectFallback(path: string, init: RequestInit) {
    if (!config.traktClientId) return false;
    const normalized = path.replace(/^\/+/, "");
    // The device-code request needs only the public client_id, so it can go
    // straight to Trakt when the Netlify proxy is Cloudflare-blocked. Token
    // exchange/refresh need the client secret (backend-only) — no direct fallback.
    if (normalized === "oauth/device/code") return true;
    if (normalized.startsWith("oauth/")) return false;
    const headers = new Headers(init.headers ?? {});
    return Boolean(headers.get("x-user-token") || headers.get("Authorization"));
  }

  private async directTrakt<T>(path: string, init: RequestInit, timeoutMs = 15_000) {
    const target = new URL(`https://api.trakt.tv/${path.replace(/^\/+/, "")}`);
    const headers = new Headers(init.headers ?? {});
    const userToken = headers.get("x-user-token");
    headers.delete("x-user-token");
    headers.set("trakt-api-version", "2");
    headers.set("trakt-api-key", config.traktClientId);
    headers.set("Accept", "application/json");
    if (userToken && !headers.has("Authorization")) headers.set("Authorization", `Bearer ${userToken}`);
    if (init.body && !headers.has("Content-Type")) headers.set("Content-Type", "application/json");
    const response = await fetch(target.toString(), {
      ...init,
      headers,
      cache: "no-store",
      // Fresh timeout: the signal inherited from the proxy attempt may already
      // be (nearly) expired by the time this fallback runs.
      signal: typeof AbortSignal.timeout === "function" ? AbortSignal.timeout(timeoutMs) : undefined
    });
    if (!response.ok) {
      const raw = await response.text().catch(() => "");
      // Carry the status: callers distinguish an expired token (401 → refresh
      // and retry) from a network-level block (403 → try the proxy instead).
      throw new HttpError(response.status, raw.includes("Cloudflare") || raw.includes("<html")
        ? "Trakt is blocking this network request right now."
        : raw || `Trakt request failed with ${response.status}`);
    }
    if (response.status === 204) return undefined as T;
    return (await response.json()) as T;
  }

  private async refreshIfNeeded(): Promise<TraktToken | null> {
    const profileId = this.profileId;
    const token = this.token?.refresh_token ?? "";
    if (this.refreshInFlight?.profileId === profileId && this.refreshInFlight.token === token) return this.refreshInFlight.promise;
    const promise = this.refreshToken();
    this.refreshInFlight = { profileId, token, promise };
    try { return await promise; }
    finally { if (this.refreshInFlight?.promise === promise) this.refreshInFlight = null; }
  }

  private async refreshToken(): Promise<TraktToken | null> {
    const profileId = this.profileId;
    const token = this.token;
    if (!token?.refresh_token) return null;
    if (token.expires_at && token.expires_at - Date.now() > 300000) return token;
    const body = {
      grant_type: "refresh_token",
      refresh_token: token.refresh_token,
      client_id: config.traktClientId,
      client_secret: config.traktClientSecret,
      redirect_uri: "urn:ietf:wg:oauth:2.0:oob"
    };
    type RefreshResponse = { access_token: string; refresh_token?: string; expires_in: number };
    let response: RefreshResponse;
    // Direct to Trakt (Netlify egress is Cloudflare-blocked); backend proxy is
    // the fallback for environments without the client secret configured.
    if (config.traktClientSecret) {
      response = await jsonRequest<RefreshResponse>("https://api.trakt.tv/oauth/token", {
        method: "POST",
        headers: { "content-type": "application/json", "trakt-api-version": "2" },
        body: JSON.stringify(body)
      }).catch(() => this.trakt<RefreshResponse>("/oauth/token", {
        method: "POST",
        body: JSON.stringify({ grant_type: "refresh_token", refresh_token: token.refresh_token })
      }));
    } else {
      response = await this.trakt<RefreshResponse>("/oauth/token", {
        method: "POST",
        body: JSON.stringify({ grant_type: "refresh_token", refresh_token: token.refresh_token })
      });
    }
    if (this.profileId !== profileId || this.token?.refresh_token !== token.refresh_token) return null;
    this.setToken({
      access_token: response.access_token,
      refresh_token: response.refresh_token ?? token.refresh_token,
      expires_at: Date.now() + response.expires_in * 1000
    });
    return this.token;
  }

  private mediaBody(item: TraktMediaRef) {
    if (item.mediaType === "tv") {
      const show = { ids: { tmdb: item.tmdbId } };
      if (item.season && item.episode) {
        return { shows: [{ ...show, seasons: [{ number: item.season, episodes: [{ number: item.episode }] }] }] };
      }
      return { shows: [show] };
    }
    return { movies: [{ ids: { tmdb: item.tmdbId } }] };
  }
}

interface TraktMediaRef {
  mediaType: "movie" | "tv";
  tmdbId: number;
  season?: number | null;
  episode?: number | null;
}

type ProgressCacheEntry = { at: number; value: unknown };

function progressCacheKey(token: string, traktShowId: number, includeSpecials: boolean, activityKey?: string | number) {
  const activity = activityKey !== undefined && activityKey !== null && activityKey !== "" ? `:${activityKey}` : "";
  return `${token.slice(0, 12)}:${traktShowId}:${includeSpecials ? "specials" : "regular"}${activity}`;
}

function readProgressCache(token: string, traktShowId: number, includeSpecials: boolean, activityKey?: string | number) {
  const cache = loadStored<Record<string, ProgressCacheEntry>>(TRAKT_PROGRESS_CACHE_KEY, {});
  const entry = cache[progressCacheKey(token, traktShowId, includeSpecials, activityKey)];
  if (!entry || Date.now() - entry.at >= TRAKT_PROGRESS_TTL_MS) return undefined;
  return entry.value;
}

// Persist only the fields Continue Watching actually reads
// (traktUpNextToMedia): a full progress payload lists every season+episode —
// hundreds of KB across a library, which once filled the ~5MB localStorage
// quota and silently broke every cache write in the app.
function slimProgress(value: unknown): unknown {
  if (!value || typeof value !== "object") return value;
  const progress = value as {
    aired?: number;
    completed?: number;
    reset_at?: string | null;
    last_watched_at?: string;
    next_episode?: { season?: number; number?: number; title?: string } | null;
  };
  return {
    aired: progress.aired,
    completed: progress.completed,
    reset_at: progress.reset_at,
    last_watched_at: progress.last_watched_at,
    next_episode: progress.next_episode
      ? { season: progress.next_episode.season, number: progress.next_episode.number, title: progress.next_episode.title }
      : null
  };
}

function writeProgressCache(token: string, traktShowId: number, includeSpecials: boolean, value: unknown, activityKey?: string | number) {
  const cache = loadStored<Record<string, ProgressCacheEntry>>(TRAKT_PROGRESS_CACHE_KEY, {});
  const next = {
    ...cache,
    [progressCacheKey(token, traktShowId, includeSpecials, activityKey)]: { at: Date.now(), value: slimProgress(value) }
  };
  // Cap must exceed the up-next scan width (300 shows) or entries churn out
  // before the next boot can reuse them.
  const entries = Object.entries(next)
    .sort((a, b) => b[1].at - a[1].at)
    .slice(0, 400);
  saveStored(TRAKT_PROGRESS_CACHE_KEY, Object.fromEntries(entries));
}

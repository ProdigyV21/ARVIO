import { createCipheriv, createDecipheriv, createHash, randomBytes, randomUUID } from "node:crypto";
import type { WeTrakrSessionStore } from "./weTrakrSessionStore";

const REFRESH_LIFETIME_MS = 180 * 24 * 60 * 60 * 1000;
const WATCHED_THRESHOLD = 90;
const tokenPattern = /^[A-Za-z0-9._~-]{20,4096}$/;
type Grant = { access_token: string; refresh_token: string; expires_at: number; refresh_expires_at: number; connection_id: string };
type Pending = { device_code: string; user_code: string; expires_at: number; interval: number; next_poll: number };
type Session = { profile: string; expires_at: number; grant?: Grant; pending?: Pending; lease?: { owner: string; until: number }; revokes?: Array<{ token: string; access: string; retry_at: number }> };
export type WeTrakrSessionMarker = { access_token: string; server_session: true; connection_id: string };

export class WeTrakrSessionError extends Error {
  constructor(public reason: string, public status = 400, public retryAfter?: string) { super(reason); }
}

const hash = (value: string) => createHash("sha256").update(value).digest();
export const newWeTrakrSessionId = () => randomBytes(32).toString("hex");
export const validWeTrakrSessionId = (value: string | undefined): value is string => /^[a-f0-9]{64}$/.test(value ?? "");
export function wetrakrCookieName(profile: string, secure: boolean) {
  return `${secure ? "__Host-" : ""}arvio_wetrakr_${hash(profile).toString("hex").slice(0, 16)}`;
}

/** Only this server module handles V2 credentials; the browser gets a non-secret marker. */
export class WeTrakrSessions {
  constructor(private store: WeTrakrSessionStore, private clientId: string,
    private fetcher: typeof fetch = fetch, private now: () => number = Date.now) {}

  private storageKey(id: string) { return hash(`wetrakr-session:${id}`).toString("hex"); }

  private seal(id: string, session: Session) {
    const nonce = randomBytes(12);
    const cipher = createCipheriv("aes-256-gcm", hash(`wetrakr-encryption:${id}`), nonce);
    cipher.setAAD(Buffer.from(this.storageKey(id)));
    const data = Buffer.concat([cipher.update(JSON.stringify(session), "utf8"), cipher.final()]);
    return Buffer.concat([nonce, cipher.getAuthTag(), data]).toString("base64url");
  }

  private async read(id: string, profile: string) {
    if (!validWeTrakrSessionId(id)) throw new WeTrakrSessionError("session_required", 401);
    const stored = await this.store.read(this.storageKey(id));
    if (!stored) return null;
    try {
      const bytes = Buffer.from(stored.data, "base64url");
      const decipher = createDecipheriv("aes-256-gcm", hash(`wetrakr-encryption:${id}`), bytes.subarray(0, 12));
      decipher.setAAD(Buffer.from(this.storageKey(id)));
      decipher.setAuthTag(bytes.subarray(12, 28));
      const session = JSON.parse(Buffer.concat([decipher.update(bytes.subarray(28)), decipher.final()]).toString("utf8")) as Session;
      if (session.profile !== profile) throw new Error("profile mismatch");
      return { session, etag: stored.etag };
    } catch { throw new WeTrakrSessionError("session_required", 401); }
  }

  private async locked<T>(id: string, profile: string, operation: (session: Session) => Promise<T>, create = false): Promise<T> {
    const owner = randomUUID();
    const deadline = Date.now() + 15_000;
    while (Date.now() < deadline) {
      const stored = await this.read(id, profile);
      if (!stored && !create) throw new WeTrakrSessionError("session_required", 401);
      const session = stored?.session ?? { profile, expires_at: this.now() + 900_000 };
      if (session.lease && session.lease.until > this.now()) {
        await new Promise(resolve => setTimeout(resolve, 100));
        continue;
      }
      session.lease = { owner, until: this.now() + 60_000 };
      if (!await this.store.compareAndSet(this.storageKey(id), this.seal(id, session), stored?.etag ?? null)) continue;
      try { return await operation(session); }
      finally {
        const latest = await this.read(id, profile);
        if (latest?.session.lease?.owner !== owner) throw new WeTrakrSessionError("session_changed", 409);
        delete session.lease;
        if (!await this.store.compareAndSet(this.storageKey(id), this.seal(id, session), latest.etag)) throw new WeTrakrSessionError("session_changed", 409);
      }
    }
    throw new WeTrakrSessionError("session_busy", 503, "2");
  }

  private async oauth(path: string, fields: Record<string, string>, timeout = 15_000): Promise<Record<string, unknown>> {
    if (!this.clientId) throw new WeTrakrSessionError("invalid_client", 503);
    const response = await this.fetcher(`https://api.wetrakr.com/oauth/${path}`, {
      method: "POST", headers: { "Content-Type": "application/json", "wetrakr-api-version": "1", "wetrakr-api-key": this.clientId, "User-Agent": "ARVIO/2.0 (Web)" },
      body: JSON.stringify({ ...fields, client_id: this.clientId }), cache: "no-store", signal: AbortSignal.timeout(timeout)
    });
    const data = await response.json().catch(() => ({})) as Record<string, unknown>;
    if (!response.ok || data.error) throw new WeTrakrSessionError(String(data.error || "provider_error"), response.status >= 400 ? response.status : 502, response.headers.get("retry-after") ?? undefined);
    return data;
  }

  private grant(data: Record<string, unknown>, connectionId: string = randomUUID()): Grant {
    data = { ...data, refresh_token: data.refresh_token || data.new_refresh_token };
    if (typeof data.access_token !== "string" || !tokenPattern.test(data.access_token) ||
        typeof data.refresh_token !== "string" || !tokenPattern.test(data.refresh_token) ||
        String(data.token_type).toLowerCase() !== "bearer" || typeof data.expires_in !== "number" || !Number.isFinite(data.expires_in) || data.expires_in <= 0) {
      throw new WeTrakrSessionError("invalid_grant_permissions", 502);
    }
    return { access_token: data.access_token, refresh_token: data.refresh_token, expires_at: this.now() + data.expires_in * 1000,
      refresh_expires_at: this.now() + REFRESH_LIFETIME_MS, connection_id: connectionId };
  }

  private marker(grant: Grant): WeTrakrSessionMarker {
    return { access_token: `wetrakr_session_${grant.connection_id}`, server_session: true, connection_id: grant.connection_id };
  }

  async start(id: string, profile: string) {
    return this.locked(id, profile, async session => {
      if (session.pending && session.pending.expires_at > this.now()) return { user_code: session.pending.user_code, verification_url: "https://wetrakr.com/activate", expires_in: Math.ceil((session.pending.expires_at - this.now()) / 1000), interval: session.pending.interval };
      const data = await this.oauth("device/code", {});
      const expires = Number(data.expires_in);
      const interval = Math.max(5, Number(data.interval) || 5);
      if (typeof data.device_code !== "string" || typeof data.user_code !== "string" || !Number.isFinite(expires) || expires <= 0) throw new WeTrakrSessionError("invalid_response", 502);
      const url = String(data.verification_url);
      if (new URL(url).origin !== "https://wetrakr.com") throw new WeTrakrSessionError("invalid_response", 502);
      session.pending = { device_code: data.device_code, user_code: data.user_code, expires_at: this.now() + expires * 1000,
        interval, next_poll: this.now() + interval * 1000 };
      session.expires_at = Math.max(session.expires_at, session.pending.expires_at);
      return { user_code: data.user_code, verification_url: url, expires_in: expires, interval };
    }, true);
  }

  async poll(id: string, profile: string, userCode: string) {
    return this.locked(id, profile, async session => {
      const pending = session.pending;
      if (!pending || pending.user_code !== userCode || pending.expires_at <= this.now()) throw new WeTrakrSessionError("expired_token");
      if (this.now() < pending.next_poll) return null;
      pending.next_poll = this.now() + pending.interval * 1000;
      try {
        const data = await this.oauth("device/token", { code: pending.device_code });
        const grant = this.grant(data);
        if (session.grant) session.revokes = [...(session.revokes ?? []), { token: session.grant.refresh_token, access: session.grant.access_token, retry_at: 0 }];
        session.grant = grant;
        session.expires_at = grant.refresh_expires_at;
        delete session.pending;
        await this.revokePending(session);
        return this.marker(grant);
      } catch (error) {
        if (error instanceof WeTrakrSessionError && error.reason === "authorization_pending") return null;
        if (error instanceof WeTrakrSessionError && (error.reason === "slow_down" || error.status === 429)) {
          pending.interval = Math.max(pending.interval + 5, Number(error.retryAfter) || 0);
          pending.next_poll = this.now() + pending.interval * 1000;
          return null;
        }
        if (error instanceof WeTrakrSessionError && [400, 401, 404, 409, 410, 418, 423].includes(error.status)) delete session.pending;
        throw error;
      }
    });
  }

  async restore(id: string, profile: string) {
    let saved = await this.read(id, profile);
    if (saved?.session.revokes?.some(item => item.retry_at <= this.now())) {
      await this.locked(id, profile, session => this.revokePending(session));
      saved = await this.read(id, profile);
    }
    const grant = saved?.session.grant;
    return grant && grant.refresh_expires_at > this.now() ? this.marker(grant) : null;
  }

  private async revokePending(session: Session) {
    const remaining = [];
    let attempted = false;
    // Prioritize the just-disconnected grant and keep provider outages within the lease/request budget.
    for (const item of [...(session.revokes ?? [])].reverse()) {
      if (attempted || item.retry_at > this.now()) { remaining.push(item); continue; }
      attempted = true;
      try {
        const response = await this.fetcher("https://api.wetrakr.com/oauth/logout", { method: "POST", headers: {
          Authorization: `Bearer ${item.access}`, "Content-Type": "application/json", "wetrakr-api-version": "1", "wetrakr-api-key": this.clientId
        }, body: JSON.stringify({ refresh_token: item.token }), signal: AbortSignal.timeout(5000), cache: "no-store" });
        if (!response.ok) throw new Error("Revoke failed");
      }
      catch { remaining.push({ ...item, retry_at: this.now() + 60_000 }); }
    }
    session.revokes = remaining.reverse();
  }

  async disconnect(id: string, profile: string, expectedConnection?: string) {
    return this.locked(id, profile, async session => {
      if (expectedConnection && session.grant && session.grant.connection_id !== expectedConnection) throw new WeTrakrSessionError("session_changed", 409);
      if (session.grant) session.revokes = [...(session.revokes ?? []), { token: session.grant.refresh_token, access: session.grant.access_token, retry_at: 0 }];
      delete session.grant;
      delete session.pending;
      session.expires_at = this.now() + 7 * 24 * 60 * 60 * 1000;
      await this.revokePending(session);
      return { disconnected: true, revoked: !session.revokes?.length };
    });
  }

  private async accessToken(id: string, profile: string, expected: string, rejected?: string) {
    const saved = (await this.read(id, profile))?.session.grant;
    if (!saved || saved.refresh_expires_at <= this.now()) throw new WeTrakrSessionError("session_required", 401);
    if (saved.connection_id !== expected) throw new WeTrakrSessionError("session_changed", 409);
    if (saved.expires_at > this.now() + 60_000 && saved.access_token !== rejected) return saved.access_token;
    return this.locked(id, profile, async session => {
      const current = session.grant;
      if (!current || current.refresh_expires_at <= this.now()) throw new WeTrakrSessionError("session_required", 401);
      if (current.connection_id !== expected) throw new WeTrakrSessionError("session_changed", 409);
      if (current.expires_at > this.now() + 60_000 && current.access_token !== rejected) return current.access_token;
      let data: Record<string, unknown>;
      try { data = await this.oauth("token/refresh", { refresh_token: current.refresh_token }); }
      catch (error) {
        if (error instanceof WeTrakrSessionError && ["INVALID_REFRESH_TOKEN"].includes(error.reason)) {
          delete session.grant;
          throw new WeTrakrSessionError("session_required", 401);
        }
        throw error;
      }
      const grant = this.grant(data, current.connection_id);
      session.grant = grant;
      session.expires_at = grant.refresh_expires_at;
      return grant.access_token;
    });
  }

  async request(id: string, profile: string, connection: string, path: string, method: string, input: URLSearchParams, body?: string) {
    const token = await this.accessToken(id, profile, connection);
    const url = new URL(`https://api.wetrakr.com${path}`);
    input.forEach((value, key) => {
      if (!["client_id", "client_secret", "access_token", "refresh_token", "app-name", "app-version"].includes(key)) url.searchParams.set(key, value);
    });

    if (["/scrobble/start", "/scrobble/stop", "/scrobble/pause"].includes(path)) url.searchParams.set("min_progress", String(WATCHED_THRESHOLD));
    const send = (access: string) => this.fetcher(url, { method, headers: { Authorization: `Bearer ${access}`, "wetrakr-api-version": "1", "wetrakr-api-key": this.clientId, "Content-Type": "application/json", "User-Agent": "ARVIO/2.0 (Web)" }, body,
      cache: "no-store", signal: AbortSignal.timeout(20_000) });
    let response = await send(token);
    if (response.status === 401 && response.headers.get("content-type")?.includes("application/json")) {
      await response.body?.cancel();
      response = await send(await this.accessToken(id, profile, connection, token));
    }
    return response;
  }
}

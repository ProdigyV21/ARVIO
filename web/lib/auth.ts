import { config, hasNetlifyBackendConfig, hasNetlifyBackendUrl, hasSupabaseConfig } from "./config";
import { jsonRequest } from "./http";
import { loadStored, removeStored, saveStored } from "./storage";
import type { AuthSession, UserProfile } from "./types";

export const SESSION_KEY = "arvio.web.supabase.session";

interface SupabaseAuthResponse {
  access_token: string;
  refresh_token: string;
  expires_in?: number;
  user?: { id?: string; email?: string };
}

export function decodeJwtPayload(token: string): Record<string, unknown> {
  const part = token.split(".")[1];
  if (!part) return {};
  const padded = part.replace(/-/g, "+").replace(/_/g, "/").padEnd(Math.ceil(part.length / 4) * 4, "=");
  try {
    return JSON.parse(atob(padded)) as Record<string, unknown>;
  } catch {
    return {};
  }
}

function sessionFromResponse(response: SupabaseAuthResponse, fallbackEmail: string, provider?: AuthSession["provider"]): AuthSession {
  const payload = decodeJwtPayload(response.access_token);
  const userId = response.user?.id ?? (payload.sub as string | undefined) ?? "";
  const email = response.user?.email ?? (payload.email as string | undefined) ?? fallbackEmail;
  const tokenProvider = provider ?? ((payload.iss as string | undefined) === "arvio-netlify" ? "netlify" : "supabase");
  return {
    accessToken: response.access_token,
    refreshToken: response.refresh_token,
    userId,
    email,
    expiresAt: Date.now() + (response.expires_in ?? 3600) * 1000,
    provider: tokenProvider
  };
}

export class AuthClient {
  session = config.selfHosted ? null : loadStored<AuthSession | null>(SESSION_KEY, null);
  private refreshInFlight: Promise<void> | null = null;

  get isAuthenticated() {
    return Boolean(this.session?.accessToken);
  }

  get isNetlifySession() {
    if (this.session?.provider === "netlify") return true;
    const payload = this.session?.accessToken ? decodeJwtPayload(this.session.accessToken) : {};
    return payload.iss === "arvio-netlify";
  }

  private async netlifyAuth<T>(path: string, body: Record<string, unknown>) {
    if (!hasNetlifyBackendConfig()) {
      return jsonRequest<T>(`/api/cloud-auth/${path}`, { method: "POST", body: JSON.stringify(body) });
    }
    return jsonRequest<T>(`${config.netlifyBackendUrl.replace(/\/+$/, "")}/${path.replace(/^\/+/, "")}`, {
      method: "POST",
      headers: {
        apikey: config.appAnonKey,
        Authorization: `Bearer ${config.appAnonKey}`
      },
      body: JSON.stringify(body)
    });
  }

  async signIn(email: string, password: string) {
    if (hasNetlifyBackendUrl()) {
      try {
        const response = await this.netlifyAuth<SupabaseAuthResponse>("auth-login", { email, password });
        this.session = sessionFromResponse(response, email, "netlify");
        saveStored(SESSION_KEY, this.session);
        return this.session;
      } catch (error) {
        throw error;
      }
    }
    if (!hasSupabaseConfig()) throw new Error("Supabase is not configured");
    const response = await jsonRequest<SupabaseAuthResponse>(`${config.supabaseUrl}/auth/v1/token?grant_type=password`, {
      method: "POST",
      headers: { apikey: config.supabaseAnonKey },
      body: JSON.stringify({ email, password })
    });
    this.session = sessionFromResponse(response, email, "supabase");
    saveStored(SESSION_KEY, this.session);
    return this.session;
  }

  async signUp(email: string, password: string) {
    if (hasNetlifyBackendUrl()) {
      try {
        const response = await this.netlifyAuth<SupabaseAuthResponse>("cloud-auth-email", { email, password });
        this.session = sessionFromResponse(response, email, "netlify");
        saveStored(SESSION_KEY, this.session);
        return this.session;
      } catch (error) {
        throw error;
      }
    }
    if (!hasSupabaseConfig()) throw new Error("Supabase is not configured");
    const response = await jsonRequest<SupabaseAuthResponse>(`${config.supabaseUrl}/auth/v1/signup`, {
      method: "POST",
      headers: { apikey: config.supabaseAnonKey },
      body: JSON.stringify({ email, password })
    });
    this.session = sessionFromResponse(response, email, "supabase");
    saveStored(SESSION_KEY, this.session);
    return this.session;
  }

  async accessToken() {
    if (!this.session) throw new Error("Sign in required");
    if (this.session.expiresAt - Date.now() < 120000) {
      await this.refresh();
    }
    if (!this.session?.accessToken) throw new Error("Sign in required");
    return this.session.accessToken;
  }

  async refresh() {
    if (this.refreshInFlight) return this.refreshInFlight;
    const sourceSession = this.session;
    if (!sourceSession) throw new Error("Sign in required");

    const refresh = (async () => {
      const payload = sourceSession.accessToken ? decodeJwtPayload(sourceSession.accessToken) : {};
      const isNetlify = sourceSession.provider === "netlify" || payload.iss === "arvio-netlify";
      const response = isNetlify
        ? await this.netlifyAuth<SupabaseAuthResponse>("auth-refresh", { refresh_token: sourceSession.refreshToken })
        : await jsonRequest<SupabaseAuthResponse>(`${config.supabaseUrl}/auth/v1/token?grant_type=refresh_token`, {
            method: "POST",
            headers: { apikey: config.supabaseAnonKey },
            body: JSON.stringify({ refresh_token: sourceSession.refreshToken })
          });

      // A sign-out while refresh was in flight must not silently restore the session.
      if (!this.session || this.session.refreshToken !== sourceSession.refreshToken) return;
      this.session = sessionFromResponse(response, sourceSession.email, isNetlify ? "netlify" : "supabase");
      saveStored(SESSION_KEY, this.session);
    })();

    this.refreshInFlight = refresh;
    try {
      await refresh;
    } finally {
      if (this.refreshInFlight === refresh) this.refreshInFlight = null;
    }
  }

  async supabase<T>(path: string, init: RequestInit = {}) {
    if (this.isNetlifySession) {
      throw new Error("This ARVIO Cloud session must use the ARVIO backend sync API.");
    }
    const token = await this.accessToken();
    return jsonRequest<T>(`${config.supabaseUrl}${path}`, {
      ...init,
      headers: {
        apikey: config.supabaseAnonKey,
        Authorization: `Bearer ${token}`,
        ...(init.headers ?? {})
      }
    });
  }

  async loadProfile() {
    if (!this.session) return null;
    const rows = await this.supabase<UserProfile[]>(
      `/rest/v1/profiles?id=eq.${this.session.userId}&select=id,email,addons,default_subtitle,auto_play_next`
    );
    return rows[0] ?? { id: this.session.userId, email: this.session.email };
  }

  signOut() {
    this.session = null;
    removeStored(SESSION_KEY);
  }
}

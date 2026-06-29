import { config, hasSupabaseConfig } from "./config";
import { jsonRequest } from "./http";
import { loadStored, removeStored, saveStored } from "./storage";
import type { AuthSession, UserProfile } from "./types";

const SESSION_KEY = "arvio.web.supabase.session";

interface SupabaseAuthResponse {
  access_token: string;
  refresh_token: string;
  expires_in?: number;
  user?: { id?: string; email?: string };
}

function decodeJwtPayload(token: string): Record<string, unknown> {
  const part = token.split(".")[1];
  if (!part) return {};
  const padded = part.replace(/-/g, "+").replace(/_/g, "/").padEnd(Math.ceil(part.length / 4) * 4, "=");
  try {
    return JSON.parse(atob(padded)) as Record<string, unknown>;
  } catch {
    return {};
  }
}

function sessionFromResponse(response: SupabaseAuthResponse, fallbackEmail: string): AuthSession {
  const payload = decodeJwtPayload(response.access_token);
  const userId = response.user?.id ?? (payload.sub as string | undefined) ?? "";
  const email = response.user?.email ?? (payload.email as string | undefined) ?? fallbackEmail;
  return {
    accessToken: response.access_token,
    refreshToken: response.refresh_token,
    userId,
    email,
    expiresAt: Date.now() + (response.expires_in ?? 3600) * 1000
  };
}

export class AuthClient {
  session = loadStored<AuthSession | null>(SESSION_KEY, null);

  get isAuthenticated() {
    return Boolean(this.session?.accessToken);
  }

  async signIn(email: string, password: string) {
    if (!hasSupabaseConfig()) throw new Error("Supabase is not configured");
    const response = await jsonRequest<SupabaseAuthResponse>(`${config.supabaseUrl}/auth/v1/token?grant_type=password`, {
      method: "POST",
      headers: { apikey: config.supabaseAnonKey },
      body: JSON.stringify({ email, password })
    });
    this.session = sessionFromResponse(response, email);
    saveStored(SESSION_KEY, this.session);
    return this.session;
  }

  async signUp(email: string, password: string) {
    if (!hasSupabaseConfig()) throw new Error("Supabase is not configured");
    const response = await jsonRequest<SupabaseAuthResponse>(`${config.supabaseUrl}/auth/v1/signup`, {
      method: "POST",
      headers: { apikey: config.supabaseAnonKey },
      body: JSON.stringify({ email, password })
    });
    this.session = sessionFromResponse(response, email);
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
    if (!this.session) throw new Error("Sign in required");
    const response = await jsonRequest<SupabaseAuthResponse>(`${config.supabaseUrl}/auth/v1/token?grant_type=refresh_token`, {
      method: "POST",
      headers: { apikey: config.supabaseAnonKey },
      body: JSON.stringify({ refresh_token: this.session.refreshToken })
    });
    this.session = sessionFromResponse(response, this.session.email);
    saveStored(SESSION_KEY, this.session);
  }

  async supabase<T>(path: string, init: RequestInit = {}) {
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

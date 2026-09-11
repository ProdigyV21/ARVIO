import { AuthClient } from "./auth";
import { config } from "./config";
import { HttpError, jsonRequest } from "./http";
import { loadStored, saveStored } from "./storage";
import { currentEntitlement } from "./entitlementPolicy";

// Web subscription entitlement — gates the web app only (the APK never checks
// this). Source of truth is the auth backend (auth.arvio.tv), keyed by the
// account email. We cache the last-known state so the app can render instantly
// and fail-open on a transient backend error (a paying user must never be
// locked out by a hiccup), while an unknown/empty state fails CLOSED to the
// paywall.

export interface EntitlementState {
  entitled: boolean;
  reason: "subscription" | "trial" | "expired" | "none" | "unknown";
  status: string;
  source: string | null;
  tier?: string | null;
  expiresAt: string | null;
  trialAvailable: boolean;
  trialDurationDays?: number;
  updatedAt?: string | null;
}

const CACHE_KEY_PREFIX = "arvio.web.entitlement.v2";
const CACHE_TTL_MS = 6 * 60 * 60 * 1000;
export const ENTITLEMENT_REFRESH_EVENT = "arvio:membership-refresh";

async function backendRequest<T>(auth: AuthClient, path: string, init: RequestInit = {}): Promise<T> {
  const token = await auth.accessToken();
  return jsonRequest<T>(`${config.netlifyBackendUrl.replace(/\/+$/, "")}/${path.replace(/^\/+/, "")}`, {
    ...init,
    headers: {
      Authorization: `Bearer ${token}`,
      ...(init.headers ?? {})
    }
  });
}

function accountCacheKey(auth: AuthClient): string | null {
  const accountId = auth.session?.userId?.trim();
  return accountId ? `${CACHE_KEY_PREFIX}:${accountId}` : null;
}

function readCache(auth: AuthClient): { at: number; state: EntitlementState } | null {
  const key = accountCacheKey(auth);
  return key ? loadStored<{ at: number; state: EntitlementState } | null>(key, null) : null;
}

export function cachedEntitlement(auth: AuthClient): EntitlementState | null {
  const cached = readCache(auth);
  return cached && Date.now() - cached.at < CACHE_TTL_MS ? currentEntitlement(cached.state) : null;
}

function cache(key: string | null, state: EntitlementState) {
  if (key) saveStored(key, { at: Date.now(), state });
}

/** Fetch the live entitlement for the signed-in account. */
export async function fetchEntitlement(auth: AuthClient): Promise<EntitlementState> {
  if (!auth.session) {
    return { entitled: false, reason: "none", status: "none", source: null, expiresAt: null, trialAvailable: true };
  }
  const key = accountCacheKey(auth);
  let state = await backendRequest<EntitlementState>(auth, "entitlement-status", { method: "GET" });
  // Netlify Blobs reads can briefly lag a fresh write (a just-paid subscription
  // or trial can propagate over a few seconds). If we hold a cached ENTITLED
  // state but the live read says none/expired, retry once before locking the
  // user out — never boot a paying user to the paywall over propagation lag.
  if (!state.entitled) {
    const cached = key === accountCacheKey(auth) ? cachedEntitlement(auth) : null;
    if (cached?.entitled) {
      await new Promise((r) => setTimeout(r, 2500));
      const retry = await backendRequest<EntitlementState>(auth, "entitlement-status", { method: "GET" }).catch(() => null);
      if (retry?.entitled) state = retry;
    }
  }
  if (key === accountCacheKey(auth)) cache(key, state);
  return state;
}

/** Start the one-time 3-day trial for the signed-in account. */
export async function startTrial(auth: AuthClient): Promise<EntitlementState> {
  if (!auth.session) throw new HttpError(401, "Sign in required");
  const key = accountCacheKey(auth);
  const attempt = () => backendRequest<EntitlementState>(auth, "entitlement-status", {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ action: "start-trial" })
  });
  let state: EntitlementState;
  try {
    state = await attempt();
  } catch (error) {
    // A stale access token 401s here even though the paywall itself rendered
    // (its GET may have come from cache). Refresh the session once and retry
    // before surfacing an error — this was the main source of "could not
    // start the trial" reports.
    if (error instanceof HttpError && error.status === 401) {
      try {
        await auth.refresh();
      } catch {
        throw new HttpError(401, "Session expired");
      }
      state = await attempt();
    } else {
      throw error;
    }
  }
  if (key === accountCacheKey(auth)) cache(key, state);
  return state;
}

/** Link a Ko-fi/PayPal email whose payment used a different address. */
export async function linkKofiEmail(auth: AuthClient, kofiEmail: string, code?: string): Promise<EntitlementState & { verificationRequired?: boolean }> {
  const key = accountCacheKey(auth);
  const state = await backendRequest<EntitlementState & { verificationRequired?: boolean }>(auth, "entitlement-link", {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ kofiEmail, code })
  });
  if (!state.verificationRequired && key === accountCacheKey(auth)) cache(key, state);
  return state;
}

// URL the paywall's subscribe button opens. Set NEXT_PUBLIC_KOFI_URL to your
// Ko-fi membership page (e.g. https://ko-fi.com/arvio/tiers).
export function kofiSubscribeUrl(): string {
  return config.kofiUrl || "https://ko-fi.com/";
}

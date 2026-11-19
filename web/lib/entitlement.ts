import { AuthClient } from "./auth";
import { REGIONAL_YEARLY_URL, regionalYearly } from "./regionalPricing";
import { config } from "./config";
import { jsonRequest } from "./http";
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
  // "grace": the paid end has passed but a late renewal is still expected;
  // access continues until graceEndsAt.
  reason: "subscription" | "grace" | "partner" | "trial" | "expired" | "none" | "unknown";
  status: string;
  source: string | null;
  tier?: string | null;
  plan?: "monthly" | "yearly" | "partner" | null;
  organizationName?: string;
  expiresAt: string | null;
  graceEndsAt?: string | null;
  // Paid at least once: tells a lapsed member apart from someone who never paid.
  everPaid?: boolean;
  startedAt?: string | null;
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
    return { entitled: false, reason: "none", status: "none", source: null, expiresAt: null, trialAvailable: false, trialDurationDays: 0 };
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
  state = currentEntitlement(state) ?? state;
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

export interface ReferralStatus {
  code: string;
  link: string;
  joined: number;
  rewardsThisYear: number;
  rewardsLeftThisYear: number;
  rewardDays: number;
  maxRewardsPerYear: number;
}

export function fetchReferralStatus(auth: AuthClient): Promise<ReferralStatus> {
  return backendRequest<ReferralStatus>(auth, "referral-status", { method: "GET" });
}

export function claimReferralCode(auth: AuthClient, code: string): Promise<{ ok: boolean; error?: string }> {
  return backendRequest(auth, "referral-claim", {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ code })
  });
}

export const EXIT_REASONS = ["too_expensive", "playback_problems", "missing_feature", "only_needed_briefly", "use_android_instead", "other"] as const;
export type ExitReason = typeof EXIT_REASONS[number];

export function submitExitSurvey(auth: AuthClient, reason: ExitReason, detail = ""): Promise<{ ok: boolean }> {
  return backendRequest(auth, "premium-exit-survey", {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ reason, detail: detail.slice(0, 300) })
  });
}

export function fetchStreamTicket(auth: AuthClient): Promise<{ ticket: string; expiresAt: string }> {
  return backendRequest(auth, "stream-ticket", { method: "GET" });
}

// The 12-month pass on Ko-fi Shop; empty when the yearly plan is not set up.
// Visitors from regional-price countries get the 50% discount link instead.
export function kofiYearlyUrl(): string {
  if (!config.kofiYearlyUrl) return "";
  return regionalYearly() ? REGIONAL_YEARLY_URL : config.kofiYearlyUrl;
}

// URL the paywall's subscribe button opens. Set NEXT_PUBLIC_KOFI_URL to your
// Ko-fi membership page (e.g. https://ko-fi.com/arvio/tiers).
export function kofiSubscribeUrl(): string {
  return config.kofiUrl || "https://ko-fi.com/";
}

import type { AuthClient } from "./auth";
import { config } from "./config";
import { jsonRequest } from "./http";

export type PremiumFunnelEvent =
  | "journey_connected"
  | "web_opened"
  | "sources_configured"
  | "sources_missing"
  | "playback_requested"
  | "playback_started"
  | "playback_failed"
  | "paywall_view"
  | "account_connected"
  | "trial_requested"
  | "trial_start_failed"
  | "checkout_opened"
  | "membership_cta_viewed"
  | "checkout_recovery_opened"
  | "checkout_recovery_closed"
  | "checkout_returned"
  | "payment_status_checked"
  | "membership_link_started"
  | "membership_linked"
  | "membership_link_failed"
  | "first_playback"
  | "external_playback_requested"
  | "download_requested"
  | "download_handoff"
  | "download_failed";

const ATTRIBUTION_KEY = "arvio.premium.attribution.v2";
const JOURNEY_KEY = "arvio.premium.journey.v1";
const ATTRIBUTION_OWNER_KEY = "arvio.premium.attribution-owner.v1";
const ATTRIBUTION_TTL = 24 * 60 * 60 * 1000;
const JOURNEY_ID = /^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[a-f0-9]{12}$/i;
const LANDING_PAGES = new Set(["home", "premium", "web", "guides", "android", "jellyfin", "servers", "addons", "debrid", "tracking", "live", "subtitles", "firetv", "browser", "collections", "selfhost", "playback"]);
const INTERNAL_HOSTS = new Set(["arvio.tv", "www.arvio.tv", "web.arvio.tv", "auth.arvio.tv"]);
let capturedQuery = "";
let memoryAttribution: Record<string, string> = {};
let memoryJourney = "";
let attributionExpiresAt = 0;
let attributionOwner = "";
let suppressDocumentReferrer = false;
export const TRIAL_INTENT_KEY = "arvio.premium.trial-intent.v1";
const inFlight = new Set<string>();
const dailyRecorded = new Map<string, string>();
const linkedJourneys = new Set<string>();

function browserStorage(kind: "sessionStorage" | "localStorage") {
  try { return typeof window === "undefined" ? undefined : window[kind]; } catch { return undefined; }
}

function storageGet(storage: Storage | undefined, key: string) {
  try { return storage?.getItem(key) ?? null; } catch { return null; }
}

function storageSet(storage: Storage | undefined, key: string, value: string) {
  try { storage?.setItem(key, value); } catch { /* storage is optional */ }
}

function analyticsAllowed() {
  if (config.selfHosted) return false;
  if (typeof navigator === "undefined") return true;
  return navigator.doNotTrack !== "1" && !("globalPrivacyControl" in navigator && navigator.globalPrivacyControl === true);
}

function clean(value: string | null, max = 80) {
  const text = String(value || "");
  return /^[a-z0-9._-]+$/i.test(text) ? text.slice(0, max).toLowerCase() : "";
}

function landingPage(value: string | null | undefined) {
  return value && LANDING_PAGES.has(value) ? value : "";
}

function referral() {
  let referrer = "";
  if (!suppressDocumentReferrer) {
    try { referrer = clean(new URL(document.referrer).hostname); } catch { /* no usable referring site */ }
  }
  const organic = /^(?:www\.)?google\.(?:com|[a-z]{2}|com\.[a-z]{2}|co\.[a-z]{2})$/.test(referrer) ||
    ["www.bing.com", "bing.com", "duckduckgo.com", "www.duckduckgo.com"].includes(referrer);
  return { referrer, source: referrer || "direct", medium: INTERNAL_HOSTS.has(referrer) ? "internal" : organic ? "organic" : referrer ? "referral" : "direct" };
}

// A page-navigation ID links the public Premium page to this signed-in session.
// It is never a cookie, persistent browser ID, URL sent to Ko-fi, or an identity
// proof. Attribution expires after a day and resets when the account changes.
export function capturePremiumAttribution() {
  if (!analyticsAllowed() || typeof window === "undefined") return {};
  const disk = browserStorage("sessionStorage");
  const query = window.location.search;
  const params = new URLSearchParams(query);
  const now = Date.now();
  if (!attributionExpiresAt) {
    try {
      const saved = JSON.parse(storageGet(disk, ATTRIBUTION_KEY) || "{}");
      if (saved.expiresAt > now && saved.expiresAt <= now + ATTRIBUTION_TTL) {
        memoryAttribution = saved.metadata || {};
        memoryJourney = storageGet(disk, JOURNEY_KEY) || "";
        attributionExpiresAt = saved.expiresAt;
      }
      attributionOwner = storageGet(disk, ATTRIBUTION_OWNER_KEY) || "";
    } catch { /* storage is optional */ }
  }
  if (attributionExpiresAt && attributionExpiresAt <= now) {
    memoryAttribution = {}; memoryJourney = "";
    // document.referrer belongs to the original document, not a fresh visit a
    // day later. Expiry must not resurrect that earlier campaign/referral.
    suppressDocumentReferrer = true;
  }
  const freshQuery = query !== capturedQuery;
  const incomingJourney = params.get("arvio_journey") || "";
  const incoming = freshQuery && JOURNEY_ID.test(incomingJourney) ? incomingJourney.toLowerCase() : "";
  const incomingSource = freshQuery ? clean(params.get("utm_source")) : "";
  const changedCampaign = freshQuery && ["source", "medium", "campaign", "content"].some(name => {
    const value = clean(params.get(`utm_${name}`));
    return value && value !== memoryAttribution[name];
  });
  if (incoming) {
    memoryJourney = incoming;
    memoryAttribution = {};
    attributionExpiresAt = now + ATTRIBUTION_TTL;
    // Avoid a random navigation ID leaking in outbound referrers or copied URLs.
    try {
      const url = new URL(window.location.href);
      url.searchParams.delete("arvio_journey");
      url.searchParams.delete("arvio_landing");
      window.history.replaceState(window.history.state, "", url.toString());
    } catch { /* optional in embedded/restricted browsers */ }
  } else if (changedCampaign) {
    memoryAttribution = {}; memoryJourney = "";
    attributionExpiresAt = now + ATTRIBUTION_TTL;
  }
  const observed = referral();
  const tag = (name: string, fallback: string) => (freshQuery ? clean(params.get(`utm_${name}`)) : "") || clean(memoryAttribution[name]) || fallback;
  memoryAttribution = {
    source: tag("source", observed.source), medium: tag("medium", incomingSource ? "campaign" : observed.medium),
    campaign: tag("campaign", "premium"), content: tag("content", "unspecified"),
    referrer: clean(memoryAttribution.referrer || observed.referrer), page: "web",
    landing_page: (freshQuery && landingPage(params.get("arvio_landing"))) || landingPage(memoryAttribution.landing_page) || "web"
  };
  if (!attributionExpiresAt || attributionExpiresAt <= now) attributionExpiresAt = now + ATTRIBUTION_TTL;
  capturedQuery = window.location.search;
  storageSet(disk, ATTRIBUTION_KEY, JSON.stringify({ expiresAt: attributionExpiresAt, metadata: memoryAttribution }));
  storageSet(disk, JOURNEY_KEY, memoryJourney);
  return memoryAttribution;
}

function attributionForAccount(accountId: string) {
  capturePremiumAttribution();
  if (attributionOwner && attributionOwner !== accountId) {
    memoryAttribution = {};
    memoryJourney = "";
    attributionExpiresAt = 0;
    suppressDocumentReferrer = true;
    const disk = browserStorage("sessionStorage");
    storageSet(disk, ATTRIBUTION_KEY, "{}");
    storageSet(disk, JOURNEY_KEY, "");
  }
  attributionOwner = accountId;
  storageSet(browserStorage("sessionStorage"), ATTRIBUTION_OWNER_KEY, accountId);
  return { metadata: capturePremiumAttribution(), journeyId: JOURNEY_ID.test(memoryJourney) ? memoryJourney : undefined };
}

export async function trackPremiumEvent(
  auth: AuthClient,
  eventName: PremiumFunnelEvent,
  metadata: Record<string, string | number | boolean> = {},
  oncePerSession = false
) {
  if (!analyticsAllowed() || !auth.session) return false;
  const sessionKey = `arvio.premium.session.${auth.session.userId}.${eventName}`;
  const sessionStore = browserStorage("sessionStorage");
  if (oncePerSession && storageGet(sessionStore, sessionKey)) return true;
  if (oncePerSession && inFlight.has(sessionKey)) return false;
  if (oncePerSession) inFlight.add(sessionKey);
  try {
    const accountId = auth.session.userId;
    const attribution = attributionForAccount(accountId);
    const token = await auth.accessToken();
    if (auth.session?.userId !== accountId) return false;
    await jsonRequest(`${config.netlifyBackendUrl.replace(/\/+$/, "")}/premium-funnel-event`, {
      method: "POST",
      keepalive: true,
      headers: { Authorization: `Bearer ${token}` },
      body: JSON.stringify({
        event_name: eventName,
        journey_id: attribution.journeyId,
        metadata: { ...attribution.metadata, ...metadata }
      })
    });
    if (oncePerSession) storageSet(sessionStore, sessionKey, "1");
    return true;
  } catch {
    return false;
  } finally {
    if (oncePerSession) inFlight.delete(sessionKey);
  }
}

export async function trackPremiumMilestone(
  auth: AuthClient,
  eventName: Extract<PremiumFunnelEvent, "account_connected" | "first_playback">,
  metadata: Record<string, string | number | boolean> = {}
) {
  const accountId = auth.session?.userId;
  if (!accountId || typeof window === "undefined") return false;
  const key = `arvio.premium.milestone.${eventName}.${accountId}`;
  if (storageGet(browserStorage("localStorage"), key)) return true;
  const recorded = await trackPremiumEvent(auth, eventName, metadata);
  if (recorded) storageSet(browserStorage("localStorage"), key, "1");
  return recorded;
}

// One diagnostic per account/event/UTC day, not one write per seek, buffer or
// render. The in-memory guard also works when Safari blocks browser storage.
export async function trackPremiumDaily(
  auth: AuthClient,
  eventName: PremiumFunnelEvent,
  metadata: Record<string, string | number | boolean> = {},
  now = new Date()
) {
  if (!analyticsAllowed() || !config.paywallEnabled || !auth.session) return false;
  const accountId = auth.session.userId;
  const attribution = attributionForAccount(accountId);
  if (attribution.journeyId) {
    const journeyKey = `${accountId}.${attribution.journeyId}`;
    if (!linkedJourneys.has(journeyKey)) {
      linkedJourneys.add(journeyKey);
      void trackPremiumEvent(auth, "journey_connected").then(recorded => { if (!recorded) linkedJourneys.delete(journeyKey); });
    }
  }
  const date = now.toISOString().slice(0, 10);
  const key = `arvio.premium.daily.${accountId}.${eventName}`;
  const disk = browserStorage("localStorage");
  if (dailyRecorded.get(key) === date || storageGet(disk, key) === date) return true;
  if (inFlight.has(key)) return false;
  inFlight.add(key);
  try {
    const recorded = await trackPremiumEvent(auth, eventName, metadata);
    if (recorded && auth.session?.userId === accountId) {
      dailyRecorded.set(key, date);
      storageSet(disk, key, date);
    }
    return recorded;
  } finally { inFlight.delete(key); }
}

// Per-attempt diagnostics (currently playback failures). Unlike the daily
// helper above, every attempt is sent — so the page caps them: one broken
// title hopping through 40 dead links must not become 40 function calls. The
// server folds attempts into the account's single daily record.
const ATTEMPT_LIMIT_PER_PAGE = 12;
const attemptsSent = new Map<string, number>();

export async function trackPremiumAttempt(
  auth: AuthClient,
  eventName: Extract<PremiumFunnelEvent, "playback_failed">,
  metadata: Record<string, string | number | boolean> = {},
  limit = ATTEMPT_LIMIT_PER_PAGE
) {
  if (!analyticsAllowed() || !config.paywallEnabled || !auth.session) return false;
  const key = `${auth.session.userId}.${eventName}`;
  const sent = attemptsSent.get(key) ?? 0;
  if (sent >= limit) return false;
  attemptsSent.set(key, sent + 1);
  return trackPremiumEvent(auth, eventName, { ...metadata, attempt: true });
}

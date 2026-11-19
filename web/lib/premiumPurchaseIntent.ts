import { config } from "./config";

export type PremiumPurchasePlan = "monthly" | "yearly";
export type PremiumPurchaseIntent = { plan: PremiumPurchasePlan; createdAt: number };

export const PREMIUM_PURCHASE_INTENT_TTL = 30 * 60 * 1000;
const STORAGE_KEY = "arvio.premium.purchase-intent.v1";
const PLAN_KEY = "premium_plan";
const TIME_KEY = "premium_intent_at";
const ATTRIBUTION_KEYS = ["utm_source", "utm_medium", "utm_campaign", "utm_content", "arvio_journey", "arvio_landing"];
let memory: PremiumPurchaseIntent | null = null;
let initialized = false;
let capturedQuery = "";

function isPlan(value: unknown): value is PremiumPurchasePlan {
  return value === "monthly" || value === "yearly";
}

function validIntent(value: unknown, now: number): value is PremiumPurchaseIntent {
  if (!value || typeof value !== "object") return false;
  const intent = value as Partial<PremiumPurchaseIntent>;
  return isPlan(intent.plan) && Number.isSafeInteger(intent.createdAt) &&
    Number(intent.createdAt) > 0 && Number(intent.createdAt) <= now && now - Number(intent.createdAt) < PREMIUM_PURCHASE_INTENT_TTL;
}

/** Selection only: never a price, payment URL, entitlement, or proof of payment. */
export function parsePremiumPurchaseIntent(search: string, now = Date.now()): PremiumPurchaseIntent | null {
  if (search.length > 4096) return null;
  const params = new URLSearchParams(search);
  if (params.getAll(PLAN_KEY).length !== 1 || params.getAll(TIME_KEY).length > 1) return null;
  const plan = params.get(PLAN_KEY);
  if (!isPlan(plan)) return null;
  const timestamp = params.get(TIME_KEY);
  if (timestamp !== null && !/^[1-9]\d{0,15}$/.test(timestamp)) return null;
  const intent = { plan, createdAt: timestamp === null ? now : Number(timestamp) };
  return validIntent(intent, now) ? intent : null;
}

/** Fixed official origin and allowlisted query only; works when storage is blocked. */
export function premiumPurchaseLoginRedirect(origin: string, search: string, now = Date.now()): string | null {
  if (origin !== "https://web.arvio.tv") return null;
  const intent = parsePremiumPurchaseIntent(search, now);
  if (!intent) return null;
  const incoming = new URLSearchParams(search);
  const destination = new URL("/", origin);
  destination.searchParams.set(PLAN_KEY, intent.plan);
  destination.searchParams.set(TIME_KEY, String(intent.createdAt));
  for (const key of ATTRIBUTION_KEYS) {
    const values = incoming.getAll(key);
    if (values.length === 1 && /^[a-z0-9._-]{1,80}$/i.test(values[0])) destination.searchParams.set(key, values[0]);
  }
  return destination.toString();
}

function officialHosted() {
  return !config.selfHosted && config.paywallEnabled && typeof window !== "undefined" && window.location.origin === "https://web.arvio.tv";
}

function persist(intent: PremiumPurchaseIntent | null) {
  try {
    if (intent) window.sessionStorage.setItem(STORAGE_KEY, JSON.stringify(intent));
    else window.sessionStorage.removeItem(STORAGE_KEY);
  } catch { /* Optional, short-lived functional storage. No identity is stored. */ }
}

function replaceQuery(intent: PremiumPurchaseIntent | null) {
  try {
    const url = new URL(window.location.href);
    url.searchParams.delete(PLAN_KEY);
    url.searchParams.delete(TIME_KEY);
    // The bounded selection and timestamp survive sign-in even if sessionStorage
    // is unavailable. They are removed as soon as the confirmation consumes it.
    if (intent) {
      url.searchParams.set(PLAN_KEY, intent.plan);
      url.searchParams.set(TIME_KEY, String(intent.createdAt));
    }
    window.history.replaceState(window.history.state, "", url.toString());
    capturedQuery = window.location.search;
  } catch { /* Restrictive embedded history must not stop sign-in or checkout. */ }
}

/** Capture before login; repeated calls never extend the original 30-minute TTL. */
export function capturePremiumPurchaseIntent(): PremiumPurchaseIntent | null {
  if (!officialHosted()) return null;
  const now = Date.now();
  if (!initialized) {
    initialized = true;
    try {
      const saved: unknown = JSON.parse(window.sessionStorage.getItem(STORAGE_KEY) || "null");
      if (validIntent(saved, now)) memory = saved;
      else persist(null);
    } catch { /* Memory and the bounded URL support blocked storage. */ }
  }
  const query = window.location.search;
  const params = new URLSearchParams(query);
  if (query !== capturedQuery && (params.has(PLAN_KEY) || params.has(TIME_KEY))) {
    capturedQuery = query;
    memory = parsePremiumPurchaseIntent(query, now);
    persist(memory);
    replaceQuery(memory);
  }
  if (memory && !validIntent(memory, now)) {
    memory = null;
    persist(null);
    replaceQuery(null);
  }
  return memory ? { ...memory } : null;
}

/** Consume once into the current confirmation; remounts/reloads cannot reopen it. */
export function consumePremiumPurchaseIntent(): PremiumPurchasePlan | null {
  const intent = capturePremiumPurchaseIntent();
  if (!officialHosted()) return null;
  clearPremiumPurchaseIntent();
  return intent?.plan ?? null;
}

export function clearPremiumPurchaseIntent(): void {
  if (typeof window === "undefined") return;
  memory = null;
  initialized = true;
  persist(null);
  replaceQuery(null);
}

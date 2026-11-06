// Regional price for the 12-month pass: visitors from lower-income countries
// get Ko-fi's 50% discount link ($10 instead of $20). The monthly membership
// can't be discounted on Ko-fi and stays $2.99 everywhere.
//
// The visitor's country comes from /api/geo (the CDN's IP lookup). Nothing is
// stored server-side; the browser keeps the answer for the session.
import { useEffect, useState } from "react";

export const REGIONAL_YEARLY_URL = "https://ko-fi.com/arvio/link/ARVIOREGION50";
export const STANDARD_YEARLY_PRICE = "$20";
export const REGIONAL_YEARLY_PRICE = "$10";
export const REGIONAL_YEARLY_PER_MONTH = "$0.83";
export const REGIONAL_YEARLY_SAVING = "72%";

// World Bank low and lower-middle income economies plus upper-middle ones
// where $20 is a lot of money; countries where Ko-fi payments don't work are
// left out.
export const REGIONAL_COUNTRIES = new Set([
  "AR", "BD", "BO", "BR", "CI", "CM", "CO", "DO", "DZ", "EC", "EG", "ET", "GE", "GH", "GT", "HN",
  "ID", "IN", "JM", "JO", "KE", "KG", "KH", "KZ", "LA", "LK", "MA", "MD", "MM", "MN", "MX", "NG",
  "NI", "NP", "PE", "PH", "PK", "PY", "SN", "SV", "TH", "TN", "TR", "TZ", "UA", "UG", "UZ", "VE",
  "VN", "ZA", "ZM", "ZW"
]);

const STORAGE_KEY = "arvio.visitorCountry.v1";
let country: string | null | undefined;
let request: Promise<string | null> | null = null;
const listeners = new Set<() => void>();

function stored(): string | null | undefined {
  try { const value = sessionStorage.getItem(STORAGE_KEY); return value === null ? undefined : value || null; } catch { return undefined; }
}

export function visitorCountry(): string | null {
  if (country === undefined && typeof window !== "undefined") { const cached = stored(); if (cached !== undefined) country = cached; }
  return country ?? null;
}

export function loadVisitorCountry(): Promise<string | null> {
  if (typeof window === "undefined") return Promise.resolve(null);
  if (visitorCountry() || country === null) return Promise.resolve(country ?? null);
  request ??= fetch("/api/geo", { cache: "no-store" })
    .then(response => response.ok ? response.json() : null)
    .then((body: { country?: unknown } | null) => typeof body?.country === "string" && /^[A-Z]{2}$/.test(body.country) ? body.country : null)
    .catch(() => null)
    .then(value => {
      country = value;
      try { sessionStorage.setItem(STORAGE_KEY, value ?? ""); } catch { /* optional */ }
      listeners.forEach(listener => listener());
      return value;
    });
  return request;
}

export function isRegionalCountry(code: string | null | undefined): boolean {
  return Boolean(code && REGIONAL_COUNTRIES.has(code.toUpperCase()));
}

/** True once the visitor is known to be in a regional-price country. */
export function regionalYearly(): boolean {
  return isRegionalCountry(visitorCountry());
}

/** Re-renders when the visitor's country arrives; returns regionalYearly(). */
export function useRegionalYearly(): boolean {
  const [regional, setRegional] = useState(() => regionalYearly());
  useEffect(() => {
    const update = () => setRegional(regionalYearly());
    listeners.add(update);
    void loadVisitorCountry().then(update);
    return () => { listeners.delete(update); };
  }, []);
  return regional;
}

import { config } from "./config";

function cleanErrorMessage(status: number, raw: string): string {
  const trimmed = raw.trim();
  if (!trimmed) return `Request failed with ${status}`;

  try {
    const parsed = JSON.parse(trimmed) as Record<string, unknown>;
    const rawNested = typeof parsed.raw === "string" ? parsed.raw : "";
    if (rawNested.includes("Cloudflare") || rawNested.includes("trakt.tv")) {
      return "The remote service blocked this browser request. Try again later or use the Android app for this action.";
    }
    const description = parsed.error_description ?? parsed.msg ?? parsed.message ?? parsed.error;
    if (typeof description === "string" && description.trim()) return description.trim();
  } catch {
    // Plain-text or HTML error bodies are handled below.
  }

  if (trimmed.startsWith("<") || trimmed.includes("<html") || trimmed.includes("Cloudflare")) {
    return "The remote service returned an HTML error page instead of API data.";
  }

  return trimmed.length > 240 ? `${trimmed.slice(0, 240)}...` : trimmed;
}

export class HttpError extends Error {
  status: number;
  retryAfter: string | null;
  constructor(status: number, message: string, retryAfter: string | null = null) {
    super(message);
    this.status = status;
    this.retryAfter = retryAfter;
  }
}

async function timedRequest<T>(url: string, init: RequestInit, consume: (response: Response) => Promise<T>): Promise<T> {
  const controller = new AbortController();
  const abort = () => controller.abort(init.signal?.reason);
  if (init.signal?.aborted) abort();
  else init.signal?.addEventListener("abort", abort, { once: true });
  const timer = setTimeout(() => controller.abort(new Error("The service took too long to respond. Please retry.")), 30_000);
  try { return await consume(await fetch(url, { ...init, signal: controller.signal })); }
  finally { clearTimeout(timer); init.signal?.removeEventListener("abort", abort); }
}

export async function jsonRequest<T>(url: string, init: RequestInit = {}): Promise<T> {
  return timedRequest(url, {
    ...init,
    headers: {
      Accept: "application/json",
      ...(init.body ? { "Content-Type": "application/json" } : {}),
      ...(init.headers ?? {})
    }
  }, async (response) => {
  if (!response.ok) {
    const message = await response.text().catch(() => "");
    throw new HttpError(response.status, cleanErrorMessage(response.status, message), response.headers.get("retry-after"));
  }
  if (response.status === 204) return undefined as T;
  return (await response.json()) as T;
  });
}

export async function textRequest(url: string, init: RequestInit = {}): Promise<string> {
  return timedRequest(url, init, async (response) => {
  if (!response.ok) {
    const message = await response.text().catch(() => "");
    throw new HttpError(response.status, cleanErrorMessage(response.status, message), response.headers.get("retry-after"));
  }
  return response.text();
  });
}

export function proxiedUrl(url: string, headers?: Record<string, string>) {
  const target = new URL("/api/proxy", window.location.origin);
  target.searchParams.set("url", url);
  if (headers && Object.keys(headers).length > 0) {
    target.searchParams.set("headers", btoa(JSON.stringify(headers)));
  }
  return target.toString();
}

// JSON-API relay via the Cloudflare resolver worker (free requests, and
// cinemeta/mdblist GETs are edge-cached there) instead of the Netlify
// /api/proxy function — debrid/catalog traffic was a large share of the
// Netlify credits burn. The worker only accepts an allowlist of API hosts;
// for anything else (or when no resolver is configured) use proxiedUrl.
export function apiProxiedUrl(url: string, headers?: Record<string, string>) {
  if (!config.resolverUrl) return proxiedUrl(url, headers);
  const target = new URL(`${config.resolverUrl.replace(/\/+$/, "")}/proxy`);
  target.searchParams.set("url", url);
  if (headers && Object.keys(headers).length > 0) {
    target.searchParams.set("h", btoa(JSON.stringify(headers)));
  }
  return target.toString();
}

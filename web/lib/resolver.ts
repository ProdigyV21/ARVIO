import { config } from "./config";
import { currentStreamTicket } from "./streamTicket";
import type { InstalledAddon, MediaItem, StreamSource } from "./types";

type ResolverEvent =
  | { type: "started"; addonCount?: number }
  | { type: "batch"; addonId?: string; addonName?: string; batch?: StreamSource[]; streams?: StreamSource[] }
  | { type: "final"; cached?: boolean; streams?: StreamSource[] }
  | { type: "error"; error?: string };

function normalizeStreamLabels(stream: StreamSource): StreamSource {
  const hints = stream.behaviorHints;
  return {
    ...stream,
    source: typeof stream.source === "string" ? stream.source : "",
    addonName: typeof stream.addonName === "string" ? stream.addonName : "Source",
    description: typeof stream.description === "string" ? stream.description : null,
    size: typeof stream.size === "string" ? stream.size : undefined,
    behaviorHints: hints && { ...hints, filename: typeof hints.filename === "string" ? hints.filename : null }
  };
}

export async function getResolverStreamsProgressive(
  addons: InstalledAddon[],
  item: MediaItem,
  season?: number,
  episode?: number,
  onUpdate?: (streams: StreamSource[], batch: StreamSource[]) => void
) {
  const endpoint = `${config.resolverUrl.replace(/\/+$/, "")}/sources`;
  const response = await fetch(endpoint, {
    method: "POST",
    headers: { "content-type": "application/json", accept: "application/x-ndjson, application/json" },
    body: JSON.stringify({ addons, item, season, episode })
  });

  if (!response.ok) {
    throw new Error(`Resolver failed with ${response.status}`);
  }

  const contentType = response.headers.get("content-type") ?? "";
  if (!response.body || contentType.includes("application/json")) {
    const payload = (await response.json()) as ResolverEvent;
    if (payload.type === "error") throw new Error(payload.error ?? "Resolver failed");
    const streams = payload.type === "final" ? payload.streams?.map(normalizeStreamLabels) ?? [] : [];
    if (streams.length) onUpdate?.(streams, streams);
    return streams;
  }

  const reader = response.body.getReader();
  const decoder = new TextDecoder();
  let buffer = "";
  let latest: StreamSource[] = [];

  while (true) {
    const { done, value } = await reader.read();
    if (done) break;
    buffer += decoder.decode(value, { stream: true });
    const lines = buffer.split("\n");
    buffer = lines.pop() ?? "";
    for (const line of lines) {
      const event = parseEvent(line);
      if (!event) continue;
      if (event.type === "error") throw new Error(event.error ?? "Resolver failed");
      if (event.type === "batch") {
        latest = event.streams?.map(normalizeStreamLabels) ?? latest;
        onUpdate?.(latest, event.batch?.map(normalizeStreamLabels) ?? []);
      }
      if (event.type === "final") {
        latest = event.streams?.map(normalizeStreamLabels) ?? latest;
      }
    }
  }

  if (buffer.trim()) {
    const event = parseEvent(buffer);
    if (event?.type === "final") latest = event.streams?.map(normalizeStreamLabels) ?? latest;
  }

  return latest;
}

export function resolverMediaUrl(url: string, headers?: Record<string, string>) {
  if (!config.resolverUrl) return null;
  const endpoint = new URL(`${config.resolverUrl.replace(/\/+$/, "")}/media`);
  endpoint.searchParams.set("url", url);
  if (headers && Object.keys(headers).length > 0) {
    endpoint.searchParams.set("h", btoa(JSON.stringify(headers)));
  }
  withStreamTicket(endpoint);
  return endpoint.toString();
}

// The relay can be limited to Premium members (STREAM_TICKET_SECRET on the
// worker); the ticket rides on every /media URL, and the worker passes it on
// to playlist segments.
function withStreamTicket(endpoint: URL) {
  const ticket = currentStreamTicket();
  if (ticket) endpoint.searchParams.set("t", ticket);
}

/** Relay only a selected source that explicitly requires browser-controlled headers. */
export function declaredHeaderRelayUrl(url: string, headers?: Record<string, string>) {
  const entries = Object.entries(headers ?? {});
  if (!config.resolverUrl || !entries.some(([name]) => /^(referer|origin|user-agent|cookie)$/i.test(name))) return null;
  // Match the existing resolver's upstream header allowlist. Never silently
  // drop a provider's custom authentication header just to use the relay.
  if (entries.some(([name, value]) => !/^(user-agent|referer|origin|icy-metadata|x-forwarded-for|authorization|cookie)$/i.test(name)
    && !(name.toLowerCase() === "accept" && value === "*/*"))) return null;
  try {
    const target = new URL(url);
    if (target.protocol !== "http:" && target.protocol !== "https:") return null;
    const relay = new URL(resolverMediaUrl(url, headers)!);
    if (target.origin === relay.origin && target.pathname === relay.pathname && target.searchParams.has("url")) return null;
    return relay.toString();
  } catch { return null; }
}

// External-player launch interstitial: iOS home-screen webapps silently drop
// custom-scheme navigations, but the Safari sheet they open for https links can
// launch app schemes (native "Open in …?" prompt). See worker /launch.
export function resolverLaunchUrl(schemeUrl: string) {
  if (!config.resolverUrl) return null;
  const endpoint = new URL(`${config.resolverUrl.replace(/\/+$/, "")}/launch`);
  endpoint.searchParams.set("to", schemeUrl);
  return endpoint.toString();
}

// Download proxy: the worker adds Content-Disposition: attachment so every
// browser (iOS Safari/Chrome preview files inline otherwise) hands the file to
// its download manager with visible progress.
export function resolverDownloadUrl(url: string, filename: string) {
  if (!config.resolverUrl) return null;
  const endpoint = new URL(`${config.resolverUrl.replace(/\/+$/, "")}/media`);
  endpoint.searchParams.set("url", url);
  endpoint.searchParams.set("dl", "1");
  endpoint.searchParams.set("filename", filename);
  withStreamTicket(endpoint);
  return endpoint.toString();
}

export function resolverSubtitleUrl(url: string) {
  if (!config.resolverUrl) return `/api/subtitle?url=${encodeURIComponent(url)}`;
  const endpoint = new URL(`${config.resolverUrl.replace(/\/+$/, "")}/subtitle`);
  endpoint.searchParams.set("url", url);
  return endpoint.toString();
}

function parseEvent(line: string) {
  const trimmed = line.trim();
  if (!trimmed) return null;
  try {
    return JSON.parse(trimmed) as ResolverEvent;
  } catch {
    return null;
  }
}

import { NextRequest, NextResponse } from "next/server";
import { allowsMediaProxy, safeProxyFetch, withinProxyBudget } from "@/lib/server/safeProxy";

const BLOCKED_HOSTS = new Set(["localhost", "127.0.0.1", "::1", "0.0.0.0"]);
const ALLOW_MEDIA_PROXY = allowsMediaProxy();

export async function GET(request: NextRequest) {
  if (!withinProxyBudget(request.headers.get("x-nf-client-connection-ip") ?? "local")) return NextResponse.json({ error: "Proxy request limit reached. Please wait." }, { status: 429, headers: { "retry-after": "60" } });
  const input = new URL(request.url);
  const raw = input.searchParams.get("url");
  if (!raw) return NextResponse.json({ error: "Missing url" }, { status: 400 });

  let target: URL;
  try {
    target = new URL(raw);
  } catch {
    return NextResponse.json({ error: "Invalid url" }, { status: 400 });
  }

  if (!["http:", "https:"].includes(target.protocol) || BLOCKED_HOSTS.has(target.hostname)) {
    return NextResponse.json({ error: "Blocked proxy target" }, { status: 400 });
  }
  const rewriteMode = input.searchParams.get("rewrite");
  const playlistOnlyRequest =
    (rewriteMode === "0" || rewriteMode === "direct" || rewriteMode === "worker") &&
    !request.headers.has("range") &&
    isLikelyPlaylistTarget(target);
  if (!ALLOW_MEDIA_PROXY && !playlistOnlyRequest && isLikelyMediaRequest(target, request)) {
    return NextResponse.json(
      { error: "Media proxy disabled to protect hosting bandwidth. Use direct playback or an external player." },
      { status: 403 }
    );
  }

  const forwardedHeaders = decodeHeaders(input.searchParams.get("headers")) ?? {};
  const range = request.headers.get("range");
  if (range) forwardedHeaders.range = range;
  const response = await fetchWithTimeout(target, {
    headers: forwardedHeaders,
    cache: "no-store",
    redirect: "follow",
    signal: request.signal
  });

  const contentType = response.headers.get("content-type") ?? "application/octet-stream";
  if (response.ok && shouldRewritePlaylist(target, contentType)) {
    let text: string;
    try { text = await response.text(); }
    catch (error) { return proxyFailure(error); }
    const hls = /^#EXT-X-/m.test(text);
    if (hls && Buffer.byteLength(text) > 2 * 1024 * 1024) {
      return NextResponse.json({ error: "Playlist exceeds the size limit" }, { status: 502, headers: { "cache-control": "no-store" } });
    }
    const rewritten = rewriteMode === "0" ? text : rewriteMode === "worker"
      ? rewritePlaylistToWorker(text, new URL(response.headers.get("x-arvio-final-url") ?? target), input.searchParams.get("headers"))
      : rewriteMode === "direct" || !ALLOW_MEDIA_PROXY ? rewritePlaylistToAbsolute(text, new URL(response.headers.get("x-arvio-final-url") ?? target)) : rewritePlaylist(text, new URL(response.headers.get("x-arvio-final-url") ?? target), request);
    const headers = new Headers();
    headers.set("content-type", contentType.includes("mpegurl") ? contentType : "application/vnd.apple.mpegurl");
    headers.set("cache-control", "private, max-age=5");
    headers.set("access-control-allow-origin", "*");
    if (!hls) cacheIptvCatalog(headers, target);
    return new NextResponse(rewritten, { status: response.status, headers });
  }

  const headers = new Headers();
  headers.set("content-type", contentType);
  const retryAfter = response.headers.get("retry-after");
  if (retryAfter) headers.set("retry-after", retryAfter);
  headers.set("x-content-type-options", "nosniff");
  const contentPolicy = response.headers.get("content-security-policy");
  if (contentPolicy) headers.set("content-security-policy", contentPolicy);
  headers.set("cache-control", playlistOnlyRequest ? "private, max-age=5" : "no-store");
  headers.set("access-control-allow-origin", "*");
  headers.set("accept-ranges", response.headers.get("accept-ranges") ?? "bytes");
  const contentLength = response.headers.get("content-length");
  const contentRange = response.headers.get("content-range");
  if (contentLength) headers.set("content-length", contentLength);
  if (contentRange) headers.set("content-range", contentRange);

  // Cache big, rarely-changing IPTV catalog responses (multi-MB channel/VOD
  // lists, M3U playlists) on the CDN so repeat loads don't re-invoke this
  // function and re-stream megabytes — a major credits burner for TV users.
  // The cache key varies by the FULL query string (Netlify-Vary: query is
  // mandatory here — without it every variant collapses into one entry), which
  // includes the user's credentials, so entries are effectively per-account.
  // Live "what's on now" data (get_short_epg etc.) is deliberately excluded.
  if (response.ok) cacheIptvCatalog(headers, target);

  return new NextResponse(response.body, { status: response.status, headers });
}

const CACHEABLE_XTREAM_ACTIONS = new Set([
  "get_live_streams",
  "get_live_categories",
  "get_vod_streams",
  "get_vod_categories",
  "get_series",
  "get_series_categories"
]);

function isCacheableIptvCatalog(target: URL) {
  const lowerPath = target.pathname.toLowerCase();
  if (lowerPath.endsWith("/player_api.php")) {
    const action = target.searchParams.get("action")?.toLowerCase() ?? "";
    return CACHEABLE_XTREAM_ACTIONS.has(action);
  }
  return isLikelyPlaylistTarget(target) && !lowerPath.endsWith(".m3u8");
}

function cacheIptvCatalog(headers: Headers, target: URL) {
  if (!isCacheableIptvCatalog(target)) return;
  headers.set("cache-control", "private, max-age=3600, stale-while-revalidate=86400");
  headers.set("netlify-cdn-cache-control", "public, durable, max-age=3600, stale-while-revalidate=86400");
  headers.set("netlify-vary", "query");
}

export async function POST(request: NextRequest) {
  if (!withinProxyBudget(request.headers.get("x-nf-client-connection-ip") ?? "local")) return NextResponse.json({ error: "Proxy request limit reached. Please wait." }, { status: 429, headers: { "retry-after": "60" } });
  const input = new URL(request.url);
  const raw = input.searchParams.get("url");
  if (!raw) return NextResponse.json({ error: "Missing url" }, { status: 400 });

  let target: URL;
  try {
    target = new URL(raw);
  } catch {
    return NextResponse.json({ error: "Invalid url" }, { status: 400 });
  }
  if (!["http:", "https:"].includes(target.protocol) || BLOCKED_HOSTS.has(target.hostname)) {
    return NextResponse.json({ error: "Blocked proxy target" }, { status: 400 });
  }
  if (!ALLOW_MEDIA_PROXY && isLikelyMediaRequest(target, request)) {
    return NextResponse.json(
      { error: "Media proxy disabled to protect hosting bandwidth. Use direct playback or an external player." },
      { status: 403 }
    );
  }

  const forwardedHeaders = decodeHeaders(input.searchParams.get("headers")) ?? {};
  const body = await request.text();
  if (body.length > 1024 * 1024) return NextResponse.json({ error: "Request too large" }, { status: 413 });
  const response = await fetchWithTimeout(target, {
    method: "POST",
    headers: { "content-type": "application/json", ...forwardedHeaders },
    body,
    cache: "no-store",
    redirect: "follow",
    signal: request.signal
  });

  const headers = new Headers();
  headers.set("content-type", response.headers.get("content-type") ?? "application/json");
  const retryAfter = response.headers.get("retry-after");
  if (retryAfter) headers.set("retry-after", retryAfter);
  headers.set("x-content-type-options", "nosniff");
  const contentPolicy = response.headers.get("content-security-policy");
  if (contentPolicy) headers.set("content-security-policy", contentPolicy);
  headers.set("cache-control", "no-store");
  headers.set("access-control-allow-origin", "*");
  return new NextResponse(response.body, { status: response.status, headers });
}

function decodeHeaders(raw: string | null) {
  if (!raw) return undefined;
  try {
    return JSON.parse(Buffer.from(raw, "base64").toString("utf8")) as Record<string, string>;
  } catch {
    return undefined;
  }
}

function shouldRewritePlaylist(target: URL, contentType: string) {
  const lowerType = contentType.toLowerCase();
  return (
    lowerType.includes("mpegurl") ||
    lowerType.includes("x-mpegurl") ||
    isLikelyPlaylistTarget(target)
  );
}

function isLikelyPlaylistTarget(target: URL) {
  const lowerPath = target.pathname.toLowerCase();
  const type = target.searchParams.get("type")?.toLowerCase();
  return (
    lowerPath.endsWith(".m3u") ||
    lowerPath.endsWith(".m3u8") ||
    lowerPath.endsWith("/get.php") ||
    type === "m3u" ||
    type === "m3u_plus"
  );
}

function isLikelyMediaRequest(target: URL, request: NextRequest) {
  if (request.headers.has("range")) return true;
  const lowerPath = target.pathname.toLowerCase();
  return /\.(m3u8|mpd|mp4|m4v|mov|mkv|webm|avi|ts|m2ts|m4s|mp3|aac|ac3|eac3|flac|wav)(?:$|[?#])/i.test(lowerPath);
}

function rewritePlaylist(text: string, baseUrl: URL, request: NextRequest) {
  const rewriteUrl = (raw: string) => {
    const trimmed = raw.trim();
    if (!trimmed || trimmed.startsWith("data:") || trimmed.startsWith("blob:")) return raw;
    let absolute: URL;
    try {
      absolute = new URL(trimmed, baseUrl);
    } catch {
      return raw;
    }
    if (!["http:", "https:"].includes(absolute.protocol)) return raw;
    const proxied = new URL("/api/proxy", request.url);
    proxied.searchParams.set("url", absolute.toString());
    return proxied.toString();
  };

  return text.split(/\r?\n/).map((line) => {
    const trimmed = line.trim();
    if (!trimmed) return line;
    if (!trimmed.startsWith("#")) return rewriteUrl(line);
    return line.replace(/URI="([^"]+)"/g, (_match, uri: string) => `URI="${rewriteUrl(uri)}"`);
  }).join("\n");
}

function rewritePlaylistToWorker(text: string, baseUrl: URL, headersParam: string | null) {
  // Hybrid hop for providers that block Cloudflare-originated fetches (e.g. CF
  // error 1003 on the playlist host): this Netlify function fetches the small
  // manifest, while heavy segment traffic is rewritten to the resolver worker.
  const resolverUrl = (process.env.NEXT_PUBLIC_ARVIO_RESOLVER_URL ?? "").replace(/\/+$/, "");
  if (!resolverUrl.startsWith("http")) return rewritePlaylistToAbsolute(text, baseUrl);
  const workerTarget = (raw: string) => {
    const trimmed = raw.trim();
    if (!trimmed || trimmed.startsWith("data:") || trimmed.startsWith("blob:")) return raw;
    let absolute: URL;
    try {
      absolute = new URL(trimmed, baseUrl);
    } catch {
      return raw;
    }
    if (!["http:", "https:"].includes(absolute.protocol)) return raw;
    if (/\.m3u8?(?:$|[?#])/i.test(absolute.pathname)) {
      // Child playlists may live on the same CF-blocked host as the master;
      // keep them on this route (root-relative resolves against the app origin).
      const params = new URLSearchParams();
      params.set("url", absolute.toString());
      if (headersParam) params.set("headers", headersParam);
      params.set("rewrite", "worker");
      return `/api/proxy?${params.toString()}`;
    }
    const proxied = new URL(`${resolverUrl}/media`);
    proxied.searchParams.set("url", absolute.toString());
    if (headersParam) proxied.searchParams.set("h", headersParam);
    return proxied.toString();
  };

  return text.split(/\r?\n/).map((line) => {
    const trimmed = line.trim();
    if (!trimmed) return line;
    if (!trimmed.startsWith("#")) return workerTarget(line);
    return line.replace(/URI="([^"]+)"/g, (_match, uri: string) => `URI="${workerTarget(uri)}"`);
  }).join("\n");
}

function rewritePlaylistToAbsolute(text: string, baseUrl: URL) {
  const absoluteUrl = (raw: string) => {
    const trimmed = raw.trim();
    if (!trimmed || trimmed.startsWith("data:") || trimmed.startsWith("blob:")) return raw;
    try {
      const absolute = new URL(trimmed, baseUrl);
      return ["http:", "https:"].includes(absolute.protocol) ? absolute.toString() : raw;
    } catch {
      return raw;
    }
  };

  return text.split(/\r?\n/).map((line) => {
    const trimmed = line.trim();
    if (!trimmed) return line;
    if (!trimmed.startsWith("#")) return absoluteUrl(line);
    return line.replace(/URI="([^"]+)"/g, (_match, uri: string) => `URI="${absoluteUrl(uri)}"`);
  }).join("\n");
}

async function fetchWithTimeout(target: URL, init: RequestInit) {
  try {
    return await safeProxyFetch(target, init, target.pathname.toLowerCase().endsWith(".m3u8") ? { maxBytes: 2 * 1024 * 1024, textOnly: true } : undefined);
  } catch (error) {
    return proxyFailure(error);
  }
}

function proxyFailure(error: unknown) {
  if (error instanceof Error && ["AbortError", "TimeoutError"].includes(error.name)) {
    return NextResponse.json({ error: "Proxy target timed out" }, { status: 504, headers: { "cache-control": "no-store" } });
  }
  return NextResponse.json({ error: "Proxy target unavailable or blocked" }, { status: 502, headers: { "cache-control": "no-store" } });
}

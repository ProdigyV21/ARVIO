import { lookup } from "node:dns/promises";
import { Agent, fetch as undiciFetch } from "undici";
import ipaddr from "ipaddr.js";

export function isPublicAddress(address: string) {
  try { return ipaddr.process(address.replace(/^\[|\]$/g, "")).range() === "unicast"; }
  catch { return false; }
}

const MAX_BYTES = 128 * 1024 * 1024;
const MAX_IMAGE_BYTES = 4 * 1024 * 1024;
const ALLOWED_HEADERS = new Set(["accept", "accept-language", "authorization", "content-type", "range", "user-agent", "referer", "origin", "x-emby-token", "x-emby-authorization", "x-plex-token"]);

export function allowsMediaProxy() {
  // A browser build flag must never turn the hosted metadata route into a CDN.
  return !process.env.NETLIFY && process.env.NODE_ENV !== "production" && process.env.ALLOW_NETLIFY_MEDIA_PROXY === "true";
}

export function isMediaProxyTarget(target: URL) {
  let path = target.pathname;
  try { path = decodeURIComponent(path); } catch { /* Keep the encoded path. */ }
  return /\.(mp4|m4v|mov|mkv|webm|avi|ts|m2ts|mts|m4s|mp3|aac|ac3|eac3|flac|wav|ogg|opus)(?:$|\/)/i.test(path);
}

function isBinary(chunk: Uint8Array) {
  return chunk.some((byte) => byte < 9 || (byte > 13 && byte < 32));
}

async function verifiedImageType(bytes: Buffer) {
  // Next.js already ships sharp. Fail closed if its optional native decoder is
  // unavailable; never fall back to forwarding unvalidated binary bytes.
  const { default: sharp } = await import("sharp");
  const metadata = await sharp(bytes, { limitInputPixels: 16_777_216, pages: 1 }).metadata();
  if (!metadata.width || !metadata.height || metadata.width * metadata.height > 16_777_216) throw new Error("Image dimensions exceed the limit");
  const types: Record<string, string> = { jpeg: "image/jpeg", png: "image/png", gif: "image/gif", webp: "image/webp", svg: "image/svg+xml" };
  const type = metadata.format === "heif" && metadata.compression === "av1" ? "image/avif" : types[metadata.format ?? ""];
  if (!type) throw new Error("Unsupported proxy image");
  return type;
}

export function safeProxyHeaders(headers: HeadersInit = {}) {
  const result = new Headers();
  new Headers(headers).forEach((value, key) => {
    if (ALLOWED_HEADERS.has(key) && value.length <= 8192) result.set(key, value);
  });
  return result;
}

/** Validate every hop and pin the validated DNS answer to prevent rebinding. */
export async function safeProxyFetch(target: URL, init: RequestInit, options: { maxBytes?: number; textOnly?: boolean } = {}): Promise<Response> {
  let current = target;
  let headers = safeProxyHeaders(init.headers);
  const signal = AbortSignal.any([AbortSignal.timeout(25_000), ...(init.signal ? [init.signal] : [])]);
  const allowMedia = !options.textOnly && allowsMediaProxy();
  const maxBytes = Math.min(MAX_BYTES, options.maxBytes ?? MAX_BYTES);
  for (let hop = 0; hop <= 4; hop++) {
    signal.throwIfAborted();
    if (!['https:', 'http:'].includes(current.protocol) || current.username || current.password) throw new Error("Blocked proxy target");
    if (!allowMedia && (headers.has("range") || isMediaProxyTarget(current))) throw new Error("Media proxy disabled");
    const hostname = current.hostname.replace(/^\[|\]$/g, "");
    const addresses = await lookup(hostname, { all: true, verbatim: true });
    signal.throwIfAborted();
    const privateAllowed = process.env.ALLOW_PRIVATE_PROXY === "true" && !process.env.NETLIFY;
    if (!addresses.length || (!privateAllowed && addresses.some((entry) => !isPublicAddress(entry.address)))) throw new Error("Blocked proxy target");
    const address = addresses[0];
    const agent = new Agent({ connect: { lookup: (_hostname, options, callback) => {
      if (options.all) callback(null, [address]);
      else callback(null, address.address, address.family);
    } } });
    let response;
    try {
      response = await undiciFetch(current, {
        method: init.method ?? "GET", headers: Object.fromEntries(headers),
        body: init.body as string | undefined, redirect: "manual", dispatcher: agent, signal
      });
    } catch (error) { await agent.destroy(); throw error; }
    if ([301, 302, 303, 307, 308].includes(response.status)) {
      const location = response.headers.get("location");
      await response.body?.cancel(); await agent.destroy();
      if (!location || hop === 4) throw new Error("Invalid proxy redirect");
      const next = new URL(location, current);
      if (current.protocol === 'https:' && next.protocol !== 'https:') throw new Error("Insecure proxy redirect");
      if (current.origin !== next.origin) {
        // A target may redirect, but it may not forward another server's credentials.
        headers = new Headers({ accept: headers.get("accept") ?? "*/*" });
      }
      if (response.status === 303 || ((response.status === 301 || response.status === 302) && init.method === 'POST')) init = { ...init, method: 'GET', body: undefined };
      current = next;
      continue;
    }
    const type = (response.headers.get("content-type") ?? "").split(";")[0].trim().toLowerCase();
    const imageResponse = !allowMedia && !options.textOnly && type.startsWith("image/");
    const limit = imageResponse ? Math.min(maxBytes, MAX_IMAGE_BYTES) : maxBytes;
    if ((!allowMedia && (/^(video\/|audio\/(?!.*mpegurl)|application\/(?:mp4|ogg|x-matroska))/i.test(type)
      || response.status === 206 || response.headers.has("content-range"))) || Number(response.headers.get("content-length")) > limit) {
      await response.body?.cancel(); await agent.destroy();
      throw new Error("Proxy response is not allowed or exceeds the size limit");
    }
    const reader = response.body?.getReader();
    if (!reader) { await agent.destroy(); return new Response(null, { status: response.status, headers: Object.fromEntries(response.headers) }); }
    let bytes = 0;
    let released = false;
    const release = async () => {
      if (released) return;
      released = true;
      signal.removeEventListener("abort", onAbort);
      await reader.cancel().catch(() => undefined);
      await agent.destroy();
    };
    const onAbort = () => { void release(); };
    signal.addEventListener("abort", onAbort, { once: true });
    const read = async () => {
      signal.throwIfAborted();
      const chunk = await reader.read();
      signal.throwIfAborted();
      if (!chunk.done) {
        bytes += chunk.value.byteLength;
        if (bytes > limit) throw new Error("Proxy response exceeds the size limit");
        if (!allowMedia && !imageResponse && isBinary(chunk.value)) throw new Error("Binary media is not allowed through the metadata proxy");
      }
      return chunk;
    };
    const resultHeaders = new Headers(Object.fromEntries(response.headers));
    resultHeaders.delete("content-encoding"); resultHeaders.delete("content-length");
    resultHeaders.set("x-arvio-final-url", current.toString());
    // Verify bounded images without paying to re-encode every logo/poster.
    if (imageResponse) {
      try {
        const chunks: Uint8Array[] = [];
        for (let chunk = await read(); !chunk.done; chunk = await read()) chunks.push(chunk.value);
        const image = Buffer.concat(chunks);
        let imageType: string;
        try { imageType = await verifiedImageType(image); }
        catch { throw new Error("Invalid proxy image or image decoder unavailable"); }
        signal.throwIfAborted();
        resultHeaders.set("content-type", imageType);
        resultHeaders.set("x-content-type-options", "nosniff");
        if (imageType === "image/svg+xml") resultHeaders.set("content-security-policy", "sandbox; default-src 'none'; style-src 'unsafe-inline'");
        return new Response(image, { status: response.status, headers: resultHeaders });
      } finally { await release(); }
    }
    let first: Awaited<ReturnType<typeof read>> | undefined;
    try { first = await read(); }
    catch (error) { await release(); throw error; }
    const body = new ReadableStream<Uint8Array>({
      async pull(controller) {
        try {
          signal.throwIfAborted();
          const chunk = first ?? await read();
          first = undefined;
          if (chunk.done) { controller.close(); await release(); return; }
          controller.enqueue(chunk.value);
        } catch (error) { controller.error(error); await release(); }
      },
      cancel: release
    });
    return new Response(body, { status: response.status, headers: resultHeaders });
  }
  throw new Error("Too many redirects");
}

const requests = new Map<string, { start: number; count: number }>();
export function withinProxyBudget(key: string, now = Date.now()) {
  const previous = requests.get(key);
  if (!previous || now - previous.start >= 60_000) {
    if (requests.size >= 4000) requests.delete(requests.keys().next().value!);
    requests.set(key, { start: now, count: 1 }); return true;
  }
  return ++previous.count <= 180;
}

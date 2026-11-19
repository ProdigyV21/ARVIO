// ARVIO app service worker (root scope). Two jobs, one worker — a scope can
// only have one, which is why this keeps its historical file name (the
// Telegram streaming code registers the same URL).
//
// 1. Telegram streaming proxy. The <video> element plays from same-origin URLs
//    like /tg-stream/<id>. Telegram files can only be fetched through the
//    authorized GramJS client that lives in the PAGE, so this worker forwards
//    every byte-range request to the page over a MessageChannel and turns the
//    page's reply into a 206 Partial Content response. This is the browser
//    equivalent of Android's local TelegramStreamingProxy.
//
// 2. Offline app shell.
//      - page HTML (same-origin navigations): network-first; the last good copy
//        is the fallback, and an inline offline screen when there is none;
//      - content-hashed /_next/static assets: cache-first (immutable);
//      - icons and the manifest: network-first with cache fallback.
//    NOTHING else is cached: no /api/*, no auth, no stream or proxy URLs, no
//    cross-origin requests, no range requests, no /version.json.
//
// Updates: the first install activates at once; a NEW version waits until the
// page's "Update ready" prompt applies it (postMessage SKIP_WAITING).

const PREFIX = "/tg-stream/";
const SHELL_CACHE = "arvio-shell-v1";
const STATIC_CACHE = "arvio-static-v1";
const STATIC_LIMIT = 400;
const SHELL_ASSETS = [
  "/manifest.webmanifest",
  "/arvio-logo.svg",
  "/arvio-wordmark.svg",
  "/arvio-icon-192.png",
  "/arvio-icon-512.png",
  "/favicon-32.png",
  "/apple-touch-icon-v3.png"
];

self.addEventListener("install", (event) => {
  // An update waits for the page's prompt; the very first install takes over.
  if (!self.registration.active) self.skipWaiting();
  event.waitUntil(precacheShell());
});

self.addEventListener("activate", (event) => {
  event.waitUntil((async () => {
    const keep = new Set([SHELL_CACHE, STATIC_CACHE]);
    for (const name of await caches.keys()) {
      if (name.startsWith("arvio-") && !keep.has(name)) await caches.delete(name);
    }
    await self.clients.claim();
  })());
});

self.addEventListener("message", (event) => {
  if (event.data && event.data.type === "SKIP_WAITING") self.skipWaiting();
});

self.addEventListener("fetch", (event) => {
  const request = event.request;
  const url = new URL(request.url);
  if (url.origin !== self.location.origin) return;
  if (url.pathname.startsWith(PREFIX)) {
    event.respondWith(handleStream(event));
    return;
  }
  const kind = cacheKind(request, url);
  if (kind === "page") event.respondWith(networkFirstPage(request, url));
  else if (kind === "static") event.respondWith(cacheFirst(request));
  else if (kind === "shell") event.respondWith(networkFirstAsset(request, url));
  // Anything else goes straight to the network, untouched.
});

// --- What may be cached -----------------------------------------------------

const NEVER = /^\/(api|auth|login\/callback|tg-stream|_next\/data|_next\/image|_next\/webpack-hmr)(\/|$)|^\/version\.json$/;
// Production chunk/CSS/media names carry a content hash; dev and HMR files
// do not, and must never be served from a cache.
// (chunks/123-0a1b2c3d4e5f6a7b.js, css/0a1b2c3d4e5f6a7b.css, media/0a1b2c3d-s.p.woff2)
const HASHED_STATIC = /^\/_next\/static\/(?!development\/|webpack\/)(?:[^?#]*\/)?(?:[^/]*[-.])?[0-9a-f]{8,}(?:[-.][^/]*)?\.(js|css|woff2?|ttf|otf|png|jpe?g|gif|svg|webp|avif)$/;
const BUILD_MANIFEST = /^\/_next\/static\/(?!development\/)[^/]+\/_(buildManifest|ssgManifest)\.js$/;

function cacheKind(request, url) {
  if (request.method !== "GET" || request.headers.has("range")) return null;
  if (NEVER.test(url.pathname) || url.pathname.includes("hot-update")) return null;
  if (request.mode === "navigate") return url.search.includes("_rsc=") ? null : "page";
  if (HASHED_STATIC.test(url.pathname) || BUILD_MANIFEST.test(url.pathname)) return "static";
  if (SHELL_ASSETS.includes(url.pathname)) return "shell";
  return null;
}

// Pages are stored by path only: /?view=search&title=tv:1 reuses the "/" shell.
function pageKey(url) {
  return new Request(url.origin + url.pathname);
}

async function precacheShell() {
  try {
    const shell = await caches.open(SHELL_CACHE);
    await Promise.all(SHELL_ASSETS.map((asset) => shell.add(asset).catch(() => undefined)));
    const response = await fetch("/", { cache: "no-store", credentials: "same-origin" });
    if (!isCacheableHtml(response)) return;
    const html = await response.clone().text();
    await shell.put(pageKey(new URL("/", self.location.origin)), response);
    // The first page load fetched its chunks before this worker controlled
    // it — fetch the ones the shell references so an offline reload works.
    const assets = new Set();
    for (const match of html.matchAll(/\/_next\/static\/[^"'\s)\\]+/g)) {
      const path = match[0];
      if (HASHED_STATIC.test(path) || BUILD_MANIFEST.test(path)) assets.add(path);
    }
    const statics = await caches.open(STATIC_CACHE);
    await Promise.all([...assets].map((path) => statics.add(path).catch(() => undefined)));
  } catch {
    // Offline during install — the shell is cached on the next online visit.
  }
}

function isCacheableHtml(response) {
  return Boolean(response && response.ok && response.type === "basic" &&
    (response.headers.get("content-type") || "").includes("text/html"));
}

async function networkFirstPage(request, url) {
  try {
    const response = await fetch(request);
    if (isCacheableHtml(response)) {
      const copy = response.clone();
      caches.open(SHELL_CACHE).then((cache) => cache.put(pageKey(url), copy)).catch(() => undefined);
    }
    return response;
  } catch {
    // Only the page's own last good copy: serving "/" for another route would
    // hydrate the wrong page.
    const cached = await caches.match(pageKey(url));
    return cached || offlineResponse();
  }
}

async function cacheFirst(request) {
  const cached = await caches.match(request);
  if (cached) return cached;
  const response = await fetch(request);
  if (response.ok && response.type === "basic") {
    const copy = response.clone();
    caches.open(STATIC_CACHE).then(async (cache) => {
      await cache.put(request, copy);
      const keys = await cache.keys();
      // Oldest first: keep the cache bounded across many deploys.
      for (let i = 0; i < keys.length - STATIC_LIMIT; i += 1) await cache.delete(keys[i]);
    }).catch(() => undefined);
  }
  return response;
}

async function networkFirstAsset(request, url) {
  try {
    const response = await fetch(request);
    if (response.ok && response.type === "basic") {
      const copy = response.clone();
      caches.open(SHELL_CACHE).then((cache) => cache.put(url.pathname, copy)).catch(() => undefined);
    }
    return response;
  } catch {
    const cached = await caches.match(url.pathname);
    if (cached) return cached;
    throw new Error("offline");
  }
}

function offlineResponse() {
  const html = `<!doctype html><html lang="en"><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover"><meta name="color-scheme" content="dark"><title>ARVIO — Offline</title>
<style>html,body{margin:0;height:100%;background:#000;color:#ededed;font:16px/1.5 system-ui,-apple-system,"Segoe UI",Roboto,sans-serif}main{min-height:100%;display:grid;place-items:center;padding:24px max(24px,env(safe-area-inset-right)) 24px max(24px,env(safe-area-inset-left));box-sizing:border-box;text-align:center}div{max-width:460px}h1{font-size:1.6rem;margin:16px 0 8px}p{color:#b5b5b5;margin:0 0 24px}button{font:inherit;font-weight:600;color:#000;background:#ededed;border:0;border-radius:999px;padding:12px 28px;min-height:48px;cursor:pointer}button:focus{outline:3px solid #fff;outline-offset:3px}</style></head>
<body><main><div><img src="/arvio-logo.svg" alt="" width="56" height="56"><h1>You're offline</h1><p>ARVIO needs a connection to browse and play. Downloads you saved on this device stay in the folder you chose and play in any video player.</p><button type="button" autofocus onclick="location.reload()">Try again</button></div></main></body></html>`;
  return new Response(html, { status: 503, headers: { "Content-Type": "text/html; charset=utf-8", "Cache-Control": "no-store" } });
}

// --- Telegram streaming ------------------------------------------------------

async function handleStream(event) {
  const url = new URL(event.request.url);
  const streamId = decodeURIComponent(url.pathname.slice(PREFIX.length));

  const range = parseRange(event.request.headers.get("Range"));

  const client =
    (await self.clients.get(event.clientId)) ||
    (await self.clients.matchAll({ type: "window" }))[0];
  if (!client) return new Response("No client", { status: 500 });

  let data;
  try {
    data = await requestRange(client, streamId, range.start, range.end);
  } catch (err) {
    return new Response(String(err && err.message ? err.message : err), { status: 502 });
  }
  if (!data || !data.ok) {
    return new Response(data && data.error ? data.error : "Not found", { status: 404 });
  }

  const { buffer, totalSize, mime, start, end } = data;
  const headers = new Headers({
    "Content-Type": mime || "video/mp4",
    "Accept-Ranges": "bytes",
    "Content-Length": String(end - start + 1),
    "Content-Range": `bytes ${start}-${end}/${totalSize}`,
    "Cache-Control": "no-store",
  });
  return new Response(buffer, { status: 206, headers });
}

function requestRange(client, streamId, start, end) {
  return new Promise((resolve, reject) => {
    const channel = new MessageChannel();
    const timeout = setTimeout(() => reject(new Error("Telegram byte range timed out")), 45000);
    channel.port1.onmessage = (e) => {
      clearTimeout(timeout);
      resolve(e.data);
    };
    client.postMessage({ type: "ARVIO_TG_RANGE", streamId, start, end }, [channel.port2]);
  });
}

function parseRange(header) {
  if (!header) return { start: 0, end: null };
  const m = /bytes=(\d*)-(\d*)/.exec(header);
  if (!m) return { start: 0, end: null };
  const start = m[1] ? Number(m[1]) : 0;
  const end = m[2] ? Number(m[2]) : null;
  return { start, end };
}

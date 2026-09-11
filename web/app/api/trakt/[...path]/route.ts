import { NextRequest, NextResponse } from "next/server";

function envValue(value: string | undefined, fallback = "") {
  return value && !value.startsWith("$") ? value : fallback;
}

async function handler(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  const { path } = await context.params;
  const netlifyBackendUrl = (
    process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL ??
    process.env.NETLIFY_BACKEND_URL ??
    "https://auth.arvio.tv/.netlify/functions"
  ).replace(/\/+$/, "");
  const appAnonKey = envValue(process.env.APP_ANON_KEY, envValue(process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? ""));
  const traktClientId = process.env.TRAKT_CLIENT_ID || process.env.NEXT_PUBLIC_TRAKT_CLIENT_ID || "";
  const traktSecret = process.env.TRAKT_CLIENT_SECRET ?? "";
  const input = new URL(request.url);
  const method = request.method;
  const body = method === "GET" || method === "HEAD" ? undefined : await request.text();
  const normalizedPath = path.join("/");

  let target: URL;
  let headers: HeadersInit;

  const usesNetlifyProxy = process.env.NEXT_PUBLIC_SELF_HOSTED !== "true" && netlifyBackendUrl.startsWith("https://") && appAnonKey.length > 40;

  if (usesNetlifyProxy) {
    target = new URL(`${netlifyBackendUrl}/trakt-proxy`);
    target.searchParams.set("path", `/${path.join("/")}`);
    target.searchParams.set("method", method);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    headers = {
      apikey: appAnonKey,
      Authorization: `Bearer ${appAnonKey}`
    };
    const userToken = request.headers.get("x-user-token");
    if (userToken) headers["x-user-token" as keyof HeadersInit] = userToken;
  } else if (traktClientId) {
    target = new URL(`https://api.trakt.tv/${path.join("/")}`);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    headers = {
      "content-type": "application/json",
      "trakt-api-version": "2",
      "trakt-api-key": traktClientId
    };
    const userToken = request.headers.get("x-user-token");
    if (userToken) headers.Authorization = `Bearer ${userToken}`;
  } else {
    return NextResponse.json({ error: "Trakt proxy is not configured" }, { status: 500 });
  }

  const parsedBody = body && ["oauth/device/token", "oauth/token", "oauth/device/code"].includes(normalizedPath) && !usesNetlifyProxy
    ? JSON.stringify({ ...JSON.parse(body), client_id: traktClientId, ...(traktSecret ? { client_secret: traktSecret } : {}) })
    : body;

  const response = await fetch(target, {
    method,
    headers,
    body: parsedBody,
    cache: "no-store",
    signal: AbortSignal.timeout(20_000)
  });

  const responseHeaders = new Headers();
  responseHeaders.set("content-type", response.headers.get("content-type") ?? "application/json");
  responseHeaders.set("cache-control", response.ok ? cacheControlForTrakt(method, normalizedPath, request) : "no-store");

  return new NextResponse(response.body, {
    status: response.status,
    headers: responseHeaders
  });
}

export const GET = handler;
export const POST = handler;
export const DELETE = handler;

function cacheControlForTrakt(method: string, path: string, request: NextRequest) {
  if (method !== "GET") return "no-store";
  if (path.startsWith("oauth/")) return "no-store";
  if (request.headers.get("x-user-token")) return "private, no-store";
  return "public, max-age=120, s-maxage=900, stale-while-revalidate=3600";
}

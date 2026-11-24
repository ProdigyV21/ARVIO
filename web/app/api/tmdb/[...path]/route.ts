import { NextRequest, NextResponse } from "next/server";

function envValue(value: string | undefined, fallback = "") {
  return value && !value.startsWith("$") ? value : fallback;
}

export async function GET(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  const { path } = await context.params;
  const netlifyBackendUrl = (
    process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL ??
    process.env.NETLIFY_BACKEND_URL ??
    "https://auth.arvio.tv/.netlify/functions"
  ).replace(/\/+$/, "");
  const appAnonKey = envValue(process.env.APP_ANON_KEY, envValue(process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? ""));
  const input = new URL(request.url);
  const calendarRequest = input.searchParams.get("arvio_calendar") === "1";
  input.searchParams.delete("arvio_calendar");
  const metadataTtl = calendarRequest ? 1800 : 86400;
  const customKey = (request.headers.get("x-tmdb-api-key") ?? "").trim();
  const tmdbKey = customKey || process.env.TMDB_API_KEY || "";
  input.searchParams.delete("api_key");

  let target: URL;
  const usesNetlifyProxy = process.env.NEXT_PUBLIC_SELF_HOSTED !== "true" && !customKey && netlifyBackendUrl.startsWith("https://") && appAnonKey.length > 40;
  if (usesNetlifyProxy) {
    target = new URL(`${netlifyBackendUrl}/tmdb-proxy`);
    target.searchParams.set("path", `/${path.join("/")}`);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    // cv busts the Next data cache — v3 drops entries poisoned by the proxy's
    // edge cache answering unrelated queries (fixed upstream: tmdb-proxy is no
    // longer edge-cached).
    target.searchParams.set("cv", calendarRequest ? "calendar-4" : "3");
  } else if (tmdbKey) {
    target = new URL(`https://api.themoviedb.org/3/${path.join("/")}`);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    target.searchParams.set("api_key", tmdbKey);
  } else {
    return NextResponse.json({ error: "TMDB proxy is not configured" }, { status: 500 });
  }

  let response: Response | null = null;
  try {
    response = await fetch(target, {
      headers: usesNetlifyProxy
        ? {
            apikey: appAnonKey,
            Authorization: `Bearer ${appAnonKey}`
          }
        : undefined,
      next: { revalidate: customKey ? 0 : metadataTtl },
      signal: AbortSignal.timeout(8000)
    });
  } catch {
    response = null;
  }

  if ((!response || !response.ok) && usesNetlifyProxy && tmdbKey) {
    const direct = new URL(`https://api.themoviedb.org/3/${path.join("/")}`);
    input.searchParams.forEach((value, key) => direct.searchParams.set(key, value));
    direct.searchParams.set("api_key", tmdbKey);
    try {
      response = await fetch(direct, { next: { revalidate: customKey ? 0 : metadataTtl }, signal: AbortSignal.timeout(10000) });
    } catch {
      response = null;
    }
  }

  if (!response) {
    return NextResponse.json({ error: "TMDB upstream unreachable" }, { status: 502 });
  }

  const headers = new Headers();
  headers.set("content-type", response.headers.get("content-type") ?? "application/json");

  if (customKey || !response.ok) {
    headers.set("cache-control", "private, no-store");
  } else if (calendarRequest) {
    // Release schedules change more often than posters/details. Keep the shared
    // cache, but never retain yesterday's schedule through a day-long data cache.
    headers.set("cache-control", "public, max-age=300, s-maxage=1800");
    headers.set("netlify-cdn-cache-control", "public, durable, max-age=1800");
    headers.set("cdn-cache-control", "public, max-age=1800");
    headers.set("netlify-vary", "query");
    headers.set("vary", "Accept-Encoding");
  } else {
    // Short browser cache; long CDN cache for shared system key requests.
    headers.set("cache-control", "public, max-age=300, s-maxage=21600, stale-while-revalidate=21600");
    headers.set("netlify-cdn-cache-control", "public, durable, max-age=21600, stale-while-revalidate=21600");
    headers.set("cdn-cache-control", "public, max-age=21600, stale-while-revalidate=21600");
    headers.set("netlify-vary", "query");
    headers.set("vary", "Accept-Encoding");
  }

  return new NextResponse(response.body, {
    status: response.status,
    headers
  });
}

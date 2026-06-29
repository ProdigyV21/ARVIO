import { NextRequest, NextResponse } from "next/server";

export async function GET(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  const { path } = await context.params;
  const netlifyBackendUrl = (process.env.NETLIFY_BACKEND_URL ?? "https://auth.arvio.tv/.netlify/functions").replace(/\/+$/, "");
  const anonKey = process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? "";
  const tmdbKey = process.env.TMDB_API_KEY ?? "";
  const input = new URL(request.url);

  let target: URL;
  const usesNetlifyProxy = netlifyBackendUrl.startsWith("https://") && anonKey.length > 40;
  if (usesNetlifyProxy) {
    target = new URL(`${netlifyBackendUrl}/tmdb-proxy`);
    target.searchParams.set("path", `/${path.join("/")}`);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
  } else if (tmdbKey) {
    target = new URL(`https://api.themoviedb.org/3/${path.join("/")}`);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    target.searchParams.set("api_key", tmdbKey);
  } else {
    return NextResponse.json({ error: "TMDB proxy is not configured" }, { status: 500 });
  }

  const response = await fetch(target, {
    headers: usesNetlifyProxy
      ? {
          apikey: anonKey,
          Authorization: `Bearer ${anonKey}`
        }
      : undefined,
    cache: "no-store"
  });

  return new NextResponse(response.body, {
    status: response.status,
    headers: {
      "content-type": response.headers.get("content-type") ?? "application/json",
      "cache-control": "no-store"
    }
  });
}

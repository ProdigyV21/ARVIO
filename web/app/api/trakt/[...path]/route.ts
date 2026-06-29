import { NextRequest, NextResponse } from "next/server";

async function handler(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  const { path } = await context.params;
  const netlifyBackendUrl = (process.env.NETLIFY_BACKEND_URL ?? "https://auth.arvio.tv/.netlify/functions").replace(/\/+$/, "");
  const anonKey = process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? "";
  const traktClientId = process.env.NEXT_PUBLIC_TRAKT_CLIENT_ID ?? "";
  const traktSecret = process.env.TRAKT_CLIENT_SECRET ?? "";
  const input = new URL(request.url);
  const method = request.method;
  const body = method === "GET" || method === "HEAD" ? undefined : await request.text();

  let target: URL;
  let headers: HeadersInit;

  const usesNetlifyProxy = netlifyBackendUrl.startsWith("https://") && anonKey.length > 40;

  if (usesNetlifyProxy) {
    target = new URL(`${netlifyBackendUrl}/trakt-proxy`);
    target.searchParams.set("path", `/${path.join("/")}`);
    target.searchParams.set("method", method);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    headers = {
      apikey: anonKey,
      Authorization: `Bearer ${anonKey}`,
      "Cache-Control": "no-store"
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

  const parsedBody = body && path.join("/") === "oauth/device/token" && traktSecret && !usesNetlifyProxy
    ? JSON.stringify({ ...JSON.parse(body), client_secret: traktSecret })
    : body;

  const response = await fetch(target, {
    method,
    headers,
    body: parsedBody,
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

export const GET = handler;
export const POST = handler;
export const DELETE = handler;

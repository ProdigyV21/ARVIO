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
  const appAnonKey = envValue(process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? "");
  const simklClientId = process.env.NEXT_PUBLIC_SIMKL_CLIENT_ID ?? process.env.SIMKL_CLIENT_ID ?? "";
  const simklSecret = process.env.SIMKL_CLIENT_SECRET ?? "";
  const input = new URL(request.url);
  const method = request.method;
  const body = method === "GET" || method === "HEAD" ? undefined : await request.text();
  const normalizedPath = path.join("/");

  let target: URL;
  let headers: HeadersInit;

  const usesNetlifyProxy = netlifyBackendUrl.startsWith("https://") && appAnonKey.length > 40;

  if (usesNetlifyProxy) {
    target = new URL(`${netlifyBackendUrl}/simkl-proxy`);
    target.searchParams.set("path", `/${normalizedPath}`);
    target.searchParams.set("method", method);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    headers = {
      apikey: appAnonKey,
      Authorization: `Bearer ${appAnonKey}`
    };
    const userToken = request.headers.get("x-user-token");
    if (userToken) headers["x-user-token" as keyof HeadersInit] = userToken;
  } else if (simklClientId) {
    target = new URL(`https://api.simkl.com/${normalizedPath}`);
    input.searchParams.forEach((value, key) => target.searchParams.set(key, value));
    headers = {
      "content-type": "application/json",
      "simkl-api-key": simklClientId
    };
    const userToken = request.headers.get("x-user-token");
    if (userToken) headers.Authorization = `Bearer ${userToken}`;
  } else {
    return NextResponse.json({ error: "Simkl proxy is not configured" }, { status: 500 });
  }

  const parsedBody = body && normalizedPath === "oauth/token" && simklSecret && !usesNetlifyProxy
    ? JSON.stringify({ ...JSON.parse(body), client_id: simklClientId, client_secret: simklSecret })
    : body;

  const response = await fetch(target, {
    method,
    headers,
    body: parsedBody,
    cache: "no-store"
  });

  const responseHeaders = new Headers();
  responseHeaders.set("content-type", response.headers.get("content-type") ?? "application/json");

  return new NextResponse(response.body, {
    status: response.status,
    headers: responseHeaders
  });
}

export const GET = handler;
export const POST = handler;
export const DELETE = handler;

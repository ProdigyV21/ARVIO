import { NextRequest, NextResponse } from "next/server";

function configuredClientId() {
  const clientId = (process.env.MDBLIST_CLIENT_ID || process.env.NEXT_PUBLIC_MDBLIST_CLIENT_ID)?.trim() ?? "";
  return clientId && !clientId.startsWith("your-") && !clientId.startsWith("$") && clientId.toLowerCase() !== "disabled"
    ? clientId : "";
}

function oauthResponse(response: Response) {
  const headers = new Headers({ "content-type": "application/json", "cache-control": "no-store" });
  const retryAfter = response.headers.get("retry-after");
  if (retryAfter) headers.set("retry-after", retryAfter);
  return new NextResponse(response.body, { status: response.status, headers });
}

// Proxies MDBList (https://api.mdblist.com) so browser calls avoid CORS and the
// user's credentials are attached server-side.
// - OAuth Bearer token can arrive via `authorization: Bearer <token>` or `x-mdblist-token`.
// - Legacy API key arrives via `x-mdblist-key`.
// Clean separation: OAuth uses Bearer ONLY (no ?apikey=). Legacy API key uses ?apikey= ONLY (no Bearer).
async function handler(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  const { path } = await context.params;
  const input = new URL(request.url);
  const method = request.method;
  const route = path.join("/");
  const body = method === "GET" || method === "HEAD" ? undefined : await request.text();

  if (route === "oauth/status") {
    if (method !== "GET") return NextResponse.json({ error: "Method not allowed" }, { status: 405 });
    return NextResponse.json({ oauthConfigured: Boolean(configuredClientId()) }, { headers: { "cache-control": "no-store" } });
  }

  if (route === "oauth/device-authorization" || route === "oauth/token") {
    if (method !== "POST") return NextResponse.json({ error: "Method not allowed" }, { status: 405 });
    const clientId = configuredClientId();
    if (!clientId) {
      return NextResponse.json({ error: "MDBList OAuth is not configured on this server" }, { status: 503 });
    }
    if (route === "oauth/device-authorization") {
      const response = await fetch("https://api.mdblist.com/oauth/device-authorization/", {
        method: "POST",
        headers: { "content-type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ client_id: clientId, scope: "write" }),
        cache: "no-store",
        signal: AbortSignal.timeout(15_000)
      });
      return oauthResponse(response);
    }
    let payload: Record<string, unknown>;
    try {
      const parsed = JSON.parse(body ?? "{}");
      if (!parsed || typeof parsed !== "object" || Array.isArray(parsed)) throw new Error();
      payload = parsed as Record<string, unknown>;
    } catch {
      return NextResponse.json({ error: "Invalid request" }, { status: 400 });
    }
    const deviceCode = payload.device_code;
    const refreshToken = payload.refresh_token;
    let params: URLSearchParams;
    if (typeof deviceCode === "string" && deviceCode.trim()) {
      if (deviceCode.length > 8192) return NextResponse.json({ error: "Invalid device code" }, { status: 400 });
      params = new URLSearchParams({
        grant_type: "urn:ietf:params:oauth:grant-type:device_code",
        client_id: clientId,
        device_code: deviceCode.trim()
      });
    } else if (payload.grant_type === "urn:ietf:params:oauth:grant-type:device_code") {
      return NextResponse.json({ error: "Missing device code" }, { status: 400 });
    } else if (typeof refreshToken === "string" && refreshToken.trim() && refreshToken.length <= 8192) {
      params = new URLSearchParams({ grant_type: "refresh_token", client_id: clientId, refresh_token: refreshToken });
    } else {
      return NextResponse.json({ error: "Missing refresh token" }, { status: 400 });
    }
    const response = await fetch("https://api.mdblist.com/oauth/token/", {
      method: "POST",
      headers: { "content-type": "application/x-www-form-urlencoded" },
      body: params,
      cache: "no-store",
      signal: AbortSignal.timeout(15_000)
    });
    return oauthResponse(response);
  }

  const authHeader = request.headers.get("authorization") ?? "";
  const tokenHeader = request.headers.get("x-mdblist-token") ?? "";
  const bearerToken = authHeader.toLowerCase().startsWith("bearer ")
    ? authHeader.slice(7).trim()
    : tokenHeader.trim();
  const apiKey = request.headers.get("x-mdblist-key")?.trim() ?? "";

  if (!bearerToken && !apiKey) {
    return NextResponse.json({ error: "Missing MDBList credentials" }, { status: 400 });
  }

  const trailingSlash = input.pathname.endsWith("/") ? "/" : "";
  const target = new URL(`https://api.mdblist.com/${path.join("/")}${trailingSlash}`);
  input.searchParams.forEach((value, key) => {
    if (key !== "apikey" && key !== "access_token") target.searchParams.set(key, value);
  });

  const upstreamHeaders = new Headers();
  if (bearerToken) {
    upstreamHeaders.set("authorization", `Bearer ${bearerToken}`);
  } else {
    target.searchParams.set("apikey", apiKey);
  }
  if (body) {
    upstreamHeaders.set("content-type", "application/json");
  }

  const response = await fetch(target, {
    method,
    headers: upstreamHeaders,
    body,
    cache: "no-store"
  });

  const responseHeaders = new Headers();
  responseHeaders.set("content-type", response.headers.get("content-type") ?? "application/json");
  responseHeaders.set("cache-control", "no-store");

  return new NextResponse(response.body, {
    status: response.status,
    headers: responseHeaders
  });
}

export const GET = handler;
export const POST = handler;
export const DELETE = handler;

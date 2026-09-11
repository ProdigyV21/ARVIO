import { NextRequest, NextResponse } from "next/server";

function envValue(value: string | undefined, fallback = "") {
  return value && !value.startsWith("$") ? value : fallback;
}

const SIMKL_REQUEST_RULES = [
  { path: /^\/oauth\/pin(?:\/[A-Za-z0-9-]+)?$/, methods: new Set(["GET"]) },
  { path: /^\/oauth\/token$/, methods: new Set(["POST"]) },
  { path: /^\/users\/settings$/, methods: new Set(["POST"]) },
  { path: /^\/scrobble\/(?:start|pause|stop)$/, methods: new Set(["POST"]) },
  { path: /^\/sync\/activities$/, methods: new Set(["GET"]) },
  { path: /^\/sync\/all-items(?:\/(?:movies|shows|anime|all)(?:\/(?:watching|plantowatch|hold|completed|dropped|all))?)?$/, methods: new Set(["GET"]) },
  { path: /^\/sync\/playback(?:\/(?:movies|episodes|shows|anime|all))?$/, methods: new Set(["GET"]) },
  { path: /^\/sync\/playback\/\d+$/, methods: new Set(["DELETE"]) },
  { path: /^\/sync\/(?:history|history\/remove|add-to-list)$/, methods: new Set(["POST"]) }
];

function isAllowedSimklRequest(path: string, method: string) {
  return SIMKL_REQUEST_RULES.some((rule) => rule.path.test(path) && rule.methods.has(method));
}

async function handler(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  const { path } = await context.params;
  const netlifyBackendUrl = (
    process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL ??
    process.env.NETLIFY_BACKEND_URL ??
    "https://auth.arvio.tv/.netlify/functions"
  ).replace(/\/+$/, "");
  const appAnonKey = envValue(process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? "");
  const simklClientId = process.env.NEXT_PUBLIC_SIMKL_CLIENT_ID || process.env.SIMKL_CLIENT_ID || "";
  const simklSecret = process.env.SIMKL_CLIENT_SECRET ?? "";
  const input = new URL(request.url);
  const method = request.method;
  const body = method === "GET" || method === "HEAD" ? undefined : await request.text();
  const normalizedPath = `/${path.join("/")}`;

  if (!isAllowedSimklRequest(normalizedPath, method)) {
    return NextResponse.json({ error: "Path or method not allowed" }, { status: 403 });
  }

  let target: URL;
  let headers: HeadersInit;

  const usesNetlifyProxy = process.env.NEXT_PUBLIC_SELF_HOSTED !== "true" && netlifyBackendUrl.startsWith("https://") && appAnonKey.length > 40;

  if (usesNetlifyProxy) {
    target = new URL(`${netlifyBackendUrl}/simkl-proxy`);
    target.searchParams.set("path", normalizedPath);
    target.searchParams.set("method", method);
    target.searchParams.set("client_id", simklClientId);
    target.searchParams.set("app-name", "arvio");
    target.searchParams.set("app-version", "1.9.996");
    input.searchParams.forEach((value, key) => {
      if (key !== "client_id" && key !== "client_secret" && key !== "app-name" && key !== "app-version") {
        target.searchParams.set(key, value);
      }
    });
    headers = {
      apikey: appAnonKey,
      Authorization: `Bearer ${appAnonKey}`,
      "user-agent": request.headers.get("user-agent") || "ARVIO/1.9.996 (Web)"
    };
    const userToken = request.headers.get("x-user-token");
    if (userToken) headers["x-user-token" as keyof HeadersInit] = userToken;
  } else if (simklClientId) {
    target = new URL(`https://api.simkl.com${normalizedPath}`);
    input.searchParams.forEach((value, key) => {
      if (key !== "client_id" && key !== "client_secret") target.searchParams.set(key, value);
    });
    target.searchParams.set("client_id", simklClientId);
    if (!target.searchParams.has("app-name") || target.searchParams.get("app-name") === "ARVIO") {
      target.searchParams.set("app-name", "arvio");
    }
    if (!target.searchParams.has("app-version")) {
      target.searchParams.set("app-version", "1.9.996");
    }
    headers = {
      "content-type": "application/json",
      "simkl-api-key": simklClientId,
      "user-agent": request.headers.get("user-agent") || "ARVIO/1.9.996 (Web)"
    };
    const userToken = request.headers.get("x-user-token");
    if (userToken) headers.Authorization = `Bearer ${userToken}`;
  } else {
    return NextResponse.json({ error: "Simkl proxy is not configured" }, { status: 500 });
  }

  const parsedBody = body && normalizedPath === "/oauth/token" && simklSecret && !usesNetlifyProxy
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
  const retryAfter = response.headers.get("retry-after");
  if (retryAfter) {
    responseHeaders.set("retry-after", retryAfter);
  }

  return new NextResponse(response.body, {
    status: response.status,
    headers: responseHeaders
  });
}

export const GET = handler;
export const POST = handler;
export const DELETE = handler;

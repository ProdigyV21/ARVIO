import { NextRequest, NextResponse } from "next/server";

const BLOCKED_HOSTS = new Set(["localhost", "127.0.0.1", "::1", "0.0.0.0"]);

export async function GET(request: NextRequest) {
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

  const forwardedHeaders = decodeHeaders(input.searchParams.get("headers"));
  const response = await fetch(target, {
    headers: forwardedHeaders,
    cache: "no-store",
    redirect: "follow"
  });

  const headers = new Headers();
  headers.set("content-type", response.headers.get("content-type") ?? "application/octet-stream");
  headers.set("cache-control", "no-store");
  headers.set("access-control-allow-origin", "*");

  return new NextResponse(response.body, { status: response.status, headers });
}

export async function POST(request: NextRequest) {
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

  const forwardedHeaders = decodeHeaders(input.searchParams.get("headers")) ?? {};
  const body = await request.text();
  const response = await fetch(target, {
    method: "POST",
    headers: { "content-type": "application/json", ...forwardedHeaders },
    body,
    cache: "no-store",
    redirect: "follow"
  });

  const headers = new Headers();
  headers.set("content-type", response.headers.get("content-type") ?? "application/json");
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

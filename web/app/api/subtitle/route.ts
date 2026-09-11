import { NextRequest, NextResponse } from "next/server";
import { safeProxyFetch, withinProxyBudget } from "@/lib/server/safeProxy";

export async function GET(request: NextRequest) {
  if (!withinProxyBudget(request.headers.get("x-nf-client-connection-ip") ?? "local")) {
    return NextResponse.json({ error: "Subtitle request limit reached. Please wait." }, { status: 429, headers: { "retry-after": "60", "cache-control": "no-store" } });
  }
  const raw = new URL(request.url).searchParams.get("url");
  if (!raw) return NextResponse.json({ error: "Missing url" }, { status: 400 });

  let target: URL;
  try { target = new URL(raw); }
  catch { return NextResponse.json({ error: "Invalid url" }, { status: 400 }); }
  if (!["https:", "http:"].includes(target.protocol) || target.username || target.password) {
    return NextResponse.json({ error: "Blocked subtitle target" }, { status: 400 });
  }

  try {
    const response = await safeProxyFetch(target, { signal: request.signal }, { maxBytes: 2 * 1024 * 1024, textOnly: true });
    if (!response.ok) {
      await response.body?.cancel();
      return NextResponse.json({ error: "Subtitle provider unavailable" }, { status: response.status, headers: { "cache-control": "no-store" } });
    }
    const text = await response.text();
    const webvtt = text.trimStart().startsWith("WEBVTT") ? text : srtToVtt(text);

    return new NextResponse(webvtt, {
      headers: {
        "content-type": "text/vtt; charset=utf-8",
        "cache-control": "public, max-age=86400, s-maxage=86400, stale-while-revalidate=604800",
        "netlify-cdn-cache-control": "public, max-age=86400, stale-while-revalidate=604800",
        "netlify-vary": "query",
        "access-control-allow-origin": "*"
      }
    });
  } catch (error) {
    const timeout = error instanceof Error && ["AbortError", "TimeoutError"].includes(error.name);
    return NextResponse.json({ error: "Subtitle target unavailable or blocked" }, { status: timeout ? 504 : 502, headers: { "cache-control": "no-store" } });
  }
}

function srtToVtt(input: string) {
  return `WEBVTT\n\n${input.replace(/\r/g, "").replace(/(\d\d:\d\d:\d\d),(\d\d\d)/g, "$1.$2")}`;
}

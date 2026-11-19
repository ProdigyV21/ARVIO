import { NextRequest, NextResponse } from "next/server";
import { safeProxyFetch, withinProxyBudget } from "@/lib/server/safeProxy";
import { charsetFromContentType, decodeSubtitleBytes, normalizeSubtitleText } from "@/lib/playerSubtitles";

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
    // Bytes, not response.text(): legacy subtitle files are windows-125x /
    // ISO-8859-x without a charset, and a UTF-8 decode turns their accents
    // into replacement characters. ASS/SSA files are converted to WebVTT.
    const bytes = new Uint8Array(await response.arrayBuffer());
    const { text } = decodeSubtitleBytes(bytes, charsetFromContentType(response.headers.get("content-type")));
    let webvtt: string;
    try { webvtt = normalizeSubtitleText(text); }
    catch { return NextResponse.json({ error: "Not a subtitle file" }, { status: 422, headers: { "cache-control": "no-store" } }); }

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

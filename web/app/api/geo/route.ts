import { NextRequest, NextResponse } from "next/server";

export const dynamic = "force-dynamic";

// The visitor's country for regional pricing: Cloudflare's lookup (web.arvio.tv
// is proxied), else Netlify's. Only the two-letter code is returned.
const ALLOWED_ORIGINS = new Set(["https://arvio.tv", "https://www.arvio.tv"]);

function countryFrom(request: NextRequest): string | null {
  const cloudflare = request.headers.get("cf-ipcountry")?.trim().toUpperCase();
  if (cloudflare && /^[A-Z]{2}$/.test(cloudflare) && cloudflare !== "XX" && cloudflare !== "T1") return cloudflare;
  const netlify = request.headers.get("x-country")?.trim().toUpperCase();
  if (netlify && /^[A-Z]{2}$/.test(netlify)) return netlify;
  const geo = request.headers.get("x-nf-geo");
  if (geo) {
    try {
      const code = String(JSON.parse(Buffer.from(geo, "base64").toString("utf8"))?.country?.code ?? "").toUpperCase();
      if (/^[A-Z]{2}$/.test(code)) return code;
    } catch { /* ignore */ }
  }
  return null;
}

function headers(request: NextRequest): Record<string, string> {
  const origin = request.headers.get("origin") ?? "";
  return {
    "cache-control": "private, no-store",
    vary: "Origin",
    ...(ALLOWED_ORIGINS.has(origin) ? { "access-control-allow-origin": origin } : {})
  };
}

export function GET(request: NextRequest) {
  return NextResponse.json({ country: countryFrom(request) }, { headers: headers(request) });
}

export function OPTIONS(request: NextRequest) {
  return new NextResponse(null, { status: 204, headers: { ...headers(request), "access-control-allow-methods": "GET" } });
}

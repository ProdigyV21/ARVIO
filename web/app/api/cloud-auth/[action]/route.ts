import { NextRequest, NextResponse } from "next/server";

const allowed = new Set(["auth-refresh", "auth-login", "cloud-auth-email"]);

export async function POST(request: NextRequest, context: { params: Promise<{ action: string }> }) {
  const { action } = await context.params;
  if (!allowed.has(action)) return NextResponse.json({ error: "Not found" }, { status: 404 });
  if (process.env.NEXT_PUBLIC_SELF_HOSTED === "true") {
    return NextResponse.json({ error: "This self-hosted installation uses local profiles, not ARVIO Cloud." }, { status: 503 });
  }
  const key = process.env.APP_ANON_KEY || process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY;
  if (!key) return NextResponse.json({ error: "Cloud authentication is not configured on this server" }, { status: 503 });
  const base = (process.env.NETLIFY_BACKEND_URL || process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL || "https://auth.arvio.tv/.netlify/functions").replace(/\/+$/, "");
  const body = await request.text();
  if (body.length > 16_384) return NextResponse.json({ error: "Request too large" }, { status: 413 });
  try {
    const response = await fetch(`${base}/${action}`, {
      method: "POST", headers: { "content-type": "application/json", apikey: key, Authorization: `Bearer ${key}` },
      body, cache: "no-store", signal: AbortSignal.timeout(15_000)
    });
    return new NextResponse(response.body, { status: response.status, headers: { "content-type": "application/json", "cache-control": "no-store" } });
  } catch {
    return NextResponse.json({ error: "Cloud authentication temporarily unavailable" }, { status: 502 });
  }
}

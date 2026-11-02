import { NextRequest, NextResponse } from "next/server";
import { wetrakrRequestContext, wetrakrSessions, wetrakrSessionFailure } from "@/lib/server/weTrakrSessionHttp";
import { WeTrakrSessionError } from "@/lib/server/weTrakrSession";

export const runtime = "nodejs";
const rules = [
  { path: /^\/sync\/tracking\/(?:planning|watched|watching|playing|hold|dropped)\/(?:movies|shows|episodes|all)$/, method: "GET" },
  { path: /^\/sync\/tracking(?:\/remove(?:\/all)?)?$/, method: "POST" },
  { path: /^\/scrobble\/(?:start|pause|stop)$/, method: "POST" },
  { path: /^\/scrobble\/playing$/, method: "DELETE" }
];
async function handler(request: NextRequest, context: { params: Promise<{ path: string[] }> }) {
  try {
    const path = `/${(await context.params).path.join("/")}`;
    if (!rules.some(rule => rule.path.test(path) && rule.method === request.method)) throw new WeTrakrSessionError("path_not_allowed", 403);
    const session = wetrakrRequestContext(request);
    if (!session.id) throw new WeTrakrSessionError("session_required", 401);
    if (Number(request.headers.get("content-length")) > 65536) throw new WeTrakrSessionError("request_too_large", 413);
    const body = request.method === "GET" ? undefined : await request.text();
    if (body && body.length > 65536) throw new WeTrakrSessionError("request_too_large", 413);
    const response = await wetrakrSessions().request(session.id, session.profile, request.headers.get("x-wetrakr-connection") || "", path, request.method, new URL(request.url).searchParams, body);
    const headers = new Headers({ "Content-Type": response.headers.get("content-type") || "application/json", "Cache-Control": "no-store", Pragma: "no-cache" });
    for (const name of ["retry-after", "x-pagination-page-count", "x-pagination-item-count", "ratelimit-reset", "ratelimit-remaining"]) {
      const value = response.headers.get(name); if (value) headers.set(name, value);
    }
    return new NextResponse(response.body, { status: response.status, headers });
  } catch (error) { return wetrakrSessionFailure(error); }
}
export const GET = handler;
export const POST = handler;
export const DELETE = handler;

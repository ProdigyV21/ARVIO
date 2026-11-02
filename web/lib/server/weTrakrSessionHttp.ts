import { NextRequest, NextResponse } from "next/server";
import { WeTrakrSessionError, WeTrakrSessions, wetrakrCookieName, validWeTrakrSessionId } from "./weTrakrSession";
import { wetrakrSessionStore } from "./weTrakrSessionStore";

export function wetrakrRequestContext(request: NextRequest) {
  const url = new URL(request.url);
  // Next can use an internal hostname while a reverse proxy serves the public origin.
  const host = (request.headers.get("x-forwarded-host") || request.headers.get("host") || url.host).split(",")[0].trim();
  const protocol = (request.headers.get("x-forwarded-proto") || url.protocol.slice(0, -1)).split(",")[0].trim();
  if (!["http", "https"].includes(protocol)) throw new WeTrakrSessionError("cross_origin_request", 403);
  const origin = new URL(`${protocol}://${host}`).origin;
  if ((request.headers.get("origin") && request.headers.get("origin") !== origin) ||
      request.headers.get("sec-fetch-site") === "cross-site") throw new WeTrakrSessionError("cross_origin_request", 403);
  const profile = request.headers.get("x-wetrakr-profile");
  if (!profile || profile.length > 128 || /[\x00-\x1f]/.test(profile)) throw new WeTrakrSessionError("profile_required");
  const secure = origin.startsWith("https://");
  const cookie = wetrakrCookieName(profile, secure);
  const id = request.cookies.get(cookie)?.value;
  return { profile, secure, cookie, id: validWeTrakrSessionId(id) ? id : undefined };
}

export function wetrakrSessions() {
  const clientId = process.env.WETRAKR_CLIENT_ID || process.env.NEXT_PUBLIC_WETRAKR_CLIENT_ID ||
    (process.env.NEXT_PUBLIC_SELF_HOSTED === "true" ? "" : "a96545eae15256f3302c72377acd3ade");
  return new WeTrakrSessions(wetrakrSessionStore(), clientId);
}

export function wetrakrSessionFailure(error: unknown) {
  const known = error instanceof WeTrakrSessionError;
  return NextResponse.json({ error: known ? error.reason : "session_unavailable", message: known ? error.reason : "WETRAKR connection is temporarily unavailable. Please retry." }, {
    status: known ? error.status : 503,
    headers: { "Cache-Control": "no-store", Pragma: "no-cache", ...(known && error.retryAfter ? { "Retry-After": error.retryAfter } : {}) }
  });
}

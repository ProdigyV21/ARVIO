import { NextRequest, NextResponse } from "next/server";
import { WeTrakrSessionError, newWeTrakrSessionId } from "@/lib/server/weTrakrSession";
import { wetrakrRequestContext, wetrakrSessions, wetrakrSessionFailure } from "@/lib/server/weTrakrSessionHttp";

export const runtime = "nodejs";
export const dynamic = "force-dynamic";

async function handler(request: NextRequest) {
  try {
    const context = wetrakrRequestContext(request);
    const services = wetrakrSessions();
    let id = context.id;
    let result: unknown;
    if (request.method === "GET") {
      result = { token: id ? await services.restore(id, context.profile) : null };
    } else {
      if (Number(request.headers.get("content-length")) > 8192) throw new WeTrakrSessionError("request_too_large", 413);
      const raw = await request.text();
      if (raw.length > 8192) throw new WeTrakrSessionError("request_too_large", 413);
      let input: Record<string, unknown>;
      try { input = JSON.parse(raw); if (!input || typeof input !== "object" || Array.isArray(input)) throw new Error(); }
      catch { throw new WeTrakrSessionError("invalid_request"); }
      if (input.action === "start") id ??= newWeTrakrSessionId();
      if (!id && input.action !== "disconnect") throw new WeTrakrSessionError("session_required", 401);
      switch (input.action) {
        case "start": result = await services.start(id!, context.profile); break;
        case "poll": result = { token: await services.poll(id!, context.profile, String(input.user_code ?? "")) }; break;
        case "disconnect":
          result = id ? await services.disconnect(id, context.profile, request.headers.get("x-wetrakr-connection") || undefined) : { disconnected: true, revoked: true };
          break;
        default: throw new WeTrakrSessionError("invalid_action", 400);
      }
    }
    const response = NextResponse.json(result, { headers: { "Cache-Control": "no-store", Pragma: "no-cache" } });
    if (id) response.cookies.set(context.cookie, id, { httpOnly: true, secure: context.secure, sameSite: "strict", path: "/", maxAge: 180 * 24 * 60 * 60 });
    return response;
  } catch (error) { return wetrakrSessionFailure(error); }
}

export const GET = handler;
export const POST = handler;

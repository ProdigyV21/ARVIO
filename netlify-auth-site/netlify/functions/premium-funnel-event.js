const { json, options, parseBody, resolveIdentity } = require("./_backend");
const { CLIENT_PREMIUM_EVENTS, recordPremiumEvent } = require("./_premium-funnel");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });

  try {
    const identity = await resolveIdentity(event);
    const body = parseBody(event);
    const eventName = String(body.event_name || "").trim();
    if (!CLIENT_PREMIUM_EVENTS.has(eventName)) {
      return json(400, { error: "unsupported_event" });
    }
    await recordPremiumEvent(event, {
      email: identity.email,
      accountId: identity.supabaseUserId,
      eventName,
      metadata: body.metadata
    });
    return json(200, { ok: true });
  } catch (error) {
    const status = Number(error?.statusCode || 500);
    if (status >= 500) console.error("premium-funnel-event failed", error);
    return json(status, { error: status === 401 ? "unauthorized" : "premium_event_failed" });
  }
};

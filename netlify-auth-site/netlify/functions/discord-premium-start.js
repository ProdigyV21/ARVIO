const { json, normalizeEmail, sha256, resolveIdentity } = require("./_backend");
const {
  SITE, discordBadgeConfigured, eligibleForDiscordBadge, createState, badgeCookie, authorizeUrl
} = require("./_discord-premium");

exports.handler = async (event) => {
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });
  if (event.headers?.origin && event.headers.origin !== SITE) {
    return json(400, { error: "invalid_origin" });
  }
  if (!discordBadgeConfigured()) return json(503, { error: "discord_badge_unavailable" });

  let identity;
  try {
    identity = await resolveIdentity(event);
  } catch {
    return json(401, { error: "unauthorized" });
  }

  const email = normalizeEmail(identity.email);
  const accountId = String(identity.supabaseUserId || "");
  if (!email || !accountId) return json(401, { error: "unauthorized" });
  try {
    const emailHash = sha256(email);
    if ((await eligibleForDiscordBadge(event, emailHash, accountId)) !== true) {
      return json(403, { error: "premium_inactive" });
    }
    const state = createState(emailHash, accountId);
    const response = json(200, { authorizeUrl: authorizeUrl(state) });
    return {
      ...response,
      headers: { ...response.headers, "Cache-Control": "no-store", "Set-Cookie": badgeCookie(state) }
    };
  } catch {
    console.error("Discord Premium badge start failed");
    return json(503, { error: "discord_badge_unavailable" });
  }
};

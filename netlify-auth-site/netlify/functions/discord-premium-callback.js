const {
  SITE, discordBadgeConfigured, eligibleForDiscordBadge, readState, badgeCookie, discordIdentity, setDiscordBadge
} = require("./_discord-premium");
const { claimDiscordBadge } = require("./_discord-premium-store");

function finish(status) {
  return {
    statusCode: 303,
    headers: {
      Location: SITE + "/?discord_badge=" + status,
      "Cache-Control": "no-store",
      "Referrer-Policy": "no-referrer",
      "Set-Cookie": badgeCookie(null)
    },
    body: ""
  };
}

exports.handler = async (event) => {
  if (event.httpMethod !== "GET") return { statusCode: 405, body: "" };
  if (!discordBadgeConfigured()) return finish("error");

  const { code, state } = event.queryStringParameters || {};
  const bound = readState(state, event.headers?.cookie);
  if (!bound || typeof code !== "string" || !code || code.length > 512) return finish("error");

  try {
    const { emailHash, accountId } = bound;
    if ((await eligibleForDiscordBadge(event, emailHash, accountId)) !== true) return finish("inactive");
    const discordId = await discordIdentity(code);
    const result = await claimDiscordBadge({
      event, emailHash, accountId, discordId, eligibleForDiscordBadge, setDiscordBadge
    });
    return finish(result);
  } catch (error) {
    console.error("Discord Premium badge callback failed", { status: error?.status || null });
    return finish("error");
  }
};

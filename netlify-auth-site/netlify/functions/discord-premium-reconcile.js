const { json } = require("./_backend");
const { reconcileDiscordBadges } = require("./_discord-premium-store");
const { discordBadgeConfigured, eligibleForDiscordBadge, setDiscordBadge } = require("./_discord-premium");

exports.handler = async (event) => {
  if (!discordBadgeConfigured()) return json(200, { skipped: "not_configured" });
  try {
    const totals = await reconcileDiscordBadges({ event, eligibleForDiscordBadge, setDiscordBadge });
    if (totals.retry) console.warn("Discord Premium role sync has pending retries", { count: totals.retry });
    return json(200, totals);
  } catch {
    console.error("Discord Premium role sync could not run");
    return json(500, { error: "discord_role_sync_failed" });
  }
};

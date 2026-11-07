const { getPool } = require("./_backend");

const CHECK_AFTER = {
  linked: "6 hours",
  join: "1 hour",
  inactive: "12 hours",
  retry: "15 minutes"
};

async function withLockedLink(pool, accountId, dueOnly, update) {
  const client = await pool.connect();
  try {
    await client.query("BEGIN");
    await client.query("SET LOCAL lock_timeout = '5s'");
    const dueClause = dueOnly ? "AND next_check_at <= now() FOR UPDATE SKIP LOCKED" : "FOR UPDATE";
    const { rows: [link] } = await client.query(
      `SELECT account_id, email_hash, discord_id FROM public.discord_premium_links
        WHERE account_id = $1 ${dueClause}`,
      [accountId]
    );
    const result = link ? await update(client, link) : null;
    await client.query("COMMIT");
    return result;
  } catch (error) {
    await client.query("ROLLBACK").catch(() => {});
    throw error;
  } finally {
    client.release();
  }
}

async function scheduleNextCheck(client, accountId, status) {
  await client.query(
    `UPDATE public.discord_premium_links
        SET next_check_at = now() + $2::interval
      WHERE account_id = $1`,
    [accountId, CHECK_AFTER[status]]
  );
}

async function synchronizeLink(client, link, { event, eligibleForDiscordBadge, setDiscordBadge }) {
  const eligibility = await eligibleForDiscordBadge(event, link.email_hash, link.account_id);
  const active = eligibility === true;
  const discordStatus = await setDiscordBadge(link.discord_id, active);
  if (eligibility === "deleted") {
    // Retain the mapping for retries until Discord has accepted the removal.
    await client.query("DELETE FROM public.discord_premium_links WHERE account_id = $1", [link.account_id]);
    return "inactive";
  }
  const status = active ? discordStatus : "inactive";
  await scheduleNextCheck(client, link.account_id, status);
  return status;
}

async function claimDiscordBadge(options) {
  const {
    event, accountId, emailHash, discordId, eligibleForDiscordBadge, pool = getPool()
  } = options;
  if (await eligibleForDiscordBadge(event, emailHash, accountId) !== true) return "inactive";

  // Commit ownership before contacting Discord. If the response is lost, the
  // scheduled worker still knows which role to verify or remove.
  await pool.query(
    `INSERT INTO public.discord_premium_links (account_id, email_hash, discord_id)
      VALUES ($1, $2, $3) ON CONFLICT DO NOTHING`,
    [accountId, emailHash, discordId]
  );
  return await withLockedLink(pool, accountId, false, async (client, link) => {
    if (link.email_hash !== emailHash || link.discord_id !== discordId) return "conflict";
    return synchronizeLink(client, link, options);
  }) || "conflict";
}

async function reconcileDiscordBadges(options) {
  const { pool = getPool(), deadline = Date.now() + 18_000 } = options;
  const { rows } = await pool.query(
    `SELECT account_id FROM public.discord_premium_links
      WHERE next_check_at <= now()
      ORDER BY next_check_at, account_id LIMIT 60`
  );
  const totals = { linked: 0, join: 0, inactive: 0, retry: 0 };
  for (const { account_id: accountId } of rows) {
    if (Date.now() >= deadline) break;
    const status = await withLockedLink(pool, accountId, true, async (client, link) => {
      try {
        return await synchronizeLink(client, link, options);
      } catch {
        // Leave the existing Discord role alone if eligibility could not be
        // checked. Retry temporary storage, network and Discord errors.
        await scheduleNextCheck(client, accountId, "retry");
        return "retry";
      }
    });
    if (status) totals[status]++;
  }
  return totals;
}

module.exports = { claimDiscordBadge, reconcileDiscordBadges };

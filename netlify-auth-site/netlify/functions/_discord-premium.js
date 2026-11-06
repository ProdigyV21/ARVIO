const crypto = require("node:crypto");
const { connectLambda, getStore } = require("@netlify/blobs");
const { normalizeEmail, sha256 } = require("./_backend");
const { entitlementsStore, readEntitlement, evaluateEntitlement } = require("./_entitlements");

const SITE = "https://auth.arvio.tv";
const CALLBACK = SITE + "/.netlify/functions/discord-premium-callback";
const COOKIE = "__Host-arvio_discord_badge";
const TTL_SECONDS = 600;
const snowflake = (id) => typeof id === "string" && /^\d{17,20}$/.test(id);

function discordBadgeConfigured() {
  return process.env.DISCORD_PREMIUM_ENABLED === "true" &&
    [process.env.DISCORD_PREMIUM_CLIENT_ID, process.env.DISCORD_PREMIUM_GUILD_ID,
      process.env.DISCORD_PREMIUM_ROLE_ID].every(snowflake) &&
    Boolean(process.env.DISCORD_PREMIUM_CLIENT_SECRET && process.env.DISCORD_PREMIUM_BOT_TOKEN);
}

function stateKey() {
  return crypto.createHmac("sha256", process.env.DISCORD_PREMIUM_CLIENT_SECRET)
    .update("ARVIO Discord Premium OAuth state v1").digest();
}

function createState(emailHash, accountId, now = Date.now()) {
  const nonce = crypto.randomBytes(12);
  const cipher = crypto.createCipheriv("aes-256-gcm", stateKey(), nonce);
  const encrypted = Buffer.concat([
    cipher.update(JSON.stringify({ emailHash, accountId, expires: now + TTL_SECONDS * 1000 })),
    cipher.final()
  ]);
  return Buffer.concat([nonce, cipher.getAuthTag(), encrypted]).toString("base64url");
}

function readState(state, cookieHeader, now = Date.now()) {
  if (typeof state !== "string" || !/^[\w-]{40,700}$/.test(state)) return null;
  const expected = cookieHeader?.split(/;\s*/).find((cookie) => cookie.startsWith(COOKIE + "="));
  if (expected !== COOKIE + "=" + sha256(state)) return null;
  try {
    const packed = Buffer.from(state, "base64url");
    const decipher = crypto.createDecipheriv("aes-256-gcm", stateKey(), packed.subarray(0, 12));
    decipher.setAuthTag(packed.subarray(12, 28));
    const decoded = JSON.parse(Buffer.concat([decipher.update(packed.subarray(28)), decipher.final()]));
    if (decoded.expires <= now || !Number.isFinite(decoded.expires) ||
        !/^[a-f0-9]{64}$/.test(decoded.emailHash) ||
        typeof decoded.accountId !== "string" || !decoded.accountId) return null;
    return decoded;
  } catch {
    return null;
  }
}

function badgeCookie(state) {
  return COOKIE + "=" + (state ? sha256(state) : "") + "; Path=/; Secure; HttpOnly; SameSite=Lax; Max-Age=" +
    (state ? TTL_SECONDS : 0);
}

async function eligibleForDiscordBadge(event, emailHash, accountId) {
  connectLambda(event);
  const lookup = { type: "json", consistency: "strong" };
  const account = await getStore("arvio-auth").get("accounts/email/" + emailHash + ".json", lookup);
  if (!account || String(account.accountId) !== accountId) return "deleted";
  const revoked = await getStore("account-deletion-jobs").get("revoked/account/" + sha256(accountId) + ".json", lookup);
  if (revoked) return "deleted";

  const store = entitlementsStore(event);
  const record = await readEntitlement(store, emailHash);
  if (record) {
    const sourceEmail = normalizeEmail(record.linkedFrom);
    const sourceHash = sourceEmail ? sha256(sourceEmail) : emailHash;
    const billingOwner = await store.get("link-owner/" + sourceHash, lookup);
    if (!billingOwner || billingOwner.accountHash === emailHash || (!sourceEmail && record.source !== "kofi")) {
      // A linked Ko-fi record is a snapshot. Check the live payment, including cancellations.
      const current = sourceHash !== emailHash && record.source === "kofi"
        ? await readEntitlement(store, sourceHash)
        : record;
      if (evaluateEntitlement(current).entitled) return true;
    }
  }

  if (process.env.PARTNER_ENABLED !== "true") return false;
  const { getPool } = require("./_partner-database");
  const { createService } = require("./_partner-service");
  // A storage failure must propagate so reconciliation retries instead of
  // revoking the role during an outage.
  const managed = await createService({ pool: getPool() }).entitlement({ email: account.email, supabaseUserId: accountId });
  return managed?.entitled === true;
}

function authorizeUrl(state) {
  const url = new URL("https://discord.com/oauth2/authorize");
  url.search = new URLSearchParams({
    client_id: process.env.DISCORD_PREMIUM_CLIENT_ID,
    redirect_uri: CALLBACK,
    response_type: "code",
    scope: "identify",
    state
  });
  return url.href;
}

async function discordIdentity(code, request = fetch) {
  const tokenResponse = await request("https://discord.com/api/v10/oauth2/token", {
    method: "POST",
    body: new URLSearchParams({
      client_id: process.env.DISCORD_PREMIUM_CLIENT_ID,
      client_secret: process.env.DISCORD_PREMIUM_CLIENT_SECRET,
      grant_type: "authorization_code",
      redirect_uri: CALLBACK,
      code
    }),
    signal: AbortSignal.timeout(4500)
  });
  if (!tokenResponse.ok) throw new Error("discord_token_exchange_failed");
  const token = await tokenResponse.json();
  if (typeof token.access_token !== "string" || !token.access_token) throw new Error("discord_token_missing");
  const identityResponse = await request("https://discord.com/api/v10/users/@me", {
    headers: { Authorization: "Bearer " + token.access_token },
    signal: AbortSignal.timeout(4500)
  });
  if (!identityResponse.ok) throw new Error("discord_identity_failed");
  const user = await identityResponse.json();
  if (user.bot || !snowflake(user.id)) throw new Error("invalid_discord_identity");
  return user.id;
}

async function setDiscordBadge(discordId, desired, request = fetch) {
  const guild = process.env.DISCORD_PREMIUM_GUILD_ID;
  const role = process.env.DISCORD_PREMIUM_ROLE_ID;
  if (![guild, role, discordId].every(snowflake)) throw new Error("invalid_discord_role_target");
  const response = await request(`https://discord.com/api/v10/guilds/${guild}/members/${discordId}/roles/${role}`, {
    method: desired ? "PUT" : "DELETE",
    headers: {
      Authorization: "Bot " + process.env.DISCORD_PREMIUM_BOT_TOKEN,
      "X-Audit-Log-Reason": "ARVIO Premium account badge sync"
    },
    signal: AbortSignal.timeout(4500)
  });
  if (response.status === 204) return desired ? "linked" : "removed";
  if (response.status === 404 && (await response.json()).code === 10007) {
    return desired ? "join" : "removed";
  }
  throw Object.assign(new Error("discord_badge_role_request_failed"), { status: response.status });
}

module.exports = {
  SITE, CALLBACK, discordBadgeConfigured, eligibleForDiscordBadge, createState, readState,
  badgeCookie, authorizeUrl, discordIdentity, setDiscordBadge
};

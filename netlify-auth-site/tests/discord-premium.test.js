const test = require("node:test");
const assert = require("node:assert/strict");
const crypto = require("node:crypto");
const fs = require("node:fs");
const vm = require("node:vm");
const { evaluateEntitlement } = require("../netlify/functions/_entitlements");
const { createState, readState, badgeCookie, authorizeUrl, discordIdentity, setDiscordBadge } = require("../netlify/functions/_discord-premium");

test("existing annual Shop purchases qualify without a new payment, including a verified billing link", async () => {
  const hash = (value) => crypto.createHash("sha256").update(value).digest("hex");
  const billing = "billing@example.test";
  const billingHash = hash(billing);
  const accountHash = hash("viewer@example.test");
  const annualPass = {
    source: "kofi", status: "active", plan: "yearly", tier: "12-month pass",
    startedAt: new Date(Date.now() - 30 * 86400000).toISOString(),
    expiresAt: new Date(Date.now() + 330 * 86400000).toISOString()
  };
  const entitlements = new Map([[billingHash, annualPass]]);
  const accounts = new Map([
    ["accounts/email/" + billingHash + ".json", { accountId: "billing-account", email: billing }],
    ["accounts/email/" + accountHash + ".json", { accountId: "viewer", email: "viewer@example.test" }]
  ]);
  const deleted = new Map();
  const module = { exports: {} };
  vm.runInNewContext(fs.readFileSync(require.resolve("../netlify/functions/_discord-premium"), "utf8"), {
    module, process: { env: {} },
    require: (name) => ({
      "node:crypto": crypto,
      "@netlify/blobs": { connectLambda() {}, getStore: (store) => store === "arvio-auth" ? accounts : deleted },
      "./_backend": { normalizeEmail: (email) => String(email || "").trim().toLowerCase(), sha256: hash },
      "./_entitlements": { entitlementsStore: () => entitlements, readEntitlement: (store, key) => store.get(key), evaluateEntitlement }
    })[name]
  });
  const eligible = module.exports.eligibleForDiscordBadge;
  assert.equal(await eligible({}, billingHash, "billing-account"), true);
  assert.equal(await eligible({}, accountHash, "viewer"), false);
  entitlements.set("link-owner/" + billingHash, { accountHash });
  entitlements.set(accountHash, { ...annualPass, linkedFrom: billing });
  assert.equal(await eligible({}, accountHash, "viewer"), true);
  assert.equal(await eligible({}, billingHash, "billing-account"), false);
  entitlements.set(billingHash, { ...annualPass, status: "cancelled" });
  assert.equal(await eligible({}, accountHash, "viewer"), false);
  deleted.set("revoked/account/" + hash("viewer") + ".json", { deleted: true });
  assert.equal(await eligible({}, accountHash, "viewer"), "deleted");
});

test("OAuth state is encrypted, expires and requires the initiating browser cookie", () => {
  process.env.DISCORD_PREMIUM_CLIENT_SECRET = "test-client-secret";
  process.env.DISCORD_PREMIUM_CLIENT_ID = "123456789012345678";
  const emailHash = "a".repeat(64);
  const accountId = "account-id";
  const state = createState(emailHash, accountId, 1000);
  const cookie = badgeCookie(state);
  assert.match(cookie, /Secure; HttpOnly; SameSite=Lax; Max-Age=600/);
  assert.ok(!Buffer.from(state, "base64url").includes(accountId));
  assert.deepEqual(readState(state, cookie, 1001), { emailHash, accountId, expires: 601000 });
  assert.equal(readState(state, cookie, 601000), null);
  assert.equal(readState(state, "", 1001), null);
  const changed = (state[0] === "a" ? "b" : "a") + state.slice(1);
  const forgedCookie = cookie.replace(crypto.createHash("sha256").update(state).digest("hex"),
    crypto.createHash("sha256").update(changed).digest("hex"));
  assert.equal(readState(changed, forgedCookie, 1001), null);
  const url = new URL(authorizeUrl(state));
  assert.equal(url.origin, "https://discord.com");
  assert.equal(url.searchParams.get("scope"), "identify");
  assert.equal(url.searchParams.get("state"), state);
});

test("Discord ID is fetched from Discord and only unknown members produce the join result", async () => {
  const discordId = "123456789012345678";
  process.env.DISCORD_PREMIUM_GUILD_ID = "123456789012345679";
  process.env.DISCORD_PREMIUM_ROLE_ID = "123456789012345680";
  process.env.DISCORD_PREMIUM_BOT_TOKEN = "test-bot-token";
  const calls = [];
  const result = await discordIdentity("authorization-code", async (url, request) => {
    calls.push({ url, request });
    return calls.length === 1
      ? Response.json({ access_token: "test-user-token" })
      : Response.json({ id: discordId });
  });
  assert.equal(result, discordId);
  assert.equal(calls[0].request.body.get("code"), "authorization-code");
  assert.equal(calls[1].request.headers.Authorization, "Bearer test-user-token");
  assert.equal(await setDiscordBadge(discordId, true, async (url, request) => {
    assert.ok(url.endsWith("/members/" + discordId + "/roles/" + process.env.DISCORD_PREMIUM_ROLE_ID));
    assert.equal(request.method, "PUT");
    assert.equal(request.headers.Authorization, "Bot test-bot-token");
    return new Response(null, { status: 204 });
  }), "linked");
  const missing = async () => Response.json({ code: 10007 }, { status: 404 });
  assert.equal(await setDiscordBadge(discordId, true, missing), "join");
  assert.equal(await setDiscordBadge(discordId, false, missing), "removed");
  await assert.rejects(setDiscordBadge(discordId, true, async () =>
    Response.json({ code: 10011 }, { status: 404 })), /discord_badge_role_request_failed/);
});

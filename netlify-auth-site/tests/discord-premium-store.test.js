const { readFileSync } = require("node:fs");
const path = require("node:path");
const assert = require("node:assert/strict");
const { test } = require("node:test");
const { PGlite } = require("@electric-sql/pglite");
const { claimDiscordBadge, reconcileDiscordBadges } = require("../netlify/functions/_discord-premium-store");

const accountId = "account-1";
const emailHash = "a".repeat(64);
const discordId = "1239908085397590037";
const event = {};

test("Discord badge links survive API failures, reject identity conflicts and reconcile expiry", async (t) => {
  const db = new PGlite();
  t.after(() => db.close());
  await db.exec(readFileSync(path.join(__dirname, "../netlify/database/migrations/20261103131500_discord_premium_links/migration.sql"), "utf8"));
  const client = { query: db.query.bind(db), release() {} };
  const pool = { query: client.query, connect: async () => client };
  let active = true;
  let failEligibility = false;
  let failDiscord = true;
  let joined = false;
  let calls = [];
  const options = {
    pool, event, accountId, emailHash, discordId,
    eligibleForDiscordBadge: async (seenEvent, seenHash, seenAccount) => {
      assert.equal(seenEvent, event);
      assert.match(seenHash, /^[a-f]{64}$/);
      assert.ok(seenAccount.startsWith("account-"));
      if (failEligibility) throw new Error("Entitlement check failed");
      return active;
    },
    setDiscordBadge: async (id, desired) => {
      calls.push([id, desired]);
      if (failDiscord) throw new Error("Discord request failed");
      return desired ? (joined ? "linked" : "join") : "removed";
    }
  };
  const due = () => db.query("UPDATE public.discord_premium_links SET next_check_at = now()");

  await assert.rejects(claimDiscordBadge(options), /Discord request failed/);
  assert.equal((await db.query("SELECT count(*)::int AS count FROM public.discord_premium_links")).rows[0].count, 1);

  failDiscord = false;
  assert.equal(await claimDiscordBadge({ ...options, accountId: "account-2", emailHash: "b".repeat(64) }), "conflict");
  assert.equal(await claimDiscordBadge({ ...options, discordId: "2239908085397590037" }), "conflict");
  assert.equal(await claimDiscordBadge({ ...options, accountId: "account-3", discordId: "3239908085397590037" }), "conflict");
  assert.equal(calls.length, 1);

  assert.equal((await reconcileDiscordBadges(options)).join, 1);
  assert.equal((await reconcileDiscordBadges(options)).join, 0);
  joined = true;
  assert.equal(await claimDiscordBadge(options), "linked");

  failEligibility = true;
  await due();
  calls = [];
  assert.equal((await reconcileDiscordBadges(options)).retry, 1);
  assert.deepEqual(calls, []);

  failEligibility = false;
  active = false;
  await due();
  assert.equal((await reconcileDiscordBadges(options)).inactive, 1);
  assert.deepEqual(calls, [[discordId, false]]);
  assert.equal(await claimDiscordBadge({ ...options, accountId: "account-2", discordId: "2239908085397590037" }), "inactive");

  active = true;
  await due();
  assert.equal((await reconcileDiscordBadges(options)).linked, 1);
  assert.deepEqual(calls.at(-1), [discordId, true]);

  active = "deleted";
  failDiscord = true;
  await due();
  assert.equal((await reconcileDiscordBadges(options)).retry, 1);
  assert.equal((await db.query("SELECT count(*)::int AS count FROM public.discord_premium_links")).rows[0].count, 1);
  failDiscord = false;
  await due();
  assert.equal((await reconcileDiscordBadges(options)).inactive, 1);
  assert.deepEqual(calls.at(-1), [discordId, false]);
  assert.equal((await db.query("SELECT count(*)::int AS count FROM public.discord_premium_links")).rows[0].count, 0);
  assert.equal(await claimDiscordBadge(options), "inactive");
});

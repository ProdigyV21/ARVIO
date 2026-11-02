import fs from "node:fs/promises";
import path from "node:path";
import crypto from "node:crypto";
import { fileURLToPath } from "node:url";
import { spawnSync } from "node:child_process";
import { getStore } from "@netlify/blobs";
import pg from "pg";
import domain from "../netlify/functions/_partner-domain.js";

// Explicit operator tool. Uses the same locally authenticated CLI as deploys;
// never prints credentials or writes them into the checkout/public artifact.
const siteId = "17ccb668-bf3c-412d-9573-a28a09e52122";
const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const mode = process.argv[2];
const address = process.argv[3] && domain.email(process.argv[3]);
if (!["prepare", "add-pilot", "runtime", "build", "status"].includes(mode) || (mode !== "build" && !address)) throw new Error("Use prepare|add-pilot|runtime|status <email>, or build");
const config = JSON.parse(await fs.readFile(path.join(process.env.APPDATA, "netlify/Config/config.json"), "utf8"));
const token = config.users[config.userId].auth.token;
async function api(route, method = "GET", body) {
  const response = await fetch(`https://api.netlify.com/api/v1${route}`, { method, headers: { Authorization: `Bearer ${token}`, "content-type": "application/json" }, ...(body ? { body: JSON.stringify(body) } : {}) });
  if (!response.ok) throw new Error(`Netlify ${method} failed (${response.status})`);
  return response.json();
}
const site = await api(`/sites/${siteId}`);
if (site.name !== "arvio-auth" || site.ssl_url !== "https://auth.arvio.tv") throw new Error("Unexpected deployment target");
const envRoute = `/accounts/${site.account_id}/env`;
const env = await api(`${envRoute}?site_id=${siteId}`);
const value = key => { const item = env.find(item => item.key === key); return item?.values.find(v => v.context === "production")?.value || item?.values.find(v => v.context === "all")?.value; };
if (mode === "build") {
  const result = spawnSync(process.execPath, [path.join(root, "scripts/build-partner-pilot.mjs")], { cwd: root, env: { ...process.env, APP_ANON_KEY: value("APP_ANON_KEY") }, encoding: "utf8" });
  process.stdout.write(result.stdout); process.stderr.write(result.stderr); process.exitCode = result.status;
} else {
  const store = getStore({ siteID: siteId, token, name: "arvio-auth", consistency: "strong" });
  const account = await store.get(`accounts/email/${domain.hash(address)}.json`, { type: "json" });
  if (!account?.accountId || account.email !== address) throw new Error("Existing ARVIO account not found");
  const database = await api(`/sites/${siteId}/database/`);
  const operatorUrl = new URL(database.connection_string);
  operatorUrl.searchParams.set("sslmode", "verify-full");
  const client = new pg.Client({ connectionString: operatorUrl.toString(), connectionTimeoutMillis: 10000, statement_timeout: 15000 });
  await client.connect();
  try {
    if (mode === "add-pilot") {
      if (process.argv[4] !== "--confirm-production") throw new Error("Explicit production confirmation is required");
      if (value("PARTNER_ENABLED") !== "true" || value("PARTNER_PILOT_MODE") !== "true" || !value("PARTNER_PILOT_OWNERS")) throw new Error("An existing restricted pilot is required");
      const rights = (await client.query("SELECT has_table_privilege(current_user,'public.partner_pilot_grants','INSERT') AS can_grant")).rows[0];
      if (!rights.can_grant) throw new Error("Production CLI database access is read-only. Obtain explicit operator approval before provisioning.");
      const owners = [...new Set(value("PARTNER_PILOT_OWNERS").split(",").map(domain.email).concat(address))];
      await client.query("BEGIN");
      try {
        const organizationId = crypto.randomUUID();
        const state = domain.newState("ARVIO QA");
        domain.audit(state, "operator", "pilot-workspace-provisioned", organizationId, new Date().toISOString());
        await client.query("INSERT INTO partner_organizations(id,owner_id,billing_email_hash,state) VALUES($1,$2,$3,$4) ON CONFLICT DO NOTHING", [organizationId, account.accountId, domain.hash(address), JSON.stringify(state)]);
        await client.query("INSERT INTO partner_pilot_grants(account_id,email_hash,seats,expires_at,granted_by,reason) VALUES($1,$2,20,$3,'operator','User-designated isolated QA pilot; complimentary, no automatic renewal') ON CONFLICT DO NOTHING", [account.accountId, domain.hash(address), new Date(Date.now() + 30 * 86400000).toISOString()]);
        await client.query("COMMIT");
      } catch (error) { await client.query("ROLLBACK"); throw error; }
      if (!value("PARTNER_PILOT_OWNERS").split(",").map(domain.email).includes(address)) {
        await api(`${envRoute}/PARTNER_PILOT_OWNERS?site_id=${siteId}`, "PATCH", { context: "production", value: owners.join(",") });
      }
      console.log(JSON.stringify({ addedPilot: address, existingOwnersPreserved: true, grantRenewed: false }));
    }
    if (mode === "runtime") {
      if (process.argv[4] !== "--confirm-production") throw new Error("Explicit production confirmation is required");
      if (env.some(item => item.key === "PARTNER_DATABASE_URL")) throw new Error("Partner runtime connection already exists; do not rotate it implicitly");
      const role = "arvio_partner_runtime";
      if ((await client.query("SELECT rolname FROM pg_roles WHERE rolname=$1", [role])).rows.length) throw new Error("Runtime role already exists without its secret; manual recovery required");
      const password = crypto.randomBytes(32).toString("hex");
      const tables = ["partner_organizations", "partner_memberships", "partner_invitations", "partner_challenges", "partner_admin_sessions", "partner_payments"];
      await client.query("BEGIN");
      // Role name and password are fixed/generated here, never user input.
      await client.query(`CREATE ROLE ${role} LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE NOINHERIT NOBYPASSRLS PASSWORD '${password}'`);
      await client.query(`GRANT USAGE ON SCHEMA public TO ${role}`);
      for (const table of tables) {
        await client.query(`GRANT SELECT,INSERT,UPDATE,DELETE ON public.${table} TO ${role}`);
        await client.query(`CREATE POLICY partner_server_runtime ON public.${table} FOR ALL TO ${role} USING (true) WITH CHECK (true)`);
      }
      await client.query(`GRANT SELECT ON public.partner_pilot_grants TO ${role}`);
      await client.query(`CREATE POLICY partner_server_runtime ON public.partner_pilot_grants FOR SELECT TO ${role} USING (true)`);
      await client.query("COMMIT");
      const url = new URL(database.connection_string);
      url.username = role; url.password = password; url.searchParams.set("sslmode", "verify-full");
      const runtime = new pg.Client({ connectionString: url.toString(), connectionTimeoutMillis: 10000 });
      await runtime.connect();
      try {
        const rights = (await runtime.query("SELECT current_user AS role,has_schema_privilege(current_user,'public','CREATE') AS can_create,has_table_privilege(current_user,'public.arvio_accounts','SELECT') AS can_read_accounts,has_table_privilege(current_user,'public.partner_pilot_grants','INSERT') AS can_grant_seats")).rows[0];
        if (rights.can_create || rights.can_read_accounts || rights.can_grant_seats) throw new Error("Runtime permissions are too broad");
        await runtime.query("BEGIN");
        const testId = `pilot-runtime-test-${crypto.randomUUID()}`;
        await runtime.query("INSERT INTO partner_challenges(account_id,code_hash,expires_at) VALUES($1,'test',now())", [testId]);
        await runtime.query("ROLLBACK");
        console.log(JSON.stringify({ runtimePermissions: rights, transactionalWriteVerified: true }));
      } finally { await runtime.end(); }
      await api(`${envRoute}?site_id=${siteId}`, "POST", [{ key: "PARTNER_DATABASE_URL", scopes: ["functions"], is_secret: true, values: [{ context: "production", value: url.toString() }] }]);
      console.log(JSON.stringify({ configuredKeys: ["PARTNER_DATABASE_URL"], scope: "Partner tables only; pilot grants read-only" }));
    }
    if (mode === "prepare") {
      if (process.argv[4] !== "--confirm-production") throw new Error("Explicit production confirmation is required");
      const permissions = await client.query("SELECT has_schema_privilege(current_user,'public','CREATE') AS can_migrate");
      if (!permissions.rows[0].can_migrate) throw new Error("Production CLI database access is read-only. Do not change access controls without the operator's explicit approval.");
      const desired = { PARTNER_ENABLED: "true", PARTNER_PILOT_MODE: "true", PARTNER_PILOT_OWNERS: address, PARTNER_APP_URL: "https://auth.arvio.tv" };
      for (const [key, expected] of Object.entries(desired)) {
        if (env.some(item => item.key === key) && value(key) !== expected) throw new Error(`Existing ${key} differs; review before changing it`);
      }
      // Additive transaction; no personal account, cloud or billing data is changed.
      await client.query(await fs.readFile(path.join(root, "sql/partner.sql"), "utf8"));
      await client.query("BEGIN");
      const organizationId = crypto.randomUUID();
      const state = domain.newState("ARVIO Pilot");
      domain.audit(state, "operator", "pilot-workspace-provisioned", organizationId, new Date().toISOString());
      await client.query("INSERT INTO partner_organizations(id,owner_id,billing_email_hash,state) VALUES($1,$2,$3,$4) ON CONFLICT DO NOTHING", [organizationId, account.accountId, domain.hash(address), JSON.stringify(state)]);
      await client.query("INSERT INTO partner_pilot_grants(account_id,email_hash,seats,expires_at,granted_by,reason) VALUES($1,$2,20,$3,'operator','User-requested private production pilot; complimentary, no automatic renewal') ON CONFLICT DO NOTHING", [account.accountId, domain.hash(address), new Date(Date.now() + 30 * 86400000).toISOString()]);
      await client.query("COMMIT");
      const pending = Object.entries(desired).filter(([key]) => !env.some(item => item.key === key)).map(([key, val]) => ({ key, scopes: ["functions"], values: [{ context: "production", value: val }] }));
      if (!env.some(item => item.key === "PARTNER_ENCRYPTION_KEY")) pending.push({ key: "PARTNER_ENCRYPTION_KEY", scopes: ["functions"], is_secret: true, values: [{ context: "production", value: crypto.randomBytes(32).toString("hex") }] });
      if (pending.length) await api(`${envRoute}?site_id=${siteId}`, "POST", pending);
      console.log(JSON.stringify({ configuredKeys: pending.map(item => item.key), previousDeploy: site.published_deploy?.id }));
    }
    const result = await client.query("SELECT o.id,o.revision,o.state->>'name' AS name,g.seats,g.expires_at,g.revoked_at FROM partner_organizations o LEFT JOIN partner_pilot_grants g ON g.account_id=o.owner_id WHERE o.owner_id=$1 AND o.billing_email_hash=$2", [account.accountId, domain.hash(address)]);
    console.log(JSON.stringify({ email: address, workspace: result.rows[0] || null, realAccount: true }));
  } finally { await client.end(); }
}

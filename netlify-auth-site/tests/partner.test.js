const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const crypto = require("node:crypto");
const { PGlite } = require("@electric-sql/pglite");
const domain = require("../netlify/functions/_partner-domain");
const { createService } = require("../netlify/functions/_partner-service");

const master = "12".repeat(32);
const now = Date.parse("2026-10-26T12:00:00Z");
const owner = { supabaseUserId: "owner-a", email: "owner@example.test" };
const other = { supabaseUserId: "owner-b", email: "other@example.test" };
const customer = { supabaseUserId: "customer-a", email: "customer@example.test" };
const payment = (extra = {}) => ({
  tier_name: "ARVIO Partner 20", amount: "39.00", currency: "USD", timestamp: new Date(now).toISOString(),
  is_subscription_payment: true, type: "Subscription", kofi_transaction_id: "payment-1", email: owner.email, ...extra
});

async function fixture(t, options = {}) {
  const db = new PGlite();
  await db.exec(fs.readFileSync(require.resolve("../sql/partner.sql"), "utf8"));
  // PGlite has one connection; queue complete transactions, not individual
  // statements, so contention tests exercise the actual SQL commit boundaries.
  let tail = Promise.resolve();
  const pool = {
    query: (...args) => db.query(...args),
    connect: async () => {
      const previous = tail; let release;
      tail = new Promise((resolve) => { release = resolve; });
      await previous;
      return { query: (...args) => db.query(...args), release };
    }
  };
  let clock = now;
  const mail = [];
  const service = createService({ pool, master, now: () => clock, sendEmail: async (...args) => mail.push(args), appUrl: "https://test.arvio.tv", ...options });
  async function verify(identity = owner) {
    await service.beginVerification(identity);
    const code = mail.at(-1)[2].match(/\b\d{6}\b/)[0];
    return (await service.verify(identity, code)).proof;
  }
  const proof = await verify();
  let org = (await service.createOrganization(owner, proof, "Northside Media")).organization;
  async function command(input) {
    const response = await service.command(owner, proof, org.id, org.revision, input);
    org = response.organization; return response;
  }
  t.after(() => db.close());
  return { db, service, mail, proof, verify, command, get org() { return org; }, time(value) { clock = value; } };
}

test("private pilot is allowlisted, complimentary, expiring and separate from payments", async (t) => {
  const f = await fixture(t, { pilotOwners: [owner.email] });
  await assert.rejects(f.service.beginVerification(other), /private pilot/);
  await assert.rejects(f.service.verify(other, "123456"), /private pilot/);
  await assert.rejects(f.service.payment(payment()), /sales are not open/);
  await f.db.query("INSERT INTO partner_pilot_grants(account_id,email_hash,seats,expires_at,granted_by,reason) VALUES($1,$2,20,$3,'operator','Private pilot test')", [owner.supabaseUserId, domain.hash(owner.email), new Date(now + 86400000).toISOString()]);
  const org = (await f.service.overview(owner, f.proof)).organization;
  assert.equal(org.billing.source, "pilot");
  assert.equal(org.billing.privatePilot, true);
  assert.equal(org.billing.seats, 20);
  const invited = await f.command({ action: "invite-customer", name: "Viewer", email: customer.email });
  const token = f.mail.at(-1)[2].match(/join#([\w-]+)/)[1];
  await f.service.invitation(customer, token, true);
  assert.equal((await f.service.membership(customer)).active, true);
  const device = { key: "x".repeat(40), name: "Pilot browser", platform: "web" };
  const delivery = await f.service.delivery(customer, device);
  assert.equal(delivery.active, true);
  assert.equal(delivery.leaseUntil, org.billing.graceUntil);
  assert.equal((await f.db.query("SELECT * FROM partner_payments")).rows.length, 0);
  assert.ok(invited.target);
  f.time(now + 86400001);
  assert.equal((await f.service.membership(customer)).active, false);
  assert.equal(await f.service.entitlement(customer), null);
  assert.equal((await f.service.delivery(customer, device)).active, false);
});

test("pilot grants require account AND email and revoked grants give no access", async (t) => {
  const f = await fixture(t, { pilotOwners: [owner.email] });
  await f.db.query("INSERT INTO partner_pilot_grants(account_id,email_hash,seats,expires_at,granted_by,reason) VALUES($1,$2,20,$3,'operator','Test')", [owner.supabaseUserId, domain.hash(other.email), new Date(now + 86400000).toISOString()]);
  assert.equal((await f.service.overview(owner, f.proof)).organization.billing.seats, 0);
  await f.db.query("UPDATE partner_pilot_grants SET email_hash=$1,revoked_at=$2", [domain.hash(owner.email), new Date(now).toISOString()]);
  assert.equal((await f.service.overview(owner, f.proof)).organization.billing.seats, 0);
});

test("configuration validation rejects private snapshots and unknown settings", () => {
  assert.throws(() => domain.validateConfig({ traktTokens: {} }), /Unsupported template/);
  assert.throws(() => domain.validateConfig({ settings: { apiKey: "private" } }), /Unsupported setting/);
  assert.throws(() => domain.validateConfig({ catalogs: [{ id: "x", name: "X", params: { password: "bad" } }] }), /Credentials/);
  assert.throws(() => domain.validateConfig({ collectionUrls: ["javascript:alert(1)"] }));
  assert.deepEqual(domain.validateConfig({ settings: { netflixHomeStyle: true } }).settings, { netflixHomeStyle: true });
  const catalog = domain.validateConfig({ catalogs: [{ id: "movies", title: "Movies", sourceType: "ADDON", addonId: "org.cinema", addonCatalogId: "movies", addonCatalogType: "movie" }] }).catalogs[0];
  assert.equal(catalog.name, "Movies");
  assert.equal(catalog.sourceType, "addon");
  assert.equal(catalog.enabled, true);
  assert.throws(() => domain.validateConfig({ catalogs: [{ id: "broken", name: "Broken" }] }), /sourceType/);
  assert.throws(() => domain.validateConfig(JSON.parse('{"settings":{"__proto__":true}}')), /Unsupported setting/);
});

test("templates include tenant-owned add-ons and IPTV without copying credentials; publication and rollback retain options", async t => {
  const f = await fixture(t);
  await f.service.payment(payment());
  const addon = (await f.command({ action: "save-service", name: "Sports addon", kind: "addon", url: "https://addon.example.test/manifest.json" })).target;
  const iptv = (await f.command({ action: "save-service", name: "Full IPTV", kind: "iptv", url: "https://provider.example.test", iptv: { mode: "xtream", movies: false } })).target;
  await assert.rejects(f.command({ action: "save-template", name: "Foreign reference", config: { serviceIds: [crypto.randomUUID()] } }), /not found/);
  const template = (await f.command({ action: "save-template", name: "Family", config: { serviceIds: [addon, iptv, addon] } })).target;
  const pkg = (await f.command({ action: "save-package", name: "TV", templateId: template, serviceIds: [iptv] })).target;
  const viewer = (await f.command({ action: "invite-customer", name: "Viewer", email: customer.email, packageId: pkg })).target;
  const invitation = f.mail.at(-1)[2].match(/join#([\w-]+)/)[1];
  await f.service.invitation(customer, invitation, true);
  // Accepting the invitation changed the administrator revision.
  const overview = await f.service.overview(owner, f.proof);
  const assigned = await f.service.command(owner, f.proof, f.org.id, overview.organization.revision, { action: "set-credentials", id: viewer, serviceId: iptv, credentials: { username: "viewer", password: " private-password ", epgUrl: "https://guide.example.test/feed.xml" } });
  assert.equal(JSON.stringify(assigned).includes("private-password"), false);
  const device = { key: "q".repeat(40), name: "QA", platform: "web" };
  const first = await f.service.delivery(customer, device);
  assert.deepEqual(first.services.map(s => s.id), [addon, iptv]);
  assert.equal(first.services[0].credentials, null, "public add-ons need no per-customer secrets");
  assert.equal(first.services[0].url, "https://addon.example.test/manifest.json");
  assert.equal(first.services[1].iptv.movies, false);
  assert.equal(first.services[1].credentials.password, " private-password ");
  assert.equal(first.services[1].credentials.epgUrl, "https://guide.example.test/feed.xml");
  assert.equal(JSON.stringify(first.config).includes("private-password"), false);
  const changed = await f.service.command(owner, f.proof, f.org.id, assigned.organization.revision, { action: "save-service", id: iptv, name: "Full IPTV", kind: "iptv", iptv: { mode: "xtream", movies: true, catchup: false } });
  assert.equal((await f.service.delivery(customer, device)).services[1].iptv.movies, false, "draft does not update devices");
  const published = await f.service.command(owner, f.proof, f.org.id, changed.organization.revision, { action: "publish-package", id: pkg });
  assert.equal((await f.service.delivery(customer, device)).services[1].iptv.movies, true);
  assert.equal((await f.service.delivery(customer, device)).services[1].iptv.catchup, false);
  await f.service.command(owner, f.proof, f.org.id, published.organization.revision, { action: "rollback-customer", id: viewer, deliveryRevision: first.revision });
  assert.equal((await f.service.delivery(customer, device)).services[1].iptv.movies, false);
  assert.equal(JSON.stringify((await f.db.query('SELECT state FROM partner_organizations')).rows).includes("private-password"), false);
});

test("IPTV configuration supports M3U and Xtream, validates capability flags and keeps passwords exact", () => {
  const m3u = { iptv: { mode: "m3u" } };
  assert.deepEqual(domain.iptvCredentials({ playlistUrl: "https://provider.test/custom?auth=private", epgUrl: "https://provider.test/guide.xml", username: "", password: "" }, m3u), { playlistUrl: "https://provider.test/custom?auth=private", epgUrl: "https://provider.test/guide.xml" });
  assert.throws(() => domain.iptvCredentials({ username: "x", password: "y" }, m3u));
  assert.throws(() => domain.iptvCredentials({ playlistUrl: "file:///private" }, m3u));
  assert.throws(() => domain.iptvCredentials({ playlistUrl: "https://provider.test/list", token: "wrong-field" }, m3u));
  assert.equal(domain.iptvCredentials({ username: "viewer", password: " pass " }, { iptv: { mode: "xtream" } }).password, " pass ");
  assert.throws(() => domain.iptvOptions({ movies: "false" }));
  assert.throws(() => domain.iptvOptions({ liveTv: false, movies: false, series: false }));
  assert.equal(domain.iptvOptions({ liveTv: false }).epg, false);
  assert.equal(domain.iptvOptions({ epg: false }).catchup, false);
  assert.equal(domain.iptvOptions({}).movies, true);
});

test("connection envelopes are tenant and purpose bound, tamper evident and write-only", () => {
  const envelope = domain.seal({ token: "private" }, "tenant-a", "service-1", master);
  assert.equal(JSON.stringify(envelope).includes("private"), false);
  assert.equal(domain.unseal(envelope, "tenant-a", "service-1", master).token, "private");
  assert.throws(() => domain.unseal(envelope, "tenant-b", "service-1", master));
  assert.throws(() => domain.unseal(envelope, "tenant-a", "service-2", master));
  assert.throws(() => domain.seal({}, "a", "b", "short"), /not configured/);
});

test("calendar billing handles month ends, duplicate/overlapping periods, expiry and refunds", () => {
  assert.equal(domain.nextMonth("2028-01-31T12:00:00Z"), "2028-02-29T12:00:00.000Z");
  assert.throws(() => domain.parsePayment(payment({ amount: "3.99" }), now), /amount/);
  assert.throws(() => domain.parsePayment(payment({ currency: "EUR" }), now), /currency/);
  assert.throws(() => domain.parsePayment(payment({ is_subscription_payment: false }), now), /membership/);
  assert.equal(domain.parsePayment(payment({ tier_name: "Premium" }), now), null);
  const paid = [{ tier: "ARVIO Partner 100", paid_until: "2026-10-27T12:00:00Z" }];
  assert.equal(domain.billing(paid, now).seats, 100);
  assert.equal(domain.billing(paid, now + 2 * 86400000).status, "grace");
  assert.equal(domain.billing(paid, now + 4 * 86400000).status, "expired");
  assert.equal(domain.billing([{ ...paid[0], revoked_at: new Date(now).toISOString() }], now).seats, 0);
});

test("administrator challenge cooldown, wrong attempts, single use and session expiry", async (t) => {
  const f = await fixture(t);
  await assert.rejects(f.service.beginVerification(owner), /one minute/);
  const oldCode = f.mail[0][2].match(/\b\d{6}\b/)[0];
  await assert.rejects(f.service.verify(owner, oldCode), /Invalid or expired/);
  await f.service.beginVerification(other);
  const real = f.mail.at(-1)[2].match(/\b\d{6}\b/)[0];
  const wrong = real === "000000" ? "000001" : "000000";
  for (let attempt = 0; attempt < 5; attempt++) await assert.rejects(f.service.verify(other, wrong), /Invalid or expired/);
  await assert.rejects(f.service.verify(other, real), /Invalid or expired/);
  f.time(now + 1800001);
  await assert.rejects(f.service.overview(owner, f.proof), /expired/);
});

test("tenant isolation, stale revisions and sanitized dashboard data", async (t) => {
  const f = await fixture(t);
  const otherProof = await f.verify(other);
  await assert.rejects(f.service.command(other, otherProof, f.org.id, f.org.revision, { action: "save-template", name: "X", config: {} }), /not found/);
  await assert.rejects(f.service.overview(other, f.proof), /expired/);
  const initial = f.org.revision;
  const response = await f.command({ action: "save-service", name: "Movies", kind: "emby", url: "https://server.test" });
  assert.equal(response.organization.services[0].connection, undefined);
  assert.equal(JSON.stringify(response).includes("server.test"), false);
  await assert.rejects(f.service.command(owner, f.proof, f.org.id, initial, { action: "save-template", name: "X", config: {} }), /another session/);
});

test("membership payments are idempotent and adding customers requires a paid seat", async (t) => {
  const f = await fixture(t);
  await assert.rejects(f.command({ action: "invite-customer", name: "Viewer", email: customer.email }), /active Partner/);
  assert.equal((await f.service.payment(payment())).duplicate, false);
  assert.equal((await f.service.payment(payment())).duplicate, true);
  assert.equal((await f.db.query("SELECT * FROM partner_payments")).rows.length, 1);
  for (let index = 0; index < 20; index++) await f.command({ action: "invite-customer", name: `Viewer ${index}`, email: `viewer${index}@example.test` });
  await assert.rejects(f.command({ action: "invite-customer", name: "Overflow", email: "over@example.test" }), /no free/);
  assert.equal(f.org.seatsUsed, 20);
});

test("invitation consent, correct identity, device delivery, acknowledgements, revocation and leaving", async (t) => {
  const f = await fixture(t);
  await f.service.payment(payment());
  const template = await f.command({ action: "save-template", name: "Cinema", config: { settings: { netflixHomeStyle: true }, catalogs: [] } });
  const service = await f.command({ action: "save-service", name: "Emby", kind: "emby", url: "https://media.test" });
  const pkg = await f.command({ action: "save-package", name: "Movies", templateId: template.target, serviceIds: [service.target] });
  const invited = await f.command({ action: "invite-customer", name: "Viewer", email: customer.email, packageId: pkg.target });
  assert.equal(invited.invitationSent, true);
  assert.equal(JSON.stringify(invited).includes("#"), false);
  const token = f.mail.at(-1)[2].match(/join#([\w-]+)/)[1];
  await assert.rejects(f.service.invitation(other, token), /email address/);
  assert.equal((await f.service.invitation(customer, token)).name, "Northside Media");
  assert.equal((await f.db.query("SELECT * FROM partner_memberships")).rows.length, 0);
  await f.service.invitation(customer, token, true);
  assert.equal((await f.service.entitlement(customer)).reason, "partner");
  assert.equal(await f.service.entitlement(other), null);
  await assert.rejects(f.service.invitation(customer, token, true), /not found/);
  // Read after acceptance to respect the revision change.
  const refresh = await f.service.overview(owner, f.proof);
  const credentials = await f.service.command(owner, f.proof, f.org.id, refresh.organization.revision, { action: "set-credentials", id: invited.target, serviceId: service.target, credentials: { token: "viewer-token", userId: "viewer-id" } });
  assert.equal(JSON.stringify(credentials).includes("viewer-token"), false);
  const device = { key: crypto.randomBytes(32).toString("base64url"), name: "Living room", platform: "android-tv", appliedRevision: 0 };
  const first = await f.service.delivery(customer, device);
  assert.equal(first.services[0].credentials.token, "viewer-token");
  assert.equal(first.config.settings.netflixHomeStyle, true);
  const delivered = await f.service.delivery(customer, { ...device, appliedRevision: first.revision });
  assert.equal(delivered.revision, first.revision);
  const overview = (await f.service.overview(owner, f.proof)).organization;
  assert.equal(overview.devices[0].appliedRevision, first.revision);
  assert.equal(overview.devices[0].keyHash, undefined);
  await f.service.command(owner, f.proof, f.org.id, overview.revision, { action: "revoke-device", id: first.deviceId });
  await assert.rejects(f.service.delivery(customer, device), /revoked/);
  await f.service.leave(customer);
  assert.equal(await f.service.entitlement(customer), null);
  assert.deepEqual(await f.service.delivery(customer, device), { managed: false });
  assert.equal((await f.db.query("SELECT * FROM partner_memberships")).rows.length, 0);
});

test("copying configurations does not copy private credentials; publishing and rollback retain versions", () => {
  const org = crypto.randomUUID();
  const context = { organizationId: org, actor: owner.email, master, entitlement: { seats: 20, status: "active" }, now };
  let state = domain.newState("Test");
  const apply = (command) => { const result = domain.mutate(state, command, context); state = result.state; return result.target; };
  const template = apply({ action: "save-template", name: "Home", config: { settings: { netflixHomeStyle: true } } });
  const service = apply({ action: "save-service", name: "Addon", kind: "addon", url: "https://addon.test/manifest.json" });
  const pkg = apply({ action: "save-package", name: "Package", templateId: template, serviceIds: [service] });
  const a = apply({ action: "invite-customer", name: "A", email: "a@test.dev", packageId: pkg });
  const b = apply({ action: "invite-customer", name: "B", email: "b@test.dev" });
  apply({ action: "set-credentials", id: a, serviceId: service, credentials: { token: "a-only" } });
  apply({ action: "copy-configuration", sourceId: a, customerIds: [b], linked: false });
  assert.deepEqual(state.customers.find((c) => c.id === b).credentials, {});
  apply({ action: "save-template", id: template, name: "Home", config: { settings: { netflixHomeStyle: false } } });
  apply({ action: "save-service", id: service, name: "Addon", kind: "addon", url: "https://updated.test/manifest.json" });
  apply({ action: "set-credentials", id: a, serviceId: service, credentials: { token: "updated-a" } });
  assert.equal(state.customers[0].delivery.config.settings.netflixHomeStyle, true);
  const serviceUrl = () => domain.unseal(state.customers[0].delivery.serviceSnapshots[0].connection, org, `service:${service}`, master).url;
  assert.equal(serviceUrl(), "https://addon.test/manifest.json");
  apply({ action: "publish-package", id: pkg });
  assert.equal(serviceUrl(), "https://updated.test/manifest.json");
  assert.equal(state.customers[0].delivery.config.settings.netflixHomeStyle, false);
  assert.equal(state.customers[1].delivery.config.settings.netflixHomeStyle, true);
  apply({ action: "rollback-customer", id: a, deliveryRevision: 1 });
  assert.equal(serviceUrl(), "https://addon.test/manifest.json");
  assert.equal(state.customers[0].delivery.config.settings.netflixHomeStyle, true);
});

test("packed configurations round-trip without mutating state or duplicating large histories", () => {
  const state = domain.newState("Packed");
  const config = { config: { settings: {}, catalogs: [{ id: "large", name: "Title", description: "x".repeat(20000) }], collectionUrls: [] }, permissions: domain.permissions(), serviceIds: [], serviceSnapshots: [] };
  state.customers = Array.from({ length: 200 }, (_, index) => ({ id: `customer-${index}`, delivery: { ...config, revision: 2 }, history: [{ ...config, revision: 1 }], credentials: {} }));
  const before = JSON.stringify(state);
  const packed = domain.packState(state);
  assert.equal(JSON.stringify(state), before);
  assert.deepEqual(domain.unpackState(packed), state);
  assert.ok(JSON.stringify(packed).length < before.length / 10);
  assert.deepEqual(domain.unpackState(state), state);
});

test("customer credentials are scoped, write-only and cannot change administrator endpoints", async (t) => {
  const f = await fixture(t);
  await f.service.payment(payment());
  const shared = await f.command({ action: "save-service", name: "TV", kind: "iptv", url: "https://provider.test", credentialMode: "customer" });
  const assigned = await f.command({ action: "save-service", name: "Movies", kind: "emby", url: "https://movies.test" });
  const pkg = await f.command({ action: "save-package", name: "Package", serviceIds: [shared.target, assigned.target] });
  await f.command({ action: "invite-customer", name: "Customer", email: customer.email, packageId: pkg.target });
  const token = f.mail.at(-1)[2].match(/join#([\w-]+)/)[1];
  await f.service.invitation(customer, token, true);
  await assert.rejects(f.service.customerCredentials(other, shared.target, { username: "x", password: "y" }), /membership/);
  await assert.rejects(f.service.customerCredentials(customer, assigned.target, { username: "x", password: "y" }), /administrator/);
  await assert.rejects(f.service.customerCredentials(customer, shared.target, { username: "x", password: "y", url: "https://evil.test" }), /Invalid/);
  await f.service.customerCredentials(customer, shared.target, { username: "viewer", password: " only-this-customer " });
  const summary = await f.service.membership(customer);
  assert.equal(summary.active, true);
  assert.equal(summary.services[0].credentialsSupplied, true);
  assert.equal(JSON.stringify(summary).includes("only-this-customer"), false);
  assert.equal(JSON.stringify(summary).includes("provider.test"), false);
  const device = { key: crypto.randomBytes(32).toString("base64url"), name: "Browser", platform: "web" };
  const current = await f.service.delivery(customer, device);
  assert.equal(current.services[0].url, "https://provider.test/");
  assert.equal(current.services[0].credentials.password, " only-this-customer ");
  await f.service.reconcile("payment-1", "Refund confirmed");
  assert.equal((await f.service.membership(customer)).active, false);
  await assert.rejects(f.service.customerCredentials(customer, shared.target, { username: "viewer", password: "changed" }), /membership/);
  assert.equal((await f.service.delivery(customer, device)).active, false);
});

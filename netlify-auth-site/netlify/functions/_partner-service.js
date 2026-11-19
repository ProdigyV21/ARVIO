const crypto = require("node:crypto");
const domain = require("./_partner-domain");
const { fail, hash, equal, text, id, email, billing, publicState } = domain;

function createService({ pool, sendEmail, master = process.env.PARTNER_ENCRYPTION_KEY, now = () => Date.now(), appUrl = "https://web.arvio.tv",
  pilotOwners = process.env.PARTNER_PILOT_MODE === "true" ? (process.env.PARTNER_PILOT_OWNERS || "").split(",").map(value => value.trim().toLowerCase()).filter(Boolean) : null }) {
  const iso = () => new Date(now()).toISOString();
  const codeHash = (accountId, code) => crypto.createHmac("sha256", master || "").update(`${accountId}:${code}`).digest("hex");
  const identityId = (identity) => text(identity.supabaseUserId, 200);
  const hydrateRow = (row) => row ? { ...row, state: domain.unpackState(row.state) } : null;
  const allowedOwnerHashes = pilotOwners === null ? null : new Set(pilotOwners.map(value => hash(email(value))));
  const ownerAllowed = (emailHash) => allowedOwnerHashes === null || allowedOwnerHashes.has(emailHash);
  function requirePilotOwner(identity) {
    if (!ownerAllowed(hash(email(identity.email)))) fail(403, "Partner is in a private pilot. This account has not been invited as an administrator.");
  }
  async function transaction(run) {
    const client = await pool.connect();
    try { await client.query("BEGIN"); const result = await run(client); await client.query("COMMIT"); return result; }
    catch (error) { await client.query("ROLLBACK"); throw error; }
    finally { client.release(); }
  }
  async function paymentState(client, row) {
    const { rows } = await client.query("SELECT tier, paid_until, revoked_at FROM partner_payments WHERE email_hash=$1", [row.billing_email_hash]);
    const paid = billing(rows, now());
    const grants = await client.query("SELECT seats, expires_at, revoked_at FROM partner_pilot_grants WHERE account_id=$1 AND email_hash=$2", [row.owner_id, row.billing_email_hash]);
    const grant = grants.rows[0];
    if (grant && !grant.revoked_at && Date.parse(grant.expires_at) > now() && (allowedOwnerHashes !== null || grant.seats >= paid.seats)) {
      const expires = new Date(grant.expires_at).toISOString();
      return { status: "active", seats: grant.seats, tier: "Complimentary pilot", paidUntil: expires, graceUntil: expires, source: "pilot", privatePilot: allowedOwnerHashes !== null };
    }
    return { ...paid, privatePilot: allowedOwnerHashes !== null };
  }
  async function owner(client, identity, organizationId, lock = false) {
    const { rows } = await client.query(`SELECT * FROM partner_organizations WHERE id=$1 AND owner_id=$2${lock ? " FOR UPDATE" : ""}`, [id(organizationId), identityId(identity)]);
    if (!rows[0]) fail(404, "Organization not found");
    return hydrateRow(rows[0]);
  }
  async function save(client, row, state) {
    const { rows } = await client.query("UPDATE partner_organizations SET state=$2,revision=revision+1,updated_at=$3 WHERE id=$1 RETURNING *", [row.id, JSON.stringify(domain.packState(state)), iso()]);
    return hydrateRow(rows[0]);
  }
  async function requireAdmin(client, identity, proof) {
    requirePilotOwner(identity);
    if (typeof proof !== "string" || proof.length < 32) fail(403, "Verify your email to manage this organization");
    const { rows } = await client.query("SELECT account_id FROM partner_admin_sessions WHERE token_hash=$1 AND account_id=$2 AND expires_at>$3", [hash(proof), identityId(identity), iso()]);
    if (!rows.length) fail(403, "Administrator verification expired. Verify again to continue.");
  }
  async function beginVerification(identity) {
    requirePilotOwner(identity);
    // Refuse to create challenges without the durable encryption secret.
    domain.seal({}, "verification", "configuration-check", master);
    const accountId = identityId(identity);
    const code = String(crypto.randomInt(0, 1000000)).padStart(6, "0");
    await transaction(async (client) => {
      // A row exists before locking, so two first requests cannot bypass cooldown.
      await client.query("INSERT INTO partner_challenges(account_id,code_hash,expires_at,sent_at,window_start,sends) VALUES($1,'',$2,$3,$3,0) ON CONFLICT DO NOTHING", [accountId, iso(), new Date(0).toISOString()]);
      const { rows } = await client.query("SELECT * FROM partner_challenges WHERE account_id=$1 FOR UPDATE", [accountId]);
      const current = rows[0];
      if (now() - Date.parse(current.sent_at) < 60000) fail(429, "Wait one minute before requesting another code");
      const sameWindow = now() - Date.parse(current.window_start) < 3600000;
      if (sameWindow && current.sends >= 5) fail(429, "Too many verification requests. Try again in an hour.");
      await client.query("UPDATE partner_challenges SET code_hash=$2,expires_at=$3,sent_at=$4,attempts=0,used=false,sends=$5,window_start=$6 WHERE account_id=$1",
        [accountId, codeHash(accountId, code), new Date(now() + 600000).toISOString(), iso(), sameWindow ? current.sends + 1 : 1, sameWindow ? current.window_start : iso()]);
    });
    await sendEmail(email(identity.email), "Verify your ARVIO Partner sign-in", `Your administrator verification code is ${code}. It expires in 10 minutes. Do not share this code.`, `<p>Your ARVIO Partner verification code is <strong>${code}</strong>.</p><p>It expires in 10 minutes. Do not share this code.</p>`);
    return { sent: true };
  }
  async function verify(identity, code) {
    requirePilotOwner(identity);
    if (!/^\d{6}$/.test(String(code))) fail(400, "Enter the six-digit verification code");
    const accountId = identityId(identity);
    const result = await transaction(async (client) => {
      const { rows } = await client.query("SELECT * FROM partner_challenges WHERE account_id=$1 FOR UPDATE", [accountId]);
      const challenge = rows[0];
      if (!challenge || challenge.used || challenge.attempts >= 5 || Date.parse(challenge.expires_at) <= now()) return null;
      await client.query("UPDATE partner_challenges SET attempts=attempts+1 WHERE account_id=$1", [accountId]);
      if (!equal(codeHash(accountId, code), challenge.code_hash)) return null;
      await client.query("UPDATE partner_challenges SET used=true WHERE account_id=$1", [accountId]);
      const proof = crypto.randomBytes(32).toString("base64url");
      const expiresAt = new Date(now() + 1800000).toISOString();
      await client.query("DELETE FROM partner_admin_sessions WHERE account_id=$1 OR expires_at<=$2", [accountId, iso()]);
      await client.query("INSERT INTO partner_admin_sessions(token_hash,account_id,expires_at) VALUES($1,$2,$3)", [hash(proof), accountId, expiresAt]);
      return { proof, expiresAt };
    });
    if (!result) fail(403, "Invalid or expired verification code");
    return result;
  }
  async function overview(identity, proof) {
    return transaction(async (client) => {
      await requireAdmin(client, identity, proof);
      const { rows } = await client.query("SELECT * FROM partner_organizations WHERE owner_id=$1", [identityId(identity)]);
      if (!rows[0]) return { organization: null, plans: domain.PLANS };
      return { organization: publicState(hydrateRow(rows[0]), await paymentState(client, rows[0])) };
    });
  }
  async function createOrganization(identity, proof, name) {
    return transaction(async (client) => {
      await requireAdmin(client, identity, proof);
      const state = domain.newState(name);
      const organizationId = crypto.randomUUID();
      domain.audit(state, identity.email, "organization-created", organizationId, iso());
      const { rows } = await client.query("INSERT INTO partner_organizations(id,owner_id,billing_email_hash,state) VALUES($1,$2,$3,$4) ON CONFLICT DO NOTHING RETURNING *", [organizationId, identityId(identity), hash(email(identity.email)), JSON.stringify(state)]);
      if (!rows[0]) fail(409, "This account already has a Partner organization");
      return { organization: publicState(rows[0], await paymentState(client, rows[0])) };
    });
  }
  async function storeInvitation(client, row, customer) {
    const token = crypto.randomBytes(32).toString("base64url");
    await client.query("DELETE FROM partner_invitations WHERE organization_id=$1 AND customer_id=$2", [row.id, customer.id]);
    await client.query("INSERT INTO partner_invitations(token_hash,organization_id,customer_id,email_hash,expires_at) VALUES($1,$2,$3,$4,$5)",
      [hash(token), row.id, customer.id, hash(customer.email), new Date(now() + 7 * 86400000).toISOString()]);
    // Token in the fragment: not in server access logs or the HTTP Referer.
    return { to: customer.email, organizationName: row.state.name, url: `${appUrl}/partner/join#${token}` };
  }
  async function sendInvitation(invitation) {
    await sendEmail(invitation.to, "Your ARVIO invitation", `${invitation.organizationName} invited you to their ARVIO managed service. Review and accept using your own ARVIO account:\n\n${invitation.url}\n\nThis invitation expires in 7 days. Your personal account and watch history remain yours.`, undefined);
  }
  async function command(identity, proof, organizationId, revision, input) {
    if (!input || typeof input !== "object" || Array.isArray(input)) fail(400, "Invalid command");
    const result = await transaction(async (client) => {
      await requireAdmin(client, identity, proof);
      const row = await owner(client, identity, organizationId, true);
      if (row.revision !== revision) fail(409, "This organization changed in another session. Refresh before saving.");
      const entitlement = await paymentState(client, row);
      let state; let target; let invitation;
      if (input.action === "resend-invitation") {
        state = structuredClone(row.state);
        const customer = domain.find(state.customers, input.id);
        if (customer.status !== "invited") fail(409, "This invitation is no longer pending");
        if (entitlement.status !== "active") fail(402, "An active membership is required");
        const last = [...state.audit].reverse().find((a) => a.target === customer.id && ["invite-customer", "resend-invitation"].includes(a.action));
        if (last && now() - Date.parse(last.at) < 60000) fail(429, "Wait one minute before resending an invitation");
        target = customer.id;
        domain.audit(state, identity.email, input.action, target, iso());
        invitation = await storeInvitation(client, row, customer);
      } else {
        ({ state, target } = domain.mutate(row.state, input, { organizationId, actor: identity.email, entitlement, master, now: now() }));
        if (input.action === "invite-customer") invitation = await storeInvitation(client, row, domain.find(state.customers, target));
        if (input.action === "set-customer-status" && input.status === "removed") {
          await client.query("DELETE FROM partner_memberships WHERE organization_id=$1 AND customer_id=$2", [organizationId, target]);
          await client.query("DELETE FROM partner_invitations WHERE organization_id=$1 AND customer_id=$2", [organizationId, target]);
        }
      }
      const saved = await save(client, row, state);
      return { organization: publicState(saved, entitlement), target, invitation };
    });
    let invitationSent;
    if (result.invitation) {
      try { await sendInvitation(result.invitation); invitationSent = true; }
      catch { invitationSent = false; }
    }
    return { organization: result.organization, target: result.target, ...(invitationSent === undefined ? {} : { invitationSent }) };
  }

  async function invitation(identity, token, accept = false) {
    if (typeof token !== "string" || !/^[\w-]{43}$/.test(token)) fail(400, "Invalid invitation");
    return transaction(async (client) => {
      // Lock organization before invitation, matching the administrator path.
      const first = await client.query("SELECT organization_id FROM partner_invitations WHERE token_hash=$1", [hash(token)]);
      if (!first.rows[0]) fail(404, "Invitation not found or already used");
      const org = await client.query("SELECT * FROM partner_organizations WHERE id=$1 FOR UPDATE", [first.rows[0].organization_id]);
      const row = hydrateRow(org.rows[0]);
      if (row && !ownerAllowed(row.billing_email_hash)) fail(403, "This organization is not part of the private pilot");
      const { rows } = await client.query("SELECT * FROM partner_invitations WHERE token_hash=$1 FOR UPDATE", [hash(token)]);
      const invite = rows[0];
      if (!row || !invite || Date.parse(invite.expires_at) <= now()) fail(410, "Invitation expired. Ask your administrator for another invitation.");
      if (hash(email(identity.email)) !== invite.email_hash) fail(403, "Sign in with the email address this invitation was sent to");
      const customer = domain.find(row.state.customers, invite.customer_id);
      if (customer.status !== "invited") fail(409, "Invitation is no longer active");
      const entitlement = await paymentState(client, row);
      if (!["active", "grace"].includes(entitlement.status) || domain.activeSeats(row.state) > entitlement.seats) fail(402, "This organization has no available paid access");
      if (!accept) return { name: row.state.name, permissions: customer.delivery?.permissions, packageName: row.state.packages.find((p) => p.id === customer.packageId)?.name || null };
      const linked = await client.query("INSERT INTO partner_memberships(account_id,organization_id,customer_id) VALUES($1,$2,$3) ON CONFLICT DO NOTHING RETURNING account_id", [identityId(identity), row.id, customer.id]);
      if (!linked.rows.length) fail(409, "This account already belongs to a managed organization. Leave it before accepting another invitation.");
      customer.status = "active"; customer.acceptedAt = iso();
      domain.audit(row.state, identity.email, "invitation-accepted", customer.id, iso());
      await save(client, row, row.state);
      await client.query("DELETE FROM partner_invitations WHERE token_hash=$1", [hash(token)]);
      return { accepted: true, organizationName: row.state.name };
    });
  }

  async function member(client, identity) {
    const found = await client.query("SELECT organization_id, customer_id FROM partner_memberships WHERE account_id=$1", [identityId(identity)]);
    if (!found.rows.length) return null;
    const link = found.rows[0];
    const org = await client.query("SELECT * FROM partner_organizations WHERE id=$1 FOR UPDATE", [link.organization_id]);
    if (!org.rows[0]) return null;
    // Recheck after the lock in case removal happened between the two reads.
    const linked = await client.query("SELECT customer_id FROM partner_memberships WHERE account_id=$1 AND organization_id=$2", [identityId(identity), link.organization_id]);
    if (!linked.rows[0]) return null;
    const row = hydrateRow(org.rows[0]);
    if (!ownerAllowed(row.billing_email_hash)) return null;
    return { row, customer: domain.find(row.state.customers, linked.rows[0].customer_id), entitlement: await paymentState(client, row) };
  }

  async function delivery(identity, deviceInput) {
    if (!deviceInput || typeof deviceInput !== "object" || Array.isArray(deviceInput)) fail(400, "Device information is required");
    return transaction(async (client) => {
      const found = await member(client, identity);
      if (!found) return { managed: false };
      const { row, customer, entitlement } = found;
      const hasSeat = row.state.customers.filter((c) => ["active", "invited"].includes(c.status)).slice(0, entitlement.seats).some((c) => c.id === customer.id);
      const active = customer.status === "active" && ["active", "grace"].includes(entitlement.status) && hasSeat;
      if (!active) return { managed: true, active: false, name: row.state.name, reason: customer.status !== "active" ? "customer_suspended" : !hasSeat && entitlement.seats > 0 ? "seat_limit" : "membership_expired" };
      const key = text(deviceInput.key, 128);
      if (!/^[\w-]{32,128}$/.test(key)) fail(400, "Invalid device key");
      const keyHash = hash(`${identityId(identity)}:${key}`);
      let device = row.state.devices.find((d) => d.customerId === customer.id && d.keyHash === keyHash);
      if (device?.revokedAt) fail(403, "This device's managed access has been revoked");
      if (!device) {
        if (row.state.devices.filter((d) => d.customerId === customer.id && !d.revokedAt).length >= 10) fail(409, "Device limit reached. Ask your administrator to review registered devices.");
        device = { id: crypto.randomUUID(), customerId: customer.id, keyHash, name: text(deviceInput.name, 80), platform: text(deviceInput.platform, 30), registeredAt: iso(), appliedRevision: 0 };
        row.state.devices.push(device);
        domain.audit(row.state, identity.email, "device-registered", device.id, iso());
      }
      device.lastSeenAt = iso();
      if (Number.isInteger(deviceInput.appliedRevision) && deviceInput.appliedRevision <= (customer.delivery?.revision || 0)) {
        device.appliedRevision = Math.max(device.appliedRevision, deviceInput.appliedRevision);
      }
      const current = customer.delivery;
      const services = [];
      for (const serviceId of current?.serviceIds || []) {
        const service = current.serviceSnapshots?.find((s) => s.id === serviceId);
        if (!service) continue;
        const configuration = domain.unseal(service.connection, row.id, `service:${service.id}`, master);
        const credentials = customer.credentials[service.id]
          ? domain.unseal(customer.credentials[service.id], row.id, `customer:${customer.id}:${service.id}`, master) : null;
        services.push({ id: service.id, name: service.name, kind: service.kind, credentialMode: service.credentialMode, ...(service.kind === "iptv" ? { iptv: domain.iptvOptions(service.iptv) } : {}), ...configuration, credentials });
      }
      // Device check-ins don't change the administrator edit revision.
      await client.query("UPDATE partner_organizations SET state=$2 WHERE id=$1", [row.id, JSON.stringify(domain.packState(row.state))]);
      return {
        managed: true, active: true, name: row.state.name, organizationId: row.id,
        customerId: customer.id, deviceId: device.id, revision: current?.revision || 0,
        leaseUntil: new Date(Math.min(now() + 86400000, Date.parse(entitlement.graceUntil))).toISOString(),
        config: current?.config || domain.validateConfig({}), permissions: current?.permissions || domain.permissions(), services
      };
    });
  }
  async function entitlement(identity) {
    return transaction(async (client) => {
      const found = await member(client, identity);
      if (!found || found.customer.status !== "active") return null;
      const { row, customer, entitlement: paid } = found;
      if (!["active", "grace"].includes(paid.status)) return null;
      const hasSeat = row.state.customers.filter((c) => ["active", "invited"].includes(c.status)).slice(0, paid.seats).some((c) => c.id === customer.id);
      if (!hasSeat) return null;
      return { entitled: true, status: paid.status, reason: "partner", source: "partner", plan: "partner", expiresAt: paid.paidUntil, graceEndsAt: paid.graceUntil, organizationName: row.state.name, trialAvailable: false, trialDurationDays: 0 };
    });
  }
  async function membership(identity) {
    return transaction(async (client) => {
      const found = await member(client, identity);
      if (!found) return { managed: false };
      const { row, customer, entitlement: paid } = found;
      const hasSeat = row.state.customers.filter(c => ["active", "invited"].includes(c.status)).slice(0, paid.seats).some(c => c.id === customer.id);
      return {
        managed: true, name: row.state.name, status: customer.status,
        active: customer.status === "active" && ["active", "grace"].includes(paid.status) && hasSeat,
        billingStatus: paid.status, permissions: customer.delivery?.permissions || domain.permissions(),
        revision: customer.delivery?.revision || 0,
        services: (customer.delivery?.serviceSnapshots || []).map((s) => ({
          id: s.id, name: s.name, kind: s.kind, credentialMode: s.credentialMode,
          ...(s.kind === "iptv" ? { iptv: domain.iptvOptions(s.iptv) } : {}),
          credentialsSupplied: !!customer.credentials[s.id]
        }))
      };
    });
  }
  async function customerCredentials(identity, serviceId, input) {
    return transaction(async (client) => {
      const found = await member(client, identity);
      if (!found || found.customer.status !== "active" || !["active", "grace"].includes(found.entitlement.status)) fail(403, "An active managed membership is required");
      const { row, customer } = found;
      if (!row.state.customers.filter(c => ["active", "invited"].includes(c.status)).slice(0, found.entitlement.seats).some(c => c.id === customer.id)) fail(403, "No paid seat is available for this account");
      const service = domain.find(customer.delivery?.serviceSnapshots || [], serviceId);
      if (service.credentialMode !== "customer") fail(403, "This connection is configured by your administrator");
      const allowed = service.kind === "iptv" ? ["username", "password", "playlistUrl", "epgUrl"] : service.kind === "addon" ? ["manifestUrl"] : service.kind === "plex" ? ["token"] : ["username", "password"];
      if (!input || typeof input !== "object" || Array.isArray(input) || Object.keys(input).some((key) => !allowed.includes(key))) fail(400, "Invalid connection credentials");
      const credentials = service.kind === "iptv" ? domain.iptvCredentials(input, service) : Object.fromEntries(allowed.map((key) => [key, key === "password" ? domain.credentialText(input[key], true) : text(input[key], 4096)]));
      if (credentials.manifestUrl) credentials.manifestUrl = domain.url(credentials.manifestUrl);
      customer.credentials[service.id] = domain.seal(credentials, row.id, `customer:${customer.id}:${service.id}`, master);
      domain.republishCurrent(customer, iso());
      domain.audit(row.state, identity.email, "customer-credentials-updated", customer.id, iso());
      await save(client, row, row.state);
      return { saved: true, revision: customer.delivery.revision };
    });
  }
  async function leave(identity) {
    return transaction(async (client) => {
      const found = await member(client, identity);
      if (!found) return { left: true };
      const { row, customer } = found;
      customer.status = "removed"; customer.credentials = {}; customer.history = []; customer.delivery = null; customer.email = "";
      row.state.devices.filter((d) => d.customerId === customer.id).forEach((d) => { d.revokedAt = iso(); });
      domain.audit(row.state, identity.email, "customer-left", customer.id, iso());
      await save(client, row, row.state);
      await client.query("DELETE FROM partner_memberships WHERE account_id=$1", [identityId(identity)]);
      return { left: true };
    });
  }
  async function payment(data) {
    if (allowedOwnerHashes !== null) fail(503, "Partner sales are not open during the private pilot");
    const parsed = domain.parsePayment(data, now());
    if (!parsed) return { handled: false };
    const result = await pool.query("INSERT INTO partner_payments(transaction_id,email_hash,tier,amount_cents,paid_at,paid_until) VALUES($1,$2,$3,$4,$5,$6) ON CONFLICT DO NOTHING RETURNING transaction_id",
      [parsed.transactionId, parsed.emailHash, parsed.tier, parsed.cents, parsed.paidAt, parsed.paidUntil]);
    return { handled: true, duplicate: result.rows.length === 0 };
  }
  async function reconcile(transactionId, reason, revoked = true) {
    const result = await pool.query("UPDATE partner_payments SET revoked_at=$2,reconciliation_reason=$3 WHERE transaction_id=$1 RETURNING transaction_id", [text(transactionId, 150), revoked ? iso() : null, text(reason, 500)]);
    if (!result.rows.length) fail(404, "Payment not found");
    return { reconciled: true };
  }
  return { beginVerification, verify, overview, createOrganization, command, invitation, delivery, entitlement, membership, customerCredentials, leave, payment, reconcile };
}

module.exports = { createService };

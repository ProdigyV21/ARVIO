// POST /entitlement-link { kofiEmail, code? }. Different billing emails require
// a short-lived ownership challenge before an active membership can be linked.
// For users whose Ko-fi/PayPal email differs from their ARVIO account email:
// copy an ACTIVE entitlement found under the Ko-fi email onto the signed-in
// account's email. Auth: the account's ARVIO access token (so a user can only
// attach an entitlement TO their own account). We only copy if the Ko-fi email
// actually has a live paid entitlement — no way to fabricate access.
const { randomBytes, timingSafeEqual } = require("node:crypto");
const { json, options, parseBody, resolveIdentity, normalizeEmail, sha256, privacyHash, sendTransactionalEmail } = require("./_backend");
const { recordPremiumEvent } = require("./_premium-funnel");
const {
  entitlementsStore,
  readEntitlement,
  writeEntitlement,
  evaluateEntitlement
} = require("./_entitlements");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });

  let identity;
  try {
    identity = await resolveIdentity(event);
  } catch (error) {
    return json(401, { error: "unauthorized", message: error.message });
  }

  const accountEmail = normalizeEmail(identity.email);
  if (!accountEmail) return json(400, { error: "no_email_on_account" });

  let body = {};
  try { body = parseBody(event); } catch { body = {}; }
  if (!body || typeof body !== "object" || Array.isArray(body)) body = {};
  const kofiEmail = normalizeEmail(body.kofiEmail);
  if (!kofiEmail || kofiEmail.length > 254 || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(kofiEmail)) return json(400, { error: "missing_kofi_email" });

  try {
    const store = entitlementsStore(event);
    const accountHash = sha256(accountEmail);

    // Same email → nothing to link; the webhook already targets this account.
    if (kofiEmail === accountEmail) {
      const current = evaluateEntitlement(await readEntitlement(store, accountHash));
      return json(200, { ...current, linked: false, note: "emails_match" });
    }

    const kofiHash = sha256(kofiEmail);
    const proofKey = `link-proof/${accountHash}/${kofiHash}`;
    const proof = await store.get(proofKey, { type: "json", consistency: "strong" });
    const code = String(body.code || "").trim().toLowerCase();
    if (!code) {
      const accountSlot = `link-mail-account/${accountHash}/${Math.floor(Date.now() / 300_000)}`;
      const accountClaim = await store.setJSON(accountSlot, { at: Date.now() }, { onlyIfNew: true });
      if (!accountClaim.modified) return json(429, { error: "Please wait five minutes before requesting another code." });
      // Atomic per-address time bucket prevents repeated requests from sending
      // email concurrently. The response does not reveal subscription status.
      const slot = `link-mail/${kofiHash}/${Math.floor(Date.now() / 300_000)}`;
      const claimed = await store.setJSON(slot, { at: Date.now() }, { onlyIfNew: true });
      if (!claimed.modified) return json(429, { error: "Please wait five minutes before requesting another code." });
      const generated = randomBytes(8).toString("hex");
      await store.setJSON(proofKey, { digest: sha256(generated), expiresAt: Date.now() + 15 * 60_000 });
      await sendTransactionalEmail(kofiEmail, "Verify your ARVIO Premium email",
        `Your verification code is ${generated}. It expires in 15 minutes. Only enter this in ARVIO if you requested to link this billing email. Do not share it.`,
        `<p>Your ARVIO Premium verification code:</p><p><strong>${generated}</strong></p><p>Expires in 15 minutes. Only enter this in ARVIO if you requested it. Do not share it.</p>`);
      return json(202, { verificationRequired: true });
    }
    if (!/^[a-f0-9]{16}$/.test(code) || !proof?.digest || proof.expiresAt < Date.now() ||
        !timingSafeEqual(Buffer.from(sha256(code)), Buffer.from(proof.digest))) {
      return json(400, { error: "Invalid or expired verification code." });
    }
    const kofiRecord = await readEntitlement(store, kofiHash);
    const kofiState = evaluateEntitlement(kofiRecord);
    if (!kofiState.entitled || kofiState.reason === "trial") {
      return json(404, { error: "no_active_entitlement_for_kofi_email" });
    }

    // A paid billing identity can belong to only one ARVIO account. Conditional
    // creation prevents simultaneous verified links from claiming it twice.
    const ownerKey = `link-owner/${kofiHash}`;
    const claimed = await store.setJSON(ownerKey, { accountHash }, { onlyIfNew: true });
    if (!claimed.modified) {
      const owner = await store.get(ownerKey, { type: "json", consistency: "strong" });
      if (owner?.accountHash !== accountHash) return json(409, { error: "This membership is already linked to another account. Contact support to transfer it." });
    }

    // Merge the paid entitlement onto the account email. Keep whichever record
    // is already richer (e.g. don't downgrade a lifetime).
    const existing = await readEntitlement(store, accountHash);
    const merged = {
      ...kofiRecord,
      linkedFrom: kofiEmail,
      updatedAt: new Date().toISOString()
    };
    // Preserve an existing later expiry / lifetime on the account.
    if (existing) {
      const existingExp = existing.expiresAt ? Date.parse(existing.expiresAt) : Infinity;
      const kofiExp = kofiRecord.expiresAt ? Date.parse(kofiRecord.expiresAt) : Infinity;
      const existingState = evaluateEntitlement(existing);
      if (existingState.entitled && existingState.reason !== "trial" && existingExp >= kofiExp) {
        merged.expiresAt = existing.expiresAt;
        merged.source = existing.source;
        merged.tier = existing.tier;
      }
      merged.trialUsed = existing.trialUsed === true || kofiRecord.trialUsed === true;
    }
    await writeEntitlement(store, accountHash, merged);
    await store.delete(proofKey);
    await recordPremiumEvent(event, {
      email: accountEmail,
      eventName: "billing_email_verified",
      metadata: { billing_key: privacyHash("premium-funnel-account", kofiEmail) }
    }).catch(() => console.error("Verified billing analytics temporarily unavailable"));
    return json(200, { ...evaluateEntitlement(merged), linked: true });
  } catch (error) {
    console.error("entitlement-link failed", { name: error.name });
    return json(503, { error: "Membership linking is temporarily unavailable. Please try again." });
  }
};

// POST /entitlement-admin  { email, action, days?, tier? }
// Manually grant/revoke web access — founder deals, lifetime perks, comp'd
// accounts, testing. Protected by ADMIN_SECRET (sent as x-admin-secret header).
// action: "grant" (days=null → lifetime), "revoke", or "get".
const { json, options, normalizeEmail, sha256 } = require("./_backend");
const {
  entitlementsStore,
  readEntitlement,
  writeEntitlement,
  evaluateEntitlement,
  buildPaidRecord
} = require("./_entitlements");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });

  const secret = process.env.ADMIN_SECRET || "";
  if (!secret || event.headers["x-admin-secret"] !== secret) {
    return json(401, { error: "unauthorized" });
  }

  let body = {};
  try { body = JSON.parse(event.body || "{}"); } catch { body = {}; }
  const email = normalizeEmail(body.email);
  if (!email) return json(400, { error: "missing_email" });
  const action = String(body.action || "get");

  try {
    const store = entitlementsStore(event);
    const emailHash = sha256(email);
    const existing = await readEntitlement(store, emailHash);

    if (action === "get") {
      return json(200, { email, ...evaluateEntitlement(existing) });
    }
    if (action === "revoke") {
      const record = {
        ...(existing || {}),
        status: "cancelled",
        source: existing ? existing.source : "manual",
        expiresAt: new Date().toISOString(),
        lastEvent: "admin-revoked",
        updatedAt: new Date().toISOString()
      };
      await writeEntitlement(store, emailHash, record);
      return json(200, { email, ...evaluateEntitlement(record) });
    }
    if (action === "grant") {
      const days = body.days === null || body.days === undefined ? null : Number(body.days);
      // An admin grant sets the access window from now (it does not stack on
      // remaining time the way payments do).
      const record = buildPaidRecord(existing ? { ...existing, status: "admin-reset", expiresAt: undefined } : null, {
        source: body.source || "manual",
        tier: body.tier || null,
        days,
        event: "admin-granted"
      });
      await writeEntitlement(store, emailHash, record);
      return json(200, { email, ...evaluateEntitlement(record) });
    }
    return json(400, { error: "unknown_action" });
  } catch (error) {
    console.error("entitlement-admin failed", error);
    return json(500, { error: "entitlement_admin_failed", message: error.message });
  }
};

// POST /kofi-webhook  ← Ko-fi calls this on every payment.
// Ko-fi sends application/x-www-form-urlencoded with a single `data` field
// containing a JSON string. We verify Ko-fi's verification_token against our
// secret, then grant/renew web access for the buyer's email.
//
// Two ways to pay:
//   - Monthly membership (type "Subscription"): one calendar month per payment
//     (in step with Ko-fi's billing date; a late renewal is covered by the
//     3-day grace). Set KOFI_MEMBERSHIP_MONTHS=0 to use KOFI_MEMBERSHIP_DAYS.
//   - 12-month pass (type "Shop Order" for one of KOFI_YEARLY_ITEM_CODES, the
//     Ko-fi Shop item's direct link code(s), comma separated): 366 days per
//     pass bought.
// Either way the new time is added on top of any paid time still ahead.
//
// Env required: KOFI_VERIFICATION_TOKEN (from Ko-fi → Settings → Webhooks/API).
// Optional:     KOFI_MEMBERSHIP_DAYS, KOFI_YEARLY_ITEM_CODES, KOFI_YEARLY_URL
//               (used in emails).
const { json, options, normalizeEmail, sha256 } = require("./_backend");
const {
  entitlementsStore,
  updateEntitlement,
  buildPaidRecord
} = require("./_entitlements");
const { recordPremiumEvent } = require("./_premium-funnel");
const { queueForPayment } = require("./_member-emails");
const { rewardReferralIfDue } = require("./_referrals");
const { PLANS: PARTNER_PLANS, equal: constantTimeEqual } = require("./_partner-domain");

const PASS_DAYS = 366;
const MAX_REMEMBERED_PAYMENTS = 40;

function parseKofiData(event) {
  // Ko-fi posts form-urlencoded: data=<url-encoded JSON string>.
  const raw = event.isBase64Encoded ? Buffer.from(event.body || "", "base64").toString("utf8") : (event.body || "");
  const params = new URLSearchParams(raw);
  const dataField = params.get("data");
  if (!dataField) {
    // Some setups post raw JSON; tolerate that too.
    try { return JSON.parse(raw); } catch { return null; }
  }
  try { return JSON.parse(dataField); } catch { return null; }
}

function yearlyItemCodes() {
  return new Set(String(process.env.KOFI_YEARLY_ITEM_CODES || "")
    .split(",").map((code) => code.trim().toLowerCase()).filter(Boolean));
}

// How many 12-month passes a Shop Order contains (0 when it is something else).
function passesInOrder(data) {
  const codes = yearlyItemCodes();
  if (!codes.size || !Array.isArray(data.shop_items)) return 0;
  return data.shop_items.reduce((total, item) => {
    if (!item || !codes.has(String(item.direct_link_code || "").trim().toLowerCase())) return total;
    const quantity = Math.floor(Number(item.quantity || 1));
    return total + (Number.isFinite(quantity) && quantity > 0 ? Math.min(quantity, 5) : 1);
  }, 0);
}

function paymentId(data) {
  return String(data.kofi_transaction_id || data.message_id || "").trim().slice(0, 100) || null;
}

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });

  const expectedToken = process.env.KOFI_VERIFICATION_TOKEN || "";
  if (!expectedToken) {
    console.error("kofi-webhook: KOFI_VERIFICATION_TOKEN not configured");
    return json(500, { error: "not_configured" });
  }

  const data = parseKofiData(event);
  if (!data || typeof data !== "object") {
    return json(400, { error: "bad_payload" });
  }

  // Verify this really came from OUR Ko-fi account.
  if (!constantTimeEqual(String(data.verification_token || ""), expectedToken)) {
    return json(401, { error: "invalid_verification_token" });
  }

  // Same Ko-fi account, separate products. Never turn a Partner payment into
  // a consumer membership, including accidentally renamed Partner tiers.
  if (Object.hasOwn(PARTNER_PLANS, data.tier_name) || /^arvio\s+partner\b/i.test(String(data.tier_name || ""))) {
    if (!Object.hasOwn(PARTNER_PLANS, data.tier_name)) return json(400, { error: "unknown_partner_tier" });
    if (process.env.PARTNER_ENABLED !== "true") return json(503, { error: "partner_not_ready" });
    try {
      const { getPool } = require("./_partner-database");
      const { createService } = require("./_partner-service");
      return json(200, { ok: true, ...await createService({ pool: getPool() }).payment(data) });
    } catch (error) {
      return json(error.statusCode || 500, { error: error.statusCode ? error.message : "partner_payment_failed" });
    }
  }

  const email = normalizeEmail(data.email);
  if (!email) {
    // No email to attach the entitlement to — accept (200) so Ko-fi doesn't
    // retry forever, but record nothing.
    return json(200, { ok: true, ignored: "no_email" });
  }

  // Only memberships and the 12-month pass grant access. A one-off tip or any
  // other shop item does not.
  const type = String(data.type || "").toLowerCase();
  const isSubscription = data.is_subscription_payment === true || type === "subscription";
  const passes = type === "shop order" ? passesInOrder(data) : 0;
  if (!isSubscription && !passes) {
    if (type === "shop order") {
      // A shop order we did not recognise. If it was meant to be the pass
      // (wrong or missing KOFI_YEARLY_ITEM_CODES), keep a record so the buyer
      // can be granted by hand instead of silently getting nothing.
      const codes = (Array.isArray(data.shop_items) ? data.shop_items : []).map((item) => String(item && item.direct_link_code || "").slice(0, 40));
      console.error("kofi-webhook: shop order not matched to the 12-month pass", { codes, configured: yearlyItemCodes().size });
      try {
        await entitlementsStore(event).setJSON(`unmatched-shop-orders/${paymentId(data) || Date.now()}.json`, {
          at: new Date().toISOString(), codes, emailHash: sha256(email), amount: String(data.amount || ""), currency: String(data.currency || "")
        });
      } catch (error) { console.error("unmatched shop order not stored", error && error.message); }
    }
    return json(200, { ok: true, ignored: "not_membership", type });
  }
  const plan = passes ? "yearly" : "monthly";
  const isFirstSub = isSubscription && data.is_first_subscription_payment === true;

  try {
    const store = entitlementsStore(event);
    const emailHash = sha256(email);
    const id = paymentId(data);
    let duplicate = false;
    let firstPayment = false;
    const monthsSetting = process.env.KOFI_MEMBERSHIP_MONTHS;
    const months = passes ? 0 : (monthsSetting === undefined || monthsSetting === "" ? 1 : Math.max(0, Math.floor(Number(monthsSetting) || 0)));
    const days = passes ? PASS_DAYS * passes : months ? null : Number(process.env.KOFI_MEMBERSHIP_DAYS || 31);
    // Conditional write: two deliveries of the same event racing each other
    // cannot both add time, and a concurrent bonus is never overwritten.
    const { record } = await updateEntitlement(store, emailHash, (existing) => {
      // Ko-fi retries a webhook it thinks failed. Paid time is added on top of
      // time already paid, so a repeated delivery must not grant twice.
      const processed = Array.isArray(existing?.payments) ? existing.payments : [];
      if (id && processed.includes(id)) { duplicate = true; return null; }
      firstPayment = !existing || existing.source === "trial" || !existing.startedAt;
      const next = buildPaidRecord(existing, {
        source: "kofi",
        tier: passes ? "12-month pass" : (data.tier_name || (existing && existing.tier) || null),
        plan,
        days,
        months,
        event: passes ? "kofi-pass-purchased" : isFirstSub ? "kofi-subscription-started" : "kofi-subscription-renewed"
      });
      if (id) next.payments = [...processed, id].slice(-MAX_REMEMBERED_PAYMENTS);
      return next;
    });
    if (duplicate) return json(200, { ok: true, duplicate: true, expiresAt: record?.expiresAt || null });

    await recordPremiumEvent(event, {
      email,
      eventName: passes || isFirstSub ? "subscription_started" : "subscription_renewed",
      metadata: { source: "kofi", tier: passes ? "12-month pass" : (data.tier_name || "membership") }
    }).catch((error) => console.error("kofi funnel recording failed", error));

    // Side effects that must never fail the payment itself.
    const reward = await rewardReferralIfDue(event, { emailHash, payerHash: emailHash })
      .catch((error) => {
        console.error("referral reward failed", error && error.message);
        return null;
      });
    const expiresAt = reward?.rewarded ? reward.expiresAt : record.expiresAt;
    await queueForPayment(event, { email, emailHash, plan, firstPayment: firstPayment || isFirstSub, record: { ...record, expiresAt } })
      .catch((error) => console.error("member emails not queued", error && error.message));

    return json(200, { ok: true, entitled: true, plan, expiresAt });
  } catch (error) {
    console.error("kofi-webhook failed", error);
    // 500 → Ko-fi will retry, which is what we want on a transient storage error.
    return json(500, { error: "kofi_webhook_failed", message: error.message });
  }
};

exports._test = { passesInOrder, paymentId, yearlyItemCodes };

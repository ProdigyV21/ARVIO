// GET  /entitlement-status  → the web app reads the signed-in account's web
//                             subscription status (paid / none).
// POST /entitlement-status  { action: "start-trial" } → legacy clients receive
//                             trial_unavailable; no trial access is created.
// Auth: the account's ARVIO access token (same as account-sync-*). The email is
// taken from the verified identity, so a user can only read THEIR account.
const { json, options, resolveIdentity, normalizeEmail, sha256 } = require("./_backend");
const {
  entitlementsStore,
  readEntitlement,
  writeEntitlement,
  evaluateEntitlement
} = require("./_entitlements");
const { recordPremiumEvent } = require("./_premium-funnel");
const { rewardReferralIfDue } = require("./_referrals");
const { partnerEntitlement } = require("./_partner-entitlement");

function requestAction(event) {
  let raw = event.body;
  if (event.isBase64Encoded && typeof raw === "string") {
    try { raw = Buffer.from(raw, "base64").toString("utf8"); } catch { raw = ""; }
  }

  let body = {};
  if (raw && typeof raw === "object" && !Array.isArray(raw)) {
    body = raw;
  } else if (typeof raw === "string" && raw.trim()) {
    try { body = JSON.parse(raw); } catch { body = {}; }
  }

  // This endpoint has one authenticated POST operation. Some browsers/proxies
  // have reached Netlify with an empty or encoded body, so keep those requests
  // backward-compatible so stale requests receive the same trial_unavailable
  // response rather than accidentally bypassing the paid-access policy.
  return String(body.action || "start-trial").trim().toLowerCase();
}

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "GET" && event.httpMethod !== "POST") {
    return json(405, { error: "method_not_allowed" });
  }

  let identity;
  try {
    identity = await resolveIdentity(event);
  } catch (error) {
    return json(401, { error: "unauthorized", message: error.message });
  }

  const email = normalizeEmail(identity.email);
  if (!email) return json(400, { error: "no_email_on_account" });
  const emailHash = sha256(email);

  try {
    const store = entitlementsStore(event);
    let record = await readEntitlement(store, emailHash);

    // Retain the old route for stale clients, without granting free access,
    // altering account records or sending trial email.
    if (event.httpMethod === "POST") {
      const action = requestAction(event);
      if (action === "start-trial") {
        const current = evaluateEntitlement(record);
        if (current.entitled) {
          return json(200, { ...current, alreadyEntitled: true });
        }
        return json(403, {
          error: "trial_unavailable",
          message: "ARVIO Web requires a paid Premium membership. Free trials are no longer available.",
          ...current
        });
      }
      return json(400, { error: "unknown_action" });
    }

    // Renewal sync for linked Ko-fi emails. The Ko-fi webhook renews the record
    // under the KO-FI email; an account that linked a different email holds a
    // COPY. On every read, take the billing record when it reaches further or
    // its plan changed (a renewal, a 12-month pass, a referral bonus), so a
    // linked member never sees "payment not received" after paying on time.
    let state = evaluateEntitlement(record);
    let payerHash = emailHash;
    if (record && record.linkedFrom) {
      const sourceEmail = normalizeEmail(record.linkedFrom);
      if (sourceEmail && sourceEmail !== email) {
        payerHash = sha256(sourceEmail);
        const source = await readEntitlement(store, payerHash);
        const sourceState = evaluateEntitlement(source);
        const end = (value) => (value && value.expiresAt === null ? Infinity : Date.parse(value?.expiresAt || "") || 0);
        if (sourceState.entitled && (!state.entitled || end(source) > end(record) || (source.plan || null) !== (record.plan || null))) {
          record = {
            ...source,
            linkedFrom: sourceEmail,
            trialUsed: record.trialUsed === true || source.trialUsed === true,
            updatedAt: new Date().toISOString()
          };
          await writeEntitlement(store, emailHash, record);
          state = evaluateEntitlement(record);
        }
      }
    }

    // Catch payments that arrived under a different, subsequently verified
    // billing email, and referrals still pending from before instant rewards.
    if (state.entitled && state.reason === "subscription" && record && record.source !== "referral") {
      const reward = await rewardReferralIfDue(event, { emailHash, payerHash }).catch((error) => {
        console.error("referral reward check failed", error && error.message);
        return null;
      });
      if (reward && reward.rewarded) {
        record = await readEntitlement(store, emailHash);
        if (record && record.linkedFrom) {
          const source = await readEntitlement(store, payerHash);
          if (source) {
            record = { ...source, linkedFrom: record.linkedFrom, trialUsed: record.trialUsed === true, updatedAt: new Date().toISOString() };
            await writeEntitlement(store, emailHash, record);
          }
        }
        state = evaluateEntitlement(record);
      }
    }

    if (state.entitled && state.reason === "subscription") {
      await recordPremiumEvent(event, { email, eventName: "paid_access_observed", metadata: { source: state.source || "unknown" } })
        .catch(() => console.error("Paid access analytics temporarily unavailable"));
    }
    if (!state.entitled) state = await partnerEntitlement(identity) || state;
    return json(200, state);
  } catch (error) {
    console.error("entitlement-status failed", error);
    return json(500, { error: "entitlement_status_failed", message: error.message });
  }
};

exports._test = { requestAction };

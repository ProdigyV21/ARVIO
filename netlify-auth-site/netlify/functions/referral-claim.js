// POST /referral-claim { code } → remember that this account was invited.
// The reward (31 days for both accounts) is granted later, on the invited
// account's first payment. Accounts that already paid cannot be invited.
const { json, options, parseBody, resolveIdentity, normalizeEmail, sha256 } = require("./_backend");
const { claimReferral } = require("./_referrals");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });
  let identity;
  try { identity = await resolveIdentity(event); } catch (error) { return json(401, { error: "unauthorized", message: error.message }); }
  const email = normalizeEmail(identity.email);
  if (!email) return json(400, { error: "no_email_on_account" });
  let body;
  try { body = parseBody(event); } catch { return json(400, { error: "invalid_json" }); }
  try {
    const result = await claimReferral(event, { emailHash: sha256(email), code: body && body.code });
    return json(result.ok ? 200 : 409, result);
  } catch (error) {
    console.error("referral-claim failed", error && error.message);
    return json(500, { error: "referral_claim_failed" });
  }
};

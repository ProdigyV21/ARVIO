// GET /referral-status → the signed-in account's invite link and rewards.
// Auth: the account's ARVIO access token (Authorization: Bearer ...).
const { json, options, resolveIdentity, normalizeEmail, sha256 } = require("./_backend");
const { referralStatus } = require("./_referrals");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "GET") return json(405, { error: "method_not_allowed" });
  let identity;
  try { identity = await resolveIdentity(event); } catch (error) { return json(401, { error: "unauthorized", message: error.message }); }
  const email = normalizeEmail(identity.email);
  if (!email) return json(400, { error: "no_email_on_account" });
  try {
    return json(200, await referralStatus(event, { email, emailHash: sha256(email) }));
  } catch (error) {
    console.error("referral-status failed", error && error.message);
    return json(500, { error: "referral_status_failed" });
  }
};

// POST /ai-subtitles-translate  { lines: string[], targetLanguage: string }
//   → { translations: string[], cached, translated, remainingLines }
//
// Hosted AI subtitles, included in ARVIO Premium: members translate subtitles into
// their own language without setting up a Groq or Gemini key. Auth is the account's
// ARVIO access token (same as entitlement-status); access follows the same Premium
// record, including a Ko-fi email linked to the account.
//
// Server configuration: GROQ_API_KEY and/or GEMINI_API_KEY (Netlify environment
// variables). Optional: AI_SUBTITLES_MONTHLY_LINES, AI_SUBTITLES_PER_MINUTE.
const { connectLambda, getStore } = require("@netlify/blobs");
const { json, options, parseBody, resolveIdentity, normalizeEmail, sha256 } = require("./_backend");
const { entitlementsStore, readEntitlement, evaluateEntitlement } = require("./_entitlements");
const { LIMITS, validateRequest, translateLines, checkUsage, recordUsage } = require("./_ai-subtitles");

function stores(event) {
  connectLambda(event);
  return { cache: getStore("ai-subtitles-cache"), usage: getStore("ai-subtitles-usage") };
}

/** Premium access for this account, following a linked Ko-fi email like entitlement-status does. */
async function isPremium(event, email) {
  const store = entitlementsStore(event);
  const record = await readEntitlement(store, sha256(email));
  if (evaluateEntitlement(record).entitled) return true;
  const linked = normalizeEmail(record && record.linkedFrom);
  if (!linked || linked === email) return false;
  return evaluateEntitlement(await readEntitlement(store, sha256(linked))).entitled;
}

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
  const email = normalizeEmail(identity.email);
  if (!email) return json(400, { error: "no_email_on_account" });

  let body;
  try { body = parseBody(event); } catch { return json(400, { error: "invalid_json" }); }
  const request = validateRequest(body);
  if (request.error) return json(400, { error: request.error, limits: { maxLines: LIMITS.maxLines, maxLineChars: LIMITS.maxLineChars } });

  try {
    if (!(await isPremium(event, email))) {
      return json(403, { error: "premium_required", message: "Hosted AI subtitles are included in ARVIO Premium." });
    }

    const { cache, usage } = stores(event);
    const emailHash = sha256(email);
    const quota = await checkUsage(usage, emailHash);
    if (!quota.allowed) {
      return json(429, { error: quota.reason, retryAfterSeconds: quota.retryAfterSeconds ?? null });
    }

    const result = await translateLines({
      lines: request.lines,
      language: request.language,
      cache,
      fetchImpl: fetch,
      keys: { groq: process.env.GROQ_API_KEY || "", gemini: process.env.GEMINI_API_KEY || "" }
    });
    const used = await recordUsage(usage, emailHash, quota.usedLines, result.translated);
    return json(200, { ...result, remainingLines: Math.max(0, LIMITS.monthlyLines - used) });
  } catch (error) {
    if (error && error.code === "not_configured") return json(503, { error: "not_configured" });
    console.error("ai-subtitles-translate failed", error && error.message);
    return json(502, { error: "translation_unavailable" });
  }
};

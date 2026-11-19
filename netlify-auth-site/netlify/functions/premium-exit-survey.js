// POST /premium-exit-survey { reason, detail? }  (ARVIO access token)
//   One answer per account per month to "What made you stop?", asked once in
//   ARVIO Web after a membership lapses. No address is stored: the key is a
//   keyed hash, and the free-text detail is capped at 300 characters.
// GET  /premium-exit-survey?months=6  (x-admin-secret header)
//   Counts per month and reason, plus the latest free-text answers.
const { connectLambda, getStore } = require("@netlify/blobs");
const { json, options, parseBody, resolveIdentity, normalizeEmail, privacyHash } = require("./_backend");

const REASONS = ["too_expensive", "playback_problems", "missing_feature", "only_needed_briefly", "use_android_instead", "other"];

function surveyStore(event) {
  connectLambda(event);
  return getStore("premium-exit-survey");
}

function cleanDetail(value) {
  return String(value || "").replace(/[\u0000-\u001f\u007f]/g, " ").replace(/\s+/g, " ").trim().slice(0, 300);
}

async function listKeys(store, prefix) {
  const keys = [];
  let cursor;
  do {
    const page = await store.list({ prefix, cursor });
    keys.push(...(page.blobs || []).map((blob) => blob.key));
    cursor = page.next_cursor || page.nextCursor || undefined;
  } while (cursor);
  return keys;
}

async function report(event, months) {
  const store = surveyStore(event);
  const now = new Date();
  const result = [];
  const details = [];
  for (let i = 0; i < months; i += 1) {
    const month = new Date(Date.UTC(now.getUTCFullYear(), now.getUTCMonth() - i, 1)).toISOString().slice(0, 7);
    const counts = Object.fromEntries(REASONS.map((reason) => [reason, 0]));
    for (const key of await listKeys(store, `responses/${month}/`)) {
      const answer = await store.get(key, { type: "json" }).catch(() => null);
      if (!answer || !REASONS.includes(answer.reason)) continue;
      counts[answer.reason] += 1;
      if (answer.detail) details.push({ month, reason: answer.reason, detail: answer.detail, at: answer.at });
    }
    result.push({ month, counts, total: Object.values(counts).reduce((a, b) => a + b, 0) });
  }
  details.sort((a, b) => String(b.at).localeCompare(String(a.at)));
  return { months: result, latestDetails: details.slice(0, 50) };
}

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;

  if (event.httpMethod === "GET") {
    const secret = process.env.ADMIN_SECRET || "";
    const header = (event.headers || {})["x-admin-secret"] || (event.headers || {})["X-Admin-Secret"];
    if (!secret || header !== secret) return json(401, { error: "unauthorized" });
    const months = Math.max(1, Math.min(24, Number((event.queryStringParameters || {}).months) || 6));
    try { return json(200, await report(event, months)); }
    catch (error) { console.error("exit survey report failed", error && error.message); return json(500, { error: "report_failed" }); }
  }

  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });
  let identity;
  try { identity = await resolveIdentity(event); } catch (error) { return json(401, { error: "unauthorized", message: error.message }); }
  const email = normalizeEmail(identity.email);
  if (!email) return json(400, { error: "no_email_on_account" });
  let body;
  try { body = parseBody(event); } catch { return json(400, { error: "invalid_json" }); }
  const reason = String(body && body.reason || "");
  if (!REASONS.includes(reason)) return json(400, { error: "invalid_reason", reasons: REASONS });
  const month = new Date().toISOString().slice(0, 7);
  try {
    await surveyStore(event).setJSON(`responses/${month}/${privacyHash("premium-exit-survey", email)}.json`, {
      reason,
      detail: cleanDetail(body.detail),
      at: new Date().toISOString()
    });
    return json(200, { ok: true });
  } catch (error) {
    console.error("exit survey save failed", error && error.message);
    return json(500, { error: "save_failed" });
  }
};

// Answers are kept for 24 months; older months are deleted by the worker.
async function cleanupExitSurvey(event, keepMonths = 24, scanMonths = 12) {
  const store = surveyStore(event);
  const now = new Date();
  let deleted = 0;
  for (let i = keepMonths; i < keepMonths + scanMonths; i += 1) {
    const month = new Date(Date.UTC(now.getUTCFullYear(), now.getUTCMonth() - i, 1)).toISOString().slice(0, 7);
    for (const key of await listKeys(store, `responses/${month}/`)) {
      await store.delete(key).then(() => { deleted += 1; }, () => {});
    }
  }
  return deleted;
}

exports.cleanupExitSurvey = cleanupExitSurvey;
exports._test = { REASONS, cleanDetail };

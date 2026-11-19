// Saves ONE Continue Watching progress change for one profile, merged into the
// stored account payload on the server. The client sends a few hundred bytes
// instead of downloading and re-uploading the whole account document, so the
// request fits fetch(..., { keepalive: true }) and can finish while an iPhone
// home-screen app or browser tab is being closed.
//
// POST body (JSON; Content-Type may be text/plain so a keepalive request needs
// no CORS preflight):
//   { "profileId": "p1", "op": "upsert", "item": { ...Android ContinueWatchingItem } }
//   { "profileId": "p1", "op": "remove", "match": { "id", "mediaType", "season?", "episode?" }, "updatedAtMs" }
// Auth: "Authorization: Bearer <access token>" exactly like account-sync-push,
// or the same token as "accessToken" in the body for header-less keepalive sends.
const {
  json,
  options,
  parseBody,
  payloadMetrics,
  resolveIdentity,
  loadSnapshotForUpdate,
  saveSnapshotToBlobs
} = require("./_backend");
const { normalizeProgressOperation, applyProgressOperation } = require("./_sync-merge");

const MAX_BODY_BYTES = 16 * 1024;

function storedPayload(snapshot) {
  const payload = snapshot?.payload;
  if (typeof payload !== "string") return payload && typeof payload === "object" && !Array.isArray(payload) ? payload : null;
  try {
    const parsed = JSON.parse(payload);
    return parsed && typeof parsed === "object" && !Array.isArray(parsed) ? parsed : null;
  } catch {
    return null;
  }
}

function withBodyToken(event, body) {
  const headers = event.headers || {};
  if (headers.authorization || headers.Authorization) return event;
  const token = typeof body?.accessToken === "string" ? body.accessToken.trim() : "";
  if (!token) return event;
  return { ...event, headers: { ...headers, authorization: `Bearer ${token}` } };
}

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });

  try {
    if (String(event.body || "").length > MAX_BODY_BYTES) {
      return json(413, { accepted: false, reason: "payload_too_large" });
    }
    let body;
    try {
      body = parseBody(event);
    } catch {
      return json(400, { accepted: false, reason: "invalid_json" });
    }
    const identity = await resolveIdentity(withBodyToken(event, body));
    let operation;
    try {
      operation = normalizeProgressOperation(body);
    } catch (error) {
      if (error?.statusCode === 400) return json(400, { accepted: false, reason: error.reason });
      throw error;
    }

    for (let attempt = 0; attempt < 5; attempt += 1) {
      const { snapshot, etag } = await loadSnapshotForUpdate(event, identity);
      const payload = storedPayload(snapshot);
      // Without an account document there is nothing to merge into; the client
      // falls back to its full-document write, which keeps today's first-sync rules.
      if (!payload) return json(409, { accepted: false, reason: "no_snapshot" });
      const result = applyProgressOperation(payload, operation);
      if (!result.applied) return json(200, { accepted: true, applied: false, reason: result.reason });
      const metrics = payloadMetrics(result.payload);
      const saved = await saveSnapshotToBlobs(event, identity, {
        payload: metrics.payload,
        payloadVersion: metrics.payloadVersion,
        restoreRank: metrics.restoreRank,
        profileCount: metrics.profileCount,
        scopedCoverage: metrics.scopedCoverage,
        payloadUpdatedAt: metrics.payloadUpdatedAt,
        source: "netlify"
      }, { etag });
      // Lost a race with another writer: re-read and re-apply on the newer snapshot.
      if (!saved) continue;
      // No appendSnapshotEvent here: those events store a full payload copy and
      // are not read by any client (account-sync-delta returns none), so a
      // checkpoint every few seconds would only multiply blob storage.
      return json(200, { accepted: true, applied: true });
    }
    return json(409, { accepted: false, reason: "snapshot_write_conflict" });
  } catch (error) {
    console.error("account-sync-progress failed", error);
    return json(error?.statusCode || 500, {
      accepted: false,
      error: "sync_progress_failed",
      message: error.message
    });
  }
};

exports._test = { withBodyToken, storedPayload };

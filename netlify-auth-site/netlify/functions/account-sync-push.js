const {
  json,
  options,
  parseBody,
  payloadMetrics,
  isExistingSnapshotRicher,
  resolveIdentity,
  loadSnapshotFromBlobs,
  saveSnapshotToBlobs,
  appendSnapshotEvent
} = require("./_backend");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") {
    return json(405, { error: "method_not_allowed" });
  }

  try {
    const identity = await resolveIdentity(event);
    const body = parseBody(event);
    const rawPayload = body.payload;
    if (!rawPayload) {
      return json(400, { accepted: false, reason: "missing_payload" });
    }

    const incoming = payloadMetrics(rawPayload);
    const existing = await loadSnapshotFromBlobs(event, identity);
    if (isExistingSnapshotRicher(existing, incoming)) {
      return json(200, {
        accepted: false,
        reason: "existing_snapshot_is_richer",
        existing,
        incoming: {
          restoreRank: incoming.restoreRank,
          profileCount: incoming.profileCount,
          scopedCoverage: incoming.scopedCoverage
        }
      });
    }

    const saved = await saveSnapshotToBlobs(event, identity, {
      payload: incoming.payload,
      payloadVersion: incoming.payloadVersion,
      restoreRank: incoming.restoreRank,
      profileCount: incoming.profileCount,
      scopedCoverage: incoming.scopedCoverage,
      payloadUpdatedAt: incoming.payloadUpdatedAt,
      source: "netlify"
    });
    await appendSnapshotEvent(event, identity, saved);

    return json(200, {
      accepted: true,
      restoreRank: incoming.restoreRank,
      profileCount: incoming.profileCount,
      scopedCoverage: incoming.scopedCoverage
    });
  } catch (error) {
    console.error("account-sync-push failed", error);
    return json(500, { accepted: false, error: "sync_push_failed", message: error.message });
  }
};

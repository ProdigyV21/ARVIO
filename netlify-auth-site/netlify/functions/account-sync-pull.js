const {
  json,
  options,
  resolveIdentity,
  loadSnapshotFromBlobs
} = require("./_backend");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "GET" && event.httpMethod !== "POST") {
    return json(405, { error: "method_not_allowed" });
  }

  try {
    const identity = await resolveIdentity(event);
    const snapshot = await loadSnapshotFromBlobs(event, identity);

    if (!snapshot) {
      return json(200, {
        payload: null,
        source: null,
        updatedAt: null,
        profileCount: null,
        restoreRank: 0,
        scopedCoverage: 0
      });
    }

    return json(200, {
      payload: snapshot.payload,
      source: snapshot.source,
      updatedAt: snapshot.updatedAt || snapshot.updated_at || snapshot.payloadUpdatedAt || null,
      payloadUpdatedAt: snapshot.payloadUpdatedAt || snapshot.payload_updated_at || null,
      profileCount: snapshot.profileCount ?? snapshot.profile_count ?? null,
      restoreRank: snapshot.restoreRank ?? snapshot.restore_rank ?? 0,
      scopedCoverage: snapshot.scopedCoverage ?? snapshot.scoped_coverage ?? 0
    });
  } catch (error) {
    console.error("account-sync-pull failed", error);
    return json(401, { error: "sync_pull_failed", message: error.message });
  }
};

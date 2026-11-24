const {
  json,
  options,
  resolveIdentity,
  loadSnapshotRevision
} = require("./_backend");

// `cursor` is the server clock and is kept for older clients. `revision` is what clients poll:
// it changes whenever the account document is saved, and costs a metadata read, not a download.
exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;

  try {
    const identity = await resolveIdentity(event);
    // A failed revision read must not fail the call: clients then just skip the change check.
    const revision = await loadSnapshotRevision(event, identity).catch((error) => {
      console.error("account-sync-cursor revision failed", error);
      return null;
    });
    return json(200, { cursor: Date.now(), revision });
  } catch (error) {
    console.error("account-sync-cursor failed", error);
    return json(401, { error: "cursor_failed", message: error.message });
  }
};

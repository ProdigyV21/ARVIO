const {
  json,
  options,
  resolveIdentity
} = require("./_backend");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;

  try {
    await resolveIdentity(event);
    return json(200, { cursor: Date.now() });
  } catch (error) {
    console.error("account-sync-cursor failed", error);
    return json(401, { error: "cursor_failed", message: error.message });
  }
};

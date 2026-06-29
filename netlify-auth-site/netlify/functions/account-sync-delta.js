const {
  json,
  options,
  parseBody,
  resolveIdentity
} = require("./_backend");

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;

  try {
    await resolveIdentity(event);
    const body = event.httpMethod === "POST" ? parseBody(event) : {};
    const since = Math.max(0, Number(body.sinceEventId || event.queryStringParameters?.sinceEventId || 0));
    return json(200, { events: [], cursor: Math.max(since, Date.now()) });
  } catch (error) {
    console.error("account-sync-delta failed", error);
    return json(401, { error: "delta_failed", message: error.message });
  }
};

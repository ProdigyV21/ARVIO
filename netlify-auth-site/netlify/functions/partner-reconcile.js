const { json, parseBody } = require("./_backend");
const { getPool } = require("./_partner-database");
const { equal } = require("./_partner-domain");
const { createService } = require("./_partner-service");

// Operator-only refund/dispute correction. Not callable with a partner's token.
exports.handler = async (event) => {
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });
  const supplied = Object.entries(event.headers || {}).find(([key]) => key.toLowerCase() === "x-admin-secret")?.[1];
  if (!process.env.ADMIN_SECRET || !supplied || !equal(supplied, process.env.ADMIN_SECRET)) return json(401, { error: "unauthorized" });
  try {
    const body = parseBody(event);
    return json(200, await createService({ pool: getPool() }).reconcile(body.transactionId, body.reason, body.revoked !== false));
  } catch (error) { return json(error.statusCode || 500, { error: error.statusCode ? error.message : "Reconciliation failed" }); }
};

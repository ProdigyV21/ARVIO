const { Pool } = require("pg");
let pool;

function getPool() {
  if (pool) return pool;
  const connectionString = process.env.PARTNER_DATABASE_URL;
  if (!connectionString) {
    throw Object.assign(new Error("Partner database is not configured"), { code: "PARTNER_DATABASE_NOT_CONFIGURED", statusCode: 503 });
  }
  pool = new Pool({ connectionString, max: 2, idleTimeoutMillis: 10000, connectionTimeoutMillis: 8000, statement_timeout: 12000 });
  return pool;
}

module.exports = { getPool };

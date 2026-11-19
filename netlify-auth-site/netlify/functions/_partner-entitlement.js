// Managed access supplements personal Premium. It never writes a personal
// entitlement, so leaving an organization cannot cancel an individual's plan.
async function partnerEntitlement(identity) {
  if (process.env.PARTNER_ENABLED !== "true") return null;
  const { getPool } = require("./_partner-database");
  const { createService } = require("./_partner-service");
  try {
    return await createService({ pool: getPool() }).entitlement(identity);
  } catch (error) {
    // Partner is supplemental. Fail closed without turning a normal consumer
    // entitlement response into a server error during a Partner outage.
    console.error("Partner entitlement unavailable", { code: error.code || "internal" });
    return null;
  }
}
module.exports = { partnerEntitlement };

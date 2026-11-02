const { json, options, parseBody, resolveIdentity, sendTransactionalEmail } = require("./_backend");
const { getPool } = require("./_partner-database");
const { createService } = require("./_partner-service");

exports.handler = async (event) => {
  const preflight = options(event);
  if (preflight) return preflight;
  if (event.httpMethod !== "POST") return json(405, { error: "method_not_allowed" });
  if (process.env.PARTNER_ENABLED !== "true") return json(503, { error: "Partner access is not available yet" });
  if (Buffer.byteLength(event.body || "", event.isBase64Encoded ? "base64" : "utf8") > 512000) return json(413, { error: "Request is too large" });
  if (!Object.entries(event.headers || {}).some(([key, value]) => key.toLowerCase() === "authorization" && /^Bearer\s+\S+/i.test(value))) return json(401, { error: "Sign in required" });
  try {
    const identity = await resolveIdentity(event);
    const body = parseBody(event);
    if (!body || typeof body !== "object" || Array.isArray(body)) return json(400, { error: "Expected a request object" });
    const service = createService({ pool: getPool(), sendEmail: sendTransactionalEmail, appUrl: process.env.PARTNER_APP_URL || "https://web.arvio.tv" });
    let result;
    switch (body.operation) {
      case "verification-start": result = await service.beginVerification(identity); break;
      case "verification-complete": result = await service.verify(identity, body.code); break;
      case "overview": result = await service.overview(identity, body.proof); break;
      case "create-organization": result = await service.createOrganization(identity, body.proof, body.name); break;
      case "command": result = await service.command(identity, body.proof, body.organizationId, body.revision, body.command); break;
      case "invitation": result = await service.invitation(identity, body.token, false); break;
      case "accept-invitation": {
        if (body.consent !== true) return json(400, { error: "Review and accept the managed configuration permissions first" });
        result = await service.invitation(identity, body.token, true); break;
      }
      case "delivery": result = await service.delivery(identity, body.device); break;
      case "membership": result = await service.membership(identity); break;
      case "customer-credentials": result = await service.customerCredentials(identity, body.serviceId, body.credentials); break;
      case "leave": {
        if (body.confirm !== true) return json(400, { error: "Confirm leaving the managed organization" });
        result = await service.leave(identity); break;
      }
      default: return json(400, { error: "Unknown partner operation" });
    }
    return json(200, result);
  } catch (error) {
    const status = Number(error.statusCode) || (error instanceof SyntaxError ? 400 : 500);
    // Connection strings, credentials and database details must not reach responses or logs.
    if (status >= 500) console.error("partner-api request failed", { code: error.code || "internal" });
    return json(status, { error: status >= 500 ? "Partner service is temporarily unavailable" : error.message });
  }
};

// GET /stream-ticket → { ticket, expiresAt } for an account with Premium.
//
// ARVIO Web attaches the ticket to the resolver worker's /media URLs, and the
// worker relays video only for valid, unexpired tickets, so the relay serves
// paying members and nobody else.
// v2 (default): ECDSA-signed with a key this site creates and keeps itself
// (_stream-keys.js); the worker verifies with the public key from
// /stream-ticket-key, so no shared secret has to be copied anywhere.
// v1 (legacy, only when STREAM_TICKET_SECRET is set): HMAC with a secret shared
// with the worker. Both are valid 14 days.
// The account tag is a short keyed hash for logs; it reveals no address.
const crypto = require("crypto");
const { json, options, resolveIdentity, normalizeEmail, sha256, privacyHash } = require("./_backend");
const { entitlementsStore, readEntitlement, evaluateEntitlement } = require("./_entitlements");
const { streamKeysStore, signTicketV2 } = require("./_stream-keys");
const { partnerEntitlement } = require("./_partner-entitlement");

// Long enough for a paused download or a tab left open for days; refreshed by
// the web app well before it runs out.
const TICKET_TTL_SECONDS = 14 * 24 * 60 * 60;
// Relay URLs are fixed for a playback session, including nested HLS requests.
// Allow normal long-form playback; revocation stops new tickets, while already
// issued Partner relay leases expire within a day (or the paid grace deadline).
const PARTNER_TICKET_TTL_SECONDS = 24 * 60 * 60;

function ticketLifetime(state, now = Date.now()) {
  if (state.source !== "partner") return TICKET_TTL_SECONDS;
  const deadline = Date.parse(state.graceEndsAt || state.expiresAt || "");
  if (!Number.isFinite(deadline)) return 0;
  return Math.max(0, Math.min(PARTNER_TICKET_TTL_SECONDS, Math.floor((deadline - now) / 1000)));
}

function ticketSecret() {
  const secret = process.env.STREAM_TICKET_SECRET || "";
  return secret.length >= 32 ? secret : "";
}

function signTicket(secret, accountTag, nowSeconds = Math.floor(Date.now() / 1000), ttlSeconds = TICKET_TTL_SECONDS) {
  const expiry = nowSeconds + ttlSeconds;
  const body = `v1.${expiry}.${accountTag}`;
  const signature = crypto.createHmac("sha256", secret).update(body).digest("base64url");
  return { ticket: `${body}.${signature}`, expiresAt: new Date(expiry * 1000).toISOString() };
}

function verifyTicket(secret, ticket, nowSeconds = Math.floor(Date.now() / 1000)) {
  const parts = String(ticket || "").split(".");
  if (parts.length !== 4 || parts[0] !== "v1") return false;
  const expiry = Number(parts[1]);
  if (!Number.isInteger(expiry) || expiry < nowSeconds) return false;
  const expected = crypto.createHmac("sha256", secret).update(parts.slice(0, 3).join(".")).digest("base64url");
  const a = Buffer.from(expected);
  const b = Buffer.from(parts[3]);
  return a.length === b.length && crypto.timingSafeEqual(a, b);
}

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "GET") return json(405, { error: "method_not_allowed" });
  const secret = ticketSecret();
  let identity;
  try { identity = await resolveIdentity(event); } catch (error) { return json(401, { error: "unauthorized", message: error.message }); }
  const email = normalizeEmail(identity.email);
  if (!email) return json(400, { error: "no_email_on_account" });
  try {
    const store = entitlementsStore(event);
    const record = await readEntitlement(store, sha256(email));
    let state = evaluateEntitlement(record);
    if (!state.entitled && record && record.linkedFrom) {
      state = evaluateEntitlement(await readEntitlement(store, sha256(normalizeEmail(record.linkedFrom))));
    }
    if (!state.entitled) state = await partnerEntitlement(identity) || state;
    if (!state.entitled) return json(403, { error: "premium_required" });
    const ttl = ticketLifetime(state);
    if (!ttl) return json(403, { error: "premium_required" });
    const accountTag = privacyHash("stream-ticket", email).slice(0, 10);
    if (secret) return json(200, signTicket(secret, accountTag, Math.floor(Date.now() / 1000), ttl));
    return json(200, await signTicketV2(streamKeysStore(event), accountTag, ttl));
  } catch (error) {
    console.error("stream-ticket failed", error && error.message);
    return json(500, { error: "stream_ticket_failed" });
  }
};

exports._test = { signTicket, verifyTicket, ticketLifetime, TICKET_TTL_SECONDS, PARTNER_TICKET_TTL_SECONDS };

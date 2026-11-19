// Signing key for ARVIO Premium stream tickets (v2).
//
// The auth site creates an ECDSA P-256 key pair once and keeps it in its own
// Blobs store, so no person ever handles a shared secret. The relay worker
// fetches only the PUBLIC key (stream-ticket-key) and verifies tickets with
// it; a public key cannot mint tickets.
//
// Ticket: v2.<expiry, unix seconds>.<account tag>.<key id>.<ECDSA-SHA256 P1363 signature, base64url>
const { webcrypto } = require("crypto");
const { connectLambda, getStore } = require("@netlify/blobs");

const KEY_NAME = "stream-ticket/ecdsa-p256.json";
const ALGORITHM = { name: "ECDSA", namedCurve: "P-256" };
const SIGN = { name: "ECDSA", hash: "SHA-256" };

let cached = null; // { kid, privateKey, publicJwk }

function streamKeysStore(event) {
  if (event) connectLambda(event);
  return getStore("stream-keys");
}

function publicPart(jwk) {
  return { kty: jwk.kty, crv: jwk.crv, x: jwk.x, y: jwk.y };
}

async function keyId(publicJwk) {
  const digest = await webcrypto.subtle.digest("SHA-256", Buffer.from(`${publicJwk.crv}.${publicJwk.x}.${publicJwk.y}`));
  return Buffer.from(digest).subarray(0, 8).toString("base64url");
}

async function load(record) {
  const privateKey = await webcrypto.subtle.importKey("jwk", record.privateJwk, ALGORITHM, false, ["sign"]);
  return { kid: record.kid, privateKey, publicJwk: publicPart(record.publicJwk) };
}

async function readRecord(store) {
  // Lambda-style functions have no uncached edge URL, so strong reads are not
  // available; eventual reads are fine for a key that never changes.
  return store.get(KEY_NAME, { type: "json" });
}

/** The key pair, created on first use. Concurrent first calls agree on one key. */
async function signingKey(store) {
  if (cached) return cached;
  let record = await readRecord(store);
  if (!record) {
    const pair = await webcrypto.subtle.generateKey(ALGORITHM, true, ["sign", "verify"]);
    const privateJwk = await webcrypto.subtle.exportKey("jwk", pair.privateKey);
    const publicJwk = publicPart(await webcrypto.subtle.exportKey("jwk", pair.publicKey));
    const fresh = { kid: await keyId(publicJwk), privateJwk, publicJwk, createdAt: new Date().toISOString() };
    const result = await store.setJSON(KEY_NAME, fresh, { onlyIfNew: true });
    if (result && result.modified) record = fresh;
    // Another instance created it first: use theirs (it may take a moment to read back).
    for (let attempt = 0; !record && attempt < 5; attempt += 1) {
      record = await readRecord(store);
      if (!record) await new Promise((resolve) => setTimeout(resolve, 200 * (attempt + 1)));
    }
  }
  if (!record || !record.privateJwk || !record.publicJwk) throw new Error("stream ticket key unavailable");
  cached = await load(record);
  return cached;
}

async function publicKeys(store) {
  const key = await signingKey(store);
  return [{ kid: key.kid, ...key.publicJwk, alg: "ES256", use: "sig" }];
}

async function signTicketV2(store, accountTag, ttlSeconds, nowSeconds = Math.floor(Date.now() / 1000)) {
  const key = await signingKey(store);
  const expiry = nowSeconds + ttlSeconds;
  const body = `v2.${expiry}.${accountTag}.${key.kid}`;
  const signature = Buffer.from(await webcrypto.subtle.sign(SIGN, key.privateKey, Buffer.from(body))).toString("base64url");
  return { ticket: `${body}.${signature}`, expiresAt: new Date(expiry * 1000).toISOString() };
}

/** Verify with a public JWK (what the relay does); used by tests. */
async function verifyTicketV2(publicJwk, ticket, nowSeconds = Math.floor(Date.now() / 1000)) {
  const parts = String(ticket || "").split(".");
  if (parts.length !== 5 || parts[0] !== "v2") return false;
  const expiry = Number(parts[1]);
  if (!Number.isInteger(expiry) || expiry < nowSeconds) return false;
  try {
    const key = await webcrypto.subtle.importKey("jwk", publicPart(publicJwk), ALGORITHM, false, ["verify"]);
    return await webcrypto.subtle.verify(SIGN, key, Buffer.from(parts[4], "base64url"), Buffer.from(parts.slice(0, 4).join(".")));
  } catch {
    return false;
  }
}

function resetCache() { cached = null; }

module.exports = { streamKeysStore, signingKey, publicKeys, signTicketV2, verifyTicketV2, KEY_NAME, _resetCache: resetCache };

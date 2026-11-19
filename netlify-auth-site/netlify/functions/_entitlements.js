// Web subscription entitlements. Stored in a dedicated Netlify Blobs store,
// keyed by sha256(email) so the Ko-fi webhook (which only knows the buyer's
// email, not an ARVIO access token) and the app (which has a JWT identity with
// an email) resolve the SAME record. The APK never reads this — web only.
//
// An entitlement record:
//   {
//     status: "active" | "expired" | "cancelled",
//     source: "kofi" | "manual" | "trial",
//     tier: string | null,
//     startedAt: ISO string,
//     expiresAt: ISO string | null,   // null = lifetime
//     trialUsed: boolean,             // retained historical trial marker
//     lastEvent: string | null,
//     updatedAt: ISO string
//   }
const { connectLambda, getStore } = require("@netlify/blobs");

// Hosted Web is paid-only. These legacy response fields stay explicit so old
// clients cannot interpret a missing value as an available three-day trial.
const TRIAL_DAYS = 0;
const TRIAL_MS = 0;

function entitlementsStore(event) {
  connectLambda(event);
  return getStore("entitlements");
}

function entitlementKey(emailHash) {
  return `email/${emailHash}.json`;
}

async function readEntitlement(store, emailHash) {
  try {
    return await store.get(entitlementKey(emailHash), { type: "json", consistency: "strong" });
  } catch (error) {
    if (String(error?.message || "").includes("uncachedEdgeURL")) {
      return store.get(entitlementKey(emailHash), { type: "json" });
    }
    if (error?.status === 404 || error?.name === "BlobNotFoundError") return null;
    throw error;
  }
}

async function writeEntitlement(store, emailHash, record) {
  await store.setJSON(entitlementKey(emailHash), record);
  return record;
}

// Read with the blob's etag, for writes that must not overwrite a concurrent
// change (two deliveries of the same Ko-fi event, a renewal racing a bonus).
async function readEntitlementVersioned(store, emailHash) {
  const key = entitlementKey(emailHash);
  let result;
  try {
    result = await store.getWithMetadata(key, { type: "json", consistency: "strong" });
  } catch (error) {
    if (String(error?.message || "").includes("uncachedEdgeURL")) result = await store.getWithMetadata(key, { type: "json" });
    else if (error?.status === 404 || error?.name === "BlobNotFoundError") result = null;
    else throw error;
  }
  return { record: result?.data ?? null, etag: result?.etag ?? null };
}

// Write only if the record is still the one read; false when it changed.
async function writeEntitlementIfUnchanged(store, emailHash, record, etag, existed = Boolean(etag)) {
  // An existing blob read without an etag (older stores) is written as before.
  const condition = etag ? { onlyIfMatch: etag } : existed ? {} : { onlyIfNew: true };
  const result = await store.setJSON(entitlementKey(emailHash), record, condition);
  return result?.modified !== false;
}

// Read-modify-write with a few retries on concurrent changes.
async function updateEntitlement(store, emailHash, change, attempts = 4) {
  for (let attempt = 0; attempt < attempts; attempt += 1) {
    const { record, etag } = await readEntitlementVersioned(store, emailHash);
    const next = await change(record);
    if (!next) return { record, changed: false };
    if (await writeEntitlementIfUnchanged(store, emailHash, next, etag, Boolean(record))) return { record: next, changed: true };
  }
  throw new Error("Entitlement changed concurrently; please retry");
}

const DAY_MS = 24 * 60 * 60 * 1000;

// A renewal that arrives a little late (PayPal retries, bank holidays) must not
// lock a paying member out. Access continues for this many days after the paid
// end; the app shows a "payment didn't come through" banner meanwhile. Grace is
// applied when reading, so it never adds paid time and never accumulates.
// Grace exists for a monthly Ko-fi membership whose renewal is late. A
// 12-month pass, a referral bonus or a manual grant simply ends.
function graceApplies(record) {
  return Boolean(record) && record.source === "kofi" && record.plan !== "yearly" && !/12-month/i.test(String(record.tier || ""));
}

function graceDays() {
  const value = Number(process.env.PREMIUM_GRACE_DAYS ?? 3);
  return Number.isFinite(value) ? Math.max(0, Math.min(14, value)) : 3;
}

// The single source of truth the app reads: is this account entitled to web
// access right now, and why. Never throws — a storage hiccup returns "unknown"
// so the caller can decide (we fail-closed on the paywall, open on errors only
// with an explicit grace, see the check function in the web app).
function evaluateEntitlement(record, now = Date.now()) {
  if (!record) {
    return { entitled: false, reason: "none", status: "none", trialAvailable: false, trialDurationDays: TRIAL_DAYS, expiresAt: null, source: null };
  }
  const expiresAt = record.expiresAt ? Date.parse(record.expiresAt) : null;
  // Keep legacy trial records for account history, but never let one unlock
  // hosted Web, including a trial whose old deadline is still in the future.
  const historicalTrial = record.source === "trial";
  const activeRecord = !historicalTrial && record.status === "active";
  const paid = activeRecord && (expiresAt === null || expiresAt > now);
  const graceEnds = activeRecord && graceApplies(record) && Number.isFinite(expiresAt) && expiresAt !== null ? expiresAt + graceDays() * DAY_MS : null;
  const inGrace = !paid && graceEnds !== null && graceEnds > now;
  const entitled = paid || inGrace;
  return {
    entitled,
    reason: paid ? "subscription" : inGrace ? "grace" : historicalTrial ? "none" : "expired",
    status: historicalTrial ? "expired" : record.status || "none",
    source: record.source || null,
    tier: record.tier || null,
    plan: record.plan || null,
    expiresAt: record.expiresAt || null,
    graceEndsAt: inGrace ? new Date(graceEnds).toISOString() : null,
    // Paid at least once (Ko-fi, a pass, a manual grant or a referral bonus).
    // Lets the app tell a lapsed member apart from someone who never paid.
    everPaid: !historicalTrial && Boolean(record.startedAt || record.expiresAt),
    startedAt: historicalTrial ? null : record.startedAt || null,
    trialAvailable: false,
    trialDurationDays: TRIAL_DAYS,
    updatedAt: record.updatedAt || null
  };
}

// The moment new paid time starts from: the current paid end when the record
// still has paid time ahead (an early renewal or a pass bought mid-membership
// must not throw days away), otherwise now. Legacy trial deadlines never count.
function paidTimeBase(existing, now) {
  if (!existing || existing.source === "trial" || existing.status !== "active" || !existing.expiresAt) return now;
  const end = Date.parse(existing.expiresAt);
  return Number.isFinite(end) && end > now ? end : now;
}

function addMonths(timestamp, months) {
  const date = new Date(timestamp);
  const day = date.getUTCDate();
  date.setUTCMonth(date.getUTCMonth() + months);
  // 31 January + 1 month is the last day of February, not 3 March.
  if (date.getUTCDate() < day) date.setUTCDate(0);
  return date.getTime();
}

// Grant/renew paid access for `months` (calendar months, so a monthly
// membership stays in step with Ko-fi's billing date) or `days` (or lifetime
// when both are null), added on top of any paid time still ahead. Merges onto
// any existing record so history (startedAt, linkedFrom, payments) is kept.
function buildPaidRecord(existing, { source, tier, days, months, event, plan, now = Date.now() }) {
  const nowDate = new Date(now);
  const lifetime = existing && existing.status === "active" && existing.source !== "trial" && existing.expiresAt === null;
  const base = paidTimeBase(existing, now);
  const expiresAt = lifetime || (days == null && !months)
    ? null
    : new Date(months ? addMonths(base, months) : base + days * DAY_MS).toISOString();
  return {
    ...(existing || {}),
    status: "active",
    source: source || "kofi",
    tier: tier || (existing && existing.tier) || null,
    plan: plan || (existing && existing.plan) || null,
    startedAt: (existing && existing.source !== "trial" && existing.startedAt) || nowDate.toISOString(),
    expiresAt,
    trialUsed: existing ? existing.trialUsed === true : false,
    lastEvent: event || null,
    updatedAt: nowDate.toISOString()
  };
}

module.exports = {
  DAY_MS,
  graceDays,
  graceApplies,
  addMonths,
  readEntitlementVersioned,
  writeEntitlementIfUnchanged,
  updateEntitlement,
  paidTimeBase,
  TRIAL_MS,
  TRIAL_DAYS,
  entitlementsStore,
  readEntitlement,
  writeEntitlement,
  evaluateEntitlement,
  buildPaidRecord
};

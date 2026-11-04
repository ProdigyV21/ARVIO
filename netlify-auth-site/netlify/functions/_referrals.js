// "Give a month, get a month". Every account can get an invite code. Someone
// who signs up through it and then starts paying earns 31 extra days for both
// accounts as soon as the payment is confirmed; the inviter can earn at
// most MAX_REWARDS_PER_YEAR a year. Each paying identity (the billing email)
// rewards at most once, however many accounts link it.
//
// Store "premium-referrals":
//   code/<CODE>.json         { ownerHash, createdAt }
//   owner/<ownerHash>.json   { code, sealedEmail, rewards: [ISO], joined }
//   referred/<emailHash>.json { code, ownerHash, claimedAt, rewardedAt? }
//   rewarded-payer/<payerHash>.json  once per billing email
// ownerHash / emailHash = sha256(email), the same key as the entitlement
// record, so a reward can extend the right membership directly.
const crypto = require("crypto");
const { connectLambda, getStore } = require("@netlify/blobs");
const { normalizeEmail, sha256 } = require("./_backend");
const { entitlementsStore, readEntitlement, updateEntitlement, evaluateEntitlement, buildPaidRecord, DAY_MS } = require("./_entitlements");
const { queueMemberEmail, _test: memberEmailInternals } = require("./_member-emails");

const REWARD_DAYS = 31;
const MAX_REWARDS_PER_YEAR = 6;
const CODE_ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
const CODE_PATTERN = /^[A-HJ-NP-Z2-9]{8}$/;

function referralStore(event) {
  connectLambda(event);
  return getStore("premium-referrals");
}

function newCode() {
  const bytes = crypto.randomBytes(8);
  return Array.from(bytes, (byte) => CODE_ALPHABET[byte % CODE_ALPHABET.length]).join("");
}

function normalizeCode(value) {
  const code = String(value || "").trim().toUpperCase();
  return CODE_PATTERN.test(code) ? code : null;
}

function inviteLink(code) {
  return `https://web.arvio.tv/?ref=${code}`;
}

async function getVersioned(store, key) {
  try {
    return await store.getWithMetadata(key, { type: "json", consistency: "strong" });
  } catch (error) {
    if (String(error?.message || "").includes("uncachedEdgeURL")) return store.getWithMetadata(key, { type: "json" });
    if (error?.status === 404 || error?.name === "BlobNotFoundError") return null;
    throw error;
  }
}

async function getJSON(store, key) {
  try {
    return await store.get(key, { type: "json", consistency: "strong" });
  } catch (error) {
    if (String(error?.message || "").includes("uncachedEdgeURL")) return store.get(key, { type: "json" }).catch(() => null);
    if (error?.status === 404 || error?.name === "BlobNotFoundError") return null;
    throw error;
  }
}

function rewardsInLastYear(owner, now = Date.now()) {
  return (owner?.rewards || []).filter((at) => now - Date.parse(at) < 365 * DAY_MS).length;
}

// The account's invite code, created on first request.
async function ensureOwner(store, { email, emailHash }) {
  const snapshot = await getVersioned(store, `owner/${emailHash}.json`);
  const existing = snapshot?.data;
  if (existing?.code) return existing;
  for (let attempt = 0; attempt < 5; attempt += 1) {
    const code = newCode();
    const claimed = await store.setJSON(`code/${code}.json`, { ownerHash: emailHash, createdAt: new Date().toISOString() }, { onlyIfNew: true });
    if (claimed?.modified === false) continue;
    // Keep any history (rewards, joined) from a record that lost its code.
    const owner = { rewards: [], joined: 0, ...(existing || {}), code, sealedEmail: memberEmailInternals.sealEmail(email), createdAt: existing?.createdAt || new Date().toISOString() };
    const written = await store.setJSON(`owner/${emailHash}.json`, owner, existing ? { onlyIfMatch: snapshot.etag } : { onlyIfNew: true });
    if (written?.modified === false) {
      // Another request created it first; keep theirs.
      await store.delete(`code/${code}.json`).catch(() => {});
      return getJSON(store, `owner/${emailHash}.json`);
    }
    return owner;
  }
  throw new Error("Could not allocate an invite code");
}

async function referralStatus(event, { email, emailHash }, now = Date.now()) {
  const store = referralStore(event);
  const owner = await ensureOwner(store, { email, emailHash });
  const used = rewardsInLastYear(owner, now);
  return {
    code: owner.code,
    link: inviteLink(owner.code),
    joined: owner.joined || 0,
    rewardsThisYear: used,
    rewardsLeftThisYear: Math.max(0, MAX_REWARDS_PER_YEAR - used),
    rewardDays: REWARD_DAYS,
    maxRewardsPerYear: MAX_REWARDS_PER_YEAR
  };
}

// Record that this account signed up through an invite. Only accounts that
// have never paid can be invited, an account cannot invite itself, and the
// first claim wins.
async function claimReferral(event, { emailHash, code }, { entitlements } = {}) {
  const normalized = normalizeCode(code);
  if (!normalized) return { ok: false, error: "invalid_code" };
  const store = referralStore(event);
  const codeRecord = await getJSON(store, `code/${normalized}.json`);
  if (!codeRecord?.ownerHash) return { ok: false, error: "unknown_code" };
  if (codeRecord.ownerHash === emailHash) return { ok: false, error: "own_code" };
  const record = await readEntitlement(entitlements || entitlementsStore(event), emailHash);
  // A linked billing email means the account already pays (or paid) elsewhere.
  if (evaluateEntitlement(record).everPaid || (record && record.linkedFrom)) return { ok: false, error: "already_member" };
  const written = await store.setJSON(`referred/${emailHash}.json`, { code: normalized, ownerHash: codeRecord.ownerHash, claimedAt: new Date().toISOString() }, { onlyIfNew: true });
  if (written?.modified === false) return { ok: false, error: "already_claimed" };
  return { ok: true };
}

// The record a bonus belongs on: the billing record when the account pays with
// a linked Ko-fi email (copies are refreshed from it), otherwise its own.
async function bonusTarget(entitlementStore, accountHash) {
  const record = await readEntitlement(entitlementStore, accountHash);
  const linked = normalizeEmail(record && record.linkedFrom);
  return linked ? sha256(linked) : accountHash;
}

function addBonus(store, hash, event, now) {
  return updateEntitlement(store, hash, (existing) => buildPaidRecord(existing, {
    // A legacy trial record is not paid access; the bonus must be visible.
    source: existing && existing.source && existing.source !== "trial" ? existing.source : "referral",
    days: REWARD_DAYS,
    event,
    now
  }));
}

// Checked after payments and when a paying account opens ARVIO Web.
// `emailHash` is the invited ARVIO account; `payerHash` the billing email
// that pays for it (the same unless a Ko-fi email is linked). Rewards both
// accounts once, when the payer started paying after the invite.
async function rewardReferralIfDue(event, { emailHash, payerHash = emailHash, now = Date.now() }, { entitlements } = {}) {
  const store = referralStore(event);
  const snapshot = await getVersioned(store, `referred/${emailHash}.json`);
  const referred = snapshot?.data;
  if (!referred || referred.rewardedAt) return { rewarded: false };

  const entitlementStore = entitlements || entitlementsStore(event);
  const payer = await readEntitlement(entitlementStore, payerHash);
  const paying = payer?.source === "kofi" && evaluateEntitlement(payer, now).reason === "subscription";
  const started = Date.parse(payer?.startedAt || "");
  if (!paying || !Number.isFinite(started) || started > now) return { rewarded: false, reason: "not_paying" };
  // Paying since before the invite: an existing member, not a new one.
  if (started < Date.parse(referred.claimedAt) - 60 * 60 * 1000) {
    await store.setJSON(`referred/${emailHash}.json`, { ...referred, rewardedAt: new Date(now).toISOString(), outcome: "existing_member" }, { onlyIfMatch: snapshot.etag });
    return { rewarded: false, reason: "existing_member" };
  }

  // One reward per billing identity, however many accounts link it.
  const payerMark = await store.setJSON(`rewarded-payer/${payerHash}.json`, { at: new Date(now).toISOString(), account: emailHash }, { onlyIfNew: true });
  if (payerMark?.modified === false) {
    await store.setJSON(`referred/${emailHash}.json`, { ...referred, rewardedAt: new Date(now).toISOString(), outcome: "payer_already_rewarded" }, { onlyIfMatch: snapshot.etag });
    return { rewarded: false, reason: "payer_already_rewarded" };
  }
  const marked = await store.setJSON(`referred/${emailHash}.json`, { ...referred, rewardedAt: new Date(now).toISOString(), outcome: "rewarded" }, { onlyIfMatch: snapshot.etag });
  if (marked?.modified === false) return { rewarded: false };

  const { record: payerRecord } = await addBonus(entitlementStore, payerHash, "referral-bonus-invited", now);

  const ownerSnapshot = await getVersioned(store, `owner/${referred.ownerHash}.json`);
  const owner = ownerSnapshot?.data;
  // The inviter deleted their account: nothing to reward or recreate.
  if (!owner) return { rewarded: true, inviterRewarded: false, expiresAt: payerRecord.expiresAt };
  const ownerUpdate = { ...owner, joined: Number(owner.joined || 0) + 1 };
  let inviterRewarded = false;
  if (rewardsInLastYear(owner, now) < MAX_REWARDS_PER_YEAR) {
    await addBonus(entitlementStore, await bonusTarget(entitlementStore, referred.ownerHash), "referral-bonus-inviter", now);
    ownerUpdate.rewards = [...(owner.rewards || []), new Date(now).toISOString()].slice(-24);
    inviterRewarded = true;
    if (owner.sealedEmail) {
      try {
        const inviterEmail = memberEmailInternals.openEmail(owner.sealedEmail);
        await queueMemberEmail(event, { email: inviterEmail, emailHash: referred.ownerHash, type: "referral_reward", dueAt: now, targetEnd: new Date(now).toISOString() });
      } catch (error) {
        console.error("referral reward email not queued", error && error.message);
      }
    }
  }
  await store.setJSON(`owner/${referred.ownerHash}.json`, ownerUpdate, { onlyIfMatch: ownerSnapshot.etag });
  return { rewarded: true, inviterRewarded, expiresAt: payerRecord.expiresAt };
}

async function deleteReferralData(event, emailHash) {
  const store = referralStore(event);
  const owner = await getJSON(store, `owner/${emailHash}.json`);
  if (owner?.code) await store.delete(`code/${owner.code}.json`).catch(() => {});
  await store.delete(`owner/${emailHash}.json`).catch(() => {});
  await store.delete(`referred/${emailHash}.json`).catch(() => {});
}

module.exports = {
  REWARD_DAYS,
  MAX_REWARDS_PER_YEAR,
  normalizeCode,
  inviteLink,
  referralStatus,
  claimReferral,
  rewardReferralIfDue,
  deleteReferralData,
  _test: { newCode, rewardsInLastYear, ensureOwner }
};

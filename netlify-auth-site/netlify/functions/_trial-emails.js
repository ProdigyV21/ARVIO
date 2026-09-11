const crypto = require("crypto");
const { connectLambda, getStore } = require("@netlify/blobs");
const { privacyHash, sendTransactionalEmail, sha256, normalizeEmail } = require("./_backend");
const { recordPremiumEvent } = require("./_premium-funnel");
const { entitlementsStore, readEntitlement, evaluateEntitlement } = require("./_entitlements");

const DAY_MS = 24 * 60 * 60 * 1000;
const JOB_RETENTION_MS = 14 * DAY_MS;
const JOB_TYPES = ["welcome", "reminder", "expired"];

function trialEmailStore(event) {
  connectLambda(event);
  return getStore("premium-trial-emails");
}

function encryptionKey() {
  const secret = process.env.ARVIO_AUTH_SECRET || "";
  if (secret.length < 32) throw new Error("ARVIO_AUTH_SECRET is not configured");
  return crypto.createHash("sha256").update(`${secret}:premium-trial-email:v1`).digest();
}

function sealEmail(email) {
  const iv = crypto.randomBytes(12);
  const cipher = crypto.createCipheriv("aes-256-gcm", encryptionKey(), iv);
  const encrypted = Buffer.concat([cipher.update(String(email), "utf8"), cipher.final()]);
  const tag = cipher.getAuthTag();
  return [iv, tag, encrypted].map((part) => part.toString("base64url")).join(".");
}

function openEmail(sealed) {
  const [ivRaw, tagRaw, encryptedRaw] = String(sealed || "").split(".");
  if (!ivRaw || !tagRaw || !encryptedRaw) throw new Error("Invalid encrypted email");
  const decipher = crypto.createDecipheriv("aes-256-gcm", encryptionKey(), Buffer.from(ivRaw, "base64url"));
  decipher.setAuthTag(Buffer.from(tagRaw, "base64url"));
  return Buffer.concat([
    decipher.update(Buffer.from(encryptedRaw, "base64url")),
    decipher.final()
  ]).toString("utf8");
}

async function getJSON(store, key) {
  try {
    return await store.get(key, { type: "json", consistency: "strong" });
  } catch (error) {
    if (String(error?.message || "").includes("uncachedEdgeURL")) {
      return store.get(key, { type: "json" }).catch(() => null);
    }
    if (error?.status === 404 || error?.name === "BlobNotFoundError") return null;
    throw error;
  }
}

async function listKeys(store, prefix) {
  const keys = [];
  let cursor;
  do {
    const page = await store.list({ prefix, cursor });
    keys.push(...(page.blobs || []).map((blob) => blob.key));
    cursor = page.next_cursor || page.nextCursor || undefined;
  } while (cursor);
  return keys;
}

function trialJobKey(accountKey, type) {
  return `jobs/${accountKey}/${type}.json`;
}

function trialEmailContent(type, expiresAt) {
  const webUrl = `https://web.arvio.tv/?utm_source=trial_email&utm_medium=email&utm_campaign=premium&utm_content=${type}`;
  const membershipUrl = process.env.KOFI_URL || process.env.NEXT_PUBLIC_KOFI_URL || "https://ko-fi.com/arvio/tiers";
  const end = new Intl.DateTimeFormat("en", {
    year: "numeric",
    month: "short",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    timeZone: "UTC",
    timeZoneName: "short"
  }).format(new Date(expiresAt));
  if (type === "welcome") {
    return {
      subject: "Your 3-day ARVIO Web trial is active",
      text: `Your ARVIO Web trial is ready until ${end}. Open ARVIO Web on Windows, Mac, iPhone, iPad or another browser: ${webUrl}\n\nYour profiles, addons, libraries and watch progress stay connected through ARVIO Cloud. The Android app remains free.\n\nWant to keep browser access after the trial? Membership is $2.99/month: ${membershipUrl}`,
      html: `<p>Your <strong>3-day ARVIO Web trial</strong> is ready until ${end}.</p><p><a href="${webUrl}">Open ARVIO Web on Windows, Mac or mobile</a></p><p>Your profiles, addons, libraries and watch progress stay connected through ARVIO Cloud. The Android app remains free.</p><p><a href="${membershipUrl}">Keep Premium after your trial for $2.99/month</a>.</p>`
    };
  }
  if (type === "reminder") {
    return {
      subject: "Your ARVIO Web trial ends tomorrow",
      text: `Your ARVIO Web trial ends at ${end}. Continue using it here: ${webUrl}\n\nTo keep Windows, Mac, browser, iPhone and iPad access after the trial, membership is $2.99/month: ${membershipUrl}`,
      html: `<p>Your ARVIO Web trial ends at <strong>${end}</strong>.</p><p><a href="${webUrl}">Continue your trial</a></p><p>To keep Windows, Mac and mobile browser access after the trial, <a href="${membershipUrl}">membership is $2.99/month</a>.</p>`
    };
  }
  return {
    subject: "Your ARVIO Web trial has ended",
    text: `Your ARVIO Web trial has ended. Your ARVIO Cloud account and Android app are unaffected.\n\nKeep Windows, Mac and mobile browser access for $2.99/month: ${membershipUrl}\n\nThis is the final email for this trial.`,
    html: `<p>Your ARVIO Web trial has ended. Your ARVIO Cloud account and Android app are unaffected.</p><p><a href="${membershipUrl}">Keep Windows, Mac and mobile browser access for $2.99/month</a>.</p><p>This is the final email for this trial.</p>`
  };
}

async function queueTrialEmails(event, email, expiresAt) {
  const normalized = String(email || "").trim().toLowerCase();
  if (!normalized || !expiresAt) return [];
  const store = trialEmailStore(event);
  const accountKey = privacyHash("premium-funnel-account", normalized);
  const expiresMs = Date.parse(expiresAt);
  if (!Number.isFinite(expiresMs)) throw new Error("Invalid trial expiry");
  const now = new Date();
  const dueAt = {
    welcome: now.toISOString(),
    reminder: new Date(expiresMs - DAY_MS).toISOString(),
    expired: new Date(expiresMs + 60 * 60 * 1000).toISOString()
  };
  const keys = [];
  for (const type of JOB_TYPES) {
    const key = trialJobKey(accountKey, type);
    keys.push(key);
    if (await getJSON(store, key)) continue;
    await store.setJSON(key, {
      accountKey,
      type,
      sealedEmail: sealEmail(normalized),
      dueAt: dueAt[type],
      expiresAt,
      status: "pending",
      attempts: 0,
      createdAt: now.toISOString(),
      updatedAt: now.toISOString()
    });
  }
  return keys;
}

async function deliverTrialEmailJob(event, store, key, job) {
  const now = new Date();
  const nowMs = now.getTime();
  if (!job || job.sentAt || job.status === "suppressed" || !JOB_TYPES.includes(job.type)) return false;
  if (job.status === "failed" && Number(job.attempts || 0) >= 6) return false;
  if (Date.parse(job.dueAt || "") > nowMs) return false;
  if (job.nextAttemptAt && Date.parse(job.nextAttemptAt) > nowMs) return false;
  if (job.status === "sending" && Date.parse(job.lockedUntil || "") > nowMs) return false;
  const attempts = Number(job.attempts || 0) + 1;
  await store.setJSON(key, {
    ...job,
    status: "sending",
    attempts,
    lockedUntil: new Date(nowMs + 10 * 60 * 1000).toISOString(),
    updatedAt: now.toISOString()
  });
  try {
    const email = openEmail(job.sealedEmail);
    const accessStore = entitlementsStore(event);
    const record = await readEntitlement(accessStore, sha256(normalizeEmail(email)));
    const linkedEmail = normalizeEmail(record?.linkedFrom);
    const linked = linkedEmail && linkedEmail !== normalizeEmail(email)
      ? await readEntitlement(accessStore, sha256(linkedEmail)) : null;
    const suppression = trialEmailSuppression(job, record, linked, nowMs);
    if (suppression) {
      await store.setJSON(key, { ...job, sealedEmail: null, status: "suppressed", reason: suppression, updatedAt: now.toISOString() });
      return false;
    }
    const content = trialEmailContent(job.type, job.expiresAt);
    const result = await sendTransactionalEmail(email, content.subject, content.text, content.html);
    const sentAt = new Date().toISOString();
    await store.setJSON(key, {
      ...job,
      sealedEmail: null,
      status: "sent",
      attempts,
      provider: result?.provider || null,
      providerId: result?.id || null,
      sentAt,
      updatedAt: sentAt
    });
    await recordPremiumEvent(event, {
      email,
      eventName: `trial_email_${job.type}_sent`,
      metadata: { provider: result?.provider || "unknown" }
    }).catch(() => {});
    return true;
  } catch (error) {
    const retryMinutes = Math.min(360, 15 * Math.pow(2, Math.min(4, attempts - 1)));
    await store.setJSON(key, {
      ...job,
      status: attempts >= 6 ? "failed" : "pending",
      attempts,
      lastError: String(error?.message || error).slice(0, 240),
      nextAttemptAt: new Date(nowMs + retryMinutes * 60 * 1000).toISOString(),
      lockedUntil: null,
      updatedAt: new Date().toISOString()
    });
    throw error;
  }
}

async function runDueTrialEmails(event, limit = 50) {
  const store = trialEmailStore(event);
  const keys = await listKeys(store, "jobs/");
  let sent = 0;
  let failed = 0;
  let deleted = 0;
  for (const key of keys) {
    if (sent + failed >= limit) break;
    const job = await getJSON(store, key);
    const completedAt = Date.parse(job?.sentAt || job?.updatedAt || "");
    if (["sent", "failed", "suppressed"].includes(job?.status) && Number.isFinite(completedAt) && Date.now() - completedAt > JOB_RETENTION_MS) {
      await store.delete(key).catch(() => {});
      deleted += 1;
      continue;
    }
    try {
      if (await deliverTrialEmailJob(event, store, key, job)) sent += 1;
    } catch (error) {
      failed += 1;
      console.error("trial email delivery failed", key, error);
    }
  }
  return { sent, failed, deleted, scanned: keys.length };
}

function trialEmailSuppression(job, record, linkedRecord, now = Date.now()) {
  const paid = candidate => {
    const state = evaluateEntitlement(candidate);
    return state.entitled && state.reason === "subscription";
  };
  if (paid(record) || paid(linkedRecord)) return "already_paid";
  if (!record) return "no_entitlement";
  if (job.type === "expired" && record.source === "trial" && Date.parse(record.expiresAt) > now) return "trial_still_active";
  if (job.type !== "expired" && Date.parse(job.expiresAt) <= now) return "stale_trial_message";
  return null;
}

module.exports = {
  JOB_TYPES,
  queueTrialEmails,
  runDueTrialEmails,
  _test: { sealEmail, openEmail, trialEmailContent, trialJobKey, trialEmailSuppression, deliverTrialEmailJob }
};

// Emails to paying members: the first-month tips, the yearly-pass offer, pass
// renewal reminders and referral rewards. Jobs are queued by the Ko-fi webhook
// (and referral rewards) and delivered by the scheduled email worker.
//
// Privacy: a job stores the address encrypted (AES-256-GCM, key derived from
// ARVIO_AUTH_SECRET) and only until it is sent; the job key is a keyed hash.
// Every email carries a one-click unsubscribe link (member-email-optout).
const crypto = require("crypto");
const { connectLambda, getStore } = require("@netlify/blobs");
const { privacyHash, sendTransactionalEmail } = require("./_backend");
const { entitlementsStore, readEntitlement, evaluateEntitlement, DAY_MS } = require("./_entitlements");

const JOB_RETENTION_MS = 30 * DAY_MS;
const MAX_ATTEMPTS = 5;
const TYPES = ["tips_day7", "yearly_day20", "pass_ends_14", "pass_ends_3", "referral_reward"];
const WEB_URL = "https://web.arvio.tv/";

function memberEmailStore(event) {
  connectLambda(event);
  return getStore("premium-member-emails");
}

function encryptionKey() {
  const secret = process.env.ARVIO_AUTH_SECRET || "";
  if (secret.length < 32) throw new Error("ARVIO_AUTH_SECRET is not configured");
  return crypto.createHash("sha256").update(`${secret}:premium-member-email:v1`).digest();
}

function sealEmail(email) {
  const iv = crypto.randomBytes(12);
  const cipher = crypto.createCipheriv("aes-256-gcm", encryptionKey(), iv);
  const encrypted = Buffer.concat([cipher.update(String(email), "utf8"), cipher.final()]);
  return [iv, cipher.getAuthTag(), encrypted].map((part) => part.toString("base64url")).join(".");
}

function openEmail(sealed) {
  const [ivRaw, tagRaw, encryptedRaw] = String(sealed || "").split(".");
  if (!ivRaw || !tagRaw || !encryptedRaw) throw new Error("Invalid encrypted email");
  const decipher = crypto.createDecipheriv("aes-256-gcm", encryptionKey(), Buffer.from(ivRaw, "base64url"));
  decipher.setAuthTag(Buffer.from(tagRaw, "base64url"));
  return Buffer.concat([decipher.update(Buffer.from(encryptedRaw, "base64url")), decipher.final()]).toString("utf8");
}

function memberAccountKey(email) {
  return privacyHash("premium-member-email", String(email || "").trim().toLowerCase());
}

function optoutSignature(accountKey) {
  return privacyHash("premium-member-email-optout", accountKey);
}

function safeEqual(a, b) {
  const left = Buffer.from(String(a || ""));
  const right = Buffer.from(String(b || ""));
  return left.length === right.length && crypto.timingSafeEqual(left, right);
}

function verifyOptout(accountKey, signature) {
  return /^[a-f0-9]{64}$/.test(String(accountKey || "")) && safeEqual(optoutSignature(accountKey), signature);
}

function authOrigin() {
  return String(process.env.URL || "https://auth.arvio.tv").replace(/\/+$/, "");
}

function optoutUrl(accountKey) {
  return `${authOrigin()}/.netlify/functions/member-email-optout?a=${accountKey}&s=${optoutSignature(accountKey)}`;
}

function yearlyUrl() {
  return String(process.env.KOFI_YEARLY_URL || "").trim();
}

function monthlyUrl() {
  return process.env.KOFI_URL || process.env.NEXT_PUBLIC_KOFI_URL || "https://ko-fi.com/arvio/tiers";
}

function formatDate(iso) {
  return new Intl.DateTimeFormat("en", { year: "numeric", month: "long", day: "numeric", timeZone: "UTC" }).format(new Date(iso));
}

function tracked(url, content) {
  const target = new URL(url);
  target.searchParams.set("utm_source", "member_email");
  target.searchParams.set("utm_medium", "email");
  target.searchParams.set("utm_campaign", "premium");
  target.searchParams.set("utm_content", content);
  return target.toString();
}

function escapeHtml(value) {
  return String(value).replace(/[&<>"']/g, (char) => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" })[char]);
}

// Plain, short emails. `paragraphs` are [text, html] pairs.
function compose(subject, paragraphs, accountKey) {
  const unsubscribe = optoutUrl(accountKey);
  const footerText = `You get this email because you are an ARVIO Premium member. Unsubscribe: ${unsubscribe}`;
  const footerHtml = `<p style="color:#777;font-size:12px">You get this email because you are an ARVIO Premium member. <a href="${escapeHtml(unsubscribe)}">Unsubscribe</a></p>`;
  return {
    subject,
    text: [...paragraphs.map(([text]) => text), footerText].join("\n\n"),
    html: [...paragraphs.map(([, html]) => html), footerHtml].join("")
  };
}

function memberEmailContent(type, { accountKey, targetEnd } = {}) {
  const web = (content) => tracked(WEB_URL, content);
  if (type === "tips_day7") {
    return compose("Three things to try in ARVIO Web", [
      ["Thanks for your first week with ARVIO Premium. Three things members often find late:", "<p>Thanks for your first week with ARVIO Premium. Three things members often find late:</p>"],
      ["1. Live TV with the programme guide: add your IPTV service under Settings, then open Live TV.\n2. Trakt or Simkl sync: connect it once in Settings and your watch history follows you between devices.\n3. Downloads: on a computer, save supported sources from the source picker.",
        "<ol><li><strong>Live TV with the programme guide:</strong> add your IPTV service under Settings, then open Live TV.</li><li><strong>Trakt or Simkl sync:</strong> connect it once in Settings and your watch history follows you between devices.</li><li><strong>Downloads:</strong> on a computer, save supported sources from the source picker.</li></ol>"],
      [`On iPhone or iPad, add ARVIO to your Home Screen (Share, then Add to Home Screen) so it opens like an app: ${web(type)}`,
        `<p>On iPhone or iPad, add ARVIO to your Home Screen (Share, then Add to Home Screen) so it opens like an app. <a href="${escapeHtml(web(type))}">Open ARVIO Web</a></p>`]
    ], accountKey);
  }
  if (type === "yearly_day20") {
    const url = tracked(yearlyUrl(), type);
    return compose("Keep ARVIO Web for a year for $20", [
      ["You can switch to the 12-month pass: one payment of $20, no automatic renewal, about $1.67 a month.", "<p>You can switch to the <strong>12-month pass</strong>: one payment of $20, no automatic renewal, about $1.67 a month.</p>"],
      ["The time you have left is added on top, so nothing is lost. After buying, cancel your monthly membership on Ko-fi.", "<p>The time you have left is added on top, so nothing is lost. After buying, cancel your monthly membership on Ko-fi.</p>"],
      [`Get the 12-month pass: ${url}`, `<p><a href="${escapeHtml(url)}">Get the 12-month pass</a></p>`]
    ], accountKey);
  }
  if (type === "pass_ends_14" || type === "pass_ends_3") {
    const end = formatDate(targetEnd);
    const yearly = yearlyUrl() ? tracked(yearlyUrl(), type) : "";
    const monthly = tracked(monthlyUrl(), type);
    return compose(type === "pass_ends_3" ? `Your ARVIO Premium pass ends on ${end}` : `Your ARVIO Premium pass ends in two weeks`, [
      [`Your 12-month pass ends on ${end}. It does not renew automatically.`, `<p>Your 12-month pass ends on <strong>${escapeHtml(end)}</strong>. It does not renew automatically.</p>`],
      yearly
        ? [`Renew for another year for $20: ${yearly}\nOr continue monthly for $2.99: ${monthly}`, `<p><a href="${escapeHtml(yearly)}">Renew for another year for $20</a> or <a href="${escapeHtml(monthly)}">continue monthly for $2.99</a>.</p>`]
        : [`Continue for $2.99 a month: ${monthly}`, `<p><a href="${escapeHtml(monthly)}">Continue for $2.99 a month</a>.</p>`],
      ["Renewing before the end date adds the new time on top of what is left.", "<p>Renewing before the end date adds the new time on top of what is left.</p>"]
    ], accountKey);
  }
  if (type === "referral_reward") {
    return compose("You got a free month of ARVIO Premium", [
      ["Someone you invited just joined ARVIO Premium, so we added 31 days to your membership. Thank you for sharing ARVIO.", "<p>Someone you invited just joined ARVIO Premium, so we added <strong>31 days</strong> to your membership. Thank you for sharing ARVIO.</p>"],
      [`Your invite link is in ARVIO Web under Settings, Premium: ${web(type)}`, `<p>Your invite link is in ARVIO Web under Settings, Premium. <a href="${escapeHtml(web(type))}">Open ARVIO Web</a></p>`]
    ], accountKey);
  }
  throw new Error(`Unknown member email type ${type}`);
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

function jobKey(accountKey, type, targetEnd) {
  const suffix = targetEnd ? `-${String(targetEnd).slice(0, 10)}` : "";
  return `jobs/${accountKey}/${type}${suffix}.json`;
}

// Queue one email. The same type (and pass end date) is queued at most once
// per account, so webhook retries and renewals never send duplicates.
async function queueMemberEmail(event, { email, emailHash, type, dueAt, targetEnd = null }, store = memberEmailStore(event)) {
  if (!TYPES.includes(type)) throw new Error(`Unknown member email type ${type}`);
  if (!email || !emailHash) return false;
  if (type === "yearly_day20" && !yearlyUrl()) return false;
  const accountKey = memberAccountKey(email);
  const job = {
    type,
    accountKey,
    emailHash,
    sealedEmail: sealEmail(email),
    dueAt: new Date(dueAt).toISOString(),
    targetEnd: targetEnd || null,
    status: "queued",
    createdAt: new Date().toISOString()
  };
  const result = await store.setJSON(jobKey(accountKey, type, targetEnd), job, { onlyIfNew: true });
  return result?.modified !== false;
}

// Jobs for a payment: the first-month emails after a first monthly payment,
// and the two renewal reminders for a 12-month pass.
async function queueForPayment(event, { email, emailHash, plan, firstPayment, record, now = Date.now() }) {
  const store = memberEmailStore(event);
  const queued = [];
  if (plan === "monthly" && firstPayment) {
    if (await queueMemberEmail(event, { email, emailHash, type: "tips_day7", dueAt: now + 7 * DAY_MS }, store)) queued.push("tips_day7");
    if (await queueMemberEmail(event, { email, emailHash, type: "yearly_day20", dueAt: now + 20 * DAY_MS }, store)) queued.push("yearly_day20");
  }
  if (plan === "yearly" && record && record.expiresAt) {
    const end = Date.parse(record.expiresAt);
    for (const [type, days] of [["pass_ends_14", 14], ["pass_ends_3", 3]]) {
      const due = end - days * DAY_MS;
      if (due > now && await queueMemberEmail(event, { email, emailHash, type, dueAt: due, targetEnd: record.expiresAt }, store)) queued.push(type);
    }
  }
  return queued;
}

// Decide at send time whether the email still makes sense: a renewed pass, a
// member who left or already holds a pass gets nothing.
function stillRelevant(job, record, now = Date.now()) {
  const state = evaluateEntitlement(record, now);
  if (job.type === "referral_reward") return true;
  if (!state.entitled) return false;
  if (job.type === "tips_day7") return true;
  if (job.type === "yearly_day20") return Boolean(yearlyUrl()) && record?.plan !== "yearly";
  if (job.type === "pass_ends_14" || job.type === "pass_ends_3") {
    const target = Date.parse(job.targetEnd || "");
    const current = Date.parse(record?.expiresAt || "");
    // Renewed or extended since the reminder was queued: no reminder needed.
    return Number.isFinite(target) && Number.isFinite(current) && current <= target + DAY_MS;
  }
  return false;
}

async function deliverMemberEmailJob(event, store, key, job, { now = Date.now(), send = sendTransactionalEmail, entitlements } = {}) {
  const finish = (status, reason) => store.setJSON(key, { ...job, sealedEmail: null, status, reason: reason || null, sentAt: status === "sent" ? new Date(now).toISOString() : null, updatedAt: new Date(now).toISOString() });
  if (await getJSON(store, `optout/${job.accountKey}.json`)) { await finish("suppressed", "unsubscribed"); return false; }
  const entitlementStore = entitlements || entitlementsStore(event);
  const record = await readEntitlement(entitlementStore, job.emailHash);
  if (!stillRelevant(job, record, now)) { await finish("suppressed", "not_relevant"); return false; }
  const email = openEmail(job.sealedEmail);
  const content = memberEmailContent(job.type, { accountKey: job.accountKey, targetEnd: job.targetEnd });
  await send(email, content.subject, content.text, content.html);
  await finish("sent");
  return true;
}

async function runDueMemberEmails(event, limit = 50, options = {}) {
  const now = options.now || Date.now();
  const store = options.store || memberEmailStore(event);
  const keys = await listKeys(store, "jobs/");
  const result = { sent: 0, failed: 0, suppressed: 0, deleted: 0, waiting: 0 };
  let processed = 0;
  for (const key of keys) {
    if (processed >= limit) break;
    const job = await getJSON(store, key);
    if (!job) continue;
    if (job.status !== "queued") {
      const done = Date.parse(job.updatedAt || job.sentAt || "");
      if (Number.isFinite(done) && now - done > JOB_RETENTION_MS) {
        processed += 1;
        await store.delete(key).then(() => { result.deleted += 1; }, () => { result.failed += 1; });
      }
      continue;
    }
    if (Date.parse(job.dueAt) > now) { result.waiting += 1; continue; }
    processed += 1;
    try {
      if (await deliverMemberEmailJob(event, store, key, job, { ...options, now })) result.sent += 1;
      else result.suppressed += 1;
    } catch (error) {
      result.failed += 1;
      console.error("member email delivery failed", key, error && error.message);
      // Back off (2, 4, 8, 16 hours) and give up after MAX_ATTEMPTS, so a
      // permanently failing address never blocks the jobs behind it.
      const attempts = Number(job.attempts || 0) + 1;
      const failedForGood = attempts >= MAX_ATTEMPTS;
      await store.setJSON(key, {
        ...job,
        attempts,
        status: failedForGood ? "failed" : "queued",
        sealedEmail: failedForGood ? null : job.sealedEmail,
        dueAt: new Date(now + 2 ** attempts * 60 * 60 * 1000).toISOString(),
        lastError: String(error && error.message || "error").slice(0, 120),
        updatedAt: new Date(now).toISOString()
      }).catch(() => {});
    }
  }
  return result;
}

async function optOut(event, accountKey, store = memberEmailStore(event)) {
  await store.setJSON(`optout/${accountKey}.json`, { at: new Date().toISOString() });
}

module.exports = {
  TYPES,
  memberEmailStore,
  memberAccountKey,
  queueMemberEmail,
  queueForPayment,
  runDueMemberEmails,
  verifyOptout,
  optOut,
  _test: { sealEmail, openEmail, memberEmailContent, stillRelevant, deliverMemberEmailJob, jobKey, optoutUrl, optoutSignature }
};

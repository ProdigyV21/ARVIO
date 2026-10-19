const { connectLambda, getStore } = require("@netlify/blobs");
const { privacyHash } = require("./_backend");
const { journeyKey, summarizeMeasurements, FAILURE_KINDS, TRANSPORTS, PHASES } = require("./_premium-measurement");

const PREMIUM_EVENTS = new Set([
  "journey_connected", "web_opened", "sources_configured", "sources_missing",
  "playback_requested", "playback_started", "playback_failed",
  "paywall_view",
  "account_connected",
  "trial_requested",
  "trial_started",
  "trial_start_failed",
  "membership_cta_viewed",
  "checkout_opened",
  "checkout_returned",
  "payment_status_checked",
  "checkout_recovery_opened",
  "checkout_recovery_closed",
  "membership_link_started",
  "membership_linked",
  "billing_email_verified",
  "membership_link_failed",
  "first_playback",
  "external_playback_requested",
  "download_requested",
  "download_handoff",
  "download_failed",
  "paid_access_observed",
  "subscription_started",
  "subscription_renewed",
  "trial_email_welcome_sent",
  "trial_email_reminder_sent",
  "trial_email_expired_sent"
]);

// Payment and trial-success events must come from their server handlers, never
// from a browser claiming that payment succeeded.
const CLIENT_PREMIUM_EVENTS = new Set([
  "journey_connected", "web_opened", "sources_configured", "sources_missing",
  "playback_requested", "playback_started", "playback_failed",
  "paywall_view", "account_connected", "trial_requested", "trial_start_failed",
  "membership_cta_viewed", "checkout_opened", "checkout_returned", "payment_status_checked", "checkout_recovery_opened", "checkout_recovery_closed", "membership_link_started", "membership_linked", "membership_link_failed", "first_playback", "external_playback_requested",
  "download_requested", "download_handoff", "download_failed"
]);
const PAYMENT_CHECK_RESULTS = new Set(["pending", "active", "error"]);

function premiumFunnelStore(event) {
  connectLambda(event);
  return getStore("premium-funnel");
}

function sanitizeMetadata(metadata) {
  if (!metadata || typeof metadata !== "object" || Array.isArray(metadata)) return {};
  const result = {};
  const allowed = new Set(["source", "medium", "campaign", "content", "referrer", "playback_type", "entry", "player", "stage", "status", "destination", "duration_days", "tier", "provider", "successful", "billing_key", "failure_kind", "transport", "phase", "flow_version"]);
  for (const [key, value] of Object.entries(metadata).slice(0, 16)) {
    if (!allowed.has(key)) continue;
    if (key === "failure_kind" && !FAILURE_KINDS.has(value)) continue;
    if (key === "transport" && !TRANSPORTS.has(value)) continue;
    if (key === "phase" && !PHASES.has(value)) continue;
    if (key === "flow_version" && value !== "direct_v2") continue;
    if (!/^[a-z][a-z0-9_]{0,31}$/i.test(key)) continue;
    if (typeof value === "boolean" || (typeof value === "number" && Number.isFinite(value))) {
      result[key] = value;
    } else if (typeof value === "string" && !value.includes("@") && !/https?:\/\//i.test(value)) {
      result[key] = value.replace(/[\r\n]/g, " ").slice(0, 120);
    }
  }
  return result;
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

async function recordPaymentCheckResult(store, key, existing, status, at) {
  let current = existing;
  // ETag compare-and-set prevents concurrent pending/active/error callbacks
  // from erasing one another. Three outcomes bound successful writes; retries
  // are bounded too, and analytics must never hold up payment access.
  for (let attempt = 0; attempt < 3; attempt += 1) {
    if (current.observedStatuses?.[status] || current.metadata?.status === status) return current;
    let snapshot;
    try {
      snapshot = await store.getWithMetadata(key, { type: "json", consistency: "strong" });
    } catch (error) {
      if (!String(error?.message || "").includes("uncachedEdgeURL")) throw error;
      snapshot = await store.getWithMetadata(key, { type: "json" });
    }
    if (!snapshot?.data || !snapshot.etag) return current;
    current = snapshot.data;
    const observedStatuses = { ...current.observedStatuses };
    if (PAYMENT_CHECK_RESULTS.has(current.metadata?.status) && !observedStatuses[current.metadata.status]) {
      observedStatuses[current.metadata.status] = current.firstAt;
    }
    if (observedStatuses[status]) return current;
    const record = { ...current, observedStatuses: { ...observedStatuses, [status]: at.toISOString() } };
    const result = await store.setJSON(key, record, { onlyIfMatch: snapshot.etag });
    if (result.modified) return record;
  }
  return current;
}

async function recordPremiumEvent(event, { email, accountId, eventName, metadata = {}, occurredAt, journeyId } = {}) {
  if (!PREMIUM_EVENTS.has(eventName)) throw new Error("Unsupported premium funnel event");
  // Email is the stable join key shared by ARVIO authentication and Ko-fi.
  // Only its keyed HMAC is stored; the raw address never enters this store.
  const identity = String(email || accountId || "").trim().toLowerCase();
  if (!identity) throw new Error("Premium funnel event requires an account identity");

  const at = occurredAt ? new Date(occurredAt) : new Date();
  if (!Number.isFinite(at.getTime())) throw new Error("Invalid premium funnel event date");
  const date = at.toISOString().slice(0, 10);
  const accountKey = privacyHash("premium-funnel-account", identity);
  const store = premiumFunnelStore(event);
  const key = `events/date/${date}/account/${accountKey}/${eventName}.json`;
  const attributionKey = journeyKey(journeyId);
  if (attributionKey) {
    // At most one link per authenticated account/UTC day, even if a client sends
    // arbitrary UUIDs. Keeping it under the account prefix preserves deletion.
    await store.setJSON(`events/date/${date}/account/${accountKey}/_journey.json`, {
      date, accountKey, eventName: "journey_connected", firstAt: at.toISOString(),
      metadata: { journey_key: attributionKey }
    }, { onlyIfNew: true });
  }
  const existing = await getJSON(store, key);
  const safeMetadata = sanitizeMetadata(metadata);
  // A pending check followed by activation must not disappear behind daily
  // deduplication. Retain only the first timestamp of each bounded result,
  // never every poll. The original event timestamp/placement stays immutable.
  if (existing && eventName === "payment_status_checked" && PAYMENT_CHECK_RESULTS.has(safeMetadata.status)) {
    return recordPaymentCheckResult(store, key, existing, safeMetadata.status, at);
  }
  // Reports count unique account-event-days, not clicks. Repeated browser
  // callbacks must not rewrite the same blob or replace its first attribution.
  if (existing && (CLIENT_PREMIUM_EVENTS.has(eventName) || eventName === "paid_access_observed")) return existing;
  if (attributionKey) safeMetadata.journey_key = attributionKey;
  const record = {
    date,
    eventName,
    accountKey,
    metadata: safeMetadata,
    count: Number(existing?.count || 0) + 1,
    firstAt: existing?.firstAt || at.toISOString(),
    updatedAt: at.toISOString()
  };
  if (eventName === "payment_status_checked" && PAYMENT_CHECK_RESULTS.has(safeMetadata.status)) {
    record.observedStatuses = { [safeMetadata.status]: at.toISOString() };
  }
  if (CLIENT_PREMIUM_EVENTS.has(eventName) || eventName === "paid_access_observed") {
    const written = await store.setJSON(key, record, { onlyIfNew: true });
    if (!written.modified) {
      const current = await getJSON(store, key);
      if (current && eventName === "payment_status_checked" && PAYMENT_CHECK_RESULTS.has(safeMetadata.status)) {
        return recordPaymentCheckResult(store, key, current, safeMetadata.status, at);
      }
      return current || record;
    }
  } else await store.setJSON(key, record);

  return record;
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

function dayRange(days, endDate) {
  const result = [];
  const today = endDate ? new Date(`${endDate}T00:00:00Z`) : new Date();
  if (!Number.isFinite(today.getTime())) throw new Error("Invalid report end date");
  for (let offset = Math.max(1, days) - 1; offset >= 0; offset -= 1) {
    result.push(new Date(today.getTime() - offset * 24 * 60 * 60 * 1000).toISOString().slice(0, 10));
  }
  return result;
}

async function premiumFunnelReport(event, days = 30, endDate) {
  const safeDays = Math.floor(Math.min(90, Math.max(1, Number(days) || 30)));
  const today = new Date().toISOString().slice(0, 10);
  const parsedEnd = endDate ? Date.parse(`${endDate}T00:00:00Z`) : null;
  if (endDate && (!/^\d{4}-\d{2}-\d{2}$/.test(endDate) || endDate > today || !Number.isFinite(parsedEnd) || new Date(parsedEnd).toISOString().slice(0, 10) !== endDate)) {
    const error = new Error("Invalid report end date");
    error.statusCode = 400;
    throw error;
  }
  const store = premiumFunnelStore(event);
  const dates = dayRange(safeDays, endDate);
  const keys = [];
  const allKeys = await listKeys(store, "events/date/");
  const dateSet = new Set(dates);
  keys.push(...allKeys.filter(key => dateSet.has(key.split("/")[2])));
  // Only the server-side successful ownership flow can write this event. Do not
  // infer a billing identity from browser events or a typed email address.
  const verifiedLinks = [];
  for (const key of allKeys.filter(key => key.endsWith('/billing_email_verified.json') && key.split("/")[2] <= dates.at(-1))) {
    const record = await getJSON(store, key);
    if (/^[a-f0-9]{64}$/.test(record?.metadata?.billing_key || "") && /^[a-f0-9]{64}$/.test(record?.accountKey || "")) {
      verifiedLinks.push({ billingKey: record.metadata.billing_key, accountKey: record.accountKey });
    }
  }
  const readMany = async keys => {
    const records = [];
    for (let offset = 0; offset < keys.length; offset += 32) {
      records.push(...(await Promise.all(keys.slice(offset, offset + 32).map(key => getJSON(store, key)))).filter(Boolean));
    }
    return records;
  };
  // A payment in a short report can belong to a visit before the report start.
  // Reuse retained, authenticated links; never guess ownership from a click.
  const contextStart = new Date(Date.parse(`${dates[0]}T00:00:00Z`) - 30 * 86400000).toISOString().slice(0, 10);
  const inContext = key => key.split("/")[2] >= contextStart && key.split("/")[2] < dates[0];
  const allJourneyKeys = await listKeys(store, "journeys/date/");
  const journeyKeys = allJourneyKeys.filter(key => dateSet.has(key.split("/")[2]));
  const detailedEvents = new Set(["_journey", "trial_started", "subscription_started", "paid_access_observed", "membership_cta_viewed", "checkout_opened", "checkout_returned", "payment_status_checked", "checkout_recovery_opened", "checkout_recovery_closed", "membership_link_started", "membership_linked", "membership_link_failed", "billing_email_verified", "playback_failed"]);
  const detailKeys = keys.filter(key => detailedEvents.has(key.split("/")[5]?.replace(/\.json$/, "")));
  // Look back only for actual new payers, not every historic visitor or trial.
  // This avoids turning a small period report into thousands of extra reads.
  const accountKeys = new Set(keys.filter(key => key.endsWith("/subscription_started.json")).map(key => key.split("/")[4]));
  for (const { billingKey, accountKey } of verifiedLinks) if (accountKeys.has(billingKey)) accountKeys.add(accountKey);
  const contextLinkKeys = allKeys.filter(key => inContext(key) && key.endsWith("/_journey.json") && accountKeys.has(key.split("/")[4]));
  const [records, journeys, contextRecords] = await Promise.all([
    readMany(detailKeys), readMany(journeyKeys), readMany(contextLinkKeys)
  ]);
  const contextJourneyIds = new Set(contextRecords.map(record => record.metadata?.journey_key).filter(Boolean));
  const contextJourneys = await readMany(allJourneyKeys.filter(key => inContext(key) && contextJourneyIds.has(key.split("/")[3])));
  const generatedAt = new Date().toISOString();
  const observationEnd = endDate && endDate < today ? new Date(Date.parse(endDate) + 86400000).toISOString() : generatedAt;
  const summary = summarizePremiumKeys(keys, dates, observationEnd, verifiedLinks);
  return { ...summary, generatedAt, periodStart: dates[0], periodEnd: dates.at(-1),
    ...summarizeMeasurements(records, journeys, verifiedLinks, { contextRecords, contextJourneys }),
    attributionLookback: { days: 30, contextStart, note: "Earlier retained journeys can explain payments in this period; they do not increase period visit counts. Expired or unlinked journeys remain unattributed." } };

}

function summarizePremiumKeys(keys, dates, generatedAt = new Date().toISOString(), verifiedLinks = []) {
  const counts = {};
  const unique = {};
  const daily = Object.fromEntries(dates.map(date => [date, {}]));
  const firstDates = {};
  const eventDates = {};
  const billingOwners = new Map();
  for (const { billingKey, accountKey } of verifiedLinks) {
    if (!billingKey || !accountKey) continue;
    // Ambiguous transfers must not attribute one payment to multiple accounts.
    const prior = billingOwners.get(billingKey);
    billingOwners.set(billingKey, prior === undefined || prior === accountKey ? accountKey : null);
  }

  for (const key of new Set(keys)) {
      const parts = key.split("/");
      const date = parts[2];
      let accountKey = parts[4] || "";
      const eventName = String(parts[5] || "").replace(/\.json$/, "");
      if (eventName === "subscription_started" || eventName === "subscription_renewed") accountKey = billingOwners.get(accountKey) || accountKey;
      if (!daily[date] || !PREMIUM_EVENTS.has(eventName) || !accountKey) continue;
      counts[eventName] = (counts[eventName] || 0) + 1;
      const dailyCounts = daily[date];
      dailyCounts[eventName] = (dailyCounts[eventName] || 0) + 1;
      if (!unique[eventName]) unique[eventName] = new Set();
      unique[eventName].add(accountKey);
      const account = firstDates[accountKey] ||= {};
      if (!account[eventName] || date < account[eventName]) account[eventName] = date;
      const accountDates = eventDates[accountKey] ||= {};
      (accountDates[eventName] ||= new Set()).add(date);
  }

  const uniqueAccounts = Object.fromEntries(
    Object.entries(unique).map(([name, accounts]) => [name, accounts.size])
  );
  const connected = uniqueAccounts.account_connected || 0;
  const trials = uniqueAccounts.trial_started || 0;
  const accounts = Object.values(firstDates);
  const transitioned = (row, from, to) => Boolean(row[from] && row[to] && row[to] >= row[from]);
  const connectedTrials = accounts.filter(row => transitioned(row, "account_connected", "trial_started")).length;
  const trialPaid = accounts.filter(row => transitioned(row, "trial_started", "subscription_started")).length;
  const matureTrials = accounts.filter(row => row.trial_started && Date.parse(row.trial_started) + 4 * 86400000 <= Date.parse(generatedAt));
  const observedCohort = days => {
    // Only day precision is available: allow the entire start day plus N full
    // days before including a trial in this denominator.
    const eligible = accounts.filter(row => row.trial_started && Date.parse(row.trial_started) + (days + 1) * 86400000 <= Date.parse(generatedAt));
    const paid = eligible.filter(row => transitioned(row, "trial_started", "subscription_started") && Date.parse(row.subscription_started) < Date.parse(row.trial_started) + (days + 1) * 86400000).length;
    return { observationDays: days, eligibleTrials: eligible.length, paidWithinWindow: paid, rate: eligible.length ? Number((paid / eligible.length).toFixed(4)) : null };
  };
  const trialUsage = event => Object.entries(firstDates).filter(([account, row]) => row.trial_started && [...(eventDates[account][event] || [])].some(date => date >= row.trial_started)).length;
  return {
    days: dates.length,
    generatedAt,
    timezone: "UTC",
    includesPartialToday: dates.includes(generatedAt.slice(0, 10)),
    eventDays: counts,
    uniqueAccounts,
    conversion: {
      connectedToTrial: connected ? Number((connectedTrials / connected).toFixed(4)) : null,
      trialToPaid: trials ? Number((trialPaid / trials).toFixed(4)) : null
    },
    trialCohort: {
      trials, paidByReportEnd: trialPaid,
      atLeastThreeCompleteDaysObserved: matureTrials.length,
      maturePaidByReportEnd: matureTrials.filter(row => transitioned(row, "trial_started", "subscription_started")).length
    },
    maturedCohorts: { sevenDays: observedCohort(7), fourteenDays: observedCohort(14) },
    trialActivation: {
      sourcesConfigured: trialUsage("sources_configured"),
      playbackRequested: trialUsage("playback_requested"),
      browserPlaybackStarted: trialUsage("playback_started"),
      browserPlaybackFailed: trialUsage("playback_failed"),
      externalPlayerRequested: trialUsage("external_playback_requested"),
      checkoutOpened: trialUsage("checkout_opened")
    },
    trialDropoff: {
      unit: "accounts with trials started in this report; stages can overlap",
      trials,
      withoutRecordedSourceSetup: trials - trialUsage("sources_configured"),
      withoutRecordedBrowserPlayback: trials - trialUsage("playback_started"),
      withoutRecordedMembershipClick: trials - trialUsage("checkout_opened"),
      note: "Missing diagnostics do not prove failure. This is not a sequential funnel or a mature payment-conversion cohort."
    },
    measurementNotes: [
      "Conversion matches anonymized account identities inside this window, with day-level ordering; it is not a lifetime cohort.",
      "Different billing emails are joined only after verified ownership in retained history before the report end; unverified links remain unmatched. Renewals are separate from starts.",
      "Event-days are deduplicated per account/event/day, not total clicks. External-player and download handoffs do not confirm successful playback or completed downloads.",
      "checkout_opened means a membership page link was opened, not a verified checkout/payment attempt. Ko-fi does not report abandoned checkouts.",
      "Membership CTA visibility, direct_v2 flow labels and recovery open/close diagnostics begin with the October 19, 2026 release. Missing historical events mean unmeasured stages, not abandonment. Each event retains its first placement/version per account/UTC day, not every impression or click.",
      "Payment status checks retain the first timestamp per pending/active/error result per account/UTC day from the October 19 release. Browser-reported active results do not establish a confirmed payment; earlier records retain only their first result.",
      "Anonymous journeys are transient navigation identifiers, not unique visitors; storage blockers, shared networks and a 256-event/hour budget can undercount. Direct Ko-fi payments may remain unattributed. Journey events expire after 30 days.",
      "Paid access observed confirms an authenticated server access check, not successful playback. Daily failure categories describe the first reported failure, never an attempt-level failure rate.",
      "Daily activation diagnostics begin with the September 2026 activation release; missing earlier diagnostics mean unmeasured usage, not failed playback. Cohorts use full calendar days and exclude trials without enough observation time."
    ],
    daily
  };
}

async function cleanupPremiumFunnel(event, retentionDays = 90) {
  const store = premiumFunnelStore(event);
  const cutoff = new Date(Date.now() - Math.max(1, retentionDays) * 24 * 60 * 60 * 1000)
    .toISOString()
    .slice(0, 10);
  const keys = await listKeys(store, "events/date/");
  const expired = keys.filter((key) => {
    const date = key.split("/")[2] || "";
    return /^\d{4}-\d{2}-\d{2}$/.test(date) && date < cutoff;
  });
  const journeyCutoff = new Date(Date.now() - 30 * 86400000).toISOString().slice(0, 10);
  const budgetCutoff = new Date(Date.now() - 2 * 86400000).toISOString().slice(0, 10);
  for (const [prefix, before] of [["journeys/date/", journeyCutoff], ["visit-budget/date/", budgetCutoff]]) {
    expired.push(...(await listKeys(store, prefix)).filter(key => /^\d{4}-\d{2}-\d{2}$/.test(key.split("/")[2]) && key.split("/")[2] < before));
  }
  for (const key of expired) await store.delete(key).catch(() => {});
  return expired.length;
}

module.exports = {
  PREMIUM_EVENTS,
  CLIENT_PREMIUM_EVENTS,
  premiumFunnelStore,
  recordPremiumEvent,
  premiumFunnelReport,
  cleanupPremiumFunnel,
  _test: { sanitizeMetadata, dayRange, summarizePremiumKeys }
};

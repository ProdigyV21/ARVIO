const { privacyHash } = require("./_backend");

const JOURNEY_EVENTS = new Set(["premium_page_view", "membership_clicked", "web_clicked", "guide_page_view"]);
const GUIDE_PAGES = new Set(["guides", "android", "jellyfin", "servers", "addons", "debrid", "tracking", "live", "subtitles", "firetv", "browser", "collections", "selfhost", "playback"]);
const UUID = /^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[a-f0-9]{12}$/i;
const FAILURE_KINDS = new Set(["network", "format", "timeout", "browser_restriction", "engine", "unknown"]);
const TRANSPORTS = new Set(["file", "hls", "dash", "mpegts", "remux"]);
const PHASES = new Set(["startup", "playback"]);

function journeyKey(value) {
  return UUID.test(String(value || "")) ? privacyHash("premium-journey", value.toLowerCase()) : null;
}

function marketingMetadata(input) {
  const result = {};
  for (const name of ["source", "medium", "campaign", "content"]) {
    const value = input?.[name];
    // Drop addresses/URLs entirely rather than turning private input into a tag.
    if (typeof value === "string" && /^[a-z0-9._-]{1,80}$/i.test(value)) result[name] = value.toLowerCase();
  }
  if (["home", "premium", "handoff"].includes(input?.page) || GUIDE_PAGES.has(input?.page)) result.page = input.page;
  return result;
}

// Atomic limits bound storage writes even if visitors rotate their random ID.
// Only a daily salted HMAC of the network address is retained, never the IP.
async function claimVisitBudget(store, event, seed, now = new Date()) {
  const date = now.toISOString().slice(0, 10);
  const headers = event.headers || {};
  const address = headers["x-nf-client-connection-ip"] || headers["client-ip"] || "unknown";
  const identity = privacyHash(`premium-visit-budget:${date}`, address);
  const minute = Math.floor(now.getTime() / 60000);
  const hour = Math.floor(now.getTime() / 3600000);
  const start = parseInt(privacyHash("premium-budget-slot", seed).slice(0, 8), 16) % 256;
  let globalSlot = false;
  // Hard ceiling: 256 anonymous event writes/hour across the whole site. Probe
  // a few slots only; loss under spikes is preferable to unbounded analytics.
  for (let offset = 0; offset < 5; offset += 1) {
    const claim = await store.setJSON(`visit-budget/date/${date}/global/${hour}-${(start + offset) % 256}.json`, { at: now.toISOString() }, { onlyIfNew: true });
    if (claim.modified) { globalSlot = true; break; }
  }
  if (!globalSlot) return false;
  // Three events per minute/network. A shared network or blockers can undercount;
  // these are diagnostic journeys, not a claim of unique human visitors.
  for (let slot = 0; slot < 3; slot += 1) {
    const result = await store.setJSON(`visit-budget/date/${date}/${identity}/${minute}-${slot}.json`, { at: now.toISOString() }, { onlyIfNew: true });
    if (result.modified) return true;
  }
  return false;
}

async function recordJourneyEvent(store, event, body, now = new Date()) {
  const key = journeyKey(body?.journey_id);
  if (!key || !JOURNEY_EVENTS.has(body?.event_name)) return { status: 400, error: "unsupported_event" };
  const date = now.toISOString().slice(0, 10);
  const blobKey = `journeys/date/${date}/${key}/${body.event_name}.json`;
  let existing;
  try {
    existing = await store.get(blobKey, { type: "json", consistency: "strong" });
  } catch (error) {
    // Older Lambda/CLI contexts omit the uncached endpoint. Match the existing
    // authenticated funnel compatibility read; the write is still atomic, so an
    // eventually-consistent cache miss cannot duplicate the actual event.
    if (!String(error?.message || "").includes("uncachedEdgeURL")) throw error;
    existing = await store.get(blobKey, { type: "json" });
  }
  if (existing) return { status: 200 };
  if (!await claimVisitBudget(store, event, blobKey, now)) return { status: 429, error: "rate_limited" };
  await store.setJSON(blobKey, {
    date, journeyKey: key, eventName: body.event_name,
    metadata: marketingMetadata(body.metadata), firstAt: now.toISOString()
  }, { onlyIfNew: true });
  return { status: 200 };
}

function summarizeMeasurements(records, journeys, verifiedLinks = []) {
  const owners = new Map();
  for (const { billingKey, accountKey } of verifiedLinks) {
    const previous = owners.get(billingKey);
    owners.set(billingKey, previous === undefined || previous === accountKey ? accountKey : null);
  }
  const accounts = new Map();
  const journeyOwners = new Map();
  const journeyConnections = new Map();
  const diagnostics = { failureKind: {}, transport: {}, phase: {} };
  for (const record of records) {
    let id = record.accountKey;
    if (["subscription_started", "subscription_renewed"].includes(record.eventName)) id = owners.get(id) || id;
    if (!id) continue;
    const account = accounts.get(id) || {};
    const time = record.firstAt || `${record.date}T00:00:00.000Z`;
    if (!account[record.eventName] || time < account[record.eventName]) account[record.eventName] = time;
    (account.times ||= {});
    (account.times[record.eventName] ||= []).push(time);
    accounts.set(id, account);
    const journey = record.metadata?.journey_key;
    if (/^[a-f0-9]{64}$/.test(journey || "")) {
      const previous = journeyOwners.get(journey);
      journeyOwners.set(journey, previous === undefined || previous === id ? id : null);
      const connection = journeyConnections.get(journey);
      if (!connection || time < connection) journeyConnections.set(journey, time);
    }
    if (record.eventName === "playback_failed") {
      for (const [field, input, allowed] of [["failureKind", "failure_kind", FAILURE_KINDS], ["transport", "transport", TRANSPORTS], ["phase", "phase", PHASES]]) {
        const value = allowed.has(record.metadata?.[input]) ? record.metadata[input] : "unclassified";
        diagnostics[field][value] = (diagnostics[field][value] || 0) + 1;
      }
    }
  }
  const grouped = new Map();
  for (const record of journeys) {
    if (!JOURNEY_EVENTS.has(record.eventName) || !record.journeyKey) continue;
    const journey = grouped.get(record.journeyKey) || { events: new Set(), firstAt: record.firstAt, metadata: record.metadata || {} };
    journey.events.add(record.eventName);
    if (record.firstAt < journey.firstAt) { journey.firstAt = record.firstAt; journey.metadata = record.metadata || {}; }
    grouped.set(record.journeyKey, journey);
  }
  const empty = () => ({ journeys: 0, guidePageViews: 0, premiumPageViews: 0, membershipClicks: 0, webClicks: 0, connectedAccounts: 0, trialsStarted: 0, confirmedNewMemberships: 0, paidAccessObserved: 0 });
  const total = empty();
  const sources = new Map();
  const landings = new Map();
  const matched = new Set();
  const paid = new Set();
  for (const [key, journey] of grouped) {
    const source = marketingMetadata(journey.metadata).source || "unattributed";
    const row = sources.get(source) || empty();
    const landing = marketingMetadata(journey.metadata).page || "unattributed";
    const landingRow = landings.get(landing) || empty();
    const accountId = journeyOwners.get(key);
    // One account can navigate through multiple anonymous landing pages. Count
    // its conversion once, attributing to the earliest measured journey below.
    journey.accountId = accountId;
    journey.connectedAt = journeyConnections.get(key);
    journey.source = source;
    journey.landing = landing;
    for (const target of [total, row, landingRow]) {
      target.journeys++;
      if (journey.events.has("guide_page_view")) target.guidePageViews++;
      if (journey.events.has("premium_page_view")) target.premiumPageViews++;
      if (journey.events.has("membership_clicked")) target.membershipClicks++;
      if (journey.events.has("web_clicked")) target.webClicks++;
    }
    sources.set(source, row);
    landings.set(landing, landingRow);
  }
  for (const journey of [...grouped.values()].sort((a, b) => a.firstAt.localeCompare(b.firstAt))) {
    const id = journey.accountId;
    if (!id || matched.has(id)) continue;
    matched.add(id);
    const account = accounts.get(id) || {};
    const row = sources.get(journey.source);
    const observed = event => (account.times?.[event] || []).some(time => time >= journey.firstAt);
    for (const target of [total, row, landings.get(journey.landing)]) {
      if (journey.connectedAt >= journey.firstAt) target.connectedAccounts++;
      if (observed("trial_started")) target.trialsStarted++;
      if (observed("subscription_started")) { target.confirmedNewMemberships++; paid.add(id); }
      if (observed("paid_access_observed")) target.paidAccessObserved++;
    }
  }
  let payments = 0, activated = 0, paymentAfterMembershipClick = 0;
  for (const account of accounts.values()) {
    if (!account.subscription_started) continue;
    payments++;
    if ((account.times?.paid_access_observed || []).some(time => time >= account.subscription_started)) activated++;
    if (account.checkout_opened && account.checkout_opened <= account.subscription_started) paymentAfterMembershipClick++;
  }
  return {
    journeys: { ...total, bySource: Object.fromEntries([...sources].sort(([a], [b]) => a.localeCompare(b))), byLandingPage: Object.fromEntries([...landings].sort(([a], [b]) => a.localeCompare(b))), confirmedPaymentsNotMatchedToJourney: Math.max(0, payments - paid.size) },
    paymentActivation: { confirmedNewMemberships: payments, paidAccessObservedAfterPayment: activated, accessNotYetObservedInWindow: payments - activated, paymentAfterMembershipPageClick: paymentAfterMembershipClick },
    playbackDiagnostics: { unit: "account-event-day", classification: "first recorded failure of each account per UTC day", ...diagnostics }
  };
}

module.exports = { journeyKey, marketingMetadata, recordJourneyEvent, summarizeMeasurements, FAILURE_KINDS, TRANSPORTS, PHASES };

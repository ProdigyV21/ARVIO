const crypto = require("node:crypto");

const PLANS = Object.freeze({
  "ARVIO Partner 20": { seats: 20, cents: 3900 },
  "ARVIO Partner 50": { seats: 50, cents: 7900 },
  "ARVIO Partner 100": { seats: 100, cents: 12900 },
  "ARVIO Partner 200": { seats: 200, cents: 22900 }
});
const DEFAULT_PERMISSIONS = Object.freeze({
  editCatalogs: false, addConnections: false, editAppearance: true,
  editPlayback: true, editMetadata: false
});
const BOOLEAN_SETTINGS = new Set([
  "netflixHomeStyle", "watchlistOnHome", "trailerAutoPlay", "trailerInCards",
  "trailerSoundEnabled", "autoPlayNext", "autoPlaySingleSource", "showBudget",
  "showEpisodeRatings", "iptvFavoritesOnHome", "spoilerBlurEnabled", "includeSpecials"
]);
const ENUM_SETTINGS = {
  cardLayoutMode: ["landscape", "poster"], clockFormat: ["12h", "24h"],
  streamSearchMode: ["parallel", "sequential"], animeEpisodeStructuring: ["broadcast", "standard"]
};
const CATALOG_KEYS = new Set([
  "id", "name", "title", "sourceType", "mediaType", "sourceUrl", "sourceRef", "endpoint",
  "params", "enabled", "layout", "kind", "collectionGroup", "collectionRailKey",
  "collectionCoverImageUrl", "collectionHeroImageUrl", "collectionDescription",
  "collectionTileShape", "collectionHideTitle", "collectionSources", "packId", "packName",
  "addonId", "addonCatalogType", "addonCatalogId", "addonName", "addonGenre",
  "collectionFocusGifUrl", "collectionHeroVideoUrl", "collectionClearLogoUrl", "requiredAddonUrls"
]);

function fail(status, message) { throw Object.assign(new Error(message), { statusCode: status }); }
function record(value) { return value && typeof value === "object" && !Array.isArray(value) ? value : {}; }
function text(value, max = 100, required = true) {
  if (typeof value !== "string" || value.length > max || /[\x00-\x1f]/.test(value)) fail(400, "Invalid text value");
  const result = value.trim();
  if (required && !result) fail(400, "A required field is empty");
  return result;
}
function credentialText(value, required = false) {
  if (typeof value !== "string" || value.length > 4096 || /[\x00-\x1f]/.test(value) || required && !value.length) fail(400, "Invalid credential value");
  return value;
}
function id(value) {
  if (!/^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[a-f0-9]{12}$/i.test(String(value))) fail(400, "Invalid identifier");
  return value;
}
function email(value) {
  const result = text(value, 254).toLowerCase();
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(result)) fail(400, "Enter a valid email address");
  return result;
}
function url(value, { local = false } = {}) {
  let parsed;
  try { parsed = new URL(text(value, 4096)); } catch { fail(400, "Enter a valid service URL"); }
  if (!(local ? ["http:", "https:"] : ["https:"]).includes(parsed.protocol) || parsed.username || parsed.password || parsed.hash) {
    fail(400, "Use an HTTPS URL without embedded username or password");
  }
  return parsed.href;
}
function hash(value) { return crypto.createHash("sha256").update(String(value)).digest("hex"); }
function equal(a, b) {
  const aa = Buffer.from(String(a)); const bb = Buffer.from(String(b));
  return aa.length === bb.length && crypto.timingSafeEqual(aa, bb);
}

function encryptionKey(organizationId, master = process.env.PARTNER_ENCRYPTION_KEY) {
  if (!/^[a-f0-9]{64}$/i.test(master || "")) fail(503, "Partner encryption is not configured");
  return Buffer.from(crypto.hkdfSync("sha256", Buffer.from(master, "hex"), Buffer.from(organizationId), Buffer.from("arvio-partner-v1"), 32));
}
function seal(value, organizationId, purpose, master) {
  const nonce = crypto.randomBytes(12);
  const cipher = crypto.createCipheriv("aes-256-gcm", encryptionKey(organizationId, master), nonce);
  cipher.setAAD(Buffer.from(`${organizationId}:${purpose}:v1`));
  const encrypted = Buffer.concat([cipher.update(JSON.stringify(value), "utf8"), cipher.final()]);
  return { version: 1, nonce: nonce.toString("base64"), data: encrypted.toString("base64"), tag: cipher.getAuthTag().toString("base64") };
}
function unseal(envelope, organizationId, purpose, master) {
  if (envelope?.version !== 1) fail(503, "Unsupported connection key version");
  const cipher = crypto.createDecipheriv("aes-256-gcm", encryptionKey(organizationId, master), Buffer.from(envelope.nonce, "base64"));
  cipher.setAAD(Buffer.from(`${organizationId}:${purpose}:v1`));
  cipher.setAuthTag(Buffer.from(envelope.tag, "base64"));
  return JSON.parse(Buffer.concat([cipher.update(Buffer.from(envelope.data, "base64")), cipher.final()]).toString("utf8"));
}

function validateConfig(input) {
  const source = record(input);
  if (JSON.stringify(source).length > 256000) fail(400, "Template configuration exceeds 256 KB");
  for (const key of Object.keys(source)) if (!["settings", "catalogs", "collectionUrls", "serviceIds"].includes(key)) fail(400, `Unsupported template field: ${key}`);
  const settings = {};
  for (const [key, value] of Object.entries(record(source.settings))) {
    if (BOOLEAN_SETTINGS.has(key) && typeof value === "boolean") settings[key] = value;
    else if (Object.hasOwn(ENUM_SETTINGS, key) && ENUM_SETTINGS[key].includes(value)) settings[key] = value;
    else fail(400, `Unsupported setting: ${key}`);
  }
  const catalogs = source.catalogs ?? [];
  if (!Array.isArray(catalogs) || catalogs.length > 200) fail(400, "A template supports up to 200 catalogs");
  const seen = new Set();
  const safeCatalogs = catalogs.map((entry) => {
    const catalog = record(entry);
    for (const key of Object.keys(catalog)) if (!CATALOG_KEYS.has(key)) fail(400, `Unsupported catalog field: ${key}`);
    const key = text(catalog.id, 160);
    if (seen.has(key)) fail(400, "Duplicate catalog identifier");
    seen.add(key);
    text(catalog.name || catalog.title, 160);
    // Catalog configuration is portable, never an account snapshot. Known
    // credential fields anywhere inside a nested discover/list object are rejected.
    const walk = (obj, depth = 0) => {
      if (!obj || typeof obj !== "object") return;
      if (depth > 32) fail(400, "Catalog configuration is nested too deeply");
      for (const [name, value] of Object.entries(obj)) {
        if (/token|password|secret|api.?key|watch.?history|watched|traktTokens|simklTokens/i.test(name)) fail(400, "Credentials and history cannot be copied into a template");
        if (name === "__proto__" || name === "constructor" || name === "prototype") fail(400, "Invalid configuration key");
        if (typeof value === "string" && /^(javascript|data|file):/i.test(value)) fail(400, "Invalid catalog URL");
        if (typeof value === "string" && /^https?:\/\//i.test(value)) publicFeed(value);
        walk(value, depth + 1);
      }
    };
    if (JSON.stringify(catalog).length > 24000) fail(400, "Catalog configuration is too large");
    walk(catalog);
    const sourceType = String(catalog.sourceType || "").toLowerCase().replace(/_/g, "-");
    if (!["preinstalled", "tmdb", "mdblist", "trakt", "simkl", "addon", "home-server", "template", "youtube"].includes(sourceType)) fail(400, "Choose a supported catalog sourceType");
    if (catalog.enabled !== undefined && typeof catalog.enabled !== "boolean") fail(400, "Catalog enabled must be true or false");
    return { ...structuredClone(catalog), id: key, name: text(catalog.name || catalog.title, 160), sourceType, enabled: catalog.enabled !== false };
  });
  if (!Array.isArray(source.collectionUrls ?? []) || (source.collectionUrls ?? []).length > 30) fail(400, "Too many collection feeds");
  const collectionUrls = [...new Set((source.collectionUrls || []).map((value) => publicFeed(value)))];
  if (!Array.isArray(source.serviceIds ?? []) || (source.serviceIds ?? []).length > 30) fail(400, "Choose up to 30 template connections");
  return { settings, catalogs: safeCatalogs, collectionUrls, ...(source.serviceIds ? { serviceIds: [...new Set(source.serviceIds.map(id))] } : {}) };
}
function iptvOptions(input = {}) {
  const defaults = { mode: "auto", liveTv: true, movies: true, series: true, epg: true, catchup: true };
  if (!input || typeof input !== "object" || Array.isArray(input)) fail(400, "Invalid IPTV options");
  for (const [key, value] of Object.entries(input)) {
    if (!Object.hasOwn(defaults, key) || (key === "mode" ? !["auto", "xtream", "m3u"].includes(value) : typeof value !== "boolean")) fail(400, "Invalid IPTV option");
    defaults[key] = value;
  }
  if (!defaults.liveTv && !defaults.movies && !defaults.series) fail(400, "Select Live TV, movies or series");
  defaults.epg = defaults.liveTv && defaults.epg;
  defaults.catchup = defaults.epg && defaults.catchup;
  return defaults;
}

function iptvCredentials(input, service) {
  const raw = record(input);
  if (Object.keys(raw).some(key => !["username", "password", "playlistUrl", "epgUrl"].includes(key))) fail(400, "Invalid IPTV credentials");
  const result = {};
  for (const [key, value] of Object.entries(raw)) {
    if (value === "") continue;
    result[key] = key === "password" ? credentialText(value, true) : text(value, 4096);
  }
  if (result.playlistUrl) result.playlistUrl = url(result.playlistUrl, { local: true });
  if (result.epgUrl) result.epgUrl = url(result.epgUrl, { local: true });
  const mode = iptvOptions(service.iptv).mode;
  if (mode === "m3u" ? !result.playlistUrl : mode === "xtream" ? !result.username || !result.password || result.playlistUrl : !result.playlistUrl && !(result.username && result.password)) fail(400, "Enter a playlist URL or the required Xtream credentials");
  if (result.playlistUrl && (result.username || result.password)) fail(400, "Choose either a playlist URL or Xtream credentials");
  return result;
}
function publicFeed(value) {
  const parsed = new URL(url(value));
  for (const key of parsed.searchParams.keys()) {
    if (/token|password|secret|api.?key|authorization/i.test(key)) fail(400, "Use a public collection feed; assign private service credentials separately");
  }
  return parsed.href;
}

// A published template shared by 200 customers should be stored once, not
// 200 times per revision. References never leave the backend.
function packState(state) {
  const packed = structuredClone(state);
  const configurations = {};
  const intern = (value) => { const key = hash(JSON.stringify(value)); configurations[key] = value; return key; };
  for (const customer of packed.customers) {
    if (customer.copiedConfig) { customer.copiedConfigRef = intern(customer.copiedConfig); delete customer.copiedConfig; }
    for (const version of [...(customer.history || []), ...(customer.delivery ? [customer.delivery] : [])]) {
      if (version.config) { version.configRef = intern(version.config); delete version.config; }
    }
  }
  const result = { ...packed, storageVersion: 1, configurations };
  if (Buffer.byteLength(JSON.stringify(result)) > 16 * 1024 * 1024) fail(400, "Workspace configuration is too large. Reduce unused templates and catalog data.");
  return result;
}
function unpackState(stored) {
  const state = structuredClone(stored);
  if (!state.storageVersion) return state;
  if (state.storageVersion !== 1) fail(503, "Unsupported workspace storage version");
  const get = (ref) => { const value = state.configurations?.[ref]; if (!value) fail(503, "Published configuration is unavailable"); return value; };
  for (const customer of state.customers) {
    if (customer.copiedConfigRef) { customer.copiedConfig = get(customer.copiedConfigRef); delete customer.copiedConfigRef; }
    for (const version of [...(customer.history || []), ...(customer.delivery ? [customer.delivery] : [])]) {
      if (version.configRef) { version.config = get(version.configRef); delete version.configRef; }
    }
  }
  delete state.configurations; delete state.storageVersion;
  return state;
}

function permissions(input = {}) {
  const result = { ...DEFAULT_PERMISSIONS };
  for (const [key, value] of Object.entries(record(input))) {
    if (!(key in DEFAULT_PERMISSIONS) || typeof value !== "boolean") fail(400, "Invalid permission");
    result[key] = value;
  }
  return result;
}

function nextMonth(iso) {
  const date = new Date(iso);
  if (!Number.isFinite(date.getTime())) fail(400, "Invalid payment timestamp");
  const day = date.getUTCDate();
  date.setUTCDate(1); date.setUTCMonth(date.getUTCMonth() + 1);
  const last = new Date(Date.UTC(date.getUTCFullYear(), date.getUTCMonth() + 1, 0)).getUTCDate();
  date.setUTCDate(Math.min(day, last));
  return date.toISOString();
}
function parsePayment(data, now = Date.now()) {
  if (!Object.hasOwn(PLANS, data?.tier_name)) return null;
  if (data.is_subscription_payment !== true || String(data.type).toLowerCase() !== "subscription") fail(400, "Partner access requires a membership payment");
  const plan = PLANS[data.tier_name];
  if (!/^\d+(\.\d{1,2})?$/.test(String(data.amount))) fail(400, "Invalid payment amount");
  const cents = Math.round(Number(data.amount) * 100);
  if (data.currency !== "USD" || cents < plan.cents || !Number.isSafeInteger(cents)) fail(400, "Partner payment amount or currency does not match the plan");
  const paidAt = Date.parse(data.timestamp);
  if (!Number.isFinite(paidAt) || paidAt > now + 300000) fail(400, "Invalid payment date");
  return {
    transactionId: text(data.kofi_transaction_id, 150), emailHash: hash(email(data.email)),
    tier: data.tier_name, cents, paidAt: new Date(paidAt).toISOString(), paidUntil: nextMonth(paidAt)
  };
}
function billing(payments, now = Date.now()) {
  const valid = payments.filter((p) => !p.revoked_at && PLANS[p.tier]);
  const paid = valid.filter((p) => Date.parse(p.paid_until) > now);
  const grace = valid.filter((p) => Date.parse(p.paid_until) + 3 * 86400000 > now);
  const candidates = paid.length ? paid : grace;
  const best = candidates.sort((a, b) => PLANS[b.tier].seats - PLANS[a.tier].seats || Date.parse(b.paid_until) - Date.parse(a.paid_until))[0];
  const last = valid.sort((a, b) => Date.parse(b.paid_until) - Date.parse(a.paid_until))[0];
  return {
    status: best ? (paid.length ? "active" : "grace") : last ? "expired" : "unpaid",
    tier: (best || last)?.tier ?? null, seats: best ? PLANS[best.tier].seats : 0,
    paidUntil: (best || last)?.paid_until ?? null,
    graceUntil: (best || last) ? new Date(Date.parse((best || last).paid_until) + 3 * 86400000).toISOString() : null
  };
}

function newState(name) {
  return { name: text(name), templates: [], packages: [], services: [], customers: [], devices: [], audit: [] };
}
function activeSeats(state) { return state.customers.filter((c) => c.status === "active" || c.status === "invited").length; }
function requireCapacity(state, entitlement, count = 1) {
  if (entitlement.status !== "active") fail(402, "An active Partner membership is required to add or enable customers");
  if (activeSeats(state) + count > entitlement.seats) fail(409, "This plan has no free customer seats. Upgrade or suspend an account first.");
}
function find(items, value) { const item = items.find((i) => i.id === id(value)); if (!item) fail(404, "Item not found"); return item; }
function configFor(state, customer) {
  const pkg = customer.packageId ? state.packages.find((p) => p.id === customer.packageId) : null;
  const template = pkg?.templateId ? state.templates.find((t) => t.id === pkg.templateId) : null;
  const config = customer.copiedConfig || template?.config || validateConfig({});
  const serviceIds = [...new Set([...(config.serviceIds || []), ...(pkg?.serviceIds || [])])];
  if (serviceIds.length > 30) fail(400, "A package and its template support up to 30 connections together");
  return { config, permissions: pkg?.permissions || permissions(), serviceIds,
    serviceSnapshots: serviceIds.map((serviceId) => structuredClone(find(state.services, serviceId))) };
}
function publish(state, customer, now) {
  const current = configFor(state, customer);
  customer.history = [...(customer.history || []), ...(customer.delivery ? [customer.delivery] : [])].slice(-12);
  customer.delivery = { ...structuredClone(current), revision: (customer.delivery?.revision || 0) + 1, publishedAt: now };
}
function republishCurrent(customer, now) {
  const current = customer.delivery;
  if (!current) return;
  customer.history = [...(customer.history || []), current].slice(-12);
  customer.delivery = { ...structuredClone(current), revision: current.revision + 1, publishedAt: now };
}
function audit(state, actor, action, target, now) {
  state.audit = [...state.audit, { id: crypto.randomUUID(), actor, action, target, at: now }].slice(-1000);
}

function mutate(original, command, context) {
  const state = structuredClone(original);
  const { organizationId, actor, entitlement, master } = context;
  const now = new Date(context.now || Date.now()).toISOString();
  const action = record(command).action;
  let target = null;
  switch (action) {
    case "save-template": {
      const existing = command.id ? find(state.templates, command.id) : null;
      if (!existing && state.templates.length >= 100) fail(400, "Template limit reached");
      const template = { id: existing?.id || crypto.randomUUID(), name: text(command.name), config: validateConfig(command.config), updatedAt: now };
      for (const serviceId of template.config.serviceIds || []) find(state.services, serviceId);
      state.templates = [...state.templates.filter((t) => t.id !== template.id), template];
      target = template.id;
      // Saving is a draft. Existing customers only change after an explicit publish.
      break;
    }
    case "save-package": {
      const existing = command.id ? find(state.packages, command.id) : null;
      if (!existing && state.packages.length >= 100) fail(400, "Package limit reached");
      const templateId = command.templateId ? find(state.templates, command.templateId).id : null;
      if (!Array.isArray(command.serviceIds) || command.serviceIds.length > 30) fail(400, "Invalid service selection");
      const serviceIds = [...new Set(command.serviceIds.map((value) => find(state.services, value).id))];
      const pkg = { id: existing?.id || crypto.randomUUID(), name: text(command.name), templateId, serviceIds, permissions: permissions(command.permissions), updatedAt: now };
      state.packages = [...state.packages.filter((p) => p.id !== pkg.id), pkg];
      target = pkg.id;
      break;
    }
    case "save-service": {
      const existing = command.id ? find(state.services, command.id) : null;
      if (!existing && state.services.length >= 100) fail(400, "Service limit reached");
      if (!["jellyfin", "emby", "plex", "addon", "iptv"].includes(command.kind)) fail(400, "Unsupported connection type");
      const serviceId = existing?.id || crypto.randomUUID();
      const service = { id: serviceId, name: text(command.name), kind: command.kind, credentialMode: command.credentialMode === "customer" ? "customer" : "assigned", updatedAt: now };
      if (existing && existing.kind !== command.kind) fail(400, "Create a new service to change its connection type");
      if (service.kind === "iptv") service.iptv = iptvOptions(command.iptv ?? existing?.iptv);
      if (command.url) service.connection = seal({ url: url(command.url, { local: command.kind !== "addon" }) }, organizationId, `service:${serviceId}`, master);
      else if (existing) service.connection = existing.connection;
      else fail(400, "Service URL is required");
      state.services = [...state.services.filter((s) => s.id !== serviceId), service]; target = serviceId;
      break;
    }
    case "invite-customer": {
      requireCapacity(state, entitlement);
      const address = email(command.email);
      if (state.customers.some((c) => c.email === address)) fail(409, "This customer already exists");
      const packageId = command.packageId ? find(state.packages, command.packageId).id : null;
      const customer = { id: crypto.randomUUID(), name: text(command.name), email: address, status: "invited", packageId, credentials: {}, createdAt: now };
      publish(state, customer, now); state.customers.push(customer); target = customer.id;
      break;
    }
    case "set-customer-status": {
      const customer = find(state.customers, command.id);
      if (!["active", "suspended", "removed"].includes(command.status)) fail(400, "Invalid customer status");
      if (command.status === "active") {
        if (!customer.acceptedAt) fail(409, "The customer must accept their invitation first");
        if (customer.status !== "active") requireCapacity(state, entitlement);
      }
      if (customer.status === "removed") fail(409, "Removed customers must be invited again");
      customer.status = command.status;
      if (command.status === "removed") {
        customer.credentials = {}; customer.history = []; customer.delivery = null; customer.email = "";
        for (const device of state.devices.filter((d) => d.customerId === customer.id)) device.revokedAt = now;
      }
      target = customer.id; break;
    }
    case "assign-package": {
      const customer = find(state.customers, command.id);
      customer.packageId = command.packageId ? find(state.packages, command.packageId).id : null;
      customer.copiedConfig = null;
      publish(state, customer, now); target = customer.id; break;
    }
    case "copy-configuration": {
      const source = find(state.customers, command.sourceId);
      if (!Array.isArray(command.customerIds) || !command.customerIds.length || command.customerIds.length > 200) fail(400, "Choose customers to receive the configuration");
      for (const customerId of new Set(command.customerIds)) {
        const customer = find(state.customers, customerId);
        if (customer.status === "removed") fail(400, "Cannot configure a removed customer");
        customer.packageId = source.packageId;
        customer.copiedConfig = command.linked === true ? null : structuredClone(source.delivery?.config || configFor(state, source).config);
        publish(state, customer, now);
      }
      target = source.id; break;
    }
    case "publish-package": {
      const pkg = find(state.packages, command.id);
      for (const customer of state.customers.filter((c) => c.packageId === pkg.id && c.status !== "removed")) publish(state, customer, now);
      target = pkg.id; break;
    }
    case "set-credentials": {
      const customer = find(state.customers, command.id);
      if (customer.status === "removed") fail(400, "Cannot configure a removed customer");
      const service = find(state.services, command.serviceId);
      const credentials = service.kind === "iptv" ? iptvCredentials(command.credentials, service) : {};
      for (const [key, value] of Object.entries(service.kind === "iptv" ? {} : record(command.credentials))) {
        if (!["username", "password", "token", "userId", "manifestUrl", "playlistUrl"].includes(key)) fail(400, "Unsupported credential field");
        credentials[key] = key === "password" ? credentialText(value) : text(value, 4096, false);
      }
      if (credentials.manifestUrl) credentials.manifestUrl = url(credentials.manifestUrl);
      if (credentials.playlistUrl) credentials.playlistUrl = url(credentials.playlistUrl, { local: true });
      customer.credentials[service.id] = seal(credentials, organizationId, `customer:${customer.id}:${service.id}`, master);
      republishCurrent(customer, now); target = customer.id; break;
    }
    case "rollback-customer": {
      const customer = find(state.customers, command.id);
      const revision = (customer.history || []).find((h) => h.revision === command.deliveryRevision);
      if (!revision) fail(404, "Configuration revision not found");
      const nextRevision = (customer.delivery?.revision || 0) + 1;
      customer.history = [...customer.history, customer.delivery].slice(-12);
      customer.delivery = { ...structuredClone(revision), revision: nextRevision, publishedAt: now };
      target = customer.id; break;
    }
    case "revoke-device": {
      const device = find(state.devices, command.id); device.revokedAt = now; target = device.id; break;
    }
    case "delete-resource": {
      if (!["templates", "packages", "services"].includes(command.kind)) fail(400, "Invalid resource");
      find(state[command.kind], command.id);
      const used = command.kind === "templates" ? state.packages.some((p) => p.templateId === command.id)
        : command.kind === "packages" ? state.customers.some((c) => c.packageId === command.id && c.status !== "removed")
          : state.templates.some(t => t.config.serviceIds?.includes(command.id)) || state.packages.some((p) => p.serviceIds.includes(command.id)) || state.customers.some((c) => c.delivery?.serviceIds.includes(command.id));
      if (used) fail(409, "This item is still assigned. Remove its assignments first.");
      state[command.kind] = state[command.kind].filter((item) => item.id !== command.id); target = command.id; break;
    }
    default: fail(400, "Unknown partner action");
  }
  audit(state, actor, action, target, now);
  return { state, target };
}

function publicState(row, entitlement) {
  return {
    id: row.id, revision: row.revision, name: row.state.name, billing: entitlement,
    seatsUsed: activeSeats(row.state), plans: PLANS,
    templates: row.state.templates, packages: row.state.packages,
    services: row.state.services.map(({ connection, ...service }) => ({ ...service, configured: !!connection })),
    customers: row.state.customers.filter((c) => c.status !== "removed").map(({ credentials, history, copiedConfig, delivery, ...customer }) => ({
      ...customer, credentialServiceIds: Object.keys(credentials || {}), configurationMode: copiedConfig ? "copy" : "linked",
      connectionServiceIds: delivery?.serviceIds || [],
      deliveryRevision: delivery?.revision || 0, publishedAt: delivery?.publishedAt || null,
      history: (history || []).map((h) => ({ revision: h.revision, publishedAt: h.publishedAt }))
    })),
    devices: row.state.devices.map(({ keyHash, ...device }) => device),
    audit: row.state.audit.slice(-100).reverse()
  };
}

module.exports = {
  PLANS, DEFAULT_PERMISSIONS, BOOLEAN_SETTINGS, ENUM_SETTINGS, fail, record, text, credentialText, id, email, url, hash, equal,
  seal, unseal, validateConfig, iptvOptions, iptvCredentials, packState, unpackState, permissions, nextMonth, parsePayment, billing, newState,
  activeSeats, requireCapacity, find, configFor, publish, republishCurrent, audit, mutate, publicState
};

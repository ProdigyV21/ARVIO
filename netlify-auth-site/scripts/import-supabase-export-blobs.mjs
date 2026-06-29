import fs from "node:fs";
import path from "node:path";
import readline from "node:readline";
import crypto from "node:crypto";
import { getStore } from "@netlify/blobs";

const args = process.argv.slice(2);
const exportDir = args.find((arg) => !arg.startsWith("--")) || process.env.SUPABASE_EXPORT_DIR;
if (!exportDir) {
  console.error("Usage: node scripts/import-supabase-export-blobs.mjs <export-dir> [--email user@example.com] [--limit 100] [--concurrency 4] [--force]");
  process.exit(1);
}

function argValue(name, fallback = "") {
  const index = args.indexOf(name);
  return index >= 0 ? String(args[index + 1] || fallback) : fallback;
}

function hasArg(name) {
  return args.includes(name);
}

function readNetlifyToken() {
  if (process.env.NETLIFY_AUTH_TOKEN) return process.env.NETLIFY_AUTH_TOKEN;
  const configPath = path.join(process.env.APPDATA || "", "netlify", "Config", "config.json");
  const config = JSON.parse(fs.readFileSync(configPath, "utf8"));
  return config.users[config.userId].auth.token;
}

const siteID = process.env.NETLIFY_SITE_ID || "17ccb668-bf3c-412d-9573-a28a09e52122";
const token = readNetlifyToken();
const legacyStore = getStore({
  name: "legacy-supabase-sync",
  siteID,
  token
});

const usersById = new Map();
const bestByUser = new Map();
const checkpointPath = path.resolve(argValue(
  "--checkpoint",
  path.join(exportDir, "netlify-blobs-import-checkpoint.json")
));
const targetEmail = normalizeEmail(argValue("--email"));
const importLimit = Number(argValue("--limit", "0") || 0);
const concurrency = Math.max(1, Number(argValue("--concurrency", "4") || 4));
const progressInterval = Math.max(1, Number(argValue("--progress", "250") || 250));
const forceImport = hasArg("--force");
const targetUserIds = new Set();
let writtenCount = 0;
let scheduledCount = 0;
let skippedByCheckpoint = 0;
const startedAt = Date.now();
const pendingWrites = new Set();

function normalizeEmail(email) {
  return String(email || "").trim().toLowerCase();
}

function sha256(value) {
  return crypto.createHash("sha256").update(String(value || "")).digest("hex");
}

async function* readNdjson(fileName) {
  const filePath = path.join(exportDir, fileName);
  if (!fs.existsSync(filePath)) return;
  const input = fs.createReadStream(filePath, { encoding: "utf8" });
  const lines = readline.createInterface({ input, crlfDelay: Infinity });
  for await (const line of lines) {
    const trimmed = line.trim();
    if (trimmed) yield JSON.parse(trimmed);
  }
}

function elapsed() {
  const seconds = Math.max(1, Math.round((Date.now() - startedAt) / 1000));
  const minutes = Math.floor(seconds / 60);
  const rest = seconds % 60;
  return minutes > 0 ? `${minutes}m${String(rest).padStart(2, "0")}s` : `${seconds}s`;
}

function metricsSummary(metrics, source, writtenAt = new Date().toISOString()) {
  return {
    restoreRank: metrics.restoreRank,
    profileCount: metrics.profileCount,
    scopedCoverage: metrics.scopedCoverage,
    payloadUpdatedAt: metrics.payloadUpdatedAt,
    payloadVersion: metrics.payloadVersion,
    source,
    writtenAt
  };
}

function loadCheckpoint() {
  if (forceImport || !fs.existsSync(checkpointPath)) return;
  const checkpoint = JSON.parse(fs.readFileSync(checkpointPath, "utf8"));
  const imported = checkpoint.imported || {};
  Object.entries(imported).forEach(([userId, summary]) => {
    bestByUser.set(userId, summary);
  });
  console.log(`checkpoint loaded: ${bestByUser.size} users from ${checkpointPath}`);
}

function saveCheckpoint(extra = {}) {
  const imported = {};
  for (const [userId, summary] of bestByUser.entries()) {
    imported[userId] = summary;
  }
  const checkpoint = {
    exportDir: path.resolve(exportDir),
    siteID,
    updatedAt: new Date().toISOString(),
    imported,
    stats: {
      writtenCount,
      scheduledCount,
      skippedByCheckpoint,
      ...extra
    }
  };
  fs.writeFileSync(checkpointPath, `${JSON.stringify(checkpoint, null, 2)}\n`, "utf8");
}

function payloadMetrics(payload) {
  const root = typeof payload === "string" ? JSON.parse(payload) : payload;
  const profiles = Array.isArray(root.profiles) ? root.profiles : null;
  const profileCount = profiles ? profiles.length : null;
  const profileIds = new Set(
    (profiles || [])
      .map((profile) => profile && profile.id)
      .filter((id) => typeof id === "string" && id.length > 0)
  );
  const scopedKeys = [
    "profileSettingsById",
    "addonsByProfile",
    "catalogsByProfile",
    "hiddenPreinstalledByProfile",
    "hiddenAddonByProfile",
    "hiddenHomeServerByProfile",
    "iptvByProfile",
    "watchlistByProfile"
  ];
  const scopedCoverage = scopedKeys.reduce((total, key) => {
    const obj = root[key];
    if (!obj || typeof obj !== "object" || Array.isArray(obj)) return total;
    let count = 0;
    profileIds.forEach((profileId) => {
      if (Object.prototype.hasOwnProperty.call(obj, profileId)) count += 1;
    });
    return total + count;
  }, 0);

  const hasFullShape = scopedKeys.some((key) => Object.prototype.hasOwnProperty.call(root, key));
  const hasConfiguredState =
    (Array.isArray(root.addons) && root.addons.length > 0) ||
    Boolean(String(root.iptvM3uUrl || "").trim()) ||
    Object.values(root.addonsByProfile || {}).some((value) => Array.isArray(value) && value.length > 0) ||
    Object.values(root.watchlistByProfile || {}).some((value) => Array.isArray(value) && value.length > 0) ||
    Object.values(root.iptvByProfile || {}).some((value) => {
      if (!value || typeof value !== "object") return false;
      return Boolean(String(value.m3uUrl || "").trim()) ||
        Boolean(String(value.epgUrl || "").trim()) ||
        (Array.isArray(value.playlists) && value.playlists.length > 0) ||
        (Array.isArray(value.favoriteChannels) && value.favoriteChannels.length > 0) ||
        (Array.isArray(value.favoriteGroups) && value.favoriteGroups.length > 0);
    });

  let usefulProfiles = false;
  if (profileCount > 1) usefulProfiles = true;
  else if (profileCount === 1) {
    const profile = profiles[0] || {};
    usefulProfiles = !(
      String(profile.name || "").toLowerCase() === "profile 1" &&
      Number(profile.avatarId || 0) === 0 &&
      Number(profile.avatarImageVersion || 0) <= 0 &&
      !profile.isKidsProfile &&
      !profile.isLocked &&
      !String(profile.pin || "").trim()
    );
  }

  let restoreRank;
  if (profileCount !== null && profileCount <= 0) restoreRank = 0;
  else if (profileCount !== null && profileCount > 1 && hasFullShape) restoreRank = 80;
  else if (profileCount !== null && profileCount > 1) restoreRank = 70;
  else if ((usefulProfiles || hasConfiguredState) && hasFullShape) restoreRank = 50;
  else if (usefulProfiles || hasConfiguredState) restoreRank = 40;
  else if (profileCount === null && hasFullShape) restoreRank = 30;
  else if (profileCount === null) restoreRank = 20;
  else restoreRank = 10;

  return {
    payload: root,
    payloadVersion: Number(root.version || 1),
    restoreRank,
    profileCount,
    scopedCoverage,
    payloadUpdatedAt: Number(root.updatedAt || 0) > 0
      ? new Date(Number(root.updatedAt)).toISOString()
      : null
  };
}

function isBetter(existing, incoming) {
  if (!existing) return true;
  if (incoming.restoreRank !== existing.restoreRank) return incoming.restoreRank > existing.restoreRank;
  const existingProfiles = existing.profileCount ?? -1;
  const incomingProfiles = incoming.profileCount ?? -1;
  if (incomingProfiles !== existingProfiles) return incomingProfiles > existingProfiles;
  if (incoming.scopedCoverage !== existing.scopedCoverage) return incoming.scopedCoverage > existing.scopedCoverage;
  return String(incoming.payloadUpdatedAt || "") >= String(existing.payloadUpdatedAt || "");
}

function limitReached() {
  return importLimit > 0 && scheduledCount >= importLimit;
}

async function withRetry(label, action) {
  let lastError;
  for (let attempt = 1; attempt <= 5; attempt += 1) {
    try {
      return await action();
    } catch (error) {
      lastError = error;
      const waitMs = Math.min(30_000, 1_000 * 2 ** (attempt - 1));
      console.warn(`${label} failed attempt=${attempt}: ${error.message}`);
      if (attempt < 5) await new Promise((resolve) => setTimeout(resolve, waitMs));
    }
  }
  throw lastError;
}

async function writeSnapshot(userId, metrics, source) {
  const user = usersById.get(userId);
  const email = normalizeEmail(user?.email || "");
  const snapshot = {
    payload: metrics.payload,
    payloadVersion: metrics.payloadVersion,
    restoreRank: metrics.restoreRank,
    profileCount: metrics.profileCount,
    scopedCoverage: metrics.scopedCoverage,
    payloadUpdatedAt: metrics.payloadUpdatedAt,
    source,
    updatedAt: new Date().toISOString()
  };
  const metadata = {
    supabaseUserId: userId,
    restoreRank: String(metrics.restoreRank),
    profileCount: String(metrics.profileCount ?? ""),
    source
  };
  await legacyStore.setJSON(`supabase/${userId}.json`, snapshot, { metadata });
  if (email) {
    await legacyStore.setJSON(`email/${sha256(email)}.json`, snapshot, { metadata });
  }
}

async function enqueueWrite(userId, metrics, source) {
  scheduledCount += 1;
  const task = (async () => {
    await withRetry(`write snapshot user=${userId} source=${source}`, async () => {
      await writeSnapshot(userId, metrics, source);
    });
    writtenCount += 1;
    bestByUser.set(userId, metricsSummary(metrics, source));
    if (writtenCount % 25 === 0) saveCheckpoint();
    if (writtenCount % 100 === 0) {
      console.log(`written=${writtenCount} scheduled=${scheduledCount} users=${bestByUser.size} elapsed=${elapsed()}`);
    }
  })();

  pendingWrites.add(task);
  task.finally(() => pendingWrites.delete(task));

  if (pendingWrites.size >= concurrency) {
    await Promise.race(pendingWrites);
  }
}

async function flushWrites() {
  if (pendingWrites.size === 0) return;
  await Promise.all(pendingWrites);
  saveCheckpoint();
}

async function considerSnapshot(userId, payload, source) {
  if (!userId || !payload) return false;
  if (targetUserIds.size > 0 && !targetUserIds.has(userId)) return false;
  if (importLimit > 0 && scheduledCount >= importLimit) return false;
  let metrics;
  try {
    metrics = payloadMetrics(payload);
  } catch (error) {
    console.warn(`skip invalid payload user=${userId} source=${source}: ${error.message}`);
    return false;
  }
  if (!isBetter(bestByUser.get(userId), metrics)) {
    skippedByCheckpoint += 1;
    return false;
  }
  await enqueueWrite(userId, metrics, source);
  return true;
}

async function loadUsers() {
  let count = 0;
  for await (const row of readNdjson("auth.users.ndjson")) {
    if (!row.id) continue;
    usersById.set(row.id, {
      id: row.id,
      email: row.email || "",
      createdAt: row.created_at || null,
      lastSignInAt: row.last_sign_in_at || null
    });
    if (targetEmail && normalizeEmail(row.email) === targetEmail) {
      targetUserIds.add(row.id);
    }
    count += 1;
  }
  console.log(`users loaded: ${count}`);
  if (targetEmail) {
    console.log(`target email ${targetEmail} matched user ids: ${Array.from(targetUserIds).join(", ") || "(none)"}`);
  }
}

async function importSnapshots() {
  const stats = {
    account_sync_state: 0,
    user_settings: 0,
    profile_addons: 0,
    written: 0
  };

  for await (const row of readNdjson("public.account_sync_state.ndjson")) {
    stats.account_sync_state += 1;
    if (await considerSnapshot(row.user_id, row.payload, "account_sync_state")) stats.written += 1;
    if (limitReached()) {
      console.log(`limit reached during account_sync_state after scanned=${stats.account_sync_state}`);
      break;
    }
    if (stats.account_sync_state % progressInterval === 0) {
      console.log(`account_sync_state scanned=${stats.account_sync_state} written=${stats.written} skipped=${skippedByCheckpoint} pending=${pendingWrites.size} elapsed=${elapsed()}`);
    }
  }
  await flushWrites();
  if (limitReached()) {
    saveCheckpoint({ finalStats: stats });
    console.log(`snapshot import stopped by limit: ${JSON.stringify(stats)} checkpointUsers=${bestByUser.size} elapsed=${elapsed()}`);
    return;
  }

  for await (const row of readNdjson("public.user_settings.ndjson")) {
    stats.user_settings += 1;
    const payload = row.settings?.accountSyncPayload;
    if (await considerSnapshot(row.user_id, payload, "user_settings")) stats.written += 1;
    if (limitReached()) {
      console.log(`limit reached during user_settings after scanned=${stats.user_settings}`);
      break;
    }
    if (stats.user_settings % progressInterval === 0) {
      console.log(`user_settings scanned=${stats.user_settings} written=${stats.written} skipped=${skippedByCheckpoint} pending=${pendingWrites.size} elapsed=${elapsed()}`);
    }
  }
  await flushWrites();
  if (limitReached()) {
    saveCheckpoint({ finalStats: stats });
    console.log(`snapshot import stopped by limit: ${JSON.stringify(stats)} checkpointUsers=${bestByUser.size} elapsed=${elapsed()}`);
    return;
  }

  for await (const row of readNdjson("public.profiles.ndjson")) {
    stats.profile_addons += 1;
    if (!row.addons) continue;
    let addons;
    try {
      addons = JSON.parse(row.addons);
    } catch {
      continue;
    }
    if (await considerSnapshot(row.id, addons.__arvioAccountSyncPayload, "profile_addons")) stats.written += 1;
    if (limitReached()) {
      console.log(`limit reached during profile_addons after scanned=${stats.profile_addons}`);
      break;
    }
    if (stats.profile_addons % progressInterval === 0) {
      console.log(`profile_addons scanned=${stats.profile_addons} written=${stats.written} skipped=${skippedByCheckpoint} pending=${pendingWrites.size} elapsed=${elapsed()}`);
    }
  }
  await flushWrites();

  saveCheckpoint({ finalStats: stats });
  console.log(`snapshot import complete: ${JSON.stringify(stats)} checkpointUsers=${bestByUser.size} elapsed=${elapsed()}`);
}

console.log(`Importing snapshots into Netlify Blobs for site ${siteID}`);
if (targetEmail) console.log(`Filter: email=${targetEmail}`);
if (importLimit > 0) console.log(`Limit: ${importLimit} writes`);
console.log(`Concurrency: ${concurrency}`);
console.log(`Checkpoint: ${checkpointPath}${forceImport ? " (force import, ignoring existing checkpoint)" : ""}`);
loadCheckpoint();
await loadUsers();
await importSnapshots();

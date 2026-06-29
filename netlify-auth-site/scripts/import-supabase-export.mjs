import fs from "node:fs";
import path from "node:path";
import readline from "node:readline";
import { Pool } from "pg";

const exportDir = process.argv[2] || process.env.SUPABASE_EXPORT_DIR;
if (!exportDir) {
  console.error("Usage: node scripts/import-supabase-export.mjs <export-dir>");
  process.exit(1);
}

const connectionString =
  process.env.NETLIFY_DB_URL ||
  process.env.NETLIFY_DATABASE_URL ||
  process.env.DATABASE_URL;

if (!connectionString) {
  console.error("NETLIFY_DB_URL, NETLIFY_DATABASE_URL, or DATABASE_URL is required.");
  process.exit(1);
}

const pool = new Pool({
  connectionString,
  max: Number(process.env.DB_POOL_MAX || 4),
  idleTimeoutMillis: 10_000,
  connectionTimeoutMillis: 8_000
});

const usersById = new Map();
const importLegacyRows = process.argv.includes("--include-legacy-rows");

function normalizeEmail(email) {
  return String(email || "").trim().toLowerCase();
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
    profileCount,
    scopedCoverage,
    restoreRank,
    payloadVersion: Number(root.version || 1),
    payloadUpdatedAt: Number(root.updatedAt || 0) > 0
      ? new Date(Number(root.updatedAt)).toISOString()
      : null
  };
}

async function upsertLegacyUser(client, user) {
  const email = normalizeEmail(user.email);
  if (!user.id || !email) return;
  usersById.set(user.id, {
    id: user.id,
    email: user.email,
    emailNormalized: email,
    createdAt: user.created_at || null,
    lastSignInAt: user.last_sign_in_at || null
  });
  await client.query(
    `INSERT INTO public.legacy_supabase_users (
       supabase_user_id, email, email_normalized, created_at, last_sign_in_at
     )
     VALUES ($1::uuid, $2, $3, $4, $5)
     ON CONFLICT (supabase_user_id) DO UPDATE SET
       email = EXCLUDED.email,
       email_normalized = EXCLUDED.email_normalized,
       created_at = EXCLUDED.created_at,
       last_sign_in_at = EXCLUDED.last_sign_in_at,
       imported_at = now()`,
    [user.id, user.email, email, user.created_at || null, user.last_sign_in_at || null]
  );
}

async function upsertSnapshot(client, userId, payload, source, updatedAt = null) {
  if (!userId || !payload) return false;
  let metrics;
  try {
    metrics = payloadMetrics(payload);
  } catch (error) {
    console.warn(`Skipping invalid payload for ${userId} from ${source}: ${error.message}`);
    return false;
  }

  const user = usersById.get(userId);
  const email = normalizeEmail(user?.email || "");
  await client.query(
    `INSERT INTO public.legacy_supabase_snapshots (
       supabase_user_id, email, email_normalized, payload, payload_version,
       restore_rank, profile_count, scoped_coverage, payload_updated_at, source
     )
     VALUES ($1::uuid, $2, $3, $4::jsonb, $5, $6, $7, $8, $9, $10)
     ON CONFLICT (supabase_user_id) DO UPDATE SET
       email = COALESCE(EXCLUDED.email, public.legacy_supabase_snapshots.email),
       email_normalized = COALESCE(EXCLUDED.email_normalized, public.legacy_supabase_snapshots.email_normalized),
       payload = EXCLUDED.payload,
       payload_version = EXCLUDED.payload_version,
       restore_rank = EXCLUDED.restore_rank,
       profile_count = EXCLUDED.profile_count,
       scoped_coverage = EXCLUDED.scoped_coverage,
       payload_updated_at = COALESCE(EXCLUDED.payload_updated_at, public.legacy_supabase_snapshots.payload_updated_at),
       source = EXCLUDED.source,
       imported_at = now()
     WHERE
       EXCLUDED.restore_rank > public.legacy_supabase_snapshots.restore_rank OR
       (
         EXCLUDED.restore_rank = public.legacy_supabase_snapshots.restore_rank AND
         COALESCE(EXCLUDED.profile_count, -1) > COALESCE(public.legacy_supabase_snapshots.profile_count, -1)
       ) OR
       (
         EXCLUDED.restore_rank = public.legacy_supabase_snapshots.restore_rank AND
         COALESCE(EXCLUDED.profile_count, -1) = COALESCE(public.legacy_supabase_snapshots.profile_count, -1) AND
         EXCLUDED.scoped_coverage > public.legacy_supabase_snapshots.scoped_coverage
       ) OR
       (
         EXCLUDED.restore_rank = public.legacy_supabase_snapshots.restore_rank AND
         COALESCE(EXCLUDED.profile_count, -1) = COALESCE(public.legacy_supabase_snapshots.profile_count, -1) AND
         EXCLUDED.scoped_coverage = public.legacy_supabase_snapshots.scoped_coverage AND
         COALESCE(EXCLUDED.payload_updated_at, $11::timestamptz) >= COALESCE(public.legacy_supabase_snapshots.payload_updated_at, $11::timestamptz)
       )`,
    [
      userId,
      user?.email || null,
      email || null,
      JSON.stringify(metrics.payload),
      metrics.payloadVersion,
      metrics.restoreRank,
      metrics.profileCount,
      metrics.scopedCoverage,
      metrics.payloadUpdatedAt || updatedAt,
      source,
      "1970-01-01T00:00:00Z"
    ]
  );
  return true;
}

async function importUsers() {
  const client = await pool.connect();
  let count = 0;
  try {
    for await (const user of readNdjson("auth.users.ndjson")) {
      await upsertLegacyUser(client, user);
      count += 1;
      if (count % 1000 === 0) console.log(`users imported: ${count}`);
    }
  } finally {
    client.release();
  }
  console.log(`users imported: ${count}`);
}

async function importSnapshots() {
  const client = await pool.connect();
  const stats = { account_sync_state: 0, user_settings: 0, profile_addons: 0 };
  try {
    for await (const row of readNdjson("public.account_sync_state.ndjson")) {
      if (await upsertSnapshot(client, row.user_id, row.payload, "account_sync_state", row.updated_at)) {
        stats.account_sync_state += 1;
      }
    }

    for await (const row of readNdjson("public.user_settings.ndjson")) {
      const payload = row.settings?.accountSyncPayload;
      const updatedAt = row.settings?.accountSyncUpdatedAt || row.updated_at;
      if (await upsertSnapshot(client, row.user_id, payload, "user_settings", updatedAt)) {
        stats.user_settings += 1;
      }
    }

    for await (const row of readNdjson("public.profiles.ndjson")) {
      if (!row.addons) continue;
      let profileAddons;
      try {
        profileAddons = JSON.parse(row.addons);
      } catch {
        continue;
      }
      const payload = profileAddons.__arvioAccountSyncPayload;
      const updatedAt = profileAddons.__arvioAccountSyncUpdatedAt || row.updated_at;
      if (await upsertSnapshot(client, row.id, payload, "profile_addons", updatedAt)) {
        stats.profile_addons += 1;
      }
    }
  } finally {
    client.release();
  }
  console.log(`snapshots considered: ${JSON.stringify(stats)}`);
}

async function importGenericLegacyRows() {
  if (!importLegacyRows) {
    console.log("legacy raw rows skipped; pass --include-legacy-rows to import watch/history tables.");
    return;
  }
  const tables = [
    ["public.watch_history.ndjson", "watch_history", "id"],
    ["public.watchlist.ndjson", "watchlist", "id"],
    ["public.sync_state.ndjson", "sync_state", "id"],
    ["public.watched_movies.ndjson", "watched_movies", "id"],
    ["public.watched_episodes.ndjson", "watched_episodes", "id"]
  ];
  const client = await pool.connect();
  try {
    for (const [fileName, tableName, keyField] of tables) {
      let count = 0;
      for await (const row of readNdjson(fileName)) {
        const rowKey = String(row[keyField] || `${row.user_id || ""}:${count}`);
        await client.query(
          `INSERT INTO public.legacy_supabase_rows (table_name, supabase_user_id, row_key, row_data)
           VALUES ($1, $2::uuid, $3, $4::jsonb)
           ON CONFLICT (table_name, row_key) DO UPDATE SET
             supabase_user_id = EXCLUDED.supabase_user_id,
             row_data = EXCLUDED.row_data,
             imported_at = now()`,
          [tableName, row.user_id || null, rowKey, JSON.stringify(row)]
        );
        count += 1;
        if (count % 25_000 === 0) console.log(`${tableName} imported: ${count}`);
      }
      console.log(`${tableName} imported: ${count}`);
    }
  } finally {
    client.release();
  }
}

try {
  console.log(`Importing Supabase export from ${exportDir}`);
  await importUsers();
  await importSnapshots();
  await importGenericLegacyRows();
  console.log("Import complete.");
} finally {
  await pool.end();
}

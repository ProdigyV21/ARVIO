// Server-side field merges for the shared account payload.
//
// Every client (Android/TV, ARVIO Web) uploads the whole account document. The
// clients already merge against the copy they read, but a peer can save in the
// window between that read and the write. These helpers re-run the same merges
// against the stored snapshot, using the per-field timestamps the clients
// already write (CloudSyncRepository.kt on Android, web/lib/cloud.ts on web):
//
//   g:<rootKey>              global settings (Android globalMergeKeys)
//   p:<profileId>:<field>    profileSettingsById fields
//   c:<profileId>:<field>    catalogsByProfile / hidden*ByProfile lists
//   i:<profileId>:<field>    IPTV fields (handled by preserveIptvFields)
//
// The rules mirror Android's mergeSettingsByTimestamp (base = incoming push,
// other = stored snapshot): a stored value replaces the pushed one only when its
// timestamp is strictly newer. A push without a timestamp is accepted unless the
// stored value carries a newer one, which is exactly today's behaviour for
// clients that never stamp anything.

const GLOBAL_MERGE_KEYS = [
  "accentColor", "oledBlackBackground", "skipProfileSelection", "customUserAgent",
  "dnsProvider", "subtitleAiEnabled", "subtitleAiAutoSelect", "subtitleAiFindBestMatch",
  "subtitlePreloadEnabled", "dolbyVisionCompatEnabled", "subtitleAiApiKey",
  "subtitleAiModel", "subtitleRemoveHearingImpaired", "telegramSearchOnClickOnly",
  "subtitleHearingSyncV2"
];
// Android: profileMergeExclude. defaultSubtitle follows subtitleSettingsUpdatedAt.
const PROFILE_MERGE_EXCLUDE = new Set(["defaultSubtitle", "subtitleSettingsUpdatedAt"]);
// Android: canMergeHomePresentationSetting (HomePresentationMigration.kt).
const HOME_PRESENTATION_FIELDS = new Set(["homePresentationVersion", "trailerAutoPlay", "netflixHomeStyle"]);
const CATALOG_FIELDS = ["catalogsByProfile", "hiddenPreinstalledByProfile", "hiddenAddonByProfile", "hiddenHomeServerByProfile"];
const DELETED_PROFILES_KEY = "deletedProfileIds";
const DELETED_PROFILES_LIMIT = 100; // ProfileListMerge.DELETED_LIMIT
const MAX_CONTINUE_WATCHING = 50; // Constants.MAX_CONTINUE_WATCHING

const hasOwn = (value, key) => Object.prototype.hasOwnProperty.call(value, key);

function isPlainObject(value) {
  return Boolean(value) && typeof value === "object" && !Array.isArray(value);
}

function plainObject(value) {
  return isPlainObject(value) ? value : {};
}

function timestamp(value) {
  const result = Number(value);
  return Number.isFinite(result) && result > 0 ? result : 0;
}

function tsOf(map, key) {
  return isPlainObject(map) ? timestamp(map[key]) : 0;
}

// ── Profiles (Android ProfileListMerge, web mergeCloudProfiles) ────────────────

function deletedProfileIds(root) {
  const list = Array.isArray(root?.[DELETED_PROFILES_KEY]) ? root[DELETED_PROFILES_KEY] : [];
  return list.filter((id) => typeof id === "string" && id.trim().length > 0);
}

function profileList(root) {
  return Array.isArray(root?.profiles) ? root.profiles.filter(isPlainObject) : [];
}

function profileIdOf(profile) {
  return typeof profile?.id === "string" ? profile.id : profile?.id == null ? "" : String(profile.id);
}

/** The copy edited last wins (missing updatedAt = oldest, tie = incoming); newest lastUsedAt kept. */
function newerProfile(incoming, stored) {
  const winner = timestamp(stored.updatedAt) > timestamp(incoming.updatedAt) ? stored : incoming;
  const lastUsedAt = Math.max(timestamp(incoming.lastUsedAt), timestamp(stored.lastUsedAt));
  return lastUsedAt > 0 ? { ...winner, lastUsedAt } : winner;
}

function isPlaceholderProfile(profile) {
  const pin = profile.pin == null ? "" : String(profile.pin);
  return String(profile.name || "").toLowerCase() === "profile 1" &&
    Number(profile.avatarId || 0) === 0 &&
    Number(profile.avatarImageVersion || 0) <= 0 &&
    !profile.isKidsProfile &&
    !profile.isLocked &&
    (!pin.trim() || pin === "null");
}

/**
 * Per-profile maps are built from the uploading device's profiles only; copy the
 * entries of profiles kept from the stored list so they are not stripped either.
 */
function keepPerProfileData(output, stored, ids) {
  for (const [key, storedMap] of Object.entries(stored)) {
    if (!isPlainObject(storedMap) || key === "fieldUpdatedAt") continue;
    if (![...ids].some((id) => hasOwn(storedMap, id))) continue;
    let target = output[key];
    if (target === undefined) target = {};
    else if (!isPlainObject(target)) continue;
    else target = { ...target };
    let changed = false;
    for (const id of ids) {
      if (hasOwn(storedMap, id) && !hasOwn(target, id)) {
        target[id] = storedMap[id];
        changed = true;
      }
    }
    if (changed) output[key] = target;
  }
}

/**
 * `cutoffMs` is when the uploading device read the account (else the upload's
 * own time). A stored profile the upload lacks is kept only when it was created
 * or edited after that read, so the uploader could not have known it; an older
 * one is gone because the uploader deleted it (Android 2.2 and older delete
 * profiles without writing deletedProfileIds).
 */
function mergeProfileList(storedPayload, incoming, cutoffMs) {
  const stored = plainObject(storedPayload);
  const deleted = [...new Set([...deletedProfileIds(stored), ...deletedProfileIds(incoming)])];
  const deletedSet = new Set(deleted);
  const storedProfiles = profileList(stored);
  const incomingProfiles = profileList(incoming);
  if (!storedProfiles.length && !deleted.length) return incoming;
  if (!Array.isArray(incoming.profiles) && !storedProfiles.length) return incoming;

  const incomingById = new Map(incomingProfiles.map((profile) => [profileIdOf(profile), profile]));
  const merged = [];
  const seen = new Set();
  const storedOnly = new Set();
  const add = (profile) => {
    const id = profileIdOf(profile);
    if (!id || deletedSet.has(id) || seen.has(id)) return;
    seen.add(id);
    merged.push(profile);
  };
  const cutoff = timestamp(cutoffMs) || timestamp(incoming.updatedAt) || Infinity;
  // An upload holding only a fresh install's placeholder knows nothing yet.
  const uploaderHasRealProfiles = incomingProfiles.some((profile) => !isPlaceholderProfile(profile));
  const knownToUploader = (profile) => Math.max(timestamp(profile.createdAt), timestamp(profile.updatedAt)) <= cutoff;
  for (const profile of storedProfiles) {
    const id = profileIdOf(profile);
    const own = incomingById.get(id);
    if (!own && uploaderHasRealProfiles && knownToUploader(profile)) continue;
    if (!own && id && !deletedSet.has(id)) storedOnly.add(id);
    add(own ? newerProfile(own, profile) : profile);
  }
  // A fresh install's untouched "Profile 1" is not a profile the user made,
  // unless it would otherwise leave the account without any profile.
  const placeholders = [];
  for (const profile of incomingProfiles) {
    if (storedProfiles.length && isPlaceholderProfile(profile)) {
      placeholders.push(profile);
      continue;
    }
    add(profile);
  }
  if (!merged.length) placeholders.forEach(add);

  const output = { ...incoming, profiles: merged };
  if (deleted.length) output[DELETED_PROFILES_KEY] = deleted.slice(-DELETED_PROFILES_LIMIT);
  const active = typeof output.activeProfileId === "string" ? output.activeProfileId : "";
  if (!active || !seen.has(active)) output.activeProfileId = merged[0] ? profileIdOf(merged[0]) : null;
  const missing = new Set([...storedOnly].filter((id) => !incomingById.has(id)));
  if (missing.size) keepPerProfileData(output, stored, missing);
  return output;
}

function activeProfileIds(payload) {
  return Array.isArray(payload?.profiles)
    ? new Set(payload.profiles.map(profileIdOf).filter(Boolean))
    : null;
}

// ── Timestamped settings (g:, p:, c:) and fieldUpdatedAt ──────────────────────

function lookupCatalogDelegationFromMetadata(entry, metadataMap) {
  if (!isPlainObject(metadataMap) || !entry?.addonCatalogId) return null;
  const entryAddonId = String(entry.addonId || "").toLowerCase();
  const entryCatId = String(entry.addonCatalogId || "");
  const entryCatType = String(entry.addonCatalogType || "").toLowerCase();
  for (const [key, meta] of Object.entries(metadataMap)) {
    if (!isPlainObject(meta)) continue;
    const manifestId = String(meta.manifest?.id || "").toLowerCase();
    const matchesAddon = !entryAddonId || key === entryAddonId || (manifestId && entryAddonId.startsWith(manifestId));
    if (!matchesAddon) continue;
    const cats = Array.isArray(meta.catalogs) ? meta.catalogs : (Array.isArray(meta.manifest?.catalogs) ? meta.manifest.catalogs : []);
    const found = cats.find(c =>
      c && String(c.id || "") === entryCatId && (!entryCatType || String(c.type || "").toLowerCase() === entryCatType)
    );
    if (found && (found.clientDelegate != null || found.clientFilter != null)) return found;
  }
  return null;
}

function preserveCatalogDelegationFields(storedList, incomingList, metadataMap) {
  const storedArr = Array.isArray(storedList) ? storedList : [];
  const storedById = new Map();
  for (const item of storedArr) {
    if (isPlainObject(item) && typeof item.id === "string" && item.id) storedById.set(item.id, item);
  }
  let changed = false;
  const result = incomingList.map((item) => {
    if (!isPlainObject(item)) return item;
    const prev = typeof item.id === "string" ? storedById.get(item.id) : null;
    const fromMeta = lookupCatalogDelegationFromMetadata(item, metadataMap);
    const needDelegate = !hasOwn(item, "clientDelegate") || item.clientDelegate == null;
    const needFilter = !hasOwn(item, "clientFilter") || item.clientFilter == null;
    const inheritedDelegate = needDelegate ? (prev?.clientDelegate ?? fromMeta?.clientDelegate) : undefined;
    const inheritedFilter = needFilter ? (prev?.clientFilter ?? fromMeta?.clientFilter) : undefined;
    if (inheritedDelegate == null && inheritedFilter == null) return item;
    changed = true;
    const next = { ...item };
    if (inheritedDelegate != null) next.clientDelegate = inheritedDelegate;
    if (inheritedFilter != null) next.clientFilter = inheritedFilter;
    return next;
  });
  return changed ? result : incomingList;
}

function mergeTimestampedSettings(storedPayload, incoming) {
  const stored = plainObject(storedPayload);
  const storedTs = plainObject(stored.fieldUpdatedAt);
  const incomingTs = plainObject(incoming.fieldUpdatedAt);
  const activeIds = activeProfileIds(incoming);
  const isActive = (id) => !activeIds || activeIds.has(id);
  const keepIncomingTs = new Set();
  const output = { ...incoming };

  if (isPlainObject(stored.addonMetadataByKey) && !hasOwn(output, "addonMetadataByKey")) {
    output.addonMetadataByKey = stored.addonMetadataByKey;
  }

  for (const key of GLOBAL_MERGE_KEYS) {
    if (!hasOwn(stored, key)) {
      // Nothing to restore: the pushed value stays, and so does its timestamp.
      if (tsOf(storedTs, `g:${key}`) > tsOf(incomingTs, `g:${key}`) && hasOwn(incoming, key)) keepIncomingTs.add(`g:${key}`);
      continue;
    }
    if (!hasOwn(incoming, key) || tsOf(storedTs, `g:${key}`) > tsOf(incomingTs, `g:${key}`)) {
      output[key] = stored[key];
    }
  }

  const storedSettings = stored.profileSettingsById;
  const incomingSettings = incoming.profileSettingsById;
  if (isPlainObject(storedSettings) && (incomingSettings === undefined || isPlainObject(incomingSettings))) {
    const next = { ...(incomingSettings || {}) };
    let changed = false;
    for (const [profileId, storedProfile] of Object.entries(storedSettings)) {
      if (!isPlainObject(storedProfile) || !isActive(profileId)) continue;
      const own = next[profileId];
      if (own === undefined) {
        next[profileId] = storedProfile;
        changed = true;
        continue;
      }
      if (!isPlainObject(own)) continue;
      const merged = { ...own };
      const ownVersion = Number(own.homePresentationVersion) || 0;
      const storedVersion = Number(storedProfile.homePresentationVersion) || 0;
      let profileChanged = false;
      for (const field of Object.keys(storedProfile)) {
        const key = `p:${profileId}:${field}`;
        if (!hasOwn(own, field)) {
          // Never drop a field the uploading copy does not know about.
          merged[field] = storedProfile[field];
          profileChanged = true;
          continue;
        }
        if (PROFILE_MERGE_EXCLUDE.has(field)) continue;
        if (HOME_PRESENTATION_FIELDS.has(field) && storedVersion < ownVersion) {
          // A legacy timestamp must not reintroduce pre-2.3 home defaults.
          keepIncomingTs.add(key);
          continue;
        }
        if (tsOf(storedTs, key) > tsOf(incomingTs, key)) {
          merged[field] = storedProfile[field];
          profileChanged = true;
        }
      }
      if (hasOwn(storedProfile, "defaultSubtitle") && hasOwn(own, "defaultSubtitle") &&
          timestamp(storedProfile.subtitleSettingsUpdatedAt) > timestamp(own.subtitleSettingsUpdatedAt)) {
        merged.defaultSubtitle = storedProfile.defaultSubtitle;
        merged.subtitleSettingsUpdatedAt = storedProfile.subtitleSettingsUpdatedAt;
        profileChanged = true;
      }
      if (profileChanged) {
        next[profileId] = merged;
        changed = true;
      }
    }
    if (changed) output.profileSettingsById = next;
  }

  const metadataMap = output.addonMetadataByKey || stored.addonMetadataByKey;
  for (const field of CATALOG_FIELDS) {
    const storedMap = stored[field];
    const incomingMap = incoming[field];
    if (!isPlainObject(storedMap) && (field !== "catalogsByProfile" || !isPlainObject(metadataMap))) continue;
    if (incomingMap !== undefined && !isPlainObject(incomingMap)) continue;
    const next = { ...(incomingMap || {}) };
    let changed = false;
    if (isPlainObject(storedMap)) {
      for (const [profileId, storedList] of Object.entries(storedMap)) {
        if (!Array.isArray(storedList) || !isActive(profileId)) continue;
        const own = next[profileId];
        if (own === undefined) {
          next[profileId] = storedList;
          changed = true;
          continue;
        }
        if (!Array.isArray(own)) continue;
        const key = `c:${profileId}:${field}`;
        if (tsOf(storedTs, key) > tsOf(incomingTs, key)) {
          next[profileId] = storedList;
          changed = true;
        } else if (field === "catalogsByProfile") {
          const preserved = preserveCatalogDelegationFields(storedList, own, metadataMap);
          if (preserved !== own) {
            next[profileId] = preserved;
            changed = true;
          }
        }
      }
    } else if (field === "catalogsByProfile") {
      for (const [profileId, own] of Object.entries(next)) {
        if (!Array.isArray(own) || !isActive(profileId)) continue;
        const preserved = preserveCatalogDelegationFields([], own, metadataMap);
        if (preserved !== own) {
          next[profileId] = preserved;
          changed = true;
        }
      }
    }
    if (changed) output[field] = next;
  }

  if (isPlainObject(stored.fieldUpdatedAt) || isPlainObject(incoming.fieldUpdatedAt)) {
    const mergedTs = { ...storedTs };
    for (const [key, value] of Object.entries(incomingTs)) {
      if (keepIncomingTs.has(key) || timestamp(value) >= tsOf(mergedTs, key)) mergedTs[key] = value;
    }
    for (const key of keepIncomingTs) {
      if (!hasOwn(incomingTs, key)) delete mergedTs[key];
    }
    output.fieldUpdatedAt = mergedTs;
  }
  return output;
}

// ── Continue Watching ─────────────────────────────────────────────────────────

function continueWatchingShowKey(item) {
  const id = Number(item?.id);
  const mediaType = String(item?.mediaType ?? "").trim().toLowerCase();
  if (!Number.isSafeInteger(id) || id === 0 || (mediaType !== "tv" && mediaType !== "movie")) return "";
  return `${mediaType}:${id}`;
}

function rowUpdatedAt(item) {
  return timestamp(item?.updatedAtMs);
}

/** Android mergeLocalContinueWatchingForProfiles freshness: updatedAtMs, then position, then progress. */
function fresher(a, b) {
  return rowUpdatedAt(a) - rowUpdatedAt(b) ||
    (Number(a?.resumePositionSeconds) || 0) - (Number(b?.resumePositionSeconds) || 0) ||
    (Number(a?.progress) || 0) - (Number(b?.progress) || 0);
}

function parsedList(value) {
  if (Array.isArray(value)) return value;
  if (typeof value === "string") {
    try {
      const parsed = JSON.parse(value);
      return Array.isArray(parsed) ? parsed : null;
    } catch {
      return null;
    }
  }
  return null;
}

/** Newest first, one row per show (Android sanitize keeps the first per mediaType:id). */
function normalizeContinueWatchingList(rows) {
  const byShow = new Map();
  for (const row of rows) {
    const key = continueWatchingShowKey(row);
    if (!key) continue;
    const current = byShow.get(key);
    if (!current || fresher(row, current) > 0) byShow.set(key, row);
  }
  return [...byShow.values()].sort((a, b) => rowUpdatedAt(b) - rowUpdatedAt(a)).slice(0, MAX_CONTINUE_WATCHING);
}

/**
 * Newest updatedAtMs per (profile, show) wins. A stored row the upload lacks is
 * kept only when it was written after the uploading device took its snapshot
 * (`cutoffMs`): such a row cannot have been removed deliberately by that device.
 * Older missing rows are treated as removed, which is today's behaviour.
 */
function mergeContinueWatching(storedPayload, incoming, cutoffMs) {
  const stored = plainObject(storedPayload);
  const storedMap = stored.localContinueWatchingByProfile;
  const incomingMap = incoming.localContinueWatchingByProfile;
  if (!isPlainObject(storedMap) || (incomingMap !== undefined && !isPlainObject(incomingMap))) return incoming;
  const activeIds = activeProfileIds(incoming);
  const cutoff = Number.isFinite(cutoffMs) && cutoffMs > 0 ? cutoffMs : Infinity;
  const next = { ...(incomingMap || {}) };
  let changed = false;
  for (const [profileId, storedRaw] of Object.entries(storedMap)) {
    if (activeIds && !activeIds.has(profileId)) continue;
    const storedRows = parsedList(storedRaw);
    if (!storedRows?.length) continue;
    const own = next[profileId];
    const ownRows = own === undefined ? [] : parsedList(own);
    if (!ownRows) continue;
    const ownKeys = new Map();
    for (const row of ownRows) {
      const key = continueWatchingShowKey(row);
      if (key && (!ownKeys.has(key) || fresher(row, ownKeys.get(key)) > 0)) ownKeys.set(key, row);
    }
    let adopted = false;
    const combined = [...ownRows];
    for (const row of storedRows) {
      const key = continueWatchingShowKey(row);
      if (!key) continue;
      const mine = ownKeys.get(key);
      if (mine ? rowUpdatedAt(row) > rowUpdatedAt(mine) : rowUpdatedAt(row) > cutoff) {
        combined.push(row);
        adopted = true;
      }
    }
    if (!adopted) continue;
    next[profileId] = normalizeContinueWatchingList(combined);
    changed = true;
  }
  return changed ? { ...incoming, localContinueWatchingByProfile: next } : incoming;
}

function parseDismissals(value) {
  const result = new Map();
  if (typeof value !== "string") return result;
  for (const entry of value.split("|")) {
    const split = entry.lastIndexOf(",");
    if (split <= 0 || split >= entry.length - 1) continue;
    const time = Number(entry.slice(split + 1));
    if (Number.isSafeInteger(time)) result.set(entry.slice(0, split), time);
  }
  return result;
}

function encodeDismissals(values) {
  return [...values.entries()].map(([key, time]) => `${key},${time}`).join("|");
}

/**
 * Dismissals are timestamped hide markers ("tv:1,1700000000000|movie:2,...").
 * Union them per key with the newest time: a later save of the title outranks
 * an older marker on every client, so a union never hides resumed progress.
 */
function mergeContinueWatchingDismissals(storedPayload, incoming) {
  const stored = plainObject(storedPayload);
  const storedMap = stored.dismissedContinueWatchingByProfile;
  const incomingMap = incoming.dismissedContinueWatchingByProfile;
  if (!isPlainObject(storedMap) || (incomingMap !== undefined && !isPlainObject(incomingMap))) return incoming;
  const activeIds = activeProfileIds(incoming);
  const next = { ...(incomingMap || {}) };
  let changed = false;
  for (const [profileId, storedValue] of Object.entries(storedMap)) {
    if (typeof storedValue !== "string" || (activeIds && !activeIds.has(profileId))) continue;
    const own = next[profileId];
    if (own !== undefined && typeof own !== "string") continue;
    const merged = parseDismissals(own);
    let profileChanged = false;
    for (const [key, time] of parseDismissals(storedValue)) {
      if (time > (merged.get(key) ?? -Infinity)) {
        merged.set(key, time);
        profileChanged = true;
      }
    }
    if (!profileChanged) continue;
    next[profileId] = encodeDismissals(merged);
    changed = true;
  }
  return changed ? { ...incoming, dismissedContinueWatchingByProfile: next } : incoming;
}

/** All field merges for a full-document push; see account-sync-push.js for ordering. */
function mergeAccountFields(storedPayload, incoming, options = {}) {
  const cutoff = timestamp(options.baseUpdatedAtMs) || timestamp(incoming.updatedAt) || Infinity;
  let output = mergeTimestampedSettings(storedPayload, incoming);
  output = mergeContinueWatching(storedPayload, output, cutoff);
  output = mergeContinueWatchingDismissals(storedPayload, output);
  return output;
}

// ── Single progress operation (account-sync-progress) ─────────────────────────

const PROGRESS_ITEM_FIELDS = new Set([
  "id", "title", "mediaType", "progress", "resumePositionSeconds", "durationSeconds", "season", "episode",
  "displaySeason", "displayEpisode", "episodeTitle", "backdropPath", "episodeStillPath", "posterPath",
  "streamKey", "streamAddonId", "streamTitle", "year", "releaseDate", "isUpNext", "updatedAtMs",
  "addonNativeId", "addonNativeAddonId", "addonNativeType"
]);

function badRequest(reason) {
  const error = new Error(reason);
  error.statusCode = 400;
  error.reason = reason;
  return error;
}

function optionalInteger(value, name) {
  if (value === undefined || value === null) return value;
  if (!Number.isSafeInteger(value) || value < 0) throw badRequest(`invalid_${name}`);
  return value;
}

/** Validates one progress operation and strips anything Android's model does not know. */
function normalizeProgressOperation(body, now = Date.now()) {
  const profileId = typeof body?.profileId === "string" ? body.profileId.trim() : "";
  if (!profileId || profileId.length > 128) throw badRequest("invalid_profile");
  const op = body?.op;
  if (op === "upsert") {
    const source = plainObject(body.item);
    const item = {};
    for (const [key, value] of Object.entries(source)) {
      if (PROGRESS_ITEM_FIELDS.has(key) && value !== undefined) item[key] = value;
    }
    if (!continueWatchingShowKey(item)) throw badRequest("invalid_item");
    if (typeof item.title !== "string" || !item.title.trim()) throw badRequest("invalid_title");
    if (!Number.isSafeInteger(item.updatedAtMs) || item.updatedAtMs <= 0) throw badRequest("invalid_updated_at");
    // A device clock far in the future would pin this row above every later save.
    if (item.updatedAtMs > now + 10 * 60_000) item.updatedAtMs = now;
    const progress = Number(item.progress);
    if (!Number.isFinite(progress) || progress < 0 || progress > 100) throw badRequest("invalid_progress");
    item.progress = Math.round(progress);
    for (const field of ["resumePositionSeconds", "durationSeconds"]) {
      const value = Number(item[field] ?? 0);
      if (!Number.isFinite(value) || value < 0) throw badRequest(`invalid_${field}`);
      item[field] = Math.round(value);
    }
    for (const field of ["season", "episode", "displaySeason", "displayEpisode"]) optionalInteger(item[field], field);
    if (item.isUpNext !== undefined && typeof item.isUpNext !== "boolean") throw badRequest("invalid_up_next");
    for (const [key, value] of Object.entries(item)) {
      if (typeof value === "string" && value.length > 2048) throw badRequest(`invalid_${key}`);
    }
    return { profileId, op, item };
  }
  if (op === "remove") {
    const match = plainObject(body.match);
    const target = {
      id: match.id,
      mediaType: String(match.mediaType ?? "").toLowerCase(),
      season: optionalInteger(match.season, "season"),
      episode: optionalInteger(match.episode, "episode")
    };
    if (!continueWatchingShowKey(target)) throw badRequest("invalid_item");
    const updatedAtMs = body.updatedAtMs;
    if (!Number.isSafeInteger(updatedAtMs) || updatedAtMs <= 0) throw badRequest("invalid_updated_at");
    return { profileId, op, match: target, updatedAtMs: Math.min(updatedAtMs, now + 10 * 60_000) };
  }
  throw badRequest("invalid_op");
}

function sameEpisode(row, season, episode) {
  return (row.season ?? null) === (season ?? null) && (row.episode ?? null) === (episode ?? null);
}

/**
 * Applies one normalized operation to a payload. Returns { payload, applied, reason }.
 * Never touches anything but this profile's Continue Watching row(s) and dismissals.
 */
function applyProgressOperation(payload, operation) {
  const root = plainObject(payload);
  const { profileId } = operation;
  if (deletedProfileIds(root).includes(profileId)) return { payload: root, applied: false, reason: "profile_deleted" };
  const byProfile = root.localContinueWatchingByProfile;
  if (byProfile !== undefined && !isPlainObject(byProfile)) return { payload: root, applied: false, reason: "unsupported_shape" };
  const current = byProfile?.[profileId] === undefined ? [] : parsedList(byProfile[profileId]);
  if (!current) return { payload: root, applied: false, reason: "unsupported_shape" };

  if (operation.op === "remove") {
    const key = continueWatchingShowKey(operation.match);
    const exact = operation.match.mediaType === "tv" && operation.match.season != null && operation.match.episode != null;
    const remaining = current.filter((row) => {
      if (continueWatchingShowKey(row) !== key) return true;
      if (rowUpdatedAt(row) > operation.updatedAtMs) return true;
      return exact && !sameEpisode(row, operation.match.season, operation.match.episode);
    });
    if (remaining.length === current.length) return { payload: root, applied: false, reason: "not_found" };
    return {
      payload: { ...root, localContinueWatchingByProfile: { ...(byProfile || {}), [profileId]: remaining } },
      applied: true
    };
  }

  const { item } = operation;
  const key = continueWatchingShowKey(item);
  const sameShow = current.filter((row) => continueWatchingShowKey(row) === key);
  if (sameShow.some((row) => rowUpdatedAt(row) > item.updatedAtMs)) {
    return { payload: root, applied: false, reason: "stale" };
  }
  // Keep what this client does not know (stream_key, display numbering, ...) for the same episode.
  const previous = sameShow.find((row) => sameEpisode(row, item.season, item.episode));
  const merged = { ...(previous || {}) };
  for (const [field, value] of Object.entries(item)) {
    if (value !== null || !previous || previous[field] == null) merged[field] = value;
  }
  const rows = [merged, ...current.filter((row) => continueWatchingShowKey(row) !== key)]
    .sort((a, b) => rowUpdatedAt(b) - rowUpdatedAt(a))
    .slice(0, MAX_CONTINUE_WATCHING);
  const output = { ...root, localContinueWatchingByProfile: { ...(byProfile || {}), [profileId]: rows } };

  // Android clears the show and exact dismissal on save (clearDismissedContinueWatching).
  const dismissals = root.dismissedContinueWatchingByProfile;
  if (isPlainObject(dismissals) && typeof dismissals[profileId] === "string") {
    const values = parseDismissals(dismissals[profileId]);
    const showKey = key;
    const exactKey = item.mediaType.toLowerCase() === "tv" && item.season != null && item.episode != null
      ? `${showKey}:${item.season}:${item.episode}` : showKey;
    let cleared = false;
    for (const dismissalKey of new Set([showKey, exactKey])) {
      if (values.has(dismissalKey) && values.get(dismissalKey) <= item.updatedAtMs) {
        values.delete(dismissalKey);
        cleared = true;
      }
    }
    if (cleared) {
      const nextDismissals = { ...dismissals };
      const encoded = encodeDismissals(values);
      if (encoded) nextDismissals[profileId] = encoded;
      else delete nextDismissals[profileId];
      output.dismissedContinueWatchingByProfile = nextDismissals;
    }
  }
  return { payload: output, applied: true };
}

module.exports = {
  GLOBAL_MERGE_KEYS,
  mergeProfileList,
  mergeTimestampedSettings,
  mergeContinueWatching,
  mergeContinueWatchingDismissals,
  mergeAccountFields,
  normalizeContinueWatchingList,
  normalizeProgressOperation,
  applyProgressOperation,
  continueWatchingShowKey
};

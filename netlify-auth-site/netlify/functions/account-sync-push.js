const {
  json,
  options,
  parseBody,
  payloadMetrics,
  isExistingSnapshotRicher,
  applyAddonWipeGuard,
  resolveIdentity,
  loadSnapshotFromBlobs,
  saveSnapshotToBlobs,
  appendSnapshotEvent
} = require("./_backend");

const TRACKING_V2_FIELDS = [
  "provider",
  "watchlistReadMode",
  "continueWatchingReadMode",
  "watchedReadMode",
  "writeToTrakt",
  "writeToSimkl"
];

function timestampOf(value, field) {
  const timestamp = Number(value?.[field] || 0);
  return Number.isFinite(timestamp) && timestamp > 0 ? timestamp : 0;
}

function copyOptionalField(target, source, field) {
  if (Object.prototype.hasOwnProperty.call(source, field)) target[field] = source[field];
  else delete target[field];
}

function preserveNewerDomain(next, previous, incoming, timestampField, fields) {
  const previousUpdatedAt = timestampOf(previous, timestampField);
  const incomingUpdatedAt = timestampOf(incoming, timestampField);
  if (previousUpdatedAt > incomingUpdatedAt ||
      (previousUpdatedAt > 0 && previousUpdatedAt === incomingUpdatedAt)) {
    for (const field of [...fields, timestampField]) copyOptionalField(next, previous, field);
    return;
  }
  if (previousUpdatedAt === 0 && incomingUpdatedAt === 0) {
    for (const field of fields) {
      if (!Object.prototype.hasOwnProperty.call(incoming, field) &&
          Object.prototype.hasOwnProperty.call(previous, field)) {
        next[field] = previous[field];
      }
    }
  }
}

function preserveTrackingRouting(existingSnapshot, incomingPayload) {
  const previous = existingSnapshot?.payload?.mdbListSyncByProfile;
  const incoming = incomingPayload?.mdbListSyncByProfile;
  if (!previous || typeof previous !== "object" || Array.isArray(previous)) {
    return incomingPayload;
  }
  if (!incoming || typeof incoming !== "object" || Array.isArray(incoming)) {
    return { ...incomingPayload, mdbListSyncByProfile: { ...previous } };
  }
  const merged = { ...incoming };
  for (const [profileId, previousSelection] of Object.entries(previous)) {
    if (!previousSelection || typeof previousSelection !== "object" || Array.isArray(previousSelection)) continue;
    const incomingSelection = merged[profileId];
    if (!incomingSelection || typeof incomingSelection !== "object" || Array.isArray(incomingSelection)) {
      merged[profileId] = { ...previousSelection };
      continue;
    }
    const next = { ...incomingSelection };
    preserveNewerDomain(next, previousSelection, incomingSelection, "updatedAt", TRACKING_V2_FIELDS);
    preserveNewerDomain(
      next,
      previousSelection,
      incomingSelection,
      "simklCredentialUpdatedAt",
      ["simklAccessToken"]
    );
    preserveNewerDomain(
      next,
      previousSelection,
      incomingSelection,
      "mdbListCredentialUpdatedAt",
      ["mdbListApiKey"]
    );
    merged[profileId] = next;
  }
  return { ...incomingPayload, mdbListSyncByProfile: merged };
}

function preserveTraktTokens(existingSnapshot, incomingPayload) {
  const previous = existingSnapshot?.payload?.traktTokens;
  const incoming = incomingPayload?.traktTokens;
  if (!previous || typeof previous !== "object" || Array.isArray(previous)) {
    return incomingPayload;
  }
  if (!incoming || typeof incoming !== "object" || Array.isArray(incoming)) {
    return { ...incomingPayload, traktTokens: { ...previous } };
  }
  const merged = { ...incoming };
  for (const [profileId, previousToken] of Object.entries(previous)) {
    if (!previousToken || typeof previousToken !== "object" || Array.isArray(previousToken)) continue;
    const incomingToken = merged[profileId];
    if (!incomingToken || typeof incomingToken !== "object" || Array.isArray(incomingToken)) {
      merged[profileId] = { ...previousToken };
      continue;
    }
    const previousUpdatedAt = timestampOf(previousToken, "updatedAt");
    const incomingUpdatedAt = timestampOf(incomingToken, "updatedAt");
    const incomingHasToken = Boolean(incomingToken.accessToken || incomingToken.access_token);
    const previousHasToken = Boolean(previousToken.accessToken || previousToken.access_token);
    if (previousUpdatedAt > incomingUpdatedAt ||
        (previousUpdatedAt > 0 && previousUpdatedAt === incomingUpdatedAt) ||
        (previousUpdatedAt === 0 && incomingUpdatedAt === 0 && !incomingHasToken && previousHasToken)) {
      merged[profileId] = { ...previousToken };
    }
  }
  return { ...incomingPayload, traktTokens: merged };
}

function preserveIptvFields(existingSnapshot, incomingPayload) {
  const previous = existingSnapshot?.payload;
  if (!previous?.iptvByProfile || !previous?.fieldUpdatedAt) return incomingPayload;
  const fields = new Set(["m3uUrl", "epgUrl", "playlists", "stalkerPortals", "stalkerPortalUrl",
    "stalkerMacAddress", "favoriteGroups", "favoriteChannels", "hiddenGroups", "lockedGroups",
    "groupOrder", "groupOrderSchema", "sortOrder"]);
  const profiles = { ...incomingPayload.iptvByProfile };
  const timestamps = { ...incomingPayload.fieldUpdatedAt };
  const activeIds = Array.isArray(incomingPayload.profiles)
    ? new Set(incomingPayload.profiles.map((profile) => profile?.id)) : null;
  for (const [id, previousState] of Object.entries(previous.iptvByProfile)) {
    if (activeIds && !activeIds.has(id)) continue;
    if (!previousState || typeof previousState !== "object" || Array.isArray(previousState)) continue;
    const next = { ...profiles[id] };
    let changed = false;
    for (const field of fields) {
      const key = `i:${id}:${field}`;
      const oldTime = timestampOf(previous.fieldUpdatedAt, key);
      const newTime = timestampOf(timestamps, key);
      if (oldTime > 0 && oldTime >= newTime) {
        copyOptionalField(next, previousState, field);
        timestamps[key] = oldTime;
        changed = true;
      }
    }
    if (changed) profiles[id] = next;
  }
  return { ...incomingPayload, iptvByProfile: profiles, fieldUpdatedAt: timestamps };
}

exports._test = { preserveTrackingRouting, preserveTraktTokens, preserveIptvFields };

exports.handler = async (event) => {
  const cors = options(event);
  if (cors) return cors;
  if (event.httpMethod !== "POST") {
    return json(405, { error: "method_not_allowed" });
  }

  try {
    const identity = await resolveIdentity(event);
    const body = parseBody(event);
    const rawPayload = body.payload;
    if (!rawPayload) {
      return json(400, { accepted: false, reason: "missing_payload" });
    }

    const existing = await loadSnapshotFromBlobs(event, identity);
    // Server-side addon wipe guard: refuse pushes that catastrophically shrink
    // the addon list (recurring client bug); existing addons are merged back.
    const parsedPayload = typeof rawPayload === "string" ? JSON.parse(rawPayload) : rawPayload;
    const { payload: addonGuardedPayload, guarded } = applyAddonWipeGuard(existing, parsedPayload);
    const guardedPayload = preserveIptvFields(existing, preserveTraktTokens(
      existing,
      preserveTrackingRouting(existing, addonGuardedPayload)
    ));
    if (guarded) {
      console.warn("account-sync-push: addon wipe guard engaged", {
        user: identity.supabaseUserId,
        incomingRootAddons: Array.isArray(parsedPayload.addons) ? parsedPayload.addons.length : null,
        preservedRootAddons: Array.isArray(guardedPayload.addons) ? guardedPayload.addons.length : null
      });
    }
    const incoming = payloadMetrics(guardedPayload);
    if (isExistingSnapshotRicher(existing, incoming)) {
      return json(200, {
        accepted: false,
        reason: "existing_snapshot_is_richer",
        existing,
        incoming: {
          restoreRank: incoming.restoreRank,
          profileCount: incoming.profileCount,
          scopedCoverage: incoming.scopedCoverage
        }
      });
    }

    const saved = await saveSnapshotToBlobs(event, identity, {
      payload: incoming.payload,
      payloadVersion: incoming.payloadVersion,
      restoreRank: incoming.restoreRank,
      profileCount: incoming.profileCount,
      scopedCoverage: incoming.scopedCoverage,
      payloadUpdatedAt: incoming.payloadUpdatedAt,
      source: "netlify"
    });
    await appendSnapshotEvent(event, identity, saved);

    return json(200, {
      accepted: true,
      addonGuard: guarded,
      restoreRank: incoming.restoreRank,
      profileCount: incoming.profileCount,
      scopedCoverage: incoming.scopedCoverage
    });
  } catch (error) {
    console.error("account-sync-push failed", error);
    return json(error?.statusCode || 500, {
      accepted: false,
      error: "sync_push_failed",
      message: error.message
    });
  }
};

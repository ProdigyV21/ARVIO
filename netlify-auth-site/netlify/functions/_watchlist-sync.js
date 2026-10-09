// Membership events are separate from cached artwork and provider ordering. Old
// clients can refresh those fields without undoing an explicit removal.
function parsed(value) {
  if (typeof value !== "string") return value;
  try { return JSON.parse(value); } catch { return null; }
}

function object(value) {
  const result = parsed(value);
  return result && typeof result === "object" && !Array.isArray(result) ? result : {};
}

function timestamp(value) {
  const result = Number(value);
  return Number.isFinite(result) && result > 0 ? result : 0;
}

function mergeWatchlistChanges(previous, incoming) {
  const changes = new Map();
  for (const source of [previous, incoming]) {
    for (const [key, raw] of Object.entries(object(source))) {
      const event = object(raw);
      const updatedAt = event.updatedAt;
      if (!/^(movie|tv):[1-9]\d*$/.test(key) || !Number.isSafeInteger(updatedAt) ||
          updatedAt <= 0 || typeof event.removed !== "boolean") continue;
      const existing = changes.get(key);
      if (!existing || updatedAt > existing.updatedAt ||
          (updatedAt === existing.updatedAt && event.removed)) {
        changes.set(key, { updatedAt, removed: event.removed });
      }
    }
  }
  return Object.fromEntries(changes);
}

function mergeWatchlistItems(previous, incoming, changes) {
  const items = new Map();
  for (const source of [previous, incoming]) {
    const list = parsed(source);
    if (!Array.isArray(list)) continue;
    for (const raw of list) {
      const item = object(raw);
      const mediaType = String(item.mediaType ?? "movie").trim().toLowerCase();
      const tmdbId = Number(item.tmdbId);
      if (!Number.isSafeInteger(tmdbId) || tmdbId <= 0 || !["movie", "tv"].includes(mediaType)) continue;
      const key = `${mediaType}:${tmdbId}`;
      if (changes[key]?.removed === true) continue;
      const existing = items.get(key);
      if (!existing || timestamp(item.addedAt) > timestamp(existing.addedAt)) {
        items.set(key, { ...item, mediaType, tmdbId });
      }
    }
  }
  const order = (item) => Number.isFinite(Number(item.sourceOrder)) ? Number(item.sourceOrder) : 2147483647;
  return [...items.values()].sort((a, b) => order(a) - order(b) || timestamp(b.addedAt) - timestamp(a.addedAt));
}

function preserveWatchlistChanges(existingSnapshot, incomingPayload) {
  const previous = object(existingSnapshot?.payload);
  const previousItems = object(previous.watchlistByProfile);
  const incomingItems = object(incomingPayload.watchlistByProfile);
  const previousChanges = object(previous.watchlistChangesByProfile);
  const incomingChanges = object(incomingPayload.watchlistChangesByProfile);
  const profileIds = new Set([
    ...Object.keys(previousItems), ...Object.keys(incomingItems),
    ...Object.keys(previousChanges), ...Object.keys(incomingChanges)
  ]);
  const activeIds = Array.isArray(incomingPayload.profiles)
    ? new Set(incomingPayload.profiles.map((profile) => profile?.id)) : null;
  const itemsByProfile = new Map();
  const changesByProfile = new Map();
  for (const id of profileIds) {
    if (activeIds && !activeIds.has(id)) continue;
    const changes = mergeWatchlistChanges(previousChanges[id], incomingChanges[id]);
    changesByProfile.set(id, changes);
    // Keep the old replacement contract until a profile has membership events
    // or the incoming client explicitly supports them. This avoids changing
    // removal behavior for accounts still using only legacy clients.
    const legacyReplacement = !Object.keys(changes).length &&
      !Object.prototype.hasOwnProperty.call(incomingChanges, id) && Array.isArray(parsed(incomingItems[id]));
    itemsByProfile.set(id, mergeWatchlistItems(legacyReplacement ? [] : previousItems[id], incomingItems[id], changes));
  }
  if (!profileIds.size) return incomingPayload;
  return {
    ...incomingPayload,
    watchlistByProfile: Object.fromEntries(itemsByProfile),
    watchlistChangesByProfile: Object.fromEntries(changesByProfile)
  };
}

module.exports = { mergeWatchlistChanges, mergeWatchlistItems, preserveWatchlistChanges };

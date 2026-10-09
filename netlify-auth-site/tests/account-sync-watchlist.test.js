const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const vm = require("node:vm");
const { preserveWatchlistChanges: merge, mergeWatchlistChanges } = require("../netlify/functions/_watchlist-sync");

const item = (id, addedAt = 100, mediaType = "movie") => ({ tmdbId: id, mediaType, title: `Title ${id}`, addedAt });
const payload = (items, changes = {}, profile = "p1") => ({
  profiles: [{ id: profile }], watchlistByProfile: { [profile]: items },
  watchlistChangesByProfile: { [profile]: changes }
});
const removed = (updatedAt) => ({ updatedAt, removed: true });
const added = (updatedAt) => ({ updatedAt, removed: false });

test("accounts with only legacy clients retain their previous list replacement behavior", () => {
  const previous = { watchlistByProfile: { p1: [item(1), item(2)] } };
  const incoming = { watchlistByProfile: { p1: [item(2)] } };
  assert.deepEqual(merge({ payload: previous }, incoming).watchlistByProfile.p1, [item(2)]);
});

test("a modern client with no local events still preserves another device's additions", () => {
  const previous = { watchlistByProfile: { p1: [item(1)] } };
  assert.deepEqual(merge({ payload: previous }, payload([item(2)])).watchlistByProfile.p1, [item(1), item(2)]);
});

test("a legacy push cannot restore a removed item even with a newer cache timestamp", () => {
  const previous = payload([], { "movie:1": removed(200) });
  const legacy = payload([item(1, 1000), item(2)]);
  delete legacy.watchlistChangesByProfile;
  const result = merge({ payload: previous }, legacy);
  assert.deepEqual(result.watchlistByProfile.p1, [item(2)]);
  assert.deepEqual(result.watchlistChangesByProfile.p1, { "movie:1": removed(200) });
});

test("deleting the last item stays deleted across repeated stale uploads", () => {
  let current = payload([item(1)]);
  current = merge({ payload: current }, payload([], { "movie:1": removed(200) }));
  for (let index = 0; index < 3; index += 1) {
    current = merge({ payload: current }, payload([item(1)], { "movie:1": added(100) }));
    assert.deepEqual(current.watchlistByProfile.p1, []);
    assert.deepEqual(current.watchlistChangesByProfile.p1["movie:1"], removed(200));
  }
});

test("only a later explicit addition can restore membership and unrelated offline additions survive", () => {
  const previous = payload([item(2)], { "movie:1": removed(200) });
  const incoming = payload([item(1, 300), item(3, 250)], { "movie:1": added(300), "movie:3": added(250) });
  const result = merge({ payload: previous }, incoming);
  assert.deepEqual(result.watchlistByProfile.p1.map((value) => value.tmdbId), [1, 3, 2]);
  assert.deepEqual(result.watchlistChangesByProfile.p1["movie:1"], added(300));
});

test("removal wins an event tie regardless of merge direction", () => {
  for (const [previous, incoming] of [[removed(200), added(200)], [added(200), removed(200)]]) {
    const result = mergeWatchlistChanges({ "movie:1": previous }, { "movie:1": incoming });
    assert.deepEqual(result["movie:1"], removed(200));
  }
});

test("items and events are isolated by profile and media type", () => {
  const previous = payload([], { "movie:1": removed(200) });
  const incoming = {
    profiles: [{ id: "p1" }, { id: "p2" }],
    watchlistByProfile: { p1: [item(1), item(1, 100, "tv")], p2: [item(1)] }
  };
  const result = merge({ payload: previous }, incoming);
  assert.deepEqual(result.watchlistByProfile.p1, [item(1, 100, "tv")]);
  assert.deepEqual(result.watchlistByProfile.p2, [item(1)]);
  assert.deepEqual(result.watchlistChangesByProfile.p2, {});
});

test("a deleted profile is not recreated by preserved server watchlists", () => {
  const previous = payload([item(1)], { "movie:2": removed(200) });
  const result = merge({ payload: previous }, payload([], {}, "p2"));
  assert.equal(result.watchlistByProfile.p1, undefined);
  assert.equal(result.watchlistChangesByProfile.p1, undefined);
});

test("partial old-client pushes preserve events and membership without changing other fields", () => {
  const previous = payload([item(2)], { "movie:1": removed(200) });
  const result = merge({ payload: previous }, { profiles: [{ id: "p1" }], accentColor: "purple" });
  assert.deepEqual(result.watchlistByProfile.p1, [item(2)]);
  assert.deepEqual(result.watchlistChangesByProfile.p1, previous.watchlistChangesByProfile.p1);
  assert.equal(result.accentColor, "purple");
  assert.deepEqual(previous.watchlistByProfile.p1, [item(2)]);
});

test("passive cached metadata keeps its timestamps and cannot create membership events", () => {
  const old = item(1, 100);
  const newer = { ...item(1, 200), posterPath: "/new.jpg", sourceOrder: 0 };
  const result = merge({ payload: payload([old]) }, payload([newer, item(2, 0)]));
  assert.deepEqual(result.watchlistByProfile.p1, [newer, item(2, 0)]);
  assert.deepEqual(result.watchlistChangesByProfile.p1, {});
});

test("nested legacy JSON is accepted while invalid membership events are ignored", () => {
  const previous = { watchlistChangesByProfile: JSON.stringify({ p1: JSON.stringify({ "movie:1": removed(200) }) }) };
  const incoming = { watchlistByProfile: JSON.stringify({ p1: JSON.stringify([item(1), item(2)]) }) };
  assert.deepEqual(merge({ payload: previous }, incoming).watchlistByProfile.p1, [item(2)]);
  assert.deepEqual(mergeWatchlistChanges({}, {
    "movie:1": { updatedAt: 300, removed: "false" },
    "movie:0": removed(300), "other:1": removed(300), "tv:2": removed(-1), "tv:3": removed(Infinity),
    "tv:4": removed("300"), "tv:5": removed(1.5)
  }), {});
});

function endpointFixture(initial, competing, alwaysConflict = false) {
  const exports = {};
  let snapshot = { payload: initial };
  let version = 1;
  let saves = 0;
  const appended = [];
  vm.runInNewContext(fs.readFileSync(require.resolve("../netlify/functions/account-sync-push"), "utf8"), {
    exports, console,
    require: (name) => name === "./_watchlist-sync" ? require("../netlify/functions/_watchlist-sync") : {
      json: (statusCode, body) => ({ statusCode, body }), options: () => null, parseBody: (event) => event.body,
      resolveIdentity: async () => ({ supabaseUserId: "user-1" }),
      applyAddonWipeGuard: (_existing, incoming) => ({ payload: incoming, guarded: false }),
      payloadMetrics: (value) => ({ payload: value }), isExistingSnapshotRicher: () => false,
      loadSnapshotForUpdate: async () => ({ snapshot, etag: String(version) }),
      saveSnapshotToBlobs: async (_event, _identity, next, condition) => {
        saves += 1;
        if (saves === 1 && competing) { snapshot = { payload: competing }; version += 1; }
        if (alwaysConflict || condition.etag !== String(version)) return null;
        snapshot = next; version += 1; return next;
      },
      appendSnapshotEvent: async (_event, _identity, value) => appended.push(value)
    }
  });
  return {
    request: (value) => exports.handler({ httpMethod: "POST", body: { payload: value } }),
    snapshot: () => snapshot, saves: () => saves, appended
  };
}

test("concurrent uploads retry against the latest snapshot before accepting a stale writer", async () => {
  const initial = payload([item(1)]);
  const fixture = endpointFixture(initial, payload([], { "movie:1": removed(200) }));
  const result = await fixture.request(payload([item(1), item(2, 300)], { "movie:2": added(300) }));
  assert.equal(result.body.accepted, true);
  assert.equal(fixture.saves(), 2);
  assert.deepEqual(fixture.snapshot().payload.watchlistByProfile.p1, [item(2, 300)]);
  assert.deepEqual(fixture.snapshot().payload.watchlistChangesByProfile.p1["movie:1"], removed(200));
  assert.equal(fixture.appended.length, 1);
});

test("exhausted write conflicts report a retryable failure without appending an uncommitted snapshot", async () => {
  const fixture = endpointFixture(payload([item(1)]), null, true);
  const result = await fixture.request(payload([], { "movie:1": removed(200) }));
  assert.equal(result.statusCode, 409);
  assert.equal(result.body.accepted, false);
  assert.equal(fixture.saves(), 5);
  assert.equal(fixture.appended.length, 0);
});

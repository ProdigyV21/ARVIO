const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const vm = require("node:vm");
const { applyProgressOperation, normalizeProgressOperation } = require("../netlify/functions/_sync-merge");

function payload() {
  return {
    version: 1,
    updatedAt: 1_000,
    activeProfileId: "p1",
    profiles: [{ id: "p1", name: "Arvind" }, { id: "p2", name: "Kids" }],
    deletedProfileIds: ["gone"],
    profileSettingsById: { p1: { autoPlayNext: true }, p2: { autoPlayNext: false } },
    fieldUpdatedAt: { "p:p1:autoPlayNext": 100 },
    watchlistByProfile: { p1: [{ tmdbId: 9, mediaType: "movie", title: "Nine", addedAt: 1 }] },
    dismissedContinueWatchingByProfile: { p1: "tv:30,700|tv:30:1:5,700|movie:1,50" },
    localContinueWatchingByProfile: {
      p1: [
        { id: 30, mediaType: "TV", title: "Show", season: 1, episode: 4, displaySeason: 1, displayEpisode: 4,
          progress: 55, resumePositionSeconds: 1300, durationSeconds: 2400, streamKey: "android-stream",
          streamAddonId: "com.torrentio", year: "2020", releaseDate: "", isUpNext: false, updatedAtMs: 800 },
        { id: 10, mediaType: "MOVIE", title: "Movie", progress: 20, resumePositionSeconds: 900, durationSeconds: 6000, updatedAtMs: 500 }
      ],
      p2: [{ id: 11, mediaType: "MOVIE", title: "Kids movie", progress: 30, updatedAtMs: 600 }]
    }
  };
}

const upsert = (item, profileId = "p1") => normalizeProgressOperation({ profileId, op: "upsert", item }, 10_000);
const webItem = (overrides = {}) => ({
  id: 30, mediaType: "tv", title: "Show", season: 1, episode: 4, displaySeason: 1, displayEpisode: 4,
  progress: 60, resumePositionSeconds: 1440, durationSeconds: 2400, streamKey: null, streamAddonId: null,
  isUpNext: false, updatedAtMs: 900, ...overrides
});

function withoutProgress(root) {
  const { localContinueWatchingByProfile: _cw, dismissedContinueWatchingByProfile: _dismissed, ...rest } = root;
  return rest;
}

test("an upsert updates the episode row and keeps what the web does not know", () => {
  const before = payload();
  const { payload: after, applied } = applyProgressOperation(before, upsert(webItem()));
  assert.equal(applied, true);
  const [row] = after.localContinueWatchingByProfile.p1;
  assert.equal(row.resumePositionSeconds, 1440);
  assert.equal(row.streamKey, "android-stream");
  assert.equal(row.streamAddonId, "com.torrentio");
  assert.equal(row.year, "2020");
  assert.deepEqual(withoutProgress(after), withoutProgress(before), "no other field changes");
  assert.deepEqual(after.localContinueWatchingByProfile.p2, before.localContinueWatchingByProfile.p2);
});

test("a new episode replaces the show's previous row (one row per show)", () => {
  const { payload: after } = applyProgressOperation(payload(), upsert(webItem({ episode: 5, displayEpisode: 5 })));
  const shows = after.localContinueWatchingByProfile.p1.filter((row) => row.id === 30);
  assert.equal(shows.length, 1);
  assert.equal(shows[0].episode, 5);
  assert.equal(shows[0].streamKey, null, "another episode's stream key is not carried over");
});

test("an older checkpoint never overwrites newer progress", () => {
  const result = applyProgressOperation(payload(), upsert(webItem({ updatedAtMs: 700, resumePositionSeconds: 10 })));
  assert.equal(result.applied, false);
  assert.equal(result.reason, "stale");
});

test("saving clears an older dismissal of the show and episode, like Android", () => {
  const { payload: after } = applyProgressOperation(payload(), upsert(webItem({ episode: 5 })));
  assert.equal(after.dismissedContinueWatchingByProfile.p1, "movie:1,50");
});

test("a removal drops only the finished episode and never newer progress", () => {
  const removeEpisode = (episode, updatedAtMs) => normalizeProgressOperation({
    profileId: "p1", op: "remove", match: { id: 30, mediaType: "tv", season: 1, episode }, updatedAtMs
  }, 10_000);
  assert.equal(applyProgressOperation(payload(), removeEpisode(3, 900)).applied, false, "other episode");
  assert.equal(applyProgressOperation(payload(), removeEpisode(4, 700)).applied, false, "newer row");
  const { payload: after } = applyProgressOperation(payload(), removeEpisode(4, 900));
  assert.deepEqual(after.localContinueWatchingByProfile.p1.map((row) => row.id), [10]);
});

test("progress for a deleted profile is ignored", () => {
  const result = applyProgressOperation(payload(), upsert(webItem(), "gone"));
  assert.equal(result.applied, false);
  assert.equal(result.reason, "profile_deleted");
});

test("invalid operations are rejected and unknown fields stripped", () => {
  for (const body of [
    { profileId: "", op: "upsert", item: webItem() },
    { profileId: "p1", op: "upsert", item: webItem({ title: " " }) },
    { profileId: "p1", op: "upsert", item: webItem({ id: "x" }) },
    { profileId: "p1", op: "upsert", item: webItem({ mediaType: "channel" }) },
    { profileId: "p1", op: "upsert", item: webItem({ progress: 140 }) },
    { profileId: "p1", op: "upsert", item: webItem({ updatedAtMs: 0 }) },
    { profileId: "p1", op: "replace", item: webItem() }
  ]) assert.throws(() => normalizeProgressOperation(body, 10_000), (error) => error.statusCode === 400);
  const { item } = normalizeProgressOperation({ profileId: "p1", op: "upsert", item: { ...webItem(), payload: { huge: true }, updatedAtMs: 99_999_999 } }, 10_000);
  assert.equal("payload" in item, false);
  assert.equal(item.updatedAtMs, 10_000, "far-future device clocks are clamped");
});

function endpoint(initial, { competing = null, alwaysConflict = false } = {}) {
  const exports = {};
  let snapshot = initial === null ? null : { payload: initial };
  let version = 1;
  let saves = 0;
  const identities = [];
  vm.runInNewContext(fs.readFileSync(require.resolve("../netlify/functions/account-sync-progress"), "utf8"), {
    exports, console,
    require: (name) => name === "./_sync-merge" ? require("../netlify/functions/_sync-merge") : {
      json: (statusCode, body) => ({ statusCode, body }),
      options: () => null,
      parseBody: (event) => JSON.parse(event.body),
      payloadMetrics: (value) => ({ payload: value, restoreRank: 80, profileCount: 2 }),
      resolveIdentity: async (event) => {
        identities.push(event.headers.authorization);
        if (event.headers.authorization !== "Bearer good-token") throw Object.assign(new Error("Token rejected"), { statusCode: 401 });
        return { supabaseUserId: "user-1" };
      },
      loadSnapshotForUpdate: async () => ({ snapshot, etag: snapshot ? String(version) : null }),
      saveSnapshotToBlobs: async (_event, _identity, next, condition) => {
        saves += 1;
        if (saves === 1 && competing) { snapshot = { payload: competing(structuredClone(snapshot.payload)) }; version += 1; }
        if (alwaysConflict || condition.etag !== String(version)) return null;
        snapshot = next; version += 1; return next;
      }
    }
  });
  return {
    request: (body, headers = {}) => exports.handler({ httpMethod: "POST", headers, body: typeof body === "string" ? body : JSON.stringify(body) }),
    snapshot: () => snapshot, saves: () => saves, identities
  };
}

test("the endpoint accepts the access token in the body for keepalive sends", async () => {
  const fixture = endpoint(payload());
  const result = await fixture.request({ profileId: "p1", op: "upsert", item: webItem(), accessToken: "good-token" });
  assert.equal(result.statusCode, 200);
  assert.deepEqual({ ...result.body }, { accepted: true, applied: true });
  assert.equal(fixture.snapshot().payload.localContinueWatchingByProfile.p1[0].resumePositionSeconds, 1440);
  assert.equal(fixture.identities[0], "Bearer good-token");
});

test("the endpoint authenticates exactly like account-sync-push with a bearer header", async () => {
  const fixture = endpoint(payload());
  const ok = await fixture.request({ profileId: "p1", op: "upsert", item: webItem() }, { authorization: "Bearer good-token" });
  assert.equal(ok.statusCode, 200);
  const rejected = await fixture.request({ profileId: "p1", op: "upsert", item: webItem(), accessToken: "forged" });
  assert.equal(rejected.statusCode, 401);
  const missing = await fixture.request({ profileId: "p1", op: "upsert", item: webItem() });
  assert.equal(missing.statusCode, 401);
});

test("a concurrent full-document write is re-read and both changes survive", async () => {
  const fixture = endpoint(payload(), {
    competing: (current) => ({ ...current, accentColor: "Purple", fieldUpdatedAt: { ...current.fieldUpdatedAt, "g:accentColor": 950 } })
  });
  const result = await fixture.request({ profileId: "p1", op: "upsert", item: webItem(), accessToken: "good-token" });
  assert.equal(result.body.applied, true);
  assert.equal(fixture.saves(), 2);
  assert.equal(fixture.snapshot().payload.accentColor, "Purple");
  assert.equal(fixture.snapshot().payload.localContinueWatchingByProfile.p1[0].resumePositionSeconds, 1440);
});

test("the endpoint reports conflicts, missing accounts and bad input without writing", async () => {
  const busy = endpoint(payload(), { alwaysConflict: true });
  const conflict = await busy.request({ profileId: "p1", op: "upsert", item: webItem(), accessToken: "good-token" });
  assert.equal(conflict.statusCode, 409);
  assert.equal(conflict.body.reason, "snapshot_write_conflict");
  const empty = endpoint(null);
  const none = await empty.request({ profileId: "p1", op: "upsert", item: webItem(), accessToken: "good-token" });
  assert.equal(none.statusCode, 409);
  assert.equal(none.body.reason, "no_snapshot");
  const bad = endpoint(payload());
  assert.equal((await bad.request({ profileId: "p1", op: "upsert", item: { id: 1 }, accessToken: "good-token" })).statusCode, 400);
  assert.equal((await bad.request("{not json", {})).statusCode, 400);
  assert.equal((await bad.request("x".repeat(20_000), {})).statusCode, 413);
  assert.equal(bad.saves(), 0);
  const stale = await bad.request({ profileId: "p1", op: "upsert", item: webItem({ updatedAtMs: 1 }), accessToken: "good-token" });
  assert.deepEqual({ ...stale.body }, { accepted: true, applied: false, reason: "stale" });
  assert.equal(bad.saves(), 0);
});

test("one progress item stays far below the 64 KB keepalive limit", () => {
  const body = JSON.stringify({
    profileId: "4a1f0f0e-7c1d-4f3a-9a77-9c41d1f5e001", op: "upsert", accessToken: "x".repeat(900),
    item: webItem({ title: "A".repeat(200), episodeTitle: "B".repeat(200), posterPath: "/p".repeat(40), backdropPath: "/b".repeat(40) })
  });
  assert.ok(body.length < 4_096, `${body.length} bytes`);
});

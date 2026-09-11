const test = require("node:test");
const assert = require("node:assert/strict");
const { _test: { preserveIptvFields: merge } } = require("../netlify/functions/account-sync-push");
const payload = (playlists, timestamp) => ({ profiles: [{ id: "p1" }],
  iptvByProfile: { p1: { playlists } }, fieldUpdatedAt: { "i:p1:playlists": timestamp } });

test("deleted playlist cannot return from an older or legacy push", () => {
  const previous = { payload: payload(["a"], 200) };
  for (const time of [0, 100, 200]) {
    assert.deepEqual(merge(previous, payload(["a", "b"], time)).iptvByProfile.p1.playlists, ["a"]);
  }
});

test("deleting all playlists and later adding a new one are both allowed", () => {
  const empty = merge({ payload: payload(["a"], 100) }, payload([], 200));
  assert.deepEqual(empty.iptvByProfile.p1.playlists, []);
  assert.deepEqual(merge({ payload: empty }, payload(["c"], 300)).iptvByProfile.p1.playlists, ["c"]);
});

test("playlist protection preserves unrelated new preferences", () => {
  const incoming = payload(["a", "b"], 100);
  incoming.iptvByProfile.p1.hiddenGroups = ["hidden"];
  incoming.iptvByProfile.p1.tvSession = { lastChannelId: "playing" };
  const result = merge({ payload: payload(["a"], 200) }, incoming);
  assert.deepEqual(result.iptvByProfile.p1.hiddenGroups, ["hidden"]);
  assert.equal(result.iptvByProfile.p1.tvSession.lastChannelId, "playing");
});

test("IPTV protection does not recreate data for a deleted profile", () => {
  const incoming = { profiles: [{ id: "p2" }], iptvByProfile: { p2: { playlists: [] } } };
  const result = merge({ payload: payload(["a"], 200) }, incoming);
  assert.equal(result.iptvByProfile.p1, undefined);
});

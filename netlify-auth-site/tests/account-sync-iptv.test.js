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

test("a web channel order survives a push from an app that does not know the field yet", () => {
  const previous = payload(["a"], 100);
  previous.iptvByProfile.p1.channelOrder = { "list_1|News": ["list_1:b", "list_1:a"] };
  previous.fieldUpdatedAt["i:p1:channelOrder"] = 300;
  const result = merge({ payload: previous }, payload(["a"], 100));
  assert.deepEqual(result.iptvByProfile.p1.channelOrder, { "list_1|News": ["list_1:b", "list_1:a"] });
  assert.equal(result.fieldUpdatedAt["i:p1:channelOrder"], 300);
  const newer = payload(["a"], 100);
  newer.iptvByProfile.p1.channelOrder = {};
  newer.fieldUpdatedAt["i:p1:channelOrder"] = 400;
  assert.deepEqual(merge({ payload: previous }, newer).iptvByProfile.p1.channelOrder, {});
});

test("web category names survive a push from an app that does not know the field yet", () => {
  const previous = payload(["a"], 100);
  previous.iptvByProfile.p1.groupNames = { "list_1|News": "Headlines" };
  previous.fieldUpdatedAt["i:p1:groupNames"] = 300;
  const result = merge({ payload: previous }, payload(["a"], 100));
  assert.deepEqual(result.iptvByProfile.p1.groupNames, { "list_1|News": "Headlines" });
  assert.equal(result.fieldUpdatedAt["i:p1:groupNames"], 300);
  const newer = payload(["a"], 100);
  newer.iptvByProfile.p1.groupNames = {};
  newer.fieldUpdatedAt["i:p1:groupNames"] = 400;
  assert.deepEqual(merge({ payload: previous }, newer).iptvByProfile.p1.groupNames, {});
});

test("a web portal list survives a push from an app that only knows the single portal", () => {
  const portals = [{ id: "stalker1", name: "Home", portalUrl: "http://a.example", macAddress: "00:1A:79:00:00:01", enabled: true },
    { id: "stalker2", name: "Away", portalUrl: "http://b.example", macAddress: "00:1A:79:00:00:02", enabled: true }];
  const previous = payload(["a"], 100);
  previous.iptvByProfile.p1.stalkerPortals = portals;
  previous.fieldUpdatedAt["i:p1:stalkerPortals"] = 300;
  const old = payload(["a"], 100);
  old.iptvByProfile.p1.stalkerPortalUrl = "http://a.example";
  assert.deepEqual(merge({ payload: previous }, old).iptvByProfile.p1.stalkerPortals, portals);
});

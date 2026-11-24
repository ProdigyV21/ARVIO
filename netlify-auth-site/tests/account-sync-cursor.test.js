const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const vm = require("node:vm");

function endpoint({ revision = "etag-1", failRevision = false, rejectIdentity = false } = {}) {
  const exports = {};
  const state = { revision, reads: 0 };
  vm.runInNewContext(fs.readFileSync(require.resolve("../netlify/functions/account-sync-cursor"), "utf8"), {
    exports, console: { error() {}, log() {} }, Date,
    require: () => ({
      json: (statusCode, body) => ({ statusCode, body }),
      options: () => null,
      resolveIdentity: async () => {
        if (rejectIdentity) throw Object.assign(new Error("Token rejected"), { statusCode: 401 });
        return { supabaseUserId: "user-1" };
      },
      loadSnapshotRevision: async () => {
        state.reads += 1;
        if (failRevision) throw new Error("blob store unavailable");
        return state.revision;
      }
    })
  });
  return { call: () => exports.handler({ httpMethod: "GET", headers: {} }), state };
}

test("the cursor call returns the revision of the stored account document", async () => {
  const fixture = endpoint({ revision: "etag-7" });
  const result = await fixture.call();
  assert.equal(result.statusCode, 200);
  assert.equal(result.body.revision, "etag-7");
  assert.ok(Number.isFinite(result.body.cursor), "the clock cursor stays for older clients");
});

test("the revision follows the document: a new save gives a new value, no save gives the same one", async () => {
  const fixture = endpoint({ revision: "etag-1" });
  const first = (await fixture.call()).body.revision;
  assert.equal((await fixture.call()).body.revision, first);
  fixture.state.revision = "etag-2";
  assert.notEqual((await fixture.call()).body.revision, first);
});

test("an account without a stored document reports a null revision", async () => {
  const result = await endpoint({ revision: null }).call();
  assert.equal(result.statusCode, 200);
  assert.equal(result.body.revision, null);
});

test("a failing revision read never fails the call; clients just skip the check", async () => {
  const result = await endpoint({ failRevision: true }).call();
  assert.equal(result.statusCode, 200);
  assert.equal(result.body.revision, null);
});

test("an unauthenticated caller is refused and no revision is read", async () => {
  const fixture = endpoint({ rejectIdentity: true });
  const result = await fixture.call();
  assert.equal(result.statusCode, 401);
  assert.equal(fixture.state.reads, 0);
});

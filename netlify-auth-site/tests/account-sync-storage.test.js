const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const vm = require("node:vm");

function fixture() {
  const records = new Map();
  const writes = [];
  let version = 0;
  let beforeWrite;
  const keyFor = (store, key) => `${store}/${key}`;
  const seed = (store, key, data) => records.set(keyFor(store, key), { data, etag: String(++version) });
  const getStore = (name) => ({
    get: async (key) => records.get(keyFor(name, key))?.data || null,
    getWithMetadata: async (key) => records.get(keyFor(name, key)) || null,
    setJSON: async (key, data, options) => {
      if (beforeWrite) { const callback = beforeWrite; beforeWrite = null; await callback(); }
      const current = records.get(keyFor(name, key));
      if ((options?.onlyIfNew && current) || (options?.onlyIfMatch && current?.etag !== options.onlyIfMatch)) {
        return { modified: false };
      }
      writes.push({ store: name, key, options });
      seed(name, key, data);
      return { modified: true, etag: String(version) };
    }
  });
  const module = { exports: {} };
  vm.runInNewContext(fs.readFileSync(require.resolve("../netlify/functions/_backend"), "utf8"), {
    module, Buffer, process, console, URL, setTimeout, clearTimeout,
    require: (name) => name === "@netlify/blobs" ? { connectLambda: () => {}, getStore } : require(name)
  });
  const backend = module.exports;
  const identity = { supabaseUserId: "account-id", email: "person@example.test" };
  const keys = backend.snapshotKeys(identity);
  return {
    backend, identity, keys, writes, seed,
    record: (name, key) => records.get(keyFor(name, key)),
    beforeWrite: (callback) => { beforeWrite = callback; }
  };
}

test("conditional snapshot saves do not overwrite or mirror a newer canonical version", async () => {
  const value = fixture();
  value.seed("account-sync", value.keys.supabase, { payload: { version: "old" } });
  const read = await value.backend.loadSnapshotForUpdate({}, value.identity);
  value.seed("account-sync", value.keys.supabase, { payload: { version: "new" } });
  const saved = await value.backend.saveSnapshotToBlobs({}, value.identity, read.snapshot, { etag: read.etag });
  assert.equal(saved, null);
  assert.equal(value.record("account-sync", value.keys.supabase).data.payload.version, "new");
  assert.equal(value.record("account-sync", value.keys.email), undefined);
  assert.equal(value.writes.length, 0);
});

test("first upload uses conditional creation and reads the canonical version for subsequent writes", async () => {
  const value = fixture();
  const empty = await value.backend.loadSnapshotForUpdate({}, value.identity);
  assert.equal(empty.snapshot, null);
  assert.equal(empty.etag, null);
  await value.backend.saveSnapshotToBlobs({}, value.identity, { payload: { watchlistByProfile: {} } }, { etag: empty.etag });
  assert.equal(value.writes[0].options.onlyIfNew, true);
  const current = await value.backend.loadSnapshotForUpdate({}, value.identity);
  assert.equal(current.etag, value.record("account-sync", value.keys.supabase).etag);
  assert.equal(current.snapshot, value.record("account-sync", value.keys.supabase).data);
  assert.equal(value.writes.length, 2);
});

test("legacy fallback claiming cannot overwrite a concurrently uploaded deletion", async () => {
  const value = fixture();
  value.seed("legacy-supabase-sync", value.keys.supabase, { payload: { watchlistByProfile: { p1: [{ tmdbId: 1 }] } } });
  const deleted = {
    payload: {
      watchlistByProfile: { p1: [] },
      watchlistChangesByProfile: { p1: { "movie:1": { updatedAt: 200, removed: true } } }
    }
  };
  value.beforeWrite(() => value.seed("account-sync", value.keys.supabase, deleted));
  const snapshot = await value.backend.loadSnapshotFromBlobs({}, value.identity);
  assert.equal(snapshot, deleted);
  assert.equal(value.record("account-sync", value.keys.supabase).data, deleted);
  assert.equal(value.writes.length, 0);
});

const test = require("node:test");
const assert = require("node:assert/strict");
const { _test } = require("../netlify/functions/account-sync-push");

test("stale and equal-time device snapshots preserve WeTrakr routing preferences", () => {
  const selection = { provider: "WETRAKR", watchlistReadMode: "WETRAKR", writeToWeTrakr: true, updatedAt: 200 };
  const existing = { payload: { mdbListSyncByProfile: { profile: selection } } };
  for (const updatedAt of [100, 200]) {
    const incoming = { mdbListSyncByProfile: { profile: { provider: "TRAKT", writeToWeTrakr: false, updatedAt } } };
    assert.deepEqual(_test.preserveTrackingRouting(existing, incoming).mdbListSyncByProfile.profile, selection);
  }
  const newer = { mdbListSyncByProfile: { profile: { ...selection, writeToWeTrakr: false, updatedAt: 300 } } };
  assert.equal(_test.preserveTrackingRouting(existing, newer).mdbListSyncByProfile.profile.writeToWeTrakr, false);
});

test("newer tracking routing survives a stale device push", () => {
  const existing = {
    payload: {
      mdbListSyncByProfile: {
        profile: { provider: "TRAKT", watchlistReadMode: "TRAKT", updatedAt: 200 }
      }
    }
  };
  const incoming = {
    mdbListSyncByProfile: {
      profile: { provider: "TRAKT", watchlistReadMode: "BOTH", updatedAt: 100 }
    }
  };

  const result = _test.preserveTrackingRouting(existing, incoming);
  assert.equal(result.mdbListSyncByProfile.profile.watchlistReadMode, "TRAKT");
  assert.equal(result.mdbListSyncByProfile.profile.updatedAt, 200);
});

test("credential domains merge independently from routing", () => {
  const existing = {
    payload: {
      mdbListSyncByProfile: {
        profile: {
          watchlistReadMode: "TRAKT",
          updatedAt: 100,
          simklAccessToken: "new-token",
          simklCredentialUpdatedAt: 300
        }
      }
    }
  };
  const incoming = {
    mdbListSyncByProfile: {
      profile: {
        watchlistReadMode: "SIMKL",
        updatedAt: 200,
        simklAccessToken: "old-token",
        simklCredentialUpdatedAt: 150
      }
    }
  };

  const result = _test.preserveTrackingRouting(existing, incoming);
  assert.equal(result.mdbListSyncByProfile.profile.watchlistReadMode, "SIMKL");
  assert.equal(result.mdbListSyncByProfile.profile.simklAccessToken, "new-token");
});

test("a newer explicit Trakt disconnect beats an older cloud token", () => {
  const existing = {
    payload: {
      traktTokens: { profile: { accessToken: "token", updatedAt: 100 } }
    }
  };
  const incoming = { traktTokens: { profile: { updatedAt: 200 } } };

  const result = _test.preserveTraktTokens(existing, incoming);
  assert.deepEqual(result.traktTokens.profile, { updatedAt: 200 });
});

test("a missing Trakt token from a stale snapshot cannot erase cloud auth", () => {
  const existing = {
    payload: {
      traktTokens: { profile: { accessToken: "token", updatedAt: 200 } }
    }
  };

  const result = _test.preserveTraktTokens(existing, { traktTokens: {} });
  assert.equal(result.traktTokens.profile.accessToken, "token");
});

test("newer MDBList OAuth credential survives stale device push", () => {
  const existing = {
    payload: {
      mdbListSyncByProfile: {
        profile: {
          mdbListAccessToken: "cloud-access-token",
          mdbListRefreshToken: "cloud-refresh-token",
          mdbListTokenExpiresAt: 999999,
          mdbListCredentialUpdatedAt: 300
        }
      }
    }
  };
  const incoming = {
    mdbListSyncByProfile: {
      profile: {
        mdbListAccessToken: "stale-access-token",
        mdbListCredentialUpdatedAt: 150
      }
    }
  };

  const result = _test.preserveTrackingRouting(existing, incoming);
  assert.equal(result.mdbListSyncByProfile.profile.mdbListAccessToken, "cloud-access-token");
  assert.equal(result.mdbListSyncByProfile.profile.mdbListRefreshToken, "cloud-refresh-token");
  assert.equal(result.mdbListSyncByProfile.profile.mdbListTokenExpiresAt, 999999);
  assert.equal(result.mdbListSyncByProfile.profile.mdbListCredentialUpdatedAt, 300);
});

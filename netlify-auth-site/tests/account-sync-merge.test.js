const test = require("node:test");
const assert = require("node:assert/strict");
const {
  mergeProfileList,
  mergeTimestampedSettings,
  mergeContinueWatching,
  mergeContinueWatchingDismissals
} = require("../netlify/functions/_sync-merge");
const { _test: { mergeWithStoredSnapshot } } = require("../netlify/functions/account-sync-push");

// Shapes copied from CloudSyncRepository.buildCloudSnapshotJson (Android 2.x).
function androidProfileSettings(overrides = {}) {
  return {
    defaultSubtitle: "Off", defaultAudioLanguage: "Auto (Original)", contentLanguage: "en-US",
    homePresentationVersion: 1, trailerAutoPlay: true, trailerSoundEnabled: false, trailerDelaySeconds: 1,
    trailerInCards: true, netflixHomeStyle: true, watchlistOnHome: false, clockFormat: "24h",
    guideRowCount: 0, showBudget: true, showEpisodeRatings: false, animeEpisodeStructuring: "broadcast",
    iptvFavoritesOnHome: true, showLoadingStats: true, spoilerBlurEnabled: false, volumeBoostDb: 0,
    dnsProvider: "system", subtitleUsageJson: "", subtitleSettingsUpdatedAt: 0, subtitleSize: "Medium",
    subtitleColor: "White", subtitleOffset: "Bottom", subtitleStyle: "Bold", subtitleFont: "System",
    subtitleStylized: true, secondarySubtitle: "Off", filterSubtitlesByLanguage: true, useForcedSubtitles: false,
    homeServerConnectionJson: "", torrServerBaseUrl: "", catalogueRowLayoutModes: {}, cardLayoutMode: "Landscape",
    frameRateMatchingMode: "Off", autoPlayNext: true, autoPlaySingleSource: true, autoPlayMinQuality: "Any",
    includeSpecials: false, autoPlayMaxQuality: "Any", autoPlayMaxSizeGb: 0, streamSearchMode: "auto",
    ...overrides
  };
}

function cwItem(id, updatedAtMs, overrides = {}) {
  return {
    id, title: `Title ${id}`, mediaType: "MOVIE", progress: 40, resumePositionSeconds: 1200, durationSeconds: 3000,
    season: null, episode: null, displaySeason: null, displayEpisode: null, episodeTitle: null,
    backdropPath: "/b.jpg", posterPath: "/p.jpg", streamKey: `stream-${id}`, streamAddonId: "com.torrentio",
    streamTitle: "1080p", year: "2024", releaseDate: "", isUpNext: false, overview: "", imdbRating: "",
    tmdbRating: "", duration: "", updatedAtMs, totalEpisodes: 0, watchedEpisodes: 0, ...overrides
  };
}

function androidPayload(overrides = {}) {
  return {
    version: 1,
    updatedAt: 1_000,
    defaultSubtitle: "Off",
    accentColor: "White",
    oledBlackBackground: false,
    skipProfileSelection: false,
    customUserAgent: "",
    dnsProvider: "system",
    subtitleAiEnabled: false,
    activeProfileId: "p1",
    profiles: [
      { id: "p1", name: "Arvind", avatarId: 3, isKidsProfile: false, isLocked: false, pin: null, createdAt: 1, lastUsedAt: 10, updatedAt: 0 },
      { id: "p2", name: "Kids", avatarId: 5, isKidsProfile: true, isLocked: false, pin: null, createdAt: 2, lastUsedAt: 5, updatedAt: 0 }
    ],
    profileAvatarImagesById: {},
    profileSettingsById: { p1: androidProfileSettings(), p2: androidProfileSettings({ contentLanguage: "nl-NL" }) },
    traktTokens: {},
    mdbListSyncByProfile: {},
    dismissedContinueWatchingByProfile: {},
    localContinueWatchingByProfile: { p1: [cwItem(10, 500)] },
    localWatchedMoviesByProfile: {},
    localWatchedEpisodesByProfile: {},
    addonsByProfile: { p1: [], p2: [] },
    addonsUpdatedAt: 0,
    addonChanges: {},
    catalogsByProfile: { p1: [{ id: "trending", title: "Trending", isPreinstalled: true }], p2: [] },
    hiddenPreinstalledByProfile: { p1: [], p2: [] },
    hiddenAddonByProfile: { p1: [], p2: [] },
    hiddenHomeServerByProfile: { p1: [], p2: [] },
    iptvByProfile: { p1: { m3uUrl: "", epgUrl: "", playlists: [] }, p2: { m3uUrl: "", epgUrl: "", playlists: [] } },
    watchlistByProfile: { p1: [], p2: [] },
    addons: [],
    catalogs: [],
    fieldUpdatedAt: {},
    ...overrides
  };
}

const clone = (value) => structuredClone(value);
const merge = (stored, incoming, base) => mergeWithStoredSnapshot({ payload: stored }, incoming, base);

test("a web edit and a concurrent Android edit of different fields both survive", () => {
  const read = androidPayload({ fieldUpdatedAt: { "p:p1:autoPlayNext": 100, "p:p1:subtitleSize": 100 } });
  // Android changes autoPlayNext after the web read the document...
  const stored = clone(read);
  stored.profileSettingsById.p1.autoPlayNext = false;
  stored.fieldUpdatedAt["p:p1:autoPlayNext"] = 200;
  // ...and the web, still holding the old copy, changes the subtitle size.
  const web = clone(read);
  web.profileSettingsById.p1.subtitleSize = "Large";
  web.fieldUpdatedAt["p:p1:subtitleSize"] = 300;
  const result = merge(stored, web);
  assert.equal(result.profileSettingsById.p1.autoPlayNext, false);
  assert.equal(result.profileSettingsById.p1.subtitleSize, "Large");
  assert.equal(result.fieldUpdatedAt["p:p1:autoPlayNext"], 200);
  assert.equal(result.fieldUpdatedAt["p:p1:subtitleSize"], 300);
});

test("for the same field the newer timestamp wins in either direction", () => {
  const stored = androidPayload({ fieldUpdatedAt: { "p:p1:clockFormat": 500, "g:accentColor": 100 }, accentColor: "Red" });
  stored.profileSettingsById.p1.clockFormat = "12h";
  const incoming = androidPayload({ fieldUpdatedAt: { "p:p1:clockFormat": 400, "g:accentColor": 900 }, accentColor: "Blue" });
  const result = merge(stored, incoming);
  assert.equal(result.profileSettingsById.p1.clockFormat, "12h", "older push loses");
  assert.equal(result.accentColor, "Blue", "newer push wins");
  assert.deepEqual(
    { clock: result.fieldUpdatedAt["p:p1:clockFormat"], accent: result.fieldUpdatedAt["g:accentColor"] },
    { clock: 500, accent: 900 }
  );
});

test("an equal timestamp keeps the pushed value, like Android's strict comparison", () => {
  const stored = androidPayload({ accentColor: "Red", fieldUpdatedAt: { "g:accentColor": 100 } });
  const incoming = androidPayload({ accentColor: "Blue", fieldUpdatedAt: { "g:accentColor": 100 } });
  assert.equal(merge(stored, incoming).accentColor, "Blue");
});

test("global settings changed on Android between the web's read and write survive", () => {
  const read = androidPayload({ fieldUpdatedAt: { "g:accentColor": 50 } });
  const stored = { ...clone(read), accentColor: "Purple", fieldUpdatedAt: { "g:accentColor": 200 } };
  const web = { ...clone(read), oledBlackBackground: true, fieldUpdatedAt: { "g:accentColor": 50, "g:oledBlackBackground": 300 } };
  const result = merge(stored, web);
  assert.equal(result.accentColor, "Purple");
  assert.equal(result.oledBlackBackground, true);
});

test("an Android push without any timestamps still works", () => {
  // Never-stamped account: today's behaviour, the push replaces values.
  const stored = androidPayload();
  const legacy = androidPayload({ accentColor: "Green" });
  delete legacy.fieldUpdatedAt;
  legacy.profileSettingsById.p1.autoPlayNext = false;
  const result = merge(stored, legacy);
  assert.equal(result.accentColor, "Green");
  assert.equal(result.profileSettingsById.p1.autoPlayNext, false);
  // A stamped account: only the stamped field is protected; others still change.
  const stamped = androidPayload({ accentColor: "Red", fieldUpdatedAt: { "g:accentColor": 100 } });
  const again = merge(stamped, legacy);
  assert.equal(again.accentColor, "Red");
  assert.equal(again.profileSettingsById.p1.autoPlayNext, false);
  assert.equal(again.fieldUpdatedAt["g:accentColor"], 100);
});

test("fields the pushed copy lacks are never dropped", () => {
  const stored = androidPayload();
  stored.profileSettingsById.p1.streamProviderEnabledMap = { "stremio:torrentio": true };
  stored.subtitleHearingSyncV2 = true;
  const web = androidPayload();
  delete web.profileSettingsById.p1.cardLayoutMode;
  delete web.profileSettingsById.p2;
  const result = merge(stored, web);
  assert.deepEqual(result.profileSettingsById.p1.streamProviderEnabledMap, { "stremio:torrentio": true });
  assert.equal(result.profileSettingsById.p1.cardLayoutMode, "Landscape");
  assert.equal(result.profileSettingsById.p2.contentLanguage, "nl-NL");
  assert.equal(result.subtitleHearingSyncV2, true);
});

test("a legacy timestamp cannot reintroduce pre-2.3 home defaults", () => {
  const stored = androidPayload({ fieldUpdatedAt: { "p:p1:trailerAutoPlay": 900 } });
  stored.profileSettingsById.p1 = androidProfileSettings({ homePresentationVersion: 0, trailerAutoPlay: false });
  const incoming = androidPayload({ fieldUpdatedAt: { "p:p1:trailerAutoPlay": 100 } });
  const result = merge(stored, incoming);
  assert.equal(result.profileSettingsById.p1.trailerAutoPlay, true);
  assert.equal(result.fieldUpdatedAt["p:p1:trailerAutoPlay"], 100);
});

test("the default subtitle follows subtitleSettingsUpdatedAt", () => {
  const stored = androidPayload();
  stored.profileSettingsById.p1 = androidProfileSettings({ defaultSubtitle: "Dutch", subtitleSettingsUpdatedAt: 500 });
  const stale = androidPayload();
  stale.profileSettingsById.p1 = androidProfileSettings({ defaultSubtitle: "English", subtitleSettingsUpdatedAt: 100 });
  assert.equal(merge(stored, stale).profileSettingsById.p1.defaultSubtitle, "Dutch");
  const newer = androidPayload();
  newer.profileSettingsById.p1 = androidProfileSettings({ defaultSubtitle: "English", subtitleSettingsUpdatedAt: 900 });
  assert.equal(merge(stored, newer).profileSettingsById.p1.defaultSubtitle, "English");
});

test("catalog lists merge by c: timestamps, including deliberately empty lists", () => {
  const stored = androidPayload({ fieldUpdatedAt: { "c:p1:catalogsByProfile": 300, "c:p1:hiddenAddonByProfile": 100 } });
  stored.catalogsByProfile.p1 = [{ id: "android-new" }];
  stored.hiddenAddonByProfile.p1 = ["old-hidden"];
  const web = androidPayload({ fieldUpdatedAt: { "c:p1:catalogsByProfile": 200, "c:p1:hiddenAddonByProfile": 400 } });
  web.catalogsByProfile.p1 = [{ id: "web-stale" }];
  web.hiddenAddonByProfile.p1 = [];
  const result = merge(stored, web);
  assert.deepEqual(result.catalogsByProfile.p1, [{ id: "android-new" }]);
  assert.deepEqual(result.hiddenAddonByProfile.p1, []);
});

test("older client catalogsByProfile push preserves omitted clientDelegate, clientFilter, and addonMetadataByKey", () => {
  const stored = androidPayload({
    fieldUpdatedAt: { "c:p1:catalogsByProfile": 200 },
    addonMetadataByKey: {
      "https://addon.example/p/p1": {
        metadataUpdatedAt: 500,
        catalogs: [{ id: "vod-recent", type: "movie", clientDelegate: "vod:recent?type=movie" }]
      }
    }
  });
  stored.catalogsByProfile.p1 = [{
    id: "addon_1",
    title: "VOD Recent",
    sourceType: "ADDON",
    addonId: "tv.arvio.discover_abc",
    addonCatalogId: "vod-recent",
    addonCatalogType: "movie",
    clientDelegate: "vod:recent?type=movie",
    clientFilter: "{\"minYear\":2022}"
  }];

  const legacyMobile = androidPayload({ fieldUpdatedAt: { "c:p1:catalogsByProfile": 400 } });
  legacyMobile.catalogsByProfile.p1 = [{
    id: "addon_1",
    title: "VOD Recent Reordered",
    sourceType: "ADDON",
    addonId: "tv.arvio.discover_abc",
    addonCatalogId: "vod-recent",
    addonCatalogType: "movie"
  }];

  const result = merge(stored, legacyMobile);
  assert.equal(result.catalogsByProfile.p1[0].title, "VOD Recent Reordered");
  assert.equal(result.catalogsByProfile.p1[0].clientDelegate, "vod:recent?type=movie");
  assert.equal(result.catalogsByProfile.p1[0].clientFilter, "{\"minYear\":2022}");
  assert.equal(result.addonMetadataByKey["https://addon.example/p/p1"].metadataUpdatedAt, 500);
});

test("a deleted profile stays deleted when a stale device still pushes it", () => {
  const stored = androidPayload({ deletedProfileIds: ["p2"] });
  stored.profiles = stored.profiles.filter((profile) => profile.id !== "p2");
  const staleTv = androidPayload({ activeProfileId: "p2" });
  const result = merge(stored, staleTv);
  assert.deepEqual(result.profiles.map((profile) => profile.id), ["p1"]);
  assert.deepEqual(result.deletedProfileIds, ["p2"]);
  assert.equal(result.activeProfileId, "p1");
  assert.equal(result.watchlistByProfile.p2, undefined);
});

test("a profile added on Android after the web's read keeps its profile and per-profile data", () => {
  const read = androidPayload();
  const stored = clone(read);
  stored.profiles.push({ id: "p3", name: "Guest", avatarId: 7, createdAt: 900, updatedAt: 0 });
  stored.profileSettingsById.p3 = androidProfileSettings({ clockFormat: "12h" });
  stored.localContinueWatchingByProfile.p3 = [cwItem(77, 900)];
  stored.watchlistByProfile.p3 = [{ tmdbId: 5, mediaType: "movie", title: "Five", addedAt: 10 }];
  stored.catalogsByProfile.p3 = [{ id: "kids" }];
  const web = clone(read);
  web.accentColor = "Blue";
  const result = merge(stored, web, 800);
  assert.deepEqual(result.profiles.map((profile) => profile.id), ["p1", "p2", "p3"]);
  assert.equal(result.profileSettingsById.p3.clockFormat, "12h");
  assert.equal(result.localContinueWatchingByProfile.p3[0].id, 77);
  assert.equal(result.watchlistByProfile.p3[0].tmdbId, 5);
  assert.deepEqual(result.catalogsByProfile.p3, [{ id: "kids" }]);
});

test("profile copies merge by updatedAt and keep the newest lastUsedAt", () => {
  const stored = androidPayload();
  stored.profiles[0] = { ...stored.profiles[0], name: "Renamed on TV", updatedAt: 500, lastUsedAt: 10 };
  const web = androidPayload();
  web.profiles[0] = { ...web.profiles[0], name: "Old name", updatedAt: 100, lastUsedAt: 900 };
  const [profile] = mergeProfileList(stored, web).profiles;
  assert.equal(profile.name, "Renamed on TV");
  assert.equal(profile.lastUsedAt, 900);
});

test("a fresh install's placeholder profile is not added to an account with profiles", () => {
  const stored = androidPayload();
  const freshInstall = androidPayload({
    profiles: [{ id: "fresh", name: "Profile 1", avatarId: 0, isKidsProfile: false, isLocked: false, pin: null }],
    activeProfileId: "fresh"
  });
  const result = mergeProfileList(stored, freshInstall);
  assert.deepEqual(result.profiles.map((profile) => profile.id), ["p1", "p2"]);
  assert.equal(result.activeProfileId, "p1");
});

test("Continue Watching keeps a row Android saved after the web read the document", () => {
  const read = androidPayload();
  const stored = clone(read);
  // Android saves progress for a new title at 2_000, after the web read at 1_500.
  stored.localContinueWatchingByProfile.p1 = [cwItem(20, 2_000), cwItem(10, 500)];
  const web = clone(read);
  web.updatedAt = 3_000;
  const result = merge(stored, web, 1_500);
  assert.deepEqual(result.localContinueWatchingByProfile.p1.map((item) => item.id), [20, 10]);
});

test("Continue Watching honours a removal made by the pushing device", () => {
  const stored = androidPayload();
  stored.localContinueWatchingByProfile.p1 = [cwItem(20, 900), cwItem(10, 500)];
  // Android finished title 20 and rebuilt its snapshot at 1_000 without it.
  const android = androidPayload({ updatedAt: 1_000 });
  android.localContinueWatchingByProfile.p1 = [cwItem(10, 500)];
  const result = merge(stored, android);
  assert.deepEqual(result.localContinueWatchingByProfile.p1.map((item) => item.id), [10]);
});

test("Continue Watching keeps the newest row per show and one row per show", () => {
  const stored = androidPayload();
  stored.localContinueWatchingByProfile.p1 = [
    cwItem(30, 900, { mediaType: "TV", season: 1, episode: 4, streamKey: "android-key" })
  ];
  const web = androidPayload({ updatedAt: 950 });
  web.localContinueWatchingByProfile.p1 = [cwItem(30, 400, { mediaType: "tv", season: 1, episode: 2 })];
  const rows = merge(stored, web, 800).localContinueWatchingByProfile.p1;
  assert.equal(rows.length, 1);
  assert.equal(rows[0].episode, 4);
  assert.equal(rows[0].streamKey, "android-key");
});

test("Continue Watching merges do not touch other profiles' rows or recreate deleted profiles", () => {
  const stored = androidPayload({ deletedProfileIds: [] });
  stored.localContinueWatchingByProfile.p2 = [cwItem(40, 5_000)];
  const incoming = androidPayload({ updatedAt: 1_000, deletedProfileIds: ["p2"] });
  incoming.profiles = incoming.profiles.filter((profile) => profile.id !== "p2");
  delete incoming.localContinueWatchingByProfile.p2;
  const result = merge(stored, incoming);
  assert.equal(result.localContinueWatchingByProfile.p2, undefined);
});

test("dismissals union per key with the newest time", () => {
  const stored = androidPayload({ dismissedContinueWatchingByProfile: { p1: "movie:1,100|tv:2,300" } });
  const incoming = androidPayload({ dismissedContinueWatchingByProfile: { p1: "tv:2,200|movie:3,50" } });
  const result = mergeContinueWatchingDismissals(stored, incoming);
  const entries = new Map(result.dismissedContinueWatchingByProfile.p1.split("|").map((entry) => entry.split(",")));
  assert.deepEqual(Object.fromEntries(entries), { "tv:2": "300", "movie:3": "50", "movie:1": "100" });
});

test("helpers leave non-object shapes alone instead of guessing", () => {
  const stored = androidPayload({ localContinueWatchingByProfile: "not-an-object" });
  const incoming = androidPayload();
  assert.equal(mergeContinueWatching(stored, incoming, 1).localContinueWatchingByProfile, incoming.localContinueWatchingByProfile);
  const strange = androidPayload({ profileSettingsById: "[]" });
  assert.equal(mergeTimestampedSettings(stored, strange).profileSettingsById, "[]");
});

test("the merge handles a large real-world payload quickly", () => {
  const profiles = Array.from({ length: 8 }, (_, index) => ({ id: `p${index}`, name: `Profile ${index + 2}`, avatarId: index, updatedAt: index }));
  const big = androidPayload({ profiles, activeProfileId: "p0" });
  big.profileAvatarImagesById = Object.fromEntries(profiles.map((profile) => [profile.id, "A".repeat(400_000)]));
  for (const { id } of profiles) {
    big.profileSettingsById[id] = androidProfileSettings();
    big.localContinueWatchingByProfile[id] = Array.from({ length: 50 }, (_, item) => cwItem(item + 1, 1_000 + item));
    big.watchlistByProfile[id] = Array.from({ length: 1500 }, (_, item) => ({ tmdbId: item + 1, mediaType: "movie", title: `W ${item}`, addedAt: item }));
    big.catalogsByProfile[id] = Array.from({ length: 60 }, (_, item) => ({ id: `catalog-${item}` }));
    for (const field of Object.keys(androidProfileSettings())) big.fieldUpdatedAt[`p:${id}:${field}`] = 100;
  }
  const incoming = clone(big);
  incoming.profileSettingsById.p0.autoPlayNext = false;
  incoming.fieldUpdatedAt["p:p0:autoPlayNext"] = 200;
  const started = process.hrtime.bigint();
  const result = merge(big, incoming, 1_000);
  const elapsedMs = Number(process.hrtime.bigint() - started) / 1e6;
  assert.equal(result.profileSettingsById.p0.autoPlayNext, false);
  assert.equal(result.localContinueWatchingByProfile.p7.length, 50);
  assert.ok(elapsedMs < 500, `merge took ${elapsedMs.toFixed(1)}ms`);
});

test("the push endpoint applies the field merges and reads baseUpdatedAtMs from the request", async () => {
  const fs = require("node:fs");
  const vm = require("node:vm");
  const read = androidPayload({ fieldUpdatedAt: { "p:p1:autoPlayNext": 100 } });
  let snapshot = { payload: clone(read) };
  // Android writes between the web's read (1_500) and its push.
  snapshot.payload.profileSettingsById.p1.autoPlayNext = false;
  snapshot.payload.fieldUpdatedAt["p:p1:autoPlayNext"] = 2_000;
  snapshot.payload.localContinueWatchingByProfile.p1.unshift(cwItem(20, 2_000));
  const exports = {};
  vm.runInNewContext(fs.readFileSync(require.resolve("../netlify/functions/account-sync-push"), "utf8"), {
    exports, console,
    require: (name) => name === "./_watchlist-sync" ? require("../netlify/functions/_watchlist-sync")
      : name === "./_sync-merge" ? require("../netlify/functions/_sync-merge") : {
        json: (statusCode, body) => ({ statusCode, body }), options: () => null, parseBody: (event) => event.body,
        resolveIdentity: async () => ({ supabaseUserId: "user-1" }),
        applyAddonWipeGuard: (_existing, incoming) => ({ payload: incoming, guarded: false }),
        payloadMetrics: (value) => ({ payload: value }), isExistingSnapshotRicher: () => false,
        loadSnapshotForUpdate: async () => ({ snapshot, etag: "1" }),
        saveSnapshotToBlobs: async (_event, _identity, next) => { snapshot = next; return next; },
        appendSnapshotEvent: async () => undefined
      }
  });
  const web = clone(read);
  web.updatedAt = 3_000;
  web.accentColor = "Blue";
  const result = await exports.handler({ httpMethod: "POST", body: { payload: web, baseUpdatedAtMs: 1_500 } });
  assert.equal(result.body.accepted, true);
  assert.equal(snapshot.payload.accentColor, "Blue");
  assert.equal(snapshot.payload.profileSettingsById.p1.autoPlayNext, false);
  assert.deepEqual(snapshot.payload.localContinueWatchingByProfile.p1.map((item) => item.id), [20, 10]);
});

test("a profile an older Android build deleted stays deleted", () => {
  const stored = androidPayload();
  stored.profiles = stored.profiles.map((profile) => ({ ...profile, createdAt: 100 }));
  // Android 2.2 removes p2 and pushes its own list, without deletedProfileIds.
  const android = androidPayload({ updatedAt: 5_000 });
  android.profiles = android.profiles.filter((profile) => profile.id !== "p2");
  const result = merge(stored, android);
  assert.deepEqual(result.profiles.map((profile) => profile.id), ["p1"]);
});

test("deleting the last profile and adding a new 'Profile 1' keeps the new one", () => {
  const stored = androidPayload();
  const web = androidPayload({ updatedAt: 5_000 });
  web.profiles = [{ id: "new", name: "Profile 1", avatarId: 0, isKidsProfile: false, isLocked: false, pin: null }];
  web.deletedProfileIds = stored.profiles.map((profile) => profile.id);
  const result = mergeProfileList(stored, web, 4_000);
  assert.deepEqual(result.profiles.map((profile) => profile.id), ["new"]);
  assert.equal(result.activeProfileId, "new");
});

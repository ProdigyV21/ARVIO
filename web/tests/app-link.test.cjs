const test = require("node:test");
const assert = require("node:assert/strict");
const { load } = require("./load.cjs");

const { arvioAppLink } = load("lib/appLink.ts");

test("builds the Android intent link for movies and episodes", () => {
  assert.equal(
    arvioAppLink({ id: 603, mediaType: "movie" }),
    "intent://open?type=movie&id=603#Intent;scheme=arvio;package=com.arvio.tv;S.browser_fallback_url=https%3A%2F%2Farvio.tv%2F;end"
  );
  assert.match(arvioAppLink({ id: 1399, mediaType: "tv" }, { season: 2, episode: 5 }), /^intent:\/\/open\?type=tv&id=1399&season=2&episode=5#Intent;/);
  assert.match(arvioAppLink({ id: 603, mediaType: "movie" }, { season: 2, episode: 5 }), /type=movie&id=603#/);
});

test("refuses items without a TMDB id", () => {
  assert.equal(arvioAppLink({ id: 0, mediaType: "movie" }), null);
  assert.equal(arvioAppLink({ id: -4, mediaType: "tv" }), null);
});

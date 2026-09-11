# Sports event artwork: implementation decision

Research checked on 2026-09-10. The Premium adapter is now implemented on the
overhaul branch, with isolated deployment testing. It is not yet enabled in the
production application. No third-party code, API keys or downloaded artwork is
committed to this PR.

## Why the current cards repeat

ARVIO currently uses programme artwork from the playlist, then conservatively
matches artwork from installed sports addons. When neither provides a match,
it displays a bundled sport photograph. The last cached-provider audit found no
safe matches among the 20 addon artwork entries. A different stock photograph
does not solve missing event identity or coverage.

## Useful existing projects

- [Jellyfin.Plugin.TheSportsDB](https://github.com/retrorat1/Jellyfin.Plugin.TheSportsDB)
  is MIT-licensed. Its episode provider resolves sports events and fetches the
  matched event's thumbnail and fanart. It handles team pairs and non-team events.
  This is a useful reference for an ARVIO metadata adapter, not an Android plugin
  we can load directly.
- [Kodi's TheSportsDB scraper](https://www.thesportsdb.com/docs_kodi_scraper)
  is another metadata reference, particularly for motorsport and UFC. It targets
  sports recordings rather than live IPTV guide matching; it is not a complete
  live-schedule solution.
- The sports-centre reference implementation reviewed for this overhaul uses a
  backend fixture endpoint and team/league badges. It does not provide a reusable
  public banner feed. Do not call another application's private backend or reuse
  its credentials.

## Recommended artwork order

1. Verified artwork for the exact event, preferring a landscape thumbnail/fanart.
2. A locally rendered matchup card with both verified, unmodified team crests,
   names and competition identity. Use a neutral background, not a guessed venue.
3. Verified competition/session artwork for non-team events, including the actual
   race, qualifying session or fight card when identified.
4. The existing honest sport fallback when metadata remains ambiguous.

[TheSportsDB artwork documentation](https://www.thesportsdb.com/docs_artwork)
lists 1280x720 event thumbnails/fanart and transparent team/league logo assets.
Not every event has every asset. Do not promise official, 4K, or universal coverage.

## Matching and performance requirements

- Match sport, competition, participants and UTC date/start time. Preserve women's,
  youth, replay and qualifying distinctions. Account for renamed/localized teams
  through verified aliases, not unrestricted fuzzy matching.
- A generic programme such as "UEFA Champions League" does not identify a match.
  Do not invent teams or treat a metadata broadcast listing as proof that an IPTV
  channel is showing an event. Retain the programme-time/channel availability checks.
- Keep the guide usable before enrichment. Use cached fixtures and artwork,
  bounded background batches, negative caching and backoff. Never request metadata
  per focus movement, nor probe video streams to find artwork.
- Cache shared fixture metadata through the existing backend; do not expose the
  production API key in clients. Store no playlist credentials in metadata queries.
  Size image decodes for actual cards, not source-image resolution.
- Android and web must share identity rules and fixtures. Verify against the real
  cached playlist: report exact-match coverage separately from fallbacks, and test
  timezones, daylight saving, ambiguous titles, offline mode and missing artwork.

## Production prerequisite

[TheSportsDB terms](https://www.thesportsdb.com/docs_terms_of_use.php) require a paid
subscription for publishing an app-store application, attribution, compliance
with rate limits, and lawful use of third-party content. A subscription is not a
blanket rights grant for every sports trademark or third-party image.

[Published pricing](https://www.thesportsdb.com/pricing) currently lists $9/month
for Single Developer (100 requests/minute) and $20/month for Small Business
(120 requests/minute). Confirm the appropriate plan and artwork permissions for
ARVIO's distributed APK and paid web service before enabling production access.
The owner has now supplied a Premium subscription. Its private key is configured
only in the backend function environment, never in the APK, web bundle or Git.

## Implemented adapter

- `sports-metadata.mjs` serves a fixed, sanitized fixture/artwork feed; it cannot
  proxy arbitrary API queries, disclose a key, or accept playlist credentials.
- Four V1 `eventsday.php` requests cover UTC yesterday through the day after
  tomorrow. Shared refresh is every 30 minutes, with a five-minute failure backoff
  and up to 24 hours of stale artwork. Reaching the provider's 1500/day limit marks
  the response partial rather than silently claiming complete coverage.
- A strongly consistent Netlify Blobs compare-and-set lease prevents concurrent
  clients/functions from stampeding the API. Cache failure does NOT bypass the
  limiter. The modern Functions API is required: the legacy `connectLambda`
  bridge loses the uncached endpoint needed for strong consistency in this SDK.
  See [Netlify's consistency documentation](https://docs.netlify.com/build/data-and-storage/netlify-blobs/#consistency).
- Four bounded V2 TV-day requests add broadcaster hints. A separate strongly
  cached V2 livescore request refreshes every two minutes. A complete cold refresh
  costs nine upstream calls normally, or at most seventeen when truncated TV-day
  results need eight fixed regional supplements (three concurrent at most).
  This work is globally cached, not repeated per user. `broadcastsPartial` remains
  true because regional supplements cannot guarantee worldwide completeness.
  No stream is opened for matching.
- Android and web cache the public feed for two minutes and persist public
  metadata for a maximum 24-hour stale fallback. Addon artwork remains supported.
  Fixtures can exist without artwork, but the Sports UI only displays events
  matched to the user's accessible channels. There is no availability toggle.
- Normalized match titles (including provider competition suffixes), or both
  complete participant names, sport and a two-hour start tolerance are required.
  Generic league titles and differing youth/women's fixtures cannot borrow artwork.
  Region-preserving channel keys handle UK-NOWTV decorations and TNT Sport/Sports.
- Event thumbnail/fanart is preferred. When both verified team crests exist,
  clients compose an uncropped matchup card on a neutral background. Generic sport
  photography is not used. Events without match artwork or paired crests are not
  shown; failed banners fall back to verified crests, otherwise the card is removed.
  Channel category or a sport mentioned in programme descriptions is not sufficient
  evidence of a sports event. Times use the device timezone
  and the profile's 12/24-hour preference. Attribution lives in About & Credits,
  not in the Sports corner.
- Fixture IDs remain stable across refreshes. Finished/cancelled fixtures suppress
  stale EPG matches, and provider live status/scores expire after five minutes.
  No arbitrary event duration is invented. Broadcaster-name matches are separate
  possible sources, labelled "Possible broadcast" rather than "Guide match".
- "Featured live" ranks major competitions and broadcast-country reach. It is
  not measured viewership: SportsDB does not document a viewer/trending metric.
  Upcoming stays chronological. More on air and ten per-sport rows follow.
  Scores are opt-in within the picker; team/competition follows are excluded.
- Catalogue matching runs off the UI thread (Android Default dispatcher, web
  worker). Native lists are lazy; web rows render an initial bounded slice and
  expand during navigation. Focused-row ordering survives background refreshes.

## Deployment and verification

Deploy the backend alongside the approved PR before distributing clients. Normal
clients resolve `/sports-metadata` under the configured auth backend. For isolated
tests, Android `SPORTS_METADATA_URL` and web `NEXT_PUBLIC_SPORTS_METADATA_URL` accept
a **public endpoint URL**, never an upstream key. Self-hosted web installations
can supply their own endpoint; otherwise their existing addon art still works.

- Backend: `node --test tests/sports-metadata.test.js` (from `netlify-auth-site`).
- Web: `node --test tests/sports-artwork.test.cjs tests/sports-guide.test.cjs tests/sports-catalogue.test.cjs` and
  `npx tsc --noEmit --incremental false`.
- Android: `SportsMetadataTest`, `SportsGuideTest`, `SportsArtworkTest`, `SportsCatalogueTest`;
  `TvOverhaulDeviceTest` covers guide/drawer/picker and image failures.
- Opt-in live art rendering: `SportsMetadataDeviceTest`, with instrumentation
  argument `sportsMetadataUrl`; `web/tests/sportsdb-browser.cjs` with environment
  variable `SPORTS_METADATA_URL`. These use real event art with **fixture channels**,
  not evidence that a provider broadcasts those matches.
- Real cached EPG matching: `netlify-auth-site/scripts/audit-sports-artwork.cjs`,
  with `SPORTS_METADATA_URL` and local `EPG_AUDIT_DB`. Read-only: no playback probes,
  user credentials or source URLs are read/output.

2026-09-10 pre-fix catalogue preview audit: the four-day feed returned 2488 events, 883
banners and 2380 badge pairs. Only four of 791 cached EPG sports candidates matched
(three distinct matchups). This is not
full-guide coverage: the old generic-title fallback has since been removed, and
the cache's date window also limits this snapshot. Do not use rendering fixtures as proof that all
provider events are enriched. Browser checks cover both artwork modes, picker
opening/closing at 1672/768/390px, and hiding unmatched events. The host-JS index
benchmark covers 50k channels and 2k fixtures; it is not an Android frame benchmark.
Production is not enabled by this PR's isolated preview deployment.

The artwork follow-up passed 28 Android unit tests, 28 web unit tests and six
backend tests. Web typechecking and real-art browser checks passed at desktop,
tablet and phone widths; Android instrumentation tests compile but were not run
in this pass. On the TCL TV with 108,145 stored channels, real SportsDB matchup
banners appeared for Fenerbahce-Roma, PSV-Shakhtar and Bayern-Bodo/Glimt.
The supplemented feed increased events with broadcast hints from 163 to 240 in
the observed response; this is still partial coverage, not a complete global feed.
Only events with artwork and matching owned channels are shown. No live match
is invented when the eligible results are upcoming only.
The final release-signed build was installed as an update on the TCL. Remote
Right transferred focus from the drawer to the first upcoming card; Select opened
the Fenerbahce-Roma picker with 29 scheduled channel entries from the stored
playlists, and Back returned to Sports. A live North Korea U20 Women-Costa Rica
fixture subsequently appeared with its own matchup artwork. No upcoming stream
was played to claim broadcast verification. Cold first results still took roughly
a minute or more on this large list; this is not an instant-load performance claim.

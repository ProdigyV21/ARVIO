# Sports coverage and APK logos

## Fixes

- Android resolves the bundled, exact-identity channel logo directory even when a provider image returns HTTP 200. Directory images precede provider tiles, with normal failure fallback. Quality/package prefixes now match web behavior without dropping station numbers, country or time shifts.
- Shared backend uses premium `eventstv.php` (1500 listings/day), not the V2 day filter (100). A failed day no longer skips later days. Regional supplements remain bounded and conditional; the shared cache/lease and backoff are unchanged.
- Both parsers retain up to 1500 broadcasters per fixture instead of 100. Android no longer discards matching local channels after the first 5000.
- Matching handles more regional prefixes, resolution/frame-rate decorations, joined station numbers and explicitly enumerated team aliases. Short complete participant names such as PSV work. Both participants, sport, time, competition and qualifiers remain checked; no fuzzy one-team matching or stream probing.
- Additional sports include motorsport, rugby, golf, snooker, darts, Australian football, cycling, athletics, volleyball and handball. Formula 1 and football codes remain distinct.
- Matched events without usable artwork appear in compact per-sport schedule rows. Real event banners/crests remain preferred; no generic posters. Failed artwork does not remove an event's channel picker.

## Verification, 10 September 2026

- Android: 31 focused JVM tests passed; Sideload Debug Kotlin/Compose compilation passed.
- Web: 573 tests passed; `tsc --noEmit` passed.
- Backend: 77 tests passed, including concurrent cache requests, truncation recovery and partial upstream failure.
- Local browser: real metadata with synthetic guide/channel fixtures; verified event artwork, extra sport rows, missing-artwork event selection and an enabled channel action. Checked desktop and 390 x 844 mobile layout; no page-width overflow. These UI fixtures are not real playback tests.
- Read-only live audit against the supplied Flixnest playlist: 54,511 channels, one channel-list request, no stream requests. Comparing current and revised fixture feeds using the revised client matcher: 651 -> 1918 broadcaster rows; 242 -> 317 fixtures with broadcasters; 39 -> 49 matched fixtures; 310 -> 516 channel candidates. The backend fixture refresh used eight shared upstream requests. These are schedule candidates, not verified playable streams or a complete worldwide coverage claim.
- Web synthetic indexing test: 50,000 channels and 2,000 fixtures completed in approximately 161 ms on this PC. Not a TV frame-time measurement.
- No new APK was installed on the physical TV during this task. Android source changes require a new APK update.

SportsDB does not guarantee that every event, broadcaster, live status or image exists. Guide matches and possible broadcast candidates remain separate. Account/provider settings were not changed.

API limits: https://www.thesportsdb.com/docs_api_guide

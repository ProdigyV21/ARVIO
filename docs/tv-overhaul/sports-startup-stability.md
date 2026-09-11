# Sports startup and stability, 10 September 2026

## Changes

- Prepare metadata and the local sports schedule while the TV guide is open, not only after selecting Sports.
- Retain one ViewModel schedule snapshot, isolated by profile, provider, excluded groups, source version, EPG refresh state and time window.
- Publish a completed EPG scan instead of repeatedly copying and rebuilding the catalogue every two seconds. Load full channel records only after identifying relevant programmes.
- Build broadcaster keys and presentation rows off the UI thread. Reuse broadcaster matches and deduplicate repeated broadcast listings without dropping provider channel variants.
- Let disk/memory cache reads proceed while a network refresh is pending. Bound metadata reads even when Content-Length is unknown.
- Keep failed-artwork cards in their original rows rather than moving a focused item during layout. Capture the picker selection immutably, deduplicate channel keys, and handle refresh failures without terminating the screen.

## Verification

- 67 selected Android regression tests passed, including sports, EPG index, metadata cache and the preceding channel-startup fixes.
- Synthetic catalogue: 500 fixtures, 100,000 broadcast entries, 40 provider channel variants. All fixtures and variants retained; final matching run took 90 ms on the development PC. This is not an end-to-end TV load measurement.
- Android TV emulator (API 31, 1.5 GB RAM): SportsRefreshDeviceTest passed. It renders a 500-event schedule, opens the picker, removes the selected event during refresh, dismisses the picker and restores the reversed schedule.
- Initial UI test failures came from asserting the obsolete heading "Available channels"; the actual picker heading is "Channels". Screenshot inspection confirmed the picker was visible. The corrected test checks its close control and actual channel.
- Public metadata endpoint returned HTTP 200, 2,496 events and 1,576,914 bytes in 3,721 ms. A cold network fetch is not instant; cached/prepared reopening avoids waiting for that fetch.
- Debug APK and instrumentation APK built successfully. No physical TV installation or playback interruption, production deployment or main-branch push was performed.

## Sentry boundaries

- Inspected recent Android reports. ANDROID-QK (145254490), on 1.9.996+312, allocates 128 MB while decompressing JSON. Current code already uses streaming gzip handling; this older report must not be attributed to the new sports loader. Metadata now also enforces its own response-size bound.
- Recent detached-layout and NaN layout reports do not establish a specific Sports call site in the retrieved evidence. This change guards concrete picker/refresh failure paths but does not claim those Sentry issues are conclusively resolved.
- Real-provider EPG scan timing and physical TCL post-update behaviour remain to be measured. No live streams were opened or provider request bursts generated for these tests.

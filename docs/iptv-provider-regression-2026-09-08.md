# IPTV provider regression audit

## Scope and causes

- A restored playback anchor was also used as a SQL paging offset. This could
  expose only the tail of a group or no rows when the anchor belonged elsewhere.
  Category paging now starts at zero and playback restoration stays independent.
- IPTV preferences were not included in field-level cloud conflict resolution.
  A stale pull could restore a removed playlist; unchanged syncs also discarded
  the channel index. Local edits now atomically stamp their values, including
  empty lists. Android, web and the backend preserve newer field values.
- Response decompression created several complete byte-array copies. JSON gzip
  decoding is now streamed, with bounded nested compression handling.
- The supplied provider declares HLS but the importer constructed TS URLs.
  The declared format is preserved. Live playback no longer overrides every
  provider's timing with an eight-second live-edge offset.
- Large-list guide matching missed country-preserving LQ/HD/FHD variants when
  numeric API EPG IDs did not match XMLTV IDs. Those variants now match without
  merging the channels or crossing provider boundaries.
- XMLTV duplicated programmes for every quality variant. The index now stores
  shared schedules with channel aliases in bounded batches. Existing history is
  retained; old database files do not immediately shrink after this migration.
- Short refreshes no longer erase archive entries. Archive reads can retain up
  to seven days, subject to the provider actually supplying that history.
- Full-guide completion is tracked separately from partial guide updates.
  HTTP 304 reads remain bounded on large lists; alternative feed timeouts do not
  trigger a sequence of repeated large imports.

## Test environment

Android TV API 31 emulator, 2 GB RAM, isolated `.iptvaudit` debug application.
No physical TV, production account, production installation or signing key was
changed. Credentials are deliberately omitted from this document.

The supplied provider returned 54,502 channels in 833 groups. Its short guide API
returned empty results for the two Dutch test channels. Its XMLTV feed was about
99 MB and contained approximately yesterday through tomorrow, not three days of
archive on a fresh installation.

## Observations

- Forced channel refresh: 29,517-45,004 ms total over the final two provider runs,
  first channel callback at 17,712-24,545 ms.
  All 54,502 rows persisted; only 240 startup rows were materialized in memory.
- Ten unchanged sync applies and one favourites-only apply retained all rows.
- Full XMLTV pass into the existing large cache: 193,521-201,320 ms; both Dutch test
  channels matched. This is a functional pass, not an instant-import pass.
- Separate-process cached repository startup: 2,028 ms for all channel/group
  counts and 2,209 ms including both Dutch channels' EPG. No provider download.
  This measures repository readiness, not complete app launch or first paint.
- The actual TV page displayed 54,502 channels, and the favourites view visibly
  displayed both Dutch channels with EPG blocks while the mini-player played.
- Latest playback-policy run: first video frame at 9,159 ms, 882 frames over the
  following 40 seconds, zero network rebuffers, longest frame gap 3,319 ms,
  1280x720 video, no container or playback error. The frame gap means this is
  NOT a stutter-free acceptance pass.
- Separate desktop stream check: 720 video packets over 29.988 seconds, maximum
  sorted PTS gap 41.712 ms, no demux warnings. This was a different time window;
  it does not establish that every stream or decoder is free of stalls.
- Fifteen navigation/matching/storage/rendering device tests passed, including two
  providers with 50,000 synthetic rows, all group pages, reverse scrolling,
  favourites ordering, hidden categories, and 288 retained archive programmes.
- Renderer-only benchmark, debug emulator, 55,000 logical channels and a bounded
  144-row window: 1,275 frames, median 34.13 ms, P95 78.67 ms, P99 102.37 ms,
  40.24% missed deadlines, 24 MiB Java heap at the end. The functional traversal
  completed, but this is NOT a smoothness pass or a release-device measurement.
- Backend tests: 69 passed. Web tests: 227 passed. Web type checking passed.
- Final Android unit runs: 866 sideload tests and 867 Play tests, zero failures
  or errors, one skipped per flavor (1,731 executed tests passed).

## Release build and installation follow-up

- Combined these changes and the preceding stability/search/library fixes with
  current GitHub main in commit `2fd626559`. Pushed that commit to main.
- The merged web run passed 508 tests and type checking. Backend passed 69 tests.
  Android passed 870 executed sideload tests and 871 executed Play tests, with
  one skipped test per flavor and no failures or errors.
- The normal ARM sideload release build completed successfully, including R8
  and release lint. No audit init script or debug application suffix was used.
- Update-installed `com.arvio.tv` version 1.9.996 (312), targeting API 36, on
  the physical TV on September 8 at 21:55 local time. No uninstall or data clear.
  Its original installation date of August 14 was preserved.
- The APK's signing certificate exactly matched the previously installed app:
  SHA-256 `9778d7533d4bc1aee80c1d2d7043fb22cba3b79cd11911d3b131c1316d5f17c1`.
  The installed APK hash matched the build output:
  `49007f8cb2d13e94bb4245af042f5bbce45ea120bd1f0ed1079f67742d92075c`.
- The new process opened with the existing profile and addons. A subsequent
  screenshot showed video playback. No fatal exception, OOM or SQLite exception
  appeared in the captured new-process log. Playback was left undisturbed;
  post-update physical-TV guide navigation and timings were not re-measured.
- GitHub's `Build and deploy web.arvio.tv` check completed successfully for the
  merged commit. Backend production deployment was not independently verified.

## Remaining validation limits

No GitHub Release was published. Live cloud synchronization across two
authenticated devices has not been exercised. Catch-up history retention was
tested; actual catch-up stream playback was not verified in this audit. Trailer
opening in the official YouTube player is an existing intentional behavior,
not reversed here.

Fresh import speed and complete playback smoothness remain below the requested
standard. Cached reopening must be measured separately from network imports;
unit-test success must not be presented as proof of real-device smoothness.

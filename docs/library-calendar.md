# Library release calendar

Library has a Calendar tab on Android TV, phones/tablets and the web app. It uses a
borderless month grid with artwork in release days, a white selected day, and a
horizontal release strip beneath the month. Existing Library tabs remain available.

## Watchlists and dates

- ARVIO's own local/cloud watchlist works without a connected tracking account.
- Connected Trakt, SIMKL and MDBList watchlists can be combined or filtered by service.
  SIMKL also includes titles currently being watched.
- Matching titles are deduplicated by media type and TMDB identity, retaining every
  contributing service as provenance. Reading the calendar does not modify lists.
- TMDB supplies movie releases and episode dates/stills. Public Trakt episode
  metadata supplies confirmed air timestamps when available; no Trakt user login is
  needed for that metadata.
- Confirmed timestamps are converted to the device's timezone. Date-only releases
  retain their published date and display **Time TBA**. Movie release regions are
  labelled, including a US fallback when no release is listed for the chosen region.
- Missing metadata is reported as a partial result with a retry action. An artwork
  failure must not discard a confirmed release date.

## Interaction and responsiveness

The month starts on Monday. Previous/next month controls work across year
boundaries and keep the selected day where possible. On TV, left/right
moves one day, up/down moves a week, and OK enters the selected day's release strip.
OK on a release opens its details. Up returns from that strip to the selected day.
Remote day navigation stays inside the displayed month; adjacent-month cells are
not remote focus targets. Use the month controls to change months. Up from the
first week enters those controls, and Down returns to the selected date, rather
than relying on geometric focus search across spillover days.

Phone layouts retain the month artwork and expose the larger selected-day cards
below it. Short landscape windows scroll vertically instead of clipping the month
or captions. The web grid supports arrows, Home/End and Page Up/Down, with a single
tab stop for the selected day.

Today has a marker independent of the selected date. An empty day offers **Next
release** when a later loaded release exists in the visible month and chosen source;
the calendar never changes the selected day just because data arrives. On TV, OK on
an empty day focuses this action (or Retry on a failed load), and Up returns to the
day. Dense six-week months retain their extra-release counts.

The Calendar uses the same full-size shared top bar as every other TV page. The
toolbar only contains month navigation, the watchlist filter and connected-service
branding; it has no Today button, timezone caption, refresh button or navigation
instruction footer. Retry remains available when a source fails. Phone date cells prioritize artwork
and release counts; complete titles and times remain in the selected-day strip.
Calendar uses the same Library tab typography, shape, padding and vertical position
as Watchlists, My lists and Homeserver. The tab row has a stable minimum height,
including when other sections expose additional controls. Normal TV windows keep
the month toolbar and release strip in view while navigating dates; only compact
or genuinely short windows scroll the entire month surface.
The TV Library tabs start at 74 dp, just below the visible topbar controls, rather
than reserving the full 98 dp gradient area as empty space. This tighter spacing
is shared by all four Library sections; topbar bounds and touch spacing stay unchanged.
Dates with multiple releases show up to three portrait posters and a `+N` count
for further releases, both focused and unfocused. Single-release cells retain
their title and time. The wide web layout follows the same poster-strip design.
Touch toolbar controls have 44 dp minimum targets. Loading states do not imply an
empty watchlist while sources are still being read.

### Service artwork

Calendar bundles official assets without recoloring: the purple Trakt favicon from
`https://app.trakt.tv/favicon.svg`, the MDBList homepage logo from
`https://mdblist.com/static/android-chrome-512x512.png`, and the white SIMKL wordmark
used by `https://docs.simkl.org/how-to-use-simkl`. The Trakt SVG paths are preserved
in an Android vector; PNG artwork retains its original colors and aspect ratio.

## Loading and isolation

Metadata requests use bounded concurrency and expiring caches. Android keeps public
metadata for 30 minutes in memory/on disk and restores a private month preview for
up to 24 hours, scoped to profile, connection identity, local watchlist membership,
language, region, month and timezone. Encoding, parsing and file access run off the
UI thread. The production Library route preloads the current month before the
Calendar tab opens. The web client also restores scoped month snapshots before
refreshing. Successfully removed source memberships are reconciled into the saved
preview even when an unrelated service fails; retained fallback data keeps its
original expiry. New, uncached remote data still requires a network response.

Completed titles appear progressively, before slower sources and optional logos finish.
Both loaders start titles as each watchlist arrives and publish confirmed episode
dates before optional air-time enrichment. Separate request limits prevent a
stalled tracker from hiding available ARVIO dates or blocking healthy artwork.
Optional time enrichment has a bounded budget including queue time. The web
coalesces progress updates while publishing the first available release promptly;
pending, empty and failed sources remain distinct. On Android, a private source
snapshot becomes reusable only after all source reads finish.
Historical season requests have their own bounded slots and start with recent
seasons, allowing newly arriving titles to publish dates without waiting for a
complete season walk. Metadata for an identical request is coalesced; no titles or
potentially relevant seasons are silently omitted.
Android batches up to 20 missing seasons per TMDB request using
`append_to_response`, preserving the normal per-season cache keys. Specials and
overlapping seasons remain included. Missing or malformed appended blocks retry
independently through the ordinary season endpoint. Batch decoding runs off the UI
thread; the same five-request global limit and two-season-request limit still apply.
Current shows and recent movie releases are prioritized without excluding other
titles. Older movies have a bounded title queue so later-arriving tracker shows do
not wait behind hundreds of pending requests. Confirmed HTTP 404 responses are
remembered for one minute to avoid repeated failed reads on date/month changes;
explicit Retry clears this negative cache. Other network errors are not hidden by it.
Changing month, profile or language cancels/replaces the old request. Private
watchlists are scoped to the current account/profile; public metadata may be cached.
Android refreshes stale lists on tab entry/resume and reacts to cloud watchlist and
connection changes.

## Verification fixtures

`LibraryCalendarDeviceTest` renders the real Android Library shell and Calendar UI
using local artwork and synthetic October 2026 release dates. The web Calendar UI
fixture exercises the production component and loader with offline metadata
adapters at desktop, tablet and phone sizes, including ARVIO-only mode. These fixture
dates are for repeatable navigation and visual tests and are never production data.

Repository/mapping tests cover timezone boundaries, date-only releases, regional
movie types, provider deduplication/failures, profile isolation, caching and bounded
requests. Emulator screenshots are saved under `artifacts/calendar` during QA.

### Validation on 4 October 2026

- Android: 23 repository/mapping tests passed; the x86 sideload debug app and
  instrumentation APK assembled successfully.
- Emulator: all eight TV Calendar scenarios and both phone-layout scenarios
  passed. Phone checks ran at 390 × 780 dp on the same TV-system emulator and
  verified six-week scrolling and caption clearance; its TV display size was
  restored afterward. Two existing top-bar scenarios passed in the initial
  implementation check. No physical TV was used.
- Web: strict TypeScript and 43 focused unit tests passed on the refined version
  (18 Calendar and 25 translation/Library/TV regressions). Calendar, existing
  Library and translation browser checks passed at desktop, tablet and phone sizes.
- Live public TMDB responses parsed with the production models. A public Trakt
  smoke request received an HTML/403 challenge in this environment, so live exact
  times were not verified; the date-only fallback and timestamp mapping are tested.
- Two existing non-Calendar landscape scrolling scenarios hit Compose idle
  timeouts. One reproduced in isolation; those broader checks are not counted as
  passing and their relation to this change has not been established.

### Validation on 5 October 2026

- Android: 36 Calendar repository, mapping and persistent-cache unit tests passed.
  The final x86 sideload debug app and instrumentation APK assembled successfully.
  Regressions cover slow historical seasons, process recreation, cache isolation,
  healthy removals during provider outages, shared-title provenance and navigating
  cached months while a provider is unavailable.
- TV emulator: nine Calendar scenarios, two shared-topbar scenarios and the disk
  cache scenario passed at 1280 x 720. The Calendar topbar retains identical bounds
  when changing Library tabs. Five- and six-week month screenshots keep release
  artwork, captions and provenance visible. The removed controls are absent and
  all three official provider assets are displayed.
- Both phone-layout scenarios passed at 390 x 780 dp on the same TV-system emulator;
  the original TV display was restored. This verifies layout, not a separate phone
  operating-system image. No physical TV was used.
- Web: 26 focused unit tests and TypeScript checks passed. Browser fixtures cover
  TV 720p/1080p, desktop, tablet, phone, six-week months and cached releases while
  metadata is held pending. These use synthetic dates and controlled data adapters.
- Device cache timing measures restoration of 250 saved releases without network
  requests, not first-ever provider loading or complete artwork rendering. New
  remote metadata still requires network responses. Evidence, timing and build
  logs are in `artifacts/calendar-oct5`; web screenshots are in `artifacts/calendar`.

The subsequent spacing/poster refinement passed all nine TV Calendar and two
shared-topbar emulator scenarios. Assertions check unchanged topbar bounds, no
overlap with the closer Calendar tabs, three posters within five-/six-week cells,
and correct overflow. Web browser checks passed at all five existing viewport
sizes, along with TypeScript. Evidence is in `artifacts/calendar-posters-oct5`.

### Validation on 8 October 2026

- Android: all 42 Calendar repository, mapping and persistent-cache unit tests
  passed. Regressions cover season batching, malformed/missing batch blocks,
  specials, unchanged release results, late-arriving shows and confirmed 404s.
- Physical TV: ten Calendar UI scenarios passed on an Android 14 TV at
  1920 x 1080. The two phone-only scenarios were skipped on this landscape device.
  Checks cover identical Library tab bounds, month boundaries, repeated native
  remote keys, toolbar/grid transitions, filtering and opening release details.
- The configured account benchmark checked 459 watchlist titles and found 45
  releases. Cold first release: 3.276 seconds; last newly discovered release:
  21.673 seconds; complete cold scan: 26.291 seconds. Cached first release:
  0.199 seconds; complete cached scan: 2.130 seconds, with the identical release
  ID set. These are repository delivery times, not complete image-render times.
- The account still produced one provider warning and 28 metadata warnings.
  Two sampled unavailable movie records returned confirmed HTTP 404 responses;
  the remaining warnings were not individually diagnosed. Partial results and
  Retry remain visible rather than silently discarding unavailable records.
- The optimized sideload release APK was installed as a signed update with the
  existing certificate and retained profiles/settings. A release-app check
  confirmed both month boundaries after 35 presses in each direction, consistent
  tabs, populated real-account artwork and opening a Calendar title. No playback
  was started during Calendar checks. The full cold scan is not instant.
- Evidence is saved locally in `artifacts/calendar-tv-*.log` and
  `artifacts/calendar-*-release*.png`. This change was tested on Android; the web
  Calendar implementation was not modified or revalidated in this pass.

The subsequent tab-spacing refinement passed the same ten physical-TV scenarios
(two portrait-phone scenarios skipped). Checks now switch between all four Library
sections and require a gap of at most 12 dp below visible topbar controls, with no
overlap when those controls are focused. Topbar bounds and touch spacing are unchanged.

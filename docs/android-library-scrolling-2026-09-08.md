# Android Library scrolling

## Report

Inspected the supplied 14.3-second recording using frames sampled across the
clip and a full-resolution frame around 5.7 seconds. The left Emby library list
stays at its original position as the remote highlight moves toward its bottom.
This is separate from the server-tab startup delay fixed in `56034bc8b`.

## Reproduction and cause

The sidebar draws manual remote focus using an integer index, but its LazyColumn
had no scroll state tied to that focus. Moving past the visible rows therefore
selected off-screen libraries without bringing them into view.

Three emulator regression tests failed against the old behavior:

- Jellyfin: highlighted library 15 did not exist in the visible/composed list.
- Emby: the same failure at library 15.
- Mobile: appending results reset the grid scroll position from 9372 to 0 in
  the test's scroll-axis measurement. A focus-following effect always scrolled
  to the default focused item when the item count changed, even on touch devices.

The grid also aligned each focused item to its top unnecessarily, and Down could
not reach the final item of an incomplete last row from some columns.

## Fix

- Give the sidebar a bounded scroll state that follows remote focus, scrolling
  only enough to reveal a clipped row; fully visible rows remain still.
- Use the same minimal-reveal logic for the title grid, only in TV content focus.
- Leave mobile drag/fling position alone when more results arrive.
- Right from libraries enters loaded titles; Left from the first column returns
  to the selected library. The title position is retained when returning right.
- Allow Down to reach the final incomplete row and guard empty content indices.
- Expose active library selection separately from the remote focus in semantics.
- Keep the existing layout, server discovery and content-loading behavior.

## Verification

Eight emulator UI tests passed, including the two previous server-tab tests.
The six new scroll tests cover 30 libraries per provider across two synthetic
connections, scrolling down and back for Jellyfin and Emby, a 55-key rapid input
burst, 63 titles in landscape and poster layouts, and mobile pagination from
60 to 120 items without a reset. Five new unit tests cover scroll geometry.

Both full unit suites passed with zero failures/errors: 821 sideload tests
(820 passed, one existing skip), 822 Play tests (821 passed, one existing skip).
The sideload debug app and instrumentation APK built successfully. Existing
Robolectric Windows cache-cleanup warnings remain nonfatal.

Verification used the API 31, 2 GB Android TV emulator and synthetic library
data, not the reporter's servers or a physical Shield. Mobile touch behavior was
tested on the emulator viewport. No physical TV or production app was changed.
These checks establish functional scrolling, not a production frame-time/jank
benchmark or network-content loading speed.

Local evidence: `C:/Users/arvin/ARVIO-library-scroll-evidence/` contains the
recording's contact sheet and full frame, plus `library-scroll-jellyfin.png`,
`library-scroll-emby.png`, `library-grid-poster.png`, and
`library-grid-landscape.png`. Baseline failures are recorded in
`C:/Users/arvin/ARVIO-library-scroll-before-device.log`.

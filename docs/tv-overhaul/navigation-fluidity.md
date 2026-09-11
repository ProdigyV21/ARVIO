# TV guide navigation refinement

## Plan and implementation

1. Keep the existing single vertical list and layer-translated drawer. Avoid
   per-frame resizing, focus zoom and additional provider requests.
2. Reveal off-screen rows concurrently with focus attachment retries. Each new
   directional request cancels the previous focus/scroll coroutine together.
3. Preserve the complete programme focus graph, but construct detailed text only
   inside the quantized viewport plus overscan. Off-screen focus nodes retain
   their dimensions, actions and accessible description for navigation.
4. Keep channel/category labels single-line in both focus states; long focused
   labels still marquee. This avoids a one-line/two-line jump on every key press.
5. Animate category and programme outlines in drawing, with fixed geometry.
   Let channel outlines finish their exit animation instead of disappearing.
6. Treat programme end times as exclusive when selecting the nearest vertical
   focus target. Previously the preceding programme tied at zero distance with
   the programme beginning at the anchor, drifting backwards on each row.
7. Read programme focus through a provider inside MiniPlayerRow, so header
   changes do not invalidate the containing guide composition.

## Verification scope

- Build: sideload debug and instrumentation APK, isolated `.overhaul` package.
- 64 guide-related unit tests passed.
- Emulator: Google TV API 31, 1.5 GB RAM, 1280 x 720. No physical TV interaction.
- Navigation fixtures: 55,000 channel records, 144-row render window and dense EPG.
- Added regression: move far right, scroll fourteen programme rows, return to
  the live time and assert the exact focused programme remains displayed.
- That regression initially failed and exposed the boundary-time drift above;
  it was not weakened to accept a different programme.
- Added header test asserting changed programme text without parent recomposition.
- Existing coverage includes rapid keys, pagination, independent category
  positions, favourite reordering/removal, context menus and hidden groups.

These renderer tests do not establish live provider latency, decoder load or
release-build performance on a Shield/TCL. Do not describe them as zero-lag
certification. Network budgets and playback behavior are unchanged by this patch.

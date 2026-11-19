# Home and Settings navigation audit - 2026-11-17

## Environment

- Baseline commit: `6f24daaf1b6fe343e3c873055b721ec1b215bde3`.
- Dedicated Android TV 14 / API 34 x86 emulator, four virtual cores, 2048 MB RAM.
- Actual guest memory: 2,021,092 kB; 192 MB heap growth limit and 512 MB large heap.
- 1920 x 1080, density 320, verified 60 Hz display, host GPU acceleration, WHPX.
- Optimized, minified, non-debuggable `sideloadRelease`, including emulator ABIs.
- Separate local adult profile named `TV Performance`, default catalogs, no personal cloud account.
- Netflix-style Home and trailer autoplay enabled. No feature or animation reductions.
- The physical TV was not connected and was not modified.

This is a constrained TV proxy, not a hardware-equivalent TV. An emulator uses host CPU/GPU
resources and graphics translation. Its absolute frame times and improvements cannot establish
physical-TV performance. The earlier physical-device record is in
`docs/navigation-performance-2026-09-29.md` (Android 14, approximately 2.34 GiB RAM, 60 Hz).

## Baseline Measurements

Macrobenchmark `FrameTimingMetric`, three measured iterations per scenario, plus warmups.
The four scenarios contain 414 measured D-pad presses in total. Setup navigation is outside
the measured block. The remote cadence and animations remain the same between versions.

| Scenario | As-installed median / p95 CPU frame duration | Profile-compiled median / p95 |
| --- | --- | --- |
| Home horizontal | 33.0 / 46.3 ms | 29.9 / 37.8 ms |
| Home vertical | 39.1 / 60.8 ms | 33.1 / 42.6 ms |
| Settings sidebar | 22.8 / 43.7 ms | 21.6 / 39.4 ms |
| Settings playback rows | 21.3 / 29.9 ms | 17.9 / 24.2 ms |

The first Home pilot was slower still: 44.8 ms median / 71.4 ms p95. It is not used as the
comparison baseline. At 60 Hz, the frame interval is approximately 16.7 ms.

`CompilationMode.Ignore()` preserves the install/JIT state. The as-installed package initially
reported `verify` / `install`. The compiled comparison uses `CompilationMode.Partial` with
baseline profiles disabled and two workload warmups, then ART compilation. It isolates the
benefit of compiling the exercised paths; it is not an APK-code improvement.

Input-injection durations are recorded separately. They include UiAutomator and input
acknowledgment overhead and must not be reported as full input-to-photon latency.

## Controlled Comparison

After all builds exited, the old APK was reinstalled and the two modified paths were rerun.
The final candidate then ran all four scenarios, using the same partial-compilation warmups,
three measured iterations, profile, display settings and input cadence. No build jobs ran
during either cohort. This comparison must not be mixed with the initial reproduction above.

| Paired scenario | Old median / p95 / p99 CPU frame duration | Candidate median / p95 / p99 |
| --- | --- | --- |
| Settings playback rows | 19.41 / 24.03 / 26.02 ms | 16.79 / 23.52 / 33.07 ms |
| Home vertical | 35.50 / 45.71 / 60.31 ms | 35.61 / 47.72 / 81.65 ms |

Settings' median improved by approximately 13.5%, but tail frames remain variable and its
p99 was worse in this cohort. Home did not demonstrate a frame-time improvement; its p95
and p99 were also worse in this run. This is not evidence that Home is fully fixed or that
all reported late responses are gone. A longer physical-TV comparison is still required.

The candidate's unpaired horizontal-Home and Settings-sidebar measurements were respectively
31.58 / 39.32 ms and 23.05 / 45.22 ms median / p95. No improvement claim is made for them.

In the first paired vertical-Home traces, recomposition time per drawn frame fell from
approximately 2.95 to 2.14 ms. RenderThread drawing remained approximately 19.1-19.8 ms per
frame. This illustrates why removing avoidable composition work did not resolve the overall
graphics bottleneck on this emulator. These are trace examples, not whole-run averages.

## Findings and Changes

1. Home animated row padding was read during composition. Its unchanged 10-20 dp spacing
   now reads animation state during layout, avoiding whole-row recomposition each frame.
2. Settings read its content focus index in the large screen's `LaunchedEffect` keys.
   Focus reveal now observes it with `snapshotFlow` / `collectLatest`. New moves cancel
   obsolete scroll requests without invalidating the entire screen. Existing 100 ms reveal
   animations, focus appearance, input handling and settings actions remain unchanged.
3. The compiled baseline profile already covered Home through wildcard rules, despite having
   no explicit `NetflixHomeKt` entries in the source profile. The compiled profile contained
   276 Home rules but no `SettingsScreenKt` rules. The TV profile generator now exercises
   Home, the Settings sidebar and all Playback rows using real D-pad navigation.
   The device-generated Settings addition contains 892 rules; existing coverage is preserved.

Perfetto also shows significant RenderThread and graphics-wait costs. Compilation and
recomposition fixes must not be described as eliminating every graphics bottleneck.

## Functional Verification

- 38 targeted JVM tests passed: Home focus, row state, Back behavior, Netflix sizing,
  Settings scroll positioning and IPTV group order.
- 22 Android TV emulator regression tests passed. These cover artwork blending, horizontal
  and vertical morph geometry, rapid reversals, first-press selection, picker dismissal,
  every Playback row, 300 catalogs and 1,000 IPTV categories.
- The new row-spacing test verifies 10 -> 20 -> 10 dp padding changes update measured height
  without recomposing the row's contents.
- Two black-box release checks passed: rapid reversal followed immediately by OK changes
  only the latest setting; deep scrolling and repeated dialog entry work with one press.
- Debug builds are used only for MockK/Compose functional tests, never for frame metrics.
- Release screenshots were inspected separately after animations settled.
- The final APK's bundled profile was installed after clearing runtime profiles, then ART
  compiled it successfully (`speed-profile`). Its exported profile contained 225
  `SettingsScreenKt` entries without opening Settings, verifying the new rules survive packaging.

An early release-smoke attempt selected a label/summary instead of its switch accessibility
node. The selector was corrected to identify checkable rows; the final two-test run passed.

## Reproduction

The benchmark package is self-instrumenting and targets the optimized app externally. Create
only the isolated English-language `TV Performance` profile on a dedicated TV test device.
Do not run the workload against a personal account or during builds or other benchmarks.

```powershell
./gradlew.bat :app:assembleSideloadRelease :benchmark:assembleBenchmarkRelease -PincludeX86Abis=true
adb -s SERIAL install -r app/build/outputs/apk/sideload/release/app-sideload-release.apk
adb -s SERIAL install -r benchmark/build/outputs/apk/benchmarkRelease/benchmark-benchmarkRelease.apk
adb -s SERIAL shell am instrument -w -r -e class com.arvio.benchmark.TvNavigationBenchmarks -e iterations 3 -e compilation partial -e androidx.benchmark.suppressErrors EMULATOR com.arvio.benchmark/androidx.test.runner.AndroidJUnitRunner
```

Use the `EMULATOR` override only on an emulator, never suppress the debuggable-app warning.
Omit `compilation partial` for a separately reset as-installed/JIT run. Pull benchmark output
after each run because profile collection can clear the benchmark output directory.
`-PbaselineProfileTv=true` opts the Baseline Profile Gradle plugin into connected-TV collection;
the default managed-phone generation remains available.

Screenshots are captured in a separate release smoke test, after animations settle, not during
frame measurements. Generated x86-inclusive APKs are emulator artifacts, not public downloads.

At the end of this initial audit, changes remained local on `codex/tv-navigation-latency`.
There was no Home speedup claim, GitHub push or physical-TV installation. The follow-up
below records the subsequent Home work rather than replacing the unsuccessful first result.

## Follow-Up Home Rendering

The final candidate also includes main's `6e0266d16` merge of optional WeTrakr lists.
The preserved baseline APK is from `6f24daaf1` with the first-pass Settings/profile and Home
spacing changes above. The isolated profile has no WeTrakr account, so those optional rows
are absent in both versions. This is not a comparison of different visible catalog layouts.

The Home changes preserve the existing 220 ms rail morphs, artwork fades, 100 ms Settings
scroll reveals, image dimensions, focus appearance, trailers and other features:

- Scope logo-map inputs to each rail. The ViewModel uses a `SnapshotStateMap`; unrelated
  logo changes must not invalidate every card's lookup. Remember the rail map by its own
  keys and values, preserving its identity until one of its actual logos changes.
- Pass enriched hero metadata only to rows containing the matching title. Existing matching
  and fallback rules remain unchanged; late synopsis/rating updates are still displayed.
- Rasterize only loaded, visible, unfocused, settled normal rails. Do not allocate textures
  for prefetched offscreen rails, skeletons, the active trailer/card or special service rows.
- Observe vertical movement outside composition. Read cache readiness in `graphicsLayer`,
  using live `Auto` compositing while moving and `Offscreen` only after 300 ms of quiet.
  This delay prepares a cache; it does not delay navigation, artwork, metadata or animation.
- Preserve overflow with compensated layer outsets of at least 16 physical pixels / 8 dp.
  Row layout dimensions and positions are unchanged.

Earlier experiments that cached rails during vertical movement were rejected: their
performance was not consistently better. Background Java activity also contaminated some
exploratory runs. No improvement claim is based on those runs. After confirming there were
no active builds, the remaining idle Gradle daemon was stopped normally before the final run.

### Final Controlled Comparison

Same constrained emulator/profile and optimized non-debuggable APKs. Alternate APK order
was **baseline, candidate, candidate, baseline**, with an emulator reboot and 20-second
boot settle before each cohort. Each scenario used partial compilation with two warmups
and three measured iterations. No builds ran during measurement; host Java CPU was sampled
throughout and the final four cohorts passed the activity guard.

CPU frame durations from the benchmark JSON, in milliseconds:

| Comparison | Scenario | Baseline median / p95 / p99 | Candidate median / p95 / p99 |
| --- | --- | --- | --- |
| Baseline then candidate | Horizontal | 37.97 / 68.17 / 107.67 | 25.55 / 37.61 / 60.81 |
| Baseline then candidate | Vertical | 48.03 / 108.97 / 258.90 | 37.74 / 52.30 / 142.55 |
| Candidate then baseline | Horizontal | 34.65 / 44.63 / 74.87 | 22.47 / 34.42 / 66.77 |
| Candidate then baseline | Vertical | 37.42 / 51.66 / 107.04 | 37.75 / 48.08 / 56.80 |

Horizontal median frame cost fell by 32.7% and 35.1%; p95 fell by 44.8% and 22.9%.
Vertical p95 improved in both orders. Its median improved in the first comparison, but was
essentially unchanged (+0.9%) in the reverse comparison. Do not describe vertical median
performance as consistently faster, or the application as completely jank-free / 60 fps.
These are CPU frame measurements, not full input-to-photon latency or physical-TV guarantees.

Frame-overrun p95 also improved in both orders: horizontal 111.37 to 46.75 ms and
51.86 to 47.07 ms; vertical 329.33 to 128.37 ms and 95.93 to 55.07 ms. Long overrun
tails remain on this emulator, so the CPU improvements do not imply perfect frame delivery.

There were six measured iterations per version/direction, 648 measured D-pad presses in
total, plus unmeasured setup and compilation warmups. Raw logs, JSON, traces, host-load
samples and separate release screenshots are local under
`artifacts/home-render-cache-20261117/final-*` and `verified-*.png`.

The final optimized release passed both black-box checks: rapid reversal followed immediately
by OK activates only the latest setting, and deep scrolling plus repeated dialog entry works
with one press. Separate release screenshots were inspected; layout and styling are unchanged.

The source-linked final regression run passed all 69 targeted checks:

- 38 JVM tests for Home focus, row state, Back behavior, Netflix sizing and Settings scrolling.
- 29 Android TV emulator tests, including the existing artwork/morph, vertical anchoring,
  rapid reversal, Settings input, 300-catalog and 1,000-category cases.
- Within those device tests: pixel comparisons at normal and low density preserve overflow,
  translucency, text shadows and exact layout bounds (RGBA tolerance 0.02 per channel).
  Cache changes do not recompose a stationary row; artwork fades and child progress updates
  continue rendering. Actual `SnapshotStateMap` logo updates reuse unrelated rail maps while
  relevant updates/removals appear correctly. Late enriched synopses follow the matching title
  when moving between rows.
- The two optimized-release black-box checks described above.

Functional builds are debug/test builds signed with the release key, solely to preserve the
isolated emulator profile between installations. Frame timings use only optimized release
builds, never debug/test builds. The physical TV was not used for these measurements.

### Main Integration Verification

The performance commit was merged with main through `05b9243f3`, preserving the subsequent
Phone QR and website changes. Home's source remains byte-for-byte identical to the controlled
comparison candidate. The merged, minified, non-debuggable release built successfully, and
both optimized-release black-box tests passed again (35.186 seconds). The 69-check regression
run above preceded this integration; it is not a claim that all 69 tests were rerun afterward.
An additional three-iteration-per-direction integration benchmark passed: horizontal CPU
median / p95 was 25.40 / 37.55 ms, vertical 37.76 / 48.52 ms. This unpaired sanity check
is separate from the controlled comparisons above and is not used to calculate their gains.

## References

- [Android Macrobenchmark guidance](https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview)
- [Emulator acceleration and host hardware](https://developer.android.com/studio/run/emulator-acceleration)
- [Baseline Profile format and measurement](https://developer.android.com/topic/performance/baselineprofiles/manually-create-measure)

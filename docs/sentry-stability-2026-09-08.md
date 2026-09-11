# Sentry stability fixes, 2026-09-08

Base: `51c761f3974f4aab94fa8d9f0b20017d9965b566` (main after PR #672).
Scope: the Android Sentry project. No web telemetry audit or production deployment.

## Patched paths

| Sentry issue | Evidence and change |
| --- | --- |
| 138695053 / ANDROID-D9 | Sideload resolves OkHttp 5.3.2 through Cloudstream/NiceHttp, but only ARVIO's own context was initialized. Initialize OkHttp's Android platform before Application injection and startup DNS. Keep Play on its OkHttp 4 initialization path. Align sideload networking modules at 5.3.2. |
| 145230250 / ANDROID-QD | Continue Watching accessed `mediaType.name` on a null deserialized enum. The web cloud writer uses lowercase `movie`/`tv`; Android previously recognized uppercase only. Accept both forms while preserving uppercase Android serialization. Validate cached/cloud entries, normalize missing optional strings, and keep valid neighbouring entries. Reject an entirely corrupt non-empty import without erasing existing local history. |
| 139563511 / ANDROID-GE | TV main-thread stack blocked in SQLite `getByIds`. Make the fallback channel lookup suspend-only and execute it on IO. Move the initial catch-up lookup into its coroutine too; evaluate its throttle after the lookup resumes. |
| 125223638 / ANDROID-2E | Launching the profile image picker can throw on TVs without a compatible picker. Catch unavailable/inaccessible picker errors and offer built-in avatars rather than crashing. |
| 145585912 / ANDROID-RP | Startup ANR inside Discord SDK static initialization/native library loading. Preload on IO, retain SDK activity setup on main, and wait for initialization before handling login/auth callbacks. Make readiness observable so settings refresh when initialization completes. Native SDK runtime remains unverified locally. |

## Already addressed on main

- 145178195 / ANDROID-Q2: source discovery uses `channelFlow`/`send` since `69cffefe0`. Existing regression tests for partial results, failure, timeout and cancellation pass.
- 144809298 / ANDROID-NM: QR rendering already rejects zero sizes and blank input. No additional patch claimed for this report.

## Still open

- 145176884 / ANDROID-Q1: null state inside Media3 FrameExtractor's asynchronous playback listener. The existing decoder-session tests pass, but do not reproduce this internal callback crash. Do not mask it with a global exception handler or claim that cancellation tests prove it fixed.
- 145591433 / ANDROID-RQ: native `JMediaCodec` crash. Needs the affected device/source combination and native diagnostics.
- 145674949 / ANDROID-RS: exhausted Java heap. The failing small allocation does not identify the memory owner; needs a heap/allocation capture.
- 145190998 / ANDROID-Q4: missing framework accessibility action on a reported Android 14 device. Needs reproduction and runtime SDK/framework verification before changing accessibility support.
- 145191041 / ANDROID-Q5: malformed startup of Discord's AuthenticationActivity. Latest-event retrieval did not provide a usable event. The startup ANR change is not claimed to fix this separate auth failure.

## Verification

Command: `gradlew.bat :app:testSideloadDebugUnitTest :app:testPlayDebugUnitTest --console=plain --max-workers=2`

- Sideload: 790 tests, 789 passed, 1 skipped, 0 failures/errors.
- Play: 791 tests, 790 passed, 1 skipped, 0 failures/errors.
- New tests cover malformed/mixed Continue Watching caches, lowercase web media types, round-trip serialization, preserved provider identity/resume positions, and public-suffix loading without Android Startup initialization.
- Both variants compile. `git diff --check` passes.
- Robolectric needs Conscrypt disabled on Windows, consistent with existing project tests. It also logs a non-failing temporary HTTP-cache cleanup warning.
- This checkout has no optional Discord Partner SDK AAR. Its native initialization change has not been exercised with the actual SDK.
- No TV installation, minified release build, production deployment, or Sentry issue resolution was performed.
- Build 312 has been reused across different commits. Use an identifiable new build for post-release Sentry validation rather than assuming all 1.9.996 events represent the same code.

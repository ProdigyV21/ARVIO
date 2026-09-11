# Ready-only seek previews

## Scope

- Main TV controls, quick remote seeking and mobile scrubbing use the same ready-only image component.
- A missing, recycled, unverified, wrong-generation or out-of-window frame creates no preview component. The caller also validates source identity.
- Existing timestamp/cue validation remains in force. A valid decoded image is not a promise of an exact frame at every millisecond; decoder previews use the existing five-second tolerance, and provider images use their cue interval.
- There is no grey placeholder, loading dash or separate timestamp strip. The normal seekbar and time labels remain visible.
- Appearance fades in over 80 ms. Position changes never crossfade or retain an outgoing image.
- The main control layout reserves space for the browsing session, independently of image readiness. Mobile previews overlay the timeline without changing its bounds and sit 4 dp above the playhead, rather than above its invisible touch target.
- JPEG compression and pruning no longer block delivery of a decoded frame. One background writer retains at most one active and one pending image. Intermediate pending writes can be dropped; decoded frames remain in the bounded memory cache. Closing the provider cancels pending persistence.
- The existing two-second remote idle commit and paused/playing resume intent are unchanged. Touch scrubbing still commits on release.

## Verification Scope

The automated suite checks cache queue bounds, cancellation, persistence metadata, invalid-frame removal, immediate image replacement, seekbar geometry, real local/range-HTTP decoding and the production player with a three-scene video. Coloured scenes deliberately make a stale image or a copy of the paused main video distinguishable.

These tests are not a new physical Matlock or cold 4K benchmark. The limitations recorded in [the Matlock validation](player-seek-preview-matlock-2026-09-07.md) still apply. With a slow or unsupported source the preview stays hidden; playback and ordinary seeking remain usable.

## Results

Final sideload debug build based on main `40672c1ad`, with this change:

| Check | Result |
| --- | --- |
| Preview JVM/Robolectric suite | 86 passed, no failures or skips |
| Android 31 TV emulator | 7 passed; suite duration 31.933 seconds |
| Android 34 phone emulator | 7 passed; suite duration 21.107 seconds |
| Screenshot inspection | Phone preview shows blue at 00:22 and green on reverse while primary video remains paused in the red scene |
| Geometry assertion | Mobile preview remains 4 dp above the playhead; scrubber bounds unchanged across loading, success and missing targets |
| TV production-player regression | Two-second idle commit works forward/backward and retains paused/playing intent |

Local logs: `preview-ready-spacing-build.log`, `preview-ready-tv-tests.log`, `preview-ready-phone-final-tests.log`. Screenshots: `preview-ready-phone-final-evidence/phone-preview-forward-blue.png` and `phone-preview-backward-green.png` under the local user directory. TV production screenshots show committed playback states, not proof of cold preview latency.

No release asset was replaced and no APK was installed on the physical TV during this change. Both test emulators were stopped afterward.

# Windowed seek previews (Android TV and mobile)

Based on main `38062eed06a662686c0bfa081fb4104855641b81`. This uses ARVIO's
existing Media3 player and preview pipeline, not an MPV player replacement.

## Behavior and limits

- TV: a fixed five-slot filmstrip with a larger selected image. Only valid,
  timestamp-matched frames appear. Missing neighbors do not shift the timeline.
- Mobile: the existing compact touch preview uses the same caching and safety
  improvements, without a five-image strip crowding the phone screen.
- Capture a small frame already displayed by the main renderer around ten-second
  boundaries. PixelCopy is asynchronous; no extra network connection, decoding or
  playback seek is needed for this rewind cache. Renderer timestamps and source
  generations are checked before and after copying. Protected/unsupported surfaces
  may return no image.
- Speculative decoding requires eight seconds of uninterrupted healthy playback,
  at least 25 seconds buffered, a foreground player, and a suitable H.264 stream
  and device. Low-memory TVs, heavy streams and IPTV do not launch this background
  decoder. Playback strain suspends speculative work.
- Warm one missing frame per turn near the current position, not the entire file.
  Background requests have a 4 MiB network budget; paused foreground work has a
  16 MiB budget. Range cache is bounded at 8 MiB and compressed disk cache at 128 MiB.
- Foreground requests preempt speculative work. Committing a seek cancels preview
  requests before playback resumes. A preview never delays seek completion.
- A second full-resolution decoder is prohibited for unknown formats, low-RAM
  devices, Dolby Vision and resolutions beyond the device's preview memory budget.
  Cached renderer frames and provider images remain usable.
- Cold remote previews are **not guaranteed instant**. Unsupported codecs and slow
  servers may yield no image; actual playback must remain independent.

## Media3 compatibility fix

A real remote 4K Dolby Vision remux exposed a main-looper null-pointer exception in
Media3 1.9.0 FrameExtractor when READY arrived without a decoded frame. A private,
Apache-licensed copy of the two extractor classes now fails the preview future and
ignores late callbacks safely. It does not change the main playback renderer.
See `app/src/main/java/com/arflix/tv/player/previewmedia3/README.md` for upstream
provenance and upgrade instructions.

## Validation

Private signed source URLs are supplied through an on-emulator fixture and are
never committed. Live sources are cached TorBox files from an already configured
addon. Tests do not submit downloads, modify the user's watch state or buy anything.

- Focused JVM suite: 95 tests passed.
- API 31 Android TV: decoder/range tests, callback regression, filmstrip geometry,
  stale-frame rejection and overlay positioning passed. Both production-player
  tests passed, including renderer-derived rewind images and remote seek
  auto-commit with paused/playing intent preserved.
- The 28.2 GB Dolby Vision remux now completes preview failure without a process
  crash (about three seconds). This is a graceful-failure check, **not** evidence
  that this emulator can decode that remux or play its primary video.
- Earlier isolated 1080p tests with 1 MiB range chunks returned validated cold
  frames in 4.8 seconds (MP4) and 5.3 seconds (MKV). Cached requests are measured
  separately; network/cache conditions make cold timings variable.

An intentionally forced second-decoder stress run on the 192 MiB TV emulator
increased dropped frames (720p baseline: zero; forced decoding: 600). This is not
the production configuration: constrained TVs cannot start that background
decoder. A separate 1080p run already dropped frames in baseline playback and
could not obtain a cold preview while the main decoder was retained. Do not use
those runs as evidence of universal cold-preview support or zero playback cost.
The final comparison exercises renderer caching with the actual device memory
policy, and explicitly checks dropped frames as well as rebuffers.

### Live comparison using the production device policy

API 34 phone emulator, 192 MiB heap, real 720p H.264 MKV debrid file:

| Measurement | 30s baseline | 45s preview caching |
|---|---:|---:|
| Playback position advanced | 30,020 ms | 45,198 ms |
| Rebuffers | 0 | 0 |
| Dropped frames | 0 | 0 |
| Extra decoder warming turns | 0 | 0 |

Four renderer frames were cached, the selected preview lookup took less than
one millisecond, and cached lookup P95 was 0.030 ms. Seeking to two minutes resumed
playback successfully, with zero player errors. This is a bounded test on this
source/device, not a guarantee for every server or codec.

The phone checks also exposed an existing portrait-layout calculation that used
the requested 150dp preview width even when the available track was narrower. The
card now uses its actual constrained width when calculating its vertical offset.
The corrected geometry test and both production phone-player tests passed. The
API 34 phone also decoded a validated cold 1080p MP4 preview in 3,913 ms at the
two-minute target (actual decoded frame 125,000 ms, within the five-second limit).

API 31 Android TV emulator, 192 MiB heap, the same real 720p debrid file:

| Measurement | 30s baseline | 45s preview caching |
|---|---:|---:|
| Playback position advanced | 30,021 ms | 45,045 ms |
| Rebuffers | 0 | 0 |
| Dropped frames | 0 | 0 |
| Extra decoder warming turns | 0 | 0 |

Four renderer frames were cached; first selected lookup took 2 ms and cached
lookup P95 was 0.291 ms. Playback resumed after seeking with zero player errors.
Both live runs used the real low-memory policy, preserving playback over speculative
future decoding. Higher-memory-device background decoding remains guarded, but
these runs do not benchmark that path on physical high-end hardware.

## Physical TV timeout correction (27 October)

The TCL Android 14 TV reproduced a failure not seen on the emulators: copying
its 3840x1632 HEVC HDR surface succeeded (PixelCopy result 0), but completed in
approximately 560–650 ms. The previous 500 ms coroutine deadline discarded every
copy. The separate 250 ms frame-advance check would also reject those copies.

The deadline is now 1,500 ms. Validation accepts continuous renderer advancement
within that interval and checks the latest presentation timestamp against the
current player position. Source-generation checks, cancellation during seeking,
and bitmap cleanup remain intact. Regression cases cover the measured TV delay,
forward/backward discontinuities, frozen timestamps and expired copies. The
streaming-device harness uses the same production timing rules.

The TV details hero also uses a wider, three-line overview on compact-height TVs,
a smaller hero-to-rail gap and more reserved season/episode space with bottom
padding. The top bar is unchanged.

The user requested no further TV interaction after diagnosis. Final validation
therefore uses automated checks and release-package verification; the final
corrected build has not been visually re-tested on the physical TV. Installation
is authorized, but launching or further navigation is not. Heavy 4K sources on
low-memory TVs still use captured rewind frames; unseen forward positions are
not guaranteed to have thumbnails, to avoid competing with playback decoding.

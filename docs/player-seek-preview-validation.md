# Seek Preview Validation

Latest UI behavior and focused test results: [ready-only previews](player-seek-preview-ready-only-2026-09-07.md).

Implementation baseline: `9a1e5c84`, 2026-09-05. Android 1.9.996 (311).

For the subsequent 1.9.996 (312) retained-decoder and two-second automatic remote seek
implementation and measured limitations, see [the September 7 retest](player-seek-preview-matlock-2026-09-07.md#retained-decoder-and-automatic-seek-retest).
The results below are historical baseline measurements, not current release certification.

## Behavior

- A single quick-seek press immediately skips 10 seconds without an image panel.
- Repeated/held input enters browsing at a cumulative 20 seconds. The primary player pauses;
  Select commits once, Back cancels the uncommitted position, and prior playing intent is restored.
- Main timeline and quick timeline share this interaction state and timestamp validation.
  Touch release commits; touch cancellation does not. The target survives the exit animation.
- Both timelines have a white thumb. Previews aspect-fit, without their own timestamp strip.
- Images carry actual decoded timestamps or validated provider cue intervals. Stale-source,
  out-of-window and unverified images are rejected. Unsupported previews hide without blocking seeking.
- Existing Jellyfin trickplay, Plex BIF and supported Emby BIF indexes are tried before local decoding.
  Explicit VTT/BIF/image-HLS descriptors are supported; arbitrary addons do not automatically expose them.

## Resource Boundaries

One active extraction and one replaceable pending request. Media3 decoding uses a loopback-only
range bridge with playback authentication, bounded reads and cancellable requests. There is no
ARVIO media host, full-file download or server-side thumbnail-generation request.

Decoded previews are capped at 480/640 pixels, with a bounded memory cache and 128 MiB versioned
disk cache enforced during writes. Secondary extraction is disabled below a 192 MiB heap class.
Background decoder warming is skipped for constrained devices/heavy streams; image-only warming
cannot escalate to decoding. Preview work is cancelled before committed playback resumes.

## Measured Tests

69 focused JVM tests passed: 29 provider index/manifest/HTTP tests, 6 interaction tests,
10 provider integration tests, 16 timestamp/scheduler tests and 8 range-network tests.
The Robolectric fixture disables Android Conscrypt JNI on the Windows host; production TLS
configuration is unchanged.

Android TV emulator, API 31, x86 with ARM translation:

- 12 instrumentation tests passed in 24.359 seconds.
- Final-code repeat after the release build: 12/12 passed in 23.132 seconds, without changing
  deadlines. A concurrent-R8 run had 10/12 pass and two decoder deadline failures on the loaded
  host; this is retained as a load-sensitivity finding, not counted as a successful run.
- Real local MP4 and authenticated range-HTTP MP4 extraction returned the expected red and blue
  scenes at actual timestamps 0 and 20 seconds, requested at 2 and 22 seconds.
- Loopback range cold extractions: 2,578 ms and 973 ms. Local extractions: 487 ms and 1,250 ms.
  Final-code quiet repeat: range 1,769/700 ms, local 357/723 ms.
- Two sets of 100 warm memory lookups passed a p95-under-100-ms assertion. This is cache-read
  timing, not measured remote-input-to-screen latency.
- Reducer and Compose harness tests cover single/repeated input, confirmation, cancellation,
  initially paused playback and retaining the target through exit. The harness uses a fake
  primary player; physical PlayerScreen wiring requires a separate manual run.

These synthetic video tests check color, timestamps, aspect ratio and corners of solid frames.
They do not independently prove cropping correctness for all patterned, rotated, anamorphic or
HDR content, nor real-world network performance.

## Physical TCL Test

TCL Smart TV Pro G08, Android 14/API 34, ARMv7, 192 MiB heap class. Updated in place with the
existing release signer; all three existing profiles remained available. No uninstall or data clear.

- Matlock S1E9, remote 4K HEVC MKV, 5.36 GiB: quick and main seeking, reverse input, Select and
  cancellation behaved correctly, without a jump to zero. The cold thumbnail did not arrive within
  the UI deadline. The empty pending panel disappeared and seeking remained usable. This is a
  failed preview-availability/performance test, not an instant-preview pass.
- Logs showed the secondary software HEVC decoder allocating full-resolution 3840x2160 buffers
  before the output scaling effect. Limiting bitmap output dimensions does not cap decoder memory.
  The cancelled loopback request later produced an HTTP 503; this does not establish an upstream
  provider 503. No fatal app exception was observed in this manual run.
- Matlock S1E9, remote 1080p H.264 MKV, 3.18 GiB: the first cold request also missed the deadline.
  A subsequent quick target at 32:43 displayed an office scene while the main video remained paused
  at an earlier scene. Select committed playback and a pause at 32:45 showed that office scene.
- The main timeline displayed a different, earlier outdoor scene when moving back to 32:25.
  Revisiting that same target showed the cached image in the first screenshot requested after a
  200 ms wait. Forward and reverse images were captured after 3-second waits. These are manual
  screenshot observations, not measured p95 input-to-display latencies or exact timestamp matching.
- Both seek surfaces showed their white thumb. Initially paused browsing preserved pause on cancel.
  Exact decoded timestamps are checked by the provider; release Log.i diagnostics are stripped,
  so their values were not independently captured from this physical release build.

Local screenshots are in `artifacts/seek-1080-next.png`, `seek-1080-committed.png`,
`seek-main-reverse-preview.png`, and `seek-main-revisit.png`; they are not repository assets.
The previously open 60 GiB movie was not retested on this build. There was no paired preview-on/off
buffering benchmark, physical HDR geometry fixture, lower-end Fire TV or mobile run.

**Release assessment:** shared seek interaction and validated-image paths are implemented and have
focused automated coverage. Real-source 1080p previews work after preparation, but universal cold
availability and Netflix-like latency have not passed. Keep this a tester build and do not advertise
instant previews for arbitrary remote media. Native home-server image paths still need live validation.

## Limitations

Cold previews from arbitrary remote files cannot be promised to be Netflix-instant. Range support,
container indexes, keyframe spacing, codec capability and provider latency determine availability.
Generic HLS/DASH video without an explicitly supported image track is unavailable rather than
fed into an unsupported decoder fallback. Protected media may not provide previews.

Plex multipart timelines and ambiguous Emby alternate versions are deliberately not mapped to
possibly incorrect thumbnails. Cross-origin storyboard images are rejected to protect credentials.
Rotating signed playback URLs currently invalidate persistent cache identity. Real Jellyfin/Plex/Emby
server-generated thumbnails still need separate live-server compatibility coverage.

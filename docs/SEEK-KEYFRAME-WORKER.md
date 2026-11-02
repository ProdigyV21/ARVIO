# Independent seek thumbnails

HTTP file previews can use a separate `:seek_preview` process with a persistent
software FFmpeg decoder. The worker reads through ARVIO's authenticated,
bounded loopback range proxy. It never seeks the main player, opens an audio
device, or reserves a hardware video decoder. Only small bitmaps and their
actual presentation timestamps cross Binder.

During playback, one low-priority extraction job uses at most two software slice
threads to decode an independent keyframe. While the player is paused for preview
browsing, the worker uses normal priority and up to four slice threads. Switching
modes recreates only the decoder; the demuxer/index and range cache are retained.
There is still only one preview decoder. Full-resolution deblocking is skipped for the 320px
thumbnail; the main playback decoder is unchanged. A
frame farther than five seconds from the requested timestamp is rejected.
Cancellation interrupts network reads; source replacement invalidates cached
results. Existing preview byte budgets, timeouts, cache limits and playback
health checks still apply. Background work stops when playback is strained.

This supports H.264, HEVC and VP9 files up to 3840×2160, including unwatched
positions when the server supports byte ranges and the container has usable
seek information. HDR10/HLG output is tone-mapped for thumbnails. Dolby Vision
profile 5 lacks an HDR10-compatible base and is deliberately rejected. IPTV
sources retain their single-connection restriction. Provider images and
renderer captures remain available independently of this backend.

Cached previews are immediate; a cold frame still requires network reads and
decoding. This does not promise instant extraction at every timestamp or zero
CPU/network cost. The player always takes priority over speculative warming.

## Rebuilding the native libraries

Run `scripts/build-seek-keyframes.sh` on Linux/WSL with Android NDK r28c:

```sh
ANDROID_NDK_HOME=/path/to/android-ndk-r28c scripts/build-seek-keyframes.sh
```

The script verifies the official FFmpeg 8.0.1 source archive SHA-256 and builds
all four Android ABIs for API 23 with 16 KiB ELF alignment. `SEEK_ABIS`,
`SEEK_BUILD_DIR` and `SEEK_JOBS` can restrict/reuse builds. No FFmpeg source
patches are applied. The JNI bridge is `app/src/main/cpp/seek_keyframe.cpp`.

FFmpeg is dynamically linked, configured without GPL/nonfree components, and
distributed under LGPL 2.1 or later. The license is bundled in
`app/src/main/assets/licenses/ffmpeg-LGPL-2.1.txt`. Matching, unmodified source
is included in `third_party/ffmpeg/ffmpeg-8.0.1.tar.xz` (upstream:
https://ffmpeg.org/releases/ffmpeg-8.0.1.tar.xz). The build script contains the
complete configuration and allows rebuilding/replacing the shared libraries.
No code from the AGPL mpvRx project is included.

## Validation

`KeyframeWorkerDeviceTest` checks authenticated ranges, unwatched 4K Main10
frames, actual timestamps, cancellation, source replacement, memory-pressure
recovery and cache hits.
`SeekPreviewStreamingDeviceTest` accepts an optional private on-device fixture
with `nativeKeyframes: true`; it checks remote unwatched positions without
logging URLs or headers. Private fixtures must never be committed.

The live playback comparison requires actual rendered video, successful preview
generation during playback, and successful resume after seeking. Its optional
`previewUrl` lets an emulator play a supported file while extracting thumbnails
from a heavier file. Reports explicitly identify that arrangement; it is a
resource-contention test, not proof of full-resolution playback support on that
emulator or every physical TV. A simulated HEVC decoder failure must not be
counted as a successful audio-only playback benchmark.

### Emulator checks, 27 October 2026

- 97 preview JVM tests passed.
- API 34 phone and API 31 TV workers decoded unwatched synthetic 4K Main10
  frames with correct timestamps and colors. The TV test also verified
  cancellation, source replacement and recovery after a memory-pressure callback.
- A cached 28.2 GB debrid remux produced validated frames at 2, 10, 20 and 30
  minutes without prior playback. Optimized phone timings were 2,477 ms for
  the first request and 882/880/790 ms for the later uncached positions.
- On the 2 GB TV emulator, a supported 720p stream rendered while the worker
  extracted five ahead frames from that 4K remux. Baseline (30 seconds) and
  preview work (45 seconds) each had zero rebuffers and dropped frames. Five
  paused-browsing thumbnails loaded; cached lookup p95 was 0.059 ms; resume
  after seek succeeded with zero player errors.
- Full 4K playback was not validated: the emulator's platform HEVC decoders
  rejected it. An earlier 1080p contention run had one rebuffer in both windows
  and failed the strict zero-rebuffer assertion; it is not counted as a pass.
  These results do not guarantee zero impact on every physical device/network.

### Physical TV follow-up, 28 October 2026

The remote previously committed a seek after two seconds regardless of whether
its preview had finished. On the TCL ARMv7 TV a first native request took 3.36
seconds, so the UI could cancel a working decoder before displaying anything.
The commit timer now permits a bounded ten-second pending request and leaves a
newly delivered frame visible for at least 1.2 seconds. OK/Back remain immediate.

Background warming now requires eight seconds of buffered media (instead of 25)
and eight seconds of stable playback. The old threshold could exceed what the
80 MiB buffer holds for high-bitrate files. One frame may start every three
seconds; buffer, dropped-frame, foreground and memory protections remain active.
Only numeric preview timing/state diagnostics survive release log stripping.

Validation on the physical TCL Smart TV Pro, Android 14, normal signed release:

- 69 targeted JVM seek/preview tests passed, including pending-preview timeout
  and healthy-buffer regression coverage; release lint/build passed.
- Two 4K HDR episodes (6.29 GB and 7.60 GB) played with ahead warming enabled.
  Background frame requests succeeded during playback; playback resumed after
  quick seeking, and deliberately paused playback remained paused after scrubbing.
- Uncached foreground requests completed in 1,731, 1,505 and 1,338 ms. The cold
  worker's first background request completed in 3,357 ms including binding.
- A cached remote target displayed 60 ms after its input event. A physical-TV
  screenshot shows the selected frame and three cached neighbors together.
- The 7.60 GB stream subsequently produced ahead frames in 796–1,651 ms.
  These are measured examples, not a guarantee of instant uncached network reads
  or zero dropped frames on every source. Physical playback was observed, not
  instrumented with a baseline dropped-frame comparison.

### Faster five-frame strip

- The HTTP reader fetches 256 KiB chunks and reuses the final redirect request
  instead of resolving the download URL on every read. It retains the headers
  actually used on the final hop, and refreshes expired locations once through
  the original URL. Requests and signed URLs remain private.
- Memory lookups check neighboring cache buckets while still validating the
  real frame timestamp, source identity and generation. This prevents a valid
  frame from being missed solely because it was stored in an adjacent bucket.
- A neighbor request may queue after the foreground result has been delivered
  but before its worker cleanup finishes. It cannot preempt an active decode.
- Both forward slots are filled before the rewind slots. All five positions
  retain their geometry with subdued empty placeholders while loading, and a
  delivered center frame remains visible for at least two seconds before auto
  commit. If neighbors are still missing, auto commit allows up to four extra
  seconds for the paused window to finish. OK, Back and further seeking remain
  immediate; failed requests cannot leave browsing waiting indefinitely.
- Numeric native phase timings distinguish source opening, seeking, reads,
  decoding and scaling. They contain no source URL, headers or account details.
- 74 targeted JVM tests pass, including five-frame warming, the completion race,
  cross-bucket reuse, expired-link refresh and redirect-header preservation.
  All 20 native libraries retain 16 KiB ELF alignment across four ABIs.

Physical-TV measurements, 28 October 2026, TCL Smart TV Pro, Android 14:

- Normal signed release, 6.29 GB 4K HDR source: three fresh foreground requests
  took 868, 1,160 and 2,056 ms. Input-to-visible-center times were approximately
  977, 1,261 and 2,162 ms including debounce and UI delivery. These are examples,
  not a controlled before/after benchmark or a sub-500-ms guarantee.
- The slowest of those requests spent 1,455 ms reading source bytes, 347 ms
  decoding and 10 ms scaling. Smaller output alone cannot remove this latency.
- Cached target 874229 displayed 30 ms after input. Screenshots show two real
  frames on either side of the selected frame, with valid timestamps. Empty
  placeholders are not counted as successful thumbnails.
- The decoder uses at most two low-priority slice threads and skips full-size
  deblocking. Background warming remains conditional on healthy playback; no
  second hardware decoder is opened. A comparative dropped-frame baseline has
  not been measured, so these checks do not establish zero playback impact.
- Final installed release (APK SHA-256
  `d463f80964ef38baf70e8be1f2d01420c85a24d862d6796e98d4c15ef409d3e7`):
  a cold-worker request at 605461 ms took 2336 ms (2442 ms input to display).
  The four uncached neighbors completed in 759, 961, 1645 and 706 ms; the
  bounded timer committed only after the fourth finished, with `resume=false`.
  A repeat seek visibly showed all five actual frames. The full cold strip
  needed about 7.3 seconds from the final input, not half a second.
  Final release build/lint and all 74 targeted tests passed.

### Progressive coverage and playback-byte reuse

The next coverage scheduler alternates local detail with wider samples. It
first covers minute marks in the next five minutes, then their half-minute
gaps. Known files below 20 GiB also receive a two-minute whole-title pass,
followed by one-minute and thirty-second passes. Unknown sizes and large
remuxes remain within a ten-minute forward window. Each turn still starts at
most one frame; the three-second pacing and playback health guard are retained.
Speculative extraction stops on metered networks. Distant preparation has a
per-source 128 MiB range-byte budget and 512-attempt limit; the usual nearby
window continues after that budget is exhausted. Initial probing is additional.
Failed points are temporarily skipped rather than trapping subsequent passes.

An indexed disk lookup can return a nearby real sample within twenty seconds
of the requested position. Such samples retain their actual PTS, show an
approximation/time label, and cannot satisfy a precise extraction request.
The seek destination is unchanged. Precise extraction replaces the sample;
source changes invalidate both. No arbitrary poster or unrelated frame is used.

The range reader can read already committed Media3 playback-cache spans and
known content length. This path has no upstream factory or write sink, never
waits for a cache hole, and treats eviction races as misses. It does not change
the main player's data source or enlarge its cache. Shared bytes join the
existing small preview byte cache, avoiding repeated disk reads. Missing
ranges still use the existing authenticated, bounded preview HTTP path.

Coverage remains progressive, not instant whole-title generation. Measurements
must distinguish the first precise frame, a nearby approximate sample, a
prepared exact frame, and completion of all five strip frames. A source that
has not been decoded or prepared still requires network access and decoding.

Physical-TV coverage checks, 28 October 2026, same TCL device and a fresh
8.12 GB 4K HDR episode, normal signed release:

- Preparation reached the two-minute sample about fifteen seconds after playback
  started and the four-minute sample after about thirty seconds. Sparse samples
  reached twelve minutes during the first roughly ninety seconds of playback.
  This is progressive coverage, not a complete cache of every second.
- Seeking to 4:03.947 showed a prepared sample at 3:58.822 in 44 ms, with its
  approximate timestamp visible. The precise replacement appeared after 1,409 ms.
  Another prepared exact-enough frame appeared in 30 ms.
- With 256 KiB range reads, untouched targets at 24:03.947, 27:03.947 and
  30:03.947 needed 2,891, 1,595 and 2,342 ms from input to precise center frame.
  Extraction itself took 2,787, 1,492 and 2,226 ms. The first two full strips
  took about 7.7 and 6.0 seconds; the third still lacked one neighbor at the
  bounded auto-commit deadline. Nearby placeholders are not counted as previews.
- Four dropped video frames were reported over an initial 44-second interval;
  no audio-underrun event was observed. This includes startup and lacks a
  controlled playback-only baseline, so it cannot establish zero playback cost.
  The playback-health, memory, metered-network and byte-budget guards remain.

These are device observations, not a guarantee for every provider, codec, file
or network. Private numeric logs and screenshots are kept in the local
`artifacts` directory; source URLs and credentials are excluded from reports.

The final candidate uses 1 MiB range reads to reduce serial HTTP round trips,
retaining the 8 MiB range cache and existing extraction-byte budgets. On the
same 8.12 GB source, three previously unprepared targets at 25:23.120,
28:23.120 and 34:23.120 produced these results:

| Measurement | 25:23 | 28:23 | 34:23 |
| --- | ---: | ---: | ---: |
| Input to visible precise center | 1,266 ms | 1,304 ms | 1,026 ms |
| Extraction | 1,172 ms | 1,192 ms | 930 ms |
| Native range-read phase | 593 ms | 358 ms | 356 ms |
| Input to final neighbor ready | 5,365 ms | 6,103 ms | 5,606 ms |

Each center came from fresh decoding, with no prepared approximate center at
the final destination. These targets differ from the 256 KiB run, so this is
directional evidence rather than a controlled same-frame benchmark. All five
frames completed before auto-commit. Repeating the final target displayed its
memory-cached center in 18 ms; a TV screenshot confirms two frames on each side.
The normal release build, release lint, signature/alignment verification and
all 84 targeted JVM tests passed. Sub-500-ms uncached extraction is not achieved.
After the final seek, playback advanced another thirty seconds while warming
continued; no additional dropped-frame or audio-underrun event was logged.
One dropped frame was reported during the initial 21-second startup interval.
This short smoke check is not a controlled performance guarantee. The TV was
left paused. Installed APK SHA-256:
`36ad1e57cb0b15f98f70ab960d7e0631f4b695659d40286c2b27bcbd45db5a15`.

## Repeatable emulator checks, 29 October 2026

The TV was left untouched at the user's request. Tests use the Android 12 TV
x86 emulator with 3 GiB guest RAM and a 192 MiB app memory class. This is not
a substitute for measuring the physical TV's CPU or a real debrid connection.
The initial 1.5 GiB emulator stalled system-wide and was excluded. Android's
package compiler was run with `cmd package compile -m speed -f com.arvio.tv`
for both comparison APKs to avoid debug class-verification timing differences.

Changes:

- Native custom AVIO pulls closed loopback ranges of at most 256 KiB, rather
  than leaving an open-ended response streaming ahead. Kotlin still owns remote
  authentication, the 1 MiB upstream cache blocks, memory and network budgets.
  The demuxer and decoder survive successive seeks.
- Source preparation gets length from the validated range response, eliminating
  a redundant HEAD request. Known playback-cache length is consulted before
  that probe, preserving zero-network cache hits. Unsupported range responses
  still fail closed.
- Opening the filmstrip restores all saved neighbors directly from disk without
  waiting for decoding. This batch is not restarted by its own cache updates.
  Cached frames skip the decorative 80 ms entrance fade.

Reproducible device checks live in `KeyframeWorkerDeviceTest` and
`SeekPreviewStreamingDeviceTest`. The cold benchmark uses three new providers
and source identities, at 8, 23 and 15 seconds, with 100 ms latency per HTTP
request and a cumulatively paced 10 MiB/s response. Each sample starts without
image, byte-range or demuxer cache. It records center latency, whole-window
latency, bytes, requests and actual decoded timestamps, including near-EOF
neighbors. The upstream HTTP fixture runs inside instrumentation, not on the TV.

Generate its synthetic fixture with FFmpeg (do not commit generated media):

```sh
ffmpeg -f lavfi -i 'testsrc2=size=3840x2160:rate=24' -t 32 -c:v libx265 -preset ultrafast -x265-params 'pools=2:frame-threads=1:keyint=48:min-keyint=48:scenecut=0:bframes=0' -filter_threads 1 -pix_fmt yuv420p10le -crf 25 preview-stress-4k-main10.mkv
ffmpeg -stream_loop 1 -i preview-stress-4k-main10.mkv -c copy preview-ui-4k-main10.mkv
ffmpeg -f lavfi -i 'testsrc2=size=1920x1080:rate=24' -t 40 -c:v libx264 -preset ultrafast -threads 2 -crf 30 -movflags +faststart preview-playback-1080.mp4
```

Push those files to `/sdcard/Android/data/com.arvio.tv/files/` on the emulator.
The 32-second fixture is HEVC Main10, 3840×2160, approximately 29.7 Mb/s and
118,937,858 bytes. It is generated test content, not an actual debrid movie.
The playback check renders the separate 1080p AVC file while decoding 4K
previews, records dropped frames/rebuffers/errors, verifies five ready cards,
captures the actual Compose filmstrip and checks playback resumes after seeking.
This isolates CPU/UI interference; it does not test shared upstream bandwidth
contention or 4K hardware playback on a physical TV.

Before eliminating HEAD, the bounded reader's three cold centers took
1,176 / 898 / 935 ms, versus 1,135 / 1,051 / 923 ms with the previous native
reader under the same controlled pacing. Whole-window times were
2,155 / 4,229 / 3,878 ms versus 2,081 / 4,182 / 3,702 ms. These small samples
do not establish a substantial speed improvement from bounded reads alone.
They did remove two redundant upstream requests per window. An earlier
per-write sleep limiter unintentionally reduced bandwidth and was discarded;
it exposed near-EOF scanning taking about eight seconds in both implementations,
with the new reader exceeding its deadline in one run. Do not generalize the
faster controlled-network results to slow connections.

The final candidate also uses a 256 KiB local AVIO buffer, reducing loopback
connection churn while preserving the bounded-read limit. With the redundant
HEAD removed, the two final cold runs produced:

| Target | Previous reader | Final run 1 | Final run 2 | Final full-window run 1 / 2 |
| --- | ---: | ---: | ---: | ---: |
| 8 seconds | 1,135 ms | 957 ms | 736 ms | 1,795 / 1,680 ms |
| 23 seconds | 1,051 ms | 779 ms | 851 ms | 3,838 / 3,873 ms |
| 15 seconds | 923 ms | 744 ms | 788 ms | 3,603 / 3,589 ms |

The new windows made 6 / 14 / 13 upstream requests, versus 9 / 17 / 16 for
the previous reader. Each cold center transferred 2,545,922 bytes in both
versions. Cache lookup was 0.08–0.21 ms; this is provider lookup time, not
remote-input-to-visible latency. Final playback verification rendered 1080p
AVC for ten seconds without previews, then fifteen seconds while independently
decoding 4K HEVC previews: zero dropped frames, rebuffers or player errors in
both windows. Five cards were visible and playback resumed after seeking.
The short, separate-source test cannot establish zero playback impact for all
devices or shared-bandwidth debrid streams.

Validation: all 116 preview JVM tests and eight selected emulator tests passed.
The latter cover native bounded reads/session reuse, 4K timestamps, cancellation,
memory-pressure recovery, the cold benchmark, four TV/mobile Compose checks and
the playback comparison. The final benchmark was repeated separately. Raw local
reports are `artifacts/preview-pull-benchmark-final*.json`,
`artifacts/preview-pull-final-playback.json`, and the actual emulator screenshot
`artifacts/preview-pull-final-five.png`. Uncached sub-500-ms previews have not
been achieved; no physical-TV or real-debrid result is claimed for this change.

After the playback-cache preservation correction, all 116 JVM tests and all
eight device tests passed again. That additional cold run measured
1,106 / 900 / 863 ms for centers and 2,111 / 4,432 / 3,988 ms for whole windows;
cached lookup remained below 1 ms. Include this variance when describing results:
the candidate's observed cold range across nine samples is 736–1,106 ms, not
consistently sub-second. The repeated playback check again reported zero errors,
rebuffers and dropped frames, five ready cards, and successful seek/resume.
Final source verification evidence is `artifacts/preview-cache-preserved-*.json`,
`artifacts/preview-cache-preserved-device-tests.txt` and
`artifacts/preview-cache-preserved-five.png`.

### Adaptive window preparation (29 October 2026)

Healthy playback now prioritizes a complete five-frame window and follows recent
forward/rewind intent. Five local turns are interleaved with one broader coverage
turn; existing speculative-read limits still apply. The player checks buffer,
playback strain, lifecycle and metered-network status every 250 ms and cancels
preparation when those conditions cease to be safe. With at least 30 seconds
buffered, a batch can prepare two video frames sequentially; a smaller healthy
buffer allows one. Batches have a 1.2-second soft scheduling deadline, retain
the existing per-frame read budget and never run parallel preview decoders.
The deadline prevents starting another frame; cancellation and the decoder's
own deadline bound a frame already in progress.

Image-only sources can prepare five cues per batch with a 750-ms soft scheduling
deadline. Their adapter retains up to 8 MiB of compressed sheets/ranges in a
source-scoped LRU cache, avoiding repeated downloads when crossing a sheet
boundary and rewinding. Authentication/source isolation and existing image
validation remain in place. This uses existing provider thumbnails; it does not
add a hosted thumbnail-generation service or promise sheets for every addon.

Prepared frames still use the existing memory/disk cache and visible timestamp
validation. Arbitrary, previously uncached 4K seeks still require network reads
and decoding; they are not guaranteed to complete in 500 ms.

Final verification after rebasing onto main f1228e394: 155 selected JVM tests
passed (123 preview tests plus 32 playback/planner/host-rule tests), and all nine
selected Android TV emulator tests passed. The five-frame window was present
before browsing began, with a 1-ms provider lookup. The controlled playback test
reported zero dropped frames, rebuffers and player errors, and successful resume.
It renders 1080p AVC while decoding a separate 4K HEVC fixture, so it does not
establish shared-bandwidth or physical-TV performance.

The image-sheet fixture adds 100 ms per HTTP request. Five previews were prepared
in 338 ms; the cached window lookup took 4.50 ms. Revisiting a new cue in a previous
sheet took 11 ms without another download. Two distinct sheets were fetched once
each, with one manifest request and no video requests or video decoding.

Final cold-center times were 773 / 761 / 770 ms; complete windows took
1,639 / 3,668 / 3,527 ms under the existing controlled network benchmark.
These are small synthetic samples, not a sub-500-ms guarantee for debrid files.
Raw local evidence: `artifacts/preview-adaptive-{sheet,playback,cold}.json`,
`artifacts/preview-adaptive-device-tests.txt`,
`artifacts/preview-adaptive-rebased-build.log`, and
`artifacts/preview-adaptive-five.png` (actual five-card emulator rendering).

### Cache contention and paused decoding (30 October 2026)

Disk reads no longer share the writer's monitor. Cache entries are immutable
files published by same-directory rename, so a read can return independently of
JPEG compression and eviction. A concurrent eviction is treated as a miss, and
read failures never delete a pathname that a writer may already have replaced.
The writer retains a bounded five-entry queue instead of conflating every burst
to one entry; this preserves a complete strip without blocking delivery. Removing
the monitor exposed lost writes in the existing strip-restoration regression
test, which the bounded queue fixes.

The paused decoder mode described above retains the existing network budgets and
single-worker ownership. Playback-time preparation remains at nice 19 with two
slice threads; paused browsing uses nice 0 and up to four. The device test checks
that a switch into paused mode retains the demuxer rather than reopening it.

After rebasing onto main `7235b1525`, all 138 selected JVM tests passed (124 preview
tests and 14 voice-search tests), followed by all nine selected Android TV emulator
tests. Controlled playback recorded zero dropped frames, rebuffers and errors,
successful resume, and five prepared thumbnails with a 1-ms window lookup. This
still uses 1080p playback plus a separate 4K preview fixture. Controlled cold-center
times were 874 / 760 / 732 ms, so these changes do not establish sub-500-ms uncached
extraction. The image-sheet fixture prepared five cues in 356 ms and restored the
window in 2.34 ms. Evidence: `artifacts/preview-priority-{cold,playback,sheet}.json`
and `artifacts/preview-priority-device-tests.txt`.

Physical-TV verification used a normal, non-debuggable, release-signed ARM APK
from `f6ffdb2cc` on a TCL Smart TV Pro (Android 14, ARMv7, 192-MiB app heap).
The installed APK SHA-256 matched
`31aed5059eb7a3475d5ed9862be52849daca35fea0c640f129d31c861f9d9555`.
Source: Dune (2021), 86.41-GB 2160p HEVC/Dolby Vision P8 remux, TorBox through
Torrentio. Fresh +5/+10/+20-minute jumps produced center UI updates in
2,984 / 1,197 / 965 ms. The first request included worker/source startup.
Baseline samples on the same file at different positions were
2,894 / 1,605 / 1,758 ms; this is a small, scene-dependent comparison, not a
controlled percentage speedup claim.

The measured gaps between completed worker jobs and the next job starting fell
from 758–1,302 ms to 6–50 ms. This removes the storage contention rather than
counting a wrong-timestamp image as ready. Eighteen cached input-to-display
updates were 11–60 ms, and an actual TV screenshot confirms two real images on
each side of the center. Cold full strips still took several seconds; no
sub-500-ms uncached guarantee is established. The third jump returned five native
frames, although only four provider completion messages preceded auto-commit;
the log report deliberately retains that distinction.

Six final frame-presentation samples spread across roughly 49 seconds showed
23.98–24.01 fps, advancing timestamps, maximum individual gaps of 50.07–66.64 ms,
and no gaps over 100 ms within those samples. The full test logged dropped-frame
batches of 7, 5, 1 and 1 over separate playback intervals, so this is not a claim
of zero dropped frames or uninterrupted measurement of every frame. Playback
resumed after each jump; the app process survived and was left on details with
playback stopped. Evidence: `artifacts/tv-priority-timing.log`,
`artifacts/tv-priority-measured-report.json`,
`artifacts/tv-priority-final-steady-*.txt`,
`artifacts/tv-priority-cached-bright.png` and
`artifacts/preview-priority-release-verification.json`.

### Worker pre-start and repeated-jump preparation (30 October 2026)

After three seconds of playing without a buffering transition, the foreground
player can bind the preview service and load its native library. This preparation
does not receive a media URL, open a decoder/demuxer or request video bytes. The
service checks available memory and uses its low-priority worker. The client
coalesces preparation, bounds connection/acknowledgement waits, resets readiness
after service death, and keeps normal on-demand binding as a fallback. Live,
adaptive, extraction-disabled and provider-image paths do not use this pre-start.

The coverage planner remembers a completed forward or backward jump of one to
thirty minutes for up to two minutes. After prioritizing the current strip, one
in six healthy preparation turns may prepare a repeat of that distance and its
neighbors. The existing 128-MiB speculative budget, per-request limits and
playback health guard still apply. Smaller later gestures clear the prediction;
out-of-range targets are rejected. This is a bounded heuristic, not a promise
that arbitrary future timestamps are prepared.

Verification: 127 preview JVM tests and all ten selected Android TV emulator
tests passed. The device tests assert idempotent preparation and zero media
requests before decoding. Worker preparation during the controlled playback
test succeeded, with zero player errors, dropped frames and rebuffers, successful
resume and five cached preview images. The fixture plays 1080p AVC while
extracting separate 4K HEVC frames, so it is not evidence of full 4K physical-TV
playback performance. Controlled cold-frame samples were 753 / 851 / 829 ms
without explicit preparation and 782 / 807 / 816 ms after preparation. These
samples do not demonstrate a material speedup on the emulator; its service
process can remain cached between tests. Physical-device timing is required to
evaluate the previous first-worker startup overhead.

Evidence: `artifacts/preview-prestart-{cold,prepared,playback}.json`,
`artifacts/preview-prestart-build.log` and
`artifacts/preview-prestart-device-tests.txt`.

Physical-TV verification used the same TCL and 86.41-GB Dune TorBox remux, with
the normal signed release from `447d523b2`. The installed SHA-256 matched
`4d890418007341eeba55e5953fa527b5d4d99c1838b3e67348b6944ca3d91a17`.
The preview service was already running before the first seek, with no frame
jobs logged beforehand. First-request provider time outside the worker fell
from approximately 1,229 ms in the previous run to 244 ms here. This interval
also includes source preparation, so it is not a pure process-start benchmark.

Fresh +5/+10/+20-minute center displays took 3,312 / 2,201 / 2,278 ms. These are
slower overall than the previous run at different positions; pre-start does not
establish an end-to-end cold-preview speedup. Native source open/seek/read phases
accounted for 2,389 / 1,562 / 1,703 ms, with center decoding alone taking
541 / 502 / 440 ms. Three cached revisits displayed in 27 / 28 / 32 ms. Only
four of five cold frames completed before auto-commit on the first two jumps;
all five completed in 7,946 ms on the third. A cached revisit screenshot confirms
two images on either side. No real-TV benefit from repeated-jump speculation is
claimed: this run did not show playback-time frame preparation. Health and
resource guards remain intact.

Six advancing frame-presentation samples over roughly 41 seconds measured
23.94–23.98 fps in five samples and 23.27 fps in one, which contained a 250.26-ms
gap. Two dropped-frame batches of four were logged. Playback resumed after all
three jumps, and the app remained alive after returning to details. These checks
do not prove zero playback impact or meet the sub-500-ms uncached target.
Evidence: `artifacts/tv-prestart-timing.log`,
`artifacts/tv-prestart-measured-report.json`,
`artifacts/tv-prestart-worker-before-seek.txt`,
`artifacts/tv-prestart-final-steady-*.txt`, `artifacts/tv-prestart-cached.png`,
and `artifacts/tv-prestart-installed-sha256.txt`.

### Preparing multi-minute 4K targets during playback (30 October 2026)

The physical-TV diagnostic run established why preparation had not been running:
the 86.41-GB remux generally had only 3.7–7.6 seconds buffered. An occasional
higher sample did not satisfy eight continuous healthy seconds above the old
eight-second reserve. Native support, memory classification and unmetered network
checks all passed, but no frame preparation ran. Twelve baseline frame-presentation
samples were 23.94–24.01 fps with no gaps over 100 ms.

The isolated, low-priority native worker now uses a four-second minimum reserve,
still requiring eight seconds of healthy playback before eligibility. A new
native preparation job starts only while the main player is not loading. The
250-ms monitor cancels work when eligibility is lost. Image-provider and Media3
paths retain their eight-second threshold; low-memory and metered-device
restrictions remain. Playback-strain cooldown survives effect restarts, while
interruptions reset the healthy-playback timer. A native preparation job may
read up to eight MiB (the observed first 4K frame required 4.68 MiB); the total
128-MiB speculative budget remains. The main playback buffer is unchanged.

After the initial local strip, preparation alternates local detail with stable
distant points: +5, +10, +20 minutes, additional landmarks and neighboring frames,
then 30-second gaps followed by finer ten-second coverage. The anchor uses a
30-second grid and remains stable for up to five minutes, allowing distant strips
to finish before priority points move. A rewind or a jump beyond that window
reanchors it. This is gradual bounded coverage, not an instant full-film storyboard.

All 131 preview JVM tests pass, including moving-playhead distant-strip coverage,
budget/EOF constraints, reserve cancellation, cooldown persistence and reset.
In the first physical-TV preparation run, 47 frames were prepared before seeking,
using 81.35 MiB of additional media reads. +5/+10/+20-minute centers became ready
55.2 / 61.2 / 69.2 seconds after the first playing-state sample. Later selection
of those targets displayed timestamp-validated memory frames in 27 / 41 / 20 ms.
Actual seeks between test positions were approximately +2 / +5 / +10 minutes;
the +5/+10/+20 labels refer to the original preparation anchor. Eighteen advancing
presentation samples during preparation were 23.94–24.01 fps with a largest gap
of 66.64 ms, none over 100 ms, and no logged dropped-frame batches or audio
underruns. Playback resumed successfully afterward. These are sampled results
on one real 4K source, not a guarantee for every device, network or uncached point.

That first run used a two-minute anchor lifetime and left one neighbor at the
farthest point incomplete before seeking. The final five-minute lifetime addresses
that coverage churn; the regression test checks that all three distant strips
and intermediate coverage are prepared while playback advances.

Evidence: `artifacts/tv-ahead-diagnostic-timing.log`,
`artifacts/tv-ahead-baseline-measured-report.json`,
`artifacts/tv-ahead-timing.log`, `artifacts/tv-ahead-measured-report.json`,
`artifacts/tv-ahead-final-steady-*.txt`, `artifacts/tv-ahead-jump*.png`,
and `artifacts/preview-ahead-build5.log`.

The final five-minute-window TV run prepared 67 frames before the first seek,
with 102.68 MiB of media reads. All five images around the +5/+10/+20-minute
landmarks were ready after 84.1 / 102.3 / 124.9 seconds respectively. Those
landmarks were relative to the preparation anchor, not the later playhead.
Selections at 116:32, 126:32 and 111:32 displayed validated memory frames in
28 / 37 / 28 ms; the ten-minute jump to 126:32 showed the complete five-image
strip immediately in the captured UI. The prepared intermediate frame at 113:32
displayed in 11 ms on a controlled recheck. The initial rapid rewind's center
was visible in the screenshot but lacked a new display timing event, so that
first sample intentionally remains unquantified in the report.

Twenty-four advancing playback samples during preparation measured
23.94–24.01 fps, with maximum gap 66.69 ms and none over 100 ms. No dropped-frame
batches or audio underruns were logged in this run, and playback resumed after
the seeks. This verifies prepared-target latency on the tested remux; a new
source still needs preparation time, and an unprepared timestamp can still use
the slower extraction path. Memory, network, buffering or playback-strain guards
can defer preparation.

Final-run evidence: `artifacts/tv-ahead-complete-timing.log`,
`artifacts/tv-ahead-complete-measured-report.json`,
`artifacts/tv-ahead-complete-final-steady-*.txt`,
`artifacts/tv-ahead-complete-jump2.png`,
`artifacts/tv-ahead-complete-intermediate-recheck.png` and
`artifacts/tv-ahead-complete-resumed.txt`. These measurements used signed APK
SHA-256 `05a3c06a5a6f909c6f2deda8d8b1b9eb76afc61c50ca5c4a92cd50209f47262e`.

The implementation was rebased onto main `fb9afc168` (including the concurrent
Trakt fixes) as `b787e3d1e`. The final build passed 216 preview/Trakt JVM tests
with zero failures/errors and produced a signed, non-debuggable ARM release.
Installed APK SHA-256 was verified on the TV as
`f4f6bc3c468219f28d72624b4a1d5dc910aca26e1ebaabaadbe969635dcc4637`.

A separate smoke test on that exact APK confirmed the same 86.41 GB source,
background extraction, all five preview slots and resumed playback. Three
advancing steady samples measured 23.98–24.01 fps with no gap over 100 ms.
This short smoke test also logged 13 dropped frames during startup before
preparation and one after resuming; it is not a zero-dropped-frame claim.
An unprepared target took 1.75 seconds, then its cached recheck took 23 ms,
confirming the distinction between cold extraction and prepared display.
The longer preparation benchmark above is kept separate from this smoke test.

Evidence: `artifacts/preview-ahead-main-build.log`,
`artifacts/preview-ahead-main-release-verification.json`,
`artifacts/tv-ahead-main-installed-sha256.txt`,
`artifacts/tv-ahead-main-smoke-timing.log`,
`artifacts/tv-ahead-main-smoke-measured-report.json`,
`artifacts/tv-ahead-main-preview-ready.png` and
`artifacts/tv-ahead-main-smoke-resumed.txt`.

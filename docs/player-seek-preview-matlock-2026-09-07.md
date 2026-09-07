# Matlock physical-TV verification, 2026-09-07

The first sections record the earlier build. The retained-decoder/automatic-seek retest
at the end is the latest result; neither run establishes universal instant previews.

## Setup

- TCL Smart TV Pro G08, existing Arvind profile and configured sources.
- Installed 1.9.996 (312), built from `808468854`, over 1.9.996 (311).
- Verified the installed and replacement APK share the release certificate.
- In-place update only: no uninstall or data clear; all three profiles remained.
- Matlock S1E10, "Crash Helmets On", duration 42:35. The preview codec reported
  1920x1080 AVC. The selected addon name was not independently recorded.

## Results

| Check | Observation | Result |
| --- | --- | --- |
| Quick timeline, 04:02 | A real scene different from paused playback appeared | Functional pass; cold delay |
| Commit 04:02 | Main playback subsequently showed the scene in the preview | Visual scene-match pass |
| Main timeline, 06:02 | A different real scene appeared after a deadline/retry | Functional pass; latency fail |
| Backward main timeline, 03:22 | Repeated deadlines and no initial frame; eventually a real earlier scene appeared | Recovery/latency fail |
| Existing account data | Arvind, Shai., Leyla remained available | Pass |

The successful screenshots are not evidence of instant cold extraction. Exact input-to-display
latency was not instrumented. The 03:22 image was only confirmed on a much later screenshot,
not within a usable interactive deadline. No universal-preview or release-readiness pass.

Warnings captured from the physical device:

```text
11:58:54.492 targetMs=362448 elapsedMs=6004 status=UNAVAILABLE reason=deadline
12:00:46.598 targetMs=202448 elapsedMs=6004 status=UNAVAILABLE reason=deadline
12:00:57.681 targetMs=202448 elapsedMs=6014 status=UNAVAILABLE reason=deadline
12:01:33.763 targetMs=202448 elapsedMs=6005 status=UNAVAILABLE reason=deadline
```

## Local screenshot evidence

- `C:/Users/arvin/arvio-matlock-quick-waited.png`: quick preview at 04:02.
- `C:/Users/arvin/arvio-matlock-committed.png`: matching scene after commit.
- `C:/Users/arvin/arvio-matlock-main-forward.png`: main preview at 06:02.
- `C:/Users/arvin/arvio-matlock-main-backward.png`: initial backward failure.
- `C:/Users/arvin/arvio-matlock-current.png`: eventual backward frame at 03:22.

## Follow-up investigation

The provider waits for an unfinished `inFlight` future before submitting another decode.
Media3 1.9.0 serializes extraction and resource release through a shared execution sequencer;
`FrameExtractor.close()` queues release rather than interrupting active extraction. Therefore
clearing the local future alone would not safely reset the underlying decoder. Do not apply
that apparent fix without covering cancellation and actual decoder recovery.

Investigate bounded decoder lifecycle/reuse and recovery, with a physical cold/backward-seek
regression test. Raising deadlines alone has not met interactive performance requirements.

## Additional automated coverage

`PlayerSeekPreviewVisualDeviceTest` exercises the production PlayerScreen with a three-scene
local clip: TV quick forward/backward and phone touch forward/backward. One test passed on
each emulator (TV 11.046 seconds; phone 13.433 seconds). The test checks scene colors distinct
from the paused main video. These fixture results do not supersede the physical-TV failures.

## Retained decoder and automatic seek retest

Implementation commits: `2c7b5703d` and `bc1f82497` on the preview-fix branch.
Signed test APK: release worktree `916f39980`, targeted build on `808468854`, not a
snapshot of every subsequently merged main commit. Version 1.9.996 (312), 95,968,931 bytes.
Release certificate verified against the existing APK; installed in place, profiles preserved.

Changes retain an outstanding Media3 operation when its UI waiter is cancelled, reuse the
decoder and its range lease between requests, cache valid late frames, and release idle work.
Remote browsing now commits after two seconds without new seek input. OK commits sooner,
Back cancels, and deliberately paused playback remains paused. Touch release still commits.
The old exit-animation callback can no longer clear a newer seek interaction. Both TV preview
panels are adjacent to the timeline; phone spacing is reduced too.

### Automated results

- All 83 focused preview JVM tests passed, including six decoder-session lifecycle tests.
- TV emulator: four device tests passed in 33.826 seconds.
- Phone emulator: four device tests passed in 19.174 seconds.
- Both runs exercise actual Media3 local/range-HTTP decoding and the production PlayerScreen.
  TV verifies delayed forward/reverse commits and playing-versus-paused intent; phone verifies
  real blue/green preview scenes while the primary video remains on the red scene.
- Phone measured range decoding: 1,808 ms cold, then 241 ms retained; local: 941 ms then 252 ms.
  These are controlled fixtures, not Matlock timings or a physical-TV latency guarantee.

### Physical TCL results, approximately 13:27-13:40 local time

- Matlock S1E10: autoplay selected a 4K source displayed as 4.99 GB. Forward and reverse
  input committed without OK, but no useful preview arrived in the two-second idle window.
  Repeated browsing also initially showed empty panels. **Cold 4K preview test failed.**
- Selected the displayed Flix-Streams Debrid Vault Turbo 1080p/H.264 source, 3.17 GB,
  duration 42:39. Cold access still required waiting/revisiting the target.
- Real previews subsequently appeared at 06:40 and 06:50, distinct from the paused background,
  on both quick and main timelines. Warm main-timeline screenshots captured them before
  auto-commit. **Warm availability passes; not evidence of instant cold access.**
- Main and quick controls applied their targets automatically, preserving intentional pause.
  No new crash observed. Restored approximately 00:22 and exited playback before standby.
- The 4K and 1080p sources differed from the earlier 42:35-duration source; this is not a
  controlled before/after performance comparison. No exact physical input-to-preview timings
  or all-source release-readiness pass is claimed.

Latest local evidence:

- `C:/Users/arvin/preview-retained-matlock-forward.png`: cold 4K pending panel.
- `C:/Users/arvin/preview-retained-matlock-forward.mp4`: quick input and automatic commit.
- `C:/Users/arvin/preview-retained-matlock-1080-browse.png`: real warm quick preview at 06:40.
- `C:/Users/arvin/preview-retained-main-0640.png`: real warm main preview, reverse.
- `C:/Users/arvin/preview-retained-main-0650.png`: real warm main preview, forward.
- `C:/Users/arvin/preview-retained-phone-evidence/phone-preview-forward-blue.png`: phone fixture.
- `C:/Users/arvin/preview-retained-restored.png`: restored playback position.

Remaining work: bound cold remote extraction latency on low-memory TVs, especially 4K HEVC.
Do not blindly switch all devices to hardware decoding: the pinned Media3 1.9.0 implementation
documents hardware flush/seek crashes with video effects. Precomputed provider previews remain
the predictable fast path; arbitrary unprepared remote video does not have the same guarantee.

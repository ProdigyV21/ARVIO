# Player Seek Preview Review and Implementation Plan

Reviewed: 2026-09-05. Android source: `9a1e5c84c696addf9d48c623a447b430ef1abaa9`, matching GitHub main at review time.

This is a source review and implementation proposal, not a new TV performance test. No player code was changed. Existing test coverage was inspected, not rerun. Proposed timings below are acceptance targets, not achieved measurements.

## Assessment

The current feature is not ready to promise fast, reliable previews across ARVIO's sources. It has real UI and request-lifecycle defects in addition to the inherent cost of decoding frames from arbitrary remote media. Increasing timeouts or thumbnail resolution alone will not solve it.

Instant cached thumbnails are feasible. Consistently instant first-time jumps anywhere in an arbitrary remote 4K remux are not guaranteed without an existing thumbnail track or prior preparation. No ARVIO-hosted media is required: use images already supplied by a user's server/source, then bounded local extraction where supported.

## Confirmed Findings

1. **Unsupported previews appear to load forever.** `PlayerScreen.kt:1579` disables new extraction for constrained TVs and heavy TV streams. The constraint is a TV heap class of 384 MB or less, or a low-RAM designation (`:332`). The UI still draws a loading thumbnail (`:4501`, `:4519`). Since there is no prefetch or active rendered-frame capture in this revision, an uncached position has no path to become ready on those configurations.

2. **The UI itself creates flicker and a jump to the start.** Both seek paths clear the bitmap on a cache miss (`:1611`, `:1643`, `:1752`, `:1805`). Closing quick seek sets its position to zero immediately (`:1727`) while its exit animation lasts 120 ms (`:4474`). The still-composed overlay can therefore render the zero position during its fade. The 20-second preview threshold controls extraction, but not the thumbnail box, so a single 10-second skip still draws a spinner.

3. **Seeking is coupled to thumbnail latency.** Quick seek waits up to 850 ms for a preview (`:1763`); the full control bar uses a separate 700 ms commit timer (`:1809`). A warmed frame can make quick seek commit immediately even during repeated input. Main-bar commit drops the scrub state before the next sampled player position, allowing the UI to briefly return to an older position. Timing and cancellation differ between the two controls.

4. **Reported thumbnail timestamps do not validate the picture.** `SeekPreviewFrameProvider.kt:363` discards Media3's `presentationTimeMs`; `:267` assigns the requested 10-second bucket to the result. A closest-keyframe result can depict a different time while every UI timestamp check succeeds. The native retriever fallback similarly returns a bitmap without a verified presentation timestamp. This is a correctness gap, not proof that every result is wrong.

5. **Timeouts do not reliably stop extraction.** Blocking Future waits, native retriever calls and synchronous HTTP run on a single executor (`SeekPreviewFrameProvider.kt:232`, `:361`, `:585`). The outer coroutine timeout cannot preempt every blocking operation; active HTTP calls are not cancelled when a target becomes obsolete. A Media3 timeout also permanently switches that session to the native fallback. Rapid scrubbing can spend time finishing work the user no longer needs.

6. **The first visit to each position is cold.** Background extraction was removed (`PlayerScreen.kt:1653`). That avoids the previous queue backlog but leaves no preparation strategy. There are no server trickplay, BIF, WebVTT storyboard, or image-track adapters in the current preview path.

7. **Preview networking is inconsistent with playback.** Media3 receives a URL but not ARVIO's configured playback HTTP client, cookie jar or data cache. Explicit custom headers force the native range path, but cookie-only authentication is not detected by that gate. The platform HTTP fallback has a different request implementation again. This can explain source-specific failures even when playback works.

8. **Presentation and cache coverage need work.** Main controls lack the quick bar's white thumb (`PlayerScreen.kt:4219`). The actual thumbnail uses `ContentScale.Fit` (`:6516`), so current cropping must be checked against decoded pixels and source display geometry, not assumed to be a Compose crop. Extraction is capped at 480x270. Aspect-ratio helpers for non-square pixels are tested but are not used by the current retrieval path. Disk images persist no actual timestamp metadata; the 128 MB eviction pass only runs at provider creation. Fallback identities based on URL paths can also miss media-version differences encoded in query parameters.

## Intended Interaction

- One short left/right press skips 10 seconds with immediate feedback and no thumbnail panel.
- Holding or repeated input enters preview browsing after the 20-second threshold. The target moves immediately and preserves its position during direction changes.
- In preview browsing, Select commits once and Back cancels the uncommitted target. Pause/play state is restored correctly. Browsing does not repeatedly seek the main player as images arrive.
- The full control bar uses the same target, preview, commit and cancel logic. Touch dragging commits on release; gesture cancellation does not commit.
- Keep the white thumb on both bars. Use a stable image container above the track, restrained white outline, and aspect-fit content. No extra time strip under the image and no large dark background slab. Keep time available on the timeline itself.
- Cached images swap directly. A short pending state must not flash a spinner for each key repeat. A previously displayed frame may remain briefly only at its previous known position, never relabelled as the new target. If no suitable image exists, use an honest pending/unavailable state and keep seeking responsive.
- Hide the preview area for sources that cannot supply previews; do not imply that an impossible request is still loading.

## Implementation Order

### 1. Establish measurable accuracy and repair seek state

Introduce one small, testable seek controller used by both existing UI surfaces. Model idle, quick skip, browsing, committing and exiting. Hold the last displayed target through the exit animation and through the player seek acknowledgement. Keep thumbnail completion independent of whether playback commits.

Add source-generation and request identifiers. Every result must carry source identity, requested time, actual frame time or cue interval, origin (server/disk/memory/decoder), and validity. Discard obsolete results from a prior source or seek request. Preserve cancellation as cancellation, not a failure that triggers a fallback.

Stop storing a requested timestamp as evidence of frame accuracy. Retain Media3's actual timestamp; for native paths without one, classify the result as approximate and validate that path with known-time fixtures. Reject out-of-window results and report them in diagnostics. Version the cache so old unvalidated bitmaps cannot contaminate new tests.

### 2. Add previews already provided by a source

Create one preview-source contract with capability discovery outside the playback critical path. Try existing provider images before starting any extra decoder:

- Jellyfin trickplay metadata and image tiles, matched to the selected media source/version.
- Plex video preview thumbnail indexes when the server has generated and exposes them.
- Emby preview indexes where available, with version and authentication checks.
- Source-provided WebVTT image cues, sprite sheets or supported HLS/DASH thumbnail tracks when explicitly advertised. Do not assume ordinary stream addons provide a standard storyboard field.

Pass stable server/item/version references through playback selection rather than guessing them from a signed playback URL. Preserve timelines across transcode offsets, resume and source changes. Fetch small manifests and image tiles directly from the source with its required authentication. Missing thumbnails must not block playback or trigger an expensive server regeneration job.

### 3. Replace fallback scheduling and make networking consistent

Maintain at most one active extraction and one replaceable latest-target request. Cancel cancellable network/decoder work on target changes and source disposal. Discard late results. A native call that cannot be interrupted must not accumulate a queue or cause multiple abandoned workers to be created.

Evaluate the media API against the pinned dependency before selecting an adapter: Media3 1.9.0's FrameExtractor does not expose an injectable data-source factory. Use a maintained pipeline that can preserve playback authentication, cookies, byte-range semantics and relevant media cache access; do not assume a version upgrade alone supplies that contract.

Validate HTTP 206 offsets and lengths, handle HEAD failures and 416 responses correctly, bound response reads, and stop on unsupported ranges. Use source-level deadlines and circuit breaking instead of repeatedly trying several slow extraction engines for each target. Adaptive streams need supported segment/index handling, not a generic native-retriever promise. Protected streams without an authorized thumbnail track fall back to normal seeking.

### 4. Prepare useful thumbnails without harming playback

Only after playback is stable, warm a small neighborhood around the current/resume position. Prioritize the user's current target, then the direction of travel, then nearby reverse positions. Deduplicate requests. Expand coverage gradually only for sources and devices that demonstrate spare capacity.

Prefetch cheap provider image tiles more broadly. For on-device video extraction, enforce time, network and memory budgets; suspend it when playback buffers, drops frames or the device is under memory/thermal pressure. Do not launch a second full-resolution decoder just because the UI needs a small thumbnail. Replace filename-based heavy-stream decisions with measured capability where feasible, while retaining conservative playback protection.

Use bounded decoded-image memory and persistent per-source thumbnail metadata. Enforce disk eviction during writes, not just startup. Existing bitmap caps are a starting budget, not a reason to unconditionally allocate more RAM. Key caches by verified media identity and source version, with profile/account separation where required.

### 5. Finish visual quality and failure behavior

Choose decode size based on the actual preview's rendered pixels and the device budget, typically 480 or 640 pixels wide. Validate 16:9, cinema-width, 4:3, rotated and anamorphic media with edge-marked reference footage. Preserve the full frame. Verify HDR-to-SDR appearance and hardware-decoder compatibility on physical TVs; the Media3 1.9 source documents decoder/color limitations.

Keep preview movement tied directly to input; reserve short animations for panel entry/exit. Make the thumbnail and thumb positions share the same track geometry and clamping. Source switching, cancellation and unavailable previews must leave no stuck spinner, stale image or layout jump.

## Verification and Release Gates

Use a timestamp-burned-in test video with distinct scenes and marked frame edges. Tests must assert the visible scene/time and geometry, not simply that two bitmaps differ. Test cold and warm caches separately, clearing synthetic fixture caches between relevant tests.

| Area | Acceptance target / requirement |
| --- | --- |
| Input response | Target and thumb respond within 50 ms at p95 on supported TV test devices. |
| Warm preview | Correct cached image displayed within 100 ms at p95, measured input-to-display. |
| Provider image path | Report cold manifest/tile timings separately; once necessary tiles are ready, meet the warm-preview target. |
| Cold decoder path | Report median/p95/max and success rate per source/device. Do not claim it is instant. Slow/unsupported requests degrade cleanly without blocking the seek. |
| Correctness | Zero stale-source images or falsely labelled current-screen captures. Every shown image lies in its validated cue/time window; log actual error for decoder frames. |
| Interaction | No jump to zero, no repetitive loading flashes, no decoder queue buildup, and one commit per confirmed browsing session. |
| Geometry | All marked frame edges visible for every tested aspect ratio; no unintended crop or stretch. |
| Playback | Paired preview-on/off runs show no reproducible preview-induced buffering or decoder failures; investigate any dropped-frame increase. |
| Resource limits | Memory remains bounded through repeated seeking and source changes; disk budget is enforced during use; background work stops after leaving playback. |

Test progressive MP4 and MKV over range HTTP, a large HEVC remux, HDR/Dolby Vision where supported, HLS/DASH VOD, authenticated Jellyfin/Plex/Emby, local media, and catch-up VOD with timeline offsets. Include missing cues, rejected ranges, expired URLs, slow responses, sparse keyframes and absent preview metadata. Verify provider connection limits are not disrupted by background extraction.

Exercise single 10-second taps, repeated taps, held acceleration, 5-minute jumps, rapid reversals, beginnings/endings, paused playback, full-bar/quick-bar transitions, Back/Select, source changes, next episode and mobile drag cancellation.

Use emulator fixtures for determinism, then physical TCL plus a lower-resource TV/Fire TV device and a phone for real decoder behavior. At least one genuine home-server source and one genuine remote file must be tested; unavailable services remain explicitly unverified. Record screenshots/video, input-to-image timing, true frame timestamps, cache hits, bytes fetched, queue depth and playback counters. Keep routine telemetry local or sampled to avoid filling Sentry.

## Scope and Recommendation

Implement the correctness and shared seek-state fixes first, then native server thumbnails and the fallback scheduler, followed by resource-aware preparation and visual polish. Do not ship another release labelled instant based only on two synthetic images or an already-warmed position. Deliver the UI fixes even on sources without preview support, with the capability limitation represented honestly.

This review covers the Android player. If the same feature is later added to the web player, reuse the provider metadata contract and behavior rules, but validate browser decoding, CORS and authentication separately.

## Primary References

- Media3 1.9.0 FrameExtractor source, including actual frame timestamps, decoder defaults and color caveats: https://github.com/androidx/media/blob/1.9.0/libraries/inspector/src/main/java/androidx/media3/inspector/FrameExtractor.java
- Jellyfin TrickPlay API: https://kotlin-sdk.jellyfin.org/dokka/jellyfin-api/org.jellyfin.sdk.api.operations/-trick-play-api/index.html
- Plex video preview thumbnail availability and generation: https://support.plex.tv/articles/202197528-video-preview-thumbnails/

# Browser playback verification

## Automated checks

From `web`, run `npm ci`, `npm test`, `npx tsc --noEmit --incremental false`, and
`npm run build`. The tests cover playback routing, M3U request headers, home-server
negotiation/session ownership, transport recovery and teardown, remux cancellation,
rapid seeks, bounded buffering, and long-GOP fragment backpressure.

Home-server tests use API fixtures, not customer servers. They include exact Plex
version/part selection, Jellyfin/Emby PlaybackInfo profiles, original-account session
credentials, failed-start retries, stale results, StrictMode and source changes.

## Interactive reproduction

1. Install FFmpeg with libx264, AAC, E-AC-3 and DTS encoding support.
2. Run `powershell -File tests/create-playback-fixtures.ps1` from `web`.
   For HEVC Main10, VP9/Opus and AV1/AAC also run
   `powershell -File tests/create-codec-fixtures.ps1` (FFmpeg with those encoders).
3. Install `netlify-auth-site` dependencies with `npm ci` in that directory. The
   fixture server uses its existing esbuild package; alternatively set `ESBUILD_PATH`
   to an installed esbuild module's absolute path.
4. From `web`, run `node tests/playback-ui-server.cjs`, then open
   `http://127.0.0.1:3099`. Select each source, seek forward/back, switch audio,
   pause/resume and close. The page displays decoded dimensions, frame count,
   playhead, audio RMS, buffered ranges and errors.
5. For the actual ARVIO overlay, start the normal dev server with
   `ARVIO_UI_FIXTURES=true`, open `/dev/stabilization` and use the MKV/HLS test
   buttons. This route is disabled outside development.

Generated media stays in the ignored `.playback-fixtures` directory, outside
`public`. It contains only generated test patterns and tones, not hosted movies.

## Results recorded on 2026-09-08

- Chromium browser, 960x540 synthetic fixtures: E-AC-3 video/audio conversion played
  with nonzero audio RMS; measured probe 140 ms, first frame 714 ms in one run.
- DTS conversion: probe 376 ms, first frame 1,582 ms; at 31 seconds, 749 decoded
  video frames, readyState 4, audio RMS 0.0625 and no error.
- Silent MKV: probe 164 ms, first frame 361 ms; at 73.7 seconds, 1,772 decoded
  frames and no error. Absence of audio is valid, not a playback failure.
- Actual ARVIO overlay: MKV playback, +30/-30 seeking, English-to-Dutch audio
  selection with position retained, pause, close and switch to HLS passed.
- HLS quality selection and diagnostics worked in the overlay. The settings panel
  was visually checked at a 390x844 mobile viewport.
- Transport fixture: actual HLS playback/seek/reload/pause, DASH playback/quality/
  seek/reload, and MPEG-TS VOD playback/teardown passed. Adaptive audio switching
  and return-to-live behavior also have API-mock coverage, but were not tested
  against a real multitrack live provider.
- A development hot-reload run was interrupted; the clean overlay run was repeated
  without edits during playback. No production-duration soak was performed.

These are local fixture timings, not Internet startup guarantees. Real Plex,
Jellyfin and Emby accounts, Safari/iOS hardware, provider outages and large remote
remuxes still need provider/device acceptance tests. Do not describe fixtures as
proof that every server or codec works.

## Mayday investigation (2026-09-08)

- The selected 23.27 GB MP4 was probed as 3840x1606 HEVC Main 10, Dolby Vision
  profile 8 with HDR10-compatible base-layer signalling. This is not profile 5;
  a file-size or "4K" label alone cannot establish browser compatibility.
- Before the fix, a separate 16.39 GB non-DV Mayday source failed with the
  unflushed-video memory guard. Video could run ahead of audio conversion and
  queue multiple GOPs. The worker now paces both tracks together while retaining
  the 24 MiB guard; tests exercise high-bitrate video, delayed audio, gaps and
  early audio EOF using the real MP4 muxer.
- After the change, the Chromium E-AC-3 fixture rendered picture and nonzero
  audio RMS (probe 323 ms, startup 1,854 ms in one run). Backward seek to 12 s and
  forward seek to 65 s rendered the requested timeline and completed playback.
- Missing-video checks now use frame presentation telemetry, not just dimensions.
  Tests cover known dimensions with zero frames, dropped frames, legitimate black
  frames, pauses, buffering, background tabs, source cleanup and one-time recovery.
- These checks do not establish that the exact 23.27 GB provider-converted source
  plays correctly. Provider conversion failed during investigation; no new video
  decoder or Dolby Vision tone mapper was added to the browser.

## Operational limits

No video relay/transcoder was added to Netlify. File repackaging and supported
audio conversion run in a browser worker. Unsupported video codecs require an
authorized home-server/provider conversion route or an external player. Browser
HTTPS, CORS, byte-range and codec restrictions still apply. Media forwarding is
hard-disabled on Netlify and in production, regardless of the optional local
development flag. No automatic probing of every source or background torrent
download is performed.

## Shared codec changes (2026-09-08)

The route does not depend on a title, genre, movie/series/anime classification,
or file size. It uses source metadata, selected-track configuration and the
browser's codec support. Plex/Emby/Jellyfin can supply authorized conversion;
that does not imply every home server or debrid plan permits transcoding.

- HEVC Main10 MP4: live Chromium probe 210 ms, startup 370 ms, picture and audio;
  forward seek to 65 seconds completed playback without error.
- VP9/Opus WebM: probe 263 ms, startup 381 ms; 1,026 frames at 42.6 seconds,
  audio RMS 0.0625 and no error.
- AV1/AAC MP4: probe 171 ms, startup 348 ms; 914 frames at 37.9 seconds,
  audio RMS 0.0623 and no error.
- E-AC-3 MKV conversion: probe 145 ms, startup 1,020 ms; 1,763 frames at
  73.3 seconds, audio RMS 0.0625 and no error.
- DTS MKV conversion: probe 280 ms, startup 1,216 ms; 758 frames at 31.4
  seconds, audio RMS 0.0625 and no error.

These synthetic local tests validate the shared playback engine, not every
Internet provider, device, 4K bitrate or HDR display. The direct Torrentio
redirect URL failed CORS in the minimal fixture page, which intentionally does
not include the app's debrid-resolution API. Production provider tests must use
the full app and its resolved CDN URL.

Dolby Vision metadata checks run in a cancellable child worker with an 8 MiB,
32-request and 10-second budget, directly against the selected provider file.
Only verified HEVC profile 8, compatibility ID 1, base layer present and no
enhancement layer is eligible for HDR10-base extraction. Profile 5, profile 7,
unknown/ambiguous configurations and unsafe packet layouts are not stripped.
No Dolby Vision-to-SDR tone mapper or general software video encoder was added.

Source warnings distinguish unsupported browser playback, provider conversion,
browser preparation and unverified playback. Selected-file failures are retained
only in bounded tab memory for 10 minutes, never cloud-synced. Debrid URL caches
are account/file scoped, bounded and single-flight; a missing requested episode
cannot fall back to the largest file. Merely browsing sources performs no debrid
resolution or torrent creation.

## Production acceptance status

Commit `901834b32d2fdce9ee59c2cbf8f3b6c0199ac798` was deployed to
`https://web.arvio.tv/`. GitHub Actions run `34245573964` passed its tests,
build, deployment and live build-stamp verification. The final local suite
passed 407 tests with no failures, skips or TODOs; TypeScript and the production
build also passed.

The signed-in production profile, Mayday details and its source picker opened.
The browser automation connection then repeatedly detached before a source
could be selected and measured. That is an incomplete acceptance test, not
evidence of either successful movie playback or a player crash. The exact
23.27 GB Mayday source and real series/anime provider sources remain unverified
on this deployment. The synthetic codec measurements above must not be
represented as those real-source tests or as cross-device certification.

## Browser tracking regression checks (2026-09-08)

The browser player already had tracker event hooks, but Trakt scrobbles used
the bulk history payload (`movies` / `shows`) instead of a singular `movie` or
`show` plus `episode`. That request format is now corrected. Scrobbles validate
TMDB and episode identifiers; unmatched private home-server IDs are not sent to
trackers as unrelated TMDB titles. Existing home-server playback reports remain
separate from tracker scrobbles.

Player events are tested for start, pause, resume, completion, explicit close,
background/foreground and profile/account changes. The app retains its 90%
completion threshold: closing at or above 90% sends stop and saves watched
state; earlier closes send pause and remain resumable. Seeking and periodic
cloud checkpoints do not create tracker heartbeats. Conversion start offsets
are included in both cloud progress and tracker percentages.

Simkl's 20.5-second write queue now retains a completed episode before the next
episode starts, coalesces quick play/pause changes, serializes slow requests,
bounds pending entries and reports actual delivery failures. It expires cached
library freshness after completion without discarding the metadata or delta
watermark. Profile changes cancel queued writes. Both provider write toggles
are tested independently of watchlist/Continue Watching read preferences.

The final suite passes **438 tests**, including **31 new tracking regressions**.
TypeScript and the production build pass. Tests execute the actual player
effect and tracker/router methods with mocked provider responses. No synthetic
watched history was inserted into a customer's live Trakt or Simkl account.

Small dispatched scrobbles use fetch keepalive. This is not a durable offline
outbox: browser termination can still lose unsent queued events, and service
outages, expired credentials or unknown titles can prevent tracker acceptance.
The other device must use the same ARVIO profile/connected tracker account and
refresh its sync before displaying the new state. No instantaneous cross-device
or crash-safe delivery guarantee is implied.

References: [Simkl scrobble lifecycle](https://api.simkl.org/guides/scrobble),
[Trakt movie scrobble schema](https://github.com/trakt/trakt-api/blob/master/projects/api/src/contracts/scrobble/schema/request/movieScrobbleRequestSchema.ts).

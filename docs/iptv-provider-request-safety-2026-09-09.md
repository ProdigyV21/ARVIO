# IPTV provider request safety audit

## Finding

Confirmed unsafe request amplification in the code. This is a plausible cause of
provider blocks, not proof of any particular provider's ban decision. The passive
TV log capture did not retain enough IPTV traffic to establish the actual cause.
The initial automated tests did not contact a customer's provider. The separate
authorized TCL follow-up below uses the TV's existing playlist configuration.

- Broad short-guide fallback could walk thousands of channels. Empty responses
  could trigger additional endpoint variants for each channel.
- Guide concurrency was limited per repository, but catalogs, XMLTV downloads,
  portal calls and playback probes did not share that protection.
- Catch-up resolution could examine 40 URLs, with a second GET for several HTTP
  errors: up to 80 probe requests before player-level retries.
- The web client could retry a rejected request through the proxy and repeat an
  already attempted Xtream API fallback. Guide calls were not coalesced.
- The old live stream remained running while replacement catch-up probes opened.
  Built-in IPTV VOD also allowed a secondary video decoder for seek previews.

## Changes

- Shared Android metadata/preflight budget: two open responses per provider,
  500 ms between starts, at most 30 HTTP request starts per rolling minute.
  Default HTTP/HTTPS ports, profiles and accounts on the same hostname share it.
  Known deferrals are rejected before establishing a new connection. Redirects
  and OkHttp follow-up requests also pass through the network guard.
- Stalker catalog pagination waits for the budget rather than dropping later
  pages. A failed page does not return a successful partial catalog. Initial
  endpoint discovery reuses its successful handshake token.
- Pause metadata requests after authentication/access rejection, rate limiting
  or server overload. Honor numeric and HTTP-date Retry-After headers. Prevent
  Media3 and UI retry loops after provider rejection. Normal video segments do
  not consume the metadata quota; a failed guide does not interrupt healthy video.
  A forbidden individual stream does not prevent selecting another channel;
  HTTP 429/503/513 apply a provider-wide playback pause.
- Replace broad guide scans with XMLTV plus bounded short-guide work. The
  background short-guide batch is at most 24 channels. Reuse guide identifiers
  for variants and avoid refetching equivalent automatically discovered XML feeds.
- Repeated/cancelled guide URL attempts retain a two-minute cooldown. A deferred
  request is an error, not an empty guide warranting the ten-minute empty-feed TTL.
- Catch-up tries at most three new candidate URLs per resolution attempt and
  stops on provider rejection. Only HTTP 405/416 allow a range-free retry.
- Stop/clear the old TV player before resolving its replacement. Built-in IPTV
  VOD does not open an extra video connection for thumbnail extraction; provider
  images and already captured previews remain supported.
- Web metadata requests share equivalent pacing/quota/cooldown protection;
  coalesce duplicate API calls, cache catalog results and briefly cache guide
  results including empty responses. CORS fallback still works, but an observed
  HTTP rejection does not cause immediate proxy retries. The proxy forwards
  Retry-After to its caller.

## Verification

Tests use local HTTP fixtures or mocked providers, never subscription endpoints.

- Android fixture: 5,000 distinct guide attempts admit only 30 HTTP requests and
  30 TCP connections within the simulated first minute; requests resume later.
- Hold two response bodies open: a third metadata request is deferred until one
  closes. A 65-page portal catalog completes across budget windows.
- HTTP 401/403/429/451/500/503/513 stop follow-up metadata requests. Test native
  OkHttp HTTP 503 retries, numeric/date Retry-After and Media3 retry suppression.
- One hundred healthy video segments incur no metadata pacing; a failed XMLTV
  response does not stop healthy playback. Actual media rejections stop retries.
- Web fixture: 5,000 guide attempts obey the quota, 50 overlapping identical
  calls coalesce, empty guides are cached, CORS/HTML fallback remains functional,
  and GET/POST proxy routes preserve Retry-After without caching rejections.

Final verification on 2026-09-09:

| Check | Result |
| --- | --- |
| `:app:testSideloadDebugUnitTest` | 887 passed, 0 failed, 1 existing skipped |
| `:app:testPlayDebugUnitTest` | 888 passed, 0 failed, 1 existing skipped |
| Web `npm test` | 515 passed, 0 failed |
| Web `tsc --noEmit --incremental false` | Passed |
| `git diff --check` | Passed |

Both Android build variants compiled. Existing Kotlin warnings and a Windows
Robolectric temporary-directory cleanup warning remain; the test build exited 0.
The two Android totals include executions of shared tests in both variants.

## Limits and deployment

These are conservative client-side limits, not a provider-approved universal
quota. Independent devices/browser tabs, provider aliases and other apps do not
share one budget. Cooldowns are in memory and reset with the process. Image/logo
downloads and ordinary video segments are not charged to the metadata quota.
There is no claim that this prevents every ban or reverses an existing block.

Slower background per-channel fallback is intentional: full coverage comes from
the bulk XMLTV feed and local index. A first XMLTV download is still subject to
the provider's bandwidth and feed completeness. Cached channels/guide are not
deleted or truncated by these limits.

No production web deployment, GitHub release or push was made for these changes.

## TCL follow-up, 2026-09-09

Installed the optimized sideload release 1.9.996 (312) in place on the existing
TCL Smart TV Pro. Compared its signing certificate with the APK pulled from the
TV before installation: both SHA-256 certificates are
`9778d7533d4bc1aee80c1d2d7043fb22cba3b79cd11911d3b131c1316d5f17c1`.
No uninstall, app-data/cache clear, or playlist re-import was performed. All
three existing profiles remained present.

Initial observations, before the two follow-up corrections:

- Cold activity launch: Android reported 3,397 ms. This is not an end-to-end
  profile or guide first-paint measurement.
- TV entry at 11:51:10 UTC: a screenshot completed at +3,209 ms showed the
  108,145-channel index, the selected 21-channel NL category, and real EPG blocks.
  This used existing caches, after opening the profile. It does not measure a
  fresh provider import, prove every channel's EPG, or claim 108k rendered rows.
- First local guide query matched 11/18 channels in 57 ms. Remaining channels
  were a mix of populated guide and missing/pending data; full coverage did not pass.
- Favorites: the existing single favorite and its guide were visible by +2,970
  ms after selection; the local query matched 1/1 in 32 ms.
- Remote navigation moved channel 16 to 1, then 1 to 13, with no stuck focus or
  reset to the beginning. The category drawer closed/reopened correctly.
- The first scroll sample reported 69/534 janky frames (12.92%, p50 23 ms,
  p90 44 ms, p99 61 ms). Video had frozen during this sample, so this is NOT a
  live-playback scrolling acceptance result. The sample also includes two still
  captures. Do not compare it directly with a video-active run.
- NPO 1 and Ziggo Sport both initially hit unsupported-container recovery and
  subsequently produced real video. Both then encountered a playlist reset;
  recovery discarded the detected HLS type and failed again as a progressive
  stream. Moving screenshots before that failure do not establish stable playback.
- A warm-up log requested 240 guide channels despite the large indexed playlist.
  The shared network guard still applies, but this unnecessarily competes with
  visible-guide work for the bounded quota.

Corrections prompted by the physical test:

- Consult successfully probed playback targets before the known-extension fast
  path, so an HLS response behind a `.ts` URL retains its detected format when
  selected again. Extended the resolver regression test to cover cached reselect
  without extra probes and an explicit forced re-probe.
- Bounded live HLS recovery reuses the prepared HLS target instead of resolving
  it back to a guessed progressive stream. Provider-rejection pauses, catch-up
  handling and retry limits are unchanged.
- Warm-up checks the actual paged-store size, not just its 240-row first-paint
  window. Small-list background network warming is capped at 24 priority IDs.

Raw logs and screenshots are retained locally in
`C:/Users/arvin/ARVIO-provider-tv-test-2026-09-09`; provider URLs in those raw logs
must not be published. Screenshot completion times are upper bounds, not exact
render timestamps, and device/host wall clocks were not synchronized.

### Corrected-build retest

`testSideloadDebugUnitTest` and `assembleSideloadRelease` completed successfully
in 7m 55s, including R8 and release lint. The rerun had 887 passed, zero failed,
and one existing skipped test. The Play variant and web suites listed above were
run before these three small Android follow-up changes, not rerun afterward.

Installed APK SHA-256:
`5EC858319A583FDE9B63D5293DDB63BADA1806282A3F6E0776848CD9B828BE7C`.
Certificate still matched; version 1.9.996 (312), target SDK 36. The package's
reported last-update time was 2026-09-09 14:06:55 local time.

Continuous PID-filtered logs covered the second TV test. No app crash was
observed. Results:

| Check | Observed result |
| --- | --- |
| Cached TV entry at 12:09:15 UTC | Favorites, its guide, and 108,145-channel index visible by +3,319 ms |
| Favorite local EPG query | 1/1 matched, 8 ms total |
| Switch to All Channels | 108,145 total and initial rows visible by +2,403 ms |
| Switch to NL category | 21/21 rows; guide queries matched 18/18, 28-199 ms total |
| Background guide work | Observed batches of 24, 1, and 18; no 240-channel warm-up burst |
| Larger category | 426 total, 144-row paged window loaded; row area still blank in the +3,146 ms capture, populated in the later capture |
| Scroll All Channels with moving NPO 1 video | 30 Down presses reached channel 31; 154/389 janky frames (39.59%), p50 36 ms, p90 73 ms, p99 150 ms |
| NL EPG-grid navigation with moving video | 30 Up/Down presses; 147/242 janky frames (60.74%), p50 53 ms, p90 200 ms, p99 300 ms |
| NL channel-column navigation with moving video | Channel 8 -> 1 -> 16 -> 8; 181/299 janky frames (60.54%), p50 48 ms, p90 150 ms, p99 250 ms |

The three corrected-build frame samples exclude screenshot capture from the
measurement window. They are short physical-device samples, not a controlled
before/after benchmark. Continuous app log capture was active. The original
12.92% sample had a frozen mini-player and must NOT be used to claim acceptable
live-playback scrolling. The active-video measurements fail the under-10% goal.
The log also reports skipped-frame bursts of 43 and 99 around NL category entry.

NPO 1 produced changing video over approximately six minutes. One initial
unsupported-container error still required HLS detection/recovery, so first play
is not instant. Later it recovered an expired live window, then encountered an
HLS playlist reset. The corrected retry reused HLS; the final TV screenshot at
12:15:52 UTC contains a fresh studio image after that reset, rather than the old
frozen image/terminal parser error. This verifies recovery in this sample, not
zero rebuffering or long-duration playback reliability. No additional video
probes were deliberately fired to stress the provider.

The observed 24/7 guide batches returned zero listings. This does not prove the
provider has no schedule, nor does the NL 18/18 query prove full-guide coverage.
Fresh XMLTV import, catch-up playback, every channel, and other providers were
not revalidated in this follow-up. HTTP request counts were not instrumented on
the physical TV; the actual quota remains covered by the local fixture tests.

Stopped the log collector and returned to Home after testing, leaving the signed
update installed and user data intact. Overall result: cache-loading and the
two narrow follow-up corrections verified, but NOT a full smoothness, complete
guide-coverage, or instant-playback pass. Rendering with live video remains a
separate unresolved performance issue.

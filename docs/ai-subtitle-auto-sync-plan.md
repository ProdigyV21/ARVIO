# AI Subtitle Auto-Sync — Port Plan

> **Status: implemented.** Everything below was built as planned; end-of-file notes list the
> small deviations made during implementation.

Port of the NuvioTV "AI Subtitle Auto-Sync" feature (LLM-based semantic line matching between
built-in subtitle cues and an addon subtitle file, used to compute a flat delay and, optionally,
a linear drift rate) into ARVIO. See the original design in the NuvioTV repo:
`docs/ai-subtitle-auto-sync-design.md` (branch `feat/ai-subtitle-auto-sync`).

Related existing ARVIO doc: [subtitle-auto-match.md](./subtitle-auto-match.md) — the "find best
match" scan whose infrastructure this feature reuses heavily.

## Feature summary

When the user selects (or has auto-matched) an addon subtitle that is out of sync:

1. **Anchor 1 (foreground):** gather ≥5 timestamped dialogue lines from a text-based built-in
   (embedded) subtitle track — which is always correctly timed to the video — and ask an LLM
   (Groq or Gemini, user's existing key) to semantically match those lines against a window of
   the addon file's lines. Each confident `{source_index, target_index}` pair yields an offset
   `builtInCueTime − addonCueTime`; pooled offsets across up to 3 attempts are reduced with a
   robust (outlier-rejecting) mean and the result is applied as a flat delay.
2. **Anchors 2–4 (background, best-effort):** an invisible secondary text-only ExoPlayer
   instant-seeks to up to 3 more positions across the runtime, repeats the matching there, and
   fits a line (OLS with residual-outlier rejection) across all anchors to correct gradual
   frame-rate-style drift. Silent fail-safe: anything that goes wrong keeps the existing result.

The AI is only used to find a handful of line-to-line matches — no transcription/translation of
the media, only a few short text snippets per sync regardless of runtime.

## Why the port is small: NuvioTV component → ARVIO equivalent

| NuvioTV component | ARVIO equivalent (already exists) |
|---|---|
| Temporary internal-track cue gathering (switch playback to built-in track, wait for rendered `CueGroup`s) | `AiSubtitleRenderersFactory` already reads the selected track's **buffered upcoming cues** by reflection (`extractBufferedIntervals`, `extractAllCueTexts`) plus the realtime `PlayerViewModel.onPlayerCues` callback. The temporary reference-track switch — including self-cue guards and AI-interim source migration — already exists in `PlayerViewModel.scoreAgainstBuiltIn`. Buffered reads let anchor 1 complete in ~seconds without waiting for dialogue to be spoken (faster than the original design). |
| Addon subtitle download + SRT/VTT parsing + per-run cache | `SubtitleSyncMatcher.loadRaw` / `parseCues` (gzip + UTF-8 normalization) |
| Delay application: renderer `subtitleDelayUs` + drift rate + self-anchoring sentinel | **Replaced by a simpler ARVIO-native path**: `PlayerViewModel.localizeSubtitle` + `SubtitleSyncMatcher.shiftTimestamps` bake the correction into a served local file, and `writeCachedMatch(sub, offsetMs)` persists it across playbacks of the same stream. No renderer-side delay/rate state machine is ported. |
| Groq/Gemini clients, API key storage, model picker, key-missing / rate-limit error surfacing, Settings UI | `SubtitleTranslationService` (same two endpoints), `SubtitleAiModel` enum, existing Settings key entry and player error toasts |
| MPV-unsupported guard | Not needed — ARVIO is ExoPlayer-only |
| Session/track identity locking, stale-cue clearing | Same pattern as the existing match-scan job management (single `Job`, cancel + clear on track change) |

## Gaps — what actually gets built

### 1. Timestamped reference cues — extend `AiSubtitleRenderersFactory`
Intervals and texts are currently extracted separately. Add
`extractBufferedTimedCues(max): List<TimedCue>` returning paired `(startMs, endMs, text)` —
a merge of the two existing reflection walkers (`intervalFromCueWrapper` already holds the cue
list; additionally join its text). Realtime fallback: `onPlayerCues` already receives
`textSample`; buffer it with its timestamp. Port `isNonDialogueMusicCue` (♪ / `[Music]` /
`(singing)` filters) and the >90 s presentation-time-jump buffer reset.

### 2. LLM semantic line matching — one new method on `SubtitleTranslationService`
Port `buildSubtitleAutoSyncPrompt` and the Groq/Gemini
"match lines → `{"pairs":[{"source_index","target_index"}]}`" request as
`matchSubtitleLines(sourceLines, targetWindowLines): List<Pair<Int, Int>>`
(temperature 0, JSON response format). Reuses the service's existing key provider, model
dispatch, JSON handling, and backoff — no new networking or settings code.

### 3. Anchor math — new pure-Kotlin `SubtitleAutoSyncMath.kt`
Near copy-paste port, independently unit-testable:
- `robustMeanOfLongs` — median-referenced outlier rejection (>450 ms → drop), mean of the rest;
  plain median at ≤2 points.
- Target-window selection: ±12 addon lines around the estimated corresponding position.
- Pooling constants: anchor 1 = up to 3 LLM attempts until ≥3 pooled offsets; drift anchors =
  up to 2 attempts each.
- Multi-anchor OLS fit: exact 2-point solve at 2 anchors; with 3+, drop a single point whose
  residual exceeds 400 ms and refit; reject the fit if residuals stay bad or the rate is
  outside the realistic frame-rate-mismatch range.

Add a JVM unit test for this file under `app/src/test`.

### 4. Anchor-1 orchestration — `PlayerViewModel.autoSyncSubtitle(sub)`
Pattern-matched to `findBestSubtitleMatch`:
- Resolve the built-in text reference track and switch to it exactly as `scoreAgainstBuiltIn`
  does — factor its track-switch prelude (index re-resolution, AI-interim migration, settle
  delay, self-cue text guard) into a shared private helper instead of duplicating it.
- Collect ≥5 dialogue reference cues (buffered first, realtime fallback), music-filtered.
- Download/parse the addon file once per run (`loadRaw` / `parseCues`).
- Up to 3 LLM attempts; pool per-pair offsets until ≥3 points (or timeout); reduce with
  `robustMeanOfLongs`.
- **Apply by re-serving the file**: `localizeSubtitle(sub, raw, offsetMs)` +
  `writeCachedMatch(sub, offsetMs)`. The sync then survives MediaItem rebuilds and future
  playbacks; "manual pick always wins" already holds because manual selection rewrites the
  match cache.
- Errors surface the same way as the match scan: no built-in text track → clear message;
  missing API key → existing key-missing toast pointing to Settings.

### 5. Drift calibration (anchors 2–4) — new `SubtitleDriftCalibrator.kt`
Port of NuvioTV's `PlayerRuntimeControllerSubtitleDriftCalibration`:
- Invisible secondary ExoPlayer with a minimal text-only renderers factory (no video/audio
  renderers), fed the same MediaItem/media source factory the player screen already builds.
- Instant-seeks to up to 3 target positions spread across whichever side of anchor 1 has more
  room (edge safety margins; nudge to a nearby position when landing in a quiet/music stretch).
- Per-anchor: same reference-cue gathering + LLM matching, budget 2 attempts, reduced with
  `robustMeanOfLongs`.
- After each anchor: refit line across all anchors (rules in §3); on an accepted fit, apply it.
- Session token checked before every await point; cancelled by track change, manual delay, or
  a re-run. All failures are silent — keep whatever result already exists.

**Key simplification vs NuvioTV:** on an accepted fit, instead of the renderer rate +
self-anchoring-sentinel mechanism, generalize `SubtitleSyncMatcher.shiftTimestamps` to
`transformTimestamps(raw, offsetMs, rate)` (`t' = t + b + r·t`) and re-serve the local file once
per accepted fit (2–3 rewrites max; each a fast regex pass over ~100 KB). The renderer-side rate
machinery — the hardest part of the original — is not ported. The 2–3 visible refinements as
anchors land are expected progressive refinement, matching the original design.

### 6. UI + settings — additive edits only
- **Player subtitle menu**: an "AI auto-sync" action next to the existing "Find best match"
  button in `PlayerScreen`, acting on the currently selected addon subtitle; progress/result via
  the existing top-center match pill/toast.
- **Auto-trigger**: one new profile-scoped setting toggle (`auto_sync_subtitles`) in the
  existing AI subtitles Settings section — when on, selecting an addon subtitle in the AI target
  language triggers auto-sync automatically; when off, the menu action is manual-only.
- Provider/model/API key settings are reused **as-is** (single key + `SubtitleAiModel`).
- ~8 new string resources.

## Deliberately skipped / deferred

| Item | Reason |
|---|---|
| Phone companion key page (`AddonConfigServer` web form) | ARVIO has no local HTTP config server and already has working key entry in Settings. |
| Renderer delay/rate/sentinel mechanism | Replaced by baking the linear transform into the served local file (§5). |
| Separate per-provider API keys | ARVIO's single key + model enum stands. |
| Debug LLM request/response overlay state | Replaced by `Log.i("SubSync", …)` traces; the original doc mandates stripping the overlay before production anyway. |
| MPV guard | ARVIO is ExoPlayer-only. |

## File touch list

| File | Change |
|---|---|
| `ui/screens/player/SubtitleAutoSyncMath.kt` **(new)** | pooling / robust mean / window selection / OLS fit + constants |
| `ui/screens/player/SubtitleDriftCalibrator.kt` **(new)** | secondary text-only player, anchors 2–4 |
| `ui/screens/player/SubtitleTranslationService.kt` | + `matchSubtitleLines()` and prompt builder |
| `ui/screens/player/AiSubtitleRenderersFactory.kt` | + `extractBufferedTimedCues()` |
| `ui/screens/player/SubtitleSyncMatcher.kt` | generalize `shiftTimestamps` → offset + rate |
| `ui/screens/player/PlayerViewModel.kt` | + `autoSyncSubtitle()`, shared reference-track helper, cache/apply wiring |
| `ui/screens/player/PlayerScreen.kt` | menu action, status pill wiring, calibrator lifecycle |
| `ui/screens/settings/SettingsScreen.kt` / `SettingsViewModel.kt` | one toggle |
| `app/src/main/res/values/strings.xml` | ~8 strings |
| `app/src/test/.../SubtitleAutoSyncMathTest.kt` **(new)** | unit tests for the math |

## Phasing

- **Phase 1** — items 1–4 + 6: complete, shippable flat-delay auto-sync (anchor 1).
- **Phase 2** — item 5: background drift calibration on top.

Each phase is independently testable on-device; Phase 1 alone matches the state in which the
feature first shipped on NuvioTV.

## Implementation notes (deviations from the plan above)

- File naming: the math file is `SubtitleAutoSync.kt` (object `SubtitleAutoSync`); the drift
  calibrator is `SubtitleDriftCalibrator.kt` as planned.
- The LLM requests reuse ARVIO's **current** provider parameters, not NuvioTV's: Groq
  `openai/gpt-oss-120b` with `reasoning_effort=low` / `reasoning_format=hidden`, Gemini
  `gemini-3.5-flash-lite` with `thinkingLevel=minimal`, `BLOCK_NONE` safety settings (movie
  dialogue trips default filters) and transient 5xx retry — all at temperature 0 with JSON
  response mode.
- Anchor-1 gathering prefers the **buffered** reflection path (`extractBufferedTimedCues`) whose
  timestamps are raw media time — no delay compensation needed and retries get fresh upcoming
  lines instantly instead of NuvioTV's 30s realtime wait. The realtime fallback compensates for
  the manual slider offset at capture time.
- When an attempt returns no matches, the ±12-line addon window is **doubled** for the next
  attempt (12 → 24 → 48) so large real offsets outside the default window can still be found;
  drift anchors additionally center the window at `trueTime − anchor1Delay`.
- Applying a result bumps `subtitleDelayResetNonce`, which resets the in-player manual delay
  slider to 0 — the correction lives entirely in the baked file, so a leftover slider value
  would stack (NuvioTV instead zeroed its renderer delay at run start).
- The invisible drift player runs at **4× playback speed** (no audio/video renderers → the
  standalone clock allows it; network throughput is the natural ceiling), shortening per-anchor
  gathering versus NuvioTV's realtime pace.
- Drift refits skip re-applying when ~unchanged (≤150 ms intercept and ≤0.0005 rate delta) —
  within measurement noise, and re-toasting buys nothing. (Originally this guarded MediaItem
  re-bakes; refits now apply renderer-side — see the renderer-side apply note below.)
- `CachedSubMatch` gained a `rate` field and the served-file id marker is now
  `#ofs<ms>[r<rate·1e6>]`, so an auto-synced (even drift-corrected) subtitle is re-baked
  automatically on future playbacks of the same stream — persistence NuvioTV didn't have.
- The `subtitle_ai_auto_sync` toggle participates in cloud settings backup/merge
  (`CloudSyncRepository`), guarded so old-version backups can't reset it.

## Post-release additions (initially deferred/deviating, since implemented)

- **OCR (ML Kit) fallback for image-based (PGS/DVB) built-in tracks** — ported from NuvioTV.
  `SubtitleCueOcr.recognizeSubtitleBitmapText` (bundled on-device Latin recognizer,
  `com.google.mlkit:text-recognition`) OCRs rendered bitmap cues fed through the realtime
  `onPlayerCues` seam (the player listener now also passes the cue bitmap). The reference-track
  picker falls back to a bitmap track (English first — the recognizer is Latin-only) when no
  text-based built-in track exists. Drift calibration runs on OCR references too: the calibrator
  OCRs its own invisible player's bitmap cues (mirroring NuvioTV's calibrator), and the anchor
  reliability gates fence noisy measurements — enabled after ML Kit proved near-text accuracy on
  clean PGS renders in practice.
- **Startup auto-trigger** — with the toggle on, auto-sync also runs when an addon subtitle is
  AUTO-selected at playback start (`maybeAutoSyncAfterAutoSelection`): a remembered sync for the
  same stream is re-applied without any LLM call; otherwise the run waits (≤45 s) for embedded
  reference tracks to resolve and starts silently (no prerequisite toasts on the auto path).
  Strictly **one automatic attempt per subtitle per stream, win or lose** — the trigger sits in
  `applyPreferredSubtitle`, which re-fires on every track/list update, so a failed run must not
  be retried on each of them (manual pick/menu action can always retry). All auto-sync session
  guards are keyed on `provider|id` only, never the URL: some addons serve expiring/signed URLs
  that change on every list refresh and would defeat once-per-stream guards.
- **Mutual exclusion with "Find best match"** — the two auto features fight over the reference
  track and status pill, so enabling auto-sync now forces the auto match-scan setting off and
  greys out its row; the player-side pref read defensively ignores stale both-on backups.
- The AI model / API key / QR settings rows are no longer visually gated on the AI-translation
  toggle — they also power auto-sync.
- Result toasts spell out the numbers: anchor 1 reports the applied offset, drift fits report
  offset + drift (s/10min), and a remembered sync reports what it re-applied.
- **Renderer-side apply (no playback hiccup)** — the original port baked every result into a
  re-served file, and the required MediaItem rebuild caused a visible buffering "push" on apply
  (NuvioTV never had one: it applied delay renderer-side). Now `SubtitleOffsetRenderer` carries
  NuvioTV's exact mechanism: `delay(position) = base + rate·(position − anchor)`, where the
  anchor **self-captures on the render thread** at the first render call after a (re-)apply.
  Render positions live in ExoPlayer's private offset timebase (media position + ~1e12 µs,
  `MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US`) — any slope≠0 math touching the raw
  position (e.g. `position·(1−rate)`) scales that offset by the rate and throws subtitles off by
  ~rate·1e6 seconds; delta-only math is immune. The view model hands the renderer the fitted
  delay AT the current position (`intercept + rate·now`) plus the rate, so `delay(T) =
  intercept + rate·T` holds exactly while staying continuous at the apply moment (no timeline
  jump → no TextRenderer cue-discard blanking). All applies — anchor 1, drift refits, and the
  remembered-sync startup re-apply — go through it. The transform is zeroed while gathering
  reference cues, restored on failure, cleared when the selection moves to a different track,
  re-armed after any MediaItem rebuild (fresh timebase), and file-baking remains only as a
  fallback (renderer hook unavailable / a baked copy already on screen). The
  track-override-first restore in `PlayerScreen` now also runs outside preload mode, so restoring
  the addon subtitle after gathering is push-free too. The `CachedSubMatch` offset/rate cache is
  unchanged — persistence still works across playbacks.
- **Drift noise floor** — a fitted rate whose total effect across the measured anchor span is
  within the per-anchor noise bound (400 ms) is unmeasurable slope, not drift: the fit snaps to
  flat (rate 0, robust-mean delay), so tiny spurious rates are never applied as drift.
- **Non-linear rescue (periodic re-sync)** — when drift anchors measure consistently but NO line
  fits them (`SubtitleDriftCalibrator.Outcome.NON_LINEAR`: stepwise timing from ad-break cuts /
  a different edit), a background loop re-measures a flat offset ~45 s ahead of the live playback
  position with the same invisible player, re-applying renderer-side only when the delay moved
  beyond noise (>450 ms; toast: "Auto-sync adjusted — offset …"). The trigger is **position-based**
  (playback ≥10 min away from the last measurement), so 10 minutes of natural watching and a
  ±10-minute user jump both re-measure immediately, while paused playback measures nothing.
  Linear content never enters this loop — an accepted fit already extrapolates forward for free.

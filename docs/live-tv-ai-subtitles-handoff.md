# Live TV AI subtitles — implementation brief

You are picking up a planned feature in the ARVIO Android TV app (Kotlin, Jetpack Compose, Media3/ExoPlayer 1.9.0).
Read this whole file before writing code. Every file path is relative to the repository root.

## 1. Goal

Live TV channels get AI-generated subtitles (transcribed, and translated into the viewer's language) that are
**on time**, not late. The owner already tried a version of this and it worked badly; section 3 explains why.

What the owner asked for:

1. A setting for "Live TV subtitles".
2. When it is on, live playback is **delayed** by a few seconds so there is a buffer to work with while the AI
   translates.
3. Use the Google (Gemini) API; users must enter their own key to use the feature.
4. Decide whether the on-device speech model used by "Find best match" would be better than the Google API.
   (Decision: **no, not as the main engine**; see section 5.)

## 2. Agreed design in one paragraph

Copy the channel's **compressed audio at the extractor** (when it is downloaded, ahead of the playhead), decode it
on a side thread, cut it into speech segments with the existing Silero VAD, send each segment to Gemini
(`generateContent`, audio in, JSON text out, transcribe plus translate in one call), and keep each resulting cue
**in media time** (the segment's start/end PTS). A scheduler shows the cue when `player` position reaches its
start. Playback is held back by D seconds (default 10, "Auto" measures and adds only what is missing) using a
custom `LoadControl`, so every cue arrives before its moment on screen.

```
stream ─► extractor ─┬─► ExoPlayer buffer (≥ D s) ─► screen / speakers at time T
                     │
                     └─► tap ─► decode ─► 16 kHz mono ─► Silero VAD segmenter ─► Gemini ─► cue [start,end] (media time)
                                                                                                │
                              LiveCueScheduler: show cue when player position reaches start ◄───┘
```

## 3. Why the previous attempt failed (do not repeat these)

The previous attempt is `GeminiLiveTranslationService` + `AudioCaptureProcessor`, wired into the **VOD** player only.

| Problem | Where | Consequence |
|---|---|---|
| Audio captured at the **audio sink**, i.e. at the moment the viewer hears it | `app/src/main/kotlin/com/arflix/tv/ui/screens/player/subtitles/AudioCaptureProcessor.kt`, installed in `AiSubtitleRenderersFactory.buildAudioSink` (`.../player/engine/exoplayer/AiSubtitleRenderersFactory.kt` ~line 160) | Every subtitle is 1–3 s late, structurally. No amount of delay fixes a sink tap. |
| Text published as a wall-clock `StateFlow<String?>` | `.../player/subtitles/GeminiLiveTranslationService.kt` (900 ms min per line, backlog of 3 lines that drops the oldest, 3 s auto-clear) | Pause, rebuffer, fast dialogue all break alignment; lines get dropped. |
| `gemini-3.5-live-translate-preview` model | same file, `MODEL` | Ignores instructions; no context / gender / register control; returns no timestamps; sentence boundaries guessed from punctuation. |
| Never wired into Live TV | Live TV builds its own ExoPlayer at `app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LiveTvScreen.kt` ~line 3084 with default renderers | Feature does not exist on Live TV at all. |

Leave the VOD feature (`toggleLiveAudioTranslation` in `PlayerViewModel.kt` ~line 4057) alone for now. Do not
delete or refactor it as part of this work.

## 4. Codebase map (what exists and how to reuse it)

### Live TV player
- `app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LiveTvScreen.kt` (~5.5k lines)
  - ~3042–3071: OkHttp client, data source factory, `mediaSourceFactory` using `iptvExtractorsFactory()`.
  - ~3077: `livePlaybackBufferProfile = buildLiveTvBufferProfile(...)`.
  - ~3084–3112: `val exoPlayer = remember(livePlaybackBufferProfile) { DefaultLoadControl.Builder()... ExoPlayer.Builder(context)... }`.
    **This is where the custom LoadControl and anything per-player goes.**
  - ~3280–3365: the function that prepares a channel. Builds `MediaItem` (sets `buildLiveTvConfiguration()` when
    not catch-up), then per-source factories:
    - HLS: `HlsMediaSource.Factory(...).setExtractorFactory(iptvHlsExtractorFactory())`
    - Non-HLS: `DefaultMediaSourceFactory(context, iptvExtractorsFactory())` (raw MPEG-TS, most Xtream `.ts` channels).
    **Both extractor factories must be wrapped with the audio tap here.**
  - ~4626: fullscreen `AndroidView { PlayerView(...) }`, followed by `FullscreenHud(...)` when `isFullScreen`.
    The AI subtitle overlay goes here (Compose, above the PlayerView, only when fullscreen).
  - ~5250: `buildLiveTvConfiguration()` pins min/max playback speed to 1.0, so ExoPlayer will not speed up to
    catch the live edge and eat the buffer. Keep it that way.
  - ~5262: `buildLiveTvBufferProfile()`: minBuffer 15 s, maxBuffer 30 s, bufferForPlayback 1 s,
    afterRebuffer 2.5–3 s, back buffer 5 s, 32/48 MB target bytes. `prioritizeTimeOverSizeThresholds` **must
    stay false** (comment explains OOM history). Max 30 s is enough for D ≤ 15 s; check byte cap on 4K channels.
  - ~2275 and ~3142: `liveAudioChoices` per-channel map; copy this pattern to remember "AI subtitles on" per channel.
- `.../tv/live/LiveSubtitles.kt`: subtitle/audio track options for live channels (`liveSubtitleOptions`,
  `applyLiveSubtitleChoice`, `LIVE_SUBTITLES_OFF`). Add the "AI subtitles" option here. When AI is on, disable
  the stream's own text track (`setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)`).
- `.../tv/live/LiveTrackPicker.kt`: the picker UI.
- `.../tv/live/IptvKeyframeExtractors.kt`: `iptvHlsExtractorFactory()` (line ~28) and `iptvExtractorsFactory()` (~35).
- `.../tv/live/IptvHlsExtractorFactory.kt`: custom `HlsExtractorFactory`. TS segments → `TsChunkExtractor(IptvTsExtractor)`
  (line ~133, reused across segments, never recreated); everything else → `DefaultHlsExtractorFactory`.
  HLS does **not** go through `ExtractorsFactory`, so it needs its own tap: wrap the returned
  `HlsMediaChunkExtractor` in a delegate whose `init(extractorOutput)` passes a tapping `ExtractorOutput`
  (and whose `recreate()` wraps the recreated one). That covers TS and non-TS HLS in one place.
- `.../tv/live/LiveTvPlaybackSession.kt`: suspend/resume on background; `resume` seeks live to default position.
  The tap must treat this as a discontinuity.
- Note: `HomeScreen.kt:583` and `TvScreen.kt:607` also use `iptvHlsExtractorFactory()` (previews). Do not tap those.

### Audio look-ahead plumbing (from "Find best match" / audio sync) — reuse this
All in `app/src/main/kotlin/com/arflix/tv/ui/screens/player/audiosync/`:
- `AudioSyncExtractors.kt`: `AudioSampleSink` interface (`wantsSamples`, `onSample(format, timeUs, data, …)`,
  `onDiscontinuity`) and `AudioSyncExtractorsFactory(delegate, sink)`. Internals `AudioTapExtractor`,
  `AudioTapExtractorOutput`, `AudioTapTrackOutput` are `private`; make the output/track wrappers `internal`
  so the HLS chunk-extractor wrapper can reuse them.
- `ArvioAudioSync.kt` ~line 464: `object AudioSyncTaps`. **Do not use it for Live TV.** Two reasons:
  1. `wrapExtractors`/`wrapAudioSink` return the factory unchanged when `BuildConfig.AUDIO_SYNC_AVAILABLE` is
     false, which is the **Play** flavor, so nothing would be tapped there.
  2. It routes to a single global `active` controller (the VOD sync controller).
  Live TV should construct `AudioSyncExtractorsFactory` directly with its own `AudioSampleSink`.
- `AudioSyncDecoder.kt`: decodes the copied compressed samples on its own low-priority thread with a private
  `MediaCodec` (falls back to FFmpeg on sideload), feeds mono PCM to a `SpeechAnalyzer`. Bounded queue, drops on
  overload. Read the constructor docs: it never uses a vendor decoder that can't run a second instance.
- `SpeechAnalyzer.kt`: resamples to 16 kHz, runs Silero, calls `ChunkListener.onChunk(frame, chunk, probability)`.
  `accept(samples, count, sampleRate, timeUs)` carries media time.
- `SileroVad.kt` + weights in `app/src/main/res/raw/silero_vad_v5_16k.bin` (main source set, both flavors).
  Loading pattern: `AudioSubtitleSyncController.kt` ~line 1230 (`loadWeights`, cached).
- `asr/SpeechSegmenter.kt`: turns VAD chunks into speech segments (`onSegment(startFrame, samples)`), with
  preroll, hangover, and a max segment length. Make the max length configurable (target ~5 s for live).
  Convert `startFrame` back to media time using the analyzer's frame→time mapping.
- `PlaybackAudioTap.kt`: the real-time sink tap. Only a last-resort fallback; it cannot give look-ahead, so if a
  channel only works through it, show "AI subtitles not supported on this channel" rather than late subtitles.

### On-device speech model ("Find best match" hearing model) — not the main engine
- `asr/AsrModel.kt`: sherpa-onnx Zipformer GigaSpeech, **English only**, ~74 MB download.
- `app/src/sideload/.../asr/SherpaSpeechToText.kt`: real implementation (offline recognizer, word timestamps).
- `app/src/play/.../asr/SherpaSpeechToText.kt`: throws; **Play flavor has no speech recognition**.
- `asr/SharedRecognizer.kt`: shared instance with delayed close.
- Optional later phase only (section 8).

### Flavors
- `app/build.gradle.kts` ~142: flavors `play` (`AUDIO_SYNC_AVAILABLE=false`) and `sideload` (`true`).
- Play has **no FFmpeg** (`app/src/play/kotlin/androidx/media3/decoder/ffmpeg/AudioSyncFfmpegDecoder.kt` throws),
  so the look-ahead copy relies on device MediaCodec only. Expect lower format coverage on Play (AC-3/E-AC-3 on
  some boxes). The Gemini engine itself needs nothing native, so the feature is meant to work in both flavors.

### Existing Gemini/AI code to copy patterns from
- `.../player/subtitles/SubtitleTranslationService.kt`
  - `GEMINI_MODEL_ID = "gemini-3.5-flash-lite"`, `GEMINI_BASE_URL = .../v1beta/models/$GEMINI_MODEL_ID:generateContent`, key in `?key=`.
  - ~228 `matchGemini` / ~384 `translateGemini`: `generationConfig.responseMimeType = "application/json"`,
    `thinkingConfig.thinkingLevel = "minimal"`, `safetySettings` BLOCK_NONE, transient retry on 500/502/503
    (2 retries, 400 ms base), no retry on 429, content-block bisection (`TRANSLATION_ERROR_CONTENT_BLOCKED`),
    `RTL_LANGUAGES`, `extractJsonArray`. Reuse or extract these helpers; do not duplicate them badly.
- `.../player/subtitles/SubtitleAiModel.kt`: `GROQ_LLAMA_70B` / `GEMINI_FLASH_25` (names are persisted, never rename).
- API key preference: `stringPreferencesKey("subtitle_ai_api_key")` (global), model `subtitle_ai_model`;
  read in `PlayerViewModel.kt` ~460/921, edited in `.../settings/SettingsViewModel.kt` ~579, synced in
  `data/repository/CloudSyncRepository.kt`.

## 5. Engine decision (owner's question 4)

| | Gemini `generateContent` per segment (**chosen**) | Gemini Live translate (old) | Sherpa on-device | Cloud STT + Translate |
|---|---|---|---|---|
| Source languages | any | any | English only | any |
| Translates | yes, same call | yes | no | two APIs |
| Timestamps | exact (segment media time) | none | word-level | word-level |
| Steerable | yes (prompt, context) | no | n/a | limited |
| Flavors | both | both | sideload only | both |
| User setup | Gemini key (existing field) | Gemini key | none | GCP project + billing + 2 APIs |

Rationale: plain HTTP (no long-lived WebSocket; Live API sessions have duration limits, which matter for hours of
TV); timing is exact by construction; prompt lets us pass the previous lines for context and fix quality.

Reuse from "Find best match" = the plumbing (tap, decoder, VAD, segmenter), not the recognizer.

## 6. Delay math (important: "a couple of seconds" is not enough)

You cannot translate a sentence until it has finished being spoken:

```
D ≥ max segment length (~5 s) + engine latency p95 (~2–3 s) + margin (~1 s)  ≈ 8–10 s
```

- Options: **Auto / 6 / 10 / 15 s**, default **10**.
- **Auto**: measure real look-ahead `bufferedPosition − currentPosition` and only add what is missing. HLS
  channels often already have 10–20 s ahead; raw TS has ~0 and needs full D.
- Implement as `SubtitleLookaheadLoadControl(delegate: DefaultLoadControl)` overriding
  `shouldStartPlayback(parameters)` to also require `parameters.bufferedDurationUs ≥ D` **only while AI subtitles are
  active for the current channel** (read an `AtomicLong`/`@Volatile` set from UI). This covers start and every
  rebuffer. Check the exact `LoadControl` API in Media3 1.9.0 before writing (`LoadControl.Parameters`).
  Everything else delegates.
- While waiting, show "Preparing subtitles… N s" over the first frame.
- Turning the feature on mid-channel: the simplest correct approach is to pause until look-ahead ≥ D, then resume.
- Cost: with the feature on, **every zap waits D seconds**. Hence default activation is per channel (section 7).

## 7. Settings and UX spec

Settings → Live TV → **AI subtitles**
- Enable switch (disabled until a valid key is saved).
- **Google AI (Gemini) API key**: prefill from `subtitle_ai_api_key` when `subtitle_ai_model` is Gemini; otherwise
  its own field. Validate on save with a cheap call (e.g. list models / a tiny generateContent); show result.
  Store like the existing key and include in cloud sync the same way.
- Target language: defaults to the profile's subtitle language (same resolution as `targetSubtitleLangCode` /
  `liveSubtitleDefault` uses).
- Delay: Auto / 6 / 10 / 15 s.
- Activation: "From the subtitle menu, per channel" (default) / "Always on".

In the live player
- Subtitle picker gets an **"AI subtitles"** entry (after the stream's own tracks). Remember per channel like `liveAudioChoices`.
- Overlay only in fullscreen (`isFullScreen && !fullscreenGuideOpen && !quickZapOpen` area). Reuse the app's
  subtitle styling (`.../player/SubtitleViewStyling.kt`, `SubtitleTypefaceResolver.kt`), handle RTL (Hebrew is
  the owner's language: `he`).
- Pipeline runs **only while fullscreen and playing** (cost control). Mini player / guide: pipeline stopped.
- Toasts/states: no key, invalid key (401), quota (429 → "AI subtitles paused", back off), offline, unsupported
  audio format on this channel.
- Strings go in `app/src/main/res/values/strings.xml` (plus translated files where the repo already keeps them:
  `values-iw`, `values-de`, `values-es`, `values-pt`, `values-pt-rBR`).

## 8. Phases and acceptance criteria

### Phase 0 — proof of concept (do this first; stop and report)
1. In `LiveTvScreen.kt`, wrap `iptvExtractorsFactory()` with `AudioSyncExtractorsFactory(..., liveSink)` for the
   non-HLS path, and add the `HlsMediaChunkExtractor` wrapper in `IptvHlsExtractorFactory` (or a wrapper factory
   around it) for HLS. Only for the Live TV fullscreen player, not Home/TV previews.
2. `liveSink` logs (tag `LiveAiSubs`): first/every-Nth sample `timeUs`, and at the same moment the player's
   **period** position (`player.currentPosition` is window-relative; in a live window add
   `timeline.getWindow(...).positionInFirstPeriodUs`, or compare against `currentPeriod`-based position). Also log
   `bufferedPosition − currentPosition`.
3. Feed samples into `AudioSyncDecoder` → `SpeechAnalyzer` → `SpeechSegmenter`; log segment start/end in media time.
4. Test on at least one raw MPEG-TS channel and one HLS channel, sideload debug build.

Pass criteria (report the numbers to the owner):
- Tap timestamps and playback position share a time base (error ≤ ~100 ms when a segment's start is compared with
  when that speech is heard).
- With playback held back D = 10 s, measured look-ahead stays ≥ ~9 s during steady playback.
- Segments are produced continuously for speech; CPU impact acceptable on the weakest test box.
- Discontinuities (zap, background/resume, rebuffer) reset cleanly.

**Stop after Phase 0 and report to the owner before building UI.** If time bases don't line up, find the offset
mapping before going further; everything else depends on it.

### Phase 1 — pipeline (new package `app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/aisubs/`)
- `LiveSpeechPipeline`: owns sink → decoder → analyzer → segmenter; emits `SpeechSegment(startUs, endUs, pcm16k)`.
  Stop/start/reset APIs; handles discontinuity; bounded memory.
- `LiveSubtitleEngine` interface: `suspend fun process(segment, context: List<String>, targetLang): List<LiveCue>`.
- `GeminiSegmentEngine`: WAV (16 kHz mono PCM16) base64 as `inline_data` (`mime_type: audio/wav`) + prompt:
  transcribe, translate into {target language name}, return JSON `{"lines":[{"text": "...", "startOffsetMs": n?}], "sourceLanguage": "xx"}`;
  if the source language already equals the target, return the transcription. Include the last 2–3 output lines as
  context. Up to 2 requests in flight, keep cue order by segment. `thinkingLevel=minimal`, JSON response mode,
  same retry/429/content-block rules as `SubtitleTranslationService`. Never log the key or URL with key.
- `LiveCueScheduler`: stores cues in media time; exposes `StateFlow<LiveCue?>` driven by player position polled
  ~every 100 ms (or a `Player.Listener` + ticker). Rules: min 1.2 s, max 6 s on screen; split long text into two
  timed cues proportionally; a late cue shows immediately if still within its window, else is dropped and counted;
  clear on discontinuity / channel change.
- Unit tests (JVM, `app/src/test/kotlin/...`): scheduler timing rules, split logic, late-cue handling, JSON
  parsing (including wrapped/malformed), delay math for Auto.

### Phase 2 — delay
- `SubtitleLookaheadLoadControl` as in section 6; wire into the `ExoPlayer.Builder` at `LiveTvScreen.kt` ~3102.
- Auto delay from measured look-ahead; "Preparing subtitles" state.
- Verify on a low-RAM profile that the 32 MB byte cap still holds D of 4K video; if not, cap D or warn.

### Phase 3 — UI
- Settings section + key validation, picker entry, per-channel memory, fullscreen overlay (styling + RTL),
  hidden debug HUD (look-ahead, latency p50/p95, late-cue count, segments/min).

### Phase 4 — hardening
- 429 backoff, offline, invalid key, background/resume, catch-up/timeshift (works with no added delay since it's
  effectively VOD), long sessions (hours) memory check, channel-zap stress test.

### Later (not now)
- Sideload-only "On-device (English, no key)" mode: segments → `SharedRecognizer` (sherpa) → English text →
  optionally existing text translation. Free/private English captions.
- Possibly move the VOD feature to the same segment engine and retire `GeminiLiveTranslationService`.
- Web app (`web/lib/liveTvPlayback.ts`) is out of scope.

## 9. Risks

- Some boxes can't run a second audio decoder: `AudioSyncDecoder` handles fallback; Play has no FFmpeg. Detect
  "no look-ahead decode possible" and show "not supported on this channel".
- Weak TV CPUs: decode + VAD is light but measure in Phase 0.
- Sports/music/crosstalk: quality will be rougher regardless of engine.
- Cost: Gemini bills audio by tokens (about 32 tokens per second of audio per Google's docs; verify), roughly 115k
  input tokens per hour of continuous speech. Only VAD speech is sent, and only while fullscreen.

## 10. Build, test, git

- Build/test like CI (`.github/workflows/build-check.yml`):
  ```
  cp secrets.defaults.properties secrets.properties   # if secrets.properties is missing
  ./gradlew testSideloadDebugUnitTest --no-daemon -PpartnerManagement=true
  ```
  The sherpa-onnx AAR (~50 MB) is downloaded by a Gradle task on first build into `app/libs/`.
  Detekt config: `config/detekt/detekt.yml`.
- Code style: match surrounding files (KDoc comments explaining *why*, `internal` visibility, no new libraries
  unless necessary).
- Branch: `claude/sharp-feynman-u0adtc` (create from latest default branch if missing). Commit in small steps per
  phase; push with `git push -u origin claude/sharp-feynman-u0adtc`. Do not open a PR unless the owner asks.
- Don't put model names/identifiers of the assistant in commits.

## 11. Open questions to confirm with the owner (ask, don't assume)

1. OK to stop after Phase 0 with numbers before UI work? (Recommended.)
2. Default activation: per channel (recommended) or always on?
3. Separate Gemini key field for live subtitles, or always reuse the existing `subtitle_ai_api_key`?
4. Should the Play build get the feature (MediaCodec-only coverage), or sideload first?

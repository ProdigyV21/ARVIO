# YouTube Trailers Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build robust YouTube trailer selection for movies and TV series.

**Architecture:** Add a pure `TrailerResolver` for TMDB video ranking and keep network orchestration in `MediaRepository`. Add TMDB season videos support for TV fallback while leaving `TrailerPlayer` unchanged unless build verification exposes a playback issue.

**Tech Stack:** Kotlin, Android app module, Retrofit TMDB API, JUnit JVM tests, Gradle sideload debug build.

---

## Files

- Create: `app/src/main/kotlin/com/arflix/tv/data/repository/TrailerResolver.kt`
- Create: `app/src/test/kotlin/com/arflix/tv/data/repository/TrailerResolverTest.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/data/api/TmdbApi.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/data/repository/MediaRepository.kt`

### Task 1: Resolver Ranking

- [ ] Write failing tests in `TrailerResolverTest` for YouTube filtering, official trailer preference, language fallback, and weak-name demotion.
- [ ] Run `.\gradlew.bat testSideloadDebugUnitTest --tests com.arflix.tv.data.repository.TrailerResolverTest --console=plain` and confirm the tests fail because `TrailerResolver` does not exist.
- [ ] Add `TrailerResolver.selectBestTrailerKey(videos, preferredLanguage)`.
- [ ] Run the same unit test command and confirm the resolver tests pass.

### Task 2: TV Season Fallback

- [ ] Extend `TmdbApi` with `getTvSeasonVideos(tvId, seasonNumber, apiKey, language)`.
- [ ] Add trailer key cache and TV season fallback inside `MediaRepository.getTrailerKey`.
- [ ] Reuse `TrailerResolver` for show-level and season-level result ranking.
- [ ] Run the targeted trailer unit tests again.

### Task 3: Verification and Publish

- [ ] Run `.\gradlew.bat testSideloadDebugUnitTest --tests com.arflix.tv.data.repository.TrailerResolverTest --console=plain`.
- [ ] Run `.\gradlew.bat assembleSideloadDebug --console=plain`.
- [ ] Run `git diff --check`.
- [ ] Commit, push `codex/youtube-trailers`, and create a PR.

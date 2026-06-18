# 2026 GUI Polish Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Upgrade the Android TV home UI toward a modern Kodi/Plex/Netflix-quality media experience.

**Architecture:** Use the existing skin/token system as the source of visual truth, then apply the updated tokens to Home, navigation chrome, and media cards. Avoid rewriting focus/navigation behavior.

**Tech Stack:** Kotlin, Jetpack Compose for Android TV, existing Arvio skin tokens, JUnit, Gradle sideload debug build.

---

## Files

- Modify: `app/src/main/kotlin/com/arflix/tv/ui/skin/ArvioSkinTokens.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/skin/ArvioFocus.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/components/AppTopBar.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/components/Sidebar.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/components/MediaCard.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/screens/home/HomeScreen.kt`
- Create: `app/src/test/kotlin/com/arflix/tv/ui/skin/ArvioSkinTokensTest.kt`

### Task 1: Skin Token Baseline

- [ ] Write failing token tests for color diversity, compact radius, and visible focus glow.
- [ ] Run `.\gradlew.bat testSideloadDebugUnitTest --tests com.arflix.tv.ui.skin.ArvioSkinTokensTest --console=plain`.
- [ ] Update skin tokens to pass the tests.

### Task 2: Home Visual Application

- [ ] Apply tokens to top bar/sidebar glass treatments.
- [ ] Add media-card image scrim and modern resting/focused border treatment.
- [ ] Tune the home hero scrim for readable cinematic depth.

### Task 3: Verification

- [ ] Run `.\gradlew.bat testSideloadDebugUnitTest --console=plain`.
- [ ] Run `.\gradlew.bat assembleSideloadDebug --console=plain`.
- [ ] Run `git diff --check`.
- [ ] Commit, push, create PR, and merge if checks pass.

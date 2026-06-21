# Discover Hub Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build a polished `Upptäck` page that helps the user decide what to watch now, keeps movies and series separate, adds Swedish seasonal catalogue rows, and leaves Home focused on Continue Watching plus fast rows.

**Architecture:** Add a small pure Kotlin discovery policy layer that maps current date + selected media mode to stable catalogue sections. The TV UI renders those sections as cinematic catalogue cards and reuses existing `CollectionDetailsScreen`/catalog loading for item browsing. Home remains the fast launch surface and only exposes enough rows to hint that there is more below.

**Tech Stack:** Kotlin, Jetpack Compose for TV, Navigation Compose, Hilt ViewModel, existing `CatalogRepository`, existing `CatalogConfig`, JUnit JVM tests, Gradle sideload staging build.

---

### Task 1: Discovery Policy And Seasonal Windows

**Files:**
- Create: `app/src/main/kotlin/com/arflix/tv/ui/screens/discover/DiscoverModels.kt`
- Test: `app/src/test/kotlin/com/arflix/tv/ui/screens/discover/DiscoverModelsTest.kt`

- [ ] **Step 1: Write failing tests**

Tests must assert:
- movies and series have separate default section ids
- Christmas appears from November 24 through January 1
- Halloween appears in October through November 7
- Midsummer appears around June 1 through June 30
- seasonal sections are inserted ahead of evergreen sections

- [ ] **Step 2: Run tests and verify RED**

Run:
```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.ui.screens.discover.DiscoverModelsTest" --console=plain --no-parallel
```
Expected: compilation/test failure because `DiscoverModels.kt` does not exist.

- [ ] **Step 3: Implement minimal policy**

Create:
- `DiscoverMediaMode` with `MOVIES`, `SERIES`
- `DiscoverSectionSpec(id, title, subtitle, catalogIds, accent)`
- `activeSwedishSeasonalSections(date, mode)`
- `defaultDiscoverSections(mode, date)`

Use existing catalog ids where possible:
- Movies: `trending_movies`, `top10_movies_today`, `just_added`, `top_movies_week`, `coming_soon`, genre/franchise collection ids.
- Series: `trending_tv`, `top10_shows_today`, `new_kdramas`, `trending_anime`, genre/service collection ids.
- Seasonal: use existing genre/family/horror/fantasy catalog ids first, then add dedicated seasonal ids only if needed in a later pass.

- [ ] **Step 4: Run tests and verify GREEN**

Run the same test command and require exit code 0.

---

### Task 2: Topbar And Navigation

**Files:**
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/components/Sidebar.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/components/AppTopBar.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/navigation/AppNavigation.kt`
- Modify: `app/src/main/res/values/strings.xml`
- Test: `app/src/test/kotlin/com/arflix/tv/ui/components/AppTopBarNavigationTest.kt`

- [ ] **Step 1: Write/update failing navigation tests**

Add assertions that topbar order is:
`Sök`, `Hem`, `Upptäck`, `Bevakningslista`, settings gear.

- [ ] **Step 2: Run navigation test and verify RED**

Run:
```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.ui.components.AppTopBarNavigationTest" --console=plain --no-parallel
```

- [ ] **Step 3: Add `DISCOVER` navigation item**

Add `SidebarItem.DISCOVER`, include it in topbar/legacy sidebar center item arrays, add `Screen.Discover`, and route Home/Search/Watchlist topbar actions to `navigateTopLevel(Screen.Discover.route)`.

- [ ] **Step 4: Run navigation test and verify GREEN**

Run the same test command and require exit code 0.

---

### Task 3: Discover Screen UI

**Files:**
- Create: `app/src/main/kotlin/com/arflix/tv/ui/screens/discover/DiscoverScreen.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/navigation/AppNavigation.kt`

- [ ] **Step 1: Create ViewModel and UI shell**

The screen loads `catalogRepository.getCatalogs()`, builds section rows from `defaultDiscoverSections`, and resolves each spec to visible cards only when a matching catalog exists.

- [ ] **Step 2: TV layout**

Use a full-screen fanart/gradient background, compact header, `Filmer | Serier` segmented control, and large horizontal catalogue cards. D-pad left/right moves within row, up/down moves between mode/rows, Enter opens `CollectionDetailsScreen`.

- [ ] **Step 3: Do not mix movies and series**

Movies mode must only show movie-oriented specs; series mode must only show series-oriented specs. Mixed service collections can be shown only when the section text clearly represents a service gateway, not a combined media list.

- [ ] **Step 4: Back behavior**

Back from `Upptäck` returns to Home if there is no deeper stack, matching Search/Watchlist.

---

### Task 4: Home Two-Row Feel

**Files:**
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/screens/home/HomeScreen.kt`
- Test: `app/src/test/kotlin/com/arflix/tv/ui/performance/MenuPerformancePolicyTest.kt`

- [ ] **Step 1: Add/update a pure layout policy test**

Assert TV Home can request adjacent row preview and returns double focused-row viewport height.

- [ ] **Step 2: Use adjacent row preview on TV Home**

Use the existing `tvHomeRowsViewportHeightDp(..., showAdjacentRowPreview = true)` path for Home rows, keeping clipping stable and focus centered.

- [ ] **Step 3: Run focused performance/layout tests**

Run:
```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.ui.performance.MenuPerformancePolicyTest" --console=plain --no-parallel
```

---

### Task 5: Build And Shield Verification

**Files:**
- No new source files expected.

- [ ] **Step 1: Run focused unit tests**

Run:
```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.ui.screens.discover.DiscoverModelsTest" --tests "com.arflix.tv.ui.components.AppTopBarNavigationTest" --tests "com.arflix.tv.ui.performance.MenuPerformancePolicyTest" --console=plain --no-parallel
```

- [ ] **Step 2: Build staging APK**

Run:
```powershell
.\gradlew.bat :app:assembleSideloadStaging --console=plain --no-parallel
```

- [ ] **Step 3: Install and AOT compile on Shield**

Run:
```powershell
$adb='C:\Users\mlixe\.cache\codex-android-sdk\platform-tools\adb.exe'
& $adb -s 192.168.1.71:5555 install -r "C:\Users\mlixe\Documents\New project\ARVIO\app\build\outputs\apk\sideload\staging\app-sideload-staging.apk"
& $adb -s 192.168.1.71:5555 shell cmd package compile -m speed -f com.arvio.tv
```

- [ ] **Step 4: Manual TV verification**

Verify on Shield:
- Home shows Continue Watching plus another row/preview below.
- Topbar includes `Upptäck`.
- Upptäck opens from topbar.
- Filmer and Serier modes do not mix content.
- Seasonal rows appear when a test date falls inside the Swedish holiday window.
- Enter opens the existing catalogue/details flow.
- D-pad navigation remains smooth.

# Kodi Catalog AIOStreams Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build the Kodi-inspired TV catalog UI from the HTML mockup and enrich focused catalog previews with fast, cached AIOStreams quality/audio availability badges.

**Architecture:** Keep catalog metadata rendering independent from stream lookup so D-pad navigation stays instant. Reuse the existing Stremio addon system for AIOStreams and extract source parsing into a pure helper shared by the source selector, autoplay, and catalog summary. Add a small stream availability repository that derives compact badges from existing `StreamRepository` results and ignores stale focus updates.

**Tech Stack:** Kotlin, Jetpack Compose for TV, Hilt, coroutines/Flow, DataStore-backed existing addon storage, Media3 existing player stack, JUnit + MockK coroutine tests, Gradle `:app:testSideloadDebugUnitTest`.

---

## Verified Inputs

- AIOStreams manifest URL was provided by the user and verified with HTTP 200.
- Manifest id starts with `com.aiostreams.viren070`.
- Manifest name is `AIOStreams`.
- Manifest version is `2.30.3`.
- Manifest declares `stream` for `movie` and `series`, plus `subtitles`.
- Do not commit the user's full manifest URL. It contains personal configuration.

## File Structure

- Create `app/src/main/kotlin/com/arflix/tv/data/stream/StreamSourceAnalysis.kt`
  - Pure parser/scorer for `StreamSource`: resolution, release type, codec, HDR/DV, audio, cached/debrid, source count, size bytes.
- Create `app/src/test/kotlin/com/arflix/tv/data/stream/StreamSourceAnalysisTest.kt`
  - Unit tests for 4K/HDR/DV/Atmos/TrueHD/7.1/5.1/cached/source-count/size tie-breaks.
- Create `app/src/main/kotlin/com/arflix/tv/data/repository/StreamAvailabilityRepository.kt`
  - Focused catalog stream summaries, timeout protection, small in-memory cache, uses existing `StreamRepository` source result cache for persistence.
- Create `app/src/main/kotlin/com/arflix/tv/di/StreamSummaryModule.kt`
  - Hilt binding from `StreamSummaryResolver` to `StreamRepositorySummaryResolver`.
- Create `app/src/test/kotlin/com/arflix/tv/data/repository/StreamAvailabilityRepositoryTest.kt`
  - Unit tests for movie summary, series S1E1 fallback, next-episode lookup key, timeout/null behavior, empty streams.
- Modify `app/src/main/kotlin/com/arflix/tv/ui/components/StreamSelector.kt`
  - Replace private duplicated parsing with `analyzeStreamSource`.
- Modify `app/src/main/kotlin/com/arflix/tv/data/repository/MediaRepository.kt`
  - Expose the existing IMDb-id resolver for catalog stream summary lookup.
- Modify `app/src/main/kotlin/com/arflix/tv/ui/screens/collections/CollectionDetailsScreen.kt`
  - Add stream summary state to preview, inject repository, remove catalog action buttons, implement Kodi-style fanart background, compact list, media flags.
- Modify `app/src/test/kotlin/com/arflix/tv/ui/screens/collections/CollectionDetailsViewModelTest.kt`
  - Cover stream summary debounce/stale ignore and cached preview merge.
- Modify `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsViewModel.kt`
  - Add `installAddonFromUrl` and status fields.
- Modify `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsScreen.kt`
  - Add "Add Stremio/AIOStreams addon" input row.

## Task 1: Add AIOStreams Install Flow In Settings

**Files:**
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsViewModel.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsScreen.kt`

- [ ] **Step 1: Extend settings state**

In `SettingsUiState`, add:

```kotlin
val addonInstallStatus: String = "",
val isInstallingAddon: Boolean = false
```

- [ ] **Step 2: Add ViewModel install method**

Add this method to `SettingsViewModel`:

```kotlin
fun installAddonFromUrl(input: String) {
    viewModelScope.launch {
        val url = input.trim()
        if (url.isBlank()) {
            _uiState.update { it.copy(addonInstallStatus = "Addon URL is empty") }
            return@launch
        }

        _uiState.update {
            it.copy(
                isInstallingAddon = true,
                addonInstallStatus = "Checking addon..."
            )
        }

        val result = streamRepository.addCustomAddon(url)
        _uiState.update { state ->
            result.fold(
                onSuccess = { addon ->
                    state.copy(
                        isInstallingAddon = false,
                        addonInstallStatus = "${addon.name} installed"
                    )
                },
                onFailure = { error ->
                    state.copy(
                        isInstallingAddon = false,
                        addonInstallStatus = error.message?.takeIf { it.isNotBlank() }
                            ?: "Addon install failed"
                    )
                }
            )
        }
    }
}
```

- [ ] **Step 3: Add Settings dialog state**

In `SettingsScreen`, add:

```kotlin
var showAddonUrlDialog by remember { mutableStateOf(false) }
```

- [ ] **Step 4: Add addon row**

In the `Stremio addons` section rows, replace the rows list with:

```kotlin
rows = listOf(
    SettingsRow("Installed addons", uiState.addons.size.toString()),
    SettingsRow("Catalogs", uiState.catalogs.size.toString()),
    SettingsRow(
        label = "Add Stremio/AIOStreams addon",
        value = if (uiState.isInstallingAddon) "Installing..." else uiState.addonInstallStatus.ifBlank { "Paste URL" },
        onClick = { showAddonUrlDialog = true }
    )
)
```

- [ ] **Step 5: Add URL modal**

Below the existing Watchmode modal, add:

```kotlin
TextInputModal(
    isVisible = showAddonUrlDialog,
    title = "Addon URL",
    hint = "Paste Stremio manifest URL",
    initialValue = "",
    isPassword = true,
    onConfirm = { value ->
        viewModel.installAddonFromUrl(value)
        showAddonUrlDialog = false
    },
    onCancel = { showAddonUrlDialog = false }
)
```

- [ ] **Step 6: Build check**

Run:

```powershell
.\gradlew.bat :app:compileSideloadDebugKotlin --console=plain --no-parallel
```

Expected: Kotlin compile succeeds. Existing AGP/D8 warnings are acceptable if compile exits 0.

## Task 2: Extract Shared Stream Source Analysis

**Files:**
- Create: `app/src/main/kotlin/com/arflix/tv/data/stream/StreamSourceAnalysis.kt`
- Create: `app/src/test/kotlin/com/arflix/tv/data/stream/StreamSourceAnalysisTest.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/components/StreamSelector.kt`

- [ ] **Step 1: Write tests first**

Create `StreamSourceAnalysisTest.kt`:

```kotlin
package com.arflix.tv.data.stream

import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StreamSourceAnalysisTest {
    @Test
    fun `analysis detects 4k dolby vision atmos and cached debrid`() {
        val analysis = analyzeStreamSource(
            stream(
                source = "Movie.2026.2160p.DV.HDR10.Atmos.TrueHD.REMUX.mkv",
                quality = "Unknown",
                size = "52 GB",
                cached = true,
                addonName = "AIOStreams - RealDebrid"
            )
        )

        assertEquals("4K", analysis.resolutionLabel)
        assertEquals(4, analysis.resolutionScore)
        assertEquals("REMUX", analysis.releaseLabel)
        assertEquals("DV", analysis.visualTag)
        assertEquals("Atmos", analysis.audioLabel)
        assertTrue(analysis.isCachedOrDebridReady)
        assertEquals(52_000_000_000L, analysis.sizeBytes)
    }

    @Test
    fun `summary chooses best resolution then larger same tier source`() {
        val summary = summarizeStreamAvailability(
            listOf(
                stream("Movie 1080p WEB-DL 8 GB", "1080p", "8 GB"),
                stream("Movie 2160p WEB-DL 18 GB", "4K", "18 GB"),
                stream("Movie 2160p REMUX Atmos 52 GB", "4K", "52 GB")
            ),
            loadedAtMs = 123L
        )

        assertEquals("4K", summary.bestResolution)
        assertEquals("Atmos", summary.bestAudioTag)
        assertEquals("52 GB", summary.maxSizeLabel)
        assertEquals(3, summary.sourceCount)
    }

    @Test
    fun `summary returns null for no streams`() {
        assertEquals(null, summarizeStreamAvailability(emptyList(), loadedAtMs = 1L))
    }

    private fun stream(
        source: String,
        quality: String,
        size: String,
        cached: Boolean = false,
        addonName: String = "AIOStreams",
        sources: List<String> = emptyList()
    ) = StreamSource(
        source = source,
        addonName = addonName,
        addonId = "aiostreams",
        quality = quality,
        size = size,
        url = "https://example.com/${source.hashCode()}",
        behaviorHints = StreamBehaviorHints(cached = cached, filename = source),
        sources = sources
    )
}
```

- [ ] **Step 2: Run failing tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.data.stream.StreamSourceAnalysisTest" --console=plain --no-parallel
```

Expected: FAIL because `analyzeStreamSource` and `summarizeStreamAvailability` do not exist.

- [ ] **Step 3: Implement pure analysis helper**

Create `StreamSourceAnalysis.kt`:

```kotlin
package com.arflix.tv.data.stream

import com.arflix.tv.data.model.StreamSource
import java.util.Locale

data class StreamSourceAnalysis(
    val stream: StreamSource,
    val title: String,
    val addonLabel: String,
    val resolutionLabel: String,
    val resolutionScore: Int,
    val releaseLabel: String?,
    val releaseScore: Int,
    val codecLabel: String?,
    val visualTag: String?,
    val audioLabel: String?,
    val transportLabel: String?,
    val languageLabel: String?,
    val chips: List<String>,
    val sizeBytes: Long,
    val isCachedOrDebridReady: Boolean,
    val isDirectHttp: Boolean
)

data class StreamAvailabilitySummary(
    val bestResolution: String?,
    val bestVisualTag: String?,
    val bestAudioTag: String?,
    val isCachedOrDebridReady: Boolean,
    val sourceCount: Int,
    val maxSizeLabel: String?,
    val bestSourceLabel: String?,
    val loadedAtMs: Long
)

private object StreamAnalysisRegexes {
    val AV1 = Regex("""\bAV1\b""", RegexOption.IGNORE_CASE)
    val HEVC = Regex("""\b(HEVC|X265|H265)\b""", RegexOption.IGNORE_CASE)
    val H264 = Regex("""\b(H264|X264|AVC)\b""", RegexOption.IGNORE_CASE)
    val REMUX = Regex("""\bREMUX\b""", RegexOption.IGNORE_CASE)
    val BLURAY = Regex("""\b(BLURAY|BDRIP|BDREMUX)\b""", RegexOption.IGNORE_CASE)
    val WEBDL = Regex("""\b(WEB[- .]?DL|WEBDL)\b""", RegexOption.IGNORE_CASE)
    val WEBRIP = Regex("""\bWEB[- .]?RIP\b""", RegexOption.IGNORE_CASE)
    val HDTV = Regex("""\bHDTV\b""", RegexOption.IGNORE_CASE)
    val CAM = Regex("""\b(CAM|TS|TELESYNC|HDCAM)\b""", RegexOption.IGNORE_CASE)
    val ATMOS = Regex("""\bATMOS\b""", RegexOption.IGNORE_CASE)
    val TRUEHD = Regex("""\bTRUEHD\b""", RegexOption.IGNORE_CASE)
    val DTS = Regex("""\b(DTS[- .]?HD|DTS|DDP|EAC3|AC3|AAC)\b""", RegexOption.IGNORE_CASE)
    val DTS_X = Regex("""\bDTS[-_.: ]?X\b""", RegexOption.IGNORE_CASE)
    val DD_PLUS = Regex("""\b(DDP|DD\+|EAC-?3|E-?AC-?3)\b""", RegexOption.IGNORE_CASE)
    val DD = Regex("""\b(AC-?3|DD(?:[ ._-]?5[ ._-]?1)?|DOLBY[ ._-]?DIGITAL)\b""", RegexOption.IGNORE_CASE)
    val CH71 = Regex("""\b7[ .]?1\b""", RegexOption.IGNORE_CASE)
    val CH51 = Regex("""\b5[ .]?1\b""", RegexOption.IGNORE_CASE)
    val MULTI_AUDIO = Regex("""\b(MULTI|DUAL[ .-]?AUDIO|MULTI[ .-]?AUDIO)\b""", RegexOption.IGNORE_CASE)
    val LANGUAGE_HINT = Regex("""\b(ENG|ENGLISH|JPN|JAPANESE|KOR|KOREAN|SPA|SPANISH|FRE|FRENCH|GER|GERMAN|ITA|ITALIAN)\b""", RegexOption.IGNORE_CASE)
    val DV = Regex("""\b(DV|DoVi|Dolby[\s._-]*Vision)\b""", RegexOption.IGNORE_CASE)
    val HDR10_PLUS = Regex("""\b(HDR10\+|HDR10\s*PLUS|HDR\s*10\s*\+)\b""", RegexOption.IGNORE_CASE)
    val HDR10 = Regex("""\bHDR10\b""", RegexOption.IGNORE_CASE)
    val HDR = Regex("""\bHDR(10\+?|10)?\b""", RegexOption.IGNORE_CASE)
    val IMAX = Regex("""\bIMAX\b""", RegexOption.IGNORE_CASE)
    val WHITESPACE = Regex("""\s+""")
    val SIZE_PATTERN = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB|TIB|GIB|MIB|KIB)""", RegexOption.IGNORE_CASE)
}

fun analyzeStreamSource(stream: StreamSource): StreamSourceAnalysis {
    val rawTitle = stream.behaviorHints?.filename?.takeIf { it.isNotBlank() } ?: stream.source
    val searchBlob = buildString {
        append(stream.quality).append(' ')
        append(stream.source).append(' ')
        append(stream.addonName).append(' ')
        append(stream.behaviorHints?.filename.orEmpty()).append(' ')
        append(stream.url.orEmpty())
    }
    val addonLabel = stream.addonName.split(" - ").firstOrNull()?.trim().orEmpty()
        .ifBlank { stream.addonName }
    val resolutionLabel = when {
        searchBlob.contains("2160p", true) || searchBlob.contains("4K", true) -> "4K"
        searchBlob.contains("1080p", true) -> "1080p"
        searchBlob.contains("720p", true) -> "720p"
        StreamAnalysisRegexes.CAM.containsMatchIn(searchBlob) -> "CAM"
        else -> stream.quality.split(" ").firstOrNull()?.take(8) ?: "SD"
    }
    val releaseLabel = when {
        StreamAnalysisRegexes.REMUX.containsMatchIn(searchBlob) -> "REMUX"
        StreamAnalysisRegexes.BLURAY.containsMatchIn(searchBlob) -> "BluRay"
        StreamAnalysisRegexes.WEBDL.containsMatchIn(searchBlob) -> "WEB-DL"
        StreamAnalysisRegexes.WEBRIP.containsMatchIn(searchBlob) -> "WEBRip"
        StreamAnalysisRegexes.HDTV.containsMatchIn(searchBlob) -> "HDTV"
        StreamAnalysisRegexes.CAM.containsMatchIn(searchBlob) -> "CAM"
        else -> null
    }
    val visualTag = when {
        StreamAnalysisRegexes.DV.containsMatchIn(searchBlob) -> "DV"
        StreamAnalysisRegexes.HDR10_PLUS.containsMatchIn(searchBlob) -> "HDR10+"
        StreamAnalysisRegexes.HDR10.containsMatchIn(searchBlob) -> "HDR10"
        StreamAnalysisRegexes.HDR.containsMatchIn(searchBlob) -> "HDR"
        else -> null
    }
    val audioLabel = when {
        StreamAnalysisRegexes.ATMOS.containsMatchIn(searchBlob) -> "Atmos"
        StreamAnalysisRegexes.TRUEHD.containsMatchIn(searchBlob) -> "TrueHD"
        StreamAnalysisRegexes.CH71.containsMatchIn(searchBlob) -> "7.1"
        StreamAnalysisRegexes.CH51.containsMatchIn(searchBlob) -> "5.1"
        StreamAnalysisRegexes.DTS_X.containsMatchIn(searchBlob) -> "DTS:X"
        StreamAnalysisRegexes.DD_PLUS.containsMatchIn(searchBlob) -> "DD+"
        StreamAnalysisRegexes.DD.containsMatchIn(searchBlob) -> "DD"
        StreamAnalysisRegexes.DTS.containsMatchIn(searchBlob) -> StreamAnalysisRegexes.DTS.find(searchBlob)?.value?.uppercase(Locale.US)
        else -> null
    }
    val isDebridReady = stream.behaviorHints?.cached == true || searchBlob.contains("debrid", true) ||
        searchBlob.contains("realdebrid", true) || searchBlob.contains("real-debrid", true) ||
        searchBlob.contains("torbox", true) || searchBlob.contains("premiumize", true) ||
        searchBlob.contains("alldebrid", true) || searchBlob.contains("[RD+]", true) ||
        searchBlob.contains("[TB+]", true)
    val transportLabel = when {
        stream.behaviorHints?.cached == true -> "Cached"
        isDebridReady -> "Debrid"
        !stream.infoHash.isNullOrBlank() || stream.sources.isNotEmpty() ||
            addonLabel.contains("torrent", true) -> "Torrent"
        !stream.url.isNullOrBlank() && stream.url.startsWith("http", true) -> "Direct"
        else -> null
    }
    val codecLabel = when {
        StreamAnalysisRegexes.AV1.containsMatchIn(searchBlob) -> "AV1"
        StreamAnalysisRegexes.HEVC.containsMatchIn(searchBlob) -> "HEVC"
        StreamAnalysisRegexes.H264.containsMatchIn(searchBlob) -> "H.264"
        else -> null
    }
    val languageLabel = when {
        StreamAnalysisRegexes.MULTI_AUDIO.containsMatchIn(searchBlob) -> "Multi-audio"
        stream.subtitles.map { it.lang }.distinct().size > 1 -> "${stream.subtitles.map { it.lang }.distinct().size} langs"
        else -> StreamAnalysisRegexes.LANGUAGE_HINT.find(searchBlob)?.value?.uppercase(Locale.US)
    }
    val chips = buildList {
        add(addonLabel)
        transportLabel?.let(::add)
        releaseLabel?.let(::add)
        codecLabel?.let(::add)
        visualTag?.let(::add)
        audioLabel?.let(::add)
        if (stream.size.isNotBlank()) add(stream.size)
    }.distinct()

    return StreamSourceAnalysis(
        stream = stream,
        title = cleanSourceDisplayTitle(rawTitle),
        addonLabel = addonLabel,
        resolutionLabel = resolutionLabel,
        resolutionScore = resolutionScore(resolutionLabel),
        releaseLabel = releaseLabel,
        releaseScore = releaseScore(releaseLabel),
        codecLabel = codecLabel,
        visualTag = visualTag,
        audioLabel = audioLabel,
        transportLabel = transportLabel,
        languageLabel = languageLabel,
        chips = chips,
        sizeBytes = parseSizeString(stream.size),
        isCachedOrDebridReady = isDebridReady,
        isDirectHttp = !stream.url.isNullOrBlank() && stream.url.startsWith("http", true)
    )
}

fun summarizeStreamAvailability(
    streams: List<StreamSource>,
    loadedAtMs: Long = System.currentTimeMillis()
): StreamAvailabilitySummary? {
    val analyses = streams.map(::analyzeStreamSource)
    if (analyses.isEmpty()) return null
    val best = analyses.maxWith(
        compareBy<StreamSourceAnalysis> { it.resolutionScore }
            .thenBy { it.releaseScore }
            .thenBy { it.sizeBytes }
            .thenBy { if (it.isCachedOrDebridReady) 1 else 0 }
    )
    return StreamAvailabilitySummary(
        bestResolution = best.resolutionLabel,
        bestVisualTag = analyses.mapNotNull { it.visualTag }.firstOrNull(),
        bestAudioTag = analyses.mapNotNull { it.audioLabel }.firstOrNull(),
        isCachedOrDebridReady = analyses.any { it.isCachedOrDebridReady },
        sourceCount = streams.size,
        maxSizeLabel = analyses.maxByOrNull { it.sizeBytes }?.stream?.size?.takeIf { it.isNotBlank() },
        bestSourceLabel = best.addonLabel,
        loadedAtMs = loadedAtMs
    )
}

fun parseSizeString(size: String): Long {
    val match = StreamAnalysisRegexes.SIZE_PATTERN.find(size) ?: return 0L
    val value = match.groupValues[1].toDoubleOrNull() ?: return 0L
    return when (match.groupValues[2].uppercase(Locale.US)) {
        "TB", "TIB" -> (value * 1_000_000_000_000L).toLong()
        "GB", "GIB" -> (value * 1_000_000_000L).toLong()
        "MB", "MIB" -> (value * 1_000_000L).toLong()
        "KB", "KIB" -> (value * 1_000L).toLong()
        else -> 0L
    }
}

private fun cleanSourceDisplayTitle(raw: String): String =
    raw.replace('\n', ' ')
        .replace('\r', ' ')
        .replace(StreamAnalysisRegexes.WHITESPACE, " ")
        .trim()
        .ifBlank { "Unknown source" }
        .take(92)

private fun resolutionScore(label: String): Int = when (label) {
    "4K" -> 4
    "1080p" -> 3
    "720p" -> 2
    "CAM" -> 0
    else -> 1
}

private fun releaseScore(label: String?): Int = when (label) {
    "REMUX" -> 5
    "BluRay" -> 4
    "WEB-DL" -> 3
    "WEBRip" -> 2
    "HDTV" -> 1
    else -> 0
}
```

- [ ] **Step 4: Run tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.data.stream.StreamSourceAnalysisTest" --console=plain --no-parallel
```

Expected: PASS.

- [ ] **Step 5: Update `StreamSelector`**

Import:

```kotlin
import com.arflix.tv.data.stream.StreamSourceAnalysis
import com.arflix.tv.data.stream.analyzeStreamSource
import com.arflix.tv.data.stream.parseSizeString
```

Replace `private data class SourcePresentation` construction in `presentSource` so it delegates to `analyzeStreamSource(stream)` and maps fields into the existing UI data class. Keep UI colors and image badge selection inside `StreamSelector`.

Replace private `getSizeBytes(stream)` body with:

```kotlin
private fun getSizeBytes(stream: StreamSource): Long = parseSizeString(stream.size)
```

- [ ] **Step 6: Run source selector/autoplay tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.ui.screens.details.AutoPlaySourcePlannerTest" --tests "com.arflix.tv.data.stream.StreamSourceAnalysisTest" --console=plain --no-parallel
```

Expected: PASS.

## Task 3: Add Stream Availability Repository

**Files:**
- Create: `app/src/main/kotlin/com/arflix/tv/data/repository/StreamAvailabilityRepository.kt`
- Create: `app/src/main/kotlin/com/arflix/tv/di/StreamSummaryModule.kt`
- Create: `app/src/test/kotlin/com/arflix/tv/data/repository/StreamAvailabilityRepositoryTest.kt`

- [ ] **Step 1: Write repository tests**

Create `StreamAvailabilityRepositoryTest.kt`:

```kotlin
package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class StreamAvailabilityRepositoryTest {
    @Test
    fun `movie summary uses imdb id and summarizes streams`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            movieStreams = listOf(stream("Movie 2160p Atmos 44 GB", "4K", "44 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val summary = repository.movieSummary("tt123", "Movie", 2026)

        assertEquals("tt123", resolver.lastMovieImdbId)
        assertEquals("4K", summary?.bestResolution)
        assertEquals("Atmos", summary?.bestAudioTag)
    }

    @Test
    fun `series summary uses supplied next episode`() = runTest {
        val resolver = FakeStreamSummaryResolver(
            episodeStreams = listOf(stream("Show S02E04 1080p 5.1 8 GB", "1080p", "8 GB"))
        )
        val repository = StreamAvailabilityRepository(resolver)

        val summary = repository.episodeSummary(
            imdbId = "tt456",
            title = "Show",
            year = 2025,
            season = 2,
            episode = 4,
            tmdbId = 99,
            genreIds = emptyList(),
            originalLanguage = "en"
        )

        assertEquals(2 to 4, resolver.lastEpisode)
        assertEquals("1080p", summary?.bestResolution)
        assertEquals("5.1", summary?.bestAudioTag)
    }

    @Test
    fun `missing imdb id returns null without resolver call`() = runTest {
        val resolver = FakeStreamSummaryResolver()
        val repository = StreamAvailabilityRepository(resolver)

        val summary = repository.movieSummary(null, "Movie", 2026)

        assertEquals(null, summary)
        assertEquals(null, resolver.lastMovieImdbId)
    }

    private class FakeStreamSummaryResolver(
        private val movieStreams: List<StreamSource> = emptyList(),
        private val episodeStreams: List<StreamSource> = emptyList()
    ) : StreamSummaryResolver {
        var lastMovieImdbId: String? = null
        var lastEpisode: Pair<Int, Int>? = null

        override suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource> {
            lastMovieImdbId = imdbId
            return movieStreams
        }

        override suspend fun episodeStreams(
            imdbId: String,
            title: String,
            year: Int?,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            genreIds: List<Int>,
            originalLanguage: String?
        ): List<StreamSource> {
            lastEpisode = season to episode
            return episodeStreams
        }
    }

    private fun stream(source: String, quality: String, size: String) = StreamSource(
        source = source,
        addonName = "AIOStreams",
        addonId = "aiostreams",
        quality = quality,
        size = size,
        url = "https://example.com/${source.hashCode()}",
        behaviorHints = StreamBehaviorHints(cached = true, filename = source)
    )
}
```

- [ ] **Step 2: Run failing tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.data.repository.StreamAvailabilityRepositoryTest" --console=plain --no-parallel
```

Expected: FAIL because `StreamAvailabilityRepository` and `StreamSummaryResolver` do not exist.

- [ ] **Step 3: Introduce resolver seam**

Define a small interface in the repository file:

```kotlin
interface StreamSummaryResolver {
    suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource>
    suspend fun episodeStreams(
        imdbId: String,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?
    ): List<StreamSource>
}
```

Add an injected implementation:

```kotlin
class StreamRepositorySummaryResolver @Inject constructor(
    private val streamRepository: StreamRepository
) : StreamSummaryResolver {
    override suspend fun movieStreams(imdbId: String, title: String, year: Int?): List<StreamSource> =
        streamRepository.resolveMovieStreams(imdbId = imdbId, title = title, year = year).streams

    override suspend fun episodeStreams(
        imdbId: String,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?
    ): List<StreamSource> =
        streamRepository.resolveEpisodeStreams(
            imdbId = imdbId,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            title = title
        ).streams
}
```

- [ ] **Step 4: Implement repository**

Implement `StreamAvailabilityRepository`:

```kotlin
class StreamAvailabilityRepository @Inject constructor(
    private val resolver: StreamSummaryResolver
) {
    private val cache = LinkedHashMap<String, StreamAvailabilitySummary>()
    private val lock = Any()

    suspend fun movieSummary(
        imdbId: String?,
        title: String,
        year: Int?,
        timeoutMs: Long = 3_500L
    ): StreamAvailabilitySummary? {
        val id = imdbId?.trim().orEmpty()
        if (id.isBlank()) return null
        val key = "movie:$id"
        cached(key)?.let { return it }
        return withTimeoutOrNull(timeoutMs) {
            summarizeStreamAvailability(resolver.movieStreams(id, title, year))
        }?.also { put(key, it) }
    }

    suspend fun episodeSummary(
        imdbId: String?,
        title: String,
        year: Int?,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        genreIds: List<Int>,
        originalLanguage: String?,
        timeoutMs: Long = 3_500L
    ): StreamAvailabilitySummary? {
        val id = imdbId?.trim().orEmpty()
        if (id.isBlank()) return null
        val safeSeason = season.coerceAtLeast(1)
        val safeEpisode = episode.coerceAtLeast(1)
        val key = "series:$id:$safeSeason:$safeEpisode"
        cached(key)?.let { return it }
        return withTimeoutOrNull(timeoutMs) {
            summarizeStreamAvailability(
                resolver.episodeStreams(
                    imdbId = id,
                    title = title,
                    year = year,
                    season = safeSeason,
                    episode = safeEpisode,
                    tmdbId = tmdbId,
                    genreIds = genreIds,
                    originalLanguage = originalLanguage
                )
            )
        }?.also { put(key, it) }
    }

    private fun cached(key: String): StreamAvailabilitySummary? = synchronized(lock) { cache[key] }

    private fun put(key: String, value: StreamAvailabilitySummary) = synchronized(lock) {
        cache[key] = value
        while (cache.size > 80) {
            val first = cache.keys.firstOrNull() ?: return@synchronized
            cache.remove(first)
        }
    }
}
```

- [ ] **Step 5: Add Hilt binding module**

Create `StreamSummaryModule.kt`:

```kotlin
package com.arflix.tv.di

import com.arflix.tv.data.repository.StreamRepositorySummaryResolver
import com.arflix.tv.data.repository.StreamSummaryResolver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StreamSummaryModule {
    @Binds
    abstract fun bindStreamSummaryResolver(
        impl: StreamRepositorySummaryResolver
    ): StreamSummaryResolver
}
```

- [ ] **Step 6: Run tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.data.repository.StreamAvailabilityRepositoryTest" --console=plain --no-parallel
```

Expected: PASS.

## Task 4: Add Stream Summary To Collection Preview ViewModel

**Files:**
- Modify: `app/src/main/kotlin/com/arflix/tv/data/repository/MediaRepository.kt`
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/screens/collections/CollectionDetailsScreen.kt`
- Modify: `app/src/test/kotlin/com/arflix/tv/ui/screens/collections/CollectionDetailsViewModelTest.kt`

- [ ] **Step 1: Expose existing IMDb-id resolver**

In `MediaRepository`, add this public helper near `getCachedImdbId`:

```kotlin
suspend fun getOrResolveImdbId(mediaType: MediaType, mediaId: Int): String? {
    return resolveImdbId(mediaType, mediaId)
}
```

This deliberately reuses the existing private `resolveImdbId` implementation and its cache.

- [ ] **Step 2: Add preview field**

Update `CollectionPreviewState`:

```kotlin
val streamSummary: StreamAvailabilitySummary? = null,
val isLoadingStreamSummary: Boolean = false
```

Import `com.arflix.tv.data.stream.StreamAvailabilitySummary`.

- [ ] **Step 3: Inject repository**

Update `CollectionDetailsViewModel` constructor:

```kotlin
private val streamAvailabilityRepository: StreamAvailabilityRepository
```

Update tests to pass a fake or relaxed mock.

- [ ] **Step 4: Preserve immediate preview speed**

In `focusPreview`, keep immediate `_uiState` update unchanged except add:

```kotlin
isLoadingStreamSummary = true
```

for non-cached previews.

- [ ] **Step 5: Resolve summary inside existing debounce job**

After `enrichedItem` is built, resolve IMDb id once and call:

```kotlin
val resolvedImdbId = runCatching {
    mediaRepository.getOrResolveImdbId(enrichedItem.mediaType, enrichedItem.id)
}.getOrNull()

val streamSummary = runCatching {
    when (enrichedItem.mediaType) {
        MediaType.MOVIE -> streamAvailabilityRepository.movieSummary(
            imdbId = resolvedImdbId,
            title = enrichedItem.title,
            year = enrichedItem.year.toIntOrNull()
        )
        MediaType.TV -> {
            val next = enrichedItem.nextEpisode
            streamAvailabilityRepository.episodeSummary(
                imdbId = resolvedImdbId,
                title = enrichedItem.title,
                year = enrichedItem.year.toIntOrNull(),
                season = next?.seasonNumber ?: 1,
                episode = next?.episodeNumber ?: 1,
                tmdbId = enrichedItem.id,
                genreIds = enrichedItem.genreIds,
                originalLanguage = enrichedItem.originalLanguage
            )
        }
    }
}.getOrNull()
```

- [ ] **Step 6: Include summary in preview copy**

Set:

```kotlin
streamSummary = streamSummary,
isLoadingStreamSummary = false
```

and keep the existing stale key guard:

```kotlin
if (previewKey(_uiState.value.preview.item) != key) return@launch
```

- [ ] **Step 7: Add tests**

Extend `CollectionDetailsViewModelTest` with:

```kotlin
@Test
fun `focused preview applies stream summary for current item`() = runTest {
    val streamAvailabilityRepository = mockk<StreamAvailabilityRepository>()
    coEvery { streamAvailabilityRepository.movieSummary(any(), any(), any(), any()) } returns
        StreamAvailabilitySummary("4K", "DV", "Atmos", true, 8, "52 GB", "AIOStreams", 1L)
    // focus a movie, advance debounce, assert summary exists.
}

@Test
fun `focused preview ignores stale stream summary when focus changes quickly`() = runTest {
    // first summary delays, second returns; assert final preview item gets only second summary.
}
```

- [ ] **Step 8: Run tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModelTest" --console=plain --no-parallel
```

Expected: PASS.

## Task 5: Implement Kodi-Inspired Catalog UI

**Files:**
- Modify: `app/src/main/kotlin/com/arflix/tv/ui/screens/collections/CollectionDetailsScreen.kt`

- [ ] **Step 1: Remove action panel from catalog preview**

In `CollectionMasterDetailList`, remove:

```kotlin
firstActionFocusRequester
isActionPanelFocused
pendingListFocusRestore
onRight = { firstActionFocusRequester.requestFocus() }
CollectionPreviewPanel action callbacks
```

Rows should treat right as no-op in this pass. Enter/click still opens details through `onItemClick`.

- [ ] **Step 2: Narrow the list**

Change sidebar width from:

```kotlin
.width(530.dp)
```

to:

```kotlin
.width(360.dp)
```

Change row height from `54.dp` to `44.dp`. Change title font size from `17.sp` to `15.sp`, metadata from `12.sp` to `11.sp`.

- [ ] **Step 3: Make fanart full-screen**

Move backdrop rendering to the root `Box` around the whole master-detail layout. Use:

```kotlin
AsyncImage(
    model = displayPreview.item?.backdrop?.takeIf { it.isNotBlank() } ?: displayPreview.item?.image,
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = Modifier.fillMaxSize(),
    alpha = 0.72f
)
```

Add horizontal and vertical gradients over the full screen:

```kotlin
Brush.horizontalGradient(
    listOf(
        appBackgroundDark().copy(alpha = 0.92f),
        appBackgroundDark().copy(alpha = 0.42f),
        Color.Black.copy(alpha = 0.18f)
    )
)
```

- [ ] **Step 4: Rebuild preview as info-only**

Replace `CollectionPreviewPanel` content with:

- large title or clearlogo when available later
- `previewMeta(item, preview)`
- stream badges row from `preview.streamSummary`
- overview
- large poster docked right/bottom

No `PreviewActionButton` calls should remain in the catalog preview.

- [ ] **Step 5: Add stream badge composable**

Add:

```kotlin
@Composable
private fun StreamSummaryFlags(preview: CollectionPreviewState) {
    val summary = preview.streamSummary
    val labels = when {
        summary != null -> buildList {
            summary.bestResolution?.let(::add)
            summary.bestVisualTag?.let(::add)
            summary.bestAudioTag?.let(::add)
            if (summary.isCachedOrDebridReady) add("Cached")
            if (summary.sourceCount > 0) add("${summary.sourceCount} sources")
            summary.maxSizeLabel?.let { add("$it max") }
        }
        preview.isLoadingStreamSummary -> listOf("Sources ...")
        else -> emptyList()
    }
    if (labels.isEmpty()) return
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        labels.take(6).forEach { label ->
            Text(
                text = label,
                color = TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Black.copy(alpha = 0.42f))
                    .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(5.dp))
                    .padding(horizontal = 9.dp, vertical = 5.dp)
            )
        }
    }
}
```

- [ ] **Step 6: Improve focus glow**

For `CollectionTextRow`, focused state should use:

```kotlin
graphicsLayer {
    scaleX = if (isFocused) 1.018f else 1f
    scaleY = if (isFocused) 1.018f else 1f
}
```

and background:

```kotlin
Brush.horizontalGradient(
    listOf(Color.White.copy(alpha = 0.16f), Color.White.copy(alpha = 0.05f))
)
```

Keep border radius at `7.dp`.

- [ ] **Step 7: Compile**

Run:

```powershell
.\gradlew.bat :app:compileSideloadDebugKotlin --console=plain --no-parallel
```

Expected: PASS.

## Task 6: End-To-End Tests And Device Verification

**Files:**
- No new source files unless fixes are required by failures.

- [ ] **Step 1: Run focused unit tests**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --tests "com.arflix.tv.data.stream.StreamSourceAnalysisTest" --tests "com.arflix.tv.data.repository.StreamAvailabilityRepositoryTest" --tests "com.arflix.tv.ui.screens.collections.CollectionDetailsViewModelTest" --console=plain --no-parallel
```

Expected: PASS.

- [ ] **Step 2: Run full unit suite**

Run:

```powershell
.\gradlew.bat :app:testSideloadDebugUnitTest --console=plain --no-parallel
```

Expected: PASS. Existing warnings are acceptable; failing tests must be fixed.

- [ ] **Step 3: Build sideload APK**

Run:

```powershell
.\gradlew.bat :app:assembleSideloadDebug --console=plain --no-parallel
```

Expected APK:

```text
app/build/outputs/apk/sideload/debug/app-sideload-debug.apk
```

- [ ] **Step 4: Install on Google TV 4K Streamer**

Use the known wireless debugging target if still connected:

```powershell
adb connect 192.168.1.8:43459
adb install -r app/build/outputs/apk/sideload/debug/app-sideload-debug.apk
```

If the port changed, ask the user for the current wireless debugging IP:port and pairing code.

- [ ] **Step 5: Install AIOStreams in app settings**

On the TV app:

1. Open Settings.
2. Open `Stremio addons`.
3. Choose `Add Stremio/AIOStreams addon`.
4. Paste the user-provided AIOStreams manifest URL.
5. Confirm the row says `AIOStreams installed`.

Do not paste the URL into source code or docs.

- [ ] **Step 6: Manual TV verification**

Verify on Google TV:

- Disney/Netflix/Max/Viaplay collection opens in Kodi-style layout.
- List is compact and D-pad up/down is smooth.
- Fanart fills the whole screen and changes without blocking scroll.
- Enter opens existing details page.
- No right-side catalog action buttons are visible.
- Focused movie shows `4K`/`HDR`/`DV`/audio/source count badges after a short async delay when AIOStreams has sources.
- Fast scrolling does not leave stale badges on the wrong title.
- Player source selector still shows source badges correctly.

## Performance Guardrails

- Catalog item focus must update fanart/title immediately from existing list data.
- Stream summary lookup must run only after debounce.
- Stream summary lookup must be cancelable or stale-guarded.
- No stream lookup should run for every visible row.
- No catalog render path should wait for AIOStreams.
- Summary timeout should fail closed by hiding badges, not by showing an error dialog.

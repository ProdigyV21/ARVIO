package com.arflix.tv.data.repository

import android.os.Bundle
import android.os.SystemClock
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.di.RepositoryAccessEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.time.YearMonth
import java.time.ZoneId
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Test
import org.junit.runner.RunWith

/** Opt-in account benchmark: only disposable calendar metadata is cleared, never account/settings. */
@RunWith(AndroidJUnit4::class)
class ReleaseCalendarLiveDeviceTest {
    @Test fun configuredWatchlistsLoadColdAndWarmWithoutLosingReleases() = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("calendarLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val access = EntryPointAccessors.fromApplication(instrumentation.targetContext, RepositoryAccessEntryPoint::class.java)
        val repository = access.releaseCalendarRepository()
        val profile = access.profileManager().getProfileIdSync()
        val month = YearMonth.now()
        val timezone = ZoneId.systemDefault()
        repository.invalidateMetadata()
        val start = SystemClock.elapsedRealtime()
        var firstReleaseMs: Long? = null
        var lastReleaseMs = 0L
        val discovered = mutableSetOf<String>()
        var lists = CalendarWatchlists(profile, emptyMap())
        val cold = repository.loadCalendar(profile, month, timezone, "NL") { progress ->
            lists = progress.watchlists
            if (firstReleaseMs == null && progress.month.entries.isNotEmpty()) {
                firstReleaseMs = SystemClock.elapsedRealtime() - start
            }
            val ids = progress.month.entries.map { it.id }.toSet()
            if (ids.any { it !in discovered }) lastReleaseMs = SystemClock.elapsedRealtime() - start
            discovered.addAll(ids)
        }
        val coldMs = SystemClock.elapsedRealtime() - start
        assertTrue("This live test requires configured watchlist titles", mergeCalendarWatchlists(lists.items).isNotEmpty())
        assertTrue("The configured account must have releases this month", cold.entries.isNotEmpty())
        val warmStart = SystemClock.elapsedRealtime()
        var warmFirstMs: Long? = null
        val warm = repository.loadMonth(lists, month, timezone, "NL") { progress ->
            if (warmFirstMs == null && progress.entries.isNotEmpty()) warmFirstMs = SystemClock.elapsedRealtime() - warmStart
        }
        val warmMs = SystemClock.elapsedRealtime() - warmStart
        assertEquals("Warm loading must not omit releases", cold.entries.map { it.id }.toSet(), warm.entries.map { it.id }.toSet())
        instrumentation.sendStatus(0, Bundle().apply {
            putString("stream", "CALENDAR_LIVE titles=${mergeCalendarWatchlists(lists.items).size} releases=${cold.entries.size} " +
                "coldFirst=${firstReleaseMs}ms coldLastRelease=${lastReleaseMs}ms coldComplete=${coldMs}ms warmFirst=${warmFirstMs}ms warmComplete=${warmMs}ms " +
                "providerWarnings=${lists.warnings.size} metadataWarnings=${cold.warnings.size - lists.warnings.size}\n")
        })
        val failedTitles = mergeCalendarWatchlists(lists.items).filter { title ->
            cold.warnings.any { it == "Release dates for ${title.media.title} are unavailable." }
        }
        for (title in failedTitles.take(2)) {
            val status = try {
                if (title.media.mediaType == com.arflix.tv.data.model.MediaType.TV) {
                    access.tmdbApi().getTvDetails(title.media.id, com.arflix.tv.util.Constants.TMDB_API_KEY)
                } else access.tmdbApi().getMovieDetails(title.media.id, com.arflix.tv.util.Constants.TMDB_API_KEY)
                "recovered"
            } catch (error: retrofit2.HttpException) { "HTTP_${error.code()}" }
            catch (_: Exception) { "network_failure" }
            instrumentation.sendStatus(0, Bundle().apply {
                putString("stream", "CALENDAR_METADATA_SAMPLE type=${title.media.mediaType} status=$status\n")
            })
        }
    }
}

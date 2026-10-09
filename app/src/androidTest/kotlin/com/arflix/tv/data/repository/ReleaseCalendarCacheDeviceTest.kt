package com.arflix.tv.data.repository

import android.os.SystemClock
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.CalendarRelease
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import java.io.File
import java.time.LocalDate
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

/** Measures the real disk restore path on-device, without provider or network access. */
@RunWith(AndroidJUnit4::class)
class ReleaseCalendarCacheDeviceTest {
    @Test fun savedMonthRestoresAfterCacheRecreation() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val directory = File(context.cacheDir, "calendar-speed-test-${System.nanoTime()}")
        val entries = List(250) { index ->
            CalendarRelease("episode-$index", MediaItem(index + 1, "Calendar fixture $index",
                mediaType = MediaType.TV, backdrop = "https://example.test/art/$index.jpg"),
                LocalDate.of(2026, 10, index % 31 + 1), kind = CalendarReleaseKind.EPISODE,
                seasonNumber = 2, episodeNumber = index + 1, sourceIds = setOf("arvio", "trakt"))
        }
        try {
            ReleaseCalendarCache(directory).writeMonth("fixture", CalendarMonthPreview(entries, mapOf("all" to 250)))
            val recreated = ReleaseCalendarCache(directory)
            val started = SystemClock.elapsedRealtimeNanos()
            val restored = recreated.readMonth("fixture")
            val elapsedMs = (SystemClock.elapsedRealtimeNanos() - started) / 1_000_000.0
            assertNotNull(restored)
            assertEquals(entries, restored!!.entries)
            File(context.getExternalFilesDir(null), "calendar-cache-timing.json").writeText(
                """{"entries":250,"restoreMs":$elapsedMs,"networkRequests":0,"scope":"on-device disk restore, not first-ever network load"}"""
            )
        } finally {
            directory.deleteRecursively()
        }
    }
}

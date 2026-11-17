package com.arflix.tv.util

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ObservedFrameRateCacheTest {
    private fun key(url: String = "https://video.test/play?id=1", headers: Map<String, String> = emptyMap(),
        filename: String? = null, size: Long? = null, content: String = "movie|1") =
        observedFrameRateKey(url, headers, filename, size, content, "qa")!!

    @Test fun completeRequestsAndCredentialsRemainDistinctAndPrivate() {
        assertNotEquals(key(), key("https://video.test/play?id=2"))
        val secret = key(headers = mapOf("Authorization" to "secret-token"))
        assertNotEquals(key(), secret)
        assertTrue(secret.matches(Regex("[a-f0-9]{64}")))
        assertEquals(secret, key(headers = mapOf("authorization" to "secret-token", "Range" to "bytes=0-1")))
    }
    @Test fun specificFileIdentitySurvivesCdnRotationButNotContentChanges() {
        val first = key("https://cdn1.test/a?token=1", filename = "episode.mkv", size = 1234)
        assertEquals(first, key("https://cdn2.test/b?token=2", filename = "episode.mkv", size = 1234))
        assertNotEquals(first, key(filename = "episode.mkv", size = 1235))
        assertNotEquals(first, key(filename = "episode.mkv", size = 1234, content = "movie|2"))
    }
    @Test fun liveAndUnsupportedInputsAreNeverCached() {
        for (url in listOf("file:///tmp/a", "https://a/live/channel", "https://a/a.m3u8?x=1", "https://a/a.mpd", "https://a/a.ts")) {
            assertNull(observedFrameRateKey(url, emptyMap(), null, null, "x", "qa"))
        }
    }
    @Test fun lruAndCorrectedRatesReplaceOldObservations() {
        ObservedFrameRateCache(capacity = 2).use { cache ->
            cache.record(key(), 24f)
            cache.record(key(content = "2"), 25f)
            assertEquals(24f, cache.get(key()))
            cache.record(key(content = "3"), 30f)
            assertNull(cache.get(key(content = "2")))
            cache.record(key(), 24000f / 1001f)
            assertEquals(24000f / 1001f, cache.get(key()))
            cache.record(key(), Float.NaN)
            assertEquals(24000f / 1001f, cache.get(key()))
        }
    }
    @Test fun persistenceRejectsExpiredFutureAndMalformedEntries() = runBlocking {
        var stored = listOf<ObservedFrameRate>()
        var now = 1_000L
        val storage = object : FrameRateStorage {
            override fun read() = stored
            override fun write(entries: List<ObservedFrameRate>) { stored = entries }
        }
        ObservedFrameRateCache(storage, { now }).use { cache -> cache.record(key(), 25f); cache.flush() }
        stored = stored + listOf(ObservedFrameRate(null, 30f, now), ObservedFrameRate("not-a-hash", 24f, now),
            ObservedFrameRate(key(content = "future"), 24f, now + 1))
        ObservedFrameRateCache(storage, { now }).use { cache ->
            cache.load()
            assertEquals(25f, cache.get(key()))
            assertNull(cache.get(key(content = "future")))
            now += 8L * 24 * 60 * 60 * 1000
            assertNull(cache.get(key()))
        }
    }
    @Test fun damagedStorageDoesNotPreventPlayback() = runBlocking {
        val storage = object : FrameRateStorage {
            override fun read(): List<ObservedFrameRate> = error("damaged")
            override fun write(entries: List<ObservedFrameRate>) = error("disk full")
        }
        ObservedFrameRateCache(storage).use { cache ->
            cache.load(); cache.record(key(), 24f); cache.flush()
            assertEquals(24f, cache.get(key()))
        }
    }
    @Test fun cachedRatesNeverStandDownDecoderCorrections() {
        ObservedFrameRateCache().use { cache ->
            cache.record(key(), 24f)
            val observed = PlaybackFrameRate()
            observed.onInputFormat(24f)
            repeat(160) { observed.onFrame(it * 40_000L, 25f) }
            cache.record(key(), observed.rate.value)
            assertEquals(25f, cache.get(key()))
        }
    }
}

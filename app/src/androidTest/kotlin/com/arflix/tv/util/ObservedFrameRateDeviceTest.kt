package com.arflix.tv.util

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class ObservedFrameRateDeviceTest {
    @Test fun androidAtomicCachePersistsOnlyHashedIdentityAndAcceptsDecoderCorrection() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val key = observedFrameRateKey("https://qa.invalid/video?token=private-qa",
            mapOf("Authorization" to "Bearer private-qa"), "private-qa.mkv", 1_234_567, "qa-test", "qa")!!
        val cache = PlaybackFrameRates.get(context)
        cache.load()
        cache.record(key, 24f)
        cache.flush()
        assertEquals(24f, cache.get(key))
        cache.record(key, 24000f / 1001f)
        cache.flush()
        assertEquals(24000f / 1001f, cache.get(key))
        val stored = File(context.filesDir, "observed-frame-rates-v1.json").readText()
        assertTrue(stored.contains(key))
        assertFalse(stored.contains("private-qa"))
        assertFalse(stored.contains("Authorization"))
        assertFalse(stored.contains("https://"))
    }
}

package com.arflix.tv.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.lang.reflect.Constructor
import java.util.Collections
import java.util.concurrent.atomic.AtomicInteger
import java.time.OffsetDateTime

class IptvRepositoryOptimizationTest {

    // Helper to instantiate private class BackslashEscapeSanitizingInputStream using reflection
    private fun createSanitizingStream(input: InputStream): InputStream {
        val clazz = Class.forName("com.arflix.tv.data.repository.IptvRepository\$BackslashEscapeSanitizingInputStream")
        val constructor = clazz.getDeclaredConstructor(InputStream::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(input) as InputStream
    }

    @Test
    fun testBackslashSanitizationBulkRead() {
        // \" -> "
        // \n -> newline
        // control chars (0x01) -> space (0x20)
        // regular chars not escaped (e.g. \y) -> keep y
        val inputStr = "Hello\\\"World\\nTest\\yDone\u0001Control"
        val expectedStr = "Hello\"World\nTestyDone Control"

        val rawStream = ByteArrayInputStream(inputStr.toByteArray(Charsets.UTF_8))
        val sanitizingStream = createSanitizingStream(rawStream)

        val buffer = ByteArray(100)
        val readCount = sanitizingStream.read(buffer, 0, buffer.size)

        val result = String(buffer, 0, readCount, Charsets.UTF_8)
        assertEquals(expectedStr, result)
    }

    @Test
    fun testBackslashSanitizationByteByByteRead() {
        val inputStr = "Hello\\\"World\\nTest\\yDone\u0001Control"
        val expectedStr = "Hello\"World\nTestyDone Control"

        val rawStream = ByteArrayInputStream(inputStr.toByteArray(Charsets.UTF_8))
        val sanitizingStream = createSanitizingStream(rawStream)

        val sb = StringBuilder()
        while (true) {
            val b = sanitizingStream.read()
            if (b == -1) break
            sb.append(b.toChar())
        }

        assertEquals(expectedStr, sb.toString())
    }

    @Test
    fun testBackslashSanitizationBoundary() {
        // Backslash at the boundary of buffer size
        val inputStr = "A\\nB" // length 4
        val rawStream = ByteArrayInputStream(inputStr.toByteArray(Charsets.UTF_8))
        val sanitizingStream = createSanitizingStream(rawStream)

        // Read exactly up to the backslash first (2 bytes: 'A', '\')
        // Actually, let's read with buffer size 2
        val buffer = ByteArray(2)
        val read1 = sanitizingStream.read(buffer, 0, 2)
        assertEquals(2, read1)
        // Since '\' is followed by 'n', our stream reads lookahead 'n' from the underlying stream,
        // maps it to '\n' and puts it into buffer[1]. So buffer should have ['A', '\n'].
        assertEquals('A', buffer[0].toInt().toChar())
        assertEquals('\n', buffer[1].toInt().toChar())

        // Next read should get the remaining 'B'
        val read2 = sanitizingStream.read(buffer, 0, 2)
        assertEquals(1, read2)
        assertEquals('B', buffer[0].toInt().toChar())
    }

    @Test
    fun testBackslashSanitizationTrailingBackslash() {
        val inputStr = "A\\" // Traling backslash
        val rawStream = ByteArrayInputStream(inputStr.toByteArray(Charsets.UTF_8))
        val sanitizingStream = createSanitizingStream(rawStream)

        val buffer = ByteArray(5)
        val read = sanitizingStream.read(buffer, 0, 5)
        assertEquals(2, read)
        assertEquals('A', buffer[0].toInt().toChar())
        assertEquals('\\', buffer[1].toInt().toChar())
    }

    @Test
    fun testConcurrentPlaylistLoadThreadSafety() = runBlocking(Dispatchers.Default) {
        // Simulate synchronized list modification and copying concurrently
        val list = Collections.synchronizedList(mutableListOf<Int>())
        val exceptionCount = AtomicInteger(0)

        // Coroutines writing to the list and reading from it (calling toList())
        val writers = (1..10).map { id ->
            async {
                repeat(1000) {
                    list.add(it)
                }
            }
        }

        val readers = (1..10).map {
            async {
                repeat(1000) {
                    try {
                        // This must be synchronized to prevent ConcurrentModificationException
                        val currentCopy = synchronized(list) { list.toList() }
                        // Use the copy to prevent compiler optimizations dropping it
                        if (currentCopy.size < 0) {
                            fail()
                        }
                    } catch (e: ConcurrentModificationException) {
                        exceptionCount.incrementAndGet()
                    }
                }
            }
        }

        writers.awaitAll()
        readers.awaitAll()

        assertEquals("Should not encounter any ConcurrentModificationException", 0, exceptionCount.get())
    }

    @Test
    fun testGuideKeyCandidatesCaching() {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>(relaxed = true)
        val profileManager = io.mockk.mockk<com.arflix.tv.data.repository.ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<com.arflix.tv.data.repository.CloudSyncInvalidationBus>(relaxed = true)
        val repository = IptvRepository(context, okHttpClient, profileManager, invalidationBus)

        // Get private field guideKeyCandidatesCache using reflection
        val cacheField = IptvRepository::class.java.getDeclaredField("guideKeyCandidatesCache")
        cacheField.isAccessible = true
        val cache = cacheField.get(repository) as Map<*, *>

        val method = IptvRepository::class.java.getDeclaredMethod("guideKeyCandidates", String::class.java)
        method.isAccessible = true

        val initialSize = cache.size

        // First call
        val result1 = method.invoke(repository, "NPO 1 FHD [NL]") as Set<String>
        val sizeAfterFirst = cache.size
        assertEquals(initialSize + 1, sizeAfterFirst)

        // Second call with same value (should hit cache)
        val result2 = method.invoke(repository, "NPO 1 FHD [NL]") as Set<String>
        assertEquals(sizeAfterFirst, cache.size)
        assertEquals(result1, result2)
    }

    @Test
    fun fixedXmlTvDateParserHandlesCommonOffsets() {
        val repository = repositoryForPrivateMethodTests()
        val method = IptvRepository::class.java.getDeclaredMethod("parseFixedXmlTvDate", String::class.java)
        method.isAccessible = true

        val utc = method.invoke(repository, "20260903214540 +0000") as Long
        val positiveOffset = method.invoke(repository, "20260903234540 +02:00") as Long
        val negativeOffset = method.invoke(repository, "20260903164540 -0500") as Long
        val expected = OffsetDateTime.parse("2026-09-03T21:45:40Z").toInstant().toEpochMilli()

        assertEquals(expected, utc)
        assertEquals(expected, positiveOffset)
        assertEquals(expected, negativeOffset)
    }

    @Test
    fun largeGuideLookupUsesExactProviderKeysWithoutFuzzyCacheChurn() {
        val repository = repositoryForPrivateMethodTests()
        val channels = (1..10_001).map { index ->
            com.arflix.tv.data.model.IptvChannel(
                id = "provider:channel-$index",
                name = "Scale Channel $index FHD",
                streamUrl = "https://example.test/live/$index",
                group = "Group ${index / 200}",
                epgId = "scale.channel.$index",
                tvgName = "Scale Channel $index",
            )
        }
        val method = IptvRepository::class.java.getDeclaredMethod("buildChannelKeyLookup", List::class.java)
        method.isAccessible = true

        @Suppress("UNCHECKED_CAST")
        val lookup = method.invoke(repository, channels) as Map<String, List<com.arflix.tv.data.model.IptvChannel>>
        val cacheField = IptvRepository::class.java.getDeclaredField("guideKeyCandidatesCache")
        cacheField.isAccessible = true
        val fuzzyCache = cacheField.get(repository) as Map<*, *>

        assertEquals("provider:channel-42", lookup.getValue("scale.channel.42").single().id)
        assertTrue(fuzzyCache.isEmpty())
    }

    @Test
    fun m3uAttributeParserPreservesProviderMetadata() {
        val repository = repositoryForPrivateMethodTests()
        val method = IptvRepository::class.java.getDeclaredMethod("parseM3uAttributes", String::class.java)
        method.isAccessible = true

        @Suppress("UNCHECKED_CAST")
        val attributes = method.invoke(
            repository,
            "#EXTINF:-1 tvg-id=\"npo1.nl\" tvg-name=\\'NPO 1\\' group-title=\"Netherlands\" " +
                "catchup-days=\"2\" http-user-agent=\"ARVIO-Test\",NPO 1"
        ) as Map<String, String>

        assertEquals("npo1.nl", attributes["tvg-id"])
        assertEquals("NPO 1", attributes["tvg-name"])
        assertEquals("Netherlands", attributes["group-title"])
        assertEquals("2", attributes["catchup-days"])
        assertEquals("ARVIO-Test", attributes["http-user-agent"])
    }

    @Test
    fun optimizedStreamKeyRemainsCompatibleWithExistingChannelIds() {
        val repository = repositoryForPrivateMethodTests()
        val method = IptvRepository::class.java.getDeclaredMethod("stableStreamKey", String::class.java)
        method.isAccessible = true

        assertEquals(
            "46-43519e1a30008258",
            method.invoke(repository, "https://example.test/live/channel.ts?token=abc")
        )
    }

    private fun repositoryForPrivateMethodTests(): IptvRepository {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>(relaxed = true)
        val profileManager = io.mockk.mockk<com.arflix.tv.data.repository.ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<com.arflix.tv.data.repository.CloudSyncInvalidationBus>(relaxed = true)
        return IptvRepository(context, okHttpClient, profileManager, invalidationBus)
    }

    @Test
    fun testFetchAndParseEpgThrows304Exception() {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>()
        val profileManager = io.mockk.mockk<com.arflix.tv.data.repository.ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<com.arflix.tv.data.repository.CloudSyncInvalidationBus>(relaxed = true)

        val builder = io.mockk.mockk<okhttp3.OkHttpClient.Builder>(relaxed = true)
        io.mockk.every { okHttpClient.newBuilder() } returns builder
        io.mockk.every { builder.connectTimeout(any(), any()) } returns builder
        io.mockk.every { builder.readTimeout(any(), any()) } returns builder
        io.mockk.every { builder.writeTimeout(any(), any()) } returns builder
        io.mockk.every { builder.callTimeout(any(), any()) } returns builder

        val customClient = io.mockk.mockk<okhttp3.OkHttpClient>()
        io.mockk.every { builder.build() } returns customClient

        val call = io.mockk.mockk<okhttp3.Call>()
        val response = io.mockk.mockk<okhttp3.Response>()
        io.mockk.every { response.code } returns 304
        io.mockk.every { response.header("Retry-After") } returns null
        io.mockk.every { response.close() } returns Unit
        io.mockk.every { call.execute() } returns response
        io.mockk.every { customClient.newCall(any()) } returns call

        val repository = IptvRepository(context, okHttpClient, profileManager, invalidationBus)
        val method = IptvRepository::class.java.getDeclaredMethod("fetchAndParseEpg", String::class.java, List::class.java, Function0::class.java)
        method.isAccessible = true

        try {
            method.invoke(repository, "http://example.com/epg.xml", emptyList<com.arflix.tv.data.model.IptvChannel>(), {})
            fail("Expected EpgNotModifiedException to be thrown")
        } catch (e: java.lang.reflect.InvocationTargetException) {
            val cause = e.cause
            assertEquals(IptvRepository.EpgNotModifiedException::class.java, cause?.javaClass)
        }
    }
}

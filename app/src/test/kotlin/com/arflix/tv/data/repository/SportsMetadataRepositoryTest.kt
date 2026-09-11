package com.arflix.tv.data.repository

import org.robolectric.RuntimeEnvironment
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.File
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class SportsMetadataRepositoryTest {
    private val json = """{"version":1,"catalogueEnabled":true,"events":[{"id":"1","title":"North vs South","sport":"Soccer","startsAt":1800000000000}]}"""

    @Test fun diskCacheIsReadableWhileRefreshIsWaitingOnNetwork() = runBlocking {
        val context = RuntimeEnvironment.getApplication()
        val disk = File(context.cacheDir, "sports-fixtures.json")
        disk.writeText(json)
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            entered.countDown()
            check(release.await(5, TimeUnit.SECONDS))
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                .body(json.toResponseBody()).build()
        }.build()
        val repo = SportsMetadataRepository(client, context)
        val refresh = async(Dispatchers.Default) { repo.load() }
        try {
            assertTrue(entered.await(3, TimeUnit.SECONDS))
            val cached = withTimeout(1000) { repo.peek() }
            assertEquals("North vs South", cached.single().title)
        } finally {
            release.countDown()
            refresh.await()
            disk.delete()
        }
    }

    @Test fun unknownLengthOversizeResponseDoesNotEvictCachedSchedule() = runBlocking {
        val context = RuntimeEnvironment.getApplication()
        val disk = File(context.cacheDir, "sports-fixtures.json")
        disk.writeText(json)
        val body = object : okhttp3.ResponseBody() {
            override fun contentType(): okhttp3.MediaType? = null
            override fun contentLength() = -1L
            override fun source() = okio.Buffer().write(ByteArray(6_000_001))
        }
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK").body(body).build()
        }.build()
        try {
            val repo = SportsMetadataRepository(client, context)
            assertEquals(1, repo.peek().size)
            assertEquals("North vs South", repo.load().single().title)
        } finally { disk.delete() }
    }
}

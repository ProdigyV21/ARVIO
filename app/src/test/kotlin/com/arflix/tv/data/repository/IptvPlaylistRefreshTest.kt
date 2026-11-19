package com.arflix.tv.data.repository

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.lang.reflect.InvocationTargetException

class IptvPlaylistRefreshTest {
    @Test
    fun refreshNeverUsesValidatorsAndRejectsUnexpectedNotModified() {
        val client = mockk<OkHttpClient>()
        val call = mockk<Call>()
        var request: Request? = null
        every { client.newCall(any()) } answers {
            request = firstArg()
            call
        }
        every { call.execute() } answers {
            Response.Builder().request(requireNotNull(request))
                .protocol(Protocol.HTTP_1_1).code(304).message("Not Modified")
                .body("".toResponseBody()).build()
        }
        val repository = IptvRepository(
            mockk<Context>(relaxed = true), client,
            mockk<ProfileManager>(relaxed = true), mockk<CloudSyncInvalidationBus>(relaxed = true),
        )
        val method = IptvRepository::class.java.getDeclaredMethod(
            "fetchAndParseM3uOnce", String::class.java, Function1::class.java, OkHttpClient::class.java, Boolean::class.javaPrimitiveType,
        ).apply { isAccessible = true }
        try {
            method.invoke(repository, "https://example.test/playlist.m3u", { _: Any -> Unit }, client, true)
            fail("An unexpected 304 must not become an empty successful playlist")
        } catch (error: InvocationTargetException) {
            assertTrue(error.targetException is IOException)
        }
        assertNotNull(request)
        assertNull(request!!.header("If-None-Match"))
        assertNull(request!!.header("If-Modified-Since"))
    }
}

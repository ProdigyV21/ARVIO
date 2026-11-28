package com.arflix.tv.cast

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.arflix.tv.R
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.SessionManager
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class CastManagerTest {

    private lateinit var manager: CastManager

    private val tv = DlnaDevice(
        id = "uuid:tv-1",
        friendlyName = "Living Room TV",
        manufacturer = "Sony",
        modelName = "Bravia",
        avTransportControlUrl = "http://192.168.1.20:52323/upnp/control/AVTransport",
        renderingControlUrl = null,
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val app = ApplicationProvider.getApplicationContext<Application>()
        manager = CastManager(app)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun isCastableUrlRejectsLoopbackAndAcceptsRemoteHosts() {
        assertFalse(manager.isCastableUrl(null))
        assertFalse(manager.isCastableUrl(""))
        assertFalse(manager.isCastableUrl("http://localhost:8080/video.mkv"))
        assertFalse(manager.isCastableUrl("http://127.0.0.1:11470/movie.mp4"))
        assertFalse(manager.isCastableUrl("http://[::1]:8080/movie.mkv"))
        assertTrue(manager.isCastableUrl("https://cdn.debrid.example/stream.mkv"))
        assertTrue(manager.isCastableUrl("http://192.168.1.50:8080/video"))
    }

    @Test
    fun reportLocalSourcePublishesAndClearsError() {
        manager.reportLocalSource()
        assertEquals(R.string.cast_error_local_source, manager.castError.value)
        manager.clearCastError()
        assertNull(manager.castError.value)
    }

    @Test
    fun loadMediaWithLocalUrlEndsDlnaSessionAndResetsToNotConnected() {
        manager.connectDlna(tv)
        assertTrue(manager.isDlnaSession)
        assertEquals(CastManager.CastState.Casting(tv.friendlyName), manager.castState.value)

        manager.loadMedia(
            url = "http://127.0.0.1:11470/movie.mkv",
            title = "Test Movie",
            imageUrl = null,
            mimeType = "video/mp4",
            positionMs = 65_000L,
        )

        assertEquals(R.string.cast_error_local_source, manager.castError.value)
        assertFalse(manager.isDlnaSession)
        assertEquals(CastManager.CastState.NotConnected, manager.castState.value)
        assertEquals(65_000L, field("dlnaPositionMs").getLong(manager))
    }

    @Test
    fun loadMediaWithLocalUrlResetsCastStateWhenGoogleSessionIsMissing() {
        val castCtx = mockk<CastContext>(relaxed = true)
        val sessionMgr = mockk<SessionManager>(relaxed = true)
        every { castCtx.sessionManager } returns sessionMgr
        every { sessionMgr.currentCastSession } returns null
        field("castContext").set(manager, castCtx)
        stateFlow().value = CastManager.CastState.Casting("Living Room")

        manager.loadMedia(
            url = "http://localhost:8080/stream.mp4",
            title = "Test Movie",
            imageUrl = null,
            mimeType = "video/mp4",
            positionMs = 12_000L,
        )

        assertEquals(R.string.cast_error_local_source, manager.castError.value)
        assertEquals(CastManager.CastState.NotConnected, manager.castState.value)
    }

    @Test
    fun seekToBeforePlayingReplacesPendingResumeSeekUntilReady() {
        manager.connectDlna(tv)
        val mockCtrl = mockk<DlnaController>(relaxed = true)
        field("dlna").set(manager, mockCtrl)
        field("dlnaPendingSeekMs").setLong(manager, 60_000L)
        field("dlnaReadyForSeek").setBoolean(manager, false)

        manager.seekTo(120_000L)
        assertEquals(120_000L, field("dlnaPendingSeekMs").getLong(manager))
        assertEquals(120_000L, manager.getApproximatePosition())
        coVerify(exactly = 0) { mockCtrl.seek(any()) }

        manager.seekTo(0L)
        assertEquals(0L, field("dlnaPendingSeekMs").getLong(manager))

        field("dlnaReadyForSeek").setBoolean(manager, true)
        manager.seekTo(90_000L)
        assertEquals(-1L, field("dlnaPendingSeekMs").getLong(manager))
        coVerify(exactly = 1) { mockCtrl.seek(90_000L) }
    }

    @Test
    fun dlnaMimeTypeMapsKnownContainersAndKeepsFallbackWithoutExtension() {
        val method = CastManager::class.java.getDeclaredMethod(
            "dlnaMimeType",
            String::class.java,
            String::class.java,
        ).apply { isAccessible = true }

        assertEquals("video/x-matroska", method.invoke(manager, "https://cdn.example/movie.mkv?token=1", "video/mp4"))
        assertEquals("video/webm", method.invoke(manager, "https://cdn.example/movie.webm", "video/mp4"))
        assertEquals("video/x-msvideo", method.invoke(manager, "https://cdn.example/movie.avi", "video/mp4"))
        assertEquals("video/mp2t", method.invoke(manager, "https://cdn.example/stream.ts?e=1", "video/mp4"))
        assertEquals("application/vnd.apple.mpegurl", method.invoke(manager, "https://cdn.example/master.m3u8?e=2", "video/mp4"))
        assertEquals("video/mp4", method.invoke(manager, "https://debrid.example/d/ABC123", "video/mp4"))
    }

    private fun field(name: String) = CastManager::class.java.getDeclaredField(name).apply { isAccessible = true }

    @Suppress("UNCHECKED_CAST")
    private fun stateFlow() = field("_castState").get(manager) as kotlinx.coroutines.flow.MutableStateFlow<CastManager.CastState>
}

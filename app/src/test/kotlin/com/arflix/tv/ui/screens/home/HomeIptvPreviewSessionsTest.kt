package com.arflix.tv.ui.screens.home

import androidx.media3.common.Player
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.ResolvedIptvChannelPolicy
import com.arflix.tv.data.repository.iptvStreamPolicy
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSessionTable
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeIptvPreviewSessionsTest {
    private val alice = IptvProviderAccount.xtream("http://panel.test", "alice", "pass")!!
    private val player = mockk<Player>(relaxed = true)
    private val autoUnknown = ResolvedIptvChannelPolicy(iptvStreamPolicy(alice, IptvConnectionLimit.AUTO, null))
    private val autoReliable = ResolvedIptvChannelPolicy(iptvStreamPolicy(alice, IptvConnectionLimit.AUTO, 1))
    private val noPanel: suspend (IptvProviderAccount) -> IptvAccountUsage? = { error("must not ask the panel") }

    private fun TestScope.table() = IptvStreamSessionTable({ testScheduler.currentTime }, StandardTestDispatcher(testScheduler))

    @Test fun aFullAccountOpensNoPreviewAndIsAskedOnceThenNotAgainForAMinute() = runTest {
        val table = table()
        var now = 0L
        var checks = 0
        val preview = HomeIptvPreviewSessions(table, { now }) { checks++; IptvAccountUsage(2, 2) }
        assertNull(preview.admit(player) { autoReliable })
        assertEquals("asked once, never polled", 1, checks)
        assertEquals(0L, currentTime)
        now = DEFAULT_IPTV_PREVIEW_REFUSAL_MS - 1
        repeat(5) { assertNull(preview.admit(player) { autoReliable }) }
        assertEquals("no request for further cards on a refused account", 1, checks)
        now = DEFAULT_IPTV_PREVIEW_REFUSAL_MS
        assertNull(preview.admit(player) { autoReliable })
        assertEquals(2, checks)
        assertEquals(0, table.openCount())
    }

    @Test fun leavingALimitedPreviewStopsItsStreamAndClosesItsSession() = runTest {
        val table = table()
        val preview = HomeIptvPreviewSessions(table) { IptvAccountUsage(0, 1) }
        val session = preview.admit(player) { autoReliable }!!.session!!
        assertEquals(1, table.openCount(alice))
        assertTrue("a session always stops on leave", preview.leave(player, stopOnLeave = false))
        verifyOrder {
            player.playWhenReady = false
            player.stop()
            player.clearMediaItems()
        }
        assertTrue(session.isClosed)
        assertEquals(0, table.openCount(alice))
    }

    @Test fun noLimitKeepsTodaysUntaggedPreview() = runTest {
        val table = table()
        val preview = HomeIptvPreviewSessions(table, checkAccount = noPanel)
        assertNull(preview.admit(player) { ResolvedIptvChannelPolicy(null) }!!.session)
        assertFalse("pauses as today", preview.leave(player, stopOnLeave = false))
        verify(exactly = 0) { player.stop() }
        assertEquals(0, table.openCount())
    }

    @Test fun unknownOrUnresponsiveAccountsDoNotPreview() = runTest {
        val table = table()
        var checks = 0
        val preview = HomeIptvPreviewSessions(table) {
            checks++
            delay(10_000)
            IptvAccountUsage(0, 1)
        }
        assertNull(preview.admit(player) { null })
        assertNull(preview.admit(player) { autoUnknown })
        assertEquals(0, checks)
        assertNull(preview.admit(player) { autoReliable })
        assertEquals(1, checks)
        assertEquals(0, table.openCount())
    }

    @Test fun aRecentlyClosedSessionDoesNotBypassAFullAccountCheck() = runTest {
        val table = table()
        table.open(alice, 1).close().join()
        assertFalse(table.isColdStart(alice))
        var checks = 0
        val preview = HomeIptvPreviewSessions(table) { checks++; IptvAccountUsage(1, 1) }
        assertNull(preview.admit(player) { autoReliable })
        assertEquals(1, checks)
        assertEquals(0, table.openCount())
    }

    @Test fun playbackTakingTheLastSlotDuringTheCheckWinsOverThePreview() = runTest {
        val table = table()
        val playback = table.open(alice, 1)
        val preview = HomeIptvPreviewSessions(table, checkAccount = { error("local slot is occupied") })
        assertNull(preview.admit(player) { autoReliable })
        assertFalse(playback.isClosed)
        playback.close().join()
        val racedPlayback = HomeIptvPreviewSessions(table) {
            table.open(alice, 1)
            IptvAccountUsage(0, 1)
        }
        assertNull(racedPlayback.admit(player) { autoReliable })
        assertEquals(1, table.openCount(alice))
    }
}

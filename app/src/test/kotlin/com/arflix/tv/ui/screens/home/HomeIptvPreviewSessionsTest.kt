package com.arflix.tv.ui.screens.home

import androidx.media3.common.Player
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.iptvStreamPolicy
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSessionTable
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    private val autoUnknown = iptvStreamPolicy(alice, IptvConnectionLimit.AUTO, null)!!
    // A tight allowance: Auto still asks the panel rather than refusing the preview outright.
    private val autoReliable = iptvStreamPolicy(alice, IptvConnectionLimit.AUTO, 2)!!
    private val noPanel: suspend (IptvProviderAccount) -> IptvAccountUsage? = { error("must not ask the panel") }

    private fun TestScope.table() = IptvStreamSessionTable({ testScheduler.currentTime }, StandardTestDispatcher(testScheduler))

    @Test fun aFullAccountOpensNoPreviewAndIsAskedOnceThenNotAgainForAMinute() = runTest {
        val table = table()
        var now = 0L
        var checks = 0
        val preview = HomeIptvPreviewSessions(table, { now }) { checks++; IptvAccountUsage(2, 2) }
        assertFalse(preview.admit(player) { autoReliable })
        assertEquals("asked once, never polled", 1, checks)
        assertEquals(0L, currentTime)
        assertNull(preview.open())
        assertNull(preview.sessionForPrepare(isHeroIptv = true))
        now = DEFAULT_IPTV_PREVIEW_REFUSAL_MS - 1
        repeat(5) { assertFalse(preview.admit(player) { autoReliable }) }
        assertEquals("no request for further cards on a refused account", 1, checks)
        now = DEFAULT_IPTV_PREVIEW_REFUSAL_MS
        assertFalse(preview.admit(player) { autoReliable })
        assertEquals(2, checks)
        assertNull(preview.open())
        assertEquals(0, table.openCount())
    }

    @Test fun leavingALimitedPreviewStopsItsStreamAndClosesItsSession() = runTest {
        val table = table()
        val preview = HomeIptvPreviewSessions(table, checkAccount = noPanel)
        assertTrue(preview.admit(player) { autoUnknown })
        val session = preview.open()!!
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
        assertTrue(preview.admit(player) { null })
        assertNull(preview.open())
        assertFalse("pauses as today", preview.leave(player, stopOnLeave = false))
        verify(exactly = 0) { player.stop() }
        assertEquals(0, table.openCount())
    }
}

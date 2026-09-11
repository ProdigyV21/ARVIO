package com.arflix.tv.ui.screens.player

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class PlayerExitTransitionTest {
    @Test fun tvPausesBeforeLeavingAndIgnoresRepeatedRequests() = runTest {
        val events = mutableListOf<String>()
        val exit = PlayerExitTransition(this, false, { events += "pause" }, {
            events += "restore orientation"
            events += "back"
        })
        exit.requestExit()
        exit.requestExit()
        assertEquals(listOf("pause", "restore orientation", "back"), events)
        assertTrue(exit.isExiting)
        assertEquals(1f, exit.alpha, 0f)
    }

    @Test fun pauseFailureDoesNotTrapTheUserInPlayer() = runTest {
        var left = false
        val exit = PlayerExitTransition(this, false, { error("Player already released") }, { left = true })
        exit.requestExit()
        assertTrue(left)
    }

    @Test fun pauseCallbackCannotReenterExit() = runTest {
        var exits = 0
        lateinit var exit: PlayerExitTransition
        exit = PlayerExitTransition(this, false, { exit.requestExit() }, { exits++ })
        exit.requestExit()
        assertEquals(1, exits)
    }
}

package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.assertEquals
import org.junit.Test

class ProgramFocusTargetTest {
    @Test fun sharedBoundarySelectsStartingProgrammeNotEndingProgramme() {
        val targets = listOf(ProgramFocusTarget(0, 30), ProgramFocusTarget(30, 60))
        assertEquals(targets[1], targets.minByOrNull { it.distanceTo(30) })
    }

    @Test fun insideProgrammeKeepsTheMatchingTime() {
        val target = ProgramFocusTarget(30, 60)
        assertEquals(0, target.distanceTo(30))
        assertEquals(0, target.distanceTo(59))
        assertEquals(1, target.distanceTo(60))
        assertEquals(10, target.distanceTo(20))
    }

    @Test fun verticalNavigationAcrossEqualSchedulesDoesNotDriftBackwards() {
        val targets = (0 until 48).map { ProgramFocusTarget(it * 30, (it + 1) * 30) }
        var anchor = 600
        repeat(40) { anchor = targets.minByOrNull { it.distanceTo(anchor) }!!.startMin }
        assertEquals(600, anchor)
    }
}

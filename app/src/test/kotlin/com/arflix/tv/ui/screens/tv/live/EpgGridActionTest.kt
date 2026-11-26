package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvProgram
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EpgGridActionTest {

    @Test
    fun currentProgramKeepsMetadataForActionDialog() {
        val program = IptvProgram(
            title = "Live Movie",
            startUtcMillis = 1_000L,
            endUtcMillis = 2_000L,
        )

        assertThat(
            epgProgramActionTarget(
                program = program,
                isPast = false,
                isLive = true,
                isCatchupSupported = false,
            )
        ).isEqualTo(program)
    }

    @Test
    fun futureProgramOpensItsDetails() {
        val program = IptvProgram(
            title = "Tomorrow's Movie",
            startUtcMillis = 3_000L,
            endUtcMillis = 4_000L,
        )

        assertThat(
            epgProgramActionTarget(
                program = program,
                isPast = false,
                isLive = false,
                isCatchupSupported = false,
            )
        ).isEqualTo(program)
    }

    @Test
    fun guidePlaceholderDoesNothing() {
        val program = IptvProgram(title = "Programming", startUtcMillis = 3_000L, endUtcMillis = 4_000L)
        assertThat(
            epgProgramActionTarget(
                program = program,
                isPast = false,
                isLive = false,
                isCatchupSupported = false,
                isPlaceholder = true,
            )
        ).isNull()
    }
}

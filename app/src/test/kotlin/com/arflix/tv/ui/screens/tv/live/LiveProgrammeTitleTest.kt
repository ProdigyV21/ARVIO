package com.arflix.tv.ui.screens.tv.live

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LiveProgrammeTitleTest {
    private val eventName = "13:45 Netherlands vs Germany | UEFA Nations League [ESPN]"

    @Test fun realProgrammeTakesPriorityOverTheChannelName() {
        assertThat(liveProgrammeTitle("Pre-match coverage", eventName, "No programme data"))
            .isEqualTo("Pre-match coverage")
    }

    @Test fun missingOrBlankProgrammeUsesTheCompleteProviderName() {
        listOf(null, "", " \n\t ").forEach { title ->
            assertThat(liveProgrammeTitle(title, eventName, "No programme data"))
                .isEqualTo(eventName)
        }
    }

    @Test fun placeholderIsOnlyUsedWhenNeitherTitleIsAvailable() {
        assertThat(liveProgrammeTitle(null, " ", "No programme data"))
            .isEqualTo("No programme data")
    }
}

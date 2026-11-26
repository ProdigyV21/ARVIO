package com.arflix.tv.ui.screens.tv.live

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LiveSportsMemoryTest {

    @Test
    fun reopensSportsWhenLeftThere() {
        assertThat(LiveSportsMemory.shouldReopen(true, null, null)).isTrue()
    }

    @Test
    fun staysOnChannelsWhenSportsWasClosed() {
        assertThat(LiveSportsMemory.shouldReopen(false, null, null)).isFalse()
    }

    @Test
    fun explicitChannelOrStreamWinsOverSports() {
        assertThat(LiveSportsMemory.shouldReopen(true, "ch1", null)).isFalse()
        assertThat(LiveSportsMemory.shouldReopen(true, null, "http://example.test/live")).isFalse()
    }

    @Test
    fun touchStartupOpensGuideWhenSportsIsRestored() {
        assertThat(LiveSportsMemory.startupMode(true, null, null, reopenSports = true))
            .isEqualTo(LiveTvStartup.LiveTvMode.Guide)
        assertThat(LiveSportsMemory.startupMode(true, null, null, reopenSports = false))
            .isEqualTo(LiveTvStartup.LiveTvMode.GroupHome)
        assertThat(LiveSportsMemory.startupMode(false, null, null, reopenSports = false))
            .isEqualTo(LiveTvStartup.LiveTvMode.Guide)
    }
}

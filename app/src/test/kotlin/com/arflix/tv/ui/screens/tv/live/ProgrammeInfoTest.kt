package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.SportsReminder
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ProgrammeInfoTest {

    private val show = IptvProgram(title = "Evening News", startUtcMillis = 10_000L, endUtcMillis = 20_000L)

    @Test
    fun reminderSwitchesToTheChannelAtTheShowsStart() {
        val reminder = programmeReminder(listOf("ch1", "ch1-hd", "ch1"), show)!!
        assertThat(reminder.eventId).isEqualTo("tv:ch1:10000")
        assertThat(reminder.startsAt).isEqualTo(10_000L)
        assertThat(reminder.title).isEqualTo("Evening News")
        assertThat(reminder.channelIds).containsExactly("ch1", "ch1-hd").inOrder()
    }

    @Test
    fun noChannelMeansNoReminder() {
        assertThat(programmeReminder(emptyList(), show)).isNull()
    }

    @Test
    fun togglingKeepsGameRemindersAndMarksTheShow() {
        val game = SportsReminder(eventId = "sportsdb:1", title = "Game", startsAt = 5_000L)
        val reminder = programmeReminder(listOf("ch1"), show)!!
        val on = listOf(game).withProgrammeReminder(reminder, on = true)
        assertThat(on).containsExactly(game, reminder)
        assertThat(on.remindedProgrammeIds()).containsExactly("tv:ch1:10000")
        val off = on.withProgrammeReminder(reminder, on = false)
        assertThat(off).containsExactly(game)
        assertThat(off.remindedProgrammeIds()).isEmpty()
    }
}

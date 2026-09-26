package com.arflix.tv.ui.components

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AppUpdateNotesScrollTest {

    @Test
    fun `down scrolls while notes have more text below`() {
        assertThat(updateNotesScrollDelta(scrollDown = true, value = 0, maxValue = 400, stepPx = 80f)).isEqualTo(80f)
        assertThat(updateNotesScrollDelta(scrollDown = true, value = 399, maxValue = 400, stepPx = 80f)).isEqualTo(80f)
    }

    @Test
    fun `up scrolls back while notes are not at the top`() {
        assertThat(updateNotesScrollDelta(scrollDown = false, value = 400, maxValue = 400, stepPx = 80f)).isEqualTo(-80f)
        assertThat(updateNotesScrollDelta(scrollDown = false, value = 1, maxValue = 400, stepPx = 80f)).isEqualTo(-80f)
    }

    @Test
    fun `keys fall through at the edges`() {
        assertThat(updateNotesScrollDelta(scrollDown = false, value = 0, maxValue = 400, stepPx = 80f)).isNull()
        assertThat(updateNotesScrollDelta(scrollDown = true, value = 400, maxValue = 400, stepPx = 80f)).isNull()
    }

    @Test
    fun `short notes that fit never consume up or down`() {
        assertThat(updateNotesScrollDelta(scrollDown = true, value = 0, maxValue = 0, stepPx = 80f)).isNull()
        assertThat(updateNotesScrollDelta(scrollDown = false, value = 0, maxValue = 0, stepPx = 80f)).isNull()
    }
}

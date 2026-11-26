package com.arflix.tv.ui.screens.tv.live

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LivePlayerPrefsTest {

    @Test
    fun sleepTimerStepsRoundToOff() {
        assertThat(nextSleepStep(0)).isEqualTo(30)
        assertThat(nextSleepStep(30)).isEqualTo(60)
        assertThat(nextSleepStep(120)).isEqualTo(0)
        assertThat(nextSleepStep(45)).isEqualTo(30)
    }

    @Test
    fun pictureModeCyclesFitFillStretch() {
        assertThat(LivePictureMode.Fit.next()).isEqualTo(LivePictureMode.Fill)
        assertThat(LivePictureMode.Fill.next()).isEqualTo(LivePictureMode.Stretch)
        assertThat(LivePictureMode.Stretch.next()).isEqualTo(LivePictureMode.Fit)
    }
}

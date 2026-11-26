package com.arflix.tv.ui.screens.home

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MobileRankInsetTest {
    @Test fun widerNumeralsGetMoreRoom() {
        assertThat(mobileRankInset(1).value).isLessThan(mobileRankInset(2).value)
        assertThat(mobileRankInset(2).value).isEqualTo(mobileRankInset(9).value)
        assertThat(mobileRankInset(9).value).isLessThan(mobileRankInset(10).value)
    }
}

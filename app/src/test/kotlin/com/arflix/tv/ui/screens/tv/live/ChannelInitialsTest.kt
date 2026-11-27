package com.arflix.tv.ui.screens.tv.live

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ChannelInitialsTest {
    @Test fun bracketedTagsAreIgnored() {
        assertThat(initialsFor("BVN (Europe)")).isEqualTo("BV")
        assertThat(initialsFor("NPO 1 [HD]")).isEqualTo("N1")
    }

    @Test fun punctuationNeverAppears() {
        assertThat(initialsFor("RTL-4")).isEqualTo("R4")
        assertThat(initialsFor("  Discovery  ")).isEqualTo("DI")
    }

    @Test fun emptyNamesFallBack() {
        assertThat(initialsFor("")).isEqualTo("??")
        assertThat(initialsFor("(  )")).isEqualTo("??")
    }
}

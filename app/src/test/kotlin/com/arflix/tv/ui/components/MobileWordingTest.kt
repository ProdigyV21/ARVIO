package com.arflix.tv.ui.components

import com.arflix.tv.ui.screens.details.touchTapWording
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MobileWordingTest {
    @Test
    fun oneSourceReadsSingular() {
        assertThat(singularSourcesLabel("sources available", 1)).isEqualTo("source available")
        assertThat(singularSourcesLabel("bronnen beschikbaar", 1)).isEqualTo("bron beschikbaar")
        assertThat(singularSourcesLabel("sources available", 3)).isEqualTo("sources available")
        assertThat(singularSourcesLabel("Quellen verfügbar", 1)).isEqualTo("Quellen verfügbar")
    }

    @Test
    fun phonesTapInsteadOfClick() {
        assertThat("Click to search YouTube".touchTapWording(true)).isEqualTo("Tap to search YouTube")
        assertThat("Click to search YouTube".touchTapWording(false)).isEqualTo("Click to search YouTube")
        assertThat("Pulsa para buscar en YouTube".touchTapWording(true)).isEqualTo("Pulsa para buscar en YouTube")
    }
}

class LiveWordingTest {
    @org.junit.Test
    fun groupsAndCountsReadNaturally() {
        com.google.common.truth.Truth.assertThat(com.arflix.tv.ui.screens.tv.live.tidyPlaylistGroupName("Entertainment;Music")).isEqualTo("Entertainment · Music")
        com.google.common.truth.Truth.assertThat(com.arflix.tv.ui.screens.tv.live.tidyPlaylistGroupName("News")).isEqualTo("News")
        com.google.common.truth.Truth.assertThat(com.arflix.tv.ui.screens.tv.live.singularChannelLabel("1 channels", 1)).isEqualTo("1 channel")
        com.google.common.truth.Truth.assertThat(com.arflix.tv.ui.screens.tv.live.singularChannelLabel("4 channels", 4)).isEqualTo("4 channels")
    }
}

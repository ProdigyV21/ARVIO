package com.arflix.tv.navigation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PhoneNavigationMotionTest {
    @Test
    fun tabsCrossfadeWhilePagesZoom() {
        listOf("home", "search", "watchlist", "tv?channelId={channelId}&streamUrl={streamUrl}", "music", "settings?autoCloudAuth={autoCloudAuth}")
            .forEach { assertThat(isTopLevelRoute(it)).isTrue() }
        assertThat(isTopLevelRoute("player/{mediaType}/{mediaId}?seasonNumber={seasonNumber}")).isTrue()
        assertThat(isTopLevelRoute("details/{mediaType}/{mediaId}?initialSeason={initialSeason}")).isFalse()
        assertThat(isTopLevelRoute("home_category/{categoryId}")).isFalse()
    }
}

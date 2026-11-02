package com.arflix.tv.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.components.LandscapeDeckLayout
import com.arflix.tv.ui.components.StackedMovieDeck

/** Compact, header-free hero. Navigation and profile switching live in the landscape rail. */
@Composable
fun LandscapeHomeHero(
    items: List<MediaItem>,
    layout: LandscapeDeckLayout,
    cardLogoUrls: Map<String, String> = emptyMap(),
    cardImdbRatings: Map<String, String> = emptyMap(),
    onDetails: (MediaItem) -> Unit = {},
    activeIndex: Int = 0,
    onActiveIndexChanged: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) return
    Box(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        StackedMovieDeck(
            items = items,
            layout = layout,
            currentIndex = activeIndex,
            onCurrentIndexChanged = onActiveIndexChanged,
            onDetails = onDetails,
            cardLogoUrls = cardLogoUrls,
            cardImdbRatings = cardImdbRatings,
        )
    }
}

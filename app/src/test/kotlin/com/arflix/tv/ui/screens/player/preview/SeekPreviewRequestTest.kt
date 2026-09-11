package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class SeekPreviewRequestTest {
    @Test fun iptvVodNeverOpensASecondVideoConnectionForPreviewExtraction() {
        assertFalse(allowSecondarySeekPreviewExtraction(512, "iptv_xtream_vod"))
        assertFalse(allowSecondarySeekPreviewExtraction(512, "iptv_stalker_vod"))
    }

    @Test fun otherSourcesRetainExtractionWithinTheDeviceMemoryBudget() {
        assertTrue(allowSecondarySeekPreviewExtraction(256, "debrid-addon"))
        assertTrue(allowSecondarySeekPreviewExtraction(256, null))
        assertFalse(allowSecondarySeekPreviewExtraction(128, "debrid-addon"))
    }
}

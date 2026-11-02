package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class SeekPreviewRequestTest {
    @Test fun fullResolutionDecoderBuffersMustFitEvenWhenThumbnailOutputIsSmall() {
        assertTrue(seekPreviewDecodeFitsMemory(192, false, 1920, 1080, "video/avc"))
        assertFalse(seekPreviewDecodeFitsMemory(192, false, 3840, 2160, "video/hevc"))
        assertFalse(seekPreviewDecodeFitsMemory(256, false, 3840, 2160, "video/hevc"))
        assertTrue(seekPreviewDecodeFitsMemory(512, false, 3840, 2160, "video/hevc"))
        assertFalse(seekPreviewDecodeFitsMemory(512, true, 1920, 1080, "video/avc"))
        assertFalse(seekPreviewDecodeFitsMemory(512, false, -1, -1, null))
        assertFalse(seekPreviewDecodeFitsMemory(512, false, 3840, 2160, "video/dolby-vision"))
    }

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

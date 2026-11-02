package com.arflix.tv.ui.screens.player.preview

import org.junit.Assert.*
import org.junit.Test

class RenderedSeekPreviewCaptureTest {
    @Test fun slowTvCopyRemainsValidDuringContinuousPlayback() {
        assertTrue(renderedPreviewCopyIsCurrent(139_556, 140_180, 140_145))
        assertTrue(renderedPreviewCopyIsCurrent(10_000, 10_033, 10_010))
    }

    @Test fun seeksFrozenFramesAndExpiredCopiesAreRejected() {
        assertFalse(renderedPreviewCopyIsCurrent(10_000, 20_000, 20_000))
        assertFalse(renderedPreviewCopyIsCurrent(20_000, 10_000, 10_000))
        assertFalse(renderedPreviewCopyIsCurrent(10_000, 10_000, 12_000))
        assertFalse(renderedPreviewCopyIsCurrent(10_000, 11_501, 11_501))
        assertFalse(renderedPreviewCopyIsCurrent(-1, 0, 0))
    }
}

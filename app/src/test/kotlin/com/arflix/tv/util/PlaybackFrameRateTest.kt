package com.arflix.tv.util

import org.junit.Assert.*
import org.junit.Test

class PlaybackFrameRateTest {
    @Test fun firstPlaybackUsesDeclaredRateImmediately() {
        val detector = PlaybackFrameRate()
        detector.onFrame(0, 24000f / 1001f)
        assertEquals(24000f / 1001f, detector.rate.value, 0.001f)
    }

    @Test fun missingContainerRateUsesDecodedTimestamps() {
        val detector = PlaybackFrameRate()
        repeat(49) { detector.onFrame(it * 40_000L, -1f) }
        assertEquals(25f, detector.rate.value, 0.001f)
    }

    @Test fun detectsFractionalFilmRate() {
        val detector = PlaybackFrameRate()
        repeat(49) { detector.onFrame(it * 41_708L, Float.NaN) }
        assertEquals(24000f / 1001f, detector.rate.value, 0.001f)
    }

    @Test fun resetDoesNotReusePreviousSourceRate() {
        val detector = PlaybackFrameRate()
        detector.onFrame(0, 25f)
        detector.reset()
        assertEquals(0f, detector.rate.value, 0f)
        detector.onFrame(0, 50f)
        assertEquals(50f, detector.rate.value, 0f)
    }

    @Test fun seekDiscontinuityDoesNotInventFrameRate() {
        val detector = PlaybackFrameRate()
        repeat(30) { detector.onFrame(it * 40_000L, -1f) }
        repeat(30) { detector.onFrame(300_000_000L + it * 40_000L, -1f) }
        assertEquals(0f, detector.rate.value, 0f)
    }

    @Test fun variableFrameTimingIsNotTreatedAsFixedRate() {
        val detector = PlaybackFrameRate()
        var time = 0L
        repeat(80) {
            time += if (it % 2 == 0) 40_000L else 60_000L
            detector.onFrame(time, -1f)
        }
        assertEquals(0f, detector.rate.value, 0f)
    }

    @Test fun choosesFilmAndPalModesInsteadOfSixty() {
        val rates = listOf(60f, 50f, 24f, 23.976f)
        assertEquals(2, matchingRefreshRateIndex(rates, 24f))
        assertEquals(3, matchingRefreshRateIndex(rates, 24000f / 1001f))
        assertEquals(1, matchingRefreshRateIndex(rates, 25f))
    }

    @Test fun supportsHigherIntegerMultiplesWithoutPulldown() {
        assertEquals(1, matchingRefreshRateIndex(listOf(60f, 120f), 24f))
        assertEquals(1, matchingRefreshRateIndex(listOf(60f, 100f), 25f))
        assertNull(matchingRefreshRateIndex(listOf(60f), 24f))
    }

    @Test fun rejectsInvalidRates() {
        listOf(0f, -1f, Float.NaN, Float.POSITIVE_INFINITY).forEach {
            assertNull(matchingRefreshRateIndex(listOf(60f), it))
        }
    }

    @Test fun keepsAlreadyCompatibleDisplayMode() {
        assertEquals(2, matchingRefreshRateIndex(listOf(24f, 60f, 120f), 24f, 2))
        assertEquals(0, matchingRefreshRateIndex(listOf(24f, 60f, 120f), 24f, 1))
    }

    @Test fun timestampRoundingDoesNotKeepChangingPublishedRate() {
        val detector = PlaybackFrameRate()
        var time = 0L
        detector.onFrame(time, -1f)
        listOf(41_708L, 41_709L, 41_707L).forEach { interval ->
            repeat(48) { time += interval; detector.onFrame(time, -1f) }
            assertEquals(24000f / 1001f, detector.rate.value, 0f)
        }
    }

    @Test fun transientFormatChangesDoNotSwitchHdmi() {
        val detector = PlaybackFrameRate()
        detector.onFrame(0, 24f)
        repeat(200) { detector.onFrame((it + 1) * 41_667L, if (it % 2 == 0) 25f else 24f) }
        assertEquals(24f, detector.rate.value, 0f)
        repeat(48) { detector.onFrame(200 * 41_667L + (it + 1) * 40_000L, 25f) }
        assertEquals(24f, detector.rate.value, 0f)
        repeat(96) { detector.onFrame(200 * 41_667L + (it + 49) * 40_000L, 25f) }
        assertEquals(25f, detector.rate.value, 0f)
    }

    @Test fun millisecondContainerTimestampsMatchIntegerAndFractionalHdmiModes() {
        val rates = listOf(24000f / 1001f, 24f, 25f, 30000f / 1001f, 30f, 50f, 60000f / 1001f, 60f)
        rates.forEachIndexed { index, fps ->
            repeat(48) { phase ->
                val detector = PlaybackFrameRate()
                repeat(49) { frame ->
                    val timestamp = kotlin.math.round((frame + phase) * 1_000.0 / fps).toLong() * 1_000
                    detector.onFrame(timestamp, -1f)
                }
                assertEquals("$fps fps, PTS phase $phase", fps, detector.rate.value, 0.001f)
                assertEquals(index, matchingRefreshRateIndex(rates, detector.rate.value))
            }
        }
    }

    @Test fun decodedCadenceCorrectsWrongDeclaredRateAndDoesNotRevert() {
        val detector = PlaybackFrameRate()
        detector.onInputFormat(30f)
        repeat(49) { detector.onFrame(it * 41_708L, 30f) }
        assertEquals(30f, detector.rate.value, 0f)
        repeat(48) { detector.onFrame((it + 49) * 41_708L, 30f) }
        assertEquals(24000f / 1001f, detector.rate.value, 0f)
        detector.onInputFormat(30f)
        repeat(200) { detector.onFrame((it + 97) * 41_708L, 30f) }
        assertEquals(24000f / 1001f, detector.rate.value, 0f)
    }

    @Test fun inaccurateNonStandardMetadataStillGetsCorrectDisplayMode() {
        val detector = PlaybackFrameRate()
        repeat(97) { detector.onFrame(it * 40_000L, 25.12f) }
        assertEquals(25f, detector.rate.value, 0f)
        assertEquals(1, matchingRefreshRateIndex(listOf(60f, 50f, 24f), detector.rate.value))
    }

    @Test fun seekDoesNotCarryPendingCadenceCorrectionAcrossDiscontinuity() {
        val detector = PlaybackFrameRate()
        detector.onInputFormat(30f)
        repeat(49) { detector.onFrame(it * 40_000L, 30f) }
        repeat(49) { detector.onFrame(300_000_000L + it * 40_000L, 30f) }
        assertEquals(30f, detector.rate.value, 0f)
        repeat(48) { detector.onFrame(300_000_000L + (it + 49) * 40_000L, 30f) }
        assertEquals(25f, detector.rate.value, 0f)
    }

    @Test fun resetClearsPendingFormatChange() {
        val detector = PlaybackFrameRate()
        detector.onFrame(0, 24f)
        repeat(47) { detector.onFrame(it * 40_000L, 25f) }
        detector.reset()
        detector.onFrame(0, 24f)
        detector.onFrame(40_000L, 25f)
        assertEquals(24f, detector.rate.value, 0f)
    }

    @Test fun canonicalizationPreservesFractionalAndIntegerDistinction() {
        assertEquals(24000f / 1001f, stablePlaybackRate(23.9766f), 0f)
        assertEquals(24f, stablePlaybackRate(24.001f), 0f)
        assertEquals(60000f / 1001f, stablePlaybackRate(59.941f), 0f)
        assertEquals(60f, stablePlaybackRate(59.999f), 0f)
    }

    @Test fun decoderFormatStartsAfrWithoutFrameCallbacks() {
        val detector = PlaybackFrameRate()
        detector.onInputFormat(24000f / 1001f)
        assertEquals(24000f / 1001f, detector.rate.value, 0f)
    }

    @Test fun invalidInputFormatDoesNotPoisonTimestampDetection() {
        val detector = PlaybackFrameRate()
        listOf(-1f, 0f, Float.NaN, Float.POSITIVE_INFINITY).forEach(detector::onInputFormat)
        assertEquals(0f, detector.rate.value, 0f)
        repeat(49) { detector.onFrame(it * 40_000L, -1f) }
        detector.onInputFormat(30f)
        assertEquals(25f, detector.rate.value, 0f)
    }

    @Test fun sourceResetAllowsNewDecoderFormatWithoutFrames() {
        val detector = PlaybackFrameRate()
        detector.onInputFormat(24f)
        detector.reset()
        detector.onInputFormat(25f)
        assertEquals(25f, detector.rate.value, 0f)
    }

    @Test fun fractionalFallbackUsesIntegerCounterpartOnlyWhenNecessary() {
        assertEquals(1, matchingRefreshRateIndex(listOf(60f, 24f), 24000f / 1001f, 0, true))
        assertEquals(0, matchingRefreshRateIndex(listOf(60f, 50f), 30000f / 1001f, 1, true))
        assertEquals(2, matchingRefreshRateIndex(listOf(60f, 24f, 23.976f), 24000f / 1001f, 1, true))
    }

    @Test fun fallbackNeverTreatsPulldownOrPalMismatchAsCompatible() {
        assertNull(matchingRefreshRateIndex(listOf(60f, 50f), 24000f / 1001f, 0, true))
        assertNull(matchingRefreshRateIndex(listOf(24f, 60f), 25f, 0, true))
        assertNull(matchingRefreshRateIndex(listOf(25f), 50f, 0, true))
    }
}

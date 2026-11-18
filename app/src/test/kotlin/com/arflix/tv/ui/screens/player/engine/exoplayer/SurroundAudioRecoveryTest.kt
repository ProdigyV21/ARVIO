package com.arflix.tv.ui.screens.player.engine.exoplayer

import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.MimeTypes
import androidx.media3.common.TrackGroup
import androidx.media3.common.Tracks
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class SurroundAudioRecoveryTest {
    private val failed = format(MimeTypes.AUDIO_TRUEHD, 8)
    private val dolbyDigital = format(MimeTypes.AUDIO_AC3, 6)

    @Test fun `preserves the same language and role on a compatible route`() {
        val valid = group("same-language", dolbyDigital)
        val tracks = Tracks(listOf(
            group("other-language", dolbyDigital.buildUpon().setLanguage("fr").build()),
            group("commentary", dolbyDigital.buildUpon().setRoleFlags(C.ROLE_FLAG_COMMENTARY).build()),
            group("stereo", format(MimeTypes.AUDIO_AC3, 2)),
            valid
        ))
        val picked = compatibleSurroundTrack(failed, tracks) { it == 48_000 }
        assertEquals(valid.mediaTrackGroup, picked?.mediaTrackGroup)
        assertEquals(listOf(0), picked?.trackIndices)
    }

    @Test fun `never uses a route without direct ac3 or another failing ac3 track`() {
        val tracks = Tracks(listOf(group("surround", dolbyDigital)))
        assertNull(compatibleSurroundTrack(failed, tracks) { false })
        assertNull(compatibleSurroundTrack(dolbyDigital, tracks) { true })
        assertNull(compatibleSurroundTrack(failed, Tracks.EMPTY) { true })
    }

    private fun group(id: String, format: Format) =
        Tracks.Group(TrackGroup(id, format), false, intArrayOf(C.FORMAT_HANDLED), booleanArrayOf(false))

    private fun format(mime: String, channels: Int) = Format.Builder()
        .setSampleMimeType(mime).setLanguage("en").setChannelCount(channels).setSampleRate(48_000)
        .build()
}

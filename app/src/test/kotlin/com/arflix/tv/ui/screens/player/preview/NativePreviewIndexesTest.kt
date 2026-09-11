package com.arflix.tv.ui.screens.player.preview

import com.arflix.tv.data.model.StreamPreviewKind
import com.arflix.tv.data.model.StreamPreviewMetadata
import java.nio.ByteBuffer
import java.nio.ByteOrder
import org.junit.Assert.*
import org.junit.Test

class NativePreviewIndexesTest {
    private val bifUrl = "https://server.example/index.bif"
    private val metadata = StreamPreviewMetadata(StreamPreviewKind.JELLYFIN, itemId = "item", mediaSourceId = "version-b",
        mediaVersion = "v1", serverUrl = "https://server.example/jellyfin", durationMs = 25_000)

    private fun bif(multiplier: Int = 1000): ByteArray = ByteBuffer.allocate(88).order(ByteOrder.LITTLE_ENDIAN).apply {
        put(byteArrayOf(0x89.toByte(), 0x42, 0x49, 0x46, 0x0d, 0x0a, 0x1a, 0x0a))
        putInt(8, 0); putInt(12, 2); putInt(16, multiplier)
        putInt(64, 0); putInt(68, 88)
        putInt(72, 10); putInt(76, 92)
        putInt(80, -1); putInt(84, 98)
    }.array()

    @Test fun `BIF parses true timestamps byte ranges sentinel and final duration`() {
        val board = BifPreviewParser.parse(bif(), bifUrl, 98, 15_000)!!
        assertEquals(88, BifPreviewParser.indexSize(bif()))
        assertEquals(88L, board.cueAt(9999)!!.byteOffset)
        assertEquals(4, board.cueAt(9999)!!.byteLength)
        assertEquals(10000L, board.cueAt(10000)!!.startMs)
        assertEquals(6, board.cueAt(10000)!!.byteLength)
        assertNull(board.cueAt(15000))
    }

    @Test fun `BIF zero multiplier means milliseconds in units of one second`() {
        assertEquals(10000L, BifPreviewParser.parse(bif(0), bifUrl, 98, 15000)!!.cues[1].startMs)
        assertEquals(20000L, BifPreviewParser.parse(bif(2000), bifUrl, 98, 25000)!!.cues[1].startMs)
    }

    @Test fun `BIF without duration omits last unknown interval`() {
        assertEquals(1, BifPreviewParser.parse(bif(), bifUrl, 98, 0)!!.cues.size)
        assertNull(BifPreviewParser.parse(bif(), bifUrl, 98, 0)!!.cueAt(10000))
    }

    @Test fun `BIF rejects truncation version count offsets ordering and overflow`() {
        assertNull(BifPreviewParser.indexSize(bif().copyOf(20)))
        assertNull(BifPreviewParser.parse(bif().copyOf(80), bifUrl, 98, 15000))
        assertNull(BifPreviewParser.parse(bif(), bifUrl, 100, 15000))
        for ((offset, value) in listOf(0 to 0, 8 to 1, 12 to -1, 12 to 20001, 68 to 80, 76 to 87, 80 to 0,
            72 to 0, 72 to -2, 16 to -1)) {
            val malformed = bif().also { ByteBuffer.wrap(it).order(ByteOrder.LITTLE_ENDIAN).putInt(offset, value) }
            assertNull("offset=$offset value=$value", BifPreviewParser.parse(malformed, bifUrl, 98, 15000))
        }
    }

    private val jellyfin = """{"Id":"item","MediaSources":[{"Id":"version-b"}],"Trickplay":{
        "version-a":{"160":{"Width":160,"Height":90,"TileWidth":2,"TileHeight":2,"Interval":10000,"ThumbnailCount":3}},
        "version-b":{"320":{"Width":320,"Height":180,"TileWidth":2,"TileHeight":1,"Interval":10000,"ThumbnailCount":3}}
    }}"""

    @Test fun `Jellyfin binds exact version and retains base path tile geometry and final interval`() {
        val board = HomeServerPreviewParser.jellyfin(jellyfin, metadata, 480)!!
        assertEquals(3, board.cues.size)
        assertEquals(PreviewCrop(320, 0, 320, 180), board.cueAt(10000)!!.crop)
        assertEquals("https://server.example/jellyfin/Videos/item/Trickplay/320/1.jpg?MediaSourceId=version-b", board.cueAt(20000)!!.url)
        assertEquals(25000L, board.cueAt(20000)!!.endMs)
    }

    @Test fun `Jellyfin never substitutes another media version`() {
        assertNull(HomeServerPreviewParser.jellyfin(jellyfin, metadata.copy(mediaSourceId = "missing"), 480))
        assertNull(HomeServerPreviewParser.jellyfin(jellyfin, metadata.copy(itemId = "another"), 480))
        assertNull(HomeServerPreviewParser.jellyfin(jellyfin, metadata.copy(mediaETag = "changed"), 480))
        assertNull(HomeServerPreviewParser.jellyfin(jellyfin.replace("\"Interval\":10000", "\"Interval\":0"), metadata, 480))
    }

    @Test fun `Jellyfin treats dashed GUID spellings as same ID`() {
        val json = jellyfin.replace("version-b", "00112233445566778899aabbccddeeff")
        assertNotNull(HomeServerPreviewParser.jellyfin(json, metadata.copy(mediaSourceId = "00112233-4455-6677-8899-aabbccddeeff"), 480))
    }

    @Test fun `Plex requires matching part with existing sd index`() {
        val json = """{"MediaContainer":{"Metadata":[{"ratingKey":"item","Media":[{"Part":[{"id":"version-b","indexes":"sd"}]}]}]}}"""
        assertEquals("https://server.example/jellyfin/library/parts/version-b/indexes/sd", HomeServerPreviewParser.plexBifUrl(json, metadata))
        assertNull(HomeServerPreviewParser.plexBifUrl(json.replace("\"sd\"", "\"\""), metadata))
        assertNull(HomeServerPreviewParser.plexBifUrl(json, metadata.copy(mediaSourceId = "other")))
        assertNull(HomeServerPreviewParser.plexBifUrl(json.replace("{\"id\":\"version-b\",\"indexes\":\"sd\"}", "{\"id\":\"version-b\",\"indexes\":\"sd\"},{\"id\":\"c\"}"), metadata))
    }

    @Test fun `Emby refuses alternate-version item-only indexes`() {
        val single = """{"Id":"item","MediaSources":[{"Id":"version-b"}]}"""
        assertTrue(HomeServerPreviewParser.embySingleVersion(single, metadata))
        assertFalse(HomeServerPreviewParser.embySingleVersion(single.replace("[{", "[{\"Id\":\"other\"},{"), metadata))
        assertFalse(HomeServerPreviewParser.embySingleVersion(single, metadata.copy(mediaSourceId = "other")))
    }
}

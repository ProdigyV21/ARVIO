package com.arflix.tv.ui.screens.player.preview

import com.arflix.tv.data.model.StreamPreviewKind
import com.arflix.tv.data.model.StreamPreviewMetadata
import org.junit.Assert.*
import org.junit.Test

class PreviewStoryboardTest {
    private val url = "https://media.example/base/previews/index.vtt"

    @Test fun `VTT resolves relative sprite cues and preserves gaps and exclusive boundaries`() {
        val board = WebVttPreviewParser.parse("""
            WEBVTT

            first
            00:00.000 --> 00:05.000
            ../sheet.jpg#xywh=160,90,160,90

            00:10.000 --> 00:15.000 align:start
            /second.png
        """.trimIndent(), url)!!
        val cue = board.cueAt(4999)!!
        assertEquals("https://media.example/base/sheet.jpg", cue.url)
        assertEquals(PreviewCrop(160, 90, 160, 90), cue.crop)
        assertNull(board.cueAt(-1))
        assertNull(board.cueAt(5000))
        assertNull(board.cueAt(9999))
        assertEquals(10000L, board.cueAt(10000)!!.startMs)
        assertNull(board.cueAt(15000))
    }

    @Test fun `VTT handles BOM CRLF comments and hours`() {
        val text = "\uFEFFWEBVTT\r\n\r\nNOTE a comment\r\nignored\r\n\r\n01:02:03.004 --> 01:02:04.005\r\ns.jpg#xywh=pixel:0,0,10,20\r\n"
        val board = WebVttPreviewParser.parse(text, url)!!
        assertEquals(3_723_004L, board.cues.single().startMs)
        assertEquals(3_724_005L, board.cues.single().endMs)
    }

    @Test fun `VTT rejects ambiguous or malformed timing and crops`() {
        for (cue in listOf(
            "00:00.000 --> 00:00.000\ns.jpg",
            "00:60.000 --> 01:01.000\ns.jpg",
            "00:00.000 --> 00:01.000\ns.jpg#xywh=-1,0,10,10",
            "00:00.000 --> 00:01.000\ns.jpg#xywh=0,0,0,10",
            "00:00.000 --> 00:01.000\ns.jpg#xywh=2147483647,0,10,10",
            "00:00.000 --> 00:02.000\ns.jpg\n\n00:01.000 --> 00:03.000\ns.jpg"
        )) assertNull(cue, WebVttPreviewParser.parse("WEBVTT\n\n$cue", url))
    }

    @Test fun `VTT does not mistake subtitles or external resources for preview images`() {
        for (payload in listOf("<b>Hello</b>", "some subtitle text", "https://other.example/image.jpg", "file:///sdcard/image.jpg")) {
            assertNull(WebVttPreviewParser.parse("WEBVTT\n\n00:00.000 --> 00:01.000\n$payload", url))
        }
        assertNull(WebVttPreviewParser.parse("WEBVTT\nX-TIMESTAMP-MAP=MPEGTS:900000,LOCAL:00:00.000\n\n00:00.000 --> 00:01.000\ns.jpg", url))
    }

    @Test fun `VTT manifests and cue counts are bounded`() {
        assertNull(WebVttPreviewParser.parse("WEBVTT" + " ".repeat(PREVIEW_MAX_MANIFEST_BYTES), url))
        val cue = "00:00.000 --> 00:01.000\ns.jpg\n\n"
        assertNull(WebVttPreviewParser.parse("WEBVTT\n\n" + cue.repeat(PREVIEW_MAX_CUES + 1), url))
    }

    @Test fun `image HLS follows only explicitly advertised image variant`() {
        val master = "#EXTM3U\n#EXT-X-STREAM-INF:BANDWIDTH=1000\nvideo.m3u8\n" +
            "#EXT-X-IMAGE-STREAM-INF:BANDWIDTH=20,CODECS=\"jpeg\",URI=\"tiles.m3u8?a=1,b=2\""
        assertEquals("https://media.example/base/previews/tiles.m3u8?a=1,b=2", ImageHlsPreviewParser.childPlaylist(master, url))
        assertNull(ImageHlsPreviewParser.parse(master, url))
    }

    @Test fun `image HLS handles partial final sprite and standalone images`() {
        val board = ImageHlsPreviewParser.parse("""
            #EXTM3U
            #EXT-X-IMAGES-ONLY
            #EXTINF:25.0,
            #EXT-X-TILES:RESOLUTION=160x90,LAYOUT=2x2,DURATION=10.0
            one.jpg
            #EXTINF:3.125,
            two.jpg
            #EXT-X-ENDLIST
        """.trimIndent(), url)!!
        assertEquals(4, board.cues.size)
        assertEquals(PreviewCrop(0, 90, 160, 90), board.cueAt(24000)!!.crop)
        assertEquals(25000L, board.cueAt(24000)!!.endMs)
        assertEquals(28125L, board.cueAt(25000)!!.endMs)
        assertNull(board.cueAt(28125))
    }

    @Test fun `HLS rejects live encrypted discontinuous and byte range playlists`() {
        val head = "#EXTM3U\n#EXT-X-IMAGES-ONLY\n"
        val body = "#EXTINF:5,\none.jpg\n#EXT-X-ENDLIST"
        for (tag in listOf("#EXT-X-KEY:METHOD=AES-128", "#EXT-X-BYTERANGE:100@0", "#EXT-X-DISCONTINUITY", "#EXT-X-MEDIA-SEQUENCE:1", "#EXT-X-GAP")) {
            assertNull(tag, ImageHlsPreviewParser.parse(head + tag + "\n" + body, url))
        }
        assertNull(ImageHlsPreviewParser.parse(head + "#EXTINF:5,\none.jpg", url))
        assertNull(ImageHlsPreviewParser.parse(head + body.replace("one.jpg", "http://media.example/one.jpg"), url))
    }

    @Test fun `image HLS validates tile capacity and nonfinite numbers`() {
        val head = "#EXTM3U\n#EXT-X-IMAGES-ONLY\n#EXTINF:25,\n"
        val end = "\none.jpg\n#EXT-X-ENDLIST"
        assertNull(ImageHlsPreviewParser.parse(head + "#EXT-X-TILES:RESOLUTION=160x90,LAYOUT=1x1,DURATION=10" + end, url))
        assertNull(ImageHlsPreviewParser.parse(head.replace("25", "NaN") + end, url))
        assertNull(ImageHlsPreviewParser.parse(head + "#EXT-X-TILES:RESOLUTION=32768x90,LAYOUT=2x2,DURATION=10" + end, url))
    }

    @Test fun `identity separates account item media version and timeline without credential dependence`() {
        val metadata = StreamPreviewMetadata(StreamPreviewKind.JELLYFIN, "server", "account", "item", "source", "v1", serverUrl = "https://media.example/")
        val key = nativePreviewCacheIdentity(metadata)
        assertEquals(64, key.length)
        assertEquals(key, nativePreviewCacheIdentity(metadata.copy(headers = mapOf("X-Emby-Token" to "test-only-value"))))
        for (changed in listOf(metadata.copy(accountId = "other"), metadata.copy(itemId = "other"), metadata.copy(mediaSourceId = "other"),
            metadata.copy(mediaVersion = "v2"), metadata.copy(timelineOffsetMs = 10_000), metadata.copy(serverId = "other"))) {
            assertNotEquals(key, nativePreviewCacheIdentity(changed))
        }
    }
}

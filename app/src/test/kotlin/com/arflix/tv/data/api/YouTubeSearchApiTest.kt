package com.arflix.tv.data.api

import com.arflix.tv.data.model.KidsAgeLevel
import com.google.gson.JsonParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class YouTubeSearchApiTest {

    private fun webRenderer(id: String, title: String, length: String? = "3:05") = """
        {"videoRenderer":{
          "videoId":"$id",
          "title":{"runs":[{"text":"$title"}]},
          "ownerText":{"runs":[{"text":"Example Channel"}]},
          ${if (length != null) "\"lengthText\":{\"simpleText\":\"$length\"}," else ""}
          "shortViewCountText":{"simpleText":"1.2M views"},
          "thumbnail":{"thumbnails":[
            {"url":"https://i.ytimg.com/vi/$id/small.jpg","width":360,"height":202},
            {"url":"https://i.ytimg.com/vi/$id/large.jpg","width":720,"height":404}
          ]}
        }}
    """

    @Test
    fun `web answer yields videos in order with channel, length and the largest thumbnail`() {
        val json = """
            {"contents":{"twoColumnSearchResultsRenderer":{"primaryContents":{"sectionListRenderer":{"contents":[
              {"itemSectionRenderer":{"contents":[
                ${webRenderer("AAAAAAAAAA1", "First video")},
                {"channelRenderer":{"channelId":"UCexample"}},
                ${webRenderer("AAAAAAAAAA2", "Second video", length = "1:02:03")}
              ]}}
            ]}}}}}
        """
        val videos = YouTubeSearchApi.parseSearchResponse(json)

        assertEquals(listOf("AAAAAAAAAA1", "AAAAAAAAAA2"), videos.map { it.id })
        val first = videos.first()
        assertEquals("First video", first.title)
        assertEquals("Example Channel", first.channel)
        assertEquals("3:05", first.duration)
        assertEquals("1.2M views", first.views)
        assertEquals("https://i.ytimg.com/vi/AAAAAAAAAA1/large.jpg", first.thumbnailUrl)
        assertEquals("1:02:03", videos[1].duration)
    }

    @Test
    fun `kids answer uses compact renderers and byline text`() {
        val json = """
            {"contents":{"sectionListRenderer":{"contents":[{"itemSectionRenderer":{"contents":[
              {"compactVideoRenderer":{
                "videoId":"BBBBBBBBBB1",
                "title":{"simpleText":"Kids video"},
                "longBylineText":{"runs":[{"text":"Kids Channel"}]},
                "lengthText":{"simpleText":"9:14"},
                "thumbnail":{"thumbnails":[{"url":"//i.ytimg.com/vi/BBBBBBBBBB1/hq.jpg","width":480,"height":270}]}
              }}
            ]}}]}}}
        """
        val video = YouTubeSearchApi.parseSearchResponse(json).single()

        assertEquals("BBBBBBBBBB1", video.id)
        assertEquals("Kids Channel", video.channel)
        assertEquals("https://i.ytimg.com/vi/BBBBBBBBBB1/hq.jpg", video.thumbnailUrl)
    }

    @Test
    fun `live streams, bad ids and duplicates are left out`() {
        val json = """
            {"contents":[
              ${webRenderer("CCCCCCCCCC1", "Live now", length = null)},
              ${webRenderer("not-an-id", "Broken")},
              ${webRenderer("CCCCCCCCCC2", "Kept")},
              ${webRenderer("CCCCCCCCCC2", "Kept again")}
            ]}
        """
        assertEquals(listOf("CCCCCCCCCC2"), YouTubeSearchApi.parseSearchResponse(json).map { it.id })
    }

    @Test
    fun `garbage answers are empty, not a crash`() {
        assertTrue(YouTubeSearchApi.parseSearchResponse("").isEmpty())
        assertTrue(YouTubeSearchApi.parseSearchResponse("<html>bot check</html>").isEmpty())
        assertTrue(YouTubeSearchApi.parseSearchResponse("""{"contents":{}}""").isEmpty())
    }

    @Test
    fun `missing thumbnail falls back to the standard still`() {
        val json = """{"videoRenderer":{"videoId":"DDDDDDDDDD1","title":{"simpleText":"T"},"lengthText":{"simpleText":"1:00"}}}"""
        assertEquals("https://i.ytimg.com/vi/DDDDDDDDDD1/mqdefault.jpg",
            YouTubeSearchApi.parseSearchResponse(json).single().thumbnailUrl)
    }

    @Test
    fun `regular search asks the web client for videos only`() {
        val body = JsonParser.parseString(YouTubeSearchApi.buildRequestBody("space", null, "en", "US")).asJsonObject
        val client = body.getAsJsonObject("context").getAsJsonObject("client")

        assertEquals("WEB", client.get("clientName").asString)
        assertEquals("EgIQAQ==", body.get("params").asString)
        assertFalse(client.has("kidsAppInfo"))
        assertEquals("space", body.get("query").asString)
    }

    @Test
    fun `kids search asks YouTube Kids at the profile's tier`() {
        val body = JsonParser.parseString(
            YouTubeSearchApi.buildRequestBody("space", YouTubeKidsCorpus.YOUNGER, "iw", "IL")
        ).asJsonObject
        val client = body.getAsJsonObject("context").getAsJsonObject("client")
        val settings = client.getAsJsonObject("kidsAppInfo").getAsJsonObject("contentSettings")

        assertEquals("WEB_KIDS", client.get("clientName").asString)
        assertEquals("KIDS_CORPUS_PREFERENCE_YOUNGER", settings.get("corpusPreference").asString)
        assertEquals("IL", client.get("gl").asString)
        assertNull(body.get("params"))
    }

    @Test
    fun `unknown device region falls back to US`() {
        val client = JsonParser.parseString(YouTubeSearchApi.buildRequestBody("q", null, "", ""))
            .asJsonObject.getAsJsonObject("context").getAsJsonObject("client")
        assertEquals("US", client.get("gl").asString)
        assertEquals("en", client.get("hl").asString)
    }

    @Test
    fun `every kids level maps to a YouTube Kids tier, never to regular YouTube`() {
        assertEquals(YouTubeKidsCorpus.YOUNGER, YouTubeKidsCorpus.forLevel(KidsAgeLevel.LITTLE_KIDS))
        assertEquals(YouTubeKidsCorpus.OLDER, YouTubeKidsCorpus.forLevel(KidsAgeLevel.OLDER_KIDS))
        assertEquals(YouTubeKidsCorpus.OLDER, YouTubeKidsCorpus.forLevel(KidsAgeLevel.TEENS))
    }
}

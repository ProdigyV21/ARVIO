package com.arflix.tv.data.repository.wetrakr

import com.google.gson.JsonParser
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class WeTrakrListsParsingTest {
    private fun json(text: String) = JsonParser.parseString(text)

    @Test fun `external lookup id comes from the object or the match of the asked type`() {
        assertEquals(42L, weTrakrResolvedId(json("""{"id":42,"type":"movie"}"""), "movie"))
        assertEquals(7L, weTrakrResolvedId(json("""{"movie":{"id":6},"show":{"id":7}}"""), "show"))
        assertEquals(9L, weTrakrResolvedId(json("""[{"id":8,"type":"movie"},{"id":9,"type":"show"}]"""), "show"))
        assertNull(weTrakrResolvedId(json("""{"error":"not_found"}"""), "movie"))
    }

    @Test fun `arrays are read bare or under a wrapper key`() {
        assertEquals(2, weTrakrArray(json("""[{"id":1},{"id":2}]""")).size)
        assertEquals(1, weTrakrArray(json("""{"lists":[{"id":1}]}""")).size)
        assertEquals(0, weTrakrArray(json("""{"other":1}""")).size)
    }

    @Test fun `a list write fails only when a title is not found or unresolved`() {
        assertTrue(weTrakrListWriteSucceeded(json("""{"added":{"movies":1},"notFound":{"movies":[]}}""")))
        assertFalse(weTrakrListWriteSucceeded(json("""{"added":{"movies":0},"notFound":{"movies":[{"ids":{"tmdb":1}}]}}""")))
        assertFalse(weTrakrListWriteSucceeded(json("""{"unresolved":[{"ids":{"tmdb":1}}]}""")))
        assertFalse(weTrakrListWriteSucceeded(json("""[]""")))
    }

    @Test fun `list items become media items by TMDB id`() {
        val item = weTrakrMediaItem(json("""{"type":"show","title":"Dark","ids":{"tmdb":70523},"first_air_date":"2017-12-01"}""").asJsonObject)
        assertEquals(70523, item?.id)
        assertEquals("2017", item?.year)
        assertNull(weTrakrMediaItem(json("""{"type":"person","ids":{"tmdb":1}}""").asJsonObject))
    }

    @Test fun `recommendation rows are recognised by id`() {
        assertEquals(com.arflix.tv.data.model.MediaType.TV, WeTrakrLists.recommendationType(WeTrakrLists.RECS_SHOWS_ID))
        assertNull(WeTrakrLists.recommendationType("trending_tv"))
    }
}

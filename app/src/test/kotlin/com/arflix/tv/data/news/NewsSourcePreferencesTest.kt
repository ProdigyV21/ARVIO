package com.arflix.tv.data.news

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class NewsSourcePreferencesTest {
    @Test fun unsetOrMalformedValuesFallBackToFoxSkyAndBbc() {
        for (json in listOf(null, "", "broken", "[]", "42")) {
            assertEquals(listOf("fox", "sky", "bbc"), NewsSourcePreferences.decode(json).selectedIds)
        }
        // A saved empty choice is the user's, not a reason to bring the defaults back.
        assertTrue(NewsSourcePreferences.decode("""{"selected":[]}""").selectedIds.isEmpty())
    }

    @Test fun unknownIdsAndInvalidCustomFeedsAreDropped() {
        val json = """{"selected":["fox","gone","custom_a","fox","custom_b",7],
            "custom":[{"id":"custom_a","name":"Mine","url":"https://feeds.example.com/a.xml"},
                      {"id":"custom_b","url":"javascript:alert(1)"},
                      {"id":"fox","url":"https://evil.example.com"}]}"""
        val selection = NewsSourcePreferences.decode(json)
        assertEquals(listOf("fox", "custom_a"), selection.selectedIds)
        assertEquals(listOf("custom_a"), selection.customProviders.map { it.id })
        assertEquals("Fox News", selection.providers.first().name)
    }

    @Test fun topicsRoundTripAndDecideWhichProvidersAreShown() {
        var selection = NewsSourcePreferences.addCustom(NewsSourceSelection(listOf("fox", "ynet_flash", "ynet")), "", "https://feeds.example.com/x")!!
        assertEquals(selection.providers, selection.shownProviders)

        selection = NewsSourcePreferences.toggleTopic(selection, NewsTopic.SPORTS)
        selection = NewsSourcePreferences.toggleTopic(selection, NewsTopic.BUSINESS)
        assertEquals(listOf(NewsTopic.BUSINESS, NewsTopic.SPORTS), selection.topics)
        // Fox and ynet cover sports; the flash feed covers nothing; a user-added feed is always shown.
        assertEquals(listOf("fox", "ynet", selection.customProviders.single().id), selection.shownProviders.map { it.id })
        assertEquals(listOf("ynet_flash"), selection.uncoveredProviders.map { it.id })
        assertEquals(selection, NewsSourcePreferences.decode(NewsSourcePreferences.encode(selection)))

        selection = NewsSourcePreferences.toggleTopic(selection, NewsTopic.SPORTS)
        selection = NewsSourcePreferences.toggleTopic(selection, NewsTopic.BUSINESS)
        assertTrue("removing the last topic means all topics", selection.topics.isEmpty())
        selection = NewsSourcePreferences.toggleTopic(NewsSourcePreferences.toggleTopic(selection, NewsTopic.TECH), null)
        assertTrue(selection.topics.isEmpty())

        val unknown = NewsSourcePreferences.decode("""{"selected":["bbc"],"topics":["sports","gossip",3,"sports"]}""")
        assertEquals(listOf(NewsTopic.SPORTS), unknown.topics)
    }

    @Test fun pathSectionTopicsMatchWholeSegmentsOnly() {
        val israelHayom = NewsProviders.presets.first { it.id == "israelhayom" }
        val sport = israelHayom.topics.getValue(NewsTopic.SPORTS)
        assertTrue(sport.matches("https://www.israelhayom.co.il/sport/israeli-soccer/article/123"))
        assertFalse(sport.matches("https://www.israelhayom.co.il/news/politics/article/sport-funding"))
        assertFalse(sport.matches("not a url"))
        // Every preset topic has a source of one kind or the other.
        NewsProviders.presets.flatMap { it.topics.values }.forEach { assertTrue(it.feedUrl != null || it.pathSections.isNotEmpty()) }
    }

    @Test fun everyPresetIsAFeedAndRetiredPresetsDropOutOfSavedChoices() {
        // CNN only had a scraped web page, not a feed, and was removed.
        assertFalse(NewsProviders.presets.any { it.id == "cnn" })
        assertEquals(listOf("fox"), NewsSourcePreferences.decode("""{"selected":["cnn","fox"]}""").selectedIds)
        assertTrue(NewsProviders.defaultSelection.all(NewsProviders::isPreset))
    }

    @Test fun interleaveKeepsEachListsOrderAndDropsRepeats() {
        fun a(link: String, title: String = link) = NewsArticle(link = link, title = title, providerId = "p")
        val merged = NewsMerge.interleave(listOf(listOf(a("1"), a("2"), a("3")), listOf(a("x"), a("1"), a("y", " 2 "))))
        assertEquals(listOf("1", "x", "2", "3"), merged.map { it.link })
    }

    @Test fun addingToggleAndRemoveRoundTrip() {
        var selection = NewsSourceSelection(listOf("fox"))
        selection = NewsSourcePreferences.addCustom(selection, "", "feeds.example.co.il/rss")!!
        val custom = selection.customProviders.single()
        assertEquals("https://feeds.example.co.il/rss", custom.url)
        assertEquals("feeds.example.co.il", custom.name)
        assertEquals("he", custom.language)
        assertEquals(listOf("fox", custom.id), selection.selectedIds)
        // Same URL again does not create a second feed.
        assertEquals(selection, NewsSourcePreferences.addCustom(selection, "Again", "https://feeds.example.co.il/rss"))
        assertNull(NewsSourcePreferences.addCustom(selection, "", "not a url"))

        val decoded = NewsSourcePreferences.decode(NewsSourcePreferences.encode(selection))
        assertEquals(selection, decoded)

        selection = NewsSourcePreferences.toggle(selection, "fox")
        assertEquals(listOf(custom.id), selection.selectedIds)
        selection = NewsSourcePreferences.toggle(selection, "bbc")
        assertEquals(listOf(custom.id, "bbc"), selection.selectedIds)
        selection = NewsSourcePreferences.removeCustom(selection, custom.id)
        assertEquals(listOf("bbc"), selection.selectedIds)
        assertTrue(selection.customProviders.isEmpty())
    }
}

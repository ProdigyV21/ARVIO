package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class PagedChannelWindowTest {
    @Test
    fun categoryWindowsStartAtFirstChannelAndGrowWithoutGapsForBothProviders() {
        val all = listOf("first", "second").flatMap { provider ->
            listOf("News", "Sports").flatMap { group -> List(300) { index ->
                IptvChannel("$provider:$group:$index", "Channel $index",
                    "https://example.invalid/$index.ts", group)
            } }
        }
        val counts = listOf("first", "second").flatMap { provider ->
            listOf("News", "Sports").map { Triple(provider, it, 300) }
        }
        val tree = buildPagedStartupChannelState(all.take(144), all.size, counts,
            emptySet(), emptySet()).tree
        val repository = mockk<IptvRepository>()
        every { repository.pagedChannelWindow(any(), any(), any(), any(), any()) } answers {
            val provider = firstArg<String?>()
            val group = secondArg<String?>()
            all.filter { (provider == null || it.id.startsWith("$provider:")) &&
                (group == null || it.group == group) }.drop(arg(2)).take(arg(3))
        }
        counts.forEach { (provider, group, _) ->
            listOf(144, 288, 432).forEach { limit ->
                val result = loadPagedChannelWindow(repository, playlistGroupCategoryId(provider, group),
                    limit, all.size, counts, tree, emptyList(), emptyList())
                assertEquals(all.filter { it.id.startsWith("$provider:") && it.group == group }.take(limit), result)
            }
        }
        assertEquals(all.take(288), loadPagedChannelWindow(repository, "all", 288,
            all.size, counts, tree, emptyList(), emptyList()))
    }
}

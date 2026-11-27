package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MultiviewTest {
    private fun channel(id: String) = IptvChannel(id, id, "http://x/$id", "Sports")
    private fun tile(id: String, vararg alternates: String) =
        MultiviewTile(key = "game:$id", title = id, channel = channel(id), alternates = alternates.map(::channel))

    @Test fun addingRespectsCapacityAndDuplicates() {
        val one = (emptyList<MultiviewTile>().adding(tile("a"), 2) as MultiviewAddResult.Added).tiles
        val two = (one.adding(tile("b"), 2) as MultiviewAddResult.Added).tiles
        assertEquals(MultiviewAddResult.Full, two.adding(tile("c"), 2))
        assertEquals(MultiviewAddResult.AlreadyThere(1), two.adding(tile("b"), 2))
        // The same channel under another game is still the same stream.
        assertEquals(MultiviewAddResult.AlreadyThere(0), two.adding(tile("a").copy(key = "other"), 4))
        // Capacity never goes past four, whatever is asked.
        val four = listOf("a", "b", "c", "d").map { tile(it) }
        assertEquals(MultiviewAddResult.Full, four.adding(tile("e"), 9))
    }

    @Test fun capacityFollowsDecodersAndMemory() {
        assertEquals(4, multiviewCapacity(decoderInstances = 16, isLowRamDevice = false, memoryClassMb = 512))
        assertEquals(2, multiviewCapacity(decoderInstances = 16, isLowRamDevice = true, memoryClassMb = 512))
        assertEquals(3, multiviewCapacity(decoderInstances = null, isLowRamDevice = false, memoryClassMb = 200))
        assertEquals(2, multiviewCapacity(decoderInstances = 3, isLowRamDevice = false, memoryClassMb = 512))
        assertEquals(1, multiviewCapacity(decoderInstances = 1, isLowRamDevice = false, memoryClassMb = 512))
        assertEquals(4, multiviewCapacity(decoderInstances = 0, isLowRamDevice = false, memoryClassMb = 512))
    }

    @Test fun cellsCoverTheAreaWithoutOverlap() {
        for (count in 1..4) {
            val cells = multiviewCells(count)
            assertEquals(count, cells.size)
            cells.forEach { assertTrue(it.x >= 0f && it.y >= 0f && it.x + it.width <= 1.0001f && it.y + it.height <= 1.0001f) }
            for (i in cells.indices) for (j in cells.indices) if (i < j) {
                val a = cells[i]; val b = cells[j]
                val overlap = a.x < b.x + b.width - 1e-4f && b.x < a.x + a.width - 1e-4f &&
                    a.y < b.y + b.height - 1e-4f && b.y < a.y + a.height - 1e-4f
                assertTrue("cells $i and $j of $count overlap", !overlap)
            }
        }
        assertTrue(multiviewCells(0).isEmpty())
        assertEquals(4, multiviewCells(7).size)
    }

    @Test fun dpadMovesBetweenNeighbours() {
        // 2x2: 0 1 / 2 3
        assertEquals(1, multiviewNeighbour(0, MultiviewDirection.Right, 4))
        assertEquals(2, multiviewNeighbour(0, MultiviewDirection.Down, 4))
        assertEquals(0, multiviewNeighbour(0, MultiviewDirection.Left, 4))
        assertEquals(1, multiviewNeighbour(3, MultiviewDirection.Up, 4))
        // Three: big tile left, two stacked right.
        assertEquals(1, multiviewNeighbour(0, MultiviewDirection.Right, 3))
        assertEquals(2, multiviewNeighbour(1, MultiviewDirection.Down, 3))
        assertEquals(0, multiviewNeighbour(2, MultiviewDirection.Left, 3))
        assertEquals(1, multiviewNeighbour(0, MultiviewDirection.Right, 2))
        assertEquals(0, multiviewNeighbour(0, MultiviewDirection.Up, 2))
    }

    @Test fun audioTileGetsTheSharpestPicture() {
        assertEquals(Int.MAX_VALUE, multiviewMaxVideoHeight(hasAudio = true, count = 1))
        assertEquals(1080, multiviewMaxVideoHeight(hasAudio = true, count = 2))
        assertEquals(720, multiviewMaxVideoHeight(hasAudio = true, count = 4))
        assertEquals(540, multiviewMaxVideoHeight(hasAudio = false, count = 4))
    }

    @Test fun audioStaysWithItsGameWhenATileCloses() {
        assertEquals(1, multiviewAudioAfterRemoval(audioIndex = 2, removed = 0, remaining = 3))
        assertEquals(2, multiviewAudioAfterRemoval(audioIndex = 2, removed = 3, remaining = 3))
        assertEquals(2, multiviewAudioAfterRemoval(audioIndex = 3, removed = 3, remaining = 3))
        assertEquals(0, multiviewAudioAfterRemoval(audioIndex = 0, removed = 0, remaining = 0))
    }

    @Test fun failedTileTriesEachAlternateOnce() {
        val t = tile("a", "b", "c")
        assertEquals("b", t.nextChannelAfter("a", setOf("a"))?.id)
        assertEquals("c", t.nextChannelAfter("b", setOf("a", "b"))?.id)
        assertNull(t.nextChannelAfter("c", setOf("a", "b", "c")))
    }

    @Test
    fun portraitStacksFullWidthGames() {
        val cells = multiviewCells(3, portrait = true)
        assertEquals(3, cells.size)
        assertTrue(cells.all { it.x == 0f && it.width == 1f })
        assertEquals(1, multiviewNeighbour(0, MultiviewDirection.Down, 3, portrait = true))
        assertEquals(0, multiviewNeighbour(0, MultiviewDirection.Right, 3, portrait = true))
    }
}

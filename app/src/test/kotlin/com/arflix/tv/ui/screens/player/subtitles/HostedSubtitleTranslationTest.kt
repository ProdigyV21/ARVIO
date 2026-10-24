package com.arflix.tv.ui.screens.player.subtitles

import com.arflix.tv.data.repository.HostedSubtitleRepository
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HostedSubtitleTranslationTest {

    @Test
    fun `hosted translation needs no personal key`() = runBlocking {
        val calls = mutableListOf<Pair<List<String>, String>>()
        val service = SubtitleTranslationService(
            apiKeyProvider = { "" },
            hostedTranslator = { lines, language ->
                calls += lines to language
                TranslationResult(lines.map { "$language:$it" }, true)
            }
        )
        val result = service.translateBatch(listOf("Hello", "Bye"), "Dutch")
        assertTrue(result.success)
        assertEquals(listOf("Dutch:Hello", "Dutch:Bye"), result.lines)
        assertEquals(1, calls.size)
    }

    @Test
    fun `a hosted refusal is passed through for the player to report`() = runBlocking {
        val service = SubtitleTranslationService(
            apiKeyProvider = { "" },
            hostedTranslator = { lines, _ -> TranslationResult(lines, false, HostedSubtitleRepository.ERROR_PREMIUM_REQUIRED) }
        )
        val result = service.translateBatch(listOf("Hello"), "Dutch")
        assertFalse(result.success)
        assertEquals(HostedSubtitleRepository.ERROR_PREMIUM_REQUIRED, result.errorMessage)
        assertEquals(listOf("Hello"), result.lines)
    }

    @Test
    fun `requests respect the service's line and size limits`() {
        val repository = HostedSubtitleRepository(authRepository = mockk(relaxed = true))
        val lines = List(130) { "line $it" }
        val chunks = repository.chunkIndices(lines.indices.toList(), lines)
        assertEquals(listOf(60, 60, 10), chunks.map { it.size })

        val long = List(40) { "x".repeat(390) }
        val sized = repository.chunkIndices(long.indices.toList(), long)
        assertTrue(sized.all { chunk -> chunk.sumOf { long[it].length } <= 11_000 })
        assertEquals(40, sized.sumOf { it.size })
    }
}

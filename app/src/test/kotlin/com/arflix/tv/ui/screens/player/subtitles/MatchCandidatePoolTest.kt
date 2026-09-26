package com.arflix.tv.ui.screens.player.subtitles

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MatchCandidatePoolTest {

    private data class Candidate(val id: String, val score: Double, val offsetMs: Long = 0L)

    private val acceptBar = 0.70
    private fun reject(c: Candidate) = c.copy(score = minOf(c.score, acceptBar - 0.01), offsetMs = 0L)

    @Test
    fun `evaluated candidates are rejected even when nothing retimed`() {
        // Regression (PR #756 review): with no confident retime at all, the sweep's verdicts used
        // to come back unchanged — a 0.78 shift the consistency check had refused was accepted.
        val swept = listOf(Candidate("a", 0.78, offsetMs = 5_800L), Candidate("b", 0.66))

        val verdicts = applyRetimeVerdicts(
            candidates = swept,
            keyOf = { it.id },
            evaluated = setOf("a", "b"),
            confident = emptyMap<String, Double>(),
            reject = ::reject,
            accept = { c, quality -> c.copy(score = quality, offsetMs = 0L) }
        )

        assertThat(verdicts.all { it.score < acceptBar && it.offsetMs == 0L }).isTrue()
    }

    @Test
    fun `confident retimes win, unevaluated candidates keep their verdict`() {
        val swept = listOf(
            Candidate("retimed", 0.40),
            Candidate("refused", 0.75, offsetMs = 1_200L),
            Candidate("past cap", 0.72, offsetMs = 900L),
            Candidate("not loaded", 0.10)
        )

        val verdicts = applyRetimeVerdicts(
            candidates = swept,
            keyOf = { it.id.takeUnless { id -> id == "not loaded" } },
            evaluated = setOf("retimed", "refused"),
            confident = mapOf("retimed" to 0.93),
            reject = ::reject,
            accept = { c, quality -> c.copy(score = quality, offsetMs = 0L) }
        ).associateBy { it.id }

        assertThat(verdicts.getValue("retimed").score).isEqualTo(0.93)
        assertThat(verdicts.getValue("refused").score).isLessThan(acceptBar)
        assertThat(verdicts.getValue("refused").offsetMs).isEqualTo(0L)
        assertThat(verdicts.getValue("past cap")).isEqualTo(Candidate("past cap", 0.72, offsetMs = 900L))
        assertThat(verdicts.getValue("not loaded")).isEqualTo(Candidate("not loaded", 0.10))
    }

    @Test
    fun `providers are taken in turn so a bounded pool keeps every provider`() {
        // Release-name ranking puts numeric-id providers (OpenSubtitles) last.
        val ranked = listOf("W1", "W2", "K1", "W3", "K2", "W4", "O1", "O2")
        val ordered = MatchCandidatePool.providerInterleaved(ranked) {
            when (it[0]) { 'W' -> "Wizdom"; 'K' -> "Ktuvit"; else -> "OpenSubtitles" }
        }

        assertThat(ordered).containsExactly("W1", "K1", "O1", "W2", "K2", "O2", "W3", "W4").inOrder()
        assertThat(ordered.take(3).map { it[0] }.toSet()).containsExactly('W', 'K', 'O')
    }

    @Test
    fun `loading stops at the candidate cap`() = runBlocking {
        val loadedIds = mutableListOf<Int>()
        val (loaded, tried) = MatchCandidatePool.loadBounded(
            pool = (1..50).toList(),
            batchSize = 6,
            maxItems = 30,
            budgetMs = 60_000L,
            startedAtMs = 0L,
            now = { 0L },
            load = { id -> synchronized(loadedIds) { loadedIds += id }; if (id % 10 == 0) null else "cues$id" }
        )

        assertThat(tried).isEqualTo(30)
        assertThat(loadedIds.max()).isEqualTo(30)
        assertThat(loaded.map { it.first }).isEqualTo((1..30).filter { it % 10 != 0 })
    }

    @Test
    fun `no new batch starts once the time budget is spent`() = runBlocking {
        var clock = 0L
        val (loaded, tried) = MatchCandidatePool.loadBounded(
            pool = (1..30).toList(),
            batchSize = 6,
            maxItems = 30,
            budgetMs = 20_000L,
            startedAtMs = 0L,
            now = { clock },
            load = { id -> clock += 1_500L; "cues$id" } // each batch of 6 costs 9s here
        )

        // Batches start at 0s, 9s and 18s; the fourth would start at 27s — past the 20s budget.
        assertThat(tried).isEqualTo(18)
        assertThat(loaded).hasSize(18)
    }
}

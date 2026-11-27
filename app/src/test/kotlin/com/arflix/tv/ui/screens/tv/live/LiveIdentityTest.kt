package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertSame
import org.junit.Test

class LiveIdentityTest {
    @Test fun equalListKeepsPreviousInstance() {
        val previous = listOf("a", "b")
        assertSame(previous, listOf("a", "b").reuseIfEqual(previous))
    }

    @Test fun changedListIsPublished() {
        val previous = listOf("a", "b")
        val incoming = listOf("a", "c")
        assertSame(incoming, incoming.reuseIfEqual(previous))
        assertSame(incoming, incoming.reuseIfEqual(null))
    }

    @Test fun byReferenceIgnoresEqualContent() {
        val map = mapOf("x" to 1)
        assertEquals(ByReference(map), ByReference(map))
        assertNotEquals(ByReference(map), ByReference(HashMap(map)))
    }
}

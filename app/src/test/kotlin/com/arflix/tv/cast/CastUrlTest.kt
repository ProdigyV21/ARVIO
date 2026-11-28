package com.arflix.tv.cast

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CastUrlTest {
    @Test fun loopbackAndMissingHostsAreLocal() {
        listOf(null, "localhost", "LOCALHOST", "127.0.0.1", "::1", "[::1]")
            .forEach { assertTrue("expected local: $it", isLocalHost(it)) }
    }

    @Test fun remoteHostsAreNotLocal() {
        listOf("example.com", "192.168.1.20", "cdn.debrid.example", "10.0.0.5", "[2001:db8::1]")
            .forEach { assertFalse("expected remote: $it", isLocalHost(it)) }
    }
}

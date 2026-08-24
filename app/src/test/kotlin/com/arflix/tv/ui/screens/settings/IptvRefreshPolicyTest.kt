package com.arflix.tv.ui.screens.settings

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IptvRefreshPolicyTest {

    @Test
    fun manualRefreshReloadsPlaylistAndEpgFromNetwork() {
        val policy = settingsIptvRefreshPolicy(force = true)

        assertTrue(policy.forcePlaylistReload)
        assertTrue(policy.forceEpgReload)
        assertTrue(policy.allowNetworkEpgFetch)
    }

    @Test
    fun automaticConfigLoadKeepsNetworkEpgDeferred() {
        val policy = settingsIptvRefreshPolicy(force = false)

        assertFalse(policy.forcePlaylistReload)
        assertFalse(policy.forceEpgReload)
        assertFalse(policy.allowNetworkEpgFetch)
    }
}

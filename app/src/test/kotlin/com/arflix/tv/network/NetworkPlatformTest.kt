package com.arflix.tv.network

import android.app.Application
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class NetworkPlatformTest {
    @Test fun publicSuffixAssetsCanBeReadWithoutStartupProvider() {
        val context = RuntimeEnvironment.getApplication()
        initializeNetworkPlatform(context)
        initializeNetworkPlatform(context)
        assertEquals("example.co.uk", "https://cdn.example.co.uk/path".toHttpUrl().topPrivateDomain())
    }
}

package com.arflix.tv.data.repository

import kotlinx.coroutines.CancellationException
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class LegacyPluginBackupTest {
    private val local = """{"profiles":[{"id":"local"}],"addons":[{"id":"new-addon"}],"accentColor":"new"}"""

    @Test fun legacyPluginDataIsPreservedWithoutRestoringUnrelatedRemoteSettings() {
        val remote = """{"pluginRepositories":[{"url":"https://example.test/repo"}],"pluginScrapers":[{"id":"one","futureField":3}],"pluginsEnabled":true,"accentColor":"old","addons":[]}"""
        val merged = JSONObject(preserveLegacyPluginBackup(local, Result.success(remote)).getOrThrow())
        val expected = JSONObject(remote)
        for (key in listOf("pluginRepositories", "pluginScrapers", "pluginsEnabled")) {
            assertEquals(expected.get(key).toString(), merged.get(key).toString())
        }
        assertEquals("new", merged.getString("accentColor"))
        assertEquals("new-addon", merged.getJSONArray("addons").getJSONObject(0).getString("id"))
        assertEquals("local", merged.getJSONArray("profiles").getJSONObject(0).getString("id"))
    }

    @Test fun explicitRemoteRemovalAndDisabledStateArePreserved() {
        val merged = JSONObject(preserveLegacyPluginBackup(local,
            Result.success("""{"pluginRepositories":[],"pluginScrapers":[],"pluginsEnabled":false}""")).getOrThrow())
        assertEquals(0, merged.getJSONArray("pluginRepositories").length())
        assertEquals(0, merged.getJSONArray("pluginScrapers").length())
        assertFalse(merged.getBoolean("pluginsEnabled"))
    }

    @Test fun freshAccountsDoNotAcquirePluginSettings() {
        assertEquals(local, preserveLegacyPluginBackup(local, Result.success(null)).getOrThrow())
        val merged = JSONObject(preserveLegacyPluginBackup(local, Result.success("{}")).getOrThrow())
        assertFalse(merged.has("pluginRepositories"))
        assertFalse(merged.has("pluginScrapers"))
        assertFalse(merged.has("pluginsEnabled"))
    }

    @Test fun failedRemoteReadMustNotProduceAReplacementBackup() {
        val failure = IllegalStateException("offline")
        assertSame(failure, preserveLegacyPluginBackup(local, Result.failure(failure)).exceptionOrNull())
    }

    @Test fun malformedRemotePayloadMustNotBeOverwritten() {
        assertTrue(preserveLegacyPluginBackup(local, Result.success("not json")).isFailure)
    }

    @Test(expected = CancellationException::class)
    fun cancellationIsNotConvertedIntoASyncFailure() {
        preserveLegacyPluginBackup(local, Result.failure(CancellationException("cancelled")))
    }
}

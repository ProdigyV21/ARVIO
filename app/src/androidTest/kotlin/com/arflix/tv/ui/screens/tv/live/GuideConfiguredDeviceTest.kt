package com.arflix.tv.ui.screens.tv.live

import android.os.Bundle
import android.os.SystemClock
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.di.GuideAuditEntryPoint
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.data.repository.IptvPlaylistEntry
import dagger.hilt.android.EntryPointAccessors
import java.net.URI
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import org.junit.Test

/** Explicitly invoked local audits; never ship in the release APK. */
class GuideConfiguredDeviceTest {
    @Test fun removeExplicitTestProfile() = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val id = InstrumentationRegistry.getArguments().getString("testProfileId")
            ?: return@runBlocking
        val access = EntryPointAccessors.fromApplication(instrumentation.targetContext, RepositoryAccessEntryPoint::class.java)
        val profiles = access.profileRepository()
        val target = profiles.getProfiles().single { it.id == id && it.name == "IPTV Navigation Test" }
        val original = profiles.getProfiles().single { it.name == "Arvind" }
        profiles.setActiveProfile(original.id)
        access.profileManager().setCurrentProfileId(original.id)
        profiles.deleteProfile(target.id)
        check(profiles.getProfiles().none { it.id == target.id })
        instrumentation.sendStatus(0, Bundle().apply { putString("stream", "Original profile restored; temporary profile removed.\n") })
    }

    @Test fun setupExplicitlyProvidedPlaylist() = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val url = InstrumentationRegistry.getArguments().getString("playlistUrl")
            ?: return@runBlocking
        require(url.startsWith("http://") || url.startsWith("https://"))
        val context = instrumentation.targetContext
        val access = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java)
        val profiles = access.profileRepository()
        val profile = profiles.getProfiles().firstOrNull { it.name == "IPTV Navigation Test" }
            ?: profiles.createProfile("IPTV Navigation Test", 0xFF447777)
        profiles.setActiveProfile(profile.id)
        access.profileManager().setCurrentProfileId(profile.id)
        val repository = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).iptvRepository()
        repository.savePlaylists(listOf(IptvPlaylistEntry("guide-audit", "TREX test", url,
            importVod = false, importSeries = false)))
        instrumentation.sendStatus(0, Bundle().apply { putString("stream", "Test profile ready: ${profile.id}\n") })
    }

    @Test fun reportConfiguredGuide() = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val repository = EntryPointAccessors.fromApplication(instrumentation.targetContext,
            GuideAuditEntryPoint::class.java).iptvRepository()
        val config = repository.observeConfig().first()
        val start = SystemClock.elapsedRealtime()
        repository.warmupFromCacheOnly()
        val message = buildString {
            append("cache_ms=${SystemClock.elapsedRealtime() - start} channels=${repository.pagedChannelCount(null)}\n")
            config.playlists.forEach { playlist ->
                val host = runCatching { URI(playlist.m3uUrl).host }.getOrNull()
                append("playlist=${playlist.name} host=$host enabled=${playlist.enabled} id=${playlist.id}\n")
            }
            append("favorites=${repository.observeFavoriteChannels().first().size} hidden=${repository.observeHiddenGroups().first().size}\n")
            repository.pagedPlaylistGroupCounts().filter { it.second.contains("NL", true) }.take(12).forEach {
                append("group=$it\n")
            }
        }
        instrumentation.sendStatus(0, Bundle().apply { putString("stream", message) })
    }
}

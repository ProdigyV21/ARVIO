package com.arflix.tv.ui.screens.tv.live

import android.os.Bundle
import android.os.SystemClock
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.di.GuideAuditEntryPoint
import com.arflix.tv.di.RepositoryAccessEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Test

/** Opt-in live-provider audit, isolated from both production and other emulator accounts. */
class GuideProviderAuditDeviceTest {
    @Test fun installExplicitSportsAddon() = runBlocking {
        val url = InstrumentationRegistry.getArguments().getString("sportsAddon") ?: return@runBlocking
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        check(context.packageName.endsWith(".overhaul"))
        require(url.startsWith("https://"))
        val access = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java)
        val result = access.streamRepository().addCustomAddon(url)
        assertTrue("Explicit test addon must install", result.isSuccess)
        report("Sports addon installed in isolated test app")
    }

    @Test fun sportsGuideUsesRealCachedProviderPrograms() = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("cachedStartup") == "true")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        check(context.packageName.endsWith(".overhaul"))
        val repository = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).iptvRepository()
        repository.warmupFromCacheOnly()
        val now = System.currentTimeMillis()
        val start = SystemClock.elapsedRealtime()
        val ids = repository.cachedGuideChannelIds(now, now + 48 * 60 * 60_000L)
        val lookupMs = SystemClock.elapsedRealtime() - start
        val events = SportsEventIndex()
        val resolver = SportsProgrammeResolver()
        var databaseMs = 0L
        var matchingMs = 0L
        var loadedProgrammes = 0
        for (batchIds in ids.toList().chunked(128)) {
            val dbStart = SystemClock.elapsedRealtime()
            val channels = repository.pagedChannelsByIds(batchIds).filter { !it.enrichForFastStartup(0).isAdult }
            val guide = repository.indexedGuideWindow(channels.map { it.id }.toSet(), now, now + 48 * 60 * 60_000L)
            databaseMs += SystemClock.elapsedRealtime() - dbStart
            loadedProgrammes += guide.values.sumOf { it.upcoming.size + if (it.now != null) 1 else 0 }
            val matchStart = SystemClock.elapsedRealtime()
            accumulateSportsGuideEvents(channels, guide, now, events, resolver = resolver)
            matchingMs += SystemClock.elapsedRealtime() - matchStart
        }
        val result = events.events()
        assertTrue("The real guide must contain sport events", result.isNotEmpty())
        assertTrue(result.all { it.channels.all { c -> c.streamUrl.startsWith("http") } })
        val allowed = result.flatMap { it.channels }.take(1)
        val sampledIds = (allowed.map { it.id } + ids.take(2)).toSet()
        val restricted = buildSportsGuideEvents(allowed, repository.indexedGuideWindow(sampledIds, now, now + 48 * 60 * 60_000), now)
        assertTrue(restricted.all { event -> event.channels.all { channel -> allowed.any { it.id == channel.id } } })
        report("real_sports_scan_ms=${SystemClock.elapsedRealtime() - start} lookup_ms=$lookupMs database_ms=$databaseMs matching_ms=$matchingMs cached_ids=${ids.size} read_programmes=$loadedProgrammes events=${result.size} on_air=${result.count { it.isOnAir(now) }} provider_artwork=${result.count { it.artwork != null }}")
        val artwork = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).sportsRepository().loadGuideArtwork()
        val illustrated = attachSportsArtwork(result, artwork)
        report("installed_addon_artwork=${artwork.size} matched_event_artwork=${illustrated.count { it.artwork != null }}")
    }

    @Test fun loadProvidedProviderAndKeepItsIndexAcrossCloudApplies() = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val url = InstrumentationRegistry.getArguments().getString("playlistUrl")
        assumeTrue("Explicit audit URL required", !url.isNullOrBlank())
        check(context.packageName.endsWith(".iptvaudit") || context.packageName.endsWith(".overhaul"))
        val access = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java)
        val profiles = access.profileRepository()
        val profile = profiles.getProfiles().firstOrNull { it.name == "IPTV Provider Audit" }
            ?: profiles.createProfile("IPTV Provider Audit", 0xFF447777)
        profiles.setActiveProfile(profile.id)
        access.profileManager().setCurrentProfileId(profile.id)
        val repository = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).iptvRepository()
        repository.savePlaylists(listOf(IptvPlaylistEntry("provider-audit", "Provider audit", url!!,
            importVod = false, importSeries = false)))
        val start = SystemClock.elapsedRealtime()
        var firstChannelsAt = 0L
        val snapshot = withTimeout(180_000) {
            repository.loadSnapshot(forcePlaylistReload = true, allowNetworkEpgFetch = false,
                onChannelsReady = { channels ->
                    if (channels.isNotEmpty() && firstChannelsAt == 0L) firstChannelsAt = SystemClock.elapsedRealtime() - start
                })
        }
        val count = repository.pagedChannelCount(null)
        val groups = repository.pagedPlaylistGroupCounts()
        report("provider_load_ms=${SystemClock.elapsedRealtime() - start} first_channels_ms=$firstChannelsAt channels=$count groups=${groups.size} memory_rows=${snapshot.channels.size}")
        val minimum = InstrumentationRegistry.getArguments().getString("minimumChannels")?.toIntOrNull() ?: 50_000
        assertTrue("Expected channels from the supplied provider", count >= minimum)
        assertEquals(count, groups.sumOf { it.third })
        listOf(groups.first(), groups[groups.size / 2], groups.last()).forEach { group ->
            val page = repository.pagedChannelWindow(group.first, group.second, 0, 144)
            assertEquals(minOf(144, group.third), page.size)
        }
        val state = repository.exportCloudConfigForProfile(profile.id)
        repeat(10) { repository.importCloudConfigForProfile(profile.id, state) }
        assertEquals(count, repository.pagedChannelCount(null))
        val selectedGroup = groups.firstOrNull { it.second.contains("NETHERLAND", true) }
            ?: groups.firstOrNull { Regex("\\bNL\\b", RegexOption.IGNORE_CASE).containsMatchIn(it.second) }
            ?: groups.first()
        val selected = repository.pagedChannelWindow(selectedGroup.first, selectedGroup.second, 0, 144)
            .filter { !it.epgId.isNullOrBlank() }.take(2)
        repository.importCloudConfigForProfile(profile.id, state.copy(favoriteChannels = selected.map { it.id }))
        assertEquals(count, repository.pagedChannelCount(null))
        report("unchanged_sync_applies=10 preference_only_apply=1 retained_channels=$count")
        val epgAt = SystemClock.elapsedRealtime()
        val guide = withTimeout(60_000) {
            repository.refreshEpgForChannels(selected.map { it.id }.toSet(), maxChannels = 2)
        }.orEmpty()
        report("focused_epg_ms=${SystemClock.elapsedRealtime() - epgAt} requested=${selected.size} matched=${guide.size} group=${selectedGroup.second}")
        if (guide.isEmpty() || InstrumentationRegistry.getArguments().getString("fullXml") == "true") {
            val fullStart = SystemClock.elapsedRealtime()
            withTimeout(600_000) {
                repository.loadSnapshot(forceEpgReload = true, allowNetworkEpgFetch = true, allowBroadShortEpg = false)
            }
            val indexed = repository.reDeriveCachedNowNext(selected.map { it.id }.toSet()).orEmpty()
            report("full_xml_ms=${SystemClock.elapsedRealtime() - fullStart} indexed_channels=${repository.indexedGuideChannelCount()} indexed_programs=${repository.indexedGuideProgramCount()} requested_matches=${indexed.size}")
            assertTrue("Dutch guide must load through XMLTV when the short API is empty", indexed.isNotEmpty())
            assertTrue("Only a completed full pass may mark the full guide fresh", repository.completedFullGuideAgeMs() < 60_000)
        }
    }

    @Test fun reopenProviderCacheWithoutDownloadingTheListsAgain() = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("cachedStartup") == "true")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        check(context.packageName.endsWith(".iptvaudit") || context.packageName.endsWith(".overhaul"))
        val repository = EntryPointAccessors.fromApplication(context, GuideAuditEntryPoint::class.java).iptvRepository()
        val start = SystemClock.elapsedRealtime()
        repository.warmupFromCacheOnly()
        val count = repository.pagedChannelCount(null)
        val groups = repository.pagedPlaylistGroupCounts()
        val channelAt = SystemClock.elapsedRealtime() - start
        val selectedGroup = groups.firstOrNull { it.second.contains("NETHERLAND", true) } ?: groups.first()
        val selected = repository.pagedChannelWindow(selectedGroup.first, selectedGroup.second, 0, 144)
            .filter { !it.epgId.isNullOrBlank() }.take(2)
        val guide = repository.reDeriveCachedNowNext(selected.map { it.id }.toSet()).orEmpty()
        report("cached_channels_ms=$channelAt cached_channels_and_epg_ms=${SystemClock.elapsedRealtime() - start} channels=$count groups=${groups.size} matched=${guide.size}")
        val minimum = InstrumentationRegistry.getArguments().getString("minimumChannels")?.toIntOrNull() ?: 50_000
        assertTrue(count >= minimum)
        assertEquals(2, guide.size)
        assertTrue("Cached startup must not repeat the lengthy import", SystemClock.elapsedRealtime() - start < 5_000)
    }

    private fun report(message: String) {
        InstrumentationRegistry.getInstrumentation().sendStatus(0, Bundle().apply { putString("stream", "$message\n") })
    }
}

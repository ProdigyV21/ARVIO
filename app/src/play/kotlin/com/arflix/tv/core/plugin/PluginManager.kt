package com.arflix.tv.core.plugin

import com.arflix.tv.domain.model.LocalScraperResult
import com.arflix.tv.domain.model.PluginRepository
import com.arflix.tv.domain.model.RemotePluginInfo
import com.arflix.tv.domain.model.ScraperInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PluginManager @Inject constructor() {

    val repositories: Flow<List<PluginRepository>> = flowOf(emptyList())
    val scrapers: Flow<List<ScraperInfo>> = flowOf(emptyList())
    val pluginsEnabled: Flow<Boolean> = flowOf(false)
    val groupStreamsByRepository: Flow<Boolean> = flowOf(false)
    var isSyncingFromRemote = false

    fun flushPendingSync() {}

    val enabledScrapers: Flow<List<ScraperInfo>> = flowOf(emptyList())

    suspend fun addRepository(manifestUrl: String): Result<PluginRepository> {
        return Result.failure(Exception("Plugins are not supported in this version"))
    }

    suspend fun removeRepository(repoId: String) {}

    suspend fun reconcileWithRemoteRepoUrls(
        remotePlugins: List<RemotePluginInfo>,
        removeMissingLocal: Boolean = true
    ) {}

    @JvmName("reconcileWithRemoteRepoUrlStrings")
    suspend fun reconcileWithRemoteRepoUrls(
        remoteUrls: List<String>,
        removeMissingLocal: Boolean = true
    ) {}

    suspend fun refreshRepository(repoId: String): Result<Unit> {
        return Result.failure(Exception("Plugins are not supported in this version"))
    }

    suspend fun toggleScraper(scraperId: String, enabled: Boolean) {}

    suspend fun toggleAllScrapersForRepo(repoId: String, enabled: Boolean) {}

    suspend fun setPluginsEnabled(enabled: Boolean) {}

    suspend fun setGroupStreamsByRepository(enabled: Boolean) {}

    suspend fun executeScrapers(
        tmdbId: String,
        mediaType: String,
        season: Int? = null,
        episode: Int? = null
    ): List<LocalScraperResult> = emptyList()

    fun executeScrapersStreaming(
        tmdbId: String,
        mediaType: String,
        season: Int? = null,
        episode: Int? = null
    ): Flow<Pair<ScraperInfo, List<LocalScraperResult>?>> = emptyFlow()

    suspend fun executeScraper(
        scraper: ScraperInfo,
        tmdbId: String,
        mediaType: String,
        season: Int?,
        episode: Int?
    ): List<LocalScraperResult> = emptyList()

    suspend fun testScraper(scraperId: String): Result<Pair<List<LocalScraperResult>, TestDiagnostics>> {
        return Result.failure(Exception("Plugins are not supported in this version"))
    }
}

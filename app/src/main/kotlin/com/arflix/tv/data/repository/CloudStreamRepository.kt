package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.arflix.tv.data.api.CloudStreamApi
import com.arflix.tv.data.model.CloudStreamExtension
import com.arflix.tv.data.model.CloudStreamExtensionManifest
import com.arflix.tv.data.model.CloudStreamPluginEntry
import com.arflix.tv.data.model.CloudStreamSearchResult
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.cloudStreamDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudStreamRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cloudStreamApi: CloudStreamApi
) {
    private val gson = Gson()

    private val repoUrlsKey = stringSetPreferencesKey("cloudstream_repo_urls")

    private fun manifestKey(url: String) = stringPreferencesKey("cs_manifest_$url")
    private fun pluginsKey(url: String) = stringPreferencesKey("cs_plugins_$url")

    val extensions: Flow<List<CloudStreamExtension>> = context.cloudStreamDataStore.data.map { prefs ->
        val urls = prefs[repoUrlsKey] ?: emptySet()
        urls.mapNotNull { url ->
            val manifestJson = prefs[manifestKey(url)] ?: return@mapNotNull null
            val pluginsJson = prefs[pluginsKey(url)] ?: return@mapNotNull null
            val manifest = runCatching {
                gson.fromJson(manifestJson, CloudStreamExtensionManifest::class.java)
            }.getOrNull() ?: return@mapNotNull null
            val pluginListType = object : TypeToken<List<CloudStreamPluginEntry>>() {}.type
            val plugins = runCatching {
                gson.fromJson<List<CloudStreamPluginEntry>>(pluginsJson, pluginListType)
            }.getOrNull() ?: emptyList()
            CloudStreamExtension(
                repositoryUrl = url,
                manifest = manifest,
                plugins = plugins
            )
        }
    }

    suspend fun addExtensionRepository(url: String): Result<CloudStreamExtension> {
        return runCatching {
            val manifest = cloudStreamApi.getRepositoryManifest(url)
            val allPlugins = mutableListOf<CloudStreamPluginEntry>()
            manifest.pluginLists.forEach { listUrl ->
                runCatching {
                    val pluginList = cloudStreamApi.getPluginList(listUrl)
                    allPlugins += pluginList.plugins + pluginList.allPlugins
                }
            }
            val distinctPlugins = allPlugins.distinctBy { it.url.ifBlank { it.name } }
            val manifestJson = gson.toJson(manifest)
            val pluginsJson = gson.toJson(distinctPlugins)
            context.cloudStreamDataStore.edit { prefs ->
                val existing = prefs[repoUrlsKey] ?: emptySet()
                prefs[repoUrlsKey] = existing + url
                prefs[manifestKey(url)] = manifestJson
                prefs[pluginsKey(url)] = pluginsJson
            }
            CloudStreamExtension(
                repositoryUrl = url,
                manifest = manifest,
                plugins = distinctPlugins
            )
        }
    }

    suspend fun removeExtensionRepository(url: String) {
        context.cloudStreamDataStore.edit { prefs ->
            val existing = prefs[repoUrlsKey] ?: emptySet()
            prefs[repoUrlsKey] = existing - url
            prefs.remove(manifestKey(url))
            prefs.remove(pluginsKey(url))
        }
    }

    suspend fun getAvailablePlugins(): List<CloudStreamPluginEntry> {
        val prefs: Preferences = context.cloudStreamDataStore.data.first()
        val urls = prefs[repoUrlsKey] ?: emptySet()
        val pluginListType = object : TypeToken<List<CloudStreamPluginEntry>>() {}.type
        return urls.flatMap { url ->
            val json = prefs[pluginsKey(url)] ?: return@flatMap emptyList()
            runCatching {
                gson.fromJson<List<CloudStreamPluginEntry>>(json, pluginListType)
            }.getOrElse { emptyList() }
        }.distinctBy { it.url.ifBlank { it.name } }
    }

    suspend fun searchAll(query: String): List<MediaItem> = coroutineScope {
        val plugins = getAvailablePlugins()
        if (plugins.isEmpty()) return@coroutineScope emptyList()
        val jobs = plugins.map { plugin ->
            async {
                withTimeoutOrNull(8_000L) {
                    runCatching { searchPlugin(plugin, query) }.getOrElse { emptyList() }
                } ?: emptyList()
            }
        }
        jobs.awaitAll().flatten()
    }

    private suspend fun searchPlugin(plugin: CloudStreamPluginEntry, query: String): List<MediaItem> {
        if (plugin.url.isBlank()) return emptyList()
        val baseUrl = plugin.url.removeSuffix("/")
        val searchUrl = "$baseUrl/search"
        val response = runCatching {
            cloudStreamApi.search(searchUrl, query)
        }.getOrNull() ?: return emptyList()
        return response.results.map { it.toMediaItem(plugin.name) }
    }

    private fun CloudStreamSearchResult.toMediaItem(sourceName: String): MediaItem {
        val isTv = type?.lowercase()?.let {
            it.contains("tvseries") || it.contains("tv_series") || it.contains("series") ||
                it.contains("anime") || it.contains("torrent")
        } ?: false
        val mediaType = if (isTv) MediaType.TV else MediaType.MOVIE
        return MediaItem(
            id = (url + name).hashCode(),
            title = name,
            subtitle = if (mediaType == MediaType.TV) "TV Series" else "Movie",
            year = year?.toString() ?: "",
            imdbRating = rating?.let { String.format("%.1f", it) } ?: "",
            tmdbRating = rating?.let { String.format("%.1f", it) } ?: "",
            mediaType = mediaType,
            image = posterUrl ?: "",
            badge = quality
        )
    }
}

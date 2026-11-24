package com.arflix.tv.music

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/** Kept apart from the synced settings: the server login belongs to this device only. */
private val Context.musicDataStore: DataStore<Preferences> by preferencesDataStore(name = "music_server")

/** The sources the Music tab plays from. */
data class MusicLibrary(
    val server: MusicServerConfig? = null,
    val addons: List<MusicAddon> = emptyList(),
) {
    val isEmpty: Boolean get() = server == null && addons.isEmpty()
}

/**
 * The user's music sources (their own server and music addons): saving them and handing out
 * one client for all of them.
 */
@Singleton
class MusicRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val configKey = stringPreferencesKey("server_v1")
    private val deviceIdKey = stringPreferencesKey("device_id")
    private val gson = Gson()

    /**
     * Its own client: a home server is often a LAN address or a .local name, which the app's
     * shared client (with DNS over HTTPS) cannot resolve, and its URLs carry the login token.
     */
    val http: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val addonsKey = stringPreferencesKey("addons_v1")

    val config: Flow<MusicServerConfig?> = context.musicDataStore.data
        .map { prefs -> prefs[configKey]?.let { runCatching { gson.fromJson(it, MusicServerConfig::class.java) }.getOrNull() } }
        .distinctUntilChanged()

    val addons: Flow<List<MusicAddon>> = context.musicDataStore.data
        .map { prefs -> decodeAddons(prefs[addonsKey]) }
        .distinctUntilChanged()

    /** Everything the Music tab plays from: the user's server and their music addons. */
    val library: Flow<MusicLibrary> = combine(config, addons) { server, addons -> MusicLibrary(server, addons) }
        .distinctUntilChanged()

    private fun decodeAddons(json: String?): List<MusicAddon> = runCatching {
        gson.fromJson(json ?: "[]", Array<MusicAddon>::class.java)?.toList().orEmpty()
            .filter { !it.baseUrl.isNullOrBlank() }
    }.getOrDefault(emptyList())

    private var cached: Pair<MusicLibrary, MusicSource>? = null

    fun sourceFor(library: MusicLibrary): MusicSource? {
        if (library.isEmpty) return null
        cached?.let { (saved, source) -> if (saved == library) return source }
        val keys = mutableListOf<String>()
        val sources = buildList<MusicSource> {
            library.server?.let { server ->
                keys += SERVER_KEY
                add(
                    when (server.type) {
                        MusicServerType.SUBSONIC -> SubsonicSource(http, server)
                        MusicServerType.JELLYFIN -> JellyfinMusicSource(http, server)
                    }
                )
            }
            library.addons.forEach { addon ->
                keys += addon.id.ifBlank { addon.baseUrl }
                add(
                    if (addon.protocol == EclipseAddonSource.PROTOCOL) EclipseAddonSource(http, addon)
                    else MusicAddonSource(http, addon)
                )
            }
        }
        val source = CompositeMusicSource(sources, keys)
        cached = library to source
        return source
    }

    suspend fun currentSource(): MusicSource? = sourceFor(library.first())

    private val collectionsKey = stringPreferencesKey("collections_v1")

    /** Liked songs, favourite albums and the user's playlists, kept on this device. */
    val collections: Flow<MusicCollections> = context.musicDataStore.data
        .map { prefs -> decodeCollections(prefs[collectionsKey]) }
        .distinctUntilChanged()

    private fun decodeCollections(json: String?): MusicCollections = json?.let {
        runCatching { gson.fromJson(it, MusicCollections::class.java)?.sanitized() }.getOrNull()
    } ?: MusicCollections()

    suspend fun updateCollections(transform: (MusicCollections) -> MusicCollections) {
        context.musicDataStore.edit { prefs ->
            prefs[collectionsKey] = gson.toJson(transform(decodeCollections(prefs[collectionsKey])))
        }
    }

    /** Reads the addon's manifest and adds it; adding the same addon again refreshes it. */
    suspend fun addAddon(url: String): MusicAddon {
        val addon = MusicAddonSource.install(http, url)
        context.musicDataStore.edit { prefs ->
            val current = decodeAddons(prefs[addonsKey]).filter { it.baseUrl != addon.baseUrl && it.id != addon.id }
            prefs[addonsKey] = gson.toJson(current + addon)
        }
        return addon
    }

    suspend fun removeAddon(baseUrl: String) {
        context.musicDataStore.edit { prefs ->
            prefs[addonsKey] = gson.toJson(decodeAddons(prefs[addonsKey]).filter { it.baseUrl != baseUrl })
        }
    }

    /** Checks the login against the server and saves it only when it works. */
    suspend fun connect(type: MusicServerType, url: String, username: String, password: String): MusicServerConfig {
        val baseUrl = normalizeServerUrl(url)
        if (baseUrl.isEmpty()) throw MusicServerException("Enter the server address")
        val config = when (type) {
            MusicServerType.SUBSONIC -> {
                val (token, salt) = SubsonicSource.credentials(password)
                MusicServerConfig(type, baseUrl, username.trim(), token, salt).also {
                    SubsonicSource(http, it).ping()
                }
            }
            MusicServerType.JELLYFIN -> {
                val deviceId = deviceId()
                val (token, userId) = JellyfinMusicSource.signIn(http, baseUrl, username.trim(), password, deviceId)
                MusicServerConfig(type, baseUrl, username.trim(), token, userId = userId, deviceId = deviceId)
            }
        }
        context.musicDataStore.edit { it[configKey] = gson.toJson(config) }
        return config
    }

    suspend fun disconnect() {
        context.musicDataStore.edit { it.remove(configKey) }
    }

    companion object {
        const val SERVER_KEY = "server"
    }

    private suspend fun deviceId(): String {
        context.musicDataStore.data.first()[deviceIdKey]?.let { return it }
        val id = "arvio-" + UUID.randomUUID().toString()
        context.musicDataStore.edit { it[deviceIdKey] = id }
        return id
    }
}

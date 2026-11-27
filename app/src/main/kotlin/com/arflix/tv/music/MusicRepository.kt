package com.arflix.tv.music

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arflix.tv.data.repository.CloudSyncInvalidationBus
import com.arflix.tv.data.repository.CloudSyncScope
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.resolveAppLanguage
import com.arflix.tv.util.settingsDataStore
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

/**
 * The music the user keeps per profile, in the synced settings so the cloud sync carries it to
 * their other devices: music addons, liked songs, favourite albums and playlists.
 */
object MusicCloudKeys {
    fun addons(profileId: String) = stringPreferencesKey("profile_${profileId}_music_addons_v1")
    fun collections(profileId: String) = stringPreferencesKey("profile_${profileId}_music_collections_v1")
}

/** The sources the Music tab plays from. */
data class MusicLibrary(
    val server: MusicServerConfig? = null,
    val addons: List<MusicAddon> = emptyList(),
    /** The app language, which picks the country charts on the Music home. */
    val language: String = "en-US",
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
    private val profileManager: ProfileManager,
    private val invalidationBus: CloudSyncInvalidationBus,
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

    private val language: Flow<String> = combine(profileManager.currentProfileId, context.settingsDataStore.data) { profileId, settings ->
        resolveAppLanguage(settings, profileId)
    }.distinctUntilChanged()

    // Before music synced, addons and collections lived on the device only; a profile without its
    // own synced copy yet still sees those until its first change saves one.
    val addons: Flow<List<MusicAddon>> = combine(
        profileManager.currentProfileId, context.settingsDataStore.data, context.musicDataStore.data,
    ) { profileId, settings, device -> decodeAddons(settings[MusicCloudKeys.addons(profileId)] ?: device[addonsKey]) }
        .distinctUntilChanged()

    /** Everything the Music tab plays from: the user's server and their music addons. */
    val library: Flow<MusicLibrary> = combine(config, addons, language) { server, addons, language -> MusicLibrary(server, addons, language) }
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
        val sources = mutableListOf<MusicSource>().apply {
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
        // The charts come last, so the user's own sources keep their place in ids and search.
        keys += ChartsSource.KEY
        sources += ChartsSource(http, library.language, context.cacheDir)
        val source = CompositeMusicSource(sources, keys)
        cached = library to source
        return source
    }

    suspend fun currentSource(): MusicSource? = sourceFor(library.first())

    /** Latin artist names for a query typed in another script; see [MusicNameLookup]. */
    suspend fun latinNames(query: String): List<String> = MusicNameLookup.latinNames(http, query)

    private val collectionsKey = stringPreferencesKey("collections_v1")

    /** Liked songs, favourite albums and the user's playlists, per profile and synced. */
    val collections: Flow<MusicCollections> = combine(
        profileManager.currentProfileId, context.settingsDataStore.data, context.musicDataStore.data,
    ) { profileId, settings, device -> decodeCollections(settings[MusicCloudKeys.collections(profileId)] ?: device[collectionsKey]) }
        .distinctUntilChanged()

    private fun decodeCollections(json: String?): MusicCollections = json?.let {
        runCatching { gson.fromJson(it, MusicCollections::class.java)?.sanitized() }.getOrNull()
    } ?: MusicCollections()

    /** A file the user picked: its name (which tells the format) and its bytes. */
    fun readDocument(uri: android.net.Uri): Pair<String, ByteArray> {
        val resolver = context.contentResolver
        val name = resolver.query(uri, arrayOf(android.provider.OpenableColumns.DISPLAY_NAME), null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) cursor.getString(0) else null
        } ?: uri.lastPathSegment.orEmpty()
        val bytes = resolver.openInputStream(uri)?.use { it.readBytes() } ?: throw MusicServerException("Could not read $name")
        return name to bytes
    }

    suspend fun updateCollections(transform: (MusicCollections) -> MusicCollections) {
        val profileId = profileManager.getProfileIdSync()
        val device = context.musicDataStore.data.first()[collectionsKey]
        context.settingsDataStore.edit { prefs ->
            val key = MusicCloudKeys.collections(profileId)
            prefs[key] = gson.toJson(transform(decodeCollections(prefs[key] ?: device)))
        }
        invalidationBus.markUserDirty(CloudSyncScope.PROFILE_SETTINGS, profileId, "music collections")
    }

    /** Reads the addon's manifest and adds it; adding the same addon again refreshes it. */
    suspend fun addAddon(url: String): MusicAddon {
        val addon = MusicAddonSource.install(http, url)
        editAddons { current -> current.filter { it.baseUrl != addon.baseUrl && it.id != addon.id } + addon }
        return addon
    }

    suspend fun removeAddon(baseUrl: String) {
        editAddons { current -> current.filter { it.baseUrl != baseUrl } }
    }

    private suspend fun editAddons(transform: (List<MusicAddon>) -> List<MusicAddon>) {
        val profileId = profileManager.getProfileIdSync()
        val device = context.musicDataStore.data.first()[addonsKey]
        context.settingsDataStore.edit { prefs ->
            val key = MusicCloudKeys.addons(profileId)
            prefs[key] = gson.toJson(transform(decodeAddons(prefs[key] ?: device)))
        }
        invalidationBus.markUserDirty(CloudSyncScope.PROFILE_SETTINGS, profileId, "music addons")
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

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
 * The user's own music server: saving the connection and handing out a client for it.
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

    val config: Flow<MusicServerConfig?> = context.musicDataStore.data
        .map { prefs -> prefs[configKey]?.let { runCatching { gson.fromJson(it, MusicServerConfig::class.java) }.getOrNull() } }
        .distinctUntilChanged()

    private var cached: Pair<MusicServerConfig, MusicSource>? = null

    fun sourceFor(config: MusicServerConfig): MusicSource {
        cached?.let { (saved, source) -> if (saved == config) return source }
        val source = when (config.type) {
            MusicServerType.SUBSONIC -> SubsonicSource(http, config)
            MusicServerType.JELLYFIN -> JellyfinMusicSource(http, config)
        }
        cached = config to source
        return source
    }

    suspend fun currentSource(): MusicSource? = config.first()?.let { sourceFor(it) }

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
        cached = null
    }

    private suspend fun deviceId(): String {
        context.musicDataStore.data.first()[deviceIdKey]?.let { return it }
        val id = "arvio-" + UUID.randomUUID().toString()
        context.musicDataStore.edit { it[deviceIdKey] = id }
        return id
    }
}

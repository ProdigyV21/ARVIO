package com.arflix.tv.data.repository.simkl

import android.content.Context
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.CatalogDiscoveryResult
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.Constants
import com.arflix.tv.util.SecureStorage
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/** A device-local V2 grant shared by tracking and custom lists. Never copy rotating refresh tokens via Cloud. */
@Singleton
class SimklListsRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val profileManager: ProfileManager
) {
    private val prefs = context.getSharedPreferences("simkl_lists_v2", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val mutex = Mutex()
    private val http = OkHttpClient.Builder().callTimeout(20, TimeUnit.SECONDS).addInterceptor(com.arflix.tv.network.sharedSimklRateLimiter).build()
    private val client get() = SimklListsClient(http, Constants.SIMKL_V2_CLIENT_ID, version = BuildConfig.VERSION_NAME.substringBefore('-'))
    private val cache = mutableMapOf<String, Pair<Long, SimklCustomList>>()
    private val indexCache = mutableMapOf<String, Pair<Long, List<SimklCustomList>>>()

    private fun key(profile: String) = "grant_$profile"
    fun profileId(): String = profileManager.getProfileIdSync()
    private fun read(profile: String): SimklListsGrant? = runCatching {
        gson.fromJson(SecureStorage.decrypt(prefs.getString(key(profile), null), "arvio_simkl_lists_v2"), SimklListsGrant::class.java)
    }.getOrNull()?.takeIf { !it.accessToken.isNullOrBlank() && !it.refreshToken.isNullOrBlank() && !it.connectionId.isNullOrBlank() }

    private fun save(profile: String, grant: SimklListsGrant) {
        check(prefs.edit().putString(key(profile), SecureStorage.encrypt(gson.toJson(grant), "arvio_simkl_lists_v2")).commit())
    }

    fun isConnected(profile: String = profileManager.getProfileIdSync()): Boolean = read(profile) != null
    suspend fun accessToken(): String = mutex.withLock { grant(profileManager.getProfileIdSync()).accessToken }
    fun connectionId(): String? = read(profileManager.getProfileIdSync())?.connectionId
    fun ownsAccessToken(token: String): Boolean = read(profileManager.getProfileIdSync())?.accessToken == token
    suspend fun refreshRejectedToken(rejected: String, profile: String = profileId(), connection: String? = connectionId()): String = mutex.withLock {
        if (profile != profileId()) throw SimklListsException("user_token_required")
        val saved = read(profile) ?: throw SimklListsException("user_token_required")
        if (saved.connectionId != connection) throw SimklListsException("user_token_required")
        if (saved.accessToken != rejected) return@withLock saved.accessToken
        client.refresh(saved.refreshToken).copy(userId = saved.userId, connectionId = saved.connectionId).also { save(profile, it) }.accessToken
    }
    suspend fun startDevice(): SimklListsDeviceCode = client.startDevice()

    suspend fun poll(session: SimklListsDeviceCode, profile: String): Boolean {
        val grant = client.poll(session)
        mutex.withLock {
            if (profile != profileId()) throw kotlinx.coroutines.CancellationException("SIMKL profile changed")
            save(profile, grant)
            indexCache.remove(profile)
            cache.keys.removeAll { it.startsWith("$profile:") }
        }
        return true
    }

    suspend fun disconnect() = mutex.withLock {
        val profile = profileManager.getProfileIdSync()
        check(prefs.edit().remove(key(profile)).commit())
        indexCache.remove(profile)
        cache.keys.removeAll { it.startsWith("$profile:") }
    }

    private suspend fun grant(profile: String): SimklListsGrant {
        val saved = read(profile) ?: throw SimklListsException("user_token_required")
        if (saved.expiresAt - System.currentTimeMillis() > 60_000) return saved
        return client.refresh(saved.refreshToken).copy(userId = saved.userId, connectionId = saved.connectionId).also { save(profile, it) }
    }

    suspend fun load(id: Long, force: Boolean = false): SimklCustomList = mutex.withLock {
        val profile = profileManager.getProfileIdSync()
        val grant = grant(profile)
        val key = "$profile:$id"
        val cached = cache[key]
        val ttl = if (cached?.second?.auto == true) 86_400_000 else 15 * 60_000
        if (!force && cached != null && System.currentTimeMillis() - cached.first < ttl) return@withLock cached.second
        // An early revoked/expired access token is refreshed once, never looped.
        val list = try { client.list(id, grant.accessToken) } catch (e: SimklListsException) {
            if (e.status != 401) throw e
            val refreshed = client.refresh(grant.refreshToken).copy(userId = grant.userId, connectionId = grant.connectionId)
            save(profile, refreshed)
            client.list(id, refreshed.accessToken)
        }
        cache[key] = System.currentTimeMillis() to list
        list
    }

    suspend fun search(query: String): List<CatalogDiscoveryResult> = mutex.withLock {
        val profile = profileManager.getProfileIdSync()
        if (read(profile) == null) return@withLock emptyList()
        var grant = grant(profile)
        val cached = indexCache[profile]
        val lists = if (cached != null && System.currentTimeMillis() - cached.first < 15 * 60_000) cached.second else {
            suspend fun <T> readWithRefresh(block: suspend (String) -> T): T = try { block(grant.accessToken) } catch (e: SimklListsException) {
                if (e.status != 401) throw e
                grant = client.refresh(grant.refreshToken).copy(userId = grant.userId, connectionId = grant.connectionId)
                save(profile, grant)
                block(grant.accessToken)
            }
            val userId = grant.userId ?: readWithRefresh { client.userId(it) }.also {
                grant = grant.copy(userId = it)
                save(profile, grant)
            }
            (readWithRefresh { client.userLists(userId, it) } + if (userId != 5L) readWithRefresh { client.officialLists(it) } else emptyList()).distinctBy { it.id }.also { fresh ->
                // A changed updated_at invalidates regular lists, while auto lists refresh daily.
                fresh.forEach { entry ->
                    val key = "$profile:${entry.id}"
                    if (!entry.auto && cache[key]?.second?.updatedAt != entry.updatedAt) cache.remove(key)
                }
                indexCache[profile] = System.currentTimeMillis() to fresh
            }
        }
        lists.filter { it.name.contains(query, true) || it.description?.contains(query, true) == true }.map { list ->
            CatalogDiscoveryResult("simkl:${list.id}", list.name, list.description, CatalogSourceType.SIMKL,
                list.url, list.ownerName, list.ownerId?.toString(), list.updatedAt, list.count, list.likes, list.posters)
        }
    }
}

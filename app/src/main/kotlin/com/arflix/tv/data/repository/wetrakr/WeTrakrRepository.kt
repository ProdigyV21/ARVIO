package com.arflix.tv.data.repository.wetrakr

import android.content.Context
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.SecureStorage
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

internal const val WETRAKR_CLIENT_ID = "a96545eae15256f3302c72377acd3ade"
internal fun JsonObject.text(key: String): String? = get(key)?.takeUnless { it.isJsonNull }?.runCatching { asString }?.getOrNull()
internal fun JsonObject.obj(key: String): JsonObject = get(key)?.takeIf { it.isJsonObject }?.asJsonObject ?: JsonObject()
internal fun JsonObject.int(key: String): Int? = text(key)?.toIntOrNull()
internal fun JsonObject.tmdb(): Int? = obj("ids").int("tmdb")?.takeIf { it > 0 }

class WeTrakrError(val status: Int, val reason: String, val retrySeconds: Long = 5) : Exception(
    when {
        status == 429 -> "WeTrakr is limiting requests. Please try again later."
        reason == "expired_token" -> "The WeTrakr code expired. Request a new code."
        reason == "access_denied" -> "WeTrakr connection was declined."
        status == 401 -> "Please reconnect WeTrakr on this device."
        else -> "WeTrakr could not complete this request. Please try again."
    }
)

data class WeTrakrDeviceCode(val userCode: String, val verificationUrl: String, val expiresAt: Long, val interval: Long)
private data class Grant(val access: String, val refresh: String, val expiresAt: Long, val refreshExpiresAt: Long, val connection: String)
private data class Pending(val device: String, val code: WeTrakrDeviceCode, var nextPoll: Long, var interval: Long)

/** Rotating grants belong to this installation and profile, never to a Cloud export. */
@Singleton
class WeTrakrRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val profiles: ProfileManager
) {
    private val preferences = context.getSharedPreferences("wetrakr_device_sessions", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val mutex = Mutex()
    private val pending = mutableMapOf<String, Pending>()
    private val cache = mutableMapOf<String, Pair<Long, List<JsonObject>>>()
    private val http = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).callTimeout(25, TimeUnit.SECONDS).build()
    private var blockedUntil = 0L
    fun profileId(): String = profiles.getProfileIdSync()
    private fun read(profile: String): Grant? = runCatching {
        gson.fromJson(SecureStorage.decrypt(preferences.getString(profile, null), "arvio_wetrakr_grant"), Grant::class.java)
    }.getOrNull()
    private fun save(profile: String, grant: Grant?) {
        val edit = preferences.edit()
        if (grant == null) edit.remove(profile) else edit.putString(profile, SecureStorage.encrypt(gson.toJson(grant), "arvio_wetrakr_grant"))
        check(edit.commit()) { "Could not save WeTrakr connection" }
    }
    fun isConnected(profile: String = profileId()): Boolean = read(profile)?.refreshExpiresAt?.let { it > System.currentTimeMillis() } == true
    private suspend fun http(path: String, method: String, body: Any? = null, access: String? = null): Pair<JsonElement, Map<String, String>> = withContext(Dispatchers.IO) {
        if (System.currentTimeMillis() < blockedUntil) throw WeTrakrError(429, "rate_limited", (blockedUntil - System.currentTimeMillis()) / 1000 + 1)
        val builder = Request.Builder().url("https://api.wetrakr.com$path")
            .header("wetrakr-api-key", WETRAKR_CLIENT_ID).header("wetrakr-api-version", "1").header("User-Agent", "ARVIO/2.0 (Android)")
        access?.let { builder.header("Authorization", "Bearer $it") }
        builder.method(method, if (method == "GET") null else gson.toJson(body ?: emptyMap<String, String>()).toRequestBody("application/json".toMediaType()))
        http.newCall(builder.build()).execute().use { response ->
            val raw = response.body?.string().orEmpty()
            val data = runCatching { JsonParser.parseString(raw) }.getOrNull() ?: JsonObject()
            if (!response.isSuccessful) {
                val retry = response.header("Retry-After")?.toLongOrNull()?.coerceAtLeast(5) ?: 60
                if (response.code == 429) blockedUntil = System.currentTimeMillis() + retry * 1000
                throw WeTrakrError(response.code, if (data.isJsonObject) data.asJsonObject.text("error") ?: "provider_error" else "provider_error", retry)
            }
            data to response.headers.names().associate { it.lowercase() to response.header(it).orEmpty() }
        }
    }
    suspend fun start(profile: String = profileId()): WeTrakrDeviceCode = mutex.withLock {
        pending[profile]?.takeIf { it.code.expiresAt > System.currentTimeMillis() }?.let { return@withLock it.code }
        val data = http("/oauth/device/code", "POST", mapOf("client_id" to WETRAKR_CLIENT_ID)).first.asJsonObject
        val url = data.text("verification_url") ?: error("Missing verification URL")
        require(url == "https://wetrakr.com/activate")
        val interval = (data.int("interval") ?: 5).coerceAtLeast(5).toLong()
        val code = WeTrakrDeviceCode(data.text("user_code") ?: error("Missing code"), url,
            System.currentTimeMillis() + (data.int("expires_in") ?: 600).coerceIn(1, 1800) * 1000L, interval)
        pending[profile] = Pending(data.text("device_code") ?: error("Missing device code"), code, System.currentTimeMillis() + interval * 1000, interval)
        code
    }
    suspend fun cancel(profile: String = profileId()) = mutex.withLock { pending.remove(profile); Unit }
    suspend fun poll(profile: String = profileId()): Boolean = mutex.withLock {
        val flow = pending[profile] ?: throw WeTrakrError(410, "expired_token")
        if (flow.code.expiresAt <= System.currentTimeMillis()) { pending.remove(profile); throw WeTrakrError(410, "expired_token") }
        if (System.currentTimeMillis() < flow.nextPoll) return@withLock false
        flow.nextPoll = System.currentTimeMillis() + flow.interval * 1000
        try {
            val data = http("/oauth/device/token", "POST", mapOf("code" to flow.device, "client_id" to WETRAKR_CLIENT_ID)).first.asJsonObject
            save(profile, grant(data)); pending.remove(profile); cache.clear(); true
        } catch (e: WeTrakrError) {
            when {
                e.reason == "authorization_pending" -> false
                e.status == 429 -> { flow.interval += 5; flow.nextPoll = System.currentTimeMillis() + maxOf(flow.interval, e.retrySeconds) * 1000; false }
                else -> { if (e.status in listOf(400, 401, 404, 409, 410, 418, 423)) pending.remove(profile); throw e }
            }
        }
    }
    private fun grant(data: JsonObject, connection: String = UUID.randomUUID().toString()): Grant {
        val access = data.text("access_token")?.takeIf { it.length >= 20 } ?: error("Invalid access token")
        val refresh = (data.text("refresh_token") ?: data.text("new_refresh_token"))?.takeIf { it.length >= 20 } ?: error("Invalid refresh token")
        val expires = data.int("expires_in")?.takeIf { it > 0 } ?: error("Invalid token expiry")
        return Grant(access, refresh, System.currentTimeMillis() + expires * 1000L, System.currentTimeMillis() + 180L * 86400000, connection)
    }
    private suspend fun access(profile: String, connection: String, rejected: String? = null): String = mutex.withLock {
        val current = read(profile)?.takeIf { it.connection == connection && it.refreshExpiresAt > System.currentTimeMillis() } ?: throw WeTrakrError(401, "session_required")
        if (current.expiresAt > System.currentTimeMillis() + 60000 && current.access != rejected) return@withLock current.access
        try {
            val next = grant(http("/oauth/token/refresh", "POST", mapOf("refresh_token" to current.refresh)).first.asJsonObject, connection)
            save(profile, next); next.access
        } catch (e: WeTrakrError) {
            if (e.reason == "INVALID_REFRESH_TOKEN") save(profile, null)
            throw e
        }
    }
    suspend fun disconnect(profile: String = profileId()) = mutex.withLock {
        val grant = read(profile)
        save(profile, null); pending.remove(profile); cache.clear()
        if (grant != null) runCatching { http("/oauth/logout", "POST", mapOf("refresh_token" to grant.refresh), grant.access) }
        Unit
    }
    suspend fun request(path: String, method: String = "GET", body: Any? = null, profile: String = profileId()): Pair<JsonElement, Map<String, String>> {
        val connection = read(profile)?.connection ?: throw WeTrakrError(401, "session_required")
        val token = access(profile, connection)
        return try { http(path, method, body, token) } catch (e: WeTrakrError) {
            // A network/edge denial is not proof that a grant was revoked.
            if (e.status == 401 && e.reason != "provider_error") http(path, method, body, access(profile, connection, token)) else throw e
        }
    }
    suspend fun list(status: String, target: String, force: Boolean = false, profile: String = profileId()): List<JsonObject> {
        val connection = read(profile)?.connection ?: throw WeTrakrError(401, "session_required")
        val key = "$profile:$connection:$status:$target"
        mutex.withLock { cache[key]?.takeIf { !force && System.currentTimeMillis() - it.first < 300000 }?.let { return it.second } }
        val rows = mutableListOf<JsonObject>()
        var page = 1
        do {
            if (read(profile)?.connection != connection) throw WeTrakrError(409, "session_changed")
            val (data, headers) = request("/sync/tracking/$status/$target?limit=100&page=$page&extended=show_level_1,episode_level_1", profile = profile)
            check(data.isJsonArray) { "Invalid WeTrakr list" }
            rows += data.asJsonArray.map { it.asJsonObject }
            val count = headers["x-pagination-page-count"]?.toIntOrNull()
            if (count != null && page >= count || count == null && data.asJsonArray.size() < 100) break
            check(++page <= 10000) { "WeTrakr pagination did not finish" }
        } while (true)
        mutex.withLock {
            if (read(profile)?.connection != connection) throw WeTrakrError(409, "session_changed")
            cache[key] = System.currentTimeMillis() to rows
        }
        return rows
    }
    suspend fun invalidate() = mutex.withLock { cache.clear() }
}

package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.api.RefreshTokenRequest
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktToken
import com.arflix.tv.util.Constants
import com.arflix.tv.util.traktDataStore
import com.google.gson.JsonParser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

data class TraktConnection(val profileId: String, val connected: Boolean, val reconnectRequired: Boolean)

class TraktAuthUnavailable(val reconnectRequired: Boolean) : IllegalStateException(
    if (reconnectRequired) "Reconnect Trakt to continue syncing" else "Trakt is temporarily unavailable"
)

/** One refresh path for foreground requests and background sync; Trakt refresh tokens are single-use. */
@Singleton
class TraktSession @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profiles: ProfileManager,
    @Named("traktAuth") private val api: TraktApi
) {
    private val refreshMutex = Mutex()
    private val backoff = mutableMapOf<String, Pair<Credential, Long>>()
    private data class Credential(val access: String?, val refresh: String?, val expires: Long?, val updated: Long?)

    private fun accessKey(id: String) = profiles.profileStringKeyFor(id, "trakt_access_token")
    private fun refreshKey(id: String) = profiles.profileStringKeyFor(id, "trakt_refresh_token")
    private fun expiresKey(id: String) = profiles.profileLongKeyFor(id, "trakt_expires_at")
    private fun updatedKey(id: String) = profiles.profileLongKeyFor(id, "trakt_token_updated_at_v3")
    private fun reconnectKey(id: String) = profiles.profileBooleanKeyFor(id, "trakt_reconnect_required")
    fun currentProfileId(): String = profiles.getProfileIdSync().ifBlank { "default" }
    private fun credential(prefs: Preferences, id: String) = Credential(
        prefs[accessKey(id)], prefs[refreshKey(id)], prefs[expiresKey(id)], prefs[updatedKey(id)]
    )

    val connection = profiles.activeProfileId.combine(context.traktDataStore.data) { id, prefs ->
        TraktConnection(id, !prefs[accessKey(id)].isNullOrBlank(), prefs[reconnectKey(id)] == true)
    }.distinctUntilChanged()

    suspend fun save(token: TraktToken, profileId: String) {
        context.traktDataStore.edit { prefs ->
            prefs[accessKey(profileId)] = token.accessToken
            prefs[refreshKey(profileId)] = token.refreshToken
            prefs[expiresKey(profileId)] = token.createdAt + token.expiresIn
            prefs[updatedKey(profileId)] = System.currentTimeMillis()
            prefs.remove(reconnectKey(profileId))
        }
    }

    suspend fun disconnect(profileId: String = currentProfileId()) {
        context.traktDataStore.edit { prefs ->
            prefs.remove(accessKey(profileId))
            prefs.remove(refreshKey(profileId))
            prefs.remove(expiresKey(profileId))
            prefs.remove(reconnectKey(profileId))
            prefs[updatedKey(profileId)] = System.currentTimeMillis()
        }
    }

    suspend fun unavailable(profileId: String = currentProfileId()): TraktAuthUnavailable =
        TraktAuthUnavailable(context.traktDataStore.data.first()[reconnectKey(profileId)] == true)

    suspend fun accessToken(
        profileId: String = currentProfileId(),
        force: Boolean = false,
        rejectedAccessToken: String? = null
    ): String? {
        val observed = credential(context.traktDataStore.data.first(), profileId)
        return refreshMutex.withLock {
            val current = credential(context.traktDataStore.data.first(), profileId)
            val access = current.access?.takeIf { it.isNotBlank() } ?: return@withLock null
            // A concurrent refresh/login/cloud restore already replaced the rejected credential.
            val rejected = rejectedAccessToken ?: observed.access
            val mustRefresh = force && access == rejected && (rejectedAccessToken != null || current == observed)
            val now = System.currentTimeMillis()
            if (!mustRefresh && (current.expires == null || now / 1000 < current.expires - 3600)) {
                return@withLock access
            }
            if (current.refresh.isNullOrBlank()) {
                if (mustRefresh) reject(profileId, current)
                return@withLock if (!mustRefresh && (current.expires == null || now / 1000 < current.expires)) access else null
            }
            fun usable(): String? = access.takeIf { !mustRefresh && current.expires != null &&
                System.currentTimeMillis() / 1000 < current.expires }
            val held = backoff[profileId]
            if (held?.first == current && now < held.second) return@withLock usable()
            try {
                check(Constants.TRAKT_CLIENT_SECRET.isNotBlank()) { "Trakt credentials missing in this APK" }
                // Finish the bounded auth request and persist its single-use replacement even
                // if the UI's short username timeout or a screen exit cancels the caller.
                withContext(NonCancellable) {
                    val token = api.refreshToken(RefreshTokenRequest(
                        refreshToken = current.refresh,
                        clientId = Constants.TRAKT_CLIENT_ID,
                        clientSecret = Constants.TRAKT_CLIENT_SECRET
                    ))
                    // Do not overwrite a login, logout or cloud restore during the request.
                    context.traktDataStore.edit { prefs ->
                        if (credential(prefs, profileId) == current) {
                            prefs[accessKey(profileId)] = token.accessToken
                            prefs[refreshKey(profileId)] = token.refreshToken
                            prefs[expiresKey(profileId)] = token.createdAt + token.expiresIn
                            prefs[updatedKey(profileId)] = System.currentTimeMillis()
                            prefs.remove(reconnectKey(profileId))
                        }
                    }
                }
                backoff.remove(profileId)
                currentCoroutineContext().ensureActive()
                credential(context.traktDataStore.data.first(), profileId).access
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                val http = error as? HttpException
                val invalidGrant = http?.code() == 400 && runCatching {
                    JsonParser.parseString(http.response()?.errorBody()?.string())
                        .asJsonObject.get("error")?.asString == "invalid_grant"
                }.getOrDefault(false)
                if (http?.code() == 401 || invalidGrant) {
                    reject(profileId, current)
                    backoff.remove(profileId)
                } else {
                    val retryMs = http?.response()?.headers()?.get("Retry-After")?.toLongOrNull()
                        ?.coerceIn(30L, 3600L)?.times(1000L) ?: 300_000L
                    backoff[profileId] = current to (System.currentTimeMillis() + retryMs)
                }
                val latest = credential(context.traktDataStore.data.first(), profileId)
                if (latest != current) latest.access else usable()
            }
        }
    }

    private suspend fun reject(profileId: String, expected: Credential) {
        context.traktDataStore.edit { prefs ->
            if (credential(prefs, profileId) == expected) {
                prefs.remove(accessKey(profileId))
                prefs.remove(refreshKey(profileId))
                prefs.remove(expiresKey(profileId))
                // A rejected local session is not a user-requested cloud logout.
                // Keep its generation so a newer rotated cloud credential can recover it.
                if (prefs[updatedKey(profileId)] == null) prefs[updatedKey(profileId)] = 1L
                prefs[reconnectKey(profileId)] = true
            }
        }
    }

    suspend fun <T> authorized(block: suspend (String) -> T): T {
        val id = currentProfileId()
        val token = accessToken(id) ?: throw unavailable(id)
        return try {
            block("Bearer $token")
        } catch (error: HttpException) {
            if (error.code() != 401) throw error
            val refreshed = accessToken(id, force = true, rejectedAccessToken = token) ?: throw unavailable(id)
            try {
                block("Bearer $refreshed")
            } catch (retry: HttpException) {
                if (retry.code() != 401) throw retry
                val prefs = context.traktDataStore.data.first()
                val expected = credential(prefs, id)
                if (expected.access == refreshed) reject(id, expected)
                throw unavailable(id)
            }
        }
    }
}

package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.api.RefreshTokenRequest
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktToken
import com.arflix.tv.util.AppLogger
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

data class TraktConnection(val profileId: String, val connected: Boolean, val reconnectRequired: Boolean)

/** Since when a still-connected profile has had no usable Trakt token (its renewal keeps failing). */
data class TraktSyncPause(val profileId: String, val since: Long)

class TraktAuthUnavailable(val reconnectRequired: Boolean) : IllegalStateException(
    if (reconnectRequired) "Reconnect Trakt to continue syncing" else "Trakt is temporarily unavailable"
)

/** One refresh path for foreground requests and background sync; Trakt refresh tokens are single-use. */
@Singleton
class TraktSession @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profiles: ProfileManager,
    @Named("traktAuth") private val api: TraktApi,
    private val invalidationBus: CloudSyncInvalidationBus = CloudSyncInvalidationBus()
) {
    private val refreshMutex = Mutex()
    private val backoff = mutableMapOf<String, Pair<Credential, Long>>()
    internal var clock: () -> Long = System::currentTimeMillis
    private data class Credential(val access: String?, val refresh: String?, val expires: Long?, val updated: Long?)

    private fun accessKey(id: String) = profiles.profileStringKeyFor(id, "trakt_access_token")
    private fun refreshKey(id: String) = profiles.profileStringKeyFor(id, "trakt_refresh_token")
    private fun expiresKey(id: String) = profiles.profileLongKeyFor(id, "trakt_expires_at")
    private fun updatedKey(id: String) = profiles.profileLongKeyFor(id, "trakt_token_updated_at_v3")
    private fun reconnectKey(id: String) = profiles.profileBooleanKeyFor(id, "trakt_reconnect_required")
    // On disk: a TV app restarts often, and an in-memory count of refusals never reached the limit.
    private fun refusalsKey(id: String) = profiles.profileIntKeyFor(id, "trakt_refresh_refusals")
    private fun refusedTokenKey(id: String) = profiles.profileIntKeyFor(id, "trakt_refresh_refused_token")
    private fun pausedKey(id: String) = profiles.profileLongKeyFor(id, "trakt_sync_paused_since")
    fun currentProfileId(): String = profiles.getProfileIdSync().ifBlank { "default" }
    private fun credential(prefs: Preferences, id: String) = Credential(
        prefs[accessKey(id)], prefs[refreshKey(id)], prefs[expiresKey(id)], prefs[updatedKey(id)]
    )

    val connection = profiles.activeProfileId.combine(context.traktDataStore.data) { id, prefs ->
        TraktConnection(id, !prefs[accessKey(id)].isNullOrBlank(), prefs[reconnectKey(id)] == true)
    }.distinctUntilChanged()

    /** Null while the active profile's Trakt token works, or while Trakt is not connected. */
    val syncPause: Flow<TraktSyncPause?> = profiles.activeProfileId.combine(context.traktDataStore.data) { id, prefs ->
        prefs[pausedKey(id)]?.takeIf { !prefs[accessKey(id)].isNullOrBlank() }?.let { TraktSyncPause(id, it) }
    }.distinctUntilChanged()

    /** Forgets the refusal count and the pause: the credential was replaced or dropped. */
    internal fun clearHealth(prefs: MutablePreferences, profileId: String) {
        prefs.remove(refusalsKey(profileId))
        prefs.remove(refusedTokenKey(profileId))
        prefs.remove(pausedKey(profileId))
    }

    private suspend fun markPaused(profileId: String) {
        if (context.traktDataStore.data.first()[pausedKey(profileId)] != null) return
        context.traktDataStore.edit { prefs ->
            if (!prefs[accessKey(profileId)].isNullOrBlank() && prefs[reconnectKey(profileId)] != true &&
                prefs[pausedKey(profileId)] == null) {
                prefs[pausedKey(profileId)] = clock()
            }
        }
    }

    private suspend fun clearPaused(profileId: String) {
        if (context.traktDataStore.data.first()[pausedKey(profileId)] == null) return
        context.traktDataStore.edit { it.remove(pausedKey(profileId)) }
    }

    suspend fun save(token: TraktToken, profileId: String) {
        context.traktDataStore.edit { prefs ->
            prefs[accessKey(profileId)] = token.accessToken
            prefs[refreshKey(profileId)] = token.refreshToken
            prefs[expiresKey(profileId)] = token.createdAt + token.expiresIn
            prefs[updatedKey(profileId)] = System.currentTimeMillis()
            prefs.remove(reconnectKey(profileId))
            clearHealth(prefs, profileId)
        }
    }

    suspend fun disconnect(profileId: String = currentProfileId()) {
        context.traktDataStore.edit { prefs ->
            prefs.remove(accessKey(profileId))
            prefs.remove(refreshKey(profileId))
            prefs.remove(expiresKey(profileId))
            prefs.remove(reconnectKey(profileId))
            clearHealth(prefs, profileId)
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
        // Null while a token is stored means syncing is paused (no-op without one, or once rejected).
        return accessTokenLocked(profileId, force, rejectedAccessToken, observed).also { if (it == null) markPaused(profileId) }
    }

    private suspend fun accessTokenLocked(
        profileId: String,
        force: Boolean,
        rejectedAccessToken: String?,
        observed: Credential
    ): String? {
        return refreshMutex.withLock {
            val current = credential(context.traktDataStore.data.first(), profileId)
            val access = current.access?.takeIf { it.isNotBlank() } ?: return@withLock null
            // A concurrent refresh/login/cloud restore already replaced the rejected credential.
            val rejected = rejectedAccessToken ?: observed.access
            val mustRefresh = force && access == rejected && (rejectedAccessToken != null || current == observed)
            val now = clock()
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
                    var rotated = false
                    context.traktDataStore.edit { prefs ->
                        if (credential(prefs, profileId) == current) {
                            rotated = true
                            prefs[accessKey(profileId)] = token.accessToken
                            prefs[refreshKey(profileId)] = token.refreshToken
                            prefs[expiresKey(profileId)] = token.createdAt + token.expiresIn
                            prefs[updatedKey(profileId)] = System.currentTimeMillis()
                            prefs.remove(reconnectKey(profileId))
                            clearHealth(prefs, profileId)
                        }
                    }
                    // Other devices share this session and the old refresh token is now dead:
                    // publish the replacement right away instead of at the next unrelated push.
                    if (rotated) invalidationBus.markUserDirty(CloudSyncScope.ACCOUNT, profileId, "trakt token rotated")
                }
                backoff.remove(profileId)
                currentCoroutineContext().ensureActive()
                credential(context.traktDataStore.data.first(), profileId).access
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                val http = error as? HttpException
                val oauthError = runCatching {
                    JsonParser.parseString(http?.response()?.errorBody()?.string())
                        .asJsonObject.get("error")?.asString
                }.getOrNull()
                AppLogger.w("TraktSession", "Token refresh failed: http=${http?.code()} error=$oauthError")
                // A lone 400/403 without invalid_grant may be transient, but the same refresh
                // token refused again and again is dead; keeping it left the session showing as
                // connected while nothing synced.
                val strikes = if (http?.code() == 400 || http?.code() == 403) {
                    recordRefusal(profileId, current)
                } else {
                    0
                }
                if (http?.code() == 401 || oauthError == "invalid_grant" || strikes >= REFUSALS_BEFORE_RECONNECT) {
                    reject(profileId, current)
                    backoff.remove(profileId)
                } else {
                    val retryMs = http?.response()?.headers()?.get("Retry-After")?.toLongOrNull()
                        ?.coerceIn(30L, 3600L)?.times(1000L) ?: 300_000L
                    backoff[profileId] = current to (clock() + retryMs)
                }
                val latest = credential(context.traktDataStore.data.first(), profileId)
                if (latest != current) latest.access else usable()
            }
        }
    }

    /**
     * Counts one more refusal of this refresh token and returns the total. Another token (a
     * login, a rotation from another device) starts over. The count is kept on disk, so app
     * restarts between the retries cannot keep a dead token looking connected forever.
     */
    private suspend fun recordRefusal(profileId: String, refused: Credential): Int {
        var strikes = 0
        context.traktDataStore.edit { prefs ->
            if (credential(prefs, profileId) != refused) return@edit
            val token = refused.refresh.hashCode()
            strikes = (prefs[refusalsKey(profileId)]?.takeIf { prefs[refusedTokenKey(profileId)] == token } ?: 0) + 1
            prefs[refusalsKey(profileId)] = strikes
            prefs[refusedTokenKey(profileId)] = token
        }
        return strikes
    }

    private companion object {
        const val REFUSALS_BEFORE_RECONNECT = 3
    }

    private suspend fun reject(profileId: String, expected: Credential): Boolean {
        var rejected = false
        context.traktDataStore.edit { prefs ->
            if (credential(prefs, profileId) == expected) {
                rejected = true
                prefs.remove(accessKey(profileId))
                prefs.remove(refreshKey(profileId))
                prefs.remove(expiresKey(profileId))
                // A rejected local session is not a user-requested cloud logout.
                // Keep its generation so a newer rotated cloud credential can recover it.
                if (prefs[updatedKey(profileId)] == null) prefs[updatedKey(profileId)] = 1L
                prefs[reconnectKey(profileId)] = true
                clearHealth(prefs, profileId)
            }
        }
        return rejected
    }

    private suspend fun accountRejects(accessToken: String): Boolean {
        return try {
            api.getMe(auth = "Bearer $accessToken", clientId = Constants.TRAKT_CLIENT_ID)
            false
        } catch (error: HttpException) {
            error.code() == 401
        } catch (error: Exception) {
            if (error is CancellationException) throw error
            false
        }
    }

    /** Runs [block] with a working token; a request that succeeds ends any recorded pause. */
    suspend fun <T> authorized(block: suspend (String) -> T): T {
        val id = currentProfileId()
        return authorizedCall(id, block).also { clearPaused(id) }
    }

    private suspend fun <T> authorizedCall(id: String, block: suspend (String) -> T): T {
        val token = accessToken(id) ?: throw unavailable(id)
        return try {
            block("Bearer $token")
        } catch (error: HttpException) {
            if (error.code() != 401) throw error
            // A list can refuse access while the account still works. Confirm the account
            // before forcing renewal, which can otherwise reject an unrefreshable credential.
            if (!accountRejects(token)) throw error
            val refreshed = accessToken(id, force = true, rejectedAccessToken = token) ?: throw unavailable(id)
            try {
                block("Bearer $refreshed")
            } catch (retry: HttpException) {
                if (retry.code() != 401) throw retry
                val prefs = context.traktDataStore.data.first()
                val expected = credential(prefs, id)
                if (expected.access == refreshed && accountRejects(refreshed) && reject(id, expected)) {
                    throw unavailable(id)
                }
                throw retry
            }
        }
    }
}

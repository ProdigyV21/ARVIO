package com.arflix.tv.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.jan.supabase.gotrue.SessionManager
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.sanitizeEmail

/**
 * DataStore-backed SessionManager for Supabase Auth.
 * Ensures session persistence survives app restarts.
 */
class DataStoreSessionManager(
    private val dataStore: DataStore<Preferences>,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
    }
) : SessionManager {
    private val TAG = "DataStoreSessionMgr"
    private val sessionKey = stringPreferencesKey("supabase_session")
    private val mutex = Mutex()

    override suspend fun saveSession(session: UserSession) {
        mutex.withLock {
            try {
                val payload = json.encodeToString(UserSession.serializer(), session)
                dataStore.edit { prefs ->
                    prefs[sessionKey] = payload
                }
                // Verify the save was successful
                val verified = dataStore.data.first()[sessionKey]
                if (verified != null) {
                } else {
                }
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun loadSession(): UserSession? {
        return mutex.withLock {
            try {
                val raw = dataStore.data.first()[sessionKey]
                if (raw == null) {
                    return@withLock null
                }
                val session = json.decodeFromString(UserSession.serializer(), raw)
                session
            } catch (e: Exception) {
                // Clear corrupted data
                try {
                    dataStore.edit { prefs -> prefs.remove(sessionKey) }
                } catch (clearError: Exception) {
                }
                null
            }
        }
    }

    override suspend fun deleteSession() {
        mutex.withLock {
            try {
                dataStore.edit { prefs -> prefs.remove(sessionKey) }
            } catch (e: Exception) {
            }
        }
    }
}

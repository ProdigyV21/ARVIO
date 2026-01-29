package com.arflix.tv.data.repository

import android.util.Log
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
                Log.d(TAG, "Saving session for user: ${session.user?.email}, token length: ${session.accessToken.length}")
                dataStore.edit { prefs ->
                    prefs[sessionKey] = payload
                }
                // Verify the save was successful
                val verified = dataStore.data.first()[sessionKey]
                if (verified != null) {
                    Log.d(TAG, "Session saved and verified successfully")
                } else {
                    Log.e(TAG, "Session save verification failed - data not found after save")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to save session: ${e.message}", e)
            }
        }
    }

    override suspend fun loadSession(): UserSession? {
        return mutex.withLock {
            try {
                val raw = dataStore.data.first()[sessionKey]
                if (raw == null) {
                    Log.d(TAG, "No session found in storage")
                    return@withLock null
                }
                Log.d(TAG, "Found session in storage, length: ${raw.length}")
                val session = json.decodeFromString(UserSession.serializer(), raw)
                Log.d(TAG, "Session loaded successfully for user: ${session.user?.email}")
                session
            } catch (e: Exception) {
                Log.e(TAG, "Failed to load session: ${e.message}", e)
                // Clear corrupted data
                try {
                    dataStore.edit { prefs -> prefs.remove(sessionKey) }
                    Log.w(TAG, "Cleared corrupted session data")
                } catch (clearError: Exception) {
                    Log.e(TAG, "Failed to clear corrupted session", clearError)
                }
                null
            }
        }
    }

    override suspend fun deleteSession() {
        mutex.withLock {
            try {
                dataStore.edit { prefs -> prefs.remove(sessionKey) }
                Log.d(TAG, "Session deleted from storage")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to delete session", e)
            }
        }
    }
}

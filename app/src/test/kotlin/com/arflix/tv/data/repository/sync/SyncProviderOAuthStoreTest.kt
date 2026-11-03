package com.arflix.tv.data.repository.sync

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class SyncProviderOAuthStoreTest {
    private lateinit var store: SyncProviderStore

    @Before fun setup() = runBlocking {
        val context = RuntimeEnvironment.getApplication()
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { context.settingsDataStore } returns MemoryPreferences()
        every { context.traktDataStore } returns MemoryPreferences()
        val profiles = mockk<ProfileManager>()
        every { profiles.getProfileIdSync() } returns "active-other-profile"
        every { profiles.profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
        every { profiles.profileLongKeyFor(any(), any()) } answers { longPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
        every { profiles.profileBooleanKeyFor(any(), any()) } answers { booleanPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
        store = SyncProviderStore(context, profiles)
    }

    @After fun cleanup() = unmockkAll()

    private class MemoryPreferences : DataStore<Preferences> {
        override val data = MutableStateFlow(emptyPreferences())
        private val mutex = Mutex()
        override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = mutex.withLock {
            transform(data.value).also { data.value = it }
        }
    }

    @Test fun rejectedCredentialIsKeptAndMarkedUntilANewOneArrives() = runBlocking {
        store.setMdbListApiKey("revoked", "one")
        val rejected = store.getMdbListCredential("one")!!
        assertTrue(store.markMdbListReconnectRequired("one", rejected))
        assertTrue(store.isMdbListReconnectRequired("one"))
        assertEquals(rejected, store.getMdbListCredential("one"))

        store.setMdbListApiKey("fresh", "one")
        assertFalse(store.isMdbListReconnectRequired("one"))
        // A late rejection of the old key cannot mark the new one.
        assertFalse(store.markMdbListReconnectRequired("one", rejected))
    }

    @Test fun newerCloudCredentialRecoversARejectedConnection() = runBlocking {
        store.setMdbListOAuthTokens("dead", "dead-refresh", 3600, "one")
        store.markMdbListReconnectRequired("one", store.getMdbListCredential("one")!!)
        store.importForProfiles(mapOf("one" to SyncProviderStore.ProfileSyncSelection(
            provider = SyncProvider.MDBLIST,
            mdbListAccessToken = "from-other-device",
            mdbListRefreshToken = "other-refresh",
            mdbListCredentialUpdatedAt = System.currentTimeMillis() + 60_000L
        )))
        assertFalse(store.isMdbListReconnectRequired("one"))
        assertEquals("from-other-device", store.getMdbListAccessToken("one"))
    }

    @Test fun lateRefreshCannotUndoDisconnect() = runBlocking {
        store.setMdbListOAuthTokens("old", "refresh", 3600, "one")
        val expected = store.getMdbListCredential("one") as SyncProviderStore.MdbListCredential.OAuth
        store.clearAllMdbListCredentials("one")
        assertFalse(store.setMdbListOAuthTokens("late", "rotated", 3600, "one", expected))
        assertNull(store.getMdbListCredential("one"))
    }

    @Test fun lateRefreshCannotReplaceNewApiKey() = runBlocking {
        store.setMdbListOAuthTokens("old", "refresh", 3600, "one")
        val expected = store.getMdbListCredential("one") as SyncProviderStore.MdbListCredential.OAuth
        store.setMdbListApiKey("new-key", "one")
        assertFalse(store.setMdbListOAuthTokens("late", "rotated", 3600, "one", expected))
        assertEquals(SyncProviderStore.MdbListCredential.ApiKey("new-key"), store.getMdbListCredential("one"))
    }

    @Test fun newLoginDoesNotInheritOldRefreshTokenOrExpiry() = runBlocking {
        store.setMdbListOAuthTokens("old", "old-refresh", 3600, "one")
        store.setMdbListOAuthTokens("new", null, null, "one")
        assertEquals(SyncProviderStore.MdbListCredential.OAuth("new"), store.getMdbListCredential("one"))
    }

    @Test fun oauthCloudRoundTripKeepsCredentialTypeAndDoesNotTouchOtherProfile() = runBlocking {
        store.setMdbListOAuthTokens("access", "refresh", 3600, "one")
        store.onProviderConnected(SyncProvider.MDBLIST, "one")
        val exported = store.exportForProfiles(listOf("one", "active-other-profile"))
        assertEquals(SyncProvider.MDBLIST, exported.getValue("one").provider)
        assertNull(exported["active-other-profile"])
        store.importForProfiles(mapOf("restored" to exported.getValue("one")))
        assertEquals(store.getMdbListCredential("one"), store.getMdbListCredential("restored"))
        assertNull(store.getMdbListApiKey("restored"))
        store.clearAllMdbListCredentials("one")
        store.onProviderDisconnected(SyncProvider.MDBLIST, "one")
        assertNotNull(store.getMdbListCredential("restored"))
    }
}

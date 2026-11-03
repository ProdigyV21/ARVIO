package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.util.settingsDataStore
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class CatalogDeletionSyncTest {
    private val state = MutableStateFlow(emptyPreferences())
    private var beforeWrite: (suspend () -> Unit)? = null
    private lateinit var repository: CatalogRepository
    private val context: Context get() = RuntimeEnvironment.getApplication()
    private val profileId = "default"
    private val catalogKey = stringPreferencesKey("profile_default_catalogs_v1")
    private val tsKey = stringPreferencesKey("cloud_sync_field_ts")

    @Before fun setup() {
        val mutex = Mutex()
        val store = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
                beforeWrite?.also { beforeWrite = null }?.invoke()
                return mutex.withLock { transform(state.value).toPreferences().also { state.value = it } }
            }
        }
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().settingsDataStore } returns store
        val profiles = mockk<ProfileManager>(relaxed = true)
        every { profiles.getProfileIdSync() } returns profileId
        repository = CatalogRepository(context, profiles, mockk(relaxed = true), mockk(relaxed = true), CloudSyncInvalidationBus())
    }

    @After fun cleanup() { unmockkStatic("com.arflix.tv.util.DataStoresKt") }

    private fun catalog(id: String = "custom_test", packId: String? = null) = CatalogConfig(
        id = id, title = id, sourceType = CatalogSourceType.TRAKT,
        sourceUrl = "https://trakt.tv/users/test/lists/$id", isPreinstalled = false, packId = packId
    )

    @Test fun unrelatedPreferencesDoNotInvalidateCatalogParsing() {
        val initial = preferencesOf(catalogKey to "[]")
        val changed = initial.toMutablePreferences().apply {
            this[booleanPreferencesKey("auto_play_next")] = false
            this[stringPreferencesKey("cloud_sync_field_ts")] = "{\"other\":123}"
            this[stringPreferencesKey("profile_other_catalogs_v1")] = "[]"
        }
        assertTrue(repository.catalogSettingsUnchanged(profileId, initial, changed))
    }

    @Test fun catalogAndHiddenOrLegacyChangesInvalidateParsing() {
        val initial = emptyPreferences()
        for (name in listOf("profile_default_catalogs_v1", "catalogs_v1",
            "profile_default_hidden_preinstalled_catalogs_v2", "profile_default_hidden_addon_catalogs_v1",
            "profile_default_hidden_home_server_catalogs_v1")) {
            val changed = preferencesOf(stringPreferencesKey(name) to "[]")
            assertFalse(name, repository.catalogSettingsUnchanged(profileId, initial, changed))
        }
    }

    @Test fun deletingLastCatalogSurvivesLegacyFallbackAndStaleCloud() = runBlocking {
        repository.replaceCatalogsForProfile(profileId, listOf(catalog()))
        val old = state.value[catalogKey]!!
        context.settingsDataStore.edit { it[stringPreferencesKey("catalogs_v1")] = old }
        assertTrue(repository.removeCustomCatalog("custom_test").isSuccess)
        assertEquals("[]", state.value[catalogKey])
        assertTrue(repository.getCatalogs().isEmpty())
        assertTrue(repository.applyCloudCatalogs(profileId, mapOf("catalogsByProfile" to old), JSONObject()))
        assertTrue(repository.getCatalogs().isEmpty())
    }

    @Test fun removingPackAlsoHidesItsAutoDerivedRowsAtomically() = runBlocking {
        val addon = catalog("addon_test", "pack").copy(sourceType = CatalogSourceType.ADDON, sourceUrl = null)
        repository.replaceCatalogsForProfile(profileId, listOf(addon, catalog("other")))
        assertTrue(repository.removeCatalogPack("pack").isSuccess)
        assertEquals(listOf("addon_test"), repository.getHiddenAddonCatalogIdsForProfile(profileId))
        assertEquals(listOf("other"), repository.getCatalogs().map { it.id })
        val stamps = JSONObject(state.value[tsKey]!!)
        assertTrue(stamps.getLong("c:default:catalogsByProfile") > 0)
        assertTrue(stamps.getLong("c:default:hiddenAddonByProfile") > 0)
    }

    @Test fun reinstallingAddonRestoresOnlyItsOwnDeletedRows() = runBlocking {
        fun addon(id: String) = com.arflix.tv.data.model.Addon(
            id = id, name = id, version = "1", description = "", isInstalled = true,
            type = com.arflix.tv.data.model.AddonType.CUSTOM, url = "https://example.test/$id/manifest.json",
            manifest = com.arflix.tv.data.model.AddonManifest(
                id = id, name = id, version = "1",
                catalogs = listOf(
                    com.arflix.tv.data.model.AddonCatalog(type = "series", id = "one", name = "One"),
                    com.arflix.tv.data.model.AddonCatalog(type = "series", id = "two", name = "Two")
                )
            )
        )
        val reinstalled = addon("example.vod")
        val other = addon("example.other")
        repository.syncAddonCatalogs(listOf(reinstalled, other))
        val rows = repository.getCatalogs().map { it.id }
        assertEquals(4, rows.size)
        rows.forEach { repository.removeCustomCatalog(it).getOrThrow() }
        assertEquals(4, repository.getHiddenAddonCatalogIdsForProfile(profileId).size)

        repository.restoreHiddenAddonCatalogs(reinstalled)
        repository.syncAddonCatalogs(listOf(reinstalled, other))

        assertEquals(listOf("One", "Two"), repository.getCatalogs().map { it.title })
        assertEquals(2, repository.getHiddenAddonCatalogIdsForProfile(profileId).size)
        assertTrue(JSONObject(state.value[tsKey]!!).getLong("c:default:hiddenAddonByProfile") > 0)
    }

    @Test fun staleCloudCannotUnhidePreinstalledCatalog() = runBlocking {
        val defaults = MediaRepository.buildPreinstalledDefaults()
        repository.ensurePreinstalledDefaults(defaults)
        val target = defaults.first()
        repository.removeCustomCatalog(target.id).getOrThrow()
        repository.applyCloudCatalogs(profileId, mapOf("hiddenPreinstalledByProfile" to "[]"), JSONObject())
        repository.ensurePreinstalledDefaults(defaults)
        assertFalse(repository.getCatalogs().any { it.id == target.id })
    }

    @Test fun automaticSetupDoesNotOutrankExistingCloudChoices() = runBlocking {
        repository.ensurePreinstalledDefaults(MediaRepository.buildPreinstalledDefaults())
        assertEquals(0L, JSONObject(state.value[tsKey] ?: "{}").optLong("c:default:catalogsByProfile"))
        assertFalse(repository.applyCloudCatalogs(profileId, mapOf("catalogsByProfile" to "[]"),
            JSONObject().put("c:default:catalogsByProfile", 100)))
        assertTrue(repository.getCatalogs().isEmpty())
    }

    @Test fun explicitlyNewerCloudEditCanRestoreCatalogAndDoesNotAffectOtherProfile() = runBlocking {
        repository.replaceCatalogsForProfile(profileId, listOf(catalog()))
        val old = state.value[catalogKey]!!
        repository.removeCustomCatalog("custom_test").getOrThrow()
        val next = JSONObject(state.value[tsKey]!!).getLong("c:default:catalogsByProfile") + 1
        assertFalse(repository.applyCloudCatalogs(profileId, mapOf("catalogsByProfile" to old),
            JSONObject().put("c:default:catalogsByProfile", next)))
        assertEquals(listOf("custom_test"), repository.getCatalogs().map { it.id })
        repository.applyCloudCatalogs("other", mapOf("catalogsByProfile" to "[]"), JSONObject())
        assertEquals(listOf("custom_test"), repository.getCatalogs().map { it.id })
    }

    @Test fun backgroundDefaultRefreshCannotRestoreCustomCatalogDeletedWhileItWasRunning() = runBlocking {
        repository.replaceCatalogsForProfile(profileId, listOf(catalog()))
        val captured = CompletableDeferred<Unit>()
        val resume = CompletableDeferred<Unit>()
        beforeWrite = { captured.complete(Unit); resume.await() }
        val refresh = async { repository.ensurePreinstalledDefaults(MediaRepository.buildPreinstalledDefaults()) }
        withTimeout(5000) { captured.await() }
        repository.removeCustomCatalog("custom_test").getOrThrow()
        resume.complete(Unit)
        refresh.await()
        assertFalse(repository.getCatalogs().any { it.id == "custom_test" })
    }

    @Test fun snapshotAssemblyKeepsDeletedListWithItsTimestamp() = runBlocking {
        repository.replaceCatalogsForProfile(profileId, listOf(catalog()))
        val captured = JSONObject(state.value[tsKey]!!)
        val root = JSONObject().put("catalogsByProfile", JSONObject().put(profileId, org.json.JSONArray(state.value[catalogKey])))
        repository.removeCustomCatalog("custom_test").getOrThrow()
        CatalogCloudFields.reconcileSnapshot(root, JSONObject(state.value[tsKey]!!),
            JSONObject(state.value[stringPreferencesKey("cloud_sync_field_base")]!!), captured)
        assertEquals("[]", CatalogCloudFields.value(root, "c:default:catalogsByProfile").toString())
    }

    @Test fun pushAndPullMergeBothKeepTheNewerDeletion() {
        val cloud = CloudSyncRepository(
            context = context, authRepository = mockk(relaxed = true), profileRepository = mockk(relaxed = true),
            profileManager = mockk(relaxed = true), catalogRepository = repository, iptvRepository = mockk(relaxed = true),
            streamRepository = mockk(relaxed = true), homeServerRepository = mockk(relaxed = true),
            traktRepository = mockk(relaxed = true), watchHistoryRepository = mockk(relaxed = true),
            watchlistRepository = mockk(relaxed = true), profileAvatarImageManager = mockk(relaxed = true),
            invalidationBus = CloudSyncInvalidationBus(), pluginDataStore = mockk(relaxed = true),
            syncProviderStore = mockk(relaxed = true), streamIntegrationRepository = mockk(relaxed = true)
        )
        fun payload(items: String, clock: Long) = JSONObject()
            .put("catalogsByProfile", JSONObject().put("default", org.json.JSONArray(items)))
            .put("fieldUpdatedAt", JSONObject().put("c:default:catalogsByProfile", clock)).toString()
        val stale = payload("[{\"id\":\"removed\"}]", 100)
        val deleted = payload("[]", 200)
        val merge = CloudSyncRepository::class.java.getDeclaredMethod(
            "mergeSettingsByTimestamp", String::class.java, String::class.java
        ).apply { isAccessible = true }
        for ((base, other) in listOf(stale to deleted, deleted to stale)) {
            val result = merge.invoke(cloud, base, other)
            val json = result.javaClass.getDeclaredField("json").apply { isAccessible = true }.get(result) as String
            val merged = JSONObject(json)
            assertEquals("[]", CatalogCloudFields.value(merged, "c:default:catalogsByProfile").toString())
            assertEquals(200L, merged.getJSONObject("fieldUpdatedAt").getLong("c:default:catalogsByProfile"))
        }
    }
}

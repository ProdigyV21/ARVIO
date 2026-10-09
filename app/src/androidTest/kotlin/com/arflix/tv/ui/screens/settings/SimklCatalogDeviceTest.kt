package com.arflix.tv.ui.screens.settings

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.arflix.tv.data.model.CatalogDiscoveryResult
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.CatalogRepository
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.simkl.SimklListsRepository
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.util.SecureStorage
import dagger.hilt.android.EntryPointAccessors
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import com.arflix.tv.data.repository.simkl.SimklListsClient
import com.arflix.tv.data.repository.simkl.SimklListsGrant
import com.arflix.tv.util.Constants
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.sync.SyncProviderStore
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/** Opt-in read-only live check. Test credentials are never checked in or printed. */
@RunWith(AndroidJUnit4::class)
class SimklCatalogDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun liveSimklListSearchRendersRealArtAndTvActions(): Unit = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("simklLive") == "true")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val grant = Gson().fromJson(File(context.filesDir, "simkl-test-grant.json").readText(), SimklListsGrant::class.java)
        val profile = mockk<ProfileManager>(relaxed = true)
        every { profile.getProfileIdSync() } returns "simkl-emulator-qa"
        coEvery { profile.getProfileId() } returns "simkl-emulator-qa"
        val prefs = context.getSharedPreferences("simkl_lists_v2", android.content.Context.MODE_PRIVATE)
        prefs.edit().putString("grant_simkl-emulator-qa", SecureStorage.encrypt(Gson().toJson(grant.copy(expiresAt = 0)), "arvio_simkl_lists_v2")).commit()
        val listsRepository = SimklListsRepository(context, profile)
        val catalogs = CatalogRepository(context, profile, mockk(relaxed = true), OkHttpClient(), mockk(relaxed = true), listsRepository)
        val tmdb = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java).tmdbApi()
        val media = MediaRepository(context, tmdb, mockk(relaxed = true), mockk(relaxed = true), OkHttpClient(),
            mockk(relaxed = true), mockk(relaxed = true), listsRepository)
        val saved = catalogs.addCustomCatalog("https://simkl.com/5/list/14462/best-mindfucks-tv-shows").getOrThrow()
        try {
        val refreshedToken = listsRepository.accessToken()
        File(context.filesDir, "simkl-test-grant.json").writeText(SecureStorage.decrypt(prefs.getString("grant_simkl-emulator-qa", null), "arvio_simkl_lists_v2")!!)
        assertNotEquals(grant.accessToken, refreshedToken)
        assertEquals(grant.connectionId, listsRepository.connectionId())
        assertEquals(refreshedToken, SimklListsRepository(context, profile).accessToken())
        assertEquals(CatalogSourceType.SIMKL, saved.sourceType)
        assertEquals("simkl_list:14462", saved.sourceRef)
        assertTrue(catalogs.getCatalogs().any { it.id == saved.id })
        assertTrue(catalogs.addCustomCatalog("https://simkl.com/lists/14462").isFailure)
        val first = media.loadCustomCatalogPage(saved, 0, 4)
        val next = media.loadCustomCatalogPage(saved, 4, 4)
        assertEquals(4, first.items.size)
        assertEquals(70523, first.items.first().id)
        assertTrue(first.hasMore)
        assertEquals(4, next.items.size)
        assertTrue(next.items.none { item -> first.items.any { it.id == item.id } })
        val api = SimklListsClient(OkHttpClient(), Constants.SIMKL_V2_CLIENT_ID)
        val list = api.list(14462, refreshedToken)
        assertTrue(list.items.size > 300)
        assertEquals("Dark", list.items.first().title)
        assertEquals(70523, list.items.first().tmdb)
        val index = api.officialLists(refreshedToken)
        val matches = index.filter { it.name.contains("mindfuck", true) }
        assertTrue(matches.isNotEmpty())
        assertTrue(listsRepository.search("mindfuck").any { it.id == "simkl:14462" })
        val imageLoader = ImageLoader.Builder(context).build()
        try {
            val poster = list.items.first().poster!!
            assertTrue(imageLoader.execute(ImageRequest.Builder(context).data(poster).size(180, 270).build()) is SuccessResult)
            val results = matches.map { item -> CatalogDiscoveryResult("simkl:${item.id}", item.name, item.description,
                CatalogSourceType.SIMKL, item.url, item.ownerName, item.ownerId?.toString(), item.updatedAt,
                item.count, item.likes, item.posters) }
            var added: String? = null
            var submitted = false
            compose.setContent {
                CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                    CatalogDiscoveryModal("mindfuck", results, false, null, "https://simkl.com/5/list/14462",
                        emptySet(), {}, { submitted = true }, { added = it.sourceUrl }, {}, { submitted = true }, {},
                        simklConnected = true)
                }
            }
            compose.waitForIdle()
            compose.onAllNodesWithText("SIMKL", substring = false).onFirst().assertIsDisplayed()
            compose.onAllNodesWithText("Add", substring = false).onFirst().performClick()
            compose.runOnIdle { assertNotNull(added) }
            compose.onNodeWithText("Add URL").performClick()
            compose.runOnIdle { assertTrue(submitted) }
            val folder = File(context.getExternalFilesDir(null), "simkl-catalog-qa").apply { mkdirs() }
            File(folder, "simkl-catalog-search-tv.png").outputStream().use {
                compose.onNode(isDialog()).captureToImage().asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, it)
            }
        } finally { imageLoader.shutdown() }
        } finally {
            catalogs.removeCustomCatalog(saved.id).getOrThrow()
            listsRepository.disconnect()
        }
    }

    @Test fun mobileSimklConnectionAndListUrlControlsFit() {
        var connect = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                CatalogDiscoveryModal("", emptyList(), false, null, "", emptySet(), {}, {}, {}, {}, {}, {},
                    onConnectSimkl = { connect = true })
            }
        }
        compose.onNodeWithText("Connect SIMKL").assertIsDisplayed().performClick()
        compose.runOnIdle { assertTrue(connect) }
        compose.onNodeWithText("Search Lists").assertIsDisplayed()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val folder = File(context.getExternalFilesDir(null), "simkl-catalog-qa").apply { mkdirs() }
        File(folder, "simkl-catalog-phone.png").outputStream().use {
            compose.onNode(isDialog()).captureToImage().asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    @Test fun liveV2DeviceSignInPersistsGrant(): Unit = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("simklDeviceAuth") == "true")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val profile = mockk<ProfileManager>(relaxed = true)
        every { profile.getProfileIdSync() } returns "simkl-pin-qa"
        val repository = SimklListsRepository(context, profile)
        val store = mockk<SyncProviderStore>(relaxed = true)
        coEvery { store.getSimklAccessToken() } coAnswers { repository.accessToken() }
        val manager = SimklAuthManager(mockk(relaxed = true), store, repository)
        val code = manager.startPinAuth()
        val folder = File(context.getExternalFilesDir(null), "simkl-catalog-qa").apply { mkdirs() }
        File(folder, "device-auth-url.txt").writeText(code.verificationUrl)
        try {
            var connected = false
            repeat(48) {
                if (!connected) { delay(5_000); connected = manager.pollPinAuth(code.userCode) }
            }
            assertTrue("Live V2 PIN authorization timed out", connected)
            assertTrue(manager.isV2Connected())
            assertEquals(Constants.SIMKL_V2_CLIENT_ID, manager.effectiveClientId)
            val token = repository.accessToken()
            val privateGrant = context.getSharedPreferences("simkl_lists_v2", android.content.Context.MODE_PRIVATE).getString("grant_simkl-pin-qa", null)
            File(context.filesDir, "simkl-test-grant.json").writeText(SecureStorage.decrypt(privateGrant, "arvio_simkl_lists_v2")!!)
            assertTrue(token.startsWith("simkl_at_"))
            assertEquals(token, SimklListsRepository(context, profile).accessToken())
            assertTrue(SimklListsClient(OkHttpClient(), Constants.SIMKL_V2_CLIENT_ID).userId(token) > 0)
        } finally {
            manager.cancelPinAuth()
            repository.disconnect()
            File(folder, "device-auth-url.txt").delete()
        }
    }
}

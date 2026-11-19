package com.arflix.tv.data.repository

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.pressKey
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.R
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbReleaseDate
import com.arflix.tv.data.api.TmdbReleaseDatesResponse
import com.arflix.tv.data.api.TmdbReleaseDatesResult
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktIds
import com.arflix.tv.data.api.TraktMovieInfo
import com.arflix.tv.data.api.TraktPublicListItem
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.KidsAgeLevel
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.screens.profile.PinEntryDialog
import com.arflix.tv.ui.screens.settings.KidsListApprovalUi
import com.arflix.tv.ui.screens.settings.KidsListOptionsDialog
import com.arflix.tv.ui.screens.settings.KidsListTitleDialog
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.PinUtil
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import java.io.IOException
import java.util.concurrent.atomic.AtomicReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class KidsListApprovalDeviceTest {
    @get:Rule val compose = createComposeRule()

    private class Fixture {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val profile = Profile(name = "Test child", isKidsProfile = true, kidsAgeLevel = KidsAgeLevel.LITTLE_KIDS.name,
            parentPin = PinUtil.hashPin("1234"))
        val other = profile.copy(id = profile.id + "-other", name = "Other child")
        val active = MutableStateFlow<Profile?>(profile)
        val catalog = CatalogConfig("custom_test", "Family list", CatalogSourceType.MDBLIST,
            sourceUrl = "https://mdblist.com/lists/test/family")
        val unrated = MediaItem(882001, "Unrated family film")
        val mature = MediaItem(882002, "Restricted film")
        val newTitle = MediaItem(882003, "New list member")
        val listBody = AtomicReference<String?>(null)
        val onNextListRequest = AtomicReference<(() -> Unit)?>(null)
        val certification = AtomicReference<String?>(null)
        val traktMembers = AtomicReference<List<TraktPublicListItem>>(emptyList())
        private val tmdb = mockk<TmdbApi>()
        private val trakt = mockk<TraktApi> {
            coEvery { getUserListItems(any(), any(), "test", "family", "movies", any(), 1, any(), any()) } answers { traktMembers.get() }
            coEvery { getUserListItems(any(), any(), "test", "family", "shows", any(), 1, any(), any()) } returns emptyList()
        }
        private val profiles = mockk<ProfileRepository> {
            every { activeProfile } returns active
            coEvery { getActiveProfile() } answers { active.value }
        }
        private val profileManager = mockk<ProfileManager> {
            coEvery { getActiveProfile() } answers { active.value }
            every { getProfileIdSync() } answers { active.value?.id ?: "default" }
        }
        private val http = OkHttpClient.Builder().addInterceptor { chain ->
            onNextListRequest.getAndSet(null)?.invoke()
            val body = listBody.get()
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1)
                .code(if (body != null) 200 else 503).message("Fixture")
                .body(body.orEmpty().toResponseBody()).build()
        }.build()
        val catalogs = CatalogRepository(context, profileManager, trakt, http, CloudSyncInvalidationBus())
        val media = spyk(MediaRepository(context, tmdb, mockk(), trakt, http, mockk(), mockk(), catalogRepository = catalogs))
        val filter = KidsContentFilter(context, tmdb, profiles, catalogs) { media }

        init {
            setMembers(unrated, mature)
            coEvery { media.getMovieDetails(any()) } answers {
                val id = firstArg<Int>()
                listOf(unrated, mature, newTitle).first { it.id == id }
            }
            coEvery { tmdb.getMovieReleaseDates(any(), any()) } answers {
                val rating = if (firstArg<Int>() == mature.id) "R" else certification.get()
                if (rating == "network failure") throw IOException("Fixture")
                TmdbReleaseDatesResponse(rating?.let { listOf(TmdbReleaseDatesResult("US", listOf(TmdbReleaseDate(it)))) }.orEmpty())
            }
        }

        fun setMembers(vararg items: MediaItem) {
            listBody.set(items.joinToString(prefix = "[", postfix = "]") { "{\"id\":${it.id},\"mediatype\":\"movie\"}" })
        }

        suspend fun seed() {
            catalogs.replaceCatalogsForProfile(profile.id, listOf(catalog))
            catalogs.replaceCatalogsForProfile(other.id, listOf(catalog))
        }

        suspend fun approved(profileId: String = profile.id) = catalogs.observeKidsListApprovals(profileId).first()
        suspend fun session() = catalogs.beginKidsListApproval(profile.id, catalog, "1234")
        suspend fun allowed(item: MediaItem) = filter.isAllowed(item.mediaType, item.id)
    }

    private suspend fun rejected(resource: Int, action: suspend () -> Unit) {
        try {
            action()
            fail("Expected approval to be rejected")
        } catch (e: CatalogException) {
            assertEquals(resource, e.messageRes)
        }
    }

    @Test fun approvalRequiresPinAndVerifiedMissingCertification() = runBlocking {
        val f = Fixture()
        f.seed()
        f.media.loadCustomCatalogPage(f.catalog, 0, 24)
        assertTrue(f.approved().isEmpty())
        assertFalse(f.allowed(f.unrated))
        rejected(R.string.kids_list_approval_pin_required) { f.catalogs.beginKidsListApproval(f.profile.id, f.catalog, "9999") }
        val session = f.session()
        rejected(R.string.kids_list_approval_rated) { f.filter.approveListTitle(session, f.mature) }
        f.certification.set("unrecognized classification")
        rejected(R.string.kids_list_approval_rated) { f.filter.approveListTitle(session, f.unrated) }
        f.certification.set("network failure")
        rejected(R.string.kids_list_approval_unavailable) { f.filter.approveListTitle(session, f.unrated) }
        f.certification.set(null)
        f.filter.approveListTitle(session, f.unrated)
        assertTrue(f.allowed(f.unrated))
        assertFalse(f.allowed(f.mature))
        val visible = withTimeout(5_000) { f.filter.state.first { it.allows(f.unrated) } }
        assertFalse(visible.allows(f.mature))
        f.setMembers(f.unrated, f.mature, f.newTitle)
        f.media.loadCustomCatalogPage(f.catalog, 0, 24)
        assertFalse(f.allowed(f.newTitle))
        f.certification.set("R")
        assertFalse("A new certification must be honored at the playback gate", f.allowed(f.unrated))
    }

    @Test fun revokesOnEmptyRemovalAndReplacementWithoutCrossingProfiles() = runBlocking {
        val f = Fixture()
        f.seed()
        val session = f.session()
        f.filter.approveListTitle(session, f.unrated)
        val refresh = requireNotNull(f.catalogs.beginKidsListRefresh(f.catalog))
        f.active.value = f.other
        f.catalogs.finishKidsListRefresh(refresh, f.catalog, emptyList())
        withTimeout(5_000) { f.filter.state.first { it.isActive && !it.allows(f.unrated) } }
        assertFalse(f.allowed(f.unrated))
        assertTrue(f.approved(f.other.id).isEmpty())
        rejected(R.string.kids_list_approval_changed) { f.filter.approveListTitle(session, f.unrated) }
        f.active.value = f.profile
        assertTrue(f.allowed(f.unrated))
        f.setMembers(f.unrated, f.newTitle)
        f.onNextListRequest.set { f.active.value = f.other }
        rejected(R.string.kids_list_approval_changed) { f.filter.approveListTitle(session, f.newTitle) }
        assertTrue(f.approved(f.other.id).isEmpty())
        assertEquals(setOf(KidsTrustedTitles.key(MediaType.MOVIE, f.unrated.id)), f.approved())
        f.active.value = f.profile
        f.listBody.set(null)
        f.media.loadCustomCatalogPage(f.catalog, 0, 24)
        assertTrue("A failed request is not an authoritative empty list", f.allowed(f.unrated))
        f.setMembers()
        f.media.loadCustomCatalogPage(f.catalog, 0, 24)
        assertFalse(f.allowed(f.unrated))
        f.setMembers(f.unrated)
        f.media.loadCustomCatalog(f.catalog)
        assertFalse("Reappearing titles need fresh approval", f.allowed(f.unrated))
        f.filter.approveListTitle(session, f.unrated)
        assertTrue(f.catalogs.removeCustomCatalog(f.catalog.id).isSuccess)
        f.catalogs.replaceCatalogsForProfile(f.profile.id, listOf(f.catalog))
        assertFalse(f.allowed(f.unrated))
        f.filter.approveListTitle(f.session(), f.unrated)
        f.catalogs.replaceCatalogsForProfile(f.profile.id, listOf(f.catalog.copy(sourceUrl = f.catalog.sourceUrl + "-replacement")))
        assertFalse(f.allowed(f.unrated))
    }

    @Test fun traktMembershipRefreshAlsoRevokesAnApprovedTitle() = runBlocking {
        val f = Fixture()
        val row = f.catalog.copy(sourceType = CatalogSourceType.TRAKT,
            sourceUrl = "https://trakt.tv/users/test/lists/family", sourceRef = "trakt_user:test:family")
        f.catalogs.replaceCatalogsForProfile(f.profile.id, listOf(row))
        f.traktMembers.set(listOf(TraktPublicListItem(type = "movie",
            movie = TraktMovieInfo(f.unrated.title, null, TraktIds(tmdb = f.unrated.id)))))
        val session = f.catalogs.beginKidsListApproval(f.profile.id, row, "1234")
        f.filter.approveListTitle(session, f.unrated)
        assertTrue(f.allowed(f.unrated))
        f.traktMembers.set(emptyList())
        f.media.loadCustomCatalogPage(row, 0, 24)
        assertFalse(f.allowed(f.unrated))
        val linked = f.catalog.copy(sourceRef = "mdblist_trakt:https://trakt.tv/users/test/lists/family")
        assertEquals(listOf(f.unrated.id, f.mature.id), f.media.loadCustomCatalogPage(linked, 0, 24).items.map { it.id })
    }

    @Test fun tvRemoteCanChooseAnIndividualTitleAfterParentPin() {
        val f = Fixture()
        runBlocking { f.seed() }
        val approve = f.context.getString(R.string.kids_list_approve_title)
        val wrongPin = f.context.getString(R.string.kids_list_approval_pin_required)
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    val scope = rememberCoroutineScope()
                    var stage by remember { mutableIntStateOf(0) }
                    var error by remember { mutableStateOf("") }
                    var attempts by remember { mutableIntStateOf(0) }
                    var session by remember { mutableStateOf<KidsListApprovalSession?>(null) }
                    var items by remember { mutableStateOf<List<MediaItem>>(emptyList()) }
                    when (stage) {
                        0 -> KidsListOptionsDialog(f.catalog.title, {}, { stage = 1 }, {})
                        1 -> androidx.compose.runtime.key(attempts) {
                            PinEntryDialog(title = f.context.getString(R.string.profile_parent_pin_enter), pinError = error,
                                onDismiss = { stage = 0 }, onPinConfirmed = { pin ->
                                    scope.launch {
                                        try {
                                            session = f.catalogs.beginKidsListApproval(f.profile.id, f.catalog, pin)
                                            items = f.media.loadCustomCatalogPage(f.catalog, 0, 24).items
                                            stage = 2
                                        } catch (e: CatalogException) {
                                            error = f.context.getString(e.messageRes)
                                            attempts++
                                        }
                                    }
                                })
                        }
                        2 -> KidsListTitleDialog(KidsListApprovalUi(f.profile.id, f.catalog, needsPin = false, items = items),
                            null, { item -> scope.launch { f.filter.approveListTitle(requireNotNull(session), item); stage = 3 } },
                            {}, { stage = 0 })
                    }
                }
            }
        }
        compose.onNodeWithText(f.context.getString(R.string.rename_catalog)).assertIsFocused()
            .performKeyInput { pressKey(Key.DirectionDown) }
        compose.onNodeWithText(approve).assertIsFocused().performKeyInput { pressKey(Key.DirectionCenter) }
        fun submitPin(pin: String) {
            pin.forEach { digit ->
                compose.onNodeWithText(digit.toString()).performSemanticsAction(SemanticsActions.OnClick) { it() }
            }
            compose.onNodeWithText(f.context.getString(R.string.confirm))
                .performSemanticsAction(SemanticsActions.OnClick) { it() }
        }
        submitPin("9999")
        compose.waitUntil(10_000) { compose.onAllNodesWithText(wrongPin).fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithText(wrongPin).assertIsDisplayed()
        submitPin("1234")
        val focusedTitle = hasText(f.unrated.title) and isFocused()
        compose.waitUntil(20_000) { compose.onAllNodes(focusedTitle).fetchSemanticsNodes().isNotEmpty() }
        InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot()?.let { screenshot ->
            java.io.File(f.context.cacheDir, "kids-list-approval.png").outputStream().use {
                screenshot.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
            }
            screenshot.recycle()
        }
        compose.onNodeWithText(f.unrated.title).assertIsFocused().performKeyInput { pressKey(Key.DirectionCenter) }
        compose.waitUntil(20_000) { compose.onAllNodesWithText(f.unrated.title).fetchSemanticsNodes().isEmpty() }
        runBlocking {
            assertEquals(setOf(KidsTrustedTitles.key(MediaType.MOVIE, f.unrated.id)), f.approved())
            assertTrue(f.allowed(f.unrated))
            assertFalse(f.allowed(f.mature))
        }
    }
}

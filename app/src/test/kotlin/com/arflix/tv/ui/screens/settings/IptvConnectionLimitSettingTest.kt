package com.arflix.tv.ui.screens.settings

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.PartnerDeliveryRepository
import com.arflix.tv.data.repository.StalkerPortalEntry
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvConnectionLimitSettingTest {
    private val scheduler = TestCoroutineScheduler()
    private val iptv = mockk<IptvRepository>(relaxed = true)
    private val cloud = mockk<CloudSyncRepository>(relaxed = true)
    private val partner = mockk<PartnerDeliveryRepository>(relaxed = true)
    private val auth = mockk<AuthRepository>(relaxed = true)
    private var created: SettingsViewModel? = null

    /** Drops the queued start-up work, which needs a real app, before it ever runs. */
    private fun viewModel() = SettingsViewModel(
        RuntimeEnvironment.getApplication(),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), iptv, mockk(relaxed = true), mockk(relaxed = true),
        auth, mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), cloud,
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        partnerDelivery = dagger.Lazy { partner }
    ).also {
        created = it
        it.viewModelScope.coroutineContext.cancelChildren()
        scheduler.advanceUntilIdle()
    }

    @Before fun setup() { Dispatchers.setMain(StandardTestDispatcher(scheduler)) }

    @After fun cleanup() {
        created?.viewModelScope?.cancel()
        scheduler.advanceUntilIdle()
        Dispatchers.resetMain()
    }

    @Test fun steppingTheLimitWritesNoCloudDataAndTriggersNoReloadWhereAConnectionEditWould() {
        every { partner.activeDelivery() } returns null
        coEvery { auth.hasValidCloudSyncSession() } returns true
        val model = viewModel().signedIn()
        clearMocks(iptv, cloud, answers = false, recordedCalls = true)
        model.stepIptvConnectionLimit("list_a")
        scheduler.advanceUntilIdle()
        coVerify(exactly = 1) { iptv.stepConnectionLimit("list_a") }
        coVerify(exactly = 0) { iptv.savePlaylists(any()) }
        coVerify(exactly = 0) { iptv.saveStalkerPortals(any()) }
        coVerify(exactly = 0) { iptv.fetchAccountInfo(any<IptvPlaylistEntry>()) }
        coVerify(exactly = 0) { iptv.loadSnapshot(any(), any(), any(), any(), any(), any()) }
        coVerify(exactly = 0) { cloud.markLocalStateDirty() }
        coVerify(exactly = 0) { cloud.markLocalStateDirtyNow() }
        coVerify(exactly = 0) { cloud.pushToCloud(any()) }
        // The same setup does see an ordinary connection edit, so the silence above is real.
        model.moveStreamProviderUp("provider")
        scheduler.advanceUntilIdle()
        coVerify(exactly = 1) { cloud.markLocalStateDirty() }
    }

    @Test fun addingASourceStoresItsChosenLimitForTheIdItsSaveAssignedAndAutoStoresNothing() {
        every { partner.activeDelivery() } returns null
        coEvery { auth.hasValidCloudSyncSession() } returns true
        val saved = IptvPlaylistEntry("list_a", "Saved", "http://a.example/list.m3u")
        val config = MutableStateFlow(IptvConfig(playlists = listOf(saved)))
        every { iptv.observePersonalConfig() } returns config
        coEvery { iptv.savePlaylists(any()) } answers { config.value = config.value.copy(playlists = firstArg()) }
        coEvery { iptv.saveStalkerPortals(any()) } answers { config.value = config.value.copy(stalkerPortals = firstArg<List<StalkerPortalEntry>>()) }
        val model = viewModel().signedIn(playlists = listOf(saved))
        model.saveIptvPlaylists(listOf(saved, IptvPlaylistEntry("list_new", "New", "http://b.example/list.m3u")), IptvConnectionLimit.TWO)
        scheduler.advanceUntilIdle()
        model.onAddStalkerPortal("http://portal.example/c", "00:1A:79:00:00:01", connectionLimit = IptvConnectionLimit.NO_LIMIT)
        scheduler.advanceUntilIdle()
        model.onAddStalkerPortal("http://other.example/c", "00:1A:79:00:00:02")
        scheduler.advanceUntilIdle()
        coVerify(exactly = 1) { iptv.saveConnectionLimit("list_new", IptvConnectionLimit.TWO) }
        coVerify(exactly = 1) { iptv.saveConnectionLimit("stalker1", IptvConnectionLimit.NO_LIMIT) }
        coVerify(exactly = 2) { iptv.saveConnectionLimit(any(), any()) }
    }

    private fun SettingsViewModel.signedIn(playlists: List<IptvPlaylistEntry> = emptyList()): SettingsViewModel = apply {
        @Suppress("UNCHECKED_CAST")
        val state = SettingsViewModel::class.java.getDeclaredField("_uiState").apply { isAccessible = true }
            .get(this) as MutableStateFlow<SettingsUiState>
        state.value = state.value.copy(isLoggedIn = true, iptvPlaylists = playlists)
    }
}

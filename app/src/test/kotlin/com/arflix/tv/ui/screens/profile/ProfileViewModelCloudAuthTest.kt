package com.arflix.tv.ui.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.repository.*
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelCloudAuthTest {
    private val context = mockk<Context>(relaxed = true)
    private val authRepository = mockk<AuthRepository>(relaxed = true)
    private val profileRepository = mockk<ProfileRepository>(relaxed = true)
    private val profileManager = mockk<ProfileManager>(relaxed = true)
    private val traktRepository = mockk<TraktRepository>(relaxed = true)
    private val watchHistoryRepository = mockk<WatchHistoryRepository>(relaxed = true)
    private val watchlistRepository = mockk<WatchlistRepository>(relaxed = true)
    private val iptvRepository = mockk<IptvRepository>(relaxed = true)
    private val profileAvatarImageManager = mockk<ProfileAvatarImageManager>(relaxed = true)
    private val cloudSyncRepository = mockk<CloudSyncRepository>(relaxed = true)
    private val tvDeviceAuthRepository = mockk<TvDeviceAuthRepository>(relaxed = true)

    private val authStateFlow = MutableStateFlow<AuthState>(AuthState.NotAuthenticated)
    private val profilesFlow = MutableStateFlow(emptyList<com.arflix.tv.data.model.Profile>())
    private val activeProfileFlow = MutableStateFlow<com.arflix.tv.data.model.Profile?>(null)

    private lateinit var viewModel: ProfileViewModel
    private val store = ViewModelStore()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        every { authRepository.authState } returns authStateFlow
        every { profileRepository.profiles } returns profilesFlow
        every { profileRepository.activeProfile } returns activeProfileFlow
        coEvery { profileRepository.getProfiles() } returns emptyList()
        coEvery { profileRepository.getActiveProfile() } returns null

        viewModel = ProfileViewModel(
            context = context,
            authRepository = authRepository,
            profileRepository = profileRepository,
            profileManager = profileManager,
            traktRepository = traktRepository,
            watchHistoryRepository = watchHistoryRepository,
            watchlistRepository = watchlistRepository,
            iptvRepository = iptvRepository,
            profileAvatarImageManager = profileAvatarImageManager,
            cloudSyncRepository = cloudSyncRepository,
            tvDeviceAuthRepository = tvDeviceAuthRepository
        )
        store.put("profile", viewModel)
    }

    @After
    fun tearDown() {
        try {
            store.clear()
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun `openCloudEmailPasswordDialog opens dialog without requiring navigation`() {
        assertFalse(viewModel.uiState.value.showCloudEmailPasswordDialog)
        viewModel.openCloudEmailPasswordDialog()
        assertTrue(viewModel.uiState.value.showCloudEmailPasswordDialog)
        assertFalse(viewModel.uiState.value.showCloudPairDialog)
    }

    @Test
    fun `closeCloudEmailPasswordDialog dismisses dialog cleanly without getting stuck`() {
        viewModel.openCloudEmailPasswordDialog()
        assertTrue(viewModel.uiState.value.showCloudEmailPasswordDialog)

        viewModel.closeCloudEmailPasswordDialog()
        assertFalse(viewModel.uiState.value.showCloudEmailPasswordDialog)
        assertFalse(viewModel.uiState.value.isCloudAuthWorking)
    }

    @Test
    fun `cancelCloudAuth clears all active cloud pairing and dialog state`() {
        viewModel.openCloudEmailPasswordDialog()
        viewModel.cancelCloudAuth()

        assertFalse(viewModel.uiState.value.showCloudPairDialog)
        assertFalse(viewModel.uiState.value.showCloudEmailPasswordDialog)
        assertFalse(viewModel.uiState.value.isCloudAuthWorking)
        assertNull(viewModel.uiState.value.cloudUserCode)
        assertNull(viewModel.uiState.value.cloudVerificationUrl)
    }

    @Test
    fun `completeCloudAuthWithEmailPassword rejects empty password with toast error`() {
        every { context.getString(any()) } returns "Password required"
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "", false)

        assertTrue(viewModel.uiState.value.showToast)
        coVerify(exactly = 0) { authRepository.signIn(any(), any()) }
    }

    @Test
    fun `successful direct sign in pulls cloud state and updates isCloudConnected`() = runBlocking {
        coEvery { authRepository.signIn("user@example.com", "validPass123") } returns Result.success(Unit)
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.RESTORED
        every { context.getString(any()) } returns "Sync completed"

        viewModel.openCloudEmailPasswordDialog()
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)

        coVerify { authRepository.signIn("user@example.com", "validPass123") }
        coVerify { cloudSyncRepository.pullFromCloud(pushPendingLocalFirst = false) }
        assertTrue(viewModel.uiState.value.isCloudConnected)
        assertFalse(viewModel.uiState.value.showCloudEmailPasswordDialog)
    }
}

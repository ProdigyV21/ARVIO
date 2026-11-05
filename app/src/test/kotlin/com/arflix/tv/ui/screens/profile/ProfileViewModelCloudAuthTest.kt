package com.arflix.tv.ui.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.data.repository.*
import com.arflix.tv.ui.components.ToastType
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
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
        every { context.getString(any()) } answers { "message-${firstArg<Int>()}" }

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
        awaitAuthResult()

        coVerify { authRepository.signIn("user@example.com", "validPass123") }
        coVerify { cloudSyncRepository.pullFromCloud(pushPendingLocalFirst = false) }
        assertTrue(viewModel.uiState.value.isCloudConnected)
        assertFalse(viewModel.uiState.value.showCloudEmailPasswordDialog)
    }

    private suspend fun awaitAuthResult(): ProfileUiState = withTimeout(5_000L) {
        viewModel.uiState.first { !it.isCloudAuthWorking && it.showToast }
    }

    private fun signInSuccessfully() {
        coEvery { authRepository.signIn("user@example.com", "validPass123") } returns Result.success(Unit)
    }

    @Test fun `failed restore retains login and exposes retry instead of success`() = runBlocking {
        signInSuccessfully()
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.FAILED
        viewModel.openCloudEmailPasswordDialog()
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        val result = awaitAuthResult()
        assertTrue(result.isCloudConnected)
        assertTrue(result.cloudRestoreFailed)
        assertFalse(result.showCloudEmailPasswordDialog)
        assertEquals(ToastType.ERROR, result.toastType)
        assertEquals(context.getString(R.string.settings_cloud_signed_in_restore_failed), result.toastMessage)
        coVerify(exactly = 2) { cloudSyncRepository.pullFromCloud(false) }
    }

    @Test fun `restore retry recovers without signing in again`() = runBlocking {
        signInSuccessfully()
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.FAILED
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        assertTrue(awaitAuthResult().cloudRestoreFailed)
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.RESTORED
        viewModel.retryCloudRestore()
        val result = awaitAuthResult()
        assertFalse(result.cloudRestoreFailed)
        assertTrue(result.isCloudConnected)
        assertEquals(ToastType.SUCCESS, result.toastType)
        coVerify(exactly = 1) { authRepository.signIn(any(), any()) }
        coVerify(exactly = 3) { cloudSyncRepository.pullFromCloud(false) }
    }

    @Test fun `successful second restore attempt reports success`() = runBlocking {
        signInSuccessfully()
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returnsMany listOf(
            CloudSyncRepository.RestoreResult.FAILED, CloudSyncRepository.RestoreResult.RESTORED
        )
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        val result = awaitAuthResult()
        assertFalse(result.cloudRestoreFailed)
        assertEquals(ToastType.SUCCESS, result.toastType)
        coVerify(exactly = 2) { cloudSyncRepository.pullFromCloud(false) }
    }

    @Test fun `new account with no backup is not a failed restore`() = runBlocking {
        coEvery { authRepository.signUp("user@arvio.app", "validPass123") } returns Result.success(Unit)
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.NO_BACKUP
        viewModel.completeCloudAuthWithEmailPassword("user@arvio.app", "validPass123", true)
        val result = awaitAuthResult()
        assertFalse(result.cloudRestoreFailed)
        assertEquals(ToastType.SUCCESS, result.toastType)
        assertEquals(context.getString(R.string.settings_cloud_sync_complete_no_backup_toast), result.toastMessage)
        coVerify(exactly = 1) { authRepository.signUp("user@arvio.app", "validPass123") }
        coVerify(exactly = 1) { cloudSyncRepository.pullFromCloud(false) }
    }

    @Test fun `unexpected restore exception is reported and remains retryable`() = runBlocking {
        signInSuccessfully()
        coEvery { cloudSyncRepository.pullFromCloud(any()) } throws java.io.IOException("offline")
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        val result = awaitAuthResult()
        assertTrue(result.cloudRestoreFailed)
        assertEquals(ToastType.ERROR, result.toastType)
        assertFalse(result.isCloudAuthWorking)
    }

    @Test fun `cancelling pending login cancels request and does not restore`() = runBlocking {
        val cancelled = CompletableDeferred<Unit>()
        coEvery { authRepository.signIn(any(), any()) } coAnswers {
            try { awaitCancellation() } finally { cancelled.complete(Unit) }
        }
        viewModel.openCloudEmailPasswordDialog()
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        viewModel.closeCloudEmailPasswordDialog()
        withTimeout(5_000L) { cancelled.await() }
        assertFalse(viewModel.uiState.value.isCloudAuthWorking)
        assertFalse(viewModel.uiState.value.showCloudEmailPasswordDialog)
        coVerify(exactly = 0) { cloudSyncRepository.pullFromCloud(any()) }
    }

    @Test fun `late cancelled login cannot restore or dismiss a newer dialog`() = runBlocking {
        val response = CompletableDeferred<Result<Unit>>()
        val returned = CompletableDeferred<Unit>()
        coEvery { authRepository.signIn(any(), any()) } coAnswers {
            withContext(NonCancellable) { response.await().also { returned.complete(Unit) } }
        }
        try {
            viewModel.openCloudEmailPasswordDialog()
            viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
            viewModel.cancelCloudAuth()
            viewModel.openCloudEmailPasswordDialog()
            response.complete(Result.success(Unit))
            withTimeout(5_000L) { returned.await() }
            assertTrue(viewModel.uiState.value.showCloudEmailPasswordDialog)
            assertFalse(viewModel.uiState.value.isCloudConnected)
            coVerify(exactly = 0) { cloudSyncRepository.pullFromCloud(any()) }
        } finally {
            response.complete(Result.success(Unit))
        }
    }

    @Test fun `duplicate login submission is ignored while request is pending`() {
        coEvery { authRepository.signIn(any(), any()) } coAnswers { awaitCancellation() }
        repeat(2) { viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false) }
        coVerify(exactly = 1) { authRepository.signIn(any(), any()) }
        viewModel.cancelCloudAuth()
    }

    @Test fun `late cancelled pairing session cannot reopen QR dialog`() = runBlocking {
        val response = CompletableDeferred<Result<TvDeviceAuthSession>>()
        val returned = CompletableDeferred<Unit>()
        coEvery { tvDeviceAuthRepository.startSession() } coAnswers {
            withContext(NonCancellable) { response.await().also { returned.complete(Unit) } }
        }
        val session = TvDeviceAuthSession("CODE", "device", "https://auth.example/", 600, 1)
        try {
            viewModel.startCloudAuth()
            viewModel.cancelCloudAuth()
            response.complete(Result.success(session))
            withTimeout(5_000L) { returned.await() }
            assertFalse(viewModel.uiState.value.showCloudPairDialog)
            assertNull(viewModel.uiState.value.cloudUserCode)
            coVerify(exactly = 0) { tvDeviceAuthRepository.pollStatus(any()) }
        } finally {
            response.complete(Result.success(session))
        }
    }

    @Test fun `late cancelled approval cannot import tokens`() = runBlocking {
        val response = CompletableDeferred<Result<TvDeviceAuthStatus>>()
        val returned = CompletableDeferred<Unit>()
        coEvery { tvDeviceAuthRepository.startSession() } returns
            Result.success(TvDeviceAuthSession("CODE", "device", "https://auth.example/", 600, 1))
        coEvery { tvDeviceAuthRepository.pollStatus(any()) } coAnswers {
            withContext(NonCancellable) { response.await().also { returned.complete(Unit) } }
        }
        val approval = Result.success(TvDeviceAuthStatus(TvDeviceAuthStatusType.APPROVED, "access", "refresh"))
        try {
            viewModel.startCloudAuth()
            viewModel.cancelCloudAuth()
            response.complete(approval)
            withTimeout(5_000L) { returned.await() }
            coVerify(exactly = 0) { authRepository.signInWithSessionTokens(any(), any()) }
            coVerify(exactly = 0) { cloudSyncRepository.pullFromCloud(any()) }
        } finally {
            response.complete(approval)
        }
    }

    @Test fun `cancelling restore prevents retry and success publication`() = runBlocking {
        val started = CompletableDeferred<Unit>()
        val cancelled = CompletableDeferred<Unit>()
        signInSuccessfully()
        coEvery { cloudSyncRepository.pullFromCloud(any()) } coAnswers {
            started.complete(Unit)
            try { awaitCancellation() } finally { cancelled.complete(Unit) }
        }
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        withTimeout(5_000L) { started.await() }
        viewModel.cancelCloudAuth()
        withTimeout(5_000L) { cancelled.await() }
        assertFalse(viewModel.uiState.value.isCloudAuthWorking)
        assertFalse(viewModel.uiState.value.showToast)
        coVerify(exactly = 1) { cloudSyncRepository.pullFromCloud(false) }
    }

    @Test fun `auth observer does not duplicate explicit login restore`() = runBlocking {
        coEvery { authRepository.signIn(any(), any()) } coAnswers {
            authStateFlow.value = AuthState.Authenticated("account", "user@example.com", null)
            Result.success(Unit)
        }
        coEvery { cloudSyncRepository.pullFromCloud(any()) } returns CloudSyncRepository.RestoreResult.RESTORED
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        awaitAuthResult()
        coVerify(exactly = 1) { cloudSyncRepository.pullFromCloud(false) }
    }

    @Test fun `clearing ViewModel cancels pending authentication`() = runBlocking {
        val cancelled = CompletableDeferred<Unit>()
        coEvery { authRepository.signIn(any(), any()) } coAnswers {
            try { awaitCancellation() } finally { cancelled.complete(Unit) }
        }
        viewModel.completeCloudAuthWithEmailPassword("user@example.com", "validPass123", false)
        store.clear()
        withTimeout(5_000L) { cancelled.await() }
        coVerify(exactly = 0) { cloudSyncRepository.pullFromCloud(any()) }
    }
}

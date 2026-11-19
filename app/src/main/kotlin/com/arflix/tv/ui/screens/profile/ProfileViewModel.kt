package com.arflix.tv.ui.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.data.model.KidsAgeLevel
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.ProfileColors
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.ProfileAvatarImageManager
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.TvDeviceAuthRepository
import com.arflix.tv.data.repository.TvDeviceAuthSession
import com.arflix.tv.data.repository.TvDeviceAuthStatusType
import com.arflix.tv.ui.components.ToastType
import com.arflix.tv.util.AuthEmailValidator
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.PinUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

data class ProfileUiState(
    val profiles: List<Profile> = emptyList(),
    val activeProfile: Profile? = null,
    val isLoading: Boolean = true,
    val isSwitchingProfile: Boolean = false,
    val isManageMode: Boolean = false,
    // Add profile dialog state
    val showAddDialog: Boolean = false,
    val newProfileName: String = "",
    val selectedColorIndex: Int = 0,
    val selectedAvatarId: Int = 0, // 0 = legacy letter, 1-24 = cartoon avatar
    val selectedAvatarImageUri: String? = null,
    val useCustomAvatarImage: Boolean = false,
    val isKidsProfile: Boolean = false,
    val kidsAgeLevel: KidsAgeLevel = KidsAgeLevel.DEFAULT,
    // Hashed parent PIN chosen in this dialog, before the profile is saved with it.
    val pendingParentPin: String? = null,
    // Edit profile dialog state
    val editingProfile: Profile? = null,
    // Toast state
    val toastMessage: String? = null,
    val toastType: ToastType = ToastType.SUCCESS,
    val showToast: Boolean = false,
    // PIN dialog state
    val showPinDialog: Boolean = false,
    val pinDialogMode: String = "", // "verify" or "setup"
    val pendingProfileForPin: Profile? = null,
    val pinContext: String = "", // "select", "edit", or "delete"
    val pinError: String = "", // Error message for wrong PIN
    // Cloud auth state
    val showCloudPairDialog: Boolean = false,
    val showCloudEmailPasswordDialog: Boolean = false,
    val cloudVerificationUrl: String? = null,
    val cloudUserCode: String? = null,
    val isCloudAuthWorking: Boolean = false,
    val isCloudConnected: Boolean = false,
    val cloudRestoreFailed: Boolean = false
)

internal fun ProfileUiState.canFinishSelection(targetProfileId: String?): Boolean =
    targetProfileId != null && activeProfile?.id == targetProfileId &&
        !isSwitchingProfile && !isManageMode && !showPinDialog

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val profileManager: ProfileManager,
    private val traktRepository: TraktRepository,
    private val watchHistoryRepository: WatchHistoryRepository,
    private val watchlistRepository: WatchlistRepository,
    private val iptvRepository: IptvRepository,
    private val profileAvatarImageManager: ProfileAvatarImageManager,
    private val cloudSyncRepository: CloudSyncRepository,
    private val tvDeviceAuthRepository: TvDeviceAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
    private var lastInitialRestoreUserId: String? = null

    private var cloudDeviceCode: String? = null
    private var cloudUserCode: String? = null
    private var cloudVerificationUrl: String? = null
    private var cloudPollIntervalMs: Long = 800L
    private var cloudExpiresAtMs: Long = 0L
    private var cloudPollingJob: Job? = null
    private var cloudAuthJob: Job? = null

    init {
        loadProfiles()
        observeProfiles()
        observeAuthState()
        restoreCloudProfilesForFreshLogin()
    }

    private fun loadProfiles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val profiles = profileRepository.getProfiles()
            val activeProfile = profileRepository.getActiveProfile()
            _uiState.value = _uiState.value.copy(
                profiles = profiles,
                activeProfile = activeProfile,
                isLoading = false
            )
        }
    }

    private fun observeProfiles() {
        viewModelScope.launch {
            profileRepository.profiles.collect { profiles ->
                _uiState.value = _uiState.value.copy(
                    profiles = profiles,
                    isLoading = _uiState.value.isLoading && profiles.isEmpty()
                )
            }
        }
        viewModelScope.launch {
            profileRepository.activeProfile.collect { profile ->
                _uiState.value = _uiState.value.copy(activeProfile = profile)
            }
        }
    }

    private fun restoreCloudProfilesForFreshLogin() {
        viewModelScope.launch {
            authRepository.authState.collect { state ->
                if (state !is AuthState.Authenticated) {
                    lastInitialRestoreUserId = null
                    if (_uiState.value.isLoading) {
                        loadProfiles()
                    }
                    return@collect
                }

                val userId = state.userId
                if (lastInitialRestoreUserId == userId) {
                    return@collect
                }
                // The explicit login flow owns its restore and reports its result.
                if (_uiState.value.isCloudAuthWorking) {
                    lastInitialRestoreUserId = userId
                    return@collect
                }
                if (cloudSyncRepository.hasMeaningfulLocalProfiles()) {
                    return@collect
                }

                lastInitialRestoreUserId = userId
                // Keep profile selector responsive while cloud restore runs. If we
                // already have local profiles, only show them first and restore in
                // the background.
                val hasLocalProfiles = runCatching { profileRepository.getProfiles() }
                    .getOrNull()
                    ?.isNotEmpty() == true
                if (!hasLocalProfiles) {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }

                val restoreResult = pullCloudProfilesWithRetry(timeoutMs = 18_000L)

                val profiles = profileRepository.getProfiles()
                val activeProfile = profileRepository.getActiveProfile()
                _uiState.value = _uiState.value.copy(
                    profiles = profiles,
                    activeProfile = activeProfile,
                    isLoading = false,
                    cloudRestoreFailed = restoreResult == CloudSyncRepository.RestoreResult.FAILED
                )
            }
        }
    }

    private fun showToast(message: String, type: ToastType = ToastType.SUCCESS) {
        _uiState.value = _uiState.value.copy(
            toastMessage = message,
            toastType = type,
            showToast = true
        )
        viewModelScope.launch {
            delay(3500)
            _uiState.value = _uiState.value.copy(showToast = false)
        }
    }

    fun dismissToast() {
        _uiState.value = _uiState.value.copy(showToast = false)
    }

    /**
     * Preload Continue Watching data when a profile is focused (before selection).
     * This enables instant display when the user actually selects the profile.
     */
    fun preloadForProfile(profile: Profile) {
        viewModelScope.launch {
            traktRepository.preloadContinueWatchingForProfile(profile.id)
        }
    }

    fun selectProfile(profile: Profile) {
        if (_uiState.value.isSwitchingProfile) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isSwitchingProfile = true
            )
            try {
                val previousProfileId = withContext(Dispatchers.IO) {
                    profileRepository.getActiveProfileId()
                }
                val isSameProfile = previousProfileId == profile.id
                withContext(Dispatchers.Default) {
                    // CRITICAL: Clear ALL profile caches BEFORE switching to ensure complete isolation.
                    // Keep this off the main thread; some profiles carry enough data here to stall
                    // touch devices during the profile tap transition.
                    if (!isSameProfile) {
                        traktRepository.clearAllProfileCaches()
                        watchHistoryRepository.clearProfileCaches()
                        watchlistRepository.clearWatchlistCache()
                        iptvRepository.invalidateCache()
                    }
                }

                // Update ProfileManager cache immediately so profile-scoped keys are correct
                // for any work started after selection.
                profileManager.setCurrentProfileId(profile.id)
                profileManager.setCurrentProfileName(profile.name)

                // Activate any preloaded Continue Watching cache for instant Home population.
                traktRepository.activatePreloadedCache(profile.id)

                // Persist only actual profile changes. Re-selecting the current
                // profile used to mark the full cloud payload dirty on every launch.
                if (!isSameProfile) {
                    withContext(Dispatchers.IO) {
                        profileRepository.setActiveProfile(profile.id)
                    }
                }

                _uiState.value = _uiState.value.copy(activeProfile = profile)

                viewModelScope.launch(Dispatchers.IO) {
                    if (profileRepository.getActiveProfileId() != profile.id) return@launch
                    runCatching { iptvRepository.warmupFromCacheOnly() }
                }

                // Home performs one throttled cloud pull after its local snapshot is
                // visible. Doing two pulls plus a forced megabyte-sized push here made
                // catalog loading compete with cloud serialization and network traffic.

                // Defer network parsing to keep initial Home navigation smooth. The
                // cache-only warmup above is safe to run immediately because it never
                // touches the provider.
                viewModelScope.launch(Dispatchers.IO) {
                    delay(45_000L)
                    if (profileRepository.getActiveProfileId() != profile.id) return@launch
                    runCatching {
                        iptvRepository.prefetchFreshStartupData()
                    }
                }
            } finally {
                _uiState.value = _uiState.value.copy(isSwitchingProfile = false)
            }
        }
    }

    fun switchProfile() {
        // Clear all caches when leaving a profile to prevent data leakage
        traktRepository.clearAllProfileCaches()
        watchHistoryRepository.clearProfileCaches()
        watchlistRepository.clearWatchlistCache()
        iptvRepository.invalidateCache()

        viewModelScope.launch {
            profileRepository.clearActiveProfile()
        }
    }

    // ========== Add Profile ==========

    fun showAddDialog() {
        _uiState.value = _uiState.value.copy(
            showAddDialog = true,
            newProfileName = "",
            selectedColorIndex = (_uiState.value.profiles.size) % ProfileColors.colors.size,
            selectedAvatarId = 0,
            selectedAvatarImageUri = null,
            useCustomAvatarImage = false,
            isKidsProfile = false,
            kidsAgeLevel = KidsAgeLevel.DEFAULT,
            pendingParentPin = null
        )
    }

    fun hideAddDialog() {
        _uiState.value = _uiState.value.copy(showAddDialog = false)
    }

    fun setNewProfileName(name: String) {
        _uiState.value = _uiState.value.copy(newProfileName = name)
    }

    fun setSelectedColorIndex(index: Int) {
        _uiState.value = _uiState.value.copy(
            selectedColorIndex = index,
            selectedAvatarId = 0,
            selectedAvatarImageUri = null,
            useCustomAvatarImage = false
        )
    }

    fun setSelectedAvatarId(id: Int) {
        _uiState.value = _uiState.value.copy(
            selectedAvatarId = id,
            selectedAvatarImageUri = null,
            useCustomAvatarImage = false
        )
    }

    fun setSelectedAvatarImage(uri: String) {
        _uiState.value = _uiState.value.copy(
            selectedAvatarImageUri = uri,
            useCustomAvatarImage = true,
            selectedAvatarId = 0
        )
    }

    fun removeSelectedAvatarImage() {
        _uiState.value = _uiState.value.copy(
            selectedAvatarImageUri = null,
            useCustomAvatarImage = false
        )
    }

    fun setKidsProfile(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(isKidsProfile = enabled)
    }

    fun setKidsAgeLevel(level: KidsAgeLevel) {
        _uiState.value = _uiState.value.copy(kidsAgeLevel = level)
    }

    fun createProfile() {
        val state = _uiState.value
        if (state.newProfileName.isBlank()) return
        if (state.isKidsProfile && state.pendingParentPin == null) {
            requestParentPinSetup(PIN_CONTEXT_PARENT_SETUP_CREATE)
            return
        }

        viewModelScope.launch {
            val profile = profileRepository.createProfile(
                name = state.newProfileName.trim(),
                avatarColor = ProfileColors.getByIndex(state.selectedColorIndex),
                avatarId = state.selectedAvatarId,
                isKidsProfile = state.isKidsProfile,
                kidsAgeLevel = state.kidsAgeLevel.name.takeIf { state.isKidsProfile },
                parentPin = state.pendingParentPin.takeIf { state.isKidsProfile }
            )
            val selectedImage = state.selectedAvatarImageUri
            if (state.useCustomAvatarImage && !selectedImage.isNullOrBlank()) {
                runCatching {
                    profileAvatarImageManager.importAvatar(profile.id, selectedImage)
                }.onSuccess { imported ->
                    profileRepository.updateProfile(
                        profile.copy(
                            avatarId = 0,
                            avatarImageVersion = imported.version,
                            avatarImageStoragePath = imported.storagePath
                        )
                    )
                }.onFailure {
                    showToast(context.getString(R.string.profile_avatar_import_failed), ToastType.ERROR)
                }
            }
            _uiState.value = _uiState.value.copy(showAddDialog = false, pendingParentPin = null)
            showToast(context.getString(R.string.profile_created_success), ToastType.SUCCESS)
            runCatching { cloudSyncRepository.pushToCloud(force = true) }
        }
    }

    // ========== Edit Profile ==========

    fun showEditDialog(profile: Profile) {
        // A kids profile's restrictions belong to the parent: its parent PIN, not access to the
        // profile itself, opens the editor (where kids mode and the age limit can change).
        if (profile.isKidsProfile && !profile.parentPin.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                showPinDialog = true,
                pinDialogMode = "verify",
                pendingProfileForPin = profile,
                pinContext = PIN_CONTEXT_PARENT_EDIT
            )
        } else if (profile.isLocked && !profile.pin.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                showPinDialog = true,
                pinDialogMode = "verify",
                pendingProfileForPin = profile,
                pinContext = "edit"
            )
        } else {
            _uiState.value = _uiState.value.copy(
                editingProfile = profile,
                newProfileName = profile.name,
                selectedColorIndex = ProfileColors.colors.indexOf(profile.avatarColor).takeIf { it >= 0 } ?: 0,
                selectedAvatarId = profile.avatarId,
                selectedAvatarImageUri = null,
                useCustomAvatarImage = profile.avatarImageVersion > 0L,
                isKidsProfile = profile.isKidsProfile,
                kidsAgeLevel = KidsAgeLevel.fromName(profile.kidsAgeLevel),
                pendingParentPin = null
            )
        }
    }

    fun hideEditDialog() {
        _uiState.value = _uiState.value.copy(editingProfile = null)
    }

    fun updateProfile() {
        val state = _uiState.value
        val editing = state.editingProfile ?: return
        if (state.newProfileName.isBlank()) return
        if (state.isKidsProfile && editing.parentPin.isNullOrEmpty() && state.pendingParentPin == null) {
            requestParentPinSetup(PIN_CONTEXT_PARENT_SETUP_EDIT)
            return
        }

        viewModelScope.launch {
            var updatedProfile = editing.copy(
                name = state.newProfileName.trim(),
                avatarColor = ProfileColors.getByIndex(state.selectedColorIndex),
                avatarId = state.selectedAvatarId,
                isKidsProfile = state.isKidsProfile,
                kidsAgeLevel = state.kidsAgeLevel.name.takeIf { state.isKidsProfile },
                // Turning kids mode off (reachable only through the parent PIN) drops the PIN.
                parentPin = if (state.isKidsProfile) state.pendingParentPin ?: editing.parentPin else null
            )
            val selectedImage = state.selectedAvatarImageUri
            if (state.useCustomAvatarImage && !selectedImage.isNullOrBlank()) {
                runCatching {
                    profileAvatarImageManager.importAvatar(editing.id, selectedImage)
                }.onSuccess { imported ->
                    updatedProfile = updatedProfile.copy(
                        avatarId = 0,
                        avatarImageVersion = imported.version,
                        avatarImageStoragePath = imported.storagePath
                    )
                }.onFailure {
                    showToast(context.getString(R.string.profile_avatar_import_failed), ToastType.ERROR)
                    return@launch
                }
            } else if (!state.useCustomAvatarImage) {
                profileAvatarImageManager.clearLocalAvatar(editing.id)
                updatedProfile = updatedProfile.copy(
                    avatarImageVersion = 0L,
                    avatarImageStoragePath = null
                )
            }

            profileRepository.updateProfile(updatedProfile)
            val updatedProfiles = _uiState.value.profiles.map {
                if (it.id == updatedProfile.id) updatedProfile else it
            }
            _uiState.value = _uiState.value.copy(
                editingProfile = null,
                profiles = updatedProfiles,
                pendingParentPin = null
            )
            showToast(context.getString(R.string.profile_updated), ToastType.SUCCESS)
            runCatching { cloudSyncRepository.pushToCloud(force = true) }
        }
    }

    // ========== Manage Mode ==========

    fun toggleManageMode() {
        _uiState.value = _uiState.value.copy(isManageMode = !_uiState.value.isManageMode)
    }

    fun exitManageMode() {
        _uiState.value = _uiState.value.copy(isManageMode = false)
    }

    fun deleteProfile(profile: Profile) {
        if (profile.isKidsProfile && !profile.parentPin.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                showPinDialog = true,
                pinDialogMode = "verify",
                pendingProfileForPin = profile,
                pinContext = PIN_CONTEXT_PARENT_DELETE
            )
        } else if (profile.isLocked && !profile.pin.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                showPinDialog = true,
                pinDialogMode = "verify",
                pendingProfileForPin = profile,
                pinContext = "delete"
            )
        } else {
            performDeleteProfile(profile)
        }
    }

    private fun performDeleteProfile(profile: Profile) {
        viewModelScope.launch {
            val activeId = _uiState.value.activeProfile?.id
            profileRepository.deleteProfile(profile.id)
            showToast(context.getString(R.string.profile_deleted), ToastType.SUCCESS)
            if (activeId == profile.id) {
                traktRepository.clearAllProfileCaches()
                watchHistoryRepository.clearProfileCaches()
                watchlistRepository.clearWatchlistCache()
                iptvRepository.invalidateCache()
                profileManager.setCurrentProfileId("default")
                profileManager.setCurrentProfileName("default")
            }
            runCatching { cloudSyncRepository.pushToCloud(force = true) }
        }
    }

    // ========== PIN Management ==========

    fun selectProfileWithLockCheck(profile: Profile) {
        if (profile.isLocked && !profile.pin.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                showPinDialog = true,
                pinDialogMode = "verify",
                pendingProfileForPin = profile,
                pinContext = "select"
            )
        } else {
            selectProfile(profile)
        }
    }

    fun showPinSetupDialog() {
        _uiState.value = _uiState.value.copy(
            showPinDialog = true,
            pinDialogMode = "setup"
        )
    }

    fun hidePinDialog() {
        _uiState.value = _uiState.value.copy(
            showPinDialog = false,
            pinDialogMode = "",
            pendingProfileForPin = null,
            pinContext = "",
            pinError = ""
        )
    }

    fun verifyPinAndSelectProfile(enteredPin: String) {
        val profile = _uiState.value.pendingProfileForPin ?: return
        val context = _uiState.value.pinContext
        val expected = if (context.startsWith(PIN_CONTEXT_PARENT_PREFIX)) profile.parentPin else profile.pin
        if (PinUtil.verifyPin(enteredPin, expected)) {
            hidePinDialog()
            // Handle PIN verification context
            when (context) {
                "edit", PIN_CONTEXT_PARENT_EDIT -> {
                    // Open edit dialog for locked profile
                    _uiState.value = _uiState.value.copy(
                        editingProfile = profile,
                        newProfileName = profile.name,
                        selectedColorIndex = ProfileColors.colors.indexOf(profile.avatarColor).takeIf { it >= 0 } ?: 0,
                        selectedAvatarId = profile.avatarId,
                        selectedAvatarImageUri = null,
                        useCustomAvatarImage = profile.avatarImageVersion > 0L,
                        isKidsProfile = profile.isKidsProfile,
                        kidsAgeLevel = KidsAgeLevel.fromName(profile.kidsAgeLevel),
                        pendingParentPin = null
                    )
                }
                "delete", PIN_CONTEXT_PARENT_DELETE -> {
                    // Delete locked profile after PIN verification
                    performDeleteProfile(profile)
                }
                else -> {
                    // "select": select the locked profile
                    selectProfile(profile)
                }
            }
        } else {
            // PIN incorrect - show error message in dialog
            _uiState.value = _uiState.value.copy(pinError = "")
            _uiState.value = _uiState.value.copy(
                pinError = "Incorrect PIN. Please try again."
            )
        }
    }

    private fun requestParentPinSetup(context: String) {
        _uiState.value = _uiState.value.copy(
            showPinDialog = true,
            pinDialogMode = "setup",
            pinContext = context
        )
    }

    /** Parent PIN chosen while saving a kids profile; the save then continues with it. */
    fun setupParentPin(pin: String) {
        val context = _uiState.value.pinContext
        hidePinDialog()
        _uiState.value = _uiState.value.copy(pendingParentPin = PinUtil.hashPin(pin))
        when (context) {
            PIN_CONTEXT_PARENT_SETUP_CREATE -> createProfile()
            PIN_CONTEXT_PARENT_SETUP_EDIT -> updateProfile()
        }
    }

    fun setupProfilePin(pin: String) {
        val profile = _uiState.value.editingProfile ?: return
        // Hash the PIN before storing
        val hashedPin = PinUtil.hashPin(pin)
        val updatedProfile = profile.copy(pin = hashedPin, isLocked = true)
        viewModelScope.launch {
            profileRepository.updateProfile(updatedProfile)
            val updatedProfiles = _uiState.value.profiles.map {
                if (it.id == updatedProfile.id) updatedProfile else it
            }
            _uiState.value = _uiState.value.copy(
                editingProfile = updatedProfile,
                profiles = updatedProfiles
            )
            hidePinDialog()
            showToast(context.getString(R.string.profile_pin_set_success), ToastType.SUCCESS)
            runCatching { cloudSyncRepository.pushToCloud(force = true) }
        }
    }

    fun removeProfilePin() {
        val profile = _uiState.value.editingProfile ?: return
        val updatedProfile = profile.copy(pin = null, isLocked = false)
        viewModelScope.launch {
            profileRepository.updateProfile(updatedProfile)
            val updatedProfiles = _uiState.value.profiles.map {
                if (it.id == updatedProfile.id) updatedProfile else it
            }
            _uiState.value = _uiState.value.copy(
                editingProfile = updatedProfile,
                profiles = updatedProfiles
            )
            showToast(context.getString(R.string.profile_pin_removed_success), ToastType.SUCCESS)
            runCatching { cloudSyncRepository.pushToCloud(force = true) }
        }
    }

    private fun observeAuthState() {
        viewModelScope.launch {
            authRepository.authState.collect { authState ->
                val isAuth = authState is AuthState.Authenticated
                _uiState.update {
                    it.copy(isCloudConnected = isAuth, cloudRestoreFailed = it.cloudRestoreFailed && isAuth)
                }
            }
        }
    }

    fun startCloudAuth() {
        if (_uiState.value.isCloudConnected || _uiState.value.isCloudAuthWorking || cloudAuthJob?.isActive == true) return
        _uiState.update { it.copy(isCloudAuthWorking = true, showToast = false) }
        cloudAuthJob = viewModelScope.launch {
            val result = ensureCloudAuthSession(startPolling = true)
            currentCoroutineContext().ensureActive()
            result
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            isCloudAuthWorking = false,
                            showCloudPairDialog = true,
                            cloudUserCode = cloudUserCode,
                            cloudVerificationUrl = cloudVerificationUrl
                        )
                    }
                }
                .onFailure { error ->
                    clearCloudAuthSession()
                    _uiState.update {
                        it.copy(
                            isCloudAuthWorking = false,
                            toastMessage = error.message ?: context.getString(R.string.cloud_login_failed_start),
                            toastType = ToastType.ERROR,
                            showToast = true
                        )
                    }
                }
        }
    }

    fun openCloudEmailPasswordDialog() {
        if (_uiState.value.isCloudConnected) return
        _uiState.update {
            it.copy(
                showCloudPairDialog = false,
                showCloudEmailPasswordDialog = true,
                isCloudAuthWorking = false
            )
        }
    }

    fun closeCloudEmailPasswordDialog() {
        cancelCloudAuth()
    }

    fun cancelCloudAuth() {
        cloudAuthJob?.cancel()
        cloudAuthJob = null
        clearCloudAuthSession()
        _uiState.update {
            it.copy(
                showCloudPairDialog = false,
                cloudUserCode = null,
                cloudVerificationUrl = null,
                showCloudEmailPasswordDialog = false,
                isCloudAuthWorking = false
            )
        }
    }

    fun completeCloudAuthWithEmailPassword(
        email: String,
        password: String,
        createAccount: Boolean
    ) {
        if (cloudAuthJob?.isActive == true) return
        val trimmedEmail = AuthEmailValidator.normalize(email)
        AuthEmailValidator.validate(trimmedEmail, rejectDisposable = createAccount)?.let { messageRes ->
            showToast(context.getString(messageRes), ToastType.ERROR)
            return
        }
        if (password.isBlank()) {
            showToast(context.getString(R.string.settings_cloud_password_required), ToastType.ERROR)
            return
        }

        _uiState.update { it.copy(isCloudAuthWorking = true, showToast = false) }
        cloudAuthJob = viewModelScope.launch {
            val userCode = cloudUserCode
            if (!userCode.isNullOrBlank()) {
                val result = tvDeviceAuthRepository.completeWithEmailPassword(
                    userCode = userCode,
                    email = trimmedEmail,
                    password = password,
                    intent = if (createAccount) "signup" else "signin"
                )
                currentCoroutineContext().ensureActive()
                result.onSuccess {
                    showToast(context.getString(R.string.settings_waiting_for_approval), ToastType.INFO)
                    _uiState.update {
                        it.copy(
                            showCloudEmailPasswordDialog = false,
                            isCloudAuthWorking = true
                        )
                    }
                    startCloudPolling()
                }.onFailure { error ->
                    showToast(error.message ?: context.getString(R.string.tv_link_failed), ToastType.ERROR)
                    _uiState.update { it.copy(isCloudAuthWorking = false) }
                }
            } else {
                val result = if (createAccount) {
                    authRepository.signUp(trimmedEmail, password)
                } else {
                    authRepository.signIn(trimmedEmail, password)
                }
                currentCoroutineContext().ensureActive()
                if (result.isSuccess) {
                    onCloudAuthSuccessful()
                } else {
                    showToast(
                        result.exceptionOrNull()?.message ?: context.getString(R.string.auth_signin_failed),
                        ToastType.ERROR
                    )
                    _uiState.update { it.copy(isCloudAuthWorking = false) }
                }
            }
        }
    }

    private fun startCloudPolling() {
        val deviceCode = cloudDeviceCode ?: return
        cloudPollingJob?.cancel()
        cloudPollingJob = viewModelScope.launch {
            _uiState.update { it.copy(isCloudAuthWorking = true) }
            val now = System.currentTimeMillis()
            val intervalMs = cloudPollIntervalMs.coerceIn(500L, 3_000L)
            val hardDeadline = now + 10 * 60_000L
            val deadline = listOf(
                cloudExpiresAtMs.takeIf { it > 0L } ?: (now + 60_000L),
                hardDeadline
            ).minOrNull() ?: hardDeadline

            while (System.currentTimeMillis() < deadline) {
                val status = tvDeviceAuthRepository.pollStatus(deviceCode).getOrNull()
                currentCoroutineContext().ensureActive()
                when (status?.status) {
                    TvDeviceAuthStatusType.PENDING -> Unit
                    TvDeviceAuthStatusType.APPROVED -> {
                        _uiState.update { it.copy(isCloudAuthWorking = true) }
                        val access = status.accessToken
                        val refresh = status.refreshToken
                        if (access.isNullOrBlank() || refresh.isNullOrBlank()) {
                            showToast(
                                status.message ?: context.getString(R.string.tv_link_approved_no_tokens),
                                ToastType.ERROR
                            )
                            _uiState.update { it.copy(isCloudAuthWorking = false) }
                            return@launch
                        }

                        val tokenImport = authRepository.signInWithSessionTokens(access, refresh)
                        currentCoroutineContext().ensureActive()
                        if (tokenImport.isSuccess) {
                            onCloudAuthSuccessful()
                        } else {
                            showToast(context.getString(R.string.tv_link_failed), ToastType.ERROR)
                            _uiState.update { it.copy(isCloudAuthWorking = false) }
                        }
                        return@launch
                    }
                    TvDeviceAuthStatusType.EXPIRED,
                    TvDeviceAuthStatusType.ERROR -> {
                        clearCloudAuthSession()
                        showToast(
                            status.message ?: context.getString(R.string.tv_link_failed),
                            ToastType.ERROR
                        )
                        _uiState.update {
                            it.copy(
                                isCloudAuthWorking = false,
                                showCloudPairDialog = false,
                                showCloudEmailPasswordDialog = false,
                                cloudUserCode = null,
                                cloudVerificationUrl = null
                            )
                        }
                        return@launch
                    }
                    null -> Unit
                }
                delay(intervalMs)
            }

            clearCloudAuthSession()
            showToast(context.getString(R.string.settings_cloud_signin_incomplete), ToastType.ERROR)
            _uiState.update {
                it.copy(
                    isCloudAuthWorking = false,
                    showCloudPairDialog = false,
                    showCloudEmailPasswordDialog = false,
                    cloudUserCode = null,
                    cloudVerificationUrl = null
                )
            }
        }
    }

    fun retryCloudRestore() {
        if (!_uiState.value.isCloudConnected || _uiState.value.isCloudAuthWorking || cloudAuthJob?.isActive == true) return
        _uiState.update { it.copy(isCloudAuthWorking = true, showToast = false) }
        cloudAuthJob = viewModelScope.launch { onCloudAuthSuccessful() }
    }

    private suspend fun pullCloudProfilesWithRetry(timeoutMs: Long = 15_000L): CloudSyncRepository.RestoreResult =
        withContext(Dispatchers.IO) {
            repeat(2) { attempt ->
                val result = try {
                    withTimeoutOrNull(timeoutMs) {
                        cloudSyncRepository.pullFromCloud(pushPendingLocalFirst = false)
                    } ?: CloudSyncRepository.RestoreResult.FAILED
                } catch (error: CancellationException) {
                    throw error
                } catch (error: Exception) {
                    AppLogger.e("ProfileViewModel", "Cloud restore failed", error)
                    CloudSyncRepository.RestoreResult.FAILED
                }
                if (result != CloudSyncRepository.RestoreResult.FAILED) return@withContext result
                if (attempt == 0) delay(1_200L)
            }
            CloudSyncRepository.RestoreResult.FAILED
        }

    private suspend fun onCloudAuthSuccessful() {
        currentCoroutineContext().ensureActive()
        val restoreResult = pullCloudProfilesWithRetry()
        currentCoroutineContext().ensureActive()
        clearCloudAuthSession(cancelPolling = false)
        val profiles = profileRepository.getProfiles()
        val activeProfile = profileRepository.getActiveProfile()
        currentCoroutineContext().ensureActive()
        _uiState.update {
            it.copy(
                isCloudAuthWorking = false,
                showCloudPairDialog = false,
                showCloudEmailPasswordDialog = false,
                cloudUserCode = null,
                cloudVerificationUrl = null,
                profiles = profiles,
                activeProfile = activeProfile,
                isCloudConnected = true,
                cloudRestoreFailed = restoreResult == CloudSyncRepository.RestoreResult.FAILED
            )
        }
        when (restoreResult) {
            CloudSyncRepository.RestoreResult.RESTORED ->
                showToast(context.getString(R.string.settings_cloud_sync_complete), ToastType.SUCCESS)
            CloudSyncRepository.RestoreResult.NO_BACKUP ->
                showToast(context.getString(R.string.settings_cloud_sync_complete_no_backup_toast), ToastType.SUCCESS)
            CloudSyncRepository.RestoreResult.FAILED ->
                showToast(context.getString(R.string.settings_cloud_signed_in_restore_failed), ToastType.ERROR)
        }
    }

    private fun hasActiveCloudAuthSession(): Boolean {
        val hasCodes = !cloudDeviceCode.isNullOrBlank() && !cloudUserCode.isNullOrBlank()
        if (!hasCodes) return false
        return cloudExpiresAtMs <= 0L || System.currentTimeMillis() < cloudExpiresAtMs
    }

    private fun applyCloudAuthSession(session: TvDeviceAuthSession) {
        cloudDeviceCode = session.deviceCode
        cloudUserCode = session.userCode
        cloudVerificationUrl = session.verificationUrl
        cloudPollIntervalMs = (session.intervalSeconds.coerceIn(1, 10) * 1000L)
        cloudExpiresAtMs = System.currentTimeMillis() + (session.expiresInSeconds.coerceAtLeast(30) * 1000L)
    }

    private fun clearCloudAuthSession(cancelPolling: Boolean = true) {
        cloudDeviceCode = null
        cloudUserCode = null
        cloudVerificationUrl = null
        cloudPollIntervalMs = 800L
        cloudExpiresAtMs = 0L
        if (cancelPolling) {
            cloudPollingJob?.cancel()
        }
        cloudPollingJob = null
    }

    private suspend fun ensureCloudAuthSession(startPolling: Boolean): Result<Unit> {
        if (hasActiveCloudAuthSession()) {
            if (startPolling && cloudPollingJob?.isActive != true) {
                startCloudPolling()
            }
            return Result.success(Unit)
        }

        clearCloudAuthSession()
        val result = tvDeviceAuthRepository.startSession()
        currentCoroutineContext().ensureActive()
        return result.map { session ->
            applyCloudAuthSession(session)
            if (startPolling) {
                startCloudPolling()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cloudAuthJob?.cancel()
        clearCloudAuthSession()
    }

    companion object {
        const val PIN_CONTEXT_PARENT_PREFIX = "parent_"
        const val PIN_CONTEXT_PARENT_EDIT = "parent_edit"
        const val PIN_CONTEXT_PARENT_DELETE = "parent_delete"
        const val PIN_CONTEXT_PARENT_SETUP_CREATE = "parent_setup_create"
        const val PIN_CONTEXT_PARENT_SETUP_EDIT = "parent_setup_edit"
    }
}

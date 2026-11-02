package com.arflix.tv.ui.screens.profile

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.KidsAgeLevel
import com.arflix.tv.ui.components.ProfileAvatarVisual
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.PinUtil
import kotlinx.coroutines.delay

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ProfileSelectionScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onProfileSelected: (String) -> Unit,
    onShowAddProfile: () -> Unit,
    onConnectCloud: () -> Unit = {},
    isCloudConnected: Boolean = false
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileSelectionContent(uiState, ProfileSelectionActions(
        createProfile = viewModel::createProfile,
        deleteProfile = viewModel::deleteProfile,
        dismissToast = viewModel::dismissToast,
        hideAddDialog = viewModel::hideAddDialog,
        hideEditDialog = viewModel::hideEditDialog,
        hidePinDialog = viewModel::hidePinDialog,
        preloadForProfile = viewModel::preloadForProfile,
        removeProfilePin = viewModel::removeProfilePin,
        removeSelectedAvatarImage = viewModel::removeSelectedAvatarImage,
        selectProfile = viewModel::selectProfile,
        selectProfileWithLockCheck = viewModel::selectProfileWithLockCheck,
        setKidsAgeLevel = viewModel::setKidsAgeLevel,
        setKidsProfile = viewModel::setKidsProfile,
        setNewProfileName = viewModel::setNewProfileName,
        setSelectedAvatarId = viewModel::setSelectedAvatarId,
        setSelectedAvatarImage = viewModel::setSelectedAvatarImage,
        setSelectedColorIndex = viewModel::setSelectedColorIndex,
        setupParentPin = viewModel::setupParentPin,
        setupProfilePin = viewModel::setupProfilePin,
        showAddDialog = viewModel::showAddDialog,
        showEditDialog = viewModel::showEditDialog,
        showPinSetupDialog = viewModel::showPinSetupDialog,
        toggleManageMode = viewModel::toggleManageMode,
        updateProfile = viewModel::updateProfile,
        verifyPinAndSelectProfile = viewModel::verifyPinAndSelectProfile,
    ), onProfileSelected, onConnectCloud, isCloudConnected)
}

internal data class ProfileSelectionActions(
    val createProfile: () -> Unit = { },
    val deleteProfile: (Profile) -> Unit = { _ -> },
    val dismissToast: () -> Unit = { },
    val hideAddDialog: () -> Unit = { },
    val hideEditDialog: () -> Unit = { },
    val hidePinDialog: () -> Unit = { },
    val preloadForProfile: (Profile) -> Unit = { _ -> },
    val removeProfilePin: () -> Unit = { },
    val removeSelectedAvatarImage: () -> Unit = { },
    val selectProfile: (Profile) -> Unit = { _ -> },
    val selectProfileWithLockCheck: (Profile) -> Unit = { _ -> },
    val setKidsAgeLevel: (KidsAgeLevel) -> Unit = { _ -> },
    val setKidsProfile: (Boolean) -> Unit = { _ -> },
    val setNewProfileName: (String) -> Unit = { _ -> },
    val setSelectedAvatarId: (Int) -> Unit = { _ -> },
    val setSelectedAvatarImage: (String) -> Unit = { _ -> },
    val setSelectedColorIndex: (Int) -> Unit = { _ -> },
    val setupParentPin: (String) -> Unit = { _ -> },
    val setupProfilePin: (String) -> Unit = { _ -> },
    val showAddDialog: () -> Unit = { },
    val showEditDialog: (Profile) -> Unit = { _ -> },
    val showPinSetupDialog: () -> Unit = { },
    val toggleManageMode: () -> Unit = { },
    val updateProfile: () -> Unit = { },
    val verifyPinAndSelectProfile: (String) -> Unit = { _ -> },
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun ProfileSelectionContent(
    uiState: ProfileUiState,
    viewModel: ProfileSelectionActions,
    onProfileSelected: (String) -> Unit,
    onConnectCloud: () -> Unit = {},
    isCloudConnected: Boolean = false,
) {

    // Create focus requesters for each profile slot (max 5 profiles + 1 add button)
    val focusRequesters = remember { List(6) { FocusRequester() } }

    // Guard against Enter key events from previous screen (TV only — touch devices don't need this)
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var isReadyForInput by remember { mutableStateOf(isTouchDevice) }


    val context = LocalContext.current
    val motionEnabled = remember(context) {
        android.provider.Settings.Global.getFloat(context.contentResolver, android.provider.Settings.Global.ANIMATOR_DURATION_SCALE, 1f) > 0f
    }

    // Coordinate tracking & 3-step transition states
    var rootCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
    val avatarCoordinatesMap = remember { mutableMapOf<String, LayoutCoordinates>() }
    var transitionProfile by remember { mutableStateOf<Profile?>(null) }
    var initialDeltaOffset by remember { mutableStateOf(Offset.Zero) }
    var isTransitioning by remember { mutableStateOf(false) }
    var minAnimationCompleted by remember { mutableStateOf(false) }

    val transitionProgress by animateFloatAsState(
        targetValue = if (isTransitioning) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (motionEnabled) 320 else 0,
            easing = FastOutSlowInEasing
        ),
        label = "profile_transition_progress"
    )

    fun startTransitionForProfile(profile: Profile) {
        val coords = avatarCoordinatesMap[profile.id]
        val root = rootCoordinates
        if (coords != null && root != null && root.isAttached && coords.isAttached) {
            val avatarCenterInRoot = root.localPositionOf(coords, Offset(coords.size.width / 2f, coords.size.height / 2f))
            val rootCenter = Offset(root.size.width / 2f, root.size.height / 2f)
            initialDeltaOffset = Offset(
                x = avatarCenterInRoot.x - rootCenter.x,
                y = avatarCenterInRoot.y - rootCenter.y
            )
        } else {
            initialDeltaOffset = Offset.Zero
        }
        transitionProfile = profile
        isTransitioning = true
    }

    // Set ready for input after a short delay to ignore stray key events (TV only)
    LaunchedEffect(Unit) {
        if (!isTouchDevice) {
            delay(300)
            isReadyForInput = true
        }
    }

    // Reset input guard when dialogs close (TV only)
    LaunchedEffect(uiState.showAddDialog, uiState.editingProfile) {
        if (!isTouchDevice && !uiState.showAddDialog && uiState.editingProfile == null && isReadyForInput) {
            isReadyForInput = false
            delay(300)
            isReadyForInput = true
        }
    }

    // Cancel any running transition if entering manage mode or editing a profile
    LaunchedEffect(uiState.isManageMode, uiState.editingProfile) {
        if (uiState.isManageMode || uiState.editingProfile != null) {
            isTransitioning = false
            transitionProfile = null
        }
    }

    // Guarantee full completion of the expand/center animation before moving to Home
    LaunchedEffect(isTransitioning) {
        if (isTransitioning) {
            minAnimationCompleted = false
            delay(if (motionEnabled) 340L else 0L)
            minAnimationCompleted = true
        }
    }

    // Navigate to Home once the expand-to-center animation has finished AND profile data loading is complete
    LaunchedEffect(isTransitioning, minAnimationCompleted, uiState.isSwitchingProfile, uiState.activeProfile?.id,
        transitionProfile?.id, uiState.isManageMode, uiState.showPinDialog) {
        if (
            isTransitioning &&
            minAnimationCompleted &&
            uiState.canFinishSelection(transitionProfile?.id)
        ) {
            uiState.activeProfile?.id?.let(onProfileSelected)
        }
    }

    // Request focus on the first available item (profile or add button)
    LaunchedEffect(uiState.isLoading) {
        if (!uiState.isLoading) {
            delay(50)
            val targetIndex = if (uiState.profiles.isNotEmpty()) {
                uiState.activeProfile?.let { active ->
                    uiState.profiles.indexOfFirst { it.id == active.id }.takeIf { it >= 0 }
                } ?: 0
            } else {
                0 // Focus on Add Profile button
            }
            try {
                focusRequesters.getOrNull(targetIndex)?.requestFocus()
            } catch (e: IllegalStateException) {
                delay(100)
                try {
                    focusRequesters.getOrNull(targetIndex)?.requestFocus()
                } catch (e2: IllegalStateException) {
                    // Give up silently
                }
            }
        }
    }

    val handleProfileClick: (Profile) -> Unit = { profile ->
        if (!uiState.isSwitchingProfile && !isTransitioning && (isTouchDevice || isReadyForInput)) {
            if (uiState.isManageMode) {
                viewModel.showEditDialog(profile)
            } else if (profile.isLocked && !profile.pin.isNullOrEmpty()) {
                viewModel.selectProfileWithLockCheck(profile)
            } else {
                startTransitionForProfile(profile)
                viewModel.selectProfile(profile)
            }
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .navigationBarsPadding()
            .onGloballyPositioned { rootCoordinates = it },
        contentAlignment = Alignment.Center
    ) {
        val contentWidth = if (isTouchDevice) (maxWidth - 48.dp).coerceAtMost(560.dp) else maxWidth - 96.dp
        val columns = if (isTouchDevice && maxWidth >= 600.dp) 3 else 2
        val avatarSize = if (isTouchDevice) ((contentWidth - 24.dp * (columns - 1)) / columns).coerceAtMost(152.dp)
            else ((contentWidth - 24.dp * 4) / 5).coerceAtMost(120.dp)
        val avatarSpacing = 24.dp
        val backgroundAlpha = if (isTransitioning) {
            (1f - transitionProgress * 2.5f).coerceIn(0f, 1f)
        } else 1f

        if (backgroundAlpha > 0f) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(contentWidth)
                    .then(if (isTouchDevice) Modifier.verticalScroll(rememberScrollState()).padding(vertical = 32.dp) else Modifier)
                    .graphicsLayer { alpha = backgroundAlpha }
            ) {
                // Title
                Text(
                    text = "ARVIO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    letterSpacing = 6.sp
                )

                Spacer(modifier = Modifier.height(if (isTouchDevice) 24.dp else 16.dp))

                Text(
                    text = if (uiState.isManageMode) stringResource(R.string.manage_profiles) else stringResource(R.string.whos_watching),
                    fontSize = if (isTouchDevice) 32.sp else 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = if (isTouchDevice) Modifier.fillMaxWidth() else Modifier
                )

                Spacer(modifier = Modifier.height(if (isTouchDevice) 32.dp else 24.dp))

                // Profile avatars row

                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(avatarSize),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.loading_profile),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.72f),
                            textAlign = TextAlign.Center
                        )
                    }
                } else if (isTouchDevice) {
                    // Compact grid; vertical scrolling keeps five profiles and actions reachable.
                    val slots = uiState.profiles.size + if (uiState.profiles.size < 5) 1 else 0
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        (0 until slots).toList().chunked(columns).forEach { row ->
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(avatarSpacing, Alignment.CenterHorizontally)) {
                                row.forEach { index ->
                                    val profile = uiState.profiles.getOrNull(index)
                                    if (profile != null) ProfileAvatar(
                                        profile = profile, isManageMode = uiState.isManageMode,
                                        isActiveProfile = uiState.activeProfile?.id == profile.id,
                                        avatarSize = avatarSize,
                                        isTransitionSelected = isTransitioning && transitionProfile?.id == profile.id,
                                        onAvatarPositioned = { avatarCoordinatesMap[profile.id] = it },
                                        onClick = { handleProfileClick(profile) },
                                        onFocus = { viewModel.preloadForProfile(profile) },
                                        onDelete = { viewModel.deleteProfile(profile) }
                                    ) else AddProfileButton(avatarSize = avatarSize,
                                        onClick = { if (!isTransitioning) viewModel.showAddDialog() })
                                }
                            }
                        }
                    }
                } else {
                    // TV: keep avatar tops aligned even when a profile has a Kids badge.
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        uiState.profiles.forEachIndexed { index, profile ->
                            ProfileAvatar(
                                profile = profile,
                                isManageMode = uiState.isManageMode,
                                isActiveProfile = uiState.activeProfile?.id == profile.id,
                                avatarSize = avatarSize,
                                isTransitionSelected = isTransitioning && transitionProfile?.id == profile.id,
                                modifier = Modifier.focusRequester(focusRequesters[index]),
                                onAvatarPositioned = { coords ->
                                    avatarCoordinatesMap[profile.id] = coords
                                },
                                onClick = { handleProfileClick(profile) },
                                onFocus = { viewModel.preloadForProfile(profile) },
                                onDelete = { viewModel.deleteProfile(profile) }
                            )

                            if (index < uiState.profiles.size - 1 || uiState.profiles.size < 5) {
                                Spacer(modifier = Modifier.width(avatarSpacing))
                            }
                        }

                        // Add profile button (max 5 profiles)
                        if (uiState.profiles.size < 5) {
                            AddProfileButton(
                                avatarSize = avatarSize,
                                modifier = Modifier.focusRequester(focusRequesters[uiState.profiles.size]),
                                onClick = { if (isReadyForInput && !uiState.isSwitchingProfile && !isTransitioning) viewModel.showAddDialog() }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(if (isTouchDevice) 32.dp else 24.dp))

                // Manage Profiles button
                ManageProfilesButton(
                    isManageMode = uiState.isManageMode,
                    onClick = {
                        if ((isTouchDevice || isReadyForInput) && !uiState.isSwitchingProfile && !isTransitioning) {
                            viewModel.toggleManageMode()
                        }
                    }
                )

                if (!isCloudConnected) {
                    Spacer(modifier = Modifier.height(if (isTouchDevice) 24.dp else 16.dp))

                    // Cloud connect button — focusable on TV, tappable on mobile
                    CloudConnectButton(
                        onClick = {
                            if ((isTouchDevice || isReadyForInput) && !uiState.isSwitchingProfile && !isTransitioning) {
                                onConnectCloud()
                            }
                        }
                    )
                }
            }
        }

        // ── 3-Step Profile Selection Transition Overlay ──
        if (transitionProfile != null) {
            val targetScale = if (motionEnabled) 1.12f else 1f
            val currentScale = lerp(1f, targetScale, transitionProgress)
            val currentOffsetX = if (motionEnabled) lerp(initialDeltaOffset.x, 0f, transitionProgress) else 0f
            val currentOffsetY = if (motionEnabled) lerp(initialDeltaOffset.y, 0f, transitionProgress) else 0f
            val avatarBaseSize = avatarSize

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // The enlarged profile card: centered directly at (0, 0)
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            translationX = currentOffsetX
                            translationY = currentOffsetY
                            scaleX = currentScale
                            scaleY = currentScale
                        }
                        .size(avatarBaseSize)
                        .clip(RoundedCornerShape(18.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    ProfileAvatarVisual(
                        profile = transitionProfile!!,
                        letterFontSize = 48.sp,
                        iconPadding = 12.dp
                    )
                }

                // Loading ring: appears only if loading is still in progress once the card is centered
                val showSpinner by produceState(initialValue = false, key1 = isTransitioning, key2 = uiState.isSwitchingProfile) {
                    if (isTransitioning && uiState.isSwitchingProfile) {
                        delay(200) // Smooth delay so instantaneous cached data doesn't flash the spinner
                        value = isTransitioning && uiState.isSwitchingProfile
                    } else {
                        value = false
                    }
                }

                val spinnerAlpha by animateFloatAsState(
                    targetValue = if (transitionProgress >= 0.95f && showSpinner) 1f else 0f,
                    animationSpec = tween(220),
                    label = "spinner_alpha"
                )

                if (spinnerAlpha > 0f) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = (avatarBaseSize * targetScale / 2) + 26.dp)
                            .size(26.dp)
                            .graphicsLayer { alpha = spinnerAlpha },
                        color = Color.White.copy(alpha = 0.85f),
                        strokeWidth = 2.5.dp,
                        trackColor = Color.White.copy(alpha = 0.12f)
                    )
                }
            }
        }

        // Add Profile Dialog
        if (uiState.showAddDialog) {
            AddProfileDialog(
                name = uiState.newProfileName,
                onNameChange = { viewModel.setNewProfileName(it) },
                selectedColorIndex = uiState.selectedColorIndex,
                onColorSelected = { viewModel.setSelectedColorIndex(it) },
                selectedAvatarId = uiState.selectedAvatarId,
                onAvatarSelected = { viewModel.setSelectedAvatarId(it) },
                selectedAvatarImageUri = uiState.selectedAvatarImageUri,
                useCustomAvatarImage = uiState.useCustomAvatarImage,
                onAvatarImageSelected = { viewModel.setSelectedAvatarImage(it) },
                onRemoveAvatarImage = { viewModel.removeSelectedAvatarImage() },
                isKidsProfile = uiState.isKidsProfile,
                onKidsProfileChange = viewModel.setKidsProfile,
                kidsAgeLevel = uiState.kidsAgeLevel,
                onKidsAgeLevelChange = viewModel.setKidsAgeLevel,
                onConfirm = { viewModel.createProfile() },
                onDismiss = { viewModel.hideAddDialog() }
            )
        }

        // Edit Profile Dialog
        uiState.editingProfile?.let { profile ->
            EditProfileDialog(
                profile = profile,
                name = uiState.newProfileName,
                onNameChange = { viewModel.setNewProfileName(it) },
                selectedColorIndex = uiState.selectedColorIndex,
                onColorSelected = { viewModel.setSelectedColorIndex(it) },
                selectedAvatarId = uiState.selectedAvatarId,
                onAvatarSelected = { viewModel.setSelectedAvatarId(it) },
                selectedAvatarImageUri = uiState.selectedAvatarImageUri,
                useCustomAvatarImage = uiState.useCustomAvatarImage,
                onAvatarImageSelected = { viewModel.setSelectedAvatarImage(it) },
                onRemoveAvatarImage = { viewModel.removeSelectedAvatarImage() },
                isKidsProfile = uiState.isKidsProfile,
                onKidsProfileChange = viewModel.setKidsProfile,
                kidsAgeLevel = uiState.kidsAgeLevel,
                onKidsAgeLevelChange = viewModel.setKidsAgeLevel,
                onConfirm = { viewModel.updateProfile() },
                onDelete = { viewModel.deleteProfile(profile); viewModel.hideEditDialog() },
                onDismiss = { viewModel.hideEditDialog() },
                onShowPinSetup = { viewModel.showPinSetupDialog() },
                onRemovePin = { viewModel.removeProfilePin() }
            )
        }

        // Toast Notification
        Toast(
            message = uiState.toastMessage ?: "",
            type = uiState.toastType,
            isVisible = uiState.showToast,
            onDismiss = { viewModel.dismissToast() }
        )

        // PIN Entry Dialog
        if (uiState.showPinDialog) {
            if (uiState.pinDialogMode == "verify") {
                val isParentPin = uiState.pinContext.startsWith(ProfileViewModel.PIN_CONTEXT_PARENT_PREFIX)
                PinEntryDialog(
                    title = stringResource(if (isParentPin) R.string.profile_parent_pin_enter else R.string.enter_pin_to_unlock),
                    onPinConfirmed = { pin ->
                        val pending = uiState.pendingProfileForPin
                        if (pending != null && PinUtil.verifyPin(pin, pending.pin) && uiState.pinContext == "select") {
                            startTransitionForProfile(pending)
                        }
                        viewModel.verifyPinAndSelectProfile(pin)
                    },
                    onDismiss = { viewModel.hidePinDialog() },
                    isSetup = false,
                    pinError = uiState.pinError
                )
            } else if (uiState.pinDialogMode == "setup") {
                val isParentPin = uiState.pinContext.startsWith(ProfileViewModel.PIN_CONTEXT_PARENT_PREFIX)
                PinEntryDialog(
                    title = stringResource(if (isParentPin) R.string.profile_parent_pin_set else R.string.set_profile_pin),
                    onPinConfirmed = { pin ->
                        if (isParentPin) viewModel.setupParentPin(pin) else viewModel.setupProfilePin(pin)
                    },
                    setupHint = if (isParentPin) stringResource(R.string.profile_parent_pin_hint) else null,
                    onDismiss = { viewModel.hidePinDialog() },
                    isSetup = true
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProfileAvatar(
    profile: Profile,
    isManageMode: Boolean,
    isActiveProfile: Boolean = false,
    avatarSize: Dp = 120.dp,
    isTransitionSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onAvatarPositioned: (LayoutCoordinates) -> Unit = {},
    onClick: () -> Unit,
    onFocus: () -> Unit = {},
    onDelete: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.04f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.graphicsLayer {
            alpha = if (isTransitionSelected) 0f else 1f
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.onGloballyPositioned { onAvatarPositioned(it) }
        ) {
            val avatarContent: @Composable () -> Unit = {
                ProfileAvatarVisual(
                    profile = profile,
                    letterFontSize = 48.sp,
                    iconPadding = 12.dp
                )
            }

            if (isTouchDevice) {
                Box(
                    modifier = Modifier
                        .size(avatarSize)
                        .semantics { contentDescription = profile.name }
                        .scale(scale)
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { onClick() }
                ) { avatarContent() }
            } else {
                Surface(
                    onClick = onClick,
                    modifier = Modifier
                        .size(avatarSize)
                        .semantics { contentDescription = profile.name }
                        .scale(scale)
                        .onFocusChanged { focusState ->
                            val wasFocused = isFocused > 0
                            isFocused = if (focusState.isFocused) 1 else 0
                            if (!wasFocused && focusState.isFocused) {
                                onFocus()
                            }
                        },
                    scale = ClickableSurfaceDefaults.scale(focusedScale = 1f),
                    shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(18.dp)),
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    border = ClickableSurfaceDefaults.border(
                        focusedBorder = androidx.tv.material3.Border(
                            border = androidx.compose.foundation.BorderStroke(3.dp, Color.White),
                            shape = RoundedCornerShape(18.dp)
                        )
                    )
                ) { avatarContent() }
            }

            // Edit icon overlay in manage mode
            if (isManageMode) {
                Box(
                    modifier = Modifier
                        .size(avatarSize)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .then(if (isTouchDevice) Modifier.clickable { onClick() } else Modifier),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = profile.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (isFocused > 0) Color.Black else Color.White.copy(alpha = 0.8f),
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(avatarSize).clip(RoundedCornerShape(50))
                .background(if (isFocused > 0) Color.White else Color.Transparent)
                .padding(horizontal = 8.dp, vertical = 5.dp),
            textAlign = TextAlign.Center
        )

        if (profile.isKidsProfile && !profile.name.equals(stringResource(R.string.profile_kids_badge), ignoreCase = true)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.profile_kids_badge),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF3B82F6))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AddProfileButton(
    avatarSize: Dp = 120.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.04f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val addContent: @Composable () -> Unit = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(48.dp))
            }
        }
        if (isTouchDevice) {
            Box(
                modifier = Modifier
                    .size(avatarSize)
                    .scale(scale)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .border(2.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(18.dp))
                    .clickable { onClick() }
            ) { addContent() }
        } else {
            Surface(
                onClick = onClick,
                modifier = Modifier
                    .size(avatarSize)
                    .scale(scale)
                    .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
                scale = ClickableSurfaceDefaults.scale(focusedScale = 1f),
                shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(18.dp)),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = Color.White.copy(alpha = 0.1f),
                    focusedContainerColor = Color.White.copy(alpha = 0.2f)
                ),
                border = ClickableSurfaceDefaults.border(
                    border = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(18.dp)
                    ),
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(3.dp, Color.White),
                        shape = RoundedCornerShape(18.dp)
                    )
                )
            ) { addContent() }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.add_profile),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (isFocused > 0) Color.Black else Color.White.copy(alpha = 0.8f),
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(avatarSize).clip(RoundedCornerShape(50))
                .background(if (isFocused > 0) Color.White else Color.Transparent)
                .padding(horizontal = 8.dp, vertical = 5.dp),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ManageProfilesButton(
    isManageMode: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    Surface(
        onClick = if (isTouchDevice) ({}) else onClick,
        modifier = Modifier
            .then(
                if (isTouchDevice) Modifier.fillMaxWidth().clickable { onClick() } else Modifier
            )
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(24.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color.Transparent,
            focusedContainerColor = Color.White.copy(alpha = 0.1f)
        ),
        border = ClickableSurfaceDefaults.border(
            border = androidx.tv.material3.Border(
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(24.dp)
            ),
            focusedBorder = androidx.tv.material3.Border(
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(24.dp)
            )
        )
    ) {
        Text(
            text = if (isManageMode) stringResource(R.string.done) else stringResource(R.string.manage_profiles),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CloudConnectButton(
    onClick: () -> Unit
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var isFocused by remember { mutableIntStateOf(0) }

    if (isTouchDevice) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.04f),
                            Color.White.copy(alpha = 0.04f)
                        )
                    )
                )
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                .clickable { onClick() }
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Cloud,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.85f),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = stringResource(R.string.connect_to_cloud),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(alpha = 0.85f)
            )
        }
    } else {
        Surface(
            onClick = onClick,
            modifier = Modifier.onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
            shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(16.dp)),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color.White.copy(alpha = 0.06f),
                focusedContainerColor = Color.White.copy(alpha = 0.18f)
            ),
            border = ClickableSurfaceDefaults.border(
                border = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)),
                    shape = RoundedCornerShape(16.dp)
                ),
                focusedBorder = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(2.dp, Color.White.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(16.dp)
                )
            )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Cloud,
                    contentDescription = null,
                    tint = if (isFocused > 0) Color.White else Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = stringResource(R.string.connect_to_cloud),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isFocused > 0) Color.White else Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

package com.arflix.tv.ui.screens.profile

import com.arflix.tv.ui.skin.resolveAccentColor

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import kotlinx.coroutines.delay
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.components.ProfileAvatarVisual
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.util.LocalDeviceType
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ProfileSelectionScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onProfileSelected: () -> Unit,
    onShowAddProfile: () -> Unit,
    onConnectCloud: () -> Unit = {},
    isCloudConnected: Boolean = false
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Create focus requesters for each profile slot (max 5 profiles + 1 add button)
    val focusRequesters = remember { List(6) { FocusRequester() } }

    // Track if profile was selected in this session to trigger navigation
    var navigateTriggered by remember { mutableStateOf(false) }

    // Guard against Enter key events from previous screen (TV only — touch devices don't need this)
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var isReadyForInput by remember { mutableStateOf(isTouchDevice) }

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

    // Navigate after the user picks a profile.
    // `navigateTriggered` MUST be a key. Keying only on the uiState values meant the tap itself
    // never re-evaluated this, so navigation depended on *observing* a change in one of them —
    // and re-selecting the already-active profile changes neither: activeProfile.id stays put,
    // and isSwitchingProfile goes true→false inside a single frame, which the StateFlow conflates
    // away before Compose ever reads it. The effect then never restarted and the tap did nothing,
    // until some unrelated hitch let a frame land mid-switch. (Re-selecting the active profile
    // became fast enough to hit this when d9e49b10 dropped its setActiveProfile disk write.)
    LaunchedEffect(navigateTriggered, uiState.activeProfile?.id, uiState.isSwitchingProfile) {
        if (
            navigateTriggered &&
            uiState.activeProfile != null &&
            !uiState.isManageMode &&
            !uiState.isSwitchingProfile
        ) {
            onProfileSelected()
        }
    }

    // Request focus on the first available item (profile or add button)
    LaunchedEffect(uiState.isLoading) {
        if (!uiState.isLoading) {
            // Brief delay for layout, with retry logic
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
                // Retry after a bit more time
                delay(100)
                try {
                    focusRequesters.getOrNull(targetIndex)?.requestFocus()
                } catch (e2: IllegalStateException) {
                    // Give up silently
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "ARVIO",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 6.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (uiState.isManageMode) stringResource(R.string.manage_profiles) else stringResource(R.string.whos_watching),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Profile avatars row
            val avatarSize = if (isTouchDevice) 90.dp else 120.dp
            val avatarSpacing = if (isTouchDevice) 16.dp else 24.dp

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
                // Mobile: use LazyRow so profiles scroll horizontally on small screens
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(avatarSpacing, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    itemsIndexed(uiState.profiles) { index, profile ->
                        ProfileAvatar(
                            profile = profile,
                            isManageMode = uiState.isManageMode,
                            isActiveProfile = uiState.activeProfile?.id == profile.id,
                            avatarSize = avatarSize,
                            modifier = Modifier.focusRequester(focusRequesters[index]),
                            onClick = {
                                if (uiState.isSwitchingProfile) return@ProfileAvatar
                                if (uiState.isManageMode) {
                                    viewModel.showEditDialog(profile)
                                } else {
                                    navigateTriggered = true
                                    viewModel.selectProfileWithLockCheck(profile)
                                }
                            },
                            onFocus = { viewModel.preloadForProfile(profile) },
                            onDelete = { viewModel.deleteProfile(profile) }
                        )
                    }

                    // Add profile button (max 5 profiles)
                    if (uiState.profiles.size < 5) {
                        item {
                            AddProfileButton(
                                avatarSize = avatarSize,
                                modifier = Modifier.focusRequester(focusRequesters[uiState.profiles.size]),
                                onClick = { viewModel.showAddDialog() }
                            )
                        }
                    }
                }
            } else {
                // TV: original Row layout with fixed spacing
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    uiState.profiles.forEachIndexed { index, profile ->
                        ProfileAvatar(
                            profile = profile,
                            isManageMode = uiState.isManageMode,
                            isActiveProfile = uiState.activeProfile?.id == profile.id,
                            avatarSize = avatarSize,
                            modifier = Modifier.focusRequester(focusRequesters[index]),
                            onClick = {
                                // Guard against stray Enter key events from previous screen
                                if (!isReadyForInput || uiState.isSwitchingProfile) return@ProfileAvatar

                                if (uiState.isManageMode) {
                                    viewModel.showEditDialog(profile)
                                } else {
                                    navigateTriggered = true
                                    viewModel.selectProfileWithLockCheck(profile)
                                }
                            },
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
                            onClick = { if (isReadyForInput && !uiState.isSwitchingProfile) viewModel.showAddDialog() }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Manage Profiles button
            ManageProfilesButton(
                isManageMode = uiState.isManageMode,
                onClick = {
                    if ((isTouchDevice || isReadyForInput) && !uiState.isSwitchingProfile) {
                        viewModel.toggleManageMode()
                    }
                }
            )

            if (!isCloudConnected) {
                Spacer(modifier = Modifier.height(24.dp))

                // Cloud connect button — focusable on TV, tappable on mobile
                CloudConnectButton(
                    onClick = {
                        if ((isTouchDevice || isReadyForInput) && !uiState.isSwitchingProfile) {
                            onConnectCloud()
                        }
                    }
                )
            }

            if (uiState.isSwitchingProfile) {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(R.string.loading_profile),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.72f)
                )
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
                PinEntryDialog(
                    title = stringResource(R.string.enter_pin_to_unlock),
                    onPinConfirmed = { pin -> viewModel.verifyPinAndSelectProfile(pin) },
                    onDismiss = { viewModel.hidePinDialog() },
                    isSetup = false,
                    pinError = uiState.pinError
                )
            } else if (uiState.pinDialogMode == "setup") {
                PinEntryDialog(
                    title = stringResource(R.string.set_profile_pin),
                    onPinConfirmed = { pin -> viewModel.setupProfilePin(pin) },
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFocus: () -> Unit = {},
    onDelete: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }
    val accentColor = resolveAccentColor(fallback = Color.White)
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.1f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {
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
                        .scale(scale)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onClick() }
                ) { avatarContent() }
            } else {
                Surface(
                    onClick = onClick,
                    modifier = Modifier
                        .size(avatarSize)
                        .scale(scale)
                        .onFocusChanged { focusState ->
                            val wasFocused = isFocused > 0
                            isFocused = if (focusState.isFocused) 1 else 0
                            if (!wasFocused && focusState.isFocused) {
                                onFocus()
                            }
                        },
                    shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    border = ClickableSurfaceDefaults.border(
                        focusedBorder = androidx.tv.material3.Border(
                            border = androidx.compose.foundation.BorderStroke(3.dp, accentColor),
                            shape = RoundedCornerShape(8.dp)
                        )
                    )
                ) { avatarContent() }
            }

            // Edit icon overlay in manage mode
            if (isManageMode) {
                Box(
                    modifier = Modifier
                        .size(avatarSize)
                        .clip(RoundedCornerShape(8.dp))
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
            color = if (isFocused > 0) Color.White else Color.White.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )

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
    val accentColor = resolveAccentColor(fallback = Color.White)
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.1f else 1f,
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
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .border(2.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .clickable { onClick() }
            ) { addContent() }
        } else {
            Surface(
                onClick = onClick,
                modifier = Modifier
                    .size(avatarSize)
                    .scale(scale)
                    .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
                shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = Color.White.copy(alpha = 0.1f),
                    focusedContainerColor = Color.White.copy(alpha = 0.2f)
                ),
                border = ClickableSurfaceDefaults.border(
                    border = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(8.dp)
                    ),
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(3.dp, accentColor),
                        shape = RoundedCornerShape(8.dp)
                    )
                )
            ) { addContent() }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.add_profile),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (isFocused > 0) Color.White else Color.White.copy(alpha = 0.7f),
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
    val accentColor = resolveAccentColor(fallback = Color.White)

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    Surface(
        onClick = if (isTouchDevice) ({}) else onClick,
        modifier = Modifier
            .then(
                if (isTouchDevice) Modifier.clickable { onClick() } else Modifier
            )
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(4.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color.Transparent,
            focusedContainerColor = Color.White.copy(alpha = 0.1f)
        ),
        border = ClickableSurfaceDefaults.border(
            border = androidx.tv.material3.Border(
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(4.dp)
            ),
            focusedBorder = androidx.tv.material3.Border(
                border = androidx.compose.foundation.BorderStroke(2.dp, accentColor),
                shape = RoundedCornerShape(4.dp)
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
    val accentColor = resolveAccentColor(fallback = Color.White)

    if (isTouchDevice) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF6C63FF).copy(alpha = 0.25f),
                            Color(0xFF00D4FF).copy(alpha = 0.18f)
                        )
                    )
                )
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(24.dp))
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
            shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(24.dp)),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color.White.copy(alpha = 0.06f),
                focusedContainerColor = Color.White.copy(alpha = 0.18f)
            ),
            border = ClickableSurfaceDefaults.border(
                border = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)),
                    shape = RoundedCornerShape(24.dp)
                ),
                focusedBorder = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(2.dp, accentColor.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(24.dp)
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

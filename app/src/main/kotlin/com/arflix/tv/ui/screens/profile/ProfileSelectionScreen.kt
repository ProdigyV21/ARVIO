package com.arflix.tv.ui.screens.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import kotlinx.coroutines.delay
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.ProfileColors
import com.arflix.tv.ui.theme.BackgroundGradientCenter
import com.arflix.tv.ui.theme.BackgroundGradientEnd
import com.arflix.tv.ui.theme.BackgroundGradientStart

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ProfileSelectionScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onProfileSelected: () -> Unit,
    onShowAddProfile: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Create focus requesters for each profile slot (max 5 profiles + 1 add button)
    val focusRequesters = remember { List(6) { FocusRequester() } }

    // Track if profile was selected in this session to trigger navigation
    var navigateTriggered by remember { mutableStateOf(false) }

    // Guard against Enter key events from previous screen
    var isReadyForInput by remember { mutableStateOf(false) }

    // Set ready for input after a short delay to ignore stray key events
    LaunchedEffect(Unit) {
        delay(300)  // Wait for any pending key events to clear
        isReadyForInput = true
    }

    // Reset input guard when dialogs close to prevent stray Enter key from selecting profiles
    LaunchedEffect(uiState.showAddDialog, uiState.editingProfile) {
        // Only reset when a dialog just closed (not on initial load)
        if (!uiState.showAddDialog && uiState.editingProfile == null && isReadyForInput) {
            isReadyForInput = false
            delay(300)
            isReadyForInput = true
        }
    }

    // Navigate when activeProfile changes after user selection
    LaunchedEffect(uiState.activeProfile?.id) {
        if (navigateTriggered && uiState.activeProfile != null && !uiState.isManageMode) {
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
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        BackgroundGradientStart,
                        BackgroundGradientCenter,
                        BackgroundGradientEnd
                    )
                )
            ),
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
                text = if (uiState.isManageMode) "Manage Profiles" else "Who's watching?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Profile avatars row
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                uiState.profiles.forEachIndexed { index, profile ->
                    ProfileAvatar(
                        profile = profile,
                        isManageMode = uiState.isManageMode,
                        isActiveProfile = uiState.activeProfile?.id == profile.id,
                        modifier = Modifier.focusRequester(focusRequesters[index]),
                        onClick = {
                            // Guard against stray Enter key events from previous screen
                            if (!isReadyForInput) return@ProfileAvatar

                            if (uiState.isManageMode) {
                                viewModel.showEditDialog(profile)
                            } else {
                                // If clicking the already active profile, navigate immediately
                                // Otherwise, set flag and let LaunchedEffect handle it when activeProfile changes
                                if (uiState.activeProfile?.id == profile.id) {
                                    viewModel.selectProfile(profile)
                                    onProfileSelected()
                                } else {
                                    navigateTriggered = true
                                    viewModel.selectProfile(profile)
                                }
                            }
                        },
                        onFocus = { viewModel.preloadForProfile(profile) },
                        onDelete = { viewModel.deleteProfile(profile) }
                    )

                    if (index < uiState.profiles.size - 1 || uiState.profiles.size < 5) {
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }

                // Add profile button (max 5 profiles)
                if (uiState.profiles.size < 5) {
                    AddProfileButton(
                        modifier = Modifier.focusRequester(focusRequesters[uiState.profiles.size]),
                        onClick = { if (isReadyForInput) viewModel.showAddDialog() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Manage Profiles button
            ManageProfilesButton(
                isManageMode = uiState.isManageMode,
                onClick = { if (isReadyForInput) viewModel.toggleManageMode() }
            )
        }

        // Add Profile Dialog
        if (uiState.showAddDialog) {
            AddProfileDialog(
                name = uiState.newProfileName,
                onNameChange = { viewModel.setNewProfileName(it) },
                selectedColorIndex = uiState.selectedColorIndex,
                onColorSelected = { viewModel.setSelectedColorIndex(it) },
                isKidsProfile = uiState.isKidsProfile,
                onKidsProfileChange = { viewModel.setIsKidsProfile(it) },
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
                isKidsProfile = uiState.isKidsProfile,
                onKidsProfileChange = { viewModel.setIsKidsProfile(it) },
                onConfirm = { viewModel.updateProfile() },
                onDelete = { viewModel.deleteProfile(profile); viewModel.hideEditDialog() },
                onDismiss = { viewModel.hideEditDialog() }
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProfileAvatar(
    profile: Profile,
    isManageMode: Boolean,
    isActiveProfile: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFocus: () -> Unit = {},
    onDelete: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.1f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {
            Surface(
                onClick = onClick,
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale)
                    .onFocusChanged { focusState ->
                        val wasFocused = isFocused > 0
                        isFocused = if (focusState.isFocused) 1 else 0
                        // Trigger preload when focus is gained
                        if (!wasFocused && focusState.isFocused) {
                            onFocus()
                        }
                    },
                shape = ClickableSurfaceDefaults.shape(
                    shape = RoundedCornerShape(8.dp)
                ),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = Color(profile.avatarColor),
                    focusedContainerColor = Color(profile.avatarColor)
                ),
                border = ClickableSurfaceDefaults.border(
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(3.dp, Color.White),
                        shape = RoundedCornerShape(8.dp)
                    )
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // First letter of name
                    Text(
                        text = profile.name.firstOrNull()?.uppercase() ?: "?",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Edit icon overlay in manage mode
            if (isManageMode) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
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

        if (profile.isKidsProfile) {
            Text(
                text = "Kids",
                fontSize = 12.sp,
                color = Color(0xFF4ADE80),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AddProfileButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.1f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Surface(
            onClick = onClick,
            modifier = Modifier
                .size(120.dp)
                .scale(scale)
                .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
            shape = ClickableSurfaceDefaults.shape(
                shape = RoundedCornerShape(8.dp)
            ),
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
                    border = androidx.compose.foundation.BorderStroke(3.dp, Color.White),
                    shape = RoundedCornerShape(8.dp)
                )
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Profile",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Add Profile",
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

    Surface(
        onClick = onClick,
        modifier = Modifier
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(
            shape = RoundedCornerShape(4.dp)
        ),
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
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(4.dp)
            )
        )
    ) {
        Text(
            text = if (isManageMode) "Done" else "Manage Profiles",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}

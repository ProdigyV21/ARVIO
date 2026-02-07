package com.arflix.tv.ui.screens.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.ProfileColors
import com.arflix.tv.ui.components.TextInputModal

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AddProfileDialog(
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    isKidsProfile: Boolean,
    onKidsProfileChange: (Boolean) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var showNameInput by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    // Name input modal
    TextInputModal(
        isVisible = showNameInput,
        title = "Enter Profile Name",
        hint = "Profile name",
        initialValue = name,
        onConfirm = { newName ->
            onNameChange(newName)
            showNameInput = false
        },
        onCancel = { showNameInput = false }
    )

    if (!showNameInput) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(400.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1a1a1a))
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Add Profile",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Preview avatar
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(ProfileColors.getByIndex(selectedColorIndex))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name.firstOrNull()?.uppercase() ?: "?",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Color selection
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ProfileColors.colors.forEachIndexed { index, color ->
                            ColorButton(
                                color = color,
                                isSelected = index == selectedColorIndex,
                                onClick = { onColorSelected(index) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Name input button - opens TV keyboard
                    NameInputButton(
                        name = name,
                        onClick = { showNameInput = true },
                        modifier = Modifier.focusRequester(focusRequester)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Kids profile toggle
                    KidsProfileToggle(
                        isKidsProfile = isKidsProfile,
                        onToggle = { onKidsProfileChange(!isKidsProfile) }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DialogButton(
                            text = "Cancel",
                            isPrimary = false,
                            onClick = onDismiss
                        )
                        DialogButton(
                            text = "Create",
                            isPrimary = true,
                            enabled = name.isNotBlank(),
                            onClick = onConfirm
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun EditProfileDialog(
    profile: Profile,
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    isKidsProfile: Boolean,
    onKidsProfileChange: (Boolean) -> Unit,
    onConfirm: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var showNameInput by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    // Name input modal
    TextInputModal(
        isVisible = showNameInput,
        title = "Enter Profile Name",
        hint = "Profile name",
        initialValue = name,
        onConfirm = { newName ->
            onNameChange(newName)
            showNameInput = false
        },
        onCancel = { showNameInput = false }
    )

    if (!showNameInput) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(400.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1a1a1a))
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Edit Profile",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Preview avatar
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(ProfileColors.getByIndex(selectedColorIndex))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name.firstOrNull()?.uppercase() ?: "?",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Color selection
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ProfileColors.colors.forEachIndexed { index, color ->
                            ColorButton(
                                color = color,
                                isSelected = index == selectedColorIndex,
                                onClick = { onColorSelected(index) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Name input button - opens TV keyboard
                    NameInputButton(
                        name = name,
                        onClick = { showNameInput = true },
                        modifier = Modifier.focusRequester(focusRequester)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Kids profile toggle
                    KidsProfileToggle(
                        isKidsProfile = isKidsProfile,
                        onToggle = { onKidsProfileChange(!isKidsProfile) }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DialogButton(
                            text = "Delete",
                            isPrimary = false,
                            isDestructive = true,
                            onClick = onDelete
                        )
                        DialogButton(
                            text = "Cancel",
                            isPrimary = false,
                            onClick = onDismiss
                        )
                        DialogButton(
                            text = "Save",
                            isPrimary = true,
                            enabled = name.isNotBlank(),
                            onClick = onConfirm
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NameInputButton(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableIntStateOf(0) }

    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color(0xFF2a2a2a),
            focusedContainerColor = Color(0xFF3a3a3a)
        ),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = androidx.tv.material3.Border(
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(8.dp)
            )
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name.ifBlank { "Enter name" },
                fontSize = 18.sp,
                color = if (name.isBlank()) Color.White.copy(alpha = 0.4f) else Color.White
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ColorButton(
    color: Long,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.2f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    Surface(
        onClick = onClick,
        modifier = Modifier
            .size(32.dp)
            .scale(scale)
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color(color),
            focusedContainerColor = Color(color)
        ),
        border = ClickableSurfaceDefaults.border(
            border = if (isSelected) {
                androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(3.dp, Color.White),
                    shape = CircleShape
                )
            } else {
                androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(0.dp, Color.Transparent),
                    shape = CircleShape
                )
            },
            focusedBorder = androidx.tv.material3.Border(
                border = androidx.compose.foundation.BorderStroke(3.dp, Color.White),
                shape = CircleShape
            )
        )
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun KidsProfileToggle(
    isKidsProfile: Boolean,
    onToggle: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }

    Surface(
        onClick = onToggle,
        modifier = Modifier
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = Color.Transparent,
            focusedContainerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (isKidsProfile) Color(0xFF4ADE80) else Color(0xFF3a3a3a))
                    .then(
                        if (isFocused > 0) Modifier.border(2.dp, Color.White, RoundedCornerShape(4.dp))
                        else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isKidsProfile) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Kids Profile",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun DialogButton(
    text: String,
    isPrimary: Boolean,
    isDestructive: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableIntStateOf(0) }

    val containerColor = when {
        isDestructive -> Color(0xFFDC2626)
        isPrimary -> Color(0xFFE50914)
        else -> Color.Transparent
    }

    val focusedContainerColor = when {
        isDestructive -> Color(0xFFEF4444)
        isPrimary -> Color(0xFFFF1A1A)
        else -> Color.White.copy(alpha = 0.1f)
    }

    Surface(
        onClick = { if (enabled) onClick() },
        modifier = Modifier
            .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
        shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(4.dp)),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = containerColor,
            focusedContainerColor = focusedContainerColor
        ),
        border = if (!isPrimary && !isDestructive) {
            ClickableSurfaceDefaults.border(
                border = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(4.dp)
                ),
                focusedBorder = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(4.dp)
                )
            )
        } else {
            ClickableSurfaceDefaults.border(
                focusedBorder = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(4.dp)
                )
            )
        }
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}

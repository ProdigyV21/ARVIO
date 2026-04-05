package com.arflix.tv.ui.screens.profile

import android.text.InputType
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.widget.doAfterTextChanged
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.ProfileColors
import com.arflix.tv.ui.components.AvatarIcon
import com.arflix.tv.ui.components.AvatarRegistry
import com.arflix.tv.util.LocalDeviceType

// ============================================================
// Add Profile Dialog
// ============================================================

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AddProfileDialog(
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    selectedAvatarId: Int = 0,
    onAvatarSelected: (Int) -> Unit = {},
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    ProfileDialogContent(
        title = "Add Profile",
        autoFocusNameInput = true,
        name = name,
        onNameChange = onNameChange,
        selectedColorIndex = selectedColorIndex,
        onColorSelected = onColorSelected,
        selectedAvatarId = selectedAvatarId,
        onAvatarSelected = onAvatarSelected,
        confirmLabel = "Create",
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        onDelete = null
    )
}

// ============================================================
// Edit Profile Dialog
// ============================================================

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun EditProfileDialog(
    profile: Profile,
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    selectedAvatarId: Int = 0,
    onAvatarSelected: (Int) -> Unit = {},
    onConfirm: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    ProfileDialogContent(
        title = "Edit Profile",
        autoFocusNameInput = false,
        name = name,
        onNameChange = onNameChange,
        selectedColorIndex = selectedColorIndex,
        onColorSelected = onColorSelected,
        selectedAvatarId = selectedAvatarId,
        onAvatarSelected = onAvatarSelected,
        confirmLabel = "Save",
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        onDelete = onDelete
    )
}

// ============================================================
// Shared dialog content — compact layout
// ============================================================

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProfileDialogContent(
    title: String,
    autoFocusNameInput: Boolean,
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    selectedAvatarId: Int,
    onAvatarSelected: (Int) -> Unit,
    confirmLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDelete: (() -> Unit)?
) {
    val context = LocalContext.current
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var editTextRef by remember { mutableStateOf<EditText?>(null) }
    val confirmButtonFocusRequester = remember { FocusRequester() }

    fun showKeyboard(editText: EditText) {
        editText.post {
            val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            val shown = imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT) ?: false
            if (!shown) {
                @Suppress("DEPRECATION")
                imm?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
            }
        }
    }

    fun hideKeyboard(editText: EditText? = editTextRef) {
        editText?.post {
            val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(editText.windowToken, 0)
            editText.clearFocus()
        }
    }

    LaunchedEffect(isTouchDevice, autoFocusNameInput) {
        if (!isTouchDevice && !autoFocusNameInput) {
            // Always give the dialog a visible focused control on TV.
            runCatching { confirmButtonFocusRequester.requestFocus() }
        }
    }

    LaunchedEffect(autoFocusNameInput, editTextRef) {
        val editText = editTextRef ?: return@LaunchedEffect
        if (autoFocusNameInput) {
            editText.requestFocus()
            editText.setSelection(editText.text?.length ?: 0)
            showKeyboard(editText)
        }
    }

    Dialog(
        onDismissRequest = {
            hideKeyboard()
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.90f)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF141414))
                    .padding(start = 28.dp, top = 28.dp, bottom = 28.dp, end = 12.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                // ---- Left column: preview + name + buttons ----
                Column(
                    modifier = Modifier.width(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Preview avatar
                    val bgColors = if (selectedAvatarId > 0) {
                        val (c1, c2) = AvatarRegistry.gradientColors(selectedAvatarId)
                        c1 to c2
                    } else {
                        val c = Color(ProfileColors.getByIndex(selectedColorIndex))
                        c to c
                    }
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                Brush.verticalGradient(listOf(bgColors.first, bgColors.second))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedAvatarId > 0) {
                            AvatarIcon(
                                avatarId = selectedAvatarId,
                                modifier = Modifier.fillMaxSize().padding(10.dp)
                            )
                        } else {
                            Text(
                                text = name.firstOrNull()?.uppercase() ?: "?",
                                fontSize = 44.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Name input
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF222222))
                            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                            .clickable {
                                editTextRef?.let { et ->
                                    et.requestFocus()
                                    et.postDelayed({ showKeyboard(et) }, 100)
                                }
                            }
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                EditText(ctx).apply {
                                    editTextRef = this
                                    setText(name)
                                    setTextColor(android.graphics.Color.WHITE)
                                    setHintTextColor(android.graphics.Color.GRAY)
                                    hint = "Profile name"
                                    textSize = 16f
                                    background = null
                                    setPadding(36, 32, 36, 32)
                                    isSingleLine = true
                                    inputType = InputType.TYPE_CLASS_TEXT
                                    imeOptions = EditorInfo.IME_ACTION_DONE
                                    isFocusable = true
                                    isFocusableInTouchMode = true
                                    doAfterTextChanged { editable ->
                                        onNameChange(editable?.toString() ?: "")
                                    }
                                    setOnEditorActionListener { _, actionId, event ->
                                        val isDoneAction = actionId == EditorInfo.IME_ACTION_DONE
                                        val isEnterKey =
                                            event?.keyCode == KeyEvent.KEYCODE_ENTER &&
                                                event.action == KeyEvent.ACTION_UP
                                        if (isDoneAction || isEnterKey) {
                                            hideKeyboard(this)
                                            runCatching { confirmButtonFocusRequester.requestFocus() }
                                            true
                                        } else false
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            update = { et ->
                                if (et.text.toString() != name) {
                                    et.setText(name)
                                    et.setSelection(name.length)
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Buttons
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DialogButton(
                            text = confirmLabel,
                            isPrimary = true,
                            enabled = name.isNotBlank(),
                            onClick = {
                                hideKeyboard()
                                onConfirm()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(confirmButtonFocusRequester)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            DialogButton(
                                text = "Cancel",
                                isPrimary = false,
                                onClick = {
                                    hideKeyboard()
                                    onDismiss()
                                },
                                modifier = Modifier.weight(1f)
                            )
                            if (onDelete != null) {
                                DialogButton(
                                    text = "Delete",
                                    isPrimary = false,
                                    isDestructive = true,
                                    onClick = {
                                        hideKeyboard()
                                        onDelete()
                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                // ---- Right column: avatar picker (4 themed rows) ----
                Column(
                    modifier = Modifier.width(460.dp)
                ) {
                    // Avatar picker - 4 horizontal scrolling rows by category
                    AvatarRegistry.categories.forEachIndexed { rowIdx, (label, ids) ->
                        Text(
                            text = label,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            // "None" option only in first row
                            if (rowIdx == 0) {
                                item {
                                    AvatarGridItem(
                                        avatarId = 0,
                                        isSelected = selectedAvatarId == 0,
                                        onClick = { onAvatarSelected(0) },
                                        isNone = true
                                    )
                                }
                            }
                            items(ids.size) { col ->
                                val id = ids[col]
                                AvatarGridItem(
                                    avatarId = id,
                                    isSelected = selectedAvatarId == id,
                                    onClick = { onAvatarSelected(id) }
                                )
                            }
                        }

                        if (rowIdx < AvatarRegistry.categories.size - 1) Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

// ============================================================
// Avatar grid item — individual avatar cell
// ============================================================

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AvatarGridItem(
    avatarId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    isNone: Boolean = false
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var isFocused by remember { mutableIntStateOf(0) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused > 0) 1.12f else 1f,
        animationSpec = tween(150),
        label = "avatarScale"
    )

    val (c1, c2) = if (isNone) {
        Color(0xFF2A2A2A) to Color(0xFF333333)
    } else {
        AvatarRegistry.gradientColors(avatarId)
    }

    val content: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(c1, c2))),
            contentAlignment = Alignment.Center
        ) {
            if (isNone) {
                Text(
                    text = "Aa",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White.copy(alpha = 0.7f)
                )
            } else {
                AvatarIcon(
                    avatarId = avatarId,
                    modifier = Modifier.fillMaxSize().padding(5.dp)
                )
            }
            // Selected checkmark
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black.copy(alpha = 0.35f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }

    if (isTouchDevice) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = if (isSelected || isFocused > 0) 2.dp else 1.dp,
                    color = if (isSelected || isFocused > 0) Color.White else Color.White.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { onClick() }
                .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 }
        ) {
            content()
        }
    } else {
        Surface(
            onClick = onClick,
            modifier = Modifier
                .size(54.dp)
                .scale(scale)
                .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
            shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(10.dp)),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            border = ClickableSurfaceDefaults.border(
                border = if (isSelected) {
                    androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(10.dp)
                    )
                } else {
                    androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.08f)),
                        shape = RoundedCornerShape(10.dp)
                    )
                },
                focusedBorder = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(10.dp)
                )
            )
        ) {
            content()
        }
    }
}

// ============================================================
// Dialog button
// ============================================================

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun DialogButton(
    text: String,
    isPrimary: Boolean,
    isDestructive: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
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

    val buttonContent: @Composable () -> Unit = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = if (enabled) Color.White else Color.White.copy(alpha = 0.4f),
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
            )
        }
    }

    if (isTouchDevice) {
        Box(
            modifier = modifier
                .focusable()
                .clip(RoundedCornerShape(6.dp))
                .background(if (isFocused > 0) focusedContainerColor else containerColor)
                .then(
                    if (!isPrimary && !isDestructive) {
                        Modifier.border(
                            width = if (isFocused > 0) 2.dp else 1.dp,
                            color = if (isFocused > 0) Color.White else Color.White.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(6.dp)
                        )
                    } else if (isFocused > 0) {
                        Modifier.border(2.dp, Color.White, RoundedCornerShape(6.dp))
                    } else {
                        Modifier
                    }
                )
                .clickable { if (enabled) onClick() }
                .onFocusChanged { isFocused = if (it.isFocused) 1 else 0 }
        ) {
            buttonContent()
        }
    } else {
        Surface(
            onClick = { if (enabled) onClick() },
            modifier = modifier.onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
            shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(6.dp)),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = containerColor,
                focusedContainerColor = focusedContainerColor
            ),
            border = if (!isPrimary && !isDestructive) {
                ClickableSurfaceDefaults.border(
                    border = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(6.dp)
                    ),
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(6.dp)
                    )
                )
            } else {
                ClickableSurfaceDefaults.border(
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(6.dp)
                    )
                )
            }
        ) {
            buttonContent()
        }
    }
}

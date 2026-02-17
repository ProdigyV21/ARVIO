package com.arflix.tv.ui.screens.profile

import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AddProfileDialog(
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var isSurfaceFocused by remember { mutableStateOf(false) }
    var editTextRef by remember { mutableStateOf<EditText?>(null) }

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
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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

                // Name input field - wrapped in focusable Surface for D-pad
                // Keyboard only opens on click, not on focus
                Surface(
                    onClick = {
                        // Only open keyboard when user clicks/presses Enter
                        editTextRef?.let { editText ->
                            editText.requestFocus()
                            editText.postDelayed({
                                val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                imm?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
                            }, 100)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            isSurfaceFocused = focusState.isFocused
                            // Don't auto-open keyboard on focus - only track focus state
                        },
                    shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = Color(0xFF2a2a2a),
                        focusedContainerColor = Color(0xFF2a2a2a)
                    ),
                    border = ClickableSurfaceDefaults.border(
                        border = androidx.tv.material3.Border(
                            border = androidx.compose.foundation.BorderStroke(
                                2.dp,
                                Color.White.copy(alpha = 0.3f)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                        focusedBorder = androidx.tv.material3.Border(
                            border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                            shape = RoundedCornerShape(8.dp)
                        )
                    )
                ) {
                    AndroidView(
                        factory = { ctx ->
                            EditText(ctx).apply {
                                editTextRef = this
                                setText(name)
                                setTextColor(android.graphics.Color.WHITE)
                                setHintTextColor(android.graphics.Color.GRAY)
                                hint = "Enter profile name"
                                textSize = 18f
                                background = null
                                setPadding(48, 48, 48, 48)
                                isSingleLine = true
                                inputType = InputType.TYPE_CLASS_TEXT
                                imeOptions = EditorInfo.IME_ACTION_DONE
                                isFocusable = true
                                isFocusableInTouchMode = true

                                doAfterTextChanged { editable ->
                                    onNameChange(editable?.toString() ?: "")
                                }

                                setOnEditorActionListener { _, actionId, _ ->
                                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                                        val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                        imm?.hideSoftInputFromWindow(windowToken, 0)
                                        clearFocus()
                                        true
                                    } else false
                                }
                                // No setOnFocusChangeListener - keyboard only opens on click
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        update = { editText ->
                            // Only update text if it differs to avoid cursor jumping
                            if (editText.text.toString() != name) {
                                editText.setText(name)
                                editText.setSelection(name.length)
                            }
                        }
                    )
                }

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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun EditProfileDialog(
    profile: Profile,
    name: String,
    onNameChange: (String) -> Unit,
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    onConfirm: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var isSurfaceFocused by remember { mutableStateOf(false) }
    var editTextRef by remember { mutableStateOf<EditText?>(null) }

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
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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

                // Name input field - wrapped in focusable Surface for D-pad
                // Keyboard only opens on click, not on focus
                Surface(
                    onClick = {
                        // Only open keyboard when user clicks/presses Enter
                        editTextRef?.let { editText ->
                            editText.requestFocus()
                            editText.postDelayed({
                                val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                imm?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
                            }, 100)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            isSurfaceFocused = focusState.isFocused
                            // Don't auto-open keyboard on focus - only track focus state
                        },
                    shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = Color(0xFF2a2a2a),
                        focusedContainerColor = Color(0xFF2a2a2a)
                    ),
                    border = ClickableSurfaceDefaults.border(
                        border = androidx.tv.material3.Border(
                            border = androidx.compose.foundation.BorderStroke(
                                2.dp,
                                Color.White.copy(alpha = 0.3f)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                        focusedBorder = androidx.tv.material3.Border(
                            border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                            shape = RoundedCornerShape(8.dp)
                        )
                    )
                ) {
                    AndroidView(
                        factory = { ctx ->
                            EditText(ctx).apply {
                                editTextRef = this
                                setText(name)
                                setTextColor(android.graphics.Color.WHITE)
                                setHintTextColor(android.graphics.Color.GRAY)
                                hint = "Enter profile name"
                                textSize = 18f
                                background = null
                                setPadding(48, 48, 48, 48)
                                isSingleLine = true
                                inputType = InputType.TYPE_CLASS_TEXT
                                imeOptions = EditorInfo.IME_ACTION_DONE
                                isFocusable = true
                                isFocusableInTouchMode = true

                                doAfterTextChanged { editable ->
                                    onNameChange(editable?.toString() ?: "")
                                }

                                setOnEditorActionListener { _, actionId, _ ->
                                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                                        val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                        imm?.hideSoftInputFromWindow(windowToken, 0)
                                        clearFocus()
                                        true
                                    } else false
                                }
                                // No setOnFocusChangeListener - keyboard only opens on click
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        update = { editText ->
                            // Only update text if it differs to avoid cursor jumping
                            if (editText.text.toString() != name) {
                                editText.setText(name)
                                editText.setSelection(name.length)
                            }
                        }
                    )
                }

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

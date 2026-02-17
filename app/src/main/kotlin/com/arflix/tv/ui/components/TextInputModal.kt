package com.arflix.tv.ui.components

import android.text.InputType
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * TV-friendly text input modal using native Android keyboard
 */
@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextInputModal(
    isVisible: Boolean,
    title: String,
    hint: String = "",
    initialValue: String = "",
    isPassword: Boolean = false,
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    var inputText by remember(isVisible) { mutableStateOf(initialValue) }
    var focusedButton by remember(isVisible) { mutableIntStateOf(-1) } // -1 = input, 0 = cancel, 1 = ok
    var isInputFocused by remember { mutableStateOf(false) }

    val inputFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val view = LocalView.current

    var editTextRef by remember { mutableStateOf<EditText?>(null) }
    // Show/hide keyboard using the EditText window token (toggleSoftInput can get stuck on some TV IMEs).
    fun showKeyboard() {
        val editText = editTextRef ?: return
        val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        editText.post {
            editText.requestFocus()
            val shown = imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT) ?: false
            if (!shown) imm?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
        }
    }

    fun hideKeyboard() {
        val editText = editTextRef ?: return
        val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        editText.post {
            imm?.hideSoftInputFromWindow(editText.windowToken, 0)
            editText.clearFocus()
            runCatching { imm?.restartInput(editText) }
        }
    }
    // Request focus on input when modal becomes visible and show keyboard
    LaunchedEffect(isVisible) {
        if (isVisible) {
            inputFocusRequester.requestFocus()
            focusedButton = -1
            // Delay to ensure focus is set before showing keyboard
            kotlinx.coroutines.delay(200)
            showKeyboard()
        } else {
            hideKeyboard()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.95f))
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                hideKeyboard()
                                onCancel()
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusedButton == -1) {
                                    focusedButton = 0
                                    hideKeyboard()
                                }
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusedButton >= 0) {
                                    focusedButton = -1
                                    inputFocusRequester.requestFocus()
                                    showKeyboard()
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                if (focusedButton > 0) {
                                    focusedButton--
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                if (focusedButton in 0..0) {
                                    focusedButton++
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                when (focusedButton) {
                                    0 -> {
                                        hideKeyboard()
                                        onCancel()
                                    }
                                    1 -> {
                                        hideKeyboard()
                                        onConfirm(inputText)
                                    }
                                    else -> {
                                        // Input is focused, show keyboard
                                        showKeyboard()
                                    }
                                }
                                true
                            }
                            else -> false
                        }
                    } else false
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(500.dp)
                    .background(Color(0xFF1A1A1A), RoundedCornerShape(20.dp))
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Input field using native EditText for better TV keyboard support
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BackgroundElevated, RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = if (focusedButton == -1) Pink else Pink.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(4.dp)
                ) {
                    AndroidView(
                        factory = { ctx ->
                            EditText(ctx).apply {
                                editTextRef = this
                                setText(inputText)
                                setTextColor(android.graphics.Color.WHITE)
                                setHintTextColor(android.graphics.Color.GRAY)
                                setHint(hint)
                                textSize = 18f
                                background = null
                                setPadding(32, 32, 32, 32)
                                isSingleLine = true
                                inputType = if (isPassword) {
                                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                                } else {
                                    InputType.TYPE_CLASS_TEXT
                                }
                                imeOptions = EditorInfo.IME_ACTION_DONE

                                // Update Compose state when text changes
                                doAfterTextChanged { editable ->
                                    inputText = editable?.toString() ?: ""
                                }

                                // Handle Done action
                                setOnEditorActionListener { _, actionId, _ ->
                                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                                        hideKeyboard()
                                        onConfirm(inputText)
                                        true
                                    } else false
                                }

                                // Request focus and show keyboard
                                setOnFocusChangeListener { v, hasFocus ->
                                    isInputFocused = hasFocus
                                    if (hasFocus) {
                                        focusedButton = -1
                                        v.postDelayed({
                                            val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                            val shown = imm?.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT) ?: false
                                            if (!shown) imm?.showSoftInput(v, InputMethodManager.SHOW_FORCED)
                                        }, 100)
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(inputFocusRequester),
                        update = { editText ->
                            // Request focus when modal becomes visible and show keyboard
                            if (isVisible && !editText.hasFocus()) {
                                editText.requestFocus()
                                editText.postDelayed({
                                    val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                    val shown = imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT) ?: false
                                    if (!shown) imm?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
                                }, 100)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Action buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ActionButton(
                        text = "CANCEL",
                        isFocused = focusedButton == 0,
                        onClick = {
                            hideKeyboard()
                            onCancel()
                        }
                    )
                    ActionButton(
                        text = "OK",
                        isFocused = focusedButton == 1,
                        isPrimary = true,
                        onClick = {
                            hideKeyboard()
                            onConfirm(inputText)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ActionButton(
    text: String,
    isFocused: Boolean,
    isPrimary: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(48.dp)
            .background(
                when {
                    isFocused -> if (isPrimary) Pink else Color.White
                    isPrimary -> Pink.copy(alpha = 0.3f)
                    else -> Color.White.copy(alpha = 0.1f)
                },
                RoundedCornerShape(8.dp)
            )
            .border(
                width = if (isFocused) 0.dp else 1.dp,
                color = if (isPrimary) Pink else Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = ArflixTypography.button,
            color = if (isFocused) {
                if (isPrimary) Color.White else Color.Black
            } else {
                if (isPrimary) Pink else TextSecondary
            },
            textAlign = TextAlign.Center
        )
    }
}

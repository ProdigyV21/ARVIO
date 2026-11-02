package com.arflix.tv.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.arflix.tv.R
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.ErrorRed
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.PinUtil
import kotlinx.coroutines.delay

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PinEntryDialog(
    title: String = stringResource(R.string.profile_enter_pin),
    onPinConfirmed: (String) -> Unit,
    onDismiss: () -> Unit,
    isSetup: Boolean = false,
    pinError: String = "",
    // Setup only: the line under the title; null keeps the profile-lock wording.
    setupHint: String? = null
) {
    var pinInput by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var isConfirmingSetup by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf(pinError) }
    val pinInvalidMessage = stringResource(R.string.profile_pin_invalid)
    val pinMismatchMessage = stringResource(R.string.profile_pin_mismatch)

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val mobileFocusRequester = remember { FocusRequester() }
    val tvFirstKeyFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(pinError) {
        errorMessage = pinError
    }

    // Auto-focus soft keyboard on mobile devices
    LaunchedEffect(isTouchDevice, isConfirmingSetup) {
        if (isTouchDevice) {
            delay(200)
            try {
                mobileFocusRequester.requestFocus()
                keyboardController?.show()
            } catch (_: Exception) {}
        } else {
            delay(150)
            try {
                tvFirstKeyFocusRequester.requestFocus()
            } catch (_: Exception) {}
        }
    }

    val currentPin = if (isSetup && isConfirmingSetup) confirmPin else pinInput

    val handleConfirm: () -> Unit = {
        val current = if (isSetup && isConfirmingSetup) confirmPin else pinInput
        if (!PinUtil.isValidPin(current)) {
            errorMessage = pinInvalidMessage
        } else if (isSetup) {
            if (!isConfirmingSetup) {
                isConfirmingSetup = true
                errorMessage = ""
            } else {
                if (pinInput != confirmPin) {
                    errorMessage = pinMismatchMessage
                    confirmPin = ""
                    isConfirmingSetup = false
                } else {
                    onPinConfirmed(pinInput)
                }
            }
        } else {
            onPinConfirmed(current)
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.90f))
                .navigationBarsPadding()
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .widthIn(min = 320.dp, max = 360.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(BackgroundElevated)
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Icon + Title
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.profile_pin_entry_cd),
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )

                Text(
                    text = when {
                        isSetup && !isConfirmingSetup -> title
                        isSetup && isConfirmingSetup -> stringResource(R.string.profile_confirm_pin)
                        else -> title
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = when {
                        isSetup && !isConfirmingSetup -> setupHint ?: stringResource(R.string.profile_pin_setup_hint)
                        isSetup && isConfirmingSetup -> stringResource(R.string.profile_pin_reenter)
                        else -> stringResource(R.string.enter_pin_to_unlock)
                    },
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )

                // PIN Input Display (Center-aligned, with invisible BasicTextField for soft keyboard on touch devices)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .then(
                            if (isTouchDevice) {
                                Modifier.clickable {
                                    mobileFocusRequester.requestFocus()
                                    keyboardController?.show()
                                }
                            } else Modifier
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isTouchDevice) {
                        BasicTextField(
                            value = currentPin,
                            onValueChange = { newValue ->
                                val digitsOnly = newValue.filter { it.isDigit() }.take(5)
                                if (isSetup && isConfirmingSetup) {
                                    confirmPin = digitsOnly
                                } else {
                                    pinInput = digitsOnly
                                }
                                errorMessage = ""
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    handleConfirm()
                                }
                            ),
                            modifier = Modifier
                                .focusRequester(mobileFocusRequester)
                                .alpha(0f)
                                .matchParentSize()
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { index ->
                            val isFilled = index < currentPin.length
                            val isCurrentSlot = index == currentPin.length
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(BackgroundElevated)
                                    .border(
                                        width = if (isFilled || isCurrentSlot) 2.dp else 1.dp,
                                        color = when {
                                            isFilled -> ErrorRed
                                            isCurrentSlot -> Color.White.copy(alpha = 0.85f)
                                            else -> Color.White.copy(alpha = 0.2f)
                                        },
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isFilled) {
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .clip(CircleShape)
                                            .background(Color.White)
                                    )
                                }
                            }
                        }
                    }
                }

                // Numeric Keypad (TV Remote Only — on touch devices the soft keyboard is used instead)
                if (!isTouchDevice) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (row in 0..2) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                for (col in 0..2) {
                                    val num = row * 3 + col + 1
                                    PinKeyButton(
                                        label = num.toString(),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(46.dp)
                                            .then(
                                                if (num == 1) Modifier.focusRequester(tvFirstKeyFocusRequester) else Modifier
                                            ),
                                        onClick = {
                                            val pin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                                            if (pin.length < 5) {
                                                val newPin = pin + num
                                                if (isSetup && isConfirmingSetup) {
                                                    confirmPin = newPin
                                                } else {
                                                    pinInput = newPin
                                                }
                                                errorMessage = ""
                                            }
                                        }
                                    )
                                }
                            }
                        }

                        // Bottom row: 0, Clear, Backspace
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            PinKeyButton(
                                label = "0",
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                onClick = {
                                    val pin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                                    if (pin.length < 5) {
                                        val newPin = pin + "0"
                                        if (isSetup && isConfirmingSetup) {
                                            confirmPin = newPin
                                        } else {
                                            pinInput = newPin
                                        }
                                        errorMessage = ""
                                    }
                                }
                            )

                            PinKeyButton(
                                label = stringResource(R.string.profile_clear),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                onClick = {
                                    if (isSetup && isConfirmingSetup) {
                                        confirmPin = ""
                                    } else {
                                        pinInput = ""
                                    }
                                    errorMessage = ""
                                }
                            )

                            PinKeyButton(
                                label = "←",
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                onClick = {
                                    val pin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                                    if (pin.isNotEmpty()) {
                                        val newPin = pin.dropLast(1)
                                        if (isSetup && isConfirmingSetup) {
                                            confirmPin = newPin
                                        } else {
                                            pinInput = newPin
                                        }
                                    }
                                    errorMessage = ""
                                }
                            )
                        }
                    }
                }

                // Error Message
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        fontSize = 12.sp,
                        color = ErrorRed,
                        textAlign = TextAlign.Center
                    )
                }

                // Action Buttons (Consistent ARVIO Dialog Styling)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PinActionButton(
                        label = stringResource(R.string.cancel),
                        onClick = onDismiss,
                        isPrimary = false,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    )

                    val canConfirm = currentPin.length in 4..5
                    PinActionButton(
                        label = if (isSetup && !isConfirmingSetup) stringResource(R.string.next) else stringResource(R.string.confirm),
                        onClick = handleConfirm,
                        isPrimary = true,
                        enabled = canConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PinActionButton(
    label: String,
    onClick: () -> Unit,
    isPrimary: Boolean,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var isFocused by remember { mutableIntStateOf(0) }

    val containerColor = when {
        isPrimary && enabled -> ErrorRed
        isPrimary && !enabled -> ErrorRed.copy(alpha = 0.35f)
        else -> Color.Transparent
    }
    val focusedContainerColor = when {
        isPrimary && enabled -> ErrorRed
        isPrimary && !enabled -> ErrorRed.copy(alpha = 0.35f)
        else -> Color.White.copy(alpha = 0.12f)
    }

    val content: @Composable () -> Unit = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = label,
                color = if (enabled) Color.White else Color.White.copy(alpha = 0.45f),
                fontWeight = if (isPrimary) FontWeight.SemiBold else FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }

    if (isTouchDevice) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(containerColor)
                .then(
                    if (!isPrimary) {
                        Modifier.border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.25f),
                            shape = RoundedCornerShape(8.dp)
                        )
                    } else {
                        Modifier
                    }
                )
                .clickable(enabled = enabled) { onClick() }
        ) {
            content()
        }
    } else {
        Surface(
            onClick = { if (enabled) onClick() },
            modifier = modifier.onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
            shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = containerColor,
                focusedContainerColor = focusedContainerColor
            ),
            border = if (!isPrimary) {
                ClickableSurfaceDefaults.border(
                    border = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.25f)),
                        shape = RoundedCornerShape(8.dp)
                    ),
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(8.dp)
                    )
                )
            } else {
                ClickableSurfaceDefaults.border(
                    focusedBorder = androidx.tv.material3.Border(
                        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(8.dp)
                    )
                )
            }
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PinKeyButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    var isFocused by remember { mutableIntStateOf(0) }

    val content: @Composable () -> Unit = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = label,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
    }

    if (isTouchDevice) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(BackgroundElevated)
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                .clickable { onClick() }
        ) {
            content()
        }
    } else {
        Surface(
            onClick = onClick,
            modifier = modifier.onFocusChanged { isFocused = if (it.isFocused) 1 else 0 },
            shape = ClickableSurfaceDefaults.shape(shape = RoundedCornerShape(8.dp)),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = BackgroundElevated,
                focusedContainerColor = Color(0xFF333333)
            ),
            border = ClickableSurfaceDefaults.border(
                border = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)),
                    shape = RoundedCornerShape(8.dp)
                ),
                focusedBorder = androidx.tv.material3.Border(
                    border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(8.dp)
                )
            )
        ) {
            content()
        }
    }
}

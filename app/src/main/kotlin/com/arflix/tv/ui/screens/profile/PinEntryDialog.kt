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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.arflix.tv.R
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.PinUtil

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PinEntryDialog(
    title: String = stringResource(R.string.profile_enter_pin),
    onPinConfirmed: (String) -> Unit,
    onDismiss: () -> Unit,
    isSetup: Boolean = false,
    pinError: String = ""
) {
    var pinInput by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var isConfirmingSetup by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf(pinError) }
    val pinInvalidMessage = stringResource(R.string.profile_pin_invalid)
    val pinMismatchMessage = stringResource(R.string.profile_pin_mismatch)

    LaunchedEffect(pinError) {
        errorMessage = pinError
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.90f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF141414))
                    .padding(40.dp)
                    .width(300.dp),
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
                        isSetup && !isConfirmingSetup -> stringResource(R.string.set_profile_pin)
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
                        isSetup && !isConfirmingSetup -> stringResource(R.string.profile_pin_setup_hint)
                        isSetup && isConfirmingSetup -> stringResource(R.string.profile_pin_reenter)
                        else -> stringResource(R.string.enter_pin_to_unlock)
                    },
                    fontSize = 12.sp,
                    color = Color(0xFFB0B0B0),
                    textAlign = TextAlign.Center
                )

                // PIN Input Display
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        val currentPin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF2A2A2A))
                                .border(
                                    width = 2.dp,
                                    color = if (index < currentPin.length) Color(0xFF4CAF50) else Color(0xFF444444),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (index < currentPin.length) {
                                Text(
                                    text = "•",
                                    fontSize = 24.sp,
                                    color = Color(0xFF4CAF50),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Numeric Keypad
                Column(
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
                                        .height(48.dp),
                                    onClick = {
                                        val currentPin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                                        if (currentPin.length < 5) {
                                            val newPin = currentPin + num
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
                                .height(48.dp),
                            onClick = {
                                val currentPin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                                if (currentPin.length < 5) {
                                    val newPin = currentPin + "0"
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
                                .height(48.dp),
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
                                .height(48.dp),
                            onClick = {
                                val currentPin = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                                if (currentPin.isNotEmpty()) {
                                    val newPin = currentPin.dropLast(1)
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

                // Error Message
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        fontSize = 12.sp,
                        color = Color(0xFFFF6B6B),
                        textAlign = TextAlign.Center
                    )
                }

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PinActionButton(
                        label = stringResource(R.string.cancel),
                        onClick = onDismiss,
                        containerColor = Color(0xFF2A2A2A),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                    )

                    PinActionButton(
                        label = stringResource(R.string.confirm),
                        onClick = {
                            val current = if (isSetup && isConfirmingSetup) confirmPin else pinInput
                            if (!PinUtil.isValidPin(current)) {
                                errorMessage = pinInvalidMessage
                            } else if (isSetup) {
                                if (!isConfirmingSetup) {
                                    isConfirmingSetup = true
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
                        },
                        containerColor = Color(0xFF4CAF50),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
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
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val content: @Composable () -> Unit = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(label, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }

    if (isTouchDevice) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(containerColor)
                .clickable { onClick() }
        ) {
            content()
        }
    } else {
        Surface(
            onClick = onClick,
            colors = ClickableSurfaceDefaults.colors(containerColor = containerColor),
            modifier = modifier
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
    val content: @Composable () -> Unit = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = label,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
    if (isTouchDevice) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF2A2A2A))
                .clickable { onClick() }
        ) {
            content()
        }
    } else {
        Surface(
            onClick = onClick,
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color(0xFF2A2A2A)
            ),
            modifier = modifier
        ) {
            content()
        }
    }
}

package com.arflix.tv.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LinkOff
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.ui.theme.*
import com.arflix.tv.util.LocalDeviceType

private const val PRIVACY_POLICY_URL = "https://arvio.tv/privacy"

private fun isRepeatedModalSelect(event: KeyEvent): Boolean =
    event.type == KeyEventType.KeyDown &&
        (event.key == Key.Enter || event.key == Key.DirectionCenter || event.key == Key.NumPadEnter) &&
        event.nativeKeyEvent.repeatCount > 0

@Composable
internal fun CloudModalScrim(
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val scrimInteraction = remember { MutableInteractionSource() }
    val contentInteraction = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.72f))
            .onPreviewKeyEvent(::isRepeatedModalSelect)
            .clickable(
                interactionSource = scrimInteraction,
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.clickable(
                interactionSource = contentInteraction,
                indication = null,
                onClick = {}
            )
        ) {
            content()
        }
    }
}

/**
 * ARVIO Cloud Email/Password modal for authentication and account creation.
 * Optimized for both touch screens (mobile/tablet) and D-pad navigation (TV).
 */
@Composable
fun CloudEmailPasswordModal(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSignIn: () -> Unit,
    onCreateAccount: () -> Unit,
    onOpenPrivacy: () -> Unit = {}
) {
    val context = LocalContext.current
    val effectiveOpenPrivacy: () -> Unit = remember(onOpenPrivacy, context) {
        {
            if (onOpenPrivacy != {}) {
                onOpenPrivacy()
            } else {
                runCatching {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY_POLICY_URL))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        }
    }

    // Focus order: 0 email, 1 password, 2 cancel, 3 sign in, 4 create, 5 privacy
    var focusedIndex by remember { mutableIntStateOf(0) }
    val emailRequester = remember { FocusRequester() }
    val passwordRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) { emailRequester.requestFocus() }
    LaunchedEffect(focusedIndex) {
        when (focusedIndex) {
            0 -> emailRequester.requestFocus()
            1 -> passwordRequester.requestFocus()
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        CloudModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(if (LocalDeviceType.current.isTouchDevice()) 0.92f else 1f)
                    .widthIn(max = 600.dp)
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 32.dp)
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionUp -> {
                                    focusedIndex = when (focusedIndex) {
                                        1 -> 0
                                        2, 3, 4 -> 1
                                        5 -> 3
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.DirectionDown -> {
                                    focusedIndex = when (focusedIndex) {
                                        0 -> 1
                                        1 -> 2
                                        2, 3, 4 -> 5
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.DirectionLeft -> {
                                    focusedIndex = when (focusedIndex) {
                                        4 -> 3
                                        3 -> 2
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.DirectionRight -> {
                                    focusedIndex = when (focusedIndex) {
                                        2 -> 3
                                        3 -> 4
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    when (focusedIndex) {
                                        2 -> { onDismiss(); true }
                                        3 -> { onSignIn(); true }
                                        4 -> { onCreateAccount(); true }
                                        5 -> { effectiveOpenPrivacy(); true }
                                        else -> false
                                    }
                                }
                                else -> false
                            }
                        } else false
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.settings_cloud_signin_title),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.settings_label_email),
                        style = ArflixTypography.caption,
                        color = if (focusedIndex == 0) Pink else TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextField(
                        value = email,
                        onValueChange = onEmailChange,
                        singleLine = true,
                        textStyle = ArflixTypography.body.copy(color = TextPrimary),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                            focusedIndicatorColor = Pink,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Pink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(emailRequester)
                            .border(
                                width = if (focusedIndex == 0) 2.dp else 1.dp,
                                color = if (focusedIndex == 0) Pink else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.settings_label_password),
                        style = ArflixTypography.caption,
                        color = if (focusedIndex == 1) Pink else TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        textStyle = ArflixTypography.body.copy(color = TextPrimary),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                            focusedIndicatorColor = Pink,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Pink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordRequester)
                            .border(
                                width = if (focusedIndex == 1) 2.dp else 1.dp,
                                color = if (focusedIndex == 1) Pink else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val isCancelFocused = focusedIndex == 2
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f)
                            )
                            .clickable { onDismiss() }
                            .border(
                                width = if (isCancelFocused) 2.dp else 0.dp,
                                color = if (isCancelFocused) Pink else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = ArflixTypography.button,
                            color = if (isCancelFocused) TextPrimary else TextSecondary
                        )
                    }

                    val isSignInFocused = focusedIndex == 3
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isSignInFocused) SuccessGreen else Pink.copy(alpha = 0.6f)
                            )
                            .clickable { onSignIn() }
                            .border(
                                width = if (isSignInFocused) 2.dp else 0.dp,
                                color = if (isSignInFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            style = ArflixTypography.button,
                            color = if (isSignInFocused) Color.White else Color.Black
                        )
                    }

                    val isCreateFocused = focusedIndex == 4
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isCreateFocused) SuccessGreen else Color.White.copy(alpha = 0.08f)
                            )
                            .clickable { onCreateAccount() }
                            .border(
                                width = if (isCreateFocused) 2.dp else 0.dp,
                                color = if (isCreateFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_btn_create),
                            style = ArflixTypography.button,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.login_privacy_notice),
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = effectiveOpenPrivacy)
                        .border(
                            width = if (focusedIndex == 5) 2.dp else 1.dp,
                            color = if (focusedIndex == 5) Pink else Color.White.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.login_read_privacy_policy),
                        style = ArflixTypography.button,
                        color = if (focusedIndex == 5) TextPrimary else TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (LocalDeviceType.current.isTouchDevice()) stringResource(R.string.settings_cloud_signin_hint_touch) else stringResource(R.string.settings_cloud_signin_hint_tv),
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

/**
 * ARVIO Cloud device pairing modal (QR Code & verification code).
 * Shows QR code on TV devices, and user code for web activation at auth.arvio.tv.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CloudPairModal(
    verificationUrl: String,
    userCode: String,
    isWorking: Boolean,
    onDismiss: () -> Unit,
    onUseEmailPassword: () -> Unit,
) {
    val effectiveVerificationUrl = remember(verificationUrl, userCode) {
        verificationUrl.ifBlank {
            userCode.takeIf { it.isNotBlank() }?.let { code ->
                "https://auth.arvio.tv/?code=$code"
            }.orEmpty()
        }
    }
    // Focus order: 0 cancel, 1 email/password
    var focusedIndex by remember { mutableIntStateOf(1) }
    val modalFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        modalFocusRequester.requestFocus()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val isMobile = LocalDeviceType.current.isTouchDevice()
        CloudModalScrim(onDismiss = onDismiss) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                val modalWidth = if (isMobile) maxWidth else (maxWidth * 0.62f).coerceIn(520.dp, 760.dp)
                val qrContainerSize = (modalWidth * 0.42f).coerceIn(190.dp, 260.dp)
                val qrBitmapSizePx = ((qrContainerSize.value * 3.2f).toInt()).coerceIn(512, 900)

                Column(
                    modifier = Modifier
                        .then(
                            if (isMobile) Modifier.fillMaxWidth(0.92f).widthIn(max = 600.dp)
                            else Modifier.widthIn(max = modalWidth).fillMaxWidth(0.62f)
                        )
                        .background(BackgroundElevated, RoundedCornerShape(16.dp))
                        .padding(horizontal = if (isMobile) 20.dp else 24.dp, vertical = if (isMobile) 24.dp else 20.dp)
                        .focusRequester(modalFocusRequester)
                        .focusable()
                        .onPreviewKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown) {
                                when (event.key) {
                                    Key.Back, Key.Escape -> {
                                        onDismiss()
                                        true
                                    }
                                    Key.DirectionLeft -> {
                                        focusedIndex = 0
                                        true
                                    }
                                    Key.DirectionRight -> {
                                        focusedIndex = 1
                                        true
                                    }
                                    Key.Enter, Key.DirectionCenter -> {
                                        when (focusedIndex) {
                                            0 -> { onDismiss(); true }
                                            1 -> { onUseEmailPassword(); true }
                                            else -> false
                                        }
                                    }
                                    else -> false
                                }
                            } else false
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.settings_cloud_pairing_title),
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    if (isMobile) {
                        Text(
                            text = stringResource(R.string.settings_pair_signin_link_device),
                            style = ArflixTypography.body,
                            color = TextSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.settings_pair_scan_qr_link_tv),
                            style = ArflixTypography.body,
                            color = TextSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    }

                    // QR code section - only shown on TV (phones can't scan their own screen)
                    if (!isMobile && effectiveVerificationUrl.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .size(qrContainerSize)
                                .background(appBackgroundDark().copy(alpha = 0.92f), RoundedCornerShape(16.dp))
                                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White)
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                QrCodeImage(
                                    data = effectiveVerificationUrl,
                                    sizePx = qrBitmapSizePx,
                                    modifier = Modifier.fillMaxSize(),
                                    foreground = android.graphics.Color.BLACK,
                                    background = android.graphics.Color.WHITE,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (userCode.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentPaste,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.settings_pair_code, userCode),
                                style = ArflixTypography.body,
                                color = TextPrimary
                            )
                        }
                    }

                    if (isWorking) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            LoadingIndicator(size = 20.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.settings_waiting_for_approval),
                                style = ArflixTypography.body,
                                color = TextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (isMobile) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        color = SuccessGreen,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { onUseEmailPassword() }
                                    .padding(vertical = 14.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Link,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = stringResource(R.string.settings_use_email_password),
                                        style = ArflixTypography.button,
                                        color = Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        color = Color.White.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { onDismiss() }
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.cancel),
                                    style = ArflixTypography.button,
                                    color = TextSecondary
                                )
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val isCancelFocused = focusedIndex == 0
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .border(
                                        width = if (isCancelFocused) 2.dp else 0.dp,
                                        color = if (isCancelFocused) Pink else Color.Transparent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { onDismiss() }
                                    .padding(vertical = 12.dp, horizontal = 14.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LinkOff,
                                        contentDescription = null,
                                        tint = if (isCancelFocused) TextPrimary else TextSecondary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = stringResource(R.string.cancel),
                                        style = ArflixTypography.button,
                                        color = if (isCancelFocused) TextPrimary else TextSecondary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            val isFallbackFocused = focusedIndex == 1
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (isFallbackFocused) SuccessGreen else Pink.copy(alpha = 0.6f),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .border(
                                        width = if (isFallbackFocused) 2.dp else 0.dp,
                                        color = if (isFallbackFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { onUseEmailPassword() }
                                    .padding(vertical = 12.dp, horizontal = 14.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Link,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = stringResource(R.string.settings_use_email_password_short),
                                        style = ArflixTypography.button,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

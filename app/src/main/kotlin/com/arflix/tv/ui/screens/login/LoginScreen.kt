package com.arflix.tv.ui.screens.login

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.ui.components.*
import com.arflix.tv.ui.theme.*

/**
 * Login Screen with Email/Password - Optimized for TV
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignUpMode by remember { mutableStateOf(false) }
    var focusedField by remember { mutableStateOf("email") }

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val buttonFocusRequester = remember { FocusRequester() }
    val toggleFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Simple logo animation
    val infiniteTransition = rememberInfiniteTransition(label = "login")
    val logoAlpha by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoAlpha"
    )

    // Handle successful login
    LaunchedEffect(uiState.authState) {
        if (uiState.authState is AuthState.Authenticated) {
            onLoginSuccess()
        }
    }

    // Request initial focus
    LaunchedEffect(Unit) {
        emailFocusRequester.requestFocus()
    }

    // Handle keyboard navigation
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0A0A0F),
                        Color(0xFF0F172A),
                        Color(0xFF0A0A0F)
                    )
                )
            )
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.DirectionDown -> {
                            when (focusedField) {
                                "email" -> {
                                    passwordFocusRequester.requestFocus()
                                    true
                                }
                                "password" -> {
                                    buttonFocusRequester.requestFocus()
                                    true
                                }
                                "button" -> {
                                    toggleFocusRequester.requestFocus()
                                    true
                                }
                                else -> false
                            }
                        }
                        Key.DirectionUp -> {
                            when (focusedField) {
                                "password" -> {
                                    emailFocusRequester.requestFocus()
                                    true
                                }
                                "button" -> {
                                    passwordFocusRequester.requestFocus()
                                    true
                                }
                                "toggle" -> {
                                    buttonFocusRequester.requestFocus()
                                    true
                                }
                                else -> false
                            }
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF1F2937).copy(alpha = 0.25f),
                            Color.Transparent
                        ),
                        radius = 900f
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 96.dp, vertical = 64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ARVIO",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 8.sp,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF8B5CF6),
                                Color(0xFFa78bfa),
                                Color(0xFFEC4899)
                            )
                        )
                    ),
                    modifier = Modifier.alpha(logoAlpha)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Your library, tuned for TV.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Keep your watchlist, history, and Trakt sync tied to your account.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.width(420.dp)
                )
            }

            Column(
                modifier = Modifier
                    .width(420.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF151520))
                    .border(
                        1.dp,
                        Color.White.copy(alpha = 0.08f),
                        RoundedCornerShape(18.dp)
                    )
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isSignUpMode) "Create your account" else "Sign in to continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Error message
                if (uiState.error != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF3D1515))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = uiState.error!!,
                            fontSize = 13.sp,
                            color = Color(0xFFEF4444)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Email field
                PremiumTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(
                        onNext = { passwordFocusRequester.requestFocus() }
                    ),
                    onRequestKeyboard = { keyboardController?.show() },
                    isFocused = focusedField == "email",
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailFocusRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                focusedField = "email"
                            }
                        }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                PremiumTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            buttonFocusRequester.requestFocus()
                        }
                    ),
                    onRequestKeyboard = { keyboardController?.show() },
                    isFocused = focusedField == "password",
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                focusedField = "password"
                            }
                        }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Sign In / Sign Up button
                GradientButton(
                    onClick = {
                        if (isSignUpMode) {
                            viewModel.signUp(email, password)
                        } else {
                            viewModel.signIn(email, password)
                        }
                    },
                    text = if (isSignUpMode) "Sign Up" else "Sign In",
                    isPrimary = true,
                    isFocused = focusedField == "button",
                    enabled = !uiState.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(buttonFocusRequester)
                        .onFocusChanged { if (it.isFocused) focusedField = "button" }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Toggle Sign In / Sign Up
                GradientButton(
                    onClick = { isSignUpMode = !isSignUpMode },
                    text = if (isSignUpMode) "Already have an account? Sign In" else "Don't have an account? Sign Up",
                    isPrimary = false,
                    isFocused = focusedField == "toggle",
                    enabled = !uiState.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(toggleFocusRequester)
                        .onFocusChanged { if (it.isFocused) focusedField = "toggle" }
                )

                // Loading indicator
                if (uiState.isLoading) {
                    Spacer(modifier = Modifier.height(20.dp))
                    SimpleLoadingDots(
                        dotCount = 3,
                        dotSize = 6.dp,
                        color = Color(0xFF8B5CF6)
                    )
                }
            }
        }
    }
}

/**
 * Premium styled text field with gradient border on focus
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PremiumTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onRequestKeyboard: () -> Unit = {},
    isPassword: Boolean = false,
    isFocused: Boolean = false,
    modifier: Modifier = Modifier
) {
    val backgroundColor = BackgroundDark.copy(alpha = 0.6f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (isFocused) {
                    Modifier.background(
                        Brush.linearGradient(
                            colors = listOf(Cyan, Purple, Pink)
                        ),
                        RoundedCornerShape(12.dp)
                    )
                } else {
                    Modifier.background(
                        BorderLight,
                        RoundedCornerShape(12.dp)
                    )
                }
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp)
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Enter, Key.NumPadEnter, Key.DirectionCenter -> {
                                onRequestKeyboard()
                                true
                            }
                            else -> false
                        }
                    } else {
                        false
                    }
                },
            textStyle = TextStyle(
                fontSize = 15.sp,
                color = TextPrimary,
                fontWeight = FontWeight.Normal
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            singleLine = true,
            cursorBrush = SolidColor(Cyan),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 15.sp,
                            color = TextTertiary
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

/**
 * Gradient button with premium styling
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GradientButton(
    onClick: () -> Unit,
    text: String,
    isPrimary: Boolean,
    isFocused: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val focusedBackground = AccentWhite
    val focusedText = ArcticBlack
    val noScale = ButtonDefaults.scale(1f, 1f, 1f, 1f, 1f)

    Box(
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (isPrimary) {
                    if (isFocused) {
                        Modifier.background(focusedBackground)
                    } else {
                        Modifier.background(
                            Color.Black,
                            RoundedCornerShape(12.dp)
                        )
                    }
                } else {
                    Modifier
                        .background(
                            if (isFocused) focusedBackground else BackgroundCard,
                            RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            brush = Brush.linearGradient(listOf(BorderLight, BorderLight)),
                            shape = RoundedCornerShape(12.dp)
                        )
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.colors(
                containerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            scale = noScale,
            shape = ButtonDefaults.shape(RoundedCornerShape(12.dp))
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isFocused) focusedText else if (isPrimary) TextPrimary else TextSecondary
            )
        }
    }
}

private val EaseInOutCubic = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)

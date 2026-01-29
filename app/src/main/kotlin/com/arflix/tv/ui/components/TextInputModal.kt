package com.arflix.tv.ui.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * TV-friendly text input modal with on-screen keyboard
 */
@OptIn(ExperimentalTvMaterial3Api::class)
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
    var focusedRow by remember(isVisible) { mutableIntStateOf(0) }
    var focusedCol by remember(isVisible) { mutableIntStateOf(0) }
    var focusedAction by remember(isVisible) { mutableIntStateOf(-1) } // -1 = keyboard, 0 = clear, 1 = cancel, 2 = ok
    
    // Keyboard layout
    val keyboard = listOf(
        listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
        listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', '⌫'),
        listOf('z', 'x', 'c', 'v', 'b', 'n', 'm', '.', '/', '-'),
        listOf(':', '_', '@', '#', '&', '?', '!', ' ', ' ', ' ')
    )
    
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
                                onCancel()
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusedAction >= 0) {
                                    focusedAction = -1
                                    focusedRow = keyboard.size - 1
                                } else if (focusedRow > 0) {
                                    focusedRow--
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusedAction == -1 && focusedRow >= keyboard.size - 1) {
                                    focusedAction = 0
                                } else if (focusedAction == -1) {
                                    focusedRow++
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                if (focusedAction >= 0) {
                                    if (focusedAction > 0) focusedAction--
                                } else {
                                    if (focusedCol > 0) focusedCol--
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                if (focusedAction >= 0) {
                                    if (focusedAction < 2) focusedAction++
                                } else {
                                    val maxCol = keyboard.getOrNull(focusedRow)?.size?.minus(1) ?: 0
                                    if (focusedCol < maxCol) focusedCol++
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                when (focusedAction) {
                                    0 -> inputText = "" // Clear
                                    1 -> onCancel() // Cancel
                                    2 -> onConfirm(inputText) // OK
                                    else -> {
                                        val char = keyboard.getOrNull(focusedRow)?.getOrNull(focusedCol)
                                        if (char == '⌫') {
                                            if (inputText.isNotEmpty()) {
                                                inputText = inputText.dropLast(1)
                                            }
                                        } else if (char != null) {
                                            inputText += char
                                        }
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
                    .width(600.dp)
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
                
                // Input field
                val displayText = if (isPassword) "•".repeat(inputText.length) else inputText
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BackgroundElevated, RoundedCornerShape(12.dp))
                        .border(2.dp, Pink.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    if (inputText.isEmpty() && hint.isNotEmpty()) {
                        Text(
                            text = hint,
                            style = ArflixTypography.body,
                            color = TextSecondary.copy(alpha = 0.5f)
                        )
                    }
                    Row {
                        Text(
                            text = displayText,
                            style = ArflixTypography.body,
                            color = TextPrimary
                        )
                        // Cursor
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(24.dp)
                                .background(Pink)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Keyboard
                keyboard.forEachIndexed { rowIndex, row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        row.forEachIndexed { colIndex, char ->
                            val isFocused = focusedAction == -1 && focusedRow == rowIndex && focusedCol == colIndex
                            
                            Box(
                                modifier = Modifier
                                    .width(if (char == ' ') 80.dp else 44.dp)
                                    .height(44.dp)
                                    .background(
                                        if (isFocused) Pink else Color.White.copy(alpha = 0.1f),
                                        RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = when (char) {
                                        ' ' -> "SPACE"
                                        '⌫' -> "⌫"
                                        else -> char.uppercase()
                                    },
                                    style = if (char == ' ') ArflixTypography.caption else ArflixTypography.button,
                                    color = if (isFocused) Color.White else TextSecondary
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Action buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ActionButton(
                        text = "CLEAR",
                        isFocused = focusedAction == 0,
                        onClick = { inputText = "" }
                    )
                    ActionButton(
                        text = "CANCEL",
                        isFocused = focusedAction == 1,
                        onClick = onCancel
                    )
                    ActionButton(
                        text = "OK",
                        isFocused = focusedAction == 2,
                        isPrimary = true,
                        onClick = { onConfirm(inputText) }
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
            .width(100.dp)
            .height(44.dp)
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

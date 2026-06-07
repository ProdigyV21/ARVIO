package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import com.arflix.tv.util.LocalDeviceType

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LoadingPane(message: String?, percent: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(color = LiveColors.Accent)
        if (!message.isNullOrBlank()) {
            Box(Modifier.padding(top = 20.dp)) {
                Text(message, style = LiveType.CellTitle.copy(color = LiveColors.FgDim))
            }
        }
        if (percent > 0) {
            LinearProgressIndicator(
                progress = { (percent / 100f).coerceIn(0f, 1f) },
                modifier = Modifier.padding(top = 12.dp).width(260.dp),
                color = LiveColors.Accent,
                trackColor = LiveColors.Divider,
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun EmptyStatePane(
    message: String,
    actionLabel: String,
    onAction: () -> Unit,
    modifier: Modifier = Modifier,
    isFocused: Boolean = true,
    onMoveUp: (() -> Unit)? = null,
    focusRequester: FocusRequester? = null
) {
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(message, style = LiveType.ProgramTitle.copy(color = LiveColors.FgDim))
        if (isTouchDevice) {
            Box(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onAction() }
                    .padding(horizontal = 18.dp, vertical = 10.dp),
            ) {
                Text(actionLabel, style = LiveType.CatLabel.copy(color = Color.White))
            }
        } else {
            val focusModifier = if (focusRequester != null) {
                Modifier.focusRequester(focusRequester)
            } else {
                Modifier
            }
            val background = if (isFocused) Color.White else Color.Black
            val contentColor = if (isFocused) Color.Black else Color.White
            val borderModifier = if (isFocused) {
                Modifier
            } else {
                Modifier.border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(background)
                    .then(borderModifier),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onAction,
                    modifier = Modifier
                        .then(focusModifier)
                        .onPreviewKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown && event.key == Key.DirectionUp) {
                                onMoveUp?.invoke()
                                true
                            } else {
                                false
                            }
                        },
                    colors = ButtonDefaults.colors(
                        containerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        contentColor = contentColor,
                        focusedContentColor = contentColor
                    ),
                    scale = ButtonDefaults.scale(1f, 1f, 1f, 1f, 1f),
                    shape = ButtonDefaults.shape(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = actionLabel,
                        style = LiveType.CatLabel.copy(color = contentColor)
                    )
                }
            }
        }
    }
}

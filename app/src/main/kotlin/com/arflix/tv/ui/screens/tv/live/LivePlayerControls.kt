package com.arflix.tv.ui.screens.tv.live

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import kotlinx.coroutines.delay
import java.util.Date

/**
 * Building blocks of the fullscreen Live TV controls.
 *
 * Focus never depends on the accent colour: at rest a control is dark glass with a white
 * glyph, focused it turns solid white with a black glyph and grows a little. The accent used
 * to fill the buttons at rest, and with the default white accent a focused button looked the
 * same as an unfocused one.
 */
internal object PlayerControlStyle {
    val Glass = Color.White.copy(alpha = 0.14f)
    val GlassStrong = Color.White.copy(alpha = 0.22f)
    val GlassBorder = Color.White.copy(alpha = 0.18f)
    val Focused = Color.White
    val OnFocused = Color(0xFF0B0B0C)
    const val FocusScale = 1.12f
    const val ChipFocusScale = 1.08f
}

/** Scale used by the controls: springs up on focus and settles back when focus leaves. */
@Composable
internal fun playerFocusScale(focused: Boolean, target: Float): Float {
    val scale by animateFloatAsState(
        targetValue = if (focused) target else 1f,
        animationSpec = spring(dampingRatio = 0.62f, stiffness = Spring.StiffnessMediumLow),
        label = "player-focus-scale",
    )
    return scale
}

@Composable
internal fun PlayerRoundButton(
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    emphasis: Boolean = false,
    size: Dp = if (emphasis) 58.dp else 44.dp,
    iconSize: Dp = if (emphasis) 32.dp else 24.dp,
    focusRequester: FocusRequester? = null,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    val scale = playerFocusScale(focused, PlayerControlStyle.FocusScale)
    val bg by animateColorAsState(
        when {
            focused -> PlayerControlStyle.Focused
            emphasis -> PlayerControlStyle.GlassStrong
            else -> PlayerControlStyle.Glass
        },
        tween(140),
        label = "player-round-bg",
    )
    val fg by animateColorAsState(
        if (focused) PlayerControlStyle.OnFocused else Color.White,
        tween(140),
        label = "player-round-fg",
    )
    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .then(if (focused) Modifier.shadow(14.dp, CircleShape, ambientColor = Color.White, spotColor = Color.White) else Modifier)
            .size(size)
            .clip(CircleShape)
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .onFocusChanged { focused = it.isFocused }
            .background(bg)
            .border(1.dp, if (focused) Color.Transparent else PlayerControlStyle.GlassBorder, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = fg,
            modifier = Modifier.size(iconSize),
        )
    }
}

/** Pill with an icon and a short label (Audio, Subtitles, Picture: Fit ...). */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PlayerChipButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null,
    highlighted: Boolean = false,
) {
    var focused by remember { mutableStateOf(false) }
    val scale = playerFocusScale(focused, PlayerControlStyle.ChipFocusScale)
    val bg by animateColorAsState(
        when {
            focused -> PlayerControlStyle.Focused
            highlighted -> LiveColors.LiveRed
            else -> PlayerControlStyle.Glass
        },
        tween(140),
        label = "player-chip-bg",
    )
    val fg by animateColorAsState(
        if (focused) PlayerControlStyle.OnFocused else Color.White,
        tween(140),
        label = "player-chip-fg",
    )
    val shape = RoundedCornerShape(50)
    Row(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .then(if (focused) Modifier.shadow(12.dp, shape, ambientColor = Color.White, spotColor = Color.White) else Modifier)
            .height(40.dp)
            .clip(shape)
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .onFocusChanged { focused = it.isFocused }
            .background(bg)
            .border(1.dp, if (focused || highlighted) Color.Transparent else PlayerControlStyle.GlassBorder, shape)
            .clickable(onClick = onClick)
            .padding(start = 14.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = fg, modifier = Modifier.size(18.dp))
        Text(
            text = label,
            style = LiveType.Badge.copy(color = fg, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, lineHeight = 16.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** Small red "LIVE" marker for the info bar; not focusable. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PlayerLiveBadge(label: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(LiveColors.LiveRed)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(Modifier.size(5.dp).clip(CircleShape).background(Color.White))
        Text(
            text = label,
            style = LiveType.Badge.copy(color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.6.sp),
        )
    }
}

/**
 * Loading ring for a tune or a stall. It waits [PlayerBufferingRevealMs] before showing, so a
 * fast zap never flashes it, and says what is happening once the wait gets long.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PlayerBufferingIndicator(
    buffering: Boolean,
    loadingLabel: String,
    slowLabel: String,
    modifier: Modifier = Modifier,
) {
    var shown by remember { mutableStateOf(false) }
    var slow by remember { mutableStateOf(false) }
    LaunchedEffect(buffering) {
        if (buffering) {
            delay(PlayerBufferingRevealMs)
            shown = true
            delay(PlayerBufferingSlowMs - PlayerBufferingRevealMs)
            slow = true
        } else {
            shown = false
            slow = false
        }
    }
    AnimatedVisibility(
        visible = shown,
        enter = fadeIn(tween(180)) + scaleIn(tween(220), initialScale = 0.85f),
        exit = fadeOut(tween(160)) + scaleOut(tween(160), targetScale = 0.9f),
        modifier = modifier.fillMaxSize(),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.semantics { contentDescription = if (slow) slowLabel else loadingLabel },
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.55f)),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.15f),
                        strokeWidth = 3.dp,
                    )
                }
                if (slow) {
                    Text(
                        text = slowLabel,
                        style = LiveType.ChannelName.copy(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.Black.copy(alpha = 0.55f))
                            .padding(horizontal = 14.dp, vertical = 6.dp),
                    )
                }
            }
        }
    }
}

internal const val PlayerBufferingRevealMs = 350L
internal const val PlayerBufferingSlowMs = 6_000L

/** Weekday, date and time in the device's language and 12/24-hour setting. */
internal fun formatPlayerHeaderClock(context: Context, millis: Long): String = runCatching {
    val date = DateFormat.format(DateFormat.getBestDateTimePattern(java.util.Locale.getDefault(), "EEEdMMM"), millis)
    val time = DateFormat.getTimeFormat(context).format(Date(millis))
    "$date  $time"
}.getOrDefault("")

/**
 * A control in the button row. On a remote it is a round icon whose caption appears just below it
 * while it has focus, so the row stays one short line; the caption is laid out outside the
 * button and never moves its neighbours. On a touch screen there is no focus to reveal a
 * caption, so the label is written out next to the icon instead.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PlayerActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    touch: Boolean = false,
    emphasis: Boolean = false,
    active: Boolean = false,
    focusRequester: FocusRequester? = null,
) {
    if (touch) {
        PlayerChipButton(
            icon = icon,
            label = label,
            onClick = onClick,
            modifier = modifier,
            focusRequester = focusRequester,
            highlighted = active,
        )
        return
    }
    var focused by remember { mutableStateOf(false) }
    val captionAlpha by animateFloatAsState(if (focused) 1f else 0f, tween(if (focused) 160 else 90), label = "player-caption")
    val gapPx = with(androidx.compose.ui.platform.LocalDensity.current) { 8.dp.roundToPx() }
    androidx.compose.ui.layout.Layout(
        modifier = modifier.onFocusChanged { focused = it.hasFocus },
        content = {
            Box {
                PlayerRoundButton(
                    icon = icon,
                    contentDescription = label,
                    emphasis = emphasis,
                    focusRequester = focusRequester,
                    onClick = onClick,
                )
                if (active) {
                    Box(
                        Modifier
                            .align(Alignment.TopEnd)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(LiveColors.LiveRed)
                            .border(1.5.dp, Color.Black, CircleShape),
                    )
                }
            }
            Text(
                text = label,
                style = LiveType.Badge.copy(color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, lineHeight = 16.sp),
                maxLines = 1,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = captionAlpha
                        translationY = (captionAlpha - 1f) * 8f
                    }
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Black.copy(alpha = 0.72f))
                    .padding(horizontal = 10.dp, vertical = 4.dp),
            )
        },
    ) { measurables, constraints ->
        val button = measurables[0].measure(constraints.copy(minWidth = 0, minHeight = 0))
        val caption = measurables[1].measure(androidx.compose.ui.unit.Constraints())
        layout(button.width, button.height) {
            button.placeRelative(0, 0)
            caption.placeRelative((button.width - caption.width) / 2, button.height + gapPx)
        }
    }
}

/**
 * Whether rewind/forward and seek-bar scrubbing can do anything: in catch-up, when the current
 * programme can be replayed from the archive, or when the stream itself carries a seekable live
 * window of some length. Otherwise those controls are left out instead of doing nothing.
 */
internal fun livePlayerSeekable(
    catchup: Boolean,
    canReplayNow: Boolean,
    playerSeekable: Boolean,
    playerDurationMs: Long,
): Boolean = catchup || canReplayNow ||
    (playerSeekable && playerDurationMs != androidx.media3.common.C.TIME_UNSET && playerDurationMs >= MinSeekableWindowMs)

private const val MinSeekableWindowMs = 30_000L

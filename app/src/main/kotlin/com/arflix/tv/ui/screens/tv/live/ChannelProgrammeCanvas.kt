package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvProgram

/** Read-only TV guide cells need no child layouts or graphics layers. */
@Composable
internal fun ChannelProgrammeCanvas(
    program: IptvProgram,
    width: Dp,
    rowHeight: Dp,
    isNow: Boolean,
    isPast: Boolean,
    isCatchupSupported: Boolean,
    contentStartOffsetPx: () -> Int,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    val measurer = rememberTextMeasurer(cacheSize = 4)
    val time = formatClock(program.startUtcMillis)
    val minutes = ((program.endUtcMillis - program.startUtcMillis) / 60_000L).coerceAtLeast(0)
    val duration = stringResource(R.string.live_label_duration_min, minutes)
    val badge = when {
        width < 150.dp -> null
        isPast && isCatchupSupported -> stringResource(R.string.live_badge_archive)
        else -> null
    }
    Box(modifier.height(rowHeight).width(width)
        .clearAndSetSemantics {
            this[SemanticsProperties.Text] = listOfNotNull(
                AnnotatedString(program.title), AnnotatedString(time),
                program.description?.takeIf { it.isNotBlank() }?.let(::AnnotatedString),
            )
            if (isNow || (isPast && isCatchupSupported)) onClick { onClick(); true }
        }
        .drawWithCache {
            val x = 7.dp.toPx() + contentStartOffsetPx()
            val available = (size.width - x - 7.dp.toPx()).toInt().coerceAtLeast(1)
            val dimmed = isPast && !isCatchupSupported
            val titleColor = if (dimmed) LiveColors.Fg.copy(alpha = 0.55f) else LiveColors.Fg
            val badgeLayout = badge?.let {
                measurer.measure(it, LiveType.Badge.copy(fontSize = 7.5.sp, lineHeight = 9.sp))
            }
            val badgeWidth = badgeLayout?.let { it.size.width + 8.dp.toPx() } ?: 0f
            val titleX = if (badgeLayout != null) badgeWidth + 6.dp.toPx() else 0f
            val title = measurer.measure(program.title,
                LiveType.CellTitle.copy(color = titleColor, fontSize = 10.sp, lineHeight = 12.sp),
                overflow = TextOverflow.Ellipsis, maxLines = if (width < 120.dp) 2 else 1,
                constraints = Constraints(maxWidth = (available - titleX).toInt().coerceAtLeast(1)))
            val footer = if (width >= 120.dp) measurer.measure(
                "$time - ${formatClock(program.endUtcMillis)}",
                LiveType.TimeMono.copy(color = LiveColors.FgDim, fontSize = 8.sp, lineHeight = 10.sp),
                maxLines = 1, overflow = TextOverflow.Ellipsis,
                constraints = Constraints(maxWidth = available)) else null
            onDrawBehind {
                val origin = Offset(1.dp.toPx(), 1.dp.toPx())
                val cellSize = Size((size.width - 2.dp.toPx()).coerceAtLeast(0f), (size.height - 2.dp.toPx()).coerceAtLeast(0f))
                val radius = CornerRadius(LiveDims.CellRadius.toPx())
                drawRoundRect(if (isNow) LiveColors.FocusBg else LiveColors.Panel, origin, cellSize, radius)
                if (badgeLayout != null) {
                    drawRoundRect(LiveColors.PanelRaised,
                        Offset(x, 5.dp.toPx()), Size(badgeWidth, badgeLayout.size.height + 1.dp.toPx()), CornerRadius(3.dp.toPx()))
                    drawText(badgeLayout, color = LiveColors.FgDim,
                        topLeft = Offset(x + 4.dp.toPx(), 5.5.dp.toPx()))
                }
                drawText(title, topLeft = Offset(x + titleX, 5.dp.toPx()))
                if (footer != null) drawText(footer, topLeft = Offset(x, size.height - 5.dp.toPx() - footer.size.height))
            }
        })
}

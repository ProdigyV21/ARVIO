package com.arflix.tv.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextSecondary

/** One tickable value inside a filter panel. */
internal data class PanelOption(
    val key: String,
    val label: String,
    val isSelected: Boolean,
    val onToggle: () -> Unit
)

/**
 * A drop-down field: its own caption, the value it currently shows, and the list behind it.
 *
 * Eleven rating values in a row would be twice as wide as the panel, so the rating ends are
 * fields that open a list rather than eleven tiles each — the shape the user asked for.
 */
internal data class PanelDropdown(
    val key: String,
    val label: String,
    val valueLabel: String,
    val entries: List<PanelOption>
)

/** What a section draws: plain tiles, or drop-down fields. */
internal sealed interface PanelEntries {
    data class Tiles(val options: List<PanelOption>) : PanelEntries
    data class Dropdowns(val fields: List<PanelDropdown>) : PanelEntries
}

/**
 * One captioned block inside a panel.
 *
 * Sections are the whole of the rebuild. The rating panel used to be seventeen identical tiles
 * in one grid with nothing saying which belonged to what, and the genre switch had to sit
 * outside the grid because a flat grid had no room for it. A panel is now a list of sections,
 * each with its own caption and its own row width.
 */
internal data class PanelSection(
    val key: String,
    val caption: String? = null,
    val entries: PanelEntries,
    val columns: Int = PANEL_COLUMNS
) {
    val count: Int
        get() = when (entries) {
            is PanelEntries.Tiles -> entries.options.size
            is PanelEntries.Dropdowns -> entries.fields.size
        }

    val shape: PanelSectionShape get() = PanelSectionShape(count, columns)
}

/** The 0–10 bar over the rating panel. It shows what is set; it is not a control. */
internal data class PanelBar(
    val from: Double,
    val to: Double,
    val ticks: List<String>,
    val span: Double = 10.0
)

/** What one open filter panel shows. */
internal data class FilterPanelSpec(
    val id: DiscoverFilterId,
    val title: String,
    val subtitle: String? = null,
    val sections: List<PanelSection>,
    val bar: PanelBar? = null,
    val footer: String? = null
) {
    val shapes: List<PanelSectionShape> get() = sections.map { it.shape }

    /** The drop-down field under [focus], or `null` when the focus is on an ordinary tile. */
    fun dropdownAt(focus: PanelFocus): PanelDropdown? {
        val entries = sections.getOrNull(focus.section)?.entries as? PanelEntries.Dropdowns
        return entries?.fields?.getOrNull(focus.entry)
    }

    /** The drop-down named by [key], wherever it sits — the phone has no focus to go by. */
    fun dropdownWithKey(key: String?): PanelDropdown? {
        if (key == null) return null
        return sections.asSequence()
            .mapNotNull { it.entries as? PanelEntries.Dropdowns }
            .flatMap { it.fields.asSequence() }
            .firstOrNull { it.key == key }
    }

    /** The tile under [focus], or `null` when the focus is on a drop-down field. */
    fun optionAt(focus: PanelFocus): PanelOption? {
        val entries = sections.getOrNull(focus.section)?.entries as? PanelEntries.Tiles
        return entries?.options?.getOrNull(focus.entry)
    }
}

/** Three across, as in the approved draft — wide enough for "Documentary", narrow enough to scan. */
internal const val PANEL_COLUMNS = 3

private val PANEL_MAX_HEIGHT = 300.dp
private val FIELD_HEIGHT = 36.dp

/**
 * One drawn line of a panel: either a row of a section's entries, or one value of an open list.
 *
 * Rows rather than whole sections are the scrolling unit, so the genre tiles — seven rows of
 * them — can be scrolled through as well as stepped between.
 */
private sealed interface PanelLine {
    val key: String

    data class Entries(
        override val key: String,
        val sectionIndex: Int,
        val caption: String?,
        val section: PanelSection,
        val range: IntRange
    ) : PanelLine

    data class ListValue(
        override val key: String,
        val option: PanelOption,
        val index: Int
    ) : PanelLine
}

/**
 * The panel that opens under a filter chip on a TV, and rises from the bottom edge on a phone.
 *
 * Focus works the way it does everywhere else on this screen: the caller owns the position and
 * the panel only paints it. [focus] is `null` on a touch device, where the panel is tapped.
 */
@Composable
internal fun DiscoverFilterPanel(
    spec: FilterPanelSpec,
    focus: PanelFocus?,
    openDropdownKey: String?,
    dropdownFocusIndex: Int,
    isTouchDevice: Boolean,
    modifier: Modifier = Modifier,
    onOpenDropdown: (PanelDropdown) -> Unit = {},
    onPickFromDropdown: (PanelOption) -> Unit = {}
) {
    val openDropdown = spec.dropdownWithKey(openDropdownKey)
    val lines = panelLines(spec, openDropdown)
    val listState = rememberLazyListState()
    val maxContentHeight = if (isTouchDevice) {
        (LocalConfiguration.current.screenHeightDp.dp * 0.45f).coerceIn(280.dp, 380.dp)
    } else {
        PANEL_MAX_HEIGHT
    }
    // Keyed on numbers only: a spec carries lambdas and is a new object on every recomposition,
    // so keying the scroll on it would restart the animation on every frame.
    LaunchedEffect(spec.id, spec.shapes, focus, openDropdownKey, dropdownFocusIndex) {
        val target = focusedLine(lines, focus, openDropdown, dropdownFocusIndex)
        if (target >= 0) listState.animateScrollToItem(target)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF101012), RoundedCornerShape(12.dp))
            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
            .padding(horizontal = 18.dp, vertical = 14.dp)
            .testTag("filter-panel-${spec.id}")
    ) {
        PanelHeader(spec)
        spec.bar?.let { PanelRangeBar(it) }
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().heightIn(max = maxContentHeight).padding(top = 10.dp)
        ) {
            items(lines, key = { it.key }) { line ->
                when (line) {
                    is PanelLine.Entries -> PanelEntriesLine(
                        line = line,
                        focusedEntry = focus
                            ?.takeIf { it.section == line.sectionIndex && it.entry in line.range }
                            ?.entry,
                        openDropdownKey = openDropdownKey,
                        isTouchDevice = isTouchDevice,
                        onOpenDropdown = onOpenDropdown
                    )
                    is PanelLine.ListValue -> PanelListValue(
                        option = line.option,
                        isVisuallyFocused = !isTouchDevice && line.index == dropdownFocusIndex,
                        isTouchDevice = isTouchDevice,
                        onPick = { onPickFromDropdown(line.option) }
                    )
                }
            }
        }
        spec.footer?.let {
            Text(
                it,
                style = ArflixTypography.caption.copy(fontSize = 11.sp),
                color = TextSecondary,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

/** Every section flattened into the rows it draws, with an open list folded in under its field. */
private fun panelLines(spec: FilterPanelSpec, openDropdown: PanelDropdown?): List<PanelLine> =
    buildList {
        spec.sections.forEachIndexed { sectionIndex, section ->
            val columns = section.columns.coerceAtLeast(1)
            var start = 0
            while (start < section.count) {
                val end = minOf(start + columns, section.count)
                add(
                    PanelLine.Entries(
                        key = "${section.key}_$start",
                        sectionIndex = sectionIndex,
                        caption = section.caption.takeIf { start == 0 },
                        section = section,
                        range = start until end
                    )
                )
                if (openDropdown != null && section.entries is PanelEntries.Dropdowns &&
                    section.entries.fields.subList(start, end).any { it.key == openDropdown.key }
                ) {
                    openDropdown.entries.forEachIndexed { index, option ->
                        add(PanelLine.ListValue("${openDropdown.key}_value_${option.key}", option, index))
                    }
                }
                start = end
            }
        }
    }

/** The line the focus is on, so the list can scroll it into view. */
private fun focusedLine(
    lines: List<PanelLine>,
    focus: PanelFocus?,
    openDropdown: PanelDropdown?,
    dropdownFocusIndex: Int
): Int {
    if (focus == null || focus.hasLeft) return -1
    if (openDropdown != null) {
        return lines.indexOfFirst { it is PanelLine.ListValue && it.index == dropdownFocusIndex }
    }
    return lines.indexOfFirst {
        it is PanelLine.Entries && it.sectionIndex == focus.section && focus.entry in it.range
    }
}

@Composable
private fun PanelEntriesLine(
    line: PanelLine.Entries,
    focusedEntry: Int?,
    openDropdownKey: String?,
    isTouchDevice: Boolean,
    onOpenDropdown: (PanelDropdown) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        line.caption?.let {
            Text(
                it,
                style = ArflixTypography.caption.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.8.sp
                ),
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            line.range.forEach { index ->
                Box(Modifier.weight(1f)) {
                    when (val entries = line.section.entries) {
                        is PanelEntries.Tiles -> PanelOptionChip(
                            option = entries.options[index],
                            isVisuallyFocused = !isTouchDevice && focusedEntry == index,
                            isTouchDevice = isTouchDevice
                        )
                        is PanelEntries.Dropdowns -> PanelDropdownField(
                            field = entries.fields[index],
                            isVisuallyFocused = !isTouchDevice && focusedEntry == index,
                            isOpen = entries.fields[index].key == openDropdownKey,
                            isTouchDevice = isTouchDevice,
                            onOpen = { onOpenDropdown(entries.fields[index]) }
                        )
                    }
                }
            }
            // The tiles of a short last row keep the width they have in a full one, so the
            // columns of a section stay columns instead of stretching to fill the gap.
            repeat(line.section.columns - (line.range.last - line.range.first + 1)) {
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun PanelHeader(spec: FilterPanelSpec) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            spec.title,
            style = ArflixTypography.body.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
            color = Color.White
        )
        spec.subtitle?.let {
            Text(
                "  ·  $it",
                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                color = TextSecondary
            )
        }
    }
}

/**
 * The 0–10 bar of the rating panel, with the set range filled in.
 *
 * Weights rather than a canvas: the bar is three boxes side by side, so it follows the panel's
 * width without a single measured pixel.
 */
@Composable
private fun PanelRangeBar(bar: PanelBar) {
    val span = bar.span.takeIf { it > 0.0 } ?: 1.0
    val before = (bar.from / span).coerceIn(0.0, 1.0)
    val filled = ((bar.to - bar.from) / span).coerceIn(0.0, 1.0)
    val after = (1.0 - before - filled).coerceAtLeast(0.0)
    Column(Modifier.fillMaxWidth().padding(top = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
        ) {
            listOf(before to false, filled to true, after to false).forEach { (share, isFilled) ->
                Box(
                    Modifier
                        .weight(share.toFloat().coerceAtLeast(0.0001f))
                        .height(8.dp)
                        .background(
                            if (isFilled) Color.White.copy(alpha = 0.88f) else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
        ) {
            bar.ticks.forEach {
                Text(it, style = ArflixTypography.caption.copy(fontSize = 10.sp), color = TextSecondary)
            }
        }
    }
}

@Composable
private fun PanelDropdownField(
    field: PanelDropdown,
    isVisuallyFocused: Boolean,
    isOpen: Boolean,
    isTouchDevice: Boolean,
    onOpen: () -> Unit
) {
    val accent = resolveAccentColor(fallback = Color.White)
    val shape = RoundedCornerShape(8.dp)
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            field.label,
            style = ArflixTypography.caption.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.8.sp
            ),
            color = TextSecondary,
            maxLines = 1
        )
        Spacer(Modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(1f)
                .height(FIELD_HEIGHT)
                .background(Color.White.copy(alpha = if (isOpen) 0.12f else 0.06f), shape)
                .border(
                    if (isVisuallyFocused) 2.dp else 1.dp,
                    if (isVisuallyFocused) accent else Color.White.copy(alpha = 0.18f),
                    shape
                )
                .then(if (isTouchDevice) Modifier.clickable { onOpen() } else Modifier)
                .padding(horizontal = 12.dp)
                .testTag("filter-dropdown-${field.key}")
        ) {
            Text(
                field.valueLabel,
                style = ArflixTypography.caption.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun PanelListValue(
    option: PanelOption,
    isVisuallyFocused: Boolean,
    isTouchDevice: Boolean,
    onPick: () -> Unit
) {
    val accent = resolveAccentColor(fallback = Color.White)
    val shape = RoundedCornerShape(8.dp)
    val background = when {
        isVisuallyFocused -> Color.White.copy(alpha = 0.16f)
        option.isSelected -> Color.White.copy(alpha = 0.92f)
        else -> Color.White.copy(alpha = 0.04f)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { selected = option.isSelected; role = Role.RadioButton }
            .background(background, shape)
            .border(
                if (isVisuallyFocused) 2.dp else 1.dp,
                if (isVisuallyFocused) accent else Color.Transparent,
                shape
            )
            .then(if (isTouchDevice) Modifier.clickable { onPick() } else Modifier)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .testTag("filter-value-${option.key}")
    ) {
        Text(
            option.label,
            style = ArflixTypography.caption.copy(fontSize = 13.sp),
            color = if (option.isSelected && !isVisuallyFocused) Color.Black else Color.White,
            maxLines = 1
        )
    }
}

@Composable
private fun PanelOptionChip(option: PanelOption, isVisuallyFocused: Boolean, isTouchDevice: Boolean) {
    val accent = resolveAccentColor(fallback = Color.White)
    val shape = RoundedCornerShape(8.dp)
    val background = when {
        isVisuallyFocused -> Color.White.copy(alpha = 0.16f)
        option.isSelected -> Color.White.copy(alpha = 0.92f)
        else -> Color.White.copy(alpha = 0.06f)
    }
    val foreground = if (option.isSelected && !isVisuallyFocused) Color.Black else Color.White
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { selected = option.isSelected; role = Role.Checkbox }
            .background(background, shape)
            .border(
                if (isVisuallyFocused) 2.dp else 1.dp,
                if (isVisuallyFocused) accent else Color.White.copy(alpha = 0.18f),
                shape
            )
            .then(if (isTouchDevice) Modifier.clickable { option.onToggle() } else Modifier)
            .padding(horizontal = 12.dp, vertical = 9.dp)
            .testTag("filter-option-${option.key}")
    ) {
        Text(
            option.label,
            style = ArflixTypography.caption.copy(fontSize = 12.sp),
            color = foreground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R

private data class TouchCategoryRailItem(
    val id: String,
    val label: String,
    val count: Int,
    val playlistSectionId: String? = null,
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TouchCategoryRail(
    tree: LiveCategoryTree,
    selectedId: String,
    playlistSections: List<PlaylistCategorySection> = emptyList(),
    onSelect: (String) -> Unit,
    onOpenSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expandedPlaylistIds by rememberSaveable { mutableStateOf(emptyList<String>()) }
    LaunchedEffect(selectedId, playlistSections) {
        playlistSections.firstOrNull { section ->
            section.categories.any { it.containsId(selectedId) }
        }?.id?.let { sectionId ->
            if (sectionId !in expandedPlaylistIds) {
                expandedPlaylistIds = expandedPlaylistIds + sectionId
            }
        }
    }
    val items = rememberTouchRailItems(tree, selectedId, playlistSections, expandedPlaylistIds)

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
    ) {
        item(key = "search") {
            Row(
                modifier = Modifier
                    .height(38.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LiveColors.PanelRaised)
                    .clickable(onClick = onOpenSearch)
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = LiveColors.FgDim,
                )
                Text(
                    text = stringResource(R.string.live_label_search_channels),
                    style = LiveType.CatLabel.copy(color = LiveColors.Fg),
                )
            }
        }

        itemsIndexed(items, key = { index, item -> "${item.id}#$index" }) { _, item ->
            val sectionId = item.playlistSectionId
            val isSectionHeader = sectionId != null
            val isSectionOpen = sectionId != null && sectionId in expandedPlaylistIds
            val active = if (isSectionHeader) {
                playlistSections.firstOrNull { it.id == sectionId }
                    ?.categories
                    ?.any { it.containsId(selectedId) } == true
            } else {
                selectedId == item.id
            }
            Box(
                modifier = Modifier
                    .height(38.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (active) LiveColors.Accent else LiveColors.Panel)
                    .clickable {
                        if (sectionId != null) {
                            expandedPlaylistIds = if (isSectionOpen) {
                                expandedPlaylistIds - sectionId
                            } else {
                                expandedPlaylistIds + sectionId
                            }
                        } else {
                            onSelect(item.id)
                        }
                    }
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    if (isSectionHeader) {
                        Icon(
                            imageVector = if (isSectionOpen) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = if (active) LiveColors.Bg else LiveColors.FgDim,
                        )
                    }
                    Text(
                        text = item.label,
                        style = LiveType.CatLabel.copy(
                            color = if (active) LiveColors.Bg else LiveColors.Fg,
                        ),
                    )
                    if (item.count > 0) {
                        Spacer(Modifier.width(2.dp))
                        Text(
                            text = formatCount(item.count),
                            style = LiveType.NumberMono.copy(
                                color = if (active) LiveColors.Bg.copy(alpha = 0.82f) else LiveColors.FgMute,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberTouchRailItems(
    tree: LiveCategoryTree,
    selectedId: String,
    playlistSections: List<PlaylistCategorySection>,
    expandedPlaylistIds: List<String>,
): List<TouchCategoryRailItem> {
    val base = buildList {
        tree.top.forEach { add(TouchCategoryRailItem(it.id, liveCategoryLabel(it.label), it.count)) }
        if (playlistSections.isEmpty()) {
            tree.global.categories.forEach { add(TouchCategoryRailItem(it.id, liveCategoryLabel(it.label), it.count)) }
            tree.countries.categories.forEach { add(TouchCategoryRailItem(it.id, liveCategoryLabel(it.label), it.count)) }
            tree.adult.categories.forEach { add(TouchCategoryRailItem(it.id, liveCategoryLabel(it.label), it.count)) }
        } else {
            playlistSections.forEach { section ->
                add(
                    TouchCategoryRailItem(
                        id = "playlist-section:${section.id}",
                        label = section.label,
                        count = section.count,
                        playlistSectionId = section.id,
                    )
                )
                if (section.id in expandedPlaylistIds) {
                    section.categories.forEach { category ->
                        add(TouchCategoryRailItem(category.id, liveCategoryLabel(category.label), category.count))
                    }
                }
            }
        }
    }.distinctBy { it.id }.toMutableList()

    val selected = tree.byId(selectedId)
    if (selected != null && base.none { it.id == selectedId }) {
        base.add(0, TouchCategoryRailItem(selected.id, liveCategoryLabel(selected.label), selected.count))
    }

    return base
}

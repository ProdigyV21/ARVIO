package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SettingsInputAntenna
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.exoplayer.ExoPlayer
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary

/**
 * Mobile-first Live TV Group Home landing screen.
 *
 * Displays in order:
 * 1. Current live / Continue Watching mini-player card
 * 2. Quick access 2x2 tile grid:
 *    - All channels
 *    - Sports
 *    - Favourites
 *    - Recently watched
 * 3. Groups heading
 * 4. Full-width group plank tiles
 *
 * No "See all" affordance is rendered; this screen serves as the complete group-navigation surface.
 */
@Composable
fun LiveTvGroupHome(
    exoPlayer: ExoPlayer,
    currentChannel: EnrichedChannel?,
    nowNext: IptvNowNext?,
    clockTickMillis: Long,
    allChannelsCount: Int,
    sportsCount: Int,
    favoritesCount: Int,
    recentsCount: Int,
    favoriteSet: Set<String>,
    onToggleFavorite: (String) -> Unit,
    onOpenFullscreen: () -> Unit,
    groups: List<LiveCategory>,
    onOpenAllChannels: () -> Unit,
    onOpenSports: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenRecents: () -> Unit,
    onSelectGroup: (LiveCategory) -> Unit,
    onOpenSearch: () -> Unit = {},
    providers: List<TvProviderFilter> = emptyList(),
    selectedProviderId: String = "all",
    onSelectProvider: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    compactLayout: Boolean = true,
    landscapeCompact: Boolean = false,
    playerActive: Boolean = true,
    variantCount: Int = 1,
    onOpenVariants: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LiveColors.Bg),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.topbar_tv),
                style = ArflixTypography.heroTitle.copy(fontSize = 28.sp),
                color = TextPrimary,
                modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search),
                tint = TextPrimary,
                modifier = Modifier
                    .size(28.dp)
                    .clickable(onClick = onOpenSearch),
            )
        }
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(bottom = 32.dp),
        ) {
        // 1. Current live / Continue Watching mini-player card
        item(key = "home_mini_player") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(LiveDims.CardRadius))
                    .background(LiveColors.Panel)
                    .border(BorderStroke(1.dp, LiveColors.Divider), RoundedCornerShape(LiveDims.CardRadius)),
            ) {
                MiniPlayerRow(
                    exoPlayer = exoPlayer,
                    channel = currentChannel,
                    clockTickMillis = clockTickMillis,
                    nowNext = nowNext,
                    favoriteSet = favoriteSet,
                    onFavoriteToggle = onToggleFavorite,
                    onFullscreenClick = onOpenFullscreen,
                    compact = compactLayout,
                    landscapeCompact = landscapeCompact,
                    playerActive = playerActive,
                    variantCount = variantCount,
                    onOpenVariants = onOpenVariants,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        // 2. Quick access 2x2 tile grid
        item(key = "home_quick_access") {
            QuickAccessGrid(
                allChannelsCount = allChannelsCount,
                sportsCount = sportsCount,
                favoritesCount = favoritesCount,
                recentsCount = recentsCount,
                onOpenAllChannels = onOpenAllChannels,
                onOpenSports = onOpenSports,
                onOpenFavorites = onOpenFavorites,
                onOpenRecents = onOpenRecents,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            )
        }

        if (providers.size > 1) {
            item(key = "home_playlist_tile") {
                PlaylistSubwayTile(
                    providers = providers,
                    selectedProviderId = selectedProviderId,
                    onSelectProvider = onSelectProvider,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        // 3. Groups heading
        item(key = "home_groups_heading") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 18.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.live_groups_title),
                    style = LiveType.CatLabel.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = LiveColors.Fg,
                    ),
                )
            }
        }

        // 4. Full-width group plank tiles (no "See all" affordance)
        items(
            items = groups,
            key = { group -> "group_plank_${group.id}" },
        ) { group ->
            GroupPlankTile(
                category = group,
                onClick = { onSelectGroup(group) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
            )
        }
    }
    }
}

@Composable
private fun QuickAccessGrid(
    allChannelsCount: Int,
    sportsCount: Int,
    favoritesCount: Int,
    recentsCount: Int,
    onOpenAllChannels: () -> Unit,
    onOpenSports: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenRecents: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            QuickAccessTile(
                title = stringResource(R.string.live_quick_all_channels),
                subtitle = stringResource(R.string.live_channels_count, allChannelsCount),
                icon = Icons.Default.GridView,
                iconTint = liveAccent(),
                onClick = onOpenAllChannels,
                modifier = Modifier.weight(1f),
            )
            QuickAccessTile(
                title = stringResource(R.string.live_quick_sports),
                subtitle = if (sportsCount > 0) stringResource(R.string.live_channels_count, sportsCount) else stringResource(R.string.live_sports_live_and_upcoming),
                icon = Icons.Default.SportsSoccer,
                iconTint = Color(0xFF4ADE80),
                onClick = onOpenSports,
                modifier = Modifier.weight(1f),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val favEnabled = favoritesCount > 0
            QuickAccessTile(
                title = stringResource(R.string.live_quick_favourites),
                subtitle = stringResource(R.string.live_channels_count, favoritesCount),
                icon = Icons.Default.Star,
                iconTint = Color(0xFFFBBF24),
                onClick = onOpenFavorites,
                enabled = favEnabled,
                modifier = Modifier.weight(1f),
            )
            val recentsEnabled = recentsCount > 0
            QuickAccessTile(
                title = stringResource(R.string.live_quick_recent),
                subtitle = stringResource(R.string.live_channels_count, recentsCount),
                icon = Icons.Default.History,
                iconTint = Color(0xFF60A5FA),
                onClick = onOpenRecents,
                enabled = recentsEnabled,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun QuickAccessTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val tileDescription = if (enabled) "$title, $subtitle" else "$title, $subtitle (disabled)"
    Row(
        modifier = modifier
            .height(72.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (enabled) LiveColors.Panel else LiveColors.Panel.copy(alpha = 0.35f))
            .border(
                BorderStroke(
                    1.dp,
                    if (enabled) LiveColors.Divider else LiveColors.Divider.copy(alpha = 0.2f),
                ),
                RoundedCornerShape(12.dp),
            )
            .clickable(enabled = enabled, onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = tileDescription
            }
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    if (enabled) iconTint.copy(alpha = 0.16f)
                    else LiveColors.FgDim.copy(alpha = 0.08f),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (enabled) iconTint else LiveColors.FgMute.copy(alpha = 0.35f),
                modifier = Modifier.size(22.dp),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                style = LiveType.CatLabel.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (enabled) LiveColors.Fg else LiveColors.FgMute.copy(alpha = 0.5f),
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                style = LiveType.TimeMono.copy(
                    fontSize = 11.sp,
                    color = if (enabled) LiveColors.FgDim else LiveColors.FgMute.copy(alpha = 0.35f),
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun PlaylistSubwayTile(
    providers: List<TvProviderFilter>,
    selectedProviderId: String,
    onSelectProvider: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (providers.size <= 1) return
    var expanded by remember { mutableStateOf(false) }
    val currentProvider = providers.firstOrNull { it.id == selectedProviderId } ?: providers.firstOrNull()
    val allProvidersLabel = stringResource(R.string.live_home_all_providers)
    // Resolved here because semantics {} is not a composable scope.
    val playlistTileDescription =
        stringResource(R.string.live_home_current_playlist, currentProvider?.label ?: allProvidersLabel)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(LiveColors.Panel)
                .border(
                    BorderStroke(
                        1.dp,
                        if (expanded) liveAccent() else LiveColors.Divider,
                    ),
                    RoundedCornerShape(10.dp),
                )
                .clickable { expanded = true }
                .semantics {
                    role = Role.Button
                    contentDescription = playlistTileDescription
                }
                .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(liveAccent().copy(alpha = 0.16f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.SettingsInputAntenna,
                    contentDescription = null,
                    tint = liveAccent(),
                    modifier = Modifier.size(20.dp),
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = currentProvider?.label ?: allProvidersLabel,
                    style = LiveType.CatLabel.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LiveColors.Fg,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = currentProvider?.let { stringResource(R.string.live_channels_count, it.count) }
                        ?: stringResource(R.string.settings_fact_playlists),
                    style = LiveType.TimeMono.copy(
                        fontSize = 11.sp,
                        color = LiveColors.FgDim,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = stringResource(R.string.live_home_select_playlist),
                tint = if (expanded) liveAccent() else LiveColors.FgMute,
                modifier = Modifier.size(20.dp),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(LiveColors.PanelRaised)
                .border(BorderStroke(1.dp, LiveColors.Divider), RoundedCornerShape(8.dp)),
        ) {
            providers.forEach { provider ->
                val isSelected = provider.id == selectedProviderId
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = provider.label,
                                style = LiveType.CatLabel.copy(
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) liveAccent() else LiveColors.Fg,
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, fill = false),
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = provider.count.toString(),
                                style = LiveType.TimeMono.copy(
                                    fontSize = 12.sp,
                                    color = if (isSelected) liveAccent().copy(alpha = 0.8f) else LiveColors.FgMute,
                                ),
                            )
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.SettingsInputAntenna,
                            contentDescription = null,
                            tint = if (isSelected) liveAccent() else LiveColors.FgMute,
                            modifier = Modifier.size(18.dp),
                        )
                    },
                    trailingIcon = if (isSelected) {
                        {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = stringResource(R.string.live_home_playlist_selected),
                                tint = liveAccent(),
                                modifier = Modifier.size(18.dp),
                            )
                        }
                    } else null,
                    onClick = {
                        onSelectProvider(provider.id)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) liveAccent().copy(alpha = 0.12f) else Color.Transparent),
                )
            }
        }
    }
}

@Composable
private fun GroupPlankTile(
    category: LiveCategory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val displayLabel = liveCategoryLabel(category.playlistGroupName ?: category.label)
    val channelsSubtitle = stringResource(R.string.live_channels_count, category.count)
    val plankDescription = "$displayLabel, $channelsSubtitle"

    Row(
        modifier = modifier
            .height(58.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LiveColors.Panel)
            .border(BorderStroke(0.5.dp, LiveColors.Divider), RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = plankDescription
            }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Icon or Flag
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(LiveColors.PanelRaised),
            contentAlignment = Alignment.Center,
        ) {
            if (!category.flagEmoji.isNullOrBlank()) {
                Text(
                    text = category.flagEmoji,
                    fontSize = 18.sp,
                )
            } else {
                Icon(
                    imageVector = groupPlankIcon(category),
                    contentDescription = null,
                    tint = liveAccent(),
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        // Group name & count
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = displayLabel,
                style = LiveType.CatLabel.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LiveColors.Fg,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = channelsSubtitle,
                style = LiveType.TimeMono.copy(
                    fontSize = 11.sp,
                    color = LiveColors.FgDim,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        // Chevron Right
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = LiveColors.FgMute,
            modifier = Modifier.size(20.dp),
        )
    }
}

private fun groupPlankIcon(category: LiveCategory): ImageVector = when (category.iconToken) {
    CategoryIcon.Favorite -> Icons.Filled.Star
    CategoryIcon.Recent -> Icons.Filled.History
    CategoryIcon.All -> Icons.Filled.Apps
    CategoryIcon.Grid -> Icons.Filled.GridView
    CategoryIcon.Sport -> Icons.Filled.SportsSoccer
    CategoryIcon.Movie -> Icons.Filled.Movie
    CategoryIcon.News -> Icons.Filled.Newspaper
    CategoryIcon.Kids -> Icons.Filled.ChildCare
    CategoryIcon.Docs -> Icons.AutoMirrored.Filled.LibraryBooks
    CategoryIcon.Music -> Icons.Filled.LibraryMusic
    CategoryIcon.Lock -> Icons.Filled.Lock
    CategoryIcon.Country -> Icons.Filled.Public
    CategoryIcon.SubEntry -> Icons.Filled.GridView
}

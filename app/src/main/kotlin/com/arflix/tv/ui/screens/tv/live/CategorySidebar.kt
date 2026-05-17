package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Left-hand category sidebar. Spec §3.1.
 * Width = 260dp (expanded). Rows 44dp tall with a left active indicator,
 * section headers use mono 10sp tracking +16%.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CategorySidebar(
    tree: LiveCategoryTree,
    selectedId: String,
    expanded: Boolean,
    onSelect: (String) -> Unit,
    onOpenSearch: () -> Unit,
    onHideCategory: (String) -> Unit = {},
    onUnhideCategory: (String) -> Unit = {},
    onMoveCategoryUp: (String) -> Unit = {},
    onMoveCategoryToTop: (String) -> Unit = {},
    onMoveCategoryDown: (String) -> Unit = {},
    onFocusEnter: () -> Unit = {},
    onMoveRight: () -> Unit = {},
    onTopBoundaryFocusChanged: (Boolean) -> Unit = {},
    focusSearchSignal: Int = 0,
    modifier: Modifier = Modifier,
) {
    val targetWidth = if (expanded) LiveDims.SidebarExpanded else LiveDims.SidebarCollapsed
    val animatedWidth by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(durationMillis = 240),
        label = "sidebar-width",
    )
    var expandedCountry by rememberSaveable { mutableStateOf<String?>(null) }
    var expandedAll by rememberSaveable { mutableStateOf(false) }
    var menuForGroup by rememberSaveable { mutableStateOf<String?>(null) }
    val searchFocusRequester = remember { FocusRequester() }

    LaunchedEffect(focusSearchSignal) {
        if (focusSearchSignal > 0) {
            repeat(3) {
                runCatching { searchFocusRequester.requestFocus() }
                delay(50L)
            }
        }
    }

    LaunchedEffect(selectedId, tree) {
        val countryId = selectedCountryGroupId(selectedId, tree)
        if (countryId != null) {
            expandedCountry = countryId
        }
        val allCategory = tree.top.firstOrNull { it.id == "all" }
        if (allCategory?.children?.any { child -> child.containsId(selectedId) } == true) {
            expandedAll = true
        }
    }

    Column(
        modifier = modifier
            .width(animatedWidth)
            .fillMaxHeight()
            .background(LiveColors.PanelDeep)
            .onFocusChanged { if (it.hasFocus) onFocusEnter() }
            // Trap DPAD_LEFT at the sidebar edge so the key doesn't bubble
            // up to the Activity and back out to the Android launcher.
            .onPreviewKeyEvent { ev ->
                if (ev.type != KeyEventType.KeyDown) {
                    false
                } else when (ev.key) {
                    Key.DirectionLeft -> true
                    Key.DirectionRight -> {
                        onMoveRight()
                        true
                    }
                    else -> false
                }
            }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        SearchEntry(
            onClick = onOpenSearch,
            expanded = expanded,
            onFocusChanged = onTopBoundaryFocusChanged,
            focusRequester = searchFocusRequester,
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            items(tree.top, key = { it.id }) { cat ->
                val isAllGroup = cat.id == "all" && cat.children.isNotEmpty()
                val isOpen = isAllGroup && expandedAll
                SidebarRow(
                    label = cat.label,
                    count = cat.count,
                    icon = iconFor(cat),
                    active = selectedId == cat.id,
                    expanded = expanded,
                    hasChildren = isAllGroup,
                    isOpenGroup = isOpen,
                    onFocused = { onTopBoundaryFocusChanged(false) },
                    onClick = {
                        if (isAllGroup) {
                            expandedAll = !expandedAll
                        }
                        onSelect(cat.id)
                    },
                )
                if (isOpen && expanded) {
                    cat.children.forEach { child ->
                        SidebarRow(
                            label = child.label,
                            count = child.count,
                            icon = iconFor(child),
                            flagEmoji = child.flagEmoji,
                            active = selectedId == child.id,
                            expanded = true,
                            indent = 28.dp,
                            labelSize = 10.5.sp,
                            hasChildren = child.children.isNotEmpty(),
                            isOpenGroup = child.containsId(selectedId),
                            onFocused = { onTopBoundaryFocusChanged(false) },
                            onClick = { onSelect(child.id) },
                        )
                        if (child.containsId(selectedId)) {
                            child.children.forEach { grandchild ->
                                SidebarRow(
                                    label = grandchild.label,
                                    count = grandchild.count,
                                    icon = iconFor(grandchild),
                                    active = selectedId == grandchild.id,
                                    expanded = true,
                                    indent = 48.dp,
                                    labelSize = 9.5.sp,
                                    onFocused = { onTopBoundaryFocusChanged(false) },
                                    onClick = { onSelect(grandchild.id) },
                                )
                            }
                        }
                    }
                }
            }
            if (tree.global.categories.isNotEmpty()) {
                item { SectionHeader(tree.global.label, expanded) }
                items(tree.global.categories, key = { it.id }) { cat ->
                    SidebarRow(
                        label = cat.label,
                        count = cat.count,
                        icon = iconFor(cat),
                        active = selectedId == cat.id,
                        expanded = expanded,
                        showMenu = menuForGroup == cat.playlistGroupName,
                        canHide = cat.playlistGroupName != null,
                        canMove = cat.playlistGroupName != null,
                        onFocused = { onTopBoundaryFocusChanged(false) },
                        onLongClick = {
                            menuForGroup = cat.playlistGroupName
                        },
                        onDismissMenu = { menuForGroup = null },
                        onHide = {
                            val groupName = cat.playlistGroupName ?: return@SidebarRow
                            menuForGroup = null
                            onHideCategory(groupName)
                        },
                        onMoveUp = {
                            val groupName = cat.playlistGroupName ?: return@SidebarRow
                            menuForGroup = null
                            onMoveCategoryUp(groupName)
                        },
                        onMoveToTop = {
                            val groupName = cat.playlistGroupName ?: return@SidebarRow
                            menuForGroup = null
                            onMoveCategoryToTop(groupName)
                        },
                        onMoveDown = {
                            val groupName = cat.playlistGroupName ?: return@SidebarRow
                            menuForGroup = null
                            onMoveCategoryDown(groupName)
                        },
                        onClick = { onSelect(cat.id) },
                    )
                }
            }
            if (tree.countries.categories.isNotEmpty()) {
                item { SectionHeader(tree.countries.label, expanded) }
                items(tree.countries.categories, key = { it.id }) { country ->
                    val isExpanded = expandedCountry == country.id
                    SidebarRow(
                        label = country.label,
                        count = country.count,
                        icon = null,
                        leadingCode = country.id,
                        active = selectedId == country.id,
                        expanded = expanded,
                        hasChildren = country.children.isNotEmpty(),
                        isOpenGroup = isExpanded,
                        onFocused = { onTopBoundaryFocusChanged(false) },
                        onClick = {
                            // Tap always toggles expansion. Opening also selects so
                            // the grid reflects the just-opened group; collapsing
                            // leaves selection alone so the user can close a group
                            // without losing their filter.
                            if (isExpanded) {
                                expandedCountry = null
                            } else {
                                expandedCountry = country.id
                                onSelect(country.id)
                            }
                        },
                    )
                    if (isExpanded && expanded) {
                        country.children.forEach { child ->
                            SidebarRow(
                                label = child.label,
                                count = child.count,
                                icon = null,
                                active = selectedId == child.id,
                                expanded = true,
                                indent = 40.dp,
                                labelSize = 10.5.sp,
                                onFocused = { onTopBoundaryFocusChanged(false) },
                                onClick = { onSelect(child.id) },
                            )
                        }
                    }
                }
            }
            if (tree.adult.categories.isNotEmpty()) {
                item { SectionHeader(tree.adult.label, expanded) }
                items(tree.adult.categories, key = { it.id }) { cat ->
                    SidebarRow(
                        label = cat.label,
                        count = cat.count,
                        icon = Icons.Filled.Lock,
                        active = selectedId == cat.id,
                        expanded = expanded,
                        onFocused = { onTopBoundaryFocusChanged(false) },
                        onClick = { onSelect(cat.id) },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SearchEntry(
    onClick: () -> Unit,
    expanded: Boolean,
    onFocusChanged: (Boolean) -> Unit = {},
    focusRequester: FocusRequester? = null,
) {
    var focused by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .onFocusChanged {
                focused = it.isFocused
                onFocusChanged(it.isFocused)
            }
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .border(
                width = if (focused) 3.dp else 0.dp,
                color = if (focused) LiveColors.FocusRing else Color.Transparent,
                shape = RoundedCornerShape(10.dp),
            )
            .clip(RoundedCornerShape(10.dp))
            .background(if (focused) LiveColors.FocusBg else LiveColors.Panel)
            .focusable()
            .onKeyEvent { ev ->
                if (ev.type == KeyEventType.KeyDown &&
                    (ev.key == Key.DirectionCenter || ev.key == Key.Enter)) {
                    onClick(); true
                } else false
            }
            .pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) }
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = LiveColors.FgDim,
            modifier = Modifier.size(14.dp),
        )
        if (expanded) {
            Text(
                text = "Search",
                style = LiveType.CatLabel.copy(color = LiveColors.FgDim),
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "/",
                style = LiveType.NumberMono.copy(color = LiveColors.FgMute),
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SectionHeader(label: String, expanded: Boolean) {
    if (!expanded) {
        Spacer(Modifier.height(8.dp))
        return
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
    ) {
        Text(
            text = label,
            style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SidebarRow(
    label: String,
    count: Int,
    icon: ImageVector?,
    active: Boolean,
    expanded: Boolean,
    onClick: () -> Unit,
    onFocused: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    showMenu: Boolean = false,
    canHide: Boolean = false,
    canUnhide: Boolean = false,
    canMove: Boolean = false,
    onDismissMenu: () -> Unit = {},
    onHide: () -> Unit = {},
    onUnhide: () -> Unit = {},
    onMoveUp: () -> Unit = {},
    onMoveToTop: () -> Unit = {},
    onMoveDown: () -> Unit = {},
    flagEmoji: String? = null,
    leadingCode: String? = null,
    hasChildren: Boolean = false,
    isOpenGroup: Boolean = false,
    indent: androidx.compose.ui.unit.Dp = 0.dp,
    labelSize: androidx.compose.ui.unit.TextUnit = 11.sp,
    focusRequester: FocusRequester? = null,
) {
    var focused by remember { mutableStateOf(false) }
    var consumedLongPress by remember { mutableStateOf(false) }
    var selectPressed by remember { mutableStateOf(false) }
    var longPressJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val bg = when {
        active && focused -> LiveColors.FocusBg
        active -> LiveColors.FocusBg
        focused -> LiveColors.Panel
        else -> Color.Transparent
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LiveDims.SidebarRowHeight)
            .padding(start = indent),
    ) {
        if (active) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(LiveDims.ActiveIndicator)
                    .background(LiveColors.Accent),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = if (active) 12.dp else 10.dp, end = 12.dp)
                .onFocusChanged {
                    focused = it.isFocused
                    if (it.isFocused) onFocused?.invoke()
                }
                .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
                .border(
                    width = if (focused) 3.dp else 0.dp,
                    color = if (focused) LiveColors.FocusRing else Color.Transparent,
                    shape = RoundedCornerShape(8.dp),
                )
                .clip(RoundedCornerShape(8.dp))
                .background(if (focused) LiveColors.PanelRaised else bg)
                .focusable()
                .onKeyEvent { ev ->
                    val isSelect = ev.key == Key.DirectionCenter || ev.key == Key.Enter
                    when {
                        !isSelect -> false
                        ev.type == KeyEventType.KeyDown -> {
                            if (!selectPressed) {
                                selectPressed = true
                                consumedLongPress = false
                                longPressJob?.cancel()
                                if (onLongClick != null) {
                                    longPressJob = scope.launch {
                                        delay(520L)
                                        if (selectPressed) {
                                            consumedLongPress = true
                                            onLongClick()
                                        }
                                    }
                                }
                            }
                            true
                        }
                        ev.type == KeyEventType.KeyUp && consumedLongPress -> {
                            longPressJob?.cancel()
                            selectPressed = false
                            consumedLongPress = false
                            true
                        }
                        ev.type == KeyEventType.KeyUp -> {
                            longPressJob?.cancel()
                            selectPressed = false
                            onClick()
                            true
                        }
                        else -> false
                    }
                }
                .pointerInput(onLongClick) {
                    detectTapGestures(
                        onTap = { onClick() },
                        onLongPress = { onLongClick?.invoke() },
                    )
                }
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            when {
                leadingCode != null -> Text(
                    text = leadingCode,
                    style = LiveType.NumberMono.copy(
                        color = if (active) LiveColors.Accent else LiveColors.FgMute,
                    ),
                    modifier = Modifier.width(20.dp),
                )
                flagEmoji != null -> Text(
                    text = flagEmoji,
                    style = LiveType.CatLabel.copy(fontSize = 14.sp),
                )
                icon != null -> Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (active) LiveColors.Accent else LiveColors.FgDim,
                    modifier = Modifier.size(14.dp),
                )
                else -> Spacer(Modifier.size(14.dp))
            }
            if (expanded) {
                Text(
                    text = label,
                    style = LiveType.CatLabel.copy(
                        color = if (active) LiveColors.Fg else LiveColors.FgDim,
                        fontSize = labelSize,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                if (count > 0) {
                    Text(
                        text = formatCount(count),
                        style = LiveType.NumberMono.copy(color = LiveColors.FgMute, fontSize = 7.sp),
                    )
                }
                if (hasChildren) {
                    Icon(
                        imageVector = if (isOpenGroup)
                            Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = LiveColors.FgMute,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
        }
        if (showMenu && (canHide || canUnhide || canMove)) {
            CategoryContextMenu(
                onDismiss = onDismissMenu,
                canHide = canHide,
                canUnhide = canUnhide,
                canMove = canMove,
                onHide = onHide,
                onUnhide = onUnhide,
                onMoveUp = onMoveUp,
                onMoveToTop = onMoveToTop,
                onMoveDown = onMoveDown,
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CategoryContextMenu(
    onDismiss: () -> Unit,
    canHide: Boolean,
    canUnhide: Boolean,
    canMove: Boolean,
    onHide: () -> Unit,
    onUnhide: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveToTop: () -> Unit,
    onMoveDown: () -> Unit,
) {
    val actions = buildList {
        if (canMove) {
            add(CategoryMenuAction("Move to top", Icons.Filled.KeyboardArrowUp, onMoveToTop))
            add(CategoryMenuAction("Move up", Icons.Filled.KeyboardArrowUp, onMoveUp))
            add(CategoryMenuAction("Move down", Icons.Filled.KeyboardArrowDown, onMoveDown))
        }
        if (canHide) {
            add(CategoryMenuAction("Hide category", Icons.Filled.VisibilityOff, onHide))
        }
        if (canUnhide) {
            add(CategoryMenuAction("Unhide category", Icons.Filled.Visibility, onUnhide))
        }
    }
    if (actions.isEmpty()) return

    var focusedIndex by remember(actions.size) { mutableStateOf(0) }
    var ignoreSelectUntilRelease by remember(actions.size) { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Popup(
        alignment = Alignment.CenterEnd,
        onDismissRequest = onDismiss,
        properties = PopupProperties(focusable = true),
    ) {
        Column(
            modifier = Modifier
                .width(184.dp)
                .background(LiveColors.PanelRaised, RoundedCornerShape(10.dp))
                .border(1.dp, LiveColors.FocusRing.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
                .focusRequester(focusRequester)
                .focusable()
                .onPreviewKeyEvent { event ->
                    val isSelect = event.key == Key.DirectionCenter || event.key == Key.Enter
                    if (ignoreSelectUntilRelease && isSelect) {
                        if (event.type == KeyEventType.KeyUp) {
                            ignoreSelectUntilRelease = false
                        }
                        true
                    } else if (event.type != KeyEventType.KeyDown) {
                        false
                    } else {
                        when (event.key) {
                            Key.DirectionUp -> {
                                focusedIndex = (focusedIndex - 1).coerceAtLeast(0)
                                true
                            }
                            Key.DirectionDown -> {
                                focusedIndex = (focusedIndex + 1).coerceAtMost(actions.lastIndex)
                                true
                            }
                            Key.DirectionCenter, Key.Enter -> {
                                actions[focusedIndex].onClick()
                                true
                            }
                            Key.DirectionLeft, Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            else -> false
                        }
                    }
                }
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            actions.forEachIndexed { index, action ->
                CategoryMenuItem(
                    action = action,
                    focused = index == focusedIndex,
                    onClick = action.onClick,
                )
            }
        }
    }
}

private data class CategoryMenuAction(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CategoryMenuItem(
    action: CategoryMenuAction,
    focused: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (focused) LiveColors.FocusRing else Color.Transparent)
            .pointerInput(onClick) { detectTapGestures(onTap = { onClick() }) }
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = null,
            tint = if (focused) Color.Black else LiveColors.FgDim,
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = action.label,
            style = LiveType.CatLabel.copy(
                color = if (focused) Color.Black else LiveColors.Fg,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private fun selectedCountryGroupId(
    selectedId: String,
    tree: LiveCategoryTree,
): String? = tree.countries.categories.firstOrNull { country ->
    country.id == selectedId || country.children.any { child -> child.id == selectedId }
}?.id

private fun LiveCategory.containsId(id: String): Boolean {
    if (this.id == id) return true
    return children.any { child -> child.containsId(id) }
}

private fun iconFor(cat: LiveCategory): ImageVector? = when (cat.iconToken) {
    CategoryIcon.Favorite -> Icons.Filled.Star
    CategoryIcon.Recent -> Icons.Filled.History
    CategoryIcon.All -> Icons.Filled.Apps
    CategoryIcon.Grid -> Icons.Filled.GridView
    CategoryIcon.Sport -> Icons.Filled.SportsSoccer
    CategoryIcon.Movie -> Icons.Filled.Movie
    CategoryIcon.News -> Icons.Filled.Newspaper
    CategoryIcon.Kids -> Icons.Filled.ChildCare
    CategoryIcon.Docs -> Icons.Filled.LibraryBooks
    CategoryIcon.Music -> Icons.Filled.LibraryMusic
    CategoryIcon.Lock -> Icons.Filled.Lock
    CategoryIcon.Country -> Icons.Filled.Public
    CategoryIcon.SubEntry -> null
}

/** Compact human count: `4821` → `4.8k`. */
fun formatCount(n: Int): String {
    if (n < 1000) return n.toString()
    val k = n / 1000.0
    return if (k < 10) String.format("%.1fk", k) else "${k.toInt()}k"
}

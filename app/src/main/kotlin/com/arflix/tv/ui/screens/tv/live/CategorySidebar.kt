package com.arflix.tv.ui.screens.tv.live

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.focusGroup
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
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
    playlistSections: List<PlaylistCategorySection> = emptyList(),
    expanded: Boolean,
    listState: LazyListState,
    focusRequester: FocusRequester? = null,
    onSelect: (String) -> Unit,
    onOpenSearch: () -> Unit,
    onHideCategory: (String?, String) -> Unit = { _, _ -> },
    onUnhideCategory: (String?, String) -> Unit = { _, _ -> },
    onMoveCategoryUp: (String?, String) -> Unit = { _, _ -> },
    onMoveCategoryToTop: (String?, String) -> Unit = { _, _ -> },
    onMoveCategoryDown: (String?, String) -> Unit = { _, _ -> },
    onFocusEnter: () -> Unit = {},
    onMoveRight: () -> Unit = {},
    onMoveUpFromSearch: () -> Unit = {},
    onTopBoundaryFocusChanged: (Boolean) -> Unit = {},
    focusSearchSignal: Int = 0,
    focusCategorySignal: Int = 0,
    isTouchDevice: Boolean = false,
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
    var expandedPlaylistIds by rememberSaveable { mutableStateOf(emptyList<String>()) }
    var activeMenu by remember { mutableStateOf<CategoryMenuState?>(null) }
    val searchFocusRequester = remember { FocusRequester() }
    val selectedCategoryFocusRequester = remember { FocusRequester() }
    val firstCategoryFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    fun openCategoryMenu(category: LiveCategory, hidden: Boolean) {
        val groupName = category.playlistGroupName ?: return
        activeMenu = CategoryMenuState(
            id = if (hidden) "hidden:${category.id}" else category.id,
            playlistId = category.playlistId,
            groupName = groupName,
            canMove = !hidden,
            canHide = !hidden,
            canUnhide = hidden,
        )
    }

    val currentMenu = activeMenu
    val activeMenuActions = currentMenu?.let { menu ->
        buildCategoryMenuActions(
            canMove = menu.canMove,
            canHide = menu.canHide,
            canUnhide = menu.canUnhide,
            onHide = {
                activeMenu = null
                onHideCategory(menu.playlistId, menu.groupName)
            },
            onUnhide = {
                activeMenu = null
                onUnhideCategory(menu.playlistId, menu.groupName)
            },
            onMoveUp = {
                activeMenu = null
                onMoveCategoryUp(menu.playlistId, menu.groupName)
            },
            onMoveToTop = {
                activeMenu = null
                onMoveCategoryToTop(menu.playlistId, menu.groupName)
            },
            onMoveDown = {
                activeMenu = null
                onMoveCategoryDown(menu.playlistId, menu.groupName)
            },
        )
    }.orEmpty()

    fun runActiveMenuAction(index: Int) {
        activeMenuActions.getOrNull(index.coerceIn(0, (activeMenuActions.size - 1).coerceAtLeast(0)))
            ?.onClick
            ?.invoke()
    }

    BackHandler(enabled = activeMenu != null) {
        activeMenu = null
    }

    val categoriesLoaded = LiveTvStartup.searchIsReachable(tree.top.size)

    // Compose gives the initial D-pad focus to the first focusable row, which
    // is search — so every time Live TV opened the selector sat in the search
    // box, and while the playlist was still loading "down" had no category to
    // move to, leaving it stuck there. Claim the category row as soon as one
    // exists. Guarded so it only runs for a fresh entry, never fighting a user
    // who deliberately moved to search afterwards.
    var searchHasFocus by remember { mutableStateOf(false) }
    // True once the user has deliberately gone to search (pressed up into it,
    // or asked for it). Until then, search holding focus can only be Compose's
    // default placement or the mini player's surface bouncing focus back, and
    // both must be corrected.
    var userChoseSearch by remember { mutableStateOf(false) }
    var categoryHasHadFocus by remember { mutableStateOf(false) }

    fun onCategoryFocused() {
        categoryHasHadFocus = true
        onTopBoundaryFocusChanged(false)
    }

    LaunchedEffect(categoriesLoaded, focusCategorySignal, searchHasFocus, userChoseSearch, isTouchDevice) {
        if (isTouchDevice || !categoriesLoaded || userChoseSearch) return@LaunchedEffect
        if (LiveTvStartup.shouldFocusSearch(focusSearchSignal)) return@LaunchedEffect
        // A single claim is not enough: the mini player attaches its video
        // surface a beat after the screen opens, that takes the platform focus,
        // and Compose then falls back to the first focusable row — search. The
        // row also lives in a LazyColumn, so its requester may not be attached
        // on the first try. Retry briefly; re-runs whenever search takes focus
        // again, so a late player start cannot strand the selector there.
        repeat(LiveTvStartup.INITIAL_FOCUS_ATTEMPTS) {
            val took = runCatching { selectedCategoryFocusRequester.requestFocus() }.isSuccess ||
                runCatching { firstCategoryFocusRequester.requestFocus() }.isSuccess
            if (took) return@LaunchedEffect
            delay(LiveTvStartup.INITIAL_FOCUS_RETRY_MS)
        }
    }

    LaunchedEffect(focusSearchSignal) {
        if (LiveTvStartup.shouldFocusSearch(focusSearchSignal)) {
            userChoseSearch = true
            repeat(3) {
                runCatching { searchFocusRequester.requestFocus() }
                delay(50L)
            }
        }
    }

    LaunchedEffect(selectedId, tree, playlistSections) {
        val countryId = selectedCountryGroupId(selectedId, tree)
        if (countryId != null) {
            expandedCountry = countryId
        }
        val allCategory = tree.top.firstOrNull { it.id == "all" }
        if (allCategory?.children?.any { child -> child.containsId(selectedId) } == true) {
            expandedAll = true
        }
        playlistSections.firstOrNull { section ->
            section.categories.any { it.containsId(selectedId) }
        }?.id?.let { sectionId ->
            if (sectionId !in expandedPlaylistIds) {
                expandedPlaylistIds = expandedPlaylistIds + sectionId
            }
        }
    }

    Column(
        modifier = modifier
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .width(animatedWidth)
            .fillMaxHeight()
            .background(LiveColors.PanelDeep)
            // Entering (or re-entering) the sidebar must land on the category
            // list. Search is the first focusable child, so a plain focusGroup
            // hands it the selector on entry and again every time the lazy list
            // recomposes underneath the focused row — which is what pinned the
            // selector in the search box while the playlist loaded.
            .arvioDpadFocusGroup(
                restoreFocusRequester = if (categoriesLoaded) selectedCategoryFocusRequester else null,
            )
            .onFocusChanged { focusState ->
                if (focusState.hasFocus) {
                    onFocusEnter()
                }
            }
            .onPreviewKeyEvent { ev ->
                val menu = activeMenu
                if (menu != null && activeMenuActions.isNotEmpty()) {
                    val isSelect = ev.key == Key.DirectionCenter || ev.key == Key.Enter || ev.key == Key.Menu
                    if (ev.type != KeyEventType.KeyDown) {
                        return@onPreviewKeyEvent isSelect
                    }
                    return@onPreviewKeyEvent when (ev.key) {
                        Key.DirectionUp -> {
                            activeMenu = menu.copy(focusedIndex = (menu.focusedIndex - 1).coerceAtLeast(0))
                            true
                        }
                        Key.DirectionDown -> {
                            activeMenu = menu.copy(focusedIndex = (menu.focusedIndex + 1).coerceAtMost(activeMenuActions.lastIndex))
                            true
                        }
                        Key.DirectionCenter, Key.Enter, Key.Menu -> {
                            runActiveMenuAction(menu.focusedIndex)
                            true
                        }
                        Key.DirectionLeft, Key.Back, Key.Escape -> {
                            activeMenu = null
                            true
                        }
                        else -> true
                    }
                }
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
            onMoveUp = onMoveUpFromSearch,
            onMoveDown = {
                tree.top.firstOrNull()?.let { first ->
                    onSelect(first.id)
                }
            },
            onFocusChanged = { atTop ->
                // Search taking focus *after* a category already had it means
                // the user walked up into it — leave the selector alone from
                // then on. Search taking it before that is Compose's default
                // placement (or the player bouncing focus back), which the
                // effect above corrects.
                if (atTop && categoryHasHadFocus) userChoseSearch = true
                searchHasFocus = atTop
                onTopBoundaryFocusChanged(atTop)
            },
            focusRequester = searchFocusRequester,
            focusable = categoriesLoaded,
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            itemsIndexed(tree.top, key = { index, cat -> "top:${cat.id}:$index" }) { index, cat ->
                val isAllGroup = cat.id == "all" && cat.children.isNotEmpty()
                val isOpen = isAllGroup && expandedAll
                SidebarRow(
                    label = liveCategoryLabel(cat.label),
                    count = cat.count,
                    icon = iconFor(cat),
                    active = selectedId == cat.id,
                    expanded = expanded,
                    hasChildren = isAllGroup,
                    isOpenGroup = isOpen,
                    // The selected category can be nested (or scrolled out of
                    // the lazy list), in which case its requester is unattached
                    // and cannot take focus. The first row always can, so it
                    // acts as the guaranteed landing spot on entry.
                    focusRequester = when {
                        selectedId == cat.id -> selectedCategoryFocusRequester
                        index == 0 -> firstCategoryFocusRequester
                        else -> null
                    },
                    onFocused = { onCategoryFocused() },
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
                            label = liveCategoryLabel(child.label),
                            count = child.count,
                            icon = iconFor(child),
                            flagEmoji = child.flagEmoji,
                            active = selectedId == child.id,
                            expanded = true,
                            indent = 28.dp,
                            labelSize = 10.5.sp,
                            hasChildren = child.children.isNotEmpty(),
                            isOpenGroup = child.containsId(selectedId),
                            focusRequester = if (selectedId == child.id) selectedCategoryFocusRequester else null,
                            onFocused = { onCategoryFocused() },
                            onClick = { onSelect(child.id) },
                        )
                        if (child.containsId(selectedId)) {
                            child.children.forEach { grandchild ->
                                SidebarRow(
                                    label = liveCategoryLabel(grandchild.label),
                                    count = grandchild.count,
                                    icon = iconFor(grandchild),
                                    active = selectedId == grandchild.id,
                                    expanded = true,
                                    indent = 48.dp,
                                    labelSize = 9.5.sp,
                                    focusRequester = if (selectedId == grandchild.id) selectedCategoryFocusRequester else null,
                                    onFocused = { onCategoryFocused() },
                                    onClick = { onSelect(grandchild.id) },
                                )
                            }
                        }
                    }
                }
            }
            if (playlistSections.isNotEmpty()) {
                playlistSections.forEach { section ->
                    item(key = "playlist-section:${section.id}") {
                        val isOpen = section.id in expandedPlaylistIds
                        SidebarRow(
                            label = section.label,
                            count = section.count,
                            icon = Icons.Filled.LibraryBooks,
                            active = section.categories.any { it.containsId(selectedId) },
                            expanded = expanded,
                            hasChildren = true,
                            isOpenGroup = isOpen,
                            onFocused = { onCategoryFocused() },
                            onClick = {
                                expandedPlaylistIds = if (isOpen) {
                                    expandedPlaylistIds - section.id
                                } else {
                                    expandedPlaylistIds + section.id
                                }
                            },
                        )
                    }
                    if (expanded && section.id in expandedPlaylistIds) {
                        itemsIndexed(
                            section.categories,
                            key = { index, cat -> "playlist:${section.id}:${cat.id}:$index" },
                        ) { _, cat ->
                            SidebarRow(
                                label = liveCategoryLabel(cat.label),
                                count = cat.count,
                                icon = iconFor(cat),
                                active = selectedId == cat.id,
                                expanded = true,
                                indent = 28.dp,
                                focusRequester = if (selectedId == cat.id) selectedCategoryFocusRequester else null,
                                onFocused = { onCategoryFocused() },
                                onLongClick = { openCategoryMenu(cat, hidden = false) },
                                onClick = { onSelect(cat.id) },
                            )
                        }
                    }
                }
            } else if (tree.global.categories.isNotEmpty()) {
                item { SectionHeader(liveSectionLabel(tree.global.label), expanded) }
                itemsIndexed(tree.global.categories, key = { index, cat -> "global:${cat.id}:$index" }) { _, cat ->
                    SidebarRow(
                        label = liveCategoryLabel(cat.label),
                        count = cat.count,
                        icon = iconFor(cat),
                        active = selectedId == cat.id,
                        expanded = expanded,
                        focusRequester = if (selectedId == cat.id) selectedCategoryFocusRequester else null,
                        onFocused = { onCategoryFocused() },
                        onLongClick = {
                            openCategoryMenu(cat, hidden = false)
                        },
                        onClick = { onSelect(cat.id) },
                    )
                }
            }
            if (tree.hidden.categories.isNotEmpty()) {
                item { SectionHeader(liveSectionLabel(tree.hidden.label), expanded) }
                itemsIndexed(tree.hidden.categories, key = { index, cat -> "hidden:${cat.id}:$index" }) { _, cat ->
                    SidebarRow(
                        label = liveCategoryLabel(cat.label),
                        count = cat.count,
                        icon = Icons.Filled.VisibilityOff,
                        active = false,
                        expanded = expanded,
                        focusRequester = if (selectedId == cat.id) selectedCategoryFocusRequester else null,
                        onFocused = { onCategoryFocused() },
                        onLongClick = {
                            openCategoryMenu(cat, hidden = true)
                        },
                        onClick = {
                            val groupName = cat.playlistGroupName ?: return@SidebarRow
                            onUnhideCategory(cat.playlistId, groupName)
                        },
                    )
                }
            }
            if (tree.countries.categories.isNotEmpty()) {
                item { SectionHeader(liveSectionLabel(tree.countries.label), expanded) }
                itemsIndexed(tree.countries.categories, key = { index, country -> "country:${country.id}:$index" }) { _, country ->
                    val isExpanded = expandedCountry == country.id
                    SidebarRow(
                        label = liveCategoryLabel(country.label),
                        count = country.count,
                        icon = null,
                        leadingCode = country.id,
                        active = selectedId == country.id,
                        expanded = expanded,
                        hasChildren = country.children.isNotEmpty(),
                        isOpenGroup = isExpanded,
                        focusRequester = if (selectedId == country.id) selectedCategoryFocusRequester else null,
                        onFocused = { onCategoryFocused() },
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
                                label = liveCategoryLabel(child.label),
                                count = child.count,
                                icon = null,
                                active = selectedId == child.id,
                                expanded = true,
                                indent = 40.dp,
                                labelSize = 10.5.sp,
                                focusRequester = if (selectedId == child.id) selectedCategoryFocusRequester else null,
                                onFocused = { onCategoryFocused() },
                                onClick = { onSelect(child.id) },
                            )
                        }
                    }
                }
            }
            if (tree.adult.categories.isNotEmpty()) {
                item { SectionHeader(liveSectionLabel(tree.adult.label), expanded) }
                itemsIndexed(tree.adult.categories, key = { index, cat -> "adult:${cat.id}:$index" }) { _, cat ->
                    SidebarRow(
                        label = liveCategoryLabel(cat.label),
                        count = cat.count,
                        icon = Icons.Filled.Lock,
                        active = selectedId == cat.id,
                        expanded = expanded,
                        focusRequester = if (selectedId == cat.id) selectedCategoryFocusRequester else null,
                        onFocused = { onCategoryFocused() },
                        onClick = { onSelect(cat.id) },
                    )
                }
            }
        }
        if (currentMenu != null && activeMenuActions.isNotEmpty()) {
            CategoryContextMenu(
                onDismiss = { activeMenu = null },
                actions = activeMenuActions,
                focusedIndex = currentMenu.focusedIndex.coerceIn(0, activeMenuActions.lastIndex),
                onFocusedIndexChange = { index ->
                    activeMenu = currentMenu.copy(focusedIndex = index.coerceIn(0, activeMenuActions.lastIndex))
                },
                onAction = { runActiveMenuAction(it) },
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SearchEntry(
    onClick: () -> Unit,
    expanded: Boolean,
    onMoveUp: () -> Unit = {},
    onMoveDown: () -> Unit = {},
    onFocusChanged: (Boolean) -> Unit = {},
    focusRequester: FocusRequester? = null,
    focusable: Boolean = true,
) {
    val focusManager = LocalFocusManager.current
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
            .onPreviewKeyEvent { ev ->
                if (ev.type != KeyEventType.KeyDown) {
                    false
                } else when (ev.key) {
                    Key.DirectionUp -> {
                        onMoveUp()
                        focusManager.moveFocus(FocusDirection.Up)
                        true
                    }
                    Key.DirectionDown -> {
                        onMoveDown()
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    }
                    Key.DirectionCenter, Key.Enter -> {
                        onClick()
                        true
                    }
                    else -> false
                }
            }
            .border(
                width = if (focused) 3.dp else 0.dp,
                color = if (focused) LiveColors.FocusRing else Color.Transparent,
                shape = RoundedCornerShape(10.dp),
            )
            .clip(RoundedCornerShape(10.dp))
            .background(if (focused) LiveColors.FocusBg else LiveColors.Panel)
            // Search is the first focusable row in the sidebar, so while the
            // categories are still loading Compose parks the D-pad selector
            // here by default — and "down" had nothing to move to yet, so
            // every key press was swallowed and the selector looked frozen.
            // Taking search out of the focus order until there is something to
            // search past sends that initial focus to the category list.
            .focusable(enabled = focusable)
            .onKeyEvent { ev ->
                if (ev.type != KeyEventType.KeyDown) return@onKeyEvent false
                when (ev.key) {
                    Key.DirectionUp -> {
                        onMoveUp()
                        true
                    }
                    Key.DirectionDown -> {
                        onMoveDown()
                        true
                    }
                    Key.DirectionCenter, Key.Enter -> {
                        onClick()
                        true
                    }
                    else -> false
                }
            }
            .pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) }
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search),
            tint = LiveColors.FgDim,
            modifier = Modifier.size(14.dp),
        )
        if (expanded) {
            Text(
                text = stringResource(R.string.search),
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
                    val isMenuKey = ev.key == Key.Menu
                    if (isMenuKey && ev.type == KeyEventType.KeyDown && onLongClick != null) {
                        consumedLongPress = true
                        longPressJob?.cancel()
                        onLongClick()
                        true
                    } else
                    when {
                        !isSelect -> false
                        ev.type == KeyEventType.KeyDown -> {
                            if (ev.nativeKeyEvent.repeatCount > 0 && onLongClick != null) {
                                if (!consumedLongPress) {
                                    consumedLongPress = true
                                    onLongClick()
                                }
                                return@onKeyEvent true
                            }
                            if (!selectPressed) {
                                selectPressed = true
                                consumedLongPress = false
                                longPressJob?.cancel()
                                if (onLongClick != null) {
                                    longPressJob = scope.launch {
                                        delay(480L)
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
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CategoryContextMenu(
    onDismiss: () -> Unit,
    actions: List<CategoryMenuAction>,
    focusedIndex: Int,
    onFocusedIndexChange: (Int) -> Unit,
    onAction: (Int) -> Unit,
) {
    if (actions.isEmpty()) return

    Popup(
        alignment = Alignment.CenterEnd,
        onDismissRequest = onDismiss,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
        Column(
            modifier = Modifier
                .width(184.dp)
                .background(LiveColors.PanelRaised, RoundedCornerShape(10.dp))
                .border(1.dp, LiveColors.FocusRing.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
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

private fun buildCategoryMenuActions(
    canHide: Boolean,
    canUnhide: Boolean,
    canMove: Boolean,
    onHide: () -> Unit,
    onUnhide: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveToTop: () -> Unit,
    onMoveDown: () -> Unit,
): List<CategoryMenuAction> = buildList {
    if (canMove) {
        add(CategoryMenuAction(R.string.live_menu_move_top, Icons.Filled.KeyboardArrowUp, onMoveToTop))
        add(CategoryMenuAction(R.string.live_menu_move_up, Icons.Filled.KeyboardArrowUp, onMoveUp))
        add(CategoryMenuAction(R.string.live_menu_move_down, Icons.Filled.KeyboardArrowDown, onMoveDown))
    }
    if (canHide) {
        add(CategoryMenuAction(R.string.live_menu_hide_category, Icons.Filled.VisibilityOff, onHide))
    }
    if (canUnhide) {
        add(CategoryMenuAction(R.string.live_menu_unhide_category, Icons.Filled.Visibility, onUnhide))
    }
}

private data class CategoryMenuAction(
    val labelRes: Int,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

private data class CategoryMenuState(
    val id: String,
    val playlistId: String?,
    val groupName: String,
    val canMove: Boolean,
    val canHide: Boolean,
    val canUnhide: Boolean,
    val focusedIndex: Int = 0,
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
            .clickable { onClick() }
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
            text = stringResource(action.labelRes),
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

internal fun LiveCategory.containsId(id: String): Boolean {
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

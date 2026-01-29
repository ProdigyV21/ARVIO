package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LinkOff
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Settings screen
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    var isSidebarFocused by remember { mutableStateOf(true) }
    var sidebarFocusIndex by remember { mutableIntStateOf(3) } // SETTINGS
    var sectionIndex by remember { mutableIntStateOf(0) }
    var contentFocusIndex by remember { mutableIntStateOf(0) }
    var activeZone by remember { mutableStateOf(Zone.SIDEBAR) }

    // Sub-focus for addon rows: 0 = toggle, 1 = delete
    var addonActionIndex by remember { mutableIntStateOf(0) }

    // Input modal states
    var showCustomAddonInput by remember { mutableStateOf(false) }
    var showTorBoxInput by remember { mutableStateOf(false) }
    var customAddonUrl by remember { mutableStateOf("") }
    var customAddonName by remember { mutableStateOf("") }
    var torBoxApiKey by remember { mutableStateOf("") }
    var showSubtitlePicker by remember { mutableStateOf(false) }
    var subtitlePickerIndex by remember { mutableIntStateOf(0) }

    val sections = listOf("general", "addons", "accounts")

    val focusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    val openSubtitlePicker = {
        viewModel.refreshSubtitleOptions()
        val options = uiState.subtitleOptions
        subtitlePickerIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            .coerceAtLeast(0)
        showSubtitlePicker = true
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(showSubtitlePicker, uiState.subtitleOptions) {
        if (showSubtitlePicker) {
            val options = uiState.subtitleOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            subtitlePickerIndex = if (targetIndex >= 0) targetIndex else subtitlePickerIndex.coerceIn(0, maxIndex)
        }
    }
    
    // Auto-scroll to focused item (heuristic: ~100dp per item)
    LaunchedEffect(contentFocusIndex, sectionIndex) {
        if (activeZone == Zone.CONTENT) {
            // Reset scroll when changing sections
            if (contentFocusIndex == 0) {
                scrollState.animateScrollTo(0)
            } else {
                // Scroll to keep focused item in view (approximate calculation)
                val targetScroll = (contentFocusIndex * 250) // ~100dp converted to px (roughly)
                scrollState.animateScrollTo(targetScroll)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                // BLOCKER FIX: Ignore main screen navigation if modals are open
                if (showCustomAddonInput || showTorBoxInput || showSubtitlePicker) return@onPreviewKeyEvent false 

                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> onBack()
                                Zone.SECTION -> {
                                    activeZone = Zone.SIDEBAR
                                    isSidebarFocused = true
                                }
                                Zone.CONTENT -> {
                                    activeZone = Zone.SECTION
                                }
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            when (activeZone) {
                                Zone.CONTENT -> {
                                    // In addons section, handle sub-navigation
                                    if (sectionIndex == 1 && contentFocusIndex < uiState.addons.size && addonActionIndex > 0) {
                                        addonActionIndex = 0
                                    } else {
                                        activeZone = Zone.SECTION
                                        addonActionIndex = 0
                                    }
                                }
                                Zone.SECTION -> {
                                    activeZone = Zone.SIDEBAR
                                    isSidebarFocused = true
                                }
                                else -> {}
                            }
                            true
                        }
                        Key.DirectionRight -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    activeZone = Zone.SECTION
                                    isSidebarFocused = false
                                }
                                Zone.SECTION -> {
                                    activeZone = Zone.CONTENT
                                    addonActionIndex = 0
                                }
                                Zone.CONTENT -> {
                                    // In addons section, handle sub-navigation to delete
                                    if (sectionIndex == 1 && contentFocusIndex < uiState.addons.size && addonActionIndex < 1) {
                                        addonActionIndex = 1
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionUp -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> if (sidebarFocusIndex > 0) sidebarFocusIndex--
                                Zone.SECTION -> {
                                    if (sectionIndex > 0) {
                                        sectionIndex--
                                        contentFocusIndex = 0 // Reset content focus when changing section
                                        addonActionIndex = 0
                                    }
                                }
                                Zone.CONTENT -> {
                                    if (contentFocusIndex > 0) {
                                        contentFocusIndex--
                                        addonActionIndex = 0 // Reset to toggle when changing rows
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> if (sidebarFocusIndex < SidebarItem.entries.size - 1) sidebarFocusIndex++
                                Zone.SECTION -> {
                                    if (sectionIndex < sections.size - 1) {
                                        sectionIndex++
                                        contentFocusIndex = 0 // Reset content focus when changing section
                                        addonActionIndex = 0
                                    }
                                }
                                Zone.CONTENT -> {
                                    // Dynamic max based on current section
                                    val maxIndex = when (sectionIndex) {
                                        0 -> 1 // General: 2 items (subtitle, auto-play)
                                        1 -> uiState.addons.size // Addons: N addons + "Add Custom" button
                                        2 -> 1 // Accounts: 2 items (Account + Trakt)
                                        else -> 0
                                    }
                                    if (contentFocusIndex < maxIndex) {
                                        contentFocusIndex++
                                        addonActionIndex = 0 // Reset to toggle when changing rows
                                    }
                                }
                            }
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    // Handle sidebar navigation
                                    when (SidebarItem.entries[sidebarFocusIndex]) {
                                        SidebarItem.SEARCH -> onNavigateToSearch()
                                        SidebarItem.HOME -> onNavigateToHome()
                                        SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                        SidebarItem.SETTINGS -> { /* Already here */ }
                                    }
                                }
                                Zone.SECTION -> activeZone = Zone.CONTENT
                                Zone.CONTENT -> {
                                    when (sectionIndex) {
                                        0 -> { // General
                                            when (contentFocusIndex) {
                                                0 -> openSubtitlePicker()
                                                1 -> viewModel.setAutoPlayNext(!uiState.autoPlayNext)
                                            }
                                        }
                                        1 -> { // Addons
                                            if (contentFocusIndex < uiState.addons.size) {
                                                val addon = uiState.addons[contentFocusIndex]
                                                if (addonActionIndex == 0) {
                                                    // Toggle addon on/off
                                                    viewModel.toggleAddon(addon.id)
                                                } else {
                                                    // Delete addon
                                                    viewModel.removeAddon(addon.id)
                                                    addonActionIndex = 0
                                                    // Adjust focus if we deleted the last item
                                                    if (contentFocusIndex >= uiState.addons.size - 1 && contentFocusIndex > 0) {
                                                        contentFocusIndex--
                                                    }
                                                }
                                            } else {
                                                // "Add Custom Addon" button
                                                showCustomAddonInput = true
                                            }
                                        }
                                        2 -> { // Accounts
                                            when (contentFocusIndex) {
                                                0 -> {
                                                    if (uiState.isLoggedIn) {
                                                        viewModel.logout()
                                                    }
                                                }
                                                1 -> { // Trakt
                                                    if (uiState.isTraktAuthenticated) {
                                                        viewModel.disconnectTrakt()
                                                    } else if (!uiState.isTraktPolling) {
                                                        viewModel.startTraktAuth()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else -> {}
                            }
                            true
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Sidebar
            Sidebar(
                selectedItem = SidebarItem.SETTINGS,
                isSidebarFocused = activeZone == Zone.SIDEBAR,
                focusedIndex = sidebarFocusIndex
            )
            
            // Settings internal sidebar
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .fillMaxSize()
                    .background(BackgroundDark)
                    .padding(vertical = 80.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = "Settings",
                    style = ArflixTypography.heroTitle.copy(fontSize = androidx.compose.ui.unit.TextUnit.Unspecified),
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                
                sections.forEachIndexed { index, section ->
                    SettingsSectionItem(
                        icon = when (section) {
                            "general" -> Icons.Default.Settings
                            "addons" -> Icons.Default.Widgets
                            "accounts" -> Icons.Default.Person
                            else -> Icons.Default.Settings
                        },
                        title = section.replaceFirstChar { it.uppercase() },
                        isSelected = sectionIndex == index,
                        isFocused = activeZone == Zone.SECTION && sectionIndex == index
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = "ARVIO v2.6.4\nBuild: Debrid Native",
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f)
                )
            }
            
            // Content area
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(48.dp)
            ) {
                when (sections[sectionIndex]) {
                    "general" -> GeneralSettings(
                        defaultSubtitle = uiState.defaultSubtitle,
                        autoPlayNext = uiState.autoPlayNext,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        onSubtitleClick = openSubtitlePicker,
                        onAutoPlayToggle = { viewModel.setAutoPlayNext(it) }
                    )
                    "addons" -> AddonsSettings(
                        addons = uiState.addons,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        focusedActionIndex = addonActionIndex,
                        onToggleAddon = { viewModel.toggleAddon(it) },
                        onDeleteAddon = { viewModel.removeAddon(it) },
                        onAddCustomAddon = { /* TODO: Show input modal */ }
                    )
                    "accounts" -> AccountsSettings(
                        isLoggedIn = uiState.isLoggedIn,
                        accountEmail = uiState.accountEmail,
                        isTraktAuthenticated = uiState.isTraktAuthenticated,
                        traktCode = uiState.traktCode?.userCode,
                        traktUrl = uiState.traktCode?.verificationUrl,
                        isTraktPolling = uiState.isTraktPolling,
                        traktExpiration = uiState.traktExpiration,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        onConnectTrakt = { viewModel.startTraktAuth() },
                        onDisconnectTrakt = { viewModel.disconnectTrakt() }
                    )
                }
            }
        }

        // Custom Addon Input Modal
        if (showCustomAddonInput) {
            InputModal(
                title = "Add Custom Addon",
                fields = listOf(
                    InputField("URL", customAddonUrl) { customAddonUrl = it },
                    InputField("Name", customAddonName) { customAddonName = it }
                ),
                onConfirm = {
                    if (customAddonUrl.isNotBlank()) {
                        viewModel.addCustomAddon(customAddonUrl, customAddonName)
                        customAddonUrl = ""
                        customAddonName = ""
                        showCustomAddonInput = false
                    }
                },
                onDismiss = {
                    customAddonUrl = ""
                    customAddonName = ""
                    showCustomAddonInput = false
                }
            )
        }

        // TorBox API Key Input Modal
        if (showTorBoxInput) {
            InputModal(
                title = "Enter TorBox API Key",
                fields = listOf(
                    InputField("API Key", torBoxApiKey) { torBoxApiKey = it }
                ),
                onConfirm = {
                    if (torBoxApiKey.isNotBlank()) {
                        viewModel.setTorBoxApiKey(torBoxApiKey)
                        torBoxApiKey = ""
                        showTorBoxInput = false
                    }
                },
                onDismiss = {
                    torBoxApiKey = ""
                    showTorBoxInput = false
                }
            )
        }

        if (showSubtitlePicker) {
            SubtitlePickerModal(
                options = uiState.subtitleOptions,
                selected = uiState.defaultSubtitle,
                focusedIndex = subtitlePickerIndex,
                onFocusChange = { subtitlePickerIndex = it },
                onSelect = {
                    viewModel.setDefaultSubtitle(it)
                    showSubtitlePicker = false
                },
                onDismiss = { showSubtitlePicker = false }
            )
        }

        // Toast notification
        uiState.toastMessage?.let { message ->
            Toast(
                message = message,
                type = when (uiState.toastType) {
                    ToastType.SUCCESS -> ComponentToastType.SUCCESS
                    ToastType.ERROR -> ComponentToastType.ERROR
                    ToastType.INFO -> ComponentToastType.INFO
                },
                isVisible = true,
                onDismiss = { viewModel.dismissToast() }
            )
        }
    }
}

private enum class Zone {
    SIDEBAR, SECTION, CONTENT
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsSectionItem(
    icon: ImageVector,
    title: String,
    isSelected: Boolean,
    isFocused: Boolean
) {
    val bgColor = when {
        isFocused -> Color.White.copy(alpha = 0.1f)
        isSelected -> Color.White.copy(alpha = 0.05f)
        else -> Color.Transparent
    }
    val textColor = when {
        isFocused -> Pink
        isSelected -> TextPrimary
        else -> TextSecondary
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = ArflixTypography.body,
            color = textColor
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GeneralSettings(
    defaultSubtitle: String,
    autoPlayNext: Boolean,
    focusedIndex: Int,
    onSubtitleClick: () -> Unit,
    onAutoPlayToggle: (Boolean) -> Unit
) {
    Column {
        Text(
            text = "Player Preferences",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Default Subtitle
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = "Default Subtitle",
            subtitle = "Preferred language for auto-selection",
            value = defaultSubtitle,
            isFocused = focusedIndex == 0,
            onClick = onSubtitleClick
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Auto-Play Next
        SettingsToggleRow(
            title = "Auto-Play Next",
            subtitle = "Start next episode automatically",
            isEnabled = autoPlayNext,
            isFocused = focusedIndex == 1,
            onToggle = onAutoPlayToggle
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    value: String,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary
                )
                Text(
                    text = subtitle,
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
            }
        }
        
        Text(
            text = value.uppercase(),
            style = ArflixTypography.label,
            color = Pink
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsToggleRow(
    title: String,
    subtitle: String,
    isEnabled: Boolean,
    isFocused: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                style = ArflixTypography.cardTitle,
                color = TextPrimary
            )
            Text(
                text = subtitle,
                style = ArflixTypography.caption,
                color = TextSecondary
            )
        }
        
        // Custom toggle indicator instead of Switch
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(26.dp)
                .background(
                    color = if (isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(13.dp)
                )
                .padding(3.dp),
            contentAlignment = if (isEnabled) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AddonsSettings(
    addons: List<com.arflix.tv.data.model.Addon> = emptyList(),
    focusedIndex: Int = -1,
    focusedActionIndex: Int = 0,
    onToggleAddon: (String) -> Unit = {},
    onDeleteAddon: (String) -> Unit = {},
    onAddCustomAddon: () -> Unit = {}
) {
    Column {
        Text(
            text = "Manage Addons",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (addons.isEmpty()) {
            Text(
                text = "No addons installed",
                style = ArflixTypography.body,
                color = TextSecondary
            )
        } else {
            addons.forEachIndexed { index, addon ->
                AddonRow(
                    addon = addon,
                    isFocused = focusedIndex == index,
                    focusedAction = if (focusedIndex == index) focusedActionIndex else -1,
                    onToggle = { onToggleAddon(addon.id) },
                    onDelete = { onDeleteAddon(addon.id) }
                )
                if (index < addons.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add custom addon button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (focusedIndex == addons.size) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                    RoundedCornerShape(12.dp)
                )
                .border(
                    width = if (focusedIndex == addons.size) 2.dp else 0.dp,
                    color = if (focusedIndex == addons.size) Pink else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Widgets,
                contentDescription = null,
                tint = Pink,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Add Custom Addon",
                style = ArflixTypography.button,
                color = Pink
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AddonRow(
    addon: com.arflix.tv.data.model.Addon,
    isFocused: Boolean,
    focusedAction: Int = -1, // 0 = toggle, 1 = delete
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    val isToggleFocused = isFocused && focusedAction == 0
    val isDeleteFocused = isFocused && focusedAction == 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Pink.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Widgets,
                    contentDescription = null,
                    tint = Pink,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = addon.name,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary
                )
                Text(
                    text = addon.description,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Toggle indicator with focus highlight
            Box(
                modifier = Modifier
                    .border(
                        width = if (isToggleFocused) 2.dp else 0.dp,
                        color = if (isToggleFocused) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(13.dp)
                    )
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(26.dp)
                        .background(
                            color = if (addon.isInstalled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(13.dp)
                        )
                        .padding(3.dp),
                    contentAlignment = if (addon.isInstalled) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                }
            }

            // Delete button
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = if (isDeleteFocused) Color(0xFFEF4444) else Color.White.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = if (isDeleteFocused) 2.dp else 0.dp,
                        color = if (isDeleteFocused) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete addon",
                    tint = if (isDeleteFocused) Color.White else TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountsSettings(
    isLoggedIn: Boolean,
    accountEmail: String?,
    isTraktAuthenticated: Boolean,
    traktCode: String?,
    traktUrl: String?,
    isTraktPolling: Boolean,
    traktExpiration: String? = null,
    focusedIndex: Int,
    onConnectTrakt: () -> Unit,
    onDisconnectTrakt: () -> Unit
) {
    Column {
        Text(
            text = "Linked Accounts",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        AccountActionRow(
            title = "ARVIO Account",
            description = accountEmail ?: "Not signed in",
            actionLabel = if (isLoggedIn) "SIGN OUT" else "SIGN IN",
            isEnabled = isLoggedIn,
            isFocused = focusedIndex == 0
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Trakt.tv
        AccountRow(
            name = "Trakt.tv",
            description = "Sync watch history, progress, and watchlist",
            isConnected = isTraktAuthenticated,
            isPolling = isTraktPolling,
            authCode = traktCode,
            authUrl = traktUrl,
            isFocused = focusedIndex == 1,
            onConnect = onConnectTrakt,
            onDisconnect = onDisconnectTrakt,
            expirationText = traktExpiration?.let { "Expires: $it" }
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountActionRow(
    title: String,
    description: String,
    actionLabel: String,
    isEnabled: Boolean,
    isFocused: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle,
                color = TextPrimary
            )
            Text(
                text = description,
                style = ArflixTypography.caption,
                color = TextSecondary
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    if (isEnabled) Pink.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(
                imageVector = if (isEnabled) Icons.Default.LinkOff else Icons.Default.Link,
                contentDescription = null,
                tint = if (isEnabled) Pink else TextSecondary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = actionLabel,
                style = ArflixTypography.label,
                color = if (isEnabled) Pink else TextSecondary
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ApiKeyAccountRow(
    name: String,
    description: String,
    isConnected: Boolean,
    apiKey: String?,
    isFocused: Boolean,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary
                )
                Text(
                    text = description,
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
            }

            if (isConnected) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(SuccessGreen.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "CONNECTED",
                        style = ArflixTypography.label,
                        color = SuccessGreen
                    )
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Pink.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = null,
                        tint = Pink,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ENTER API KEY",
                        style = ArflixTypography.label,
                        color = Pink
                    )
                }
            }
        }

        // Show API key hint when not connected
        if (!isConnected) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Get your API key from torbox.app/settings",
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.6f)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountRow(
    name: String,
    description: String,
    isConnected: Boolean,
    isPolling: Boolean,
    authCode: String?,
    authUrl: String?,
    isFocused: Boolean,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    expirationText: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary
                )
                Text(
                    text = description,
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
            }
            
            if (isConnected) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(SuccessGreen.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "CONNECTED",
                        style = ArflixTypography.label,
                        color = SuccessGreen
                    )
                }
            } else if (isPolling) {
                LoadingIndicator(
                    color = Pink,
                    size = 24.dp,
                    strokeWidth = 2.dp
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Pink.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = null,
                        tint = Pink,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "CONNECT",
                        style = ArflixTypography.label,
                        color = Pink
                    )
                }
            }
        }
        
        // Show expiration date when connected
        if (isConnected && expirationText != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = expirationText,
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.7f)
            )
        }

        // Show auth code when polling
        if (isPolling && authCode != null && authUrl != null) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Go to:",
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Text(
                    text = authUrl,
                    style = ArflixTypography.body,
                    color = Pink
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Enter code:",
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Text(
                    text = authCode,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Waiting for authorization...",
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

/**
 * Data class for input field
 */
data class InputField(
    val label: String,
    val value: String,
    val onValueChange: (String) -> Unit
)

/**
 * Input modal for text entry (custom addon URL, API keys, etc.)
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun InputModal(
    title: String,
    fields: List<InputField>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    // Track which element is focused: 0 to fields.size-1 = text fields, fields.size = paste button, fields.size+1 = cancel, fields.size+2 = confirm
    var focusedIndex by remember { mutableIntStateOf(0) } // Start on first text field
    val totalItems = fields.size + 3 // fields + paste + cancel + confirm

    // Create focus requesters for each text field
    val fieldFocusRequesters = remember { fields.map { FocusRequester() } }

    // Clipboard manager for paste functionality
    val clipboardManager = LocalClipboardManager.current

    // Request focus on first field when modal opens
    LaunchedEffect(Unit) {
        if (fieldFocusRequesters.isNotEmpty()) {
            fieldFocusRequesters[0].requestFocus()
        }
    }

    // Request focus when focusedIndex changes to a text field
    LaunchedEffect(focusedIndex) {
        if (focusedIndex < fields.size && focusedIndex >= 0) {
            fieldFocusRequesters[focusedIndex].requestFocus()
        }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .width(550.dp)
                .background(BackgroundElevated, RoundedCornerShape(16.dp))
                .padding(32.dp)
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusedIndex > 0) {
                                    focusedIndex--
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusedIndex < totalItems - 1) {
                                    focusedIndex++
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                if (focusedIndex == fields.size + 2) {
                                    focusedIndex = fields.size + 1
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                if (focusedIndex == fields.size + 1) {
                                    focusedIndex = fields.size + 2
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                when {
                                    focusedIndex == fields.size -> {
                                        // Paste button - paste clipboard to first field (URL)
                                        val clipboardText = clipboardManager.getText()?.text
                                        if (clipboardText != null && fields.isNotEmpty()) {
                                            fields[0].onValueChange(clipboardText)
                                        }
                                        true
                                    }
                                    focusedIndex == fields.size + 1 -> {
                                        onDismiss()
                                        true
                                    }
                                    focusedIndex == fields.size + 2 -> {
                                        onConfirm()
                                        true
                                    }
                                    else -> false // Let text field handle Enter
                                }
                            }
                            else -> false
                        }
                    } else false
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Input fields
            fields.forEachIndexed { index, field ->
                val isFocused = focusedIndex == index

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = field.label,
                        style = ArflixTypography.caption,
                        color = if (isFocused) Pink else TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    androidx.compose.material3.TextField(
                        value = field.value,
                        onValueChange = field.onValueChange,
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Enter ${field.label.lowercase()}...",
                                color = TextSecondary.copy(alpha = 0.5f)
                            )
                        },
                        textStyle = ArflixTypography.body.copy(color = TextPrimary),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                            focusedIndicatorColor = Pink,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Pink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(fieldFocusRequesters[index])
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) Pink else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }

                if (index < fields.size - 1) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Paste button
            val isPasteFocused = focusedIndex == fields.size
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (isPasteFocused) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = if (isPasteFocused) 2.dp else 0.dp,
                        color = if (isPasteFocused) Pink else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ContentPaste,
                    contentDescription = "Paste",
                    tint = if (isPasteFocused) Pink else TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Paste from Clipboard",
                    style = ArflixTypography.button,
                    color = if (isPasteFocused) Pink else TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cancel button
                val isCancelFocused = focusedIndex == fields.size + 1
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (isCancelFocused) 2.dp else 0.dp,
                            color = if (isCancelFocused) Pink else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancel",
                        style = ArflixTypography.button,
                        color = if (isCancelFocused) TextPrimary else TextSecondary
                    )
                }

                // Confirm button
                val isConfirmFocused = focusedIndex == fields.size + 2
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isConfirmFocused) SuccessGreen else Pink.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (isConfirmFocused) 2.dp else 0.dp,
                            color = if (isConfirmFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Confirm",
                        style = ArflixTypography.button,
                        color = Color.White
                    )
                }
            }

            // Hint text
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Press Enter to select • Navigate with D-pad",
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.5f)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SubtitlePickerModal(
    options: List<String>,
    selected: String,
    focusedIndex: Int,
    onFocusChange: (Int) -> Unit,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()
    val safeIndex = focusedIndex.coerceIn(0, (options.size - 1).coerceAtLeast(0))

    LaunchedEffect(safeIndex) {
        if (options.isNotEmpty()) {
            listState.animateScrollToItem(safeIndex)
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            onDismiss()
                            true
                        }
                        Key.DirectionUp -> {
                            if (safeIndex > 0) onFocusChange(safeIndex - 1)
                            true
                        }
                        Key.DirectionDown -> {
                            if (safeIndex < options.size - 1) onFocusChange(safeIndex + 1)
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            if (options.isNotEmpty()) {
                                onSelect(options[safeIndex])
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
                .width(520.dp)
                .background(BackgroundElevated, RoundedCornerShape(16.dp))
                .padding(28.dp)
        ) {
            Text(
                text = "Default Subtitles",
                style = ArflixTypography.sectionTitle,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier.heightIn(max = 360.dp)
            ) {
                itemsIndexed(options) { index, option ->
                    val isFocused = index == safeIndex
                    val isSelected = option.equals(selected, ignoreCase = true)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.Transparent,
                                RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) Pink else Color.White.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            style = ArflixTypography.body,
                            color = if (isFocused) TextPrimary else TextSecondary,
                            modifier = Modifier.weight(1f)
                        )
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = SuccessGreen,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Press Enter to select",
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
